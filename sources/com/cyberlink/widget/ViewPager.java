package com.cyberlink.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes.dex */
public class ViewPager extends androidx.viewpager.widget.ViewPager {

    /* renamed from: k0 */
    public boolean f7279k0;

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7279k0 = true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f7279k0 && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f7279k0 && super.onTouchEvent(motionEvent);
    }

    public void setSwipeable(boolean z8) {
        this.f7279k0 = z8;
    }
}
