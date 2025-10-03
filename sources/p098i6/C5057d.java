package p098i6;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.collections.AbstractC5236s;
import kotlin.collections.C5223f;
import kotlin.collections.C5226i;
import kotlin.collections.C5227j;
import kotlin.collections.C5234q;
import kotlin.collections.C5239v;
import kotlin.text.C5246c;
import kotlin.text.C5255l;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.AbstractC5483a0;
import okhttp3.AbstractC5516q;
import okhttp3.AbstractC5524y;
import okhttp3.C5517r;
import okhttp3.C5518s;
import okhttp3.C5522w;
import okhttp3.C5525z;
import okhttp3.InterfaceC5488e;
import okio.ByteString;
import p007a6.C0038b;
import p007a6.C0042f;
import p007a6.C0045i;
import p027c6.C0747c;
import p027c6.C0749e;
import p157o6.C5469a;
import p203t5.C6313a;
import p204t6.C6322c;
import p204t6.C6334o;
import p204t6.InterfaceC6323d;
import p204t6.InterfaceC6324e;
import p204t6.InterfaceC6342w;

/* renamed from: i6.d */
/* loaded from: classes2.dex */
public final class C5057d {

    /* renamed from: a */
    public static final byte[] f17443a;

    /* renamed from: b */
    public static final C5517r f17444b = C5517r.f18938c.m21645g(new String[0]);

    /* renamed from: c */
    public static final AbstractC5483a0 f17445c;

    /* renamed from: d */
    public static final AbstractC5524y f17446d;

    /* renamed from: e */
    public static final C6334o f17447e;

    /* renamed from: f */
    public static final TimeZone f17448f;

    /* renamed from: g */
    public static final Regex f17449g;

    /* renamed from: h */
    public static final boolean f17450h;

    /* renamed from: i */
    public static final String f17451i;

    static {
        byte[] bArr = new byte[0];
        f17443a = bArr;
        f17445c = AbstractC5483a0.a.m21232c(AbstractC5483a0.f18496b, bArr, null, 1, null);
        f17446d = AbstractC5524y.a.m21829j(AbstractC5524y.f19061a, bArr, null, 0, 0, 7, null);
        C6334o.a aVar = C6334o.f21354e;
        ByteString.C5526a c5526a = ByteString.f19095d;
        f17447e = aVar.m24279d(c5526a.m21899b("efbbbf"), c5526a.m21899b("feff"), c5526a.m21899b("fffe"), c5526a.m21899b("0000ffff"), c5526a.m21899b("ffff0000"));
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        C0042f.m155b(timeZone);
        f17448f = timeZone;
        f17449g = new Regex("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
        f17450h = false;
        String name = C5522w.class.getName();
        C0042f.m157d(name, "OkHttpClient::class.java.name");
        f17451i = StringsKt__StringsKt.m20477c0(StringsKt__StringsKt.m20476b0(name, "okhttp3."), "Client");
    }

    /* renamed from: A */
    public static /* synthetic */ int m19761A(String str, int i9, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i9 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = str.length();
        }
        return m19812z(str, i9, i10);
    }

    /* renamed from: B */
    public static final int m19762B(String str, int i9, int i10) {
        C0042f.m158e(str, "<this>");
        int i11 = i10 - 1;
        if (i9 <= i11) {
            while (true) {
                char cCharAt = str.charAt(i11);
                if (!((((cCharAt == '\t' || cCharAt == '\n') || cCharAt == '\f') || cCharAt == '\r') || cCharAt == ' ')) {
                    return i11 + 1;
                }
                if (i11 == i9) {
                    break;
                }
                i11--;
            }
        }
        return i9;
    }

