package net.meander.subtlyd.tags;

import net.meander.subtlyd.util.UtilSD;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

/**
 * @see net.minecraft.tags.BiomeTags
 */
public class BiomeTagsSD {
    public static final TagKey<Biome> IS_WINDY = create("is_windy");
    public static final TagKey<Biome> IS_SLIGHTLY_FOGGY = create("is_slightly_foggy");
    public static final TagKey<Biome> IS_VERY_FOGGY = create("is_very_foggy");
    public static final TagKey<Biome> IS_FOGGY = create("is_foggy");
    public static final TagKey<Biome> HAS_CESPITOSE = create("has_cespitose");
    public static final TagKey<Biome> SPAWNS_COLD_VARIANT_MONSTERS = create("spawns_cold_variant_monsters");
    public static final TagKey<Biome> SPAWNS_DRY_VARIANT_MONSTERS = create("spawns_dry_variant_monsters");
    public static final TagKey<Biome> SPAWNS_COLOSSAL_SQUIDS = create("spawns_colossal_squids");

    private static TagKey<Biome> create(String string) {
        return TagKey.create(Registries.BIOME, UtilSD.identifier(string));
    }
}
