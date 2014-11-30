package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.item.ItemDroid;
import com.fr3gu.letsmodreboot.item.ItemInfo;
import com.fr3gu.letsmodreboot.item.ItemLMRB;
import com.fr3gu.letsmodreboot.item.ItemMapleLeaf;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Fredrik on 2014-07-10.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static final ItemLMRB mapleLeaf = new ItemMapleLeaf();
    public static final ItemLMRB droid = new ItemDroid();

    public static void init() {
        GameRegistry.registerItem(mapleLeaf, ItemInfo.MAPLELEAF_NAME);
        GameRegistry.registerItem(droid, ItemInfo.DROID_NAME);
    }
}
