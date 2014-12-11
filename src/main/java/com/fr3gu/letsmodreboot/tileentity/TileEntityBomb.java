package com.fr3gu.letsmodreboot.tileentity;

import com.fr3gu.letsmodreboot.init.ModBlocks;
import com.fr3gu.letsmodreboot.utility.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBomb extends TileEntity {

    private static final int SPREAD_TIME = 5;
    private static final int SPREAD_LEVELS = 30;

    private int _timer;
    private int _level;

    public TileEntityBomb() {
        _timer = SPREAD_TIME;
        _level = 0;
    }

    public boolean isIdle() {
        return _timer < 0;
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote) {
            if (_timer == 0 && _level < SPREAD_LEVELS) {
                spreadBombs(xCoord + 1, yCoord, zCoord);
                spreadBombs(xCoord - 1, yCoord, zCoord);
                spreadBombs(xCoord, yCoord, zCoord + 1);
                spreadBombs(xCoord, yCoord, zCoord - 1);
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
            }
            else if(_timer == SPREAD_TIME * (_level - SPREAD_LEVELS)) {
                worldObj.createExplosion(null, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 4, true);
            }
            _timer--;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setShort("Timer", (short)_timer);
        nbtTagCompound.setByte("Level", (byte)_level);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        _timer = nbtTagCompound.getShort("Timer");
        _level = nbtTagCompound.getByte("Level");
    }

    private void spreadBombs(int x, int y, int z) {
        if(worldObj.isAirBlock(x, y , z)) {
            worldObj.setBlock(x, y, z, ModBlocks.bomb);

            TileEntity tile = worldObj.getTileEntity(x, y, z);

            if(tile != null && tile instanceof TileEntityBomb) {
                TileEntityBomb bomb = (TileEntityBomb)tile;
                bomb._level = _level + 1;
            }
            else
                LogHelper.error("TileEntity tile is not a TileEntityBomb!");

        }
    }
}
