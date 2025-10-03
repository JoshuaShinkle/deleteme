package p139m6;

import com.google.firebase.analytics.FirebaseAnalytics;
import p007a6.C0042f;

/* renamed from: m6.f */
/* loaded from: classes.dex */
public final class C5333f {

    /* renamed from: a */
    public static final C5333f f18168a = new C5333f();

    /* renamed from: a */
    public static final boolean m20953a(String str) {
        C0042f.m158e(str, FirebaseAnalytics.Param.METHOD);
        return (C0042f.m154a(str, "GET") || C0042f.m154a(str, "HEAD")) ? false : true;
    }

    /* renamed from: d */
    public static final boolean m20954d(String str) {
        C0042f.m158e(str, FirebaseAnalytics.Param.METHOD);
        return C0042f.m154a(str, "POST") || C0042f.m154a(str, "PUT") || C0042f.m154a(str, "PATCH") || C0042f.m154a(str, "PROPPATCH") || C0042f.m154a(str, "REPORT");
    }

    /* renamed from: b */
    public final boolean m20955b(String str) {
        C0042f.m158e(str, FirebaseAnalytics.Param.METHOD);
        return !C0042f.m154a(str, "PROPFIND");
    }

    /* renamed from: c */
    public final boolean m20956c(String str) {
        C0042f.m158e(str, FirebaseAnalytics.Param.METHOD);
        return C0042f.m154a(str, "PROPFIND");
    }
}
