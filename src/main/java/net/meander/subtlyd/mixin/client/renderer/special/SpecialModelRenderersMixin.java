package net.meander.subtlyd.mixin.client.renderer.special;

import com.mojang.serialization.MapCodec;
import net.meander.subtlyd.client.renderer.special.SpecialModelRenderersSD;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpecialModelRenderers.class)
public class SpecialModelRenderersMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerEntries(Registry<MapCodec<? extends SpecialModelRenderer.Unbaked<?>>> registry, CallbackInfoReturnable<MapCodec<? extends SpecialModelRenderer.Unbaked<?>>> cir) {
        SpecialModelRenderersSD.bootstrap(registry);
    }
}
