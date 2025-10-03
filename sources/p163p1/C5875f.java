package p163p1;

import android.content.Context;
import android.util.Log;
import p163p1.InterfaceC5872c;
import p197t.C6273a;

/* renamed from: p1.f */
/* loaded from: classes.dex */
public class C5875f implements InterfaceC5873d {
    @Override // p163p1.InterfaceC5873d
    /* renamed from: a */
    public InterfaceC5872c mo23319a(Context context, InterfaceC5872c.a aVar) {
        boolean z8 = C6273a.m24022a(context, "android.permission.ACCESS_NETWORK_STATE") == 0;
        if (Log.isLoggable("ConnectivityMonitor", 3)) {
            Log.d("ConnectivityMonitor", z8 ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor" : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
        }
        return z8 ? new C5874e(context, aVar) : new C5879j();
    }
}
