package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;

@KeepForSdk
@SafeParcelable.Class(creator = "FeatureCreator")
/* loaded from: classes2.dex */
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Feature> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getName", m17520id = 1)
    private final String zza;

    @SafeParcelable.Field(getter = "getOldVersion", m17520id = 2)
    @Deprecated
    private final int zzb;

    @SafeParcelable.Field(defaultValue = "-1", getter = "getVersion", m17520id = 3)
    private final long zzc;

    @SafeParcelable.Constructor
    public Feature(@SafeParcelable.Param(m17521id = 1) String str, @SafeParcelable.Param(m17521id = 2) int i9, @SafeParcelable.Param(m17521id = 3) long j9) {
        this.zza = str;
        this.zzb = i9;
        this.zzc = j9;
    }

    @KeepForSdk
    public Feature(String str, long j9) {
        this.zza = str;
        this.zzc = j9;
        this.zzb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            if (((getName() != null && getName().equals(feature.getName())) || (getName() == null && feature.getName() == null)) && getVersion() == feature.getVersion()) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public String getName() {
        return this.zza;
    }

    @KeepForSdk
    public long getVersion() {
        long j9 = this.zzc;
        return j9 == -1 ? this.zzb : j9;
    }

    public final int hashCode() {
        return Objects.hashCode(getName(), Long.valueOf(getVersion()));
    }

    public final String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add(AppMeasurementSdk.ConditionalUserProperty.NAME, getName());
        stringHelper.add("version", Long.valueOf(getVersion()));
        return stringHelper.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, getVersion());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
