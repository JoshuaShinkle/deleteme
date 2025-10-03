package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zze implements Parcelable.Creator<CredentialPickerConfig> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CredentialPickerConfig createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        int i10 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                z8 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 2) {
                z9 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 3) {
                z10 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 4) {
                i10 = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 1000) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CredentialPickerConfig(i9, z8, z9, z10, i10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CredentialPickerConfig[] newArray(int i9) {
        return new CredentialPickerConfig[i9];
    }
}
