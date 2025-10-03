package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.server.response.SafeParcelResponse;
import com.google.android.gms.internal.plus.zzr;

/* loaded from: classes2.dex */
public interface zzb extends IInterface {
    void zza(int i9, Bundle bundle);

    void zza(int i9, Bundle bundle, Bundle bundle2);

    void zza(int i9, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor);

    void zza(int i9, Bundle bundle, SafeParcelResponse safeParcelResponse);

    void zza(int i9, zzr zzrVar);

    void zza(Status status);

    void zza(DataHolder dataHolder, String str);

    void zza(DataHolder dataHolder, String str, String str2);

    void zzb(String str);

    void zzc(String str);
}
