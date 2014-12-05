package com.fr3gu.letsmodreboot.tileentity;

import com.fr3gu.letsmodreboot.network.PacketHandler;
import com.fr3gu.letsmodreboot.network.message.MessageTileEntityLMRB;
import com.fr3gu.letsmodreboot.reference.Names;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityLMRB extends TileEntity {

    protected ForgeDirection _orientation;
    protected byte _state;
    protected String _customName;
    protected String _owner;

    public TileEntityLMRB() {
        _orientation = ForgeDirection.SOUTH;
        _state = 0;
        _customName = "";
        _owner = "";
    }

    public ForgeDirection getOrientation() {
        return _orientation;
    }

    public void setOrientation(ForgeDirection orientation) {
        _orientation = orientation;
    }

    public void setOrientation(int orientation) {
        _orientation = ForgeDirection.getOrientation(orientation);
    }

    public short getState() {
        return _state;
    }

    public void setState(byte state) {
        _state = state;
    }

    public String getCustomName() {
        return _customName;
    }

    public void setCustomName(String customName) {
        _customName = customName;
    }

    public String getOwner() {
        return _owner;
    }

    public void setOwner(String owner) {
        _owner = owner;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        if(nbtTagCompound.hasKey(Names.NBT.DIRECTION)) {
            _orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Names.NBT.DIRECTION));
        }

        if(nbtTagCompound.hasKey(Names.NBT.STATE)) {
            _state = nbtTagCompound.getByte(Names.NBT.STATE);
        }

        if(nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME)) {
            _customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }

        if(nbtTagCompound.hasKey(Names.NBT.OWNER)) {
            _owner = nbtTagCompound.getString(Names.NBT.OWNER);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.DIRECTION, (byte)_orientation.ordinal());
        nbtTagCompound.setByte(Names.NBT.STATE, _state);

        if(hasCustomName()) {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, _customName);
        }

        if(hasOwner()) {
            nbtTagCompound.setString(Names.NBT.OWNER, _owner);
        }
    }

    private boolean hasCustomName() {
        return _customName != "" && _customName.length() > 0;
    }

    private boolean hasOwner() {
        return _owner != "" && _owner.length() > 0;
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityLMRB(this));
    }
}
