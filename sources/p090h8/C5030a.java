package p090h8;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import p060e8.C4772d;

/* renamed from: h8.a */
/* loaded from: classes.dex */
public final class C5030a {

    /* renamed from: a */
    public final char[] f17353a;

    /* renamed from: b */
    public final Reader f17354b;

    /* renamed from: c */
    public int f17355c;

    /* renamed from: d */
    public int f17356d;

    /* renamed from: e */
    public int f17357e;

    /* renamed from: f */
    public int f17358f;

    /* renamed from: g */
    public int f17359g;

    /* renamed from: h */
    public final String[] f17360h;

    public C5030a(Reader reader, int i9) throws IOException {
        this.f17360h = new String[512];
        C4772d.m19004j(reader);
        C4772d.m18998d(reader.markSupported());
        this.f17354b = reader;
        this.f17353a = new char[i9 > 32768 ? 32768 : i9];
        m19619b();
    }

    /* renamed from: G */
    public static boolean m19608G(char[] cArr, int i9, int i10, String str) {
        if (i10 != str.length()) {
            return false;
        }
        int i11 = 0;
        while (true) {
            int i12 = i10 - 1;
            if (i10 == 0) {
                return true;
            }
            int i13 = i9 + 1;
            int i14 = i11 + 1;
            if (cArr[i9] != str.charAt(i11)) {
                return false;
            }
            i9 = i13;
            i10 = i12;
            i11 = i14;
        }
    }

    /* renamed from: c */
    public static String m19609c(char[] cArr, String[] strArr, int i9, int i10) {
        if (i10 > 12) {
            return new String(cArr, i9, i10);
        }
        if (i10 < 1) {
            return "";
        }
        int i11 = 0;
        int i12 = i9;
        int i13 = 0;
        while (i11 < i10) {
            i13 = (i13 * 31) + cArr[i12];
            i11++;
            i12++;
        }
        int length = i13 & (strArr.length - 1);
        String str = strArr[length];
        if (str == null) {
            String str2 = new String(cArr, i9, i10);
            strArr[length] = str2;
            return str2;
        }
        if (m19608G(cArr, i9, i10, str)) {
            return str;
        }
        String str3 = new String(cArr, i9, i10);
        strArr[length] = str3;
        return str3;
    }

    /* renamed from: A */
    public boolean m19610A() {
        char c9;
        return !m19634r() && (c9 = this.f17353a[this.f17357e]) >= '0' && c9 <= '9';
    }

