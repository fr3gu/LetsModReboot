package com.fr3gu.letsmodreboot.proxy;

import com.fr3gu.letsmodreboot.client.render.RenderDroid;
import com.fr3gu.letsmodreboot.client.render.RenderDroidItem;
import com.fr3gu.letsmodreboot.entity.EntityDroid;
import com.fr3gu.letsmodreboot.init.ModItems;
import com.fr3gu.letsmodreboot.item.ItemDroid;
import com.fr3gu.letsmodreboot.model.ModelDroid;
import com.fr3gu.letsmodreboot.reference.RenderIds;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {

        RenderIds.droid = RenderingRegistry.getNextAvailableRenderId();

        ModelDroid model = new ModelDroid();
        RenderingRegistry.registerEntityRenderingHandler(EntityDroid.class, new RenderDroid(model));
        MinecraftForgeClient.registerItemRenderer(ModItems.droid, new RenderDroidItem(model));
    }
}
