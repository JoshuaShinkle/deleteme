package p132m;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: m.e */
/* loaded from: classes.dex */
public class C5306e<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public C5306e(int i9) {
        if (i9 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i9;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int safeSizeOf(K k9, V v8) {
        int iSizeOf = sizeOf(k9, v8);
        if (iSizeOf >= 0) {
            return iSizeOf;
        }
        throw new IllegalStateException("Negative size: " + k9 + "=" + v8);
    }

    public V create(K k9) {
        return null;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public void entryRemoved(boolean z8, K k9, V v8, V v9) {
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final V get(K k9) {
        V v8;
        if (k9 == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v9 = this.map.get(k9);
            if (v9 != null) {
                this.hitCount++;
                return v9;
            }
            this.missCount++;
            V vCreate = create(k9);
            if (vCreate == null) {
                return null;
            }
            synchronized (this) {
                this.createCount++;
                v8 = (V) this.map.put(k9, vCreate);
                if (v8 != null) {
                    this.map.put(k9, v8);
                } else {
                    this.size += safeSizeOf(k9, vCreate);
                }
            }
            if (v8 != null) {
                entryRemoved(false, k9, vCreate, v8);
                return v8;
            }
            trimToSize(this.maxSize);
            return vCreate;
        }
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final V put(K k9, V v8) {
        V vPut;
        if (k9 == null || v8 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(k9, v8);
            vPut = this.map.put(k9, v8);
            if (vPut != null) {
                this.size -= safeSizeOf(k9, vPut);
            }
        }
        if (vPut != null) {
            entryRemoved(false, k9, vPut, v8);
        }
        trimToSize(this.maxSize);
        return vPut;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final V remove(K k9) {
        V vRemove;
        if (k9 == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            vRemove = this.map.remove(k9);
            if (vRemove != null) {
                this.size -= safeSizeOf(k9, vRemove);
            }
        }
        if (vRemove != null) {
            entryRemoved(false, k9, vRemove, null);
        }
        return vRemove;
    }

    public void resize(int i9) {
        if (i9 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = i9;
        }
        trimToSize(i9);
    }

    public final synchronized int size() {
        return this.size;
    }

    public int sizeOf(K k9, V v8) {
        return 1;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int i9;
        int i10;
        i9 = this.hitCount;
        i10 = this.missCount + i9;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i10 != 0 ? (i9 * 100) / i10 : 0));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void trimToSize(int i9) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.size >= 0 && (!this.map.isEmpty() || this.size == 0)) {
                    if (this.size <= i9 || this.map.isEmpty()) {
                        break;
                    }
                    Map.Entry<K, V> next = this.map.entrySet().iterator().next();
                    key = next.getKey();
                    value = next.getValue();
                    this.map.remove(key);
                    this.size -= safeSizeOf(key, value);
                    this.evictionCount++;
                } else {
                    break;
                }
            }
            entryRemoved(true, key, value, null);
        }
    }
}
