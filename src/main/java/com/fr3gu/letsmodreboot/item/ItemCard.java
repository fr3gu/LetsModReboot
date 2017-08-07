package com.fr3gu.letsmodreboot.item;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCard extends ItemLMRB {

    @SideOnly(Side.CLIENT)
    private IIcon[] _icons;

    public ItemCard() {
        super();
        this.setUnlocalizedName(ItemInfo.CARD_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.setHasSubtypes(true);
        this.maxStackSize = 64;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);
        return super.getUnlocalizedName() + ItemInfo.CARD_NAMES[meta];
    }

    /*
    @Override
     */
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        int numberOfCards = ItemInfo.CARD_NAMES.length;

        _icons = new IIcon[numberOfCards];

        for (int i = 0; i < numberOfCards; i++) {
            _icons[i] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + ItemInfo.CARD_ICONS[i])));
        }
    }
    */

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
                return EnumChatFormatting.BLUE + super.getItemStackDisplayName(itemStack);
            case 1:
                return EnumChatFormatting.YELLOW + super.getItemStackDisplayName(itemStack);
            case 2:
                return EnumChatFormatting.GREEN + super.getItemStackDisplayName(itemStack);
            default:
                return EnumChatFormatting.WHITE + super.getItemStackDisplayName(itemStack);
        }
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        IBlockState blockState = world.getBlockState(new BlockPos(hitX, hitY, hitZ));
        if(!world.isRemote && blockState.getBlock() == ModBlocks.machine) {
            //int meta = world.getBlockMetadata(hitX, hitY, hitZ);
            boolean isDisabled = blockState.withProperty();

            //int ISDISABLED = meta % 2;

            int type = itemStack.getItemDamage() + 1;

            int newMeta = type * 2 + disabled;

            world.setBlockMetadataWithNotify(hitX, hitY, hitZ, newMeta, 3);

            itemStack.stackSize--;

            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
