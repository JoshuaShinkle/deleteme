package okhttp3;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.C5255l;
import p007a6.C0040d;
import p007a6.C0042f;
import p221v5.C6487c;

/* renamed from: okhttp3.u */
/* loaded from: classes2.dex */
public final class C5520u {

    /* renamed from: e */
    public static final a f18962e = new a(null);

    /* renamed from: f */
    public static final Pattern f18963f = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* renamed from: g */
    public static final Pattern f18964g = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* renamed from: a */
    public final String f18965a;

    /* renamed from: b */
    public final String f18966b;

    /* renamed from: c */
    public final String f18967c;

    /* renamed from: d */
    public final String[] f18968d;

    /* renamed from: okhttp3.u$a */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(C0040d c0040d) {
            this();
        }

        /* renamed from: a */
        public final C5520u m21713a(String str) {
            C0042f.m158e(str, "<this>");
            Matcher matcher = C5520u.f18963f.matcher(str);
            if (!matcher.lookingAt()) {
                throw new IllegalArgumentException(("No subtype found for: \"" + str + '\"').toString());
            }
            String strGroup = matcher.group(1);
            C0042f.m157d(strGroup, "typeSubtype.group(1)");
            Locale locale = Locale.US;
            C0042f.m157d(locale, "US");
            String lowerCase = strGroup.toLowerCase(locale);
            C0042f.m157d(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            String strGroup2 = matcher.group(2);
            C0042f.m157d(strGroup2, "typeSubtype.group(2)");
            C0042f.m157d(locale, "US");
            String lowerCase2 = strGroup2.toLowerCase(locale);
            C0042f.m157d(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
            ArrayList arrayList = new ArrayList();
            Matcher matcher2 = C5520u.f18964g.matcher(str);
            int iEnd = matcher.end();
            while (iEnd < str.length()) {
                matcher2.region(iEnd, str.length());
                if (!matcher2.lookingAt()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Parameter is not formatted correctly: \"");
                    String strSubstring = str.substring(iEnd);
                    C0042f.m157d(strSubstring, "this as java.lang.String).substring(startIndex)");
                    sb.append(strSubstring);
                    sb.append("\" for: \"");
                    sb.append(str);
                    sb.append('\"');
                    throw new IllegalArgumentException(sb.toString().toString());
                }
                String strGroup3 = matcher2.group(1);
                if (strGroup3 == null) {
                    iEnd = matcher2.end();
                } else {
                    String strGroup4 = matcher2.group(2);
                    if (strGroup4 == null) {
                        strGroup4 = matcher2.group(3);
                    } else if (C5255l.m20525x(strGroup4, "'", false, 2, null) && C5255l.m20512k(strGroup4, "'", false, 2, null) && strGroup4.length() > 2) {
                        strGroup4 = strGroup4.substring(1, strGroup4.length() - 1);
                        C0042f.m157d(strGroup4, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    arrayList.add(strGroup3);
                    arrayList.add(strGroup4);
                    iEnd = matcher2.end();
                }
            }
            return new C5520u(str, lowerCase, lowerCase2, (String[]) arrayList.toArray(new String[0]), null);
        }

        /* renamed from: b */
        public final C5520u m21714b(String str) {
            C0042f.m158e(str, "<this>");
            try {
                return m21713a(str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    public C5520u(String str, String str2, String str3, String[] strArr) {
        this.f18965a = str;
        this.f18966b = str2;
        this.f18967c = str3;
        this.f18968d = strArr;
    }

    public /* synthetic */ C5520u(String str, String str2, String str3, String[] strArr, C0040d c0040d) {
        this(str, str2, str3, strArr);
    }

    /* renamed from: d */
    public static /* synthetic */ Charset m21708d(C5520u c5520u, Charset charset, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            charset = null;
        }
        return c5520u.m21710c(charset);
    }

    /* renamed from: f */
    public static final C5520u m21709f(String str) {
        return f18962e.m21714b(str);
    }

    /* renamed from: c */
    public final Charset m21710c(Charset charset) {
        String strM21711e = m21711e("charset");
        if (strM21711e == null) {
            return charset;
        }
        try {
            return Charset.forName(strM21711e);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    /* renamed from: e */
    public final String m21711e(String str) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        int i9 = 0;
        int iM24821b = C6487c.m24821b(0, this.f18968d.length - 1, 2);
        if (iM24821b < 0) {
            return null;
        }
        while (!C5255l.m20513l(this.f18968d[i9], str, true)) {
            if (i9 == iM24821b) {
                return null;
            }
            i9 += 2;
        }
        return this.f18968d[i9 + 1];
    }

    public boolean equals(Object obj) {
        return (obj instanceof C5520u) && C0042f.m154a(((C5520u) obj).f18965a, this.f18965a);
    }

    /* renamed from: g */
    public final String m21712g() {
        return this.f18966b;
    }

    public int hashCode() {
        return this.f18965a.hashCode();
    }

    public String toString() {
        return this.f18965a;
    }
}
