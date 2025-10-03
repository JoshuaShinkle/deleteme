package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzr extends com.google.android.gms.analytics.zzi<zzr> {
    private String name;
    private String zzno;
    private String zztz;
    private String zzua;
    private String zzub;
    private String zzuc;
    private String zzud;
    private String zzue;
    private String zzuf;
    private String zzug;

    public final String getId() {
        return this.zzno;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSource() {
        return this.zztz;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.name);
        map.put("source", this.zztz);
        map.put("medium", this.zzua);
        map.put("keyword", this.zzub);
        map.put(FirebaseAnalytics.Param.CONTENT, this.zzuc);
        map.put(TtmlNode.ATTR_ID, this.zzno);
        map.put("adNetworkId", this.zzud);
        map.put("gclid", this.zzue);
        map.put("dclid", this.zzuf);
        map.put(FirebaseAnalytics.Param.ACLID, this.zzug);
        return com.google.android.gms.analytics.zzi.zza((Object) map);
    }

    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzi zziVar) {
        zzr zzrVar = (zzr) zziVar;
        if (!TextUtils.isEmpty(this.name)) {
            zzrVar.name = this.name;
        }
        if (!TextUtils.isEmpty(this.zztz)) {
            zzrVar.zztz = this.zztz;
        }
        if (!TextUtils.isEmpty(this.zzua)) {
            zzrVar.zzua = this.zzua;
        }
        if (!TextUtils.isEmpty(this.zzub)) {
            zzrVar.zzub = this.zzub;
        }
        if (!TextUtils.isEmpty(this.zzuc)) {
            zzrVar.zzuc = this.zzuc;
        }
        if (!TextUtils.isEmpty(this.zzno)) {
            zzrVar.zzno = this.zzno;
        }
        if (!TextUtils.isEmpty(this.zzud)) {
            zzrVar.zzud = this.zzud;
        }
        if (!TextUtils.isEmpty(this.zzue)) {
            zzrVar.zzue = this.zzue;
        }
        if (!TextUtils.isEmpty(this.zzuf)) {
            zzrVar.zzuf = this.zzuf;
        }
        if (TextUtils.isEmpty(this.zzug)) {
            return;
        }
        zzrVar.zzug = this.zzug;
    }

    public final String zzbd() {
        return this.zzua;
    }

    public final String zzbe() {
        return this.zzub;
    }

    public final String zzbf() {
        return this.zzuc;
    }

    public final String zzbg() {
        return this.zzud;
    }

    public final String zzbh() {
        return this.zzue;
    }

    public final String zzbi() {
        return this.zzuf;
    }

    public final String zzbj() {
        return this.zzug;
    }

    public final void zzc(String str) {
        this.zztz = str;
    }

    public final void zzd(String str) {
        this.zzua = str;
    }

    public final void zze(String str) {
        this.zzub = str;
    }

    public final void zzf(String str) {
        this.zzuc = str;
    }

    public final void zzg(String str) {
        this.zzno = str;
    }

    public final void zzh(String str) {
        this.zzud = str;
    }

    public final void zzi(String str) {
        this.zzue = str;
    }

    public final void zzj(String str) {
        this.zzuf = str;
    }

    public final void zzk(String str) {
        this.zzug = str;
    }
}
