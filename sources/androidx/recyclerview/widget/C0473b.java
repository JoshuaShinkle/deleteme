package androidx.recyclerview.widget;

/* renamed from: androidx.recyclerview.widget.b */
/* loaded from: classes.dex */
public class C0473b implements InterfaceC0481j {

    /* renamed from: b */
    public final InterfaceC0481j f2585b;

    /* renamed from: c */
    public int f2586c = 0;

    /* renamed from: d */
    public int f2587d = -1;

    /* renamed from: e */
    public int f2588e = -1;

    /* renamed from: f */
    public Object f2589f = null;

    public C0473b(InterfaceC0481j interfaceC0481j) {
        this.f2585b = interfaceC0481j;
    }

    @Override // androidx.recyclerview.widget.InterfaceC0481j
    /* renamed from: a */
    public void mo2761a(int i9, int i10) {
        m2765e();
        this.f2585b.mo2761a(i9, i10);
    }

    @Override // androidx.recyclerview.widget.InterfaceC0481j
    /* renamed from: b */
    public void mo2762b(int i9, int i10) {
        int i11;
        if (this.f2586c == 1 && i9 >= (i11 = this.f2587d)) {
            int i12 = this.f2588e;
            if (i9 <= i11 + i12) {
                this.f2588e = i12 + i10;
                this.f2587d = Math.min(i9, i11);
                return;
            }
        }
        m2765e();
        this.f2587d = i9;
        this.f2588e = i10;
        this.f2586c = 1;
    }

    @Override // androidx.recyclerview.widget.InterfaceC0481j
    /* renamed from: c */
    public void mo2763c(int i9, int i10) {
        int i11;
        if (this.f2586c == 2 && (i11 = this.f2587d) >= i9 && i11 <= i9 + i10) {
            this.f2588e += i10;
            this.f2587d = i9;
        } else {
            m2765e();
            this.f2587d = i9;
            this.f2588e = i10;
            this.f2586c = 2;
        }
    }

    @Override // androidx.recyclerview.widget.InterfaceC0481j
    /* renamed from: d */
    public void mo2764d(int i9, int i10, Object obj) {
        int i11;
        if (this.f2586c == 3) {
            int i12 = this.f2587d;
            int i13 = this.f2588e;
            if (i9 <= i12 + i13 && (i11 = i9 + i10) >= i12 && this.f2589f == obj) {
                this.f2587d = Math.min(i9, i12);
                this.f2588e = Math.max(i13 + i12, i11) - this.f2587d;
                return;
            }
        }
        m2765e();
        this.f2587d = i9;
        this.f2588e = i10;
        this.f2589f = obj;
        this.f2586c = 3;
    }

    /* renamed from: e */
    public void m2765e() {
        int i9 = this.f2586c;
        if (i9 == 0) {
            return;
        }
        if (i9 == 1) {
            this.f2585b.mo2762b(this.f2587d, this.f2588e);
        } else if (i9 == 2) {
            this.f2585b.mo2763c(this.f2587d, this.f2588e);
        } else if (i9 == 3) {
            this.f2585b.mo2764d(this.f2587d, this.f2588e, this.f2589f);
        }
        this.f2589f = null;
        this.f2586c = 0;
    }
}
