package com.google.android.gms.internal.gtm;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* loaded from: classes2.dex */
public final class zzao {
    public static final String VERSION;
    public static final String zzwe;

    static {
        String strReplaceAll = String.valueOf(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
        VERSION = strReplaceAll;
        String strValueOf = String.valueOf(strReplaceAll);
        zzwe = strValueOf.length() != 0 ? "ma".concat(strValueOf) : new String("ma");
    }
}
