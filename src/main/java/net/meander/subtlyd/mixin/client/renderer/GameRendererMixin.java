package net.meander.subtlyd.mixin.client.renderer;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow protected abstract void takeAutoScreenshot(Path screenshotFile);
    private static final int SCREENSHOT_WIDTH = 455;
    private static final int SCREENSHOT_HEIGHT = 256;

    @ModifyVariable(method = "lambda$takeAutoScreenshot$1", at = @At("STORE"), name = "scaled")
    private static NativeImage setScreenshotRatio(NativeImage scaled) {
        return new NativeImage(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT, false);
    }

    @Redirect(method = "lambda$takeAutoScreenshot$1", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/NativeImage;resizeSubRectTo(IIIILcom/mojang/blaze3d/platform/NativeImage;)V"))
    private static void cropScreenshot(NativeImage instance, int sourceX, int sourceY, int sizeX, int sizeY, NativeImage _to, @Local(name = "screenshot", argsOnly = true) NativeImage screenshot) {
        int width = screenshot.getWidth();
        int height = screenshot.getHeight();

        int cropX = 0;
        int cropY = 0;
        int cropWidth = width;
        int cropHeight = height;

        float targetRatio = (float) SCREENSHOT_WIDTH / SCREENSHOT_HEIGHT;
        float sourceRatio = (float) cropWidth / cropHeight;

        if (sourceRatio > targetRatio) {
            cropWidth = (int) (height * targetRatio);
            cropX = (width - cropWidth) / 2;
        } else {
            cropHeight = (int) (width / targetRatio);
            cropY = (height - cropHeight) / 2;
        }

        instance.resizeSubRectTo(cropX, cropY, cropWidth, cropHeight, _to);
    }

    @Redirect(method = "tryTakeScreenshotIfNeeded", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/GameRenderer;hasWorldScreenshot:Z", opcode = Opcodes.GETFIELD))
    private boolean captureScreenshot(GameRenderer instance) {
        return false;
    }

    @Inject(method = "lambda$tryTakeScreenshotIfNeeded$0", at = @At("TAIL"))
    private void captureScreenshot(Path path, CallbackInfo ci) {
        takeAutoScreenshot(path);
    }
}
