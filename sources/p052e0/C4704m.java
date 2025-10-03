package p052e0;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p052e0.InterfaceC4707p;

/* renamed from: e0.m */
/* loaded from: classes.dex */
public class C4704m {

    /* renamed from: a */
    public final AccessibilityNodeInfo f16408a;

    /* renamed from: b */
    public int f16409b = -1;

    /* renamed from: c */
    public int f16410c = -1;

    /* renamed from: e0.m$a */
    public static class a {

        /* renamed from: A */
        public static final a f16411A;

        /* renamed from: B */
        public static final a f16412B;

        /* renamed from: C */
        public static final a f16413C;

        /* renamed from: D */
        public static final a f16414D;

        /* renamed from: E */
        public static final a f16415E;

        /* renamed from: F */
        public static final a f16416F;

        /* renamed from: G */
        public static final a f16417G;

        /* renamed from: H */
        public static final a f16418H;

        /* renamed from: I */
        public static final a f16419I;

        /* renamed from: J */
        public static final a f16420J;

        /* renamed from: K */
        public static final a f16421K;

        /* renamed from: L */
        public static final a f16422L;

        /* renamed from: M */
        public static final a f16423M;

        /* renamed from: N */
        public static final a f16424N;

        /* renamed from: d */
        public static final a f16425d = new a(1, null);

        /* renamed from: e */
        public static final a f16426e = new a(2, null);

        /* renamed from: f */
        public static final a f16427f = new a(4, null);

        /* renamed from: g */
        public static final a f16428g = new a(8, null);

        /* renamed from: h */
        public static final a f16429h = new a(16, null);

        /* renamed from: i */
        public static final a f16430i = new a(32, null);

        /* renamed from: j */
        public static final a f16431j = new a(64, null);

        /* renamed from: k */
        public static final a f16432k = new a(128, null);

        /* renamed from: l */
        public static final a f16433l = new a(256, null, InterfaceC4707p.b.class);

        /* renamed from: m */
        public static final a f16434m = new a(512, null, InterfaceC4707p.b.class);

        /* renamed from: n */
        public static final a f16435n = new a(UserMetadata.MAX_ATTRIBUTE_SIZE, null, InterfaceC4707p.c.class);

        /* renamed from: o */
        public static final a f16436o = new a(2048, null, InterfaceC4707p.c.class);

        /* renamed from: p */
        public static final a f16437p = new a(4096, null);

        /* renamed from: q */
        public static final a f16438q = new a(UserMetadata.MAX_INTERNAL_KEY_SIZE, null);

        /* renamed from: r */
        public static final a f16439r = new a(16384, null);

        /* renamed from: s */
        public static final a f16440s = new a(32768, null);

        /* renamed from: t */
        public static final a f16441t = new a(C3322C.DEFAULT_BUFFER_SEGMENT_SIZE, null);

        /* renamed from: u */
        public static final a f16442u = new a(131072, null, InterfaceC4707p.g.class);

        /* renamed from: v */
        public static final a f16443v = new a(262144, null);

        /* renamed from: w */
        public static final a f16444w = new a(524288, null);

        /* renamed from: x */
        public static final a f16445x = new a(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, null);

        /* renamed from: y */
        public static final a f16446y = new a(2097152, null, InterfaceC4707p.h.class);

        /* renamed from: z */
        public static final a f16447z;

        /* renamed from: a */
        public final Object f16448a;

        /* renamed from: b */
        public final int f16449b;

        /* renamed from: c */
        public final Class<? extends InterfaceC4707p.a> f16450c;

        static {
            int i9 = Build.VERSION.SDK_INT;
            f16447z = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
            f16411A = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, InterfaceC4707p.e.class);
            f16412B = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            f16413C = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
            f16414D = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            f16415E = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
            f16416F = new a(i9 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            f16417G = new a(i9 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            f16418H = new a(i9 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            f16419I = new a(i9 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            f16420J = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
            f16421K = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, InterfaceC4707p.f.class);
            f16422L = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, null, InterfaceC4707p.d.class);
            f16423M = new a(i9 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            f16424N = new a(i9 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i9, CharSequence charSequence) {
            this(null, i9, charSequence, null, null);
        }

        /* renamed from: a */
        public int m18833a() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f16448a).getId();
        }

        /* renamed from: b */
        public CharSequence m18834b() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f16448a).getLabel();
        }

