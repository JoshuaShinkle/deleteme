package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzx implements Parcelable.Creator<zzr.zzc> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzc createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i9 = 0;
        String strCreateString = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i10 = 1;
            if (fieldId != 1) {
                i10 = 2;
                if (fieldId != 2) {
                    SafeParcelReader.skipUnknownField(parcel, header);
                } else {
                    strCreateString = SafeParcelReader.createString(parcel, header);
                }
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i10));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzc(hashSet, i9, strCreateString);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzc[] newArray(int i9) {
        return new zzr.zzc[i9];
    }
}
