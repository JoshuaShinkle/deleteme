package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zza implements Parcelable.Creator<CameraPosition> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CameraPosition createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        LatLng latLng = null;
        float f9 = BitmapDescriptorFactory.HUE_RED;
        float f10 = 0.0f;
        float f11 = 0.0f;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                latLng = (LatLng) SafeParcelReader.createParcelable(parcel, header, LatLng.CREATOR);
            } else if (fieldId == 3) {
                f9 = SafeParcelReader.readFloat(parcel, header);
            } else if (fieldId == 4) {
                f10 = SafeParcelReader.readFloat(parcel, header);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                f11 = SafeParcelReader.readFloat(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CameraPosition(latLng, f9, f10, f11);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CameraPosition[] newArray(int i9) {
        return new CameraPosition[i9];
    }
}
