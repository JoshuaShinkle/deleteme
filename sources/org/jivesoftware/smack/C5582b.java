package org.jivesoftware.smack;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.XMPPError;
import p222v6.C6492e;
import p240x6.InterfaceC6582g;

/* renamed from: org.jivesoftware.smack.b */
/* loaded from: classes.dex */
public class C5582b {

    /* renamed from: a */
    public InterfaceC6582g f19197a;

    /* renamed from: b */
    public ArrayBlockingQueue<AbstractC5594b> f19198b;

    /* renamed from: c */
    public XMPPConnection f19199c;

    /* renamed from: d */
    public boolean f19200d;

    public C5582b(XMPPConnection xMPPConnection, InterfaceC6582g interfaceC6582g) {
        this(xMPPConnection, interfaceC6582g, C6492e.m24829e());
    }

    /* renamed from: a */
    public void m22021a() {
        if (this.f19200d) {
            return;
        }
        this.f19200d = true;
        this.f19199c.m21976M(this);
    }

    /* renamed from: b */
    public AbstractC5594b m22022b(long j9) {
        try {
            return this.f19198b.poll(j9, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: c */
    public AbstractC5594b m22023c() {
        return m22024d(this.f19199c.m22013y());
    }

    /* renamed from: d */
    public AbstractC5594b m22024d(long j9) throws SmackException.NoResponseException, XMPPException.XMPPErrorException {
        AbstractC5594b abstractC5594bM22022b = m22022b(j9);
        m22021a();
        if (abstractC5594bM22022b == null) {
            throw new SmackException.NoResponseException();
        }
        XMPPError xMPPErrorM22156e = abstractC5594bM22022b.m22156e();
        if (xMPPErrorM22156e == null) {
            return abstractC5594bM22022b;
        }
        throw new XMPPException.XMPPErrorException(xMPPErrorM22156e);
    }

    /* renamed from: e */
    public void m22025e(AbstractC5594b abstractC5594b) {
        if (abstractC5594b == null) {
            return;
        }
        InterfaceC6582g interfaceC6582g = this.f19197a;
        if (interfaceC6582g == null || interfaceC6582g.mo25192a(abstractC5594b)) {
            while (!this.f19198b.offer(abstractC5594b)) {
                this.f19198b.poll();
            }
        }
    }

    public C5582b(XMPPConnection xMPPConnection, InterfaceC6582g interfaceC6582g, int i9) {
        this.f19200d = false;
        this.f19199c = xMPPConnection;
        this.f19197a = interfaceC6582g;
        this.f19198b = new ArrayBlockingQueue<>(i9);
    }
}
