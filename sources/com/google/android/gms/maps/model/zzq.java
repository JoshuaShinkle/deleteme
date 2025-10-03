package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzq implements Parcelable.Creator<StreetViewSource> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StreetViewSource createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new StreetViewSource(i9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StreetViewSource[] newArray(int i9) {
        return new StreetViewSource[i9];
    }
}
