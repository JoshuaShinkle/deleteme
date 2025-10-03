package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzl implements Parcelable.Creator<PolylineOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PolylineOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        Cap cap = null;
        Cap cap2 = null;
        ArrayList arrayListCreateTypedList2 = null;
        float f9 = 0.0f;
        float f10 = 0.0f;
        int i9 = 0;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        int i10 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, LatLng.CREATOR);
                    break;
                case 3:
                    f9 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 4:
                    i9 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    f10 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 6:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 8:
                    z10 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 9:
                    cap = (Cap) SafeParcelReader.createParcelable(parcel, header, Cap.CREATOR);
                    break;
                case 10:
                    cap2 = (Cap) SafeParcelReader.createParcelable(parcel, header, Cap.CREATOR);
                    break;
                case 11:
                    i10 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 12:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new PolylineOptions(arrayListCreateTypedList, f9, i9, f10, z8, z9, z10, cap, cap2, i10, arrayListCreateTypedList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PolylineOptions[] newArray(int i9) {
        return new PolylineOptions[i9];
    }
}
