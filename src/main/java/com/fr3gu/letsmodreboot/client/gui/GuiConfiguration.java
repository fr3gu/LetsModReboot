package com.fr3gu.letsmodreboot.client.gui;

import com.fr3gu.letsmodreboot.handler.ConfigurationHandler;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

@SuppressWarnings("unsafe")
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
