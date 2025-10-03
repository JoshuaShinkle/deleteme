package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.core.widget.C0337l;
import androidx.core.widget.InterfaceC0327b;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import p011b0.C0582m;
import p020c.C0694a;
import p215v.C6430d;

/* loaded from: classes.dex */
public class AppCompatTextView extends TextView implements InterfaceC0327b {

    /* renamed from: b */
    public final C0223d f831b;

    /* renamed from: c */
    public final C0241m f832c;

    /* renamed from: d */
    public final C0237k f833d;

    /* renamed from: e */
    public Future<C0582m> f834e;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    /* renamed from: c */
    public final void m683c() {
        Future<C0582m> future = this.f834e;
        if (future != null) {
            try {
                this.f834e = null;
                C0337l.m1619n(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            c0223d.m799b();
        }
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m944b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeMaxTextSize();
        }
        C0241m c0241m = this.f832c;
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
        C0241m c0241m = this.f832c;
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
        C0241m c0241m = this.f832c;
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
        C0241m c0241m = this.f832c;
        return c0241m != null ? c0241m.m949h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (InterfaceC0327b.f1850a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            return c0241m.m950i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return C0337l.m1607b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return C0337l.m1608c(this);
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            return c0223d.m800c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            return c0223d.m801d();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f832c.m951j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f832c.m952k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        m683c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        C0237k c0237k;
        return (Build.VERSION.SDK_INT >= 28 || (c0237k = this.f833d) == null) ? super.getTextClassifier() : c0237k.m909a();
    }

    public C0582m.a getTextMetricsParamsCompat() {
        return C0337l.m1612g(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return C0229g.m845a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m956o(z8, i9, i10, i11, i12);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i9, int i10) {
        m683c();
        super.onMeasure(i9, i10);
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        super.onTextChanged(charSequence, i9, i10, i11);
        C0241m c0241m = this.f832c;
        if (c0241m == null || InterfaceC0327b.f1850a || !c0241m.m953l()) {
            return;
        }
        this.f832c.m945c();
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i9, int i10, int i11, int i12) {
        if (InterfaceC0327b.f1850a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i9, i10, i11, i12);
            return;
        }
        C0241m c0241m = this.f832c;
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
        C0241m c0241m = this.f832c;
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
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m962u(i9);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            c0223d.m803f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        super.setBackgroundResource(i9);
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            c0223d.m804g(i9);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m957p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m957p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m957p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m957p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0337l.m1622q(this, callback));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i9) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i9);
        } else {
            C0337l.m1616k(this, i9);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i9) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i9);
        } else {
            C0337l.m1617l(this, i9);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i9) {
        C0337l.m1618m(this, i9);
    }

    public void setPrecomputedText(C0582m c0582m) {
        C0337l.m1619n(this, c0582m);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            c0223d.m806i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0223d c0223d = this.f831b;
        if (c0223d != null) {
            c0223d.m807j(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.f832c.m963v(colorStateList);
        this.f832c.m944b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.f832c.m964w(mode);
        this.f832c.m944b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i9) {
        super.setTextAppearance(context, i9);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m958q(context, i9);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        C0237k c0237k;
        if (Build.VERSION.SDK_INT >= 28 || (c0237k = this.f833d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            c0237k.m910b(textClassifier);
        }
    }

    public void setTextFuture(Future<C0582m> future) {
        this.f834e = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(C0582m.a aVar) {
        C0337l.m1621p(this, aVar);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i9, float f9) {
        if (InterfaceC0327b.f1850a) {
            super.setTextSize(i9, f9);
            return;
        }
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m967z(i9, f9);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i9) {
        Typeface typefaceM24594a = (typeface == null || i9 <= 0) ? null : C6430d.m24594a(getContext(), typeface, i9);
        if (typefaceM24594a != null) {
            typeface = typefaceM24594a;
        }
        super.setTypeface(typeface, i9);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i9) {
        super(C0244n0.m998b(context), attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0223d c0223d = new C0223d(this);
        this.f831b = c0223d;
        c0223d.m802e(attributeSet, i9);
        C0241m c0241m = new C0241m(this);
        this.f832c = c0241m;
        c0241m.m954m(attributeSet, i9);
        c0241m.m944b();
        this.f833d = new C0237k(this);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i9, int i10, int i11, int i12) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i9 != 0 ? C0694a.m3458b(context, i9) : null, i10 != 0 ? C0694a.m3458b(context, i10) : null, i11 != 0 ? C0694a.m3458b(context, i11) : null, i12 != 0 ? C0694a.m3458b(context, i12) : null);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m957p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i9, int i10, int i11, int i12) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i9 != 0 ? C0694a.m3458b(context, i9) : null, i10 != 0 ? C0694a.m3458b(context, i10) : null, i11 != 0 ? C0694a.m3458b(context, i11) : null, i12 != 0 ? C0694a.m3458b(context, i12) : null);
        C0241m c0241m = this.f832c;
        if (c0241m != null) {
            c0241m.m957p();
        }
    }
}
