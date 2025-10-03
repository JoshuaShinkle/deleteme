package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.ULogUtility;

/* loaded from: classes.dex */
public class NotificationActivity extends BaseActivity {
    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_notification);
        if (!Globals.m7388i0().m7464O1()) {
            ULogUtility.m16676l("NotificationActivity", "user is not login, navigate to splash activity for login");
            Globals.m7388i0().m7617t2(null);
            finish();
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            if (MeetingManager.m5622o()) {
                ULogUtility.m16680p("NotificationActivity", "not start ChatDialogActivity when in meeting.");
                finish();
                return;
            }
        } else if (MeetingManager.m5619l()) {
            ULogUtility.m16680p("NotificationActivity", "not start ChatDialogActivity when in meeting.");
            finish();
            return;
        }
        if (C3199c.m17029x()) {
            ULogUtility.m16680p("NotificationActivity", "not start ChatDialogActivity when uploading files.");
            finish();
            return;
        }
        Intent intent = getIntent();
        Intent intent2 = new Intent(this, (Class<?>) ChatDialogActivity.class);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            intent2.putExtras(extras);
        }
        intent2.setFlags(268468224);
        startActivity(intent2);
        finish();
    }
}
