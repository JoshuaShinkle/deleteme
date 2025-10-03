package p129l6;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import p007a6.C0040d;
import p007a6.C0042f;

/* renamed from: l6.a */
/* loaded from: classes.dex */
public abstract class AbstractC5294a {

    /* renamed from: a */
    public final String f17971a;

    /* renamed from: b */
    public final boolean f17972b;

    /* renamed from: c */
    public C5297d f17973c;

    /* renamed from: d */
    public long f17974d;

    public AbstractC5294a(String str, boolean z8) {
        C0042f.m158e(str, AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.f17971a = str;
        this.f17972b = z8;
        this.f17974d = -1L;
    }

    /* renamed from: a */
    public final boolean m20647a() {
        return this.f17972b;
    }

    /* renamed from: b */
    public final String m20648b() {
        return this.f17971a;
    }

    /* renamed from: c */
    public final long m20649c() {
        return this.f17974d;
    }

    /* renamed from: d */
    public final C5297d m20650d() {
        return this.f17973c;
    }

    /* renamed from: e */
    public final void m20651e(C5297d c5297d) {
        C0042f.m158e(c5297d, "queue");
        C5297d c5297d2 = this.f17973c;
        if (c5297d2 == c5297d) {
            return;
        }
        if (!(c5297d2 == null)) {
            throw new IllegalStateException("task is in multiple queues".toString());
        }
        this.f17973c = c5297d;
    }

    /* renamed from: f */
    public abstract long mo20652f();

    /* renamed from: g */
    public final void m20653g(long j9) {
        this.f17974d = j9;
    }

    public String toString() {
        return this.f17971a;
    }

    public /* synthetic */ AbstractC5294a(String str, boolean z8, int i9, C0040d c0040d) {
        this(str, (i9 & 2) != 0 ? true : z8);
    }
}
