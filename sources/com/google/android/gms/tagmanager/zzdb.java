package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import com.google.android.exoplayer2.source.ExtractorMediaSource;

@TargetApi(12)
/* loaded from: classes2.dex */
final class zzdb<K, V> implements zzp<K, V> {
    private LruCache<K, V> zzahy;

    public zzdb(int i9, zzs<K, V> zzsVar) {
        this.zzahy = new zzdc(this, ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, zzsVar);
    }

    @Override // com.google.android.gms.tagmanager.zzp
    public final V get(K k9) {
        return this.zzahy.get(k9);
    }

    @Override // com.google.android.gms.tagmanager.zzp
    public final void zza(K k9, V v8) {
        this.zzahy.put(k9, v8);
    }
}
