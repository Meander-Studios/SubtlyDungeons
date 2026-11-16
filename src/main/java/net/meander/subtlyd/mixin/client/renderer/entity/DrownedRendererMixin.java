package net.meander.subtlyd.mixin.client.renderer.entity;

import net.meander.subtlyd.client.renderer.entity.state.UndeadRenderStateSD;
import net.meander.subtlyd.util.UtilSD;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DrownedRenderer.class)
public class DrownedRendererMixin {
    private final Identifier DROWNED_LEADER_LOCATION = UtilSD.identifier("textures/entity/zombie/drowned_leader.png");
    private final Identifier GURGLE_LEADER_LOCATION = UtilSD.identifier("textures/entity/zombie/drowned_leader_baby.png");

    @Inject(
            method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/ZombieRenderState;)Lnet/minecraft/resources/Identifier;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void modifyLeaderTexture(ZombieRenderState state, CallbackInfoReturnable<Identifier> cir) {
        Identifier leaderLocation = cir.getReturnValue();

        if (((UndeadRenderStateSD) state).isLeader()) {
            leaderLocation = state.isBaby ? GURGLE_LEADER_LOCATION : DROWNED_LEADER_LOCATION;
        }

        cir.setReturnValue(leaderLocation);
    }
}
