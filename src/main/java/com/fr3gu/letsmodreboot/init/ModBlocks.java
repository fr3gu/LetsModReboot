package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.block.BlockFlag;
import com.fr3gu.letsmodreboot.block.BlockLMRB;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Fredrik on 2014-07-15.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockLMRB flag = new BlockFlag();

    public static void init() {
        GameRegistry.registerBlock(flag, "flag");
    }
}
