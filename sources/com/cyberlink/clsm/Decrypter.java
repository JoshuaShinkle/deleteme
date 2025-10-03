package com.cyberlink.clsm;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class Decrypter extends StreamWriter {
    public Decrypter(long j9, OutputStream outputStream, C1199a c1199a) {
        super(j9, m5224nC(j9, c1199a.f5810a, c1199a.f5811b), outputStream);
    }

    /* renamed from: nC */
    private static native long m5224nC(long j9, String str, String str2);

    /* renamed from: nD */
    private native boolean m5225nD(byte[] bArr, int i9, boolean z8);

    /* renamed from: nR */
    private native void m5226nR();

    @Override // com.cyberlink.clsm.StreamWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super.close();
        m5226nR();
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: f */
    public void mo5227f(byte[] bArr) {
        m5225nD(bArr, 0, false);
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: u */
    public /* bridge */ /* synthetic */ int mo5228u() {
        return super.mo5228u();
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: v */
    public void mo5229v(byte[] bArr, int i9) {
        m5225nD(bArr, i9, true);
    }

    @Override // com.cyberlink.clsm.StreamWriter
    /* renamed from: w */
    public /* bridge */ /* synthetic */ boolean mo5230w(InputStream inputStream) {
        return super.mo5230w(inputStream);
    }
}
