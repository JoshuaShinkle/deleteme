package p092i0;

import android.view.animation.Interpolator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* renamed from: i0.d */
/* loaded from: classes.dex */
public abstract class AbstractInterpolatorC5041d implements Interpolator {

    /* renamed from: a */
    public final float[] f17407a;

    /* renamed from: b */
    public final float f17408b;

    public AbstractInterpolatorC5041d(float[] fArr) {
        this.f17407a = fArr;
        this.f17408b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f9) {
        if (f9 >= 1.0f) {
            return 1.0f;
        }
        if (f9 <= BitmapDescriptorFactory.HUE_RED) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float[] fArr = this.f17407a;
        int iMin = Math.min((int) ((fArr.length - 1) * f9), fArr.length - 2);
        float f10 = this.f17408b;
        float f11 = (f9 - (iMin * f10)) / f10;
        float[] fArr2 = this.f17407a;
        float f12 = fArr2[iMin];
        return f12 + (f11 * (fArr2[iMin + 1] - f12));
    }
}
