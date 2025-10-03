package p232w7;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: w7.j */
/* loaded from: classes.dex */
public class C6557j implements InterfaceC5595c {

    /* renamed from: a */
    public String f22059a;

    /* renamed from: b */
    public String f22060b;

    /* renamed from: c */
    public String f22061c;

    public C6557j(String str, String str2, String str3) {
        this.f22059a = str;
        this.f22061c = str3;
        this.f22060b = str2;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return this.f22059a;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return this.f22061c;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return this.f22060b;
    }

    public String toString() {
        return getClass().getName() + "payload [" + mo190a() + "]";
    }
}
