package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import p042d0.C4647u;
import p132m.C5302a;
import p132m.C5305d;
import p206u.C6350d;

/* renamed from: androidx.transition.m */
/* loaded from: classes.dex */
public abstract class AbstractC0532m implements Cloneable {
    static final boolean DBG = false;
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private ArrayList<C0539t> mEndValuesList;
    private f mEpicenterCallback;
    private C5302a<String, String> mNameOverrides;
    AbstractC0535p mPropagation;
    private ArrayList<C0539t> mStartValuesList;
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final AbstractC0523g STRAIGHT_PATH_MOTION = new a();
    private static ThreadLocal<C5302a<Animator, d>> sRunningAnimators = new ThreadLocal<>();
    private String mName = getClass().getName();
    private long mStartDelay = -1;
    long mDuration = -1;
    private TimeInterpolator mInterpolator = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    ArrayList<View> mTargets = new ArrayList<>();
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class> mTargetTypes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Class> mTargetTypeExcludes = null;
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<Class> mTargetTypeChildExcludes = null;
    private C0540u mStartValues = new C0540u();
    private C0540u mEndValues = new C0540u();
    C0536q mParent = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    private ViewGroup mSceneRoot = null;
    boolean mCanRemoveViews = false;
    ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    private int mNumInstances = 0;
    private boolean mPaused = false;
    private boolean mEnded = false;
    private ArrayList<g> mListeners = null;
    private ArrayList<Animator> mAnimators = new ArrayList<>();
    private AbstractC0523g mPathMotion = STRAIGHT_PATH_MOTION;

    /* renamed from: androidx.transition.m$a */
    public static class a extends AbstractC0523g {
        @Override // androidx.transition.AbstractC0523g
        /* renamed from: a */
        public Path mo3077a(float f9, float f10, float f11, float f12) {
            Path path = new Path();
            path.moveTo(f9, f10);
            path.lineTo(f11, f12);
            return path;
        }
    }

    /* renamed from: androidx.transition.m$b */
    public class b extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ C5302a f2939a;

