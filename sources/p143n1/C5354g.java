package p143n1;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import p012b1.AbstractC0588c;
import p022c1.InterfaceC0707d;
import p190s1.C6250g;
import p199t1.AbstractC6281f;
import p207u0.C6358h;
import p207u0.C6359i;
import p207u0.ComponentCallbacks2C6355e;
import p208u1.InterfaceC6362b;
import p217v1.C6451c;
import p225w0.InterfaceC6503a;
import p226w1.C6516i;
import p226w1.C6517j;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6595h;

/* renamed from: n1.g */
/* loaded from: classes.dex */
public class C5354g {

    /* renamed from: a */
    public final InterfaceC6503a f18219a;

    /* renamed from: b */
    public final Handler f18220b;

    /* renamed from: c */
    public final List<b> f18221c;

    /* renamed from: d */
    public final C6359i f18222d;

    /* renamed from: e */
    public final InterfaceC0707d f18223e;

    /* renamed from: f */
    public boolean f18224f;

    /* renamed from: g */
    public boolean f18225g;

    /* renamed from: h */
    public boolean f18226h;

    /* renamed from: i */
    public C6358h<Bitmap> f18227i;

    /* renamed from: j */
    public a f18228j;

    /* renamed from: k */
    public boolean f18229k;

    /* renamed from: l */
    public a f18230l;

    /* renamed from: m */
    public Bitmap f18231m;

    /* renamed from: n */
    public InterfaceC6595h<Bitmap> f18232n;

    /* renamed from: o */
    public a f18233o;

    /* renamed from: n1.g$a */
    public static class a extends AbstractC6281f<Bitmap> {

        /* renamed from: e */
        public final Handler f18234e;

        /* renamed from: f */
        public final int f18235f;

        /* renamed from: g */
        public final long f18236g;

        /* renamed from: h */
        public Bitmap f18237h;

        public a(Handler handler, int i9, long j9) {
            this.f18234e = handler;
            this.f18235f = i9;
            this.f18236g = j9;
        }

        /* renamed from: c */
        public Bitmap m21044c() {
            return this.f18237h;
        }

        @Override // p199t1.InterfaceC6283h
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void mo7183f(Bitmap bitmap, InterfaceC6362b<? super Bitmap> interfaceC6362b) {
            this.f18237h = bitmap;
            this.f18234e.sendMessageAtTime(this.f18234e.obtainMessage(1, this), this.f18236g);
        }
    }

    /* renamed from: n1.g$b */
    public interface b {
        /* renamed from: a */
        void mo21009a();
    }

