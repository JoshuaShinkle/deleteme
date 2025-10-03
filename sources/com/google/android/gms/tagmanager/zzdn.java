package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
class zzdn extends BroadcastReceiver {

    @VisibleForTesting
    private static final String zzabm = "com.google.android.gms.tagmanager.zzdn";
    private final zzfm zzaic;

    public zzdn(zzfm zzfmVar) {
        this.zzaic = zzfmVar;
    }

    public static void zzn(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzabm, true);
        context.sendBroadcast(intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            if (!"com.google.analytics.RADIO_POWERED".equals(action) || intent.hasExtra(zzabm)) {
                return;
            }
            this.zzaic.zzjp();
            return;
        }
        Bundle extras = intent.getExtras();
        Boolean boolValueOf = Boolean.FALSE;
        if (extras != null) {
            boolValueOf = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
        }
        this.zzaic.zzf(!boolValueOf.booleanValue());
    }
}
