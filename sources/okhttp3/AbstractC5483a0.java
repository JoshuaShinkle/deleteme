package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.text.C5246c;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p204t6.C6322c;
import p204t6.InterfaceC6324e;
import p248y5.C6791a;

/* renamed from: okhttp3.a0 */
/* loaded from: classes2.dex */
public abstract class AbstractC5483a0 implements Closeable {

    /* renamed from: b */
    public static final a f18496b = new a(null);

    /* renamed from: okhttp3.a0$a */
    public static final class a {

        /* renamed from: okhttp3.a0$a$a, reason: collision with other inner class name */
        public static final class C6873a extends AbstractC5483a0 {

            /* renamed from: c */
            public final /* synthetic */ C5520u f18497c;

            /* renamed from: d */
            public final /* synthetic */ long f18498d;

            /* renamed from: e */
            public final /* synthetic */ InterfaceC6324e f18499e;

            public C6873a(C5520u c5520u, long j9, InterfaceC6324e interfaceC6324e) {
                this.f18497c = c5520u;
                this.f18498d = j9;
                this.f18499e = interfaceC6324e;
            }

            @Override // okhttp3.AbstractC5483a0
            /* renamed from: v */
            public long mo20968v() {
                return this.f18498d;
            }

            @Override // okhttp3.AbstractC5483a0
            /* renamed from: w */
            public C5520u mo20969w() {
                return this.f18497c;
            }

            @Override // okhttp3.AbstractC5483a0
            /* renamed from: x */
            public InterfaceC6324e mo20970x() {
                return this.f18499e;
            }
        }

        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: c */
        public static /* synthetic */ AbstractC5483a0 m21232c(a aVar, byte[] bArr, C5520u c5520u, int i9, Object obj) {
            if ((i9 & 1) != 0) {
                c5520u = null;
            }
            return aVar.m21234b(bArr, c5520u);
        }

        /* renamed from: a */
        public final AbstractC5483a0 m21233a(InterfaceC6324e interfaceC6324e, C5520u c5520u, long j9) {
            C0042f.m158e(interfaceC6324e, "<this>");
            return new C6873a(c5520u, j9, interfaceC6324e);
        }

        /* renamed from: b */
        public final AbstractC5483a0 m21234b(byte[] bArr, C5520u c5520u) {
            C0042f.m158e(bArr, "<this>");
            return m21233a(new C6322c().write(bArr), c5520u, bArr.length);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        C5057d.m19799m(mo20970x());
    }

    /* renamed from: f */
    public final InputStream m21229f() {
        return mo20970x().mo24216c();
    }

    /* renamed from: u */
    public final Charset m21230u() {
        Charset charsetM21710c;
        C5520u c5520uMo20969w = mo20969w();
        return (c5520uMo20969w == null || (charsetM21710c = c5520uMo20969w.m21710c(C5246c.f17846b)) == null) ? C5246c.f17846b : charsetM21710c;
    }

    /* renamed from: v */
    public abstract long mo20968v();

    /* renamed from: w */
    public abstract C5520u mo20969w();

    /* renamed from: x */
    public abstract InterfaceC6324e mo20970x();

    /* renamed from: y */
    public final String m21231y() {
        InterfaceC6324e interfaceC6324eMo20970x = mo20970x();
        try {
            String strMo24222k = interfaceC6324eMo20970x.mo24222k(C5057d.m19769I(interfaceC6324eMo20970x, m21230u()));
            C6791a.m25347a(interfaceC6324eMo20970x, null);
            return strMo24222k;
        } finally {
        }
    }
}
