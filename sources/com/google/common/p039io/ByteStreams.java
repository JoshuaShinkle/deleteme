package com.google.common.p039io;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class ByteStreams {
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() { // from class: com.google.common.io.ByteStreams.1
        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int i9) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            Preconditions.checkNotNull(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i9, int i10) {
            Preconditions.checkNotNull(bArr);
        }
    };
    private static final int ZERO_COPY_CHUNK_SIZE = 524288;

    public static final class FastByteArrayOutputStream extends ByteArrayOutputStream {
        private FastByteArrayOutputStream() {
        }

        public void writeTo(byte[] bArr, int i9) {
            System.arraycopy(((ByteArrayOutputStream) this).buf, 0, bArr, i9, ((ByteArrayOutputStream) this).count);
        }
    }

    private ByteStreams() {
    }

    @CanIgnoreReturnValue
    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(outputStream);
        byte[] bArrCreateBuffer = createBuffer();
        long j9 = 0;
        while (true) {
            int i9 = inputStream.read(bArrCreateBuffer);
            if (i9 == -1) {
                return j9;
            }
            outputStream.write(bArrCreateBuffer, 0, i9);
            j9 += i9;
        }
    }

    public static byte[] createBuffer() {
        return new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
    }

    @CanIgnoreReturnValue
    public static long exhaust(InputStream inputStream) {
        byte[] bArrCreateBuffer = createBuffer();
        long j9 = 0;
        while (true) {
            long j10 = inputStream.read(bArrCreateBuffer);
            if (j10 == -1) {
                return j9;
            }
            j9 += j10;
        }
    }

    public static InputStream limit(InputStream inputStream, long j9) {
        return new LimitedInputStream(inputStream, j9);
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr) {
        return newDataInput(new ByteArrayInputStream(bArr));
    }

    public static ByteArrayDataOutput newDataOutput() {
        return newDataOutput(new ByteArrayOutputStream());
    }

    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    @CanIgnoreReturnValue
    public static int read(InputStream inputStream, byte[] bArr, int i9, int i10) throws IOException {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(bArr);
        if (i10 < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        int i11 = 0;
        while (i11 < i10) {
            int i12 = inputStream.read(bArr, i9 + i11, i10 - i11);
            if (i12 == -1) {
                break;
            }
            i11 += i12;
        }
        return i11;
    }

    @CanIgnoreReturnValue
    public static <T> T readBytes(InputStream inputStream, ByteProcessor<T> byteProcessor) throws IOException {
        int i9;
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkNotNull(byteProcessor);
        byte[] bArrCreateBuffer = createBuffer();
        do {
            i9 = inputStream.read(bArrCreateBuffer);
            if (i9 == -1) {
                break;
            }
        } while (byteProcessor.processBytes(bArrCreateBuffer, 0, i9));
        return byteProcessor.getResult();
    }

    public static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        readFully(inputStream, bArr, 0, bArr.length);
    }

    public static void skipFully(InputStream inputStream, long j9) throws IOException {
        long jSkipUpTo = skipUpTo(inputStream, j9);
        if (jSkipUpTo >= j9) {
            return;
        }
        throw new EOFException("reached end of stream after skipping " + jSkipUpTo + " bytes; " + j9 + " bytes expected");
    }

    private static long skipSafely(InputStream inputStream, long j9) throws IOException {
        int iAvailable = inputStream.available();
        if (iAvailable == 0) {
            return 0L;
        }
        return inputStream.skip(Math.min(iAvailable, j9));
    }

    public static long skipUpTo(InputStream inputStream, long j9) throws IOException {
        byte[] bArrCreateBuffer = createBuffer();
        long j10 = 0;
        while (j10 < j9) {
            long j11 = j9 - j10;
            long jSkipSafely = skipSafely(inputStream, j11);
            if (jSkipSafely == 0) {
                jSkipSafely = inputStream.read(bArrCreateBuffer, 0, (int) Math.min(j11, bArrCreateBuffer.length));
                if (jSkipSafely == -1) {
                    break;
                }
            }
            j10 += jSkipSafely;
        }
        return j10;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(32, inputStream.available()));
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static class ByteArrayDataInputStream implements ByteArrayDataInput {
        final DataInput input;

        public ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.input = new DataInputStream(byteArrayInputStream);
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public boolean readBoolean() {
            try {
                return this.input.readBoolean();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public byte readByte() {
            try {
                return this.input.readByte();
            } catch (EOFException e9) {
                throw new IllegalStateException(e9);
            } catch (IOException e10) {
                throw new AssertionError(e10);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public char readChar() {
            try {
                return this.input.readChar();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public double readDouble() {
            try {
                return this.input.readDouble();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public float readFloat() {
            try {
                return this.input.readFloat();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            try {
                this.input.readFully(bArr);
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public int readInt() {
            try {
                return this.input.readInt();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public String readLine() {
            try {
                return this.input.readLine();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public long readLong() {
            try {
                return this.input.readLong();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public short readShort() {
            try {
                return this.input.readShort();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public String readUTF() {
            try {
                return this.input.readUTF();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public int readUnsignedByte() {
            try {
                return this.input.readUnsignedByte();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public int readUnsignedShort() {
            try {
                return this.input.readUnsignedShort();
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public int skipBytes(int i9) {
            try {
                return this.input.skipBytes(i9);
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataInput, java.io.DataInput
        public void readFully(byte[] bArr, int i9, int i10) throws IOException {
            try {
                this.input.readFully(bArr, i9, i10);
            } catch (IOException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }

    public static class ByteArrayDataOutputStream implements ByteArrayDataOutput {
        final ByteArrayOutputStream byteArrayOutputSteam;
        final DataOutput output;

        public ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            this.byteArrayOutputSteam = byteArrayOutputStream;
            this.output = new DataOutputStream(byteArrayOutputStream);
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput
        public byte[] toByteArray() {
            return this.byteArrayOutputSteam.toByteArray();
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void write(int i9) throws IOException {
            try {
                this.output.write(i9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeBoolean(boolean z8) throws IOException {
            try {
                this.output.writeBoolean(z8);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeByte(int i9) throws IOException {
            try {
                this.output.writeByte(i9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeBytes(String str) throws IOException {
            try {
                this.output.writeBytes(str);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeChar(int i9) throws IOException {
            try {
                this.output.writeChar(i9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeChars(String str) throws IOException {
            try {
                this.output.writeChars(str);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeDouble(double d9) throws IOException {
            try {
                this.output.writeDouble(d9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeFloat(float f9) throws IOException {
            try {
                this.output.writeFloat(f9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeInt(int i9) throws IOException {
            try {
                this.output.writeInt(i9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeLong(long j9) throws IOException {
            try {
                this.output.writeLong(j9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeShort(int i9) throws IOException {
            try {
                this.output.writeShort(i9);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void writeUTF(String str) throws IOException {
            try {
                this.output.writeUTF(str);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void write(byte[] bArr) throws IOException {
            try {
                this.output.write(bArr);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }

        @Override // com.google.common.p039io.ByteArrayDataOutput, java.io.DataOutput
        public void write(byte[] bArr, int i9, int i10) throws IOException {
            try {
                this.output.write(bArr, i9, i10);
            } catch (IOException e9) {
                throw new AssertionError(e9);
            }
        }
    }

    public static ByteArrayDataInput newDataInput(byte[] bArr, int i9) {
        Preconditions.checkPositionIndex(i9, bArr.length);
        return newDataInput(new ByteArrayInputStream(bArr, i9, bArr.length - i9));
    }

    public static ByteArrayDataOutput newDataOutput(int i9) {
        if (i9 >= 0) {
            return newDataOutput(new ByteArrayOutputStream(i9));
        }
        throw new IllegalArgumentException(String.format("Invalid size: %s", Integer.valueOf(i9)));
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i9, int i10) throws IOException {
        int i11 = read(inputStream, bArr, i9, i10);
        if (i11 == i10) {
            return;
        }
        throw new EOFException("reached end of stream after reading " + i11 + " bytes; " + i10 + " bytes expected");
    }

    public static final class LimitedInputStream extends FilterInputStream {
        private long left;
        private long mark;

        public LimitedInputStream(InputStream inputStream, long j9) {
            super(inputStream);
            this.mark = -1L;
            Preconditions.checkNotNull(inputStream);
            Preconditions.checkArgument(j9 >= 0, "limit must be non-negative");
            this.left = j9;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() {
            return (int) Math.min(((FilterInputStream) this).in.available(), this.left);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int i9) {
            ((FilterInputStream) this).in.mark(i9);
            this.mark = this.left;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (this.left == 0) {
                return -1;
            }
            int i9 = ((FilterInputStream) this).in.read();
            if (i9 != -1) {
                this.left--;
            }
            return i9;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() {
            if (!((FilterInputStream) this).in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            ((FilterInputStream) this).in.reset();
            this.left = this.mark;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j9) throws IOException {
            long jSkip = ((FilterInputStream) this).in.skip(Math.min(j9, this.left));
            this.left -= jSkip;
            return jSkip;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) throws IOException {
            long j9 = this.left;
            if (j9 == 0) {
                return -1;
            }
            int i11 = ((FilterInputStream) this).in.read(bArr, i9, (int) Math.min(i10, j9));
            if (i11 != -1) {
                this.left -= i11;
            }
            return i11;
        }
    }

    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        return new ByteArrayDataInputStream((ByteArrayInputStream) Preconditions.checkNotNull(byteArrayInputStream));
    }

    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        return new ByteArrayDataOutputStream((ByteArrayOutputStream) Preconditions.checkNotNull(byteArrayOutputStream));
    }

    public static byte[] toByteArray(InputStream inputStream, int i9) throws IOException {
        byte[] bArr = new byte[i9];
        int i10 = i9;
        while (i10 > 0) {
            int i11 = i9 - i10;
            int i12 = inputStream.read(bArr, i11, i10);
            if (i12 == -1) {
                return Arrays.copyOf(bArr, i11);
            }
            i10 -= i12;
        }
        int i13 = inputStream.read();
        if (i13 == -1) {
            return bArr;
        }
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        fastByteArrayOutputStream.write(i13);
        copy(inputStream, fastByteArrayOutputStream);
        byte[] bArr2 = new byte[fastByteArrayOutputStream.size() + i9];
        System.arraycopy(bArr, 0, bArr2, 0, i9);
        fastByteArrayOutputStream.writeTo(bArr2, i9);
        return bArr2;
    }

    @CanIgnoreReturnValue
    public static long copy(ReadableByteChannel readableByteChannel, WritableByteChannel writableByteChannel) throws IOException {
        Preconditions.checkNotNull(readableByteChannel);
        Preconditions.checkNotNull(writableByteChannel);
        long jWrite = 0;
        if (readableByteChannel instanceof FileChannel) {
            FileChannel fileChannel = (FileChannel) readableByteChannel;
            long jPosition = fileChannel.position();
            long j9 = jPosition;
            while (true) {
                long jTransferTo = fileChannel.transferTo(j9, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, writableByteChannel);
                j9 += jTransferTo;
                fileChannel.position(j9);
                if (jTransferTo <= 0 && j9 >= fileChannel.size()) {
                    return j9 - jPosition;
                }
            }
        } else {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(createBuffer());
            while (readableByteChannel.read(byteBufferWrap) != -1) {
                byteBufferWrap.flip();
                while (byteBufferWrap.hasRemaining()) {
                    jWrite += writableByteChannel.write(byteBufferWrap);
                }
                byteBufferWrap.clear();
            }
            return jWrite;
        }
    }
}
