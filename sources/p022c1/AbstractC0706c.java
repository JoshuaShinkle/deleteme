package p022c1;

import java.util.Queue;
import p022c1.InterfaceC0715l;
import p226w1.C6517j;

/* renamed from: c1.c */
/* loaded from: classes.dex */
public abstract class AbstractC0706c<T extends InterfaceC0715l> {

    /* renamed from: a */
    public final Queue<T> f3360a = C6517j.m24945f(20);

    /* renamed from: a */
    public abstract T mo3482a();

    /* renamed from: b */
    public T m3483b() {
        T tPoll = this.f3360a.poll();
        return tPoll == null ? (T) mo3482a() : tPoll;
    }

    /* renamed from: c */
    public void m3484c(T t8) {
        if (this.f3360a.size() < 20) {
            this.f3360a.offer(t8);
        }
    }
}
