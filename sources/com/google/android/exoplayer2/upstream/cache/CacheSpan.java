package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.C3322C;
import java.io.File;

/* loaded from: classes.dex */
public class CacheSpan implements Comparable<CacheSpan> {
    public final File file;
    public final boolean isCached;
    public final String key;
    public final long lastAccessTimestamp;
    public final long length;
    public final long position;

    public CacheSpan(String str, long j9, long j10) {
        this(str, j9, j10, C3322C.TIME_UNSET, null);
    }

    public boolean isHoleSpan() {
        return !this.isCached;
    }

    public boolean isOpenEnded() {
        return this.length == -1;
    }

    public CacheSpan(String str, long j9, long j10, long j11, File file) {
        this.key = str;
        this.position = j9;
        this.length = j10;
        this.isCached = file != null;
        this.file = file;
        this.lastAccessTimestamp = j11;
    }

    @Override // java.lang.Comparable
    public int compareTo(CacheSpan cacheSpan) {
        if (!this.key.equals(cacheSpan.key)) {
            return this.key.compareTo(cacheSpan.key);
        }
        long j9 = this.position - cacheSpan.position;
        if (j9 == 0) {
            return 0;
        }
        return j9 < 0 ? -1 : 1;
    }
}
