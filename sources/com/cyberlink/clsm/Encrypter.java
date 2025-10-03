package com.cyberlink.clsm;

import androidx.annotation.Keep;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class Encrypter extends StreamWriter {

    /* renamed from: e */
    public C1199a f5795e;

    public Encrypter(long j9, OutputStream outputStream) {
        super(j9, m5231nC(j9), outputStream);
    }

    /* renamed from: nC */
    private static native long m5231nC(long j9);

    /* renamed from: nE */
    private native boolean m5232nE(byte[] bArr, int i9, boolean z8);

    /* renamed from: nR */
    private native void m5233nR();

    @Override // com.cyberlink.clsm.StreamWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super.close();
        m5233nR();
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: f */
    public void mo5227f(byte[] bArr) {
        m5232nE(bArr, 0, false);
    }

    @Keep
    public void onEnd(String str, String str2) {
        this.f5795e = C1199a.m5277a(str, str2);
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: v */
    public void mo5229v(byte[] bArr, int i9) {
        m5232nE(bArr, i9, true);
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: w */
    public /* bridge */ /* synthetic */ boolean mo5230w(InputStream inputStream) {
        return super.mo5230w(inputStream);
    }

    /* renamed from: x */
    public C1199a m5234x() {
        return this.f5795e;
    }
}
