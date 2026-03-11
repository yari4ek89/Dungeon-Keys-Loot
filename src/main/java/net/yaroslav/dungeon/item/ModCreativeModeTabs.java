package net.yaroslav.dungeon.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yaroslav.dungeon.DungeonMod;
import net.yaroslav.dungeon.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DungeonMod.MODID);

    public static final Supplier<CreativeModeTab> DUNGEONS_TAB = CREATIVE_MODE_TAB.register("dungeon_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("Dungeon Keys & Loot"))
                    .icon(() -> new ItemStack(ModItems.GOLD_KEY.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.GOLD_KEY);
                        output.accept(ModItems.IRON_KEY);
                        output.accept(ModItems.COPPER_KEY);
                        output.accept(ModBlocks.LOCK_CACHE_BLOCK);
                            }
                    )
                    .build());
}
