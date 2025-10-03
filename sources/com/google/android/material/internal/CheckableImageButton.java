package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import androidx.appcompat.widget.AppCompatImageButton;
import p010b.C0560a;
import p042d0.C4613a;
import p042d0.C4647u;
import p052e0.C4704m;

/* loaded from: classes2.dex */
public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    private static final int[] DRAWABLE_STATE_CHECKED = {R.attr.state_checked};
    private boolean checked;

    public CheckableImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i9) {
        if (!this.checked) {
            return super.onCreateDrawableState(i9);
        }
        int[] iArr = DRAWABLE_STATE_CHECKED;
        return View.mergeDrawableStates(super.onCreateDrawableState(i9 + iArr.length), iArr);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z8) {
        if (this.checked != z8) {
            this.checked = z8;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.imageButtonStyle);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        C4647u.m18530Z(this, new C4613a() { // from class: com.google.android.material.internal.CheckableImageButton.1
            @Override // p042d0.C4613a
            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                accessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
            }

            @Override // p042d0.C4613a
            public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
                super.onInitializeAccessibilityNodeInfo(view, c4704m);
                c4704m.m18779S(true);
                c4704m.m18780T(CheckableImageButton.this.isChecked());
            }
        });
    }
}
