package p232w7;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.pubsub.EventElementType;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

/* renamed from: w7.d */
/* loaded from: classes.dex */
public class C6551d implements InterfaceC5595c {

    /* renamed from: a */
    public EventElementType f22051a;

    /* renamed from: b */
    public C6554g f22052b;

    public C6551d(EventElementType eventElementType, C6554g c6554g) {
        this.f22051a = eventElementType;
        this.f22052b = c6554g;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "event";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return ("<event xmlns='" + PubSubNamespace.EVENT.m22695a() + "'>") + this.f22052b.mo190a() + "</event>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return PubSubNamespace.EVENT.m22695a();
    }
}
