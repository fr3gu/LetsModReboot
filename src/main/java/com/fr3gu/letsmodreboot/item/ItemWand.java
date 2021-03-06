package com.fr3gu.letsmodreboot.item;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.entity.EntitySpaceship;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class ItemWand extends ItemLMRB {

    public ItemWand() {
        super();
        this.setUnlocalizedName(ItemInfo.WAND_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    private IIcon _chargedIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        _chargedIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + ItemInfo.WAND_CHARGED_TEXTURE_SUFFIX)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return isCharged(stack.getItemDamage()) ? _chargedIcon : itemIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target) {

        if(!target.worldObj.isRemote) {
            target.motionY = 2;
            if(isCharged(itemStack.getItemDamage())) {
                target.motionX = (target.posX - player.posX) * 2;
                target.motionZ = (target.posZ - player.posZ) * 2;

                itemStack.setItemDamage(0);
            }
            else {
                itemStack.setItemDamage(itemStack.getItemDamage() + 1);
            }

            target.worldObj.playSoundAtEntity(player, (Reference.MOD_ID + ":wand"), 1F, 0F);

        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean useExtraInformation) {
        info.add("This fun thing has been used " + itemStack.getItemDamage() + " " + (itemStack.getItemDamage() == 1 ? "time" : "times"));

        if(isCharged(itemStack.getItemDamage())) {
            info.add("This item is charged!");
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote && player.isSneaking()) {
            EntitySpaceship ship = new EntitySpaceship(world, x, y, z);

            if(isCharged(itemStack.getItemDamage())) {
                ship.setIsCharged(true);
                itemStack.setItemDamage(0);
            }
            else
                itemStack.setItemDamage(itemStack.getItemDamage() + 1);

            world.spawnEntityInWorld(ship);

            return true;
        }
        return false;

    }
    private boolean isCharged(int dmg) {
        return dmg >= 10;
    }
}
