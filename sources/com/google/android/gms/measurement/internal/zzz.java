package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzz implements Parcelable.Creator<zzw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        zzkw zzkwVar = null;
        String strCreateString3 = null;
        zzar zzarVar = null;
        zzar zzarVar2 = null;
        zzar zzarVar3 = null;
        long j9 = 0;
        long j10 = 0;
        long j11 = 0;
        boolean z8 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    zzkwVar = (zzkw) SafeParcelReader.createParcelable(parcel, header, zzkw.CREATOR);
                    break;
                case 5:
                    j9 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 6:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    zzarVar = (zzar) SafeParcelReader.createParcelable(parcel, header, zzar.CREATOR);
                    break;
                case 9:
                    j10 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 10:
                    zzarVar2 = (zzar) SafeParcelReader.createParcelable(parcel, header, zzar.CREATOR);
                    break;
                case 11:
                    j11 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 12:
                    zzarVar3 = (zzar) SafeParcelReader.createParcelable(parcel, header, zzar.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzw(strCreateString, strCreateString2, zzkwVar, j9, z8, strCreateString3, zzarVar, j10, zzarVar2, j11, zzarVar3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzw[] newArray(int i9) {
        return new zzw[i9];
    }
}
