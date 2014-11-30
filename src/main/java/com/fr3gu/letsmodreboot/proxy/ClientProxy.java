package com.fr3gu.letsmodreboot.proxy;

import com.fr3gu.letsmodreboot.client.render.RenderDroid;
import com.fr3gu.letsmodreboot.entity.EntityDroid;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * Created by Fredrik on 2014-07-10.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityDroid.class, new RenderDroid());
    }
}
