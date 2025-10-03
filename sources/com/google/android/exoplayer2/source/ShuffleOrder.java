package com.google.android.exoplayer2.source;

import java.util.Arrays;
import java.util.Random;

/* loaded from: classes.dex */
public interface ShuffleOrder {

    public static class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int i9) {
            this(i9, new Random());
        }

        private static int[] createShuffledList(int i9, Random random) {
            int[] iArr = new int[i9];
            int i10 = 0;
            while (i10 < i9) {
                int i11 = i10 + 1;
                int iNextInt = random.nextInt(i11);
                iArr[i10] = iArr[iNextInt];
                iArr[iNextInt] = i10;
                i10 = i11;
            }
            return iArr;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndInsert(int i9, int i10) {
            int[] iArr = new int[i10];
            int[] iArr2 = new int[i10];
            int i11 = 0;
            int i12 = 0;
            while (i12 < i10) {
                iArr[i12] = this.random.nextInt(this.shuffled.length + 1);
                int i13 = i12 + 1;
                int iNextInt = this.random.nextInt(i13);
                iArr2[i12] = iArr2[iNextInt];
                iArr2[iNextInt] = i12 + i9;
                i12 = i13;
            }
            Arrays.sort(iArr);
            int[] iArr3 = new int[this.shuffled.length + i10];
            int i14 = 0;
            int i15 = 0;
            while (true) {
                int[] iArr4 = this.shuffled;
                if (i11 >= iArr4.length + i10) {
                    return new DefaultShuffleOrder(iArr3, new Random(this.random.nextLong()));
                }
                if (i14 >= i10 || i15 != iArr[i14]) {
                    int i16 = i15 + 1;
                    int i17 = iArr4[i15];
                    iArr3[i11] = i17;
                    if (i17 >= i9) {
                        iArr3[i11] = i17 + i10;
                    }
                    i15 = i16;
                } else {
                    iArr3[i11] = iArr2[i14];
                    i14++;
                }
                i11++;
            }
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndRemove(int i9) {
            int[] iArr = new int[this.shuffled.length - 1];
            int i10 = 0;
            boolean z8 = false;
            while (true) {
                int[] iArr2 = this.shuffled;
                if (i10 >= iArr2.length) {
                    return new DefaultShuffleOrder(iArr, new Random(this.random.nextLong()));
                }
                int i11 = iArr2[i10];
                if (i11 == i9) {
                    z8 = true;
                } else {
                    int i12 = z8 ? i10 - 1 : i10;
                    if (i11 > i9) {
                        i11--;
                    }
                    iArr[i12] = i11;
                }
                i10++;
            }
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getFirstIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[0];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLastIndex() {
            int[] iArr = this.shuffled;
            if (iArr.length > 0) {
                return iArr[iArr.length - 1];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLength() {
            return this.shuffled.length;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getNextIndex(int i9) {
            int i10 = this.indexInShuffled[i9] + 1;
            int[] iArr = this.shuffled;
            if (i10 < iArr.length) {
                return iArr[i10];
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getPreviousIndex(int i9) {
            int i10 = this.indexInShuffled[i9] - 1;
            if (i10 >= 0) {
                return this.shuffled[i10];
            }
            return -1;
        }

        public DefaultShuffleOrder(int i9, long j9) {
            this(i9, new Random(j9));
        }

        private DefaultShuffleOrder(int i9, Random random) {
            this(createShuffledList(i9, random), random);
        }

        private DefaultShuffleOrder(int[] iArr, Random random) {
            this.shuffled = iArr;
            this.random = random;
            this.indexInShuffled = new int[iArr.length];
            for (int i9 = 0; i9 < iArr.length; i9++) {
                this.indexInShuffled[iArr[i9]] = i9;
            }
        }
    }

    public static final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        public UnshuffledShuffleOrder(int i9) {
            this.length = i9;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndClear() {
            return new UnshuffledShuffleOrder(0);
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndInsert(int i9, int i10) {
            return new UnshuffledShuffleOrder(this.length + i10);
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public ShuffleOrder cloneAndRemove(int i9) {
            return new UnshuffledShuffleOrder(this.length - 1);
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getFirstIndex() {
            return this.length > 0 ? 0 : -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLastIndex() {
            int i9 = this.length;
            if (i9 > 0) {
                return i9 - 1;
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getLength() {
            return this.length;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getNextIndex(int i9) {
            int i10 = i9 + 1;
            if (i10 < this.length) {
                return i10;
            }
            return -1;
        }

        @Override // com.google.android.exoplayer2.source.ShuffleOrder
        public int getPreviousIndex(int i9) {
            int i10 = i9 - 1;
            if (i10 >= 0) {
                return i10;
            }
            return -1;
        }
    }

    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int i9, int i10);

    ShuffleOrder cloneAndRemove(int i9);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int i9);

    int getPreviousIndex(int i9);
}
