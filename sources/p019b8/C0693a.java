package p019b8;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: b8.a */
/* loaded from: classes.dex */
public class C0693a implements InterfaceC5595c {

    /* renamed from: a */
    public boolean f3342a = false;

    /* renamed from: b */
    public boolean f3343b = false;

    /* renamed from: c */
    public boolean f3344c = false;

    /* renamed from: d */
    public boolean f3345d = false;

    /* renamed from: e */
    public boolean f3346e = true;

    /* renamed from: f */
    public String f3347f = null;

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "x";
    }

    /* renamed from: c */
    public String m3445c() {
        return this.f3347f;
    }

    /* renamed from: d */
    public boolean m3446d() {
        return this.f3345d;
    }

    /* renamed from: e */
    public boolean m3447e() {
        return this.f3343b;
    }

    /* renamed from: f */
    public boolean m3448f() {
        return this.f3344c;
    }

    /* renamed from: g */
    public boolean m3449g() {
        return this.f3342a;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "jabber:x:event";
    }

    /* renamed from: h */
    public void m3450h(boolean z8) {
        this.f3346e = z8;
    }

    /* renamed from: i */
    public void m3451i(boolean z8) {
        this.f3345d = z8;
        m3450h(false);
    }

    /* renamed from: j */
    public void m3452j(boolean z8) {
        this.f3343b = z8;
        m3450h(false);
    }

    /* renamed from: k */
    public void m3453k(boolean z8) {
        this.f3344c = z8;
        m3450h(false);
    }

    /* renamed from: l */
    public void m3454l(boolean z8) {
        this.f3342a = z8;
        m3450h(false);
    }

    /* renamed from: m */
    public void m3455m(String str) {
        this.f3347f = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        if (m3449g()) {
            sb.append("<");
            sb.append("offline");
            sb.append("/>");
        }
        if (m3447e()) {
            sb.append("<");
            sb.append("delivered");
            sb.append("/>");
        }
        if (m3448f()) {
            sb.append("<");
            sb.append("displayed");
            sb.append("/>");
        }
        if (m3446d()) {
            sb.append("<");
            sb.append("composing");
            sb.append("/>");
        }
        if (m3445c() != null) {
            sb.append("<id>");
            sb.append(m3445c());
            sb.append("</id>");
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }
}
