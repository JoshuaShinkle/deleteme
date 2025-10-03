package com.cyberlink.you.activity.webinar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.clrtc.C1019i;
import com.cyberlink.clrtc.InterfaceC1113s;
import com.cyberlink.clrtc.NetQuality;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.clrtc.rtc.RTCAudioManager;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupInfoActivity;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.p038ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.iid.ServiceStarter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.perfectcorp.utility.C4507b;
import com.perfectcorp.utility.C4508c;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.BaseActivity;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.network.Key;
import com.perfectcorp.ycl.p040bc.model.network.NetworkCommon;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import com.perfectcorp.ycl.p040bc.model.network.NetworkMessage;
import com.perfectcorp.ycl.pages.live.AbstractC4591k;
import com.perfectcorp.ycl.pages.live.C4582b;
import com.perfectcorp.ycl.pages.live.C4583c;
import com.perfectcorp.ycl.pages.live.C4597q;
import com.perfectcorp.ycl.pages.live.C4600t;
import com.perfectcorp.ycl.pages.live.C4604x;
import com.perfectcorp.ycl.pages.live.InterfaceC4605y;
import com.perfectcorp.ycl.pages.live.LivePlayer;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p003a2.C0012b;
import p047d5.C4677a;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p147n5.C5366d;
import p156o5.C5468b;
import p209u2.AbstractC6381r;
import p224w.dialogs.SimpleMessageDialog;

/* loaded from: classes.dex */
public class LiveAudienceActivity extends BaseActivity implements LivePlayer.InterfaceC4580c {

    /* renamed from: K0 */
    public static final int f11865K0 = new Random().nextInt();

    /* renamed from: L0 */
    public static final CookieManager f11866L0;

    /* renamed from: M0 */
    public static AudioManager.OnAudioFocusChangeListener f11867M0;

    /* renamed from: D */
    public Date f11874D;

    /* renamed from: E */
    public Date f11876E;

    /* renamed from: J */
    public C2626r f11886J;

    /* renamed from: K */
    public C4582b f11888K;

    /* renamed from: L */
    public View f11889L;

    /* renamed from: M */
    public C4597q f11890M;

    /* renamed from: N */
    public LivePlayer f11891N;

    /* renamed from: O */
    public LivePlayer f11892O;

    /* renamed from: P */
    public long f11893P;

    /* renamed from: Q */
    public int f11894Q;

    /* renamed from: U */
    public String f11898U;

    /* renamed from: V */
    public String f11899V;

    /* renamed from: W */
    public String f11900W;

    /* renamed from: b0 */
    public String f11905b0;

    /* renamed from: c0 */
    public String f11906c0;

    /* renamed from: i0 */
    public TextView f11912i0;

    /* renamed from: j0 */
    public TextView f11913j0;

    /* renamed from: l */
    public Uri f11915l;

    /* renamed from: m */
    public Uri f11917m;

    /* renamed from: m0 */
    public long f11918m0;

    /* renamed from: n */
    public Uri f11919n;

    /* renamed from: n0 */
    public RunnableC2627s f11920n0;

    /* renamed from: o */
    public LivePlayer f11921o;

    /* renamed from: o0 */
    public RTCAudioManager f11922o0;

    /* renamed from: p */
    public boolean f11923p;

    /* renamed from: p0 */
    public NileNetwork f11924p0;

    /* renamed from: q */
    public C4600t f11925q;

    /* renamed from: q0 */
    public SimpleMessageDialog f11926q0;

    /* renamed from: r */
    public String f11927r;

    /* renamed from: s */
    public String f11929s;

    /* renamed from: s0 */
    public Dialog f11930s0;

    /* renamed from: t0 */
    public Dialog f11932t0;

    /* renamed from: u */
    public long f11933u;

    /* renamed from: v0 */
    public View f11936v0;

    /* renamed from: x0 */
    public Runnable f11940x0;

    /* renamed from: y0 */
    public SimpleMessageDialog f11942y0;

    /* renamed from: t */
    public int f11931t = 1;

    /* renamed from: v */
    public boolean f11935v = false;

    /* renamed from: w */
    public boolean f11937w = false;

    /* renamed from: x */
    public int f11939x = 0;

    /* renamed from: y */
    public boolean f11941y = true;

    /* renamed from: z */
    public boolean f11943z = false;

    /* renamed from: A */
    public boolean f11868A = false;

    /* renamed from: B */
    public boolean f11870B = true;

    /* renamed from: C */
    public boolean f11872C = false;

    /* renamed from: F */
    public int f11878F = 0;

    /* renamed from: G */
    public ArrayList<Long> f11880G = new ArrayList<>();

    /* renamed from: H */
    public ArrayList<String> f11882H = new ArrayList<>();

    /* renamed from: I */
    public int f11884I = -1;

    /* renamed from: R */
    public int f11895R = 2;

    /* renamed from: S */
    public double f11896S = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

    /* renamed from: T */
    public int f11897T = 0;

    /* renamed from: X */
    public boolean f11901X = true;

    /* renamed from: Y */
    public boolean f11902Y = false;

    /* renamed from: Z */
    public boolean f11903Z = false;

    /* renamed from: a0 */
    public String f11904a0 = null;

    /* renamed from: d0 */
    public boolean f11907d0 = false;

    /* renamed from: e0 */
    public boolean f11908e0 = false;

    /* renamed from: f0 */
    public String f11909f0 = "";

    /* renamed from: g0 */
    public String f11910g0 = "";

    /* renamed from: h0 */
    public boolean f11911h0 = false;

    /* renamed from: k0 */
    public String f11914k0 = "";

    /* renamed from: l0 */
    public String f11916l0 = "";

    /* renamed from: r0 */
    public boolean f11928r0 = false;

    /* renamed from: u0 */
    public long f11934u0 = 0;

    /* renamed from: w0 */
    public List<JSONObject> f11938w0 = new ArrayList();

    /* renamed from: z0 */
    public boolean f11944z0 = true;

    /* renamed from: A0 */
    public Runnable f11869A0 = new Runnable() { // from class: com.cyberlink.you.activity.webinar.q0
        @Override // java.lang.Runnable
        public final void run() {
            this.f12202b.m13537L3();
        }
    };

    /* renamed from: B0 */
    public Runnable f11871B0 = new RunnableC2620l();

    /* renamed from: C0 */
    public Runnable f11873C0 = new Runnable() { // from class: com.cyberlink.you.activity.webinar.r0
        @Override // java.lang.Runnable
        public final void run() throws Resources.NotFoundException {
            this.f12206b.m13531J3();
        }
    };

    /* renamed from: D0 */
    public Runnable f11875D0 = new Runnable() { // from class: com.cyberlink.you.activity.webinar.s0
        @Override // java.lang.Runnable
        public final void run() throws Resources.NotFoundException {
            this.f12209b.m13534K3();
        }
    };

    /* renamed from: E0 */
    public Runnable f11877E0 = new Runnable() { // from class: com.cyberlink.you.activity.webinar.t0
        @Override // java.lang.Runnable
        public final void run() {
            this.f12212b.m13707i3();
        }
    };

    /* renamed from: F0 */
    public Runnable f11879F0 = new Runnable() { // from class: com.cyberlink.you.activity.webinar.u0
        @Override // java.lang.Runnable
        public final void run() {
            this.f12214b.m13711k3();
        }
    };

    /* renamed from: G0 */
    public BroadcastReceiver f11881G0 = new C2612d();

    /* renamed from: H0 */
    public C4597q.i f11883H0 = new C2613e();

    /* renamed from: I0 */
    public WebViewClient f11885I0 = new C2615g();

    /* renamed from: J0 */
    public WebChromeClient f11887J0 = new C2616h();

    public enum RollCallEvent {
        START,
        START_NO_LIMIT,
        UPDATE,
        STOP
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$a */
    public class C2609a implements C4508c.a {

        /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$a$a */
        public class a implements C4508c.a {
            public a() {
            }

            @Override // com.perfectcorp.utility.C4508c.a
            /* renamed from: a */
            public void mo13742a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(LiveAudienceActivity.this, Permission.BLUETOOTH_BLE);
                } else {
                    C4677a.m18695V(LiveAudienceActivity.this.getString(R.string.permission_denied));
                }
            }

            @Override // com.perfectcorp.utility.C4508c.a
            /* renamed from: b */
            public void mo13743b() {
                LiveAudienceActivity.this.m13704g5();
                LiveAudienceActivity.this.m13686W4();
            }
        }

        public C2609a() {
        }

