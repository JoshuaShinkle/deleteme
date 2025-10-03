package com.google.android.gms.internal.p260authapi;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* loaded from: classes2.dex */
public final class zzr {
    public static PendingIntent zzc(Context context, Auth.AuthCredentialsOptions authCredentialsOptions, HintRequest hintRequest, String str) {
        Preconditions.checkNotNull(context, "context must not be null");
        Preconditions.checkNotNull(hintRequest, "request must not be null");
        String strZzd = authCredentialsOptions == null ? null : authCredentialsOptions.zzd();
        String strZzs = TextUtils.isEmpty(str) ? zzal.zzs() : (String) Preconditions.checkNotNull(str);
        Intent intentPutExtra = new Intent("com.google.android.gms.auth.api.credentials.PICKER").putExtra("claimedCallingPackage", strZzd);
        intentPutExtra.putExtra("logSessionId", strZzs);
        SafeParcelableSerializer.serializeToIntentExtra(hintRequest, intentPutExtra, "com.google.android.gms.credentials.HintRequest");
        return PendingIntent.getActivity(context, 2000, intentPutExtra, 134217728);
    }
}
