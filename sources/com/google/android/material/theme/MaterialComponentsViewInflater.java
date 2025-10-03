package com.google.android.material.theme;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Keep;
import androidx.appcompat.app.C0125g;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.button.MaterialButton;

@Keep
/* loaded from: classes2.dex */
public class MaterialComponentsViewInflater extends C0125g {
    @Override // androidx.appcompat.app.C0125g
    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }
}
