package p163p1;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import p163p1.InterfaceC5872c;
import p226w1.C6516i;

/* renamed from: p1.e */
/* loaded from: classes.dex */
public final class C5874e implements InterfaceC5872c {

    /* renamed from: b */
    public final Context f20320b;

    /* renamed from: c */
    public final InterfaceC5872c.a f20321c;

    /* renamed from: d */
    public boolean f20322d;

    /* renamed from: e */
    public boolean f20323e;

    /* renamed from: f */
    public final BroadcastReceiver f20324f = new a();

    /* renamed from: p1.e$a */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            C5874e c5874e = C5874e.this;
            boolean z8 = c5874e.f20322d;
            c5874e.f20322d = c5874e.m23320c(context);
            if (z8 != C5874e.this.f20322d) {
                if (Log.isLoggable("ConnectivityMonitor", 3)) {
                    Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + C5874e.this.f20322d);
                }
                C5874e c5874e2 = C5874e.this;
                c5874e2.f20321c.mo23318a(c5874e2.f20322d);
            }
        }
    }

    public C5874e(Context context, InterfaceC5872c.a aVar) {
        this.f20320b = context.getApplicationContext();
        this.f20321c = aVar;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: c */
    public boolean m23320c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C6516i.m24938d((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException e9) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e9);
            }
            return true;
        }
    }

    /* renamed from: d */
    public final void m23321d() {
        if (this.f20323e) {
            return;
        }
        this.f20322d = m23320c(this.f20320b);
        try {
            this.f20320b.registerReceiver(this.f20324f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.f20323e = true;
        } catch (SecurityException e9) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to register", e9);
            }
        }
    }

    /* renamed from: k */
    public final void m23322k() {
        if (this.f20323e) {
            this.f20320b.unregisterReceiver(this.f20324f);
            this.f20323e = false;
        }
    }

    @Override // p163p1.InterfaceC5878i
    public void onDestroy() {
    }

    @Override // p163p1.InterfaceC5878i
    public void onStart() {
        m23321d();
    }

    @Override // p163p1.InterfaceC5878i
    public void onStop() {
        m23322k();
    }
}
