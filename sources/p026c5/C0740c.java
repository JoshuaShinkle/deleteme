package p026c5;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

/* renamed from: c5.c */
/* loaded from: classes2.dex */
public class C0740c {

    /* renamed from: c */
    public String f3468c;

    /* renamed from: d */
    public int f3469d;

    /* renamed from: a */
    public int f3466a = 0;

    /* renamed from: e */
    public ArrayList f3470e = new ArrayList(10);

    /* renamed from: f */
    public int f3471f = 0;

    /* renamed from: b */
    public int f3467b = -1;

    public C0740c(byte[] bArr) {
        this.f3469d = 1;
        try {
            this.f3468c = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            this.f3469d = 9;
        }
    }

    /* renamed from: a */
    public void m3597a(String str, boolean z8) {
        String str2;
        if (z8) {
            StringBuffer stringBuffer = new StringBuffer(this.f3466a - this.f3471f);
            int i9 = this.f3471f;
            int i10 = 0;
            while (i9 < this.f3466a) {
                if ('\\' == this.f3468c.charAt(i9)) {
                    i9++;
                }
                stringBuffer.setCharAt(i10, this.f3468c.charAt(i9));
                i10++;
                i9++;
            }
            str2 = new String(stringBuffer);
        } else {
            str2 = this.f3468c.substring(this.f3471f, this.f3466a);
        }
        this.f3470e.add(new C0742e(str, str2, this.f3469d != 7 ? 2 : 1));
    }

    /* renamed from: b */
    public Iterator m3598b() {
        return this.f3470e.iterator();
    }

    /* renamed from: c */
    public boolean m3599c(char c9) {
        if (c9 >= 0 && c9 <= ' ') {
            return false;
        }
        if (c9 < ':' || c9 > '@') {
            return ((c9 >= '[' && c9 <= ']') || ',' == c9 || '%' == c9 || '(' == c9 || ')' == c9 || '{' == c9 || '}' == c9 || 127 == c9) ? false : true;
        }
        return false;
    }

    /* renamed from: d */
    public boolean m3600d(char c9) {
        return '\t' == c9 || '\n' == c9 || '\r' == c9 || ' ' == c9;
    }

    /* renamed from: e */
    public void m3601e() throws SaslException {
        if (this.f3469d == 9) {
            throw new SaslException("No UTF-8 support on platform");
        }
        String strSubstring = "<no name>";
        char c9 = 0;
        boolean z8 = false;
        while (this.f3466a < this.f3468c.length()) {
            char cCharAt = this.f3468c.charAt(this.f3466a);
            switch (this.f3469d) {
                case 1:
                case 2:
                    if (m3600d(cCharAt)) {
                        continue;
                    } else {
                        if (!m3599c(cCharAt)) {
                            this.f3467b = this.f3466a;
                            throw new SaslException("Parse error: Invalid name character");
                        }
                        this.f3471f = this.f3466a;
                        this.f3469d = 3;
                        break;
                    }
                case 3:
                    if (m3599c(cCharAt)) {
                        continue;
                    } else if (m3600d(cCharAt)) {
                        strSubstring = this.f3468c.substring(this.f3471f, this.f3466a);
                        this.f3469d = 4;
                        break;
                    } else {
                        if ('=' != cCharAt) {
                            this.f3467b = this.f3466a;
                            throw new SaslException("Parse error: Invalid name character");
                        }
                        strSubstring = this.f3468c.substring(this.f3471f, this.f3466a);
                        this.f3469d = 5;
                        break;
                    }
                case 4:
                    if (m3600d(cCharAt)) {
                        continue;
                    } else {
                        if ('=' != cCharAt) {
                            this.f3467b = this.f3466a;
                            throw new SaslException("Parse error: Expected equals sign '='.");
                        }
                        this.f3469d = 5;
                        break;
                    }
                case 5:
                    if (m3600d(cCharAt)) {
                        continue;
                    } else if ('\"' == cCharAt) {
                        this.f3471f = this.f3466a + 1;
                        this.f3469d = 7;
                        break;
                    } else {
                        if (!m3599c(cCharAt)) {
                            this.f3467b = this.f3466a;
                            throw new SaslException("Parse error: Unexpected character");
                        }
                        this.f3471f = this.f3466a;
                        this.f3469d = 8;
                        break;
                    }
                case 6:
                    if (m3600d(cCharAt)) {
                        continue;
                    } else {
                        if (cCharAt != ',') {
                            this.f3467b = this.f3466a;
                            throw new SaslException("Parse error: Expected a comma.");
                        }
                        this.f3469d = 2;
                        break;
                    }
                case 7:
                    if ('\\' == cCharAt) {
                        z8 = true;
                    }
                    if ('\"' == cCharAt && '\\' != c9) {
                        m3597a(strSubstring, z8);
                        this.f3469d = 6;
                        z8 = false;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (m3599c(cCharAt)) {
                        continue;
                    } else if (m3600d(cCharAt)) {
                        m3597a(strSubstring, false);
                        this.f3469d = 6;
                        break;
                    } else {
                        if (',' != cCharAt) {
                            this.f3467b = this.f3466a;
                            throw new SaslException("Parse error: Invalid value character");
                        }
                        m3597a(strSubstring, false);
                        this.f3469d = 2;
                        break;
                    }
            }
            this.f3466a++;
            c9 = cCharAt;
        }
        int i9 = this.f3469d;
        if (i9 == 2) {
            throw new SaslException("Parse error: Trailing comma.");
        }
        if (i9 == 3 || i9 == 4 || i9 == 5) {
            throw new SaslException("Parse error: Missing value.");
        }
        if (i9 == 7) {
            throw new SaslException("Parse error: Missing closing quote.");
        }
        if (i9 != 8) {
            return;
        }
        m3597a(strSubstring, false);
    }
}
