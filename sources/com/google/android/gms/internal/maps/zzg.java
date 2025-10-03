package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes2.dex */
public final class zzg extends zza implements zze {
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(int i9) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i9);
        Parcel parcelZza2 = zza(1, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zzb(String str) {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZza2 = zza(3, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zzc(String str) {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZza2 = zza(7, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zzi() {
        Parcel parcelZza = zza(4, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(String str) {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        Parcel parcelZza2 = zza(2, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        Parcel parcelZza2 = zza(5, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.internal.maps.zze
    public final IObjectWrapper zza(Bitmap bitmap) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, bitmap);
        Parcel parcelZza2 = zza(6, parcelZza);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return iObjectWrapperAsInterface;
    }
}
