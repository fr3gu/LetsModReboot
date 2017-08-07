package com.fr3gu.letsmodreboot.block;

import com.fr3gu.letsmodreboot.LetsModReboot;
import com.fr3gu.letsmodreboot.creativetab.CreativeTabLMRB;
import com.fr3gu.letsmodreboot.init.ModBlocks;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLMRB extends Block {

    private final String BASE_NAME;

    public BlockLMRB(String name, Material material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabLMRB.LMRB_TAB);
        BASE_NAME = name;

        ModBlocks.register(this);
    }

    public BlockLMRB(String name) {
        this(name, Material.ROCK);
    }

    /*
    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
    */

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s:%s", Reference.MOD_ID, BASE_NAME);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName().toString()));

    }

    /*
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    */
}
