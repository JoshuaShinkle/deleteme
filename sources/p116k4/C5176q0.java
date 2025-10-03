package p116k4;

import android.util.Log;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import org.apache.commons.lang3.time.DateUtils;

/* renamed from: k4.q0 */
/* loaded from: classes.dex */
public final class C5176q0 {
    /* renamed from: a */
    public static String m20222a(long j9) {
        Log.d("TimeIntervalUtility", "[getConnectTimeInterval] duration= " + j9);
        return j9 < 1000 ? "<1s" : j9 < 3000 ? "1-3s" : j9 < DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS ? "3-5s" : j9 < 10000 ? "5-10s" : j9 < 15000 ? "10-15s" : j9 < SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS ? "15-30s" : j9 < 60000 ? "30s-1m" : j9 < 300000 ? "1m-5m" : j9 < 600000 ? "5m-10m" : j9 < 1800000 ? "10m-30m" : j9 < DateUtils.MILLIS_PER_HOUR ? "30m-1hr" : ">1hr";
    }

    /* renamed from: b */
    public static String m20223b(long j9) {
        Log.d("TimeIntervalUtility", " [getConnectTimeInterval] duration= " + j9);
        return m20226e(j9);
    }

    /* renamed from: c */
    public static String m20224c(long j9) {
        Log.d("TimeIntervalUtility", " [getEnterChatRoomTimeInterval] duration= " + j9);
        return m20226e(j9);
    }

    /* renamed from: d */
    public static String m20225d(long j9) {
        Log.d("TimeIntervalUtility", "[getSendRoundTripTimeInterval] duration= " + j9);
        return j9 < 500 ? "<0.5s" : j9 < 1000 ? "0.5-1s" : j9 < 3000 ? "1-3s" : j9 < DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS ? "3-5s" : j9 < 10000 ? "5-10s" : j9 < 15000 ? "10-15s" : j9 < SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS ? "15-30s" : j9 < 60000 ? "30s-1m" : j9 < 300000 ? "1m-5m" : j9 < 600000 ? "5m-10m" : j9 < 1800000 ? "10m-30m" : ">30m";
    }

    /* renamed from: e */
    public static String m20226e(long j9) {
        return j9 < 500 ? "<0.5s" : j9 < 1000 ? "0.5-1s" : j9 < AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS ? "1s-2s" : j9 < 3000 ? "2s-3s" : j9 < DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS ? "3-5s" : j9 < 10000 ? "5-10s" : j9 < 15000 ? "10-15s" : j9 < SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS ? "15-30s" : j9 < 60000 ? "30s-1m" : j9 < 300000 ? "1m-5m" : j9 < 600000 ? "5m-10m" : j9 < 1800000 ? "10m-30m" : j9 < DateUtils.MILLIS_PER_HOUR ? "30m-1hr" : ">=1hr";
    }
}
