package p012b1;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import p226w1.C6517j;

/* renamed from: b1.l */
/* loaded from: classes.dex */
public class C0597l {

    /* renamed from: a */
    public boolean f3137a;

    /* renamed from: b */
    public final Handler f3138b = new Handler(Looper.getMainLooper(), new a());

    /* renamed from: b1.l$a */
    public static final class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((InterfaceC0595j) message.obj).mo3281b();
            return true;
        }
    }

    /* renamed from: a */
    public void m3290a(InterfaceC0595j<?> interfaceC0595j) {
        C6517j.m24941b();
        if (this.f3137a) {
            this.f3138b.obtainMessage(1, interfaceC0595j).sendToTarget();
            return;
        }
        this.f3137a = true;
        interfaceC0595j.mo3281b();
        this.f3137a = false;
    }
}
