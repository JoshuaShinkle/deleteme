package com.bumptech.glide.load.engine;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import p012b1.InterfaceC0595j;
import p021c0.InterfaceC0699e;
import p152o1.InterfaceC5398e;
import p226w1.C6516i;
import p243y0.C6592e;
import p243y0.InterfaceC6593f;
import p252z0.InterfaceC6806e;

/* renamed from: com.bumptech.glide.load.engine.e */
/* loaded from: classes.dex */
public class C0836e<DataType, ResourceType, Transcode> {

    /* renamed from: a */
    public final Class<DataType> f3796a;

    /* renamed from: b */
    public final List<? extends InterfaceC6593f<DataType, ResourceType>> f3797b;

    /* renamed from: c */
    public final InterfaceC5398e<ResourceType, Transcode> f3798c;

    /* renamed from: d */
    public final InterfaceC0699e<List<Throwable>> f3799d;

    /* renamed from: e */
    public final String f3800e;

    /* renamed from: com.bumptech.glide.load.engine.e$a */
    public interface a<ResourceType> {
        /* renamed from: a */
        InterfaceC0595j<ResourceType> mo3870a(InterfaceC0595j<ResourceType> interfaceC0595j);
    }

    public C0836e(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends InterfaceC6593f<DataType, ResourceType>> list, InterfaceC5398e<ResourceType, Transcode> interfaceC5398e, InterfaceC0699e<List<Throwable>> interfaceC0699e) {
        this.f3796a = cls;
        this.f3797b = list;
        this.f3798c = interfaceC5398e;
        this.f3799d = interfaceC0699e;
        this.f3800e = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    /* renamed from: a */
    public InterfaceC0595j<Transcode> m3927a(InterfaceC6806e<DataType> interfaceC6806e, int i9, int i10, C6592e c6592e, a<ResourceType> aVar) {
        return this.f3798c.mo21102a(aVar.mo3870a(m3928b(interfaceC6806e, i9, i10, c6592e)), c6592e);
    }

    /* renamed from: b */
    public final InterfaceC0595j<ResourceType> m3928b(InterfaceC6806e<DataType> interfaceC6806e, int i9, int i10, C6592e c6592e) {
        List<Throwable> list = (List) C6516i.m24938d(this.f3799d.mo3465b());
        try {
            return m3929c(interfaceC6806e, i9, i10, c6592e, list);
        } finally {
            this.f3799d.mo3464a(list);
        }
    }

    /* renamed from: c */
    public final InterfaceC0595j<ResourceType> m3929c(InterfaceC6806e<DataType> interfaceC6806e, int i9, int i10, C6592e c6592e, List<Throwable> list) throws GlideException {
        int size = this.f3797b.size();
        InterfaceC0595j<ResourceType> interfaceC0595jMo3998b = null;
        for (int i11 = 0; i11 < size; i11++) {
            InterfaceC6593f<DataType, ResourceType> interfaceC6593f = this.f3797b.get(i11);
            try {
                if (interfaceC6593f.mo3997a(interfaceC6806e.mo19963a(), c6592e)) {
                    interfaceC0595jMo3998b = interfaceC6593f.mo3998b(interfaceC6806e.mo19963a(), i9, i10, c6592e);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e9) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + interfaceC6593f, e9);
                }
                list.add(e9);
            }
            if (interfaceC0595jMo3998b != null) {
                break;
            }
        }
        if (interfaceC0595jMo3998b != null) {
            return interfaceC0595jMo3998b;
        }
        throw new GlideException(this.f3800e, new ArrayList(list));
    }

    public String toString() {
        return "DecodePath{ dataClass=" + this.f3796a + ", decoders=" + this.f3797b + ", transcoder=" + this.f3798c + '}';
    }
}
