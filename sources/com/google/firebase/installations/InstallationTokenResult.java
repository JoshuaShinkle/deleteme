package com.google.firebase.installations;

import com.google.auto.value.AutoValue;
import com.google.firebase.installations.AutoValue_InstallationTokenResult;

@AutoValue
/* loaded from: classes2.dex */
public abstract class InstallationTokenResult {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract InstallationTokenResult build();

        public abstract Builder setToken(String str);

        public abstract Builder setTokenCreationTimestamp(long j9);

        public abstract Builder setTokenExpirationTimestamp(long j9);
    }

    public static Builder builder() {
        return new AutoValue_InstallationTokenResult.Builder();
    }

    public abstract String getToken();

    public abstract long getTokenCreationTimestamp();

    public abstract long getTokenExpirationTimestamp();

    public abstract Builder toBuilder();
}
