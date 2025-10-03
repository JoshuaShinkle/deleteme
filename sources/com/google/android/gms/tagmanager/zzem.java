package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzem extends zzbq {

    /* renamed from: ID */
    private static final String f15357ID = com.google.android.gms.internal.gtm.zza.RESOLUTION.toString();
    private final Context zzrm;

    public zzem(Context context) {
        super(f15357ID, new String[0]);
        this.zzrm = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final com.google.android.gms.internal.gtm.zzl zzb(Map<String, com.google.android.gms.internal.gtm.zzl> map) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.zzrm.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i9 = displayMetrics.widthPixels;
        int i10 = displayMetrics.heightPixels;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i9);
        sb.append("x");
        sb.append(i10);
        return zzgj.zzi(sb.toString());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzgw() {
        return true;
    }
}
