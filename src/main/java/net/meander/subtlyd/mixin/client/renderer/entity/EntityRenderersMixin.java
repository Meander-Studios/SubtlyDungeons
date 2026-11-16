package net.meander.subtlyd.mixin.client.renderer.entity;

import net.meander.subtlyd.client.renderer.entity.EntityRenderersSD;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.WritableRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderers.class)
public class EntityRenderersMixin {
    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerModEntityRenderers(WritableRegistry<EntityRendererProvider<?>> registry, CallbackInfo ci) {
        EntityRenderersSD.bootstrap(registry);
    }
}
