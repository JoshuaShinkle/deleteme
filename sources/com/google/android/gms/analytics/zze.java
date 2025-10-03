package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import android.util.LogPrinter;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;

@VisibleForTesting
/* loaded from: classes2.dex */
public final class zze implements zzo {
    private static final Uri zzrh;
    private final LogPrinter zzsb = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority(ImagesContract.LOCAL);
        zzrh = builder.build();
    }

    @Override // com.google.android.gms.analytics.zzo
    public final Uri zzae() {
        return zzrh;
    }

    @Override // com.google.android.gms.analytics.zzo
    public final void zzb(zzg zzgVar) {
        ArrayList arrayList = new ArrayList(zzgVar.zzaj());
        Collections.sort(arrayList, new zzf(this));
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        int i9 = 0;
        while (i9 < size) {
            Object obj = arrayList.get(i9);
            i9++;
            String string = ((zzi) obj).toString();
            if (!TextUtils.isEmpty(string)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(string);
            }
        }
        this.zzsb.println(sb.toString());
    }
}
