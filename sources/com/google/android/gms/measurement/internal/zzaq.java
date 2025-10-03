package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzaq implements Parcelable.Creator<zzar> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzar createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        zzam zzamVar = null;
        String strCreateString2 = null;
        long j9 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 3) {
                zzamVar = (zzam) SafeParcelReader.createParcelable(parcel, header, zzam.CREATOR);
            } else if (fieldId == 4) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                j9 = SafeParcelReader.readLong(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzar(strCreateString, zzamVar, strCreateString2, j9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzar[] newArray(int i9) {
        return new zzar[i9];
    }
}
