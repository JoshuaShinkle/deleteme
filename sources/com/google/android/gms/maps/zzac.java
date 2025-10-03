package com.google.android.gms.maps;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

/* loaded from: classes2.dex */
final class zzac extends zzaq {
    private final /* synthetic */ OnMapReadyCallback zzbc;

    public zzac(MapView.zza zzaVar, OnMapReadyCallback onMapReadyCallback) {
        this.zzbc = onMapReadyCallback;
    }

    @Override // com.google.android.gms.maps.internal.zzap
    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) {
        this.zzbc.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
