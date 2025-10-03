package com.cyberlink.clrtc.voe;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Process;
import androidx.annotation.Keep;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import org.webrtc.Logging;
import p044d2.C4665b;

@Keep
/* loaded from: classes.dex */
public class AudioSpeaker {
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "AudioSpeaker";
    private static volatile boolean speakerMute = false;
    private final AudioManager audioManager;
    private ByteBuffer byteBuffer;
    private final Context context;
    private byte[] emptyBytes;
    private final long nativeAudioTrack;
    private AudioTrack audioTrack = null;
    private C1163a audioThread = null;

    /* renamed from: com.cyberlink.clrtc.voe.AudioSpeaker$a */
    public class C1163a extends Thread {

        /* renamed from: b */
        public volatile boolean f5717b;

        public C1163a(String str) {
            super(str);
            this.f5717b = true;
        }

        /* renamed from: a */
        public void m5221a() throws InterruptedException {
            this.f5717b = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }

        @TargetApi(21)
        /* renamed from: b */
        public final int m5222b(AudioTrack audioTrack, ByteBuffer byteBuffer, int i9) {
            return audioTrack.write(byteBuffer, i9, 0);
        }

        /* renamed from: c */
        public final int m5223c(AudioTrack audioTrack, ByteBuffer byteBuffer, int i9) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i9);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IllegalStateException, SecurityException, IllegalArgumentException {
            Process.setThreadPriority(-19);
            Logging.m23185d(AudioSpeaker.TAG, "AudioTrackThread" + C4665b.m18652d());
            try {
                AudioSpeaker.this.audioTrack.play();
                AudioSpeaker.assertTrue(AudioSpeaker.this.audioTrack.getPlayState() == 3);
                int iCapacity = AudioSpeaker.this.byteBuffer.capacity();
                while (this.f5717b) {
                    try {
                        AudioSpeaker audioSpeaker = AudioSpeaker.this;
                        audioSpeaker.nativeGetPlayoutData(iCapacity, audioSpeaker.nativeAudioTrack);
                        AudioSpeaker.assertTrue(iCapacity <= AudioSpeaker.this.byteBuffer.remaining());
                        if (AudioSpeaker.speakerMute) {
                            AudioSpeaker.this.byteBuffer.clear();
                            AudioSpeaker.this.byteBuffer.put(AudioSpeaker.this.emptyBytes);
                            AudioSpeaker.this.byteBuffer.position(0);
                        }
                        int iM5222b = C4665b.m18660l() ? m5222b(AudioSpeaker.this.audioTrack, AudioSpeaker.this.byteBuffer, iCapacity) : m5223c(AudioSpeaker.this.audioTrack, AudioSpeaker.this.byteBuffer, iCapacity);
                        if (iM5222b != iCapacity) {
                            Logging.m23186e(AudioSpeaker.TAG, "AudioTrack.write failed: " + iM5222b);
                            if (iM5222b == -3) {
                                this.f5717b = false;
                            }
                        }
                        AudioSpeaker.this.byteBuffer.rewind();
                    } catch (UnsatisfiedLinkError unused) {
                        Logging.m23186e(AudioSpeaker.TAG, "AudioTrack.nativeGetPlayoutData failed");
                        this.f5717b = false;
                    }
                }
                try {
                    AudioSpeaker.this.audioTrack.stop();
                } catch (IllegalStateException e9) {
                    Logging.m23186e(AudioSpeaker.TAG, "AudioTrack.stop failed: " + e9.getMessage());
                }
                AudioSpeaker.assertTrue(AudioSpeaker.this.audioTrack.getPlayState() == 1);
                AudioSpeaker.this.audioTrack.flush();
            } catch (IllegalStateException e10) {
                Logging.m23186e(AudioSpeaker.TAG, "AudioTrack.play failed: " + e10.getMessage());
                AudioSpeaker.this.releaseAudioResources();
            }
        }
    }

    public AudioSpeaker(Context context, long j9) {
        Logging.m23185d(TAG, "ctor" + C4665b.m18652d());
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

    @Keep
    private int getStreamMaxVolume() {
        Logging.m23185d(TAG, "getStreamMaxVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamMaxVolume(0);
    }

    @Keep
    private int getStreamVolume() {
        Logging.m23185d(TAG, "getStreamVolume");
        assertTrue(this.audioManager != null);
        return this.audioManager.getStreamVolume(0);
    }

    @Keep
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
        if (C4665b.m18660l()) {
            return this.audioManager.isVolumeFixed();
        }
        return false;
    }

    private void logMainParameters() {
        Logging.m23185d(TAG, "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    @TargetApi(24)
    private void logMainParametersExtended() {
        if (C4665b.m18661m()) {
            Logging.m23185d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
        if (C4665b.m18662n()) {
            Logging.m23185d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    @TargetApi(24)
    private void logUnderrunCount() {
        if (C4665b.m18662n()) {
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

    @Keep
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

    @Keep
    private boolean startPlayout() {
        Logging.m23185d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        if (this.audioTrack.getState() != 1) {
            Logging.m23186e(TAG, "AudioTrack instance is not successfully initialized.");
            return false;
        }
        C1163a c1163a = new C1163a("AudioSpeakerJavaThread");
        this.audioThread = c1163a;
        c1163a.start();
        return true;
    }

    @Keep
    private boolean stopPlayout() throws InterruptedException {
        Logging.m23185d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.m5221a();
        this.audioThread = null;
        releaseAudioResources();
        return true;
    }
}
