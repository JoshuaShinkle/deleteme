package p114k2;

import android.opengl.GLException;
import com.cyberlink.media.opengl.GLMoreUtils;
import p125l2.C5281e;
import p176q5.C6162a;

/* renamed from: k2.b */
/* loaded from: classes.dex */
public final class C5115b {

    /* renamed from: a */
    public final int f17592a;

    /* renamed from: b */
    public final int f17593b;

    /* renamed from: c */
    public int f17594c;

    /* renamed from: d */
    public int f17595d;

    /* renamed from: e */
    public InterfaceC5119f f17596e;

    /* renamed from: f */
    public InterfaceC5119f f17597f;

    public C5115b(int i9, int i10) {
        this.f17592a = i9;
        this.f17593b = i10;
    }

    /* renamed from: a */
    public final void m19972a() {
        int i9 = this.f17595d;
        if (i9 == 0) {
            m19975d();
        } else {
            C5117d c5117d = new C5117d(i9);
            try {
                GLMoreUtils.m5386d();
            } finally {
                c5117d.close();
            }
        }
        this.f17596e = new C5116c(this.f17594c);
        this.f17597f = new C5120g(0, 0, this.f17592a, this.f17593b);
        C6162a.glFramebufferTexture2D(36160, 36064, 3553, this.f17595d, 0);
        int iGlCheckFramebufferStatus = C6162a.glCheckFramebufferStatus(36160);
        if (iGlCheckFramebufferStatus == 36053) {
            return;
        }
        throw new GLException(0, "Framebuffer status is " + GLMoreUtils.m5385c(iGlCheckFramebufferStatus));
    }

    /* renamed from: b */
    public void m19973b() {
        InterfaceC5119f interfaceC5119f = this.f17597f;
        if (interfaceC5119f != null) {
            interfaceC5119f.close();
            this.f17597f = null;
        }
        InterfaceC5119f interfaceC5119f2 = this.f17596e;
        if (interfaceC5119f2 != null) {
            interfaceC5119f2.close();
            this.f17596e = null;
        }
    }

    /* renamed from: c */
    public int m19974c() {
        return this.f17595d;
    }

    /* renamed from: d */
    public final void m19975d() {
        int[] iArr = new int[1];
        C6162a.glGenFramebuffers(1, iArr, 0);
        this.f17594c = iArr[0];
        int[] iArr2 = new int[1];
        C6162a.glGenTextures(1, iArr2, 0);
        int i9 = iArr2[0];
        this.f17595d = i9;
        C5117d c5117d = new C5117d(i9);
        try {
            C6162a.glTexImage2D(3553, 0, 6407, this.f17592a, this.f17593b, 0, 6407, 5121, null);
            GLMoreUtils.m5386d();
        } finally {
            c5117d.close();
        }
    }

    /* renamed from: e */
    public void m19976e(boolean z8) {
        if (!z8) {
            int i9 = this.f17595d;
            if (i9 != 0) {
                C6162a.glDeleteTextures(1, new int[]{i9}, 0);
            }
            int i10 = this.f17594c;
            if (i10 != 0) {
                C6162a.glDeleteFramebuffers(1, new int[]{i10}, 0);
            }
        }
        this.f17595d = 0;
        this.f17594c = 0;
    }

    /* renamed from: f */
    public void m19977f() throws Throwable {
        if (this.f17596e != null) {
            throw new IllegalStateException("start() was previous called without finish().");
        }
        try {
            m19972a();
        } catch (Throwable th) {
            m19973b();
            C5281e.m20560a(th);
        }
    }
}
