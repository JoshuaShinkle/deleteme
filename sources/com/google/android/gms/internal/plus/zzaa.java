package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzaa implements Parcelable.Creator<zzr.zzf> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzf createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i9 = 0;
        String strCreateString = null;
        boolean z8 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i10 = 1;
            if (fieldId != 1) {
                i10 = 2;
                if (fieldId != 2) {
                    i10 = 3;
                    if (fieldId != 3) {
                        SafeParcelReader.skipUnknownField(parcel, header);
                    } else {
                        strCreateString = SafeParcelReader.createString(parcel, header);
                    }
                } else {
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                }
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i10));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzf(hashSet, i9, z8, strCreateString);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzf[] newArray(int i9) {
        return new zzr.zzf[i9];
    }
}
