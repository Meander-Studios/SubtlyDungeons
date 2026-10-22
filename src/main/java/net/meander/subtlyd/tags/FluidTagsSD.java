package net.meander.subtlyd.tags;

import net.meander.subtlyd.util.UtilSD;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

/**
 * @see net.minecraft.tags.FluidTags
 */
public class FluidTagsSD {
    public static final TagKey<Fluid> SUPPORTS_REEDS = create("supports_reeds");

    private static TagKey<Fluid> create(String name) {
        return TagKey.create(Registries.FLUID, UtilSD.identifier(name));
    }
}
