package com.google.android.gms.appinvite;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.appinvite.zzf;
import com.google.android.gms.internal.appinvite.zzm;

@Deprecated
/* loaded from: classes2.dex */
public final class AppInvite {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final AppInviteApi AppInviteApi;
    private static final Api.ClientKey<zzm> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zzm, Api.ApiOptions.NoOptions> zze;

    static {
        Api.ClientKey<zzm> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zza zzaVar = new zza();
        zze = zzaVar;
        API = new Api<>("AppInvite.API", zzaVar, clientKey);
        AppInviteApi = new zzf();
    }

    private AppInvite() {
    }
}
