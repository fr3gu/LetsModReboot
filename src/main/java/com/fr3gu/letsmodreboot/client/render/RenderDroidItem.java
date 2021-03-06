package com.fr3gu.letsmodreboot.client.render;

import com.fr3gu.letsmodreboot.model.ModelDroid;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.client.IItemRenderer;

public class RenderDroidItem implements IItemRenderer {

    private ModelDroid _model;

    public RenderDroidItem(ModelDroid model) {
        _model = model;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type != ItemRenderType.ENTITY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        GL11.glTranslatef(1F, 1F, 1F);

        switch (type) {
            case ENTITY:
                GL11.glTranslatef(-0.5F, 0F, 0F);
                break;
            case EQUIPPED:
                GL11.glTranslatef(-0.6F, -0.7F, -0.8F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(-1.0F, -0.1F, -0.2F);
                break;
            case INVENTORY:
                GL11.glTranslatef(0F, -0.40F, 0F);
                break;
            case FIRST_PERSON_MAP:
                break;
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(RenderDroid.texture);

        _model.render(0, 0, (float)Math.PI, 6, 0.5F, 0F, item.stackSize / 64F, 0.0625F);

        GL11.glPopMatrix();
    }
}
