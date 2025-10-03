package androidx.recyclerview.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.core.widget.C0329d;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.widget.C0472a;
import androidx.recyclerview.widget.C0474c;
import androidx.recyclerview.widget.C0488q;
import androidx.recyclerview.widget.C0489r;
import androidx.recyclerview.widget.RunnableC0478g;
import com.google.android.exoplayer2.extractor.p037ts.TsExtractor;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import p021c0.C0702h;
import p042d0.C4614a0;
import p042d0.C4631j;
import p042d0.C4637m;
import p042d0.C4647u;
import p042d0.InterfaceC4633k;
import p052e0.C4693b;
import p052e0.C4704m;
import p142n0.C5346a;
import p142n0.C5347b;
import p251z.C6800f;

/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements InterfaceC4633k {
    static final boolean DEBUG = false;
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    static final long FOREVER_NS = Long.MAX_VALUE;
    public static final int HORIZONTAL = 0;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    static final int MAX_SCROLL_DURATION = 2000;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator;
    C0484m mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private InterfaceC0458s mActiveOnItemTouchListener;
    AbstractC0446g mAdapter;
    C0472a mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private InterfaceC0449j mChildDrawingOrderCallback;
    C0474c mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mEatenAccessibilityChangeFlags;
    private C0450k mEdgeEffectFactory;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    RunnableC0478g mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    boolean mIsAttached;
    AbstractC0451l mItemAnimator;
    private AbstractC0451l.b mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    final ArrayList<AbstractC0453n> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    AbstractC0454o mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int[] mNestedOffsets;
    private final C0463x mObserver;
    private List<InterfaceC0456q> mOnChildAttachStateListeners;
    private AbstractC0457r mOnFlingListener;
    private final ArrayList<InterfaceC0458s> mOnItemTouchListeners;
    final List<AbstractC0442c0> mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    RunnableC0478g.b mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final C0461v mRecycler;
    InterfaceC0462w mRecyclerListener;
    private EdgeEffect mRightGlow;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    final int[] mScrollConsumed;
    private AbstractC0459t mScrollListener;
    private List<AbstractC0459t> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    final int[] mScrollStepConsumed;
    private C4637m mScrollingChildHelper;
    final C0465z mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final RunnableC0440b0 mViewFlinger;
    private final C0489r.b mViewInfoProcessCallback;
    final C0489r mViewInfoStore;
    private static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    private static final int[] CLIP_TO_PADDING_ATTR = {R.attr.clipToPadding};
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    static final boolean POST_UPDATES_ON_ANIMATION = true;
    static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;

    /* renamed from: androidx.recyclerview.widget.RecyclerView$a */
    public class RunnableC0437a implements Runnable {
        public RunnableC0437a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = RecyclerView.this;
            if (!recyclerView.mFirstLayoutComplete || recyclerView.isLayoutRequested()) {
                return;
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            if (!recyclerView2.mIsAttached) {
                recyclerView2.requestLayout();
            } else if (recyclerView2.mLayoutFrozen) {
                recyclerView2.mLayoutWasDefered = true;
            } else {
                recyclerView2.consumePendingUpdateOperations();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$a0 */
    public static abstract class AbstractC0438a0 {
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$b */
    public class RunnableC0439b implements Runnable {
        public RunnableC0439b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC0451l abstractC0451l = RecyclerView.this.mItemAnimator;
            if (abstractC0451l != null) {
                abstractC0451l.mo2414u();
            }
            RecyclerView.this.mPostedAnimatorRunner = false;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$b0 */
    public class RunnableC0440b0 implements Runnable {

        /* renamed from: b */
        public int f2421b;

        /* renamed from: c */
        public int f2422c;

        /* renamed from: d */
        public OverScroller f2423d;

        /* renamed from: e */
        public Interpolator f2424e;

        /* renamed from: f */
        public boolean f2425f;

        /* renamed from: g */
        public boolean f2426g;

        public RunnableC0440b0() {
            Interpolator interpolator = RecyclerView.sQuinticInterpolator;
            this.f2424e = interpolator;
            this.f2425f = false;
            this.f2426g = false;
            this.f2423d = new OverScroller(RecyclerView.this.getContext(), interpolator);
        }

        /* renamed from: a */
        public final int m2344a(int i9, int i10, int i11, int i12) {
            int iRound;
            int iAbs = Math.abs(i9);
            int iAbs2 = Math.abs(i10);
            boolean z8 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i11 * i11) + (i12 * i12));
            int iSqrt2 = (int) Math.sqrt((i9 * i9) + (i10 * i10));
            RecyclerView recyclerView = RecyclerView.this;
            int width = z8 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i13 = width / 2;
            float f9 = width;
            float f10 = i13;
            float fM2346c = f10 + (m2346c(Math.min(1.0f, (iSqrt2 * 1.0f) / f9)) * f10);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fM2346c / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z8) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f9) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        /* renamed from: b */
        public final void m2345b() {
            this.f2426g = false;
            this.f2425f = true;
        }

        /* renamed from: c */
        public final float m2346c(float f9) {
            return (float) Math.sin((f9 - 0.5f) * 0.47123894f);
        }

        /* renamed from: d */
        public final void m2347d() {
            this.f2425f = false;
            if (this.f2426g) {
                m2349f();
            }
        }

        /* renamed from: e */
        public void m2348e(int i9, int i10) {
            RecyclerView.this.setScrollState(2);
            this.f2422c = 0;
            this.f2421b = 0;
            this.f2423d.fling(0, 0, i9, i10, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            m2349f();
        }

        /* renamed from: f */
        public void m2349f() {
            if (this.f2425f) {
                this.f2426g = true;
            } else {
                RecyclerView.this.removeCallbacks(this);
                C4647u.m18525U(RecyclerView.this, this);
            }
        }

        /* renamed from: g */
        public void m2350g(int i9, int i10) {
            m2352i(i9, i10, 0, 0);
        }

        /* renamed from: h */
        public void m2351h(int i9, int i10, int i11) {
            m2353j(i9, i10, i11, RecyclerView.sQuinticInterpolator);
        }

        /* renamed from: i */
        public void m2352i(int i9, int i10, int i11, int i12) {
            m2351h(i9, i10, m2344a(i9, i10, i11, i12));
        }

        /* renamed from: j */
        public void m2353j(int i9, int i10, int i11, Interpolator interpolator) {
            if (this.f2424e != interpolator) {
                this.f2424e = interpolator;
                this.f2423d = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            RecyclerView.this.setScrollState(2);
            this.f2422c = 0;
            this.f2421b = 0;
            this.f2423d.startScroll(0, 0, i9, i10, i11);
            m2349f();
        }

        /* renamed from: k */
        public void m2354k(int i9, int i10, Interpolator interpolator) {
            int iM2344a = m2344a(i9, i10, 0, 0);
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            m2353j(i9, i10, iM2344a, interpolator);
        }

        /* renamed from: l */
        public void m2355l() {
            RecyclerView.this.removeCallbacks(this);
            this.f2423d.abortAnimation();
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00e7  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00ee  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00f7  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            int i9;
            int i10;
            int i11;
            int i12;
            int i13;
            if (RecyclerView.this.mLayout == null) {
                m2355l();
                return;
            }
            m2345b();
            RecyclerView.this.consumePendingUpdateOperations();
            OverScroller overScroller = this.f2423d;
            AbstractC0464y abstractC0464y = RecyclerView.this.mLayout.f2448g;
            if (overScroller.computeScrollOffset()) {
                int[] iArr = RecyclerView.this.mScrollConsumed;
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i14 = currX - this.f2421b;
                int i15 = currY - this.f2422c;
                this.f2421b = currX;
                this.f2422c = currY;
                if (RecyclerView.this.dispatchNestedPreScroll(i14, i15, iArr, null, 1)) {
                    i14 -= iArr[0];
                    i15 -= iArr[1];
                }
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mAdapter != null) {
                    recyclerView.scrollStep(i14, i15, recyclerView.mScrollStepConsumed);
                    int[] iArr2 = RecyclerView.this.mScrollStepConsumed;
                    i10 = iArr2[0];
                    i9 = iArr2[1];
                    i11 = i14 - i10;
                    i12 = i15 - i9;
                    if (abstractC0464y != null && !abstractC0464y.m2602g() && abstractC0464y.m2603h()) {
                        int iM2620b = RecyclerView.this.mState.m2620b();
                        if (iM2620b == 0) {
                            abstractC0464y.m2613r();
                        } else if (abstractC0464y.m2601f() >= iM2620b) {
                            abstractC0464y.m2611p(iM2620b - 1);
                            abstractC0464y.m2605j(i14 - i11, i15 - i12);
                        } else {
                            abstractC0464y.m2605j(i14 - i11, i15 - i12);
                        }
                    }
                } else {
                    i9 = 0;
                    i10 = 0;
                    i11 = 0;
                    i12 = 0;
                }
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(i14, i15);
                }
                if (!RecyclerView.this.dispatchNestedScroll(i10, i9, i11, i12, null, 1) && (i11 != 0 || i12 != 0)) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    if (i11 == currX) {
                        i13 = 0;
                        if (i12 == currY) {
                            currVelocity = 0;
                            if (RecyclerView.this.getOverScrollMode() != 2) {
                                RecyclerView.this.absorbGlows(i13, currVelocity);
                            }
                            if ((i13 == 0 || i11 == currX || overScroller.getFinalX() == 0) && (currVelocity != 0 || i12 == currY || overScroller.getFinalY() == 0)) {
                                overScroller.abortAnimation();
                            }
                        } else {
                            if (i12 < 0) {
                                currVelocity = -currVelocity;
                            } else if (i12 <= 0) {
                            }
                            if (RecyclerView.this.getOverScrollMode() != 2) {
                            }
                            if (i13 == 0) {
                                overScroller.abortAnimation();
                            } else {
                                overScroller.abortAnimation();
                            }
                        }
                    } else {
                        if (i11 < 0) {
                            i13 = -currVelocity;
                        } else if (i11 > 0) {
                            i13 = currVelocity;
                        }
                        if (i12 == currY) {
                        }
                    }
                }
                if (i10 != 0 || i9 != 0) {
                    RecyclerView.this.dispatchOnScrolled(i10, i9);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z8 = (i14 == 0 && i15 == 0) || (i14 != 0 && RecyclerView.this.mLayout.mo2298k() && i10 == i14) || (i15 != 0 && RecyclerView.this.mLayout.mo2300l() && i9 == i15);
                if (overScroller.isFinished() || !(z8 || RecyclerView.this.hasNestedScrollingParent(1))) {
                    RecyclerView.this.setScrollState(0);
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView.this.mPrefetchRegistry.m2842b();
                    }
                    RecyclerView.this.stopNestedScroll(1);
                } else {
                    m2349f();
                    RecyclerView recyclerView2 = RecyclerView.this;
                    RunnableC0478g runnableC0478g = recyclerView2.mGapWorker;
                    if (runnableC0478g != null) {
                        runnableC0478g.m2836f(recyclerView2, i14, i15);
                    }
                }
            }
            if (abstractC0464y != null) {
                if (abstractC0464y.m2602g()) {
                    abstractC0464y.m2605j(0, 0);
                }
                if (!this.f2426g) {
                    abstractC0464y.m2613r();
                }
            }
            m2347d();
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$c */
    public static class InterpolatorC0441c implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f9) {
            float f10 = f9 - 1.0f;
            return (f10 * f10 * f10 * f10 * f10) + 1.0f;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$c0 */
    public static abstract class AbstractC0442c0 {
        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_SET_A11Y_ITEM_DELEGATE = 16384;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        int mFlags;
        WeakReference<RecyclerView> mNestedRecyclerView;
        RecyclerView mOwnerRecyclerView;
        int mPosition = -1;
        int mOldPosition = -1;
        long mItemId = -1;
        int mItemViewType = -1;
        int mPreLayoutPosition = -1;
        AbstractC0442c0 mShadowedHolder = null;
        AbstractC0442c0 mShadowingHolder = null;
        List<Object> mPayloads = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        C0461v mScrapContainer = null;
        boolean mInChangeScrap = false;
        private int mWasImportantForAccessibilityBeforeHidden = 0;
        int mPendingAccessibilityState = -1;

        public AbstractC0442c0(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        private void createPayloadsIfNeeded() {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
        }

        public void addChangePayload(Object obj) {
            if (obj == null) {
                addFlags(1024);
            } else if ((1024 & this.mFlags) == 0) {
                createPayloadsIfNeeded();
                this.mPayloads.add(obj);
            }
        }

        public void addFlags(int i9) {
            this.mFlags = i9 | this.mFlags;
        }

        public void clearOldPosition() {
            this.mOldPosition = -1;
            this.mPreLayoutPosition = -1;
        }

        public void clearPayload() {
            List<Object> list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
        }

        public void clearReturnedFromScrapFlag() {
            this.mFlags &= -33;
        }

        public void clearTmpDetachFlag() {
            this.mFlags &= -257;
        }

        public boolean doesTransientStatePreventRecycling() {
            return (this.mFlags & 16) == 0 && C4647u.m18510F(this.itemView);
        }

        public void flagRemovedAndOffsetPosition(int i9, int i10, boolean z8) {
            addFlags(8);
            offsetPosition(i10, z8);
            this.mPosition = i9;
        }

        public final int getAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionFor(this);
        }

        public final long getItemId() {
            return this.mItemId;
        }

        public final int getItemViewType() {
            return this.mItemViewType;
        }

        public final int getLayoutPosition() {
            int i9 = this.mPreLayoutPosition;
            return i9 == -1 ? this.mPosition : i9;
        }

        public final int getOldPosition() {
            return this.mOldPosition;
        }

        @Deprecated
        public final int getPosition() {
            int i9 = this.mPreLayoutPosition;
            return i9 == -1 ? this.mPosition : i9;
        }

        public List<Object> getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            List<Object> list = this.mPayloads;
            return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
        }

        public boolean hasAnyOfTheFlags(int i9) {
            return (i9 & this.mFlags) != 0;
        }

        public boolean isAdapterPositionUnknown() {
            return (this.mFlags & FLAG_ADAPTER_POSITION_UNKNOWN) != 0 || isInvalid();
        }

        public boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            return (this.mFlags & 16) == 0 && !C4647u.m18510F(this.itemView);
        }

        public boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        public boolean isScrap() {
            return this.mScrapContainer != null;
        }

        public boolean isTmpDetached() {
            return (this.mFlags & FLAG_TMP_DETACHED) != 0;
        }

        public boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        public boolean needsUpdate() {
            return (this.mFlags & 2) != 0;
        }

        public void offsetPosition(int i9, boolean z8) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z8) {
                this.mPreLayoutPosition += i9;
            }
            this.mPosition += i9;
            if (this.itemView.getLayoutParams() != null) {
                ((C0455p) this.itemView.getLayoutParams()).f2468c = true;
            }
        }

        public void onEnteredHiddenState(RecyclerView recyclerView) {
            int i9 = this.mPendingAccessibilityState;
            if (i9 != -1) {
                this.mWasImportantForAccessibilityBeforeHidden = i9;
            } else {
                this.mWasImportantForAccessibilityBeforeHidden = C4647u.m18563q(this.itemView);
            }
            recyclerView.setChildImportantForAccessibilityInternal(this, 4);
        }

        public void onLeftHiddenState(RecyclerView recyclerView) {
            recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
            this.mWasImportantForAccessibilityBeforeHidden = 0;
        }

        public void resetInternal() {
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            clearPayload();
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        public void saveOldPosition() {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
        }

        public void setFlags(int i9, int i10) {
            this.mFlags = (i9 & i10) | (this.mFlags & (~i10));
        }

        public final void setIsRecyclable(boolean z8) {
            int i9 = this.mIsRecyclableCount;
            int i10 = z8 ? i9 - 1 : i9 + 1;
            this.mIsRecyclableCount = i10;
            if (i10 < 0) {
                this.mIsRecyclableCount = 0;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
                return;
            }
            if (!z8 && i10 == 1) {
                this.mFlags |= 16;
            } else if (z8 && i10 == 0) {
                this.mFlags &= -17;
            }
        }

        public void setScrapContainer(C0461v c0461v, boolean z8) {
            this.mScrapContainer = c0461v;
            this.mInChangeScrap = z8;
        }

        public boolean shouldBeKeptAsChild() {
            return (this.mFlags & 16) != 0;
        }

        public boolean shouldIgnore() {
            return (this.mFlags & FLAG_IGNORE) != 0;
        }

        public void stopIgnoring() {
            this.mFlags &= -129;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if (needsUpdate()) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if (isAdapterPositionUnknown()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public void unScrap() {
            this.mScrapContainer.m2565J(this);
        }

        public boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$d */
    public class C0443d implements C0489r.b {
        public C0443d() {
        }

        @Override // androidx.recyclerview.widget.C0489r.b
        /* renamed from: a */
        public void mo2356a(AbstractC0442c0 abstractC0442c0) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mLayout.m2499l1(abstractC0442c0.itemView, recyclerView.mRecycler);
        }

        @Override // androidx.recyclerview.widget.C0489r.b
        /* renamed from: b */
        public void mo2357b(AbstractC0442c0 abstractC0442c0, AbstractC0451l.c cVar, AbstractC0451l.c cVar2) {
            RecyclerView.this.animateAppearance(abstractC0442c0, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.C0489r.b
        /* renamed from: c */
        public void mo2358c(AbstractC0442c0 abstractC0442c0, AbstractC0451l.c cVar, AbstractC0451l.c cVar2) {
            RecyclerView.this.mRecycler.m2565J(abstractC0442c0);
            RecyclerView.this.animateDisappearance(abstractC0442c0, cVar, cVar2);
        }

        @Override // androidx.recyclerview.widget.C0489r.b
        /* renamed from: d */
        public void mo2359d(AbstractC0442c0 abstractC0442c0, AbstractC0451l.c cVar, AbstractC0451l.c cVar2) {
            abstractC0442c0.setIsRecyclable(false);
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mDataSetHasChangedAfterLayout) {
                if (recyclerView.mItemAnimator.mo2396b(abstractC0442c0, abstractC0442c0, cVar, cVar2)) {
                    RecyclerView.this.postAnimationRunner();
                }
            } else if (recyclerView.mItemAnimator.mo2398d(abstractC0442c0, cVar, cVar2)) {
                RecyclerView.this.postAnimationRunner();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$e */
    public class C0444e implements C0474c.b {
        public C0444e() {
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: a */
        public View mo2360a(int i9) {
            return RecyclerView.this.getChildAt(i9);
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: b */
        public void mo2361b(View view) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onEnteredHiddenState(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: c */
        public int mo2362c() {
            return RecyclerView.this.getChildCount();
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: d */
        public void mo2363d() {
            int iMo2362c = mo2362c();
            for (int i9 = 0; i9 < iMo2362c; i9++) {
                View viewMo2360a = mo2360a(i9);
                RecyclerView.this.dispatchChildDetached(viewMo2360a);
                viewMo2360a.clearAnimation();
            }
            RecyclerView.this.removeAllViews();
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: e */
        public int mo2364e(View view) {
            return RecyclerView.this.indexOfChild(view);
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: f */
        public AbstractC0442c0 mo2365f(View view) {
            return RecyclerView.getChildViewHolderInt(view);
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: g */
        public void mo2366g(int i9) {
            AbstractC0442c0 childViewHolderInt;
            View viewMo2360a = mo2360a(i9);
            if (viewMo2360a != null && (childViewHolderInt = RecyclerView.getChildViewHolderInt(viewMo2360a)) != null) {
                if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("called detach on an already detached child " + childViewHolderInt + RecyclerView.this.exceptionLabel());
                }
                childViewHolderInt.addFlags(256);
            }
            RecyclerView.this.detachViewFromParent(i9);
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: h */
        public void mo2367h(View view) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onLeftHiddenState(RecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: i */
        public void mo2368i(View view, int i9) {
            RecyclerView.this.addView(view, i9);
            RecyclerView.this.dispatchChildAttached(view);
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: j */
        public void mo2369j(int i9) {
            View childAt = RecyclerView.this.getChildAt(i9);
            if (childAt != null) {
                RecyclerView.this.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            RecyclerView.this.removeViewAt(i9);
        }

        @Override // androidx.recyclerview.widget.C0474c.b
        /* renamed from: k */
        public void mo2370k(View view, int i9, ViewGroup.LayoutParams layoutParams) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    throw new IllegalArgumentException("Called attach on a child which is not detached: " + childViewHolderInt + RecyclerView.this.exceptionLabel());
                }
                childViewHolderInt.clearTmpDetachFlag();
            }
            RecyclerView.this.attachViewToParent(view, i9, layoutParams);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$f */
    public class C0445f implements C0472a.a {
        public C0445f() {
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: a */
        public void mo2371a(int i9, int i10) {
            RecyclerView.this.offsetPositionRecordsForMove(i9, i10);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: b */
        public void mo2372b(C0472a.b bVar) {
            m2379i(bVar);
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: c */
        public void mo2373c(int i9, int i10, Object obj) {
            RecyclerView.this.viewRangeUpdate(i9, i10, obj);
            RecyclerView.this.mItemsChanged = true;
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: d */
        public void mo2374d(C0472a.b bVar) {
            m2379i(bVar);
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: e */
        public AbstractC0442c0 mo2375e(int i9) {
            AbstractC0442c0 abstractC0442c0FindViewHolderForPosition = RecyclerView.this.findViewHolderForPosition(i9, true);
            if (abstractC0442c0FindViewHolderForPosition == null || RecyclerView.this.mChildHelper.m2779n(abstractC0442c0FindViewHolderForPosition.itemView)) {
                return null;
            }
            return abstractC0442c0FindViewHolderForPosition;
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: f */
        public void mo2376f(int i9, int i10) {
            RecyclerView.this.offsetPositionRecordsForRemove(i9, i10, false);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: g */
        public void mo2377g(int i9, int i10) {
            RecyclerView.this.offsetPositionRecordsForInsert(i9, i10);
            RecyclerView.this.mItemsAddedOrRemoved = true;
        }

        @Override // androidx.recyclerview.widget.C0472a.a
        /* renamed from: h */
        public void mo2378h(int i9, int i10) {
            RecyclerView.this.offsetPositionRecordsForRemove(i9, i10, true);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mItemsAddedOrRemoved = true;
            recyclerView.mState.f2503d += i10;
        }

        /* renamed from: i */
        public void m2379i(C0472a.b bVar) {
            int i9 = bVar.f2581a;
            if (i9 == 1) {
                RecyclerView recyclerView = RecyclerView.this;
                recyclerView.mLayout.mo2224Q0(recyclerView, bVar.f2582b, bVar.f2584d);
                return;
            }
            if (i9 == 2) {
                RecyclerView recyclerView2 = RecyclerView.this;
                recyclerView2.mLayout.mo2230T0(recyclerView2, bVar.f2582b, bVar.f2584d);
            } else if (i9 == 4) {
                RecyclerView recyclerView3 = RecyclerView.this;
                recyclerView3.mLayout.mo2233V0(recyclerView3, bVar.f2582b, bVar.f2584d, bVar.f2583c);
            } else {
                if (i9 != 8) {
                    return;
                }
                RecyclerView recyclerView4 = RecyclerView.this;
                recyclerView4.mLayout.mo2228S0(recyclerView4, bVar.f2582b, bVar.f2584d, 1);
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$g */
    public static abstract class AbstractC0446g<VH extends AbstractC0442c0> {
        private final C0447h mObservable = new C0447h();
        private boolean mHasStableIds = false;

        public final void bindViewHolder(VH vh, int i9) {
            vh.mPosition = i9;
            if (hasStableIds()) {
                vh.mItemId = getItemId(i9);
            }
            vh.setFlags(1, 519);
            C6800f.m25355a(RecyclerView.TRACE_BIND_VIEW_TAG);
            onBindViewHolder(vh, i9, vh.getUnmodifiedPayloads());
            vh.clearPayload();
            ViewGroup.LayoutParams layoutParams = vh.itemView.getLayoutParams();
            if (layoutParams instanceof C0455p) {
                ((C0455p) layoutParams).f2468c = true;
            }
            C6800f.m25356b();
        }

        public final VH createViewHolder(ViewGroup viewGroup, int i9) {
            try {
                C6800f.m25355a(RecyclerView.TRACE_CREATE_VIEW_TAG);
                VH vh = (VH) onCreateViewHolder(viewGroup, i9);
                if (vh.itemView.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                vh.mItemViewType = i9;
                return vh;
            } finally {
                C6800f.m25356b();
            }
        }

        public abstract int getItemCount();

        public long getItemId(int i9) {
            return -1L;
        }

        public int getItemViewType(int i9) {
            return 0;
        }

        public final boolean hasObservers() {
            return this.mObservable.m2380a();
        }

        public final boolean hasStableIds() {
            return this.mHasStableIds;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.m2381b();
        }

        public final void notifyItemChanged(int i9) {
            this.mObservable.m2383d(i9, 1);
        }

        public final void notifyItemInserted(int i9) {
            this.mObservable.m2385f(i9, 1);
        }

        public final void notifyItemMoved(int i9, int i10) {
            this.mObservable.m2382c(i9, i10);
        }

        public final void notifyItemRangeChanged(int i9, int i10) {
            this.mObservable.m2383d(i9, i10);
        }

        public final void notifyItemRangeInserted(int i9, int i10) {
            this.mObservable.m2385f(i9, i10);
        }

        public final void notifyItemRangeRemoved(int i9, int i10) {
            this.mObservable.m2386g(i9, i10);
        }

        public final void notifyItemRemoved(int i9) {
            this.mObservable.m2386g(i9, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public abstract void onBindViewHolder(VH vh, int i9);

        public void onBindViewHolder(VH vh, int i9, List<Object> list) {
            onBindViewHolder(vh, i9);
        }

        public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i9);

        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        }

        public boolean onFailedToRecycleView(VH vh) {
            return false;
        }

        public void onViewAttachedToWindow(VH vh) {
        }

        public void onViewDetachedFromWindow(VH vh) {
        }

        public void onViewRecycled(VH vh) {
        }

        public void registerAdapterDataObserver(AbstractC0448i abstractC0448i) {
            this.mObservable.registerObserver(abstractC0448i);
        }

        public void setHasStableIds(boolean z8) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z8;
        }

        public void unregisterAdapterDataObserver(AbstractC0448i abstractC0448i) {
            this.mObservable.unregisterObserver(abstractC0448i);
        }

        public final void notifyItemChanged(int i9, Object obj) {
            this.mObservable.m2384e(i9, 1, obj);
        }

        public final void notifyItemRangeChanged(int i9, int i10, Object obj) {
            this.mObservable.m2384e(i9, i10, obj);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$h */
    public static class C0447h extends Observable<AbstractC0448i> {
        /* renamed from: a */
        public boolean m2380a() {
            return !((Observable) this).mObservers.isEmpty();
        }

        /* renamed from: b */
        public void m2381b() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AbstractC0448i) ((Observable) this).mObservers.get(size)).mo2387a();
            }
        }

        /* renamed from: c */
        public void m2382c(int i9, int i10) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AbstractC0448i) ((Observable) this).mObservers.get(size)).mo2391e(i9, i10, 1);
            }
        }

        /* renamed from: d */
        public void m2383d(int i9, int i10) {
            m2384e(i9, i10, null);
        }

        /* renamed from: e */
        public void m2384e(int i9, int i10, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AbstractC0448i) ((Observable) this).mObservers.get(size)).mo2389c(i9, i10, obj);
            }
        }

        /* renamed from: f */
        public void m2385f(int i9, int i10) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AbstractC0448i) ((Observable) this).mObservers.get(size)).mo2390d(i9, i10);
            }
        }

        /* renamed from: g */
        public void m2386g(int i9, int i10) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AbstractC0448i) ((Observable) this).mObservers.get(size)).mo2392f(i9, i10);
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$i */
    public static abstract class AbstractC0448i {
        /* renamed from: a */
        public void mo2387a() {
        }

        /* renamed from: b */
        public void m2388b(int i9, int i10) {
        }

        /* renamed from: c */
        public void mo2389c(int i9, int i10, Object obj) {
            m2388b(i9, i10);
        }

        /* renamed from: d */
        public void mo2390d(int i9, int i10) {
        }

        /* renamed from: e */
        public void mo2391e(int i9, int i10, int i11) {
        }

        /* renamed from: f */
        public void mo2392f(int i9, int i10) {
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$j */
    public interface InterfaceC0449j {
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$k */
    public static class C0450k {
        /* renamed from: a */
        public EdgeEffect m2393a(RecyclerView recyclerView, int i9) {
            return new EdgeEffect(recyclerView.getContext());
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$l */
    public static abstract class AbstractC0451l {

        /* renamed from: a */
        public b f2431a = null;

        /* renamed from: b */
        public ArrayList<a> f2432b = new ArrayList<>();

        /* renamed from: c */
        public long f2433c = 120;

        /* renamed from: d */
        public long f2434d = 120;

        /* renamed from: e */
        public long f2435e = 250;

        /* renamed from: f */
        public long f2436f = 250;

        /* renamed from: androidx.recyclerview.widget.RecyclerView$l$a */
        public interface a {
            /* renamed from: a */
            void m2416a();
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$l$b */
        public interface b {
            /* renamed from: a */
            void mo2417a(AbstractC0442c0 abstractC0442c0);
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$l$c */
        public static class c {

            /* renamed from: a */
            public int f2437a;

            /* renamed from: b */
            public int f2438b;

            /* renamed from: c */
            public int f2439c;

            /* renamed from: d */
            public int f2440d;

            /* renamed from: a */
            public c m2418a(AbstractC0442c0 abstractC0442c0) {
                return m2419b(abstractC0442c0, 0);
            }

            /* renamed from: b */
            public c m2419b(AbstractC0442c0 abstractC0442c0, int i9) {
                View view = abstractC0442c0.itemView;
                this.f2437a = view.getLeft();
                this.f2438b = view.getTop();
                this.f2439c = view.getRight();
                this.f2440d = view.getBottom();
                return this;
            }
        }

        /* renamed from: e */
        public static int m2394e(AbstractC0442c0 abstractC0442c0) {
            int i9 = abstractC0442c0.mFlags & 14;
            if (abstractC0442c0.isInvalid()) {
                return 4;
            }
            if ((i9 & 4) != 0) {
                return i9;
            }
            int oldPosition = abstractC0442c0.getOldPosition();
            int adapterPosition = abstractC0442c0.getAdapterPosition();
            return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? i9 : i9 | 2048;
        }

        /* renamed from: a */
        public abstract boolean mo2395a(AbstractC0442c0 abstractC0442c0, c cVar, c cVar2);

        /* renamed from: b */
        public abstract boolean mo2396b(AbstractC0442c0 abstractC0442c0, AbstractC0442c0 abstractC0442c02, c cVar, c cVar2);

        /* renamed from: c */
        public abstract boolean mo2397c(AbstractC0442c0 abstractC0442c0, c cVar, c cVar2);

        /* renamed from: d */
        public abstract boolean mo2398d(AbstractC0442c0 abstractC0442c0, c cVar, c cVar2);

        /* renamed from: f */
        public abstract boolean mo2399f(AbstractC0442c0 abstractC0442c0);

        /* renamed from: g */
        public boolean mo2400g(AbstractC0442c0 abstractC0442c0, List<Object> list) {
            return mo2399f(abstractC0442c0);
        }

        /* renamed from: h */
        public final void m2401h(AbstractC0442c0 abstractC0442c0) {
            m2411r(abstractC0442c0);
            b bVar = this.f2431a;
            if (bVar != null) {
                bVar.mo2417a(abstractC0442c0);
            }
        }

        /* renamed from: i */
        public final void m2402i() {
            int size = this.f2432b.size();
            for (int i9 = 0; i9 < size; i9++) {
                this.f2432b.get(i9).m2416a();
            }
            this.f2432b.clear();
        }

        /* renamed from: j */
        public abstract void mo2403j(AbstractC0442c0 abstractC0442c0);

        /* renamed from: k */
        public abstract void mo2404k();

        /* renamed from: l */
        public long m2405l() {
            return this.f2433c;
        }

        /* renamed from: m */
        public long m2406m() {
            return this.f2436f;
        }

        /* renamed from: n */
        public long m2407n() {
            return this.f2435e;
        }

        /* renamed from: o */
        public long m2408o() {
            return this.f2434d;
        }

        /* renamed from: p */
        public abstract boolean mo2409p();

        /* renamed from: q */
        public c m2410q() {
            return new c();
        }

        /* renamed from: r */
        public void m2411r(AbstractC0442c0 abstractC0442c0) {
        }

        /* renamed from: s */
        public c m2412s(C0465z c0465z, AbstractC0442c0 abstractC0442c0) {
            return m2410q().m2418a(abstractC0442c0);
        }

        /* renamed from: t */
        public c m2413t(C0465z c0465z, AbstractC0442c0 abstractC0442c0, int i9, List<Object> list) {
            return m2410q().m2418a(abstractC0442c0);
        }

        /* renamed from: u */
        public abstract void mo2414u();

        /* renamed from: v */
        public void m2415v(b bVar) {
            this.f2431a = bVar;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$m */
    public class C0452m implements AbstractC0451l.b {
        public C0452m() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0451l.b
        /* renamed from: a */
        public void mo2417a(AbstractC0442c0 abstractC0442c0) {
            abstractC0442c0.setIsRecyclable(true);
            if (abstractC0442c0.mShadowedHolder != null && abstractC0442c0.mShadowingHolder == null) {
                abstractC0442c0.mShadowedHolder = null;
            }
            abstractC0442c0.mShadowingHolder = null;
            if (abstractC0442c0.shouldBeKeptAsChild() || RecyclerView.this.removeAnimatingView(abstractC0442c0.itemView) || !abstractC0442c0.isTmpDetached()) {
                return;
            }
            RecyclerView.this.removeDetachedView(abstractC0442c0.itemView, false);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$n */
    public static abstract class AbstractC0453n {
        @Deprecated
        /* renamed from: d */
        public void m2420d(Rect rect, int i9, RecyclerView recyclerView) {
            rect.set(0, 0, 0, 0);
        }

        /* renamed from: e */
        public void mo2421e(Rect rect, View view, RecyclerView recyclerView, C0465z c0465z) {
            m2420d(rect, ((C0455p) view.getLayoutParams()).m2533a(), recyclerView);
        }

        @Deprecated
        /* renamed from: f */
        public void m2422f(Canvas canvas, RecyclerView recyclerView) {
        }

        /* renamed from: g */
        public void mo2423g(Canvas canvas, RecyclerView recyclerView, C0465z c0465z) {
            m2422f(canvas, recyclerView);
        }

        @Deprecated
        /* renamed from: h */
        public void m2424h(Canvas canvas, RecyclerView recyclerView) {
        }

        /* renamed from: i */
        public void mo2425i(Canvas canvas, RecyclerView recyclerView, C0465z c0465z) {
            m2424h(canvas, recyclerView);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$o */
    public static abstract class AbstractC0454o {

        /* renamed from: a */
        public C0474c f2442a;

        /* renamed from: b */
        public RecyclerView f2443b;

        /* renamed from: c */
        public final C0488q.b f2444c;

        /* renamed from: d */
        public final C0488q.b f2445d;

        /* renamed from: e */
        public C0488q f2446e;

        /* renamed from: f */
        public C0488q f2447f;

        /* renamed from: g */
        public AbstractC0464y f2448g;

        /* renamed from: h */
        public boolean f2449h;

        /* renamed from: i */
        public boolean f2450i;

        /* renamed from: j */
        public boolean f2451j;

        /* renamed from: k */
        public boolean f2452k;

        /* renamed from: l */
        public boolean f2453l;

        /* renamed from: m */
        public int f2454m;

        /* renamed from: n */
        public boolean f2455n;

        /* renamed from: o */
        public int f2456o;

        /* renamed from: p */
        public int f2457p;

        /* renamed from: q */
        public int f2458q;

        /* renamed from: r */
        public int f2459r;

        /* renamed from: androidx.recyclerview.widget.RecyclerView$o$a */
        public class a implements C0488q.b {
            public a() {
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: a */
            public View mo2527a(int i9) {
                return AbstractC0454o.this.m2446I(i9);
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: b */
            public int mo2528b(View view) {
                return AbstractC0454o.this.m2459Q(view) - ((ViewGroup.MarginLayoutParams) ((C0455p) view.getLayoutParams())).leftMargin;
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: c */
            public int mo2529c() {
                return AbstractC0454o.this.m2479d0();
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: d */
            public int mo2530d() {
                return AbstractC0454o.this.m2502n0() - AbstractC0454o.this.m2482e0();
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: e */
            public int mo2531e(View view) {
                return AbstractC0454o.this.m2462T(view) + ((ViewGroup.MarginLayoutParams) ((C0455p) view.getLayoutParams())).rightMargin;
            }
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$o$b */
        public class b implements C0488q.b {
            public b() {
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: a */
            public View mo2527a(int i9) {
                return AbstractC0454o.this.m2446I(i9);
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: b */
            public int mo2528b(View view) {
                return AbstractC0454o.this.m2463U(view) - ((ViewGroup.MarginLayoutParams) ((C0455p) view.getLayoutParams())).topMargin;
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: c */
            public int mo2529c() {
                return AbstractC0454o.this.m2485f0();
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: d */
            public int mo2530d() {
                return AbstractC0454o.this.m2466W() - AbstractC0454o.this.m2477c0();
            }

            @Override // androidx.recyclerview.widget.C0488q.b
            /* renamed from: e */
            public int mo2531e(View view) {
                return AbstractC0454o.this.m2456O(view) + ((ViewGroup.MarginLayoutParams) ((C0455p) view.getLayoutParams())).bottomMargin;
            }
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$o$c */
        public interface c {
            /* renamed from: a */
            void mo2532a(int i9, int i10);
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$o$d */
        public static class d {

            /* renamed from: a */
            public int f2462a;

            /* renamed from: b */
            public int f2463b;

            /* renamed from: c */
            public boolean f2464c;

            /* renamed from: d */
            public boolean f2465d;
        }

        public AbstractC0454o() {
            a aVar = new a();
            this.f2444c = aVar;
            b bVar = new b();
            this.f2445d = bVar;
            this.f2446e = new C0488q(aVar);
            this.f2447f = new C0488q(bVar);
            this.f2449h = false;
            this.f2450i = false;
            this.f2451j = false;
            this.f2452k = true;
            this.f2453l = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
        /* renamed from: K */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static int m2426K(int i9, int i10, int i11, int i12, boolean z8) {
            int iMax = Math.max(0, i9 - i11);
            if (z8) {
                if (i12 < 0) {
                    if (i12 != -1 || (i10 != Integer.MIN_VALUE && (i10 == 0 || i10 != 1073741824))) {
                        i10 = 0;
                        i12 = 0;
                    } else {
                        i12 = iMax;
                    }
                }
                i10 = 1073741824;
            } else if (i12 >= 0) {
                i10 = 1073741824;
            } else if (i12 != -1) {
                if (i12 == -2) {
                    if (i10 == Integer.MIN_VALUE || i10 == 1073741824) {
                        i12 = iMax;
                        i10 = Integer.MIN_VALUE;
                    } else {
                        i12 = iMax;
                        i10 = 0;
                    }
                }
            }
            return View.MeasureSpec.makeMeasureSpec(i12, i10);
        }

        /* renamed from: h0 */
        public static d m2427h0(Context context, AttributeSet attributeSet, int i9, int i10) {
            d dVar = new d();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5347b.RecyclerView, i9, i10);
            dVar.f2462a = typedArrayObtainStyledAttributes.getInt(C5347b.RecyclerView_android_orientation, 1);
            dVar.f2463b = typedArrayObtainStyledAttributes.getInt(C5347b.RecyclerView_spanCount, 1);
            dVar.f2464c = typedArrayObtainStyledAttributes.getBoolean(C5347b.RecyclerView_reverseLayout, false);
            dVar.f2465d = typedArrayObtainStyledAttributes.getBoolean(C5347b.RecyclerView_stackFromEnd, false);
            typedArrayObtainStyledAttributes.recycle();
            return dVar;
        }

        /* renamed from: n */
        public static int m2428n(int i9, int i10, int i11) {
            int mode = View.MeasureSpec.getMode(i9);
            int size = View.MeasureSpec.getSize(i9);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i10, i11) : size : Math.min(size, Math.max(i10, i11));
        }

        /* renamed from: v0 */
        public static boolean m2429v0(int i9, int i10, int i11) {
            int mode = View.MeasureSpec.getMode(i10);
            int size = View.MeasureSpec.getSize(i10);
            if (i11 > 0 && i9 != i11) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i9;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i9;
            }
            return true;
        }

        /* renamed from: A */
        public void m2430A(RecyclerView recyclerView, C0461v c0461v) {
            this.f2450i = false;
            mo2264H0(recyclerView, c0461v);
        }

        /* renamed from: A0 */
        public void m2431A0(int i9, int i10) {
            View viewM2446I = m2446I(i9);
            if (viewM2446I != null) {
                m2519x(i9);
                m2489h(viewM2446I, i10);
            } else {
                throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i9 + this.f2443b.toString());
            }
        }

        /* renamed from: A1 */
        public void m2432A1(int i9, int i10) {
            this.f2443b.setMeasuredDimension(i9, i10);
        }

        /* renamed from: B */
        public View m2433B(View view) {
            View viewFindContainingItemView;
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null || (viewFindContainingItemView = recyclerView.findContainingItemView(view)) == null || this.f2442a.m2779n(viewFindContainingItemView)) {
                return null;
            }
            return viewFindContainingItemView;
        }

        /* renamed from: B0 */
        public void mo2434B0(int i9) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                recyclerView.offsetChildrenHorizontal(i9);
            }
        }

        /* renamed from: B1 */
        public void mo2210B1(Rect rect, int i9, int i10) {
            m2432A1(m2428n(i9, rect.width() + m2479d0() + m2482e0(), m2475b0()), m2428n(i10, rect.height() + m2485f0() + m2477c0(), m2472a0()));
        }

        /* renamed from: C */
        public View mo2258C(int i9) {
            int iM2448J = m2448J();
            for (int i10 = 0; i10 < iM2448J; i10++) {
                View viewM2446I = m2446I(i10);
                AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(viewM2446I);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i9 && !childViewHolderInt.shouldIgnore() && (this.f2443b.mState.m2623e() || !childViewHolderInt.isRemoved())) {
                    return viewM2446I;
                }
            }
            return null;
        }

        /* renamed from: C0 */
        public void mo2435C0(int i9) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                recyclerView.offsetChildrenVertical(i9);
            }
        }

        /* renamed from: C1 */
        public void m2436C1(int i9, int i10) {
            int iM2448J = m2448J();
            if (iM2448J == 0) {
                this.f2443b.defaultOnMeasure(i9, i10);
                return;
            }
            int i11 = Integer.MIN_VALUE;
            int i12 = Integer.MAX_VALUE;
            int i13 = Integer.MIN_VALUE;
            int i14 = Integer.MAX_VALUE;
            for (int i15 = 0; i15 < iM2448J; i15++) {
                View viewM2446I = m2446I(i15);
                Rect rect = this.f2443b.mTempRect;
                m2457P(viewM2446I, rect);
                int i16 = rect.left;
                if (i16 < i14) {
                    i14 = i16;
                }
                int i17 = rect.right;
                if (i17 > i11) {
                    i11 = i17;
                }
                int i18 = rect.top;
                if (i18 < i12) {
                    i12 = i18;
                }
                int i19 = rect.bottom;
                if (i19 > i13) {
                    i13 = i19;
                }
            }
            this.f2443b.mTempRect.set(i14, i12, i11, i13);
            mo2210B1(this.f2443b.mTempRect, i9, i10);
        }

        /* renamed from: D */
        public abstract C0455p mo2212D();

        /* renamed from: D0 */
        public void m2437D0(AbstractC0446g abstractC0446g, AbstractC0446g abstractC0446g2) {
        }

        /* renamed from: D1 */
        public void m2438D1(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.f2443b = null;
                this.f2442a = null;
                this.f2458q = 0;
                this.f2459r = 0;
            } else {
                this.f2443b = recyclerView;
                this.f2442a = recyclerView.mChildHelper;
                this.f2458q = recyclerView.getWidth();
                this.f2459r = recyclerView.getHeight();
            }
            this.f2456o = 1073741824;
            this.f2457p = 1073741824;
        }

        /* renamed from: E */
        public C0455p mo2213E(Context context, AttributeSet attributeSet) {
            return new C0455p(context, attributeSet);
        }

        /* renamed from: E0 */
        public boolean m2439E0(RecyclerView recyclerView, ArrayList<View> arrayList, int i9, int i10) {
            return false;
        }

        /* renamed from: E1 */
        public boolean m2440E1(View view, int i9, int i10, C0455p c0455p) {
            return (!view.isLayoutRequested() && this.f2452k && m2429v0(view.getWidth(), i9, ((ViewGroup.MarginLayoutParams) c0455p).width) && m2429v0(view.getHeight(), i10, ((ViewGroup.MarginLayoutParams) c0455p).height)) ? false : true;
        }

        /* renamed from: F */
        public C0455p mo2214F(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof C0455p ? new C0455p((C0455p) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0455p((ViewGroup.MarginLayoutParams) layoutParams) : new C0455p(layoutParams);
        }

        /* renamed from: F0 */
        public void m2441F0(RecyclerView recyclerView) {
        }

        /* renamed from: F1 */
        public boolean mo2261F1() {
            return false;
        }

        /* renamed from: G */
        public int m2442G() {
            return -1;
        }

        @Deprecated
        /* renamed from: G0 */
        public void m2443G0(RecyclerView recyclerView) {
        }

        /* renamed from: G1 */
        public boolean m2444G1(View view, int i9, int i10, C0455p c0455p) {
            return (this.f2452k && m2429v0(view.getMeasuredWidth(), i9, ((ViewGroup.MarginLayoutParams) c0455p).width) && m2429v0(view.getMeasuredHeight(), i10, ((ViewGroup.MarginLayoutParams) c0455p).height)) ? false : true;
        }

        /* renamed from: H */
        public int m2445H(View view) {
            return ((C0455p) view.getLayoutParams()).f2467b.bottom;
        }

        /* renamed from: H0 */
        public void mo2264H0(RecyclerView recyclerView, C0461v c0461v) {
            m2443G0(recyclerView);
        }

        /* renamed from: H1 */
        public void mo2265H1(RecyclerView recyclerView, C0465z c0465z, int i9) {
            Log.e(RecyclerView.TAG, "You must override smoothScrollToPosition to support smooth scrolling");
        }

        /* renamed from: I */
        public View m2446I(int i9) {
            C0474c c0474c = this.f2442a;
            if (c0474c != null) {
                return c0474c.m2771f(i9);
            }
            return null;
        }

        /* renamed from: I0 */
        public View mo2215I0(View view, int i9, C0461v c0461v, C0465z c0465z) {
            return null;
        }

        /* renamed from: I1 */
        public void m2447I1(AbstractC0464y abstractC0464y) {
            AbstractC0464y abstractC0464y2 = this.f2448g;
            if (abstractC0464y2 != null && abstractC0464y != abstractC0464y2 && abstractC0464y2.m2603h()) {
                this.f2448g.m2613r();
            }
            this.f2448g = abstractC0464y;
            abstractC0464y.m2612q(this.f2443b, this);
        }

        /* renamed from: J */
        public int m2448J() {
            C0474c c0474c = this.f2442a;
            if (c0474c != null) {
                return c0474c.m2772g();
            }
            return 0;
        }

        /* renamed from: J0 */
        public void mo2268J0(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f2443b;
            m2450K0(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
        }

        /* renamed from: J1 */
        public void m2449J1() {
            AbstractC0464y abstractC0464y = this.f2448g;
            if (abstractC0464y != null) {
                abstractC0464y.m2613r();
            }
        }

        /* renamed from: K0 */
        public void m2450K0(C0461v c0461v, C0465z c0465z, AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z8 = true;
            if (!recyclerView.canScrollVertically(1) && !this.f2443b.canScrollVertically(-1) && !this.f2443b.canScrollHorizontally(-1) && !this.f2443b.canScrollHorizontally(1)) {
                z8 = false;
            }
            accessibilityEvent.setScrollable(z8);
            AbstractC0446g abstractC0446g = this.f2443b.mAdapter;
            if (abstractC0446g != null) {
                accessibilityEvent.setItemCount(abstractC0446g.getItemCount());
            }
        }

        /* renamed from: K1 */
        public boolean mo2216K1() {
            return false;
        }

        /* renamed from: L */
        public final int[] m2451L(RecyclerView recyclerView, View view, Rect rect, boolean z8) {
            int[] iArr = new int[2];
            int iM2479d0 = m2479d0();
            int iM2485f0 = m2485f0();
            int iM2502n0 = m2502n0() - m2482e0();
            int iM2466W = m2466W() - m2477c0();
            int left = (view.getLeft() + rect.left) - view.getScrollX();
            int top = (view.getTop() + rect.top) - view.getScrollY();
            int iWidth = rect.width() + left;
            int iHeight = rect.height() + top;
            int i9 = left - iM2479d0;
            int iMin = Math.min(0, i9);
            int i10 = top - iM2485f0;
            int iMin2 = Math.min(0, i10);
            int i11 = iWidth - iM2502n0;
            int iMax = Math.max(0, i11);
            int iMax2 = Math.max(0, iHeight - iM2466W);
            if (m2468Y() != 1) {
                if (iMin == 0) {
                    iMin = Math.min(i9, iMax);
                }
                iMax = iMin;
            } else if (iMax == 0) {
                iMax = Math.max(iMin, i11);
            }
            if (iMin2 == 0) {
                iMin2 = Math.min(i10, iMax2);
            }
            iArr[0] = iMax;
            iArr[1] = iMin2;
            return iArr;
        }

        /* renamed from: L0 */
        public void m2452L0(C0461v c0461v, C0465z c0465z, C4704m c4704m) {
            if (this.f2443b.canScrollVertically(-1) || this.f2443b.canScrollHorizontally(-1)) {
                c4704m.m18787a(UserMetadata.MAX_INTERNAL_KEY_SIZE);
                c4704m.m18814o0(true);
            }
            if (this.f2443b.canScrollVertically(1) || this.f2443b.canScrollHorizontally(1)) {
                c4704m.m18787a(4096);
                c4704m.m18814o0(true);
            }
            c4704m.m18783W(C4704m.b.m18836a(mo2243j0(c0461v, c0465z), mo2220N(c0461v, c0465z), m2515u0(c0461v, c0465z), m2496k0(c0461v, c0465z)));
        }

        /* renamed from: M */
        public boolean m2453M() {
            RecyclerView recyclerView = this.f2443b;
            return recyclerView != null && recyclerView.mClipToPadding;
        }

        /* renamed from: M0 */
        public void m2454M0(C4704m c4704m) {
            RecyclerView recyclerView = this.f2443b;
            m2452L0(recyclerView.mRecycler, recyclerView.mState, c4704m);
        }

        /* renamed from: N */
        public int mo2220N(C0461v c0461v, C0465z c0465z) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null || recyclerView.mAdapter == null || !mo2298k()) {
                return 1;
            }
            return this.f2443b.mAdapter.getItemCount();
        }

        /* renamed from: N0 */
        public void m2455N0(View view, C4704m c4704m) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null || childViewHolderInt.isRemoved() || this.f2442a.m2779n(childViewHolderInt.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.f2443b;
            mo2222O0(recyclerView.mRecycler, recyclerView.mState, view, c4704m);
        }

        /* renamed from: O */
        public int m2456O(View view) {
            return view.getBottom() + m2445H(view);
        }

        /* renamed from: O0 */
        public void mo2222O0(C0461v c0461v, C0465z c0465z, View view, C4704m c4704m) {
            c4704m.m18784X(C4704m.c.m18837a(mo2300l() ? m2487g0(view) : 0, 1, mo2298k() ? m2487g0(view) : 0, 1, false, false));
        }

        /* renamed from: P */
        public void m2457P(View view, Rect rect) {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        /* renamed from: P0 */
        public View m2458P0(View view, int i9) {
            return null;
        }

        /* renamed from: Q */
        public int m2459Q(View view) {
            return view.getLeft() - m2470Z(view);
        }

        /* renamed from: Q0 */
        public void mo2224Q0(RecyclerView recyclerView, int i9, int i10) {
        }

        /* renamed from: R */
        public int m2460R(View view) {
            Rect rect = ((C0455p) view.getLayoutParams()).f2467b;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        /* renamed from: R0 */
        public void mo2226R0(RecyclerView recyclerView) {
        }

        /* renamed from: S */
        public int m2461S(View view) {
            Rect rect = ((C0455p) view.getLayoutParams()).f2467b;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        /* renamed from: S0 */
        public void mo2228S0(RecyclerView recyclerView, int i9, int i10, int i11) {
        }

        /* renamed from: T */
        public int m2462T(View view) {
            return view.getRight() + m2492i0(view);
        }

        /* renamed from: T0 */
        public void mo2230T0(RecyclerView recyclerView, int i9, int i10) {
        }

        /* renamed from: U */
        public int m2463U(View view) {
            return view.getTop() - m2498l0(view);
        }

        /* renamed from: U0 */
        public void m2464U0(RecyclerView recyclerView, int i9, int i10) {
        }

        /* renamed from: V */
        public View m2465V() {
            View focusedChild;
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.f2442a.m2779n(focusedChild)) {
                return null;
            }
            return focusedChild;
        }

        /* renamed from: V0 */
        public void mo2233V0(RecyclerView recyclerView, int i9, int i10, Object obj) {
            m2464U0(recyclerView, i9, i10);
        }

        /* renamed from: W */
        public int m2466W() {
            return this.f2459r;
        }

        /* renamed from: W0 */
        public void mo2235W0(C0461v c0461v, C0465z c0465z) {
            Log.e(RecyclerView.TAG, "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        /* renamed from: X */
        public int m2467X() {
            return this.f2457p;
        }

        /* renamed from: X0 */
        public void mo2237X0(C0465z c0465z) {
        }

        /* renamed from: Y */
        public int m2468Y() {
            return C4647u.m18567s(this.f2443b);
        }

        /* renamed from: Y0 */
        public void m2469Y0(C0461v c0461v, C0465z c0465z, int i9, int i10) {
            this.f2443b.defaultOnMeasure(i9, i10);
        }

        /* renamed from: Z */
        public int m2470Z(View view) {
            return ((C0455p) view.getLayoutParams()).f2467b.left;
        }

        @Deprecated
        /* renamed from: Z0 */
        public boolean m2471Z0(RecyclerView recyclerView, View view, View view2) {
            return m2518w0() || recyclerView.isComputingLayout();
        }

        /* renamed from: a0 */
        public int m2472a0() {
            return C4647u.m18568t(this.f2443b);
        }

        /* renamed from: a1 */
        public boolean m2473a1(RecyclerView recyclerView, C0465z c0465z, View view, View view2) {
            return m2471Z0(recyclerView, view, view2);
        }

        /* renamed from: b */
        public void m2474b(View view) {
            m2476c(view, -1);
        }

        /* renamed from: b0 */
        public int m2475b0() {
            return C4647u.m18569u(this.f2443b);
        }

        /* renamed from: b1 */
        public void mo2287b1(Parcelable parcelable) {
        }

        /* renamed from: c */
        public void m2476c(View view, int i9) {
            m2484f(view, i9, true);
        }

        /* renamed from: c0 */
        public int m2477c0() {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        /* renamed from: c1 */
        public Parcelable mo2289c1() {
            return null;
        }

        /* renamed from: d */
        public void m2478d(View view) {
            m2481e(view, -1);
        }

        /* renamed from: d0 */
        public int m2479d0() {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        /* renamed from: d1 */
        public void mo2480d1(int i9) {
        }

        /* renamed from: e */
        public void m2481e(View view, int i9) {
            m2484f(view, i9, false);
        }

        /* renamed from: e0 */
        public int m2482e0() {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        /* renamed from: e1 */
        public void m2483e1(AbstractC0464y abstractC0464y) {
            if (this.f2448g == abstractC0464y) {
                this.f2448g = null;
            }
        }

        /* renamed from: f */
        public final void m2484f(View view, int i9, boolean z8) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z8 || childViewHolderInt.isRemoved()) {
                this.f2443b.mViewInfoStore.m2943b(childViewHolderInt);
            } else {
                this.f2443b.mViewInfoStore.m2957p(childViewHolderInt);
            }
            C0455p c0455p = (C0455p) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.unScrap();
                } else {
                    childViewHolderInt.clearReturnedFromScrapFlag();
                }
                this.f2442a.m2768c(view, i9, view.getLayoutParams(), false);
            } else if (view.getParent() == this.f2443b) {
                int iM2778m = this.f2442a.m2778m(view);
                if (i9 == -1) {
                    i9 = this.f2442a.m2772g();
                }
                if (iM2778m == -1) {
                    throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.f2443b.indexOfChild(view) + this.f2443b.exceptionLabel());
                }
                if (iM2778m != i9) {
                    this.f2443b.mLayout.m2431A0(iM2778m, i9);
                }
            } else {
                this.f2442a.m2766a(view, i9, false);
                c0455p.f2468c = true;
                AbstractC0464y abstractC0464y = this.f2448g;
                if (abstractC0464y != null && abstractC0464y.m2603h()) {
                    this.f2448g.m2606k(view);
                }
            }
            if (c0455p.f2469d) {
                childViewHolderInt.itemView.invalidate();
                c0455p.f2469d = false;
            }
        }

        /* renamed from: f0 */
        public int m2485f0() {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        /* renamed from: f1 */
        public boolean m2486f1(int i9, Bundle bundle) {
            RecyclerView recyclerView = this.f2443b;
            return m2488g1(recyclerView.mRecycler, recyclerView.mState, i9, bundle);
        }

        /* renamed from: g */
        public void mo2293g(String str) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        /* renamed from: g0 */
        public int m2487g0(View view) {
            return ((C0455p) view.getLayoutParams()).m2533a();
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x006f A[PHI: r2
          0x006f: PHI (r2v8 int) = (r2v4 int), (r2v12 int) binds: [B:22:0x005e, B:15:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
        /* renamed from: g1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public boolean m2488g1(C0461v c0461v, C0465z c0465z, int i9, Bundle bundle) {
            int iM2466W;
            int iM2502n0;
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null) {
                return false;
            }
            if (i9 == 4096) {
                iM2466W = recyclerView.canScrollVertically(1) ? (m2466W() - m2485f0()) - m2477c0() : 0;
                if (this.f2443b.canScrollHorizontally(1)) {
                    iM2502n0 = (m2502n0() - m2479d0()) - m2482e0();
                }
            } else if (i9 != 8192) {
                iM2466W = 0;
                iM2502n0 = 0;
            } else {
                iM2466W = recyclerView.canScrollVertically(-1) ? -((m2466W() - m2485f0()) - m2477c0()) : 0;
                iM2502n0 = this.f2443b.canScrollHorizontally(-1) ? -((m2502n0() - m2479d0()) - m2482e0()) : 0;
            }
            if (iM2466W == 0 && iM2502n0 == 0) {
                return false;
            }
            this.f2443b.smoothScrollBy(iM2502n0, iM2466W);
            return true;
        }

        /* renamed from: h */
        public void m2489h(View view, int i9) {
            m2491i(view, i9, (C0455p) view.getLayoutParams());
        }

        /* renamed from: h1 */
        public boolean m2490h1(View view, int i9, Bundle bundle) {
            RecyclerView recyclerView = this.f2443b;
            return m2493i1(recyclerView.mRecycler, recyclerView.mState, view, i9, bundle);
        }

        /* renamed from: i */
        public void m2491i(View view, int i9, C0455p c0455p) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isRemoved()) {
                this.f2443b.mViewInfoStore.m2943b(childViewHolderInt);
            } else {
                this.f2443b.mViewInfoStore.m2957p(childViewHolderInt);
            }
            this.f2442a.m2768c(view, i9, c0455p, childViewHolderInt.isRemoved());
        }

        /* renamed from: i0 */
        public int m2492i0(View view) {
            return ((C0455p) view.getLayoutParams()).f2467b.right;
        }

        /* renamed from: i1 */
        public boolean m2493i1(C0461v c0461v, C0465z c0465z, View view, int i9, Bundle bundle) {
            return false;
        }

        /* renamed from: j */
        public void m2494j(View view, Rect rect) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        /* renamed from: j0 */
        public int mo2243j0(C0461v c0461v, C0465z c0465z) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView == null || recyclerView.mAdapter == null || !mo2300l()) {
                return 1;
            }
            return this.f2443b.mAdapter.getItemCount();
        }

        /* renamed from: j1 */
        public void m2495j1(C0461v c0461v) {
            for (int iM2448J = m2448J() - 1; iM2448J >= 0; iM2448J--) {
                if (!RecyclerView.getChildViewHolderInt(m2446I(iM2448J)).shouldIgnore()) {
                    m2501m1(iM2448J, c0461v);
                }
            }
        }

        /* renamed from: k */
        public boolean mo2298k() {
            return false;
        }

        /* renamed from: k0 */
        public int m2496k0(C0461v c0461v, C0465z c0465z) {
            return 0;
        }

        /* renamed from: k1 */
        public void m2497k1(C0461v c0461v) {
            int iM2578j = c0461v.m2578j();
            for (int i9 = iM2578j - 1; i9 >= 0; i9--) {
                View viewM2582n = c0461v.m2582n(i9);
                AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(viewM2582n);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.f2443b.removeDetachedView(viewM2582n, false);
                    }
                    AbstractC0451l abstractC0451l = this.f2443b.mItemAnimator;
                    if (abstractC0451l != null) {
                        abstractC0451l.mo2403j(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    c0461v.m2593y(viewM2582n);
                }
            }
            c0461v.m2573e();
            if (iM2578j > 0) {
                this.f2443b.invalidate();
            }
        }

        /* renamed from: l */
        public boolean mo2300l() {
            return false;
        }

        /* renamed from: l0 */
        public int m2498l0(View view) {
            return ((C0455p) view.getLayoutParams()).f2467b.top;
        }

        /* renamed from: l1 */
        public void m2499l1(View view, C0461v c0461v) {
            m2505o1(view);
            c0461v.m2557B(view);
        }

        /* renamed from: m */
        public boolean mo2244m(C0455p c0455p) {
            return c0455p != null;
        }

        /* renamed from: m0 */
        public void m2500m0(View view, boolean z8, Rect rect) {
            Matrix matrix;
            if (z8) {
                Rect rect2 = ((C0455p) view.getLayoutParams()).f2467b;
                rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            } else {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (this.f2443b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.f2443b.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        /* renamed from: m1 */
        public void m2501m1(int i9, C0461v c0461v) {
            View viewM2446I = m2446I(i9);
            m2507p1(i9);
            c0461v.m2557B(viewM2446I);
        }

        /* renamed from: n0 */
        public int m2502n0() {
            return this.f2458q;
        }

        /* renamed from: n1 */
        public boolean m2503n1(Runnable runnable) {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                return recyclerView.removeCallbacks(runnable);
            }
            return false;
        }

        /* renamed from: o */
        public void mo2304o(int i9, int i10, C0465z c0465z, c cVar) {
        }

        /* renamed from: o0 */
        public int m2504o0() {
            return this.f2456o;
        }

        /* renamed from: o1 */
        public void m2505o1(View view) {
            this.f2442a.m2781p(view);
        }

        /* renamed from: p */
        public void mo2306p(int i9, c cVar) {
        }

        /* renamed from: p0 */
        public boolean m2506p0() {
            int iM2448J = m2448J();
            for (int i9 = 0; i9 < iM2448J; i9++) {
                ViewGroup.LayoutParams layoutParams = m2446I(i9).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: p1 */
        public void m2507p1(int i9) {
            if (m2446I(i9) != null) {
                this.f2442a.m2782q(i9);
            }
        }

        /* renamed from: q */
        public int mo2307q(C0465z c0465z) {
            return 0;
        }

        /* renamed from: q0 */
        public boolean m2508q0() {
            return this.f2450i;
        }

        /* renamed from: q1 */
        public boolean m2509q1(RecyclerView recyclerView, View view, Rect rect, boolean z8) {
            return m2510r1(recyclerView, view, rect, z8, false);
        }

        /* renamed from: r */
        public int mo2309r(C0465z c0465z) {
            return 0;
        }

        /* renamed from: r0 */
        public boolean mo2310r0() {
            return this.f2451j;
        }

        /* renamed from: r1 */
        public boolean m2510r1(RecyclerView recyclerView, View view, Rect rect, boolean z8, boolean z9) {
            int[] iArrM2451L = m2451L(recyclerView, view, rect, z8);
            int i9 = iArrM2451L[0];
            int i10 = iArrM2451L[1];
            if ((z9 && !m2511s0(recyclerView, i9, i10)) || (i9 == 0 && i10 == 0)) {
                return false;
            }
            if (z8) {
                recyclerView.scrollBy(i9, i10);
            } else {
                recyclerView.smoothScrollBy(i9, i10);
            }
            return true;
        }

        /* renamed from: s */
        public int mo2311s(C0465z c0465z) {
            return 0;
        }

        /* renamed from: s0 */
        public final boolean m2511s0(RecyclerView recyclerView, int i9, int i10) {
            View focusedChild = recyclerView.getFocusedChild();
            if (focusedChild == null) {
                return false;
            }
            int iM2479d0 = m2479d0();
            int iM2485f0 = m2485f0();
            int iM2502n0 = m2502n0() - m2482e0();
            int iM2466W = m2466W() - m2477c0();
            Rect rect = this.f2443b.mTempRect;
            m2457P(focusedChild, rect);
            return rect.left - i9 < iM2502n0 && rect.right - i9 > iM2479d0 && rect.top - i10 < iM2466W && rect.bottom - i10 > iM2485f0;
        }

        /* renamed from: s1 */
        public void m2512s1() {
            RecyclerView recyclerView = this.f2443b;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        /* renamed from: t */
        public int mo2313t(C0465z c0465z) {
            return 0;
        }

        /* renamed from: t0 */
        public final boolean m2513t0() {
            return this.f2453l;
        }

        /* renamed from: t1 */
        public void m2514t1() {
            this.f2449h = true;
        }

        /* renamed from: u */
        public int mo2315u(C0465z c0465z) {
            return 0;
        }

        /* renamed from: u0 */
        public boolean m2515u0(C0461v c0461v, C0465z c0465z) {
            return false;
        }

        /* renamed from: u1 */
        public final void m2516u1(C0461v c0461v, int i9, View view) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.shouldIgnore()) {
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.f2443b.mAdapter.hasStableIds()) {
                m2507p1(i9);
                c0461v.m2558C(childViewHolderInt);
            } else {
                m2519x(i9);
                c0461v.m2559D(view);
                this.f2443b.mViewInfoStore.m2952k(childViewHolderInt);
            }
        }

        /* renamed from: v */
        public int mo2317v(C0465z c0465z) {
            return 0;
        }

        /* renamed from: v1 */
        public int mo2247v1(int i9, C0461v c0461v, C0465z c0465z) {
            return 0;
        }

        /* renamed from: w */
        public void m2517w(C0461v c0461v) {
            for (int iM2448J = m2448J() - 1; iM2448J >= 0; iM2448J--) {
                m2516u1(c0461v, iM2448J, m2446I(iM2448J));
            }
        }

        /* renamed from: w0 */
        public boolean m2518w0() {
            AbstractC0464y abstractC0464y = this.f2448g;
            return abstractC0464y != null && abstractC0464y.m2603h();
        }

        /* renamed from: w1 */
        public void mo2319w1(int i9) {
        }

        /* renamed from: x */
        public void m2519x(int i9) {
            m2521y(i9, m2446I(i9));
        }

        /* renamed from: x0 */
        public boolean m2520x0(View view, boolean z8, boolean z9) {
            boolean z10 = this.f2446e.m2936b(view, 24579) && this.f2447f.m2936b(view, 24579);
            return z8 ? z10 : !z10;
        }

        /* renamed from: x1 */
        public int mo2248x1(int i9, C0461v c0461v, C0465z c0465z) {
            return 0;
        }

        /* renamed from: y */
        public final void m2521y(int i9, View view) {
            this.f2442a.m2769d(i9);
        }

        /* renamed from: y0 */
        public void m2522y0(View view, int i9, int i10, int i11, int i12) {
            C0455p c0455p = (C0455p) view.getLayoutParams();
            Rect rect = c0455p.f2467b;
            view.layout(i9 + rect.left + ((ViewGroup.MarginLayoutParams) c0455p).leftMargin, i10 + rect.top + ((ViewGroup.MarginLayoutParams) c0455p).topMargin, (i11 - rect.right) - ((ViewGroup.MarginLayoutParams) c0455p).rightMargin, (i12 - rect.bottom) - ((ViewGroup.MarginLayoutParams) c0455p).bottomMargin);
        }

        /* renamed from: y1 */
        public void m2523y1(RecyclerView recyclerView) {
            m2526z1(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        /* renamed from: z */
        public void m2524z(RecyclerView recyclerView) {
            this.f2450i = true;
            m2441F0(recyclerView);
        }

        /* renamed from: z0 */
        public void m2525z0(View view, int i9, int i10) {
            C0455p c0455p = (C0455p) view.getLayoutParams();
            Rect itemDecorInsetsForChild = this.f2443b.getItemDecorInsetsForChild(view);
            int i11 = i9 + itemDecorInsetsForChild.left + itemDecorInsetsForChild.right;
            int i12 = i10 + itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom;
            int iM2426K = m2426K(m2502n0(), m2504o0(), m2479d0() + m2482e0() + ((ViewGroup.MarginLayoutParams) c0455p).leftMargin + ((ViewGroup.MarginLayoutParams) c0455p).rightMargin + i11, ((ViewGroup.MarginLayoutParams) c0455p).width, mo2298k());
            int iM2426K2 = m2426K(m2466W(), m2467X(), m2485f0() + m2477c0() + ((ViewGroup.MarginLayoutParams) c0455p).topMargin + ((ViewGroup.MarginLayoutParams) c0455p).bottomMargin + i12, ((ViewGroup.MarginLayoutParams) c0455p).height, mo2300l());
            if (m2440E1(view, iM2426K, iM2426K2, c0455p)) {
                view.measure(iM2426K, iM2426K2);
            }
        }

        /* renamed from: z1 */
        public void m2526z1(int i9, int i10) {
            this.f2458q = View.MeasureSpec.getSize(i9);
            int mode = View.MeasureSpec.getMode(i9);
            this.f2456o = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.f2458q = 0;
            }
            this.f2459r = View.MeasureSpec.getSize(i10);
            int mode2 = View.MeasureSpec.getMode(i10);
            this.f2457p = mode2;
            if (mode2 != 0 || RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                return;
            }
            this.f2459r = 0;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$q */
    public interface InterfaceC0456q {
        /* renamed from: a */
        void m2537a(View view);

        /* renamed from: b */
        void m2538b(View view);
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$r */
    public static abstract class AbstractC0457r {
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$s */
    public interface InterfaceC0458s {
        /* renamed from: a */
        void mo2539a(RecyclerView recyclerView, MotionEvent motionEvent);

        /* renamed from: b */
        boolean mo2540b(RecyclerView recyclerView, MotionEvent motionEvent);

        /* renamed from: c */
        void mo2541c(boolean z8);
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$t */
    public static abstract class AbstractC0459t {
        /* renamed from: a */
        public void m2542a(RecyclerView recyclerView, int i9) {
        }

        /* renamed from: b */
        public void mo2543b(RecyclerView recyclerView, int i9, int i10) {
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$u */
    public static class C0460u {

        /* renamed from: a */
        public SparseArray<a> f2470a = new SparseArray<>();

        /* renamed from: b */
        public int f2471b = 0;

        /* renamed from: androidx.recyclerview.widget.RecyclerView$u$a */
        public static class a {

            /* renamed from: a */
            public final ArrayList<AbstractC0442c0> f2472a = new ArrayList<>();

            /* renamed from: b */
            public int f2473b = 5;

            /* renamed from: c */
            public long f2474c = 0;

            /* renamed from: d */
            public long f2475d = 0;
        }

        /* renamed from: a */
        public void m2544a() {
            this.f2471b++;
        }

        /* renamed from: b */
        public void m2545b() {
            for (int i9 = 0; i9 < this.f2470a.size(); i9++) {
                this.f2470a.valueAt(i9).f2472a.clear();
            }
        }

        /* renamed from: c */
        public void m2546c() {
            this.f2471b--;
        }

        /* renamed from: d */
        public void m2547d(int i9, long j9) {
            a aVarM2550g = m2550g(i9);
            aVarM2550g.f2475d = m2553j(aVarM2550g.f2475d, j9);
        }

        /* renamed from: e */
        public void m2548e(int i9, long j9) {
            a aVarM2550g = m2550g(i9);
            aVarM2550g.f2474c = m2553j(aVarM2550g.f2474c, j9);
        }

        /* renamed from: f */
        public AbstractC0442c0 m2549f(int i9) {
            a aVar = this.f2470a.get(i9);
            if (aVar == null || aVar.f2472a.isEmpty()) {
                return null;
            }
            return aVar.f2472a.remove(r2.size() - 1);
        }

        /* renamed from: g */
        public final a m2550g(int i9) {
            a aVar = this.f2470a.get(i9);
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a();
            this.f2470a.put(i9, aVar2);
            return aVar2;
        }

        /* renamed from: h */
        public void m2551h(AbstractC0446g abstractC0446g, AbstractC0446g abstractC0446g2, boolean z8) {
            if (abstractC0446g != null) {
                m2546c();
            }
            if (!z8 && this.f2471b == 0) {
                m2545b();
            }
            if (abstractC0446g2 != null) {
                m2544a();
            }
        }

        /* renamed from: i */
        public void m2552i(AbstractC0442c0 abstractC0442c0) {
            int itemViewType = abstractC0442c0.getItemViewType();
            ArrayList<AbstractC0442c0> arrayList = m2550g(itemViewType).f2472a;
            if (this.f2470a.get(itemViewType).f2473b <= arrayList.size()) {
                return;
            }
            abstractC0442c0.resetInternal();
            arrayList.add(abstractC0442c0);
        }

        /* renamed from: j */
        public long m2553j(long j9, long j10) {
            return j9 == 0 ? j10 : ((j9 / 4) * 3) + (j10 / 4);
        }

        /* renamed from: k */
        public boolean m2554k(int i9, long j9, long j10) {
            long j11 = m2550g(i9).f2475d;
            return j11 == 0 || j9 + j11 < j10;
        }

        /* renamed from: l */
        public boolean m2555l(int i9, long j9, long j10) {
            long j11 = m2550g(i9).f2474c;
            return j11 == 0 || j9 + j11 < j10;
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$v */
    public final class C0461v {

        /* renamed from: a */
        public final ArrayList<AbstractC0442c0> f2476a;

        /* renamed from: b */
        public ArrayList<AbstractC0442c0> f2477b;

        /* renamed from: c */
        public final ArrayList<AbstractC0442c0> f2478c;

        /* renamed from: d */
        public final List<AbstractC0442c0> f2479d;

        /* renamed from: e */
        public int f2480e;

        /* renamed from: f */
        public int f2481f;

        /* renamed from: g */
        public C0460u f2482g;

        public C0461v() {
            ArrayList<AbstractC0442c0> arrayList = new ArrayList<>();
            this.f2476a = arrayList;
            this.f2477b = null;
            this.f2478c = new ArrayList<>();
            this.f2479d = Collections.unmodifiableList(arrayList);
            this.f2480e = 2;
            this.f2481f = 2;
        }

        /* renamed from: A */
        public void m2556A(int i9) {
            m2569a(this.f2478c.get(i9), true);
            this.f2478c.remove(i9);
        }

        /* renamed from: B */
        public void m2557B(View view) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.isTmpDetached()) {
                RecyclerView.this.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            m2558C(childViewHolderInt);
        }

        /* renamed from: C */
        public void m2558C(AbstractC0442c0 abstractC0442c0) {
            boolean z8;
            boolean z9 = true;
            if (abstractC0442c0.isScrap() || abstractC0442c0.itemView.getParent() != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scrapped or attached views may not be recycled. isScrap:");
                sb.append(abstractC0442c0.isScrap());
                sb.append(" isAttached:");
                sb.append(abstractC0442c0.itemView.getParent() != null);
                sb.append(RecyclerView.this.exceptionLabel());
                throw new IllegalArgumentException(sb.toString());
            }
            if (abstractC0442c0.isTmpDetached()) {
                throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + abstractC0442c0 + RecyclerView.this.exceptionLabel());
            }
            if (abstractC0442c0.shouldIgnore()) {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + RecyclerView.this.exceptionLabel());
            }
            boolean zDoesTransientStatePreventRecycling = abstractC0442c0.doesTransientStatePreventRecycling();
            AbstractC0446g abstractC0446g = RecyclerView.this.mAdapter;
            if ((abstractC0446g != null && zDoesTransientStatePreventRecycling && abstractC0446g.onFailedToRecycleView(abstractC0442c0)) || abstractC0442c0.isRecyclable()) {
                if (this.f2481f <= 0 || abstractC0442c0.hasAnyOfTheFlags(526)) {
                    z8 = false;
                } else {
                    int size = this.f2478c.size();
                    if (size >= this.f2481f && size > 0) {
                        m2556A(0);
                        size--;
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK && size > 0 && !RecyclerView.this.mPrefetchRegistry.m2844d(abstractC0442c0.mPosition)) {
                        int i9 = size - 1;
                        while (i9 >= 0) {
                            if (!RecyclerView.this.mPrefetchRegistry.m2844d(this.f2478c.get(i9).mPosition)) {
                                break;
                            } else {
                                i9--;
                            }
                        }
                        size = i9 + 1;
                    }
                    this.f2478c.add(size, abstractC0442c0);
                    z8 = true;
                }
                if (z8) {
                    z9 = false;
                } else {
                    m2569a(abstractC0442c0, true);
                }
                z = z8;
            } else {
                z9 = false;
            }
            RecyclerView.this.mViewInfoStore.m2958q(abstractC0442c0);
            if (z || z9 || !zDoesTransientStatePreventRecycling) {
                return;
            }
            abstractC0442c0.mOwnerRecyclerView = null;
        }

        /* renamed from: D */
        public void m2559D(View view) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.hasAnyOfTheFlags(12) && childViewHolderInt.isUpdated() && !RecyclerView.this.canReuseUpdatedViewHolder(childViewHolderInt)) {
                if (this.f2477b == null) {
                    this.f2477b = new ArrayList<>();
                }
                childViewHolderInt.setScrapContainer(this, true);
                this.f2477b.add(childViewHolderInt);
                return;
            }
            if (!childViewHolderInt.isInvalid() || childViewHolderInt.isRemoved() || RecyclerView.this.mAdapter.hasStableIds()) {
                childViewHolderInt.setScrapContainer(this, false);
                this.f2476a.add(childViewHolderInt);
            } else {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + RecyclerView.this.exceptionLabel());
            }
        }

        /* renamed from: E */
        public void m2560E(C0460u c0460u) {
            C0460u c0460u2 = this.f2482g;
            if (c0460u2 != null) {
                c0460u2.m2546c();
            }
            this.f2482g = c0460u;
            if (c0460u == null || RecyclerView.this.getAdapter() == null) {
                return;
            }
            this.f2482g.m2544a();
        }

        /* renamed from: F */
        public void m2561F(AbstractC0438a0 abstractC0438a0) {
        }

        /* renamed from: G */
        public void m2562G(int i9) {
            this.f2480e = i9;
            m2566K();
        }

        /* renamed from: H */
        public final boolean m2563H(AbstractC0442c0 abstractC0442c0, int i9, int i10, long j9) {
            abstractC0442c0.mOwnerRecyclerView = RecyclerView.this;
            int itemViewType = abstractC0442c0.getItemViewType();
            long nanoTime = RecyclerView.this.getNanoTime();
            if (j9 != Long.MAX_VALUE && !this.f2482g.m2554k(itemViewType, nanoTime, j9)) {
                return false;
            }
            RecyclerView.this.mAdapter.bindViewHolder(abstractC0442c0, i9);
            this.f2482g.m2547d(abstractC0442c0.getItemViewType(), RecyclerView.this.getNanoTime() - nanoTime);
            m2570b(abstractC0442c0);
            if (!RecyclerView.this.mState.m2623e()) {
                return true;
            }
            abstractC0442c0.mPreLayoutPosition = i10;
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005f  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0131 A[PHI: r1 r4
          0x0131: PHI (r1v12 androidx.recyclerview.widget.RecyclerView$c0) = (r1v11 androidx.recyclerview.widget.RecyclerView$c0), (r1v30 androidx.recyclerview.widget.RecyclerView$c0) binds: [B:28:0x005d, B:45:0x00ad] A[DONT_GENERATE, DONT_INLINE]
          0x0131: PHI (r4v3 boolean) = (r4v2 boolean), (r4v7 boolean) binds: [B:28:0x005d, B:45:0x00ad] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x0152  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x017e  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x01ae  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x01bc  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x01d8 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01db  */
        /* renamed from: I */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public AbstractC0442c0 m2564I(int i9, boolean z8, long j9) {
            AbstractC0442c0 abstractC0442c0M2580l;
            boolean z9;
            AbstractC0442c0 abstractC0442c0;
            boolean z10;
            boolean zM2563H;
            ViewGroup.LayoutParams layoutParams;
            C0455p c0455p;
            RecyclerView recyclerViewFindNestedRecyclerView;
            if (i9 < 0 || i9 >= RecyclerView.this.mState.m2620b()) {
                throw new IndexOutOfBoundsException("Invalid item position " + i9 + "(" + i9 + "). Item count:" + RecyclerView.this.mState.m2620b() + RecyclerView.this.exceptionLabel());
            }
            if (RecyclerView.this.mState.m2623e()) {
                abstractC0442c0M2580l = m2576h(i9);
                if (abstractC0442c0M2580l != null) {
                    z9 = true;
                }
                if (abstractC0442c0M2580l == null && (abstractC0442c0M2580l = m2581m(i9, z8)) != null) {
                    if (m2567L(abstractC0442c0M2580l)) {
                        if (!z8) {
                            abstractC0442c0M2580l.addFlags(4);
                            if (abstractC0442c0M2580l.isScrap()) {
                                RecyclerView.this.removeDetachedView(abstractC0442c0M2580l.itemView, false);
                                abstractC0442c0M2580l.unScrap();
                            } else if (abstractC0442c0M2580l.wasReturnedFromScrap()) {
                                abstractC0442c0M2580l.clearReturnedFromScrapFlag();
                            }
                            m2558C(abstractC0442c0M2580l);
                        }
                        abstractC0442c0M2580l = null;
                    } else {
                        z9 = true;
                    }
                }
                if (abstractC0442c0M2580l != null) {
                    abstractC0442c0 = abstractC0442c0M2580l;
                } else {
                    int iM2746m = RecyclerView.this.mAdapterHelper.m2746m(i9);
                    if (iM2746m < 0 || iM2746m >= RecyclerView.this.mAdapter.getItemCount()) {
                        throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i9 + "(offset:" + iM2746m + ").state:" + RecyclerView.this.mState.m2620b() + RecyclerView.this.exceptionLabel());
                    }
                    int itemViewType = RecyclerView.this.mAdapter.getItemViewType(iM2746m);
                    if (RecyclerView.this.mAdapter.hasStableIds() && (abstractC0442c0M2580l = m2580l(RecyclerView.this.mAdapter.getItemId(iM2746m), itemViewType, z8)) != null) {
                        abstractC0442c0M2580l.mPosition = iM2746m;
                        z9 = true;
                    }
                    if (abstractC0442c0M2580l == null) {
                        AbstractC0442c0 abstractC0442c0M2549f = m2577i().m2549f(itemViewType);
                        if (abstractC0442c0M2549f != null) {
                            abstractC0442c0M2549f.resetInternal();
                            if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                                m2586r(abstractC0442c0M2549f);
                            }
                        }
                        abstractC0442c0M2580l = abstractC0442c0M2549f;
                    }
                    if (abstractC0442c0M2580l == null) {
                        long nanoTime = RecyclerView.this.getNanoTime();
                        if (j9 != Long.MAX_VALUE && !this.f2482g.m2555l(itemViewType, nanoTime, j9)) {
                            return null;
                        }
                        RecyclerView recyclerView = RecyclerView.this;
                        AbstractC0442c0 abstractC0442c0CreateViewHolder = recyclerView.mAdapter.createViewHolder(recyclerView, itemViewType);
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK && (recyclerViewFindNestedRecyclerView = RecyclerView.findNestedRecyclerView(abstractC0442c0CreateViewHolder.itemView)) != null) {
                            abstractC0442c0CreateViewHolder.mNestedRecyclerView = new WeakReference<>(recyclerViewFindNestedRecyclerView);
                        }
                        this.f2482g.m2548e(itemViewType, RecyclerView.this.getNanoTime() - nanoTime);
                        abstractC0442c0 = abstractC0442c0CreateViewHolder;
                    }
                }
                z10 = z9;
                if (z10 && !RecyclerView.this.mState.m2623e() && abstractC0442c0.hasAnyOfTheFlags(UserMetadata.MAX_INTERNAL_KEY_SIZE)) {
                    abstractC0442c0.setFlags(0, UserMetadata.MAX_INTERNAL_KEY_SIZE);
                    if (RecyclerView.this.mState.f2510k) {
                        int iM2394e = AbstractC0451l.m2394e(abstractC0442c0) | 4096;
                        RecyclerView recyclerView2 = RecyclerView.this;
                        RecyclerView.this.recordAnimationInfoIfBouncedHiddenView(abstractC0442c0, recyclerView2.mItemAnimator.m2413t(recyclerView2.mState, abstractC0442c0, iM2394e, abstractC0442c0.getUnmodifiedPayloads()));
                    }
                }
                if (RecyclerView.this.mState.m2623e() || !abstractC0442c0.isBound()) {
                    if (abstractC0442c0.isBound() || abstractC0442c0.needsUpdate() || abstractC0442c0.isInvalid()) {
                        zM2563H = m2563H(abstractC0442c0, RecyclerView.this.mAdapterHelper.m2746m(i9), i9, j9);
                    }
                    layoutParams = abstractC0442c0.itemView.getLayoutParams();
                    if (layoutParams == null) {
                        c0455p = (C0455p) RecyclerView.this.generateDefaultLayoutParams();
                        abstractC0442c0.itemView.setLayoutParams(c0455p);
                    } else if (RecyclerView.this.checkLayoutParams(layoutParams)) {
                        c0455p = (C0455p) layoutParams;
                    } else {
                        c0455p = (C0455p) RecyclerView.this.generateLayoutParams(layoutParams);
                        abstractC0442c0.itemView.setLayoutParams(c0455p);
                    }
                    c0455p.f2466a = abstractC0442c0;
                    c0455p.f2469d = z10 && zM2563H;
                    return abstractC0442c0;
                }
                abstractC0442c0.mPreLayoutPosition = i9;
                zM2563H = false;
                layoutParams = abstractC0442c0.itemView.getLayoutParams();
                if (layoutParams == null) {
                }
                c0455p.f2466a = abstractC0442c0;
                c0455p.f2469d = z10 && zM2563H;
                return abstractC0442c0;
            }
            abstractC0442c0M2580l = null;
            z9 = false;
            if (abstractC0442c0M2580l == null) {
                if (m2567L(abstractC0442c0M2580l)) {
                }
            }
            if (abstractC0442c0M2580l != null) {
            }
            z10 = z9;
            if (z10) {
                abstractC0442c0.setFlags(0, UserMetadata.MAX_INTERNAL_KEY_SIZE);
                if (RecyclerView.this.mState.f2510k) {
                }
            }
            if (RecyclerView.this.mState.m2623e()) {
                if (abstractC0442c0.isBound()) {
                }
                zM2563H = m2563H(abstractC0442c0, RecyclerView.this.mAdapterHelper.m2746m(i9), i9, j9);
            }
            layoutParams = abstractC0442c0.itemView.getLayoutParams();
            if (layoutParams == null) {
            }
            c0455p.f2466a = abstractC0442c0;
            c0455p.f2469d = z10 && zM2563H;
            return abstractC0442c0;
        }

        /* renamed from: J */
        public void m2565J(AbstractC0442c0 abstractC0442c0) {
            if (abstractC0442c0.mInChangeScrap) {
                this.f2477b.remove(abstractC0442c0);
            } else {
                this.f2476a.remove(abstractC0442c0);
            }
            abstractC0442c0.mScrapContainer = null;
            abstractC0442c0.mInChangeScrap = false;
            abstractC0442c0.clearReturnedFromScrapFlag();
        }

        /* renamed from: K */
        public void m2566K() {
            AbstractC0454o abstractC0454o = RecyclerView.this.mLayout;
            this.f2481f = this.f2480e + (abstractC0454o != null ? abstractC0454o.f2454m : 0);
            for (int size = this.f2478c.size() - 1; size >= 0 && this.f2478c.size() > this.f2481f; size--) {
                m2556A(size);
            }
        }

        /* renamed from: L */
        public boolean m2567L(AbstractC0442c0 abstractC0442c0) {
            if (abstractC0442c0.isRemoved()) {
                return RecyclerView.this.mState.m2623e();
            }
            int i9 = abstractC0442c0.mPosition;
            if (i9 >= 0 && i9 < RecyclerView.this.mAdapter.getItemCount()) {
                if (RecyclerView.this.mState.m2623e() || RecyclerView.this.mAdapter.getItemViewType(abstractC0442c0.mPosition) == abstractC0442c0.getItemViewType()) {
                    return !RecyclerView.this.mAdapter.hasStableIds() || abstractC0442c0.getItemId() == RecyclerView.this.mAdapter.getItemId(abstractC0442c0.mPosition);
                }
                return false;
            }
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + abstractC0442c0 + RecyclerView.this.exceptionLabel());
        }

        /* renamed from: M */
        public void m2568M(int i9, int i10) {
            int i11;
            int i12 = i10 + i9;
            for (int size = this.f2478c.size() - 1; size >= 0; size--) {
                AbstractC0442c0 abstractC0442c0 = this.f2478c.get(size);
                if (abstractC0442c0 != null && (i11 = abstractC0442c0.mPosition) >= i9 && i11 < i12) {
                    abstractC0442c0.addFlags(2);
                    m2556A(size);
                }
            }
        }

        /* renamed from: a */
        public void m2569a(AbstractC0442c0 abstractC0442c0, boolean z8) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(abstractC0442c0);
            if (abstractC0442c0.hasAnyOfTheFlags(16384)) {
                abstractC0442c0.setFlags(0, 16384);
                C4647u.m18530Z(abstractC0442c0.itemView, null);
            }
            if (z8) {
                m2575g(abstractC0442c0);
            }
            abstractC0442c0.mOwnerRecyclerView = null;
            m2577i().m2552i(abstractC0442c0);
        }

        /* renamed from: b */
        public final void m2570b(AbstractC0442c0 abstractC0442c0) {
            if (RecyclerView.this.isAccessibilityEnabled()) {
                View view = abstractC0442c0.itemView;
                if (C4647u.m18563q(view) == 0) {
                    C4647u.m18548i0(view, 1);
                }
                if (C4647u.m18507C(view)) {
                    return;
                }
                abstractC0442c0.addFlags(16384);
                C4647u.m18530Z(view, RecyclerView.this.mAccessibilityDelegate.m2884a());
            }
        }

        /* renamed from: c */
        public void m2571c() {
            this.f2476a.clear();
            m2594z();
        }

        /* renamed from: d */
        public void m2572d() {
            int size = this.f2478c.size();
            for (int i9 = 0; i9 < size; i9++) {
                this.f2478c.get(i9).clearOldPosition();
            }
            int size2 = this.f2476a.size();
            for (int i10 = 0; i10 < size2; i10++) {
                this.f2476a.get(i10).clearOldPosition();
            }
            ArrayList<AbstractC0442c0> arrayList = this.f2477b;
            if (arrayList != null) {
                int size3 = arrayList.size();
                for (int i11 = 0; i11 < size3; i11++) {
                    this.f2477b.get(i11).clearOldPosition();
                }
            }
        }

        /* renamed from: e */
        public void m2573e() {
            this.f2476a.clear();
            ArrayList<AbstractC0442c0> arrayList = this.f2477b;
            if (arrayList != null) {
                arrayList.clear();
            }
        }

        /* renamed from: f */
        public int m2574f(int i9) {
            if (i9 >= 0 && i9 < RecyclerView.this.mState.m2620b()) {
                return !RecyclerView.this.mState.m2623e() ? i9 : RecyclerView.this.mAdapterHelper.m2746m(i9);
            }
            throw new IndexOutOfBoundsException("invalid position " + i9 + ". State item count is " + RecyclerView.this.mState.m2620b() + RecyclerView.this.exceptionLabel());
        }

        /* renamed from: g */
        public void m2575g(AbstractC0442c0 abstractC0442c0) {
            RecyclerView.this.getClass();
            AbstractC0446g abstractC0446g = RecyclerView.this.mAdapter;
            if (abstractC0446g != null) {
                abstractC0446g.onViewRecycled(abstractC0442c0);
            }
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mState != null) {
                recyclerView.mViewInfoStore.m2958q(abstractC0442c0);
            }
        }

        /* renamed from: h */
        public AbstractC0442c0 m2576h(int i9) {
            int size;
            int iM2746m;
            ArrayList<AbstractC0442c0> arrayList = this.f2477b;
            if (arrayList != null && (size = arrayList.size()) != 0) {
                for (int i10 = 0; i10 < size; i10++) {
                    AbstractC0442c0 abstractC0442c0 = this.f2477b.get(i10);
                    if (!abstractC0442c0.wasReturnedFromScrap() && abstractC0442c0.getLayoutPosition() == i9) {
                        abstractC0442c0.addFlags(32);
                        return abstractC0442c0;
                    }
                }
                if (RecyclerView.this.mAdapter.hasStableIds() && (iM2746m = RecyclerView.this.mAdapterHelper.m2746m(i9)) > 0 && iM2746m < RecyclerView.this.mAdapter.getItemCount()) {
                    long itemId = RecyclerView.this.mAdapter.getItemId(iM2746m);
                    for (int i11 = 0; i11 < size; i11++) {
                        AbstractC0442c0 abstractC0442c02 = this.f2477b.get(i11);
                        if (!abstractC0442c02.wasReturnedFromScrap() && abstractC0442c02.getItemId() == itemId) {
                            abstractC0442c02.addFlags(32);
                            return abstractC0442c02;
                        }
                    }
                }
            }
            return null;
        }

        /* renamed from: i */
        public C0460u m2577i() {
            if (this.f2482g == null) {
                this.f2482g = new C0460u();
            }
            return this.f2482g;
        }

        /* renamed from: j */
        public int m2578j() {
            return this.f2476a.size();
        }

        /* renamed from: k */
        public List<AbstractC0442c0> m2579k() {
            return this.f2479d;
        }

        /* renamed from: l */
        public AbstractC0442c0 m2580l(long j9, int i9, boolean z8) {
            for (int size = this.f2476a.size() - 1; size >= 0; size--) {
                AbstractC0442c0 abstractC0442c0 = this.f2476a.get(size);
                if (abstractC0442c0.getItemId() == j9 && !abstractC0442c0.wasReturnedFromScrap()) {
                    if (i9 == abstractC0442c0.getItemViewType()) {
                        abstractC0442c0.addFlags(32);
                        if (abstractC0442c0.isRemoved() && !RecyclerView.this.mState.m2623e()) {
                            abstractC0442c0.setFlags(2, 14);
                        }
                        return abstractC0442c0;
                    }
                    if (!z8) {
                        this.f2476a.remove(size);
                        RecyclerView.this.removeDetachedView(abstractC0442c0.itemView, false);
                        m2593y(abstractC0442c0.itemView);
                    }
                }
            }
            int size2 = this.f2478c.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return null;
                }
                AbstractC0442c0 abstractC0442c02 = this.f2478c.get(size2);
                if (abstractC0442c02.getItemId() == j9) {
                    if (i9 == abstractC0442c02.getItemViewType()) {
                        if (!z8) {
                            this.f2478c.remove(size2);
                        }
                        return abstractC0442c02;
                    }
                    if (!z8) {
                        m2556A(size2);
                        return null;
                    }
                }
            }
        }

        /* renamed from: m */
        public AbstractC0442c0 m2581m(int i9, boolean z8) {
            View viewM2770e;
            int size = this.f2476a.size();
            for (int i10 = 0; i10 < size; i10++) {
                AbstractC0442c0 abstractC0442c0 = this.f2476a.get(i10);
                if (!abstractC0442c0.wasReturnedFromScrap() && abstractC0442c0.getLayoutPosition() == i9 && !abstractC0442c0.isInvalid() && (RecyclerView.this.mState.f2507h || !abstractC0442c0.isRemoved())) {
                    abstractC0442c0.addFlags(32);
                    return abstractC0442c0;
                }
            }
            if (z8 || (viewM2770e = RecyclerView.this.mChildHelper.m2770e(i9)) == null) {
                int size2 = this.f2478c.size();
                for (int i11 = 0; i11 < size2; i11++) {
                    AbstractC0442c0 abstractC0442c02 = this.f2478c.get(i11);
                    if (!abstractC0442c02.isInvalid() && abstractC0442c02.getLayoutPosition() == i9) {
                        if (!z8) {
                            this.f2478c.remove(i11);
                        }
                        return abstractC0442c02;
                    }
                }
                return null;
            }
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(viewM2770e);
            RecyclerView.this.mChildHelper.m2784s(viewM2770e);
            int iM2778m = RecyclerView.this.mChildHelper.m2778m(viewM2770e);
            if (iM2778m != -1) {
                RecyclerView.this.mChildHelper.m2769d(iM2778m);
                m2559D(viewM2770e);
                childViewHolderInt.addFlags(8224);
                return childViewHolderInt;
            }
            throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + childViewHolderInt + RecyclerView.this.exceptionLabel());
        }

        /* renamed from: n */
        public View m2582n(int i9) {
            return this.f2476a.get(i9).itemView;
        }

        /* renamed from: o */
        public View m2583o(int i9) {
            return m2584p(i9, false);
        }

        /* renamed from: p */
        public View m2584p(int i9, boolean z8) {
            return m2564I(i9, z8, Long.MAX_VALUE).itemView;
        }

        /* renamed from: q */
        public final void m2585q(ViewGroup viewGroup, boolean z8) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (childAt instanceof ViewGroup) {
                    m2585q((ViewGroup) childAt, true);
                }
            }
            if (z8) {
                if (viewGroup.getVisibility() == 4) {
                    viewGroup.setVisibility(0);
                    viewGroup.setVisibility(4);
                } else {
                    int visibility = viewGroup.getVisibility();
                    viewGroup.setVisibility(4);
                    viewGroup.setVisibility(visibility);
                }
            }
        }

        /* renamed from: r */
        public final void m2586r(AbstractC0442c0 abstractC0442c0) {
            View view = abstractC0442c0.itemView;
            if (view instanceof ViewGroup) {
                m2585q((ViewGroup) view, false);
            }
        }

        /* renamed from: s */
        public void m2587s() {
            int size = this.f2478c.size();
            for (int i9 = 0; i9 < size; i9++) {
                C0455p c0455p = (C0455p) this.f2478c.get(i9).itemView.getLayoutParams();
                if (c0455p != null) {
                    c0455p.f2468c = true;
                }
            }
        }

        /* renamed from: t */
        public void m2588t() {
            int size = this.f2478c.size();
            for (int i9 = 0; i9 < size; i9++) {
                AbstractC0442c0 abstractC0442c0 = this.f2478c.get(i9);
                if (abstractC0442c0 != null) {
                    abstractC0442c0.addFlags(6);
                    abstractC0442c0.addChangePayload(null);
                }
            }
            AbstractC0446g abstractC0446g = RecyclerView.this.mAdapter;
            if (abstractC0446g == null || !abstractC0446g.hasStableIds()) {
                m2594z();
            }
        }

        /* renamed from: u */
        public void m2589u(int i9, int i10) {
            int size = this.f2478c.size();
            for (int i11 = 0; i11 < size; i11++) {
                AbstractC0442c0 abstractC0442c0 = this.f2478c.get(i11);
                if (abstractC0442c0 != null && abstractC0442c0.mPosition >= i9) {
                    abstractC0442c0.offsetPosition(i10, true);
                }
            }
        }

        /* renamed from: v */
        public void m2590v(int i9, int i10) {
            int i11;
            int i12;
            int i13;
            int i14;
            if (i9 < i10) {
                i11 = -1;
                i13 = i9;
                i12 = i10;
            } else {
                i11 = 1;
                i12 = i9;
                i13 = i10;
            }
            int size = this.f2478c.size();
            for (int i15 = 0; i15 < size; i15++) {
                AbstractC0442c0 abstractC0442c0 = this.f2478c.get(i15);
                if (abstractC0442c0 != null && (i14 = abstractC0442c0.mPosition) >= i13 && i14 <= i12) {
                    if (i14 == i9) {
                        abstractC0442c0.offsetPosition(i10 - i9, false);
                    } else {
                        abstractC0442c0.offsetPosition(i11, false);
                    }
                }
            }
        }

        /* renamed from: w */
        public void m2591w(int i9, int i10, boolean z8) {
            int i11 = i9 + i10;
            for (int size = this.f2478c.size() - 1; size >= 0; size--) {
                AbstractC0442c0 abstractC0442c0 = this.f2478c.get(size);
                if (abstractC0442c0 != null) {
                    int i12 = abstractC0442c0.mPosition;
                    if (i12 >= i11) {
                        abstractC0442c0.offsetPosition(-i10, z8);
                    } else if (i12 >= i9) {
                        abstractC0442c0.addFlags(8);
                        m2556A(size);
                    }
                }
            }
        }

        /* renamed from: x */
        public void m2592x(AbstractC0446g abstractC0446g, AbstractC0446g abstractC0446g2, boolean z8) {
            m2571c();
            m2577i().m2551h(abstractC0446g, abstractC0446g2, z8);
        }

        /* renamed from: y */
        public void m2593y(View view) {
            AbstractC0442c0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            childViewHolderInt.mScrapContainer = null;
            childViewHolderInt.mInChangeScrap = false;
            childViewHolderInt.clearReturnedFromScrapFlag();
            m2558C(childViewHolderInt);
        }

        /* renamed from: z */
        public void m2594z() {
            for (int size = this.f2478c.size() - 1; size >= 0; size--) {
                m2556A(size);
            }
            this.f2478c.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                RecyclerView.this.mPrefetchRegistry.m2842b();
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$w */
    public interface InterfaceC0462w {
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$x */
    public class C0463x extends AbstractC0448i {
        public C0463x() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0448i
        /* renamed from: a */
        public void mo2387a() {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mState.f2506g = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (RecyclerView.this.mAdapterHelper.m2749p()) {
                return;
            }
            RecyclerView.this.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0448i
        /* renamed from: c */
        public void mo2389c(int i9, int i10, Object obj) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.m2751r(i9, i10, obj)) {
                m2595g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0448i
        /* renamed from: d */
        public void mo2390d(int i9, int i10) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.m2752s(i9, i10)) {
                m2595g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0448i
        /* renamed from: e */
        public void mo2391e(int i9, int i10, int i11) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.m2753t(i9, i10, i11)) {
                m2595g();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0448i
        /* renamed from: f */
        public void mo2392f(int i9, int i10) {
            RecyclerView.this.assertNotInLayoutOrScroll(null);
            if (RecyclerView.this.mAdapterHelper.m2754u(i9, i10)) {
                m2595g();
            }
        }

        /* renamed from: g */
        public void m2595g() {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
                RecyclerView recyclerView = RecyclerView.this;
                if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                    C4647u.m18525U(recyclerView, recyclerView.mUpdateChildViewsRunnable);
                    return;
                }
            }
            RecyclerView recyclerView2 = RecyclerView.this;
            recyclerView2.mAdapterUpdateDuringMeasure = true;
            recyclerView2.requestLayout();
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$y */
    public static abstract class AbstractC0464y {

        /* renamed from: b */
        public RecyclerView f2486b;

        /* renamed from: c */
        public AbstractC0454o f2487c;

        /* renamed from: d */
        public boolean f2488d;

        /* renamed from: e */
        public boolean f2489e;

        /* renamed from: f */
        public View f2490f;

        /* renamed from: h */
        public boolean f2492h;

        /* renamed from: a */
        public int f2485a = -1;

        /* renamed from: g */
        public final a f2491g = new a(0, 0);

        /* renamed from: androidx.recyclerview.widget.RecyclerView$y$a */
        public static class a {

            /* renamed from: a */
            public int f2493a;

            /* renamed from: b */
            public int f2494b;

            /* renamed from: c */
            public int f2495c;

            /* renamed from: d */
            public int f2496d;

            /* renamed from: e */
            public Interpolator f2497e;

            /* renamed from: f */
            public boolean f2498f;

            /* renamed from: g */
            public int f2499g;

            public a(int i9, int i10) {
                this(i9, i10, Integer.MIN_VALUE, null);
            }

            /* renamed from: a */
            public boolean m2614a() {
                return this.f2496d >= 0;
            }

            /* renamed from: b */
            public void m2615b(int i9) {
                this.f2496d = i9;
            }

            /* renamed from: c */
            public void m2616c(RecyclerView recyclerView) {
                int i9 = this.f2496d;
                if (i9 >= 0) {
                    this.f2496d = -1;
                    recyclerView.jumpToPositionForSmoothScroller(i9);
                    this.f2498f = false;
                    return;
                }
                if (!this.f2498f) {
                    this.f2499g = 0;
                    return;
                }
                m2618e();
                Interpolator interpolator = this.f2497e;
                if (interpolator == null) {
                    int i10 = this.f2495c;
                    if (i10 == Integer.MIN_VALUE) {
                        recyclerView.mViewFlinger.m2350g(this.f2493a, this.f2494b);
                    } else {
                        recyclerView.mViewFlinger.m2351h(this.f2493a, this.f2494b, i10);
                    }
                } else {
                    recyclerView.mViewFlinger.m2353j(this.f2493a, this.f2494b, this.f2495c, interpolator);
                }
                int i11 = this.f2499g + 1;
                this.f2499g = i11;
                if (i11 > 10) {
                    Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                this.f2498f = false;
            }

            /* renamed from: d */
            public void m2617d(int i9, int i10, int i11, Interpolator interpolator) {
                this.f2493a = i9;
                this.f2494b = i10;
                this.f2495c = i11;
                this.f2497e = interpolator;
                this.f2498f = true;
            }

            /* renamed from: e */
            public final void m2618e() {
                if (this.f2497e != null && this.f2495c < 1) {
                    throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
                }
                if (this.f2495c < 1) {
                    throw new IllegalStateException("Scroll duration must be a positive number");
                }
            }

            public a(int i9, int i10, int i11, Interpolator interpolator) {
                this.f2496d = -1;
                this.f2498f = false;
                this.f2499g = 0;
                this.f2493a = i9;
                this.f2494b = i10;
                this.f2495c = i11;
                this.f2497e = interpolator;
            }
        }

        /* renamed from: androidx.recyclerview.widget.RecyclerView$y$b */
        public interface b {
            /* renamed from: a */
            PointF mo2285a(int i9);
        }

        /* renamed from: a */
        public PointF m2596a(int i9) {
            Object objM2600e = m2600e();
            if (objM2600e instanceof b) {
                return ((b) objM2600e).mo2285a(i9);
            }
            Log.w(RecyclerView.TAG, "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + b.class.getCanonicalName());
            return null;
        }

        /* renamed from: b */
        public View m2597b(int i9) {
            return this.f2486b.mLayout.mo2258C(i9);
        }

        /* renamed from: c */
        public int m2598c() {
            return this.f2486b.mLayout.m2448J();
        }

        /* renamed from: d */
        public int m2599d(View view) {
            return this.f2486b.getChildLayoutPosition(view);
        }

        /* renamed from: e */
        public AbstractC0454o m2600e() {
            return this.f2487c;
        }

        /* renamed from: f */
        public int m2601f() {
            return this.f2485a;
        }

        /* renamed from: g */
        public boolean m2602g() {
            return this.f2488d;
        }

        /* renamed from: h */
        public boolean m2603h() {
            return this.f2489e;
        }

        /* renamed from: i */
        public void m2604i(PointF pointF) {
            float f9 = pointF.x;
            float f10 = pointF.y;
            float fSqrt = (float) Math.sqrt((f9 * f9) + (f10 * f10));
            pointF.x /= fSqrt;
            pointF.y /= fSqrt;
        }

        /* renamed from: j */
        public void m2605j(int i9, int i10) {
            PointF pointFM2596a;
            RecyclerView recyclerView = this.f2486b;
            if (!this.f2489e || this.f2485a == -1 || recyclerView == null) {
                m2613r();
            }
            if (this.f2488d && this.f2490f == null && this.f2487c != null && (pointFM2596a = m2596a(this.f2485a)) != null) {
                float f9 = pointFM2596a.x;
                if (f9 != BitmapDescriptorFactory.HUE_RED || pointFM2596a.y != BitmapDescriptorFactory.HUE_RED) {
                    recyclerView.scrollStep((int) Math.signum(f9), (int) Math.signum(pointFM2596a.y), null);
                }
            }
            this.f2488d = false;
            View view = this.f2490f;
            if (view != null) {
                if (m2599d(view) == this.f2485a) {
                    mo2610o(this.f2490f, recyclerView.mState, this.f2491g);
                    this.f2491g.m2616c(recyclerView);
                    m2613r();
                } else {
                    Log.e(RecyclerView.TAG, "Passed over target position while smooth scrolling.");
                    this.f2490f = null;
                }
            }
            if (this.f2489e) {
                mo2607l(i9, i10, recyclerView.mState, this.f2491g);
                boolean zM2614a = this.f2491g.m2614a();
                this.f2491g.m2616c(recyclerView);
                if (zM2614a) {
                    if (!this.f2489e) {
                        m2613r();
                    } else {
                        this.f2488d = true;
                        recyclerView.mViewFlinger.m2349f();
                    }
                }
            }
        }

        /* renamed from: k */
        public void m2606k(View view) {
            if (m2599d(view) == m2601f()) {
                this.f2490f = view;
            }
        }

        /* renamed from: l */
        public abstract void mo2607l(int i9, int i10, C0465z c0465z, a aVar);

        /* renamed from: m */
        public abstract void mo2608m();

        /* renamed from: n */
        public abstract void mo2609n();

        /* renamed from: o */
        public abstract void mo2610o(View view, C0465z c0465z, a aVar);

        /* renamed from: p */
        public void m2611p(int i9) {
            this.f2485a = i9;
        }

        /* renamed from: q */
        public void m2612q(RecyclerView recyclerView, AbstractC0454o abstractC0454o) {
            if (this.f2492h) {
                Log.w(RecyclerView.TAG, "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + StringUtils.SPACE + "is intended to only be used once. You should create a new instance for each use.");
            }
            this.f2486b = recyclerView;
            this.f2487c = abstractC0454o;
            int i9 = this.f2485a;
            if (i9 == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.mState.f2500a = i9;
            this.f2489e = true;
            this.f2488d = true;
            this.f2490f = m2597b(m2601f());
            mo2608m();
            this.f2486b.mViewFlinger.m2349f();
            this.f2492h = true;
        }

        /* renamed from: r */
        public final void m2613r() {
            if (this.f2489e) {
                this.f2489e = false;
                mo2609n();
                this.f2486b.mState.f2500a = -1;
                this.f2490f = null;
                this.f2485a = -1;
                this.f2488d = false;
                this.f2487c.m2483e1(this);
                this.f2487c = null;
                this.f2486b = null;
            }
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$z */
    public static class C0465z {

        /* renamed from: b */
        public SparseArray<Object> f2501b;

        /* renamed from: m */
        public int f2512m;

        /* renamed from: n */
        public long f2513n;

        /* renamed from: o */
        public int f2514o;

        /* renamed from: p */
        public int f2515p;

        /* renamed from: q */
        public int f2516q;

        /* renamed from: a */
        public int f2500a = -1;

        /* renamed from: c */
        public int f2502c = 0;

        /* renamed from: d */
        public int f2503d = 0;

        /* renamed from: e */
        public int f2504e = 1;

        /* renamed from: f */
        public int f2505f = 0;

        /* renamed from: g */
        public boolean f2506g = false;

        /* renamed from: h */
        public boolean f2507h = false;

        /* renamed from: i */
        public boolean f2508i = false;

        /* renamed from: j */
        public boolean f2509j = false;

        /* renamed from: k */
        public boolean f2510k = false;

        /* renamed from: l */
        public boolean f2511l = false;

        /* renamed from: a */
        public void m2619a(int i9) {
            if ((this.f2504e & i9) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i9) + " but it is " + Integer.toBinaryString(this.f2504e));
        }

        /* renamed from: b */
        public int m2620b() {
            return this.f2507h ? this.f2502c - this.f2503d : this.f2505f;
        }

        /* renamed from: c */
        public int m2621c() {
            return this.f2500a;
        }

        /* renamed from: d */
        public boolean m2622d() {
            return this.f2500a != -1;
        }

        /* renamed from: e */
        public boolean m2623e() {
            return this.f2507h;
        }

        /* renamed from: f */
        public void m2624f(AbstractC0446g abstractC0446g) {
            this.f2504e = 1;
            this.f2505f = abstractC0446g.getItemCount();
            this.f2507h = false;
            this.f2508i = false;
            this.f2509j = false;
        }

        /* renamed from: g */
        public boolean m2625g() {
            return this.f2511l;
        }

        public String toString() {
            return "State{mTargetPosition=" + this.f2500a + ", mData=" + this.f2501b + ", mItemCount=" + this.f2505f + ", mIsMeasuring=" + this.f2509j + ", mPreviousLayoutItemCount=" + this.f2502c + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f2503d + ", mStructureChanged=" + this.f2506g + ", mInPreLayout=" + this.f2507h + ", mRunSimpleAnimations=" + this.f2510k + ", mRunPredictiveAnimations=" + this.f2511l + '}';
        }
    }

    static {
        Class<?> cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new InterpolatorC0441c();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    private void addAnimatingView(AbstractC0442c0 abstractC0442c0) {
        View view = abstractC0442c0.itemView;
        boolean z8 = view.getParent() == this;
        this.mRecycler.m2565J(getChildViewHolder(view));
        if (abstractC0442c0.isTmpDetached()) {
            this.mChildHelper.m2768c(view, -1, view.getLayoutParams(), true);
        } else if (z8) {
            this.mChildHelper.m2776k(view);
        } else {
            this.mChildHelper.m2767b(view, true);
        }
    }

    private void animateChange(AbstractC0442c0 abstractC0442c0, AbstractC0442c0 abstractC0442c02, AbstractC0451l.c cVar, AbstractC0451l.c cVar2, boolean z8, boolean z9) {
        abstractC0442c0.setIsRecyclable(false);
        if (z8) {
            addAnimatingView(abstractC0442c0);
        }
        if (abstractC0442c0 != abstractC0442c02) {
            if (z9) {
                addAnimatingView(abstractC0442c02);
            }
            abstractC0442c0.mShadowedHolder = abstractC0442c02;
            addAnimatingView(abstractC0442c0);
            this.mRecycler.m2565J(abstractC0442c0);
            abstractC0442c02.setIsRecyclable(false);
            abstractC0442c02.mShadowingHolder = abstractC0442c0;
        }
        if (this.mItemAnimator.mo2396b(abstractC0442c0, abstractC0442c02, cVar, cVar2)) {
            postAnimationRunner();
        }
    }

    private void cancelTouch() {
        resetTouch();
        setScrollState(0);
    }

    public static void clearNestedRecyclerViewIfNotNested(AbstractC0442c0 abstractC0442c0) {
        WeakReference<RecyclerView> weakReference = abstractC0442c0.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == abstractC0442c0.itemView) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            abstractC0442c0.mNestedRecyclerView = null;
        }
    }

    private void createLayoutManager(Context context, String str, AttributeSet attributeSet, int i9, int i10) throws NoSuchMethodException, SecurityException {
        Constructor constructor;
        Object[] objArr;
        if (str != null) {
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return;
            }
            String fullClassName = getFullClassName(context, strTrim);
            try {
                Class<? extends U> clsAsSubclass = (isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).loadClass(fullClassName).asSubclass(AbstractC0454o.class);
                try {
                    constructor = clsAsSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                    objArr = new Object[]{context, attributeSet, Integer.valueOf(i9), Integer.valueOf(i10)};
                } catch (NoSuchMethodException e9) {
                    try {
                        constructor = clsAsSubclass.getConstructor(new Class[0]);
                        objArr = null;
                    } catch (NoSuchMethodException e10) {
                        e10.initCause(e9);
                        throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + fullClassName, e10);
                    }
                }
                constructor.setAccessible(true);
                setLayoutManager((AbstractC0454o) constructor.newInstance(objArr));
            } catch (ClassCastException e11) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + fullClassName, e11);
            } catch (ClassNotFoundException e12) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + fullClassName, e12);
            } catch (IllegalAccessException e13) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + fullClassName, e13);
            } catch (InstantiationException e14) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e14);
            } catch (InvocationTargetException e15) {
                throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + fullClassName, e15);
            }
        }
    }

    private boolean didChildRangeChange(int i9, int i10) {
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        int[] iArr = this.mMinMaxLayoutPositions;
        return (iArr[0] == i9 && iArr[1] == i10) ? false : true;
    }

    private void dispatchContentChangedIfNecessary() {
        int i9 = this.mEatenAccessibilityChangeFlags;
        this.mEatenAccessibilityChangeFlags = 0;
        if (i9 == 0 || !isAccessibilityEnabled()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(2048);
        C4693b.m18746b(accessibilityEventObtain, i9);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    private void dispatchLayoutStep1() {
        this.mState.m2619a(1);
        fillRemainingScrollValues(this.mState);
        this.mState.f2509j = false;
        startInterceptRequestLayout();
        this.mViewInfoStore.m2947f();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        C0465z c0465z = this.mState;
        c0465z.f2508i = c0465z.f2510k && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        c0465z.f2507h = c0465z.f2511l;
        c0465z.f2505f = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.f2510k) {
            int iM2772g = this.mChildHelper.m2772g();
            for (int i9 = 0; i9 < iM2772g; i9++) {
                AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2771f(i9));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    this.mViewInfoStore.m2946e(childViewHolderInt, this.mItemAnimator.m2413t(this.mState, childViewHolderInt, AbstractC0451l.m2394e(childViewHolderInt), childViewHolderInt.getUnmodifiedPayloads()));
                    if (this.mState.f2508i && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.m2944c(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.f2511l) {
            saveOldPositions();
            C0465z c0465z2 = this.mState;
            boolean z8 = c0465z2.f2506g;
            c0465z2.f2506g = false;
            this.mLayout.mo2235W0(this.mRecycler, c0465z2);
            this.mState.f2506g = z8;
            for (int i10 = 0; i10 < this.mChildHelper.m2772g(); i10++) {
                AbstractC0442c0 childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.m2771f(i10));
                if (!childViewHolderInt2.shouldIgnore() && !this.mViewInfoStore.m2950i(childViewHolderInt2)) {
                    int iM2394e = AbstractC0451l.m2394e(childViewHolderInt2);
                    boolean zHasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(UserMetadata.MAX_INTERNAL_KEY_SIZE);
                    if (!zHasAnyOfTheFlags) {
                        iM2394e |= 4096;
                    }
                    AbstractC0451l.c cVarM2413t = this.mItemAnimator.m2413t(this.mState, childViewHolderInt2, iM2394e, childViewHolderInt2.getUnmodifiedPayloads());
                    if (zHasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, cVarM2413t);
                    } else {
                        this.mViewInfoStore.m2942a(childViewHolderInt2, cVarM2413t);
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.f2504e = 2;
    }

    private void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.m2619a(6);
        this.mAdapterHelper.m2743j();
        this.mState.f2505f = this.mAdapter.getItemCount();
        C0465z c0465z = this.mState;
        c0465z.f2503d = 0;
        c0465z.f2507h = false;
        this.mLayout.mo2235W0(this.mRecycler, c0465z);
        C0465z c0465z2 = this.mState;
        c0465z2.f2506g = false;
        this.mPendingSavedState = null;
        c0465z2.f2510k = c0465z2.f2510k && this.mItemAnimator != null;
        c0465z2.f2504e = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    private void dispatchLayoutStep3() {
        this.mState.m2619a(4);
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        C0465z c0465z = this.mState;
        c0465z.f2504e = 1;
        if (c0465z.f2510k) {
            for (int iM2772g = this.mChildHelper.m2772g() - 1; iM2772g >= 0; iM2772g--) {
                AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2771f(iM2772g));
                if (!childViewHolderInt.shouldIgnore()) {
                    long changedHolderKey = getChangedHolderKey(childViewHolderInt);
                    AbstractC0451l.c cVarM2412s = this.mItemAnimator.m2412s(this.mState, childViewHolderInt);
                    AbstractC0442c0 abstractC0442c0M2948g = this.mViewInfoStore.m2948g(changedHolderKey);
                    if (abstractC0442c0M2948g == null || abstractC0442c0M2948g.shouldIgnore()) {
                        this.mViewInfoStore.m2945d(childViewHolderInt, cVarM2412s);
                    } else {
                        boolean zM2949h = this.mViewInfoStore.m2949h(abstractC0442c0M2948g);
                        boolean zM2949h2 = this.mViewInfoStore.m2949h(childViewHolderInt);
                        if (zM2949h && abstractC0442c0M2948g == childViewHolderInt) {
                            this.mViewInfoStore.m2945d(childViewHolderInt, cVarM2412s);
                        } else {
                            AbstractC0451l.c cVarM2955n = this.mViewInfoStore.m2955n(abstractC0442c0M2948g);
                            this.mViewInfoStore.m2945d(childViewHolderInt, cVarM2412s);
                            AbstractC0451l.c cVarM2954m = this.mViewInfoStore.m2954m(childViewHolderInt);
                            if (cVarM2955n == null) {
                                handleMissingPreInfoForChangeError(changedHolderKey, childViewHolderInt, abstractC0442c0M2948g);
                            } else {
                                animateChange(abstractC0442c0M2948g, childViewHolderInt, cVarM2955n, cVarM2954m, zM2949h, zM2949h2);
                            }
                        }
                    }
                }
            }
            this.mViewInfoStore.m2956o(this.mViewInfoProcessCallback);
        }
        this.mLayout.m2497k1(this.mRecycler);
        C0465z c0465z2 = this.mState;
        c0465z2.f2502c = c0465z2.f2505f;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        c0465z2.f2510k = false;
        c0465z2.f2511l = false;
        this.mLayout.f2449h = false;
        ArrayList<AbstractC0442c0> arrayList = this.mRecycler.f2477b;
        if (arrayList != null) {
            arrayList.clear();
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o.f2455n) {
            abstractC0454o.f2454m = 0;
            abstractC0454o.f2455n = false;
            this.mRecycler.m2566K();
        }
        this.mLayout.mo2237X0(this.mState);
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mViewInfoStore.m2947f();
        int[] iArr = this.mMinMaxLayoutPositions;
        if (didChildRangeChange(iArr[0], iArr[1])) {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchOnItemTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        InterfaceC0458s interfaceC0458s = this.mActiveOnItemTouchListener;
        if (interfaceC0458s != null) {
            if (action != 0) {
                interfaceC0458s.mo2539a(this, motionEvent);
                if (action == 3 || action == 1) {
                    this.mActiveOnItemTouchListener = null;
                }
                return true;
            }
            this.mActiveOnItemTouchListener = null;
        }
        if (action != 0) {
            int size = this.mOnItemTouchListeners.size();
            for (int i9 = 0; i9 < size; i9++) {
                InterfaceC0458s interfaceC0458s2 = this.mOnItemTouchListeners.get(i9);
                if (interfaceC0458s2.mo2540b(this, motionEvent)) {
                    this.mActiveOnItemTouchListener = interfaceC0458s2;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 0) {
            this.mActiveOnItemTouchListener = null;
        }
        int size = this.mOnItemTouchListeners.size();
        for (int i9 = 0; i9 < size; i9++) {
            InterfaceC0458s interfaceC0458s = this.mOnItemTouchListeners.get(i9);
            if (interfaceC0458s.mo2540b(this, motionEvent) && action != 3) {
                this.mActiveOnItemTouchListener = interfaceC0458s;
                return true;
            }
        }
        return false;
    }

    private void findMinMaxChildLayoutPositions(int[] iArr) {
        int iM2772g = this.mChildHelper.m2772g();
        if (iM2772g == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i9 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        for (int i11 = 0; i11 < iM2772g; i11++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2771f(i11));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i9) {
                    i9 = layoutPosition;
                }
                if (layoutPosition > i10) {
                    i10 = layoutPosition;
                }
            }
        }
        iArr[0] = i9;
        iArr[1] = i10;
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            RecyclerView recyclerViewFindNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i9));
            if (recyclerViewFindNestedRecyclerView != null) {
                return recyclerViewFindNestedRecyclerView;
            }
        }
        return null;
    }

    private View findNextViewToFocus() {
        AbstractC0442c0 abstractC0442c0FindViewHolderForAdapterPosition;
        C0465z c0465z = this.mState;
        int i9 = c0465z.f2512m;
        if (i9 == -1) {
            i9 = 0;
        }
        int iM2620b = c0465z.m2620b();
        for (int i10 = i9; i10 < iM2620b; i10++) {
            AbstractC0442c0 abstractC0442c0FindViewHolderForAdapterPosition2 = findViewHolderForAdapterPosition(i10);
            if (abstractC0442c0FindViewHolderForAdapterPosition2 == null) {
                break;
            }
            if (abstractC0442c0FindViewHolderForAdapterPosition2.itemView.hasFocusable()) {
                return abstractC0442c0FindViewHolderForAdapterPosition2.itemView;
            }
        }
        int iMin = Math.min(iM2620b, i9);
        do {
            iMin--;
            if (iMin < 0 || (abstractC0442c0FindViewHolderForAdapterPosition = findViewHolderForAdapterPosition(iMin)) == null) {
                return null;
            }
        } while (!abstractC0442c0FindViewHolderForAdapterPosition.itemView.hasFocusable());
        return abstractC0442c0FindViewHolderForAdapterPosition.itemView;
    }

    public static AbstractC0442c0 getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((C0455p) view.getLayoutParams()).f2466a;
    }

    public static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        C0455p c0455p = (C0455p) view.getLayoutParams();
        Rect rect2 = c0455p.f2467b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) c0455p).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) c0455p).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) c0455p).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) c0455p).bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view) {
        int id = view.getId();
        while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            if (view.getId() != -1) {
                id = view.getId();
            }
        }
        return id;
    }

    private String getFullClassName(Context context, String str) {
        if (str.charAt(0) == '.') {
            return context.getPackageName() + str;
        }
        if (str.contains(".")) {
            return str;
        }
        return RecyclerView.class.getPackage().getName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + str;
    }

    private C4637m getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new C4637m(this);
        }
        return this.mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long j9, AbstractC0442c0 abstractC0442c0, AbstractC0442c0 abstractC0442c02) {
        int iM2772g = this.mChildHelper.m2772g();
        for (int i9 = 0; i9 < iM2772g; i9++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2771f(i9));
            if (childViewHolderInt != abstractC0442c0 && getChangedHolderKey(childViewHolderInt) == j9) {
                AbstractC0446g abstractC0446g = this.mAdapter;
                if (abstractC0446g == null || !abstractC0446g.hasStableIds()) {
                    throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + abstractC0442c0 + exceptionLabel());
                }
                throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + childViewHolderInt + " \n View Holder 2:" + abstractC0442c0 + exceptionLabel());
            }
        }
        Log.e(TAG, "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + abstractC0442c02 + " cannot be found but it is necessary for " + abstractC0442c0 + exceptionLabel());
    }

    private boolean hasUpdatedView() {
        int iM2772g = this.mChildHelper.m2772g();
        for (int i9 = 0; i9 < iM2772g; i9++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2771f(i9));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"InlinedApi"})
    private void initAutofill() {
        if (C4647u.m18565r(this) == 0) {
            C4647u.m18550j0(this, 8);
        }
    }

    private void initChildrenHelper() {
        this.mChildHelper = new C0474c(new C0444e());
    }

    private boolean isPreferredNextFocus(View view, View view2, int i9) {
        int i10;
        if (view2 == null || view2 == this || findContainingItemView(view2) == null) {
            return false;
        }
        if (view == null || findContainingItemView(view) == null) {
            return true;
        }
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        this.mTempRect2.set(0, 0, view2.getWidth(), view2.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        offsetDescendantRectToMyCoords(view2, this.mTempRect2);
        char c9 = 65535;
        int i11 = this.mLayout.m2468Y() == 1 ? -1 : 1;
        Rect rect = this.mTempRect;
        int i12 = rect.left;
        Rect rect2 = this.mTempRect2;
        int i13 = rect2.left;
        if ((i12 < i13 || rect.right <= i13) && rect.right < rect2.right) {
            i10 = 1;
        } else {
            int i14 = rect.right;
            int i15 = rect2.right;
            i10 = ((i14 > i15 || i12 >= i15) && i12 > i13) ? -1 : 0;
        }
        int i16 = rect.top;
        int i17 = rect2.top;
        if ((i16 < i17 || rect.bottom <= i17) && rect.bottom < rect2.bottom) {
            c9 = 1;
        } else {
            int i18 = rect.bottom;
            int i19 = rect2.bottom;
            if ((i18 <= i19 && i16 < i19) || i16 <= i17) {
                c9 = 0;
            }
        }
        if (i9 == 1) {
            return c9 < 0 || (c9 == 0 && i10 * i11 <= 0);
        }
        if (i9 == 2) {
            return c9 > 0 || (c9 == 0 && i10 * i11 >= 0);
        }
        if (i9 == 17) {
            return i10 < 0;
        }
        if (i9 == 33) {
            return c9 < 0;
        }
        if (i9 == 66) {
            return i10 > 0;
        }
        if (i9 == 130) {
            return c9 > 0;
        }
        throw new IllegalArgumentException("Invalid direction: " + i9 + exceptionLabel());
    }

    private void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i9 = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i9);
            int x8 = (int) (motionEvent.getX(i9) + 0.5f);
            this.mLastTouchX = x8;
            this.mInitialTouchX = x8;
            int y8 = (int) (motionEvent.getY(i9) + 0.5f);
            this.mLastTouchY = y8;
            this.mInitialTouchY = y8;
        }
    }

    private boolean predictiveItemAnimationsEnabled() {
        return this.mItemAnimator != null && this.mLayout.mo2216K1();
    }

    private void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z8;
        if (this.mDataSetHasChangedAfterLayout) {
            this.mAdapterHelper.m2758y();
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.mo2226R0(this);
            }
        }
        if (predictiveItemAnimationsEnabled()) {
            this.mAdapterHelper.m2756w();
        } else {
            this.mAdapterHelper.m2743j();
        }
        boolean z9 = false;
        boolean z10 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.f2510k = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z8 = this.mDataSetHasChangedAfterLayout) || z10 || this.mLayout.f2449h) && (!z8 || this.mAdapter.hasStableIds());
        C0465z c0465z = this.mState;
        if (c0465z.f2510k && z10 && !this.mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled()) {
            z9 = true;
        }
        c0465z.f2511l = z9;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void pullGlows(float f9, float f10, float f11, float f12) {
        boolean z8;
        boolean z9 = true;
        if (f10 < BitmapDescriptorFactory.HUE_RED) {
            ensureLeftGlow();
            C0329d.m1593a(this.mLeftGlow, (-f10) / getWidth(), 1.0f - (f11 / getHeight()));
        } else {
            if (f10 <= BitmapDescriptorFactory.HUE_RED) {
                z8 = false;
                if (f12 >= BitmapDescriptorFactory.HUE_RED) {
                    ensureTopGlow();
                    C0329d.m1593a(this.mTopGlow, (-f12) / getHeight(), f9 / getWidth());
                } else if (f12 > BitmapDescriptorFactory.HUE_RED) {
                    ensureBottomGlow();
                    C0329d.m1593a(this.mBottomGlow, f12 / getHeight(), 1.0f - (f9 / getWidth()));
                } else {
                    z9 = z8;
                }
                if (z9 && f10 == BitmapDescriptorFactory.HUE_RED && f12 == BitmapDescriptorFactory.HUE_RED) {
                    return;
                }
                C4647u.m18524T(this);
            }
            ensureRightGlow();
            C0329d.m1593a(this.mRightGlow, f10 / getWidth(), f11 / getHeight());
        }
        z8 = true;
        if (f12 >= BitmapDescriptorFactory.HUE_RED) {
        }
        if (z9) {
        }
        C4647u.m18524T(this);
    }

    private void recoverFocusFromState() {
        View viewFindViewById;
        if (!this.mPreserveFocusAfterLayout || this.mAdapter == null || !hasFocus() || getDescendantFocusability() == 393216) {
            return;
        }
        if (getDescendantFocusability() == 131072 && isFocused()) {
            return;
        }
        if (!isFocused()) {
            View focusedChild = getFocusedChild();
            if (!IGNORE_DETACHED_FOCUSED_CHILD || (focusedChild.getParent() != null && focusedChild.hasFocus())) {
                if (!this.mChildHelper.m2779n(focusedChild)) {
                    return;
                }
            } else if (this.mChildHelper.m2772g() == 0) {
                requestFocus();
                return;
            }
        }
        View viewFindNextViewToFocus = null;
        AbstractC0442c0 abstractC0442c0FindViewHolderForItemId = (this.mState.f2513n == -1 || !this.mAdapter.hasStableIds()) ? null : findViewHolderForItemId(this.mState.f2513n);
        if (abstractC0442c0FindViewHolderForItemId != null && !this.mChildHelper.m2779n(abstractC0442c0FindViewHolderForItemId.itemView) && abstractC0442c0FindViewHolderForItemId.itemView.hasFocusable()) {
            viewFindNextViewToFocus = abstractC0442c0FindViewHolderForItemId.itemView;
        } else if (this.mChildHelper.m2772g() > 0) {
            viewFindNextViewToFocus = findNextViewToFocus();
        }
        if (viewFindNextViewToFocus != null) {
            int i9 = this.mState.f2514o;
            if (i9 != -1 && (viewFindViewById = viewFindNextViewToFocus.findViewById(i9)) != null && viewFindViewById.isFocusable()) {
                viewFindNextViewToFocus = viewFindViewById;
            }
            viewFindNextViewToFocus.requestFocus();
        }
    }

    private void releaseGlows() {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.mLeftGlow.isFinished();
        } else {
            zIsFinished = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.mBottomGlow.isFinished();
        }
        if (zIsFinished) {
            C4647u.m18524T(this);
        }
    }

    private void requestChildOnScreen(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof C0455p) {
            C0455p c0455p = (C0455p) layoutParams;
            if (!c0455p.f2468c) {
                Rect rect = c0455p.f2467b;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.m2510r1(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    private void resetFocusInfo() {
        C0465z c0465z = this.mState;
        c0465z.f2513n = -1L;
        c0465z.f2512m = -1;
        c0465z.f2514o = -1;
    }

    private void resetTouch() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        stopNestedScroll(0);
        releaseGlows();
    }

    private void saveFocusInfo() {
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) ? getFocusedChild() : null;
        AbstractC0442c0 abstractC0442c0FindContainingViewHolder = focusedChild != null ? findContainingViewHolder(focusedChild) : null;
        if (abstractC0442c0FindContainingViewHolder == null) {
            resetFocusInfo();
            return;
        }
        this.mState.f2513n = this.mAdapter.hasStableIds() ? abstractC0442c0FindContainingViewHolder.getItemId() : -1L;
        this.mState.f2512m = this.mDataSetHasChangedAfterLayout ? -1 : abstractC0442c0FindContainingViewHolder.isRemoved() ? abstractC0442c0FindContainingViewHolder.mOldPosition : abstractC0442c0FindContainingViewHolder.getAdapterPosition();
        this.mState.f2514o = getDeepestFocusedViewWithId(abstractC0442c0FindContainingViewHolder.itemView);
    }

    private void setAdapterInternal(AbstractC0446g abstractC0446g, boolean z8, boolean z9) {
        AbstractC0446g abstractC0446g2 = this.mAdapter;
        if (abstractC0446g2 != null) {
            abstractC0446g2.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z8 || z9) {
            removeAndRecycleViews();
        }
        this.mAdapterHelper.m2758y();
        AbstractC0446g abstractC0446g3 = this.mAdapter;
        this.mAdapter = abstractC0446g;
        if (abstractC0446g != null) {
            abstractC0446g.registerAdapterDataObserver(this.mObserver);
            abstractC0446g.onAttachedToRecyclerView(this);
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.m2437D0(abstractC0446g3, this.mAdapter);
        }
        this.mRecycler.m2592x(abstractC0446g3, this.mAdapter, z8);
        this.mState.f2506g = true;
    }

    private void stopScrollersInternal() {
        this.mViewFlinger.m2355l();
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.m2449J1();
        }
    }

    public void absorbGlows(int i9, int i10) {
        if (i9 < 0) {
            ensureLeftGlow();
            this.mLeftGlow.onAbsorb(-i9);
        } else if (i9 > 0) {
            ensureRightGlow();
            this.mRightGlow.onAbsorb(i9);
        }
        if (i10 < 0) {
            ensureTopGlow();
            this.mTopGlow.onAbsorb(-i10);
        } else if (i10 > 0) {
            ensureBottomGlow();
            this.mBottomGlow.onAbsorb(i10);
        }
        if (i9 == 0 && i10 == 0) {
            return;
        }
        C4647u.m18524T(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i9, int i10) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null || !abstractC0454o.m2439E0(this, arrayList, i9, i10)) {
            super.addFocusables(arrayList, i9, i10);
        }
    }

    public void addItemDecoration(AbstractC0453n abstractC0453n, int i9) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.mo2293g("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i9 < 0) {
            this.mItemDecorations.add(abstractC0453n);
        } else {
            this.mItemDecorations.add(i9, abstractC0453n);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(InterfaceC0456q interfaceC0456q) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(interfaceC0456q);
    }

    public void addOnItemTouchListener(InterfaceC0458s interfaceC0458s) {
        this.mOnItemTouchListeners.add(interfaceC0458s);
    }

    public void addOnScrollListener(AbstractC0459t abstractC0459t) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(abstractC0459t);
    }

    public void animateAppearance(AbstractC0442c0 abstractC0442c0, AbstractC0451l.c cVar, AbstractC0451l.c cVar2) {
        abstractC0442c0.setIsRecyclable(false);
        if (this.mItemAnimator.mo2395a(abstractC0442c0, cVar, cVar2)) {
            postAnimationRunner();
        }
    }

    public void animateDisappearance(AbstractC0442c0 abstractC0442c0, AbstractC0451l.c cVar, AbstractC0451l.c cVar2) {
        addAnimatingView(abstractC0442c0);
        abstractC0442c0.setIsRecyclable(false);
        if (this.mItemAnimator.mo2397c(abstractC0442c0, cVar, cVar2)) {
            postAnimationRunner();
        }
    }

    public void assertInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + exceptionLabel());
        }
        throw new IllegalStateException(str + exceptionLabel());
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + exceptionLabel());
        }
        if (this.mDispatchScrollCounter > 0) {
            Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + exceptionLabel()));
        }
    }

    public boolean canReuseUpdatedViewHolder(AbstractC0442c0 abstractC0442c0) {
        AbstractC0451l abstractC0451l = this.mItemAnimator;
        return abstractC0451l == null || abstractC0451l.mo2400g(abstractC0442c0, abstractC0442c0.getUnmodifiedPayloads());
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0455p) && this.mLayout.mo2244m((C0455p) layoutParams);
    }

    public void clearOldPositions() {
        int iM2775j = this.mChildHelper.m2775j();
        for (int i9 = 0; i9 < iM2775j; i9++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i9));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        this.mRecycler.m2572d();
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<InterfaceC0456q> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<AbstractC0459t> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null && abstractC0454o.mo2298k()) {
            return this.mLayout.mo2307q(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null && abstractC0454o.mo2298k()) {
            return this.mLayout.mo2309r(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null && abstractC0454o.mo2298k()) {
            return this.mLayout.mo2311s(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null && abstractC0454o.mo2300l()) {
            return this.mLayout.mo2313t(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null && abstractC0454o.mo2300l()) {
            return this.mLayout.mo2315u(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null && abstractC0454o.mo2300l()) {
            return this.mLayout.mo2317v(this.mState);
        }
        return 0;
    }

    public void considerReleasingGlowsOnScroll(int i9, int i10) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i9 <= 0) {
            zIsFinished = false;
        } else {
            this.mLeftGlow.onRelease();
            zIsFinished = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i9 < 0) {
            this.mRightGlow.onRelease();
            zIsFinished |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i10 > 0) {
            this.mTopGlow.onRelease();
            zIsFinished |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i10 < 0) {
            this.mBottomGlow.onRelease();
            zIsFinished |= this.mBottomGlow.isFinished();
        }
        if (zIsFinished) {
            C4647u.m18524T(this);
        }
    }

    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            C6800f.m25355a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            dispatchLayout();
            C6800f.m25356b();
            return;
        }
        if (this.mAdapterHelper.m2749p()) {
            if (!this.mAdapterHelper.m2748o(4) || this.mAdapterHelper.m2748o(11)) {
                if (this.mAdapterHelper.m2749p()) {
                    C6800f.m25355a(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                    dispatchLayout();
                    C6800f.m25356b();
                    return;
                }
                return;
            }
            C6800f.m25355a(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            this.mAdapterHelper.m2756w();
            if (!this.mLayoutWasDefered) {
                if (hasUpdatedView()) {
                    dispatchLayout();
                } else {
                    this.mAdapterHelper.m2742i();
                }
            }
            stopInterceptRequestLayout(true);
            onExitLayoutOrScroll();
            C6800f.m25356b();
        }
    }

    public void defaultOnMeasure(int i9, int i10) {
        setMeasuredDimension(AbstractC0454o.m2428n(i9, getPaddingLeft() + getPaddingRight(), C4647u.m18569u(this)), AbstractC0454o.m2428n(i10, getPaddingTop() + getPaddingBottom(), C4647u.m18568t(this)));
    }

    public void dispatchChildAttached(View view) {
        AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        AbstractC0446g abstractC0446g = this.mAdapter;
        if (abstractC0446g != null && childViewHolderInt != null) {
            abstractC0446g.onViewAttachedToWindow(childViewHolderInt);
        }
        List<InterfaceC0456q> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).m2538b(view);
            }
        }
    }

    public void dispatchChildDetached(View view) {
        AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        AbstractC0446g abstractC0446g = this.mAdapter;
        if (abstractC0446g != null && childViewHolderInt != null) {
            abstractC0446g.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<InterfaceC0456q> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).m2537a(view);
            }
        }
    }

    public void dispatchLayout() {
        if (this.mAdapter == null) {
            Log.e(TAG, "No adapter attached; skipping layout");
            return;
        }
        if (this.mLayout == null) {
            Log.e(TAG, "No layout manager attached; skipping layout");
            return;
        }
        C0465z c0465z = this.mState;
        c0465z.f2509j = false;
        if (c0465z.f2504e == 1) {
            dispatchLayoutStep1();
            this.mLayout.m2523y1(this);
            dispatchLayoutStep2();
        } else if (!this.mAdapterHelper.m2750q() && this.mLayout.m2502n0() == getWidth() && this.mLayout.m2466W() == getHeight()) {
            this.mLayout.m2523y1(this);
        } else {
            this.mLayout.m2523y1(this);
            dispatchLayoutStep2();
        }
        dispatchLayoutStep3();
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f9, float f10, boolean z8) {
        return getScrollingChildHelper().m18474a(f9, f10, z8);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f9, float f10) {
        return getScrollingChildHelper().m18475b(f9, f10);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i9, int i10, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().m18476c(i9, i10, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i9, int i10, int i11, int i12, int[] iArr) {
        return getScrollingChildHelper().m18479f(i9, i10, i11, i12, iArr);
    }

    public void dispatchOnScrollStateChanged(int i9) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.mo2480d1(i9);
        }
        onScrollStateChanged(i9);
        AbstractC0459t abstractC0459t = this.mScrollListener;
        if (abstractC0459t != null) {
            abstractC0459t.m2542a(this, i9);
        }
        List<AbstractC0459t> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).m2542a(this, i9);
            }
        }
    }

    public void dispatchOnScrolled(int i9, int i10) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        onScrolled(i9, i10);
        AbstractC0459t abstractC0459t = this.mScrollListener;
        if (abstractC0459t != null) {
            abstractC0459t.mo2543b(this, i9, i10);
        }
        List<AbstractC0459t> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).mo2543b(this, i9, i10);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int i9;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            AbstractC0442c0 abstractC0442c0 = this.mPendingAccessibilityImportanceChange.get(size);
            if (abstractC0442c0.itemView.getParent() == this && !abstractC0442c0.shouldIgnore() && (i9 = abstractC0442c0.mPendingAccessibilityState) != -1) {
                C4647u.m18548i0(abstractC0442c0.itemView, i9);
                abstractC0442c0.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z8;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z9 = false;
        for (int i9 = 0; i9 < size; i9++) {
            this.mItemDecorations.get(i9).mo2425i(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z8 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, BitmapDescriptorFactory.HUE_RED);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z8 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z8 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(-paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z8 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate((-getWidth()) + getPaddingRight(), (-getHeight()) + getPaddingBottom());
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z9 = true;
            }
            z8 |= z9;
            canvas.restoreToCount(iSave4);
        }
        if ((z8 || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.mo2409p()) ? z8 : true) {
            C4647u.m18524T(this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j9) {
        return super.drawChild(canvas, view, j9);
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        EdgeEffect edgeEffectM2393a = this.mEdgeEffectFactory.m2393a(this, 3);
        this.mBottomGlow = edgeEffectM2393a;
        if (this.mClipToPadding) {
            edgeEffectM2393a.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectM2393a.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        EdgeEffect edgeEffectM2393a = this.mEdgeEffectFactory.m2393a(this, 0);
        this.mLeftGlow = edgeEffectM2393a;
        if (this.mClipToPadding) {
            edgeEffectM2393a.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectM2393a.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        EdgeEffect edgeEffectM2393a = this.mEdgeEffectFactory.m2393a(this, 2);
        this.mRightGlow = edgeEffectM2393a;
        if (this.mClipToPadding) {
            edgeEffectM2393a.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffectM2393a.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        EdgeEffect edgeEffectM2393a = this.mEdgeEffectFactory.m2393a(this, 1);
        this.mTopGlow = edgeEffectM2393a;
        if (this.mClipToPadding) {
            edgeEffectM2393a.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffectM2393a.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public String exceptionLabel() {
        return StringUtils.SPACE + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    public final void fillRemainingScrollValues(C0465z c0465z) {
        if (getScrollState() != 2) {
            c0465z.f2515p = 0;
            c0465z.f2516q = 0;
        } else {
            OverScroller overScroller = this.mViewFlinger.f2423d;
            c0465z.f2515p = overScroller.getFinalX() - overScroller.getCurrX();
            c0465z.f2516q = overScroller.getFinalY() - overScroller.getCurrY();
        }
    }

    public View findChildViewUnder(float f9, float f10) {
        for (int iM2772g = this.mChildHelper.m2772g() - 1; iM2772g >= 0; iM2772g--) {
            View viewM2771f = this.mChildHelper.m2771f(iM2772g);
            float translationX = viewM2771f.getTranslationX();
            float translationY = viewM2771f.getTranslationY();
            if (f9 >= viewM2771f.getLeft() + translationX && f9 <= viewM2771f.getRight() + translationX && f10 >= viewM2771f.getTop() + translationY && f10 <= viewM2771f.getBottom() + translationY) {
                return viewM2771f;
            }
        }
        return null;
    }

    public View findContainingItemView(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public AbstractC0442c0 findContainingViewHolder(View view) {
        View viewFindContainingItemView = findContainingItemView(view);
        if (viewFindContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(viewFindContainingItemView);
    }

    public AbstractC0442c0 findViewHolderForAdapterPosition(int i9) {
        AbstractC0442c0 abstractC0442c0 = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int iM2775j = this.mChildHelper.m2775j();
        for (int i10 = 0; i10 < iM2775j; i10++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i10));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionFor(childViewHolderInt) == i9) {
                if (!this.mChildHelper.m2779n(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                abstractC0442c0 = childViewHolderInt;
            }
        }
        return abstractC0442c0;
    }

    public AbstractC0442c0 findViewHolderForItemId(long j9) {
        AbstractC0446g abstractC0446g = this.mAdapter;
        AbstractC0442c0 abstractC0442c0 = null;
        if (abstractC0446g != null && abstractC0446g.hasStableIds()) {
            int iM2775j = this.mChildHelper.m2775j();
            for (int i9 = 0; i9 < iM2775j; i9++) {
                AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i9));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j9) {
                    if (!this.mChildHelper.m2779n(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    abstractC0442c0 = childViewHolderInt;
                }
            }
        }
        return abstractC0442c0;
    }

    public AbstractC0442c0 findViewHolderForLayoutPosition(int i9) {
        return findViewHolderForPosition(i9, false);
    }

    @Deprecated
    public AbstractC0442c0 findViewHolderForPosition(int i9) {
        return findViewHolderForPosition(i9, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean fling(int i9, int i10) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.mLayoutFrozen) {
            return false;
        }
        boolean zMo2298k = abstractC0454o.mo2298k();
        boolean zMo2300l = this.mLayout.mo2300l();
        if (zMo2298k == 0 || Math.abs(i9) < this.mMinFlingVelocity) {
            i9 = 0;
        }
        if (!zMo2300l || Math.abs(i10) < this.mMinFlingVelocity) {
            i10 = 0;
        }
        if (i9 == 0 && i10 == 0) {
            return false;
        }
        float f9 = i9;
        float f10 = i10;
        if (!dispatchNestedPreFling(f9, f10)) {
            boolean z8 = zMo2298k != 0 || zMo2300l;
            dispatchNestedFling(f9, f10, z8);
            int i11 = zMo2298k;
            if (z8) {
                if (zMo2300l) {
                    i11 = (zMo2298k ? 1 : 0) | 2;
                }
                startNestedScroll(i11, 1);
                int i12 = this.mMaxFlingVelocity;
                int iMax = Math.max(-i12, Math.min(i9, i12));
                int i13 = this.mMaxFlingVelocity;
                this.mViewFlinger.m2348e(iMax, Math.max(-i13, Math.min(i10, i13)));
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i9) {
        View viewMo2215I0;
        boolean z8;
        View viewM2458P0 = this.mLayout.m2458P0(view, i9);
        if (viewM2458P0 != null) {
            return viewM2458P0;
        }
        boolean z9 = (this.mAdapter == null || this.mLayout == null || isComputingLayout() || this.mLayoutFrozen) ? false : true;
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (z9 && (i9 == 2 || i9 == 1)) {
            if (this.mLayout.mo2300l()) {
                int i10 = i9 == 2 ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : 33;
                z8 = focusFinder.findNextFocus(this, view, i10) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i9 = i10;
                }
            } else {
                z8 = false;
            }
            if (!z8 && this.mLayout.mo2298k()) {
                int i11 = (this.mLayout.m2468Y() == 1) ^ (i9 == 2) ? 66 : 17;
                boolean z10 = focusFinder.findNextFocus(this, view, i11) == null;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                    i9 = i11;
                }
                z8 = z10;
            }
            if (z8) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                this.mLayout.mo2215I0(view, i9, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            }
            viewMo2215I0 = focusFinder.findNextFocus(this, view, i9);
        } else {
            View viewFindNextFocus = focusFinder.findNextFocus(this, view, i9);
            if (viewFindNextFocus == null && z9) {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null) {
                    return null;
                }
                startInterceptRequestLayout();
                viewMo2215I0 = this.mLayout.mo2215I0(view, i9, this.mRecycler, this.mState);
                stopInterceptRequestLayout(false);
            } else {
                viewMo2215I0 = viewFindNextFocus;
            }
        }
        if (viewMo2215I0 == null || viewMo2215I0.hasFocusable()) {
            return isPreferredNextFocus(view, viewMo2215I0, i9) ? viewMo2215I0 : super.focusSearch(view, i9);
        }
        if (getFocusedChild() == null) {
            return super.focusSearch(view, i9);
        }
        requestChildOnScreen(viewMo2215I0, null);
        return view;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            return abstractC0454o.mo2212D();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            return abstractC0454o.mo2213E(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    public AbstractC0446g getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionFor(AbstractC0442c0 abstractC0442c0) {
        if (abstractC0442c0.hasAnyOfTheFlags(524) || !abstractC0442c0.isBound()) {
            return -1;
        }
        return this.mAdapterHelper.m2738e(abstractC0442c0.mPosition);
    }

    @Override // android.view.View
    public int getBaseline() {
        AbstractC0454o abstractC0454o = this.mLayout;
        return abstractC0454o != null ? abstractC0454o.m2442G() : super.getBaseline();
    }

    public long getChangedHolderKey(AbstractC0442c0 abstractC0442c0) {
        return this.mAdapter.hasStableIds() ? abstractC0442c0.getItemId() : abstractC0442c0.mPosition;
    }

    public int getChildAdapterPosition(View view) {
        AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAdapterPosition();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i9, int i10) {
        return super.getChildDrawingOrder(i9, i10);
    }

    public long getChildItemId(View view) {
        AbstractC0442c0 childViewHolderInt;
        AbstractC0446g abstractC0446g = this.mAdapter;
        if (abstractC0446g == null || !abstractC0446g.hasStableIds() || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1L;
        }
        return childViewHolderInt.getItemId();
    }

    public int getChildLayoutPosition(View view) {
        AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    @Deprecated
    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public AbstractC0442c0 getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public C0484m getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public C0450k getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public AbstractC0451l getItemAnimator() {
        return this.mItemAnimator;
    }

    public Rect getItemDecorInsetsForChild(View view) {
        C0455p c0455p = (C0455p) view.getLayoutParams();
        if (!c0455p.f2468c) {
            return c0455p.f2467b;
        }
        if (this.mState.m2623e() && (c0455p.m2534b() || c0455p.m2536d())) {
            return c0455p.f2467b;
        }
        Rect rect = c0455p.f2467b;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i9).mo2421e(this.mTempRect, view, this, this.mState);
            int i10 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i10 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        c0455p.f2468c = false;
        return rect;
    }

    public AbstractC0453n getItemDecorationAt(int i9) {
        int itemDecorationCount = getItemDecorationCount();
        if (i9 >= 0 && i9 < itemDecorationCount) {
            return this.mItemDecorations.get(i9);
        }
        throw new IndexOutOfBoundsException(i9 + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public AbstractC0454o getLayoutManager() {
        return this.mLayout;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    public AbstractC0457r getOnFlingListener() {
        return null;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public C0460u getRecycledViewPool() {
        return this.mRecycler.m2577i();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().m18484k();
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.m2749p();
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new C0472a(new C0445f());
    }

    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable != null && drawable != null && stateListDrawable2 != null && drawable2 != null) {
            Resources resources = getContext().getResources();
            new C0477f(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(C5346a.fastscroll_default_thickness), resources.getDimensionPixelSize(C5346a.fastscroll_minimum_range), resources.getDimensionPixelOffset(C5346a.fastscroll_margin));
        } else {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + exceptionLabel());
        }
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.mo2293g("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean isAnimating() {
        AbstractC0451l abstractC0451l = this.mItemAnimator;
        return abstractC0451l != null && abstractC0451l.mo2409p();
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    public boolean isLayoutFrozen() {
        return this.mLayoutFrozen;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().m18486m();
    }

    public void jumpToPositionForSmoothScroller(int i9) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            return;
        }
        abstractC0454o.mo2319w1(i9);
        awakenScrollBars();
    }

    public void markItemDecorInsetsDirty() {
        int iM2775j = this.mChildHelper.m2775j();
        for (int i9 = 0; i9 < iM2775j; i9++) {
            ((C0455p) this.mChildHelper.m2774i(i9).getLayoutParams()).f2468c = true;
        }
        this.mRecycler.m2587s();
    }

    public void markKnownViewsInvalid() {
        int iM2775j = this.mChildHelper.m2775j();
        for (int i9 = 0; i9 < iM2775j; i9++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i9));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        this.mRecycler.m2588t();
    }

    public void offsetChildrenHorizontal(int i9) {
        int iM2772g = this.mChildHelper.m2772g();
        for (int i10 = 0; i10 < iM2772g; i10++) {
            this.mChildHelper.m2771f(i10).offsetLeftAndRight(i9);
        }
    }

    public void offsetChildrenVertical(int i9) {
        int iM2772g = this.mChildHelper.m2772g();
        for (int i10 = 0; i10 < iM2772g; i10++) {
            this.mChildHelper.m2771f(i10).offsetTopAndBottom(i9);
        }
    }

    public void offsetPositionRecordsForInsert(int i9, int i10) {
        int iM2775j = this.mChildHelper.m2775j();
        for (int i11 = 0; i11 < iM2775j; i11++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i11));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i9) {
                childViewHolderInt.offsetPosition(i10, false);
                this.mState.f2506g = true;
            }
        }
        this.mRecycler.m2589u(i9, i10);
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int i9, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int iM2775j = this.mChildHelper.m2775j();
        if (i9 < i10) {
            i13 = -1;
            i12 = i9;
            i11 = i10;
        } else {
            i11 = i9;
            i12 = i10;
            i13 = 1;
        }
        for (int i15 = 0; i15 < iM2775j; i15++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i15));
            if (childViewHolderInt != null && (i14 = childViewHolderInt.mPosition) >= i12 && i14 <= i11) {
                if (i14 == i9) {
                    childViewHolderInt.offsetPosition(i10 - i9, false);
                } else {
                    childViewHolderInt.offsetPosition(i13, false);
                }
                this.mState.f2506g = true;
            }
        }
        this.mRecycler.m2590v(i9, i10);
        requestLayout();
    }

    public void offsetPositionRecordsForRemove(int i9, int i10, boolean z8) {
        int i11 = i9 + i10;
        int iM2775j = this.mChildHelper.m2775j();
        for (int i12 = 0; i12 < iM2775j; i12++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i12));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i13 = childViewHolderInt.mPosition;
                if (i13 >= i11) {
                    childViewHolderInt.offsetPosition(-i10, z8);
                    this.mState.f2506g = true;
                } else if (i13 >= i9) {
                    childViewHolderInt.flagRemovedAndOffsetPosition(i9 - 1, -i10, z8);
                    this.mState.f2506g = true;
                }
            }
        }
        this.mRecycler.m2591w(i9, i10, z8);
        requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onAttachedToWindow() {
        float refreshRate;
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !isLayoutRequested();
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.m2524z(this);
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            ThreadLocal<RunnableC0478g> threadLocal = RunnableC0478g.f2686f;
            RunnableC0478g runnableC0478g = threadLocal.get();
            this.mGapWorker = runnableC0478g;
            if (runnableC0478g == null) {
                this.mGapWorker = new RunnableC0478g();
                Display displayM18557n = C4647u.m18557n(this);
                if (isInEditMode() || displayM18557n == null) {
                    refreshRate = 60.0f;
                    RunnableC0478g runnableC0478g2 = this.mGapWorker;
                    runnableC0478g2.f2690d = (long) (1.0E9f / refreshRate);
                    threadLocal.set(runnableC0478g2);
                } else {
                    refreshRate = displayM18557n.getRefreshRate();
                    if (refreshRate < 30.0f) {
                    }
                    RunnableC0478g runnableC0478g22 = this.mGapWorker;
                    runnableC0478g22.f2690d = (long) (1.0E9f / refreshRate);
                    threadLocal.set(runnableC0478g22);
                }
            }
            this.mGapWorker.m2832a(this);
        }
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        RunnableC0478g runnableC0478g;
        super.onDetachedFromWindow();
        AbstractC0451l abstractC0451l = this.mItemAnimator;
        if (abstractC0451l != null) {
            abstractC0451l.mo2404k();
        }
        stopScroll();
        this.mIsAttached = false;
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.m2430A(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.m2951j();
        if (!ALLOW_THREAD_GAP_WORK || (runnableC0478g = this.mGapWorker) == null) {
            return;
        }
        runnableC0478g.m2840j(this);
        this.mGapWorker = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.mItemDecorations.get(i9).mo2423g(canvas, this, this.mState);
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float f9;
        float axisValue;
        if (this.mLayout != null && !this.mLayoutFrozen && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                f9 = this.mLayout.mo2300l() ? -motionEvent.getAxisValue(9) : 0.0f;
                axisValue = this.mLayout.mo2298k() ? motionEvent.getAxisValue(10) : 0.0f;
                if (f9 != BitmapDescriptorFactory.HUE_RED || axisValue != BitmapDescriptorFactory.HUE_RED) {
                    scrollByInternal((int) (axisValue * this.mScaledHorizontalScrollFactor), (int) (f9 * this.mScaledVerticalScrollFactor), motionEvent);
                }
            } else if ((motionEvent.getSource() & 4194304) != 0) {
                float axisValue2 = motionEvent.getAxisValue(26);
                if (this.mLayout.mo2300l()) {
                    f9 = -axisValue2;
                    if (f9 != BitmapDescriptorFactory.HUE_RED) {
                        scrollByInternal((int) (axisValue * this.mScaledHorizontalScrollFactor), (int) (f9 * this.mScaledVerticalScrollFactor), motionEvent);
                    }
                } else {
                    if (this.mLayout.mo2298k()) {
                        axisValue = axisValue2;
                        f9 = 0.0f;
                    } else {
                        f9 = 0.0f;
                        axisValue = 0.0f;
                    }
                    if (f9 != BitmapDescriptorFactory.HUE_RED) {
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        if (this.mLayoutFrozen) {
            return false;
        }
        if (dispatchOnItemTouchIntercept(motionEvent)) {
            cancelTouch();
            return true;
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            return false;
        }
        boolean zMo2298k = abstractC0454o.mo2298k();
        boolean zMo2300l = this.mLayout.mo2300l();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x8 = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x8;
            this.mInitialTouchX = x8;
            int y8 = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y8;
            this.mInitialTouchY = y8;
            if (this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            int i9 = zMo2298k;
            if (zMo2300l) {
                i9 = (zMo2298k ? 1 : 0) | 2;
            }
            startNestedScroll(i9, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (iFindPointerIndex < 0) {
                Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x9 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y9 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i10 = x9 - this.mInitialTouchX;
                int i11 = y9 - this.mInitialTouchY;
                if (zMo2298k == 0 || Math.abs(i10) <= this.mTouchSlop) {
                    z8 = false;
                } else {
                    this.mLastTouchX = x9;
                    z8 = true;
                }
                if (zMo2300l && Math.abs(i11) > this.mTouchSlop) {
                    this.mLastTouchY = y9;
                    z8 = true;
                }
                if (z8) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            cancelTouch();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x10 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x10;
            this.mInitialTouchX = x10;
            int y10 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y10;
            this.mInitialTouchY = y10;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        return this.mScrollState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        C6800f.m25355a(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        C6800f.m25356b();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    public void onMeasure(int i9, int i10) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            defaultOnMeasure(i9, i10);
            return;
        }
        boolean z8 = false;
        if (abstractC0454o.mo2310r0()) {
            int mode = View.MeasureSpec.getMode(i9);
            int mode2 = View.MeasureSpec.getMode(i10);
            this.mLayout.m2469Y0(this.mRecycler, this.mState, i9, i10);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z8 = true;
            }
            if (z8 || this.mAdapter == null) {
                return;
            }
            if (this.mState.f2504e == 1) {
                dispatchLayoutStep1();
            }
            this.mLayout.m2526z1(i9, i10);
            this.mState.f2509j = true;
            dispatchLayoutStep2();
            this.mLayout.m2436C1(i9, i10);
            if (this.mLayout.mo2261F1()) {
                this.mLayout.m2526z1(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.mState.f2509j = true;
                dispatchLayoutStep2();
                this.mLayout.m2436C1(i9, i10);
                return;
            }
            return;
        }
        if (this.mHasFixedSize) {
            this.mLayout.m2469Y0(this.mRecycler, this.mState, i9, i10);
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            processAdapterUpdatesAndSetAnimationFlags();
            onExitLayoutOrScroll();
            C0465z c0465z = this.mState;
            if (c0465z.f2511l) {
                c0465z.f2507h = true;
            } else {
                this.mAdapterHelper.m2743j();
                this.mState.f2507h = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else if (this.mState.f2511l) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        AbstractC0446g abstractC0446g = this.mAdapter;
        if (abstractC0446g != null) {
            this.mState.f2505f = abstractC0446g.getItemCount();
        } else {
            this.mState.f2505f = 0;
        }
        startInterceptRequestLayout();
        this.mLayout.m2469Y0(this.mRecycler, this.mState, i9, i10);
        stopInterceptRequestLayout(false);
        this.mState.f2507h = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i9, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i9, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.getSuperState());
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null || (parcelable2 = this.mPendingSavedState.f2418b) == null) {
            return;
        }
        abstractC0454o.mo2287b1(parcelable2);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.m2340a(savedState2);
        } else {
            AbstractC0454o abstractC0454o = this.mLayout;
            if (abstractC0454o != null) {
                savedState.f2418b = abstractC0454o.mo2289c1();
            } else {
                savedState.f2418b = null;
            }
        }
        return savedState;
    }

    public void onScrollStateChanged(int i9) {
    }

    public void onScrolled(int i9, int i10) {
    }

    @Override // android.view.View
    public void onSizeChanged(int i9, int i10, int i11, int i12) {
        super.onSizeChanged(i9, i10, i11, i12);
        if (i9 == i11 && i10 == i12) {
            return;
        }
        invalidateGlows();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0109  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z8;
        boolean z9 = false;
        if (this.mLayoutFrozen || this.mIgnoreMotionEventTillDown) {
            return false;
        }
        if (dispatchOnItemTouch(motionEvent)) {
            cancelTouch();
            return true;
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            return false;
        }
        boolean zMo2298k = abstractC0454o.mo2298k();
        boolean zMo2300l = this.mLayout.mo2300l();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
        }
        int[] iArr2 = this.mNestedOffsets;
        motionEventObtain.offsetLocation(iArr2[0], iArr2[1]);
        if (actionMasked == 0) {
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x8 = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x8;
            this.mInitialTouchX = x8;
            int y8 = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y8;
            this.mInitialTouchY = y8;
            int i9 = zMo2298k;
            if (zMo2300l) {
                i9 = (zMo2298k ? 1 : 0) | 2;
            }
            startNestedScroll(i9, 0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.addMovement(motionEventObtain);
            this.mVelocityTracker.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT, this.mMaxFlingVelocity);
            float f9 = zMo2298k != 0 ? -this.mVelocityTracker.getXVelocity(this.mScrollPointerId) : 0.0f;
            float f10 = zMo2300l ? -this.mVelocityTracker.getYVelocity(this.mScrollPointerId) : 0.0f;
            if ((f9 == BitmapDescriptorFactory.HUE_RED && f10 == BitmapDescriptorFactory.HUE_RED) || !fling((int) f9, (int) f10)) {
                setScrollState(0);
            }
            resetTouch();
            z9 = true;
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (iFindPointerIndex < 0) {
                Log.e(TAG, "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x9 = (int) (motionEvent.getX(iFindPointerIndex) + 0.5f);
            int y9 = (int) (motionEvent.getY(iFindPointerIndex) + 0.5f);
            int i10 = this.mLastTouchX - x9;
            int i11 = this.mLastTouchY - y9;
            if (dispatchNestedPreScroll(i10, i11, this.mScrollConsumed, this.mScrollOffset, 0)) {
                int[] iArr3 = this.mScrollConsumed;
                i10 -= iArr3[0];
                i11 -= iArr3[1];
                int[] iArr4 = this.mScrollOffset;
                motionEventObtain.offsetLocation(iArr4[0], iArr4[1]);
                int[] iArr5 = this.mNestedOffsets;
                int i12 = iArr5[0];
                int[] iArr6 = this.mScrollOffset;
                iArr5[0] = i12 + iArr6[0];
                iArr5[1] = iArr5[1] + iArr6[1];
            }
            if (this.mScrollState != 1) {
                if (zMo2298k != 0) {
                    int iAbs = Math.abs(i10);
                    int i13 = this.mTouchSlop;
                    if (iAbs > i13) {
                        i10 = i10 > 0 ? i10 - i13 : i10 + i13;
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    if (zMo2300l) {
                        int iAbs2 = Math.abs(i11);
                        int i14 = this.mTouchSlop;
                        if (iAbs2 > i14) {
                            i11 = i11 > 0 ? i11 - i14 : i11 + i14;
                            z8 = true;
                        }
                    }
                    if (z8) {
                        setScrollState(1);
                    }
                }
            }
            if (this.mScrollState == 1) {
                int[] iArr7 = this.mScrollOffset;
                this.mLastTouchX = x9 - iArr7[0];
                this.mLastTouchY = y9 - iArr7[1];
                if (scrollByInternal(zMo2298k != 0 ? i10 : 0, zMo2300l ? i11 : 0, motionEventObtain)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                RunnableC0478g runnableC0478g = this.mGapWorker;
                if (runnableC0478g != null && (i10 != 0 || i11 != 0)) {
                    runnableC0478g.m2836f(this, i10, i11);
                }
            }
        } else if (actionMasked == 3) {
            cancelTouch();
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x10 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x10;
            this.mInitialTouchX = x10;
            int y10 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y10;
            this.mInitialTouchY = y10;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        if (!z9) {
            this.mVelocityTracker.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public void postAnimationRunner() {
        if (this.mPostedAnimatorRunner || !this.mIsAttached) {
            return;
        }
        C4647u.m18525U(this, this.mItemAnimatorRunner);
        this.mPostedAnimatorRunner = true;
    }

    public void processDataSetCompletelyChanged(boolean z8) {
        this.mDispatchItemsChangedEvent = z8 | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    public void recordAnimationInfoIfBouncedHiddenView(AbstractC0442c0 abstractC0442c0, AbstractC0451l.c cVar) {
        abstractC0442c0.setFlags(0, UserMetadata.MAX_INTERNAL_KEY_SIZE);
        if (this.mState.f2508i && abstractC0442c0.isUpdated() && !abstractC0442c0.isRemoved() && !abstractC0442c0.shouldIgnore()) {
            this.mViewInfoStore.m2944c(getChangedHolderKey(abstractC0442c0), abstractC0442c0);
        }
        this.mViewInfoStore.m2946e(abstractC0442c0, cVar);
    }

    public void removeAndRecycleViews() {
        AbstractC0451l abstractC0451l = this.mItemAnimator;
        if (abstractC0451l != null) {
            abstractC0451l.mo2404k();
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.m2495j1(this.mRecycler);
            this.mLayout.m2497k1(this.mRecycler);
        }
        this.mRecycler.m2571c();
    }

    public boolean removeAnimatingView(View view) {
        startInterceptRequestLayout();
        boolean zM2783r = this.mChildHelper.m2783r(view);
        if (zM2783r) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(view);
            this.mRecycler.m2565J(childViewHolderInt);
            this.mRecycler.m2558C(childViewHolderInt);
        }
        stopInterceptRequestLayout(!zM2783r);
        return zM2783r;
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z8) {
        AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + exceptionLabel());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z8);
    }

    public void removeItemDecoration(AbstractC0453n abstractC0453n) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            abstractC0454o.mo2293g("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(abstractC0453n);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeItemDecorationAt(int i9) {
        int itemDecorationCount = getItemDecorationCount();
        if (i9 >= 0 && i9 < itemDecorationCount) {
            removeItemDecoration(getItemDecorationAt(i9));
            return;
        }
        throw new IndexOutOfBoundsException(i9 + " is an invalid index for size " + itemDecorationCount);
    }

    public void removeOnChildAttachStateChangeListener(InterfaceC0456q interfaceC0456q) {
        List<InterfaceC0456q> list = this.mOnChildAttachStateListeners;
        if (list == null) {
            return;
        }
        list.remove(interfaceC0456q);
    }

    public void removeOnItemTouchListener(InterfaceC0458s interfaceC0458s) {
        this.mOnItemTouchListeners.remove(interfaceC0458s);
        if (this.mActiveOnItemTouchListener == interfaceC0458s) {
            this.mActiveOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(AbstractC0459t abstractC0459t) {
        List<AbstractC0459t> list = this.mScrollListeners;
        if (list != null) {
            list.remove(abstractC0459t);
        }
    }

    public void repositionShadowingViews() {
        AbstractC0442c0 abstractC0442c0;
        int iM2772g = this.mChildHelper.m2772g();
        for (int i9 = 0; i9 < iM2772g; i9++) {
            View viewM2771f = this.mChildHelper.m2771f(i9);
            AbstractC0442c0 childViewHolder = getChildViewHolder(viewM2771f);
            if (childViewHolder != null && (abstractC0442c0 = childViewHolder.mShadowingHolder) != null) {
                View view = abstractC0442c0.itemView;
                int left = viewM2771f.getLeft();
                int top = viewM2771f.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.m2473a1(this, this.mState, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z8) {
        return this.mLayout.m2509q1(this, view, rect, z8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z8) {
        int size = this.mOnItemTouchListeners.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.mOnItemTouchListeners.get(i9).mo2541c(z8);
        }
        super.requestDisallowInterceptTouchEvent(z8);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutFrozen) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public void saveOldPositions() {
        int iM2775j = this.mChildHelper.m2775j();
        for (int i9 = 0; i9 < iM2775j; i9++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i9));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    @Override // android.view.View
    public void scrollBy(int i9, int i10) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutFrozen) {
            return;
        }
        boolean zMo2298k = abstractC0454o.mo2298k();
        boolean zMo2300l = this.mLayout.mo2300l();
        if (zMo2298k || zMo2300l) {
            if (!zMo2298k) {
                i9 = 0;
            }
            if (!zMo2300l) {
                i10 = 0;
            }
            scrollByInternal(i9, i10, null);
        }
    }

    public boolean scrollByInternal(int i9, int i10, MotionEvent motionEvent) {
        int i11;
        int i12;
        int i13;
        int i14;
        consumePendingUpdateOperations();
        if (this.mAdapter != null) {
            scrollStep(i9, i10, this.mScrollStepConsumed);
            int[] iArr = this.mScrollStepConsumed;
            int i15 = iArr[0];
            int i16 = iArr[1];
            i12 = i16;
            i13 = i15;
            i14 = i9 - i15;
            i11 = i10 - i16;
        } else {
            i11 = 0;
            i12 = 0;
            i13 = 0;
            i14 = 0;
        }
        if (!this.mItemDecorations.isEmpty()) {
            invalidate();
        }
        int i17 = i11;
        if (dispatchNestedScroll(i13, i12, i14, i11, this.mScrollOffset, 0)) {
            int i18 = this.mLastTouchX;
            int[] iArr2 = this.mScrollOffset;
            int i19 = iArr2[0];
            this.mLastTouchX = i18 - i19;
            int i20 = this.mLastTouchY;
            int i21 = iArr2[1];
            this.mLastTouchY = i20 - i21;
            if (motionEvent != null) {
                motionEvent.offsetLocation(i19, i21);
            }
            int[] iArr3 = this.mNestedOffsets;
            int i22 = iArr3[0];
            int[] iArr4 = this.mScrollOffset;
            iArr3[0] = i22 + iArr4[0];
            iArr3[1] = iArr3[1] + iArr4[1];
        } else if (getOverScrollMode() != 2) {
            if (motionEvent != null && !C4631j.m18470a(motionEvent, 8194)) {
                pullGlows(motionEvent.getX(), i14, motionEvent.getY(), i17);
            }
            considerReleasingGlowsOnScroll(i9, i10);
        }
        if (i13 != 0 || i12 != 0) {
            dispatchOnScrolled(i13, i12);
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        return (i13 == 0 && i12 == 0) ? false : true;
    }

    public void scrollStep(int i9, int i10, int[] iArr) {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        C6800f.m25355a(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        int iMo2247v1 = i9 != 0 ? this.mLayout.mo2247v1(i9, this.mRecycler, this.mState) : 0;
        int iMo2248x1 = i10 != 0 ? this.mLayout.mo2248x1(i10, this.mRecycler, this.mState) : 0;
        C6800f.m25356b();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = iMo2247v1;
            iArr[1] = iMo2248x1;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i9, int i10) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i9) {
        if (this.mLayoutFrozen) {
            return;
        }
        stopScroll();
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            abstractC0454o.mo2319w1(i9);
            awakenScrollBars();
        }
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (shouldDeferAccessibilityEvent(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setAccessibilityDelegateCompat(C0484m c0484m) {
        this.mAccessibilityDelegate = c0484m;
        C4647u.m18530Z(this, c0484m);
    }

    public void setAdapter(AbstractC0446g abstractC0446g) {
        setLayoutFrozen(false);
        setAdapterInternal(abstractC0446g, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(InterfaceC0449j interfaceC0449j) {
        if (interfaceC0449j == null) {
            return;
        }
        setChildrenDrawingOrderEnabled(false);
    }

    public boolean setChildImportantForAccessibilityInternal(AbstractC0442c0 abstractC0442c0, int i9) {
        if (!isComputingLayout()) {
            C4647u.m18548i0(abstractC0442c0.itemView, i9);
            return true;
        }
        abstractC0442c0.mPendingAccessibilityState = i9;
        this.mPendingAccessibilityImportanceChange.add(abstractC0442c0);
        return false;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z8) {
        if (z8 != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z8;
        super.setClipToPadding(z8);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectFactory(C0450k c0450k) {
        C0702h.m3468b(c0450k);
        this.mEdgeEffectFactory = c0450k;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z8) {
        this.mHasFixedSize = z8;
    }

    public void setItemAnimator(AbstractC0451l abstractC0451l) {
        AbstractC0451l abstractC0451l2 = this.mItemAnimator;
        if (abstractC0451l2 != null) {
            abstractC0451l2.mo2404k();
            this.mItemAnimator.m2415v(null);
        }
        this.mItemAnimator = abstractC0451l;
        if (abstractC0451l != null) {
            abstractC0451l.m2415v(this.mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int i9) {
        this.mRecycler.m2562G(i9);
    }

    public void setLayoutFrozen(boolean z8) {
        if (z8 != this.mLayoutFrozen) {
            assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
            if (z8) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0));
                this.mLayoutFrozen = true;
                this.mIgnoreMotionEventTillDown = true;
                stopScroll();
                return;
            }
            this.mLayoutFrozen = false;
            if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                requestLayout();
            }
            this.mLayoutWasDefered = false;
        }
    }

    public void setLayoutManager(AbstractC0454o abstractC0454o) {
        if (abstractC0454o == this.mLayout) {
            return;
        }
        stopScroll();
        if (this.mLayout != null) {
            AbstractC0451l abstractC0451l = this.mItemAnimator;
            if (abstractC0451l != null) {
                abstractC0451l.mo2404k();
            }
            this.mLayout.m2495j1(this.mRecycler);
            this.mLayout.m2497k1(this.mRecycler);
            this.mRecycler.m2571c();
            if (this.mIsAttached) {
                this.mLayout.m2430A(this, this.mRecycler);
            }
            this.mLayout.m2438D1(null);
            this.mLayout = null;
        } else {
            this.mRecycler.m2571c();
        }
        this.mChildHelper.m2780o();
        this.mLayout = abstractC0454o;
        if (abstractC0454o != null) {
            if (abstractC0454o.f2443b != null) {
                throw new IllegalArgumentException("LayoutManager " + abstractC0454o + " is already attached to a RecyclerView:" + abstractC0454o.f2443b.exceptionLabel());
            }
            abstractC0454o.m2438D1(this);
            if (this.mIsAttached) {
                this.mLayout.m2524z(this);
            }
        }
        this.mRecycler.m2566K();
        requestLayout();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z8) {
        getScrollingChildHelper().m18487n(z8);
    }

    public void setOnFlingListener(AbstractC0457r abstractC0457r) {
    }

    @Deprecated
    public void setOnScrollListener(AbstractC0459t abstractC0459t) {
        this.mScrollListener = abstractC0459t;
    }

    public void setPreserveFocusAfterLayout(boolean z8) {
        this.mPreserveFocusAfterLayout = z8;
    }

    public void setRecycledViewPool(C0460u c0460u) {
        this.mRecycler.m2560E(c0460u);
    }

    public void setRecyclerListener(InterfaceC0462w interfaceC0462w) {
    }

    public void setScrollState(int i9) {
        if (i9 == this.mScrollState) {
            return;
        }
        this.mScrollState = i9;
        if (i9 != 2) {
            stopScrollersInternal();
        }
        dispatchOnScrollStateChanged(i9);
    }

    public void setScrollingTouchSlop(int i9) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        if (i9 != 0) {
            if (i9 == 1) {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w(TAG, "setScrollingTouchSlop(): bad argument constant " + i9 + "; using default value");
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(AbstractC0438a0 abstractC0438a0) {
        this.mRecycler.m2561F(abstractC0438a0);
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (!isComputingLayout()) {
            return false;
        }
        int iM18745a = accessibilityEvent != null ? C4693b.m18745a(accessibilityEvent) : 0;
        this.mEatenAccessibilityChangeFlags |= iM18745a != 0 ? iM18745a : 0;
        return true;
    }

    public void smoothScrollBy(int i9, int i10) {
        smoothScrollBy(i9, i10, null);
    }

    public void smoothScrollToPosition(int i9) {
        if (this.mLayoutFrozen) {
            return;
        }
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            abstractC0454o.mo2265H1(this, this.mState, i9);
        }
    }

    public void startInterceptRequestLayout() {
        int i9 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i9;
        if (i9 != 1 || this.mLayoutFrozen) {
            return;
        }
        this.mLayoutWasDefered = false;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i9) {
        return getScrollingChildHelper().m18489p(i9);
    }

    public void stopInterceptRequestLayout(boolean z8) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z8 && !this.mLayoutFrozen) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z8 && this.mLayoutWasDefered && !this.mLayoutFrozen && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutFrozen) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().m18491r();
    }

    public void stopScroll() {
        setScrollState(0);
        stopScrollersInternal();
    }

    public void swapAdapter(AbstractC0446g abstractC0446g, boolean z8) {
        setLayoutFrozen(false);
        setAdapterInternal(abstractC0446g, true, z8);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public void viewRangeUpdate(int i9, int i10, Object obj) {
        int i11;
        int iM2775j = this.mChildHelper.m2775j();
        int i12 = i9 + i10;
        for (int i13 = 0; i13 < iM2775j; i13++) {
            View viewM2774i = this.mChildHelper.m2774i(i13);
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(viewM2774i);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i11 = childViewHolderInt.mPosition) >= i9 && i11 < i12) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((C0455p) viewM2774i.getLayoutParams()).f2468c = true;
            }
        }
        this.mRecycler.m2568M(i9, i10);
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public boolean dispatchNestedPreScroll(int i9, int i10, int[] iArr, int[] iArr2, int i11) {
        return getScrollingChildHelper().m18477d(i9, i10, iArr, iArr2, i11);
    }

    public boolean dispatchNestedScroll(int i9, int i10, int i11, int i12, int[] iArr, int i13) {
        return getScrollingChildHelper().m18480g(i9, i10, i11, i12, iArr, i13);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AbstractC0442c0 findViewHolderForPosition(int i9, boolean z8) {
        int iM2775j = this.mChildHelper.m2775j();
        AbstractC0442c0 abstractC0442c0 = null;
        for (int i10 = 0; i10 < iM2775j; i10++) {
            AbstractC0442c0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.m2774i(i10));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved()) {
                if (z8) {
                    if (childViewHolderInt.mPosition != i9) {
                        continue;
                    } else {
                        if (!this.mChildHelper.m2779n(childViewHolderInt.itemView)) {
                            return childViewHolderInt;
                        }
                        abstractC0442c0 = childViewHolderInt;
                    }
                } else if (childViewHolderInt.getLayoutPosition() != i9) {
                    continue;
                }
            }
        }
        return abstractC0442c0;
    }

    public boolean hasNestedScrollingParent(int i9) {
        return getScrollingChildHelper().m18485l(i9);
    }

    public void onExitLayoutOrScroll(boolean z8) {
        int i9 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i9;
        if (i9 < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z8) {
                dispatchContentChangedIfNecessary();
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public void smoothScrollBy(int i9, int i10, Interpolator interpolator) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutFrozen) {
            return;
        }
        if (!abstractC0454o.mo2298k()) {
            i9 = 0;
        }
        if (!this.mLayout.mo2300l()) {
            i10 = 0;
        }
        if (i9 == 0 && i10 == 0) {
            return;
        }
        this.mViewFlinger.m2354k(i9, i10, interpolator);
    }

    public boolean startNestedScroll(int i9, int i10) {
        return getScrollingChildHelper().m18490q(i9, i10);
    }

    @Override // p042d0.InterfaceC4633k
    public void stopNestedScroll(int i9) {
        getScrollingChildHelper().m18492s(i9);
    }

    public RecyclerView(Context context, AttributeSet attributeSet, int i9) throws NoSuchMethodException, SecurityException {
        super(context, attributeSet, i9);
        this.mObserver = new C0463x();
        this.mRecycler = new C0461v();
        this.mViewInfoStore = new C0489r();
        this.mUpdateChildViewsRunnable = new RunnableC0437a();
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = new C0450k();
        this.mItemAnimator = new C0475d();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        boolean z8 = true;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new RunnableC0440b0();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new RunnableC0478g.b() : null;
        this.mState = new C0465z();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new C0452m();
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.mNestedOffsets = new int[2];
        this.mScrollStepConsumed = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new RunnableC0439b();
        this.mViewInfoProcessCallback = new C0443d();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, CLIP_TO_PADDING_ATTR, i9, 0);
            this.mClipToPadding = typedArrayObtainStyledAttributes.getBoolean(0, true);
            typedArrayObtainStyledAttributes.recycle();
        } else {
            this.mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = C4614a0.m18390a(viewConfiguration, context);
        this.mScaledVerticalScrollFactor = C4614a0.m18391b(viewConfiguration, context);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.m2415v(this.mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        if (C4647u.m18563q(this) == 0) {
            C4647u.m18548i0(this, 1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new C0484m(this));
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, C5347b.RecyclerView, i9, 0);
            String string = typedArrayObtainStyledAttributes2.getString(C5347b.RecyclerView_layoutManager);
            if (typedArrayObtainStyledAttributes2.getInt(C5347b.RecyclerView_android_descendantFocusability, -1) == -1) {
                setDescendantFocusability(262144);
            }
            boolean z9 = typedArrayObtainStyledAttributes2.getBoolean(C5347b.RecyclerView_fastScrollEnabled, false);
            this.mEnableFastScroller = z9;
            if (z9) {
                initFastScroller((StateListDrawable) typedArrayObtainStyledAttributes2.getDrawable(C5347b.RecyclerView_fastScrollVerticalThumbDrawable), typedArrayObtainStyledAttributes2.getDrawable(C5347b.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable) typedArrayObtainStyledAttributes2.getDrawable(C5347b.RecyclerView_fastScrollHorizontalThumbDrawable), typedArrayObtainStyledAttributes2.getDrawable(C5347b.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            typedArrayObtainStyledAttributes2.recycle();
            createLayoutManager(context, string, attributeSet, i9, 0);
            TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, NESTED_SCROLLING_ATTRS, i9, 0);
            z8 = typedArrayObtainStyledAttributes3.getBoolean(0, true);
            typedArrayObtainStyledAttributes3.recycle();
        } else {
            setDescendantFocusability(262144);
        }
        setNestedScrollingEnabled(z8);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0436a();

        /* renamed from: b */
        public Parcelable f2418b;

        /* renamed from: androidx.recyclerview.widget.RecyclerView$SavedState$a */
        public static class C0436a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2418b = parcel.readParcelable(classLoader == null ? AbstractC0454o.class.getClassLoader() : classLoader);
        }

        /* renamed from: a */
        public void m2340a(SavedState savedState) {
            this.f2418b = savedState.f2418b;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeParcelable(this.f2418b, 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* renamed from: androidx.recyclerview.widget.RecyclerView$p */
    public static class C0455p extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public AbstractC0442c0 f2466a;

        /* renamed from: b */
        public final Rect f2467b;

        /* renamed from: c */
        public boolean f2468c;

        /* renamed from: d */
        public boolean f2469d;

        public C0455p(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2467b = new Rect();
            this.f2468c = true;
            this.f2469d = false;
        }

        /* renamed from: a */
        public int m2533a() {
            return this.f2466a.getLayoutPosition();
        }

        /* renamed from: b */
        public boolean m2534b() {
            return this.f2466a.isUpdated();
        }

        /* renamed from: c */
        public boolean m2535c() {
            return this.f2466a.isRemoved();
        }

        /* renamed from: d */
        public boolean m2536d() {
            return this.f2466a.isInvalid();
        }

        public C0455p(int i9, int i10) {
            super(i9, i10);
            this.f2467b = new Rect();
            this.f2468c = true;
            this.f2469d = false;
        }

        public C0455p(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f2467b = new Rect();
            this.f2468c = true;
            this.f2469d = false;
        }

        public C0455p(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f2467b = new Rect();
            this.f2468c = true;
            this.f2469d = false;
        }

        public C0455p(C0455p c0455p) {
            super((ViewGroup.LayoutParams) c0455p);
            this.f2467b = new Rect();
            this.f2468c = true;
            this.f2469d = false;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        AbstractC0454o abstractC0454o = this.mLayout;
        if (abstractC0454o != null) {
            return abstractC0454o.mo2214F(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    public void addItemDecoration(AbstractC0453n abstractC0453n) {
        addItemDecoration(abstractC0453n, -1);
    }
}
