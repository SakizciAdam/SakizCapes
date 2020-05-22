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
package me.sakizcapes.listeners;

import me.sakizcapes.SakizCapes;
import me.sakizcapes.commons.SimpleSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * Listener which adds the cape to the player upon joining the world
 */
public class EntityWorldJoinListener {

    // Minecraft instance
    private Minecraft mc = Minecraft.func_71410_x();
    
    
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
    	if (!SakizCapes.getSettings().isEnabled()) return; // Return if the mod isn't enabled
        if (!(event.entity instanceof EntityPlayer)) return; // Return if the entity wasn't a player
        if (event.entity.getPersistentID().equals(mc.func_110432_I().func_148256_e().getId())) renderCape();
    }
    /*
    @SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Post event)
	{
    	if (!SakizCapes.getSettings().isEnabled()) return; // Return if the mod isn't enabled
        if (!(event.entity instanceof EntityPlayer)) return; // Return if the entity wasn't a player
        if (event.entity.getPersistentID().equals(mc.getSession().getProfile().getId())) renderCape();
	}*/

   
    private void renderCape() {
        Minecraft.func_71410_x().field_71474_y.func_178878_a(EnumPlayerModelParts.CAPE, true);
        for (RenderPlayer render : Minecraft.func_71410_x().func_175598_ae().getSkinMap().values()) {
            render.func_177094_a(new me.sakizcapes.cape.CapeLayer(render));
        }
    }
}
