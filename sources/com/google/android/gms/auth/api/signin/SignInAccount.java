package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInAccountCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public class SignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInAccount> CREATOR = new zzd();

    @SafeParcelable.Field(defaultValue = "", m17520id = 4)
    @Deprecated
    private String zzby;

    @SafeParcelable.Field(getter = "getGoogleSignInAccount", m17520id = 7)
    private GoogleSignInAccount zzbz;

    @SafeParcelable.Field(defaultValue = "", m17520id = 8)
    @Deprecated
    private String zzca;

    @SafeParcelable.Constructor
    public SignInAccount(@SafeParcelable.Param(m17521id = 4) String str, @SafeParcelable.Param(m17521id = 7) GoogleSignInAccount googleSignInAccount, @SafeParcelable.Param(m17521id = 8) String str2) {
        this.zzbz = googleSignInAccount;
        this.zzby = Preconditions.checkNotEmpty(str, "8.3 and 8.4 SDKs require non-null email");
        this.zzca = Preconditions.checkNotEmpty(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzbz;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 4, this.zzby, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzbz, i9, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzca, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
