package okhttp3;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.C5226i;
import kotlin.collections.C5231n;
import kotlin.text.C5255l;
import kotlin.text.StringsKt__StringsKt;
import p007a6.C0038b;
import p007a6.C0040d;
import p007a6.C0042f;
import p017b6.InterfaceC0691a;
import p098i6.C5057d;
import p203t5.C6317e;
import p221v5.C6487c;

/* renamed from: okhttp3.r */
/* loaded from: classes2.dex */
public final class C5517r implements Iterable<Pair<? extends String, ? extends String>>, InterfaceC0691a {

    /* renamed from: c */
    public static final b f18938c = new b(null);

    /* renamed from: b */
    public final String[] f18939b;

    /* renamed from: okhttp3.r$a */
    public static final class a {

        /* renamed from: a */
        public final List<String> f18940a = new ArrayList(20);

        /* renamed from: a */
        public final a m21631a(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            b bVar = C5517r.f18938c;
            bVar.m21642d(str);
            bVar.m21643e(str2, str);
            m21633c(str, str2);
            return this;
        }

        /* renamed from: b */
        public final a m21632b(String str) {
            C0042f.m158e(str, "line");
            int iM20461M = StringsKt__StringsKt.m20461M(str, ':', 1, false, 4, null);
            if (iM20461M != -1) {
                String strSubstring = str.substring(0, iM20461M);
                C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String strSubstring2 = str.substring(iM20461M + 1);
                C0042f.m157d(strSubstring2, "this as java.lang.String).substring(startIndex)");
                m21633c(strSubstring, strSubstring2);
            } else if (str.charAt(0) == ':') {
                String strSubstring3 = str.substring(1);
                C0042f.m157d(strSubstring3, "this as java.lang.String).substring(startIndex)");
                m21633c("", strSubstring3);
            } else {
                m21633c("", str);
            }
            return this;
        }

        /* renamed from: c */
        public final a m21633c(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            this.f18940a.add(str);
            this.f18940a.add(StringsKt__StringsKt.m20487m0(str2).toString());
            return this;
        }

        /* renamed from: d */
        public final a m21634d(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            C5517r.f18938c.m21642d(str);
            m21633c(str, str2);
            return this;
        }

        /* renamed from: e */
        public final C5517r m21635e() {
            return new C5517r((String[]) this.f18940a.toArray(new String[0]), null);
        }

        /* renamed from: f */
        public final List<String> m21636f() {
            return this.f18940a;
        }

        /* renamed from: g */
        public final a m21637g(String str) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            int i9 = 0;
            while (i9 < this.f18940a.size()) {
                if (C5255l.m20513l(str, this.f18940a.get(i9), true)) {
                    this.f18940a.remove(i9);
                    this.f18940a.remove(i9);
                    i9 -= 2;
                }
                i9 += 2;
            }
            return this;
        }

