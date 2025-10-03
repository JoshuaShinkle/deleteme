package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.p036ui.SelectServerDialog;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p015b4.C0676f;
import p015b4.InterfaceC0673c;
import p116k4.AbstractC5146g0;
import p116k4.C5172p;
import p201t3.C6301o;
import p209u2.C6366c;

/* loaded from: classes.dex */
public class SettingActivity extends BaseActivity {

    /* renamed from: Q */
    public static boolean f8942Q = true;

    /* renamed from: R */
    public static boolean f8943R = true;

    /* renamed from: S */
    public static boolean f8944S = true;

    /* renamed from: T */
    public static boolean f8945T = true;

    /* renamed from: A */
    public View f8946A;

    /* renamed from: e */
    public C0676f f8964e;

    /* renamed from: f */
    public FriendsClient f8965f;

    /* renamed from: g */
    public UserInfo f8966g;

    /* renamed from: h */
    public ImageView f8967h;

    /* renamed from: i */
    public ImageView f8968i;

    /* renamed from: j */
    public ImageView f8969j;

    /* renamed from: k */
    public TextView f8970k;

    /* renamed from: l */
    public View f8971l;

    /* renamed from: m */
    public View f8972m;

    /* renamed from: n */
    public View f8973n;

    /* renamed from: o */
    public View f8974o;

    /* renamed from: p */
    public View f8975p;

    /* renamed from: q */
    public View f8976q;

    /* renamed from: r */
    public View f8977r;

    /* renamed from: s */
    public View f8978s;

    /* renamed from: t */
    public View f8979t;

    /* renamed from: u */
    public TextView f8980u;

    /* renamed from: v */
    public View f8981v;

    /* renamed from: w */
    public View f8982w;

    /* renamed from: x */
    public View f8983x;

    /* renamed from: y */
    public View f8984y;

    /* renamed from: z */
    public View f8985z;

    /* renamed from: c */
    public final String f8962c = "SettingActivity";

    /* renamed from: d */
    public final C6366c f8963d = new C6366c(10);

