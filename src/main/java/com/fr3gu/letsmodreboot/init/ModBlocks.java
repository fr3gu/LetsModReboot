package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.block.*;
import com.fr3gu.letsmodreboot.item.ItemMachine;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockLMRB flag = new BlockFlag();
    public static final BlockLMRB machine = new BlockMachine();
    public static final BlockContainerBase bomb = new BlockBomb();

    public static void init() {
        GameRegistry.register(flag, new ResourceLocation(BlockInfo.FLAG_UNLOCALIZED_NAME));
        GameRegistry.registerBlock(machine, ItemMachine.class, BlockInfo.MACHINE_UNLOCALIZED_NAME);
        GameRegistry.registerBlock(bomb, BlockInfo.BOMB_UNLOCALIZED_NAME);
    }

    public static void register(Block block) {

    }
}
