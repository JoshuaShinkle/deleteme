package okhttp3;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.base.Ascii;
import com.google.common.net.HttpHeaders;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.AbstractC5524y;
import okhttp3.C5517r;
import okhttp3.C5520u;
import okio.ByteString;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p204t6.C6322c;
import p204t6.InterfaceC6323d;

/* renamed from: okhttp3.v */
/* loaded from: classes2.dex */
public final class C5521v extends AbstractC5524y {

    /* renamed from: g */
    public static final b f18969g = new b(null);

    /* renamed from: h */
    public static final C5520u f18970h;

    /* renamed from: i */
    public static final C5520u f18971i;

    /* renamed from: j */
    public static final C5520u f18972j;

    /* renamed from: k */
    public static final C5520u f18973k;

    /* renamed from: l */
    public static final C5520u f18974l;

    /* renamed from: m */
    public static final byte[] f18975m;

    /* renamed from: n */
    public static final byte[] f18976n;

    /* renamed from: o */
    public static final byte[] f18977o;

    /* renamed from: b */
    public final ByteString f18978b;

    /* renamed from: c */
    public final C5520u f18979c;

    /* renamed from: d */
    public final List<c> f18980d;

    /* renamed from: e */
    public final C5520u f18981e;

    /* renamed from: f */
    public long f18982f;

    /* renamed from: okhttp3.v$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final void m21725a(StringBuilder sb, String str) {
            C0042f.m158e(sb, "<this>");
            C0042f.m158e(str, "key");
            sb.append('\"');
            int length = str.length();
            for (int i9 = 0; i9 < length; i9++) {
                char cCharAt = str.charAt(i9);
                if (cCharAt == '\n') {
                    sb.append("%0A");
                } else if (cCharAt == '\r') {
                    sb.append("%0D");
                } else if (cCharAt == '\"') {
                    sb.append("%22");
                } else {
                    sb.append(cCharAt);
                }
            }
            sb.append('\"');
        }
    }

    /* renamed from: okhttp3.v$c */
    public static final class c {

        /* renamed from: c */
        public static final a f18986c = new a(null);

        /* renamed from: a */
        public final C5517r f18987a;

        /* renamed from: b */
        public final AbstractC5524y f18988b;

        /* renamed from: okhttp3.v$c$a */
        public static final class a {
            public a() {
            }

            public /* synthetic */ a(C0040d c0040d) {
                this();
            }

            /* renamed from: a */
            public final c m21728a(C5517r c5517r, AbstractC5524y abstractC5524y) {
                C0042f.m158e(abstractC5524y, TtmlNode.TAG_BODY);
                C0040d c0040d = null;
                if (!((c5517r != null ? c5517r.m21626a(HttpHeaders.CONTENT_TYPE) : null) == null)) {
                    throw new IllegalArgumentException("Unexpected header: Content-Type".toString());
                }
                if ((c5517r != null ? c5517r.m21626a(HttpHeaders.CONTENT_LENGTH) : null) == null) {
                    return new c(c5517r, abstractC5524y, c0040d);
                }
                throw new IllegalArgumentException("Unexpected header: Content-Length".toString());
            }

            /* renamed from: b */
            public final c m21729b(String str, String str2) {
                C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
                C0042f.m158e(str2, "value");
                return m21730c(str, null, AbstractC5524y.a.m21827h(AbstractC5524y.f19061a, str2, null, 1, null));
            }

            /* renamed from: c */
            public final c m21730c(String str, String str2, AbstractC5524y abstractC5524y) {
                C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
                C0042f.m158e(abstractC5524y, TtmlNode.TAG_BODY);
                StringBuilder sb = new StringBuilder();
                sb.append("form-data; name=");
                b bVar = C5521v.f18969g;
                bVar.m21725a(sb, str);
                if (str2 != null) {
                    sb.append("; filename=");
                    bVar.m21725a(sb, str2);
                }
                String string = sb.toString();
                C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
                return m21728a(new C5517r.a().m21634d(HttpHeaders.CONTENT_DISPOSITION, string).m21635e(), abstractC5524y);
            }
        }

        public c(C5517r c5517r, AbstractC5524y abstractC5524y) {
            this.f18987a = c5517r;
            this.f18988b = abstractC5524y;
        }

        public /* synthetic */ c(C5517r c5517r, AbstractC5524y abstractC5524y, C0040d c0040d) {
            this(c5517r, abstractC5524y);
        }

        /* renamed from: a */
        public final AbstractC5524y m21726a() {
            return this.f18988b;
        }

        /* renamed from: b */
        public final C5517r m21727b() {
            return this.f18987a;
        }
    }

    static {
        C5520u.a aVar = C5520u.f18962e;
        f18970h = aVar.m21713a("multipart/mixed");
        f18971i = aVar.m21713a("multipart/alternative");
        f18972j = aVar.m21713a("multipart/digest");
        f18973k = aVar.m21713a("multipart/parallel");
        f18974l = aVar.m21713a("multipart/form-data");
        f18975m = new byte[]{58, 32};
        f18976n = new byte[]{Ascii.f15380CR, 10};
        f18977o = new byte[]{45, 45};
    }

    public C5521v(ByteString byteString, C5520u c5520u, List<c> list) {
        C0042f.m158e(byteString, "boundaryByteString");
        C0042f.m158e(c5520u, "type");
        C0042f.m158e(list, "parts");
        this.f18978b = byteString;
        this.f18979c = c5520u;
        this.f18980d = list;
        this.f18981e = C5520u.f18962e.m21713a(c5520u + "; boundary=" + m21718i());
        this.f18982f = -1L;
    }

