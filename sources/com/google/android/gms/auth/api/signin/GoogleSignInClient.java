package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    private static final zzc zzbp = new zzc(null);

    @VisibleForTesting
    private static int zzbq = zzd.zzbt;

    public static class zzc implements PendingResultUtil.ResultConverter<GoogleSignInResult, GoogleSignInAccount> {
        private zzc() {
        }

        public /* synthetic */ zzc(com.google.android.gms.auth.api.signin.zzc zzcVar) {
            this();
        }

        @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
        public final /* synthetic */ GoogleSignInAccount convert(Result result) {
            return ((GoogleSignInResult) result).getSignInAccount();
        }
    }

    @VisibleForTesting
    public enum zzd {
        public static final int zzbt = 1;
        public static final int zzbu = 2;
        public static final int zzbv = 3;
        public static final int zzbw = 4;
        private static final /* synthetic */ int[] zzbx = {1, 2, 3, 4};

        public static int[] zzi() {
            return (int[]) zzbx.clone();
        }
    }

    public GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new ApiExceptionMapper());
    }

    private final synchronized int zzh() {
        if (zzbq == zzd.zzbt) {
            Context applicationContext = getApplicationContext();
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(applicationContext, 12451000);
            if (iIsGooglePlayServicesAvailable == 0) {
                zzbq = zzd.zzbw;
            } else if (googleApiAvailability.getErrorResolutionIntent(applicationContext, iIsGooglePlayServicesAvailable, null) != null || DynamiteModule.getLocalVersion(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) {
                zzbq = zzd.zzbu;
            } else {
                zzbq = zzd.zzbv;
            }
        }
        return zzbq;
    }

    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        int i9 = com.google.android.gms.auth.api.signin.zzc.zzbr[zzh() - 1];
        return i9 != 1 ? i9 != 2 ? zzg.zze(applicationContext, getApiOptions()) : zzg.zzc(applicationContext, getApiOptions()) : zzg.zzd(applicationContext, getApiOptions());
    }

    public Task<Void> revokeAccess() {
        return PendingResultUtil.toVoidTask(zzg.zzd(asGoogleApiClient(), getApplicationContext(), zzh() == zzd.zzbv));
    }

    public Task<Void> signOut() {
        return PendingResultUtil.toVoidTask(zzg.zzc(asGoogleApiClient(), getApplicationContext(), zzh() == zzd.zzbv));
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return PendingResultUtil.toTask(zzg.zzc(asGoogleApiClient(), getApplicationContext(), getApiOptions(), zzh() == zzd.zzbv), zzbp);
    }

    public GoogleSignInClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, (StatusExceptionMapper) new ApiExceptionMapper());
    }
}
