package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.reference.Strings;
import com.fr3gu.letsmodreboot.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


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
        _sideIcons = new IIcon[Strings.MACHINE_SIDES.length];
        for(int i = 0; i < Strings.MACHINE_SIDES.length; i++) {
            _sideIcons[i] = register.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_side" + Strings.MACHINE_SIDES[i])));
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
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if(world.getBlockMetadata(x, y, z) % 2 == 0) {
            setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
        }
        else {
            
        }
    }

    private boolean isDisabled(int meta) {
        return meta % 2 == 1;
    }

    private void spawnAnvil(World world, int x, int y, int z) {
        if(world.isAirBlock(x, y, z)) {
            world.setBlock(x, y, z, Block.getBlockFromName("Anvil"));
        }
    }
}
