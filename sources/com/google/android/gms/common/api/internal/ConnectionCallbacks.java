package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
/* loaded from: classes2.dex */
public interface ConnectionCallbacks {
    @ShowFirstParty
    @KeepForSdk
    void onConnected(Bundle bundle);

    @ShowFirstParty
    @KeepForSdk
    void onConnectionSuspended(@RecentlyNonNull int i9);
}
