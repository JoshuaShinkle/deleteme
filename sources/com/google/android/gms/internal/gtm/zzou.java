package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzou {
    private zzl zzakg;
    private final Map<String, zzl> zzats;

    private zzou() {
        this.zzats = new HashMap();
    }

    public final zzou zzb(String str, zzl zzlVar) {
        this.zzats.put(str, zzlVar);
        return this;
    }

    public final zzou zzm(zzl zzlVar) {
        this.zzakg = zzlVar;
        return this;
    }

    public final zzot zzmm() {
        return new zzot(this.zzats, this.zzakg);
    }
}
