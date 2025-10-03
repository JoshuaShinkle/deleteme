package p076g4;

import android.view.MotionEvent;

/* renamed from: g4.b */
/* loaded from: classes.dex */
public class C4958b {

    /* renamed from: a */
    public MotionEvent f17056a;

    public C4958b(MotionEvent motionEvent) {
        this.f17056a = motionEvent;
    }

    /* renamed from: g */
    public static C4958b m19229g(MotionEvent motionEvent) {
        try {
            return new C4957a(motionEvent);
        } catch (VerifyError unused) {
            return new C4958b(motionEvent);
        }
    }

    /* renamed from: a */
    public int m19230a() {
        return this.f17056a.getAction();
    }

    /* renamed from: b */
    public float m19231b() {
        return this.f17056a.getX();
    }

    /* renamed from: c */
    public float mo19227c(int i9) {
        m19233f(i9);
        return m19231b();
    }

    /* renamed from: d */
    public float m19232d() {
        return this.f17056a.getY();
    }

    /* renamed from: e */
    public float mo19228e(int i9) {
        m19233f(i9);
        return m19232d();
    }

    /* renamed from: f */
    public final void m19233f(int i9) {
        if (i9 > 0) {
            throw new IllegalArgumentException("Invalid pointer index for Donut/Cupcake");
        }
    }
}
