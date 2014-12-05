package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.block.BlockFlag;
import com.fr3gu.letsmodreboot.block.BlockInfo;
import com.fr3gu.letsmodreboot.block.BlockLMRB;
import com.fr3gu.letsmodreboot.block.BlockMachine;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Fredrik on 2014-07-15.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockLMRB flag = new BlockFlag();
    public static final BlockLMRB machine = new BlockMachine();

    public static void init() {
        GameRegistry.registerBlock(flag, BlockInfo.FLAG_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(machine, BlockInfo.MACHINE_UNLOCALIZED_NAME);
    }
}
