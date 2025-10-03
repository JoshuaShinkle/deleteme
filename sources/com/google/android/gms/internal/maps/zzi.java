package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes2.dex */
public abstract class zzi extends zzb implements zzh {
    public static zzh zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        return iInterfaceQueryLocalInterface instanceof zzh ? (zzh) iInterfaceQueryLocalInterface : new zzj(iBinder);
    }
}
