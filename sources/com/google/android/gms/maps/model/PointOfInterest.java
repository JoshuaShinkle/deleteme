package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PointOfInterestCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class PointOfInterest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzj();

    @SafeParcelable.Field(m17520id = 2)
    public final LatLng latLng;

    @SafeParcelable.Field(m17520id = 4)
    public final String name;

    @SafeParcelable.Field(m17520id = 3)
    public final String placeId;

    @SafeParcelable.Constructor
    public PointOfInterest(@SafeParcelable.Param(m17521id = 2) LatLng latLng, @SafeParcelable.Param(m17521id = 3) String str, @SafeParcelable.Param(m17521id = 4) String str2) {
        this.latLng = latLng;
        this.placeId = str;
        this.name = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.latLng, i9, false);
        SafeParcelWriter.writeString(parcel, 3, this.placeId, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
