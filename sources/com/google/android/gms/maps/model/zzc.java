package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzc implements Parcelable.Creator<CircleOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CircleOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        LatLng latLng = null;
        ArrayList arrayListCreateTypedList = null;
        double d9 = 0.0d;
        float f9 = 0.0f;
        float f10 = 0.0f;
        int i9 = 0;
        int i10 = 0;
        boolean z8 = false;
        boolean z9 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel, header, LatLng.CREATOR);
                    break;
                case 3:
                    d9 = SafeParcelReader.readDouble(parcel, header);
                    break;
                case 4:
                    f9 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 5:
                    i9 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 6:
                    i10 = SafeParcelReader.readInt(parcel, header);
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
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CircleOptions(latLng, d9, f9, i9, i10, f10, z8, z9, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CircleOptions[] newArray(int i9) {
        return new CircleOptions[i9];
    }
}
