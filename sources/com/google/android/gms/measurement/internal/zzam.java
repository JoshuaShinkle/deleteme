package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

@SafeParcelable.Class(creator = "EventParamsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class zzam extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzam> CREATOR = new zzao();

    @SafeParcelable.Field(getter = "z", m17520id = 2)
    private final Bundle zza;

    @SafeParcelable.Constructor
    public zzam(@SafeParcelable.Param(m17521id = 2) Bundle bundle) {
        this.zza = bundle;
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzap(this);
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzb(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final Object zza(String str) {
        return this.zza.get(str);
    }

    public final Long zzb(String str) {
        return Long.valueOf(this.zza.getLong(str));
    }

    public final Double zzc(String str) {
        return Double.valueOf(this.zza.getDouble(str));
    }

    public final String zzd(String str) {
        return this.zza.getString(str);
    }

    public final int zza() {
        return this.zza.size();
    }

    public final Bundle zzb() {
        return new Bundle(this.zza);
    }
}
