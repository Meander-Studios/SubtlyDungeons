package net.meander.subtlyd.mixin.common.world.level.storage.loot.functions;

import com.mojang.serialization.MapCodec;
import net.meander.subtlyd.world.level.storage.loot.functions.LootItemFunctionsSD;
import net.minecraft.core.WritableRegistry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LootItemFunctions.class)
public class LootItemFunctionsMixin {
    @Inject(method = "bootstrap", at = @At("RETURN"))
    private static void registerFunctions(WritableRegistry<MapCodec<? extends LootItemFunction>> registry, CallbackInfo ci) {
        LootItemFunctionsSD.bootstrap(registry);
    }
}