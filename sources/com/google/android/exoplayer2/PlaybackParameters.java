package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* loaded from: classes.dex */
public final class PlaybackParameters {
    public static final PlaybackParameters DEFAULT = new PlaybackParameters(1.0f, 1.0f);
    public final float pitch;
    private final int scaledUsPerMs;
    public final float speed;

    public PlaybackParameters(float f9, float f10) {
        Assertions.checkArgument(f9 > BitmapDescriptorFactory.HUE_RED);
        Assertions.checkArgument(f10 > BitmapDescriptorFactory.HUE_RED);
        this.speed = f9;
        this.pitch = f10;
        this.scaledUsPerMs = Math.round(f9 * 1000.0f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlaybackParameters.class != obj.getClass()) {
            return false;
        }
        PlaybackParameters playbackParameters = (PlaybackParameters) obj;
        return this.speed == playbackParameters.speed && this.pitch == playbackParameters.pitch;
    }

    public long getMediaTimeUsForPlayoutTimeMs(long j9) {
        return j9 * this.scaledUsPerMs;
    }

    public int hashCode() {
        return ((527 + Float.floatToRawIntBits(this.speed)) * 31) + Float.floatToRawIntBits(this.pitch);
    }
}
