package com.fr3gu.letsmodreboot.command;

import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fredrik on 2014-11-09.
 */
public class SampleCommand implements ICommand {

    private List aliases;

    public SampleCommand() {
        this.aliases = new ArrayList();
        this.aliases.add("sample");
        this.aliases.add("sam");
    }

    @Override
    public String getCommandName() {
        return "sample";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return "sample <text>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] input) {
        World world = null;
        ICommandSender sender = commandSender instanceof EntityPlayer ? ((EntityPlayer) commandSender) : null;
        if(sender == null) {
            sender = commandSender instanceof DedicatedServer ? ((DedicatedServer) commandSender) : null;
            world = ((DedicatedServer)sender).getEntityWorld();
        }
        else {
            world = ((EntityPlayer)sender).worldObj;
        }

        double playerPosX = 0;
        double playerPosY = 64;
        double playerPosZ = 0;

        if(sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            playerPosX = Math.floor(player.posX);
            playerPosY = Math.floor(player.posY);
            playerPosZ = Math.floor(player.posZ);
        }

        if(input.length == 5) {
            playerPosX = Integer.parseInt(input[3]);
            playerPosY = Integer.parseInt(input[4]);
            playerPosZ = Integer.parseInt(input[5]);
        }

        if(input.length == 0) {
            sender.addChatMessage(new ChatComponentText("Inga argument!"));
            return;
        }

        if(input[0].equalsIgnoreCase("help")) {
            sender.addChatMessage(new ChatComponentText("Enter block name as unlocalized with modid, i.e. \"letsmodreboot:flag\""));
            return;
        }

        String blockNameIn = input[0];
        int radius = 5;
        int heightRadius = 5;
        if(input.length == 3) {
            System.out.println("Radius: " + input[1]);
            System.out.println("Height radius: " + input[2]);
            radius = Integer.parseInt(input[1]);
            heightRadius = Integer.parseInt(input[2]);
        }

        //try {
            ArrayList<Reka> foundBlocks = new ArrayList<Reka>();

            for (int z = -radius; z < radius; z++) {
                for (int y = -heightRadius; y < heightRadius; y++) {
                    for (int x = -radius; x < radius; x++) {
                        int posX = (int) playerPosX + x;
                        int posY = (int) playerPosY + y;
                        int posZ = (int) playerPosZ + z;
                        //System.out.println("Scanning " + posX + ", " + posY + ", " + posZ);
                        Block b = world.getBlock(posX, posY, posZ);
                        String blockId = b.getUnlocalizedName();
                        if(blockId.equals("tile." + blockNameIn)) {
                            foundBlocks.add(new Reka(b, posX, posY, posZ));
                        }
                    }
                }
            }

            if(foundBlocks.size() > 0) {
                for (Reka r : foundBlocks) {
                    String foundBlockName = r.getBlock().getLocalizedName();
                    System.out.println("BlockId: " + foundBlockName);
                    int[] coords = r.getCoords();
                    sender.addChatMessage(new ChatComponentText("Found block (" + foundBlockName + ") at " + coords[0] + " " + coords[1] + " " + coords[2]));
                }
                sender.addChatMessage(new ChatComponentText("---------------------------"));
                sender.addChatMessage(new ChatComponentText("Found blocks: " + foundBlocks.size()));
            }
            else {
                sender.addChatMessage(new ChatComponentText("Could not find block \"" + blockNameIn + "\""));
            }

            /*if(input.length > 1 && input.length < 4) {
                int x = Integer.parseInt(input[1]);
                int y = Integer.parseInt(input[2]);
                int z = Integer.parseInt(input[3]);

                String blockId = sender.worldObj.getBlock(x, y, z).getUnlocalizedName();
                sender.addChatMessage(new ChatComponentText("BlockId (x y z): " + blockId));
            }*/

        //}
        //catch (Exception e) {
        //    System.out.println("Error: " + e.getMessage());
        //}


    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] input) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] input, int index) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class Reka {
    private Block _block;
    private int[] _coords;

    public Reka(Block block, int posX, int posY, int posZ) {
        _block = block;
        _coords = new int[] { posX, posY, posZ};
    }

    public Block getBlock() {
        return _block;
    }

    public int[] getCoords() {
        return _coords;
    }


}
