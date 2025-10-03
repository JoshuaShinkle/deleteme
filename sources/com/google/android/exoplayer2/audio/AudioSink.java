package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.PlaybackParameters;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public interface AudioSink {
    public static final long CURRENT_POSITION_NOT_SET = Long.MIN_VALUE;

    public static final class ConfigurationException extends Exception {
        public ConfigurationException(Throwable th) {
            super(th);
        }

        public ConfigurationException(String str) {
            super(str);
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;

        public InitializationException(int i9, int i10, int i11, int i12) {
            super("AudioTrack init failed: " + i9 + ", Config(" + i10 + ", " + i11 + ", " + i12 + ")");
            this.audioTrackState = i9;
        }
    }

    public interface Listener {
        void onAudioSessionId(int i9);

        void onPositionDiscontinuity();

        void onUnderrun(int i9, long j9, long j10);
    }

    public static final class WriteException extends Exception {
        public final int errorCode;

        public WriteException(int i9) {
            super("AudioTrack write failed: " + i9);
            this.errorCode = i9;
        }
    }

    void configure(int i9, int i10, int i11, int i12, int[] iArr, int i13, int i14);

    void disableTunneling();

    void enableTunnelingV21(int i9);

    long getCurrentPositionUs(boolean z8);

    PlaybackParameters getPlaybackParameters();

    boolean handleBuffer(ByteBuffer byteBuffer, long j9);

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEncodingSupported(int i9);

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream();

    void release();

    void reset();

    void setAudioAttributes(AudioAttributes audioAttributes);

    void setAudioSessionId(int i9);

    void setListener(Listener listener);

    PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters);

    void setVolume(float f9);
}
