package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.enums.EnumAnvilDropShape;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;


public class BlockMachine extends BlockLMRB {
    public static final IProperty<Boolean> ISDISABLED = PropertyBool.create("disabled");
    public static final PropertyEnum<EnumAnvilDropShape> DROPSHAPE = PropertyEnum.create("dropshape", EnumAnvilDropShape.class);

    public static final AxisAlignedBB AABB_DISABLED = new AxisAlignedBB(0F, 0F, 0F, 1F, 1F, 1F);
    public static final AxisAlignedBB AABB_ENABLED = new AxisAlignedBB(0F, 0F, 0F, 0F, 0F, 0F);

    public BlockMachine() {
        super(BlockInfo.MACHINE_UNLOCALIZED_NAME);
        this.setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        this.setHardness(2F);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(blockState.getBaseState().withProperty(ISDISABLED, false).withProperty(DROPSHAPE, EnumAnvilDropShape.DEFAULT));
    }

    /*
    @SideOnly(Side.CLIENT)
    private IIcon _topIcon;

    @SideOnly(Side.CLIENT)
    private IIcon _bottomIcon;

    @SideOnly(Side.CLIENT)
    private IIcon[] _sideIcons;

    @SideOnly(Side.CLIENT)
    private IIcon _disabledIcon;
    */

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ISDISABLED);
    }

    /*
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
    */

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entityIn) {
        IBlockState blockState = world.getBlockState(pos);
        if(!world.isRemote && !isDisabled(blockState)) {
            spawnAnvil(world, pos.add(0, 20, 0));
        }
    }

    @Override
    public void neighborChanged(IBlockState blockState, World world, BlockPos pos, Block neighbor) {

        BlockStateContainer neighborBlockState = neighbor.getBlockState();
        EnumAnvilDropShape dropType = blockState.getValue(DROPSHAPE);
        boolean blockIndirectlyGettingPowered = world.isBlockIndirectlyGettingPowered(pos) > 0;
        if(!world.isRemote && blockIndirectlyGettingPowered && !isDisabled(blockState)) {
            switch (dropType) {
                case DEFAULT:
                    for(int i = 0; i < 5; i++) {
                        spawnAnvil(world, pos.add(0, 20 + i, 0));
                    }
                    break;

                case SQUARE:
                    for(int i = -1; i <= 1; i++) {
                        spawnAnvil(world, pos.add(i, 20, -2));
                        spawnAnvil(world, pos.add(i, 20, 2));
                        spawnAnvil(world, pos.add(-2, 20, i));
                        spawnAnvil(world, pos.add(2, 20, i));
                    }
                    break;

                case CROSS:
                    for(int i = 1; i <= 3; i++) {
                        spawnAnvil(world, pos.add(i, 20, 0));
                        spawnAnvil(world, pos.add(- i, 20, 0));
                        spawnAnvil(world, pos.add(0, 20, i));
                        spawnAnvil(world, pos.add(0, 20, -i));
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            boolean isDisabled = state.getValue(ISDISABLED);
            world.setBlockState(pos, state.withProperty(ISDISABLED, !isDisabled));
        }
        return true;
    }

    /*@Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos blockPos) {
        if(world.getBlockMetadata(x, y, z) % 2 == 0) {
            this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
        }
        else {
            
        }
    }*/
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return state.getValue(ISDISABLED) ? AABB_DISABLED : AABB_ENABLED;
    }

    /*
    @Override
    public int damageDropped(int meta) {
        return meta;
    }
    */

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for(int i = 0; i < BlockInfo.MACHINE_SIDES.length; i++) {
            list.add(new ItemStack(item, 1, i * 2));
        }
    }

    private boolean isDisabled(IBlockState blockState) {
        return blockState.getValue(ISDISABLED);
    }

    private void spawnAnvil(World world, BlockPos blockPos) {
        if(world.isAirBlock(blockPos)) {
            world.setBlockState(blockPos, Blocks.ANVIL.getDefaultState());
        }
    }

    /*@Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityBomb();
    }*/

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityBomb();
    }
}
