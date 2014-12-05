package com.fr3gu.letsmodreboot.network;

import com.fr3gu.letsmodreboot.network.message.MessageTileEntityLMRB;
import com.fr3gu.letsmodreboot.reference.Reference;
import com.fr3gu.letsmodreboot.tileentity.MessageTileEntityDummy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(MessageTileEntityLMRB.class, MessageTileEntityLMRB.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageTileEntityDummy.class, MessageTileEntityDummy.class, 10, Side.CLIENT);
    }
}
