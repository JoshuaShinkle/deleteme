package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AbstractC0405a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class zzf extends AbstractC0405a<Void> implements SignInConnectionListener {
    private Semaphore zzce;
    private Set<GoogleApiClient> zzcf;

    public zzf(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzce = new Semaphore(0);
        this.zzcf = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // androidx.loader.content.AbstractC0405a
    /* renamed from: zzj, reason: merged with bridge method [inline-methods] */
    public final Void loadInBackground() throws InterruptedException {
        Iterator<GoogleApiClient> it = this.zzcf.iterator();
        int i9 = 0;
        while (it.hasNext()) {
            if (it.next().maybeSignIn(this)) {
                i9++;
            }
        }
        try {
            this.zzce.tryAcquire(i9, 5L, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e9) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e9);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override // com.google.android.gms.common.api.internal.SignInConnectionListener
    public final void onComplete() {
        this.zzce.release();
    }

    @Override // androidx.loader.content.C0406b
    public final void onStartLoading() {
        this.zzce.drainPermits();
        forceLoad();
    }
}
