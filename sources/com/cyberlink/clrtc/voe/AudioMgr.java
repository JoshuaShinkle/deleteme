package com.cyberlink.clrtc.voe;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import androidx.annotation.Keep;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Timer;
import java.util.TimerTask;
import org.webrtc.Logging;
import p044d2.C4664a;
import p044d2.C4665b;

@Keep
/* loaded from: classes.dex */
public class AudioMgr {
    private static final String[] AUDIO_MODES = {"MODE_NORMAL", "MODE_RINGTONE", "MODE_IN_CALL", "MODE_IN_COMMUNICATION"};
    private static final int BITS_PER_SAMPLE = 16;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final String TAG = "AudioMgr";
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
    private final C1162a volumeLogger;

    /* renamed from: com.cyberlink.clrtc.voe.AudioMgr$a */
    public static class C1162a {

        /* renamed from: a */
        public final AudioManager f5712a;

        /* renamed from: b */
        public Timer f5713b;

        /* renamed from: com.cyberlink.clrtc.voe.AudioMgr$a$a */
        public class a extends TimerTask {

            /* renamed from: b */
            public final int f5714b;

            /* renamed from: c */
            public final int f5715c;

            public a(int i9, int i10) {
                this.f5714b = i9;
                this.f5715c = i10;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int mode = C1162a.this.f5712a.getMode();
                if (mode == 1) {
                    Logging.m23185d(AudioMgr.TAG, "STREAM_RING stream volume: " + C1162a.this.f5712a.getStreamVolume(2) + " (max=" + this.f5714b + ")");
                    return;
                }
                if (mode == 3) {
                    Logging.m23185d(AudioMgr.TAG, "VOICE_CALL stream volume: " + C1162a.this.f5712a.getStreamVolume(0) + " (max=" + this.f5715c + ")");
                }
            }
        }

        public C1162a(AudioManager audioManager) {
            this.f5712a = audioManager;
        }

        /* renamed from: c */
        public void m5219c() {
            Timer timer = new Timer("CLRTCVolumeLevelLoggerThread");
            this.f5713b = timer;
            timer.schedule(new a(this.f5712a.getStreamMaxVolume(2), this.f5712a.getStreamMaxVolume(0)), 0L, SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS);
        }

        /* renamed from: d */
        public final void m5220d() {
            Timer timer = this.f5713b;
            if (timer != null) {
                timer.cancel();
                this.f5713b = null;
            }
        }
    }

    public AudioMgr(Context context, long j9) {
        Logging.m23185d(TAG, "ctor" + C4665b.m18652d());
        this.context = context;
        this.nativeAudioManager = j9;
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.audioManager = audioManager;
        this.volumeLogger = new C1162a(audioManager);
        storeAudioParameters();
        nativeCacheAudioParameters(this.sampleRate, this.outputChannels, this.inputChannels, this.hardwareAEC, this.hardwareAGC, this.hardwareNS, this.lowLatencyOutput, this.lowLatencyInput, this.proAudio, this.outputBufferSize, this.inputBufferSize, j9);
    }

    private static void assertTrue(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    @Keep
    private void dispose() {
        Logging.m23185d(TAG, "dispose" + C4665b.m18652d());
        if (this.initialized) {
            this.volumeLogger.m5220d();
        }
    }

    private int getLowLatencyInputFramesPerBuffer() {
        assertTrue(isLowLatencyInputSupported());
        return getLowLatencyOutputFramesPerBuffer();
    }

    @TargetApi(17)
    private int getLowLatencyOutputFramesPerBuffer() {
        String property;
        assertTrue(isLowLatencyOutputSupported());
        return (C4665b.m18657i() && (property = this.audioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER")) != null) ? Integer.parseInt(property) : DEFAULT_FRAME_PER_BUFFER;
    }

    private static int getMinInputFrameSize(int i9, int i10) {
        return AudioRecord.getMinBufferSize(i9, i10 == 1 ? 16 : 12, 2) / (i10 * 2);
    }

    private static int getMinOutputFrameSize(int i9, int i10) {
        return AudioTrack.getMinBufferSize(i9, i10 == 1 ? 4 : 12, 2) / (i10 * 2);
    }

    private int getNativeOutputSampleRate() {
        if (C4665b.m18655g()) {
            Logging.m23185d(TAG, "Running emulator, overriding sample rate to 8 kHz.");
            return 8000;
        }
        if (C4665b.m18654f()) {
            Logging.m23185d(TAG, "Default sample rate is overridden to " + C4665b.m18651c() + " Hz");
            return C4665b.m18651c();
        }
        int sampleRateOnJellyBeanMR10OrHigher = C4665b.m18657i() ? getSampleRateOnJellyBeanMR10OrHigher() : C4665b.m18651c();
        Logging.m23185d(TAG, "Sample rate is set to " + sampleRateOnJellyBeanMR10OrHigher + " Hz");
        return sampleRateOnJellyBeanMR10OrHigher;
    }

    @TargetApi(17)
    private int getSampleRateOnJellyBeanMR10OrHigher() {
        String property = this.audioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE");
        return property == null ? C4665b.m18651c() : Integer.parseInt(property);
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

    @Keep
    private boolean init() {
        Logging.m23185d(TAG, "init" + C4665b.m18652d());
        if (this.initialized) {
            return true;
        }
        Logging.m23185d(TAG, "audio mode is: " + AUDIO_MODES[this.audioManager.getMode()]);
        this.initialized = true;
        this.volumeLogger.m5219c();
        return true;
    }

    private static boolean isAcousticEchoCancelerSupported() {
        return C4664a.m18632b();
    }

    @Keep
    private boolean isCommunicationModeEnabled() {
        return this.audioManager.getMode() == 3;
    }

    @Keep
    private boolean isDeviceBlacklistedForOpenSLESUsage() {
        boolean zM18649a = blacklistDeviceForOpenSLESUsageIsOverridden ? blacklistDeviceForOpenSLESUsage : C4665b.m18649a();
        if (zM18649a) {
            Logging.m23186e(TAG, Build.MODEL + " is blacklisted for OpenSL ES usage!");
        }
        return zM18649a;
    }

    private boolean isLowLatencyOutputSupported() {
        return isOpenSLESSupported() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    private static boolean isNoiseSuppressorSupported() {
        return C4664a.m18633c();
    }

    private static boolean isOpenSLESSupported() {
        return C4665b.m18656h();
    }

    @TargetApi(23)
    private boolean isProAudioSupported() {
        return C4665b.m18661m() && this.context.getPackageManager().hasSystemFeature("android.hardware.audio.pro");
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
        return C4665b.m18660l() && isLowLatencyOutputSupported();
    }
}
