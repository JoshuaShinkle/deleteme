package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.widget.C0230g0;
import p010b.C0560a;
import p010b.C0562c;
import p010b.C0564e;
import p020c.C0694a;
import p215v.C6427a;

/* renamed from: androidx.appcompat.widget.f */
/* loaded from: classes.dex */
public final class C0227f {

    /* renamed from: b */
    public static final PorterDuff.Mode f1054b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c */
    public static C0227f f1055c;

    /* renamed from: a */
    public C0230g0 f1056a;

    /* renamed from: androidx.appcompat.widget.f$a */
    public class a implements C0230g0.c {

        /* renamed from: a */
        public final int[] f1057a = {C0564e.abc_textfield_search_default_mtrl_alpha, C0564e.abc_textfield_default_mtrl_alpha, C0564e.abc_ab_share_pack_mtrl_alpha};

        /* renamed from: b */
        public final int[] f1058b = {C0564e.abc_ic_commit_search_api_mtrl_alpha, C0564e.abc_seekbar_tick_mark_material, C0564e.abc_ic_menu_share_mtrl_alpha, C0564e.abc_ic_menu_copy_mtrl_am_alpha, C0564e.abc_ic_menu_cut_mtrl_alpha, C0564e.abc_ic_menu_selectall_mtrl_alpha, C0564e.abc_ic_menu_paste_mtrl_am_alpha};

        /* renamed from: c */
        public final int[] f1059c = {C0564e.abc_textfield_activated_mtrl_alpha, C0564e.abc_textfield_search_activated_mtrl_alpha, C0564e.abc_cab_background_top_mtrl_alpha, C0564e.abc_text_cursor_material, C0564e.abc_text_select_handle_left_mtrl_dark, C0564e.abc_text_select_handle_middle_mtrl_dark, C0564e.abc_text_select_handle_right_mtrl_dark, C0564e.abc_text_select_handle_left_mtrl_light, C0564e.abc_text_select_handle_middle_mtrl_light, C0564e.abc_text_select_handle_right_mtrl_light};

        /* renamed from: d */
        public final int[] f1060d = {C0564e.abc_popup_background_mtrl_mult, C0564e.abc_cab_background_internal_bg, C0564e.abc_menu_hardkey_panel_mtrl_mult};

        /* renamed from: e */
        public final int[] f1061e = {C0564e.abc_tab_indicator_material, C0564e.abc_textfield_search_material};

        /* renamed from: f */
        public final int[] f1062f = {C0564e.abc_btn_check_material, C0564e.abc_btn_radio_material, C0564e.abc_btn_check_material_anim, C0564e.abc_btn_radio_material_anim};

        /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x006c A[RETURN] */
        @Override // androidx.appcompat.widget.C0230g0.c
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean mo827a(Context context, int i9, Drawable drawable) {
            PorterDuff.Mode mode;
            int i10;
            boolean z8;
            int iRound;
            PorterDuff.Mode mode2 = C0227f.f1054b;
            if (m832f(this.f1057a, i9)) {
                i10 = C0560a.colorControlNormal;
            } else if (m832f(this.f1059c, i9)) {
                i10 = C0560a.colorControlActivated;
            } else {
                if (m832f(this.f1060d, i9)) {
                    mode2 = PorterDuff.Mode.MULTIPLY;
                } else {
                    if (i9 == C0564e.abc_list_divider_mtrl_alpha) {
                        z8 = true;
                        iRound = Math.round(40.8f);
                        i10 = 16842800;
                        mode = mode2;
                        if (z8) {
                            return false;
                        }
                        if (C0262x.m1073a(drawable)) {
                            drawable = drawable.mutate();
                        }
                        drawable.setColorFilter(C0227f.m820e(C0240l0.m936c(context, i10), mode));
                        if (iRound != -1) {
                            drawable.setAlpha(iRound);
                        }
                        return true;
                    }
                    if (i9 != C0564e.abc_dialog_material_background) {
                        mode = mode2;
                        i10 = 0;
                        z8 = false;
                        iRound = -1;
                        if (z8) {
                        }
                    }
                }
                mode = mode2;
                iRound = -1;
                i10 = 16842801;
                z8 = true;
                if (z8) {
                }
            }
            mode = mode2;
            z8 = true;
            iRound = -1;
            if (z8) {
            }
        }

