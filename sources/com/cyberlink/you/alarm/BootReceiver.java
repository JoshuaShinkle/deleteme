package com.cyberlink.you.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.cyberlink.you.friends.FriendsClient;
import p095i3.C5048a;

/* loaded from: classes.dex */
public class BootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d("BootReceiver", "onReceive");
        FriendsClient.m15678o();
        C5048a.m19721a(context);
    }
}
