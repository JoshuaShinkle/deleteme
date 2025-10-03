package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import p042d0.C4613a;
import p052e0.C4704m;

/* renamed from: androidx.recyclerview.widget.m */
/* loaded from: classes.dex */
public class C0484m extends C4613a {

    /* renamed from: a */
    public final RecyclerView f2720a;

    /* renamed from: b */
    public final C4613a f2721b = new a(this);

    /* renamed from: androidx.recyclerview.widget.m$a */
    public static class a extends C4613a {

        /* renamed from: a */
        public final C0484m f2722a;

        public a(C0484m c0484m) {
            this.f2722a = c0484m;
        }

        @Override // p042d0.C4613a
        public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
            super.onInitializeAccessibilityNodeInfo(view, c4704m);
            if (this.f2722a.m2885b() || this.f2722a.f2720a.getLayoutManager() == null) {
                return;
            }
            this.f2722a.f2720a.getLayoutManager().m2455N0(view, c4704m);
        }

        @Override // p042d0.C4613a
        public boolean performAccessibilityAction(View view, int i9, Bundle bundle) {
            if (super.performAccessibilityAction(view, i9, bundle)) {
                return true;
            }
            if (this.f2722a.m2885b() || this.f2722a.f2720a.getLayoutManager() == null) {
                return false;
            }
            return this.f2722a.f2720a.getLayoutManager().m2490h1(view, i9, bundle);
        }
    }

    public C0484m(RecyclerView recyclerView) {
        this.f2720a = recyclerView;
    }

    /* renamed from: a */
    public C4613a m2884a() {
        return this.f2721b;
    }

    /* renamed from: b */
    public boolean m2885b() {
        return this.f2720a.hasPendingAdapterUpdates();
    }

    @Override // p042d0.C4613a
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if (!(view instanceof RecyclerView) || m2885b()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().mo2268J0(accessibilityEvent);
        }
    }

    @Override // p042d0.C4613a
    public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
        super.onInitializeAccessibilityNodeInfo(view, c4704m);
        c4704m.m18781U(RecyclerView.class.getName());
        if (m2885b() || this.f2720a.getLayoutManager() == null) {
            return;
        }
        this.f2720a.getLayoutManager().m2454M0(c4704m);
    }

    @Override // p042d0.C4613a
    public boolean performAccessibilityAction(View view, int i9, Bundle bundle) {
        if (super.performAccessibilityAction(view, i9, bundle)) {
            return true;
        }
        if (m2885b() || this.f2720a.getLayoutManager() == null) {
            return false;
        }
        return this.f2720a.getLayoutManager().m2486f1(i9, bundle);
    }
}
