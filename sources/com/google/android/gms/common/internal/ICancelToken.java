package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes2.dex */
public interface ICancelToken extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements ICancelToken {
        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        public static ICancelToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            return iInterfaceQueryLocalInterface instanceof ICancelToken ? (ICancelToken) iInterfaceQueryLocalInterface : new zzw(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zzb
        public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) {
            if (i9 != 2) {
                return false;
            }
            cancel();
            return true;
        }
    }

    void cancel();
}
