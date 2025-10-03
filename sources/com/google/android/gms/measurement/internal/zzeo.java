package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class zzeo extends com.google.android.gms.internal.measurement.zzc implements zzep {
    public zzeo() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.measurement.zzc
    public final boolean zza(int i9, Parcel parcel, Parcel parcel2, int i10) {
        switch (i9) {
            case 1:
                zza((zzar) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzar.CREATOR), (zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                zza((zzkw) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzkw.CREATOR), (zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 3:
            case 8:
            default:
                return false;
            case 4:
                zza((zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 5:
                zza((zzar) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzar.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzb((zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                List<zzkw> listZza = zza((zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR), com.google.android.gms.internal.measurement.zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza);
                return true;
            case 9:
                byte[] bArrZza = zza((zzar) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzar.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(bArrZza);
                return true;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                String strZzc = zzc((zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(strZzc);
                return true;
            case 12:
                zza((zzw) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzw.CREATOR), (zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 13:
                zza((zzw) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzw.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                List<zzkw> listZza2 = zza(parcel.readString(), parcel.readString(), com.google.android.gms.internal.measurement.zzb.zza(parcel), (zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza2);
                return true;
            case 15:
                List<zzkw> listZza3 = zza(parcel.readString(), parcel.readString(), parcel.readString(), com.google.android.gms.internal.measurement.zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza3);
                return true;
            case 16:
                List<zzw> listZza4 = zza(parcel.readString(), parcel.readString(), (zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza4);
                return true;
            case 17:
                List<zzw> listZza5 = zza(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza5);
                return true;
            case 18:
                zzd((zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 19:
                zza((Bundle) com.google.android.gms.internal.measurement.zzb.zza(parcel, Bundle.CREATOR), (zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 20:
                zze((zzn) com.google.android.gms.internal.measurement.zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
        }
    }
}
