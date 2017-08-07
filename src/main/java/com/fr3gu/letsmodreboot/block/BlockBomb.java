package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockBomb extends BlockContainerBase {

    public BlockBomb() {
        super(BlockInfo.BOMB_UNLOCALIZED_NAME, Material.IRON);
        this.setHardness(2F);
        this.setSoundType(SoundType.METAL);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBomb();
    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return null; }
}
