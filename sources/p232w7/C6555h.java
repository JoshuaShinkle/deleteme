package p232w7;

import org.jivesoftware.smack.packet.InterfaceC5595c;

/* renamed from: w7.h */
/* loaded from: classes.dex */
public class C6555h<E extends InterfaceC5595c> extends C6553f {

    /* renamed from: d */
    public E f22057d;

    public C6555h(String str, String str2, E e9) {
        super(str, str2);
        if (e9 == null) {
            throw new IllegalArgumentException("payload cannot be 'null'");
        }
        this.f22057d = e9;
    }

    @Override // p232w7.C6553f, p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e */
    public String mo190a() {
        StringBuilder sb = new StringBuilder("<item");
        if (m25119d() != null) {
            sb.append(" id='");
            sb.append(m25119d());
            sb.append("'");
        }
        if (m25121c() != null) {
            sb.append(" node='");
            sb.append(m25121c());
            sb.append("'");
        }
        sb.append(">");
        sb.append(this.f22057d.mo190a());
        sb.append("</item>");
        return sb.toString();
    }

    @Override // p232w7.C6553f, p232w7.C6554g
    public String toString() {
        return getClass().getName() + " | Content [" + mo190a() + "]";
    }
}
