package p002a1;

import android.net.Uri;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: a1.b */
/* loaded from: classes.dex */
public final class C0007b {
    /* renamed from: a */
    public static boolean m48a(Uri uri) {
        return m49b(uri) && !m52e(uri);
    }

    /* renamed from: b */
    public static boolean m49b(Uri uri) {
        return uri != null && FirebaseAnalytics.Param.CONTENT.equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* renamed from: c */
    public static boolean m50c(Uri uri) {
        return m49b(uri) && m52e(uri);
    }

    /* renamed from: d */
    public static boolean m51d(int i9, int i10) {
        return i9 != Integer.MIN_VALUE && i10 != Integer.MIN_VALUE && i9 <= 512 && i10 <= 384;
    }

    /* renamed from: e */
    public static boolean m52e(Uri uri) {
        return uri.getPathSegments().contains(MimeTypes.BASE_TYPE_VIDEO);
    }
}
