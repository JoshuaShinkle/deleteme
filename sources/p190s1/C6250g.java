package p190s1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.resource.bitmap.C0849a;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Map;
import p012b1.AbstractC0588c;
import p103j1.C5074g;
import p103j1.C5075h;
import p103j1.C5076i;
import p103j1.C5079l;
import p103j1.C5081n;
import p143n1.C5350c;
import p143n1.C5353f;
import p143n1.C5356i;
import p217v1.C6450b;
import p226w1.C6509b;
import p226w1.C6516i;
import p226w1.C6517j;
import p243y0.C6590c;
import p243y0.C6591d;
import p243y0.C6592e;
import p243y0.InterfaceC6589b;
import p243y0.InterfaceC6595h;

/* renamed from: s1.g */
/* loaded from: classes.dex */
public class C6250g implements Cloneable {

    /* renamed from: A */
    public boolean f21065A;

    /* renamed from: b */
    public int f21066b;

    /* renamed from: f */
    public Drawable f21070f;

    /* renamed from: g */
    public int f21071g;

    /* renamed from: h */
    public Drawable f21072h;

    /* renamed from: i */
    public int f21073i;

    /* renamed from: n */
    public boolean f21078n;

    /* renamed from: p */
    public Drawable f21080p;

    /* renamed from: q */
    public int f21081q;

    /* renamed from: u */
    public boolean f21085u;

    /* renamed from: v */
    public Resources.Theme f21086v;

    /* renamed from: w */
    public boolean f21087w;

    /* renamed from: x */
    public boolean f21088x;

    /* renamed from: y */
    public boolean f21089y;

    /* renamed from: c */
    public float f21067c = 1.0f;

    /* renamed from: d */
    public AbstractC0588c f21068d = AbstractC0588c.f3111e;

    /* renamed from: e */
    public Priority f21069e = Priority.NORMAL;

    /* renamed from: j */
    public boolean f21074j = true;

    /* renamed from: k */
    public int f21075k = -1;

    /* renamed from: l */
    public int f21076l = -1;

    /* renamed from: m */
    public InterfaceC6589b f21077m = C6450b.m24697c();

    /* renamed from: o */
    public boolean f21079o = true;

    /* renamed from: r */
    public C6592e f21082r = new C6592e();

    /* renamed from: s */
    public Map<Class<?>, InterfaceC6595h<?>> f21083s = new C6509b();

    /* renamed from: t */
    public Class<?> f21084t = Object.class;

    /* renamed from: z */
    public boolean f21090z = true;

    /* renamed from: J */
    public static boolean m23908J(int i9, int i10) {
        return (i9 & i10) != 0;
    }

    /* renamed from: e */
    public static C6250g m23909e(Class<?> cls) {
        return new C6250g().mo23519d(cls);
    }

    /* renamed from: e0 */
    public static C6250g m23910e0(InterfaceC6589b interfaceC6589b) {
        return new C6250g().mo23520d0(interfaceC6589b);
    }

    /* renamed from: i */
    public static C6250g m23911i(AbstractC0588c abstractC0588c) {
        return new C6250g().mo23523h(abstractC0588c);
    }

    /* renamed from: A */
    public final float m23912A() {
        return this.f21067c;
    }

    /* renamed from: B */
    public final Resources.Theme m23913B() {
        return this.f21086v;
    }

    /* renamed from: C */
    public final Map<Class<?>, InterfaceC6595h<?>> m23914C() {
        return this.f21083s;
    }

    /* renamed from: D */
    public final boolean m23915D() {
        return this.f21065A;
    }

    /* renamed from: E */
    public final boolean m23916E() {
        return this.f21088x;
    }

    /* renamed from: F */
    public final boolean m23917F() {
        return this.f21074j;
    }

    /* renamed from: G */
    public final boolean m23918G() {
        return m23920I(8);
    }

    /* renamed from: H */
    public boolean m23919H() {
        return this.f21090z;
    }

    /* renamed from: I */
    public final boolean m23920I(int i9) {
        return m23908J(this.f21066b, i9);
    }

    /* renamed from: K */
    public final boolean m23921K() {
        return this.f21079o;
    }

