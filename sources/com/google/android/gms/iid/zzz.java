package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes2.dex */
abstract class zzz<T> {
    final int what;
    final int zzcp;
    final TaskCompletionSource<T> zzcq = new TaskCompletionSource<>();
    final Bundle zzcr;

    public zzz(int i9, int i10, Bundle bundle) {
        this.zzcp = i9;
        this.what = i10;
        this.zzcr = bundle;
    }

    public String toString() {
        int i9 = this.what;
        int i10 = this.zzcp;
        zzw();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i9);
        sb.append(" id=");
        sb.append(i10);
        sb.append(" oneWay=false}");
        return sb.toString();
    }

    public final void zzd(zzaa zzaaVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(zzaaVar);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 14 + strValueOf2.length());
            sb.append("Failing ");
            sb.append(strValueOf);
            sb.append(" with ");
            sb.append(strValueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcq.setException(zzaaVar);
    }

    public abstract void zzh(Bundle bundle);

    public abstract boolean zzw();
}
