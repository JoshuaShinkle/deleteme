package com.google.protobuf;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.Utf8;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes2.dex */
abstract class BinaryWriter extends ByteOutput implements Writer {
    public static final int DEFAULT_CHUNK_SIZE = 4096;
    private static final int MAP_KEY_NUMBER = 1;
    private static final int MAP_VALUE_NUMBER = 2;
    private final BufferAllocator alloc;
    final ArrayDeque<AllocatedBuffer> buffers;
    private final int chunkSize;
    int totalDoneBytes;

    /* renamed from: com.google.protobuf.BinaryWriter$1 */
    public static /* synthetic */ class C43821 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public static final class SafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private int limitMinusOne;
        private int pos;

        public SafeDirectWriter(BufferAllocator bufferAllocator, int i9) {
            super(bufferAllocator, i9, null);
            nextBuffer();
        }

        private int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        private void nextBuffer() {
            nextBuffer(newDirectBuffer());
        }

        private int spaceLeft() {
            return this.pos + 1;
        }

        private void writeVarint32FiveBytes(int i9) {
            ByteBuffer byteBuffer = this.buffer;
            int i10 = this.pos;
            this.pos = i10 - 1;
            byteBuffer.put(i10, (byte) (i9 >>> 28));
            int i11 = this.pos - 4;
            this.pos = i11;
            this.buffer.putInt(i11 + 1, (i9 & 127) | 128 | ((((i9 >>> 21) & 127) | 128) << 24) | ((((i9 >>> 14) & 127) | 128) << 16) | ((((i9 >>> 7) & 127) | 128) << 8));
        }

        private void writeVarint32FourBytes(int i9) {
            int i10 = this.pos - 4;
            this.pos = i10;
            this.buffer.putInt(i10 + 1, (i9 & 127) | 128 | ((266338304 & i9) << 3) | (((2080768 & i9) | 2097152) << 2) | (((i9 & 16256) | 16384) << 1));
        }

        private void writeVarint32OneByte(int i9) {
            ByteBuffer byteBuffer = this.buffer;
            int i10 = this.pos;
            this.pos = i10 - 1;
            byteBuffer.put(i10, (byte) i9);
        }

        private void writeVarint32ThreeBytes(int i9) {
            int i10 = this.pos - 3;
            this.pos = i10;
            this.buffer.putInt(i10, (((i9 & 127) | 128) << 8) | ((2080768 & i9) << 10) | (((i9 & 16256) | 16384) << 9));
        }

        private void writeVarint32TwoBytes(int i9) {
            int i10 = this.pos - 2;
            this.pos = i10;
            this.buffer.putShort(i10 + 1, (short) ((i9 & 127) | 128 | ((i9 & 16256) << 1)));
        }

        private void writeVarint64EightBytes(long j9) {
            int i9 = this.pos - 8;
            this.pos = i9;
            this.buffer.putLong(i9 + 1, (j9 & 127) | 128 | ((71494644084506624L & j9) << 7) | (((558551906910208L & j9) | 562949953421312L) << 6) | (((4363686772736L & j9) | 4398046511104L) << 5) | (((34091302912L & j9) | 34359738368L) << 4) | (((266338304 & j9) | 268435456) << 3) | (((2080768 & j9) | 2097152) << 2) | (((16256 & j9) | PlaybackStateCompat.ACTION_PREPARE) << 1));
        }

        private void writeVarint64EightBytesWithSign(long j9) {
            int i9 = this.pos - 8;
            this.pos = i9;
            this.buffer.putLong(i9 + 1, (j9 & 127) | 128 | (((71494644084506624L & j9) | 72057594037927936L) << 7) | (((558551906910208L & j9) | 562949953421312L) << 6) | (((4363686772736L & j9) | 4398046511104L) << 5) | (((34091302912L & j9) | 34359738368L) << 4) | (((266338304 & j9) | 268435456) << 3) | (((2080768 & j9) | 2097152) << 2) | (((16256 & j9) | PlaybackStateCompat.ACTION_PREPARE) << 1));
        }

        private void writeVarint64FiveBytes(long j9) {
            int i9 = this.pos - 5;
            this.pos = i9;
            this.buffer.putLong(i9 - 2, (((j9 & 127) | 128) << 24) | ((34091302912L & j9) << 28) | (((266338304 & j9) | 268435456) << 27) | (((2080768 & j9) | 2097152) << 26) | (((16256 & j9) | PlaybackStateCompat.ACTION_PREPARE) << 25));
        }

        private void writeVarint64FourBytes(long j9) {
            writeVarint32FourBytes((int) j9);
        }

        private void writeVarint64NineBytes(long j9) {
            ByteBuffer byteBuffer = this.buffer;
            int i9 = this.pos;
            this.pos = i9 - 1;
            byteBuffer.put(i9, (byte) (j9 >>> 56));
            writeVarint64EightBytesWithSign(j9 & 72057594037927935L);
        }

        private void writeVarint64OneByte(long j9) {
            writeVarint32OneByte((int) j9);
        }

        private void writeVarint64SevenBytes(long j9) {
            int i9 = this.pos - 7;
            this.pos = i9;
            this.buffer.putLong(i9, (((j9 & 127) | 128) << 8) | ((558551906910208L & j9) << 14) | (((4363686772736L & j9) | 4398046511104L) << 13) | (((34091302912L & j9) | 34359738368L) << 12) | (((266338304 & j9) | 268435456) << 11) | (((2080768 & j9) | 2097152) << 10) | (((16256 & j9) | PlaybackStateCompat.ACTION_PREPARE) << 9));
        }

        private void writeVarint64SixBytes(long j9) {
            int i9 = this.pos - 6;
            this.pos = i9;
            this.buffer.putLong(i9 - 1, (((j9 & 127) | 128) << 16) | ((4363686772736L & j9) << 21) | (((34091302912L & j9) | 34359738368L) << 20) | (((266338304 & j9) | 268435456) << 19) | (((2080768 & j9) | 2097152) << 18) | (((16256 & j9) | PlaybackStateCompat.ACTION_PREPARE) << 17));
        }

        private void writeVarint64TenBytes(long j9) {
            ByteBuffer byteBuffer = this.buffer;
            int i9 = this.pos;
            this.pos = i9 - 1;
            byteBuffer.put(i9, (byte) (j9 >>> 63));
            ByteBuffer byteBuffer2 = this.buffer;
            int i10 = this.pos;
            this.pos = i10 - 1;
            byteBuffer2.put(i10, (byte) (((j9 >>> 56) & 127) | 128));
            writeVarint64EightBytesWithSign(j9 & 72057594037927935L);
        }

        private void writeVarint64ThreeBytes(long j9) {
            writeVarint32ThreeBytes((int) j9);
        }

