package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.micro;

import java.io.IOException;

/* loaded from: classes2.dex */
public abstract class MessageMicro {
    public abstract int getCachedSize();

    public abstract int getSerializedSize();

    public abstract MessageMicro mergeFrom(CodedInputStreamMicro codedInputStreamMicro);

    public MessageMicro mergeFrom(byte[] bArr) {
        return mergeFrom(bArr, 0, bArr.length);
    }

    public boolean parseUnknownField(CodedInputStreamMicro codedInputStreamMicro, int i9) {
        return codedInputStreamMicro.skipField(i9);
    }

    public byte[] toByteArray() {
        int serializedSize = getSerializedSize();
        byte[] bArr = new byte[serializedSize];
        toByteArray(bArr, 0, serializedSize);
        return bArr;
    }

    public abstract void writeTo(CodedOutputStreamMicro codedOutputStreamMicro);

    public MessageMicro mergeFrom(byte[] bArr, int i9, int i10) throws InvalidProtocolBufferMicroException {
        try {
            CodedInputStreamMicro codedInputStreamMicroNewInstance = CodedInputStreamMicro.newInstance(bArr, i9, i10);
            mergeFrom(codedInputStreamMicroNewInstance);
            codedInputStreamMicroNewInstance.checkLastTagWas(0);
            return this;
        } catch (InvalidProtocolBufferMicroException e9) {
            throw e9;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public void toByteArray(byte[] bArr, int i9, int i10) {
        try {
            CodedOutputStreamMicro codedOutputStreamMicroNewInstance = CodedOutputStreamMicro.newInstance(bArr, i9, i10);
            writeTo(codedOutputStreamMicroNewInstance);
            codedOutputStreamMicroNewInstance.checkNoSpaceLeft();
        } catch (IOException unused) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }
}
