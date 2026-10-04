package net.meander.subtlyd.mixin.client.gui.screens.worldselection;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.meander.subtlyd.client.gui.screens.CustomTerrainSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SelectableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.nio.file.Path;

@Mixin(WorldSelectionList.class)
public class WorldSelectionListMixin {
    @Shadow @Final private Screen screen;
    private static final boolean USE_EXPERIMENTAL_GUI = Minecraft.getInstance().options.experimentalGui().get();

    @Inject(method = "getRowWidth", at = @At("RETURN"), cancellable = true)
    public void getRowWidth(CallbackInfoReturnable<Integer> cir) {
        if (USE_EXPERIMENTAL_GUI) {
            cir.setReturnValue(screen.width - 8);
        }
    }

    @Mixin(targets = "net.minecraft.client.gui.screens.worldselection.WorldSelectionList$WorldListEntry")
    private abstract static class WorldListEntryMixin extends WorldSelectionList.Entry implements SelectableEntry {
        @Shadow @Final private LevelSummary summary;
        private static final boolean USE_EXPERIMENTAL_GUI = Minecraft.getInstance().options.experimentalGui().get();
        private static final int ICON_WIDTH = 57;
        private static final int ICON_HEIGHT = 32;

        @Inject(method = "recreateWorld", at = @At("HEAD"))
        private void loadRecreateSettings(CallbackInfo ci) {
            Path oldWorldDir = Minecraft.getInstance().gameDirectory.toPath().resolve("saves").resolve(summary.getLevelId());

            CustomTerrainSettings.loadSettingsFromFile(oldWorldDir);
        }

        @ModifyArgs(method = "extractContent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"))
        private void modifyWorldIconWidth(Args args) {
            if (USE_EXPERIMENTAL_GUI) {
                args.set(6, ICON_WIDTH);
                args.set(8, ICON_WIDTH);
            }
        }

        @ModifyArg(method = "extractContent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V"), index = 2)
        private int modifyWorldIconFillWidth(int x1) {
            if (USE_EXPERIMENTAL_GUI) {
                return getContentX() + ICON_WIDTH;
            }

            return x1;
        }

        /**
         * Replaces the isMouseOver with isMouseWithin
         */
        @Redirect(method = "extractContent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/worldselection/WorldSelectionList$WorldListEntry;mouseOverIcon(III)Z"))
        private boolean modifyIsOverIcon(WorldSelectionList.WorldListEntry instance, final int relX, final int relY, final int size, final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final boolean hovered, final float a) {
            if (USE_EXPERIMENTAL_GUI) {
                return isMouseWithin(mouseX, mouseY, getContentX() + ICON_WIDTH, getContentY() + size);
            }

            return instance.mouseOverIcon(relX, relY, size);
        }

        @Redirect(method = "extractContent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
        private void modifyPlayButtonIconSprite(GuiGraphicsExtractor instance, final RenderPipeline renderPipeline, final Identifier location, final int x, final int y, final int width, final int height) {
            if (USE_EXPERIMENTAL_GUI) {
                int MID_ICON = getContentX() + ICON_WIDTH / 4;

                instance.blitSprite(RenderPipelines.GUI_TEXTURED, location, MID_ICON, getContentY(), width, height);
            }
        }

        @Inject(method = "getTextX", at = @At("RETURN"), cancellable = true)
        private void extendTextWidth(CallbackInfoReturnable<Integer> cir) {
            if (USE_EXPERIMENTAL_GUI) {
                cir.setReturnValue(getContentX() + ICON_WIDTH + 3);
            }
        }

        /**
         * Replaces isMouseOver with isMouseWithin based on a mouseButtonEvent.
         */
        @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/worldselection/WorldSelectionList$WorldListEntry;mouseOverIcon(III)Z"))
        private boolean modifyIsClickedIcon(WorldSelectionList.WorldListEntry instance, int relX, int relY, int size, final MouseButtonEvent event, final boolean doubleClick) {
            if (USE_EXPERIMENTAL_GUI) {
                return isMouseWithin((int) event.x(), (int) event.y(), getContentX() + ICON_WIDTH, getContentY() + ICON_HEIGHT);
            }

            return instance.mouseOverIcon(relX, relY, size);
        }

        /**
         * Tests if the mouse is within a specified rectangle.
         * @param mouseX The mouse's x-position.
         * @param mouseY THe mouse's y-position.
         * @param right The right edge of the rectangle.
         * @param top The top edge of the rectangle.
         * @return Whether the mouse is within this rectangle or not.
         */
        private boolean isMouseWithin(final int mouseX, final int mouseY, final int right, int top) {
            return mouseX >= getContentX()
                    && mouseX < right
                    && mouseY >= getContentY()
                    && mouseY < top;
        }
    }
}
