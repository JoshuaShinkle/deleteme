package p182r2;

import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2997x0;
import com.cyberlink.you.database.MessageObj;
import org.jivesoftware.smack.packet.Message;
import p209u2.AbstractC6381r;

/* renamed from: r2.q */
/* loaded from: classes.dex */
public class RunnableC6209q implements Runnable {

    /* renamed from: b */
    public final String f20908b;

    /* renamed from: c */
    public final String f20909c;

    /* renamed from: d */
    public final AbstractC6381r<C6210r, Void> f20910d;

    public RunnableC6209q(String str, String str2, AbstractC6381r<C6210r, Void> abstractC6381r) {
        this.f20908b = str;
        this.f20909c = str2;
        this.f20910d = abstractC6381r;
    }

    /* renamed from: b */
    public static /* synthetic */ void m23738b(MessageObj messageObj, C6210r c6210r) {
        C2950b0.m14923v().m15267h(new C2997x0(messageObj.m14777o(), c6210r.f20912c.m22161k(), "2"));
        messageObj.m14762X("11");
        C2950b0.m14916o().m15159D(messageObj.m14777o(), messageObj, "Status");
    }

    @Override // java.lang.Runnable
    public void run() {
        C6218z.m23767f("XSender.R.M", "[%s] run", this.f20909c);
        final MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(this.f20909c);
        if (messageObjM15179r == null) {
            C6218z.m23770i("XSender.R.M", "[%s] > messageId not in database", this.f20909c);
            return;
        }
        if ("11".equals(messageObjM15179r.m14740B()) || "5".equals(messageObjM15179r.m14740B())) {
            C6218z.m23770i("XSender.R.M", "[%s] receipt has sent", this.f20909c);
            return;
        }
        Message messageM23683c = C6192b0.m23683c("XSender.R.M", this.f20908b, messageObjM15179r, false);
        if (messageM23683c == null) {
            return;
        }
        messageM23683c.m22166s(C2925v.m14542A());
        final C6210r c6210r = new C6210r(messageObjM15179r.m14777o(), messageM23683c);
        if (C2950b0.m14901C(new Runnable() { // from class: r2.p
            @Override // java.lang.Runnable
            public final void run() {
                RunnableC6209q.m23738b(messageObjM15179r, c6210r);
            }
        })) {
            C6218z.m23767f("XSender.R.M", "[%s] > done", this.f20909c);
            this.f20910d.m24506d(c6210r);
        } else {
            C6218z.m23764c("XSender.R.M", "[%s] > failed. Please see logcat for more detail", this.f20909c);
            this.f20910d.m24507e();
        }
    }
}
