package com.fr3gu.letsmodreboot.skill;

import com.fr3gu.letsmodreboot.contract.INBTTaggable;
import com.fr3gu.letsmodreboot.reference.Names;
import com.fr3gu.letsmodreboot.utility.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class PlayerKnowledge implements INBTTaggable
{
    private Set<ItemStack> knownItemStacks;

    public PlayerKnowledge()
    {
        this.knownItemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
    }

    public PlayerKnowledge(Collection<ItemStack> knownItemStacks)
    {
        this.knownItemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.knownItemStacks.addAll(knownItemStacks);
    }

    public PlayerKnowledge(ItemStack... knownItemStacks)
    {
        this(Arrays.asList(knownItemStacks));
    }

    public PlayerKnowledge(NBTTagCompound nbtTagCompound)
    {
        this.knownItemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.readFromNBT(nbtTagCompound);
    }

    public boolean isItemStackKnown(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;
        return this.knownItemStacks.contains(unitItemStack);
    }

    public Set<ItemStack> getKnownItemStacks()
    {
        return this.knownItemStacks;
    }

    public boolean learnItemStack(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (!this.knownItemStacks.contains(unitItemStack))
        {
            return this.knownItemStacks.add(unitItemStack);
        }

        return false;
    }

    public boolean forgetItemStack(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (this.knownItemStacks.contains(unitItemStack))
        {
            return this.knownItemStacks.remove(unitItemStack);
        }

        return false;
    }

    public void resetKnownItemStacks()
    {
        this.knownItemStacks.clear();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey(Names.NBT.KNOWLEDGE))
        {
            // Read in the ItemStacks in the inventory from NBT
            if (nbtTagCompound.hasKey(Names.NBT.KNOWLEDGE))
            {
                NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.KNOWLEDGE, 10);
                knownItemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
                for (int i = 0; i < tagList.tagCount(); ++i)
                {
                    NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                    ItemStack itemStack = ItemStack.loadItemStackFromNBT(tagCompound);
                    knownItemStacks.add(itemStack);
                }
            }
            else
            {
                knownItemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
            }
        }
        else
        {
            knownItemStacks = new TreeSet<ItemStack>(ItemHelper.comparator);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        // Write the ItemStacks in the set to NBT
        NBTTagList tagList = new NBTTagList();
        for (ItemStack itemStack : knownItemStacks)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
        if(nbtTagCompound != null)
            nbtTagCompound.setTag(Names.NBT.KNOWLEDGE, tagList);
    }

    public static PlayerKnowledge readPlayerKnowledgeFromNBT(NBTTagCompound nbtTagCompound)
    {
        PlayerKnowledge playerKnowledge = new PlayerKnowledge();

        playerKnowledge.readFromNBT(nbtTagCompound);

        return playerKnowledge;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (ItemStack itemStack : knownItemStacks)
        {
            stringBuilder.append(String.format("%s, ", ItemHelper.toString(itemStack)));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
