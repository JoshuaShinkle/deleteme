package com.cyberlink.you.widgetpool.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import p116k4.C5190x;
import p218v2.C6453a0;

/* loaded from: classes.dex */
public class MultiLangTextView extends TextView {
    public MultiLangTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m17312a(context, attributeSet);
    }

    /* renamed from: a */
    public final void m17312a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6453a0.MultiLangLayoutArgs);
        String string = typedArrayObtainStyledAttributes.getString(1);
        boolean z8 = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
        if (string == null || string.equals("")) {
            return;
        }
        try {
            C5190x.m20276b(this, z8, C5190x.m20275a(string));
        } catch (Exception e9) {
            Log.e("MultiLangTextView", string, e9);
        }
    }

    public MultiLangTextView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        m17312a(context, attributeSet);
    }
}
