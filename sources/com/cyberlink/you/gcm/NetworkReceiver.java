package com.cyberlink.you.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.cyberlink.you.utility.ULogUtility;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class NetworkReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            boolean z8 = !intent.getBooleanExtra("noConnectivity", false);
            ULogUtility.m16689y("onReceive: " + intent.getAction());
            ULogUtility.m16689y(" > connected: " + z8);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                ULogUtility.m16689y(" > type: N/A");
                return;
            }
            String str = " > type: " + activeNetworkInfo.getTypeName();
            if (1 == activeNetworkInfo.getType()) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (!C6383t.m24517f(extraInfo)) {
                    str = str + ", name: " + extraInfo;
                }
            }
            String subtypeName = activeNetworkInfo.getSubtypeName();
            if (!C6383t.m24517f(subtypeName)) {
                str = str + ", subType: " + subtypeName;
            }
            ULogUtility.m16689y(str);
        }
    }
}
