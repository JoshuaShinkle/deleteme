package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.cyberlink.clrtc.C1121t;
import com.cyberlink.link.detect.CustomURLSpan;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.page.ConfirmPwdActivity;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.activity.webinar.C2698m2;
import com.cyberlink.you.feedback.C3031d;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import p044d2.C4664a;
import p044d2.C4665b;
import p047d5.C4677a;
import p116k4.C5152i0;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p135m2.C5314a;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6366c;
import p209u2.C6385v;
import p218v2.C6456d;

/* loaded from: classes.dex */
public class JoinCLMWActivity extends BaseActivity {

    /* renamed from: q */
    public static final String f7988q = "JoinCLMWActivity";

    /* renamed from: c */
    public EditText f7989c;

    /* renamed from: d */
    public EditText f7990d;

    /* renamed from: e */
    public String f7991e;

    /* renamed from: f */
    public DialogC3133q f7992f;

    /* renamed from: i */
    public String f7995i;

    /* renamed from: j */
    public String f7996j;

    /* renamed from: l */
    public String f7998l;

    /* renamed from: m */
    public String f7999m;

    /* renamed from: n */
    public String f8000n;

    /* renamed from: o */
    public String f8001o;

    /* renamed from: g */
    public final C6366c f7993g = new C6366c(5);

    /* renamed from: h */
    public boolean f7994h = false;

    /* renamed from: k */
    public boolean f7997k = false;

