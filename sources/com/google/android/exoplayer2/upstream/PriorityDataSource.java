package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;

/* loaded from: classes.dex */
public final class PriorityDataSource implements DataSource {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource upstream;

    public PriorityDataSource(DataSource dataSource, PriorityTaskManager priorityTaskManager, int i9) {
        this.upstream = (DataSource) Assertions.checkNotNull(dataSource);
        this.priorityTaskManager = (PriorityTaskManager) Assertions.checkNotNull(priorityTaskManager);
        this.priority = i9;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() {
        this.upstream.close();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.upstream.getUri();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.open(dataSpec);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i9, int i10) {
        this.priorityTaskManager.proceedOrThrow(this.priority);
        return this.upstream.read(bArr, i9, i10);
    }
}
