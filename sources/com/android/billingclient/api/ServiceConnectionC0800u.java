package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzd;
import com.google.android.gms.internal.play_billing.zzgd;
import java.util.concurrent.Callable;

/* renamed from: com.android.billingclient.api.u */
/* loaded from: classes.dex */
public final class ServiceConnectionC0800u implements ServiceConnection {

    /* renamed from: a */
    public final Object f3641a = new Object();

    /* renamed from: b */
    public boolean f3642b = false;

    /* renamed from: c */
    public InterfaceC0769f f3643c;

    /* renamed from: d */
    public final /* synthetic */ C0766e f3644d;

    public /* synthetic */ ServiceConnectionC0800u(C0766e c0766e, InterfaceC0769f interfaceC0769f, C0798t c0798t) {
        this.f3644d = c0766e;
        this.f3643c = interfaceC0769f;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01dc  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object m3790a() {
        Bundle bundle;
        int i9;
        int iZzq;
        synchronized (this.f3641a) {
            if (this.f3642b) {
                return null;
            }
            if (TextUtils.isEmpty(null)) {
                bundle = null;
            } else {
                bundle = new Bundle();
                bundle.putString("accountName", null);
            }
            int i10 = 3;
            try {
                String packageName = this.f3644d.f3564e.getPackageName();
                iZzq = 3;
                int i11 = 20;
                while (true) {
                    if (i11 < 3) {
                        i11 = 0;
                        break;
                    }
                    if (bundle == null) {
                        try {
                            iZzq = this.f3644d.f3566g.zzq(i11, packageName, "subs");
                        } catch (Exception e9) {
                            e = e9;
                            i10 = iZzq;
                            zzb.zzk("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                            this.f3644d.f3560a = 0;
                            this.f3644d.f3566g = null;
                            i9 = 42;
                            iZzq = i10;
                            if (iZzq != 0) {
                            }
                            return null;
                        }
                    } else {
                        iZzq = this.f3644d.f3566g.zzc(i11, packageName, "subs", bundle);
                    }
                    if (iZzq == 0) {
                        zzb.zzi("BillingClient", "highestLevelSupportedForSubs: " + i11);
                        break;
                    }
                    i11--;
                }
                boolean z8 = true;
                this.f3644d.f3569j = i11 >= 5;
                this.f3644d.f3568i = i11 >= 3;
                if (i11 < 3) {
                    zzb.zzi("BillingClient", "In-app billing API does not support subscription on this device.");
                    i9 = 9;
                } else {
                    i9 = 1;
                }
                int i12 = 20;
                while (true) {
                    if (i12 < 3) {
                        break;
                    }
                    iZzq = bundle == null ? this.f3644d.f3566g.zzq(i12, packageName, "inapp") : this.f3644d.f3566g.zzc(i12, packageName, "inapp", bundle);
                    if (iZzq == 0) {
                        this.f3644d.f3570k = i12;
                        zzb.zzi("BillingClient", "mHighestLevelSupportedForInApp: " + this.f3644d.f3570k);
                        break;
                    }
                    i12--;
                }
                C0766e c0766e = this.f3644d;
                c0766e.f3582w = c0766e.f3570k >= 20;
                C0766e c0766e2 = this.f3644d;
                c0766e2.f3581v = c0766e2.f3570k >= 19;
                C0766e c0766e3 = this.f3644d;
                c0766e3.f3580u = c0766e3.f3570k >= 18;
                C0766e c0766e4 = this.f3644d;
                c0766e4.f3579t = c0766e4.f3570k >= 17;
                C0766e c0766e5 = this.f3644d;
                c0766e5.f3578s = c0766e5.f3570k >= 16;
                C0766e c0766e6 = this.f3644d;
                c0766e6.f3577r = c0766e6.f3570k >= 15;
                C0766e c0766e7 = this.f3644d;
                c0766e7.f3576q = c0766e7.f3570k >= 14;
                C0766e c0766e8 = this.f3644d;
                c0766e8.f3575p = c0766e8.f3570k >= 12;
                C0766e c0766e9 = this.f3644d;
                c0766e9.f3574o = c0766e9.f3570k >= 10;
                C0766e c0766e10 = this.f3644d;
                c0766e10.f3573n = c0766e10.f3570k >= 9;
                C0766e c0766e11 = this.f3644d;
                c0766e11.f3572m = c0766e11.f3570k >= 8;
                C0766e c0766e12 = this.f3644d;
                if (c0766e12.f3570k < 6) {
                    z8 = false;
                }
                c0766e12.f3571l = z8;
                if (this.f3644d.f3570k < 3) {
                    zzb.zzj("BillingClient", "In-app billing API version 3 is not supported on this device.");
                    i9 = 36;
                }
                if (iZzq == 0) {
                    this.f3644d.f3560a = 2;
                } else {
                    this.f3644d.f3560a = 0;
                    this.f3644d.f3566g = null;
                }
            } catch (Exception e10) {
                e = e10;
            }
            if (iZzq != 0) {
                this.f3644d.f3565f.mo3667c(C0810z.m3800b(6));
                m3793d(C0761c0.f3536l);
            } else {
                InterfaceC0755a0 interfaceC0755a0 = this.f3644d.f3565f;
                C0774h c0774h = C0761c0.f3525a;
                interfaceC0755a0.mo3666b(C0810z.m3799a(i9, 6, c0774h));
                m3793d(c0774h);
            }
            return null;
        }
    }

    /* renamed from: b */
    public final /* synthetic */ void m3791b() {
        this.f3644d.f3560a = 0;
        this.f3644d.f3566g = null;
        InterfaceC0755a0 interfaceC0755a0 = this.f3644d.f3565f;
        C0774h c0774h = C0761c0.f3538n;
        interfaceC0755a0.mo3666b(C0810z.m3799a(24, 6, c0774h));
        m3793d(c0774h);
    }

    /* renamed from: c */
    public final void m3792c() {
        synchronized (this.f3641a) {
            this.f3643c = null;
            this.f3642b = true;
        }
    }

    /* renamed from: d */
    public final void m3793d(C0774h c0774h) {
        synchronized (this.f3641a) {
            InterfaceC0769f interfaceC0769f = this.f3643c;
            if (interfaceC0769f != null) {
                interfaceC0769f.onBillingSetupFinished(c0774h);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzb.zzi("BillingClient", "Billing service connected.");
        this.f3644d.f3566g = zzd.zzn(iBinder);
        C0766e c0766e = this.f3644d;
        if (c0766e.m3722z(new Callable() { // from class: com.android.billingclient.api.r
            @Override // java.util.concurrent.Callable
            public final Object call() {
                this.f3635b.m3790a();
                return null;
            }
        }, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS, new Runnable() { // from class: com.android.billingclient.api.s
            @Override // java.lang.Runnable
            public final void run() {
                this.f3636b.m3791b();
            }
        }, c0766e.m3719v()) == null) {
            C0774h c0774hM3721x = this.f3644d.m3721x();
            this.f3644d.f3565f.mo3666b(C0810z.m3799a(25, 6, c0774hM3721x));
            m3793d(c0774hM3721x);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        zzb.zzj("BillingClient", "Billing service disconnected.");
        this.f3644d.f3565f.mo3665a(zzgd.zzw());
        this.f3644d.f3566g = null;
        this.f3644d.f3560a = 0;
        synchronized (this.f3641a) {
            InterfaceC0769f interfaceC0769f = this.f3643c;
            if (interfaceC0769f != null) {
                interfaceC0769f.onBillingServiceDisconnected();
            }
        }
    }
}
