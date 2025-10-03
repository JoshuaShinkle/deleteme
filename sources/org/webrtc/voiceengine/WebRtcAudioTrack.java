package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import org.webrtc.Logging;

/* loaded from: classes3.dex */
public class WebRtcAudioTrack {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioTrack";
    private static volatile boolean speakerMute = false;
    private final AudioManager audioManager;
    private ByteBuffer byteBuffer;
    private final Context context;
    private byte[] emptyBytes;
    private final long nativeAudioTrack;
    private AudioTrack audioTrack = null;
    private AudioTrackThread audioThread = null;

    public class AudioTrackThread extends Thread {
        private volatile boolean keepAlive;

        public AudioTrackThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        @TargetApi(21)
        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i9) {
            return audioTrack.write(byteBuffer, i9, 0);
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i9) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i9);
        }

        public void joinThread() throws InterruptedException {
            this.keepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IllegalStateException, SecurityException, IllegalArgumentException {
            Process.setThreadPriority(-19);
            Logging.m23185d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            try {
                WebRtcAudioTrack.this.audioTrack.play();
                WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 3);
                int iCapacity = WebRtcAudioTrack.this.byteBuffer.capacity();
                while (this.keepAlive) {
                    try {
                        WebRtcAudioTrack webRtcAudioTrack = WebRtcAudioTrack.this;
                        webRtcAudioTrack.nativeGetPlayoutData(iCapacity, webRtcAudioTrack.nativeAudioTrack);
                        WebRtcAudioTrack.assertTrue(iCapacity <= WebRtcAudioTrack.this.byteBuffer.remaining());
                        if (WebRtcAudioTrack.speakerMute) {
                            WebRtcAudioTrack.this.byteBuffer.clear();
                            WebRtcAudioTrack.this.byteBuffer.put(WebRtcAudioTrack.this.emptyBytes);
                            WebRtcAudioTrack.this.byteBuffer.position(0);
                        }
                        int iWriteOnLollipop = WebRtcAudioUtils.runningOnLollipopOrHigher() ? writeOnLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, iCapacity) : writePreLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, iCapacity);
                        if (iWriteOnLollipop != iCapacity) {
                            Logging.m23186e(WebRtcAudioTrack.TAG, "AudioTrack.write failed: " + iWriteOnLollipop);
                            if (iWriteOnLollipop == -3) {
                                this.keepAlive = false;
                            }
                        }
                        WebRtcAudioTrack.this.byteBuffer.rewind();
                    } catch (UnsatisfiedLinkError unused) {
                        Logging.m23186e(WebRtcAudioTrack.TAG, "AudioTrack.nativeGetPlayoutData failed");
                        this.keepAlive = false;
                    }
                }
                try {
                    WebRtcAudioTrack.this.audioTrack.stop();
                } catch (IllegalStateException e9) {
                    Logging.m23186e(WebRtcAudioTrack.TAG, "AudioTrack.stop failed: " + e9.getMessage());
                }
                WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 1);
                WebRtcAudioTrack.this.audioTrack.flush();
            } catch (IllegalStateException e10) {
                Logging.m23186e(WebRtcAudioTrack.TAG, "AudioTrack.play failed: " + e10.getMessage());
                WebRtcAudioTrack.this.releaseAudioResources();
            }
        }
    }

    public WebRtcAudioTrack(Context context, long j9) {
        Logging.m23185d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioTrack = j9;
        this.audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private int channelCountToConfiguration(int i9) {
        return i9 == 1 ? 4 : 12;
    }

    private int getStreamMaxVolume() {
        Logging.m23185d(TAG, "getStreamMaxVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamMaxVolume(0);
    }

    private int getStreamVolume() {
        Logging.m23185d(TAG, "getStreamVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamVolume(0);
    }

    private boolean initPlayout(int i9, int i10) {
        Logging.m23185d(TAG, "initPlayout(sampleRate=" + i9 + ", channels=" + i10 + ")");
        this.byteBuffer = ByteBuffer.allocateDirect(i10 * 2 * (i9 / 100));
        StringBuilder sb = new StringBuilder();
        sb.append("byteBuffer.capacity: ");
        sb.append(this.byteBuffer.capacity());
        Logging.m23185d(TAG, sb.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioTrack);
        int iChannelCountToConfiguration = channelCountToConfiguration(i10);
        int minBufferSize = AudioTrack.getMinBufferSize(i9, iChannelCountToConfiguration, 2);
        Logging.m23185d(TAG, "AudioTrack.getMinBufferSize: " + minBufferSize);
        if (minBufferSize < this.byteBuffer.capacity()) {
            Logging.m23186e(TAG, "AudioTrack.getMinBufferSize returns an invalid value.");
            return false;
        }
        if (this.audioTrack != null) {
            Logging.m23186e(TAG, "Conflict with existing AudioTrack.");
            return false;
        }
        try {
            AudioTrack audioTrack = new AudioTrack(0, i9, iChannelCountToConfiguration, 2, minBufferSize, 1);
            this.audioTrack = audioTrack;
            if (audioTrack.getState() != 1) {
                Logging.m23186e(TAG, "Initialization of audio track failed.");
                releaseAudioResources();
                return false;
            }
            logMainParameters();
            logMainParametersExtended();
            return true;
        } catch (IllegalArgumentException e9) {
            Logging.m23185d(TAG, e9.getMessage());
            releaseAudioResources();
            return false;
        }
    }

    private boolean isVolumeFixed() {
        if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return this.audioManager.isVolumeFixed();
        }
        return false;
    }

    private void logMainParameters() {
        Logging.m23185d(TAG, "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    @TargetApi(24)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            Logging.m23185d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.m23185d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    @TargetApi(24)
    private void logUnderrunCount() {
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.m23185d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
        }
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j9);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeGetPlayoutData(int i9, long j9);

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAudioResources() {
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this.audioTrack = null;
        }
    }

    public static void setSpeakerMute(boolean z8) {
        Logging.m23189w(TAG, "setSpeakerMute(" + z8 + ")");
        speakerMute = z8;
    }

    private boolean setStreamVolume(int i9) {
        Logging.m23185d(TAG, "setStreamVolume(" + i9 + ")");
        assertTrue(this.audioManager != null);
        if (isVolumeFixed()) {
            Logging.m23186e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, i9, 0);
        return true;
    }

    private boolean startPlayout() {
        Logging.m23185d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        if (this.audioTrack.getState() != 1) {
            Logging.m23186e(TAG, "AudioTrack instance is not successfully initialized.");
            return false;
        }
        AudioTrackThread audioTrackThread = new AudioTrackThread("AudioTrackJavaThread");
        this.audioThread = audioTrackThread;
        audioTrackThread.start();
        return true;
    }

    private boolean stopPlayout() throws InterruptedException {
        Logging.m23185d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.joinThread();
        this.audioThread = null;
        releaseAudioResources();
        return true;
    }
}
