package org.jivesoftware.smackx.bytestreams.socks5;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import p069f7.InterfaceC4791a;
import p099i7.C5058a;
import p099i7.C5060c;
import p222v6.C6488a;
import p222v6.InterfaceC6489b;

/* loaded from: classes.dex */
public final class Socks5BytestreamManager {

    /* renamed from: k */
    public static final Random f19583k;

    /* renamed from: l */
    public static final Map<XMPPConnection, Socks5BytestreamManager> f19584l;

    /* renamed from: a */
    public final XMPPConnection f19585a;

    /* renamed from: b */
    public final Map<String, InterfaceC4791a> f19586b = new ConcurrentHashMap();

    /* renamed from: c */
    public final List<InterfaceC4791a> f19587c = Collections.synchronizedList(new LinkedList());

    /* renamed from: e */
    public int f19589e = 10000;

    /* renamed from: f */
    public int f19590f = 10000;

    /* renamed from: g */
    public final List<String> f19591g = Collections.synchronizedList(new LinkedList());

    /* renamed from: h */
    public String f19592h = null;

    /* renamed from: i */
    public boolean f19593i = true;

    /* renamed from: j */
    public List<String> f19594j = Collections.synchronizedList(new LinkedList());

    /* renamed from: d */
    public final C5058a f19588d = new C5058a(this);

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager$a */
    public class C5623a implements InterfaceC6489b {

        /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager$a$a */
        public class a extends C6488a {

            /* renamed from: a */
            public final /* synthetic */ XMPPConnection f19595a;

            public a(XMPPConnection xMPPConnection) {
                this.f19595a = xMPPConnection;
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void connectionClosed() {
                Socks5BytestreamManager.m22402e(this.f19595a).m22404b();
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void connectionClosedOnError(Exception exc) {
                Socks5BytestreamManager.m22402e(this.f19595a).m22404b();
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void reconnectionSuccessful() {
                Socks5BytestreamManager.m22402e(this.f19595a);
            }
        }

        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            Socks5BytestreamManager.m22402e(xMPPConnection);
            if (xMPPConnection == null) {
                return;
            }
            xMPPConnection.m21992c(new a(xMPPConnection));
        }
    }

    static {
        XMPPConnection.m21962b(new C5623a());
        f19583k = new Random();
        f19584l = new HashMap();
    }

    public Socks5BytestreamManager(XMPPConnection xMPPConnection) {
        this.f19585a = xMPPConnection;
    }

    /* renamed from: e */
    public static synchronized Socks5BytestreamManager m22402e(XMPPConnection xMPPConnection) {
        if (xMPPConnection == null) {
            return null;
        }
        Map<XMPPConnection, Socks5BytestreamManager> map = f19584l;
        Socks5BytestreamManager socks5BytestreamManager = map.get(xMPPConnection);
        if (socks5BytestreamManager == null) {
            socks5BytestreamManager = new Socks5BytestreamManager(xMPPConnection);
            map.put(xMPPConnection, socks5BytestreamManager);
            socks5BytestreamManager.m22403a();
        }
        return socks5BytestreamManager;
    }

    /* renamed from: a */
    public final void m22403a() {
        XMPPConnection xMPPConnection = this.f19585a;
        C5058a c5058a = this.f19588d;
        xMPPConnection.m21993d(c5058a, c5058a.m19815c());
        m22405c();
    }

    /* renamed from: b */
    public synchronized void m22404b() {
        this.f19585a.m21977N(this.f19588d);
        this.f19588d.m19817e();
        this.f19587c.clear();
        this.f19586b.clear();
        this.f19592h = null;
        this.f19591g.clear();
        this.f19594j.clear();
        Map<XMPPConnection, Socks5BytestreamManager> map = f19584l;
        map.remove(this.f19585a);
        if (map.size() == 0) {
            C5060c.m19822e().m19826i();
        }
        ServiceDiscoveryManager serviceDiscoveryManagerM22486j = ServiceDiscoveryManager.m22486j(this.f19585a);
        if (serviceDiscoveryManagerM22486j != null) {
            serviceDiscoveryManagerM22486j.m22493m("http://jabber.org/protocol/bytestreams");
        }
    }

    /* renamed from: c */
    public final void m22405c() {
        ServiceDiscoveryManager serviceDiscoveryManagerM22486j = ServiceDiscoveryManager.m22486j(this.f19585a);
        if (serviceDiscoveryManagerM22486j.m22492l("http://jabber.org/protocol/bytestreams")) {
            return;
        }
        serviceDiscoveryManagerM22486j.m22488g("http://jabber.org/protocol/bytestreams");
    }

    /* renamed from: d */
    public List<InterfaceC4791a> m22406d() {
        return this.f19587c;
    }

    /* renamed from: f */
    public List<String> m22407f() {
        return this.f19594j;
    }

    /* renamed from: g */
    public InterfaceC4791a m22408g(String str) {
        return this.f19586b.get(str);
    }

    /* renamed from: h */
    public void m22409h(AbstractC5586IQ abstractC5586IQ) {
        this.f19585a.m21979P(AbstractC5586IQ.m22063v(abstractC5586IQ, new XMPPError(XMPPError.C5591a.f19318j)));
    }
}
