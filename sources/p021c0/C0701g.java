package p021c0;

/* renamed from: c0.g */
/* loaded from: classes.dex */
public class C0701g<T> extends C0700f<T> {

    /* renamed from: c */
    public final Object f3357c;

    public C0701g(int i9) {
        super(i9);
        this.f3357c = new Object();
    }

    @Override // p021c0.C0700f, p021c0.InterfaceC0699e
    /* renamed from: a */
    public boolean mo3464a(T t8) {
        boolean zMo3464a;
        synchronized (this.f3357c) {
            zMo3464a = super.mo3464a(t8);
        }
        return zMo3464a;
    }

    @Override // p021c0.C0700f, p021c0.InterfaceC0699e
    /* renamed from: b */
    public T mo3465b() {
        T t8;
        synchronized (this.f3357c) {
            t8 = (T) super.mo3465b();
        }
        return t8;
    }
}
