package org.jivesoftware.smackx.vcardtemp;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import p222v6.InterfaceC6489b;

/* loaded from: classes.dex */
public class VCardManager {

    /* renamed from: org.jivesoftware.smackx.vcardtemp.VCardManager$a */
    public class C5673a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.m22486j(xMPPConnection).m22488g("vcard-temp");
        }
    }

    static {
        XMPPConnection.m21962b(new C5673a());
    }
}
