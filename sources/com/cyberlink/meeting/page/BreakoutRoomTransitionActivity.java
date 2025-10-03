package com.cyberlink.meeting.page;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.meeting.model.BreakoutRoom;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.MimeTypes;

/* loaded from: classes.dex */
public class BreakoutRoomTransitionActivity extends BaseActivity {

    /* renamed from: c */
    public String f6337c;

    /* renamed from: d */
    public String f6338d;

    /* renamed from: e */
    public MeetingActivity.BreakoutRoomState f6339e;

    /* renamed from: com.cyberlink.meeting.page.BreakoutRoomTransitionActivity$a */
    public static /* synthetic */ class C1263a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f6340a;

        static {
            int[] iArr = new int[MeetingActivity.BreakoutRoomState.values().length];
            f6340a = iArr;
            try {
                iArr[MeetingActivity.BreakoutRoomState.CHILD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6340a[MeetingActivity.BreakoutRoomState.PARENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6340a[MeetingActivity.BreakoutRoomState.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public /* synthetic */ void m5737j(String str, String str2, String str3, BreakoutRoom breakoutRoom, boolean z8, boolean z9, boolean z10) {
        MeetingActivity.m6576xb(this, this.f6337c, str, str2, str3, this.f6338d, null, null, null, "group chatroom msg", this.f6339e, breakoutRoom, Boolean.valueOf(z8), Boolean.valueOf(z9), Boolean.valueOf(z10));
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        final boolean z8;
        final boolean z9;
        final boolean z10;
        final String str;
        final String str2;
        final String str3;
        final BreakoutRoom breakoutRoom;
        super.onCreate(bundle);
        setContentView(R.layout.activity_breakout_room_transition);
        ImageView imageView = (ImageView) findViewById(R.id.transitionImage);
        TextView textView = (TextView) findViewById(R.id.actionTextView);
        Intent intent = getIntent();
        if (intent != null) {
            BreakoutRoom breakoutRoom2 = (BreakoutRoom) intent.getParcelableExtra("breakoutRoom");
            String stringExtra = intent.getStringExtra("password");
            String stringExtra2 = intent.getStringExtra("ltiToken");
            boolean booleanExtra = intent.getBooleanExtra("isMicrophoneOn", true);
            boolean booleanExtra2 = intent.getBooleanExtra("isCameraOn", false);
            boolean booleanExtra3 = intent.getBooleanExtra("isSpeakerOn", false);
            String stringExtra3 = intent.getStringExtra("displayName");
            this.f6337c = breakoutRoom2 != null ? breakoutRoom2.f6329d : intent.getStringExtra("meetingId");
            String stringExtra4 = intent.getStringExtra("type");
            this.f6338d = stringExtra4;
            if (stringExtra4 == null) {
                this.f6338d = MimeTypes.BASE_TYPE_VIDEO;
            }
            MeetingActivity.BreakoutRoomState breakoutRoomState = (MeetingActivity.BreakoutRoomState) intent.getSerializableExtra("breakoutRoomState");
            this.f6339e = breakoutRoomState;
            if (breakoutRoomState != null) {
                int i9 = C1263a.f6340a[breakoutRoomState.ordinal()];
                if (i9 == 1) {
                    imageView.setImageResource(R.drawable.img_join_breakoutroom_2x);
                    textView.setText(R.string.breakout_room_transition_join_breakout_room);
                    ((TextView) findViewById(R.id.roomNameTextView)).setText(breakoutRoom2 != null ? breakoutRoom2.f6327b : "");
                } else if (i9 == 2 || i9 == 3) {
                    textView.setText(R.string.breakout_room_transition_join_back_to_main);
                    imageView.setImageResource(R.drawable.img_return2main_2x);
                } else {
                    ULogUtility.m16676l("BreakoutRoomTransitionActivity", "mBreakoutRoomState is null!");
                }
            }
            z10 = booleanExtra3;
            breakoutRoom = breakoutRoom2;
            z8 = booleanExtra;
            z9 = booleanExtra2;
            str = stringExtra3;
            str2 = stringExtra;
            str3 = stringExtra2;
        } else {
            z8 = true;
            z9 = false;
            z10 = false;
            str = null;
            str2 = null;
            str3 = null;
            breakoutRoom = null;
        }
        new Handler().postDelayed(new Runnable() { // from class: o2.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f18300b.m5737j(str, str2, str3, breakoutRoom, z8, z9, z10);
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }
}
