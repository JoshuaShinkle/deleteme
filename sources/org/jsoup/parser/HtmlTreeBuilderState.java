package org.jsoup.parser;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.nodes.AbstractC5690g;
import org.jsoup.nodes.C5684a;
import org.jsoup.nodes.C5685b;
import org.jsoup.nodes.C5689f;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;
import p060e8.C4771c;
import p080g8.C4973b;
import p090h8.C5032c;
import p090h8.C5034e;

/* loaded from: classes.dex */
enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                return true;
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
            } else {
                if (!token.m22999i()) {
                    c5795a.m23047B0(HtmlTreeBuilderState.BeforeHtml);
                    return c5795a.mo23078e(token);
                }
                Token.C5723e c5723eM22993c = token.m22993c();
                C5689f c5689f = new C5689f(c5795a.f20205h.m19644b(c5723eM22993c.m23008p()), c5723eM22993c.m23010r(), c5723eM22993c.m23011s());
                c5689f.m22933U(c5723eM22993c.m23009q());
                c5795a.m23111w().m22850T(c5689f);
                if (c5723eM22993c.m23012t()) {
                    c5795a.m23111w().m22823B0(Document.QuirksMode.quirks);
                }
                c5795a.m23047B0(HtmlTreeBuilderState.BeforeHtml);
            }
            return true;
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m22999i()) {
                c5795a.m23097p(this);
                return false;
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
            } else {
                if (HtmlTreeBuilderState.m22971i(token)) {
                    return true;
                }
                if (!token.m23002l() || !token.m22995e().m23018D().equals("html")) {
                    if ((!token.m23001k() || !C4771c.m18980b(token.m22994d().m23018D(), TtmlNode.TAG_HEAD, TtmlNode.TAG_BODY, "html", TtmlNode.TAG_BR)) && token.m23001k()) {
                        c5795a.m23097p(this);
                        return false;
                    }
                    return m22981k(token, c5795a);
                }
                c5795a.m23057L(token.m22995e());
                c5795a.m23047B0(HtmlTreeBuilderState.BeforeHead);
            }
            return true;
        }

        /* renamed from: k */
        public final boolean m22981k(Token token, C5795a c5795a) {
            c5795a.m23067V("html");
            c5795a.m23047B0(HtmlTreeBuilderState.BeforeHead);
            return c5795a.mo23078e(token);
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                return true;
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
            } else {
                if (token.m22999i()) {
                    c5795a.m23097p(this);
                    return false;
                }
                if (token.m23002l() && token.m22995e().m23018D().equals("html")) {
                    return HtmlTreeBuilderState.InBody.mo22972j(token, c5795a);
                }
                if (!token.m23002l() || !token.m22995e().m23018D().equals(TtmlNode.TAG_HEAD)) {
                    if (token.m23001k() && C4771c.m18980b(token.m22994d().m23018D(), TtmlNode.TAG_HEAD, TtmlNode.TAG_BODY, "html", TtmlNode.TAG_BR)) {
                        c5795a.m23143g(TtmlNode.TAG_HEAD);
                        return c5795a.mo23078e(token);
                    }
                    if (token.m23001k()) {
                        c5795a.m23097p(this);
                        return false;
                    }
                    c5795a.m23143g(TtmlNode.TAG_HEAD);
                    return c5795a.mo23078e(token);
                }
                c5795a.m23118z0(c5795a.m23057L(token.m22995e()));
                c5795a.m23047B0(HtmlTreeBuilderState.InHead);
            }
            return true;
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                c5795a.m23059N(token.m22991a());
                return true;
            }
            int i9 = C5717a.f20043a[token.f20061a.ordinal()];
            if (i9 == 1) {
                c5795a.m23060O(token.m22992b());
            } else {
                if (i9 == 2) {
                    c5795a.m23097p(this);
                    return false;
                }
                if (i9 == 3) {
                    Token.C5726h c5726hM22995e = token.m22995e();
                    String strM23018D = c5726hM22995e.m23018D();
                    if (strM23018D.equals("html")) {
                        return HtmlTreeBuilderState.InBody.mo22972j(token, c5795a);
                    }
                    if (C4771c.m18980b(strM23018D, "base", "basefont", "bgsound", "command", "link")) {
                        Element elementM23061P = c5795a.m23061P(c5726hM22995e);
                        if (strM23018D.equals("base") && elementM23061P.mo19262q("href")) {
                            c5795a.m23079e0(elementM23061P);
                        }
                    } else if (strM23018D.equals("meta")) {
                        c5795a.m23061P(c5726hM22995e);
                    } else if (strM23018D.equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                        HtmlTreeBuilderState.m22969g(c5726hM22995e, c5795a);
                    } else if (C4771c.m18980b(strM23018D, "noframes", TtmlNode.TAG_STYLE)) {
                        HtmlTreeBuilderState.m22968f(c5726hM22995e, c5795a);
                    } else if (strM23018D.equals("noscript")) {
                        c5795a.m23057L(c5726hM22995e);
                        c5795a.m23047B0(HtmlTreeBuilderState.InHeadNoscript);
                    } else {
                        if (!strM23018D.equals("script")) {
                            if (!strM23018D.equals(TtmlNode.TAG_HEAD)) {
                                return m22982k(token, c5795a);
                            }
                            c5795a.m23097p(this);
                            return false;
                        }
                        c5795a.f20199b.m23139u(TokeniserState.ScriptData);
                        c5795a.m23077d0();
                        c5795a.m23047B0(HtmlTreeBuilderState.Text);
                        c5795a.m23057L(c5726hM22995e);
                    }
                } else {
                    if (i9 != 4) {
                        return m22982k(token, c5795a);
                    }
                    String strM23018D2 = token.m22994d().m23018D();
                    if (!strM23018D2.equals(TtmlNode.TAG_HEAD)) {
                        if (C4771c.m18980b(strM23018D2, TtmlNode.TAG_BODY, "html", TtmlNode.TAG_BR)) {
                            return m22982k(token, c5795a);
                        }
                        c5795a.m23097p(this);
                        return false;
                    }
                    c5795a.m23084i0();
                    c5795a.m23047B0(HtmlTreeBuilderState.AfterHead);
                }
            }
            return true;
        }

        /* renamed from: k */
        public final boolean m22982k(Token token, AbstractC5797c abstractC5797c) {
            abstractC5797c.m23142f(TtmlNode.TAG_HEAD);
            return abstractC5797c.mo23078e(token);
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m22999i()) {
                c5795a.m23097p(this);
                return true;
            }
            if (token.m23002l() && token.m22995e().m23018D().equals("html")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (token.m23001k() && token.m22994d().m23018D().equals("noscript")) {
                c5795a.m23084i0();
                c5795a.m23047B0(HtmlTreeBuilderState.InHead);
                return true;
            }
            if (HtmlTreeBuilderState.m22971i(token) || token.m22998h() || (token.m23002l() && C4771c.m18980b(token.m22995e().m23018D(), "basefont", "bgsound", "link", "meta", "noframes", TtmlNode.TAG_STYLE))) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InHead);
            }
            if (token.m23001k() && token.m22994d().m23018D().equals(TtmlNode.TAG_BR)) {
                return m22983k(token, c5795a);
            }
            if ((!token.m23002l() || !C4771c.m18980b(token.m22995e().m23018D(), TtmlNode.TAG_HEAD, "noscript")) && !token.m23001k()) {
                return m22983k(token, c5795a);
            }
            c5795a.m23097p(this);
            return false;
        }

        /* renamed from: k */
        public final boolean m22983k(Token token, C5795a c5795a) {
            c5795a.m23097p(this);
            c5795a.m23059N(new Token.C5721c().m23005p(token.toString()));
            return true;
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                c5795a.m23059N(token.m22991a());
                return true;
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
                return true;
            }
            if (token.m22999i()) {
                c5795a.m23097p(this);
                return true;
            }
            if (!token.m23002l()) {
                if (!token.m23001k()) {
                    m22984k(token, c5795a);
                    return true;
                }
                if (C4771c.m18980b(token.m22994d().m23018D(), TtmlNode.TAG_BODY, "html")) {
                    m22984k(token, c5795a);
                    return true;
                }
                c5795a.m23097p(this);
                return false;
            }
            Token.C5726h c5726hM22995e = token.m22995e();
            String strM23018D = c5726hM22995e.m23018D();
            if (strM23018D.equals("html")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (strM23018D.equals(TtmlNode.TAG_BODY)) {
                c5795a.m23057L(c5726hM22995e);
                c5795a.m23099q(false);
                c5795a.m23047B0(HtmlTreeBuilderState.InBody);
                return true;
            }
            if (strM23018D.equals("frameset")) {
                c5795a.m23057L(c5726hM22995e);
                c5795a.m23047B0(HtmlTreeBuilderState.InFrameset);
                return true;
            }
            if (!C4771c.m18980b(strM23018D, "base", "basefont", "bgsound", "link", "meta", "noframes", "script", TtmlNode.TAG_STYLE, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)) {
                if (strM23018D.equals(TtmlNode.TAG_HEAD)) {
                    c5795a.m23097p(this);
                    return false;
                }
                m22984k(token, c5795a);
                return true;
            }
            c5795a.m23097p(this);
            Element elementM23117z = c5795a.m23117z();
            c5795a.m23094n0(elementM23117z);
            c5795a.m23092m0(token, HtmlTreeBuilderState.InHead);
            c5795a.m23102r0(elementM23117z);
            return true;
        }

        /* renamed from: k */
        public final boolean m22984k(Token token, C5795a c5795a) {
            c5795a.m23143g(TtmlNode.TAG_BODY);
            c5795a.m23099q(true);
            return c5795a.mo23078e(token);
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) throws IOException {
            Element element;
            int i9 = C5717a.f20043a[token.f20061a.ordinal()];
            boolean z8 = true;
            if (i9 == 1) {
                c5795a.m23060O(token.m22992b());
            } else {
                if (i9 == 2) {
                    c5795a.m23097p(this);
                    return false;
                }
                if (i9 != 3) {
                    if (i9 == 4) {
                        Token.C5725g c5725gM22994d = token.m22994d();
                        String strM23018D = c5725gM22994d.m23018D();
                        if (C4771c.m18981c(strM23018D, C5718b.f20059p)) {
                            int i10 = 0;
                            while (i10 < 8) {
                                Element elementM23107u = c5795a.m23107u(strM23018D);
                                if (elementM23107u == null) {
                                    return m22985k(token, c5795a);
                                }
                                if (!c5795a.m23081g0(elementM23107u)) {
                                    c5795a.m23097p(this);
                                    c5795a.m23100q0(elementM23107u);
                                    return z8;
                                }
                                if (!c5795a.m23050E(elementM23107u.mo22827v())) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                if (c5795a.m23140a() != elementM23107u) {
                                    c5795a.m23097p(this);
                                }
                                ArrayList<Element> arrayListM23046B = c5795a.m23046B();
                                int size = arrayListM23046B.size();
                                boolean z9 = false;
                                Element element2 = null;
                                for (int i11 = 0; i11 < size && i11 < 64; i11++) {
                                    element = arrayListM23046B.get(i11);
                                    if (element != elementM23107u) {
                                        if (z9 && c5795a.m23074b0(element)) {
                                            break;
                                        }
                                    } else {
                                        element2 = arrayListM23046B.get(i11 - 1);
                                        z9 = z8;
                                    }
                                }
                                element = null;
                                if (element == null) {
                                    c5795a.m23088k0(elementM23107u.mo22827v());
                                    c5795a.m23100q0(elementM23107u);
                                    return z8;
                                }
                                Element elementM23085j = element;
                                Element element3 = elementM23085j;
                                for (int i12 = 0; i12 < 3; i12++) {
                                    if (c5795a.m23081g0(elementM23085j)) {
                                        elementM23085j = c5795a.m23085j(elementM23085j);
                                    }
                                    if (!c5795a.m23071Z(elementM23085j)) {
                                        c5795a.m23102r0(elementM23085j);
                                    } else {
                                        if (elementM23085j == elementM23107u) {
                                            break;
                                        }
                                        Element element4 = new Element(C5034e.m19652l(elementM23085j.mo22827v(), C5032c.f17364d), c5795a.m23109v());
                                        c5795a.m23106t0(elementM23085j, element4);
                                        c5795a.m23110v0(elementM23085j, element4);
                                        if (element3.m22869p0() != null) {
                                            element3.m22938F();
                                        }
                                        element4.m22850T(element3);
                                        elementM23085j = element4;
                                        element3 = elementM23085j;
                                    }
                                }
                                if (C4771c.m18981c(element2.mo22827v(), C5718b.f20060q)) {
                                    if (element3.m22869p0() != null) {
                                        element3.m22938F();
                                    }
                                    c5795a.m23063R(element3);
                                } else {
                                    if (element3.m22869p0() != null) {
                                        element3.m22938F();
                                    }
                                    element2.m22850T(element3);
                                }
                                Element element5 = new Element(elementM23107u.m22873u0(), c5795a.m23109v());
                                element5.mo19257e().m22912d(elementM23107u.mo19257e());
                                for (AbstractC5690g abstractC5690g : (AbstractC5690g[]) element.m22952k().toArray(new AbstractC5690g[element.mo19259j()])) {
                                    element5.m22850T(abstractC5690g);
                                }
                                element.m22850T(element5);
                                c5795a.m23100q0(elementM23107u);
                                c5795a.m23102r0(elementM23107u);
                                c5795a.m23066U(element, element5);
                                i10++;
                                z8 = true;
                            }
                        } else if (C4771c.m18981c(strM23018D, C5718b.f20058o)) {
                            if (!c5795a.m23050E(strM23018D)) {
                                c5795a.m23097p(this);
                                return false;
                            }
                            c5795a.m23103s();
                            if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                c5795a.m23097p(this);
                            }
                            c5795a.m23088k0(strM23018D);
                        } else {
                            if (strM23018D.equals(TtmlNode.TAG_SPAN)) {
                                return m22985k(token, c5795a);
                            }
                            if (strM23018D.equals("li")) {
                                if (!c5795a.m23049D(strM23018D)) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                c5795a.m23105t(strM23018D);
                                if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                    c5795a.m23097p(this);
                                }
                                c5795a.m23088k0(strM23018D);
                            } else if (strM23018D.equals(TtmlNode.TAG_BODY)) {
                                if (!c5795a.m23050E(TtmlNode.TAG_BODY)) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                c5795a.m23047B0(HtmlTreeBuilderState.AfterBody);
                            } else if (strM23018D.equals("html")) {
                                if (c5795a.m23142f(TtmlNode.TAG_BODY)) {
                                    return c5795a.mo23078e(c5725gM22994d);
                                }
                            } else if (strM23018D.equals("form")) {
                                C4973b c4973bM23113x = c5795a.m23113x();
                                c5795a.m23114x0(null);
                                if (c4973bM23113x == null || !c5795a.m23050E(strM23018D)) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                c5795a.m23103s();
                                if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                    c5795a.m23097p(this);
                                }
                                c5795a.m23102r0(c4973bM23113x);
                            } else if (strM23018D.equals(TtmlNode.TAG_P)) {
                                if (!c5795a.m23048C(strM23018D)) {
                                    c5795a.m23097p(this);
                                    c5795a.m23143g(strM23018D);
                                    return c5795a.mo23078e(c5725gM22994d);
                                }
                                c5795a.m23105t(strM23018D);
                                if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                    c5795a.m23097p(this);
                                }
                                c5795a.m23088k0(strM23018D);
                            } else if (!C4771c.m18981c(strM23018D, C5718b.f20049f)) {
                                String[] strArr = C5718b.f20046c;
                                if (C4771c.m18981c(strM23018D, strArr)) {
                                    if (!c5795a.m23052G(strArr)) {
                                        c5795a.m23097p(this);
                                        return false;
                                    }
                                    c5795a.m23105t(strM23018D);
                                    if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                        c5795a.m23097p(this);
                                    }
                                    c5795a.m23090l0(strArr);
                                } else {
                                    if (strM23018D.equals("sarcasm")) {
                                        return m22985k(token, c5795a);
                                    }
                                    if (!C4771c.m18981c(strM23018D, C5718b.f20051h)) {
                                        if (!strM23018D.equals(TtmlNode.TAG_BR)) {
                                            return m22985k(token, c5795a);
                                        }
                                        c5795a.m23097p(this);
                                        c5795a.m23143g(TtmlNode.TAG_BR);
                                        return false;
                                    }
                                    if (!c5795a.m23050E(AppMeasurementSdk.ConditionalUserProperty.NAME)) {
                                        if (!c5795a.m23050E(strM23018D)) {
                                            c5795a.m23097p(this);
                                            return false;
                                        }
                                        c5795a.m23103s();
                                        if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                            c5795a.m23097p(this);
                                        }
                                        c5795a.m23088k0(strM23018D);
                                        c5795a.m23087k();
                                    }
                                }
                            } else {
                                if (!c5795a.m23050E(strM23018D)) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                c5795a.m23105t(strM23018D);
                                if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                                    c5795a.m23097p(this);
                                }
                                c5795a.m23088k0(strM23018D);
                            }
                        }
                    } else if (i9 == 5) {
                        Token.C5721c c5721cM22991a = token.m22991a();
                        if (c5721cM22991a.m23006q().equals(HtmlTreeBuilderState.f20041y)) {
                            c5795a.m23097p(this);
                            return false;
                        }
                        if (c5795a.m23101r() && HtmlTreeBuilderState.m22971i(c5721cM22991a)) {
                            c5795a.m23098p0();
                            c5795a.m23059N(c5721cM22991a);
                        } else {
                            c5795a.m23098p0();
                            c5795a.m23059N(c5721cM22991a);
                            c5795a.m23099q(false);
                        }
                    }
                    return z8;
                }
                Token.C5726h c5726hM22995e = token.m22995e();
                String strM23018D2 = c5726hM22995e.m23018D();
                if (strM23018D2.equals("a")) {
                    if (c5795a.m23107u("a") != null) {
                        c5795a.m23097p(this);
                        c5795a.m23142f("a");
                        Element elementM23115y = c5795a.m23115y("a");
                        if (elementM23115y != null) {
                            c5795a.m23100q0(elementM23115y);
                            c5795a.m23102r0(elementM23115y);
                        }
                    }
                    c5795a.m23098p0();
                    c5795a.m23096o0(c5795a.m23057L(c5726hM22995e));
                } else if (C4771c.m18981c(strM23018D2, C5718b.f20052i)) {
                    c5795a.m23098p0();
                    c5795a.m23061P(c5726hM22995e);
                    c5795a.m23099q(false);
                } else if (C4771c.m18981c(strM23018D2, C5718b.f20045b)) {
                    if (c5795a.m23048C(TtmlNode.TAG_P)) {
                        c5795a.m23142f(TtmlNode.TAG_P);
                    }
                    c5795a.m23057L(c5726hM22995e);
                } else if (strM23018D2.equals(TtmlNode.TAG_SPAN)) {
                    c5795a.m23098p0();
                    c5795a.m23057L(c5726hM22995e);
                } else if (strM23018D2.equals("li")) {
                    c5795a.m23099q(false);
                    ArrayList<Element> arrayListM23046B2 = c5795a.m23046B();
                    int size2 = arrayListM23046B2.size() - 1;
                    while (true) {
                        if (size2 <= 0) {
                            break;
                        }
                        Element element6 = arrayListM23046B2.get(size2);
                        if (element6.mo22827v().equals("li")) {
                            c5795a.m23142f("li");
                            break;
                        }
                        if (c5795a.m23074b0(element6) && !C4771c.m18981c(element6.mo22827v(), C5718b.f20048e)) {
                            break;
                        }
                        size2--;
                    }
                    if (c5795a.m23048C(TtmlNode.TAG_P)) {
                        c5795a.m23142f(TtmlNode.TAG_P);
                    }
                    c5795a.m23057L(c5726hM22995e);
                } else if (strM23018D2.equals("html")) {
                    c5795a.m23097p(this);
                    Element element7 = c5795a.m23046B().get(0);
                    Iterator<C5684a> it = c5726hM22995e.m23029y().iterator();
                    while (it.hasNext()) {
                        C5684a next = it.next();
                        if (!element7.mo19262q(next.getKey())) {
                            element7.mo19257e().m22926u(next);
                        }
                    }
                } else {
                    if (C4771c.m18981c(strM23018D2, C5718b.f20044a)) {
                        return c5795a.m23092m0(token, HtmlTreeBuilderState.InHead);
                    }
                    if (strM23018D2.equals(TtmlNode.TAG_BODY)) {
                        c5795a.m23097p(this);
                        ArrayList<Element> arrayListM23046B3 = c5795a.m23046B();
                        if (arrayListM23046B3.size() == 1 || (arrayListM23046B3.size() > 2 && !arrayListM23046B3.get(1).mo22827v().equals(TtmlNode.TAG_BODY))) {
                            return false;
                        }
                        c5795a.m23099q(false);
                        Element element8 = arrayListM23046B3.get(1);
                        Iterator<C5684a> it2 = c5726hM22995e.m23029y().iterator();
                        while (it2.hasNext()) {
                            C5684a next2 = it2.next();
                            if (!element8.mo19262q(next2.getKey())) {
                                element8.mo19257e().m22926u(next2);
                            }
                        }
                    } else if (strM23018D2.equals("frameset")) {
                        c5795a.m23097p(this);
                        ArrayList<Element> arrayListM23046B4 = c5795a.m23046B();
                        if (arrayListM23046B4.size() == 1 || ((arrayListM23046B4.size() > 2 && !arrayListM23046B4.get(1).mo22827v().equals(TtmlNode.TAG_BODY)) || !c5795a.m23101r())) {
                            return false;
                        }
                        Element element9 = arrayListM23046B4.get(1);
                        if (element9.m22869p0() != null) {
                            element9.m22938F();
                        }
                        for (int i13 = 1; arrayListM23046B4.size() > i13; i13 = 1) {
                            arrayListM23046B4.remove(arrayListM23046B4.size() - i13);
                        }
                        c5795a.m23057L(c5726hM22995e);
                        c5795a.m23047B0(HtmlTreeBuilderState.InFrameset);
                    } else {
                        String[] strArr2 = C5718b.f20046c;
                        if (C4771c.m18981c(strM23018D2, strArr2)) {
                            if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                c5795a.m23142f(TtmlNode.TAG_P);
                            }
                            if (C4771c.m18981c(c5795a.m23140a().mo22827v(), strArr2)) {
                                c5795a.m23097p(this);
                                c5795a.m23084i0();
                            }
                            c5795a.m23057L(c5726hM22995e);
                        } else if (C4771c.m18981c(strM23018D2, C5718b.f20047d)) {
                            if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                c5795a.m23142f(TtmlNode.TAG_P);
                            }
                            c5795a.m23057L(c5726hM22995e);
                            c5795a.f20198a.m19637u("\n");
                            c5795a.m23099q(false);
                        } else {
                            if (strM23018D2.equals("form")) {
                                if (c5795a.m23113x() != null) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                    c5795a.m23142f(TtmlNode.TAG_P);
                                }
                                c5795a.m23062Q(c5726hM22995e, true);
                                return true;
                            }
                            if (C4771c.m18981c(strM23018D2, C5718b.f20049f)) {
                                c5795a.m23099q(false);
                                ArrayList<Element> arrayListM23046B5 = c5795a.m23046B();
                                int size3 = arrayListM23046B5.size() - 1;
                                while (true) {
                                    if (size3 <= 0) {
                                        break;
                                    }
                                    Element element10 = arrayListM23046B5.get(size3);
                                    if (C4771c.m18981c(element10.mo22827v(), C5718b.f20049f)) {
                                        c5795a.m23142f(element10.mo22827v());
                                        break;
                                    }
                                    if (c5795a.m23074b0(element10) && !C4771c.m18981c(element10.mo22827v(), C5718b.f20048e)) {
                                        break;
                                    }
                                    size3--;
                                }
                                if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                    c5795a.m23142f(TtmlNode.TAG_P);
                                }
                                c5795a.m23057L(c5726hM22995e);
                            } else if (strM23018D2.equals("plaintext")) {
                                if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                    c5795a.m23142f(TtmlNode.TAG_P);
                                }
                                c5795a.m23057L(c5726hM22995e);
                                c5795a.f20199b.m23139u(TokeniserState.PLAINTEXT);
                            } else if (strM23018D2.equals("button")) {
                                if (c5795a.m23048C("button")) {
                                    c5795a.m23097p(this);
                                    c5795a.m23142f("button");
                                    c5795a.mo23078e(c5726hM22995e);
                                } else {
                                    c5795a.m23098p0();
                                    c5795a.m23057L(c5726hM22995e);
                                    c5795a.m23099q(false);
                                }
                            } else if (C4771c.m18981c(strM23018D2, C5718b.f20050g)) {
                                c5795a.m23098p0();
                                c5795a.m23096o0(c5795a.m23057L(c5726hM22995e));
                            } else if (strM23018D2.equals("nobr")) {
                                c5795a.m23098p0();
                                if (c5795a.m23050E("nobr")) {
                                    c5795a.m23097p(this);
                                    c5795a.m23142f("nobr");
                                    c5795a.m23098p0();
                                }
                                c5795a.m23096o0(c5795a.m23057L(c5726hM22995e));
                            } else if (C4771c.m18981c(strM23018D2, C5718b.f20051h)) {
                                c5795a.m23098p0();
                                c5795a.m23057L(c5726hM22995e);
                                c5795a.m23064S();
                                c5795a.m23099q(false);
                            } else if (strM23018D2.equals("table")) {
                                if (c5795a.m23111w().m22822A0() != Document.QuirksMode.quirks && c5795a.m23048C(TtmlNode.TAG_P)) {
                                    c5795a.m23142f(TtmlNode.TAG_P);
                                }
                                c5795a.m23057L(c5726hM22995e);
                                c5795a.m23099q(false);
                                c5795a.m23047B0(HtmlTreeBuilderState.InTable);
                            } else if (strM23018D2.equals("input")) {
                                c5795a.m23098p0();
                                if (!c5795a.m23061P(c5726hM22995e).mo19255c("type").equalsIgnoreCase("hidden")) {
                                    c5795a.m23099q(false);
                                }
                            } else if (C4771c.m18981c(strM23018D2, C5718b.f20053j)) {
                                c5795a.m23061P(c5726hM22995e);
                            } else if (strM23018D2.equals("hr")) {
                                if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                    c5795a.m23142f(TtmlNode.TAG_P);
                                }
                                c5795a.m23061P(c5726hM22995e);
                                c5795a.m23099q(false);
                            } else if (strM23018D2.equals("image")) {
                                if (c5795a.m23115y("svg") == null) {
                                    return c5795a.mo23078e(c5726hM22995e.m23016B("img"));
                                }
                                c5795a.m23057L(c5726hM22995e);
                            } else if (strM23018D2.equals("isindex")) {
                                c5795a.m23097p(this);
                                if (c5795a.m23113x() != null) {
                                    return false;
                                }
                                c5795a.m23143g("form");
                                if (c5726hM22995e.f20085j.m22918m("action")) {
                                    c5795a.m23113x().m22851W("action", c5726hM22995e.f20085j.m22916k("action"));
                                }
                                c5795a.m23143g("hr");
                                c5795a.m23143g("label");
                                c5795a.mo23078e(new Token.C5721c().m23005p(c5726hM22995e.f20085j.m22918m("prompt") ? c5726hM22995e.f20085j.m22916k("prompt") : "This is a searchable index. Enter search keywords: "));
                                C5685b c5685b = new C5685b();
                                Iterator<C5684a> it3 = c5726hM22995e.f20085j.iterator();
                                while (it3.hasNext()) {
                                    C5684a next3 = it3.next();
                                    if (!C4771c.m18981c(next3.getKey(), C5718b.f20054k)) {
                                        c5685b.m22926u(next3);
                                    }
                                }
                                c5685b.m22925t(AppMeasurementSdk.ConditionalUserProperty.NAME, "isindex");
                                c5795a.mo23082h("input", c5685b);
                                c5795a.m23142f("label");
                                c5795a.m23143g("hr");
                                c5795a.m23142f("form");
                            } else if (strM23018D2.equals("textarea")) {
                                c5795a.m23057L(c5726hM22995e);
                                c5795a.f20199b.m23139u(TokeniserState.Rcdata);
                                c5795a.m23077d0();
                                c5795a.m23099q(false);
                                c5795a.m23047B0(HtmlTreeBuilderState.Text);
                            } else if (strM23018D2.equals("xmp")) {
                                if (c5795a.m23048C(TtmlNode.TAG_P)) {
                                    c5795a.m23142f(TtmlNode.TAG_P);
                                }
                                c5795a.m23098p0();
                                c5795a.m23099q(false);
                                HtmlTreeBuilderState.m22968f(c5726hM22995e, c5795a);
                            } else if (strM23018D2.equals("iframe")) {
                                c5795a.m23099q(false);
                                HtmlTreeBuilderState.m22968f(c5726hM22995e, c5795a);
                            } else if (strM23018D2.equals("noembed")) {
                                HtmlTreeBuilderState.m22968f(c5726hM22995e, c5795a);
                            } else if (strM23018D2.equals("select")) {
                                c5795a.m23098p0();
                                c5795a.m23057L(c5726hM22995e);
                                c5795a.m23099q(false);
                                HtmlTreeBuilderState htmlTreeBuilderStateM23045A0 = c5795a.m23045A0();
                                if (htmlTreeBuilderStateM23045A0.equals(HtmlTreeBuilderState.InTable) || htmlTreeBuilderStateM23045A0.equals(HtmlTreeBuilderState.InCaption) || htmlTreeBuilderStateM23045A0.equals(HtmlTreeBuilderState.InTableBody) || htmlTreeBuilderStateM23045A0.equals(HtmlTreeBuilderState.InRow) || htmlTreeBuilderStateM23045A0.equals(HtmlTreeBuilderState.InCell)) {
                                    c5795a.m23047B0(HtmlTreeBuilderState.InSelectInTable);
                                } else {
                                    c5795a.m23047B0(HtmlTreeBuilderState.InSelect);
                                }
                            } else if (C4771c.m18981c(strM23018D2, C5718b.f20055l)) {
                                if (c5795a.m23140a().mo22827v().equals("option")) {
                                    c5795a.m23142f("option");
                                }
                                c5795a.m23098p0();
                                c5795a.m23057L(c5726hM22995e);
                            } else if (!C4771c.m18981c(strM23018D2, C5718b.f20056m)) {
                                if (!strM23018D2.equals("math") && !strM23018D2.equals("svg") && C4771c.m18981c(strM23018D2, C5718b.f20057n)) {
                                    c5795a.m23097p(this);
                                    return false;
                                }
                                c5795a.m23098p0();
                                c5795a.m23057L(c5726hM22995e);
                            } else if (c5795a.m23050E("ruby")) {
                                c5795a.m23103s();
                                if (!c5795a.m23140a().mo22827v().equals("ruby")) {
                                    c5795a.m23097p(this);
                                    c5795a.m23086j0("ruby");
                                }
                                c5795a.m23057L(c5726hM22995e);
                            }
                        }
                    }
                }
            }
            return true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:
        
            return true;
         */
        /* renamed from: k */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean m22985k(Token token, C5795a c5795a) {
            String strM19644b = c5795a.f20205h.m19644b(token.m22994d().m23015A());
            ArrayList<Element> arrayListM23046B = c5795a.m23046B();
            int size = arrayListM23046B.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Element element = arrayListM23046B.get(size);
                if (element.mo22827v().equals(strM19644b)) {
                    c5795a.m23105t(strM19644b);
                    if (!strM19644b.equals(c5795a.m23140a().mo22827v())) {
                        c5795a.m23097p(this);
                    }
                    c5795a.m23088k0(strM19644b);
                } else {
                    if (c5795a.m23074b0(element)) {
                        c5795a.m23097p(this);
                        return false;
                    }
                    size--;
                }
            }
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m22997g()) {
                c5795a.m23059N(token.m22991a());
                return true;
            }
            if (token.m23000j()) {
                c5795a.m23097p(this);
                c5795a.m23084i0();
                c5795a.m23047B0(c5795a.m23083h0());
                return c5795a.mo23078e(token);
            }
            if (!token.m23001k()) {
                return true;
            }
            c5795a.m23084i0();
            c5795a.m23047B0(c5795a.m23083h0());
            return true;
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m22997g()) {
                c5795a.m23080f0();
                c5795a.m23077d0();
                c5795a.m23047B0(HtmlTreeBuilderState.InTableText);
                return c5795a.mo23078e(token);
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
                return true;
            }
            if (token.m22999i()) {
                c5795a.m23097p(this);
                return false;
            }
            if (!token.m23002l()) {
                if (!token.m23001k()) {
                    if (!token.m23000j()) {
                        return m22986k(token, c5795a);
                    }
                    if (c5795a.m23140a().mo22827v().equals("html")) {
                        c5795a.m23097p(this);
                    }
                    return true;
                }
                String strM23018D = token.m22994d().m23018D();
                if (!strM23018D.equals("table")) {
                    if (!C4771c.m18980b(strM23018D, TtmlNode.TAG_BODY, "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                        return m22986k(token, c5795a);
                    }
                    c5795a.m23097p(this);
                    return false;
                }
                if (!c5795a.m23056K(strM23018D)) {
                    c5795a.m23097p(this);
                    return false;
                }
                c5795a.m23088k0("table");
                c5795a.m23112w0();
                return true;
            }
            Token.C5726h c5726hM22995e = token.m22995e();
            String strM23018D2 = c5726hM22995e.m23018D();
            if (strM23018D2.equals("caption")) {
                c5795a.m23093n();
                c5795a.m23064S();
                c5795a.m23057L(c5726hM22995e);
                c5795a.m23047B0(HtmlTreeBuilderState.InCaption);
            } else if (strM23018D2.equals("colgroup")) {
                c5795a.m23093n();
                c5795a.m23057L(c5726hM22995e);
                c5795a.m23047B0(HtmlTreeBuilderState.InColumnGroup);
            } else {
                if (strM23018D2.equals("col")) {
                    c5795a.m23143g("colgroup");
                    return c5795a.mo23078e(token);
                }
                if (C4771c.m18980b(strM23018D2, "tbody", "tfoot", "thead")) {
                    c5795a.m23093n();
                    c5795a.m23057L(c5726hM22995e);
                    c5795a.m23047B0(HtmlTreeBuilderState.InTableBody);
                } else {
                    if (C4771c.m18980b(strM23018D2, "td", "th", "tr")) {
                        c5795a.m23143g("tbody");
                        return c5795a.mo23078e(token);
                    }
                    if (strM23018D2.equals("table")) {
                        c5795a.m23097p(this);
                        if (c5795a.m23142f("table")) {
                            return c5795a.mo23078e(token);
                        }
                    } else {
                        if (C4771c.m18980b(strM23018D2, TtmlNode.TAG_STYLE, "script")) {
                            return c5795a.m23092m0(token, HtmlTreeBuilderState.InHead);
                        }
                        if (strM23018D2.equals("input")) {
                            if (!c5726hM22995e.f20085j.m22916k("type").equalsIgnoreCase("hidden")) {
                                return m22986k(token, c5795a);
                            }
                            c5795a.m23061P(c5726hM22995e);
                        } else {
                            if (!strM23018D2.equals("form")) {
                                return m22986k(token, c5795a);
                            }
                            c5795a.m23097p(this);
                            if (c5795a.m23113x() != null) {
                                return false;
                            }
                            c5795a.m23062Q(c5726hM22995e, false);
                        }
                    }
                }
            }
            return true;
        }

        /* renamed from: k */
        public boolean m22986k(Token token, C5795a c5795a) {
            c5795a.m23097p(this);
            if (!C4771c.m18980b(c5795a.m23140a().mo22827v(), "table", "tbody", "tfoot", "thead", "tr")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            c5795a.m23116y0(true);
            boolean zM23092m0 = c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            c5795a.m23116y0(false);
            return zM23092m0;
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (C5717a.f20043a[token.f20061a.ordinal()] == 5) {
                Token.C5721c c5721cM22991a = token.m22991a();
                if (c5721cM22991a.m23006q().equals(HtmlTreeBuilderState.f20041y)) {
                    c5795a.m23097p(this);
                    return false;
                }
                c5795a.m23044A().add(c5721cM22991a.m23006q());
                return true;
            }
            if (c5795a.m23044A().size() > 0) {
                for (String str : c5795a.m23044A()) {
                    if (HtmlTreeBuilderState.m22970h(str)) {
                        c5795a.m23059N(new Token.C5721c().m23005p(str));
                    } else {
                        c5795a.m23097p(this);
                        if (C4771c.m18980b(c5795a.m23140a().mo22827v(), "table", "tbody", "tfoot", "thead", "tr")) {
                            c5795a.m23116y0(true);
                            c5795a.m23092m0(new Token.C5721c().m23005p(str), HtmlTreeBuilderState.InBody);
                            c5795a.m23116y0(false);
                        } else {
                            c5795a.m23092m0(new Token.C5721c().m23005p(str), HtmlTreeBuilderState.InBody);
                        }
                    }
                }
                c5795a.m23080f0();
            }
            c5795a.m23047B0(c5795a.m23083h0());
            return c5795a.mo23078e(token);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m23001k() && token.m22994d().m23018D().equals("caption")) {
                if (!c5795a.m23056K(token.m22994d().m23018D())) {
                    c5795a.m23097p(this);
                    return false;
                }
                c5795a.m23103s();
                if (!c5795a.m23140a().mo22827v().equals("caption")) {
                    c5795a.m23097p(this);
                }
                c5795a.m23088k0("caption");
                c5795a.m23087k();
                c5795a.m23047B0(HtmlTreeBuilderState.InTable);
                return true;
            }
            if ((token.m23002l() && C4771c.m18980b(token.m22995e().m23018D(), "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr")) || (token.m23001k() && token.m22994d().m23018D().equals("table"))) {
                c5795a.m23097p(this);
                if (c5795a.m23142f("caption")) {
                    return c5795a.mo23078e(token);
                }
                return true;
            }
            if (!token.m23001k() || !C4771c.m18980b(token.m22994d().m23018D(), TtmlNode.TAG_BODY, "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            c5795a.m23097p(this);
            return false;
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                c5795a.m23059N(token.m22991a());
                return true;
            }
            int i9 = C5717a.f20043a[token.f20061a.ordinal()];
            if (i9 == 1) {
                c5795a.m23060O(token.m22992b());
            } else if (i9 == 2) {
                c5795a.m23097p(this);
            } else if (i9 == 3) {
                Token.C5726h c5726hM22995e = token.m22995e();
                String strM23018D = c5726hM22995e.m23018D();
                strM23018D.hashCode();
                if (!strM23018D.equals("col")) {
                    return !strM23018D.equals("html") ? m22973k(token, c5795a) : c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
                }
                c5795a.m23061P(c5726hM22995e);
            } else {
                if (i9 != 4) {
                    if (i9 == 6 && c5795a.m23140a().mo22827v().equals("html")) {
                        return true;
                    }
                    return m22973k(token, c5795a);
                }
                if (!token.m22994d().f20078c.equals("colgroup")) {
                    return m22973k(token, c5795a);
                }
                if (c5795a.m23140a().mo22827v().equals("html")) {
                    c5795a.m23097p(this);
                    return false;
                }
                c5795a.m23084i0();
                c5795a.m23047B0(HtmlTreeBuilderState.InTable);
            }
            return true;
        }

        /* renamed from: k */
        public final boolean m22973k(Token token, AbstractC5797c abstractC5797c) {
            if (abstractC5797c.m23142f("colgroup")) {
                return abstractC5797c.mo23078e(token);
            }
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            int i9 = C5717a.f20043a[token.f20061a.ordinal()];
            if (i9 == 3) {
                Token.C5726h c5726hM22995e = token.m22995e();
                String strM23018D = c5726hM22995e.m23018D();
                if (strM23018D.equals("template")) {
                    c5795a.m23057L(c5726hM22995e);
                    return true;
                }
                if (strM23018D.equals("tr")) {
                    c5795a.m23091m();
                    c5795a.m23057L(c5726hM22995e);
                    c5795a.m23047B0(HtmlTreeBuilderState.InRow);
                    return true;
                }
                if (!C4771c.m18980b(strM23018D, "th", "td")) {
                    return C4771c.m18980b(strM23018D, "caption", "col", "colgroup", "tbody", "tfoot", "thead") ? m22975l(token, c5795a) : m22974k(token, c5795a);
                }
                c5795a.m23097p(this);
                c5795a.m23143g("tr");
                return c5795a.mo23078e(c5726hM22995e);
            }
            if (i9 != 4) {
                return m22974k(token, c5795a);
            }
            String strM23018D2 = token.m22994d().m23018D();
            if (!C4771c.m18980b(strM23018D2, "tbody", "tfoot", "thead")) {
                if (strM23018D2.equals("table")) {
                    return m22975l(token, c5795a);
                }
                if (!C4771c.m18980b(strM23018D2, TtmlNode.TAG_BODY, "caption", "col", "colgroup", "html", "td", "th", "tr")) {
                    return m22974k(token, c5795a);
                }
                c5795a.m23097p(this);
                return false;
            }
            if (!c5795a.m23056K(strM23018D2)) {
                c5795a.m23097p(this);
                return false;
            }
            c5795a.m23091m();
            c5795a.m23084i0();
            c5795a.m23047B0(HtmlTreeBuilderState.InTable);
            return true;
        }

        /* renamed from: k */
        public final boolean m22974k(Token token, C5795a c5795a) {
            return c5795a.m23092m0(token, HtmlTreeBuilderState.InTable);
        }

        /* renamed from: l */
        public final boolean m22975l(Token token, C5795a c5795a) {
            if (!c5795a.m23056K("tbody") && !c5795a.m23056K("thead") && !c5795a.m23050E("tfoot")) {
                c5795a.m23097p(this);
                return false;
            }
            c5795a.m23091m();
            c5795a.m23142f(c5795a.m23140a().mo22827v());
            return c5795a.mo23078e(token);
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m23002l()) {
                Token.C5726h c5726hM22995e = token.m22995e();
                String strM23018D = c5726hM22995e.m23018D();
                if (strM23018D.equals("template")) {
                    c5795a.m23057L(c5726hM22995e);
                    return true;
                }
                if (!C4771c.m18980b(strM23018D, "th", "td")) {
                    return C4771c.m18980b(strM23018D, "caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr") ? m22977l(token, c5795a) : m22976k(token, c5795a);
                }
                c5795a.m23095o();
                c5795a.m23057L(c5726hM22995e);
                c5795a.m23047B0(HtmlTreeBuilderState.InCell);
                c5795a.m23064S();
                return true;
            }
            if (!token.m23001k()) {
                return m22976k(token, c5795a);
            }
            String strM23018D2 = token.m22994d().m23018D();
            if (strM23018D2.equals("tr")) {
                if (!c5795a.m23056K(strM23018D2)) {
                    c5795a.m23097p(this);
                    return false;
                }
                c5795a.m23095o();
                c5795a.m23084i0();
                c5795a.m23047B0(HtmlTreeBuilderState.InTableBody);
                return true;
            }
            if (strM23018D2.equals("table")) {
                return m22977l(token, c5795a);
            }
            if (!C4771c.m18980b(strM23018D2, "tbody", "tfoot", "thead")) {
                if (!C4771c.m18980b(strM23018D2, TtmlNode.TAG_BODY, "caption", "col", "colgroup", "html", "td", "th")) {
                    return m22976k(token, c5795a);
                }
                c5795a.m23097p(this);
                return false;
            }
            if (c5795a.m23056K(strM23018D2)) {
                c5795a.m23142f("tr");
                return c5795a.mo23078e(token);
            }
            c5795a.m23097p(this);
            return false;
        }

        /* renamed from: k */
        public final boolean m22976k(Token token, C5795a c5795a) {
            return c5795a.m23092m0(token, HtmlTreeBuilderState.InTable);
        }

        /* renamed from: l */
        public final boolean m22977l(Token token, AbstractC5797c abstractC5797c) {
            if (abstractC5797c.m23142f("tr")) {
                return abstractC5797c.mo23078e(token);
            }
            return false;
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (!token.m23001k()) {
                if (!token.m23002l() || !C4771c.m18980b(token.m22995e().m23018D(), "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                    return m22978k(token, c5795a);
                }
                if (c5795a.m23056K("td") || c5795a.m23056K("th")) {
                    m22979l(c5795a);
                    return c5795a.mo23078e(token);
                }
                c5795a.m23097p(this);
                return false;
            }
            String strM23018D = token.m22994d().m23018D();
            if (!C4771c.m18980b(strM23018D, "td", "th")) {
                if (C4771c.m18980b(strM23018D, TtmlNode.TAG_BODY, "caption", "col", "colgroup", "html")) {
                    c5795a.m23097p(this);
                    return false;
                }
                if (!C4771c.m18980b(strM23018D, "table", "tbody", "tfoot", "thead", "tr")) {
                    return m22978k(token, c5795a);
                }
                if (c5795a.m23056K(strM23018D)) {
                    m22979l(c5795a);
                    return c5795a.mo23078e(token);
                }
                c5795a.m23097p(this);
                return false;
            }
            if (!c5795a.m23056K(strM23018D)) {
                c5795a.m23097p(this);
                c5795a.m23047B0(HtmlTreeBuilderState.InRow);
                return false;
            }
            c5795a.m23103s();
            if (!c5795a.m23140a().mo22827v().equals(strM23018D)) {
                c5795a.m23097p(this);
            }
            c5795a.m23088k0(strM23018D);
            c5795a.m23087k();
            c5795a.m23047B0(HtmlTreeBuilderState.InRow);
            return true;
        }

        /* renamed from: k */
        public final boolean m22978k(Token token, C5795a c5795a) {
            return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
        }

        /* renamed from: l */
        public final void m22979l(C5795a c5795a) {
            if (c5795a.m23056K("td")) {
                c5795a.m23142f("td");
            } else {
                c5795a.m23142f("th");
            }
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            String strM23018D;
            switch (C5717a.f20043a[token.f20061a.ordinal()]) {
                case 1:
                    c5795a.m23060O(token.m22992b());
                    return true;
                case 2:
                    c5795a.m23097p(this);
                    return false;
                case 3:
                    Token.C5726h c5726hM22995e = token.m22995e();
                    String strM23018D2 = c5726hM22995e.m23018D();
                    if (strM23018D2.equals("html")) {
                        return c5795a.m23092m0(c5726hM22995e, HtmlTreeBuilderState.InBody);
                    }
                    if (strM23018D2.equals("option")) {
                        if (c5795a.m23140a().mo22827v().equals("option")) {
                            c5795a.m23142f("option");
                        }
                        c5795a.m23057L(c5726hM22995e);
                    } else {
                        if (!strM23018D2.equals("optgroup")) {
                            if (strM23018D2.equals("select")) {
                                c5795a.m23097p(this);
                                return c5795a.m23142f("select");
                            }
                            if (!C4771c.m18980b(strM23018D2, "input", "keygen", "textarea")) {
                                return strM23018D2.equals("script") ? c5795a.m23092m0(token, HtmlTreeBuilderState.InHead) : m22980k(token, c5795a);
                            }
                            c5795a.m23097p(this);
                            if (!c5795a.m23053H("select")) {
                                return false;
                            }
                            c5795a.m23142f("select");
                            return c5795a.mo23078e(c5726hM22995e);
                        }
                        if (c5795a.m23140a().mo22827v().equals("option")) {
                            c5795a.m23142f("option");
                        } else if (c5795a.m23140a().mo22827v().equals("optgroup")) {
                            c5795a.m23142f("optgroup");
                        }
                        c5795a.m23057L(c5726hM22995e);
                    }
                    return true;
                case 4:
                    strM23018D = token.m22994d().m23018D();
                    strM23018D.hashCode();
                    switch (strM23018D) {
                        case "option":
                            if (c5795a.m23140a().mo22827v().equals("option")) {
                                c5795a.m23084i0();
                            } else {
                                c5795a.m23097p(this);
                            }
                            return true;
                        case "select":
                            if (!c5795a.m23053H(strM23018D)) {
                                c5795a.m23097p(this);
                                return false;
                            }
                            c5795a.m23088k0(strM23018D);
                            c5795a.m23112w0();
                            return true;
                        case "optgroup":
                            if (c5795a.m23140a().mo22827v().equals("option") && c5795a.m23085j(c5795a.m23140a()) != null && c5795a.m23085j(c5795a.m23140a()).mo22827v().equals("optgroup")) {
                                c5795a.m23142f("option");
                            }
                            if (c5795a.m23140a().mo22827v().equals("optgroup")) {
                                c5795a.m23084i0();
                            } else {
                                c5795a.m23097p(this);
                            }
                            return true;
                        default:
                            return m22980k(token, c5795a);
                    }
                case 5:
                    Token.C5721c c5721cM22991a = token.m22991a();
                    if (c5721cM22991a.m23006q().equals(HtmlTreeBuilderState.f20041y)) {
                        c5795a.m23097p(this);
                        return false;
                    }
                    c5795a.m23059N(c5721cM22991a);
                    return true;
                case 6:
                    if (!c5795a.m23140a().mo22827v().equals("html")) {
                        c5795a.m23097p(this);
                    }
                    return true;
                default:
                    return m22980k(token, c5795a);
            }
        }

        /* renamed from: k */
        public final boolean m22980k(Token token, C5795a c5795a) {
            c5795a.m23097p(this);
            return false;
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m23002l() && C4771c.m18980b(token.m22995e().m23018D(), "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th")) {
                c5795a.m23097p(this);
                c5795a.m23142f("select");
                return c5795a.mo23078e(token);
            }
            if (!token.m23001k() || !C4771c.m18980b(token.m22994d().m23018D(), "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InSelect);
            }
            c5795a.m23097p(this);
            if (!c5795a.m23056K(token.m22994d().m23018D())) {
                return false;
            }
            c5795a.m23142f("select");
            return c5795a.mo23078e(token);
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
                return true;
            }
            if (token.m22999i()) {
                c5795a.m23097p(this);
                return false;
            }
            if (token.m23002l() && token.m22995e().m23018D().equals("html")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (token.m23001k() && token.m22994d().m23018D().equals("html")) {
                if (c5795a.m23070Y()) {
                    c5795a.m23097p(this);
                    return false;
                }
                c5795a.m23047B0(HtmlTreeBuilderState.AfterAfterBody);
                return true;
            }
            if (token.m23000j()) {
                return true;
            }
            c5795a.m23097p(this);
            c5795a.m23047B0(HtmlTreeBuilderState.InBody);
            return c5795a.mo23078e(token);
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            Token.C5726h c5726hM22995e;
            if (HtmlTreeBuilderState.m22971i(token)) {
                c5795a.m23059N(token.m22991a());
            } else if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
            } else {
                if (token.m22999i()) {
                    c5795a.m23097p(this);
                    return false;
                }
                if (token.m23002l()) {
                    c5726hM22995e = token.m22995e();
                    String strM23018D = c5726hM22995e.m23018D();
                    strM23018D.hashCode();
                    switch (strM23018D) {
                        case "frameset":
                            c5795a.m23057L(c5726hM22995e);
                            break;
                        case "html":
                            return c5795a.m23092m0(c5726hM22995e, HtmlTreeBuilderState.InBody);
                        case "frame":
                            c5795a.m23061P(c5726hM22995e);
                            break;
                        case "noframes":
                            return c5795a.m23092m0(c5726hM22995e, HtmlTreeBuilderState.InHead);
                        default:
                            c5795a.m23097p(this);
                            return false;
                    }
                } else if (token.m23001k() && token.m22994d().m23018D().equals("frameset")) {
                    if (c5795a.m23140a().mo22827v().equals("html")) {
                        c5795a.m23097p(this);
                        return false;
                    }
                    c5795a.m23084i0();
                    if (!c5795a.m23070Y() && !c5795a.m23140a().mo22827v().equals("frameset")) {
                        c5795a.m23047B0(HtmlTreeBuilderState.AfterFrameset);
                    }
                } else {
                    if (!token.m23000j()) {
                        c5795a.m23097p(this);
                        return false;
                    }
                    if (!c5795a.m23140a().mo22827v().equals("html")) {
                        c5795a.m23097p(this);
                    }
                }
            }
            return true;
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (HtmlTreeBuilderState.m22971i(token)) {
                c5795a.m23059N(token.m22991a());
                return true;
            }
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
                return true;
            }
            if (token.m22999i()) {
                c5795a.m23097p(this);
                return false;
            }
            if (token.m23002l() && token.m22995e().m23018D().equals("html")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (token.m23001k() && token.m22994d().m23018D().equals("html")) {
                c5795a.m23047B0(HtmlTreeBuilderState.AfterAfterFrameset);
                return true;
            }
            if (token.m23002l() && token.m22995e().m23018D().equals("noframes")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InHead);
            }
            if (token.m23000j()) {
                return true;
            }
            c5795a.m23097p(this);
            return false;
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
                return true;
            }
            if (token.m22999i() || HtmlTreeBuilderState.m22971i(token) || (token.m23002l() && token.m22995e().m23018D().equals("html"))) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (token.m23000j()) {
                return true;
            }
            c5795a.m23097p(this);
            c5795a.m23047B0(HtmlTreeBuilderState.InBody);
            return c5795a.mo23078e(token);
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            if (token.m22998h()) {
                c5795a.m23060O(token.m22992b());
                return true;
            }
            if (token.m22999i() || HtmlTreeBuilderState.m22971i(token) || (token.m23002l() && token.m22995e().m23018D().equals("html"))) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InBody);
            }
            if (token.m23000j()) {
                return true;
            }
            if (token.m23002l() && token.m22995e().m23018D().equals("noframes")) {
                return c5795a.m23092m0(token, HtmlTreeBuilderState.InHead);
            }
            c5795a.m23097p(this);
            return false;
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /* renamed from: j */
        public boolean mo22972j(Token token, C5795a c5795a) {
            return true;
        }
    };


    /* renamed from: y */
    public static String f20041y = String.valueOf((char) 0);

    /* renamed from: org.jsoup.parser.HtmlTreeBuilderState$a */
    public static /* synthetic */ class C5717a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f20043a;

        static {
            int[] iArr = new int[Token.TokenType.values().length];
            f20043a = iArr;
            try {
                iArr[Token.TokenType.Comment.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f20043a[Token.TokenType.Doctype.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f20043a[Token.TokenType.StartTag.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f20043a[Token.TokenType.EndTag.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f20043a[Token.TokenType.Character.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f20043a[Token.TokenType.EOF.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* renamed from: org.jsoup.parser.HtmlTreeBuilderState$b */
    public static final class C5718b {

        /* renamed from: a */
        public static final String[] f20044a = {"base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", TtmlNode.TAG_STYLE, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE};

        /* renamed from: b */
        public static final String[] f20045b = {"address", "article", "aside", "blockquote", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", TtmlNode.TAG_P, "section", "summary", "ul"};

        /* renamed from: c */
        public static final String[] f20046c = {"h1", "h2", "h3", "h4", "h5", "h6"};

        /* renamed from: d */
        public static final String[] f20047d = {"listing", "pre"};

        /* renamed from: e */
        public static final String[] f20048e = {"address", TtmlNode.TAG_DIV, TtmlNode.TAG_P};

        /* renamed from: f */
        public static final String[] f20049f = {"dd", "dt"};

        /* renamed from: g */
        public static final String[] f20050g = {"b", "big", "code", "em", "font", "i", "s", "small", "strike", "strong", TtmlNode.TAG_TT, "u"};

        /* renamed from: h */
        public static final String[] f20051h = {"applet", "marquee", "object"};

        /* renamed from: i */
        public static final String[] f20052i = {"area", TtmlNode.TAG_BR, "embed", "img", "keygen", "wbr"};

        /* renamed from: j */
        public static final String[] f20053j = {"param", "source", "track"};

        /* renamed from: k */
        public static final String[] f20054k = {"action", AppMeasurementSdk.ConditionalUserProperty.NAME, "prompt"};

        /* renamed from: l */
        public static final String[] f20055l = {"optgroup", "option"};

        /* renamed from: m */
        public static final String[] f20056m = {"rp", "rt"};

        /* renamed from: n */
        public static final String[] f20057n = {"caption", "col", "colgroup", "frame", TtmlNode.TAG_HEAD, "tbody", "td", "tfoot", "th", "thead", "tr"};

        /* renamed from: o */
        public static final String[] f20058o = {"address", "article", "aside", "blockquote", "button", TtmlNode.CENTER, "details", "dir", TtmlNode.TAG_DIV, "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};

        /* renamed from: p */
        public static final String[] f20059p = {"a", "b", "big", "code", "em", "font", "i", "nobr", "s", "small", "strike", "strong", TtmlNode.TAG_TT, "u"};

        /* renamed from: q */
        public static final String[] f20060q = {"table", "tbody", "tfoot", "thead", "tr"};
    }

    /* renamed from: f */
    public static void m22968f(Token.C5726h c5726h, C5795a c5795a) {
        c5795a.f20199b.m23139u(TokeniserState.Rawtext);
        c5795a.m23077d0();
        c5795a.m23047B0(Text);
        c5795a.m23057L(c5726h);
    }

    /* renamed from: g */
    public static void m22969g(Token.C5726h c5726h, C5795a c5795a) {
        c5795a.f20199b.m23139u(TokeniserState.Rcdata);
        c5795a.m23077d0();
        c5795a.m23047B0(Text);
        c5795a.m23057L(c5726h);
    }

    /* renamed from: h */
    public static boolean m22970h(String str) {
        return C4771c.m18983e(str);
    }

    /* renamed from: i */
    public static boolean m22971i(Token token) {
        if (token.m22997g()) {
            return m22970h(token.m22991a().m23006q());
        }
        return false;
    }

    /* renamed from: j */
    public abstract boolean mo22972j(Token token, C5795a c5795a);
}
