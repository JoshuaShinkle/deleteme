package com.cyberlink.clrtc.rtc;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.List;
import java.util.Set;
import org.webrtc.ThreadUtils;
import p023c2.C0717a;

/* loaded from: classes.dex */
public class RTCBluetoothManager {

    /* renamed from: a */
    public final Context f5547a;

    /* renamed from: b */
    public final RTCAudioManager f5548b;

    /* renamed from: c */
    public final AudioManager f5549c;

    /* renamed from: d */
    public final Handler f5550d;

    /* renamed from: e */
    public int f5551e;

    /* renamed from: f */
    public State f5552f;

    /* renamed from: g */
    public final BluetoothProfile.ServiceListener f5553g;

    /* renamed from: h */
    public BluetoothAdapter f5554h;

    /* renamed from: i */
    public BluetoothHeadset f5555i;

    /* renamed from: j */
    public BluetoothDevice f5556j;

    /* renamed from: k */
    public final BroadcastReceiver f5557k;

    /* renamed from: l */
    public final Runnable f5558l = new RunnableC1110a();

    public enum State {
        UNINITIALIZED,
        ERROR,
        HEADSET_UNAVAILABLE,
        HEADSET_AVAILABLE,
        SCO_DISCONNECTING,
        SCO_CONNECTING,
        SCO_CONNECTED
    }

