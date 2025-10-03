package p141n;

/* renamed from: n.e */
/* loaded from: classes.dex */
public class C5345e<T> implements InterfaceC5344d<T> {

    /* renamed from: a */
    public final Object[] f18195a;

    /* renamed from: b */
    public int f18196b;

    public C5345e(int i9) {
        if (i9 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f18195a = new Object[i9];
    }

    @Override // p141n.InterfaceC5344d
    /* renamed from: a */
    public boolean mo20993a(T t8) {
        int i9 = this.f18196b;
        Object[] objArr = this.f18195a;
        if (i9 >= objArr.length) {
            return false;
        }
        objArr[i9] = t8;
        this.f18196b = i9 + 1;
        return true;
    }

    @Override // p141n.InterfaceC5344d
    /* renamed from: b */
    public T mo20994b() {
        int i9 = this.f18196b;
        if (i9 <= 0) {
            return null;
        }
        int i10 = i9 - 1;
        Object[] objArr = this.f18195a;
        T t8 = (T) objArr[i10];
        objArr[i10] = null;
        this.f18196b = i9 - 1;
        return t8;
    }

    @Override // p141n.InterfaceC5344d
    /* renamed from: c */
    public void mo20995c(T[] tArr, int i9) {
        if (i9 > tArr.length) {
            i9 = tArr.length;
        }
        for (int i10 = 0; i10 < i9; i10++) {
            T t8 = tArr[i10];
            int i11 = this.f18196b;
            Object[] objArr = this.f18195a;
            if (i11 < objArr.length) {
                objArr[i11] = t8;
                this.f18196b = i11 + 1;
            }
        }
    }
}
