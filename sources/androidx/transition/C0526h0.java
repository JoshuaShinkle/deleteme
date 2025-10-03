package androidx.transition;

import android.view.View;
import android.view.WindowId;

/* renamed from: androidx.transition.h0 */
/* loaded from: classes.dex */
public class C0526h0 implements InterfaceC0528i0 {

    /* renamed from: a */
    public final WindowId f2924a;

    public C0526h0(View view) {
        this.f2924a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof C0526h0) && ((C0526h0) obj).f2924a.equals(this.f2924a);
    }

    public int hashCode() {
        return this.f2924a.hashCode();
    }
}
