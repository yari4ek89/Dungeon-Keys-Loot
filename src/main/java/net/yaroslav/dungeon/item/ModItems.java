package net.yaroslav.dungeon.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yaroslav.dungeon.DungeonMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(
            DungeonMod.MODID
    );

    public static final DeferredItem<Item> GOLD_KEY = ITEMS.register(
            "gold_key", () -> new KeyItem(new Item.Properties(), "item.dungeon.gold_key.tooltip")
    );

    public static final DeferredItem<Item> IRON_KEY = ITEMS.register(
            "iron_key", () -> new KeyItem(new Item.Properties(), "item.dungeon.iron_key.tooltip")
    );

    public static final DeferredItem<Item> COPPER_KEY = ITEMS.register(
            "copper_key", () -> new KeyItem(new Item.Properties(), "item.dungeon.copper_key.tooltip")
    );
}
