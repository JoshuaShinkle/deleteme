package com.cyberlink.you.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.cyberlink.you.utility.ULogUtility;

/* loaded from: classes.dex */
public class NotificationDismissedReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public final String f12416a = "NotificationDismissedReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() == null || !intent.getBooleanExtra("isMeeting", false)) {
            return;
        }
        ULogUtility.m16670f("NotificationDismissedReceiver", "clearMeetingCallNotification");
        NotificationHelper.m14096l();
    }
}
