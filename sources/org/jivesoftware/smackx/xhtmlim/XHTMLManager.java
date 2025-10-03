package org.jivesoftware.smackx.xhtmlim;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import p222v6.InterfaceC6489b;

/* loaded from: classes.dex */
public class XHTMLManager {

    /* renamed from: org.jivesoftware.smackx.xhtmlim.XHTMLManager$a */
    public class C5677a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            XHTMLManager.m22810b(xMPPConnection, true);
        }
    }

    static {
        XMPPConnection.m21962b(new C5677a());
    }

    /* renamed from: a */
    public static boolean m22809a(XMPPConnection xMPPConnection) {
        return ServiceDiscoveryManager.m22486j(xMPPConnection).m22492l("http://jabber.org/protocol/xhtml-im");
    }

    /* renamed from: b */
    public static synchronized void m22810b(XMPPConnection xMPPConnection, boolean z8) {
        if (m22809a(xMPPConnection) == z8) {
            return;
        }
        if (z8) {
            ServiceDiscoveryManager.m22486j(xMPPConnection).m22488g("http://jabber.org/protocol/xhtml-im");
        } else {
            ServiceDiscoveryManager.m22486j(xMPPConnection).m22493m("http://jabber.org/protocol/xhtml-im");
        }
    }
}
