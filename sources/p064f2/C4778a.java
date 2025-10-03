package p064f2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import p125l2.C5278b;

/* renamed from: f2.a */
/* loaded from: classes.dex */
public final class C4778a {

    /* renamed from: a */
    public static final Set<String> f16623a = Collections.unmodifiableSet(new HashSet(Arrays.asList("application/x-dtcp1")));

    /* renamed from: b */
    public static final Set<String> f16624b = Collections.unmodifiableSet(new HashSet(Arrays.asList("video/vnd.dlna.mpeg-tts")));

    /* renamed from: c */
    public static final Set<String> f16625c = Collections.unmodifiableSet(new HashSet(Arrays.asList("DLNA.ORG_PN", "SONY.COM_PN")));

    /* renamed from: d */
    public static final Set<String> f16626d = Collections.unmodifiableSet(new HashSet(Arrays.asList("DTCP_MPEG_TS_JP_T", "DTCP_AVC_TS_SD_30_JP_AAC_T")));

    /* renamed from: e */
    public static final Set<String> f16627e = Collections.emptySet();

    /* renamed from: a */
    public static boolean m19009a(String str, Map<String, String> map) {
        if (map == null || !map.containsKey("CL-DTCP-Port") || Integer.valueOf(map.get("CL-DTCP-Port")).intValue() <= 0) {
            return str != null && C5278b.m20554f(str) && "dtcp".equals(C5278b.m20551c(str).toLowerCase(Locale.US));
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m19010b(String str, boolean z8) {
        return (z8 ? f16623a : f16624b).contains(str);
    }

    /* renamed from: c */
    public static boolean m19011c(String str, boolean z8) {
        if (str != null && !str.isEmpty()) {
            if (z8) {
                if (str.startsWith("DTCP_AVC")) {
                    return true;
                }
            } else if (str.startsWith("AVC_TS")) {
                return true;
            }
        }
        return false;
    }
}
