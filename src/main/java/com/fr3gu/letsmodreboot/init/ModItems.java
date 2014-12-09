package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.item.*;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Fredrik on 2014-07-10.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static final ItemLMRB mapleLeaf = new ItemMapleLeaf();
    public static final ItemLMRB droid = new ItemDroid();
    public static final ItemLMRB card = new ItemCard();
    public static final ItemLMRB rocketWand = new ItemWand();

    public static void init() {
        GameRegistry.registerItem(mapleLeaf, ItemInfo.MAPLELEAF_NAME);
        GameRegistry.registerItem(droid, ItemInfo.DROID_NAME);
        GameRegistry.registerItem(card, ItemInfo.CARD_NAME);
        GameRegistry.registerItem(rocketWand, ItemInfo.WAND_NAME);
    }
}
