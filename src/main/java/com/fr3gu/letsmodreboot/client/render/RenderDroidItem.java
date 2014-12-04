package com.fr3gu.letsmodreboot.client.render;

import com.fr3gu.letsmodreboot.model.ModelDroid;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderDroidItem implements IItemRenderer {

    private ModelDroid _model;

    public RenderDroidItem(ModelDroid model) {
        _model = model;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

    }
}
