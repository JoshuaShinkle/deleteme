package org.jivesoftware.smackx.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import p119k7.AbstractC5202a;
import p140m7.InterfaceC5339a;
import p222v6.AbstractC6491d;
import p222v6.InterfaceC6489b;
import p240x6.C6584i;
import p259z7.C6837a;

/* loaded from: classes.dex */
public class AdHocCommandManager extends AbstractC6491d {

    /* renamed from: f */
    public static Map<XMPPConnection, AdHocCommandManager> f19648f = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: b */
    public final Map<String, C5631e> f19649b;

    /* renamed from: c */
    public final Map<String, AbstractC5202a> f19650c;

    /* renamed from: d */
    public final ServiceDiscoveryManager f19651d;

    /* renamed from: e */
    public Thread f19652e;

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager$a */
    public class C5627a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            AdHocCommandManager.m22452f(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager$b */
    public class C5628b implements InterfaceC5339a {
        public C5628b() {
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
            ArrayList arrayList = new ArrayList();
            for (C5631e c5631e : AdHocCommandManager.this.m22453g()) {
                DiscoverItems.C5638a c5638a = new DiscoverItems.C5638a(c5631e.m22462d());
                c5638a.m22516b(c5631e.m22460b());
                c5638a.m22517c(c5631e.m22461c());
                arrayList.add(c5638a);
            }
            return arrayList;
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager$c */
    public class C5629c implements InterfaceC5583c {
        public C5629c() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            try {
                AdHocCommandManager.this.m22455i((AdHocCommandData) abstractC5594b);
            } catch (SmackException unused) {
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager$d */
    public class RunnableC5630d implements Runnable {
        public RunnableC5630d() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (true) {
                for (String str : AdHocCommandManager.this.f19650c.keySet()) {
                    AbstractC5202a abstractC5202a = (AbstractC5202a) AdHocCommandManager.this.f19650c.get(str);
                    if (abstractC5202a != null) {
                        if (System.currentTimeMillis() - abstractC5202a.m20341n() > 240000) {
                            AdHocCommandManager.this.f19650c.remove(str);
                        }
                    }
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.commands.AdHocCommandManager$e */
    public static class C5631e {

        /* renamed from: a */
        public String f19656a;

        /* renamed from: b */
        public String f19657b;

        /* renamed from: c */
        public String f19658c;

        /* renamed from: a */
        public AbstractC5202a m22459a() {
            throw null;
        }

        /* renamed from: b */
        public String m22460b() {
            return this.f19657b;
        }

        /* renamed from: c */
        public String m22461c() {
            return this.f19656a;
        }

        /* renamed from: d */
        public String m22462d() {
            return this.f19658c;
        }
    }

    static {
        XMPPConnection.m21962b(new C5627a());
    }

    public AdHocCommandManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19649b = new ConcurrentHashMap();
        this.f19650c = new ConcurrentHashMap();
        this.f19651d = ServiceDiscoveryManager.m22486j(xMPPConnection);
        f19648f.put(xMPPConnection, this);
        ServiceDiscoveryManager.m22486j(xMPPConnection).m22488g("http://jabber.org/protocol/commands");
        ServiceDiscoveryManager.m22486j(xMPPConnection).m22495o("http://jabber.org/protocol/commands", new C5628b());
        xMPPConnection.m21993d(new C5629c(), new C6584i(AdHocCommandData.class));
        this.f19652e = null;
    }

    /* renamed from: f */
    public static synchronized AdHocCommandManager m22452f(XMPPConnection xMPPConnection) {
        AdHocCommandManager adHocCommandManager;
        adHocCommandManager = f19648f.get(xMPPConnection);
        if (adHocCommandManager == null) {
            adHocCommandManager = new AdHocCommandManager(xMPPConnection);
        }
        return adHocCommandManager;
    }

    /* renamed from: g */
    public final Collection<C5631e> m22453g() {
        return this.f19649b.values();
    }

    /* renamed from: h */
    public final AbstractC5202a m22454h(String str, String str2) throws XMPPException.XMPPErrorException {
        C5631e c5631e = this.f19649b.get(str);
        try {
            AbstractC5202a abstractC5202aM22459a = c5631e.m22459a();
            abstractC5202aM22459a.m20345r(str2);
            abstractC5202aM22459a.m22447k(c5631e.m22460b());
            abstractC5202aM22459a.m22448l(c5631e.m22461c());
            return abstractC5202aM22459a;
        } catch (IllegalAccessException unused) {
            throw new XMPPException.XMPPErrorException(new XMPPError(XMPPError.C5591a.f19310b));
        } catch (InstantiationException unused2) {
            throw new XMPPException.XMPPErrorException(new XMPPError(XMPPError.C5591a.f19310b));
        }
    }

    /* renamed from: i */
    public final void m22455i(AdHocCommandData adHocCommandData) {
        if (adHocCommandData.m22066B() != AbstractC5586IQ.a.f19232c) {
            return;
        }
        AdHocCommandData adHocCommandData2 = new AdHocCommandData();
        adHocCommandData2.m22167t(adHocCommandData.m22160j());
        adHocCommandData2.m22166s(adHocCommandData.m22161k());
        adHocCommandData2.m22479U(adHocCommandData.m22472N());
        adHocCommandData2.m22477S(adHocCommandData.m22162l());
        String strM22473O = adHocCommandData.m22473O();
        String strM22472N = adHocCommandData.m22472N();
        if (strM22473O == null) {
            if (!this.f19649b.containsKey(strM22472N)) {
                m22456j(adHocCommandData2, XMPPError.C5591a.f19316h);
                return;
            }
            String strM22349n = C5616j.m22349n(15);
            try {
                AbstractC5202a abstractC5202aM22454h = m22454h(strM22472N, strM22349n);
                adHocCommandData2.m22070F(AbstractC5586IQ.a.f19233d);
                abstractC5202aM22454h.mo20339j(adHocCommandData2);
                if (!abstractC5202aM22454h.m20342o(adHocCommandData.m22160j())) {
                    m22456j(adHocCommandData2, XMPPError.C5591a.f19311c);
                    return;
                }
                AdHocCommand.Action actionM22467I = adHocCommandData.m22467I();
                if (actionM22467I != null && actionM22467I.equals(AdHocCommand.Action.unknown)) {
                    m22457k(adHocCommandData2, XMPPError.C5591a.f19312d, AdHocCommand.SpecificErrorCondition.malformedAction);
                    return;
                }
                if (actionM22467I != null && !actionM22467I.equals(AdHocCommand.Action.execute)) {
                    m22457k(adHocCommandData2, XMPPError.C5591a.f19312d, AdHocCommand.SpecificErrorCondition.badAction);
                    return;
                }
                abstractC5202aM22454h.m20343p();
                abstractC5202aM22454h.m22440c();
                if (abstractC5202aM22454h.m20344q()) {
                    adHocCommandData2.m22481W(AdHocCommand.Status.completed);
                } else {
                    adHocCommandData2.m22481W(AdHocCommand.Status.executing);
                    this.f19650c.put(strM22349n, abstractC5202aM22454h);
                    if (this.f19652e == null) {
                        Thread thread = new Thread(new RunnableC5630d());
                        this.f19652e = thread;
                        thread.setDaemon(true);
                        this.f19652e.start();
                    }
                }
                m24823a().m21979P(adHocCommandData2);
                return;
            } catch (XMPPException.XMPPErrorException e9) {
                XMPPError xMPPErrorM22018a = e9.m22018a();
                if (XMPPError.Type.CANCEL.equals(xMPPErrorM22018a.m22143d())) {
                    adHocCommandData2.m22481W(AdHocCommand.Status.canceled);
                    this.f19650c.remove(strM22349n);
                }
                m22458l(adHocCommandData2, xMPPErrorM22018a);
                return;
            }
        }
        AbstractC5202a abstractC5202a = this.f19650c.get(strM22473O);
        if (abstractC5202a == null) {
            m22457k(adHocCommandData2, XMPPError.C5591a.f19312d, AdHocCommand.SpecificErrorCondition.badSessionid);
            return;
        }
        if (System.currentTimeMillis() - abstractC5202a.m20341n() > 120000) {
            this.f19650c.remove(strM22473O);
            m22457k(adHocCommandData2, XMPPError.C5591a.f19319k, AdHocCommand.SpecificErrorCondition.sessionExpired);
            return;
        }
        synchronized (abstractC5202a) {
            AdHocCommand.Action actionM22467I2 = adHocCommandData.m22467I();
            if (actionM22467I2 != null && actionM22467I2.equals(AdHocCommand.Action.unknown)) {
                m22457k(adHocCommandData2, XMPPError.C5591a.f19312d, AdHocCommand.SpecificErrorCondition.malformedAction);
                return;
            }
            if (actionM22467I2 == null || AdHocCommand.Action.execute.equals(actionM22467I2)) {
                actionM22467I2 = abstractC5202a.m22443f();
            }
            if (!abstractC5202a.m22444g(actionM22467I2)) {
                m22457k(adHocCommandData2, XMPPError.C5591a.f19312d, AdHocCommand.SpecificErrorCondition.badAction);
                return;
            }
            try {
                adHocCommandData2.m22070F(AbstractC5586IQ.a.f19233d);
                abstractC5202a.mo20339j(adHocCommandData2);
                if (AdHocCommand.Action.next.equals(actionM22467I2)) {
                    abstractC5202a.m20343p();
                    abstractC5202a.m22445h(new C6837a(adHocCommandData.m22471M()));
                    if (abstractC5202a.m20344q()) {
                        adHocCommandData2.m22481W(AdHocCommand.Status.completed);
                    } else {
                        adHocCommandData2.m22481W(AdHocCommand.Status.executing);
                    }
                } else if (AdHocCommand.Action.complete.equals(actionM22467I2)) {
                    abstractC5202a.m20343p();
                    abstractC5202a.m22439b(new C6837a(adHocCommandData.m22471M()));
                    adHocCommandData2.m22481W(AdHocCommand.Status.completed);
                    this.f19650c.remove(strM22473O);
                } else if (AdHocCommand.Action.prev.equals(actionM22467I2)) {
                    abstractC5202a.m20340m();
                    abstractC5202a.m22446i();
                } else if (AdHocCommand.Action.cancel.equals(actionM22467I2)) {
                    abstractC5202a.m22438a();
                    adHocCommandData2.m22481W(AdHocCommand.Status.canceled);
                    this.f19650c.remove(strM22473O);
                }
                m24823a().m21979P(adHocCommandData2);
            } catch (XMPPException.XMPPErrorException e10) {
                XMPPError xMPPErrorM22018a2 = e10.m22018a();
                if (XMPPError.Type.CANCEL.equals(xMPPErrorM22018a2.m22143d())) {
                    adHocCommandData2.m22481W(AdHocCommand.Status.canceled);
                    this.f19650c.remove(strM22473O);
                }
                m22458l(adHocCommandData2, xMPPErrorM22018a2);
            }
        }
    }

    /* renamed from: j */
    public final void m22456j(AdHocCommandData adHocCommandData, XMPPError.C5591a c5591a) {
        m22458l(adHocCommandData, new XMPPError(c5591a));
    }

    /* renamed from: k */
    public final void m22457k(AdHocCommandData adHocCommandData, XMPPError.C5591a c5591a, AdHocCommand.SpecificErrorCondition specificErrorCondition) {
        XMPPError xMPPError = new XMPPError(c5591a);
        xMPPError.m22140a(new AdHocCommandData.C5632a(specificErrorCondition));
        m22458l(adHocCommandData, xMPPError);
    }

    /* renamed from: l */
    public final void m22458l(AdHocCommandData adHocCommandData, XMPPError xMPPError) {
        adHocCommandData.m22070F(AbstractC5586IQ.a.f19234e);
        adHocCommandData.m22164q(xMPPError);
        m24823a().m21979P(adHocCommandData);
    }
}
