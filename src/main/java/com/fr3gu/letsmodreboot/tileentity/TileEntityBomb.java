package com.fr3gu.letsmodreboot.tileentity;

import com.fr3gu.letsmodreboot.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBomb extends TileEntity {

    private short _timer;

    public TileEntityBomb() {
        _timer = 100;
    }

    public boolean isIdle() {
        return _timer < 0;
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
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setShort("Timer", _timer);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        _timer = nbtTagCompound.getShort("Timer");
    }

    private void spreadBombs(int x, int y, int z) {
        if(worldObj.isAirBlock(x, y , z)) {
            worldObj.setBlock(x, y, z, ModBlocks.bomb);
        }
    }
}
