package p174q3;

import android.graphics.Color;
import com.cyberlink.p030U.R;
import com.cyberlink.you.fingerpaint.kernel.FingerPaintObject;

/* renamed from: q3.i */
/* loaded from: classes.dex */
public class C6152i {

    /* renamed from: a */
    public static final int[] f20778a = {R.drawable.doodle_color_01, R.drawable.doodle_color_02, R.drawable.doodle_color_03, R.drawable.doodle_color_04, R.drawable.doodle_color_05, R.drawable.doodle_color_06, R.drawable.doodle_color_07, R.drawable.doodle_color_08, R.drawable.doodle_color_09, R.drawable.doodle_color_10};

    /* renamed from: b */
    public static final int[] f20779b = {R.drawable.doodle_brush_color_01, R.drawable.doodle_brush_color_02, R.drawable.doodle_brush_color_03, R.drawable.doodle_brush_color_04, R.drawable.doodle_brush_color_05, R.drawable.doodle_brush_color_06, R.drawable.doodle_brush_color_07, R.drawable.doodle_brush_color_08, R.drawable.doodle_brush_color_09, R.drawable.doodle_brush_color_10};

    /* renamed from: c */
    public static final int[] f20780c = {R.drawable.doodle_brush_color_thumnail_01, R.drawable.doodle_brush_color_thumnail_02, R.drawable.doodle_brush_color_thumnail_03, R.drawable.doodle_brush_color_thumnail_04, R.drawable.doodle_brush_color_thumnail_05, R.drawable.doodle_brush_color_thumnail_06, R.drawable.doodle_brush_color_thumnail_07, R.drawable.doodle_brush_color_thumnail_08, R.drawable.doodle_brush_color_thumnail_09, R.drawable.doodle_brush_color_thumnail_10};

    /* renamed from: a */
    public static int m23609a(int i9, boolean z8) {
        int[] iArr = z8 ? f20780c : f20779b;
        if (i9 < iArr.length) {
            return iArr[i9];
        }
        return 0;
    }

    /* renamed from: b */
    public static int m23610b(String str, boolean z8) {
        try {
            return m23609a(m23611c(Color.parseColor(str)), z8);
        } catch (Exception e9) {
            e9.printStackTrace();
            return 0;
        }
    }

    /* renamed from: c */
    public static final int m23611c(int i9) {
        int i10 = 0;
        while (true) {
            int[] iArr = FingerPaintObject.f13556b;
            if (i10 >= iArr.length) {
                return 4;
            }
            if (iArr[i10] == i9) {
                return i10;
            }
            i10++;
        }
    }
}
