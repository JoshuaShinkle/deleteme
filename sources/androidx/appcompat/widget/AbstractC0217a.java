package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p010b.C0560a;
import p010b.C0569j;
import p042d0.C4620d0;
import p042d0.C4647u;
import p042d0.InterfaceC4622e0;

/* renamed from: androidx.appcompat.widget.a */
/* loaded from: classes.dex */
public abstract class AbstractC0217a extends ViewGroup {

    /* renamed from: b */
    public final a f1019b;

    /* renamed from: c */
    public final Context f1020c;

    /* renamed from: d */
    public ActionMenuView f1021d;

    /* renamed from: e */
    public ActionMenuPresenter f1022e;

    /* renamed from: f */
    public int f1023f;

    /* renamed from: g */
    public C4620d0 f1024g;

    /* renamed from: h */
    public boolean f1025h;

    /* renamed from: i */
    public boolean f1026i;

    /* renamed from: androidx.appcompat.widget.a$a */
    public class a implements InterfaceC4622e0 {

        /* renamed from: a */
        public boolean f1027a = false;

        /* renamed from: b */
        public int f1028b;

        public a() {
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: a */
        public void mo787a(View view) {
            this.f1027a = true;
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: b */
        public void mo350b(View view) {
            if (this.f1027a) {
                return;
            }
            AbstractC0217a abstractC0217a = AbstractC0217a.this;
            abstractC0217a.f1024g = null;
            AbstractC0217a.super.setVisibility(this.f1028b);
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: c */
        public void mo351c(View view) {
            AbstractC0217a.super.setVisibility(0);
            this.f1027a = false;
        }

        /* renamed from: d */
        public a m788d(C4620d0 c4620d0, int i9) {
            AbstractC0217a.this.f1024g = c4620d0;
            this.f1028b = i9;
            return this;
        }
    }

    public AbstractC0217a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* renamed from: d */
    public static int m784d(int i9, int i10, boolean z8) {
        return z8 ? i9 - i10 : i9 + i10;
    }

    /* renamed from: c */
    public int m785c(View view, int i9, int i10, int i11) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i9, Integer.MIN_VALUE), i10);
        return Math.max(0, (i9 - view.getMeasuredWidth()) - i11);
    }

    /* renamed from: e */
    public int m786e(View view, int i9, int i10, int i11, boolean z8) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i12 = i10 + ((i11 - measuredHeight) / 2);
        if (z8) {
            view.layout(i9 - measuredWidth, i12, i9, measuredHeight + i12);
        } else {
            view.layout(i9, i12, i9 + measuredWidth, measuredHeight + i12);
        }
        return z8 ? -measuredWidth : measuredWidth;
    }

    /* renamed from: f */
    public C4620d0 mo579f(int i9, long j9) {
        C4620d0 c4620d0 = this.f1024g;
        if (c4620d0 != null) {
            c4620d0.m18409b();
        }
        if (i9 != 0) {
            C4620d0 c4620d0M18408a = C4647u.m18533b(this).m18408a(BitmapDescriptorFactory.HUE_RED);
            c4620d0M18408a.m18411d(j9);
            c4620d0M18408a.m18413f(this.f1019b.m788d(c4620d0M18408a, i9));
            return c4620d0M18408a;
        }
        if (getVisibility() != 0) {
            setAlpha(BitmapDescriptorFactory.HUE_RED);
        }
        C4620d0 c4620d0M18408a2 = C4647u.m18533b(this).m18408a(1.0f);
        c4620d0M18408a2.m18411d(j9);
        c4620d0M18408a2.m18413f(this.f1019b.m788d(c4620d0M18408a2, i9));
        return c4620d0M18408a2;
    }

    public int getAnimatedVisibility() {
        return this.f1024g != null ? this.f1019b.f1028b : getVisibility();
    }

    public int getContentHeight() {
        return this.f1023f;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, C0569j.ActionBar, C0560a.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(C0569j.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f1022e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.m628y(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1026i = false;
        }
        if (!this.f1026i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f1026i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f1026i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1025h = false;
        }
        if (!this.f1025h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f1025h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1025h = false;
        }
        return true;
    }

    public void setContentHeight(int i9) {
        this.f1023f = i9;
        requestLayout();
    }

    @Override // android.view.View
    public void setVisibility(int i9) {
        if (i9 != getVisibility()) {
            C4620d0 c4620d0 = this.f1024g;
            if (c4620d0 != null) {
                c4620d0.m18409b();
            }
            super.setVisibility(i9);
        }
    }

    public AbstractC0217a(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f1019b = new a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(C0560a.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f1020c = context;
        } else {
            this.f1020c = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }
}
