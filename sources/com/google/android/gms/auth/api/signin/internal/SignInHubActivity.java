package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.content.C0406b;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p260authapi.zzan;
import p102j0.AbstractC5066a;

@KeepName
/* loaded from: classes2.dex */
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzcr = false;
    private boolean zzcs = false;
    private SignInConfiguration zzct;
    private boolean zzcu;
    private int zzcv;
    private Intent zzcw;

    public class zzc implements AbstractC5066a.a<Void> {
        private zzc() {
        }

        @Override // p102j0.AbstractC5066a.a
        public final C0406b<Void> onCreateLoader(int i9, Bundle bundle) {
            return new zzf(SignInHubActivity.this, GoogleApiClient.getAllClients());
        }

        @Override // p102j0.AbstractC5066a.a
        public final /* synthetic */ void onLoadFinished(C0406b<Void> c0406b, Void r32) {
            SignInHubActivity signInHubActivity = SignInHubActivity.this;
            signInHubActivity.setResult(signInHubActivity.zzcv, SignInHubActivity.this.zzcw);
            SignInHubActivity.this.finish();
        }

        @Override // p102j0.AbstractC5066a.a
        public final void onLoaderReset(C0406b<Void> c0406b) {
        }
    }

    private final void zzc(int i9) {
        Status status = new Status(i9);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzcr = false;
    }

    private final void zzr() {
        getSupportLoaderManager().mo19835c(0, null, new zzc());
        zzcr = false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        if (this.zzcs) {
            return;
        }
        setResult(0);
        if (i9 != 40962) {
            return;
        }
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
            if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                zzo.zzd(this).zzc(this.zzct.zzp(), (GoogleSignInAccount) zzan.checkNotNull(googleSignInAccount));
                intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                intent.putExtra("googleSignInAccount", googleSignInAccount);
                this.zzcu = true;
                this.zzcv = i10;
                this.zzcw = intent;
                zzr();
                return;
            }
            if (intent.hasExtra("errorCode")) {
                int intExtra = intent.getIntExtra("errorCode", 8);
                if (intExtra == 13) {
                    intExtra = GoogleSignInStatusCodes.SIGN_IN_CANCELLED;
                }
                zzc(intExtra);
                return;
            }
        }
        zzc(8);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String str = (String) zzan.checkNotNull(intent.getAction());
        if ("com.google.android.gms.auth.NO_IMPL".equals(str)) {
            zzc(GoogleSignInStatusCodes.SIGN_IN_FAILED);
            return;
        }
        if (!str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") && !str.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            String strValueOf = String.valueOf(intent.getAction());
            Log.e("AuthSignInClient", strValueOf.length() != 0 ? "Unknown action: ".concat(strValueOf) : new String("Unknown action: "));
            finish();
            return;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) ((Bundle) zzan.checkNotNull(intent.getBundleExtra("config"))).getParcelable("config");
        if (signInConfiguration == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
            return;
        }
        this.zzct = signInConfiguration;
        if (bundle != null) {
            boolean z8 = bundle.getBoolean("signingInGoogleApiClients");
            this.zzcu = z8;
            if (z8) {
                this.zzcv = bundle.getInt("signInResultCode");
                this.zzcw = (Intent) zzan.checkNotNull((Intent) bundle.getParcelable("signInResultData"));
                zzr();
                return;
            }
            return;
        }
        if (zzcr) {
            setResult(0);
            zzc(GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
            return;
        }
        zzcr = true;
        Intent intent2 = new Intent(str);
        if (str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
            intent2.setPackage("com.google.android.gms");
        } else {
            intent2.setPackage(getPackageName());
        }
        intent2.putExtra("config", this.zzct);
        try {
            startActivityForResult(intent2, 40962);
        } catch (ActivityNotFoundException unused) {
            this.zzcs = true;
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            zzc(17);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzcu);
        if (this.zzcu) {
            bundle.putInt("signInResultCode", this.zzcv);
            bundle.putParcelable("signInResultData", this.zzcw);
        }
    }
}
