package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzer extends com.google.android.gms.internal.measurement.zza implements zzep {
    public zzer(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(zzar zzarVar, zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zzarVar);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(1, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zzb(zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(6, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final String zzc(zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        Parcel parcelZza = zza(11, parcelM17533a_);
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zzd(zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(18, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zze(zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(20, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(zzkw zzkwVar, zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zzkwVar);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(2, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(4, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(zzar zzarVar, String str, String str2) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zzarVar);
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        zzb(5, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final List<zzkw> zza(zzn zznVar, boolean z8) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, z8);
        Parcel parcelZza = zza(7, parcelM17533a_);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzkw.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final byte[] zza(zzar zzarVar, String str) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zzarVar);
        parcelM17533a_.writeString(str);
        Parcel parcelZza = zza(9, parcelM17533a_);
        byte[] bArrCreateByteArray = parcelZza.createByteArray();
        parcelZza.recycle();
        return bArrCreateByteArray;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(long j9, String str, String str2, String str3) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeLong(j9);
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        parcelM17533a_.writeString(str3);
        zzb(10, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(zzw zzwVar, zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zzwVar);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(12, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(zzw zzwVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zzwVar);
        zzb(13, parcelM17533a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final List<zzkw> zza(String str, String str2, boolean z8, zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, z8);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        Parcel parcelZza = zza(14, parcelM17533a_);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzkw.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final List<zzkw> zza(String str, String str2, String str3, boolean z8) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        parcelM17533a_.writeString(str3);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, z8);
        Parcel parcelZza = zza(15, parcelM17533a_);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzkw.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final List<zzw> zza(String str, String str2, zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        Parcel parcelZza = zza(16, parcelM17533a_);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzw.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final List<zzw> zza(String str, String str2, String str3) {
        Parcel parcelM17533a_ = m17533a_();
        parcelM17533a_.writeString(str);
        parcelM17533a_.writeString(str2);
        parcelM17533a_.writeString(str3);
        Parcel parcelZza = zza(17, parcelM17533a_);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzw.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzep
    public final void zza(Bundle bundle, zzn zznVar) {
        Parcel parcelM17533a_ = m17533a_();
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, bundle);
        com.google.android.gms.internal.measurement.zzb.zza(parcelM17533a_, zznVar);
        zzb(19, parcelM17533a_);
    }
}
