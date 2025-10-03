package okhttp3;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.collections.C5226i;
import kotlin.text.C5255l;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import org.apache.commons.p159io.IOUtils;
import p007a6.C0040d;
import p007a6.C0042f;
import p098i6.C5054a;
import p098i6.C5057d;
import p139m6.C5330c;

/* renamed from: okhttp3.l */
/* loaded from: classes2.dex */
public final class C5511l {

    /* renamed from: j */
    public static final a f18908j = new a(null);

    /* renamed from: k */
    public static final Pattern f18909k = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* renamed from: l */
    public static final Pattern f18910l = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* renamed from: m */
    public static final Pattern f18911m = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* renamed from: n */
    public static final Pattern f18912n = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* renamed from: a */
    public final String f18913a;

    /* renamed from: b */
    public final String f18914b;

    /* renamed from: c */
    public final long f18915c;

    /* renamed from: d */
    public final String f18916d;

    /* renamed from: e */
    public final String f18917e;

    /* renamed from: f */
    public final boolean f18918f;

    /* renamed from: g */
    public final boolean f18919g;

    /* renamed from: h */
    public final boolean f18920h;

    /* renamed from: i */
    public final boolean f18921i;

    /* renamed from: okhttp3.l$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x003d  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final int m21577a(String str, int i9, int i10, boolean z8) {
            boolean z9;
            while (i9 < i10) {
                char cCharAt = str.charAt(i9);
                if ((cCharAt >= ' ' || cCharAt == '\t') && cCharAt < 127) {
                    z9 = false;
                    if ('0' <= cCharAt && cCharAt < ':') {
                        z9 = true;
                    } else {
                        if (!('a' <= cCharAt && cCharAt < '{')) {
                            if (('A' <= cCharAt && cCharAt < '[') || cCharAt == ':') {
                            }
                        }
                    }
                }
                if (z9 == (!z8)) {
                    return i9;
                }
                i9++;
            }
            return i10;
        }

        /* renamed from: b */
        public final boolean m21578b(String str, String str2) {
            if (C0042f.m154a(str, str2)) {
                return true;
            }
            return C5255l.m20512k(str, str2, false, 2, null) && str.charAt((str.length() - str2.length()) - 1) == '.' && !C5057d.m19795i(str);
        }

        /* renamed from: c */
        public final C5511l m21579c(C5518s c5518s, String str) {
            C0042f.m158e(c5518s, "url");
            C0042f.m158e(str, "setCookie");
            return m21580d(System.currentTimeMillis(), c5518s, str);
        }

