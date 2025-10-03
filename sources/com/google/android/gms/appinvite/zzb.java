package com.google.android.gms.appinvite;

import android.view.View;

/* loaded from: classes2.dex */
final class zzb implements View.OnClickListener {
    private final /* synthetic */ PreviewActivity zzi;

    public zzb(PreviewActivity previewActivity) {
        this.zzi = previewActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzi.finish();
    }
}
