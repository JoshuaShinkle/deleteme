package com.google.android.gms.common.api.internal;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes2.dex */
public interface RemoteCall<T, U> {
    @KeepForSdk
    void accept(@RecentlyNonNull T t8, @RecentlyNonNull U u8);
}
