package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.C0837f;
import com.bumptech.glide.load.engine.GlideException;
import java.util.Iterator;
import java.util.List;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p124l1.C5272a;
import p190s1.C6250g;
import p190s1.InterfaceC6246c;
import p190s1.InterfaceC6247d;
import p190s1.InterfaceC6249f;
import p190s1.InterfaceC6251h;
import p199t1.InterfaceC6282g;
import p199t1.InterfaceC6283h;
import p207u0.C6357g;
import p208u1.InterfaceC6363c;
import p226w1.C6512e;
import p226w1.C6517j;
import p235x1.AbstractC6565c;
import p235x1.C6563a;

/* loaded from: classes.dex */
public final class SingleRequest<R> implements InterfaceC6246c, InterfaceC6282g, InterfaceC6251h, C6563a.f {

    /* renamed from: B */
    public static final InterfaceC0699e<SingleRequest<?>> f3912B = C6563a.m25127d(150, new C0851a());

    /* renamed from: C */
    public static final boolean f3913C = Log.isLoggable("Request", 2);

    /* renamed from: A */
    public int f3914A;

    /* renamed from: b */
    public boolean f3915b;

    /* renamed from: c */
    public final String f3916c;

    /* renamed from: d */
    public final AbstractC6565c f3917d;

    /* renamed from: e */
    public InterfaceC6249f<R> f3918e;

    /* renamed from: f */
    public InterfaceC6247d f3919f;

    /* renamed from: g */
    public Context f3920g;

    /* renamed from: h */
    public C6357g f3921h;

    /* renamed from: i */
    public Object f3922i;

    /* renamed from: j */
    public Class<R> f3923j;

    /* renamed from: k */
    public C6250g f3924k;

    /* renamed from: l */
    public int f3925l;

    /* renamed from: m */
    public int f3926m;

    /* renamed from: n */
    public Priority f3927n;

    /* renamed from: o */
    public InterfaceC6283h<R> f3928o;

    /* renamed from: p */
    public List<InterfaceC6249f<R>> f3929p;

    /* renamed from: q */
    public C0837f f3930q;

    /* renamed from: r */
    public InterfaceC6363c<? super R> f3931r;

    /* renamed from: s */
    public InterfaceC0595j<R> f3932s;

    /* renamed from: t */
    public C0837f.d f3933t;

    /* renamed from: u */
    public long f3934u;

    /* renamed from: v */
    public Status f3935v;

    /* renamed from: w */
    public Drawable f3936w;

    /* renamed from: x */
    public Drawable f3937x;

    /* renamed from: y */
    public Drawable f3938y;

    /* renamed from: z */
    public int f3939z;

    public enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    /* renamed from: com.bumptech.glide.request.SingleRequest$a */
    public class C0851a implements C6563a.d<SingleRequest<?>> {
        @Override // p235x1.C6563a.d
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SingleRequest<?> mo3287a() {
            return new SingleRequest<>();
        }
    }

    public SingleRequest() {
        this.f3916c = f3913C ? String.valueOf(super.hashCode()) : null;
        this.f3917d = AbstractC6565c.m25138a();
    }

    /* renamed from: A */
    public static <R> SingleRequest<R> m4001A(Context context, C6357g c6357g, Object obj, Class<R> cls, C6250g c6250g, int i9, int i10, Priority priority, InterfaceC6283h<R> interfaceC6283h, InterfaceC6249f<R> interfaceC6249f, List<InterfaceC6249f<R>> list, InterfaceC6247d interfaceC6247d, C0837f c0837f, InterfaceC6363c<? super R> interfaceC6363c) {
        SingleRequest<R> singleRequest = (SingleRequest) f3912B.mo3465b();
        if (singleRequest == null) {
            singleRequest = new SingleRequest<>();
        }
        singleRequest.m4025s(context, c6357g, obj, cls, c6250g, i9, i10, priority, interfaceC6283h, interfaceC6249f, list, interfaceC6247d, c0837f, interfaceC6363c);
        return singleRequest;
    }

