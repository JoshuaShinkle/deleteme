package com.google.firebase.crashlytics.internal.metadata;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.primitives.UnsignedBytes;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes2.dex */
class QueueFile implements Closeable {
    static final int HEADER_LENGTH = 16;
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    public static class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final int position;

        public Element(int i9, int i10) {
            this.position = i9;
            this.length = i10;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.position + ", length = " + this.length + "]";
        }
    }

    public final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) throws IOException {
            QueueFile.nonNull(bArr, "buffer");
            if ((i9 | i10) < 0 || i10 > bArr.length - i9) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i11 = this.remaining;
            if (i11 <= 0) {
                return -1;
            }
            if (i10 > i11) {
                i10 = i11;
            }
            QueueFile.this.ringRead(this.position, bArr, i9, i10);
            this.position = QueueFile.this.wrapPosition(this.position + i10);
            this.remaining -= i10;
            return i10;
        }

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.position + 4);
            this.remaining = element.length;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int i9 = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return i9;
        }
    }

    public interface ElementReader {
        void read(InputStream inputStream, int i9);
    }

    public QueueFile(File file) throws IOException {
        this.buffer = new byte[16];
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    private void expandIfNecessary(int i9) throws IOException {
        int i10 = i9 + 4;
        int iRemainingBytes = remainingBytes();
        if (iRemainingBytes >= i10) {
            return;
        }
        int i11 = this.fileLength;
        do {
            iRemainingBytes += i11;
            i11 <<= 1;
        } while (iRemainingBytes < i10);
        setLength(i11);
        Element element = this.last;
        int iWrapPosition = wrapPosition(element.position + 4 + element.length);
        if (iWrapPosition < this.first.position) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.fileLength);
            long j9 = iWrapPosition - 4;
            if (channel.transferTo(16L, j9, channel) != j9) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        int i12 = this.last.position;
        int i13 = this.first.position;
        if (i12 < i13) {
            int i14 = (this.fileLength + i12) - 16;
            writeHeader(i11, this.elementCount, i13, i14);
            this.last = new Element(i14, this.last.length);
        } else {
            writeHeader(i11, this.elementCount, i13, i12);
        }
        this.fileLength = i11;
    }

    private static void initialize(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile randomAccessFileOpen = open(file2);
        try {
            randomAccessFileOpen.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            randomAccessFileOpen.seek(0L);
            byte[] bArr = new byte[16];
            writeInts(bArr, 4096, 0, 0, 0);
            randomAccessFileOpen.write(bArr);
            randomAccessFileOpen.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            randomAccessFileOpen.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t8, String str) {
        if (t8 != null) {
            return t8;
        }
        throw new NullPointerException(str);
    }

    private static RandomAccessFile open(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    private Element readElement(int i9) throws IOException {
        if (i9 == 0) {
            return Element.NULL;
        }
        this.raf.seek(i9);
        return new Element(i9, this.raf.readInt());
    }

    private void readHeader() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        int i9 = readInt(this.buffer, 0);
        this.fileLength = i9;
        if (i9 <= this.raf.length()) {
            this.elementCount = readInt(this.buffer, 4);
            int i10 = readInt(this.buffer, 8);
            int i11 = readInt(this.buffer, 12);
            this.first = readElement(i10);
            this.last = readElement(i11);
            return;
        }
        throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
    }

    private static int readInt(byte[] bArr, int i9) {
        return ((bArr[i9] & UnsignedBytes.MAX_VALUE) << 24) + ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 16) + ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 8) + (bArr[i9 + 3] & UnsignedBytes.MAX_VALUE);
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ringRead(int i9, byte[] bArr, int i10, int i11) throws IOException {
        int iWrapPosition = wrapPosition(i9);
        int i12 = iWrapPosition + i11;
        int i13 = this.fileLength;
        if (i12 <= i13) {
            this.raf.seek(iWrapPosition);
            this.raf.readFully(bArr, i10, i11);
            return;
        }
        int i14 = i13 - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.readFully(bArr, i10, i14);
        this.raf.seek(16L);
        this.raf.readFully(bArr, i10 + i14, i11 - i14);
    }

    private void ringWrite(int i9, byte[] bArr, int i10, int i11) throws IOException {
        int iWrapPosition = wrapPosition(i9);
        int i12 = iWrapPosition + i11;
        int i13 = this.fileLength;
        if (i12 <= i13) {
            this.raf.seek(iWrapPosition);
            this.raf.write(bArr, i10, i11);
            return;
        }
        int i14 = i13 - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.write(bArr, i10, i14);
        this.raf.seek(16L);
        this.raf.write(bArr, i10 + i14, i11 - i14);
    }

    private void setLength(int i9) throws IOException {
        this.raf.setLength(i9);
        this.raf.getChannel().force(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int wrapPosition(int i9) {
        int i10 = this.fileLength;
        return i9 < i10 ? i9 : (i9 + 16) - i10;
    }

    private void writeHeader(int i9, int i10, int i11, int i12) throws IOException {
        writeInts(this.buffer, i9, i10, i11, i12);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private static void writeInt(byte[] bArr, int i9, int i10) {
        bArr[i9] = (byte) (i10 >> 24);
        bArr[i9 + 1] = (byte) (i10 >> 16);
        bArr[i9 + 2] = (byte) (i10 >> 8);
        bArr[i9 + 3] = (byte) i10;
    }

    private static void writeInts(byte[] bArr, int... iArr) {
        int i9 = 0;
        for (int i10 : iArr) {
            writeInt(bArr, i9, i10);
            i9 += 4;
        }
    }

    public void add(byte[] bArr) {
        add(bArr, 0, bArr.length);
    }

    public synchronized void clear() {
        writeHeader(4096, 0, 0, 0);
        this.elementCount = 0;
        Element element = Element.NULL;
        this.first = element;
        this.last = element;
        if (this.fileLength > 4096) {
            setLength(4096);
        }
        this.fileLength = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.raf.close();
    }

    public synchronized void forEach(ElementReader elementReader) {
        int iWrapPosition = this.first.position;
        for (int i9 = 0; i9 < this.elementCount; i9++) {
            Element element = readElement(iWrapPosition);
            elementReader.read(new ElementInputStream(element), element.length);
            iWrapPosition = wrapPosition(element.position + 4 + element.length);
        }
    }

    public boolean hasSpaceFor(int i9, int i10) {
        return (usedBytes() + 4) + i9 <= i10;
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    public synchronized byte[] peek() {
        if (isEmpty()) {
            return null;
        }
        Element element = this.first;
        int i9 = element.length;
        byte[] bArr = new byte[i9];
        ringRead(element.position + 4, bArr, 0, i9);
        return bArr;
    }

    public synchronized void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (this.elementCount == 1) {
            clear();
        } else {
            Element element = this.first;
            int iWrapPosition = wrapPosition(element.position + 4 + element.length);
            ringRead(iWrapPosition, this.buffer, 0, 4);
            int i9 = readInt(this.buffer, 0);
            writeHeader(this.fileLength, this.elementCount - 1, iWrapPosition, this.last.position);
            this.elementCount--;
            this.first = new Element(iWrapPosition, i9);
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        sb.append("fileLength=");
        sb.append(this.fileLength);
        sb.append(", size=");
        sb.append(this.elementCount);
        sb.append(", first=");
        sb.append(this.first);
        sb.append(", last=");
        sb.append(this.last);
        sb.append(", element lengths=[");
        try {
            forEach(new ElementReader() { // from class: com.google.firebase.crashlytics.internal.metadata.QueueFile.1
                boolean first = true;

                @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
                public void read(InputStream inputStream, int i9) {
                    if (this.first) {
                        this.first = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i9);
                }
            });
        } catch (IOException e9) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e9);
        }
        sb.append("]]");
        return sb.toString();
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        Element element = this.last;
        int i9 = element.position;
        int i10 = this.first.position;
        return i9 >= i10 ? (i9 - i10) + 4 + element.length + 16 : (((i9 + 4) + element.length) + this.fileLength) - i10;
    }

    public synchronized void add(byte[] bArr, int i9, int i10) {
        int iWrapPosition;
        nonNull(bArr, "buffer");
        if ((i9 | i10) < 0 || i10 > bArr.length - i9) {
            throw new IndexOutOfBoundsException();
        }
        expandIfNecessary(i10);
        boolean zIsEmpty = isEmpty();
        if (zIsEmpty) {
            iWrapPosition = 16;
        } else {
            Element element = this.last;
            iWrapPosition = wrapPosition(element.position + 4 + element.length);
        }
        Element element2 = new Element(iWrapPosition, i10);
        writeInt(this.buffer, 0, i10);
        ringWrite(element2.position, this.buffer, 0, 4);
        ringWrite(element2.position + 4, bArr, i9, i10);
        writeHeader(this.fileLength, this.elementCount + 1, zIsEmpty ? element2.position : this.first.position, element2.position);
        this.last = element2;
        this.elementCount++;
        if (zIsEmpty) {
            this.first = element2;
        }
    }

    public QueueFile(RandomAccessFile randomAccessFile) throws IOException {
        this.buffer = new byte[16];
        this.raf = randomAccessFile;
        readHeader();
    }

    public synchronized void peek(ElementReader elementReader) {
        if (this.elementCount > 0) {
            elementReader.read(new ElementInputStream(this.first), this.first.length);
        }
    }
}