    /* renamed from: n1.g$c */
    public class c implements Handler.Callback {
        public c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == 1) {
                C5354g.this.m21037n((a) message.obj);
                return true;
            }
            if (i9 != 2) {
                return false;
            }
            C5354g.this.f18222d.m24432m((a) message.obj);
            return false;
        }
    }

    public C5354g(ComponentCallbacks2C6355e componentCallbacks2C6355e, InterfaceC6503a interfaceC6503a, int i9, int i10, InterfaceC6595h<Bitmap> interfaceC6595h, Bitmap bitmap) {
        this(componentCallbacks2C6355e.m24391g(), ComponentCallbacks2C6355e.m24387u(componentCallbacks2C6355e.m24393i()), interfaceC6503a, null, m21025j(ComponentCallbacks2C6355e.m24387u(componentCallbacks2C6355e.m24393i()), i9, i10), interfaceC6595h, bitmap);
    }

    /* renamed from: g */
    public static InterfaceC6589b m21024g() {
        return new C6451c(Double.valueOf(Math.random()));
    }

    /* renamed from: j */
    public static C6358h<Bitmap> m21025j(C6359i c6359i, int i9, int i10) {
        return c6359i.mo23575d().mo23566b(C6250g.m23911i(AbstractC0588c.f3108b).mo23528m0(true).mo23522g0(true).mo23512X(i9, i10));
    }

    /* renamed from: a */
    public void m21026a() {
        this.f18221c.clear();
        m21038o();
        m21041r();
        a aVar = this.f18228j;
        if (aVar != null) {
            this.f18222d.m24432m(aVar);
            this.f18228j = null;
        }
        a aVar2 = this.f18230l;
        if (aVar2 != null) {
            this.f18222d.m24432m(aVar2);
            this.f18230l = null;
        }
        a aVar3 = this.f18233o;
        if (aVar3 != null) {
            this.f18222d.m24432m(aVar3);
            this.f18233o = null;
        }
        this.f18219a.clear();
        this.f18229k = true;
    }

    /* renamed from: b */
    public ByteBuffer m21027b() {
        return this.f18219a.getData().asReadOnlyBuffer();
    }

    /* renamed from: c */
    public Bitmap m21028c() {
        a aVar = this.f18228j;
        return aVar != null ? aVar.m21044c() : this.f18231m;
    }

    /* renamed from: d */
    public int m21029d() {
        a aVar = this.f18228j;
        if (aVar != null) {
            return aVar.f18235f;
        }
        return -1;
    }

    /* renamed from: e */
    public Bitmap m21030e() {
        return this.f18231m;
    }

    /* renamed from: f */
    public int m21031f() {
        return this.f18219a.mo24874a();
    }

    /* renamed from: h */
    public final int m21032h() {
        return C6517j.m24946g(m21028c().getWidth(), m21028c().getHeight(), m21028c().getConfig());
    }

    /* renamed from: i */
    public int m21033i() {
        return m21028c().getHeight();
    }

    /* renamed from: k */
    public int m21034k() {
        return this.f18219a.mo24879f() + m21032h();
    }

    /* renamed from: l */
    public int m21035l() {
        return m21028c().getWidth();
    }

    /* renamed from: m */
    public final void m21036m() {
        if (!this.f18224f || this.f18225g) {
            return;
        }
        if (this.f18226h) {
            C6516i.m24935a(this.f18233o == null, "Pending target must be null when starting from the first frame");
            this.f18219a.mo24877d();
            this.f18226h = false;
        }
        a aVar = this.f18233o;
        if (aVar != null) {
            this.f18233o = null;
            m21037n(aVar);
            return;
        }
        this.f18225g = true;
        long jUptimeMillis = SystemClock.uptimeMillis() + this.f18219a.mo24875b();
        this.f18219a.advance();
        this.f18230l = new a(this.f18220b, this.f18219a.mo24878e(), jUptimeMillis);
        this.f18227i.mo23566b(C6250g.m23910e0(m21024g())).mo23569s(this.f18219a).m24419k(this.f18230l);
    }

    /* renamed from: n */
    public void m21037n(a aVar) {
        this.f18225g = false;
        if (this.f18229k) {
            this.f18220b.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        if (!this.f18224f) {
            this.f18233o = aVar;
            return;
        }
        if (aVar.m21044c() != null) {
            m21038o();
            a aVar2 = this.f18228j;
            this.f18228j = aVar;
            for (int size = this.f18221c.size() - 1; size >= 0; size--) {
                this.f18221c.get(size).mo21009a();
            }
            if (aVar2 != null) {
                this.f18220b.obtainMessage(2, aVar2).sendToTarget();
            }
        }
        m21036m();
    }

    /* renamed from: o */
    public final void m21038o() {
        Bitmap bitmap = this.f18231m;
        if (bitmap != null) {
            this.f18223e.mo3487c(bitmap);
            this.f18231m = null;
        }
    }

    /* renamed from: p */
    public void m21039p(InterfaceC6595h<Bitmap> interfaceC6595h, Bitmap bitmap) {
        this.f18232n = (InterfaceC6595h) C6516i.m24938d(interfaceC6595h);
        this.f18231m = (Bitmap) C6516i.m24938d(bitmap);
        this.f18227i = this.f18227i.mo23566b(new C6250g().mo23525j0(interfaceC6595h));
    }

    /* renamed from: q */
    public final void m21040q() {
        if (this.f18224f) {
            return;
        }
        this.f18224f = true;
        this.f18229k = false;
        m21036m();
    }

    /* renamed from: r */
    public final void m21041r() {
        this.f18224f = false;
    }

    /* renamed from: s */
    public void m21042s(b bVar) {
        if (this.f18229k) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        if (this.f18221c.contains(bVar)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        boolean zIsEmpty = this.f18221c.isEmpty();
        this.f18221c.add(bVar);
        if (zIsEmpty) {
            m21040q();
        }
    }

    /* renamed from: t */
    public void m21043t(b bVar) {
        this.f18221c.remove(bVar);
        if (this.f18221c.isEmpty()) {
            m21041r();
        }
    }

    public C5354g(InterfaceC0707d interfaceC0707d, C6359i c6359i, InterfaceC6503a interfaceC6503a, Handler handler, C6358h<Bitmap> c6358h, InterfaceC6595h<Bitmap> interfaceC6595h, Bitmap bitmap) {
        this.f18221c = new ArrayList();
        this.f18222d = c6359i;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new c()) : handler;
        this.f18223e = interfaceC0707d;
        this.f18220b = handler;
        this.f18227i = c6358h;
        this.f18219a = interfaceC6503a;
        m21039p(interfaceC6595h, bitmap);
    }
}
