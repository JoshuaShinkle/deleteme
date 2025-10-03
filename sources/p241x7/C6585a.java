package p241x7;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import p222v6.AbstractC6491d;
import p222v6.InterfaceC6489b;
import p240x6.C6581f;

/* renamed from: x7.a */
/* loaded from: classes.dex */
public class C6585a extends AbstractC6491d implements InterfaceC5583c {

    /* renamed from: d */
    public static Map<XMPPConnection, C6585a> f22136d = new WeakHashMap();

    /* renamed from: b */
    public boolean f22137b;

    /* renamed from: c */
    public Set<InterfaceC6586b> f22138c;

    /* renamed from: x7.a$a */
    public class a implements InterfaceC6489b {
        @Override // p222v6.InterfaceC6489b
        /* renamed from: a */
        public void mo21958a(XMPPConnection xMPPConnection) {
            C6585a.m25197d(xMPPConnection);
        }
    }

    static {
        XMPPConnection.m21962b(new a());
    }

    public C6585a(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.f22137b = false;
        this.f22138c = Collections.synchronizedSet(new HashSet());
        ServiceDiscoveryManager.m22486j(xMPPConnection).m22488g("urn:xmpp:receipts");
        xMPPConnection.m21993d(this, new C6581f("urn:xmpp:receipts"));
    }

    /* renamed from: c */
    public static String m25196c(Message message) {
        message.m22154b(new DeliveryReceiptRequest());
        return message.m22161k();
    }

    /* renamed from: d */
    public static synchronized C6585a m25197d(XMPPConnection xMPPConnection) {
        C6585a c6585a;
        c6585a = f22136d.get(xMPPConnection);
        if (c6585a == null) {
            c6585a = new C6585a(xMPPConnection);
            f22136d.put(xMPPConnection, c6585a);
        }
        return c6585a;
    }

    @Override // org.jivesoftware.smack.InterfaceC5583c
    public void processPacket(AbstractC5594b abstractC5594b) {
        DeliveryReceipt deliveryReceiptM22696c = DeliveryReceipt.m22696c(abstractC5594b);
        if (deliveryReceiptM22696c != null) {
            Iterator<InterfaceC6586b> it = this.f22138c.iterator();
            while (it.hasNext()) {
                it.next().m25198a(abstractC5594b.m22160j(), abstractC5594b.m22162l(), deliveryReceiptM22696c.m22697d());
            }
        }
        if (!this.f22137b || DeliveryReceiptRequest.m22699c(abstractC5594b) == null) {
            return;
        }
        XMPPConnection xMPPConnectionM24823a = m24823a();
        Message message = new Message(abstractC5594b.m22160j(), Message.Type.normal);
        message.m22154b(new DeliveryReceipt(abstractC5594b.m22161k()));
        xMPPConnectionM24823a.m21979P(message);
    }
}
