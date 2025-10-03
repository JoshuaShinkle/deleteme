package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.plus.zzr;
import java.util.HashSet;

/* loaded from: classes2.dex */
public final class zzy implements Parcelable.Creator<zzr.zzd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzd createFromParcel(Parcel parcel) {
        int i9;
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        int i10 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i10 = SafeParcelReader.readInt(parcel, header);
                    i9 = 1;
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    i9 = 2;
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    i9 = 3;
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    i9 = 4;
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    i9 = 5;
                    break;
                case 6:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    i9 = 6;
                    break;
                case 7:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    i9 = 7;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    continue;
            }
            hashSet.add(Integer.valueOf(i9));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzd(hashSet, i10, strCreateString, strCreateString2, strCreateString3, strCreateString4, strCreateString5, strCreateString6);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzd[] newArray(int i9) {
        return new zzr.zzd[i9];
    }
}
