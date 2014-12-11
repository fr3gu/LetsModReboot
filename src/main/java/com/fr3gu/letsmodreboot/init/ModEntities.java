package com.fr3gu.letsmodreboot.init;

import com.fr3gu.letsmodreboot.LetsModReboot;
import com.fr3gu.letsmodreboot.entity.EntityDroid;
import com.fr3gu.letsmodreboot.entity.EntitySpaceship;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {

    public static void init() {
        EntityRegistry.registerModEntity(EntityDroid.class, "EntityDroid", 0, LetsModReboot.instance, 80, 3, true);
        EntityRegistry.registerModEntity(EntitySpaceship.class, "EntitySpaceship", 1, LetsModReboot.instance, 80, 3, true);

    }
}
