package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzo implements Parcelable.Creator<zzn> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzn createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        String strCreateString = null;
        String[] strArrCreateStringArray = null;
        String[] strArrCreateStringArray2 = null;
        String[] strArrCreateStringArray3 = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        PlusCommonExtras plusCommonExtras = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        strCreateString = SafeParcelReader.createString(parcel, header);
                        break;
                    case 2:
                        strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                        break;
                    case 3:
                        strArrCreateStringArray2 = SafeParcelReader.createStringArray(parcel, header);
                        break;
                    case 4:
                        strArrCreateStringArray3 = SafeParcelReader.createStringArray(parcel, header);
                        break;
                    case 5:
                        strCreateString2 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 6:
                        strCreateString3 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 7:
                        strCreateString4 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 8:
                        strCreateString5 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 9:
                        plusCommonExtras = (PlusCommonExtras) SafeParcelReader.createParcelable(parcel, header, PlusCommonExtras.CREATOR);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, header);
                        break;
                }
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzn(i9, strCreateString, strArrCreateStringArray, strArrCreateStringArray2, strArrCreateStringArray3, strCreateString2, strCreateString3, strCreateString4, strCreateString5, plusCommonExtras);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzn[] newArray(int i9) {
        return new zzn[i9];
    }
}
