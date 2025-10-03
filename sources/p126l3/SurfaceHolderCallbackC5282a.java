package p126l3;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

/* renamed from: l3.a */
/* loaded from: classes.dex */
public class SurfaceHolderCallbackC5282a extends SurfaceView implements SurfaceHolder.Callback {

    /* renamed from: b */
    public SurfaceHolder f17890b;

    /* renamed from: c */
    public Camera f17891c;

    public SurfaceHolderCallbackC5282a(Context context, Camera camera) {
        super(context);
        this.f17891c = camera;
        SurfaceHolder holder = getHolder();
        this.f17890b = holder;
        holder.addCallback(this);
        this.f17890b.setType(3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i9, int i10, int i11) throws IOException {
        if (this.f17890b.getSurface() == null) {
            return;
        }
        try {
            this.f17891c.stopPreview();
        } catch (Exception unused) {
        }
        try {
            this.f17891c.setPreviewDisplay(this.f17890b);
            this.f17891c.startPreview();
        } catch (Exception e9) {
            Log.d("CameraPreview", "Error starting camera preview: " + e9.getMessage());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) throws IOException {
        try {
            this.f17891c.setPreviewDisplay(surfaceHolder);
            this.f17891c.startPreview();
        } catch (IOException e9) {
            Log.d("CameraPreview", "Error setting camera preview: " + e9.getMessage());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
