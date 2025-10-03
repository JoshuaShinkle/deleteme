package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.core.widget.C0331f;
import java.lang.reflect.Field;
import p010b.C0560a;
import p041d.C4612a;
import p042d0.C4620d0;
import p224w.C6494a;

/* renamed from: androidx.appcompat.widget.y */
/* loaded from: classes.dex */
public class C0263y extends ListView {

    /* renamed from: b */
    public final Rect f1224b;

    /* renamed from: c */
    public int f1225c;

    /* renamed from: d */
    public int f1226d;

    /* renamed from: e */
    public int f1227e;

    /* renamed from: f */
    public int f1228f;

    /* renamed from: g */
    public int f1229g;

    /* renamed from: h */
    public Field f1230h;

    /* renamed from: i */
    public a f1231i;

    /* renamed from: j */
    public boolean f1232j;

    /* renamed from: k */
    public boolean f1233k;

    /* renamed from: l */
    public boolean f1234l;

    /* renamed from: m */
    public C4620d0 f1235m;

    /* renamed from: n */
    public C0331f f1236n;

    /* renamed from: o */
    public b f1237o;

    /* renamed from: androidx.appcompat.widget.y$a */
    public static class a extends C4612a {

        /* renamed from: b */
        public boolean f1238b;

        public a(Drawable drawable) {
            super(drawable);
            this.f1238b = true;
        }

        /* renamed from: a */
        public void m1086a(boolean z8) {
            this.f1238b = z8;
        }

