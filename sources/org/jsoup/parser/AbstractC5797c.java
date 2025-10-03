package org.jsoup.parser;

import java.io.Reader;
import java.util.ArrayList;
import org.jsoup.nodes.C5685b;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;
import p060e8.C4772d;
import p090h8.C5030a;
import p090h8.C5032c;

/* renamed from: org.jsoup.parser.c */
/* loaded from: classes.dex */
public abstract class AbstractC5797c {

    /* renamed from: a */
    public C5030a f20198a;

    /* renamed from: b */
    public C5796b f20199b;

    /* renamed from: c */
    public Document f20200c;

    /* renamed from: d */
    public ArrayList<Element> f20201d;

    /* renamed from: e */
    public String f20202e;

    /* renamed from: f */
    public Token f20203f;

    /* renamed from: g */
    public ParseErrorList f20204g;

    /* renamed from: h */
    public C5032c f20205h;

    /* renamed from: i */
    public Token.C5726h f20206i = new Token.C5726h();

    /* renamed from: j */
    public Token.C5725g f20207j = new Token.C5725g();

    /* renamed from: a */
    public Element m23140a() {
        int size = this.f20201d.size();
        if (size > 0) {
            return this.f20201d.get(size - 1);
        }
        return null;
    }

    /* renamed from: b */
    public abstract C5032c mo23073b();

    /* renamed from: c */
    public void mo23075c(Reader reader, String str, ParseErrorList parseErrorList, C5032c c5032c) {
        C4772d.m19005k(reader, "String input must not be null");
        C4772d.m19005k(str, "BaseURI must not be null");
        this.f20200c = new Document(str);
        this.f20205h = c5032c;
        this.f20198a = new C5030a(reader);
        this.f20204g = parseErrorList;
        this.f20203f = null;
        this.f20199b = new C5796b(this.f20198a, parseErrorList);
        this.f20201d = new ArrayList<>(32);
        this.f20202e = str;
    }

    /* renamed from: d */
    public Document m23141d(Reader reader, String str, ParseErrorList parseErrorList, C5032c c5032c) {
        mo23075c(reader, str, parseErrorList, c5032c);
        m23144i();
        return this.f20200c;
    }

    /* renamed from: e */
    public abstract boolean mo23078e(Token token);

    /* renamed from: f */
    public boolean m23142f(String str) {
        Token token = this.f20203f;
        Token.C5725g c5725g = this.f20207j;
        return token == c5725g ? mo23078e(new Token.C5725g().m23016B(str)) : mo23078e(c5725g.mo23003m().m23016B(str));
    }

    /* renamed from: g */
    public boolean m23143g(String str) {
        Token token = this.f20203f;
        Token.C5726h c5726h = this.f20206i;
        return token == c5726h ? mo23078e(new Token.C5726h().m23016B(str)) : mo23078e(c5726h.mo23003m().m23016B(str));
    }

    /* renamed from: h */
    public boolean mo23082h(String str, C5685b c5685b) {
        Token token = this.f20203f;
        Token.C5726h c5726h = this.f20206i;
        if (token == c5726h) {
            return mo23078e(new Token.C5726h().m23014G(str, c5685b));
        }
        c5726h.mo23003m();
        this.f20206i.m23014G(str, c5685b);
        return mo23078e(this.f20206i);
    }

    /* renamed from: i */
    public void m23144i() {
        Token tokenM23138t;
        do {
            tokenM23138t = this.f20199b.m23138t();
            mo23078e(tokenM23138t);
            tokenM23138t.mo23003m();
        } while (tokenM23138t.f20061a != Token.TokenType.EOF);
    }
}
