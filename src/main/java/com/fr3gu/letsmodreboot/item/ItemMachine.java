package com.fr3gu.letsmodreboot.item;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.reference.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemMachine extends ItemBlockLMRB {

    public ItemMachine(Block block) {
        super(block);
        setHasSubtypes(true);
        setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        maxStackSize = 1;
    }

    @Override
    public int getMetadata(int dmg) {
        return dmg;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage() / 2;
        return super.getUnlocalizedName() + Strings.MACHINE_SIDES[meta];
    }
}
