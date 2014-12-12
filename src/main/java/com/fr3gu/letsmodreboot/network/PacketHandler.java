package com.fr3gu.letsmodreboot.network;

import com.fr3gu.letsmodreboot.network.message.MessageTileEntityBomb;
import com.fr3gu.letsmodreboot.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntityBomb.class, MessageTileEntityBomb.class, 0, Side.SERVER);
    }
}
