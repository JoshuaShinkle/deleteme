package p250y7;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: y7.a */
/* loaded from: classes.dex */
public class C6793a implements InterfaceC5595c {

    /* renamed from: a */
    public String f22531a;

    /* renamed from: b */
    public String f22532b;

    public C6793a(String str, String str2) {
        this.f22531a = str;
        this.f22532b = str2;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "header";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<header name='" + this.f22531a + "'>" + this.f22532b + "</header>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/shim";
    }
}
