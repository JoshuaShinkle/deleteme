package p104j2;

import com.cyberlink.media.Config;
import com.google.android.exoplayer2.util.MimeTypes;

/* renamed from: j2.d */
/* loaded from: classes.dex */
public final class C5093d {
    /* renamed from: a */
    public static boolean m19930a(String str, boolean z8) {
        try {
            boolean z9 = Config.HAVE_AC3;
            return Config.class.getDeclaredField(str).getBoolean(null);
        } catch (Throwable unused) {
            return z8;
        }
    }

    /* renamed from: b */
    public static final boolean m19931b() {
        return m19930a("HAVE_AC3", false);
    }

    /* renamed from: c */
    public static final boolean m19932c() {
        return m19930a("HAVE_AC3_INAPP_PURCHASE", false);
    }

    /* renamed from: d */
    public static final boolean m19933d() {
        return m19930a("HAVE_ALAC", false);
    }

    /* renamed from: e */
    public static final boolean m19934e() {
        return m19930a("HAVE_AVC", false);
    }

    /* renamed from: f */
    public static final boolean m19935f() {
        return m19930a("HAVE_DTS", false);
    }

    /* renamed from: g */
    public static final boolean m19936g() {
        return m19930a("HAVE_DTS_INAPP_PURCHASE", true);
    }

    /* renamed from: h */
    public static boolean m19937h(String str) {
        return (m19931b() && MimeTypes.AUDIO_AC3.equals(str)) || (m19938i() && MimeTypes.AUDIO_E_AC3.equals(str)) || ((m19935f() && "audio/dts".equals(str)) || ((m19941l() && MimeTypes.AUDIO_MPEG_L2.equals(str)) || ((m19943n() && MimeTypes.VIDEO_MPEG2.equals(str)) || ((m19944o() && MimeTypes.VIDEO_MP4V.equals(str)) || ((m19934e() && MimeTypes.VIDEO_H264.equals(str)) || ((m19942m() && MimeTypes.AUDIO_MPEG.equals(str)) || ((m19933d() && MimeTypes.AUDIO_ALAC.equals(str)) || ((m19945p() && "audio/x-monkeys-audio".equals(str)) || (m19946q() && ("video/rv10".equals(str) || "video/rv20".equals(str) || "video/rv30".equals(str) || "video/rv40".equals(str) || "audio/ra-144".equals(str) || "audio/ra-288".equals(str) || "audio/cook".equals(str) || "audio/ralf".equals(str)))))))))));
    }

    /* renamed from: i */
    public static final boolean m19938i() {
        return m19930a("HAVE_EAC3", false);
    }

    /* renamed from: j */
    public static final boolean m19939j() {
        return m19930a("HAVE_EAC3_INAPP_PURCHASE", false);
    }

    /* renamed from: k */
    public static boolean m19940k(String str) {
        return (m19932c() && MimeTypes.AUDIO_AC3.equals(str)) || (m19939j() && MimeTypes.AUDIO_E_AC3.equals(str)) || (m19936g() && "audio/dts".equals(str));
    }

    /* renamed from: l */
    public static final boolean m19941l() {
        return m19930a("HAVE_MPEG_AUDIO_LAYER_2", false);
    }

    /* renamed from: m */
    public static final boolean m19942m() {
        return m19930a("HAVE_MP3", false);
    }

    /* renamed from: n */
    public static final boolean m19943n() {
        return m19930a("HAVE_MPEG2_VIDEO", false);
    }

    /* renamed from: o */
    public static final boolean m19944o() {
        return m19930a("HAVE_MPEG4_VIDEO", false);
    }

    /* renamed from: p */
    public static final boolean m19945p() {
        return m19930a("HAVE_MONKEYS", false);
    }

    /* renamed from: q */
    public static final boolean m19946q() {
        return m19930a("HAVE_REAL_CODEC", false);
    }
}
