package org.jivesoftware.smack.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: org.jivesoftware.smack.util.h */
/* loaded from: classes.dex */
public class C5614h extends Writer {

    /* renamed from: b */
    public Writer f19522b;

    /* renamed from: c */
    public List<InterfaceC5617k> f19523c = new ArrayList();

    public C5614h(Writer writer) {
        this.f19522b = null;
        this.f19522b = writer;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f19522b.close();
    }

    /* renamed from: f */
    public void m22333f(InterfaceC5617k interfaceC5617k) {
        if (interfaceC5617k == null) {
            return;
        }
        synchronized (this.f19523c) {
            if (!this.f19523c.contains(interfaceC5617k)) {
                this.f19523c.add(interfaceC5617k);
            }
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.f19522b.flush();
    }

    /* renamed from: u */
    public final void m22334u(String str) {
        int size;
        InterfaceC5617k[] interfaceC5617kArr;
        synchronized (this.f19523c) {
            size = this.f19523c.size();
            interfaceC5617kArr = new InterfaceC5617k[size];
            this.f19523c.toArray(interfaceC5617kArr);
        }
        for (int i9 = 0; i9 < size; i9++) {
            interfaceC5617kArr[i9].write(str);
        }
    }

    /* renamed from: v */
    public void m22335v(InterfaceC5617k interfaceC5617k) {
        synchronized (this.f19523c) {
            this.f19523c.remove(interfaceC5617k);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i9, int i10) throws IOException {
        this.f19522b.write(cArr, i9, i10);
        m22334u(new String(cArr, i9, i10));
    }

    @Override // java.io.Writer
    public void write(int i9) throws IOException {
        this.f19522b.write(i9);
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        this.f19522b.write(cArr);
        m22334u(new String(cArr));
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        this.f19522b.write(str);
        m22334u(str);
    }

    @Override // java.io.Writer
    public void write(String str, int i9, int i10) throws IOException {
        this.f19522b.write(str, i9, i10);
        m22334u(str.substring(i9, i10 + i9));
    }
}
