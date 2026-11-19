package net.meander.subtlyd.world.entity.monster;

import net.meander.subtlyd.tags.BiomeTagsSD;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.apache.commons.lang3.tuple.Triple;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class MonsterSD {
    private static final Triple<EntityType<? extends Monster>, EntityType<? extends Monster>, EntityType<? extends Monster>> ZOMBIES = Triple.of(EntityTypes.ZOMBIE, EntityTypes.FROSTBITE, EntityTypes.HUSK);
    private static final Triple<EntityType<? extends Monster>, EntityType<? extends Monster>, EntityType<? extends Monster>> SKELETONS = Triple.of(EntityTypes.SKELETON, EntityTypes.STRAY, EntityTypes.PARCHED);

    public static void spawnTemperatureVariant(
            Monster monster,
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        if (spawnReason == EntitySpawnReason.NATURAL || spawnReason == EntitySpawnReason.REINFORCEMENT || spawnReason == EntitySpawnReason.CHUNK_GENERATION) {
            RandomSource random = level.getRandom();
            BlockPos pos = monster.blockPosition();

            Triple<EntityType<? extends Monster>, EntityType<? extends Monster>, EntityType<? extends Monster>> selected;

            if (monster.is(ZOMBIES.getLeft())) {
                selected = ZOMBIES;
            } else if (monster.is(SKELETONS.getLeft())) {
                selected = SKELETONS;
            } else {
                return;
            }

            if (difficulty.isHarderThan(random.nextFloat() * 3.0F)) {
                Holder<Biome> biome = level.getBiome(pos);
                Monster variant = null;
                ServerLevel serverLevel = level.getLevel();

                if (level.canSeeSky(pos)) {
                    if (biome.is(BiomeTagsSD.SPAWNS_COLD_VARIANT_MONSTERS)) {
                        variant = selected.getMiddle().create(serverLevel, spawnReason);
                    } else if (biome.is(BiomeTagsSD.SPAWNS_DRY_VARIANT_MONSTERS)) {
                        variant = selected.getRight().create(serverLevel, spawnReason);
                    }
                }

                if (variant != null) {
                    variant.moveOrInterpolateTo(monster.position(), monster.getYRot(), monster.getXRot());
                    variant.finalizeSpawn(level, difficulty, spawnReason, groupData);
                    level.addFreshEntity(variant);
                    monster.discard();
                    cir.setReturnValue(groupData);
                }
            }
        }
    }
}
