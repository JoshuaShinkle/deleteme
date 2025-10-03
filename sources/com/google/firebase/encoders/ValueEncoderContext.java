package com.google.firebase.encoders;

/* loaded from: classes2.dex */
public interface ValueEncoderContext {
    ValueEncoderContext add(double d9);

    ValueEncoderContext add(float f9);

    ValueEncoderContext add(int i9);

    ValueEncoderContext add(long j9);

    ValueEncoderContext add(String str);

    ValueEncoderContext add(boolean z8);

    ValueEncoderContext add(byte[] bArr);
}
