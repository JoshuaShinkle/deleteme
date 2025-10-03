package com.google.protobuf;

import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public abstract class ByteString implements Iterable<Byte>, Serializable {
    static final int CONCATENATE_BY_COPY_SIZE = 128;
    public static final ByteString EMPTY = new LiteralByteString(Internal.EMPTY_BYTE_ARRAY);
    static final int MAX_READ_FROM_CHUNK_SIZE = 8192;
    static final int MIN_READ_FROM_CHUNK_SIZE = 256;
    private static final int UNSIGNED_BYTE_MASK = 255;
    private static final Comparator<ByteString> UNSIGNED_LEXICOGRAPHICAL_COMPARATOR;
    private static final ByteArrayCopier byteArrayCopier;
    private int hash = 0;

    public static abstract class AbstractByteIterator implements ByteIterator {
        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public final Byte next() {
            return Byte.valueOf(nextByte());
        }
    }

    public static final class ArraysByteArrayCopier implements ByteArrayCopier {
        private ArraysByteArrayCopier() {
        }

        @Override // com.google.protobuf.ByteString.ByteArrayCopier
        public byte[] copyFrom(byte[] bArr, int i9, int i10) {
            return Arrays.copyOfRange(bArr, i9, i10 + i9);
        }
    }

    public static final class BoundedByteString extends LiteralByteString {
        private static final long serialVersionUID = 1;
        private final int bytesLength;
        private final int bytesOffset;

        public BoundedByteString(byte[] bArr, int i9, int i10) {
            super(bArr);
            ByteString.checkRange(i9, i9 + i10, bArr.length);
            this.bytesOffset = i9;
            this.bytesLength = i10;
        }

        private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
            throw new InvalidObjectException("BoundedByteStream instances are not to be serialized directly");
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public byte byteAt(int i9) {
            ByteString.checkIndex(i9, size());
            return this.bytes[this.bytesOffset + i9];
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public void copyToInternal(byte[] bArr, int i9, int i10, int i11) {
            System.arraycopy(this.bytes, getOffsetIntoBytes() + i9, bArr, i10, i11);
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString
        public int getOffsetIntoBytes() {
            return this.bytesOffset;
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public byte internalByteAt(int i9) {
            return this.bytes[this.bytesOffset + i9];
        }

        @Override // com.google.protobuf.ByteString.LiteralByteString, com.google.protobuf.ByteString
        public int size() {
            return this.bytesLength;
        }

        public Object writeReplace() {
            return ByteString.wrap(toByteArray());
        }
    }

    public interface ByteArrayCopier {
        byte[] copyFrom(byte[] bArr, int i9, int i10);
    }

    public interface ByteIterator extends Iterator<Byte> {
        byte nextByte();
    }

    public static final class CodedBuilder {
        private final byte[] buffer;
        private final CodedOutputStream output;

        public ByteString build() {
            this.output.checkNoSpaceLeft();
            return new LiteralByteString(this.buffer);
        }

        public CodedOutputStream getCodedOutput() {
            return this.output;
        }

        private CodedBuilder(int i9) {
            byte[] bArr = new byte[i9];
            this.buffer = bArr;
            this.output = CodedOutputStream.newInstance(bArr);
        }
    }

    public static abstract class LeafByteString extends ByteString {
        public abstract boolean equalsRange(ByteString byteString, int i9, int i10);

        @Override // com.google.protobuf.ByteString
        public final int getTreeDepth() {
            return 0;
        }

        @Override // com.google.protobuf.ByteString
        public final boolean isBalanced() {
            return true;
        }

        @Override // com.google.protobuf.ByteString, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator<Byte> iterator() {
            return super.iterator2();
        }

        @Override // com.google.protobuf.ByteString
        public void writeToReverse(ByteOutput byteOutput) {
            writeTo(byteOutput);
        }
    }

    public static class LiteralByteString extends LeafByteString {
        private static final long serialVersionUID = 1;
        protected final byte[] bytes;

        public LiteralByteString(byte[] bArr) {
            bArr.getClass();
            this.bytes = bArr;
        }

        @Override // com.google.protobuf.ByteString
        public final ByteBuffer asReadOnlyByteBuffer() {
            return ByteBuffer.wrap(this.bytes, getOffsetIntoBytes(), size()).asReadOnlyBuffer();
        }

        @Override // com.google.protobuf.ByteString
        public final List<ByteBuffer> asReadOnlyByteBufferList() {
            return Collections.singletonList(asReadOnlyByteBuffer());
        }

        @Override // com.google.protobuf.ByteString
        public byte byteAt(int i9) {
            return this.bytes[i9];
        }

        @Override // com.google.protobuf.ByteString
        public final void copyTo(ByteBuffer byteBuffer) {
            byteBuffer.put(this.bytes, getOffsetIntoBytes(), size());
        }

        @Override // com.google.protobuf.ByteString
        public void copyToInternal(byte[] bArr, int i9, int i10, int i11) {
            System.arraycopy(this.bytes, i9, bArr, i10, i11);
        }

        @Override // com.google.protobuf.ByteString
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ByteString) || size() != ((ByteString) obj).size()) {
                return false;
            }
            if (size() == 0) {
                return true;
            }
            if (!(obj instanceof LiteralByteString)) {
                return obj.equals(this);
            }
            LiteralByteString literalByteString = (LiteralByteString) obj;
            int iPeekCachedHashCode = peekCachedHashCode();
            int iPeekCachedHashCode2 = literalByteString.peekCachedHashCode();
            if (iPeekCachedHashCode == 0 || iPeekCachedHashCode2 == 0 || iPeekCachedHashCode == iPeekCachedHashCode2) {
                return equalsRange(literalByteString, 0, size());
            }
            return false;
        }

        @Override // com.google.protobuf.ByteString.LeafByteString
        public final boolean equalsRange(ByteString byteString, int i9, int i10) {
            if (i10 > byteString.size()) {
                throw new IllegalArgumentException("Length too large: " + i10 + size());
            }
            int i11 = i9 + i10;
            if (i11 > byteString.size()) {
                throw new IllegalArgumentException("Ran off end of other: " + i9 + ", " + i10 + ", " + byteString.size());
            }
            if (!(byteString instanceof LiteralByteString)) {
                return byteString.substring(i9, i11).equals(substring(0, i10));
            }
            LiteralByteString literalByteString = (LiteralByteString) byteString;
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

        @Override // com.google.protobuf.ByteString
        public byte internalByteAt(int i9) {
            return this.bytes[i9];
        }

        @Override // com.google.protobuf.ByteString
        public final boolean isValidUtf8() {
            int offsetIntoBytes = getOffsetIntoBytes();
            return Utf8.isValidUtf8(this.bytes, offsetIntoBytes, size() + offsetIntoBytes);
        }

        @Override // com.google.protobuf.ByteString
        public final CodedInputStream newCodedInput() {
            return CodedInputStream.newInstance(this.bytes, getOffsetIntoBytes(), size(), true);
        }

        @Override // com.google.protobuf.ByteString
        public final InputStream newInput() {
            return new ByteArrayInputStream(this.bytes, getOffsetIntoBytes(), size());
        }

        @Override // com.google.protobuf.ByteString
        public final int partialHash(int i9, int i10, int i11) {
            return Internal.partialHash(i9, this.bytes, getOffsetIntoBytes() + i10, i11);
        }

        @Override // com.google.protobuf.ByteString
        public final int partialIsValidUtf8(int i9, int i10, int i11) {
            int offsetIntoBytes = getOffsetIntoBytes() + i10;
            return Utf8.partialIsValidUtf8(i9, this.bytes, offsetIntoBytes, i11 + offsetIntoBytes);
        }

        @Override // com.google.protobuf.ByteString
        public int size() {
            return this.bytes.length;
        }

        @Override // com.google.protobuf.ByteString
        public final ByteString substring(int i9, int i10) {
            int iCheckRange = ByteString.checkRange(i9, i10, size());
            return iCheckRange == 0 ? ByteString.EMPTY : new BoundedByteString(this.bytes, getOffsetIntoBytes() + i9, iCheckRange);
        }

        @Override // com.google.protobuf.ByteString
        public final String toStringInternal(Charset charset) {
            return new String(this.bytes, getOffsetIntoBytes(), size(), charset);
        }

        @Override // com.google.protobuf.ByteString
        public final void writeTo(OutputStream outputStream) throws IOException {
            outputStream.write(toByteArray());
        }

        @Override // com.google.protobuf.ByteString
        public final void writeToInternal(OutputStream outputStream, int i9, int i10) throws IOException {
            outputStream.write(this.bytes, getOffsetIntoBytes() + i9, i10);
        }

        @Override // com.google.protobuf.ByteString
        public final void writeTo(ByteOutput byteOutput) {
            byteOutput.writeLazy(this.bytes, getOffsetIntoBytes(), size());
        }
    }

    public static final class SystemByteArrayCopier implements ByteArrayCopier {
        private SystemByteArrayCopier() {
        }

        @Override // com.google.protobuf.ByteString.ByteArrayCopier
        public byte[] copyFrom(byte[] bArr, int i9, int i10) {
            byte[] bArr2 = new byte[i10];
            System.arraycopy(bArr, i9, bArr2, 0, i10);
            return bArr2;
        }
    }

    static {
        byteArrayCopier = Android.isOnAndroidDevice() ? new SystemByteArrayCopier() : new ArraysByteArrayCopier();
        UNSIGNED_LEXICOGRAPHICAL_COMPARATOR = new Comparator<ByteString>() { // from class: com.google.protobuf.ByteString.2
            /* JADX WARN: Type inference failed for: r0v0, types: [com.google.protobuf.ByteString$ByteIterator, java.util.Iterator] */
            /* JADX WARN: Type inference failed for: r1v0, types: [com.google.protobuf.ByteString$ByteIterator, java.util.Iterator] */
            @Override // java.util.Comparator
            public int compare(ByteString byteString, ByteString byteString2) {
                ?? Iterator2 = byteString.iterator2();
                ?? Iterator22 = byteString2.iterator2();
                while (Iterator2.hasNext() && Iterator22.hasNext()) {
                    int iCompare = Integer.compare(ByteString.toInt(Iterator2.nextByte()), ByteString.toInt(Iterator22.nextByte()));
                    if (iCompare != 0) {
                        return iCompare;
                    }
                }
                return Integer.compare(byteString.size(), byteString2.size());
            }
        };
    }

    private static ByteString balancedConcat(Iterator<ByteString> it, int i9) {
        if (i9 < 1) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(i9)));
        }
        if (i9 == 1) {
            return it.next();
        }
        int i10 = i9 >>> 1;
        return balancedConcat(it, i10).concat(balancedConcat(it, i9 - i10));
    }

    public static void checkIndex(int i9, int i10) {
        if (((i10 - (i9 + 1)) | i9) < 0) {
            if (i9 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i9);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i9 + ", " + i10);
        }
    }

    public static int checkRange(int i9, int i10, int i11) {
        int i12 = i10 - i9;
        if ((i9 | i10 | i12 | (i11 - i10)) >= 0) {
            return i12;
        }
        if (i9 < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i9 + " < 0");
        }
        if (i10 < i9) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i9 + ", " + i10);
        }
        throw new IndexOutOfBoundsException("End index: " + i10 + " >= " + i11);
    }

    public static ByteString copyFrom(byte[] bArr, int i9, int i10) {
        checkRange(i9, i9 + i10, bArr.length);
        return new LiteralByteString(byteArrayCopier.copyFrom(bArr, i9, i10));
    }

    public static ByteString copyFromUtf8(String str) {
        return new LiteralByteString(str.getBytes(Internal.UTF_8));
    }

    public static CodedBuilder newCodedBuilder(int i9) {
        return new CodedBuilder(i9);
    }

    public static Output newOutput(int i9) {
        return new Output(i9);
    }

    private static ByteString readChunk(InputStream inputStream, int i9) throws IOException {
        byte[] bArr = new byte[i9];
        int i10 = 0;
        while (i10 < i9) {
            int i11 = inputStream.read(bArr, i10, i9 - i10);
            if (i11 == -1) {
                break;
            }
            i10 += i11;
        }
        if (i10 == 0) {
            return null;
        }
        return copyFrom(bArr, 0, i10);
    }

    public static ByteString readFrom(InputStream inputStream) {
        return readFrom(inputStream, MIN_READ_FROM_CHUNK_SIZE, 8192);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int toInt(byte b9) {
        return b9 & UnsignedBytes.MAX_VALUE;
    }

    private String truncateAndEscapeForDisplay() {
        if (size() <= 50) {
            return TextFormatEscaper.escapeBytes(this);
        }
        return TextFormatEscaper.escapeBytes(substring(0, 47)) + "...";
    }

    public static Comparator<ByteString> unsignedLexicographicalComparator() {
        return UNSIGNED_LEXICOGRAPHICAL_COMPARATOR;
    }

    public static ByteString wrap(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasArray()) {
            return new NioByteString(byteBuffer);
        }
        return wrap(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
    }

    public abstract ByteBuffer asReadOnlyByteBuffer();

    public abstract List<ByteBuffer> asReadOnlyByteBufferList();

    public abstract byte byteAt(int i9);

    public final ByteString concat(ByteString byteString) {
        if (Integer.MAX_VALUE - size() >= byteString.size()) {
            return RopeByteString.concatenate(this, byteString);
        }
        throw new IllegalArgumentException("ByteString would be too long: " + size() + "+" + byteString.size());
    }

    public abstract void copyTo(ByteBuffer byteBuffer);

    public void copyTo(byte[] bArr, int i9) {
        copyTo(bArr, 0, i9, size());
    }

    public abstract void copyToInternal(byte[] bArr, int i9, int i10, int i11);

    public final boolean endsWith(ByteString byteString) {
        return size() >= byteString.size() && substring(size() - byteString.size()).equals(byteString);
    }

    public abstract boolean equals(Object obj);

    public abstract int getTreeDepth();

    public final int hashCode() {
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

    public abstract byte internalByteAt(int i9);

    public abstract boolean isBalanced();

    public final boolean isEmpty() {
        return size() == 0;
    }

    public abstract boolean isValidUtf8();

    public abstract CodedInputStream newCodedInput();

    public abstract InputStream newInput();

    public abstract int partialHash(int i9, int i10, int i11);

    public abstract int partialIsValidUtf8(int i9, int i10, int i11);

    public final int peekCachedHashCode() {
        return this.hash;
    }

    public abstract int size();

    public final boolean startsWith(ByteString byteString) {
        return size() >= byteString.size() && substring(0, byteString.size()).equals(byteString);
    }

    public final ByteString substring(int i9) {
        return substring(i9, size());
    }

    public abstract ByteString substring(int i9, int i10);

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[size];
        copyToInternal(bArr, 0, 0, size);
        return bArr;
    }

    public final String toString(String str) throws UnsupportedEncodingException {
        try {
            return toString(Charset.forName(str));
        } catch (UnsupportedCharsetException e9) {
            UnsupportedEncodingException unsupportedEncodingException = new UnsupportedEncodingException(str);
            unsupportedEncodingException.initCause(e9);
            throw unsupportedEncodingException;
        }
    }

    public abstract String toStringInternal(Charset charset);

    public final String toStringUtf8() {
        return toString(Internal.UTF_8);
    }

    public abstract void writeTo(ByteOutput byteOutput);

    public abstract void writeTo(OutputStream outputStream);

    public final void writeTo(OutputStream outputStream, int i9, int i10) {
        checkRange(i9, i9 + i10, size());
        if (i10 > 0) {
            writeToInternal(outputStream, i9, i10);
        }
    }

    public abstract void writeToInternal(OutputStream outputStream, int i9, int i10);

    public abstract void writeToReverse(ByteOutput byteOutput);

    public static Output newOutput() {
        return new Output(CONCATENATE_BY_COPY_SIZE);
    }

    public static ByteString readFrom(InputStream inputStream, int i9) {
        return readFrom(inputStream, i9, i9);
    }

    @Deprecated
    public final void copyTo(byte[] bArr, int i9, int i10, int i11) {
        checkRange(i9, i9 + i11, size());
        checkRange(i10, i10 + i11, bArr.length);
        if (i11 > 0) {
            copyToInternal(bArr, i9, i10, i11);
        }
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public Iterator<Byte> iterator2() {
        return new AbstractByteIterator() { // from class: com.google.protobuf.ByteString.1
            private final int limit;
            private int position = 0;

            {
                this.limit = ByteString.this.size();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.position < this.limit;
            }

            @Override // com.google.protobuf.ByteString.ByteIterator
            public byte nextByte() {
                int i9 = this.position;
                if (i9 >= this.limit) {
                    throw new NoSuchElementException();
                }
                this.position = i9 + 1;
                return ByteString.this.internalByteAt(i9);
            }
        };
    }

    public static ByteString copyFrom(byte[] bArr) {
        return copyFrom(bArr, 0, bArr.length);
    }

    public static ByteString readFrom(InputStream inputStream, int i9, int i10) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            ByteString chunk = readChunk(inputStream, i9);
            if (chunk == null) {
                return copyFrom(arrayList);
            }
            arrayList.add(chunk);
            i9 = Math.min(i9 * 2, i10);
        }
    }

    public static final class Output extends OutputStream {
        private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        private byte[] buffer;
        private int bufferPos;
        private final ArrayList<ByteString> flushedBuffers;
        private int flushedBuffersTotalBytes;
        private final int initialCapacity;

        public Output(int i9) {
            if (i9 < 0) {
                throw new IllegalArgumentException("Buffer size < 0");
            }
            this.initialCapacity = i9;
            this.flushedBuffers = new ArrayList<>();
            this.buffer = new byte[i9];
        }

        private byte[] copyArray(byte[] bArr, int i9) {
            byte[] bArr2 = new byte[i9];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i9));
            return bArr2;
        }

        private void flushFullBuffer(int i9) {
            this.flushedBuffers.add(new LiteralByteString(this.buffer));
            int length = this.flushedBuffersTotalBytes + this.buffer.length;
            this.flushedBuffersTotalBytes = length;
            this.buffer = new byte[Math.max(this.initialCapacity, Math.max(i9, length >>> 1))];
            this.bufferPos = 0;
        }

        private void flushLastBuffer() {
            int i9 = this.bufferPos;
            byte[] bArr = this.buffer;
            if (i9 >= bArr.length) {
                this.flushedBuffers.add(new LiteralByteString(this.buffer));
                this.buffer = EMPTY_BYTE_ARRAY;
            } else if (i9 > 0) {
                this.flushedBuffers.add(new LiteralByteString(copyArray(bArr, i9)));
            }
            this.flushedBuffersTotalBytes += this.bufferPos;
            this.bufferPos = 0;
        }

        public synchronized void reset() {
            this.flushedBuffers.clear();
            this.flushedBuffersTotalBytes = 0;
            this.bufferPos = 0;
        }

        public synchronized int size() {
            return this.flushedBuffersTotalBytes + this.bufferPos;
        }

        public synchronized ByteString toByteString() {
            flushLastBuffer();
            return ByteString.copyFrom(this.flushedBuffers);
        }

        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
        }

        @Override // java.io.OutputStream
        public synchronized void write(int i9) {
            if (this.bufferPos == this.buffer.length) {
                flushFullBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i10 = this.bufferPos;
            this.bufferPos = i10 + 1;
            bArr[i10] = (byte) i9;
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            ByteString[] byteStringArr;
            byte[] bArr;
            int i9;
            synchronized (this) {
                ArrayList<ByteString> arrayList = this.flushedBuffers;
                byteStringArr = (ByteString[]) arrayList.toArray(new ByteString[arrayList.size()]);
                bArr = this.buffer;
                i9 = this.bufferPos;
            }
            for (ByteString byteString : byteStringArr) {
                byteString.writeTo(outputStream);
            }
            outputStream.write(copyArray(bArr, i9));
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] bArr, int i9, int i10) {
            byte[] bArr2 = this.buffer;
            int length = bArr2.length;
            int i11 = this.bufferPos;
            if (i10 <= length - i11) {
                System.arraycopy(bArr, i9, bArr2, i11, i10);
                this.bufferPos += i10;
            } else {
                int length2 = bArr2.length - i11;
                System.arraycopy(bArr, i9, bArr2, i11, length2);
                int i12 = i10 - length2;
                flushFullBuffer(i12);
                System.arraycopy(bArr, i9 + length2, this.buffer, 0, i12);
                this.bufferPos = i12;
            }
        }
    }

    public static ByteString copyFrom(ByteBuffer byteBuffer, int i9) {
        checkRange(0, i9, byteBuffer.remaining());
        byte[] bArr = new byte[i9];
        byteBuffer.get(bArr);
        return new LiteralByteString(bArr);
    }

    public static ByteString wrap(byte[] bArr) {
        return new LiteralByteString(bArr);
    }

    public final String toString(Charset charset) {
        return size() == 0 ? "" : toStringInternal(charset);
    }

    public static ByteString wrap(byte[] bArr, int i9, int i10) {
        return new BoundedByteString(bArr, i9, i10);
    }

    public final String toString() {
        return String.format(Locale.ROOT, "<ByteString@%s size=%d contents=\"%s\">", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()), truncateAndEscapeForDisplay());
    }

    public static ByteString copyFrom(ByteBuffer byteBuffer) {
        return copyFrom(byteBuffer, byteBuffer.remaining());
    }

    public static ByteString copyFrom(String str, String str2) {
        return new LiteralByteString(str.getBytes(str2));
    }

    public static ByteString copyFrom(String str, Charset charset) {
        return new LiteralByteString(str.getBytes(charset));
    }

    public static ByteString copyFrom(Iterable<ByteString> iterable) {
        int size;
        if (!(iterable instanceof Collection)) {
            Iterator<ByteString> it = iterable.iterator();
            size = 0;
            while (it.hasNext()) {
                it.next();
                size++;
            }
        } else {
            size = ((Collection) iterable).size();
        }
        if (size == 0) {
            return EMPTY;
        }
        return balancedConcat(iterable.iterator(), size);
    }
}
