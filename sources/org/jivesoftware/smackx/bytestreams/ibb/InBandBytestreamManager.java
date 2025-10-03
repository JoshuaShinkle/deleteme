package org.jivesoftware.smackx.bytestreams.ibb;

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
import p069f7.InterfaceC4791a;
import p079g7.C4968a;
import p079g7.C4969b;
import p079g7.C4971d;
import p222v6.C6488a;
import p222v6.InterfaceC6489b;

/* loaded from: classes.dex */
public class InBandBytestreamManager {

    /* renamed from: l */
    public static final Random f19560l;

    /* renamed from: m */
    public static final Map<XMPPConnection, InBandBytestreamManager> f19561m;

    /* renamed from: a */
    public final XMPPConnection f19562a;

    /* renamed from: d */
    public final C4971d f19565d;

    /* renamed from: e */
    public final C4969b f19566e;

    /* renamed from: f */
    public final C4968a f19567f;

    /* renamed from: b */
    public final Map<String, InterfaceC4791a> f19563b = new ConcurrentHashMap();

    /* renamed from: c */
    public final List<InterfaceC4791a> f19564c = Collections.synchronizedList(new LinkedList());

    /* renamed from: g */
    public final Map<String, C5622a> f19568g = new ConcurrentHashMap();

    /* renamed from: h */
    public int f19569h = 4096;

    /* renamed from: i */
    public int f19570i = 65535;

    /* renamed from: j */
    public StanzaType f19571j = StanzaType.IQ;

    /* renamed from: k */
    public List<String> f19572k = Collections.synchronizedList(new LinkedList());

    public enum StanzaType {
        IQ,
        MESSAGE
    }

    /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager$a */
    public class C5621a implements InterfaceC6489b {

        /* renamed from: org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager$a$a */
        public class a extends C6488a {

            /* renamed from: a */
            public final /* synthetic */ XMPPConnection f19576a;

            public a(XMPPConnection xMPPConnection) {
                this.f19576a = xMPPConnection;
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void connectionClosed() {
                InBandBytestreamManager.m22382d(this.f19576a).m22383b();
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void connectionClosedOnError(Exception exc) {
                InBandBytestreamManager.m22382d(this.f19576a).m22383b();
            }

            @Override // p222v6.C6488a, p222v6.InterfaceC6490c
            public void reconnectionSuccessful() {
                InBandBytestreamManager.m22382d(this.f19576a);
            }
        }

        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            InBandBytestreamManager.m22382d(xMPPConnection);
            if (xMPPConnection == null) {
                return;
            }
            xMPPConnection.m21992c(new a(xMPPConnection));
        }
    }

    static {
        XMPPConnection.m21962b(new C5621a());
        f19560l = new Random();
        f19561m = new HashMap();
    }

    public InBandBytestreamManager(XMPPConnection xMPPConnection) {
        this.f19562a = xMPPConnection;
        C4971d c4971d = new C4971d(this);
        this.f19565d = c4971d;
        xMPPConnection.m21993d(c4971d, c4971d.m19247c());
        C4969b c4969b = new C4969b(this);
        this.f19566e = c4969b;
        xMPPConnection.m21993d(c4969b, c4969b.m19244a());
        C4968a c4968a = new C4968a(this);
        this.f19567f = c4968a;
        xMPPConnection.m21993d(c4968a, c4968a.m19243a());
    }

    /* renamed from: d */
    public static synchronized InBandBytestreamManager m22382d(XMPPConnection xMPPConnection) {
        if (xMPPConnection == null) {
            return null;
        }
        Map<XMPPConnection, InBandBytestreamManager> map = f19561m;
        InBandBytestreamManager inBandBytestreamManager = map.get(xMPPConnection);
        if (inBandBytestreamManager == null) {
            inBandBytestreamManager = new InBandBytestreamManager(xMPPConnection);
            map.put(xMPPConnection, inBandBytestreamManager);
        }
        return inBandBytestreamManager;
    }

    /* renamed from: b */
    public final void m22383b() {
        f19561m.remove(this.f19562a);
        this.f19562a.m21977N(this.f19565d);
        this.f19562a.m21977N(this.f19566e);
        this.f19562a.m21977N(this.f19567f);
        this.f19565d.m19249e();
        this.f19563b.clear();
        this.f19564c.clear();
        this.f19568g.clear();
        this.f19572k.clear();
    }

    /* renamed from: c */
    public List<InterfaceC4791a> m22384c() {
        return this.f19564c;
    }

    /* renamed from: e */
    public List<String> m22385e() {
        return this.f19572k;
    }

    /* renamed from: f */
    public int m22386f() {
        return this.f19570i;
    }

    /* renamed from: g */
    public Map<String, C5622a> m22387g() {
        return this.f19568g;
    }

    /* renamed from: h */
    public InterfaceC4791a m22388h(String str) {
        return this.f19563b.get(str);
    }

    /* renamed from: i */
    public void m22389i(AbstractC5586IQ abstractC5586IQ) {
        this.f19562a.m21979P(AbstractC5586IQ.m22063v(abstractC5586IQ, new XMPPError(XMPPError.C5591a.f19316h)));
    }

    /* renamed from: j */
    public void m22390j(AbstractC5586IQ abstractC5586IQ) {
        this.f19562a.m21979P(AbstractC5586IQ.m22063v(abstractC5586IQ, new XMPPError(XMPPError.C5591a.f19318j)));
    }

    /* renamed from: k */
    public void m22391k(AbstractC5586IQ abstractC5586IQ) {
        this.f19562a.m21979P(AbstractC5586IQ.m22063v(abstractC5586IQ, new XMPPError(XMPPError.C5591a.f19328t)));
    }
}
