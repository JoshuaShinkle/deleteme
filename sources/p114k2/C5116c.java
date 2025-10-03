package p114k2;

import android.opengl.GLES20;

/* renamed from: k2.c */
/* loaded from: classes.dex */
public final class C5116c extends AbstractC5118e {
    public C5116c(int i9) {
        this(i9, 36160, 36006);
    }

    @Override // p114k2.AbstractC5118e
    /* renamed from: f */
    public int mo19978f() {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(this.f17599c, iArr, 0);
        return iArr[0];
    }

    @Override // p114k2.AbstractC5118e
    /* renamed from: v */
    public void mo19979v(int i9) {
        GLES20.glBindFramebuffer(this.f17598b, i9);
    }

    public C5116c(int i9, int i10, int i11) {
        super(i9, i10, i11);
    }
}
