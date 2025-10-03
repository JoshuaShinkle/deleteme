package p164p2;

import android.content.Context;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: p2.e */
/* loaded from: classes.dex */
public abstract class AbstractViewOnTouchListenerC5918e implements View.OnTouchListener {

    /* renamed from: b */
    public final float f20404b;

    /* renamed from: c */
    public final float f20405c = 150.0f;

    /* renamed from: d */
    public float f20406d;

    /* renamed from: e */
    public float f20407e;

    /* renamed from: f */
    public float f20408f;

    /* renamed from: g */
    public float f20409g;

    /* renamed from: h */
    public float f20410h;

    /* renamed from: i */
    public float f20411i;

    /* renamed from: j */
    public long f20412j;

    public AbstractViewOnTouchListenerC5918e(Context context) {
        this.f20404b = TypedValue.applyDimension(1, 16.0f, context.getResources().getDisplayMetrics());
    }

    /* renamed from: a */
    public abstract boolean mo7002a(View view);

    /* renamed from: b */
    public final boolean m23378b() {
        return Math.abs(this.f20406d - this.f20410h) < this.f20404b && Math.abs(this.f20407e - this.f20411i) < this.f20404b && ((float) (System.currentTimeMillis() - this.f20412j)) < 150.0f;
    }

    /* renamed from: c */
    public abstract void mo7003c(View view, boolean z8);

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!mo7002a(view)) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f20406d = motionEvent.getRawX();
            this.f20407e = motionEvent.getRawY();
            this.f20408f = view.getX() - this.f20406d;
            this.f20409g = view.getY() - this.f20407e;
            this.f20412j = System.currentTimeMillis();
        } else if (actionMasked == 1) {
            this.f20410h = motionEvent.getRawX();
            this.f20411i = motionEvent.getRawY();
            mo7003c(view, m23378b());
        } else {
            if (actionMasked != 2) {
                return false;
            }
            float rawX = motionEvent.getRawX() + this.f20408f;
            float rawY = motionEvent.getRawY() + this.f20409g;
            view.setX(rawX);
            view.setY(rawY);
        }
        return true;
    }
}
