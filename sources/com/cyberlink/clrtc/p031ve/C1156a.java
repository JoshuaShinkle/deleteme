package com.cyberlink.clrtc.p031ve;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.Scopes;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

/* renamed from: com.cyberlink.clrtc.ve.a */
/* loaded from: classes.dex */
public class C1156a {

    /* renamed from: a */
    public static int f5680a = 2;

    /* renamed from: b */
    public static int f5681b = 0;

    /* renamed from: c */
    public static int f5682c = 0;

    /* renamed from: d */
    public static int f5683d = 2;

    /* renamed from: e */
    public static int f5684e = 0;

    /* renamed from: f */
    public static boolean f5685f = false;

    /* renamed from: g */
    public static final String[] f5686g = {"OMX.qcom."};

    /* renamed from: h */
    public static final String[] f5687h = {"OMX.Exynos.", "OMX.MTK."};

    /* renamed from: i */
    public static final String[] f5688i = {"OMX.Nvidia."};

    /* renamed from: j */
    public static final Pattern f5689j = Pattern.compile("mt[0-9]{4}");

    static {
        m5187a();
    }

    /* renamed from: a */
    public static void m5187a() {
        HashSet hashSet = new HashSet();
        m5189c(hashSet);
        Iterator it = hashSet.iterator();
        boolean z8 = false;
        while (it.hasNext()) {
            String str = (String) it.next();
            for (String str2 : f5687h) {
                if (str.startsWith(str2)) {
                    z8 = true;
                }
            }
            for (String str3 : f5688i) {
                if (str.startsWith(str3)) {
                    f5685f = true;
                }
            }
        }
        if ("samsung".equalsIgnoreCase(Build.BRAND) && "pxa1908".equalsIgnoreCase(Build.HARDWARE) && "grandprimevelte".equalsIgnoreCase(Build.DEVICE)) {
            f5685f = true;
        }
        if (!z8 || m5190d()) {
            return;
        }
        f5681b = 8;
        f5682c = 512;
    }

    /* renamed from: b */
    public static void m5188b(HashSet<String> hashSet, MediaCodecInfo mediaCodecInfo, boolean z8) {
        if (mediaCodecInfo.isEncoder() != z8) {
            return;
        }
        String name = mediaCodecInfo.getName();
        if (name.toLowerCase().contains(".google.h264.") || name.toLowerCase().contains(".soft.h264.")) {
            return;
        }
        for (String str : mediaCodecInfo.getSupportedTypes()) {
            if (str.equalsIgnoreCase(MimeTypes.VIDEO_H264)) {
                hashSet.add(name);
                MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
                capabilitiesForType.getMimeType();
                if (z8) {
                    capabilitiesForType.getEncoderCapabilities();
                }
                capabilitiesForType.getVideoCapabilities();
            }
        }
    }

    /* renamed from: c */
    public static void m5189c(HashSet<String> hashSet) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i9 = 0; i9 < codecCount; i9++) {
            m5188b(hashSet, MediaCodecList.getCodecInfoAt(i9), false);
        }
        for (int i10 = 0; i10 < codecCount; i10++) {
            m5188b(hashSet, MediaCodecList.getCodecInfoAt(i10), true);
        }
    }

    /* renamed from: d */
    public static boolean m5190d() {
        return false;
    }

    /* renamed from: e */
    public static int m5191e() {
        return f5682c;
    }

    /* renamed from: f */
    public static int m5192f() {
        return f5681b;
    }

    /* renamed from: g */
    public static boolean m5193g() {
        return f5685f;
    }

    /* renamed from: h */
    public static boolean m5194h() {
        return m5195i() && f5681b > 0 && f5682c > 0;
    }

    /* renamed from: i */
    public static boolean m5195i() {
        return true;
    }

    /* renamed from: j */
    public static boolean m5196j() {
        return f5689j.matcher(Build.HARDWARE).matches();
    }

    /* renamed from: k */
    public static boolean m5197k() {
        return true;
    }

    /* renamed from: l */
    public static boolean m5198l(boolean z8) {
        String str = Build.HARDWARE;
        if ("mt6735".equalsIgnoreCase(str)) {
            return true;
        }
        String str2 = Build.BRAND;
        if ("Sony".equalsIgnoreCase(str2) && "mt6752".equalsIgnoreCase(str) && "E5553".equalsIgnoreCase(Build.DEVICE)) {
            return true;
        }
        if ("Sony".equalsIgnoreCase(str2) && "MSM8960".equalsIgnoreCase(Build.BOARD) && "C6502".equalsIgnoreCase(Build.DEVICE)) {
            return false;
        }
        return !z8;
    }

    /* renamed from: m */
    public static MediaFormat m5199m(MediaFormat mediaFormat) {
        int i9;
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(mediaFormat.getString("mime"), mediaFormat.getInteger("width"), mediaFormat.getInteger("height"));
        mediaFormatCreateVideoFormat.setInteger("bitrate", mediaFormat.getInteger("bitrate"));
        mediaFormatCreateVideoFormat.setInteger("color-format", mediaFormat.getInteger("color-format"));
        mediaFormatCreateVideoFormat.setInteger("frame-rate", mediaFormat.getInteger("frame-rate"));
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", mediaFormat.getInteger("i-frame-interval"));
        int i10 = f5680a;
        if (i10 > 0) {
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", i10);
        }
        if (m5195i()) {
            int i11 = f5681b;
            if (i11 > 0) {
                mediaFormatCreateVideoFormat.setInteger(Scopes.PROFILE, i11);
            }
            if (m5197k() && (i9 = f5682c) > 0) {
                mediaFormatCreateVideoFormat.setInteger(FirebaseAnalytics.Param.LEVEL, i9);
            }
            mediaFormatCreateVideoFormat.setInteger("bitrate-mode", f5683d);
            int i12 = f5684e;
            if (i12 > 0) {
                mediaFormatCreateVideoFormat.setInteger("complexity", i12);
            }
        }
        return mediaFormatCreateVideoFormat;
    }

    /* renamed from: n */
    public static void m5200n(int i9) {
        f5682c = i9;
    }

    /* renamed from: o */
    public static void m5201o(int i9) {
        f5681b = i9;
    }
}
