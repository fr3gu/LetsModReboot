package com.fr3gu.letsmodreboot.item;

import com.fr3gu.letsmodreboot.block.BlockInfo;
import com.fr3gu.letsmodreboot.block.BlockMachine;
import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ItemCard extends ItemLMRB {
    @SideOnly(Side.CLIENT)
    private IIcon[] _icons;

    public ItemCard() {
        super();
        this.setUnlocalizedName(ItemInfo.CARD_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.maxStackSize = 64;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);
        return super.getUnlocalizedName() + ItemInfo.CARD_NAMES[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        int numberOfCards = ItemInfo.CARD_NAMES.length;

        _icons = new IIcon[numberOfCards];

        for (int i = 0; i < numberOfCards; i++) {
            _icons[i] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + BlockInfo.MACHINE_SIDES[i])));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public IIcon getIconFromDamage(int meta) {
        int j = MathHelper.clamp_int(meta, 0, 5);
        return _icons[j];
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for(int meta = 0; meta < ItemInfo.CARD_NAMES.length; meta++) {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {

        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);

        switch (meta) {
            case 0:
                return EnumChatFormatting.BLUE + ItemInfo.CARD_NAME;
            case 1:
                return EnumChatFormatting.YELLOW + ItemInfo.CARD_NAME;
            case 2:
                return EnumChatFormatting.GREEN + ItemInfo.CARD_NAME;
            default:
                return EnumChatFormatting.WHITE + ItemInfo.CARD_NAME;
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote && world.getBlock(x, y, z) instanceof BlockMachine) {
            int meta = world.getBlockMetadata(x, y, z);

            int disabled = meta % 2;

            int type = itemStack.getItemDamage() + 1;

            int newMeta = type * 2 + disabled;

            world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);

            itemStack.stackSize--;

            return true;
        }
        return false;
    }
}