        @Override // androidx.appcompat.widget.C0230g0.c
        /* renamed from: b */
        public PorterDuff.Mode mo828b(int i9) {
            if (i9 == C0564e.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        @Override // androidx.appcompat.widget.C0230g0.c
        /* renamed from: c */
        public Drawable mo829c(C0230g0 c0230g0, Context context, int i9) {
            if (i9 == C0564e.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{c0230g0.m858i(context, C0564e.abc_cab_background_internal_bg), c0230g0.m858i(context, C0564e.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        @Override // androidx.appcompat.widget.C0230g0.c
        /* renamed from: d */
        public ColorStateList mo830d(Context context, int i9) {
            if (i9 == C0564e.abc_edit_text_material) {
                return C0694a.m3457a(context, C0562c.abc_tint_edittext);
            }
            if (i9 == C0564e.abc_switch_track_mtrl_alpha) {
                return C0694a.m3457a(context, C0562c.abc_tint_switch_track);
            }
            if (i9 == C0564e.abc_switch_thumb_material) {
                return m837k(context);
            }
            if (i9 == C0564e.abc_btn_default_mtrl_shape) {
                return m836j(context);
            }
            if (i9 == C0564e.abc_btn_borderless_material) {
                return m833g(context);
            }
            if (i9 == C0564e.abc_btn_colored_material) {
                return m835i(context);
            }
            if (i9 == C0564e.abc_spinner_mtrl_am_alpha || i9 == C0564e.abc_spinner_textfield_background_material) {
                return C0694a.m3457a(context, C0562c.abc_tint_spinner);
            }
            if (m832f(this.f1058b, i9)) {
                return C0240l0.m938e(context, C0560a.colorControlNormal);
            }
            if (m832f(this.f1061e, i9)) {
                return C0694a.m3457a(context, C0562c.abc_tint_default);
            }
            if (m832f(this.f1062f, i9)) {
                return C0694a.m3457a(context, C0562c.abc_tint_btn_checkable);
            }
            if (i9 == C0564e.abc_seekbar_thumb_material) {
                return C0694a.m3457a(context, C0562c.abc_tint_seek_thumb);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.C0230g0.c
        /* renamed from: e */
        public boolean mo831e(Context context, int i9, Drawable drawable) {
            if (i9 == C0564e.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i10 = C0560a.colorControlNormal;
                m838l(drawableFindDrawableByLayerId, C0240l0.m936c(context, i10), C0227f.f1054b);
                m838l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), C0240l0.m936c(context, i10), C0227f.f1054b);
                m838l(layerDrawable.findDrawableByLayerId(R.id.progress), C0240l0.m936c(context, C0560a.colorControlActivated), C0227f.f1054b);
                return true;
            }
            if (i9 != C0564e.abc_ratingbar_material && i9 != C0564e.abc_ratingbar_indicator_material && i9 != C0564e.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            m838l(layerDrawable2.findDrawableByLayerId(R.id.background), C0240l0.m935b(context, C0560a.colorControlNormal), C0227f.f1054b);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i11 = C0560a.colorControlActivated;
            m838l(drawableFindDrawableByLayerId2, C0240l0.m936c(context, i11), C0227f.f1054b);
            m838l(layerDrawable2.findDrawableByLayerId(R.id.progress), C0240l0.m936c(context, i11), C0227f.f1054b);
            return true;
        }

        /* renamed from: f */
        public final boolean m832f(int[] iArr, int i9) {
            for (int i10 : iArr) {
                if (i10 == i9) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: g */
        public final ColorStateList m833g(Context context) {
            return m834h(context, 0);
        }

        /* renamed from: h */
        public final ColorStateList m834h(Context context, int i9) {
            int iM936c = C0240l0.m936c(context, C0560a.colorControlHighlight);
            return new ColorStateList(new int[][]{C0240l0.f1143b, C0240l0.f1146e, C0240l0.f1144c, C0240l0.f1150i}, new int[]{C0240l0.m935b(context, C0560a.colorButtonNormal), C6427a.m24588b(iM936c, i9), C6427a.m24588b(iM936c, i9), i9});
        }

        /* renamed from: i */
        public final ColorStateList m835i(Context context) {
            return m834h(context, C0240l0.m936c(context, C0560a.colorAccent));
        }

        /* renamed from: j */
        public final ColorStateList m836j(Context context) {
            return m834h(context, C0240l0.m936c(context, C0560a.colorButtonNormal));
        }

        /* renamed from: k */
        public final ColorStateList m837k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i9 = C0560a.colorSwitchThumbNormal;
            ColorStateList colorStateListM938e = C0240l0.m938e(context, i9);
            if (colorStateListM938e == null || !colorStateListM938e.isStateful()) {
                iArr[0] = C0240l0.f1143b;
                iArr2[0] = C0240l0.m935b(context, i9);
                iArr[1] = C0240l0.f1147f;
                iArr2[1] = C0240l0.m936c(context, C0560a.colorControlActivated);
                iArr[2] = C0240l0.f1150i;
                iArr2[2] = C0240l0.m936c(context, i9);
            } else {
                int[] iArr3 = C0240l0.f1143b;
                iArr[0] = iArr3;
                iArr2[0] = colorStateListM938e.getColorForState(iArr3, 0);
                iArr[1] = C0240l0.f1147f;
                iArr2[1] = C0240l0.m936c(context, C0560a.colorControlActivated);
                iArr[2] = C0240l0.f1150i;
                iArr2[2] = colorStateListM938e.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        /* renamed from: l */
        public final void m838l(Drawable drawable, int i9, PorterDuff.Mode mode) {
            if (C0262x.m1073a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = C0227f.f1054b;
            }
            drawable.setColorFilter(C0227f.m820e(i9, mode));
        }
    }

    /* renamed from: b */
    public static synchronized C0227f m819b() {
        if (f1055c == null) {
            m821h();
        }
        return f1055c;
    }

    /* renamed from: e */
    public static synchronized PorterDuffColorFilter m820e(int i9, PorterDuff.Mode mode) {
        return C0230g0.m849k(i9, mode);
    }

    /* renamed from: h */
    public static synchronized void m821h() {
        if (f1055c == null) {
            C0227f c0227f = new C0227f();
            f1055c = c0227f;
            c0227f.f1056a = C0230g0.m848g();
            f1055c.f1056a.m866t(new a());
        }
    }

    /* renamed from: i */
    public static void m822i(Drawable drawable, C0246o0 c0246o0, int[] iArr) {
        C0230g0.m852v(drawable, c0246o0, iArr);
    }

    /* renamed from: c */
    public synchronized Drawable m823c(Context context, int i9) {
        return this.f1056a.m858i(context, i9);
    }

    /* renamed from: d */
    public synchronized Drawable m824d(Context context, int i9, boolean z8) {
        return this.f1056a.m859j(context, i9, z8);
    }

    /* renamed from: f */
    public synchronized ColorStateList m825f(Context context, int i9) {
        return this.f1056a.m860l(context, i9);
    }

    /* renamed from: g */
    public synchronized void m826g(Context context) {
        this.f1056a.m864r(context);
    }
}