    /* renamed from: C */
    public static /* synthetic */ int m19763C(String str, int i9, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i9 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = str.length();
        }
        return m19762B(str, i9, i10);
    }

    /* renamed from: D */
    public static final int m19764D(String str, int i9) {
        C0042f.m158e(str, "<this>");
        int length = str.length();
        while (i9 < length) {
            char cCharAt = str.charAt(i9);
            if (cCharAt != ' ' && cCharAt != '\t') {
                return i9;
            }
            i9++;
        }
        return str.length();
    }

    /* renamed from: E */
    public static final String[] m19765E(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        C0042f.m158e(strArr, "<this>");
        C0042f.m158e(strArr2, "other");
        C0042f.m158e(comparator, "comparator");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i9 = 0;
            while (true) {
                if (i9 >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i9]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i9++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: F */
    public static final boolean m19766F(Socket socket, InterfaceC6324e interfaceC6324e) throws SocketException {
        C0042f.m158e(socket, "<this>");
        C0042f.m158e(interfaceC6324e, "source");
        try {
            int soTimeout = socket.getSoTimeout();
            try {
                socket.setSoTimeout(1);
                boolean z8 = !interfaceC6324e.mo24218g();
                socket.setSoTimeout(soTimeout);
                return z8;
            } catch (Throwable th) {
                socket.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    /* renamed from: G */
    public static final boolean m19767G(String str) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        return C5255l.m20513l(str, HttpHeaders.AUTHORIZATION, true) || C5255l.m20513l(str, HttpHeaders.COOKIE, true) || C5255l.m20513l(str, HttpHeaders.PROXY_AUTHORIZATION, true) || C5255l.m20513l(str, HttpHeaders.SET_COOKIE, true);
    }

    /* renamed from: H */
    public static final int m19768H(char c9) {
        if ('0' <= c9 && c9 < ':') {
            return c9 - '0';
        }
        char c10 = 'a';
        if (!('a' <= c9 && c9 < 'g')) {
            c10 = 'A';
            if (!('A' <= c9 && c9 < 'G')) {
                return -1;
            }
        }
        return (c9 - c10) + 10;
    }

    /* renamed from: I */
    public static final Charset m19769I(InterfaceC6324e interfaceC6324e, Charset charset) {
        C0042f.m158e(interfaceC6324e, "<this>");
        C0042f.m158e(charset, "default");
        int iMo24225n = interfaceC6324e.mo24225n(f17447e);
        if (iMo24225n == -1) {
            return charset;
        }
        if (iMo24225n == 0) {
            Charset charset2 = StandardCharsets.UTF_8;
            C0042f.m157d(charset2, "UTF_8");
            return charset2;
        }
        if (iMo24225n == 1) {
            Charset charset3 = StandardCharsets.UTF_16BE;
            C0042f.m157d(charset3, "UTF_16BE");
            return charset3;
        }
        if (iMo24225n == 2) {
            Charset charset4 = StandardCharsets.UTF_16LE;
            C0042f.m157d(charset4, "UTF_16LE");
            return charset4;
        }
        if (iMo24225n == 3) {
            return C5246c.f17845a.m20498a();
        }
        if (iMo24225n == 4) {
            return C5246c.f17845a.m20499b();
        }
        throw new AssertionError();
    }

    /* renamed from: J */
    public static final int m19770J(InterfaceC6324e interfaceC6324e) {
        C0042f.m158e(interfaceC6324e, "<this>");
        return m19790d(interfaceC6324e.readByte(), 255) | (m19790d(interfaceC6324e.readByte(), 255) << 16) | (m19790d(interfaceC6324e.readByte(), 255) << 8);
    }

    /* renamed from: K */
    public static final int m19771K(C6322c c6322c, byte b9) throws EOFException {
        C0042f.m158e(c6322c, "<this>");
        int i9 = 0;
        while (!c6322c.mo24218g() && c6322c.m24236z(0L) == b9) {
            i9++;
            c6322c.readByte();
        }
        return i9;
    }

    /* renamed from: L */
    public static final boolean m19772L(InterfaceC6342w interfaceC6342w, int i9, TimeUnit timeUnit) {
        C0042f.m158e(interfaceC6342w, "<this>");
        C0042f.m158e(timeUnit, "timeUnit");
        long jNanoTime = System.nanoTime();
        long jMo24241c = interfaceC6342w.mo21076a().mo24243e() ? interfaceC6342w.mo21076a().mo24241c() - jNanoTime : Long.MAX_VALUE;
        interfaceC6342w.mo21076a().mo24242d(Math.min(jMo24241c, timeUnit.toNanos(i9)) + jNanoTime);
        try {
            C6322c c6322c = new C6322c();
            while (interfaceC6342w.mo21077d(c6322c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                c6322c.m24231u();
            }
            if (jMo24241c == Long.MAX_VALUE) {
                interfaceC6342w.mo21076a().mo24239a();
            } else {
                interfaceC6342w.mo21076a().mo24242d(jNanoTime + jMo24241c);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (jMo24241c == Long.MAX_VALUE) {
                interfaceC6342w.mo21076a().mo24239a();
            } else {
                interfaceC6342w.mo21076a().mo24242d(jNanoTime + jMo24241c);
            }
            return false;
        } catch (Throwable th) {
            if (jMo24241c == Long.MAX_VALUE) {
                interfaceC6342w.mo21076a().mo24239a();
            } else {
                interfaceC6342w.mo21076a().mo24242d(jNanoTime + jMo24241c);
            }
            throw th;
        }
    }

    /* renamed from: M */
    public static final ThreadFactory m19773M(final String str, final boolean z8) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        return new ThreadFactory() { // from class: i6.b
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return C5057d.m19774N(str, z8, runnable);
            }
        };
    }

    /* renamed from: N */
    public static final Thread m19774N(String str, boolean z8, Runnable runnable) {
        C0042f.m158e(str, "$name");
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(z8);
        return thread;
    }

    /* renamed from: O */
    public static final List<C5469a> m19775O(C5517r c5517r) {
        C0042f.m158e(c5517r, "<this>");
        C0747c c0747cM3626g = C0749e.m3626g(0, c5517r.size());
        ArrayList arrayList = new ArrayList(C5227j.m20408n(c0747cM3626g, 10));
        Iterator<Integer> it = c0747cM3626g.iterator();
        while (it.hasNext()) {
            int iNextInt = ((AbstractC5236s) it).nextInt();
            arrayList.add(new C5469a(c5517r.m21627b(iNextInt), c5517r.m21629d(iNextInt)));
        }
        return arrayList;
    }

    /* renamed from: P */
    public static final C5517r m19776P(List<C5469a> list) {
        C0042f.m158e(list, "<this>");
        C5517r.a aVar = new C5517r.a();
        for (C5469a c5469a : list) {
            aVar.m21633c(c5469a.m21114a().m21895u(), c5469a.m21115b().m21895u());
        }
        return aVar.m21635e();
    }

    /* renamed from: Q */
    public static final String m19777Q(C5518s c5518s, boolean z8) {
        String strM21653h;
        C0042f.m158e(c5518s, "<this>");
        if (StringsKt__StringsKt.m20451C(c5518s.m21653h(), ":", false, 2, null)) {
            strM21653h = '[' + c5518s.m21653h() + ']';
        } else {
            strM21653h = c5518s.m21653h();
        }
        if (!z8 && c5518s.m21657l() == C5518s.f18941k.m21697c(c5518s.m21661p())) {
            return strM21653h;
        }
        return strM21653h + ':' + c5518s.m21657l();
    }

    /* renamed from: R */
    public static /* synthetic */ String m19778R(C5518s c5518s, boolean z8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            z8 = false;
        }
        return m19777Q(c5518s, z8);
    }

    /* renamed from: S */
    public static final <T> List<T> m19779S(List<? extends T> list) {
        C0042f.m158e(list, "<this>");
        List<T> listUnmodifiableList = Collections.unmodifiableList(C5234q.m20420I(list));
        C0042f.m157d(listUnmodifiableList, "unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    /* renamed from: T */
    public static final <K, V> Map<K, V> m19780T(Map<K, ? extends V> map) {
        C0042f.m158e(map, "<this>");
        if (map.isEmpty()) {
            return C5239v.m20435c();
        }
        Map<K, V> mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(map));
        C0042f.m157d(mapUnmodifiableMap, "{\n    Collections.unmodi…(LinkedHashMap(this))\n  }");
        return mapUnmodifiableMap;
    }

    /* renamed from: U */
    public static final long m19781U(String str, long j9) {
        C0042f.m158e(str, "<this>");
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j9;
        }
    }

    /* renamed from: V */
    public static final int m19782V(String str, int i9) throws NumberFormatException {
        if (str != null) {
            try {
                long j9 = Long.parseLong(str);
                if (j9 > 2147483647L) {
                    return Integer.MAX_VALUE;
                }
                if (j9 < 0) {
                    return 0;
                }
                return (int) j9;
            } catch (NumberFormatException unused) {
            }
        }
        return i9;
    }

    /* renamed from: W */
    public static final String m19783W(String str, int i9, int i10) {
        C0042f.m158e(str, "<this>");
        int iM19812z = m19812z(str, i9, i10);
        String strSubstring = str.substring(iM19812z, m19762B(str, iM19812z, i10));
        C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    /* renamed from: X */
    public static /* synthetic */ String m19784X(String str, int i9, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i9 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = str.length();
        }
        return m19783W(str, i9, i10);
    }

    /* renamed from: Y */
    public static final Throwable m19785Y(Exception exc, List<? extends Exception> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C0042f.m158e(exc, "<this>");
        C0042f.m158e(list, "suppressed");
        Iterator<? extends Exception> it = list.iterator();
        while (it.hasNext()) {
            C6313a.m24147a(exc, it.next());
        }
        return exc;
    }

    /* renamed from: Z */
    public static final void m19786Z(InterfaceC6323d interfaceC6323d, int i9) {
        C0042f.m158e(interfaceC6323d, "<this>");
        interfaceC6323d.writeByte((i9 >>> 16) & 255);
        interfaceC6323d.writeByte((i9 >>> 8) & 255);
        interfaceC6323d.writeByte(i9 & 255);
    }

    /* renamed from: c */
    public static final <E> void m19789c(List<E> list, E e9) {
        C0042f.m158e(list, "<this>");
        if (list.contains(e9)) {
            return;
        }
        list.add(e9);
    }

    /* renamed from: d */
    public static final int m19790d(byte b9, int i9) {
        return b9 & i9;
    }

    /* renamed from: e */
    public static final int m19791e(short s8, int i9) {
        return s8 & i9;
    }

    /* renamed from: f */
    public static final long m19792f(int i9, long j9) {
        return i9 & j9;
    }

    /* renamed from: g */
    public static final AbstractC5516q.c m19793g(final AbstractC5516q abstractC5516q) {
        C0042f.m158e(abstractC5516q, "<this>");
        return new AbstractC5516q.c() { // from class: i6.c
            @Override // okhttp3.AbstractC5516q.c
            /* renamed from: a */
            public final AbstractC5516q mo19760a(InterfaceC5488e interfaceC5488e) {
                return C5057d.m19794h(abstractC5516q, interfaceC5488e);
            }
        };
    }

    /* renamed from: h */
    public static final AbstractC5516q m19794h(AbstractC5516q abstractC5516q, InterfaceC5488e interfaceC5488e) {
        C0042f.m158e(abstractC5516q, "$this_asFactory");
        C0042f.m158e(interfaceC5488e, "it");
        return abstractC5516q;
    }

    /* renamed from: i */
    public static final boolean m19795i(String str) {
        C0042f.m158e(str, "<this>");
        return f17449g.m20441a(str);
    }

    /* renamed from: j */
    public static final boolean m19796j(C5518s c5518s, C5518s c5518s2) {
        C0042f.m158e(c5518s, "<this>");
        C0042f.m158e(c5518s2, "other");
        return C0042f.m154a(c5518s.m21653h(), c5518s2.m21653h()) && c5518s.m21657l() == c5518s2.m21657l() && C0042f.m154a(c5518s.m21661p(), c5518s2.m21661p());
    }

    /* renamed from: k */
    public static final int m19797k(String str, long j9, TimeUnit timeUnit) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        boolean z8 = true;
        if (!(j9 >= 0)) {
            throw new IllegalStateException((str + " < 0").toString());
        }
        if (!(timeUnit != null)) {
            throw new IllegalStateException("unit == null".toString());
        }
        long millis = timeUnit.toMillis(j9);
        if (!(millis <= 2147483647L)) {
            throw new IllegalArgumentException((str + " too large.").toString());
        }
        if (millis == 0 && j9 > 0) {
            z8 = false;
        }
        if (z8) {
            return (int) millis;
        }
        throw new IllegalArgumentException((str + " too small.").toString());
    }

    /* renamed from: l */
    public static final void m19798l(long j9, long j10, long j11) {
        if ((j10 | j11) < 0 || j10 > j9 || j9 - j10 < j11) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* renamed from: m */
    public static final void m19799m(Closeable closeable) throws IOException {
        C0042f.m158e(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e9) {
            throw e9;
        } catch (Exception unused) {
        }
    }

    /* renamed from: n */
    public static final void m19800n(Socket socket) throws IOException {
        C0042f.m158e(socket, "<this>");
        try {
            socket.close();
        } catch (AssertionError e9) {
            throw e9;
        } catch (RuntimeException e10) {
            if (!C0042f.m154a(e10.getMessage(), "bio == null")) {
                throw e10;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: o */
    public static final String[] m19801o(String[] strArr, String str) {
        C0042f.m158e(strArr, "<this>");
        C0042f.m158e(str, "value");
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length + 1);
        C0042f.m157d(objArrCopyOf, "copyOf(this, newSize)");
        String[] strArr2 = (String[]) objArrCopyOf;
        strArr2[C5223f.m20388k(strArr2)] = str;
        return strArr2;
    }

    /* renamed from: p */
    public static final int m19802p(String str, char c9, int i9, int i10) {
        C0042f.m158e(str, "<this>");
        while (i9 < i10) {
            if (str.charAt(i9) == c9) {
                return i9;
            }
            i9++;
        }
        return i10;
    }

    /* renamed from: q */
    public static final int m19803q(String str, String str2, int i9, int i10) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "delimiters");
        while (i9 < i10) {
            if (StringsKt__StringsKt.m20450B(str2, str.charAt(i9), false, 2, null)) {
                return i9;
            }
            i9++;
        }
        return i10;
    }

    /* renamed from: r */
    public static /* synthetic */ int m19804r(String str, char c9, int i9, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i9 = 0;
        }
        if ((i11 & 4) != 0) {
            i10 = str.length();
        }
        return m19802p(str, c9, i9, i10);
    }

    /* renamed from: s */
    public static final boolean m19805s(InterfaceC6342w interfaceC6342w, int i9, TimeUnit timeUnit) {
        C0042f.m158e(interfaceC6342w, "<this>");
        C0042f.m158e(timeUnit, "timeUnit");
        try {
            return m19772L(interfaceC6342w, i9, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    /* renamed from: t */
    public static final String m19806t(String str, Object... objArr) {
        C0042f.m158e(str, "format");
        C0042f.m158e(objArr, "args");
        C0045i c0045i = C0045i.f138a;
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        String str2 = String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
        C0042f.m157d(str2, "format(locale, format, *args)");
        return str2;
    }

    /* renamed from: u */
    public static final boolean m19807u(String[] strArr, String[] strArr2, Comparator<? super String> comparator) {
        C0042f.m158e(strArr, "<this>");
        C0042f.m158e(comparator, "comparator");
        if (!(strArr.length == 0) && strArr2 != null) {
            if (!(strArr2.length == 0)) {
                for (String str : strArr) {
                    Iterator itM151a = C0038b.m151a(strArr2);
                    while (itM151a.hasNext()) {
                        if (comparator.compare(str, (String) itM151a.next()) == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* renamed from: v */
    public static final long m19808v(C5525z c5525z) {
        C0042f.m158e(c5525z, "<this>");
        String strM21626a = c5525z.m21839C().m21626a(HttpHeaders.CONTENT_LENGTH);
        if (strM21626a != null) {
            return m19781U(strM21626a, -1L);
        }
        return -1L;
    }

    @SafeVarargs
    /* renamed from: w */
    public static final <T> List<T> m19809w(T... tArr) {
        C0042f.m158e(tArr, "elements");
        Object[] objArr = (Object[]) tArr.clone();
        List<T> listUnmodifiableList = Collections.unmodifiableList(C5226i.m20402h(Arrays.copyOf(objArr, objArr.length)));
        C0042f.m157d(listUnmodifiableList, "unmodifiableList(listOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    /* renamed from: x */
    public static final int m19810x(String[] strArr, String str, Comparator<String> comparator) {
        C0042f.m158e(strArr, "<this>");
        C0042f.m158e(str, "value");
        C0042f.m158e(comparator, "comparator");
        int length = strArr.length;
        for (int i9 = 0; i9 < length; i9++) {
            if (comparator.compare(strArr[i9], str) == 0) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: y */
    public static final int m19811y(String str) {
        C0042f.m158e(str, "<this>");
        int length = str.length();
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = str.charAt(i9);
            if (C0042f.m159f(cCharAt, 31) <= 0 || C0042f.m159f(cCharAt, 127) >= 0) {
                return i9;
            }
        }
        return -1;
    }

    /* renamed from: z */
    public static final int m19812z(String str, int i9, int i10) {
        C0042f.m158e(str, "<this>");
        while (i9 < i10) {
            char cCharAt = str.charAt(i9);
            if (!((((cCharAt == '\t' || cCharAt == '\n') || cCharAt == '\f') || cCharAt == '\r') || cCharAt == ' ')) {
                return i9;
            }
            i9++;
        }
        return i10;
    }
}
