package net.meander.subtlyd.mixin.client.gui.screens.multiplayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerSelectionList.class)
public class ServerSelectionListMixin {
    @Shadow @Final private JoinMultiplayerScreen screen;
    private static final boolean USE_EXPERIMENTAL_GUI = Minecraft.getInstance().options.experimentalGui().get();

    @Inject(method = "getRowWidth", at = @At("RETURN"), cancellable = true)
    public void getRowWidth(CallbackInfoReturnable<Integer> cir) {
        if (USE_EXPERIMENTAL_GUI) {
            cir.setReturnValue(screen.width - 8);
        }
    }
}
