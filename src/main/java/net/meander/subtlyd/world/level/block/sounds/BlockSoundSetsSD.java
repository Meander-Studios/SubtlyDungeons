package net.meander.subtlyd.world.level.block.sounds;

import net.meander.subtlyd.sounds.SoundEventsSD;
import net.meander.subtlyd.util.UtilSD;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.sounds.BlockSoundSet;

/**
 * @see net.minecraft.world.level.block.sounds.BlockSoundSets
 */
public class BlockSoundSetsSD {
    public static final ResourceKey<BlockSoundSet> SNOW_BRICKS = key("sound_bricks");

    public static void bootstrap(final BootstrapContext<BlockSoundSet> context) {
        context.register(SNOW_BRICKS, new BlockSoundSet(SoundEventsSD.SNOW_BRICK));
    }

    private static ResourceKey<BlockSoundSet> key(final String id) {
        return ResourceKey.create(Registries.BLOCK_SOUND_SET, UtilSD.identifier(id));
    }
}
