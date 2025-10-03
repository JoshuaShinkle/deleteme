package com.cyberlink.clrtc.rtc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.util.Log;
import com.cyberlink.clrtc.rtc.RTCBluetoothManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.HashSet;
import java.util.Set;
import org.webrtc.ThreadUtils;
import p013b2.C0598a;
import p023c2.C0717a;

/* loaded from: classes.dex */
public class RTCAudioManager {

    /* renamed from: a */
    public final Context f5517a;

    /* renamed from: b */
    public AudioManager f5518b;

    /* renamed from: c */
    public InterfaceC1108b f5519c;

    /* renamed from: d */
    public AudioManagerState f5520d;

    /* renamed from: i */
    public AudioDevice f5525i;

    /* renamed from: j */
    public AudioDevice f5526j;

    /* renamed from: k */
    public AudioDevice f5527k;

    /* renamed from: l */
    public final String f5528l;

    /* renamed from: m */
    public C0598a f5529m;

    /* renamed from: n */
    public final RTCBluetoothManager f5530n;

    /* renamed from: p */
    public BroadcastReceiver f5532p;

    /* renamed from: q */
    public AudioManager.OnAudioFocusChangeListener f5533q;

    /* renamed from: e */
    public int f5521e = -2;

    /* renamed from: f */
    public boolean f5522f = false;

    /* renamed from: g */
    public boolean f5523g = false;

    /* renamed from: h */
    public boolean f5524h = false;

    /* renamed from: o */
    public Set<AudioDevice> f5531o = new HashSet();

    /* renamed from: r */
    public boolean f5534r = true;

    public enum AudioDevice {
        SPEAKER_PHONE,
        WIRED_HEADSET,
        EARPIECE,
        BLUETOOTH,
        NONE
    }

    public enum AudioManagerState {
        UNINITIALIZED,
        PREINITIALIZED,
        RUNNING
    }

