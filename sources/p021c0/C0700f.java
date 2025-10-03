package p021c0;

/* renamed from: c0.f */
/* loaded from: classes.dex */
public class C0700f<T> implements InterfaceC0699e<T> {

    /* renamed from: a */
    public final Object[] f3355a;

    /* renamed from: b */
    public int f3356b;

    public C0700f(int i9) {
        if (i9 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f3355a = new Object[i9];
    }

    @Override // p021c0.InterfaceC0699e
    /* renamed from: a */
    public boolean mo3464a(T t8) {
        if (m3466c(t8)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i9 = this.f3356b;
        Object[] objArr = this.f3355a;
        if (i9 >= objArr.length) {
            return false;
        }
        objArr[i9] = t8;
        this.f3356b = i9 + 1;
        return true;
    }

    @Override // p021c0.InterfaceC0699e
    /* renamed from: b */
    public T mo3465b() {
        int i9 = this.f3356b;
        if (i9 <= 0) {
            return null;
        }
        int i10 = i9 - 1;
        Object[] objArr = this.f3355a;
        T t8 = (T) objArr[i10];
        objArr[i10] = null;
        this.f3356b = i9 - 1;
        return t8;
    }

    /* renamed from: c */
    public final boolean m3466c(T t8) {
        for (int i9 = 0; i9 < this.f3356b; i9++) {
            if (this.f3355a[i9] == t8) {
                return true;
            }
        }
        return false;
    }
}
