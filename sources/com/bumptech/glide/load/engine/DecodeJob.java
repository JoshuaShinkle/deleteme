package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.engine.C0836e;
import com.bumptech.glide.load.engine.InterfaceC0834c;
import com.bumptech.glide.load.resource.bitmap.C0849a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import p012b1.AbstractC0588c;
import p012b1.C0586a;
import p012b1.C0587b;
import p012b1.C0590e;
import p012b1.C0594i;
import p012b1.C0596k;
import p012b1.InterfaceC0592g;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p043d1.InterfaceC4653a;
import p207u0.C6357g;
import p226w1.C6512e;
import p235x1.AbstractC6565c;
import p235x1.C6563a;
import p235x1.C6564b;
import p243y0.C6591d;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6594g;
import p243y0.InterfaceC6595h;
import p252z0.InterfaceC6805d;
import p252z0.InterfaceC6806e;

/* loaded from: classes.dex */
public class DecodeJob<R> implements InterfaceC0834c.a, Runnable, Comparable<DecodeJob<?>>, C6563a.f {

    /* renamed from: A */
    public Object f3701A;

    /* renamed from: B */
    public DataSource f3702B;

    /* renamed from: C */
    public InterfaceC6805d<?> f3703C;

    /* renamed from: D */
    public volatile InterfaceC0834c f3704D;

    /* renamed from: E */
    public volatile boolean f3705E;

    /* renamed from: F */
    public volatile boolean f3706F;

    /* renamed from: e */
    public final InterfaceC0829e f3710e;

    /* renamed from: f */
    public final InterfaceC0699e<DecodeJob<?>> f3711f;

    /* renamed from: i */
    public C6357g f3714i;

    /* renamed from: j */
    public InterfaceC6589b f3715j;

    /* renamed from: k */
    public Priority f3716k;

    /* renamed from: l */
    public C0590e f3717l;

    /* renamed from: m */
    public int f3718m;

    /* renamed from: n */
    public int f3719n;

    /* renamed from: o */
    public AbstractC0588c f3720o;

    /* renamed from: p */
    public C6592e f3721p;

    /* renamed from: q */
    public InterfaceC0826b<R> f3722q;

    /* renamed from: r */
    public int f3723r;

    /* renamed from: s */
    public Stage f3724s;

    /* renamed from: t */
    public RunReason f3725t;

    /* renamed from: u */
    public long f3726u;

    /* renamed from: v */
    public boolean f3727v;

    /* renamed from: w */
    public Object f3728w;

    /* renamed from: x */
    public Thread f3729x;

    /* renamed from: y */
    public InterfaceC6589b f3730y;

    /* renamed from: z */
    public InterfaceC6589b f3731z;

    /* renamed from: b */
    public final C0835d<R> f3707b = new C0835d<>();

    /* renamed from: c */
    public final List<Throwable> f3708c = new ArrayList();

    /* renamed from: d */
    public final AbstractC6565c f3709d = AbstractC6565c.m25138a();

    /* renamed from: g */
    public final C0828d<?> f3712g = new C0828d<>();

    /* renamed from: h */
    public final C0830f f3713h = new C0830f();

    public enum RunReason {
        INITIALIZE,
        SWITCH_TO_SOURCE_SERVICE,
        DECODE_DATA
    }

