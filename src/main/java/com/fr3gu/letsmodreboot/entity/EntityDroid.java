package com.fr3gu.letsmodreboot.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDroid extends Entity {

    private double startY;
    private double targetY;
    private float coreRotation;
    private float panelRotation;

    public EntityDroid(World world) {
        super(world);
    }

    public EntityDroid(World world, double x, double y, double z) {
        this(world);

        posX = x;
        startY = posY = y;
        posZ = z;
    }


    @Override
    protected void entityInit() {
        dataWatcher.addObject(20, (byte)0);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        startY = compound.getShort("Start");
        targetY = compound.getShort("Target");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setShort("Start", (short)startY);
        compound.setShort("Target", (short)targetY);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(!worldObj.isRemote) {
            if(targetY == 0 || Math.abs(posY - targetY) < 0.25) {
                targetY = startY + worldObj.rand.nextDouble() * 5;
            }

            if(posY < targetY) {
                motionY = 0.05;
            }
            else {
                motionY = -0.05;
            }

            boolean light = worldObj.getBlockLightValue((int)posX, (int)posY, (int)posZ) == 15;
            dataWatcher.updateObject(20, light ? (byte)1: (byte) 0);
        }
        else {
            coreRotation += 0.05F;

            if(dataWatcher.getWatchableObjectByte(20) != 0) {
                // sun is up; extend solar panels!
                panelRotation = Math.min((float)Math.PI / 2, panelRotation + 0.02F);
            }
            else {
                panelRotation = Math.max(0, panelRotation - 0.02F);
            }
        }

        //setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    public float getCoreRotation() {
        return coreRotation;
    }

    public float getPanelRotation() {
        return panelRotation;
    }
}
