package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class ByteString implements Iterable<Byte> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int CONCATENATE_BY_COPY_SIZE = 128;
    public static final ByteString EMPTY = new LiteralByteString(new byte[0]);
    static final int MAX_READ_FROM_CHUNK_SIZE = 8192;
    static final int MIN_READ_FROM_CHUNK_SIZE = 256;

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

    private static ByteString balancedConcat(Iterator<ByteString> it, int i9) {
        if (i9 == 1) {
            return it.next();
        }
        int i10 = i9 >>> 1;
        return balancedConcat(it, i10).concat(balancedConcat(it, i9 - i10));
    }

    public static ByteString copyFrom(byte[] bArr, int i9, int i10) {
        byte[] bArr2 = new byte[i10];
        System.arraycopy(bArr, i9, bArr2, 0, i10);
        return new LiteralByteString(bArr2);
    }

    public static ByteString copyFromUtf8(String str) {
        try {
            return new LiteralByteString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e9) {
            throw new RuntimeException("UTF-8 not supported?", e9);
        }
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

    public abstract ByteBuffer asReadOnlyByteBuffer();

    public abstract List<ByteBuffer> asReadOnlyByteBufferList();

    public abstract byte byteAt(int i9);

    public ByteString concat(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        if (size + size2 < 2147483647L) {
            return RopeByteString.concatenate(this, byteString);
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("ByteString would be too long: ");
        sb.append(size);
        sb.append("+");
        sb.append(size2);
        throw new IllegalArgumentException(sb.toString());
    }

    public abstract void copyTo(ByteBuffer byteBuffer);

    public void copyTo(byte[] bArr, int i9) {
        copyTo(bArr, 0, i9, size());
    }

    public abstract void copyToInternal(byte[] bArr, int i9, int i10, int i11);

    public boolean endsWith(ByteString byteString) {
        return size() >= byteString.size() && substring(size() - byteString.size()).equals(byteString);
    }

    public abstract boolean equals(Object obj);

    public abstract int getTreeDepth();

    public abstract int hashCode();

    public abstract boolean isBalanced();

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract boolean isValidUtf8();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Iterable
    /* renamed from: iterator */
    public abstract Iterator<Byte> iterator();

    public abstract CodedInputStream newCodedInput();

    public abstract InputStream newInput();

    public abstract int partialHash(int i9, int i10, int i11);

    public abstract int partialIsValidUtf8(int i9, int i10, int i11);

    public abstract int peekCachedHashCode();

    public abstract int size();

    public boolean startsWith(ByteString byteString) {
        return size() >= byteString.size() && substring(0, byteString.size()).equals(byteString);
    }

    public ByteString substring(int i9) {
        return substring(i9, size());
    }

    public abstract ByteString substring(int i9, int i10);

    public byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[size];
        copyToInternal(bArr, 0, 0, size);
        return bArr;
    }

    public String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    public abstract String toString(String str);

    public String toStringUtf8() {
        try {
            return toString("UTF-8");
        } catch (UnsupportedEncodingException e9) {
            throw new RuntimeException("UTF-8 not supported?", e9);
        }
    }

    public abstract void writeTo(OutputStream outputStream);

    public void writeTo(OutputStream outputStream, int i9, int i10) {
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(i9);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i10 < 0) {
            StringBuilder sb2 = new StringBuilder(23);
            sb2.append("Length < 0: ");
            sb2.append(i10);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        int i11 = i9 + i10;
        if (i11 <= size()) {
            if (i10 > 0) {
                writeToInternal(outputStream, i9, i10);
            }
        } else {
            StringBuilder sb3 = new StringBuilder(39);
            sb3.append("Source end offset exceeded: ");
            sb3.append(i11);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public abstract void writeToInternal(OutputStream outputStream, int i9, int i10);

    public static Output newOutput() {
        return new Output(CONCATENATE_BY_COPY_SIZE);
    }

    public static ByteString readFrom(InputStream inputStream, int i9) {
        return readFrom(inputStream, i9, i9);
    }

    public void copyTo(byte[] bArr, int i9, int i10, int i11) {
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(i9);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i10 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Target offset < 0: ");
            sb2.append(i10);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        if (i11 < 0) {
            StringBuilder sb3 = new StringBuilder(23);
            sb3.append("Length < 0: ");
            sb3.append(i11);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
        int i12 = i9 + i11;
        if (i12 > size()) {
            StringBuilder sb4 = new StringBuilder(34);
            sb4.append("Source end offset < 0: ");
            sb4.append(i12);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
        int i13 = i10 + i11;
        if (i13 <= bArr.length) {
            if (i11 > 0) {
                copyToInternal(bArr, i9, i10, i11);
            }
        } else {
            StringBuilder sb5 = new StringBuilder(34);
            sb5.append("Target end offset < 0: ");
            sb5.append(i13);
            throw new IndexOutOfBoundsException(sb5.toString());
        }
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

    public static ByteString copyFrom(byte[] bArr) {
        return copyFrom(bArr, 0, bArr.length);
    }

    public static ByteString copyFrom(ByteBuffer byteBuffer, int i9) {
        byte[] bArr = new byte[i9];
        byteBuffer.get(bArr);
        return new LiteralByteString(bArr);
    }

    public static ByteString copyFrom(ByteBuffer byteBuffer) {
        return copyFrom(byteBuffer, byteBuffer.remaining());
    }

    public static ByteString copyFrom(String str, String str2) {
        return new LiteralByteString(str.getBytes(str2));
    }

    public static ByteString copyFrom(Iterable<ByteString> iterable) {
        Collection arrayList;
        if (!(iterable instanceof Collection)) {
            arrayList = new ArrayList();
            Iterator<ByteString> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        } else {
            arrayList = (Collection) iterable;
        }
        if (arrayList.isEmpty()) {
            return EMPTY;
        }
        return balancedConcat(arrayList.iterator(), arrayList.size());
    }
}
