package p116k4;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import p209u2.C6385v;

/* renamed from: k4.i0 */
/* loaded from: classes.dex */
public class C5152i0 {
    /* renamed from: b */
    public static void m20065b(final Dialog dialog) {
        C6385v.m24527e(new Runnable() { // from class: k4.h0
            @Override // java.lang.Runnable
            public final void run() {
                C5152i0.m20066c(dialog);
            }
        });
    }

    /* renamed from: c */
    public static /* synthetic */ void m20066c(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        Context baseContext = dialog.getContext() instanceof ContextWrapper ? ((ContextWrapper) dialog.getContext()).getBaseContext() : null;
        if (!(baseContext instanceof Activity)) {
            dialog.dismiss();
            return;
        }
        Activity activity = (Activity) baseContext;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        dialog.dismiss();
    }
}
