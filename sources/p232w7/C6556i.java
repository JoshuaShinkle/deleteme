package p232w7;

import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;

/* renamed from: w7.i */
/* loaded from: classes.dex */
public class C6556i implements InterfaceC5595c {

    /* renamed from: a */
    public String f22058a;

    public C6556i(String str) {
        if (str == null) {
            throw new IllegalArgumentException("itemId must not be 'null'");
        }
        this.f22058a = str;
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: b */
    public String mo191b() {
        return "retract";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String mo190a() {
        return "<retract id='" + this.f22058a + "'/>";
    }

    @Override // org.jivesoftware.smack.packet.InterfaceC5595c
    public String getNamespace() {
        return PubSubNamespace.EVENT.m22695a();
    }
}
