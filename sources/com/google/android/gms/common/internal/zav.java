package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zav implements Parcelable.Creator<zas> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zas createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        boolean z8 = false;
        boolean z9 = false;
        IBinder iBinder = null;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i9 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            } else if (fieldId == 3) {
                connectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, header, ConnectionResult.CREATOR);
            } else if (fieldId == 4) {
                z8 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z9 = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zas(i9, iBinder, connectionResult, z8, z9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zas[] newArray(int i9) {
        return new zas[i9];
    }
}