        /* renamed from: c */
        public boolean m18835c(View view, Bundle bundle) {
            return false;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.f16448a;
            return obj2 == null ? aVar.f16448a == null : obj2.equals(aVar.f16448a);
        }

        public int hashCode() {
            Object obj = this.f16448a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public a(Object obj) {
            this(obj, 0, null, null, null);
        }

        public a(int i9, CharSequence charSequence, Class<? extends InterfaceC4707p.a> cls) {
            this(null, i9, charSequence, null, cls);
        }

        public a(Object obj, int i9, CharSequence charSequence, InterfaceC4707p interfaceC4707p, Class<? extends InterfaceC4707p.a> cls) {
            this.f16449b = i9;
            if (obj == null) {
                this.f16448a = new AccessibilityNodeInfo.AccessibilityAction(i9, charSequence);
            } else {
                this.f16448a = obj;
            }
            this.f16450c = cls;
        }
    }

    /* renamed from: e0.m$b */
    public static class b {

        /* renamed from: a */
        public final Object f16451a;

        public b(Object obj) {
            this.f16451a = obj;
        }

        /* renamed from: a */
        public static b m18836a(int i9, int i10, boolean z8, int i11) {
            return new b(AccessibilityNodeInfo.CollectionInfo.obtain(i9, i10, z8, i11));
        }
    }

    /* renamed from: e0.m$c */
    public static class c {

        /* renamed from: a */
        public final Object f16452a;

        public c(Object obj) {
            this.f16452a = obj;
        }

