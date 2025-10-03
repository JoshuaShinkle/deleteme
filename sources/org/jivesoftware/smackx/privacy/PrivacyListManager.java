package org.jivesoftware.smackx.privacy;

import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smackx.privacy.packet.Privacy;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import p222v6.AbstractC6491d;
import p222v6.InterfaceC6489b;
import p223v7.InterfaceC6493a;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6581f;
import p240x6.InterfaceC6582g;

/* loaded from: classes.dex */
public class PrivacyListManager extends AbstractC6491d {

    /* renamed from: c */
    public static final InterfaceC6582g f19796c = new C6576a(new C6579d(AbstractC5586IQ.a.f19232c), new C6581f(SearchIntents.EXTRA_QUERY, "jabber:iq:privacy"));

    /* renamed from: d */
    public static final Map<XMPPConnection, PrivacyListManager> f19797d = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: b */
    public final List<InterfaceC6493a> f19798b;

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager$a */
    public class C5666a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            PrivacyListManager.m22651d(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager$b */
    public class C5667b implements InterfaceC5583c {

        /* renamed from: a */
        public final /* synthetic */ XMPPConnection f19799a;

        public C5667b(XMPPConnection xMPPConnection) {
            this.f19799a = xMPPConnection;
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            Privacy privacy = (Privacy) abstractC5594b;
            synchronized (PrivacyListManager.this.f19798b) {
                for (InterfaceC6493a interfaceC6493a : PrivacyListManager.this.f19798b) {
                    for (Map.Entry<String, List<PrivacyItem>> entry : privacy.m22655J().entrySet()) {
                        String key = entry.getKey();
                        List<PrivacyItem> value = entry.getValue();
                        if (value.isEmpty()) {
                            interfaceC6493a.m24836a(key);
                        } else {
                            interfaceC6493a.m24837b(key, value);
                        }
                    }
                }
            }
            this.f19799a.m21979P(AbstractC5586IQ.m22064w(privacy));
        }
    }

    static {
        XMPPConnection.m21962b(new C5666a());
    }

    public PrivacyListManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19798b = new ArrayList();
        f19797d.put(xMPPConnection, this);
        xMPPConnection.m21993d(new C5667b(xMPPConnection), f19796c);
    }

    /* renamed from: d */
    public static synchronized PrivacyListManager m22651d(XMPPConnection xMPPConnection) {
        PrivacyListManager privacyListManager;
        privacyListManager = f19797d.get(xMPPConnection);
        if (privacyListManager == null) {
            privacyListManager = new PrivacyListManager(xMPPConnection);
        }
        return privacyListManager;
    }
}
