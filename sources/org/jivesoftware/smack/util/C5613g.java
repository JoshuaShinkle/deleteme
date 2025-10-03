package org.jivesoftware.smack.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/* renamed from: org.jivesoftware.smack.util.g */
/* loaded from: classes.dex */
public class C5613g extends Reader {

    /* renamed from: b */
    public Reader f19520b;

    /* renamed from: c */
    public List<InterfaceC5615i> f19521c = new ArrayList();

    public C5613g(Reader reader) {
        this.f19520b = null;
        this.f19520b = reader;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f19520b.close();
    }

    /* renamed from: f */
    public void m22331f(InterfaceC5615i interfaceC5615i) {
        if (interfaceC5615i == null) {
            return;
        }
        synchronized (this.f19521c) {
            if (!this.f19521c.contains(interfaceC5615i)) {
                this.f19521c.add(interfaceC5615i);
            }
        }
    }

    @Override // java.io.Reader
    public void mark(int i9) throws IOException {
        this.f19520b.mark(i9);
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.f19520b.markSupported();
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i9, int i10) throws IOException {
        int size;
        InterfaceC5615i[] interfaceC5615iArr;
        int i11 = this.f19520b.read(cArr, i9, i10);
        if (i11 > 0) {
            String str = new String(cArr, i9, i11);
            synchronized (this.f19521c) {
                size = this.f19521c.size();
                interfaceC5615iArr = new InterfaceC5615i[size];
                this.f19521c.toArray(interfaceC5615iArr);
            }
            for (int i12 = 0; i12 < size; i12++) {
                interfaceC5615iArr[i12].read(str);
            }
        }
        return i11;
    }

    @Override // java.io.Reader
    public boolean ready() {
        return this.f19520b.ready();
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.f19520b.reset();
    }

    @Override // java.io.Reader
    public long skip(long j9) {
        return this.f19520b.skip(j9);
    }

    /* renamed from: u */
    public void m22332u(InterfaceC5615i interfaceC5615i) {
        synchronized (this.f19521c) {
            this.f19521c.remove(interfaceC5615i);
        }
    }

    @Override // java.io.Reader
    public int read() {
        return this.f19520b.read();
    }

    @Override // java.io.Reader
    public int read(char[] cArr) {
        return this.f19520b.read(cArr);
    }
}
