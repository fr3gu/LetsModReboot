package com.fr3gu.letsmodreboot.proxy;

import com.fr3gu.letsmodreboot.handler.*;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy implements IProxy {

    public void registerEventHandlers() {
        ItemEventHandler itemEventHandler = new ItemEventHandler();
        CraftingHandler craftingHandler = new CraftingHandler();
        PlayerEventHandler playerEventHandler = new PlayerEventHandler();

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(itemEventHandler);
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(playerEventHandler);
        FMLCommonHandler.instance().bus().register(craftingHandler);
        MinecraftForge.EVENT_BUS.register(craftingHandler);
    }
}
