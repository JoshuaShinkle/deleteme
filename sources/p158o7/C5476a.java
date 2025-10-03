package p158o7;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: o7.a */
/* loaded from: classes.dex */
public class C5476a implements InterfaceC5595c {

    /* renamed from: a */
    public final String f18454a;

    /* renamed from: b */
    public final boolean f18455b;

    /* renamed from: c */
    public final String f18456c;

    public C5476a(String str, String str2, boolean z8) {
        this.f18456c = str;
        this.f18454a = str2;
        this.f18455b = z8;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "chunk";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<chunk xmlns='urn:xmpp:http' streamId='" + this.f18454a + "' last='" + Boolean.toString(this.f18455b) + "'>" + this.f18456c + "</chunk>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:http";
    }
}
