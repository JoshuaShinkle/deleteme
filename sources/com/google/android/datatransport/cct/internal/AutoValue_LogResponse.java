package com.google.android.datatransport.cct.internal;

/* loaded from: classes.dex */
final class AutoValue_LogResponse extends LogResponse {
    private final long nextRequestWaitMillis;

    public AutoValue_LogResponse(long j9) {
        this.nextRequestWaitMillis = j9;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof LogResponse) && this.nextRequestWaitMillis == ((LogResponse) obj).getNextRequestWaitMillis();
    }

    @Override // com.google.android.datatransport.cct.internal.LogResponse
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    public int hashCode() {
        long j9 = this.nextRequestWaitMillis;
        return ((int) (j9 ^ (j9 >>> 32))) ^ 1000003;
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }
}
