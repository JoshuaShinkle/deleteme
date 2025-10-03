package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.C0250q0;
import com.google.android.material.C3476R;

/* loaded from: classes2.dex */
public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0250q0 c0250q0M1003u = C0250q0.m1003u(context, attributeSet, C3476R.styleable.TabItem);
        this.text = c0250q0M1003u.m1020p(C3476R.styleable.TabItem_android_text);
        this.icon = c0250q0M1003u.m1011g(C3476R.styleable.TabItem_android_icon);
        this.customLayout = c0250q0M1003u.m1018n(C3476R.styleable.TabItem_android_layout, 0);
        c0250q0M1003u.m1024w();
    }
}
