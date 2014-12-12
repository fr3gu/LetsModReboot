package com.fr3gu.letsmodreboot.tileentity;

import com.fr3gu.letsmodreboot.network.PacketHandler;
import com.fr3gu.letsmodreboot.network.message.MessageTileEntityDummy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public class TileEntityDummyArray extends TileEntityLMRB {
    private int _trueXCoord, _trueYCoord, _trueZCoord;
    private int _ticksSinceSync;

    public TileEntityDummyArray() {
        super();
    }

    public int getTrueXCoord() {
        return _trueXCoord;
    }

    public int getTrueYCoord() {
        return _trueYCoord;
    }

    public int getTrueZCoord() {
        return _trueZCoord;
    }

    public void setTrueCoords(int trueXCoord, int trueYCoord, int trueZCoord) {
        _trueXCoord = trueXCoord;
        _trueYCoord = trueYCoord;
        _trueZCoord = trueZCoord;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(++_ticksSinceSync % 10 == 0) {
            if(!worldObj.isRemote && !(worldObj.getTileEntity(_trueXCoord, _trueYCoord, _trueZCoord) instanceof  TileEntityLMRB)) {
                this.invalidate();
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityDummy(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        _trueXCoord = nbtTagCompound.getInteger("trueXCoord");
        _trueYCoord = nbtTagCompound.getInteger("trueYCoord");
        _trueZCoord = nbtTagCompound.getInteger("trueZCoord");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("trueXCoord", _trueXCoord);
        nbtTagCompound.setInteger("trueYCoord", _trueYCoord);
        nbtTagCompound.setInteger("trueZCoord", _trueZCoord);
    }
}
