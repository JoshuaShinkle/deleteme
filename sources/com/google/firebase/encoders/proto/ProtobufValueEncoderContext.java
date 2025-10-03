package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* loaded from: classes2.dex */
class ProtobufValueEncoderContext implements ValueEncoderContext {
    private FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    private boolean encoded = false;
    private boolean skipDefault = false;

    public ProtobufValueEncoderContext(ProtobufDataEncoderContext protobufDataEncoderContext) {
        this.objEncoderCtx = protobufDataEncoderContext;
    }

    private void checkNotUsed() {
        if (this.encoded) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.encoded = true;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(String str) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, str, this.skipDefault);
        return this;
    }

    public void resetContext(FieldDescriptor fieldDescriptor, boolean z8) {
        this.encoded = false;
        this.field = fieldDescriptor;
        this.skipDefault = z8;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(float f9) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, f9, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(double d9) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, d9, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(int i9) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, i9, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(long j9) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, j9, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(boolean z8) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, z8, this.skipDefault);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(byte[] bArr) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, bArr, this.skipDefault);
        return this;
    }
}
