package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzm implements Parcelable.Creator<zzn> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzn createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        String strCreateString7 = null;
        Boolean booleanObject = null;
        ArrayList<String> arrayListCreateStringList = null;
        String strCreateString8 = null;
        long j9 = 0;
        long j10 = 0;
        long j11 = 0;
        long j12 = 0;
        long j13 = 0;
        boolean z8 = true;
        boolean z9 = true;
        boolean z10 = true;
        boolean z11 = false;
        int i9 = 0;
        boolean z12 = false;
        long j14 = -2147483648L;
        String strCreateString9 = "";
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
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    j9 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 7:
                    j10 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 8:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 10:
                    z11 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 11:
                    j14 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 12:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    break;
                case 13:
                    j11 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 14:
                    j12 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 15:
                    i9 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 16:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 17:
                    z10 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 18:
                    z12 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 19:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    break;
                case 20:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 21:
                    booleanObject = SafeParcelReader.readBooleanObject(parcel, header);
                    break;
                case 22:
                    j13 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 23:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 24:
                    strCreateString8 = SafeParcelReader.createString(parcel, header);
                    break;
                case 25:
                    strCreateString9 = SafeParcelReader.createString(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzn(strCreateString, strCreateString2, strCreateString3, strCreateString4, j9, j10, strCreateString5, z8, z11, j14, strCreateString6, j11, j12, i9, z9, z10, z12, strCreateString7, booleanObject, j13, arrayListCreateStringList, strCreateString8, strCreateString9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzn[] newArray(int i9) {
        return new zzn[i9];
    }
}
