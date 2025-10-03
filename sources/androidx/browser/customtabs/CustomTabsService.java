package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import p000a.InterfaceC0000a;
import p000a.InterfaceC0001b;
import p111k.C5110b;
import p132m.C5302a;

/* loaded from: classes.dex */
public abstract class CustomTabsService extends Service {

    /* renamed from: b */
    public final Map<IBinder, IBinder.DeathRecipient> f1242b = new C5302a();

    /* renamed from: c */
    public InterfaceC0001b.a f1243c = new BinderC0265a();

    /* renamed from: androidx.browser.customtabs.CustomTabsService$a */
    public class BinderC0265a extends InterfaceC0001b.a {

        /* renamed from: androidx.browser.customtabs.CustomTabsService$a$a */
        public class a implements IBinder.DeathRecipient {

            /* renamed from: a */
            public final /* synthetic */ C5110b f1245a;

            public a(C5110b c5110b) {
                this.f1245a = c5110b;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                CustomTabsService.this.m1090a(this.f1245a);
            }
        }

        public BinderC0265a() {
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: a */
        public boolean mo3a(InterfaceC0000a interfaceC0000a, Uri uri) {
            return CustomTabsService.this.m1095f(new C5110b(interfaceC0000a), uri);
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: d */
        public Bundle mo4d(String str, Bundle bundle) {
            return CustomTabsService.this.m1091b(str, bundle);
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: e */
        public boolean mo5e(InterfaceC0000a interfaceC0000a) {
            C5110b c5110b = new C5110b(interfaceC0000a);
            try {
                a aVar = new a(c5110b);
                synchronized (CustomTabsService.this.f1242b) {
                    interfaceC0000a.asBinder().linkToDeath(aVar, 0);
                    CustomTabsService.this.f1242b.put(interfaceC0000a.asBinder(), aVar);
                }
                return CustomTabsService.this.m1093d(c5110b);
            } catch (RemoteException unused) {
                return false;
            }
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: f */
        public boolean mo6f(long j9) {
            return CustomTabsService.this.m1098i(j9);
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: g */
        public boolean mo7g(InterfaceC0000a interfaceC0000a, Uri uri, Bundle bundle, List<Bundle> list) {
            return CustomTabsService.this.m1092c(new C5110b(interfaceC0000a), uri, bundle, list);
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: h */
        public int mo8h(InterfaceC0000a interfaceC0000a, String str, Bundle bundle) {
            return CustomTabsService.this.m1094e(new C5110b(interfaceC0000a), str, bundle);
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: k */
        public boolean mo9k(InterfaceC0000a interfaceC0000a, Bundle bundle) {
            return CustomTabsService.this.m1096g(new C5110b(interfaceC0000a), bundle);
        }

        @Override // p000a.InterfaceC0001b
        /* renamed from: l */
        public boolean mo10l(InterfaceC0000a interfaceC0000a, int i9, Uri uri, Bundle bundle) {
            return CustomTabsService.this.m1097h(new C5110b(interfaceC0000a), i9, uri, bundle);
        }
    }

    /* renamed from: a */
    public boolean m1090a(C5110b c5110b) {
        try {
            synchronized (this.f1242b) {
                IBinder iBinderM19959a = c5110b.m19959a();
                iBinderM19959a.unlinkToDeath(this.f1242b.get(iBinderM19959a), 0);
                this.f1242b.remove(iBinderM19959a);
            }
            return true;
        } catch (NoSuchElementException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public abstract Bundle m1091b(String str, Bundle bundle);

    /* renamed from: c */
    public abstract boolean m1092c(C5110b c5110b, Uri uri, Bundle bundle, List<Bundle> list);

    /* renamed from: d */
    public abstract boolean m1093d(C5110b c5110b);

    /* renamed from: e */
    public abstract int m1094e(C5110b c5110b, String str, Bundle bundle);

    /* renamed from: f */
    public abstract boolean m1095f(C5110b c5110b, Uri uri);

    /* renamed from: g */
    public abstract boolean m1096g(C5110b c5110b, Bundle bundle);

    /* renamed from: h */
    public abstract boolean m1097h(C5110b c5110b, int i9, Uri uri, Bundle bundle);

    /* renamed from: i */
    public abstract boolean m1098i(long j9);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1243c;
    }
}
