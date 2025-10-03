package okhttp3.internal.publicsuffix;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.C5225h;
import kotlin.collections.C5226i;
import kotlin.collections.C5234q;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.lang3.ClassUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p058e6.C4766h;
import p098i6.C5057d;
import p168p6.C6113j;
import p203t5.C6319g;
import p204t6.C6328i;
import p204t6.C6331l;
import p204t6.InterfaceC6324e;
import p248y5.C6791a;

/* loaded from: classes.dex */
public final class PublicSuffixDatabase {

    /* renamed from: e */
    public static final C5508a f18884e = new C5508a(null);

    /* renamed from: f */
    public static final byte[] f18885f = {42};

    /* renamed from: g */
    public static final List<String> f18886g = C5225h.m20396b("*");

    /* renamed from: h */
    public static final PublicSuffixDatabase f18887h = new PublicSuffixDatabase();

    /* renamed from: a */
    public final AtomicBoolean f18888a = new AtomicBoolean(false);

    /* renamed from: b */
    public final CountDownLatch f18889b = new CountDownLatch(1);

    /* renamed from: c */
    public byte[] f18890c;

    /* renamed from: d */
    public byte[] f18891d;

    /* renamed from: okhttp3.internal.publicsuffix.PublicSuffixDatabase$a */
    public static final class C5508a {
        public C5508a() {
        }

        public /* synthetic */ C5508a(C0040d c0040d) {
            this();
        }

        /* renamed from: b */
        public final String m21552b(byte[] bArr, byte[][] bArr2, int i9) {
            int i10;
            boolean z8;
            int iM19790d;
            int iM19790d2;
            int length = bArr.length;
            int i11 = 0;
            while (i11 < length) {
                int i12 = (i11 + length) / 2;
                while (i12 > -1 && bArr[i12] != 10) {
                    i12--;
                }
                int i13 = i12 + 1;
                int i14 = 1;
                while (true) {
                    i10 = i13 + i14;
                    if (bArr[i10] == 10) {
                        break;
                    }
                    i14++;
                }
                int i15 = i10 - i13;
                int i16 = i9;
                boolean z9 = false;
                int i17 = 0;
                int i18 = 0;
                while (true) {
                    if (z9) {
                        iM19790d = 46;
                        z8 = false;
                    } else {
                        z8 = z9;
                        iM19790d = C5057d.m19790d(bArr2[i16][i17], 255);
                    }
                    iM19790d2 = iM19790d - C5057d.m19790d(bArr[i13 + i18], 255);
                    if (iM19790d2 != 0) {
                        break;
                    }
                    i18++;
                    i17++;
                    if (i18 == i15) {
                        break;
                    }
                    if (bArr2[i16].length != i17) {
                        z9 = z8;
                    } else {
                        if (i16 == bArr2.length - 1) {
                            break;
                        }
                        i16++;
                        i17 = -1;
                        z9 = true;
                    }
                }
                if (iM19790d2 >= 0) {
                    if (iM19790d2 <= 0) {
                        int i19 = i15 - i18;
                        int length2 = bArr2[i16].length - i17;
                        int length3 = bArr2.length;
                        for (int i20 = i16 + 1; i20 < length3; i20++) {
                            length2 += bArr2[i20].length;
                        }
                        if (length2 >= i19) {
                            if (length2 <= i19) {
                                Charset charset = StandardCharsets.UTF_8;
                                C0042f.m157d(charset, "UTF_8");
                                return new String(bArr, i13, i15, charset);
                            }
                        }
                    }
                    i11 = i10 + 1;
                }
                length = i13 - 1;
            }
            return null;
        }

        /* renamed from: c */
        public final PublicSuffixDatabase m21553c() {
            return PublicSuffixDatabase.f18887h;
        }
    }

