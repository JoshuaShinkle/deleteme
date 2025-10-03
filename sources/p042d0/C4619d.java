package p042d0;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* renamed from: d0.d */
/* loaded from: classes.dex */
public final class C4619d {

    /* renamed from: a */
    public final a f16232a;

    /* renamed from: d0.d$a */
    public interface a {
        /* renamed from: a */
        boolean mo18407a(MotionEvent motionEvent);
    }

    /* renamed from: d0.d$b */
    public static class b implements a {

        /* renamed from: a */
        public final GestureDetector f16233a;

        public b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.f16233a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // p042d0.C4619d.a
        /* renamed from: a */
        public boolean mo18407a(MotionEvent motionEvent) {
            return this.f16233a.onTouchEvent(motionEvent);
        }
    }

    public C4619d(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    /* renamed from: a */
    public boolean m18406a(MotionEvent motionEvent) {
        return this.f16232a.mo18407a(motionEvent);
    }

    public C4619d(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        this.f16232a = new b(context, onGestureListener, handler);
    }
}
