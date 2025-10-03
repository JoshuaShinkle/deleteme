package p192s3;

import android.view.MotionEvent;

/* renamed from: s3.c */
/* loaded from: classes.dex */
public class C6262c {

    /* renamed from: a */
    public MotionEvent f21147a;

    public C6262c(MotionEvent motionEvent) {
        this.f21147a = motionEvent;
    }

    /* renamed from: g */
    public static C6262c m23998g(MotionEvent motionEvent) {
        try {
            return new C6260a(motionEvent);
        } catch (VerifyError unused) {
            return new C6262c(motionEvent);
        }
    }

    /* renamed from: a */
    public int m23999a() {
        return this.f21147a.getAction();
    }

    /* renamed from: b */
    public float m24000b() {
        return this.f21147a.getX();
    }

    /* renamed from: c */
    public float mo23983c(int i9) {
        m24002f(i9);
        return m24000b();
    }

    /* renamed from: d */
    public float m24001d() {
        return this.f21147a.getY();
    }

    /* renamed from: e */
    public float mo23984e(int i9) {
        m24002f(i9);
        return m24001d();
    }

    /* renamed from: f */
    public final void m24002f(int i9) {
        if (i9 > 0) {
            throw new IllegalArgumentException("Invalid pointer index for Donut/Cupcake");
        }
    }
}
