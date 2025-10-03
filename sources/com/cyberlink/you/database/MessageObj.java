package com.cyberlink.you.database;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.drawable.AnimationDrawable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5170o0;

/* loaded from: classes.dex */
public class MessageObj {

    /* renamed from: K */
    public static final Comparator<MessageObj> f12923K = new C2937a();

    /* renamed from: C */
    public boolean f12926C;

    /* renamed from: D */
    public WeakReference<Activity> f12927D;

    /* renamed from: E */
    public WeakReference<TextView> f12928E;

    /* renamed from: F */
    public WeakReference<ImageView> f12929F;

    /* renamed from: G */
    public WeakReference<View> f12930G;

    /* renamed from: H */
    public WeakReference<RelativeLayout> f12931H;

    /* renamed from: I */
    public Timer f12932I;

    /* renamed from: J */
    public Timer f12933J;

    /* renamed from: a */
    public long f12934a;

    /* renamed from: b */
    public String f12935b;

    /* renamed from: c */
    public String f12936c;

    /* renamed from: d */
    public Date f12937d;

    /* renamed from: e */
    public MessageType f12938e;

    /* renamed from: f */
    public String f12939f;

    /* renamed from: g */
    public int f12940g;

    /* renamed from: h */
    public String f12941h;

    /* renamed from: i */
    public String f12942i;

    /* renamed from: j */
    public String f12943j;

    /* renamed from: k */
    public String f12944k;

    /* renamed from: m */
    public String f12946m;

    /* renamed from: n */
    public String f12947n;

    /* renamed from: o */
    public String f12948o;

    /* renamed from: p */
    public int f12949p;

    /* renamed from: q */
    public TTLStatus f12950q;

    /* renamed from: r */
    public long f12951r;

    /* renamed from: s */
    public int f12952s;

    /* renamed from: t */
    public MemberStatus f12953t;

    /* renamed from: u */
    public SpannableString f12954u;

    /* renamed from: v */
    public long f12955v;

    /* renamed from: w */
    public boolean f12956w;

    /* renamed from: x */
    public String f12957x;

    /* renamed from: y */
    public Date f12958y = null;

    /* renamed from: z */
    public int f12959z = 0;

    /* renamed from: A */
    public boolean f12924A = false;

    /* renamed from: B */
    public boolean f12925B = false;

    /* renamed from: l */
    public String f12945l = m14754P();

    public enum MemberStatus {
        NO_MemberStatus,
        MemberLeave,
        MemberCreate,
        MemberCreateV2,
        MemberDeleted,
        MediaCreate,
        AdminDeleted,
        AdminCreate,
        MediaDelete,
        AlbumCreate,
        AlbumDelete,
        AlbumUpdate,
        PhotoComment,
        DisplayNameUpdated
    }

    public enum MessageType {
        Text,
        Photo,
        Sticker,
        AnimSticker,
        DeliveryReceipt,
        None,
        Date,
        Event,
        AnimPngSticker,
        Comment,
        CreateMedia,
        DeleteMedia,
        CommentUpdate,
        PhotoNote,
        Audio,
        NewVersion,
        UserContact,
        StickerTypeUnknown,
        AnnouncementType01,
        AnnouncementType02,
        AnnouncementType03,
        Video,
        ShareLocation,
        UnreadLine,
        File,
        RTC,
        Call,
        Poll,
        PollPost,
        ReportToAdmin,
        ReplyText,
        VideoDelete,
        FileDelete,
        TOPIC,
        SUPERVISE,
        ENCRYPTED_MSG,
        ENCRYPTED_SESSION
    }

    public enum NoteType {
        None,
        Text,
        Audio
    }

    public enum TTLStatus {
        NO_TTL,
        NOT_START,
        START,
        END
    }

