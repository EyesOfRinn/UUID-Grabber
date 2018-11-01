package xyz.rinn.uuidgrabber.utils;

import java.util.UUID;

public class UUIDUtils {
    public static UUID getUUID(final String id) {
        return UUID.fromString(id.substring(0, 8) + '-' + id.substring(8, 12) + '-' + id.substring(12, 16) + '-' + id.substring(16, 20) + '-' + id.substring(20, 32));
    }
}