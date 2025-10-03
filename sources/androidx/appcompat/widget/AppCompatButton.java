package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.core.widget.C0337l;
import androidx.core.widget.InterfaceC0327b;
import p010b.C0560a;

/* loaded from: classes.dex */
public class AppCompatButton extends Button implements InterfaceC0327b {
    private final C0223d mBackgroundTintHelper;
    private final C0241m mTextHelper;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
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

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            return c0241m.m946e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeMinTextSize();
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            return c0241m.m947f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeStepGranularity();
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            return c0241m.m948g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        C0241m c0241m = this.mTextHelper;
        return c0241m != null ? c0241m.m949h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            return c0241m.m950i();
        }
        return 0;
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

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.m951j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.m952k();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m956o(z8, i9, i10, i11, i12);
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        super.onTextChanged(charSequence, i9, i10, i11);
        C0241m c0241m = this.mTextHelper;
        if (c0241m == null || InterfaceC0327b.f1850a || !c0241m.m953l()) {
            return;
        }
        this.mTextHelper.m945c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i9, int i10, int i11, int i12) {
        if (InterfaceC0327b.f1850a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i9, i10, i11, i12);
            return;
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m960s(i9, i10, i11, i12);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i9) {
        if (InterfaceC0327b.f1850a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i9);
            return;
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m961t(iArr, i9);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i9) {
        if (InterfaceC0327b.f1850a) {
            super.setAutoSizeTextTypeWithDefaults(i9);
            return;
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m962u(i9);
        }
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

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0337l.m1622q(this, callback));
    }

    public void setSupportAllCaps(boolean z8) {
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m959r(z8);
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

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.m963v(colorStateList);
        this.mTextHelper.m944b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.m964w(mode);
        this.mTextHelper.m944b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i9) {
        super.setTextAppearance(context, i9);
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m958q(context, i9);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i9, float f9) {
        if (InterfaceC0327b.f1850a) {
            super.setTextSize(i9, f9);
            return;
        }
        C0241m c0241m = this.mTextHelper;
        if (c0241m != null) {
            c0241m.m967z(i9, f9);
        }
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0223d c0223d = new C0223d(this);
        this.mBackgroundTintHelper = c0223d;
        c0223d.m802e(attributeSet, i9);
        C0241m c0241m = new C0241m(this);
        this.mTextHelper = c0241m;
        c0241m.m954m(attributeSet, i9);
        c0241m.m944b();
    }
}
