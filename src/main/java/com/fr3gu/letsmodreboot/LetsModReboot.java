package com.fr3gu.letsmodreboot;

import com.fr3gu.letsmodreboot.command.SampleCommand;
import com.fr3gu.letsmodreboot.handler.ConfigurationHandler;
import com.fr3gu.letsmodreboot.init.ModBlocks;
import com.fr3gu.letsmodreboot.init.ModEntities;
import com.fr3gu.letsmodreboot.init.ModItems;
import com.fr3gu.letsmodreboot.proxy.IProxy;
import com.fr3gu.letsmodreboot.reference.Reference;
import com.fr3gu.letsmodreboot.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class LetsModReboot {

    @Mod.Instance(Reference.MOD_ID)
    public static LetsModReboot instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();

        ModBlocks.init();

        ModEntities.init();

        LogHelper.info("Pre-initialization complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.initRenderers();

        LogHelper.info("Initialization complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new SampleCommand());
    }
}
