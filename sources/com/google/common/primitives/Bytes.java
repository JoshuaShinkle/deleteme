package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

@GwtCompatible
/* loaded from: classes2.dex */
public final class Bytes {

    @GwtCompatible
    public static class ByteArrayAsList extends AbstractList<Byte> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final byte[] array;
        final int end;
        final int start;

        public ByteArrayAsList(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return (obj instanceof Byte) && Bytes.indexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ByteArrayAsList)) {
                return super.equals(obj);
            }
            ByteArrayAsList byteArrayAsList = (ByteArrayAsList) obj;
            int size = size();
            if (byteArrayAsList.size() != size) {
                return false;
            }
            for (int i9 = 0; i9 < size; i9++) {
                if (this.array[this.start + i9] != byteArrayAsList.array[byteArrayAsList.start + i9]) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int iHashCode = 1;
            for (int i9 = this.start; i9 < this.end; i9++) {
                iHashCode = (iHashCode * 31) + Bytes.hashCode(this.array[i9]);
            }
            return iHashCode;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            int iIndexOf;
            if (!(obj instanceof Byte) || (iIndexOf = Bytes.indexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            int iLastIndexOf;
            if (!(obj instanceof Byte) || (iLastIndexOf = Bytes.lastIndexOf(this.array, ((Byte) obj).byteValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return iLastIndexOf - this.start;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Byte> subList(int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, size());
            if (i9 == i10) {
                return Collections.emptyList();
            }
            byte[] bArr = this.array;
            int i11 = this.start;
            return new ByteArrayAsList(bArr, i9 + i11, i11 + i10);
        }

        public byte[] toByteArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
            sb.append('[');
            sb.append((int) this.array[this.start]);
            int i9 = this.start;
            while (true) {
                i9++;
                if (i9 >= this.end) {
                    sb.append(']');
                    return sb.toString();
                }
                sb.append(", ");
                sb.append((int) this.array[i9]);
            }
        }

        public ByteArrayAsList(byte[] bArr, int i9, int i10) {
            this.array = bArr;
            this.start = i9;
            this.end = i10;
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte get(int i9) {
            Preconditions.checkElementIndex(i9, size());
            return Byte.valueOf(this.array[this.start + i9]);
        }

        @Override // java.util.AbstractList, java.util.List
        public Byte set(int i9, Byte b9) {
            Preconditions.checkElementIndex(i9, size());
            byte[] bArr = this.array;
            int i10 = this.start;
            byte b10 = bArr[i10 + i9];
            bArr[i10 + i9] = ((Byte) Preconditions.checkNotNull(b9)).byteValue();
            return Byte.valueOf(b10);
        }
    }

    private Bytes() {
    }

    public static List<Byte> asList(byte... bArr) {
        return bArr.length == 0 ? Collections.emptyList() : new ByteArrayAsList(bArr);
    }

    public static byte[] concat(byte[]... bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        int length2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        return bArr3;
    }

    public static boolean contains(byte[] bArr, byte b9) {
        for (byte b10 : bArr) {
            if (b10 == b9) {
                return true;
            }
        }
        return false;
    }

    public static byte[] ensureCapacity(byte[] bArr, int i9, int i10) {
        Preconditions.checkArgument(i9 >= 0, "Invalid minLength: %s", i9);
        Preconditions.checkArgument(i10 >= 0, "Invalid padding: %s", i10);
        return bArr.length < i9 ? Arrays.copyOf(bArr, i9 + i10) : bArr;
    }

    public static int hashCode(byte b9) {
        return b9;
    }

    public static int indexOf(byte[] bArr, byte b9) {
        return indexOf(bArr, b9, 0, bArr.length);
    }

    public static int lastIndexOf(byte[] bArr, byte b9) {
        return lastIndexOf(bArr, b9, 0, bArr.length);
    }

    public static byte[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ByteArrayAsList) {
            return ((ByteArrayAsList) collection).toByteArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        byte[] bArr = new byte[length];
        for (int i9 = 0; i9 < length; i9++) {
            bArr[i9] = ((Number) Preconditions.checkNotNull(array[i9])).byteValue();
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(byte[] bArr, byte b9, int i9, int i10) {
        while (i9 < i10) {
            if (bArr[i9] == b9) {
                return i9;
            }
            i9++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(byte[] bArr, byte b9, int i9, int i10) {
        for (int i11 = i10 - 1; i11 >= i9; i11--) {
            if (bArr[i11] == b9) {
                return i11;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int indexOf(byte[] bArr, byte[] bArr2) {
        Preconditions.checkNotNull(bArr, "array");
        Preconditions.checkNotNull(bArr2, "target");
        if (bArr2.length == 0) {
            return 0;
        }
        int i9 = 0;
        while (i9 < (bArr.length - bArr2.length) + 1) {
            for (int i10 = 0; i10 < bArr2.length; i10++) {
                if (bArr[i9 + i10] != bArr2[i10]) {
                    break;
                }
            }
            return i9;
        }
        return -1;
    }
}
