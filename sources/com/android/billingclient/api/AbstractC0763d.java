package com.android.billingclient.api;

import android.app.Activity;
import android.content.Context;

/* renamed from: com.android.billingclient.api.d */
/* loaded from: classes.dex */
public abstract class AbstractC0763d {

    /* renamed from: com.android.billingclient.api.d$a */
    public static final class a {

        /* renamed from: a */
        public volatile C0783l0 f3553a;

        /* renamed from: b */
        public final Context f3554b;

        /* renamed from: c */
        public volatile InterfaceC0782l f3555c;

        public /* synthetic */ a(Context context, C0803v0 c0803v0) {
            this.f3554b = context;
        }

        /* renamed from: a */
        public AbstractC0763d m3679a() {
            if (this.f3554b == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            if (this.f3555c == null) {
                throw new IllegalArgumentException("Please provide a valid listener for purchases updates.");
            }
            if (this.f3553a == null) {
                throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
            }
            InterfaceC0782l interfaceC0782l = this.f3555c;
            return this.f3555c != null ? new C0766e(null, this.f3553a, this.f3554b, this.f3555c, null, null) : new C0766e(null, this.f3553a, this.f3554b, null, null);
        }

        /* renamed from: b */
        public a m3680b() {
            C0779j0 c0779j0 = new C0779j0(null);
            c0779j0.m3764a();
            this.f3553a = c0779j0.m3765b();
            return this;
        }

        /* renamed from: c */
        public a m3681c(InterfaceC0782l interfaceC0782l) {
            this.f3555c = interfaceC0782l;
            return this;
        }
    }

    /* renamed from: f */
    public static a m3670f(Context context) {
        return new a(context, null);
    }

    /* renamed from: a */
    public abstract void mo3671a(C0754a c0754a, InterfaceC0757b interfaceC0757b);

    /* renamed from: b */
    public abstract void mo3672b();

    /* renamed from: c */
    public abstract C0774h mo3673c(String str);

    /* renamed from: d */
    public abstract boolean mo3674d();

    /* renamed from: e */
    public abstract C0774h mo3675e(Activity activity, C0772g c0772g);

    /* renamed from: g */
    public abstract void mo3676g(C0784m c0784m, InterfaceC0780k interfaceC0780k);

    @Deprecated
    /* renamed from: h */
    public abstract void mo3677h(C0786n c0786n, InterfaceC0788o interfaceC0788o);

    /* renamed from: i */
    public abstract void mo3678i(InterfaceC0769f interfaceC0769f);
}
