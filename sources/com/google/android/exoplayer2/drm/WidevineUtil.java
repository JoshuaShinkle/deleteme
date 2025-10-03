package com.google.android.exoplayer2.drm;

import android.util.Pair;
import com.google.android.exoplayer2.C3322C;
import java.util.Map;

/* loaded from: classes.dex */
public final class WidevineUtil {
    public static final String PROPERTY_LICENSE_DURATION_REMAINING = "LicenseDurationRemaining";
    public static final String PROPERTY_PLAYBACK_DURATION_REMAINING = "PlaybackDurationRemaining";

    private WidevineUtil() {
    }

    private static long getDurationRemainingSec(Map<String, String> map, String str) {
        if (map == null) {
            return C3322C.TIME_UNSET;
        }
        try {
            String str2 = map.get(str);
            return str2 != null ? Long.parseLong(str2) : C3322C.TIME_UNSET;
        } catch (NumberFormatException unused) {
            return C3322C.TIME_UNSET;
        }
    }

    public static Pair<Long, Long> getLicenseDurationRemainingSec(DrmSession<?> drmSession) {
        Map<String, String> mapQueryKeyStatus = drmSession.queryKeyStatus();
        if (mapQueryKeyStatus == null) {
            return null;
        }
        return new Pair<>(Long.valueOf(getDurationRemainingSec(mapQueryKeyStatus, PROPERTY_LICENSE_DURATION_REMAINING)), Long.valueOf(getDurationRemainingSec(mapQueryKeyStatus, PROPERTY_PLAYBACK_DURATION_REMAINING)));
    }
}
