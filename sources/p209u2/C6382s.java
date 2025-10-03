package p209u2;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: u2.s */
/* loaded from: classes.dex */
public final class C6382s {

    /* renamed from: a */
    public final int f21546a;

    /* renamed from: b */
    public final int f21547b;

    /* renamed from: c */
    public final float f21548c;

    public C6382s(int i9, int i10) {
        this.f21546a = i9;
        this.f21547b = i10;
        this.f21548c = i10 == 0 ? BitmapDescriptorFactory.HUE_RED : i9 / i10;
    }

    /* renamed from: a */
    public float m24509a() {
        return this.f21548c;
    }

    /* renamed from: b */
    public int m24510b() {
        return this.f21547b;
    }

    /* renamed from: c */
    public int m24511c() {
        return this.f21546a;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C6382s)) {
            return false;
        }
        C6382s c6382s = (C6382s) obj;
        return this.f21546a == c6382s.f21546a && this.f21547b == c6382s.f21547b;
    }

    public int hashCode() {
        int i9 = this.f21547b;
        int i10 = this.f21546a;
        return i9 ^ ((i10 >>> 16) | (i10 << 16));
    }

    public String toString() {
        return this.f21546a + "x" + this.f21547b;
    }
}
