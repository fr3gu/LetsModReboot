package com.fr3gu.letsmodreboot.client.render;

import com.fr3gu.letsmodreboot.entity.EntitySpaceship;
import com.fr3gu.letsmodreboot.model.ModelSpaceship;
import com.fr3gu.letsmodreboot.reference.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSpaceship extends Render {
	
	public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/spaceship.png");
    public static final ResourceLocation chargedTexture = new ResourceLocation(Reference.MOD_ID + ":textures/models/spaceship_charged.png");
	
	protected ModelSpaceship _model;
	
	public RenderSpaceship(ModelSpaceship model) {
		shadowSize = 0.5F;
		_model = model;
	}
	
	public void renderSpaceship(EntitySpaceship spaceship, double x, double y, double z, float yaw, float partialTickTime) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(-1.0F, -1.0F, 1.0F);

        bindEntityTexture(spaceship);
		
		_model.render(spaceship, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		this.renderSpaceship((EntitySpaceship)entity, x, y, z, yaw, partialTickTime);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntitySpaceship)entity).getIsCharged() ? chargedTexture : texture;
	}
	
}