    /* renamed from: B */
    public View.OnClickListener f8947B = new View.OnClickListener() { // from class: com.cyberlink.you.activity.qe
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11086b.m9879l0(view);
        }
    };

    /* renamed from: C */
    public final View.OnClickListener f8948C = new ViewOnClickListenerC1721a();

    /* renamed from: D */
    public View.OnClickListener f8949D = new View.OnClickListener() { // from class: com.cyberlink.you.activity.bf
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9752b.m9880m0(view);
        }
    };

    /* renamed from: E */
    public View.OnClickListener f8950E = new View.OnClickListener() { // from class: com.cyberlink.you.activity.cf
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9785b.m9884o0(view);
        }
    };

    /* renamed from: F */
    public View.OnClickListener f8951F = new View.OnClickListener() { // from class: com.cyberlink.you.activity.df
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10374b.m9885p0(view);
        }
    };

    /* renamed from: G */
    public View.OnClickListener f8952G = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ef
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10409b.m9887q0(view);
        }
    };

    /* renamed from: H */
    public View.OnClickListener f8953H = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ff
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10444b.m9889r0(view);
        }
    };

    /* renamed from: I */
    public View.OnClickListener f8954I = new View.OnClickListener() { // from class: com.cyberlink.you.activity.gf
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10695b.m9891s0(view);
        }
    };

    /* renamed from: J */
    public View.OnClickListener f8955J = new View.OnClickListener() { // from class: com.cyberlink.you.activity.hf
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10726b.m9893u0(view);
        }
    };

    /* renamed from: K */
    public View.OnClickListener f8956K = new ViewOnClickListenerC1722b();

    /* renamed from: L */
    public View.OnClickListener f8957L = new View.OnClickListener() { // from class: com.cyberlink.you.activity.if
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10756b.m9896w0(view);
        }
    };

    /* renamed from: M */
    public View.OnClickListener f8958M = new View.OnClickListener() { // from class: com.cyberlink.you.activity.re
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11145b.m9897x0(view);
        }
    };

    /* renamed from: N */
    public final View.OnClickListener f8959N = new View.OnClickListener() { // from class: com.cyberlink.you.activity.af
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f9723b.m9882n0(view);
        }
    };

    /* renamed from: O */
    public final InterfaceC0673c f8960O = new C1725e();

    /* renamed from: P */
    public FriendsClient.InterfaceC3051i f8961P = new C1726f();

    /* renamed from: com.cyberlink.you.activity.SettingActivity$a */
    public class ViewOnClickListenerC1721a implements View.OnClickListener {
        public ViewOnClickListenerC1721a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m9921d(boolean z8, final boolean z9, final View view, DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) throws JSONException {
            if ("200".equals(str3)) {
                ULogUtility.m16680p("SettingActivity", "sync info after update info from server, result = " + str4);
                UserInfo userInfoM9903F0 = SettingActivity.this.m9903F0(str4);
                if (userInfoM9903F0 != null) {
                    SettingActivity.this.f8966g = userInfoM9903F0;
                    CLUtility.m16527e(SettingActivity.this.m9915c0(), SettingActivity.this.f8966g);
                    SettingActivity.this.m9913P0();
                    SettingActivity.this.m9909L0();
                    if (z8 && !z9) {
                        Globals.m7388i0().m7401A3(true);
                    }
                }
            } else {
                SettingActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.kf
                    @Override // java.lang.Runnable
                    public final void run() {
                        view.setSelected(z9);
                    }
                });
            }
            dialogC3133q.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) throws JSONException {
            final boolean zIsSelected = view.isSelected();
            view.setSelected(!zIsSelected);
            final boolean z8 = view == SettingActivity.this.findViewById(R.id.SettingAutoSendInvitationArea);
            JSONObject jSONObjectM9902E0 = SettingActivity.this.m9902E0();
            if (jSONObjectM9902E0 == null) {
                ULogUtility.m16676l("SettingActivity", "packageAttrsObject fail");
                view.setSelected(zIsSelected);
                return;
            }
            final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(SettingActivity.this).m16413d(0L).m16411b();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("attrs", jSONObjectM9902E0.toString()));
            FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.jf
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                    this.f10789a.m9921d(z8, zIsSelected, view, dialogC3133qM16411b, str, str2, str3, str4);
                }
            };
            ULogUtility.m16680p("SettingActivity", "update info to server.");
            SettingActivity.this.f8965f.m15734m("user", "updateUser", arrayList, interfaceC3051i);
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingActivity$b */
    public class ViewOnClickListenerC1722b implements View.OnClickListener {
        public ViewOnClickListenerC1722b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m9925d(final View view, final boolean z8, DialogC3133q dialogC3133q, String str, String str2, String str3, String str4) throws JSONException {
            if ("200".equals(str3)) {
                ULogUtility.m16680p("SettingActivity", "sync info after update info from server, result = " + str4);
                UserInfo userInfoM9903F0 = SettingActivity.this.m9903F0(str4);
                if (userInfoM9903F0 != null) {
                    SettingActivity.this.f8966g = userInfoM9903F0;
                    CLUtility.m16527e(SettingActivity.this.m9915c0(), SettingActivity.this.f8966g);
                }
            } else {
                SettingActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.mf
                    @Override // java.lang.Runnable
                    public final void run() {
                        view.setSelected(z8);
                    }
                });
            }
            dialogC3133q.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) throws JSONException {
            final DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(SettingActivity.this).m16413d(0L).m16411b();
            final boolean zIsSelected = view.isSelected();
            view.setSelected(!zIsSelected);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dou.allow.meeting.recording", SettingActivity.this.f8979t.isSelected() ? "1" : "0");
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                arrayList.add(new C6301o("attrs", jSONObject.toString()));
                FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.lf
                    @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                    /* renamed from: a */
                    public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                        this.f10859a.m9925d(view, zIsSelected, dialogC3133qM16411b, str, str2, str3, str4);
                    }
                };
                ULogUtility.m16680p("SettingActivity", "update info to server.");
                SettingActivity.this.f8965f.m15734m("user", "updateUser", arrayList, interfaceC3051i);
            } catch (Exception unused) {
                Log.e("SettingActivity", "Generate allow recording object Fail");
                dialogC3133qM16411b.dismiss();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingActivity$c */
    public class AsyncTaskC1723c extends AsyncTask<Void, Void, Integer> {

        /* renamed from: a */
        public final /* synthetic */ RelativeLayout f8988a;

        public AsyncTaskC1723c(RelativeLayout relativeLayout) {
            this.f8988a = relativeLayout;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(C2950b0.m14899A().m15033y());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            int iIntValue = num.intValue();
            ((TextView) this.f8988a.findViewById(R.id.EditGotoTitleTextView)).setText(SettingActivity.this.getString(R.string.setting_block_users_title) + iIntValue);
            this.f8988a.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Setting_Blocked");
            this.f8988a.setOnClickListener(SettingActivity.this.f8951F);
            if (iIntValue <= 0) {
                this.f8988a.setClickable(false);
                this.f8988a.findViewById(R.id.EditGotoImageView).setVisibility(4);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingActivity$d */
    public class AsyncTaskC1724d extends AsyncTask<Void, Void, String> {

        /* renamed from: a */
        public final /* synthetic */ Uri f8990a;

        public AsyncTaskC1724d(Uri uri) {
            this.f8990a = uri;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) {
            Ringtone ringtone = RingtoneManager.getRingtone(SettingActivity.this.m9915c0(), this.f8990a);
            return ringtone == null ? "" : CLUtility.m16542h2(ringtone.getTitle(SettingActivity.this.m9915c0()));
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            if (SettingActivity.this.f8966g == null || !SettingActivity.this.f8966g.f13792q) {
                SettingActivity.this.f8975p.setVisibility(8);
            } else {
                SettingActivity.this.f8975p.setVisibility(0);
            }
            SettingActivity.this.f8970k.setText(SettingActivity.this.getResources().getString(R.string.setting_notifications_sound_select_title, str));
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingActivity$e */
    public class C1725e implements InterfaceC0673c {
        public C1725e() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.SettingActivity$f */
    public class C1726f implements FriendsClient.InterfaceC3051i {
        public C1726f() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) throws JSONException {
            if ("200".equals(str3)) {
                if (!Globals.m7388i0().m7409C1().booleanValue()) {
                    ULogUtility.m16680p("SettingActivity", "get info from server, result = " + str4);
                }
                UserInfo userInfoM9903F0 = SettingActivity.this.m9903F0(str4);
                if (userInfoM9903F0 != null) {
                    SettingActivity.this.f8966g = userInfoM9903F0;
                    CLUtility.m16527e(SettingActivity.this.m9915c0(), SettingActivity.this.f8966g);
                    SettingActivity.this.m9913P0();
                    Log.i("SettingActivity", "[getSelfInfo] succeed");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m9854C0() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("U", 0);
        this.f8967h.setSelected(sharedPreferences.getBoolean("setting_notification_incoming_call", f8942Q));
        this.f8968i.setSelected(sharedPreferences.getBoolean("setting_notification_sound", f8943R));
        this.f8969j.setSelected(sharedPreferences.getBoolean("setting_notification_vibrate", f8944S));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m9856D0() {
        this.f8972m.setSelected(this.f8966g.f13792q);
        this.f8983x.setSelected(this.f8966g.f13795t);
        this.f8984y.setSelected(this.f8966g.f13796u);
        this.f8946A.setSelected(this.f8966g.f13771A);
        this.f8977r.setSelected(!this.f8966g.f13797v);
        if (this.f8966g.m15773d()) {
            this.f8977r.setSelected(false);
            this.f8977r.setEnabled(false);
            this.f8977r.setAlpha(0.3f);
        }
        m9908K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g0 */
    public /* synthetic */ void m9871g0(View view) {
        view.setSelected(!view.isSelected());
        AbstractC5146g0.m20050h("lockScreenEnabled", Boolean.valueOf(view.isSelected()), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i0 */
    public /* synthetic */ void m9873i0(View view) {
        Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        intent.putExtra("android.provider.extra.CHANNEL_ID", NotificationHelper.m14102r());
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j0 */
    public /* synthetic */ void m9875j0(View view) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getApplicationContext().getPackageName());
            intent.putExtra("app_uid", getApplicationContext().getApplicationInfo().uid);
            intent.putExtra("android.provider.extra.APP_PACKAGE", getApplicationContext().getPackageName());
            startActivity(intent);
        } catch (ActivityNotFoundException e9) {
            ULogUtility.m16680p("SettingActivity", "go to OS notification setting exception " + e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m9877k0(ScrollView scrollView) {
        scrollView.scrollTo(0, this.f8982w.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m9879l0(View view) {
        m9914b0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m9880m0(View view) {
        boolean z8;
        String str = (String) view.getTag();
        str.hashCode();
        switch (str) {
            case "setting_notification_sound":
                z8 = f8943R;
                break;
            case "setting_notification_incoming_call":
                z8 = f8942Q;
                break;
            case "setting_notification_vibrate":
                z8 = f8944S;
                break;
            default:
                return;
        }
        getApplicationContext().getSharedPreferences("U", 0).edit().putBoolean(str, !r2.getBoolean(str, z8)).apply();
        if (str.equals("setting_notification_sound")) {
            NotificationHelper.m14084a0();
            m9911N0();
        }
        m9912O0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m9882n0(View view) {
        if (this.f8963d.m24458a()) {
            new SelectServerDialog().m16355h(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o0 */
    public /* synthetic */ void m9884o0(View view) {
        startActivityForResult(new Intent(m9915c0(), (Class<?>) RingtonePickerActivity.class), 304);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m9885p0(View view) {
        startActivityForResult(new Intent(m9915c0(), (Class<?>) BlockUserActivity.class), 301);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m9887q0(View view) {
        startActivityForResult(new Intent(m9915c0(), (Class<?>) AboutPageActivity.class), 302);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m9889r0(View view) {
        Intent intent = new Intent(m9915c0(), (Class<?>) SettingPrivacyPasswordActivity.class);
        intent.putExtra("privacyType", "privacyPassword");
        startActivityForResult(intent, 303);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m9891s0(View view) {
        Intent intent = new Intent(m9915c0(), (Class<?>) SettingPrivacyDeviceActivity.class);
        intent.putExtra("privacyType", "privacyPassword");
        startActivityForResult(intent, 303);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m9893u0(View view) {
        startActivityForResult(new Intent(m9915c0(), (Class<?>) SettingFontSizeActivity.class), 305);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w0 */
    public /* synthetic */ void m9896w0(View view) {
        startActivity(new Intent(m9915c0(), (Class<?>) PrivacyControlActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x0 */
    public /* synthetic */ void m9897x0(View view) {
        startActivity(new Intent(m9915c0(), (Class<?>) ProxyConfigActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m9899y0(View view) {
        startActivity(new Intent(this, (Class<?>) UpgradeToProUserActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z0 */
    public /* synthetic */ void m9901z0() {
        boolean zM14059C = NotificationHelper.m14059C();
        this.f8982w.setVisibility(zM14059C ? 8 : 0);
        this.f8981v.setVisibility(zM14059C ? 0 : 8);
        if (zM14059C) {
            if (!this.f8966g.f13792q) {
                this.f8974o.setVisibility(8);
                this.f8975p.setVisibility(8);
                this.f8976q.setVisibility(8);
                this.f8977r.setVisibility(8);
                this.f8978s.setVisibility(8);
                this.f8985z.setVisibility(8);
                return;
            }
            this.f8974o.setVisibility(0);
            if (this.f8968i.isSelected()) {
                this.f8975p.setVisibility(0);
            }
            this.f8977r.setVisibility(0);
            this.f8978s.setVisibility(0);
            this.f8976q.setVisibility(8);
            this.f8985z.setVisibility(0);
        }
    }

    /* renamed from: E0 */
    public final JSONObject m9902E0() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            String str = "1";
            jSONObject.put("notification.disabled", !this.f8972m.isSelected() ? "1" : "0");
            jSONObject.put("friend.autoInvite.enabled", this.f8983x.isSelected() ? "1" : "0");
            jSONObject.put("friend.autoAccept.enabled", this.f8984y.isSelected() ? "1" : "0");
            jSONObject.put("notification.hide.message", this.f8977r.isSelected() ? "0" : "1");
            if (!this.f8946A.isSelected()) {
                str = "0";
            }
            jSONObject.put("peopleSearch.allowed", str);
            return jSONObject;
        } catch (Exception unused) {
            Log.e("SettingActivity", "[packageAttrsObject] Fail");
            return null;
        }
    }

    /* renamed from: F0 */
    public final UserInfo m9903F0(String str) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            Log.e("SettingActivity", "[SuggestionListFriends] Parse error. JSONstr=" + str);
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        try {
            jSONObject2 = jSONObject.getJSONObject("result");
        } catch (JSONException unused2) {
            Log.e("SettingActivity", "[SuggestionListFriends] 'results' missing. JSONstr=" + str);
            jSONObject2 = null;
        }
        if (jSONObject2 != null) {
            return C5172p.m20197s(jSONObject2);
        }
        return null;
    }

    /* renamed from: G0 */
    public final void m9904G0() {
        if (Globals.m7388i0().m7484S1()) {
            TextView textView = (TextView) this.f8971l.findViewById(R.id.accountType);
            String string = getString(R.string.setting_pro_account);
            if (Globals.m7388i0().m7403B0() > 0) {
                string = string + StringUtils.SPACE + Globals.m7388i0().m7403B0();
            }
            textView.setText(string);
            this.f8971l.findViewById(R.id.proPlanLink).setVisibility(8);
            findViewById(R.id.accountTypeDescription).setVisibility(8);
        } else {
            TextView textView2 = (TextView) findViewById(R.id.accountTypeDescription);
            int iM7408C0 = Globals.m7388i0().m7408C0();
            if (iM7408C0 > 0) {
                textView2.setVisibility(0);
                textView2.setText(Html.fromHtml(m9915c0().getString(R.string.setting_basic_description_with_duration, Integer.valueOf(iM7408C0)), 0));
            } else {
                textView2.setVisibility(8);
            }
            if (Globals.m7388i0().m7591p()) {
                this.f8971l.findViewById(R.id.proPlanLink).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ue
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f11436b.m9899y0(view);
                    }
                });
            } else {
                this.f8971l.findViewById(R.id.proPlanLink).setVisibility(8);
            }
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.EmailAccountItem);
        ((TextView) relativeLayout.findViewById(R.id.EditGotoTitleTextView)).setText(getString(R.string.friends_invitation_selector_string_email));
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.FaceIdItem);
        relativeLayout2.setVisibility(8);
        ((TextView) relativeLayout2.findViewById(R.id.EditGotoTitleTextView)).setText(getString(R.string.connect_face_id));
        relativeLayout2.findViewById(R.id.EditGotoImageView).setVisibility(8);
        if (CLUtility.m16497V0(this) == null) {
            return;
        }
        boolean zM7583n0 = Globals.m7388i0().m7583n0();
        relativeLayout.findViewById(R.id.EditGotoImageView).setVisibility(8);
        if (zM7583n0) {
            ((TextView) relativeLayout.findViewById(R.id.EditGotoTextView)).setText(Globals.m7388i0().m7498V0());
            relativeLayout.findViewById(R.id.EditWarningImageView).setVisibility(8);
        }
        if (relativeLayout2.getVisibility() != 0 || this.f8964e.m3392e()) {
            return;
        }
        if (Globals.m7388i0().m7429G1()) {
            ((TextView) relativeLayout2.findViewById(R.id.EditGotoTextView)).setText(R.string.done_btn);
            relativeLayout2.findViewById(R.id.EditWarningImageView).setVisibility(8);
            this.f8964e.m3387a(relativeLayout2);
        } else {
            ((TextView) relativeLayout2.findViewById(R.id.EditGotoTextView)).setText(getString(R.string.create));
            relativeLayout2.findViewById(R.id.EditWarningImageView).setVisibility(0);
            this.f8964e.m3387a(relativeLayout2);
        }
    }

    /* renamed from: H0 */
    public final void m9905H0() {
        new AsyncTaskC1723c((RelativeLayout) findViewById(R.id.SettingBlockUserArea)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: I0 */
    public final void m9906I0() {
        View viewFindViewById = findViewById(R.id.SettingPrivacyDevice);
        viewFindViewById.setOnClickListener(this.f8954I);
        viewFindViewById.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Setting_Device");
        ((TextView) viewFindViewById.findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_privacy_devices));
    }

    /* renamed from: J0 */
    public final void m9907J0() {
        View viewFindViewById = findViewById(R.id.FontSizeSettingArea);
        viewFindViewById.setOnClickListener(this.f8955J);
        viewFindViewById.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Settings_FontSize");
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.FontSizeItem);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.EditGotoTextView);
        ((TextView) relativeLayout.findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_chat_font_size_title));
        textView.setText(getResources().getString(R.string.setting_on));
        if (Globals.m7388i0().m7519a0() == 0) {
            textView.setText(getResources().getString(R.string.setting_small));
            return;
        }
        if (Globals.m7388i0().m7519a0() == 1) {
            textView.setText(getResources().getString(R.string.setting_normal));
            return;
        }
        if (Globals.m7388i0().m7519a0() == 2) {
            textView.setText(getResources().getString(R.string.setting_large));
        } else if (Globals.m7388i0().m7519a0() == 3) {
            textView.setText(getResources().getString(R.string.setting_extra_large));
        } else {
            textView.setText(getResources().getString(R.string.setting_normal));
        }
    }

    /* renamed from: K0 */
    public final void m9908K0() {
        if (this.f8979t == null) {
            View viewFindViewById = findViewById(R.id.AllowRecordingSettingArea);
            this.f8979t = viewFindViewById;
            ((TextView) viewFindViewById.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.clm_meeting_allow_recording));
            this.f8979t.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_AllowRecording");
            this.f8980u = (TextView) findViewById(R.id.AllowRecordingNote);
        }
        UserInfo userInfo = this.f8966g;
        if (userInfo != null) {
            if (userInfo.m15772c()) {
                this.f8979t.setSelected(false);
                this.f8979t.setEnabled(false);
                this.f8979t.setAlpha(0.3f);
                this.f8980u.setText(R.string.setting_meeting_forbid_allow_recording_note);
            } else {
                this.f8979t.setEnabled(true);
                this.f8979t.setAlpha(1.0f);
                this.f8979t.setSelected(this.f8966g.m15771b());
                this.f8980u.setText(R.string.setting_meeting_allow_recording_introduce_v2);
            }
        }
        this.f8979t.setOnClickListener(this.f8956K);
    }

    /* renamed from: L0 */
    public final void m9909L0() {
        if (this.f8966g == null) {
            return;
        }
        m9915c0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.se
            @Override // java.lang.Runnable
            public final void run() {
                this.f11176b.m9901z0();
            }
        });
    }

    /* renamed from: M0 */
    public final void m9910M0() {
        View viewFindViewById = findViewById(R.id.SettingPrivacyPasswordLock);
        viewFindViewById.setOnClickListener(this.f8953H);
        viewFindViewById.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Setting_Password");
        TextView textView = (TextView) viewFindViewById.findViewById(R.id.EditGotoTextView);
        ((TextView) viewFindViewById.findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_privacy_password_title));
        if (Globals.m7388i0().m7468P0() == 0) {
            textView.setText(getResources().getString(R.string.setting_off));
        } else {
            textView.setText(getResources().getString(R.string.setting_on));
        }
    }

    /* renamed from: N0 */
    public final void m9911N0() {
        Uri uriM7433H0 = Globals.m7388i0().m7433H0(false);
        if (uriM7433H0 != null) {
            new AsyncTaskC1724d(uriM7433H0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            this.f8975p.setVisibility(8);
            this.f8970k.setText(getResources().getString(R.string.setting_notifications_sound_select_title, ""));
        }
    }

    /* renamed from: O0 */
    public final void m9912O0() {
        m9915c0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.te
            @Override // java.lang.Runnable
            public final void run() {
                this.f11399b.m9854C0();
            }
        });
    }

    /* renamed from: P0 */
    public final void m9913P0() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("U", 0);
        sharedPreferences.edit().putBoolean("setting_notification", this.f8966g.f13792q).apply();
        sharedPreferences.edit().putBoolean("setting_notification_show_preview", !this.f8966g.f13797v).apply();
        m9915c0().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ze
            @Override // java.lang.Runnable
            public final void run() {
                this.f12306b.m9856D0();
            }
        });
    }

    /* renamed from: b0 */
    public final void m9914b0() {
        Globals.m7388i0().m7470P2(true);
        Globals.m7388i0().m7421E3(false);
        finish();
    }

    /* renamed from: c0 */
    public final Activity m9915c0() {
        return this;
    }

    /* renamed from: d0 */
    public final void m9916d0() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        this.f8965f.m15734m("user", "userInfoV2", arrayList, this.f8961P);
    }

    /* renamed from: e0 */
    public final void m9917e0() {
        ((ImageView) findViewById(R.id.SettingBackBtn)).setOnClickListener(this.f8947B);
        ((TextView) ((RelativeLayout) findViewById(R.id.SettingEmailArea)).findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_account_link_email_title));
        ((TextView) ((RelativeLayout) findViewById(R.id.SettingFacebookArea)).findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_account_link_facebook_title));
        findViewById(R.id.AccountTypeTextView).setOnClickListener(this.f8959N);
        this.f8971l = findViewById(R.id.accountTypeItem);
        View viewFindViewById = findViewById(R.id.SettingNotificationItemArea);
        this.f8972m = viewFindViewById;
        ((TextView) viewFindViewById.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_notifications_message_notifications_title));
        this.f8972m.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_Notification");
        this.f8972m.setOnClickListener(this.f8948C);
        UserInfo userInfo = this.f8966g;
        if (userInfo != null) {
            this.f8972m.setSelected(userInfo.f13792q);
        }
        View viewFindViewById2 = findViewById(R.id.SettingIncomingCallNotificationArea);
        this.f8973n = viewFindViewById2;
        ((TextView) viewFindViewById2.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_notifications_incoming_call_title));
        this.f8967h = (ImageView) this.f8973n.findViewById(R.id.EditCheckImageView);
        this.f8973n.setOnClickListener(this.f8949D);
        this.f8973n.setTag("setting_notification_incoming_call");
        View viewFindViewById3 = findViewById(R.id.SettingSoundArea);
        this.f8974o = viewFindViewById3;
        ((TextView) viewFindViewById3.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_notifications_sound_title));
        ImageView imageView = (ImageView) this.f8974o.findViewById(R.id.EditCheckImageView);
        this.f8968i = imageView;
        imageView.setContentDescription("[AID]Settings_Sound");
        this.f8974o.setOnClickListener(this.f8949D);
        this.f8974o.setTag("setting_notification_sound");
        View viewFindViewById4 = findViewById(R.id.SettingSoundSelectArea);
        this.f8975p = viewFindViewById4;
        TextView textView = (TextView) viewFindViewById4.findViewById(R.id.EditGotoTitleTextView);
        this.f8970k = textView;
        textView.setTextColor(getResources().getColor(R.color.you_color_normal_gray));
        this.f8975p.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Settings_SoundSelect");
        this.f8975p.setOnClickListener(this.f8950E);
        m9911N0();
        View viewFindViewById5 = findViewById(R.id.SettingVibrateArea);
        this.f8976q = viewFindViewById5;
        ((TextView) viewFindViewById5.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_notifications_vibrate_title));
        ImageView imageView2 = (ImageView) this.f8976q.findViewById(R.id.EditCheckImageView);
        this.f8969j = imageView2;
        imageView2.setContentDescription("[AID]Settings_Vibrate");
        this.f8976q.setOnClickListener(this.f8949D);
        this.f8976q.setTag("setting_notification_vibrate");
        View viewFindViewById6 = findViewById(R.id.SettingShowPreviewArea);
        this.f8977r = viewFindViewById6;
        ((TextView) viewFindViewById6.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.setting_notifications_show_preview_title));
        this.f8977r.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_ShowPreview");
        this.f8977r.setOnClickListener(this.f8948C);
        if (this.f8966g != null) {
            this.f8977r.setSelected(!r0.f13797v);
            if (this.f8966g.m15773d()) {
                this.f8977r.setSelected(false);
                this.f8977r.setEnabled(false);
                this.f8977r.setAlpha(0.3f);
            }
        }
        View viewFindViewById7 = findViewById(R.id.relativeLayoutLockScreenSetting);
        this.f8978s = viewFindViewById7;
        ((TextView) viewFindViewById7.findViewById(R.id.EditCheckTextView)).setText(getString(R.string.popup_when_screen_is_off));
        this.f8978s.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_LockScreenSettings");
        this.f8978s.setSelected(AbstractC5146g0.m20046d("lockScreenEnabled", true, this));
        this.f8978s.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.ve
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11819b.m9871g0(view);
            }
        });
        View viewFindViewById8 = findViewById(R.id.OSNotificationSettingLink);
        this.f8985z = viewFindViewById8;
        ((TextView) viewFindViewById8.findViewById(R.id.EditGotoTitleTextView)).setText(R.string.setting_more_notification_control);
        this.f8985z.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.we
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11851b.m9873i0(view);
            }
        });
        this.f8981v = findViewById(R.id.SettingNotificationArea);
        this.f8982w = findViewById(R.id.goToNotificationSettingArea);
        findViewById(R.id.goToNotificationSettingBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.xe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12254b.m9875j0(view);
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("IS_NEED_SCROLL_TO_NOTIFICATION_AREA") && extras.getBoolean("IS_NEED_SCROLL_TO_NOTIFICATION_AREA", false)) {
            final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
            scrollView.post(new Runnable() { // from class: com.cyberlink.you.activity.ye
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12279b.m9877k0(scrollView);
                }
            });
        }
        m9909L0();
        m9904G0();
        m9907J0();
        m9908K0();
        m9910M0();
        m9906I0();
        View viewFindViewById9 = findViewById(R.id.SettingAutoSendInvitationArea);
        this.f8983x = viewFindViewById9;
        ((TextView) viewFindViewById9.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.profile_auto_invite_title));
        this.f8983x.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_AutoInvite");
        UserInfo userInfo2 = this.f8966g;
        if (userInfo2 != null) {
            this.f8983x.setSelected(userInfo2.f13795t);
        }
        this.f8983x.setOnClickListener(this.f8948C);
        View viewFindViewById10 = findViewById(R.id.SettingAutoAcceptInvitationArea);
        this.f8984y = viewFindViewById10;
        ((TextView) viewFindViewById10.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.profile_auto_accept_title));
        this.f8984y.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_AutoAccept");
        UserInfo userInfo3 = this.f8966g;
        if (userInfo3 != null) {
            this.f8984y.setSelected(userInfo3.f13796u);
        }
        this.f8984y.setOnClickListener(this.f8948C);
        View viewFindViewById11 = findViewById(R.id.SettingAllowSearchArea);
        this.f8946A = viewFindViewById11;
        ((TextView) viewFindViewById11.findViewById(R.id.EditCheckTextView)).setText(getResources().getString(R.string.profile_allow_search_title));
        this.f8946A.findViewById(R.id.EditCheckImageView).setContentDescription("[AID]Settings_AllowSearch");
        UserInfo userInfo4 = this.f8966g;
        if (userInfo4 != null) {
            this.f8946A.setSelected(userInfo4.f13771A);
        }
        this.f8946A.setOnClickListener(this.f8948C);
        View viewFindViewById12 = findViewById(R.id.SettingMorePrivacyArea);
        viewFindViewById12.setOnClickListener(this.f8957L);
        viewFindViewById12.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Setting_MorePrivacy");
        ((TextView) viewFindViewById12.findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_more_privacy_control));
        View viewFindViewById13 = findViewById(R.id.SettingProxyConfig);
        viewFindViewById13.setOnClickListener(this.f8958M);
        ((TextView) viewFindViewById13.findViewById(R.id.EditGotoTitleTextView)).setText("Config proxy");
        m9905H0();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.SettingAboutPageArea);
        relativeLayout.findViewById(R.id.EditGotoImageView).setContentDescription("[AID]Settings_About");
        ((TextView) relativeLayout.findViewById(R.id.EditGotoTitleTextView)).setText(getResources().getString(R.string.setting_about));
        relativeLayout.setOnClickListener(this.f8952G);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        C0676f c0676f = this.f8964e;
        if (c0676f != null) {
            c0676f.m3393f(i9, i10, intent);
        }
        switch (i9) {
            case 301:
                m9905H0();
                break;
            case 302:
                if (i10 == -1) {
                    finish();
                    break;
                }
                break;
            case 303:
                if (i10 == -1 && intent.getBooleanExtra("newPrivacyPassword", false) && this.f8977r.isSelected()) {
                    this.f8977r.callOnClick();
                }
                m9910M0();
                break;
            case 304:
                if (i10 == -1) {
                    Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
                    if (uri == null) {
                        this.f8975p.setVisibility(8);
                        getApplicationContext().getSharedPreferences("U", 0).edit().putBoolean("setting_notification_sound", !r3.getBoolean("setting_notification_sound", f8943R)).apply();
                        m9912O0();
                        break;
                    } else {
                        Globals.m7388i0().m7436H3(uri);
                        m9911N0();
                        break;
                    }
                }
                break;
            case 305:
                m9907J0();
                break;
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m9914b0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        this.f8965f = new FriendsClient(true);
        this.f8964e = new C0676f(this.f8965f, this, this.f8960O, 306);
        if (Globals.m7380c2()) {
            this.f8964e.m3394h(Globals.m7388i0().m7506X());
        }
        this.f8966g = CLUtility.m16497V0(this);
        m9917e0();
        m9912O0();
        m9916d0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FriendsClient friendsClient = this.f8965f;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        m9904G0();
        m9909L0();
    }
}
