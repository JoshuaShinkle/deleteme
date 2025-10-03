package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class zai extends zak {
    private final SparseArray<zaa> zad;

    public class zaa implements GoogleApiClient.OnConnectionFailedListener {
        public final int zaa;
        public final GoogleApiClient zab;
        public final GoogleApiClient.OnConnectionFailedListener zac;

        public zaa(int i9, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zaa = i9;
            this.zab = googleApiClient;
            this.zac = onConnectionFailedListener;
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 27);
            sb.append("beginFailureResolution for ");
            sb.append(strValueOf);
            Log.d("AutoManageHelper", sb.toString());
            zai.this.zab(connectionResult, this.zaa);
        }
    }

    private zai(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zad = new SparseArray<>();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zai zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(lifecycleActivity);
        zai zaiVar = (zai) fragment.getCallbackOrNull("AutoManageHelper", zai.class);
        return zaiVar != null ? zaiVar : new zai(fragment);
    }

    private final zaa zab(int i9) {
        if (this.zad.size() <= i9) {
            return null;
        }
        SparseArray<zaa> sparseArray = this.zad;
        return sparseArray.get(sparseArray.keyAt(i9));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i9 = 0; i9 < this.zad.size(); i9++) {
            zaa zaaVarZab = zab(i9);
            if (zaaVarZab != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zaaVarZab.zaa);
                printWriter.println(":");
                zaaVarZab.zab.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        boolean z8 = this.zaa;
        String strValueOf = String.valueOf(this.zad);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 14);
        sb.append("onStart ");
        sb.append(z8);
        sb.append(StringUtils.SPACE);
        sb.append(strValueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.zab.get() == null) {
            for (int i9 = 0; i9 < this.zad.size(); i9++) {
                zaa zaaVarZab = zab(i9);
                if (zaaVarZab != null) {
                    zaaVarZab.zab.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        for (int i9 = 0; i9 < this.zad.size(); i9++) {
            zaa zaaVarZab = zab(i9);
            if (zaaVarZab != null) {
                zaaVarZab.zab.disconnect();
            }
        }
    }

    public final void zaa(int i9, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z8 = this.zad.indexOfKey(i9) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i9);
        Preconditions.checkState(z8, sb.toString());
        zam zamVar = this.zab.get();
        boolean z9 = this.zaa;
        String strValueOf = String.valueOf(zamVar);
        StringBuilder sb2 = new StringBuilder(strValueOf.length() + 49);
        sb2.append("starting AutoManage for client ");
        sb2.append(i9);
        sb2.append(StringUtils.SPACE);
        sb2.append(z9);
        sb2.append(StringUtils.SPACE);
        sb2.append(strValueOf);
        Log.d("AutoManageHelper", sb2.toString());
        zaa zaaVar = new zaa(i9, googleApiClient, onConnectionFailedListener);
        googleApiClient.registerConnectionFailedListener(zaaVar);
        this.zad.put(i9, zaaVar);
        if (this.zaa && zamVar == null) {
            String strValueOf2 = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(strValueOf2.length() + 11);
            sb3.append("connecting ");
            sb3.append(strValueOf2);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    public final void zaa(int i9) {
        zaa zaaVar = this.zad.get(i9);
        this.zad.remove(i9);
        if (zaaVar != null) {
            zaaVar.zab.unregisterConnectionFailedListener(zaaVar);
            zaaVar.zab.disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa(ConnectionResult connectionResult, int i9) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i9 < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaa zaaVar = this.zad.get(i9);
        if (zaaVar != null) {
            zaa(i9);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaaVar.zac;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zak
    public final void zaa() {
        for (int i9 = 0; i9 < this.zad.size(); i9++) {
            zaa zaaVarZab = zab(i9);
            if (zaaVarZab != null) {
                zaaVarZab.zab.connect();
            }
        }
    }
}
