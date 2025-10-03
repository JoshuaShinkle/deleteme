package p182r2;

import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2997x0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.Message;

/* renamed from: r2.s */
/* loaded from: classes.dex */
public class RunnableC6211s implements Runnable {

    /* renamed from: b */
    public final a f20914b;

    /* renamed from: r2.s$a */
    public interface a {
        /* renamed from: a */
        void mo23739a(ArrayList<C6210r> arrayList);
    }

    public RunnableC6211s(a aVar) {
        this.f20914b = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Message messageM23682b;
        C6218z.m23768g("XSender.R.R", "run", new Object[0]);
        ArrayList<C6210r> arrayList = new ArrayList<>();
        List<C2997x0> listM15265f = C2950b0.m14923v().m15265f();
        if (listM15265f != null && !listM15265f.isEmpty()) {
            for (C2997x0 c2997x0 : listM15265f) {
                String strM15269a = c2997x0.m15269a();
                MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(strM15269a);
                if (messageObjM15179r == null) {
                    C2950b0.m14923v().m15268i(strM15269a);
                } else {
                    Group groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j());
                    if (groupM15077n != null && (messageM23682b = C6192b0.m23682b("XSender.R.R", groupM15077n, messageObjM15179r, false)) != null) {
                        messageM23682b.m22166s(c2997x0.m15270b());
                        arrayList.add(new C6210r(strM15269a, messageM23682b));
                        C6218z.m23762a("XSender.R.R", "> add: %s", strM15269a);
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.f20914b.mo23739a(arrayList);
        }
        C6218z.m23768g("XSender.R.R", "> #%d done", Integer.valueOf(arrayList.size()));
    }
}
