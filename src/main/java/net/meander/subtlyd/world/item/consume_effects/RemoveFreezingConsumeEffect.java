package net.meander.subtlyd.world.item.consume_effects;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;

/**
 * @see ApplyStatusEffectsConsumeEffect
 */
public record RemoveFreezingConsumeEffect() implements ConsumeEffect {
    public static final RemoveFreezingConsumeEffect INSTANCE = new RemoveFreezingConsumeEffect();
    public static final MapCodec<RemoveFreezingConsumeEffect> CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, RemoveFreezingConsumeEffect> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<RemoveFreezingConsumeEffect> getType() {
        return ConsumeEffectSD.Type.REDUCE_FREEZING;
    }

    @Override
    public boolean apply(final Level level, final ItemStack stack, final LivingEntity user) {
        if (user.isFreezing()) {
            user.setTicksFrozen(user.getTicksFrozen() - 1);
        }

        return true;
    }
}
