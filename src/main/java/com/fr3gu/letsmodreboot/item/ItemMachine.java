package com.fr3gu.letsmodreboot.item;

import com.fr3gu.letsmodreboot.block.BlockInfo;
import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemMachine extends ItemBlockLMRB {

    public ItemMachine(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
    }

    @Override
    public int getMetadata(int dmg) {
        return dmg;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = itemStack.getItemDamage() / 2;
        return super.getUnlocalizedName() + BlockInfo.MACHINE_SIDES[meta];
    }
}
