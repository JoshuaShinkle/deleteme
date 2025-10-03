package com.google.android.exoplayer2.source.chunk;

import android.util.Log;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.HttpDataSource;

/* loaded from: classes.dex */
public final class ChunkedTrackBlacklistUtil {
    public static final long DEFAULT_TRACK_BLACKLIST_MS = 60000;
    private static final String TAG = "ChunkedTrackBlacklist";

    private ChunkedTrackBlacklistUtil() {
    }

    public static boolean maybeBlacklistTrack(TrackSelection trackSelection, int i9, Exception exc) {
        return maybeBlacklistTrack(trackSelection, i9, exc, 60000L);
    }

    public static boolean shouldBlacklist(Exception exc) {
        if (!(exc instanceof HttpDataSource.InvalidResponseCodeException)) {
            return false;
        }
        int i9 = ((HttpDataSource.InvalidResponseCodeException) exc).responseCode;
        return i9 == 404 || i9 == 410;
    }

    public static boolean maybeBlacklistTrack(TrackSelection trackSelection, int i9, Exception exc, long j9) {
        if (!shouldBlacklist(exc)) {
            return false;
        }
        boolean zBlacklist = trackSelection.blacklist(i9, j9);
        int i10 = ((HttpDataSource.InvalidResponseCodeException) exc).responseCode;
        if (zBlacklist) {
            Log.w(TAG, "Blacklisted: duration=" + j9 + ", responseCode=" + i10 + ", format=" + trackSelection.getFormat(i9));
        } else {
            Log.w(TAG, "Blacklisting failed (cannot blacklist last enabled track): responseCode=" + i10 + ", format=" + trackSelection.getFormat(i9));
        }
        return zBlacklist;
    }
}
