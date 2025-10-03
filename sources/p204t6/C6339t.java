package p204t6;

import com.google.android.exoplayer2.C3322C;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.concurrent.atomic.AtomicReference;
import p007a6.C0042f;

/* renamed from: t6.t */
/* loaded from: classes.dex */
public final class C6339t {

    /* renamed from: a */
    public static final C6339t f21374a = new C6339t();

    /* renamed from: b */
    public static final int f21375b = C3322C.DEFAULT_BUFFER_SEGMENT_SIZE;

    /* renamed from: c */
    public static final C6338s f21376c = new C6338s(new byte[0], 0, 0, false, false);

    /* renamed from: d */
    public static final int f21377d;

    /* renamed from: e */
    public static final AtomicReference<C6338s>[] f21378e;

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f21377d = iHighestOneBit;
        AtomicReference<C6338s>[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i9 = 0; i9 < iHighestOneBit; i9++) {
            atomicReferenceArr[i9] = new AtomicReference<>();
        }
        f21378e = atomicReferenceArr;
    }

    /* renamed from: b */
    public static final void m24292b(C6338s c6338s) {
        AtomicReference<C6338s> atomicReferenceM24294a;
        C6338s c6338s2;
        C6338s andSet;
        C0042f.m158e(c6338s, "segment");
        if (!(c6338s.f21372f == null && c6338s.f21373g == null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (c6338s.f21370d || (andSet = (atomicReferenceM24294a = f21374a.m24294a()).getAndSet((c6338s2 = f21376c))) == c6338s2) {
            return;
        }
        int i9 = andSet != null ? andSet.f21369c : 0;
        if (i9 >= f21375b) {
            atomicReferenceM24294a.set(andSet);
            return;
        }
        c6338s.f21372f = andSet;
        c6338s.f21368b = 0;
        c6338s.f21369c = i9 + UserMetadata.MAX_INTERNAL_KEY_SIZE;
        atomicReferenceM24294a.set(c6338s);
    }

    /* renamed from: c */
    public static final C6338s m24293c() {
        AtomicReference<C6338s> atomicReferenceM24294a = f21374a.m24294a();
        C6338s c6338s = f21376c;
        C6338s andSet = atomicReferenceM24294a.getAndSet(c6338s);
        if (andSet == c6338s) {
            return new C6338s();
        }
        if (andSet == null) {
            atomicReferenceM24294a.set(null);
            return new C6338s();
        }
        atomicReferenceM24294a.set(andSet.f21372f);
        andSet.f21372f = null;
        andSet.f21369c = 0;
        return andSet;
    }

    /* renamed from: a */
    public final AtomicReference<C6338s> m24294a() {
        return f21378e[(int) (Thread.currentThread().getId() & (f21377d - 1))];
    }
}
