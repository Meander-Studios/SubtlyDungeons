package net.meander.subtlyd.mixin.common.world.entity.monster.zombie;

import net.meander.subtlyd.network.syncher.EntityDataAccessors;
import net.meander.subtlyd.world.entity.monster.MonsterSD;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public abstract class ZombieMixin {
    @Inject(method = "handleAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/zombie/Zombie;setCanBreakDoors(Z)V"))
    private void setLeader(CallbackInfo ci) {
        Zombie zombie = (Zombie) (Object) this;

        zombie.getEntityData().set(EntityDataAccessors.DATA_ID_ZOMBIE_LEADER, true);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void defineLeaderData(SynchedEntityData.Builder entityData, CallbackInfo ci) {
        entityData.define(EntityDataAccessors.DATA_ID_ZOMBIE_LEADER, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void saveLeaderData(ValueOutput output, CallbackInfo ci) {
        Zombie zombie = (Zombie) (Object) this;

        output.putBoolean("IsLeader", zombie.getEntityData().get(EntityDataAccessors.DATA_ID_ZOMBIE_LEADER));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void loadLeaderData(ValueInput input, CallbackInfo ci) {
        Zombie zombie = (Zombie) (Object) this;

        zombie.getEntityData().set(EntityDataAccessors.DATA_ID_ZOMBIE_LEADER, input.getBooleanOr("IsLeader", false));
    }

    @Inject(method = "finalizeSpawn", at = @At("HEAD"), cancellable = true)
    private void convertToVariantOnHard(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        Zombie zombie = (Zombie) (Object) this;

        MonsterSD.spawnTemperatureVariant(zombie, level, difficulty, spawnReason, groupData, cir);
    }
}