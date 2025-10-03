package com.google.android.gms.internal.gtm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.firebase.messaging.Constants;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class zzda extends zzan {
    private String zzaau;
    private String zzaav;
    protected int zzaax;
    private int zzacu;
    protected boolean zzacv;
    private boolean zzacw;
    private boolean zzacx;

    public zzda(zzap zzapVar) {
        super(zzapVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzan
    public final void zzaw() throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo;
        int i9;
        zzcc zzccVarZzq;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e9) {
            zzd("PackageManager doesn't know about the app package", e9);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzt("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle == null || (i9 = bundle.getInt("com.google.android.gms.analytics.globalConfigResource")) <= 0 || (zzccVarZzq = new zzca(zzcm()).zzq(i9)) == null) {
            return;
        }
        zzq("Loading global XML config values");
        String str = zzccVarZzq.zzaau;
        if (str != null) {
            this.zzaau = str;
            zzb("XML config - app name", str);
        }
        String str2 = zzccVarZzq.zzaav;
        if (str2 != null) {
            this.zzaav = str2;
            zzb("XML config - app version", str2);
        }
        String str3 = zzccVarZzq.zzaaw;
        if (str3 != null) {
            String lowerCase = str3.toLowerCase(Locale.US);
            int i10 = "verbose".equals(lowerCase) ? 0 : "info".equals(lowerCase) ? 1 : "warning".equals(lowerCase) ? 2 : Constants.IPC_BUNDLE_KEY_SEND_ERROR.equals(lowerCase) ? 3 : -1;
            if (i10 >= 0) {
                this.zzacu = i10;
                zza("XML config - log level", Integer.valueOf(i10));
            }
        }
        int i11 = zzccVarZzq.zzaax;
        if (i11 >= 0) {
            this.zzaax = i11;
            this.zzacv = true;
            zzb("XML config - dispatch period (sec)", Integer.valueOf(i11));
        }
        int i12 = zzccVarZzq.zzaay;
        if (i12 != -1) {
            boolean z8 = i12 == 1;
            this.zzacx = z8;
            this.zzacw = true;
            zzb("XML config - dry run", Boolean.valueOf(z8));
        }
    }

    public final String zzaz() {
        zzdb();
        return this.zzaau;
    }

    public final String zzba() {
        zzdb();
        return this.zzaav;
    }

    public final boolean zzgh() {
        zzdb();
        return false;
    }

    public final boolean zzgi() {
        zzdb();
        return this.zzacw;
    }

    public final boolean zzgj() {
        zzdb();
        return this.zzacx;
    }
}
