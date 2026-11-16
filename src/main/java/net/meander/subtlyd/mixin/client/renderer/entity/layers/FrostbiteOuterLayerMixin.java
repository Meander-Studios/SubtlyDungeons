package net.meander.subtlyd.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.meander.subtlyd.client.renderer.entity.state.UndeadRenderStateSD;
import net.meander.subtlyd.util.UtilSD;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.FrostbiteOuterLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FrostbiteOuterLayer.class)
public class FrostbiteOuterLayerMixin {
    private static final Identifier FROSTBITE_OUTER_LAYER_LOCATION = Identifier.withDefaultNamespace("textures/entity/zombie/frostbite_outer_layer.png");
    private static final Identifier BABY_FROSTBITE_OUTER_LAYER_LOCATION = Identifier.withDefaultNamespace("textures/entity/zombie/frostbite_outer_layer_baby.png");
    private static final Identifier FROSTBITE_LEADER_OUTER_LAYER_LOCATION = UtilSD.identifier("textures/entity/zombie/frostbite_leader_outer_layer.png");
    private static final Identifier BABY_FROSTBITE_LEADER_OUTER_LAYER_LOCATION = UtilSD.identifier("textures/entity/zombie/frostbite_leader_outer_layer_baby.png");
    private Identifier layerLocation;

    @Inject(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/ZombieRenderState;FF)V", at = @At("HEAD"))
    private void declareLayerLocation(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, ZombieRenderState state, float yRot, float xRot, CallbackInfo ci) {
        layerLocation = ((UndeadRenderStateSD) state).isLeader() ? (state.isBaby ? BABY_FROSTBITE_LEADER_OUTER_LAYER_LOCATION : FROSTBITE_LEADER_OUTER_LAYER_LOCATION) : (state.isBaby ? BABY_FROSTBITE_OUTER_LAYER_LOCATION : FROSTBITE_OUTER_LAYER_LOCATION);
    }

    @ModifyArg(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/ZombieRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/FrostbiteOuterLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/Identifier;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V"),
            index = 1
    )
    private Identifier setLeaderOuterLayer(Identifier identifier) {
        return layerLocation;
    }
}