        public b(C5302a c5302a) {
            this.f2939a = c5302a;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2939a.remove(animator);
            AbstractC0532m.this.mCurrentAnimators.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AbstractC0532m.this.mCurrentAnimators.add(animator);
        }
    }

    /* renamed from: androidx.transition.m$c */
    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            AbstractC0532m.this.end();
            animator.removeListener(this);
        }
    }

    /* renamed from: androidx.transition.m$d */
    public static class d {

        /* renamed from: a */
        public View f2942a;

        /* renamed from: b */
        public String f2943b;

        /* renamed from: c */
        public C0539t f2944c;

        /* renamed from: d */
        public InterfaceC0528i0 f2945d;

        /* renamed from: e */
        public AbstractC0532m f2946e;

        public d(View view, String str, AbstractC0532m abstractC0532m, InterfaceC0528i0 interfaceC0528i0, C0539t c0539t) {
            this.f2942a = view;
            this.f2943b = str;
            this.f2944c = c0539t;
            this.f2945d = interfaceC0528i0;
            this.f2946e = abstractC0532m;
        }
    }

    /* renamed from: androidx.transition.m$e */
    public static class e {
        /* renamed from: a */
        public static <T> ArrayList<T> m3089a(ArrayList<T> arrayList, T t8) {
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            if (!arrayList.contains(t8)) {
                arrayList.add(t8);
            }
            return arrayList;
        }

        /* renamed from: b */
        public static <T> ArrayList<T> m3090b(ArrayList<T> arrayList, T t8) {
            if (arrayList == null) {
                return arrayList;
            }
            arrayList.remove(t8);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }

    /* renamed from: androidx.transition.m$f */
    public static abstract class f {
        /* renamed from: a */
        public abstract Rect mo3072a(AbstractC0532m abstractC0532m);
    }

    /* renamed from: androidx.transition.m$g */
    public interface g {
        /* renamed from: a */
        void mo3073a(AbstractC0532m abstractC0532m);

        /* renamed from: b */
        void mo3050b(AbstractC0532m abstractC0532m);

        /* renamed from: c */
        void mo3051c(AbstractC0532m abstractC0532m);

        /* renamed from: d */
        void mo3052d(AbstractC0532m abstractC0532m);

        /* renamed from: e */
        void mo3053e(AbstractC0532m abstractC0532m);
    }

    public AbstractC0532m() {
    }

    private void addUnmatched(C5302a<View, C0539t> c5302a, C5302a<View, C0539t> c5302a2) {
        for (int i9 = 0; i9 < c5302a.size(); i9++) {
            C0539t c0539tM20755m = c5302a.m20755m(i9);
            if (isValidTarget(c0539tM20755m.f2966b)) {
                this.mStartValuesList.add(c0539tM20755m);
                this.mEndValuesList.add(null);
            }
        }
        for (int i10 = 0; i10 < c5302a2.size(); i10++) {
            C0539t c0539tM20755m2 = c5302a2.m20755m(i10);
            if (isValidTarget(c0539tM20755m2.f2966b)) {
                this.mEndValuesList.add(c0539tM20755m2);
                this.mStartValuesList.add(null);
            }
        }
    }

    private static void addViewValues(C0540u c0540u, View view, C0539t c0539t) {
        c0540u.f2968a.put(view, c0539t);
        int id = view.getId();
        if (id >= 0) {
            if (c0540u.f2969b.indexOfKey(id) >= 0) {
                c0540u.f2969b.put(id, null);
            } else {
                c0540u.f2969b.put(id, view);
            }
        }
        String strM18574z = C4647u.m18574z(view);
        if (strM18574z != null) {
            if (c0540u.f2971d.containsKey(strM18574z)) {
                c0540u.f2971d.put(strM18574z, null);
            } else {
                c0540u.f2971d.put(strM18574z, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (c0540u.f2970c.m20724h(itemIdAtPosition) < 0) {
                    C4647u.m18546h0(view, true);
                    c0540u.f2970c.m20726j(itemIdAtPosition, view);
                    return;
                }
                View viewM20722e = c0540u.f2970c.m20722e(itemIdAtPosition);
                if (viewM20722e != null) {
                    C4647u.m18546h0(viewM20722e, false);
                    c0540u.f2970c.m20726j(itemIdAtPosition, null);
                }
            }
        }
    }

    private static boolean alreadyContains(int[] iArr, int i9) {
        int i10 = iArr[i9];
        for (int i11 = 0; i11 < i9; i11++) {
            if (iArr[i11] == i10) {
                return true;
            }
        }
        return false;
    }

    private void captureHierarchy(View view, boolean z8) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.mTargetIdExcludes;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.mTargetExcludes;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class> arrayList3 = this.mTargetTypeExcludes;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i9 = 0; i9 < size; i9++) {
                        if (this.mTargetTypeExcludes.get(i9).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    C0539t c0539t = new C0539t();
                    c0539t.f2966b = view;
                    if (z8) {
                        captureStartValues(c0539t);
                    } else {
                        captureEndValues(c0539t);
                    }
                    c0539t.f2967c.add(this);
                    capturePropagationValues(c0539t);
                    if (z8) {
                        addViewValues(this.mStartValues, view, c0539t);
                    } else {
                        addViewValues(this.mEndValues, view, c0539t);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.mTargetIdChildExcludes;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.mTargetChildExcludes;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class> arrayList6 = this.mTargetTypeChildExcludes;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i10 = 0; i10 < size2; i10++) {
                                    if (this.mTargetTypeChildExcludes.get(i10).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i11 = 0; i11 < viewGroup.getChildCount(); i11++) {
                                captureHierarchy(viewGroup.getChildAt(i11), z8);
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Integer> excludeId(ArrayList<Integer> arrayList, int i9, boolean z8) {
        return i9 > 0 ? z8 ? e.m3089a(arrayList, Integer.valueOf(i9)) : e.m3090b(arrayList, Integer.valueOf(i9)) : arrayList;
    }

    private static <T> ArrayList<T> excludeObject(ArrayList<T> arrayList, T t8, boolean z8) {
        return t8 != null ? z8 ? e.m3089a(arrayList, t8) : e.m3090b(arrayList, t8) : arrayList;
    }

    private ArrayList<Class> excludeType(ArrayList<Class> arrayList, Class cls, boolean z8) {
        return cls != null ? z8 ? e.m3089a(arrayList, cls) : e.m3090b(arrayList, cls) : arrayList;
    }

    private ArrayList<View> excludeView(ArrayList<View> arrayList, View view, boolean z8) {
        return view != null ? z8 ? e.m3089a(arrayList, view) : e.m3090b(arrayList, view) : arrayList;
    }

    private static C5302a<Animator, d> getRunningAnimators() {
        C5302a<Animator, d> c5302a = sRunningAnimators.get();
        if (c5302a != null) {
            return c5302a;
        }
        C5302a<Animator, d> c5302a2 = new C5302a<>();
        sRunningAnimators.set(c5302a2);
        return c5302a2;
    }

    private static boolean isValidMatch(int i9) {
        return i9 >= 1 && i9 <= 4;
    }

    private static boolean isValueChanged(C0539t c0539t, C0539t c0539t2, String str) {
        Object obj = c0539t.f2965a.get(str);
        Object obj2 = c0539t2.f2965a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    private void matchIds(C5302a<View, C0539t> c5302a, C5302a<View, C0539t> c5302a2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i9 = 0; i9 < size; i9++) {
            View viewValueAt = sparseArray.valueAt(i9);
            if (viewValueAt != null && isValidTarget(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i9))) != null && isValidTarget(view)) {
                C0539t c0539t = c5302a.get(viewValueAt);
                C0539t c0539t2 = c5302a2.get(view);
                if (c0539t != null && c0539t2 != null) {
                    this.mStartValuesList.add(c0539t);
                    this.mEndValuesList.add(c0539t2);
                    c5302a.remove(viewValueAt);
                    c5302a2.remove(view);
                }
            }
        }
    }

    private void matchInstances(C5302a<View, C0539t> c5302a, C5302a<View, C0539t> c5302a2) {
        C0539t c0539tRemove;
        View view;
        for (int size = c5302a.size() - 1; size >= 0; size--) {
            View viewM20751i = c5302a.m20751i(size);
            if (viewM20751i != null && isValidTarget(viewM20751i) && (c0539tRemove = c5302a2.remove(viewM20751i)) != null && (view = c0539tRemove.f2966b) != null && isValidTarget(view)) {
                this.mStartValuesList.add(c5302a.mo20753k(size));
                this.mEndValuesList.add(c0539tRemove);
            }
        }
    }

    private void matchItemIds(C5302a<View, C0539t> c5302a, C5302a<View, C0539t> c5302a2, C5305d<View> c5305d, C5305d<View> c5305d2) {
        View viewM20722e;
        int iM20730n = c5305d.m20730n();
        for (int i9 = 0; i9 < iM20730n; i9++) {
            View viewM20731o = c5305d.m20731o(i9);
            if (viewM20731o != null && isValidTarget(viewM20731o) && (viewM20722e = c5305d2.m20722e(c5305d.m20725i(i9))) != null && isValidTarget(viewM20722e)) {
                C0539t c0539t = c5302a.get(viewM20731o);
                C0539t c0539t2 = c5302a2.get(viewM20722e);
                if (c0539t != null && c0539t2 != null) {
                    this.mStartValuesList.add(c0539t);
                    this.mEndValuesList.add(c0539t2);
                    c5302a.remove(viewM20731o);
                    c5302a2.remove(viewM20722e);
                }
            }
        }
    }

    private void matchNames(C5302a<View, C0539t> c5302a, C5302a<View, C0539t> c5302a2, C5302a<String, View> c5302a3, C5302a<String, View> c5302a4) {
        View view;
        int size = c5302a3.size();
        for (int i9 = 0; i9 < size; i9++) {
            View viewM20755m = c5302a3.m20755m(i9);
            if (viewM20755m != null && isValidTarget(viewM20755m) && (view = c5302a4.get(c5302a3.m20751i(i9))) != null && isValidTarget(view)) {
                C0539t c0539t = c5302a.get(viewM20755m);
                C0539t c0539t2 = c5302a2.get(view);
                if (c0539t != null && c0539t2 != null) {
                    this.mStartValuesList.add(c0539t);
                    this.mEndValuesList.add(c0539t2);
                    c5302a.remove(viewM20755m);
                    c5302a2.remove(view);
                }
            }
        }
    }

    private void matchStartAndEnd(C0540u c0540u, C0540u c0540u2) {
        C5302a<View, C0539t> c5302a = new C5302a<>(c0540u.f2968a);
        C5302a<View, C0539t> c5302a2 = new C5302a<>(c0540u2.f2968a);
        int i9 = 0;
        while (true) {
            int[] iArr = this.mMatchOrder;
            if (i9 >= iArr.length) {
                addUnmatched(c5302a, c5302a2);
                return;
            }
            int i10 = iArr[i9];
            if (i10 == 1) {
                matchInstances(c5302a, c5302a2);
            } else if (i10 == 2) {
                matchNames(c5302a, c5302a2, c0540u.f2971d, c0540u2.f2971d);
            } else if (i10 == 3) {
                matchIds(c5302a, c5302a2, c0540u.f2969b, c0540u2.f2969b);
            } else if (i10 == 4) {
                matchItemIds(c5302a, c5302a2, c0540u.f2970c, c0540u2.f2970c);
            }
            i9++;
        }
    }

    private static int[] parseMatchOrder(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i9 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String strTrim = stringTokenizer.nextToken().trim();
            if ("id".equalsIgnoreCase(strTrim)) {
                iArr[i9] = 3;
            } else if (MATCH_INSTANCE_STR.equalsIgnoreCase(strTrim)) {
                iArr[i9] = 1;
            } else if ("name".equalsIgnoreCase(strTrim)) {
                iArr[i9] = 2;
            } else if (MATCH_ITEM_ID_STR.equalsIgnoreCase(strTrim)) {
                iArr[i9] = 4;
            } else {
                if (!strTrim.isEmpty()) {
                    throw new InflateException("Unknown match type in matchOrder: '" + strTrim + "'");
                }
                int[] iArr2 = new int[iArr.length - 1];
                System.arraycopy(iArr, 0, iArr2, 0, i9);
                i9--;
                iArr = iArr2;
            }
            i9++;
        }
        return iArr;
    }

    private void runAnimator(Animator animator, C5302a<Animator, d> c5302a) {
        if (animator != null) {
            animator.addListener(new b(c5302a));
            animate(animator);
        }
    }

    public AbstractC0532m addListener(g gVar) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(gVar);
        return this;
    }

    public AbstractC0532m addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public void animate(Animator animator) {
        if (animator == null) {
            end();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new c());
        animator.start();
    }

    public void cancel() {
        for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
            this.mCurrentAnimators.get(size).cancel();
        }
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
        int size2 = arrayList2.size();
        for (int i9 = 0; i9 < size2; i9++) {
            ((g) arrayList2.get(i9)).mo3052d(this);
        }
    }

    public abstract void captureEndValues(C0539t c0539t);

    public void capturePropagationValues(C0539t c0539t) {
    }

    public abstract void captureStartValues(C0539t c0539t);

    public void captureValues(ViewGroup viewGroup, boolean z8) {
        ArrayList<String> arrayList;
        ArrayList<Class> arrayList2;
        C5302a<String, String> c5302a;
        clearValues(z8);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && (((arrayList = this.mTargetNames) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetTypes) == null || arrayList2.isEmpty()))) {
            for (int i9 = 0; i9 < this.mTargetIds.size(); i9++) {
                View viewFindViewById = viewGroup.findViewById(this.mTargetIds.get(i9).intValue());
                if (viewFindViewById != null) {
                    C0539t c0539t = new C0539t();
                    c0539t.f2966b = viewFindViewById;
                    if (z8) {
                        captureStartValues(c0539t);
                    } else {
                        captureEndValues(c0539t);
                    }
                    c0539t.f2967c.add(this);
                    capturePropagationValues(c0539t);
                    if (z8) {
                        addViewValues(this.mStartValues, viewFindViewById, c0539t);
                    } else {
                        addViewValues(this.mEndValues, viewFindViewById, c0539t);
                    }
                }
            }
            for (int i10 = 0; i10 < this.mTargets.size(); i10++) {
                View view = this.mTargets.get(i10);
                C0539t c0539t2 = new C0539t();
                c0539t2.f2966b = view;
                if (z8) {
                    captureStartValues(c0539t2);
                } else {
                    captureEndValues(c0539t2);
                }
                c0539t2.f2967c.add(this);
                capturePropagationValues(c0539t2);
                if (z8) {
                    addViewValues(this.mStartValues, view, c0539t2);
                } else {
                    addViewValues(this.mEndValues, view, c0539t2);
                }
            }
        } else {
            captureHierarchy(viewGroup, z8);
        }
        if (z8 || (c5302a = this.mNameOverrides) == null) {
            return;
        }
        int size = c5302a.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i11 = 0; i11 < size; i11++) {
            arrayList3.add(this.mStartValues.f2971d.remove(this.mNameOverrides.m20751i(i11)));
        }
        for (int i12 = 0; i12 < size; i12++) {
            View view2 = (View) arrayList3.get(i12);
            if (view2 != null) {
                this.mStartValues.f2971d.put(this.mNameOverrides.m20755m(i12), view2);
            }
        }
    }

    public void clearValues(boolean z8) {
        if (z8) {
            this.mStartValues.f2968a.clear();
            this.mStartValues.f2969b.clear();
            this.mStartValues.f2970c.m20718a();
        } else {
            this.mEndValues.f2968a.clear();
            this.mEndValues.f2969b.clear();
            this.mEndValues.f2970c.m20718a();
        }
    }

    public Animator createAnimator(ViewGroup viewGroup, C0539t c0539t, C0539t c0539t2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void createAnimators(ViewGroup viewGroup, C0540u c0540u, C0540u c0540u2, ArrayList<C0539t> arrayList, ArrayList<C0539t> arrayList2) {
        View view;
        Animator animator;
        C0539t c0539t;
        int i9;
        Animator animator2;
        C0539t c0539t2;
        C5302a<Animator, d> runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i10 = 0;
        while (i10 < size) {
            C0539t c0539t3 = arrayList.get(i10);
            C0539t c0539t4 = arrayList2.get(i10);
            if (c0539t3 != null && !c0539t3.f2967c.contains(this)) {
                c0539t3 = null;
            }
            if (c0539t4 != null && !c0539t4.f2967c.contains(this)) {
                c0539t4 = null;
            }
            if (c0539t3 != null || c0539t4 != null) {
                if (c0539t3 == null || c0539t4 == null || isTransitionRequired(c0539t3, c0539t4)) {
                    Animator animatorCreateAnimator = createAnimator(viewGroup, c0539t3, c0539t4);
                    if (animatorCreateAnimator != null) {
                        if (c0539t4 != null) {
                            View view2 = c0539t4.f2966b;
                            String[] transitionProperties = getTransitionProperties();
                            if (view2 == null || transitionProperties == null || transitionProperties.length <= 0) {
                                animator2 = animatorCreateAnimator;
                                c0539t2 = null;
                            } else {
                                c0539t2 = new C0539t();
                                c0539t2.f2966b = view2;
                                C0539t c0539t5 = c0540u2.f2968a.get(view2);
                                if (c0539t5 != null) {
                                    int i11 = 0;
                                    while (i11 < transitionProperties.length) {
                                        Map<String, Object> map = c0539t2.f2965a;
                                        Animator animator3 = animatorCreateAnimator;
                                        String str = transitionProperties[i11];
                                        map.put(str, c0539t5.f2965a.get(str));
                                        i11++;
                                        animatorCreateAnimator = animator3;
                                        transitionProperties = transitionProperties;
                                    }
                                }
                                Animator animator4 = animatorCreateAnimator;
                                int size2 = runningAnimators.size();
                                int i12 = 0;
                                while (true) {
                                    if (i12 >= size2) {
                                        animator2 = animator4;
                                        break;
                                    }
                                    d dVar = runningAnimators.get(runningAnimators.m20751i(i12));
                                    if (dVar.f2944c != null && dVar.f2942a == view2 && dVar.f2943b.equals(getName()) && dVar.f2944c.equals(c0539t2)) {
                                        animator2 = null;
                                        break;
                                    }
                                    i12++;
                                }
                            }
                            view = view2;
                            animator = animator2;
                            c0539t = c0539t2;
                        } else {
                            view = c0539t3.f2966b;
                            animator = animatorCreateAnimator;
                            c0539t = null;
                        }
                        if (animator != null) {
                            i9 = size;
                            runningAnimators.put(animator, new d(view, getName(), this, C0514b0.m3026e(viewGroup), c0539t));
                            this.mAnimators.add(animator);
                        }
                    }
                    i9 = size;
                }
                i9 = size;
            }
            i10++;
            size = i9;
        }
        for (int i13 = 0; i13 < sparseIntArray.size(); i13++) {
            Animator animator5 = this.mAnimators.get(sparseIntArray.keyAt(i13));
            animator5.setStartDelay((sparseIntArray.valueAt(i13) - Long.MAX_VALUE) + animator5.getStartDelay());
        }
    }

    public void end() {
        int i9 = this.mNumInstances - 1;
        this.mNumInstances = i9;
        if (i9 == 0) {
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i10 = 0; i10 < size; i10++) {
                    ((g) arrayList2.get(i10)).mo3051c(this);
                }
            }
            for (int i11 = 0; i11 < this.mStartValues.f2970c.m20730n(); i11++) {
                View viewM20731o = this.mStartValues.f2970c.m20731o(i11);
                if (viewM20731o != null) {
                    C4647u.m18546h0(viewM20731o, false);
                }
            }
            for (int i12 = 0; i12 < this.mEndValues.f2970c.m20730n(); i12++) {
                View viewM20731o2 = this.mEndValues.f2970c.m20731o(i12);
                if (viewM20731o2 != null) {
                    C4647u.m18546h0(viewM20731o2, false);
                }
            }
            this.mEnded = true;
        }
    }

    public AbstractC0532m excludeChildren(View view, boolean z8) {
        this.mTargetChildExcludes = excludeView(this.mTargetChildExcludes, view, z8);
        return this;
    }

    public AbstractC0532m excludeTarget(View view, boolean z8) {
        this.mTargetExcludes = excludeView(this.mTargetExcludes, view, z8);
        return this;
    }

    public void forceToEnd(ViewGroup viewGroup) {
        C5302a<Animator, d> runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        if (viewGroup != null) {
            InterfaceC0528i0 interfaceC0528i0M3026e = C0514b0.m3026e(viewGroup);
            for (int i9 = size - 1; i9 >= 0; i9--) {
                d dVarM20755m = runningAnimators.m20755m(i9);
                if (dVarM20755m.f2942a != null && interfaceC0528i0M3026e != null && interfaceC0528i0M3026e.equals(dVarM20755m.f2945d)) {
                    runningAnimators.m20751i(i9).end();
                }
            }
        }
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Rect getEpicenter() {
        f fVar = this.mEpicenterCallback;
        if (fVar == null) {
            return null;
        }
        return fVar.mo3072a(this);
    }

    public f getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x002d, code lost:
    
        if (r3 < 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002f, code lost:
    
        if (r7 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0031, code lost:
    
        r6 = r5.mEndValuesList;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0034, code lost:
    
        r6 = r5.mStartValuesList;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003d, code lost:
    
        return r6.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C0539t getMatchedTransitionValues(View view, boolean z8) {
        C0536q c0536q = this.mParent;
        if (c0536q != null) {
            return c0536q.getMatchedTransitionValues(view, z8);
        }
        ArrayList<C0539t> arrayList = z8 ? this.mStartValuesList : this.mEndValuesList;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size) {
                i9 = -1;
                break;
            }
            C0539t c0539t = arrayList.get(i9);
            if (c0539t == null) {
                return null;
            }
            if (c0539t.f2966b == view) {
                break;
            }
            i9++;
        }
    }

    public String getName() {
        return this.mName;
    }

    public AbstractC0523g getPathMotion() {
        return this.mPathMotion;
    }

    public AbstractC0535p getPropagation() {
        return null;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List<Integer> getTargetIds() {
        return this.mTargetIds;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class> getTargetTypes() {
        return this.mTargetTypes;
    }

    public List<View> getTargets() {
        return this.mTargets;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public C0539t getTransitionValues(View view, boolean z8) {
        C0536q c0536q = this.mParent;
        if (c0536q != null) {
            return c0536q.getTransitionValues(view, z8);
        }
        return (z8 ? this.mStartValues : this.mEndValues).f2968a.get(view);
    }

    public boolean isTransitionRequired(C0539t c0539t, C0539t c0539t2) {
        if (c0539t == null || c0539t2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties == null) {
            Iterator<String> it = c0539t.f2965a.keySet().iterator();
            while (it.hasNext()) {
                if (isValueChanged(c0539t, c0539t2, it.next())) {
                }
            }
            return false;
        }
        for (String str : transitionProperties) {
            if (!isValueChanged(c0539t, c0539t2, str)) {
            }
        }
        return false;
        return true;
    }

    public boolean isValidTarget(View view) {
        ArrayList<Class> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.mTargetIdExcludes;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.mTargetExcludes;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class> arrayList5 = this.mTargetTypeExcludes;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i9 = 0; i9 < size; i9++) {
                if (this.mTargetTypeExcludes.get(i9).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.mTargetNameExcludes != null && C4647u.m18574z(view) != null && this.mTargetNameExcludes.contains(C4647u.m18574z(view))) {
            return false;
        }
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (((arrayList = this.mTargetTypes) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetNames) == null || arrayList2.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.mTargetNames;
        if (arrayList6 != null && arrayList6.contains(C4647u.m18574z(view))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (int i10 = 0; i10 < this.mTargetTypes.size(); i10++) {
                if (this.mTargetTypes.get(i10).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void pause(View view) {
        if (this.mEnded) {
            return;
        }
        C5302a<Animator, d> runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        InterfaceC0528i0 interfaceC0528i0M3026e = C0514b0.m3026e(view);
        for (int i9 = size - 1; i9 >= 0; i9--) {
            d dVarM20755m = runningAnimators.m20755m(i9);
            if (dVarM20755m.f2942a != null && interfaceC0528i0M3026e.equals(dVarM20755m.f2945d)) {
                C0511a.m3017b(runningAnimators.m20751i(i9));
            }
        }
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
            int size2 = arrayList2.size();
            for (int i10 = 0; i10 < size2; i10++) {
                ((g) arrayList2.get(i10)).mo3050b(this);
            }
        }
        this.mPaused = true;
    }

    public void playTransition(ViewGroup viewGroup) {
        d dVar;
        this.mStartValuesList = new ArrayList<>();
        this.mEndValuesList = new ArrayList<>();
        matchStartAndEnd(this.mStartValues, this.mEndValues);
        C5302a<Animator, d> runningAnimators = getRunningAnimators();
        int size = runningAnimators.size();
        InterfaceC0528i0 interfaceC0528i0M3026e = C0514b0.m3026e(viewGroup);
        for (int i9 = size - 1; i9 >= 0; i9--) {
            Animator animatorM20751i = runningAnimators.m20751i(i9);
            if (animatorM20751i != null && (dVar = runningAnimators.get(animatorM20751i)) != null && dVar.f2942a != null && interfaceC0528i0M3026e.equals(dVar.f2945d)) {
                C0539t c0539t = dVar.f2944c;
                View view = dVar.f2942a;
                C0539t transitionValues = getTransitionValues(view, true);
                C0539t matchedTransitionValues = getMatchedTransitionValues(view, true);
                if (!(transitionValues == null && matchedTransitionValues == null) && dVar.f2946e.isTransitionRequired(c0539t, matchedTransitionValues)) {
                    if (animatorM20751i.isRunning() || animatorM20751i.isStarted()) {
                        animatorM20751i.cancel();
                    } else {
                        runningAnimators.remove(animatorM20751i);
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public AbstractC0532m removeListener(g gVar) {
        ArrayList<g> arrayList = this.mListeners;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(gVar);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public AbstractC0532m removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                C5302a<Animator, d> runningAnimators = getRunningAnimators();
                int size = runningAnimators.size();
                InterfaceC0528i0 interfaceC0528i0M3026e = C0514b0.m3026e(view);
                for (int i9 = size - 1; i9 >= 0; i9--) {
                    d dVarM20755m = runningAnimators.m20755m(i9);
                    if (dVarM20755m.f2942a != null && interfaceC0528i0M3026e.equals(dVarM20755m.f2945d)) {
                        C0511a.m3018c(runningAnimators.m20751i(i9));
                    }
                }
                ArrayList<g> arrayList = this.mListeners;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                    int size2 = arrayList2.size();
                    for (int i10 = 0; i10 < size2; i10++) {
                        ((g) arrayList2.get(i10)).mo3053e(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    public void runAnimators() {
        start();
        C5302a<Animator, d> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (runningAnimators.containsKey(next)) {
                start();
                runAnimator(next, runningAnimators);
            }
        }
        this.mAnimators.clear();
        end();
    }

    public void setCanRemoveViews(boolean z8) {
        this.mCanRemoveViews = z8;
    }

    public AbstractC0532m setDuration(long j9) {
        this.mDuration = j9;
        return this;
    }

    public void setEpicenterCallback(f fVar) {
        this.mEpicenterCallback = fVar;
    }

    public AbstractC0532m setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.mMatchOrder = DEFAULT_MATCH_ORDER;
            return;
        }
        for (int i9 = 0; i9 < iArr.length; i9++) {
            if (!isValidMatch(iArr[i9])) {
                throw new IllegalArgumentException("matches contains invalid value");
            }
            if (alreadyContains(iArr, i9)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            }
        }
        this.mMatchOrder = (int[]) iArr.clone();
    }

    public void setPathMotion(AbstractC0523g abstractC0523g) {
        if (abstractC0523g == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = abstractC0523g;
        }
    }

    public void setPropagation(AbstractC0535p abstractC0535p) {
    }

    public AbstractC0532m setSceneRoot(ViewGroup viewGroup) {
        this.mSceneRoot = viewGroup;
        return this;
    }

    public AbstractC0532m setStartDelay(long j9) {
        this.mStartDelay = j9;
        return this;
    }

    public void start() {
        if (this.mNumInstances == 0) {
            ArrayList<g> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i9 = 0; i9 < size; i9++) {
                    ((g) arrayList2.get(i9)).mo3073a(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString("");
    }

    public AbstractC0532m addTarget(int i9) {
        if (i9 != 0) {
            this.mTargetIds.add(Integer.valueOf(i9));
        }
        return this;
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AbstractC0532m mo25565clone() {
        try {
            AbstractC0532m abstractC0532m = (AbstractC0532m) super.clone();
            abstractC0532m.mAnimators = new ArrayList<>();
            abstractC0532m.mStartValues = new C0540u();
            abstractC0532m.mEndValues = new C0540u();
            abstractC0532m.mStartValuesList = null;
            abstractC0532m.mEndValuesList = null;
            return abstractC0532m;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public AbstractC0532m excludeChildren(int i9, boolean z8) {
        this.mTargetIdChildExcludes = excludeId(this.mTargetIdChildExcludes, i9, z8);
        return this;
    }

    public AbstractC0532m excludeTarget(int i9, boolean z8) {
        this.mTargetIdExcludes = excludeId(this.mTargetIdExcludes, i9, z8);
        return this;
    }

    public AbstractC0532m removeTarget(int i9) {
        if (i9 != 0) {
            this.mTargetIds.remove(Integer.valueOf(i9));
        }
        return this;
    }

    public String toString(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.mDuration != -1) {
            str2 = str2 + "dur(" + this.mDuration + ") ";
        }
        if (this.mStartDelay != -1) {
            str2 = str2 + "dly(" + this.mStartDelay + ") ";
        }
        if (this.mInterpolator != null) {
            str2 = str2 + "interp(" + this.mInterpolator + ") ";
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.mTargetIds.size() > 0) {
            for (int i9 = 0; i9 < this.mTargetIds.size(); i9++) {
                if (i9 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.mTargetIds.get(i9);
            }
        }
        if (this.mTargets.size() > 0) {
            for (int i10 = 0; i10 < this.mTargets.size(); i10++) {
                if (i10 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.mTargets.get(i10);
            }
        }
        return str3 + ")";
    }

    public AbstractC0532m addTarget(String str) {
        if (this.mTargetNames == null) {
            this.mTargetNames = new ArrayList<>();
        }
        this.mTargetNames.add(str);
        return this;
    }

    public AbstractC0532m excludeChildren(Class cls, boolean z8) {
        this.mTargetTypeChildExcludes = excludeType(this.mTargetTypeChildExcludes, cls, z8);
        return this;
    }

    public AbstractC0532m excludeTarget(String str, boolean z8) {
        this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, str, z8);
        return this;
    }

    public AbstractC0532m removeTarget(String str) {
        ArrayList<String> arrayList = this.mTargetNames;
        if (arrayList != null) {
            arrayList.remove(str);
        }
        return this;
    }

    public AbstractC0532m excludeTarget(Class cls, boolean z8) {
        this.mTargetTypeExcludes = excludeType(this.mTargetTypeExcludes, cls, z8);
        return this;
    }

    public AbstractC0532m removeTarget(Class cls) {
        ArrayList<Class> arrayList = this.mTargetTypes;
        if (arrayList != null) {
            arrayList.remove(cls);
        }
        return this;
    }

    public AbstractC0532m addTarget(Class cls) {
        if (this.mTargetTypes == null) {
            this.mTargetTypes = new ArrayList<>();
        }
        this.mTargetTypes.add(cls);
        return this;
    }

    public AbstractC0532m(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0531l.f2930c);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long jM24373a = C6350d.m24373a(typedArrayObtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (jM24373a >= 0) {
            setDuration(jM24373a);
        }
        long jM24373a2 = C6350d.m24373a(typedArrayObtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (jM24373a2 > 0) {
            setStartDelay(jM24373a2);
        }
        int iM24374b = C6350d.m24374b(typedArrayObtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (iM24374b > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, iM24374b));
        }
        String strM24375c = C6350d.m24375c(typedArrayObtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (strM24375c != null) {
            setMatchOrder(parseMatchOrder(strM24375c));
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