    /* renamed from: u */
    public static boolean m4002u(SingleRequest<?> singleRequest, SingleRequest<?> singleRequest2) {
        List<InterfaceC6249f<?>> list = singleRequest.f3929p;
        int size = list == null ? 0 : list.size();
        List<InterfaceC6249f<?>> list2 = singleRequest2.f3929p;
        return size == (list2 == null ? 0 : list2.size());
    }

    /* renamed from: x */
    public static int m4003x(int i9, float f9) {
        return i9 == Integer.MIN_VALUE ? i9 : Math.round(f9 * i9);
    }

    /* renamed from: B */
    public final void m4004B(GlideException glideException, int i9) {
        boolean zMo7170c;
        this.f3917d.mo25140c();
        int iM24410f = this.f3921h.m24410f();
        if (iM24410f <= i9) {
            Log.w("Glide", "Load failed for " + this.f3922i + " with size [" + this.f3939z + "x" + this.f3914A + "]", glideException);
            if (iM24410f <= 4) {
                glideException.m3887g("Glide");
            }
        }
        this.f3933t = null;
        this.f3935v = Status.FAILED;
        boolean z8 = true;
        this.f3915b = true;
        try {
            List<InterfaceC6249f<R>> list = this.f3929p;
            if (list != null) {
                Iterator<InterfaceC6249f<R>> it = list.iterator();
                zMo7170c = false;
                while (it.hasNext()) {
                    zMo7170c |= it.next().mo7170c(glideException, this.f3922i, this.f3928o, m4026t());
                }
            } else {
                zMo7170c = false;
            }
            InterfaceC6249f<R> interfaceC6249f = this.f3918e;
            if (interfaceC6249f == null || !interfaceC6249f.mo7170c(glideException, this.f3922i, this.f3928o, m4026t())) {
                z8 = false;
            }
            if (!(zMo7170c | z8)) {
                m4007E();
            }
            this.f3915b = false;
            m4029y();
        } catch (Throwable th) {
            this.f3915b = false;
            throw th;
        }
    }

    /* renamed from: C */
    public final void m4005C(InterfaceC0595j<R> interfaceC0595j, R r8, DataSource dataSource) {
        boolean zMo7171d;
        boolean zM4026t = m4026t();
        this.f3935v = Status.COMPLETE;
        this.f3932s = interfaceC0595j;
        if (this.f3921h.m24410f() <= 3) {
            Log.d("Glide", "Finished loading " + r8.getClass().getSimpleName() + " from " + dataSource + " for " + this.f3922i + " with size [" + this.f3939z + "x" + this.f3914A + "] in " + C6512e.m24922a(this.f3934u) + " ms");
        }
        boolean z8 = true;
        this.f3915b = true;
        try {
            List<InterfaceC6249f<R>> list = this.f3929p;
            if (list != null) {
                Iterator<InterfaceC6249f<R>> it = list.iterator();
                zMo7171d = false;
                while (it.hasNext()) {
                    zMo7171d |= it.next().mo7171d(r8, this.f3922i, this.f3928o, dataSource, zM4026t);
                }
            } else {
                zMo7171d = false;
            }
            InterfaceC6249f<R> interfaceC6249f = this.f3918e;
            if (interfaceC6249f == null || !interfaceC6249f.mo7171d(r8, this.f3922i, this.f3928o, dataSource, zM4026t)) {
                z8 = false;
            }
            if (!(z8 | zMo7171d)) {
                this.f3928o.mo7183f(r8, this.f3931r.mo24446a(dataSource, zM4026t));
            }
            this.f3915b = false;
            m4030z();
        } catch (Throwable th) {
            this.f3915b = false;
            throw th;
        }
    }

