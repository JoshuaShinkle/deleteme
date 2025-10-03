package org.jsoup.parser;

import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Arrays;
import org.apache.commons.lang3.CharUtils;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Token;
import p060e8.C4772d;
import p090h8.C5030a;
import p090h8.C5031b;

/* renamed from: org.jsoup.parser.b */
/* loaded from: classes.dex */
public final class C5796b {

    /* renamed from: r */
    public static final char[] f20179r;

    /* renamed from: s */
    public static final int[] f20180s;

    /* renamed from: a */
    public final C5030a f20181a;

    /* renamed from: b */
    public final ParseErrorList f20182b;

    /* renamed from: d */
    public Token f20184d;

    /* renamed from: i */
    public Token.AbstractC5727i f20189i;

    /* renamed from: o */
    public String f20195o;

    /* renamed from: c */
    public TokeniserState f20183c = TokeniserState.Data;

    /* renamed from: e */
    public boolean f20185e = false;

    /* renamed from: f */
    public String f20186f = null;

    /* renamed from: g */
    public StringBuilder f20187g = new StringBuilder(UserMetadata.MAX_ATTRIBUTE_SIZE);

    /* renamed from: h */
    public StringBuilder f20188h = new StringBuilder(UserMetadata.MAX_ATTRIBUTE_SIZE);

    /* renamed from: j */
    public Token.C5726h f20190j = new Token.C5726h();

    /* renamed from: k */
    public Token.C5725g f20191k = new Token.C5725g();

    /* renamed from: l */
    public Token.C5721c f20192l = new Token.C5721c();

    /* renamed from: m */
    public Token.C5723e f20193m = new Token.C5723e();

    /* renamed from: n */
    public Token.C5722d f20194n = new Token.C5722d();

    /* renamed from: p */
    public final int[] f20196p = new int[1];

    /* renamed from: q */
    public final int[] f20197q = new int[2];

    static {
        char[] cArr = {'\t', '\n', CharUtils.f19105CR, '\f', ' ', '<', '&'};
        f20179r = cArr;
        f20180s = new int[]{8364, TsExtractor.TS_STREAM_TYPE_AC3, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 141, 381, 143, 144, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 157, 382, 376};
        Arrays.sort(cArr);
    }

    public C5796b(C5030a c5030a, ParseErrorList parseErrorList) {
        this.f20181a = c5030a;
        this.f20182b = parseErrorList;
    }

    /* renamed from: a */
    public void m23119a(TokeniserState tokeniserState) {
        this.f20181a.m19618a();
        this.f20183c = tokeniserState;
    }

    /* renamed from: b */
    public String m23120b() {
        return this.f20195o;
    }

    /* renamed from: c */
    public final void m23121c(String str) {
        if (this.f20182b.m22989a()) {
            this.f20182b.add(new C5031b(this.f20181a.m19615F(), "Invalid character reference: %s", str));
        }
    }

    /* renamed from: d */
    public int[] m23122d(Character ch, boolean z8) {
        int iIntValue;
        if (this.f20181a.m19634r()) {
            return null;
        }
        if ((ch != null && ch.charValue() == this.f20181a.m19633q()) || this.f20181a.m19642z(f20179r)) {
            return null;
        }
        int[] iArr = this.f20196p;
        this.f20181a.m19636t();
        if (this.f20181a.m19637u("#")) {
            boolean zM19638v = this.f20181a.m19638v("X");
            C5030a c5030a = this.f20181a;
            String strM19623g = zM19638v ? c5030a.m19623g() : c5030a.m19622f();
            if (strM19623g.length() == 0) {
                m23121c("numeric reference with no numerals");
                this.f20181a.m19616H();
                return null;
            }
            if (!this.f20181a.m19637u(";")) {
                m23121c("missing semicolon");
            }
            try {
                iIntValue = Integer.valueOf(strM19623g, zM19638v ? 16 : 10).intValue();
            } catch (NumberFormatException unused) {
                iIntValue = -1;
            }
            if (iIntValue == -1 || ((iIntValue >= 55296 && iIntValue <= 57343) || iIntValue > 1114111)) {
                m23121c("character outside of valid range");
                iArr[0] = 65533;
                return iArr;
            }
            if (iIntValue >= 128) {
                int[] iArr2 = f20180s;
                if (iIntValue < iArr2.length + 128) {
                    m23121c("character is not a valid unicode code point");
                    iIntValue = iArr2[iIntValue - 128];
                }
            }
            iArr[0] = iIntValue;
            return iArr;
        }
        String strM19625i = this.f20181a.m19625i();
        boolean zM19639w = this.f20181a.m19639w(';');
        if (!(Entities.m22884f(strM19625i) || (Entities.m22885g(strM19625i) && zM19639w))) {
            this.f20181a.m19616H();
            if (zM19639w) {
                m23121c(String.format("invalid named referenece '%s'", strM19625i));
            }
            return null;
        }
        if (z8 && (this.f20181a.m19612C() || this.f20181a.m19610A() || this.f20181a.m19641y('=', '-', '_'))) {
            this.f20181a.m19616H();
            return null;
        }
        if (!this.f20181a.m19637u(";")) {
            m23121c("missing semicolon");
        }
        int iM22882d = Entities.m22882d(strM19625i, this.f20197q);
        if (iM22882d == 1) {
            iArr[0] = this.f20197q[0];
            return iArr;
        }
        if (iM22882d == 2) {
            return this.f20197q;
        }
        C4772d.m18995a("Unexpected characters returned for " + strM19625i);
        return this.f20197q;
    }

