package com.fr3gu.letsmodreboot.model;

import com.fr3gu.letsmodreboot.entity.EntityDroid;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2014-11-30.
 */
public class ModelDroid extends ModelBase {

    private ArrayList<ModelRenderer> parts;
    private ModelRenderer core;
    private ArrayList<ModelRenderer> panels;

    public ModelDroid() {
        parts = new ArrayList<ModelRenderer>();
        textureHeight = 64;
        textureWidth = 64;

        ModelRenderer main = new ModelRenderer(this, 0, 0);
        main.addBox(-5, -5, -5, 10, 10, 10);
        main.setRotationPoint(0, 0, 0);
        parts.add(main);

        ModelRenderer pillars = new ModelRenderer(this, 0, 20);
        for(int x = -1; x <= 1; x += 2) {
            for(int z = -1; z <= 1; z += 2) {
                pillars.addBox(-1 + x * 4, -1, -1 + z * 4, 2, 2, 2);
            }
        }
        pillars.setRotationPoint(0, 6, 0);
        parts.add(pillars);

        ModelRenderer top = new ModelRenderer(this, 0, 20);
        top.addBox(-5, -2, -5, 10, 4, 10);
        top.setRotationPoint(0, 9, 0);
        parts.add(top);

        panels = new ArrayList<ModelRenderer>();
        for(float r = 0; r < Math.PI * 2; r += Math.PI / 2) {
            ModelRenderer side = new ModelRenderer(this, 0, 34);
            side.addBox(-4, -2.5F, 5, 8, 5, 1);
            side.setRotationPoint(0, 0, 0);
            side.rotateAngleY = r;
            parts.add(side);

            ModelRenderer panel = new ModelRenderer(this, 18, 34);
            panel.addBox(-4, -0.5F, -0.5F, 8, 5, 1);
            panel.setRotationPoint(0, 2, 4.5F);
            side.addChild(panel);
            panels.add(panel);
        }

        core = new ModelRenderer(this, 30, 0);
        core.addBox(-3, -1, -3, 6, 2, 6);
        core.setRotationPoint(0, 6, 0);
        parts.add(core);
    }

    @Override
    public void render(Entity entity, float val1, float val2, float val3, float val4, float val5, float mult) {
        EntityDroid droid = (EntityDroid)entity;
        core.rotateAngleY = droid.getCoreRotation();
        for(ModelRenderer panel: panels) {
            panel.rotateAngleX = droid.getPanelRotation();
        }
        for(ModelRenderer part: parts) {
            part.render(mult);
        }
    }
}
