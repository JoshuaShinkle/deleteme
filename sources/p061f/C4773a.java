package p061f;

import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;

/* renamed from: f.a */
/* loaded from: classes.dex */
public class C4773a implements TransformationMethod {

    /* renamed from: b */
    public Locale f16603b;

    public C4773a(Context context) {
        this.f16603b = context.getResources().getConfiguration().locale;
    }

    @Override // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        if (charSequence != null) {
            return charSequence.toString().toUpperCase(this.f16603b);
        }
        return null;
    }

    @Override // android.text.method.TransformationMethod
    public void onFocusChanged(View view, CharSequence charSequence, boolean z8, int i9, Rect rect) {
    }
}
