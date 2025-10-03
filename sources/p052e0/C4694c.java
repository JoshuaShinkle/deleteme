package p052e0;

import android.view.accessibility.AccessibilityManager;

/* renamed from: e0.c */
/* loaded from: classes.dex */
public final class C4694c {

    /* renamed from: e0.c$a */
    public interface a {
        void onTouchExplorationStateChanged(boolean z8);
    }

    /* renamed from: e0.c$b */
    public static final class b implements AccessibilityManager.TouchExplorationStateChangeListener {

        /* renamed from: a */
        public final a f16407a;

        public b(a aVar) {
            this.f16407a = aVar;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                return this.f16407a.equals(((b) obj).f16407a);
            }
            return false;
        }

        public int hashCode() {
            return this.f16407a.hashCode();
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z8) {
            this.f16407a.onTouchExplorationStateChanged(z8);
        }
    }

    /* renamed from: a */
    public static boolean m18747a(AccessibilityManager accessibilityManager, a aVar) {
        if (aVar == null) {
            return false;
        }
        return accessibilityManager.addTouchExplorationStateChangeListener(new b(aVar));
    }

    /* renamed from: b */
    public static boolean m18748b(AccessibilityManager accessibilityManager, a aVar) {
        if (aVar == null) {
            return false;
        }
        return accessibilityManager.removeTouchExplorationStateChangeListener(new b(aVar));
    }
}
