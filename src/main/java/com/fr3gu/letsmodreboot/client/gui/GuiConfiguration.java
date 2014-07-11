package com.fr3gu.letsmodreboot.client.gui;

import com.fr3gu.letsmodreboot.handler.ConfigurationHandler;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by Fredrik on 2014-07-10.
 */
public class GuiConfiguration extends GuiConfig {

    public GuiConfiguration(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}
