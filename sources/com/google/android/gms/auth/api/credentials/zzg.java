package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzg implements Parcelable.Creator<CredentialRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CredentialRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        String[] strArrCreateStringArray = null;
        CredentialPickerConfig credentialPickerConfig = null;
        CredentialPickerConfig credentialPickerConfig2 = null;
        String strCreateString = null;
        String strCreateString2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        z8 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    case 2:
                        strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                        break;
                    case 3:
                        credentialPickerConfig = (CredentialPickerConfig) SafeParcelReader.createParcelable(parcel, header, CredentialPickerConfig.CREATOR);
                        break;
                    case 4:
                        credentialPickerConfig2 = (CredentialPickerConfig) SafeParcelReader.createParcelable(parcel, header, CredentialPickerConfig.CREATOR);
                        break;
                    case 5:
                        z9 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    case 6:
                        strCreateString = SafeParcelReader.createString(parcel, header);
                        break;
                    case 7:
                        strCreateString2 = SafeParcelReader.createString(parcel, header);
                        break;
                    case 8:
                        z10 = SafeParcelReader.readBoolean(parcel, header);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, header);
                        break;
                }
            } else {
                i9 = SafeParcelReader.readInt(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CredentialRequest(i9, z8, strArrCreateStringArray, credentialPickerConfig, credentialPickerConfig2, z9, strCreateString, strCreateString2, z10);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CredentialRequest[] newArray(int i9) {
        return new CredentialRequest[i9];
    }
}
