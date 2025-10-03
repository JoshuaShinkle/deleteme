package p022c1;

import android.graphics.Bitmap;

/* renamed from: c1.e */
/* loaded from: classes.dex */
public class C0708e implements InterfaceC0707d {
    @Override // p022c1.InterfaceC0707d
    /* renamed from: a */
    public void mo3485a(int i9) {
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: b */
    public void mo3486b() {
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: c */
    public void mo3487c(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: d */
    public Bitmap mo3488d(int i9, int i10, Bitmap.Config config) {
        return Bitmap.createBitmap(i9, i10, config);
    }

    @Override // p022c1.InterfaceC0707d
    /* renamed from: e */
    public Bitmap mo3489e(int i9, int i10, Bitmap.Config config) {
        return mo3488d(i9, i10, config);
    }
}
