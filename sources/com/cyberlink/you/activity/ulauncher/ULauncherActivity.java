package com.cyberlink.you.activity.ulauncher;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.vending.billing.util.BillingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.SubscriptionInfo;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.BindCLAccountActivity;
import com.cyberlink.you.activity.SettingActivity;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.activity.ulauncher.C2556b;
import com.cyberlink.you.activity.ulauncher.C2559e;
import com.cyberlink.you.alarm.AlarmReceiver;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2981p0;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.p036ui.C3121e;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.widgetpool.common.CLFragmentTabHost;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005a4.C0032a;
import p015b4.C0681k;
import p055e3.C4714a;
import p075g3.C4857f;
import p116k4.C5154j;
import p116k4.C5164m0;
import p116k4.C5170o0;
import p116k4.C5173p0;
import p116k4.C5175q;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p116k4.C5192y;
import p116k4.C5194z;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6385v;
import p218v2.C6456d;

/* loaded from: classes.dex */
public class ULauncherActivity extends BaseFragmentActivity implements AbstractC2555a.a, C2556b.p, C2559e.l {

    /* renamed from: G */
    public static boolean f11452G = false;

    /* renamed from: d */
    public View f11460d;

    /* renamed from: e */
    public ImageView f11461e;

    /* renamed from: f */
    public View f11462f;

    /* renamed from: g */
    public View f11463g;

    /* renamed from: h */
    public TextView f11464h;

    /* renamed from: j */
    public TextView f11466j;

    /* renamed from: k */
    public AsyncTaskC2552l f11467k;

    /* renamed from: o */
    public TextView f11471o;

    /* renamed from: p */
    public TextView f11472p;

    /* renamed from: q */
    public AsyncTaskC2553m f11473q;

    /* renamed from: y */
    public BillingManager f11481y;

    /* renamed from: c */
    public int[] f11459c = {270, 160};

    /* renamed from: i */
    public CLFragmentTabHost f11465i = null;

    /* renamed from: l */
    public boolean f11468l = false;

    /* renamed from: m */
    public int f11469m = 0;

    /* renamed from: n */
    public final Object f11470n = new Object();

    /* renamed from: r */
    public int f11474r = -1;

    /* renamed from: s */
    public boolean f11475s = false;

    /* renamed from: t */
    public boolean f11476t = false;

    /* renamed from: u */
    public boolean f11477u = false;

    /* renamed from: v */
    public Handler f11478v = new Handler();

    /* renamed from: w */
    public boolean f11479w = false;

    /* renamed from: x */
    public Runnable f11480x = new Runnable() { // from class: g3.u3
        @Override // java.lang.Runnable
        public final void run() {
            this.f17023b.m12869M1();
        }
    };

    /* renamed from: z */
    public C3062b.b f11482z = new C2541a();

    /* renamed from: A */
    public C3062b.b f11453A = new C2542b();

    /* renamed from: B */
    public C3062b.b f11454B = new C2543c();

