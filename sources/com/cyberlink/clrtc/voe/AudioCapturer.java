package com.cyberlink.clrtc.voe;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioRecord;
import android.os.Process;
import androidx.annotation.Keep;
import java.nio.ByteBuffer;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
import p044d2.C4664a;
import p044d2.C4665b;

@Keep
/* loaded from: classes.dex */
public class AudioCapturer {
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "AudioCapturer";
    private static InterfaceC1161b errorCallback = null;
    private static volatile boolean microphoneMute = false;
    private AudioRecord audioRecord = null;
    private C1160a audioThread = null;
    private ByteBuffer byteBuffer;
    private final Context context;
    private C4664a effects;
    private byte[] emptyBytes;
    private final long nativeAudioRecord;

    /* renamed from: com.cyberlink.clrtc.voe.AudioCapturer$a */
    public class C1160a extends Thread {

        /* renamed from: b */
        public volatile boolean f5710b;

        public C1160a(String str) {
            super(str);
            this.f5710b = true;
        }

        /* renamed from: a */
        public void m5216a() {
            Logging.m23185d(AudioCapturer.TAG, "stopThread");
            this.f5710b = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IllegalStateException, SecurityException, IllegalArgumentException {
            Process.setThreadPriority(-19);
            Logging.m23185d(AudioCapturer.TAG, "AudioCaptureThread" + C4665b.m18652d());
            AudioCapturer.assertTrue(AudioCapturer.this.audioRecord.getRecordingState() == 3);
            System.nanoTime();
            while (this.f5710b) {
                int i9 = AudioCapturer.this.audioRecord.read(AudioCapturer.this.byteBuffer, AudioCapturer.this.byteBuffer.capacity());
                if (i9 == AudioCapturer.this.byteBuffer.capacity()) {
                    if (AudioCapturer.microphoneMute) {
                        AudioCapturer.this.byteBuffer.clear();
                        AudioCapturer.this.byteBuffer.put(AudioCapturer.this.emptyBytes);
                    }
                    try {
                        AudioCapturer audioCapturer = AudioCapturer.this;
                        audioCapturer.nativeDataIsRecorded(i9, audioCapturer.nativeAudioRecord);
                    } catch (UnsatisfiedLinkError unused) {
                        Logging.m23186e(AudioCapturer.TAG, "AudioRecord.nativeDataIsRecorded failed");
                        this.f5710b = false;
                        AudioCapturer.this.reportWebRtcAudioRecordError("AudioRecord.nativeDataIsRecorded failed");
                    }
                } else {
                    String str = "AudioRecord.read failed: " + i9;
                    Logging.m23186e(AudioCapturer.TAG, str);
                    if (i9 == -3) {
                        this.f5710b = false;
                        AudioCapturer.this.reportWebRtcAudioRecordError(str);
                    }
                }
            }
            try {
                if (AudioCapturer.this.audioRecord != null) {
                    AudioCapturer.this.audioRecord.stop();
                }
            } catch (IllegalStateException e9) {
                Logging.m23186e(AudioCapturer.TAG, "AudioRecord.stop failed: " + e9.getMessage());
            }
        }
    }

    /* renamed from: com.cyberlink.clrtc.voe.AudioCapturer$b */
    public interface InterfaceC1161b {
    }

