package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.AbstractC5236s;
import kotlin.collections.C5222e;
import kotlin.collections.C5223f;
import kotlin.collections.C5225h;
import kotlin.collections.C5227j;
import kotlin.collections.C5234q;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;
import p007a6.C0042f;
import p027c6.C0745a;
import p027c6.C0747c;
import p027c6.C0749e;
import p058e6.C4766h;
import p058e6.InterfaceC4761c;
import p203t5.C6317e;
import p257z5.InterfaceC6832b;
import p257z5.InterfaceC6833c;

/* loaded from: classes2.dex */
public class StringsKt__StringsKt extends C5255l {
    /* renamed from: A */
    public static final boolean m20449A(CharSequence charSequence, CharSequence charSequence2, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(charSequence2, "other");
        if (charSequence2 instanceof String) {
            if (m20462N(charSequence, (String) charSequence2, 0, z8, 2, null) >= 0) {
                return true;
            }
        } else if (m20460L(charSequence, charSequence2, 0, charSequence.length(), z8, false, 16, null) >= 0) {
            return true;
        }
        return false;
    }

    /* renamed from: B */
    public static /* synthetic */ boolean m20450B(CharSequence charSequence, char c9, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m20489z(charSequence, c9, z8);
    }

    /* renamed from: C */
    public static /* synthetic */ boolean m20451C(CharSequence charSequence, CharSequence charSequence2, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m20449A(charSequence, charSequence2, z8);
    }

    /* renamed from: D */
    public static final boolean m20452D(CharSequence charSequence, CharSequence charSequence2, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(charSequence2, "suffix");
        return (!z8 && (charSequence instanceof String) && (charSequence2 instanceof String)) ? C5255l.m20512k((String) charSequence, (String) charSequence2, false, 2, null) : m20475a0(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z8);
    }

    /* renamed from: E */
    public static /* synthetic */ boolean m20453E(CharSequence charSequence, CharSequence charSequence2, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m20452D(charSequence, charSequence2, z8);
    }

