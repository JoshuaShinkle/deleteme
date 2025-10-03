package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p132m.C5302a;

/* loaded from: classes2.dex */
public final class zzdn implements zzcs {
    private static final Map<String, zzdn> zza = new C5302a();
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc;
    private final Object zzd;
    private volatile Map<String, ?> zze;
    private final List<zzct> zzf;

    private zzdn(SharedPreferences sharedPreferences) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener(this) { // from class: com.google.android.gms.internal.measurement.zzdm
            private final zzdn zza;

            {
                this.zza = this;
            }

            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                this.zza.zza(sharedPreferences2, str);
            }
        };
        this.zzc = onSharedPreferenceChangeListener;
        this.zzd = new Object();
        this.zzf = new ArrayList();
        this.zzb = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public static zzdn zza(Context context, String str) {
        zzdn zzdnVar;
        if (zzcm.zza()) {
            throw null;
        }
        synchronized (zzdn.class) {
            Map<String, zzdn> map = zza;
            zzdnVar = map.get(null);
            if (zzdnVar == null) {
                zzdnVar = new zzdn(zzb(context, null));
                map.put(null, zzdnVar);
            }
        }
        return zzdnVar;
    }

    private static SharedPreferences zzb(Context context, String str) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (!str.startsWith("direct_boot:")) {
                return context.getSharedPreferences(str, 0);
            }
            if (zzcm.zza()) {
                context = context.createDeviceProtectedStorageContext();
            }
            return context.getSharedPreferences(str.substring(12), 0);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcs
    public final Object zza(String str) {
        Map<String, ?> map = this.zze;
        if (map == null) {
            synchronized (this.zzd) {
                map = this.zze;
                if (map == null) {
                    StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzb.getAll();
                        this.zze = all;
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public static synchronized void zza() {
        for (zzdn zzdnVar : zza.values()) {
            zzdnVar.zzb.unregisterOnSharedPreferenceChangeListener(zzdnVar.zzc);
        }
        zza.clear();
    }

    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzd) {
            this.zze = null;
            zzdc.zza();
        }
        synchronized (this) {
            Iterator<zzct> it = this.zzf.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }
}
