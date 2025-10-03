package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzw implements Parcelable.Creator<zzr.zzb.C6861zzb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb.C6861zzb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i9 = 0;
        int i10 = 0;
        String strCreateString = null;
        int i11 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i12 = 1;
            if (fieldId != 1) {
                i12 = 2;
                if (fieldId != 2) {
                    i12 = 3;
                    if (fieldId != 3) {
                        i12 = 4;
                        if (fieldId != 4) {
                            SafeParcelReader.skipUnknownField(parcel, header);
                        } else {
                            i10 = SafeParcelReader.readInt(parcel, header);
                        }
                    } else {
                        strCreateString = SafeParcelReader.createString(parcel, header);
                    }
                } else {
                    i9 = SafeParcelReader.readInt(parcel, header);
                }
            } else {
                i11 = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i12));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzb.C6861zzb(hashSet, i11, i9, strCreateString, i10);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb.C6861zzb[] newArray(int i9) {
        return new zzr.zzb.C6861zzb[i9];
    }
}
