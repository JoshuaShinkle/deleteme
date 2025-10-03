package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
/* loaded from: classes2.dex */
public class Wrappers {
    private static Wrappers zza = new Wrappers();
    private PackageManagerWrapper zzb = null;

    @KeepForSdk
    public static PackageManagerWrapper packageManager(Context context) {
        return zza.zza(context);
    }

    @VisibleForTesting
    public final synchronized PackageManagerWrapper zza(Context context) {
        if (this.zzb == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzb = new PackageManagerWrapper(context);
        }
        return this.zzb;
    }
}
