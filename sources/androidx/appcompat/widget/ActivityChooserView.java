package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.InterfaceC0146p;
import p010b.C0563d;
import p010b.C0565f;
import p010b.C0566g;
import p010b.C0567h;
import p010b.C0569j;
import p042d0.AbstractC4615b;
import p042d0.C4647u;
import p052e0.C4704m;

/* loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {

    /* renamed from: b */
    public final C0173f f755b;

    /* renamed from: c */
    public final ViewOnClickListenerC0174g f756c;

    /* renamed from: d */
    public final View f757d;

    /* renamed from: e */
    public final Drawable f758e;

    /* renamed from: f */
    public final FrameLayout f759f;

    /* renamed from: g */
    public final ImageView f760g;

    /* renamed from: h */
    public final FrameLayout f761h;

    /* renamed from: i */
    public final ImageView f762i;

    /* renamed from: j */
    public final int f763j;

    /* renamed from: k */
    public AbstractC4615b f764k;

    /* renamed from: l */
    public final DataSetObserver f765l;

    /* renamed from: m */
    public final ViewTreeObserver.OnGlobalLayoutListener f766m;

    /* renamed from: n */
    public ListPopupWindow f767n;

    /* renamed from: o */
    public PopupWindow.OnDismissListener f768o;

    /* renamed from: p */
    public boolean f769p;

    /* renamed from: q */
    public int f770q;

    /* renamed from: r */
    public boolean f771r;

    /* renamed from: s */
    public int f772s;

    public static class InnerLayout extends LinearLayout {

        /* renamed from: b */
        public static final int[] f773b = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            C0250q0 c0250q0M1003u = C0250q0.m1003u(context, attributeSet, f773b);
            setBackgroundDrawable(c0250q0M1003u.m1011g(0));
            c0250q0M1003u.m1024w();
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$a */
    public class C0168a extends DataSetObserver {
        public C0168a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.f755b.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f755b.notifyDataSetInvalidated();
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$b */
    public class ViewTreeObserverOnGlobalLayoutListenerC0169b implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserverOnGlobalLayoutListenerC0169b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ActivityChooserView.this.m649b()) {
                if (!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().dismiss();
                    return;
                }
                ActivityChooserView.this.getListPopupWindow().show();
                AbstractC4615b abstractC4615b = ActivityChooserView.this.f764k;
                if (abstractC4615b != null) {
                    abstractC4615b.m18395k(true);
                }
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$c */
    public class C0170c extends View.AccessibilityDelegate {
        public C0170c() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            C4704m.m18763w0(accessibilityNodeInfo).m18778R(true);
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$d */
    public class C0171d extends AbstractViewOnTouchListenerC0218a0 {
        public C0171d(View view) {
            super(view);
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        /* renamed from: b */
        public InterfaceC0146p mo458b() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        /* renamed from: c */
        public boolean mo459c() {
            ActivityChooserView.this.m650c();
            return true;
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        /* renamed from: d */
        public boolean mo632d() {
            ActivityChooserView.this.m648a();
            return true;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$e */
    public class C0172e extends DataSetObserver {
        public C0172e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.m652e();
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$f */
    public class C0173f extends BaseAdapter {

        /* renamed from: b */
        public int f779b = 4;

        /* renamed from: c */
        public boolean f780c;

        /* renamed from: d */
        public boolean f781d;

        /* renamed from: e */
        public boolean f782e;

        public C0173f() {
        }

        /* renamed from: a */
        public int m653a() {
            throw null;
        }

        /* renamed from: b */
        public C0221c m654b() {
            return null;
        }

        /* renamed from: c */
        public ResolveInfo m655c() {
            throw null;
        }

        /* renamed from: d */
        public int m656d() {
            throw null;
        }

        /* renamed from: e */
        public boolean m657e() {
            return this.f780c;
        }

        /* renamed from: f */
        public void m658f(C0221c c0221c) {
            ActivityChooserView.this.f755b.m654b();
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            throw null;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            int itemViewType = getItemViewType(i9);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (this.f780c) {
                throw null;
            }
            throw null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i9) {
            return (this.f782e && i9 == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i9);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(C0566g.abc_activity_chooser_view_list_item, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(C0565f.title)).setText(ActivityChooserView.this.getContext().getString(C0567h.abc_activity_chooser_view_see_all));
                return viewInflate;
            }
            if (view == null || view.getId() != C0565f.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(C0566g.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(C0565f.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i9);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(C0565f.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f780c && i9 == 0 && this.f781d) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActivityChooserView$g */
    public class ViewOnClickListenerC0174g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public ViewOnClickListenerC0174g() {
        }

        /* renamed from: a */
        public final void m659a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.f768o;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.f761h) {
                activityChooserView.m648a();
                ActivityChooserView.this.f755b.m655c();
                ActivityChooserView.this.f755b.m654b();
                throw null;
            }
            if (view != activityChooserView.f759f) {
                throw new IllegalArgumentException();
            }
            activityChooserView.f769p = false;
            activityChooserView.m651d(activityChooserView.f770q);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            m659a();
            AbstractC4615b abstractC4615b = ActivityChooserView.this.f764k;
            if (abstractC4615b != null) {
                abstractC4615b.m18395k(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            int itemViewType = ((C0173f) adapterView.getAdapter()).getItemViewType(i9);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.m651d(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.m648a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (!activityChooserView.f769p) {
                activityChooserView.f755b.m657e();
                ActivityChooserView.this.f755b.m654b();
                throw null;
            }
            if (i9 <= 0) {
                return;
            }
            activityChooserView.f755b.m654b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.f761h) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f755b.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.f769p = true;
                activityChooserView2.m651d(activityChooserView2.f770q);
            }
            return true;
        }
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f765l = new C0168a();
        this.f766m = new ViewTreeObserverOnGlobalLayoutListenerC0169b();
        this.f770q = 4;
        int[] iArr = C0569j.ActivityChooserView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i9, 0);
        C4647u.m18528X(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i9, 0);
        this.f770q = typedArrayObtainStyledAttributes.getInt(C0569j.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(C0569j.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(C0566g.abc_activity_chooser_view, (ViewGroup) this, true);
        ViewOnClickListenerC0174g viewOnClickListenerC0174g = new ViewOnClickListenerC0174g();
        this.f756c = viewOnClickListenerC0174g;
        View viewFindViewById = findViewById(C0565f.activity_chooser_view_content);
        this.f757d = viewFindViewById;
        this.f758e = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(C0565f.default_activity_button);
        this.f761h = frameLayout;
        frameLayout.setOnClickListener(viewOnClickListenerC0174g);
        frameLayout.setOnLongClickListener(viewOnClickListenerC0174g);
        int i10 = C0565f.image;
        this.f762i = (ImageView) frameLayout.findViewById(i10);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(C0565f.expand_activities_button);
        frameLayout2.setOnClickListener(viewOnClickListenerC0174g);
        frameLayout2.setAccessibilityDelegate(new C0170c());
        frameLayout2.setOnTouchListener(new C0171d(frameLayout2));
        this.f759f = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i10);
        this.f760g = imageView;
        imageView.setImageDrawable(drawable);
        C0173f c0173f = new C0173f();
        this.f755b = c0173f;
        c0173f.registerDataSetObserver(new C0172e());
        Resources resources = context.getResources();
        this.f763j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0563d.abc_config_prefDialogWidth));
    }

    /* renamed from: a */
    public boolean m648a() {
        if (!m649b()) {
            return true;
        }
        getListPopupWindow().dismiss();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.f766m);
        return true;
    }

    /* renamed from: b */
    public boolean m649b() {
        return getListPopupWindow().mo488a();
    }

    /* renamed from: c */
    public boolean m650c() {
        if (m649b() || !this.f771r) {
            return false;
        }
        this.f769p = false;
        m651d(this.f770q);
        return true;
    }

    /* renamed from: d */
    public void m651d(int i9) {
        this.f755b.m654b();
        throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }

    /* renamed from: e */
    public void m652e() {
        if (this.f755b.getCount() > 0) {
            this.f759f.setEnabled(true);
        } else {
            this.f759f.setEnabled(false);
        }
        int iM653a = this.f755b.m653a();
        int iM656d = this.f755b.m656d();
        if (iM653a == 1 || (iM653a > 1 && iM656d > 0)) {
            this.f761h.setVisibility(0);
            ResolveInfo resolveInfoM655c = this.f755b.m655c();
            PackageManager packageManager = getContext().getPackageManager();
            this.f762i.setImageDrawable(resolveInfoM655c.loadIcon(packageManager));
            if (this.f772s != 0) {
                this.f761h.setContentDescription(getContext().getString(this.f772s, resolveInfoM655c.loadLabel(packageManager)));
            }
        } else {
            this.f761h.setVisibility(8);
        }
        if (this.f761h.getVisibility() == 0) {
            this.f757d.setBackgroundDrawable(this.f758e);
        } else {
            this.f757d.setBackgroundDrawable(null);
        }
    }

    public C0221c getDataModel() {
        this.f755b.m654b();
        return null;
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.f767n == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.f767n = listPopupWindow;
            listPopupWindow.mo678n(this.f755b);
            this.f767n.m712x(this);
            this.f767n.m690D(true);
            this.f767n.m692F(this.f756c);
            this.f767n.m691E(this.f756c);
        }
        return this.f767n;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f755b.m654b();
        this.f771r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f755b.m654b();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f766m);
        }
        if (m649b()) {
            m648a();
        }
        this.f771r = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        this.f757d.layout(0, 0, i11 - i9, i12 - i10);
        if (m649b()) {
            return;
        }
        m648a();
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        View view = this.f757d;
        if (this.f761h.getVisibility() != 0) {
            i10 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i10), 1073741824);
        }
        measureChild(view, i9, i10);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(C0221c c0221c) {
        this.f755b.m658f(c0221c);
        if (m649b()) {
            m648a();
            m650c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i9) {
        this.f772s = i9;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i9) {
        this.f760g.setContentDescription(getContext().getString(i9));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f760g.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i9) {
        this.f770q = i9;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f768o = onDismissListener;
    }

    public void setProvider(AbstractC4615b abstractC4615b) {
        this.f764k = abstractC4615b;
    }
}