        private void writeVarint64TwoBytes(long j9) {
            writeVarint32TwoBytes((int) j9);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.buffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.buffer.position(this.pos + 1);
                this.buffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i9) {
            if (spaceLeft() < i9) {
                nextBuffer(i9);
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b9) {
            ByteBuffer byteBuffer = this.buffer;
            int i9 = this.pos;
            this.pos = i9 - 1;
            byteBuffer.put(i9, b9);
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i9, boolean z8) {
            requireSpace(6);
            write(z8 ? (byte) 1 : (byte) 0);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i9, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i9, 2);
            } catch (IOException e9) {
                throw new RuntimeException(e9);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i9) {
            writeTag(i9, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i9, int i10) {
            requireSpace(9);
            writeFixed32(i10);
            writeTag(i9, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i9, long j9) {
            requireSpace(13);
            writeFixed64(j9);
            writeTag(i9, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj) {
            writeTag(i9, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i9, int i10) {
            requireSpace(15);
            writeInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i9, int i10) {
            if (spaceLeft() < i10) {
                this.totalDoneBytes += i10;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i9, i10));
                nextBuffer();
            } else {
                int i11 = this.pos - i10;
                this.pos = i11;
                this.buffer.position(i11 + 1);
                this.buffer.put(bArr, i9, i10);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i9, int i10) {
            requireSpace(10);
            writeSInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i9, long j9) {
            requireSpace(15);
            writeSInt64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i9) {
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i9, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i9, int i10) {
            writeVarint32(WireFormat.makeTag(i9, i10));
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i9, int i10) {
            requireSpace(10);
            writeVarint32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i9, long j9) {
            requireSpace(15);
            writeVarint64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i9) {
            if ((i9 & (-128)) == 0) {
                writeVarint32OneByte(i9);
                return;
            }
            if ((i9 & (-16384)) == 0) {
                writeVarint32TwoBytes(i9);
                return;
            }
            if (((-2097152) & i9) == 0) {
                writeVarint32ThreeBytes(i9);
            } else if (((-268435456) & i9) == 0) {
                writeVarint32FourBytes(i9);
            } else {
                writeVarint32FiveBytes(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j9) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j9)) {
                case 1:
                    writeVarint64OneByte(j9);
                    break;
                case 2:
                    writeVarint64TwoBytes(j9);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j9);
                    break;
                case 4:
                    writeVarint64FourBytes(j9);
                    break;
                case 5:
                    writeVarint64FiveBytes(j9);
                    break;
                case 6:
                    writeVarint64SixBytes(j9);
                    break;
                case 7:
                    writeVarint64SevenBytes(j9);
                    break;
                case 8:
                    writeVarint64EightBytes(j9);
                    break;
                case 9:
                    writeVarint64NineBytes(j9);
                    break;
                case 10:
                    writeVarint64TenBytes(j9);
                    break;
            }
        }

        private void nextBuffer(int i9) {
            nextBuffer(newDirectBuffer(i9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i9, int i10) {
            if (spaceLeft() < i10) {
                nextBuffer(i10);
            }
            int i11 = this.pos - i10;
            this.pos = i11;
            this.buffer.position(i11 + 1);
            this.buffer.put(bArr, i9, i10);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasNioBuffer()) {
                ByteBuffer byteBufferNioBuffer = allocatedBuffer.nioBuffer();
                if (byteBufferNioBuffer.isDirect()) {
                    finishCurrentBuffer();
                    this.buffers.addFirst(allocatedBuffer);
                    this.buffer = byteBufferNioBuffer;
                    byteBufferNioBuffer.limit(byteBufferNioBuffer.capacity());
                    this.buffer.position(0);
                    this.buffer.order(ByteOrder.LITTLE_ENDIAN);
                    int iLimit = this.buffer.limit() - 1;
                    this.limitMinusOne = iLimit;
                    this.pos = iLimit;
                    return;
                }
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            throw new RuntimeException("Allocated buffer does not have NIO buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z8) {
            write(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i9) {
            int i10 = this.pos - 4;
            this.pos = i10;
            this.buffer.putInt(i10 + 1, i9);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j9) {
            int i9 = this.pos - 8;
            this.pos = i9;
            this.buffer.putLong(i9 + 1, j9);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj, Schema schema) {
            writeTag(i9, 4);
            schema.writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i9) {
            if (i9 >= 0) {
                writeVarint32(i9);
            } else {
                writeVarint64(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i9) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i9));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j9) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                nextBuffer(iRemaining);
            }
            int i9 = this.pos - iRemaining;
            this.pos = i9;
            this.buffer.position(i9 + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeString(String str) {
            int i9;
            int i10;
            int i11;
            char cCharAt;
            requireSpace(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (cCharAt = str.charAt(length)) < 128) {
                this.buffer.put(this.pos + length, (byte) cCharAt);
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char cCharAt2 = str.charAt(length);
                if (cCharAt2 < 128 && (i11 = this.pos) >= 0) {
                    ByteBuffer byteBuffer = this.buffer;
                    this.pos = i11 - 1;
                    byteBuffer.put(i11, (byte) cCharAt2);
                } else if (cCharAt2 < 2048 && (i10 = this.pos) > 0) {
                    ByteBuffer byteBuffer2 = this.buffer;
                    this.pos = i10 - 1;
                    byteBuffer2.put(i10, (byte) ((cCharAt2 & '?') | 128));
                    ByteBuffer byteBuffer3 = this.buffer;
                    int i12 = this.pos;
                    this.pos = i12 - 1;
                    byteBuffer3.put(i12, (byte) ((cCharAt2 >>> 6) | 960));
                } else if ((cCharAt2 < 55296 || 57343 < cCharAt2) && (i9 = this.pos) > 1) {
                    ByteBuffer byteBuffer4 = this.buffer;
                    this.pos = i9 - 1;
                    byteBuffer4.put(i9, (byte) ((cCharAt2 & '?') | 128));
                    ByteBuffer byteBuffer5 = this.buffer;
                    int i13 = this.pos;
                    this.pos = i13 - 1;
                    byteBuffer5.put(i13, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    ByteBuffer byteBuffer6 = this.buffer;
                    int i14 = this.pos;
                    this.pos = i14 - 1;
                    byteBuffer6.put(i14, (byte) ((cCharAt2 >>> '\f') | 480));
                } else {
                    if (this.pos > 2) {
                        if (length != 0) {
                            char cCharAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt2);
                                ByteBuffer byteBuffer7 = this.buffer;
                                int i15 = this.pos;
                                this.pos = i15 - 1;
                                byteBuffer7.put(i15, (byte) ((codePoint & 63) | 128));
                                ByteBuffer byteBuffer8 = this.buffer;
                                int i16 = this.pos;
                                this.pos = i16 - 1;
                                byteBuffer8.put(i16, (byte) (((codePoint >>> 6) & 63) | 128));
                                ByteBuffer byteBuffer9 = this.buffer;
                                int i17 = this.pos;
                                this.pos = i17 - 1;
                                byteBuffer9.put(i17, (byte) (((codePoint >>> 12) & 63) | 128));
                                ByteBuffer byteBuffer10 = this.buffer;
                                int i18 = this.pos;
                                this.pos = i18 - 1;
                                byteBuffer10.put(i18, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    }
                    requireSpace(length);
                    length++;
                }
                length--;
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            } else {
                int i9 = this.pos - iRemaining;
                this.pos = i9;
                this.buffer.position(i9 + 1);
                this.buffer.put(byteBuffer);
            }
        }
    }

    public static final class SafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private int limit;
        private int limitMinusOne;
        private int offset;
        private int offsetMinusOne;
        private int pos;

        public SafeHeapWriter(BufferAllocator bufferAllocator, int i9) {
            super(bufferAllocator, i9, null);
            nextBuffer();
        }

        private void nextBuffer() {
            nextBuffer(newHeapBuffer());
        }

        private void writeVarint32FiveBytes(int i9) {
            byte[] bArr = this.buffer;
            int i10 = this.pos;
            int i11 = i10 - 1;
            bArr[i10] = (byte) (i9 >>> 28);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((i9 >>> 21) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((i9 >>> 14) & 127) | 128);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((i9 >>> 7) & 127) | 128);
            this.pos = i14 - 1;
            bArr[i14] = (byte) ((i9 & 127) | 128);
        }

        private void writeVarint32FourBytes(int i9) {
            byte[] bArr = this.buffer;
            int i10 = this.pos;
            int i11 = i10 - 1;
            bArr[i10] = (byte) (i9 >>> 21);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((i9 >>> 14) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((i9 >>> 7) & 127) | 128);
            this.pos = i13 - 1;
            bArr[i13] = (byte) ((i9 & 127) | 128);
        }

        private void writeVarint32OneByte(int i9) {
            byte[] bArr = this.buffer;
            int i10 = this.pos;
            this.pos = i10 - 1;
            bArr[i10] = (byte) i9;
        }

        private void writeVarint32ThreeBytes(int i9) {
            byte[] bArr = this.buffer;
            int i10 = this.pos;
            int i11 = i10 - 1;
            bArr[i10] = (byte) (i9 >>> 14);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((i9 >>> 7) & 127) | 128);
            this.pos = i12 - 1;
            bArr[i12] = (byte) ((i9 & 127) | 128);
        }

        private void writeVarint32TwoBytes(int i9) {
            byte[] bArr = this.buffer;
            int i10 = this.pos;
            int i11 = i10 - 1;
            bArr[i10] = (byte) (i9 >>> 7);
            this.pos = i11 - 1;
            bArr[i11] = (byte) ((i9 & 127) | 128);
        }

        private void writeVarint64EightBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 49);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 42) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 35) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((j9 >>> 28) & 127) | 128);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((j9 >>> 21) & 127) | 128);
            int i15 = i14 - 1;
            bArr[i14] = (byte) (((j9 >>> 14) & 127) | 128);
            int i16 = i15 - 1;
            bArr[i15] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i16 - 1;
            bArr[i16] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64FiveBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 28);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 21) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 14) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i13 - 1;
            bArr[i13] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64FourBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 21);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 14) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i12 - 1;
            bArr[i12] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64NineBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 56);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 49) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 42) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((j9 >>> 35) & 127) | 128);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((j9 >>> 28) & 127) | 128);
            int i15 = i14 - 1;
            bArr[i14] = (byte) (((j9 >>> 21) & 127) | 128);
            int i16 = i15 - 1;
            bArr[i15] = (byte) (((j9 >>> 14) & 127) | 128);
            int i17 = i16 - 1;
            bArr[i16] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i17 - 1;
            bArr[i17] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64OneByte(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            this.pos = i9 - 1;
            bArr[i9] = (byte) j9;
        }

        private void writeVarint64SevenBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 42);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 35) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 28) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((j9 >>> 21) & 127) | 128);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((j9 >>> 14) & 127) | 128);
            int i15 = i14 - 1;
            bArr[i14] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i15 - 1;
            bArr[i15] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64SixBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 35);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 28) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 21) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((j9 >>> 14) & 127) | 128);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i14 - 1;
            bArr[i14] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64TenBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 63);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 56) & 127) | 128);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((j9 >>> 49) & 127) | 128);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((j9 >>> 42) & 127) | 128);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((j9 >>> 35) & 127) | 128);
            int i15 = i14 - 1;
            bArr[i14] = (byte) (((j9 >>> 28) & 127) | 128);
            int i16 = i15 - 1;
            bArr[i15] = (byte) (((j9 >>> 21) & 127) | 128);
            int i17 = i16 - 1;
            bArr[i16] = (byte) (((j9 >>> 14) & 127) | 128);
            int i18 = i17 - 1;
            bArr[i17] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i18 - 1;
            bArr[i18] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64ThreeBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (((int) j9) >>> 14);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((j9 >>> 7) & 127) | 128);
            this.pos = i11 - 1;
            bArr[i11] = (byte) ((j9 & 127) | 128);
        }

        private void writeVarint64TwoBytes(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (j9 >>> 7);
            this.pos = i10 - 1;
            bArr[i10] = (byte) ((((int) j9) & 127) | 128);
        }

        public int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.allocatedBuffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                AllocatedBuffer allocatedBuffer = this.allocatedBuffer;
                allocatedBuffer.position((this.pos - allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i9) {
            if (spaceLeft() < i9) {
                nextBuffer(i9);
            }
        }

        public int spaceLeft() {
            return this.pos - this.offsetMinusOne;
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            this.pos = i9 - 1;
            bArr[i9] = b9;
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i9, boolean z8) {
            requireSpace(6);
            write(z8 ? (byte) 1 : (byte) 0);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i9, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i9, 2);
            } catch (IOException e9) {
                throw new RuntimeException(e9);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i9) {
            writeTag(i9, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i9, int i10) {
            requireSpace(9);
            writeFixed32(i10);
            writeTag(i9, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i9, long j9) {
            requireSpace(13);
            writeFixed64(j9);
            writeTag(i9, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj) {
            writeTag(i9, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i9, int i10) {
            requireSpace(15);
            writeInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i9, int i10) {
            if (spaceLeft() < i10) {
                this.totalDoneBytes += i10;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i9, i10));
                nextBuffer();
            } else {
                int i11 = this.pos - i10;
                this.pos = i11;
                System.arraycopy(bArr, i9, this.buffer, i11 + 1, i10);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i9, int i10) {
            requireSpace(10);
            writeSInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i9, long j9) {
            requireSpace(15);
            writeSInt64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i9) {
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i9, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i9, int i10) {
            writeVarint32(WireFormat.makeTag(i9, i10));
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i9, int i10) {
            requireSpace(10);
            writeVarint32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i9, long j9) {
            requireSpace(15);
            writeVarint64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i9) {
            if ((i9 & (-128)) == 0) {
                writeVarint32OneByte(i9);
                return;
            }
            if ((i9 & (-16384)) == 0) {
                writeVarint32TwoBytes(i9);
                return;
            }
            if (((-2097152) & i9) == 0) {
                writeVarint32ThreeBytes(i9);
            } else if (((-268435456) & i9) == 0) {
                writeVarint32FourBytes(i9);
            } else {
                writeVarint32FiveBytes(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j9) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j9)) {
                case 1:
                    writeVarint64OneByte(j9);
                    break;
                case 2:
                    writeVarint64TwoBytes(j9);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j9);
                    break;
                case 4:
                    writeVarint64FourBytes(j9);
                    break;
                case 5:
                    writeVarint64FiveBytes(j9);
                    break;
                case 6:
                    writeVarint64SixBytes(j9);
                    break;
                case 7:
                    writeVarint64SevenBytes(j9);
                    break;
                case 8:
                    writeVarint64EightBytes(j9);
                    break;
                case 9:
                    writeVarint64NineBytes(j9);
                    break;
                case 10:
                    writeVarint64TenBytes(j9);
                    break;
            }
        }

        private void nextBuffer(int i9) {
            nextBuffer(newHeapBuffer(i9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i9, int i10) {
            if (spaceLeft() < i10) {
                nextBuffer(i10);
            }
            int i11 = this.pos - i10;
            this.pos = i11;
            System.arraycopy(bArr, i9, this.buffer, i11 + 1, i10);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasArray()) {
                finishCurrentBuffer();
                this.buffers.addFirst(allocatedBuffer);
                this.allocatedBuffer = allocatedBuffer;
                this.buffer = allocatedBuffer.array();
                int iArrayOffset = allocatedBuffer.arrayOffset();
                this.limit = allocatedBuffer.limit() + iArrayOffset;
                int iPosition = iArrayOffset + allocatedBuffer.position();
                this.offset = iPosition;
                this.offsetMinusOne = iPosition - 1;
                int i9 = this.limit - 1;
                this.limitMinusOne = i9;
                this.pos = i9;
                return;
            }
            throw new RuntimeException("Allocator returned non-heap buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z8) {
            write(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i9) {
            byte[] bArr = this.buffer;
            int i10 = this.pos;
            int i11 = i10 - 1;
            bArr[i10] = (byte) ((i9 >> 24) & 255);
            int i12 = i11 - 1;
            bArr[i11] = (byte) ((i9 >> 16) & 255);
            int i13 = i12 - 1;
            bArr[i12] = (byte) ((i9 >> 8) & 255);
            this.pos = i13 - 1;
            bArr[i13] = (byte) (i9 & 255);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j9) {
            byte[] bArr = this.buffer;
            int i9 = this.pos;
            int i10 = i9 - 1;
            bArr[i9] = (byte) (((int) (j9 >> 56)) & 255);
            int i11 = i10 - 1;
            bArr[i10] = (byte) (((int) (j9 >> 48)) & 255);
            int i12 = i11 - 1;
            bArr[i11] = (byte) (((int) (j9 >> 40)) & 255);
            int i13 = i12 - 1;
            bArr[i12] = (byte) (((int) (j9 >> 32)) & 255);
            int i14 = i13 - 1;
            bArr[i13] = (byte) (((int) (j9 >> 24)) & 255);
            int i15 = i14 - 1;
            bArr[i14] = (byte) (((int) (j9 >> 16)) & 255);
            int i16 = i15 - 1;
            bArr[i15] = (byte) (((int) (j9 >> 8)) & 255);
            this.pos = i16 - 1;
            bArr[i16] = (byte) (((int) j9) & 255);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj, Schema schema) {
            writeTag(i9, 4);
            schema.writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i9) {
            if (i9 >= 0) {
                writeVarint32(i9);
            } else {
                writeVarint64(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i9) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i9));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j9) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                nextBuffer(iRemaining);
            }
            int i9 = this.pos - iRemaining;
            this.pos = i9;
            byteBuffer.get(this.buffer, i9 + 1, iRemaining);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            int i9 = this.pos - iRemaining;
            this.pos = i9;
            byteBuffer.get(this.buffer, i9 + 1, iRemaining);
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeString(String str) {
            int i9;
            int i10;
            int i11;
            char cCharAt;
            requireSpace(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (cCharAt = str.charAt(length)) < 128) {
                this.buffer[this.pos + length] = (byte) cCharAt;
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char cCharAt2 = str.charAt(length);
                if (cCharAt2 < 128 && (i11 = this.pos) > this.offsetMinusOne) {
                    byte[] bArr = this.buffer;
                    this.pos = i11 - 1;
                    bArr[i11] = (byte) cCharAt2;
                } else if (cCharAt2 < 2048 && (i10 = this.pos) > this.offset) {
                    byte[] bArr2 = this.buffer;
                    int i12 = i10 - 1;
                    bArr2[i10] = (byte) ((cCharAt2 & '?') | 128);
                    this.pos = i12 - 1;
                    bArr2[i12] = (byte) ((cCharAt2 >>> 6) | 960);
                } else if ((cCharAt2 < 55296 || 57343 < cCharAt2) && (i9 = this.pos) > this.offset + 1) {
                    byte[] bArr3 = this.buffer;
                    int i13 = i9 - 1;
                    bArr3[i9] = (byte) ((cCharAt2 & '?') | 128);
                    int i14 = i13 - 1;
                    bArr3[i13] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                    this.pos = i14 - 1;
                    bArr3[i14] = (byte) ((cCharAt2 >>> '\f') | 480);
                } else {
                    if (this.pos > this.offset + 2) {
                        if (length != 0) {
                            char cCharAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt2);
                                byte[] bArr4 = this.buffer;
                                int i15 = this.pos;
                                int i16 = i15 - 1;
                                bArr4[i15] = (byte) ((codePoint & 63) | 128);
                                int i17 = i16 - 1;
                                bArr4[i16] = (byte) (((codePoint >>> 6) & 63) | 128);
                                int i18 = i17 - 1;
                                bArr4[i17] = (byte) (((codePoint >>> 12) & 63) | 128);
                                this.pos = i18 - 1;
                                bArr4[i18] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    }
                    requireSpace(length);
                    length++;
                }
                length--;
            }
        }
    }

    public static final class UnsafeDirectWriter extends BinaryWriter {
        private ByteBuffer buffer;
        private long bufferOffset;
        private long limitMinusOne;
        private long pos;

        public UnsafeDirectWriter(BufferAllocator bufferAllocator, int i9) {
            super(bufferAllocator, i9, null);
            nextBuffer();
        }

        private int bufferPos() {
            return (int) (this.pos - this.bufferOffset);
        }

        private int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private void nextBuffer() {
            nextBuffer(newDirectBuffer());
        }

        private int spaceLeft() {
            return bufferPos() + 1;
        }

        private void writeVarint32FiveBytes(int i9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) (i9 >>> 28));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (((i9 >>> 21) & 127) | 128));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((i9 >>> 14) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((i9 >>> 7) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint32FourBytes(int i9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) (i9 >>> 21));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (((i9 >>> 14) & 127) | 128));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((i9 >>> 7) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint32OneByte(int i9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) i9);
        }

        private void writeVarint32ThreeBytes(int i9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) (i9 >>> 14));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (((i9 >>> 7) & 127) | 128));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) (i9 >>> 7));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint64EightBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 49));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 42) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 35) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((j9 >>> 28) & 127) | 128));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) (((j9 >>> 21) & 127) | 128));
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(j15, (byte) (((j9 >>> 14) & 127) | 128));
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(j16, (byte) (((j9 >>> 7) & 127) | 128));
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(j17, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 28));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 21) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 14) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((j9 >>> 7) & 127) | 128));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64FourBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 21));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 14) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 7) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64NineBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 56));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 49) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 42) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((j9 >>> 35) & 127) | 128));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) (((j9 >>> 28) & 127) | 128));
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(j15, (byte) (((j9 >>> 21) & 127) | 128));
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(j16, (byte) (((j9 >>> 14) & 127) | 128));
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(j17, (byte) (((j9 >>> 7) & 127) | 128));
            long j18 = this.pos;
            this.pos = j18 - 1;
            UnsafeUtil.putByte(j18, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64OneByte(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) j9);
        }

        private void writeVarint64SevenBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 42));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 35) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 28) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((j9 >>> 21) & 127) | 128));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) (((j9 >>> 14) & 127) | 128));
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(j15, (byte) (((j9 >>> 7) & 127) | 128));
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(j16, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64SixBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 35));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 28) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 21) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((j9 >>> 14) & 127) | 128));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) (((j9 >>> 7) & 127) | 128));
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(j15, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64TenBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 63));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 56) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((j9 >>> 49) & 127) | 128));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((j9 >>> 42) & 127) | 128));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) (((j9 >>> 35) & 127) | 128));
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(j15, (byte) (((j9 >>> 28) & 127) | 128));
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(j16, (byte) (((j9 >>> 21) & 127) | 128));
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(j17, (byte) (((j9 >>> 14) & 127) | 128));
            long j18 = this.pos;
            this.pos = j18 - 1;
            UnsafeUtil.putByte(j18, (byte) (((j9 >>> 7) & 127) | 128));
            long j19 = this.pos;
            this.pos = j19 - 1;
            UnsafeUtil.putByte(j19, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (((int) j9) >>> 14));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((j9 >>> 7) & 127) | 128));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (j9 >>> 7));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) ((((int) j9) & 127) | 128));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.buffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.buffer.position(bufferPos() + 1);
                this.buffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i9) {
            if (spaceLeft() < i9) {
                nextBuffer(i9);
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, b9);
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i9, boolean z8) {
            requireSpace(6);
            write(z8 ? (byte) 1 : (byte) 0);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i9, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i9, 2);
            } catch (IOException e9) {
                throw new RuntimeException(e9);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i9) {
            writeTag(i9, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i9, int i10) {
            requireSpace(9);
            writeFixed32(i10);
            writeTag(i9, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i9, long j9) {
            requireSpace(13);
            writeFixed64(j9);
            writeTag(i9, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj) {
            writeTag(i9, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i9, int i10) {
            requireSpace(15);
            writeInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i9, int i10) {
            if (spaceLeft() < i10) {
                this.totalDoneBytes += i10;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i9, i10));
                nextBuffer();
            } else {
                this.pos -= i10;
                this.buffer.position(bufferPos() + 1);
                this.buffer.put(bArr, i9, i10);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i9, int i10) {
            requireSpace(10);
            writeSInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i9, long j9) {
            requireSpace(15);
            writeSInt64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i9) {
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i9, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i9, int i10) {
            writeVarint32(WireFormat.makeTag(i9, i10));
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i9, int i10) {
            requireSpace(10);
            writeVarint32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i9, long j9) {
            requireSpace(15);
            writeVarint64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i9) {
            if ((i9 & (-128)) == 0) {
                writeVarint32OneByte(i9);
                return;
            }
            if ((i9 & (-16384)) == 0) {
                writeVarint32TwoBytes(i9);
                return;
            }
            if (((-2097152) & i9) == 0) {
                writeVarint32ThreeBytes(i9);
            } else if (((-268435456) & i9) == 0) {
                writeVarint32FourBytes(i9);
            } else {
                writeVarint32FiveBytes(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j9) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j9)) {
                case 1:
                    writeVarint64OneByte(j9);
                    break;
                case 2:
                    writeVarint64TwoBytes(j9);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j9);
                    break;
                case 4:
                    writeVarint64FourBytes(j9);
                    break;
                case 5:
                    writeVarint64FiveBytes(j9);
                    break;
                case 6:
                    writeVarint64SixBytes(j9);
                    break;
                case 7:
                    writeVarint64SevenBytes(j9);
                    break;
                case 8:
                    writeVarint64EightBytes(j9);
                    break;
                case 9:
                    writeVarint64NineBytes(j9);
                    break;
                case 10:
                    writeVarint64TenBytes(j9);
                    break;
            }
        }

        private void nextBuffer(int i9) {
            nextBuffer(newDirectBuffer(i9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i9, int i10) {
            if (spaceLeft() < i10) {
                nextBuffer(i10);
            }
            this.pos -= i10;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(bArr, i9, i10);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasNioBuffer()) {
                ByteBuffer byteBufferNioBuffer = allocatedBuffer.nioBuffer();
                if (byteBufferNioBuffer.isDirect()) {
                    finishCurrentBuffer();
                    this.buffers.addFirst(allocatedBuffer);
                    this.buffer = byteBufferNioBuffer;
                    byteBufferNioBuffer.limit(byteBufferNioBuffer.capacity());
                    this.buffer.position(0);
                    long jAddressOffset = UnsafeUtil.addressOffset(this.buffer);
                    this.bufferOffset = jAddressOffset;
                    long jLimit = jAddressOffset + (this.buffer.limit() - 1);
                    this.limitMinusOne = jLimit;
                    this.pos = jLimit;
                    return;
                }
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            throw new RuntimeException("Allocated buffer does not have NIO buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z8) {
            write(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i9) {
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(j9, (byte) ((i9 >> 24) & 255));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) ((i9 >> 16) & 255));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) ((i9 >> 8) & 255));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (i9 & 255));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j9) {
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(j10, (byte) (((int) (j9 >> 56)) & 255));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(j11, (byte) (((int) (j9 >> 48)) & 255));
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(j12, (byte) (((int) (j9 >> 40)) & 255));
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(j13, (byte) (((int) (j9 >> 32)) & 255));
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(j14, (byte) (((int) (j9 >> 24)) & 255));
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(j15, (byte) (((int) (j9 >> 16)) & 255));
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(j16, (byte) (((int) (j9 >> 8)) & 255));
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(j17, (byte) (((int) j9) & 255));
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj, Schema schema) {
            writeTag(i9, 4);
            schema.writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i9) {
            if (i9 >= 0) {
                writeVarint32(i9);
            } else {
                writeVarint64(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i9) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i9));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j9) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                nextBuffer(iRemaining);
            }
            this.pos -= iRemaining;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0044  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00a7  */
        @Override // com.google.protobuf.BinaryWriter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void writeString(String str) {
            char cCharAt;
            requireSpace(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length < 0 || (cCharAt = str.charAt(length)) >= 128) {
                    break;
                }
                long j9 = this.pos;
                this.pos = j9 - 1;
                UnsafeUtil.putByte(j9, (byte) cCharAt);
            }
            if (length == -1) {
                return;
            }
            while (length >= 0) {
                char cCharAt2 = str.charAt(length);
                if (cCharAt2 < 128) {
                    long j10 = this.pos;
                    if (j10 >= this.bufferOffset) {
                        this.pos = j10 - 1;
                        UnsafeUtil.putByte(j10, (byte) cCharAt2);
                    } else if (cCharAt2 < 2048) {
                        long j11 = this.pos;
                        if (j11 > this.bufferOffset) {
                            this.pos = j11 - 1;
                            UnsafeUtil.putByte(j11, (byte) ((cCharAt2 & '?') | 128));
                            long j12 = this.pos;
                            this.pos = j12 - 1;
                            UnsafeUtil.putByte(j12, (byte) ((cCharAt2 >>> 6) | 960));
                        } else if (cCharAt2 < 55296 || 57343 < cCharAt2) {
                            long j13 = this.pos;
                            if (j13 > this.bufferOffset + 1) {
                                this.pos = j13 - 1;
                                UnsafeUtil.putByte(j13, (byte) ((cCharAt2 & '?') | 128));
                                long j14 = this.pos;
                                this.pos = j14 - 1;
                                UnsafeUtil.putByte(j14, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                                long j15 = this.pos;
                                this.pos = j15 - 1;
                                UnsafeUtil.putByte(j15, (byte) ((cCharAt2 >>> '\f') | 480));
                            } else {
                                if (this.pos > this.bufferOffset + 2) {
                                    if (length != 0) {
                                        char cCharAt3 = str.charAt(length - 1);
                                        if (Character.isSurrogatePair(cCharAt3, cCharAt2)) {
                                            length--;
                                            int codePoint = Character.toCodePoint(cCharAt3, cCharAt2);
                                            long j16 = this.pos;
                                            this.pos = j16 - 1;
                                            UnsafeUtil.putByte(j16, (byte) ((codePoint & 63) | 128));
                                            long j17 = this.pos;
                                            this.pos = j17 - 1;
                                            UnsafeUtil.putByte(j17, (byte) (((codePoint >>> 6) & 63) | 128));
                                            long j18 = this.pos;
                                            this.pos = j18 - 1;
                                            UnsafeUtil.putByte(j18, (byte) (((codePoint >>> 12) & 63) | 128));
                                            long j19 = this.pos;
                                            this.pos = j19 - 1;
                                            UnsafeUtil.putByte(j19, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                        }
                                    }
                                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                                }
                                requireSpace(length);
                                length++;
                            }
                        }
                    }
                }
                length--;
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            } else {
                this.pos -= iRemaining;
                this.buffer.position(bufferPos() + 1);
                this.buffer.put(byteBuffer);
            }
        }
    }

    public static final class UnsafeHeapWriter extends BinaryWriter {
        private AllocatedBuffer allocatedBuffer;
        private byte[] buffer;
        private long limit;
        private long limitMinusOne;
        private long offset;
        private long offsetMinusOne;
        private long pos;

        public UnsafeHeapWriter(BufferAllocator bufferAllocator, int i9) {
            super(bufferAllocator, i9, null);
            nextBuffer();
        }

        private int arrayPos() {
            return (int) this.pos;
        }

        public static boolean isSupported() {
            return UnsafeUtil.hasUnsafeArrayOperations();
        }

        private void nextBuffer() {
            nextBuffer(newHeapBuffer());
        }

        private void writeVarint32FiveBytes(int i9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, (byte) (i9 >>> 28));
            byte[] bArr2 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr2, j10, (byte) (((i9 >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr3, j11, (byte) (((i9 >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr4, j12, (byte) (((i9 >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr5, j13, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint32FourBytes(int i9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, (byte) (i9 >>> 21));
            byte[] bArr2 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr2, j10, (byte) (((i9 >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr3, j11, (byte) (((i9 >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr4, j12, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint32OneByte(int i9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, (byte) i9);
        }

        private void writeVarint32ThreeBytes(int i9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, (byte) (i9 >>> 14));
            byte[] bArr2 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr2, j10, (byte) (((i9 >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr3, j11, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, (byte) (i9 >>> 7));
            byte[] bArr2 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr2, j10, (byte) ((i9 & 127) | 128));
        }

        private void writeVarint64EightBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 49));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 42) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 35) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((j9 >>> 28) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) (((j9 >>> 21) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(bArr6, j15, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(bArr7, j16, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(bArr8, j17, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 28));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64FourBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 21));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64NineBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 56));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 49) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 42) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((j9 >>> 35) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) (((j9 >>> 28) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(bArr6, j15, (byte) (((j9 >>> 21) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(bArr7, j16, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(bArr8, j17, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr9 = this.buffer;
            long j18 = this.pos;
            this.pos = j18 - 1;
            UnsafeUtil.putByte(bArr9, j18, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64OneByte(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) j9);
        }

        private void writeVarint64SevenBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 42));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 35) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 28) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((j9 >>> 21) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(bArr6, j15, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(bArr7, j16, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64SixBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 35));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 28) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 21) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(bArr6, j15, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64TenBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 63));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 56) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((j9 >>> 49) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((j9 >>> 42) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) (((j9 >>> 35) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(bArr6, j15, (byte) (((j9 >>> 28) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(bArr7, j16, (byte) (((j9 >>> 21) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(bArr8, j17, (byte) (((j9 >>> 14) & 127) | 128));
            byte[] bArr9 = this.buffer;
            long j18 = this.pos;
            this.pos = j18 - 1;
            UnsafeUtil.putByte(bArr9, j18, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr10 = this.buffer;
            long j19 = this.pos;
            this.pos = j19 - 1;
            UnsafeUtil.putByte(bArr10, j19, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (((int) j9) >>> 14));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((j9 >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) ((j9 & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (j9 >>> 7));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) ((((int) j9) & 127) | 128));
        }

        public int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.allocatedBuffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.allocatedBuffer.position((arrayPos() - this.allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i9) {
            if (spaceLeft() < i9) {
                nextBuffer(i9);
            }
        }

        public int spaceLeft() {
            return (int) (this.pos - this.offsetMinusOne);
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, b9);
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i9, boolean z8) {
            requireSpace(6);
            write(z8 ? (byte) 1 : (byte) 0);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i9, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i9, 2);
            } catch (IOException e9) {
                throw new RuntimeException(e9);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i9) {
            writeTag(i9, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i9, int i10) {
            requireSpace(9);
            writeFixed32(i10);
            writeTag(i9, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i9, long j9) {
            requireSpace(13);
            writeFixed64(j9);
            writeTag(i9, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj) {
            writeTag(i9, 4);
            Protobuf.getInstance().writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i9, int i10) {
            requireSpace(15);
            writeInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i9, int i10) {
            if (i9 < 0 || i9 + i10 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            if (spaceLeft() >= i10) {
                this.pos -= i10;
                System.arraycopy(bArr, i9, this.buffer, arrayPos() + 1, i10);
            } else {
                this.totalDoneBytes += i10;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i9, i10));
                nextBuffer();
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.getInstance().writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i9, int i10) {
            requireSpace(10);
            writeSInt32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i9, long j9) {
            requireSpace(15);
            writeSInt64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i9) {
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i9, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i9, int i10) {
            writeVarint32(WireFormat.makeTag(i9, i10));
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i9, int i10) {
            requireSpace(10);
            writeVarint32(i10);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i9, long j9) {
            requireSpace(15);
            writeVarint64(j9);
            writeTag(i9, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i9) {
            if ((i9 & (-128)) == 0) {
                writeVarint32OneByte(i9);
                return;
            }
            if ((i9 & (-16384)) == 0) {
                writeVarint32TwoBytes(i9);
                return;
            }
            if (((-2097152) & i9) == 0) {
                writeVarint32ThreeBytes(i9);
            } else if (((-268435456) & i9) == 0) {
                writeVarint32FourBytes(i9);
            } else {
                writeVarint32FiveBytes(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j9) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j9)) {
                case 1:
                    writeVarint64OneByte(j9);
                    break;
                case 2:
                    writeVarint64TwoBytes(j9);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j9);
                    break;
                case 4:
                    writeVarint64FourBytes(j9);
                    break;
                case 5:
                    writeVarint64FiveBytes(j9);
                    break;
                case 6:
                    writeVarint64SixBytes(j9);
                    break;
                case 7:
                    writeVarint64SevenBytes(j9);
                    break;
                case 8:
                    writeVarint64EightBytes(j9);
                    break;
                case 9:
                    writeVarint64NineBytes(j9);
                    break;
                case 10:
                    writeVarint64TenBytes(j9);
                    break;
            }
        }

        private void nextBuffer(int i9) {
            nextBuffer(newHeapBuffer(i9));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i9, int i10) {
            if (i9 < 0 || i9 + i10 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i9), Integer.valueOf(i10)));
            }
            requireSpace(i10);
            this.pos -= i10;
            System.arraycopy(bArr, i9, this.buffer, arrayPos() + 1, i10);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasArray()) {
                finishCurrentBuffer();
                this.buffers.addFirst(allocatedBuffer);
                this.allocatedBuffer = allocatedBuffer;
                this.buffer = allocatedBuffer.array();
                int iArrayOffset = allocatedBuffer.arrayOffset();
                this.limit = allocatedBuffer.limit() + iArrayOffset;
                long jPosition = iArrayOffset + allocatedBuffer.position();
                this.offset = jPosition;
                this.offsetMinusOne = jPosition - 1;
                long j9 = this.limit - 1;
                this.limitMinusOne = j9;
                this.pos = j9;
                return;
            }
            throw new RuntimeException("Allocator returned non-heap buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z8) {
            write(z8 ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i9) {
            byte[] bArr = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr, j9, (byte) ((i9 >> 24) & 255));
            byte[] bArr2 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr2, j10, (byte) ((i9 >> 16) & 255));
            byte[] bArr3 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr3, j11, (byte) ((i9 >> 8) & 255));
            byte[] bArr4 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr4, j12, (byte) (i9 & 255));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j9) {
            byte[] bArr = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr, j10, (byte) (((int) (j9 >> 56)) & 255));
            byte[] bArr2 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr2, j11, (byte) (((int) (j9 >> 48)) & 255));
            byte[] bArr3 = this.buffer;
            long j12 = this.pos;
            this.pos = j12 - 1;
            UnsafeUtil.putByte(bArr3, j12, (byte) (((int) (j9 >> 40)) & 255));
            byte[] bArr4 = this.buffer;
            long j13 = this.pos;
            this.pos = j13 - 1;
            UnsafeUtil.putByte(bArr4, j13, (byte) (((int) (j9 >> 32)) & 255));
            byte[] bArr5 = this.buffer;
            long j14 = this.pos;
            this.pos = j14 - 1;
            UnsafeUtil.putByte(bArr5, j14, (byte) (((int) (j9 >> 24)) & 255));
            byte[] bArr6 = this.buffer;
            long j15 = this.pos;
            this.pos = j15 - 1;
            UnsafeUtil.putByte(bArr6, j15, (byte) (((int) (j9 >> 16)) & 255));
            byte[] bArr7 = this.buffer;
            long j16 = this.pos;
            this.pos = j16 - 1;
            UnsafeUtil.putByte(bArr7, j16, (byte) (((int) (j9 >> 8)) & 255));
            byte[] bArr8 = this.buffer;
            long j17 = this.pos;
            this.pos = j17 - 1;
            UnsafeUtil.putByte(bArr8, j17, (byte) (((int) j9) & 255));
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i9, Object obj, Schema schema) {
            writeTag(i9, 4);
            schema.writeTo(obj, this);
            writeTag(i9, 3);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i9) {
            if (i9 >= 0) {
                writeVarint32(i9);
            } else {
                writeVarint64(i9);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i9) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i9));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j9) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j9));
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i9, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i9, 2);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00b5  */
        @Override // com.google.protobuf.BinaryWriter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void writeString(String str) {
            char cCharAt;
            requireSpace(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length < 0 || (cCharAt = str.charAt(length)) >= 128) {
                    break;
                }
                byte[] bArr = this.buffer;
                long j9 = this.pos;
                this.pos = j9 - 1;
                UnsafeUtil.putByte(bArr, j9, (byte) cCharAt);
            }
            if (length == -1) {
                return;
            }
            while (length >= 0) {
                char cCharAt2 = str.charAt(length);
                if (cCharAt2 < 128) {
                    long j10 = this.pos;
                    if (j10 > this.offsetMinusOne) {
                        byte[] bArr2 = this.buffer;
                        this.pos = j10 - 1;
                        UnsafeUtil.putByte(bArr2, j10, (byte) cCharAt2);
                    } else if (cCharAt2 < 2048) {
                        long j11 = this.pos;
                        if (j11 > this.offset) {
                            byte[] bArr3 = this.buffer;
                            this.pos = j11 - 1;
                            UnsafeUtil.putByte(bArr3, j11, (byte) ((cCharAt2 & '?') | 128));
                            byte[] bArr4 = this.buffer;
                            long j12 = this.pos;
                            this.pos = j12 - 1;
                            UnsafeUtil.putByte(bArr4, j12, (byte) ((cCharAt2 >>> 6) | 960));
                        } else if (cCharAt2 < 55296 || 57343 < cCharAt2) {
                            long j13 = this.pos;
                            if (j13 > this.offset + 1) {
                                byte[] bArr5 = this.buffer;
                                this.pos = j13 - 1;
                                UnsafeUtil.putByte(bArr5, j13, (byte) ((cCharAt2 & '?') | 128));
                                byte[] bArr6 = this.buffer;
                                long j14 = this.pos;
                                this.pos = j14 - 1;
                                UnsafeUtil.putByte(bArr6, j14, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                                byte[] bArr7 = this.buffer;
                                long j15 = this.pos;
                                this.pos = j15 - 1;
                                UnsafeUtil.putByte(bArr7, j15, (byte) ((cCharAt2 >>> '\f') | 480));
                            } else {
                                if (this.pos > this.offset + 2) {
                                    if (length != 0) {
                                        char cCharAt3 = str.charAt(length - 1);
                                        if (Character.isSurrogatePair(cCharAt3, cCharAt2)) {
                                            length--;
                                            int codePoint = Character.toCodePoint(cCharAt3, cCharAt2);
                                            byte[] bArr8 = this.buffer;
                                            long j16 = this.pos;
                                            this.pos = j16 - 1;
                                            UnsafeUtil.putByte(bArr8, j16, (byte) ((codePoint & 63) | 128));
                                            byte[] bArr9 = this.buffer;
                                            long j17 = this.pos;
                                            this.pos = j17 - 1;
                                            UnsafeUtil.putByte(bArr9, j17, (byte) (((codePoint >>> 6) & 63) | 128));
                                            byte[] bArr10 = this.buffer;
                                            long j18 = this.pos;
                                            this.pos = j18 - 1;
                                            UnsafeUtil.putByte(bArr10, j18, (byte) (((codePoint >>> 12) & 63) | 128));
                                            byte[] bArr11 = this.buffer;
                                            long j19 = this.pos;
                                            this.pos = j19 - 1;
                                            UnsafeUtil.putByte(bArr11, j19, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                        }
                                    }
                                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                                }
                                requireSpace(length);
                                length++;
                            }
                        }
                    }
                }
                length--;
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            requireSpace(iRemaining);
            this.pos -= iRemaining;
            byteBuffer.get(this.buffer, arrayPos() + 1, iRemaining);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            this.pos -= iRemaining;
            byteBuffer.get(this.buffer, arrayPos() + 1, iRemaining);
        }
    }

    public /* synthetic */ BinaryWriter(BufferAllocator bufferAllocator, int i9, C43821 c43821) {
        this(bufferAllocator, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte computeUInt64SizeNoTag(long j9) {
        byte b9;
        if (((-128) & j9) == 0) {
            return (byte) 1;
        }
        if (j9 < 0) {
            return (byte) 10;
        }
        if (((-34359738368L) & j9) != 0) {
            b9 = (byte) 6;
            j9 >>>= 28;
        } else {
            b9 = 2;
        }
        if (((-2097152) & j9) != 0) {
            b9 = (byte) (b9 + 2);
            j9 >>>= 14;
        }
        return (j9 & (-16384)) != 0 ? (byte) (b9 + 1) : b9;
    }

    public static boolean isUnsafeDirectSupported() {
        return UnsafeDirectWriter.isSupported();
    }

    public static boolean isUnsafeHeapSupported() {
        return UnsafeHeapWriter.isSupported();
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator) {
        return newDirectInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator) {
        return newHeapInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newSafeDirectInstance(BufferAllocator bufferAllocator, int i9) {
        return new SafeDirectWriter(bufferAllocator, i9);
    }

    public static BinaryWriter newSafeHeapInstance(BufferAllocator bufferAllocator, int i9) {
        return new SafeHeapWriter(bufferAllocator, i9);
    }

    public static BinaryWriter newUnsafeDirectInstance(BufferAllocator bufferAllocator, int i9) {
        if (isUnsafeDirectSupported()) {
            return new UnsafeDirectWriter(bufferAllocator, i9);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    public static BinaryWriter newUnsafeHeapInstance(BufferAllocator bufferAllocator, int i9) {
        if (isUnsafeHeapSupported()) {
            return new UnsafeHeapWriter(bufferAllocator, i9);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    private final void writeBoolList_Internal(int i9, List<Boolean> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeBool(i9, list.get(size).booleanValue());
            }
            return;
        }
        requireSpace(list.size() + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeBool(list.get(size2).booleanValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeDoubleList_Internal(int i9, List<Double> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeDouble(i9, list.get(size).doubleValue());
            }
            return;
        }
        requireSpace((list.size() * 8) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFixed64(Double.doubleToRawLongBits(list.get(size2).doubleValue()));
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeFixed32List_Internal(int i9, List<Integer> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed32(i9, list.get(size).intValue());
            }
            return;
        }
        requireSpace((list.size() * 4) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFixed32(list.get(size2).intValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeFixed64List_Internal(int i9, List<Long> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFixed64(i9, list.get(size).longValue());
            }
            return;
        }
        requireSpace((list.size() * 8) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFixed64(list.get(size2).longValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeFloatList_Internal(int i9, List<Float> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeFloat(i9, list.get(size).floatValue());
            }
            return;
        }
        requireSpace((list.size() * 4) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeFixed32(Float.floatToRawIntBits(list.get(size2).floatValue()));
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeInt32List_Internal(int i9, List<Integer> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeInt32(i9, list.get(size).intValue());
            }
            return;
        }
        requireSpace((list.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeInt32(list.get(size2).intValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private void writeLazyString(int i9, Object obj) {
        if (obj instanceof String) {
            writeString(i9, (String) obj);
        } else {
            writeBytes(i9, (ByteString) obj);
        }
    }

    public static final void writeMapEntryField(Writer writer, int i9, WireFormat.FieldType fieldType, Object obj) {
        switch (C43821.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                writer.writeBool(i9, ((Boolean) obj).booleanValue());
                return;
            case 2:
                writer.writeFixed32(i9, ((Integer) obj).intValue());
                return;
            case 3:
                writer.writeFixed64(i9, ((Long) obj).longValue());
                return;
            case 4:
                writer.writeInt32(i9, ((Integer) obj).intValue());
                return;
            case 5:
                writer.writeInt64(i9, ((Long) obj).longValue());
                return;
            case 6:
                writer.writeSFixed32(i9, ((Integer) obj).intValue());
                return;
            case 7:
                writer.writeSFixed64(i9, ((Long) obj).longValue());
                return;
            case 8:
                writer.writeSInt32(i9, ((Integer) obj).intValue());
                return;
            case 9:
                writer.writeSInt64(i9, ((Long) obj).longValue());
                return;
            case 10:
                writer.writeString(i9, (String) obj);
                return;
            case 11:
                writer.writeUInt32(i9, ((Integer) obj).intValue());
                return;
            case 12:
                writer.writeUInt64(i9, ((Long) obj).longValue());
                return;
            case 13:
                writer.writeFloat(i9, ((Float) obj).floatValue());
                return;
            case 14:
                writer.writeDouble(i9, ((Double) obj).doubleValue());
                return;
            case 15:
                writer.writeMessage(i9, obj);
                return;
            case 16:
                writer.writeBytes(i9, (ByteString) obj);
                return;
            case 17:
                if (obj instanceof Internal.EnumLite) {
                    writer.writeEnum(i9, ((Internal.EnumLite) obj).getNumber());
                    return;
                } else {
                    if (!(obj instanceof Integer)) {
                        throw new IllegalArgumentException("Unexpected type for enum in map.");
                    }
                    writer.writeEnum(i9, ((Integer) obj).intValue());
                    return;
                }
            default:
                throw new IllegalArgumentException("Unsupported map value type for: " + fieldType);
        }
    }

    private final void writeSInt32List_Internal(int i9, List<Integer> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeSInt32(i9, list.get(size).intValue());
            }
            return;
        }
        requireSpace((list.size() * 5) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeSInt32(list.get(size2).intValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeSInt64List_Internal(int i9, List<Long> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeSInt64(i9, list.get(size).longValue());
            }
            return;
        }
        requireSpace((list.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeSInt64(list.get(size2).longValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeUInt32List_Internal(int i9, List<Integer> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeUInt32(i9, list.get(size).intValue());
            }
            return;
        }
        requireSpace((list.size() * 5) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeVarint32(list.get(size2).intValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    private final void writeUInt64List_Internal(int i9, List<Long> list, boolean z8) {
        if (!z8) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeUInt64(i9, list.get(size).longValue());
            }
            return;
        }
        requireSpace((list.size() * 10) + 10);
        int totalBytesWritten = getTotalBytesWritten();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeVarint64(list.get(size2).longValue());
        }
        writeVarint32(getTotalBytesWritten() - totalBytesWritten);
        writeTag(i9, 2);
    }

    public final Queue<AllocatedBuffer> complete() {
        finishCurrentBuffer();
        return this.buffers;
    }

    @Override // com.google.protobuf.Writer
    public final Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.DESCENDING;
    }

    public abstract void finishCurrentBuffer();

    public abstract int getTotalBytesWritten();

    public final AllocatedBuffer newDirectBuffer() {
        return this.alloc.allocateDirectBuffer(this.chunkSize);
    }

    public final AllocatedBuffer newHeapBuffer() {
        return this.alloc.allocateHeapBuffer(this.chunkSize);
    }

    public abstract void requireSpace(int i9);

    public abstract void writeBool(boolean z8);

    @Override // com.google.protobuf.Writer
    public final void writeBoolList(int i9, List<Boolean> list, boolean z8) {
        if (list instanceof BooleanArrayList) {
            writeBoolList_Internal(i9, (BooleanArrayList) list, z8);
        } else {
            writeBoolList_Internal(i9, list, z8);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeBytesList(int i9, List<ByteString> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeBytes(i9, list.get(size));
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeDouble(int i9, double d9) {
        writeFixed64(i9, Double.doubleToRawLongBits(d9));
    }

    @Override // com.google.protobuf.Writer
    public final void writeDoubleList(int i9, List<Double> list, boolean z8) {
        if (list instanceof DoubleArrayList) {
            writeDoubleList_Internal(i9, (DoubleArrayList) list, z8);
        } else {
            writeDoubleList_Internal(i9, list, z8);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeEnum(int i9, int i10) {
        writeInt32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public final void writeEnumList(int i9, List<Integer> list, boolean z8) {
        writeInt32List(i9, list, z8);
    }

    public abstract void writeFixed32(int i9);

    @Override // com.google.protobuf.Writer
    public final void writeFixed32List(int i9, List<Integer> list, boolean z8) {
        if (list instanceof IntArrayList) {
            writeFixed32List_Internal(i9, (IntArrayList) list, z8);
        } else {
            writeFixed32List_Internal(i9, list, z8);
        }
    }

    public abstract void writeFixed64(long j9);

    @Override // com.google.protobuf.Writer
    public final void writeFixed64List(int i9, List<Long> list, boolean z8) {
        if (list instanceof LongArrayList) {
            writeFixed64List_Internal(i9, (LongArrayList) list, z8);
        } else {
            writeFixed64List_Internal(i9, list, z8);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeFloat(int i9, float f9) {
        writeFixed32(i9, Float.floatToRawIntBits(f9));
    }

    @Override // com.google.protobuf.Writer
    public final void writeFloatList(int i9, List<Float> list, boolean z8) {
        if (list instanceof FloatArrayList) {
            writeFloatList_Internal(i9, (FloatArrayList) list, z8);
        } else {
            writeFloatList_Internal(i9, list, z8);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeGroupList(int i9, List<?> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i9, list.get(size));
        }
    }

    public abstract void writeInt32(int i9);

    @Override // com.google.protobuf.Writer
    public final void writeInt32List(int i9, List<Integer> list, boolean z8) {
        if (list instanceof IntArrayList) {
            writeInt32List_Internal(i9, (IntArrayList) list, z8);
        } else {
            writeInt32List_Internal(i9, list, z8);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeInt64(int i9, long j9) {
        writeUInt64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public final void writeInt64List(int i9, List<Long> list, boolean z8) {
        writeUInt64List(i9, list, z8);
    }

    @Override // com.google.protobuf.Writer
    public <K, V> void writeMap(int i9, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            int totalBytesWritten = getTotalBytesWritten();
            writeMapEntryField(this, 2, metadata.valueType, entry.getValue());
            writeMapEntryField(this, 1, metadata.keyType, entry.getKey());
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageList(int i9, List<?> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i9, list.get(size));
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageSetItem(int i9, Object obj) {
        writeTag(1, 4);
        if (obj instanceof ByteString) {
            writeBytes(3, (ByteString) obj);
        } else {
            writeMessage(3, obj);
        }
        writeUInt32(2, i9);
        writeTag(1, 3);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed32(int i9, int i10) {
        writeFixed32(i9, i10);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed32List(int i9, List<Integer> list, boolean z8) {
        writeFixed32List(i9, list, z8);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed64(int i9, long j9) {
        writeFixed64(i9, j9);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed64List(int i9, List<Long> list, boolean z8) {
        writeFixed64List(i9, list, z8);
    }

    public abstract void writeSInt32(int i9);

    @Override // com.google.protobuf.Writer
    public final void writeSInt32List(int i9, List<Integer> list, boolean z8) {
        if (list instanceof IntArrayList) {
            writeSInt32List_Internal(i9, (IntArrayList) list, z8);
        } else {
            writeSInt32List_Internal(i9, list, z8);
        }
    }

    public abstract void writeSInt64(long j9);

    @Override // com.google.protobuf.Writer
    public final void writeSInt64List(int i9, List<Long> list, boolean z8) {
        if (list instanceof LongArrayList) {
            writeSInt64List_Internal(i9, (LongArrayList) list, z8);
        } else {
            writeSInt64List_Internal(i9, list, z8);
        }
    }

    public abstract void writeString(String str);

    @Override // com.google.protobuf.Writer
    public final void writeStringList(int i9, List<String> list) {
        if (!(list instanceof LazyStringList)) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeString(i9, list.get(size));
            }
            return;
        }
        LazyStringList lazyStringList = (LazyStringList) list;
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeLazyString(i9, lazyStringList.getRaw(size2));
        }
    }

    public abstract void writeTag(int i9, int i10);

    @Override // com.google.protobuf.Writer
    public final void writeUInt32List(int i9, List<Integer> list, boolean z8) {
        if (list instanceof IntArrayList) {
            writeUInt32List_Internal(i9, (IntArrayList) list, z8);
        } else {
            writeUInt32List_Internal(i9, list, z8);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeUInt64List(int i9, List<Long> list, boolean z8) {
        if (list instanceof LongArrayList) {
            writeUInt64List_Internal(i9, (LongArrayList) list, z8);
        } else {
            writeUInt64List_Internal(i9, list, z8);
        }
    }

    public abstract void writeVarint32(int i9);

    public abstract void writeVarint64(long j9);

    private BinaryWriter(BufferAllocator bufferAllocator, int i9) {
        this.buffers = new ArrayDeque<>(4);
        if (i9 <= 0) {
            throw new IllegalArgumentException("chunkSize must be > 0");
        }
        this.alloc = (BufferAllocator) Internal.checkNotNull(bufferAllocator, "alloc");
        this.chunkSize = i9;
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator, int i9) {
        return isUnsafeDirectSupported() ? newUnsafeDirectInstance(bufferAllocator, i9) : newSafeDirectInstance(bufferAllocator, i9);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator, int i9) {
        return isUnsafeHeapSupported() ? newUnsafeHeapInstance(bufferAllocator, i9) : newSafeHeapInstance(bufferAllocator, i9);
    }

    public final AllocatedBuffer newDirectBuffer(int i9) {
        return this.alloc.allocateDirectBuffer(Math.max(i9, this.chunkSize));
    }

    public final AllocatedBuffer newHeapBuffer(int i9) {
        return this.alloc.allocateHeapBuffer(Math.max(i9, this.chunkSize));
    }

    @Override // com.google.protobuf.Writer
    public final void writeGroupList(int i9, List<?> list, Schema schema) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i9, list.get(size), schema);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageList(int i9, List<?> list, Schema schema) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i9, list.get(size), schema);
        }
    }

    private final void writeBoolList_Internal(int i9, BooleanArrayList booleanArrayList, boolean z8) {
        if (z8) {
            requireSpace(booleanArrayList.size() + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = booleanArrayList.size() - 1; size >= 0; size--) {
                writeBool(booleanArrayList.getBoolean(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = booleanArrayList.size() - 1; size2 >= 0; size2--) {
            writeBool(i9, booleanArrayList.getBoolean(size2));
        }
    }

    private final void writeDoubleList_Internal(int i9, DoubleArrayList doubleArrayList, boolean z8) {
        if (z8) {
            requireSpace((doubleArrayList.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = doubleArrayList.size() - 1; size >= 0; size--) {
                writeFixed64(Double.doubleToRawLongBits(doubleArrayList.getDouble(size)));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = doubleArrayList.size() - 1; size2 >= 0; size2--) {
            writeDouble(i9, doubleArrayList.getDouble(size2));
        }
    }

    private final void writeFixed32List_Internal(int i9, IntArrayList intArrayList, boolean z8) {
        if (z8) {
            requireSpace((intArrayList.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeFixed32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeFixed32(i9, intArrayList.getInt(size2));
        }
    }

    private final void writeFixed64List_Internal(int i9, LongArrayList longArrayList, boolean z8) {
        if (z8) {
            requireSpace((longArrayList.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeFixed64(longArrayList.getLong(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            writeFixed64(i9, longArrayList.getLong(size2));
        }
    }

    private final void writeFloatList_Internal(int i9, FloatArrayList floatArrayList, boolean z8) {
        if (z8) {
            requireSpace((floatArrayList.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = floatArrayList.size() - 1; size >= 0; size--) {
                writeFixed32(Float.floatToRawIntBits(floatArrayList.getFloat(size)));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = floatArrayList.size() - 1; size2 >= 0; size2--) {
            writeFloat(i9, floatArrayList.getFloat(size2));
        }
    }

    private final void writeInt32List_Internal(int i9, IntArrayList intArrayList, boolean z8) {
        if (z8) {
            requireSpace((intArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeInt32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeInt32(i9, intArrayList.getInt(size2));
        }
    }

    private final void writeSInt32List_Internal(int i9, IntArrayList intArrayList, boolean z8) {
        if (z8) {
            requireSpace((intArrayList.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeSInt32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeSInt32(i9, intArrayList.getInt(size2));
        }
    }

    private final void writeSInt64List_Internal(int i9, LongArrayList longArrayList, boolean z8) {
        if (z8) {
            requireSpace((longArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeSInt64(longArrayList.getLong(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            writeSInt64(i9, longArrayList.getLong(size2));
        }
    }

    private final void writeUInt32List_Internal(int i9, IntArrayList intArrayList, boolean z8) {
        if (z8) {
            requireSpace((intArrayList.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = intArrayList.size() - 1; size >= 0; size--) {
                writeVarint32(intArrayList.getInt(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = intArrayList.size() - 1; size2 >= 0; size2--) {
            writeUInt32(i9, intArrayList.getInt(size2));
        }
    }

    private final void writeUInt64List_Internal(int i9, LongArrayList longArrayList, boolean z8) {
        if (z8) {
            requireSpace((longArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            for (int size = longArrayList.size() - 1; size >= 0; size--) {
                writeVarint64(longArrayList.getLong(size));
            }
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i9, 2);
            return;
        }
        for (int size2 = longArrayList.size() - 1; size2 >= 0; size2--) {
            writeUInt64(i9, longArrayList.getLong(size2));
        }
    }
}
