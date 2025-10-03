package p043d1;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import p226w1.C6516i;

/* renamed from: d1.c */
/* loaded from: classes.dex */
public final class C4655c {

    /* renamed from: a */
    public final Map<String, a> f16291a = new HashMap();

    /* renamed from: b */
    public final b f16292b = new b();

    /* renamed from: d1.c$a */
    public static class a {

        /* renamed from: a */
        public final Lock f16293a = new ReentrantLock();

        /* renamed from: b */
        public int f16294b;
    }

    /* renamed from: d1.c$b */
    public static class b {

        /* renamed from: a */
        public final Queue<a> f16295a = new ArrayDeque();

        /* renamed from: a */
        public a m18603a() {
            a aVarPoll;
            synchronized (this.f16295a) {
                aVarPoll = this.f16295a.poll();
            }
            return aVarPoll == null ? new a() : aVarPoll;
        }

        /* renamed from: b */
        public void m18604b(a aVar) {
            synchronized (this.f16295a) {
                if (this.f16295a.size() < 10) {
                    this.f16295a.offer(aVar);
                }
            }
        }
    }

    /* renamed from: a */
    public void m18601a(String str) {
        a aVarM18603a;
        synchronized (this) {
            aVarM18603a = this.f16291a.get(str);
            if (aVarM18603a == null) {
                aVarM18603a = this.f16292b.m18603a();
                this.f16291a.put(str, aVarM18603a);
            }
            aVarM18603a.f16294b++;
        }
        aVarM18603a.f16293a.lock();
    }

    /* renamed from: b */
    public void m18602b(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) C6516i.m24938d(this.f16291a.get(str));
            int i9 = aVar.f16294b;
            if (i9 < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + aVar.f16294b);
            }
            int i10 = i9 - 1;
            aVar.f16294b = i10;
            if (i10 == 0) {
                a aVarRemove = this.f16291a.remove(str);
                if (!aVarRemove.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVarRemove + ", safeKey: " + str);
                }
                this.f16292b.m18604b(aVarRemove);
            }
        }
        aVar.f16293a.unlock();
    }
}
