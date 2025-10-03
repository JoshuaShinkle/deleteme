package p043d1;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import p043d1.InterfaceC4653a;
import p216v0.C6446a;
import p243y0.InterfaceC6589b;

/* renamed from: d1.e */
/* loaded from: classes.dex */
public class C4657e implements InterfaceC4653a {

    /* renamed from: b */
    public final File f16299b;

    /* renamed from: c */
    public final long f16300c;

    /* renamed from: e */
    public C6446a f16302e;

    /* renamed from: d */
    public final C4655c f16301d = new C4655c();

    /* renamed from: a */
    public final C4663k f16298a = new C4663k();

    @Deprecated
    public C4657e(File file, long j9) {
        this.f16299b = file;
        this.f16300c = j9;
    }

    /* renamed from: c */
    public static InterfaceC4653a m18606c(File file, long j9) {
        return new C4657e(file, j9);
    }

    @Override // p043d1.InterfaceC4653a
    /* renamed from: a */
    public void mo18599a(InterfaceC6589b interfaceC6589b, InterfaceC4653a.b bVar) {
        C6446a c6446aM18607d;
        String strM18629b = this.f16298a.m18629b(interfaceC6589b);
        this.f16301d.m18601a(strM18629b);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strM18629b + " for for Key: " + interfaceC6589b);
            }
            try {
                c6446aM18607d = m18607d();
            } catch (IOException e9) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e9);
                }
            }
            if (c6446aM18607d.m24657H(strM18629b) != null) {
                return;
            }
            C6446a.c cVarM24655F = c6446aM18607d.m24655F(strM18629b);
            if (cVarM24655F == null) {
                throw new IllegalStateException("Had two simultaneous puts for: " + strM18629b);
            }
            try {
                if (bVar.mo3266a(cVarM24655F.m24671f(0))) {
                    cVarM24655F.m24670e();
                }
                cVarM24655F.m24669b();
            } catch (Throwable th) {
                cVarM24655F.m24669b();
                throw th;
            }
        } finally {
            this.f16301d.m18602b(strM18629b);
        }
    }

    @Override // p043d1.InterfaceC4653a
    /* renamed from: b */
    public File mo18600b(InterfaceC6589b interfaceC6589b) {
        String strM18629b = this.f16298a.m18629b(interfaceC6589b);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strM18629b + " for for Key: " + interfaceC6589b);
        }
        try {
            C6446a.e eVarM24657H = m18607d().m24657H(strM18629b);
            if (eVarM24657H != null) {
                return eVarM24657H.m24686a(0);
            }
            return null;
        } catch (IOException e9) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e9);
            return null;
        }
    }

    @Override // p043d1.InterfaceC4653a
    public synchronized void clear() {
        try {
            try {
                m18607d().m24654D();
            } catch (IOException e9) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to clear disk cache or disk cache cleared externally", e9);
                }
            }
        } finally {
            m18608e();
        }
    }

    /* renamed from: d */
    public final synchronized C6446a m18607d() {
        if (this.f16302e == null) {
            this.f16302e = C6446a.m24643J(this.f16299b, 1, 1, this.f16300c);
        }
        return this.f16302e;
    }

    /* renamed from: e */
    public final synchronized void m18608e() {
        this.f16302e = null;
    }
}
