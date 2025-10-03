package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "HintRequestCreator")
/* loaded from: classes2.dex */
public final class HintRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<HintRequest> CREATOR = new zzj();

    @SafeParcelable.Field(getter = "getAccountTypes", m17520id = 4)
    private final String[] zzab;

    @SafeParcelable.Field(getter = "isIdTokenRequested", m17520id = 5)
    private final boolean zzae;

    @SafeParcelable.Field(getter = "getServerClientId", m17520id = 6)
    private final String zzaf;

    @SafeParcelable.Field(getter = "getIdTokenNonce", m17520id = 7)
    private final String zzag;

    @SafeParcelable.Field(getter = "getHintPickerConfig", m17520id = 1)
    private final CredentialPickerConfig zzai;

    @SafeParcelable.Field(getter = "isEmailAddressIdentifierSupported", m17520id = 2)
    private final boolean zzaj;

    @SafeParcelable.Field(getter = "isPhoneNumberIdentifierSupported", m17520id = 3)
    private final boolean zzak;

    @SafeParcelable.Field(m17520id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int zzv;

    public static final class Builder {
        private String[] zzab;
        private String zzaf;
        private String zzag;
        private boolean zzaj;
        private boolean zzak;
        private CredentialPickerConfig zzai = new CredentialPickerConfig.Builder().build();
        private boolean zzae = false;

        public final HintRequest build() {
            if (this.zzab == null) {
                this.zzab = new String[0];
            }
            if (this.zzaj || this.zzak || this.zzab.length != 0) {
                return new HintRequest(this);
            }
            throw new IllegalStateException("At least one authentication method must be specified");
        }

        public final Builder setAccountTypes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            this.zzab = strArr;
            return this;
        }

        public final Builder setEmailAddressIdentifierSupported(boolean z8) {
            this.zzaj = z8;
            return this;
        }

        public final Builder setHintPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzai = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
            return this;
        }

        public final Builder setIdTokenNonce(String str) {
            this.zzag = str;
            return this;
        }

        public final Builder setIdTokenRequested(boolean z8) {
            this.zzae = z8;
            return this;
        }

        public final Builder setPhoneNumberIdentifierSupported(boolean z8) {
            this.zzak = z8;
            return this;
        }

        public final Builder setServerClientId(String str) {
            this.zzaf = str;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public HintRequest(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) CredentialPickerConfig credentialPickerConfig, @SafeParcelable.Param(m17521id = 2) boolean z8, @SafeParcelable.Param(m17521id = 3) boolean z9, @SafeParcelable.Param(m17521id = 4) String[] strArr, @SafeParcelable.Param(m17521id = 5) boolean z10, @SafeParcelable.Param(m17521id = 6) String str, @SafeParcelable.Param(m17521id = 7) String str2) {
        this.zzv = i9;
        this.zzai = (CredentialPickerConfig) Preconditions.checkNotNull(credentialPickerConfig);
        this.zzaj = z8;
        this.zzak = z9;
        this.zzab = (String[]) Preconditions.checkNotNull(strArr);
        if (i9 < 2) {
            this.zzae = true;
            this.zzaf = null;
            this.zzag = null;
        } else {
            this.zzae = z10;
            this.zzaf = str;
            this.zzag = str2;
        }
    }

    public final String[] getAccountTypes() {
        return this.zzab;
    }

    public final CredentialPickerConfig getHintPickerConfig() {
        return this.zzai;
    }

    public final String getIdTokenNonce() {
        return this.zzag;
    }

    public final String getServerClientId() {
        return this.zzaf;
    }

    public final boolean isEmailAddressIdentifierSupported() {
        return this.zzaj;
    }

    public final boolean isIdTokenRequested() {
        return this.zzae;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getHintPickerConfig(), i9, false);
        SafeParcelWriter.writeBoolean(parcel, 2, isEmailAddressIdentifierSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzak);
        SafeParcelWriter.writeStringArray(parcel, 4, getAccountTypes(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.zzv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private HintRequest(Builder builder) {
        this(2, builder.zzai, builder.zzaj, builder.zzak, builder.zzab, builder.zzae, builder.zzaf, builder.zzag);
    }
}
