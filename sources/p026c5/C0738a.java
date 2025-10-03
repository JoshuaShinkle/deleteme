package p026c5;

import com.google.android.exoplayer2.C3322C;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

/* renamed from: c5.a */
/* loaded from: classes2.dex */
public class C0738a {

    /* renamed from: a */
    public ArrayList f3445a = new ArrayList(5);

    /* renamed from: b */
    public String f3446b = null;

    /* renamed from: c */
    public int f3447c = 0;

    /* renamed from: d */
    public boolean f3448d = false;

    /* renamed from: e */
    public int f3449e = -1;

    /* renamed from: f */
    public String f3450f = null;

    /* renamed from: g */
    public String f3451g = null;

    /* renamed from: h */
    public int f3452h = 0;

    public C0738a(byte[] bArr) throws NumberFormatException {
        C0740c c0740c = new C0740c(bArr);
        try {
            c0740c.m3601e();
            m3576a(c0740c);
        } catch (SaslException unused) {
        }
    }

    /* renamed from: a */
    public void m3576a(C0740c c0740c) throws SaslException, NumberFormatException {
        Iterator itM3598b = c0740c.m3598b();
        while (itM3598b.hasNext()) {
            C0742e c0742e = (C0742e) itM3598b.next();
            String strM3603a = c0742e.m3603a();
            if (strM3603a.equals("realm")) {
                m3587l(c0742e);
            } else if (strM3603a.equals("nonce")) {
                m3585j(c0742e);
            } else if (strM3603a.equals("qop")) {
                m3586k(c0742e);
            } else if (strM3603a.equals("maxbuf")) {
                m3584i(c0742e);
            } else if (strM3603a.equals("charset")) {
                m3582g(c0742e);
            } else if (strM3603a.equals("algorithm")) {
                m3581f(c0742e);
            } else if (strM3603a.equals("cipher")) {
                m3583h(c0742e);
            } else if (strM3603a.equals("stale")) {
                m3588m(c0742e);
            }
        }
        if (-1 == this.f3449e) {
            this.f3449e = C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;
        }
        int i9 = this.f3447c;
        if (i9 == 0) {
            this.f3447c = 1;
            return;
        }
        if ((i9 & 1) != 1) {
            throw new SaslException("Only qop-auth is supported by client");
        }
        if ((i9 & 4) == 4 && (this.f3452h & 31) == 0) {
            throw new SaslException("Invalid cipher options");
        }
        if (this.f3446b == null) {
            throw new SaslException("Missing nonce directive");
        }
        if (this.f3448d) {
            throw new SaslException("Unexpected stale flag");
        }
        if (this.f3451g == null) {
            throw new SaslException("Missing algorithm directive");
        }
    }

    /* renamed from: b */
    public String m3577b() {
        return this.f3451g;
    }

    /* renamed from: c */
    public String m3578c() {
        return this.f3446b;
    }

    /* renamed from: d */
    public int m3579d() {
        return this.f3447c;
    }

    /* renamed from: e */
    public ArrayList m3580e() {
        return this.f3445a;
    }

    /* renamed from: f */
    public void m3581f(C0742e c0742e) throws SaslException {
        if (this.f3451g != null) {
            throw new SaslException("Too many algorithm directives.");
        }
        String strM3604b = c0742e.m3604b();
        this.f3451g = strM3604b;
        if ("md5-sess".equals(strM3604b)) {
            return;
        }
        throw new SaslException("Invalid algorithm directive value: " + this.f3451g);
    }

    /* renamed from: g */
    public void m3582g(C0742e c0742e) throws SaslException {
        if (this.f3450f != null) {
            throw new SaslException("Too many charset directives.");
        }
        String strM3604b = c0742e.m3604b();
        this.f3450f = strM3604b;
        if (!strM3604b.equals("utf-8")) {
            throw new SaslException("Invalid character encoding directive");
        }
    }

    /* renamed from: h */
    public void m3583h(C0742e c0742e) throws SaslException {
        if (this.f3452h != 0) {
            throw new SaslException("Too many cipher directives.");
        }
        C0744g c0744g = new C0744g(c0742e.m3604b());
        c0744g.m3609c();
        for (String strM3609c = c0744g.m3609c(); strM3609c != null; strM3609c = c0744g.m3609c()) {
            if ("3des".equals(strM3609c)) {
                this.f3452h |= 1;
            } else if ("des".equals(strM3609c)) {
                this.f3452h |= 2;
            } else if ("rc4-40".equals(strM3609c)) {
                this.f3452h |= 4;
            } else if ("rc4".equals(strM3609c)) {
                this.f3452h |= 8;
            } else if ("rc4-56".equals(strM3609c)) {
                this.f3452h |= 16;
            } else {
                this.f3452h |= 32;
            }
        }
        if (this.f3452h == 0) {
            this.f3452h = 32;
        }
    }

    /* renamed from: i */
    public void m3584i(C0742e c0742e) throws SaslException, NumberFormatException {
        if (-1 != this.f3449e) {
            throw new SaslException("Too many maxBuf directives.");
        }
        int i9 = Integer.parseInt(c0742e.m3604b());
        this.f3449e = i9;
        if (i9 == 0) {
            throw new SaslException("Max buf value must be greater than zero.");
        }
    }

    /* renamed from: j */
    public void m3585j(C0742e c0742e) throws SaslException {
        if (this.f3446b != null) {
            throw new SaslException("Too many nonce values.");
        }
        this.f3446b = c0742e.m3604b();
    }

    /* renamed from: k */
    public void m3586k(C0742e c0742e) throws SaslException {
        if (this.f3447c != 0) {
            throw new SaslException("Too many qop directives.");
        }
        C0744g c0744g = new C0744g(c0742e.m3604b());
        for (String strM3609c = c0744g.m3609c(); strM3609c != null; strM3609c = c0744g.m3609c()) {
            if (strM3609c.equals("auth")) {
                this.f3447c |= 1;
            } else if (strM3609c.equals("auth-int")) {
                this.f3447c |= 2;
            } else if (strM3609c.equals("auth-conf")) {
                this.f3447c |= 4;
            } else {
                this.f3447c |= 8;
            }
        }
    }

    /* renamed from: l */
    public void m3587l(C0742e c0742e) {
        this.f3445a.add(c0742e.m3604b());
    }

    /* renamed from: m */
    public void m3588m(C0742e c0742e) throws SaslException {
        if (this.f3448d) {
            throw new SaslException("Too many stale directives.");
        }
        if ("true".equals(c0742e.m3604b())) {
            this.f3448d = true;
            return;
        }
        throw new SaslException("Invalid stale directive value: " + c0742e.m3604b());
    }
}
