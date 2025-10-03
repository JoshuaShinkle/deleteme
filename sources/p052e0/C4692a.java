package p052e0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* renamed from: e0.a */
/* loaded from: classes.dex */
public final class C4692a extends ClickableSpan {

    /* renamed from: b */
    public final int f16404b;

    /* renamed from: c */
    public final C4704m f16405c;

    /* renamed from: d */
    public final int f16406d;

    public C4692a(int i9, C4704m c4704m, int i10) {
        this.f16404b = i9;
        this.f16405c = c4704m;
        this.f16406d = i10;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f16404b);
        this.f16405c.m18771K(this.f16406d, bundle);
    }
}
