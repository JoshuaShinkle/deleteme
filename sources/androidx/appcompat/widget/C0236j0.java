package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AbstractC0119a;
import androidx.appcompat.widget.LinearLayoutCompat;
import p010b.C0560a;
import p071g.C4795a;

/* renamed from: androidx.appcompat.widget.j0 */
/* loaded from: classes.dex */
public class C0236j0 extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* renamed from: k */
    public static final Interpolator f1101k = new DecelerateInterpolator();

    /* renamed from: b */
    public Runnable f1102b;

    /* renamed from: c */
    public c f1103c;

    /* renamed from: d */
    public LinearLayoutCompat f1104d;

    /* renamed from: e */
    public Spinner f1105e;

    /* renamed from: f */
    public boolean f1106f;

    /* renamed from: g */
    public int f1107g;

    /* renamed from: h */
    public int f1108h;

    /* renamed from: i */
    public int f1109i;

    /* renamed from: j */
    public int f1110j;

    /* renamed from: androidx.appcompat.widget.j0$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ View f1111b;

        public a(View view) {
            this.f1111b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            C0236j0.this.smoothScrollTo(this.f1111b.getLeft() - ((C0236j0.this.getWidth() - this.f1111b.getWidth()) / 2), 0);
            C0236j0.this.f1102b = null;
        }
    }

    /* renamed from: androidx.appcompat.widget.j0$b */
    public class b extends BaseAdapter {
        public b() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return C0236j0.this.f1104d.getChildCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            return ((d) C0236j0.this.f1104d.getChildAt(i9)).m907b();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            if (view == null) {
                return C0236j0.this.m902c((AbstractC0119a.c) getItem(i9), true);
            }
            ((d) view).m906a((AbstractC0119a.c) getItem(i9));
            return view;
        }
    }

    /* renamed from: androidx.appcompat.widget.j0$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((d) view).m907b().m387e();
            int childCount = C0236j0.this.f1104d.getChildCount();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = C0236j0.this.f1104d.getChildAt(i9);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.j0$d */
    public class d extends LinearLayout {

        /* renamed from: b */
        public final int[] f1115b;

        /* renamed from: c */
        public AbstractC0119a.c f1116c;

        /* renamed from: d */
        public TextView f1117d;

        /* renamed from: e */
        public ImageView f1118e;

        /* renamed from: f */
        public View f1119f;

        /* JADX WARN: Illegal instructions before constructor call */
        public d(Context context, AbstractC0119a.c cVar, boolean z8) {
            int i9 = C0560a.actionBarTabStyle;
            super(context, null, i9);
            int[] iArr = {R.attr.background};
            this.f1115b = iArr;
            this.f1116c = cVar;
            C0250q0 c0250q0M1004v = C0250q0.m1004v(context, null, iArr, i9, 0);
            if (c0250q0M1004v.m1023s(0)) {
                setBackgroundDrawable(c0250q0M1004v.m1011g(0));
            }
            c0250q0M1004v.m1024w();
            if (z8) {
                setGravity(8388627);
            }
            m908c();
        }

        /* renamed from: a */
        public void m906a(AbstractC0119a.c cVar) {
            this.f1116c = cVar;
            m908c();
        }

        /* renamed from: b */
        public AbstractC0119a.c m907b() {
            return this.f1116c;
        }

        /* renamed from: c */
        public void m908c() {
            AbstractC0119a.c cVar = this.f1116c;
            View viewM384b = cVar.m384b();
            if (viewM384b != null) {
                ViewParent parent = viewM384b.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewM384b);
                    }
                    addView(viewM384b);
                }
                this.f1119f = viewM384b;
                TextView textView = this.f1117d;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f1118e;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f1118e.setImageDrawable(null);
                    return;
                }
                return;
            }
            View view = this.f1119f;
            if (view != null) {
                removeView(view);
                this.f1119f = null;
            }
            Drawable drawableM385c = cVar.m385c();
            CharSequence charSequenceM386d = cVar.m386d();
            if (drawableM385c != null) {
                if (this.f1118e == null) {
                    AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    appCompatImageView.setLayoutParams(layoutParams);
                    addView(appCompatImageView, 0);
                    this.f1118e = appCompatImageView;
                }
                this.f1118e.setImageDrawable(drawableM385c);
                this.f1118e.setVisibility(0);
            } else {
                ImageView imageView2 = this.f1118e;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f1118e.setImageDrawable(null);
                }
            }
            boolean z8 = !TextUtils.isEmpty(charSequenceM386d);
            if (z8) {
                if (this.f1117d == null) {
                    AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), null, C0560a.actionBarTabTextStyle);
                    appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    appCompatTextView.setLayoutParams(layoutParams2);
                    addView(appCompatTextView);
                    this.f1117d = appCompatTextView;
                }
                this.f1117d.setText(charSequenceM386d);
                this.f1117d.setVisibility(0);
            } else {
                TextView textView2 = this.f1117d;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f1117d.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f1118e;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.m383a());
            }
            C0254s0.m1061a(this, z8 ? null : cVar.m383a());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i9, int i10) {
            super.onMeasure(i9, i10);
            if (C0236j0.this.f1107g > 0) {
                int measuredWidth = getMeasuredWidth();
                int i11 = C0236j0.this.f1107g;
                if (measuredWidth > i11) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), i10);
                }
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z8) {
            boolean z9 = isSelected() != z8;
            super.setSelected(z8);
            if (z9 && z8) {
                sendAccessibilityEvent(4);
            }
        }
    }

    /* renamed from: a */
    public void m900a(int i9) {
        View childAt = this.f1104d.getChildAt(i9);
        Runnable runnable = this.f1102b;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        a aVar = new a(childAt);
        this.f1102b = aVar;
        post(aVar);
    }

    /* renamed from: b */
    public final Spinner m901b() {
        AppCompatSpinner appCompatSpinner = new AppCompatSpinner(getContext(), null, C0560a.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.C0183a(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    /* renamed from: c */
    public d m902c(AbstractC0119a.c cVar, boolean z8) {
        d dVar = new d(getContext(), cVar, z8);
        if (z8) {
            dVar.setBackgroundDrawable(null);
            dVar.setLayoutParams(new AbsListView.LayoutParams(-1, this.f1109i));
        } else {
            dVar.setFocusable(true);
            if (this.f1103c == null) {
                this.f1103c = new c();
            }
            dVar.setOnClickListener(this.f1103c);
        }
        return dVar;
    }

    /* renamed from: d */
    public final boolean m903d() {
        Spinner spinner = this.f1105e;
        return spinner != null && spinner.getParent() == this;
    }

    /* renamed from: e */
    public final void m904e() {
        if (m903d()) {
            return;
        }
        if (this.f1105e == null) {
            this.f1105e = m901b();
        }
        removeView(this.f1104d);
        addView(this.f1105e, new ViewGroup.LayoutParams(-2, -1));
        if (this.f1105e.getAdapter() == null) {
            this.f1105e.setAdapter((SpinnerAdapter) new b());
        }
        Runnable runnable = this.f1102b;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.f1102b = null;
        }
        this.f1105e.setSelection(this.f1110j);
    }

    /* renamed from: f */
    public final boolean m905f() {
        if (!m903d()) {
            return false;
        }
        removeView(this.f1105e);
        addView(this.f1104d, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f1105e.getSelectedItemPosition());
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f1102b;
        if (runnable != null) {
            post(runnable);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C4795a c4795aM19032b = C4795a.m19032b(getContext());
        setContentHeight(c4795aM19032b.m19037f());
        this.f1108h = c4795aM19032b.m19036e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f1102b;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i9, long j9) {
        ((d) view).m907b().m387e();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        int mode = View.MeasureSpec.getMode(i9);
        boolean z8 = mode == 1073741824;
        setFillViewport(z8);
        int childCount = this.f1104d.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f1107g = -1;
        } else {
            if (childCount > 2) {
                this.f1107g = (int) (View.MeasureSpec.getSize(i9) * 0.4f);
            } else {
                this.f1107g = View.MeasureSpec.getSize(i9) / 2;
            }
            this.f1107g = Math.min(this.f1107g, this.f1108h);
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f1109i, 1073741824);
        if (!z8 && this.f1106f) {
            this.f1104d.measure(0, iMakeMeasureSpec);
            if (this.f1104d.getMeasuredWidth() > View.MeasureSpec.getSize(i9)) {
                m904e();
            } else {
                m905f();
            }
        } else {
            m905f();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i9, iMakeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z8 || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.f1110j);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z8) {
        this.f1106f = z8;
    }

    public void setContentHeight(int i9) {
        this.f1109i = i9;
        requestLayout();
    }

    public void setTabSelected(int i9) {
        this.f1110j = i9;
        int childCount = this.f1104d.getChildCount();
        int i10 = 0;
        while (i10 < childCount) {
            View childAt = this.f1104d.getChildAt(i10);
            boolean z8 = i10 == i9;
            childAt.setSelected(z8);
            if (z8) {
                m900a(i9);
            }
            i10++;
        }
        Spinner spinner = this.f1105e;
        if (spinner == null || i9 < 0) {
            return;
        }
        spinner.setSelection(i9);
    }
}
