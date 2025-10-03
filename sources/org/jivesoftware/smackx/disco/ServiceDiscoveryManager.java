package org.jivesoftware.smackx.disco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import p009a8.C0055a;
import p140m7.InterfaceC5339a;
import p222v6.AbstractC6491d;
import p222v6.InterfaceC6489b;
import p240x6.C6584i;

/* loaded from: classes.dex */
public class ServiceDiscoveryManager extends AbstractC6491d {

    /* renamed from: g */
    public static DiscoverInfo.C5637b f19677g = new DiscoverInfo.C5637b("client", "Smack", "pc");

    /* renamed from: h */
    public static Map<XMPPConnection, ServiceDiscoveryManager> f19678h = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: b */
    public Set<DiscoverInfo.C5637b> f19679b;

    /* renamed from: c */
    public DiscoverInfo.C5637b f19680c;

    /* renamed from: d */
    public final Set<String> f19681d;

    /* renamed from: e */
    public C0055a f19682e;

    /* renamed from: f */
    public Map<String, InterfaceC5339a> f19683f;

    /* renamed from: org.jivesoftware.smackx.disco.ServiceDiscoveryManager$a */
    public class C5633a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.m22486j(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.disco.ServiceDiscoveryManager$b */
    public class C5634b implements InterfaceC5583c {
        public C5634b() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            DiscoverItems discoverItems;
            XMPPConnection xMPPConnectionM24823a = ServiceDiscoveryManager.this.m24823a();
            if (xMPPConnectionM24823a == null || (discoverItems = (DiscoverItems) abstractC5594b) == null || discoverItems.m22066B() != AbstractC5586IQ.a.f19231b) {
                return;
            }
            DiscoverItems discoverItems2 = new DiscoverItems();
            discoverItems2.m22070F(AbstractC5586IQ.a.f19233d);
            discoverItems2.m22167t(discoverItems.m22160j());
            discoverItems2.m22166s(discoverItems.m22161k());
            discoverItems2.m22514K(discoverItems.m22513J());
            InterfaceC5339a interfaceC5339aM22491k = ServiceDiscoveryManager.this.m22491k(discoverItems.m22513J());
            if (interfaceC5339aM22491k != null) {
                discoverItems2.m22511H(interfaceC5339aM22491k.mo20984d());
                discoverItems2.m22155c(interfaceC5339aM22491k.mo20981a());
            } else if (discoverItems.m22513J() != null) {
                discoverItems2.m22070F(AbstractC5586IQ.a.f19234e);
                discoverItems2.m22164q(new XMPPError(XMPPError.C5591a.f19316h));
            }
            xMPPConnectionM24823a.m21979P(discoverItems2);
        }
    }

    /* renamed from: org.jivesoftware.smackx.disco.ServiceDiscoveryManager$c */
    public class C5635c implements InterfaceC5583c {
        public C5635c() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            DiscoverInfo discoverInfo;
            XMPPConnection xMPPConnectionM24823a = ServiceDiscoveryManager.this.m24823a();
            if (xMPPConnectionM24823a == null || (discoverInfo = (DiscoverInfo) abstractC5594b) == null || discoverInfo.m22066B() != AbstractC5586IQ.a.f19231b) {
                return;
            }
            DiscoverInfo discoverInfo2 = new DiscoverInfo();
            discoverInfo2.m22070F(AbstractC5586IQ.a.f19233d);
            discoverInfo2.m22167t(discoverInfo.m22160j());
            discoverInfo2.m22166s(discoverInfo.m22161k());
            discoverInfo2.m22503N(discoverInfo.m22502M());
            if (discoverInfo.m22502M() == null) {
                ServiceDiscoveryManager.this.m22487f(discoverInfo2);
            } else {
                InterfaceC5339a interfaceC5339aM22491k = ServiceDiscoveryManager.this.m22491k(discoverInfo.m22502M());
                if (interfaceC5339aM22491k != null) {
                    discoverInfo2.m22498I(interfaceC5339aM22491k.mo20982b());
                    discoverInfo2.m22499J(interfaceC5339aM22491k.mo20983c());
                    discoverInfo2.m22155c(interfaceC5339aM22491k.mo20981a());
                } else {
                    discoverInfo2.m22070F(AbstractC5586IQ.a.f19234e);
                    discoverInfo2.m22164q(new XMPPError(XMPPError.C5591a.f19316h));
                }
            }
            xMPPConnectionM24823a.m21979P(discoverInfo2);
        }
    }

    static {
        XMPPConnection.m21962b(new C5633a());
    }

    public ServiceDiscoveryManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19679b = new HashSet();
        this.f19680c = f19677g;
        this.f19681d = new HashSet();
        this.f19682e = null;
        this.f19683f = new ConcurrentHashMap();
        f19678h.put(xMPPConnection, this);
        m22488g("http://jabber.org/protocol/disco#info");
        m22488g("http://jabber.org/protocol/disco#items");
        xMPPConnection.m21993d(new C5634b(), new C6584i(DiscoverItems.class));
        xMPPConnection.m21993d(new C5635c(), new C6584i(DiscoverInfo.class));
    }

    /* renamed from: j */
    public static synchronized ServiceDiscoveryManager m22486j(XMPPConnection xMPPConnection) {
        ServiceDiscoveryManager serviceDiscoveryManager;
        serviceDiscoveryManager = f19678h.get(xMPPConnection);
        if (serviceDiscoveryManager == null) {
            serviceDiscoveryManager = new ServiceDiscoveryManager(xMPPConnection);
        }
        return serviceDiscoveryManager;
    }

    /* renamed from: f */
    public void m22487f(DiscoverInfo discoverInfo) {
        discoverInfo.m22499J(m22490i());
        synchronized (this.f19681d) {
            Iterator<String> it = m22489h().iterator();
            while (it.hasNext()) {
                discoverInfo.m22496G(it.next());
            }
            discoverInfo.m22154b(this.f19682e);
        }
    }

    /* renamed from: g */
    public void m22488g(String str) {
        synchronized (this.f19681d) {
            this.f19681d.add(str);
            m22494n();
        }
    }

    /* renamed from: h */
    public List<String> m22489h() {
        List<String> listUnmodifiableList;
        synchronized (this.f19681d) {
            listUnmodifiableList = Collections.unmodifiableList(new ArrayList(this.f19681d));
        }
        return listUnmodifiableList;
    }

    /* renamed from: i */
    public Set<DiscoverInfo.C5637b> m22490i() {
        HashSet hashSet = new HashSet(this.f19679b);
        hashSet.add(f19677g);
        return Collections.unmodifiableSet(hashSet);
    }

    /* renamed from: k */
    public final InterfaceC5339a m22491k(String str) {
        if (str == null) {
            return null;
        }
        return this.f19683f.get(str);
    }

    /* renamed from: l */
    public boolean m22492l(String str) {
        boolean zContains;
        synchronized (this.f19681d) {
            zContains = this.f19681d.contains(str);
        }
        return zContains;
    }

    /* renamed from: m */
    public void m22493m(String str) {
        synchronized (this.f19681d) {
            this.f19681d.remove(str);
            m22494n();
        }
    }

    /* renamed from: n */
    public final void m22494n() {
    }

    /* renamed from: o */
    public void m22495o(String str, InterfaceC5339a interfaceC5339a) {
        this.f19683f.put(str, interfaceC5339a);
    }
}
