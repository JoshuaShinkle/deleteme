package p042d0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import p052e0.C4704m;
import p052e0.C4705n;
import p179r.C6179b;

/* renamed from: d0.a */
/* loaded from: classes.dex */
public class C4613a {
    private static final View.AccessibilityDelegate DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    private final View.AccessibilityDelegate mBridge;
    private final View.AccessibilityDelegate mOriginalDelegate;

    /* renamed from: d0.a$a */
    public static final class a extends View.AccessibilityDelegate {

        /* renamed from: a */
        public final C4613a f16227a;

        public a(C4613a c4613a) {
            this.f16227a = c4613a;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.f16227a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            C4705n accessibilityNodeProvider = this.f16227a.getAccessibilityNodeProvider(view);
            if (accessibilityNodeProvider != null) {
                return (AccessibilityNodeProvider) accessibilityNodeProvider.m18839d();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f16227a.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            C4704m c4704mM18763w0 = C4704m.m18763w0(accessibilityNodeInfo);
            c4704mM18763w0.m18812n0(C4647u.m18516L(view));
            c4704mM18763w0.m18798f0(C4647u.m18511G(view));
            c4704mM18763w0.m18807k0(C4647u.m18549j(view));
            this.f16227a.onInitializeAccessibilityNodeInfo(view, c4704mM18763w0);
            c4704mM18763w0.m18795e(accessibilityNodeInfo.getText(), view);
            List<C4704m.a> actionList = C4613a.getActionList(view);
            for (int i9 = 0; i9 < actionList.size(); i9++) {
                c4704mM18763w0.m18789b(actionList.get(i9));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.f16227a.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.f16227a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View view, int i9, Bundle bundle) {
            return this.f16227a.performAccessibilityAction(view, i9, bundle);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i9) {
            this.f16227a.sendAccessibilityEvent(view, i9);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.f16227a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }

    public C4613a() {
        this(DEFAULT_DELEGATE);
    }

    public static List<C4704m.a> getActionList(View view) {
        List<C4704m.a> list = (List) view.getTag(C6179b.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    private boolean isSpanStillValid(ClickableSpan clickableSpan, View view) {
        if (clickableSpan != null) {
            ClickableSpan[] clickableSpanArrM18762n = C4704m.m18762n(view.createAccessibilityNodeInfo().getText());
            for (int i9 = 0; clickableSpanArrM18762n != null && i9 < clickableSpanArrM18762n.length; i9++) {
                if (clickableSpan.equals(clickableSpanArrM18762n[i9])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean performClickableSpanAction(int i9, View view) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) view.getTag(C6179b.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i9)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!isSpanStillValid(clickableSpan, view)) {
            return false;
        }
        clickableSpan.onClick(view);
        return true;
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public C4705n getAccessibilityNodeProvider(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.mOriginalDelegate.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new C4705n(accessibilityNodeProvider);
        }
        return null;
    }

    public View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, C4704m c4704m) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, c4704m.m18828v0());
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.mOriginalDelegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean performAccessibilityAction(View view, int i9, Bundle bundle) {
        List<C4704m.a> actionList = getActionList(view);
        boolean zPerformAccessibilityAction = false;
        int i10 = 0;
        while (true) {
            if (i10 >= actionList.size()) {
                break;
            }
            C4704m.a aVar = actionList.get(i10);
            if (aVar.m18833a() == i9) {
                zPerformAccessibilityAction = aVar.m18835c(view, bundle);
                break;
            }
            i10++;
        }
        if (!zPerformAccessibilityAction) {
            zPerformAccessibilityAction = this.mOriginalDelegate.performAccessibilityAction(view, i9, bundle);
        }
        return (zPerformAccessibilityAction || i9 != C6179b.accessibility_action_clickable_span) ? zPerformAccessibilityAction : performClickableSpanAction(bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1), view);
    }

    public void sendAccessibilityEvent(View view, int i9) {
        this.mOriginalDelegate.sendAccessibilityEvent(view, i9);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.mOriginalDelegate.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public C4613a(View.AccessibilityDelegate accessibilityDelegate) {
        this.mOriginalDelegate = accessibilityDelegate;
        this.mBridge = new a(this);
    }
}
