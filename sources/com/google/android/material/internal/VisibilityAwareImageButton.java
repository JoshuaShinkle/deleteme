package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* loaded from: classes2.dex */
public class VisibilityAwareImageButton extends ImageButton {
    private int userSetVisibility;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    public final void internalSetVisibility(int i9, boolean z8) {
        super.setVisibility(i9);
        if (z8) {
            this.userSetVisibility = i9;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i9) {
        internalSetVisibility(i9, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.userSetVisibility = getVisibility();
    }
}
