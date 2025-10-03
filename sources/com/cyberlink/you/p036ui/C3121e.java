package com.cyberlink.you.p036ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.utility.CLUtility;
import java.io.IOException;
import p116k4.C5175q;

/* renamed from: com.cyberlink.you.ui.e */
/* loaded from: classes.dex */
public class C3121e {

    /* renamed from: a */
    public CheckBox f14389a;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public /* synthetic */ void m16364h(ULauncherActivity uLauncherActivity, DialogInterface dialogInterface, int i9) {
        m16371l(this.f14389a.isChecked());
        m16370g(uLauncherActivity);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public /* synthetic */ void m16365i(DialogInterface dialogInterface, int i9) {
        m16371l(this.f14389a.isChecked());
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public /* synthetic */ void m16366j(DialogInterface dialogInterface, int i9) throws IOException {
        m16371l(this.f14389a.isChecked());
        C5175q.m20221c();
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public /* synthetic */ void m16367k(DialogInterface dialogInterface, int i9) {
        m16371l(this.f14389a.isChecked());
        dialogInterface.dismiss();
    }

    /* renamed from: e */
    public AlertDialog m16368e(final ULauncherActivity uLauncherActivity) {
        View viewInflate = LayoutInflater.from(uLauncherActivity).inflate(R.layout.dialog_faq_checkbox, (ViewGroup) null);
        this.f14389a = (CheckBox) viewInflate.findViewById(R.id.skip);
        return C3123g.m16382a(uLauncherActivity).setView(viewInflate).setTitle(Globals.m7375Z0(R.string.warnig_special_device_title)).setMessage(R.string.warnig_special_device).setNegativeButton(uLauncherActivity.getString(R.string.menu_details), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f14386b.m16364h(uLauncherActivity, dialogInterface, i9);
            }
        }).setPositiveButton(uLauncherActivity.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.d
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f14388b.m16365i(dialogInterface, i9);
            }
        }).create();
    }

    /* renamed from: f */
    public AlertDialog m16369f(ULauncherActivity uLauncherActivity) {
        View viewInflate = LayoutInflater.from(uLauncherActivity).inflate(R.layout.dialog_faq_checkbox, (ViewGroup) null);
        this.f14389a = (CheckBox) viewInflate.findViewById(R.id.skip);
        return C3123g.m16382a(uLauncherActivity).setView(viewInflate).setTitle(Globals.m7375Z0(R.string.warnig_special_device_title)).setMessage(R.string.warnig_special_device).setNegativeButton(uLauncherActivity.getString(R.string.setting), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) throws IOException {
                this.f14384b.m16366j(dialogInterface, i9);
            }
        }).setPositiveButton(uLauncherActivity.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f14385b.m16367k(dialogInterface, i9);
            }
        }).create();
    }

    /* renamed from: g */
    public final void m16370g(Activity activity) {
        String strM16544i0 = CLUtility.m16544i0();
        if (strM16544i0.isEmpty()) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(strM16544i0));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: l */
    public final void m16371l(boolean z8) {
        Globals.m7388i0().m7551g3(z8);
    }
}
