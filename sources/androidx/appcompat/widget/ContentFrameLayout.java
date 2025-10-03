package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* renamed from: b */
    public TypedValue f839b;

    /* renamed from: c */
    public TypedValue f840c;

    /* renamed from: d */
    public TypedValue f841d;

    /* renamed from: e */
    public TypedValue f842e;

    /* renamed from: f */
    public TypedValue f843f;

    /* renamed from: g */
    public TypedValue f844g;

    /* renamed from: h */
    public final Rect f845h;

    /* renamed from: i */
    public InterfaceC0182a f846i;

    /* renamed from: androidx.appcompat.widget.ContentFrameLayout$a */
    public interface InterfaceC0182a {
        /* renamed from: a */
        void mo349a();

        void onDetachedFromWindow();
    }

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    public void m686a(int i9, int i10, int i11, int i12) {
        this.f845h.set(i9, i10, i11, i12);
        if (C4647u.m18513I(this)) {
            requestLayout();
        }
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f843f == null) {
            this.f843f = new TypedValue();
        }
        return this.f843f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f844g == null) {
            this.f844g = new TypedValue();
        }
        return this.f844g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f841d == null) {
            this.f841d = new TypedValue();
        }
        return this.f841d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f842e == null) {
            this.f842e = new TypedValue();
        }
        return this.f842e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f839b == null) {
            this.f839b = new TypedValue();
        }
        return this.f839b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f840c == null) {
            this.f840c = new TypedValue();
        }
        return this.f840c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        InterfaceC0182a interfaceC0182a = this.f846i;
        if (interfaceC0182a != null) {
            interfaceC0182a.mo349a();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        InterfaceC0182a interfaceC0182a = this.f846i;
        if (interfaceC0182a != null) {
            interfaceC0182a.onDetachedFromWindow();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00db  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onMeasure(int i9, int i10) {
        boolean z8;
        int i11;
        int i12;
        float fraction;
        int i13;
        int i14;
        float fraction2;
        int i15;
        int i16;
        float fraction3;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z9 = true;
        boolean z10 = displayMetrics.widthPixels < displayMetrics.heightPixels;
        int mode = View.MeasureSpec.getMode(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        if (mode != Integer.MIN_VALUE) {
            z8 = false;
        } else {
            TypedValue typedValue = z10 ? this.f842e : this.f841d;
            if (typedValue != null && (i15 = typedValue.type) != 0) {
                if (i15 == 5) {
                    fraction3 = typedValue.getDimension(displayMetrics);
                } else if (i15 == 6) {
                    int i17 = displayMetrics.widthPixels;
                    fraction3 = typedValue.getFraction(i17, i17);
                } else {
                    i16 = 0;
                    if (i16 <= 0) {
                        Rect rect = this.f845h;
                        i9 = View.MeasureSpec.makeMeasureSpec(Math.min(i16 - (rect.left + rect.right), View.MeasureSpec.getSize(i9)), 1073741824);
                        z8 = true;
                    }
                }
                i16 = (int) fraction3;
                if (i16 <= 0) {
                }
            }
        }
        if (mode2 == Integer.MIN_VALUE) {
            TypedValue typedValue2 = z10 ? this.f843f : this.f844g;
            if (typedValue2 != null && (i13 = typedValue2.type) != 0) {
                if (i13 == 5) {
                    fraction2 = typedValue2.getDimension(displayMetrics);
                } else if (i13 == 6) {
                    int i18 = displayMetrics.heightPixels;
                    fraction2 = typedValue2.getFraction(i18, i18);
                } else {
                    i14 = 0;
                    if (i14 > 0) {
                        Rect rect2 = this.f845h;
                        i10 = View.MeasureSpec.makeMeasureSpec(Math.min(i14 - (rect2.top + rect2.bottom), View.MeasureSpec.getSize(i10)), 1073741824);
                    }
                }
                i14 = (int) fraction2;
                if (i14 > 0) {
                }
            }
        }
        super.onMeasure(i9, i10);
        int measuredWidth = getMeasuredWidth();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        if (z8 || mode != Integer.MIN_VALUE) {
            z9 = false;
        } else {
            TypedValue typedValue3 = z10 ? this.f840c : this.f839b;
            if (typedValue3 != null && (i11 = typedValue3.type) != 0) {
                if (i11 == 5) {
                    fraction = typedValue3.getDimension(displayMetrics);
                } else if (i11 == 6) {
                    int i19 = displayMetrics.widthPixels;
                    fraction = typedValue3.getFraction(i19, i19);
                } else {
                    i12 = 0;
                    if (i12 > 0) {
                        Rect rect3 = this.f845h;
                        i12 -= rect3.left + rect3.right;
                    }
                    if (measuredWidth >= i12) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
                    }
                }
                i12 = (int) fraction;
                if (i12 > 0) {
                }
                if (measuredWidth >= i12) {
                }
            }
        }
        if (z9) {
            super.onMeasure(iMakeMeasureSpec, i10);
        }
    }

    public void setAttachListener(InterfaceC0182a interfaceC0182a) {
        this.f846i = interfaceC0182a;
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f845h = new Rect();
    }
}
