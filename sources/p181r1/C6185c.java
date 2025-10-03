package p181r1;

import com.bumptech.glide.load.engine.C0836e;
import com.bumptech.glide.load.engine.C0840i;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import p132m.C5302a;
import p152o1.C5400g;
import p226w1.C6515h;

/* renamed from: r1.c */
/* loaded from: classes.dex */
public class C6185c {

    /* renamed from: c */
    public static final C0840i<?, ?, ?> f20839c = new C0840i<>(Object.class, Object.class, Object.class, Collections.singletonList(new C0836e(Object.class, Object.class, Object.class, Collections.emptyList(), new C5400g(), null)), null);

    /* renamed from: a */
    public final C5302a<C6515h, C0840i<?, ?, ?>> f20840a = new C5302a<>();

    /* renamed from: b */
    public final AtomicReference<C6515h> f20841b = new AtomicReference<>();

    /* renamed from: a */
    public <Data, TResource, Transcode> C0840i<Data, TResource, Transcode> m23654a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        C0840i<Data, TResource, Transcode> c0840i;
        C6515h c6515hM23655b = m23655b(cls, cls2, cls3);
        synchronized (this.f20840a) {
            c0840i = (C0840i) this.f20840a.get(c6515hM23655b);
        }
        this.f20841b.set(c6515hM23655b);
        return c0840i;
    }

    /* renamed from: b */
    public final C6515h m23655b(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        C6515h andSet = this.f20841b.getAndSet(null);
        if (andSet == null) {
            andSet = new C6515h();
        }
        andSet.m24934b(cls, cls2, cls3);
        return andSet;
    }

    /* renamed from: c */
    public boolean m23656c(C0840i<?, ?, ?> c0840i) {
        return f20839c.equals(c0840i);
    }

    /* renamed from: d */
    public void m23657d(Class<?> cls, Class<?> cls2, Class<?> cls3, C0840i<?, ?, ?> c0840i) {
        synchronized (this.f20840a) {
            C5302a<C6515h, C0840i<?, ?, ?>> c5302a = this.f20840a;
            C6515h c6515h = new C6515h(cls, cls2, cls3);
            if (c0840i == null) {
                c0840i = f20839c;
            }
            c5302a.put(c6515h, c0840i);
        }
    }
}
