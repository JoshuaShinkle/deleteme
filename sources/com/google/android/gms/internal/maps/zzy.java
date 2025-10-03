package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzy extends zza implements zzw {
    public zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final int getFillColor() {
        Parcel parcelZza = zza(12, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final List getHoles() {
        Parcel parcelZza = zza(6, zza());
        ArrayList arrayListZzb = zzc.zzb(parcelZza);
        parcelZza.recycle();
        return arrayListZzb;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final String getId() {
        Parcel parcelZza = zza(2, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final List<LatLng> getPoints() {
        Parcel parcelZza = zza(4, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(LatLng.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final int getStrokeColor() {
        Parcel parcelZza = zza(10, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final int getStrokeJointType() {
        Parcel parcelZza = zza(24, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final List<PatternItem> getStrokePattern() {
        Parcel parcelZza = zza(26, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(PatternItem.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final float getStrokeWidth() {
        Parcel parcelZza = zza(8, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final float getZIndex() {
        Parcel parcelZza = zza(14, zza());
        float f9 = parcelZza.readFloat();
        parcelZza.recycle();
        return f9;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final boolean isClickable() {
        Parcel parcelZza = zza(22, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final boolean isGeodesic() {
        Parcel parcelZza = zza(18, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final boolean isVisible() {
        Parcel parcelZza = zza(16, zza());
        boolean zZza = zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void remove() {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setClickable(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(21, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setFillColor(int i9) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i9);
        zzb(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setGeodesic(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(17, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setHoles(List list) {
        Parcel parcelZza = zza();
        parcelZza.writeList(list);
        zzb(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setPoints(List<LatLng> list) {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setStrokeColor(int i9) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i9);
        zzb(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setStrokeJointType(int i9) {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i9);
        zzb(23, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setStrokePattern(List<PatternItem> list) {
        Parcel parcelZza = zza();
        parcelZza.writeTypedList(list);
        zzb(25, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setStrokeWidth(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setVisible(boolean z8) {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z8);
        zzb(15, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void setZIndex(float f9) {
        Parcel parcelZza = zza();
        parcelZza.writeFloat(f9);
        zzb(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final boolean zzb(zzw zzwVar) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzwVar);
        Parcel parcelZza2 = zza(19, parcelZza);
        boolean zZza = zzc.zza(parcelZza2);
        parcelZza2.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzb(27, parcelZza);
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final int zzj() {
        Parcel parcelZza = zza(20, zza());
        int i9 = parcelZza.readInt();
        parcelZza.recycle();
        return i9;
    }

    @Override // com.google.android.gms.internal.maps.zzw
    public final IObjectWrapper zzk() {
        Parcel parcelZza = zza(28, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }
}
