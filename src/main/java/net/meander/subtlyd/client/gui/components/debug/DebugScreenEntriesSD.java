package net.meander.subtlyd.client.gui.components.debug;

import net.meander.subtlyd.util.UtilSD;
import net.minecraft.client.core.registries.ClientRegistries;
import net.minecraft.client.gui.components.debug.DebugEntryNoop;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

/**
 * @see net.minecraft.client.gui.components.debug.DebugScreenEntries
 */
public class DebugScreenEntriesSD {
    public static final ResourceKey<DebugScreenEntry> ENTITY_CULLING_METHOD = key("entity_culling_method");
    public static final ResourceKey<DebugScreenEntry> VISUALIZE_ENTITY_OCCLUSION = key("vizualize_entity_occlusion");

    private static ResourceKey<DebugScreenEntry> key(final String name) {
        return ResourceKey.create(ClientRegistries.DEBUG_SCREEN_ENTRY, UtilSD.identifier(name));
    }

    public static DebugScreenEntry bootstrap(final Registry<DebugScreenEntry> registry) {
        Registry.register(registry, ENTITY_CULLING_METHOD, new DebugEntryEntityCullingMethod());
        return Registry.register(registry, VISUALIZE_ENTITY_OCCLUSION, new DebugEntryNoop());
    }
}
