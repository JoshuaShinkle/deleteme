package androidx.browser.browseractions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import p101j.C5065a;

/* loaded from: classes.dex */
public class BrowserActionsFallbackMenuView extends LinearLayout {

    /* renamed from: b */
    public final int f1240b;

    /* renamed from: c */
    public final int f1241c;

    public BrowserActionsFallbackMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1240b = getResources().getDimensionPixelOffset(C5065a.browser_actions_context_menu_min_padding);
        this.f1241c = getResources().getDimensionPixelOffset(C5065a.browser_actions_context_menu_max_width);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(getResources().getDisplayMetrics().widthPixels - (this.f1240b * 2), this.f1241c), 1073741824), i10);
    }
}
