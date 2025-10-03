package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
public interface IStatusCallback extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.base.zaa implements IStatusCallback {

        public static class zaa extends com.google.android.gms.internal.base.zab implements IStatusCallback {
            public zaa(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
            }

            @Override // com.google.android.gms.common.api.internal.IStatusCallback
            public final void onResult(Status status) {
                Parcel parcelZaa = zaa();
                com.google.android.gms.internal.base.zad.zaa(parcelZaa, status);
                zac(1, parcelZaa);
            }
        }

        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        @RecentlyNonNull
        public static IStatusCallback asInterface(@RecentlyNonNull IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            return iInterfaceQueryLocalInterface instanceof IStatusCallback ? (IStatusCallback) iInterfaceQueryLocalInterface : new zaa(iBinder);
        }

        @Override // com.google.android.gms.internal.base.zaa
        @RecentlyNonNull
        public final boolean zaa(@RecentlyNonNull int i9, @RecentlyNonNull Parcel parcel, @RecentlyNonNull Parcel parcel2, @RecentlyNonNull int i10) {
            if (i9 != 1) {
                return false;
            }
            onResult((Status) com.google.android.gms.internal.base.zad.zaa(parcel, Status.CREATOR));
            return true;
        }
    }

    void onResult(@RecentlyNonNull Status status);
}
