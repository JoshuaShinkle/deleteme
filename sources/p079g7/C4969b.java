package p079g7;

import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import p240x6.C6576a;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* renamed from: g7.b */
/* loaded from: classes.dex */
public class C4969b implements InterfaceC5583c {

    /* renamed from: a */
    public final InBandBytestreamManager f17063a;

    /* renamed from: b */
    public final InterfaceC6582g f17064b = new C6576a(new C6584i(Data.class));

    public C4969b(InBandBytestreamManager inBandBytestreamManager) {
        this.f17063a = inBandBytestreamManager;
    }

    /* renamed from: a */
    public InterfaceC6582g m19244a() {
        return this.f17064b;
    }

    @Override // org.jivesoftware.smack.InterfaceC5583c
    public void processPacket(AbstractC5594b abstractC5594b) {
        Data data = (Data) abstractC5594b;
        if (this.f17063a.m22387g().get(data.m22398H().m19606c()) == null) {
            this.f17063a.m22389i(data);
        }
    }
}