    /* renamed from: C */
    public final C2907m0.g f11455C = new C2907m0.g() { // from class: g3.d3
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f16853b.m12873O1();
        }
    };

    /* renamed from: D */
    public C5321e.m f11456D = new C5321e.m() { // from class: g3.e3
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f16870b.m12875P1(c2904l, map);
        }
    };

    /* renamed from: E */
    public C2907m0.h f11457E = new C2907m0.h() { // from class: g3.f3
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f16883b.m12871N1(z8);
        }
    };

    /* renamed from: F */
    public CLUtility.InterfaceC3139f f11458F = new C2545e();

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$a */
    public class C2541a implements C3062b.b {

        /* renamed from: a */
        public int f11483a = 0;

        public C2541a() {
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: a */
        public void mo9343a() {
            this.f11483a++;
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: b */
        public void mo9344b(int i9, int i10) {
            int i11 = this.f11483a + 1;
            this.f11483a = i11;
            if (i9 == i11) {
                AbstractC2555a abstractC2555aM12911B1 = ULauncherActivity.this.m12911B1();
                if (abstractC2555aM12911B1 instanceof C2559e) {
                    ((C2559e) abstractC2555aM12911B1).m13351v1();
                }
                this.f11483a = 0;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$b */
    public class C2542b implements C3062b.b {

        /* renamed from: a */
        public int f11485a = 0;

        public C2542b() {
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: a */
        public void mo9343a() {
            this.f11485a++;
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: b */
        public void mo9344b(int i9, int i10) {
            int i11 = this.f11485a + 1;
            this.f11485a = i11;
            if (i9 == i11) {
                AbstractC2555a abstractC2555aM12911B1 = ULauncherActivity.this.m12911B1();
                if (abstractC2555aM12911B1 instanceof C2559e) {
                    ((C2559e) abstractC2555aM12911B1).m13353w1(false);
                }
                this.f11485a = 0;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$c */
    public class C2543c implements C3062b.b {

        /* renamed from: a */
        public int f11487a = 0;

        public C2543c() {
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: a */
        public void mo9343a() {
            this.f11487a++;
        }

        @Override // com.cyberlink.you.friends.C3062b.b
        /* renamed from: b */
        public void mo9344b(int i9, int i10) {
            this.f11487a++;
            if (i9 == i10) {
                AbstractC2555a abstractC2555aM12911B1 = ULauncherActivity.this.m12911B1();
                if (abstractC2555aM12911B1 instanceof C2559e) {
                    ((C2559e) abstractC2555aM12911B1).m13357y1();
                }
                this.f11487a = 0;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$d */
    public class C2544d extends AbstractC6381r<Intent, Void> {
        public C2544d() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Intent intent) {
            if (intent != null) {
                ULauncherActivity.this.startActivity(intent);
            }
            ULauncherActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$e */
    public class C2545e implements CLUtility.InterfaceC3139f {
        public C2545e() {
        }

        @Override // com.cyberlink.you.utility.CLUtility.InterfaceC3139f
        /* renamed from: a */
        public void mo12947a() {
            ULogUtility.m16676l("ULauncherActivity", " queryPermissions fail");
        }

        @Override // com.cyberlink.you.utility.CLUtility.InterfaceC3139f
        public void onSuccess() {
            if (ULauncherActivity.this.isFinishing() || ULauncherActivity.this.isDestroyed()) {
                return;
            }
            ULauncherActivity.this.f11465i.getTabWidget().getChildAt(3).setVisibility(ULauncherActivity.this.m12912C1() ? 0 : 8);
            ULauncherActivity.this.m12916W1();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$f */
    public class C2546f implements InterfaceC5288c {
        public C2546f() {
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            ULogUtility.m16670f("ULauncherActivity", "Reject notification permission");
            if (z8) {
                C5183t0.m20262b(ULauncherActivity.this.m12910A1(), Permission.NOTIFICATION);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            ULogUtility.m16670f("ULauncherActivity", "Accept notification permission");
            ULauncherActivity.this.m12926e2();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$g */
    public class C2547g extends PromisedTask.AbstractC3021b<SubscriptionInfo> {
        public C2547g() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(SubscriptionInfo subscriptionInfo) {
            Globals.m7388i0().m7476Q3("pro".equalsIgnoreCase(subscriptionInfo.plan));
            Globals.m7388i0().m7642x3(Integer.parseInt(subscriptionInfo.attendeeCapacity));
            Globals.m7388i0().m7647y3(Integer.parseInt(subscriptionInfo.maximumLength));
            Globals.m7388i0().m7646y2(subscriptionInfo.androidOnHoldProductIds);
            if (Globals.m7388i0().m7591p()) {
                ULauncherActivity.this.m12939u1();
            } else {
                if (ULauncherActivity.this.f11477u || !C2925v.m14620s0()) {
                    return;
                }
                C2925v.m14569N0(ULauncherActivity.this, null, false, null, "main page");
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$h */
    public class C2548h extends PromisedTask.AbstractC3021b<JSONArray> {
        public C2548h() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(JSONArray jSONArray) {
            Log.d("ULauncherActivity", "result : " + jSONArray);
            Globals.m7388i0().m7453L3(jSONArray.toString());
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$i */
    public class C2549i implements BillingManager.BillingUpdatesListener {

        /* renamed from: a */
        public final /* synthetic */ List f11494a;

        public C2549i(List list) {
            this.f11494a = list;
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onError(BillingManager.ErrorMessage errorMessage) {
            if (ULauncherActivity.this.f11481y != null) {
                ULauncherActivity.this.f11481y.destroy();
                ULauncherActivity.this.f11481y = null;
            }
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onPurchasesEmpty() {
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onPurchasesOwned(List<Purchase> list, List<Pair<Purchase, BillingManager.ErrorMessage>> list2, boolean z8) {
            Iterator<Purchase> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Purchase next = it.next();
                if (ULauncherActivity.this.f11481y.getSubscriptionProProductId().contains(next.m3644e().get(0))) {
                    Globals.m7388i0().m7476Q3(true);
                    for (SkuDetails skuDetails : this.f11494a) {
                        if (skuDetails.m3651e().equals(next.m3644e().get(0)) && !TextUtils.isEmpty(skuDetails.m3647a())) {
                            ULogUtility.m16670f("ULauncherActivity", "onPurchasesOwned UseProFreeTrialBefore product id:" + next.m3644e().get(0));
                            Globals.m7388i0().m7559i4();
                        }
                    }
                }
            }
            if (ULauncherActivity.this.f11481y != null) {
                ULauncherActivity.this.f11481y.destroy();
                ULauncherActivity.this.f11481y = null;
            }
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onPurchasesVerify() {
        }

        @Override // com.android.vending.billing.util.BillingManager.BillingUpdatesListener
        public void onSkuDetailsResponse(List<SkuDetails> list) {
            this.f11494a.addAll(list);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$j */
    public interface InterfaceC2550j {
        /* renamed from: a */
        void mo12950a(boolean z8);
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$k */
    public static class AsyncTaskC2551k extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public InterfaceC2550j f11496a;

        public /* synthetic */ AsyncTaskC2551k(InterfaceC2550j interfaceC2550j, C2541a c2541a) {
            this(interfaceC2550j);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            Thread.currentThread().setName("CheckBindCLAccountTask");
            ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask doInBackground");
            String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            Pair<String, String> pairM15676n = FriendsClient.m15676n("user", "checkDou", arrayList);
            String str = (String) pairM15676n.first;
            String str2 = (String) pairM15676n.second;
            boolean z8 = false;
            if (str != null) {
                Log.d("ULauncherActivity", "[CheckBindCLAccountTask] statusCode is : " + str);
                if (str.equals("200")) {
                    ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask success");
                    try {
                        String string = new JSONObject(str2).getString("douToken");
                        if (string != null && !string.isEmpty()) {
                            Globals.m7388i0().m7447K2(string);
                            z8 = true;
                        }
                    } catch (JSONException e9) {
                        Log.d("ULauncherActivity", "[CheckBindCLAccountTask] parse jason error");
                        ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask parse douToken error");
                        e9.printStackTrace();
                    }
                } else {
                    ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask error : " + str);
                    try {
                        ULauncherActivity.m12893a2(C0681k.m3398d(new JSONObject(str2).getString("errorMessage")));
                    } catch (JSONException e10) {
                        Log.d("ULauncherActivity", "[CheckBindCLAccountTask] parse errorMessage error");
                        ULauncherActivity.m12893a2("");
                        e10.printStackTrace();
                    }
                }
            } else {
                ULauncherActivity.m12893a2("");
                ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask send request error");
            }
            return Boolean.valueOf(z8);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            this.f11496a.mo12950a(bool.booleanValue());
        }

        public AsyncTaskC2551k(InterfaceC2550j interfaceC2550j) {
            this.f11496a = interfaceC2550j;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$l */
    public class AsyncTaskC2552l extends AsyncTask<Void, Void, long[]> {

        /* renamed from: a */
        public UpdateFriendBadgeType f11497a;

        public AsyncTaskC2552l(UpdateFriendBadgeType updateFriendBadgeType) {
            this.f11497a = updateFriendBadgeType;
        }

        /* renamed from: a */
        public final boolean m12953a() {
            boolean z8 = false;
            if (!C6456d.m24714D().m24748G()) {
                return false;
            }
            try {
                FriendsClient friendsClient = new FriendsClient(1);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
                arrayList.add(new C6301o("pageSize", String.valueOf(1)));
                Pair<String, String> pairM15731j = friendsClient.m15731j("invite", "listReceivedInvite", arrayList);
                String str = (String) pairM15731j.first;
                String str2 = (String) pairM15731j.second;
                friendsClient.m15717U0();
                if ("200".equals(str)) {
                    ULauncherActivity.this.getSharedPreferences("cached_invite_list", 0).edit().putInt("countReceivedInvite", CLUtility.m16553k1(str2)).apply();
                    z8 = true;
                } else {
                    Log.e("ULauncherActivity", "[QueryReceivedInviteList] statusCode=" + str);
                }
            } catch (Exception e9) {
                Log.e("ULauncherActivity", "[QueryReceivedInviteList] " + e9.getMessage());
            }
            return z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public long[] doInBackground(Void... voidArr) {
            Log.d("ULauncherActivity", "FriendBadgesTask: start");
            Thread.currentThread().setName("FriendBadgesTask");
            long jCurrentTimeMillis = System.currentTimeMillis();
            long[] jArr = {-1, -1};
            if (isCancelled()) {
                return jArr;
            }
            List<Friend> listM15025q = C2950b0.m14899A().m15025q();
            long j9 = 0;
            if (listM15025q != null) {
                long j10 = 0;
                for (Friend friend : listM15025q) {
                    if (friend.f13659q >= 0) {
                        if (friend.f13659q + 86400 >= System.currentTimeMillis() / 1000) {
                            j10++;
                        }
                    }
                }
                j9 = j10;
            }
            jArr[0] = j9;
            if (isCancelled()) {
                return jArr;
            }
            if (!ULauncherActivity.this.f11476t) {
                ULauncherActivity.this.f11476t = m12953a();
            }
            jArr[1] = m12955c();
            Log.i("ULauncherActivity", "FriendBadgesTask: " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            return jArr;
        }

        /* renamed from: c */
        public final int m12955c() {
            return ULauncherActivity.this.getSharedPreferences("cached_invite_list", 0).getInt("countReceivedInvite", 0);
        }

        @Override // android.os.AsyncTask
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(long[] jArr) {
            Log.d("ULauncherActivity", String.format("[FriendBadgesTask] onPostExecute: type(%s)", this.f11497a.toString()));
            if (jArr.length < 2) {
                ULauncherActivity.this.m12925d2(false);
                return;
            }
            SharedPreferences sharedPreferences = ULauncherActivity.this.getSharedPreferences("U", 0);
            long j9 = sharedPreferences.getLong("lastnewFriendCountOnBadge", 0L);
            long j10 = sharedPreferences.getLong("lastReceivedInviteCountOnBadge", 0L);
            long j11 = jArr[0];
            long j12 = jArr[1];
            long j13 = j11 + j12;
            if (j11 < 0 || j12 < 0) {
                return;
            }
            if (j13 <= 0) {
                ULauncherActivity.this.m12925d2(false);
            } else {
                if (j13 > 99) {
                    ULauncherActivity.this.f11466j.setText("N");
                } else {
                    ULauncherActivity.this.f11466j.setText(String.valueOf(j13));
                }
                if (this.f11497a == UpdateFriendBadgeType.UFB_TYPE_INIT) {
                    ULauncherActivity.this.m12925d2(jArr[0] > j9 || jArr[1] > j10 || sharedPreferences.getBoolean("isFriendBadgeShow", false));
                } else if (jArr[0] > j9 || jArr[1] > j10) {
                    ULauncherActivity.this.m12925d2(true);
                }
            }
            Object[] objArr = new Object[3];
            objArr[0] = Boolean.valueOf(ULauncherActivity.this.f11466j.getVisibility() == 0);
            objArr[1] = Long.valueOf(jArr[0]);
            objArr[2] = Long.valueOf(jArr[1]);
            Log.d("ULauncherActivity", String.format("Friend Badge Info: show(%b), new friend count(%d), received invitation count(%d)", objArr));
            SharedPreferences sharedPreferences2 = ULauncherActivity.this.getSharedPreferences("U", 0);
            sharedPreferences2.edit().putLong("lastnewFriendCountOnBadge", jArr[0]).apply();
            sharedPreferences2.edit().putLong("lastReceivedInviteCountOnBadge", jArr[1]).apply();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$m */
    public class AsyncTaskC2553m extends AsyncTask<Void, Void, Integer> {
        public AsyncTaskC2553m() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) {
            int i9;
            C5194z.a aVarM20296f;
            Thread.currentThread().setName("MoreBadgesTask");
            if (Globals.m7388i0().m7608s()) {
                i9 = 1;
            } else {
                i9 = 0;
                if (C5192y.m20281f().m20284e() && (aVarM20296f = C5194z.m20296f()) != null) {
                    if (aVarM20296f.m20305a() > Globals.m7388i0().m7597q0()) {
                        Globals.m7388i0().m7416D3(true);
                        i9 = 1;
                    } else {
                        Globals.m7388i0().m7416D3(false);
                    }
                }
            }
            if (Globals.m7388i0().m7620u() || (C5164m0.m20108m().m20114g() && C5164m0.m20108m().m20113f())) {
                i9++;
            }
            if (Globals.m7388i0().m7602r()) {
                i9++;
            }
            if (!Globals.m7388i0().m7523b0()) {
                Globals.m7388i0().m7421E3(true);
                i9++;
            }
            return Integer.valueOf(i9);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            if (num.intValue() == 0) {
                ULauncherActivity.this.f11472p.setVisibility(8);
            } else {
                ULauncherActivity.this.f11472p.setText(String.valueOf(num));
                ULauncherActivity.this.f11472p.setVisibility(0);
            }
        }

        public /* synthetic */ AsyncTaskC2553m(ULauncherActivity uLauncherActivity, C2541a c2541a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.ULauncherActivity$n */
    public class RunnableC2554n implements Runnable {
        public RunnableC2554n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONObject jSONObjectM7556i1 = Globals.m7388i0().m7556i1();
            if (jSONObjectM7556i1 != null) {
                Iterator<String> itKeys = jSONObjectM7556i1.keys();
                while (itKeys.hasNext()) {
                    C5321e.m20824o().m20881n0(null, itKeys.next(), null);
                }
            }
        }

        public /* synthetic */ RunnableC2554n(ULauncherActivity uLauncherActivity, C2541a c2541a) {
            this();
        }
    }

    /* renamed from: F1 */
    public static boolean m12859F1() {
        HashSet hashSet = new HashSet(Arrays.asList("xiaomi", "sony", "oppo", "huawei"));
        String str = Build.MANUFACTURER;
        Locale locale = Locale.US;
        return hashSet.contains(str.toLowerCase(locale)) || ("asus".equalsIgnoreCase(str) && !Build.MODEL.toLowerCase(locale).contains("nexus"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G1 */
    public /* synthetic */ void m12860G1() {
        long jM14480D = C2907m0.m14454I().m14480D();
        Log.e("ULauncherActivity", "abnormal read count " + jM14480D);
        if (jM14480D < 0) {
            Log.e("ULauncherActivity", "abnormal read count " + jM14480D);
            return;
        }
        if (jM14480D != 0) {
            if (jM14480D > 99) {
                this.f11471o.setText("N");
            } else {
                this.f11471o.setText(String.valueOf(jM14480D));
            }
            this.f11471o.setVisibility(0);
            return;
        }
        this.f11471o.setVisibility(8);
        NotificationManager notificationManager = (NotificationManager) m12910A1().getSystemService("notification");
        if (!NotificationHelper.f12417a) {
            notificationManager.cancelAll();
            return;
        }
        notificationManager.cancel(0);
        notificationManager.cancel(1);
        notificationManager.cancel(2);
        notificationManager.cancel(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H1 */
    public /* synthetic */ void m12861H1() {
        Thread.currentThread().setName("ULauncherActivity.UploadSuggestList");
        m12924d1();
        runOnUiThread(new Runnable() { // from class: g3.i3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16932b.m12936o2();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I1 */
    public /* synthetic */ void m12862I1(DialogInterface dialogInterface, int i9) {
        Intent intent = new Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J1 */
    public /* synthetic */ void m12863J1() {
        C1260a.m5681s(Globals.m7388i0().m7506X()).m15439e(new C2548h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K1 */
    public /* synthetic */ void m12865K1() throws JSONException {
        C1260a.m5672i(Globals.m7388i0().m7506X()).m15439e(new C2547g());
        FriendsClient friendsClient = new FriendsClient(1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        Pair<String, String> pairM15731j = friendsClient.m15731j("user", "queryMsgSubscription", arrayList);
        String str = (String) pairM15731j.first;
        String str2 = (String) pairM15731j.second;
        friendsClient.m15717U0();
        if ("200".equals(str)) {
            try {
                String string = new JSONObject(str2).getString("isPro");
                if (C5170o0.m20170e(string)) {
                    return;
                }
                Globals.m7388i0().m7546f3(Boolean.parseBoolean(string));
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L1 */
    public /* synthetic */ void m12867L1(View view) {
        AbstractC2555a abstractC2555aM12911B1 = m12911B1();
        if (abstractC2555aM12911B1 instanceof C4857f) {
            ((C4857f) abstractC2555aM12911B1).m19198w();
        } else {
            this.f11465i.setCurrentTab(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M1 */
    public /* synthetic */ void m12869M1() {
        this.f11479w = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N1 */
    public /* synthetic */ void m12871N1(boolean z8) {
        if (z8) {
            m12920b1(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O1 */
    public /* synthetic */ void m12873O1() {
        runOnUiThread(new Runnable() { // from class: g3.h3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16896b.mo12935n0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    /* renamed from: P1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ boolean m12875P1(C2904l c2904l, Map map) {
        char c9 = 0;
        if (map == null) {
            Log.e("ULauncherActivity", "info == null");
            return false;
        }
        String str = (String) map.get("eventType");
        if (str.equals("friend.friend.created") || str.equals("friend.friend.deleted") || str.equals("friend.friend.hided") || str.equals("friend.friend.showed") || str.equals("friend.friend.blocked") || str.equals("invite.friend.created") || str.equals("invite.friend.accepted") || str.equals("invite.friend.canceled")) {
            this.f11476t = false;
        }
        if (this.f11477u) {
            return true;
        }
        String strM22346k = c2904l != null ? C5616j.m22346k(c2904l.m14428m()) : null;
        switch (str.hashCode()) {
            case -1868147997:
                if (!str.equals("group.member.created")) {
                    c9 = 65535;
                    break;
                }
                break;
            case -1524614164:
                if (str.equals("friend.friend.blocked")) {
                    c9 = 1;
                    break;
                }
                break;
            case -1346239468:
                if (str.equals("group.member.deleted")) {
                    c9 = 2;
                    break;
                }
                break;
            case -1267404515:
                if (str.equals("invite.friend.created")) {
                    c9 = 3;
                    break;
                }
                break;
            case -1061631694:
                if (str.equals("group.member.leaved")) {
                    c9 = 4;
                    break;
                }
                break;
            case -474621720:
                if (str.equals("friend.friend.created")) {
                    c9 = 5;
                    break;
                }
                break;
            case -306809838:
                if (str.equals("invite.friend.accepted")) {
                    c9 = 6;
                    break;
                }
                break;
            case -228547230:
                if (str.equals("friend.friend.hided")) {
                    c9 = 7;
                    break;
                }
                break;
            case -220662488:
                if (str.equals("suggestion.suggestion.created")) {
                    c9 = '\b';
                    break;
                }
                break;
            case 47286809:
                if (str.equals("friend.friend.deleted")) {
                    c9 = '\t';
                    break;
                }
                break;
            case 189290855:
                if (str.equals("group.member.created.v2")) {
                    c9 = '\n';
                    break;
                }
                break;
            case 596368569:
                if (str.equals("dou.permission.changed")) {
                    c9 = 11;
                    break;
                }
                break;
            case 1018813168:
                if (str.equals("friend.friend.broke.up")) {
                    c9 = '\f';
                    break;
                }
                break;
            case 1716541700:
                if (str.equals("invite.friend.canceled")) {
                    c9 = CharUtils.f19105CR;
                    break;
                }
                break;
            case 1819312732:
                if (str.equals("friend.friend.showed")) {
                    c9 = 14;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
            case 2:
            case 4:
            case '\n':
                mo12935n0();
                return true;
            case 1:
                m12933l2((String) map.get("userId"));
                return true;
            case 3:
                m12932k2(strM22346k);
                return true;
            case 5:
                m12930i2(strM22346k);
                return true;
            case 6:
                m12931j2(strM22346k);
                return true;
            case 7:
                m12933l2((String) map.get("userId"));
                return true;
            case '\b':
                m12936o2();
                return true;
            case '\t':
                m12933l2((String) map.get("userId"));
                return true;
            case 11:
                AbstractC2555a abstractC2555aM12911B1 = m12911B1();
                if (abstractC2555aM12911B1 instanceof C2557c) {
                    ((C2557c) abstractC2555aM12911B1).m13159D();
                }
                return true;
            case '\f':
                if (!String.valueOf(Globals.m7388i0().m7568k1()).equals(strM22346k)) {
                    mo12935n0();
                }
                return true;
            case '\r':
                m12933l2(strM22346k);
                return true;
            case 14:
                m12930i2((String) map.get("userId"));
                return true;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q1 */
    public /* synthetic */ void m12877Q1() {
        m12929h2(UpdateFriendBadgeType.UFB_TYPE_INIT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R1 */
    public /* synthetic */ void m12879R1(ProgressDialog progressDialog, boolean z8) {
        if (m7367J0()) {
            return;
        }
        progressDialog.dismiss();
        if (z8) {
            ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask end");
            return;
        }
        ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask direct to BindCLAccountActivity");
        startActivity(new Intent(getApplicationContext(), (Class<?>) BindCLAccountActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S1 */
    public /* synthetic */ void m12881S1(boolean z8, DialogInterface dialogInterface, int i9) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) SettingActivity.class);
        if (!z8) {
            intent.putExtra("IS_NEED_SCROLL_TO_NOTIFICATION_AREA", true);
        }
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T1 */
    public /* synthetic */ void m12883T1(boolean z8) {
        View view = this.f11460d;
        if (view != null) {
            view.setVisibility(z8 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U1 */
    public /* synthetic */ void m12885U1() {
        Log.d("ULauncherActivity", "[updateFriendBadgeByEvent] Start to check if there is remained task");
        synchronized (this.f11470n) {
            this.f11468l = false;
        }
        m12934m2(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V1 */
    public /* synthetic */ void m12887V1(boolean z8) {
        synchronized (this.f11470n) {
            Log.d("ULauncherActivity", "[updateFriendBadgeByEvent] Start");
            if (z8) {
                this.f11469m++;
            }
            if (this.f11469m <= 0) {
                Log.d("ULauncherActivity", "[updateFriendBadgeByEvent] There is no task remained");
                return;
            }
            if (this.f11477u) {
                this.f11469m = 0;
                Log.d("ULauncherActivity", "[updateFriendBadgeByEvent] Activity is paused and wait to update after resume");
                return;
            }
            if (this.f11468l) {
                Log.d("ULauncherActivity", "[updateFriendBadgeByEvent] " + this.f11469m + " tasks wait to run next time.");
            } else {
                this.f11468l = true;
                this.f11469m = 0;
                m12929h2(UpdateFriendBadgeType.UFB_TYPE_UPDATE);
                Log.d("ULauncherActivity", "[updateFriendBadgeByEvent] Check remained task after 5 seconds");
                new Handler().postDelayed(new Runnable() { // from class: g3.k3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16949b.m12885U1();
                    }
                }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
        }
    }

    /* renamed from: a2 */
    public static void m12893a2(String str) {
        C5187v0.m20268d(C0681k.m3399e(Globals.m7372O(), str));
    }

    /* renamed from: n2 */
    public static void m12904n2() throws PackageManager.NameNotFoundException {
        Globals.m7388i0();
        String packageName = Globals.m7372O().getPackageName();
        try {
            Globals.m7388i0();
            PackageInfo packageInfo = Globals.m7372O().getPackageManager().getPackageInfo(packageName, 0);
            String strM7649z0 = Globals.m7388i0().m7649z0();
            String str = packageInfo.versionName;
            ULogUtility.m16670f("ULauncherActivity", "[DB][updateLastVersion] last version: " + strM7649z0 + " replace by " + str);
            Globals.m7388i0().m7630v3(str);
        } catch (PackageManager.NameNotFoundException e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: A1 */
    public final Activity m12910A1() {
        return this;
    }

    /* renamed from: B1 */
    public final AbstractC2555a m12911B1() {
        PageType pageType = PageType.None;
        int currentTab = this.f11465i.getCurrentTab();
        PageType pageType2 = currentTab != 0 ? currentTab != 1 ? currentTab != 2 ? currentTab != 3 ? currentTab != 4 ? pageType : PageType.More : PageType.Phones : PageType.Meetings_And_Webinars : PageType.Friends : PageType.Chats;
        if (pageType2 != pageType) {
            return (AbstractC2555a) getSupportFragmentManager().mo1848e(pageType2.name());
        }
        Log.e("ULauncherActivity", "Cannot find page id = " + currentTab);
        return null;
    }

    /* renamed from: C1 */
    public final boolean m12912C1() {
        for (String str : Globals.m7388i0().m7455M0().split(",")) {
            if ("SIP_TRUNK".equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: D1 */
    public final void m12913D1(int i9) {
        CLFragmentTabHost cLFragmentTabHost = (CLFragmentTabHost) findViewById(R.id.tabhost);
        this.f11465i = cLFragmentTabHost;
        cLFragmentTabHost.m17293g(this, getSupportFragmentManager(), R.id.realtabcontent);
        CLFragmentTabHost cLFragmentTabHost2 = this.f11465i;
        PageType pageType = PageType.Chats;
        cLFragmentTabHost2.m17287a(cLFragmentTabHost2.newTabSpec(pageType.name()).setIndicator(m12945z1(R.drawable.image_selector_tabbar_chat, pageType.name())), C4857f.class, null);
        CLFragmentTabHost cLFragmentTabHost3 = this.f11465i;
        PageType pageType2 = PageType.Friends;
        cLFragmentTabHost3.m17287a(cLFragmentTabHost3.newTabSpec(pageType2.name()).setIndicator(m12945z1(R.drawable.image_selector_tabbar_friends, pageType2.name())), C2559e.class, null);
        CLFragmentTabHost cLFragmentTabHost4 = this.f11465i;
        PageType pageType3 = PageType.Meetings_And_Webinars;
        cLFragmentTabHost4.m17287a(cLFragmentTabHost4.newTabSpec(pageType3.name()).setIndicator(m12945z1(R.drawable.image_selector_tabbar_meeting, pageType3.name())), C2557c.class, null);
        CLFragmentTabHost cLFragmentTabHost5 = this.f11465i;
        PageType pageType4 = PageType.Phones;
        cLFragmentTabHost5.m17287a(cLFragmentTabHost5.newTabSpec(pageType4.name()).setIndicator(m12945z1(R.drawable.image_selector_tabbar_phone, pageType4.name())), C2561g.class, null);
        CLFragmentTabHost cLFragmentTabHost6 = this.f11465i;
        PageType pageType5 = PageType.More;
        cLFragmentTabHost6.m17287a(cLFragmentTabHost6.newTabSpec(pageType5.name()).setIndicator(m12945z1(R.drawable.image_selector_tabbar_more, pageType5.name())), C2560f.class, null);
        this.f11465i.getTabWidget().setDividerDrawable((Drawable) null);
        this.f11465i.getTabWidget().getChildAt(0).setOnClickListener(new View.OnClickListener() { // from class: g3.s3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f17007b.m12867L1(view);
            }
        });
        this.f11465i.getTabWidget().getChildAt(3).setVisibility(m12912C1() ? 0 : 8);
        this.f11465i.setCurrentTab(i9);
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2559e.l
    /* renamed from: E0 */
    public void mo12914E0(String str) {
        this.f11464h.setText(str);
    }

    /* renamed from: E1 */
    public final boolean m12915E1() {
        boolean zM20170e = C5170o0.m20170e(Globals.m7388i0().m7506X());
        ULogUtility.m16688x("ULauncherActivity", "bNeedCheckDouToken : " + zM20170e);
        return zM20170e;
    }

    /* renamed from: W1 */
    public final void m12916W1() {
        int i9 = 0;
        for (int i10 = 0; i10 < this.f11465i.getTabWidget().getChildCount(); i10++) {
            if (this.f11465i.getTabWidget().getChildAt(i10).getVisibility() == 0) {
                i9++;
            }
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f9 = displayMetrics.widthPixels;
        float f10 = f9 / i9;
        int i11 = this.f11459c[0];
        float f11 = f10 / i11;
        float f12 = ((f9 / 4.0f) / i11) * r3[1];
        for (int i12 = 0; i12 < this.f11465i.getTabWidget().getChildCount(); i12++) {
            View childAt = this.f11465i.getTabWidget().getChildAt(i12);
            if (childAt.getVisibility() == 0) {
                m12919Z1(childAt, 0, f11);
                childAt.setLayoutParams(new LinearLayout.LayoutParams((int) f10, (int) f12));
            }
        }
    }

    /* renamed from: X1 */
    public final void m12917X1() {
        Intent intent = new Intent("android.settings.MANAGE_APP_USE_FULL_SCREEN_INTENT");
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    /* renamed from: Y1 */
    public final void m12918Y1() {
        if (Build.VERSION.SDK_INT >= 33) {
            C5287b.m20583f(Permission.NOTIFICATION, new C2546f(), this);
        } else {
            m12926e2();
        }
    }

    /* renamed from: Z1 */
    public final void m12919Z1(View view, int i9, float f9) {
        TextView textView = (TextView) view.findViewById(R.id.badge);
        if (i9 == R.drawable.image_selector_tabbar_friends) {
            this.f11466j = textView;
        } else if (i9 == R.drawable.image_selector_tabbar_chat) {
            this.f11471o = textView;
        } else if (i9 == R.drawable.image_selector_tabbar_more) {
            this.f11472p = textView;
        }
        int iRound = Math.round(45.0f * f9);
        int iRound2 = Math.round(42.0f * f9);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.height = iRound;
        layoutParams.width = iRound;
        layoutParams.setMargins(iRound2, 0, 0, 0);
        textView.setTextSize((f9 * 26.0f) / getResources().getDisplayMetrics().scaledDensity);
    }

    /* renamed from: b1 */
    public final void m12920b1(boolean z8) {
        if (z8) {
            C2907m0.m14454I().m14495W(this.f11457E);
        }
        runOnUiThread(new Runnable() { // from class: g3.n3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16972b.m12860G1();
            }
        });
    }

    /* renamed from: b2 */
    public final void m12921b2() {
        if (m7367J0() || !m12859F1() || Globals.m7388i0().m7542f() || Globals.m7388i0().m7622u1()) {
            return;
        }
        Globals.m7388i0().m7490T2(true);
        (C5175q.m20219a() ? new C3121e().m16369f(this) : new C3121e().m16368e(this)).show();
    }

    /* renamed from: c1 */
    public final void m12922c1() {
        new Thread(new Runnable() { // from class: g3.o3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16979b.m12861H1();
            }
        }).start();
    }

    /* renamed from: c2 */
    public void m12923c2(boolean z8) {
        View view = this.f11463g;
        if (view != null) {
            view.setVisibility(z8 ? 0 : 8);
        }
        CLFragmentTabHost cLFragmentTabHost = this.f11465i;
        if (cLFragmentTabHost != null) {
            cLFragmentTabHost.getTabWidget().setVisibility(z8 ? 0 : 8);
        }
    }

    /* renamed from: d1 */
    public final void m12924d1() {
        C5173p0.m20207e().m20215k(true);
        ArrayList<SuggestionFriend> arrayListM20211g = C5173p0.m20207e().m20211g();
        long jM7398A0 = Globals.m7388i0().m7398A0();
        if (arrayListM20211g.size() <= 0) {
            Globals.m7388i0().m7411C3(false);
        } else if (jM7398A0 < C5173p0.m20207e().m20210f()) {
            Globals.m7388i0().m7411C3(true);
            Globals.m7388i0().m7401A3(true);
        }
        this.f11475s = true;
    }

    /* renamed from: d2 */
    public void m12925d2(boolean z8) {
        TextView textView = this.f11466j;
        if (textView == null) {
            return;
        }
        textView.setVisibility(z8 ? 0 : 8);
        getSharedPreferences("U", 0).edit().putBoolean("isFriendBadgeShow", z8).apply();
    }

    /* renamed from: e2 */
    public final void m12926e2() {
        SharedPreferences sharedPreferences = getSharedPreferences("U", 0);
        long j9 = sharedPreferences.getLong("notificationCheckTime", 0L);
        final boolean zM14059C = NotificationHelper.m14059C();
        if (!(CLUtility.m16433E1() && zM14059C) && System.currentTimeMillis() - j9 >= 2592000000L) {
            ULogUtility.m16683s("ULauncherActivity", "Detect turn off notification");
            C3123g.m16382a(this).setMessage(R.string.popup_notification_reminder).setPositiveButton(R.string.close, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.chat_group_more_dialog_open_allert, new DialogInterface.OnClickListener() { // from class: g3.t3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f17015b.m12881S1(zM14059C, dialogInterface, i9);
                }
            }).setCancelable(false).create().show();
            sharedPreferences.edit().putLong("notificationCheckTime", System.currentTimeMillis()).apply();
        }
    }

    /* renamed from: f2 */
    public void m12927f2(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: g3.l3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16955b.m12883T1(z8);
            }
        });
    }

    /* renamed from: g2 */
    public final void m12928g2() {
        if (Globals.m7388i0().m7578m0() || !FriendsClient.m15680p0()) {
            return;
        }
        C3062b.m15823w(this.f11453A);
        C3062b.m15822v(this.f11482z);
        C3062b.m15825y(this.f11454B);
        Globals.m7388i0().m7425F2(true);
    }

    /* renamed from: h2 */
    public void m12929h2(UpdateFriendBadgeType updateFriendBadgeType) {
        mo12940v(updateFriendBadgeType, false);
    }

    /* renamed from: i2 */
    public final void m12930i2(String str) {
        Log.d("ULauncherActivity", "updateFriendBadgeByAddFriend");
        if (str == null) {
            return;
        }
        m12934m2(true);
    }

    /* renamed from: j2 */
    public final void m12931j2(String str) {
        Log.d("ULauncherActivity", "updateFriendBadgeByInviteAcceptEvent");
        if (str != null && str.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            m12934m2(true);
        }
    }

    /* renamed from: k2 */
    public final void m12932k2(String str) {
        Log.d("ULauncherActivity", "updateFriendBadgeByInviteCreateEvent");
        if (str == null || str.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            return;
        }
        m12934m2(true);
    }

    /* renamed from: l2 */
    public final void m12933l2(String str) {
        Log.d("ULauncherActivity", "updateFriendBadgeByRemoveFriend");
        if (str == null) {
            return;
        }
        m12934m2(true);
    }

    /* renamed from: m2 */
    public final void m12934m2(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: g3.j3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16941b.m12887V1(z8);
            }
        });
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a.a
    /* renamed from: n0 */
    public void mo12935n0() {
        AbstractC2555a abstractC2555aM12911B1 = m12911B1();
        if (abstractC2555aM12911B1 instanceof C4857f) {
            ((C4857f) abstractC2555aM12911B1).m19200y();
        }
        if (C2907m0.m14454I().m14489N()) {
            m12920b1(false);
        } else {
            C2907m0.m14454I().m14511u(this.f11457E);
        }
    }

    /* renamed from: o2 */
    public final void m12936o2() {
        AsyncTaskC2553m asyncTaskC2553m = this.f11473q;
        if (asyncTaskC2553m != null) {
            asyncTaskC2553m.cancel(true);
        }
        AsyncTaskC2553m asyncTaskC2553m2 = new AsyncTaskC2553m(this, null);
        this.f11473q = asyncTaskC2553m2;
        asyncTaskC2553m2.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AbstractC2555a abstractC2555aM12911B1 = m12911B1();
        if (abstractC2555aM12911B1 == null || !abstractC2555aM12911B1.mo12962j()) {
            if (this.f11479w || getSupportFragmentManager().mo1849f() > 0) {
                super.onBackPressed();
                return;
            }
            this.f11479w = true;
            C5187v0.m20267c(R.string.tap_back_again_to_exit);
            this.f11478v.postDelayed(this.f11480x, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws PackageManager.NameNotFoundException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ULogUtility.m16670f("ULauncherActivity", "onCreate enter");
        boolean booleanExtra = false;
        this.f11476t = false;
        super.onCreate(bundle);
        setContentView(R.layout.activity_u_launcher);
        new C2981p0().start();
        m12941v1();
        boolean zM7533d1 = Globals.m7388i0().m7533d1();
        Log.d("ULauncherActivity", "[updateSticker] stickerUpdatedWithServer = " + zM7533d1 + " isSyncUserSticker = " + f11452G);
        if ("".equals(getSharedPreferences("U", 0).getString("stickerPacksOrder", "")) || (!f11452G && zM7533d1)) {
            f11452G = true;
            Globals.m7388i0().m7579m1();
        }
        int intExtra = getApplicationContext().getSharedPreferences("U", 0).getInt("Tab_Index", 0);
        Intent intent = getIntent();
        if (intent != null) {
            intExtra = intent.getIntExtra("Tab_Index", intExtra);
            booleanExtra = intent.getBooleanExtra("isVerified", false);
        }
        if (booleanExtra) {
            CLUtility.m16610y2();
        }
        this.f11460d = findViewById(R.id.waitingCursor);
        this.f11461e = (ImageView) findViewById(R.id.topBarRightImage);
        this.f11462f = findViewById(R.id.topBarRightImageClickArea);
        this.f11464h = (TextView) findViewById(R.id.topBarTitle);
        this.f11463g = findViewById(R.id.ULauncherHeader);
        if (intExtra == 3 && !m12912C1()) {
            intExtra = 4;
        }
        m12913D1(intExtra);
        C2907m0.m14454I();
        C5321e.m20824o().m20875k(this.f11456D);
        C6385v.m24526d(new RunnableC2554n(this, null));
        stopWatch.stop();
        Log.v("ULauncherActivity", "onCreate exit " + stopWatch.getTime() + " ms");
        m12922c1();
        Globals.m7388i0().m7607r4();
        m12928g2();
        m12944y1();
        m12918Y1();
        CLUtility.m16522c2(this.f11458F);
        m12943x1();
        m12938t1();
        m12942w1();
        m12904n2();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        C5321e.m20824o().m20832B0(this.f11456D);
        this.f11465i.clearAllTabs();
        BillingManager billingManager = this.f11481y;
        if (billingManager != null) {
            billingManager.destroy();
            this.f11481y = null;
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int intExtra = intent.getIntExtra("Tab_Index", -1);
        if (intExtra >= 0) {
            this.f11465i.setCurrentTab(intExtra);
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Log.v("ULauncherActivity", "onPause enter");
        this.f11477u = true;
        super.onPause();
        C2907m0.m14454I().m14494V(this.f11455C);
        C6456d.m24714D().m24751K(getClass().getSimpleName());
        stopWatch.stop();
        Log.v("ULauncherActivity", "onPause exit " + stopWatch.getTime() + " ms");
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        this.f11477u = false;
        Log.v("ULauncherActivity", "onResume enter");
        super.onResume();
        m12941v1();
        C6456d.m24714D().m24743A(getClass().getSimpleName());
        C2907m0.m14454I().m14510t(this.f11455C);
        mo12935n0();
        if (this.f11475s) {
            m12936o2();
        }
        new Handler().postDelayed(new Runnable() { // from class: g3.q3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16993b.m12877Q1();
            }
        }, 2000);
        C0032a c0032a = new C0032a();
        if (c0032a.m129j()) {
            c0032a.m132m(this);
        }
        C2541a c2541a = null;
        if (m12915E1()) {
            ULogUtility.m16688x("ULauncherActivity", "CheckBindCLAccountTask start");
            final ProgressDialog progressDialogShow = ProgressDialog.show(m12910A1(), "", getString(R.string.u_launcher_upgrade_data), true);
            new AsyncTaskC2551k(new InterfaceC2550j() { // from class: g3.r3
                @Override // com.cyberlink.you.activity.ulauncher.ULauncherActivity.InterfaceC2550j
                /* renamed from: a */
                public final void mo12950a(boolean z8) {
                    this.f16999a.m12879R1(progressDialogShow, z8);
                }
            }, c2541a).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
        m12921b2();
        if (C2925v.m14620s0()) {
            C2925v.m14569N0(this, null, false, null, "main page");
        }
        stopWatch.stop();
        ULogUtility.m16683s("ULauncherActivity", "onResume exit " + stopWatch.getTime() + " ms");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00a5  */
    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a.a
    /* renamed from: q0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo12937q0(View.OnClickListener onClickListener, PageType pageType) {
        int i9;
        PageType pageType2 = PageType.Friends;
        if (!pageType.equals(pageType2)) {
            if (pageType.equals(PageType.Chats)) {
                this.f11464h.setText(getString(R.string.u_launcher_title_messages));
                this.f11461e.setImageResource(R.drawable.icon_multi_chat);
                mo12935n0();
                i9 = 0;
            } else if (pageType.equals(PageType.More)) {
                this.f11464h.setText(getString(R.string.more_fragment));
                this.f11461e.setImageResource(0);
                i9 = 4;
            } else if (pageType.equals(PageType.Meetings_And_Webinars)) {
                this.f11464h.setText(getResources().getString(R.string.u_launcher_title_meetings_and_webinars));
                this.f11461e.setImageResource(0);
                i9 = 2;
            } else if (pageType.equals(PageType.Phones)) {
                this.f11464h.setText(getResources().getString(R.string.u_launcher_title_phone));
                this.f11461e.setImageResource(0);
                i9 = 3;
            }
            this.f11462f.setOnClickListener(onClickListener);
            if (!pageType.equals(pageType2)) {
                CLUtility.m16589t1(this);
            }
            if (this.f11474r == 1 && i9 != 1) {
                m12925d2(false);
            }
            this.f11474r = i9;
            getSharedPreferences("U", 0).edit().putInt("Tab_Index", i9).apply();
        }
        this.f11464h.setText(getString(R.string.u_launcher_title_contacts));
        this.f11461e.setImageResource(R.drawable.icon_add);
        i9 = 1;
        this.f11462f.setOnClickListener(onClickListener);
        if (!pageType.equals(pageType2)) {
        }
        if (this.f11474r == 1) {
            m12925d2(false);
        }
        this.f11474r = i9;
        getSharedPreferences("U", 0).edit().putInt("Tab_Index", i9).apply();
    }

    /* renamed from: t1 */
    public final void m12938t1() {
        AlarmManager alarmManager;
        if (m7367J0() || (alarmManager = (AlarmManager) getSystemService("alarm")) == null) {
            return;
        }
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 33) {
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, new Intent(this, (Class<?>) AlarmReceiver.class), 201326592);
            if (broadcast != null) {
                alarmManager.setInexactRepeating(0, System.currentTimeMillis(), FriendsClient.m15653U() * 1000, broadcast);
                return;
            }
            return;
        }
        if (i9 >= 31) {
            if (alarmManager.canScheduleExactAlarms()) {
                PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 0, new Intent(this, (Class<?>) AlarmReceiver.class), 201326592);
                if (broadcast2 != null) {
                    alarmManager.setRepeating(0, System.currentTimeMillis(), FriendsClient.m15653U() * 1000, broadcast2);
                    return;
                }
                return;
            }
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setMessage(getString(R.string.u_launcher_alert_remind));
            builderM16382a.setPositiveButton(getString(R.string.permission_go_app_setting), new DialogInterface.OnClickListener() { // from class: g3.g3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    this.f16890b.m12862I1(dialogInterface, i10);
                }
            });
            ((TextView) builderM16382a.show().findViewById(android.R.id.message)).setTextSize(2, 16.0f);
        }
    }

    /* renamed from: u1 */
    public final void m12939u1() {
        C2549i c2549i = new C2549i(new ArrayList());
        BillingManager billingManager = new BillingManager(this);
        this.f11481y = billingManager;
        billingManager.setBillingUpdatesListener(c2549i);
        this.f11481y.queryData();
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2559e.l
    /* renamed from: v */
    public void mo12940v(UpdateFriendBadgeType updateFriendBadgeType, boolean z8) {
        if (z8) {
            this.f11476t = false;
        }
        AsyncTaskC2552l asyncTaskC2552l = this.f11467k;
        if (asyncTaskC2552l != null) {
            asyncTaskC2552l.cancel(true);
        }
        AsyncTaskC2552l asyncTaskC2552l2 = new AsyncTaskC2552l(updateFriendBadgeType);
        this.f11467k = asyncTaskC2552l2;
        asyncTaskC2552l2.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: v1 */
    public final void m12941v1() {
        C4714a.m18867b(getIntent(), new C2544d());
    }

    /* renamed from: w1 */
    public final void m12942w1() {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT < 34 || (notificationManager = (NotificationManager) m12910A1().getSystemService("notification")) == null || notificationManager.canUseFullScreenIntent()) {
            return;
        }
        Log.i("ULauncherActivity", "[Meeting] Requesting full screen intent permission");
        m12917X1();
    }

    /* renamed from: x1 */
    public void m12943x1() {
        C6385v.m24526d(new Runnable() { // from class: g3.m3
            @Override // java.lang.Runnable
            public final void run() {
                this.f16964b.m12863J1();
            }
        });
    }

    /* renamed from: y1 */
    public void m12944y1() {
        C6385v.m24526d(new Runnable() { // from class: g3.p3
            @Override // java.lang.Runnable
            public final void run() throws JSONException {
                this.f16987b.m12865K1();
            }
        });
    }

    /* renamed from: z1 */
    public final View m12945z1(int i9, String str) {
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        float f9 = r0.widthPixels / (m12912C1() ? 5 : 4);
        int[] iArr = this.f11459c;
        float f10 = f9 / iArr[0];
        float f11 = iArr[1] * f10;
        View viewInflate = getLayoutInflater().inflate(R.layout.tab_launcher, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.tabImageView);
        if (imageView != null) {
            imageView.setImageResource(i9);
            imageView.setContentDescription(str);
        }
        m12919Z1(viewInflate, i9, f10);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams((int) f9, (int) f11));
        return viewInflate;
    }
}
