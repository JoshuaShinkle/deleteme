package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzav implements ServiceConnection {
    final /* synthetic */ zzat zzxe;
    private volatile zzce zzxf;
    private volatile boolean zzxg;

    public zzav(zzat zzatVar) {
        this.zzxe = zzatVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceConnected");
        synchronized (this) {
            try {
                if (iBinder == null) {
                    this.zzxe.zzu("Service connected with null binder");
                    return;
                }
                zzce zzcfVar = null;
                try {
                    String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                    if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(interfaceDescriptor)) {
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                        zzcfVar = iInterfaceQueryLocalInterface instanceof zzce ? (zzce) iInterfaceQueryLocalInterface : new zzcf(iBinder);
                        this.zzxe.zzq("Bound to IAnalyticsService interface");
                    } else {
                        this.zzxe.zze("Got binder with a wrong descriptor", interfaceDescriptor);
                    }
                } catch (RemoteException unused) {
                    this.zzxe.zzu("Service connect failed to get IAnalyticsService");
                }
                if (zzcfVar == null) {
                    try {
                        ConnectionTracker.getInstance().unbindService(this.zzxe.getContext(), this.zzxe.zzxa);
                    } catch (IllegalArgumentException unused2) {
                    }
                } else if (this.zzxg) {
                    this.zzxf = zzcfVar;
                } else {
                    this.zzxe.zzt("onServiceConnected received after the timeout limit");
                    this.zzxe.zzcq().zza(new zzaw(this, zzcfVar));
                }
            } finally {
                notifyAll();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzxe.zzcq().zza(new zzax(this, componentName));
    }

    public final zzce zzdq() {
        com.google.android.gms.analytics.zzk.zzav();
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        Context context = this.zzxe.getContext();
        intent.putExtra("app_package_name", context.getPackageName());
        ConnectionTracker connectionTracker = ConnectionTracker.getInstance();
        synchronized (this) {
            this.zzxf = null;
            this.zzxg = true;
            boolean zBindService = connectionTracker.bindService(context, intent, this.zzxe.zzxa, TsExtractor.TS_STREAM_TYPE_AC3);
            this.zzxe.zza("Bind to service requested", Boolean.valueOf(zBindService));
            if (!zBindService) {
                this.zzxg = false;
                return null;
            }
            try {
                wait(zzby.zzaak.get().longValue());
            } catch (InterruptedException unused) {
                this.zzxe.zzt("Wait for service connect was interrupted");
            }
            this.zzxg = false;
            zzce zzceVar = this.zzxf;
            this.zzxf = null;
            if (zzceVar == null) {
                this.zzxe.zzu("Successfully bound to service but never got onServiceConnected callback");
            }
            return zzceVar;
        }
    }
}
