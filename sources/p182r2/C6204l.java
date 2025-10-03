package p182r2;

import android.os.SystemClock;
import com.cyberlink.you.chat.C2898i;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.chat.p035p.MetaExtension;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.Arrays;
import java.util.List;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.C5616j;
import p209u2.C6383t;
import p218v2.C6456d;
import p236x2.C6566a;
import p241x7.C6585a;

/* renamed from: r2.l */
/* loaded from: classes.dex */
public class C6204l extends AbstractC6197e<AbstractC6191b, C6201i> {

    /* renamed from: h */
    public static final List<MessageObj.MessageType> f20895h = Arrays.asList(MessageObj.MessageType.Text, MessageObj.MessageType.Sticker, MessageObj.MessageType.AnimPngSticker, MessageObj.MessageType.AnimSticker, MessageObj.MessageType.ReplyText, MessageObj.MessageType.Comment);

    /* renamed from: i */
    public static final List<MessageObj.MessageType> f20896i = Arrays.asList(MessageObj.MessageType.Photo, MessageObj.MessageType.Video, MessageObj.MessageType.File, MessageObj.MessageType.Audio, MessageObj.MessageType.PhotoNote, MessageObj.MessageType.CreateMedia);

    /* renamed from: j */
    public static final List<MessageObj.MessageType> f20897j = Arrays.asList(MessageObj.MessageType.ShareLocation, MessageObj.MessageType.UserContact);

    /* renamed from: g */
    public final long f20898g;

