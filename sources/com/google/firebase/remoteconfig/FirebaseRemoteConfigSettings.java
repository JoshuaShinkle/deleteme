package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;

/* loaded from: classes2.dex */
public class FirebaseRemoteConfigSettings {
    private final boolean enableDeveloperMode;
    private final long fetchTimeoutInSeconds;
    private final long minimumFetchInterval;

    public static class Builder {
        private boolean enableDeveloperMode = false;
        private long fetchTimeoutInSeconds = 60;
        private long minimumFetchInterval = ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS;

        public FirebaseRemoteConfigSettings build() {
            return new FirebaseRemoteConfigSettings(this);
        }

        public long getFetchTimeoutInSeconds() {
            return this.fetchTimeoutInSeconds;
        }

        public long getMinimumFetchIntervalInSeconds() {
            return this.minimumFetchInterval;
        }

        @Deprecated
        public Builder setDeveloperModeEnabled(boolean z8) {
            this.enableDeveloperMode = z8;
            return this;
        }

        public Builder setFetchTimeoutInSeconds(long j9) {
            if (j9 < 0) {
                throw new IllegalArgumentException(String.format("Fetch connection timeout has to be a non-negative number. %d is an invalid argument", Long.valueOf(j9)));
            }
            this.fetchTimeoutInSeconds = j9;
            return this;
        }

        public Builder setMinimumFetchIntervalInSeconds(long j9) {
            if (j9 >= 0) {
                this.minimumFetchInterval = j9;
                return this;
            }
            throw new IllegalArgumentException("Minimum interval between fetches has to be a non-negative number. " + j9 + " is an invalid argument");
        }
    }

    public long getFetchTimeoutInSeconds() {
        return this.fetchTimeoutInSeconds;
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.minimumFetchInterval;
    }

    @Deprecated
    public boolean isDeveloperModeEnabled() {
        return this.enableDeveloperMode;
    }

    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.setDeveloperModeEnabled(isDeveloperModeEnabled());
        builder.setFetchTimeoutInSeconds(getFetchTimeoutInSeconds());
        builder.setMinimumFetchIntervalInSeconds(getMinimumFetchIntervalInSeconds());
        return builder;
    }

    private FirebaseRemoteConfigSettings(Builder builder) {
        this.enableDeveloperMode = builder.enableDeveloperMode;
        this.fetchTimeoutInSeconds = builder.fetchTimeoutInSeconds;
        this.minimumFetchInterval = builder.minimumFetchInterval;
    }
}