    /* renamed from: B */
    public boolean m19611B(String str) throws IOException {
        m19619b();
        int length = str.length();
        if (length > this.f17355c - this.f17357e) {
            return false;
        }
        for (int i9 = 0; i9 < length; i9++) {
            if (Character.toUpperCase(str.charAt(i9)) != Character.toUpperCase(this.f17353a[this.f17357e + i9])) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: C */
    public boolean m19612C() {
        if (m19634r()) {
            return false;
        }
        char c9 = this.f17353a[this.f17357e];
        return (c9 >= 'A' && c9 <= 'Z') || (c9 >= 'a' && c9 <= 'z') || Character.isLetter(c9);
    }

    /* renamed from: D */
    public int m19613D(char c9) throws IOException {
        m19619b();
        for (int i9 = this.f17357e; i9 < this.f17355c; i9++) {
            if (c9 == this.f17353a[i9]) {
                return i9 - this.f17357e;
            }
        }
        return -1;
    }

    /* renamed from: E */
    public int m19614E(CharSequence charSequence) throws IOException {
        m19619b();
        char cCharAt = charSequence.charAt(0);
        int i9 = this.f17357e;
        while (i9 < this.f17355c) {
            if (cCharAt != this.f17353a[i9]) {
                do {
                    i9++;
                    if (i9 >= this.f17355c) {
                        break;
                    }
                } while (cCharAt != this.f17353a[i9]);
            }
            int i10 = i9 + 1;
            int length = (charSequence.length() + i10) - 1;
            int i11 = this.f17355c;
            if (i9 < i11 && length <= i11) {
                int i12 = i10;
                for (int i13 = 1; i12 < length && charSequence.charAt(i13) == this.f17353a[i12]; i13++) {
                    i12++;
                }
                if (i12 == length) {
                    return i9 - this.f17357e;
                }
            }
            i9 = i10;
        }
        return -1;
    }

    /* renamed from: F */
    public int m19615F() {
        return this.f17358f + this.f17357e;
    }

    /* renamed from: H */
    public void m19616H() {
        this.f17357e = this.f17359g;
    }

    /* renamed from: I */
    public void m19617I() {
        this.f17357e--;
    }

    /* renamed from: a */
    public void m19618a() {
        this.f17357e++;
    }

    /* renamed from: b */
    public final void m19619b() throws IOException {
        int i9 = this.f17357e;
        if (i9 < this.f17356d) {
            return;
        }
        try {
            this.f17354b.skip(i9);
            this.f17354b.mark(32768);
            int i10 = this.f17354b.read(this.f17353a);
            this.f17354b.reset();
            if (i10 != -1) {
                this.f17355c = i10;
                this.f17358f += this.f17357e;
                this.f17357e = 0;
                this.f17359g = 0;
                if (i10 > 24576) {
                    i10 = 24576;
                }
                this.f17356d = i10;
            }
        } catch (IOException e9) {
            throw new UncheckedIOException(e9);
        }
    }

    /* renamed from: d */
    public char m19620d() throws IOException {
        m19619b();
        char c9 = m19635s() ? (char) 65535 : this.f17353a[this.f17357e];
        this.f17357e++;
        return c9;
    }

    /* renamed from: e */
    public String m19621e() throws IOException {
        int i9;
        char c9;
        m19619b();
        int i10 = this.f17357e;
        int i11 = this.f17355c;
        char[] cArr = this.f17353a;
        while (true) {
            i9 = this.f17357e;
            if (i9 >= i11 || (c9 = cArr[i9]) == '&' || c9 == '<' || c9 == 0) {
                break;
            }
            this.f17357e = i9 + 1;
        }
        return i9 > i10 ? m19609c(this.f17353a, this.f17360h, i10, i9 - i10) : "";
    }

    /* renamed from: f */
    public String m19622f() throws IOException {
        int i9;
        char c9;
        m19619b();
        int i10 = this.f17357e;
        while (true) {
            i9 = this.f17357e;
            if (i9 >= this.f17355c || (c9 = this.f17353a[i9]) < '0' || c9 > '9') {
                break;
            }
            this.f17357e = i9 + 1;
        }
        return m19609c(this.f17353a, this.f17360h, i10, i9 - i10);
    }

    /* renamed from: g */
    public String m19623g() throws IOException {
        int i9;
        char c9;
        m19619b();
        int i10 = this.f17357e;
        while (true) {
            i9 = this.f17357e;
            if (i9 >= this.f17355c || (((c9 = this.f17353a[i9]) < '0' || c9 > '9') && ((c9 < 'A' || c9 > 'F') && (c9 < 'a' || c9 > 'f')))) {
                break;
            }
            this.f17357e = i9 + 1;
        }
        return m19609c(this.f17353a, this.f17360h, i10, i9 - i10);
    }

    /* renamed from: h */
    public String m19624h() throws IOException {
        char c9;
        m19619b();
        int i9 = this.f17357e;
        while (true) {
            int i10 = this.f17357e;
            if (i10 >= this.f17355c || (((c9 = this.f17353a[i10]) < 'A' || c9 > 'Z') && ((c9 < 'a' || c9 > 'z') && !Character.isLetter(c9)))) {
                break;
            }
            this.f17357e++;
        }
        return m19609c(this.f17353a, this.f17360h, i9, this.f17357e - i9);
    }

    /* renamed from: i */
    public String m19625i() throws IOException {
        char c9;
        m19619b();
        int i9 = this.f17357e;
        while (true) {
            int i10 = this.f17357e;
            if (i10 >= this.f17355c || (((c9 = this.f17353a[i10]) < 'A' || c9 > 'Z') && ((c9 < 'a' || c9 > 'z') && !Character.isLetter(c9)))) {
                break;
            }
            this.f17357e++;
        }
        while (!m19635s()) {
            char[] cArr = this.f17353a;
            int i11 = this.f17357e;
            char c10 = cArr[i11];
            if (c10 < '0' || c10 > '9') {
                break;
            }
            this.f17357e = i11 + 1;
        }
        return m19609c(this.f17353a, this.f17360h, i9, this.f17357e - i9);
    }

    /* renamed from: j */
    public String m19626j() throws IOException {
        int i9;
        char c9;
        m19619b();
        int i10 = this.f17357e;
        int i11 = this.f17355c;
        char[] cArr = this.f17353a;
        while (true) {
            i9 = this.f17357e;
            if (i9 >= i11 || (c9 = cArr[i9]) == '\t' || c9 == '\n' || c9 == '\r' || c9 == '\f' || c9 == ' ' || c9 == '/' || c9 == '>' || c9 == 0) {
                break;
            }
            this.f17357e = i9 + 1;
        }
        return i9 > i10 ? m19609c(this.f17353a, this.f17360h, i10, i9 - i10) : "";
    }

    /* renamed from: k */
    public String m19627k(char c9) throws IOException {
        int iM19613D = m19613D(c9);
        if (iM19613D == -1) {
            return m19631o();
        }
        String strM19609c = m19609c(this.f17353a, this.f17360h, this.f17357e, iM19613D);
        this.f17357e += iM19613D;
        return strM19609c;
    }

    /* renamed from: l */
    public String m19628l(String str) throws IOException {
        int iM19614E = m19614E(str);
        if (iM19614E == -1) {
            return m19631o();
        }
        String strM19609c = m19609c(this.f17353a, this.f17360h, this.f17357e, iM19614E);
        this.f17357e += iM19614E;
        return strM19609c;
    }

    /* renamed from: m */
    public String m19629m(char... cArr) throws IOException {
        m19619b();
        int i9 = this.f17357e;
        int i10 = this.f17355c;
        char[] cArr2 = this.f17353a;
        loop0: while (this.f17357e < i10) {
            for (char c9 : cArr) {
                if (cArr2[this.f17357e] == c9) {
                    break loop0;
                }
            }
            this.f17357e++;
        }
        int i11 = this.f17357e;
        return i11 > i9 ? m19609c(this.f17353a, this.f17360h, i9, i11 - i9) : "";
    }

    /* renamed from: n */
    public String m19630n(char... cArr) throws IOException {
        m19619b();
        int i9 = this.f17357e;
        int i10 = this.f17355c;
        char[] cArr2 = this.f17353a;
        while (true) {
            int i11 = this.f17357e;
            if (i11 >= i10 || Arrays.binarySearch(cArr, cArr2[i11]) >= 0) {
                break;
            }
            this.f17357e++;
        }
        int i12 = this.f17357e;
        return i12 > i9 ? m19609c(this.f17353a, this.f17360h, i9, i12 - i9) : "";
    }

    /* renamed from: o */
    public String m19631o() throws IOException {
        m19619b();
        char[] cArr = this.f17353a;
        String[] strArr = this.f17360h;
        int i9 = this.f17357e;
        String strM19609c = m19609c(cArr, strArr, i9, this.f17355c - i9);
        this.f17357e = this.f17355c;
        return strM19609c;
    }

    /* renamed from: p */
    public boolean m19632p(String str) {
        Locale locale = Locale.ENGLISH;
        return m19614E(str.toLowerCase(locale)) > -1 || m19614E(str.toUpperCase(locale)) > -1;
    }

    /* renamed from: q */
    public char m19633q() throws IOException {
        m19619b();
        if (m19635s()) {
            return (char) 65535;
        }
        return this.f17353a[this.f17357e];
    }

    /* renamed from: r */
    public boolean m19634r() throws IOException {
        m19619b();
        return this.f17357e >= this.f17355c;
    }

    /* renamed from: s */
    public final boolean m19635s() {
        return this.f17357e >= this.f17355c;
    }

    /* renamed from: t */
    public void m19636t() {
        this.f17359g = this.f17357e;
    }

    public String toString() {
        char[] cArr = this.f17353a;
        int i9 = this.f17357e;
        return new String(cArr, i9, this.f17355c - i9);
    }

    /* renamed from: u */
    public boolean m19637u(String str) throws IOException {
        m19619b();
        if (!m19640x(str)) {
            return false;
        }
        this.f17357e += str.length();
        return true;
    }

    /* renamed from: v */
    public boolean m19638v(String str) {
        if (!m19611B(str)) {
            return false;
        }
        this.f17357e += str.length();
        return true;
    }

    /* renamed from: w */
    public boolean m19639w(char c9) {
        return !m19634r() && this.f17353a[this.f17357e] == c9;
    }

    /* renamed from: x */
    public boolean m19640x(String str) throws IOException {
        m19619b();
        int length = str.length();
        if (length > this.f17355c - this.f17357e) {
            return false;
        }
        for (int i9 = 0; i9 < length; i9++) {
            if (str.charAt(i9) != this.f17353a[this.f17357e + i9]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: y */
    public boolean m19641y(char... cArr) throws IOException {
        if (m19634r()) {
            return false;
        }
        m19619b();
        char c9 = this.f17353a[this.f17357e];
        for (char c10 : cArr) {
            if (c10 == c9) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: z */
    public boolean m19642z(char[] cArr) throws IOException {
        m19619b();
        return !m19634r() && Arrays.binarySearch(cArr, this.f17353a[this.f17357e]) >= 0;
    }

    public C5030a(Reader reader) {
        this(reader, 32768);
    }

    public C5030a(String str) {
        this(new StringReader(str), str.length());
    }
}
