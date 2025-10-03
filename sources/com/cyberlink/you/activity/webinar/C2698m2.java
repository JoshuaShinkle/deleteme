package com.cyberlink.you.activity.webinar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.EmailConnectActivity;
import com.cyberlink.you.activity.JoinCLMWActivity;
import com.cyberlink.you.activity.webinar.C2698m2;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.plus.PlusShare;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.Message;
import com.perfectcorp.ycl.p040bc.model.network.NetworkCommon;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.sqlcipher.database.SQLiteDatabase;
import p057e5.C4757b;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5187v0;

/* renamed from: com.cyberlink.you.activity.webinar.m2 */
/* loaded from: classes.dex */
public class C2698m2 {

    /* renamed from: com.cyberlink.you.activity.webinar.m2$a */
    public class a extends PromisedTask.AbstractC4504d<Live> {

        /* renamed from: a */
        public final /* synthetic */ String f12158a;

        /* renamed from: b */
        public final /* synthetic */ String f12159b;

        /* renamed from: c */
        public final /* synthetic */ Activity f12160c;

        /* renamed from: d */
        public final /* synthetic */ String f12161d;

        /* renamed from: e */
        public final /* synthetic */ DialogC3133q f12162e;

        /* renamed from: f */
        public final /* synthetic */ boolean f12163f;

        /* renamed from: g */
        public final /* synthetic */ String f12164g;

