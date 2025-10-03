package kotlin.text;

import p007a6.C0042f;
import p027c6.C0749e;

/* renamed from: kotlin.text.n */
/* loaded from: classes2.dex */
public class C5257n extends C5256m {
    /* renamed from: n0 */
    public static final String m20526n0(String str, int i9) {
        C0042f.m158e(str, "<this>");
        if (i9 >= 0) {
            String strSubstring = str.substring(0, C0749e.m3622c(i9, str.length()));
            C0042f.m157d(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(("Requested character count " + i9 + " is less than zero.").toString());
    }
}
