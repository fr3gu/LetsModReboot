package com.fr3gu.letsmodreboot.client.render;

import com.fr3gu.letsmodreboot.entity.EntityDroid;
import com.fr3gu.letsmodreboot.model.ModelDroid;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Fredrik on 2014-11-30.
 */
public class RenderDroid extends Render {

    private ModelDroid model;

    public RenderDroid() {
        model = new ModelDroid();
        shadowSize = 0.5F;
    }

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/droid.png");

    public void renderDroid(EntityDroid droid, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glScalef(1F, 1F, 1F);

        bindEntityTexture(droid);

        model.render(droid, 0F, 0F, 0F, 0F, 0F, 0.0625F);

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
