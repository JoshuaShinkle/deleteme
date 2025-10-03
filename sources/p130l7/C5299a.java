package p130l7;

import java.util.Date;
import org.jivesoftware.smack.util.XmppDateTime;

/* renamed from: l7.a */
/* loaded from: classes.dex */
public class C5299a extends C5300b {

    /* renamed from: e */
    public C5300b f17994e;

    public C5299a(C5300b c5300b) {
        super(c5300b.mo20686e());
        this.f17994e = c5300b;
    }

    @Override // p130l7.C5300b, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "delay";
    }

    @Override // p130l7.C5300b
    /* renamed from: c */
    public String mo20684c() {
        return this.f17994e.mo20684c();
    }

    @Override // p130l7.C5300b
    /* renamed from: d */
    public String mo20685d() {
        return this.f17994e.mo20685d();
    }

    @Override // p130l7.C5300b
    /* renamed from: e */
    public Date mo20686e() {
        return this.f17994e.mo20686e();
    }

    @Override // p130l7.C5300b
    /* renamed from: f */
    public void mo20687f(String str) {
        this.f17994e.mo20687f(str);
    }

    @Override // p130l7.C5300b
    /* renamed from: g */
    public void mo20688g(String str) {
        this.f17994e.mo20688g(str);
    }

    @Override // p130l7.C5300b, org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:delay";
    }

    @Override // p130l7.C5300b, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: h */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\"");
        sb.append(" stamp=\"");
        sb.append(XmppDateTime.m22269f(mo20686e()));
        sb.append("\"");
        if (mo20684c() != null && mo20684c().length() > 0) {
            sb.append(" from=\"");
            sb.append(mo20684c());
            sb.append("\"");
        }
        sb.append(">");
        if (mo20685d() != null && mo20685d().length() > 0) {
            sb.append(mo20685d());
        }
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }
}
