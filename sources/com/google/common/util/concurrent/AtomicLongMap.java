package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@GwtCompatible
/* loaded from: classes2.dex */
public final class AtomicLongMap<K> implements Serializable {
    private transient Map<K, Long> asMap;
    private final ConcurrentHashMap<K, AtomicLong> map;

    private AtomicLongMap(ConcurrentHashMap<K, AtomicLong> concurrentHashMap) {
        this.map = (ConcurrentHashMap) Preconditions.checkNotNull(concurrentHashMap);
    }

    public static <K> AtomicLongMap<K> create() {
        return new AtomicLongMap<>(new ConcurrentHashMap());
    }

    private Map<K, Long> createAsMap() {
        return Collections.unmodifiableMap(Maps.transformValues(this.map, new Function<AtomicLong, Long>() { // from class: com.google.common.util.concurrent.AtomicLongMap.1
            @Override // com.google.common.base.Function
            public Long apply(AtomicLong atomicLong) {
                return Long.valueOf(atomicLong.get());
            }
        }));
    }

    @CanIgnoreReturnValue
    public long addAndGet(K k9, long j9) {
        AtomicLong atomicLongPutIfAbsent;
        long j10;
        long j11;
        do {
            atomicLongPutIfAbsent = this.map.get(k9);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(k9, new AtomicLong(j9))) == null) {
                return j9;
            }
            do {
                j10 = atomicLongPutIfAbsent.get();
                if (j10 != 0) {
                    j11 = j10 + j9;
                }
            } while (!atomicLongPutIfAbsent.compareAndSet(j10, j11));
            return j11;
        } while (!this.map.replace(k9, atomicLongPutIfAbsent, new AtomicLong(j9)));
        return j9;
    }

    public Map<K, Long> asMap() {
        Map<K, Long> map = this.asMap;
        if (map != null) {
            return map;
        }
        Map<K, Long> mapCreateAsMap = createAsMap();
        this.asMap = mapCreateAsMap;
        return mapCreateAsMap;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @CanIgnoreReturnValue
    public long decrementAndGet(K k9) {
        return addAndGet(k9, -1L);
    }

    public long get(K k9) {
        AtomicLong atomicLong = this.map.get(k9);
        if (atomicLong == null) {
            return 0L;
        }
        return atomicLong.get();
    }

    @CanIgnoreReturnValue
    public long getAndAdd(K k9, long j9) {
        AtomicLong atomicLongPutIfAbsent;
        long j10;
        do {
            atomicLongPutIfAbsent = this.map.get(k9);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(k9, new AtomicLong(j9))) == null) {
                return 0L;
            }
            do {
                j10 = atomicLongPutIfAbsent.get();
                if (j10 == 0) {
                }
            } while (!atomicLongPutIfAbsent.compareAndSet(j10, j10 + j9));
            return j10;
        } while (!this.map.replace(k9, atomicLongPutIfAbsent, new AtomicLong(j9)));
        return 0L;
    }

    @CanIgnoreReturnValue
    public long getAndDecrement(K k9) {
        return getAndAdd(k9, -1L);
    }

    @CanIgnoreReturnValue
    public long getAndIncrement(K k9) {
        return getAndAdd(k9, 1L);
    }

    @CanIgnoreReturnValue
    public long incrementAndGet(K k9) {
        return addAndGet(k9, 1L);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @CanIgnoreReturnValue
    public long put(K k9, long j9) {
        AtomicLong atomicLongPutIfAbsent;
        long j10;
        do {
            atomicLongPutIfAbsent = this.map.get(k9);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(k9, new AtomicLong(j9))) == null) {
                return 0L;
            }
            do {
                j10 = atomicLongPutIfAbsent.get();
                if (j10 == 0) {
                }
            } while (!atomicLongPutIfAbsent.compareAndSet(j10, j9));
            return j10;
        } while (!this.map.replace(k9, atomicLongPutIfAbsent, new AtomicLong(j9)));
        return 0L;
    }

    public void putAll(Map<? extends K, ? extends Long> map) {
        for (Map.Entry<? extends K, ? extends Long> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue().longValue());
        }
    }

    public long putIfAbsent(K k9, long j9) {
        AtomicLong atomicLongPutIfAbsent;
        do {
            atomicLongPutIfAbsent = this.map.get(k9);
            if (atomicLongPutIfAbsent == null && (atomicLongPutIfAbsent = this.map.putIfAbsent(k9, new AtomicLong(j9))) == null) {
                return 0L;
            }
            long j10 = atomicLongPutIfAbsent.get();
            if (j10 != 0) {
                return j10;
            }
        } while (!this.map.replace(k9, atomicLongPutIfAbsent, new AtomicLong(j9)));
        return 0L;
    }

    @CanIgnoreReturnValue
    public long remove(K k9) {
        long j9;
        AtomicLong atomicLong = this.map.get(k9);
        if (atomicLong == null) {
            return 0L;
        }
        do {
            j9 = atomicLong.get();
            if (j9 == 0) {
                break;
            }
        } while (!atomicLong.compareAndSet(j9, 0L));
        this.map.remove(k9, atomicLong);
        return j9;
    }

    public void removeAllZeros() {
        Iterator<Map.Entry<K, AtomicLong>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            AtomicLong value = it.next().getValue();
            if (value != null && value.get() == 0) {
                it.remove();
            }
        }
    }

    @CanIgnoreReturnValue
    @Beta
    public boolean removeIfZero(K k9) {
        return remove(k9, 0L);
    }

    public boolean replace(K k9, long j9, long j10) {
        if (j9 == 0) {
            return putIfAbsent(k9, j10) == 0;
        }
        AtomicLong atomicLong = this.map.get(k9);
        if (atomicLong == null) {
            return false;
        }
        return atomicLong.compareAndSet(j9, j10);
    }

    public int size() {
        return this.map.size();
    }

    public long sum() {
        Iterator<AtomicLong> it = this.map.values().iterator();
        long j9 = 0;
        while (it.hasNext()) {
            j9 += it.next().get();
        }
        return j9;
    }

    public String toString() {
        return this.map.toString();
    }

    public static <K> AtomicLongMap<K> create(Map<? extends K, ? extends Long> map) {
        AtomicLongMap<K> atomicLongMapCreate = create();
        atomicLongMapCreate.putAll(map);
        return atomicLongMapCreate;
    }

    public boolean remove(K k9, long j9) {
        AtomicLong atomicLong = this.map.get(k9);
        if (atomicLong == null) {
            return false;
        }
        long j10 = atomicLong.get();
        if (j10 != j9) {
            return false;
        }
        if (j10 != 0 && !atomicLong.compareAndSet(j10, 0L)) {
            return false;
        }
        this.map.remove(k9, atomicLong);
        return true;
    }
}
