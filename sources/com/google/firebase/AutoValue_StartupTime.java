package com.google.firebase;

/* loaded from: classes2.dex */
final class AutoValue_StartupTime extends StartupTime {
    private final long elapsedRealtime;
    private final long epochMillis;
    private final long uptimeMillis;

    public AutoValue_StartupTime(long j9, long j10, long j11) {
        this.epochMillis = j9;
        this.elapsedRealtime = j10;
        this.uptimeMillis = j11;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StartupTime)) {
            return false;
        }
        StartupTime startupTime = (StartupTime) obj;
        return this.epochMillis == startupTime.getEpochMillis() && this.elapsedRealtime == startupTime.getElapsedRealtime() && this.uptimeMillis == startupTime.getUptimeMillis();
    }

    @Override // com.google.firebase.StartupTime
    public long getElapsedRealtime() {
        return this.elapsedRealtime;
    }

    @Override // com.google.firebase.StartupTime
    public long getEpochMillis() {
        return this.epochMillis;
    }

    @Override // com.google.firebase.StartupTime
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public int hashCode() {
        long j9 = this.epochMillis;
        long j10 = this.elapsedRealtime;
        int i9 = (((((int) (j9 ^ (j9 >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j10 ^ (j10 >>> 32)))) * 1000003;
        long j11 = this.uptimeMillis;
        return i9 ^ ((int) ((j11 >>> 32) ^ j11));
    }

    public String toString() {
        return "StartupTime{epochMillis=" + this.epochMillis + ", elapsedRealtime=" + this.elapsedRealtime + ", uptimeMillis=" + this.uptimeMillis + "}";
    }
}
