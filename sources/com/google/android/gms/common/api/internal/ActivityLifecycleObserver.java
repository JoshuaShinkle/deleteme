package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes2.dex */
public abstract class ActivityLifecycleObserver {
    @RecentlyNonNull
    @KeepForSdk
    /* renamed from: of */
    public static final ActivityLifecycleObserver m17507of(@RecentlyNonNull Activity activity) {
        return new zaa(activity);
    }

    @RecentlyNonNull
    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(@RecentlyNonNull Runnable runnable);
}
