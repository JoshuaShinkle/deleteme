package p143n1;

import android.graphics.Bitmap;
import p022c1.InterfaceC0705b;
import p022c1.InterfaceC0707d;
import p225w0.InterfaceC6503a;

/* renamed from: n1.b */
/* loaded from: classes.dex */
public final class C5349b implements InterfaceC6503a.a {

    /* renamed from: a */
    public final InterfaceC0707d f18205a;

    /* renamed from: b */
    public final InterfaceC0705b f18206b;

    public C5349b(InterfaceC0707d interfaceC0707d, InterfaceC0705b interfaceC0705b) {
        this.f18205a = interfaceC0707d;
        this.f18206b = interfaceC0705b;
    }

    @Override // p225w0.InterfaceC6503a.a
    /* renamed from: a */
    public Bitmap mo21003a(int i9, int i10, Bitmap.Config config) {
        return this.f18205a.mo3489e(i9, i10, config);
    }

    @Override // p225w0.InterfaceC6503a.a
    /* renamed from: b */
    public int[] mo21004b(int i9) {
        InterfaceC0705b interfaceC0705b = this.f18206b;
        return interfaceC0705b == null ? new int[i9] : (int[]) interfaceC0705b.mo3481d(i9, int[].class);
    }

    @Override // p225w0.InterfaceC6503a.a
    /* renamed from: c */
    public void mo21005c(Bitmap bitmap) {
        this.f18205a.mo3487c(bitmap);
    }

    @Override // p225w0.InterfaceC6503a.a
    /* renamed from: d */
    public void mo21006d(byte[] bArr) {
        InterfaceC0705b interfaceC0705b = this.f18206b;
        if (interfaceC0705b == null) {
            return;
        }
        interfaceC0705b.put(bArr);
    }

    @Override // p225w0.InterfaceC6503a.a
    /* renamed from: e */
    public byte[] mo21007e(int i9) {
        InterfaceC0705b interfaceC0705b = this.f18206b;
        return interfaceC0705b == null ? new byte[i9] : (byte[]) interfaceC0705b.mo3481d(i9, byte[].class);
    }

    @Override // p225w0.InterfaceC6503a.a
    /* renamed from: f */
    public void mo21008f(int[] iArr) {
        InterfaceC0705b interfaceC0705b = this.f18206b;
        if (interfaceC0705b == null) {
            return;
        }
        interfaceC0705b.put(iArr);
    }
}
