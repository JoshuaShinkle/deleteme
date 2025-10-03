package com.cyberlink.you.pages.photoimport.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.you.pages.photoimport.view.tiv.TouchImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
public class TouchViewPager extends ViewPager {

    /* renamed from: k0 */
    public TouchImageView f14275k0;

    /* renamed from: l0 */
    public boolean f14276l0;

    /* renamed from: m0 */
    public boolean f14277m0;

    /* renamed from: n0 */
    public InterfaceC3108b f14278n0;

    /* renamed from: o0 */
    public GestureDetector f14279o0;

    /* renamed from: p0 */
    public GestureDetector.OnGestureListener f14280p0;

    /* renamed from: q0 */
    public boolean f14281q0;

    /* renamed from: r0 */
    public PointF f14282r0;

    /* renamed from: com.cyberlink.you.pages.photoimport.view.TouchViewPager$a */
    public class GestureDetectorOnGestureListenerC3107a implements GestureDetector.OnGestureListener {

        /* renamed from: b */
        public final float f14283b = -300.0f;

        /* renamed from: c */
        public final float f14284c = 300.0f;

        public GestureDetectorOnGestureListenerC3107a() {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            TouchImageView touchImageView = TouchViewPager.this.f14275k0;
            if (touchImageView == null || touchImageView.m16269n() || TouchViewPager.this.f14278n0 == null) {
                return false;
            }
            if (f10 >= -300.0f && f10 <= 300.0f) {
                return false;
            }
            TouchViewPager.this.f14278n0.mo12492a();
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.pages.photoimport.view.TouchViewPager$b */
    public interface InterfaceC3108b {
        /* renamed from: a */
        void mo12492a();
    }

    public TouchViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f14276l0 = true;
        this.f14277m0 = true;
        this.f14280p0 = new GestureDetectorOnGestureListenerC3107a();
        this.f14281q0 = false;
        this.f14279o0 = new GestureDetector(context, this.f14280p0);
    }

    /* renamed from: S */
    public final float[] m16255S(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.f14282r0 = new PointF(motionEvent.getX(0), motionEvent.getY(0));
            return null;
        }
        if (action != 1 && action != 2) {
            return null;
        }
        PointF pointF = new PointF(motionEvent.getX(0), motionEvent.getY(0));
        float f9 = pointF.x;
        PointF pointF2 = this.f14282r0;
        return new float[]{f9 - pointF2.x, pointF.y - pointF2.y};
    }

    /* renamed from: T */
    public final boolean m16256T(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (IllegalArgumentException e9) {
            e9.printStackTrace();
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1) {
            m16256T(motionEvent);
        }
        if ((motionEvent.getAction() & 255) == 0) {
            this.f14281q0 = false;
        }
        if (this.f14275k0 == null) {
            return false;
        }
        float[] fArrM16255S = m16255S(motionEvent);
        if (this.f14275k0.m16272q()) {
            try {
                return super.onInterceptTouchEvent(motionEvent);
            } catch (IllegalArgumentException unused) {
                Log.e("TouchViewPager", "IllegalArgmentException: pointerIndex out of range");
                return false;
            }
        }
        if (fArrM16255S != null && this.f14275k0.f14299N && fArrM16255S[0] <= BitmapDescriptorFactory.HUE_RED && !this.f14281q0 && this.f14276l0) {
            return m16256T(motionEvent);
        }
        if (fArrM16255S != null && this.f14275k0.f14297L && fArrM16255S[0] >= BitmapDescriptorFactory.HUE_RED && !this.f14281q0 && this.f14276l0) {
            return m16256T(motionEvent);
        }
        if (fArrM16255S == null) {
            TouchImageView touchImageView = this.f14275k0;
            if ((touchImageView.f14297L || touchImageView.f14299N) && !this.f14281q0 && this.f14276l0) {
                return m16256T(motionEvent);
            }
        }
        this.f14281q0 = true;
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) throws Resources.NotFoundException {
        if ((motionEvent.getAction() & 255) == 1) {
            super.onTouchEvent(motionEvent);
        }
        if (this.f14275k0 == null) {
            return false;
        }
        float[] fArrM16255S = m16255S(motionEvent);
        if (this.f14275k0.m16272q()) {
            return super.onTouchEvent(motionEvent);
        }
        if (fArrM16255S != null && this.f14275k0.f14299N && fArrM16255S[0] < BitmapDescriptorFactory.HUE_RED) {
            return super.onTouchEvent(motionEvent);
        }
        if (fArrM16255S != null && this.f14275k0.f14297L && fArrM16255S[0] > BitmapDescriptorFactory.HUE_RED) {
            return super.onTouchEvent(motionEvent);
        }
        if (fArrM16255S == null) {
            TouchImageView touchImageView = this.f14275k0;
            if (touchImageView.f14297L || touchImageView.f14299N) {
                return super.onTouchEvent(motionEvent);
            }
        }
        return false;
    }

    public void setPagingEnabled(boolean z8) {
        this.f14276l0 = z8;
    }

    public void setSwipeOutListener(InterfaceC3108b interfaceC3108b) {
        this.f14278n0 = interfaceC3108b;
    }
}
