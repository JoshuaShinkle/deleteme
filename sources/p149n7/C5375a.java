package p149n7;

import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import p130l7.C5299a;

/* renamed from: n7.a */
/* loaded from: classes.dex */
public class C5375a implements InterfaceC5595c {

    /* renamed from: a */
    public C5299a f18280a;

    /* renamed from: b */
    public AbstractC5594b f18281b;

    public C5375a(C5299a c5299a, AbstractC5594b abstractC5594b) {
        this.f18280a = c5299a;
        this.f18281b = abstractC5594b;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "forwarded";
    }

    /* renamed from: c */
    public C5299a m21084c() {
        return this.f18280a;
    }

    /* renamed from: d */
    public AbstractC5594b m21085d() {
        return this.f18281b;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(mo191b());
        sb.append(" xmlns=\"");
        sb.append(getNamespace());
        sb.append("\">");
        C5299a c5299a = this.f18280a;
        if (c5299a != null) {
            sb.append(c5299a.mo190a());
        }
        sb.append(this.f18281b.mo22057u());
        sb.append("</");
        sb.append(mo191b());
        sb.append(">");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "urn:xmpp:forward:0";
    }
}
