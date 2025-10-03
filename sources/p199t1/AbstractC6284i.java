package p199t1;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p190s1.InterfaceC6246c;
import p226w1.C6516i;

@Deprecated
/* renamed from: t1.i */
/* loaded from: classes.dex */
public abstract class AbstractC6284i<T extends View, Z> extends AbstractC6276a<Z> {

    /* renamed from: h */
    public static boolean f21182h;

    /* renamed from: i */
    public static Integer f21183i;

    /* renamed from: c */
    public final T f21184c;

    /* renamed from: d */
    public final a f21185d;

    /* renamed from: e */
    public View.OnAttachStateChangeListener f21186e;

    /* renamed from: f */
    public boolean f21187f;

    /* renamed from: g */
    public boolean f21188g;

    /* renamed from: t1.i$a */
    public static final class a {

        /* renamed from: e */
        public static Integer f21189e;

        /* renamed from: a */
        public final View f21190a;

        /* renamed from: b */
        public final List<InterfaceC6282g> f21191b = new ArrayList();

        /* renamed from: c */
        public boolean f21192c;

        /* renamed from: d */
        public ViewTreeObserverOnPreDrawListenerC6883a f21193d;

        /* renamed from: t1.i$a$a, reason: collision with other inner class name */
        public static final class ViewTreeObserverOnPreDrawListenerC6883a implements ViewTreeObserver.OnPreDrawListener {

            /* renamed from: b */
            public final WeakReference<a> f21194b;

            public ViewTreeObserverOnPreDrawListenerC6883a(a aVar) {
                this.f21194b = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable("ViewTarget", 2)) {
                    Log.v("ViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                a aVar = this.f21194b.get();
                if (aVar == null) {
                    return true;
                }
                aVar.m24060a();
                return true;
            }
        }

        public a(View view) {
            this.f21190a = view;
        }

        /* renamed from: c */
        public static int m24059c(Context context) {
            if (f21189e == null) {
                Display defaultDisplay = ((WindowManager) C6516i.m24938d((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                f21189e = Integer.valueOf(Math.max(point.x, point.y));
            }
            return f21189e.intValue();
        }

        /* renamed from: a */
        public void m24060a() {
            if (this.f21191b.isEmpty()) {
                return;
            }
            int iM24065g = m24065g();
            int iM24064f = m24064f();
            if (m24067i(iM24065g, iM24064f)) {
                m24068j(iM24065g, iM24064f);
                m24061b();
            }
        }

        /* renamed from: b */
        public void m24061b() {
            ViewTreeObserver viewTreeObserver = this.f21190a.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f21193d);
            }
            this.f21193d = null;
            this.f21191b.clear();
        }

        /* renamed from: d */
        public void m24062d(InterfaceC6282g interfaceC6282g) {
            int iM24065g = m24065g();
            int iM24064f = m24064f();
            if (m24067i(iM24065g, iM24064f)) {
                interfaceC6282g.mo4012e(iM24065g, iM24064f);
                return;
            }
            if (!this.f21191b.contains(interfaceC6282g)) {
                this.f21191b.add(interfaceC6282g);
            }
            if (this.f21193d == null) {
                ViewTreeObserver viewTreeObserver = this.f21190a.getViewTreeObserver();
                ViewTreeObserverOnPreDrawListenerC6883a viewTreeObserverOnPreDrawListenerC6883a = new ViewTreeObserverOnPreDrawListenerC6883a(this);
                this.f21193d = viewTreeObserverOnPreDrawListenerC6883a;
                viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC6883a);
            }
        }

        /* renamed from: e */
        public final int m24063e(int i9, int i10, int i11) {
            int i12 = i10 - i11;
            if (i12 > 0) {
                return i12;
            }
            if (this.f21192c && this.f21190a.isLayoutRequested()) {
                return 0;
            }
            int i13 = i9 - i11;
            if (i13 > 0) {
                return i13;
            }
            if (this.f21190a.isLayoutRequested() || i10 != -2) {
                return 0;
            }
            if (Log.isLoggable("ViewTarget", 4)) {
                Log.i("ViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return m24059c(this.f21190a.getContext());
        }

        /* renamed from: f */
        public final int m24064f() {
            int paddingTop = this.f21190a.getPaddingTop() + this.f21190a.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.f21190a.getLayoutParams();
            return m24063e(this.f21190a.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        /* renamed from: g */
        public final int m24065g() {
            int paddingLeft = this.f21190a.getPaddingLeft() + this.f21190a.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.f21190a.getLayoutParams();
            return m24063e(this.f21190a.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        /* renamed from: h */
        public final boolean m24066h(int i9) {
            return i9 > 0 || i9 == Integer.MIN_VALUE;
        }

        /* renamed from: i */
        public final boolean m24067i(int i9, int i10) {
            return m24066h(i9) && m24066h(i10);
        }

        /* renamed from: j */
        public final void m24068j(int i9, int i10) {
            Iterator it = new ArrayList(this.f21191b).iterator();
            while (it.hasNext()) {
                ((InterfaceC6282g) it.next()).mo4012e(i9, i10);
            }
        }

        /* renamed from: k */
        public void m24069k(InterfaceC6282g interfaceC6282g) {
            this.f21191b.remove(interfaceC6282g);
        }
    }

    public AbstractC6284i(T t8) {
        this.f21184c = (T) C6516i.m24938d(t8);
        this.f21185d = new a(t8);
    }

    @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: a */
    public void mo23898a(InterfaceC6246c interfaceC6246c) {
        m24058l(interfaceC6246c);
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: b */
    public void mo23899b(InterfaceC6282g interfaceC6282g) {
        this.f21185d.m24069k(interfaceC6282g);
    }

    /* renamed from: c */
    public final Object m24055c() {
        Integer num = f21183i;
        return num == null ? this.f21184c.getTag() : this.f21184c.getTag(num.intValue());
    }

    /* renamed from: d */
    public final void m24056d() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.f21186e;
        if (onAttachStateChangeListener == null || this.f21188g) {
            return;
        }
        this.f21184c.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.f21188g = true;
    }

    @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: g */
    public void mo23900g(Drawable drawable) {
        super.mo23900g(drawable);
        m24056d();
    }

    @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: h */
    public InterfaceC6246c mo23901h() {
        Object objM24055c = m24055c();
        if (objM24055c == null) {
            return null;
        }
        if (objM24055c instanceof InterfaceC6246c) {
            return (InterfaceC6246c) objM24055c;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: i */
    public void mo23902i(Drawable drawable) {
        super.mo23902i(drawable);
        this.f21185d.m24061b();
        if (this.f21187f) {
            return;
        }
        m24057k();
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: j */
    public void mo23903j(InterfaceC6282g interfaceC6282g) {
        this.f21185d.m24062d(interfaceC6282g);
    }

    /* renamed from: k */
    public final void m24057k() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.f21186e;
        if (onAttachStateChangeListener == null || !this.f21188g) {
            return;
        }
        this.f21184c.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        this.f21188g = false;
    }

    /* renamed from: l */
    public final void m24058l(Object obj) {
        Integer num = f21183i;
        if (num != null) {
            this.f21184c.setTag(num.intValue(), obj);
        } else {
            f21182h = true;
            this.f21184c.setTag(obj);
        }
    }

    public String toString() {
        return "Target for: " + this.f21184c;
    }
}