    /* renamed from: D */
    public final void m4006D(InterfaceC0595j<?> interfaceC0595j) {
        this.f3930q.m3938k(interfaceC0595j);
        this.f3932s = null;
    }

    /* renamed from: E */
    public final void m4007E() {
        if (m4019m()) {
            Drawable drawableM4023q = this.f3922i == null ? m4023q() : null;
            if (drawableM4023q == null) {
                drawableM4023q = m4022p();
            }
            if (drawableM4023q == null) {
                drawableM4023q = m4024r();
            }
            this.f3928o.mo7182e(drawableM4023q);
        }
    }

    @Override // p190s1.InterfaceC6251h
    /* renamed from: a */
    public void mo4008a(GlideException glideException) {
        m4004B(glideException, 5);
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: b */
    public void mo4009b() {
        m4017k();
        this.f3920g = null;
        this.f3921h = null;
        this.f3922i = null;
        this.f3923j = null;
        this.f3924k = null;
        this.f3925l = -1;
        this.f3926m = -1;
        this.f3928o = null;
        this.f3929p = null;
        this.f3918e = null;
        this.f3919f = null;
        this.f3931r = null;
        this.f3933t = null;
        this.f3936w = null;
        this.f3937x = null;
        this.f3938y = null;
        this.f3939z = -1;
        this.f3914A = -1;
        f3912B.mo3464a(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p190s1.InterfaceC6251h
    /* renamed from: c */
    public void mo4010c(InterfaceC0595j<?> interfaceC0595j, DataSource dataSource) {
        this.f3917d.mo25140c();
        this.f3933t = null;
        if (interfaceC0595j == null) {
            mo4008a(new GlideException("Expected to receive a Resource<R> with an object of " + this.f3923j + " inside, but instead got null."));
            return;
        }
        Object obj = interfaceC0595j.get();
        if (obj != null && this.f3923j.isAssignableFrom(obj.getClass())) {
            if (m4020n()) {
                m4005C(interfaceC0595j, obj, dataSource);
                return;
            } else {
                m4006D(interfaceC0595j);
                this.f3935v = Status.COMPLETE;
                return;
            }
        }
        m4006D(interfaceC0595j);
        StringBuilder sb = new StringBuilder();
        sb.append("Expected to receive an object of ");
        sb.append(this.f3923j);
        sb.append(" but instead got ");
        sb.append(obj != null ? obj.getClass() : "");
        sb.append("{");
        sb.append(obj);
        sb.append("} inside Resource{");
        sb.append(interfaceC0595j);
        sb.append("}.");
        sb.append(obj == null ? " To indicate failure return a null Resource object, rather than a Resource object containing null data." : "");
        mo4008a(new GlideException(sb.toString()));
    }

    @Override // p190s1.InterfaceC6246c
    public void clear() {
        C6517j.m24941b();
        m4017k();
        this.f3917d.mo25140c();
        Status status = this.f3935v;
        Status status2 = Status.CLEARED;
        if (status == status2) {
            return;
        }
        m4021o();
        InterfaceC0595j<R> interfaceC0595j = this.f3932s;
        if (interfaceC0595j != null) {
            m4006D(interfaceC0595j);
        }
        if (m4018l()) {
            this.f3928o.mo23902i(m4024r());
        }
        this.f3935v = status2;
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: d */
    public boolean mo4011d() {
        return isComplete();
    }

    @Override // p199t1.InterfaceC6282g
    /* renamed from: e */
    public void mo4012e(int i9, int i10) {
        this.f3917d.mo25140c();
        boolean z8 = f3913C;
        if (z8) {
            m4028w("Got onSizeReady in " + C6512e.m24922a(this.f3934u));
        }
        if (this.f3935v != Status.WAITING_FOR_SIZE) {
            return;
        }
        Status status = Status.RUNNING;
        this.f3935v = status;
        float fM23912A = this.f3924k.m23912A();
        this.f3939z = m4003x(i9, fM23912A);
        this.f3914A = m4003x(i10, fM23912A);
        if (z8) {
            m4028w("finished setup for calling load in " + C6512e.m24922a(this.f3934u));
        }
        this.f3933t = this.f3930q.m3935g(this.f3921h, this.f3922i, this.f3924k.m23949z(), this.f3939z, this.f3914A, this.f3924k.m23948y(), this.f3923j, this.f3927n, this.f3924k.m23936m(), this.f3924k.m23914C(), this.f3924k.m23922L(), this.f3924k.m23919H(), this.f3924k.m23942s(), this.f3924k.m23917F(), this.f3924k.m23916E(), this.f3924k.m23915D(), this.f3924k.m23941r(), this);
        if (this.f3935v != status) {
            this.f3933t = null;
        }
        if (z8) {
            m4028w("finished onSizeReady in " + C6512e.m24922a(this.f3934u));
        }
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: f */
    public boolean mo4013f(InterfaceC6246c interfaceC6246c) {
        if (!(interfaceC6246c instanceof SingleRequest)) {
            return false;
        }
        SingleRequest singleRequest = (SingleRequest) interfaceC6246c;
        return this.f3925l == singleRequest.f3925l && this.f3926m == singleRequest.f3926m && C6517j.m24942c(this.f3922i, singleRequest.f3922i) && this.f3923j.equals(singleRequest.f3923j) && this.f3924k.equals(singleRequest.f3924k) && this.f3927n == singleRequest.f3927n && m4002u(this, singleRequest);
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: g */
    public boolean mo4014g() {
        return this.f3935v == Status.FAILED;
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: h */
    public boolean mo4015h() {
        return this.f3935v == Status.CLEARED;
    }

    @Override // p190s1.InterfaceC6246c
    /* renamed from: i */
    public void mo4016i() {
        m4017k();
        this.f3917d.mo25140c();
        this.f3934u = C6512e.m24923b();
        if (this.f3922i == null) {
            if (C6517j.m24959t(this.f3925l, this.f3926m)) {
                this.f3939z = this.f3925l;
                this.f3914A = this.f3926m;
            }
            m4004B(new GlideException("Received null model"), m4023q() == null ? 5 : 3);
            return;
        }
        Status status = this.f3935v;
        Status status2 = Status.RUNNING;
        if (status == status2) {
            throw new IllegalArgumentException("Cannot restart a running request");
        }
        if (status == Status.COMPLETE) {
            mo4010c(this.f3932s, DataSource.MEMORY_CACHE);
            return;
        }
        Status status3 = Status.WAITING_FOR_SIZE;
        this.f3935v = status3;
        if (C6517j.m24959t(this.f3925l, this.f3926m)) {
            mo4012e(this.f3925l, this.f3926m);
        } else {
            this.f3928o.mo23903j(this);
        }
        Status status4 = this.f3935v;
        if ((status4 == status2 || status4 == status3) && m4019m()) {
            this.f3928o.mo23900g(m4024r());
        }
        if (f3913C) {
            m4028w("finished run method in " + C6512e.m24922a(this.f3934u));
        }
    }

    @Override // p190s1.InterfaceC6246c
    public boolean isComplete() {
        return this.f3935v == Status.COMPLETE;
    }

    @Override // p190s1.InterfaceC6246c
    public boolean isRunning() {
        Status status = this.f3935v;
        return status == Status.RUNNING || status == Status.WAITING_FOR_SIZE;
    }

    @Override // p235x1.C6563a.f
    /* renamed from: j */
    public AbstractC6565c mo3286j() {
        return this.f3917d;
    }

    /* renamed from: k */
    public final void m4017k() {
        if (this.f3915b) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    /* renamed from: l */
    public final boolean m4018l() {
        InterfaceC6247d interfaceC6247d = this.f3919f;
        return interfaceC6247d == null || interfaceC6247d.mo23891l(this);
    }

    /* renamed from: m */
    public final boolean m4019m() {
        InterfaceC6247d interfaceC6247d = this.f3919f;
        return interfaceC6247d == null || interfaceC6247d.mo23886a(this);
    }

    /* renamed from: n */
    public final boolean m4020n() {
        InterfaceC6247d interfaceC6247d = this.f3919f;
        return interfaceC6247d == null || interfaceC6247d.mo23890k(this);
    }

    /* renamed from: o */
    public final void m4021o() {
        m4017k();
        this.f3917d.mo25140c();
        this.f3928o.mo23899b(this);
        C0837f.d dVar = this.f3933t;
        if (dVar != null) {
            dVar.m3943a();
            this.f3933t = null;
        }
    }

    /* renamed from: p */
    public final Drawable m4022p() {
        if (this.f3936w == null) {
            Drawable drawableM23938o = this.f3924k.m23938o();
            this.f3936w = drawableM23938o;
            if (drawableM23938o == null && this.f3924k.m23937n() > 0) {
                this.f3936w = m4027v(this.f3924k.m23937n());
            }
        }
        return this.f3936w;
    }

    /* renamed from: q */
    public final Drawable m4023q() {
        if (this.f3938y == null) {
            Drawable drawableM23939p = this.f3924k.m23939p();
            this.f3938y = drawableM23939p;
            if (drawableM23939p == null && this.f3924k.m23940q() > 0) {
                this.f3938y = m4027v(this.f3924k.m23940q());
            }
        }
        return this.f3938y;
    }

    /* renamed from: r */
    public final Drawable m4024r() {
        if (this.f3937x == null) {
            Drawable drawableM23945v = this.f3924k.m23945v();
            this.f3937x = drawableM23945v;
            if (drawableM23945v == null && this.f3924k.m23946w() > 0) {
                this.f3937x = m4027v(this.f3924k.m23946w());
            }
        }
        return this.f3937x;
    }

    /* renamed from: s */
    public final void m4025s(Context context, C6357g c6357g, Object obj, Class<R> cls, C6250g c6250g, int i9, int i10, Priority priority, InterfaceC6283h<R> interfaceC6283h, InterfaceC6249f<R> interfaceC6249f, List<InterfaceC6249f<R>> list, InterfaceC6247d interfaceC6247d, C0837f c0837f, InterfaceC6363c<? super R> interfaceC6363c) {
        this.f3920g = context;
        this.f3921h = c6357g;
        this.f3922i = obj;
        this.f3923j = cls;
        this.f3924k = c6250g;
        this.f3925l = i9;
        this.f3926m = i10;
        this.f3927n = priority;
        this.f3928o = interfaceC6283h;
        this.f3918e = interfaceC6249f;
        this.f3929p = list;
        this.f3919f = interfaceC6247d;
        this.f3930q = c0837f;
        this.f3931r = interfaceC6363c;
        this.f3935v = Status.PENDING;
    }

    /* renamed from: t */
    public final boolean m4026t() {
        InterfaceC6247d interfaceC6247d = this.f3919f;
        return interfaceC6247d == null || !interfaceC6247d.mo23887c();
    }

    /* renamed from: v */
    public final Drawable m4027v(int i9) {
        return C5272a.m20533a(this.f3921h, i9, this.f3924k.m23913B() != null ? this.f3924k.m23913B() : this.f3920g.getTheme());
    }

    /* renamed from: w */
    public final void m4028w(String str) {
        Log.v("Request", str + " this: " + this.f3916c);
    }

    /* renamed from: y */
    public final void m4029y() {
        InterfaceC6247d interfaceC6247d = this.f3919f;
        if (interfaceC6247d != null) {
            interfaceC6247d.mo23888e(this);
        }
    }

    /* renamed from: z */
    public final void m4030z() {
        InterfaceC6247d interfaceC6247d = this.f3919f;
        if (interfaceC6247d != null) {
            interfaceC6247d.mo23889j(this);
        }
    }
}
