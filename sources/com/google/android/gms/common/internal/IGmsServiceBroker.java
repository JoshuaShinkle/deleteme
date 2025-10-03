package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* loaded from: classes2.dex */
public interface IGmsServiceBroker extends IInterface {

    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        public Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        @Override // android.os.IInterface
        @CanIgnoreReturnValue
        @KeepForSdk
        public IBinder asBinder() {
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:61:0x00d2  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x00e7  */
        @Override // android.os.Binder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean onTransact(int i9, Parcel parcel, Parcel parcel2, int i10) {
            IGmsCallbacks zzaaVar;
            if (i9 > 16777215) {
                return super.onTransact(i9, parcel, parcel2, i10);
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder == null) {
                zzaaVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                zzaaVar = iInterfaceQueryLocalInterface instanceof IGmsCallbacks ? (IGmsCallbacks) iInterfaceQueryLocalInterface : new zzaa(strongBinder);
            }
            if (i9 == 46) {
                getService(zzaaVar, parcel.readInt() != 0 ? GetServiceRequest.CREATOR.createFromParcel(parcel) : null);
                Preconditions.checkNotNull(parcel2);
                parcel2.writeNoException();
                return true;
            }
            if (i9 == 47) {
                if (parcel.readInt() != 0) {
                    zzaj.CREATOR.createFromParcel(parcel);
                }
                throw new UnsupportedOperationException();
            }
            parcel.readInt();
            if (i9 != 4) {
                parcel.readString();
                if (i9 == 1) {
                    parcel.readString();
                    parcel.createStringArray();
                    parcel.readString();
                    if (parcel.readInt() != 0) {
                    }
                } else if (i9 == 2 || i9 == 23 || i9 == 25 || i9 == 27) {
                    if (parcel.readInt() != 0) {
                    }
                } else if (i9 == 30) {
                    parcel.createStringArray();
                    parcel.readString();
                    if (parcel.readInt() != 0) {
                    }
                } else if (i9 == 34) {
                    parcel.readString();
                } else if (i9 != 41 && i9 != 43 && i9 != 37 && i9 != 38) {
                    switch (i9) {
                        case 9:
                            parcel.readString();
                            parcel.createStringArray();
                            parcel.readString();
                            parcel.readStrongBinder();
                            parcel.readString();
                            if (parcel.readInt() != 0) {
                                break;
                            }
                            break;
                        case 10:
                            parcel.readString();
                            parcel.createStringArray();
                            break;
                        case 19:
                            parcel.readStrongBinder();
                            if (parcel.readInt() != 0) {
                                break;
                            }
                            break;
                    }
                }
            }
            throw new UnsupportedOperationException();
        }
    }

    @KeepForSdk
    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest);
}
