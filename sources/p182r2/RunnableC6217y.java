package p182r2;

import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2997x0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.Message;
import p209u2.AbstractC6381r;

/* renamed from: r2.y */
/* loaded from: classes.dex */
public class RunnableC6217y implements Runnable {

    /* renamed from: b */
    public final Group f20927b;

    /* renamed from: c */
    public final String f20928c;

    /* renamed from: d */
    public final AbstractC6381r<C6210r, Void> f20929d;

    public RunnableC6217y(Group group, String str, AbstractC6381r<C6210r, Void> abstractC6381r) {
        this.f20927b = group;
        this.f20928c = str;
        this.f20929d = abstractC6381r;
    }

    /* renamed from: b */
    public static /* synthetic */ void m23761b(MessageObj messageObj, C6210r c6210r) {
        C2950b0.m14923v().m15267h(new C2997x0(messageObj.m14777o(), c6210r.f20912c.m22161k(), "2"));
        ArrayList arrayList = new ArrayList();
        messageObj.m14763Y(MessageObj.TTLStatus.START);
        arrayList.add("TTLStatus");
        messageObj.m14760V(FriendsClient.m15646K());
        arrayList.add("SDStarttime");
        messageObj.m14762X("11");
        arrayList.add("Status");
        C2950b0.m14916o().m15160E(messageObj.m14777o(), messageObj, arrayList);
    }

    @Override // java.lang.Runnable
    public void run() {
        C6218z.m23767f("XSender.R.S", "[%s] run", this.f20928c);
        final MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(this.f20928c);
        if (messageObjM15179r == null) {
            C6218z.m23770i("XSender.R.S", "[%s] > messageId not in database", this.f20928c);
            return;
        }
        if ("11".equals(messageObjM15179r.m14740B()) || "5".equals(messageObjM15179r.m14740B())) {
            C6218z.m23770i("XSender.R.S", "[%s] receipt has sent", this.f20928c);
            return;
        }
        if (!MessageObj.TTLStatus.NOT_START.equals(messageObjM15179r.m14741C())) {
            C6218z.m23770i("XSender.R.S", "[%s] has count down", this.f20928c);
            return;
        }
        Message messageM23682b = C6192b0.m23682b("XSender.R.S", this.f20927b, messageObjM15179r, true);
        if (messageM23682b == null) {
            return;
        }
        messageM23682b.m22166s(C2925v.m14542A());
        final C6210r c6210r = new C6210r(messageObjM15179r.m14777o(), messageM23682b);
        if (C2950b0.m14901C(new Runnable() { // from class: r2.x
            @Override // java.lang.Runnable
            public final void run() {
                RunnableC6217y.m23761b(messageObjM15179r, c6210r);
            }
        })) {
            C6218z.m23767f("XSender.R.S", "[%s] > done", this.f20928c);
            this.f20929d.m24506d(c6210r);
        } else {
            C6218z.m23764c("XSender.R.S", "[%s] > failed. Please see logcat for more detail", this.f20928c);
            this.f20929d.m24507e();
        }
    }
}
