package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zab implements Parcelable.Creator<zaa> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaa createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        StringToIntConverter stringToIntConverter = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i9 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 2) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                stringToIntConverter = (StringToIntConverter) SafeParcelReader.createParcelable(parcel, header, StringToIntConverter.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zaa(i9, stringToIntConverter);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaa[] newArray(int i9) {
        return new zaa[i9];
    }
}
