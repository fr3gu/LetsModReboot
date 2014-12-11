package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.block.*;
import com.fr3gu.letsmodreboot.item.ItemMachine;
import com.fr3gu.letsmodreboot.reference.Reference;
import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockLMRB flag = new BlockFlag();
    public static final BlockLMRB machine = new BlockMachine();
    public static final BlockContainerLMRB bomb = new BlockBomb();

    public static void init() {
        GameRegistry.registerBlock(flag, BlockInfo.FLAG_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(machine, ItemMachine.class, BlockInfo.MACHINE_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(bomb, BlockInfo.BOMB_UNLOCALIZED_NAME);
    }
}
