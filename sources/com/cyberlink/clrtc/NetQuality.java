package com.cyberlink.clrtc;

import p209u2.C6372i;

/* loaded from: classes.dex */
public class NetQuality {

    /* renamed from: a */
    public final C6372i f4874a = new C6372i(1);

    /* renamed from: b */
    public final C6372i f4875b = new C6372i(1);

    /* renamed from: c */
    public final C6372i f4876c = new C6372i(1);

    /* renamed from: d */
    public final C6372i f4877d = new C6372i(1);

    /* renamed from: e */
    public final C6372i f4878e = new C6372i(1);

    /* renamed from: f */
    public final C6372i f4879f = new C6372i(1);

    public enum Quality {
        UNKNOWN(-1),
        GOOD(0),
        ACCEPTABLE(1),
        POOR(2),
        UNAVAILABLE(3);

        private final int value;

        Quality(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static Quality m4474a(int i9) {
            for (Quality quality : values()) {
                if (quality.value == i9) {
                    return quality;
                }
            }
            return UNKNOWN;
        }
    }

    /* renamed from: a */
    public void m4471a(int i9, int i10, int i11, int i12, int i13, int i14) {
        this.f4874a.m24482a(i9);
        this.f4875b.m24482a(i10);
        this.f4876c.m24482a(i11);
        this.f4877d.m24482a(i12);
        this.f4878e.m24482a(i13);
        this.f4879f.m24482a(i14);
    }

    /* renamed from: b */
    public Quality m4472b() {
        return Quality.m4474a((int) this.f4877d.m24483b());
    }

    /* renamed from: c */
    public Quality m4473c() {
        return Quality.m4474a((int) this.f4874a.m24483b());
    }
}
