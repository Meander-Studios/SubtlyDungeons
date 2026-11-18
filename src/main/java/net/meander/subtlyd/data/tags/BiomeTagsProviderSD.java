package net.meander.subtlyd.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.meander.subtlyd.tags.BiomeTagsSD;
import net.meander.subtlyd.world.level.biome.BiomesSD;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

/**
 * @see net.minecraft.data.tags.BiomeTagsProvider
 */
public class BiomeTagsProviderSD extends FabricTagsProvider<Biome> {
    public BiomeTagsProviderSD(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, Registries.BIOME, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BiomeTagsSD.IS_WINDY)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.ICE_SPIKES)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.SNOWY_TAIGA)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.SNOWY_BEACH)
                .add(Biomes.FROZEN_PEAKS)
                .add(Biomes.JAGGED_PEAKS)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.GROVE);
        tag(BiomeTagsSD.IS_VERY_FOGGY)
                .add(Biomes.PALE_GARDEN);
        tag(BiomeTagsSD.IS_FOGGY)
                .add(Biomes.DARK_FOREST)
                .add(Biomes.SWAMP)
                .add(Biomes.MANGROVE_SWAMP);
        tag(BiomeTagsSD.IS_SLIGHTLY_FOGGY)
                .add(Biomes.JUNGLE)
                .add(Biomes.BAMBOO_JUNGLE);
        tag(BiomeTagsSD.HAS_CESPITOSE)
                .add(Biomes.DARK_FOREST);
        tag(BiomeTagsSD.SPAWNS_COLD_VARIANT_MONSTERS)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.SNOWY_BEACH)
                .add(Biomes.SNOWY_TAIGA)
                .add(Biomes.ICE_SPIKES)
                .add(Biomes.ICE_CAVES)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.GROVE)
                .add(Biomes.JAGGED_PEAKS)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.FROZEN_PEAKS);
        tag(BiomeTagsSD.SPAWNS_WARM_VARIANT_MONSTERS)
                .add(Biomes.DESERT)
                .add(Biomes.BADLANDS)
                .add(Biomes.ERODED_BADLANDS);
        tag(BiomeTagsSD.SPAWNS_COLOSSAL_SQUIDS)
                .add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN);
        tag(BiomeTags.IS_BEACH)
                .add(BiomesSD.GRAVEL_BEACH);
        tag(BiomeTags.IS_RIVER)
                .add(BiomesSD.WARM_RIVER);
        tag(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS)
                .add(BiomesSD.WARM_RIVER);
        tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
                .add(BiomesSD.WARM_RIVER);
    }
}
