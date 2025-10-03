package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.core.widget.C0333h;
import p010b.C0569j;

/* loaded from: classes.dex */
class AppCompatPopupWindow extends PopupWindow {

    /* renamed from: b */
    public static final boolean f795b = false;

    /* renamed from: a */
    public boolean f796a;

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        m662a(context, attributeSet, i9, 0);
    }

    /* renamed from: a */
    public final void m662a(Context context, AttributeSet attributeSet, int i9, int i10) {
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, C0569j.PopupWindow, i9, i10);
        int i11 = C0569j.PopupWindow_overlapAnchor;
        if (c0250q0M1004v.m1023s(i11)) {
            m663b(c0250q0M1004v.m1005a(i11, false));
        }
        setBackgroundDrawable(c0250q0M1004v.m1011g(C0569j.PopupWindow_android_popupBackground));
        c0250q0M1004v.m1024w();
    }

    /* renamed from: b */
    public final void m663b(boolean z8) {
        if (f795b) {
            this.f796a = z8;
        } else {
            C0333h.m1600a(this, z8);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i9, int i10) {
        if (f795b && this.f796a) {
            i10 -= view.getHeight();
        }
        super.showAsDropDown(view, i9, i10);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i9, int i10, int i11, int i12) {
        if (f795b && this.f796a) {
            i10 -= view.getHeight();
        }
        super.update(view, i9, i10, i11, i12);
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i9, int i10) {
        super(context, attributeSet, i9, i10);
        m662a(context, attributeSet, i9, i10);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i9, int i10, int i11) {
        if (f795b && this.f796a) {
            i10 -= view.getHeight();
        }
        super.showAsDropDown(view, i9, i10, i11);
    }
}
