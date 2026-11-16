package net.meander.subtlyd.world.level.storage.loot.predicates;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jspecify.annotations.Nullable;

public record IsWetCondition() implements LootItemCondition {
    public static final MapCodec<IsWetCondition> CODEC = MapCodec.unit(new IsWetCondition());

    @Override
    public MapCodec<? extends LootItemCondition> codec() {
        return CODEC;
    }

    @Override
    public @Nullable Boolean constantValue(ConstantConditionEvaluationContext context) {
        return null;
    }

    @Override
    public boolean test(LootContext lootContext) {
        Entity target = lootContext.getOptional(LootContextParams.THIS_ENTITY);

        if (target != null) {
            return target.isInWaterOrRain();
        }

        return false;
    }

    @Override
    public void validate(ValidationContext context) {

    }
}
