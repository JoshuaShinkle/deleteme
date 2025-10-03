package p059e7;

import org.jivesoftware.smackx.amp.packet.AMPExtension;

/* renamed from: e7.a */
/* loaded from: classes.dex */
public class C4768a implements AMPExtension.InterfaceC5619a {

    /* renamed from: a */
    public final String f16565a;

    public C4768a(String str) {
        if (str == null) {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        }
        this.f16565a = str;
    }

    @Override // org.jivesoftware.smackx.amp.packet.AMPExtension.InterfaceC5619a
    public String getName() {
        return "expire-at";
    }

    @Override // org.jivesoftware.smackx.amp.packet.AMPExtension.InterfaceC5619a
    public String getValue() {
        return this.f16565a;
    }
}
