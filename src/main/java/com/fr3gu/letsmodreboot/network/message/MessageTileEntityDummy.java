package com.fr3gu.letsmodreboot.network.message;

import com.fr3gu.letsmodreboot.tileentity.TileEntityDummyArray;
import com.fr3gu.letsmodreboot.tileentity.TileEntityLMRB;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class MessageTileEntityDummy implements IMessage, IMessageHandler<MessageTileEntityDummy, IMessage> {

    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName, owner;
    public int trueXCoord, trueYCoord, trueZCoord;

    public MessageTileEntityDummy() {

    }

    public MessageTileEntityDummy(TileEntityDummyArray tileEntityDummyArray) {
        this.x = tileEntityDummyArray.xCoord;
        this.y = tileEntityDummyArray.yCoord;
        this.z = tileEntityDummyArray.zCoord;
        this.orientation = (byte)tileEntityDummyArray.getOrientation().ordinal();
        this.state = (byte)tileEntityDummyArray.getState();
        this.customName = tileEntityDummyArray.getCustomName();
        this.owner = tileEntityDummyArray.getOwner();
        this.trueXCoord = tileEntityDummyArray.getTrueXCoord();
        this.trueYCoord = tileEntityDummyArray.getTrueZCoord();
        this.trueZCoord = tileEntityDummyArray.getTrueYCoord();
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.orientation = buffer.readByte();
        this.state = buffer.readByte();
        int customNameLength = buffer.readInt();
        this.customName = new String(buffer.readBytes(customNameLength).array());
        int ownerLength = buffer.readInt();
        this.owner = new String(buffer.readBytes(ownerLength).array());
        this.trueXCoord = buffer.readInt();
        this.trueYCoord = buffer.readInt();
        this.trueZCoord = buffer.readInt();
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     *
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeByte(orientation);
        buffer.writeByte(state);
        buffer.writeInt(customName.length());
        buffer.writeBytes(customName.getBytes());
        buffer.writeInt(owner.length());
        buffer.writeBytes(owner.getBytes());
        buffer.writeInt(trueXCoord);
        buffer.writeInt(trueYCoord);
        buffer.writeInt(trueZCoord);
    }

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param message The message
     * @param ctx
     * @return an optional return message
     */
    @Override
    public IMessage onMessage(MessageTileEntityDummy message, MessageContext ctx) {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if(tileEntity instanceof TileEntityLMRB) {
            ((TileEntityLMRB) tileEntity).setOrientation(message.orientation);
            ((TileEntityLMRB) tileEntity).setState(message.state);
            ((TileEntityLMRB) tileEntity).setCustomName(message.customName);
            ((TileEntityLMRB) tileEntity).setOwner(message.owner);

            if(tileEntity instanceof TileEntityDummyArray) {
                ((TileEntityDummyArray) tileEntity).setTrueCoords(message.trueXCoord, message.trueYCoord, message.trueZCoord);
            }
        }

        return null;
    }
}
