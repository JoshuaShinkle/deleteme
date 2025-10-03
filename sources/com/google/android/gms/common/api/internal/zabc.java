package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* loaded from: classes2.dex */
final class zabc implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zaa;

    public zabc(GoogleApiManager googleApiManager) {
        this.zaa = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z8) {
        this.zaa.zaq.sendMessage(this.zaa.zaq.obtainMessage(1, Boolean.valueOf(z8)));
    }
}