    /* renamed from: r2.l$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f20899a;

        static {
            int[] iArr = new int[MessageObj.MessageType.values().length];
            f20899a = iArr;
            try {
                iArr[MessageObj.MessageType.Text.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f20899a[MessageObj.MessageType.Sticker.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f20899a[MessageObj.MessageType.AnimSticker.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f20899a[MessageObj.MessageType.AnimPngSticker.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f20899a[MessageObj.MessageType.Photo.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f20899a[MessageObj.MessageType.File.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f20899a[MessageObj.MessageType.ReplyText.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f20899a[MessageObj.MessageType.CreateMedia.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f20899a[MessageObj.MessageType.DeleteMedia.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f20899a[MessageObj.MessageType.VideoDelete.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f20899a[MessageObj.MessageType.FileDelete.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public C6204l(AbstractC6191b abstractC6191b, C6201i c6201i) {
        super(abstractC6191b, c6201i, c6201i.f20884c.m14788z().getTime());
        this.f20898g = SystemClock.elapsedRealtime();
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: c */
    public Message mo23705c() {
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(((C6201i) this.f20875f).f20852a);
        if (messageObjM15179r == null) {
            C6218z.m23762a(mo23706d(), "[%s] > messageId not in database", mo23707e());
            return null;
        }
        if ("0".equals(messageObjM15179r.m14740B())) {
            C6218z.m23762a(mo23706d(), "[%s] > already sent", mo23707e());
            return null;
        }
        if (!C6456d.m24714D().m24748G()) {
            C6218z.m23769h(mo23706d(), "[%s] > network unavailable", mo23707e());
        }
        if (m23730j()) {
            C6218z.m23770i(mo23706d(), "[%s] > timeout in queue", mo23707e());
            return m23733m();
        }
        Group groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j());
        if (groupM15077n == null) {
            C6218z.m23770i(mo23706d(), "[%s] > target group unavailable, GID: %s", mo23707e(), messageObjM15179r.m14772j());
            return m23733m();
        }
        Message messageM14593f = C2925v.m14593f(groupM15077n.f13716c, ((C6201i) this.f20875f).f20883b, messageObjM15179r);
        C6585a.m25196c(messageM14593f);
        return groupM15077n.f13711J ? m23729i(messageObjM15179r.m14772j(), messageM14593f) : messageM14593f;
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: d */
    public String mo23706d() {
        return "XSender.M.T";
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: e */
    public String mo23707e() {
        return ((C6201i) this.f20875f).f20852a;
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: f */
    public void mo23708f() {
        m23732l();
    }

    @Override // p182r2.AbstractC6197e
    /* renamed from: g */
    public void mo23709g() {
        m23731k();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [org.jivesoftware.smack.packet.Message, org.jivesoftware.smack.packet.b] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v4, types: [org.jivesoftware.smack.packet.c] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* renamed from: i */
    public final Message m23729i(String str, Message message) {
        String strMo190a;
        C2898i c2898i;
        ?? r72;
        C2898i c2898i2;
        MetaExtension metaExtension;
        Object obj = this.f20875f;
        if (!((C6201i) obj).f20885d) {
            return message;
        }
        switch (a.f20899a[((C6201i) obj).f20884c.m14778p().ordinal()]) {
            case 1:
                strMo190a = "<body>" + ((Object) C5616j.m22341f(message.m22106z())) + "</" + TtmlNode.TAG_BODY + ">";
                c2898i = null;
                r72 = 0;
                break;
            case 2:
            case 3:
            case 4:
                c2898i = (C2898i) message.m22157g("sticker", "U");
                strMo190a = null;
                r72 = strMo190a;
                break;
            case 5:
                c2898i2 = (C2898i) message.m22157g("media", "U");
                metaExtension = new MetaExtension("media", c2898i2.m14372d("mediaId"), null);
                strMo190a = null;
                c2898i = c2898i2;
                r72 = metaExtension;
                break;
            case 6:
                c2898i2 = (C2898i) message.m22157g("file", "U");
                metaExtension = new MetaExtension("file", c2898i2.m14372d("mediaId"), null);
                strMo190a = null;
                c2898i = c2898i2;
                r72 = metaExtension;
                break;
            case 7:
                c2898i = (C2898i) message.m22157g("textReply", "urn:xmpp:textreply:0");
                strMo190a = null;
                r72 = strMo190a;
                break;
            case 8:
                c2898i = (C2898i) message.m22157g("groupAlbum", "U");
                strMo190a = null;
                r72 = strMo190a;
                break;
            case 9:
                c2898i = (C2898i) message.m22157g("photoDelete", "U");
                strMo190a = null;
                r72 = strMo190a;
                break;
            case 10:
                c2898i = (C2898i) message.m22157g("videoDelete", "U");
                strMo190a = null;
                r72 = strMo190a;
                break;
            case 11:
                c2898i = (C2898i) message.m22157g("fileDelete", "U");
                strMo190a = null;
                r72 = strMo190a;
                break;
            default:
                C6218z.m23769h(mo23706d(), "[%s] > w/o implementation", mo23707e());
                c2898i = null;
                strMo190a = null;
                r72 = strMo190a;
                break;
        }
        if (c2898i != null) {
            strMo190a = c2898i.mo190a();
        }
        if (C6383t.m24517f(strMo190a)) {
            C6218z.m23770i(mo23706d(), "[%s] > no encrypt data", mo23707e());
            return m23733m();
        }
        String strM14305g = C2889b.m14298h().m14305g(str, strMo190a);
        if (C6383t.m24517f(strM14305g)) {
            C6218z.m23770i(mo23706d(), "[%s] > encrypt failed", mo23707e());
            return m23733m();
        }
        C2898i c2898i3 = new C2898i("encrypted", "U");
        c2898i3.m14376h(strM14305g);
        if (r72 != 0) {
            message.m22154b(r72);
        }
        message.m22154b(c2898i3);
        message.m22090P(null);
        message.m22163o(c2898i);
        return message;
    }

    /* renamed from: j */
    public final boolean m23730j() {
        return SystemClock.elapsedRealtime() - this.f20898g > 180000;
    }

    /* renamed from: k */
    public final void m23731k() {
        MessageObj.MessageType messageTypeM14778p = ((C6201i) this.f20875f).f20884c.m14778p();
        if (f20895h.contains(messageTypeM14778p)) {
            C6566a.m25162u("Usage_Data_Msg");
        } else if (f20896i.contains(messageTypeM14778p)) {
            C6566a.m25162u("Usage_Data_File");
        } else if (f20897j.contains(messageTypeM14778p)) {
            C6566a.m25162u("Usage_Data_Misc");
        }
    }

    /* renamed from: l */
    public final void m23732l() {
        if (C2950b0.m14916o().m15161F(((C6201i) this.f20875f).f20884c.m14777o())) {
            C6566a.m25152k(true, ((C6201i) this.f20875f).f20884c.m14778p());
        }
    }

    /* renamed from: m */
    public final Message m23733m() {
        m23732l();
        ((AbstractC6191b) this.f20874e).mo23669g((C6201i) this.f20875f);
        return null;
    }
}
