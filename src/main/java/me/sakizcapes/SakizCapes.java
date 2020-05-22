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
package me.sakizcapes;

import java.io.File;
import java.io.IOException;

import me.sakizcapes.commands.SCCommand;
import me.sakizcapes.commons.Settings;
import me.sakizcapes.listeners.EntityWorldJoinListener;
import me.sakizcapes.proxy.IProxy;
import me.sakizcapes.utils.Reference;
import me.sakizcapes.wing.WingRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * SimpleCapes: A mod which gives a cape to the player
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class SakizCapes {

    // Config for saving data
    private static Configuration config;

    // Mod commons
    private static Settings settings;

    // Assign proxies of the mod
    @SidedProxy(

            // Client side proxy
            clientSide = Reference.CLIENT_PROXY,

            // Server side proxy
            serverSide = Reference.SERVER_PROXY
    )
    private static IProxy proxy;

    // The update manager

    
    /*
     * Initialize variables here
     */
    static {
        config = new Configuration(new File("config/sakizcapes.cfg"));
        settings = new Settings();

    }

    /**
     * Called before the mod is fully initialized
     * <p>
     * Registries: Initiate variables and client command registries
     *
     * @param event Forge's pre-init event
     */
    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
    	ClientCommandHandler.instance.registerCommand(new SCCommand());
        File modDirectory = new File(Minecraft.getMinecraft().mcDataDir, Reference.MOD_ID);
        if (!modDirectory.exists()) modDirectory.mkdirs();
        
    }

    /**
     * Called when the mod has been fully initialized
     * <p>
     * Registries: Events and client-server command registries
     *
     * @param event Forge's init event
     * @throws IOException 
     */
    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) throws IOException {

        MinecraftForge.EVENT_BUS.register(new EntityWorldJoinListener());
		MinecraftForge.EVENT_BUS.register(new WingRenderer());
		
    }

    /**
     * Called after the mod has been successfully initialized
     * <p>
     * Registries: Nothing
     *
     * @param event Forge's post init event
     */
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event) {
    }

    /**
     * The mod config
     *
     * @return The config file used to store all the mod data and HTTP caches if any
     */
    public static Configuration getConfig() {
        return config;
    }

    /**
     * Mod settings
     *
     * @return An instance of the mod commons
     */
    public static Settings getSettings() {
        return settings;
    }

    /**
     * The mod update manager
     *
     * @return An instance of the mod update manager
     */
   

}
