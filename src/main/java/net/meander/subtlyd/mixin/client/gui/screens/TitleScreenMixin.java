package net.meander.subtlyd.mixin.client.gui.screens;

import net.meander.subtlyd.client.renderer.gui.GuiAvatarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Shadow private boolean fading;
    private final int BUTTON_HEIGHT = 20;
    private final int SPRITE_XPOS = 4;
    private static final boolean USE_EXPERIMENTAL_GUI = Minecraft.getInstance().options.experimentalGui().get();

    protected TitleScreenMixin(Component component) {
        super(component);
    }

    @ModifyVariable(method = "init", at = @At(value = "STORE", ordinal = 2), name = "topPos")
    private int modifySpacing(int topPos) {
        if (USE_EXPERIMENTAL_GUI) {
            return topPos - 12;
        }

        return topPos;
    }

    @ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/SpriteIconButton;setPosition(II)V", ordinal = 0))
    private void setLanguagePos(Args args) {
        if (USE_EXPERIMENTAL_GUI) {
            args.set(0, SPRITE_XPOS);
            args.set(1, height - (BUTTON_HEIGHT + 4));
        }
    }

    @ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/SpriteIconButton;setPosition(II)V", ordinal = 1))
    private void setAccessibilityPos(Args args) {
        if (USE_EXPERIMENTAL_GUI) {
            args.set(0, SPRITE_XPOS + (BUTTON_HEIGHT + 4));
            args.set(1, height - (BUTTON_HEIGHT + 4));
        }
    }

    @ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/FriendsButton;setPosition(II)V"))
    private void setFriendsPos(Args args) {
        if (USE_EXPERIMENTAL_GUI) {
            args.set(0, SPRITE_XPOS + (BUTTON_HEIGHT + 4) * 2);
            args.set(1, height - (BUTTON_HEIGHT + 4));
        }
    }

    @Redirect(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V"))
    private void removeVersionText(GuiGraphicsExtractor graphics, Font font, String str, int x, int y, int color) {
        if (!USE_EXPERIMENTAL_GUI) {
            graphics.text(font, str, x, y, color);
        }
    }

    /**
     * Renders the player in the bottom right corner of the screen after the fade animation is complete, as player skins do not support transparency.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void extractAvatarRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        if (USE_EXPERIMENTAL_GUI) {
            final int avatarScale = 40;

            if (!fading) {
                GuiAvatarRenderer avatarRenderer = new GuiAvatarRenderer();

                avatarRenderer.extractRenderState(graphics, width / 2 + 170, height / 4 + 132, avatarScale, mouseX, mouseY);
            }
        }
    }
}
