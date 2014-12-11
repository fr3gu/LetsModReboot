package com.fr3gu.letsmodreboot.entity;

import com.fr3gu.letsmodreboot.utility.LogHelper;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySpaceship extends Entity implements IEntityAdditionalSpawnData {

    private boolean _isCharged;
    private double _startY;

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

        setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    @Override
    protected void entityInit() {
        LogHelper.info("Init! Charged: " + _isCharged);
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
        _isCharged = additionalData.getBoolean(0);
    }
}