    /* renamed from: L */
    public final boolean m23922L() {
        return this.f21078n;
    }

    /* renamed from: M */
    public final boolean m23923M() {
        return m23920I(2048);
    }

    /* renamed from: N */
    public final boolean m23924N() {
        return C6517j.m24959t(this.f21076l, this.f21075k);
    }

    /* renamed from: O */
    public C6250g mo23507O() {
        this.f21085u = true;
        return this;
    }

    /* renamed from: P */
    public C6250g mo23508P(boolean z8) {
        if (this.f21087w) {
            return clone().mo23508P(z8);
        }
        this.f21089y = z8;
        this.f21066b |= 524288;
        return m23930b0();
    }

    /* renamed from: Q */
    public C6250g mo23509Q() {
        return m23927V(DownsampleStrategy.f3879b, new C5074g());
    }

    /* renamed from: R */
    public C6250g mo23510R() {
        return m23926U(DownsampleStrategy.f3882e, new C5075h());
    }

    /* renamed from: S */
    public C6250g m23925S() {
        return m23927V(DownsampleStrategy.f3879b, new C5076i());
    }

    /* renamed from: T */
    public C6250g mo23511T() {
        return m23926U(DownsampleStrategy.f3878a, new C5081n());
    }

    /* renamed from: U */
    public final C6250g m23926U(DownsampleStrategy downsampleStrategy, InterfaceC6595h<Bitmap> interfaceC6595h) {
        return m23929a0(downsampleStrategy, interfaceC6595h, false);
    }

    /* renamed from: V */
    public final C6250g m23927V(DownsampleStrategy downsampleStrategy, InterfaceC6595h<Bitmap> interfaceC6595h) {
        if (this.f21087w) {
            return clone().m23927V(downsampleStrategy, interfaceC6595h);
        }
        mo23524j(downsampleStrategy);
        return m23934k0(interfaceC6595h, false);
    }

    /* renamed from: W */
    public C6250g m23928W(int i9) {
        return mo23512X(i9, i9);
    }

    /* renamed from: X */
    public C6250g mo23512X(int i9, int i10) {
        if (this.f21087w) {
            return clone().mo23512X(i9, i10);
        }
        this.f21076l = i9;
        this.f21075k = i10;
        this.f21066b |= 512;
        return m23930b0();
    }

    /* renamed from: Y */
    public C6250g mo23513Y(int i9) {
        if (this.f21087w) {
            return clone().mo23513Y(i9);
        }
        this.f21073i = i9;
        int i10 = this.f21066b | 128;
        this.f21072h = null;
        this.f21066b = i10 & (-65);
        return m23930b0();
    }

    /* renamed from: Z */
    public C6250g mo23514Z(Priority priority) {
        if (this.f21087w) {
            return clone().mo23514Z(priority);
        }
        this.f21069e = (Priority) C6516i.m24938d(priority);
        this.f21066b |= 8;
        return m23930b0();
    }

