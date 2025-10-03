package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* loaded from: classes2.dex */
public class zzam {
    private final zzap zzwc;

    public zzam(zzap zzapVar) {
        Preconditions.checkNotNull(zzapVar);
        this.zzwc = zzapVar;
    }

    public static boolean zzda() {
        return Log.isLoggable(zzby.zzzb.get(), 2);
    }

    public final Context getContext() {
        return this.zzwc.getContext();
    }

    public final void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public final void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public final void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public final zzap zzcm() {
        return this.zzwc;
    }

    public final Clock zzcn() {
        return this.zzwc.zzcn();
    }

    public final zzci zzco() {
        return this.zzwc.zzco();
    }

    public final zzbq zzcp() {
        return this.zzwc.zzcp();
    }

    public final com.google.android.gms.analytics.zzk zzcq() {
        return this.zzwc.zzcq();
    }

    public final GoogleAnalytics zzcr() {
        return this.zzwc.zzde();
    }

    public final zzae zzcs() {
        return this.zzwc.zzcs();
    }

    public final zzbv zzct() {
        return this.zzwc.zzct();
    }

    public final zzda zzcu() {
        return this.zzwc.zzcu();
    }

    public final zzcm zzcv() {
        return this.zzwc.zzcv();
    }

    public final zzbh zzcw() {
        return this.zzwc.zzdh();
    }

    public final zzad zzcx() {
        return this.zzwc.zzdg();
    }

    public final zzba zzcy() {
        return this.zzwc.zzcy();
    }

    public final zzbu zzcz() {
        return this.zzwc.zzcz();
    }

    public final void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public final void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public final void zzq(String str) {
        zza(2, str, null, null, null);
    }

    public final void zzr(String str) {
        zza(3, str, null, null, null);
    }

    public final void zzs(String str) {
        zza(4, str, null, null, null);
    }

    public final void zzt(String str) {
        zza(5, str, null, null, null);
    }

    public final void zzu(String str) {
        zza(6, str, null, null, null);
    }

    public final void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public final void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public final void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    public final void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public static String zzc(String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String strZzb = zzb(obj);
        String strZzb2 = zzb(obj2);
        String strZzb3 = zzb(obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(strZzb)) {
            sb.append(str2);
            sb.append(strZzb);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(strZzb2)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(strZzb2);
        }
        if (!TextUtils.isEmpty(strZzb3)) {
            sb.append(str3);
            sb.append(strZzb3);
        }
        return sb.toString();
    }

    public final void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public final void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    private final void zza(int i9, String str, Object obj, Object obj2, Object obj3) {
        zzap zzapVar = this.zzwc;
        zzci zzciVarZzdd = zzapVar != null ? zzapVar.zzdd() : null;
        if (zzciVarZzdd != null) {
            String str2 = zzby.zzzb.get();
            if (Log.isLoggable(str2, i9)) {
                Log.println(i9, str2, zzc(str, obj, obj2, obj3));
            }
            if (i9 >= 5) {
                zzciVarZzdd.zzb(i9, str, obj, obj2, obj3);
                return;
            }
            return;
        }
        String str3 = zzby.zzzb.get();
        if (Log.isLoggable(str3, i9)) {
            Log.println(i9, str3, zzc(str, obj, obj2, obj3));
        }
    }

    private static String zzb(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Boolean) {
            return obj == Boolean.TRUE ? "true" : "false";
        }
        if (obj instanceof Throwable) {
            return ((Throwable) obj).toString();
        }
        return obj.toString();
    }
}
