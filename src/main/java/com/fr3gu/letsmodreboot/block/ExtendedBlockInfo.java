package com.fr3gu.letsmodreboot.block;

import net.minecraft.block.Block;

public class ExtendedBlockInfo {
    private Block _block;
    private int[] _coords;

    public ExtendedBlockInfo(Block block, int posX, int posY, int posZ) {
        _block = block;
        _coords = new int[] { posX, posY, posZ};
    }

    public Block getBlock() {
        return _block;
    }

    public int[] getCoords() {
        return _coords;
    }


}
