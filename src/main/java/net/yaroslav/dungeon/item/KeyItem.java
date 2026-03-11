package net.yaroslav.dungeon.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class KeyItem extends Item {
    private String translatable = "block.dungeon.lock_cache.tooltip";

    public KeyItem(Properties properties, String str) {
        super(properties);
        this.translatable = str;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable(this.translatable).withStyle(ChatFormatting.GRAY));
    }
}
