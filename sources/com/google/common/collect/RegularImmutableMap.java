package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final int ABSENT = -1;
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    private static final long serialVersionUID = 0;
    private final transient Object[] alternatingKeysAndValues;
    private final transient int[] hashTable;
    private final transient int size;

    public static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int keyOffset;
        private final transient ImmutableMap<K, V> map;
        private final transient int size;

        public EntrySet(ImmutableMap<K, V> immutableMap, Object[] objArr, int i9, int i10) {
            this.map = immutableMap;
            this.alternatingKeysAndValues = objArr;
            this.keyOffset = i9;
            this.size = i10;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.map.get(key));
        }

        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.RegularImmutableMap.EntrySet.1
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return EntrySet.this.size;
                }

                @Override // java.util.List
                public Map.Entry<K, V> get(int i9) {
                    Preconditions.checkElementIndex(i9, EntrySet.this.size);
                    int i10 = i9 * 2;
                    return new AbstractMap.SimpleImmutableEntry(EntrySet.this.alternatingKeysAndValues[EntrySet.this.keyOffset + i10], EntrySet.this.alternatingKeysAndValues[i10 + (EntrySet.this.keyOffset ^ 1)]);
                }
            };
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }
    }

    public static final class KeySet<K> extends ImmutableSet<K> {
        private final transient ImmutableList<K> list;
        private final transient ImmutableMap<K, ?> map;

        public KeySet(ImmutableMap<K, ?> immutableMap, ImmutableList<K> immutableList) {
            this.map = immutableMap;
            this.list = immutableList;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public ImmutableList<K> asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.map.get(obj) != null;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }
    }

    public static final class KeysOrValuesAsList extends ImmutableList<Object> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        public KeysOrValuesAsList(Object[] objArr, int i9, int i10) {
            this.alternatingKeysAndValues = objArr;
            this.offset = i9;
            this.size = i10;
        }

        @Override // java.util.List
        public Object get(int i9) {
            Preconditions.checkElementIndex(i9, this.size);
            return this.alternatingKeysAndValues[(i9 * 2) + this.offset];
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }
    }

    private RegularImmutableMap(int[] iArr, Object[] objArr, int i9) {
        this.hashTable = iArr;
        this.alternatingKeysAndValues = objArr;
        this.size = i9;
    }

    public static <K, V> RegularImmutableMap<K, V> create(int i9, Object[] objArr) {
        if (i9 == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (i9 == 1) {
            CollectPreconditions.checkEntryNotNull(objArr[0], objArr[1]);
            return new RegularImmutableMap<>(null, objArr, 1);
        }
        Preconditions.checkPositionIndex(i9, objArr.length >> 1);
        return new RegularImmutableMap<>(createHashTable(objArr, i9, ImmutableSet.chooseTableSize(i9), 0), objArr, i9);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0034, code lost:
    
        r12[r7] = r5;
        r3 = r3 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int[] createHashTable(Object[] objArr, int i9, int i10, int i11) {
        if (i9 == 1) {
            CollectPreconditions.checkEntryNotNull(objArr[i11], objArr[i11 ^ 1]);
            return null;
        }
        int i12 = i10 - 1;
        int[] iArr = new int[i10];
        Arrays.fill(iArr, -1);
        int i13 = 0;
        while (i13 < i9) {
            int i14 = i13 * 2;
            int i15 = i14 + i11;
            Object obj = objArr[i15];
            Object obj2 = objArr[i14 + (i11 ^ 1)];
            CollectPreconditions.checkEntryNotNull(obj, obj2);
            int iSmear = Hashing.smear(obj.hashCode());
            while (true) {
                int i16 = iSmear & i12;
                int i17 = iArr[i16];
                if (i17 == -1) {
                    break;
                }
                if (objArr[i17].equals(obj)) {
                    throw new IllegalArgumentException("Multiple entries with same key: " + obj + "=" + obj2 + " and " + objArr[i17] + "=" + objArr[i17 ^ 1]);
                }
                iSmear = i16 + 1;
            }
        }
        return iArr;
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableCollection<V> createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        return (V) get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, obj);
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    public static Object get(int[] iArr, Object[] objArr, int i9, int i10, Object obj) {
        if (obj == null) {
            return null;
        }
        if (i9 == 1) {
            if (objArr[i10].equals(obj)) {
                return objArr[i10 ^ 1];
            }
            return null;
        }
        if (iArr == null) {
            return null;
        }
        int length = iArr.length - 1;
        int iSmear = Hashing.smear(obj.hashCode());
        while (true) {
            int i11 = iSmear & length;
            int i12 = iArr[i11];
            if (i12 == -1) {
                return null;
            }
            if (objArr[i12].equals(obj)) {
                return objArr[i12 ^ 1];
            }
            iSmear = i11 + 1;
        }
    }
}
