package com.fr3gu.letsmodreboot.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created by fr3gu on 2017-03-23.
 */
public enum EnumAnvilDropShape implements IStringSerializable {

    DEFAULT("normal"),
    CROSS("cross"),
    SQUARE("square");

    private final String VARIANT;

    EnumAnvilDropShape(String name)
    {
        this.VARIANT = name;
    }

    public String toString()
    {
        return this.VARIANT;
    }

    public String getName()
    {
        return this.VARIANT;
    }
}
