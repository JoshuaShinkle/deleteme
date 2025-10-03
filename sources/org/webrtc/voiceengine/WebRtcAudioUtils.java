package org.webrtc.voiceengine;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import java.util.Arrays;
import java.util.List;
import org.webrtc.Logging;

/* loaded from: classes3.dex */
public final class WebRtcAudioUtils {
    private static final String TAG = "WebRtcAudioUtils";
    private static final String[] BLACKLISTED_OPEN_SL_ES_MODELS = new String[0];
    private static final String[] BLACKLISTED_AEC_MODELS = {"D6503", "ONE A2005", "MotoG3"};
    private static final String[] BLACKLISTED_NS_MODELS = {"Nexus 10", "Nexus 9", "ONE A2005"};
    private static final int DEFAULT_SAMPLE_RATE_HZ = 48000;
    private static int defaultSampleRateHz = DEFAULT_SAMPLE_RATE_HZ;
    private static boolean isDefaultSampleRateOverridden = false;
    private static boolean useWebRtcBasedAcousticEchoCanceler = false;
    private static boolean useWebRtcBasedNoiseSuppressor = false;

    public static boolean deviceIsBlacklistedForOpenSLESUsage() {
        return Arrays.asList(BLACKLISTED_OPEN_SL_ES_MODELS).contains(Build.MODEL);
    }

    public static List<String> getBlackListedModelsForAecUsage() {
        return Arrays.asList(BLACKLISTED_AEC_MODELS);
    }

    public static List<String> getBlackListedModelsForNsUsage() {
        return Arrays.asList(BLACKLISTED_NS_MODELS);
    }

    public static synchronized int getDefaultSampleRateHz() {
        return defaultSampleRateHz;
    }

    public static String getThreadInfo() {
        return "@[name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId() + "]";
    }

    public static boolean hasPermission(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.canUseAcousticEchoCanceler();
    }

    public static boolean isAutomaticGainControlSupported() {
        return false;
    }

    public static synchronized boolean isDefaultSampleRateOverridden() {
        return isDefaultSampleRateOverridden;
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioEffects.canUseNoiseSuppressor();
    }

    public static void logDeviceInfo(String str) {
        Logging.m23185d(str, "Android SDK: " + Build.VERSION.SDK_INT + ", Release: " + Build.VERSION.RELEASE + ", Brand: " + Build.BRAND + ", Device: " + Build.DEVICE + ", Id: " + Build.ID + ", Hardware: " + Build.HARDWARE + ", Manufacturer: " + Build.MANUFACTURER + ", Model: " + Build.MODEL + ", Product: " + Build.PRODUCT);
    }

    public static boolean runningOnEmulator() {
        return Build.HARDWARE.equals("goldfish") && Build.BRAND.startsWith("generic_");
    }

    public static boolean runningOnGingerBreadOrHigher() {
        return true;
    }

    public static boolean runningOnJellyBeanMR1OrHigher() {
        return true;
    }

    public static boolean runningOnJellyBeanMR2OrHigher() {
        return true;
    }

    public static boolean runningOnJellyBeanOrHigher() {
        return true;
    }

    public static boolean runningOnLollipopOrHigher() {
        return true;
    }

    public static boolean runningOnMarshmallowOrHigher() {
        return true;
    }

    public static boolean runningOnNougatOrHigher() {
        return true;
    }

    public static synchronized void setDefaultSampleRateHz(int i9) {
        isDefaultSampleRateOverridden = true;
        defaultSampleRateHz = i9;
    }

    public static synchronized void setWebRtcBasedAcousticEchoCanceler(boolean z8) {
        useWebRtcBasedAcousticEchoCanceler = z8;
    }

    public static synchronized void setWebRtcBasedAutomaticGainControl(boolean z8) {
        Logging.m23189w(TAG, "setWebRtcBasedAutomaticGainControl() is deprecated");
    }

    public static synchronized void setWebRtcBasedNoiseSuppressor(boolean z8) {
        useWebRtcBasedNoiseSuppressor = z8;
    }

    public static synchronized boolean useWebRtcBasedAcousticEchoCanceler() {
        if (useWebRtcBasedAcousticEchoCanceler) {
            Logging.m23189w(TAG, "Overriding default behavior; now using WebRTC AEC!");
        }
        return useWebRtcBasedAcousticEchoCanceler;
    }

    public static synchronized boolean useWebRtcBasedAutomaticGainControl() {
        return true;
    }

    public static synchronized boolean useWebRtcBasedNoiseSuppressor() {
        if (useWebRtcBasedNoiseSuppressor) {
            Logging.m23189w(TAG, "Overriding default behavior; now using WebRTC NS!");
        }
        return useWebRtcBasedNoiseSuppressor;
    }
}
