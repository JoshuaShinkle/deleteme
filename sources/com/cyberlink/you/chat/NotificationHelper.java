package com.cyberlink.you.chat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.LockScreenActivity;
import com.cyberlink.you.activity.NotificationActivity;
import com.cyberlink.you.activity.QueryMessageActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.splash.SplashActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sqlcipher.database.SQLiteDatabase;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jivesoftware.smack.packet.Message;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.AbstractC5146g0;
import p116k4.C5170o0;
import p116k4.C5172p;
import p173q2.C6127a;
import p188s.C6232g;
import p188s.C6239n;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6385v;

/* loaded from: classes.dex */
public class NotificationHelper {

    /* renamed from: a */
    public static boolean f12417a = false;

    public enum HasNotifiedResultType {
        NO_RECORD,
        HAS_NOTIFIED,
        IN_DATABASE
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$a */
    public class C2834a extends AbstractC6381r<Friend, Void> {

        /* renamed from: c */
        public final /* synthetic */ C2904l f12422c;

        /* renamed from: d */
        public final /* synthetic */ XMPPManager.HandleType f12423d;

        public C2834a(C2904l c2904l, XMPPManager.HandleType handleType) {
            this.f12422c = c2904l;
            this.f12423d = handleType;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Friend friend) {
            Map<String, String> mapM14373e;
            C2898i c2898i = (C2898i) this.f12422c.m14426l("event", "urn:xmpp:custom:event");
            if (c2898i == null || (mapM14373e = c2898i.m14373e()) == null || !"invite.friend.accepted".equals(mapM14373e.get("eventType"))) {
                return;
            }
            String str = String.format(Globals.m7372O().getString(R.string.notification_user_accpet_friend_request), friend.m15621b());
            Intent intent = new Intent(Globals.m7372O(), (Class<?>) ULauncherActivity.class);
            intent.putExtra("Tab_Index", 1);
            NotificationHelper.m14069M("U", str, str, intent, friend.f13647e, this.f12423d);
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$b */
    public class C2835b extends AbstractC6381r<Friend, Void> {

        /* renamed from: c */
        public final /* synthetic */ C2904l f12424c;

        /* renamed from: d */
        public final /* synthetic */ XMPPManager.HandleType f12425d;

        public C2835b(C2904l c2904l, XMPPManager.HandleType handleType) {
            this.f12424c = c2904l;
            this.f12425d = handleType;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Friend friend) {
            Map<String, String> mapM14373e;
            C2898i c2898i = (C2898i) this.f12424c.m14426l("event", "urn:xmpp:custom:event");
            if (c2898i == null || (mapM14373e = c2898i.m14373e()) == null || !"invite.friend.created".equals(mapM14373e.get("eventType"))) {
                return;
            }
            String str = String.format(Globals.m7372O().getString(R.string.notification_user_invitation_friend_request), friend.m15621b());
            Intent intent = new Intent(Globals.m7372O(), (Class<?>) ULauncherActivity.class);
            intent.putExtra("Tab_Index", 1);
            NotificationHelper.m14069M("U", str, str, intent, friend.f13647e, this.f12425d);
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$c */
    public class C2836c extends AbstractC6381r<Bitmap, Void> {

        /* renamed from: c */
        public final /* synthetic */ String f12426c;

        /* renamed from: d */
        public final /* synthetic */ String f12427d;

        /* renamed from: e */
        public final /* synthetic */ String f12428e;

        /* renamed from: f */
        public final /* synthetic */ Intent f12429f;

        /* renamed from: g */
        public final /* synthetic */ boolean f12430g;

        /* renamed from: h */
        public final /* synthetic */ XMPPManager.HandleType f12431h;

        public C2836c(String str, String str2, String str3, Intent intent, boolean z8, XMPPManager.HandleType handleType) {
            this.f12426c = str;
            this.f12427d = str2;
            this.f12428e = str3;
            this.f12429f = intent;
            this.f12430g = z8;
            this.f12431h = handleType;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) throws Resources.NotFoundException {
            Context applicationContext = Globals.m7388i0().getApplicationContext();
            try {
                String string = this.f12426c;
                String string2 = this.f12427d;
                String str = this.f12428e;
                boolean zM7439I1 = Globals.m7388i0().m7439I1();
                NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
                PendingIntent activity = PendingIntent.getActivity(applicationContext, 0, this.f12429f, Build.VERSION.SDK_INT >= 31 ? 201326592 : SQLiteDatabase.CREATE_IF_NECESSARY);
                if (this.f12430g) {
                    string = applicationContext.getResources().getString(R.string.u_app_name);
                    string2 = applicationContext.getResources().getString(R.string.privacy_password_notification_content);
                    str = string2;
                }
                C6232g.e eVarM23843i = new C6232g.e(applicationContext, NotificationHelper.m14104t()).m23858x(R.drawable.ic_stat_name_s).m23840f(true).m23847m(Globals.m7388i0().m7482S(zM7439I1)).m23846l(string).m23845k(string2).m23832A(str).m23844j(activity).m23859y(Globals.m7388i0().m7433H0(zM7439I1)).m23843i(Globals.m7388i0().m7423F0());
                if (bitmap != null) {
                    eVarM23843i.m23851q(bitmap);
                }
                notificationManager.cancel(2);
                notificationManager.cancel(Globals.m7388i0().m7472Q(), 1);
                notificationManager.notify(2, eVarM23843i.m23837b());
                if (!Globals.m7388i0().m7460N1()) {
                    CLUtility.m16486R2(applicationContext);
                }
                ULogUtility.m16659B(this.f12431h.toString(), "[sendEventNotification] message content:" + string2);
                ULogUtility.m16659B(this.f12431h.toString(), "Notify success.");
            } catch (Exception e9) {
                ULogUtility.m16659B(this.f12431h.toString(), "Notify fail. exception = " + Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$d */
    public class C2837d extends AbstractC6381r<Bitmap, Void> {

        /* renamed from: c */
        public final /* synthetic */ Intent f12432c;

        /* renamed from: d */
        public final /* synthetic */ String f12433d;

        /* renamed from: e */
        public final /* synthetic */ String f12434e;

        /* renamed from: f */
        public final /* synthetic */ String f12435f;

        public C2837d(Intent intent, String str, String str2, String str3) {
            this.f12432c = intent;
            this.f12433d = str;
            this.f12434e = str2;
            this.f12435f = str3;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) {
            Context contextM7372O = Globals.m7372O();
            Intent intent = new Intent(Globals.m7372O(), (Class<?>) MeetingActivity.class);
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            Bundle extras = this.f12432c.getExtras();
            if (extras != null) {
                intent.putExtras(extras);
            }
            int i9 = Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728;
            PendingIntent activity = PendingIntent.getActivity(contextM7372O, 0, intent, i9);
            Intent intent2 = new Intent(contextM7372O, (Class<?>) NotificationDismissedReceiver.class);
            intent2.putExtra("isMeeting", true);
            PendingIntent broadcast = PendingIntent.getBroadcast(contextM7372O, 1, intent2, i9);
            Intent intent3 = new Intent(Globals.m7372O(), (Class<?>) MeetingActivity.class);
            intent3.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            intent3.putExtras(NotificationHelper.m14107w(this.f12432c.getExtras(), "notificationCall", this.f12433d, this.f12434e));
            PendingIntent.getActivity(Globals.m7372O(), 2, intent3, i9);
            Intent intent4 = new Intent(Globals.m7372O(), (Class<?>) MeetingActivity.class);
            intent4.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            intent4.putExtras(NotificationHelper.m14107w(this.f12432c.getExtras(), "notificationDecline", this.f12433d, this.f12434e));
            PendingIntent.getActivity(Globals.m7372O(), 3, intent4, i9);
            C6232g.e eVarM23850p = new C6232g.e(contextM7372O, NotificationHelper.m14101q()).m23858x(R.drawable.ic_stat_name_s).m23846l(this.f12433d).m23845k(this.f12435f).m23860z(new C6232g.c().m23829h(this.f12433d).m23828g(this.f12435f)).m23840f(true).m23832A(this.f12435f).m23843i(Globals.m7388i0().m7423F0()).m23841g("call").m23848n(broadcast).m23850p(activity, true);
            if (bitmap != null) {
                eVarM23850p.m23851q(bitmap);
            }
            Notification notificationM23837b = eVarM23850p.m23837b();
            notificationM23837b.flags |= 4;
            ((NotificationManager) contextM7372O.getSystemService("notification")).notify(4, notificationM23837b);
            ULogUtility.m16659B("sendMeetingNotification", "[sendNotification] message content:" + this.f12435f);
            ULogUtility.m16659B("sendMeetingNotification", "Notify success.");
            NotificationHelper.f12417a = true;
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$e */
    public class C2838e extends AbstractC6381r<Bitmap, Void> {

        /* renamed from: c */
        public final /* synthetic */ String f12436c;

        /* renamed from: d */
        public final /* synthetic */ String f12437d;

        /* renamed from: e */
        public final /* synthetic */ String f12438e;

        /* renamed from: f */
        public final /* synthetic */ Group f12439f;

        /* renamed from: g */
        public final /* synthetic */ Intent f12440g;

        /* renamed from: h */
        public final /* synthetic */ boolean f12441h;

        /* renamed from: i */
        public final /* synthetic */ XMPPManager.HandleType f12442i;

        public C2838e(String str, String str2, String str3, Group group, Intent intent, boolean z8, XMPPManager.HandleType handleType) {
            this.f12436c = str;
            this.f12437d = str2;
            this.f12438e = str3;
            this.f12439f = group;
            this.f12440g = intent;
            this.f12441h = z8;
            this.f12442i = handleType;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) throws Resources.NotFoundException {
            Context contextM7372O = Globals.m7372O();
            try {
                String string = this.f12436c;
                String string2 = this.f12437d;
                String str = this.f12438e;
                boolean zM7439I1 = Globals.m7388i0().m7439I1();
                NotificationManager notificationManager = (NotificationManager) contextM7372O.getSystemService("notification");
                PendingIntent activity = PendingIntent.getActivity(contextM7372O, Globals.m7388i0().m7432H(this.f12439f), this.f12440g, Build.VERSION.SDK_INT >= 31 ? 335544320 : SQLiteDatabase.CREATE_IF_NECESSARY);
                if (this.f12441h) {
                    string = contextM7372O.getResources().getString(R.string.u_app_name);
                    string2 = contextM7372O.getResources().getString(R.string.privacy_password_notification_content);
                    str = string2;
                }
                C6232g.c cVarM23828g = new C6232g.c().m23829h(string).m23828g(string2);
                String strM14104t = ("Dual".equals(this.f12439f.f13716c) && "U Team".equals(this.f12439f.f13717d)) ? NotificationHelper.m14104t() : NotificationHelper.m14102r();
                if (NotificationHelper.f12417a) {
                    strM14104t = NotificationHelper.m14103s();
                }
                C6232g.e eVarM23843i = new C6232g.e(contextM7372O, strM14104t).m23860z(cVarM23828g).m23846l(string).m23845k(string2).m23858x(R.drawable.ic_stat_name_s).m23840f(true).m23832A(str).m23844j(activity).m23847m(Globals.m7388i0().m7482S(zM7439I1)).m23859y(Globals.m7388i0().m7433H0(zM7439I1)).m23843i(Globals.m7388i0().m7423F0());
                if (bitmap != null) {
                    eVarM23843i.m23851q(bitmap);
                }
                notificationManager.cancel(2);
                notificationManager.notify(this.f12439f.f13723j, 1, eVarM23843i.m23837b());
                Globals.m7388i0().m7420E2(this.f12439f.f13723j);
                ULogUtility.m16659B(this.f12442i.toString(), "[sendNotification] message content:" + string2);
                ULogUtility.m16659B(this.f12442i.toString(), "Notify success.");
            } catch (Exception e9) {
                ULogUtility.m16659B(this.f12442i.toString(), "Notify fail. exception = " + Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$f */
    public class C2839f extends AbstractC6381r<Bitmap, Void> {

        /* renamed from: c */
        public final /* synthetic */ Group f12443c;

        /* renamed from: d */
        public final /* synthetic */ String f12444d;

        /* renamed from: e */
        public final /* synthetic */ boolean f12445e;

        public C2839f(Group group, String str, boolean z8) {
            this.f12443c = group;
            this.f12444d = str;
            this.f12445e = z8;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) throws Resources.NotFoundException {
            NotificationHelper.m14079W(this.f12443c, this.f12444d);
            Context contextM7372O = Globals.m7372O();
            try {
                String string = this.f12444d;
                String string2 = contextM7372O.getResources().getString(R.string.call_miss);
                boolean zM7439I1 = Globals.m7388i0().m7439I1();
                NotificationManager notificationManager = (NotificationManager) contextM7372O.getSystemService("notification");
                int iM7432H = Globals.m7388i0().m7432H(this.f12443c);
                Intent intent = new Intent(Globals.m7372O(), (Class<?>) ULauncherActivity.class);
                intent.putExtra("Tab_Index", 3);
                PendingIntent activity = PendingIntent.getActivity(contextM7372O, iM7432H, intent, Build.VERSION.SDK_INT >= 31 ? 335544320 : SQLiteDatabase.CREATE_IF_NECESSARY);
                if (this.f12445e) {
                    string = contextM7372O.getResources().getString(R.string.u_app_name);
                    string2 = contextM7372O.getResources().getString(R.string.privacy_password_notification_content);
                }
                C6232g.c cVarM23828g = new C6232g.c().m23829h(string).m23828g(string2);
                String strM14102r = NotificationHelper.m14102r();
                if (NotificationHelper.f12417a) {
                    strM14102r = NotificationHelper.m14103s();
                }
                C6232g.e eVarM23843i = new C6232g.e(contextM7372O, strM14102r).m23860z(cVarM23828g).m23846l(string).m23845k(string2).m23858x(R.drawable.ic_stat_name_s).m23840f(true).m23832A(string2).m23844j(activity).m23847m(Globals.m7388i0().m7482S(zM7439I1)).m23859y(Globals.m7388i0().m7433H0(zM7439I1)).m23843i(Globals.m7388i0().m7423F0());
                if (bitmap != null) {
                    eVarM23843i.m23851q(bitmap);
                }
                notificationManager.cancel(2);
                notificationManager.notify(this.f12443c.f13723j, 1, eVarM23843i.m23837b());
                Globals.m7388i0().m7420E2(this.f12443c.f13723j);
                ULogUtility.m16659B("PSTN", "[sendNotification] message content:" + string2);
                ULogUtility.m16659B("PSTN", "Notify success.");
            } catch (Exception e9) {
                ULogUtility.m16659B("PSTN", "Notify fail. exception = " + Log.getStackTraceString(e9));
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$g */
    public class C2840g extends Thread {
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("clearNotificationList");
            Log.d("NotificationHelper", "clearNotificationList enter");
            C2950b0.m14917p().m15191d(new Date().getTime() - DateUtils.MILLIS_PER_DAY);
            Log.d("NotificationHelper", "clearNotificationList leave");
        }
    }

    /* renamed from: com.cyberlink.you.chat.NotificationHelper$h */
    public static /* synthetic */ class C2841h {

        /* renamed from: a */
        public static final /* synthetic */ int[] f12446a;

        static {
            int[] iArr = new int[MessageObj.MessageType.values().length];
            f12446a = iArr;
            try {
                iArr[MessageObj.MessageType.DeleteMedia.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12446a[MessageObj.MessageType.Text.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12446a[MessageObj.MessageType.Photo.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f12446a[MessageObj.MessageType.PhotoNote.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f12446a[MessageObj.MessageType.Sticker.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f12446a[MessageObj.MessageType.AnimSticker.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f12446a[MessageObj.MessageType.AnimPngSticker.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f12446a[MessageObj.MessageType.StickerTypeUnknown.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f12446a[MessageObj.MessageType.Comment.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f12446a[MessageObj.MessageType.CreateMedia.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f12446a[MessageObj.MessageType.Event.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f12446a[MessageObj.MessageType.Audio.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f12446a[MessageObj.MessageType.UserContact.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f12446a[MessageObj.MessageType.Video.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f12446a[MessageObj.MessageType.AnnouncementType01.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f12446a[MessageObj.MessageType.AnnouncementType02.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f12446a[MessageObj.MessageType.AnnouncementType03.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f12446a[MessageObj.MessageType.ShareLocation.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f12446a[MessageObj.MessageType.File.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f12446a[MessageObj.MessageType.Poll.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f12446a[MessageObj.MessageType.PollPost.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f12446a[MessageObj.MessageType.ReportToAdmin.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f12446a[MessageObj.MessageType.Call.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f12446a[MessageObj.MessageType.ReplyText.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f12446a[MessageObj.MessageType.VideoDelete.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f12446a[MessageObj.MessageType.FileDelete.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
        }
    }

    /* renamed from: A */
    public static void m14057A() {
        NotificationManager notificationManager = (NotificationManager) Globals.m7388i0().getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(3);
        }
    }

    /* renamed from: B */
    public static boolean m14058B() {
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        boolean zM7494U1 = Globals.m7388i0().m7494U1();
        boolean z8 = true;
        boolean zM20046d = AbstractC5146g0.m20046d("lockScreenEnabled", true, applicationContext);
        if (!m14059C() || !zM20046d || MeetingActivity.m6383gb() || (zM7494U1 && !LockScreenActivity.f8034g && !Globals.m7388i0().m7460N1())) {
            z8 = false;
        }
        ULogUtility.m16683s("NotificationHelper", "isShowTextWhenScreenLock = " + z8 + ", isInMeetingAndActivityAtForeground = " + MeetingActivity.m6383gb() + " | isLockScreenEnabled = " + zM20046d + " | isScreenOn = " + zM7494U1 + " | LockScreenActivity.isActive = " + LockScreenActivity.f8034g + " | isLockScreen = " + Globals.m7388i0().m7460N1());
        return z8;
    }

    /* renamed from: C */
    public static boolean m14059C() {
        return C6239n.m23876b(Globals.m7372O()).m23877a();
    }

    /* renamed from: E */
    public static /* synthetic */ void m14061E(boolean z8, String str, final AbstractC6381r abstractC6381r) {
        if (z8) {
            str = null;
        }
        final Bitmap bitmapM23462c = C6127a.m23462c(Globals.m7372O(), str, R.drawable.ic_launcher);
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.chat.f0
            @Override // java.lang.Runnable
            public final void run() {
                abstractC6381r.m24506d(bitmapM23462c);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0456  */
    /* renamed from: F */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void m14062F(String str, XMPPManager.HandleType handleType, boolean z8, Context context, Group group, String str2, boolean z9, C2904l c2904l, long j9, String str3, String str4, String str5, String str6) throws JSONException, Resources.NotFoundException, NumberFormatException {
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String strM14748J;
        if (str5 == null) {
            ULogUtility.m16659B(str, "stop query userInfo from server. statusCode = null");
            return;
        }
        if (!str5.equals("200")) {
            ULogUtility.m16659B(str, "stop query userInfo from server. statusCode = " + str5);
            return;
        }
        ULogUtility.m16659B(str, "stop query userInfo from server.");
        Intent intent = (handleType == XMPPManager.HandleType.HEART_BEAT && z8) ? new Intent(context, (Class<?>) QueryMessageActivity.class) : (handleType == XMPPManager.HandleType.GCM && z8) ? new Intent(context, (Class<?>) QueryMessageActivity.class) : new Intent(context, (Class<?>) NotificationActivity.class);
        intent.setFlags(335544320);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", group);
        bundle.putSerializable("handleType", handleType);
        bundle.putString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, str2);
        intent.putExtras(bundle);
        Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str6));
        if (friendM20184f == null) {
            ULogUtility.m16659B(str, "get userInfo from server fail. jsonstr = " + str6);
            return;
        }
        String string = context.getResources().getString(R.string.notification_default_string);
        if (z9) {
            str7 = group.f13717d;
            switch (C2841h.f12446a[c2904l.m14389D().ordinal()]) {
                case 2:
                    if (!c2904l.m14397L()) {
                        if (!c2904l.m14448w().equals(Message.Type.chat)) {
                            string = String.format(context.getResources().getString(R.string.notification_chat_user_say), friendM20184f.m15621b(), c2904l.m14420i());
                            break;
                        } else {
                            string = c2904l.m14420i();
                            break;
                        }
                    }
                    break;
                case 3:
                case 4:
                    if (!c2904l.m14397L()) {
                        string = String.format(context.getResources().getString(R.string.notification_chat_user_send_photo), friendM20184f.m15621b());
                        break;
                    }
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    if (!c2904l.m14397L()) {
                        string = String.format(context.getResources().getString(R.string.notification_chat_user_send_sticker), friendM20184f.m15621b());
                        break;
                    }
                    break;
                case 9:
                    string = String.format(context.getResources().getString(R.string.notification_chat_user_comment_photo), friendM20184f.m15621b());
                    break;
                case 10:
                    try {
                        JSONObject jSONObject = new JSONObject(c2904l.m14420i());
                        String string2 = jSONObject.getString("albumId");
                        String string3 = jSONObject.getString("albumName");
                        String string4 = jSONObject.getString("numberUpload");
                        if (string2 != null && string3 != null && string4 != null) {
                            string = String.format(context.getResources().getQuantityString(R.plurals.notification_chat_user_share_photos, Integer.parseInt(string4)), friendM20184f.m15621b(), string4, string3);
                            break;
                        }
                    } catch (JSONException e9) {
                        Log.d("NotificationHelper", Log.getStackTraceString(e9));
                        break;
                    }
                    break;
                case 11:
                    C2898i c2898i = (C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event");
                    if (c2898i != null) {
                        Map<String, String> mapM14373e = c2898i.m14373e();
                        String str12 = mapM14373e.get("eventType");
                        if (str12.equals("group.member.created") && !group.f13716c.equals("Dual")) {
                            string = String.format(context.getResources().getString(R.string.notification_chat_invite_user_into_group), friendM20184f.m15621b(), group.f13717d);
                            str11 = null;
                        } else if (str12.equals("bulletin.topic.created") || str12.equals("bulletin.post.created")) {
                            String str13 = str12.equals("bulletin.topic.created") ? String.format(context.getResources().getString(R.string.notification_topic_created), friendM20184f.m15621b(), group.f13717d) : String.format(context.getResources().getString(R.string.notification_post_created), friendM20184f.m15621b(), group.f13717d);
                            str11 = mapM14373e.get("topicId");
                            TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str11));
                            if (topicObjM14984n != null && topicObjM14984n.f13106u.equals("Topic")) {
                                bundle.putInt("chatDialogTabType", ChatDialogActivity.Tab.Bulletins.ordinal());
                            }
                            bundle.putString("intent_topic", str11);
                            intent.putExtras(bundle);
                            string = str13;
                        }
                        str8 = str11;
                        if (Globals.m7388i0().m7534d2() || !(handleType == XMPPManager.HandleType.HEART_BEAT || handleType == XMPPManager.HandleType.GCM)) {
                            str9 = string;
                            str10 = str7;
                        } else {
                            str9 = "(" + handleType + ")" + string;
                            str10 = "(" + handleType + ")" + str7;
                        }
                        if (!Globals.m7388i0().m7443J1()) {
                            if (Globals.m7388i0().m7399A1(friendM20184f, str9)) {
                                m14081Y(group, str9, handleType, friendM20184f.f13647e, group.f13716c.equals("Dual") ? null : str10, str8, str2, j9);
                                m14072P(group, str10, str9, str9, intent, friendM20184f.f13647e, handleType);
                                break;
                            }
                        } else {
                            m14081Y(group, str9, handleType, friendM20184f.f13647e, group.f13716c.equals("Dual") ? null : str10, str8, str2, j9);
                            m14072P(group, str10, str9, str9, intent, friendM20184f.f13647e, handleType);
                            break;
                        }
                    }
                    break;
                case 14:
                    if (!c2904l.m14397L()) {
                        string = String.format(context.getResources().getString(R.string.notification_chat_user_send_video), friendM20184f.m15621b());
                        break;
                    }
                    break;
                case 15:
                case 16:
                case 17:
                    if (!c2904l.m14397L() && (strM14748J = c2904l.m14449w0(String.valueOf(group.f13727n)).m14748J(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, FirebaseAnalytics.Param.CONTENT)) != null) {
                        string = String.format(context.getResources().getString(R.string.notification_chat_user_say), friendM20184f.m15621b(), strM14748J);
                        break;
                    }
                    break;
                case 18:
                    string = String.format(context.getResources().getString(R.string.notification_chat_user_send_location), friendM20184f.m15621b());
                    break;
                case 19:
                    string = String.format(context.getResources().getString(R.string.notification_chat_user_send_file), friendM20184f.m15621b());
                    break;
                case 23:
                    try {
                        String string5 = new JSONObject(c2904l.m14420i()).getString("callerId");
                        if (string5 != null && !string5.equals(Globals.m7388i0().m7587o0())) {
                            string = context.getResources().getString(R.string.call_miss);
                            break;
                        }
                    } catch (JSONException e10) {
                        e10.printStackTrace();
                        break;
                    }
                    break;
                case 24:
                    try {
                        String string6 = new JSONObject(c2904l.m14420i()).getString("replyText");
                        if (string6 != null) {
                            string = String.format(context.getResources().getString(R.string.notification_chat_user_say), friendM20184f.m15621b(), string6);
                            break;
                        }
                    } catch (JSONException e11) {
                        e11.printStackTrace();
                        break;
                    }
                    break;
                case 25:
                    try {
                        JSONObject jSONObject2 = new JSONObject(c2904l.m14420i());
                        String string7 = jSONObject2.getString("albumId");
                        String string8 = jSONObject2.getString("numberDelete");
                        if (string7 != null && string8 != null) {
                            int i9 = Integer.parseInt(string8);
                            string = String.format(context.getResources().getQuantityString(R.plurals.event_group_videos_delete, i9), friendM20184f.m15621b(), Integer.valueOf(i9));
                            break;
                        }
                    } catch (JSONException e12) {
                        Log.d("NotificationHelper", Log.getStackTraceString(e12));
                        break;
                    }
                    break;
                case 26:
                    try {
                        JSONObject jSONObject3 = new JSONObject(c2904l.m14420i());
                        String string9 = jSONObject3.getString("albumId");
                        String string10 = jSONObject3.getString("numberDelete");
                        if (string9 != null && string10 != null) {
                            int i10 = Integer.parseInt(string10);
                            string = String.format(context.getResources().getQuantityString(R.plurals.event_group_files_delete, i10), friendM20184f.m15621b(), Integer.valueOf(i10));
                            break;
                        }
                    } catch (JSONException e13) {
                        Log.d("NotificationHelper", Log.getStackTraceString(e13));
                        break;
                    }
                    break;
            }
            return;
        }
        str7 = "U";
        str8 = null;
        if (Globals.m7388i0().m7534d2()) {
            str9 = string;
            str10 = str7;
        }
        if (!Globals.m7388i0().m7443J1()) {
        }
    }

    /* renamed from: G */
    public static /* synthetic */ void m14063G(FriendsClient friendsClient, AbstractC6381r abstractC6381r, String str, String str2, String str3, String str4) {
        Friend friendM20184f;
        friendsClient.m15717U0();
        if ("200".equals(str3) && (friendM20184f = C5172p.m20184f(C5172p.m20195q(str4))) != null) {
            abstractC6381r.m24506d(friendM20184f);
        }
    }

    /* renamed from: H */
    public static /* synthetic */ void m14064H(FriendsClient friendsClient, AbstractC6381r abstractC6381r, String str, String str2, String str3, String str4) {
        Friend friendM20184f;
        friendsClient.m15717U0();
        if ("200".equals(str3) && (friendM20184f = C5172p.m20184f(C5172p.m20195q(str4))) != null) {
            abstractC6381r.m24506d(friendM20184f);
        }
    }

    /* renamed from: I */
    public static /* synthetic */ void m14065I(String str, Group group) throws Resources.NotFoundException {
        Context contextM7372O = Globals.m7372O();
        String string = contextM7372O.getResources().getString(R.string.call_miss);
        if (!Globals.m7388i0().m7507X1()) {
            str = contextM7372O.getResources().getString(R.string.u_app_name);
            string = contextM7372O.getResources().getString(R.string.privacy_password_notification_content);
        }
        ULogUtility.m16683s("NotificationHelper", "showPSTNTextWhenScreenLock, content = " + string);
        if (m14058B()) {
            ULogUtility.m16683s("NotificationHelper", "[showPSTNTextWhenScreenLock] start LockScreenActivity");
            Intent intent = new Intent(contextM7372O, (Class<?>) LockScreenActivity.class);
            intent.putExtra(FirebaseAnalytics.Param.CONTENT, string);
            intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str);
            intent.putExtra("PSTN", true);
            if (group != null) {
                intent.putExtra("Group", group);
            }
            intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            contextM7372O.startActivity(intent);
        }
    }

    /* renamed from: J */
    public static /* synthetic */ void m14066J(XMPPManager.HandleType handleType, String str, Group group, String str2, String str3, long j9, String str4, String str5) {
        Context applicationContext = Globals.m7388i0().getApplicationContext();
        boolean z8 = handleType == XMPPManager.HandleType.GCM;
        boolean z9 = handleType == XMPPManager.HandleType.HEART_BEAT;
        String string = (!Globals.m7388i0().m7507X1() || group.f13711J) ? applicationContext.getResources().getString(R.string.privacy_password_notification_content) : str;
        ULogUtility.m16683s("NotificationHelper", "showTextWhenScreenLock, content = " + str);
        if (m14058B()) {
            ULogUtility.m16683s("NotificationHelper", "start LockScreenActivity");
            Intent intent = new Intent(applicationContext, (Class<?>) LockScreenActivity.class);
            intent.putExtra(FirebaseAnalytics.Param.CONTENT, string);
            intent.putExtra("isGcm", z8);
            intent.putExtra("isHeartBeat", z9);
            intent.putExtra("avatar", str2);
            intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str3);
            intent.putExtra("time", j9);
            if (group != null) {
                intent.putExtra("Group", group);
            }
            if (str4 != null) {
                intent.putExtra("topicId", str4);
            }
            if (str5 != null) {
                intent.putExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, str5);
            }
            intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            applicationContext.startActivity(intent);
        }
    }

    /* renamed from: K */
    public static void m14067K(String str) {
        C2950b0.m14917p().m15193f(str, Long.valueOf(new Date().getTime()));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:100:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0633 A[PHI: r22
      0x0633: PHI (r22v12 java.lang.Object) = 
      (r22v0 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v2 java.lang.Object)
      (r22v4 java.lang.Object)
      (r22v13 java.lang.Object)
     binds: [B:158:0x05ff, B:155:0x05df, B:152:0x05bd, B:149:0x058f, B:43:0x01fc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0245 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0200 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x028b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x03c1  */
    /* renamed from: L */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m14068L(final Group group, final C2904l c2904l, final XMPPManager.HandleType handleType) {
        Intent intent;
        Intent intent2;
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        Object obj;
        String string8;
        String string9;
        String string10;
        String str;
        String str2;
        String str3;
        C2898i c2898i;
        String str4;
        String str5;
        MessageObj.MessageType messageTypeM14389D = c2904l.m14389D();
        int[] iArr = C2841h.f12446a;
        if (iArr[messageTypeM14389D.ordinal()] != 1) {
            final String string11 = handleType.toString();
            final String strM14446v = c2904l.m14446v();
            ULogUtility.m16659B(string11, "Call NotificationHelper.sendChatNotification. msgId: " + strM14446v);
            if (!CLUtility.m16433E1()) {
                ULogUtility.m16659B(string11, "Notification is disable.");
            }
            if (group.f13733t) {
                ULogUtility.m16659B(string11, "Group notification is disable. groupId: " + group.f13727n);
                return;
            }
            if (c2904l.m14422j().before(Globals.m7388i0().m7502W0())) {
                ULogUtility.m16659B(string11, "Not notify (Message time is < Registration Time). msgId: " + strM14446v);
                return;
            }
            boolean zM14396K = c2904l.m14396K();
            String strM14446v2 = c2904l.m14446v();
            XMPPManager.HandleType handleType2 = XMPPManager.HandleType.HEART_BEAT;
            HasNotifiedResultType hasNotifiedResultTypeM14110z = m14110z(zM14396K, strM14446v2, handleType == handleType2);
            if (hasNotifiedResultTypeM14110z.equals(HasNotifiedResultType.HAS_NOTIFIED)) {
                ULogUtility.m16659B(string11, "Not notify (This message was notified). msgId: " + strM14446v);
                return;
            }
            if (hasNotifiedResultTypeM14110z.equals(HasNotifiedResultType.IN_DATABASE)) {
                ULogUtility.m16659B(string11, "Not notify (This message is in database). msgId: " + strM14446v);
                return;
            }
            m14067K(c2904l.m14446v());
            ULogUtility.m16659B(string11, "start notify. msgId: " + strM14446v);
            final boolean zM7507X1 = Globals.m7388i0().m7507X1();
            final boolean zEquals = Globals.m7388i0().m7483S0().equals("v1");
            final Context applicationContext = Globals.m7388i0().getApplicationContext();
            String strM14430n = c2904l.m14430n();
            Friend friendM15003C = C2950b0.m14899A().m15003C(strM14430n);
            final long time = c2904l.m14422j().getTime();
            if (friendM15003C == null) {
                ULogUtility.m16659B(string11, "start query userInfo from server. userId=" + strM14430n);
                FriendsClient friendsClient = new FriendsClient();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("userId", strM14430n));
                friendsClient.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.b0
                    @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                    /* renamed from: a */
                    public final void mo134a(String str6, String str7, String str8, String str9) throws JSONException, Resources.NotFoundException, NumberFormatException {
                        NotificationHelper.m14062F(string11, handleType, zEquals, applicationContext, group, strM14446v, zM7507X1, c2904l, time, str6, str7, str8, str9);
                    }
                });
                return;
            }
            ULogUtility.m16659B(string11, "get userInfo from db. userId=" + strM14430n + " isV1=" + zEquals);
            if (handleType == handleType2 && zEquals) {
                intent2 = new Intent(applicationContext, (Class<?>) QueryMessageActivity.class);
            } else {
                if (handleType != XMPPManager.HandleType.GCM || !zEquals) {
                    intent = new Intent(applicationContext, (Class<?>) NotificationActivity.class);
                    intent.setFlags(335544320);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Group", group);
                    bundle.putSerializable("handleType", handleType);
                    bundle.putString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, strM14446v);
                    intent.putExtras(bundle);
                    String str6 = group.f13717d;
                    String string12 = applicationContext.getResources().getString(R.string.notification_default_string);
                    switch (iArr[c2904l.m14389D().ordinal()]) {
                        case 2:
                            obj = "Dual";
                            if (c2904l.m14397L()) {
                                string = string12;
                            } else {
                                string = c2904l.m14448w().equals(Message.Type.chat) ? c2904l.m14420i() : String.format(applicationContext.getResources().getString(R.string.notification_chat_user_say), friendM15003C.m15621b(), c2904l.m14420i());
                            }
                            str2 = null;
                            if (!zM7507X1) {
                                string = applicationContext.getResources().getString(R.string.notification_default_string);
                            }
                            if (Globals.m7388i0().m7534d2() || !(handleType == XMPPManager.HandleType.HEART_BEAT || handleType == XMPPManager.HandleType.GCM)) {
                                str4 = str6;
                                str5 = string;
                            } else {
                                str5 = "(" + handleType + ")" + string;
                                str4 = "(" + handleType + ")" + str6;
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                                if (Globals.m7388i0().m7399A1(friendM15003C, str5)) {
                                    m14081Y(group, str5, handleType, friendM15003C.f13647e, group.f13716c.equals(obj) ? null : str4, str2, strM14446v, time);
                                    m14072P(group, str4, str5, str5, intent, friendM15003C.f13647e, handleType);
                                    break;
                                }
                            } else {
                                Intent intent3 = intent;
                                m14081Y(group, str5, handleType, friendM15003C.f13647e, group.f13716c.equals(obj) ? null : str4, str2, strM14446v, time);
                                m14072P(group, str4, str5, str5, intent3, friendM15003C.f13647e, handleType);
                                break;
                            }
                            break;
                        case 3:
                        case 4:
                            obj = "Dual";
                            if (!c2904l.m14397L()) {
                                string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_send_photo), friendM15003C.m15621b());
                            }
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                                str4 = str6;
                                str5 = string;
                                break;
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            obj = "Dual";
                            if (!c2904l.m14397L()) {
                                string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_send_sticker), friendM15003C.m15621b());
                            }
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 9:
                            obj = "Dual";
                            string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_comment_photo), friendM15003C.m15621b());
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 10:
                            obj = "Dual";
                            try {
                                JSONObject jSONObject = new JSONObject(c2904l.m14420i());
                                string8 = jSONObject.getString("albumId");
                                string9 = jSONObject.getString("albumName");
                                string10 = jSONObject.getString("numberUpload");
                            } catch (JSONException e9) {
                                Log.d("NotificationHelper", Log.getStackTraceString(e9));
                                break;
                            }
                            if (string8 != null && string9 != null && string10 != null) {
                                string = String.format(applicationContext.getResources().getQuantityString(R.plurals.notification_chat_user_share_photos, Integer.parseInt(string10)), friendM15003C.m15621b(), string10, string9);
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            break;
                        case 11:
                            C2898i c2898i2 = (C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event");
                            if (c2898i2 != null) {
                                Map<String, String> mapM14373e = c2898i2.m14373e();
                                String str7 = mapM14373e.get("eventType");
                                if (str7.equals("group.member.created") && !group.f13716c.equals("Dual")) {
                                    string = String.format(applicationContext.getResources().getString(R.string.notification_chat_invite_user_into_group), friendM15003C.m15621b(), group.f13717d);
                                    obj = "Dual";
                                    str = null;
                                } else if (str7.equals("bulletin.topic.created") || str7.equals("bulletin.post.created")) {
                                    str = mapM14373e.get("topicId");
                                    obj = "Dual";
                                    TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
                                    if (topicObjM14984n == null || topicObjM14984n.m14857w()) {
                                        String str8 = str7.equals("bulletin.topic.created") ? String.format(applicationContext.getResources().getString(R.string.notification_topic_created), friendM15003C.m15621b(), group.f13717d) : String.format(applicationContext.getResources().getString(R.string.notification_post_created), friendM15003C.m15621b(), group.f13717d);
                                        bundle.putInt("chatDialogTabType", ChatDialogActivity.Tab.Bulletins.ordinal());
                                        bundle.putString(Constants.FirelogAnalytics.PARAM_TOPIC, str);
                                        intent.putExtras(bundle);
                                        string = str8;
                                    }
                                }
                                str2 = str;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            break;
                        case 12:
                            if (!c2904l.m14397L()) {
                                string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_receive_voice_msg), friendM15003C.m15621b());
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            obj = "Dual";
                            string = string12;
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 13:
                            if (!c2904l.m14397L()) {
                                string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_send_user_contact), friendM15003C.m15621b());
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            obj = "Dual";
                            string = string12;
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 14:
                            if (!c2904l.m14397L()) {
                                string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_send_video), friendM15003C.m15621b());
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            obj = "Dual";
                            string = string12;
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 15:
                        case 16:
                        case 17:
                            if (!c2904l.m14397L()) {
                                String strM14748J = c2904l.m14449w0(String.valueOf(group.f13727n)).m14748J(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, FirebaseAnalytics.Param.CONTENT);
                                string = strM14748J != null ? String.format(applicationContext.getResources().getString(R.string.notification_chat_user_say), friendM15003C.m15621b(), strM14748J) : string12;
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            obj = "Dual";
                            string = string12;
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 18:
                            string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_send_location), friendM15003C.m15621b());
                            obj = "Dual";
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 19:
                            string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_send_file), friendM15003C.m15621b());
                            obj = "Dual";
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 20:
                        case 21:
                            if (c2904l.m14389D().equals(MessageObj.MessageType.Poll)) {
                                c2898i = (C2898i) c2904l.m14426l("poll", "U");
                                str3 = String.format(applicationContext.getResources().getString(R.string.S_added_a_new_poll_in_S), friendM15003C.m15621b(), group.f13717d);
                            } else if (c2904l.m14389D().equals(MessageObj.MessageType.PollPost)) {
                                c2898i = (C2898i) c2904l.m14426l("pollPost", "U");
                                str3 = String.format(applicationContext.getResources().getString(R.string.S_replied_to_a_poll_in_S), friendM15003C.m15621b(), group.f13717d);
                            } else {
                                str3 = string12;
                                c2898i = null;
                            }
                            if (c2898i != null) {
                                String str9 = c2898i.m14373e().get("topicId");
                                if (str9 != null) {
                                    bundle.putString(Constants.FirelogAnalytics.PARAM_TOPIC, str9);
                                    intent.putExtras(bundle);
                                }
                                str2 = str9;
                                string = str3;
                                obj = "Dual";
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            } else {
                                string = str3;
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            break;
                        case 22:
                            string = ((C2898i) c2904l.m14426l("reportToAdmin", "urn:xmpp:reportadmin:0")).m14374f();
                            obj = "Dual";
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 23:
                            try {
                                JSONObject jSONObject2 = new JSONObject(c2904l.m14420i());
                                string4 = jSONObject2.getString("callerId");
                                string5 = jSONObject2.getString("statusV2");
                                if (C5170o0.m20170e(string5)) {
                                    string5 = jSONObject2.getString("status");
                                }
                            } catch (JSONException e10) {
                                e10.printStackTrace();
                                break;
                            }
                            if (!string5.equals("meeting") && string4 != null && !string4.equals(Globals.m7388i0().m7587o0())) {
                                string = applicationContext.getResources().getString(R.string.call_miss);
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            break;
                        case 24:
                            try {
                                String string13 = new JSONObject(c2904l.m14420i()).getString("replyText");
                                if (string13 != null) {
                                    string = String.format(applicationContext.getResources().getString(R.string.notification_chat_user_say), friendM15003C.m15621b(), string13);
                                }
                                obj = "Dual";
                            } catch (JSONException e11) {
                                e11.printStackTrace();
                                break;
                            }
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                        case 25:
                            try {
                                JSONObject jSONObject3 = new JSONObject(c2904l.m14420i());
                                string6 = jSONObject3.getString("albumId");
                                string7 = jSONObject3.getString("numberDelete");
                            } catch (JSONException e12) {
                                Log.d("NotificationHelper", Log.getStackTraceString(e12));
                                break;
                            }
                            if (string6 != null && string7 != null) {
                                int i9 = Integer.parseInt(string7);
                                string = String.format(applicationContext.getResources().getQuantityString(R.plurals.event_group_videos_delete, i9), friendM15003C.m15621b(), Integer.valueOf(i9));
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            break;
                        case 26:
                            try {
                                JSONObject jSONObject4 = new JSONObject(c2904l.m14420i());
                                string2 = jSONObject4.getString("albumId");
                                string3 = jSONObject4.getString("numberDelete");
                            } catch (JSONException e13) {
                                Log.d("NotificationHelper", Log.getStackTraceString(e13));
                                break;
                            }
                            if (string2 != null && string3 != null) {
                                int i10 = Integer.parseInt(string3);
                                string = String.format(applicationContext.getResources().getQuantityString(R.plurals.event_group_files_delete, i10), friendM15003C.m15621b(), Integer.valueOf(i10));
                                obj = "Dual";
                                str2 = null;
                                if (!zM7507X1) {
                                }
                                if (Globals.m7388i0().m7534d2()) {
                                }
                                if (!Globals.m7388i0().m7443J1()) {
                                }
                            }
                            break;
                        default:
                            obj = "Dual";
                            string = string12;
                            str2 = null;
                            if (!zM7507X1) {
                            }
                            if (Globals.m7388i0().m7534d2()) {
                            }
                            if (!Globals.m7388i0().m7443J1()) {
                            }
                            break;
                    }
                }
                intent2 = new Intent(applicationContext, (Class<?>) QueryMessageActivity.class);
            }
            intent = intent2;
            intent.setFlags(335544320);
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("Group", group);
            bundle2.putSerializable("handleType", handleType);
            bundle2.putString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, strM14446v);
            intent.putExtras(bundle2);
            String str62 = group.f13717d;
            String string122 = applicationContext.getResources().getString(R.string.notification_default_string);
            switch (iArr[c2904l.m14389D().ordinal()]) {
            }
        }
    }

    /* renamed from: M */
    public static void m14069M(String str, String str2, String str3, Intent intent, String str4, XMPPManager.HandleType handleType) {
        boolean z8 = !Globals.m7388i0().m7507X1();
        m14099o(z8, str4, new C2836c(str, str2, str3, intent, z8, handleType));
    }

    /* renamed from: N */
    public static void m14070N(C2904l c2904l, XMPPManager.HandleType handleType) {
        final C2834a c2834a = new C2834a(c2904l, handleType);
        String strM14430n = c2904l.m14430n();
        Friend friendM15001A = C2950b0.m14899A().m15001A(strM14430n);
        if (friendM15001A != null) {
            c2834a.m24506d(friendM15001A);
            return;
        }
        final FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", strM14430n));
        friendsClient.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.g0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                NotificationHelper.m14063G(friendsClient, c2834a, str, str2, str3, str4);
            }
        });
    }

    @TargetApi(29)
    /* renamed from: O */
    public static void m14071O(Group group, String str, String str2, Intent intent, String str3) {
        m14099o(false, str3, new C2837d(intent, str, str3, str2));
    }

    /* renamed from: P */
    public static void m14072P(Group group, String str, String str2, String str3, Intent intent, String str4, XMPPManager.HandleType handleType) {
        boolean z8 = !Globals.m7388i0().m7507X1() || group.f13711J;
        m14099o(z8, str4, new C2838e(str, str2, str3, group, intent, z8, handleType));
    }

    /* renamed from: Q */
    public static void m14073Q(Group group, String str) {
        boolean z8 = !Globals.m7388i0().m7507X1();
        m14099o(z8, null, new C2839f(group, str, z8));
    }

    /* renamed from: R */
    public static void m14074R(Intent intent) {
        Context contextM7372O = Globals.m7372O();
        int i9 = Build.VERSION.SDK_INT;
        PendingIntent activity = PendingIntent.getActivity(contextM7372O, 0, intent, i9 >= 31 ? 201326592 : 134217728);
        String strM14108x = m14108x(intent);
        String string = contextM7372O.getString(R.string.u_app_name);
        C6232g.c cVarM23828g = new C6232g.c().m23829h(string).m23828g(strM14108x);
        String strM14101q = i9 >= 29 ? m14101q() : m14104t();
        if (f12417a) {
            strM14101q = m14103s();
        }
        Notification notificationM23837b = new C6232g.e(contextM7372O, strM14101q).m23860z(cVarM23828g).m23846l(string).m23845k(strM14108x).m23858x(R.drawable.ic_stat_name_s).m23840f(true).m23832A(strM14108x).m23843i(Globals.m7388i0().m7423F0()).m23841g("alarm").m23850p(activity, true).m23837b();
        if (!f12417a) {
            notificationM23837b.flags |= 4;
        }
        ((NotificationManager) contextM7372O.getSystemService("notification")).notify(5, notificationM23837b);
    }

    /* renamed from: S */
    public static void m14075S(C2904l c2904l, XMPPManager.HandleType handleType) {
        HasNotifiedResultType hasNotifiedResultTypeM14110z = m14110z(c2904l.m14396K(), c2904l.m14446v(), handleType == XMPPManager.HandleType.HEART_BEAT);
        if (hasNotifiedResultTypeM14110z.equals(HasNotifiedResultType.HAS_NOTIFIED) || hasNotifiedResultTypeM14110z.equals(HasNotifiedResultType.IN_DATABASE)) {
            return;
        }
        m14067K(c2904l.m14446v());
        final C2835b c2835b = new C2835b(c2904l, handleType);
        String strM14430n = c2904l.m14430n();
        Friend friendM15001A = C2950b0.m14899A().m15001A(strM14430n);
        if (friendM15001A != null) {
            c2835b.m24506d(friendM15001A);
            return;
        }
        final FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", strM14430n));
        friendsClient.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.chat.d0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                NotificationHelper.m14064H(friendsClient, c2835b, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: T */
    public static void m14076T() {
        Globals globalsM7388i0 = Globals.m7388i0();
        Intent intent = new Intent(globalsM7388i0, (Class<?>) SplashActivity.class);
        intent.setFlags(4194304);
        C6232g.e eVarM23855u = new C6232g.e(globalsM7388i0, m14103s()).m23858x(R.drawable.ic_stat_name_s).m23843i(Globals.m7388i0().m7423F0()).m23844j(PendingIntent.getActivity(globalsM7388i0, 0, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728)).m23846l(globalsM7388i0.getString(R.string.u_app_name)).m23845k(globalsM7388i0.getString(R.string.clm_meeting_in_progress)).m23856v(2).m23840f(false).m23855u(true);
        NotificationManager notificationManager = (NotificationManager) globalsM7388i0.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.notify(3, eVarM23855u.m23837b());
        }
    }

    /* renamed from: U */
    public static void m14077U(String str) {
        Globals globalsM7388i0 = Globals.m7388i0();
        Intent intent = new Intent(globalsM7388i0, (Class<?>) SplashActivity.class);
        intent.setFlags(4194304);
        C6232g.e eVarM23855u = new C6232g.e(globalsM7388i0, m14103s()).m23858x(R.drawable.ic_stat_name_s).m23843i(Globals.m7388i0().m7423F0()).m23844j(PendingIntent.getActivity(globalsM7388i0, 0, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728)).m23846l(globalsM7388i0.getString(R.string.u_app_name)).m23845k(str).m23856v(2).m23840f(false).m23855u(true);
        NotificationManager notificationManager = (NotificationManager) globalsM7388i0.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.notify(3, eVarM23855u.m23837b());
        }
    }

    /* renamed from: V */
    public static void m14078V(String str) {
        Globals globalsM7388i0 = Globals.m7388i0();
        Intent intent = new Intent(globalsM7388i0, (Class<?>) SplashActivity.class);
        intent.setFlags(4194304);
        C6232g.e eVarM23840f = new C6232g.e(globalsM7388i0, m14104t()).m23858x(R.drawable.ic_stat_name_s).m23843i(Globals.m7388i0().m7423F0()).m23844j(PendingIntent.getActivity(globalsM7388i0, 0, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728)).m23846l(globalsM7388i0.getString(R.string.u_app_name)).m23845k(str).m23856v(2).m23840f(true);
        NotificationManager notificationManager = (NotificationManager) globalsM7388i0.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.notify(0, eVarM23840f.m23837b());
        }
    }

    /* renamed from: W */
    public static void m14079W(final Group group, final String str) {
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.chat.e0
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                NotificationHelper.m14065I(str, group);
            }
        });
    }

    /* renamed from: X */
    public static void m14080X(Group group, String str, XMPPManager.HandleType handleType, String str2, String str3, long j9) {
        m14081Y(group, str, handleType, str2, str3, null, null, j9);
    }

    /* renamed from: Y */
    public static void m14081Y(final Group group, final String str, final XMPPManager.HandleType handleType, final String str2, final String str3, final String str4, final String str5, final long j9) {
        C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.chat.a0
            @Override // java.lang.Runnable
            public final void run() {
                NotificationHelper.m14066J(handleType, str, group, str2, str3, j9, str4, str5);
            }
        });
    }

    /* renamed from: Z */
    public static void m14082Z() {
        NotificationManager notificationManager = (NotificationManager) Globals.m7372O().getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannels(m14105u());
        }
        m14095k();
    }

    /* renamed from: a0 */
    public static void m14084a0() {
        m14086b0(Globals.m7388i0().m7433H0(false));
    }

    /* renamed from: b0 */
    public static void m14086b0(Uri uri) {
        NotificationManager notificationManager = (NotificationManager) Globals.m7372O().getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            String strM14101q = m14101q();
            int iM7628v1 = Globals.m7388i0().m7628v1();
            AudioAttributes audioAttributesM14106v = uri == null ? null : m14106v();
            for (NotificationChannel notificationChannel : notificationManager.getNotificationChannels()) {
                NotificationChannel notificationChannelM14100p = m14100p(notificationChannel, iM7628v1);
                if (notificationChannel.getId().equals(strM14101q)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("android.resource");
                    sb.append(File.pathSeparator);
                    String str = File.separator;
                    sb.append(str);
                    sb.append(str);
                    sb.append(Globals.m7388i0().getPackageName());
                    sb.append(str);
                    sb.append(R.raw.call);
                    Uri uri2 = Uri.parse(sb.toString());
                    if (uri == null) {
                        uri2 = null;
                    }
                    notificationChannelM14100p.setSound(uri2, audioAttributesM14106v);
                } else {
                    notificationChannelM14100p.setSound(uri, audioAttributesM14106v);
                }
                notificationManager.createNotificationChannel(notificationChannelM14100p);
                notificationManager.deleteNotificationChannel(notificationChannel.getId());
            }
        }
    }

    /* renamed from: k */
    public static void m14095k() {
        NotificationChannel notificationChannel;
        NotificationManager notificationManager = (NotificationManager) Globals.m7372O().getSystemService(NotificationManager.class);
        if (notificationManager == null || (notificationChannel = notificationManager.getNotificationChannel(m14101q())) == null) {
            return;
        }
        Uri sound = notificationChannel.getSound();
        StringBuilder sb = new StringBuilder();
        sb.append("android.resource");
        sb.append(File.pathSeparator);
        String str = File.separator;
        sb.append(str);
        sb.append(str);
        sb.append(Globals.m7388i0().getPackageName());
        sb.append(str);
        sb.append(R.raw.call);
        Uri uri = Uri.parse(sb.toString());
        if (sound == null || sound.equals(uri)) {
            return;
        }
        ULogUtility.m16670f("NotificationHelper", "update notification channel due to meeting incoming call sound is wrong");
        m14084a0();
    }

    /* renamed from: l */
    public static void m14096l() {
        NotificationManager notificationManager = (NotificationManager) Globals.m7372O().getSystemService("notification");
        if (notificationManager != null) {
            ULogUtility.m16659B("NotificationHelper", "clearMeetingCallNotification");
            notificationManager.cancel(4);
        }
        f12417a = false;
    }

    /* renamed from: m */
    public static void m14097m() {
        new C2840g().start();
    }

    /* renamed from: n */
    public static void m14098n() {
        NotificationManager notificationManager = (NotificationManager) Globals.m7372O().getSystemService("notification");
        if (notificationManager != null) {
            ULogUtility.m16659B("NotificationHelper", "clearReminderMeetingCallNotification");
            notificationManager.cancel(5);
        }
    }

    /* renamed from: o */
    public static void m14099o(final boolean z8, final String str, final AbstractC6381r<Bitmap, Void> abstractC6381r) {
        C6385v.m24526d(new Runnable() { // from class: com.cyberlink.you.chat.c0
            @Override // java.lang.Runnable
            public final void run() {
                NotificationHelper.m14061E(z8, str, abstractC6381r);
            }
        });
    }

    /* renamed from: p */
    public static NotificationChannel m14100p(NotificationChannel notificationChannel, int i9) {
        String id = notificationChannel.getId();
        NotificationChannel notificationChannel2 = new NotificationChannel(id.substring(0, id.lastIndexOf("#") + 1) + i9, notificationChannel.getName(), notificationChannel.getImportance());
        notificationChannel2.setDescription(notificationChannel.getDescription());
        notificationChannel2.setBypassDnd(notificationChannel.canBypassDnd());
        notificationChannel2.setLockscreenVisibility(notificationChannel.getLockscreenVisibility());
        notificationChannel2.setSound(notificationChannel.getSound(), notificationChannel.getAudioAttributes());
        notificationChannel2.enableLights(notificationChannel.shouldShowLights());
        notificationChannel2.setLightColor(notificationChannel.getLightColor());
        notificationChannel2.setVibrationPattern(notificationChannel.getVibrationPattern());
        notificationChannel2.enableVibration(notificationChannel.shouldVibrate());
        notificationChannel2.setShowBadge(notificationChannel.canShowBadge());
        notificationChannel2.setGroup(notificationChannel.getGroup());
        return notificationChannel2;
    }

    /* renamed from: q */
    public static String m14101q() {
        return "channel_id_meeting#" + Globals.m7388i0().m7428G0();
    }

    /* renamed from: r */
    public static String m14102r() {
        return "channel_id_message#" + Globals.m7388i0().m7428G0();
    }

    /* renamed from: s */
    public static String m14103s() {
        return "channel_id_others#" + Globals.m7388i0().m7428G0();
    }

    /* renamed from: t */
    public static String m14104t() {
        return "channel_id_system#" + Globals.m7388i0().m7428G0();
    }

    /* renamed from: u */
    public static List<NotificationChannel> m14105u() {
        ArrayList arrayList = new ArrayList();
        Context contextM7372O = Globals.m7372O();
        Globals globalsM7388i0 = Globals.m7388i0();
        Uri uriM7433H0 = globalsM7388i0.m7433H0(false);
        AudioAttributes audioAttributesM14106v = uriM7433H0 == null ? null : m14106v();
        boolean zM7557i2 = globalsM7388i0.m7557i2();
        NotificationChannel notificationChannel = new NotificationChannel(m14102r(), contextM7372O.getString(R.string.channel_name_message), 4);
        notificationChannel.setSound(uriM7433H0, audioAttributesM14106v);
        notificationChannel.enableVibration(zM7557i2);
        arrayList.add(notificationChannel);
        NotificationChannel notificationChannel2 = new NotificationChannel(m14104t(), contextM7372O.getString(R.string.channel_name_system), 3);
        notificationChannel2.setSound(uriM7433H0, audioAttributesM14106v);
        notificationChannel2.enableVibration(zM7557i2);
        arrayList.add(notificationChannel2);
        if (Build.VERSION.SDK_INT >= 29) {
            StringBuilder sb = new StringBuilder();
            sb.append("android.resource");
            sb.append(File.pathSeparator);
            String str = File.separator;
            sb.append(str);
            sb.append(str);
            sb.append(Globals.m7388i0().getPackageName());
            sb.append(str);
            sb.append(R.raw.call);
            Uri uri = Uri.parse(sb.toString());
            NotificationChannel notificationChannel3 = new NotificationChannel(m14101q(), contextM7372O.getString(R.string.channel_name_meeting_call), 4);
            notificationChannel3.setSound(uri, audioAttributesM14106v);
            notificationChannel3.setVibrationPattern(new long[]{0, 1000, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS});
            notificationChannel3.enableVibration(true);
            arrayList.add(notificationChannel3);
        }
        NotificationChannel notificationChannel4 = new NotificationChannel(m14103s(), contextM7372O.getString(R.string.channel_name_others), 2);
        notificationChannel4.setShowBadge(false);
        notificationChannel4.enableVibration(false);
        arrayList.add(notificationChannel4);
        return arrayList;
    }

    /* renamed from: v */
    public static AudioAttributes m14106v() {
        return new AudioAttributes.Builder().setUsage(5).setContentType(4).build();
    }

    /* renamed from: w */
    public static Bundle m14107w(Bundle bundle, String str, String str2, String str3) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("action", bundle.getString("action"));
        bundle2.putString("meetingId", bundle.getString("meetingId"));
        bundle2.putString("meetingMServerAddress", bundle.getString("meetingMServerAddress"));
        bundle2.putString("meetingMServerToken", bundle.getString("meetingMServerToken"));
        bundle2.putString("type", bundle.getString("type"));
        bundle2.putBoolean("isPreJoinMeeting", bundle.getBoolean("isPreJoinMeeting"));
        bundle2.putBoolean("isFromNotification", true);
        bundle2.putString("notificationName", str2);
        bundle2.putString("notificationAvatar", str3);
        bundle2.putString("notificationAction", str);
        return bundle2;
    }

    /* renamed from: x */
    public static String m14108x(Intent intent) {
        String stringExtra = intent.getStringExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
        String stringExtra2 = intent.getStringExtra("startTime");
        try {
            stringExtra = StringEscapeUtils.unescapeXml(stringExtra);
        } catch (Exception unused) {
        }
        long j9 = 0;
        if (TextUtils.isEmpty(stringExtra2)) {
            ULogUtility.m16676l("NotificationHelper", "[getRemindMeetingContent] startDate is empty string ");
        } else {
            try {
                long j10 = Long.parseLong(stringExtra2) - System.currentTimeMillis();
                if (j10 > 0) {
                    long j11 = (j10 / 1000) / 60;
                    j9 = j11 == 0 ? 1L : j11;
                }
            } catch (NumberFormatException e9) {
                ULogUtility.m16676l("NotificationHelper", "[getRemindMeetingContent] parse startDate fail : " + e9);
            }
        }
        return Globals.m7372O().getString(R.string.clm_meeting_remind_description, stringExtra, String.valueOf(j9));
    }

    /* renamed from: y */
    public static HasNotifiedResultType m14109y(String str, boolean z8) {
        return m14110z(false, str, z8);
    }

    /* renamed from: z */
    public static HasNotifiedResultType m14110z(boolean z8, String str, boolean z9) {
        HasNotifiedResultType hasNotifiedResultType = HasNotifiedResultType.NO_RECORD;
        if (z8) {
            return HasNotifiedResultType.HAS_NOTIFIED;
        }
        try {
            boolean zM15192e = C2950b0.m14917p().m15192e(str);
            if (zM15192e) {
                hasNotifiedResultType = HasNotifiedResultType.HAS_NOTIFIED;
            }
            if (z9 && Globals.m7388i0().m7483S0().equals("v1") && !zM15192e) {
                if (C2950b0.m14916o().m15179r(str) != null) {
                    return HasNotifiedResultType.IN_DATABASE;
                }
            }
        } catch (Exception e9) {
            Log.d("NotificationHelper", e9.getMessage());
        }
        return hasNotifiedResultType;
    }
}
