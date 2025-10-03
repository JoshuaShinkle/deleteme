package p228w3;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: w3.t */
/* loaded from: classes.dex */
public class C6538t implements InterfaceC6541w {

    /* renamed from: a */
    public float f22015a = 0.375f;

    /* renamed from: b */
    public float f22016b = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: c */
    public float f22017c = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: d */
    public float f22018d = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: e */
    public float f22019e = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: f */
    public float[] f22020f;

    /* renamed from: a */
    public float m25086a() {
        return this.f22018d;
    }

    /* renamed from: b */
    public float[] m25087b() {
        return this.f22020f;
    }

    /* renamed from: c */
    public float m25088c() {
        return this.f22019e;
    }

    /* renamed from: d */
    public float m25089d() {
        return this.f22017c;
    }

    /* renamed from: e */
    public float m25090e() {
        return this.f22016b;
    }

    /* renamed from: f */
    public float m25091f() {
        return this.f22015a;
    }

    /* renamed from: g */
    public void m25092g(float f9) {
        this.f22018d = f9;
    }

    /* renamed from: h */
    public void m25093h(float[] fArr) {
        if (fArr != null) {
            for (int i9 = 0; i9 < fArr.length; i9++) {
                if (i9 == 0) {
                    this.f22016b = fArr[i9];
                } else if (i9 == 1) {
                    this.f22017c = fArr[i9];
                } else if (i9 == 2) {
                    this.f22018d = fArr[i9];
                }
            }
            this.f22020f = fArr;
        }
    }

    /* renamed from: i */
    public void m25094i(float f9) {
        this.f22019e = f9;
    }

    /* renamed from: j */
    public void m25095j(float f9) {
        this.f22017c = f9;
    }

    /* renamed from: k */
    public void m25096k(float f9) {
        this.f22016b = f9;
    }

    /* renamed from: l */
    public void m25097l(float f9) {
        this.f22015a = f9;
    }
}
