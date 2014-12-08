package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.block.*;
import com.fr3gu.letsmodreboot.reference.Reference;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Fredrik on 2014-07-15.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockLMRB flag = new BlockFlag();
    public static final BlockLMRB machine = new BlockMachine();
    public static final BlockContainerLMRB bomb = new BlockBomb();

    public static void init() {
        GameRegistry.registerBlock(flag, BlockInfo.FLAG_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(machine, BlockInfo.MACHINE_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(bomb, BlockInfo.BOMB_UNLOCALIZED_NAME);
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityBomb.class, BlockInfo.BOMB_TE_KEY);
    }
}
