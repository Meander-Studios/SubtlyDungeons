package net.meander.subtlyd.mixin.common.world.entity.animal;

import net.meander.subtlyd.tags.BiomeTagsSD;
import net.meander.subtlyd.world.level.GameRulesSD;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.animal.AgeableWaterCreature;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import net.minecraft.world.entity.animal.squid.Squid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Squid.class)
public abstract class SquidMixin extends AgeableWaterCreature {
    protected SquidMixin(EntityType<? extends AgeableWaterCreature> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void addPredatorGoal(CallbackInfo ci) {
        ServerLevel level = (ServerLevel) level();

        if (level.getGameRules().get(GameRulesSD.ADVANCED_MOBS)) {
            getGoalSelector().addGoal(1, new AvoidEntityGoal<>(this, Dolphin.class, 8.0F, 1.0, 1.0));
        }
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void spawnColossal(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        Squid squid = (Squid) (Object) this;

        if (level.getBiome(squid.blockPosition()).is(BiomeTagsSD.SPAWNS_COLOSSAL_SQUIDS)) {
            if (squid.getRandom().nextDouble() < 0.05) {
                AttributeInstance maxHealth = squid.getAttribute(Attributes.MAX_HEALTH);
                AttributeInstance scale = squid.getAttribute(Attributes.SCALE);

                if (maxHealth != null && scale != null) {
                    scale.setBaseValue(4.0);
                    maxHealth.setBaseValue(40.0);
                    squid.setHealth(40.0F);
                }
            }
        }
    }
}
