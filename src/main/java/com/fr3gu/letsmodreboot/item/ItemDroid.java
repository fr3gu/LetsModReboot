package com.fr3gu.letsmodreboot.item;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.entity.EntityDroid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Fredrik on 2014-11-30.
 */
public class ItemDroid extends ItemLMRB {

    public ItemDroid() {
        super();
        this.setUnlocalizedName(ItemInfo.DROID_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            world.spawnEntityInWorld(new EntityDroid(world, x + 0.5, y + 1.5, z + 0.5));
            itemStack.stackSize--;

            return true;
        }
        else {
            return false;
        }

    };
}