        public a(String str, String str2, Activity activity, String str3, DialogC3133q dialogC3133q, boolean z8, String str4) {
            this.f12158a = str;
            this.f12159b = str2;
            this.f12160c = activity;
            this.f12161d = str3;
            this.f12162e = dialogC3133q;
            this.f12163f = z8;
            this.f12164g = str4;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Live live) {
            C2698m2.m13959k(this.f12158a, live, this.f12159b, this.f12160c, this.f12161d, this.f12162e, this.f12163f, this.f12164g);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            super.onError(i9);
            this.f12162e.dismiss();
            if (NetworkCommon.isNetworkConnected()) {
                Activity activity = this.f12160c;
                if (activity instanceof JoinCLMWActivity) {
                    ((JoinCLMWActivity) activity).m8683P0(R.string.ycl_live_or_meeting_not_exist);
                    return;
                } else {
                    C5187v0.m20267c(R.string.ycl_live_not_exist);
                    return;
                }
            }
            Activity activity2 = this.f12160c;
            if (activity2 instanceof JoinCLMWActivity) {
                ((JoinCLMWActivity) activity2).m8683P0(R.string.ycl_no_network_fail);
            } else {
                C5187v0.m20267c(R.string.ycl_no_network_fail);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.m2$b */
    public class b extends PromisedTask.AbstractC4504d<Message.JoinRoomResponse> {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f12165a;

        /* renamed from: b */
        public final /* synthetic */ Live f12166b;

        /* renamed from: c */
        public final /* synthetic */ Live.RecordedVideo f12167c;

        /* renamed from: d */
        public final /* synthetic */ Activity f12168d;

        /* renamed from: e */
        public final /* synthetic */ String f12169e;

        /* renamed from: f */
        public final /* synthetic */ String f12170f;

        /* renamed from: g */
        public final /* synthetic */ boolean f12171g;

        /* renamed from: h */
        public final /* synthetic */ String f12172h;

        /* renamed from: i */
        public final /* synthetic */ String f12173i;

        /* renamed from: j */
        public final /* synthetic */ String f12174j;

        public b(DialogC3133q dialogC3133q, Live live, Live.RecordedVideo recordedVideo, Activity activity, String str, String str2, boolean z8, String str3, String str4, String str5) {
            this.f12165a = dialogC3133q;
            this.f12166b = live;
            this.f12167c = recordedVideo;
            this.f12168d = activity;
            this.f12169e = str;
            this.f12170f = str2;
            this.f12171g = z8;
            this.f12172h = str3;
            this.f12173i = str4;
            this.f12174j = str5;
        }

        /* renamed from: d */
        public static /* synthetic */ void m13969d(DialogInterface dialogInterface, int i9) {
        }

        /* renamed from: e */
        public static /* synthetic */ void m13970e(Activity activity, String str, String str2, String str3, String str4, boolean z8, Message.JoinRoomResponse joinRoomResponse, DialogInterface dialogInterface, int i9) {
            C2698m2.m13953e(activity, str, str2, str3, str4, z8, null, null, null, joinRoomResponse.assistantStatus);
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public void onDone(final Message.JoinRoomResponse joinRoomResponse) throws NumberFormatException {
            this.f12165a.dismiss();
            if (NetworkLive.STATE.VIDEO_ON_DEMAND.equals(this.f12166b.status.state)) {
                Boolean bool = this.f12167c.expired;
                if (bool == null || bool.booleanValue()) {
                    C4757b.m18888e(this.f12168d, R.string.wbn_vod_has_expired);
                    return;
                } else {
                    C2698m2.m13964p(this.f12168d, this.f12167c.uri, this.f12169e, this.f12170f, this.f12171g);
                    return;
                }
            }
            String strM7391n1 = Globals.m7391n1();
            if (Globals.m7388i0().m7414D1() && Globals.m7380c2()) {
                strM7391n1 = strM7391n1.replace("(RD)", "").trim();
            }
            String strSubstring = strM7391n1.substring(0, strM7391n1.lastIndexOf(46));
            double d9 = Double.parseDouble(NetworkMessage.requiredVersion);
            double d10 = Double.parseDouble(NetworkMessage.recommendVersion);
            double d11 = Double.parseDouble(strSubstring);
            if (d11 >= d9 && d11 >= d10) {
                C2698m2.m13953e(this.f12168d, this.f12169e, this.f12172h, this.f12170f, this.f12173i, this.f12171g, null, null, null, joinRoomResponse.assistantStatus);
                return;
            }
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this.f12168d);
            if (d11 < d9) {
                builderM16382a.setMessage(R.string.required_update_version);
                builderM16382a.setNegativeButton(this.f12168d.getString(R.string.close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.n2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        C2698m2.b.m13969d(dialogInterface, i9);
                    }
                });
            } else {
                builderM16382a.setMessage(R.string.recommended_update_version);
                String string = this.f12168d.getString(R.string.wbn_qna_later);
                final Activity activity = this.f12168d;
                final String str = this.f12169e;
                final String str2 = this.f12172h;
                final String str3 = this.f12170f;
                final String str4 = this.f12173i;
                final boolean z8 = this.f12171g;
                builderM16382a.setNegativeButton(string, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.o2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        C2698m2.b.m13970e(activity, str, str2, str3, str4, z8, joinRoomResponse, dialogInterface, i9);
                    }
                });
            }
            String string2 = this.f12168d.getString(R.string.update_now);
            final Activity activity2 = this.f12168d;
            builderM16382a.setPositiveButton(string2, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.p2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    CLUtility.m16477P1(activity2);
                }
            });
            builderM16382a.setCancelable(false);
            builderM16382a.create().show();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            super.onError(i9);
            this.f12165a.dismiss();
            if (C2698m2.m13958j(this.f12168d, this.f12174j, i9, this.f12166b, this.f12169e)) {
                ((JoinCLMWActivity) this.f12168d).m8683P0(R.string.ycl_live_or_meeting_not_exist);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.m2$c */
    public class c extends PromisedTask.AbstractC4504d<String> {

        /* renamed from: a */
        public final /* synthetic */ Activity f12175a;

        /* renamed from: b */
        public final /* synthetic */ String f12176b;

        /* renamed from: c */
        public final /* synthetic */ String f12177c;

        /* renamed from: d */
        public final /* synthetic */ String f12178d;

        /* renamed from: e */
        public final /* synthetic */ String f12179e;

        /* renamed from: f */
        public final /* synthetic */ boolean f12180f;

        /* renamed from: g */
        public final /* synthetic */ String f12181g;

        /* renamed from: h */
        public final /* synthetic */ String f12182h;

        /* renamed from: i */
        public final /* synthetic */ String f12183i;

        /* renamed from: j */
        public final /* synthetic */ String f12184j;

        public c(Activity activity, String str, String str2, String str3, String str4, boolean z8, String str5, String str6, String str7, String str8) {
            this.f12175a = activity;
            this.f12176b = str;
            this.f12177c = str2;
            this.f12178d = str3;
            this.f12179e = str4;
            this.f12180f = z8;
            this.f12181g = str5;
            this.f12182h = str6;
            this.f12183i = str7;
            this.f12184j = str8;
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(String str) {
            C2698m2.m13963o(this.f12175a, this.f12176b, this.f12177c, this.f12178d, this.f12179e, this.f12180f, this.f12181g, this.f12182h, this.f12183i, this.f12184j);
        }
    }

    /* renamed from: e */
    public static void m13953e(Activity activity, String str, String str2, String str3, String str4, boolean z8, String str5, String str6, String str7, String str8) {
        if (TextUtils.isEmpty(str3)) {
            NetworkMessage.addAnonymousParticipantName(str).done(new c(activity, str, str2, str3, str4, z8, str5, str6, str7, str8));
        } else {
            m13963o(activity, str, str2, str3, str4, z8, str5, str6, str7, str8);
        }
    }

    /* renamed from: f */
    public static Date m13954f(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault()).parse(str);
        } catch (ParseException e9) {
            Log.d("WebinarUtility", "getDate error: " + e9);
            return null;
        }
    }

