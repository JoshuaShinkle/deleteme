package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.Logging;

/* loaded from: classes3.dex */
public class WebRtcAudioManager {
    private static final String[] AUDIO_MODES = {"MODE_NORMAL", "MODE_RINGTONE", "MODE_IN_CALL", "MODE_IN_COMMUNICATION"};
    private static final int BITS_PER_SAMPLE = 16;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "WebRtcAudioManager";
    private static boolean blacklistDeviceForOpenSLESUsage = false;
    private static boolean blacklistDeviceForOpenSLESUsageIsOverridden = false;
    private static boolean useStereoInput = false;
    private static boolean useStereoOutput = false;
    private final AudioManager audioManager;
    private final Context context;
    private boolean hardwareAEC;
    private boolean hardwareAGC;
    private boolean hardwareNS;
    private boolean initialized = false;
    private int inputBufferSize;
    private int inputChannels;
    private boolean lowLatencyInput;
    private boolean lowLatencyOutput;
    private final long nativeAudioManager;
    private int nativeChannels;
    private int nativeSampleRate;
    private int outputBufferSize;
    private int outputChannels;
    private boolean proAudio;
    private int sampleRate;
    private final VolumeLogger volumeLogger;

    public static class VolumeLogger {
        private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
        private static final int TIMER_PERIOD_IN_SECONDS = 30;
        private final AudioManager audioManager;
        private Timer timer;

        public class LogVolumeTask extends TimerTask {
            private final int maxRingVolume;
            private final int maxVoiceCallVolume;

            public LogVolumeTask(int i9, int i10) {
                this.maxRingVolume = i9;
                this.maxVoiceCallVolume = i10;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int mode = VolumeLogger.this.audioManager.getMode();
                if (mode == 1) {
                    Logging.m23185d(WebRtcAudioManager.TAG, "STREAM_RING stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(2) + " (max=" + this.maxRingVolume + ")");
                    return;
                }
                if (mode == 3) {
                    Logging.m23185d(WebRtcAudioManager.TAG, "VOICE_CALL stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(0) + " (max=" + this.maxVoiceCallVolume + ")");
                }
            }
        }

        public VolumeLogger(AudioManager audioManager) {
            this.audioManager = audioManager;
        }

        private void stop() {
            Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
                this.timer = null;
            }
        }

        public void start() {
            Timer timer = new Timer(THREAD_NAME);
            this.timer = timer;
            timer.schedule(new LogVolumeTask(this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0L, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
        }
    }

    public WebRtcAudioManager(Context context, long j9) {
        Logging.m23185d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
        this.context = context;
        this.nativeAudioManager = j9;
        this.audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.volumeLogger = null;
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.outputChannels, this.inputChannels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.lowLatencyInput, this.proAudio, this.outputBufferSize, this.inputBufferSize, j9);
    }

