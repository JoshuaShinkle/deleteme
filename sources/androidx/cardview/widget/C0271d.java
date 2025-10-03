package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* renamed from: androidx.cardview.widget.d */
/* loaded from: classes.dex */
public class C0271d extends Drawable {

    /* renamed from: a */
    public float f1251a;

    /* renamed from: c */
    public final RectF f1253c;

    /* renamed from: d */
    public final Rect f1254d;

    /* renamed from: e */
    public float f1255e;

    /* renamed from: h */
    public ColorStateList f1258h;

    /* renamed from: i */
    public PorterDuffColorFilter f1259i;

    /* renamed from: j */
    public ColorStateList f1260j;

    /* renamed from: f */
    public boolean f1256f = false;

    /* renamed from: g */
    public boolean f1257g = true;

    /* renamed from: k */
    public PorterDuff.Mode f1261k = PorterDuff.Mode.SRC_IN;

    /* renamed from: b */
    public final Paint f1252b = new Paint(5);

    public C0271d(ColorStateList colorStateList, float f9) {
        this.f1251a = f9;
        m1124e(colorStateList);
        this.f1253c = new RectF();
        this.f1254d = new Rect();
    }

    /* renamed from: a */
    public final PorterDuffColorFilter m1120a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* renamed from: b */
    public ColorStateList m1121b() {
        return this.f1258h;
    }

    /* renamed from: c */
    public float m1122c() {
        return this.f1255e;
    }

    /* renamed from: d */
    public float m1123d() {
        return this.f1251a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z8;
        Paint paint = this.f1252b;
        if (this.f1259i == null || paint.getColorFilter() != null) {
            z8 = false;
        } else {
            paint.setColorFilter(this.f1259i);
            z8 = true;
        }
        RectF rectF = this.f1253c;
        float f9 = this.f1251a;
        canvas.drawRoundRect(rectF, f9, f9, paint);
        if (z8) {
            paint.setColorFilter(null);
        }
    }

    /* renamed from: e */
    public final void m1124e(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f1258h = colorStateList;
        this.f1252b.setColor(colorStateList.getColorForState(getState(), this.f1258h.getDefaultColor()));
    }

    /* renamed from: f */
    public void m1125f(ColorStateList colorStateList) {
        m1124e(colorStateList);
        invalidateSelf();
    }

    /* renamed from: g */
    public void m1126g(float f9, boolean z8, boolean z9) {
        if (f9 == this.f1255e && this.f1256f == z8 && this.f1257g == z9) {
            return;
        }
        this.f1255e = f9;
        this.f1256f = z8;
        this.f1257g = z9;
        m1128i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f1254d, this.f1251a);
    }

    /* renamed from: h */
    public void m1127h(float f9) {
        if (f9 == this.f1251a) {
            return;
        }
        this.f1251a = f9;
        m1128i(null);
        invalidateSelf();
    }

    /* renamed from: i */
    public final void m1128i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f1253c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f1254d.set(rect);
        if (this.f1256f) {
            this.f1254d.inset((int) Math.ceil(C0272e.m1129a(this.f1255e, this.f1251a, this.f1257g)), (int) Math.ceil(C0272e.m1130b(this.f1255e, this.f1251a, this.f1257g)));
            this.f1253c.set(this.f1254d);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f1260j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f1258h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        m1128i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f1258h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z8 = colorForState != this.f1252b.getColor();
        if (z8) {
            this.f1252b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f1260j;
        if (colorStateList2 == null || (mode = this.f1261k) == null) {
            return z8;
        }
        this.f1259i = m1120a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        this.f1252b.setAlpha(i9);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1252b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f1260j = colorStateList;
        this.f1259i = m1120a(colorStateList, this.f1261k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f1261k = mode;
        this.f1259i = m1120a(this.f1260j, mode);
        invalidateSelf();
    }
}