    /* renamed from: a */
    public C6250g mo23515a(C6250g c6250g) {
        if (this.f21087w) {
            return clone().mo23515a(c6250g);
        }
        if (m23908J(c6250g.f21066b, 2)) {
            this.f21067c = c6250g.f21067c;
        }
        if (m23908J(c6250g.f21066b, 262144)) {
            this.f21088x = c6250g.f21088x;
        }
        if (m23908J(c6250g.f21066b, ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES)) {
            this.f21065A = c6250g.f21065A;
        }
        if (m23908J(c6250g.f21066b, 4)) {
            this.f21068d = c6250g.f21068d;
        }
        if (m23908J(c6250g.f21066b, 8)) {
            this.f21069e = c6250g.f21069e;
        }
        if (m23908J(c6250g.f21066b, 16)) {
            this.f21070f = c6250g.f21070f;
            this.f21071g = 0;
            this.f21066b &= -33;
        }
        if (m23908J(c6250g.f21066b, 32)) {
            this.f21071g = c6250g.f21071g;
            this.f21070f = null;
            this.f21066b &= -17;
        }
        if (m23908J(c6250g.f21066b, 64)) {
            this.f21072h = c6250g.f21072h;
            this.f21073i = 0;
            this.f21066b &= -129;
        }
        if (m23908J(c6250g.f21066b, 128)) {
            this.f21073i = c6250g.f21073i;
            this.f21072h = null;
            this.f21066b &= -65;
        }
        if (m23908J(c6250g.f21066b, 256)) {
            this.f21074j = c6250g.f21074j;
        }
        if (m23908J(c6250g.f21066b, 512)) {
            this.f21076l = c6250g.f21076l;
            this.f21075k = c6250g.f21075k;
        }
        if (m23908J(c6250g.f21066b, UserMetadata.MAX_ATTRIBUTE_SIZE)) {
            this.f21077m = c6250g.f21077m;
        }
        if (m23908J(c6250g.f21066b, 4096)) {
            this.f21084t = c6250g.f21084t;
        }
        if (m23908J(c6250g.f21066b, UserMetadata.MAX_INTERNAL_KEY_SIZE)) {
            this.f21080p = c6250g.f21080p;
            this.f21081q = 0;
            this.f21066b &= -16385;
        }
        if (m23908J(c6250g.f21066b, 16384)) {
            this.f21081q = c6250g.f21081q;
            this.f21080p = null;
            this.f21066b &= -8193;
        }
        if (m23908J(c6250g.f21066b, 32768)) {
            this.f21086v = c6250g.f21086v;
        }
        if (m23908J(c6250g.f21066b, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE)) {
            this.f21079o = c6250g.f21079o;
        }
        if (m23908J(c6250g.f21066b, 131072)) {
            this.f21078n = c6250g.f21078n;
        }
        if (m23908J(c6250g.f21066b, 2048)) {
            this.f21083s.putAll(c6250g.f21083s);
            this.f21090z = c6250g.f21090z;
        }
        if (m23908J(c6250g.f21066b, 524288)) {
            this.f21089y = c6250g.f21089y;
        }
        if (!this.f21079o) {
            this.f21083s.clear();
            int i9 = this.f21066b & (-2049);
            this.f21078n = false;
            this.f21066b = i9 & (-131073);
            this.f21090z = true;
        }
        this.f21066b |= c6250g.f21066b;
        this.f21082r.m25210d(c6250g.f21082r);
        return m23930b0();
    }

    /* renamed from: a0 */
    public final C6250g m23929a0(DownsampleStrategy downsampleStrategy, InterfaceC6595h<Bitmap> interfaceC6595h, boolean z8) {
        C6250g c6250gM23932h0 = z8 ? m23932h0(downsampleStrategy, interfaceC6595h) : m23927V(downsampleStrategy, interfaceC6595h);
        c6250gM23932h0.f21090z = true;
        return c6250gM23932h0;
    }

    /* renamed from: b */
    public C6250g mo23516b() {
        if (this.f21085u && !this.f21087w) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.f21087w = true;
        return mo23507O();
    }

    /* renamed from: b0 */
    public final C6250g m23930b0() {
        if (this.f21085u) {
            throw new IllegalStateException("You cannot modify locked RequestOptions, consider clone()");
        }
        return this;
    }

    @Override // 
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public C6250g clone() {
        try {
            C6250g c6250g = (C6250g) super.clone();
            C6592e c6592e = new C6592e();
            c6250g.f21082r = c6592e;
            c6592e.m25210d(this.f21082r);
            C6509b c6509b = new C6509b();
            c6250g.f21083s = c6509b;
            c6509b.putAll(this.f21083s);
            c6250g.f21085u = false;
            c6250g.f21087w = false;
            return c6250g;
        } catch (CloneNotSupportedException e9) {
            throw new RuntimeException(e9);
        }
    }

    /* renamed from: c0 */
    public <T> C6250g mo23518c0(C6591d<T> c6591d, T t8) {
        if (this.f21087w) {
            return clone().mo23518c0(c6591d, t8);
        }
        C6516i.m24938d(c6591d);
        C6516i.m24938d(t8);
        this.f21082r.m25211e(c6591d, t8);
        return m23930b0();
    }

    /* renamed from: d */
    public C6250g mo23519d(Class<?> cls) {
        if (this.f21087w) {
            return clone().mo23519d(cls);
        }
        this.f21084t = (Class) C6516i.m24938d(cls);
        this.f21066b |= 4096;
        return m23930b0();
    }

