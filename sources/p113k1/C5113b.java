package p113k1;

import p012b1.InterfaceC0595j;
import p226w1.C6516i;

/* renamed from: k1.b */
/* loaded from: classes.dex */
public class C5113b implements InterfaceC0595j<byte[]> {

    /* renamed from: b */
    public final byte[] f17591b;

    public C5113b(byte[] bArr) {
        this.f17591b = (byte[]) C6516i.m24938d(bArr);
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public byte[] get() {
        return this.f17591b;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: b */
    public void mo3281b() {
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: c */
    public int mo3282c() {
        return this.f17591b.length;
    }

    @Override // p012b1.InterfaceC0595j
    /* renamed from: d */
    public Class<byte[]> mo3283d() {
        return byte[].class;
    }
}
