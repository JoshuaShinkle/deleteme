package p240x6;

import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

/* renamed from: x6.i */
/* loaded from: classes.dex */
public class C6584i implements InterfaceC6582g {

    /* renamed from: b */
    public static final C6584i f22133b = new C6584i(Presence.class);

    /* renamed from: c */
    public static final C6584i f22134c = new C6584i(Message.class);

    /* renamed from: a */
    public Class<? extends AbstractC5594b> f22135a;

    public C6584i(Class<? extends AbstractC5594b> cls) {
        if (!AbstractC5594b.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Packet type must be a sub-class of Packet.");
        }
        this.f22135a = cls;
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        return this.f22135a.isInstance(abstractC5594b);
    }

    public String toString() {
        return "PacketTypeFilter: " + this.f22135a.getName();
    }
}
