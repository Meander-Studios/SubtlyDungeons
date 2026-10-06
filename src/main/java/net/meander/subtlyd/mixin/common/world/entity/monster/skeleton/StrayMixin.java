package net.meander.subtlyd.mixin.common.world.entity.monster.skeleton;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.skeleton.Stray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Stray.class)
public class StrayMixin {
    @ModifyArgs(
            method = "getArrow",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;I)V")
    )
    private void setFreezingArrow(Args args) {
        args.set(0, MobEffects.FREEZING);
        args.set(1, 220);
    }
}
