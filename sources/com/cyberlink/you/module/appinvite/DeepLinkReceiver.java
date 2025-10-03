package com.cyberlink.you.module.appinvite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p005a4.C0032a;
import p193s4.C6263a;

/* loaded from: classes.dex */
public class DeepLinkReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (C6263a.m24007e(intent)) {
            new C0032a(intent).m131l();
        }
    }
}
