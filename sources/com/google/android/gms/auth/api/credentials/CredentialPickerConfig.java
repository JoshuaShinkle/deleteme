package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class(creator = "CredentialPickerConfigCreator")
/* loaded from: classes2.dex */
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CredentialPickerConfig> CREATOR = new zze();

    @SafeParcelable.Field(getter = "shouldShowCancelButton", m17520id = 2)
    private final boolean mShowCancelButton;

    @SafeParcelable.Field(m17520id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)
    private final int zzv;

    @SafeParcelable.Field(getter = "shouldShowAddAccountButton", m17520id = 1)
    private final boolean zzw;

    @SafeParcelable.Field(getter = "isForNewAccount", m17520id = 3)
    @Deprecated
    private final boolean zzx;

    @SafeParcelable.Field(getter = "getPromptInternalId", m17520id = 4)
    private final int zzy;

    public static class Builder {
        private boolean zzw = false;
        private boolean mShowCancelButton = true;
        private int zzz = 1;

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this);
        }

        @Deprecated
        public Builder setForNewAccount(boolean z8) {
            this.zzz = z8 ? 3 : 1;
            return this;
        }

        public Builder setPrompt(int i9) {
            this.zzz = i9;
            return this;
        }

        public Builder setShowAddAccountButton(boolean z8) {
            this.zzw = z8;
            return this;
        }

        public Builder setShowCancelButton(boolean z8) {
            this.mShowCancelButton = z8;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    @SafeParcelable.Constructor
    public CredentialPickerConfig(@SafeParcelable.Param(m17521id = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT) int i9, @SafeParcelable.Param(m17521id = 1) boolean z8, @SafeParcelable.Param(m17521id = 2) boolean z9, @SafeParcelable.Param(m17521id = 3) boolean z10, @SafeParcelable.Param(m17521id = 4) int i10) {
        this.zzv = i9;
        this.zzw = z8;
        this.mShowCancelButton = z9;
        if (i9 < 2) {
            this.zzx = z10;
            this.zzy = z10 ? 3 : 1;
        } else {
            this.zzx = i10 == 3;
            this.zzy = i10;
        }
    }

    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzy == 3;
    }

    public final boolean shouldShowAddAccountButton() {
        return this.zzw;
    }

    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i9) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzy);
        SafeParcelWriter.writeInt(parcel, CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.zzv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private CredentialPickerConfig(Builder builder) {
        this(2, builder.zzw, builder.mShowCancelButton, false, builder.zzz);
    }
}
