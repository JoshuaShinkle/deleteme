package p123l0;

import android.media.session.MediaSessionManager;
import android.os.Build;

/* renamed from: l0.e */
/* loaded from: classes.dex */
public final class C5266e {

    /* renamed from: a */
    public InterfaceC5267f f17870a;

    public C5266e(String str, int i9, int i10) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f17870a = new C5269h(str, i9, i10);
        } else {
            this.f17870a = new C5270i(str, i9, i10);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C5266e) {
            return this.f17870a.equals(((C5266e) obj).f17870a);
        }
        return false;
    }

    public int hashCode() {
        return this.f17870a.hashCode();
    }

    public C5266e(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        this.f17870a = new C5269h(remoteUserInfo);
    }
}
