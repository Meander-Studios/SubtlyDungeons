package net.meander.subtlyd.client.renderer.entity;

import net.meander.subtlyd.client.model.geom.ModelLayersSD;
import net.meander.subtlyd.util.UtilSD;
import net.meander.subtlyd.world.entity.EntityTypesSD;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Registry;

/**
 * @see EntityRenderers
 */
public class EntityRenderersSD {
    public static EntityRendererProvider<?> bootstrap(final Registry<EntityRendererProvider<?>> registry) {
        UtilSD.LOGGER.debug("Registering entity renderers...");
        EntityRenderers.register(registry, EntityTypesSD.TENT, context -> new TentRenderer(context, ModelLayersSD.TENT));
        return EntityRenderers.register(registry, EntityTypesSD.BLAST_FUNGUS, ThrownItemRenderer::new);
    }
}
