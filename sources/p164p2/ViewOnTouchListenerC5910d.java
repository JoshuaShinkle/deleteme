package p164p2;

import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import com.cyberlink.clrtc.NileNetwork;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p209u2.C6382s;

/* renamed from: p2.d */
/* loaded from: classes.dex */
public final class ViewOnTouchListenerC5910d implements View.OnTouchListener {

    /* renamed from: c */
    public NileNetwork f20387c;

    /* renamed from: e */
    public C6382s f20389e;

    /* renamed from: f */
    public boolean f20390f;

    /* renamed from: b */
    public final PointF f20386b = new PointF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);

    /* renamed from: d */
    public C6382s f20388d = new C6382s(0, 0);

    /* renamed from: g */
    public final Handler f20391g = new Handler(Looper.getMainLooper());

    /* renamed from: h */
    public final Runnable f20392h = new Runnable() { // from class: p2.b
        @Override // java.lang.Runnable
        public final void run() {
            this.f20365b.m23369e();
        }
    };

    /* renamed from: i */
    public final Runnable f20393i = new Runnable() { // from class: p2.c
        @Override // java.lang.Runnable
        public final void run() {
            this.f20374b.m23370f();
        }
    };

    /* renamed from: j */
    public final Rect f20394j = new Rect();

    public ViewOnTouchListenerC5910d(NileNetwork nileNetwork) {
        this.f20387c = nileNetwork;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public /* synthetic */ void m23369e() {
        this.f20387c.m4922X7(this.f20386b);
        this.f20391g.postDelayed(this.f20392h, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public /* synthetic */ void m23370f() {
        this.f20386b.set(-1.0f, -1.0f);
        this.f20387c.m4922X7(this.f20386b);
    }

    /* renamed from: c */
    public final void m23371c() {
        if (this.f20388d.m24509a() > this.f20389e.m24509a()) {
            Rect rect = this.f20394j;
            rect.top = 0;
            rect.bottom = this.f20388d.m24510b();
            int iRound = Math.round(this.f20388d.m24510b() * this.f20389e.m24509a());
            this.f20394j.left = Math.round((this.f20388d.m24511c() - iRound) * 0.5f);
            Rect rect2 = this.f20394j;
            rect2.right = rect2.left + iRound;
            return;
        }
        Rect rect3 = this.f20394j;
        rect3.left = 0;
        rect3.right = this.f20388d.m24511c();
        int iRound2 = Math.round(this.f20388d.m24511c() / this.f20389e.m24509a());
        this.f20394j.top = Math.round((this.f20388d.m24510b() - iRound2) * 0.5f);
        Rect rect4 = this.f20394j;
        rect4.bottom = rect4.top + iRound2;
    }

    /* renamed from: d */
    public final void m23372d(View view) {
        if (view.getWidth() != this.f20388d.m24511c() || view.getHeight() != this.f20388d.m24510b()) {
            this.f20388d = new C6382s(view.getWidth(), view.getHeight());
        }
        m23371c();
    }

    /* renamed from: g */
    public final void m23373g(boolean z8) {
        this.f20391g.removeCallbacks(this.f20393i);
        this.f20392h.run();
        if (z8) {
            this.f20391g.removeCallbacks(this.f20392h);
            this.f20391g.postDelayed(this.f20393i, 500L);
        }
    }

    /* renamed from: h */
    public void m23374h(C6382s c6382s) {
        if (c6382s.m24509a() == BitmapDescriptorFactory.HUE_RED) {
            return;
        }
        this.f20389e = c6382s;
        m23371c();
    }

    /* renamed from: i */
    public void m23375i(boolean z8) {
        this.f20390f = z8;
    }

    /* renamed from: j */
    public final void m23376j(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 1 || actionMasked == 2 || actionMasked == 3) {
            m23372d(view);
            m23377k(motionEvent.getX(), motionEvent.getY());
        }
    }

    /* renamed from: k */
    public final void m23377k(float f9, float f10) {
        int i9 = this.f20394j.left;
        if (f9 <= i9) {
            this.f20386b.x = BitmapDescriptorFactory.HUE_RED;
        } else if (f9 >= r0.right) {
            this.f20386b.x = 1.0f;
        } else {
            this.f20386b.x = (f9 - i9) / r0.width();
        }
        int i10 = this.f20394j.top;
        if (f10 <= i10) {
            this.f20386b.y = BitmapDescriptorFactory.HUE_RED;
        } else if (f10 >= r6.bottom) {
            this.f20386b.y = 1.0f;
        } else {
            this.f20386b.y = (f10 - i10) / r6.height();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.f20390f || this.f20389e.m24509a() == BitmapDescriptorFactory.HUE_RED) {
            return false;
        }
        m23376j(view, motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            m23373g(false);
            return true;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                return true;
            }
            if (actionMasked != 3) {
                return false;
            }
        }
        m23373g(true);
        return false;
    }
}
