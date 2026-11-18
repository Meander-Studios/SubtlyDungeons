package net.meander.subtlyd.mixin.common.world.entity.animal.golem;

import com.llamalad7.mixinextras.sugar.Local;
import net.meander.subtlyd.tags.BlockTagsSD;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.IceBall;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowGolem.class)
public abstract class SnowGolemMixin {
    @Inject(
            method = "performRangedAttack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/Projectile;spawnProjectile(Lnet/minecraft/world/entity/projectile/Projectile;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Ljava/util/function/Consumer;)Lnet/minecraft/world/entity/projectile/Projectile;"
            ),
            cancellable = true
    )
    private void spawnIceBallInstead(
            CallbackInfo ci,
            @Local(name = "xd") double xd,
            @Local(name = "yd")  double yd,
            @Local(name = "zd") double zd,
            @Local(name = "yo") double yo,
            @Local(name = "serverLevel") ServerLevel serverLevel
    ) {
        SnowGolem golem = (SnowGolem) (Object) this;

        if (golem.getBlockStateOn().is(BlockTagsSD.CHILLS_SNOW_GOLEM)) {
            ItemStack iceBallStack = new ItemStack(Items.ICE_BALL);

            Projectile.spawnProjectile(
                    new IceBall(serverLevel, golem, iceBallStack),
                    serverLevel,
                    iceBallStack,
                    projectile -> projectile.shoot(xd, yd + yo - projectile.getY(), zd, 1.6F, golem.rangedAttackUncertainty(serverLevel))
            );
            golem.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (golem.getRandom().nextFloat() * 0.4F + 0.8F));
            ci.cancel();
        }
    }
}