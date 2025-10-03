package com.google.firebase.installations.remote;

import com.google.firebase.installations.Utils;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
class RequestLimiter {
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS = TimeUnit.HOURS.toMillis(24);
    private static final long MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS = TimeUnit.MINUTES.toMillis(30);
    private int attemptCount;
    private long nextRequestTime;
    private final Utils utils;

    public RequestLimiter(Utils utils) {
        this.utils = utils;
    }

    private synchronized long getBackoffDuration(int i9) {
        if (isRetryableError(i9)) {
            return (long) Math.min(Math.pow(2.0d, this.attemptCount) + this.utils.getRandomDelayForSyncPrevention(), MAXIMUM_BACKOFF_DURATION_FOR_SERVER_ERRORS);
        }
        return MAXIMUM_BACKOFF_DURATION_FOR_CONFIGURATION_ERRORS;
    }

    private static boolean isRetryableError(int i9) {
        return i9 == 429 || (i9 >= 500 && i9 < 600);
    }

    private static boolean isSuccessfulOrRequiresNewFidCreation(int i9) {
        return (i9 >= 200 && i9 < 300) || i9 == 401 || i9 == 404;
    }

    private synchronized void resetBackoffStrategy() {
        this.attemptCount = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean isRequestAllowed() {
        boolean z8;
        if (this.attemptCount != 0) {
            z8 = this.utils.currentTimeInMillis() > this.nextRequestTime;
        }
        return z8;
    }

    public synchronized void setNextRequestTime(int i9) {
        if (isSuccessfulOrRequiresNewFidCreation(i9)) {
            resetBackoffStrategy();
            return;
        }
        this.attemptCount++;
        this.nextRequestTime = this.utils.currentTimeInMillis() + getBackoffDuration(i9);
    }

    public RequestLimiter() {
        this.utils = Utils.getInstance();
    }
}
