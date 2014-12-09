package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.block.BlockInfo;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityBomb.class, BlockInfo.BOMB_TE_KEY);
    }
}
