package com.fr3gu.letsmodreboot.command;

import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

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
        final EntityPlayer p = (EntityPlayer) commandSender;

        if(input.length == 0) {
            p.addChatMessage(new ChatComponentText("Inga argument!"));
            return;
        }

        if(input[0].equalsIgnoreCase("help")) {
            p.addChatMessage(new ChatComponentText("Enter block name as unlocalized with modid, i.e. \"letsmodreboot:flag\""));
            return;
        }

        try {
            ArrayList<Reka> foundBlocks = new ArrayList<Reka>();
            for (int z = -5; z < 5; z++) {
                for (int y = -5; y < 5; y++) {
                    for (int x = -5; x < 5; x++) {
                        int posX = (int) p.posX + x;
                        int posY = (int) p.posY + y;
                        int posZ = (int) p.posZ + z;
                        Block b = p.worldObj.getBlock(posX, posY, posZ);
                        String blockId = b.getUnlocalizedName();
                        if(blockId.equals("tile." + input[0])) {
                            foundBlocks.add(new Reka(b, posX, posY, posZ));
                        }
                    }
                }
            }

            if(foundBlocks.size() > 0) {
                for (Reka r : foundBlocks) {
                    String blockName = r.getBlock().getLocalizedName();
                    System.out.println("BlockId: " + blockName);
                    int[] coords = r.getCoords();
                    p.addChatMessage(new ChatComponentText("Found block (" + blockName + ") at " + coords[0] + " " + coords[1] + " " + coords[2]));
                }
                p.addChatMessage(new ChatComponentText("---------------------------"));
                p.addChatMessage(new ChatComponentText("Found blocks: " + foundBlocks.size()));
            }
            else {
                p.addChatMessage(new ChatComponentText("Could not find block \"" + input[0] + "\""));
            }

            if(input.length > 1 && input.length < 4) {
                int x = Integer.parseInt(input[1]);
                int y = Integer.parseInt(input[2]);
                int z = Integer.parseInt(input[3]);

                String blockId = p.worldObj.getBlock(x, y, z).getUnlocalizedName();
                p.addChatMessage(new ChatComponentText("BlockId (x y z): " + blockId));
            }

        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


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
