package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TreeSet;

/* loaded from: classes.dex */
final class CachedContent {
    private final TreeSet<SimpleCacheSpan> cachedSpans;

    /* renamed from: id */
    public final int f15325id;
    public final String key;
    private long length;
    private boolean locked;

    public CachedContent(DataInputStream dataInputStream) {
        this(dataInputStream.readInt(), dataInputStream.readUTF(), dataInputStream.readLong());
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public long getCachedBytesLength(long j9, long j10) {
        SimpleCacheSpan span = getSpan(j9);
        if (span.isHoleSpan()) {
            return -Math.min(span.isOpenEnded() ? Long.MAX_VALUE : span.length, j10);
        }
        long j11 = j9 + j10;
        long jMax = span.position + span.length;
        if (jMax < j11) {
            for (SimpleCacheSpan simpleCacheSpan : this.cachedSpans.tailSet(span, false)) {
                long j12 = simpleCacheSpan.position;
                if (j12 > jMax) {
                    break;
                }
                jMax = Math.max(jMax, j12 + simpleCacheSpan.length);
                if (jMax >= j11) {
                    break;
                }
            }
        }
        return Math.min(jMax - j9, j10);
    }

    public long getLength() {
        return this.length;
    }

    public SimpleCacheSpan getSpan(long j9) {
        SimpleCacheSpan simpleCacheSpanCreateLookup = SimpleCacheSpan.createLookup(this.key, j9);
        SimpleCacheSpan simpleCacheSpanFloor = this.cachedSpans.floor(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanFloor != null && simpleCacheSpanFloor.position + simpleCacheSpanFloor.length > j9) {
            return simpleCacheSpanFloor;
        }
        SimpleCacheSpan simpleCacheSpanCeiling = this.cachedSpans.ceiling(simpleCacheSpanCreateLookup);
        return simpleCacheSpanCeiling == null ? SimpleCacheSpan.createOpenHole(this.key, j9) : SimpleCacheSpan.createClosedHole(this.key, j9, simpleCacheSpanCeiling.position - j9);
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public int headerHashCode() {
        int iHashCode = ((this.f15325id * 31) + this.key.hashCode()) * 31;
        long j9 = this.length;
        return iHashCode + ((int) (j9 ^ (j9 >>> 32)));
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean isLocked() {
        return this.locked;
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (!this.cachedSpans.remove(cacheSpan)) {
            return false;
        }
        cacheSpan.file.delete();
        return true;
    }

    public void setLength(long j9) {
        this.length = j9;
    }

    public void setLocked(boolean z8) {
        this.locked = z8;
    }

    public SimpleCacheSpan touch(SimpleCacheSpan simpleCacheSpan) throws Cache.CacheException {
        Assertions.checkState(this.cachedSpans.remove(simpleCacheSpan));
        SimpleCacheSpan simpleCacheSpanCopyWithUpdatedLastAccessTime = simpleCacheSpan.copyWithUpdatedLastAccessTime(this.f15325id);
        if (simpleCacheSpan.file.renameTo(simpleCacheSpanCopyWithUpdatedLastAccessTime.file)) {
            this.cachedSpans.add(simpleCacheSpanCopyWithUpdatedLastAccessTime);
            return simpleCacheSpanCopyWithUpdatedLastAccessTime;
        }
        throw new Cache.CacheException("Renaming of " + simpleCacheSpan.file + " to " + simpleCacheSpanCopyWithUpdatedLastAccessTime.file + " failed.");
    }

    public void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f15325id);
        dataOutputStream.writeUTF(this.key);
        dataOutputStream.writeLong(this.length);
    }

    public CachedContent(int i9, String str, long j9) {
        this.f15325id = i9;
        this.key = str;
        this.length = j9;
        this.cachedSpans = new TreeSet<>();
    }
}
