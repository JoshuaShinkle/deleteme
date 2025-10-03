package p071g;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import p010b.C0560a;
import p010b.C0561b;
import p010b.C0563d;
import p010b.C0569j;

/* renamed from: g.a */
/* loaded from: classes.dex */
public class C4795a {

    /* renamed from: a */
    public Context f16651a;

    public C4795a(Context context) {
        this.f16651a = context;
    }

    /* renamed from: b */
    public static C4795a m19032b(Context context) {
        return new C4795a(context);
    }

    /* renamed from: a */
    public boolean m19033a() {
        return this.f16651a.getApplicationInfo().targetSdkVersion < 14;
    }

    /* renamed from: c */
    public int m19034c() {
        return this.f16651a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    /* renamed from: d */
    public int m19035d() {
        Configuration configuration = this.f16651a.getResources().getConfiguration();
        int i9 = configuration.screenWidthDp;
        int i10 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i9 > 600) {
            return 5;
        }
        if (i9 > 960 && i10 > 720) {
            return 5;
        }
        if (i9 > 720 && i10 > 960) {
            return 5;
        }
        if (i9 >= 500) {
            return 4;
        }
        if (i9 > 640 && i10 > 480) {
            return 4;
        }
        if (i9 <= 480 || i10 <= 640) {
            return i9 >= 360 ? 3 : 2;
        }
        return 4;
    }

    /* renamed from: e */
    public int m19036e() {
        return this.f16651a.getResources().getDimensionPixelSize(C0563d.abc_action_bar_stacked_tab_max_width);
    }

    /* renamed from: f */
    public int m19037f() {
        TypedArray typedArrayObtainStyledAttributes = this.f16651a.obtainStyledAttributes(null, C0569j.ActionBar, C0560a.actionBarStyle, 0);
        int layoutDimension = typedArrayObtainStyledAttributes.getLayoutDimension(C0569j.ActionBar_height, 0);
        Resources resources = this.f16651a.getResources();
        if (!m19038g()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(C0563d.abc_action_bar_stacked_max_height));
        }
        typedArrayObtainStyledAttributes.recycle();
        return layoutDimension;
    }

    /* renamed from: g */
    public boolean m19038g() {
        return this.f16651a.getResources().getBoolean(C0561b.abc_action_bar_embed_tabs);
    }

    /* renamed from: h */
    public boolean m19039h() {
        return true;
    }
}
