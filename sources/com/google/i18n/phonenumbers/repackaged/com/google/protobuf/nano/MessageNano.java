package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public abstract class MessageNano {
    protected volatile int cachedSize = -1;

    public static final <T extends MessageNano> T mergeFrom(T t8, byte[] bArr) {
        return (T) mergeFrom(t8, bArr, 0, bArr.length);
    }

    public static final boolean messageNanoEquals(MessageNano messageNano, MessageNano messageNano2) {
        int serializedSize;
        if (messageNano == messageNano2) {
            return true;
        }
        if (messageNano == null || messageNano2 == null || messageNano.getClass() != messageNano2.getClass() || messageNano2.getSerializedSize() != (serializedSize = messageNano.getSerializedSize())) {
            return false;
        }
        byte[] bArr = new byte[serializedSize];
        byte[] bArr2 = new byte[serializedSize];
        toByteArray(messageNano, bArr, 0, serializedSize);
        toByteArray(messageNano2, bArr2, 0, serializedSize);
        return Arrays.equals(bArr, bArr2);
    }

    public static final byte[] toByteArray(MessageNano messageNano) {
        int serializedSize = messageNano.getSerializedSize();
        byte[] bArr = new byte[serializedSize];
        toByteArray(messageNano, bArr, 0, serializedSize);
        return bArr;
    }

    public int computeSerializedSize() {
        return 0;
    }

    public int getCachedSize() {
        if (this.cachedSize < 0) {
            getSerializedSize();
        }
        return this.cachedSize;
    }

    public int getSerializedSize() {
        int iComputeSerializedSize = computeSerializedSize();
        this.cachedSize = iComputeSerializedSize;
        return iComputeSerializedSize;
    }

    public abstract MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano);

    public String toString() {
        return MessageNanoPrinter.print(this);
    }

    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
    }

    public static final <T extends MessageNano> T mergeFrom(T t8, byte[] bArr, int i9, int i10) throws InvalidProtocolBufferNanoException {
        try {
            CodedInputByteBufferNano codedInputByteBufferNanoNewInstance = CodedInputByteBufferNano.newInstance(bArr, i9, i10);
            t8.mergeFrom(codedInputByteBufferNanoNewInstance);
            codedInputByteBufferNanoNewInstance.checkLastTagWas(0);
            return t8;
        } catch (InvalidProtocolBufferNanoException e9) {
            throw e9;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public MessageNano mo25570clone() {
        return (MessageNano) super.clone();
    }

    public static final void toByteArray(MessageNano messageNano, byte[] bArr, int i9, int i10) {
        try {
            CodedOutputByteBufferNano codedOutputByteBufferNanoNewInstance = CodedOutputByteBufferNano.newInstance(bArr, i9, i10);
            messageNano.writeTo(codedOutputByteBufferNanoNewInstance);
            codedOutputByteBufferNanoNewInstance.checkNoSpaceLeft();
        } catch (IOException e9) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e9);
        }
    }
}
