package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class AppCompatImageView extends ImageView {

    /* renamed from: b */
    public final C0223d f790b;

    /* renamed from: c */
    public final C0231h f791c;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            c0223d.m799b();
        }
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m874b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            return c0223d.m800c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            return c0223d.m801d();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            return c0231h.m875c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            return c0231h.m876d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f791c.m877e() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            c0223d.m803f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        super.setBackgroundResource(i9);
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            c0223d.m804g(i9);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m874b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m874b();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i9) {
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m879g(i9);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m874b();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            c0223d.m806i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0223d c0223d = this.f790b;
        if (c0223d != null) {
            c0223d.m807j(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m880h(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        C0231h c0231h = this.f791c;
        if (c0231h != null) {
            c0231h.m881i(mode);
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0223d c0223d = new C0223d(this);
        this.f790b = c0223d;
        c0223d.m802e(attributeSet, i9);
        C0231h c0231h = new C0231h(this);
        this.f791c = c0231h;
        c0231h.m878f(attributeSet, i9);
    }
}
