package com.google.android.gms.tagmanager;

import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes2.dex */
final class zzq<K, V> {

    @VisibleForTesting
    private final zzs<K, V> zzaeb = new zzr(this);

    public static zzp<K, V> zza(int i9, zzs<K, V> zzsVar) {
        return new zzdb(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzsVar);
    }
}
