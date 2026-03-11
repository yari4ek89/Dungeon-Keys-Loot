package net.yaroslav.dungeon.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.yaroslav.dungeon.DungeonMod;
import net.yaroslav.dungeon.item.ModItems;

import java.util.List;

public class LockedCacheBlock extends Block {
    public LockedCacheBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide) {
            ItemStack hand = player.getItemInHand(player.getUsedItemHand());
            ResourceLocation lootTableId = ResourceLocation.fromNamespaceAndPath(DungeonMod.MODID, "locked_cache/copper");

            if(hand.is(ModItems.COPPER_KEY) || hand.is(ModItems.IRON_KEY) || hand.is(ModItems.GOLD_KEY)) {
                if(hand.is(ModItems.COPPER_KEY)) { lootTableId = ResourceLocation.fromNamespaceAndPath(DungeonMod.MODID, "locked_cache/copper"); }
                else if(hand.is(ModItems.IRON_KEY)) { lootTableId = ResourceLocation.fromNamespaceAndPath(DungeonMod.MODID, "locked_cache/iron"); }
                else if(hand.is(ModItems.GOLD_KEY)) { lootTableId = ResourceLocation.fromNamespaceAndPath(DungeonMod.MODID, "locked_cache/gold"); }

                ServerLevel serverLevel = (ServerLevel) level;
                ResourceKey<LootTable> lootKey = ResourceKey.create(Registries.LOOT_TABLE, lootTableId);

                LootTable table = serverLevel.getServer().reloadableRegistries().getLootTable(lootKey);
                System.out.println("Table: " + table);

                LootParams params = new LootParams.Builder(serverLevel)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withLuck(1.0f)
                        .create(LootContextParamSets.CHEST);

                List<ItemStack> items = table.getRandomItems(params);
                System.out.println("Items count: " + items.size());
                for(ItemStack stack : items) {
                    System.out.println("Items: " + stack);
                    ItemEntity entity = new ItemEntity(serverLevel, pos.getX(), pos.getY(), pos.getZ(), stack);
                    serverLevel.addFreshEntity(entity);
            }
                hand.shrink(1);
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                level.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 1.0f, 1.0f);
                return InteractionResult.SUCCESS;
            } else {
                player.sendSystemMessage(Component.literal("No key"));
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.dungeon.lock_cache.tooltip").withStyle(ChatFormatting.GRAY));
    }
}
