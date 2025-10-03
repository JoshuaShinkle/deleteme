package com.cyberlink.you.module.appinvite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p112k0.C5111a;
import p193s4.C6263a;

/* loaded from: classes.dex */
public class ReferrerReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        C5111a.m19960b(context).m19962c(C6263a.m24004b(intent, new Intent("com.cyberlink.you.appinvite.deeplink")));
    }
}
