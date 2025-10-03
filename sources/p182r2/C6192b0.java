package p182r2;

import com.cyberlink.you.chat.AdvancedMessage;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import p254z2.C6818b;

/* renamed from: r2.b0 */
/* loaded from: classes.dex */
public class C6192b0 {
    /* renamed from: a */
    public static String m23681a(Group group, MessageObj messageObj) {
        if (group.f13716c.equals("Dual")) {
            return group.f13723j;
        }
        return group.f13723j + "/" + messageObj.m14745G();
    }

    /* renamed from: b */
    public static Message m23682b(String str, Group group, MessageObj messageObj, boolean z8) {
        return m23683c(str, m23681a(group, messageObj), messageObj, z8);
    }

    /* renamed from: c */
    public static Message m23683c(String str, String str2, MessageObj messageObj, boolean z8) {
        String strM14777o = messageObj.m14777o();
        MessageObj.TTLStatus tTLStatusM14741C = messageObj.m14741C();
        MessageObj.TTLStatus tTLStatus = MessageObj.TTLStatus.NO_TTL;
        if (!tTLStatusM14741C.equals(tTLStatus) && !z8) {
            C6218z.m23767f(str, "> self-destruct message, skip: %s", strM14777o);
            return null;
        }
        if (messageObj.m14741C().equals(tTLStatus) && z8) {
            C6218z.m23767f(str, "> non-self-destruct message, skip: %s", strM14777o);
            return null;
        }
        if (C6818b.m25402m(messageObj)) {
            C6218z.m23767f(str, "> self-message, skip: %s", strM14777o);
            return null;
        }
        AdvancedMessage advancedMessage = new AdvancedMessage(str2, Message.Type.chat);
        if (z8) {
            advancedMessage.m14056e0("1");
        }
        advancedMessage.m22154b(new DeliveryReceipt(strM14777o));
        return advancedMessage;
    }
}
