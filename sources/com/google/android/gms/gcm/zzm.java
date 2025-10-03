package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes2.dex */
final class zzm implements zzn {
    private final PendingIntent zzav;
    private final Context zzl;

    public zzm(Context context) {
        this.zzl = context;
        this.zzav = PendingIntent.getBroadcast(context, 0, new Intent().setPackage("com.google.example.invalidpackage"), 0);
    }

    private final Intent zzh(String str) {
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("app", this.zzav);
        intent.putExtra("source", 4);
        intent.putExtra("source_version", 12451000);
        intent.putExtra("scheduler_action", str);
        return intent;
    }

    @Override // com.google.android.gms.gcm.zzn
    public final boolean zzd(Task task) {
        Intent intentZzh = zzh("SCHEDULE_TASK");
        Bundle bundle = new Bundle();
        task.toBundle(bundle);
        intentZzh.putExtras(bundle);
        this.zzl.sendBroadcast(intentZzh);
        return true;
    }

    @Override // com.google.android.gms.gcm.zzn
    public final boolean zzd(ComponentName componentName, String str) {
        Intent intentZzh = zzh("CANCEL_TASK");
        intentZzh.putExtra("component", componentName);
        intentZzh.putExtra("tag", str);
        this.zzl.sendBroadcast(intentZzh);
        return true;
    }

    @Override // com.google.android.gms.gcm.zzn
    public final boolean zzd(ComponentName componentName) {
        Intent intentZzh = zzh("CANCEL_ALL");
        intentZzh.putExtra("component", componentName);
        this.zzl.sendBroadcast(intentZzh);
        return true;
    }
}
