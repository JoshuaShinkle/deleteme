package com.google.android.gms.tagmanager;

import android.util.Log;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
/* loaded from: classes2.dex */
public final class zzba implements zzdj {
    private int zzyr = 5;

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void setLogLevel(int i9) {
        this.zzyr = i9;
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zza(String str, Throwable th) {
        if (this.zzyr <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzab(String str) {
        if (this.zzyr <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzac(String str) {
        if (this.zzyr <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzav(String str) {
        if (this.zzyr <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzaw(String str) {
        if (this.zzyr <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzax(String str) {
        if (this.zzyr <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdj
    public final void zzb(String str, Throwable th) {
        if (this.zzyr <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }
}
