package p240x6;

import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;

/* renamed from: x6.d */
/* loaded from: classes.dex */
public class C6579d implements InterfaceC6582g {

    /* renamed from: a */
    public AbstractC5586IQ.a f22128a;

    public C6579d(AbstractC5586IQ.a aVar) {
        this.f22128a = aVar;
    }

    @Override // p240x6.InterfaceC6582g
    /* renamed from: a */
    public boolean mo25192a(AbstractC5594b abstractC5594b) {
        return (abstractC5594b instanceof AbstractC5586IQ) && ((AbstractC5586IQ) abstractC5594b).m22066B().equals(this.f22128a);
    }
}
