package me.sakizcapes.wing;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import me.sakizcapes.SakizCapes;
import me.sakizcapes.commons.Settings;
import me.sakizcapes.utils.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class WingRenderer extends ModelBase {
	private Minecraft mc;
	private ResourceLocation location;
	private ModelRenderer wing;
	private ModelRenderer wingTip;
	private boolean playerUsesFullHeight;

	public WingRenderer() throws IOException
	{
		this.mc = Minecraft.func_71410_x();
	
		this.location = new ResourceLocation(Reference.MOD_ID,"wings.png");
		
		this.playerUsesFullHeight = Loader.isModLoaded("animations");

		// Set texture offsets.
		func_78085_a("wing.bone", 0, 0);
		func_78085_a("wing.skin", -10, 8);
		func_78085_a("wingtip.bone", 0, 5);
		func_78085_a("wingtip.skin", -10, 18);

		// Create wing model renderer.
		wing = new ModelRenderer(this, "wing");
		wing.func_78787_b(30, 30); // 300px / 10px
		wing.func_78793_a(-2F, 0, 0);
		wing.func_78786_a("bone", -10.0F, -1.0F, -1.0F, 10, 2, 2);
		wing.func_78786_a("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10);

		// Create wing tip model renderer.
		wingTip = new ModelRenderer(this, "wingtip");
		wingTip.func_78787_b(30, 30); // 300px / 10px
		wingTip.func_78793_a(-10.0F, 0.0F, 0.0F);
		wingTip.func_78786_a("bone", -10.0F, -0.5F, -0.5F, 10, 1, 1);
		wingTip.func_78786_a("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10);
		wing.func_78792_a(wingTip); // Make the wingtip rotate around the wing.
	}

	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event)
	{
		EntityPlayer player = event.entityPlayer;

		if (SakizCapes.getSettings().isWingEnabled() && player.equals(mc.field_71439_g) && !player.func_82150_aj()) // Should render wings onto this player?
		{
			renderWings(player, event.partialRenderTick);
		}
	}

	private void renderWings(EntityPlayer player, float partialTicks)
	{
		double scale = 100 / 100D;
		double rotate = Settings.interpolate(player.field_70760_ar, player.field_70761_aq, partialTicks);

		GL11.glPushMatrix();
		GL11.glScaled(-scale, -scale, scale);
		GL11.glRotated(180 + rotate, 0, 1, 0); // Rotate the wings to be with the player.
		GL11.glTranslated(0, -(playerUsesFullHeight ? 1.45 : 1.25) / scale, 0); // Move wings correct amount up.
		GL11.glTranslated(0, 0, 0.2 / scale);

		if (player.func_70093_af())
		{
			GL11.glTranslated(0D, 0.125D / scale, 0D);
		}

		
		mc.func_110434_K().func_110577_a(location);

		for (int j = 0; j < 2; ++j)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
			float f11 = (System.currentTimeMillis() % 1000) / 1000F * (float) Math.PI * 2.0F;
			this.wing.field_78795_f = (float) Math.toRadians(-80F) - (float) Math.cos((double)f11) * 0.2F;
			this.wing.field_78796_g = (float) Math.toRadians(20F) + (float) Math.sin(f11) * 0.4F;
			this.wing.field_78808_h = (float) Math.toRadians(20F);
			this.wingTip.field_78808_h = -((float)(Math.sin((double)(f11 + 2.0F)) + 0.5D)) * 0.75F;
			this.wing.func_78785_a(0.0625F);
			GL11.glScalef(-1.0F, 1.0F, 1.0F);

			if (j == 0)
			{
				GL11.glCullFace(1028);
			}
		}

		GL11.glCullFace(1029);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glColor3f(255F, 255F, 255F);
		GL11.glPopMatrix();
	}

	
}