        /* JADX WARN: Removed duplicated region for block: B:46:0x00dc A[PHI: r1
          0x00dc: PHI (r1v24 long) = (r1v8 long), (r1v12 long) binds: [B:45:0x00da, B:56:0x0102] A[DONT_GENERATE, DONT_INLINE]] */
        /* renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final C5511l m21580d(long j9, C5518s c5518s, String str) throws NumberFormatException {
            long j10;
            long j11;
            C5511l c5511l;
            String str2;
            String str3;
            C0042f.m158e(c5518s, "url");
            C0042f.m158e(str, "setCookie");
            int iM19804r = C5057d.m19804r(str, ';', 0, 0, 6, null);
            int iM19804r2 = C5057d.m19804r(str, '=', 0, iM19804r, 2, null);
            if (iM19804r2 == iM19804r) {
                return null;
            }
            String strM19784X = C5057d.m19784X(str, 0, iM19804r2, 1, null);
            if ((strM19784X.length() == 0) || C5057d.m19811y(strM19784X) != -1) {
                return null;
            }
            String strM19783W = C5057d.m19783W(str, iM19804r2 + 1, iM19804r);
            if (C5057d.m19811y(strM19783W) != -1) {
                return null;
            }
            int i9 = iM19804r + 1;
            int length = str.length();
            String strM21582f = null;
            String str4 = null;
            boolean z8 = false;
            boolean z9 = false;
            boolean z10 = false;
            boolean z11 = true;
            long jM21584h = -1;
            long jM21583g = 253402300799999L;
            while (i9 < length) {
                int iM19802p = C5057d.m19802p(str, ';', i9, length);
                int iM19802p2 = C5057d.m19802p(str, '=', i9, iM19802p);
                String strM19783W2 = C5057d.m19783W(str, i9, iM19802p2);
                String strM19783W3 = iM19802p2 < iM19802p ? C5057d.m19783W(str, iM19802p2 + 1, iM19802p) : "";
                if (C5255l.m20513l(strM19783W2, "expires", true)) {
                    try {
                        jM21583g = m21583g(strM19783W3, 0, strM19783W3.length());
                        z10 = true;
                    } catch (NumberFormatException | IllegalArgumentException unused) {
                    }
                } else if (C5255l.m20513l(strM19783W2, "max-age", true)) {
                    jM21584h = m21584h(strM19783W3);
                    z10 = true;
                } else if (C5255l.m20513l(strM19783W2, "domain", true)) {
                    strM21582f = m21582f(strM19783W3);
                    z11 = false;
                } else if (C5255l.m20513l(strM19783W2, "path", true)) {
                    str4 = strM19783W3;
                } else if (C5255l.m20513l(strM19783W2, "secure", true)) {
                    z8 = true;
                } else if (C5255l.m20513l(strM19783W2, "httponly", true)) {
                    z9 = true;
                }
                i9 = iM19802p + 1;
            }
            long j12 = Long.MIN_VALUE;
            if (jM21584h != Long.MIN_VALUE) {
                if (jM21584h != -1) {
                    j12 = j9 + (jM21584h <= 9223372036854775L ? jM21584h * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT : Long.MAX_VALUE);
                    if (j12 >= j9) {
                        j11 = 253402300799999L;
                        if (j12 <= 253402300799999L) {
                            j10 = j12;
                        }
                    } else {
                        j11 = 253402300799999L;
                    }
                    j10 = j11;
                } else {
                    j10 = jM21583g;
                }
            }
            String strM21653h = c5518s.m21653h();
            if (strM21582f == null) {
                str2 = strM21653h;
                c5511l = null;
            } else {
                if (!m21578b(strM21653h, strM21582f)) {
                    return null;
                }
                c5511l = null;
                str2 = strM21582f;
            }
            if (strM21653h.length() != str2.length() && PublicSuffixDatabase.f18884e.m21553c().m21547c(str2) == null) {
                return c5511l;
            }
            String strSubstring = "/";
            String str5 = str4;
            if (str5 == null || !C5255l.m20525x(str5, "/", false, 2, c5511l)) {
                String strM21649d = c5518s.m21649d();
                int iM20466R = StringsKt__StringsKt.m20466R(strM21649d, IOUtils.DIR_SEPARATOR_UNIX, 0, false, 6, null);
                if (iM20466R != 0) {
                    strSubstring = strM21649d.substring(0, iM20466R);
                    C0042f.m157d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                str3 = strSubstring;
            } else {
                str3 = str5;
            }
            return new C5511l(strM19784X, strM19783W, j10, str2, str3, z8, z9, z10, z11, null);
        }

        /* renamed from: e */
        public final List<C5511l> m21581e(C5518s c5518s, C5517r c5517r) {
            C0042f.m158e(c5518s, "url");
            C0042f.m158e(c5517r, "headers");
            List<String> listM21630e = c5517r.m21630e(HttpHeaders.SET_COOKIE);
            int size = listM21630e.size();
            ArrayList arrayList = null;
            for (int i9 = 0; i9 < size; i9++) {
                C5511l c5511lM21579c = m21579c(c5518s, listM21630e.get(i9));
                if (c5511lM21579c != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(c5511lM21579c);
                }
            }
            if (arrayList == null) {
                return C5226i.m20400f();
            }
            List<C5511l> listUnmodifiableList = Collections.unmodifiableList(arrayList);
            C0042f.m157d(listUnmodifiableList, "{\n        Collections.un…ableList(cookies)\n      }");
            return listUnmodifiableList;
        }

