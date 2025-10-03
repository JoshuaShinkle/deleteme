package com.cyberlink.you.activity.webinar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.plus.PlusShare;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.Message;
import com.perfectcorp.ycl.p040bc.model.Register;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkMessage;
import com.perfectcorp.ycl.p040bc.model.network.NetworkRegister;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import p057e5.C4757b;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p116k4.C5187v0;

/* loaded from: classes.dex */
public class WebinarRegistrationActivity extends BaseActivity {

    /* renamed from: c */
    public String f12049c;

    /* renamed from: d */
    public Date f12050d;

    /* renamed from: e */
    public Date f12051e;

    /* renamed from: f */
    public String f12052f;

    /* renamed from: g */
    public String f12053g;

    /* renamed from: h */
    public String f12054h;

    /* renamed from: j */
    public boolean f12056j;

    /* renamed from: k */
    public View f12057k;

    /* renamed from: l */
    public ViewGroup f12058l;

    /* renamed from: m */
    public View f12059m;

    /* renamed from: o */
    public String f12061o;

    /* renamed from: i */
    public String f12055i = "";

    /* renamed from: n */
    public Map<String, C2646d> f12060n = new HashMap();

    /* renamed from: p */
    public View.OnClickListener f12062p = new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity.2

        /* renamed from: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity$2$a */
        public class a extends PromisedTask.AbstractC4504d<Register.registerLiveResult> {

            /* renamed from: a */
            public final /* synthetic */ DialogC3133q f12065a;

