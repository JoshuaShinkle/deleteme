package com.perfectcorp.ycl.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.perfectcorp.ycl.commons.utility.Log;

/* loaded from: classes2.dex */
public class StorageMonitor extends BroadcastReceiver {

    /* renamed from: c */
    public static final InterfaceC4607b f16223c = new C4606a();

    /* renamed from: d */
    public static final StorageMonitor f16224d = new StorageMonitor();

    /* renamed from: a */
    public InterfaceC4607b f16225a;

    /* renamed from: b */
    public boolean f16226b = false;

    /* renamed from: com.perfectcorp.ycl.utility.StorageMonitor$a */
    public class C4606a implements InterfaceC4607b {
        @Override // com.perfectcorp.ycl.utility.StorageMonitor.InterfaceC4607b
        /* renamed from: a */
        public void mo18389a(Uri uri) {
        }
    }

    /* renamed from: com.perfectcorp.ycl.utility.StorageMonitor$b */
    public interface InterfaceC4607b {
        /* renamed from: a */
        void mo18389a(Uri uri);
    }

    private StorageMonitor() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            if ("android.intent.action.MEDIA_EJECT".equals(action)) {
                Log.m18153g("StorageMonitor", "onReceive: " + action);
                this.f16225a.mo18389a(intent.getData());
            }
        } catch (Exception unused) {
        }
    }
}