    public enum Stage {
        INITIALIZE,
        RESOURCE_CACHE,
        DATA_CACHE,
        SOURCE,
        ENCODE,
        FINISHED
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$a */
    public static /* synthetic */ class C0825a {

        /* renamed from: a */
        public static final /* synthetic */ int[] f3743a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f3744b;

        /* renamed from: c */
        public static final /* synthetic */ int[] f3745c;

        static {
            int[] iArr = new int[EncodeStrategy.values().length];
            f3745c = iArr;
            try {
                iArr[EncodeStrategy.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3745c[EncodeStrategy.TRANSFORMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[Stage.values().length];
            f3744b = iArr2;
            try {
                iArr2[Stage.RESOURCE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3744b[Stage.DATA_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3744b[Stage.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3744b[Stage.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3744b[Stage.INITIALIZE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[RunReason.values().length];
            f3743a = iArr3;
            try {
                iArr3[RunReason.INITIALIZE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3743a[RunReason.SWITCH_TO_SOURCE_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3743a[RunReason.DECODE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$b */
    public interface InterfaceC0826b<R> {
        /* renamed from: a */
        void mo3867a(GlideException glideException);

        /* renamed from: c */
        void mo3868c(InterfaceC0595j<R> interfaceC0595j, DataSource dataSource);

        /* renamed from: d */
        void mo3869d(DecodeJob<?> decodeJob);
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$c */
    public final class C0827c<Z> implements C0836e.a<Z> {

        /* renamed from: a */
        public final DataSource f3746a;

        public C0827c(DataSource dataSource) {
            this.f3746a = dataSource;
        }

        @Override // com.bumptech.glide.load.engine.C0836e.a
        /* renamed from: a */
        public InterfaceC0595j<Z> mo3870a(InterfaceC0595j<Z> interfaceC0595j) {
            return DecodeJob.this.m3862v(this.f3746a, interfaceC0595j);
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$d */
    public static class C0828d<Z> {

        /* renamed from: a */
        public InterfaceC6589b f3748a;

        /* renamed from: b */
        public InterfaceC6594g<Z> f3749b;

        /* renamed from: c */
        public C0594i<Z> f3750c;

        /* renamed from: a */
        public void m3871a() {
            this.f3748a = null;
            this.f3749b = null;
            this.f3750c = null;
        }

        /* renamed from: b */
        public void m3872b(InterfaceC0829e interfaceC0829e, C6592e c6592e) {
            C6564b.m25134a("DecodeJob.encode");
            try {
                interfaceC0829e.mo3875a().mo18599a(this.f3748a, new C0587b(this.f3749b, this.f3750c, c6592e));
            } finally {
                this.f3750c.m3285g();
                C6564b.m25137d();
            }
        }

        /* renamed from: c */
        public boolean m3873c() {
            return this.f3750c != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: d */
        public <X> void m3874d(InterfaceC6589b interfaceC6589b, InterfaceC6594g<X> interfaceC6594g, C0594i<X> c0594i) {
            this.f3748a = interfaceC6589b;
            this.f3749b = interfaceC6594g;
            this.f3750c = c0594i;
        }
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$e */
    public interface InterfaceC0829e {
        /* renamed from: a */
        InterfaceC4653a mo3875a();
    }

    /* renamed from: com.bumptech.glide.load.engine.DecodeJob$f */
    public static class C0830f {

        /* renamed from: a */
        public boolean f3751a;

        /* renamed from: b */
        public boolean f3752b;

        /* renamed from: c */
        public boolean f3753c;

        /* renamed from: a */
        public final boolean m3876a(boolean z8) {
            return (this.f3753c || z8 || this.f3752b) && this.f3751a;
        }

        /* renamed from: b */
        public synchronized boolean m3877b() {
            this.f3752b = true;
            return m3876a(false);
        }

        /* renamed from: c */
        public synchronized boolean m3878c() {
            this.f3753c = true;
            return m3876a(false);
        }

        /* renamed from: d */
        public synchronized boolean m3879d(boolean z8) {
            this.f3751a = true;
            return m3876a(z8);
        }

        /* renamed from: e */
        public synchronized void m3880e() {
            this.f3752b = false;
            this.f3751a = false;
            this.f3753c = false;
        }
    }

    public DecodeJob(InterfaceC0829e interfaceC0829e, InterfaceC0699e<DecodeJob<?>> interfaceC0699e) {
        this.f3710e = interfaceC0829e;
        this.f3711f = interfaceC0699e;
    }

    /* renamed from: A */
    public final void m3839A() {
        int i9 = C0825a.f3743a[this.f3725t.ordinal()];
        if (i9 == 1) {
            this.f3724s = m3851k(Stage.INITIALIZE);
            this.f3704D = m3850i();
            m3865y();
        } else if (i9 == 2) {
            m3865y();
        } else {
            if (i9 == 3) {
                m3849h();
                return;
            }
            throw new IllegalStateException("Unrecognized run reason: " + this.f3725t);
        }
    }

    /* renamed from: B */
    public final void m3840B() {
        this.f3709d.mo25140c();
        if (this.f3705E) {
            throw new IllegalStateException("Already notified");
        }
        this.f3705E = true;
    }

    /* renamed from: C */
    public boolean m3841C() {
        Stage stageM3851k = m3851k(Stage.INITIALIZE);
        return stageM3851k == Stage.RESOURCE_CACHE || stageM3851k == Stage.DATA_CACHE;
    }

    /* renamed from: a */
    public void m3842a() {
        this.f3706F = true;
        InterfaceC0834c interfaceC0834c = this.f3704D;
        if (interfaceC0834c != null) {
            interfaceC0834c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c.a
    /* renamed from: b */
    public void mo3843b(InterfaceC6589b interfaceC6589b, Object obj, InterfaceC6805d<?> interfaceC6805d, DataSource dataSource, InterfaceC6589b interfaceC6589b2) {
        this.f3730y = interfaceC6589b;
        this.f3701A = obj;
        this.f3703C = interfaceC6805d;
        this.f3702B = dataSource;
        this.f3731z = interfaceC6589b2;
        if (Thread.currentThread() != this.f3729x) {
            this.f3725t = RunReason.DECODE_DATA;
            this.f3722q.mo3869d(this);
        } else {
            C6564b.m25134a("DecodeJob.decodeFromRetrievedData");
            try {
                m3849h();
            } finally {
                C6564b.m25137d();
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(DecodeJob<?> decodeJob) {
        int iM3853m = m3853m() - decodeJob.m3853m();
        return iM3853m == 0 ? this.f3723r - decodeJob.f3723r : iM3853m;
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c.a
    /* renamed from: d */
    public void mo3845d() {
        this.f3725t = RunReason.SWITCH_TO_SOURCE_SERVICE;
        this.f3722q.mo3869d(this);
    }

    @Override // com.bumptech.glide.load.engine.InterfaceC0834c.a
    /* renamed from: e */
    public void mo3846e(InterfaceC6589b interfaceC6589b, Exception exc, InterfaceC6805d<?> interfaceC6805d, DataSource dataSource) {
        interfaceC6805d.mo57b();
        GlideException glideException = new GlideException("Fetching data failed", exc);
        glideException.m3890j(interfaceC6589b, dataSource, interfaceC6805d.mo56a());
        this.f3708c.add(glideException);
        if (Thread.currentThread() == this.f3729x) {
            m3865y();
        } else {
            this.f3725t = RunReason.SWITCH_TO_SOURCE_SERVICE;
            this.f3722q.mo3869d(this);
        }
    }

    /* renamed from: f */
    public final <Data> InterfaceC0595j<R> m3847f(InterfaceC6805d<?> interfaceC6805d, Data data, DataSource dataSource) {
        if (data == null) {
            interfaceC6805d.mo57b();
            return null;
        }
        try {
            long jM24923b = C6512e.m24923b();
            InterfaceC0595j<R> interfaceC0595jM3848g = m3848g(data, dataSource);
            if (Log.isLoggable("DecodeJob", 2)) {
                m3855o("Decoded result " + interfaceC0595jM3848g, jM24923b);
            }
            return interfaceC0595jM3848g;
        } finally {
            interfaceC6805d.mo57b();
        }
    }

    /* renamed from: g */
    public final <Data> InterfaceC0595j<R> m3848g(Data data, DataSource dataSource) {
        return m3866z(data, dataSource, this.f3707b.m3911h(data.getClass()));
    }

    /* renamed from: h */
    public final void m3849h() {
        InterfaceC0595j<R> interfaceC0595jM3847f;
        if (Log.isLoggable("DecodeJob", 2)) {
            m3856p("Retrieved data", this.f3726u, "data: " + this.f3701A + ", cache key: " + this.f3730y + ", fetcher: " + this.f3703C);
        }
        try {
            interfaceC0595jM3847f = m3847f(this.f3703C, this.f3701A, this.f3702B);
        } catch (GlideException e9) {
            e9.m3889i(this.f3731z, this.f3702B);
            this.f3708c.add(e9);
            interfaceC0595jM3847f = null;
        }
        if (interfaceC0595jM3847f != null) {
            m3858r(interfaceC0595jM3847f, this.f3702B);
        } else {
            m3865y();
        }
    }

    /* renamed from: i */
    public final InterfaceC0834c m3850i() {
        int i9 = C0825a.f3744b[this.f3724s.ordinal()];
        if (i9 == 1) {
            return new C0841j(this.f3707b, this);
        }
        if (i9 == 2) {
            return new C0833b(this.f3707b, this);
        }
        if (i9 == 3) {
            return new C0842k(this.f3707b, this);
        }
        if (i9 == 4) {
            return null;
        }
        throw new IllegalStateException("Unrecognized stage: " + this.f3724s);
    }

    @Override // p235x1.C6563a.f
    /* renamed from: j */
    public AbstractC6565c mo3286j() {
        return this.f3709d;
    }

    /* renamed from: k */
    public final Stage m3851k(Stage stage) {
        int i9 = C0825a.f3744b[stage.ordinal()];
        if (i9 == 1) {
            return this.f3720o.mo3267a() ? Stage.DATA_CACHE : m3851k(Stage.DATA_CACHE);
        }
        if (i9 == 2) {
            return this.f3727v ? Stage.FINISHED : Stage.SOURCE;
        }
        if (i9 == 3 || i9 == 4) {
            return Stage.FINISHED;
        }
        if (i9 == 5) {
            return this.f3720o.mo3268b() ? Stage.RESOURCE_CACHE : m3851k(Stage.RESOURCE_CACHE);
        }
        throw new IllegalArgumentException("Unrecognized stage: " + stage);
    }

    /* renamed from: l */
    public final C6592e m3852l(DataSource dataSource) {
        C6592e c6592e = this.f3721p;
        boolean z8 = dataSource == DataSource.RESOURCE_DISK_CACHE || this.f3707b.m3925v();
        C6591d<Boolean> c6591d = C0849a.f3898i;
        Boolean bool = (Boolean) c6592e.m25209c(c6591d);
        if (bool != null && (!bool.booleanValue() || z8)) {
            return c6592e;
        }
        C6592e c6592e2 = new C6592e();
        c6592e2.m25210d(this.f3721p);
        c6592e2.m25211e(c6591d, Boolean.valueOf(z8));
        return c6592e2;
    }

    /* renamed from: m */
    public final int m3853m() {
        return this.f3716k.ordinal();
    }

    /* renamed from: n */
    public DecodeJob<R> m3854n(C6357g c6357g, Object obj, C0590e c0590e, InterfaceC6589b interfaceC6589b, int i9, int i10, Class<?> cls, Class<R> cls2, Priority priority, AbstractC0588c abstractC0588c, Map<Class<?>, InterfaceC6595h<?>> map, boolean z8, boolean z9, boolean z10, C6592e c6592e, InterfaceC0826b<R> interfaceC0826b, int i11) {
        this.f3707b.m3923t(c6357g, obj, interfaceC6589b, i9, i10, abstractC0588c, cls, cls2, priority, c6592e, map, z8, z9, this.f3710e);
        this.f3714i = c6357g;
        this.f3715j = interfaceC6589b;
        this.f3716k = priority;
        this.f3717l = c0590e;
        this.f3718m = i9;
        this.f3719n = i10;
        this.f3720o = abstractC0588c;
        this.f3727v = z10;
        this.f3721p = c6592e;
        this.f3722q = interfaceC0826b;
        this.f3723r = i11;
        this.f3725t = RunReason.INITIALIZE;
        this.f3728w = obj;
        return this;
    }

    /* renamed from: o */
    public final void m3855o(String str, long j9) {
        m3856p(str, j9, null);
    }

    /* renamed from: p */
    public final void m3856p(String str, long j9, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" in ");
        sb.append(C6512e.m24922a(j9));
        sb.append(", load key: ");
        sb.append(this.f3717l);
        if (str2 != null) {
            str3 = ", " + str2;
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(", thread: ");
        sb.append(Thread.currentThread().getName());
        Log.v("DecodeJob", sb.toString());
    }

    /* renamed from: q */
    public final void m3857q(InterfaceC0595j<R> interfaceC0595j, DataSource dataSource) {
        m3840B();
        this.f3722q.mo3868c(interfaceC0595j, dataSource);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: r */
    public final void m3858r(InterfaceC0595j<R> interfaceC0595j, DataSource dataSource) {
        C0594i c0594i;
        if (interfaceC0595j instanceof InterfaceC0592g) {
            ((InterfaceC0592g) interfaceC0595j).mo3274a();
        }
        if (this.f3712g.m3873c()) {
            interfaceC0595j = C0594i.m3279e(interfaceC0595j);
            c0594i = interfaceC0595j;
        } else {
            c0594i = 0;
        }
        m3857q(interfaceC0595j, dataSource);
        this.f3724s = Stage.ENCODE;
        try {
            if (this.f3712g.m3873c()) {
                this.f3712g.m3872b(this.f3710e, this.f3721p);
            }
            m3860t();
        } finally {
            if (c0594i != 0) {
                c0594i.m3285g();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        C6564b.m25135b("DecodeJob#run(model=%s)", this.f3728w);
        InterfaceC6805d<?> interfaceC6805d = this.f3703C;
        try {
        } catch (Throwable th) {
            try {
                if (Log.isLoggable("DecodeJob", 3)) {
                    Log.d("DecodeJob", "DecodeJob threw unexpectedly, isCancelled: " + this.f3706F + ", stage: " + this.f3724s, th);
                }
                if (this.f3724s != Stage.ENCODE) {
                    this.f3708c.add(th);
                    m3859s();
                }
                if (!this.f3706F) {
                    throw th;
                }
                if (interfaceC6805d != null) {
                }
            } finally {
                if (interfaceC6805d != null) {
                    interfaceC6805d.mo57b();
                }
                C6564b.m25137d();
            }
        }
        if (this.f3706F) {
            m3859s();
            return;
        }
        m3839A();
        if (interfaceC6805d != null) {
            interfaceC6805d.mo57b();
        }
        C6564b.m25137d();
    }

    /* renamed from: s */
    public final void m3859s() {
        m3840B();
        this.f3722q.mo3867a(new GlideException("Failed to load resource", new ArrayList(this.f3708c)));
        m3861u();
    }

    /* renamed from: t */
    public final void m3860t() {
        if (this.f3713h.m3877b()) {
            m3864x();
        }
    }

    /* renamed from: u */
    public final void m3861u() {
        if (this.f3713h.m3878c()) {
            m3864x();
        }
    }

    /* renamed from: v */
    public <Z> InterfaceC0595j<Z> m3862v(DataSource dataSource, InterfaceC0595j<Z> interfaceC0595j) {
        InterfaceC0595j<Z> interfaceC0595jMo19696b;
        InterfaceC6595h<Z> interfaceC6595h;
        EncodeStrategy encodeStrategyMo19854a;
        InterfaceC6589b c0586a;
        Class<?> cls = interfaceC0595j.get().getClass();
        InterfaceC6594g<Z> interfaceC6594gM3916m = null;
        if (dataSource != DataSource.RESOURCE_DISK_CACHE) {
            InterfaceC6595h<Z> interfaceC6595hM3920q = this.f3707b.m3920q(cls);
            interfaceC6595h = interfaceC6595hM3920q;
            interfaceC0595jMo19696b = interfaceC6595hM3920q.mo19696b(this.f3714i, interfaceC0595j, this.f3718m, this.f3719n);
        } else {
            interfaceC0595jMo19696b = interfaceC0595j;
            interfaceC6595h = null;
        }
        if (!interfaceC0595j.equals(interfaceC0595jMo19696b)) {
            interfaceC0595j.mo3281b();
        }
        if (this.f3707b.m3924u(interfaceC0595jMo19696b)) {
            interfaceC6594gM3916m = this.f3707b.m3916m(interfaceC0595jMo19696b);
            encodeStrategyMo19854a = interfaceC6594gM3916m.mo19854a(this.f3721p);
        } else {
            encodeStrategyMo19854a = EncodeStrategy.NONE;
        }
        InterfaceC6594g interfaceC6594g = interfaceC6594gM3916m;
        if (!this.f3720o.mo3270d(!this.f3707b.m3926w(this.f3730y), dataSource, encodeStrategyMo19854a)) {
            return interfaceC0595jMo19696b;
        }
        if (interfaceC6594g == null) {
            throw new Registry.NoResultEncoderAvailableException(interfaceC0595jMo19696b.get().getClass());
        }
        int i9 = C0825a.f3745c[encodeStrategyMo19854a.ordinal()];
        if (i9 == 1) {
            c0586a = new C0586a(this.f3730y, this.f3715j);
        } else {
            if (i9 != 2) {
                throw new IllegalArgumentException("Unknown strategy: " + encodeStrategyMo19854a);
            }
            c0586a = new C0596k(this.f3707b.m3905b(), this.f3730y, this.f3715j, this.f3718m, this.f3719n, interfaceC6595h, cls, this.f3721p);
        }
        C0594i c0594iM3279e = C0594i.m3279e(interfaceC0595jMo19696b);
        this.f3712g.m3874d(c0586a, interfaceC6594g, c0594iM3279e);
        return c0594iM3279e;
    }

    /* renamed from: w */
    public void m3863w(boolean z8) {
        if (this.f3713h.m3879d(z8)) {
            m3864x();
        }
    }

    /* renamed from: x */
    public final void m3864x() {
        this.f3713h.m3880e();
        this.f3712g.m3871a();
        this.f3707b.m3904a();
        this.f3705E = false;
        this.f3714i = null;
        this.f3715j = null;
        this.f3721p = null;
        this.f3716k = null;
        this.f3717l = null;
        this.f3722q = null;
        this.f3724s = null;
        this.f3704D = null;
        this.f3729x = null;
        this.f3730y = null;
        this.f3701A = null;
        this.f3702B = null;
        this.f3703C = null;
        this.f3726u = 0L;
        this.f3706F = false;
        this.f3728w = null;
        this.f3708c.clear();
        this.f3711f.mo3464a(this);
    }

    /* renamed from: y */
    public final void m3865y() {
        this.f3729x = Thread.currentThread();
        this.f3726u = C6512e.m24923b();
        boolean zMo3900a = false;
        while (!this.f3706F && this.f3704D != null && !(zMo3900a = this.f3704D.mo3900a())) {
            this.f3724s = m3851k(this.f3724s);
            this.f3704D = m3850i();
            if (this.f3724s == Stage.SOURCE) {
                mo3845d();
                return;
            }
        }
        if ((this.f3724s == Stage.FINISHED || this.f3706F) && !zMo3900a) {
            m3859s();
        }
    }

    /* renamed from: z */
    public final <Data, ResourceType> InterfaceC0595j<R> m3866z(Data data, DataSource dataSource, C0840i<Data, ResourceType, R> c0840i) {
        C6592e c6592eM3852l = m3852l(dataSource);
        InterfaceC6806e<Data> interfaceC6806eM3815l = this.f3714i.m24412h().m3815l(data);
        try {
            return c0840i.m3963a(interfaceC6806eM3815l, c6592eM3852l, this.f3718m, this.f3719n, new C0827c(dataSource));
        } finally {
            interfaceC6806eM3815l.mo19964b();
        }
    }
}
