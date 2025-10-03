package org.jsoup.parser;

import java.io.IOException;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.p159io.IOUtils;
import org.jsoup.parser.Token;
import p090h8.C5030a;
import p147n5.C5370h;

/* loaded from: classes.dex */
enum TokeniserState {
    Data { // from class: org.jsoup.parser.TokeniserState.1
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19633q = c5030a.m19633q();
            if (cM19633q == 0) {
                c5796b.m23136r(this);
                c5796b.m23127i(c5030a.m19620d());
            } else {
                if (cM19633q == '&') {
                    c5796b.m23119a(TokeniserState.CharacterReferenceInData);
                    return;
                }
                if (cM19633q == '<') {
                    c5796b.m23119a(TokeniserState.TagOpen);
                } else if (cM19633q != 65535) {
                    c5796b.m23128j(c5030a.m19621e());
                } else {
                    c5796b.m23129k(new Token.C5724f());
                }
            }
        }
    },
    CharacterReferenceInData { // from class: org.jsoup.parser.TokeniserState.2
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            TokeniserState.m23039j(c5796b, TokeniserState.Data);
        }
    },
    Rcdata { // from class: org.jsoup.parser.TokeniserState.3
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19633q = c5030a.m19633q();
            if (cM19633q == 0) {
                c5796b.m23136r(this);
                c5030a.m19618a();
                c5796b.m23127i((char) 65533);
            } else {
                if (cM19633q == '&') {
                    c5796b.m23119a(TokeniserState.CharacterReferenceInRcdata);
                    return;
                }
                if (cM19633q == '<') {
                    c5796b.m23119a(TokeniserState.RcdataLessthanSign);
                } else if (cM19633q != 65535) {
                    c5796b.m23128j(c5030a.m19629m('&', '<', 0));
                } else {
                    c5796b.m23129k(new Token.C5724f());
                }
            }
        }
    },
    CharacterReferenceInRcdata { // from class: org.jsoup.parser.TokeniserState.4
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            TokeniserState.m23039j(c5796b, TokeniserState.Rcdata);
        }
    },
    Rawtext { // from class: org.jsoup.parser.TokeniserState.5
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23040k(c5796b, c5030a, this, TokeniserState.RawtextLessthanSign);
        }
    },
    ScriptData { // from class: org.jsoup.parser.TokeniserState.6
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23040k(c5796b, c5030a, this, TokeniserState.ScriptDataLessthanSign);
        }
    },
    PLAINTEXT { // from class: org.jsoup.parser.TokeniserState.7
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19633q = c5030a.m19633q();
            if (cM19633q == 0) {
                c5796b.m23136r(this);
                c5030a.m19618a();
                c5796b.m23127i((char) 65533);
            } else if (cM19633q != 65535) {
                c5796b.m23128j(c5030a.m19627k((char) 0));
            } else {
                c5796b.m23129k(new Token.C5724f());
            }
        }
    },
    TagOpen { // from class: org.jsoup.parser.TokeniserState.8
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19633q = c5030a.m19633q();
            if (cM19633q == '!') {
                c5796b.m23119a(TokeniserState.MarkupDeclarationOpen);
                return;
            }
            if (cM19633q == '/') {
                c5796b.m23119a(TokeniserState.EndTagOpen);
                return;
            }
            if (cM19633q == '?') {
                c5796b.m23119a(TokeniserState.BogusComment);
                return;
            }
            if (c5030a.m19612C()) {
                c5796b.m23125g(true);
                c5796b.m23139u(TokeniserState.TagName);
            } else {
                c5796b.m23136r(this);
                c5796b.m23127i('<');
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    EndTagOpen { // from class: org.jsoup.parser.TokeniserState.9
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (c5030a.m19634r()) {
                c5796b.m23134p(this);
                c5796b.m23128j("</");
                c5796b.m23139u(TokeniserState.Data);
            } else if (c5030a.m19612C()) {
                c5796b.m23125g(false);
                c5796b.m23139u(TokeniserState.TagName);
            } else if (c5030a.m19639w('>')) {
                c5796b.m23136r(this);
                c5796b.m23119a(TokeniserState.Data);
            } else {
                c5796b.m23136r(this);
                c5796b.m23119a(TokeniserState.BogusComment);
            }
        }
    },
    TagName { // from class: org.jsoup.parser.TokeniserState.10
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            c5796b.f20189i.m23026v(c5030a.m19626j());
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.f20189i.m23026v(TokeniserState.f20152u0);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d == '/') {
                    c5796b.m23139u(TokeniserState.SelfClosingStartTag);
                    return;
                }
                if (cM19620d == '>') {
                    c5796b.m23133o();
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                } else if (cM19620d == 65535) {
                    c5796b.m23134p(this);
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                } else if (cM19620d != '\t' && cM19620d != '\n' && cM19620d != '\f' && cM19620d != '\r') {
                    c5796b.f20189i.m23025u(cM19620d);
                    return;
                }
            }
            c5796b.m23139u(TokeniserState.BeforeAttributeName);
        }
    },
    RcdataLessthanSign { // from class: org.jsoup.parser.TokeniserState.11
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (c5030a.m19639w(IOUtils.DIR_SEPARATOR_UNIX)) {
                c5796b.m23126h();
                c5796b.m23119a(TokeniserState.RCDATAEndTagOpen);
                return;
            }
            if (c5030a.m19612C() && c5796b.m23120b() != null) {
                if (!c5030a.m19632p("</" + c5796b.m23120b())) {
                    c5796b.f20189i = c5796b.m23125g(false).m23016B(c5796b.m23120b());
                    c5796b.m23133o();
                    c5030a.m19617I();
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                }
            }
            c5796b.m23128j("<");
            c5796b.m23139u(TokeniserState.Rcdata);
        }
    },
    RCDATAEndTagOpen { // from class: org.jsoup.parser.TokeniserState.12
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (!c5030a.m19612C()) {
                c5796b.m23128j("</");
                c5796b.m23139u(TokeniserState.Rcdata);
            } else {
                c5796b.m23125g(false);
                c5796b.f20189i.m23025u(c5030a.m19633q());
                c5796b.f20188h.append(c5030a.m19633q());
                c5796b.m23119a(TokeniserState.RCDATAEndTagName);
            }
        }
    },
    RCDATAEndTagName { // from class: org.jsoup.parser.TokeniserState.13
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            if (c5030a.m19612C()) {
                String strM19624h = c5030a.m19624h();
                c5796b.f20189i.m23026v(strM19624h);
                c5796b.f20188h.append(strM19624h);
                return;
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                if (c5796b.m23137s()) {
                    c5796b.m23139u(TokeniserState.BeforeAttributeName);
                    return;
                } else {
                    m23043m(c5796b, c5030a);
                    return;
                }
            }
            if (cM19620d == '/') {
                if (c5796b.m23137s()) {
                    c5796b.m23139u(TokeniserState.SelfClosingStartTag);
                    return;
                } else {
                    m23043m(c5796b, c5030a);
                    return;
                }
            }
            if (cM19620d != '>') {
                m23043m(c5796b, c5030a);
            } else if (!c5796b.m23137s()) {
                m23043m(c5796b, c5030a);
            } else {
                c5796b.m23133o();
                c5796b.m23139u(TokeniserState.Data);
            }
        }

        /* renamed from: m */
        public final void m23043m(C5796b c5796b, C5030a c5030a) {
            c5796b.m23128j("</" + c5796b.f20188h.toString());
            c5030a.m19617I();
            c5796b.m23139u(TokeniserState.Rcdata);
        }
    },
    RawtextLessthanSign { // from class: org.jsoup.parser.TokeniserState.14
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (c5030a.m19639w(IOUtils.DIR_SEPARATOR_UNIX)) {
                c5796b.m23126h();
                c5796b.m23119a(TokeniserState.RawtextEndTagOpen);
            } else {
                c5796b.m23127i('<');
                c5796b.m23139u(TokeniserState.Rawtext);
            }
        }
    },
    RawtextEndTagOpen { // from class: org.jsoup.parser.TokeniserState.15
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            TokeniserState.m23041l(c5796b, c5030a, TokeniserState.RawtextEndTagName, TokeniserState.Rawtext);
        }
    },
    RawtextEndTagName { // from class: org.jsoup.parser.TokeniserState.16
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23038h(c5796b, c5030a, TokeniserState.Rawtext);
        }
    },
    ScriptDataLessthanSign { // from class: org.jsoup.parser.TokeniserState.17
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '!') {
                c5796b.m23128j("<!");
                c5796b.m23139u(TokeniserState.ScriptDataEscapeStart);
            } else if (cM19620d == '/') {
                c5796b.m23126h();
                c5796b.m23139u(TokeniserState.ScriptDataEndTagOpen);
            } else {
                c5796b.m23128j("<");
                c5030a.m19617I();
                c5796b.m23139u(TokeniserState.ScriptData);
            }
        }
    },
    ScriptDataEndTagOpen { // from class: org.jsoup.parser.TokeniserState.18
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            TokeniserState.m23041l(c5796b, c5030a, TokeniserState.ScriptDataEndTagName, TokeniserState.ScriptData);
        }
    },
    ScriptDataEndTagName { // from class: org.jsoup.parser.TokeniserState.19
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23038h(c5796b, c5030a, TokeniserState.ScriptData);
        }
    },
    ScriptDataEscapeStart { // from class: org.jsoup.parser.TokeniserState.20
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (!c5030a.m19639w('-')) {
                c5796b.m23139u(TokeniserState.ScriptData);
            } else {
                c5796b.m23127i('-');
                c5796b.m23119a(TokeniserState.ScriptDataEscapeStartDash);
            }
        }
    },
    ScriptDataEscapeStartDash { // from class: org.jsoup.parser.TokeniserState.21
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (!c5030a.m19639w('-')) {
                c5796b.m23139u(TokeniserState.ScriptData);
            } else {
                c5796b.m23127i('-');
                c5796b.m23119a(TokeniserState.ScriptDataEscapedDashDash);
            }
        }
    },
    ScriptDataEscaped { // from class: org.jsoup.parser.TokeniserState.22
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            if (c5030a.m19634r()) {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            char cM19633q = c5030a.m19633q();
            if (cM19633q == 0) {
                c5796b.m23136r(this);
                c5030a.m19618a();
                c5796b.m23127i((char) 65533);
            } else if (cM19633q == '-') {
                c5796b.m23127i('-');
                c5796b.m23119a(TokeniserState.ScriptDataEscapedDash);
            } else if (cM19633q != '<') {
                c5796b.m23128j(c5030a.m19629m('-', '<', 0));
            } else {
                c5796b.m23119a(TokeniserState.ScriptDataEscapedLessthanSign);
            }
        }
    },
    ScriptDataEscapedDash { // from class: org.jsoup.parser.TokeniserState.23
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            if (c5030a.m19634r()) {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.m23127i((char) 65533);
                c5796b.m23139u(TokeniserState.ScriptDataEscaped);
            } else if (cM19620d == '-') {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataEscapedDashDash);
            } else if (cM19620d == '<') {
                c5796b.m23139u(TokeniserState.ScriptDataEscapedLessthanSign);
            } else {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.24
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            if (c5030a.m19634r()) {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.m23127i((char) 65533);
                c5796b.m23139u(TokeniserState.ScriptDataEscaped);
            } else {
                if (cM19620d == '-') {
                    c5796b.m23127i(cM19620d);
                    return;
                }
                if (cM19620d == '<') {
                    c5796b.m23139u(TokeniserState.ScriptDataEscapedLessthanSign);
                } else if (cM19620d != '>') {
                    c5796b.m23127i(cM19620d);
                    c5796b.m23139u(TokeniserState.ScriptDataEscaped);
                } else {
                    c5796b.m23127i(cM19620d);
                    c5796b.m23139u(TokeniserState.ScriptData);
                }
            }
        }
    },
    ScriptDataEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.25
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (!c5030a.m19612C()) {
                if (c5030a.m19639w(IOUtils.DIR_SEPARATOR_UNIX)) {
                    c5796b.m23126h();
                    c5796b.m23119a(TokeniserState.ScriptDataEscapedEndTagOpen);
                    return;
                } else {
                    c5796b.m23127i('<');
                    c5796b.m23139u(TokeniserState.ScriptDataEscaped);
                    return;
                }
            }
            c5796b.m23126h();
            c5796b.f20188h.append(c5030a.m19633q());
            c5796b.m23128j("<" + c5030a.m19633q());
            c5796b.m23119a(TokeniserState.ScriptDataDoubleEscapeStart);
        }
    },
    ScriptDataEscapedEndTagOpen { // from class: org.jsoup.parser.TokeniserState.26
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (!c5030a.m19612C()) {
                c5796b.m23128j("</");
                c5796b.m23139u(TokeniserState.ScriptDataEscaped);
            } else {
                c5796b.m23125g(false);
                c5796b.f20189i.m23025u(c5030a.m19633q());
                c5796b.f20188h.append(c5030a.m19633q());
                c5796b.m23119a(TokeniserState.ScriptDataEscapedEndTagName);
            }
        }
    },
    ScriptDataEscapedEndTagName { // from class: org.jsoup.parser.TokeniserState.27
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23038h(c5796b, c5030a, TokeniserState.ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart { // from class: org.jsoup.parser.TokeniserState.28
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23037g(c5796b, c5030a, TokeniserState.ScriptDataDoubleEscaped, TokeniserState.ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped { // from class: org.jsoup.parser.TokeniserState.29
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19633q = c5030a.m19633q();
            if (cM19633q == 0) {
                c5796b.m23136r(this);
                c5030a.m19618a();
                c5796b.m23127i((char) 65533);
            } else if (cM19633q == '-') {
                c5796b.m23127i(cM19633q);
                c5796b.m23119a(TokeniserState.ScriptDataDoubleEscapedDash);
            } else if (cM19633q == '<') {
                c5796b.m23127i(cM19633q);
                c5796b.m23119a(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            } else if (cM19633q != 65535) {
                c5796b.m23128j(c5030a.m19629m('-', '<', 0));
            } else {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    ScriptDataDoubleEscapedDash { // from class: org.jsoup.parser.TokeniserState.30
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.m23127i((char) 65533);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscaped);
            } else if (cM19620d == '-') {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscapedDashDash);
            } else if (cM19620d == '<') {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            } else if (cM19620d != 65535) {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscaped);
            } else {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    ScriptDataDoubleEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.31
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.m23127i((char) 65533);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscaped);
                return;
            }
            if (cM19620d == '-') {
                c5796b.m23127i(cM19620d);
                return;
            }
            if (cM19620d == '<') {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscapedLessthanSign);
            } else if (cM19620d == '>') {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptData);
            } else if (cM19620d != 65535) {
                c5796b.m23127i(cM19620d);
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscaped);
            } else {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.32
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (!c5030a.m19639w(IOUtils.DIR_SEPARATOR_UNIX)) {
                c5796b.m23139u(TokeniserState.ScriptDataDoubleEscaped);
                return;
            }
            c5796b.m23127i(IOUtils.DIR_SEPARATOR_UNIX);
            c5796b.m23126h();
            c5796b.m23119a(TokeniserState.ScriptDataDoubleEscapeEnd);
        }
    },
    ScriptDataDoubleEscapeEnd { // from class: org.jsoup.parser.TokeniserState.33
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            TokeniserState.m23037g(c5796b, c5030a, TokeniserState.ScriptDataEscaped, TokeniserState.ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName { // from class: org.jsoup.parser.TokeniserState.34
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23017C();
                c5030a.m19617I();
                c5796b.m23139u(TokeniserState.AttributeName);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d != '\"' && cM19620d != '\'') {
                    if (cM19620d == '/') {
                        c5796b.m23139u(TokeniserState.SelfClosingStartTag);
                        return;
                    }
                    if (cM19620d == 65535) {
                        c5796b.m23134p(this);
                        c5796b.m23139u(TokeniserState.Data);
                        return;
                    }
                    if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r') {
                        return;
                    }
                    switch (cM19620d) {
                        case '<':
                        case '=':
                            break;
                        case '>':
                            c5796b.m23133o();
                            c5796b.m23139u(TokeniserState.Data);
                            break;
                        default:
                            c5796b.f20189i.m23017C();
                            c5030a.m19617I();
                            c5796b.m23139u(TokeniserState.AttributeName);
                            break;
                    }
                    return;
                }
                c5796b.m23136r(this);
                c5796b.f20189i.m23017C();
                c5796b.f20189i.m23020p(cM19620d);
                c5796b.m23139u(TokeniserState.AttributeName);
            }
        }
    },
    AttributeName { // from class: org.jsoup.parser.TokeniserState.35
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            c5796b.f20189i.m23021q(c5030a.m19630n(TokeniserState.f20148s0));
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23020p((char) 65533);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d != '\"' && cM19620d != '\'') {
                    if (cM19620d == '/') {
                        c5796b.m23139u(TokeniserState.SelfClosingStartTag);
                        return;
                    }
                    if (cM19620d == 65535) {
                        c5796b.m23134p(this);
                        c5796b.m23139u(TokeniserState.Data);
                        return;
                    }
                    if (cM19620d != '\t' && cM19620d != '\n' && cM19620d != '\f' && cM19620d != '\r') {
                        switch (cM19620d) {
                            case '<':
                                break;
                            case '=':
                                c5796b.m23139u(TokeniserState.BeforeAttributeValue);
                                break;
                            case '>':
                                c5796b.m23133o();
                                c5796b.m23139u(TokeniserState.Data);
                                break;
                            default:
                                c5796b.f20189i.m23020p(cM19620d);
                                break;
                        }
                        return;
                    }
                }
                c5796b.m23136r(this);
                c5796b.f20189i.m23020p(cM19620d);
                return;
            }
            c5796b.m23139u(TokeniserState.AfterAttributeName);
        }
    },
    AfterAttributeName { // from class: org.jsoup.parser.TokeniserState.36
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23020p((char) 65533);
                c5796b.m23139u(TokeniserState.AttributeName);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d != '\"' && cM19620d != '\'') {
                    if (cM19620d == '/') {
                        c5796b.m23139u(TokeniserState.SelfClosingStartTag);
                        return;
                    }
                    if (cM19620d == 65535) {
                        c5796b.m23134p(this);
                        c5796b.m23139u(TokeniserState.Data);
                        return;
                    }
                    if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r') {
                        return;
                    }
                    switch (cM19620d) {
                        case '<':
                            break;
                        case '=':
                            c5796b.m23139u(TokeniserState.BeforeAttributeValue);
                            break;
                        case '>':
                            c5796b.m23133o();
                            c5796b.m23139u(TokeniserState.Data);
                            break;
                        default:
                            c5796b.f20189i.m23017C();
                            c5030a.m19617I();
                            c5796b.m23139u(TokeniserState.AttributeName);
                            break;
                    }
                    return;
                }
                c5796b.m23136r(this);
                c5796b.f20189i.m23017C();
                c5796b.f20189i.m23020p(cM19620d);
                c5796b.m23139u(TokeniserState.AttributeName);
            }
        }
    },
    BeforeAttributeValue { // from class: org.jsoup.parser.TokeniserState.37
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23022r((char) 65533);
                c5796b.m23139u(TokeniserState.AttributeValue_unquoted);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d == '\"') {
                    c5796b.m23139u(TokeniserState.AttributeValue_doubleQuoted);
                    return;
                }
                if (cM19620d != '`') {
                    if (cM19620d == 65535) {
                        c5796b.m23134p(this);
                        c5796b.m23133o();
                        c5796b.m23139u(TokeniserState.Data);
                        return;
                    }
                    if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r') {
                        return;
                    }
                    if (cM19620d == '&') {
                        c5030a.m19617I();
                        c5796b.m23139u(TokeniserState.AttributeValue_unquoted);
                        return;
                    }
                    if (cM19620d == '\'') {
                        c5796b.m23139u(TokeniserState.AttributeValue_singleQuoted);
                        return;
                    }
                    switch (cM19620d) {
                        case '<':
                        case '=':
                            break;
                        case '>':
                            c5796b.m23136r(this);
                            c5796b.m23133o();
                            c5796b.m23139u(TokeniserState.Data);
                            break;
                        default:
                            c5030a.m19617I();
                            c5796b.m23139u(TokeniserState.AttributeValue_unquoted);
                            break;
                    }
                    return;
                }
                c5796b.m23136r(this);
                c5796b.f20189i.m23022r(cM19620d);
                c5796b.m23139u(TokeniserState.AttributeValue_unquoted);
            }
        }
    },
    AttributeValue_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.38
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            String strM19629m = c5030a.m19629m(TokeniserState.f20146r0);
            if (strM19629m.length() > 0) {
                c5796b.f20189i.m23023s(strM19629m);
            } else {
                c5796b.f20189i.m23019F();
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23022r((char) 65533);
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23139u(TokeniserState.AfterAttributeValue_quoted);
                return;
            }
            if (cM19620d != '&') {
                if (cM19620d != 65535) {
                    c5796b.f20189i.m23022r(cM19620d);
                    return;
                } else {
                    c5796b.m23134p(this);
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                }
            }
            int[] iArrM23122d = c5796b.m23122d('\"', true);
            if (iArrM23122d != null) {
                c5796b.f20189i.m23024t(iArrM23122d);
            } else {
                c5796b.f20189i.m23022r('&');
            }
        }
    },
    AttributeValue_singleQuoted { // from class: org.jsoup.parser.TokeniserState.39
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            String strM19629m = c5030a.m19629m(TokeniserState.f20144q0);
            if (strM19629m.length() > 0) {
                c5796b.f20189i.m23023s(strM19629m);
            } else {
                c5796b.f20189i.m23019F();
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23022r((char) 65533);
                return;
            }
            if (cM19620d == 65535) {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != '&') {
                if (cM19620d != '\'') {
                    c5796b.f20189i.m23022r(cM19620d);
                    return;
                } else {
                    c5796b.m23139u(TokeniserState.AfterAttributeValue_quoted);
                    return;
                }
            }
            int[] iArrM23122d = c5796b.m23122d('\'', true);
            if (iArrM23122d != null) {
                c5796b.f20189i.m23024t(iArrM23122d);
            } else {
                c5796b.f20189i.m23022r('&');
            }
        }
    },
    AttributeValue_unquoted { // from class: org.jsoup.parser.TokeniserState.40
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            String strM19630n = c5030a.m19630n(TokeniserState.f20150t0);
            if (strM19630n.length() > 0) {
                c5796b.f20189i.m23023s(strM19630n);
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20189i.m23022r((char) 65533);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d != '\"' && cM19620d != '`') {
                    if (cM19620d == 65535) {
                        c5796b.m23134p(this);
                        c5796b.m23139u(TokeniserState.Data);
                        return;
                    }
                    if (cM19620d != '\t' && cM19620d != '\n' && cM19620d != '\f' && cM19620d != '\r') {
                        if (cM19620d == '&') {
                            int[] iArrM23122d = c5796b.m23122d('>', true);
                            if (iArrM23122d != null) {
                                c5796b.f20189i.m23024t(iArrM23122d);
                                return;
                            } else {
                                c5796b.f20189i.m23022r('&');
                                return;
                            }
                        }
                        if (cM19620d != '\'') {
                            switch (cM19620d) {
                                case '<':
                                case '=':
                                    break;
                                case '>':
                                    c5796b.m23133o();
                                    c5796b.m23139u(TokeniserState.Data);
                                    break;
                                default:
                                    c5796b.f20189i.m23022r(cM19620d);
                                    break;
                            }
                            return;
                        }
                    }
                }
                c5796b.m23136r(this);
                c5796b.f20189i.m23022r(cM19620d);
                return;
            }
            c5796b.m23139u(TokeniserState.BeforeAttributeName);
        }
    },
    AfterAttributeValue_quoted { // from class: org.jsoup.parser.TokeniserState.41
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                c5796b.m23139u(TokeniserState.BeforeAttributeName);
                return;
            }
            if (cM19620d == '/') {
                c5796b.m23139u(TokeniserState.SelfClosingStartTag);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23133o();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d == 65535) {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
            } else {
                c5796b.m23136r(this);
                c5030a.m19617I();
                c5796b.m23139u(TokeniserState.BeforeAttributeName);
            }
        }
    },
    SelfClosingStartTag { // from class: org.jsoup.parser.TokeniserState.42
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '>') {
                c5796b.f20189i.f20084i = true;
                c5796b.m23133o();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d == 65535) {
                c5796b.m23134p(this);
                c5796b.m23139u(TokeniserState.Data);
            } else {
                c5796b.m23136r(this);
                c5030a.m19617I();
                c5796b.m23139u(TokeniserState.BeforeAttributeName);
            }
        }
    },
    BogusComment { // from class: org.jsoup.parser.TokeniserState.43
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            c5030a.m19617I();
            Token.C5722d c5722d = new Token.C5722d();
            c5722d.f20071c = true;
            c5722d.f20070b.append(c5030a.m19627k('>'));
            c5796b.m23129k(c5722d);
            c5796b.m23119a(TokeniserState.Data);
        }
    },
    MarkupDeclarationOpen { // from class: org.jsoup.parser.TokeniserState.44
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (c5030a.m19637u("--")) {
                c5796b.m23123e();
                c5796b.m23139u(TokeniserState.CommentStart);
            } else if (c5030a.m19638v("DOCTYPE")) {
                c5796b.m23139u(TokeniserState.Doctype);
            } else if (c5030a.m19637u("[CDATA[")) {
                c5796b.m23126h();
                c5796b.m23139u(TokeniserState.CdataSection);
            } else {
                c5796b.m23136r(this);
                c5796b.m23119a(TokeniserState.BogusComment);
            }
        }
    },
    CommentStart { // from class: org.jsoup.parser.TokeniserState.45
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20194n.f20070b.append((char) 65533);
                c5796b.m23139u(TokeniserState.Comment);
                return;
            }
            if (cM19620d == '-') {
                c5796b.m23139u(TokeniserState.CommentStartDash);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d != 65535) {
                c5796b.f20194n.f20070b.append(cM19620d);
                c5796b.m23139u(TokeniserState.Comment);
            } else {
                c5796b.m23134p(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    CommentStartDash { // from class: org.jsoup.parser.TokeniserState.46
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20194n.f20070b.append((char) 65533);
                c5796b.m23139u(TokeniserState.Comment);
                return;
            }
            if (cM19620d == '-') {
                c5796b.m23139u(TokeniserState.CommentStartDash);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d != 65535) {
                c5796b.f20194n.f20070b.append(cM19620d);
                c5796b.m23139u(TokeniserState.Comment);
            } else {
                c5796b.m23134p(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    Comment { // from class: org.jsoup.parser.TokeniserState.47
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19633q = c5030a.m19633q();
            if (cM19633q == 0) {
                c5796b.m23136r(this);
                c5030a.m19618a();
                c5796b.f20194n.f20070b.append((char) 65533);
            } else if (cM19633q == '-') {
                c5796b.m23119a(TokeniserState.CommentEndDash);
            } else {
                if (cM19633q != 65535) {
                    c5796b.f20194n.f20070b.append(c5030a.m19629m('-', 0));
                    return;
                }
                c5796b.m23134p(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    CommentEndDash { // from class: org.jsoup.parser.TokeniserState.48
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                StringBuilder sb = c5796b.f20194n.f20070b;
                sb.append('-');
                sb.append((char) 65533);
                c5796b.m23139u(TokeniserState.Comment);
                return;
            }
            if (cM19620d == '-') {
                c5796b.m23139u(TokeniserState.CommentEnd);
                return;
            }
            if (cM19620d == 65535) {
                c5796b.m23134p(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else {
                StringBuilder sb2 = c5796b.f20194n.f20070b;
                sb2.append('-');
                sb2.append(cM19620d);
                c5796b.m23139u(TokeniserState.Comment);
            }
        }
    },
    CommentEnd { // from class: org.jsoup.parser.TokeniserState.49
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                StringBuilder sb = c5796b.f20194n.f20070b;
                sb.append("--");
                sb.append((char) 65533);
                c5796b.m23139u(TokeniserState.Comment);
                return;
            }
            if (cM19620d == '!') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.CommentEndBang);
                return;
            }
            if (cM19620d == '-') {
                c5796b.m23136r(this);
                c5796b.f20194n.f20070b.append('-');
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d == 65535) {
                c5796b.m23134p(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else {
                c5796b.m23136r(this);
                StringBuilder sb2 = c5796b.f20194n.f20070b;
                sb2.append("--");
                sb2.append(cM19620d);
                c5796b.m23139u(TokeniserState.Comment);
            }
        }
    },
    CommentEndBang { // from class: org.jsoup.parser.TokeniserState.50
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                StringBuilder sb = c5796b.f20194n.f20070b;
                sb.append("--!");
                sb.append((char) 65533);
                c5796b.m23139u(TokeniserState.Comment);
                return;
            }
            if (cM19620d == '-') {
                c5796b.f20194n.f20070b.append("--!");
                c5796b.m23139u(TokeniserState.CommentEndDash);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d == 65535) {
                c5796b.m23134p(this);
                c5796b.m23131m();
                c5796b.m23139u(TokeniserState.Data);
            } else {
                StringBuilder sb2 = c5796b.f20194n.f20070b;
                sb2.append("--!");
                sb2.append(cM19620d);
                c5796b.m23139u(TokeniserState.Comment);
            }
        }
    },
    Doctype { // from class: org.jsoup.parser.TokeniserState.51
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                c5796b.m23139u(TokeniserState.BeforeDoctypeName);
                return;
            }
            if (cM19620d != '>') {
                if (cM19620d != 65535) {
                    c5796b.m23136r(this);
                    c5796b.m23139u(TokeniserState.BeforeDoctypeName);
                    return;
                }
                c5796b.m23134p(this);
            }
            c5796b.m23136r(this);
            c5796b.m23124f();
            c5796b.f20193m.f20076f = true;
            c5796b.m23132n();
            c5796b.m23139u(TokeniserState.Data);
        }
    },
    BeforeDoctypeName { // from class: org.jsoup.parser.TokeniserState.52
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            if (c5030a.m19612C()) {
                c5796b.m23124f();
                c5796b.m23139u(TokeniserState.DoctypeName);
                return;
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.m23124f();
                c5796b.f20193m.f20072b.append((char) 65533);
                c5796b.m23139u(TokeniserState.DoctypeName);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d == 65535) {
                    c5796b.m23134p(this);
                    c5796b.m23124f();
                    c5796b.f20193m.f20076f = true;
                    c5796b.m23132n();
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                }
                if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r') {
                    return;
                }
                c5796b.m23124f();
                c5796b.f20193m.f20072b.append(cM19620d);
                c5796b.m23139u(TokeniserState.DoctypeName);
            }
        }
    },
    DoctypeName { // from class: org.jsoup.parser.TokeniserState.53
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            if (c5030a.m19612C()) {
                c5796b.f20193m.f20072b.append(c5030a.m19624h());
                return;
            }
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20072b.append((char) 65533);
                return;
            }
            if (cM19620d != ' ') {
                if (cM19620d == '>') {
                    c5796b.m23132n();
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                }
                if (cM19620d == 65535) {
                    c5796b.m23134p(this);
                    c5796b.f20193m.f20076f = true;
                    c5796b.m23132n();
                    c5796b.m23139u(TokeniserState.Data);
                    return;
                }
                if (cM19620d != '\t' && cM19620d != '\n' && cM19620d != '\f' && cM19620d != '\r') {
                    c5796b.f20193m.f20072b.append(cM19620d);
                    return;
                }
            }
            c5796b.m23139u(TokeniserState.AfterDoctypeName);
        }
    },
    AfterDoctypeName { // from class: org.jsoup.parser.TokeniserState.54
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) {
            if (c5030a.m19634r()) {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (c5030a.m19641y('\t', '\n', CharUtils.f19105CR, '\f', ' ')) {
                c5030a.m19618a();
                return;
            }
            if (c5030a.m19639w('>')) {
                c5796b.m23132n();
                c5796b.m23119a(TokeniserState.Data);
                return;
            }
            if (c5030a.m19638v("PUBLIC")) {
                c5796b.f20193m.f20073c = "PUBLIC";
                c5796b.m23139u(TokeniserState.AfterDoctypePublicKeyword);
            } else if (c5030a.m19638v(C5370h.type)) {
                c5796b.f20193m.f20073c = C5370h.type;
                c5796b.m23139u(TokeniserState.AfterDoctypeSystemKeyword);
            } else {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23119a(TokeniserState.BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword { // from class: org.jsoup.parser.TokeniserState.55
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                c5796b.m23139u(TokeniserState.BeforeDoctypePublicIdentifier);
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypePublicIdentifier_doubleQuoted);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypePublicIdentifier_singleQuoted);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23139u(TokeniserState.BogusDoctype);
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    BeforeDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.56
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23139u(TokeniserState.DoctypePublicIdentifier_doubleQuoted);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23139u(TokeniserState.DoctypePublicIdentifier_singleQuoted);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23139u(TokeniserState.BogusDoctype);
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.57
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20074d.append((char) 65533);
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23139u(TokeniserState.AfterDoctypePublicIdentifier);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.f20193m.f20074d.append(cM19620d);
                return;
            }
            c5796b.m23134p(this);
            c5796b.f20193m.f20076f = true;
            c5796b.m23132n();
            c5796b.m23139u(TokeniserState.Data);
        }
    },
    DoctypePublicIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.58
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20074d.append((char) 65533);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23139u(TokeniserState.AfterDoctypePublicIdentifier);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.f20193m.f20074d.append(cM19620d);
                return;
            }
            c5796b.m23134p(this);
            c5796b.f20193m.f20076f = true;
            c5796b.m23132n();
            c5796b.m23139u(TokeniserState.Data);
        }
    },
    AfterDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.59
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                c5796b.m23139u(TokeniserState.BetweenDoctypePublicAndSystemIdentifiers);
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23139u(TokeniserState.BogusDoctype);
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers { // from class: org.jsoup.parser.TokeniserState.60
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23139u(TokeniserState.BogusDoctype);
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    AfterDoctypeSystemKeyword { // from class: org.jsoup.parser.TokeniserState.61
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                c5796b.m23139u(TokeniserState.BeforeDoctypeSystemIdentifier);
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    BeforeDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.62
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_doubleQuoted);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23139u(TokeniserState.DoctypeSystemIdentifier_singleQuoted);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23139u(TokeniserState.BogusDoctype);
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.63
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20075e.append((char) 65533);
                return;
            }
            if (cM19620d == '\"') {
                c5796b.m23139u(TokeniserState.AfterDoctypeSystemIdentifier);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.f20193m.f20075e.append(cM19620d);
                return;
            }
            c5796b.m23134p(this);
            c5796b.f20193m.f20076f = true;
            c5796b.m23132n();
            c5796b.m23139u(TokeniserState.Data);
        }
    },
    DoctypeSystemIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.64
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == 0) {
                c5796b.m23136r(this);
                c5796b.f20193m.f20075e.append((char) 65533);
                return;
            }
            if (cM19620d == '\'') {
                c5796b.m23139u(TokeniserState.AfterDoctypeSystemIdentifier);
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23136r(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
                return;
            }
            if (cM19620d != 65535) {
                c5796b.f20193m.f20075e.append(cM19620d);
                return;
            }
            c5796b.m23134p(this);
            c5796b.f20193m.f20076f = true;
            c5796b.m23132n();
            c5796b.m23139u(TokeniserState.Data);
        }
    },
    AfterDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.65
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                return;
            }
            if (cM19620d == '>') {
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            } else if (cM19620d != 65535) {
                c5796b.m23136r(this);
                c5796b.m23139u(TokeniserState.BogusDoctype);
            } else {
                c5796b.m23134p(this);
                c5796b.f20193m.f20076f = true;
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    BogusDoctype { // from class: org.jsoup.parser.TokeniserState.66
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '>') {
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            } else {
                if (cM19620d != 65535) {
                    return;
                }
                c5796b.m23132n();
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    },
    CdataSection { // from class: org.jsoup.parser.TokeniserState.67
        @Override // org.jsoup.parser.TokeniserState
        /* renamed from: i */
        public void mo23042i(C5796b c5796b, C5030a c5030a) throws IOException {
            c5796b.f20188h.append(c5030a.m19628l("]]>"));
            if (c5030a.m19637u("]]>") || c5030a.m19634r()) {
                c5796b.m23129k(new Token.C5720b(c5796b.f20188h.toString()));
                c5796b.m23139u(TokeniserState.Data);
            }
        }
    };


    /* renamed from: q0 */
    public static final char[] f20144q0 = {0, '&', '\''};

    /* renamed from: r0 */
    public static final char[] f20146r0 = {0, '\"', '&'};

    /* renamed from: s0 */
    public static final char[] f20148s0 = {0, '\t', '\n', '\f', CharUtils.f19105CR, ' ', '\"', '\'', IOUtils.DIR_SEPARATOR_UNIX, '<', '=', '>'};

    /* renamed from: t0 */
    public static final char[] f20150t0 = {0, '\t', '\n', '\f', CharUtils.f19105CR, ' ', '\"', '&', '\'', '<', '=', '>', '`'};

    /* renamed from: u0 */
    public static final String f20152u0 = String.valueOf((char) 65533);

    /* renamed from: g */
    public static void m23037g(C5796b c5796b, C5030a c5030a, TokeniserState tokeniserState, TokeniserState tokeniserState2) throws IOException {
        if (c5030a.m19612C()) {
            String strM19624h = c5030a.m19624h();
            c5796b.f20188h.append(strM19624h);
            c5796b.m23128j(strM19624h);
            return;
        }
        char cM19620d = c5030a.m19620d();
        if (cM19620d != '\t' && cM19620d != '\n' && cM19620d != '\f' && cM19620d != '\r' && cM19620d != ' ' && cM19620d != '/' && cM19620d != '>') {
            c5030a.m19617I();
            c5796b.m23139u(tokeniserState2);
        } else {
            if (c5796b.f20188h.toString().equals("script")) {
                c5796b.m23139u(tokeniserState);
            } else {
                c5796b.m23139u(tokeniserState2);
            }
            c5796b.m23127i(cM19620d);
        }
    }

    /* renamed from: h */
    public static void m23038h(C5796b c5796b, C5030a c5030a, TokeniserState tokeniserState) throws IOException {
        if (c5030a.m19612C()) {
            String strM19624h = c5030a.m19624h();
            c5796b.f20189i.m23026v(strM19624h);
            c5796b.f20188h.append(strM19624h);
            return;
        }
        boolean z8 = true;
        if (c5796b.m23137s() && !c5030a.m19634r()) {
            char cM19620d = c5030a.m19620d();
            if (cM19620d == '\t' || cM19620d == '\n' || cM19620d == '\f' || cM19620d == '\r' || cM19620d == ' ') {
                c5796b.m23139u(BeforeAttributeName);
            } else if (cM19620d == '/') {
                c5796b.m23139u(SelfClosingStartTag);
            } else if (cM19620d != '>') {
                c5796b.f20188h.append(cM19620d);
            } else {
                c5796b.m23133o();
                c5796b.m23139u(Data);
            }
            z8 = false;
        }
        if (z8) {
            c5796b.m23128j("</" + c5796b.f20188h.toString());
            c5796b.m23139u(tokeniserState);
        }
    }

    /* renamed from: j */
    public static void m23039j(C5796b c5796b, TokeniserState tokeniserState) {
        int[] iArrM23122d = c5796b.m23122d(null, false);
        if (iArrM23122d == null) {
            c5796b.m23127i('&');
        } else {
            c5796b.m23130l(iArrM23122d);
        }
        c5796b.m23139u(tokeniserState);
    }

    /* renamed from: k */
    public static void m23040k(C5796b c5796b, C5030a c5030a, TokeniserState tokeniserState, TokeniserState tokeniserState2) throws IOException {
        char cM19633q = c5030a.m19633q();
        if (cM19633q == 0) {
            c5796b.m23136r(tokeniserState);
            c5030a.m19618a();
            c5796b.m23127i((char) 65533);
        } else if (cM19633q == '<') {
            c5796b.m23119a(tokeniserState2);
        } else if (cM19633q != 65535) {
            c5796b.m23128j(c5030a.m19629m('<', 0));
        } else {
            c5796b.m23129k(new Token.C5724f());
        }
    }

    /* renamed from: l */
    public static void m23041l(C5796b c5796b, C5030a c5030a, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (c5030a.m19612C()) {
            c5796b.m23125g(false);
            c5796b.m23139u(tokeniserState);
        } else {
            c5796b.m23128j("</");
            c5796b.m23139u(tokeniserState2);
        }
    }

    /* renamed from: i */
    public abstract void mo23042i(C5796b c5796b, C5030a c5030a);
}
