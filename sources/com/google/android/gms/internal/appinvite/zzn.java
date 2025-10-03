package com.google.android.gms.internal.appinvite;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.api.Status;

/* loaded from: classes2.dex */
final class zzn extends zze {
    private final /* synthetic */ zzk zzq;

    public zzn(zzk zzkVar) {
        this.zzq = zzkVar;
    }

    @Override // com.google.android.gms.internal.appinvite.zze, com.google.android.gms.internal.appinvite.zzo
    public final void zza(Status status, Intent intent) {
        Activity activity;
        this.zzq.setResult((zzk) new zzp(status, intent));
        if (!AppInviteReferral.hasReferral(intent) || !this.zzq.zzm || this.zzq.zzl == null || (activity = (Activity) this.zzq.zzl.get()) == null) {
            return;
        }
        activity.startActivity(intent);
    }
}
