package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzcz {
    private static final char[] zzact = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static double zza(String str, double d9) {
        if (str == null) {
            return 100.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return 100.0d;
        }
    }

    public static Map<String, String> zzaf(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=", 3);
            if (strArrSplit.length > 1) {
                map.put(strArrSplit[0], TextUtils.isEmpty(strArrSplit[1]) ? null : strArrSplit[1]);
                if (strArrSplit.length == 3 && !TextUtils.isEmpty(strArrSplit[1]) && !map.containsKey(strArrSplit[1])) {
                    map.put(strArrSplit[1], TextUtils.isEmpty(strArrSplit[2]) ? null : strArrSplit[2]);
                }
            } else if (strArrSplit.length == 1 && strArrSplit[0].length() != 0) {
                map.put(strArrSplit[0], null);
            }
        }
        return map;
    }

    public static long zzag(String str) {
        if (str == null) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public static String zzah(String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains("?")) {
            String[] strArrSplit = str.split("[\\?]");
            if (strArrSplit.length > 1) {
                str = strArrSplit[1];
            }
        }
        if (str.contains("%3D")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return null;
            }
        } else if (!str.contains("=")) {
            return null;
        }
        Map<String, String> mapZzaf = zzaf(str);
        String[] strArr = {"dclid", "utm_source", "gclid", FirebaseAnalytics.Param.ACLID, "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "anid", "gmob_t"};
        StringBuilder sb = new StringBuilder();
        for (int i9 = 0; i9 < 11; i9++) {
            if (!TextUtils.isEmpty(mapZzaf.get(strArr[i9]))) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(strArr[i9]);
                sb.append("=");
                sb.append(mapZzaf.get(strArr[i9]));
            }
        }
        return sb.toString();
    }

    public static MessageDigest zzai(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        for (int i9 = 0; i9 < 2; i9++) {
            try {
                messageDigest = MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException unused) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
        }
        return null;
    }

    public static boolean zzaj(String str) {
        return TextUtils.isEmpty(str) || !str.startsWith("http:");
    }

    public static boolean zzb(String str, boolean z8) {
        return str == null || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("1") || !(str.equalsIgnoreCase("false") || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("0"));
    }

    public static String zzc(boolean z8) {
        return z8 ? "1" : "0";
    }

    public static void zzc(Map<String, String> map, String str, String str2) {
        if (str2 == null || !TextUtils.isEmpty(map.get(str))) {
            return;
        }
        map.put(str, str2);
    }

    public static zzr zza(zzci zzciVar, String str) {
        Preconditions.checkNotNull(zzciVar);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new HashMap();
        try {
            String strValueOf = String.valueOf(str);
            Map<String, String> map = HttpUtils.parse(new URI(strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?")), "UTF-8");
            zzr zzrVar = new zzr();
            zzrVar.zzf(map.get("utm_content"));
            zzrVar.zzd(map.get("utm_medium"));
            zzrVar.setName(map.get("utm_campaign"));
            zzrVar.zzc(map.get("utm_source"));
            zzrVar.zze(map.get("utm_term"));
            zzrVar.zzg(map.get("utm_id"));
            zzrVar.zzh(map.get("anid"));
            zzrVar.zzi(map.get("gclid"));
            zzrVar.zzj(map.get("dclid"));
            zzrVar.zzk(map.get(FirebaseAnalytics.Param.ACLID));
            return zzrVar;
        } catch (URISyntaxException e9) {
            zzciVar.zzd("No valid campaign data found", e9);
            return null;
        }
    }

    public static boolean zzc(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, str), 0);
            if (serviceInfo != null) {
                if (serviceInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static void zzb(Map<String, String> map, String str, String str2) {
        if (str2 == null || map.containsKey(str)) {
            return;
        }
        map.put(str, str2);
    }

    public static void zzb(Map<String, String> map, String str, boolean z8) {
        if (map.containsKey(str)) {
            return;
        }
        map.put(str, z8 ? "1" : "0");
    }

    public static String zza(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language.toLowerCase(locale));
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-");
            sb.append(locale.getCountry().toLowerCase(locale));
        }
        return sb.toString();
    }

    public static void zza(Map<String, String> map, String str, Map<String, String> map2) {
        zzb(map, str, map2.get(str));
    }

    public static boolean zza(double d9, String str) {
        int i9;
        if (d9 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && d9 < 100.0d) {
            if (TextUtils.isEmpty(str)) {
                i9 = 1;
            } else {
                i9 = 0;
                for (int length = str.length() - 1; length >= 0; length--) {
                    char cCharAt = str.charAt(length);
                    i9 = ((i9 << 6) & 268435455) + cCharAt + (cCharAt << 14);
                    int i10 = 266338304 & i9;
                    if (i10 != 0) {
                        i9 ^= i10 >> 21;
                    }
                }
            }
            if (i9 % 10000 >= d9 * 100.0d) {
                return true;
            }
        }
        return false;
    }

    public static boolean zza(Context context, String str, boolean z8) throws PackageManager.NameNotFoundException {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 0);
            if (receiverInfo != null && receiverInfo.enabled) {
                if (!z8) {
                    return true;
                }
                if (receiverInfo.exported) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }
}
