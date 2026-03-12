package net.yaroslav.dungeon.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yaroslav.dungeon.DungeonMod;
import net.yaroslav.dungeon.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(DungeonMod.MODID);

    public static final DeferredBlock<Block> COPPER_LOCK_CACHE_BLOCK = registerBlock("copper_lock_cache",
            () -> new LockedCacheBlock(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 7), "copper")
    );

    public static final DeferredBlock<Block> IRON_LOCK_CACHE_BLOCK = registerBlock("iron_lock_cache",
            () -> new LockedCacheBlock(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 7), "iron")
    );

    public static final DeferredBlock<Block> GOLD_LOCK_CACHE_BLOCK = registerBlock("gold_lock_cache",
            () -> new LockedCacheBlock(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.STONE)
                    .lightLevel(state -> 7), "gold")
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
