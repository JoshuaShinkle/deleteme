package androidx.core.widget;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: androidx.core.widget.h */
/* loaded from: classes.dex */
public final class C0333h {
    /* renamed from: a */
    public static void m1600a(PopupWindow popupWindow, boolean z8) {
        popupWindow.setOverlapAnchor(z8);
    }

    /* renamed from: b */
    public static void m1601b(PopupWindow popupWindow, int i9) {
        popupWindow.setWindowLayoutType(i9);
    }

    /* renamed from: c */
    public static void m1602c(PopupWindow popupWindow, View view, int i9, int i10, int i11) {
        popupWindow.showAsDropDown(view, i9, i10, i11);
    }
}
