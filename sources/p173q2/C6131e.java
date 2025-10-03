package p173q2;

import java.io.File;
import java.security.MessageDigest;
import p209u2.C6383t;
import p243y0.InterfaceC6589b;

/* renamed from: q2.e */
/* loaded from: classes.dex */
public class C6131e implements InterfaceC6589b {

    /* renamed from: b */
    public final String f20759b;

    /* renamed from: c */
    public final long f20760c;

    /* renamed from: d */
    public final long f20761d;

    public C6131e(File file) {
        this.f20759b = file.getAbsolutePath();
        this.f20760c = file.length();
        this.f20761d = file.lastModified();
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        messageDigest.update(m23490c());
    }

    /* renamed from: c */
    public final byte[] m23490c() {
        return C6383t.m24515d("MD5", this.f20759b + String.valueOf(this.f20760c) + String.valueOf(this.f20761d));
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C6131e c6131e = (C6131e) obj;
        if (this.f20760c != c6131e.f20760c || this.f20761d != c6131e.f20761d) {
            return false;
        }
        String str = this.f20759b;
        String str2 = c6131e.f20759b;
        return str != null ? str.equals(str2) : str2 == null;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        String str = this.f20759b;
        int iHashCode = str != null ? str.hashCode() : 0;
        long j9 = this.f20760c;
        int i9 = ((iHashCode * 31) + ((int) (j9 ^ (j9 >>> 32)))) * 31;
        long j10 = this.f20761d;
        return i9 + ((int) (j10 ^ (j10 >>> 32)));
    }
}
