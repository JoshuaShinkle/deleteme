package org.jivesoftware.smackx.privacy.packet;

/* loaded from: classes.dex */
public class PrivacyItem {

    /* renamed from: a */
    public final boolean f19806a;

    /* renamed from: b */
    public final int f19807b;

    /* renamed from: c */
    public final Type f19808c;

    /* renamed from: d */
    public final String f19809d;

    /* renamed from: e */
    public boolean f19810e;

    /* renamed from: f */
    public boolean f19811f;

    /* renamed from: g */
    public boolean f19812g;

    /* renamed from: h */
    public boolean f19813h;

    public enum Type {
        group,
        jid,
        subscription
    }

    public PrivacyItem(boolean z8, int i9) {
        this(null, null, z8, i9);
    }

    /* renamed from: a */
    public int m22663a() {
        return this.f19807b;
    }

    /* renamed from: b */
    public Type m22664b() {
        return this.f19808c;
    }

    /* renamed from: c */
    public String m22665c() {
        return this.f19809d;
    }

    /* renamed from: d */
    public boolean m22666d() {
        return this.f19806a;
    }

    /* renamed from: e */
    public boolean m22667e() {
        return (m22668f() || m22669g() || m22670h() || m22671i()) ? false : true;
    }

    /* renamed from: f */
    public boolean m22668f() {
        return this.f19810e;
    }

    /* renamed from: g */
    public boolean m22669g() {
        return this.f19811f;
    }

    /* renamed from: h */
    public boolean m22670h() {
        return this.f19812g;
    }

    /* renamed from: i */
    public boolean m22671i() {
        return this.f19813h;
    }

    /* renamed from: j */
    public void m22672j(boolean z8) {
        this.f19810e = z8;
    }

    /* renamed from: k */
    public void m22673k(boolean z8) {
        this.f19811f = z8;
    }

    /* renamed from: l */
    public void m22674l(boolean z8) {
        this.f19812g = z8;
    }

    /* renamed from: m */
    public void m22675m(boolean z8) {
        this.f19813h = z8;
    }

    /* renamed from: n */
    public String m22676n() {
        StringBuilder sb = new StringBuilder();
        sb.append("<item");
        if (m22666d()) {
            sb.append(" action=\"allow\"");
        } else {
            sb.append(" action=\"deny\"");
        }
        sb.append(" order=\"");
        sb.append(m22663a());
        sb.append("\"");
        if (m22664b() != null) {
            sb.append(" type=\"");
            sb.append(m22664b());
            sb.append("\"");
        }
        if (m22665c() != null) {
            sb.append(" value=\"");
            sb.append(m22665c());
            sb.append("\"");
        }
        if (m22667e()) {
            sb.append("/>");
        } else {
            sb.append(">");
            if (m22668f()) {
                sb.append("<iq/>");
            }
            if (m22669g()) {
                sb.append("<message/>");
            }
            if (m22670h()) {
                sb.append("<presence-in/>");
            }
            if (m22671i()) {
                sb.append("<presence-out/>");
            }
            sb.append("</item>");
        }
        return sb.toString();
    }

    public PrivacyItem(Type type, String str, boolean z8, int i9) {
        this.f19810e = false;
        this.f19811f = false;
        this.f19812g = false;
        this.f19813h = false;
        this.f19808c = type;
        this.f19809d = str;
        this.f19806a = z8;
        this.f19807b = i9;
    }
}
