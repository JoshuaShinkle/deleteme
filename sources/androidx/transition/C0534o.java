package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import p042d0.C4647u;
import p132m.C5302a;

/* renamed from: androidx.transition.o */
/* loaded from: classes.dex */
public class C0534o {

    /* renamed from: a */
    public static AbstractC0532m f2947a = new C0513b();

    /* renamed from: b */
    public static ThreadLocal<WeakReference<C5302a<ViewGroup, ArrayList<AbstractC0532m>>>> f2948b = new ThreadLocal<>();

    /* renamed from: c */
    public static ArrayList<ViewGroup> f2949c = new ArrayList<>();

    /* renamed from: androidx.transition.o$a */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* renamed from: b */
        public AbstractC0532m f2950b;

        /* renamed from: c */
        public ViewGroup f2951c;

        /* renamed from: androidx.transition.o$a$a, reason: collision with other inner class name */
        public class C6840a extends C0533n {

            /* renamed from: a */
            public final /* synthetic */ C5302a f2952a;

            public C6840a(C5302a c5302a) {
                this.f2952a = c5302a;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.transition.AbstractC0532m.g
            /* renamed from: c */
            public void mo3051c(AbstractC0532m abstractC0532m) {
                ((ArrayList) this.f2952a.get(a.this.f2951c)).remove(abstractC0532m);
            }
        }

        public a(AbstractC0532m abstractC0532m, ViewGroup viewGroup) {
            this.f2950b = abstractC0532m;
            this.f2951c = viewGroup;
        }

        /* renamed from: a */
        public final void m3095a() {
            this.f2951c.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f2951c.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            m3095a();
            if (!C0534o.f2949c.remove(this.f2951c)) {
                return true;
            }
            C5302a<ViewGroup, ArrayList<AbstractC0532m>> c5302aM3092b = C0534o.m3092b();
            ArrayList<AbstractC0532m> arrayList = c5302aM3092b.get(this.f2951c);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                c5302aM3092b.put(this.f2951c, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f2950b);
            this.f2950b.addListener(new C6840a(c5302aM3092b));
            this.f2950b.captureValues(this.f2951c, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((AbstractC0532m) it.next()).resume(this.f2951c);
                }
            }
            this.f2950b.playTransition(this.f2951c);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            m3095a();
            C0534o.f2949c.remove(this.f2951c);
            ArrayList<AbstractC0532m> arrayList = C0534o.m3092b().get(this.f2951c);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<AbstractC0532m> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().resume(this.f2951c);
                }
            }
            this.f2950b.clearValues(true);
        }
    }

    /* renamed from: a */
    public static void m3091a(ViewGroup viewGroup, AbstractC0532m abstractC0532m) {
        if (f2949c.contains(viewGroup) || !C4647u.m18513I(viewGroup)) {
            return;
        }
        f2949c.add(viewGroup);
        if (abstractC0532m == null) {
            abstractC0532m = f2947a;
        }
        AbstractC0532m abstractC0532mMo25565clone = abstractC0532m.mo25565clone();
        m3094d(viewGroup, abstractC0532mMo25565clone);
        C0530k.m3087c(viewGroup, null);
        m3093c(viewGroup, abstractC0532mMo25565clone);
    }

    /* renamed from: b */
    public static C5302a<ViewGroup, ArrayList<AbstractC0532m>> m3092b() {
        C5302a<ViewGroup, ArrayList<AbstractC0532m>> c5302a;
        WeakReference<C5302a<ViewGroup, ArrayList<AbstractC0532m>>> weakReference = f2948b.get();
        if (weakReference != null && (c5302a = weakReference.get()) != null) {
            return c5302a;
        }
        C5302a<ViewGroup, ArrayList<AbstractC0532m>> c5302a2 = new C5302a<>();
        f2948b.set(new WeakReference<>(c5302a2));
        return c5302a2;
    }

    /* renamed from: c */
    public static void m3093c(ViewGroup viewGroup, AbstractC0532m abstractC0532m) {
        if (abstractC0532m == null || viewGroup == null) {
            return;
        }
        a aVar = new a(abstractC0532m, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    /* renamed from: d */
    public static void m3094d(ViewGroup viewGroup, AbstractC0532m abstractC0532m) {
        ArrayList<AbstractC0532m> arrayList = m3092b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<AbstractC0532m> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().pause(viewGroup);
            }
        }
        if (abstractC0532m != null) {
            abstractC0532m.captureValues(viewGroup, true);
        }
        C0530k c0530kM3086b = C0530k.m3086b(viewGroup);
        if (c0530kM3086b != null) {
            c0530kM3086b.m3088a();
        }
    }
}
