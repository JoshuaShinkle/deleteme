package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.lifecycle.C0386g;
import androidx.lifecycle.C0390k;
import androidx.lifecycle.C0396q;
import androidx.lifecycle.InterfaceC0383d;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.InterfaceC0397r;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.savedstate.C0493a;
import androidx.savedstate.InterfaceC0494b;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import p021c0.C0695a;
import p042d0.C4625g;
import p102j0.AbstractC5066a;
import p188s.AbstractC6241p;

/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, InterfaceC0385f, InterfaceC0397r, InterfaceC0494b {
    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    boolean mAdded;
    C0356d mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    private boolean mCalled;
    LayoutInflaterFactory2C0369h mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    LayoutInflaterFactory2C0369h mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    AbstractC0367f mHost;
    boolean mInLayout;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    private Boolean mIsPrimaryNavigationFragment;
    LayoutInflater mLayoutInflater;
    C0386g mLifecycleRegistry;
    Lifecycle.State mMaxState;
    boolean mMenuVisible;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    Runnable mPostponedDurationRunnable;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    C0493a mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    SparseArray<Parcelable> mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    String mTargetWho;
    boolean mUserVisibleHint;
    View mView;
    C0376o mViewLifecycleOwner;
    C0390k<InterfaceC0385f> mViewLifecycleOwnerLiveData;
    String mWho;

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$a */
    public class RunnableC0353a implements Runnable {
        public RunnableC0353a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.startPostponedEnterTransition();
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$b */
    public class RunnableC0354b implements Runnable {
        public RunnableC0354b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.callStartTransitionListener();
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$c */
    public class C0355c extends AbstractC0364c {
        public C0355c() {
        }

        @Override // androidx.fragment.app.AbstractC0364c
        /* renamed from: b */
        public View mo1761b(int i9) {
            View view = Fragment.this.mView;
            if (view != null) {
                return view.findViewById(i9);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // androidx.fragment.app.AbstractC0364c
        /* renamed from: c */
        public boolean mo1762c() {
            return Fragment.this.mView != null;
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$d */
    public static class C0356d {

        /* renamed from: a */
        public View f1965a;

        /* renamed from: b */
        public Animator f1966b;

        /* renamed from: c */
        public int f1967c;

        /* renamed from: d */
        public int f1968d;

        /* renamed from: e */
        public int f1969e;

        /* renamed from: f */
        public int f1970f;

        /* renamed from: g */
        public Object f1971g = null;

        /* renamed from: h */
        public Object f1972h;

        /* renamed from: i */
        public Object f1973i;

        /* renamed from: j */
        public Object f1974j;

        /* renamed from: k */
        public Object f1975k;

        /* renamed from: l */
        public Object f1976l;

        /* renamed from: m */
        public Boolean f1977m;

        /* renamed from: n */
        public Boolean f1978n;

        /* renamed from: o */
        public boolean f1979o;

        /* renamed from: p */
        public InterfaceC0357e f1980p;

        /* renamed from: q */
        public boolean f1981q;

        public C0356d() {
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            this.f1972h = obj;
            this.f1973i = null;
            this.f1974j = obj;
            this.f1975k = null;
            this.f1976l = obj;
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$e */
    public interface InterfaceC0357e {
        /* renamed from: a */
        void mo1763a();

        /* renamed from: b */
        void mo1764b();
    }

    public Fragment() {
        this.mState = 0;
        this.mWho = UUID.randomUUID().toString();
        this.mTargetWho = null;
        this.mIsPrimaryNavigationFragment = null;
        this.mChildFragmentManager = new LayoutInflaterFactory2C0369h();
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mPostponedDurationRunnable = new RunnableC0353a();
        this.mMaxState = Lifecycle.State.RESUMED;
        this.mViewLifecycleOwnerLiveData = new C0390k<>();
        initLifecycle();
    }

    private C0356d ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new C0356d();
        }
        return this.mAnimationInfo;
    }

    private void initLifecycle() {
        this.mLifecycleRegistry = new C0386g(this);
        this.mSavedStateRegistryController = C0493a.m2968a(this);
        this.mLifecycleRegistry.mo2047a(new InterfaceC0383d() { // from class: androidx.fragment.app.Fragment.2
            @Override // androidx.lifecycle.InterfaceC0383d
            /* renamed from: c */
            public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
                View view;
                if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.mView) == null) {
                    return;
                }
                view.cancelPendingInputEvents();
            }
        });
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    public void callStartTransitionListener() {
        C0356d c0356d = this.mAnimationInfo;
        InterfaceC0357e interfaceC0357e = null;
        if (c0356d != null) {
            c0356d.f1979o = false;
            InterfaceC0357e interfaceC0357e2 = c0356d.f1980p;
            c0356d.f1980p = null;
            interfaceC0357e = interfaceC0357e2;
        }
        if (interfaceC0357e != null) {
            interfaceC0357e.mo1764b();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (this.mInnerView != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            AbstractC5066a.m19833b(this).mo19834a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.mo1845b(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Fragment findFragmentByWho(String str) {
        return str.equals(this.mWho) ? this : this.mChildFragmentManager.m1957v0(str);
    }

    public final FragmentActivity getActivity() {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f == null) {
            return null;
        }
        return (FragmentActivity) abstractC0367f.m1841d();
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null || (bool = c0356d.f1978n) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null || (bool = c0356d.f1977m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public View getAnimatingAway() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        return c0356d.f1965a;
    }

    public Animator getAnimator() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        return c0356d.f1966b;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final AbstractC0368g getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public Context getContext() {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f == null) {
            return null;
        }
        return abstractC0367f.m1842e();
    }

    public Object getEnterTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        return c0356d.f1971g;
    }

    public AbstractC6241p getEnterTransitionCallback() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        c0356d.getClass();
        return null;
    }

    public Object getExitTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        return c0356d.f1973i;
    }

    public AbstractC6241p getExitTransitionCallback() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        c0356d.getClass();
        return null;
    }

    public final AbstractC0368g getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f == null) {
            return null;
        }
        return abstractC0367f.mo1767i();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // androidx.lifecycle.InterfaceC0385f
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public AbstractC5066a getLoaderManager() {
        return AbstractC5066a.m19833b(this);
    }

    public int getNextAnim() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return 0;
        }
        return c0356d.f1968d;
    }

    public int getNextTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return 0;
        }
        return c0356d.f1969e;
    }

    public int getNextTransitionStyle() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return 0;
        }
        return c0356d.f1970f;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        Object obj = c0356d.f1974j;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        Object obj = c0356d.f1972h;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    @Override // androidx.savedstate.InterfaceC0494b
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.m2969b();
    }

    public Object getSharedElementEnterTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        return c0356d.f1975k;
    }

    public Object getSharedElementReturnTransition() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return null;
        }
        Object obj = c0356d.f1976l;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    public int getStateAfterAnimating() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return 0;
        }
        return c0356d.f1967c;
    }

    public final String getString(int i9) {
        return getResources().getString(i9);
    }

    public final String getTag() {
        return this.mTag;
    }

    public final Fragment getTargetFragment() {
        String str;
        Fragment fragment = this.mTarget;
        if (fragment != null) {
            return fragment;
        }
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return layoutInflaterFactory2C0369h.f2031h.get(str);
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i9) {
        return getResources().getText(i9);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public InterfaceC0385f getViewLifecycleOwner() {
        C0376o c0376o = this.mViewLifecycleOwner;
        if (c0376o != null) {
            return c0376o;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public LiveData<InterfaceC0385f> getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.InterfaceC0397r
    public C0396q getViewModelStore() {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.mFragmentManager;
        if (layoutInflaterFactory2C0369h != null) {
            return layoutInflaterFactory2C0369h.m1870D0(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        initLifecycle();
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new LayoutInflaterFactory2C0369h();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public boolean isHideReplaced() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return false;
        }
        return c0356d.f1981q;
    }

    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    public boolean isPostponed() {
        C0356d c0356d = this.mAnimationInfo;
        if (c0356d == null) {
            return false;
        }
        return c0356d.f1979o;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 4;
    }

    public final boolean isStateSaved() {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null) {
            return false;
        }
        return layoutInflaterFactory2C0369h.m1884K0();
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.m1904V0();
    }

    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    public void onActivityResult(int i9, int i10, Intent intent) {
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        AbstractC0367f abstractC0367f = this.mHost;
        Activity activityM1841d = abstractC0367f == null ? null : abstractC0367f.m1841d();
        if (activityM1841d != null) {
            this.mCalled = false;
            onAttach(activityM1841d);
        }
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        if (this.mChildFragmentManager.m1882J0(1)) {
            return;
        }
        this.mChildFragmentManager.m1875G();
    }

    public Animation onCreateAnimation(int i9, boolean z8, int i10) {
        return null;
    }

    public Animator onCreateAnimator(int i9, boolean z8, int i10) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i9 = this.mContentLayoutId;
        if (i9 != 0) {
            return layoutInflater.inflate(i9, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z8) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        AbstractC0367f abstractC0367f = this.mHost;
        Activity activityM1841d = abstractC0367f == null ? null : abstractC0367f.m1841d();
        if (activityM1841d != null) {
            this.mCalled = false;
            onInflate(activityM1841d, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z8) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z8) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z8) {
    }

    public void onRequestPermissionsResult(int i9, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.m1904V0();
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (this.mCalled) {
            this.mChildFragmentManager.m1869D();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    public void performAttach() {
        this.mChildFragmentManager.m1954u(this.mHost, new C0355c(), this);
        this.mCalled = false;
        onAttach(this.mHost.m1842e());
        if (this.mCalled) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
    }

    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.mChildFragmentManager.m1871E(configuration);
    }

    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        return onContextItemSelected(menuItem) || this.mChildFragmentManager.m1873F(menuItem);
    }

    public void performCreate(Bundle bundle) {
        this.mChildFragmentManager.m1904V0();
        this.mState = 1;
        this.mCalled = false;
        this.mSavedStateRegistryController.m2970c(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.m2088i(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z8 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z8 = true;
        }
        return z8 | this.mChildFragmentManager.m1877H(menu, menuInflater);
    }

    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.m1904V0();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new C0376o();
        View viewOnCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = viewOnCreateView;
        if (viewOnCreateView != null) {
            this.mViewLifecycleOwner.m2044b();
            this.mViewLifecycleOwnerLiveData.mo2061l(this.mViewLifecycleOwner);
        } else {
            if (this.mViewLifecycleOwner.m2045c()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.mViewLifecycleOwner = null;
        }
    }

    public void performDestroy() {
        this.mChildFragmentManager.m1879I();
        this.mLifecycleRegistry.m2088i(Lifecycle.Event.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (this.mCalled) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public void performDestroyView() {
        this.mChildFragmentManager.m1881J();
        if (this.mView != null) {
            this.mViewLifecycleOwner.m2043a(Lifecycle.Event.ON_DESTROY);
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            AbstractC5066a.m19833b(this).mo19836d();
            this.mPerformedCreateView = false;
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    public void performDetach() {
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled) {
            if (this.mChildFragmentManager.m1876G0()) {
                return;
            }
            this.mChildFragmentManager.m1879I();
            this.mChildFragmentManager = new LayoutInflaterFactory2C0369h();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
    }

    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = layoutInflaterOnGetLayoutInflater;
        return layoutInflaterOnGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
        this.mChildFragmentManager.m1883K();
    }

    public void performMultiWindowModeChanged(boolean z8) {
        onMultiWindowModeChanged(z8);
        this.mChildFragmentManager.m1885L(z8);
    }

    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        return (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) || this.mChildFragmentManager.m1913a0(menuItem);
    }

    public void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        this.mChildFragmentManager.m1915b0(menu);
    }

    public void performPause() {
        this.mChildFragmentManager.m1919d0();
        if (this.mView != null) {
            this.mViewLifecycleOwner.m2043a(Lifecycle.Event.ON_PAUSE);
        }
        this.mLifecycleRegistry.m2088i(Lifecycle.Event.ON_PAUSE);
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (this.mCalled) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
    }

    public void performPictureInPictureModeChanged(boolean z8) {
        onPictureInPictureModeChanged(z8);
        this.mChildFragmentManager.m1921e0(z8);
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z8 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z8 = true;
        }
        return z8 | this.mChildFragmentManager.m1923f0(menu);
    }

    public void performPrimaryNavigationFragmentChanged() {
        boolean zM1880I0 = this.mFragmentManager.m1880I0(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != zM1880I0) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(zM1880I0);
            onPrimaryNavigationFragmentChanged(zM1880I0);
            this.mChildFragmentManager.m1924g0();
        }
    }

    public void performResume() {
        this.mChildFragmentManager.m1904V0();
        this.mChildFragmentManager.m1947q0();
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        C0386g c0386g = this.mLifecycleRegistry;
        Lifecycle.Event event = Lifecycle.Event.ON_RESUME;
        c0386g.m2088i(event);
        if (this.mView != null) {
            this.mViewLifecycleOwner.m2043a(event);
        }
        this.mChildFragmentManager.m1926h0();
        this.mChildFragmentManager.m1947q0();
    }

    public void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.m2971d(bundle);
        Parcelable parcelableM1925g1 = this.mChildFragmentManager.m1925g1();
        if (parcelableM1925g1 != null) {
            bundle.putParcelable("android:support:fragments", parcelableM1925g1);
        }
    }

    public void performStart() {
        this.mChildFragmentManager.m1904V0();
        this.mChildFragmentManager.m1947q0();
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        C0386g c0386g = this.mLifecycleRegistry;
        Lifecycle.Event event = Lifecycle.Event.ON_START;
        c0386g.m2088i(event);
        if (this.mView != null) {
            this.mViewLifecycleOwner.m2043a(event);
        }
        this.mChildFragmentManager.m1928i0();
    }

    public void performStop() {
        this.mChildFragmentManager.m1932k0();
        if (this.mView != null) {
            this.mViewLifecycleOwner.m2043a(Lifecycle.Event.ON_STOP);
        }
        this.mLifecycleRegistry.m2088i(Lifecycle.Event.ON_STOP);
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (this.mCalled) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().f1979o = true;
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(String[] strArr, int i9) {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f != null) {
            abstractC0367f.mo1771m(this, strArr, i9);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final FragmentActivity requireActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException("Fragment " + this + " does not have any arguments.");
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public final AbstractC0368g requireFragmentManager() {
        AbstractC0368g fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a host.");
    }

    public final Fragment requireParentFragment() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException("Fragment " + this + " is not attached to any Fragment or host");
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    public void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.mChildFragmentManager.m1922e1(parcelable);
        this.mChildFragmentManager.m1875G();
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mInnerView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (this.mCalled) {
            if (this.mView != null) {
                this.mViewLifecycleOwner.m2043a(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z8) {
        ensureAnimationInfo().f1978n = Boolean.valueOf(z8);
    }

    public void setAllowReturnTransitionOverlap(boolean z8) {
        ensureAnimationInfo().f1977m = Boolean.valueOf(z8);
    }

    public void setAnimatingAway(View view) {
        ensureAnimationInfo().f1965a = view;
    }

    public void setAnimator(Animator animator) {
        ensureAnimationInfo().f1966b = animator;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(AbstractC6241p abstractC6241p) {
        ensureAnimationInfo().getClass();
    }

    public void setEnterTransition(Object obj) {
        ensureAnimationInfo().f1971g = obj;
    }

    public void setExitSharedElementCallback(AbstractC6241p abstractC6241p) {
        ensureAnimationInfo().getClass();
    }

    public void setExitTransition(Object obj) {
        ensureAnimationInfo().f1973i = obj;
    }

    public void setHasOptionsMenu(boolean z8) {
        if (this.mHasMenu != z8) {
            this.mHasMenu = z8;
            if (!isAdded() || isHidden()) {
                return;
            }
            this.mHost.mo1776r();
        }
    }

    public void setHideReplaced(boolean z8) {
        ensureAnimationInfo().f1981q = z8;
    }

    public void setInitialSavedState(SavedState savedState) {
        Bundle bundle;
        if (this.mFragmentManager != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (savedState == null || (bundle = savedState.f1961b) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z8) {
        if (this.mMenuVisible != z8) {
            this.mMenuVisible = z8;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                this.mHost.mo1776r();
            }
        }
    }

    public void setNextAnim(int i9) {
        if (this.mAnimationInfo == null && i9 == 0) {
            return;
        }
        ensureAnimationInfo().f1968d = i9;
    }

    public void setNextTransition(int i9, int i10) {
        if (this.mAnimationInfo == null && i9 == 0 && i10 == 0) {
            return;
        }
        ensureAnimationInfo();
        C0356d c0356d = this.mAnimationInfo;
        c0356d.f1969e = i9;
        c0356d.f1970f = i10;
    }

    public void setOnStartEnterTransitionListener(InterfaceC0357e interfaceC0357e) {
        ensureAnimationInfo();
        C0356d c0356d = this.mAnimationInfo;
        InterfaceC0357e interfaceC0357e2 = c0356d.f1980p;
        if (interfaceC0357e == interfaceC0357e2) {
            return;
        }
        if (interfaceC0357e != null && interfaceC0357e2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (c0356d.f1979o) {
            c0356d.f1980p = interfaceC0357e;
        }
        if (interfaceC0357e != null) {
            interfaceC0357e.mo1763a();
        }
    }

    public void setReenterTransition(Object obj) {
        ensureAnimationInfo().f1974j = obj;
    }

    public void setRetainInstance(boolean z8) {
        this.mRetainInstance = z8;
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z8) {
            layoutInflaterFactory2C0369h.m1948r(this);
        } else {
            layoutInflaterFactory2C0369h.m1918c1(this);
        }
    }

    public void setReturnTransition(Object obj) {
        ensureAnimationInfo().f1972h = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        ensureAnimationInfo().f1975k = obj;
    }

    public void setSharedElementReturnTransition(Object obj) {
        ensureAnimationInfo().f1976l = obj;
    }

    public void setStateAfterAnimating(int i9) {
        ensureAnimationInfo().f1967c = i9;
    }

    public void setTargetFragment(Fragment fragment, int i9) {
        AbstractC0368g fragmentManager = getFragmentManager();
        AbstractC0368g fragmentManager2 = fragment != null ? fragment.getFragmentManager() : null;
        if (fragmentManager != null && fragmentManager2 != null && fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException("Fragment " + fragment + " must share the same FragmentManager to be set as a target fragment");
        }
        for (Fragment targetFragment = fragment; targetFragment != null; targetFragment = targetFragment.getTargetFragment()) {
            if (targetFragment == this) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        } else if (this.mFragmentManager == null || fragment.mFragmentManager == null) {
            this.mTargetWho = null;
            this.mTarget = fragment;
        } else {
            this.mTargetWho = fragment.mWho;
            this.mTarget = null;
        }
        this.mTargetRequestCode = i9;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z8) {
        if (!this.mUserVisibleHint && z8 && this.mState < 3 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            this.mFragmentManager.m1906W0(this);
        }
        this.mUserVisibleHint = z8;
        this.mDeferStart = this.mState < 3 && !z8;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z8);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f != null) {
            return abstractC0367f.mo1773o(str);
        }
        return false;
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent) {
        startActivity(intent, null);
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i9) {
        startActivityForResult(intent, i9, null);
    }

    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12, Bundle bundle) {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f != null) {
            abstractC0367f.mo1775q(this, intentSender, i9, intent, i10, i11, i12, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void startPostponedEnterTransition() {
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.mFragmentManager;
        if (layoutInflaterFactory2C0369h == null || layoutInflaterFactory2C0369h.f2041r == null) {
            ensureAnimationInfo().f1979o = false;
        } else if (Looper.myLooper() != this.mFragmentManager.f2041r.m1843f().getLooper()) {
            this.mFragmentManager.f2041r.m1843f().postAtFrontOfQueue(new RunnableC0354b());
        } else {
            callStartTransitionListener();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        C0695a.m3459a(this, sb);
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")");
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(StringUtils.SPACE);
            sb.append(this.mTag);
        }
        sb.append('}');
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0352a();

        /* renamed from: b */
        public final Bundle f1961b;

        /* renamed from: androidx.fragment.app.Fragment$SavedState$a */
        public static class C0352a implements Parcelable.ClassLoaderCreator<SavedState> {
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

        public SavedState(Bundle bundle) {
            this.f1961b = bundle;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            parcel.writeBundle(this.f1961b);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle bundle = parcel.readBundle();
            this.f1961b = bundle;
            if (classLoader == null || bundle == null) {
                return;
            }
            bundle.setClassLoader(classLoader);
        }
    }

    @Deprecated
    public static Fragment instantiate(Context context, String str, Bundle bundle) throws IllegalAccessException, java.lang.InstantiationException, IllegalArgumentException, InvocationTargetException {
        try {
            Fragment fragmentNewInstance = C0366e.m1839d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.setArguments(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e9) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e9);
        } catch (java.lang.InstantiationException e10) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e10);
        } catch (NoSuchMethodException e11) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e11);
        } catch (InvocationTargetException e12) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e12);
        }
    }

    public final String getString(int i9, Object... objArr) {
        return getResources().getString(i9, objArr);
    }

    public final void postponeEnterTransition(long j9, TimeUnit timeUnit) {
        ensureAnimationInfo().f1979o = true;
        LayoutInflaterFactory2C0369h layoutInflaterFactory2C0369h = this.mFragmentManager;
        Handler handlerM1843f = layoutInflaterFactory2C0369h != null ? layoutInflaterFactory2C0369h.f2041r.m1843f() : new Handler(Looper.getMainLooper());
        handlerM1843f.removeCallbacks(this.mPostponedDurationRunnable);
        handlerM1843f.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j9));
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent, Bundle bundle) {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f != null) {
            abstractC0367f.mo1774p(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i9, Bundle bundle) {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f != null) {
            abstractC0367f.mo1774p(this, intent, i9, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        AbstractC0367f abstractC0367f = this.mHost;
        if (abstractC0367f != null) {
            LayoutInflater layoutInflaterMo1768j = abstractC0367f.mo1768j();
            C4625g.m18427a(layoutInflaterMo1768j, this.mChildFragmentManager.m1866B0());
            return layoutInflaterMo1768j;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }

    public Fragment(int i9) {
        this();
        this.mContentLayoutId = i9;
    }
}
