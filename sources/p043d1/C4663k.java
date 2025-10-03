package p043d1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import p021c0.InterfaceC0699e;
import p226w1.C6513f;
import p226w1.C6516i;
import p226w1.C6517j;
import p235x1.AbstractC6565c;
import p235x1.C6563a;
import p243y0.InterfaceC6589b;

/* renamed from: d1.k */
/* loaded from: classes.dex */
public class C4663k {

    /* renamed from: a */
    public final C6513f<InterfaceC6589b, String> f16322a = new C6513f<>(1000);

    /* renamed from: b */
    public final InterfaceC0699e<b> f16323b = C6563a.m25128e(10, new a());

    /* renamed from: d1.k$a */
    public class a implements C6563a.d<b> {
        public a() {
        }

        @Override // p235x1.C6563a.d
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public b mo3287a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e9) {
                throw new RuntimeException(e9);
            }
        }
    }

    /* renamed from: d1.k$b */
    public static final class b implements C6563a.f {

        /* renamed from: b */
        public final MessageDigest f16325b;

        /* renamed from: c */
        public final AbstractC6565c f16326c = AbstractC6565c.m25138a();

        public b(MessageDigest messageDigest) {
            this.f16325b = messageDigest;
        }

        @Override // p235x1.C6563a.f
        /* renamed from: j */
        public AbstractC6565c mo3286j() {
            return this.f16326c;
        }
    }

    /* renamed from: a */
    public final String m18628a(InterfaceC6589b interfaceC6589b) {
        b bVar = (b) C6516i.m24938d(this.f16323b.mo3465b());
        try {
            interfaceC6589b.mo3265a(bVar.f16325b);
            return C6517j.m24960u(bVar.f16325b.digest());
        } finally {
            this.f16323b.mo3464a(bVar);
        }
    }

    /* renamed from: b */
    public String m18629b(InterfaceC6589b interfaceC6589b) {
        String strM24926g;
        synchronized (this.f16322a) {
            strM24926g = this.f16322a.m24926g(interfaceC6589b);
        }
        if (strM24926g == null) {
            strM24926g = m18628a(interfaceC6589b);
        }
        synchronized (this.f16322a) {
            this.f16322a.m24928k(interfaceC6589b, strM24926g);
        }
        return strM24926g;
    }
}
