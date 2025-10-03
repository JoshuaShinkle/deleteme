package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.AbstractC5218a;
import kotlin.collections.AbstractC5236s;
import org.apache.commons.lang3.ClassUtils;
import p007a6.C0042f;
import p027c6.C0747c;
import p027c6.C0749e;

/* renamed from: kotlin.text.l */
/* loaded from: classes2.dex */
public class C5255l extends C5254k {
    /* renamed from: h */
    public static final String m20509h(char[] cArr) {
        C0042f.m158e(cArr, "<this>");
        return new String(cArr);
    }

    /* renamed from: i */
    public static final String m20510i(char[] cArr, int i9, int i10) {
        C0042f.m158e(cArr, "<this>");
        AbstractC5218a.f17831b.m20369a(i9, i10, cArr.length);
        return new String(cArr, i9, i10 - i9);
    }

    /* renamed from: j */
    public static final boolean m20511j(String str, String str2, boolean z8) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "suffix");
        return !z8 ? str.endsWith(str2) : m20515n(str, str.length() - str2.length(), str2, 0, str2.length(), true);
    }

    /* renamed from: k */
    public static /* synthetic */ boolean m20512k(String str, String str2, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m20511j(str, str2, z8);
    }

    /* renamed from: l */
    public static final boolean m20513l(String str, String str2, boolean z8) {
        return str == null ? str2 == null : !z8 ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    /* renamed from: m */
    public static final boolean m20514m(CharSequence charSequence) {
        boolean z8;
        C0042f.m158e(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return true;
        }
        Iterable iterableM20455G = StringsKt__StringsKt.m20455G(charSequence);
        if ((iterableM20455G instanceof Collection) && ((Collection) iterableM20455G).isEmpty()) {
            z8 = true;
        } else {
            Iterator it = iterableM20455G.iterator();
            while (it.hasNext()) {
                if (!C5244a.m20496c(charSequence.charAt(((AbstractC5236s) it).nextInt()))) {
                    z8 = false;
                    break;
                }
            }
            z8 = true;
        }
        return z8;
    }

    /* renamed from: n */
    public static final boolean m20515n(String str, int i9, String str2, int i10, int i11, boolean z8) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "other");
        return !z8 ? str.regionMatches(i9, str2, i10, i11) : str.regionMatches(z8, i9, str2, i10, i11);
    }

    /* renamed from: o */
    public static /* synthetic */ boolean m20516o(String str, int i9, String str2, int i10, int i11, boolean z8, int i12, Object obj) {
        if ((i12 & 16) != 0) {
            z8 = false;
        }
        return m20515n(str, i9, str2, i10, i11, z8);
    }

    /* renamed from: p */
    public static final String m20517p(CharSequence charSequence, int i9) {
        C0042f.m158e(charSequence, "<this>");
        if (!(i9 >= 0)) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i9 + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
        }
        if (i9 == 0) {
            return "";
        }
        if (i9 == 1) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            char cCharAt = charSequence.charAt(0);
            char[] cArr = new char[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                cArr[i10] = cCharAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(charSequence.length() * i9);
        AbstractC5236s abstractC5236sM3613d = new C0747c(1, i9).iterator();
        while (abstractC5236sM3613d.hasNext()) {
            abstractC5236sM3613d.nextInt();
            sb.append(charSequence);
        }
        String string = sb.toString();
        C0042f.m155b(string);
        return string;
    }

    /* renamed from: q */
    public static final String m20518q(String str, char c9, char c10, boolean z8) {
        C0042f.m158e(str, "<this>");
        if (!z8) {
            String strReplace = str.replace(c9, c10);
            C0042f.m157d(strReplace, "replace(...)");
            return strReplace;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if (C5245b.m20497d(cCharAt, c9, z8)) {
                cCharAt = c10;
            }
            sb.append(cCharAt);
        }
        String string = sb.toString();
        C0042f.m157d(string, "toString(...)");
        return string;
    }

    /* renamed from: r */
    public static final String m20519r(String str, String str2, String str3, boolean z8) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "oldValue");
        C0042f.m158e(str3, "newValue");
        int i9 = 0;
        int iM20458J = StringsKt__StringsKt.m20458J(str, str2, 0, z8);
        if (iM20458J < 0) {
            return str;
        }
        int length = str2.length();
        int iM3621b = C0749e.m3621b(length, 1);
        int length2 = (str.length() - length) + str3.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        do {
            sb.append((CharSequence) str, i9, iM20458J);
            sb.append(str3);
            i9 = iM20458J + length;
            if (iM20458J >= str.length()) {
                break;
            }
            iM20458J = StringsKt__StringsKt.m20458J(str, str2, iM20458J + iM3621b, z8);
        } while (iM20458J > 0);
        sb.append((CharSequence) str, i9, str.length());
        String string = sb.toString();
        C0042f.m157d(string, "toString(...)");
        return string;
    }

    /* renamed from: s */
    public static /* synthetic */ String m20520s(String str, char c9, char c10, boolean z8, int i9, Object obj) {
        if ((i9 & 4) != 0) {
            z8 = false;
        }
        return m20518q(str, c9, c10, z8);
    }

    /* renamed from: t */
    public static /* synthetic */ String m20521t(String str, String str2, String str3, boolean z8, int i9, Object obj) {
        if ((i9 & 4) != 0) {
            z8 = false;
        }
        return m20519r(str, str2, str3, z8);
    }

    /* renamed from: u */
    public static final boolean m20522u(String str, String str2, int i9, boolean z8) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "prefix");
        return !z8 ? str.startsWith(str2, i9) : m20515n(str, i9, str2, 0, str2.length(), z8);
    }

    /* renamed from: v */
    public static final boolean m20523v(String str, String str2, boolean z8) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(str2, "prefix");
        return !z8 ? str.startsWith(str2) : m20515n(str, 0, str2, 0, str2.length(), z8);
    }

    /* renamed from: w */
    public static /* synthetic */ boolean m20524w(String str, String str2, int i9, boolean z8, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z8 = false;
        }
        return m20522u(str, str2, i9, z8);
    }

    /* renamed from: x */
    public static /* synthetic */ boolean m20525x(String str, String str2, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m20523v(str, str2, z8);
    }
}
