package com.google.android.material.internal;

import android.graphics.PorterDuff;
import android.view.View;
import p042d0.C4647u;

/* loaded from: classes2.dex */
public class ViewUtils {
    public static boolean isLayoutRtl(View view) {
        return C4647u.m18567s(view) == 1;
    }

    public static PorterDuff.Mode parseTintMode(int i9, PorterDuff.Mode mode) {
        if (i9 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i9 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i9 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i9) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
