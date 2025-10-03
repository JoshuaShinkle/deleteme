package p182r2;

import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import java.util.List;

/* renamed from: r2.h */
/* loaded from: classes.dex */
public class RunnableC6200h implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        int i9;
        C6218z.m23768g("XSender.M.F", "run", new Object[0]);
        List<MessageObj> listM15187z = C2950b0.m14916o().m15187z();
        if (listM15187z == null || listM15187z.isEmpty()) {
            i9 = 0;
        } else {
            i9 = 0;
            for (MessageObj messageObj : listM15187z) {
                if (!"3".equals(messageObj.m14740B())) {
                    messageObj.m14762X("3");
                    C2950b0.m14916o().m15159D(messageObj.m14777o(), messageObj, "Status");
                    i9++;
                    C6218z.m23768g("XSender.M.F", "[%s] > mark failed", messageObj.m14777o());
                }
            }
        }
        C6218z.m23768g("XSender.M.F", "> #%d done", Integer.valueOf(i9));
    }
}
