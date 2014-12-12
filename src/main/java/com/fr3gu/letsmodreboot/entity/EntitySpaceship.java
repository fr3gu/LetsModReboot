package com.fr3gu.letsmodreboot.entity;

import com.fr3gu.letsmodreboot.network.PacketHandler;
import com.fr3gu.letsmodreboot.network.message.MessageTileEntityBomb;
import com.fr3gu.letsmodreboot.network.message.MessageTileEntityLMRB;
import com.fr3gu.letsmodreboot.utility.LogHelper;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySpaceship extends Entity implements IEntityAdditionalSpawnData {

    private boolean _isCharged;
    private double _startY;
    private boolean _lastPressedState;

    public EntitySpaceship(World world) {
        super(world);
        init();
    }

    public EntitySpaceship(World world, double x, double y, double z) {
        this(world);

        posX = x + 0.5;
        _startY = posY = y + 1.5;
        posZ = z + 0.5;
    }

    private void init() {
        setSize(1.5F, 0.6F);

    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return boundingBox;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {
        return entity != riddenByEntity ? entity.boundingBox : null;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !isDead;
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        if(!worldObj.isRemote && riddenByEntity == null) {
            player.mountEntity(this);
        }
        return true;
    }

    @Override
    public double getMountedYOffset() {
        return -0.15;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(!worldObj.isRemote) {
            if(riddenByEntity != null) {
                motionY = 0.2;
            }
            else if (worldObj.isAirBlock((int)posX, (int)posY - 1, (int)posZ)) {
                motionY = -0.1;
            }
            else {
                motionY = 0;
            }
        }
        else {
            sendInformation();
        }

        setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    private void sendInformation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        boolean state = minecraft.gameSettings.keyBindJump.isPressed();

        if(state && !_lastPressedState && _isCharged && riddenByEntity == minecraft.thePlayer) {
            PacketHandler.INSTANCE.sendToServer(new MessageTileEntityBomb(this));
        }
        _lastPressedState = state;
    }

    @Override
    protected void entityInit() {
        // NOOP
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        _isCharged = compound.getBoolean("IsCharged");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setBoolean("IsCharged", _isCharged);
    }

    public boolean getIsCharged() {
        return _isCharged;
    }

    public void setIsCharged(boolean isCharged) {
        _isCharged = isCharged;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(_isCharged);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        _isCharged = additionalData.readBoolean();
    }

    public void doDrop() {
        LogHelper.info("BOMB DROPPED!");

    }
}
