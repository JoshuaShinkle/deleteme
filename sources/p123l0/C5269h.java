package p123l0;

import android.media.session.MediaSessionManager;
import p021c0.C0697c;

/* renamed from: l0.h */
/* loaded from: classes.dex */
public final class C5269h implements InterfaceC5267f {

    /* renamed from: a */
    public final MediaSessionManager.RemoteUserInfo f17871a;

    public C5269h(String str, int i9, int i10) {
        this.f17871a = new MediaSessionManager.RemoteUserInfo(str, i9, i10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C5269h) {
            return this.f17871a.equals(((C5269h) obj).f17871a);
        }
        return false;
    }

    public int hashCode() {
        return C0697c.m3462b(this.f17871a);
    }

    public C5269h(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        this.f17871a = remoteUserInfo;
    }
}
