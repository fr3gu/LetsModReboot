package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBomb extends BlockContainerLMRB {

    public BlockBomb() {
        super(Material.iron);
        this.setBlockName(BlockInfo.BOMB_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.setHardness(2F);
        this.setStepSound(Block.soundTypeMetal);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBomb();
    }
}
