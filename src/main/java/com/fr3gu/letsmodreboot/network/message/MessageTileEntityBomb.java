package com.fr3gu.letsmodreboot.network.message;

import com.fr3gu.letsmodreboot.entity.EntitySpaceship;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class MessageTileEntityBomb implements IMessage, IMessageHandler<MessageTileEntityBomb, IMessage> {

    public int shipEntityId;

    public MessageTileEntityBomb() {

    }

    public MessageTileEntityBomb(EntitySpaceship ship) {
        shipEntityId = ship.getEntityId();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        shipEntityId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(shipEntityId);
    }

    @Override
    public IMessage onMessage(MessageTileEntityBomb message, MessageContext ctx) {
        MessageTileEntityBomb bombMessage = message;
        int entityId = bombMessage.shipEntityId;
        EntityPlayerMP playerEntity = ctx.getServerHandler().playerEntity;
        World worldObj = playerEntity.worldObj;
        Entity entity = worldObj.getEntityByID(entityId);

        if(entity != null && entity instanceof EntitySpaceship && entity.riddenByEntity == playerEntity) {
            ((EntitySpaceship)entity).doDrop();
        }

        return null;
    }
}
