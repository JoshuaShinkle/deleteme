package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes2.dex */
public final class zzae extends zza implements zzac {
    public zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void clearTileCache() {
        zzb(2, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final boolean getFadeIn() {
        Parcel parcelZza = zza(11, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final String getId() {
        Parcel parcelZza = zza(3, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final float getTransparency() {
        Parcel parcelZza = zza(13, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final float getZIndex() {
        Parcel parcelZza = zza(5, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final boolean isVisible() {
        Parcel parcelZza = zza(7, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setFadeIn(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(10, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setTransparency(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(12, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setVisible(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(6, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setZIndex(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final boolean zza(zzac zzacVar) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzacVar);
        Parcel parcelZza2 = zza(8, parcelZza);
        boolean zZza = zzc.zza(parcelZza2);
        parcelZza2.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final int zzj() {
        Parcel parcelZza = zza(9, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }
}
