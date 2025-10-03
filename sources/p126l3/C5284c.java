package p126l3;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.TextureView;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import p116k4.C5187v0;
import p145n3.C5359a;
import p145n3.InterfaceC5360b;

/* renamed from: l3.c */
/* loaded from: classes.dex */
public class C5284c {

    /* renamed from: m */
    public static AtomicBoolean f17892m = new AtomicBoolean(false);

    /* renamed from: a */
    public Camera f17893a;

    /* renamed from: b */
    public TextureView f17894b;

    /* renamed from: c */
    public int f17895c;

    /* renamed from: f */
    public int f17898f;

    /* renamed from: g */
    public int f17899g;

    /* renamed from: h */
    public Map<String, Integer> f17900h;

    /* renamed from: i */
    public Handler f17901i;

    /* renamed from: j */
    public InterfaceC5360b<Bitmap> f17902j;

    /* renamed from: d */
    public int f17896d = 1280;

    /* renamed from: e */
    public int f17897e = 720;

    /* renamed from: k */
    public TextureView.SurfaceTextureListener f17903k = new a();

    /* renamed from: l */
    public Camera.PreviewCallback f17904l = new b();

    /* renamed from: l3.c$a */
    public class a implements TextureView.SurfaceTextureListener {
        public a() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i9, int i10) throws IOException {
            C5284c.this.m20574h();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            C5284c.this.m20571e();
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i9, int i10) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    /* renamed from: l3.c$b */
    public class b implements Camera.PreviewCallback {

        /* renamed from: l3.c$b$a */
        public class a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ Camera f17907b;

            /* renamed from: c */
            public final /* synthetic */ byte[] f17908c;

            public a(Camera camera, byte[] bArr) {
                this.f17907b = camera;
                this.f17908c = bArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmapCreateBitmap;
                Camera.Size previewSize = this.f17907b.getParameters().getPreviewSize();
                Bitmap bitmapCreateBitmap2 = null;
                try {
                    Bitmap bitmapM21052b = C5359a.m21052b(C5359a.m21053c(C5359a.m21051a(this.f17908c, previewSize.width, previewSize.height), 270.0f));
                    float fIntValue = (((Integer) C5284c.this.f17900h.get("maskWidth")).intValue() * 1.0f) / bitmapM21052b.getWidth();
                    Matrix matrix = new Matrix();
                    matrix.postScale(fIntValue, fIntValue);
                    bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapM21052b, 0, 0, bitmapM21052b.getWidth(), bitmapM21052b.getHeight(), matrix, true);
                    bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap2, 0, ((Integer) C5284c.this.f17900h.get("maskMarginTop")).intValue(), ((Integer) C5284c.this.f17900h.get("maskWidth")).intValue(), ((Integer) C5284c.this.f17900h.get("maskHeight")).intValue());
                } catch (Exception e9) {
                    Log.d("ResetFaceIdEx", e9.getMessage());
                    bitmapCreateBitmap = bitmapCreateBitmap2;
                }
                C5284c.this.f17902j.mo8198a(bitmapCreateBitmap);
                C5284c.f17892m.set(false);
            }
        }

        public b() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            if (C5284c.f17892m.compareAndSet(false, true)) {
                C5284c.this.f17901i.post(new a(camera, bArr));
            }
        }
    }

    public C5284c(TextureView textureView, Map<String, Integer> map, int i9, InterfaceC5360b<Bitmap> interfaceC5360b) {
        this.f17895c = i9;
        this.f17894b = textureView;
        this.f17900h = map;
        this.f17902j = interfaceC5360b;
    }

    /* renamed from: e */
    public void m20571e() {
        Camera camera = this.f17893a;
        if (camera != null) {
            camera.setPreviewCallback(null);
            this.f17893a.stopPreview();
            this.f17893a.release();
            this.f17893a = null;
        }
    }

    /* renamed from: f */
    public int m20572f() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i9 = -1;
        for (int i10 = 0; i10 < numberOfCameras; i10++) {
            Camera.getCameraInfo(i10, cameraInfo);
            if (cameraInfo.facing == 0) {
                i9 = i10;
            }
        }
        if (i9 != -1 || numberOfCameras <= 0) {
            return i9;
        }
        return 0;
    }

    /* renamed from: g */
    public final Camera.Size m20573g(List<Camera.Size> list, int i9, int i10) {
        double d9 = i10 / i9;
        Camera.Size size = null;
        if (list == null) {
            return null;
        }
        double dAbs = Double.MAX_VALUE;
        double dAbs2 = Double.MAX_VALUE;
        for (Camera.Size size2 : list) {
            if (Math.abs((size2.height / size2.width) - d9) <= 0.1d && Math.abs(size2.height - i10) < dAbs2) {
                dAbs2 = Math.abs(size2.height - i10);
                size = size2;
            }
        }
        if (size == null) {
            for (Camera.Size size3 : list) {
                if (Math.abs(size3.height - i10) < dAbs) {
                    size = size3;
                    dAbs = Math.abs(size3.height - i10);
                }
            }
        }
        return size;
    }

    /* renamed from: h */
    public void m20574h() throws IOException {
        if (!this.f17894b.isAvailable()) {
            this.f17894b.setSurfaceTextureListener(this.f17903k);
            return;
        }
        m20575i();
        if (this.f17895c == -1) {
            this.f17895c = m20572f();
        }
        int i9 = this.f17895c;
        if (i9 == -1) {
            C5187v0.m20268d("No camera");
            return;
        }
        Camera cameraOpen = Camera.open(i9);
        this.f17893a = cameraOpen;
        cameraOpen.setDisplayOrientation(90);
        this.f17893a.setPreviewCallback(this.f17904l);
        Camera.Size sizeM20573g = m20573g(this.f17893a.getParameters().getSupportedPreviewSizes(), this.f17896d, this.f17897e);
        if (sizeM20573g == null) {
            C5187v0.m20268d("No support review size");
            return;
        }
        this.f17898f = sizeM20573g.width;
        this.f17899g = sizeM20573g.height;
        Camera.Parameters parameters = this.f17893a.getParameters();
        parameters.setPreviewSize(this.f17898f, this.f17899g);
        this.f17893a.setParameters(parameters);
        ViewGroup.LayoutParams layoutParams = this.f17894b.getLayoutParams();
        int i10 = this.f17899g;
        int i11 = this.f17898f;
        if (i10 > i11) {
            layoutParams.width = i11;
            layoutParams.height = i10;
        } else {
            layoutParams.width = i10;
            layoutParams.height = i11;
        }
        int i12 = layoutParams.width;
        float fIntValue = (this.f17900h.get("maskWidth").intValue() * 1.0f) / i12;
        int i13 = (int) (i12 * fIntValue);
        int i14 = (int) (layoutParams.height * fIntValue);
        layoutParams.width = i13;
        layoutParams.height = i14;
        this.f17894b.setLayoutParams(layoutParams);
        SurfaceTexture surfaceTexture = this.f17894b.getSurfaceTexture();
        surfaceTexture.setDefaultBufferSize(i13, i14);
        try {
            this.f17893a.setPreviewTexture(surfaceTexture);
            this.f17893a.startPreview();
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: i */
    public void m20575i() {
        HandlerThread handlerThread = new HandlerThread("Camera.image");
        handlerThread.start();
        this.f17901i = new Handler(handlerThread.getLooper());
    }
}
