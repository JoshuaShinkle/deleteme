package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes2.dex */
class EnumCountHashMap<K extends Enum<K>> extends AbstractObjectCountMap<K> {
    private final Class<K> keyType;

    public abstract class EnumIterator<T> extends AbstractObjectCountMap<K>.Itr<T> {
        int nextIndex;

        private EnumIterator() {
            super();
            this.nextIndex = -1;
        }

        @Override // com.google.common.collect.AbstractObjectCountMap.Itr, java.util.Iterator
        public boolean hasNext() {
            int i9;
            int[] iArr;
            while (true) {
                i9 = this.index;
                iArr = EnumCountHashMap.this.values;
                if (i9 >= iArr.length || iArr[i9] > 0) {
                    break;
                }
                this.index = i9 + 1;
            }
            return i9 != iArr.length;
        }

        @Override // com.google.common.collect.AbstractObjectCountMap.Itr, java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.nextCalled = true;
            int i9 = this.index;
            this.nextIndex = i9;
            this.index = i9 + 1;
            return getOutput(i9);
        }

        @Override // com.google.common.collect.AbstractObjectCountMap.Itr, java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.nextCalled);
            this.expectedModCount++;
            EnumCountHashMap.this.removeEntry(this.nextIndex);
            this.nextCalled = false;
            this.nextIndex = -1;
            this.index--;
        }
    }

    public class EnumMapEntry extends AbstractObjectCountMap<K>.MapEntry {
        public EnumMapEntry(int i9) {
            super(i9);
        }

        @Override // com.google.common.collect.AbstractObjectCountMap.MapEntry, com.google.common.collect.Multiset.Entry
        public int getCount() {
            int i9 = EnumCountHashMap.this.values[this.lastKnownIndex];
            if (i9 == -1) {
                return 0;
            }
            return i9;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractObjectCountMap.MapEntry
        public int setCount(int i9) {
            EnumCountHashMap enumCountHashMap = EnumCountHashMap.this;
            int[] iArr = enumCountHashMap.values;
            int i10 = this.lastKnownIndex;
            int i11 = iArr[i10];
            if (i11 == -1) {
                enumCountHashMap.put((EnumCountHashMap) this.key, i9);
                return 0;
            }
            iArr[i10] = i9;
            if (i11 == -1) {
                return 0;
            }
            return i11;
        }
    }

    public EnumCountHashMap(Class<K> cls) {
        this.keyType = cls;
        K[] enumConstants = cls.getEnumConstants();
        this.keys = enumConstants;
        if (enumConstants != null) {
            int[] iArr = new int[enumConstants.length];
            this.values = iArr;
            Arrays.fill(iArr, 0, enumConstants.length, -1);
        } else {
            throw new IllegalStateException("Expected Enum class type, but got " + cls.getName());
        }
    }

    public static <K extends Enum<K>> EnumCountHashMap<K> create(Class<K> cls) {
        return new EnumCountHashMap<>(cls);
    }

    private boolean isValidKey(Object obj) {
        if (obj == null) {
            return false;
        }
        Class<?> cls = obj.getClass();
        return cls == this.keyType || cls.getSuperclass() == this.keyType;
    }

    private void typeCheck(K k9) {
        Class<?> cls = k9.getClass();
        if (cls == this.keyType || cls.getSuperclass() == this.keyType) {
            return;
        }
        throw new ClassCastException(cls + " != " + this.keyType);
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public void clear() {
        this.modCount++;
        if (this.keys != null) {
            int[] iArr = this.values;
            Arrays.fill(iArr, 0, iArr.length, -1);
            this.size = 0;
        }
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public boolean containsKey(Object obj) {
        return isValidKey(obj) && this.values[((Enum) obj).ordinal()] != -1;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public Set<Multiset.Entry<K>> createEntrySet() {
        return new AbstractObjectCountMap<K>.EntrySetView() { // from class: com.google.common.collect.EnumCountHashMap.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<K>> iterator() {
                return new EnumCountHashMap<K>.EnumIterator<Multiset.Entry<K>>() { // from class: com.google.common.collect.EnumCountHashMap.2.1
                    {
                        EnumCountHashMap enumCountHashMap = EnumCountHashMap.this;
                    }

                    @Override // com.google.common.collect.AbstractObjectCountMap.Itr
                    public Multiset.Entry<K> getOutput(int i9) {
                        return new EnumMapEntry(i9);
                    }
                };
            }
        };
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public Set<K> createKeySet() {
        return new AbstractObjectCountMap<K>.KeySetView() { // from class: com.google.common.collect.EnumCountHashMap.1
            private Object[] getFilteredKeyArray() {
                Object[] objArr = new Object[EnumCountHashMap.this.size];
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    EnumCountHashMap enumCountHashMap = EnumCountHashMap.this;
                    Object[] objArr2 = enumCountHashMap.keys;
                    if (i9 >= objArr2.length) {
                        return objArr;
                    }
                    if (enumCountHashMap.values[i9] != -1) {
                        objArr[i10] = objArr2[i9];
                        i10++;
                    }
                    i9++;
                }
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<K> iterator() {
                return new EnumCountHashMap<K>.EnumIterator<K>() { // from class: com.google.common.collect.EnumCountHashMap.1.1
                    {
                        EnumCountHashMap enumCountHashMap = EnumCountHashMap.this;
                    }

                    @Override // com.google.common.collect.AbstractObjectCountMap.Itr
                    public K getOutput(int i9) {
                        return (K) EnumCountHashMap.this.keys[i9];
                    }
                };
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return getFilteredKeyArray();
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) ObjectArrays.toArrayImpl(getFilteredKeyArray(), 0, EnumCountHashMap.this.size, tArr);
            }
        };
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int firstIndex() {
        for (int i9 = 0; i9 < this.keys.length; i9++) {
            if (this.values[i9] > 0) {
                return i9;
            }
        }
        return -1;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int get(Object obj) {
        if (containsKey(obj)) {
            return this.values[((Enum) obj).ordinal()];
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public Multiset.Entry<K> getEntry(int i9) {
        Preconditions.checkElementIndex(i9, this.size);
        return new EnumMapEntry(i9);
    }

    public int hashCode() {
        int i9 = 0;
        int iHashCode = 0;
        while (true) {
            Object[] objArr = this.keys;
            if (i9 >= objArr.length) {
                return iHashCode;
            }
            iHashCode += objArr[i9].hashCode() ^ this.values[i9];
            i9++;
        }
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int indexOf(Object obj) {
        if (isValidKey(obj)) {
            return ((Enum) obj).ordinal();
        }
        return -1;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int nextIndex(int i9) {
        do {
            i9++;
            if (i9 >= this.keys.length) {
                return -1;
            }
        } while (this.values[i9] <= 0);
        return i9;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int remove(Object obj) {
        int iOrdinal;
        int[] iArr;
        int i9;
        if (!isValidKey(obj) || (i9 = (iArr = this.values)[(iOrdinal = ((Enum) obj).ordinal())]) == -1) {
            return 0;
        }
        iArr[iOrdinal] = -1;
        this.size--;
        this.modCount++;
        return i9;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int removeEntry(int i9) {
        return remove(this.keys[i9]);
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int put(K k9, int i9) {
        CollectPreconditions.checkPositive(i9, "count");
        typeCheck(k9);
        int iOrdinal = k9.ordinal();
        int[] iArr = this.values;
        int i10 = iArr[iOrdinal];
        iArr[iOrdinal] = i9;
        this.modCount++;
        if (i10 != -1) {
            return i10;
        }
        this.size++;
        return 0;
    }
}
