package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b;

/* renamed from: androidx.appcompat.app.f */
/* loaded from: classes.dex */
public class C0124f extends DialogInterfaceOnCancelListenerC0363b {
    @Override // androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b
    public Dialog onCreateDialog(Bundle bundle) {
        return new DialogC0123e(getContext(), getTheme());
    }

    @Override // androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b
    public void setupDialog(Dialog dialog, int i9) {
        if (!(dialog instanceof DialogC0123e)) {
            super.setupDialog(dialog, i9);
            return;
        }
        DialogC0123e dialogC0123e = (DialogC0123e) dialog;
        if (i9 != 1 && i9 != 2) {
            if (i9 != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        dialogC0123e.supportRequestWindowFeature(1);
    }
}
