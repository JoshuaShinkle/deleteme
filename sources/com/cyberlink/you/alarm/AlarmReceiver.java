package com.cyberlink.you.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.cyberlink.you.utility.ULogUtility;
import p095i3.C5048a;

/* loaded from: classes.dex */
public class AlarmReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ULogUtility.m16690z("AlarmReceiver start, start AlarmService to query archive message via HTTP");
        AlarmService.m14001l(context, intent);
        ULogUtility.m16690z("Register for next alarm.");
        C5048a.m19721a(context);
    }
}
