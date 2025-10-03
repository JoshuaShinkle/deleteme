package com.google.zxing.client.android.wifi;

/* loaded from: classes2.dex */
enum NetworkType {
    WEP,
    WPA,
    NO_PASSWORD;

    public static NetworkType forIntentValue(String str) {
        if (str == null) {
            return NO_PASSWORD;
        }
        if ("WPA".equals(str)) {
            return WPA;
        }
        if ("WEP".equals(str)) {
            return WEP;
        }
        if ("nopass".equals(str)) {
            return NO_PASSWORD;
        }
        throw new IllegalArgumentException(str);
    }
}
