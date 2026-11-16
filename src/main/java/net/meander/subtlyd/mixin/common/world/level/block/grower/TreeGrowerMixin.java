package net.meander.subtlyd.mixin.common.world.level.block.grower;

import com.llamalad7.mixinextras.sugar.Local;
import net.meander.subtlyd.data.worldgen.features.TreeFeaturesSD;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Mixin(TreeGrower.class)
public class TreeGrowerMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true, name = "megaTrees")
    private static WeightedList<ResourceKey<Feature>> addBaobabTree(
            WeightedList<ResourceKey<Feature>> megaTrees,
            @Local(name = "trees", argsOnly = true) WeightedList<ResourceKey<Feature>> trees
    ) {
        if (Objects.equals(trees, WeightedList.of(TreeFeatures.ACACIA))) {
            megaTrees = WeightedList.of(TreeFeaturesSD.BAOBAB);
        }

        return megaTrees;
    }
}