package com.fr3gu.letsmodreboot.client.render;

import com.fr3gu.letsmodreboot.entity.EntityDroid;
import com.fr3gu.letsmodreboot.model.ModelDroid;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("UnusedParameters")
public class RenderDroid extends Render {

    private ModelDroid _model;

    public RenderDroid(ModelDroid model) {
        _model = model;
        shadowSize = 0.5F;
    }

    public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/droid.png");

    public void renderDroid(EntityDroid droid, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glScalef(1F, 1F, 1F);

        bindEntityTexture(droid);

        _model.render(droid.getCoreRotation(), droid.getPanelRotation(), droid.getOuterPanelRotation(), droid.getHelmetPosition(), droid.getColorRed(), droid.getColorGreen(), droid.getColorBlue(), 0.0625F);

        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
        renderDroid((EntityDroid) entity, x, y, z, yaw, partialTickTime);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
