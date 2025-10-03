package com.bumptech.glide;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.C0836e;
import com.bumptech.glide.load.engine.C0840i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p073g1.C4822p;
import p073g1.InterfaceC4820n;
import p073g1.InterfaceC4821o;
import p152o1.C5399f;
import p152o1.InterfaceC5398e;
import p181r1.C6183a;
import p181r1.C6184b;
import p181r1.C6185c;
import p181r1.C6186d;
import p181r1.C6187e;
import p181r1.C6188f;
import p235x1.C6563a;
import p243y0.InterfaceC6588a;
import p243y0.InterfaceC6593f;
import p243y0.InterfaceC6594g;
import p252z0.C6807f;
import p252z0.InterfaceC6806e;

/* loaded from: classes.dex */
public class Registry {

    /* renamed from: a */
    public final C4822p f3674a;

    /* renamed from: b */
    public final C6183a f3675b;

    /* renamed from: c */
    public final C6187e f3676c;

    /* renamed from: d */
    public final C6188f f3677d;

    /* renamed from: e */
    public final C6807f f3678e;

    /* renamed from: f */
    public final C5399f f3679f;

    /* renamed from: g */
    public final C6184b f3680g;

    /* renamed from: h */
    public final C6186d f3681h = new C6186d();

    /* renamed from: i */
    public final C6185c f3682i = new C6185c();

