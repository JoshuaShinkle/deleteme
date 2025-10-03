package p073g1;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;
import p226w1.C6516i;
import p243y0.InterfaceC6589b;

/* renamed from: g1.g */
/* loaded from: classes.dex */
public class C4813g implements InterfaceC6589b {

    /* renamed from: b */
    public final InterfaceC4814h f16748b;

    /* renamed from: c */
    public final URL f16749c;

    /* renamed from: d */
    public final String f16750d;

    /* renamed from: e */
    public String f16751e;

    /* renamed from: f */
    public URL f16752f;

    /* renamed from: g */
    public volatile byte[] f16753g;

    /* renamed from: h */
    public int f16754h;

    public C4813g(URL url) {
        this(url, InterfaceC4814h.f16756b);
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        messageDigest.update(m19105d());
    }

    /* renamed from: c */
    public String m19104c() {
        String str = this.f16750d;
        return str != null ? str : ((URL) C6516i.m24938d(this.f16749c)).toString();
    }

    /* renamed from: d */
    public final byte[] m19105d() {
        if (this.f16753g == null) {
            this.f16753g = m19104c().getBytes(InterfaceC6589b.f22139a);
        }
        return this.f16753g;
    }

    /* renamed from: e */
    public Map<String, String> m19106e() {
        return this.f16748b.mo19111a();
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (!(obj instanceof C4813g)) {
            return false;
        }
        C4813g c4813g = (C4813g) obj;
        return m19104c().equals(c4813g.m19104c()) && this.f16748b.equals(c4813g.f16748b);
    }

    /* renamed from: f */
    public final String m19107f() {
        if (TextUtils.isEmpty(this.f16751e)) {
            String string = this.f16750d;
            if (TextUtils.isEmpty(string)) {
                string = ((URL) C6516i.m24938d(this.f16749c)).toString();
            }
            this.f16751e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.f16751e;
    }

    /* renamed from: g */
    public final URL m19108g() {
        if (this.f16752f == null) {
            this.f16752f = new URL(m19107f());
        }
        return this.f16752f;
    }

    /* renamed from: h */
    public String m19109h() {
        return m19107f();
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        if (this.f16754h == 0) {
            int iHashCode = m19104c().hashCode();
            this.f16754h = iHashCode;
            this.f16754h = (iHashCode * 31) + this.f16748b.hashCode();
        }
        return this.f16754h;
    }

    /* renamed from: i */
    public URL m19110i() {
        return m19108g();
    }

    public String toString() {
        return m19104c();
    }

    public C4813g(String str) {
        this(str, InterfaceC4814h.f16756b);
    }

    public C4813g(URL url, InterfaceC4814h interfaceC4814h) {
        this.f16749c = (URL) C6516i.m24938d(url);
        this.f16750d = null;
        this.f16748b = (InterfaceC4814h) C6516i.m24938d(interfaceC4814h);
    }

    public C4813g(String str, InterfaceC4814h interfaceC4814h) {
        this.f16749c = null;
        this.f16750d = C6516i.m24936b(str);
        this.f16748b = (InterfaceC4814h) C6516i.m24938d(interfaceC4814h);
    }
}
