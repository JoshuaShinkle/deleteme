package com.perfectcorp.ycl.p040bc.widgetpool.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.perfectcorp.utility.C4507b;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class ObservableScrollView extends ScrollView {

    /* renamed from: b */
    public InterfaceC4566a f15992b;

    /* renamed from: c */
    public boolean f15993c;

    /* renamed from: d */
    public boolean f15994d;

    /* renamed from: e */
    public int f15995e;

    /* renamed from: f */
    public OverScroller f15996f;

    /* renamed from: g */
    public boolean f15997g;

    /* renamed from: com.perfectcorp.ycl.bc.widgetpool.common.ObservableScrollView$a */
    public interface InterfaceC4566a {
        /* renamed from: a */
        void mo18145a(ObservableScrollView observableScrollView, int i9);

        /* renamed from: b */
        void mo18146b(ObservableScrollView observableScrollView, int i9, int i10, int i11, int i12);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i9) throws NoSuchFieldException, SecurityException {
        super(context, attributeSet, i9);
        this.f15992b = null;
        this.f15993c = false;
        this.f15994d = false;
        this.f15995e = 0;
        this.f15996f = null;
        this.f15997g = true;
        m18144a();
    }

    /* renamed from: a */
    public final void m18144a() throws NoSuchFieldException, SecurityException {
        try {
            Field declaredField = ScrollView.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.f15996f = (OverScroller) declaredField.get(this);
        } catch (Exception e9) {
            C4507b.m18102c(e9);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.f15994d) {
            return;
        }
        OverScroller overScroller = this.f15996f;
        if (overScroller == null) {
            C4507b.m18101b("mScroller in null");
            return;
        }
        if (overScroller.computeScrollOffset()) {
            if (this.f15995e != 2) {
                C4507b.m18101b("SCROLL_STATE_FLING");
                this.f15995e = 2;
                InterfaceC4566a interfaceC4566a = this.f15992b;
                if (interfaceC4566a != null) {
                    interfaceC4566a.mo18145a(this, 2);
                    return;
                }
                return;
            }
            return;
        }
        if (this.f15995e != 0) {
            C4507b.m18101b("SCROLL_STATE_IDLE");
            this.f15993c = false;
            this.f15995e = 0;
            InterfaceC4566a interfaceC4566a2 = this.f15992b;
            if (interfaceC4566a2 != null) {
                interfaceC4566a2.mo18145a(this, 0);
            }
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.f15997g) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f15994d = true;
        } else if (action == 1) {
            this.f15994d = false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onScrollChanged(int i9, int i10, int i11, int i12) {
        super.onScrollChanged(i9, i10, i11, i12);
        if (this.f15992b != null) {
            if (!this.f15993c) {
                this.f15993c = true;
                C4507b.m18101b("SCROLL_STATE_TOUCH_SCROLL");
                this.f15995e = 1;
                this.f15992b.mo18145a(this, 1);
            }
            this.f15992b.mo18146b(this, i9, i10, i11, i12);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f15997g) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f15994d = true;
        } else if (action == 1) {
            this.f15994d = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnScrollChangeListener(InterfaceC4566a interfaceC4566a) {
        this.f15992b = interfaceC4566a;
    }

    public void setScrollable(boolean z8) {
        this.f15997g = z8;
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) throws NoSuchFieldException, SecurityException {
        super(context, attributeSet);
        this.f15992b = null;
        this.f15993c = false;
        this.f15994d = false;
        this.f15995e = 0;
        this.f15996f = null;
        this.f15997g = true;
        m18144a();
    }
}
