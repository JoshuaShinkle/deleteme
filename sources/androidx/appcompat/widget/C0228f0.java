package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.C0136f;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: androidx.appcompat.widget.f0 */
/* loaded from: classes.dex */
public class C0228f0 extends ListPopupWindow implements InterfaceC0224d0 {

    /* renamed from: K */
    public static Method f1063K;

    /* renamed from: J */
    public InterfaceC0224d0 f1064J;

    /* renamed from: androidx.appcompat.widget.f0$a */
    public static class a extends C0263y {

        /* renamed from: p */
        public final int f1065p;

        /* renamed from: q */
        public final int f1066q;

        /* renamed from: r */
        public InterfaceC0224d0 f1067r;

        /* renamed from: s */
        public MenuItem f1068s;

        public a(Context context, boolean z8) {
            super(context, z8);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.f1065p = 21;
                this.f1066q = 22;
            } else {
                this.f1065p = 22;
                this.f1066q = 21;
            }
        }

        @Override // androidx.appcompat.widget.C0263y
        /* renamed from: d */
        public /* bridge */ /* synthetic */ int mo843d(int i9, int i10, int i11, int i12, int i13) {
            return super.mo843d(i9, i10, i11, i12, i13);
        }

        @Override // androidx.appcompat.widget.C0263y
        /* renamed from: e */
        public /* bridge */ /* synthetic */ boolean mo844e(MotionEvent motionEvent, int i9) {
            return super.mo844e(motionEvent, i9);
        }

        @Override // androidx.appcompat.widget.C0263y, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // androidx.appcompat.widget.C0263y, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // androidx.appcompat.widget.C0263y, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // androidx.appcompat.widget.C0263y, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // androidx.appcompat.widget.C0263y, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            C0136f c0136f;
            int headersCount;
            int iPointToPosition;
            int i9;
            if (this.f1067r != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    c0136f = (C0136f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    c0136f = (C0136f) adapter;
                    headersCount = 0;
                }
                C0139i item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i9 = iPointToPosition - headersCount) < 0 || i9 >= c0136f.getCount()) ? null : c0136f.getItem(i9);
                MenuItem menuItem = this.f1068s;
                if (menuItem != item) {
                    C0137g c0137gM516b = c0136f.m516b();
                    if (menuItem != null) {
                        this.f1067r.mo507f(c0137gM516b, menuItem);
                    }
                    this.f1068s = item;
                    if (item != null) {
                        this.f1067r.mo506c(c0137gM516b, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i9, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i9 == this.f1065p) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i9 != this.f1066q) {
                return super.onKeyDown(i9, keyEvent);
            }
            setSelection(-1);
            ((C0136f) getAdapter()).m516b().close(false);
            return true;
        }

        @Override // androidx.appcompat.widget.C0263y, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(InterfaceC0224d0 interfaceC0224d0) {
            this.f1067r = interfaceC0224d0;
        }

        @Override // androidx.appcompat.widget.C0263y, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                f1063K = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public C0228f0(Context context, AttributeSet attributeSet, int i9, int i10) {
        super(context, attributeSet, i9, i10);
    }

    /* renamed from: L */
    public void m839L(Object obj) {
        this.f859G.setEnterTransition((Transition) obj);
    }

    /* renamed from: M */
    public void m840M(Object obj) {
        this.f859G.setExitTransition((Transition) obj);
    }

    /* renamed from: N */
    public void m841N(InterfaceC0224d0 interfaceC0224d0) {
        this.f1064J = interfaceC0224d0;
    }

    /* renamed from: O */
    public void m842O(boolean z8) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Build.VERSION.SDK_INT > 28) {
            this.f859G.setTouchModal(z8);
            return;
        }
        Method method = f1063K;
        if (method != null) {
            try {
                method.invoke(this.f859G, Boolean.valueOf(z8));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // androidx.appcompat.widget.InterfaceC0224d0
    /* renamed from: c */
    public void mo506c(C0137g c0137g, MenuItem menuItem) {
        InterfaceC0224d0 interfaceC0224d0 = this.f1064J;
        if (interfaceC0224d0 != null) {
            interfaceC0224d0.mo506c(c0137g, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.InterfaceC0224d0
    /* renamed from: f */
    public void mo507f(C0137g c0137g, MenuItem menuItem) {
        InterfaceC0224d0 interfaceC0224d0 = this.f1064J;
        if (interfaceC0224d0 != null) {
            interfaceC0224d0.mo507f(c0137g, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    /* renamed from: q */
    public C0263y mo705q(Context context, boolean z8) {
        a aVar = new a(context, z8);
        aVar.setHoverListener(this);
        return aVar;
    }
}
