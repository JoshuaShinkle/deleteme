package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@Deprecated
/* loaded from: classes2.dex */
public class FirebaseInstanceIdService extends Service {
    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Deprecated
    public void onTokenRefresh() {
    }
}