    /* renamed from: F */
    public static final Pair<Integer, String> m20454F(CharSequence charSequence, Collection<String> collection, int i9, boolean z8, boolean z9) {
        Object next;
        Object next2;
        if (!z8 && collection.size() == 1) {
            String str = (String) C5234q.m20414C(collection);
            int iM20462N = !z9 ? m20462N(charSequence, str, i9, false, 4, null) : m20467S(charSequence, str, i9, false, 4, null);
            if (iM20462N < 0) {
                return null;
            }
            return C6317e.m24151a(Integer.valueOf(iM20462N), str);
        }
        C0745a c0747c = !z9 ? new C0747c(C0749e.m3621b(i9, 0), charSequence.length()) : C0749e.m3624e(C0749e.m3622c(i9, m20456H(charSequence)), 0);
        if (charSequence instanceof String) {
            int iM3610a = c0747c.m3610a();
            int iM3611b = c0747c.m3611b();
            int iM3612c = c0747c.m3612c();
            if ((iM3612c > 0 && iM3610a <= iM3611b) || (iM3612c < 0 && iM3611b <= iM3610a)) {
                while (true) {
                    Iterator<T> it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        String str2 = (String) next2;
                        if (C5255l.m20515n(str2, 0, (String) charSequence, iM3610a, str2.length(), z8)) {
                            break;
                        }
                    }
                    String str3 = (String) next2;
                    if (str3 == null) {
                        if (iM3610a == iM3611b) {
                            break;
                        }
                        iM3610a += iM3612c;
                    } else {
                        return C6317e.m24151a(Integer.valueOf(iM3610a), str3);
                    }
                }
            }
        } else {
            int iM3610a2 = c0747c.m3610a();
            int iM3611b2 = c0747c.m3611b();
            int iM3612c2 = c0747c.m3612c();
            if ((iM3612c2 > 0 && iM3610a2 <= iM3611b2) || (iM3612c2 < 0 && iM3611b2 <= iM3610a2)) {
                while (true) {
                    Iterator<T> it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        String str4 = (String) next;
                        if (m20475a0(str4, 0, charSequence, iM3610a2, str4.length(), z8)) {
                            break;
                        }
                    }
                    String str5 = (String) next;
                    if (str5 == null) {
                        if (iM3610a2 == iM3611b2) {
                            break;
                        }
                        iM3610a2 += iM3612c2;
                    } else {
                        return C6317e.m24151a(Integer.valueOf(iM3610a2), str5);
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: G */
    public static final C0747c m20455G(CharSequence charSequence) {
        C0042f.m158e(charSequence, "<this>");
        return new C0747c(0, charSequence.length() - 1);
    }

    /* renamed from: H */
    public static final int m20456H(CharSequence charSequence) {
        C0042f.m158e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    /* renamed from: I */
    public static final int m20457I(CharSequence charSequence, char c9, int i9, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        return (z8 || !(charSequence instanceof String)) ? m20463O(charSequence, new char[]{c9}, i9, z8) : ((String) charSequence).indexOf(c9, i9);
    }

    /* renamed from: J */
    public static final int m20458J(CharSequence charSequence, String str, int i9, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(str, "string");
        return (z8 || !(charSequence instanceof String)) ? m20460L(charSequence, str, i9, charSequence.length(), z8, false, 16, null) : ((String) charSequence).indexOf(str, i9);
    }

    /* renamed from: K */
    public static final int m20459K(CharSequence charSequence, CharSequence charSequence2, int i9, int i10, boolean z8, boolean z9) {
        C0745a c0747c = !z9 ? new C0747c(C0749e.m3621b(i9, 0), C0749e.m3622c(i10, charSequence.length())) : C0749e.m3624e(C0749e.m3622c(i9, m20456H(charSequence)), C0749e.m3621b(i10, 0));
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int iM3610a = c0747c.m3610a();
            int iM3611b = c0747c.m3611b();
            int iM3612c = c0747c.m3612c();
            if ((iM3612c <= 0 || iM3610a > iM3611b) && (iM3612c >= 0 || iM3611b > iM3610a)) {
                return -1;
            }
            while (!C5255l.m20515n((String) charSequence2, 0, (String) charSequence, iM3610a, charSequence2.length(), z8)) {
                if (iM3610a == iM3611b) {
                    return -1;
                }
                iM3610a += iM3612c;
            }
            return iM3610a;
        }
        int iM3610a2 = c0747c.m3610a();
        int iM3611b2 = c0747c.m3611b();
        int iM3612c2 = c0747c.m3612c();
        if ((iM3612c2 <= 0 || iM3610a2 > iM3611b2) && (iM3612c2 >= 0 || iM3611b2 > iM3610a2)) {
            return -1;
        }
        while (!m20475a0(charSequence2, 0, charSequence, iM3610a2, charSequence2.length(), z8)) {
            if (iM3610a2 == iM3611b2) {
                return -1;
            }
            iM3610a2 += iM3612c2;
        }
        return iM3610a2;
    }

    /* renamed from: L */
    public static /* synthetic */ int m20460L(CharSequence charSequence, CharSequence charSequence2, int i9, int i10, boolean z8, boolean z9, int i11, Object obj) {
        if ((i11 & 16) != 0) {
            z9 = false;
        }
        return m20459K(charSequence, charSequence2, i9, i10, z8, z9);
    }

    /* renamed from: M */
    public static /* synthetic */ int m20461M(CharSequence charSequence, char c9, int i9, boolean z8, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            i9 = 0;
        }
        if ((i10 & 4) != 0) {
            z8 = false;
        }
        return m20457I(charSequence, c9, i9, z8);
    }

    /* renamed from: N */
    public static /* synthetic */ int m20462N(CharSequence charSequence, String str, int i9, boolean z8, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            i9 = 0;
        }
        if ((i10 & 4) != 0) {
            z8 = false;
        }
        return m20458J(charSequence, str, i9, z8);
    }

    /* renamed from: O */
    public static final int m20463O(CharSequence charSequence, char[] cArr, int i9, boolean z8) {
        boolean z9;
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(cArr, "chars");
        if (!z8 && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(C5223f.m20390m(cArr), i9);
        }
        AbstractC5236s it = new C0747c(C0749e.m3621b(i9, 0), m20456H(charSequence)).iterator();
        while (it.hasNext()) {
            int iNextInt = it.nextInt();
            char cCharAt = charSequence.charAt(iNextInt);
            int length = cArr.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    z9 = false;
                    break;
                }
                if (C5245b.m20497d(cArr[i10], cCharAt, z8)) {
                    z9 = true;
                    break;
                }
                i10++;
            }
            if (z9) {
                return iNextInt;
            }
        }
        return -1;
    }

    /* renamed from: P */
    public static final int m20464P(CharSequence charSequence, char c9, int i9, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        return (z8 || !(charSequence instanceof String)) ? m20468T(charSequence, new char[]{c9}, i9, z8) : ((String) charSequence).lastIndexOf(c9, i9);
    }

    /* renamed from: Q */
    public static final int m20465Q(CharSequence charSequence, String str, int i9, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(str, "string");
        return (z8 || !(charSequence instanceof String)) ? m20459K(charSequence, str, i9, 0, z8, true) : ((String) charSequence).lastIndexOf(str, i9);
    }

    /* renamed from: R */
    public static /* synthetic */ int m20466R(CharSequence charSequence, char c9, int i9, boolean z8, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            i9 = m20456H(charSequence);
        }
        if ((i10 & 4) != 0) {
            z8 = false;
        }
        return m20464P(charSequence, c9, i9, z8);
    }

    /* renamed from: S */
    public static /* synthetic */ int m20467S(CharSequence charSequence, String str, int i9, boolean z8, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            i9 = m20456H(charSequence);
        }
        if ((i10 & 4) != 0) {
            z8 = false;
        }
        return m20465Q(charSequence, str, i9, z8);
    }

    /* renamed from: T */
    public static final int m20468T(CharSequence charSequence, char[] cArr, int i9, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(cArr, "chars");
        if (!z8 && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(C5223f.m20390m(cArr), i9);
        }
        for (int iM3622c = C0749e.m3622c(i9, m20456H(charSequence)); -1 < iM3622c; iM3622c--) {
            char cCharAt = charSequence.charAt(iM3622c);
            int length = cArr.length;
            boolean z9 = false;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    break;
                }
                if (C5245b.m20497d(cArr[i10], cCharAt, z8)) {
                    z9 = true;
                    break;
                }
                i10++;
            }
            if (z9) {
                return iM3622c;
            }
        }
        return -1;
    }

    /* renamed from: U */
    public static final InterfaceC4761c<String> m20469U(CharSequence charSequence) {
        C0042f.m158e(charSequence, "<this>");
        return m20483i0(charSequence, new String[]{IOUtils.LINE_SEPARATOR_WINDOWS, "\n", StringUtils.f19107CR}, false, 0, 6, null);
    }

    /* renamed from: V */
    public static final List<String> m20470V(CharSequence charSequence) {
        C0042f.m158e(charSequence, "<this>");
        return C4766h.m18901g(m20469U(charSequence));
    }

    /* renamed from: W */
    public static final InterfaceC4761c<C0747c> m20471W(CharSequence charSequence, final char[] cArr, int i9, final boolean z8, int i10) {
        m20478d0(i10);
        return new C5247d(charSequence, i9, i10, new InterfaceC6833c<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // p257z5.InterfaceC6833c
            /* renamed from: c */
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> mo20490c(CharSequence charSequence2, Integer num) {
                return m20491d(charSequence2, num.intValue());
            }

            /* renamed from: d */
            public final Pair<Integer, Integer> m20491d(CharSequence charSequence2, int i11) {
                C0042f.m158e(charSequence2, "$this$$receiver");
                int iM20463O = StringsKt__StringsKt.m20463O(charSequence2, cArr, i11, z8);
                if (iM20463O < 0) {
                    return null;
                }
                return C6317e.m24151a(Integer.valueOf(iM20463O), 1);
            }
        });
    }

    /* renamed from: X */
    public static final InterfaceC4761c<C0747c> m20472X(CharSequence charSequence, String[] strArr, int i9, final boolean z8, int i10) {
        m20478d0(i10);
        final List listM20379b = C5222e.m20379b(strArr);
        return new C5247d(charSequence, i9, i10, new InterfaceC6833c<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // p257z5.InterfaceC6833c
            /* renamed from: c */
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> mo20490c(CharSequence charSequence2, Integer num) {
                return m20492d(charSequence2, num.intValue());
            }

            /* renamed from: d */
            public final Pair<Integer, Integer> m20492d(CharSequence charSequence2, int i11) {
                C0042f.m158e(charSequence2, "$this$$receiver");
                Pair pairM20454F = StringsKt__StringsKt.m20454F(charSequence2, listM20379b, i11, z8, false);
                if (pairM20454F != null) {
                    return C6317e.m24151a(pairM20454F.m20348c(), Integer.valueOf(((String) pairM20454F.m20349d()).length()));
                }
                return null;
            }
        });
    }

    /* renamed from: Y */
    public static /* synthetic */ InterfaceC4761c m20473Y(CharSequence charSequence, char[] cArr, int i9, boolean z8, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i9 = 0;
        }
        if ((i11 & 4) != 0) {
            z8 = false;
        }
        if ((i11 & 8) != 0) {
            i10 = 0;
        }
        return m20471W(charSequence, cArr, i9, z8, i10);
    }

    /* renamed from: Z */
    public static /* synthetic */ InterfaceC4761c m20474Z(CharSequence charSequence, String[] strArr, int i9, boolean z8, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i9 = 0;
        }
        if ((i11 & 4) != 0) {
            z8 = false;
        }
        if ((i11 & 8) != 0) {
            i10 = 0;
        }
        return m20472X(charSequence, strArr, i9, z8, i10);
    }

    /* renamed from: a0 */
    public static final boolean m20475a0(CharSequence charSequence, int i9, CharSequence charSequence2, int i10, int i11, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(charSequence2, "other");
        if (i10 < 0 || i9 < 0 || i9 > charSequence.length() - i11 || i10 > charSequence2.length() - i11) {
            return false;
        }
        for (int i12 = 0; i12 < i11; i12++) {
            if (!C5245b.m20497d(charSequence.charAt(i9 + i12), charSequence2.charAt(i10 + i12), z8)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b0 */
    public static final String m20476b0(String str, CharSequence charSequence) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(charSequence, "prefix");
        if (!m20485k0(str, charSequence, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(charSequence.length());
        C0042f.m157d(strSubstring, "substring(...)");
        return strSubstring;
    }

    /* renamed from: c0 */
    public static final String m20477c0(String str, CharSequence charSequence) {
        C0042f.m158e(str, "<this>");
        C0042f.m158e(charSequence, "suffix");
        if (!m20453E(str, charSequence, false, 2, null)) {
            return str;
        }
        String strSubstring = str.substring(0, str.length() - charSequence.length());
        C0042f.m157d(strSubstring, "substring(...)");
        return strSubstring;
    }

    /* renamed from: d0 */
    public static final void m20478d0(int i9) {
        if (i9 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i9).toString());
    }

    /* renamed from: e0 */
    public static final List<String> m20479e0(CharSequence charSequence, char[] cArr, boolean z8, int i9) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(cArr, "delimiters");
        if (cArr.length == 1) {
            return m20480f0(charSequence, String.valueOf(cArr[0]), z8, i9);
        }
        Iterable iterableM18895a = C4766h.m18895a(m20473Y(charSequence, cArr, 0, z8, i9, 2, null));
        ArrayList arrayList = new ArrayList(C5227j.m20408n(iterableM18895a, 10));
        Iterator it = iterableM18895a.iterator();
        while (it.hasNext()) {
            arrayList.add(m20486l0(charSequence, (C0747c) it.next()));
        }
        return arrayList;
    }

    /* renamed from: f0 */
    public static final List<String> m20480f0(CharSequence charSequence, String str, boolean z8, int i9) {
        m20478d0(i9);
        int length = 0;
        int iM20458J = m20458J(charSequence, str, 0, z8);
        if (iM20458J == -1 || i9 == 1) {
            return C5225h.m20396b(charSequence.toString());
        }
        boolean z9 = i9 > 0;
        ArrayList arrayList = new ArrayList(z9 ? C0749e.m3622c(i9, 10) : 10);
        do {
            arrayList.add(charSequence.subSequence(length, iM20458J).toString());
            length = str.length() + iM20458J;
            if (z9 && arrayList.size() == i9 - 1) {
                break;
            }
            iM20458J = m20458J(charSequence, str, length, z8);
        } while (iM20458J != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    /* renamed from: g0 */
    public static /* synthetic */ List m20481g0(CharSequence charSequence, char[] cArr, boolean z8, int i9, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z8 = false;
        }
        if ((i10 & 4) != 0) {
            i9 = 0;
        }
        return m20479e0(charSequence, cArr, z8, i9);
    }

    /* renamed from: h0 */
    public static final InterfaceC4761c<String> m20482h0(final CharSequence charSequence, String[] strArr, boolean z8, int i9) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(strArr, "delimiters");
        return C4766h.m18900f(m20474Z(charSequence, strArr, 0, z8, i9, 2, null), new InterfaceC6832b<C0747c, String>() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p257z5.InterfaceC6832b
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String mo20353b(C0747c c0747c) {
                C0042f.m158e(c0747c, "it");
                return StringsKt__StringsKt.m20486l0(charSequence, c0747c);
            }
        });
    }

    /* renamed from: i0 */
    public static /* synthetic */ InterfaceC4761c m20483i0(CharSequence charSequence, String[] strArr, boolean z8, int i9, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z8 = false;
        }
        if ((i10 & 4) != 0) {
            i9 = 0;
        }
        return m20482h0(charSequence, strArr, z8, i9);
    }

    /* renamed from: j0 */
    public static final boolean m20484j0(CharSequence charSequence, CharSequence charSequence2, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(charSequence2, "prefix");
        return (!z8 && (charSequence instanceof String) && (charSequence2 instanceof String)) ? C5255l.m20525x((String) charSequence, (String) charSequence2, false, 2, null) : m20475a0(charSequence, 0, charSequence2, 0, charSequence2.length(), z8);
    }

    /* renamed from: k0 */
    public static /* synthetic */ boolean m20485k0(CharSequence charSequence, CharSequence charSequence2, boolean z8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            z8 = false;
        }
        return m20484j0(charSequence, charSequence2, z8);
    }

    /* renamed from: l0 */
    public static final String m20486l0(CharSequence charSequence, C0747c c0747c) {
        C0042f.m158e(charSequence, "<this>");
        C0042f.m158e(c0747c, "range");
        return charSequence.subSequence(c0747c.m3618h().intValue(), c0747c.m3617g().intValue() + 1).toString();
    }

    /* renamed from: m0 */
    public static final CharSequence m20487m0(CharSequence charSequence) {
        C0042f.m158e(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i9 = 0;
        boolean z8 = false;
        while (i9 <= length) {
            boolean zM20496c = C5244a.m20496c(charSequence.charAt(!z8 ? i9 : length));
            if (z8) {
                if (!zM20496c) {
                    break;
                }
                length--;
            } else if (zM20496c) {
                i9++;
            } else {
                z8 = true;
            }
        }
        return charSequence.subSequence(i9, length + 1);
    }

    /* renamed from: z */
    public static final boolean m20489z(CharSequence charSequence, char c9, boolean z8) {
        C0042f.m158e(charSequence, "<this>");
        return m20461M(charSequence, c9, 0, z8, 2, null) >= 0;
    }
}
