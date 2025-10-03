package com.google.firebase.dynamiclinks.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.util.VisibleForTesting;

/* loaded from: classes2.dex */
public final class zzc extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzd> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zzd, Api.ApiOptions.NoOptions> zzp;

    static {
        Api.ClientKey<zzd> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzb zzbVar = new zzb();
        zzp = zzbVar;
        API = new Api<>("DynamicLinks.API", zzbVar, clientKey);
    }

    @VisibleForTesting
    public zzc(Context context) {
        super(context, API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
