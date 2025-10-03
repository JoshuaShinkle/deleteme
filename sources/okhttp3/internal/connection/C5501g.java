package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.C5485b0;
import p007a6.C0042f;

/* renamed from: okhttp3.internal.connection.g */
/* loaded from: classes.dex */
public final class C5501g {

    /* renamed from: a */
    public final Set<C5485b0> f18738a = new LinkedHashSet();

    /* renamed from: a */
    public final synchronized void m21379a(C5485b0 c5485b0) {
        C0042f.m158e(c5485b0, "route");
        this.f18738a.remove(c5485b0);
    }

    /* renamed from: b */
    public final synchronized void m21380b(C5485b0 c5485b0) {
        C0042f.m158e(c5485b0, "failedRoute");
        this.f18738a.add(c5485b0);
    }

    /* renamed from: c */
    public final synchronized boolean m21381c(C5485b0 c5485b0) {
        C0042f.m158e(c5485b0, "route");
        return this.f18738a.contains(c5485b0);
    }
}