        @Override // com.perfectcorp.utility.C4508c.a
        /* renamed from: a */
        public void mo13742a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(LiveAudienceActivity.this, Permission.MICROPHONE);
            } else {
                C4677a.m18695V(LiveAudienceActivity.this.getString(R.string.permission_denied));
            }
        }

        @Override // com.perfectcorp.utility.C4508c.a
        /* renamed from: b */
        public void mo13743b() {
            if (Build.VERSION.SDK_INT < 31) {
                LiveAudienceActivity.this.m13704g5();
                LiveAudienceActivity.this.m13686W4();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Permission.BLUETOOTH_BLE.mo16657b());
                C4508c.m18118e(LiveAudienceActivity.this, arrayList, new a());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$b */
    public class RunnableC2610b implements Runnable {

        /* renamed from: b */
        public final String f11952b;

        /* renamed from: c */
        public final Integer f11953c;

        /* renamed from: d */
        public final Live.RecordedVideo f11954d;

        /* renamed from: e */
        public final Boolean f11955e;

        /* renamed from: f */
        public final String f11956f;

        /* renamed from: g */
        public final /* synthetic */ Live f11957g;

        /* renamed from: h */
        public final /* synthetic */ boolean f11958h;

        public RunnableC2610b(Live live, boolean z8) {
            this.f11957g = live;
            this.f11958h = z8;
            Live.Status status = live.status;
            this.f11952b = status.state;
            this.f11953c = status.onlineUsers;
            this.f11954d = live.video;
            this.f11955e = status.unpublished;
            this.f11956f = status.resumeTime;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m13745b() {
            LiveAudienceActivity.this.f11943z = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            ULogUtility.m16670f("LiveAudienceActivity", "queryLiveOnDoneUiThread");
            if (LiveAudienceActivity.this.f11925q != null && LiveAudienceActivity.this.f11925q.f16186d != null) {
                ((TextView) LiveAudienceActivity.this.findViewById(R.id.live_title)).setText(LiveAudienceActivity.this.f11925q.f16186d);
            }
            ((TextView) LiveAudienceActivity.this.findViewById(R.id.people_count)).setText(String.format(Locale.getDefault(), "%d", this.f11953c));
            LiveAudienceActivity.this.m13710j5();
            if (this.f11952b.equals(NetworkLive.STATE.COMPLETE) || this.f11952b.equals(NetworkLive.STATE.STOPPED)) {
                LiveAudienceActivity.this.m13693b5(R.string.ycl_end_live_title, !this.f11958h);
                return;
            }
            if (this.f11952b.equals(NetworkLive.STATE.SCHEDULED)) {
                LiveAudienceActivity.this.m13711k3();
                return;
            }
            if (this.f11952b.equals(NetworkLive.STATE.VIDEO_ON_DEMAND)) {
                if (this.f11954d.expired.booleanValue()) {
                    LiveAudienceActivity.this.m13682S4(true, "");
                    LiveAudienceActivity.this.m13693b5(R.string.wbn_vod_has_expired, true);
                    return;
                }
                return;
            }
            if (!this.f11952b.equals(NetworkLive.STATE.LIVE) || !this.f11955e.booleanValue()) {
                if (LiveAudienceActivity.this.f11888K != null) {
                    LiveAudienceActivity.this.f11888K.m18233v(true);
                }
                LiveAudienceActivity.this.f11868A = true;
                LiveAudienceActivity.this.f11874D = null;
                LiveAudienceActivity.this.m13682S4(false, "");
                ULogUtility.m16670f("LiveAudienceActivity", "queryLiveOnDoneUiThread startWatching");
                LiveAudienceActivity.this.m13708i5();
                C4677a.m18684I(new Runnable() { // from class: com.cyberlink.you.activity.webinar.w0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f12219b.m13745b();
                    }
                }, 10000L);
                return;
            }
            LiveAudienceActivity.this.f11868A = false;
            LiveAudienceActivity.this.m13712k5();
            if (C4509d.m18120b(this.f11956f)) {
                LiveAudienceActivity.this.m13681R4(R.string.wbn_presenter_is_offline);
            } else {
                LiveAudienceActivity.this.f11876E = null;
                LiveAudienceActivity.this.f11874D = null;
                try {
                    LiveAudienceActivity.this.f11876E = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).parse(this.f11956f);
                    LiveAudienceActivity.this.m13711k3();
                } catch (ParseException unused) {
                    ULogUtility.m16670f("LiveAudienceActivity", "Parse pause time interval exception");
                    C4507b.m18101b("Parse pause time interval exception");
                }
            }
            LiveAudienceActivity.this.m13723p5();
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$c */
    public class C2611c extends PromisedTask.AbstractC4504d<Live> {
        public C2611c() {
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Live live) {
            Live.QNA qna = live.f15981qa;
            if (qna == null || C4509d.m18120b(qna.eventId)) {
                return;
            }
            LiveAudienceActivity.this.f11916l0 = live.f15981qa.eventId;
            LiveAudienceActivity.this.f11911h0 = live.f15981qa.enabled.booleanValue();
            ULogUtility.m16670f("LiveAudienceActivity", "[updateQNAInfo] enableQNA : " + LiveAudienceActivity.this.f11911h0 + " meeting id :" + LiveAudienceActivity.this.f11916l0);
            if (LiveAudienceActivity.this.f11907d0 || !LiveAudienceActivity.this.f11911h0) {
                return;
            }
            LiveAudienceActivity.this.m13697d3(true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$d */
    public class C2612d extends BroadcastReceiver {
        public C2612d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                boolean z8 = !intent.getBooleanExtra("noConnectivity", false);
                if (LiveAudienceActivity.this.f11901X) {
                    LiveAudienceActivity.this.f11901X = false;
                    return;
                }
                ULogUtility.m16670f("LiveAudienceActivity", "NetworkConnected : " + z8 + " isAppInForeground : " + LiveAudienceActivity.this.f11902Y);
                C4507b.m18108i("NetworkConnected : " + z8 + " isAppInForeground : " + LiveAudienceActivity.this.f11902Y);
                C4677a.m18693T("NetworkConnected : " + z8 + " isAppInForeground : " + LiveAudienceActivity.this.f11902Y);
                if (z8 && LiveAudienceActivity.this.f11902Y) {
                    LiveAudienceActivity.this.m13739y4();
                    LiveAudienceActivity.this.m13741z4();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$e */
    public class C2613e implements C4597q.i {

        /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$e$a */
        public class a extends PromisedTask.AbstractC4504d {

            /* renamed from: a */
            public final /* synthetic */ String f11963a;

            public a(String str) {
                this.f11963a = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: c */
            public /* synthetic */ void m13751c(String str) {
                LiveAudienceActivity.this.f11890M.m18276F(str);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: d */
            public /* synthetic */ void m13752d() {
                C4677a.m18693T(LiveAudienceActivity.this.getString(R.string.wbn_like_message_error));
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            public void onDone(Object obj) {
                LiveAudienceActivity liveAudienceActivity = LiveAudienceActivity.this;
                final String str = this.f11963a;
                liveAudienceActivity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.x0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f12222b.m13751c(str);
                    }
                });
            }

            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                LiveAudienceActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.y0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f12226b.m13752d();
                    }
                });
            }
        }

        /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$e$b */
        public class b extends PromisedTask.AbstractC4504d {

            /* renamed from: a */
            public final /* synthetic */ String f11965a;

            public b(String str) {
                this.f11965a = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: c */
            public /* synthetic */ void m13755c(String str) {
                LiveAudienceActivity.this.f11890M.m18283M(str);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: d */
            public /* synthetic */ void m13756d() {
                C4677a.m18693T(LiveAudienceActivity.this.getString(R.string.wbn_unlike_message_error));
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            public void onDone(Object obj) {
                LiveAudienceActivity liveAudienceActivity = LiveAudienceActivity.this;
                final String str = this.f11965a;
                liveAudienceActivity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.a1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f12095b.m13755c(str);
                    }
                });
            }

            @Override // com.perfectcorp.utility.PromisedTask
            public void onError(int i9) {
                LiveAudienceActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.z0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f12229b.m13756d();
                    }
                });
            }
        }

        public C2613e() {
        }

        @Override // com.perfectcorp.ycl.pages.live.C4597q.i
        /* renamed from: a */
        public void mo13747a(String str) {
            NetworkMessage.unlikeMsg(LiveAudienceActivity.this.f11927r, Long.parseLong(str), !C4509d.m18120b(LiveAudienceActivity.this.f11906c0) ? Long.parseLong(LiveAudienceActivity.this.f11906c0) : 0L).done(new b(str));
        }

        @Override // com.perfectcorp.ycl.pages.live.C4597q.i
        /* renamed from: b */
        public void mo13748b(String str) {
            NetworkMessage.likeMsg(LiveAudienceActivity.this.f11927r, Long.parseLong(str), !C4509d.m18120b(LiveAudienceActivity.this.f11906c0) ? Long.parseLong(LiveAudienceActivity.this.f11906c0) : 0L).done(new a(str));
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$f */
    public class C2614f extends PromisedTask.AbstractC3021b<String> {
        public C2614f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: s */
        public /* synthetic */ void m13758s(WebView webView, View view) {
            LiveAudienceActivity.this.f11936v0.setVisibility(8);
            ((InputMethodManager) LiveAudienceActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(webView.getWindowToken(), 2);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: t, reason: merged with bridge method [inline-methods] */
        public void mo5703q(String str) {
            LiveAudienceActivity.this.f11936v0.setVisibility(0);
            final WebView webView = (WebView) LiveAudienceActivity.this.f11936v0.findViewById(R.id.rollCallPage);
            webView.setVisibility(0);
            webView.setWebViewClient(LiveAudienceActivity.this.f11885I0);
            webView.setWebChromeClient(LiveAudienceActivity.this.f11887J0);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setTextZoom(100);
            settings.setDomStorageEnabled(true);
            webView.loadUrl(str);
            LiveAudienceActivity.this.f11936v0.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.b1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12103b.m13758s(webView, view);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$g */
    public class C2615g extends WebViewClient {
        public C2615g() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$h */
    public class C2616h extends WebChromeClient {
        public C2616h() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            ((TextView) LiveAudienceActivity.this.findViewById(R.id.title)).setText(webView.getTitle());
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$i */
    public static /* synthetic */ class C2617i {

        /* renamed from: a */
        public static final /* synthetic */ int[] f11970a;

        static {
            int[] iArr = new int[NileNetwork.Status.values().length];
            f11970a = iArr;
            try {
                iArr[NileNetwork.Status.REACH_LIMIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$j */
    public class C2618j extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ ImageView f11971j;

        /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$j$a */
        public class a implements InterfaceC1113s {

            /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$j$a$a, reason: collision with other inner class name */
            public class C6852a extends AbstractC6381r<Boolean, Void> {
                public C6852a() {
                }

                @Override // p209u2.AbstractC6381r
                /* renamed from: k, reason: merged with bridge method [inline-methods] */
                public void m24503g(Boolean bool) {
                    ULogUtility.m16670f("LiveAudienceActivity", "registerWebinarViewerInfo :" + bool);
                }
            }

            public a() {
            }

            @Override // com.cyberlink.clrtc.InterfaceC1113s
            public void OnQNAHostHangup() {
                ULogUtility.m16670f("LiveAudienceActivity", "OnQNAHostHangup");
                LiveAudienceActivity.this.m13683T4();
            }

            @Override // com.cyberlink.clrtc.InterfaceC1113s
            public void OnQNAHostPicking() {
                ULogUtility.m16670f("LiveAudienceActivity", "OnQNAHostPicking");
                LiveAudienceActivity.this.m13684U4();
            }

            @Override // com.cyberlink.clrtc.InterfaceC1113s
            /* renamed from: e0 */
            public void mo5124e0(NileNetwork.Status status, int i9) {
                if (C2617i.f11970a[status.ordinal()] != 1) {
                    return;
                }
                LiveAudienceActivity.this.m13688Y4();
            }

            @Override // com.cyberlink.clrtc.InterfaceC1113s
            /* renamed from: l */
            public void mo5133l(boolean z8) {
                LiveAudienceActivity.this.m13698d5();
            }

            @Override // com.cyberlink.clrtc.InterfaceC1113s
            /* renamed from: l0 */
            public void mo5134l0(C0012b c0012b, boolean z8, boolean z9) {
                LiveAudienceActivity.this.f11918m0 = c0012b.f66b.f65d;
                ULogUtility.m16670f("LiveAudienceActivity", "onJoinCompleted userId : " + LiveAudienceActivity.this.f11918m0);
                LiveAudienceActivity.this.f11924p0.m4910N7((int) LiveAudienceActivity.this.f11918m0, LiveAudienceActivity.this.f11898U, LiveAudienceActivity.this.f11899V, LiveAudienceActivity.this.f11900W, new C6852a());
            }

            @Override // com.cyberlink.clrtc.InterfaceC1113s
            /* renamed from: s */
            public void mo5141s(List<C0012b> list) {
                for (C0012b c0012b : list) {
                    if (c0012b.f66b.f65d == LiveAudienceActivity.this.f11918m0) {
                        ULogUtility.m16670f("LiveAudienceActivity", "participant hasVoice() : " + c0012b.m90i());
                        C2618j.this.f11971j.setImageResource(c0012b.m90i() ? R.drawable.webinar_qna_mic_presenting : R.drawable.webinar_qna_mic_on);
                    }
                }
            }
        }

        public C2618j(ImageView imageView) {
            this.f11971j = imageView;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ULogUtility.m16670f("LiveAudienceActivity", "Join Q&A meeting errorCode : " + i9 + " errorBody : " + str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            LiveAudienceActivity liveAudienceActivity = LiveAudienceActivity.this;
            liveAudienceActivity.f11924p0 = MeetingManager.m5610c(liveAudienceActivity.f11916l0);
            LiveAudienceActivity.this.f11924p0.m4955n8(new a());
            LiveAudienceActivity.this.m13706h5();
            String strM18708l = C4677a.m18708l();
            LiveAudienceActivity.this.f11924p0.m4907L3(new C1019i.b().m5005f(meeting.mserverAddr).m5006g(meeting.token).m5007h(Globals.m7388i0().m7568k1().longValue()).m5008i(strM18708l).m5002c(LiveAudienceActivity.this.f11916l0).m5003d(strM18708l).m5000a());
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$k */
    public class C2619k implements RTCAudioManager.InterfaceC1108b {
        public C2619k() {
        }

        @Override // com.cyberlink.clrtc.rtc.RTCAudioManager.InterfaceC1108b
        /* renamed from: a */
        public void mo5071a(RTCAudioManager.AudioDevice audioDevice, Set<RTCAudioManager.AudioDevice> set) {
            LiveAudienceActivity.this.m13737x4(audioDevice, set);
        }

        @Override // com.cyberlink.clrtc.rtc.RTCAudioManager.InterfaceC1108b
        /* renamed from: b */
        public void mo5072b() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$l */
    public class RunnableC2620l implements Runnable {
        public RunnableC2620l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (LiveAudienceActivity.this.f11921o != null) {
                ULogUtility.m16680p("LiveAudienceActivity", "unMute");
                LiveAudienceActivity.this.f11921o.m18169A();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$m */
    public class C2621m implements C4582b.b {
        public C2621m() {
        }

        @Override // com.perfectcorp.ycl.pages.live.C4582b.b
        /* renamed from: a */
        public void mo13762a() {
            if (LiveAudienceActivity.this.f11888K != null) {
                LiveAudienceActivity.this.f11888K.m18227f();
            }
        }

        @Override // com.perfectcorp.ycl.pages.live.C4582b.b
        /* renamed from: b */
        public void mo13763b() throws Resources.NotFoundException {
            if (LiveAudienceActivity.this.f11889L.getVisibility() == 0) {
                LiveAudienceActivity.this.m13716m5();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$n */
    public class C2622n extends C4582b {
        public C2622n(Context context, View view, C4600t c4600t, C4582b.b bVar, C4582b.a aVar) {
            super(context, view, c4600t, bVar, aVar);
        }

        @Override // com.perfectcorp.ycl.pages.live.C4582b, com.perfectcorp.ycl.pages.live.C4590j
        /* renamed from: q */
        public void mo13764q(View view) throws Resources.NotFoundException {
            super.mo13764q(view);
            LiveAudienceActivity.this.m13716m5();
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$o */
    public class AnimationAnimationListenerC2623o implements Animation.AnimationListener {
        public AnimationAnimationListenerC2623o() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            View viewFindViewById = LiveAudienceActivity.this.findViewById(R.id.chat_window);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.bottomMargin = LiveAudienceActivity.this.f11889L.getMeasuredHeight();
            viewFindViewById.setLayoutParams(layoutParams);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$p */
    public class AnimationAnimationListenerC2624p implements Animation.AnimationListener {
        public AnimationAnimationListenerC2624p() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            View viewFindViewById = LiveAudienceActivity.this.findViewById(R.id.message_bar_container);
            View viewFindViewById2 = LiveAudienceActivity.this.findViewById(R.id.chat_window);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById2.getLayoutParams();
            layoutParams.bottomMargin = viewFindViewById.getMeasuredHeight();
            viewFindViewById2.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$q */
    public class C2625q extends PromisedTask.AbstractC4504d<Live> {

        /* renamed from: a */
        public final /* synthetic */ String f11981a;

        /* renamed from: b */
        public final /* synthetic */ boolean f11982b;

        /* renamed from: c */
        public final /* synthetic */ String f11983c;

        /* renamed from: d */
        public final /* synthetic */ boolean f11984d;

        /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$q$a */
        public class a implements C4604x.d {
            public a() {
            }

            @Override // com.perfectcorp.ycl.pages.live.C4604x.d
            /* renamed from: a */
            public void mo13768a(C5366d c5366d) throws JSONException {
                LiveAudienceActivity.this.m13667G4(c5366d);
            }

            @Override // com.perfectcorp.ycl.pages.live.C4604x.d
            /* renamed from: b */
            public void mo13769b(C5366d c5366d) {
                LiveAudienceActivity.this.m13665E4(c5366d);
            }
        }

        /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$q$b */
        public class b extends PromisedTask.AbstractC4504d<Live.LivePermission> {
            public b() {
            }

            @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onDone(Live.LivePermission livePermission) {
                C4507b.m18101b("Is block by the host : " + livePermission.chatBlocked);
                LiveAudienceActivity.this.m13690a3(livePermission.chatBlocked.booleanValue());
                if (livePermission.chatBlocked.booleanValue() || !LiveAudienceActivity.this.f11911h0 || LiveAudienceActivity.this.f11928r0) {
                    return;
                }
                LiveAudienceActivity.this.m13697d3(true);
            }
        }

        public C2625q(String str, boolean z8, String str2, boolean z9) {
            this.f11981a = str;
            this.f11982b = z8;
            this.f11983c = str2;
            this.f11984d = z9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m13766b(int i9) {
            ULogUtility.m16670f("LiveAudienceActivity", "[queryLive] errorCode:" + i9);
            if (!NetworkCommon.isNetworkConnected()) {
                LiveAudienceActivity.this.m13679P4(R.string.ycl_no_network_fail);
                return;
            }
            if (i9 == 404) {
                LiveAudienceActivity.this.m13679P4(R.string.ycl_live_not_exist);
            } else if (i9 == NetworkManager.NetworkErrorCode.E_BAD_REQUEST.getValue() && LiveAudienceActivity.this.f11902Y) {
                LiveAudienceActivity.this.m13739y4();
                LiveAudienceActivity.this.m13741z4();
            }
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onDone(Live live) {
            C4677a.m18693T("queryLive");
            ULogUtility.m16670f("LiveAudienceActivity", "[queryLive] onDone");
            C4604x.m18359s().m18383y(live.liveId, LiveAudienceActivity.this.f11929s, this.f11981a, this.f11982b, LiveAudienceActivity.this.f11906c0, new a(), new C4604x.f() { // from class: com.cyberlink.you.activity.webinar.d1
                @Override // com.perfectcorp.ycl.pages.live.C4604x.f
                /* renamed from: a */
                public final void mo13933a(String str, String str2) {
                    ULogUtility.m16670f(str, str2);
                }
            });
            LiveAudienceActivity.this.f11925q = new C4600t(live);
            LiveAudienceActivity.this.f11890M.m18281K(String.valueOf(LiveAudienceActivity.this.f11925q.f16183a));
            LiveAudienceActivity.this.m13662C4(live);
            LiveAudienceActivity.this.m13664D4(live);
            LiveAudienceActivity liveAudienceActivity = LiveAudienceActivity.this;
            Boolean bool = live.chatEnabled;
            liveAudienceActivity.f11870B = bool != null ? bool.booleanValue() : true;
            LiveAudienceActivity.this.m13692b3();
            Live.QNA qna = live.f15981qa;
            if (qna != null && !C4509d.m18120b(qna.eventId)) {
                LiveAudienceActivity.this.f11916l0 = live.f15981qa.eventId;
                LiveAudienceActivity.this.f11911h0 = live.f15981qa.enabled.booleanValue();
                ULogUtility.m16670f("LiveAudienceActivity", "[queryLive] enableQNA : " + LiveAudienceActivity.this.f11911h0 + " meeting id :" + LiveAudienceActivity.this.f11916l0);
            }
            NetworkLive.isBlockByHost(this.f11981a, this.f11983c).done(new b());
            Live.ScheduleInterval scheduleInterval = live.scheduleInterval;
            LiveAudienceActivity.this.f11903Z = live.msgGroupEnabled.booleanValue();
            LiveAudienceActivity.this.f11904a0 = live.inviteURL;
            C4507b.m18101b("queryLive status : " + live.status);
            if (scheduleInterval != null) {
                try {
                    LiveAudienceActivity.this.f11874D = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).parse(scheduleInterval.startDate);
                } catch (ParseException unused) {
                    C4507b.m18101b("Parse schedule live time interval exception");
                }
            }
            LiveAudienceActivity.this.m13668H4(live, this.f11984d);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(final int i9) {
            super.onError(i9);
            LiveAudienceActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.c1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12108b.m13766b(i9);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$r */
    public class C2626r extends AbstractC4591k {
        public C2626r(Context context, View view, InterfaceC4605y interfaceC4605y) {
            super(context, view, interfaceC4605y);
        }

        @Override // com.perfectcorp.ycl.pages.live.AbstractC4591k
        /* renamed from: m */
        public boolean mo13771m(MenuItem menuItem, int i9) {
            return LiveAudienceActivity.this.m13659A4(menuItem, i9);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveAudienceActivity$s */
    public class RunnableC2627s implements Runnable {

        /* renamed from: b */
        public NileNetwork f11989b;

        /* renamed from: c */
        public View f11990c;

        public RunnableC2627s(NileNetwork nileNetwork) {
            this.f11989b = nileNetwork;
            this.f11990c = LiveAudienceActivity.this.findViewById(R.id.txtBBOSD);
        }

        @Override // java.lang.Runnable
        public void run() {
            NetQuality.Quality qualityM4473c = this.f11989b.f4920f.m4473c();
            NetQuality.Quality qualityM4472b = this.f11989b.f4920f.m4472b();
            NetQuality.Quality quality = NetQuality.Quality.ACCEPTABLE;
            if (qualityM4473c.compareTo(quality) > 0 || qualityM4472b.compareTo(quality) > 0) {
                this.f11990c.animate().cancel();
                this.f11990c.animate().alpha(1.0f).start();
                this.f11990c.setSelected(qualityM4473c.compareTo(NetQuality.Quality.POOR) >= 0);
            } else if (this.f11990c.getAlpha() != BitmapDescriptorFactory.HUE_RED) {
                this.f11990c.animate().cancel();
                this.f11990c.animate().alpha(BitmapDescriptorFactory.HUE_RED).start();
            }
            C4677a.m18684I(this, 500L);
        }
    }

    static {
        CookieManager cookieManager = new CookieManager();
        f11866L0 = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        f11867M0 = new AudioManager.OnAudioFocusChangeListener() { // from class: com.cyberlink.you.activity.webinar.o
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i9) {
                LiveAudienceActivity.m13645t4(i9);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C3 */
    public /* synthetic */ void m13510C3(boolean z8) {
        this.f11907d0 = z8;
        EmojiconEditText emojiconEditText = (EmojiconEditText) findViewById(R.id.message_edit_text);
        if (this.f11870B && emojiconEditText != null) {
            if (z8) {
                emojiconEditText.setText((CharSequence) null);
                emojiconEditText.setHint(R.string.wbn_block_by_host);
            } else {
                emojiconEditText.setHint(R.string.ycl_edit_message_text_hint_enter_message);
            }
        }
        m13695c3();
        if (this.f11907d0) {
            m13697d3(false);
        } else if (this.f11911h0) {
            findViewById(R.id.QNA_icon).setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D3 */
    public /* synthetic */ void m13513D3(boolean z8) throws Resources.NotFoundException {
        if (z8) {
            m13734w3();
        }
        View viewFindViewById = findViewById(R.id.qna_window);
        if (viewFindViewById != null) {
            if (z8) {
                m13709j3();
                viewFindViewById.setVisibility(0);
            } else if (viewFindViewById.getVisibility() == 0) {
                m13683T4();
            }
        }
        View viewFindViewById2 = findViewById(R.id.QNA_icon);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setEnabled(z8);
        }
        m13695c3();
        if (z8) {
            setRequestedOrientation(1);
            C4677a.m18685K(this.f11869A0);
        }
        findViewById(R.id.fullscreen_btn).setEnabled(!z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E3 */
    public /* synthetic */ void m13516E3() {
        if (this.f11941y) {
            double dM18176h = (this.f11921o.m18176h() - this.f11921o.m18177i()) / 1000.0d;
            C4507b.m18108i("[checkBufferPosition] bufferTime : " + dM18176h);
            if (dM18176h < this.f11896S || this.f11931t == 2) {
                int i9 = this.f11894Q + 1;
                this.f11894Q = i9;
                if (i9 >= 3) {
                    ULogUtility.m16670f("LiveAudienceActivity", "[checkBufferPosition] affect 480p buffer, reset raise quality condition");
                    C4507b.m18108i("[checkBufferPosition] affect 480p buffer, reset raise quality condition");
                    m13670J4();
                    this.f11897T = 0;
                    return;
                }
            }
            if (System.currentTimeMillis() - this.f11893P <= DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
                m13703g3();
                return;
            }
            this.f11897T++;
            C4507b.m18108i("[checkBufferPosition] successCheckBufferTimes : " + this.f11897T);
            if (this.f11897T >= 3) {
                m13669I4();
            } else {
                m13670J4();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F3 */
    public /* synthetic */ void m13519F3() {
        if (this.f11941y) {
            if (this.f11931t != 3 && this.f11868A) {
                int i9 = this.f11939x + 1;
                this.f11939x = i9;
                int i10 = this.f11895R;
                if (i9 >= i10) {
                    if (i10 < 6) {
                        this.f11895R = i10 + 1;
                    }
                    ULogUtility.m16670f("LiveAudienceActivity", "Player not start playing too long... mCurrentPlayerState:" + this.f11931t);
                    C4677a.m18693T("Player not start playing too long...");
                    m13701f3();
                }
            }
            m13705h3();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G3 */
    public /* synthetic */ void m13522G3() throws JSONException {
        if (!this.f11902Y) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", RollCallEvent.STOP.name());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            this.f11938w0.add(jSONObject);
            return;
        }
        Dialog dialog = this.f11930s0;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f11930s0.dismiss();
        m13689Z4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H3 */
    public /* synthetic */ void m13525H3(Emojicon emojicon) {
        EmojiconEditText emojiconEditText = (EmojiconEditText) this.f11888K.m18229h();
        int selectionStart = emojiconEditText.getSelectionStart();
        int selectionEnd = emojiconEditText.getSelectionEnd();
        if (selectionStart < 0) {
            emojiconEditText.append(emojicon.getEmoji());
        } else {
            emojiconEditText.getText().replace(Math.min(selectionStart, selectionEnd), Math.max(selectionStart, selectionEnd), emojicon.getEmoji(), 0, emojicon.getEmoji().length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I3 */
    public /* synthetic */ void m13528I3(int i9) {
        if (getResources().getConfiguration().orientation == 2) {
            findViewById(R.id.root).scrollTo(0, i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J3 */
    public /* synthetic */ void m13531J3() throws Resources.NotFoundException {
        m13696c5(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K3 */
    public /* synthetic */ void m13534K3() throws Resources.NotFoundException {
        m13696c5(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L3 */
    public /* synthetic */ void m13537L3() {
        setRequestedOrientation(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M3 */
    public /* synthetic */ void m13540M3(View view) throws NumberFormatException {
        if (C4677a.m18713q("SecretTrick", false) || C4677a.m18679C()) {
            m13687X4(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N3 */
    public /* synthetic */ void m13543N3(View view) {
        m13697d3(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O3 */
    public /* synthetic */ boolean m13546O3(View view, MotionEvent motionEvent) throws Resources.NotFoundException {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        if (this.f11889L.getVisibility() == 0) {
            m13716m5();
        }
        m13734w3();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P3 */
    public /* synthetic */ boolean m13549P3(View view, MotionEvent motionEvent) throws Resources.NotFoundException {
        if (motionEvent.getAction() == 0) {
            if (this.f11889L.getVisibility() != 0) {
                return false;
            }
            m13716m5();
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return false;
        }
        view.performClick();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q3 */
    public /* synthetic */ void m13552Q3(View view) {
        if (C4677a.m18713q("SecretTrick", false)) {
            m13714l5();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R3 */
    public /* synthetic */ void m13555R3(View view) {
        C4677a.m18685K(this.f11869A0);
        setRequestedOrientation(6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S3 */
    public /* synthetic */ void m13558S3(View view) {
        setRequestedOrientation(1);
        C4677a.m18684I(this.f11869A0, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T3 */
    public /* synthetic */ boolean m13561T3(View view, MotionEvent motionEvent) throws Resources.NotFoundException {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        if (this.f11889L.getVisibility() == 0) {
            m13716m5();
        } else if (getResources().getConfiguration().orientation == 2) {
            m13719n5();
        }
        ULogUtility.m16670f("LiveAudienceActivity", "videoContainer orientation:" + getResources().getConfiguration().orientation);
        m13734w3();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U3 */
    public /* synthetic */ void m13565U3(int i9) {
        m13712k5();
        if (this.f11911h0 && findViewById(R.id.qna_window).getVisibility() == 0) {
            m13683T4();
        }
        if (!NetworkCommon.isNetworkConnected()) {
            findViewById(R.id.QNA_icon).setEnabled(false);
            m13681R4(R.string.ycl_no_network_fail);
        } else if (i9 == 3) {
            C4677a.m18695V(getString(R.string.network_unstable));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V3 */
    public /* synthetic */ void m13569V3() {
        m13723p5();
        if (this.f11868A && this.f11931t == 3) {
            LivePlayer livePlayer = this.f11921o;
            if (livePlayer != null) {
                livePlayer.m18187s(false);
                C4507b.m18101b("[onStateChange] showVideoFrameAndUnMute");
                this.f11921o.m18193y();
                m13733v4();
            }
            m13682S4(false, "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W3 */
    public /* synthetic */ void m13573W3() {
        if (NetworkCommon.isNetworkConnected()) {
            C4677a.m18693T("sync time for delay too long...");
            this.f11921o.m18174e(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X3 */
    public /* synthetic */ void m13577X3(DialogInterface dialogInterface, int i9) {
        m13720o3();
    }

    /* renamed from: Y3 */
    public static /* synthetic */ void m13581Y3(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z3 */
    public /* synthetic */ void m13585Z3(DialogInterface dialogInterface, int i9) {
        CLUtility.m16477P1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a4 */
    public /* synthetic */ void m13588a4(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Permission.MICROPHONE.mo16657b());
        C4508c.m18118e(this, arrayList, new C2609a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b4 */
    public /* synthetic */ void m13591b4(View view) {
        this.f11928r0 = true;
        m13709j3();
    }

    /* renamed from: c4 */
    public static /* synthetic */ void m13594c4(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d4 */
    public /* synthetic */ void m13597d4(View view) {
        m13720o3();
    }

    /* renamed from: e4 */
    public static /* synthetic */ void m13600e4(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f4 */
    public /* synthetic */ void m13603f4(View view) {
        this.f11944z0 = true;
        m13720o3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g4 */
    public /* synthetic */ void m13606g4(View view) {
        GroupInfoActivity.m8433T0(this, this.f11904a0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h4 */
    public /* synthetic */ void m13609h4(View view) {
        m13709j3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i4 */
    public /* synthetic */ void m13612i4(View view, View view2) {
        LivePlayer livePlayer = this.f11921o;
        if (livePlayer != null) {
            livePlayer.m18184p();
        }
        MeetingManager.m5630w(this.f11916l0, MeetingManager.MeetingStatus.IN_MEETING);
        this.f11924p0.m4976y8(false, null);
        this.f11924p0.m4951l8(true, null);
        this.f11924p0.m4942h3();
        RunnableC2627s runnableC2627s = new RunnableC2627s(this.f11924p0);
        this.f11920n0 = runnableC2627s;
        C4677a.m18683H(runnableC2627s);
        view.clearAnimation();
        view.setVisibility(8);
        findViewById(R.id.qna_join_cancel).setVisibility(8);
        m13685V4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j4 */
    public /* synthetic */ void m13615j4(View view, DialogInterface dialogInterface, int i9) {
        view.setVisibility(8);
        m13683T4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k4 */
    public /* synthetic */ void m13618k4(final View view, View view2) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.wbn_qna_leave));
        builderM16382a.setMessage(getString(R.string.wbn_qna_stop_talk_to_host));
        builderM16382a.setPositiveButton(R.string.bc_dialog_button_cancel, (DialogInterface.OnClickListener) null);
        builderM16382a.setNegativeButton(R.string.wbn_qna_leave, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.p0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f12197b.m13615j4(view, dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.show();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 2.0f);
        Button button = alertDialogCreate.getButton(-1);
        if (button != null) {
            ((LinearLayout) button.getParent()).setOrientation(1);
            button.setLayoutParams(layoutParams);
            C5179r0.m20247b(button, 1);
        }
        Button button2 = alertDialogCreate.getButton(-2);
        if (button2 != null) {
            button2.setLayoutParams(layoutParams);
            button2.setTextColor(getResources().getColor(R.color.you_color_delete_red));
            C5179r0.m20247b(button2, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l4 */
    public /* synthetic */ void m13621l4(View view) {
        MeetingManager.m5631x(this.f11916l0);
        m13709j3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m4 */
    public /* synthetic */ boolean m13624m4(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        this.f11878F = itemId;
        if (itemId == 0) {
            this.f11915l = this.f11917m;
            this.f11884I = -1;
        } else if (itemId != 1) {
            this.f11915l = m13715m3(this.f11878F + TtmlNode.TAG_P);
            this.f11884I = this.f11878F + (-2);
        } else {
            this.f11915l = this.f11919n;
        }
        C4677a.m18693T(this.f11915l.toString());
        m13701f3();
        return true;
    }

    /* renamed from: n4 */
    public static /* synthetic */ void m13627n4(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o4 */
    public /* synthetic */ void m13630o4(View view) {
        this.f11932t0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p4 */
    public /* synthetic */ void m13633p4(View view) {
        this.f11930s0.dismiss();
        m13661B4();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q4 */
    public /* synthetic */ void m13636q4(boolean z8, int i9, View view) {
        if (z8) {
            m13720o3();
        } else if (i9 == R.string.ycl_vod_is_preparing) {
            m13681R4(R.string.ycl_end_live_title);
        } else {
            m13681R4(i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r4 */
    public /* synthetic */ void m13639r4(DialogInterface dialogInterface, int i9) {
        CLUtility.m16477P1(this);
    }

    /* renamed from: s4 */
    public static /* synthetic */ void m13642s4(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: t4 */
    public static /* synthetic */ void m13645t4(int i9) {
        Log.d("LiveAudienceActivity", "onAudioFocusChange: " + (i9 != -3 ? i9 != -2 ? i9 != -1 ? i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "AUDIOFOCUS_INVALID" : "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE" : "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK" : "AUDIOFOCUS_GAIN_TRANSIENT" : "AUDIOFOCUS_GAIN" : "AUDIOFOCUS_LOSS" : "AUDIOFOCUS_LOSS_TRANSIENT" : "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK"));
    }

    /* renamed from: A3 */
    public final void m13658A3(Intent intent) {
        this.f11925q = C4600t.m18339a(intent);
        this.f11886J = new C2626r(this, findViewById(R.id.controls_root), this.f11921o);
        this.f11890M = new C4597q(this, findViewById(R.id.root), this.f11883H0);
        m13740z3();
        this.f11888K = new C2622n(this, findViewById(R.id.chat_message_bar), this.f11925q, new C2621m(), new C4582b.a() { // from class: com.cyberlink.you.activity.webinar.l
            @Override // com.perfectcorp.ycl.pages.live.C4582b.a
            /* renamed from: a */
            public final void mo13948a(int i9) {
                this.f12151a.m13528I3(i9);
            }
        });
    }

    /* renamed from: A4 */
    public final boolean m13659A4(MenuItem menuItem, int i9) {
        if (this.f11921o == null || menuItem.getGroupId() != 1) {
            return false;
        }
        this.f11921o.m18190v(i9, menuItem.getItemId() - 2);
        return true;
    }

    /* renamed from: B3 */
    public final int m13660B3(String str, String str2) {
        String[] strArr;
        int i9;
        double d9;
        String[] strArr2;
        double d10;
        String[] strArr3;
        String[] strArr4;
        String strM7391n1 = Globals.m7391n1();
        int iLastIndexOf = strM7391n1.lastIndexOf(40);
        if (iLastIndexOf > 0) {
            strM7391n1 = strM7391n1.substring(0, iLastIndexOf);
        }
        String[] strArrSplit = strM7391n1.replace(StringUtils.SPACE, "").split("\\.");
        String[] strArrSplit2 = str.split("\\.");
        String[] strArrSplit3 = str2.split("\\.");
        int iMax = Math.max(Math.max(strArrSplit2.length, strArrSplit3.length), strArrSplit.length);
        int i10 = 1;
        int i11 = (iMax * 2) - 1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i12 < iMax) {
            double d11 = i13;
            int length = strArrSplit.length;
            double d12 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (length > i12) {
                i9 = i12;
                strArr = strArrSplit2;
                d9 = Integer.parseInt(strArrSplit[i12]) * Math.pow(10.0d, i11);
            } else {
                strArr = strArrSplit2;
                i9 = i12;
                d9 = 0.0d;
            }
            i13 = (int) (d11 + d9);
            double d13 = i14;
            String[] strArr5 = strArr;
            int i16 = i9;
            if (strArr5.length > i16) {
                strArr2 = strArr5;
                d10 = Integer.parseInt(strArr5[i16]) * Math.pow(10.0d, i11);
            } else {
                strArr2 = strArr5;
                d10 = 0.0d;
            }
            int i17 = (int) (d13 + d10);
            double d14 = i15;
            if (strArrSplit3.length > i16) {
                strArr3 = strArrSplit;
                strArr4 = strArrSplit3;
                d12 = Integer.parseInt(strArrSplit3[i16]) * Math.pow(10.0d, i11);
            } else {
                strArr3 = strArrSplit;
                strArr4 = strArrSplit3;
            }
            i15 = (int) (d14 + d12);
            i11 -= 2;
            i12 = i16 + 1;
            strArrSplit3 = strArr4;
            i14 = i17;
            strArrSplit = strArr3;
            strArrSplit2 = strArr2;
            i10 = 1;
        }
        Object[] objArr = new Object[i10];
        objArr[0] = "[LiveAudienceActivity][isOldVersionWithRequiredVersion] currentVersion: " + i13 + " / requiredVersion: " + i14 + " / recommendedVersion: " + i15;
        C4507b.m18101b(objArr);
        if (i13 < i14) {
            return -1;
        }
        return i13 < i15 ? 0 : 1;
    }

    /* renamed from: B4 */
    public final void m13661B4() {
        C1260a.m5682t(this.f11914k0, m13718n3(), false).m15439e(new C2614f());
    }

    /* renamed from: C4 */
    public final void m13662C4(Live live) {
        ArrayList<String> arrayList = live.quality;
        if (arrayList != null) {
            this.f11882H = arrayList;
            ULogUtility.m16670f("LiveAudienceActivity", "lowerQualities : " + this.f11882H);
            if (this.f11882H.size() > 0) {
                C4507b.m18108i("lowerQualities : " + this.f11882H);
            } else {
                C4507b.m18108i("There is no other qualities option");
            }
        }
        Live.StreamAddrs streamAddrs = live.streamAddrs;
        if (streamAddrs != null) {
            Uri uri = streamAddrs.flv;
            if (uri != null && !uri.equals(Uri.EMPTY)) {
                this.f11915l = live.streamAddrs.flv;
            }
            Uri uri2 = live.streamAddrs.rtmp;
            if (uri2 != null && !uri2.equals(Uri.EMPTY)) {
                if (this.f11915l == null) {
                    this.f11915l = live.streamAddrs.rtmp;
                }
                this.f11919n = live.streamAddrs.rtmp;
            }
        } else {
            this.f11919n = Uri.parse(live.pushAddr);
        }
        this.f11917m = this.f11915l;
    }

    @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
    /* renamed from: D0 */
    public void mo13663D0(IOException iOException, final int i9, LivePlayer livePlayer) {
        if (livePlayer != this.f11921o) {
            return;
        }
        ULogUtility.m16670f("LiveAudienceActivity", "[onLoadError] IOException:" + iOException.getMessage());
        ULogUtility.m16670f("LiveAudienceActivity", "[onLoadError] continueLodeError:" + iOException.getMessage());
        C4507b.m18108i("onLoadError: " + iOException.getClass().getSimpleName() + ", onLoadError:" + i9);
        C4677a.m18693T("" + iOException.getClass().getSimpleName() + ", onLoadError:" + i9);
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.v
            @Override // java.lang.Runnable
            public final void run() {
                this.f12215b.m13565U3(i9);
            }
        });
    }

    /* renamed from: D4 */
    public final void m13664D4(Live live) {
        this.f11908e0 = false;
        if (live == null || !live.watermarkEnabled.booleanValue()) {
            return;
        }
        this.f11908e0 = true;
        Live.WaterMark waterMark = live.watermark;
        this.f11909f0 = waterMark.text;
        this.f11910g0 = waterMark.position;
        if (C4509d.m18120b(this.f11905b0)) {
            return;
        }
        this.f11909f0 = this.f11905b0;
    }

    /* renamed from: E4 */
    public final void m13665E4(C5366d c5366d) {
        String str = c5366d.event;
        C5366d.a aVar = c5366d.attributes;
        ULogUtility.m16670f("LiveAudienceActivity", "[queryArchiveOnDoneEvent] id:" + c5366d.f18250id + " archive:" + str);
        str.hashCode();
        if (str.equals("live.msg.liked")) {
            m13724q3(aVar);
        } else if (str.equals("live.msg.unliked")) {
            m13730u3(aVar);
        }
    }

    /* renamed from: F4 */
    public final void m13666F4(String str, String str2, boolean z8) {
        NetworkLive.getLive(str2, NetworkLive.EMPTY_PARAMETER.longValue(), str, z8).done(new C2625q(str2, z8, str, !C4509d.m18120b(str) ? str.equals(C4509d.m18120b(this.f11929s) ? this.f11927r : this.f11929s) : false));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0031  */
    /* renamed from: G4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m13667G4(C5366d c5366d) throws JSONException {
        char c9;
        Integer num;
        ArrayList<String> arrayList;
        String str = c5366d.event;
        C5366d.a aVar = c5366d.attributes;
        ULogUtility.m16670f("LiveAudienceActivity", "[queryLiveOnDoneEvent] id:" + c5366d.f18250id + " live_event:" + str);
        str.hashCode();
        switch (str.hashCode()) {
            case -1812300891:
                if (!str.equals("live.qa.enabled")) {
                    c9 = 65535;
                    break;
                } else {
                    c9 = 0;
                    break;
                }
            case -1718004072:
                if (str.equals("live.qa.disabled")) {
                    c9 = 1;
                    break;
                }
                break;
            case -1648691118:
                if (str.equals("live.user.candidate.assistant")) {
                    c9 = 2;
                    break;
                }
                break;
            case -1500536387:
                if (str.equals("live.user.assistant")) {
                    c9 = 3;
                    break;
                }
                break;
            case -1255391106:
                if (str.equals("live.msg.liked")) {
                    c9 = 4;
                    break;
                }
                break;
            case -1240735056:
                if (str.equals("live.rollcall.started")) {
                    c9 = 5;
                    break;
                }
                break;
            case -1240001518:
                if (str.equals("live.user.unblocked")) {
                    c9 = 6;
                    break;
                }
                break;
            case -1227869188:
                if (str.equals("live.rollcall.stopped")) {
                    c9 = 7;
                    break;
                }
                break;
            case -1195499014:
                if (str.equals("live.status.changed")) {
                    c9 = '\b';
                    break;
                }
                break;
            case -421977658:
                if (str.equals("live.version.info")) {
                    c9 = '\t';
                    break;
                }
                break;
            case -189333280:
                if (str.equals("live.start")) {
                    c9 = '\n';
                    break;
                }
                break;
            case -155560589:
                if (str.equals("live.unpublished")) {
                    c9 = 11;
                    break;
                }
                break;
            case -117521463:
                if (str.equals("live.completed")) {
                    c9 = '\f';
                    break;
                }
                break;
            case -3245755:
                if (str.equals("live.msg.unliked")) {
                    c9 = CharUtils.f19105CR;
                    break;
                }
                break;
            case 101167629:
                if (str.equals("live.publish")) {
                    c9 = 14;
                    break;
                }
                break;
            case 514182418:
                if (str.equals("live.rollcall.duration.updated")) {
                    c9 = 15;
                    break;
                }
                break;
            case 857127632:
                if (str.equals("live.chat.disabled")) {
                    c9 = 16;
                    break;
                }
                break;
            case 979237827:
                if (str.equals("live.quality.changed")) {
                    c9 = 17;
                    break;
                }
                break;
            case 1731935371:
                if (str.equals("live.user.blocked")) {
                    c9 = 18;
                    break;
                }
                break;
            case 1831287978:
                if (str.equals("live.msg.deleted")) {
                    c9 = 19;
                    break;
                }
                break;
            case 1872998509:
                if (str.equals("live.chat.enabled")) {
                    c9 = 20;
                    break;
                }
                break;
            case 1964110155:
                if (str.equals("live.user.normal.for.assistant.case")) {
                    c9 = 21;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
                this.f11911h0 = true;
                this.f11928r0 = false;
                if (!this.f11907d0) {
                    m13697d3(true);
                    break;
                }
                break;
            case 1:
                this.f11911h0 = false;
                if (!this.f11907d0) {
                    m13697d3(false);
                    break;
                }
                break;
            case 2:
                if (aVar != null) {
                    if ((!C4509d.m18120b(aVar.uid) && aVar.uid.equals(this.f11906c0)) || (!C4509d.m18120b(aVar.uuid) && aVar.uuid.equals(Key.Init.Parameter.uuid))) {
                        NetworkLive.candidateAssistantAck(this.f11914k0, m13718n3());
                        m13674M4();
                        break;
                    }
                }
                break;
            case 3:
                this.f11890M.m18289r(aVar.uid);
                if ((!C4509d.m18120b(aVar.uid) && aVar.uid.equals(this.f11906c0)) || (!C4509d.m18120b(aVar.uuid) && aVar.uuid.equals(Key.Init.Parameter.uuid))) {
                    m13674M4();
                    break;
                }
                break;
            case 4:
                m13724q3(aVar);
                break;
            case 5:
                m13726r3(c5366d.timestamp, aVar.duration);
                break;
            case 6:
                if (aVar != null) {
                    if ((!C4509d.m18120b(aVar.uid) && aVar.uid.equals(this.f11906c0)) || (!C4509d.m18120b(aVar.uuid) && aVar.uuid.equals(Key.Init.Parameter.uuid))) {
                        m13690a3(false);
                        break;
                    }
                }
                break;
            case 7:
                m13728s3();
                break;
            case '\b':
                TextView textView = (TextView) findViewById(R.id.people_count);
                if (aVar != null && (num = aVar.onlineUsers) != null) {
                    textView.setText(String.valueOf(num));
                    break;
                }
                break;
            case '\t':
                int iM13660B3 = m13660B3(aVar.required.get("android"), aVar.recommended.get("android"));
                if (iM13660B3 <= 0) {
                    AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
                    if (iM13660B3 < 0) {
                        builderM16382a.setMessage(R.string.required_update_version);
                        builderM16382a.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.c0
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i9) {
                                this.f12107b.m13577X3(dialogInterface, i9);
                            }
                        });
                    } else if (iM13660B3 == 0) {
                        builderM16382a.setMessage(R.string.recommended_update_version);
                        builderM16382a.setNegativeButton(getString(R.string.wbn_qna_later), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.d0
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i9) {
                                LiveAudienceActivity.m13581Y3(dialogInterface, i9);
                            }
                        });
                    }
                    builderM16382a.setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.e0
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i9) {
                            this.f12118b.m13585Z3(dialogInterface, i9);
                        }
                    });
                    builderM16382a.setCancelable(false);
                    builderM16382a.create().show();
                }
                C4507b.m18101b("[LiveAudienceActivity][queryLiveOnDoneEvent] featureName: " + aVar.featureName + " / resultOfOldVersion: " + iM13660B3);
                break;
            case '\n':
                this.f11874D = null;
                m13682S4(false, "");
                m13708i5();
                m13710j5();
                break;
            case 11:
                this.f11868A = false;
                m13736x3();
                m13712k5();
                if (aVar == null || C4509d.m18120b(aVar.resumeTime)) {
                    m13681R4(R.string.wbn_presenter_is_offline);
                } else {
                    this.f11876E = null;
                    try {
                        this.f11876E = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).parse(aVar.resumeTime);
                        m13711k3();
                    } catch (ParseException unused) {
                        C4507b.m18101b("Parse pause time interval exception");
                    }
                }
                m13723p5();
                if (this.f11911h0 && findViewById(R.id.qna_window).getVisibility() == 0) {
                    m13683T4();
                    break;
                }
                break;
            case '\f':
                this.f11868A = false;
                this.f11941y = false;
                m13723p5();
                m13710j5();
                m13682S4(true, "");
                m13736x3();
                if (aVar != null && NetworkLive.STATE.VIDEO_ON_DEMAND.equals(aVar.state)) {
                    m13693b5(R.string.ycl_vod_is_preparing, false);
                } else {
                    m13693b5(R.string.ycl_end_live_title, false);
                }
                C4582b c4582b = this.f11888K;
                if (c4582b != null) {
                    c4582b.m18233v(false);
                }
                m13712k5();
                break;
            case '\r':
                m13730u3(aVar);
                break;
            case 14:
                this.f11868A = true;
                this.f11876E = null;
                this.f11874D = null;
                m13708i5();
                m13710j5();
                m13725q5();
                break;
            case 15:
                m13729t3(aVar.duration);
                break;
            case 16:
                this.f11870B = false;
                m13692b3();
                break;
            case 17:
                C4677a.m18693T("Live quality changed, " + aVar);
                if (aVar != null && (arrayList = aVar.quality) != null && arrayList.size() == 0) {
                    this.f11882H.clear();
                    if (this.f11884I >= 0) {
                        C4677a.m18685K(this.f11877E0);
                        this.f11915l = this.f11917m;
                        this.f11884I = -1;
                        m13701f3();
                        break;
                    }
                }
                break;
            case 18:
                if (aVar != null) {
                    if ((!C4509d.m18120b(aVar.uid) && aVar.uid.equals(this.f11906c0)) || (!C4509d.m18120b(aVar.uuid) && aVar.uuid.equals(Key.Init.Parameter.uuid))) {
                        m13690a3(true);
                        break;
                    }
                }
                break;
            case 19:
                if (aVar != null && !C4509d.m18120b(aVar.messageId)) {
                    this.f11890M.m18294w(aVar.messageId);
                    break;
                }
                break;
            case 20:
                this.f11870B = true;
                m13692b3();
                break;
            case 21:
                this.f11890M.m18277G(aVar.uid);
                break;
        }
    }

    /* renamed from: H4 */
    public final void m13668H4(Live live, boolean z8) {
        runOnUiThread(new RunnableC2610b(live, z8));
    }

    /* renamed from: I4 */
    public final void m13669I4() {
        if (this.f11884I < 0) {
            return;
        }
        this.f11884I = -1;
        this.f11878F = 0;
        this.f11915l = this.f11917m;
        C4507b.m18101b("[raiseQuality] showVideoFrameAndUnMute");
        m13713l3().m18193y();
        if (this.f11921o.m18183o()) {
            this.f11921o.m18182n();
            this.f11921o.m18186r(false);
        }
        this.f11921o = m13713l3();
        m13733v4();
        this.f11886J = new C2626r(this, findViewById(R.id.controls_root), this.f11921o);
        this.f11939x = 0;
        this.f11880G.clear();
        this.f11933u = 0L;
        if (this.f11921o == this.f11891N) {
            C4507b.m18108i("[raiseQuality] primeLivePlayer");
            C4677a.m18693T("raiseQuality : primeLivePlayer");
        } else {
            C4507b.m18108i("[raiseQuality] secondaryLivePlayer");
            C4677a.m18693T("raiseQuality : secondaryLivePlayer");
        }
    }

    /* renamed from: J4 */
    public final void m13670J4() {
        ULogUtility.m16670f("LiveAudienceActivity", "retryRaiseQuality");
        C4507b.m18108i("retryRaiseQuality");
        if (m13713l3().m18183o()) {
            m13713l3().m18182n();
            m13713l3().m18186r(false);
        }
        C4677a.m18685K(this.f11877E0);
        C4677a.m18684I(this.f11877E0, 60000L);
    }

    /* renamed from: K4 */
    public final void m13671K4(String str) {
        C4677a.m18705i().edit().putString("LastLiveId", str).apply();
    }

    /* renamed from: L4 */
    public final void m13672L4() {
        this.f11912i0 = (TextView) findViewById(R.id.qna_subtitle);
        this.f11913j0 = (TextView) findViewById(R.id.qna_content);
        findViewById(R.id.qna_prepare_ask).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12196b.m13588a4(view);
            }
        });
        findViewById(R.id.qna_prepare_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12201b.m13591b4(view);
            }
        });
        C5179r0.m20247b((TextView) findViewById(R.id.qna_prepare_cancel), 1);
    }

    @Override // com.perfectcorp.ycl.BaseActivity
    /* renamed from: M0 */
    public void mo13673M0() throws Resources.NotFoundException {
        super.mo13673M0();
        if (this.f11889L.getVisibility() == 0) {
            m13716m5();
        }
    }

    /* renamed from: M4 */
    public final void m13674M4() {
        SimpleMessageDialog simpleMessageDialog = this.f11942y0;
        if (simpleMessageDialog == null || !simpleMessageDialog.isShowing()) {
            SimpleMessageDialog simpleMessageDialogM24864k = new SimpleMessageDialog.C6499b(this, false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(R.string.bc_dialog_button_close), new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.v0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveAudienceActivity.m13594c4(view);
                }
            }, true, SimpleMessageDialog.C6500c.f21850g)).m24870q(C4677a.m18710n().getString(R.string.wbn_assistant_title), getResources().getColor(R.color.ycl_text_style_a)).m24869p(C4677a.m18710n().getString(R.string.wbn_assistant_content), SimpleMessageDialog.C6500c.f21849f).m24864k();
            this.f11942y0 = simpleMessageDialogM24864k;
            simpleMessageDialogM24864k.show();
        }
    }

    @Override // com.perfectcorp.ycl.BaseActivity
    /* renamed from: N0 */
    public boolean mo13675N0() throws Resources.NotFoundException {
        ULogUtility.m16670f("LiveAudienceActivity", "onBack");
        if (this.f11936v0.getVisibility() == 0) {
            this.f11936v0.setVisibility(8);
            return true;
        }
        if (this.f11889L.getVisibility() == 0) {
            m13716m5();
            return true;
        }
        m13676N4();
        return true;
    }

    /* renamed from: N4 */
    public final void m13676N4() {
        m13732v3();
        String string = C4677a.m18710n().getString(R.string.bc_dialog_button_ok);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12106b.m13597d4(view);
            }
        };
        int i9 = SimpleMessageDialog.C6500c.f21850g;
        SimpleMessageDialog.C6500c c6500c = new SimpleMessageDialog.C6500c(string, onClickListener, true, i9);
        SimpleMessageDialog simpleMessageDialogM24864k = new SimpleMessageDialog.C6499b(this, false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(c6500c).m24867n(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(R.string.bc_dialog_button_cancel), new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveAudienceActivity.m13600e4(view);
            }
        }, true, i9)).m24870q(C4677a.m18710n().getString(R.string.bc_dialog_title_Notice), getResources().getColor(R.color.ycl_text_style_a)).m24869p(C4677a.m18710n().getString(R.string.plk_confirm_to_leave_audience), SimpleMessageDialog.C6500c.f21849f).m24864k();
        this.f11926q0 = simpleMessageDialogM24864k;
        simpleMessageDialogM24864k.show();
    }

    @Override // com.perfectcorp.ycl.BaseActivity
    /* renamed from: O0 */
    public void mo13677O0() {
        ULogUtility.m16670f("LiveAudienceActivity", "onScreenOff");
        m13712k5();
    }

    /* renamed from: O4 */
    public final void m13678O4(boolean z8) {
        m13721o5();
        this.f11886J.m18250o(z8);
        this.f11921o.m18192x(z8);
        this.f11923p = z8;
    }

    /* renamed from: P4 */
    public final void m13679P4(int i9) {
        if (this.f11944z0) {
            this.f11944z0 = false;
            m13732v3();
            SimpleMessageDialog simpleMessageDialogM24864k = new SimpleMessageDialog.C6499b(this, false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(R.string.bc_dialog_button_close), new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.j0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12143b.m13603f4(view);
                }
            }, true, SimpleMessageDialog.C6500c.f21850g)).m24870q(C4677a.m18710n().getString(R.string.bc_dialog_title_Notice), getResources().getColor(R.color.ycl_text_style_a)).m24869p(C4677a.m18710n().getString(i9), SimpleMessageDialog.C6500c.f21849f).m24864k();
            this.f11926q0 = simpleMessageDialogM24864k;
            simpleMessageDialogM24864k.show();
        }
    }

    /* renamed from: Q4 */
    public final void m13680Q4(String str) {
        if (!this.f11903Z || C4509d.m18120b(this.f11904a0)) {
            return;
        }
        boolean z8 = true;
        if (getString(R.string.ycl_scheduled_live_not_yet_start).equals(str) || this.f11874D != null) {
            findViewById(R.id.LiveGroupButtonAnchor).setVisibility(8);
            findViewById(R.id.btnLiveSurvey).setVisibility(8);
        } else if (!getString(R.string.ycl_end_live_title).equals(str)) {
            z8 = false;
        }
        if (!z8) {
            findViewById(R.id.LiveGroupLayout).setVisibility(8);
            return;
        }
        findViewById(R.id.btnLiveJoinGroup).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12228b.m13606g4(view);
            }
        });
        findViewById(R.id.LiveGroupLayout).setVisibility(0);
        findViewById(R.id.LiveGroupLayout).bringToFront();
    }

    /* renamed from: R4 */
    public final void m13681R4(int i9) {
        m13682S4(true, getResources().getString(i9));
    }

    /* renamed from: S4 */
    public final void m13682S4(boolean z8, String str) {
        TextView textView = (TextView) findViewById(R.id.on_video_message);
        if (textView == null) {
            return;
        }
        textView.setText(str);
        textView.setVisibility(z8 ? 0 : 8);
        if (z8) {
            textView.bringToFront();
        }
        m13680Q4(str);
    }

    /* renamed from: T4 */
    public final void m13683T4() {
        findViewById(R.id.qna_title).setVisibility(0);
        findViewById(R.id.QNA_icon).setSelected(false);
        if (!this.f11911h0 || this.f11907d0) {
            this.f11912i0.setText(getString(R.string.wbn_qna_host_turn_off));
            this.f11913j0.setText("");
        } else {
            this.f11912i0.setText(getString(R.string.wbn_qna_conversation_over));
            this.f11913j0.setText(getString(R.string.wbn_qna_raise_hand_again));
        }
        C4677a.m18685K(this.f11920n0);
        MeetingManager.m5631x(this.f11916l0);
        ULogUtility.m16680p("LiveAudienceActivity", "unMuteRunnable run");
        C4677a.m18684I(this.f11871B0, 4000L);
        RTCAudioManager rTCAudioManager = this.f11922o0;
        if (rTCAudioManager != null) {
            rTCAudioManager.m5068s();
            this.f11922o0 = null;
        }
        findViewById(R.id.qna_prepare_layout).setVisibility(8);
        findViewById(R.id.qna_join_layout).setVisibility(0);
        findViewById(R.id.qna_mic).setVisibility(8);
        findViewById(R.id.qna_join_cancel).setVisibility(8);
        findViewById(R.id.qna_join_start_ask).setVisibility(8);
        findViewById(R.id.qna_join_start_ask).clearAnimation();
        findViewById(R.id.qna_join_hang_up).setVisibility(8);
        findViewById(R.id.qna_join_speaking).setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.txtBBOSD);
        if (textView.getAlpha() != BitmapDescriptorFactory.HUE_RED) {
            textView.animate().cancel();
            textView.animate().alpha(BitmapDescriptorFactory.HUE_RED).start();
        }
        View viewFindViewById = findViewById(R.id.qna_join_close);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.k0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12148b.m13609h4(view);
                }
            });
        }
    }

    /* renamed from: U4 */
    public final void m13684U4() {
        this.f11912i0.setText(getString(R.string.wbn_qna_host_invite_to_speak));
        this.f11913j0.setText(getString(R.string.wbn_qna_join_and_start_talk));
        final View viewFindViewById = findViewById(R.id.qna_join_start_ask);
        viewFindViewById.setVisibility(0);
        viewFindViewById.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.qna_pick_up));
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12138b.m13612i4(viewFindViewById, view);
            }
        });
    }

    /* renamed from: V4 */
    public final void m13685V4() {
        this.f11912i0.setText("");
        this.f11913j0.setText("");
        findViewById(R.id.qna_title).setVisibility(8);
        ImageView imageView = (ImageView) findViewById(R.id.qna_join_speaking);
        imageView.setVisibility(0);
        ((AnimationDrawable) imageView.getBackground()).start();
        final View viewFindViewById = findViewById(R.id.qna_join_hang_up);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.o0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12186b.m13618k4(viewFindViewById, view);
                }
            });
        }
    }

    /* renamed from: W4 */
    public final void m13686W4() {
        findViewById(R.id.qna_prepare_layout).setVisibility(8);
        findViewById(R.id.qna_join_layout).setVisibility(0);
        findViewById(R.id.fullscreen_btn).setEnabled(false);
        setRequestedOrientation(1);
        this.f11912i0.setText(getString(R.string.wbn_qna_in_waiting_list));
        this.f11913j0.setText(getString(R.string.wbn_qna_test_microphone));
        View viewFindViewById = findViewById(R.id.qna_join_cancel);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.g0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12129b.m13621l4(view);
                }
            });
        }
    }

    /* renamed from: X4 */
    public final void m13687X4(View view) throws NumberFormatException {
        PopupMenu popupMenu = new PopupMenu(this, view);
        Menu menu = popupMenu.getMenu();
        menu.add(0, 0, 0, "FLV");
        menu.add(0, 1, 0, "RTMP");
        for (int i9 = 0; i9 < this.f11882H.size(); i9++) {
            String str = this.f11882H.get(i9);
            try {
                menu.add(0, Integer.parseInt(str.substring(0, str.indexOf(TtmlNode.TAG_P))), 0, "FLV-" + str);
            } catch (NumberFormatException unused) {
                ULogUtility.m16670f("LiveAudienceActivity", "showQualityPopup NumberFormatException");
            }
        }
        menu.setGroupCheckable(0, true, true);
        menu.findItem(this.f11878F).setChecked(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.cyberlink.you.activity.webinar.b0
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return this.f12102a.m13624m4(menuItem);
            }
        });
        popupMenu.show();
    }

    /* renamed from: Y4 */
    public final void m13688Y4() {
        m13709j3();
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.wbn_qna_reach_limit));
        builderM16382a.setNegativeButton(getString(R.string.bc_dialog_button_close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.f0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                LiveAudienceActivity.m13627n4(dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    /* renamed from: Z4 */
    public final void m13689Z4() {
        if (this.f11932t0 == null) {
            this.f11932t0 = C3123g.m16385d(this, "", getString(R.string.clm_roll_call_end));
        }
        TextView textView = (TextView) this.f11932t0.findViewById(R.id.v_btn);
        textView.setText(R.string.close);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12155b.m13630o4(view);
            }
        });
        this.f11932t0.show();
    }

    /* renamed from: a3 */
    public final void m13690a3(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.a0
            @Override // java.lang.Runnable
            public final void run() {
                this.f12093b.m13510C3(z8);
            }
        });
    }

    /* renamed from: a5 */
    public final void m13691a5(String str) {
        Dialog dialog = this.f11930s0;
        if (dialog == null) {
            this.f11930s0 = C3123g.m16385d(this, "", str);
        } else {
            ((TextView) dialog.findViewById(R.id.dialogContent)).setText(str);
        }
        this.f11930s0.setCancelable(false);
        TextView textView = (TextView) this.f11930s0.findViewById(R.id.v_btn);
        textView.setText(R.string.continue_btn);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12225b.m13633p4(view);
            }
        });
        Dialog dialog2 = this.f11932t0;
        if (dialog2 != null && dialog2.isShowing()) {
            this.f11932t0.dismiss();
        }
        this.f11930s0.show();
    }

    /* renamed from: b3 */
    public final void m13692b3() {
        View viewFindViewById = findViewById(R.id.disable_chat_window);
        View viewFindViewById2 = findViewById(R.id.chat_window);
        EmojiconEditText emojiconEditText = (EmojiconEditText) findViewById(R.id.message_edit_text);
        boolean z8 = getResources().getConfiguration().orientation == 1;
        if (this.f11870B) {
            if (z8) {
                viewFindViewById2.setVisibility(0);
            }
            viewFindViewById.setVisibility(4);
            emojiconEditText.setEnabled(true);
            m13690a3(this.f11907d0);
        } else {
            if (z8) {
                viewFindViewById2.setVisibility(4);
            }
            viewFindViewById.setVisibility(0);
            emojiconEditText.setEnabled(false);
            emojiconEditText.setText("");
            emojiconEditText.setHint(R.string.ycl_edit_message_text_hint_enter_message);
            this.f11890M.m18292u();
        }
        m13695c3();
        C4604x.m18359s().m18367I(this.f11870B);
    }

    /* renamed from: b5 */
    public final void m13693b5(final int i9, final boolean z8) {
        m13732v3();
        SimpleMessageDialog simpleMessageDialogM24864k = new SimpleMessageDialog.C6499b(this, false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(R.string.bc_dialog_button_close), new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12132b.m13636q4(z8, i9, view);
            }
        }, true, SimpleMessageDialog.C6500c.f21850g)).m24870q(C4677a.m18710n().getString(R.string.bc_dialog_title_Notice), getResources().getColor(R.color.ycl_text_style_a)).m24869p(C4677a.m18710n().getString(i9), SimpleMessageDialog.C6500c.f21849f).m24864k();
        this.f11926q0 = simpleMessageDialogM24864k;
        simpleMessageDialogM24864k.show();
    }

    @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
    /* renamed from: c0 */
    public void mo13694c0(LivePlayer livePlayer) {
        if (livePlayer == this.f11921o) {
            m13731u4();
        }
    }

    /* renamed from: c3 */
    public final void m13695c3() {
        View viewFindViewById = findViewById(R.id.disable_message_bar_container);
        int i9 = 0;
        boolean z8 = this.f11911h0 && findViewById(R.id.qna_window).getVisibility() == 0;
        if (this.f11870B && !this.f11907d0 && !z8) {
            i9 = 8;
        }
        viewFindViewById.setVisibility(i9);
    }

    /* renamed from: c5 */
    public final void m13696c5(int i9) throws Resources.NotFoundException {
        View viewFindViewById;
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, i9 == 0 ? R.anim.bc_fade_in : R.anim.bc_fade_out);
        C4582b c4582b = this.f11888K;
        if (c4582b != null) {
            c4582b.m18235x(animationLoadAnimation);
            this.f11888K.m18234w(i9);
        }
        View viewFindViewById2 = findViewById(R.id.chat_window);
        if (viewFindViewById2 != null) {
            viewFindViewById2.startAnimation(animationLoadAnimation);
            viewFindViewById2.setVisibility(i9);
        }
        if (getResources().getConfiguration().orientation != 2 || (viewFindViewById = findViewById(R.id.restore_btn)) == null) {
            return;
        }
        viewFindViewById.startAnimation(animationLoadAnimation);
        viewFindViewById.setVisibility(i9);
    }

    /* renamed from: d3 */
    public final void m13697d3(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.r
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                this.f12204b.m13513D3(z8);
            }
        });
    }

    /* renamed from: d5 */
    public final void m13698d5() {
        if (!C4509d.m18120b(this.f11916l0)) {
            MeetingManager.m5631x(this.f11916l0);
        }
        m13709j3();
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.wbn_qna_update_to_latest));
        builderM16382a.setPositiveButton(getString(R.string.wbn_qna_update_now), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.m0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f12156b.m13639r4(dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(getString(R.string.bc_dialog_button_close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.n0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                LiveAudienceActivity.m13642s4(dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    /* renamed from: e3 */
    public final void m13699e3(Configuration configuration) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        View viewFindViewById = findViewById(R.id.top_bar);
        View viewFindViewById2 = findViewById(R.id.videoContainer);
        View viewFindViewById3 = findViewById(R.id.live_info_bar);
        View viewFindViewById4 = findViewById(R.id.LiveGroupLayout);
        View viewFindViewById5 = findViewById(R.id.restore_btn);
        View viewFindViewById6 = findViewById(R.id.chat_window);
        View viewFindViewById7 = findViewById(R.id.chat_container);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById2.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewFindViewById4.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById6.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) viewFindViewById7.getLayoutParams();
        int i9 = configuration.orientation;
        if (i9 == 1) {
            viewFindViewById.setVisibility(0);
            viewFindViewById3.setVisibility(0);
            viewFindViewById5.setVisibility(4);
            marginLayoutParams.width = -1;
            marginLayoutParams.height = (displayMetrics.widthPixels * 9) / 16;
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.topMargin = 0;
            marginLayoutParams2.bottomMargin = (int) (displayMetrics.density * 12.0f);
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.addRule(3, R.id.videoContainer);
            viewFindViewById6.setBackgroundColor(Color.argb(255, 255, 255, 255));
            viewFindViewById7.setBackgroundColor(Color.rgb(230, 230, 230));
            this.f11890M.m18291t(0);
            marginLayoutParams3.rightMargin = 0;
            getWindow().setSoftInputMode(16);
        } else if (i9 == 2) {
            viewFindViewById.setVisibility(8);
            viewFindViewById3.setVisibility(8);
            viewFindViewById5.setVisibility(0);
            int iMin = Math.min((displayMetrics.heightPixels * 16) / 9, displayMetrics.widthPixels);
            marginLayoutParams.width = iMin;
            marginLayoutParams.height = -1;
            marginLayoutParams.leftMargin = Math.max((displayMetrics.widthPixels - iMin) / 2, 0);
            marginLayoutParams.topMargin = Math.max((displayMetrics.heightPixels - ((marginLayoutParams.width * 9) / 16)) / 2, 0);
            marginLayoutParams2.bottomMargin = (int) (displayMetrics.density * 80.0f);
            layoutParams.width = (displayMetrics.widthPixels * 10) / 16;
            layoutParams.height = C4677a.m18702f(R.dimen.t75dp);
            layoutParams.removeRule(3);
            viewFindViewById6.setBackgroundColor(0);
            viewFindViewById7.setBackgroundColor(0);
            this.f11890M.m18291t(1);
            getWindow().setSoftInputMode(32);
        }
        viewFindViewById2.setLayoutParams(marginLayoutParams);
        viewFindViewById4.setLayoutParams(marginLayoutParams2);
        viewFindViewById6.setLayoutParams(layoutParams);
        viewFindViewById7.setLayoutParams(marginLayoutParams3);
    }

    /* renamed from: e5 */
    public final void m13700e5() {
        TextView textView = (TextView) findViewById(R.id.water_mark_text_view);
        if (textView == null || C4509d.m18120b(this.f11909f0) || C4509d.m18120b(this.f11910g0)) {
            return;
        }
        String[] strArrSplit = this.f11910g0.split("_");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        if (strArrSplit[0].equalsIgnoreCase("top")) {
            layoutParams.addRule(6, R.id.videoContainer);
        } else {
            layoutParams.addRule(8, R.id.videoContainer);
        }
        int iM18702f = C4677a.m18702f(R.dimen.t25dp);
        if (strArrSplit[1].equalsIgnoreCase(TtmlNode.LEFT)) {
            layoutParams.addRule(5, R.id.videoContainer);
            layoutParams.setMargins(0, 0, iM18702f, 0);
        } else {
            layoutParams.addRule(7, R.id.videoContainer);
            layoutParams.setMargins(iM18702f, 0, 0, 0);
        }
        textView.setLayoutParams(layoutParams);
        textView.setText(this.f11909f0);
        textView.setVisibility(0);
    }

    /* renamed from: f3 */
    public final void m13701f3() {
        m13712k5();
        C4677a.m18693T("reloading...");
        ULogUtility.m16670f("LiveAudienceActivity", "[cancelAndReload] reloading...");
        m13708i5();
        this.f11943z = true;
    }

    /* renamed from: f5 */
    public final void m13702f5() {
        ULogUtility.m16670f("LiveAudienceActivity", "shutdownWatching");
        m13712k5();
        C4604x.m18359s().m18372N();
    }

    /* renamed from: g3 */
    public final void m13703g3() {
        if (this.f11941y) {
            C4677a.m18684I(new Runnable() { // from class: com.cyberlink.you.activity.webinar.t
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12211b.m13516E3();
                }
            }, 500L);
        }
    }

    /* renamed from: g5 */
    public final void m13704g5() {
        if (C4509d.m18120b(this.f11916l0)) {
            return;
        }
        findViewById(R.id.QNA_icon).setSelected(true);
        C1260a.m5673k(this.f11916l0, "", this.f11914k0).m15439e(new C2618j((ImageView) findViewById(R.id.qna_mic)));
    }

    /* renamed from: h3 */
    public final void m13705h3() {
        if (this.f11941y) {
            C4677a.m18684I(new Runnable() { // from class: com.cyberlink.you.activity.webinar.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12213b.m13519F3();
                }
            }, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        }
    }

    /* renamed from: h5 */
    public final void m13706h5() {
        C2619k c2619k = new C2619k();
        RTCAudioManager rTCAudioManagerM5052d = RTCAudioManager.m5052d(getApplicationContext(), f11867M0);
        this.f11922o0 = rTCAudioManagerM5052d;
        rTCAudioManagerM5052d.m5067r(c2619k, 3);
    }

    /* renamed from: i3 */
    public final void m13707i3() {
        if (this.f11941y && this.f11868A) {
            if (!NetworkCommon.isNetWorkFast()) {
                ULogUtility.m16670f("LiveAudienceActivity", "checkRaiseQuality isNetWorkFast false");
                C4677a.m18685K(this.f11877E0);
                C4677a.m18684I(this.f11877E0, 60000L);
                return;
            }
            C4507b.m18108i("checkRaiseQuality");
            m13713l3().m18185q(this.f11917m, 500L);
            m13713l3().m18187s(false);
            m13713l3().m18182n();
            this.f11893P = System.currentTimeMillis();
            this.f11894Q = 0;
            this.f11896S = (this.f11921o.m18176h() - this.f11921o.m18177i()) / 1000.0d;
            ULogUtility.m16670f("LiveAudienceActivity", "current bufferTime : " + this.f11896S);
            C4507b.m18108i("current bufferTime : " + this.f11896S);
            m13703g3();
        }
    }

    /* renamed from: i5 */
    public final void m13708i5() {
        int i9;
        if (this.f11915l == null) {
            return;
        }
        if (this.f11884I >= 0) {
            this.f11897T = 0;
            C4677a.m18685K(this.f11877E0);
            C4677a.m18684I(this.f11877E0, 60000L);
            i9 = 2000;
        } else {
            i9 = ServiceStarter.ERROR_UNKNOWN;
        }
        this.f11921o.m18185q(this.f11915l, i9);
        C4507b.m18101b("start Watching : " + this.f11915l);
        ULogUtility.m16670f("LiveAudienceActivity", "[startWatching] contentUri:" + this.f11915l);
        ULogUtility.m16670f("LiveAudienceActivity", "[startWatching] minBufferMs:" + i9);
        C4677a.m18693T("start Watching : " + this.f11915l);
        if (this.f11908e0) {
            m13700e5();
        }
        this.f11939x = 0;
        this.f11880G.clear();
        this.f11933u = 0L;
        m13733v4();
    }

    /* renamed from: j3 */
    public final void m13709j3() {
        findViewById(R.id.qna_window).setVisibility(8);
        findViewById(R.id.qna_prepare_layout).setVisibility(0);
        findViewById(R.id.qna_join_layout).setVisibility(8);
        findViewById(R.id.qna_mic).setVisibility(0);
        findViewById(R.id.qna_join_cancel).setVisibility(0);
        findViewById(R.id.qna_join_start_ask).setVisibility(8);
        findViewById(R.id.qna_join_start_ask).clearAnimation();
        findViewById(R.id.qna_join_hang_up).setVisibility(8);
        findViewById(R.id.qna_join_close).setVisibility(8);
        RTCAudioManager rTCAudioManager = this.f11922o0;
        if (rTCAudioManager != null) {
            rTCAudioManager.m5068s();
            this.f11922o0 = null;
        }
        this.f11912i0.setText(getString(R.string.wbn_qna_host_start_session));
        this.f11912i0.setVisibility(0);
        this.f11913j0.setText(getString(R.string.wbn_qna_ask_question_reminder));
        findViewById(R.id.fullscreen_btn).setEnabled(true);
        C4677a.m18684I(this.f11869A0, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        if (this.f11911h0) {
            findViewById(R.id.QNA_icon).setSelected(false);
            m13695c3();
        }
    }

    /* renamed from: j5 */
    public final void m13710j5() {
        C4677a.m18685K(this.f11879F0);
    }

    /* renamed from: k3 */
    public final void m13711k3() {
        Date date = new Date();
        Date date2 = this.f11874D;
        if (date2 == null) {
            Date date3 = this.f11876E;
            if (date3 != null) {
                long time = date3.getTime() - date.getTime();
                if (time <= 0) {
                    m13681R4(R.string.wbn_presenter_is_offline);
                    return;
                }
                long j9 = time / 1000;
                m13682S4(true, String.format(getResources().getString(R.string.wbn_paused_live_start_on), String.format(Locale.getDefault(), "%02d:%02d", Long.valueOf(j9 / 60), Long.valueOf(j9 % 60))));
                m13735w4();
                return;
            }
            return;
        }
        long time2 = date2.getTime() - date.getTime();
        if (time2 <= 0) {
            m13681R4(R.string.ycl_scheduled_live_not_yet_start);
            return;
        }
        String str = String.format(getResources().getString(R.string.plk_scheduled_live_start_on), DateFormat.getDateTimeInstance(2, 3).format(this.f11874D));
        if (time2 / DateUtils.MILLIS_PER_HOUR < 1) {
            long j10 = time2 / 1000;
            str = str + "\n" + String.format(getResources().getString(R.string.plk_scheduled_live_count_down), String.format(Locale.getDefault(), "%02d:%02d", Long.valueOf(j10 / 60), Long.valueOf(j10 % 60)));
        }
        m13682S4(true, str);
        m13735w4();
    }

    /* renamed from: k5 */
    public final void m13712k5() {
        ULogUtility.m16670f("LiveAudienceActivity", "stopWatching");
        if (this.f11921o.m18183o()) {
            this.f11921o.m18186r(false);
        }
        if (m13713l3().m18183o()) {
            m13713l3().m18186r(false);
        }
        this.f11943z = false;
    }

    /* renamed from: l3 */
    public final LivePlayer m13713l3() {
        LivePlayer livePlayer = this.f11921o;
        LivePlayer livePlayer2 = this.f11891N;
        return livePlayer == livePlayer2 ? this.f11892O : livePlayer2;
    }

    /* renamed from: l5 */
    public final void m13714l5() {
        m13678O4(!this.f11923p);
    }

    /* renamed from: m3 */
    public final Uri m13715m3(String str) {
        String string = this.f11917m.toString();
        String strSubstring = string.substring(string.lastIndexOf("."));
        return Uri.parse(string.replace(strSubstring, "-" + str + strSubstring));
    }

    /* renamed from: m5 */
    public final void m13716m5() throws Resources.NotFoundException {
        if (this.f11889L.getVisibility() != 0) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, R.anim.bc_slide_up);
            animationLoadAnimation.setAnimationListener(new AnimationAnimationListenerC2623o());
            this.f11889L.startAnimation(animationLoadAnimation);
            this.f11889L.setVisibility(0);
            return;
        }
        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.bc_slide_down);
        animationLoadAnimation2.setAnimationListener(new AnimationAnimationListenerC2624p());
        this.f11889L.startAnimation(animationLoadAnimation2);
        this.f11889L.setVisibility(4);
    }

    @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
    /* renamed from: n */
    public void mo13717n(int i9, LivePlayer livePlayer) {
        if (livePlayer != this.f11921o) {
            return;
        }
        ULogUtility.m16670f("LiveAudienceActivity", "[onStateChange] newState:" + i9);
        int i10 = this.f11931t;
        if (i10 == 3 && i9 == 2) {
            C4507b.m18101b("Buffering start, mTotalBufferingDurationMs:" + this.f11933u);
            C5468b.m21111a().m21112b(f11865K0);
            if (this.f11880G.size() >= 3) {
                this.f11880G.remove(0);
            }
            this.f11880G.add(Long.valueOf(SystemClock.elapsedRealtime()));
        } else if (i10 == 2 && i9 == 3) {
            long jM21113c = C5468b.m21111a().m21113c(f11865K0, TimeUnit.MILLISECONDS);
            long j9 = this.f11933u;
            if (jM21113c == -1) {
                jM21113c = 0;
            }
            this.f11933u = j9 + jM21113c;
            C4507b.m18101b("Buffering end, mTotalBufferingDurationMs:" + this.f11933u);
            this.f11939x = 0;
        }
        this.f11931t = i9;
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.w
            @Override // java.lang.Runnable
            public final void run() {
                this.f12218b.m13569V3();
            }
        });
        if (this.f11933u > DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
            this.f11933u = 0L;
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.x
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12221b.m13573W3();
                }
            });
        }
    }

    /* renamed from: n3 */
    public final long m13718n3() {
        String stringExtra = getIntent().getStringExtra("liveId");
        try {
            return Long.parseLong(stringExtra);
        } catch (Exception unused) {
            ULogUtility.m16670f("LiveAudienceActivity", "parse liveId failed, liveId:" + stringExtra);
            C4507b.m18101b("parse liveId failed, liveId:" + stringExtra);
            m13679P4(R.string.ycl_live_not_exist);
            return -1L;
        }
    }

    /* renamed from: n5 */
    public final void m13719n5() throws Resources.NotFoundException {
        C4677a.m18685K(this.f11873C0);
        C4582b c4582b = this.f11888K;
        if (c4582b != null) {
            m13696c5(c4582b.m18232k() ? 4 : 0);
        }
    }

    /* renamed from: o3 */
    public final void m13720o3() {
        finish();
    }

    /* renamed from: o5 */
    public final void m13721o5() {
        this.f11886J.m18253r(this.f11921o.m18181m(2), this.f11921o.m18181m(1), this.f11921o.m18181m(3));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        findViewById(R.id.root).scrollTo(0, 0);
        CLUtility.m16589t1(this);
        m13699e3(configuration);
        C4677a.m18685K(this.f11873C0);
        C4677a.m18685K(this.f11875D0);
        if (configuration.orientation == 1) {
            C4677a.m18684I(this.f11875D0, 250L);
        } else {
            C4677a.m18684I(this.f11873C0, 250L);
        }
        m13727r5(configuration);
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalAccessException, Resources.NotFoundException, SecurityException, IllegalArgumentException {
        super.onCreate(bundle);
        ULogUtility.m16670f("LiveAudienceActivity", "onCreate");
        NetworkCommon.registerNetworkChange(getApplicationContext(), this.f11881G0);
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            getResources().getDimensionPixelSize(identifier);
        }
        setContentView(R.layout.activity_live_audience);
        View viewFindViewById = findViewById(R.id.root);
        viewFindViewById.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.webinar.d
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f12114b.m13549P3(view, motionEvent);
            }
        });
        View viewFindViewById2 = findViewById(R.id.top_bar);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12117b.m13552Q3(view);
                }
            });
        }
        findViewById(R.id.fullscreen_btn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12126b.m13555R3(view);
            }
        });
        findViewById(R.id.restore_btn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12128b.m13558S3(view);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) viewFindViewById.findViewById(R.id.videoContainer);
        if (relativeLayout != null) {
            relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.webinar.h
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.f12131b.m13561T3(view, motionEvent);
                }
            });
        }
        findViewById(R.id.people_icon).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NumberFormatException {
                this.f12137b.m13540M3(view);
            }
        });
        View viewFindViewById3 = findViewById(R.id.QNA_icon);
        viewFindViewById3.setEnabled(false);
        viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12142b.m13543N3(view);
            }
        });
        findViewById(R.id.chat_container).setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.webinar.k
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f12147b.m13546O3(view, motionEvent);
            }
        });
        LivePlayer livePlayerM18167g = LivePlayer.m18167g(this, (AspectRatioFrameLayout) findViewById(R.id.prime_video_frame), (TextureView) findViewById(R.id.prime_texture_view), this);
        this.f11891N = livePlayerM18167g;
        livePlayerM18167g.m18191w(findViewById(R.id.status_view));
        this.f11921o = this.f11891N;
        LivePlayer livePlayerM18167g2 = LivePlayer.m18167g(this, (AspectRatioFrameLayout) findViewById(R.id.secondary_video_frame), (TextureView) findViewById(R.id.secondary_texture_view), this);
        this.f11892O = livePlayerM18167g2;
        livePlayerM18167g2.m18191w(findViewById(R.id.status_view));
        CookieHandler cookieHandler = CookieHandler.getDefault();
        CookieManager cookieManager = f11866L0;
        if (cookieHandler != cookieManager) {
            CookieHandler.setDefault(cookieManager);
        }
        m13738y3();
        m13658A3(getIntent());
        m13705h3();
        m13699e3(getResources().getConfiguration());
        if (getResources().getConfiguration().orientation == 2) {
            m13696c5(4);
        }
        m13672L4();
        C4604x.m18359s().m18364F();
        this.f11936v0 = findViewById(R.id.extraPage);
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() throws SecurityException {
        super.onDestroy();
        ULogUtility.m16670f("LiveAudienceActivity", "onDestroy");
        if (!C4509d.m18120b(this.f11916l0)) {
            MeetingManager.m5631x(this.f11916l0);
        }
        RTCAudioManager rTCAudioManager = this.f11922o0;
        if (rTCAudioManager != null) {
            rTCAudioManager.m5068s();
            this.f11922o0 = null;
        }
        NetworkCommon.unregisterNetworkChange(getApplicationContext(), this.f11881G0);
        m13702f5();
        this.f11941y = false;
        m13710j5();
        C4604x.m18359s().m18376q();
        Runnable runnable = this.f11940x0;
        if (runnable != null) {
            C4677a.m18685K(runnable);
        }
        SimpleMessageDialog simpleMessageDialog = this.f11942y0;
        if (simpleMessageDialog == null || !simpleMessageDialog.isShowing()) {
            return;
        }
        this.f11942y0.dismiss();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        ULogUtility.m16670f("LiveAudienceActivity", "[onNewIntent]");
        m13712k5();
        setIntent(intent);
        m13658A3(getIntent());
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        ULogUtility.m16670f("LiveAudienceActivity", "onStop");
        super.onPause();
        if (Util.SDK_INT <= 23) {
            m13739y4();
        }
        this.f11902Y = false;
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ULogUtility.m16670f("LiveAudienceActivity", "onResume");
        if (Util.SDK_INT <= 23 || this.f11921o == null) {
            m13741z4();
        }
        this.f11902Y = true;
        m13722p3();
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        ULogUtility.m16670f("LiveAudienceActivity", "onStart");
        if (Util.SDK_INT > 23) {
            m13741z4();
        }
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        ULogUtility.m16670f("LiveAudienceActivity", "onStop");
        if (Util.SDK_INT > 23) {
            m13739y4();
        }
    }

    /* renamed from: p3 */
    public final void m13722p3() {
        Dialog dialog;
        Dialog dialog2;
        for (JSONObject jSONObject : this.f11938w0) {
            String strOptString = jSONObject.optString("event");
            if (RollCallEvent.START.name().equals(strOptString)) {
                long jOptLong = jSONObject.optLong("endTime");
                String strOptString2 = jSONObject.optString("message");
                long jCurrentTimeMillis = jOptLong - System.currentTimeMillis();
                if (jCurrentTimeMillis > 0) {
                    m13691a5(strOptString2);
                    C4677a.m18684I(this.f11940x0, jCurrentTimeMillis);
                }
            } else if (RollCallEvent.START_NO_LIMIT.name().equals(strOptString)) {
                m13691a5(jSONObject.optString("message"));
            } else if (RollCallEvent.UPDATE.name().equals(strOptString)) {
                long jOptLong2 = jSONObject.optLong("endTime");
                String strOptString3 = jSONObject.optString("message");
                long jCurrentTimeMillis2 = jOptLong2 - System.currentTimeMillis();
                if (jCurrentTimeMillis2 > 0 && (dialog = this.f11930s0) != null && dialog.isShowing()) {
                    m13691a5(strOptString3);
                    C4677a.m18685K(this.f11940x0);
                    C4677a.m18684I(this.f11940x0, jCurrentTimeMillis2);
                }
            } else if (RollCallEvent.STOP.name().equals(strOptString) && (dialog2 = this.f11930s0) != null && dialog2.isShowing()) {
                this.f11930s0.dismiss();
                m13689Z4();
            }
        }
        this.f11938w0.clear();
    }

    /* renamed from: p5 */
    public final void m13723p5() {
        View viewFindViewById = findViewById(R.id.loading_cursor);
        if (!this.f11868A || this.f11931t != 2) {
            viewFindViewById.setVisibility(8);
        } else {
            viewFindViewById.setVisibility(0);
            viewFindViewById.bringToFront();
        }
    }

    /* renamed from: q3 */
    public final void m13724q3(C5366d.a aVar) {
        if (aVar == null || C4509d.m18120b(aVar.messageId)) {
            return;
        }
        this.f11890M.m18296y(aVar.messageId, (C4509d.m18120b(aVar.userId) || aVar.userId.equalsIgnoreCase("0")) ? aVar.uuid : aVar.userId);
    }

    /* renamed from: q5 */
    public final void m13725q5() {
        NetworkLive.getLive(this.f11914k0, NetworkLive.EMPTY_PARAMETER.longValue(), this.f11927r, this.f11872C).done(new C2611c());
    }

    /* renamed from: r3 */
    public final void m13726r3(long j9, int i9) throws JSONException {
        if (C4509d.m18120b(this.f11906c0) || this.f11925q.f16183a == Long.parseLong(this.f11906c0)) {
            return;
        }
        this.f11934u0 = j9;
        String str = this.f11925q.f16184b;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z8 = i9 == Integer.MAX_VALUE;
        long j10 = this.f11934u0 + (i9 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        String string = z8 ? getString(R.string.clm_roll_call_start_no_limit, str) : getString(R.string.clm_roll_call_start, str, Integer.valueOf(i9 / 60), CLUtility.m16454J2(new Date(j10)));
        if (this.f11940x0 == null) {
            this.f11940x0 = new Runnable() { // from class: com.cyberlink.you.activity.webinar.l0
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    this.f12152b.m13522G3();
                }
            };
        }
        if (this.f11902Y) {
            m13691a5(string);
            if (z8) {
                return;
            }
            C4677a.m18684I(this.f11940x0, j10 - System.currentTimeMillis());
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (z8) {
                jSONObject.put("event", RollCallEvent.START_NO_LIMIT.name());
            } else {
                jSONObject.put("event", RollCallEvent.START.name());
                jSONObject.put("endTime", j10);
            }
            jSONObject.put("message", string);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        this.f11938w0.add(jSONObject);
    }

    /* renamed from: r5 */
    public final void m13727r5(Configuration configuration) {
        getWindow().getDecorView().setSystemUiVisibility((configuration == null ? 1 : configuration.orientation) == 2 ? 4102 : 0);
    }

    /* renamed from: s3 */
    public final void m13728s3() throws JSONException {
        if (C4509d.m18120b(this.f11906c0) || this.f11925q.f16183a == Long.parseLong(this.f11906c0)) {
            return;
        }
        C4677a.m18685K(this.f11940x0);
        if (!this.f11902Y) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", RollCallEvent.STOP.name());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            this.f11938w0.add(jSONObject);
            return;
        }
        Dialog dialog = this.f11930s0;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f11930s0.dismiss();
        m13689Z4();
    }

    /* renamed from: t3 */
    public final void m13729t3(int i9) throws JSONException {
        if (C4509d.m18120b(this.f11906c0) || this.f11925q.f16183a == Long.parseLong(this.f11906c0)) {
            return;
        }
        long j9 = this.f11934u0 + (i9 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        String string = getString(R.string.clm_roll_call_start, this.f11925q.f16184b, Integer.valueOf(i9 / 60), CLUtility.m16454J2(new Date(j9)));
        C4677a.m18685K(this.f11940x0);
        if (this.f11902Y) {
            Dialog dialog = this.f11930s0;
            if (dialog == null || !dialog.isShowing()) {
                return;
            }
            m13691a5(string);
            C4677a.m18684I(this.f11940x0, j9 - System.currentTimeMillis());
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", RollCallEvent.UPDATE.name());
            jSONObject.put("endTime", j9);
            jSONObject.put("message", string);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        this.f11938w0.add(jSONObject);
    }

    /* renamed from: u3 */
    public final void m13730u3(C5366d.a aVar) {
        if (aVar == null || C4509d.m18120b(aVar.messageId)) {
            return;
        }
        this.f11890M.m18293v(aVar.messageId, (C4509d.m18120b(aVar.userId) || aVar.userId.equalsIgnoreCase("0")) ? aVar.uuid : aVar.userId);
    }

    /* renamed from: u4 */
    public final void m13731u4() {
        if (this.f11884I < this.f11882H.size() - 1) {
            int i9 = this.f11884I + 1;
            this.f11884I = i9;
            this.f11878F = i9 + 2;
            this.f11915l = m13715m3(this.f11882H.get(i9));
            ULogUtility.m16670f("LiveAudienceActivity", "[lowerDownQuality] contentUri : " + this.f11915l);
            C4507b.m18108i("[lowerDownQuality] contentUri : " + this.f11915l);
            m13701f3();
        }
    }

    /* renamed from: v3 */
    public final void m13732v3() {
        SimpleMessageDialog simpleMessageDialog = this.f11926q0;
        if (simpleMessageDialog == null || !simpleMessageDialog.isShowing()) {
            return;
        }
        this.f11926q0.dismiss();
        this.f11926q0 = null;
    }

    /* renamed from: v4 */
    public final void m13733v4() {
        if (C4509d.m18120b(this.f11916l0) || MeetingManager.m5615h(this.f11916l0) != MeetingManager.MeetingStatus.IN_MEETING || this.f11921o == null) {
            return;
        }
        C4507b.m18101b("muteWhenQNA");
        this.f11921o.m18184p();
    }

    /* renamed from: w3 */
    public final void m13734w3() throws Resources.NotFoundException {
        if (this.f11888K.m18231j()) {
            mo13673M0();
        }
    }

    /* renamed from: w4 */
    public final void m13735w4() {
        C4677a.m18684I(this.f11879F0, 500L);
    }

    /* renamed from: x3 */
    public final void m13736x3() {
        if (this.f11908e0) {
            ((TextView) findViewById(R.id.water_mark_text_view)).setVisibility(8);
        }
    }

    /* renamed from: x4 */
    public final void m13737x4(RTCAudioManager.AudioDevice audioDevice, Set<RTCAudioManager.AudioDevice> set) {
        if (audioDevice == RTCAudioManager.AudioDevice.WIRED_HEADSET || audioDevice == RTCAudioManager.AudioDevice.BLUETOOTH) {
            this.f11922o0.m5066q(false);
        } else {
            this.f11922o0.m5066q(true);
        }
        NileNetwork nileNetwork = this.f11924p0;
        if (nileNetwork != null) {
            nileNetwork.m4904J7(audioDevice);
        }
    }

    /* renamed from: y3 */
    public final void m13738y3() {
        this.f11889L = findViewById(R.id.emoji_fragment);
        C4583c c4583c = new C4583c();
        c4583c.m18207i(new C4583c.d() { // from class: com.cyberlink.you.activity.webinar.s
            @Override // com.perfectcorp.ycl.pages.live.C4583c.d
            /* renamed from: a */
            public final void mo13973a(Emojicon emojicon) {
                this.f12208a.m13525H3(emojicon);
            }
        });
        getSupportFragmentManager().mo1844a().m1980b(R.id.emoji_fragment, c4583c).mo1793g();
    }

    /* renamed from: y4 */
    public final void m13739y4() {
        ULogUtility.m16670f("LiveAudienceActivity", "onHidden");
        if (this.f11921o.m18183o()) {
            m13712k5();
            C4604x.m18359s().m18363E();
            C4604x.m18359s().m18366H(true);
        }
    }

    /* renamed from: z3 */
    public final void m13740z3() {
        C4604x.m18359s().m18368J(this.f11890M);
    }

    /* renamed from: z4 */
    public final void m13741z4() {
        LivePlayer livePlayer = this.f11921o;
        if (livePlayer == null || !livePlayer.m18183o()) {
            Intent intent = getIntent();
            this.f11914k0 = intent.getStringExtra("token");
            if (intent.hasExtra("waterMark")) {
                this.f11905b0 = intent.getStringExtra("waterMark");
            }
            this.f11872C = intent.getBooleanExtra("isEnterprise", false);
            long longExtra = intent.getLongExtra("userId", 0L);
            if (longExtra > 0) {
                String strValueOf = String.valueOf(longExtra);
                this.f11906c0 = strValueOf;
                this.f11890M.m18282L(strValueOf);
            }
            this.f11898U = intent.getStringExtra("corporate");
            this.f11899V = intent.getStringExtra("department");
            this.f11900W = intent.getStringExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
            String string = Long.toString(m13718n3());
            if (C4509d.m18120b(string)) {
                return;
            }
            this.f11929s = intent.getStringExtra("registryKey");
            if (!string.equals(this.f11927r)) {
                this.f11890M.m18292u();
            }
            if (intent.hasExtra("assistantStatus")) {
                String stringExtra = intent.getStringExtra("assistantStatus");
                ULogUtility.m16680p("LiveAudienceActivity", "[onShown] assistant status : " + stringExtra);
                if (Arrays.asList("CANDIDATE", "ASSISTANT").contains(stringExtra)) {
                    if ("ASSISTANT".equals(stringExtra)) {
                        this.f11890M.m18289r(this.f11906c0);
                    }
                    m13674M4();
                }
            }
            this.f11927r = string;
            if (!C4509d.m18120b(this.f11929s)) {
                string = this.f11929s;
            }
            m13666F4(string, this.f11914k0, this.f11872C);
            m13671K4(this.f11929s);
            C4604x.m18359s().m18366H(false);
        }
    }
}
