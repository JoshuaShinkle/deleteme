package p041d;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import p224w.C6494a;

/* renamed from: d.a */
/* loaded from: classes.dex */
public class C4612a extends Drawable implements Drawable.Callback {
    private Drawable mDrawable;

    public C4612a(Drawable drawable) {
        setWrappedDrawable(drawable);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.mDrawable.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.mDrawable.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.mDrawable.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    public Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return C6494a.m24840c(this.mDrawable);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDrawable.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.mDrawable.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i9) {
        return this.mDrawable.setLevel(i9);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j9) {
        scheduleSelf(runnable, j9);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i9) {
        this.mDrawable.setAlpha(i9);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z8) {
        C6494a.m24841d(this.mDrawable, z8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i9) {
        this.mDrawable.setChangingConfigurations(i9);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawable.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z8) {
        this.mDrawable.setDither(z8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z8) {
        this.mDrawable.setFilterBitmap(z8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f9, float f10) {
        C6494a.m24842e(this.mDrawable, f9, f10);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i9, int i10, int i11, int i12) {
        C6494a.m24843f(this.mDrawable, i9, i10, i11, i12);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return this.mDrawable.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i9) {
        C6494a.m24845h(this.mDrawable, i9);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        C6494a.m24846i(this.mDrawable, colorStateList);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        C6494a.m24847j(this.mDrawable, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z8, boolean z9) {
        return super.setVisible(z8, z9) || this.mDrawable.setVisible(z8, z9);
    }

    public void setWrappedDrawable(Drawable drawable) {
        Drawable drawable2 = this.mDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
