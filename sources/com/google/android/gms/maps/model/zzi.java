package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzi implements Parcelable.Creator<PatternItem> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PatternItem createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        Float floatObject = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                i9 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                floatObject = SafeParcelReader.readFloatObject(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new PatternItem(i9, floatObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ PatternItem[] newArray(int i9) {
        return new PatternItem[i9];
    }
}
