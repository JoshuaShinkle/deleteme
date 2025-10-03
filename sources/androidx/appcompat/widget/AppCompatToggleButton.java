package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* loaded from: classes.dex */
public class AppCompatToggleButton extends ToggleButton {

    /* renamed from: b */
    public final C0241m f835b;

    public AppCompatToggleButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyleToggle);
    }

    public AppCompatToggleButton(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        C0240l0.m934a(this, getContext());
        C0241m c0241m = new C0241m(this);
        this.f835b = c0241m;
        c0241m.m954m(attributeSet, i9);
    }
}
