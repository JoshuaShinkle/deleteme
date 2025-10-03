package p012b1;

import java.io.File;
import p043d1.InterfaceC4653a;
import p243y0.C6592e;
import p243y0.InterfaceC6588a;

/* renamed from: b1.b */
/* loaded from: classes.dex */
public class C0587b<DataType> implements InterfaceC4653a.b {

    /* renamed from: a */
    public final InterfaceC6588a<DataType> f3104a;

    /* renamed from: b */
    public final DataType f3105b;

    /* renamed from: c */
    public final C6592e f3106c;

    public C0587b(InterfaceC6588a<DataType> interfaceC6588a, DataType datatype, C6592e c6592e) {
        this.f3104a = interfaceC6588a;
        this.f3105b = datatype;
        this.f3106c = c6592e;
    }

    @Override // p043d1.InterfaceC4653a.b
    /* renamed from: a */
    public boolean mo3266a(File file) {
        return this.f3104a.mo19086b(this.f3105b, file, this.f3106c);
    }
}
