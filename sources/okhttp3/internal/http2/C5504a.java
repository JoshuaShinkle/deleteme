package okhttp3.internal.http2;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.C5222e;
import kotlin.collections.C5234q;
import okio.ByteString;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5057d;
import p157o6.C5469a;
import p157o6.C5473e;
import p204t6.C6322c;
import p204t6.C6331l;
import p204t6.InterfaceC6324e;
import p204t6.InterfaceC6342w;

/* renamed from: okhttp3.internal.http2.a */
/* loaded from: classes.dex */
public final class C5504a {

    /* renamed from: a */
    public static final C5504a f18766a;

    /* renamed from: b */
    public static final C5469a[] f18767b;

    /* renamed from: c */
    public static final Map<ByteString, Integer> f18768c;

    static {
        C5504a c5504a = new C5504a();
        f18766a = c5504a;
        ByteString byteString = C5469a.f18392g;
        ByteString byteString2 = C5469a.f18393h;
        ByteString byteString3 = C5469a.f18394i;
        ByteString byteString4 = C5469a.f18391f;
        f18767b = new C5469a[]{new C5469a(C5469a.f18395j, ""), new C5469a(byteString, "GET"), new C5469a(byteString, "POST"), new C5469a(byteString2, "/"), new C5469a(byteString2, "/index.html"), new C5469a(byteString3, "http"), new C5469a(byteString3, "https"), new C5469a(byteString4, "200"), new C5469a(byteString4, "204"), new C5469a(byteString4, "206"), new C5469a(byteString4, "304"), new C5469a(byteString4, "400"), new C5469a(byteString4, "404"), new C5469a(byteString4, "500"), new C5469a("accept-charset", ""), new C5469a("accept-encoding", "gzip, deflate"), new C5469a("accept-language", ""), new C5469a("accept-ranges", ""), new C5469a("accept", ""), new C5469a("access-control-allow-origin", ""), new C5469a("age", ""), new C5469a("allow", ""), new C5469a("authorization", ""), new C5469a("cache-control", ""), new C5469a("content-disposition", ""), new C5469a("content-encoding", ""), new C5469a("content-language", ""), new C5469a("content-length", ""), new C5469a("content-location", ""), new C5469a("content-range", ""), new C5469a("content-type", ""), new C5469a("cookie", ""), new C5469a("date", ""), new C5469a("etag", ""), new C5469a("expect", ""), new C5469a("expires", ""), new C5469a(Constants.MessagePayloadKeys.FROM, ""), new C5469a("host", ""), new C5469a("if-match", ""), new C5469a("if-modified-since", ""), new C5469a("if-none-match", ""), new C5469a("if-range", ""), new C5469a("if-unmodified-since", ""), new C5469a("last-modified", ""), new C5469a("link", ""), new C5469a(FirebaseAnalytics.Param.LOCATION, ""), new C5469a("max-forwards", ""), new C5469a("proxy-authenticate", ""), new C5469a("proxy-authorization", ""), new C5469a("range", ""), new C5469a("referer", ""), new C5469a("refresh", ""), new C5469a("retry-after", ""), new C5469a("server", ""), new C5469a("set-cookie", ""), new C5469a("strict-transport-security", ""), new C5469a("transfer-encoding", ""), new C5469a("user-agent", ""), new C5469a("vary", ""), new C5469a("via", ""), new C5469a("www-authenticate", "")};
        f18768c = c5504a.m21399d();
    }

