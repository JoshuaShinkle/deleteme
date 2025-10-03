package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* loaded from: classes2.dex */
public final class zaj implements Parcelable.Creator<FastJsonResponse.Field> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FastJsonResponse.Field createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        int i10 = 0;
        boolean z8 = false;
        int i11 = 0;
        boolean z9 = false;
        int i12 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        com.google.android.gms.common.server.converter.zaa zaaVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i9 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    i10 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 3:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 4:
                    i11 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 6:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                    i12 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 8:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    zaaVar = (com.google.android.gms.common.server.converter.zaa) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.common.server.converter.zaa.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new FastJsonResponse.Field(i9, i10, z8, i11, z9, strCreateString, i12, strCreateString2, zaaVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FastJsonResponse.Field[] newArray(int i9) {
        return new FastJsonResponse.Field[i9];
    }
}
