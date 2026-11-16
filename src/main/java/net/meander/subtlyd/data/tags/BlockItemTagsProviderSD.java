package net.meander.subtlyd.data.tags;

import net.meander.subtlyd.references.BlockItemIdsSD;
import net.meander.subtlyd.tags.BlockItemTagsSD;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.tags.BlockItemTagId;

import java.util.function.Function;

/**
 * @see net.minecraft.data.tags.BlockItemTagsProvider
 */
public class BlockItemTagsProviderSD extends BlockItemTagsProvider {
    public BlockItemTagsProviderSD(final Function<BlockItemTagId, CombinedAppender> tagSupplier) {
        super(tagSupplier);
    }

    @Override
    protected void run() {
        tag(BlockItemTagsSD.STONE_TILES)
                .add(BlockItemIdsSD.STONE_TILES)
                .add(BlockItemIdsSD.STONE_TILE_STAIRS)
                .add(BlockItemIdsSD.STONE_TILE_SLAB)
                .add(BlockItemIdsSD.STONE_TILE_WALL);
        tag(BlockItemTagsSD.DRIPSTONE)
                .add(BlockItemIdsSD.CHISELED_POLISHED_DRIPSTONE)
                .add(BlockItemIdsSD.POLISHED_DRIPSTONE)
                .add(BlockItemIdsSD.POLISHED_DRIPSTONE_STAIRS)
                .add(BlockItemIdsSD.POLISHED_DRIPSTONE_SLAB)
                .add(BlockItemIdsSD.POLISHED_DRIPSTONE_WALL);
        tag(BlockItemTagsSD.DYED_TERRACOTTA_STAIRS)
                .addAll(BlockItemIdsSD.DYED_TERRACOTTA_STAIRS.asList());
        tag(BlockItemTagsSD.DYED_TERRACOTTA_SLABS)
                .addAll(BlockItemIdsSD.DYED_TERRACOTTA_SLAB.asList());
    }
}
