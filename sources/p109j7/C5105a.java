package p109j7;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.util.C5618l;

/* renamed from: j7.a */
/* loaded from: classes.dex */
public class C5105a implements InterfaceC5595c {

    /* renamed from: a */
    public final String f17567a;

    /* renamed from: b */
    public final String f17568b;

    /* renamed from: c */
    public final String f17569c;

    public C5105a(String str, String str2, String str3) {
        this.f17567a = str;
        this.f17568b = str2;
        this.f17569c = str3;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        C5618l c5618l = new C5618l(this);
        c5618l.m22355f("hash", this.f17569c).m22355f("node", this.f17567a).m22355f("ver", this.f17568b);
        c5618l.m22358i();
        return c5618l;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "c";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return "http://jabber.org/protocol/caps";
    }
}