        /* renamed from: h */
        public final a m21638h(String str, String str2) {
            C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
            C0042f.m158e(str2, "value");
            b bVar = C5517r.f18938c;
            bVar.m21642d(str);
            bVar.m21643e(str2, str);
            m21637g(str);
            m21633c(str, str2);
            return this;
        }
    }

    /* renamed from: okhttp3.r$b */
    public static final class b {
        public b() {
        }

        public /* synthetic */ b(C0040d c0040d) {
            this();
        }

        /* renamed from: d */
        public final void m21642d(String str) {
            if (!(str.length() > 0)) {
                throw new IllegalArgumentException("name is empty".toString());
            }
            int length = str.length();
            for (int i9 = 0; i9 < length; i9++) {
                char cCharAt = str.charAt(i9);
                if (!('!' <= cCharAt && cCharAt < 127)) {
                    throw new IllegalArgumentException(C5057d.m19806t("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i9), str).toString());
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0021  */
        /* renamed from: e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m21643e(String str, String str2) {
            boolean z8;
            int length = str.length();
            for (int i9 = 0; i9 < length; i9++) {
                char cCharAt = str.charAt(i9);
                if (cCharAt == '\t') {
                    z8 = true;
                } else if (!(' ' <= cCharAt && cCharAt < 127)) {
                    z8 = false;
                }
                if (!z8) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(C5057d.m19806t("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i9), str2));
                    sb.append(C5057d.m19767G(str2) ? "" : ": " + str);
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            }
        }

        /* renamed from: f */
        public final String m21644f(String[] strArr, String str) {
            int length = strArr.length - 2;
            int iM24821b = C6487c.m24821b(length, 0, -2);
            if (iM24821b > length) {
                return null;
            }
            while (!C5255l.m20513l(str, strArr[length], true)) {
                if (length == iM24821b) {
                    return null;
                }
                length -= 2;
            }
            return strArr[length + 1];
        }

        /* renamed from: g */
        public final C5517r m21645g(String... strArr) {
            C0042f.m158e(strArr, "namesAndValues");
            int i9 = 0;
            if (!(strArr.length % 2 == 0)) {
                throw new IllegalArgumentException("Expected alternating header names and values".toString());
            }
            String[] strArr2 = (String[]) strArr.clone();
            int length = strArr2.length;
            for (int i10 = 0; i10 < length; i10++) {
                String str = strArr2[i10];
                if (!(str != null)) {
                    throw new IllegalArgumentException("Headers cannot be null".toString());
                }
                strArr2[i10] = StringsKt__StringsKt.m20487m0(str).toString();
            }
            int iM24821b = C6487c.m24821b(0, strArr2.length - 1, 2);
            if (iM24821b >= 0) {
                while (true) {
                    String str2 = strArr2[i9];
                    String str3 = strArr2[i9 + 1];
                    m21642d(str2);
                    m21643e(str3, str2);
                    if (i9 == iM24821b) {
                        break;
                    }
                    i9 += 2;
                }
            }
            return new C5517r(strArr2, null);
        }
    }

    public C5517r(String[] strArr) {
        this.f18939b = strArr;
    }

    public /* synthetic */ C5517r(String[] strArr, C0040d c0040d) {
        this(strArr);
    }

    /* renamed from: a */
    public final String m21626a(String str) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        return f18938c.m21644f(this.f18939b, str);
    }

    /* renamed from: b */
    public final String m21627b(int i9) {
        return this.f18939b[i9 * 2];
    }

    /* renamed from: c */
    public final a m21628c() {
        a aVar = new a();
        C5231n.m20411q(aVar.m21636f(), this.f18939b);
        return aVar;
    }

    /* renamed from: d */
    public final String m21629d(int i9) {
        return this.f18939b[(i9 * 2) + 1];
    }

    /* renamed from: e */
    public final List<String> m21630e(String str) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        int size = size();
        ArrayList arrayList = null;
        for (int i9 = 0; i9 < size; i9++) {
            if (C5255l.m20513l(str, m21627b(i9), true)) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(m21629d(i9));
            }
        }
        if (arrayList == null) {
            return C5226i.m20400f();
        }
        List<String> listUnmodifiableList = Collections.unmodifiableList(arrayList);
        C0042f.m157d(listUnmodifiableList, "{\n      Collections.unmodifiableList(result)\n    }");
        return listUnmodifiableList;
    }

    public boolean equals(Object obj) {
        return (obj instanceof C5517r) && Arrays.equals(this.f18939b, ((C5517r) obj).f18939b);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f18939b);
    }

    @Override // java.lang.Iterable
    public Iterator<Pair<? extends String, ? extends String>> iterator() {
        int size = size();
        Pair[] pairArr = new Pair[size];
        for (int i9 = 0; i9 < size; i9++) {
            pairArr[i9] = C6317e.m24151a(m21627b(i9), m21629d(i9));
        }
        return C0038b.m151a(pairArr);
    }

    public final int size() {
        return this.f18939b.length / 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i9 = 0; i9 < size; i9++) {
            String strM21627b = m21627b(i9);
            String strM21629d = m21629d(i9);
            sb.append(strM21627b);
            sb.append(": ");
            if (C5057d.m19767G(strM21627b)) {
                strM21629d = "██";
            }
            sb.append(strM21629d);
            sb.append("\n");
        }
        String string = sb.toString();
        C0042f.m157d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
