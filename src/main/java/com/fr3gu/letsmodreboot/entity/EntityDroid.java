package com.fr3gu.letsmodreboot.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDroid extends Entity {

    private double _startY;
    private double _targetY;
    private float _coreRotation;
    private float _panelRotation;
    private float _outerPanelRotation;
    private float _helmetPositionRotation;
    private float _colorRed;
    private float _colorGreen;
    private float _colorBlue;

    public EntityDroid(World world) {
        super(world);
        _outerPanelRotation = (float)Math.PI;
        _colorRed = world.rand.nextFloat();
        _colorGreen = world.rand.nextFloat();
        _colorBlue = world.rand.nextFloat();
    }

    public EntityDroid(World world, double x, double y, double z) {
        this(world);

        posX = x;
        _startY = posY = y;
        posZ = z;
    }


    @Override
    protected void entityInit() {
        dataWatcher.addObject(20, (byte)0);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        _startY = compound.getShort("Start");
        _targetY = compound.getShort("Target");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setShort("Start", (short) _startY);
        compound.setShort("Target", (short) _targetY);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(!worldObj.isRemote) {
            if(_targetY == 0 || Math.abs(posY - _targetY) < 0.25) {
                _targetY = _startY + worldObj.rand.nextDouble() * 5;
            }

            if(posY < _targetY) {
                motionY = 0.05;
            }
            else {
                motionY = -0.05;
            }

            boolean light = worldObj.getBlockLightValue((int)posX, (int)posY, (int)posZ) == 15;
            dataWatcher.updateObject(20, light ? (byte)1: (byte) 0);
        }
        else {
            _coreRotation += 0.05F;
            _helmetPositionRotation += 0.02F;

            if(dataWatcher.getWatchableObjectByte(20) != 0) {
                // sun is up; extend solar panels!
                _panelRotation = Math.min((float)Math.PI / 2, _panelRotation + 0.02F);
                _outerPanelRotation = Math.max(0, _outerPanelRotation - 0.04F);
            }
            else {
                _panelRotation = Math.max(0, _panelRotation - 0.02F);
                _outerPanelRotation = Math.min((float)Math.PI, _outerPanelRotation + 0.04F);
            }
        }

        setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }

    public float getCoreRotation() {
        return _coreRotation;
    }

    public float getPanelRotation() {
        return -_panelRotation;
    }

    public float getOuterPanelRotation() {
        return -_outerPanelRotation;
    }

    public float getHelmetPosition() {
        return 6 + Math.abs((float)Math.sin(_helmetPositionRotation)) * 5.5F;
    }

    public float getColorRed() {
        return _colorRed;
    }

    public float getColorGreen() {
        return _colorGreen;
    }

    public float getColorBlue() {
        return _colorBlue;
    }
}
