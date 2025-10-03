package p103j1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.concurrent.locks.Lock;
import p012b1.InterfaceC0595j;
import p022c1.C0708e;
import p022c1.InterfaceC0707d;

/* renamed from: j1.k */
/* loaded from: classes.dex */
public final class C5078k {

    /* renamed from: a */
    public static final InterfaceC0707d f17511a = new a();

    /* renamed from: j1.k$a */
    public class a extends C0708e {
        @Override // p022c1.C0708e, p022c1.InterfaceC0707d
        /* renamed from: c */
        public void mo3487c(Bitmap bitmap) {
        }
    }

    /* renamed from: a */
    public static InterfaceC0595j<Bitmap> m19880a(InterfaceC0707d interfaceC0707d, Drawable drawable, int i9, int i10) {
        Bitmap bitmapM19881b;
        Drawable current = drawable.getCurrent();
        boolean z8 = false;
        if (current instanceof BitmapDrawable) {
            bitmapM19881b = ((BitmapDrawable) current).getBitmap();
        } else if (current instanceof Animatable) {
            bitmapM19881b = null;
        } else {
            bitmapM19881b = m19881b(interfaceC0707d, current, i9, i10);
            z8 = true;
        }
        if (!z8) {
            interfaceC0707d = f17511a;
        }
        return C5071d.m19858f(bitmapM19881b, interfaceC0707d);
    }

    /* renamed from: b */
    public static Bitmap m19881b(InterfaceC0707d interfaceC0707d, Drawable drawable, int i9, int i10) {
        if (i9 == Integer.MIN_VALUE && drawable.getIntrinsicWidth() <= 0) {
            if (Log.isLoggable("DrawableToBitmap", 5)) {
                Log.w("DrawableToBitmap", "Unable to draw " + drawable + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic width");
            }
            return null;
        }
        if (i10 == Integer.MIN_VALUE && drawable.getIntrinsicHeight() <= 0) {
            if (Log.isLoggable("DrawableToBitmap", 5)) {
                Log.w("DrawableToBitmap", "Unable to draw " + drawable + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic height");
            }
            return null;
        }
        if (drawable.getIntrinsicWidth() > 0) {
            i9 = drawable.getIntrinsicWidth();
        }
        if (drawable.getIntrinsicHeight() > 0) {
            i10 = drawable.getIntrinsicHeight();
        }
        Lock lockM19899i = C5086s.m19899i();
        lockM19899i.lock();
        Bitmap bitmapMo3488d = interfaceC0707d.mo3488d(i9, i10, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapMo3488d);
            drawable.setBounds(0, 0, i9, i10);
            drawable.draw(canvas);
            canvas.setBitmap(null);
            return bitmapMo3488d;
        } finally {
            lockM19899i.unlock();
        }
    }
}
