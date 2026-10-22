package net.meander.subtlyd.core.registries;

import net.meander.subtlyd.client.gui.components.debug.DebugScreenEntriesSD;
import net.meander.subtlyd.client.renderer.entity.EntityRenderersSD;
import net.minecraft.client.core.registries.ClientRegistries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltinRegistryBuilder;

public class BuiltInClientRegistriesSD {
    private static final BuiltinRegistryBuilder BUILDER = new BuiltinRegistryBuilder(ClientRegistries.ROOT_REGISTRY_NAME);
    public static final Registry<EntityRendererProvider<?>> ENTITY_RENDERER = BUILDER.registerSimple(
            ClientRegistries.ENTITY_RENDERER, EntityRenderersSD::bootstrap
    );
    public static final Registry<DebugScreenEntry> DEBUG_SCREEN_ENTRY = BUILDER.registerSimple(
            ClientRegistries.DEBUG_SCREEN_ENTRY, DebugScreenEntriesSD::bootstrap
    );
    public static final Registry<? extends Registry<?>> REGISTRY = BUILDER.build();

    public static void bootstrap() {
        BUILDER.bootStrap();
    }
}