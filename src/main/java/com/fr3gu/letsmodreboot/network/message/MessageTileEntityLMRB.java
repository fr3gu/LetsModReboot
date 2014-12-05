package com.fr3gu.letsmodreboot.network.message;

import com.fr3gu.letsmodreboot.tileentity.TileEntityLMRB;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityLMRB implements IMessage, IMessageHandler<MessageTileEntityLMRB, IMessage> {

    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntityLMRB() {

    }

    public MessageTileEntityLMRB(TileEntityLMRB tileEntityLMRB) {
        this.x = tileEntityLMRB.xCoord;
        this.y = tileEntityLMRB.yCoord;
        this.z = tileEntityLMRB.zCoord;
        this.orientation = (byte) tileEntityLMRB.getOrientation().ordinal();
        this.state = (byte) tileEntityLMRB.getState();
        this.customName = tileEntityLMRB.getCustomName();
        this.owner = tileEntityLMRB.getOwner();
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        int ownerLength = buf.readInt();
        this.owner = new String(buf.readBytes(ownerLength).array());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeInt(owner.length());
        buf.writeBytes(owner.getBytes());
    }

    @Override
    public IMessage onMessage(MessageTileEntityLMRB message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityLMRB)
        {
            ((TileEntityLMRB) tileEntity).setOrientation(message.orientation);
            ((TileEntityLMRB) tileEntity).setState(message.state);
            ((TileEntityLMRB) tileEntity).setCustomName(message.customName);
            ((TileEntityLMRB) tileEntity).setOwner(message.owner);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityLMRB - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x, y, z, orientation, state, customName, owner);
    }
}
