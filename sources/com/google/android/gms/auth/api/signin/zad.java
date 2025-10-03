package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zad implements Parcelable.Creator<GoogleSignInOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i9 = 0;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        ArrayList arrayListCreateTypedList = null;
        Account account = null;
        String strCreateString = null;
        String strCreateString2 = null;
        ArrayList arrayListCreateTypedList2 = null;
        String strCreateString3 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i9 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                case 4:
                    z8 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 5:
                    z9 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 6:
                    z10 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 9:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                case 10:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GoogleSignInOptions(i9, (ArrayList<Scope>) arrayListCreateTypedList, account, z8, z9, z10, strCreateString, strCreateString2, (ArrayList<GoogleSignInOptionsExtensionParcelable>) arrayListCreateTypedList2, strCreateString3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions[] newArray(int i9) {
        return new GoogleSignInOptions[i9];
    }
}
