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
package me.sakizcapes.commands;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.sakizcapes.SakizCapes;
import me.sakizcapes.cape.CapeDownloader;
import me.sakizcapes.cape.CapeMode;
import me.sakizcapes.commons.SimpleSender;
import me.sakizcapes.utils.ChatColor;
import me.sakizcapes.utils.ImageUtils;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

/**
 * Class which handles command input for "/simplecapes"
 */
public class SCCommand implements ICommand {

    // Red chat color, since Forge has issues when sending messages (if string needs a new line it becomes white)
    private me.sakizcapes.utils.ChatColor red = ChatColor.RED;

    /**
     * Gets the name of the command
     */
    @Override
    public String func_71517_b() {
        return "sakizcapes";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender The command sender that executed the command
     */
    @Override
    public String func_71518_a(ICommandSender sender) {
        return red+"\n/sc <ackanat/kapakanat/accape/kapacape>\n/sc resim <resim>\n/sc url <URL>\n/sc pano";
    }

    @Override
    public List<String> func_71514_a() {
        return Collections.singletonList("sc");
    }

    /**
     * Callback when the command is invoked
     *
     * @param sender The command sender that executed the command
     * @param args   The arguments that were passed
     */
    @Override
    public void func_71515_b(ICommandSender sender, String[] args) {
    	if(args.length==0) {
    		SimpleSender.send("&cYanl�� kullan�m. " + red + "Bunlar� dene" + func_71518_a(sender));
    	} else {
    		if(args[0].equalsIgnoreCase("ackanat")) {
    			
    			SakizCapes.getSettings().setWingEnabled(true);
                SimpleSender.send("&aCape/Kanat a��ld�");
    		}
    		else if(args[0].equalsIgnoreCase("kapakanat")) {
    			
    			SakizCapes.getSettings().setWingEnabled(false);
                SimpleSender.send("&aCape/Kanat kapand�");
    		}
    		else if(args[0].equalsIgnoreCase("accape")) {
    			SakizCapes.getSettings().setEnabled(true);
    			
                SimpleSender.send("&aCape/Kanat a��ld�");
    		}
    		else if(args[0].equalsIgnoreCase("kapacape")) {
    			SakizCapes.getSettings().setEnabled(false);
                SimpleSender.send("&aCape/Kanat kapand�");
    		}
    		else if(args[0].equalsIgnoreCase("resim")) {
    			String name = args[1];
                if (ImageUtils.getImageFromFile(name) == null) {
                    SimpleSender.send("&cBilinmeyen resim");
                } else {
                    SakizCapes.getSettings().setCurrentMode(CapeMode.LOCAL);
                    SakizCapes.getSettings().setCapePath(name);
                    CapeDownloader.DOWNLOADER.updateCachedTexture();
                    SakizCapes.getSettings().setCapeSet(true);
                    SimpleSender.send("&aCape yenilendi");
                }
    		}
    		else if(args[0].equalsIgnoreCase("url")) {
    			SimpleSender.send("&eURL i�leniyor");
                String url = args[1];
                BufferedImage capeImage = ImageUtils.getImageFromURL(url);
                if (capeImage == null) {
                    SimpleSender.send("&cBilinmeyen URL!");
                } else {
                    SakizCapes.getSettings().setCurrentMode(CapeMode.URL);
                    SakizCapes.getSettings().setCapeURL(url);
                    CapeDownloader.DOWNLOADER.updateCachedTexture();
                    SakizCapes.getSettings().setCapeSet(true);
                    SimpleSender.send("&aCape yenilendi");
                }
    		}
    		else if(args[0].equalsIgnoreCase("pano")) {
    			if (ImageUtils.getImageFromClipboard() == null) {
                    SimpleSender.send("&cHi� bir resmi kopyalamam��s�n!");
                } else {
                    SakizCapes.getSettings().setCurrentMode(CapeMode.CLIPBOARD);
                    ImageUtils.saveImage(ImageUtils.getImageFromClipboard());
                    CapeDownloader.DOWNLOADER.updateCachedTexture();
                    SakizCapes.getSettings().setCapeSet(true);
                    SimpleSender.send("&aCape yenilendi");
                }
    		} else {
    			SimpleSender.send("&cYanl�� kullan�m. " + red + "Bunlar� dene" + func_71518_a(sender));
    		}
    	}
        
    }

    /**
     * Returns true if the given command sender is allowed to use this command.
     *
     * @param sender The command sender that executed the command
     */
    @Override
    public boolean func_71519_b(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> tab = new ArrayList<String>();
        tab.add("accape");
        tab.add("kapacape");
        tab.add("ackanat");
        tab.add("kapakanat");
        tab.add("resim");
        tab.add("url");
        tab.add("pano");
        return tab;
    }


    @Override
    public boolean func_82358_a(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

}
