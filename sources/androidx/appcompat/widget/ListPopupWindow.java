package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.InterfaceC0146p;
import androidx.core.widget.C0333h;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p010b.C0560a;
import p010b.C0569j;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class ListPopupWindow implements InterfaceC0146p {

    /* renamed from: H */
    public static Method f851H;

    /* renamed from: I */
    public static Method f852I;

    /* renamed from: A */
    public final RunnableC0186c f853A;

    /* renamed from: B */
    public Runnable f854B;

    /* renamed from: C */
    public final Handler f855C;

    /* renamed from: D */
    public final Rect f856D;

    /* renamed from: E */
    public Rect f857E;

    /* renamed from: F */
    public boolean f858F;

    /* renamed from: G */
    public PopupWindow f859G;

    /* renamed from: b */
    public Context f860b;

    /* renamed from: c */
    public ListAdapter f861c;

    /* renamed from: d */
    public C0263y f862d;

    /* renamed from: e */
    public int f863e;

    /* renamed from: f */
    public int f864f;

    /* renamed from: g */
    public int f865g;

    /* renamed from: h */
    public int f866h;

    /* renamed from: i */
    public int f867i;

    /* renamed from: j */
    public boolean f868j;

    /* renamed from: k */
    public boolean f869k;

    /* renamed from: l */
    public boolean f870l;

    /* renamed from: m */
    public int f871m;

    /* renamed from: n */
    public boolean f872n;

    /* renamed from: o */
    public boolean f873o;

    /* renamed from: p */
    public int f874p;

    /* renamed from: q */
    public View f875q;

    /* renamed from: r */
    public int f876r;

    /* renamed from: s */
    public DataSetObserver f877s;

    /* renamed from: t */
    public View f878t;

    /* renamed from: u */
    public Drawable f879u;

    /* renamed from: v */
    public AdapterView.OnItemClickListener f880v;

    /* renamed from: w */
    public AdapterView.OnItemSelectedListener f881w;

    /* renamed from: x */
    public final RunnableC0190g f882x;

    /* renamed from: y */
    public final ViewOnTouchListenerC0189f f883y;

    /* renamed from: z */
    public final C0188e f884z;

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$a */
    public class RunnableC0184a implements Runnable {
        public RunnableC0184a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewM706r = ListPopupWindow.this.m706r();
            if (viewM706r == null || viewM706r.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.show();
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$b */
    public class C0185b implements AdapterView.OnItemSelectedListener {
        public C0185b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i9, long j9) {
            C0263y c0263y;
            if (i9 == -1 || (c0263y = ListPopupWindow.this.f862d) == null) {
                return;
            }
            c0263y.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$c */
    public class RunnableC0186c implements Runnable {
        public RunnableC0186c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.m704p();
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$d */
    public class C0187d extends DataSetObserver {
        public C0187d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.mo488a()) {
                ListPopupWindow.this.show();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$e */
    public class C0188e implements AbsListView.OnScrollListener {
        public C0188e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 != 1 || ListPopupWindow.this.m709u() || ListPopupWindow.this.f859G.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.f855C.removeCallbacks(listPopupWindow.f882x);
            ListPopupWindow.this.f882x.run();
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$f */
    public class ViewOnTouchListenerC0189f implements View.OnTouchListener {
        public ViewOnTouchListenerC0189f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x8 = (int) motionEvent.getX();
            int y8 = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.f859G) != null && popupWindow.isShowing() && x8 >= 0 && x8 < ListPopupWindow.this.f859G.getWidth() && y8 >= 0 && y8 < ListPopupWindow.this.f859G.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.f855C.postDelayed(listPopupWindow.f882x, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.f855C.removeCallbacks(listPopupWindow2.f882x);
            return false;
        }
    }

    /* renamed from: androidx.appcompat.widget.ListPopupWindow$g */
    public class RunnableC0190g implements Runnable {
        public RunnableC0190g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0263y c0263y = ListPopupWindow.this.f862d;
            if (c0263y == null || !C4647u.m18512H(c0263y) || ListPopupWindow.this.f862d.getCount() <= ListPopupWindow.this.f862d.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.f862d.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.f874p) {
                listPopupWindow.f859G.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f851H = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f852I = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, C0560a.listPopupWindowStyle);
    }

    /* renamed from: A */
    public void m687A(int i9) {
        this.f871m = i9;
    }

    /* renamed from: B */
    public void m688B(Rect rect) {
        this.f857E = rect != null ? new Rect(rect) : null;
    }

    /* renamed from: C */
    public void m689C(int i9) {
        this.f859G.setInputMethodMode(i9);
    }

    /* renamed from: D */
    public void m690D(boolean z8) {
        this.f858F = z8;
        this.f859G.setFocusable(z8);
    }

    /* renamed from: E */
    public void m691E(PopupWindow.OnDismissListener onDismissListener) {
        this.f859G.setOnDismissListener(onDismissListener);
    }

    /* renamed from: F */
    public void m692F(AdapterView.OnItemClickListener onItemClickListener) {
        this.f880v = onItemClickListener;
    }

    /* renamed from: G */
    public void m693G(boolean z8) {
        this.f870l = true;
        this.f869k = z8;
    }

    /* renamed from: H */
    public final void m694H(boolean z8) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Build.VERSION.SDK_INT > 28) {
            this.f859G.setIsClippedToScreen(z8);
            return;
        }
        Method method = f851H;
        if (method != null) {
            try {
                method.invoke(this.f859G, Boolean.valueOf(z8));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    /* renamed from: I */
    public void m695I(int i9) {
        this.f876r = i9;
    }

    /* renamed from: J */
    public void m696J(int i9) {
        C0263y c0263y = this.f862d;
        if (!mo488a() || c0263y == null) {
            return;
        }
        c0263y.setListSelectionHidden(false);
        c0263y.setSelection(i9);
        if (c0263y.getChoiceMode() != 0) {
            c0263y.setItemChecked(i9, true);
        }
    }

    /* renamed from: K */
    public void m697K(int i9) {
        this.f864f = i9;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    /* renamed from: a */
    public boolean mo488a() {
        return this.f859G.isShowing();
    }

    /* renamed from: b */
    public int m698b() {
        return this.f865g;
    }

    /* renamed from: d */
    public void m699d(int i9) {
        this.f865g = i9;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    public void dismiss() {
        this.f859G.dismiss();
        m711w();
        this.f859G.setContentView(null);
        this.f862d = null;
        this.f855C.removeCallbacks(this.f882x);
    }

    /* renamed from: g */
    public Drawable m700g() {
        return this.f859G.getBackground();
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    /* renamed from: h */
    public ListView mo492h() {
        return this.f862d;
    }

    /* renamed from: j */
    public void m701j(int i9) {
        this.f866h = i9;
        this.f868j = true;
    }

    /* renamed from: m */
    public int m702m() {
        if (this.f868j) {
            return this.f866h;
        }
        return 0;
    }

    /* renamed from: n */
    public void mo678n(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f877s;
        if (dataSetObserver == null) {
            this.f877s = new C0187d();
        } else {
            ListAdapter listAdapter2 = this.f861c;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f861c = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f877s);
        }
        C0263y c0263y = this.f862d;
        if (c0263y != null) {
            c0263y.setAdapter(this.f861c);
        }
    }

    /* renamed from: o */
    public final int m703o() {
        int measuredHeight;
        int i9;
        int iMakeMeasureSpec;
        int i10;
        if (this.f862d == null) {
            Context context = this.f860b;
            this.f854B = new RunnableC0184a();
            C0263y c0263yMo705q = mo705q(context, !this.f858F);
            this.f862d = c0263yMo705q;
            Drawable drawable = this.f879u;
            if (drawable != null) {
                c0263yMo705q.setSelector(drawable);
            }
            this.f862d.setAdapter(this.f861c);
            this.f862d.setOnItemClickListener(this.f880v);
            this.f862d.setFocusable(true);
            this.f862d.setFocusableInTouchMode(true);
            this.f862d.setOnItemSelectedListener(new C0185b());
            this.f862d.setOnScrollListener(this.f884z);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.f881w;
            if (onItemSelectedListener != null) {
                this.f862d.setOnItemSelectedListener(onItemSelectedListener);
            }
            View view = this.f862d;
            View view2 = this.f875q;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i11 = this.f876r;
                if (i11 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(view, layoutParams);
                } else if (i11 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.f876r);
                } else {
                    linearLayout.addView(view, layoutParams);
                    linearLayout.addView(view2);
                }
                int i12 = this.f864f;
                if (i12 >= 0) {
                    i10 = Integer.MIN_VALUE;
                } else {
                    i12 = 0;
                    i10 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i12, i10), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
            }
            this.f859G.setContentView(view);
        } else {
            View view3 = this.f875q;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.f859G.getBackground();
        if (background != null) {
            background.getPadding(this.f856D);
            Rect rect = this.f856D;
            int i13 = rect.top;
            i9 = rect.bottom + i13;
            if (!this.f868j) {
                this.f866h = -i13;
            }
        } else {
            this.f856D.setEmpty();
            i9 = 0;
        }
        int iM707s = m707s(m706r(), this.f866h, this.f859G.getInputMethodMode() == 2);
        if (this.f872n || this.f863e == -1) {
            return iM707s + i9;
        }
        int i14 = this.f864f;
        if (i14 == -2) {
            int i15 = this.f860b.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.f856D;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i15 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i14 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i14, 1073741824);
        } else {
            int i16 = this.f860b.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.f856D;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i16 - (rect3.left + rect3.right), 1073741824);
        }
        int iMo843d = this.f862d.mo843d(iMakeMeasureSpec, 0, -1, iM707s - measuredHeight, -1);
        if (iMo843d > 0) {
            measuredHeight += i9 + this.f862d.getPaddingTop() + this.f862d.getPaddingBottom();
        }
        return iMo843d + measuredHeight;
    }

    /* renamed from: p */
    public void m704p() {
        C0263y c0263y = this.f862d;
        if (c0263y != null) {
            c0263y.setListSelectionHidden(true);
            c0263y.requestLayout();
        }
    }

    /* renamed from: q */
    public C0263y mo705q(Context context, boolean z8) {
        return new C0263y(context, z8);
    }

    /* renamed from: r */
    public View m706r() {
        return this.f878t;
    }

    /* renamed from: s */
    public final int m707s(View view, int i9, boolean z8) {
        return this.f859G.getMaxAvailableHeight(view, i9, z8);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.f859G.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0146p
    public void show() {
        int iM703o = m703o();
        boolean zM709u = m709u();
        C0333h.m1601b(this.f859G, this.f867i);
        if (this.f859G.isShowing()) {
            if (C4647u.m18512H(m706r())) {
                int width = this.f864f;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = m706r().getWidth();
                }
                int i9 = this.f863e;
                if (i9 == -1) {
                    if (!zM709u) {
                        iM703o = -1;
                    }
                    if (zM709u) {
                        this.f859G.setWidth(this.f864f == -1 ? -1 : 0);
                        this.f859G.setHeight(0);
                    } else {
                        this.f859G.setWidth(this.f864f == -1 ? -1 : 0);
                        this.f859G.setHeight(-1);
                    }
                } else if (i9 != -2) {
                    iM703o = i9;
                }
                this.f859G.setOutsideTouchable((this.f873o || this.f872n) ? false : true);
                this.f859G.update(m706r(), this.f865g, this.f866h, width < 0 ? -1 : width, iM703o < 0 ? -1 : iM703o);
                return;
            }
            return;
        }
        int width2 = this.f864f;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = m706r().getWidth();
        }
        int i10 = this.f863e;
        if (i10 == -1) {
            iM703o = -1;
        } else if (i10 != -2) {
            iM703o = i10;
        }
        this.f859G.setWidth(width2);
        this.f859G.setHeight(iM703o);
        m694H(true);
        this.f859G.setOutsideTouchable((this.f873o || this.f872n) ? false : true);
        this.f859G.setTouchInterceptor(this.f883y);
        if (this.f870l) {
            C0333h.m1600a(this.f859G, this.f869k);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = f852I;
            if (method != null) {
                try {
                    method.invoke(this.f859G, this.f857E);
                } catch (Exception e9) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e9);
                }
            }
        } else {
            this.f859G.setEpicenterBounds(this.f857E);
        }
        C0333h.m1602c(this.f859G, m706r(), this.f865g, this.f866h, this.f871m);
        this.f862d.setSelection(-1);
        if (!this.f858F || this.f862d.isInTouchMode()) {
            m704p();
        }
        if (this.f858F) {
            return;
        }
        this.f855C.post(this.f853A);
    }

    /* renamed from: t */
    public int m708t() {
        return this.f864f;
    }

    /* renamed from: u */
    public boolean m709u() {
        return this.f859G.getInputMethodMode() == 2;
    }

    /* renamed from: v */
    public boolean m710v() {
        return this.f858F;
    }

    /* renamed from: w */
    public final void m711w() {
        View view = this.f875q;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f875q);
            }
        }
    }

    /* renamed from: x */
    public void m712x(View view) {
        this.f878t = view;
    }

    /* renamed from: y */
    public void m713y(int i9) {
        this.f859G.setAnimationStyle(i9);
    }

    /* renamed from: z */
    public void m714z(int i9) {
        Drawable background = this.f859G.getBackground();
        if (background == null) {
            m697K(i9);
            return;
        }
        background.getPadding(this.f856D);
        Rect rect = this.f856D;
        this.f864f = rect.left + rect.right + i9;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i9) {
        this(context, attributeSet, i9, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i9, int i10) {
        this.f863e = -2;
        this.f864f = -2;
        this.f867i = CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE;
        this.f871m = 0;
        this.f872n = false;
        this.f873o = false;
        this.f874p = Integer.MAX_VALUE;
        this.f876r = 0;
        this.f882x = new RunnableC0190g();
        this.f883y = new ViewOnTouchListenerC0189f();
        this.f884z = new C0188e();
        this.f853A = new RunnableC0186c();
        this.f856D = new Rect();
        this.f860b = context;
        this.f855C = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.ListPopupWindow, i9, i10);
        this.f865g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0569j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(C0569j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.f866h = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f868j = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i9, i10);
        this.f859G = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }
}
