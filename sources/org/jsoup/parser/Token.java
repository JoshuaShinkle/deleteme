package org.jsoup.parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.C5685b;
import p060e8.C4772d;
import p070f8.C4794b;

/* loaded from: classes.dex */
public abstract class Token {

    /* renamed from: a */
    public TokenType f20061a;

    public enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    /* renamed from: org.jsoup.parser.Token$b */
    public static final class C5720b extends C5721c {
        public C5720b(String str) {
            m23005p(str);
        }

        @Override // org.jsoup.parser.Token.C5721c
        public String toString() {
            return "<![CDATA[" + m23006q() + "]]>";
        }
    }

    /* renamed from: org.jsoup.parser.Token$c */
    public static class C5721c extends Token {

        /* renamed from: b */
        public String f20069b;

        public C5721c() {
            super();
            this.f20061a = TokenType.Character;
        }

        @Override // org.jsoup.parser.Token
        /* renamed from: m */
        public Token mo23003m() {
            this.f20069b = null;
            return this;
        }

        /* renamed from: p */
        public C5721c m23005p(String str) {
            this.f20069b = str;
            return this;
        }

        /* renamed from: q */
        public String m23006q() {
            return this.f20069b;
        }

        public String toString() {
            return m23006q();
        }
    }

    /* renamed from: org.jsoup.parser.Token$d */
    public static final class C5722d extends Token {

        /* renamed from: b */
        public final StringBuilder f20070b;

        /* renamed from: c */
        public boolean f20071c;

        public C5722d() {
            super();
            this.f20070b = new StringBuilder();
            this.f20071c = false;
            this.f20061a = TokenType.Comment;
        }

        @Override // org.jsoup.parser.Token
        /* renamed from: m */
        public Token mo23003m() {
            Token.m22990n(this.f20070b);
            this.f20071c = false;
            return this;
        }

        /* renamed from: p */
        public String m23007p() {
            return this.f20070b.toString();
        }

        public String toString() {
            return "<!--" + m23007p() + "-->";
        }
    }

    /* renamed from: org.jsoup.parser.Token$e */
    public static final class C5723e extends Token {

        /* renamed from: b */
        public final StringBuilder f20072b;

        /* renamed from: c */
        public String f20073c;

        /* renamed from: d */
        public final StringBuilder f20074d;

        /* renamed from: e */
        public final StringBuilder f20075e;

        /* renamed from: f */
        public boolean f20076f;

        public C5723e() {
            super();
            this.f20072b = new StringBuilder();
            this.f20073c = null;
            this.f20074d = new StringBuilder();
            this.f20075e = new StringBuilder();
            this.f20076f = false;
            this.f20061a = TokenType.Doctype;
        }

        @Override // org.jsoup.parser.Token
        /* renamed from: m */
        public Token mo23003m() {
            Token.m22990n(this.f20072b);
            this.f20073c = null;
            Token.m22990n(this.f20074d);
            Token.m22990n(this.f20075e);
            this.f20076f = false;
            return this;
        }

        /* renamed from: p */
        public String m23008p() {
            return this.f20072b.toString();
        }

        /* renamed from: q */
        public String m23009q() {
            return this.f20073c;
        }

        /* renamed from: r */
        public String m23010r() {
            return this.f20074d.toString();
        }

        /* renamed from: s */
        public String m23011s() {
            return this.f20075e.toString();
        }

        /* renamed from: t */
        public boolean m23012t() {
            return this.f20076f;
        }
    }

    /* renamed from: org.jsoup.parser.Token$f */
    public static final class C5724f extends Token {
        public C5724f() {
            super();
            this.f20061a = TokenType.EOF;
        }

        @Override // org.jsoup.parser.Token
        /* renamed from: m */
        public Token mo23003m() {
            return this;
        }
    }

    /* renamed from: org.jsoup.parser.Token$g */
    public static final class C5725g extends AbstractC5727i {
        public C5725g() {
            this.f20061a = TokenType.EndTag;
        }

        public String toString() {
            return "</" + m23015A() + ">";
        }
    }

    /* renamed from: org.jsoup.parser.Token$h */
    public static final class C5726h extends AbstractC5727i {
        public C5726h() {
            this.f20085j = new C5685b();
            this.f20061a = TokenType.StartTag;
        }

        @Override // org.jsoup.parser.Token.AbstractC5727i, org.jsoup.parser.Token
        /* renamed from: E, reason: merged with bridge method [inline-methods] */
        public AbstractC5727i mo23003m() {
            super.mo23003m();
            this.f20085j = new C5685b();
            return this;
        }

        /* renamed from: G */
        public C5726h m23014G(String str, C5685b c5685b) {
            this.f20077b = str;
            this.f20085j = c5685b;
            this.f20078c = C4794b.m19030a(str);
            return this;
        }

        public String toString() {
            C5685b c5685b = this.f20085j;
            if (c5685b == null || c5685b.size() <= 0) {
                return "<" + m23015A() + ">";
            }
            return "<" + m23015A() + StringUtils.SPACE + this.f20085j.toString() + ">";
        }
    }

    /* renamed from: org.jsoup.parser.Token$i */
    public static abstract class AbstractC5727i extends Token {

        /* renamed from: b */
        public String f20077b;

        /* renamed from: c */
        public String f20078c;

        /* renamed from: d */
        public String f20079d;

