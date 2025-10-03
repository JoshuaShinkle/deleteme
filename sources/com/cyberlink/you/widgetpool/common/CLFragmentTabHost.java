package com.cyberlink.you.widgetpool.common;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import androidx.fragment.app.AbstractC0368g;
import androidx.fragment.app.AbstractC0372k;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CLFragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {

    /* renamed from: b */
    public final ArrayList<C3238c> f15057b;

    /* renamed from: c */
    public FrameLayout f15058c;

    /* renamed from: d */
    public Context f15059d;

    /* renamed from: e */
    public AbstractC0368g f15060e;

    /* renamed from: f */
    public int f15061f;

    /* renamed from: g */
    public TabHost.OnTabChangeListener f15062g;

    /* renamed from: h */
    public C3238c f15063h;

    /* renamed from: i */
    public boolean f15064i;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C3235a();

        /* renamed from: b */
        public String f15065b;

        /* renamed from: com.cyberlink.you.widgetpool.common.CLFragmentTabHost$SavedState$a */
        public class C3235a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.f15065b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeString(this.f15065b);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f15065b = parcel.readString();
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.common.CLFragmentTabHost$b */
    public static class C3237b implements TabHost.TabContentFactory {

        /* renamed from: a */
        public final Context f15066a;

        public C3237b(Context context) {
            this.f15066a = context;
        }

        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            View view = new View(this.f15066a);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    /* renamed from: com.cyberlink.you.widgetpool.common.CLFragmentTabHost$c */
    public static final class C3238c {

        /* renamed from: a */
        public final String f15067a;

        /* renamed from: b */
        public final Class<?> f15068b;

        /* renamed from: c */
        public final Bundle f15069c;

        /* renamed from: d */
        public Fragment f15070d;

        public C3238c(String str, Class<?> cls, Bundle bundle) {
            this.f15067a = str;
            this.f15068b = cls;
            this.f15069c = bundle;
        }
    }

    public CLFragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f15057b = new ArrayList<>();
        m17292f(context, attributeSet);
    }

    /* renamed from: a */
    public void m17287a(TabHost.TabSpec tabSpec, Class<?> cls, Bundle bundle) {
        tabSpec.setContent(new C3237b(this.f15059d));
        String tag = tabSpec.getTag();
        C3238c c3238c = new C3238c(tag, cls, bundle);
        if (this.f15064i) {
            c3238c.f15070d = this.f15060e.mo1848e(tag);
            if (c3238c.f15070d != null && !c3238c.f15070d.isDetached()) {
                AbstractC0372k abstractC0372kMo1844a = this.f15060e.mo1844a();
                abstractC0372kMo1844a.mo1797k(c3238c.f15070d);
                abstractC0372kMo1844a.mo1794h();
            }
        }
        this.f15057b.add(c3238c);
        addTab(tabSpec);
    }

    /* renamed from: b */
    public final AbstractC0372k m17288b(String str, AbstractC0372k abstractC0372k) {
        C3238c c3238cM17291e = m17291e(str);
        if (this.f15063h != c3238cM17291e) {
            if (abstractC0372k == null) {
                abstractC0372k = this.f15060e.mo1844a();
            }
            C3238c c3238c = this.f15063h;
            if (c3238c != null && c3238c.f15070d != null) {
                abstractC0372k.mo1797k(this.f15063h.f15070d);
            }
            if (c3238cM17291e != null) {
                if (c3238cM17291e.f15070d == null) {
                    c3238cM17291e.f15070d = Fragment.instantiate(this.f15059d, c3238cM17291e.f15068b.getName(), c3238cM17291e.f15069c);
                    abstractC0372k.m1981c(this.f15061f, c3238cM17291e.f15070d, c3238cM17291e.f15067a);
                } else {
                    abstractC0372k.m1984f(c3238cM17291e.f15070d);
                }
            }
            this.f15063h = c3238cM17291e;
        }
        return abstractC0372k;
    }

    /* renamed from: c */
    public final void m17289c() {
        if (this.f15058c == null) {
            FrameLayout frameLayout = (FrameLayout) findViewById(this.f15061f);
            this.f15058c = frameLayout;
            if (frameLayout != null) {
                return;
            }
            throw new IllegalStateException("No tab content FrameLayout found for id " + this.f15061f);
        }
    }

    /* renamed from: d */
    public final void m17290d(Context context) {
        if (findViewById(R.id.tabs) == null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(R.id.tabs);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, BitmapDescriptorFactory.HUE_RED));
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(R.id.tabcontent);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(0, 0, BitmapDescriptorFactory.HUE_RED));
            FrameLayout frameLayout2 = new FrameLayout(context);
            this.f15058c = frameLayout2;
            frameLayout2.setId(this.f15061f);
            linearLayout.addView(frameLayout2, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    /* renamed from: e */
    public final C3238c m17291e(String str) {
        int size = this.f15057b.size();
        for (int i9 = 0; i9 < size; i9++) {
            C3238c c3238c = this.f15057b.get(i9);
            if (c3238c.f15067a.equals(str)) {
                return c3238c;
            }
        }
        return null;
    }

    /* renamed from: f */
    public final void m17292f(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.f15061f = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    /* renamed from: g */
    public void m17293g(Context context, AbstractC0368g abstractC0368g, int i9) {
        m17290d(context);
        super.setup();
        this.f15059d = context;
        this.f15060e = abstractC0368g;
        this.f15061f = i9;
        m17289c();
        this.f15058c.setId(i9);
        if (getId() == -1) {
            setId(R.id.tabhost);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.f15057b.size();
        AbstractC0372k abstractC0372kMo1844a = null;
        for (int i9 = 0; i9 < size; i9++) {
            C3238c c3238c = this.f15057b.get(i9);
            c3238c.f15070d = this.f15060e.mo1848e(c3238c.f15067a);
            if (c3238c.f15070d != null && !c3238c.f15070d.isDetached()) {
                if (c3238c.f15067a.equals(currentTabTag)) {
                    this.f15063h = c3238c;
                } else {
                    if (abstractC0372kMo1844a == null) {
                        abstractC0372kMo1844a = this.f15060e.mo1844a();
                    }
                    abstractC0372kMo1844a.mo1797k(c3238c.f15070d);
                }
            }
        }
        this.f15064i = true;
        AbstractC0372k abstractC0372kM17288b = m17288b(currentTabTag, abstractC0372kMo1844a);
        if (abstractC0372kM17288b != null) {
            try {
                abstractC0372kM17288b.mo1794h();
            } catch (IllegalStateException e9) {
                e9.printStackTrace();
            }
            this.f15060e.mo1846c();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f15064i = false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.f15065b);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f15065b = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    public void onTabChanged(String str) {
        AbstractC0372k abstractC0372kM17288b;
        if (isInEditMode()) {
            return;
        }
        if (this.f15064i && (abstractC0372kM17288b = m17288b(str, null)) != null) {
            abstractC0372kM17288b.mo1794h();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f15062g;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    public void setOnTabChangedListener(TabHost.OnTabChangeListener onTabChangeListener) {
        this.f15062g = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }
}
