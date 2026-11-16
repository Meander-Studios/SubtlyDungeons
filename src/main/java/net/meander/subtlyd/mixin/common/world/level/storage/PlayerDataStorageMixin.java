package net.meander.subtlyd.mixin.common.world.level.storage;

import net.meander.subtlyd.datafix.DataFixersSD;
import net.meander.subtlyd.nbt.NbtUtilsSD;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.storage.PlayerDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerDataStorage.class)
public class PlayerDataStorageMixin {
    @Inject(method = "load*", at = @At("RETURN"), cancellable = true)
    private void applyPlayerDataFixer(CallbackInfoReturnable<Optional<CompoundTag>> cir) {
        cir.getReturnValue().ifPresent(tag -> cir.setReturnValue(Optional.of(NbtUtilsSD.update(References.PLAYER, tag, DataFixersSD.SNOW_BRICK_RENAMES))));
    }
}