        /* renamed from: e */
        public StringBuilder f20080e;

        /* renamed from: f */
        public String f20081f;

        /* renamed from: g */
        public boolean f20082g;

        /* renamed from: h */
        public boolean f20083h;

        /* renamed from: i */
        public boolean f20084i;

        /* renamed from: j */
        public C5685b f20085j;

        public AbstractC5727i() {
            super();
            this.f20080e = new StringBuilder();
            this.f20082g = false;
            this.f20083h = false;
            this.f20084i = false;
        }

        /* renamed from: A */
        public final String m23015A() {
            String str = this.f20077b;
            C4772d.m18996b(str == null || str.length() == 0);
            return this.f20077b;
        }

        /* renamed from: B */
        public final AbstractC5727i m23016B(String str) {
            this.f20077b = str;
            this.f20078c = C4794b.m19030a(str);
            return this;
        }

        /* renamed from: C */
        public final void m23017C() {
            if (this.f20085j == null) {
                this.f20085j = new C5685b();
            }
            String str = this.f20079d;
            if (str != null) {
                String strTrim = str.trim();
                this.f20079d = strTrim;
                if (strTrim.length() > 0) {
                    this.f20085j.m22925t(this.f20079d, this.f20083h ? this.f20080e.length() > 0 ? this.f20080e.toString() : this.f20081f : this.f20082g ? "" : null);
                }
            }
            this.f20079d = null;
            this.f20082g = false;
            this.f20083h = false;
            Token.m22990n(this.f20080e);
            this.f20081f = null;
        }

        /* renamed from: D */
        public final String m23018D() {
            return this.f20078c;
        }

        @Override // org.jsoup.parser.Token
        /* renamed from: E */
        public AbstractC5727i mo23003m() {
            this.f20077b = null;
            this.f20078c = null;
            this.f20079d = null;
            Token.m22990n(this.f20080e);
            this.f20081f = null;
            this.f20082g = false;
            this.f20083h = false;
            this.f20084i = false;
            this.f20085j = null;
            return this;
        }

        /* renamed from: F */
        public final void m23019F() {
            this.f20082g = true;
        }

        /* renamed from: p */
        public final void m23020p(char c9) {
            m23021q(String.valueOf(c9));
        }

        /* renamed from: q */
        public final void m23021q(String str) {
            String str2 = this.f20079d;
            if (str2 != null) {
                str = str2.concat(str);
            }
            this.f20079d = str;
        }

        /* renamed from: r */
        public final void m23022r(char c9) {
            m23027w();
            this.f20080e.append(c9);
        }

        /* renamed from: s */
        public final void m23023s(String str) {
            m23027w();
            if (this.f20080e.length() == 0) {
                this.f20081f = str;
            } else {
                this.f20080e.append(str);
            }
        }

        /* renamed from: t */
        public final void m23024t(int[] iArr) {
            m23027w();
            for (int i9 : iArr) {
                this.f20080e.appendCodePoint(i9);
            }
        }

        /* renamed from: u */
        public final void m23025u(char c9) {
            m23026v(String.valueOf(c9));
        }

        /* renamed from: v */
        public final void m23026v(String str) {
            String str2 = this.f20077b;
            if (str2 != null) {
                str = str2.concat(str);
            }
            this.f20077b = str;
            this.f20078c = C4794b.m19030a(str);
        }

        /* renamed from: w */
        public final void m23027w() {
            this.f20083h = true;
            String str = this.f20081f;
            if (str != null) {
                this.f20080e.append(str);
                this.f20081f = null;
            }
        }

        /* renamed from: x */
        public final void m23028x() {
            if (this.f20079d != null) {
                m23017C();
            }
        }

        /* renamed from: y */
        public final C5685b m23029y() {
            return this.f20085j;
        }

        /* renamed from: z */
        public final boolean m23030z() {
            return this.f20084i;
        }
    }

    /* renamed from: n */
    public static void m22990n(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    /* renamed from: a */
    public final C5721c m22991a() {
        return (C5721c) this;
    }

    /* renamed from: b */
    public final C5722d m22992b() {
        return (C5722d) this;
    }

    /* renamed from: c */
    public final C5723e m22993c() {
        return (C5723e) this;
    }

    /* renamed from: d */
    public final C5725g m22994d() {
        return (C5725g) this;
    }

    /* renamed from: e */
    public final C5726h m22995e() {
        return (C5726h) this;
    }

    /* renamed from: f */
    public final boolean m22996f() {
        return this instanceof C5720b;
    }

    /* renamed from: g */
    public final boolean m22997g() {
        return this.f20061a == TokenType.Character;
    }

    /* renamed from: h */
    public final boolean m22998h() {
        return this.f20061a == TokenType.Comment;
    }

    /* renamed from: i */
    public final boolean m22999i() {
        return this.f20061a == TokenType.Doctype;
    }

    /* renamed from: j */
    public final boolean m23000j() {
        return this.f20061a == TokenType.EOF;
    }

    /* renamed from: k */
    public final boolean m23001k() {
        return this.f20061a == TokenType.EndTag;
    }

    /* renamed from: l */
    public final boolean m23002l() {
        return this.f20061a == TokenType.StartTag;
    }

    /* renamed from: m */
    public abstract Token mo23003m();

    /* renamed from: o */
    public String m23004o() {
        return getClass().getSimpleName();
    }

    public Token() {
    }
}
