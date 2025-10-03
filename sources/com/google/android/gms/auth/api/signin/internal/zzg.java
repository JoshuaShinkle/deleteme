package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class zzg {
    private static Logger zzcb = new Logger("GoogleSignInCommon", new String[0]);

    public static GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        if (intent == null) {
            return new GoogleSignInResult(null, Status.RESULT_INTERNAL_ERROR);
        }
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        if (googleSignInAccount != null) {
            return new GoogleSignInResult(googleSignInAccount, Status.RESULT_SUCCESS);
        }
        if (status == null) {
            status = Status.RESULT_INTERNAL_ERROR;
        }
        return new GoogleSignInResult(null, status);
    }

    public static Intent zzc(Context context, GoogleSignInOptions googleSignInOptions) {
        zzcb.m17524d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    public static Intent zzd(Context context, GoogleSignInOptions googleSignInOptions) {
        zzcb.m17524d("getFallbackSignInIntent()", new Object[0]);
        Intent intentZzc = zzc(context, googleSignInOptions);
        intentZzc.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return intentZzc;
    }

    public static Intent zze(Context context, GoogleSignInOptions googleSignInOptions) {
        zzcb.m17524d("getNoImplementationSignInIntent()", new Object[0]);
        Intent intentZzc = zzc(context, googleSignInOptions);
        intentZzc.setAction("com.google.android.gms.auth.NO_IMPL");
        return intentZzc;
    }

    public static PendingResult<Status> zzd(GoogleApiClient googleApiClient, Context context, boolean z8) {
        zzcb.m17524d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zzc(context);
        if (z8) {
            return zzc.zzf(savedRefreshToken);
        }
        return googleApiClient.execute(new zzn(googleApiClient));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static OptionalPendingResult<GoogleSignInResult> zzc(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions, boolean z8) {
        GoogleSignInResult googleSignInResult;
        boolean zEquals;
        GoogleSignInAccount googleSignInAccountZzl;
        zzcb.m17524d("silentSignIn()", new Object[0]);
        zzcb.m17524d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull(googleSignInOptions);
        GoogleSignInOptions googleSignInOptionsZzm = zzo.zzd(context).zzm();
        if (googleSignInOptionsZzm != null) {
            Account account = googleSignInOptionsZzm.getAccount();
            Account account2 = googleSignInOptions.getAccount();
            if (account == null) {
                zEquals = account2 == null;
            } else {
                zEquals = account.equals(account2);
            }
            googleSignInResult = (!zEquals || googleSignInOptions.isServerAuthCodeRequested() || (googleSignInOptions.isIdTokenRequested() && !(googleSignInOptionsZzm.isIdTokenRequested() && Objects.equal(googleSignInOptions.getServerClientId(), googleSignInOptionsZzm.getServerClientId()))) || !new HashSet(googleSignInOptionsZzm.getScopes()).containsAll(new HashSet(googleSignInOptions.getScopes())) || (googleSignInAccountZzl = zzo.zzd(context).zzl()) == null || googleSignInAccountZzl.isExpired()) ? null : new GoogleSignInResult(googleSignInAccountZzl, Status.RESULT_SUCCESS);
        }
        if (googleSignInResult != null) {
            zzcb.m17524d("Eligible saved sign in result found", new Object[0]);
            return PendingResults.immediatePendingResult(googleSignInResult, googleApiClient);
        }
        if (z8) {
            return PendingResults.immediatePendingResult(new GoogleSignInResult(null, new Status(4)), googleApiClient);
        }
        zzcb.m17524d("trySilentSignIn()", new Object[0]);
        return new OptionalPendingResultImpl(googleApiClient.enqueue(new zzj(googleApiClient, context, googleSignInOptions)));
    }

    public static PendingResult<Status> zzc(GoogleApiClient googleApiClient, Context context, boolean z8) {
        zzcb.m17524d("Signing out", new Object[0]);
        zzc(context);
        if (z8) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return googleApiClient.execute(new zzl(googleApiClient));
    }

    private static void zzc(Context context) {
        zzo.zzd(context).clear();
        Iterator<GoogleApiClient> it = GoogleApiClient.getAllClients().iterator();
        while (it.hasNext()) {
            it.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }
}
