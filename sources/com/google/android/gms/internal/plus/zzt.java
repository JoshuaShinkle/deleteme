package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzt implements Parcelable.Creator<zzr.zza> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zza createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i9 = 0;
        int i10 = 0;
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
                        SafeParcelReader.skipUnknownField(parcel, header);
                    } else {
                        i11 = SafeParcelReader.readInt(parcel, header);
                    }
                } else {
                    i10 = SafeParcelReader.readInt(parcel, header);
                }
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i12));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zza(hashSet, i9, i10, i11);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zza[] newArray(int i9) {
        return new zzr.zza[i9];
    }
}
