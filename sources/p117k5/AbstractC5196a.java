package p117k5;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.perfectcorp.ycl.network.downloader.DownloadingState;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p107j5.C5102a;
import p107j5.InterfaceC5103b;

/* renamed from: k5.a */
/* loaded from: classes2.dex */
public abstract class AbstractC5196a<Result> extends AbstractC5198c<Result> {

    /* renamed from: d */
    public static final InterfaceC5103b f17799d = new a();

    /* renamed from: e */
    public static final b f17800e = new b(Looper.getMainLooper());

    /* renamed from: b */
    public DownloadingState f17801b = new DownloadingState(DownloadingState.State.Waiting, DownloadingState.f16032c);

    /* renamed from: c */
    public final List<InterfaceC5103b> f17802c = new ArrayList();

    /* renamed from: k5.a$a */
    public class a implements InterfaceC5103b {
        @Override // p107j5.InterfaceC5103b
        /* renamed from: a */
        public void mo19951a(double d9) {
        }
    }

    /* renamed from: k5.a$b */
    public static class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            c cVar = (c) message.obj;
            if (message.what != 534) {
                return;
            }
            Iterator<InterfaceC5103b> it = cVar.f17803a.m20315d().iterator();
            while (it.hasNext()) {
                it.next().mo19951a(cVar.f17804b);
            }
        }
    }

    /* renamed from: k5.a$c */
    public static final class c {

        /* renamed from: a */
        public final AbstractC5196a f17803a;

        /* renamed from: b */
        public final double f17804b;

        public /* synthetic */ c(AbstractC5196a abstractC5196a, double d9, a aVar) {
            this(abstractC5196a, d9);
        }

        public c(AbstractC5196a abstractC5196a, double d9) {
            this.f17803a = abstractC5196a;
            this.f17804b = d9;
        }
    }

    /* renamed from: d */
    public List<InterfaceC5103b> m20315d() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.f17802c);
        }
        return arrayList;
    }

    /* renamed from: e */
    public final void m20316e(double d9) {
        if (this.f17807a.isCancelled() || this.f17807a.isDone()) {
            return;
        }
        m20318g(new DownloadingState(DownloadingState.State.Running, new C5102a((long) d9, 0L)));
        f17800e.obtainMessage(534, new c(this, d9, null)).sendToTarget();
    }

    /* renamed from: f */
    public final void m20317f() {
        f17800e.removeMessages(534);
    }

    /* renamed from: g */
    public final void m20318g(DownloadingState downloadingState) {
        this.f17801b = downloadingState;
    }
}
