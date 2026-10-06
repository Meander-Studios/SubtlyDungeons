package net.meander.subtlyd.data.loot.packs;

import net.meander.subtlyd.util.UtilSD;
import net.meander.subtlyd.world.item.ItemsSD;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @see net.minecraft.data.loot.packs.VanillaEntityLoot
 */
public class EntityLootSD {
    private static final EntityPredicate onFirePredicate = EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true)).build();

    private static ResourceKey<LootTable> getLootTable(EntityType<?> entityType) {
        return entityType.getDefaultLootTable().orElseThrow();
    }

    public static void register(WritableRegistry<LootTable> registry, HolderLookup.Provider provider) {
        try {
            modify(registry, getLootTable(EntityTypes.SQUID), EntityLootSD::calamari);
            modify(registry, getLootTable(EntityTypes.GLOW_SQUID), EntityLootSD::calamari);
            modify(registry, getLootTable(EntityTypes.WITCH), table -> covenElixir(table, provider));
            modify(registry, getLootTable(EntityTypes.COW), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.MOOSHROOM), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.HORSE), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.LLAMA), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.MULE), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.DONKEY), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.TRADER_LLAMA), EntityLootSD::bonusLeather);
            modify(registry, getLootTable(EntityTypes.STRAY), table -> freezingArrow(table, provider));
        } catch (Exception e) {
            UtilSD.LOGGER.error("Failed to register entity loot table {}", e.getMessage());
        }
    }

    private static void modify(WritableRegistry<LootTable> registry, ResourceKey<LootTable> key, Consumer<LootTable> modifier) {
        registry.get(key).ifPresent(lootTableReference -> modifier.accept(lootTableReference.value()));
    }

    private static void calamari(LootTable table) {
        List<LootPool> newPools = new ArrayList<>(table.pools);

        newPools.add(LootPool.lootPool()
                .add(LootItem.lootTableItem(ItemsSD.CALAMARI)
                        .apply(SmeltItemFunction.smelted()
                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, onFirePredicate))))
                .setRolls(ContextIntProviders.exactly(1))
                .build());

        table.pools = newPools;
    }

    private static void bonusLeather(LootTable table) {
        List<LootPool> newPools = new ArrayList<>(table.pools);

        newPools.add(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.LEATHER)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2))))
                .build());

        table.pools = newPools;
    }

    private static void covenElixir(LootTable table, HolderLookup.Provider provider) {
        HolderLookup.RegistryLookup<Biome> biomeLookup = provider.lookupOrThrow(Registries.BIOME);
        HolderLookup.RegistryLookup<Structure> structureLookup = provider.lookupOrThrow(Registries.STRUCTURE);
        List<LootPool> newPools = new ArrayList<>(table.pools);

        newPools.add(LootPool.lootPool()
                .add(LootItem.lootTableItem(ItemsSD.COVEN_ELIXIR)
                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setStructures(HolderSet.direct(
                                structureLookup.getOrThrow(BuiltinStructures.SWAMP_HUT)))))
                        .setWeight(18))
                .add(LootItem.lootTableItem(ItemsSD.COVEN_ELIXIR)
                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(
                                biomeLookup.getOrThrow(BiomeTags.HAS_SWAMP_HUT))))
                        .setWeight(1))
                .add(EmptyLootItem.emptyItem()
                        .setWeight(19))
                .setRolls(ContextIntProviders.exactly(1))
                .build());

        table.pools = newPools;
    }

    private static void freezingArrow(LootTable table, HolderLookup.Provider provider) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = provider.lookupOrThrow(Registries.ENCHANTMENT);
        List<LootPool> newPools = new ArrayList<>(table.pools);

        newPools.removeIf(pool -> isItemPresent(pool, Items.TIPPED_ARROW));

        newPools.add(LootPool.lootPool()
                .setRolls(ContextIntProviders.exactly(1))
                .add(LootItem.lootTableItem(Items.TIPPED_ARROW)
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments, ContextFloatProviders.between(0.0F, 1.0F)).setLimit(1))
                )
                .apply(SetPotionFunction.setPotion(Potions.FREEZING))
                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                .build());

        table.pools = newPools;
    }

    private static boolean isItemPresent(LootPool pool, Item item) {
        try {
            Field entriesField = LootPool.class.getDeclaredField("entries");

            entriesField.setAccessible(true);

            List<?> entries = (List<?>) entriesField.get(pool);

            for (Object entry : entries) {
                if (entry instanceof LootItem) {
                    Field itemField = LootItem.class.getDeclaredField("item");

                    itemField.setAccessible(true);

                    Holder<?> itemHolder = (Holder<?>) itemField.get(entry);

                    if (itemHolder != null && itemHolder.value() == item) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            UtilSD.LOGGER.error("Failed to inspect loot pool entries: {}", e.getMessage());
        }

        return false;
    }
}