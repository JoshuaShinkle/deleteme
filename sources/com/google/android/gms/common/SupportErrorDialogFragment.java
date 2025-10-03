package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import androidx.fragment.app.AbstractC0368g;
import androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public class SupportErrorDialogFragment extends DialogInterfaceOnCancelListenerC0363b {
    private Dialog zaa;
    private DialogInterface.OnCancelListener zab;

    public static SupportErrorDialogFragment newInstance(@RecentlyNonNull Dialog dialog) {
        return newInstance(dialog, null);
    }

    @Override // androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b, android.content.DialogInterface.OnCancelListener
    public void onCancel(@RecentlyNonNull DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.zab;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.zaa == null) {
            setShowsDialog(false);
        }
        return this.zaa;
    }

    @Override // androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b
    public void show(@RecentlyNonNull AbstractC0368g abstractC0368g, String str) {
        super.show(abstractC0368g, str);
    }

    public static SupportErrorDialogFragment newInstance(@RecentlyNonNull Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
        Dialog dialog2 = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        supportErrorDialogFragment.zaa = dialog2;
        if (onCancelListener != null) {
            supportErrorDialogFragment.zab = onCancelListener;
        }
        return supportErrorDialogFragment;
    }
}
