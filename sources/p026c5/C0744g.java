package p026c5;

import org.apache.harmony.javax.security.sasl.SaslException;

/* renamed from: c5.g */
/* loaded from: classes2.dex */
public class C0744g {

    /* renamed from: a */
    public int f3482a = 0;

    /* renamed from: b */
    public int f3483b = 0;

    /* renamed from: c */
    public int f3484c = 1;

    /* renamed from: d */
    public String f3485d;

    public C0744g(String str) {
        this.f3485d = str;
    }

    /* renamed from: a */
    public boolean m3607a(char c9) {
        if (c9 >= 0 && c9 <= ' ') {
            return false;
        }
        if (c9 < ':' || c9 > '@') {
            return ((c9 >= '[' && c9 <= ']') || ',' == c9 || '%' == c9 || '(' == c9 || ')' == c9 || '{' == c9 || '}' == c9 || 127 == c9) ? false : true;
        }
        return false;
    }

    /* renamed from: b */
    public boolean m3608b(char c9) {
        return '\t' == c9 || '\n' == c9 || '\r' == c9 || ' ' == c9;
    }

    /* renamed from: c */
    public String m3609c() throws SaslException {
        String strSubstring;
        String str = null;
        if (this.f3484c == 6) {
            return null;
        }
        while (this.f3482a < this.f3485d.length() && str == null) {
            char cCharAt = this.f3485d.charAt(this.f3482a);
            int i9 = this.f3484c;
            if (i9 == 1 || i9 == 2) {
                if (m3608b(cCharAt)) {
                    continue;
                } else {
                    if (!m3607a(cCharAt)) {
                        this.f3484c = 5;
                        throw new SaslException("Invalid token character at position " + this.f3482a);
                    }
                    this.f3483b = this.f3482a;
                    this.f3484c = 3;
                }
            } else if (i9 != 3) {
                if (i9 == 4 && !m3608b(cCharAt)) {
                    if (cCharAt != ',') {
                        this.f3484c = 5;
                        throw new SaslException("Expected a comma, found '" + cCharAt + "' at postion " + this.f3482a);
                    }
                    this.f3484c = 2;
                }
            } else if (m3607a(cCharAt)) {
                continue;
            } else {
                if (m3608b(cCharAt)) {
                    strSubstring = this.f3485d.substring(this.f3483b, this.f3482a);
                    this.f3484c = 4;
                } else {
                    if (',' != cCharAt) {
                        this.f3484c = 5;
                        throw new SaslException("Invalid token character at position " + this.f3482a);
                    }
                    strSubstring = this.f3485d.substring(this.f3483b, this.f3482a);
                    this.f3484c = 2;
                }
                str = strSubstring;
            }
            this.f3482a++;
        }
        if (str != null) {
            return str;
        }
        int i10 = this.f3484c;
        if (i10 == 2) {
            throw new SaslException("Trialing comma");
        }
        if (i10 != 3) {
            return str;
        }
        String strSubstring2 = this.f3485d.substring(this.f3483b);
        this.f3484c = 6;
        return strSubstring2;
    }
}