        /* renamed from: f */
        public final String m21582f(String str) {
            if (!(!C5255l.m20512k(str, ".", false, 2, null))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            String strM19759e = C5054a.m19759e(StringsKt__StringsKt.m20476b0(str, "."));
            if (strM19759e != null) {
                return strM19759e;
            }
            throw new IllegalArgumentException();
        }

        /* renamed from: g */
        public final long m21583g(String str, int i9, int i10) throws NumberFormatException {
            int iM21577a = m21577a(str, i9, i10, false);
            Matcher matcher = C5511l.f18912n.matcher(str);
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int iM20462N = -1;
            int i14 = -1;
            int i15 = -1;
            while (iM21577a < i10) {
                int iM21577a2 = m21577a(str, iM21577a + 1, i10, true);
                matcher.region(iM21577a, iM21577a2);
                if (i12 == -1 && matcher.usePattern(C5511l.f18912n).matches()) {
                    String strGroup = matcher.group(1);
                    C0042f.m157d(strGroup, "matcher.group(1)");
                    i12 = Integer.parseInt(strGroup);
                    String strGroup2 = matcher.group(2);
                    C0042f.m157d(strGroup2, "matcher.group(2)");
                    i14 = Integer.parseInt(strGroup2);
                    String strGroup3 = matcher.group(3);
                    C0042f.m157d(strGroup3, "matcher.group(3)");
                    i15 = Integer.parseInt(strGroup3);
                } else if (i13 == -1 && matcher.usePattern(C5511l.f18911m).matches()) {
                    String strGroup4 = matcher.group(1);
                    C0042f.m157d(strGroup4, "matcher.group(1)");
                    i13 = Integer.parseInt(strGroup4);
                } else if (iM20462N == -1 && matcher.usePattern(C5511l.f18910l).matches()) {
                    String strGroup5 = matcher.group(1);
                    C0042f.m157d(strGroup5, "matcher.group(1)");
                    Locale locale = Locale.US;
                    C0042f.m157d(locale, "US");
                    String lowerCase = strGroup5.toLowerCase(locale);
                    C0042f.m157d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    String strPattern = C5511l.f18910l.pattern();
                    C0042f.m157d(strPattern, "MONTH_PATTERN.pattern()");
                    iM20462N = StringsKt__StringsKt.m20462N(strPattern, lowerCase, 0, false, 6, null) / 4;
                } else if (i11 == -1 && matcher.usePattern(C5511l.f18909k).matches()) {
                    String strGroup6 = matcher.group(1);
                    C0042f.m157d(strGroup6, "matcher.group(1)");
                    i11 = Integer.parseInt(strGroup6);
                }
                iM21577a = m21577a(str, iM21577a2 + 1, i10, false);
            }
            if (70 <= i11 && i11 < 100) {
                i11 += 1900;
            }
            if (i11 >= 0 && i11 < 70) {
                i11 += 2000;
            }
            if (!(i11 >= 1601)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(iM20462N != -1)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(1 <= i13 && i13 < 32)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(i12 >= 0 && i12 < 24)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(i14 >= 0 && i14 < 60)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (!(i15 >= 0 && i15 < 60)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar(C5057d.f17448f);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i11);
            gregorianCalendar.set(2, iM20462N - 1);
            gregorianCalendar.set(5, i13);
            gregorianCalendar.set(11, i12);
            gregorianCalendar.set(12, i14);
            gregorianCalendar.set(13, i15);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }

        /* renamed from: h */
        public final long m21584h(String str) throws NumberFormatException {
            try {
                long j9 = Long.parseLong(str);
                if (j9 <= 0) {
                    return Long.MIN_VALUE;
                }
                return j9;
            } catch (NumberFormatException e9) {
                if (new Regex("-?\\d+").m20441a(str)) {
                    return C5255l.m20525x(str, "-", false, 2, null) ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
                throw e9;
            }
        }
    }

    public C5511l(String str, String str2, long j9, String str3, String str4, boolean z8, boolean z9, boolean z10, boolean z11) {
        this.f18913a = str;
        this.f18914b = str2;
        this.f18915c = j9;
        this.f18916d = str3;
        this.f18917e = str4;
        this.f18918f = z8;
        this.f18919g = z9;
        this.f18920h = z10;
        this.f18921i = z11;
    }

    public /* synthetic */ C5511l(String str, String str2, long j9, String str3, String str4, boolean z8, boolean z9, boolean z10, boolean z11, C0040d c0040d) {
        this(str, str2, j9, str3, str4, z8, z9, z10, z11);
    }

    /* renamed from: e */
    public final String m21574e() {
        return this.f18913a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5511l) {
            C5511l c5511l = (C5511l) obj;
            if (C0042f.m154a(c5511l.f18913a, this.f18913a) && C0042f.m154a(c5511l.f18914b, this.f18914b) && c5511l.f18915c == this.f18915c && C0042f.m154a(c5511l.f18916d, this.f18916d) && C0042f.m154a(c5511l.f18917e, this.f18917e) && c5511l.f18918f == this.f18918f && c5511l.f18919g == this.f18919g && c5511l.f18920h == this.f18920h && c5511l.f18921i == this.f18921i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public final String m21575f(boolean z8) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f18913a);
        sb.append('=');
        sb.append(this.f18914b);
        if (this.f18920h) {
            if (this.f18915c == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(C5330c.m20935b(new Date(this.f18915c)));
            }
        }
        if (!this.f18921i) {
            sb.append("; domain=");
            if (z8) {
                sb.append(".");
            }
            sb.append(this.f18916d);
        }
        sb.append("; path=");
        sb.append(this.f18917e);
        if (this.f18918f) {
            sb.append("; secure");
        }
        if (this.f18919g) {
            sb.append("; httponly");
        }
        String string = sb.toString();
        C0042f.m157d(string, "toString()");
        return string;
    }

    /* renamed from: g */
    public final String m21576g() {
        return this.f18914b;
    }

    public int hashCode() {
        return ((((((((((((((((527 + this.f18913a.hashCode()) * 31) + this.f18914b.hashCode()) * 31) + Long.hashCode(this.f18915c)) * 31) + this.f18916d.hashCode()) * 31) + this.f18917e.hashCode()) * 31) + Boolean.hashCode(this.f18918f)) * 31) + Boolean.hashCode(this.f18919g)) * 31) + Boolean.hashCode(this.f18920h)) * 31) + Boolean.hashCode(this.f18921i);
    }

    public String toString() {
        return m21575f(false);
    }
}
