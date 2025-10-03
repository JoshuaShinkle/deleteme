package p116k4;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Date;
import java.util.Map;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: k4.s */
/* loaded from: classes.dex */
public class C5180s {

    /* renamed from: k4.s$a */
    public static /* synthetic */ class a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f17748a;

        static {
            int[] iArr = new int[MessageObj.MessageType.values().length];
            f17748a = iArr;
            try {
                iArr[MessageObj.MessageType.Text.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17748a[MessageObj.MessageType.Photo.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17748a[MessageObj.MessageType.CreateMedia.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17748a[MessageObj.MessageType.AnimSticker.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17748a[MessageObj.MessageType.Sticker.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17748a[MessageObj.MessageType.AnimPngSticker.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17748a[MessageObj.MessageType.Comment.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f17748a[MessageObj.MessageType.PhotoNote.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f17748a[MessageObj.MessageType.Audio.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f17748a[MessageObj.MessageType.CommentUpdate.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f17748a[MessageObj.MessageType.UserContact.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f17748a[MessageObj.MessageType.AnnouncementType01.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f17748a[MessageObj.MessageType.AnnouncementType02.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f17748a[MessageObj.MessageType.AnnouncementType03.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f17748a[MessageObj.MessageType.Video.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f17748a[MessageObj.MessageType.File.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f17748a[MessageObj.MessageType.NewVersion.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f17748a[MessageObj.MessageType.ShareLocation.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f17748a[MessageObj.MessageType.Call.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f17748a[MessageObj.MessageType.Event.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f17748a[MessageObj.MessageType.Poll.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f17748a[MessageObj.MessageType.PollPost.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f17748a[MessageObj.MessageType.ReportToAdmin.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f17748a[MessageObj.MessageType.ReplyText.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f17748a[MessageObj.MessageType.VideoDelete.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f17748a[MessageObj.MessageType.FileDelete.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f17748a[MessageObj.MessageType.ENCRYPTED_MSG.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m20248a(String str, String str2) {
        return (str == null || str2 == null || C5616j.m22346k(str) == null || !str2.equals(str)) ? false : true;
    }

    /* renamed from: b */
    public static String m20249b(Context context, TopicCommentObj topicCommentObj) throws JSONException {
        Friend friendM20254g;
        String string = (topicCommentObj == null || (friendM20254g = m20254g(String.valueOf(topicCommentObj.m14031f()))) == null) ? null : context.getString(R.string.last_msg_post_created, friendM20254g.m15621b());
        JSONObject jSONObject = new JSONObject();
        if (string != null) {
            try {
                jSONObject.put(FirebaseAnalytics.Param.CONTENT, string);
                jSONObject.put("time", topicCommentObj.m14030e());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    /* renamed from: c */
    public static String m20250c(Context context, MessageObj messageObj) {
        return messageObj == null ? m20251d(null, null, null) : m20251d(context, messageObj, m20254g(messageObj.m14745G()));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: d */
    public static String m20251d(Context context, MessageObj messageObj, Friend friend) throws JSONException, Resources.NotFoundException {
        String strM14779q;
        String strM14747I;
        int i9;
        strM14779q = "";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FirebaseAnalytics.Param.CONTENT, "");
            jSONObject.put("time", 0L);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        if (context == null || friend == null) {
            return jSONObject.toString();
        }
        String strValueOf = String.valueOf(friend.f13645c);
        MessageObj.MessageType messageTypeM14778p = messageObj.m14778p();
        if (MessageObj.MessageType.Call == messageTypeM14778p) {
            try {
                strValueOf = messageObj.m14747I("callerId").split("@")[0];
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }
        boolean zM20248a = m20248a(String.valueOf(Globals.m7388i0().m7568k1()), strValueOf);
        if (!messageObj.m14741C().equals(MessageObj.TTLStatus.NOT_START)) {
            switch (a.f17748a[messageTypeM14778p.ordinal()]) {
                case 1:
                    if (!zM20248a) {
                        strM14779q = messageObj.m14779q();
                        break;
                    } else if (!messageObj.m14750L()) {
                        if (!messageObj.m14752N()) {
                            strM14779q = messageObj.m14779q();
                            break;
                        } else {
                            strM14779q = context.getResources().getString(R.string.schedule_chat_you_send_msg);
                            break;
                        }
                    } else {
                        strM14779q = context.getResources().getString(R.string.chat_you_send_broadcast);
                        break;
                    }
                case 2:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_photo), friend.m15621b());
                        break;
                    } else if (!messageObj.m14750L()) {
                        if (!messageObj.m14752N()) {
                            strM14779q = context.getResources().getString(R.string.notification_chat_you_send_photo);
                            break;
                        } else {
                            strM14779q = context.getResources().getString(R.string.schedule_chat_you_send_photo);
                            break;
                        }
                    } else {
                        strM14779q = context.getResources().getString(R.string.chat_you_send_broadcast);
                        break;
                    }
                case 3:
                    try {
                        String string = new JSONObject(messageObj.m14779q()).getString("numberUpload");
                        if (string != null && (i9 = Integer.parseInt(string)) != 0) {
                            strM14747I = zM20248a ? String.format(context.getResources().getQuantityString(R.plurals.chatlist_you_share_photo, i9), Integer.valueOf(i9)) : String.format(context.getResources().getQuantityString(R.plurals.chatlist_user_share_photo, i9), friend.m15621b(), Integer.valueOf(i9));
                            strM14779q = strM14747I;
                            break;
                        }
                    } catch (JSONException e11) {
                        Log.e("LastMessageUtils", Log.getStackTraceString(e11));
                        break;
                    }
                    break;
                case 4:
                case 5:
                case 6:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_sticker), friend.m15621b());
                        break;
                    } else if (!messageObj.m14750L()) {
                        if (!messageObj.m14752N()) {
                            strM14779q = context.getResources().getString(R.string.notification_chat_you_send_sticker);
                            break;
                        } else {
                            strM14779q = context.getResources().getString(R.string.schedule_chat_you_send_sticker);
                            break;
                        }
                    } else {
                        strM14779q = context.getResources().getString(R.string.chat_you_send_broadcast);
                        break;
                    }
                case 7:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_comment_photo), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.notification_chat_you_comment_photo);
                        break;
                    }
                case 8:
                    MessageObj.NoteType noteTypeM14780r = messageObj.m14780r();
                    if (!zM20248a) {
                        if (!noteTypeM14780r.equals(MessageObj.NoteType.Text)) {
                            strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_photo_voice_note), friend.m15621b());
                            break;
                        } else {
                            strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_photo_text_note), friend.m15621b());
                            break;
                        }
                    } else if (!messageObj.m14750L()) {
                        if (!messageObj.m14752N()) {
                            if (!noteTypeM14780r.equals(MessageObj.NoteType.Text)) {
                                strM14779q = context.getResources().getString(R.string.notification_chat_you_send_photo_voice_note);
                                break;
                            } else {
                                strM14779q = context.getResources().getString(R.string.notification_chat_you_send_photo_text_note);
                                break;
                            }
                        } else {
                            strM14779q = context.getResources().getString(R.string.schedule_chat_you_send_photo);
                            break;
                        }
                    } else {
                        strM14779q = context.getResources().getString(R.string.chat_you_send_broadcast);
                        break;
                    }
                case 9:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_receive_voice_msg), friend.m15621b());
                        break;
                    } else if (!messageObj.m14750L()) {
                        if (!messageObj.m14752N()) {
                            strM14779q = context.getResources().getString(R.string.notification_chat_you_send_voice_msg);
                            break;
                        } else {
                            strM14779q = context.getResources().getString(R.string.schedule_chat_you_send_voice);
                            break;
                        }
                    } else {
                        strM14779q = context.getResources().getString(R.string.chat_you_send_broadcast);
                        break;
                    }
                case 10:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_edit_comment_photo), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.notification_chat_you_edit_comment_photo);
                        break;
                    }
                case 11:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_user_contact), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.notification_chat_you_send_user_contact);
                        break;
                    }
                case 12:
                case 13:
                case 14:
                    try {
                        strM14747I = messageTypeM14778p.equals(MessageObj.MessageType.AnnouncementType02) ? messageObj.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE) : messageObj.m14748J(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, FirebaseAnalytics.Param.CONTENT);
                        strM14779q = strM14747I;
                        break;
                    } catch (Exception unused) {
                        break;
                    }
                case 15:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_video), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.notification_chat_you_send_video);
                        break;
                    }
                case 16:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_file), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.notification_chat_you_send_file);
                        break;
                    }
                case 17:
                    strM14779q = context.getResources().getString(R.string.unsupported_message);
                    break;
                case 18:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_location), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.schedule_chat_you_send_location);
                        break;
                    }
                case 19:
                    String strM14747I2 = messageObj.m14747I("statusV2");
                    if (C5170o0.m20170e(strM14747I2)) {
                        strM14747I2 = messageObj.m14747I("status");
                    }
                    String str = strM14747I2;
                    try {
                        Friend friendM20254g = m20254g(messageObj.m14747I("calleeId").split("@")[0]);
                        if (friendM20254g == null) {
                            ULogUtility.m16670f("LastMessageUtils", "Parse callee fail, message : " + messageObj.toString());
                        } else {
                            strM14779q = friendM20254g.m15621b();
                        }
                    } catch (Exception e12) {
                        ULogUtility.m16670f("LastMessageUtils", "Parse callee exception, message : " + messageObj.toString());
                        e12.printStackTrace();
                    }
                    if (!"normal".equals(str)) {
                        if (!"cancel".equals(str)) {
                            if (!"busy".equals(str) && !"reject".equals(str)) {
                                if (!"unreached".equals(str)) {
                                    strM14779q = context.getResources().getString(R.string.call_miss);
                                    break;
                                } else {
                                    strM14779q = context.getResources().getString(R.string.call_unreached, strM14779q);
                                    break;
                                }
                            } else {
                                strM14779q = context.getResources().getString(R.string.call_busy, strM14779q);
                                break;
                            }
                        } else {
                            strM14779q = context.getResources().getString(R.string.call_cancel);
                            break;
                        }
                    } else {
                        strM14779q = context.getResources().getString(R.string.had_a_call, CLUtility.m16523d(messageObj.m14747I("duration")));
                        break;
                    }
                    break;
                case 20:
                    String strM14747I3 = messageObj.m14747I("statusV2");
                    if (C5170o0.m20170e(strM14747I3)) {
                        strM14747I3 = messageObj.m14747I("status");
                    }
                    if (!"meeting".equals(strM14747I3)) {
                        if (TtmlNode.END.equals(strM14747I3)) {
                            Date dateM20085a = C5157k.m20085a(messageObj.m14747I("endTime"));
                            strM14779q = context.getResources().getString(R.string.meeting_end, dateM20085a != null ? CLUtility.m16454J2(dateM20085a) : "");
                            break;
                        }
                    } else {
                        Date dateM20085a2 = C5157k.m20085a(messageObj.m14747I("startTime"));
                        strM14779q = context.getResources().getString(R.string.had_a_meeting, dateM20085a2 != null ? CLUtility.m16454J2(dateM20085a2) : "");
                        break;
                    }
                    break;
                case 21:
                    strM14779q = context.getString(R.string.S_asked_a_question_in_polls, friend.m15621b());
                    break;
                case 22:
                    strM14779q = context.getString(R.string.S_replied_to_a_question_in_polls, friend.m15621b());
                    break;
                case 23:
                    strM14779q = messageObj.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
                    break;
                case 24:
                    strM14779q = messageObj.m14747I("replyText");
                    break;
                case 25:
                    int iM20253f = m20253f(messageObj);
                    if (iM20253f != 0) {
                        if (!zM20248a) {
                            strM14779q = String.format(context.getResources().getQuantityString(R.plurals.event_group_videos_delete, iM20253f), friend.m15621b(), Integer.valueOf(iM20253f));
                            break;
                        } else {
                            strM14779q = String.format(context.getResources().getQuantityString(R.plurals.chatlist_you_delete_video, iM20253f), Integer.valueOf(iM20253f));
                            break;
                        }
                    } else {
                        break;
                    }
                case 26:
                    int iM20253f2 = m20253f(messageObj);
                    if (iM20253f2 != 0) {
                        if (!zM20248a) {
                            strM14779q = String.format(context.getResources().getQuantityString(R.plurals.event_group_files_delete, iM20253f2), friend.m15621b(), Integer.valueOf(iM20253f2));
                            break;
                        } else {
                            strM14779q = String.format(context.getResources().getQuantityString(R.plurals.chatlist_you_delete_file, iM20253f2), Integer.valueOf(iM20253f2));
                            break;
                        }
                    } else {
                        break;
                    }
                case 27:
                    if (!zM20248a) {
                        strM14779q = String.format(context.getResources().getString(R.string.notification_chat_user_send_locked_msg), friend.m15621b());
                        break;
                    } else {
                        strM14779q = context.getResources().getString(R.string.notification_chat_you_send_locked_msg);
                        break;
                    }
            }
            return jSONObject.toString();
        }
        strM14779q = context.getResources().getString(R.string.notification_default_string);
        try {
            jSONObject.put(FirebaseAnalytics.Param.CONTENT, strM14779q);
            jSONObject.put("time", messageObj.m14788z().getTime());
        } catch (JSONException e13) {
            e13.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: e */
    public static String m20252e(Context context, TopicObj topicObj) throws JSONException {
        Friend friendM20254g;
        String string = (topicObj == null || (friendM20254g = m20254g(String.valueOf(topicObj.m14841f()))) == null) ? null : context.getString(R.string.last_msg_topic_created, friendM20254g.m15621b());
        JSONObject jSONObject = new JSONObject();
        try {
            if (string != null) {
                jSONObject.put(FirebaseAnalytics.Param.CONTENT, string);
                jSONObject.put("time", topicObj.m14840e());
            } else {
                jSONObject.put(FirebaseAnalytics.Param.CONTENT, "");
                jSONObject.put("time", new Date().getTime());
            }
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: f */
    public static int m20253f(MessageObj messageObj) throws JSONException {
        try {
            String string = new JSONObject(messageObj.m14779q()).getString("numberDelete");
            if (C5170o0.m20170e(string)) {
                return 0;
            }
            return Integer.parseInt(string);
        } catch (JSONException unused) {
            return 0;
        }
    }

    /* renamed from: g */
    public static Friend m20254g(String str) {
        Map<String, Friend> mapM7574l1 = Globals.m7388i0().m7574l1();
        Friend friendM15003C = mapM7574l1.containsKey(str) ? mapM7574l1.get(str) : C2950b0.m14899A().m15003C(str);
        if (friendM15003C == null && (friendM15003C = new FriendsClient().m15727f0(str)) != null) {
            C2950b0.m14899A().m15018j(friendM15003C, false);
        }
        if (friendM15003C != null && friendM15003C.f13664v) {
            String string = Globals.m7388i0().getString(R.string.unknown_user_name);
            friendM15003C.m15624e(string);
            friendM15003C.m15625f(string);
        }
        return friendM15003C;
    }

    /* renamed from: h */
    public static String m20255h(String str) {
        if (str != null) {
            try {
                MessageObj messageObjM15176o = C2950b0.m14916o().m15176o(str);
                MessageObj messageObjM15173l = C2950b0.m14916o().m15173l(str);
                TopicObj topicObjM14983m = C2950b0.m14906e().m14983m(Long.valueOf(str).longValue());
                TopicCommentObj topicCommentObjM14950m = C2950b0.m14905d().m14950m(Long.valueOf(str).longValue());
                long time = (messageObjM15176o == null || messageObjM15176o.m14788z() == null) ? 0L : messageObjM15176o.m14788z().getTime();
                long time2 = (messageObjM15173l == null || messageObjM15173l.m14788z() == null) ? 0L : messageObjM15173l.m14788z().getTime();
                if (time < time2) {
                    messageObjM15176o = messageObjM15173l;
                    time = time2;
                }
                long jM14840e = topicObjM14983m != null ? topicObjM14983m.m14840e() : 0L;
                long jM14030e = topicCommentObjM14950m != null ? topicCommentObjM14950m.m14030e() : 0L;
                Log.d("LastMessageUtils", "[updateLastMessage] lastMessageTime:" + time + " lastTopicTime:" + jM14840e + " lastPostTime:" + jM14030e);
                Group group = new Group();
                if (time == 0 && jM14840e == 0 && jM14030e == 0) {
                    group.f13739z = "";
                } else if (time > jM14840e) {
                    if (time > jM14030e) {
                        group.f13739z = m20250c(Globals.m7388i0(), messageObjM15176o);
                    } else {
                        group.f13739z = m20249b(Globals.m7388i0(), topicCommentObjM14950m);
                    }
                } else if (jM14840e > jM14030e) {
                    group.f13739z = m20252e(Globals.m7388i0(), topicObjM14983m);
                } else {
                    group.f13739z = m20249b(Globals.m7388i0(), topicCommentObjM14950m);
                }
                C2950b0.m14912k().m15062A(str, group, "LastMsg");
                CLUtility.m16585s1(group, false);
                return group.f13739z;
            } catch (Exception e9) {
                Log.e("LastMessageUtils", "[updateLastMessage] Error : " + e9.getMessage());
            }
        }
        return "";
    }

    /* renamed from: i */
    public static void m20256i(Context context, MessageObj messageObj) {
        String strM20250c;
        if (messageObj == null || (strM20250c = m20250c(context, messageObj)) == null) {
            return;
        }
        Group group = new Group();
        group.f13739z = strM20250c;
        C2950b0.m14912k().m15062A(messageObj.m14772j(), group, "LastMsg");
        CLUtility.m16585s1(group, false);
    }
}
