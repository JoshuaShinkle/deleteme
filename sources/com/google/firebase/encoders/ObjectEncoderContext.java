package com.google.firebase.encoders;

/* loaded from: classes2.dex */
public interface ObjectEncoderContext {
    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, double d9);

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, float f9);

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, int i9);

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, long j9);

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, Object obj);

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor, boolean z8);

    @Deprecated
    ObjectEncoderContext add(String str, double d9);

    @Deprecated
    ObjectEncoderContext add(String str, int i9);

    @Deprecated
    ObjectEncoderContext add(String str, long j9);

    @Deprecated
    ObjectEncoderContext add(String str, Object obj);

    @Deprecated
    ObjectEncoderContext add(String str, boolean z8);

    ObjectEncoderContext inline(Object obj);

    ObjectEncoderContext nested(FieldDescriptor fieldDescriptor);

    ObjectEncoderContext nested(String str);
}