    /* renamed from: com.cyberlink.clrtc.rtc.RTCBluetoothManager$a */
    public class RunnableC1110a implements Runnable {
        public RunnableC1110a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RTCBluetoothManager.this.m5086j();
        }
    }

    /* renamed from: com.cyberlink.clrtc.rtc.RTCBluetoothManager$b */
    public class C1111b extends BroadcastReceiver {
        public C1111b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (RTCBluetoothManager.this.f5552f == State.UNINITIALIZED) {
                return;
            }
            String action = intent.getAction();
            if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                Log.d("RTCBluetoothManager", "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_CONNECTION_STATE_CHANGED, s=" + RTCBluetoothManager.this.m5099x(intExtra) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + RTCBluetoothManager.this.f5552f);
                if (intExtra == 2) {
                    RTCBluetoothManager rTCBluetoothManager = RTCBluetoothManager.this;
                    rTCBluetoothManager.f5551e = 0;
                    rTCBluetoothManager.m5084B();
                } else if (intExtra != 1 && intExtra != 3 && intExtra == 0) {
                    RTCBluetoothManager.this.m5101z();
                    RTCBluetoothManager.this.m5084B();
                }
            } else if (action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
                Log.d("RTCBluetoothManager", "BluetoothHeadsetBroadcastReceiver.onReceive: a=ACTION_AUDIO_STATE_CHANGED, s=" + RTCBluetoothManager.this.m5099x(intExtra2) + ", sb=" + isInitialStickyBroadcast() + ", BT state: " + RTCBluetoothManager.this.f5552f);
                if (intExtra2 == 12) {
                    RTCBluetoothManager.this.m5087k();
                    if (RTCBluetoothManager.this.f5552f == State.SCO_CONNECTING) {
                        Log.d("RTCBluetoothManager", "+++ Bluetooth audio SCO is now connected");
                        RTCBluetoothManager.this.f5552f = State.SCO_CONNECTED;
                        RTCBluetoothManager rTCBluetoothManager2 = RTCBluetoothManager.this;
                        rTCBluetoothManager2.f5551e = 0;
                        rTCBluetoothManager2.m5084B();
                    } else {
                        Log.w("RTCBluetoothManager", "Unexpected state BluetoothHeadset.STATE_AUDIO_CONNECTED");
                    }
                } else if (intExtra2 == 11) {
                    Log.d("RTCBluetoothManager", "+++ Bluetooth audio SCO is now connecting...");
                } else if (intExtra2 == 10) {
                    Log.d("RTCBluetoothManager", "+++ Bluetooth audio SCO is now disconnected");
                    if (isInitialStickyBroadcast()) {
                        Log.d("RTCBluetoothManager", "Ignore STATE_AUDIO_DISCONNECTED initial sticky broadcast.");
                        return;
                    } else {
                        RTCBluetoothManager.this.f5548b.m5054e().mo5072b();
                        RTCBluetoothManager.this.m5084B();
                    }
                }
            }
            Log.d("RTCBluetoothManager", "onReceive done: BT state=" + RTCBluetoothManager.this.f5552f);
        }

        public /* synthetic */ C1111b(RTCBluetoothManager rTCBluetoothManager, RunnableC1110a runnableC1110a) {
            this();
        }
    }

    /* renamed from: com.cyberlink.clrtc.rtc.RTCBluetoothManager$c */
    public class C1112c implements BluetoothProfile.ServiceListener {
        public C1112c() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i9, BluetoothProfile bluetoothProfile) {
            if (i9 != 1 || RTCBluetoothManager.this.f5552f == State.UNINITIALIZED) {
                return;
            }
            Log.d("RTCBluetoothManager", "BluetoothServiceListener.onServiceConnected: BT state=" + RTCBluetoothManager.this.f5552f);
            RTCBluetoothManager.this.f5555i = (BluetoothHeadset) bluetoothProfile;
            RTCBluetoothManager.this.m5084B();
            Log.d("RTCBluetoothManager", "onServiceConnected done: BT state=" + RTCBluetoothManager.this.f5552f);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i9) {
            if (i9 != 1 || RTCBluetoothManager.this.f5552f == State.UNINITIALIZED) {
                return;
            }
            Log.d("RTCBluetoothManager", "BluetoothServiceListener.onServiceDisconnected: BT state=" + RTCBluetoothManager.this.f5552f);
            RTCBluetoothManager.this.m5101z();
            RTCBluetoothManager.this.f5555i = null;
            RTCBluetoothManager.this.f5556j = null;
            RTCBluetoothManager.this.f5552f = State.HEADSET_UNAVAILABLE;
            RTCBluetoothManager.this.m5084B();
            Log.d("RTCBluetoothManager", "onServiceDisconnected done: BT state=" + RTCBluetoothManager.this.f5552f);
        }

        public /* synthetic */ C1112c(RTCBluetoothManager rTCBluetoothManager, RunnableC1110a runnableC1110a) {
            this();
        }
    }

    public RTCBluetoothManager(Context context, RTCAudioManager rTCAudioManager) {
        Log.d("RTCBluetoothManager", "ctor");
        ThreadUtils.checkIsOnMainThread();
        this.f5547a = context;
        this.f5548b = rTCAudioManager;
        this.f5549c = m5088m(context);
        this.f5552f = State.UNINITIALIZED;
        RunnableC1110a runnableC1110a = null;
        this.f5553g = new C1112c(this, runnableC1110a);
        this.f5557k = new C1111b(this, runnableC1110a);
        this.f5550d = new Handler(Looper.getMainLooper());
    }

    /* renamed from: l */
    public static RTCBluetoothManager m5082l(Context context, RTCAudioManager rTCAudioManager) {
        Log.d("RTCBluetoothManager", "create" + C0717a.m3547b());
        return new RTCBluetoothManager(context, rTCAudioManager);
    }

    /* renamed from: A */
    public void m5083A(BroadcastReceiver broadcastReceiver) {
        try {
            this.f5547a.unregisterReceiver(broadcastReceiver);
        } catch (Exception e9) {
            Log.d("RTCBluetoothManager", "[unregisterReceiver] Error: " + e9);
        }
    }

    /* renamed from: B */
    public final void m5084B() {
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCBluetoothManager", "updateAudioDeviceState");
        this.f5548b.m5070u();
    }

    /* renamed from: C */
    public void m5085C() {
        if (this.f5552f == State.UNINITIALIZED || this.f5555i == null) {
            return;
        }
        Log.d("RTCBluetoothManager", "updateDevice");
        List<BluetoothDevice> connectedDevices = this.f5555i.getConnectedDevices();
        if (connectedDevices.isEmpty()) {
            this.f5556j = null;
            this.f5552f = State.HEADSET_UNAVAILABLE;
            Log.d("RTCBluetoothManager", "No connected bluetooth headset");
        } else {
            this.f5556j = connectedDevices.get(0);
            this.f5552f = State.HEADSET_AVAILABLE;
            Log.d("RTCBluetoothManager", "Connected bluetooth headset: name=" + this.f5556j.getName() + ", state=" + m5099x(this.f5555i.getConnectionState(this.f5556j)) + ", SCO audio=" + this.f5555i.isAudioConnected(this.f5556j));
        }
        Log.d("RTCBluetoothManager", "updateDevice done: BT state=" + this.f5552f);
    }

    /* renamed from: j */
    public final void m5086j() {
        boolean z8;
        ThreadUtils.checkIsOnMainThread();
        if (this.f5552f == State.UNINITIALIZED || this.f5555i == null) {
            return;
        }
        Log.d("RTCBluetoothManager", "bluetoothTimeout: BT state=" + this.f5552f + ", attempts: " + this.f5551e + ", SCO is on: " + m5093r());
        if (this.f5552f != State.SCO_CONNECTING) {
            return;
        }
        List<BluetoothDevice> connectedDevices = this.f5555i.getConnectedDevices();
        if (connectedDevices.size() > 0) {
            BluetoothDevice bluetoothDevice = connectedDevices.get(0);
            this.f5556j = bluetoothDevice;
            if (this.f5555i.isAudioConnected(bluetoothDevice)) {
                Log.d("RTCBluetoothManager", "SCO connected with " + this.f5556j.getName());
                z8 = true;
            } else {
                Log.d("RTCBluetoothManager", "SCO is not connected with " + this.f5556j.getName());
                z8 = false;
            }
        } else {
            z8 = false;
        }
        if (z8) {
            this.f5552f = State.SCO_CONNECTED;
            this.f5551e = 0;
        } else {
            Log.w("RTCBluetoothManager", "BT failed to connect after timeout");
            m5101z();
        }
        m5084B();
        Log.d("RTCBluetoothManager", "bluetoothTimeout done: BT state=" + this.f5552f);
    }

    /* renamed from: k */
    public final void m5087k() {
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCBluetoothManager", "cancelTimer");
        this.f5550d.removeCallbacks(this.f5558l);
    }

    /* renamed from: m */
    public AudioManager m5088m(Context context) {
        return (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
    }

    /* renamed from: n */
    public boolean m5089n(Context context, BluetoothProfile.ServiceListener serviceListener, int i9) {
        return this.f5554h.getProfileProxy(context, serviceListener, i9);
    }

    /* renamed from: o */
    public State m5090o() {
        ThreadUtils.checkIsOnMainThread();
        return this.f5552f;
    }

    /* renamed from: p */
    public boolean m5091p(Context context, String str) {
        return this.f5547a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* renamed from: q */
    public boolean m5092q() {
        BluetoothAdapter bluetoothAdapter = this.f5554h;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled() && this.f5554h.getProfileConnectionState(1) == 2;
    }

    /* renamed from: r */
    public final boolean m5093r() {
        return this.f5549c.isBluetoothScoOn();
    }

    /* renamed from: s */
    public void m5094s(BluetoothAdapter bluetoothAdapter) {
        Log.d("RTCBluetoothManager", "BluetoothAdapter: enabled=" + bluetoothAdapter.isEnabled() + ", state=" + m5099x(bluetoothAdapter.getState()) + ", name=" + bluetoothAdapter.getName() + ", address=" + bluetoothAdapter.getAddress());
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()) {
            return;
        }
        Log.d("RTCBluetoothManager", "paired devices:");
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            Log.d("RTCBluetoothManager", " name=" + bluetoothDevice.getName() + ", address=" + bluetoothDevice.getAddress());
        }
    }

    /* renamed from: t */
    public void m5095t(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.f5547a.registerReceiver(broadcastReceiver, intentFilter);
    }

    /* renamed from: u */
    public void m5096u() {
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCBluetoothManager", TtmlNode.START);
        if (!m5091p(this.f5547a, "android.permission.BLUETOOTH")) {
            Log.w("RTCBluetoothManager", "Process (pid=" + Process.myPid() + ") lacks BLUETOOTH permission");
            return;
        }
        if (this.f5552f != State.UNINITIALIZED) {
            Log.w("RTCBluetoothManager", "Invalid BT state");
            return;
        }
        this.f5555i = null;
        this.f5556j = null;
        this.f5551e = 0;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.f5554h = defaultAdapter;
        if (defaultAdapter == null) {
            Log.w("RTCBluetoothManager", "Device does not support Bluetooth");
            return;
        }
        if (!this.f5549c.isBluetoothScoAvailableOffCall()) {
            Log.e("RTCBluetoothManager", "Bluetooth SCO audio is not available off call");
            return;
        }
        m5094s(this.f5554h);
        if (!m5089n(this.f5547a, this.f5553g, 1)) {
            Log.e("RTCBluetoothManager", "BluetoothAdapter.getProfileProxy(HEADSET) failed");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        m5095t(this.f5557k, intentFilter);
        Log.d("RTCBluetoothManager", "HEADSET profile state: " + m5099x(this.f5554h.getProfileConnectionState(1)));
        Log.d("RTCBluetoothManager", "Bluetooth proxy for headset profile has started");
        this.f5552f = State.HEADSET_UNAVAILABLE;
        Log.d("RTCBluetoothManager", "start done: BT state=" + this.f5552f);
    }

    /* renamed from: v */
    public boolean m5097v() {
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCBluetoothManager", "startSco: BT state=" + this.f5552f + ", attempts: " + this.f5551e + ", SCO is on: " + m5093r());
        if (this.f5551e >= 2) {
            Log.e("RTCBluetoothManager", "BT SCO connection fails - no more attempts");
            return false;
        }
        if (this.f5552f != State.HEADSET_AVAILABLE) {
            Log.e("RTCBluetoothManager", "BT SCO connection fails - no headset available");
            return false;
        }
        Log.d("RTCBluetoothManager", "Starting Bluetooth SCO and waits for ACTION_AUDIO_STATE_CHANGED...");
        this.f5552f = State.SCO_CONNECTING;
        this.f5549c.startBluetoothSco();
        this.f5551e++;
        m5098w();
        Log.d("RTCBluetoothManager", "startScoAudio done: BT state=" + this.f5552f);
        return true;
    }

    /* renamed from: w */
    public final void m5098w() {
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCBluetoothManager", "startTimer");
        this.f5550d.postDelayed(this.f5558l, 4000L);
    }

    /* renamed from: x */
    public final String m5099x(int i9) {
        if (i9 == 0) {
            return "DISCONNECTED";
        }
        if (i9 == 1) {
            return "CONNECTING";
        }
        if (i9 == 2) {
            return "CONNECTED";
        }
        if (i9 == 3) {
            return "DISCONNECTING";
        }
        switch (i9) {
            case 10:
                return "OFF";
            case 11:
                return "TURNING_ON";
            case 12:
                return "ON";
            case 13:
                return "TURNING_OFF";
            default:
                return "INVALID";
        }
    }

    /* renamed from: y */
    public void m5100y() {
        ThreadUtils.checkIsOnMainThread();
        m5083A(this.f5557k);
        Log.d("RTCBluetoothManager", "stop: BT state=" + this.f5552f);
        if (this.f5554h != null) {
            m5101z();
            State state = this.f5552f;
            State state2 = State.UNINITIALIZED;
            if (state != state2) {
                m5087k();
                BluetoothHeadset bluetoothHeadset = this.f5555i;
                if (bluetoothHeadset != null) {
                    this.f5554h.closeProfileProxy(1, bluetoothHeadset);
                    this.f5555i = null;
                }
                this.f5554h = null;
                this.f5556j = null;
                this.f5552f = state2;
            }
        }
        Log.d("RTCBluetoothManager", "stop done: BT state=" + this.f5552f);
    }

    /* renamed from: z */
    public void m5101z() {
        ThreadUtils.checkIsOnMainThread();
        Log.d("RTCBluetoothManager", "stopScoAudio: BT state=" + this.f5552f + ", SCO is on: " + m5093r());
        State state = this.f5552f;
        if (state == State.SCO_CONNECTING || state == State.SCO_CONNECTED) {
            m5087k();
            this.f5549c.stopBluetoothSco();
            this.f5552f = State.SCO_DISCONNECTING;
            Log.d("RTCBluetoothManager", "stopScoAudio done: BT state=" + this.f5552f);
        }
    }
}
