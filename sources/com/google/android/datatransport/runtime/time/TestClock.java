package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public class TestClock implements Clock {
    private final AtomicLong timestamp;

    public TestClock(long j9) {
        this.timestamp = new AtomicLong(j9);
    }

    public void advance(long j9) {
        if (j9 < 0) {
            throw new IllegalArgumentException("cannot advance time backwards.");
        }
        this.timestamp.addAndGet(j9);
    }

    @Override // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return this.timestamp.get();
    }

    public void tick() {
        advance(1L);
    }
}
