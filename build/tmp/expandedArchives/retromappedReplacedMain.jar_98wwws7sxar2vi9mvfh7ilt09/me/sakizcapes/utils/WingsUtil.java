package me.sakizcapes.utils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WingsUtil {
	private static Map<UUID, Integer> rotateState = new ConcurrentHashMap<UUID, Integer>();
	public static float interpolate(float yaw1, float yaw2, float percent) {
        float rotation = (yaw1 + (yaw2 - yaw1) * percent) % 360.0f;
        if (rotation < 0.0f) rotation += 360.0f;
        return rotation;
    }
	public static int get(UUID uuid) {
        return rotateState.getOrDefault(uuid, 0);
    }
}
