package com.google.firebase.crashlytics.internal.settings;

/* loaded from: classes2.dex */
public class Settings {
    public final int cacheDuration;
    public final long expiresAtMillis;
    public final FeatureFlagData featureFlagData;
    public final double onDemandBackoffBase;
    public final int onDemandBackoffStepDurationSeconds;
    public final double onDemandUploadRatePerMinute;
    public final SessionData sessionData;
    public final int settingsVersion;

    public static class FeatureFlagData {
        public final boolean collectAnrs;
        public final boolean collectBuildIds;
        public final boolean collectReports;

        public FeatureFlagData(boolean z8, boolean z9, boolean z10) {
            this.collectReports = z8;
            this.collectAnrs = z9;
            this.collectBuildIds = z10;
        }
    }

    public static class SessionData {
        public final int maxCompleteSessionsCount;
        public final int maxCustomExceptionEvents;

        public SessionData(int i9, int i10) {
            this.maxCustomExceptionEvents = i9;
            this.maxCompleteSessionsCount = i10;
        }
    }

    public Settings(long j9, SessionData sessionData, FeatureFlagData featureFlagData, int i9, int i10, double d9, double d10, int i11) {
        this.expiresAtMillis = j9;
        this.sessionData = sessionData;
        this.featureFlagData = featureFlagData;
        this.settingsVersion = i9;
        this.cacheDuration = i10;
        this.onDemandUploadRatePerMinute = d9;
        this.onDemandBackoffBase = d10;
        this.onDemandBackoffStepDurationSeconds = i11;
    }

    public boolean isExpired(long j9) {
        return this.expiresAtMillis < j9;
    }
}