    private static void assertTrue(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private void dispose() {
        Logging.m23185d(TAG, "dispose" + WebRtcAudioUtils.getThreadInfo());
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        String property;
        assertTrue(isLowLatencyOutputSupported());
        return (WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() && (property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) ? Integer.parseInt(property) : DEFAULT_FRAME_PER_BUFFER;
    }

    private static int getMinInputFrameSize(int i9, int i10) {
        return AudioRecord.getMinBufferSize(i9, i10 == 1 ? 16 : 12, 2) / (i10 * 2);
    }

    private static int getMinOutputFrameSize(int i9, int i10) {
        return AudioTrack.getMinBufferSize(i9, i10 == 1 ? 4 : 12, 2) / (i10 * 2);
    }

    private int getNativeOutputSampleRate() {
        if (WebRtcAudioUtils.runningOnEmulator()) {
            Logging.m23185d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return 8000;
        }
        if (WebRtcAudioUtils.isDefaultSampleRateOverridden()) {
            Logging.m23185d(TAG, "Default sample rate is overriden to " + WebRtcAudioUtils.getDefaultSampleRateHz() + " Hz");
            return WebRtcAudioUtils.getDefaultSampleRateHz();
        }
        int sampleRateOnJellyBeanMR10OrHigher = WebRtcAudioUtils.runningOnJellyBeanMR1OrHigher() ? getSampleRateOnJellyBeanMR10OrHigher() : WebRtcAudioUtils.getDefaultSampleRateHz();
        Logging.m23185d(TAG, "Sample rate is set to " + sampleRateOnJellyBeanMR10OrHigher + " Hz");
        return sampleRateOnJellyBeanMR10OrHigher;
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        return Integer.parseInt("48000");
    }

    public static synchronized boolean getStereoInput() {
        return useStereoInput;
    }

    public static synchronized boolean getStereoOutput() {
        return useStereoOutput;
    }

    private boolean hasEarpiece() {
        return this.context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    private boolean init() {
        Logging.m23185d(TAG, "init" + WebRtcAudioUtils.getThreadInfo());
        if (this.initialized) {
            return true;
        }
        Logging.m23185d(TAG, "audio mode is: " + AUDIO_MODES[this.audioManager.getMode()]);
        this.initialized = true;
        return true;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    private boolean isCommunicationModeEnabled() {
        return this.audioManager.getMode() == 3;
    }

    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean zDeviceIsBlacklistedForOpenSLESUsage = blacklistDeviceForOpenSLESUsageIsOverridden ? blacklistDeviceForOpenSLESUsage : WebRtcAudioUtils.deviceIsBlacklistedForOpenSLESUsage();
        if (zDeviceIsBlacklistedForOpenSLESUsage) {
            Logging.m23186e(TAG, Build.MODEL + " is blacklisted for OpenSL ES usage!");
        }
        return zDeviceIsBlacklistedForOpenSLESUsage;
    }

    private boolean isLowLatencyOutputSupported() {
        return isOpenSLESSupported() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    private static boolean isOpenSLESSupported() {
        return WebRtcAudioUtils.runningOnGingerBreadOrHigher();
    }

    @TargetApi(23)
    private boolean isProAudioSupported() {
        return WebRtcAudioUtils.runningOnMarshmallowOrHigher() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.pro");
    }

    private native void nativeCacheAudioParameters(int i9, int i10, int i11, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, int i12, int i13, long j9);

    public static synchronized void setBlacklistDeviceForOpenSLESUsage(boolean z8) {
        blacklistDeviceForOpenSLESUsageIsOverridden = true;
        blacklistDeviceForOpenSLESUsage = z8;
    }

    public static synchronized void setStereoInput(boolean z8) {
        Logging.m23189w(TAG, "Overriding default input behavior: setStereoInput(" + z8 + ')');
        useStereoInput = z8;
    }

    public static synchronized void setStereoOutput(boolean z8) {
        Logging.m23189w(TAG, "Overriding default output behavior: setStereoOutput(" + z8 + ')');
        useStereoOutput = z8;
    }

    private void storeAudioParameters() {
        this.outputChannels = getStereoOutput() ? 2 : 1;
        this.inputChannels = getStereoInput() ? 2 : 1;
        this.sampleRate = getNativeOutputSampleRate();
        this.hardwareAEC = isAcousticEchoCancelerSupported();
        this.hardwareAGC = false;
        this.hardwareNS = isNoiseSuppressorSupported();
        this.lowLatencyOutput = isLowLatencyOutputSupported();
        this.lowLatencyInput = isLowLatencyInputSupported();
        this.proAudio = isProAudioSupported();
        this.outputBufferSize = this.lowLatencyOutput ? getLowLatencyOutputFramesPerBuffer() : getMinOutputFrameSize(this.sampleRate, this.outputChannels);
        this.inputBufferSize = this.lowLatencyInput ? getLowLatencyInputFramesPerBuffer() : getMinInputFrameSize(this.sampleRate, this.inputChannels);
    }

    public boolean isLowLatencyInputSupported() {
        return WebRtcAudioUtils.runningOnLollipopOrHigher() && isLowLatencyOutputSupported();
    }
}
