package com.cyberlink.clsm;

import android.util.Log;
import androidx.annotation.Keep;

/* loaded from: classes.dex */
public final class SmLog {

    /* renamed from: a */
    public static volatile InterfaceC1197b f5806a = new C1196a();

    /* renamed from: com.cyberlink.clsm.SmLog$a */
    public class C1196a extends C1198c {
        public C1196a() {
            super(null);
        }
    }

    /* renamed from: com.cyberlink.clsm.SmLog$b */
    public interface InterfaceC1197b {
        /* renamed from: a */
        default void mo5271a(String str) {
        }

        /* renamed from: b */
        default void mo5272b(String str) {
        }

        /* renamed from: c */
        default void mo5273c(String str) {
        }

        /* renamed from: d */
        default void mo5274d(String str) {
        }

        /* renamed from: e */
        default void mo5275e(Throwable th) {
        }

        /* renamed from: f */
        default void mo5276f(String str) {
        }
    }

    /* renamed from: com.cyberlink.clsm.SmLog$c */
    public static class C1198c implements InterfaceC1197b {
        public C1198c() {
        }

        public /* synthetic */ C1198c(C1196a c1196a) {
            this();
        }

        @Override // com.cyberlink.clsm.SmLog.InterfaceC1197b
        /* renamed from: a */
        public void mo5271a(String str) {
            Log.e("CLSM", str);
        }

        @Override // com.cyberlink.clsm.SmLog.InterfaceC1197b
        /* renamed from: b */
        public void mo5272b(String str) {
            Log.v("CLSM", str);
        }

        @Override // com.cyberlink.clsm.SmLog.InterfaceC1197b
        /* renamed from: c */
        public void mo5273c(String str) {
            Log.d("CLSM", str);
        }

        @Override // com.cyberlink.clsm.SmLog.InterfaceC1197b
        /* renamed from: d */
        public void mo5274d(String str) {
            Log.i("CLSM", str);
        }

        @Override // com.cyberlink.clsm.SmLog.InterfaceC1197b
        /* renamed from: e */
        public void mo5275e(Throwable th) {
            Log.e("CLSM", "Something went wrong", th);
        }

        @Override // com.cyberlink.clsm.SmLog.InterfaceC1197b
        /* renamed from: f */
        public void mo5276f(String str) {
            Log.w("CLSM", str);
        }
    }

    @Keep
    public static void OnLog(int i9, String str) {
        if (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }
        if (2 == i9) {
            f5806a.mo5272b(str);
            return;
        }
        if (3 == i9) {
            f5806a.mo5273c(str);
            return;
        }
        if (4 == i9) {
            f5806a.mo5274d(str);
        } else if (5 == i9) {
            f5806a.mo5276f(str);
        } else if (6 == i9) {
            f5806a.mo5271a(str);
        }
    }

    /* renamed from: a */
    public static void m5268a(Throwable th) {
        f5806a.mo5275e(th);
    }

    /* renamed from: b */
    public static void m5269b(InterfaceC1197b interfaceC1197b) {
        if (interfaceC1197b == null) {
            interfaceC1197b = new C1198c(null);
        }
        f5806a = interfaceC1197b;
    }

    /* renamed from: c */
    public static void m5270c(String str) {
        f5806a.mo5276f(str);
    }
}
