package p001a0;

import android.util.Base64;
import java.util.List;
import p021c0.C0702h;

/* renamed from: a0.a */
/* loaded from: classes.dex */
public final class C0003a {

    /* renamed from: a */
    public final String f1a;

    /* renamed from: b */
    public final String f2b;

    /* renamed from: c */
    public final String f3c;

    /* renamed from: d */
    public final List<List<byte[]>> f4d;

    /* renamed from: e */
    public final int f5e;

    /* renamed from: f */
    public final String f6f;

    public C0003a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) C0702h.m3468b(str);
        this.f1a = str4;
        String str5 = (String) C0702h.m3468b(str2);
        this.f2b = str5;
        String str6 = (String) C0702h.m3468b(str3);
        this.f3c = str6;
        this.f4d = (List) C0702h.m3468b(list);
        this.f5e = 0;
        this.f6f = str4 + "-" + str5 + "-" + str6;
    }

    /* renamed from: a */
    public List<List<byte[]>> m13a() {
        return this.f4d;
    }

    /* renamed from: b */
    public int m14b() {
        return this.f5e;
    }

    /* renamed from: c */
    public String m15c() {
        return this.f6f;
    }

    /* renamed from: d */
    public String m16d() {
        return this.f1a;
    }

    /* renamed from: e */
    public String m17e() {
        return this.f2b;
    }

    /* renamed from: f */
    public String m18f() {
        return this.f3c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f1a + ", mProviderPackage: " + this.f2b + ", mQuery: " + this.f3c + ", mCertificates:");
        for (int i9 = 0; i9 < this.f4d.size(); i9++) {
            sb.append(" [");
            List<byte[]> list = this.f4d.get(i9);
            for (int i10 = 0; i10 < list.size(); i10++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i10), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f5e);
        return sb.toString();
    }
}
