package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
class zzm extends zzgh {

    /* renamed from: ID */
    private static final String f15370ID;
    private static final String URL;
    private static final String zzadw;
    private static final String zzadx;
    private static final String zzady;
    private static final Set<String> zzadz;
    private final zza zzaea;
    private final Context zzrm;

    public interface zza {
        zzbx zzgx();
    }

    static {
        String string = com.google.android.gms.internal.gtm.zza.ARBITRARY_PIXEL.toString();
        f15370ID = string;
        URL = com.google.android.gms.internal.gtm.zzb.URL.toString();
        zzadw = com.google.android.gms.internal.gtm.zzb.ADDITIONAL_PARAMS.toString();
        zzadx = com.google.android.gms.internal.gtm.zzb.UNREPEATABLE.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 17);
        sb.append("gtm_");
        sb.append(string);
        sb.append("_unrepeatable");
        zzady = sb.toString();
        zzadz = new HashSet();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    private final synchronized boolean zzak(String str) {
        Set<String> set = zzadz;
        if (set.contains(str)) {
            return true;
        }
        if (!this.zzrm.getSharedPreferences(zzady, 0).contains(str)) {
            return false;
        }
        set.add(str);
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzgh
    public final void zzd(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        String str = zzadx;
        String strZzc = map.get(str) != null ? zzgj.zzc(map.get(str)) : null;
        if (strZzc == null || !zzak(strZzc)) {
            Uri.Builder builderBuildUpon = Uri.parse(zzgj.zzc(map.get(URL))).buildUpon();
            com.google.android.gms.internal.gtm.zzl zzlVar = map.get(zzadw);
            if (zzlVar != null) {
                Object objZzh = zzgj.zzh(zzlVar);
                if (!(objZzh instanceof List)) {
                    String strValueOf = String.valueOf(builderBuildUpon.build().toString());
                    zzdi.zzav(strValueOf.length() != 0 ? "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(strValueOf) : new String("ArbitraryPixel: additional params not a list: not sending partial hit: "));
                    return;
                }
                for (Object obj : (List) objZzh) {
                    if (!(obj instanceof Map)) {
                        String strValueOf2 = String.valueOf(builderBuildUpon.build().toString());
                        zzdi.zzav(strValueOf2.length() != 0 ? "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(strValueOf2) : new String("ArbitraryPixel: additional params contains non-map: not sending partial hit: "));
                        return;
                    } else {
                        for (Map.Entry entry : ((Map) obj).entrySet()) {
                            builderBuildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                        }
                    }
                }
            }
            String string = builderBuildUpon.build().toString();
            this.zzaea.zzgx().zzay(string);
            String strValueOf3 = String.valueOf(string);
            zzdi.zzab(strValueOf3.length() != 0 ? "ArbitraryPixel: url = ".concat(strValueOf3) : new String("ArbitraryPixel: url = "));
            if (strZzc != null) {
                synchronized (zzm.class) {
                    zzadz.add(strZzc);
                    zzft.zza(this.zzrm, zzady, strZzc, "true");
                }
            }
        }
    }

    @VisibleForTesting
    private zzm(Context context, zza zzaVar) {
        super(f15370ID, URL);
        this.zzaea = zzaVar;
        this.zzrm = context;
    }
}