    /* renamed from: com.cyberlink.clrtc.rtc.RTCAudioManager$a */
    public class RunnableC1107a implements Runnable {
        public RunnableC1107a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RTCAudioManager.this.m5060k();
        }
    }

    /* renamed from: com.cyberlink.clrtc.rtc.RTCAudioManager$b */
    public interface InterfaceC1108b {
        /* renamed from: a */
        void mo5071a(AudioDevice audioDevice, Set<AudioDevice> set);

        /* renamed from: b */
        void mo5072b();
    }

    /* renamed from: com.cyberlink.clrtc.rtc.RTCAudioManager$c */
    public class C1109c extends BroadcastReceiver {
        public C1109c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(RemoteConfigConstants.ResponseFieldKey.STATE, 0);
            int intExtra2 = intent.getIntExtra("microphone", 0);
            String stringExtra = intent.getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME);
            StringBuilder sb = new StringBuilder();
            sb.append("WiredHeadsetReceiver.onReceive");
            sb.append(C0717a.m3547b());
            sb.append(": a=");
            sb.append(intent.getAction());
            sb.append(", s=");
            sb.append(intExtra == 0 ? "unplugged" : "plugged");
            sb.append(", m=");
            sb.append(intExtra2 == 1 ? "mic" : "no mic");
            sb.append(", n=");
            sb.append(stringExtra);
            sb.append(", sb=");
            sb.append(isInitialStickyBroadcast());
            Log.d("RTCAudioManager", sb.toString());
            RTCAudioManager.this.f5524h = intExtra == 1;
            RTCAudioManager.this.m5070u();
        }

        public /* synthetic */ C1109c(RTCAudioManager rTCAudioManager, RunnableC1107a runnableC1107a) {
            this();
        }
    }

    public RTCAudioManager(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.f5529m = null;
        Log.d("RTCAudioManager", "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.f5517a = context;
        this.f5518b = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.f5530n = RTCBluetoothManager.m5082l(context, this);
        this.f5532p = new C1109c(this, null);
        this.f5520d = AudioManagerState.UNINITIALIZED;
        this.f5533q = onAudioFocusChangeListener;
        this.f5528l = "true";
        Log.d("RTCAudioManager", "useSpeakerphone: true");
        if ("true".equals("false")) {
            this.f5525i = AudioDevice.EARPIECE;
        } else {
            this.f5525i = AudioDevice.SPEAKER_PHONE;
        }
        this.f5529m = C0598a.m3291a(context, new RunnableC1107a());
        Log.d("RTCAudioManager", "defaultAudioDevice: " + this.f5525i);
        C0717a.m3548c("RTCAudioManager");
    }

    /* renamed from: d */
    public static RTCAudioManager m5052d(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        return new RTCAudioManager(context, onAudioFocusChangeListener);
    }

    /* renamed from: c */
    public void m5053c(int i9) {
        if (this.f5518b.getMode() == i9) {
            return;
        }
        Log.d("RTCAudioManager", "[changeAudioManagerMode] setMode : " + i9);
        this.f5518b.setMode(i9);
    }

    /* renamed from: e */
    public InterfaceC1108b m5054e() {
        return this.f5519c;
    }

    /* renamed from: f */
    public int m5055f() {
        return this.f5518b.getRingerMode();
    }

    /* renamed from: g */
    public AudioDevice m5056g() {
        ThreadUtils.checkIsOnMainThread();
        return this.f5526j;
    }

    /* renamed from: h */
    public final boolean m5057h() {
        return this.f5517a.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    @Deprecated
    /* renamed from: i */
    public boolean m5058i() {
        for (AudioDeviceInfo audioDeviceInfo : this.f5518b.getDevices(3)) {
            int type = audioDeviceInfo.getType();
            if (type == 3) {
                Log.d("RTCAudioManager", "hasWiredHeadset: found wired headset");
                return true;
            }
            if (type == 11) {
                Log.d("RTCAudioManager", "hasWiredHeadset: found USB audio device");
                return true;
            }
            if (type == 22) {
                Log.d("RTCAudioManager", "hasWiredHeadset: found USB audio headset device");
                return true;
            }
        }
        return false;
    }

    /* renamed from: j */
    public boolean m5059j() {
        return this.f5530n.m5092q();
    }

    /* renamed from: k */
    public final void m5060k() {
        if (this.f5528l.equals("auto") && this.f5531o.size() == 2) {
            Set<AudioDevice> set = this.f5531o;
            AudioDevice audioDevice = AudioDevice.EARPIECE;
            if (set.contains(audioDevice)) {
                Set<AudioDevice> set2 = this.f5531o;
                AudioDevice audioDevice2 = AudioDevice.SPEAKER_PHONE;
                if (set2.contains(audioDevice2)) {
                    if (this.f5529m.m3295e()) {
                        m5063n(audioDevice);
                    } else {
                        m5063n(audioDevice2);
                    }
                }
            }
        }
    }

    /* renamed from: l */
    public final void m5061l(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.f5517a.registerReceiver(broadcastReceiver, intentFilter);
    }

    /* renamed from: m */
    public void m5062m(AudioDevice audioDevice) {
        ThreadUtils.checkIsOnMainThread();
        if (!this.f5531o.contains(audioDevice)) {
            Log.e("RTCAudioManager", "Can not select " + audioDevice + " from available " + this.f5531o);
        }
        this.f5527k = audioDevice;
        m5070u();
    }

    /* renamed from: n */
    public final void m5063n(AudioDevice audioDevice) {
        Log.d("RTCAudioManager", "setAudioDeviceInternal(device=" + audioDevice + ")");
        C0717a.m3546a(this.f5531o.contains(audioDevice));
        this.f5526j = audioDevice;
    }

    /* renamed from: o */
    public final void m5064o(boolean z8) {
        if (this.f5518b.isMicrophoneMute() == z8) {
            return;
        }
        this.f5518b.setMicrophoneMute(z8);
    }

    /* renamed from: p */
    public void m5065p(boolean z8) {
        this.f5534r = z8;
        m5070u();
    }

    /* renamed from: q */
    public void m5066q(boolean z8) {
        this.f5518b.setSpeakerphoneOn(z8);
    }

    /* renamed from: r */
    public int m5067r(InterfaceC1108b interfaceC1108b, int i9) {
        Log.d("RTCAudioManager", TtmlNode.START);
        ThreadUtils.checkIsOnMainThread();
        AudioManagerState audioManagerState = this.f5520d;
        AudioManagerState audioManagerState2 = AudioManagerState.RUNNING;
        if (audioManagerState == audioManagerState2) {
            Log.e("RTCAudioManager", "AudioManager is already active");
            return this.f5521e;
        }
        Log.d("RTCAudioManager", "AudioManager starts...");
        this.f5519c = interfaceC1108b;
        this.f5520d = audioManagerState2;
        this.f5521e = this.f5518b.getMode();
        Log.d("RTCAudioManager", "[start] savedAudioMode : " + this.f5521e);
        this.f5522f = this.f5518b.isSpeakerphoneOn();
        this.f5523g = this.f5518b.isMicrophoneMute();
        this.f5524h = m5058i();
        if (this.f5518b.requestAudioFocus(this.f5533q, 0, 2) == 1) {
            Log.d("RTCAudioManager", "Audio focus request granted for VOICE_CALL streams");
        } else {
            Log.e("RTCAudioManager", "Audio focus request failed");
        }
        Log.d("RTCAudioManager", "[start] setMode : " + i9);
        this.f5518b.setMode(i9);
        m5064o(false);
        AudioDevice audioDevice = AudioDevice.NONE;
        this.f5527k = audioDevice;
        this.f5526j = audioDevice;
        this.f5531o.clear();
        this.f5530n.m5096u();
        m5070u();
        m5061l(this.f5532p, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        Log.d("RTCAudioManager", "AudioManager started");
        return this.f5521e;
    }

    /* renamed from: s */
    public int m5068s() {
        Log.d("RTCAudioManager", "stop");
        ThreadUtils.checkIsOnMainThread();
        if (this.f5520d != AudioManagerState.RUNNING) {
            Log.e("RTCAudioManager", "Trying to stop AudioManager in incorrect state: " + this.f5520d);
            return this.f5521e;
        }
        this.f5520d = AudioManagerState.UNINITIALIZED;
        m5069t(this.f5532p);
        this.f5530n.m5100y();
        m5066q(this.f5522f);
        m5064o(this.f5523g);
        Log.d("RTCAudioManager", "[stop] setMode : " + this.f5521e);
        this.f5518b.setMode(this.f5521e);
        this.f5518b.abandonAudioFocus(this.f5533q);
        this.f5533q = null;
        Log.d("RTCAudioManager", "Abandoned audio focus for VOICE_CALL streams");
        C0598a c0598a = this.f5529m;
        if (c0598a != null) {
            c0598a.m3297g();
            this.f5529m = null;
        }
        this.f5519c = null;
        Log.d("RTCAudioManager", "AudioManager stopped");
        return this.f5521e;
    }

    /* renamed from: t */
    public final void m5069t(BroadcastReceiver broadcastReceiver) {
        this.f5517a.unregisterReceiver(broadcastReceiver);
    }

    /* renamed from: u */
    public void m5070u() {
        AudioDevice audioDevice;
        AudioDevice audioDevice2;
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCAudioManager", "--- updateAudioDeviceState: wired headset=" + this.f5524h + ", BT state=" + this.f5530n.m5090o());
        Log.d("RTCAudioManager", "Device status: available=" + this.f5531o + ", selected=" + this.f5526j + ", user selected=" + this.f5527k);
        RTCBluetoothManager.State stateM5090o = this.f5530n.m5090o();
        RTCBluetoothManager.State state = RTCBluetoothManager.State.HEADSET_AVAILABLE;
        if (stateM5090o == state || this.f5530n.m5090o() == RTCBluetoothManager.State.HEADSET_UNAVAILABLE || this.f5530n.m5090o() == RTCBluetoothManager.State.SCO_DISCONNECTING) {
            this.f5530n.m5085C();
        }
        HashSet hashSet = new HashSet();
        RTCBluetoothManager.State stateM5090o2 = this.f5530n.m5090o();
        RTCBluetoothManager.State state2 = RTCBluetoothManager.State.SCO_CONNECTED;
        if (stateM5090o2 == state2 || this.f5530n.m5090o() == RTCBluetoothManager.State.SCO_CONNECTING || this.f5530n.m5090o() == state) {
            hashSet.add(AudioDevice.BLUETOOTH);
        }
        if (this.f5524h) {
            hashSet.add(AudioDevice.WIRED_HEADSET);
        } else {
            hashSet.add(AudioDevice.SPEAKER_PHONE);
            if (m5057h()) {
                hashSet.add(AudioDevice.EARPIECE);
            }
        }
        boolean z8 = true;
        boolean z9 = !this.f5531o.equals(hashSet);
        this.f5531o = hashSet;
        if (this.f5530n.m5090o() == RTCBluetoothManager.State.HEADSET_UNAVAILABLE && this.f5527k == AudioDevice.BLUETOOTH) {
            this.f5527k = AudioDevice.NONE;
        }
        boolean z10 = this.f5524h;
        if (z10 && this.f5527k == AudioDevice.SPEAKER_PHONE) {
            this.f5527k = AudioDevice.WIRED_HEADSET;
        }
        if (!z10 && this.f5527k == AudioDevice.WIRED_HEADSET) {
            this.f5527k = AudioDevice.SPEAKER_PHONE;
        }
        boolean z11 = false;
        boolean z12 = this.f5530n.m5090o() == state && ((audioDevice2 = this.f5527k) == AudioDevice.NONE || audioDevice2 == AudioDevice.BLUETOOTH);
        if ((this.f5530n.m5090o() == state2 || this.f5530n.m5090o() == RTCBluetoothManager.State.SCO_CONNECTING) && (audioDevice = this.f5527k) != AudioDevice.NONE && audioDevice != AudioDevice.BLUETOOTH) {
            z11 = true;
        }
        if (this.f5530n.m5090o() == state || this.f5530n.m5090o() == RTCBluetoothManager.State.SCO_CONNECTING || this.f5530n.m5090o() == state2) {
            Log.d("RTCAudioManager", "Need BT audio: start=" + z12 + ", stop=" + z11 + ", BT state=" + this.f5530n.m5090o());
        }
        if (z11) {
            this.f5530n.m5101z();
            this.f5530n.m5085C();
        }
        if (!z12 || z11 || !this.f5534r || this.f5530n.m5097v()) {
            z8 = z9;
        } else {
            this.f5531o.remove(AudioDevice.BLUETOOTH);
        }
        AudioDevice audioDevice3 = this.f5530n.m5090o() == state2 ? AudioDevice.BLUETOOTH : this.f5524h ? AudioDevice.WIRED_HEADSET : this.f5525i;
        if (audioDevice3 != this.f5526j || z8) {
            m5063n(audioDevice3);
            Log.d("RTCAudioManager", "New device status: available=" + this.f5531o + ", selected=" + audioDevice3);
            InterfaceC1108b interfaceC1108b = this.f5519c;
            if (interfaceC1108b != null) {
                interfaceC1108b.mo5071a(this.f5526j, this.f5531o);
            }
        }
        Log.d("RTCAudioManager", "--- updateAudioDeviceState done");
    }
}