        /* renamed from: a */
        public static c m18837a(int i9, int i10, int i11, int i12, boolean z8, boolean z9) {
            return new c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i9, i10, i11, i12, z8, z9));
        }
    }

    public C4704m(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f16408a = accessibilityNodeInfo;
    }

    /* renamed from: H */
    public static C4704m m18758H() {
        return m18763w0(AccessibilityNodeInfo.obtain());
    }

    /* renamed from: I */
    public static C4704m m18759I(View view) {
        return m18763w0(AccessibilityNodeInfo.obtain(view));
    }

    /* renamed from: J */
    public static C4704m m18760J(C4704m c4704m) {
        return m18763w0(AccessibilityNodeInfo.obtain(c4704m.f16408a));
    }

    /* renamed from: h */
    public static String m18761h(int i9) {
        if (i9 == 1) {
            return "ACTION_FOCUS";
        }
        if (i9 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i9) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case UserMetadata.MAX_ATTRIBUTE_SIZE /* 1024 */:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case UserMetadata.MAX_INTERNAL_KEY_SIZE /* 8192 */:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case C3322C.DEFAULT_BUFFER_SEGMENT_SIZE /* 65536 */:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            default:
                switch (i9) {
                    case R.id.accessibilityActionShowOnScreen:
                        return "ACTION_SHOW_ON_SCREEN";
                    case R.id.accessibilityActionScrollToPosition:
                        return "ACTION_SCROLL_TO_POSITION";
                    case R.id.accessibilityActionScrollUp:
                        return "ACTION_SCROLL_UP";
                    case R.id.accessibilityActionScrollLeft:
                        return "ACTION_SCROLL_LEFT";
                    case R.id.accessibilityActionScrollDown:
                        return "ACTION_SCROLL_DOWN";
                    case R.id.accessibilityActionScrollRight:
                        return "ACTION_SCROLL_RIGHT";
                    case R.id.accessibilityActionContextClick:
                        return "ACTION_CONTEXT_CLICK";
                    case R.id.accessibilityActionSetProgress:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i9) {
                            case R.id.accessibilityActionShowTooltip:
                                return "ACTION_SHOW_TOOLTIP";
                            case R.id.accessibilityActionHideTooltip:
                                return "ACTION_HIDE_TOOLTIP";
                            case R.id.accessibilityActionPageUp:
                                return "ACTION_PAGE_UP";
                            case R.id.accessibilityActionPageDown:
                                return "ACTION_PAGE_DOWN";
                            case R.id.accessibilityActionPageLeft:
                                return "ACTION_PAGE_LEFT";
                            case R.id.accessibilityActionPageRight:
                                return "ACTION_PAGE_RIGHT";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    /* renamed from: n */
    public static ClickableSpan[] m18762n(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    /* renamed from: w0 */
    public static C4704m m18763w0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new C4704m(accessibilityNodeInfo);
    }

    /* renamed from: A */
    public boolean m18764A() {
        return this.f16408a.isFocusable();
    }

    /* renamed from: B */
    public boolean m18765B() {
        return this.f16408a.isFocused();
    }

    /* renamed from: C */
    public boolean m18766C() {
        return this.f16408a.isLongClickable();
    }

    /* renamed from: D */
    public boolean m18767D() {
        return this.f16408a.isPassword();
    }

    /* renamed from: E */
    public boolean m18768E() {
        return this.f16408a.isScrollable();
    }

    /* renamed from: F */
    public boolean m18769F() {
        return this.f16408a.isSelected();
    }

    /* renamed from: G */
    public boolean m18770G() {
        return this.f16408a.isVisibleToUser();
    }

    /* renamed from: K */
    public boolean m18771K(int i9, Bundle bundle) {
        return this.f16408a.performAction(i9, bundle);
    }

    /* renamed from: L */
    public void m18772L() {
        this.f16408a.recycle();
    }

    /* renamed from: M */
    public boolean m18773M(a aVar) {
        return this.f16408a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f16448a);
    }

    /* renamed from: N */
    public void m18774N(boolean z8) {
        this.f16408a.setAccessibilityFocused(z8);
    }

    /* renamed from: O */
    public final void m18775O(int i9, boolean z8) {
        Bundle bundleM18815p = m18815p();
        if (bundleM18815p != null) {
            int i10 = bundleM18815p.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i9);
            if (!z8) {
                i9 = 0;
            }
            bundleM18815p.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i9 | i10);
        }
    }

    @Deprecated
    /* renamed from: P */
    public void m18776P(Rect rect) {
        this.f16408a.setBoundsInParent(rect);
    }

    /* renamed from: Q */
    public void m18777Q(Rect rect) {
        this.f16408a.setBoundsInScreen(rect);
    }

    /* renamed from: R */
    public void m18778R(boolean z8) {
        this.f16408a.setCanOpenPopup(z8);
    }

    /* renamed from: S */
    public void m18779S(boolean z8) {
        this.f16408a.setCheckable(z8);
    }

    /* renamed from: T */
    public void m18780T(boolean z8) {
        this.f16408a.setChecked(z8);
    }

    /* renamed from: U */
    public void m18781U(CharSequence charSequence) {
        this.f16408a.setClassName(charSequence);
    }

    /* renamed from: V */
    public void m18782V(boolean z8) {
        this.f16408a.setClickable(z8);
    }

    /* renamed from: W */
    public void m18783W(Object obj) {
        this.f16408a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f16451a);
    }

    /* renamed from: X */
    public void m18784X(Object obj) {
        this.f16408a.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((c) obj).f16452a);
    }

    /* renamed from: Y */
    public void m18785Y(CharSequence charSequence) {
        this.f16408a.setContentDescription(charSequence);
    }

    /* renamed from: Z */
    public void m18786Z(boolean z8) {
        this.f16408a.setContentInvalid(z8);
    }

    /* renamed from: a */
    public void m18787a(int i9) {
        this.f16408a.addAction(i9);
    }

    /* renamed from: a0 */
    public void m18788a0(boolean z8) {
        this.f16408a.setDismissable(z8);
    }

    /* renamed from: b */
    public void m18789b(a aVar) {
        this.f16408a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f16448a);
    }

    /* renamed from: b0 */
    public void m18790b0(boolean z8) {
        this.f16408a.setEnabled(z8);
    }

    /* renamed from: c */
    public void m18791c(View view) {
        this.f16408a.addChild(view);
    }

    /* renamed from: c0 */
    public void m18792c0(CharSequence charSequence) {
        this.f16408a.setError(charSequence);
    }

    /* renamed from: d */
    public void m18793d(View view, int i9) {
        this.f16408a.addChild(view, i9);
    }

    /* renamed from: d0 */
    public void m18794d0(boolean z8) {
        this.f16408a.setFocusable(z8);
    }

    /* renamed from: e */
    public void m18795e(CharSequence charSequence, View view) {
    }

    /* renamed from: e0 */
    public void m18796e0(boolean z8) {
        this.f16408a.setFocused(z8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C4704m)) {
            return false;
        }
        C4704m c4704m = (C4704m) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f16408a;
        if (accessibilityNodeInfo == null) {
            if (c4704m.f16408a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(c4704m.f16408a)) {
            return false;
        }
        return this.f16410c == c4704m.f16410c && this.f16409b == c4704m.f16409b;
    }

    /* renamed from: f */
    public final List<Integer> m18797f(String str) {
        ArrayList<Integer> integerArrayList = this.f16408a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f16408a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    /* renamed from: f0 */
    public void m18798f0(boolean z8) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f16408a.setHeading(z8);
        } else {
            m18775O(2, z8);
        }
    }

    /* renamed from: g */
    public List<a> m18799g() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.f16408a.getActionList();
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i9 = 0; i9 < size; i9++) {
            arrayList.add(new a(actionList.get(i9)));
        }
        return arrayList;
    }

    /* renamed from: g0 */
    public void m18800g0(CharSequence charSequence) {
        this.f16408a.setHintText(charSequence);
    }

    /* renamed from: h0 */
    public void m18801h0(boolean z8) {
        this.f16408a.setLongClickable(z8);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f16408a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    /* renamed from: i */
    public int m18802i() {
        return this.f16408a.getActions();
    }

    /* renamed from: i0 */
    public void m18803i0(int i9) {
        this.f16408a.setMovementGranularities(i9);
    }

    @Deprecated
    /* renamed from: j */
    public void m18804j(Rect rect) {
        this.f16408a.getBoundsInParent(rect);
    }

    /* renamed from: j0 */
    public void m18805j0(CharSequence charSequence) {
        this.f16408a.setPackageName(charSequence);
    }

    /* renamed from: k */
    public void m18806k(Rect rect) {
        this.f16408a.getBoundsInScreen(rect);
    }

    /* renamed from: k0 */
    public void m18807k0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f16408a.setPaneTitle(charSequence);
        } else {
            this.f16408a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    /* renamed from: l */
    public int m18808l() {
        return this.f16408a.getChildCount();
    }

    /* renamed from: l0 */
    public void m18809l0(View view) {
        this.f16409b = -1;
        this.f16408a.setParent(view);
    }

    /* renamed from: m */
    public CharSequence m18810m() {
        return this.f16408a.getClassName();
    }

    /* renamed from: m0 */
    public void m18811m0(View view, int i9) {
        this.f16409b = i9;
        this.f16408a.setParent(view, i9);
    }

    /* renamed from: n0 */
    public void m18812n0(boolean z8) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f16408a.setScreenReaderFocusable(z8);
        } else {
            m18775O(1, z8);
        }
    }

    /* renamed from: o */
    public CharSequence m18813o() {
        return this.f16408a.getContentDescription();
    }

    /* renamed from: o0 */
    public void m18814o0(boolean z8) {
        this.f16408a.setScrollable(z8);
    }

    /* renamed from: p */
    public Bundle m18815p() {
        return this.f16408a.getExtras();
    }

    /* renamed from: p0 */
    public void m18816p0(boolean z8) {
        this.f16408a.setSelected(z8);
    }

    /* renamed from: q */
    public int m18817q() {
        return this.f16408a.getMovementGranularities();
    }

    /* renamed from: q0 */
    public void m18818q0(boolean z8) {
        this.f16408a.setShowingHintText(z8);
    }

    /* renamed from: r */
    public CharSequence m18819r() {
        return this.f16408a.getPackageName();
    }

    /* renamed from: r0 */
    public void m18820r0(View view) {
        this.f16410c = -1;
        this.f16408a.setSource(view);
    }

    /* renamed from: s */
    public CharSequence m18821s() {
        if (!m18825u()) {
            return this.f16408a.getText();
        }
        List<Integer> listM18797f = m18797f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listM18797f2 = m18797f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listM18797f3 = m18797f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listM18797f4 = m18797f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f16408a.getText(), 0, this.f16408a.getText().length()));
        for (int i9 = 0; i9 < listM18797f.size(); i9++) {
            spannableString.setSpan(new C4692a(listM18797f4.get(i9).intValue(), this, m18815p().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listM18797f.get(i9).intValue(), listM18797f2.get(i9).intValue(), listM18797f3.get(i9).intValue());
        }
        return spannableString;
    }

    /* renamed from: s0 */
    public void m18822s0(View view, int i9) {
        this.f16410c = i9;
        this.f16408a.setSource(view, i9);
    }

    /* renamed from: t */
    public String m18823t() {
        return this.f16408a.getViewIdResourceName();
    }

    /* renamed from: t0 */
    public void m18824t0(CharSequence charSequence) {
        this.f16408a.setText(charSequence);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        m18804j(rect);
        sb.append("; boundsInParent: " + rect);
        m18806k(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(m18819r());
        sb.append("; className: ");
        sb.append(m18810m());
        sb.append("; text: ");
        sb.append(m18821s());
        sb.append("; contentDescription: ");
        sb.append(m18813o());
        sb.append("; viewId: ");
        sb.append(m18823t());
        sb.append("; checkable: ");
        sb.append(m18829w());
        sb.append("; checked: ");
        sb.append(m18830x());
        sb.append("; focusable: ");
        sb.append(m18764A());
        sb.append("; focused: ");
        sb.append(m18765B());
        sb.append("; selected: ");
        sb.append(m18769F());
        sb.append("; clickable: ");
        sb.append(m18831y());
        sb.append("; longClickable: ");
        sb.append(m18766C());
        sb.append("; enabled: ");
        sb.append(m18832z());
        sb.append("; password: ");
        sb.append(m18767D());
        sb.append("; scrollable: " + m18768E());
        sb.append("; [");
        List<a> listM18799g = m18799g();
        for (int i9 = 0; i9 < listM18799g.size(); i9++) {
            a aVar = listM18799g.get(i9);
            String strM18761h = m18761h(aVar.m18833a());
            if (strM18761h.equals("ACTION_UNKNOWN") && aVar.m18834b() != null) {
                strM18761h = aVar.m18834b().toString();
            }
            sb.append(strM18761h);
            if (i9 != listM18799g.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: u */
    public final boolean m18825u() {
        return !m18797f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    /* renamed from: u0 */
    public void m18826u0(boolean z8) {
        this.f16408a.setVisibleToUser(z8);
    }

    /* renamed from: v */
    public boolean m18827v() {
        return this.f16408a.isAccessibilityFocused();
    }

    /* renamed from: v0 */
    public AccessibilityNodeInfo m18828v0() {
        return this.f16408a;
    }

    /* renamed from: w */
    public boolean m18829w() {
        return this.f16408a.isCheckable();
    }

    /* renamed from: x */
    public boolean m18830x() {
        return this.f16408a.isChecked();
    }

    /* renamed from: y */
    public boolean m18831y() {
        return this.f16408a.isClickable();
    }

    /* renamed from: z */
    public boolean m18832z() {
        return this.f16408a.isEnabled();
    }
}
