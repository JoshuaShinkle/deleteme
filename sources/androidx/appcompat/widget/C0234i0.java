package androidx.appcompat.widget;

/* renamed from: androidx.appcompat.widget.i0 */
/* loaded from: classes.dex */
public class C0234i0 {

    /* renamed from: a */
    public int f1087a = 0;

    /* renamed from: b */
    public int f1088b = 0;

    /* renamed from: c */
    public int f1089c = Integer.MIN_VALUE;

    /* renamed from: d */
    public int f1090d = Integer.MIN_VALUE;

    /* renamed from: e */
    public int f1091e = 0;

    /* renamed from: f */
    public int f1092f = 0;

    /* renamed from: g */
    public boolean f1093g = false;

    /* renamed from: h */
    public boolean f1094h = false;

    /* renamed from: a */
    public int m888a() {
        return this.f1093g ? this.f1087a : this.f1088b;
    }

    /* renamed from: b */
    public int m889b() {
        return this.f1087a;
    }

    /* renamed from: c */
    public int m890c() {
        return this.f1088b;
    }

    /* renamed from: d */
    public int m891d() {
        return this.f1093g ? this.f1088b : this.f1087a;
    }

    /* renamed from: e */
    public void m892e(int i9, int i10) {
        this.f1094h = false;
        if (i9 != Integer.MIN_VALUE) {
            this.f1091e = i9;
            this.f1087a = i9;
        }
        if (i10 != Integer.MIN_VALUE) {
            this.f1092f = i10;
            this.f1088b = i10;
        }
    }

    /* renamed from: f */
    public void m893f(boolean z8) {
        if (z8 == this.f1093g) {
            return;
        }
        this.f1093g = z8;
        if (!this.f1094h) {
            this.f1087a = this.f1091e;
            this.f1088b = this.f1092f;
            return;
        }
        if (z8) {
            int i9 = this.f1090d;
            if (i9 == Integer.MIN_VALUE) {
                i9 = this.f1091e;
            }
            this.f1087a = i9;
            int i10 = this.f1089c;
            if (i10 == Integer.MIN_VALUE) {
                i10 = this.f1092f;
            }
            this.f1088b = i10;
            return;
        }
        int i11 = this.f1089c;
        if (i11 == Integer.MIN_VALUE) {
            i11 = this.f1091e;
        }
        this.f1087a = i11;
        int i12 = this.f1090d;
        if (i12 == Integer.MIN_VALUE) {
            i12 = this.f1092f;
        }
        this.f1088b = i12;
    }

    /* renamed from: g */
    public void m894g(int i9, int i10) {
        this.f1089c = i9;
        this.f1090d = i10;
        this.f1094h = true;
        if (this.f1093g) {
            if (i10 != Integer.MIN_VALUE) {
                this.f1087a = i10;
            }
            if (i9 != Integer.MIN_VALUE) {
                this.f1088b = i9;
                return;
            }
            return;
        }
        if (i9 != Integer.MIN_VALUE) {
            this.f1087a = i9;
        }
        if (i10 != Integer.MIN_VALUE) {
            this.f1088b = i10;
        }
    }
}
