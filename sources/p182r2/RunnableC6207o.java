package p182r2;

import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2997x0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.Message;
import p209u2.AbstractC6381r;

/* renamed from: r2.o */
/* loaded from: classes.dex */
public class RunnableC6207o implements Runnable {

    /* renamed from: b */
    public final Group f20904b;

    /* renamed from: c */
    public final AbstractC6381r<List<C6210r>, Void> f20905c;

    public RunnableC6207o(Group group, AbstractC6381r<List<C6210r>, Void> abstractC6381r) {
        this.f20904b = group;
        this.f20905c = abstractC6381r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m23736b(List list, ArrayList arrayList) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MessageObj messageObj = (MessageObj) it.next();
            Message messageM23682b = C6192b0.m23682b("XSender.R.G", this.f20904b, messageObj, false);
            if (messageM23682b != null) {
                messageM23682b.m22166s(C2925v.m14542A());
                C6210r c6210r = new C6210r(messageObj.m14777o(), messageM23682b);
                C2950b0.m14923v().m15267h(new C2997x0(messageObj.m14777o(), c6210r.f20912c.m22161k(), "2"));
                messageObj.m14762X("11");
                C2950b0.m14916o().m15159D(messageObj.m14777o(), messageObj, "Status");
                C6218z.m23762a("XSender.R.G", "> add: %s", messageObj.m14777o());
                arrayList.add(c6210r);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        C6218z.m23768g("XSender.R.G", "run: [%d] %s", Long.valueOf(this.f20904b.f13727n), this.f20904b.f13717d);
        final ArrayList arrayList = new ArrayList();
        final List<MessageObj> listM15172k = C2950b0.m14916o().m15172k(String.valueOf(this.f20904b.f13727n));
        if (!listM15172k.isEmpty() ? C2950b0.m14901C(new Runnable() { // from class: r2.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f20901b.m23736b(listM15172k, arrayList);
            }
        }) : true) {
            C6218z.m23768g("XSender.R.G", "> #%d done", Integer.valueOf(arrayList.size()));
            this.f20905c.m24506d(arrayList);
        } else {
            C6218z.m23764c("XSender.R.G", "> failed. Please see logcat for more detail", new Object[0]);
            this.f20905c.m24507e();
        }
    }
}
