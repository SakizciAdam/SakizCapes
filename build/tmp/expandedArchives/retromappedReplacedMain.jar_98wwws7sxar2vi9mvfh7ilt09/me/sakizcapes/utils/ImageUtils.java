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
package me.sakizcapes.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import me.sakizcapes.commons.SimpleSender;
import net.minecraft.client.Minecraft;

/**
 * A utility containing helping methods for images
 */
public class ImageUtils {

    /**
     * Fetches a {@link BufferedImage} from the given URL
     *
     * @param url URL to fetch from
     * @return A buffered image from the url
     */
    public static BufferedImage getImageFromURL(String url) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(url));
        } catch (IOException e) {
            me.sakizcapes.commons.SimpleSender.send("&cFailed to fetch &cimage from the &cgiven &cURL. &cTry &cagain &cin &ca &cminute &cor &cuse &canother URL.");
        }
        return image;
    }

    /**
     * Gets a {@link BufferedImage} from {@link Minecraft#mcDataDir} as a parent and {@link Reference#MOD_ID}
     * as a child
     *
     * @param fileName Name of the image
     * @return A buffered image with the name
     */
    public static BufferedImage getImageFromFile(String fileName) {
        File file = new File(Minecraft.func_71410_x().field_71412_D, Reference.MOD_ID + File.separator + fileName);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Converts the given {@link Image} into a {@link BufferedImage}
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bufferedImage;
    }

    /**
     * Fetches an {@link Image} from the system clipboard and converts it to a {@link BufferedImage}
     *
     * @return A buffered image from the system clipboard
     */
    public static BufferedImage getImageFromClipboard() {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                Image image = (Image) transferable.getTransferData(DataFlavor.imageFlavor);
                return toBufferedImage(image);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch(IOException e) {
            	e.printStackTrace();
            }
        }
        return null;
    }

    public static void saveImage(BufferedImage image) {
        try {
            File clipboardFile = new File(Minecraft.func_71410_x().field_71412_D, Reference.MOD_ID + File.separator + "clipboard.png");
            ImageIO.write(image, "png", clipboardFile);
        } catch (IOException e) {
            SimpleSender.send("&cFailed to save &cimage!");
            e.printStackTrace();
        }
    }

}
