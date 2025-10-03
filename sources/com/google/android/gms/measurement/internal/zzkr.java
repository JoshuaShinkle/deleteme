package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzkr implements zzfc {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzkp zzb;

    public zzkr(zzkp zzkpVar, String str) {
        this.zzb = zzkpVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzfc
    public final void zza(String str, int i9, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zza(i9, th, bArr, this.zza);
    }
}
