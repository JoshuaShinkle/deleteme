package p015b4;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import p116k4.C5170o0;
import p201t3.C6288b;
import p201t3.C6301o;

/* renamed from: b4.b */
/* loaded from: classes.dex */
public class C0672b {
    /* renamed from: a */
    public static C6288b<String> m3379a(String str, C6288b.d<String> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o(Scopes.EMAIL, str));
        return new C6288b.c("user", "sendDouVerification", arrayList, String.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: b */
    public static C6288b<String> m3380b(String str, C6288b.d<String> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o(Scopes.EMAIL, str));
        return new C6288b.c("user", "forgotBindDouPassword", arrayList, String.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: c */
    public static C6288b<String> m3381c(String str, C6288b.d<String> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o(Scopes.EMAIL, str));
        return new C6288b.c("user", "forgotEmailPassword", arrayList, String.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: d */
    public static C6288b<C0684n> m3382d(String str, String str2, C6288b.d<C0684n> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("accountSource", str));
        arrayList.add(new C6301o("accountToken", str2));
        return new C6288b.c("user", "register", arrayList, C0684n.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: e */
    public static C6288b<String> m3383e(String str, String str2, C6288b.d<String> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("accountToken", str));
        arrayList.add(new C6301o("password", str2));
        return new C6288b.c("user", "resetEmailPassword", arrayList, String.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: f */
    public static C6288b<C0683m> m3384f(String str, String str2, C6288b.d<C0683m> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o(Scopes.EMAIL, str));
        arrayList.add(new C6301o("password", str2));
        return new C6288b.c("user", "signInDou", arrayList, C0683m.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: g */
    public static C6288b<C0683m> m3385g(String str, C6288b.d<C0683m> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("accessToken", str));
        return new C6288b.c("user", "ssoSignInDou", arrayList, C0683m.class).m24097b(dVar).m24099d(hVar).m24096a();
    }

    /* renamed from: h */
    public static C6288b<C0671a> m3386h(String str, String str2, String str3, boolean z8, String str4, String str5, String str6, C6288b.d<C0671a> dVar, C6288b.h hVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o(Scopes.EMAIL, str));
        arrayList.add(new C6301o("password", str2));
        arrayList.add(new C6301o("displayName", str3));
        arrayList.add(new C6301o("edm", String.valueOf(z8)));
        arrayList.add(new C6301o("locale", str4));
        if (!C5170o0.m20170e(str6)) {
            arrayList.add(new C6301o(TtmlNode.TAG_REGION, str6));
        }
        if (!C5170o0.m20170e(str5)) {
            arrayList.add(new C6301o("token", str5));
        }
        return new C6288b.c("user", "signUpDou", arrayList, C0671a.class).m24097b(dVar).m24099d(hVar).m24096a();
    }
}
