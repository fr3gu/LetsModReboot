package com.fr3gu.letsmodreboot.client.render;

import com.fr3gu.letsmodreboot.model.ModelDroid;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

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

        GL11.glPushMatrix();
        GL11.glTranslatef(1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(RenderDroid.texture);

        _model.render(null, 0, 0, (float)Math.PI, -11.5F, 0, 0.0625F);

        GL11.glPopMatrix();
    }
}
