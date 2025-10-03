package com.google.android.exoplayer2.upstream.cache;

import android.os.ConditionVariable;
import android.util.Log;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes.dex */
public final class SimpleCache implements Cache {
    private static final String TAG = "SimpleCache";
    private final File cacheDir;
    private final CacheEvictor evictor;
    private final CachedContentIndex index;
    private final HashMap<String, ArrayList<Cache.Listener>> listeners;
    private long totalSpace;

    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this(file, cacheEvictor, null, false);
    }

    private void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.index.getOrAdd(simpleCacheSpan.key).addSpan(simpleCacheSpan);
        this.totalSpace += simpleCacheSpan.length;
        notifySpanAdded(simpleCacheSpan);
    }

    private SimpleCacheSpan getSpan(String str, long j9) throws Throwable {
        SimpleCacheSpan span;
        CachedContent cachedContent = this.index.get(str);
        if (cachedContent == null) {
            return SimpleCacheSpan.createOpenHole(str, j9);
        }
        while (true) {
            span = cachedContent.getSpan(j9);
            if (!span.isCached || span.file.exists()) {
                break;
            }
            removeStaleSpansAndCachedContents();
        }
        return span;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initialize() throws Throwable {
        if (!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
            return;
        }
        this.index.load();
        File[] fileArrListFiles = this.cacheDir.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (!file.getName().equals(CachedContentIndex.FILE_NAME)) {
                SimpleCacheSpan simpleCacheSpanCreateCacheEntry = file.length() > 0 ? SimpleCacheSpan.createCacheEntry(file, this.index) : null;
                if (simpleCacheSpanCreateCacheEntry != null) {
                    addSpan(simpleCacheSpanCreateCacheEntry);
                } else {
                    file.delete();
                }
            }
        }
        this.index.removeEmpty();
        try {
            this.index.store();
        } catch (Cache.CacheException e9) {
            Log.e(TAG, "Storing index file failed", e9);
        }
    }

    private void notifySpanAdded(SimpleCacheSpan simpleCacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanAdded(this, simpleCacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, simpleCacheSpan);
    }

    private void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    private void notifySpanTouched(SimpleCacheSpan simpleCacheSpan, CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanTouched(this, simpleCacheSpan, cacheSpan);
            }
        }
        this.evictor.onSpanTouched(this, simpleCacheSpan, cacheSpan);
    }

    private void removeSpan(CacheSpan cacheSpan, boolean z8) {
        CachedContent cachedContent = this.index.get(cacheSpan.key);
        if (cachedContent == null || !cachedContent.removeSpan(cacheSpan)) {
            return;
        }
        this.totalSpace -= cacheSpan.length;
        if (z8) {
            try {
                this.index.maybeRemove(cachedContent.key);
                this.index.store();
            } finally {
                notifySpanRemoved(cacheSpan);
            }
        }
    }

    private void removeStaleSpansAndCachedContents() throws Throwable {
        ArrayList arrayList = new ArrayList();
        Iterator<CachedContent> it = this.index.getAll().iterator();
        while (it.hasNext()) {
            Iterator<SimpleCacheSpan> it2 = it.next().getSpans().iterator();
            while (it2.hasNext()) {
                SimpleCacheSpan next = it2.next();
                if (!next.file.exists()) {
                    arrayList.add(next);
                }
            }
        }
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            removeSpan((CacheSpan) arrayList.get(i9), false);
        }
        this.index.removeEmpty();
        this.index.store();
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized NavigableSet<CacheSpan> addListener(String str, Cache.Listener listener) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.listeners.put(str, arrayList);
        }
        arrayList.add(listener);
        return getCachedSpans(str);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void commitFile(File file) {
        SimpleCacheSpan simpleCacheSpanCreateCacheEntry = SimpleCacheSpan.createCacheEntry(file, this.index);
        boolean z8 = true;
        Assertions.checkState(simpleCacheSpanCreateCacheEntry != null);
        CachedContent cachedContent = this.index.get(simpleCacheSpanCreateCacheEntry.key);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isLocked());
        if (file.exists()) {
            if (file.length() == 0) {
                file.delete();
                return;
            }
            Long lValueOf = Long.valueOf(cachedContent.getLength());
            if (lValueOf.longValue() != -1) {
                if (simpleCacheSpanCreateCacheEntry.position + simpleCacheSpanCreateCacheEntry.length > lValueOf.longValue()) {
                    z8 = false;
                }
                Assertions.checkState(z8);
            }
            addSpan(simpleCacheSpanCreateCacheEntry);
            this.index.store();
            notifyAll();
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getCacheSpace() {
        return this.totalSpace;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getCachedLength(String str, long j9, long j10) {
        CachedContent cachedContent;
        cachedContent = this.index.get(str);
        return cachedContent != null ? cachedContent.getCachedBytesLength(j9, j10) : -j10;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        CachedContent cachedContent;
        cachedContent = this.index.get(str);
        return (cachedContent == null || cachedContent.isEmpty()) ? new TreeSet() : new TreeSet((Collection) cachedContent.getSpans());
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized long getContentLength(String str) {
        return this.index.getContentLength(str);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized Set<String> getKeys() {
        return new HashSet(this.index.getKeys());
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean isCached(String str, long j9, long j10) {
        boolean z8;
        CachedContent cachedContent = this.index.get(str);
        if (cachedContent != null) {
            z8 = cachedContent.getCachedBytesLength(j9, j10) >= j10;
        }
        return z8;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        CachedContent cachedContent = this.index.get(cacheSpan.key);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isLocked());
        cachedContent.setLocked(false);
        this.index.maybeRemove(cachedContent.key);
        notifyAll();
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void removeListener(String str, Cache.Listener listener) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
        if (arrayList != null) {
            arrayList.remove(listener);
            if (arrayList.isEmpty()) {
                this.listeners.remove(str);
            }
        }
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void setContentLength(String str, long j9) {
        this.index.setContentLength(str, j9);
        this.index.store();
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized File startFile(String str, long j9, long j10) {
        CachedContent cachedContent;
        cachedContent = this.index.get(str);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isLocked());
        if (!this.cacheDir.exists()) {
            removeStaleSpansAndCachedContents();
            this.cacheDir.mkdirs();
        }
        this.evictor.onStartFile(this, str, j9, j10);
        return SimpleCacheSpan.getCacheFile(this.cacheDir, cachedContent.f15325id, j9, System.currentTimeMillis());
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, byte[] bArr) {
        this(file, cacheEvictor, bArr, bArr != null);
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized SimpleCacheSpan startReadWrite(String str, long j9) {
        SimpleCacheSpan simpleCacheSpanStartReadWriteNonBlocking;
        while (true) {
            simpleCacheSpanStartReadWriteNonBlocking = startReadWriteNonBlocking(str, j9);
            if (simpleCacheSpanStartReadWriteNonBlocking == null) {
                wait();
            }
        }
        return simpleCacheSpanStartReadWriteNonBlocking;
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized SimpleCacheSpan startReadWriteNonBlocking(String str, long j9) {
        SimpleCacheSpan span = getSpan(str, j9);
        if (span.isCached) {
            SimpleCacheSpan simpleCacheSpan = this.index.get(str).touch(span);
            notifySpanTouched(span, simpleCacheSpan);
            return simpleCacheSpan;
        }
        CachedContent orAdd = this.index.getOrAdd(str);
        if (orAdd.isLocked()) {
            return null;
        }
        orAdd.setLocked(true);
        return span;
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, byte[] bArr, boolean z8) {
        this(file, cacheEvictor, new CachedContentIndex(file, bArr, z8));
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, CachedContentIndex cachedContentIndex) {
        this.totalSpace = 0L;
        this.cacheDir = file;
        this.evictor = cacheEvictor;
        this.index = cachedContentIndex;
        this.listeners = new HashMap<>();
        final ConditionVariable conditionVariable = new ConditionVariable();
        new Thread("SimpleCache.initialize()") { // from class: com.google.android.exoplayer2.upstream.cache.SimpleCache.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (SimpleCache.this) {
                    conditionVariable.open();
                    SimpleCache.this.initialize();
                    SimpleCache.this.evictor.onCacheInitialized();
                }
            }
        }.start();
        conditionVariable.block();
    }

    @Override // com.google.android.exoplayer2.upstream.cache.Cache
    public synchronized void removeSpan(CacheSpan cacheSpan) {
        removeSpan(cacheSpan, true);
    }
}
