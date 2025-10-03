package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioRecord;
import android.os.Process;
import java.nio.ByteBuffer;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;

/* loaded from: classes3.dex */
public class WebRtcAudioRecord {
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioRecord";
    private static WebRtcAudioRecordErrorCallback errorCallback = null;
    private static volatile boolean microphoneMute = false;
    private AudioRecord audioRecord = null;
    private AudioRecordThread audioThread = null;
    private ByteBuffer byteBuffer;
    private final Context context;
    private WebRtcAudioEffects effects;
    private byte[] emptyBytes;
    private final long nativeAudioRecord;

    public class AudioRecordThread extends Thread {
        private volatile boolean keepAlive;

        public AudioRecordThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IllegalStateException, SecurityException, IllegalArgumentException {
            Process.setThreadPriority(-19);
            Logging.m23185d(WebRtcAudioRecord.TAG, "AudioRecordThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioRecord.assertTrue(WebRtcAudioRecord.this.audioRecord.getRecordingState() == 3);
            System.nanoTime();
            while (this.keepAlive) {
                int i9 = WebRtcAudioRecord.this.audioRecord.read(WebRtcAudioRecord.this.byteBuffer, WebRtcAudioRecord.this.byteBuffer.capacity());
                if (i9 == WebRtcAudioRecord.this.byteBuffer.capacity()) {
                    if (WebRtcAudioRecord.microphoneMute) {
                        WebRtcAudioRecord.this.byteBuffer.clear();
                        WebRtcAudioRecord.this.byteBuffer.put(WebRtcAudioRecord.this.emptyBytes);
                    }
                    try {
                        WebRtcAudioRecord webRtcAudioRecord = WebRtcAudioRecord.this;
                        webRtcAudioRecord.nativeDataIsRecorded(i9, webRtcAudioRecord.nativeAudioRecord);
                    } catch (UnsatisfiedLinkError unused) {
                        Logging.m23186e(WebRtcAudioRecord.TAG, "AudioRecord.nativeDataIsRecorded failed");
                        this.keepAlive = false;
                        WebRtcAudioRecord.this.reportWebRtcAudioRecordError("AudioRecord.nativeDataIsRecorded failed");
                    }
                } else {
                    String str = "AudioRecord.read failed: " + i9;
                    Logging.m23186e(WebRtcAudioRecord.TAG, str);
                    if (i9 == -3) {
                        this.keepAlive = false;
                        WebRtcAudioRecord.this.reportWebRtcAudioRecordError(str);
                    }
                }
            }
            try {
                if (WebRtcAudioRecord.this.audioRecord != null) {
                    WebRtcAudioRecord.this.audioRecord.stop();
                }
            } catch (IllegalStateException e9) {
                Logging.m23186e(WebRtcAudioRecord.TAG, "AudioRecord.stop failed: " + e9.getMessage());
            }
        }

        public void stopThread() {
            Logging.m23185d(WebRtcAudioRecord.TAG, "stopThread");
            this.keepAlive = false;
        }
    }

    public interface WebRtcAudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String str);

        void onWebRtcAudioRecordInitError(String str);

        void onWebRtcAudioRecordStartError(String str);
    }

    public WebRtcAudioRecord(Context context, long j9) {
        this.effects = null;
        Logging.m23185d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioRecord = j9;
        this.effects = WebRtcAudioEffects.create();
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

    private boolean enableBuiltInAEC(boolean z8) {
        Logging.m23185d(TAG, "enableBuiltInAEC(" + z8 + ')');
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            return webRtcAudioEffects.setAEC(z8);
        }
        Logging.m23186e(TAG, "Built-in AEC is not supported on this platform");
        return false;
    }

    private boolean enableBuiltInNS(boolean z8) {
        Logging.m23185d(TAG, "enableBuiltInNS(" + z8 + ')');
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            return webRtcAudioEffects.setNS(z8);
        }
        Logging.m23186e(TAG, "Built-in NS is not supported on this platform");
        return false;
    }

    private int initRecording(int i9, int i10) throws IllegalStateException {
        Logging.m23185d(TAG, "initRecording(sampleRate=" + i9 + ", channels=" + i10 + ")");
        if (!WebRtcAudioUtils.hasPermission(this.context, "android.permission.RECORD_AUDIO")) {
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
            WebRtcAudioEffects webRtcAudioEffects = this.effects;
            if (webRtcAudioEffects != null) {
                webRtcAudioEffects.enable(this.audioRecord.getAudioSessionId());
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
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
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
        WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback = errorCallback;
        if (webRtcAudioRecordErrorCallback != null) {
            webRtcAudioRecordErrorCallback.onWebRtcAudioRecordError(str);
        }
    }

    private void reportWebRtcAudioRecordInitError(String str) {
        Logging.m23186e(TAG, "Init recording error: " + str);
        WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback = errorCallback;
        if (webRtcAudioRecordErrorCallback != null) {
            webRtcAudioRecordErrorCallback.onWebRtcAudioRecordInitError(str);
        }
    }

    private void reportWebRtcAudioRecordStartError(String str) {
        Logging.m23186e(TAG, "Start recording error: " + str);
        WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback = errorCallback;
        if (webRtcAudioRecordErrorCallback != null) {
            webRtcAudioRecordErrorCallback.onWebRtcAudioRecordStartError(str);
        }
    }

    public static void setErrorCallback(WebRtcAudioRecordErrorCallback webRtcAudioRecordErrorCallback) {
        Logging.m23185d(TAG, "Set error callback");
        errorCallback = webRtcAudioRecordErrorCallback;
    }

    public static void setMicrophoneMute(boolean z8) {
        Logging.m23189w(TAG, "setMicrophoneMute(" + z8 + ")");
        microphoneMute = z8;
    }

    private boolean startRecording() throws IllegalStateException {
        Logging.m23185d(TAG, "startRecording");
        assertTrue(this.audioRecord != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() == 3) {
                AudioRecordThread audioRecordThread = new AudioRecordThread("AudioRecordJavaThread");
                this.audioThread = audioRecordThread;
                audioRecordThread.start();
                return true;
            }
            reportWebRtcAudioRecordStartError("AudioRecord.startRecording failed - incorrect state :" + this.audioRecord.getRecordingState());
            return false;
        } catch (IllegalStateException e9) {
            reportWebRtcAudioRecordStartError("AudioRecord.startRecording failed: " + e9.getMessage());
            return false;
        }
    }

    private boolean stopRecording() {
        Logging.m23185d(TAG, "stopRecording");
        assertTrue(this.audioThread != null);
        this.audioThread.stopThread();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000L)) {
            Logging.m23186e(TAG, "Join of AudioRecordJavaThread timed out");
        }
        this.audioThread = null;
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            webRtcAudioEffects.release();
        }
        releaseAudioResources();
        return true;
    }
}
