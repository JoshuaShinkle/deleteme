package com.cyberlink.meeting.page;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.plus.PlusShare;
import java.io.IOException;
import org.apache.commons.lang3.StringEscapeUtils;
import p116k4.C5152i0;
import p116k4.C5170o0;
import p197t.C6273a;

/* loaded from: classes.dex */
public class RemindMeetingActivity extends BaseActivity implements MeetingManager.InterfaceC1253c {

    /* renamed from: c */
    public String f6416c;

    /* renamed from: d */
    public String f6417d;

    /* renamed from: e */
    public long f6418e;

    /* renamed from: f */
    public String f6419f;

    /* renamed from: g */
    public TextView f6420g;

    /* renamed from: com.cyberlink.meeting.page.RemindMeetingActivity$a */
    public class C1282a extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ ProgressDialog f6421j;

        /* renamed from: k */
        public final /* synthetic */ String f6422k;

        public C1282a(ProgressDialog progressDialog, String str) {
            this.f6421j = progressDialog;
            this.f6422k = str;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            C5152i0.m20065b(this.f6421j);
            RemindMeetingActivity.this.m5880s(i9, str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            C5152i0.m20065b(this.f6421j);
            RemindMeetingActivity remindMeetingActivity = RemindMeetingActivity.this;
            MeetingActivity.m6531tb(remindMeetingActivity, this.f6422k, null, null, remindMeetingActivity.f6419f, null, meeting.mserverAddr, meeting.token, "remind meeting page");
            RemindMeetingActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public /* synthetic */ void m5873v(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public /* synthetic */ void m5874w(View view) {
        m5881u(this.f6416c, MimeTypes.BASE_TYPE_AUDIO);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y */
    public /* synthetic */ void m5875y(View view) {
        m5881u(this.f6416c, MimeTypes.BASE_TYPE_VIDEO);
    }

    /* renamed from: B */
    public final void m5877B() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        Vibrator vibrator;
        AudioManager audioManager = (AudioManager) getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null || audioManager.getRingerMode() == 0) {
            Log.v("RemindMeetingActivity", "can not get audioManager or device is at silent mode, not play remindSound or vibrate.");
            return;
        }
        boolean zM7439I1 = Globals.m7388i0().m7439I1();
        Uri uriM7433H0 = Globals.m7388i0().m7433H0(zM7439I1);
        if (!zM7439I1 && uriM7433H0 != null) {
            try {
                final MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(this, uriM7433H0);
                mediaPlayer.setAudioStreamType(2);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: o2.z
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer2) {
                        mediaPlayer.release();
                    }
                });
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e9) {
                Log.e("RemindMeetingActivity", "play remind sound error:" + e9);
            }
        }
        if (!Globals.m7388i0().m7557i2() || (vibrator = (Vibrator) getSystemService("vibrator")) == null) {
            return;
        }
        vibrator.vibrate(500L);
    }

    @Override // com.cyberlink.meeting.clrtc.MeetingManager.InterfaceC1253c
    /* renamed from: R */
    public void mo5638R(String str, MeetingManager.MeetingStatus meetingStatus) {
        if ((meetingStatus == MeetingManager.MeetingStatus.IN_MEETING || meetingStatus == MeetingManager.MeetingStatus.PRE_JOIN_FAILED) && !isFinishing()) {
            ULogUtility.m16680p("RemindMeetingActivity", "The new meeting is start, dismiss RemindMeetingActivity page. meetingId = " + str);
            finish();
        }
    }

    @Override // com.cyberlink.meeting.clrtc.MeetingManager.InterfaceC1253c
    /* renamed from: S */
    public void mo5639S(String str, String str2) {
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (40001 == i9 && i10 == -1) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                Log.w("RemindMeetingActivity", "Cannot confirm password result");
                return;
            }
            String string = extras.getString("c.c.m.p.ConfirmPwd_EXTRA_PASSWD");
            if (C5170o0.m20170e(string)) {
                Log.w("RemindMeetingActivity", "Result has no password");
                return;
            }
            MeetingActivity.m6531tb(this, this.f6416c, null, string, this.f6419f, null, extras.getString("meetingMServerAddress"), extras.getString("meetingMServerToken"), "remind meeting page");
            finish();
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        NotificationManager notificationManager;
        super.onCreate(bundle);
        if (MeetingManager.m5619l() && !isFinishing()) {
            finish();
        }
        Window window = getWindow();
        window.addFlags(2097152);
        window.addFlags(524288);
        setContentView(R.layout.activity_meeting_remind);
        MeetingManager.m5609b(this);
        int i9 = Build.VERSION.SDK_INT;
        if (CLUtility.m16533f1(this) > 0) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(C6273a.m24024c(this, R.color.transparent));
            window.getDecorView().setSystemUiVisibility(1280);
        }
        m5878q();
        TextView textView = (TextView) findViewById(R.id.txtDismiss);
        TextView textView2 = (TextView) findViewById(R.id.txtRemindDescription);
        this.f6420g = (TextView) findViewById(R.id.txtJoinError);
        textView2.setText(getString(R.string.clm_meeting_remind_description, this.f6417d, String.valueOf(this.f6418e)));
        textView.setPaintFlags(textView.getPaintFlags() | 8);
        textView.setOnClickListener(new View.OnClickListener() { // from class: o2.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18364b.m5873v(view);
            }
        });
        findViewById(R.id.btnRemindAudioMeeting).setOnClickListener(new View.OnClickListener() { // from class: o2.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18366b.m5874w(view);
            }
        });
        findViewById(R.id.btnRemindVideoMeeting).setOnClickListener(new View.OnClickListener() { // from class: o2.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f18368b.m5875y(view);
            }
        });
        m5877B();
        if (i9 < 29 || (notificationManager = (NotificationManager) getSystemService("notification")) == null) {
            return;
        }
        notificationManager.cancel(this.f6416c, 1);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MeetingManager.m5601A(this);
    }

    /* renamed from: q */
    public final void m5878q() {
        Intent intent = getIntent();
        this.f6416c = intent.getStringExtra("eventId");
        this.f6417d = intent.getStringExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
        this.f6418e = m5879r(intent.getStringExtra("startTime"));
        try {
            this.f6417d = StringEscapeUtils.unescapeXml(this.f6417d);
        } catch (Exception unused) {
        }
        ULogUtility.m16680p("RemindMeetingActivity", "MeetingId = " + this.f6416c + " Title = " + this.f6417d + " RemainMinute = " + this.f6418e);
    }

    /* renamed from: r */
    public final long m5879r(String str) {
        try {
            long j9 = Long.parseLong(str) - System.currentTimeMillis();
            if (j9 <= 0) {
                return 0L;
            }
            long j10 = (j9 / 1000) / 60;
            if (j10 == 0) {
                return 1L;
            }
            return j10;
        } catch (Exception e9) {
            ULogUtility.m16676l("RemindMeetingActivity", "[handleRemindMeeting] parse startDate fail : " + e9);
            return 0L;
        }
    }

    /* renamed from: s */
    public final void m5880s(int i9, String str) {
        if (MeetingManager.m5619l()) {
            this.f6420g.setText(R.string.clm_error_other_meeting_in_progress);
            return;
        }
        int iM5671h = C1260a.m5671h(i9, str);
        if (iM5671h != R.string.clm_error_pwd) {
            this.f6420g.setText(iM5671h);
            return;
        }
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ConfirmPwdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("meetingId", this.f6416c);
        intent.putExtras(bundle);
        startActivityForResult(intent, 40001);
    }

    /* renamed from: u */
    public final void m5881u(String str, String str2) {
        this.f6419f = str2;
        C1260a.m5673k(str, null, Globals.m7388i0().m7506X()).m15439e(new C1282a(ProgressDialog.show(this, "", getString(R.string.loading), true), str));
    }
}
