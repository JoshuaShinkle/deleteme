package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* renamed from: androidx.fragment.app.c */
/* loaded from: classes.dex */
public abstract class AbstractC0364c {
    @Deprecated
    /* renamed from: a */
    public Fragment m1811a(Context context, String str, Bundle bundle) {
        return Fragment.instantiate(context, str, bundle);
    }

    /* renamed from: b */
    public abstract View mo1761b(int i9);

    /* renamed from: c */
    public abstract boolean mo1762c();
}
