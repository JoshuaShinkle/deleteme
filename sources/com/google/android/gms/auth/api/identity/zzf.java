package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzf implements Parcelable.Creator<BeginSignInRequest.PasswordRequestOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BeginSignInRequest.PasswordRequestOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z8 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 1) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z8 = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new BeginSignInRequest.PasswordRequestOptions(z8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BeginSignInRequest.PasswordRequestOptions[] newArray(int i9) {
        return new BeginSignInRequest.PasswordRequestOptions[i9];
    }
}