    /* renamed from: e */
    public void m23123e() {
        this.f20194n.mo23003m();
    }

    /* renamed from: f */
    public void m23124f() {
        this.f20193m.mo23003m();
    }

    /* renamed from: g */
    public Token.AbstractC5727i m23125g(boolean z8) {
        Token.AbstractC5727i abstractC5727iMo23003m = z8 ? this.f20190j.mo23003m() : this.f20191k.mo23003m();
        this.f20189i = abstractC5727iMo23003m;
        return abstractC5727iMo23003m;
    }

    /* renamed from: h */
    public void m23126h() {
        Token.m22990n(this.f20188h);
    }

    /* renamed from: i */
    public void m23127i(char c9) {
        m23128j(String.valueOf(c9));
    }

    /* renamed from: j */
    public void m23128j(String str) {
        if (this.f20186f == null) {
            this.f20186f = str;
            return;
        }
        if (this.f20187g.length() == 0) {
            this.f20187g.append(this.f20186f);
        }
        this.f20187g.append(str);
    }

    /* renamed from: k */
    public void m23129k(Token token) {
        C4772d.m18997c(this.f20185e, "There is an unread token pending!");
        this.f20184d = token;
        this.f20185e = true;
        Token.TokenType tokenType = token.f20061a;
        if (tokenType == Token.TokenType.StartTag) {
            this.f20195o = ((Token.C5726h) token).f20077b;
        } else {
            if (tokenType != Token.TokenType.EndTag || ((Token.C5725g) token).f20085j == null) {
                return;
            }
            m23135q("Attributes incorrectly present on end tag");
        }
    }

    /* renamed from: l */
    public void m23130l(int[] iArr) {
        m23128j(new String(iArr, 0, iArr.length));
    }

    /* renamed from: m */
    public void m23131m() {
        m23129k(this.f20194n);
    }

    /* renamed from: n */
    public void m23132n() {
        m23129k(this.f20193m);
    }

    /* renamed from: o */
    public void m23133o() {
        this.f20189i.m23028x();
        m23129k(this.f20189i);
    }

    /* renamed from: p */
    public void m23134p(TokeniserState tokeniserState) {
        if (this.f20182b.m22989a()) {
            this.f20182b.add(new C5031b(this.f20181a.m19615F(), "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    /* renamed from: q */
    public void m23135q(String str) {
        if (this.f20182b.m22989a()) {
            this.f20182b.add(new C5031b(this.f20181a.m19615F(), str));
        }
    }

    /* renamed from: r */
    public void m23136r(TokeniserState tokeniserState) {
        if (this.f20182b.m22989a()) {
            this.f20182b.add(new C5031b(this.f20181a.m19615F(), "Unexpected character '%s' in input state [%s]", Character.valueOf(this.f20181a.m19633q()), tokeniserState));
        }
    }

    /* renamed from: s */
    public boolean m23137s() {
        return this.f20195o != null && this.f20189i.m23015A().equalsIgnoreCase(this.f20195o);
    }

    /* renamed from: t */
    public Token m23138t() {
        while (!this.f20185e) {
            this.f20183c.mo23042i(this, this.f20181a);
        }
        if (this.f20187g.length() > 0) {
            String string = this.f20187g.toString();
            StringBuilder sb = this.f20187g;
            sb.delete(0, sb.length());
            this.f20186f = null;
            return this.f20192l.m23005p(string);
        }
        String str = this.f20186f;
        if (str == null) {
            this.f20185e = false;
            return this.f20184d;
        }
        Token.C5721c c5721cM23005p = this.f20192l.m23005p(str);
        this.f20186f = null;
        return c5721cM23005p;
    }

    /* renamed from: u */
    public void m23139u(TokeniserState tokeniserState) {
        this.f20183c = tokeniserState;
    }
}
