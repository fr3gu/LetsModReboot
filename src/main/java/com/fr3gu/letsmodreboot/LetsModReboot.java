package com.fr3gu.letsmodreboot;

import com.fr3gu.letsmodreboot.command.SampleCommand;
import com.fr3gu.letsmodreboot.handler.ConfigurationHandler;
import com.fr3gu.letsmodreboot.handler.CraftingHandler;
import com.fr3gu.letsmodreboot.handler.GuiHandler;
import com.fr3gu.letsmodreboot.init.*;
import com.fr3gu.letsmodreboot.network.PacketHandler;
import com.fr3gu.letsmodreboot.proxy.IProxy;
import com.fr3gu.letsmodreboot.reference.Reference;
import com.fr3gu.letsmodreboot.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class LetsModReboot {

    @Mod.Instance(Reference.MOD_ID)
    public static LetsModReboot instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        PacketHandler.init();

        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();

        ModBlocks.init();

        ModEntities.init();

        LogHelper.info("Pre-initialization complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        ModTileEntities.init();

        proxy.initRenderingAndTextures();

        proxy.registerEventHandlers();

        CraftingHandler.init();

        ModRecipes.init();

        FMLInterModComms.sendMessage("Waila", "register", "mcp.mobius.waila_demo.ProviderDemo.callbackRegister");

        LogHelper.info("Initialization complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //RecipeRegistry.getInstance().registerVanillaRecipes();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new SampleCommand());
    }
}
