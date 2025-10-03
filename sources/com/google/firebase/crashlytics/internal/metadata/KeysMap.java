package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
class KeysMap {
    private final Map<String, String> keys = new HashMap();
    private final int maxEntries;
    private final int maxEntryLength;

    public KeysMap(int i9, int i10) {
        this.maxEntries = i9;
        this.maxEntryLength = i10;
    }

    private String sanitizeKey(String str) {
        if (str != null) {
            return sanitizeString(str, this.maxEntryLength);
        }
        throw new IllegalArgumentException("Custom attribute key must not be null.");
    }

    public static String sanitizeString(String str, int i9) {
        if (str == null) {
            return str;
        }
        String strTrim = str.trim();
        return strTrim.length() > i9 ? strTrim.substring(0, i9) : strTrim;
    }

    public synchronized Map<String, String> getKeys() {
        return Collections.unmodifiableMap(new HashMap(this.keys));
    }

    public synchronized boolean setKey(String str, String str2) {
        String strSanitizeKey = sanitizeKey(str);
        if (this.keys.size() >= this.maxEntries && !this.keys.containsKey(strSanitizeKey)) {
            Logger.getLogger().m17775w("Ignored entry \"" + str + "\" when adding custom keys. Maximum allowable: " + this.maxEntries);
            return false;
        }
        String strSanitizeString = sanitizeString(str2, this.maxEntryLength);
        if (CommonUtils.nullSafeEquals(this.keys.get(strSanitizeKey), strSanitizeString)) {
            return false;
        }
        Map<String, String> map = this.keys;
        if (str2 == null) {
            strSanitizeString = "";
        }
        map.put(strSanitizeKey, strSanitizeString);
        return true;
    }

    public synchronized void setKeys(Map<String, String> map) {
        int i9 = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strSanitizeKey = sanitizeKey(entry.getKey());
            if (this.keys.size() < this.maxEntries || this.keys.containsKey(strSanitizeKey)) {
                String value = entry.getValue();
                this.keys.put(strSanitizeKey, value == null ? "" : sanitizeString(value, this.maxEntryLength));
            } else {
                i9++;
            }
        }
        if (i9 > 0) {
            Logger.getLogger().m17775w("Ignored " + i9 + " entries when adding custom keys. Maximum allowable: " + this.maxEntries);
        }
    }
}
