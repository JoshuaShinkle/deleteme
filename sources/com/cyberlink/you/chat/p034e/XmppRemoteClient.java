package com.cyberlink.you.chat.p034e;

import android.util.Pair;
import com.cyberlink.clsm.Session;
import com.cyberlink.you.chat.C2898i;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.ULogUtility;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.Message;
import p054e2.AbstractC4712c;

/* loaded from: classes.dex */
public class XmppRemoteClient extends AbstractC4712c implements XMPPManager.InterfaceC2849a0 {

    /* renamed from: e */
    public final ConcurrentHashMap<String, Pair<CountDownLatch, AtomicLong>> f12597e = new ConcurrentHashMap<>();

    /* renamed from: f */
    public final ConcurrentHashMap<String, Pair<CountDownLatch, AtomicReference<Session>>> f12598f = new ConcurrentHashMap<>();

    /* renamed from: com.cyberlink.you.chat.e.XmppRemoteClient$a */
    public class C2886a implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ CountDownLatch f12600a;

        public C2886a(CountDownLatch countDownLatch) {
            this.f12600a = countDownLatch;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            this.f12600a.countDown();
            this.f12600a.countDown();
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            this.f12600a.countDown();
        }
    }

    /* renamed from: com.cyberlink.you.chat.e.XmppRemoteClient$b */
    public class C2887b implements XMPPManager.InterfaceC2873x {

        /* renamed from: a */
        public final /* synthetic */ CountDownLatch f12602a;

        public C2887b(CountDownLatch countDownLatch) {
            this.f12602a = countDownLatch;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            this.f12602a.countDown();
            this.f12602a.countDown();
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            this.f12602a.countDown();
        }
    }

    public XmppRemoteClient() {
        XMPPManager.m14184g0().m14206G(this);
    }

    /* renamed from: l */
    public static AbstractC5586IQ m14294l(String str) {
        String strM14542A = C2925v.m14542A();
        final String str2 = "<e2ee xmlns='urn:xmpp:mam:3'><sessionId>" + str + "</sessionId></e2ee>";
        AbstractC5586IQ abstractC5586IQ = new AbstractC5586IQ() { // from class: com.cyberlink.you.chat.e.XmppRemoteClient.3
            @Override // org.jivesoftware.smack.packet.AbstractC5586IQ
            /* renamed from: y */
            public CharSequence mo9675y() {
                return str2;
            }
        };
        abstractC5586IQ.m22166s(strM14542A);
        abstractC5586IQ.m22070F(AbstractC5586IQ.a.f19231b);
        return abstractC5586IQ;
    }

    /* renamed from: n */
    public static Message m14295n(Group group, String str, String str2) {
        Message message = new Message(group.f13723j, "Dual".equals(group.f13716c) ? Message.Type.chat : Message.Type.groupchat);
        message.m22166s(C2925v.m14542A());
        C2898i c2898i = new C2898i("encrypted", "U");
        c2898i.m14375g("type", "session");
        c2898i.m14375g("sessionId", str);
        c2898i.m14376h(str2);
        message.m22154b(c2898i);
        return message;
    }

    @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
    /* renamed from: F */
    public void mo5716F(String str) {
        mo5718p(str, new Date(0L));
    }

    /* renamed from: o */
    public void m14296o(String str, long j9, String str2, String str3) {
        Pair<CountDownLatch, AtomicReference<Session>> pairRemove = this.f12598f.remove(str);
        if (pairRemove == null) {
            return;
        }
        ((AtomicReference) pairRemove.second).set(new Session(str2, str3, j9));
        if (((CountDownLatch) pairRemove.first).getCount() > 0) {
            ((CountDownLatch) pairRemove.first).countDown();
        }
    }

    @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
    /* renamed from: p */
    public void mo5718p(String str, Date date) {
        Pair<CountDownLatch, AtomicLong> pairRemove = this.f12597e.remove(str);
        if (pairRemove == null) {
            return;
        }
        ((AtomicLong) pairRemove.second).set(date.getTime());
        if (((CountDownLatch) pairRemove.first).getCount() > 0) {
            ((CountDownLatch) pairRemove.first).countDown();
        }
    }

    @Override // com.cyberlink.clsm.IRemoteClient
    public Session querySessionInfo(String str) throws InterruptedException {
        AbstractC5586IQ abstractC5586IQM14294l = m14294l(str);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Pair<CountDownLatch, AtomicReference<Session>> pair = new Pair<>(countDownLatch, new AtomicReference(null));
        this.f12598f.put(abstractC5586IQM14294l.m22161k(), pair);
        XMPPManager.m14184g0().m14241e1(abstractC5586IQM14294l, new C2887b(countDownLatch));
        try {
            countDownLatch.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
        }
        return (Session) ((AtomicReference) pair.second).get();
    }

    @Override // p054e2.AbstractC4712c, com.cyberlink.clsm.IRemoteClient
    public boolean register(String str, String str2) {
        ULogUtility.m16680p("CLSM", "register: " + str + ", " + str2);
        return super.register(str, str2);
    }

    @Override // com.cyberlink.clsm.IRemoteClient
    public long updateSessionInfo(String str, String str2, String str3) throws InterruptedException {
        Group groupM15077n = C2950b0.m14912k().m15077n(str);
        if (groupM15077n == null) {
            return 0L;
        }
        Message messageM14295n = m14295n(groupM15077n, str2, str3);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Pair<CountDownLatch, AtomicLong> pair = new Pair<>(countDownLatch, new AtomicLong(0L));
        this.f12597e.put(messageM14295n.m22161k(), pair);
        XMPPManager.m14184g0().m14241e1(messageM14295n, new C2886a(countDownLatch));
        try {
            countDownLatch.await(30L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
        }
        return ((AtomicLong) pair.second).get();
    }
}
