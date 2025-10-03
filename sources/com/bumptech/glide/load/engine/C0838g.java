package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DecodeJob;
import java.util.ArrayList;
import java.util.List;
import p012b1.InterfaceC0589d;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p053e1.ExecutorServiceC4708a;
import p190s1.InterfaceC6251h;
import p226w1.C6517j;
import p235x1.AbstractC6565c;
import p235x1.C6563a;
import p243y0.InterfaceC6589b;

/* renamed from: com.bumptech.glide.load.engine.g */
/* loaded from: classes.dex */
public class C0838g<R> implements DecodeJob.InterfaceC0826b<R>, C6563a.f {

    /* renamed from: y */
    public static final a f3825y = new a();

    /* renamed from: z */
    public static final Handler f3826z = new Handler(Looper.getMainLooper(), new b());

    /* renamed from: b */
    public final List<InterfaceC6251h> f3827b;

    /* renamed from: c */
    public final AbstractC6565c f3828c;

    /* renamed from: d */
    public final InterfaceC0699e<C0838g<?>> f3829d;

    /* renamed from: e */
    public final a f3830e;

    /* renamed from: f */
    public final InterfaceC0589d f3831f;

    /* renamed from: g */
    public final ExecutorServiceC4708a f3832g;

    /* renamed from: h */
    public final ExecutorServiceC4708a f3833h;

    /* renamed from: i */
    public final ExecutorServiceC4708a f3834i;

    /* renamed from: j */
    public final ExecutorServiceC4708a f3835j;

    /* renamed from: k */
    public InterfaceC6589b f3836k;

    /* renamed from: l */
    public boolean f3837l;

    /* renamed from: m */
    public boolean f3838m;

    /* renamed from: n */
    public boolean f3839n;

    /* renamed from: o */
    public boolean f3840o;

    /* renamed from: p */
    public InterfaceC0595j<?> f3841p;

    /* renamed from: q */
    public DataSource f3842q;

    /* renamed from: r */
    public boolean f3843r;

    /* renamed from: s */
    public GlideException f3844s;

    /* renamed from: t */
    public boolean f3845t;

    /* renamed from: u */
    public List<InterfaceC6251h> f3846u;

    /* renamed from: v */
    public C0839h<?> f3847v;

    /* renamed from: w */
    public DecodeJob<R> f3848w;

    /* renamed from: x */
    public volatile boolean f3849x;

    /* renamed from: com.bumptech.glide.load.engine.g$a */
    public static class a {
        /* renamed from: a */
        public <R> C0839h<R> m3957a(InterfaceC0595j<R> interfaceC0595j, boolean z8) {
            return new C0839h<>(interfaceC0595j, z8, true);
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.g$b */
    public static class b implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            C0838g c0838g = (C0838g) message.obj;
            int i9 = message.what;
            if (i9 == 1) {
                c0838g.m3950k();
            } else if (i9 == 2) {
                c0838g.m3949i();
            } else {
                if (i9 != 3) {
                    throw new IllegalStateException("Unrecognized message: " + message.what);
                }
                c0838g.m3948h();
            }
            return true;
        }
    }

    public C0838g(ExecutorServiceC4708a executorServiceC4708a, ExecutorServiceC4708a executorServiceC4708a2, ExecutorServiceC4708a executorServiceC4708a3, ExecutorServiceC4708a executorServiceC4708a4, InterfaceC0589d interfaceC0589d, InterfaceC0699e<C0838g<?>> interfaceC0699e) {
        this(executorServiceC4708a, executorServiceC4708a2, executorServiceC4708a3, executorServiceC4708a4, interfaceC0589d, interfaceC0699e, f3825y);
    }

    @Override // com.bumptech.glide.load.engine.DecodeJob.InterfaceC0826b
    /* renamed from: a */
    public void mo3867a(GlideException glideException) {
        this.f3844s = glideException;
        f3826z.obtainMessage(2, this).sendToTarget();
    }

