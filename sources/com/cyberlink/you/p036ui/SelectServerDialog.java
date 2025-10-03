package com.cyberlink.you.p036ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class SelectServerDialog {

    /* renamed from: a */
    public ArrayList<C3116a> f14381a = new ArrayList<C3116a>() { // from class: com.cyberlink.you.ui.SelectServerDialog.1
        {
            add(new C3116a("TW", "Region-G"));
            add(new C3116a("CHN", "Region-C"));
        }
    };

    /* renamed from: com.cyberlink.you.ui.SelectServerDialog$a */
    public static class C3116a {

        /* renamed from: a */
        public final String f14382a;

        /* renamed from: b */
        public final String f14383b;

        public C3116a(String str, String str2) {
            this.f14382a = str;
            this.f14383b = str2;
        }

        /* renamed from: c */
        public String m16359c() {
            return this.f14382a;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ void m16351e(DialogInterface dialogInterface, int i9) {
        Globals.m7388i0().m7466O3(this.f14381a.get(i9).m16359c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public /* synthetic */ void m16352f(Activity activity, DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        Globals.m7377a3("");
        Globals.m7369J2("");
        m16356i(activity);
    }

    /* renamed from: g */
    public static /* synthetic */ void m16353g(Activity activity, DialogInterface dialogInterface, int i9) {
        activity.finishAffinity();
        System.exit(0);
    }

    /* renamed from: d */
    public final int m16354d() {
        String strM7463O0 = Globals.m7388i0().m7463O0();
        for (int i9 = 0; i9 < this.f14381a.size(); i9++) {
            if (strM7463O0.equalsIgnoreCase(this.f14381a.get(i9).f14382a)) {
                return i9;
            }
        }
        return 0;
    }

    /* renamed from: h */
    public void m16355h(final Activity activity) {
        ArrayList arrayList = new ArrayList();
        Iterator<C3116a> it = this.f14381a.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().f14383b);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.clw_registration_info_region));
        builder.setSingleChoiceItems((CharSequence[]) arrayList.toArray(new String[0]), m16354d(), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f14400b.m16351e(dialogInterface, i9);
            }
        });
        builder.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.i
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f14401b.m16352f(activity, dialogInterface, i9);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /* renamed from: i */
    public final void m16356i(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("U will be quitted. Please relaunch to enable the setting.");
        builder.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.ui.j
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                SelectServerDialog.m16353g(activity, dialogInterface, i9);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
