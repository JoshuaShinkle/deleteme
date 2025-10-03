package p232w7;

import org.jivesoftware.smackx.pubsub.PubSubElementType;

/* renamed from: w7.f */
/* loaded from: classes.dex */
public class C6553f extends C6554g {

    /* renamed from: c */
    public String f22054c;

    public C6553f(String str, String str2) {
        super(PubSubElementType.f19855m, str2);
        this.f22054c = str;
    }

    /* renamed from: d */
    public String m25119d() {
        return this.f22054c;
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        StringBuilder sb = new StringBuilder("<item");
        if (this.f22054c != null) {
            sb.append(" id='");
            sb.append(this.f22054c);
            sb.append("'");
        }
        if (m25121c() != null) {
            sb.append(" node='");
            sb.append(m25121c());
            sb.append("'");
        }
        sb.append("/>");
        return sb.toString();
    }

    @Override // p232w7.C6554g, org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return null;
    }

    @Override // p232w7.C6554g
    public String toString() {
        return getClass().getName() + " | Content [" + mo190a() + "]";
    }
}
