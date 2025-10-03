package org.jivesoftware.smackx.iqlast;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.iqlast.packet.LastActivity;
import p222v6.AbstractC6491d;
import p222v6.InterfaceC6489b;
import p240x6.C6576a;
import p240x6.C6579d;
import p240x6.C6584i;
import p240x6.InterfaceC6582g;

/* loaded from: classes.dex */
public class LastActivityManager extends AbstractC6491d {

    /* renamed from: d */
    public static final Map<XMPPConnection, LastActivityManager> f19728d = new WeakHashMap();

    /* renamed from: e */
    public static final InterfaceC6582g f19729e = new C6576a(new C6579d(AbstractC5586IQ.a.f19231b), new C6584i(LastActivity.class));

    /* renamed from: f */
    public static boolean f19730f = true;

    /* renamed from: b */
    public volatile long f19731b;

    /* renamed from: c */
    public boolean f19732c;

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager$a */
    public class C5649a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            LastActivityManager.m22551i(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager$b */
    public class C5650b implements InterfaceC5583c {
        public C5650b() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            Presence.Mode modeM22120w = ((Presence) abstractC5594b).m22120w();
            if (modeM22120w == null) {
                return;
            }
            int i9 = C5653e.f19736a[modeM22120w.ordinal()];
            if (i9 == 1 || i9 == 2) {
                LastActivityManager.this.m22554j();
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager$c */
    public class C5651c implements InterfaceC5583c {
        public C5651c() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            if (((Message) abstractC5594b).m22086L() == Message.Type.error) {
                return;
            }
            LastActivityManager.this.m22554j();
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager$d */
    public class C5652d implements InterfaceC5583c {
        public C5652d() {
        }

        @Override // org.jivesoftware.smack.InterfaceC5583c
        public void processPacket(AbstractC5594b abstractC5594b) {
            if (LastActivityManager.this.f19732c) {
                LastActivity lastActivity = new LastActivity();
                lastActivity.m22070F(AbstractC5586IQ.a.f19233d);
                lastActivity.m22167t(abstractC5594b.m22160j());
                lastActivity.m22165r(abstractC5594b.m22162l());
                lastActivity.m22166s(abstractC5594b.m22161k());
                lastActivity.m22557I(LastActivityManager.this.m22553h());
                LastActivityManager.this.m24823a().m21979P(lastActivity);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager$e */
    public static /* synthetic */ class C5653e {

        /* renamed from: a */
        public static final /* synthetic */ int[] f19736a;

        static {
            int[] iArr = new int[Presence.Mode.values().length];
            f19736a = iArr;
            try {
                iArr[Presence.Mode.available.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f19736a[Presence.Mode.chat.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        XMPPConnection.m21962b(new C5649a());
    }

    public LastActivityManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f19732c = false;
        xMPPConnection.m21994e(new C5650b(), C6584i.f22133b);
        xMPPConnection.m21994e(new C5651c(), C6584i.f22134c);
        xMPPConnection.m21993d(new C5652d(), f19729e);
        if (f19730f) {
            m22552g();
        }
        m22554j();
        f19728d.put(xMPPConnection, this);
    }

    /* renamed from: i */
    public static synchronized LastActivityManager m22551i(XMPPConnection xMPPConnection) {
        LastActivityManager lastActivityManager;
        lastActivityManager = f19728d.get(xMPPConnection);
        if (lastActivityManager == null) {
            lastActivityManager = new LastActivityManager(xMPPConnection);
        }
        return lastActivityManager;
    }

    /* renamed from: g */
    public synchronized void m22552g() {
        ServiceDiscoveryManager.m22486j(m24823a()).m22488g("jabber:iq:last");
        this.f19732c = true;
    }

    /* renamed from: h */
    public final long m22553h() {
        return (System.currentTimeMillis() - this.f19731b) / 1000;
    }

    /* renamed from: j */
    public final void m22554j() {
        this.f19731b = System.currentTimeMillis();
    }
}
