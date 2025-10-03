package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.customview.widget.C0341b;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;
import p042d0.C4613a;
import p042d0.C4618c0;
import p042d0.C4647u;
import p052e0.C4693b;
import p052e0.C4704m;
import p052e0.C4705n;
import p052e0.C4706o;
import p132m.C5309h;

/* renamed from: androidx.customview.widget.a */
/* loaded from: classes.dex */
public abstract class AbstractC0340a extends C4613a {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final C0341b.a<C4704m> NODE_ADAPTER = new a();
    private static final C0341b.b<C5309h<C4704m>, C4704m> SPARSE_VALUES_ADAPTER = new b();
    private final View mHost;
    private final AccessibilityManager mManager;
    private c mNodeProvider;
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final int[] mTempGlobalRect = new int[2];
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;

    /* renamed from: androidx.customview.widget.a$a */
    public static class a implements C0341b.a<C4704m> {
        @Override // androidx.customview.widget.C0341b.a
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo1632a(C4704m c4704m, Rect rect) {
            c4704m.m18804j(rect);
        }
    }

    /* renamed from: androidx.customview.widget.a$b */
    public static class b implements C0341b.b<C5309h<C4704m>, C4704m> {
        @Override // androidx.customview.widget.C0341b.b
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public C4704m mo1634a(C5309h<C4704m> c5309h, int i9) {
            return c5309h.m20769o(i9);
        }

        @Override // androidx.customview.widget.C0341b.b
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int mo1635b(C5309h<C4704m> c5309h) {
            return c5309h.m20768n();
        }
    }

    /* renamed from: androidx.customview.widget.a$c */
    public class c extends C4705n {
        public c() {
        }

        @Override // p052e0.C4705n
        /* renamed from: a */
        public C4704m mo1638a(int i9) {
            return C4704m.m18760J(AbstractC0340a.this.obtainAccessibilityNodeInfo(i9));
        }

        @Override // p052e0.C4705n
        /* renamed from: c */
        public C4704m mo1639c(int i9) {
            int i10 = i9 == 2 ? AbstractC0340a.this.mAccessibilityFocusedVirtualViewId : AbstractC0340a.this.mKeyboardFocusedVirtualViewId;
            if (i10 == Integer.MIN_VALUE) {
                return null;
            }
            return mo1638a(i10);
        }

        @Override // p052e0.C4705n
        /* renamed from: e */
        public boolean mo1640e(int i9, int i10, Bundle bundle) {
            return AbstractC0340a.this.performAction(i9, i10, bundle);
        }
    }

