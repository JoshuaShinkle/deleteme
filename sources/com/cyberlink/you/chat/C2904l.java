package com.cyberlink.you.chat;

import android.util.Log;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jivesoftware.smack.packet.InterfaceC5595c;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.carbons.packet.CarbonExtension;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5172p;
import p130l7.C5299a;
import p149n7.C5375a;
import p201t3.C6301o;
import p209u2.C6383t;

/* renamed from: com.cyberlink.you.chat.l */
/* loaded from: classes.dex */
public class C2904l {

    /* renamed from: a */
    public Message f12699a;

    /* renamed from: b */
    public MessageObj.MessageType f12700b;

    /* renamed from: f */
    public Group f12704f;

    /* renamed from: j */
    public boolean f12708j;

    /* renamed from: m */
    public String f12711m;

    /* renamed from: n */
    public String f12712n;

    /* renamed from: o */
    public String f12713o;

    /* renamed from: p */
    public String f12714p;

    /* renamed from: q */
    public String f12715q;

    /* renamed from: r */
    public String f12716r;

    /* renamed from: s */
    public XMPPManager.HandleType f12717s;

    /* renamed from: t */
    public int f12718t;

    /* renamed from: u */
    public Date f12719u;

    /* renamed from: v */
    public String f12720v;

    /* renamed from: w */
    public String f12721w;

    /* renamed from: x */
    public String f12722x;

    /* renamed from: y */
    public boolean f12723y;

    /* renamed from: c */
    public String f12701c = null;

    /* renamed from: d */
    public c f12702d = null;

    /* renamed from: e */
    public Map<String, Boolean> f12703e = new HashMap();

    /* renamed from: z */
    public final Set<MessageObj.MessageType> f12724z = new HashSet();

    /* renamed from: i */
    public boolean f12707i = false;

    /* renamed from: h */
    public boolean f12706h = false;

    /* renamed from: k */
    public boolean f12709k = false;

    /* renamed from: g */
    public boolean f12705g = false;

    /* renamed from: l */
    public boolean f12710l = false;

    /* renamed from: com.cyberlink.you.chat.l$a */
    public class a extends Thread {

        /* renamed from: b */
        public final /* synthetic */ String f12725b;

        /* renamed from: c */
        public final /* synthetic */ String f12726c;

        /* renamed from: d */
        public final /* synthetic */ String f12727d;

        /* renamed from: e */
        public final /* synthetic */ String f12728e;

        /* renamed from: f */
        public final /* synthetic */ String f12729f;

        /* renamed from: g */
        public final /* synthetic */ String f12730g;

