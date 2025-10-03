package org.jivesoftware.smackx.time;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.time.packet.Time;
import p222v6.AbstractC6491d;
import p222v6.InterfaceC6489b;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* loaded from: classes.dex */
public class EntityTimeManager extends AbstractC6491d {

    /* renamed from: c */
    public static final Map<XMPPConnection, EntityTimeManager> f19908c = new WeakHashMap();

    /* renamed from: d */
    public static final InterfaceC6582g f19909d = new C6576a(new C6584i(Time.class), new C6579d(AbstractC5586IQ.a.f19231b));

    /* renamed from: e */
    public static boolean f19910e = true;

    /* renamed from: b */
    public boolean f19911b;

    /* renamed from: org.jivesoftware.smackx.time.EntityTimeManager$a */
    public class C5671a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            EntityTimeManager.m22735f(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.time.EntityTimeManager$b */
    public class C5672b implements InterfaceC5583c {
        public C5672b() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            if (EntityTimeManager.this.f19911b) {
                EntityTimeManager.this.m24823a().m21979P(Time.m22737G(abstractC5594b));
            }
        }
    }

    static {
        XMPPConnection.m21962b(new C5671a());
    }

    public EntityTimeManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19911b = false;
        f19908c.put(xMPPConnection, this);
        if (f19910e) {
            m22736e();
        }
        xMPPConnection.m21993d(new C5672b(), f19909d);
    }

    /* renamed from: f */
    public static synchronized EntityTimeManager m22735f(XMPPConnection xMPPConnection) {
        EntityTimeManager entityTimeManager;
        entityTimeManager = f19908c.get(xMPPConnection);
        if (entityTimeManager == null) {
            entityTimeManager = new EntityTimeManager(xMPPConnection);
        }
        return entityTimeManager;
    }

    /* renamed from: e */
    public synchronized void m22736e() {
        if (this.f19911b) {
            return;
        }
        ServiceDiscoveryManager.m22486j(m24823a()).m22488g("urn:xmpp:time");
        this.f19911b = true;
    }
}