    public AbstractC0340a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mHost = view;
        this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (C4647u.m18563q(view) == 0) {
            C4647u.m18548i0(view, 1);
        }
    }

    private boolean clearAccessibilityFocus(int i9) {
        if (this.mAccessibilityFocusedVirtualViewId != i9) {
            return false;
        }
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHost.invalidate();
        sendEventForVirtualView(i9, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
        return true;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        int i9 = this.mKeyboardFocusedVirtualViewId;
        return i9 != Integer.MIN_VALUE && onPerformActionForVirtualView(i9, 16, null);
    }

    private AccessibilityEvent createEvent(int i9, int i10) {
        return i9 != -1 ? createEventForChild(i9, i10) : createEventForHost(i10);
    }

    private AccessibilityEvent createEventForChild(int i9, int i10) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i10);
        C4704m c4704mObtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(i9);
        accessibilityEventObtain.getText().add(c4704mObtainAccessibilityNodeInfo.m18821s());
        accessibilityEventObtain.setContentDescription(c4704mObtainAccessibilityNodeInfo.m18813o());
        accessibilityEventObtain.setScrollable(c4704mObtainAccessibilityNodeInfo.m18768E());
        accessibilityEventObtain.setPassword(c4704mObtainAccessibilityNodeInfo.m18767D());
        accessibilityEventObtain.setEnabled(c4704mObtainAccessibilityNodeInfo.m18832z());
        accessibilityEventObtain.setChecked(c4704mObtainAccessibilityNodeInfo.m18830x());
        onPopulateEventForVirtualView(i9, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(c4704mObtainAccessibilityNodeInfo.m18810m());
        C4706o.m18842c(accessibilityEventObtain, this.mHost, i9);
        accessibilityEventObtain.setPackageName(this.mHost.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    private AccessibilityEvent createEventForHost(int i9) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i9);
        this.mHost.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    private C4704m createNodeForChild(int i9) {
        C4704m c4704mM18758H = C4704m.m18758H();
        c4704mM18758H.m18790b0(true);
        c4704mM18758H.m18794d0(true);
        c4704mM18758H.m18781U(DEFAULT_CLASS_NAME);
        Rect rect = INVALID_PARENT_BOUNDS;
        c4704mM18758H.m18776P(rect);
        c4704mM18758H.m18777Q(rect);
        c4704mM18758H.m18809l0(this.mHost);
        onPopulateNodeForVirtualView(i9, c4704mM18758H);
        if (c4704mM18758H.m18821s() == null && c4704mM18758H.m18813o() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        c4704mM18758H.m18804j(this.mTempParentRect);
        if (this.mTempParentRect.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iM18802i = c4704mM18758H.m18802i();
        if ((iM18802i & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iM18802i & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        c4704mM18758H.m18805j0(this.mHost.getContext().getPackageName());
        c4704mM18758H.m18822s0(this.mHost, i9);
        if (this.mAccessibilityFocusedVirtualViewId == i9) {
            c4704mM18758H.m18774N(true);
            c4704mM18758H.m18787a(128);
        } else {
            c4704mM18758H.m18774N(false);
            c4704mM18758H.m18787a(64);
        }
        boolean z8 = this.mKeyboardFocusedVirtualViewId == i9;
        if (z8) {
            c4704mM18758H.m18787a(2);
        } else if (c4704mM18758H.m18764A()) {
            c4704mM18758H.m18787a(1);
        }
        c4704mM18758H.m18796e0(z8);
        this.mHost.getLocationOnScreen(this.mTempGlobalRect);
        c4704mM18758H.m18806k(this.mTempScreenRect);
        if (this.mTempScreenRect.equals(rect)) {
            c4704mM18758H.m18804j(this.mTempScreenRect);
            if (c4704mM18758H.f16409b != -1) {
                C4704m c4704mM18758H2 = C4704m.m18758H();
                for (int i10 = c4704mM18758H.f16409b; i10 != -1; i10 = c4704mM18758H2.f16409b) {
                    c4704mM18758H2.m18811m0(this.mHost, -1);
                    c4704mM18758H2.m18776P(INVALID_PARENT_BOUNDS);
                    onPopulateNodeForVirtualView(i10, c4704mM18758H2);
                    c4704mM18758H2.m18804j(this.mTempParentRect);
                    Rect rect2 = this.mTempScreenRect;
                    Rect rect3 = this.mTempParentRect;
                    rect2.offset(rect3.left, rect3.top);
                }
                c4704mM18758H2.m18772L();
            }
            this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
        }
        if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
            this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                c4704mM18758H.m18777Q(this.mTempScreenRect);
                if (isVisibleToUser(this.mTempScreenRect)) {
                    c4704mM18758H.m18826u0(true);
                }
            }
        }
        return c4704mM18758H;
    }

    private C4704m createNodeForHost() {
        C4704m c4704mM18759I = C4704m.m18759I(this.mHost);
        C4647u.m18521Q(this.mHost, c4704mM18759I);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (c4704mM18759I.m18808l() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            c4704mM18759I.m18793d(this.mHost, ((Integer) arrayList.get(i9)).intValue());
        }
        return c4704mM18759I;
    }

    private C5309h<C4704m> getAllNodes() {
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        C5309h<C4704m> c5309h = new C5309h<>();
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            c5309h.m20765k(i9, createNodeForChild(i9));
        }
        return c5309h;
    }

    private void getBoundsInParent(int i9, Rect rect) {
        obtainAccessibilityNodeInfo(i9).m18804j(rect);
    }

    private static Rect guessPreviouslyFocusedRect(View view, int i9, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i9 == 17) {
            rect.set(width, 0, width, height);
        } else if (i9 == 33) {
            rect.set(0, height, width, height);
        } else if (i9 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i9 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean isVisibleToUser(Rect rect) {
        if (rect == null || rect.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.mHost.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= BitmapDescriptorFactory.HUE_RED || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private static int keyToDirection(int i9) {
        if (i9 == 19) {
            return 33;
        }
        if (i9 == 21) {
            return 17;
        }
        if (i9 != 22) {
            return TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
        }
        return 66;
    }

    private boolean moveFocus(int i9, Rect rect) {
        C4704m c4704m;
        C5309h<C4704m> allNodes = getAllNodes();
        int i10 = this.mKeyboardFocusedVirtualViewId;
        C4704m c4704mM20760e = i10 == Integer.MIN_VALUE ? null : allNodes.m20760e(i10);
        if (i9 == 1 || i9 == 2) {
            c4704m = (C4704m) C0341b.m1644d(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, c4704mM20760e, i9, C4647u.m18567s(this.mHost) == 1, false);
        } else {
            if (i9 != 17 && i9 != 33 && i9 != 66 && i9 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i11 = this.mKeyboardFocusedVirtualViewId;
            if (i11 != Integer.MIN_VALUE) {
                getBoundsInParent(i11, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                guessPreviouslyFocusedRect(this.mHost, i9, rect2);
            }
            c4704m = (C4704m) C0341b.m1643c(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, c4704mM20760e, rect2, i9);
        }
        return requestKeyboardFocusForVirtualView(c4704m != null ? allNodes.m20764j(allNodes.m20763i(c4704m)) : Integer.MIN_VALUE);
    }

    private boolean performActionForChild(int i9, int i10, Bundle bundle) {
        return i10 != 1 ? i10 != 2 ? i10 != 64 ? i10 != 128 ? onPerformActionForVirtualView(i9, i10, bundle) : clearAccessibilityFocus(i9) : requestAccessibilityFocus(i9) : clearKeyboardFocusForVirtualView(i9) : requestKeyboardFocusForVirtualView(i9);
    }

    private boolean performActionForHost(int i9, Bundle bundle) {
        return C4647u.m18523S(this.mHost, i9, bundle);
    }

    private boolean requestAccessibilityFocus(int i9) {
        int i10;
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled() || (i10 = this.mAccessibilityFocusedVirtualViewId) == i9) {
            return false;
        }
        if (i10 != Integer.MIN_VALUE) {
            clearAccessibilityFocus(i10);
        }
        this.mAccessibilityFocusedVirtualViewId = i9;
        this.mHost.invalidate();
        sendEventForVirtualView(i9, 32768);
        return true;
    }

    private void updateHoveredVirtualView(int i9) {
        int i10 = this.mHoveredVirtualViewId;
        if (i10 == i9) {
            return;
        }
        this.mHoveredVirtualViewId = i9;
        sendEventForVirtualView(i9, 128);
        sendEventForVirtualView(i10, 256);
    }

    public final boolean clearKeyboardFocusForVirtualView(int i9) {
        if (this.mKeyboardFocusedVirtualViewId != i9) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(i9, false);
        sendEventForVirtualView(i9, 8);
        return true;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.mManager.isEnabled() || !this.mManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
            updateHoveredVirtualView(virtualViewAt);
            return virtualViewAt != Integer.MIN_VALUE;
        }
        if (action != 10 || this.mHoveredVirtualViewId == Integer.MIN_VALUE) {
            return false;
        }
        updateHoveredVirtualView(Integer.MIN_VALUE);
        return true;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int i9 = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return moveFocus(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return moveFocus(1, null);
            }
            return false;
        }
        if (keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (!keyEvent.hasNoModifiers()) {
                        return false;
                    }
                    int iKeyToDirection = keyToDirection(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z8 = false;
                    while (i9 < repeatCount && moveFocus(iKeyToDirection, null)) {
                        i9++;
                        z8 = true;
                    }
                    return z8;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
            return false;
        }
        clickKeyboardFocusedVirtualView();
        return true;
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    @Override // p042d0.C4613a
    public C4705n getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new c();
        }
        return this.mNodeProvider;
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    public abstract int getVirtualViewAt(float f9, float f10);

    public abstract void getVisibleVirtualViews(List<Integer> list);

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i9) {
        invalidateVirtualView(i9, 0);
    }

    public C4704m obtainAccessibilityNodeInfo(int i9) {
        return i9 == -1 ? createNodeForHost() : createNodeForChild(i9);
    }

    public final void onFocusChanged(boolean z8, int i9, Rect rect) {
        int i10 = this.mKeyboardFocusedVirtualViewId;
        if (i10 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i10);
        }
        if (z8) {
            moveFocus(i9, rect);
        }
    }

    @Override // p042d0.C4613a
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    @Override // p042d0.C4613a
    public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
        super.onInitializeAccessibilityNodeInfo(view, c4704m);
        onPopulateNodeForHost(c4704m);
    }

    public abstract boolean onPerformActionForVirtualView(int i9, int i10, Bundle bundle);

    public void onPopulateEventForHost(AccessibilityEvent accessibilityEvent) {
    }

    public void onPopulateEventForVirtualView(int i9, AccessibilityEvent accessibilityEvent) {
    }

    public void onPopulateNodeForHost(C4704m c4704m) {
    }

    public abstract void onPopulateNodeForVirtualView(int i9, C4704m c4704m);

    public void onVirtualViewKeyboardFocusChanged(int i9, boolean z8) {
    }

    public boolean performAction(int i9, int i10, Bundle bundle) {
        return i9 != -1 ? performActionForChild(i9, i10, bundle) : performActionForHost(i10, bundle);
    }

    public final boolean requestKeyboardFocusForVirtualView(int i9) {
        int i10;
        if ((!this.mHost.isFocused() && !this.mHost.requestFocus()) || (i10 = this.mKeyboardFocusedVirtualViewId) == i9) {
            return false;
        }
        if (i10 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(i10);
        }
        this.mKeyboardFocusedVirtualViewId = i9;
        onVirtualViewKeyboardFocusChanged(i9, true);
        sendEventForVirtualView(i9, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i9, int i10) {
        ViewParent parent;
        if (i9 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        return C4618c0.m18405h(parent, this.mHost, createEvent(i9, i10));
    }

    public final void invalidateVirtualView(int i9, int i10) {
        ViewParent parent;
        if (i9 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventCreateEvent = createEvent(i9, 2048);
        C4693b.m18746b(accessibilityEventCreateEvent, i10);
        C4618c0.m18405h(parent, this.mHost, accessibilityEventCreateEvent);
    }
}
