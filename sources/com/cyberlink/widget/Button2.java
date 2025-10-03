package com.cyberlink.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class Button2 extends Button {
    public Button2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public void dispatchSetPressed(boolean z8) {
        super.dispatchSetPressed(z8);
        setAlpha(z8 ? 0.3f : 1.0f);
    }

    public Button2(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
    }
}
