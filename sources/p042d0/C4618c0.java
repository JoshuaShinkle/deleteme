package p042d0;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: d0.c0 */
/* loaded from: classes.dex */
public final class C4618c0 {
    /* renamed from: a */
    public static boolean m18398a(ViewParent viewParent, View view, float f9, float f10, boolean z8) {
        try {
            return viewParent.onNestedFling(view, f9, f10, z8);
        } catch (AbstractMethodError e9) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e9);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m18399b(ViewParent viewParent, View view, float f9, float f10) {
        try {
            return viewParent.onNestedPreFling(view, f9, f10);
        } catch (AbstractMethodError e9) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e9);
            return false;
        }
    }

    /* renamed from: c */
    public static void m18400c(ViewParent viewParent, View view, int i9, int i10, int[] iArr, int i11) {
        if (viewParent instanceof InterfaceC4639n) {
            ((InterfaceC4639n) viewParent).onNestedPreScroll(view, i9, i10, iArr, i11);
            return;
        }
        if (i11 == 0) {
            try {
                viewParent.onNestedPreScroll(view, i9, i10, iArr);
            } catch (AbstractMethodError e9) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e9);
            }
        }
    }

    /* renamed from: d */
    public static void m18401d(ViewParent viewParent, View view, int i9, int i10, int i11, int i12, int i13, int[] iArr) {
        if (viewParent instanceof InterfaceC4641o) {
            ((InterfaceC4641o) viewParent).mo595j(view, i9, i10, i11, i12, i13, iArr);
            return;
        }
        iArr[0] = iArr[0] + i11;
        iArr[1] = iArr[1] + i12;
        if (viewParent instanceof InterfaceC4639n) {
            ((InterfaceC4639n) viewParent).onNestedScroll(view, i9, i10, i11, i12, i13);
            return;
        }
        if (i13 == 0) {
            try {
                viewParent.onNestedScroll(view, i9, i10, i11, i12);
            } catch (AbstractMethodError e9) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e9);
            }
        }
    }

    /* renamed from: e */
    public static void m18402e(ViewParent viewParent, View view, View view2, int i9, int i10) {
        if (viewParent instanceof InterfaceC4639n) {
            ((InterfaceC4639n) viewParent).onNestedScrollAccepted(view, view2, i9, i10);
            return;
        }
        if (i10 == 0) {
            try {
                viewParent.onNestedScrollAccepted(view, view2, i9);
            } catch (AbstractMethodError e9) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e9);
            }
        }
    }

    /* renamed from: f */
    public static boolean m18403f(ViewParent viewParent, View view, View view2, int i9, int i10) {
        if (viewParent instanceof InterfaceC4639n) {
            return ((InterfaceC4639n) viewParent).onStartNestedScroll(view, view2, i9, i10);
        }
        if (i10 != 0) {
            return false;
        }
        try {
            return viewParent.onStartNestedScroll(view, view2, i9);
        } catch (AbstractMethodError e9) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e9);
            return false;
        }
    }

    /* renamed from: g */
    public static void m18404g(ViewParent viewParent, View view, int i9) {
        if (viewParent instanceof InterfaceC4639n) {
            ((InterfaceC4639n) viewParent).onStopNestedScroll(view, i9);
            return;
        }
        if (i9 == 0) {
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError e9) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e9);
            }
        }
    }

    @Deprecated
    /* renamed from: h */
    public static boolean m18405h(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }
}