    /* renamed from: d0 */
    public C6250g mo23520d0(InterfaceC6589b interfaceC6589b) {
        if (this.f21087w) {
            return clone().mo23520d0(interfaceC6589b);
        }
        this.f21077m = (InterfaceC6589b) C6516i.m24938d(interfaceC6589b);
        this.f21066b |= UserMetadata.MAX_ATTRIBUTE_SIZE;
        return m23930b0();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C6250g)) {
            return false;
        }
        C6250g c6250g = (C6250g) obj;
        return Float.compare(c6250g.f21067c, this.f21067c) == 0 && this.f21071g == c6250g.f21071g && C6517j.m24943d(this.f21070f, c6250g.f21070f) && this.f21073i == c6250g.f21073i && C6517j.m24943d(this.f21072h, c6250g.f21072h) && this.f21081q == c6250g.f21081q && C6517j.m24943d(this.f21080p, c6250g.f21080p) && this.f21074j == c6250g.f21074j && this.f21075k == c6250g.f21075k && this.f21076l == c6250g.f21076l && this.f21078n == c6250g.f21078n && this.f21079o == c6250g.f21079o && this.f21088x == c6250g.f21088x && this.f21089y == c6250g.f21089y && this.f21068d.equals(c6250g.f21068d) && this.f21069e == c6250g.f21069e && this.f21082r.equals(c6250g.f21082r) && this.f21083s.equals(c6250g.f21083s) && this.f21084t.equals(c6250g.f21084t) && C6517j.m24943d(this.f21077m, c6250g.f21077m) && C6517j.m24943d(this.f21086v, c6250g.f21086v);
    }

    /* renamed from: f0 */
    public C6250g mo23521f0(float f9) {
        if (this.f21087w) {
            return clone().mo23521f0(f9);
        }
        if (f9 < BitmapDescriptorFactory.HUE_RED || f9 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.f21067c = f9;
        this.f21066b |= 2;
        return m23930b0();
    }

    /* renamed from: g */
    public C6250g m23931g() {
        return mo23518c0(C0849a.f3898i, Boolean.FALSE);
    }

    /* renamed from: g0 */
    public C6250g mo23522g0(boolean z8) {
        if (this.f21087w) {
            return clone().mo23522g0(true);
        }
        this.f21074j = !z8;
        this.f21066b |= 256;
        return m23930b0();
    }

    /* renamed from: h */
    public C6250g mo23523h(AbstractC0588c abstractC0588c) {
        if (this.f21087w) {
            return clone().mo23523h(abstractC0588c);
        }
        this.f21068d = (AbstractC0588c) C6516i.m24938d(abstractC0588c);
        this.f21066b |= 4;
        return m23930b0();
    }

    /* renamed from: h0 */
    public final C6250g m23932h0(DownsampleStrategy downsampleStrategy, InterfaceC6595h<Bitmap> interfaceC6595h) {
        if (this.f21087w) {
            return clone().m23932h0(downsampleStrategy, interfaceC6595h);
        }
        mo23524j(downsampleStrategy);
        return mo23525j0(interfaceC6595h);
    }

    public int hashCode() {
        return C6517j.m24954o(this.f21086v, C6517j.m24954o(this.f21077m, C6517j.m24954o(this.f21084t, C6517j.m24954o(this.f21083s, C6517j.m24954o(this.f21082r, C6517j.m24954o(this.f21069e, C6517j.m24954o(this.f21068d, C6517j.m24955p(this.f21089y, C6517j.m24955p(this.f21088x, C6517j.m24955p(this.f21079o, C6517j.m24955p(this.f21078n, C6517j.m24953n(this.f21076l, C6517j.m24953n(this.f21075k, C6517j.m24955p(this.f21074j, C6517j.m24954o(this.f21080p, C6517j.m24953n(this.f21081q, C6517j.m24954o(this.f21072h, C6517j.m24953n(this.f21073i, C6517j.m24954o(this.f21070f, C6517j.m24953n(this.f21071g, C6517j.m24950k(this.f21067c)))))))))))))))))))));
    }

    /* renamed from: i0 */
    public final <T> C6250g m23933i0(Class<T> cls, InterfaceC6595h<T> interfaceC6595h, boolean z8) {
        if (this.f21087w) {
            return clone().m23933i0(cls, interfaceC6595h, z8);
        }
        C6516i.m24938d(cls);
        C6516i.m24938d(interfaceC6595h);
        this.f21083s.put(cls, interfaceC6595h);
        int i9 = this.f21066b | 2048;
        this.f21079o = true;
        int i10 = i9 | C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;
        this.f21066b = i10;
        this.f21090z = false;
        if (z8) {
            this.f21066b = i10 | 131072;
            this.f21078n = true;
        }
        return m23930b0();
    }

    /* renamed from: j */
    public C6250g mo23524j(DownsampleStrategy downsampleStrategy) {
        return mo23518c0(DownsampleStrategy.f3885h, C6516i.m24938d(downsampleStrategy));
    }

    /* renamed from: j0 */
    public C6250g mo23525j0(InterfaceC6595h<Bitmap> interfaceC6595h) {
        return m23934k0(interfaceC6595h, true);
    }

    /* renamed from: k */
    public C6250g mo23526k(int i9) {
        if (this.f21087w) {
            return clone().mo23526k(i9);
        }
        this.f21071g = i9;
        int i10 = this.f21066b | 32;
        this.f21070f = null;
        this.f21066b = i10 & (-17);
        return m23930b0();
    }

    /* renamed from: k0 */
    public final C6250g m23934k0(InterfaceC6595h<Bitmap> interfaceC6595h, boolean z8) {
        if (this.f21087w) {
            return clone().m23934k0(interfaceC6595h, z8);
        }
        C5079l c5079l = new C5079l(interfaceC6595h, z8);
        m23933i0(Bitmap.class, interfaceC6595h, z8);
        m23933i0(Drawable.class, c5079l, z8);
        m23933i0(BitmapDrawable.class, c5079l.m19882c(), z8);
        m23933i0(C5350c.class, new C5353f(interfaceC6595h), z8);
        return m23930b0();
    }

    /* renamed from: l */
    public C6250g m23935l(DecodeFormat decodeFormat) {
        C6516i.m24938d(decodeFormat);
        return mo23518c0(C0849a.f3895f, decodeFormat).mo23518c0(C5356i.f18240a, decodeFormat);
    }

    /* renamed from: l0 */
    public C6250g mo23527l0(InterfaceC6595h<Bitmap>... interfaceC6595hArr) {
        return m23934k0(new C6590c(interfaceC6595hArr), true);
    }

    /* renamed from: m */
    public final AbstractC0588c m23936m() {
        return this.f21068d;
    }

    /* renamed from: m0 */
    public C6250g mo23528m0(boolean z8) {
        if (this.f21087w) {
            return clone().mo23528m0(z8);
        }
        this.f21065A = z8;
        this.f21066b |= ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES;
        return m23930b0();
    }

    /* renamed from: n */
    public final int m23937n() {
        return this.f21071g;
    }

    /* renamed from: o */
    public final Drawable m23938o() {
        return this.f21070f;
    }

    /* renamed from: p */
    public final Drawable m23939p() {
        return this.f21080p;
    }

    /* renamed from: q */
    public final int m23940q() {
        return this.f21081q;
    }

    /* renamed from: r */
    public final boolean m23941r() {
        return this.f21089y;
    }

    /* renamed from: s */
    public final C6592e m23942s() {
        return this.f21082r;
    }

    /* renamed from: t */
    public final int m23943t() {
        return this.f21075k;
    }

    /* renamed from: u */
    public final int m23944u() {
        return this.f21076l;
    }

    /* renamed from: v */
    public final Drawable m23945v() {
        return this.f21072h;
    }

    /* renamed from: w */
    public final int m23946w() {
        return this.f21073i;
    }

    /* renamed from: x */
    public final Priority m23947x() {
        return this.f21069e;
    }

    /* renamed from: y */
    public final Class<?> m23948y() {
        return this.f21084t;
    }

    /* renamed from: z */
    public final InterfaceC6589b m23949z() {
        return this.f21077m;
    }
}