        @Override // p041d.C4612a, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f1238b) {
                super.draw(canvas);
            }
        }

        @Override // p041d.C4612a, android.graphics.drawable.Drawable
        public void setHotspot(float f9, float f10) {
            if (this.f1238b) {
                super.setHotspot(f9, f10);
            }
        }

        @Override // p041d.C4612a, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i9, int i10, int i11, int i12) {
            if (this.f1238b) {
                super.setHotspotBounds(i9, i10, i11, i12);
            }
        }

        @Override // p041d.C4612a, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f1238b) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // p041d.C4612a, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z8, boolean z9) {
            if (this.f1238b) {
                return super.setVisible(z8, z9);
            }
            return false;
        }
    }

    /* renamed from: androidx.appcompat.widget.y$b */
    public class b implements Runnable {
        public b() {
        }

        /* renamed from: a */
        public void m1087a() {
            C0263y c0263y = C0263y.this;
            c0263y.f1237o = null;
            c0263y.removeCallbacks(this);
        }

        /* renamed from: b */
        public void m1088b() {
            C0263y.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            C0263y c0263y = C0263y.this;
            c0263y.f1237o = null;
            c0263y.drawableStateChanged();
        }
    }

    public C0263y(Context context, boolean z8) throws NoSuchFieldException, SecurityException {
        super(context, null, C0560a.dropDownListViewStyle);
        this.f1224b = new Rect();
        this.f1225c = 0;
        this.f1226d = 0;
        this.f1227e = 0;
        this.f1228f = 0;
        this.f1233k = z8;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1230h = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e9) {
            e9.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z8) {
        a aVar = this.f1231i;
        if (aVar != null) {
            aVar.m1086a(z8);
        }
    }

    /* renamed from: a */
    public final void m1077a() {
        this.f1234l = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f1229g - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        C4620d0 c4620d0 = this.f1235m;
        if (c4620d0 != null) {
            c4620d0.m18409b();
            this.f1235m = null;
        }
    }

    /* renamed from: b */
    public final void m1078b(View view, int i9) {
        performItemClick(view, i9, getItemIdAtPosition(i9));
    }

    /* renamed from: c */
    public final void m1079c(Canvas canvas) {
        Drawable selector;
        if (this.f1224b.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f1224b);
        selector.draw(canvas);
    }

    /* renamed from: d */
    public int mo843d(int i9, int i10, int i11, int i12, int i13) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        View view = null;
        while (i14 < count) {
            int itemViewType = adapter.getItemViewType(i14);
            if (itemViewType != i15) {
                view = null;
                i15 = itemViewType;
            }
            view = adapter.getView(i14, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i17 = layoutParams.height;
            view.measure(i9, i17 > 0 ? View.MeasureSpec.makeMeasureSpec(i17, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i14 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i12) {
                return (i13 < 0 || i14 <= i13 || i16 <= 0 || measuredHeight == i12) ? i12 : i16;
            }
            if (i13 >= 0 && i14 >= i13) {
                i16 = measuredHeight;
            }
            i14++;
        }
        return measuredHeight;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        m1079c(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.f1237o != null) {
            return;
        }
        super.drawableStateChanged();
        setSelectorEnabled(true);
        m1085k();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean mo844e(MotionEvent motionEvent, int i9) throws IllegalAccessException, IllegalArgumentException {
        boolean z8;
        boolean z9;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            z8 = false;
        } else {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    z8 = true;
                    z9 = false;
                } else {
                    z9 = false;
                    z8 = false;
                }
                if (z8 || z9) {
                    m1077a();
                }
                if (z8) {
                    C0331f c0331f = this.f1236n;
                    if (c0331f != null) {
                        c0331f.m1567m(false);
                    }
                } else {
                    if (this.f1236n == null) {
                        this.f1236n = new C0331f(this);
                    }
                    this.f1236n.m1567m(true);
                    this.f1236n.onTouch(this, motionEvent);
                }
                return z8;
            }
            z8 = true;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i9);
        if (iFindPointerIndex >= 0) {
            int x8 = (int) motionEvent.getX(iFindPointerIndex);
            int y8 = (int) motionEvent.getY(iFindPointerIndex);
            int iPointToPosition = pointToPosition(x8, y8);
            if (iPointToPosition == -1) {
                z9 = true;
            } else {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                m1083i(childAt, iPointToPosition, x8, y8);
                if (actionMasked == 1) {
                    m1078b(childAt, iPointToPosition);
                }
                z8 = true;
                z9 = false;
            }
        }
        if (z8) {
            m1077a();
        }
        if (z8) {
        }
        return z8;
    }

    /* renamed from: f */
    public final void m1080f(int i9, View view) throws IllegalAccessException, IllegalArgumentException {
        Rect rect = this.f1224b;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1225c;
        rect.top -= this.f1226d;
        rect.right += this.f1227e;
        rect.bottom += this.f1228f;
        try {
            boolean z8 = this.f1230h.getBoolean(this);
            if (view.isEnabled() != z8) {
                this.f1230h.set(this, Boolean.valueOf(!z8));
                if (i9 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: g */
    public final void m1081g(int i9, View view) throws IllegalAccessException, IllegalArgumentException {
        Drawable selector = getSelector();
        boolean z8 = (selector == null || i9 == -1) ? false : true;
        if (z8) {
            selector.setVisible(false, false);
        }
        m1080f(i9, view);
        if (z8) {
            Rect rect = this.f1224b;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            C6494a.m24842e(selector, fExactCenterX, fExactCenterY);
        }
    }

    /* renamed from: h */
    public final void m1082h(int i9, View view, float f9, float f10) throws IllegalAccessException, IllegalArgumentException {
        m1081g(i9, view);
        Drawable selector = getSelector();
        if (selector == null || i9 == -1) {
            return;
        }
        C6494a.m24842e(selector, f9, f10);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f1233k || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f1233k || super.hasWindowFocus();
    }

    /* renamed from: i */
    public final void m1083i(View view, int i9, float f9, float f10) throws IllegalAccessException, IllegalArgumentException {
        View childAt;
        this.f1234l = true;
        drawableHotspotChanged(f9, f10);
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i10 = this.f1229g;
        if (i10 != -1 && (childAt = getChildAt(i10 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f1229g = i9;
        view.drawableHotspotChanged(f9 - view.getLeft(), f10 - view.getTop());
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        m1082h(i9, view, f9, f10);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f1233k || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f1233k && this.f1232j) || super.isInTouchMode();
    }

    /* renamed from: j */
    public final boolean m1084j() {
        return this.f1234l;
    }

    /* renamed from: k */
    public final void m1085k() {
        Drawable selector = getSelector();
        if (selector != null && m1084j() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.f1237o = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f1237o == null) {
            b bVar = new b();
            this.f1237o = bVar;
            bVar.m1088b();
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                }
                m1085k();
            }
        } else {
            setSelection(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1229g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.f1237o;
        if (bVar != null) {
            bVar.m1087a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z8) {
        this.f1232j = z8;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f1231i = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1225c = rect.left;
        this.f1226d = rect.top;
        this.f1227e = rect.right;
        this.f1228f = rect.bottom;
    }
}
