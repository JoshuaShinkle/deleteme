package com.cyberlink.you.sticker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes.dex */
public class StickerViewPager extends ViewPager {

    /* renamed from: k0 */
    public boolean f14348k0;

    public StickerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14348k0 = true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f14348k0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f14348k0) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setPagingEnabled(boolean z8) {
        this.f14348k0 = z8;
    }
}
