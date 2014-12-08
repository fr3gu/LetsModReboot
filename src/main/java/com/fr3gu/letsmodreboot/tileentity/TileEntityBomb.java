package com.fr3gu.letsmodreboot.tileentity;

import com.fr3gu.letsmodreboot.init.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBomb extends TileEntity {

    private int _timer;

    public TileEntityBomb() {
        _timer = 100;
    }

    @Override
    public void updateEntity() {
        if(_timer == 0 && !worldObj.isRemote) {
            spreadBombs(xCoord + 1, yCoord, zCoord);
            spreadBombs(xCoord - 1, yCoord, zCoord);
            spreadBombs(xCoord, yCoord, zCoord + 1);
            spreadBombs(xCoord, yCoord, zCoord - 1);
        }

        _timer--;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

    }

    private void spreadBombs(int x, int y, int z) {
        if(worldObj.isAirBlock(x, y , z)) {
            worldObj.setBlock(x, y, z, ModBlocks.bomb);
        }
    }
}
