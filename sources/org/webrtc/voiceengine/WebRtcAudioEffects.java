package org.webrtc.voiceengine;

import android.annotation.TargetApi;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import java.util.List;
import java.util.UUID;
import org.webrtc.Logging;

/* loaded from: classes3.dex */
class WebRtcAudioEffects {
    private static final boolean DEBUG = false;
    private static final String TAG = "WebRtcAudioEffects";
    private AcousticEchoCanceler aec = null;

    /* renamed from: ns */
    private NoiseSuppressor f20240ns = null;
    private boolean shouldEnableAec = false;
    private boolean shouldEnableNs = false;
    private static final UUID AOSP_ACOUSTIC_ECHO_CANCELER = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");
    private static final UUID AOSP_NOISE_SUPPRESSOR = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");
    private static AudioEffect.Descriptor[] cachedEffects = null;

    private WebRtcAudioEffects() {
        Logging.m23185d(TAG, "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    private static void assertTrue(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public static boolean canUseAcousticEchoCanceler() {
        boolean z8 = (!isAcousticEchoCancelerSupported() || WebRtcAudioUtils.useWebRtcBasedAcousticEchoCanceler() || isAcousticEchoCancelerBlacklisted() || isAcousticEchoCancelerExcludedByUUID()) ? false : true;
        Logging.m23185d(TAG, "canUseAcousticEchoCanceler: " + z8);
        return z8;
    }

    public static boolean canUseNoiseSuppressor() {
        boolean z8 = (!isNoiseSuppressorSupported() || WebRtcAudioUtils.useWebRtcBasedNoiseSuppressor() || isNoiseSuppressorBlacklisted() || isNoiseSuppressorExcludedByUUID()) ? false : true;
        Logging.m23185d(TAG, "canUseNoiseSuppressor: " + z8);
        return z8;
    }

    public static WebRtcAudioEffects create() {
        if (WebRtcAudioUtils.runningOnJellyBeanOrHigher()) {
            return new WebRtcAudioEffects();
        }
        Logging.m23189w(TAG, "API level 16 or higher is required!");
        return null;
    }

    @TargetApi(18)
    private boolean effectTypeIsVoIP(UUID uuid) {
        if (WebRtcAudioUtils.runningOnJellyBeanMR2OrHigher()) {
            return (AudioEffect.EFFECT_TYPE_AEC.equals(uuid) && isAcousticEchoCancelerSupported()) || (AudioEffect.EFFECT_TYPE_NS.equals(uuid) && isNoiseSuppressorSupported());
        }
        return false;
    }

    private static AudioEffect.Descriptor[] getAvailableEffects() {
        AudioEffect.Descriptor[] descriptorArr = cachedEffects;
        if (descriptorArr != null) {
            return descriptorArr;
        }
        AudioEffect.Descriptor[] descriptorArrQueryEffects = AudioEffect.queryEffects();
        cachedEffects = descriptorArrQueryEffects;
        return descriptorArrQueryEffects;
    }

    public static boolean isAcousticEchoCancelerBlacklisted() {
        List<String> blackListedModelsForAecUsage = WebRtcAudioUtils.getBlackListedModelsForAecUsage();
        String str = Build.MODEL;
        boolean zContains = blackListedModelsForAecUsage.contains(str);
        if (zContains) {
            Logging.m23189w(TAG, str + " is blacklisted for HW AEC usage!");
        }
        return zContains;
    }

    @TargetApi(18)
    private static boolean isAcousticEchoCancelerEffectAvailable() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_AEC);
    }

    @TargetApi(18)
    private static boolean isAcousticEchoCancelerExcludedByUUID() {
        for (AudioEffect.Descriptor descriptor : getAvailableEffects()) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_AEC) && descriptor.uuid.equals(AOSP_ACOUSTIC_ECHO_CANCELER)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAcousticEchoCancelerSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && isAcousticEchoCancelerEffectAvailable();
    }

    private static boolean isEffectTypeAvailable(UUID uuid) {
        AudioEffect.Descriptor[] availableEffects = getAvailableEffects();
        if (availableEffects == null) {
            return false;
        }
        for (AudioEffect.Descriptor descriptor : availableEffects) {
            if (descriptor.type.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoiseSuppressorBlacklisted() {
        List<String> blackListedModelsForNsUsage = WebRtcAudioUtils.getBlackListedModelsForNsUsage();
        String str = Build.MODEL;
        boolean zContains = blackListedModelsForNsUsage.contains(str);
        if (zContains) {
            Logging.m23189w(TAG, str + " is blacklisted for HW NS usage!");
        }
        return zContains;
    }

    @TargetApi(18)
    private static boolean isNoiseSuppressorEffectAvailable() {
        return isEffectTypeAvailable(AudioEffect.EFFECT_TYPE_NS);
    }

    @TargetApi(18)
    private static boolean isNoiseSuppressorExcludedByUUID() {
        for (AudioEffect.Descriptor descriptor : getAvailableEffects()) {
            if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_NS) && descriptor.uuid.equals(AOSP_NOISE_SUPPRESSOR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNoiseSuppressorSupported() {
        return WebRtcAudioUtils.runningOnJellyBeanOrHigher() && isNoiseSuppressorEffectAvailable();
    }

    public void enable(int i9) throws IllegalStateException {
        Logging.m23185d(TAG, "enable(audioSession=" + i9 + ")");
        assertTrue(this.aec == null);
        assertTrue(this.f20240ns == null);
        for (AudioEffect.Descriptor descriptor : AudioEffect.queryEffects()) {
            if (effectTypeIsVoIP(descriptor.type)) {
                Logging.m23185d(TAG, "name: " + descriptor.name + ", mode: " + descriptor.connectMode + ", implementor: " + descriptor.implementor + ", UUID: " + descriptor.uuid);
            }
        }
        if (isAcousticEchoCancelerSupported()) {
            AcousticEchoCanceler acousticEchoCancelerCreate = AcousticEchoCanceler.create(i9);
            this.aec = acousticEchoCancelerCreate;
            if (acousticEchoCancelerCreate != null) {
                boolean enabled = acousticEchoCancelerCreate.getEnabled();
                boolean z8 = this.shouldEnableAec && canUseAcousticEchoCanceler();
                if (this.aec.setEnabled(z8) != 0) {
                    Logging.m23186e(TAG, "Failed to set the AcousticEchoCanceler state");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("AcousticEchoCanceler: was ");
                sb.append(enabled ? "enabled" : "disabled");
                sb.append(", enable: ");
                sb.append(z8);
                sb.append(", is now: ");
                sb.append(this.aec.getEnabled() ? "enabled" : "disabled");
                Logging.m23185d(TAG, sb.toString());
            } else {
                Logging.m23186e(TAG, "Failed to create the AcousticEchoCanceler instance");
            }
        }
        if (isNoiseSuppressorSupported()) {
            NoiseSuppressor noiseSuppressorCreate = NoiseSuppressor.create(i9);
            this.f20240ns = noiseSuppressorCreate;
            if (noiseSuppressorCreate == null) {
                Logging.m23186e(TAG, "Failed to create the NoiseSuppressor instance");
                return;
            }
            boolean enabled2 = noiseSuppressorCreate.getEnabled();
            boolean z9 = this.shouldEnableNs && canUseNoiseSuppressor();
            if (this.f20240ns.setEnabled(z9) != 0) {
                Logging.m23186e(TAG, "Failed to set the NoiseSuppressor state");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("NoiseSuppressor: was ");
            sb2.append(enabled2 ? "enabled" : "disabled");
            sb2.append(", enable: ");
            sb2.append(z9);
            sb2.append(", is now: ");
            sb2.append(this.f20240ns.getEnabled() ? "enabled" : "disabled");
            Logging.m23185d(TAG, sb2.toString());
        }
    }

    public void release() {
        Logging.m23185d(TAG, "release");
        AcousticEchoCanceler acousticEchoCanceler = this.aec;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.aec = null;
        }
        NoiseSuppressor noiseSuppressor = this.f20240ns;
        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            this.f20240ns = null;
        }
    }

    public boolean setAEC(boolean z8) {
        Logging.m23185d(TAG, "setAEC(" + z8 + ")");
        if (!canUseAcousticEchoCanceler()) {
            Logging.m23189w(TAG, "Platform AEC is not supported");
            this.shouldEnableAec = false;
            return false;
        }
        if (this.aec == null || z8 == this.shouldEnableAec) {
            this.shouldEnableAec = z8;
            return true;
        }
        Logging.m23186e(TAG, "Platform AEC state can't be modified while recording");
        return false;
    }

    public boolean setNS(boolean z8) {
        Logging.m23185d(TAG, "setNS(" + z8 + ")");
        if (!canUseNoiseSuppressor()) {
            Logging.m23189w(TAG, "Platform NS is not supported");
            this.shouldEnableNs = false;
            return false;
        }
        if (this.f20240ns == null || z8 == this.shouldEnableNs) {
            this.shouldEnableNs = z8;
            return true;
        }
        Logging.m23186e(TAG, "Platform NS state can't be modified while recording");
        return false;
    }
}
