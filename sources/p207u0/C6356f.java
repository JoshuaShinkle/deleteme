package p207u0;

import android.content.Context;
import com.bumptech.glide.load.engine.C0837f;
import java.util.Map;
import p022c1.C0708e;
import p022c1.C0712i;
import p022c1.C0713j;
import p022c1.InterfaceC0705b;
import p022c1.InterfaceC0707d;
import p043d1.C4659g;
import p043d1.C4660h;
import p043d1.C4662j;
import p043d1.InterfaceC4653a;
import p043d1.InterfaceC4661i;
import p053e1.ExecutorServiceC4708a;
import p132m.C5302a;
import p163p1.C5875f;
import p163p1.C5881l;
import p163p1.InterfaceC5873d;
import p190s1.C6250g;

/* renamed from: u0.f */
/* loaded from: classes.dex */
public final class C6356f {

    /* renamed from: b */
    public C0837f f21438b;

    /* renamed from: c */
    public InterfaceC0707d f21439c;

    /* renamed from: d */
    public InterfaceC0705b f21440d;

    /* renamed from: e */
    public InterfaceC4661i f21441e;

    /* renamed from: f */
    public ExecutorServiceC4708a f21442f;

    /* renamed from: g */
    public ExecutorServiceC4708a f21443g;

    /* renamed from: h */
    public InterfaceC4653a.a f21444h;

    /* renamed from: i */
    public C4662j f21445i;

    /* renamed from: j */
    public InterfaceC5873d f21446j;

    /* renamed from: m */
    public C5881l.b f21449m;

    /* renamed from: n */
    public ExecutorServiceC4708a f21450n;

    /* renamed from: o */
    public boolean f21451o;

    /* renamed from: a */
    public final Map<Class<?>, AbstractC6360j<?, ?>> f21437a = new C5302a();

    /* renamed from: k */
    public int f21447k = 4;

    /* renamed from: l */
    public C6250g f21448l = new C6250g();

    /* renamed from: a */
    public ComponentCallbacks2C6355e m24401a(Context context) {
        if (this.f21442f == null) {
            this.f21442f = ExecutorServiceC4708a.m18848f();
        }
        if (this.f21443g == null) {
            this.f21443g = ExecutorServiceC4708a.m18846d();
        }
        if (this.f21450n == null) {
            this.f21450n = ExecutorServiceC4708a.m18844b();
        }
        if (this.f21445i == null) {
            this.f21445i = new C4662j.a(context).m18625a();
        }
        if (this.f21446j == null) {
            this.f21446j = new C5875f();
        }
        if (this.f21439c == null) {
            int iM18622b = this.f21445i.m18622b();
            if (iM18622b > 0) {
                this.f21439c = new C0713j(iM18622b);
            } else {
                this.f21439c = new C0708e();
            }
        }
        if (this.f21440d == null) {
            this.f21440d = new C0712i(this.f21445i.m18621a());
        }
        if (this.f21441e == null) {
            this.f21441e = new C4660h(this.f21445i.m18623d());
        }
        if (this.f21444h == null) {
            this.f21444h = new C4659g(context);
        }
        if (this.f21438b == null) {
            this.f21438b = new C0837f(this.f21441e, this.f21444h, this.f21443g, this.f21442f, ExecutorServiceC4708a.m18850h(), ExecutorServiceC4708a.m18844b(), this.f21451o);
        }
        return new ComponentCallbacks2C6355e(context, this.f21438b, this.f21441e, this.f21439c, this.f21440d, new C5881l(this.f21449m), this.f21446j, this.f21447k, this.f21448l.mo23507O(), this.f21437a);
    }

    /* renamed from: b */
    public C6356f m24402b(InterfaceC4653a.a aVar) {
        this.f21444h = aVar;
        return this;
    }

    /* renamed from: c */
    public C6356f m24403c(int i9) {
        if (i9 < 2 || i9 > 6) {
            throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
        }
        this.f21447k = i9;
        return this;
    }

    /* renamed from: d */
    public void m24404d(C5881l.b bVar) {
        this.f21449m = bVar;
    }
}
