package p182r2;

import com.cyberlink.util.PriorityThreadPoolExecutor;
import com.cyberlink.you.chat.AdvancedMessage;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import org.jivesoftware.smack.packet.Message;

/* renamed from: r2.w */
/* loaded from: classes.dex */
public class C6215w extends AbstractC6197e<C6214v, C6210r> {
    public C6215w(C6214v c6214v, C6210r c6210r) {
        super(c6214v, c6210r, m23759i(c6210r.f20912c));
    }

    /* renamed from: i */
    public static PriorityThreadPoolExecutor.Priority m23759i(Message message) {
        return ((message instanceof AdvancedMessage) && "1".equals(((AdvancedMessage) message).m14054c0())) ? PriorityThreadPoolExecutor.Priority.HIGH : PriorityThreadPoolExecutor.Priority.NORMAL;
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: c */
    public Message mo23705c() {
        String str = ((C6210r) this.f20875f).f20911b;
        if (C2950b0.m14923v().m15266g(((C6210r) this.f20875f).f20852a) == null) {
            C6218z.m23762a(mo23706d(), "[%s] > receiptId not in database: %s", mo23707e(), ((C6210r) this.f20875f).f20852a);
            return null;
        }
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
        if (messageObjM15179r != null && "5".equals(messageObjM15179r.m14740B())) {
            C6218z.m23762a(mo23706d(), "[%s] > receipt has sent", mo23707e());
            return null;
        }
        if (!C2950b0.m14904c().m14872g(str)) {
            return ((C6210r) this.f20875f).f20912c;
        }
        C6218z.m23762a(mo23706d(), "[%s] > found receipt sent", mo23707e());
        C2950b0.m14923v().m15268i(str);
        return null;
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: d */
    public String mo23706d() {
        return "XSender.R.T";
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: e */
    public String mo23707e() {
        return ((C6210r) this.f20875f).f20911b;
    }
}
