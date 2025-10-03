package p114k2;

import android.opengl.GLES20;
import java.nio.IntBuffer;

/* renamed from: k2.g */
/* loaded from: classes.dex */
public final class C5120g implements InterfaceC5119f {

    /* renamed from: b */
    public final IntBuffer f17601b = m19981f();

    public C5120g(int i9, int i10, int i11, int i12) {
        GLES20.glViewport(i9, i10, i11, i12);
    }

    @Override // p114k2.InterfaceC5119f, java.lang.AutoCloseable
    public void close() {
        int[] iArrArray = this.f17601b.array();
        GLES20.glViewport(iArrArray[0], iArrArray[1], iArrArray[2], iArrArray[3]);
    }

    /* renamed from: f */
    public final IntBuffer m19981f() {
        IntBuffer intBufferAllocate = IntBuffer.allocate(4);
        GLES20.glGetIntegerv(2978, intBufferAllocate);
        return intBufferAllocate;
    }
}
