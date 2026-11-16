package net.meander.subtlyd.mixin.common.world.entity;

import net.meander.subtlyd.datafix.DataFixersSD;
import net.meander.subtlyd.nbt.NbtUtilsSD;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityProcessor;
import net.minecraft.world.entity.EntitySpawnRequest;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(EntityType.class)
public class EntityTypeMixin {
    @Inject(method = "loadEntityRecursive(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/EntitySpawnRequest;Lnet/minecraft/world/entity/EntityProcessor;)Lnet/minecraft/world/entity/Entity;", at = @At("HEAD"))
    private static void applyDataFixer(CompoundTag tag, Level level, EntitySpawnRequest request, EntityProcessor postLoad, CallbackInfoReturnable<Entity> cir) {
        CompoundTag updated = NbtUtilsSD.update(References.ENTITY, tag, DataFixersSD.SNOW_BRICK_RENAMES);
        tag.merge(updated);
    }
}
