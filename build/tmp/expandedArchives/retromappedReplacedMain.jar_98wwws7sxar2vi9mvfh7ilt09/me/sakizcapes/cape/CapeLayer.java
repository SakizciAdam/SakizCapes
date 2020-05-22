/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.sakizcapes.cape;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;

import java.util.UUID;

import me.sakizcapes.SakizCapes;

/**
 * The cape layer which handles cape rendering
 */
public class CapeLayer implements LayerRenderer<AbstractClientPlayer> {

    // Player to render the cape for
    private final RenderPlayer playerRenderer;

    /**
     * Adds a cape layer to the player
     *
     * @param playerRendererIn Player to render the cape for
     */
    public CapeLayer(RenderPlayer playerRendererIn) {
        this.playerRenderer = playerRendererIn;
    }

    @Override
    public void func_177141_a(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!SakizCapes.getSettings().isCapeSet()) return;
        if (!SakizCapes.getSettings().isEnabled()) return;
        final UUID playerUUID = Minecraft.func_71410_x().func_110432_I().func_148256_e().getId();
        if (!entity.getPersistentID().equals(playerUUID)) return;
        if (!entity.func_82150_aj() && entity.func_175148_a(EnumPlayerModelParts.CAPE)) {
            float f9 = 0.14F;
            float f10 = 0.0F;
            if (entity.func_70093_af()) {
                f9 = 0.1F;
                f10 = 0.09F;
            }
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            try {
                this.playerRenderer.func_110776_a(CapeDownloader.DOWNLOADER.getCachedTexture());
            } catch (NullPointerException ignored) {
            }
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(0.0F, f10, f9);
            double d0 = entity.field_71091_bM + (entity.field_71094_bP - entity.field_71091_bM) * (double) partialTicks - (entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double) partialTicks);
            double d1 = entity.field_71096_bN + (entity.field_71095_bQ - entity.field_71096_bN) * (double) partialTicks - (entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double) partialTicks);
            double d2 = entity.field_71097_bO + (entity.field_71085_bR - entity.field_71097_bO) * (double) partialTicks - (entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double) partialTicks);
            float f = entity.field_70760_ar + (entity.field_70761_aq - entity.field_70760_ar) * partialTicks;
            double d3 = (double) MathHelper.func_76126_a(f * 0.017453292F);
            double d4 = (double) (-MathHelper.func_76134_b(f * 0.017453292F));
            float f1 = (float) d1 * 10.0F;
            f1 = MathHelper.func_76131_a(f1, 3.0F, 32.0F);
            float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
            float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            float f4 = entity.field_71107_bF + (entity.field_71109_bG - entity.field_71107_bF) * partialTicks;
            f1 += MathHelper.func_76126_a((entity.field_70141_P + (entity.field_70140_Q - entity.field_70141_P) * partialTicks) * 6.0F) * 32.0F * f4;
            if (entity.func_70093_af()) {
                f1 += 20.0F;
            }

            GlStateManager.func_179114_b(5.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179114_b(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
            this.playerRenderer.func_177087_b().func_178728_c(0.0625F);
            GlStateManager.func_179121_F();
        }
    }

    @Override
    public boolean func_177142_b() {
        return false;
    }
}
