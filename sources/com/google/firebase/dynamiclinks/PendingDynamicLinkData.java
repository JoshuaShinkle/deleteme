package com.google.firebase.dynamiclinks;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.dynamiclinks.internal.DynamicLinkData;

/* loaded from: classes2.dex */
public class PendingDynamicLinkData {
    private final DynamicLinkData zzi;

    @VisibleForTesting
    @KeepForSdk
    public PendingDynamicLinkData(DynamicLinkData dynamicLinkData) {
        if (dynamicLinkData == null) {
            this.zzi = null;
            return;
        }
        if (dynamicLinkData.getClickTimestamp() == 0) {
            dynamicLinkData.zza(DefaultClock.getInstance().currentTimeMillis());
        }
        this.zzi = dynamicLinkData;
    }

    @VisibleForTesting
    private final Uri zzc() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return null;
        }
        return dynamicLinkData.zzc();
    }

    public long getClickTimestamp() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return 0L;
        }
        return dynamicLinkData.getClickTimestamp();
    }

    @ShowFirstParty
    @KeepForSdk
    public Bundle getExtensions() {
        DynamicLinkData dynamicLinkData = this.zzi;
        return dynamicLinkData == null ? new Bundle() : dynamicLinkData.zzf();
    }

    public Uri getLink() {
        String strZzd;
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null || (strZzd = dynamicLinkData.zzd()) == null) {
            return null;
        }
        return Uri.parse(strZzd);
    }

    public int getMinimumAppVersion() {
        DynamicLinkData dynamicLinkData = this.zzi;
        if (dynamicLinkData == null) {
            return 0;
        }
        return dynamicLinkData.zze();
    }

    public Intent getUpdateAppIntent(Context context) {
        if (getMinimumAppVersion() == 0) {
            return null;
        }
        try {
            if (context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode < getMinimumAppVersion() && zzc() != null) {
                return new Intent("android.intent.action.VIEW").setData(zzc()).setPackage("com.android.vending");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    public PendingDynamicLinkData(String str, int i9, long j9, Uri uri) {
        this.zzi = new DynamicLinkData(null, str, i9, j9, null, uri);
    }
}
