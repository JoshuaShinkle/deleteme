package p114k2;

import android.opengl.GLSurfaceView;

/* renamed from: k2.h */
/* loaded from: classes.dex */
public class C5121h implements InterfaceC5114a {

    /* renamed from: a */
    public final GLSurfaceView f17602a;

    public C5121h(GLSurfaceView gLSurfaceView) {
        this.f17602a = gLSurfaceView;
    }

    @Override // p114k2.InterfaceC5114a
    /* renamed from: a */
    public void mo19970a() {
        this.f17602a.requestRender();
    }

    @Override // p114k2.InterfaceC5114a
    /* renamed from: b */
    public void mo19971b(Runnable runnable) {
        this.f17602a.queueEvent(runnable);
    }

    /* renamed from: c */
    public GLSurfaceView m19982c() {
        return this.f17602a;
    }
}
