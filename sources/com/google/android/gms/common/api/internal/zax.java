package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import p132m.C5303b;

/* loaded from: classes2.dex */
public class zax extends zak {
    private final C5303b<ApiKey<?>> zad;
    private final GoogleApiManager zae;

    private zax(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager) {
        this(lifecycleFragment, googleApiManager, GoogleApiAvailability.getInstance());
    }

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, ApiKey<?> apiKey) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(activity);
        zax zaxVar = (zax) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zax.class);
        if (zaxVar == null) {
            zaxVar = new zax(fragment, googleApiManager);
        }
        Preconditions.checkNotNull(apiKey, "ApiKey cannot be null");
        zaxVar.zad.add(apiKey);
        googleApiManager.zaa(zaxVar);
    }

    private final void zad() {
        if (this.zad.isEmpty()) {
            return;
        }
        this.zae.zaa(this);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onResume() {
        super.onResume();
        zad();
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        zad();
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zae.zab(this);
    }

    public final C5303b<ApiKey<?>> zac() {
        return this.zad;
    }

    @VisibleForTesting
    private zax(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment, googleApiAvailability);
        this.zad = new C5303b<>();
        this.zae = googleApiManager;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa(ConnectionResult connectionResult, int i9) {
        this.zae.zab(connectionResult, i9);
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa() {
        this.zae.zac();
    }
}
