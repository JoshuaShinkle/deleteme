package com.perfectcorp.ycl.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.WindowManager;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.perfectcorp.ycl.commons.utility.Log;

/* loaded from: classes2.dex */
public class DisplayBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: a */
    public final Context f16220a;

    /* renamed from: b */
    public boolean f16221b;

    /* renamed from: c */
    public Point f16222c;

    /* renamed from: a */
    public final synchronized void m18388a() {
        ((WindowManager) this.f16220a.getSystemService("window")).getDefaultDisplay().getSize(this.f16222c);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null || (action = intent.getAction()) == null || !action.equals("android.intent.action.HDMI_PLUGGED")) {
            return;
        }
        this.f16221b = intent.getBooleanExtra(RemoteConfigConstants.ResponseFieldKey.STATE, false);
        Log.m18151e("masteraccess.DisplayBroadcastReceiver", "mPlugged: " + String.valueOf(this.f16221b));
        m18388a();
    }
}
