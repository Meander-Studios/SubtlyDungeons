
package net.meander.subtlyd.mixin.common.server.level;

import net.meander.subtlyd.datafix.DataFixersSD;
import net.meander.subtlyd.nbt.NbtUtilsSD;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.util.datafix.fixes.References;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkMap.class)
public class ChunkMapMixin {
    @Inject(method = "upgradeChunkTag", at = @At("HEAD"))
    private void updateChunkData(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        tag.merge(NbtUtilsSD.update(References.CHUNK, tag, DataFixersSD.SNOW_BRICK_RENAMES));
    }
}
