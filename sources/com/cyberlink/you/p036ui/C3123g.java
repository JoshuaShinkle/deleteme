package com.cyberlink.you.p036ui;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

/* renamed from: com.cyberlink.you.ui.g */
/* loaded from: classes.dex */
public class C3123g {
    /* renamed from: a */
    public static AlertDialog.Builder m16382a(Context context) {
        return new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.Theme.Holo.Light.Dialog));
    }

    /* renamed from: b */
    public static Dialog m16383b(Context context, String str, String str2) {
        Dialog dialog = new Dialog(context, com.cyberlink.p030U.R.style.DialogBaseTheme);
        dialog.setContentView(com.cyberlink.p030U.R.layout.dialog_breakout_room_broadcast);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        ((TextView) dialog.findViewById(com.cyberlink.p030U.R.id.dialogTitle)).setText(str);
        ((TextView) dialog.findViewById(com.cyberlink.p030U.R.id.dialogContent)).setText(str2);
        return dialog;
    }

    /* renamed from: c */
    public static Dialog m16384c(Context context) {
        return new Dialog(context, com.cyberlink.p030U.R.style.DialogBaseTheme);
    }

    /* renamed from: d */
    public static Dialog m16385d(Context context, String str, String str2) {
        Dialog dialogM16387f = m16387f(context, str, str2);
        dialogM16387f.findViewById(com.cyberlink.p030U.R.id.verticalLayout).setVisibility(8);
        dialogM16387f.findViewById(com.cyberlink.p030U.R.id.horizontalLayout).setVisibility(8);
        dialogM16387f.findViewById(com.cyberlink.p030U.R.id.v_btn).setVisibility(0);
        return dialogM16387f;
    }

    /* renamed from: e */
    public static Dialog m16386e(Context context, String str, String str2, boolean z8) {
        Dialog dialogM16387f = m16387f(context, str, str2);
        if (z8) {
            dialogM16387f.findViewById(com.cyberlink.p030U.R.id.verticalLayout).setVisibility(0);
            dialogM16387f.findViewById(com.cyberlink.p030U.R.id.horizontalLayout).setVisibility(8);
        } else {
            dialogM16387f.findViewById(com.cyberlink.p030U.R.id.verticalLayout).setVisibility(8);
            dialogM16387f.findViewById(com.cyberlink.p030U.R.id.horizontalLayout).setVisibility(0);
        }
        return dialogM16387f;
    }

    /* renamed from: f */
    public static Dialog m16387f(Context context, String str, String str2) {
        Dialog dialog = new Dialog(context, com.cyberlink.p030U.R.style.DialogBaseTheme);
        dialog.setContentView(com.cyberlink.p030U.R.layout.custom_show_dialog);
        if (TextUtils.isEmpty(str)) {
            dialog.findViewById(com.cyberlink.p030U.R.id.titlePanel).setVisibility(8);
        } else {
            ((TextView) dialog.findViewById(com.cyberlink.p030U.R.id.dialogTitle)).setText(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            ((TextView) dialog.findViewById(com.cyberlink.p030U.R.id.dialogContent)).setText(str2);
        }
        return dialog;
    }
}
