package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzd implements Parcelable.Creator<BeginSignInRequest.GoogleIdTokenRequestOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BeginSignInRequest.GoogleIdTokenRequestOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z8 = false;
        boolean z9 = false;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        ArrayList<String> arrayListCreateStringList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 5:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new BeginSignInRequest.GoogleIdTokenRequestOptions(z8, strCreateString, strCreateString2, z9, strCreateString3, arrayListCreateStringList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BeginSignInRequest.GoogleIdTokenRequestOptions[] newArray(int i9) {
        return new BeginSignInRequest.GoogleIdTokenRequestOptions[i9];
    }
}
