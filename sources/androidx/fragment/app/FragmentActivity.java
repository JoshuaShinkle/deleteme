package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.InterfaceC0096c;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.C0386g;
import androidx.lifecycle.C0396q;
import androidx.lifecycle.InterfaceC0397r;
import androidx.lifecycle.Lifecycle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p102j0.AbstractC5066a;
import p132m.C5309h;
import p188s.AbstractC6241p;
import p188s.C6226a;

/* loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements C6226a.b {
    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final C0386g mFragmentLifecycleRegistry;
    final C0365d mFragments;
    int mNextCandidateRequestIndex;
    C5309h<String> mPendingFragmentActivityResults;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mStartedActivityFromFragment;
    boolean mStartedIntentSenderFromFragment;
    boolean mStopped;

    /* renamed from: androidx.fragment.app.FragmentActivity$a */
    public class C0358a extends AbstractC0367f<FragmentActivity> implements InterfaceC0397r, InterfaceC0096c {
        public C0358a() {
            super(FragmentActivity.this);
        }

        @Override // androidx.fragment.app.AbstractC0367f, androidx.fragment.app.AbstractC0364c
        /* renamed from: b */
        public View mo1761b(int i9) {
            return FragmentActivity.this.findViewById(i9);
        }

        @Override // androidx.fragment.app.AbstractC0367f, androidx.fragment.app.AbstractC0364c
        /* renamed from: c */
        public boolean mo1762c() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: g */
        public void mo1765g(Fragment fragment) {
            FragmentActivity.this.onAttachFragment(fragment);
        }

        @Override // androidx.lifecycle.InterfaceC0385f
        public Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.InterfaceC0096c
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.getOnBackPressedDispatcher();
        }

        @Override // androidx.lifecycle.InterfaceC0397r
        public C0396q getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: h */
        public void mo1766h(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: j */
        public LayoutInflater mo1768j() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: k */
        public int mo1769k() {
            Window window = FragmentActivity.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: l */
        public boolean mo1770l() {
            return FragmentActivity.this.getWindow() != null;
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: m */
        public void mo1771m(Fragment fragment, String[] strArr, int i9) {
            FragmentActivity.this.requestPermissionsFromFragment(fragment, strArr, i9);
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: n */
        public boolean mo1772n(Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: o */
        public boolean mo1773o(String str) {
            return C6226a.m23791q(FragmentActivity.this, str);
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: p */
        public void mo1774p(Fragment fragment, Intent intent, int i9, Bundle bundle) {
            FragmentActivity.this.startActivityFromFragment(fragment, intent, i9, bundle);
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: q */
        public void mo1775q(Fragment fragment, IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12, Bundle bundle) {
            FragmentActivity.this.startIntentSenderFromFragment(fragment, intentSender, i9, intent, i10, i11, i12, bundle);
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: r */
        public void mo1776r() {
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }

        @Override // androidx.fragment.app.AbstractC0367f
        /* renamed from: s, reason: merged with bridge method [inline-methods] */
        public FragmentActivity mo1767i() {
            return FragmentActivity.this;
        }
    }

    public FragmentActivity() {
        this.mFragments = C0365d.m1812b(new C0358a());
        this.mFragmentLifecycleRegistry = new C0386g(this);
        this.mStopped = true;
    }

    private int allocateRequestIndex(Fragment fragment) {
        if (this.mPendingFragmentActivityResults.m20768n() >= MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (this.mPendingFragmentActivityResults.m20762h(this.mNextCandidateRequestIndex) >= 0) {
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        }
        int i9 = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.m20765k(i9, fragment.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
        return i9;
    }

    public static void checkForValidRequestCode(int i9) {
        if ((i9 & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    private void markFragmentsCreated() {
        while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED)) {
        }
    }

    private static boolean markState(AbstractC0368g abstractC0368g, Lifecycle.State state) {
        boolean zMarkState = false;
        for (Fragment fragment : abstractC0368g.mo1852i()) {
            if (fragment != null) {
                if (fragment.getLifecycle().mo2048b().m2050a(Lifecycle.State.STARTED)) {
                    fragment.mLifecycleRegistry.m2094p(state);
                    zMarkState = true;
                }
                if (fragment.getHost() != null) {
                    zMarkState |= markState(fragment.getChildFragmentManager(), state);
                }
            }
        }
        return zMarkState;
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.m1834w(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            AbstractC5066a.m19833b(this).mo19834a(str2, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.m1832u().mo1845b(str, fileDescriptor, printWriter, strArr);
    }

    public AbstractC0368g getSupportFragmentManager() {
        return this.mFragments.m1832u();
    }

    @Deprecated
    public AbstractC5066a getSupportLoaderManager() {
        return AbstractC5066a.m19833b(this);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        this.mFragments.m1833v();
        int i11 = i9 >> 16;
        if (i11 == 0) {
            C6226a.m23785k();
            super.onActivityResult(i9, i10, intent);
            return;
        }
        int i12 = i11 - 1;
        String strM20760e = this.mPendingFragmentActivityResults.m20760e(i12);
        this.mPendingFragmentActivityResults.m20766l(i12);
        if (strM20760e == null) {
            Log.w(TAG, "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentM1831t = this.mFragments.m1831t(strM20760e);
        if (fragmentM1831t != null) {
            fragmentM1831t.onActivityResult(i9 & 65535, i10, intent);
            return;
        }
        Log.w(TAG, "Activity result no fragment exists for who: " + strM20760e);
    }

    public void onAttachFragment(Fragment fragment) {
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.m1833v();
        this.mFragments.m1815d(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mFragments.m1813a(null);
        if (bundle != null) {
            this.mFragments.m1835x(bundle.getParcelable(FRAGMENTS_TAG));
            if (bundle.containsKey(NEXT_CANDIDATE_REQUEST_INDEX_TAG)) {
                this.mNextCandidateRequestIndex = bundle.getInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG);
                int[] intArray = bundle.getIntArray(ALLOCATED_REQUEST_INDICIES_TAG);
                String[] stringArray = bundle.getStringArray(REQUEST_FRAGMENT_WHO_TAG);
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w(TAG, "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.mPendingFragmentActivityResults = new C5309h<>(intArray.length);
                    for (int i9 = 0; i9 < intArray.length; i9++) {
                        this.mPendingFragmentActivityResults.m20765k(intArray[i9], stringArray[i9]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new C5309h<>();
            this.mNextCandidateRequestIndex = 0;
        }
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_CREATE);
        this.mFragments.m1817f();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i9, Menu menu) {
        return i9 == 0 ? super.onCreatePanelMenu(i9, menu) | this.mFragments.m1818g(menu, getMenuInflater()) : super.onCreatePanelMenu(i9, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.m1819h();
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.m1820i();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i9, MenuItem menuItem) {
        if (super.onMenuItemSelected(i9, menuItem)) {
            return true;
        }
        if (i9 == 0) {
            return this.mFragments.m1822k(menuItem);
        }
        if (i9 != 6) {
            return false;
        }
        return this.mFragments.m1816e(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z8) {
        this.mFragments.m1821j(z8);
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.m1833v();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i9, Menu menu) {
        if (i9 == 0) {
            this.mFragments.m1823l(menu);
        }
        super.onPanelClosed(i9, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.m1824m();
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z8) {
        this.mFragments.m1825n(z8);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Deprecated
    public boolean onPrepareOptionsPanel(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i9, View view, Menu menu) {
        return i9 == 0 ? onPrepareOptionsPanel(view, menu) | this.mFragments.m1826o(menu) : super.onPreparePanel(i9, view, menu);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i9, String[] strArr, int[] iArr) {
        this.mFragments.m1833v();
        int i10 = (i9 >> 16) & 65535;
        if (i10 != 0) {
            int i11 = i10 - 1;
            String strM20760e = this.mPendingFragmentActivityResults.m20760e(i11);
            this.mPendingFragmentActivityResults.m20766l(i11);
            if (strM20760e == null) {
                Log.w(TAG, "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentM1831t = this.mFragments.m1831t(strM20760e);
            if (fragmentM1831t != null) {
                fragmentM1831t.onRequestPermissionsResult(i9 & 65535, strArr, iArr);
                return;
            }
            Log.w(TAG, "Activity result no fragment exists for who: " + strM20760e);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mResumed = true;
        this.mFragments.m1833v();
        this.mFragments.m1830s();
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_RESUME);
        this.mFragments.m1827p();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        markFragmentsCreated();
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_STOP);
        Parcelable parcelableM1836y = this.mFragments.m1836y();
        if (parcelableM1836y != null) {
            bundle.putParcelable(FRAGMENTS_TAG, parcelableM1836y);
        }
        if (this.mPendingFragmentActivityResults.m20768n() > 0) {
            bundle.putInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG, this.mNextCandidateRequestIndex);
            int[] iArr = new int[this.mPendingFragmentActivityResults.m20768n()];
            String[] strArr = new String[this.mPendingFragmentActivityResults.m20768n()];
            for (int i9 = 0; i9 < this.mPendingFragmentActivityResults.m20768n(); i9++) {
                iArr[i9] = this.mPendingFragmentActivityResults.m20764j(i9);
                strArr[i9] = this.mPendingFragmentActivityResults.m20769o(i9);
            }
            bundle.putIntArray(ALLOCATED_REQUEST_INDICIES_TAG, iArr);
            bundle.putStringArray(REQUEST_FRAGMENT_WHO_TAG, strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.m1814c();
        }
        this.mFragments.m1833v();
        this.mFragments.m1830s();
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_START);
        this.mFragments.m1828q();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.m1833v();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        this.mFragments.m1829r();
        this.mFragmentLifecycleRegistry.m2088i(Lifecycle.Event.ON_STOP);
    }

    public void requestPermissionsFromFragment(Fragment fragment, String[] strArr, int i9) {
        if (i9 == -1) {
            C6226a.m23788n(this, strArr, i9);
            return;
        }
        checkForValidRequestCode(i9);
        try {
            this.mRequestedPermissionsFromFragment = true;
            C6226a.m23788n(this, strArr, ((allocateRequestIndex(fragment) + 1) << 16) + (i9 & 65535));
        } finally {
            this.mRequestedPermissionsFromFragment = false;
        }
    }

    public void setEnterSharedElementCallback(AbstractC6241p abstractC6241p) {
        C6226a.m23789o(this, abstractC6241p);
    }

    public void setExitSharedElementCallback(AbstractC6241p abstractC6241p) {
        C6226a.m23790p(this, abstractC6241p);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i9) {
        if (!this.mStartedActivityFromFragment && i9 != -1) {
            checkForValidRequestCode(i9);
        }
        super.startActivityForResult(intent, i9);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i9) {
        startActivityFromFragment(fragment, intent, i9, (Bundle) null);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i9 != -1) {
            checkForValidRequestCode(i9);
        }
        super.startIntentSenderForResult(intentSender, i9, intent, i10, i11, i12);
    }

    public void startIntentSenderFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12, Bundle bundle) {
        this.mStartedIntentSenderFromFragment = true;
        try {
            if (i9 == -1) {
                C6226a.m23793s(this, intentSender, i9, intent, i10, i11, i12, bundle);
            } else {
                checkForValidRequestCode(i9);
                C6226a.m23793s(this, intentSender, ((allocateRequestIndex(fragment) + 1) << 16) + (i9 & 65535), intent, i10, i11, i12, bundle);
            }
        } finally {
            this.mStartedIntentSenderFromFragment = false;
        }
    }

    public void supportFinishAfterTransition() {
        C6226a.m23784j(this);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    public void supportPostponeEnterTransition() {
        C6226a.m23786l(this);
    }

    public void supportStartPostponedEnterTransition() {
        C6226a.m23794t(this);
    }

    @Override // p188s.C6226a.b
    public final void validateRequestPermissionsRequestCode(int i9) {
        if (this.mRequestedPermissionsFromFragment || i9 == -1) {
            return;
        }
        checkForValidRequestCode(i9);
    }

    public void startActivityFromFragment(Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i9, Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        try {
            if (i9 == -1) {
                C6226a.m23792r(this, intent, -1, bundle);
            } else {
                checkForValidRequestCode(i9);
                C6226a.m23792r(this, intent, ((allocateRequestIndex(fragment) + 1) << 16) + (i9 & 65535), bundle);
            }
        } finally {
            this.mStartedActivityFromFragment = false;
        }
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i9, Bundle bundle) {
        if (!this.mStartedActivityFromFragment && i9 != -1) {
            checkForValidRequestCode(i9);
        }
        super.startActivityForResult(intent, i9, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i9 != -1) {
            checkForValidRequestCode(i9);
        }
        super.startIntentSenderForResult(intentSender, i9, intent, i10, i11, i12, bundle);
    }

    public FragmentActivity(int i9) {
        super(i9);
        this.mFragments = C0365d.m1812b(new C0358a());
        this.mFragmentLifecycleRegistry = new C0386g(this);
        this.mStopped = true;
    }
}
