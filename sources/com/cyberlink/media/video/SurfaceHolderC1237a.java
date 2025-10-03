package com.cyberlink.media.video;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: com.cyberlink.media.video.a */
/* loaded from: classes.dex */
public class SurfaceHolderC1237a implements SurfaceHolder {

    /* renamed from: b */
    public final Set<SurfaceHolder.Callback> f5998b = Collections.newSetFromMap(new IdentityHashMap());

    /* renamed from: c */
    public volatile Surface f5999c;

    /* renamed from: com.cyberlink.media.video.a$a */
    public static class a extends SurfaceHolderC1237a {

        /* renamed from: d */
        public final WeakReference<View> f6000d;

        public a(View view) {
            this.f6000d = new WeakReference<>(view);
        }

        @Override // com.cyberlink.media.video.SurfaceHolderC1237a, android.view.SurfaceHolder
        public void setKeepScreenOn(boolean z8) {
            View view = this.f6000d.get();
            if (view != null) {
                view.setKeepScreenOn(z8);
            }
        }
    }

    @TargetApi(14)
    /* renamed from: com.cyberlink.media.video.a$b */
    public static class b extends a implements TextureView.SurfaceTextureListener {

        /* renamed from: e */
        public SurfaceTexture f6001e;

        public b(TextureView textureView) {
            super(textureView);
            textureView.setSurfaceTextureListener(this);
            if (textureView.isAvailable()) {
                onSurfaceTextureAvailable(textureView.getSurfaceTexture(), textureView.getWidth(), textureView.getHeight());
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i9, int i10) {
            if (this.f6001e == surfaceTexture) {
                return;
            }
            this.f6001e = surfaceTexture;
            m5456d(new Surface(surfaceTexture));
            m5455c(i9, i10);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.f6001e = null;
            m5456d(null);
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i9, int i10) {
            m5455c(i9, i10);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    /* renamed from: a */
    public final List<SurfaceHolder.Callback> m5453a() {
        ArrayList arrayList;
        synchronized (this.f5998b) {
            arrayList = new ArrayList(this.f5998b);
        }
        return arrayList;
    }

    @Override // android.view.SurfaceHolder
    public void addCallback(SurfaceHolder.Callback callback) {
        synchronized (this.f5998b) {
            this.f5998b.add(callback);
        }
    }

    /* renamed from: b */
    public final void m5454b(boolean z8) {
        Log.d("SurfaceTextureHolder", "notifySurfaceAlive " + z8);
        if (z8) {
            Iterator<SurfaceHolder.Callback> it = m5453a().iterator();
            while (it.hasNext()) {
                it.next().surfaceCreated(this);
            }
        } else {
            Iterator<SurfaceHolder.Callback> it2 = m5453a().iterator();
            while (it2.hasNext()) {
                it2.next().surfaceDestroyed(this);
            }
        }
    }

    /* renamed from: c */
    public void m5455c(int i9, int i10) {
        Log.d("SurfaceTextureHolder", "notifySurfaceSizeChanged " + i9 + "x" + i10);
        Iterator<SurfaceHolder.Callback> it = m5453a().iterator();
        while (it.hasNext()) {
            it.next().surfaceChanged(this, -1, i9, i10);
        }
    }

    /* renamed from: d */
    public void m5456d(Surface surface) {
        if (surface == this.f5999c) {
            return;
        }
        if (this.f5999c != null) {
            this.f5999c = null;
            m5454b(false);
        }
        if (surface != null) {
            this.f5999c = surface;
            m5454b(true);
        }
    }

    @Override // android.view.SurfaceHolder
    public Surface getSurface() {
        return this.f5999c;
    }

    @Override // android.view.SurfaceHolder
    public Rect getSurfaceFrame() {
        return null;
    }

    @Override // android.view.SurfaceHolder
    public boolean isCreating() {
        return false;
    }

    @Override // android.view.SurfaceHolder
    public Canvas lockCanvas() {
        return null;
    }

    @Override // android.view.SurfaceHolder
    public Canvas lockCanvas(Rect rect) {
        return null;
    }

    @Override // android.view.SurfaceHolder
    public void removeCallback(SurfaceHolder.Callback callback) {
        synchronized (this.f5998b) {
            this.f5998b.remove(callback);
        }
    }

    @Override // android.view.SurfaceHolder
    public void setFixedSize(int i9, int i10) {
        throw new UnsupportedOperationException("setFixedSize() is not supported by " + getClass().getName());
    }

    @Override // android.view.SurfaceHolder
    public void setFormat(int i9) {
    }

    @Override // android.view.SurfaceHolder
    public void setKeepScreenOn(boolean z8) {
        throw new UnsupportedOperationException("setKeepScreenOn() is not supported by " + getClass().getName());
    }

    @Override // android.view.SurfaceHolder
    public void setSizeFromLayout() {
    }

    @Override // android.view.SurfaceHolder
    @Deprecated
    public void setType(int i9) {
    }

    @Override // android.view.SurfaceHolder
    public void unlockCanvasAndPost(Canvas canvas) {
    }
}
