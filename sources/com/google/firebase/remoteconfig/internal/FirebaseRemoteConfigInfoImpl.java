package com.google.firebase.remoteconfig.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* loaded from: classes2.dex */
public class FirebaseRemoteConfigInfoImpl implements FirebaseRemoteConfigInfo {
    private final FirebaseRemoteConfigSettings configSettings;
    private final int lastFetchStatus;
    private final long lastSuccessfulFetchTimeInMillis;

    public static class Builder {
        private FirebaseRemoteConfigSettings builderConfigSettings;
        private int builderLastFetchStatus;
        private long builderLastSuccessfulFetchTimeInMillis;

        private Builder() {
        }

        public FirebaseRemoteConfigInfoImpl build() {
            return new FirebaseRemoteConfigInfoImpl(this.builderLastSuccessfulFetchTimeInMillis, this.builderLastFetchStatus, this.builderConfigSettings);
        }

        public Builder withConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
            this.builderConfigSettings = firebaseRemoteConfigSettings;
            return this;
        }

        public Builder withLastFetchStatus(int i9) {
            this.builderLastFetchStatus = i9;
            return this;
        }

        public Builder withLastSuccessfulFetchTimeInMillis(long j9) {
            this.builderLastSuccessfulFetchTimeInMillis = j9;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public FirebaseRemoteConfigSettings getConfigSettings() {
        return this.configSettings;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public long getFetchTimeMillis() {
        return this.lastSuccessfulFetchTimeInMillis;
    }

    @Override // com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo
    public int getLastFetchStatus() {
        return this.lastFetchStatus;
    }

    private FirebaseRemoteConfigInfoImpl(long j9, int i9, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.lastSuccessfulFetchTimeInMillis = j9;
        this.lastFetchStatus = i9;
        this.configSettings = firebaseRemoteConfigSettings;
    }
}
