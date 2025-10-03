package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import p010b.C0569j;

/* loaded from: classes.dex */
public final class ViewStubCompat extends View {

    /* renamed from: b */
    public int f1015b;

    /* renamed from: c */
    public int f1016c;

    /* renamed from: d */
    public WeakReference<View> f1017d;

    /* renamed from: e */
    public LayoutInflater f1018e;

    /* renamed from: androidx.appcompat.widget.ViewStubCompat$a */
    public interface InterfaceC0216a {
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: a */
    public View m781a() {
        ViewParent parent = getParent();
        if (!(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
        if (this.f1015b == 0) {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        LayoutInflater layoutInflaterFrom = this.f1018e;
        if (layoutInflaterFrom == null) {
            layoutInflaterFrom = LayoutInflater.from(getContext());
        }
        View viewInflate = layoutInflaterFrom.inflate(this.f1015b, viewGroup, false);
        int i9 = this.f1016c;
        if (i9 != -1) {
            viewInflate.setId(i9);
        }
        int iIndexOfChild = viewGroup.indexOfChild(this);
        viewGroup.removeViewInLayout(this);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            viewGroup.addView(viewInflate, iIndexOfChild, layoutParams);
        } else {
            viewGroup.addView(viewInflate, iIndexOfChild);
        }
        this.f1017d = new WeakReference<>(viewInflate);
        return viewInflate;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f1016c;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f1018e;
    }

    public int getLayoutResource() {
        return this.f1015b;
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i9) {
        this.f1016c = i9;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f1018e = layoutInflater;
    }

    public void setLayoutResource(int i9) {
        this.f1015b = i9;
    }

    public void setOnInflateListener(InterfaceC0216a interfaceC0216a) {
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
        WeakReference<View> weakReference = this.f1017d;
        if (weakReference != null) {
            View view = weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i9);
            return;
        }
        super.setVisibility(i9);
        if (i9 == 0 || i9 == 4) {
            m781a();
        }
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f1015b = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.ViewStubCompat, i9, 0);
        this.f1016c = typedArrayObtainStyledAttributes.getResourceId(C0569j.ViewStubCompat_android_inflatedId, -1);
        this.f1015b = typedArrayObtainStyledAttributes.getResourceId(C0569j.ViewStubCompat_android_layout, 0);
        setId(typedArrayObtainStyledAttributes.getResourceId(C0569j.ViewStubCompat_android_id, -1));
        typedArrayObtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }
}
