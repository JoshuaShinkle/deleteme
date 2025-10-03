package com.google.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Writer;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class UnknownFieldSetLite {
    private static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    private static final int MIN_CAPACITY = 8;
    private int count;
    private boolean isMutable;
    private int memoizedSerializedSize;
    private Object[] objects;
    private int[] tags;

    private UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    private void ensureCapacity() {
        int i9 = this.count;
        int[] iArr = this.tags;
        if (i9 == iArr.length) {
            int i10 = i9 + (i9 < 4 ? 8 : i9 >> 1);
            this.tags = Arrays.copyOf(iArr, i10);
            this.objects = Arrays.copyOf(this.objects, i10);
        }
    }

    private static boolean equals(int[] iArr, int[] iArr2, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            if (iArr[i10] != iArr2[i10]) {
                return false;
            }
        }
        return true;
    }

    public static UnknownFieldSetLite getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static int hashCode(int[] iArr, int i9) {
        int i10 = 17;
        for (int i11 = 0; i11 < i9; i11++) {
            i10 = (i10 * 31) + iArr[i11];
        }
        return i10;
    }

    private UnknownFieldSetLite mergeFrom(CodedInputStream codedInputStream) {
        int tag;
        do {
            tag = codedInputStream.readTag();
            if (tag == 0) {
                break;
            }
        } while (mergeFieldFrom(tag, codedInputStream));
        return this;
    }

    public static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i9 = unknownFieldSetLite.count + unknownFieldSetLite2.count;
        int[] iArrCopyOf = Arrays.copyOf(unknownFieldSetLite.tags, i9);
        System.arraycopy(unknownFieldSetLite2.tags, 0, iArrCopyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        Object[] objArrCopyOf = Arrays.copyOf(unknownFieldSetLite.objects, i9);
        System.arraycopy(unknownFieldSetLite2.objects, 0, objArrCopyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        return new UnknownFieldSetLite(i9, iArrCopyOf, objArrCopyOf, true);
    }

    public static UnknownFieldSetLite newInstance() {
        return new UnknownFieldSetLite();
    }

    private static void writeField(int i9, Object obj, Writer writer) {
        int tagFieldNumber = WireFormat.getTagFieldNumber(i9);
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            writer.writeInt64(tagFieldNumber, ((Long) obj).longValue());
            return;
        }
        if (tagWireType == 1) {
            writer.writeFixed64(tagFieldNumber, ((Long) obj).longValue());
            return;
        }
        if (tagWireType == 2) {
            writer.writeBytes(tagFieldNumber, (ByteString) obj);
            return;
        }
        if (tagWireType != 3) {
            if (tagWireType != 5) {
                throw new RuntimeException(InvalidProtocolBufferException.invalidWireType());
            }
            writer.writeFixed32(tagFieldNumber, ((Integer) obj).intValue());
        } else if (writer.fieldOrder() == Writer.FieldOrder.ASCENDING) {
            writer.writeStartGroup(tagFieldNumber);
            ((UnknownFieldSetLite) obj).writeTo(writer);
            writer.writeEndGroup(tagFieldNumber);
        } else {
            writer.writeEndGroup(tagFieldNumber);
            ((UnknownFieldSetLite) obj).writeTo(writer);
            writer.writeStartGroup(tagFieldNumber);
        }
    }

    public void checkMutable() {
        if (!this.isMutable) {
            throw new UnsupportedOperationException();
        }
    }

    public int getSerializedSize() {
        int iComputeUInt64Size;
        int i9 = this.memoizedSerializedSize;
        if (i9 != -1) {
            return i9;
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.count; i11++) {
            int i12 = this.tags[i11];
            int tagFieldNumber = WireFormat.getTagFieldNumber(i12);
            int tagWireType = WireFormat.getTagWireType(i12);
            if (tagWireType == 0) {
                iComputeUInt64Size = CodedOutputStream.computeUInt64Size(tagFieldNumber, ((Long) this.objects[i11]).longValue());
            } else if (tagWireType == 1) {
                iComputeUInt64Size = CodedOutputStream.computeFixed64Size(tagFieldNumber, ((Long) this.objects[i11]).longValue());
            } else if (tagWireType == 2) {
                iComputeUInt64Size = CodedOutputStream.computeBytesSize(tagFieldNumber, (ByteString) this.objects[i11]);
            } else if (tagWireType == 3) {
                iComputeUInt64Size = (CodedOutputStream.computeTagSize(tagFieldNumber) * 2) + ((UnknownFieldSetLite) this.objects[i11]).getSerializedSize();
            } else {
                if (tagWireType != 5) {
                    throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                }
                iComputeUInt64Size = CodedOutputStream.computeFixed32Size(tagFieldNumber, ((Integer) this.objects[i11]).intValue());
            }
            i10 += iComputeUInt64Size;
        }
        this.memoizedSerializedSize = i10;
        return i10;
    }

    public int getSerializedSizeAsMessageSet() {
        int i9 = this.memoizedSerializedSize;
        if (i9 != -1) {
            return i9;
        }
        int iComputeRawMessageSetExtensionSize = 0;
        for (int i10 = 0; i10 < this.count; i10++) {
            iComputeRawMessageSetExtensionSize += CodedOutputStream.computeRawMessageSetExtensionSize(WireFormat.getTagFieldNumber(this.tags[i10]), (ByteString) this.objects[i10]);
        }
        this.memoizedSerializedSize = iComputeRawMessageSetExtensionSize;
        return iComputeRawMessageSetExtensionSize;
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    public boolean mergeFieldFrom(int i9, CodedInputStream codedInputStream) throws InvalidProtocolBufferException.InvalidWireTypeException {
        checkMutable();
        int tagFieldNumber = WireFormat.getTagFieldNumber(i9);
        int tagWireType = WireFormat.getTagWireType(i9);
        if (tagWireType == 0) {
            storeField(i9, Long.valueOf(codedInputStream.readInt64()));
            return true;
        }
        if (tagWireType == 1) {
            storeField(i9, Long.valueOf(codedInputStream.readFixed64()));
            return true;
        }
        if (tagWireType == 2) {
            storeField(i9, codedInputStream.readBytes());
            return true;
        }
        if (tagWireType == 3) {
            UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
            unknownFieldSetLite.mergeFrom(codedInputStream);
            codedInputStream.checkLastTagWas(WireFormat.makeTag(tagFieldNumber, 4));
            storeField(i9, unknownFieldSetLite);
            return true;
        }
        if (tagWireType == 4) {
            return false;
        }
        if (tagWireType != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        storeField(i9, Integer.valueOf(codedInputStream.readFixed32()));
        return true;
    }

    public UnknownFieldSetLite mergeLengthDelimitedField(int i9, ByteString byteString) {
        checkMutable();
        if (i9 == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        storeField(WireFormat.makeTag(i9, 2), byteString);
        return this;
    }

    public UnknownFieldSetLite mergeVarintField(int i9, int i10) {
        checkMutable();
        if (i9 == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        storeField(WireFormat.makeTag(i9, 0), Long.valueOf(i10));
        return this;
    }

    public final void printWithIndent(StringBuilder sb, int i9) {
        for (int i10 = 0; i10 < this.count; i10++) {
            MessageLiteToString.printField(sb, i9, String.valueOf(WireFormat.getTagFieldNumber(this.tags[i10])), this.objects[i10]);
        }
    }

    public void storeField(int i9, Object obj) {
        checkMutable();
        ensureCapacity();
        int[] iArr = this.tags;
        int i10 = this.count;
        iArr[i10] = i9;
        this.objects[i10] = obj;
        this.count = i10 + 1;
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream) {
        for (int i9 = 0; i9 < this.count; i9++) {
            codedOutputStream.writeRawMessageSetExtension(WireFormat.getTagFieldNumber(this.tags[i9]), (ByteString) this.objects[i9]);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException.InvalidWireTypeException {
        for (int i9 = 0; i9 < this.count; i9++) {
            int i10 = this.tags[i9];
            int tagFieldNumber = WireFormat.getTagFieldNumber(i10);
            int tagWireType = WireFormat.getTagWireType(i10);
            if (tagWireType == 0) {
                codedOutputStream.writeUInt64(tagFieldNumber, ((Long) this.objects[i9]).longValue());
            } else if (tagWireType == 1) {
                codedOutputStream.writeFixed64(tagFieldNumber, ((Long) this.objects[i9]).longValue());
            } else if (tagWireType == 2) {
                codedOutputStream.writeBytes(tagFieldNumber, (ByteString) this.objects[i9]);
            } else if (tagWireType == 3) {
                codedOutputStream.writeTag(tagFieldNumber, 3);
                ((UnknownFieldSetLite) this.objects[i9]).writeTo(codedOutputStream);
                codedOutputStream.writeTag(tagFieldNumber, 4);
            } else {
                if (tagWireType != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                codedOutputStream.writeFixed32(tagFieldNumber, ((Integer) this.objects[i9]).intValue());
            }
        }
    }

    private UnknownFieldSetLite(int i9, int[] iArr, Object[] objArr, boolean z8) {
        this.memoizedSerializedSize = -1;
        this.count = i9;
        this.tags = iArr;
        this.objects = objArr;
        this.isMutable = z8;
    }

    private static boolean equals(Object[] objArr, Object[] objArr2, int i9) {
        for (int i10 = 0; i10 < i9; i10++) {
            if (!objArr[i10].equals(objArr2[i10])) {
                return false;
            }
        }
        return true;
    }

    private static int hashCode(Object[] objArr, int i9) {
        int iHashCode = 17;
        for (int i10 = 0; i10 < i9; i10++) {
            iHashCode = (iHashCode * 31) + objArr[i10].hashCode();
        }
        return iHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        int i9 = this.count;
        return i9 == unknownFieldSetLite.count && equals(this.tags, unknownFieldSetLite.tags, i9) && equals(this.objects, unknownFieldSetLite.objects, this.count);
    }

    public int hashCode() {
        int i9 = this.count;
        return ((((527 + i9) * 31) + hashCode(this.tags, i9)) * 31) + hashCode(this.objects, this.count);
    }

    public void writeAsMessageSetTo(Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            for (int i9 = this.count - 1; i9 >= 0; i9--) {
                writer.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[i9]), this.objects[i9]);
            }
            return;
        }
        for (int i10 = 0; i10 < this.count; i10++) {
            writer.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[i10]), this.objects[i10]);
        }
    }

    public void writeTo(Writer writer) {
        if (this.count == 0) {
            return;
        }
        if (writer.fieldOrder() == Writer.FieldOrder.ASCENDING) {
            for (int i9 = 0; i9 < this.count; i9++) {
                writeField(this.tags[i9], this.objects[i9], writer);
            }
            return;
        }
        for (int i10 = this.count - 1; i10 >= 0; i10--) {
            writeField(this.tags[i10], this.objects[i10], writer);
        }
    }
}