            public a(DialogC3133q dialogC3133q) {
                this.f12065a = dialogC3133q;
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onDone(Register.registerLiveResult registerliveresult) {
                this.f12065a.dismiss();
                if (registerliveresult == null || !"APPROVED".equalsIgnoreCase(registerliveresult.registrationStatus)) {
                    WebinarRegistrationActivity.this.m13918O();
                } else {
                    WebinarRegistrationActivity.this.m13917N();
                }
            }

            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                super.onError(i9);
                this.f12065a.dismiss();
                if (i9 == 401) {
                    WebinarRegistrationActivity.this.m13918O();
                    return;
                }
                if (i9 != 403) {
                    C5187v0.m20267c(R.string.ycl_live_not_exist);
                    ULogUtility.m16676l("WebinarRegistrationActivity", "registerLive onError Code=" + i9);
                    return;
                }
                C5187v0.m20267c(R.string.clw_registration_no_need_warning);
                ULogUtility.m16676l("WebinarRegistrationActivity", "registerLive onError Code=" + i9);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (WebinarRegistrationActivity.this.m13916E()) {
                return;
            }
            NetworkRegister.registerLive(WebinarRegistrationActivity.this.f12061o, WebinarRegistrationActivity.this.f12049c, WebinarRegistrationActivity.this.f12052f, WebinarRegistrationActivity.this.f12053g, new HashMap<String, String>() { // from class: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity.2.1
                {
                    put("organizationName", ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_organization")).m13927c());
                    put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_title")).m13927c());
                    put("department", ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_department")).m13927c());
                    put("companyURL", ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_company_url")).m13927c());
                    put(TtmlNode.TAG_REGION, ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_region")).m13927c());
                    put("phone", ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_phone")).m13927c());
                }
            }).done(new a(new DialogC3133q.b(WebinarRegistrationActivity.this).m16411b()));
        }
    };

    /* renamed from: q */
    public View.OnClickListener f12063q = new ViewOnClickListenerC2644b();

    public enum PreSurveyConfig {
        required,
        optional,
        hidden;

        /* renamed from: a */
        public static PreSurveyConfig m13922a(String str) {
            if (str == null) {
                return null;
            }
            switch (str) {
                case "REQUIRED":
                    return required;
                case "OPTIONAL":
                    return optional;
                case "HIDDEN":
                    return hidden;
                default:
                    return null;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity$a */
    public class C2643a extends PromisedTask.AbstractC4504d<Register.queryRegistrationFormResult> {

        /* renamed from: a */
        public final /* synthetic */ Activity f12071a;

        /* renamed from: b */
        public final /* synthetic */ String f12072b;

        /* renamed from: c */
        public final /* synthetic */ Live f12073c;

        public C2643a(Activity activity, String str, Live live) {
            this.f12071a = activity;
            this.f12072b = str;
            this.f12073c = live;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Register.queryRegistrationFormResult queryregistrationformresult) {
            Intent intent = new Intent(this.f12071a, (Class<?>) WebinarRegistrationActivity.class);
            if (queryregistrationformresult.preSurveyConfigEnabled.booleanValue()) {
                try {
                    intent.putExtra("pre_survey_organization", queryregistrationformresult.preSurveyConfig.getString("organizationOption"));
                    intent.putExtra("pre_survey_title", queryregistrationformresult.preSurveyConfig.getString("titleOption"));
                    intent.putExtra("pre_survey_department", queryregistrationformresult.preSurveyConfig.getString("departmentOption"));
                    intent.putExtra("pre_survey_company_url", queryregistrationformresult.preSurveyConfig.getString("companyUrlOption"));
                    intent.putExtra("pre_survey_region", queryregistrationformresult.preSurveyConfig.getString("regionOption"));
                    intent.putExtra("pre_survey_phone", queryregistrationformresult.preSurveyConfig.getString("phoneOption"));
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            intent.putExtra("live_id", this.f12072b);
            intent.putExtra("live_title", this.f12073c.title);
            intent.putExtra("live_time_start", C2698m2.m13954f(this.f12073c.scheduleInterval.startDate));
            intent.putExtra("live_time_end", C2698m2.m13954f(this.f12073c.scheduleInterval.endDate));
            intent.putExtra("show_register_layout", true);
            intent.putExtra("live_status_state", this.f12073c.status.state);
            Live.RecordedVideo recordedVideo = this.f12073c.video;
            if (recordedVideo != null) {
                Uri uri = recordedVideo.uri;
                if (uri != null) {
                    intent.putExtra("live_video_uri", uri.toString());
                }
                intent.putExtra("live_video_expired", this.f12073c.video.expired);
            }
            this.f12071a.startActivity(intent);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            super.onError(i9);
            C2698m2.m13957i(this.f12071a, "WebinarRegistrationActivity", i9);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity$b */
    public class ViewOnClickListenerC2644b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity$b$a */
        public class a extends PromisedTask.AbstractC4504d<Message.JoinRoomResponse> {

            /* renamed from: a */
            public final /* synthetic */ DialogC3133q f12075a;

            public a(DialogC3133q dialogC3133q) {
                this.f12075a = dialogC3133q;
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onDone(Message.JoinRoomResponse joinRoomResponse) {
                this.f12075a.dismiss();
                if (!NetworkLive.STATE.VIDEO_ON_DEMAND.equals(WebinarRegistrationActivity.this.f12054h)) {
                    WebinarRegistrationActivity webinarRegistrationActivity = WebinarRegistrationActivity.this;
                    C2698m2.m13953e(webinarRegistrationActivity, webinarRegistrationActivity.f12049c, "", WebinarRegistrationActivity.this.f12061o, null, false, ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_organization")).m13927c(), ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_department")).m13927c(), ((C2646d) WebinarRegistrationActivity.this.f12060n.get("pre_survey_title")).m13927c(), joinRoomResponse.assistantStatus);
                } else if (WebinarRegistrationActivity.this.f12056j) {
                    C4757b.m18888e(WebinarRegistrationActivity.this, R.string.wbn_vod_has_expired);
                } else {
                    WebinarRegistrationActivity webinarRegistrationActivity2 = WebinarRegistrationActivity.this;
                    C2698m2.m13964p(webinarRegistrationActivity2, Uri.parse(webinarRegistrationActivity2.f12055i), WebinarRegistrationActivity.this.f12049c, WebinarRegistrationActivity.this.f12061o, false);
                }
                WebinarRegistrationActivity.this.onBackPressed();
            }

            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                super.onError(i9);
                this.f12075a.dismiss();
                C2698m2.m13957i(WebinarRegistrationActivity.this, "WebinarRegistrationActivity", i9);
            }
        }

        public ViewOnClickListenerC2644b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NetworkMessage.joinRoom(WebinarRegistrationActivity.this.f12061o, WebinarRegistrationActivity.this.f12049c, false).done(new a(new DialogC3133q.b(WebinarRegistrationActivity.this).m16411b()));
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity$c */
    public static /* synthetic */ class C2645c {

        /* renamed from: a */
        public static final /* synthetic */ int[] f12077a;

        static {
            int[] iArr = new int[PreSurveyConfig.values().length];
            f12077a = iArr;
            try {
                iArr[PreSurveyConfig.required.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12077a[PreSurveyConfig.optional.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12077a[PreSurveyConfig.hidden.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.WebinarRegistrationActivity$d */
    public static class C2646d {

        /* renamed from: a */
        public PreSurveyConfig f12078a;

        /* renamed from: b */
        public View f12079b;

        /* renamed from: c */
        public TextView f12080c;

        /* renamed from: d */
        public View f12081d;

        /* renamed from: e */
        public int f12082e;

        /* renamed from: f */
        public int f12083f;

        public /* synthetic */ C2646d(String str, PreSurveyConfig preSurveyConfig, View view, int i9, int i10, String str2, C2643a c2643a) {
            this(str, preSurveyConfig, view, i9, i10, str2);
        }

        /* renamed from: c */
        public final String m13927c() {
            return this.f12080c.getText().toString();
        }

        /* renamed from: d */
        public final void m13928d() {
            int i9 = C2645c.f12077a[this.f12078a.ordinal()];
            if (i9 == 1) {
                this.f12079b.setVisibility(0);
                this.f12080c.setHint(this.f12082e);
            } else if (i9 == 2) {
                this.f12079b.setVisibility(0);
                this.f12080c.setHint(this.f12083f);
            } else {
                if (i9 != 3) {
                    return;
                }
                this.f12079b.setVisibility(8);
            }
        }

        /* renamed from: e */
        public final boolean m13929e() {
            boolean z8 = this.f12078a == PreSurveyConfig.required && this.f12080c.getText().length() == 0;
            m13930f(z8);
            return z8;
        }

        /* renamed from: f */
        public final void m13930f(boolean z8) {
            if (z8) {
                this.f12080c.setBackgroundResource(R.drawable.bg_edittext_radius_red_border);
                this.f12081d.setVisibility(0);
            } else {
                this.f12080c.setBackgroundResource(R.drawable.bg_edittext_radius_gray_border);
                this.f12081d.setVisibility(4);
            }
        }

        public C2646d(String str, PreSurveyConfig preSurveyConfig, View view, int i9, int i10, String str2) {
            PreSurveyConfig preSurveyConfigM13922a = PreSurveyConfig.m13922a(str);
            this.f12078a = preSurveyConfigM13922a != null ? preSurveyConfigM13922a : preSurveyConfig;
            this.f12079b = view;
            this.f12080c = (TextView) view.findViewById(R.id.registration_text);
            this.f12081d = view.findViewById(R.id.registration_star);
            this.f12082e = i9;
            this.f12083f = i10;
            this.f12080c.setText((C5170o0.m20169d(str2) || preSurveyConfigM13922a == PreSurveyConfig.hidden) ? "" : str2);
            m13928d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H */
    public /* synthetic */ void m13897H(View view) {
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I */
    public /* synthetic */ void m13898I(View view) {
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J */
    public /* synthetic */ void m13899J(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View currentFocus = getCurrentFocus();
        if (inputMethodManager == null || currentFocus == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    /* renamed from: L */
    public static void m13900L(Activity activity, Live live, String str) {
        NetworkRegister.queryRegistrationForm(Globals.m7388i0().m7506X(), str).done(new C2643a(activity, str, live));
    }

    /* renamed from: B */
    public final void m13913B() {
        findViewById(R.id.HeaderBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.h2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12136b.m13897H(view);
            }
        });
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.i2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12141b.m13898I(view);
            }
        });
        View viewFindViewById = findViewById(R.id.btnRegister);
        viewFindViewById.setOnClickListener(this.f12062p);
        C5179r0.m20247b((TextView) viewFindViewById, 1);
        findViewById(R.id.btnJoinWebinar).setOnClickListener(this.f12063q);
        this.f12058l.getChildAt(0).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.j2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12146b.m13899J(view);
            }
        });
    }

    /* renamed from: C */
    public final void m13914C(Intent intent) {
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(this);
        this.f12060n.put("pre_survey_organization", new C2646d(intent.getStringExtra("pre_survey_organization"), PreSurveyConfig.optional, findViewById(R.id.preSurveyOrganization), R.string.clw_registration_info_company, R.string.clw_registration_info_company_optional, userInfoM16497V0 != null ? userInfoM16497V0.f13789n : "", null));
        Map<String, C2646d> map = this.f12060n;
        String stringExtra = intent.getStringExtra("pre_survey_title");
        PreSurveyConfig preSurveyConfig = PreSurveyConfig.hidden;
        map.put("pre_survey_title", new C2646d(stringExtra, preSurveyConfig, findViewById(R.id.preSurveyTitle), R.string.clw_registration_info_job_title, R.string.clw_registration_info_job_title_optional, userInfoM16497V0 != null ? userInfoM16497V0.f13791p : "", null));
        C2643a c2643a = null;
        this.f12060n.put("pre_survey_department", new C2646d(intent.getStringExtra("pre_survey_department"), preSurveyConfig, findViewById(R.id.preSurveyDepartment), R.string.clw_registration_info_department, R.string.clw_registration_info_department_optional, userInfoM16497V0 != null ? userInfoM16497V0.f13790o : "", c2643a));
        this.f12060n.put("pre_survey_company_url", new C2646d(intent.getStringExtra("pre_survey_company_url"), preSurveyConfig, findViewById(R.id.preSurveyCompanyURL), R.string.clw_registration_info_companyURL, R.string.clw_registration_info_companyURL_optional, "", c2643a));
        this.f12060n.put("pre_survey_region", new C2646d(intent.getStringExtra("pre_survey_region"), preSurveyConfig, findViewById(R.id.preSurveyRegion), R.string.clw_registration_info_region, R.string.clw_registration_info_region_optional, "", c2643a));
        this.f12060n.put("pre_survey_phone", new C2646d(intent.getStringExtra("pre_survey_phone"), preSurveyConfig, findViewById(R.id.preSurveyPhone), R.string.clw_registration_info_phone, R.string.clw_registration_info_phone_optional, "", c2643a));
    }

    /* renamed from: D */
    public final void m13915D(Intent intent) {
        ((TextView) findViewById(R.id.webinarTitle)).setText(intent.getStringExtra("live_title"));
        TextView textView = (TextView) findViewById(R.id.webinarTime);
        textView.setText(m13919y());
        C5179r0.m20247b(textView, 1);
        ((TextView) findViewById(R.id.registerEmail).findViewById(R.id.registration_text)).setText(this.f12052f);
        ((TextView) findViewById(R.id.registerName).findViewById(R.id.registration_text)).setText(this.f12053g);
    }

    /* renamed from: E */
    public final boolean m13916E() {
        Iterator<Map.Entry<String, C2646d>> it = this.f12060n.entrySet().iterator();
        boolean zM13929e = false;
        while (it.hasNext()) {
            zM13929e |= it.next().getValue().m13929e();
        }
        return zM13929e;
    }

    /* renamed from: N */
    public void m13917N() {
        this.f12058l.setVisibility(8);
        this.f12059m.setVisibility(0);
    }

    /* renamed from: O */
    public void m13918O() {
        this.f12058l.setVisibility(8);
        this.f12057k.setVisibility(0);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webinar_registration);
        this.f12057k = findViewById(R.id.layout_under_review);
        this.f12058l = (ViewGroup) findViewById(R.id.layout_register);
        this.f12059m = findViewById(R.id.layout_register_success);
        Intent intent = getIntent();
        if (intent.getBooleanExtra("show_register_layout", false)) {
            m13920z(intent);
            m13915D(intent);
            this.f12061o = Globals.m7388i0().m7506X();
        } else {
            m13918O();
        }
        m13913B();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View currentFocus = getCurrentFocus();
        if (inputMethodManager == null || currentFocus == null) {
            return super.onTouchEvent(motionEvent);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        return true;
    }

    /* renamed from: y */
    public final String m13919y() {
        if (DateFormat.is24HourFormat(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append(CLUtility.m16426C2(this.f12050d, true));
            sb.append(StringUtils.SPACE);
            Locale locale = Locale.US;
            sb.append(new SimpleDateFormat("H:mm", locale).format(this.f12050d));
            sb.append(" - ");
            sb.append(new SimpleDateFormat("H:mm", locale).format(this.f12051e));
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(CLUtility.m16426C2(this.f12050d, true));
        sb2.append(StringUtils.SPACE);
        Locale locale2 = Locale.US;
        sb2.append(new SimpleDateFormat("h:mm a", locale2).format(this.f12050d));
        sb2.append(" - ");
        sb2.append(new SimpleDateFormat("h:mm a", locale2).format(this.f12051e));
        return sb2.toString();
    }

    /* renamed from: z */
    public final void m13920z(Intent intent) {
        this.f12049c = intent.getStringExtra("live_id");
        this.f12050d = (Date) intent.getSerializableExtra("live_time_start");
        this.f12051e = (Date) intent.getSerializableExtra("live_time_end");
        this.f12054h = intent.getStringExtra("live_status_state");
        this.f12055i = intent.getStringExtra("live_video_uri");
        this.f12056j = intent.getBooleanExtra("live_video_expired", true);
        this.f12052f = Globals.m7388i0().m7498V0();
        this.f12053g = CLUtility.m16497V0(this) != null ? CLUtility.m16497V0(this).f13778c : "";
        m13914C(intent);
    }
}
