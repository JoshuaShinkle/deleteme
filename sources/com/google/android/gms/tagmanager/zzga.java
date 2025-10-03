package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzga implements DataLayer.zzb {
    private final /* synthetic */ TagManager zzalh;

    public zzga(TagManager tagManager) {
        this.zzalh = tagManager;
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzb
    public final void zzc(Map<String, Object> map) {
        Object obj = map.get("event");
        if (obj != null) {
            this.zzalh.zzbl(obj.toString());
        }
    }
}
