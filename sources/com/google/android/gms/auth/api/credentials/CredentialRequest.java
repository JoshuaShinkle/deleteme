package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SafeParcelable.Class(creator = "CredentialRequestCreator")
/* loaded from: classes2.dex */
public final class CredentialRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CredentialRequest> CREATOR = new zzg();

    @SafeParcelable.Field(getter = "isPasswordLoginSupported", m17520id = 1)
    private final boolean zzaa;

    @SafeParcelable.Field(getter = "getAccountTypes", m17520id = 2)
    private final String[] zzab;

    @SafeParcelable.Field(getter = "getCredentialPickerConfig", m17520id = 3)
    private final CredentialPickerConfig zzac;

    @SafeParcelable.Field(getter = "getCredentialHintPickerConfig", m17520id = 4)
    private final CredentialPickerConfig zzad;

    @SafeParcelable.Field(getter = "isIdTokenRequested", m17520id = 5)
    private final boolean zzae;

    @SafeParcelable.Field(getter = "getServerClientId", m17520id = 6)
    private final String zzaf;

    @SafeParcelable.Field(getter = "getIdTokenNonce", m17520id = 7)
    private final String zzag;

    @SafeParcelable.Field(getter = "getRequireUserMediation", m17520id = 8)
    private final boolean zzah;

    @SafeParcelable.Field(m17520id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int zzv;

    public static final class Builder {
        private boolean zzaa;
        private String[] zzab;
        private CredentialPickerConfig zzac;
        private CredentialPickerConfig zzad;
        private String zzag;
        private boolean zzae = false;
        private boolean zzah = false;
        private String zzaf = null;

        public final CredentialRequest build() {
            if (this.zzab == null) {
                this.zzab = new String[0];
            }
            if (this.zzaa || this.zzab.length != 0) {
                return new CredentialRequest(this);
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

        public final Builder setCredentialHintPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzad = credentialPickerConfig;
            return this;
        }

        public final Builder setCredentialPickerConfig(CredentialPickerConfig credentialPickerConfig) {
            this.zzac = credentialPickerConfig;
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

        public final Builder setPasswordLoginSupported(boolean z8) {
            this.zzaa = z8;
            return this;
        }

        public final Builder setServerClientId(String str) {
            this.zzaf = str;
            return this;
        }

        @Deprecated
        public final Builder setSupportsPasswordLogin(boolean z8) {
            return setPasswordLoginSupported(z8);
        }
    }

    @SafeParcelable.Constructor
    public CredentialRequest(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) boolean z8, @SafeParcelable.Param(m17521id = 2) String[] strArr, @SafeParcelable.Param(m17521id = 3) CredentialPickerConfig credentialPickerConfig, @SafeParcelable.Param(m17521id = 4) CredentialPickerConfig credentialPickerConfig2, @SafeParcelable.Param(m17521id = 5) boolean z9, @SafeParcelable.Param(m17521id = 6) String str, @SafeParcelable.Param(m17521id = 7) String str2, @SafeParcelable.Param(m17521id = 8) boolean z10) {
        this.zzv = i9;
        this.zzaa = z8;
        this.zzab = (String[]) Preconditions.checkNotNull(strArr);
        this.zzac = credentialPickerConfig == null ? new CredentialPickerConfig.Builder().build() : credentialPickerConfig;
        this.zzad = credentialPickerConfig2 == null ? new CredentialPickerConfig.Builder().build() : credentialPickerConfig2;
        if (i9 < 3) {
            this.zzae = true;
            this.zzaf = null;
            this.zzag = null;
        } else {
            this.zzae = z9;
            this.zzaf = str;
            this.zzag = str2;
        }
        this.zzah = z10;
    }

    public final String[] getAccountTypes() {
        return this.zzab;
    }

    public final Set<String> getAccountTypesSet() {
        return new HashSet(Arrays.asList(this.zzab));
    }

    public final CredentialPickerConfig getCredentialHintPickerConfig() {
        return this.zzad;
    }

    public final CredentialPickerConfig getCredentialPickerConfig() {
        return this.zzac;
    }

    public final String getIdTokenNonce() {
        return this.zzag;
    }

    public final String getServerClientId() {
        return this.zzaf;
    }

    @Deprecated
    public final boolean getSupportsPasswordLogin() {
        return isPasswordLoginSupported();
    }

    public final boolean isIdTokenRequested() {
        return this.zzae;
    }

    public final boolean isPasswordLoginSupported() {
        return this.zzaa;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isPasswordLoginSupported());
        SafeParcelWriter.writeStringArray(parcel, 2, getAccountTypes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getCredentialPickerConfig(), i9, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getCredentialHintPickerConfig(), i9, false);
        SafeParcelWriter.writeBoolean(parcel, 5, isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, getIdTokenNonce(), false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzah);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.zzv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private CredentialRequest(Builder builder) {
        this(4, builder.zzaa, builder.zzab, builder.zzac, builder.zzad, builder.zzae, builder.zzaf, builder.zzag, false);
    }
}
