package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

/* loaded from: classes2.dex */
final class zzk extends zzam {
    private final /* synthetic */ GoogleMap.OnMapLoadedCallback zzs;

    public zzk(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.zzs = onMapLoadedCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void onMapLoaded() {
        this.zzs.onMapLoaded();
    }
}
