package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        zzt zztVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        int i9 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i9 = SafeParcelReader.readInt(parcel, header);
                hashSet.add(1);
            } else if (fieldId == 2) {
                zztVar = (zzt) SafeParcelReader.createParcelable(parcel, header, zzt.CREATOR);
                hashSet.add(2);
            } else if (fieldId == 3) {
                strCreateString = SafeParcelReader.createString(parcel, header);
                hashSet.add(3);
            } else if (fieldId == 4) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
                hashSet.add(4);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                strCreateString3 = SafeParcelReader.createString(parcel, header);
                hashSet.add(5);
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr(hashSet, i9, zztVar, strCreateString, strCreateString2, strCreateString3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int i9) {
        return new zzr[i9];
    }
}
