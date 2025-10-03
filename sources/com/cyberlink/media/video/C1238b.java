package com.cyberlink.media.video;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.cyberlink.media.video.b */
/* loaded from: classes.dex */
public final class C1238b extends GLSurfaceView implements GLSurfaceView.Renderer {

    /* renamed from: b */
    public b f6002b;

    /* renamed from: c */
    public boolean f6003c;

    /* renamed from: d */
    public final Runnable f6004d;

    /* renamed from: com.cyberlink.media.video.b$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C1238b.this.f6002b != null) {
                C1238b.this.f6002b.mo5459a();
            }
        }
    }

    /* renamed from: com.cyberlink.media.video.b$b */
    public interface b extends GLSurfaceView.Renderer {
        /* renamed from: a */
        void mo5459a();
    }

    public C1238b(Context context) {
        super(context);
        this.f6004d = new a();
    }

    /* renamed from: c */
    public final void m5458c() {
        synchronized (this) {
            if (this.f6003c) {
                this.f6003c = false;
                if (this.f6002b != null) {
                    queueEvent(this.f6004d);
                }
            }
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        b bVar = this.f6002b;
        if (bVar != null) {
            bVar.onDrawFrame(gl10);
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        m5458c();
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i9, int i10) {
        b bVar = this.f6002b;
        if (bVar != null) {
            bVar.onSurfaceChanged(gl10, i9, i10);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        synchronized (this) {
            this.f6003c = true;
        }
        b bVar = this.f6002b;
        if (bVar != null) {
            bVar.onSurfaceCreated(gl10, eGLConfig);
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        if (renderer instanceof b) {
            this.f6002b = (b) renderer;
            super.setRenderer(this);
        } else {
            this.f6002b = null;
            super.setRenderer(renderer);
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        m5458c();
        super.surfaceDestroyed(surfaceHolder);
    }
}
