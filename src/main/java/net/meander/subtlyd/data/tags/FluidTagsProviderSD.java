package net.meander.subtlyd.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.meander.subtlyd.tags.FluidTagsSD;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.material.FluidIds;

import java.util.concurrent.CompletableFuture;

/**
 * @see FluidTagsProvider
 */
public class FluidTagsProviderSD extends FabricTagsProvider.FluidTagsProvider {
    public FluidTagsProviderSD(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(FluidTagsSD.SUPPORTS_REEDS)
                .add(FluidIds.WATER);
    }
}