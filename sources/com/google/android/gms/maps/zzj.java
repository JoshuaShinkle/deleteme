package com.google.android.gms.maps;

import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzba;

/* loaded from: classes2.dex */
final class zzj extends zzba {
    private final /* synthetic */ GoogleMap.OnMyLocationClickListener zzr;

    public zzj(GoogleMap googleMap, GoogleMap.OnMyLocationClickListener onMyLocationClickListener) {
        this.zzr = onMyLocationClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzaz
    public final void onMyLocationClick(Location location) {
        this.zzr.onMyLocationClick(location);
    }
}
