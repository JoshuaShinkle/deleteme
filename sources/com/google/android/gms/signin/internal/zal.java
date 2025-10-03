package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zas;

/* loaded from: classes2.dex */
public final class zal implements Parcelable.Creator<zam> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zam createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        ConnectionResult connectionResult = null;
        zas zasVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i9 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, header, ConnectionResult.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                zasVar = (zas) SafeParcelReader.createParcelable(parcel, header, zas.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zam(i9, connectionResult, zasVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zam[] newArray(int i9) {
        return new zam[i9];
    }
}
