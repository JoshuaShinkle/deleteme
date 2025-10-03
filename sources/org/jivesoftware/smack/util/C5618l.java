package org.jivesoftware.smack.util;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: org.jivesoftware.smack.util.l */
/* loaded from: classes.dex */
public class C5618l implements Appendable, CharSequence {

    /* renamed from: c */
    public static final String f19528c = Character.toString('>');

    /* renamed from: b */
    public final C5612f f19529b;

    public C5618l() {
        this.f19529b = new C5612f();
    }

    @Override // java.lang.Appendable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C5618l append(char c9) {
        this.f19529b.append(c9);
        return this;
    }

    @Override // java.lang.Appendable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C5618l append(CharSequence charSequence) {
        this.f19529b.append(charSequence);
        return this;
    }

    @Override // java.lang.Appendable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C5618l append(CharSequence charSequence, int i9, int i10) {
        this.f19529b.append(charSequence, i9, i10);
        return this;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i9) {
        return this.f19529b.charAt(i9);
    }

    /* renamed from: d */
    public C5618l m22353d(C5618l c5618l) {
        this.f19529b.m22329d(c5618l.f19529b);
        return this;
    }

    /* renamed from: e */
    public C5618l m22354e(String str, Enum<?> r22) {
        m22355f(str, r22.name());
        return this;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5618l) {
            return toString().equals(((C5618l) obj).toString());
        }
        return false;
    }

    /* renamed from: f */
    public C5618l m22355f(String str, String str2) {
        this.f19529b.append(' ').append(str).append("='");
        m22363n(str2);
        this.f19529b.append('\'');
        return this;
    }

    /* renamed from: g */
    public C5618l m22356g(String str) {
        this.f19529b.append("</").append(str);
        m22370u();
        return this;
    }

    /* renamed from: h */
    public C5618l m22357h(InterfaceC5595c interfaceC5595c) {
        m22356g(interfaceC5595c.mo191b());
        return this;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    /* renamed from: i */
    public C5618l m22358i() {
        this.f19529b.append("/>");
        return this;
    }

    /* renamed from: j */
    public C5618l m22359j(boolean z8, String str) {
        if (z8) {
            m22362m(str);
        }
        return this;
    }

    /* renamed from: k */
    public C5618l m22360k(String str, Enum<?> r22) {
        m22361l(str, r22.name());
        return this;
    }

    /* renamed from: l */
    public C5618l m22361l(String str, String str2) {
        m22365p(str);
        m22363n(str2);
        m22356g(str);
        return this;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f19529b.length();
    }

    /* renamed from: m */
    public C5618l m22362m(String str) {
        m22364o(str);
        return m22358i();
    }

    /* renamed from: n */
    public C5618l m22363n(String str) {
        this.f19529b.append(C5616j.m22341f(str));
        return this;
    }

    /* renamed from: o */
    public C5618l m22364o(String str) {
        this.f19529b.append('<').append(str);
        return this;
    }

    /* renamed from: p */
    public C5618l m22365p(String str) {
        m22364o(str).m22370u();
        return this;
    }

    /* renamed from: q */
    public C5618l m22366q(CharSequence charSequence) {
        if (charSequence != null) {
            append(charSequence);
        }
        return this;
    }

    /* renamed from: r */
    public C5618l m22367r(String str, String str2) {
        if (str2 != null) {
            m22355f(str, str2);
        }
        return this;
    }

    /* renamed from: s */
    public C5618l m22368s(String str, String str2) {
        if (str2 != null) {
            m22361l(str, str2);
        }
        return this;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i9, int i10) {
        return this.f19529b.subSequence(i9, i10);
    }

    /* renamed from: t */
    public C5618l m22369t(InterfaceC5595c interfaceC5595c) {
        m22364o(interfaceC5595c.mo191b());
        m22372w(interfaceC5595c.getNamespace());
        return this;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f19529b.toString();
    }

    /* renamed from: u */
    public C5618l m22370u() {
        this.f19529b.append(f19528c);
        return this;
    }

    /* renamed from: v */
    public C5618l m22371v(String str) {
        m22367r("xml:lang", str);
        return this;
    }

    /* renamed from: w */
    public C5618l m22372w(String str) {
        m22367r("xmlns", str);
        return this;
    }

    public C5618l(InterfaceC5595c interfaceC5595c) {
        this();
        m22369t(interfaceC5595c);
    }
}
