package com.google.android.material.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.C0124f;

/* loaded from: classes2.dex */
public class BottomSheetDialogFragment extends C0124f {
    @Override // androidx.appcompat.app.C0124f, androidx.fragment.app.DialogInterfaceOnCancelListenerC0363b
    public Dialog onCreateDialog(Bundle bundle) {
        return new BottomSheetDialog(getContext(), getTheme());
    }
}
