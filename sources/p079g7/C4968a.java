package p079g7;

import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.bytestreams.ibb.C5622a;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* renamed from: g7.a */
/* loaded from: classes.dex */
public class C4968a implements InterfaceC5583c {

    /* renamed from: a */
    public final InBandBytestreamManager f17061a;

    /* renamed from: b */
    public final InterfaceC6582g f17062b = new C6576a(new C6584i(Close.class), new C6579d(AbstractC5586IQ.a.f19232c));

    public C4968a(InBandBytestreamManager inBandBytestreamManager) {
        this.f17061a = inBandBytestreamManager;
    }

    /* renamed from: a */
    public InterfaceC6582g m19243a() {
        return this.f17062b;
    }

    @Override // org.jivesoftware.smack.InterfaceC5583c
    public void processPacket(AbstractC5594b abstractC5594b) {
        Close close = (Close) abstractC5594b;
        C5622a c5622a = this.f17061a.m22387g().get(close.m22396H());
        if (c5622a == null) {
            this.f17061a.m22389i(close);
        } else {
            c5622a.m22392a(close);
            this.f17061a.m22387g().remove(close.m22396H());
        }
    }
}
