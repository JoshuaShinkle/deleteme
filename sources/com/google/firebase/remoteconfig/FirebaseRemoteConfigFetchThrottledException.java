package com.google.firebase.remoteconfig;

/* loaded from: classes2.dex */
public class FirebaseRemoteConfigFetchThrottledException extends FirebaseRemoteConfigFetchException {
    private final long throttleEndTimeMillis;

    public FirebaseRemoteConfigFetchThrottledException(long j9) {
        this("Fetch was throttled.", j9);
    }

    public long getThrottleEndTimeMillis() {
        return this.throttleEndTimeMillis;
    }

    public FirebaseRemoteConfigFetchThrottledException(String str, long j9) {
        super(str);
        this.throttleEndTimeMillis = j9;
    }
}
