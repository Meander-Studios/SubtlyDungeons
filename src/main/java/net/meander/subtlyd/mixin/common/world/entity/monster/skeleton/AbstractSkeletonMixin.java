package net.meander.subtlyd.mixin.common.world.entity.monster.skeleton;

import net.meander.subtlyd.tags.BiomeTagsSD;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin {
    @Inject(method = "finalizeSpawn", at = @At("HEAD"), cancellable = true)
    private void convertToVariantOnHard(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        AbstractSkeleton skeleton = (AbstractSkeleton) (Object) this;

        if (!(skeleton.is(EntityTypes.PARCHED) || skeleton.is(EntityTypes.STRAY))) {
            if (level.getDifficulty() == Difficulty.HARD) {
                BlockPos pos = skeleton.blockPosition();

                if (level.getCurrentDifficultyAt(pos).isHarderThan(level.getRandom().nextFloat() * 3.0F)) {
                    AbstractSkeleton variant = null;
                    Holder<Biome> biome = level.getBiome(pos);

                    if (biome.is(BiomeTagsSD.SPAWNS_COLD_VARIANT_MONSTERS)) {
                        variant = EntityTypes.STRAY.create(level.getLevel(), spawnReason);
                    } else if (biome.is(BiomeTagsSD.SPAWNS_WARM_VARIANT_MONSTERS)) {
                        variant = EntityTypes.PARCHED.create(level.getLevel(), spawnReason);
                    }

                    if (variant != null) {
                        variant.moveOrInterpolateTo(skeleton.position(), skeleton.getYRot(), skeleton.getXRot());
                        variant.finalizeSpawn(level, difficulty, spawnReason, groupData);
                        level.addFreshEntity(variant);
                        skeleton.discard();
                        cir.setReturnValue(groupData);
                    }
                }
            }
        }
    }
}