        public a(String str, String str2, String str3, String str4, String str5, String str6) {
            this.f12725b = str;
            this.f12726c = str2;
            this.f12727d = str3;
            this.f12728e = str4;
            this.f12729f = str5;
            this.f12730g = str6;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("loadCommandUrl");
            try {
                try {
                    FriendsClient friendsClient = new FriendsClient();
                    String str = this.f12725b;
                    String strM14418h = C2904l.this.m14418h();
                    String strM14388C = C2904l.this.m14388C();
                    Group groupM15081r = C2950b0.m14912k().m15081r(strM14418h);
                    if (groupM15081r == null) {
                        groupM15081r = C2950b0.m14912k().m15081r(strM14388C);
                    }
                    if (groupM15081r != null) {
                        GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(this.f12725b);
                        if (groupAlbumObjM15056i == null && (groupAlbumObjM15056i = friendsClient.m15708O(this.f12725b, String.valueOf(groupM15081r.f13727n))) != null) {
                            C2950b0.m14911j().m15053f(groupAlbumObjM15056i);
                        }
                        if (groupAlbumObjM15056i != null && groupAlbumObjM15056i.m14677d().equals("Chat") && groupAlbumObjM15056i.m14676c().equals(groupM15081r.f13718e)) {
                            str = groupM15081r.f13718e;
                        }
                        long j9 = Long.parseLong(this.f12726c);
                        C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                        if (c2973l0M14725v == null || C5170o0.m20170e(c2973l0M14725v.m15149u().f13200d)) {
                            FriendsClient.m15657X(str, j9);
                        }
                    }
                    friendsClient.m15717U0();
                    HashMap map = new HashMap();
                    map.put("albumId", str);
                    map.put("mediaId", this.f12726c);
                    map.put("comment", this.f12727d);
                    map.put("commentType", this.f12728e);
                    map.put("duration", this.f12729f);
                    map.put("commentId", this.f12730g);
                    C2904l.this.f12701c = new JSONObject(map).toString();
                    if (C2950b0.m14920s().m15223l(Long.parseLong(this.f12730g)) == null) {
                        C2950b0.m14920s().m15217f(new C3061a(-1L, Long.parseLong(this.f12730g), Long.parseLong(this.f12726c), C2904l.this.m14430n() == null ? -1L : Long.parseLong(C2904l.this.m14430n()), this.f12727d, 0L, 0L, this.f12728e, new C3061a.a(), false, 2));
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            } finally {
                C2904l.this.f12703e.put("GetMediaInfo", Boolean.TRUE);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.l$b */
    public class b extends Thread {

        /* renamed from: b */
        public final /* synthetic */ String f12732b;

        /* renamed from: c */
        public final /* synthetic */ String f12733c;

        /* renamed from: d */
        public final /* synthetic */ String f12734d;

        /* renamed from: e */
        public final /* synthetic */ String f12735e;

        /* renamed from: f */
        public final /* synthetic */ String f12736f;

        /* renamed from: g */
        public final /* synthetic */ String f12737g;

        /* renamed from: h */
        public final /* synthetic */ String f12738h;

        public b(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.f12732b = str;
            this.f12733c = str2;
            this.f12734d = str3;
            this.f12735e = str4;
            this.f12736f = str5;
            this.f12737g = str6;
            this.f12738h = str7;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("loadCommandUrl");
            try {
                try {
                    FriendsClient friendsClient = new FriendsClient();
                    String str = this.f12732b;
                    String strM14418h = C2904l.this.m14418h();
                    String strM14388C = C2904l.this.m14388C();
                    Group groupM15081r = C2950b0.m14912k().m15081r(strM14418h);
                    if (groupM15081r == null) {
                        groupM15081r = C2950b0.m14912k().m15081r(strM14388C);
                    }
                    if (groupM15081r != null) {
                        GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(this.f12732b);
                        if (groupAlbumObjM15056i == null && (groupAlbumObjM15056i = friendsClient.m15708O(this.f12732b, String.valueOf(groupM15081r.f13727n))) != null) {
                            C2950b0.m14911j().m15053f(groupAlbumObjM15056i);
                        }
                        if (groupAlbumObjM15056i != null && groupAlbumObjM15056i.m14677d().equals("Chat") && groupAlbumObjM15056i.m14676c().equals(groupM15081r.f13718e)) {
                            str = groupM15081r.f13718e;
                        }
                        long j9 = Long.parseLong(this.f12733c);
                        C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                        if (c2973l0M14725v == null || C5170o0.m20170e(c2973l0M14725v.m15149u().f13200d)) {
                            FriendsClient.m15657X(str, j9);
                        }
                    }
                    friendsClient.m15717U0();
                    HashMap map = new HashMap();
                    map.put("albumId", str);
                    map.put("mediaId", this.f12733c);
                    map.put("comment", this.f12734d);
                    map.put("commentType", this.f12735e);
                    map.put(TtmlNode.ATTR_TTS_COLOR, this.f12736f);
                    map.put("commentId", this.f12737g);
                    map.put("thumbnail", this.f12738h);
                    C2904l.this.f12701c = new JSONObject(map).toString();
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            } finally {
                C2904l.this.f12703e.put("GetMediaInfo", Boolean.TRUE);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.l$c */
    public static class c {

        /* renamed from: a */
        public long f12740a;

        /* renamed from: b */
        public String f12741b;

        /* renamed from: c */
        public String f12742c;

        /* renamed from: d */
        public String f12743d;

        /* renamed from: e */
        public String f12744e;

        /* renamed from: f */
        public int f12745f;

        /* renamed from: g */
        public int f12746g;

        /* renamed from: h */
        public int f12747h;

        /* renamed from: i */
        public String f12748i;
    }

    public C2904l(Message message, XMPPManager.HandleType handleType) throws JSONException, InterruptedException, NumberFormatException {
        this.f12700b = MessageObj.MessageType.None;
        this.f12699a = message;
        this.f12708j = message.m22081G().contains("urn:xmpp:mam:3");
        this.f12717s = handleType;
        this.f12719u = new Date(FriendsClient.m15646K());
        m14421i0();
        int iM22077C = this.f12699a.m22077C();
        this.f12718t = iM22077C;
        if (iM22077C <= 1) {
            m14405V();
        } else {
            this.f12700b = MessageObj.MessageType.NewVersion;
        }
        if (this.f12699a.m22085K() != null) {
            this.f12719u = this.f12699a.m22085K();
        }
        m14390E();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O */
    public /* synthetic */ void m14378O(FriendsClient friendsClient, String str, String str2, String str3, String str4) {
        friendsClient.m15717U0();
        if (str3 == null) {
            Log.d("CLXMPPMessage", "Response is null");
            this.f12700b = MessageObj.MessageType.StickerTypeUnknown;
        } else if (str3.equals("200")) {
            this.f12700b = m14444u(str4);
        } else {
            Log.d("CLXMPPMessage", "statusCode=" + str3);
            this.f12700b = MessageObj.MessageType.StickerTypeUnknown;
        }
        this.f12703e.put("GetPackType", Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P */
    public /* synthetic */ void m14379P(FriendsClient friendsClient, String str, String str2, String str3, String str4) throws JSONException {
        friendsClient.m15717U0();
        if (str3 == null) {
            Log.d("CLXMPPMessage", "Response is null");
        } else if (str3.equals("200")) {
            m14433o0(str4);
        } else {
            Log.d("CLXMPPMessage", "statusCode=" + str3);
        }
        this.f12703e.put("ParseSticker", Boolean.TRUE);
    }

    /* renamed from: e */
    public static void m14384e(C2898i c2898i, Map<String, String> map, String str) {
        m14385f(c2898i, map, str, false);
    }

    /* renamed from: f */
    public static void m14385f(C2898i c2898i, Map<String, String> map, String str, boolean z8) {
        String strM14372d = c2898i.m14372d(str);
        if (C6383t.m24517f(strM14372d)) {
            return;
        }
        if (z8) {
            strM14372d = StringEscapeUtils.unescapeXml(strM14372d);
        }
        map.put(str, strM14372d);
    }

    /* renamed from: A */
    public Date m14386A() {
        return this.f12699a.m22085K();
    }

    /* renamed from: B */
    public int m14387B() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g(Constants.FirelogAnalytics.PARAM_TTL, "U");
            if (c2898i != null) {
                return Integer.parseInt(c2898i.m14374f());
            }
            return 0;
        } catch (Exception e9) {
            e9.printStackTrace();
            return 0;
        }
    }

    /* renamed from: C */
    public String m14388C() {
        return m14399N() ? this.f12711m : this.f12699a.m22162l();
    }

    /* renamed from: D */
    public MessageObj.MessageType m14389D() {
        return this.f12700b;
    }

    /* renamed from: E */
    public final void m14390E() {
        this.f12724z.add(MessageObj.MessageType.Text);
        this.f12724z.add(MessageObj.MessageType.AnimSticker);
        this.f12724z.add(MessageObj.MessageType.AnimPngSticker);
        this.f12724z.add(MessageObj.MessageType.Sticker);
        this.f12724z.add(MessageObj.MessageType.Photo);
        this.f12724z.add(MessageObj.MessageType.Comment);
        this.f12724z.add(MessageObj.MessageType.CreateMedia);
        this.f12724z.add(MessageObj.MessageType.DeleteMedia);
        this.f12724z.add(MessageObj.MessageType.Audio);
        this.f12724z.add(MessageObj.MessageType.CommentUpdate);
        this.f12724z.add(MessageObj.MessageType.PhotoNote);
        this.f12724z.add(MessageObj.MessageType.NewVersion);
        this.f12724z.add(MessageObj.MessageType.UserContact);
        this.f12724z.add(MessageObj.MessageType.StickerTypeUnknown);
        this.f12724z.add(MessageObj.MessageType.AnnouncementType01);
        this.f12724z.add(MessageObj.MessageType.AnnouncementType02);
        this.f12724z.add(MessageObj.MessageType.AnnouncementType03);
        this.f12724z.add(MessageObj.MessageType.Video);
        this.f12724z.add(MessageObj.MessageType.File);
        this.f12724z.add(MessageObj.MessageType.ShareLocation);
        this.f12724z.add(MessageObj.MessageType.Call);
        this.f12724z.add(MessageObj.MessageType.Poll);
        this.f12724z.add(MessageObj.MessageType.PollPost);
        this.f12724z.add(MessageObj.MessageType.ReportToAdmin);
        this.f12724z.add(MessageObj.MessageType.ReplyText);
        this.f12724z.add(MessageObj.MessageType.VideoDelete);
        this.f12724z.add(MessageObj.MessageType.FileDelete);
        this.f12724z.add(MessageObj.MessageType.ENCRYPTED_MSG);
    }

    /* renamed from: F */
    public boolean m14391F() {
        return this.f12709k;
    }

    /* renamed from: G */
    public boolean m14392G() {
        Iterator<Boolean> it = this.f12703e.values().iterator();
        boolean zBooleanValue = true;
        while (it.hasNext()) {
            zBooleanValue &= it.next().booleanValue();
        }
        return zBooleanValue;
    }

    /* renamed from: H */
    public boolean m14393H() {
        return this.f12723y;
    }

    /* renamed from: I */
    public boolean m14394I() {
        return this.f12724z.contains(m14389D());
    }

    /* renamed from: J */
    public boolean m14395J() {
        return this.f12700b == MessageObj.MessageType.NewVersion && this.f12699a.m22087M();
    }

    /* renamed from: K */
    public boolean m14396K() {
        return this.f12708j;
    }

    /* renamed from: L */
    public boolean m14397L() {
        return this.f12706h;
    }

    /* renamed from: M */
    public boolean m14398M() {
        return this.f12710l;
    }

    /* renamed from: N */
    public boolean m14399N() {
        return this.f12707i;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00c3  */
    /* renamed from: Q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m14400Q() {
        char c9;
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("announcement", "urn:xmpp:announcement:0");
            String strM14372d = c2898i.m14372d("type");
            String strM14372d2 = c2898i.m14372d("url");
            String strUnescapeXml = StringEscapeUtils.unescapeXml(c2898i.m14372d("titleOfUrl"));
            String strM14372d3 = c2898i.m14372d("template");
            String strUnescapeXml2 = StringEscapeUtils.unescapeXml(c2898i.m14372d(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
            String strM14372d4 = c2898i.m14372d(TtmlNode.ATTR_ID);
            String strM14372d5 = c2898i.m14372d("shareToFB");
            C2898i c2898i2 = (C2898i) this.f12699a.m22157g("image", "urn:xmpp:announcement:image:0");
            String strM14548D = c2898i2 != null ? C2925v.m14548D(StringEscapeUtils.unescapeXml(c2898i2.m14372d("src")), c2898i2.m14372d("width"), c2898i2.m14372d("height")) : null;
            C2898i c2898i3 = (C2898i) this.f12699a.m22157g(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, "urn:xmpp:announcement:description:0");
            this.f12701c = C2925v.m14544B(strM14372d, strM14372d4, strM14372d3, strM14372d2, strUnescapeXml, strUnescapeXml2, strM14548D, c2898i3 != null ? C2925v.m14546C(StringEscapeUtils.unescapeXml(c2898i3.m14372d(FirebaseAnalytics.Param.CONTENT))) : null, strM14372d5);
            switch (strM14372d.hashCode()) {
                case -677539554:
                    if (!strM14372d.equals("type-001")) {
                        c9 = 65535;
                        break;
                    } else {
                        c9 = 0;
                        break;
                    }
                case -677539553:
                    if (strM14372d.equals("type-002")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case -677539552:
                    if (strM14372d.equals("type-003")) {
                        c9 = 3;
                        break;
                    }
                    break;
                case -677539551:
                default:
                    c9 = 65535;
                    break;
                case -677539550:
                    if (strM14372d.equals("type-005")) {
                        c9 = 2;
                        break;
                    }
                    break;
            }
            if (c9 == 0) {
                this.f12700b = MessageObj.MessageType.AnnouncementType01;
                return;
            }
            if (c9 == 1 || c9 == 2) {
                this.f12700b = MessageObj.MessageType.AnnouncementType02;
            } else {
                if (c9 != 3) {
                    return;
                }
                this.f12700b = MessageObj.MessageType.AnnouncementType03;
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: R */
    public final void m14401R() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g(MimeTypes.BASE_TYPE_AUDIO, "U");
            c cVar = new c();
            this.f12702d = cVar;
            cVar.f12740a = Long.parseLong(c2898i.m14372d("mediaId"));
            this.f12702d.f12741b = c2898i.m14372d("mediaType");
            this.f12702d.f12743d = StringEscapeUtils.unescapeXml(c2898i.m14372d("thumbnail"));
            this.f12702d.f12744e = StringEscapeUtils.unescapeXml(c2898i.m14372d("original"));
            c cVar2 = this.f12702d;
            cVar2.f12745f = 0;
            cVar2.f12746g = 0;
            String str = cVar2.f12741b;
            if (str != null) {
                try {
                    this.f12700b = MessageObj.MessageType.valueOf(str);
                } catch (Exception unused) {
                }
                if (this.f12700b.equals(MessageObj.MessageType.Audio)) {
                    this.f12701c = C2925v.m14580X(null, String.valueOf(this.f12702d.f12740a), c2898i.m14372d("duration"));
                }
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: S */
    public final void m14402S() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("call", "urn:xmpp:call:0");
            HashMap map = new HashMap();
            map.put("callType", c2898i.m14372d("callType"));
            map.put("status", c2898i.m14372d("status"));
            map.put("statusV2", c2898i.m14372d("statusV2"));
            map.put("callId", c2898i.m14372d("callId"));
            map.put("callerId", c2898i.m14372d("callerId"));
            map.put("calleeId", c2898i.m14372d("calleeId"));
            map.put("duration", c2898i.m14372d("duration"));
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.Call;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: T */
    public final void m14403T() throws InterruptedException {
        C2898i c2898i;
        String strM14372d;
        String strM14372d2;
        try {
            c2898i = (C2898i) this.f12699a.m22157g("photoComment", "U");
        } catch (Exception e9) {
            e9.printStackTrace();
            c2898i = null;
        }
        if (c2898i == null) {
            return;
        }
        String strM14372d3 = c2898i.m14372d("commentType");
        if (strM14372d3 == null || strM14372d3.equals("null")) {
            strM14372d = "0";
            strM14372d2 = "";
            strM14372d3 = "CommentText";
        } else {
            strM14372d = c2898i.m14372d("duration");
            strM14372d2 = c2898i.m14372d("commentId");
        }
        String strM14372d4 = c2898i.m14372d("albumId");
        String strM14372d5 = c2898i.m14372d("mediaId");
        String strM14374f = c2898i.m14374f();
        this.f12703e.clear();
        this.f12703e.put("GetMediaInfo", Boolean.FALSE);
        new a(strM14372d4, strM14372d5, strM14374f, strM14372d3, strM14372d, strM14372d2).start();
        while (!m14392G()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e10) {
                Log.d("CLXMPPMessage", Log.getStackTraceString(e10));
            }
        }
        this.f12700b = MessageObj.MessageType.Comment;
    }

    /* renamed from: U */
    public final void m14404U() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("photoCommentUpdate", "U");
            String strM14372d = c2898i.m14372d("albumId");
            String strM14372d2 = c2898i.m14372d("mediaId");
            String strM14372d3 = c2898i.m14372d("commentType");
            String strM14374f = c2898i.m14374f();
            String strM14372d4 = c2898i.m14372d("commentId");
            if (strM14372d3 == null || strM14372d3.equals("null")) {
                strM14372d3 = "CommentText";
            }
            if (strM14372d4 == null || strM14372d4.equals("null")) {
                strM14372d4 = "";
            }
            HashMap map = new HashMap();
            map.put("albumId", strM14372d);
            map.put("mediaId", strM14372d2);
            map.put("comment", strM14374f);
            map.put("commentType", strM14372d3);
            map.put("commentId", strM14372d4);
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.CommentUpdate;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: V */
    public final void m14405V() throws JSONException, InterruptedException, NumberFormatException {
        if (this.f12699a.m22082H() == null || this.f12699a.m22106z() == null) {
            String strM22106z = this.f12699a.m22106z();
            this.f12701c = strM22106z;
            if (strM22106z != null) {
                this.f12700b = MessageObj.MessageType.Text;
                if (this.f12699a.m22157g("info", "U") != null) {
                    m14413d0();
                }
            } else if (this.f12699a.m22157g("announcement", "urn:xmpp:announcement:0") != null) {
                m14400Q();
            } else if (this.f12699a.m22157g("media", "U") != null) {
                m14419h0();
            } else if (this.f12699a.m22157g("sticker", "U") != null) {
                m14431n0();
            } else if (this.f12699a.m22157g("received", "urn:xmpp:receipts") != null) {
                this.f12700b = MessageObj.MessageType.DeliveryReceipt;
            } else if (this.f12699a.m22157g("event", "urn:xmpp:custom:event") != null) {
                m14410a0();
            } else if (this.f12699a.m22157g("photoComment", "U") != null) {
                m14403T();
            } else if (this.f12699a.m22157g("doodleComment", "U") != null) {
                m14408Y();
            } else if (this.f12699a.m22157g("groupAlbum", "U") != null) {
                m14414e0();
            } else if (this.f12699a.m22157g("photoDelete", "U") != null) {
                m14407X();
            } else if (this.f12699a.m22157g("photoCommentUpdate", "U") != null) {
                m14404U();
            } else if (this.f12699a.m22157g(MimeTypes.BASE_TYPE_AUDIO, "U") != null) {
                m14401R();
            } else if (this.f12699a.m22157g("userContact", "urn:xmpp:contact:0") != null) {
                m14435p0();
            } else if (this.f12699a.m22157g(MimeTypes.BASE_TYPE_VIDEO, "urn:xmpp:video:0") != null) {
                m14437q0();
            } else if (this.f12699a.m22157g(FirebaseAnalytics.Param.LOCATION, "urn:xmpp:location:0") != null) {
                m14417g0();
            } else if (this.f12699a.m22157g("file", "U") != null) {
                m14411b0();
            } else if (this.f12699a.m22157g("call", "urn:xmpp:call:0") != null) {
                m14402S();
            } else if (this.f12699a.m22157g("poll", "U") != null) {
                m14423j0();
            } else if (this.f12699a.m22157g("pollPost", "U") != null) {
                m14425k0();
            } else if (this.f12699a.m22157g("textReply", "urn:xmpp:textreply:0") != null) {
                m14427l0();
            } else if (this.f12699a.m22157g("reportToAdmin", "urn:xmpp:reportadmin:0") != null) {
                m14429m0();
            } else if (this.f12699a.m22157g("videoDelete", "U") != null) {
                m14439r0();
            } else if (this.f12699a.m22157g("fileDelete", "U") != null) {
                m14412c0();
            } else if (this.f12699a.m22157g("encrypted", "U") != null) {
                m14409Z();
            } else if (this.f12699a.m22087M()) {
                this.f12700b = MessageObj.MessageType.NewVersion;
            }
        } else {
            this.f12700b = MessageObj.MessageType.valueOf(this.f12699a.m22082H());
            this.f12701c = this.f12699a.m22106z();
        }
        if (this.f12699a.m22157g("schedule", "U") != null) {
            this.f12705g = true;
        }
        if (this.f12699a.m22157g(Constants.FirelogAnalytics.PARAM_TTL, "U") != null) {
            this.f12706h = true;
        }
        if (this.f12699a.m22157g("broadcast", "U") != null) {
            m14415f0();
        }
    }

    /* renamed from: W */
    public final void m14406W() {
        try {
            if (this.f12699a.m22157g("delay", "urn:xmpp:delay") != null) {
                this.f12719u = ((C5299a) this.f12699a.m22157g("delay", "urn:xmpp:delay")).mo20686e();
            }
        } catch (Exception unused) {
            Log.d("CLXMPPMessage", "Error msg=" + this.f12699a.toString());
        }
    }

    /* renamed from: X */
    public final void m14407X() {
        C2898i c2898i = (C2898i) this.f12699a.m22157g("photoDelete", "U");
        this.f12701c = C2925v.m14554G(c2898i.m14372d("albumId"), c2898i.m14372d("albumName"), c2898i.m14372d("numberDelete"));
        this.f12700b = MessageObj.MessageType.DeleteMedia;
    }

    /* renamed from: Y */
    public final void m14408Y() throws InterruptedException {
        C2898i c2898i;
        String str;
        String str2;
        String strM14372d;
        String strUnescapeXml;
        try {
            c2898i = (C2898i) this.f12699a.m22157g("doodleComment", "U");
        } catch (Exception e9) {
            e9.printStackTrace();
            c2898i = null;
        }
        if (c2898i == null) {
            return;
        }
        String strM14372d2 = c2898i.m14372d("commentType");
        if (strM14372d2 == null || strM14372d2.equals("null")) {
            str = "CommentText";
            str2 = "#00000000";
            strM14372d = "";
            strUnescapeXml = strM14372d;
        } else {
            String strM14372d3 = c2898i.m14372d(TtmlNode.ATTR_TTS_COLOR);
            str = strM14372d2;
            str2 = strM14372d3;
            strUnescapeXml = StringEscapeUtils.unescapeXml(c2898i.m14372d("thumbnail"));
            strM14372d = c2898i.m14372d("commentId");
        }
        String strM14372d4 = c2898i.m14372d("albumId");
        String strM14372d5 = c2898i.m14372d("mediaId");
        String strM14374f = c2898i.m14374f();
        this.f12703e.clear();
        this.f12703e.put("GetMediaInfo", Boolean.FALSE);
        new b(strM14372d4, strM14372d5, strM14374f, str, str2, strM14372d, strUnescapeXml).start();
        while (!m14392G()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e10) {
                Log.d("CLXMPPMessage", Log.getStackTraceString(e10));
            }
        }
        this.f12700b = MessageObj.MessageType.Comment;
    }

    /* renamed from: Z */
    public final void m14409Z() {
        C2898i c2898i = (C2898i) this.f12699a.m22157g("encrypted", "U");
        HashMap map = new HashMap();
        map.put(FirebaseAnalytics.Param.CONTENT, c2898i.m14374f());
        String strM14372d = c2898i.m14372d("type");
        if (strM14372d == null) {
            this.f12700b = MessageObj.MessageType.ENCRYPTED_MSG;
        } else if (!"session".equals(strM14372d)) {
            this.f12700b = MessageObj.MessageType.NewVersion;
            return;
        } else {
            this.f12700b = MessageObj.MessageType.ENCRYPTED_SESSION;
            map.put("sessionId", c2898i.m14372d("sessionId"));
        }
        this.f12701c = new JSONObject(map).toString();
    }

    /* renamed from: a0 */
    public final void m14410a0() {
        this.f12700b = MessageObj.MessageType.Event;
        this.f12701c = "";
    }

    /* renamed from: b0 */
    public final void m14411b0() throws NumberFormatException {
        C2898i c2898i = (C2898i) this.f12699a.m22157g("file", "U");
        HashMap map = new HashMap();
        m14384e(c2898i, map, "mediaType");
        m14385f(c2898i, map, "mediaName", true);
        m14384e(c2898i, map, "mediaSize");
        m14384e(c2898i, map, "mediaId");
        m14384e(c2898i, map, "albumId");
        m14384e(c2898i, map, "expirationTime");
        m14385f(c2898i, map, "original", true);
        m14384e(c2898i, map, "key");
        m14384e(c2898i, map, "auth");
        String str = (String) map.get("albumId");
        long j9 = Long.parseLong((String) map.get("mediaId"));
        if (!C5170o0.m20170e((CharSequence) map.get("key"))) {
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
            if (c2973l0M14725v == null) {
                c2973l0M14725v = (C2973l0) FriendsClient.m15657X(str, j9).first;
            }
            if (c2973l0M14725v != null) {
                c2973l0M14725v.m15148t().f13204h = (String) map.get("key");
                c2973l0M14725v.m15148t().f13205i = (String) map.get("auth");
                C2950b0.m14914m().m14701K(c2973l0M14725v);
            }
        }
        this.f12701c = new JSONObject(map).toString();
        this.f12700b = MessageObj.MessageType.File;
        try {
            c cVar = new c();
            this.f12702d = cVar;
            cVar.f12740a = Long.parseLong((String) map.get("mediaId"));
            this.f12702d.f12741b = (String) map.get("mediaType");
            this.f12702d.f12742c = (String) map.get("albumId");
            this.f12702d.f12744e = (String) map.get("original");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: c0 */
    public final void m14412c0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("fileDelete", "U");
            HashMap map = new HashMap();
            map.put("albumId", c2898i.m14372d("albumId"));
            map.put("albumName", c2898i.m14372d("albumName"));
            map.put("numberDelete", c2898i.m14372d("numberDelete"));
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.FileDelete;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: d0 */
    public final void m14413d0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("info", "U");
            this.f12720v = c2898i.m14372d("sourceId");
            this.f12722x = c2898i.m14372d("lastFwId");
            this.f12721w = c2898i.m14372d("msgFwId");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: e0 */
    public final void m14414e0() {
        C2898i c2898i = (C2898i) this.f12699a.m22157g("groupAlbum", "U");
        HashMap map = new HashMap();
        m14384e(c2898i, map, "albumId");
        m14384e(c2898i, map, "albumName");
        m14384e(c2898i, map, "mediaId");
        m14384e(c2898i, map, "mediaType");
        m14384e(c2898i, map, "width");
        m14384e(c2898i, map, "height");
        m14384e(c2898i, map, "numberUpload");
        map.put(FirebaseAnalytics.Param.CONTENT, c2898i.m14374f());
        this.f12701c = new JSONObject(map).toString();
        this.f12700b = MessageObj.MessageType.CreateMedia;
        try {
            c cVar = new c();
            this.f12702d = cVar;
            cVar.f12740a = Long.parseLong(c2898i.m14372d("mediaId"));
            this.f12702d.f12741b = c2898i.m14372d("mediaType");
            this.f12702d.f12742c = c2898i.m14372d("albumId");
            this.f12702d.f12744e = c2898i.m14374f();
            this.f12702d.f12743d = c2898i.m14374f();
            this.f12702d.f12745f = Integer.parseInt(c2898i.m14372d("width"));
            this.f12702d.f12746g = Integer.parseInt(c2898i.m14372d("height"));
            C2950b0.m14914m().m14701K(m14416g(this.f12702d.f12742c));
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: f0 */
    public final void m14415f0() {
        try {
            this.f12723y = ((C2898i) this.f12699a.m22157g("broadcast", "U")).m14374f().equals("1");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0137  */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C2973l0 m14416g(String str) {
        String string;
        String str2;
        long j9;
        String str3;
        c cVar = this.f12702d;
        if (cVar == null) {
            return null;
        }
        long j10 = cVar.f12740a;
        long time = m14386A().getTime();
        c cVar2 = this.f12702d;
        String str4 = cVar2.f12741b;
        int i9 = cVar2.f12745f;
        int i10 = cVar2.f12746g;
        int i11 = cVar2.f12747h;
        C2973l0.a aVar = new C2973l0.a();
        aVar.f13200d = this.f12702d.f12743d;
        int i12 = i11;
        aVar.f13197a = -1L;
        aVar.f13198b = null;
        aVar.f13199c = null;
        C2973l0.a aVar2 = new C2973l0.a();
        c cVar3 = this.f12702d;
        string = "";
        aVar2.f13200d = cVar3.f12744e;
        aVar2.f13197a = -1L;
        aVar2.f13198b = null;
        aVar2.f13199c = null;
        if (!C5170o0.m20170e(cVar3.f12748i)) {
            aVar2.f13203g = this.f12702d.f12748i;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f12701c);
            String string2 = jSONObject.has("mediaName") ? jSONObject.getString("mediaName") : string;
            try {
                if (jSONObject.has("mediaSize")) {
                    aVar2.f13197a = jSONObject.getLong("mediaSize");
                }
                j9 = jSONObject.has("expirationTime") ? jSONObject.getLong("expirationTime") : 0L;
                try {
                    string = jSONObject.has(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) ? jSONObject.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) : "";
                    if (jSONObject.has("commentCount")) {
                        i12 = jSONObject.getInt("commentCount");
                    }
                    if (jSONObject.has("thumbKey")) {
                        aVar.f13204h = jSONObject.getString("thumbKey");
                    }
                    if (jSONObject.has("thumbAuth")) {
                        aVar.f13205i = jSONObject.getString("thumbAuth");
                    }
                    if (jSONObject.has("key")) {
                        aVar2.f13204h = jSONObject.getString("key");
                    }
                    if (jSONObject.has("auth")) {
                        aVar2.f13205i = jSONObject.getString("auth");
                    }
                    if (jSONObject.has("duration")) {
                        aVar2.f13203g = jSONObject.getString("duration");
                    }
                    str3 = string2;
                } catch (JSONException unused) {
                    str2 = string2;
                    str3 = str2;
                    C2973l0 c2973l0 = new C2973l0(-1L, str, j10, str3, string, str4, time, (String) null, aVar, aVar2, i9, i10, 0, 0, 0, i12, 0L);
                    if (j9 > 0) {
                    }
                    return c2973l0;
                }
            } catch (JSONException unused2) {
                str2 = string2;
                j9 = 0;
                str3 = str2;
                C2973l0 c2973l02 = new C2973l0(-1L, str, j10, str3, string, str4, time, (String) null, aVar, aVar2, i9, i10, 0, 0, 0, i12, 0L);
                if (j9 > 0) {
                }
                return c2973l02;
            }
        } catch (JSONException unused3) {
            str2 = string;
        }
        C2973l0 c2973l022 = new C2973l0(-1L, str, j10, str3, string, str4, time, (String) null, aVar, aVar2, i9, i10, 0, 0, 0, i12, 0L);
        if (j9 > 0) {
            c2973l022.m15120G(j9);
        }
        return c2973l022;
    }

    /* renamed from: g0 */
    public final void m14417g0() {
        C2898i c2898i = (C2898i) this.f12699a.m22157g(FirebaseAnalytics.Param.LOCATION, "urn:xmpp:location:0");
        HashMap map = new HashMap();
        m14384e(c2898i, map, AppMeasurementSdk.ConditionalUserProperty.NAME);
        m14384e(c2898i, map, "address");
        m14384e(c2898i, map, "latitude");
        m14384e(c2898i, map, "longitude");
        m14384e(c2898i, map, "snapshotUrl");
        m14384e(c2898i, map, "snapshotWidth");
        m14384e(c2898i, map, "snapshotHeight");
        m14384e(c2898i, map, "snapshotMediaId");
        this.f12701c = new JSONObject(map).toString();
        this.f12700b = MessageObj.MessageType.ShareLocation;
    }

    /* renamed from: h */
    public String m14418h() {
        Group groupM15077n;
        if (this.f12700b == MessageObj.MessageType.Event) {
            C2898i c2898i = (C2898i) m14426l("event", "urn:xmpp:custom:event");
            if (c2898i == null) {
                return "";
            }
            Map<String, String> mapM14373e = c2898i.m14373e();
            if (mapM14373e.get("groupId") != null && (groupM15077n = C2950b0.m14912k().m15077n(mapM14373e.get("groupId"))) != null) {
                return groupM15077n.f13723j;
            }
        } else if (this.f12710l) {
            return C5616j.m22345j(m14388C());
        }
        return C5616j.m22345j(m14428m());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x017f A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0188 A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0195 A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01a2 A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01af A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01bc A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01cb A[Catch: JSONException -> 0x022d, TryCatch #4 {JSONException -> 0x022d, blocks: (B:44:0x0172, B:46:0x017f, B:47:0x0182, B:49:0x0188, B:50:0x018b, B:52:0x0195, B:53:0x0198, B:55:0x01a2, B:56:0x01a5, B:58:0x01af, B:59:0x01b2, B:61:0x01bc, B:62:0x01bf, B:64:0x01cb, B:66:0x01d9, B:68:0x01e9, B:70:0x0223), top: B:84:0x0172 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0221  */
    /* renamed from: h0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m14419h0() throws JSONException {
        String str;
        boolean z8;
        String strM14372d;
        String str2;
        String str3;
        JSONObject jSONObject;
        int i9;
        int i10;
        String strM14372d2;
        String strM14372d3;
        String strM14372d4;
        String strM14372d5;
        String strM14372d6;
        String str4 = "";
        C2898i c2898i = (C2898i) this.f12699a.m22157g("media", "U");
        boolean z9 = false;
        try {
            c cVar = new c();
            this.f12702d = cVar;
            cVar.f12740a = Long.parseLong(c2898i.m14372d("mediaId"));
            this.f12702d.f12741b = c2898i.m14372d("mediaType");
            this.f12702d.f12742c = c2898i.m14372d("albumId");
            this.f12702d.f12743d = StringEscapeUtils.unescapeXml(c2898i.m14372d("thumbnail"));
            this.f12702d.f12744e = StringEscapeUtils.unescapeXml(c2898i.m14372d("original"));
            this.f12702d.f12745f = Integer.parseInt(c2898i.m14372d("width"));
            this.f12702d.f12746g = Integer.parseInt(c2898i.m14372d("height"));
            String strM14372d7 = c2898i.m14372d("commentCount");
            if (!C5170o0.m20170e(strM14372d7)) {
                this.f12702d.f12747h = Integer.parseInt(strM14372d7);
            }
        } catch (Exception e9) {
            c cVar2 = this.f12702d;
            if (cVar2 != null) {
                cVar2.f12745f = 0;
                cVar2.f12746g = 0;
            }
            e9.printStackTrace();
        }
        try {
            String strUnescapeXml = StringEscapeUtils.unescapeXml(c2898i.m14372d(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
            boolean zM20170e = C5170o0.m20170e(strUnescapeXml);
            if (zM20170e) {
                strUnescapeXml = "";
            }
            z8 = !zM20170e;
            str = strUnescapeXml;
        } catch (Exception e10) {
            e10.printStackTrace();
            str = "";
            z8 = false;
        }
        try {
            strM14372d = c2898i.m14372d("noteMediaType");
            if (C5170o0.m20170e(strM14372d)) {
                strM14372d6 = "";
            } else {
                try {
                    strM14372d6 = c2898i.m14372d("noteMediaDescription");
                    z9 = true;
                    str4 = strM14372d;
                } catch (Exception e11) {
                    e = e11;
                    e.printStackTrace();
                    str2 = "";
                    str3 = this.f12702d.f12741b;
                    if (str3 != null) {
                    }
                    jSONObject = new JSONObject(this.f12701c);
                    i9 = this.f12702d.f12745f;
                    if (i9 > 0) {
                    }
                    i10 = this.f12702d.f12746g;
                    if (i10 > 0) {
                    }
                    strM14372d2 = c2898i.m14372d("key");
                    if (!C5170o0.m20170e(strM14372d2)) {
                    }
                    strM14372d3 = c2898i.m14372d("auth");
                    if (!C5170o0.m20170e(strM14372d3)) {
                    }
                    strM14372d4 = c2898i.m14372d("thumbKey");
                    if (!C5170o0.m20170e(strM14372d4)) {
                    }
                    strM14372d5 = c2898i.m14372d("thumbAuth");
                    if (!C5170o0.m20170e(strM14372d5)) {
                    }
                    this.f12701c = jSONObject.toString();
                    if (jSONObject.has("key")) {
                    }
                }
            }
            str2 = strM14372d6;
            strM14372d = str4;
        } catch (Exception e12) {
            e = e12;
            strM14372d = "";
        }
        str3 = this.f12702d.f12741b;
        if (str3 != null) {
            if (z8 || z9) {
                String strM14372d8 = c2898i.m14372d("commentCount");
                this.f12700b = MessageObj.MessageType.PhotoNote;
                String strValueOf = String.valueOf(this.f12702d.f12740a);
                c cVar3 = this.f12702d;
                this.f12701c = C2925v.m14575S(strValueOf, str, strM14372d, str2, strM14372d8, null, null, cVar3.f12744e, null, cVar3.f12745f, cVar3.f12746g);
            } else {
                try {
                    this.f12700b = MessageObj.MessageType.valueOf(str3);
                } catch (Exception unused) {
                }
                if (this.f12700b.equals(MessageObj.MessageType.Photo)) {
                    c cVar4 = this.f12702d;
                    String str5 = cVar4.f12744e;
                    String strValueOf2 = String.valueOf(cVar4.f12740a);
                    c cVar5 = this.f12702d;
                    this.f12701c = C2925v.m14570O("", null, str5, null, strValueOf2, null, null, cVar5.f12745f, cVar5.f12746g);
                } else if (this.f12700b.equals(MessageObj.MessageType.Video)) {
                    this.f12701c = C2925v.m14579W("", null, String.valueOf(this.f12702d.f12740a), c2898i.m14372d("duration"), "", "", "false", "0", "0");
                }
            }
        }
        try {
            jSONObject = new JSONObject(this.f12701c);
            i9 = this.f12702d.f12745f;
            if (i9 > 0) {
                jSONObject.put("width", i9);
            }
            i10 = this.f12702d.f12746g;
            if (i10 > 0) {
                jSONObject.put("height", i10);
            }
            strM14372d2 = c2898i.m14372d("key");
            if (!C5170o0.m20170e(strM14372d2)) {
                jSONObject.put("key", strM14372d2);
            }
            strM14372d3 = c2898i.m14372d("auth");
            if (!C5170o0.m20170e(strM14372d3)) {
                jSONObject.put("auth", strM14372d3);
            }
            strM14372d4 = c2898i.m14372d("thumbKey");
            if (!C5170o0.m20170e(strM14372d4)) {
                jSONObject.put("thumbKey", strM14372d4);
            }
            strM14372d5 = c2898i.m14372d("thumbAuth");
            if (!C5170o0.m20170e(strM14372d5)) {
                jSONObject.put("thumbAuth", strM14372d5);
            }
            this.f12701c = jSONObject.toString();
            if (jSONObject.has("key")) {
                if (z9) {
                    c cVar6 = this.f12702d;
                    FriendsClient.m15657X(cVar6.f12742c, cVar6.f12740a);
                    return;
                }
                return;
            }
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(this.f12702d.f12740a);
            if (c2973l0M14725v == null) {
                c cVar7 = this.f12702d;
                c2973l0M14725v = (C2973l0) FriendsClient.m15657X(cVar7.f12742c, cVar7.f12740a).first;
            }
            if (c2973l0M14725v != null) {
                c2973l0M14725v.m15148t().f13204h = jSONObject.optString("key", null);
                c2973l0M14725v.m15148t().f13205i = jSONObject.optString("auth", null);
                c2973l0M14725v.m15149u().f13204h = jSONObject.optString("thumbKey", null);
                c2973l0M14725v.m15149u().f13205i = jSONObject.optString("thumbAuth", null);
                c2973l0M14725v.m15123J(this.f12702d.f12747h);
                C2950b0.m14914m().m14701K(c2973l0M14725v);
            }
        } catch (JSONException e13) {
            C5154j.m20076j(e13);
        }
    }

    /* renamed from: i */
    public String m14420i() {
        return this.f12701c;
    }

    /* renamed from: i0 */
    public final void m14421i0() {
        Message message;
        CarbonExtension carbonExtension = (CarbonExtension) this.f12699a.m22157g("sent", "urn:xmpp:carbons:2");
        if (carbonExtension != null && (message = (Message) carbonExtension.m22435c().m21085d()) != null) {
            this.f12709k = true;
            this.f12699a = message;
        }
        C2898i c2898i = (C2898i) this.f12699a.m22157g("result", "urn:xmpp:mam:tmp");
        C2898i c2898i2 = (C2898i) this.f12699a.m22157g("result", "urn:xmpp:mam:0");
        C2898i c2898i3 = (C2898i) this.f12699a.m22157g("result", "urn:xmpp:mam:3");
        if (c2898i == null) {
            c2898i = c2898i2 != null ? c2898i2 : c2898i3 != null ? c2898i3 : null;
        }
        if (c2898i == null) {
            m14406W();
            return;
        }
        this.f12716r = c2898i.m14372d("queryid");
        C5375a c5375a = (C5375a) this.f12699a.m22157g("forwarded", "urn:xmpp:forward:0");
        if (c5375a != null) {
            Message message2 = (Message) c5375a.m21085d();
            this.f12699a = message2;
            if (message2 != null) {
                this.f12711m = message2.m22162l();
                this.f12712n = this.f12699a.m22160j();
                this.f12713o = this.f12699a.m22086L().toString();
                this.f12714p = this.f12699a.m22161k();
            }
            C5299a c5299aM21084c = c5375a.m21084c();
            if (c5299aM21084c != null) {
                this.f12719u = c5299aM21084c.mo20686e();
            }
        }
        this.f12707i = true;
    }

    /* renamed from: j */
    public Date m14422j() {
        return this.f12719u;
    }

    /* renamed from: j0 */
    public final void m14423j0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("poll", "U");
            String strM14372d = c2898i.m14372d("topicId");
            String strM14374f = c2898i.m14374f();
            HashMap map = new HashMap();
            map.put("topicId", strM14372d);
            map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, strM14374f);
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.Poll;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: k */
    public XMPPError m14424k() {
        return this.f12699a.m22156e();
    }

    /* renamed from: k0 */
    public final void m14425k0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("pollPost", "U");
            String strM14372d = c2898i.m14372d("topicId");
            String strM14372d2 = c2898i.m14372d("postId");
            String strM14372d3 = c2898i.m14372d("topicDesc");
            String strM14374f = c2898i.m14374f();
            HashMap map = new HashMap();
            map.put("topicId", strM14372d);
            map.put("postId", strM14372d2);
            map.put("topicDesc", strM14372d3);
            map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, strM14374f);
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.PollPost;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: l */
    public InterfaceC5595c m14426l(String str, String str2) {
        return this.f12699a.m22157g(str, str2);
    }

    /* renamed from: l0 */
    public final void m14427l0() throws JSONException {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("textReply", "urn:xmpp:textreply:0");
            String strM14372d = c2898i.m14372d("sourceId");
            String strM14372d2 = c2898i.m14372d("sourceSender");
            String strM14372d3 = c2898i.m14372d("sourceType");
            JSONObject jSONObject = new JSONObject(c2898i.m14374f());
            String string = jSONObject.getString("source");
            String string2 = jSONObject.getString(TtmlNode.TAG_BODY);
            HashMap map = new HashMap();
            map.put("replyMessageId", strM14372d);
            map.put("replySender", strM14372d2);
            map.put("replyMessage", string);
            map.put("replyText", string2);
            map.put("sourceType", strM14372d3);
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.ReplyText;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: m */
    public String m14428m() {
        return m14399N() ? this.f12712n : m14398M() ? this.f12715q : this.f12699a.m22160j();
    }

    /* renamed from: m0 */
    public final void m14429m0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("reportToAdmin", "urn:xmpp:reportadmin:0");
            String strM14372d = c2898i.m14372d("reportType");
            String strM14372d2 = c2898i.m14372d("reporterId");
            String strM14372d3 = c2898i.m14372d("topicId");
            String strM14372d4 = c2898i.m14372d("groupId");
            String strM14374f = c2898i.m14374f();
            HashMap map = new HashMap();
            map.put("reportType", strM14372d);
            map.put("reporterId", strM14372d2);
            map.put("topicId", strM14372d3);
            map.put("groupId", strM14372d4);
            map.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, strM14374f);
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.ReportToAdmin;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: n */
    public String m14430n() {
        String strM14428m = m14428m();
        if (strM14428m == null) {
            return null;
        }
        if (!strM14428m.contains("@conference.u.cyberlink.com") || m14418h().equals(Globals.m7388i0().m7587o0())) {
            return strM14428m.split("@")[0];
        }
        String[] strArrSplit = strM14428m.split("/");
        if (strArrSplit.length >= 2) {
            return strArrSplit[1];
        }
        return null;
    }

    /* renamed from: n0 */
    public final void m14431n0() throws InterruptedException {
        C2898i c2898i;
        try {
            c2898i = (C2898i) this.f12699a.m22157g("sticker", "U");
        } catch (Exception e9) {
            e9.printStackTrace();
            c2898i = null;
        }
        if (c2898i == null) {
            return;
        }
        this.f12700b = MessageObj.MessageType.Sticker;
        String strM14372d = c2898i.m14372d("stickerId");
        String strM14372d2 = c2898i.m14372d("packId");
        String strM14372d3 = c2898i.m14372d("width");
        String strM14372d4 = c2898i.m14372d("height");
        this.f12703e.clear();
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(NumberUtils.toLong(strM14372d2, -1L));
        if (stickerPackObjM15293k != null) {
            this.f12700b = CLUtility.m16607y(stickerPackObjM15293k.m14805i());
        } else {
            this.f12703e.put("GetPackType", Boolean.FALSE);
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("packId", strM14372d2));
            final FriendsClient friendsClient = new FriendsClient();
            friendsClient.m15734m("sticker", "pack.info", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.j
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f12693a.m14378O(friendsClient, str, str2, str3, str4);
                }
            });
        }
        if (C2950b0.m14924w().m15278f(Long.parseLong(strM14372d)) == null) {
            this.f12703e.put("ParseSticker", Boolean.FALSE);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new C6301o("token", strM7449L));
            arrayList2.add(new C6301o("stickerId", strM14372d));
            final FriendsClient friendsClient2 = new FriendsClient();
            friendsClient2.m15734m("sticker", "sticker.info", arrayList2, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.k
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                    this.f12696a.m14379P(friendsClient2, str, str2, str3, str4);
                }
            });
        }
        while (!m14392G()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e10) {
                Log.d("CLXMPPMessage", Log.getStackTraceString(e10));
            }
        }
        StickerObj stickerObjM15278f = C2950b0.m14924w().m15278f(Long.parseLong(strM14372d));
        if (stickerObjM15278f != null) {
            try {
                stickerObjM15278f.m16295t(Integer.parseInt(strM14372d3));
                stickerObjM15278f.m16292q(Integer.parseInt(strM14372d4));
                C2950b0.m14924w().m15281i(stickerObjM15278f.m16285j(), stickerObjM15278f);
            } catch (Exception e11) {
                e11.printStackTrace();
            }
        }
        if (MessageObj.MessageType.StickerTypeUnknown.equals(this.f12700b)) {
            this.f12701c = C2925v.m14577U(strM14372d2, strM14372d, strM14372d3, strM14372d4);
        } else {
            this.f12701c = strM14372d;
        }
    }

    /* renamed from: o */
    public Group m14432o() {
        return this.f12704f;
    }

    /* renamed from: o0 */
    public final void m14433o0(String str) throws JSONException {
        try {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                ArrayList arrayList = new ArrayList();
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i9);
                        try {
                            long j9 = jSONObject.getLong("stickerId");
                            long j10 = jSONObject.getLong("packId");
                            StickerObj stickerObj = new StickerObj(-1L, j9, j10, jSONObject.getLong("stickerOrder"), jSONObject.getLong("lastModified"), jSONObject.getString("originalURL"), jSONObject.getString("thumbnailURL"), 0, 0);
                            StringBuilder sb = new StringBuilder();
                            sb.append(CLUtility.m16541h1(j10));
                            String str2 = File.separator;
                            sb.append(str2);
                            sb.append(j9);
                            sb.append("_");
                            sb.append("thumbnail");
                            stickerObj.m16294s(sb.toString());
                            stickerObj.m16293r(CLUtility.m16541h1(j10) + str2 + j9);
                            arrayList.add(stickerObj);
                        } catch (JSONException unused) {
                            Log.e("CLXMPPMessage", "[sticker.sticker.list] Parse item error. JSONstr=" + jSONObject.toString());
                        }
                    } catch (JSONException unused2) {
                        Log.e("CLXMPPMessage", "[sticker.sticker.info] stickerInfo parse error. JSONstr=" + str);
                    }
                }
                C2950b0.m14924w().m15277e(arrayList);
            } catch (JSONException unused3) {
                Log.e("CLXMPPMessage", "[sticker.sticker.info] 'results' missing. JSONstr=" + str);
            }
        } catch (JSONException unused4) {
            Log.e("CLXMPPMessage", "[sticker.sticker.info] Parse error. JSONstr=" + str);
        }
    }

    /* renamed from: p */
    public String m14434p() {
        return this.f12722x;
    }

    /* renamed from: p0 */
    public final void m14435p0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("userContact", "urn:xmpp:contact:0");
            this.f12701c = C2925v.m14578V(c2898i.m14372d("userId"), c2898i.m14372d("displayName"));
            this.f12700b = MessageObj.MessageType.UserContact;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: q */
    public c m14436q() {
        return this.f12702d;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* renamed from: q0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m14437q0() {
        C2898i c2898i;
        Exception e9;
        String str;
        try {
            c2898i = (C2898i) this.f12699a.m22157g(MimeTypes.BASE_TYPE_VIDEO, "urn:xmpp:video:0");
        } catch (Exception e10) {
            c2898i = null;
            e9 = e10;
        }
        try {
            c cVar = new c();
            this.f12702d = cVar;
            cVar.f12740a = Long.parseLong(c2898i.m14372d("mediaId"));
            this.f12702d.f12741b = c2898i.m14372d("mediaType");
            this.f12702d.f12743d = StringEscapeUtils.unescapeXml(c2898i.m14372d("thumbnail"));
            this.f12702d.f12744e = StringEscapeUtils.unescapeXml(c2898i.m14372d("original"));
            this.f12702d.f12745f = Integer.parseInt(c2898i.m14372d("width"));
            this.f12702d.f12746g = Integer.parseInt(c2898i.m14372d("height"));
            String strM14372d = c2898i.m14372d("duration");
            if (!C5170o0.m20170e(strM14372d)) {
                this.f12702d.f12748i = strM14372d;
            }
        } catch (Exception e11) {
            e9 = e11;
            c cVar2 = this.f12702d;
            cVar2.f12745f = 0;
            cVar2.f12746g = 0;
            e9.printStackTrace();
            if (c2898i == null) {
                return;
            }
            try {
                this.f12700b = MessageObj.MessageType.valueOf(str);
            } catch (Exception unused) {
            }
            if (this.f12700b.equals(MessageObj.MessageType.Video)) {
            }
        }
        if (c2898i == null && (str = this.f12702d.f12741b) != null) {
            this.f12700b = MessageObj.MessageType.valueOf(str);
            if (this.f12700b.equals(MessageObj.MessageType.Video)) {
                return;
            }
            String strM14372d2 = c2898i.m14372d("duration");
            String strValueOf = String.valueOf(this.f12702d.f12740a);
            c cVar3 = this.f12702d;
            this.f12701c = C2925v.m14579W("", null, strValueOf, strM14372d2, cVar3.f12743d, "", "false", String.valueOf(cVar3.f12745f), String.valueOf(this.f12702d.f12746g));
        }
    }

    /* renamed from: r */
    public XMPPManager.HandleType m14438r() {
        return this.f12717s;
    }

    /* renamed from: r0 */
    public final void m14439r0() {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("videoDelete", "U");
            HashMap map = new HashMap();
            map.put("albumId", c2898i.m14372d("albumId"));
            map.put("albumName", c2898i.m14372d("albumName"));
            map.put("numberDelete", c2898i.m14372d("numberDelete"));
            this.f12701c = new JSONObject(map).toString();
            this.f12700b = MessageObj.MessageType.VideoDelete;
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: s */
    public String m14440s() {
        return this.f12721w;
    }

    /* renamed from: s0 */
    public void m14441s0(Date date) {
        this.f12719u = date;
    }

    /* renamed from: t */
    public String m14442t() {
        return this.f12720v;
    }

    /* renamed from: t0 */
    public void m14443t0(Group group) {
        this.f12704f = group;
    }

    public String toString() {
        return this.f12699a.toString();
    }

    /* renamed from: u */
    public final MessageObj.MessageType m14444u(String str) {
        try {
            try {
                List<StickerPackObj> listM20177D = C5172p.m20177D(new JSONObject(str).getJSONArray("results"), false, false);
                if (listM20177D == null || listM20177D.size() <= 0) {
                    return MessageObj.MessageType.Sticker;
                }
                C2950b0.m14925x().m15289g(listM20177D);
                return CLUtility.m16607y(listM20177D.get(0).m14805i());
            } catch (JSONException unused) {
                Log.e("CLXMPPMessage", "[sticker.pack.info] 'results' missing. JSONstr=" + str);
                return MessageObj.MessageType.Sticker;
            }
        } catch (JSONException unused2) {
            Log.e("CLXMPPMessage", "[sticker.sticker.info] Parse error. JSONstr=" + str);
            return MessageObj.MessageType.Sticker;
        }
    }

    /* renamed from: u0 */
    public void m14445u0(String str) {
        if (m14399N()) {
            this.f12714p = str;
        } else {
            this.f12699a.m22166s(str);
        }
    }

    /* renamed from: v */
    public String m14446v() {
        return m14399N() ? this.f12714p : this.f12699a.m22161k();
    }

    /* renamed from: v0 */
    public void m14447v0(String str) {
        this.f12710l = true;
        this.f12715q = str;
    }

    /* renamed from: w */
    public Message.Type m14448w() {
        if (m14399N()) {
            String str = this.f12713o;
            Message.Type type = Message.Type.chat;
            if (str.equals(type.toString())) {
                return type;
            }
            String str2 = this.f12713o;
            Message.Type type2 = Message.Type.groupchat;
            if (str2.equals(type2.toString())) {
                return type2;
            }
        }
        return this.f12699a.m22086L();
    }

    /* renamed from: w0 */
    public MessageObj m14449w0(String str) {
        int iM14387B;
        if (!m14394I() && m14389D() != MessageObj.MessageType.Event) {
            return null;
        }
        String strM14446v = m14446v();
        Date dateM14422j = m14422j();
        MessageObj.MessageType messageTypeM14389D = m14389D();
        String strM14420i = m14420i();
        String strM14430n = m14430n();
        Friend friendM15003C = C2950b0.m14899A().m15003C(strM14430n);
        String strM15620a = friendM15003C == null ? "" : friendM15003C.m15620a();
        String str2 = C2925v.m14592e0(messageTypeM14389D) ? "3" : "0";
        MessageObj.TTLStatus tTLStatus = MessageObj.TTLStatus.NO_TTL;
        MessageObj.MemberStatus memberStatus = MessageObj.MemberStatus.NO_MemberStatus;
        if (this.f12706h) {
            tTLStatus = MessageObj.TTLStatus.NOT_START;
            iM14387B = m14387B();
        } else {
            iM14387B = 0;
        }
        return new MessageObj(-1L, strM14446v, str, dateM14422j.getTime(), messageTypeM14389D, strM14420i, 0, strM14430n, strM15620a, "0", tTLStatus, 0L, iM14387B, this.f12705g ? m14451y() : 0L, memberStatus, m14395J(), m14452z(), str2, m14442t(), m14440s(), m14434p(), m14393H(), 0);
    }

    /* renamed from: x */
    public String m14450x() {
        return this.f12716r;
    }

    /* renamed from: y */
    public long m14451y() throws ParseException {
        try {
            C2898i c2898i = (C2898i) this.f12699a.m22157g("schedule", "U");
            if (c2898i == null) {
                return 0L;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = simpleDateFormat.parse(c2898i.m14374f());
            if (date != null) {
                return date.getTime();
            }
            return 0L;
        } catch (Exception e9) {
            e9.printStackTrace();
            return 0L;
        }
    }

    /* renamed from: z */
    public String m14452z() {
        return this.f12699a.m22081G();
    }
}
