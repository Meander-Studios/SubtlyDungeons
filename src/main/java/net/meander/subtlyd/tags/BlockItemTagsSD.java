package net.meander.subtlyd.tags;

import net.meander.subtlyd.util.UtilSD;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;

/**
 * @see net.minecraft.tags.BlockTags
 */
public class BlockItemTagsSD {
    public static final BlockItemTagId STONE_TILES = create("stone_tiles");
    public static final BlockItemTagId DRIPSTONE = create("dripstone");
    public static final BlockItemTagId DYED_TERRACOTTA_STAIRS = create("dyed_terracotta_stairs");
    public static final BlockItemTagId DYED_TERRACOTTA_SLABS = create("dyed_terracotta_slabs");

    public static BlockItemTagId create(final String name) {
        Identifier id = UtilSD.identifier(name);

        return BlockItemTagId.create(id, id);
    }
}
