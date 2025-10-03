package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.backends.BackendResponse;

/* loaded from: classes.dex */
final class AutoValue_BackendResponse extends BackendResponse {
    private final long nextRequestWaitMillis;
    private final BackendResponse.Status status;

    public AutoValue_BackendResponse(BackendResponse.Status status, long j9) {
        if (status == null) {
            throw new NullPointerException("Null status");
        }
        this.status = status;
        this.nextRequestWaitMillis = j9;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BackendResponse)) {
            return false;
        }
        BackendResponse backendResponse = (BackendResponse) obj;
        return this.status.equals(backendResponse.getStatus()) && this.nextRequestWaitMillis == backendResponse.getNextRequestWaitMillis();
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public long getNextRequestWaitMillis() {
        return this.nextRequestWaitMillis;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendResponse
    public BackendResponse.Status getStatus() {
        return this.status;
    }

    public int hashCode() {
        int iHashCode = (this.status.hashCode() ^ 1000003) * 1000003;
        long j9 = this.nextRequestWaitMillis;
        return iHashCode ^ ((int) (j9 ^ (j9 >>> 32)));
    }

    public String toString() {
        return "BackendResponse{status=" + this.status + ", nextRequestWaitMillis=" + this.nextRequestWaitMillis + "}";
    }
}
