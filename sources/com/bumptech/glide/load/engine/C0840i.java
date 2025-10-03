package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.engine.C0836e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p226w1.C6516i;
import p243y0.C6592e;
import p252z0.InterfaceC6806e;

/* renamed from: com.bumptech.glide.load.engine.i */
/* loaded from: classes.dex */
public class C0840i<Data, ResourceType, Transcode> {

    /* renamed from: a */
    public final Class<Data> f3857a;

    /* renamed from: b */
    public final InterfaceC0699e<List<Throwable>> f3858b;

    /* renamed from: c */
    public final List<? extends C0836e<Data, ResourceType, Transcode>> f3859c;

    /* renamed from: d */
    public final String f3860d;

    public C0840i(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<C0836e<Data, ResourceType, Transcode>> list, InterfaceC0699e<List<Throwable>> interfaceC0699e) {
        this.f3857a = cls;
        this.f3858b = interfaceC0699e;
        this.f3859c = (List) C6516i.m24937c(list);
        this.f3860d = "Failed LoadPath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    /* renamed from: a */
    public InterfaceC0595j<Transcode> m3963a(InterfaceC6806e<Data> interfaceC6806e, C6592e c6592e, int i9, int i10, C0836e.a<ResourceType> aVar) {
        List<Throwable> list = (List) C6516i.m24938d(this.f3858b.mo3465b());
        try {
            return m3964b(interfaceC6806e, c6592e, i9, i10, aVar, list);
        } finally {
            this.f3858b.mo3464a(list);
        }
    }

    /* renamed from: b */
    public final InterfaceC0595j<Transcode> m3964b(InterfaceC6806e<Data> interfaceC6806e, C6592e c6592e, int i9, int i10, C0836e.a<ResourceType> aVar, List<Throwable> list) throws GlideException {
        int size = this.f3859c.size();
        InterfaceC0595j<Transcode> interfaceC0595jM3927a = null;
        for (int i11 = 0; i11 < size; i11++) {
            try {
                interfaceC0595jM3927a = this.f3859c.get(i11).m3927a(interfaceC6806e, i9, i10, c6592e, aVar);
            } catch (GlideException e9) {
                list.add(e9);
            }
            if (interfaceC0595jM3927a != null) {
                break;
            }
        }
        if (interfaceC0595jM3927a != null) {
            return interfaceC0595jM3927a;
        }
        throw new GlideException(this.f3860d, new ArrayList(list));
    }

    public String toString() {
        return "LoadPath{decodePaths=" + Arrays.toString(this.f3859c.toArray()) + '}';
    }
}
