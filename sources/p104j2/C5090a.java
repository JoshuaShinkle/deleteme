package p104j2;

import com.google.android.exoplayer2.util.MimeTypes;

/* renamed from: j2.a */
/* loaded from: classes.dex */
public final class C5090a {
    /* renamed from: a */
    public static String m19923a(String str) {
        return "audio/x-ms-ac3".equals(str) ? MimeTypes.AUDIO_AC3 : MimeTypes.AUDIO_DTS_HD.equals(str) ? "audio/dts" : str;
    }
}
