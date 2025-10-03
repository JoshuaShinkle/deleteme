package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
final class SortedLists {

    public enum KeyAbsentBehavior {
        NEXT_LOWER { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.1
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int i9) {
                return i9 - 1;
            }
        },
        NEXT_HIGHER { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.2
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int i9) {
                return i9;
            }
        },
        INVERTED_INSERTION_INDEX { // from class: com.google.common.collect.SortedLists.KeyAbsentBehavior.3
            @Override // com.google.common.collect.SortedLists.KeyAbsentBehavior
            public int resultIndex(int i9) {
                return ~i9;
            }
        };

        public abstract int resultIndex(int i9);
    }

    public enum KeyPresentBehavior {
        ANY_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.1
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e9, List<? extends E> list, int i9) {
                return i9;
            }
        },
        LAST_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e9, List<? extends E> list, int i9) {
                int size = list.size() - 1;
                while (i9 < size) {
                    int i10 = ((i9 + size) + 1) >>> 1;
                    if (comparator.compare(list.get(i10), e9) > 0) {
                        size = i10 - 1;
                    } else {
                        i9 = i10;
                    }
                }
                return i9;
            }
        },
        FIRST_PRESENT { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e9, List<? extends E> list, int i9) {
                int i10 = 0;
                while (i10 < i9) {
                    int i11 = (i10 + i9) >>> 1;
                    if (comparator.compare(list.get(i11), e9) < 0) {
                        i10 = i11 + 1;
                    } else {
                        i9 = i11;
                    }
                }
                return i10;
            }
        },
        FIRST_AFTER { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.4
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e9, List<? extends E> list, int i9) {
                return KeyPresentBehavior.LAST_PRESENT.resultIndex(comparator, e9, list, i9) + 1;
            }
        },
        LAST_BEFORE { // from class: com.google.common.collect.SortedLists.KeyPresentBehavior.5
            @Override // com.google.common.collect.SortedLists.KeyPresentBehavior
            public <E> int resultIndex(Comparator<? super E> comparator, E e9, List<? extends E> list, int i9) {
                return KeyPresentBehavior.FIRST_PRESENT.resultIndex(comparator, e9, list, i9) - 1;
            }
        };

        public abstract <E> int resultIndex(Comparator<? super E> comparator, E e9, List<? extends E> list, int i9);
    }

    private SortedLists() {
    }

    public static <E extends Comparable> int binarySearch(List<? extends E> list, E e9, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(e9);
        return binarySearch(list, e9, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K extends Comparable> int binarySearch(List<E> list, Function<? super E, K> function, K k9, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        return binarySearch(list, function, k9, Ordering.natural(), keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E, K> int binarySearch(List<E> list, Function<? super E, K> function, K k9, Comparator<? super K> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        return binarySearch((List<? extends K>) Lists.transform(list, function), k9, comparator, keyPresentBehavior, keyAbsentBehavior);
    }

    public static <E> int binarySearch(List<? extends E> list, E e9, Comparator<? super E> comparator, KeyPresentBehavior keyPresentBehavior, KeyAbsentBehavior keyAbsentBehavior) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(keyPresentBehavior);
        Preconditions.checkNotNull(keyAbsentBehavior);
        if (!(list instanceof RandomAccess)) {
            list = Lists.newArrayList(list);
        }
        int size = list.size() - 1;
        int i9 = 0;
        while (i9 <= size) {
            int i10 = (i9 + size) >>> 1;
            int iCompare = comparator.compare(e9, list.get(i10));
            if (iCompare < 0) {
                size = i10 - 1;
            } else {
                if (iCompare <= 0) {
                    return i9 + keyPresentBehavior.resultIndex(comparator, e9, list.subList(i9, size + 1), i10 - i9);
                }
                i9 = i10 + 1;
            }
        }
        return keyAbsentBehavior.resultIndex(i9);
    }
}
