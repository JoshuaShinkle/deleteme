package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.C3476R;
import p042d0.C4628h0;
import p042d0.C4647u;
import p042d0.InterfaceC4643q;

/* loaded from: classes2.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {
    Drawable insetForeground;
    Rect insets;
    private Rect tempRect;

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.insets == null || this.insetForeground == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        this.tempRect.set(0, 0, width, this.insets.top);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(canvas);
        this.tempRect.set(0, height - this.insets.bottom, width, height);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(canvas);
        Rect rect = this.tempRect;
        Rect rect2 = this.insets;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(canvas);
        Rect rect3 = this.tempRect;
        Rect rect4 = this.insets;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.insetForeground.setBounds(this.tempRect);
        this.insetForeground.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.insetForeground;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.insetForeground;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void onInsetsChanged(C4628h0 c4628h0) {
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.tempRect = new Rect();
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, C3476R.styleable.ScrimInsetsFrameLayout, i9, C3476R.style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.insetForeground = typedArrayObtainStyledAttributes.getDrawable(C3476R.styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayObtainStyledAttributes.recycle();
        setWillNotDraw(true);
        C4647u.m18554l0(this, new InterfaceC4643q() { // from class: com.google.android.material.internal.ScrimInsetsFrameLayout.1
            @Override // p042d0.InterfaceC4643q
            public C4628h0 onApplyWindowInsets(View view, C4628h0 c4628h0) {
                ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
                if (scrimInsetsFrameLayout.insets == null) {
                    scrimInsetsFrameLayout.insets = new Rect();
                }
                ScrimInsetsFrameLayout.this.insets.set(c4628h0.m18437e(), c4628h0.m18439g(), c4628h0.m18438f(), c4628h0.m18436d());
                ScrimInsetsFrameLayout.this.onInsetsChanged(c4628h0);
                ScrimInsetsFrameLayout.this.setWillNotDraw(!c4628h0.m18441i() || ScrimInsetsFrameLayout.this.insetForeground == null);
                C4647u.m18524T(ScrimInsetsFrameLayout.this);
                return c4628h0.m18435c();
            }
        });
    }
}
