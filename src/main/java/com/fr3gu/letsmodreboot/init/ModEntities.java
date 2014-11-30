package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.LetsModReboot;
import com.fr3gu.letsmodreboot.entity.EntityDroid;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Fredrik on 2014-11-30.
 */
public class ModEntities {

    public static void init() {
        EntityRegistry.registerModEntity(EntityDroid.class, "EntityDroid", EntityRegistry.findGlobalUniqueEntityId(), LetsModReboot.instance, 80, 3, true);
    }
}
