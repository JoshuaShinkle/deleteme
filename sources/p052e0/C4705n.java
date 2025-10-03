package p052e0;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* renamed from: e0.n */
/* loaded from: classes.dex */
public class C4705n {

    /* renamed from: a */
    public final Object f16453a;

    /* renamed from: e0.n$a */
    public static class a extends AccessibilityNodeProvider {

        /* renamed from: a */
        public final C4705n f16454a;

        public a(C4705n c4705n) {
            this.f16454a = c4705n;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i9) {
            C4704m c4704mMo1638a = this.f16454a.mo1638a(i9);
            if (c4704mMo1638a == null) {
                return null;
            }
            return c4704mMo1638a.m18828v0();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i9) {
            List<C4704m> listM18838b = this.f16454a.m18838b(str, i9);
            if (listM18838b == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listM18838b.size();
            for (int i10 = 0; i10 < size; i10++) {
                arrayList.add(listM18838b.get(i10).m18828v0());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i9, int i10, Bundle bundle) {
            return this.f16454a.mo1640e(i9, i10, bundle);
        }
    }

    /* renamed from: e0.n$b */
    public static class b extends a {
        public b(C4705n c4705n) {
            super(c4705n);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int i9) {
            C4704m c4704mMo1639c = this.f16454a.mo1639c(i9);
            if (c4704mMo1639c == null) {
                return null;
            }
            return c4704mMo1639c.m18828v0();
        }
    }

    public C4705n() {
        this.f16453a = new b(this);
    }

    /* renamed from: a */
    public C4704m mo1638a(int i9) {
        return null;
    }

    /* renamed from: b */
    public List<C4704m> m18838b(String str, int i9) {
        return null;
    }

    /* renamed from: c */
    public C4704m mo1639c(int i9) {
        return null;
    }

    /* renamed from: d */
    public Object m18839d() {
        return this.f16453a;
    }

    /* renamed from: e */
    public boolean mo1640e(int i9, int i10, Bundle bundle) {
        return false;
    }

    public C4705n(Object obj) {
        this.f16453a = obj;
    }
}
