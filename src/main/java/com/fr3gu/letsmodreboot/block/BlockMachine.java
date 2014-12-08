package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.reference.Strings;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import com.fr3gu.letsmodreboot.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;


public class BlockMachine extends BlockLMRB {

    public BlockMachine() {
        super();
        this.setBlockName(BlockInfo.MACHINE_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.setHardness(2F);
        this.setStepSound(Block.soundTypeMetal);
    }

    @SideOnly(Side.CLIENT)
    private IIcon _topIcon;

    @SideOnly(Side.CLIENT)
    private IIcon _bottomIcon;

    @SideOnly(Side.CLIENT)
    private IIcon[] _sideIcons;

    @SideOnly(Side.CLIENT)
    private IIcon _disabledIcon;


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        _topIcon = register.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_top")));
        _bottomIcon = register.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_bottom")));
        _sideIcons = new IIcon[BlockInfo.MACHINE_SIDES.length];
        for(int i = 0; i < BlockInfo.MACHINE_SIDES.length; i++) {
            _sideIcons[i] = register.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_side" + BlockInfo.MACHINE_SIDES[i])));
        }
        _disabledIcon = register.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_disabled")));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if(side == 0)
            return _bottomIcon;

        if(side == 1)
            return isDisabled(meta) ? _disabledIcon : _topIcon;

        int type = meta / 2;
        return _sideIcons[type];
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if(!world.isRemote && !isDisabled(world.getBlockMetadata(x, y, z))) {
            spawnAnvil(world, x, y + 20, z);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int meta = world.getBlockMetadata(x, y, z);
        if(!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z) && !isDisabled(meta)) {
            switch (meta / 2) {
                case 1:
                    for(int i = 0; i < 5; i++) {
                        spawnAnvil(world, x, y + 20 + i, z);
                    }
                    break;

                case 2:
                    for(int i = -1; i <= 1; i++) {
                        spawnAnvil(world, x + i, y + 20, z - 2);
                        spawnAnvil(world, x + i, y + 20, z + 2);
                        spawnAnvil(world, x - 2, y + 20, z + i);
                        spawnAnvil(world, x + 2, y + 20, z + i);
                    }
                    break;

                case 3:
                    for(int i = 1; i <= 3; i++) {
                        spawnAnvil(world, x + i, y + 20, z);
                        spawnAnvil(world, x - i, y + 20, z);
                        spawnAnvil(world, x, y + 20, z + i);
                        spawnAnvil(world, x, y + 20, z - i);
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);

            int type = meta / 2;

            int disabled = meta % 2 == 0 ? 1 : 0;

            int newMeta = type * 2 + disabled;

            world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
        }
        return true;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if(world.getBlockMetadata(x, y, z) % 2 == 0) {
            setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
        }
        else {
            
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < BlockInfo.MACHINE_SIDES.length; i++) {
            list.add(new ItemStack(item, 1, i * 2));
        }
    }

    private boolean isDisabled(int meta) {
        return meta % 2 == 1;
    }

    private void spawnAnvil(World world, int x, int y, int z) {
        if(world.isAirBlock(x, y, z)) {
            world.setBlock(x, y, z, Blocks.anvil);
        }
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityBomb();
    }
}
