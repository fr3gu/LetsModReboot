package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBomb extends BlockContainerLMRB {

    public BlockBomb() {
        super(Material.iron);
        this.setBlockName(BlockInfo.BOMB_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.setHardness(2F);
        this.setStepSound(Block.soundTypeMetal);
    }

    @SideOnly(Side.CLIENT)
    private IIcon _idleIcon;

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBomb();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        _idleIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + BlockInfo.BOMB_IDLE_TEXTURE_SUFFIX)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityBomb bomb = (TileEntityBomb)world.getTileEntity(x, y, z);
        return bomb.isIdle() ? _idleIcon : blockIcon;
    }
}
