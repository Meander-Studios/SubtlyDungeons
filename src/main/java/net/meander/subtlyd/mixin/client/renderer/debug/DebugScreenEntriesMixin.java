package net.meander.subtlyd.mixin.client.renderer.debug;

import net.meander.subtlyd.client.gui.components.debug.DebugScreenEntriesSD;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.client.gui.components.debug.DebugScreenEntryStatus;
import net.minecraft.client.gui.components.debug.DebugScreenProfile;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(DebugScreenEntries.class)
public abstract class DebugScreenEntriesMixin {
    @Shadow @Final @Mutable public static Map<DebugScreenProfile, Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus>> PROFILES;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void registerModDebugEntries(Registry<DebugScreenEntry> registry, CallbackInfoReturnable<DebugScreenEntry> cir) {
        DebugScreenEntriesSD.bootstrap(registry);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyProfiles(CallbackInfo ci) {
        Map<DebugScreenProfile, Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus>> mutableProfiles = new HashMap<>(PROFILES);
        Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus> defaultEntries = new HashMap<>(mutableProfiles.getOrDefault(DebugScreenProfile.DEFAULT, Map.of()));
        Map<ResourceKey<DebugScreenEntry>, DebugScreenEntryStatus> performanceEntries = new HashMap<>(mutableProfiles.getOrDefault(DebugScreenProfile.PERFORMANCE, Map.of()));

        defaultEntries.put(DebugScreenEntriesSD.ENTITY_CULLING_METHOD, DebugScreenEntryStatus.IN_OVERLAY);
        performanceEntries.put(DebugScreenEntriesSD.ENTITY_CULLING_METHOD, DebugScreenEntryStatus.IN_OVERLAY);

        mutableProfiles.put(DebugScreenProfile.DEFAULT, Map.copyOf(defaultEntries));
        mutableProfiles.put(DebugScreenProfile.PERFORMANCE, Map.copyOf(performanceEntries));

        PROFILES = Map.copyOf(mutableProfiles);
    }
}