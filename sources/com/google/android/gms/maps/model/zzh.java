package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzh implements Parcelable.Creator<MarkerOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MarkerOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        LatLng latLng = null;
        String strCreateString = null;
        String strCreateString2 = null;
        IBinder iBinder = null;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        float f14 = 0.5f;
        float f15 = 1.0f;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel, header, LatLng.CREATOR);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 6:
                    f9 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 7:
                    f10 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 8:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 9:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 10:
                    z10 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 11:
                    f11 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 12:
                    f14 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 13:
                    f12 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 14:
                    f15 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 15:
                    f13 = SafeParcelReader.readFloat(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new MarkerOptions(latLng, strCreateString, strCreateString2, iBinder, f9, f10, z8, z9, z10, f11, f14, f12, f15, f13);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MarkerOptions[] newArray(int i9) {
        return new MarkerOptions[i9];
    }
}
