package p240x6;

import org.jivesoftware.smack.packet.AbstractC5594b;

/* renamed from: x6.h */
/* loaded from: classes.dex */
public class C6583h implements InterfaceC6582g {

    /* renamed from: a */
    public String f22132a;

    public C6583h(AbstractC5594b abstractC5594b) {
        this(abstractC5594b.m22161k());
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        return this.f22132a.equals(abstractC5594b.m22161k());
    }

    public String toString() {
        return "PacketIDFilter by id: " + this.f22132a;
    }

    public C6583h(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Packet ID must not be null.");
        }
        this.f22132a = str;
    }
}