    /* renamed from: p */
    public final View.OnClickListener f8002p = new View.OnClickListener() { // from class: com.cyberlink.you.activity.r6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11135b.m8667u0(view);
        }
    };

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$a */
    public class C1538a extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ String f8003j;

        /* renamed from: k */
        public final /* synthetic */ String f8004k;

        /* renamed from: l */
        public final /* synthetic */ String f8005l;

        /* renamed from: m */
        public final /* synthetic */ Bundle f8006m;

        public C1538a(String str, String str2, String str3, Bundle bundle) {
            this.f8003j = str;
            this.f8004k = str2;
            this.f8005l = str3;
            this.f8006m = bundle;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            JoinCLMWActivity.this.f7992f.dismiss();
            JoinCLMWActivity.this.m8699k0(i9, str, this.f8006m);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            JoinCLMWActivity.this.f7992f.dismiss();
            String str = !C5170o0.m20170e(meeting.callType) ? meeting.callType.equals("VOICE") ? MimeTypes.BASE_TYPE_AUDIO : MimeTypes.BASE_TYPE_VIDEO : null;
            JoinCLMWActivity joinCLMWActivity = JoinCLMWActivity.this;
            MeetingActivity.m6565wb(joinCLMWActivity, this.f8003j, this.f8004k, this.f8005l, joinCLMWActivity.f7999m, str, null, meeting.mserverAddr, meeting.token, "join meeting page w/o pwd");
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$b */
    public class C1539b extends GestureDetector.SimpleOnGestureListener {
        public C1539b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(JoinCLMWActivity.this);
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$c */
    public class C1540c implements TextWatcher {

        /* renamed from: b */
        public boolean f8009b = false;

        /* renamed from: c */
        public boolean f8010c = false;

        /* renamed from: d */
        public int f8011d = -1;

        /* renamed from: e */
        public int f8012e = -1;

        /* renamed from: f */
        public final char f8013f = '-';

        /* renamed from: g */
        public final int f8014g = 1;

        /* renamed from: h */
        public final int f8015h = 3;

        /* renamed from: i */
        public final /* synthetic */ EditText f8016i;

        public C1540c(EditText editText) {
            this.f8016i = editText;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int i9;
            if (this.f8009b) {
                return;
            }
            StringBuilder sb = new StringBuilder(editable.toString().replace("-", ""));
            int i10 = this.f8011d + this.f8012e;
            if (this.f8010c) {
                i10 = -1;
                for (int i11 = 0; i11 < this.f8011d; i11++) {
                    if (Character.isDigit(editable.charAt(i11))) {
                        i10++;
                    }
                }
                if (i10 > 0) {
                    sb.delete(i10, i10 + 1);
                } else {
                    i10 = 0;
                }
            }
            for (int length = (sb.length() - 1) / 3; length > 0; length--) {
                sb.insert(length * 3, '-');
            }
            if (editable.toString().equals(sb.toString())) {
                return;
            }
            if (!this.f8010c && ((i9 = this.f8011d) == 3 || i9 == 7)) {
                i10++;
            }
            SpannableString spannableString = new SpannableString(sb);
            for (int i12 = 3; i12 < sb.length(); i12 += 4) {
                spannableString.setSpan(new ForegroundColorSpan(-3355444), i12, i12 + 1, 33);
            }
            this.f8009b = true;
            editable.replace(0, editable.length(), spannableString);
            if (i10 < 0) {
                i10 = 0;
            }
            this.f8016i.setSelection(Math.min(i10, editable.length()));
            this.f8009b = false;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (this.f8009b) {
                return;
            }
            this.f8010c = i10 == 1 && '-' == charSequence.charAt(i9);
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (this.f8009b) {
                return;
            }
            this.f8011d = i9;
            this.f8012e = i11;
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$d */
    public class C1541d extends AbstractC6381r<Live, Integer> {

        /* renamed from: c */
        public final /* synthetic */ String f8018c;

        /* renamed from: d */
        public final /* synthetic */ String f8019d;

        /* renamed from: e */
        public final /* synthetic */ long f8020e;

        /* renamed from: f */
        public final /* synthetic */ String f8021f;

        public C1541d(String str, String str2, long j9, String str3) {
            this.f8018c = str;
            this.f8019d = str2;
            this.f8020e = j9;
            this.f8021f = str3;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Live live) {
            if (NetworkLive.STATE.LIVE.equals(live.status.state) && C5170o0.m20170e(this.f8018c)) {
                JoinCLMWActivity.this.m8683P0(R.string.clmw_join_invalid_name);
            } else {
                C2698m2.m13955g(this.f8019d, this.f8020e, this.f8021f, JoinCLMWActivity.this, JoinCLMWActivity.f7988q, JoinCLMWActivity.this.f7992f, JoinCLMWActivity.this.f7994h, JoinCLMWActivity.this.f7996j);
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            JoinCLMWActivity.this.m8683P0(R.string.ycl_live_or_meeting_not_exist);
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$e */
    public class C1542e extends PromisedTask.AbstractC4504d<Live> {

        /* renamed from: a */
        public final /* synthetic */ AbstractC6381r f8023a;

        public C1542e(AbstractC6381r abstractC6381r) {
            this.f8023a = abstractC6381r;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Live live) {
            C4677a.m18693T("queryLive");
            JoinCLMWActivity.this.f7992f.dismiss();
            this.f8023a.m24506d(live);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            super.onError(i9);
            JoinCLMWActivity.this.f7992f.dismiss();
            if (C6456d.m24714D().m24748G()) {
                this.f8023a.m24508f(Integer.valueOf(i9));
            } else {
                JoinCLMWActivity.this.m8683P0(R.string.error_server_response);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$f */
    public class C1543f extends AbstractC6381r<Live, Integer> {

        /* renamed from: c */
        public final /* synthetic */ String f8025c;

        public C1543f(String str) {
            this.f8025c = str;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Live live) throws Exception {
            if (!this.f8025c.equals(live.eventId)) {
                JoinCLMWActivity.this.f7991e = this.f8025c;
            }
            if (C5170o0.m20169d(live.eventId) || "0".equals(live.eventId)) {
                JoinCLMWActivity.this.f7989c.setText("");
            } else {
                JoinCLMWActivity.this.f7989c.setText(live.eventId);
                JoinCLMWActivity.this.f7989c.setSelection(JoinCLMWActivity.this.f7989c.getText().length());
            }
            if (NetworkLive.STATE.VIDEO_ON_DEMAND.equals(live.status.state)) {
                JoinCLMWActivity.this.m8689V0(this.f8025c);
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            JoinCLMWActivity.this.m8683P0(R.string.ycl_live_or_meeting_not_exist);
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$g */
    public class DialogInterfaceOnClickListenerC1544g implements DialogInterface.OnClickListener {
        public DialogInterfaceOnClickListenerC1544g() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            SharedPreferences sharedPreferences = JoinCLMWActivity.this.getSharedPreferences("U", 0);
            sharedPreferences.edit().putString("meetingLtiEventId", JoinCLMWActivity.this.f7998l).apply();
            sharedPreferences.edit().putString("meetingLtiToken", JoinCLMWActivity.this.f7999m).apply();
            sharedPreferences.edit().putString("meetingLtiUserName", JoinCLMWActivity.this.f8001o).apply();
            ULogUtility.m16683s("JoinCLMWActivity", "EventId: " + ((Object) JoinCLMWActivity.this.f7989c.getText()) + " / ltiToken: " + JoinCLMWActivity.this.f7999m + " / UserName: " + JoinCLMWActivity.this.f8001o);
            sharedPreferences.edit().commit();
            JoinCLMWActivity.this.m8684Q0();
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$h */
    public class C1545h extends PromisedTask.AbstractC4504d<String> {

        /* renamed from: a */
        public final /* synthetic */ String f8028a;

        /* renamed from: b */
        public final /* synthetic */ String f8029b;

        public C1545h(String str, String str2) {
            this.f8028a = str;
            this.f8029b = str2;
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            Log.d(JoinCLMWActivity.f7988q, "checkFaceId errorCode:" + i9);
            JoinCLMWActivity.this.m8678K0(this.f8028a, this.f8029b);
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(String str) throws JSONException {
            Log.d(JoinCLMWActivity.f7988q, "checkFaceId result:" + str);
            try {
                Globals.m7388i0().m7452L2(new JSONObject(str).getBoolean("hasFace"));
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            JoinCLMWActivity.this.m8678K0(this.f8028a, this.f8029b);
        }
    }

    /* renamed from: com.cyberlink.you.activity.JoinCLMWActivity$i */
    public class C1546i extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ String f8031j;

        /* renamed from: k */
        public final /* synthetic */ String f8032k;

        public C1546i(String str, String str2) {
            this.f8031j = str;
            this.f8032k = str2;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            JoinCLMWActivity.this.f7992f.dismiss();
            if (i9 == 404) {
                JoinCLMWActivity.this.m8683P0(R.string.clm_error_invalid_id);
            }
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            Bundle bundle = new Bundle();
            bundle.putString("meetingId", this.f8031j);
            bundle.putString("displayName", this.f8032k);
            JoinCLMWActivity.this.m8701m0(this.f8031j, "", bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C0 */
    public /* synthetic */ void m8634C0(CompoundButton compoundButton, boolean z8) {
        C1121t.m5160i(z8);
        if (z8) {
            ((Switch) findViewById(R.id.aospAec)).setChecked(false);
            C4665b.m18663o(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m8636D0(CompoundButton compoundButton, boolean z8) {
        if (!z8 && !C1121t.m5156e()) {
            ((Switch) findViewById(R.id.swtAecm)).setChecked(true);
            return;
        }
        C4665b.m18663o(z8);
        if (z8) {
            ((Switch) findViewById(R.id.swtAecm)).setChecked(false);
            C1121t.m5160i(false);
        }
    }

    /* renamed from: E0 */
    public static /* synthetic */ void m8638E0() {
        Globals.m7388i0().m7402B();
    }

    /* renamed from: F0 */
    public static /* synthetic */ void m8639F0(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G0 */
    public /* synthetic */ void m8640G0() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.feedback_error));
        builderM16382a.setMessage(getString(R.string.sign_out_unsuccessful));
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.w6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                JoinCLMWActivity.m8639F0(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H0 */
    public /* synthetic */ void m8642H0(FriendsClient friendsClient, ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        friendsClient.m15717U0();
        C5152i0.m20065b(progressDialog);
        if (str3 == null || !str3.equals("200")) {
            C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.u6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11428b.m8640G0();
                }
            });
        } else {
            C6385v.m24527e(new Runnable() { // from class: com.cyberlink.you.activity.t6
                @Override // java.lang.Runnable
                public final void run() {
                    JoinCLMWActivity.m8638E0();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ boolean m8659p0(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 6) {
            return false;
        }
        findViewById(R.id.JoinVideoMeetingBtn).performClick();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m8661q0(View view, boolean z8) {
        if (z8) {
            m8691Z();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s0 */
    public /* synthetic */ void m8665s0(DialogInterface dialogInterface, int i9) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u0 */
    public /* synthetic */ void m8667u0(View view) {
        if (this.f7993g.m24458a()) {
            findViewById(R.id.EchoConfigArea).setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w0 */
    public /* synthetic */ void m8670w0(View view) {
        CLUtility.m16589t1(this);
        finish();
    }

    /* renamed from: x0 */
    public static /* synthetic */ boolean m8671x0(GestureDetector gestureDetector, View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y0 */
    public /* synthetic */ void m8673y0(View view) throws Exception {
        if (C5179r0.m20246a()) {
            return;
        }
        CLUtility.m16589t1(this);
        if (this.f7997k && Globals.m7388i0().m7464O1()) {
            String string = Long.toString(CLUtility.m16497V0(this).f13777b);
            if ((!C5170o0.m20170e(this.f8000n) && !this.f8000n.equals(string)) || C5170o0.m20170e(this.f8000n)) {
                m8695e0(R.string.clm_error_join_fail_with_different_account_title, R.string.clm_error_join_fail_with_different_account_content, R.string.clm_error_join_fail_with_different_account_button);
                return;
            }
        }
        m8687T0();
        m8685R0();
    }

    /* renamed from: I0 */
    public final void m8676I0() {
        UserInfo userInfoM16497V0;
        if (!this.f7994h || C5170o0.m20170e(this.f7990d.getText().toString())) {
            if (!C5170o0.m20170e(this.f7999m) && !C5170o0.m20170e(this.f8001o)) {
                this.f7990d.setEnabled(false);
                this.f7990d.setText(this.f8001o);
            } else {
                if (Globals.m7388i0().m7464O1() && (userInfoM16497V0 = CLUtility.m16497V0(this)) != null && !C5170o0.m20170e(userInfoM16497V0.f13778c)) {
                    this.f7990d.setText(userInfoM16497V0.f13778c);
                    return;
                }
                String strM18708l = C4677a.m18708l();
                if (C5170o0.m20170e(strM18708l)) {
                    return;
                }
                this.f7990d.setText(strM18708l);
            }
        }
    }

    /* renamed from: J0 */
    public final void m8677J0(String str, String str2) throws Exception {
        if (C5170o0.m20170e(str)) {
            m8683P0(R.string.clmw_join_wrong_id);
            return;
        }
        if (!C6456d.m24714D().m24748G()) {
            m8683P0(R.string.error_server_response);
            return;
        }
        String strM5292b = CustomURLSpan.m5292b();
        String strM5293c = CustomURLSpan.m5293c();
        try {
            if (str.startsWith(strM5292b)) {
                String strSubstring = str.substring(strM5292b.length());
                Long.parseLong(strSubstring);
                m8702n0(strSubstring, str2);
            } else {
                if (str.startsWith(strM5293c)) {
                    m8703o0(null, str.substring(strM5293c.length()), str2);
                    return;
                }
                String strReplace = str.replace("-", "");
                if (strReplace.length() == 18) {
                    m8703o0(null, strReplace, str2);
                } else {
                    if (strReplace.length() != 9) {
                        throw new Exception("Invalid event id");
                    }
                    if (Long.parseLong(strReplace) % 2 == 0) {
                        m8703o0(strReplace, this.f7991e, str2);
                    } else {
                        m8702n0(strReplace, str2);
                    }
                }
            }
        } catch (Exception unused) {
            m8683P0(R.string.clmw_join_wrong_id);
        }
    }

    /* renamed from: K0 */
    public final void m8678K0(String str, String str2) {
        if (!this.f7992f.isShowing()) {
            this.f7992f.show();
        }
        C1260a.m5680r(str).m15439e(new C1546i(str, str2));
    }

    /* renamed from: L0 */
    public final void m8679L0() {
        findViewById(R.id.JoinMeetingBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.z6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12296b.m8670w0(view);
            }
        });
        final GestureDetector gestureDetector = new GestureDetector(this, new C1539b());
        findViewById(R.id.JoinMeetingArea).setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.a7
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return JoinCLMWActivity.m8671x0(gestureDetector, view, motionEvent);
            }
        });
        findViewById(R.id.JoinVideoMeetingBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.b7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Exception {
                this.f9741b.m8673y0(view);
            }
        });
        m8690Y(this.f7989c);
        m8692b0();
        m8693c0();
    }

    /* renamed from: M0 */
    public final void m8680M0() {
        Switch r02 = (Switch) findViewById(R.id.force16khzSampling);
        r02.setChecked(C5314a.m20794d());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cyberlink.you.activity.d7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                C1121t.m5159h(z8);
            }
        });
    }

    /* renamed from: N0 */
    public final void m8681N0() {
        Switch r02 = (Switch) findViewById(R.id.swtAecm);
        r02.setChecked(C1121t.m5156e());
        r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cyberlink.you.activity.c7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f9774a.m8634C0(compoundButton, z8);
            }
        });
    }

    /* renamed from: O0 */
    public final void m8682O0() {
        Switch r02 = (Switch) findViewById(R.id.aospAec);
        if (!C4664a.m18637i()) {
            m8688U0((ViewGroup) findViewById(R.id.layoutAospAec), false);
        } else {
            r02.setChecked(C4665b.m18666r());
            r02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.cyberlink.you.activity.x6
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                    this.f12248a.m8636D0(compoundButton, z8);
                }
            });
        }
    }

    /* renamed from: P0 */
    public void m8683P0(int i9) {
        m8696g0(R.string.setting_notifications_notifications_title, i9);
    }

    /* renamed from: Q0 */
    public final void m8684Q0() {
        final FriendsClient friendsClient = new FriendsClient();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        final ProgressDialog progressDialogShow = ProgressDialog.show(this, "", getString(R.string.loading), true);
        friendsClient.m15734m("user", "signOut", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.e7
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10394a.m8642H0(friendsClient, progressDialogShow, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: R0 */
    public final void m8685R0() throws Exception {
        m8677J0(this.f7989c.getText().toString(), this.f7990d.getText().toString());
    }

    /* renamed from: S0 */
    public final void m8686S0(String str, String str2) {
        this.f7992f.show();
        NetworkLive.userFaceCollection(Globals.m7388i0().m7506X()).done(new C1545h(str, str2));
    }

    /* renamed from: T0 */
    public final void m8687T0() {
        String string = this.f7990d.getText().toString();
        if (C5170o0.m20170e(string)) {
            return;
        }
        C4677a.m18686L(string);
    }

    /* renamed from: U0 */
    public final void m8688U0(ViewGroup viewGroup, boolean z8) {
        for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
            viewGroup.getChildAt(i9).setEnabled(z8);
        }
        viewGroup.setBackgroundResource(z8 ? R.color.transparent : R.color.you_color_light_gray);
    }

    /* renamed from: V0 */
    public final void m8689V0(String str) throws Exception {
        m8677J0(str, this.f7990d.getText().toString());
    }

    /* renamed from: Y */
    public final void m8690Y(EditText editText) {
        editText.addTextChangedListener(new C1540c(editText));
    }

    /* renamed from: Z */
    public final void m8691Z() {
        if (this.f7990d.isEnabled()) {
            this.f7989c.setImeOptions(5);
        } else {
            this.f7989c.setImeOptions(6);
        }
    }

    /* renamed from: b0 */
    public final void m8692b0() {
        TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { // from class: com.cyberlink.you.activity.s6
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
                return this.f11166b.m8659p0(textView, i9, keyEvent);
            }
        };
        this.f7989c.setOnEditorActionListener(onEditorActionListener);
        this.f7990d.setOnEditorActionListener(onEditorActionListener);
    }

    /* renamed from: c0 */
    public final void m8693c0() {
        this.f7989c.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.cyberlink.you.activity.v6
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z8) {
                this.f11811b.m8661q0(view, z8);
            }
        });
    }

    /* renamed from: d0 */
    public final void m8694d0(String str) {
        if (C5170o0.m20170e(str)) {
            m8683P0(R.string.ycl_live_or_meeting_not_exist);
            return;
        }
        try {
            m8697i0(NetworkLive.EMPTY_PARAMETER.longValue(), str, new C1543f(str));
        } catch (Exception e9) {
            Log.e(f7988q, "Invalid CLW url", e9);
            m8683P0(R.string.ycl_live_or_meeting_not_exist);
        }
    }

    /* renamed from: e0 */
    public final void m8695e0(int i9, int i10, int i11) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(i9);
        builder.setMessage(i10);
        builder.setPositiveButton(i11, new DialogInterfaceOnClickListenerC1544g());
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    /* renamed from: g0 */
    public final void m8696g0(int i9, int i10) {
        final Dialog dialogM16385d = C3123g.m16385d(this, getString(i9), getString(i10));
        dialogM16385d.setCancelable(false);
        TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
        textView.setText(R.string.close);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.y6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16385d.dismiss();
            }
        });
        dialogM16385d.show();
    }

    /* renamed from: i0 */
    public final void m8697i0(long j9, String str, AbstractC6381r<Live, Integer> abstractC6381r) {
        if (!this.f7992f.isShowing()) {
            this.f7992f.show();
        }
        NetworkLive.getLive(Globals.m7388i0().m7506X(), j9, str, this.f7994h).done(new C1542e(abstractC6381r));
    }

    /* renamed from: j0 */
    public final void m8698j0() {
        Uri data = getIntent().getData();
        if (data != null) {
            String scheme = data.getScheme();
            String host = data.getHost();
            if (scheme == null || host == null) {
                return;
            }
            if (!host.equalsIgnoreCase("watch")) {
                if (host.equalsIgnoreCase("portal")) {
                    if (Globals.m7388i0().m7464O1()) {
                        ULogUtility.m16670f(f7988q, "navigate to ULauncher meeting and webinars tab");
                        Intent intent = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
                        intent.setFlags(67108864);
                        intent.putExtra("Tab_Index", 2);
                        startActivity(intent);
                    } else {
                        ULogUtility.m16676l(f7988q, "user is not login, navigate to splash activity for login");
                        Globals.m7388i0().m7617t2(null);
                    }
                    finish();
                    return;
                }
                return;
            }
            if (!getString(R.string.meeting_scheme).equalsIgnoreCase(scheme)) {
                if (getString(R.string.webinars_scheme).equalsIgnoreCase(scheme)) {
                    C3031d c3031d = new C3031d(data.toString());
                    String strM15504h = c3031d.m15504h("liveId");
                    if (!C5170o0.m20170e(c3031d.m15504h("regToken"))) {
                        strM15504h = c3031d.m15504h("regToken");
                    }
                    ULogUtility.m16680p(f7988q, "liveId from deep link : " + strM15504h);
                    m8694d0(strM15504h);
                    return;
                }
                if (getString(R.string.webinars_en_scheme).equalsIgnoreCase(scheme)) {
                    this.f7994h = true;
                    C3031d c3031d2 = new C3031d(data.toString(), 2);
                    this.f7995i = c3031d2.m15504h("sso");
                    if (!C5170o0.m20170e(c3031d2.m15504h(AppMeasurementSdk.ConditionalUserProperty.NAME))) {
                        String strM20173h = C5170o0.m20173h(c3031d2.m15504h(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        if (this.f7990d != null) {
                            if (C5170o0.m20170e(strM20173h)) {
                                this.f7990d.setText("");
                            } else {
                                this.f7990d.setText(strM20173h);
                                this.f7990d.setEnabled(false);
                            }
                        }
                    }
                    if (!C5170o0.m20170e(c3031d2.m15504h("watermark"))) {
                        this.f7996j = C5170o0.m20173h(c3031d2.m15504h("watermark"));
                    }
                    m8694d0(c3031d2.m15504h("liveId"));
                    return;
                }
                return;
            }
            C3031d c3031d3 = new C3031d(data.toString());
            String strM15504h2 = c3031d3.m15504h("meetingId");
            String strM15504h3 = c3031d3.m15504h("ltiToken");
            String strM15504h4 = c3031d3.m15504h("username");
            String strM15504h5 = c3031d3.m15504h("uid");
            UserInfo userInfoM16497V0 = CLUtility.m16497V0(this);
            ULogUtility.m16683s("JoinCLMWActivity", "(deep) EventId: " + strM15504h2 + " / ltiToken: " + strM15504h3 + " / UserName: " + strM15504h4 + " / uId: " + strM15504h5);
            this.f7997k = true ^ C5170o0.m20170e(strM15504h3);
            if (!C5170o0.m20170e(strM15504h2)) {
                this.f7989c.setText(strM15504h2);
                EditText editText = this.f7989c;
                editText.setSelection(editText.getText().length());
                this.f7998l = strM15504h2;
            }
            if (this.f7997k && !C5170o0.m20170e(strM15504h4)) {
                this.f7999m = strM15504h3;
                this.f8001o = C5170o0.m20173h(strM15504h4.replace("+", "%2B"));
                this.f7989c.setEnabled(false);
            }
            if (!C5170o0.m20170e(strM15504h5)) {
                this.f8000n = strM15504h5;
            }
            if (this.f7997k && Globals.m7388i0().m7464O1()) {
                String string = Long.toString(userInfoM16497V0.f13777b);
                if ((C5170o0.m20170e(strM15504h5) || strM15504h5.equals(string)) && !C5170o0.m20170e(strM15504h5)) {
                    return;
                }
                m8695e0(R.string.clm_error_join_fail_with_different_account_title, R.string.clm_error_join_fail_with_different_account_content, R.string.clm_error_join_fail_with_different_account_button);
            }
        }
    }

    /* renamed from: k0 */
    public final void m8699k0(int i9, String str, Bundle bundle) {
        int iM5671h = C1260a.m5671h(i9, str);
        if (iM5671h == R.string.clm_error_pwd) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) ConfirmPwdActivity.class);
            bundle.putString("type", "needMeetingPasswordVerification");
            bundle.putString("meetingLtiToken", this.f7999m);
            intent.putExtras(bundle);
            startActivityForResult(intent, 40001);
            return;
        }
        if (iM5671h == R.string.clm_error_sign_in) {
            C3123g.m16382a(this).setTitle(R.string.clm_error_fail_title).setMessage(getResources().getString(R.string.clm_error_sign_in_description_v2)).setCancelable(false).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.f7
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    this.f10431b.m8665s0(dialogInterface, i10);
                }
            }).show();
            return;
        }
        if (iM5671h == R.string.clm_error_not_invited) {
            m8696g0(R.string.clm_error_invite_fail_title, R.string.clm_error_invite_fail_content);
            return;
        }
        if (iM5671h == R.string.clm_error_face_verification) {
            bundle.putBoolean("needUserFaceVerification", true);
            if (!Globals.m7388i0().m7429G1()) {
                bundle.putBoolean("bindFaceId", false);
            }
            Intent intent2 = new Intent(getApplicationContext(), (Class<?>) FaceVerificationActivity.class);
            intent2.putExtras(bundle);
            startActivityForResult(intent2, 40003);
            return;
        }
        if (iM5671h == R.string.clm_error_face_invalid) {
            Intent intent3 = new Intent(this, (Class<?>) ConfirmPwdActivity.class);
            bundle.putString("type", "needUserPasswordVerification");
            intent3.putExtras(bundle);
            startActivityForResult(intent3, 40001);
            return;
        }
        if (iM5671h == R.string.clm_error_old_version) {
            m8696g0(R.string.clm_error_old_version_title, iM5671h);
        } else {
            m8683P0(iM5671h);
        }
    }

    /* renamed from: l0 */
    public final void m8700l0() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("meetingLtiEventId");
        String stringExtra2 = intent.getStringExtra("meetingLtiToken");
        String stringExtra3 = intent.getStringExtra("meetingLtiUserName");
        ULogUtility.m16683s("JoinCLMWActivity", "(extra) EventId: " + stringExtra + " / ltiToken: " + stringExtra2 + " / UserName: " + stringExtra3);
        if (Globals.m7388i0().m7464O1() || C5170o0.m20170e(stringExtra) || C5170o0.m20170e(stringExtra2) || C5170o0.m20170e(stringExtra3)) {
            return;
        }
        this.f7989c.setText(stringExtra);
        this.f7989c.setEnabled(false);
        EditText editText = this.f7989c;
        editText.setSelection(editText.getText().length());
        this.f7998l = stringExtra;
        this.f7999m = stringExtra2;
        this.f8001o = stringExtra3;
    }

    /* renamed from: m0 */
    public final void m8701m0(String str, String str2, Bundle bundle) {
        if (!this.f7992f.isShowing()) {
            this.f7992f.show();
        }
        C1260a.m5675m(str, str2, this.f7999m, Globals.m7388i0().m7506X()).m15439e(new C1538a(str, bundle.getString("displayName"), str2, bundle));
    }

    /* renamed from: n0 */
    public final void m8702n0(String str, String str2) {
        if (MeetingManager.m5619l()) {
            m8683P0(R.string.clm_error_other_meeting_in_progress);
        } else if (C5170o0.m20170e(str2)) {
            m8683P0(R.string.clmw_join_invalid_name);
        } else {
            m8686S0(str, str2);
        }
    }

    /* renamed from: o0 */
    public final void m8703o0(String str, String str2, String str3) throws NumberFormatException {
        long jLongValue = NetworkLive.EMPTY_PARAMETER.longValue();
        try {
            if (!C5170o0.m20170e(str)) {
                jLongValue = Long.parseLong(str);
            }
        } catch (NumberFormatException unused) {
            Log.v(f7988q, "inValid eventId");
        }
        if (jLongValue == NetworkLive.EMPTY_PARAMETER.longValue() && C5170o0.m20170e(str2)) {
            m8683P0(R.string.clmw_join_wrong_id);
            return;
        }
        try {
            m8697i0(jLongValue, str2, new C1541d(str3, this.f7994h ? this.f7995i : Globals.m7388i0().m7506X(), jLongValue, str2));
        } catch (Exception e9) {
            Log.e(f7988q, "Invalid CLW id", e9);
            m8683P0(R.string.ycl_live_or_meeting_not_exist);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        Bundle extras;
        Bundle extras2;
        if (i9 == 40001) {
            if (i10 == -1) {
                if (intent == null) {
                    Log.w(f7988q, "Cannot confirm password result");
                    return;
                }
                Bundle extras3 = intent.getExtras();
                if (extras3 == null) {
                    return;
                }
                int i11 = extras3.getInt("joinMeetingErrorCode", -1);
                String string = extras3.getString("meetingErrorBody");
                String string2 = extras3.getString("c.c.m.p.ConfirmPwd_EXTRA_PASSWD");
                boolean z8 = extras3.getBoolean("needMeetingPasswordVerification", false);
                String string3 = extras3.getString("meetingMServerAddress");
                String string4 = extras3.getString("meetingMServerToken");
                if (i11 != -1) {
                    m8699k0(i11, string, extras3);
                    return;
                } else if (z8 && C5170o0.m20170e(string2)) {
                    Log.w(f7988q, "Result has no password");
                    return;
                } else {
                    MeetingActivity.m6565wb(this, this.f7989c.getText().toString().replace("-", ""), this.f7990d.getText().toString(), string2, this.f7999m, null, null, string3, string4, "join meeting page w/ pwd");
                    return;
                }
            }
            return;
        }
        if (i9 != 40003) {
            if (i9 == 40005 && i10 == -1 && intent != null && (extras2 = intent.getExtras()) != null && Globals.m7388i0().m7429G1()) {
                m8699k0(403, "Require face verification", extras2);
                return;
            }
            return;
        }
        if (i10 != -1 || intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        boolean z9 = extras.getBoolean("faceIdCapacity", true);
        boolean z10 = extras.getBoolean("bindFaceId", true);
        extras.remove("faceIdCapacity");
        extras.remove("bindFaceId");
        if (!z9) {
            Intent intent2 = new Intent(this, (Class<?>) ConfirmPwdActivity.class);
            extras.putString("type", "needUserPasswordVerification");
            intent2.putExtras(extras);
            startActivityForResult(intent2, 40001);
            return;
        }
        if (!z10) {
            Intent intent3 = new Intent(this, (Class<?>) RegistrationFaceIdActivity.class);
            intent3.putExtras(extras);
            startActivityForResult(intent3, 40005);
            return;
        }
        int i12 = extras.getInt("joinMeetingErrorCode", -1);
        String string5 = extras.getString("meetingErrorBody");
        String string6 = extras.getString("displayName", "");
        String strReplace = this.f7989c.getText().toString().replace("-", "");
        String string7 = extras.getString("meetingMServerAddress");
        String string8 = extras.getString("meetingMServerToken");
        if (i12 != -1) {
            m8699k0(i12, string5, extras);
        } else {
            MeetingActivity.m6565wb(this, strReplace, string6, null, this.f7999m, null, null, string7, string8, "join meeting page face verification");
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_join_meeting);
        C5314a.m20793c();
        this.f7989c = (EditText) findViewById(R.id.txtJoinEventId);
        this.f7990d = (EditText) findViewById(R.id.txtJoinDisplayName);
        this.f7992f = new DialogC3133q.b(this).m16410a();
        TextView textView = (TextView) findViewById(R.id.JoinMeetTitleTextView);
        textView.setOnClickListener(this.f8002p);
        C5179r0.m20247b(textView, 1);
        SpannableString spannableString = new SpannableString(getString(R.string.clmw_join_id_hint));
        spannableString.setSpan(new RelativeSizeSpan(0.9f), 0, spannableString.length(), 0);
        this.f7989c.setHint(spannableString);
        this.f7989c.requestFocus();
        m8679L0();
        m8680M0();
        m8681N0();
        m8682O0();
        m8700l0();
        m8698j0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        m8676I0();
    }
}
