package p215v;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import p001a0.C0004b;
import p206u.C6347a;

/* renamed from: v.r */
/* loaded from: classes.dex */
public class C6444r {

    /* renamed from: a */
    public ConcurrentHashMap<Long, C6347a.b> f21674a = new ConcurrentHashMap<>();

    /* renamed from: v.r$a */
    public class a implements b<C0004b.f> {
        public a() {
        }

        @Override // p215v.C6444r.b
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int mo24632a(C0004b.f fVar) {
            return fVar.m38d();
        }

        @Override // p215v.C6444r.b
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean mo24633b(C0004b.f fVar) {
            return fVar.m39e();
        }
    }

    /* renamed from: v.r$b */
    public interface b<T> {
        /* renamed from: a */
        int mo24632a(T t8);

        /* renamed from: b */
        boolean mo24633b(T t8);
    }

    /* renamed from: d */
    public static <T> T m24631d(T[] tArr, int i9, b<T> bVar) {
        int i10 = (i9 & 1) == 0 ? 400 : 700;
        boolean z8 = (i9 & 2) != 0;
        T t8 = null;
        int i11 = Integer.MAX_VALUE;
        for (T t9 : tArr) {
            int iAbs = (Math.abs(bVar.mo24632a(t9) - i10) * 2) + (bVar.mo24633b(t9) == z8 ? 0 : 1);
            if (t8 == null || i11 > iAbs) {
                t8 = t9;
                i11 = iAbs;
            }
        }
        return t8;
    }

    /* renamed from: a */
    public Typeface mo24604a(Context context, C6347a.b bVar, Resources resources, int i9) {
        throw null;
    }

    /* renamed from: b */
    public Typeface mo24606b(Context context, CancellationSignal cancellationSignal, C0004b.f[] fVarArr, int i9) {
        throw null;
    }

    /* renamed from: c */
    public Typeface mo24607c(Context context, Resources resources, int i9, String str, int i10) {
        File fileM24639d = C6445s.m24639d(context);
        if (fileM24639d == null) {
            return null;
        }
        try {
            if (C6445s.m24637b(fileM24639d, resources, i9)) {
                return Typeface.createFromFile(fileM24639d.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileM24639d.delete();
        }
    }

    /* renamed from: e */
    public C0004b.f mo24630e(C0004b.f[] fVarArr, int i9) {
        return (C0004b.f) m24631d(fVarArr, i9, new a());
    }
}
