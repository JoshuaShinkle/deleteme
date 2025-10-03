package p090h8;

import p060e8.C4771c;
import p060e8.C4772d;

/* renamed from: h8.f */
/* loaded from: classes.dex */
public class C5035f {

    /* renamed from: a */
    public String f17388a;

    /* renamed from: b */
    public int f17389b = 0;

    public C5035f(String str) {
        C4772d.m19004j(str);
        this.f17388a = str;
    }

    /* renamed from: s */
    public static String m19662s(String str) {
        StringBuilder sbM18993o = C4771c.m18993o();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i9 = 0;
        char c9 = 0;
        while (i9 < length) {
            char c10 = charArray[i9];
            if (c10 != '\\') {
                sbM18993o.append(c10);
            } else if (c9 != 0 && c9 == '\\') {
                sbM18993o.append(c10);
            }
            i9++;
            c9 = c10;
        }
        return sbM18993o.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0078 A[EDGE_INSN: B:45:0x0078->B:38:0x0078 BREAK  A[LOOP:0: B:3:0x0007->B:49:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[LOOP:0: B:3:0x0007->B:49:?, LOOP_END, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String m19663a(char c9, char c10) {
        char cCharValue = 0;
        int i9 = -1;
        int i10 = -1;
        boolean z8 = false;
        boolean z9 = false;
        int i11 = 0;
        while (!m19672j()) {
            Character chValueOf = Character.valueOf(m19665c());
            if (cCharValue == 0 || cCharValue != '\\') {
                if (chValueOf.equals('\'') && chValueOf.charValue() != c9 && !z8) {
                    z9 = !z9;
                } else if (chValueOf.equals('\"') && chValueOf.charValue() != c9 && !z9) {
                    z8 = !z8;
                }
                if (!z9 && !z8) {
                    if (chValueOf.equals(Character.valueOf(c9))) {
                        i11++;
                        if (i9 == -1) {
                            i9 = this.f17389b;
                        }
                    } else if (chValueOf.equals(Character.valueOf(c10))) {
                        i11--;
                    }
                    if (i11 > 0 && cCharValue != 0) {
                        i10 = this.f17389b;
                    }
                    cCharValue = chValueOf.charValue();
                    if (i11 <= 0) {
                    }
                } else if (i11 <= 0) {
                    break;
                }
            } else {
                if (i11 > 0) {
                    i10 = this.f17389b;
                }
                cCharValue = chValueOf.charValue();
                if (i11 <= 0) {
                }
            }
        }
        String strSubstring = i10 >= 0 ? this.f17388a.substring(i9, i10) : "";
        if (i11 > 0) {
            C4772d.m18995a("Did not find balanced marker at '" + strSubstring + "'");
        }
        return strSubstring;
    }

    /* renamed from: b */
    public String m19664b(String str) {
        String strM19669g = m19669g(str);
        m19673k(str);
        return strM19669g;
    }

    /* renamed from: c */
    public char m19665c() {
        String str = this.f17388a;
        int i9 = this.f17389b;
        this.f17389b = i9 + 1;
        return str.charAt(i9);
    }

    /* renamed from: d */
    public void m19666d(String str) {
        if (!m19674l(str)) {
            throw new IllegalStateException("Queue did not match expected sequence");
        }
        int length = str.length();
        if (length > m19680r()) {
            throw new IllegalStateException("Queue not long enough to consume sequence");
        }
        this.f17389b += length;
    }

    /* renamed from: e */
    public String m19667e() {
        int i9 = this.f17389b;
        while (!m19672j() && (m19678p() || m19675m('-', '_'))) {
            this.f17389b++;
        }
        return this.f17388a.substring(i9, this.f17389b);
    }

    /* renamed from: f */
    public String m19668f() {
        int i9 = this.f17389b;
        while (!m19672j() && (m19678p() || m19676n("*|", "|", "_", "-"))) {
            this.f17389b++;
        }
        return this.f17388a.substring(i9, this.f17389b);
    }

    /* renamed from: g */
    public String m19669g(String str) {
        int iIndexOf = this.f17388a.indexOf(str, this.f17389b);
        if (iIndexOf == -1) {
            return m19679q();
        }
        String strSubstring = this.f17388a.substring(this.f17389b, iIndexOf);
        this.f17389b += strSubstring.length();
        return strSubstring;
    }

    /* renamed from: h */
    public String m19670h(String... strArr) {
        int i9 = this.f17389b;
        while (!m19672j() && !m19676n(strArr)) {
            this.f17389b++;
        }
        return this.f17388a.substring(i9, this.f17389b);
    }

    /* renamed from: i */
    public boolean m19671i() {
        boolean z8 = false;
        while (m19677o()) {
            this.f17389b++;
            z8 = true;
        }
        return z8;
    }

    /* renamed from: j */
    public boolean m19672j() {
        return m19680r() == 0;
    }

    /* renamed from: k */
    public boolean m19673k(String str) {
        if (!m19674l(str)) {
            return false;
        }
        this.f17389b += str.length();
        return true;
    }

    /* renamed from: l */
    public boolean m19674l(String str) {
        return this.f17388a.regionMatches(true, this.f17389b, str, 0, str.length());
    }

    /* renamed from: m */
    public boolean m19675m(char... cArr) {
        if (m19672j()) {
            return false;
        }
        for (char c9 : cArr) {
            if (this.f17388a.charAt(this.f17389b) == c9) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: n */
    public boolean m19676n(String... strArr) {
        for (String str : strArr) {
            if (m19674l(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: o */
    public boolean m19677o() {
        return !m19672j() && C4771c.m18986h(this.f17388a.charAt(this.f17389b));
    }

    /* renamed from: p */
    public boolean m19678p() {
        return !m19672j() && Character.isLetterOrDigit(this.f17388a.charAt(this.f17389b));
    }

    /* renamed from: q */
    public String m19679q() {
        String str = this.f17388a;
        String strSubstring = str.substring(this.f17389b, str.length());
        this.f17389b = this.f17388a.length();
        return strSubstring;
    }

    /* renamed from: r */
    public final int m19680r() {
        return this.f17388a.length() - this.f17389b;
    }

    public String toString() {
        return this.f17388a.substring(this.f17389b);
    }
}
