package org.apache.commons.lang3;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Random;

/* loaded from: classes.dex */
public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static byte[] nextBytes(int i9) {
        Validate.isTrue(i9 >= 0, "Count cannot be negative.", new Object[0]);
        byte[] bArr = new byte[i9];
        RANDOM.nextBytes(bArr);
        return bArr;
    }

    public static double nextDouble(double d9, double d10) {
        Validate.isTrue(d10 >= d9, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(d9 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, "Both range values must be non-negative.", new Object[0]);
        return d9 == d10 ? d9 : d9 + ((d10 - d9) * RANDOM.nextDouble());
    }

    public static float nextFloat(float f9, float f10) {
        Validate.isTrue(f10 >= f9, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(f9 >= BitmapDescriptorFactory.HUE_RED, "Both range values must be non-negative.", new Object[0]);
        return f9 == f10 ? f9 : f9 + ((f10 - f9) * RANDOM.nextFloat());
    }

    public static int nextInt(int i9, int i10) {
        Validate.isTrue(i10 >= i9, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(i9 >= 0, "Both range values must be non-negative.", new Object[0]);
        return i9 == i10 ? i9 : i9 + RANDOM.nextInt(i10 - i9);
    }

    public static long nextLong(long j9, long j10) {
        Validate.isTrue(j10 >= j9, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(j9 >= 0, "Both range values must be non-negative.", new Object[0]);
        return j9 == j10 ? j9 : (long) nextDouble(j9, j10);
    }
}
