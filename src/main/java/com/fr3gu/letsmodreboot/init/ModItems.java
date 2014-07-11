package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.item.ItemLMRB;
import com.fr3gu.letsmodreboot.item.ItemMapleLeaf;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Fredrik on 2014-07-10.
 */
public class ModItems {

    public static final ItemLMRB mapleLeaf = new ItemMapleLeaf();

    public static void init() {
        GameRegistry.registerItem(mapleLeaf, "MapleLeaf");
    }
}
