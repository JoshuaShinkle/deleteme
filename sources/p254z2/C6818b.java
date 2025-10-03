package p254z2;

import android.os.AsyncTask;
import android.util.Pair;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;
import p116k4.C5187v0;
import p182r2.C6196d0;
import p182r2.C6201i;
import p201t3.C6301o;
import p209u2.C6369f;
import p209u2.C6385v;

/* renamed from: z2.b */
/* loaded from: classes.dex */
public class C6818b {

    /* renamed from: a */
    public static final String f22579a = "b";

    /* renamed from: b */
    public static FriendsClient f22580b;

    /* renamed from: z2.b$a */
    public interface a {
        /* renamed from: a */
        void mo11883a();

        void onSuccess();
    }

    /* renamed from: z2.b$b */
    public static class b extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public Group f22581a;

        /* renamed from: b */
        public boolean f22582b;

        /* renamed from: c */
        public List<String> f22583c;

        /* renamed from: d */
        public a f22584d;

        public b(Group group, boolean z8, List<String> list, a aVar) {
            this.f22581a = group;
            this.f22582b = z8;
            this.f22583c = list;
            this.f22584d = aVar;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            ArrayList<String> arrayListM25407b = m25407b(this.f22581a);
            if (arrayListM25407b == null || arrayListM25407b.isEmpty()) {
                return Boolean.FALSE;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = arrayListM25407b.iterator();
            while (it.hasNext()) {
                arrayList.add(new C6201i(this.f22581a.f13723j, C2950b0.m14916o().m15179r(it.next())));
            }
            C6196d0.m23692d().m23699i(arrayList);
            return Boolean.TRUE;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(6:126|(6:(7:(2:128|129)(3:131|132|(17:276|134|135|260|139|140|266|141|142|252|143|278|144|145|(1:155)(3:151|152|(2:284|154))|157|175)(1:138))|278|144|145|(2:147|155)(1:156)|157|175)|266|141|142|252|143)|130|260|139|140) */
        /* JADX WARN: Code restructure failed: missing block: B:164:0x04d5, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:165:0x04d6, code lost:
        
            r25 = r13;
         */
        /* JADX WARN: Removed duplicated region for block: B:102:0x0371 A[Catch: Exception -> 0x0633, TryCatch #6 {Exception -> 0x0633, blocks: (B:88:0x031e, B:90:0x0326, B:92:0x032c, B:94:0x0337, B:96:0x0356, B:97:0x035c, B:99:0x0366, B:102:0x0371, B:104:0x0379, B:105:0x0380, B:107:0x0388, B:108:0x038f, B:112:0x0399, B:115:0x03b6, B:118:0x03c0, B:77:0x02dd, B:80:0x02eb, B:83:0x02f5), top: B:264:0x031e }] */
        /* JADX WARN: Removed duplicated region for block: B:169:0x04e3 A[Catch: Exception -> 0x0623, TryCatch #16 {Exception -> 0x0623, blocks: (B:154:0x049d, B:157:0x04ab, B:169:0x04e3, B:171:0x04ef, B:174:0x04f5, B:177:0x051f), top: B:284:0x049d }] */
        /* JADX WARN: Removed duplicated region for block: B:206:0x05ca A[Catch: Exception -> 0x0602, TRY_ENTER, TryCatch #18 {Exception -> 0x0602, blocks: (B:197:0x059c, B:206:0x05ca, B:211:0x05d7, B:210:0x05d4), top: B:288:0x059c }] */
        /* JADX WARN: Removed duplicated region for block: B:220:0x0607 A[Catch: Exception -> 0x061f, TryCatch #8 {Exception -> 0x061f, blocks: (B:222:0x0610, B:213:0x05e5, B:216:0x05ef, B:217:0x05f4, B:220:0x0607), top: B:268:0x0610 }] */
        /* JADX WARN: Removed duplicated region for block: B:228:0x061c  */
        /* JADX WARN: Removed duplicated region for block: B:264:0x031e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:268:0x0610 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:288:0x059c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x0366 A[Catch: Exception -> 0x0633, TryCatch #6 {Exception -> 0x0633, blocks: (B:88:0x031e, B:90:0x0326, B:92:0x032c, B:94:0x0337, B:96:0x0356, B:97:0x035c, B:99:0x0366, B:102:0x0371, B:104:0x0379, B:105:0x0380, B:107:0x0388, B:108:0x038f, B:112:0x0399, B:115:0x03b6, B:118:0x03c0, B:77:0x02dd, B:80:0x02eb, B:83:0x02f5), top: B:264:0x031e }] */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final ArrayList<String> m25407b(Group group) throws JSONException {
            String str;
            ArrayList<String> arrayList;
            ArrayList<MessageObj> arrayList2;
            String str2;
            JSONObject jSONObject;
            JSONObject jSONObject2;
            String str3;
            ArrayList<String> arrayList3;
            String str4;
            C2973l0 c2973l0M15129a;
            MessageObj.MessageType messageType;
            String str5;
            String strM14747I;
            String strM14747I2;
            String strM14747I3;
            String str6;
            long j9;
            String str7;
            String strM14550E;
            C3061a c3061aM25399j;
            String strM14570O;
            String strM14579W;
            String strM14747I4;
            MessageObj messageObjM25401l;
            if (group == null) {
                ULogUtility.m16670f(C6818b.f22579a, "[multipleMessageCopy] group is null");
                return null;
            }
            List<String> list = this.f22583c;
            if (list == null || list.size() == 0) {
                ULogUtility.m16670f(C6818b.f22579a, "[multipleMessageCopy] get sharedMediaMsgIdList null or zero");
                return null;
            }
            FriendsClient unused = C6818b.f22580b = new FriendsClient();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList<String> arrayList7 = new ArrayList<>();
            Iterator<String> it = this.f22583c.iterator();
            while (true) {
                str = "mediaId";
                arrayList = arrayList7;
                if (!it.hasNext()) {
                    break;
                }
                MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(it.next());
                if (messageObjM15179r != null) {
                    MessageObj.MessageType messageTypeM14778p = messageObjM15179r.m14778p();
                    if (Arrays.asList(MessageObj.MessageType.Photo, MessageObj.MessageType.Comment, MessageObj.MessageType.PhotoNote, MessageObj.MessageType.CommentUpdate, MessageObj.MessageType.Video, MessageObj.MessageType.File).contains(messageTypeM14778p)) {
                        long j10 = NumberUtils.toLong(messageObjM15179r.m14747I("mediaId"), -1L);
                        if (j10 != -1) {
                            arrayList4.add(Long.valueOf(j10));
                        }
                    } else if (messageTypeM14778p.equals(MessageObj.MessageType.Audio)) {
                        long j11 = NumberUtils.toLong(messageObjM15179r.m14747I("mediaId"), -1L);
                        if (j11 != -1) {
                            arrayList5.add(Long.valueOf(j11));
                        }
                    }
                    arrayList6.add(messageObjM15179r);
                }
                arrayList7 = arrayList;
            }
            String str8 = "albumId";
            if (arrayList5.size() > 0) {
                ArrayList arrayList8 = new ArrayList();
                str2 = "Doodle";
                arrayList2 = arrayList6;
                arrayList8.add(new C6301o("token", Globals.m7388i0().m7449L()));
                int i9 = 0;
                while (i9 < arrayList5.size()) {
                    arrayList8.add(new C6301o("mediaId", String.valueOf(arrayList5.get(i9))));
                    i9++;
                    arrayList5 = arrayList5;
                }
                arrayList8.add(new C6301o("albumId", group.f13720g));
                arrayList8.add(new C6301o("includeComments", String.valueOf(this.f22582b)));
                arrayList8.add(new C6301o("includeDescription", String.valueOf(this.f22582b)));
                arrayList8.add(new C6301o("includeMediaNotes", String.valueOf(this.f22582b)));
                Pair<String, String> pairM15731j = C6818b.f22580b.m15731j("media", "copyMedia", arrayList8);
                String str9 = (String) pairM15731j.first;
                String str10 = (String) pairM15731j.second;
                if (!"200".equals(str9)) {
                    C5187v0.m20267c(R.string.error_server_response);
                    ULogUtility.m16670f(C6818b.f22579a, "[media.copyMedia - Voice] error code: " + str9);
                    return null;
                }
                try {
                    jSONObject = new JSONObject(str10);
                } catch (JSONException unused2) {
                    C5187v0.m20267c(R.string.error_server_response);
                    ULogUtility.m16670f(C6818b.f22579a, "[media.copyMedia - Voice] parse error jsonStr = " + str10);
                    return null;
                }
            } else {
                arrayList2 = arrayList6;
                str2 = "Doodle";
                jSONObject = null;
            }
            if (arrayList4.size() > 0) {
                ArrayList arrayList9 = new ArrayList();
                arrayList9.add(new C6301o("token", Globals.m7388i0().m7449L()));
                for (int i10 = 0; i10 < arrayList4.size(); i10++) {
                    arrayList9.add(new C6301o("mediaId", String.valueOf(arrayList4.get(i10))));
                }
                arrayList9.add(new C6301o("albumId", group.f13718e));
                arrayList9.add(new C6301o("includeComments", String.valueOf(this.f22582b)));
                arrayList9.add(new C6301o("includeDescription", String.valueOf(this.f22582b)));
                arrayList9.add(new C6301o("includeMediaNotes", String.valueOf(this.f22582b)));
                Pair<String, String> pairM15731j2 = C6818b.f22580b.m15731j("media", "copyMedia", arrayList9);
                String str11 = (String) pairM15731j2.first;
                String str12 = (String) pairM15731j2.second;
                if (!"200".equals(str11)) {
                    C5187v0.m20267c(R.string.error_server_response);
                    ULogUtility.m16670f(C6818b.f22579a, "[media.copyMedia - Non Voice] error code: " + str11);
                    return null;
                }
                try {
                    jSONObject2 = new JSONObject(str12);
                } catch (JSONException unused3) {
                    C5187v0.m20267c(R.string.error_server_response);
                    ULogUtility.m16670f(C6818b.f22579a, "[media.copyMedia - Non Voice] parse error jsonStr = " + str12);
                    return null;
                }
            } else {
                jSONObject2 = null;
            }
            for (MessageObj messageObj : arrayList2) {
                Object objM14778p = messageObj.m14778p();
                try {
                    MessageObj.MessageType messageType2 = MessageObj.MessageType.Audio;
                    if (!objM14778p.equals(messageType2)) {
                        MessageObj.MessageType[] messageTypeArr = new MessageObj.MessageType[6];
                        messageTypeArr[0] = MessageObj.MessageType.Photo;
                        messageTypeArr[1] = MessageObj.MessageType.Comment;
                        try {
                            messageTypeArr[2] = MessageObj.MessageType.PhotoNote;
                            messageTypeArr[3] = MessageObj.MessageType.CommentUpdate;
                            messageTypeArr[4] = MessageObj.MessageType.Video;
                            messageTypeArr[5] = MessageObj.MessageType.File;
                            if (!Arrays.asList(messageTypeArr).contains(objM14778p) || jSONObject2 == null) {
                                c2973l0M15129a = null;
                            } else {
                                try {
                                    long j12 = NumberUtils.toLong(messageObj.m14747I(str), -1L);
                                    c2973l0M15129a = C2950b0.m14914m().m14725v(j12).m15129a(group.f13718e, jSONObject2.getLong(String.valueOf(j12)), this.f22582b);
                                    C2950b0.m14914m().m14712i(c2973l0M15129a);
                                } catch (Exception e9) {
                                    e = e9;
                                    str3 = str8;
                                    arrayList3 = arrayList;
                                    str4 = str;
                                    ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                    arrayList = arrayList3;
                                    str = str4;
                                    str8 = str3;
                                }
                            }
                            if (c2973l0M15129a != null) {
                            }
                            MessageObj.MessageType messageType3 = MessageObj.MessageType.None;
                            messageType = MessageObj.MessageType.Text;
                            if (objM14778p.equals(messageType)) {
                            }
                            strM14550E = strM14747I4;
                            messageType2 = messageType;
                            str3 = str8;
                            if (messageType2 == messageType) {
                            }
                            if (messageObjM25401l != null) {
                            }
                        } catch (Exception e10) {
                            e = e10;
                            str3 = str8;
                            arrayList3 = arrayList;
                            str4 = str;
                            ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                            arrayList = arrayList3;
                            str = str4;
                            str8 = str3;
                        }
                    } else if (jSONObject != null) {
                        try {
                            long j13 = NumberUtils.toLong(messageObj.m14747I(str), -1L);
                            c2973l0M15129a = C2950b0.m14914m().m14725v(j13).m15129a(group.f13720g, jSONObject.getLong(String.valueOf(j13)), this.f22582b);
                            C2950b0.m14914m().m14712i(c2973l0M15129a);
                            if (c2973l0M15129a != null) {
                                try {
                                    String str13 = c2973l0M15129a.m15149u().f13201e;
                                    if (str13 != null && !str13.isEmpty()) {
                                        File file = new File(str13);
                                        if (file.exists()) {
                                            String strM16517b1 = CLUtility.m16517b1(Globals.m7388i0().getApplicationContext(), String.valueOf(c2973l0M15129a.m15144p()));
                                            if (C6369f.m24459a(file, new File(strM16517b1))) {
                                                c2973l0M15129a.m15149u().f13201e = strM16517b1;
                                            }
                                        }
                                    }
                                } catch (Exception e11) {
                                    e = e11;
                                    str3 = str8;
                                    arrayList3 = arrayList;
                                    str4 = str;
                                    ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                    arrayList = arrayList3;
                                    str = str4;
                                    str8 = str3;
                                }
                            }
                            MessageObj.MessageType messageType32 = MessageObj.MessageType.None;
                            messageType = MessageObj.MessageType.Text;
                            if (objM14778p.equals(messageType)) {
                                strM14747I4 = messageObj.m14779q();
                            } else if (objM14778p.equals(MessageObj.MessageType.ReplyText)) {
                                strM14747I4 = messageObj.m14747I("replyText");
                            } else {
                                MessageObj.MessageType messageType4 = MessageObj.MessageType.UserContact;
                                if (objM14778p.equals(messageType4)) {
                                    strM14550E = messageObj.m14779q();
                                    messageType2 = messageType4;
                                } else {
                                    if (objM14778p.equals(messageType2)) {
                                        if (c2973l0M15129a != null) {
                                            strM14579W = C2925v.m14580X(c2973l0M15129a.m15148t().f13201e, String.valueOf(c2973l0M15129a.m15144p()), messageObj.m14747I("duration"));
                                            strM14550E = strM14579W;
                                        }
                                        str3 = str8;
                                        messageType2 = messageType32;
                                        strM14550E = null;
                                    } else {
                                        messageType2 = MessageObj.MessageType.Video;
                                        if (objM14778p.equals(messageType2)) {
                                            if (c2973l0M15129a != null) {
                                                strM14579W = C2925v.m14579W(null, null, String.valueOf(c2973l0M15129a.m15144p()), messageObj.m14747I("duration"), messageObj.m14747I("videoThumbPath"), null, null, null, null);
                                                strM14550E = strM14579W;
                                            }
                                            str3 = str8;
                                            messageType2 = messageType32;
                                            strM14550E = null;
                                        } else {
                                            try {
                                                MessageObj.MessageType messageType5 = MessageObj.MessageType.Comment;
                                                if (Arrays.asList(messageType5, MessageObj.MessageType.CommentUpdate).contains(objM14778p)) {
                                                    try {
                                                        if (!this.f22582b) {
                                                            str7 = str8;
                                                            messageType2 = MessageObj.MessageType.PhotoNote;
                                                            if (!objM14778p.equals(messageType2) || !this.f22582b) {
                                                                messageType2 = MessageObj.MessageType.File;
                                                                if (!objM14778p.equals(messageType2)) {
                                                                    str3 = str7;
                                                                    messageType2 = MessageObj.MessageType.ShareLocation;
                                                                    if (objM14778p.equals(messageType2)) {
                                                                        strM14570O = messageObj.m14779q();
                                                                    } else {
                                                                        messageType2 = MessageObj.MessageType.AnnouncementType02;
                                                                        if (objM14778p.equals(messageType2)) {
                                                                            strM14570O = messageObj.m14779q();
                                                                        } else {
                                                                            if (c2973l0M15129a != null) {
                                                                                strM14570O = C2925v.m14570O("", null, c2973l0M15129a.m15148t().f13200d, null, String.valueOf(c2973l0M15129a.m15144p()), null, null, c2973l0M15129a.m15151w(), c2973l0M15129a.m15141m());
                                                                                messageType2 = MessageObj.MessageType.Photo;
                                                                            }
                                                                            messageType2 = messageType32;
                                                                            strM14550E = null;
                                                                        }
                                                                    }
                                                                } else if (c2973l0M15129a != null) {
                                                                    try {
                                                                        JSONObject jSONObject3 = new JSONObject(messageObj.m14779q());
                                                                        str3 = str7;
                                                                        jSONObject3.put(str3, c2973l0M15129a.m15131c());
                                                                        jSONObject3.put(str, String.valueOf(c2973l0M15129a.m15144p()));
                                                                        strM14570O = jSONObject3.toString();
                                                                    } catch (Exception e12) {
                                                                        e = e12;
                                                                        str3 = str7;
                                                                        str4 = str;
                                                                        arrayList3 = arrayList;
                                                                        ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                                                        arrayList = arrayList3;
                                                                        str = str4;
                                                                        str8 = str3;
                                                                    }
                                                                }
                                                                strM14550E = strM14570O;
                                                            } else if (c2973l0M15129a != null) {
                                                                JSONObject jSONObject4 = new JSONObject(messageObj.m14779q());
                                                                jSONObject4.put(str, String.valueOf(c2973l0M15129a.m15144p()));
                                                                jSONObject4.put("commentCount", c2973l0M15129a.m15150v());
                                                                strM14550E = jSONObject4.toString();
                                                                str3 = str7;
                                                            }
                                                            str3 = str7;
                                                            messageType2 = messageType32;
                                                            strM14550E = null;
                                                        } else if (c2973l0M15129a != null) {
                                                            String strValueOf = String.valueOf(c2973l0M15129a.m15144p());
                                                            String strM14747I5 = messageObj.m14747I("commentType");
                                                            String strM14747I6 = messageObj.m14747I("comment");
                                                            try {
                                                                try {
                                                                    try {
                                                                        if ("Audio".equals(strM14747I5)) {
                                                                            str6 = "CommentMedia";
                                                                            strM14747I2 = messageObj.m14747I("duration");
                                                                            str5 = str2;
                                                                        } else {
                                                                            str5 = str2;
                                                                            if (str5.equals(strM14747I5)) {
                                                                                try {
                                                                                    strM14747I = messageObj.m14747I("thumbnail");
                                                                                    strM14747I2 = strM14747I6;
                                                                                    strM14747I3 = messageObj.m14747I(TtmlNode.ATTR_TTS_COLOR);
                                                                                    str6 = str5;
                                                                                    str2 = str5;
                                                                                    j9 = NumberUtils.toLong(messageObj.m14747I("commentId"), -1L);
                                                                                    str7 = str8;
                                                                                    long j14 = NumberUtils.toLong(messageObj.m14747I(str), -1L);
                                                                                    long j15 = jSONObject2.getLong(String.valueOf(j14));
                                                                                    String strValueOf2 = "";
                                                                                    if (j9 == -1 && j14 != -1 && (c3061aM25399j = C6818b.m25399j(j15, C6818b.m25397h(j9, j14))) != null) {
                                                                                        C2950b0.m14920s().m15217f(c3061aM25399j);
                                                                                        if (c3061aM25399j.m15783e() != -1) {
                                                                                            try {
                                                                                                strValueOf2 = String.valueOf(c3061aM25399j.m15783e());
                                                                                            } catch (Exception e13) {
                                                                                                e = e13;
                                                                                                str4 = str;
                                                                                                arrayList3 = arrayList;
                                                                                                str3 = str7;
                                                                                                ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                                                                                arrayList = arrayList3;
                                                                                                str = str4;
                                                                                                str8 = str3;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    strM14550E = C2925v.m14550E(group.f13718e, strValueOf, strM14747I2, str6, strValueOf2, Globals.m7388i0().getResources().getString(R.string.notify_user_to_upgrade), strM14747I, strM14747I3);
                                                                                    messageType2 = messageType5;
                                                                                    str3 = str7;
                                                                                } catch (Exception e14) {
                                                                                    e = e14;
                                                                                    str2 = str5;
                                                                                    str3 = str8;
                                                                                    arrayList3 = arrayList;
                                                                                    str4 = str;
                                                                                    ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                                                                    arrayList = arrayList3;
                                                                                    str = str4;
                                                                                    str8 = str3;
                                                                                }
                                                                            } else {
                                                                                str6 = "CommentText";
                                                                                strM14747I2 = strM14747I6;
                                                                            }
                                                                        }
                                                                        long j152 = jSONObject2.getLong(String.valueOf(j14));
                                                                        String strValueOf22 = "";
                                                                        if (j9 == -1) {
                                                                        }
                                                                        strM14550E = C2925v.m14550E(group.f13718e, strValueOf, strM14747I2, str6, strValueOf22, Globals.m7388i0().getResources().getString(R.string.notify_user_to_upgrade), strM14747I, strM14747I3);
                                                                        messageType2 = messageType5;
                                                                        str3 = str7;
                                                                    } catch (Exception e15) {
                                                                        e = e15;
                                                                    }
                                                                    long j142 = NumberUtils.toLong(messageObj.m14747I(str), -1L);
                                                                } catch (Exception e16) {
                                                                    e = e16;
                                                                }
                                                                j9 = NumberUtils.toLong(messageObj.m14747I("commentId"), -1L);
                                                                str7 = str8;
                                                            } catch (Exception e17) {
                                                                e = e17;
                                                                str3 = str8;
                                                                arrayList3 = arrayList;
                                                                str4 = str;
                                                                ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                                                arrayList = arrayList3;
                                                                str = str4;
                                                                str8 = str3;
                                                            }
                                                            strM14747I = null;
                                                            strM14747I3 = null;
                                                            str2 = str5;
                                                        } else {
                                                            str3 = str8;
                                                            messageType2 = messageType32;
                                                            strM14550E = null;
                                                        }
                                                    } catch (Exception e18) {
                                                        e = e18;
                                                    }
                                                }
                                            } catch (Exception e19) {
                                                e = e19;
                                            }
                                        }
                                    }
                                    if (messageType2 == messageType) {
                                        try {
                                            boolean zM25402m = C6818b.m25402m(messageObj);
                                            Friend friendM15003C = C2950b0.m14899A().m15003C(messageObj.m14745G());
                                            UserInfo userInfoM16497V0 = CLUtility.m16497V0(Globals.m7372O());
                                            if (userInfoM16497V0 != null) {
                                                try {
                                                    if (!userInfoM16497V0.f13800y) {
                                                        messageObjM25401l = C6818b.m25401l(group, messageType2, strM14550E);
                                                        str4 = str;
                                                    } else if (messageObj.m14749K()) {
                                                        str4 = str;
                                                        messageObjM25401l = C6818b.m25400k(group, messageType2, strM14550E, messageObj.m14782t(), messageObj.m14777o(), zM25402m ? null : friendM15003C.f13648f);
                                                    } else {
                                                        str4 = str;
                                                        messageObjM25401l = zM25402m ? C6818b.m25401l(group, messageType2, strM14550E) : C6818b.m25400k(group, messageType2, strM14550E, friendM15003C.f13648f, messageObj.m14777o(), null);
                                                    }
                                                } catch (Exception e20) {
                                                    e = e20;
                                                    str4 = str;
                                                    arrayList3 = arrayList;
                                                    ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                                    arrayList = arrayList3;
                                                    str = str4;
                                                    str8 = str3;
                                                }
                                            }
                                        } catch (Exception e21) {
                                            e = e21;
                                            str4 = str;
                                            arrayList3 = arrayList;
                                            ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                            arrayList = arrayList3;
                                            str = str4;
                                            str8 = str3;
                                        }
                                    } else {
                                        str4 = str;
                                        messageObjM25401l = C6818b.m25401l(group, messageType2, strM14550E);
                                    }
                                    if (messageObjM25401l != null) {
                                        try {
                                            arrayList3 = arrayList;
                                        } catch (Exception e22) {
                                            e = e22;
                                            arrayList3 = arrayList;
                                            ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                            arrayList = arrayList3;
                                            str = str4;
                                            str8 = str3;
                                        }
                                        try {
                                            arrayList3.add(messageObjM25401l.m14777o());
                                        } catch (Exception e23) {
                                            e = e23;
                                            ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                                            arrayList = arrayList3;
                                            str = str4;
                                            str8 = str3;
                                        }
                                    } else {
                                        arrayList3 = arrayList;
                                    }
                                }
                                str3 = str8;
                                if (messageType2 == messageType) {
                                }
                                if (messageObjM25401l != null) {
                                }
                            }
                            strM14550E = strM14747I4;
                            messageType2 = messageType;
                            str3 = str8;
                            if (messageType2 == messageType) {
                            }
                            if (messageObjM25401l != null) {
                            }
                        } catch (Exception e24) {
                            e = e24;
                            str3 = str8;
                            arrayList3 = arrayList;
                            str4 = str;
                            ULogUtility.m16670f(C6818b.f22579a, "[handleMultipleMessageCopy] ex: " + e.getMessage());
                            arrayList = arrayList3;
                            str = str4;
                            str8 = str3;
                        }
                    } else {
                        c2973l0M15129a = null;
                        if (c2973l0M15129a != null) {
                        }
                        MessageObj.MessageType messageType322 = MessageObj.MessageType.None;
                        messageType = MessageObj.MessageType.Text;
                        if (objM14778p.equals(messageType)) {
                        }
                        strM14550E = strM14747I4;
                        messageType2 = messageType;
                        str3 = str8;
                        if (messageType2 == messageType) {
                        }
                        if (messageObjM25401l != null) {
                        }
                    }
                } catch (Exception e25) {
                    e = e25;
                    str3 = str8;
                    arrayList3 = arrayList;
                }
                arrayList = arrayList3;
                str = str4;
                str8 = str3;
            }
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                this.f22584d.onSuccess();
            } else {
                this.f22584d.mo11883a();
            }
        }
    }

    /* renamed from: h */
    public static int m25397h(long j9, long j10) {
        List<C3061a> listM25405p = m25405p(j10);
        int i9 = 0;
        if (!listM25405p.isEmpty()) {
            Iterator<C3061a> it = listM25405p.iterator();
            while (it.hasNext() && j9 != it.next().m15783e()) {
                i9++;
            }
        }
        return i9;
    }

    /* renamed from: i */
    public static void m25398i(Group group, boolean z8, List<String> list, a aVar) {
        new b(group, z8, list, aVar).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: j */
    public static C3061a m25399j(long j9, int i9) {
        try {
            return m25405p(j9).get(i9);
        } catch (IndexOutOfBoundsException e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: k */
    public static MessageObj m25400k(Group group, MessageObj.MessageType messageType, String str, String str2, String str3, String str4) {
        try {
            MessageObj messageObjM14560J = C2925v.m14560J(String.valueOf(group.f13727n), messageType, str, 0, null, str2, str3, str4, false);
            messageObjM14560J.m14758T(C2925v.m14593f(group.f13716c, group.f13723j, messageObjM14560J).m22161k());
            if (C2925v.m14594f0(messageObjM14560J)) {
                messageObjM14560J.m14765a0("3");
            }
            C2950b0.m14916o().m15157B(messageObjM14560J);
            return messageObjM14560J;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: l */
    public static MessageObj m25401l(Group group, MessageObj.MessageType messageType, String str) {
        try {
            MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), messageType, str, 0, null);
            messageObjM14558I.m14758T(C2925v.m14593f(group.f13716c, group.f13723j, messageObjM14558I).m22161k());
            if (C2925v.m14594f0(messageObjM14558I)) {
                messageObjM14558I.m14765a0("3");
            }
            C2950b0.m14916o().m15157B(messageObjM14558I);
            return messageObjM14558I;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: m */
    public static boolean m25402m(MessageObj messageObj) {
        if (messageObj.m14778p() == MessageObj.MessageType.Call) {
            String strValueOf = String.valueOf(Globals.m7388i0().m7587o0());
            String strM14747I = messageObj.m14747I("callerId");
            if (strM14747I != null) {
                return strM14747I.equals(strValueOf);
            }
            return false;
        }
        String strValueOf2 = String.valueOf(Globals.m7388i0().m7568k1());
        String strM14745G = messageObj.m14745G();
        if (strM14745G != null) {
            return strM14745G.equals(strValueOf2);
        }
        return false;
    }

    /* renamed from: n */
    public static List<C3061a> m25403n(Long l9, String str) {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayM20196r = C5172p.m20196r(str);
        if (jSONArrayM20196r != null) {
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    arrayList.add(C5172p.m20183e(jSONArrayM20196r.getJSONObject(i9), l9.longValue()));
                } catch (JSONException e9) {
                    String str2 = f22579a;
                    ULogUtility.m16670f(str2, "[parseComment] [exception] " + e9.getMessage());
                    ULogUtility.m16670f(str2, "[parseComment] [jsonString] " + str);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: o */
    public static List<C3061a> m25404o(long j9, int i9) {
        ArrayList arrayList = new ArrayList();
        String strM7449L = Globals.m7388i0().m7449L();
        for (int i10 = 2; i10 <= i9; i10++) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList2.add(new C6301o("token", strM7449L));
            arrayList2.add(new C6301o("mediaId", String.valueOf(j9)));
            arrayList2.add(new C6301o("pageIndex", String.valueOf(i10)));
            arrayList2.add(new C6301o("pageSize", String.valueOf(20)));
            Pair<String, String> pairM15731j = f22580b.m15731j("media", "listComment", arrayList2);
            String str = (String) pairM15731j.first;
            String str2 = (String) pairM15731j.second;
            if ("200".equals(str)) {
                int iM16553k1 = CLUtility.m16553k1(str2);
                int iM16494U0 = CLUtility.m16494U0(str2);
                if (iM16553k1 == -1 || iM16494U0 == -1) {
                    return arrayList;
                }
                arrayList.addAll(m25403n(Long.valueOf(j9), str2));
            }
        }
        return arrayList;
    }

    /* renamed from: p */
    public static List<C3061a> m25405p(long j9) {
        ArrayList arrayList = new ArrayList();
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new C6301o("token", strM7449L));
        arrayList2.add(new C6301o("mediaId", String.valueOf(j9)));
        arrayList2.add(new C6301o("pageIndex", "1"));
        arrayList2.add(new C6301o("pageSize", String.valueOf(20)));
        Pair<String, String> pairM15731j = f22580b.m15731j("media", "listComment", arrayList2);
        String str = (String) pairM15731j.first;
        String str2 = (String) pairM15731j.second;
        if ("200".equals(str)) {
            int iM16553k1 = CLUtility.m16553k1(str2);
            int iM16494U0 = CLUtility.m16494U0(str2);
            if (iM16553k1 != -1 && iM16494U0 != -1) {
                arrayList.addAll(m25403n(Long.valueOf(j9), str2));
                if (iM16553k1 != iM16494U0) {
                    arrayList.addAll(m25404o(j9, CLUtility.m16559m(iM16553k1, 20)));
                }
            }
        }
        return arrayList;
    }
}