    /* renamed from: b */
    public final List<String> m21546b(List<String> list) throws InterruptedException {
        String str;
        String strM21552b;
        String str2;
        List<String> listM20400f;
        List<String> listM20400f2;
        if (this.f18888a.get() || !this.f18888a.compareAndSet(false, true)) {
            try {
                this.f18889b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            m21549e();
        }
        if (!(this.f18890c != null)) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.".toString());
        }
        int size = list.size();
        byte[][] bArr = new byte[size][];
        for (int i9 = 0; i9 < size; i9++) {
            String str3 = list.get(i9);
            Charset charset = StandardCharsets.UTF_8;
            C0042f.m157d(charset, "UTF_8");
            byte[] bytes = str3.getBytes(charset);
            C0042f.m157d(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i9] = bytes;
        }
        int i10 = 0;
        while (true) {
            str = null;
            if (i10 >= size) {
                strM21552b = null;
                break;
            }
            C5508a c5508a = f18884e;
            byte[] bArr2 = this.f18890c;
            if (bArr2 == null) {
                C0042f.m167n("publicSuffixListBytes");
                bArr2 = null;
            }
            strM21552b = c5508a.m21552b(bArr2, bArr, i10);
            if (strM21552b != null) {
                break;
            }
            i10++;
        }
        if (size > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i11 = 0; i11 < length; i11++) {
                bArr3[i11] = f18885f;
                C5508a c5508a2 = f18884e;
                byte[] bArr4 = this.f18890c;
                if (bArr4 == null) {
                    C0042f.m167n("publicSuffixListBytes");
                    bArr4 = null;
                }
                String strM21552b2 = c5508a2.m21552b(bArr4, bArr3, i11);
                if (strM21552b2 != null) {
                    str2 = strM21552b2;
                    break;
                }
            }
            str2 = null;
        } else {
            str2 = null;
        }
        if (str2 != null) {
            int i12 = size - 1;
            int i13 = 0;
            while (true) {
                if (i13 >= i12) {
                    break;
                }
                C5508a c5508a3 = f18884e;
                byte[] bArr5 = this.f18891d;
                if (bArr5 == null) {
                    C0042f.m167n("publicSuffixExceptionListBytes");
                    bArr5 = null;
                }
                String strM21552b3 = c5508a3.m21552b(bArr5, bArr, i13);
                if (strM21552b3 != null) {
                    str = strM21552b3;
                    break;
                }
                i13++;
            }
        }
        if (str != null) {
            return StringsKt__StringsKt.m20481g0('!' + str, new char[]{ClassUtils.PACKAGE_SEPARATOR_CHAR}, false, 0, 6, null);
        }
        if (strM21552b == null && str2 == null) {
            return f18886g;
        }
        if (strM21552b == null || (listM20400f = StringsKt__StringsKt.m20481g0(strM21552b, new char[]{ClassUtils.PACKAGE_SEPARATOR_CHAR}, false, 0, 6, null)) == null) {
            listM20400f = C5226i.m20400f();
        }
        if (str2 == null || (listM20400f2 = StringsKt__StringsKt.m20481g0(str2, new char[]{ClassUtils.PACKAGE_SEPARATOR_CHAR}, false, 0, 6, null)) == null) {
            listM20400f2 = C5226i.m20400f();
        }
        return listM20400f.size() > listM20400f2.size() ? listM20400f : listM20400f2;
    }

    /* renamed from: c */
    public final String m21547c(String str) throws InterruptedException {
        int size;
        int size2;
        C0042f.m158e(str, "domain");
        String unicode = IDN.toUnicode(str);
        C0042f.m157d(unicode, "unicodeDomain");
        List<String> listM21550f = m21550f(unicode);
        List<String> listM21546b = m21546b(listM21550f);
        if (listM21550f.size() == listM21546b.size() && listM21546b.get(0).charAt(0) != '!') {
            return null;
        }
        if (listM21546b.get(0).charAt(0) == '!') {
            size = listM21550f.size();
            size2 = listM21546b.size();
        } else {
            size = listM21550f.size();
            size2 = listM21546b.size() + 1;
        }
        return C4766h.m18899e(C4766h.m18896b(C5234q.m20422r(m21550f(str)), size - size2), ".", null, null, 0, null, null, 62, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, byte[]] */
    /* renamed from: d */
    public final void m21548d() {
        try {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
            if (resourceAsStream == null) {
                return;
            }
            InterfaceC6324e interfaceC6324eM24256b = C6331l.m24256b(new C6328i(C6331l.m24260f(resourceAsStream)));
            try {
                ref$ObjectRef.element = interfaceC6324eM24256b.mo24226o(interfaceC6324eM24256b.readInt());
                ref$ObjectRef2.element = interfaceC6324eM24256b.mo24226o(interfaceC6324eM24256b.readInt());
                C6319g c6319g = C6319g.f21314a;
                C6791a.m25347a(interfaceC6324eM24256b, null);
                synchronized (this) {
                    T t8 = ref$ObjectRef.element;
                    C0042f.m155b(t8);
                    this.f18890c = (byte[]) t8;
                    T t9 = ref$ObjectRef2.element;
                    C0042f.m155b(t9);
                    this.f18891d = (byte[]) t9;
                }
            } finally {
            }
        } finally {
            this.f18889b.countDown();
        }
    }

    /* renamed from: e */
    public final void m21549e() {
        boolean z8 = false;
        while (true) {
            try {
                try {
                    m21548d();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z8 = true;
                } catch (IOException e9) {
                    C6113j.f20745a.m23447g().m23440j("Failed to read public suffix list", 5, e9);
                    if (z8) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z8) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z8) {
            Thread.currentThread().interrupt();
        }
    }

    /* renamed from: f */
    public final List<String> m21550f(String str) {
        List<String> listM20481g0 = StringsKt__StringsKt.m20481g0(str, new char[]{ClassUtils.PACKAGE_SEPARATOR_CHAR}, false, 0, 6, null);
        return C0042f.m154a(C5234q.m20430z(listM20481g0), "") ? C5234q.m20423s(listM20481g0, 1) : listM20481g0;
    }
}
