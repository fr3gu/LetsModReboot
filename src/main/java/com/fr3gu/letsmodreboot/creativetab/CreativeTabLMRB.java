package com.fr3gu.letsmodreboot.creativetab;

import com.fr3gu.letsmodreboot.init.ModItems;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Fredrik on 2014-07-15.
 */
public class CreativeTabLMRB {

    public static final CreativeTabs LMRB_TAB = new CreativeTabs(Reference.MOD_ID) {

        @Override
        public Item getTabIconItem() {
            return ModItems.mapleLeaf;
        }

        @Override
        public String getTranslatedTabLabel() {
            return "Let's Mod: Reboot";
        }
    };
}
