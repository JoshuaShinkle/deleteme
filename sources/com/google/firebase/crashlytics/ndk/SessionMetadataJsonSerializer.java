package com.google.firebase.crashlytics.ndk;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class SessionMetadataJsonSerializer {
    private SessionMetadataJsonSerializer() {
    }

    private static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    public static String serializeBeginSession(String str, String str2, long j9) {
        HashMap map = new HashMap();
        map.put("session_id", str);
        map.put("generator", str2);
        map.put("started_at_seconds", Long.valueOf(j9));
        return new JSONObject(map).toString();
    }

    public static String serializeSessionApp(String str, String str2, String str3, String str4, int i9, String str5, String str6) {
        HashMap map = new HashMap();
        map.put("app_identifier", str);
        map.put("version_code", str2);
        map.put("version_name", str3);
        map.put("install_uuid", str4);
        map.put("delivery_mechanism", Integer.valueOf(i9));
        map.put("development_platform", emptyIfNull(str5));
        map.put("development_platform_version", emptyIfNull(str6));
        return new JSONObject(map).toString();
    }

    public static String serializeSessionDevice(int i9, String str, int i10, long j9, long j10, boolean z8, int i11, String str2, String str3) {
        HashMap map = new HashMap();
        map.put("arch", Integer.valueOf(i9));
        map.put("build_model", str);
        map.put("available_processors", Integer.valueOf(i10));
        map.put("total_ram", Long.valueOf(j9));
        map.put("disk_space", Long.valueOf(j10));
        map.put("is_emulator", Boolean.valueOf(z8));
        map.put(RemoteConfigConstants.ResponseFieldKey.STATE, Integer.valueOf(i11));
        map.put("build_manufacturer", str2);
        map.put("build_product", str3);
        return new JSONObject(map).toString();
    }

    public static String serializeSessionOs(String str, String str2, boolean z8) {
        HashMap map = new HashMap();
        map.put("version", str);
        map.put("build_version", str2);
        map.put("is_rooted", Boolean.valueOf(z8));
        return new JSONObject(map).toString();
    }
}
