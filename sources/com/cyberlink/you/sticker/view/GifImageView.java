package com.cyberlink.you.sticker.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class GifImageView extends View {

    /* renamed from: b */
    public Movie f14349b;

    /* renamed from: c */
    public long f14350c;

    /* renamed from: d */
    public int f14351d;

    /* renamed from: e */
    public int f14352e;

    /* renamed from: f */
    public float f14353f;

    /* renamed from: g */
    public float f14354g;

    /* renamed from: h */
    public boolean f14355h;

    public GifImageView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f14349b = null;
        this.f14353f = 1.0f;
        this.f14354g = 1.0f;
        this.f14355h = false;
        setLayerType(1, null);
    }

    /* renamed from: a */
    public void m16300a() {
        this.f14355h = false;
        this.f14350c = 0L;
    }

    /* renamed from: b */
    public void m16301b() {
        this.f14355h = true;
    }

    /* renamed from: c */
    public final byte[] m16302c(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(UserMetadata.MAX_ATTRIBUTE_SIZE);
        byte[] bArr = new byte[UserMetadata.MAX_ATTRIBUTE_SIZE];
        while (true) {
            try {
                int i9 = inputStream.read(bArr);
                if (i9 < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i9);
            } catch (IOException e9) {
                e9.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.drawColor(-1);
        Movie movie = this.f14349b;
        if (movie != null) {
            if (this.f14355h) {
                movie.setTime(0);
            } else {
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (this.f14350c == 0) {
                    this.f14350c = jUptimeMillis;
                }
                this.f14349b.setTime((int) ((jUptimeMillis - this.f14350c) % this.f14349b.duration()));
            }
            canvas.save();
            canvas.scale(this.f14353f, this.f14354g);
            this.f14349b.draw(canvas, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            canvas.restore();
            if (this.f14355h) {
                return;
            }
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        Movie movie = this.f14349b;
        if (movie != null) {
            this.f14351d = movie.width();
            this.f14352e = this.f14349b.height();
        }
        if (mode == 1073741824) {
            if (this.f14349b != null) {
                this.f14353f = size / this.f14351d;
            }
        } else if (mode != Integer.MIN_VALUE) {
            size = this.f14351d;
            this.f14353f = 1.0f;
        } else if (this.f14349b != null) {
            int i11 = this.f14351d;
            if (size > i11) {
                size = i11;
            }
            this.f14353f = size / i11;
        }
        if (mode2 == 1073741824) {
            if (this.f14349b != null) {
                this.f14354g = size2 / this.f14352e;
            }
        } else if (mode2 != Integer.MIN_VALUE) {
            float f9 = this.f14353f;
            if (f9 != 1.0f) {
                size2 = Math.round(this.f14352e * f9);
                this.f14354g = this.f14353f;
            } else {
                size2 = this.f14352e;
                this.f14354g = 1.0f;
            }
        } else if (this.f14349b != null) {
            int i12 = this.f14352e;
            int i13 = size2 > i12 ? i12 : size2;
            if (size2 > i12) {
                float f10 = this.f14353f;
                if (f10 != 1.0f) {
                    size2 = Math.round(i12 * f10);
                    this.f14354g = this.f14353f;
                } else {
                    this.f14354g = i12 / i12;
                    size2 = i12;
                }
            } else {
                size2 = i13;
            }
        }
        setMeasuredDimension(size, size2);
    }

    public void setGifImage(InputStream inputStream) throws IOException {
        byte[] bArrM16302c = m16302c(inputStream);
        this.f14349b = Movie.decodeByteArray(bArrM16302c, 0, bArrM16302c.length);
        this.f14350c = 0L;
        this.f14355h = false;
        setLayerType(1, null);
        requestLayout();
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14349b = null;
        this.f14353f = 1.0f;
        this.f14354g = 1.0f;
        this.f14355h = false;
        setLayerType(1, null);
    }

    public void setGifImage(ByteArrayOutputStream byteArrayOutputStream) {
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        this.f14349b = Movie.decodeByteArray(byteArray, 0, byteArray.length);
        this.f14350c = 0L;
        this.f14355h = false;
        setLayerType(1, null);
        requestLayout();
    }

    public void setGifImage(File file) throws IOException {
        if (file == null || file.toString().length() == 0) {
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file.toString());
            setGifImage(fileInputStream);
            fileInputStream.close();
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }
}
