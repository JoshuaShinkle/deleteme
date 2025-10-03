package p044d2;

import android.annotation.TargetApi;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build;
import java.util.List;
import java.util.UUID;
import org.webrtc.Logging;

/* renamed from: d2.a */
/* loaded from: classes.dex */
public class C4664a {

    /* renamed from: e */
    public static final UUID f16327e = UUID.fromString("bb392ec0-8d4d-11e0-a896-0002a5d5c51b");

    /* renamed from: f */
    public static final UUID f16328f = UUID.fromString("c06c8400-8e06-11e0-9cb6-0002a5d5c51b");

    /* renamed from: g */
    public static AudioEffect.Descriptor[] f16329g = null;

    /* renamed from: a */
    public AcousticEchoCanceler f16330a = null;

    /* renamed from: b */
    public NoiseSuppressor f16331b = null;

    /* renamed from: c */
    public boolean f16332c = false;

    /* renamed from: d */
    public boolean f16333d = false;

    public C4664a() {
        Logging.m23185d("AudioEffects", "ctor" + C4665b.m18652d());
    }

    /* renamed from: a */
    public static void m18631a(boolean z8) {
        if (!z8) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    /* renamed from: b */
    public static boolean m18632b() {
        boolean z8 = m18638j() && !C4665b.m18667s() && (!m18637i() || C4665b.m18666r());
        Logging.m23185d("AudioEffects", "canUseAcousticEchoCanceler: " + z8);
        return z8;
    }

    /* renamed from: c */
    public static boolean m18633c() {
        boolean z8 = (!m18643o() || C4665b.m18668t() || m18640l() || m18642n()) ? false : true;
        Logging.m23185d("AudioEffects", "canUseNoiseSuppressor: " + z8);
        return z8;
    }

    /* renamed from: d */
    public static C4664a m18634d() {
        if (C4665b.m18659k()) {
            return new C4664a();
        }
        Logging.m23189w("AudioEffects", "API level 16 or higher is required!");
        return null;
    }

    /* renamed from: g */
    public static AudioEffect.Descriptor[] m18635g() {
        AudioEffect.Descriptor[] descriptorArr = f16329g;
        if (descriptorArr != null) {
            return descriptorArr;
        }
        System.currentTimeMillis();
        AudioEffect.Descriptor[] descriptorArrQueryEffects = AudioEffect.queryEffects();
        f16329g = descriptorArrQueryEffects;
        return descriptorArrQueryEffects;
    }

    @TargetApi(18)
    /* renamed from: h */
    public static boolean m18636h() {
        return m18639k(AudioEffect.EFFECT_TYPE_AEC);
    }

    @TargetApi(18)
    /* renamed from: i */
    public static boolean m18637i() {
        if (C4665b.m18658j()) {
            for (AudioEffect.Descriptor descriptor : m18635g()) {
                if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_AEC) && descriptor.uuid.equals(f16327e)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: j */
    public static boolean m18638j() {
        return C4665b.m18659k() && m18636h();
    }

    /* renamed from: k */
    public static boolean m18639k(UUID uuid) {
        AudioEffect.Descriptor[] descriptorArrM18635g = m18635g();
        if (descriptorArrM18635g == null) {
            return false;
        }
        for (AudioEffect.Descriptor descriptor : descriptorArrM18635g) {
            if (descriptor.type.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: l */
    public static boolean m18640l() {
        List<String> listM18650b = C4665b.m18650b();
        String str = Build.MODEL;
        boolean zContains = listM18650b.contains(str);
        if (zContains) {
            Logging.m23189w("AudioEffects", str + " is blacklisted for HW NS usage!");
        }
        return zContains;
    }

    @TargetApi(18)
    /* renamed from: m */
    public static boolean m18641m() {
        return m18639k(AudioEffect.EFFECT_TYPE_NS);
    }

    @TargetApi(18)
    /* renamed from: n */
    public static boolean m18642n() {
        if (C4665b.m18658j()) {
            for (AudioEffect.Descriptor descriptor : m18635g()) {
                if (descriptor.type.equals(AudioEffect.EFFECT_TYPE_NS) && descriptor.uuid.equals(f16328f)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: o */
    public static boolean m18643o() {
        return C4665b.m18659k() && m18641m();
    }

    @TargetApi(18)
    /* renamed from: e */
    public final boolean m18644e(UUID uuid) {
        if (C4665b.m18658j()) {
            return (AudioEffect.EFFECT_TYPE_AEC.equals(uuid) && m18638j()) || (AudioEffect.EFFECT_TYPE_NS.equals(uuid) && m18643o());
        }
        return false;
    }

    /* renamed from: f */
    public void m18645f(int i9) throws IllegalStateException {
        Logging.m23185d("AudioEffects", "enable(audioSession=" + i9 + ")");
        m18631a(this.f16330a == null);
        m18631a(this.f16331b == null);
        for (AudioEffect.Descriptor descriptor : AudioEffect.queryEffects()) {
            if (m18644e(descriptor.type)) {
                Logging.m23185d("AudioEffects", "name: " + descriptor.name + ", mode: " + descriptor.connectMode + ", implementor: " + descriptor.implementor + ", UUID: " + descriptor.uuid);
            }
        }
        if (m18638j()) {
            AcousticEchoCanceler acousticEchoCancelerCreate = AcousticEchoCanceler.create(i9);
            this.f16330a = acousticEchoCancelerCreate;
            if (acousticEchoCancelerCreate != null) {
                boolean enabled = acousticEchoCancelerCreate.getEnabled();
                boolean z8 = this.f16332c && m18632b();
                if (this.f16330a.setEnabled(z8) != 0) {
                    Logging.m23186e("AudioEffects", "Failed to set the AcousticEchoCanceler state");
                }
                StringBuilder sb = new StringBuilder();
                sb.append("AcousticEchoCanceler: was ");
                sb.append(enabled ? "enabled" : "disabled");
                sb.append(", enable: ");
                sb.append(z8);
                sb.append(", is now: ");
                sb.append(this.f16330a.getEnabled() ? "enabled" : "disabled");
                Logging.m23185d("AudioEffects", sb.toString());
            } else {
                Logging.m23186e("AudioEffects", "Failed to create the AcousticEchoCanceler instance");
            }
        }
        if (m18643o()) {
            NoiseSuppressor noiseSuppressorCreate = NoiseSuppressor.create(i9);
            this.f16331b = noiseSuppressorCreate;
            if (noiseSuppressorCreate == null) {
                Logging.m23186e("AudioEffects", "Failed to create the NoiseSuppressor instance");
                return;
            }
            boolean enabled2 = noiseSuppressorCreate.getEnabled();
            boolean z9 = this.f16333d && m18633c();
            if (this.f16331b.setEnabled(z9) != 0) {
                Logging.m23186e("AudioEffects", "Failed to set the NoiseSuppressor state");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("NoiseSuppressor: was ");
            sb2.append(enabled2 ? "enabled" : "disabled");
            sb2.append(", enable: ");
            sb2.append(z9);
            sb2.append(", is now: ");
            sb2.append(this.f16331b.getEnabled() ? "enabled" : "disabled");
            Logging.m23185d("AudioEffects", sb2.toString());
        }
    }

    /* renamed from: p */
    public void m18646p() {
        Logging.m23185d("AudioEffects", "release");
        AcousticEchoCanceler acousticEchoCanceler = this.f16330a;
        if (acousticEchoCanceler != null) {
            acousticEchoCanceler.release();
            this.f16330a = null;
        }
        NoiseSuppressor noiseSuppressor = this.f16331b;
        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            this.f16331b = null;
        }
    }

    /* renamed from: q */
    public boolean m18647q(boolean z8) {
        Logging.m23185d("AudioEffects", "setAEC(" + z8 + ")");
        if (!m18632b()) {
            Logging.m23189w("AudioEffects", "Platform AEC is not supported");
            this.f16332c = false;
            return false;
        }
        if (this.f16330a == null || z8 == this.f16332c) {
            this.f16332c = z8;
            return true;
        }
        Logging.m23186e("AudioEffects", "Platform AEC state can't be modified while recording");
        return false;
    }

    /* renamed from: r */
    public boolean m18648r(boolean z8) {
        Logging.m23185d("AudioEffects", "setNS(" + z8 + ")");
        if (!m18633c()) {
            Logging.m23189w("AudioEffects", "Platform NS is not supported");
            this.f16333d = false;
            return false;
        }
        if (this.f16331b == null || z8 == this.f16333d) {
            this.f16333d = z8;
            return true;
        }
        Logging.m23186e("AudioEffects", "Platform NS state can't be modified while recording");
        return false;
    }
}
