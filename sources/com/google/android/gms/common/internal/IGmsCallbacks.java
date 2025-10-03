package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes2.dex */
public interface IGmsCallbacks extends IInterface {
    void onPostInitComplete(int i9, IBinder iBinder, Bundle bundle);

    void zzb(int i9, Bundle bundle);

    void zzc(int i9, IBinder iBinder, zzj zzjVar);
}
