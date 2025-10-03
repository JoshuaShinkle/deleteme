package p065f3;

import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.utility.ULogUtility;
import java.util.List;
import p209u2.AbstractC6381r;

/* renamed from: f3.h */
/* loaded from: classes.dex */
public class RunnableC4786h implements Runnable {

    /* renamed from: c */
    public static volatile transient boolean f16634c = false;

    /* renamed from: b */
    public final AbstractC6381r<Void, Void> f16635b;

    public RunnableC4786h(AbstractC6381r<Void, Void> abstractC6381r) {
        this.f16635b = abstractC6381r;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!f16634c) {
            System.currentTimeMillis();
            List<MessageObj> listM15183v = C2950b0.m14916o().m15183v();
            if (listM15183v != null && !listM15183v.isEmpty()) {
                ULogUtility.m16680p("UnknownMessageTask", "Unknown messages found: " + listM15183v.size());
                for (MessageObj messageObj : listM15183v) {
                    ULogUtility.m16683s("UnknownMessageTask", " > " + messageObj.m14739A());
                    String strM14739A = messageObj.m14739A();
                    XMPPManager.HandleType handleType = XMPPManager.HandleType.UPDATE_NEW_VERSION;
                    XMPPManager.m14184g0().m14271u0(XMPPManager.m14187k(strM14739A, handleType), handleType, false);
                }
            }
            f16634c = true;
        }
        this.f16635b.m24505c();
    }
}
