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
package me.sakizcapes.commons;

import me.sakizcapes.SakizCapes;
import me.sakizcapes.cape.CapeMode;

/**
 * Class which contains and manages all the mod commons (while saving it to config etc)
 */
public class Settings {


    private boolean enabled;
    private boolean wing;
    private boolean dragon;
    private boolean sendUpdateNotifications;
    private boolean capeSet = false;

    private String capeURL;
    private String capePath;

    private CapeMode currentMode;

    public Settings() {
        enabled = SakizCapes.getConfig().get("Enabled", "Enabled", true).getBoolean();
        wing = SakizCapes.getConfig().get("EnabledWing", "EnabledWing", true).getBoolean();
        dragon = SakizCapes.getConfig().get("EnabledDragon", "EnabledDragon", true).getBoolean();

        sendUpdateNotifications = SakizCapes.getConfig().get("Settings", "SendUpdates", true).getBoolean();
        capeSet = SakizCapes.getConfig().get("Cape", "Set", false).getBoolean();
        capePath = SakizCapes.getConfig().get("Cape", "Path", "").getString();
        capeURL = SakizCapes.getConfig().get("Cape", "URL", "").getString();
        currentMode = CapeMode.fromName(SakizCapes.getConfig().get("Cape", "Mode", "URL").getString());
    }
    /*
     * 
     * Wing
     * 
     */
    public boolean isWingEnabled() {
        return wing;
    }
    public void setWingEnabled(boolean wing) {
        this.wing = wing;
        SakizCapes.getConfig().get("EnabledWing", "EnabledWing", true).set(wing);
        SakizCapes.getConfig().save();
    }

    /*
     * 
     * Other
     * 
     */
    public void setSendNotification(boolean flag) {
        this.sendUpdateNotifications = flag;
        SakizCapes.getConfig().get("Settings", "SendUpdates", true).set(flag);
        SakizCapes.getConfig().save();
    }
    public boolean sendNotification() {
        return sendUpdateNotifications;
    }
    public static float interpolate(float yaw1, float yaw2, float percent)
	{
		float f = (yaw1 + (yaw2 - yaw1) * percent) % 360;

		if (f < 0)
		{
			f += 360;
		}

		return f;
	}
    /*
     * 
     * CAPE
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        SakizCapes.getConfig().get("Enabled", "Enabled", true).set(enabled);
        SakizCapes.getConfig().save();
    }
    public String getCapeURL() {
        return capeURL;
    }
    public void setCapeURL(String capeURL) {
        this.capeURL = capeURL;
        SakizCapes.getConfig().get("Cape", "URL", "").set(capeURL);
        SakizCapes.getConfig().save();
    }
    public String getCapePath() {
        return capePath;
    }
    public void setCapePath(String capePath) {
        this.capePath = capePath;
        SakizCapes.getConfig().get("Cape", "Path", "").set(capePath);
        SakizCapes.getConfig().save();
    }
    public CapeMode getCurrentMode() {
        return currentMode;
    }
    public void setCurrentMode(CapeMode currentMode) {
        this.currentMode = currentMode;
        SakizCapes.getConfig().get("Cape", "Mode", "URL").set(currentMode.name());
        if (currentMode == CapeMode.CLIPBOARD) {
            SakizCapes.getConfig().get("Cape", "ClipboardSaved", false).set(true);
        }
        SakizCapes.getConfig().save();
    }
    public boolean isCapeSet() {
        return capeSet;
    }
    public void setCapeSet(boolean capeSet) {
        this.capeSet = capeSet;
        SakizCapes.getConfig().get("Cape", "Set", false).set(capeSet);
        SakizCapes.getConfig().save();
    }

    public boolean isClipboardSaved() {
        return SakizCapes.getConfig().get("Cape", "ClipboardSaved", false).getBoolean();
    }

}
