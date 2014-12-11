package com.fr3gu.letsmodreboot.waila;

import com.fr3gu.letsmodreboot.tileentity.TileEntityBomb;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class WailaDataProvider implements IWailaDataProvider {
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        if (accessor.getTileEntity() instanceof TileEntityBomb)
        {
            if (accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY + 1, accessor.getPosition().blockZ) instanceof TileEntityBomb)
            {
                currentTip.set(0, String.format("%s", StatCollector.translateToLocal("tile.letsmodreboot:bombWaila")));
            }
        }

        return currentTip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        if (accessor.getTileEntity() instanceof TileEntityBomb)
        {
            if (accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY + 1, accessor.getPosition().blockZ) instanceof TileEntityBomb)
            {
                currentTip.add(0, String.format("%s", StatCollector.translateToLocal("tile.letsmodreboot:bombWaila")));
            }
        }
        return currentTip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currentTip;
    }

    public static void callbackRegister(IWailaRegistrar registrar)
    {
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityBomb.class);
    }
}
