package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInConfigurationCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes2.dex */
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zzx();

    @SafeParcelable.Field(getter = "getConsumerPkgName", m17520id = 2)
    private final String zzcp;

    @SafeParcelable.Field(getter = "getGoogleConfig", m17520id = 5)
    private GoogleSignInOptions zzcq;

    @SafeParcelable.Constructor
    public SignInConfiguration(@SafeParcelable.Param(m17521id = 2) String str, @SafeParcelable.Param(m17521id = 5) GoogleSignInOptions googleSignInOptions) {
        this.zzcp = Preconditions.checkNotEmpty(str);
        this.zzcq = googleSignInOptions;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SignInConfiguration)) {
            return false;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
        if (this.zzcp.equals(signInConfiguration.zzcp)) {
            GoogleSignInOptions googleSignInOptions = this.zzcq;
            if (googleSignInOptions == null) {
                if (signInConfiguration.zzcq == null) {
                    return true;
                }
            } else if (googleSignInOptions.equals(signInConfiguration.zzcq)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.zzcp).addObject(this.zzcq).hash();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcp, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzcq, i9, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final GoogleSignInOptions zzp() {
        return this.zzcq;
    }
}