    @Override // okhttp3.AbstractC5524y
    /* renamed from: a */
    public long mo21715a() throws EOFException {
        long j9 = this.f18982f;
        if (j9 != -1) {
            return j9;
        }
        long jM21719j = m21719j(null, true);
        this.f18982f = jM21719j;
        return jM21719j;
    }

    @Override // okhttp3.AbstractC5524y
    /* renamed from: b */
    public C5520u mo21716b() {
        return this.f18981e;
    }

    @Override // okhttp3.AbstractC5524y
    /* renamed from: h */
    public void mo21717h(InterfaceC6323d interfaceC6323d) throws EOFException {
        C0042f.m158e(interfaceC6323d, "sink");
        m21719j(interfaceC6323d, false);
    }

    /* renamed from: i */
    public final String m21718i() {
        return this.f18978b.m21895u();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: j */
    public final long m21719j(InterfaceC6323d interfaceC6323d, boolean z8) throws EOFException {
        C6322c c6322c;
        if (z8) {
            interfaceC6323d = new C6322c();
            c6322c = interfaceC6323d;
        } else {
            c6322c = 0;
        }
        int size = this.f18980d.size();
        long j9 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            c cVar = this.f18980d.get(i9);
            C5517r c5517rM21727b = cVar.m21727b();
            AbstractC5524y abstractC5524yM21726a = cVar.m21726a();
            C0042f.m155b(interfaceC6323d);
            interfaceC6323d.write(f18977o);
            interfaceC6323d.mo24227p(this.f18978b);
            interfaceC6323d.write(f18976n);
            if (c5517rM21727b != null) {
                int size2 = c5517rM21727b.size();
                for (int i10 = 0; i10 < size2; i10++) {
                    interfaceC6323d.mo24221j(c5517rM21727b.m21627b(i10)).write(f18975m).mo24221j(c5517rM21727b.m21629d(i10)).write(f18976n);
                }
            }
            C5520u c5520uMo21716b = abstractC5524yM21726a.mo21716b();
            if (c5520uMo21716b != null) {
                interfaceC6323d.mo24221j("Content-Type: ").mo24221j(c5520uMo21716b.toString()).write(f18976n);
            }
            long jMo21715a = abstractC5524yM21726a.mo21715a();
            if (jMo21715a != -1) {
                interfaceC6323d.mo24221j("Content-Length: ").mo24229s(jMo21715a).write(f18976n);
            } else if (z8) {
                C0042f.m155b(c6322c);
                c6322c.m24231u();
                return -1L;
            }
            byte[] bArr = f18976n;
            interfaceC6323d.write(bArr);
            if (z8) {
                j9 += jMo21715a;
            } else {
                abstractC5524yM21726a.mo21717h(interfaceC6323d);
            }
            interfaceC6323d.write(bArr);
        }
        C0042f.m155b(interfaceC6323d);
        byte[] bArr2 = f18977o;
        interfaceC6323d.write(bArr2);
        interfaceC6323d.mo24227p(this.f18978b);
        interfaceC6323d.write(bArr2);
        interfaceC6323d.write(f18976n);
        if (!z8) {
            return j9;
        }
        C0042f.m155b(c6322c);
        long size3 = j9 + c6322c.size();
        c6322c.m24231u();
        return size3;
    }

    /* renamed from: okhttp3.v$a */
    public static final class a {

        /* renamed from: a */
        public final ByteString f18983a;

        /* renamed from: b */
        public C5520u f18984b;

        /* renamed from: c */
        public final List<c> f18985c;

        /* JADX WARN: Multi-variable type inference failed */
        public a() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public a(String str) {
            C0042f.m158e(str, "boundary");
            this.f18983a = ByteString.f19095d.m21901d(str);
            this.f18984b = C5521v.f18970h;
            this.f18985c = new ArrayList();
        }

        /* renamed from: a */
        public final a m21720a(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            m21722c(c.f18986c.m21729b(str, str2));
            return this;
        }

        /* renamed from: b */
        public final a m21721b(String str, String str2, AbstractC5524y abstractC5524y) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(abstractC5524y, TtmlNode.TAG_BODY);
            m21722c(c.f18986c.m21730c(str, str2, abstractC5524y));
            return this;
        }

        /* renamed from: c */
        public final a m21722c(c cVar) {
            C0042f.m158e(cVar, "part");
            this.f18985c.add(cVar);
            return this;
        }

        /* renamed from: d */
        public final C5521v m21723d() {
            if (!this.f18985c.isEmpty()) {
                return new C5521v(this.f18983a, this.f18984b, C5057d.m19779S(this.f18985c));
            }
            throw new IllegalStateException("Multipart body must have at least one part.".toString());
        }

        /* renamed from: e */
        public final a m21724e(C5520u c5520u) {
            C0042f.m158e(c5520u, "type");
            if (C0042f.m154a(c5520u.m21712g(), "multipart")) {
                this.f18984b = c5520u;
                return this;
            }
            throw new IllegalArgumentException(("multipart != " + c5520u).toString());
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ a(String str, int i9, C0040d c0040d) {
            if ((i9 & 1) != 0) {
                str = UUID.randomUUID().toString();
                C0042f.m157d(str, "randomUUID().toString()");
            }
            this(str);
        }
    }
}
