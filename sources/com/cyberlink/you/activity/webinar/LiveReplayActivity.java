package com.cyberlink.you.activity.webinar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.google.android.exoplayer2.p038ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.Util;
import com.perfectcorp.utility.C4509d;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.BaseActivity;
import com.perfectcorp.ycl.commons.utility.Log;
import com.perfectcorp.ycl.p040bc.model.Live;
import com.perfectcorp.ycl.p040bc.model.network.NetworkCommon;
import com.perfectcorp.ycl.p040bc.model.network.NetworkLive;
import com.perfectcorp.ycl.pages.live.AbstractC4591k;
import com.perfectcorp.ycl.pages.live.C4600t;
import com.perfectcorp.ycl.pages.live.InterfaceC4605y;
import com.perfectcorp.ycl.pages.live.LivePlayer;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import p047d5.C4677a;
import p156o5.C5468b;
import p224w.dialogs.SimpleMessageDialog;

/* loaded from: classes.dex */
public class LiveReplayActivity extends BaseActivity implements LivePlayer.InterfaceC4580c {

    /* renamed from: D */
    public static final int f11992D = new Random().nextInt();

    /* renamed from: E */
    public static final CookieManager f11993E;

    /* renamed from: A */
    public C2633f f11994A;

    /* renamed from: l */
    public Uri f11997l;

    /* renamed from: m */
    public LivePlayer f11998m;

    /* renamed from: n */
    public boolean f11999n;

    /* renamed from: o */
    public C4600t f12000o;

    /* renamed from: p */
    public String f12001p;

    /* renamed from: q */
    public String f12002q;

    /* renamed from: s */
    public long f12004s;

    /* renamed from: r */
    public int f12003r = 1;

    /* renamed from: t */
    public boolean f12005t = false;

    /* renamed from: u */
    public int f12006u = 0;

    /* renamed from: v */
    public boolean f12007v = true;

    /* renamed from: w */
    public boolean f12008w = false;

    /* renamed from: x */
    public boolean f12009x = false;

    /* renamed from: y */
    public int f12010y = 0;

    /* renamed from: z */
    public float f12011z = 1.0f;

    /* renamed from: B */
    public boolean f11995B = true;

    /* renamed from: C */
    public Runnable f11996C = new Runnable() { // from class: com.cyberlink.you.activity.webinar.p1
        @Override // java.lang.Runnable
        public final void run() throws Resources.NotFoundException {
            this.f12199b.m13833y1();
        }
    };

