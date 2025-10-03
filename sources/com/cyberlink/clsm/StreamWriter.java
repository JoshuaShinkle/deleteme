package com.cyberlink.clsm;

import androidx.annotation.Keep;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import p209u2.C6370g;

/* loaded from: classes.dex */
abstract class StreamWriter implements Closeable {

    /* renamed from: b */
    public final OutputStream f5807b;

    @Keep
    final long clientPtr;

    @Keep
    final long nativePtr;

    /* renamed from: c */
    public volatile boolean f5808c = false;

    /* renamed from: d */
    public volatile int f5809d = 0;

    public StreamWriter(long j9, long j10, OutputStream outputStream) {
        this.clientPtr = j9;
        this.nativePtr = j10;
        this.f5807b = outputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        C6370g.m24480b(this.f5807b);
    }

    /* renamed from: f */
    public abstract void mo5227f(byte[] bArr);

    @Keep
    public void onBytes(byte[] bArr) throws IOException {
        this.f5807b.write(bArr);
    }

    @Keep
    public void onError(int i9) {
        this.f5808c = true;
        this.f5809d = i9;
    }

    /* renamed from: u */
    public int mo5228u() {
        return this.f5809d;
    }

    /* renamed from: v */
    public abstract void mo5229v(byte[] bArr, int i9);

    /* renamed from: w */
    public boolean mo5230w(InputStream inputStream) {
        int i9;
        byte[] bArr = new byte[UserMetadata.MAX_INTERNAL_KEY_SIZE];
        while (!this.f5808c && (i9 = inputStream.read(bArr)) != -1) {
            mo5229v(bArr, i9);
        }
        mo5227f(bArr);
        return !this.f5808c;
    }
}
