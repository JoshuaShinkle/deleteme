package p232w7;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.pubsub.PubSubElementType;

/* renamed from: w7.g */
/* loaded from: classes.dex */
public class C6554g implements InterfaceC5595c {

    /* renamed from: a */
    public PubSubElementType f22055a;

    /* renamed from: b */
    public String f22056b;

    public C6554g(PubSubElementType pubSubElementType, String str) {
        this.f22055a = pubSubElementType;
        this.f22056b = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: a */
    public CharSequence mo190a() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        sb.append(mo191b());
        if (this.f22056b == null) {
            str = "";
        } else {
            str = " node='" + this.f22056b + '\'';
        }
        sb.append(str);
        sb.append("/>");
        return sb.toString();
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return this.f22055a.m22686a();
    }

    /* renamed from: c */
    public String m25121c() {
        return this.f22056b;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return this.f22055a.m22687b().m22695a();
    }

    public String toString() {
        return getClass().getName() + " - content [" + ((Object) mo190a()) + "]";
    }

    public C6554g(PubSubElementType pubSubElementType) {
        this(pubSubElementType, null);
    }
}
