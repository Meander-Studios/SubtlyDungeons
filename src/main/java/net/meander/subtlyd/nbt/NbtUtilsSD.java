
package net.meander.subtlyd.nbt;

import com.mojang.datafixers.DSL;
import com.mojang.serialization.Dynamic;
import net.meander.subtlyd.datafix.DataFixersSD;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.datafix.fixes.References;

import java.util.Map;

/**
 * @see net.minecraft.nbt.NbtUtils
 */
public final class NbtUtilsSD {
    public static final int DATA_VERSION = 2;
    private static final String DATA_VERSION_NAME = "DataVersionSD";

    private NbtUtilsSD() {
    }

    public static int getDataVersion(CompoundTag tag) {
        return tag.getIntOr(DATA_VERSION_NAME, 0);
    }

    public static void write(CompoundTag tag) {
        tag.putInt(DATA_VERSION_NAME, DATA_VERSION);
    }

    public static CompoundTag update(DSL.TypeReference type, CompoundTag tag, Map<String, String> renameMap) {
        int oldVersion = getDataVersion(tag);

        tag = renameIds(tag, renameMap);

        if (type.equals(References.ENTITY) && oldVersion < DATA_VERSION) {
            Dynamic<Tag> result = DataFixersSD.getFixer().update(
                    type,
                    new Dynamic<>(NbtOps.INSTANCE, tag),
                    DataFixersSD.MC_DATA_VERSION + oldVersion,
                    DataFixersSD.MC_DATA_VERSION + DATA_VERSION
            );
            CompoundTag updated = (CompoundTag) result.getValue();

            write(updated);
            return updated;
        }

        return tag;
    }

    private static CompoundTag renameIds(CompoundTag tag, Map<String, String> renameMap) {
        return (CompoundTag) renameIds((Tag) tag, renameMap);
    }

    private static Tag renameIds(Tag tag, Map<String, String> renameMap) {
        if (tag instanceof CompoundTag compound) {
            CompoundTag renamed = new CompoundTag();

            for (String key : compound.keySet()) {
                renamed.put(key, renameIds(compound.get(key), renameMap));
            }

            return renamed;
        } else if (tag instanceof ListTag list) {
            ListTag renamed = new ListTag();
            for (Tag element : list) {
                renamed.add(renameIds(element, renameMap));
            }

            return renamed;
        } else if (tag instanceof StringTag(String value)) {
            String replacement = renameMap.get(value);

            return replacement == null ? tag : StringTag.valueOf(replacement);
        }

        return tag;
    }
}
