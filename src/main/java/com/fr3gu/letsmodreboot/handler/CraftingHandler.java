package com.fr3gu.letsmodreboot.handler;

import com.fr3gu.letsmodreboot.contract.IOwnable;
import com.fr3gu.letsmodreboot.utility.ItemHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingHandler {
    public static void init()
    {
        // Add in the ability to dye Alchemical Bags
        //CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());
    }

//    @SubscribeEvent
//    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
//    {
//        if (event.crafting.getItem() instanceof IOwnable)
//        {
//            ItemHelper.setOwner(event.crafting, event.player);
//        }
//    }
}
