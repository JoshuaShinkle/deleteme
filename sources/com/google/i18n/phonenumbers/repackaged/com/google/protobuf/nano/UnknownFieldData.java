package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedOutputByteBufferNano;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class UnknownFieldData {
    final byte[] bytes;
    final int tag;

    public UnknownFieldData(int i9, byte[] bArr) {
        this.tag = i9;
        this.bytes = bArr;
    }

    public int computeSerializedSize() {
        return CodedOutputByteBufferNano.computeRawVarint32Size(this.tag) + 0 + this.bytes.length;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UnknownFieldData)) {
            return false;
        }
        UnknownFieldData unknownFieldData = (UnknownFieldData) obj;
        return this.tag == unknownFieldData.tag && Arrays.equals(this.bytes, unknownFieldData.bytes);
    }

    public int hashCode() {
        return ((527 + this.tag) * 31) + Arrays.hashCode(this.bytes);
    }

    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws CodedOutputByteBufferNano.OutOfSpaceException {
        codedOutputByteBufferNano.writeRawVarint32(this.tag);
        codedOutputByteBufferNano.writeRawBytes(this.bytes);
    }
}
