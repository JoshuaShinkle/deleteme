package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import p197t.C6274b;

/* loaded from: classes2.dex */
final class zzcx implements zzcs {
    private static zzcx zza;
    private final Context zzb;
    private final ContentObserver zzc;

    private zzcx(Context context) {
        this.zzb = context;
        zzcz zzczVar = new zzcz(this, null);
        this.zzc = zzczVar;
        context.getContentResolver().registerContentObserver(zzck.zza, true, zzczVar);
    }

    public static zzcx zza(Context context) {
        zzcx zzcxVar;
        synchronized (zzcx.class) {
            if (zza == null) {
                zza = C6274b.m24031b(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzcx(context) : new zzcx();
            }
            zzcxVar = zza;
        }
        return zzcxVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.measurement.zzcs
    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final String zza(final String str) {
        if (this.zzb == null) {
            return null;
        }
        try {
            return (String) zzcv.zza(new zzcu(this, str) { // from class: com.google.android.gms.internal.measurement.zzcw
                private final zzcx zza;
                private final String zzb;

                {
                    this.zza = this;
                    this.zzb = str;
                }

                @Override // com.google.android.gms.internal.measurement.zzcu
                public final Object zza() {
                    return this.zza.zzb(this.zzb);
                }
            });
        } catch (IllegalStateException | SecurityException e9) {
            String strValueOf = String.valueOf(str);
            Log.e("GservicesLoader", strValueOf.length() != 0 ? "Unable to read GServices for: ".concat(strValueOf) : new String("Unable to read GServices for: "), e9);
            return null;
        }
    }

    public final /* synthetic */ String zzb(String str) {
        return zzck.zza(this.zzb.getContentResolver(), str, (String) null);
    }

    private zzcx() {
        this.zzb = null;
        this.zzc = null;
    }

    public static synchronized void zza() {
        Context context;
        zzcx zzcxVar = zza;
        if (zzcxVar != null && (context = zzcxVar.zzb) != null && zzcxVar.zzc != null) {
            context.getContentResolver().unregisterContentObserver(zza.zzc);
        }
        zza = null;
    }
}