    /* renamed from: a */
    public final ByteString m21396a(ByteString byteString) throws IOException {
        C0042f.m158e(byteString, AppMeasurementSdk.ConditionalUserProperty.NAME);
        int iM21892r = byteString.m21892r();
        for (int i9 = 0; i9 < iM21892r; i9++) {
            byte bM21878d = byteString.m21878d(i9);
            if (65 <= bM21878d && bM21878d < 91) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.m21895u());
            }
        }
        return byteString;
    }

    /* renamed from: b */
    public final Map<ByteString, Integer> m21397b() {
        return f18768c;
    }

    /* renamed from: c */
    public final C5469a[] m21398c() {
        return f18767b;
    }

    /* renamed from: d */
    public final Map<ByteString, Integer> m21399d() {
        C5469a[] c5469aArr = f18767b;
        LinkedHashMap linkedHashMap = new LinkedHashMap(c5469aArr.length);
        int length = c5469aArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            C5469a[] c5469aArr2 = f18767b;
            if (!linkedHashMap.containsKey(c5469aArr2[i9].f18396a)) {
                linkedHashMap.put(c5469aArr2[i9].f18396a, Integer.valueOf(i9));
            }
        }
        Map<ByteString, Integer> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        C0042f.m157d(mapUnmodifiableMap, "unmodifiableMap(result)");
        return mapUnmodifiableMap;
    }

    /* renamed from: okhttp3.internal.http2.a$a */
    public static final class a {

        /* renamed from: a */
        public final int f18769a;

        /* renamed from: b */
        public int f18770b;

        /* renamed from: c */
        public final List<C5469a> f18771c;

        /* renamed from: d */
        public final InterfaceC6324e f18772d;

        /* renamed from: e */
        public C5469a[] f18773e;

        /* renamed from: f */
        public int f18774f;

        /* renamed from: g */
        public int f18775g;

        /* renamed from: h */
        public int f18776h;

        public a(InterfaceC6342w interfaceC6342w, int i9, int i10) {
            C0042f.m158e(interfaceC6342w, "source");
            this.f18769a = i9;
            this.f18770b = i10;
            this.f18771c = new ArrayList();
            this.f18772d = C6331l.m24256b(interfaceC6342w);
            this.f18773e = new C5469a[8];
            this.f18774f = r2.length - 1;
        }

        /* renamed from: a */
        public final void m21400a() {
            int i9 = this.f18770b;
            int i10 = this.f18776h;
            if (i9 < i10) {
                if (i9 == 0) {
                    m21401b();
                } else {
                    m21403d(i10 - i9);
                }
            }
        }

        /* renamed from: b */
        public final void m21401b() {
            C5222e.m20384g(this.f18773e, null, 0, 0, 6, null);
            this.f18774f = this.f18773e.length - 1;
            this.f18775g = 0;
            this.f18776h = 0;
        }

        /* renamed from: c */
        public final int m21402c(int i9) {
            return this.f18774f + 1 + i9;
        }

        /* renamed from: d */
        public final int m21403d(int i9) {
            int i10;
            int i11 = 0;
            if (i9 > 0) {
                int length = this.f18773e.length;
                while (true) {
                    length--;
                    i10 = this.f18774f;
                    if (length < i10 || i9 <= 0) {
                        break;
                    }
                    C5469a c5469a = this.f18773e[length];
                    C0042f.m155b(c5469a);
                    int i12 = c5469a.f18398c;
                    i9 -= i12;
                    this.f18776h -= i12;
                    this.f18775g--;
                    i11++;
                }
                C5469a[] c5469aArr = this.f18773e;
                System.arraycopy(c5469aArr, i10 + 1, c5469aArr, i10 + 1 + i11, this.f18775g);
                this.f18774f += i11;
            }
            return i11;
        }

        /* renamed from: e */
        public final List<C5469a> m21404e() {
            List<C5469a> listM20418G = C5234q.m20418G(this.f18771c);
            this.f18771c.clear();
            return listM20418G;
        }

        /* renamed from: f */
        public final ByteString m21405f(int i9) throws IOException {
            if (m21407h(i9)) {
                return C5504a.f18766a.m21398c()[i9].f18396a;
            }
            int iM21402c = m21402c(i9 - C5504a.f18766a.m21398c().length);
            if (iM21402c >= 0) {
                C5469a[] c5469aArr = this.f18773e;
                if (iM21402c < c5469aArr.length) {
                    C5469a c5469a = c5469aArr[iM21402c];
                    C0042f.m155b(c5469a);
                    return c5469a.f18396a;
                }
            }
            throw new IOException("Header index too large " + (i9 + 1));
        }

        /* renamed from: g */
        public final void m21406g(int i9, C5469a c5469a) {
            this.f18771c.add(c5469a);
            int i10 = c5469a.f18398c;
            if (i9 != -1) {
                C5469a c5469a2 = this.f18773e[m21402c(i9)];
                C0042f.m155b(c5469a2);
                i10 -= c5469a2.f18398c;
            }
            int i11 = this.f18770b;
            if (i10 > i11) {
                m21401b();
                return;
            }
            int iM21403d = m21403d((this.f18776h + i10) - i11);
            if (i9 == -1) {
                int i12 = this.f18775g + 1;
                C5469a[] c5469aArr = this.f18773e;
                if (i12 > c5469aArr.length) {
                    C5469a[] c5469aArr2 = new C5469a[c5469aArr.length * 2];
                    System.arraycopy(c5469aArr, 0, c5469aArr2, c5469aArr.length, c5469aArr.length);
                    this.f18774f = this.f18773e.length - 1;
                    this.f18773e = c5469aArr2;
                }
                int i13 = this.f18774f;
                this.f18774f = i13 - 1;
                this.f18773e[i13] = c5469a;
                this.f18775g++;
            } else {
                this.f18773e[i9 + m21402c(i9) + iM21403d] = c5469a;
            }
            this.f18776h += i10;
        }

        /* renamed from: h */
        public final boolean m21407h(int i9) {
            return i9 >= 0 && i9 <= C5504a.f18766a.m21398c().length - 1;
        }

        /* renamed from: i */
        public final int m21408i() {
            return C5057d.m19790d(this.f18772d.readByte(), 255);
        }

        /* renamed from: j */
        public final ByteString m21409j() {
            int iM21408i = m21408i();
            boolean z8 = (iM21408i & 128) == 128;
            long jM21412m = m21412m(iM21408i, 127);
            if (!z8) {
                return this.f18772d.mo24217e(jM21412m);
            }
            C6322c c6322c = new C6322c();
            C5473e.f18441a.m21167b(this.f18772d, jM21412m, c6322c);
            return c6322c.m24192E();
        }

        /* renamed from: k */
        public final void m21410k() throws IOException {
            while (!this.f18772d.mo24218g()) {
                int iM19790d = C5057d.m19790d(this.f18772d.readByte(), 255);
                if (iM19790d == 128) {
                    throw new IOException("index == 0");
                }
                if ((iM19790d & 128) == 128) {
                    m21411l(m21412m(iM19790d, 127) - 1);
                } else if (iM19790d == 64) {
                    m21414o();
                } else if ((iM19790d & 64) == 64) {
                    m21413n(m21412m(iM19790d, 63) - 1);
                } else if ((iM19790d & 32) == 32) {
                    int iM21412m = m21412m(iM19790d, 31);
                    this.f18770b = iM21412m;
                    if (iM21412m < 0 || iM21412m > this.f18769a) {
                        throw new IOException("Invalid dynamic table size update " + this.f18770b);
                    }
                    m21400a();
                } else if (iM19790d == 16 || iM19790d == 0) {
                    m21416q();
                } else {
                    m21415p(m21412m(iM19790d, 15) - 1);
                }
            }
        }

        /* renamed from: l */
        public final void m21411l(int i9) throws IOException {
            if (m21407h(i9)) {
                this.f18771c.add(C5504a.f18766a.m21398c()[i9]);
                return;
            }
            int iM21402c = m21402c(i9 - C5504a.f18766a.m21398c().length);
            if (iM21402c >= 0) {
                C5469a[] c5469aArr = this.f18773e;
                if (iM21402c < c5469aArr.length) {
                    List<C5469a> list = this.f18771c;
                    C5469a c5469a = c5469aArr[iM21402c];
                    C0042f.m155b(c5469a);
                    list.add(c5469a);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i9 + 1));
        }

        /* renamed from: m */
        public final int m21412m(int i9, int i10) {
            int i11 = i9 & i10;
            if (i11 < i10) {
                return i11;
            }
            int i12 = 0;
            while (true) {
                int iM21408i = m21408i();
                if ((iM21408i & 128) == 0) {
                    return i10 + (iM21408i << i12);
                }
                i10 += (iM21408i & 127) << i12;
                i12 += 7;
            }
        }

        /* renamed from: n */
        public final void m21413n(int i9) {
            m21406g(-1, new C5469a(m21405f(i9), m21409j()));
        }

        /* renamed from: o */
        public final void m21414o() {
            m21406g(-1, new C5469a(C5504a.f18766a.m21396a(m21409j()), m21409j()));
        }

        /* renamed from: p */
        public final void m21415p(int i9) throws IOException {
            this.f18771c.add(new C5469a(m21405f(i9), m21409j()));
        }

        /* renamed from: q */
        public final void m21416q() throws IOException {
            this.f18771c.add(new C5469a(C5504a.f18766a.m21396a(m21409j()), m21409j()));
        }

        public /* synthetic */ a(InterfaceC6342w interfaceC6342w, int i9, int i10, int i11, C0040d c0040d) {
            this(interfaceC6342w, i9, (i11 & 4) != 0 ? i9 : i10);
        }
    }

    /* renamed from: okhttp3.internal.http2.a$b */
    public static final class b {

        /* renamed from: a */
        public int f18777a;

        /* renamed from: b */
        public final boolean f18778b;

        /* renamed from: c */
        public final C6322c f18779c;

        /* renamed from: d */
        public int f18780d;

        /* renamed from: e */
        public boolean f18781e;

        /* renamed from: f */
        public int f18782f;

        /* renamed from: g */
        public C5469a[] f18783g;

        /* renamed from: h */
        public int f18784h;

        /* renamed from: i */
        public int f18785i;

        /* renamed from: j */
        public int f18786j;

        public b(int i9, boolean z8, C6322c c6322c) {
            C0042f.m158e(c6322c, "out");
            this.f18777a = i9;
            this.f18778b = z8;
            this.f18779c = c6322c;
            this.f18780d = Integer.MAX_VALUE;
            this.f18782f = i9;
            this.f18783g = new C5469a[8];
            this.f18784h = r2.length - 1;
        }

        /* renamed from: a */
        public final void m21417a() {
            int i9 = this.f18782f;
            int i10 = this.f18786j;
            if (i9 < i10) {
                if (i9 == 0) {
                    m21418b();
                } else {
                    m21419c(i10 - i9);
                }
            }
        }

        /* renamed from: b */
        public final void m21418b() {
            C5222e.m20384g(this.f18783g, null, 0, 0, 6, null);
            this.f18784h = this.f18783g.length - 1;
            this.f18785i = 0;
            this.f18786j = 0;
        }

        /* renamed from: c */
        public final int m21419c(int i9) {
            int i10;
            int i11 = 0;
            if (i9 > 0) {
                int length = this.f18783g.length;
                while (true) {
                    length--;
                    i10 = this.f18784h;
                    if (length < i10 || i9 <= 0) {
                        break;
                    }
                    C5469a c5469a = this.f18783g[length];
                    C0042f.m155b(c5469a);
                    i9 -= c5469a.f18398c;
                    int i12 = this.f18786j;
                    C5469a c5469a2 = this.f18783g[length];
                    C0042f.m155b(c5469a2);
                    this.f18786j = i12 - c5469a2.f18398c;
                    this.f18785i--;
                    i11++;
                }
                C5469a[] c5469aArr = this.f18783g;
                System.arraycopy(c5469aArr, i10 + 1, c5469aArr, i10 + 1 + i11, this.f18785i);
                C5469a[] c5469aArr2 = this.f18783g;
                int i13 = this.f18784h;
                Arrays.fill(c5469aArr2, i13 + 1, i13 + 1 + i11, (Object) null);
                this.f18784h += i11;
            }
            return i11;
        }

        /* renamed from: d */
        public final void m21420d(C5469a c5469a) {
            int i9 = c5469a.f18398c;
            int i10 = this.f18782f;
            if (i9 > i10) {
                m21418b();
                return;
            }
            m21419c((this.f18786j + i9) - i10);
            int i11 = this.f18785i + 1;
            C5469a[] c5469aArr = this.f18783g;
            if (i11 > c5469aArr.length) {
                C5469a[] c5469aArr2 = new C5469a[c5469aArr.length * 2];
                System.arraycopy(c5469aArr, 0, c5469aArr2, c5469aArr.length, c5469aArr.length);
                this.f18784h = this.f18783g.length - 1;
                this.f18783g = c5469aArr2;
            }
            int i12 = this.f18784h;
            this.f18784h = i12 - 1;
            this.f18783g[i12] = c5469a;
            this.f18785i++;
            this.f18786j += i9;
        }

        /* renamed from: e */
        public final void m21421e(int i9) {
            this.f18777a = i9;
            int iMin = Math.min(i9, 16384);
            int i10 = this.f18782f;
            if (i10 == iMin) {
                return;
            }
            if (iMin < i10) {
                this.f18780d = Math.min(this.f18780d, iMin);
            }
            this.f18781e = true;
            this.f18782f = iMin;
            m21417a();
        }

        /* renamed from: f */
        public final void m21422f(ByteString byteString) {
            C0042f.m158e(byteString, "data");
            if (this.f18778b) {
                C5473e c5473e = C5473e.f18441a;
                if (c5473e.m21169d(byteString) < byteString.m21892r()) {
                    C6322c c6322c = new C6322c();
                    c5473e.m21168c(byteString, c6322c);
                    ByteString byteStringM24192E = c6322c.m24192E();
                    m21424h(byteStringM24192E.m21892r(), 127, 128);
                    this.f18779c.mo24227p(byteStringM24192E);
                    return;
                }
            }
            m21424h(byteString.m21892r(), 127, 0);
            this.f18779c.mo24227p(byteString);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x007f  */
        /* renamed from: g */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m21423g(List<C5469a> list) {
            int length;
            int length2;
            C0042f.m158e(list, "headerBlock");
            if (this.f18781e) {
                int i9 = this.f18780d;
                if (i9 < this.f18782f) {
                    m21424h(i9, 31, 32);
                }
                this.f18781e = false;
                this.f18780d = Integer.MAX_VALUE;
                m21424h(this.f18782f, 31, 32);
            }
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                C5469a c5469a = list.get(i10);
                ByteString byteStringMo21894t = c5469a.f18396a.mo21894t();
                ByteString byteString = c5469a.f18397b;
                C5504a c5504a = C5504a.f18766a;
                Integer num = c5504a.m21397b().get(byteStringMo21894t);
                if (num != null) {
                    length2 = num.intValue() + 1;
                    if (!(2 <= length2 && length2 < 8)) {
                        length = length2;
                        length2 = -1;
                    } else if (C0042f.m154a(c5504a.m21398c()[length2 - 1].f18397b, byteString)) {
                        length = length2;
                    } else if (C0042f.m154a(c5504a.m21398c()[length2].f18397b, byteString)) {
                        length2++;
                        length = length2;
                    }
                } else {
                    length = -1;
                    length2 = -1;
                }
                if (length2 == -1) {
                    int i11 = this.f18784h + 1;
                    int length3 = this.f18783g.length;
                    while (true) {
                        if (i11 >= length3) {
                            break;
                        }
                        C5469a c5469a2 = this.f18783g[i11];
                        C0042f.m155b(c5469a2);
                        if (C0042f.m154a(c5469a2.f18396a, byteStringMo21894t)) {
                            C5469a c5469a3 = this.f18783g[i11];
                            C0042f.m155b(c5469a3);
                            if (C0042f.m154a(c5469a3.f18397b, byteString)) {
                                length2 = C5504a.f18766a.m21398c().length + (i11 - this.f18784h);
                                break;
                            } else if (length == -1) {
                                length = (i11 - this.f18784h) + C5504a.f18766a.m21398c().length;
                            }
                        }
                        i11++;
                    }
                }
                if (length2 != -1) {
                    m21424h(length2, 127, 128);
                } else if (length == -1) {
                    this.f18779c.writeByte(64);
                    m21422f(byteStringMo21894t);
                    m21422f(byteString);
                    m21420d(c5469a);
                } else if (!byteStringMo21894t.m21893s(C5469a.f18390e) || C0042f.m154a(C5469a.f18395j, byteStringMo21894t)) {
                    m21424h(length, 63, 64);
                    m21422f(byteString);
                    m21420d(c5469a);
                } else {
                    m21424h(length, 15, 0);
                    m21422f(byteString);
                }
            }
        }

        /* renamed from: h */
        public final void m21424h(int i9, int i10, int i11) {
            if (i9 < i10) {
                this.f18779c.writeByte(i9 | i11);
                return;
            }
            this.f18779c.writeByte(i11 | i10);
            int i12 = i9 - i10;
            while (i12 >= 128) {
                this.f18779c.writeByte(128 | (i12 & 127));
                i12 >>>= 7;
            }
            this.f18779c.writeByte(i12);
        }

        public /* synthetic */ b(int i9, boolean z8, C6322c c6322c, int i10, C0040d c0040d) {
            this((i10 & 1) != 0 ? 4096 : i9, (i10 & 2) != 0 ? true : z8, c6322c);
        }
    }
}
