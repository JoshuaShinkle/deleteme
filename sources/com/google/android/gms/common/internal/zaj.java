package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* loaded from: classes2.dex */
public final class zaj {
    private final SparseIntArray zaa;
    private GoogleApiAvailabilityLight zab;

    public zaj() {
        this(GoogleApiAvailability.getInstance());
    }

    public final int zaa(Context context, Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        int iIsGooglePlayServicesAvailable = 0;
        if (!client.requiresGooglePlayServices()) {
            return 0;
        }
        int minApkVersion = client.getMinApkVersion();
        int i9 = this.zaa.get(minApkVersion, -1);
        if (i9 != -1) {
            return i9;
        }
        int i10 = 0;
        while (true) {
            if (i10 >= this.zaa.size()) {
                iIsGooglePlayServicesAvailable = i9;
                break;
            }
            int iKeyAt = this.zaa.keyAt(i10);
            if (iKeyAt > minApkVersion && this.zaa.get(iKeyAt) == 0) {
                break;
            }
            i10++;
        }
        if (iIsGooglePlayServicesAvailable == -1) {
            iIsGooglePlayServicesAvailable = this.zab.isGooglePlayServicesAvailable(context, minApkVersion);
        }
        this.zaa.put(minApkVersion, iIsGooglePlayServicesAvailable);
        return iIsGooglePlayServicesAvailable;
    }

    public zaj(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zaa = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.zab = googleApiAvailabilityLight;
    }

    public final void zaa() {
        this.zaa.clear();
    }
}
