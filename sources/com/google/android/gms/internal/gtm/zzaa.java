package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.UUID;

@ShowFirstParty
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzaa extends com.google.android.gms.analytics.zzi<zzaa> {
    private String zzva;
    private int zzvb;
    private int zzvc;
    private String zzvd;
    private String zzve;
    private boolean zzvf;
    private boolean zzvg;

    public zzaa() {
        this(false);
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("screenName", this.zzva);
        map.put("interstitial", Boolean.valueOf(this.zzvf));
        map.put("automatic", Boolean.valueOf(this.zzvg));
        map.put("screenId", Integer.valueOf(this.zzvb));
        map.put("referrerScreenId", Integer.valueOf(this.zzvc));
        map.put("referrerScreenName", this.zzvd);
        map.put("referrerUri", this.zzve);
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzaa zzaaVar = (zzaa) zziVar;
        if (!TextUtils.isEmpty(this.zzva)) {
            zzaaVar.zzva = this.zzva;
        }
        int i9 = this.zzvb;
        if (i9 != 0) {
            zzaaVar.zzvb = i9;
        }
        int i10 = this.zzvc;
        if (i10 != 0) {
            zzaaVar.zzvc = i10;
        }
        if (!TextUtils.isEmpty(this.zzvd)) {
            zzaaVar.zzvd = this.zzvd;
        }
        if (!TextUtils.isEmpty(this.zzve)) {
            String str = this.zzve;
            if (TextUtils.isEmpty(str)) {
                zzaaVar.zzve = null;
            } else {
                zzaaVar.zzve = str;
            }
        }
        boolean z8 = this.zzvf;
        if (z8) {
            zzaaVar.zzvf = z8;
        }
        boolean z9 = this.zzvg;
        if (z9) {
            zzaaVar.zzvg = z9;
        }
    }

    public final String zzca() {
        return this.zzva;
    }

    public final int zzcb() {
        return this.zzvb;
    }

    public final String zzcc() {
        return this.zzve;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private zzaa(boolean z8) {
        UUID uuidRandomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (uuidRandomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits == 0 && (leastSignificantBits = (int) (uuidRandomUUID.getMostSignificantBits() & 2147483647L)) == 0) {
            Log.e("GAv4", "UUID.randomUUID() returned 0.");
            leastSignificantBits = Integer.MAX_VALUE;
        }
        this(false, leastSignificantBits);
    }

    @ShowFirstParty
    @VisibleForTesting
    private zzaa(boolean z8, int i9) {
        Preconditions.checkNotZero(i9);
        this.zzvb = i9;
        this.zzvg = false;
    }
}
