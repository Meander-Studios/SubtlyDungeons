package net.meander.subtlyd.datafix;

import com.mojang.datafixers.schemas.Schema;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

public class V2SD extends NamespacedSchema {
    public V2SD(int versionKey, Schema parent) {
        super(versionKey, parent);
    }
}
