package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

@KeepForSdk
@Deprecated
/* loaded from: classes2.dex */
public class WakeLockTracker {
    private static WakeLockTracker zza = new WakeLockTracker();

    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return zza;
    }

    @KeepForSdk
    public void registerAcquireEvent(Context context, Intent intent, String str, String str2, String str3, int i9, String str4) {
    }

    @KeepForSdk
    public void registerDeadlineEvent(Context context, String str, String str2, String str3, int i9, List<String> list, boolean z8, long j9) {
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i9, String str2, String str3, String str4, int i10, List<String> list) {
    }

    @KeepForSdk
    public void registerEvent(Context context, String str, int i9, String str2, String str3, String str4, int i10, List<String> list, long j9) {
    }

    @KeepForSdk
    public void registerReleaseEvent(Context context, Intent intent) {
    }
}
