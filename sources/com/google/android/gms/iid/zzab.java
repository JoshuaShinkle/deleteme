package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;

/* loaded from: classes2.dex */
final class zzab extends zzz<Bundle> {
    public zzab(int i9, int i10, Bundle bundle) {
        super(i9, 1, bundle);
    }

    @Override // com.google.android.gms.iid.zzz
    public final void zzh(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(bundle2);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 16 + strValueOf2.length());
            sb.append("Finishing ");
            sb.append(strValueOf);
            sb.append(" with ");
            sb.append(strValueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcq.setResult(bundle2);
    }

    @Override // com.google.android.gms.iid.zzz
    public final boolean zzw() {
        return false;
    }
}