    /* renamed from: com.cyberlink.you.activity.webinar.LiveReplayActivity$a */
    public class C2628a implements SeekBar.OnSeekBarChangeListener {
        public C2628a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i9, boolean z8) throws Resources.NotFoundException {
            if (z8) {
                LiveReplayActivity.this.m13818V1();
                long jM18178j = (LiveReplayActivity.this.f11998m.m18178j() * i9) / seekBar.getMax();
                TextView textView = (TextView) LiveReplayActivity.this.findViewById(R.id.current_time);
                if (textView != null) {
                    long j9 = jM18178j / 1000;
                    long j10 = j9 / 60;
                    textView.setText(String.format("%02d:%02d:%02d", Long.valueOf(j10 / 60), Long.valueOf(j10 % 60), Long.valueOf(j9 % 60)));
                }
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) throws Resources.NotFoundException {
            LiveReplayActivity.this.m13818V1();
            LiveReplayActivity.this.f12008w = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) throws Resources.NotFoundException {
            LiveReplayActivity.this.m13833y1();
            if (LiveReplayActivity.this.f11998m != null) {
                LiveReplayActivity.this.f11998m.m18174e((LiveReplayActivity.this.f11998m.m18178j() * seekBar.getProgress()) / seekBar.getMax());
            }
            LiveReplayActivity.this.f12008w = false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveReplayActivity$b */
    public class C2629b extends PromisedTask.AbstractC4504d {
        public C2629b() {
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        public void onDone(Object obj) {
            Log.m18147a("LiveReplayActivity", "addWatchHistory success | liveId = " + LiveReplayActivity.this.f12002q);
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            Log.m18149c("LiveReplayActivity", "addWatchHistory fail | liveId = " + LiveReplayActivity.this.f12002q + " error = " + i9);
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveReplayActivity$c */
    public class AnimationAnimationListenerC2630c implements Animation.AnimationListener {
        public AnimationAnimationListenerC2630c() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (LiveReplayActivity.this.f11998m == null || !LiveReplayActivity.this.f11998m.m18180l()) {
                return;
            }
            C4677a.m18684I(LiveReplayActivity.this.f11996C, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveReplayActivity$d */
    public class AnimationAnimationListenerC2631d implements Animation.AnimationListener {
        public AnimationAnimationListenerC2631d() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveReplayActivity$e */
    public class C2632e extends PromisedTask.AbstractC4504d<Live> {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f12016a;

        public C2632e(DialogC3133q dialogC3133q) {
            this.f12016a = dialogC3133q;
        }

        @Override // com.perfectcorp.utility.PromisedTask.AbstractC4504d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onDone(Live live) {
            C4677a.m18693T("queryLive");
            this.f12016a.dismiss();
            LiveReplayActivity.this.m13814R1(live);
            Intent intent = LiveReplayActivity.this.getIntent();
            LiveReplayActivity.this.f11997l = intent.getData();
            LiveReplayActivity.this.m13821Y1();
        }

        @Override // com.perfectcorp.utility.PromisedTask
        public void onError(int i9) {
            super.onError(i9);
            this.f12016a.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.webinar.LiveReplayActivity$f */
    public class C2633f extends AbstractC4591k {
        public C2633f(Context context, View view, InterfaceC4605y interfaceC4605y) {
            super(context, view, interfaceC4605y);
        }

        @Override // com.perfectcorp.ycl.pages.live.AbstractC4591k
        /* renamed from: m */
        public boolean mo13771m(MenuItem menuItem, int i9) {
            return LiveReplayActivity.this.m13813Q1(menuItem, i9);
        }
    }

    static {
        CookieManager cookieManager = new CookieManager();
        f11993E = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A1 */
    public /* synthetic */ void m13772A1() {
        SeekBar seekBar;
        TextView textView;
        if (this.f12007v) {
            long jM18177i = this.f11998m.m18177i();
            long jM18178j = this.f11998m.m18178j();
            if (!this.f12008w && (textView = (TextView) findViewById(R.id.current_time)) != null) {
                long j9 = jM18177i / 1000;
                long j10 = j9 / 60;
                textView.setText(String.format("%02d:%02d:%02d", Long.valueOf(j10 / 60), Long.valueOf(j10 % 60), Long.valueOf(j9 % 60)));
            }
            TextView textView2 = (TextView) findViewById(R.id.total_time);
            if (textView2 != null) {
                long j11 = jM18178j / 1000;
                long j12 = j11 / 60;
                textView2.setText(String.format("%02d:%02d:%02d", Long.valueOf(j12 / 60), Long.valueOf(j12 % 60), Long.valueOf(j11 % 60)));
            }
            if (!this.f12008w && (seekBar = (SeekBar) findViewById(R.id.seekBar)) != null) {
                seekBar.setProgress(jM18178j > 0 ? (int) ((seekBar.getMax() * jM18177i) / jM18178j) : 0);
            }
            m13831w1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B1 */
    public /* synthetic */ void m13773B1() {
        if (this.f12007v) {
            if (this.f12003r != 3) {
                int i9 = this.f12006u + 1;
                this.f12006u = i9;
                if (i9 > 3) {
                    this.f12006u = 0;
                    C4677a.m18693T("Player not start playing too long...");
                    m13829u1();
                }
            }
            m13832x1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C1 */
    public /* synthetic */ boolean m13774C1(View view, MotionEvent motionEvent) throws Resources.NotFoundException {
        if (motionEvent.getAction() == 0) {
            m13824b2();
            return false;
        }
        if (motionEvent.getAction() != 1) {
            return false;
        }
        view.performClick();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D1 */
    public /* synthetic */ void m13775D1(View view) {
        if (C4677a.m18713q("SecretTrick", false)) {
            m13823a2();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E1 */
    public /* synthetic */ void m13776E1(View view) {
        if (this.f11998m != null) {
            this.f11998m.m18173d(!r2.m18180l());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F1 */
    public /* synthetic */ void m13777F1(View view) {
        setRequestedOrientation(6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G1 */
    public /* synthetic */ void m13778G1() {
        setRequestedOrientation(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H1 */
    public /* synthetic */ void m13779H1(View view) {
        setRequestedOrientation(7);
        view.postDelayed(new Runnable() { // from class: com.cyberlink.you.activity.webinar.g1
            @Override // java.lang.Runnable
            public final void run() {
                this.f12130b.m13778G1();
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I1 */
    public /* synthetic */ boolean m13780I1(View view, MotionEvent motionEvent) throws Resources.NotFoundException {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        m13824b2();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J1 */
    public /* synthetic */ void m13781J1() {
        m13822Z1(true);
        if (NetworkCommon.isNetworkConnected()) {
            m13821Y1();
        } else {
            m13817U1(R.string.ycl_no_network_fail);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K1 */
    public /* synthetic */ void m13782K1() throws Resources.NotFoundException {
        m13826d2();
        m13827e2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L1 */
    public /* synthetic */ void m13783L1(Dialog dialog, CompoundButton compoundButton, boolean z8) {
        if (z8) {
            m13830v1(Float.parseFloat((String) compoundButton.getTag()));
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M1 */
    public /* synthetic */ void m13784M1(View view) {
        final Dialog dialog = new Dialog(this, R.style.DialogBaseTheme);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.cyberlink.you.activity.webinar.j1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z8) {
                this.f12144a.m13783L1(dialog, compoundButton, z8);
            }
        };
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_replay_setting, (ViewGroup) null);
        ViewGroup viewGroup = (ViewGroup) viewInflate.findViewById(R.id.radioGroupPlaybackRate);
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = viewGroup.getChildAt(i9);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                try {
                    radioButton.setChecked(this.f12011z == Float.parseFloat((String) radioButton.getTag()));
                } catch (NumberFormatException unused) {
                }
                radioButton.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }
        dialog.setContentView(viewInflate);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N1 */
    public /* synthetic */ void m13785N1(View view) {
        this.f11995B = true;
        finish();
    }

    @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
    /* renamed from: D0 */
    public void mo13663D0(IOException iOException, int i9, LivePlayer livePlayer) {
        C4677a.m18693T("" + iOException.getClass().getSimpleName() + ", onLoadError:" + i9);
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.s1
            @Override // java.lang.Runnable
            public final void run() {
                this.f12210b.m13781J1();
            }
        });
    }

    @Override // com.perfectcorp.ycl.BaseActivity
    /* renamed from: N0 */
    public boolean mo13675N0() {
        finish();
        return true;
    }

    @Override // com.perfectcorp.ycl.BaseActivity
    /* renamed from: O0 */
    public void mo13677O0() {
        m13822Z1(true);
    }

    /* renamed from: O1 */
    public final void m13811O1() {
        if (this.f11998m.m18183o()) {
            m13822Z1(true);
        }
    }

    /* renamed from: P1 */
    public final void m13812P1() {
        NetworkLive.getLive(this.f12001p, 0L, this.f12002q, this.f12009x).done(new C2632e(new DialogC3133q.b(this).m16411b()));
    }

    /* renamed from: Q1 */
    public final boolean m13813Q1(MenuItem menuItem, int i9) {
        if (this.f11998m == null || menuItem.getGroupId() != 1) {
            return false;
        }
        this.f11998m.m18190v(i9, menuItem.getItemId() - 2);
        return true;
    }

    /* renamed from: R1 */
    public final void m13814R1(Live live) {
        if (live == null || !live.watermarkEnabled.booleanValue()) {
            return;
        }
        Live.WaterMark waterMark = live.watermark;
        m13819W1(waterMark.text, waterMark.position);
    }

    /* renamed from: S1 */
    public final void m13815S1() {
        findViewById(R.id.btnReplaySetting).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.h1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12135b.m13784M1(view);
            }
        });
    }

    /* renamed from: T1 */
    public final void m13816T1(boolean z8) {
        m13825c2();
        this.f11994A.m18250o(z8);
        this.f11998m.m18192x(z8);
        this.f11999n = z8;
    }

    /* renamed from: U1 */
    public final void m13817U1(int i9) {
        if (this.f11995B) {
            this.f11995B = false;
            new SimpleMessageDialog.C6499b(this, false).m24868o(SimpleMessageDialog.LayoutType.LAYOUT_TYPE_VERTICAL_BUTTON).m24866m(new SimpleMessageDialog.C6500c(C4677a.m18710n().getString(R.string.bc_dialog_button_close), new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.i1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12140b.m13785N1(view);
                }
            }, true, SimpleMessageDialog.C6500c.f21850g)).m24870q(C4677a.m18710n().getString(R.string.bc_dialog_title_Notice), getResources().getColor(R.color.ycl_text_style_a)).m24869p(C4677a.m18710n().getString(i9), SimpleMessageDialog.C6500c.f21849f).m24864k().show();
        }
    }

    /* renamed from: V1 */
    public final void m13818V1() throws Resources.NotFoundException {
        C4677a.m18685K(this.f11996C);
        View viewFindViewById = findViewById(R.id.ui_panel);
        if (viewFindViewById.getVisibility() != 0) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, R.anim.bc_fade_in);
            animationLoadAnimation.setAnimationListener(new AnimationAnimationListenerC2630c());
            viewFindViewById.startAnimation(animationLoadAnimation);
            viewFindViewById.setVisibility(0);
            return;
        }
        LivePlayer livePlayer = this.f11998m;
        if (livePlayer == null || !livePlayer.m18180l()) {
            return;
        }
        C4677a.m18684I(this.f11996C, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    /* renamed from: W1 */
    public final void m13819W1(String str, String str2) {
        TextView textView = (TextView) findViewById(R.id.water_mark_text_view);
        if (textView == null || C4509d.m18120b(str) || C4509d.m18120b(str2)) {
            return;
        }
        String[] strArrSplit = str2.split("_");
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
        textView.setText(str);
        textView.setVisibility(0);
    }

    /* renamed from: X1 */
    public final void m13820X1() {
        m13822Z1(false);
    }

    /* renamed from: Y1 */
    public final void m13821Y1() {
        Uri uri;
        if (this.f11998m.m18183o() || (uri = this.f11997l) == null) {
            return;
        }
        this.f11998m.m18185q(uri, 500L);
        this.f11998m.m18187s(false);
        this.f12006u = 0;
    }

    /* renamed from: Z1 */
    public final void m13822Z1(boolean z8) {
        if (this.f11998m.m18183o()) {
            this.f11998m.m18186r(z8);
        }
    }

    /* renamed from: a2 */
    public final void m13823a2() {
        m13816T1(!this.f11999n);
    }

    /* renamed from: b2 */
    public final void m13824b2() throws Resources.NotFoundException {
        if (findViewById(R.id.ui_panel).getVisibility() != 0) {
            m13818V1();
        } else {
            m13833y1();
        }
    }

    @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
    /* renamed from: c0 */
    public void mo13694c0(LivePlayer livePlayer) {
    }

    /* renamed from: c2 */
    public final void m13825c2() {
        this.f11994A.m18253r(this.f11998m.m18181m(2), this.f11998m.m18181m(1), this.f11998m.m18181m(3));
    }

    /* renamed from: d2 */
    public final void m13826d2() {
        View viewFindViewById = findViewById(R.id.loading_cursor);
        if (viewFindViewById != null) {
            if (this.f12003r == 2) {
                viewFindViewById.setVisibility(0);
            } else {
                viewFindViewById.setVisibility(8);
            }
        }
    }

    /* renamed from: e2 */
    public final void m13827e2() throws Resources.NotFoundException {
        LivePlayer livePlayer;
        ImageView imageView = (ImageView) findViewById(R.id.play_pause_btn);
        if (imageView == null || (livePlayer = this.f11998m) == null) {
            return;
        }
        boolean zM18180l = livePlayer.m18180l();
        int i9 = this.f12003r;
        int i10 = R.drawable.webinar_btn_play;
        if (i9 == 4) {
            this.f11998m.m18173d(false);
            this.f11998m.m18174e(0L);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.webinar_btn_play));
        } else {
            Resources resources = getResources();
            if (zM18180l) {
                i10 = R.drawable.webinar_btn_pause;
            }
            imageView.setImageDrawable(resources.getDrawable(i10));
        }
        View viewFindViewById = findViewById(R.id.ui_panel);
        if (viewFindViewById == null || viewFindViewById.getVisibility() != 0) {
            return;
        }
        m13818V1();
    }

    @Override // com.perfectcorp.ycl.pages.live.LivePlayer.InterfaceC4580c
    /* renamed from: n */
    public void mo13717n(int i9, LivePlayer livePlayer) {
        int i10 = this.f12003r;
        if (i10 == 3 && i9 == 2) {
            Log.m18147a("LiveReplayActivity", "Buffering start, mTotalBufferingDurationMs:" + this.f12004s);
            C5468b.m21111a().m21112b(f11992D);
            this.f12006u = 0;
        } else if (i10 == 2 && i9 == 3) {
            long jM21113c = C5468b.m21111a().m21113c(f11992D, TimeUnit.MILLISECONDS);
            long j9 = this.f12004s;
            if (jM21113c == -1) {
                jM21113c = 0;
            }
            this.f12004s = j9 + jM21113c;
            Log.m18147a("LiveReplayActivity", "Buffering end, mTotalBufferingDurationMs:" + this.f12004s);
        }
        this.f12003r = i9;
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.webinar.r1
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                this.f12207b.m13782K1();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m13828t1(configuration);
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalAccessException, Resources.NotFoundException, SecurityException, IllegalArgumentException {
        super.onCreate(bundle);
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.f12010y = getResources().getDimensionPixelSize(identifier);
        }
        setContentView(R.layout.activity_live_replay);
        View viewFindViewById = findViewById(R.id.root);
        viewFindViewById.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.webinar.e1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f12119b.m13774C1(view, motionEvent);
            }
        });
        View viewFindViewById2 = findViewById(R.id.top_bar);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.k1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f12149b.m13775D1(view);
                }
            });
        }
        ((ImageView) findViewById(R.id.play_pause_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12153b.m13776E1(view);
            }
        });
        ((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new C2628a());
        findViewById(R.id.fullscreen_btn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12157b.m13777F1(view);
            }
        });
        findViewById(R.id.restore_btn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.webinar.n1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12185b.m13779H1(view);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) viewFindViewById.findViewById(R.id.videoContainer);
        if (relativeLayout != null) {
            relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.webinar.o1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.f12188b.m13780I1(view, motionEvent);
                }
            });
        }
        LivePlayer livePlayerM18167g = LivePlayer.m18167g(this, (AspectRatioFrameLayout) findViewById(R.id.video_frame), (TextureView) findViewById(R.id.texture_view), this);
        this.f11998m = livePlayerM18167g;
        livePlayerM18167g.m18191w(findViewById(R.id.status_view));
        CookieHandler cookieHandler = CookieHandler.getDefault();
        CookieManager cookieManager = f11993E;
        if (cookieHandler != cookieManager) {
            CookieHandler.setDefault(cookieManager);
        }
        m13834z1(getIntent());
        m13828t1(getResources().getConfiguration());
        m13832x1();
        m13831w1();
        m13818V1();
        if (!this.f12009x) {
            NetworkLive.addWatchHistory(this.f12001p, this.f12002q).done(new C2629b());
        }
        m13815S1();
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() throws SecurityException {
        super.onDestroy();
        m13820X1();
        this.f12007v = false;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        m13822Z1(false);
        setIntent(intent);
        m13834z1(getIntent());
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            m13811O1();
        }
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || this.f11998m == null) {
            m13812P1();
        }
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            m13812P1();
        }
    }

    @Override // com.perfectcorp.ycl.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            m13811O1();
        }
    }

    /* renamed from: t1 */
    public final void m13828t1(Configuration configuration) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        View viewFindViewById = findViewById(R.id.top_bar);
        View viewFindViewById2 = findViewById(R.id.videoContainer);
        View viewFindViewById3 = findViewById(R.id.restore_btn);
        View viewFindViewById4 = findViewById(R.id.fullscreen_btn);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById2.getLayoutParams();
        int i9 = configuration.orientation;
        if (i9 == 1) {
            viewFindViewById.setVisibility(0);
            viewFindViewById3.setVisibility(4);
            viewFindViewById4.setVisibility(0);
            marginLayoutParams.width = -1;
            marginLayoutParams.height = (displayMetrics.widthPixels * 9) / 16;
            marginLayoutParams.leftMargin = 0;
            marginLayoutParams.topMargin = 0;
        } else if (i9 == 2) {
            viewFindViewById.setVisibility(8);
            viewFindViewById3.setVisibility(0);
            viewFindViewById4.setVisibility(4);
            int iMin = Math.min(((displayMetrics.heightPixels - this.f12010y) * 16) / 9, displayMetrics.widthPixels);
            marginLayoutParams.width = iMin;
            marginLayoutParams.height = -1;
            marginLayoutParams.leftMargin = Math.max((displayMetrics.widthPixels - iMin) / 2, 0);
            marginLayoutParams.topMargin = Math.max(((displayMetrics.heightPixels - this.f12010y) - ((marginLayoutParams.width * 9) / 16)) / 2, 0);
        }
        viewFindViewById2.setLayoutParams(marginLayoutParams);
    }

    /* renamed from: u1 */
    public final void m13829u1() {
        m13822Z1(true);
        C4677a.m18693T("reloading...");
        m13821Y1();
    }

    /* renamed from: v1 */
    public final void m13830v1(float f9) {
        if (f9 == 1.0f || f9 == 1.5f || f9 == 2.0f) {
            this.f12011z = f9;
            LivePlayer livePlayer = this.f11998m;
            if (livePlayer != null) {
                livePlayer.m18188t(f9);
            }
        }
    }

    /* renamed from: w1 */
    public final void m13831w1() {
        if (this.f12007v) {
            C4677a.m18684I(new Runnable() { // from class: com.cyberlink.you.activity.webinar.f1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12127b.m13772A1();
                }
            }, 1000L);
        }
    }

    /* renamed from: x1 */
    public final void m13832x1() {
        if (this.f12007v) {
            C4677a.m18684I(new Runnable() { // from class: com.cyberlink.you.activity.webinar.q1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12203b.m13773B1();
                }
            }, 3000L);
        }
    }

    /* renamed from: y1 */
    public final void m13833y1() throws Resources.NotFoundException {
        C4677a.m18685K(this.f11996C);
        View viewFindViewById = findViewById(R.id.ui_panel);
        if (viewFindViewById.getVisibility() == 4) {
            return;
        }
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(this, R.anim.bc_fade_out);
        animationLoadAnimation.setAnimationListener(new AnimationAnimationListenerC2631d());
        viewFindViewById.startAnimation(animationLoadAnimation);
        viewFindViewById.setVisibility(4);
    }

    /* renamed from: z1 */
    public final void m13834z1(Intent intent) {
        this.f12000o = C4600t.m18339a(intent);
        this.f12001p = intent.getStringExtra("token");
        this.f12002q = intent.getStringExtra("liveId");
        this.f12009x = intent.getBooleanExtra("isEnterprise", false);
        this.f11994A = new C2633f(this, findViewById(R.id.controls_root), this.f11998m);
        m13820X1();
    }
}
