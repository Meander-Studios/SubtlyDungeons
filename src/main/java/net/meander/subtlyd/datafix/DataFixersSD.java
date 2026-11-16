package net.meander.subtlyd.datafix;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.meander.subtlyd.nbt.NbtUtilsSD;
import net.minecraft.SharedConstants;

import java.util.Map;

/**
 * @see net.minecraft.util.datafix.DataFixers
 */
public class DataFixersSD {
    private static final DataFixer FIXER = createFixer();
    public static final int MC_DATA_VERSION = SharedConstants.getCurrentVersion().dataVersion().version();

    public static final Map<String, String> SNOW_BRICK_RENAMES = Map.of(
            "subtlyd:snow_bricks", "minecraft:snow_bricks",
            "subtlyd:snow_brick_stairs", "minecraft:snow_brick_stairs",
            "subtlyd:snow_brick_slab", "minecraft:snow_brick_slab",
            "subtlyd:snow_brick_wall", "minecraft:snow_brick_wall"
    );

    private static DataFixer createFixer() {
        DataFixerBuilder fixerUpper = new DataFixerBuilder(MC_DATA_VERSION + NbtUtilsSD.DATA_VERSION);

        fixerUpper.addSchema(MC_DATA_VERSION, V0SD::new);

        Schema v1 = fixerUpper.addSchema(MC_DATA_VERSION + 1, V1SD::new);

        fixerUpper.addSchema(MC_DATA_VERSION + 2, V2SD::new);
        fixerUpper.addFixer(new TentEntityColorComponentFix(v1, true));
        return fixerUpper.build().fixer();
    }

    public static DataFixer getFixer() {
        return FIXER;
    }
}
