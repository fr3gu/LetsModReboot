package com.fr3gu.letsmodreboot.contract;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable {
    void readFromNBT(NBTTagCompound nbtTagCompound);
    void writeToNBT(NBTTagCompound nbtTagCompound);
}
