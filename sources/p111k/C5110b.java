package p111k;

import android.os.IBinder;
import p000a.InterfaceC0000a;

/* renamed from: k.b */
/* loaded from: classes.dex */
public class C5110b {

    /* renamed from: a */
    public final InterfaceC0000a f17573a;

    /* renamed from: b */
    public final C5109a f17574b = new a();

    /* renamed from: k.b$a */
    public class a extends C5109a {
        public a() {
        }
    }

    public C5110b(InterfaceC0000a interfaceC0000a) {
        this.f17573a = interfaceC0000a;
    }

    /* renamed from: a */
    public IBinder m19959a() {
        return this.f17573a.asBinder();
    }

    public boolean equals(Object obj) {
        if (obj instanceof C5110b) {
            return ((C5110b) obj).m19959a().equals(this.f17573a.asBinder());
        }
        return false;
    }

    public int hashCode() {
        return m19959a().hashCode();
    }
}
