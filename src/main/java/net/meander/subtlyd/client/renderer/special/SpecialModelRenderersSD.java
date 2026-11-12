package net.meander.subtlyd.client.renderer.special;

import com.mojang.serialization.MapCodec;
import net.meander.subtlyd.util.UtilSD;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.core.Registry;

/**
 * @see SpecialModelRenderers
 */
public class SpecialModelRenderersSD {
    public static MapCodec<? extends SpecialModelRenderer.Unbaked<?>> bootstrap(final Registry<MapCodec<? extends SpecialModelRenderer.Unbaked<?>>> registry) {
        UtilSD.LOGGER.debug("Mapping special model renderers...");

        return Registry.register(registry, UtilSD.identifier("heavy_shield"), HeavyShieldSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