    /* renamed from: g */
    public static void m13955g(String str, long j9, String str2, Activity activity, String str3, DialogC3133q dialogC3133q, boolean z8, String str4) {
        final PromisedTask<?, ?, Live> live = NetworkLive.getLive(str, j9, str2, z8);
        dialogC3133q.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.cyberlink.you.activity.webinar.l2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                live.cancel(true);
            }
        });
        dialogC3133q.show();
        live.done(new a(str, str2, activity, str3, dialogC3133q, z8, str4));
    }

    /* renamed from: h */
    public static void m13956h(final Activity activity) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        builderM16382a.setTitle(R.string.clmw_join_registered_webinar_sign_in_required_title);
        builderM16382a.setMessage(R.string.clmw_join_registered_webinar_sign_in_required_message);
        builderM16382a.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.k2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                C2698m2.m13961m(activity, dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* renamed from: i */
    public static void m13957i(Activity activity, String str, int i9) {
        m13958j(activity, str, i9, null, "");
    }

    /* renamed from: j */
    public static boolean m13958j(Activity activity, String str, int i9, Live live, String str2) {
        switch (i9) {
            case 4031:
                C4757b.m18888e(activity, R.string.wbn_user_count_exceeded);
                return false;
            case 4032:
                if (Globals.m7388i0().m7464O1()) {
                    if (activity instanceof WebinarRegistrationActivity) {
                        C5187v0.m20267c(R.string.ycl_live_not_exist);
                        ULogUtility.m16684t(str, "[unexpected] after registerLive onDone, get joinRoom ErrorCode.RESPONSE_REGISTRATION_REQUIRED");
                        return false;
                    }
                    if (live != null) {
                        WebinarRegistrationActivity.m13900L(activity, live, str2);
                        return false;
                    }
                    C5187v0.m20267c(R.string.ycl_live_not_exist);
                    ULogUtility.m16684t(str, "[unexpected] after registerLive onDone, get joinRoom ErrorCode.RESPONSE_REGISTRATION_REQUIRED");
                    return false;
                }
                if (live.signInRequiredToRegister.booleanValue()) {
                    m13956h(activity);
                    return false;
                }
                if (C4509d.m18120b(live.registerURL)) {
                    return false;
                }
                try {
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(live.registerURL)));
                    return false;
                } catch (ActivityNotFoundException e9) {
                    C5154j.m20076j(e9);
                    return false;
                }
            case 4033:
                if (!(activity instanceof WebinarRegistrationActivity)) {
                    activity.startActivity(new Intent(activity, (Class<?>) WebinarRegistrationActivity.class));
                    return false;
                }
                ((WebinarRegistrationActivity) activity).m13918O();
                ULogUtility.m16684t(str, "[unexpected] after registerLive onDone, get joinRoom ErrorCode.RESPONSE_REGISTRATION_UNDERPROCESS");
                return false;
            default:
                if (activity instanceof JoinCLMWActivity) {
                    return true;
                }
                C5187v0.m20267c(R.string.ycl_live_not_exist);
                ULogUtility.m16676l(str, "after registerLive onDone, get joinRoom ErrorCode=" + i9);
                return false;
        }
    }

    /* renamed from: k */
    public static void m13959k(String str, Live live, String str2, Activity activity, String str3, DialogC3133q dialogC3133q, boolean z8, String str4) {
        String str5;
        boolean z9;
        Live.RecordedVideo recordedVideo = live.video;
        String str6 = live.liveId;
        if (Globals.m7388i0().m7464O1() || live.signInRequiredToRegister.booleanValue() || C4509d.m18120b(live.registerURL) || C4509d.m18120b(str2)) {
            str5 = str6;
            z9 = false;
        } else {
            z9 = true;
            str5 = str2;
        }
        NetworkMessage.joinRoom(str, str5, z8).done(new b(dialogC3133q, live, recordedVideo, activity, live.liveId, str, z8, z9 ? str2 : null, str4, str3));
    }

    /* renamed from: m */
    public static /* synthetic */ void m13961m(Activity activity, DialogInterface dialogInterface, int i9) {
        Intent intent = new Intent(activity.getApplicationContext(), (Class<?>) EmailConnectActivity.class);
        intent.addFlags(32768);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("registerType", 1);
        activity.startActivity(intent);
        activity.finish();
    }

    /* renamed from: n */
    public static void m13962n(Activity activity) {
        activity.overridePendingTransition(R.anim.bc_slide_in_right, R.anim.bc_slide_out_left);
    }

    /* renamed from: o */
    public static void m13963o(Activity activity, String str, String str2, String str3, String str4, boolean z8, String str5, String str6, String str7, String str8) {
        Intent intent = new Intent();
        intent.putExtra("token", str3);
        intent.putExtra("liveId", str);
        if (!C4509d.m18120b(str2)) {
            intent.putExtra("registryKey", str2);
        }
        if (!C5170o0.m20170e(str4)) {
            intent.putExtra("waterMark", str4);
        }
        intent.putExtra("isEnterprise", z8);
        intent.putExtra("userId", Globals.m7388i0().m7568k1());
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(activity);
        if (C5170o0.m20170e(str5)) {
            str5 = userInfoM16497V0 == null ? "" : userInfoM16497V0.f13789n;
        }
        intent.putExtra("corporate", str5);
        if (C5170o0.m20170e(str6)) {
            str6 = userInfoM16497V0 == null ? "" : userInfoM16497V0.f13790o;
        }
        intent.putExtra("department", str6);
        if (C5170o0.m20170e(str7)) {
            str7 = userInfoM16497V0 == null ? "" : userInfoM16497V0.f13791p;
        }
        intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, str7);
        if (!C5170o0.m20170e(str8)) {
            intent.putExtra("assistantStatus", str8);
        }
        intent.setClass(activity, LiveAudienceActivity.class);
        activity.startActivity(intent);
        m13962n(activity);
    }

    /* renamed from: p */
    public static void m13964p(Activity activity, Uri uri, String str, String str2, boolean z8) {
        if (uri == null || TextUtils.isEmpty(uri.toString())) {
            C4757b.m18888e(activity, R.string.ycl_vod_is_private);
            return;
        }
        Intent intent = new Intent();
        intent.setData(uri);
        intent.putExtra("token", str2);
        intent.putExtra("liveId", str);
        intent.putExtra("isEnterprise", z8);
        intent.setClass(activity, LiveReplayActivity.class);
        activity.startActivity(intent);
        m13962n(activity);
    }
}