    public AudioCapturer(Context context, long j9) {
        this.effects = null;
        Logging.m23185d(TAG, "ctor" + C4665b.m18652d());
        this.context = context;
        this.nativeAudioRecord = j9;
        this.effects = C4664a.m18634d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private int channelCountToConfiguration(int i9) {
        return i9 == 1 ? 16 : 12;
    }

    @Keep
    private boolean enableBuiltInAEC(boolean z8) {
        Logging.m23185d(TAG, "enableBuiltInAEC(" + z8 + ')');
        C4664a c4664a = this.effects;
        if (c4664a != null) {
            return c4664a.m18647q(z8);
        }
        Logging.m23186e(TAG, "Built-in AEC is not supported on this platform");
        return false;
    }

    @Keep
    private boolean enableBuiltInNS(boolean z8) {
        Logging.m23185d(TAG, "enableBuiltInNS(" + z8 + ')');
        C4664a c4664a = this.effects;
        if (c4664a != null) {
            return c4664a.m18648r(z8);
        }
        Logging.m23186e(TAG, "Built-in NS is not supported on this platform");
        return false;
    }

    @Keep
    private int initRecording(int i9, int i10) throws IllegalStateException {
        Logging.m23185d(TAG, "initRecording(sampleRate=" + i9 + ", channels=" + i10 + ")");
        if (!C4665b.m18653e(this.context, "android.permission.RECORD_AUDIO")) {
            reportWebRtcAudioRecordInitError("RECORD_AUDIO permission is missing");
            return -1;
        }
        if (this.audioRecord != null) {
            reportWebRtcAudioRecordInitError("InitRecording called twice without StopRecording.");
            return -1;
        }
        int i11 = i9 / 100;
        this.byteBuffer = ByteBuffer.allocateDirect(i10 * 2 * i11);
        Logging.m23185d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioRecord);
        int iChannelCountToConfiguration = channelCountToConfiguration(i10);
        int minBufferSize = AudioRecord.getMinBufferSize(i9, iChannelCountToConfiguration, 2);
        if (minBufferSize == -1 || minBufferSize == -2) {
            reportWebRtcAudioRecordInitError("AudioRecord.getMinBufferSize failed: " + minBufferSize);
            return -1;
        }
        Logging.m23185d(TAG, "AudioRecord.getMinBufferSize: " + minBufferSize);
        int iMax = Math.max(minBufferSize * 2, this.byteBuffer.capacity());
        Logging.m23185d(TAG, "bufferSizeInBytes: " + iMax);
        try {
            AudioRecord audioRecord = new AudioRecord(7, i9, iChannelCountToConfiguration, 2, iMax);
            this.audioRecord = audioRecord;
            if (audioRecord.getState() != 1) {
                reportWebRtcAudioRecordInitError("Failed to create a new AudioRecord instance");
                releaseAudioResources();
                return -1;
            }
            C4664a c4664a = this.effects;
            if (c4664a != null) {
                c4664a.m18645f(this.audioRecord.getAudioSessionId());
            }
            logMainParameters();
            logMainParametersExtended();
            return i11;
        } catch (IllegalArgumentException e9) {
            reportWebRtcAudioRecordInitError("AudioRecord ctor error: " + e9.getMessage());
            releaseAudioResources();
            return -1;
        }
    }

    private void logMainParameters() {
        Logging.m23185d(TAG, "AudioRecord: session ID: " + this.audioRecord.getAudioSessionId() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
    }

    @TargetApi(23)
    private void logMainParametersExtended() {
        if (C4665b.m18661m()) {
            Logging.m23185d(TAG, "AudioRecord: buffer size in frames: " + this.audioRecord.getBufferSizeInFrames());
        }
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer, long j9);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDataIsRecorded(int i9, long j9);

    private void releaseAudioResources() {
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.release();
            this.audioRecord = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioRecordError(String str) {
        Logging.m23186e(TAG, "Run-time recording error: " + str);
    }

    private void reportWebRtcAudioRecordInitError(String str) {
        Logging.m23186e(TAG, "Init recording error: " + str);
    }

    private void reportWebRtcAudioRecordStartError(String str) {
        Logging.m23186e(TAG, "Start recording error: " + str);
    }

    public static void setErrorCallback(InterfaceC1161b interfaceC1161b) {
        Logging.m23185d(TAG, "Set error callback");
    }

    public static void setMicrophoneMute(boolean z8) {
        Logging.m23189w(TAG, "setMicrophoneMute(" + z8 + ")");
        microphoneMute = z8;
    }

    @Keep
    private boolean startRecording() throws IllegalStateException {
        Logging.m23185d(TAG, "startRecording");
        assertTrue(this.audioRecord != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() == 3) {
                C1160a c1160a = new C1160a("AudioCaptureJavaThread");
                this.audioThread = c1160a;
                c1160a.start();
                return true;
            }
            reportWebRtcAudioRecordStartError("AudioRecord.startRecording failed - incorrect state :" + this.audioRecord.getRecordingState());
            return false;
        } catch (IllegalStateException e9) {
            reportWebRtcAudioRecordStartError("AudioRecord.startRecording failed: " + e9.getMessage());
            return false;
        }
    }

    @Keep
    private boolean stopRecording() {
        Logging.m23185d(TAG, "stopRecording");
        assertTrue(this.audioThread != null);
        this.audioThread.m5216a();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000L)) {
            Logging.m23186e(TAG, "Join of AudioRecordJavaThread timed out");
        }
        this.audioThread = null;
        C4664a c4664a = this.effects;
        if (c4664a != null) {
            c4664a.m18646p();
        }
        releaseAudioResources();
        return true;
    }
}
