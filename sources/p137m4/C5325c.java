package p137m4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/* renamed from: m4.c */
/* loaded from: classes.dex */
public class C5325c {

    /* renamed from: b */
    public static AbstractC5323a f18146b;

    /* renamed from: a */
    public c f18147a = null;

    /* renamed from: m4.c$a */
    public class a extends c {

        /* renamed from: f */
        public final /* synthetic */ b f18148f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Activity activity, View view, b bVar) {
            super(activity, view);
            this.f18148f = bVar;
        }

        @Override // p137m4.C5325c.c
        /* renamed from: a */
        public void mo20920a() {
            b bVar = this.f18148f;
            if (bVar != null) {
                bVar.mo6997a();
            }
        }

        @Override // p137m4.C5325c.c
        /* renamed from: b */
        public void mo20921b() {
            b bVar = this.f18148f;
            if (bVar != null) {
                bVar.mo6998b();
            }
        }
    }

    /* renamed from: m4.c$b */
    public interface b {
        /* renamed from: a */
        void mo6997a();

        /* renamed from: b */
        void mo6998b();
    }

    /* renamed from: m4.c$c */
    public abstract class c implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: b */
        public final View f18150b;

        /* renamed from: c */
        public final View f18151c;

        /* renamed from: d */
        public final Rect f18152d = new Rect();

        public c(Activity activity, View view) {
            this.f18150b = activity.getWindow().getDecorView();
            this.f18151c = view;
        }

        /* renamed from: a */
        public abstract void mo20920a();

        /* renamed from: b */
        public abstract void mo20921b();

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            DisplayMetrics displayMetrics;
            this.f18150b.getWindowVisibleDisplayFrame(this.f18152d);
            Context context = this.f18150b.getContext();
            if ("Samsung".equalsIgnoreCase(Build.BRAND)) {
                displayMetrics = context.getResources().getDisplayMetrics();
            } else {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                if (windowManager == null) {
                    return;
                }
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics2);
                displayMetrics = displayMetrics2;
            }
            int i9 = displayMetrics.heightPixels - this.f18152d.bottom;
            if (i9 > 0) {
                if (Build.VERSION.SDK_INT < 30) {
                    this.f18151c.setPadding(0, 0, 0, i9);
                }
                mo20921b();
            } else if (this.f18151c.getPaddingBottom() != 0) {
                if (Build.VERSION.SDK_INT < 30) {
                    this.f18151c.setPadding(0, 0, 0, 0);
                }
                mo20920a();
            }
        }
    }

    /* renamed from: m4.c$d */
    public static class d {

        /* renamed from: a */
        public static final C5325c f18154a = new C5325c();
    }

    /* renamed from: a */
    public static C5325c m20917a() {
        return d.f18154a;
    }

    /* renamed from: b */
    public void m20918b(View view, Activity activity, b bVar) {
        if (activity == null) {
            return;
        }
        m20919c(activity);
        this.f18147a = new a(activity, view, bVar);
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.f18147a);
    }

    /* renamed from: c */
    public void m20919c(Activity activity) {
        if (activity != null && this.f18147a != null) {
            activity.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this.f18147a);
            return;
        }
        AbstractC5323a abstractC5323a = f18146b;
        if (abstractC5323a != null) {
            abstractC5323a.m20915a();
            f18146b.m20916b(null);
        }
    }
}
