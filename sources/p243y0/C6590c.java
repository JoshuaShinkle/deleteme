package p243y0;

import android.content.Context;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import p012b1.InterfaceC0595j;

/* renamed from: y0.c */
/* loaded from: classes.dex */
public class C6590c<T> implements InterfaceC6595h<T> {

    /* renamed from: b */
    public final Collection<? extends InterfaceC6595h<T>> f22140b;

    @SafeVarargs
    public C6590c(InterfaceC6595h<T>... interfaceC6595hArr) {
        if (interfaceC6595hArr.length == 0) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.f22140b = Arrays.asList(interfaceC6595hArr);
    }

    @Override // p243y0.InterfaceC6589b
    /* renamed from: a */
    public void mo3265a(MessageDigest messageDigest) {
        Iterator<? extends InterfaceC6595h<T>> it = this.f22140b.iterator();
        while (it.hasNext()) {
            it.next().mo3265a(messageDigest);
        }
    }

    @Override // p243y0.InterfaceC6595h
    /* renamed from: b */
    public InterfaceC0595j<T> mo19696b(Context context, InterfaceC0595j<T> interfaceC0595j, int i9, int i10) {
        Iterator<? extends InterfaceC6595h<T>> it = this.f22140b.iterator();
        InterfaceC0595j<T> interfaceC0595j2 = interfaceC0595j;
        while (it.hasNext()) {
            InterfaceC0595j<T> interfaceC0595jMo19696b = it.next().mo19696b(context, interfaceC0595j2, i9, i10);
            if (interfaceC0595j2 != null && !interfaceC0595j2.equals(interfaceC0595j) && !interfaceC0595j2.equals(interfaceC0595jMo19696b)) {
                interfaceC0595j2.mo3281b();
            }
            interfaceC0595j2 = interfaceC0595jMo19696b;
        }
        return interfaceC0595j2;
    }

    @Override // p243y0.InterfaceC6589b
    public boolean equals(Object obj) {
        if (obj instanceof C6590c) {
            return this.f22140b.equals(((C6590c) obj).f22140b);
        }
        return false;
    }

    @Override // p243y0.InterfaceC6589b
    public int hashCode() {
        return this.f22140b.hashCode();
    }
}
