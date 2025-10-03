package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import p000a.InterfaceC0000a;
import p000a.InterfaceC0002c;

/* loaded from: classes.dex */
public class PostMessageService extends Service {

    /* renamed from: b */
    public InterfaceC0002c.a f1247b = new BinderC0266a();

    /* renamed from: androidx.browser.customtabs.PostMessageService$a */
    public class BinderC0266a extends InterfaceC0002c.a {
        public BinderC0266a() {
        }

        @Override // p000a.InterfaceC0002c
        /* renamed from: b */
        public void mo11b(InterfaceC0000a interfaceC0000a, Bundle bundle) {
            interfaceC0000a.mo1j(bundle);
        }

        @Override // p000a.InterfaceC0002c
        /* renamed from: c */
        public void mo12c(InterfaceC0000a interfaceC0000a, String str, Bundle bundle) {
            interfaceC0000a.mo0i(str, bundle);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1247b;
    }
}
