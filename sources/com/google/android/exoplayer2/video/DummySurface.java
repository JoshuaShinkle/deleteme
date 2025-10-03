package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

@TargetApi(17)
/* loaded from: classes.dex */
public final class DummySurface extends Surface {
    private static final int EGL_PROTECTED_CONTENT_EXT = 12992;
    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    private static final int SECURE_MODE_NONE = 0;
    private static final int SECURE_MODE_PROTECTED_PBUFFER = 2;
    private static final int SECURE_MODE_SURFACELESS_CONTEXT = 1;
    private static final String TAG = "DummySurface";
    private static int secureMode;
    private static boolean secureModeInitialized;
    public final boolean secure;
    private final DummySurfaceThread thread;
    private boolean threadReleased;

    public static class DummySurfaceThread extends HandlerThread implements SurfaceTexture.OnFrameAvailableListener, Handler.Callback {
        private static final int MSG_INIT = 1;
        private static final int MSG_RELEASE = 3;
        private static final int MSG_UPDATE_TEXTURE = 2;
        private EGLContext context;
        private EGLDisplay display;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private EGLSurface pbuffer;
        private DummySurface surface;
        private SurfaceTexture surfaceTexture;
        private final int[] textureIdHolder;

        public DummySurfaceThread() {
            super("dummySurface");
            this.textureIdHolder = new int[1];
        }

        private void initInternal(int i9) {
            EGLSurface eGLSurface;
            EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
            this.display = eGLDisplayEglGetDisplay;
            Assertions.checkState(eGLDisplayEglGetDisplay != null, "eglGetDisplay failed");
            int[] iArr = new int[2];
            Assertions.checkState(EGL14.eglInitialize(this.display, iArr, 0, iArr, 1), "eglInitialize failed");
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr2 = new int[1];
            Assertions.checkState(EGL14.eglChooseConfig(this.display, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, iArr2, 0) && iArr2[0] > 0 && eGLConfigArr[0] != null, "eglChooseConfig failed");
            EGLConfig eGLConfig = eGLConfigArr[0];
            EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(this.display, eGLConfig, EGL14.EGL_NO_CONTEXT, i9 == 0 ? new int[]{12440, 2, 12344} : new int[]{12440, 2, DummySurface.EGL_PROTECTED_CONTENT_EXT, 1, 12344}, 0);
            this.context = eGLContextEglCreateContext;
            Assertions.checkState(eGLContextEglCreateContext != null, "eglCreateContext failed");
            if (i9 == 1) {
                eGLSurface = EGL14.EGL_NO_SURFACE;
            } else {
                EGLSurface eGLSurfaceEglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(this.display, eGLConfig, i9 == 2 ? new int[]{12375, 1, 12374, 1, DummySurface.EGL_PROTECTED_CONTENT_EXT, 1, 12344} : new int[]{12375, 1, 12374, 1, 12344}, 0);
                this.pbuffer = eGLSurfaceEglCreatePbufferSurface;
                Assertions.checkState(eGLSurfaceEglCreatePbufferSurface != null, "eglCreatePbufferSurface failed");
                eGLSurface = this.pbuffer;
            }
            Assertions.checkState(EGL14.eglMakeCurrent(this.display, eGLSurface, eGLSurface, this.context), "eglMakeCurrent failed");
            GLES20.glGenTextures(1, this.textureIdHolder, 0);
            SurfaceTexture surfaceTexture = new SurfaceTexture(this.textureIdHolder[0]);
            this.surfaceTexture = surfaceTexture;
            surfaceTexture.setOnFrameAvailableListener(this);
            this.surface = new DummySurface(this, this.surfaceTexture, i9 != 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void releaseInternal() {
            try {
                SurfaceTexture surfaceTexture = this.surfaceTexture;
                if (surfaceTexture != null) {
                    surfaceTexture.release();
                    GLES20.glDeleteTextures(1, this.textureIdHolder, 0);
                }
            } finally {
                EGLSurface eGLSurface = this.pbuffer;
                if (eGLSurface != null) {
                    EGL14.eglDestroySurface(this.display, eGLSurface);
                }
                EGLContext eGLContext = this.context;
                if (eGLContext != null) {
                    EGL14.eglDestroyContext(this.display, eGLContext);
                }
                this.pbuffer = null;
                this.context = null;
                this.display = null;
                this.surface = null;
                this.surfaceTexture = null;
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i9 = message.what;
            try {
                if (i9 != 1) {
                    if (i9 == 2) {
                        this.surfaceTexture.updateTexImage();
                        return true;
                    }
                    if (i9 != 3) {
                        return true;
                    }
                    try {
                        releaseInternal();
                    } finally {
                        try {
                            return true;
                        } finally {
                        }
                    }
                    return true;
                }
                try {
                    try {
                        initInternal(message.arg1);
                        synchronized (this) {
                            notify();
                        }
                    } catch (Error e9) {
                        Log.e(DummySurface.TAG, "Failed to initialize dummy surface", e9);
                        this.initError = e9;
                        synchronized (this) {
                            notify();
                        }
                    }
                } catch (RuntimeException e10) {
                    Log.e(DummySurface.TAG, "Failed to initialize dummy surface", e10);
                    this.initException = e10;
                    synchronized (this) {
                        notify();
                    }
                }
                return true;
            } catch (Throwable th) {
                synchronized (this) {
                    notify();
                    throw th;
                }
            }
        }

        public DummySurface init(int i9) {
            boolean z8;
            start();
            this.handler = new Handler(getLooper(), this);
            synchronized (this) {
                z8 = false;
                this.handler.obtainMessage(1, i9, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        z8 = true;
                    }
                }
            }
            if (z8) {
                Thread.currentThread().interrupt();
            }
            RuntimeException runtimeException = this.initException;
            if (runtimeException != null) {
                throw runtimeException;
            }
            Error error = this.initError;
            if (error == null) {
                return this.surface;
            }
            throw error;
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.handler.sendEmptyMessage(2);
        }

        public void release() {
            this.handler.sendEmptyMessage(3);
        }
    }

    private static void assertApiLevel17OrHigher() {
        if (Util.SDK_INT < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
    }

    @TargetApi(24)
    private static int getSecureModeV24(Context context) {
        String strEglQueryString;
        int i9 = Util.SDK_INT;
        if (i9 < 26 && ("samsung".equals(Util.MANUFACTURER) || "XT1650".equals(Util.MODEL))) {
            return 0;
        }
        if ((i9 >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) && (strEglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && strEglQueryString.contains(EXTENSION_PROTECTED_CONTENT)) {
            return strEglQueryString.contains(EXTENSION_SURFACELESS_CONTEXT) ? 1 : 2;
        }
        return 0;
    }

    public static synchronized boolean isSecureSupported(Context context) {
        if (!secureModeInitialized) {
            secureMode = Util.SDK_INT < 24 ? 0 : getSecureModeV24(context);
            secureModeInitialized = true;
        }
        return secureMode != 0;
    }

    public static DummySurface newInstanceV17(Context context, boolean z8) {
        assertApiLevel17OrHigher();
        Assertions.checkState(!z8 || isSecureSupported(context));
        return new DummySurfaceThread().init(z8 ? secureMode : 0);
    }

    @Override // android.view.Surface
    public void release() {
        super.release();
        synchronized (this.thread) {
            if (!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }
        }
    }

    private DummySurface(DummySurfaceThread dummySurfaceThread, SurfaceTexture surfaceTexture, boolean z8) {
        super(surfaceTexture);
        this.thread = dummySurfaceThread;
        this.secure = z8;
    }
}
