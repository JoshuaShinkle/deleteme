package com.cyberlink.you.feedback;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.cyberlink.p030U.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p173q2.C6136j;
import p209u2.AbstractC6381r;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public class UICImageView extends AppCompatImageView {

    /* renamed from: o */
    public static int f13439o;

    /* renamed from: d */
    public String f13440d;

    /* renamed from: e */
    public Bitmap f13441e;

    /* renamed from: f */
    public int f13442f;

    /* renamed from: g */
    public Rect f13443g;

    /* renamed from: h */
    public RectF f13444h;

    /* renamed from: i */
    public int f13445i;

    /* renamed from: j */
    public int f13446j;

    /* renamed from: k */
    public int f13447k;

    /* renamed from: l */
    public boolean f13448l;

    /* renamed from: m */
    public boolean f13449m;

    /* renamed from: n */
    public float f13450n;

    /* renamed from: com.cyberlink.you.feedback.UICImageView$a */
    public class ViewOnTouchListenerC3024a implements View.OnTouchListener {
        public ViewOnTouchListenerC3024a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.feedback.UICImageView$b */
    public class RunnableC3025b implements Runnable {
        public RunnableC3025b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UICImageView uICImageView = UICImageView.this;
            uICImageView.m15470j(uICImageView.f13440d);
        }
    }

    /* renamed from: com.cyberlink.you.feedback.UICImageView$c */
    public class C3026c extends AbstractC6381r<Bitmap, Void> {
        public C3026c() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) {
            UICImageView.this.f13441e = bitmap;
            UICImageView.this.m15476p();
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r12) {
            UICImageView.this.m15474n();
        }
    }

    /* renamed from: com.cyberlink.you.feedback.UICImageView$d */
    public class RunnableC3027d implements Runnable {
        public RunnableC3027d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UICImageView uICImageView = UICImageView.this;
            uICImageView.setImageBitmap(uICImageView.f13441e);
        }
    }

    public UICImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: i */
    public final void m15469i(Bitmap bitmap) {
        float f9;
        float f10;
        if (bitmap == null || !this.f13449m) {
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix imageMatrix = getImageMatrix();
        int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int i9 = width * height2;
        int i10 = width2 * height;
        float f11 = BitmapDescriptorFactory.HUE_RED;
        if (i9 > i10) {
            f10 = height2 / height;
            float f12 = (width2 - (width * f10)) * 0.5f;
            f9 = 0.0f;
            f11 = f12;
        } else {
            float f13 = width2 / width;
            f9 = (height2 - (height * f13)) * 0.5f * this.f13450n;
            f10 = f13;
        }
        imageMatrix.setScale(f10, f10);
        imageMatrix.postTranslate((int) (f11 + 0.5f), (int) (f9 + 0.5f));
        setImageMatrix(imageMatrix);
    }

    /* renamed from: j */
    public final void m15470j(String str) {
        C6136j.m23590j(getContext(), str, 720, new C3026c());
    }

    /* renamed from: k */
    public final boolean m15471k(int i9, int i10) {
        int height;
        int width = this.f13446j;
        if (width == 0 || (height = this.f13447k) == 0) {
            Bitmap bitmap = this.f13441e;
            width = bitmap != null ? bitmap.getWidth() : 0;
            Bitmap bitmap2 = this.f13441e;
            height = bitmap2 != null ? bitmap2.getHeight() : 0;
        }
        if (width != 0 && height != 0) {
            int i11 = this.f13442f;
            if (i11 == 1) {
                setMeasuredDimension(View.MeasureSpec.getSize(i9), (int) Math.ceil((r7 * height) / width));
                return true;
            }
            if (i11 == 2) {
                setMeasuredDimension((int) Math.ceil((r7 * width) / height), View.MeasureSpec.getSize(i10));
                return true;
            }
            if (i11 == 3) {
                int size = View.MeasureSpec.getSize(i9);
                setMeasuredDimension(size, View.MeasureSpec.getSize(i10));
                setScaleType(ImageView.ScaleType.MATRIX);
                Matrix imageMatrix = getImageMatrix();
                float f9 = size / width;
                imageMatrix.setScale(f9, f9, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
                setImageMatrix(imageMatrix);
                return true;
            }
        }
        return false;
    }

    /* renamed from: l */
    public final void m15472l(Context context, AttributeSet attributeSet, int i9) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.UICImageViewArgs, i9, 0);
        this.f13442f = typedArrayObtainStyledAttributes.getInteger(0, 0);
        this.f13445i = typedArrayObtainStyledAttributes.getResourceId(12, R.color.bc_issue_image_background);
        this.f13449m = typedArrayObtainStyledAttributes.getBoolean(1, false);
        this.f13450n = typedArrayObtainStyledAttributes.getFloat(2, 1.0f);
        Rect rect = new Rect();
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, 0);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, 0);
        rect.right = dimensionPixelSize;
        if (rect.top > 0 || rect.bottom > 0 || rect.left > 0 || dimensionPixelSize > 0) {
            this.f13443g = rect;
        }
        RectF rectF = new RectF();
        rectF.top = typedArrayObtainStyledAttributes.getFloat(9, BitmapDescriptorFactory.HUE_RED);
        rectF.bottom = typedArrayObtainStyledAttributes.getFloat(6, BitmapDescriptorFactory.HUE_RED);
        rectF.left = typedArrayObtainStyledAttributes.getFloat(7, BitmapDescriptorFactory.HUE_RED);
        float f9 = typedArrayObtainStyledAttributes.getFloat(8, BitmapDescriptorFactory.HUE_RED);
        rectF.right = f9;
        if (rectF.top > BitmapDescriptorFactory.HUE_RED || rectF.bottom > BitmapDescriptorFactory.HUE_RED || rectF.left > BitmapDescriptorFactory.HUE_RED || f9 > BitmapDescriptorFactory.HUE_RED) {
            this.f13444h = rectF;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    /* renamed from: m */
    public final Bitmap m15473m(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Rect rect = this.f13443g;
        if (rect == null) {
            if (this.f13444h == null) {
                return bitmap;
            }
            int width = (int) (bitmap.getWidth() * this.f13444h.left);
            int height = (int) (bitmap.getHeight() * this.f13444h.top);
            return Bitmap.createBitmap(bitmap, width, height, (int) ((bitmap.getWidth() * (1.0f - this.f13444h.right)) - width), (int) ((bitmap.getHeight() * (1.0f - this.f13444h.bottom)) - height));
        }
        int i9 = rect.left;
        int i10 = rect.top;
        int width2 = bitmap.getWidth();
        Rect rect2 = this.f13443g;
        int i11 = (width2 - rect2.right) - rect2.left;
        int height2 = bitmap.getHeight();
        Rect rect3 = this.f13443g;
        return Bitmap.createBitmap(bitmap, i9, i10, i11, (height2 - rect3.bottom) - rect3.top);
    }

    /* renamed from: n */
    public final void m15474n() {
        int i9 = f13439o;
        if (i9 >= 4 || this.f13440d == null) {
            return;
        }
        f13439o = i9 + 1;
        postDelayed(new RunnableC3025b(), 500L);
    }

    /* renamed from: o */
    public void m15475o(Uri uri, Integer num, Integer num2, String str) {
        if (uri != null) {
            f13439o = 0;
            String str2 = this.f13440d;
            if (str2 != null && str2.equals(uri.toString()) && this.f13441e != null) {
                m15476p();
                return;
            }
        }
        if (this.f13448l || this.f13441e == null) {
            if (str == null) {
                setImageResource(this.f13445i);
            } else {
                try {
                    setImageDrawable(new ColorDrawable(Color.parseColor(str)));
                } catch (Exception unused) {
                    setImageResource(this.f13445i);
                }
            }
        }
        int i9 = this.f13446j;
        int i10 = this.f13447k;
        this.f13446j = num != null ? num.intValue() : 0;
        int iIntValue = num2 != null ? num2.intValue() : 0;
        this.f13447k = iIntValue;
        if (i9 != this.f13446j || i10 != iIntValue) {
            requestLayout();
        }
        this.f13441e = null;
        if (uri == null) {
            this.f13440d = null;
            return;
        }
        String string = uri.toString();
        this.f13440d = string;
        m15470j(string);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i9, int i10) {
        if (this.f13442f == 0 || !m15471k(i9, i10)) {
            super.onMeasure(i9, i10);
        }
        m15476p();
    }

    /* renamed from: p */
    public final void m15476p() {
        if (this.f13441e != null) {
            post(new RunnableC3027d());
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        Bitmap bitmapM15473m = m15473m(bitmap);
        m15469i(bitmapM15473m);
        super.setImageBitmap(bitmapM15473m);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        m15475o(uri, null, null, null);
    }

    public UICImageView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f13440d = null;
        this.f13441e = null;
        this.f13442f = 0;
        this.f13443g = null;
        this.f13444h = null;
        this.f13445i = R.color.bc_issue_image_background;
        this.f13446j = 0;
        this.f13447k = 0;
        this.f13448l = true;
        this.f13449m = false;
        this.f13450n = 1.0f;
        if (isInEditMode()) {
            return;
        }
        m15472l(context, attributeSet, i9);
        setOnTouchListener(new ViewOnTouchListenerC3024a());
    }
}
