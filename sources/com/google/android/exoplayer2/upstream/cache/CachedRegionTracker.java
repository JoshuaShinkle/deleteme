package com.google.android.exoplayer2.upstream.cache;

import android.util.Log;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.upstream.cache.Cache;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/* loaded from: classes.dex */
public final class CachedRegionTracker implements Cache.Listener {
    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final TreeSet<Region> regions = new TreeSet<>();
    private final Region lookupRegion = new Region(0, 0);

    public static class Region implements Comparable<Region> {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long j9, long j10) {
            this.startOffset = j9;
            this.endOffset = j10;
        }

        @Override // java.lang.Comparable
        public int compareTo(Region region) {
            long j9 = this.startOffset;
            long j10 = region.startOffset;
            if (j9 < j10) {
                return -1;
            }
            return j9 == j10 ? 0 : 1;
        }
    }

    public CachedRegionTracker(Cache cache, String str, ChunkIndex chunkIndex) {
        this.cache = cache;
        this.cacheKey = str;
        this.chunkIndex = chunkIndex;
        synchronized (this) {
            Iterator<CacheSpan> itDescendingIterator = cache.addListener(str, this).descendingIterator();
            while (itDescendingIterator.hasNext()) {
                mergeSpan(itDescendingIterator.next());
            }
        }
    }

    private void mergeSpan(CacheSpan cacheSpan) {
        long j9 = cacheSpan.position;
        Region region = new Region(j9, cacheSpan.length + j9);
        Region regionFloor = this.regions.floor(region);
        Region regionCeiling = this.regions.ceiling(region);
        boolean zRegionsConnect = regionsConnect(regionFloor, region);
        if (regionsConnect(region, regionCeiling)) {
            if (zRegionsConnect) {
                regionFloor.endOffset = regionCeiling.endOffset;
                regionFloor.endOffsetIndex = regionCeiling.endOffsetIndex;
            } else {
                region.endOffset = regionCeiling.endOffset;
                region.endOffsetIndex = regionCeiling.endOffsetIndex;
                this.regions.add(region);
            }
            this.regions.remove(regionCeiling);
            return;
        }
        if (!zRegionsConnect) {
            int iBinarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region.endOffset);
            if (iBinarySearch < 0) {
                iBinarySearch = (-iBinarySearch) - 2;
            }
            region.endOffsetIndex = iBinarySearch;
            this.regions.add(region);
            return;
        }
        regionFloor.endOffset = region.endOffset;
        int i9 = regionFloor.endOffsetIndex;
        while (true) {
            ChunkIndex chunkIndex = this.chunkIndex;
            if (i9 >= chunkIndex.length - 1) {
                break;
            }
            int i10 = i9 + 1;
            if (chunkIndex.offsets[i10] > regionFloor.endOffset) {
                break;
            } else {
                i9 = i10;
            }
        }
        regionFloor.endOffsetIndex = i9;
    }

    private boolean regionsConnect(Region region, Region region2) {
        return (region == null || region2 == null || region.endOffset != region2.startOffset) ? false : true;
    }

    public synchronized int getRegionEndTimeMs(long j9) {
        int i9;
        Region region = this.lookupRegion;
        region.startOffset = j9;
        Region regionFloor = this.regions.floor(region);
        if (regionFloor != null) {
            long j10 = regionFloor.endOffset;
            if (j9 <= j10 && (i9 = regionFloor.endOffsetIndex) != -1) {
                ChunkIndex chunkIndex = this.chunkIndex;
                if (i9 == chunkIndex.length - 1) {
                    if (j10 == chunkIndex.offsets[i9] + chunkIndex.sizes[i9]) {
                        return -2;
                    }
                }
                return (int) ((chunkIndex.timesUs[i9] + ((chunkIndex.durationsUs[i9] * (j10 - chunkIndex.offsets[i9])) / chunkIndex.sizes[i9])) / 1000);
            }
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache.Listener
    public synchronized void onSpanAdded(Cache cache, CacheSpan cacheSpan) {
        mergeSpan(cacheSpan);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache.Listener
    public synchronized void onSpanRemoved(Cache cache, CacheSpan cacheSpan) {
        long j9 = cacheSpan.position;
        Region region = new Region(j9, cacheSpan.length + j9);
        Region regionFloor = this.regions.floor(region);
        if (regionFloor == null) {
            Log.e(TAG, "Removed a span we were not aware of");
            return;
        }
        this.regions.remove(regionFloor);
        long j10 = regionFloor.startOffset;
        long j11 = region.startOffset;
        if (j10 < j11) {
            Region region2 = new Region(j10, j11);
            int iBinarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region2.endOffset);
            if (iBinarySearch < 0) {
                iBinarySearch = (-iBinarySearch) - 2;
            }
            region2.endOffsetIndex = iBinarySearch;
            this.regions.add(region2);
        }
        long j12 = regionFloor.endOffset;
        long j13 = region.endOffset;
        if (j12 > j13) {
            Region region3 = new Region(j13 + 1, j12);
            region3.endOffsetIndex = regionFloor.endOffsetIndex;
            this.regions.add(region3);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache.Listener
    public void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, this);
    }
}
