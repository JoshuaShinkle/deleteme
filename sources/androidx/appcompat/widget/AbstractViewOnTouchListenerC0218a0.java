package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.appcompat.view.menu.InterfaceC0146p;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: androidx.appcompat.widget.a0 */
/* loaded from: classes.dex */
public abstract class AbstractViewOnTouchListenerC0218a0 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: b */
    public final float f1030b;

    /* renamed from: c */
    public final int f1031c;

    /* renamed from: d */
    public final int f1032d;

    /* renamed from: e */
    public final View f1033e;

    /* renamed from: f */
    public Runnable f1034f;

    /* renamed from: g */
    public Runnable f1035g;

    /* renamed from: h */
    public boolean f1036h;

    /* renamed from: i */
    public int f1037i;

    /* renamed from: j */
    public final int[] f1038j = new int[2];

    /* renamed from: androidx.appcompat.widget.a0$a */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = AbstractViewOnTouchListenerC0218a0.this.f1033e.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.a0$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractViewOnTouchListenerC0218a0.this.m791e();
        }
    }

    public AbstractViewOnTouchListenerC0218a0(View view) {
        this.f1033e = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1030b = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1031c = tapTimeout;
        this.f1032d = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    /* renamed from: h */
    public static boolean m789h(View view, float f9, float f10, float f11) {
        float f12 = -f11;
        return f9 >= f12 && f10 >= f12 && f9 < ((float) (view.getRight() - view.getLeft())) + f11 && f10 < ((float) (view.getBottom() - view.getTop())) + f11;
    }

    /* renamed from: a */
    public final void m790a() {
        Runnable runnable = this.f1035g;
        if (runnable != null) {
            this.f1033e.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f1034f;
        if (runnable2 != null) {
            this.f1033e.removeCallbacks(runnable2);
        }
    }

    /* renamed from: b */
    public abstract InterfaceC0146p mo458b();

    /* renamed from: c */
    public abstract boolean mo459c();

    /* renamed from: d */
    public boolean mo632d() {
        InterfaceC0146p interfaceC0146pMo458b = mo458b();
        if (interfaceC0146pMo458b == null || !interfaceC0146pMo458b.mo488a()) {
            return true;
        }
        interfaceC0146pMo458b.dismiss();
        return true;
    }

    /* renamed from: e */
    public void m791e() {
        m790a();
        View view = this.f1033e;
        if (view.isEnabled() && !view.isLongClickable() && mo459c()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.f1036h = true;
        }
    }

    /* renamed from: f */
    public final boolean m792f(MotionEvent motionEvent) throws IllegalAccessException, IllegalArgumentException {
        C0263y c0263y;
        View view = this.f1033e;
        InterfaceC0146p interfaceC0146pMo458b = mo458b();
        if (interfaceC0146pMo458b == null || !interfaceC0146pMo458b.mo488a() || (c0263y = (C0263y) interfaceC0146pMo458b.mo492h()) == null || !c0263y.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        m794i(view, motionEventObtainNoHistory);
        m795j(c0263y, motionEventObtainNoHistory);
        boolean zMo844e = c0263y.mo844e(motionEventObtainNoHistory, this.f1037i);
        motionEventObtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return zMo844e && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m793g(MotionEvent motionEvent) {
        View view = this.f1033e;
        if (!view.isEnabled()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1037i = motionEvent.getPointerId(0);
            if (this.f1034f == null) {
                this.f1034f = new a();
            }
            view.postDelayed(this.f1034f, this.f1031c);
            if (this.f1035g == null) {
                this.f1035g = new b();
            }
            view.postDelayed(this.f1035g, this.f1032d);
        } else if (actionMasked == 1) {
            m790a();
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.f1037i);
            if (iFindPointerIndex >= 0 && !m789h(view, motionEvent.getX(iFindPointerIndex), motionEvent.getY(iFindPointerIndex), this.f1030b)) {
                m790a();
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            }
        } else if (actionMasked == 3) {
        }
        return false;
    }

    /* renamed from: i */
    public final boolean m794i(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f1038j);
        motionEvent.offsetLocation(r0[0], r0[1]);
        return true;
    }

    /* renamed from: j */
    public final boolean m795j(View view, MotionEvent motionEvent) {
        view.getLocationOnScreen(this.f1038j);
        motionEvent.offsetLocation(-r0[0], -r0[1]);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z8;
        boolean z9 = this.f1036h;
        if (z9) {
            z8 = m792f(motionEvent) || !mo632d();
        } else {
            z8 = m793g(motionEvent) && mo459c();
            if (z8) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
                this.f1033e.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.f1036h = z8;
        return z8 || z9;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f1036h = false;
        this.f1037i = -1;
        Runnable runnable = this.f1034f;
        if (runnable != null) {
            this.f1033e.removeCallbacks(runnable);
        }
    }
}
