package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
class BoundedByteString extends LiteralByteString {
    private final int bytesLength;
    private final int bytesOffset;

    public class BoundedByteIterator implements ByteString.ByteIterator {
        private final int limit;
        private int position;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.limit;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            int i9 = this.position;
            if (i9 >= this.limit) {
                throw new NoSuchElementException();
            }
            byte[] bArr = BoundedByteString.this.bytes;
            this.position = i9 + 1;
            return bArr[i9];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private BoundedByteIterator() {
            int offsetIntoBytes = BoundedByteString.this.getOffsetIntoBytes();
            this.position = offsetIntoBytes;
            this.limit = offsetIntoBytes + BoundedByteString.this.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }
    }

    public BoundedByteString(byte[] bArr, int i9, int i10) {
        super(bArr);
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(29);
            sb.append("Offset too small: ");
            sb.append(i9);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i10 < 0) {
            StringBuilder sb2 = new StringBuilder(29);
            sb2.append("Length too small: ");
            sb2.append(i9);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (i9 + i10 <= bArr.length) {
            this.bytesOffset = i9;
            this.bytesLength = i10;
            return;
        }
        StringBuilder sb3 = new StringBuilder(48);
        sb3.append("Offset+Length too large: ");
        sb3.append(i9);
        sb3.append("+");
        sb3.append(i10);
        throw new IllegalArgumentException(sb3.toString());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LiteralByteString, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public byte byteAt(int i9) {
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(28);
            sb.append("Index too small: ");
            sb.append(i9);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        if (i9 < size()) {
            return this.bytes[this.bytesOffset + i9];
        }
        int size = size();
        StringBuilder sb2 = new StringBuilder(41);
        sb2.append("Index too large: ");
        sb2.append(i9);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LiteralByteString, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i9, int i10, int i11) {
        System.arraycopy(this.bytes, getOffsetIntoBytes() + i9, bArr, i10, i11);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LiteralByteString
    public int getOffsetIntoBytes() {
        return this.bytesOffset;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LiteralByteString, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int size() {
        return this.bytesLength;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.LiteralByteString, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString, java.lang.Iterable
    public Iterator<Byte> iterator() {
        return new BoundedByteIterator();
    }
}
