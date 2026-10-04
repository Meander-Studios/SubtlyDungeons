package net.meander.subtlyd.mixin.client.gui.screens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.gui.screens.FaviconTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FaviconTexture.class)
public class FaviconTextureMixin {
    @WrapOperation(method = "upload", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/NativeImage;getWidth()I", ordinal = 0))
    private int allowNewWidth(NativeImage instance, Operation<Integer> original) {
        int width = original.call(instance);

        if (width == 455 && instance.getHeight() == 256) {
            return 64;
        }

        return width;
    }

    @WrapOperation(method = "upload", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/NativeImage;getHeight()I", ordinal = 0))
    private int allowNewHeight(NativeImage instance, Operation<Integer> original) {
        int height = original.call(instance);

        if (height == 256 && instance.getWidth() == 455) {
            return 64;
        }

        return height;
    }

    @ModifyArg(method = "upload", at = @At(value = "INVOKE", target = "Ljava/lang/IllegalArgumentException;<init>(Ljava/lang/String;)V"))
    private String modifyExceptionMessage(String original) {
        return original.replace("64x64", "64x64 or 455x256");
    }
}