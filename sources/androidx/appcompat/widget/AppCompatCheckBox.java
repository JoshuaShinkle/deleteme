package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import p010b.C0560a;
import p020c.C0694a;

/* loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox {
    private final C0223d mBackgroundTintHelper;
    private final C0225e mCompoundButtonHelper;
    private final C0241m mTextHelper;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m799b();
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m944b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0225e c0225e = this.mCompoundButtonHelper;
        return c0225e != null ? c0225e.m810b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            return c0223d.m800c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            return c0223d.m801d();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        C0225e c0225e = this.mCompoundButtonHelper;
        if (c0225e != null) {
            return c0225e.m811c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0225e c0225e = this.mCompoundButtonHelper;
        if (c0225e != null) {
            return c0225e.m812d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m803f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        super.setBackgroundResource(i9);
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m804g(i9);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0225e c0225e = this.mCompoundButtonHelper;
        if (c0225e != null) {
            c0225e.m814f();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m806i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0223d c0223d = this.mBackgroundTintHelper;
        if (c0223d != null) {
            c0223d.m807j(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C0225e c0225e = this.mCompoundButtonHelper;
        if (c0225e != null) {
            c0225e.m815g(colorStateList);
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0225e c0225e = this.mCompoundButtonHelper;
        if (c0225e != null) {
            c0225e.m816h(mode);
        }
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0225e c0225e = new C0225e(this);
        this.mCompoundButtonHelper = c0225e;
        c0225e.m813e(attributeSet, i9);
        C0223d c0223d = new C0223d(this);
        this.mBackgroundTintHelper = c0223d;
        c0223d.m802e(attributeSet, i9);
        C0241m c0241m = new C0241m(this);
        this.mTextHelper = c0241m;
        c0241m.m954m(attributeSet, i9);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i9) {
        setButtonDrawable(C0694a.m3458b(getContext(), i9));
    }
}
