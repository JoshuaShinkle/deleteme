package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

/* loaded from: classes2.dex */
public final class zzai implements Parcelable.Creator<StreetViewPanoramaOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StreetViewPanoramaOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        String strCreateString = null;
        LatLng latLng = null;
        Integer integerObject = null;
        StreetViewSource streetViewSource = null;
        byte b9 = 0;
        byte b10 = 0;
        byte b11 = 0;
        byte b12 = 0;
        byte b13 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) SafeParcelReader.createParcelable(parcel, header, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel, header, LatLng.CREATOR);
                    break;
                case 5:
                    integerObject = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                case 6:
                    b9 = SafeParcelReader.readByte(parcel, header);
                    break;
                case 7:
                    b10 = SafeParcelReader.readByte(parcel, header);
                    break;
                case 8:
                    b11 = SafeParcelReader.readByte(parcel, header);
                    break;
                case 9:
                    b12 = SafeParcelReader.readByte(parcel, header);
                    break;
                case 10:
                    b13 = SafeParcelReader.readByte(parcel, header);
                    break;
                case 11:
                    streetViewSource = (StreetViewSource) SafeParcelReader.createParcelable(parcel, header, StreetViewSource.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new StreetViewPanoramaOptions(streetViewPanoramaCamera, strCreateString, latLng, integerObject, b9, b10, b11, b12, b13, streetViewSource);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StreetViewPanoramaOptions[] newArray(int i9) {
        return new StreetViewPanoramaOptions[i9];
    }
}