    /* renamed from: com.cyberlink.you.database.MessageObj$a */
    public class C2937a implements Comparator<MessageObj> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObj messageObj, MessageObj messageObj2) {
            MessageType messageType;
            MessageType messageTypeM14778p = messageObj == null ? MessageType.None : messageObj.m14778p();
            MessageType messageTypeM14778p2 = messageObj2 == null ? MessageType.None : messageObj2.m14778p();
            Date date = messageObj == null ? new Date(0L) : messageObj.m14788z();
            Date date2 = messageObj2 == null ? new Date(0L) : messageObj2.m14788z();
            if (date.before(date2)) {
                return -1;
            }
            if (date.after(date2)) {
                return 1;
            }
            MessageType messageType2 = MessageType.Date;
            if (messageTypeM14778p == messageType2) {
                return -1;
            }
            if (messageTypeM14778p2 == messageType2 || messageTypeM14778p == (messageType = MessageType.UnreadLine)) {
                return 1;
            }
            return messageTypeM14778p2 == messageType ? -1 : 0;
        }
    }

    /* renamed from: com.cyberlink.you.database.MessageObj$b */
    public class C2938b extends TimerTask {

        /* renamed from: com.cyberlink.you.database.MessageObj$b$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ Activity f13023b;

            /* renamed from: c */
            public final /* synthetic */ long f13024c;

            public a(Activity activity, long j9) {
                this.f13023b = activity;
                this.f13024c = j9;
            }

            @Override // java.lang.Runnable
            public void run() {
                TextView textView = (TextView) MessageObj.this.f12928E.get();
                Activity activity = this.f13023b;
                if (activity == null || textView == null) {
                    return;
                }
                textView.setText(activity.getString(R.string.self_destruct_chat_start, CLUtility.m16549j1(this.f13024c)));
            }
        }

        /* renamed from: com.cyberlink.you.database.MessageObj$b$b */
        public class b implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ Activity f13026b;

            public b(Activity activity) {
                this.f13026b = activity;
            }

            @Override // java.lang.Runnable
            public void run() throws JSONException {
                RelativeLayout relativeLayout;
                C2950b0.m14916o().m15169h(MessageObj.this.m14777o());
                JSONObject jSONObjectM7487T = Globals.m7388i0().m7487T();
                try {
                    jSONObjectM7487T.put(MessageObj.this.m14777o(), MessageObj.this.m14788z().getTime());
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
                Globals.m7388i0().m7440I2(jSONObjectM7487T);
                ChatDialogActivity chatDialogActivity = (ChatDialogActivity) this.f13026b;
                if (chatDialogActivity != null) {
                    if (MessageObj.this.f12930G == null || MessageObj.this.f12929F == null) {
                        chatDialogActivity.m11583na(MessageObj.this);
                        return;
                    }
                    View view = (View) MessageObj.this.f12930G.get();
                    ImageView imageView = (ImageView) MessageObj.this.f12929F.get();
                    if (view == null || imageView == null) {
                        chatDialogActivity.m11583na(MessageObj.this);
                        return;
                    }
                    view.setVisibility(8);
                    imageView.setVisibility(0);
                    if (MessageObj.this.f12931H != null && MessageObj.this.f12931H.get() != null && (relativeLayout = (RelativeLayout) MessageObj.this.f12931H.get()) != null) {
                        relativeLayout.setVisibility(8);
                    }
                    imageView.setBackgroundResource(R.drawable.message_explode);
                    ((AnimationDrawable) imageView.getBackground()).start();
                    MessageObj.this.f12933J = new Timer();
                    MessageObj.this.f12933J.schedule(MessageObj.this.new C2939c(), 2250L);
                }
            }
        }

        public C2938b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Activity activity;
            if (MessageObj.this.f12927D == null || MessageObj.this.f12928E == null || (activity = (Activity) MessageObj.this.f12927D.get()) == null) {
                return;
            }
            long jLongValue = Long.valueOf(MessageObj.this.m14787y()).longValue() - ((FriendsClient.m15646K() - MessageObj.this.m14786x()) / 1000);
            if (jLongValue >= 0) {
                activity.runOnUiThread(new a(activity, jLongValue));
                return;
            }
            MessageObj.this.m14763Y(TTLStatus.END);
            MessageObj.this.f12932I.cancel();
            activity.runOnUiThread(new b(activity));
        }
    }

    /* renamed from: com.cyberlink.you.database.MessageObj$c */
    public class C2939c extends TimerTask {

        /* renamed from: com.cyberlink.you.database.MessageObj$c$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ Activity f13029b;

            public a(Activity activity) {
                this.f13029b = activity;
            }

            @Override // java.lang.Runnable
            public void run() {
                ChatDialogActivity chatDialogActivity = (ChatDialogActivity) this.f13029b;
                if (chatDialogActivity != null) {
                    chatDialogActivity.m11583na(MessageObj.this);
                }
            }
        }

        public C2939c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Activity activity;
            MessageObj.this.f12933J.cancel();
            MessageObj.this.f12933J = null;
            if (MessageObj.this.f12927D == null || MessageObj.this.f12928E == null || (activity = (Activity) MessageObj.this.f12927D.get()) == null) {
                return;
            }
            activity.runOnUiThread(new a(activity));
        }
    }

    public MessageObj(long j9, String str, String str2, long j10, MessageType messageType, String str3, int i9, String str4, String str5, String str6, TTLStatus tTLStatus, long j11, int i10, long j12, MemberStatus memberStatus, boolean z8, String str7, String str8, String str9, String str10, String str11, boolean z9, int i11) {
        this.f12934a = j9;
        this.f12935b = str;
        this.f12936c = str2;
        this.f12937d = new Date(j10);
        this.f12938e = messageType;
        this.f12939f = str3;
        this.f12940g = i9;
        this.f12941h = str4;
        this.f12942i = str5;
        this.f12943j = str6;
        this.f12950q = tTLStatus;
        this.f12951r = j11;
        this.f12952s = i10;
        this.f12953t = memberStatus;
        this.f12955v = j12;
        this.f12956w = z8;
        this.f12957x = str7;
        this.f12944k = str8;
        this.f12946m = str9;
        this.f12947n = str10;
        this.f12948o = str11;
        this.f12926C = z9;
        this.f12949p = i11;
    }

    /* renamed from: i */
    public static int m14738i(MessageObj messageObj, MessageObj messageObj2) {
        return f12923K.compare(messageObj, messageObj2);
    }

    /* renamed from: A */
    public String m14739A() {
        return this.f12957x;
    }

    /* renamed from: B */
    public String m14740B() {
        return this.f12943j;
    }

    /* renamed from: C */
    public TTLStatus m14741C() {
        return this.f12950q;
    }

    /* renamed from: D */
    public Date m14742D() {
        return this.f12958y;
    }

    /* renamed from: E */
    public int m14743E() {
        return this.f12949p;
    }

    /* renamed from: F */
    public String m14744F() {
        return this.f12944k;
    }

    /* renamed from: G */
    public String m14745G() {
        return this.f12941h;
    }

    /* renamed from: H */
    public String m14746H() {
        return this.f12942i;
    }

    /* renamed from: I */
    public String m14747I(String str) {
        try {
            if (this.f12939f == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(this.f12939f);
            if (!jSONObject.has(str) || jSONObject.isNull(str)) {
                return null;
            }
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            if (str.equals("mediaId")) {
                return m14779q();
            }
            return null;
        }
    }

    /* renamed from: J */
    public String m14748J(String str, String str2) {
        try {
            if (this.f12939f == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(this.f12939f);
            JSONObject jSONObject2 = (!jSONObject.has(str) || jSONObject.isNull(str)) ? null : jSONObject.getJSONObject(str);
            if (jSONObject2 == null || !jSONObject2.has(str2) || jSONObject2.isNull(str2)) {
                return null;
            }
            return jSONObject2.getString(str2);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: K */
    public boolean m14749K() {
        return (C5170o0.m20170e(this.f12946m) || C5170o0.m20170e(this.f12947n)) ? false : true;
    }

    /* renamed from: L */
    public boolean m14750L() {
        return this.f12926C;
    }

    /* renamed from: M */
    public boolean m14751M() {
        return this.f12956w;
    }

    /* renamed from: N */
    public boolean m14752N() {
        return this.f12955v != 0;
    }

    /* renamed from: O */
    public boolean m14753O() {
        return !this.f12950q.equals(TTLStatus.NO_TTL);
    }

    /* renamed from: P */
    public final String m14754P() {
        MessageType messageType = this.f12938e;
        return messageType == null ? "" : messageType == MessageType.PhotoNote ? m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) : (messageType == MessageType.Comment || messageType == MessageType.CommentUpdate) ? m14747I("comment") : messageType == MessageType.ReplyText ? m14747I("replyText") : "";
    }

    /* renamed from: Q */
    public void m14755Q(String str) {
        this.f12954u = C5170o0.m20168c(this.f12939f, str);
    }

    /* renamed from: R */
    public void m14756R(MessageType messageType) {
        this.f12938e = messageType;
    }

    /* renamed from: S */
    public void m14757S(String str) {
        this.f12939f = str;
    }

    /* renamed from: T */
    public void m14758T(String str) {
        this.f12935b = str;
    }

    /* renamed from: U */
    public void m14759U(int i9) {
        this.f12940g = i9;
    }

    /* renamed from: V */
    public void m14760V(long j9) {
        this.f12951r = j9;
    }

    /* renamed from: W */
    public void m14761W(Date date) {
        this.f12937d = date;
    }

    /* renamed from: X */
    public void m14762X(String str) {
        this.f12943j = str;
    }

    /* renamed from: Y */
    public void m14763Y(TTLStatus tTLStatus) {
        this.f12950q = tTLStatus;
    }

    /* renamed from: Z */
    public void m14764Z(Date date) {
        this.f12958y = date;
    }

    /* renamed from: a0 */
    public void m14765a0(String str) {
        this.f12944k = str;
    }

    /* renamed from: b0 */
    public void m14766b0(String str) {
        this.f12942i = str;
    }

    /* renamed from: c0 */
    public void m14767c0(Activity activity, TextView textView, View view, ImageView imageView, RelativeLayout relativeLayout) {
        if (this.f12932I == null) {
            Timer timer = new Timer();
            this.f12932I = timer;
            timer.schedule(new C2938b(), new Date(), 1000L);
        }
        this.f12927D = new WeakReference<>(activity);
        this.f12928E = new WeakReference<>(textView);
        this.f12930G = new WeakReference<>(view);
        this.f12929F = new WeakReference<>(imageView);
        this.f12931H = new WeakReference<>(relativeLayout);
    }

    /* renamed from: d0 */
    public ContentValues m14768d0() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(m14774l()));
        contentValues.put("MessageId", m14777o());
        contentValues.put("GroupId", m14772j());
        contentValues.put("SendTime", Long.valueOf(m14788z().getTime()));
        contentValues.put("MessageType", m14778p().toString());
        contentValues.put("MessageContent", m14779q());
        contentValues.put("ReadCount", Integer.valueOf(m14783u()));
        contentValues.put("UserId", m14745G());
        contentValues.put("UserName", m14746H());
        contentValues.put("Status", m14740B());
        contentValues.put("TTLStatus", m14741C().toString());
        contentValues.put("SDStarttime", Long.valueOf(m14786x()));
        contentValues.put("SDTotaltime", Integer.valueOf(m14787y()));
        contentValues.put("SCSendtime", Long.valueOf(m14784v()));
        contentValues.put("MemberStatus", m14776n().toString());
        contentValues.put("IsNewVersion", Integer.valueOf(m14751M() ? 1 : 0));
        contentValues.put("SrcXml", m14739A());
        contentValues.put("UploadStatus", m14744F());
        contentValues.put("SearchMessage", m14785w());
        contentValues.put("OriginalSenderId", m14782t());
        contentValues.put("OriginalMessageId", m14781s());
        contentValues.put("LastForwarderId", m14775m());
        contentValues.put("isFromBroadcaster", Boolean.valueOf(m14750L()));
        contentValues.put("totalSizeOfInvitee", Integer.valueOf(m14743E()));
        return contentValues;
    }

    /* renamed from: e0 */
    public ContentValues m14769e0(String str) {
        if (str == null || str.isEmpty()) {
            return new ContentValues();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return m14770f0(arrayList);
    }

    /* renamed from: f0 */
    public ContentValues m14770f0(List<String> list) {
        if (list == null) {
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        for (String str : list) {
            if (str != null && !str.isEmpty()) {
                if (str.equals("MessageId")) {
                    contentValues.put("MessageId", m14777o());
                } else if (str.equals("GroupId")) {
                    contentValues.put("GroupId", m14772j());
                } else if (str.equals("SendTime")) {
                    contentValues.put("SendTime", Long.valueOf(m14788z().getTime()));
                } else if (str.equals("MessageType")) {
                    contentValues.put("MessageType", m14778p().toString());
                } else if (str.equals("MessageContent")) {
                    contentValues.put("MessageContent", m14779q());
                } else if (str.equals("ReadCount")) {
                    contentValues.put("ReadCount", Integer.valueOf(m14783u()));
                } else if (str.equals("UserId")) {
                    contentValues.put("UserId", m14745G());
                } else if (str.equals("UserName")) {
                    contentValues.put("UserName", m14746H());
                } else if (str.equals("Status")) {
                    contentValues.put("Status", m14740B());
                } else if (str.equals("TTLStatus")) {
                    contentValues.put("TTLStatus", m14741C().toString());
                } else if (str.equals("SDStarttime")) {
                    contentValues.put("SDStarttime", Long.valueOf(m14786x()));
                } else if (str.equals("SDTotaltime")) {
                    contentValues.put("SDTotaltime", Integer.valueOf(m14787y()));
                } else if (str.equals("SCSendtime")) {
                    contentValues.put("SCSendtime", Long.valueOf(m14784v()));
                } else if (str.equals("MemberStatus")) {
                    contentValues.put("MemberStatus", m14776n().toString());
                } else if (str.equals("IsNewVersion")) {
                    contentValues.put("IsNewVersion", Integer.valueOf(m14751M() ? 1 : 0));
                } else if (str.equals("SrcXml")) {
                    contentValues.put("SrcXml", m14739A());
                } else if (str.equals("UploadStatus")) {
                    contentValues.put("UploadStatus", m14744F());
                } else if (str.equals("SearchMessage")) {
                    contentValues.put("SearchMessage", m14785w());
                } else if (str.equals("OriginalSenderId")) {
                    contentValues.put("OriginalSenderId", m14782t());
                } else if (str.equals("OriginalMessageId")) {
                    contentValues.put("OriginalMessageId", m14781s());
                } else if (str.equals("LastForwarderId")) {
                    contentValues.put("LastForwarderId", m14775m());
                } else if (str.equals("isFromBroadcaster")) {
                    contentValues.put("isFromBroadcaster", Boolean.valueOf(m14750L()));
                } else if (str.equals("totalSizeOfInvitee")) {
                    contentValues.put("totalSizeOfInvitee", Integer.valueOf(m14743E()));
                }
            }
        }
        return contentValues;
    }

    /* renamed from: g0 */
    public void m14771g0(MessageObj messageObj) {
        this.f12934a = messageObj.f12934a;
        this.f12935b = messageObj.f12935b;
        this.f12936c = messageObj.f12936c;
        this.f12937d = messageObj.f12937d;
        this.f12938e = messageObj.f12938e;
        this.f12939f = messageObj.f12939f;
        this.f12940g = messageObj.f12940g;
        this.f12941h = messageObj.f12941h;
        this.f12942i = messageObj.f12942i;
        this.f12943j = messageObj.f12943j;
        this.f12944k = messageObj.f12944k;
        this.f12945l = messageObj.f12945l;
        this.f12946m = messageObj.f12946m;
        this.f12947n = messageObj.f12947n;
        this.f12948o = messageObj.f12948o;
    }

    /* renamed from: j */
    public String m14772j() {
        return this.f12936c;
    }

    /* renamed from: k */
    public SpannableString m14773k() {
        return this.f12954u;
    }

    /* renamed from: l */
    public long m14774l() {
        return this.f12934a;
    }

    /* renamed from: m */
    public String m14775m() {
        return this.f12948o;
    }

    /* renamed from: n */
    public MemberStatus m14776n() {
        return this.f12953t;
    }

    /* renamed from: o */
    public String m14777o() {
        return this.f12935b;
    }

    /* renamed from: p */
    public MessageType m14778p() {
        return this.f12938e;
    }

    /* renamed from: q */
    public String m14779q() {
        return this.f12939f;
    }

    /* renamed from: r */
    public NoteType m14780r() {
        if (!this.f12938e.equals(MessageType.PhotoNote)) {
            return NoteType.None;
        }
        String strM14747I = m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
        return (strM14747I == null || strM14747I.isEmpty()) ? NoteType.Audio : NoteType.Text;
    }

    /* renamed from: s */
    public String m14781s() {
        return this.f12947n;
    }

    /* renamed from: t */
    public String m14782t() {
        return this.f12946m;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\n");
        stringBuffer.append("  _id: " + this.f12934a);
        stringBuffer.append("\n");
        stringBuffer.append("  MessageId: " + this.f12935b);
        stringBuffer.append("\n");
        stringBuffer.append("  GroupId: " + this.f12936c);
        stringBuffer.append("\n");
        stringBuffer.append("  SendingTime: " + this.f12937d);
        stringBuffer.append("\n");
        stringBuffer.append("  MessageContent: " + this.f12939f);
        stringBuffer.append("\n");
        stringBuffer.append("  ReadCount: " + this.f12940g);
        stringBuffer.append("\n");
        stringBuffer.append("  UserId: " + this.f12941h);
        stringBuffer.append("\n");
        stringBuffer.append("  UserName: " + this.f12942i);
        stringBuffer.append("\n");
        stringBuffer.append("  Status: " + this.f12943j);
        stringBuffer.append("\n");
        stringBuffer.append("  TTLStatus: " + this.f12950q);
        stringBuffer.append("\n");
        stringBuffer.append("  SelfDestructStartTime: " + this.f12951r);
        stringBuffer.append("\n");
        stringBuffer.append("  SelfDestructTotalTime: " + this.f12952s);
        stringBuffer.append("\n");
        stringBuffer.append("  MemberStatus: " + this.f12953t);
        stringBuffer.append("\n");
        stringBuffer.append("  ScheduledTime: " + this.f12955v);
        stringBuffer.append("\n");
        stringBuffer.append("  IsNewVersion: " + this.f12956w);
        stringBuffer.append("\n");
        stringBuffer.append("  SrcXml: " + this.f12957x);
        stringBuffer.append("\n");
        stringBuffer.append("  UploadStatus: " + this.f12944k);
        stringBuffer.append("\n");
        stringBuffer.append("  SearchMessage: " + this.f12945l);
        stringBuffer.append("\n");
        stringBuffer.append("  OriginalSenderId: " + this.f12946m);
        stringBuffer.append("\n");
        stringBuffer.append("  OriginalMessageId: " + this.f12947n);
        stringBuffer.append("\n");
        stringBuffer.append("  LastForwarderId: " + this.f12948o);
        stringBuffer.append("\n");
        stringBuffer.append("  mIsFromBroadcaster: " + this.f12926C);
        stringBuffer.append("\n");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    /* renamed from: u */
    public int m14783u() {
        return this.f12940g;
    }

    /* renamed from: v */
    public long m14784v() {
        return this.f12955v;
    }

    /* renamed from: w */
    public String m14785w() {
        if (C5170o0.m20170e(this.f12945l)) {
            this.f12945l = m14754P();
        }
        return this.f12945l;
    }

    /* renamed from: x */
    public long m14786x() {
        return this.f12951r;
    }

    /* renamed from: y */
    public int m14787y() {
        return this.f12952s;
    }

    /* renamed from: z */
    public Date m14788z() {
        return this.f12937d;
    }
}
