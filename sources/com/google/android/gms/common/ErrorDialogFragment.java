package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes2.dex */
public class ErrorDialogFragment extends DialogFragment {
    private Dialog zaa;
    private DialogInterface.OnCancelListener zab;

    public static ErrorDialogFragment newInstance(@RecentlyNonNull Dialog dialog) {
        return newInstance(dialog, null);
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(@RecentlyNonNull DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.zab;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.zaa == null) {
            setShowsDialog(false);
        }
        return this.zaa;
    }

    @Override // android.app.DialogFragment
    public void show(@RecentlyNonNull FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }

    public static ErrorDialogFragment newInstance(@RecentlyNonNull Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        Dialog dialog2 = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        dialog2.setOnCancelListener(null);
        dialog2.setOnDismissListener(null);
        errorDialogFragment.zaa = dialog2;
        if (onCancelListener != null) {
            errorDialogFragment.zab = onCancelListener;
        }
        return errorDialogFragment;
    }
}
