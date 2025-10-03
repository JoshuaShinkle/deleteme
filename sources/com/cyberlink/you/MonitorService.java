package com.cyberlink.you;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.core.app.JobIntentService;
import com.cyberlink.you.chat.NotificationHelper;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import p218v2.C6456d;

/* loaded from: classes.dex */
public class MonitorService extends JobIntentService {
    /* renamed from: j */
    public static void m7659j(Context context, Intent intent) {
        JobIntentService.m1472d(context, MonitorService.class, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, intent);
    }

    @Override // androidx.core.app.JobIntentService
    /* renamed from: g */
    public void mo1477g(Intent intent) {
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public int onStartCommand(Intent intent, int i9, int i10) {
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        C6456d.m24714D().m24745C();
        NotificationHelper.m14057A();
        stopSelf();
        super.onTaskRemoved(intent);
    }
}
