package org.jsoup.parser;

import java.io.Reader;
import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.C5686c;
import org.jsoup.nodes.C5687d;
import org.jsoup.nodes.C5689f;
import org.jsoup.nodes.C5692i;
import org.jsoup.nodes.C5693j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;
import p050d8.C4686a;
import p060e8.C4772d;
import p090h8.C5032c;
import p090h8.C5033d;
import p090h8.C5034e;

/* renamed from: org.jsoup.parser.d */
/* loaded from: classes.dex */
public class C5798d extends AbstractC5797c {

    /* renamed from: org.jsoup.parser.d$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f20208a;

        static {
            int[] iArr = new int[Token.TokenType.values().length];
            f20208a = iArr;
            try {
                iArr[Token.TokenType.StartTag.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f20208a[Token.TokenType.EndTag.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f20208a[Token.TokenType.Comment.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f20208a[Token.TokenType.Character.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f20208a[Token.TokenType.Doctype.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f20208a[Token.TokenType.EOF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: b */
    public C5032c mo23073b() {
        return C5032c.f17364d;
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: c */
    public void mo23075c(Reader reader, String str, ParseErrorList parseErrorList, C5032c c5032c) {
        super.mo23075c(reader, str, parseErrorList, c5032c);
        this.f20201d.add(this.f20200c);
        this.f20200c.m22830z0().m22842m(Document.OutputSettings.Syntax.xml);
    }

    @Override // org.jsoup.parser.AbstractC5797c
    /* renamed from: e */
    public boolean mo23078e(Token token) {
        switch (a.f20208a[token.f20061a.ordinal()]) {
            case 1:
                m23145j(token.m22995e());
                break;
            case 2:
                m23150o(token.m22994d());
                break;
            case 3:
                m23147l(token.m22992b());
                break;
            case 4:
                m23146k(token.m22991a());
                break;
            case 5:
                m23148m(token.m22993c());
                break;
            case 6:
                break;
            default:
                C4772d.m18995a("Unexpected token type: " + token.f20061a);
                break;
        }
        return true;
    }

    /* renamed from: j */
    public Element m23145j(Token.C5726h c5726h) {
        C5034e c5034eM19652l = C5034e.m19652l(c5726h.m23015A(), this.f20205h);
        Element element = new Element(c5034eM19652l, this.f20202e, this.f20205h.m19643a(c5726h.f20085j));
        m23149n(element);
        if (!c5726h.m23030z()) {
            this.f20201d.add(element);
        } else if (!c5034eM19652l.m19658f()) {
            c5034eM19652l.m19661j();
        }
        return element;
    }

    /* renamed from: k */
    public void m23146k(Token.C5721c c5721c) {
        String strM23006q = c5721c.m23006q();
        m23149n(c5721c.m22996f() ? new C5686c(strM23006q) : new C5692i(strM23006q));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [org.jsoup.nodes.g, org.jsoup.nodes.j] */
    /* JADX WARN: Type inference failed for: r5v0, types: [org.jsoup.parser.c, org.jsoup.parser.d] */
    /* renamed from: l */
    public void m23147l(Token.C5722d c5722d) {
        C5687d c5687d = new C5687d(c5722d.m23007p());
        if (c5722d.f20071c) {
            String strM22930T = c5687d.m22930T();
            if (strM22930T.length() > 1 && (strM22930T.startsWith("!") || strM22930T.startsWith("?"))) {
                Document documentM18744b = C4686a.m18744b("<" + strM22930T.substring(1, strM22930T.length() - 1) + ">", this.f20202e, C5033d.m19646e());
                if (documentM18744b.mo19259j() > 0) {
                    Element elementM22853Y = documentM18744b.m22853Y(0);
                    ?? c5693j = new C5693j(this.f20205h.m19644b(elementM22853Y.m22874v0()), strM22930T.startsWith("!"));
                    c5693j.mo19257e().m22912d(elementM22853Y.mo19257e());
                    c5687d = c5693j;
                }
            }
        }
        m23149n(c5687d);
    }

    /* renamed from: m */
    public void m23148m(Token.C5723e c5723e) {
        C5689f c5689f = new C5689f(this.f20205h.m19644b(c5723e.m23008p()), c5723e.m23010r(), c5723e.m23011s());
        c5689f.m22933U(c5723e.m23009q());
        m23149n(c5689f);
    }

    /* renamed from: n */
    public final void m23149n(AbstractC5690g abstractC5690g) {
        m23140a().m22850T(abstractC5690g);
    }

    /* renamed from: o */
    public final void m23150o(Token.C5725g c5725g) {
        Element element;
        String strM19644b = this.f20205h.m19644b(c5725g.f20077b);
        int size = this.f20201d.size() - 1;
        while (true) {
            if (size < 0) {
                element = null;
                break;
            }
            element = this.f20201d.get(size);
            if (element.mo22827v().equals(strM19644b)) {
                break;
            } else {
                size--;
            }
        }
        if (element == null) {
            return;
        }
        for (int size2 = this.f20201d.size() - 1; size2 >= 0; size2--) {
            Element element2 = this.f20201d.get(size2);
            this.f20201d.remove(size2);
            if (element2 == element) {
                return;
            }
        }
    }
}
