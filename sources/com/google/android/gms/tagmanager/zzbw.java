package com.google.android.gms.tagmanager;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzbw {
    private final long zzabb;
    private final long zzagy;
    private final long zzagz;
    private String zzaha;

    public zzbw(long j9, long j10, long j11) {
        this.zzagy = j9;
        this.zzabb = j10;
        this.zzagz = j11;
    }

    public final void zzbc(String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return;
        }
        this.zzaha = str;
    }

    public final long zzih() {
        return this.zzagy;
    }

    public final long zzii() {
        return this.zzagz;
    }

    public final String zzij() {
        return this.zzaha;
    }
}
