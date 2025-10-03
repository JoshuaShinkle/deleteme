package p150o;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: o.c */
/* loaded from: classes.dex */
public class C5386c extends C5387d {

    /* renamed from: c */
    public float f18285c = BitmapDescriptorFactory.HUE_RED;

    @Override // p150o.C5387d
    /* renamed from: e */
    public void mo1372e() {
        super.mo1372e();
        this.f18285c = BitmapDescriptorFactory.HUE_RED;
    }

    /* renamed from: g */
    public void m21089g() {
        this.f18287b = 2;
    }

    /* renamed from: h */
    public void m21090h(int i9) {
        int i10 = this.f18287b;
        if (i10 == 0 || this.f18285c != i9) {
            this.f18285c = i9;
            if (i10 == 1) {
                m21093c();
            }
            m21092b();
        }
    }
}
