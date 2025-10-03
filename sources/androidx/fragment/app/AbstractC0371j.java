package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import p189s0.AbstractC6243a;

/* renamed from: androidx.fragment.app.j */
/* loaded from: classes.dex */
public abstract class AbstractC0371j extends AbstractC6243a {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapt";
    private final int mBehavior;
    private AbstractC0372k mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final AbstractC0368g mFragmentManager;
    private ArrayList<Fragment> mFragments;
    private ArrayList<Fragment.SavedState> mSavedState;

    @Deprecated
    public AbstractC0371j(AbstractC0368g abstractC0368g) {
        this(abstractC0368g, 0);
    }

    @Override // p189s0.AbstractC6243a
    public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.mo1844a();
        }
        while (this.mSavedState.size() <= i9) {
            this.mSavedState.add(null);
        }
        this.mSavedState.set(i9, fragment.isAdded() ? this.mFragmentManager.mo1856m(fragment) : null);
        this.mFragments.set(i9, null);
        this.mCurTransaction.mo1800o(fragment);
        if (fragment == this.mCurrentPrimaryItem) {
            this.mCurrentPrimaryItem = null;
        }
    }

    @Override // p189s0.AbstractC6243a
    public void finishUpdate(ViewGroup viewGroup) {
        AbstractC0372k abstractC0372k = this.mCurTransaction;
        if (abstractC0372k != null) {
            abstractC0372k.mo1796j();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int i9);

    @Override // p189s0.AbstractC6243a
    public Object instantiateItem(ViewGroup viewGroup, int i9) {
        Fragment.SavedState savedState;
        Fragment fragment;
        if (this.mFragments.size() > i9 && (fragment = this.mFragments.get(i9)) != null) {
            return fragment;
        }
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.mo1844a();
        }
        Fragment item = getItem(i9);
        if (this.mSavedState.size() > i9 && (savedState = this.mSavedState.get(i9)) != null) {
            item.setInitialSavedState(savedState);
        }
        while (this.mFragments.size() <= i9) {
            this.mFragments.add(null);
        }
        item.setMenuVisibility(false);
        if (this.mBehavior == 0) {
            item.setUserVisibleHint(false);
        }
        this.mFragments.set(i9, item);
        this.mCurTransaction.m1980b(viewGroup.getId(), item);
        if (this.mBehavior == 1) {
            this.mCurTransaction.mo1801q(item, Lifecycle.State.STARTED);
        }
        return item;
    }

    @Override // p189s0.AbstractC6243a
    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // p189s0.AbstractC6243a
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) throws NumberFormatException {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.mSavedState.clear();
            this.mFragments.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.mSavedState.add((Fragment.SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int i9 = Integer.parseInt(str.substring(1));
                    Fragment fragmentMo1850g = this.mFragmentManager.mo1850g(bundle, str);
                    if (fragmentMo1850g != null) {
                        while (this.mFragments.size() <= i9) {
                            this.mFragments.add(null);
                        }
                        fragmentMo1850g.setMenuVisibility(false);
                        this.mFragments.set(i9, fragmentMo1850g);
                    } else {
                        Log.w(TAG, "Bad fragment at key " + str);
                    }
                }
            }
        }
    }

    @Override // p189s0.AbstractC6243a
    public Parcelable saveState() {
        Bundle bundle;
        if (this.mSavedState.size() > 0) {
            bundle = new Bundle();
            Fragment.SavedState[] savedStateArr = new Fragment.SavedState[this.mSavedState.size()];
            this.mSavedState.toArray(savedStateArr);
            bundle.putParcelableArray("states", savedStateArr);
        } else {
            bundle = null;
        }
        for (int i9 = 0; i9 < this.mFragments.size(); i9++) {
            Fragment fragment = this.mFragments.get(i9);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.mFragmentManager.mo1855l(bundle, "f" + i9, fragment);
            }
        }
        return bundle;
    }

    @Override // p189s0.AbstractC6243a
    public void setPrimaryItem(ViewGroup viewGroup, int i9, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                if (this.mBehavior == 1) {
                    if (this.mCurTransaction == null) {
                        this.mCurTransaction = this.mFragmentManager.mo1844a();
                    }
                    this.mCurTransaction.mo1801q(this.mCurrentPrimaryItem, Lifecycle.State.STARTED);
                } else {
                    this.mCurrentPrimaryItem.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.mBehavior == 1) {
                if (this.mCurTransaction == null) {
                    this.mCurTransaction = this.mFragmentManager.mo1844a();
                }
                this.mCurTransaction.mo1801q(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // p189s0.AbstractC6243a
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public AbstractC0371j(AbstractC0368g abstractC0368g, int i9) {
        this.mCurTransaction = null;
        this.mSavedState = new ArrayList<>();
        this.mFragments = new ArrayList<>();
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = abstractC0368g;
        this.mBehavior = i9;
    }
}
