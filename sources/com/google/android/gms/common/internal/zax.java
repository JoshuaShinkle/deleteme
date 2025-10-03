package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zax implements Parcelable.Creator<zau> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zau createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        int i10 = 0;
        Scope[] scopeArr = null;
        int i11 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i9 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                i11 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 3) {
                i10 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                scopeArr = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zau(i9, i11, i10, scopeArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zau[] newArray(int i9) {
        return new zau[i9];
    }
}
