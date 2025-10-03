package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.base.C3455R;
import com.google.android.gms.common.util.DeviceProperties;
import p224w.C6494a;

/* loaded from: classes2.dex */
public final class zay extends Button {
    public zay(Context context) {
        this(context, null);
    }

    public final void zaa(Resources resources, int i9, int i10) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i11 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i11);
        setMinWidth(i11);
        int i12 = C3455R.drawable.common_google_signin_btn_icon_dark;
        int i13 = C3455R.drawable.common_google_signin_btn_icon_light;
        int iZaa = zaa(i10, i12, i13, i13);
        int i14 = C3455R.drawable.common_google_signin_btn_text_dark;
        int i15 = C3455R.drawable.common_google_signin_btn_text_light;
        int iZaa2 = zaa(i10, i14, i15, i15);
        if (i9 == 0 || i9 == 1) {
            iZaa = iZaa2;
        } else if (i9 != 2) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Unknown button size: ");
            sb.append(i9);
            throw new IllegalStateException(sb.toString());
        }
        Drawable drawableM24849l = C6494a.m24849l(resources.getDrawable(iZaa));
        C6494a.m24846i(drawableM24849l, resources.getColorStateList(C3455R.color.common_google_signin_btn_tint));
        C6494a.m24847j(drawableM24849l, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(drawableM24849l);
        int i16 = C3455R.color.common_google_signin_btn_text_dark;
        int i17 = C3455R.color.common_google_signin_btn_text_light;
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zaa(i10, i16, i17, i17))));
        if (i9 == 0) {
            setText(resources.getString(C3455R.string.common_signin_button_text));
        } else if (i9 == 1) {
            setText(resources.getString(C3455R.string.common_signin_button_text_long));
        } else {
            if (i9 != 2) {
                StringBuilder sb2 = new StringBuilder(32);
                sb2.append("Unknown button size: ");
                sb2.append(i9);
                throw new IllegalStateException(sb2.toString());
            }
            setText((CharSequence) null);
        }
        setTransformationMethod(null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }

    private zay(Context context, AttributeSet attributeSet) {
        super(context, null, R.attr.buttonStyle);
    }

    private static int zaa(int i9, int i10, int i11, int i12) {
        if (i9 == 0) {
            return i10;
        }
        if (i9 == 1) {
            return i11;
        }
        if (i9 == 2) {
            return i12;
        }
        StringBuilder sb = new StringBuilder(33);
        sb.append("Unknown color scheme: ");
        sb.append(i9);
        throw new IllegalStateException(sb.toString());
    }
}
