package p156o5;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: o5.b */
/* loaded from: classes2.dex */
public class C5468b {

    /* renamed from: a */
    public SparseArray<List<Long>> f18387a;

    /* renamed from: o5.b$b */
    public static class b {

        /* renamed from: a */
        public static C5468b f18388a = new C5468b();
    }

    /* renamed from: a */
    public static C5468b m21111a() {
        return b.f18388a;
    }

    /* renamed from: b */
    public void m21112b(int i9) {
        long jNanoTime = System.nanoTime();
        List<Long> arrayList = this.f18387a.get(i9);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f18387a.put(i9, arrayList);
        }
        arrayList.add(Long.valueOf(jNanoTime));
    }

    /* renamed from: c */
    public long m21113c(int i9, TimeUnit timeUnit) {
        int size;
        List<Long> list = this.f18387a.get(i9);
        if (list != null && list.size() - 1 >= 0) {
            return timeUnit.convert(System.nanoTime() - list.remove(size).longValue(), TimeUnit.NANOSECONDS);
        }
        return -1L;
    }

    public C5468b() {
        this.f18387a = new SparseArray<>();
    }
}
