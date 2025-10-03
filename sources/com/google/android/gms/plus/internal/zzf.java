package com.google.android.gms.plus.internal;

import android.os.IInterface;
import com.google.android.gms.common.internal.ICancelToken;
import java.util.List;

/* loaded from: classes2.dex */
public interface zzf extends IInterface {
    String getAccountName();

    ICancelToken zza(zzb zzbVar, int i9, int i10, int i11, String str);

    void zza();

    void zza(zzb zzbVar);

    void zza(zzb zzbVar, List<String> list);
}
