package org.jivesoftware.smackx.muc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import p140m7.InterfaceC5339a;
import p222v6.InterfaceC6489b;

/* loaded from: classes.dex */
public class MultiUserChat {

    /* renamed from: b */
    public static final Logger f19749b = Logger.getLogger(MultiUserChat.class.getName());

    /* renamed from: c */
    public static Map<XMPPConnection, List<String>> f19750c = new WeakHashMap();

    /* renamed from: a */
    public XMPPConnection f19751a;

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat$a */
    public class C5656a implements InterfaceC6489b {

        /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat$a$a */
        public class a implements InterfaceC5339a {

            /* renamed from: a */
            public final /* synthetic */ WeakReference f19752a;

            public a(WeakReference weakReference) {
                this.f19752a = weakReference;
            }

            @Override // p140m7.InterfaceC5339a
            /* renamed from: a */
            public List<InterfaceC5595c> mo20981a() {
                return null;
            }

            @Override // p140m7.InterfaceC5339a
            /* renamed from: b */
            public List<String> mo20982b() {
                return null;
            }

            @Override // p140m7.InterfaceC5339a
            /* renamed from: c */
            public List<DiscoverInfo.C5637b> mo20983c() {
                return null;
            }

            @Override // p140m7.InterfaceC5339a
            /* renamed from: d */
            public List<DiscoverItems.C5638a> mo20984d() {
                XMPPConnection xMPPConnection = (XMPPConnection) this.f19752a.get();
                if (xMPPConnection == null) {
                    return new LinkedList();
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = MultiUserChat.m22566c(xMPPConnection).iterator();
                while (it.hasNext()) {
                    arrayList.add(new DiscoverItems.C5638a((String) it.next()));
                }
                return arrayList;
            }
        }

        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.m22486j(xMPPConnection).m22488g("http://jabber.org/protocol/muc");
            ServiceDiscoveryManager.m22486j(xMPPConnection).m22495o("http://jabber.org/protocol/muc#rooms", new a(new WeakReference(xMPPConnection)));
        }
    }

    static {
        XMPPConnection.m21962b(new C5656a());
    }

    /* renamed from: c */
    public static List<String> m22566c(XMPPConnection xMPPConnection) {
        List<String> list = f19750c.get(xMPPConnection);
        return list != null ? list : Collections.emptyList();
    }

    /* renamed from: b */
    public final void m22567b() {
        try {
            if (this.f19751a == null) {
            } else {
                throw null;
            }
        } catch (Exception unused) {
        }
    }

    public void finalize() throws Throwable {
        m22567b();
        super.finalize();
    }
}