    /* renamed from: b */
    public void m3944b(InterfaceC6251h interfaceC6251h) {
        C6517j.m24941b();
        this.f3828c.mo25140c();
        if (this.f3843r) {
            interfaceC6251h.mo4010c(this.f3847v, this.f3842q);
        } else if (this.f3845t) {
            interfaceC6251h.mo4008a(this.f3844s);
        } else {
            this.f3827b.add(interfaceC6251h);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bumptech.glide.load.engine.DecodeJob.InterfaceC0826b
    /* renamed from: c */
    public void mo3868c(InterfaceC0595j<R> interfaceC0595j, DataSource dataSource) {
        this.f3841p = interfaceC0595j;
        this.f3842q = dataSource;
        f3826z.obtainMessage(1, this).sendToTarget();
    }

    @Override // com.bumptech.glide.load.engine.DecodeJob.InterfaceC0826b
    /* renamed from: d */
    public void mo3869d(DecodeJob<?> decodeJob) {
        m3947g().execute(decodeJob);
    }

    /* renamed from: e */
    public final void m3945e(InterfaceC6251h interfaceC6251h) {
        if (this.f3846u == null) {
            this.f3846u = new ArrayList(2);
        }
        if (this.f3846u.contains(interfaceC6251h)) {
            return;
        }
        this.f3846u.add(interfaceC6251h);
    }

    /* renamed from: f */
    public void m3946f() {
        if (this.f3845t || this.f3843r || this.f3849x) {
            return;
        }
        this.f3849x = true;
        this.f3848w.m3842a();
        this.f3831f.mo3272b(this, this.f3836k);
    }

    /* renamed from: g */
    public final ExecutorServiceC4708a m3947g() {
        return this.f3838m ? this.f3834i : this.f3839n ? this.f3835j : this.f3833h;
    }

    /* renamed from: h */
    public void m3948h() {
        this.f3828c.mo25140c();
        if (!this.f3849x) {
            throw new IllegalStateException("Not cancelled");
        }
        this.f3831f.mo3272b(this, this.f3836k);
        m3954o(false);
    }

    /* renamed from: i */
    public void m3949i() {
        this.f3828c.mo25140c();
        if (this.f3849x) {
            m3954o(false);
            return;
        }
        if (this.f3827b.isEmpty()) {
            throw new IllegalStateException("Received an exception without any callbacks to notify");
        }
        if (this.f3845t) {
            throw new IllegalStateException("Already failed once");
        }
        this.f3845t = true;
        this.f3831f.mo3271a(this, this.f3836k, null);
        for (InterfaceC6251h interfaceC6251h : this.f3827b) {
            if (!m3952m(interfaceC6251h)) {
                interfaceC6251h.mo4008a(this.f3844s);
            }
        }
        m3954o(false);
    }

    @Override // p235x1.C6563a.f
    /* renamed from: j */
    public AbstractC6565c mo3286j() {
        return this.f3828c;
    }

    /* renamed from: k */
    public void m3950k() {
        this.f3828c.mo25140c();
        if (this.f3849x) {
            this.f3841p.mo3281b();
            m3954o(false);
            return;
        }
        if (this.f3827b.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        }
        if (this.f3843r) {
            throw new IllegalStateException("Already have resource");
        }
        C0839h<?> c0839hM3957a = this.f3830e.m3957a(this.f3841p, this.f3837l);
        this.f3847v = c0839hM3957a;
        this.f3843r = true;
        c0839hM3957a.m3958a();
        this.f3831f.mo3271a(this, this.f3836k, this.f3847v);
        int size = this.f3827b.size();
        for (int i9 = 0; i9 < size; i9++) {
            InterfaceC6251h interfaceC6251h = this.f3827b.get(i9);
            if (!m3952m(interfaceC6251h)) {
                this.f3847v.m3958a();
                interfaceC6251h.mo4010c(this.f3847v, this.f3842q);
            }
        }
        this.f3847v.m3961g();
        m3954o(false);
    }

    /* renamed from: l */
    public C0838g<R> m3951l(InterfaceC6589b interfaceC6589b, boolean z8, boolean z9, boolean z10, boolean z11) {
        this.f3836k = interfaceC6589b;
        this.f3837l = z8;
        this.f3838m = z9;
        this.f3839n = z10;
        this.f3840o = z11;
        return this;
    }

    /* renamed from: m */
    public final boolean m3952m(InterfaceC6251h interfaceC6251h) {
        List<InterfaceC6251h> list = this.f3846u;
        return list != null && list.contains(interfaceC6251h);
    }

    /* renamed from: n */
    public boolean m3953n() {
        return this.f3840o;
    }

    /* renamed from: o */
    public final void m3954o(boolean z8) {
        C6517j.m24941b();
        this.f3827b.clear();
        this.f3836k = null;
        this.f3847v = null;
        this.f3841p = null;
        List<InterfaceC6251h> list = this.f3846u;
        if (list != null) {
            list.clear();
        }
        this.f3845t = false;
        this.f3849x = false;
        this.f3843r = false;
        this.f3848w.m3863w(z8);
        this.f3848w = null;
        this.f3844s = null;
        this.f3842q = null;
        this.f3829d.mo3464a(this);
    }

    /* renamed from: p */
    public void m3955p(InterfaceC6251h interfaceC6251h) {
        C6517j.m24941b();
        this.f3828c.mo25140c();
        if (this.f3843r || this.f3845t) {
            m3945e(interfaceC6251h);
            return;
        }
        this.f3827b.remove(interfaceC6251h);
        if (this.f3827b.isEmpty()) {
            m3946f();
        }
    }

    /* renamed from: q */
    public void m3956q(DecodeJob<R> decodeJob) {
        this.f3848w = decodeJob;
        (decodeJob.m3841C() ? this.f3832g : m3947g()).execute(decodeJob);
    }

    public C0838g(ExecutorServiceC4708a executorServiceC4708a, ExecutorServiceC4708a executorServiceC4708a2, ExecutorServiceC4708a executorServiceC4708a3, ExecutorServiceC4708a executorServiceC4708a4, InterfaceC0589d interfaceC0589d, InterfaceC0699e<C0838g<?>> interfaceC0699e, a aVar) {
        this.f3827b = new ArrayList(2);
        this.f3828c = AbstractC6565c.m25138a();
        this.f3832g = executorServiceC4708a;
        this.f3833h = executorServiceC4708a2;
        this.f3834i = executorServiceC4708a3;
        this.f3835j = executorServiceC4708a4;
        this.f3831f = interfaceC0589d;
        this.f3829d = interfaceC0699e;
        this.f3830e = aVar;
    }
}
