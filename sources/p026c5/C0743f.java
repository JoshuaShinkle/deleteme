package p026c5;

import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

/* renamed from: c5.f */
/* loaded from: classes2.dex */
public class C0743f {

    /* renamed from: a */
    public String f3481a = null;

    public C0743f(byte[] bArr) {
        C0740c c0740c = new C0740c(bArr);
        try {
            c0740c.m3601e();
            m3605a(c0740c);
        } catch (SaslException unused) {
        }
    }

    /* renamed from: a */
    public void m3605a(C0740c c0740c) throws SaslException {
        Iterator itM3598b = c0740c.m3598b();
        while (itM3598b.hasNext()) {
            C0742e c0742e = (C0742e) itM3598b.next();
            if (c0742e.m3603a().equals("rspauth")) {
                this.f3481a = c0742e.m3604b();
            }
        }
        if (this.f3481a == null) {
            throw new SaslException("Missing response-auth directive.");
        }
    }

    /* renamed from: b */
    public String m3606b() {
        return this.f3481a;
    }
}
