package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
class LiteralByteString extends ByteString {
    protected final byte[] bytes;
    private int hash = 0;

    public class LiteralByteIterator implements ByteString.ByteIterator {
        private final int limit;
        private int position;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.limit;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            try {
                byte[] bArr = LiteralByteString.this.bytes;
                int i9 = this.position;
                this.position = i9 + 1;
                return bArr[i9];
            } catch (ArrayIndexOutOfBoundsException e9) {
                throw new NoSuchElementException(e9.getMessage());
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private LiteralByteIterator() {
            this.position = 0;
            this.limit = LiteralByteString.this.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }
    }

    public LiteralByteString(byte[] bArr) {
        this.bytes = bArr;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(asReadOnlyByteBuffer());
        return arrayList;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public byte byteAt(int i9) {
        return this.bytes[i9];
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        byteBuffer.put(this.bytes, getOffsetIntoBytes(), size());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i9, int i10, int i11) {
        System.arraycopy(this.bytes, i9, bArr, i10, i11);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString) || size() != ((ByteString) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof LiteralByteString) {
            return equalsRange((LiteralByteString) obj, 0, size());
        }
        if (obj instanceof RopeByteString) {
            return obj.equals(this);
        }
        String strValueOf = String.valueOf(obj.getClass());
        StringBuilder sb = new StringBuilder(strValueOf.length() + 49);
        sb.append("Has a new type of ByteString been created? Found ");
        sb.append(strValueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean equalsRange(LiteralByteString literalByteString, int i9, int i10) {
        if (i10 > literalByteString.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i10);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i9 + i10 > literalByteString.size()) {
            int size2 = literalByteString.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: ");
            sb2.append(i9);
            sb2.append(", ");
            sb2.append(i10);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        byte[] bArr = this.bytes;
        byte[] bArr2 = literalByteString.bytes;
        int offsetIntoBytes = getOffsetIntoBytes() + i10;
        int offsetIntoBytes2 = getOffsetIntoBytes();
        int offsetIntoBytes3 = literalByteString.getOffsetIntoBytes() + i9;
        while (offsetIntoBytes2 < offsetIntoBytes) {
            if (bArr[offsetIntoBytes2] != bArr2[offsetIntoBytes3]) {
                return false;
            }
            offsetIntoBytes2++;
            offsetIntoBytes3++;
        }
        return true;
    }

    public int getOffsetIntoBytes() {
        return 0;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int getTreeDepth() {
        return 0;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int hashCode() {
        int iPartialHash = this.hash;
        if (iPartialHash == 0) {
            int size = size();
            iPartialHash = partialHash(size, 0, size);
            if (iPartialHash == 0) {
                iPartialHash = 1;
            }
            this.hash = iPartialHash;
        }
        return iPartialHash;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public boolean isBalanced() {
        return true;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public boolean isValidUtf8() {
        int offsetIntoBytes = getOffsetIntoBytes();
        return Utf8.isValidUtf8(this.bytes, offsetIntoBytes, size() + offsetIntoBytes);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public InputStream newInput() {
        return new ByteArrayInputStream(this.bytes, getOffsetIntoBytes(), size());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int partialHash(int i9, int i10, int i11) {
        return hashCode(i9, this.bytes, getOffsetIntoBytes() + i10, i11);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int partialIsValidUtf8(int i9, int i10, int i11) {
        int offsetIntoBytes = getOffsetIntoBytes() + i10;
        return Utf8.partialIsValidUtf8(i9, this.bytes, offsetIntoBytes, i11 + offsetIntoBytes);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int peekCachedHashCode() {
        return this.hash;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int size() {
        return this.bytes.length;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public ByteString substring(int i9, int i10) {
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i9);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i10 > size()) {
            int size = size();
            StringBuilder sb2 = new StringBuilder(36);
            sb2.append("End index: ");
            sb2.append(i10);
            sb2.append(" > ");
            sb2.append(size);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        int i11 = i10 - i9;
        if (i11 >= 0) {
            return i11 == 0 ? ByteString.EMPTY : new BoundedByteString(this.bytes, getOffsetIntoBytes() + i9, i11);
        }
        StringBuilder sb3 = new StringBuilder(66);
        sb3.append("Beginning index larger than ending index: ");
        sb3.append(i9);
        sb3.append(", ");
        sb3.append(i10);
        throw new IndexOutOfBoundsException(sb3.toString());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public String toString(String str) {
        return new String(this.bytes, getOffsetIntoBytes(), size(), str);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i9, int i10) throws IOException {
        outputStream.write(this.bytes, getOffsetIntoBytes() + i9, i10);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString, java.lang.Iterable
    public Iterator<Byte> iterator() {
        return new LiteralByteIterator();
    }

    public static int hashCode(int i9, byte[] bArr, int i10, int i11) {
        for (int i12 = i10; i12 < i10 + i11; i12++) {
            i9 = (i9 * 31) + bArr[i12];
        }
        return i9;
    }

    public static int hashCode(byte[] bArr) {
        int iHashCode = hashCode(bArr.length, bArr, 0, bArr.length);
        if (iHashCode == 0) {
            return 1;
        }
        return iHashCode;
    }
}
