package p167p5;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import p202t4.C6312a;

/* renamed from: p5.a */
/* loaded from: classes2.dex */
public final class C6103a {

    /* renamed from: a */
    public boolean f20709a;

    /* renamed from: b */
    public long f20710b = 400;

    /* renamed from: c */
    public final Set<View> f20711c = Collections.newSetFromMap(new C6312a());

    /* renamed from: d */
    public final Set<View> f20712d = Collections.newSetFromMap(new C6312a());

    /* renamed from: e */
    @SuppressLint({"HandlerLeak"})
    public final Handler f20713e = new a(Looper.getMainLooper());

    /* renamed from: p5.a$a */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            C6103a.this.m23400b();
        }
    }

    /* renamed from: b */
    public final void m23400b() {
        Iterator<View> it = this.f20712d.iterator();
        while (it.hasNext()) {
            it.next().setEnabled(true);
        }
        this.f20712d.clear();
        this.f20709a = false;
    }
}
