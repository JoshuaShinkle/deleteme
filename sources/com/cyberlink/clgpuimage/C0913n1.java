package com.cyberlink.clgpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.view.View;
import com.cyberlink.clgpuimage.GPUImage;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: com.cyberlink.clgpuimage.n1 */
/* loaded from: classes.dex */
public class C0913n1 extends GLSurfaceView {

    /* renamed from: b */
    public GPUImage f4710b;

    /* renamed from: c */
    public C0936w0 f4711c;

    /* renamed from: d */
    public float f4712d;

    public C0913n1(Context context) {
        super(context);
        this.f4712d = BitmapDescriptorFactory.HUE_RED;
        m4360a();
    }

    /* renamed from: a */
    public void m4360a() {
        if (isInEditMode()) {
            return;
        }
        GPUImage gPUImage = new GPUImage(getContext());
        this.f4710b = gPUImage;
        gPUImage.m4155q(this);
    }

    /* renamed from: b */
    public void m4361b(Bitmap bitmap, boolean z8) {
        this.f4710b.m4146g(z8);
        this.f4710b.m4156r(bitmap);
    }

    public Bitmap getBitmap() {
        return this.f4710b.m4147i();
    }

    public C0936w0 getFilter() {
        return this.f4711c;
    }

    public Bitmap getImage() {
        return this.f4710b.m4149k();
    }

    public C0895h1 getRender() {
        GPUImage gPUImage = this.f4710b;
        if (gPUImage != null) {
            return gPUImage.m4152n();
        }
        return null;
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onMeasure(int i9, int i10) {
        if (this.f4712d == BitmapDescriptorFactory.HUE_RED) {
            super.onMeasure(i9, i10);
            return;
        }
        int size = View.MeasureSpec.getSize(i9);
        int size2 = View.MeasureSpec.getSize(i10);
        float f9 = size;
        float f10 = this.f4712d;
        float f11 = size2;
        if (f9 / f10 < f11) {
            size2 = Math.round(f9 / f10);
        } else {
            size = Math.round(f11 * f10);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    public void setFilter(C0936w0 c0936w0) {
        this.f4711c = c0936w0;
        this.f4710b.m4154p(c0936w0);
        requestRender();
    }

    public void setImage(Uri uri) {
        this.f4710b.m4157s(uri);
    }

    public void setRatio(float f9) {
        this.f4712d = f9;
        requestLayout();
        this.f4710b.m4145f();
    }

    public void setScaleType(GPUImage.ScaleType scaleType) {
        this.f4710b.m4159u(scaleType);
    }

    public void setImage(Bitmap bitmap) {
        this.f4710b.m4145f();
        this.f4710b.m4156r(bitmap);
    }
}
