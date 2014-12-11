package com.fr3gu.letsmodreboot.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {
    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ModItems.rocketWand), new Object[]{
                "  X",
                " / ",
                "/  ",
                'X', Items.golden_carrot,
                '/', Items.stick
        });
    }
}