    /* renamed from: j */
    public final InterfaceC0699e<List<Throwable>> f3683j;

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(String str) {
            super(str);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(Object obj) {
            super("Failed to find any ModelLoaders for model: " + obj);
        }

        public NoModelLoaderAvailableException(Class<?> cls, Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public Registry() {
        InterfaceC0699e<List<Throwable>> interfaceC0699eM25129f = C6563a.m25129f();
        this.f3683j = interfaceC0699eM25129f;
        this.f3674a = new C4822p(interfaceC0699eM25129f);
        this.f3675b = new C6183a();
        this.f3676c = new C6187e();
        this.f3677d = new C6188f();
        this.f3678e = new C6807f();
        this.f3679f = new C5399f();
        this.f3680g = new C6184b();
        m3823t(Arrays.asList("Gif", "Bitmap", "BitmapDrawable"));
    }

    /* renamed from: a */
    public <Model, Data> Registry m3804a(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<Model, Data> interfaceC4821o) {
        this.f3674a.m19127a(cls, cls2, interfaceC4821o);
        return this;
    }

    /* renamed from: b */
    public <Data, TResource> Registry m3805b(Class<Data> cls, Class<TResource> cls2, InterfaceC6593f<Data, TResource> interfaceC6593f) {
        m3808e("legacy_append", cls, cls2, interfaceC6593f);
        return this;
    }

    /* renamed from: c */
    public <Data> Registry m3806c(Class<Data> cls, InterfaceC6588a<Data> interfaceC6588a) {
        this.f3675b.m23649a(cls, interfaceC6588a);
        return this;
    }

    /* renamed from: d */
    public <TResource> Registry m3807d(Class<TResource> cls, InterfaceC6594g<TResource> interfaceC6594g) {
        this.f3677d.m23666a(cls, interfaceC6594g);
        return this;
    }

    /* renamed from: e */
    public <Data, TResource> Registry m3808e(String str, Class<Data> cls, Class<TResource> cls2, InterfaceC6593f<Data, TResource> interfaceC6593f) {
        this.f3676c.m23660a(str, interfaceC6593f, cls, cls2);
        return this;
    }

    /* renamed from: f */
    public final <Data, TResource, Transcode> List<C0836e<Data, TResource, Transcode>> m3809f(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        ArrayList arrayList = new ArrayList();
        for (Class cls4 : this.f3676c.m23663d(cls, cls2)) {
            for (Class cls5 : this.f3679f.m21105b(cls4, cls3)) {
                arrayList.add(new C0836e(cls, cls4, cls5, this.f3676c.m23661b(cls, cls4), this.f3679f.m21104a(cls4, cls5), this.f3683j));
            }
        }
        return arrayList;
    }

    /* renamed from: g */
    public List<ImageHeaderParser> m3810g() {
        List<ImageHeaderParser> listM23653b = this.f3680g.m23653b();
        if (listM23653b.isEmpty()) {
            throw new NoImageHeaderParserException();
        }
        return listM23653b;
    }

    /* renamed from: h */
    public <Data, TResource, Transcode> C0840i<Data, TResource, Transcode> m3811h(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        C0840i<Data, TResource, Transcode> c0840iM23654a = this.f3682i.m23654a(cls, cls2, cls3);
        if (this.f3682i.m23656c(c0840iM23654a)) {
            return null;
        }
        if (c0840iM23654a == null) {
            List<C0836e<Data, TResource, Transcode>> listM3809f = m3809f(cls, cls2, cls3);
            c0840iM23654a = listM3809f.isEmpty() ? null : new C0840i<>(cls, cls2, cls3, listM3809f, this.f3683j);
            this.f3682i.m23657d(cls, cls2, cls3, c0840iM23654a);
        }
        return c0840iM23654a;
    }

    /* renamed from: i */
    public <Model> List<InterfaceC4820n<Model, ?>> m3812i(Model model) {
        List<InterfaceC4820n<Model, ?>> listM19129d = this.f3674a.m19129d(model);
        if (listM19129d.isEmpty()) {
            throw new NoModelLoaderAvailableException(model);
        }
        return listM19129d;
    }

    /* renamed from: j */
    public <Model, TResource, Transcode> List<Class<?>> m3813j(Class<Model> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        List<Class<?>> listM23658a = this.f3681h.m23658a(cls, cls2);
        if (listM23658a == null) {
            listM23658a = new ArrayList<>();
            Iterator<Class<?>> it = this.f3674a.m19128c(cls).iterator();
            while (it.hasNext()) {
                for (Class<?> cls4 : this.f3676c.m23663d(it.next(), cls2)) {
                    if (!this.f3679f.m21105b(cls4, cls3).isEmpty() && !listM23658a.contains(cls4)) {
                        listM23658a.add(cls4);
                    }
                }
            }
            this.f3681h.m23659b(cls, cls2, Collections.unmodifiableList(listM23658a));
        }
        return listM23658a;
    }

    /* renamed from: k */
    public <X> InterfaceC6594g<X> m3814k(InterfaceC0595j<X> interfaceC0595j) {
        InterfaceC6594g<X> interfaceC6594gM23667b = this.f3677d.m23667b(interfaceC0595j.mo3283d());
        if (interfaceC6594gM23667b != null) {
            return interfaceC6594gM23667b;
        }
        throw new NoResultEncoderAvailableException(interfaceC0595j.mo3283d());
    }

    /* renamed from: l */
    public <X> InterfaceC6806e<X> m3815l(X x8) {
        return this.f3678e.m25366a(x8);
    }

    /* renamed from: m */
    public <X> InterfaceC6588a<X> m3816m(X x8) {
        InterfaceC6588a<X> interfaceC6588aM23650b = this.f3675b.m23650b(x8.getClass());
        if (interfaceC6588aM23650b != null) {
            return interfaceC6588aM23650b;
        }
        throw new NoSourceEncoderAvailableException(x8.getClass());
    }

    /* renamed from: n */
    public boolean m3817n(InterfaceC0595j<?> interfaceC0595j) {
        return this.f3677d.m23667b(interfaceC0595j.mo3283d()) != null;
    }

    /* renamed from: o */
    public <Model, Data> Registry m3818o(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<Model, Data> interfaceC4821o) {
        this.f3674a.m19131f(cls, cls2, interfaceC4821o);
        return this;
    }

    /* renamed from: p */
    public Registry m3819p(ImageHeaderParser imageHeaderParser) {
        this.f3680g.m23652a(imageHeaderParser);
        return this;
    }

    /* renamed from: q */
    public <TResource, Transcode> Registry m3820q(Class<TResource> cls, Class<Transcode> cls2, InterfaceC5398e<TResource, Transcode> interfaceC5398e) {
        this.f3679f.m21106c(cls, cls2, interfaceC5398e);
        return this;
    }

    /* renamed from: r */
    public Registry m3821r(InterfaceC6806e.a<?> aVar) {
        this.f3678e.m25367b(aVar);
        return this;
    }

    /* renamed from: s */
    public <Model, Data> Registry m3822s(Class<Model> cls, Class<Data> cls2, InterfaceC4821o<? extends Model, ? extends Data> interfaceC4821o) {
        this.f3674a.m19132g(cls, cls2, interfaceC4821o);
        return this;
    }

    /* renamed from: t */
    public final Registry m3823t(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        this.f3676c.m23664e(arrayList);
        return this;
    }
}
