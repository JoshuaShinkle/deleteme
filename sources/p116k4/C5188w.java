package p116k4;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import net.sqlcipher.database.SQLiteDatabase;

/* renamed from: k4.w */
/* loaded from: classes.dex */
public class C5188w {

    /* renamed from: a */
    public static final int[] f17765a = {2000, 2105, 2222, 2353, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 2667, 2857, 3078, 3333, 3636, 4000, 4444, 5000, 5714, 6667, 8000, 10000, 11111, GoogleSignInStatusCodes.SIGN_IN_FAILED, 14286, 16667, 20000, 25000, 33333, SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH};

    /* renamed from: b */
    public static final int[] f17766b = {2000, 2105, 2222, 2353, DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS, 2667, 2857, 3078, 3333, 3636, 4000, 4444, 5000, 5714, 6667, 8000, 10000, 11111, GoogleSignInStatusCodes.SIGN_IN_FAILED, 14286, 16667, 20000, 25000, 33333, SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH};

    /* renamed from: c */
    public static final int[] f17767c = {-100, 100};

    /* renamed from: d */
    public static final int[] f17768d = {2000, SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH};

    /* renamed from: a */
    public static float m20274a(float[] fArr, float[] fArr2, float[] fArr3, int i9, int i10) {
        int i11 = 0;
        while (i11 < i9 && fArr[i11] <= i10) {
            i11++;
        }
        if (i11 == i9) {
            return fArr2[i9 - 1];
        }
        if (i11 == 0) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f9 = fArr2[i11];
        int i12 = i11 - 1;
        float f10 = fArr2[i12];
        float f11 = fArr[i11];
        float f12 = fArr[i12];
        float f13 = (f9 - f10) / (f11 - f12);
        float f14 = (i10 - f12) / (f11 - f12);
        if (f9 - f10 == BitmapDescriptorFactory.HUE_RED) {
            return f10;
        }
        float f15 = 1.0f - f14;
        float f16 = fArr3[i12];
        return (((((f13 * f14) * f14) + ((f14 * f15) * f16)) / (f13 + ((((fArr3[i11] + f16) - (2.0f * f13)) * f14) * f15))) * (f9 - f10)) + f10;
    }
}
