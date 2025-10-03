package com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.ExtendableMessageNano;

/* loaded from: classes2.dex */
public abstract class ExtendableMessageNano<M extends ExtendableMessageNano<M>> extends MessageNano {
    protected FieldArray unknownFieldData;

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        if (this.unknownFieldData == null) {
            return 0;
        }
        int iComputeSerializedSize = 0;
        for (int i9 = 0; i9 < this.unknownFieldData.size(); i9++) {
            iComputeSerializedSize += this.unknownFieldData.dataAt(i9).computeSerializedSize();
        }
        return iComputeSerializedSize;
    }

    public final <T> T getExtension(Extension<M, T> extension) {
        FieldData fieldData;
        FieldArray fieldArray = this.unknownFieldData;
        if (fieldArray == null || (fieldData = fieldArray.get(WireFormatNano.getTagFieldNumber(extension.tag))) == null) {
            return null;
        }
        return (T) fieldData.getValue(extension);
    }

    public final boolean hasExtension(Extension<M, ?> extension) {
        FieldArray fieldArray = this.unknownFieldData;
        return (fieldArray == null || fieldArray.get(WireFormatNano.getTagFieldNumber(extension.tag)) == null) ? false : true;
    }

    public final <T> M setExtension(Extension<M, T> extension, T t8) {
        int tagFieldNumber = WireFormatNano.getTagFieldNumber(extension.tag);
        FieldData fieldData = null;
        if (t8 == null) {
            FieldArray fieldArray = this.unknownFieldData;
            if (fieldArray != null) {
                fieldArray.remove(tagFieldNumber);
                if (this.unknownFieldData.isEmpty()) {
                    this.unknownFieldData = null;
                }
            }
        } else {
            FieldArray fieldArray2 = this.unknownFieldData;
            if (fieldArray2 == null) {
                this.unknownFieldData = new FieldArray();
            } else {
                fieldData = fieldArray2.get(tagFieldNumber);
            }
            if (fieldData == null) {
                this.unknownFieldData.put(tagFieldNumber, new FieldData(extension, t8));
            } else {
                fieldData.setValue(extension, t8);
            }
        }
        return this;
    }

    public final boolean storeUnknownField(CodedInputByteBufferNano codedInputByteBufferNano, int i9) {
        FieldData fieldData;
        int position = codedInputByteBufferNano.getPosition();
        if (!codedInputByteBufferNano.skipField(i9)) {
            return false;
        }
        int tagFieldNumber = WireFormatNano.getTagFieldNumber(i9);
        UnknownFieldData unknownFieldData = new UnknownFieldData(i9, codedInputByteBufferNano.getData(position, codedInputByteBufferNano.getPosition() - position));
        FieldArray fieldArray = this.unknownFieldData;
        if (fieldArray == null) {
            this.unknownFieldData = new FieldArray();
            fieldData = null;
        } else {
            fieldData = fieldArray.get(tagFieldNumber);
        }
        if (fieldData == null) {
            fieldData = new FieldData();
            this.unknownFieldData.put(tagFieldNumber, fieldData);
        }
        fieldData.addUnknownField(unknownFieldData);
        return true;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws CodedOutputByteBufferNano.OutOfSpaceException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (this.unknownFieldData == null) {
            return;
        }
        for (int i9 = 0; i9 < this.unknownFieldData.size(); i9++) {
            this.unknownFieldData.dataAt(i9).writeTo(codedOutputByteBufferNano);
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.nano.MessageNano
    /* renamed from: clone */
    public M mo25570clone() {
        M m8 = (M) super.mo25570clone();
        InternalNano.cloneUnknownFieldData(this, m8);
        return m8;
    }
}
