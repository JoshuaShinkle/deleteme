package com.cyberlink.meeting.page.p032m;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.rockerhieu.emojicon.EmojiconTextView;
import java.io.IOException;
import p116k4.C5170o0;
import p116k4.C5179r0;
import p173q2.C6127a;
import p197t.C6273a;

/* loaded from: classes.dex */
public class PreJoinMeetingFailActivity extends BaseFragmentActivity implements MeetingManager.InterfaceC1253c {

    /* renamed from: c */
    public String f7034c;

    /* renamed from: d */
    public String f7035d;

    /* renamed from: e */
    public String f7036e;

    /* renamed from: f */
    public String f7037f;

    /* renamed from: g */
    public Friend f7038g;

    /* renamed from: h */
    public Group f7039h;

    /* renamed from: i */
    public MeetingActivity.InviteCallType f7040i;

    /* renamed from: j */
    public boolean f7041j;

    /* renamed from: k */
    public View.OnClickListener f7042k = new ViewOnClickListenerC1369a();

    /* renamed from: l */
    public View.OnClickListener f7043l = new View.OnClickListener() { // from class: p2.o6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f20550b.m7088U0(view);
        }
    };

    /* renamed from: m */
    public View.OnClickListener f7044m = new View.OnClickListener() { // from class: p2.p6
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f20575b.m7089V0(view);
        }
    };

    /* renamed from: com.cyberlink.meeting.page.m.PreJoinMeetingFailActivity$a */
    public class ViewOnClickListenerC1369a implements View.OnClickListener {
        public ViewOnClickListenerC1369a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PreJoinMeetingFailActivity.this.f7041j) {
                PreJoinMeetingFailActivity preJoinMeetingFailActivity = PreJoinMeetingFailActivity.this;
                MeetingActivity.m6370f9(preJoinMeetingFailActivity, preJoinMeetingFailActivity.f7039h, true, PreJoinMeetingFailActivity.this.f7037f, "pre-join meeting failed and click call again button");
            } else {
                PreJoinMeetingFailActivity preJoinMeetingFailActivity2 = PreJoinMeetingFailActivity.this;
                MeetingActivity.m6531tb(preJoinMeetingFailActivity2, preJoinMeetingFailActivity2.f7034c, null, null, PreJoinMeetingFailActivity.this.f7037f, PreJoinMeetingFailActivity.this.f7039h, PreJoinMeetingFailActivity.this.f7035d, PreJoinMeetingFailActivity.this.f7036e, "pre-join meeting failed and click call again button");
            }
            PreJoinMeetingFailActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.PreJoinMeetingFailActivity$b */
    public class C1370b implements MediaPlayer.OnCompletionListener {

        /* renamed from: b */
        public final /* synthetic */ MediaPlayer f7046b;

        public C1370b(MediaPlayer mediaPlayer) {
            this.f7046b = mediaPlayer;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            this.f7046b.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U0 */
    public /* synthetic */ void m7088U0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V0 */
    public /* synthetic */ void m7089V0(View view) {
        CLUtility.m16477P1(this);
    }

    @Override // com.cyberlink.meeting.clrtc.MeetingManager.InterfaceC1253c
    /* renamed from: R */
    public void mo5638R(String str, MeetingManager.MeetingStatus meetingStatus) {
        if (meetingStatus != MeetingManager.MeetingStatus.IN_MEETING || isFinishing()) {
            return;
        }
        ULogUtility.m16680p("PreJoinMeetingFailActivity", "The new meeting is start, dismiss preJoinMeetingFailActivity page. meetingId = " + str);
        finish();
    }

    @Override // com.cyberlink.meeting.clrtc.MeetingManager.InterfaceC1253c
    /* renamed from: S */
    public void mo5639S(String str, String str2) {
    }

    /* renamed from: S0 */
    public final void m7090S0(Intent intent) {
        if (intent == null) {
            ULogUtility.m16676l("PreJoinMeetingFailActivity", "getExtra failed, intent is null");
            return;
        }
        this.f7034c = intent.getStringExtra("meetingId");
        String stringExtra = intent.getStringExtra("type");
        this.f7037f = stringExtra;
        if (C5170o0.m20170e(stringExtra)) {
            this.f7037f = MimeTypes.BASE_TYPE_VIDEO;
        }
        this.f7039h = (Group) intent.getParcelableExtra("group");
        this.f7038g = (Friend) intent.getParcelableExtra("inviter");
        this.f7040i = (MeetingActivity.InviteCallType) intent.getSerializableExtra("inviteCallType");
        Group group = this.f7039h;
        this.f7041j = group != null && group.f13716c.equals("Dual");
        this.f7035d = intent.getStringExtra("meetingMServerAddress");
        this.f7036e = intent.getStringExtra("meetingMServerToken");
    }

    /* renamed from: T0 */
    public final boolean m7091T0() {
        return !C5170o0.m20170e(this.f7037f) && this.f7037f.equals(MimeTypes.BASE_TYPE_AUDIO);
    }

    /* renamed from: W0 */
    public final void m7092W0(String str, boolean z8, ImageView imageView) {
        C6127a.m23474o(this, imageView, str, z8 ? R.drawable.pic_default : R.drawable.pic_default_group);
    }

    /* renamed from: X0 */
    public final void m7093X0() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Vibrator vibrator;
        AudioManager audioManager = (AudioManager) getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null || audioManager.getRingerMode() == 0) {
            ULogUtility.m16683s("PreJoinMeetingFailActivity", "can not get audioManager or device is at silent mode, not play remindSound or vibrate.");
            return;
        }
        boolean zM7439I1 = Globals.m7388i0().m7439I1();
        Uri uriM7433H0 = Globals.m7388i0().m7433H0(zM7439I1);
        if (!zM7439I1 && uriM7433H0 != null) {
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(this, uriM7433H0);
                mediaPlayer.setAudioStreamType(2);
                mediaPlayer.setOnCompletionListener(new C1370b(mediaPlayer));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e9) {
                ULogUtility.m16676l("PreJoinMeetingFailActivity", "play remind sound error:" + e9);
            }
        }
        if (!Globals.m7388i0().m7557i2() || (vibrator = (Vibrator) getSystemService("vibrator")) == null) {
            return;
        }
        vibrator.vibrate(500L);
    }

    /* renamed from: Y0 */
    public final void m7094Y0(String str) {
        TextView textView = (TextView) findViewById(R.id.preJoinFailedErrorMessageText);
        textView.setText(str);
        C5179r0.m20247b(textView, 3);
        findViewById(R.id.btnPreJoinFailedCallAgain).setVisibility(8);
    }

    /* renamed from: Z0 */
    public final void m7095Z0() {
        m7094Y0(getString(R.string.clm_error_old_app_client));
        findViewById(R.id.btnPreJoinFailedUpdate).setVisibility(0);
    }

    /* renamed from: a1 */
    public final void m7096a1() {
        String stringExtra = getIntent().getStringExtra("c.c.u.m.HOST_NAME");
        if (stringExtra == null) {
            return;
        }
        m7094Y0(getString(R.string.clm_error_limit_with_count_host, stringExtra, Integer.valueOf(getIntent().getIntExtra("c.c.u.m.LIMIT_COUNT", 0))));
    }

    /* renamed from: b1 */
    public final void m7097b1() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.meetingBottomButtonLayout);
        ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.preJoinFailedBottomButtonLayout);
        TextView textView = (TextView) findViewById(R.id.preJoinFailedCallAgainText);
        viewGroup.setVisibility(8);
        viewGroup2.setVisibility(0);
        findViewById(R.id.btnPreJoinFailedCallAgain).setOnClickListener(this.f7042k);
        findViewById(R.id.btnPreJoinFailedDismiss).setOnClickListener(this.f7043l);
        findViewById(R.id.btnPreJoinFailedUpdate).setOnClickListener(this.f7044m);
        if (this.f7041j) {
            textView.setText(R.string.clm_meeting_button_call_again);
        } else {
            textView.setText(R.string.clmw_join_join_meeting);
        }
    }

    /* renamed from: c1 */
    public final void m7098c1() {
        EmojiconTextView emojiconTextView = (EmojiconTextView) findViewById(R.id.meetingInviteStatus);
        ImageView imageView = (ImageView) findViewById(R.id.status_icon);
        if (this.f7040i != MeetingActivity.InviteCallType.CALLEE) {
            emojiconTextView.setText(R.string.calling);
            imageView.setVisibility(8);
            return;
        }
        if (m7091T0()) {
            emojiconTextView.setText(R.string.incoming_voice_call);
        } else {
            emojiconTextView.setText(R.string.incoming_video_call);
        }
        if (m7091T0()) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
        }
    }

    /* renamed from: d1 */
    public final void m7099d1() {
        Group group = this.f7039h;
        if (group != null) {
            if (this.f7038g == null || this.f7040i != MeetingActivity.InviteCallType.CALLEE || this.f7041j) {
                m7092W0(group.f13724k, this.f7041j, (ImageView) findViewById(R.id.meetingInviteeAvatar));
                ((EmojiconTextView) findViewById(R.id.meetingInviteeName)).setText(this.f7039h.f13717d);
                ((EmojiconTextView) findViewById(R.id.meetingInviteeGroup)).setText("");
            } else {
                C6127a.m23469j(this, (ImageView) findViewById(R.id.meetingInviteeAvatar), this.f7038g);
                ((EmojiconTextView) findViewById(R.id.meetingInviteeName)).setText(this.f7038g.m15620a());
                ((EmojiconTextView) findViewById(R.id.meetingInviteeGroup)).setText(getString(R.string.clm_invite_from_group, this.f7039h.f13717d));
            }
        }
    }

    /* renamed from: e1 */
    public final void m7100e1() {
        ULogUtility.m16683s("PreJoinMeetingFailActivity", "updateLayout meetingId = " + this.f7034c);
        findViewById(R.id.meetingInviteLayout).setVisibility(0);
        m7098c1();
        m7099d1();
        m7097b1();
        TextView textView = (TextView) findViewById(R.id.preJoinFailedErrorMessageText);
        textView.setText(R.string.clm_meeting_bad_network_find_place_try_again);
        C5179r0.m20247b(textView, 3);
        if (getIntent().getBooleanExtra("c.c.u.m.REACH_LIMIT", false)) {
            m7096a1();
        } else if (getIntent().getBooleanExtra("c.c.u.m.OLD_APP", false)) {
            m7095Z0();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        super.onCreate(bundle);
        Window window = getWindow();
        window.addFlags(2097152);
        window.addFlags(524288);
        if (CLUtility.m16533f1(this) > 0) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(C6273a.m24024c(this, R.color.transparent));
            window.getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.view_item_meeting_invite);
        MeetingManager.m5609b(this);
        m7090S0(getIntent());
        m7100e1();
        m7093X0();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MeetingManager.m5601A(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        ULogUtility.m16683s("PreJoinMeetingFailActivity", "onNewIntent");
        getIntent().replaceExtras(intent);
        m7090S0(getIntent());
        m7100e1();
    }
}
