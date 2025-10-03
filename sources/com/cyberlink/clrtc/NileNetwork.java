package com.cyberlink.clrtc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Size;
import android.util.SparseArray;
import android.view.WindowManager;
import androidx.annotation.Keep;
import com.cyberlink.clrtc.C1090p7;
import com.cyberlink.clrtc.NetQuality;
import com.cyberlink.clrtc.model.ActiveMedia;
import com.cyberlink.clrtc.model.ChatMsg;
import com.cyberlink.clrtc.rtc.RTCAudioManager;
import com.cyberlink.clrtc.voe.AudioMgr;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.Logging;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoRenderer;
import p003a2.C0011a;
import p003a2.C0012b;
import p023c2.C0718b;
import p044d2.C4664a;
import p044d2.C4665b;
import p209u2.AbstractC6381r;
import p209u2.C6382s;
import p209u2.ThreadFactoryC6373j;

/* loaded from: classes.dex */
public class NileNetwork implements NileNativeCallback, C1090p7.a {

    /* renamed from: F */
    public static final AtomicInteger f4908F = new AtomicInteger();

    /* renamed from: G */
    public static final ExecutorService f4909G = Executors.newSingleThreadExecutor(new ThreadFactoryC6373j("JNile_worker"));

    /* renamed from: d */
    public String[] f4918d;

    /* renamed from: e */
    public final Logger f4919e;

    /* renamed from: f */
    public final NetQuality f4920f;

    /* renamed from: g */
    public final EglBase.Context f4921g;

    /* renamed from: h */
    public final Context f4922h;

    /* renamed from: m */
    public final C1090p7 f4927m;

    /* renamed from: n */
    public final BroadcastReceiver f4928n;

    @Keep
    private final long nativeNileClient;

    /* renamed from: w */
    public C0956b f4937w;

    /* renamed from: a */
    public volatile boolean f4915a = false;

    /* renamed from: b */
    public List<String> f4916b = new ArrayList();

    /* renamed from: c */
    public List<String> f4917c = new ArrayList();

    /* renamed from: i */
    public final Handler f4923i = new Handler(Looper.getMainLooper());

    /* renamed from: j */
    public final AtomicLong f4924j = new AtomicLong();

    /* renamed from: k */
    public InterfaceC1113s f4925k = C1010h.f5225a;

    /* renamed from: l */
    public InterfaceC1081o7 f4926l = C1010h.f5226b;

    /* renamed from: o */
    public final SparseArray<C0011a> f4929o = new SparseArray<>();

    /* renamed from: p */
    @SuppressLint({"UseSparseArrays"})
    public final HashMap<Integer, C0012b> f4930p = new HashMap<>();

    /* renamed from: q */
    @SuppressLint({"UseSparseArrays"})
    public final HashMap<Integer, ActiveMedia> f4931q = new HashMap<>();

    /* renamed from: r */
    public final HashSet<VideoRenderer> f4932r = new HashSet<>();

    /* renamed from: s */
    public VideoRenderer f4933s = null;

    /* renamed from: t */
    public VideoRenderer f4934t = null;

    /* renamed from: u */
    public final HashSet<SurfaceViewRenderer> f4935u = new HashSet<>();

    /* renamed from: v */
    public C0947a f4936v = null;

    /* renamed from: x */
    public C0012b f4938x = null;

    /* renamed from: y */
    public C0012b f4939y = null;

    /* renamed from: z */
    public C1019i f4940z = C1019i.f5246i;

    /* renamed from: A */
    public final SparseArray<ChatMsg> f4910A = new SparseArray<>();

    /* renamed from: B */
    public final SparseArray<HashSet<ChatMsg>> f4911B = new SparseArray<>();

    /* renamed from: C */
    public final C0946c f4912C = new C0946c(null);

    /* renamed from: D */
    public DisplayMode f4913D = DisplayMode.SPEAKER;

    /* renamed from: E */
    public long f4914E = Long.MIN_VALUE;

    public enum BackToWaitingRoomStatus {
        SUCCESS(0),
        INVALID_USER(1),
        IS_HOST(2),
        OLD_USER(3);

        private final int value;

        BackToWaitingRoomStatus(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static BackToWaitingRoomStatus m4979a(int i9) {
            for (BackToWaitingRoomStatus backToWaitingRoomStatus : values()) {
                if (backToWaitingRoomStatus.value == i9) {
                    return backToWaitingRoomStatus;
                }
            }
            return SUCCESS;
        }
    }

    public enum DisplayMode {
        GALLERY(0),
        SPEAKER(1),
        DESKTOP(2);

        private final int value;

        DisplayMode(int i9) {
            this.value = i9;
        }

        /* renamed from: b */
        public static DisplayMode m4981b(int i9) {
            for (DisplayMode displayMode : values()) {
                if (displayMode.value == i9) {
                    return displayMode;
                }
            }
            return SPEAKER;
        }
    }

    public enum EventType {
        WEBINAR(0),
        MEETING(1),
        PANELIST_HOST(2),
        PANELIST_CLIENT(3),
        VOICE_CALL(4),
        GROUP_CALL(5),
        S_MEETING(6),
        S_VOICE_CALL(7),
        S_GROUP_CALL(8),
        WEBINAR_ASKING_HOST(9),
        WEBINAR_ASKING_VIEWER(10);

        private final int value;

        EventType(int i9) {
            this.value = i9;
        }
    }

    public enum ForwardOptionAction {
        NONE(0),
        RAISE_HAND(10001),
        MUTE_ANOTHER_MIC(10002),
        LOCK_DESKTOP_SHARING(10003),
        LOCK_RECORDING(10004),
        LOCK_CHAT_ROOM(10005),
        UNKNOWN(999);

        private final int value;

        ForwardOptionAction(int i9) {
            this.value = i9;
        }

        /* renamed from: b */
        public static ForwardOptionAction m4984b(int i9) {
            for (ForwardOptionAction forwardOptionAction : values()) {
                if (forwardOptionAction.value == i9) {
                    return forwardOptionAction;
                }
            }
            return UNKNOWN;
        }
    }

    public enum HostControlStatus {
        SUCCESS(0),
        INVALID_USER(1),
        OLD_USER(2);

        private final int value;

        HostControlStatus(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static HostControlStatus m4985a(int i9) {
            for (HostControlStatus hostControlStatus : values()) {
                if (hostControlStatus.value == i9) {
                    return hostControlStatus;
                }
            }
            return SUCCESS;
        }
    }

    public enum ParticipantState {
        ParticipantState_Unknown(-1),
        ParticipantState_Pre_Join(0),
        ParticipantState_Joined(1),
        ParticipantState_Lobby(2);

        private final int value;

        ParticipantState(int i9) {
            this.value = i9;
        }

        /* renamed from: b */
        public static ParticipantState m4987b(int i9) {
            for (ParticipantState participantState : values()) {
                if (participantState.value == i9) {
                    return participantState;
                }
            }
            return ParticipantState_Unknown;
        }
    }

    public enum PhoneLineInviteResult {
        PhoneLine_Invite_NoResult(-1),
        PhoneLine_Invite_Answered(0),
        PhoneLine_Invite_Busy(1),
        PhoneLine_Invite_Reject(2),
        PhoneLine_Invite_Error(3),
        PhoneLine_Invite_PreJoinOK(4),
        PhoneLine_Invite_AnotherDevicePickup(5),
        PhoneLine_Invite_NormalUserLeave(6),
        PhoneLine_Invite_Ringing(7);

        private final int value;

        PhoneLineInviteResult(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static PhoneLineInviteResult m4988a(int i9) {
            for (PhoneLineInviteResult phoneLineInviteResult : values()) {
                if (phoneLineInviteResult.value == i9) {
                    return phoneLineInviteResult;
                }
            }
            return PhoneLine_Invite_NoResult;
        }
    }

    public enum PlatformType {
        Platform_Unknown(0),
        Platform_Windows(1),
        Platform_MacOS(2),
        Platform_Android(3),
        Platform_iPhone(4),
        Platform_PhoneLine(5);

        private final int value;

        PlatformType(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static PlatformType m4989a(int i9) {
            for (PlatformType platformType : values()) {
                if (platformType.value == i9) {
                    return platformType;
                }
            }
            return Platform_Unknown;
        }
    }

    public enum PreJoinUserStatus {
        ONLINE(1),
        ANSWERED(2),
        UNKNOWN(999);

        private final int value;

        PreJoinUserStatus(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static PreJoinUserStatus m4990a(int i9) {
            for (PreJoinUserStatus preJoinUserStatus : values()) {
                if (preJoinUserStatus.value == i9) {
                    return preJoinUserStatus;
                }
            }
            return UNKNOWN;
        }
    }

    public enum RTCError {
        OK(0),
        UNKNOWN(-1),
        WEBCAM(-2),
        DESKTOP_CAPTURER(-3),
        MICROPHONE(-4),
        REMOTE_VIDEO(-5),
        RELAY_SERVER(-20),
        LOW_UPLOAD_BANDWIDTH(-21),
        LOW_DOWNLOAD_BANDWIDTH(-22);

        private final int code;

        RTCError(int i9) {
            this.code = i9;
        }

        /* renamed from: a */
        public static RTCError m4991a(int i9) {
            for (RTCError rTCError : values()) {
                if (rTCError.code == i9) {
                    return rTCError;
                }
            }
            return UNKNOWN;
        }
    }

    public enum Status {
        WAITINGROOM(-6),
        START_STREAMING(-5),
        RESUME_STREAMING(-4),
        RECONNECTING_TO_SERVER(-3),
        JOINED(-2),
        NOT_JOINED(-1),
        RESERVED(0),
        JOIN_NEED_MORE_INFO(1),
        MSERVER_REJECT(2),
        REACH_LIMIT(3),
        SERVER_BUSY(4),
        SSERVER_UNAVAILABLE(5),
        VERSION_UNSYNC(6),
        EVENT_NOT_READY(7),
        EVENT_CLOSED(8),
        EVENT_ABNORMAL(9),
        SSERVER_REJECT(10),
        SSERVER_ABNORMAL(11),
        SSERVER_TIMEOUT(12),
        RTC_CREATE_LOCAL_SDP_FAIL(13),
        RTC_SET_LOCAL_SDP_FAIL(14),
        RTC_SET_REMOTE_SDP_FAIL(15),
        RTC_CONNECT_FAIL(16),
        RTC_UNKNOWN_ERROR(17),
        MSERVER_DISCONNECTED(18),
        SERVER_UPDATE(19),
        INVALID_JOIN_TOKEN(20),
        INVALID_JOIN_TYPE(21),
        ERROR_STOPS(22),
        MSERVER_UNREACHABLE(23),
        UNKNOWN(999);

        private final int value;

        Status(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static Status m4992a(int i9) {
            for (Status status : values()) {
                if (status.value == i9) {
                    return status;
                }
            }
            return UNKNOWN;
        }
    }

    public enum StopDTStatus {
        SUCCESS(0),
        OLD_USER(1),
        NO_SHARING(2);

        private final int value;

        StopDTStatus(int i9) {
            this.value = i9;
        }

        /* renamed from: a */
        public static StopDTStatus m4993a(int i9) {
            for (StopDTStatus stopDTStatus : values()) {
                if (stopDTStatus.value == i9) {
                    return stopDTStatus;
                }
            }
            return SUCCESS;
        }
    }

    /* renamed from: com.cyberlink.clrtc.NileNetwork$a */
    public class C0944a extends BroadcastReceiver {
        public C0944a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NileNetwork.this.m4971v8();
        }
    }

    /* renamed from: com.cyberlink.clrtc.NileNetwork$b */
    public static /* synthetic */ class C0945b {

        /* renamed from: a */
        public static final /* synthetic */ int[] f5047a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f5048b;

        /* renamed from: c */
        public static final /* synthetic */ int[] f5049c;

        static {
            int[] iArr = new int[PreJoinUserStatus.values().length];
            f5049c = iArr;
            try {
                iArr[PreJoinUserStatus.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5049c[PreJoinUserStatus.ANSWERED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[NileError.values().length];
            f5048b = iArr2;
            try {
                iArr2[NileError.CALL_CALLEE_TIMEOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr3 = new int[ForwardOptionAction.values().length];
            f5047a = iArr3;
            try {
                iArr3[ForwardOptionAction.RAISE_HAND.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5047a[ForwardOptionAction.MUTE_ANOTHER_MIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5047a[ForwardOptionAction.LOCK_DESKTOP_SHARING.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5047a[ForwardOptionAction.LOCK_CHAT_ROOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5047a[ForwardOptionAction.LOCK_RECORDING.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5047a[ForwardOptionAction.NONE.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    static {
        System.loadLibrary("c++_shared");
        System.loadLibrary("DO");
    }

    public NileNetwork(Context context, EglBase.Context context2, File file, int i9, boolean z8) throws PackageManager.NameNotFoundException {
        long jLongValue;
        this.f4937w = null;
        Logger logger = new Logger(context, file);
        this.f4919e = logger;
        this.f4920f = new NetQuality();
        this.f4922h = context;
        this.f4921g = context2;
        this.f4937w = null;
        long jCurrentTimeMillis = System.currentTimeMillis();
        final String strM4964s3 = m4964s3(context);
        try {
            jLongValue = ((Long) f4909G.submit(new Callable() { // from class: com.cyberlink.clrtc.s5
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f5578b.m4544I6(strM4964s3);
                }
            }).get()).longValue();
            logger.m4458g("JNile", " > native instance instantiated, took " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        } catch (Exception unused) {
            jLongValue = 0;
        }
        this.nativeNileClient = jLongValue;
        if (jLongValue == 0) {
            this.f4919e.m4459h("JNile", "Cannot load Nile native libraries");
            throw new IllegalStateException("Cannot load Nile native libraries");
        }
        this.f4927m = new C1090p7(context, this.f4923i, this);
        AudioMgr.setBlacklistDeviceForOpenSLESUsage(true);
        C4665b.m18664p(i9);
        C4665b.m18665q(z8);
        m4961q8(C1121t.m5157f(), C1121t.m5152a(), C1121t.m5153b());
        m4963r8(C1121t.m5158g(), C1121t.m5154c());
        BroadcastReceiver broadcastReceiverM4898H7 = m4898H7();
        this.f4928n = broadcastReceiverM4898H7;
        this.f4922h.registerReceiver(broadcastReceiverM4898H7, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        m4971v8();
        m4952m3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A4 */
    public /* synthetic */ void m4480A4(int i9, int i10) {
        this.f4925k.mo5112N(new C6382s(i9, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A5 */
    public /* synthetic */ void m4481A5(HostControlStatus hostControlStatus, int i9) {
        this.f4925k.mo5109H0(hostControlStatus, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A6 */
    public /* synthetic */ void m4482A6(int[] iArr, ForwardOptionAction forwardOptionAction, boolean z8) {
        String[] strArr;
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > forwardGlobalAddOrRemove, nile network has been released");
            return;
        }
        int[] iArr2 = new int[0];
        if (iArr.length > 0) {
            String[] strArr2 = new String[iArr.length];
            for (int i9 = 0; i9 < iArr.length; i9++) {
                strArr2[i9] = String.valueOf(iArr[i9]);
            }
            strArr = strArr2;
        } else {
            strArr = new String[0];
        }
        nForwardOption(iArr2, forwardOptionAction.value, strArr, z8 ? 1 : 2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A7 */
    public /* synthetic */ void m4483A7() {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > stopDesktopSharing, nile network has been released");
        } else {
            if (nStopDesktopSharing()) {
                return;
            }
            this.f4919e.m4468u("JNile", " > stopDesktopSharing failed");
        }
    }

    /* renamed from: B3 */
    public static long m4488B3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, calendar.get(1) + 2000);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B4 */
    public /* synthetic */ void m4489B4() {
        this.f4925k.mo5119b(nIsWebcamEnabled(), nIsMicrophoneEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B5 */
    public /* synthetic */ void m4490B5(int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnRemoveHostAck run");
        if (m4901I7()) {
            return;
        }
        final HostControlStatus hostControlStatusM4985a = HostControlStatus.m4985a(i9);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.b0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5080b.m4481A5(hostControlStatusM4985a, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B6 */
    public /* synthetic */ void m4491B6(ForwardOptionAction forwardOptionAction, boolean z8) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > forwardGlobalSwitch, nile network has been released");
            return;
        }
        nForwardOption(new int[0], forwardOptionAction.value, ((forwardOptionAction == ForwardOptionAction.LOCK_RECORDING || forwardOptionAction == ForwardOptionAction.MUTE_ANOTHER_MIC) && !z8) ? new String[]{"0"} : new String[0], 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C4 */
    public /* synthetic */ void m4497C4() {
        this.f4919e.m4467t("JNile", " > OnDeviceChanged run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.k2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5320b.m4489B4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C5 */
    public /* synthetic */ void m4498C5(int i9, int i10) {
        this.f4925k.OnRemoveHostNotify(i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C7 */
    public /* synthetic */ void m4500C7(DisplayMode displayMode, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > [WORKER] switchDisplayMode, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > switchDisplayMode run");
        if (!m4894G3() && nSetDisplayMode(displayMode.ordinal())) {
            this.f4919e.m4467t("JNile", " > switchDisplayMode[" + displayMode + "] successfully");
            this.f4913D = displayMode;
        } else {
            this.f4919e.m4468u("JNile", " > switchDisplayMode[" + displayMode + "] failed");
        }
        final DisplayMode displayMode2 = this.f4913D;
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.u3
                @Override // java.lang.Runnable
                public final void run() {
                    abstractC6381r.m24506d(displayMode2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D4 */
    public /* synthetic */ void m4505D4(int i9) {
        this.f4925k.m5151z0(DisplayMode.m4981b(nGetDisplayMode()), i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D5 */
    public /* synthetic */ void m4506D5(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnRemoveHostNotify run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.b7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5110b.m4498C5(i9, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D6 */
    public /* synthetic */ void m4507D6(final AbstractC6381r abstractC6381r) {
        final ArrayList arrayList = new ArrayList(this.f4930p.values());
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.x
            @Override // java.lang.Runnable
            public final void run() {
                abstractC6381r.m24506d(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D7 */
    public /* synthetic */ void m4508D7(boolean z8) {
        this.f4925k.mo5123e(this.f4916b, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E4 */
    public /* synthetic */ void m4513E4(final int i9) {
        this.f4919e.m4467t("JNile", " > OnDisplayModeChanged run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.n2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5404b.m4505D4(i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E5 */
    public /* synthetic */ void m4514E5(C0012b c0012b) {
        this.f4925k.mo5140r(c0012b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E6 */
    public /* synthetic */ void m4515E6(HashSet hashSet) {
        this.f4925k.mo5115Q(hashSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E7 */
    public /* synthetic */ void m4516E7(C0012b c0012b, List list) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > verifyProperty, nile network has been released");
            return;
        }
        int[] iArr = {c0012b.f66b.f63b};
        String strM85d = c0012b.m85d();
        if (TextUtils.isEmpty(strM85d) || list.isEmpty()) {
            nVerified(iArr, false);
            this.f4919e.m4467t("JNile", " > verifyProperty NG: " + c0012b.f66b.f63b);
            return;
        }
        byte[] bytes = strM85d.getBytes();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (nCFP(((String) it.next()).getBytes(), bytes)) {
                nVerified(iArr, true);
                this.f4919e.m4467t("JNile", " > verifyProperty OK: " + c0012b.f66b.f63b);
                return;
            }
        }
        this.f4919e.m4467t("JNile", " > verifyProperty NG: " + c0012b.f66b.f63b);
        nVerified(iArr, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F4 */
    public /* synthetic */ void m4521F4(List list) {
        this.f4925k.mo5108H(list, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F5 */
    public /* synthetic */ void m4522F5(int i9) {
        this.f4919e.m4467t("JNile", " > OnRequestMuteStatusChanged run");
        if (m4901I7()) {
            return;
        }
        final C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > participant is unavailable, ignore");
        } else if (this.f4938x.m94m()) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.v1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5635b.m4514E5(c0012b);
                }
            });
        } else {
            this.f4919e.m4460i("JNile", " > already allow recording, ignore");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F6 */
    public /* synthetic */ void m4523F6(C1019i c1019i, EventType eventType) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > joinEvent, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > joinEvent run");
        this.f4940z = c1019i;
        nSetActiveNum(this.f4932r.size());
        nSetDisplayMode(DisplayMode.SPEAKER.value);
        long j9 = c1019i.f5249c;
        boolean zNJoinEvent = nJoinEvent(c1019i.f5247a, c1019i.f5248b, j9 > 0 ? String.valueOf(j9) : null, c1019i.f5251e, c1019i.f5250d.getBytes(), c1019i.f5252f, 0, eventType.value, c1019i.f5253g, c1019i.f5254h.getBytes());
        this.f4919e.m4467t("JNile", "join meeting: " + c1019i.f5251e + " with user: " + c1019i.f5250d + ": " + zNJoinEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G4 */
    public /* synthetic */ void m4528G4(int i9, String str) {
        C0012b c0012b;
        this.f4919e.m4467t("JNile", " > OnDisplayNameChanged run");
        if (m4901I7() || (c0012b = this.f4930p.get(Integer.valueOf(i9))) == null || TextUtils.equals(c0012b.m86e(), str)) {
            return;
        }
        this.f4919e.m4458g("JNile", " > old:" + c0012b.m86e() + ", new:" + str);
        c0012b.m72B(str);
        final List listSingletonList = Collections.singletonList(c0012b);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.g7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5223b.m4521F4(listSingletonList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G5 */
    public /* synthetic */ void m4529G5() {
        this.f4925k.m5143u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G6 */
    public /* synthetic */ void m4530G6() {
        this.f4919e.m4467t("JNile", " > leaveMeeting run");
        boolean zNLeaveEvent = nLeaveEvent();
        this.f4910A.clear();
        this.f4911B.clear();
        this.f4912C.m4994a();
        this.f4929o.clear();
        this.f4930p.clear();
        this.f4938x = null;
        this.f4914E = Long.MIN_VALUE;
        m4950l3();
        C0947a c0947a = this.f4936v;
        if (c0947a != null) {
            c0947a.m4444s();
        }
        if (zNLeaveEvent) {
            this.f4919e.m4467t("JNile", " > leaveMeeting successfully");
        } else {
            this.f4919e.m4468u("JNile", " > leaveMeeting failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H4 */
    public /* synthetic */ void m4535H4() {
        this.f4925k.OnDowngradeVideoQuality();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H5 */
    public /* synthetic */ void m4536H5(boolean z8) {
        this.f4925k.mo5146w0(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H6 */
    public /* synthetic */ void m4537H6(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > moveToWaitingRoom, nile network has been released");
        } else {
            if (nMoveToWaitingRoom(i9)) {
                return;
            }
            this.f4919e.m4468u("JNile", " > moveToWaitingRoom failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I4 */
    public /* synthetic */ void m4542I4(int i9) {
        this.f4925k.mo5139q(m4974x3(), i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I5 */
    public /* synthetic */ void m4543I5(C0012b c0012b, boolean z8) {
        this.f4925k.mo5120c(c0012b, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I6 */
    public /* synthetic */ Long m4544I6(String str) {
        this.f4919e.m4467t("JNile", " > instantiate native instance");
        this.f4924j.set(Thread.currentThread().getId());
        return Long.valueOf(nCreateNileClient(this.f4922h, this.f4919e, this.f4921g, str, m4869y3(), m4968u3()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J4 */
    public /* synthetic */ void m4549J4(int i9) {
        this.f4925k.mo5145w(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J5 */
    public /* synthetic */ void m4550J5(int i9, final boolean z8) {
        this.f4919e.m4467t("JNile", " > OnRequestMuteStatusChanged run");
        if (m4901I7()) {
            return;
        }
        if (i9 == 0) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.i0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5263b.m4536H5(z8);
                }
            });
            return;
        }
        final C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > participant is unavailable, ignore");
        } else {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.j0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5294b.m4543I5(c0012b, z8);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J6 */
    public /* synthetic */ void m4551J6(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > onVolumeChanged, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > onVolumeChanged run");
        if (nSetSystemVolume(i9)) {
            this.f4919e.m4467t("JNile", " > nSetSystemVolume[" + i9 + "] successfully");
            return;
        }
        this.f4919e.m4468u("JNile", " > nSetSystemVolume[" + i9 + "] failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K4 */
    public /* synthetic */ void m4556K4(final int i9) {
        this.f4919e.m4467t("JNile", " > OnFileUploadCanceled run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.c2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5122b.m4549J4(i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K5 */
    public /* synthetic */ void m4557K5(ArrayList arrayList) {
        this.f4925k.mo5118Z(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K6 */
    public /* synthetic */ void m4558K6(C1019i c1019i) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > preJoinMeeting, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > preJoinMeeting run");
        this.f4940z = c1019i;
        nSetDisplayMode(DisplayMode.SPEAKER.value);
        long j9 = c1019i.f5249c;
        boolean zNPreJoinEvent = nPreJoinEvent(c1019i.f5247a, c1019i.f5248b, j9 > 0 ? String.valueOf(j9) : null, c1019i.f5251e, c1019i.f5250d.getBytes(), c1019i.f5252f, 0, EventType.S_VOICE_CALL.value, c1019i.f5253g, c1019i.f5254h.getBytes());
        this.f4919e.m4467t("JNile", " > eventId:" + c1019i.f5251e + ", user:" + c1019i.f5250d + ": " + zNPreJoinEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L4 */
    public /* synthetic */ void m4563L4(int i9, int i10, String str, int i11, String str2) {
        this.f4925k.mo5149y0(i9, i10, str, i11, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L5 */
    public /* synthetic */ void m4564L5(int[] iArr) {
        this.f4919e.m4467t("JNile", " > OnRequestVerifyUser run");
        final ArrayList arrayList = new ArrayList();
        for (int i9 : iArr) {
            C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
            if (c0012b == null) {
                this.f4919e.m4460i("JNile", " > UID:" + i9 + " is unavailable, skip");
            } else {
                arrayList.add(c0012b);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.k1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5318b.m4557K5(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L6 */
    public /* synthetic */ void m4565L6(String str, String str2, int i9, int i10) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > prepareToFileUpload, nile network has been released");
        } else if (nPrepareToFileUpload(str, str2, i9, i10)) {
            this.f4919e.m4467t("JNile", " > prepareToFileUpload: successfully");
        } else {
            this.f4919e.m4468u("JNile", " > prepareToFileUpload: failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M3 */
    public /* synthetic */ void m4570M3(HostControlStatus hostControlStatus, int i9) {
        this.f4925k.mo5110J(hostControlStatus, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M4 */
    public /* synthetic */ void m4571M4(final int i9, final int i10, final String str, final int i11, final String str2) {
        this.f4919e.m4467t("JNile", " > OnFileUploadCompleted run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.r1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5503b.m4563L4(i9, i10, str, i11, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M5 */
    public /* synthetic */ void m4572M5(int i9, int i10, int i11) {
        this.f4925k.OnRollCallStartNotify(i9, i10, i11);
    }

    /* renamed from: M6 */
    public static /* synthetic */ void m4573M6(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N3 */
    public /* synthetic */ void m4578N3(int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnAddHostAck run");
        if (m4901I7()) {
            return;
        }
        final HostControlStatus hostControlStatusM4985a = HostControlStatus.m4985a(i9);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.n1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5401b.m4570M3(hostControlStatusM4985a, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N4 */
    public /* synthetic */ void m4579N4(int i9, String[] strArr, String[] strArr2, String str) {
        this.f4925k.mo5107G0(i9, strArr, strArr2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N5 */
    public /* synthetic */ void m4580N5(final int i9, final int i10, final int i11) {
        this.f4919e.m4467t("JNile", " > OnRollCallStartNotify run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.z6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5789b.m4572M5(i9, i10, i11);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N6 */
    public /* synthetic */ void m4581N6(int i9, String str, String str2, String str3, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > registerWebinarViewerInfo, nile network has been released");
            return;
        }
        final boolean zNRegisterViewerInfo = nRegisterViewerInfo(i9, str.getBytes(), str2.getBytes(), str3.getBytes());
        if (zNRegisterViewerInfo) {
            this.f4919e.m4467t("JNile", " > registerWebinarViewerInfo successfully");
        } else {
            this.f4919e.m4468u("JNile", " > registerWebinarViewerInfo failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.q3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4573M6(abstractC6381r, zNRegisterViewerInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O3 */
    public /* synthetic */ void m4586O3(int i9, int i10) {
        this.f4925k.OnAddHostNotify(i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O4 */
    public /* synthetic */ void m4587O4(final int i9, final String[] strArr, final String[] strArr2, final String str) {
        this.f4919e.m4467t("JNile", " > OnFileUploadPrepared run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.c1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5117b.m4579N4(i9, strArr, strArr2, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O5 */
    public /* synthetic */ void m4588O5() {
        this.f4925k.OnRollCallStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O6 */
    public /* synthetic */ void m4589O6() {
        this.f4919e.m4467t("JNile", " > release run");
        if (nReleaseNileNetwork()) {
            this.f4919e.m4467t("JNile", " > release successfully");
        } else {
            this.f4919e.m4467t("JNile", " > release failed");
        }
        this.f4923i.removeCallbacksAndMessages(null);
        m4884B8();
        m4886C8();
        this.f4919e.m4462o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P3 */
    public /* synthetic */ void m4594P3(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnAddHostNotify run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.k0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5315b.m4586O3(i9, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P4 */
    public /* synthetic */ void m4595P4(boolean z8) {
        this.f4925k.mo5105E(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P5 */
    public /* synthetic */ void m4596P5() {
        this.f4919e.m4467t("JNile", " > OnRollCallStop run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.n0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5400b.m4588O5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P6 */
    public /* synthetic */ void m4597P6(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > removeHost, nile network has been released");
        } else {
            if (nRemoveHost(i9)) {
                return;
            }
            this.f4919e.m4468u("JNile", " > removeHost failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q3 */
    public /* synthetic */ void m4602Q3(C0012b c0012b, C0012b c0012b2) {
        this.f4925k.mo5102B(c0012b, c0012b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q4 */
    public /* synthetic */ void m4603Q4(boolean z8) {
        this.f4925k.mo5103C0(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q5 */
    public /* synthetic */ void m4604Q5(Status status, int i9) {
        this.f4919e.m4467t("JNile", " > OnStatusChanged run: " + status);
        this.f4925k.mo5124e0(status, i9);
    }

    /* renamed from: Q6 */
    public static /* synthetic */ void m4605Q6(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R3 */
    public /* synthetic */ void m4610R3(int i9, int i10) {
        this.f4919e.m4467t("JNile", " > OnAnUserHadBeenKicked run");
        if (m4901I7()) {
            return;
        }
        final C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > kicker is unavailable, ignore");
        } else {
            final C0012b c0012b2 = this.f4930p.get(Integer.valueOf(i10));
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.j1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5297b.m4602Q3(c0012b, c0012b2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R4 */
    public /* synthetic */ void m4611R4(int i9, int i10, String[] strArr, int i11) {
        final boolean z8;
        ForwardOptionAction forwardOptionActionM4984b = ForwardOptionAction.m4984b(i9);
        this.f4919e.m4467t("JNile", "> OnForwardOptionNotify run: sui: " + i10 + " / o: " + i9 + " / count: " + strArr.length);
        if (m4901I7()) {
            return;
        }
        int i12 = C0945b.f5047a[forwardOptionActionM4984b.ordinal()];
        if (i12 == 1) {
            m4896G8(strArr, i11);
            return;
        }
        if (i12 == 2) {
            z8 = strArr.length == 0;
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.z0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5773b.m4595P4(z8);
                }
            });
        } else {
            if (i12 != 5) {
                return;
            }
            z8 = strArr.length == 0;
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.a1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5060b.m4603Q4(z8);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R5 */
    public /* synthetic */ void m4612R5(StopDTStatus stopDTStatus) {
        this.f4925k.mo5126g(stopDTStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R6 */
    public /* synthetic */ void m4613R6(C0012b c0012b, boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > requestAudioMute, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > requestAudioMute run");
        final boolean zNRequestAudioMute = nRequestAudioMute(c0012b.f66b.f63b, z8);
        if (zNRequestAudioMute) {
            this.f4919e.m4467t("JNile", " > requestAudioMute[" + c0012b.f66b.f63b + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > requestAudioMute[" + c0012b.f66b.f63b + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.s3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4605Q6(abstractC6381r, zNRequestAudioMute);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S3 */
    public /* synthetic */ void m4618S3(String str, String str2) {
        this.f4925k.OnBRBroadcast(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S4 */
    public /* synthetic */ void m4619S4(int i9) {
        this.f4925k.OnHaveBeenKickedout(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S5 */
    public /* synthetic */ void m4620S5(int i9) {
        final StopDTStatus stopDTStatusM4993a = StopDTStatus.m4993a(i9);
        this.f4919e.m4467t("JNile", " > OnStopDTAck run status :" + stopDTStatusM4993a);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.w
            @Override // java.lang.Runnable
            public final void run() {
                this.f5719b.m4612R5(stopDTStatusM4993a);
            }
        });
    }

    /* renamed from: S6 */
    public static /* synthetic */ void m4621S6(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T3 */
    public /* synthetic */ void m4626T3(final String str, final String str2) {
        this.f4919e.m4467t("JNile", " > OnBRBroadcast run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.u0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5612b.m4618S3(str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T4 */
    public /* synthetic */ void m4627T4(final int i9) {
        this.f4919e.m4467t("JNile", " > OnHaveBeenKickedout run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.l7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5349b.m4619S4(i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T5 */
    public /* synthetic */ void m4628T5() {
        this.f4925k.OnStopDTByHost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T6 */
    public /* synthetic */ void m4629T6(C0012b c0012b, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > requestKickUser, nile network has been released");
            return;
        }
        final boolean zNRequestKickUser = nRequestKickUser(c0012b.f66b.f63b);
        if (zNRequestKickUser) {
            this.f4919e.m4467t("JNile", " > requestKickUser[" + c0012b.f66b.f63b + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > requestKickUser[" + c0012b.f66b.f63b + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.v3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4621S6(abstractC6381r, zNRequestKickUser);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U3 */
    public /* synthetic */ void m4634U3(int i9, int i10) {
        this.f4925k.OnBREventTime(i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U4 */
    public /* synthetic */ void m4635U4(boolean z8, boolean z9) {
        C0012b c0012b = this.f4938x;
        if (c0012b != null) {
            this.f4925k.mo5134l0(c0012b, z8, z9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U5 */
    public /* synthetic */ void m4636U5() {
        this.f4919e.m4467t("JNile", " > OnStopDTByHost run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.z1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5775b.m4628T5();
            }
        });
    }

    /* renamed from: U6 */
    public static /* synthetic */ void m4637U6(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V3 */
    public /* synthetic */ void m4642V3(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnBREventTime run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.l0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5334b.m4634U3(i9, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V4 */
    public /* synthetic */ void m4643V4(int i9, final boolean z8, final boolean z9) {
        this.f4919e.m4467t("JNile", " > OnJoinCompleted run");
        this.f4919e.m4464q(this.f4940z, i9);
        C1019i c1019i = this.f4940z;
        C0012b c0012b = new C0012b(i9, c1019i.f5250d, c1019i.f5249c, nIsWebcamEnabled(), nIsMicrophoneEnabled());
        this.f4938x = c0012b;
        this.f4929o.put(i9, c0012b.f66b);
        this.f4930p.put(Integer.valueOf(i9), this.f4938x);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.f1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5182b.m4635U4(z8, z9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V5 */
    public /* synthetic */ void m4644V5(int[] iArr) {
        this.f4925k.mo5135m0(iArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V6 */
    public /* synthetic */ void m4645V6(boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > requestLaserPointer, nile network has been released");
            return;
        }
        final boolean z9 = !m4894G3() && nRequestLaserPointer(z8);
        if (z9) {
            this.f4919e.m4467t("JNile", " > requestLaserPointer[" + z8 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > requestLaserPointer[" + z8 + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.f3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4637U6(abstractC6381r, z9);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W3 */
    public /* synthetic */ void m4649W3(ArrayList arrayList) {
        this.f4925k.mo5136o(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W4 */
    public /* synthetic */ void m4650W4(C0012b c0012b) {
        this.f4925k.mo5137o0(c0012b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W5 */
    public /* synthetic */ void m4651W5(final int[] iArr) {
        this.f4919e.m4467t("JNile", " > OnUnderRecording run");
        if (m4901I7()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i9 : iArr) {
            C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
            if (c0012b != null) {
                c0012b.m78H(true);
                hashSet.add(c0012b);
            }
        }
        for (C0012b c0012b2 : this.f4930p.values()) {
            if (!hashSet.contains(c0012b2)) {
                c0012b2.m78H(false);
                hashSet.add(c0012b2);
            }
        }
        this.f4919e.m4467t("JNile", "OnUnderRecording: invoke NileCallback->onUnderRecordingChanged / ids length = " + iArr.length);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.e0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5162b.m4644V5(iArr);
            }
        });
    }

    /* renamed from: W6 */
    public static /* synthetic */ void m4652W6(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X3 */
    public /* synthetic */ void m4656X3(String[] strArr, String[] strArr2) {
        this.f4919e.m4467t("JNile", " > OnBRList run");
        if (m4901I7()) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < strArr.length; i9++) {
            arrayList.add(new Pair(strArr[i9], strArr2[i9]));
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.w1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5723b.m4649W3(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X4 */
    public /* synthetic */ void m4657X4(int i9) {
        this.f4919e.m4467t("JNile", " > OnKickOldUserFailed run");
        if (m4901I7()) {
            return;
        }
        final C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > kickedUser is unavailable, ignore");
        } else {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.e2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5166b.m4650W4(c0012b);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X5 */
    public /* synthetic */ void m4658X5(ArrayList arrayList, ArrayList arrayList2, boolean z8) {
        this.f4925k.mo5114P(arrayList, arrayList2, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X6 */
    public /* synthetic */ void m4659X6(int i9, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > requestPauseRecording, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > requestPauseRecording run");
        final boolean zNRequestPauseRecording = nRequestPauseRecording(i9);
        if (zNRequestPauseRecording) {
            this.f4919e.m4467t("JNile", " > requestPauseRecording[" + i9 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > requestPauseRecording[" + i9 + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.t3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4652W6(abstractC6381r, zNRequestPauseRecording);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y3 */
    public /* synthetic */ void m4663Y3(int i9, String str) {
        this.f4925k.OnBRListHost(i9, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y4 */
    public /* synthetic */ void m4664Y4(boolean z8) {
        this.f4925k.mo5144u0(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y5 */
    public /* synthetic */ void m4665Y5(int[] iArr, int[] iArr2, final boolean z8) {
        C0012b c0012b;
        this.f4919e.m4467t("JNile", " > OnUpdateActiveList run");
        if (m4901I7()) {
            return;
        }
        Iterator<C0012b> it = this.f4930p.values().iterator();
        while (it.hasNext()) {
            it.next().m101t(ActiveMedia.f5373c);
        }
        this.f4931q.clear();
        this.f4939y = null;
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        for (int i9 = 0; i9 < iArr.length; i9++) {
            int i10 = iArr[i9];
            ActiveMedia.Type typeM5009a = ActiveMedia.Type.m5009a(iArr2[i9]);
            arrayList2.add(typeM5009a);
            ActiveMedia activeMedia = new ActiveMedia(i9, typeM5009a);
            this.f4919e.m4467t("JNile", " > streamId: " + i9 + " -> UID: " + i10 + ", " + typeM5009a);
            if (i10 > 0) {
                c0012b = this.f4930p.get(Integer.valueOf(i10));
                if (c0012b != null) {
                    if (!z8 || i9 == 0) {
                        c0012b.m101t(activeMedia);
                    }
                    if (this.f4939y == null) {
                        this.f4939y = c0012b;
                    }
                } else {
                    this.f4919e.m4468u("JNile", " > participant is absent, UID: " + i10);
                    this.f4931q.put(Integer.valueOf(i10), activeMedia);
                }
            } else {
                c0012b = null;
            }
            arrayList.add(c0012b);
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.r0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5499b.m4658X5(arrayList, arrayList2, z8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y6 */
    public /* synthetic */ void m4666Y6(int i9, C0011a c0011a, ChatMsg chatMsg) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > sendFileMessage, nile network has been released");
            return;
        }
        if (nSendFileMsg(i9, c0011a.f63b, chatMsg.f5388h.getBytes())) {
            this.f4919e.m4467t("JNile", " > sendFileMessage: [" + i9 + "] successfully");
            return;
        }
        this.f4919e.m4468u("JNile", " > sendFileMessage: [" + i9 + "] failed");
        OnChatMsgSendFailed(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z3 */
    public /* synthetic */ void m4670Z3(final int i9, final String str) {
        this.f4919e.m4467t("JNile", " > OnBRListHost run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.l2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5339b.m4663Y3(i9, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z4 */
    public /* synthetic */ void m4671Z4(int i9, float f9, float f10) {
        this.f4919e.m4467t("JNile", " > OnLaserPointerReceived run");
        if (m4901I7()) {
            return;
        }
        C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > participant is unavailable, ignore");
        } else {
            this.f4925k.m5128i(c0012b, new PointF(f9, f10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z5 */
    public /* synthetic */ void m4672Z5(ArrayList arrayList) {
        this.f4925k.mo5130j0(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z6 */
    public /* synthetic */ void m4673Z6(PointF pointF) {
        if (this.f4939y == null) {
            return;
        }
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > sendLaserPointer, nile network has been released");
            return;
        }
        if (!m4894G3() && nSendLaserPointer(this.f4939y.f66b.f63b, pointF.x, pointF.y)) {
            return;
        }
        this.f4919e.m4468u("JNile", " > sendLaserPointer failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a4 */
    public /* synthetic */ void m4677a4(String str) {
        this.f4925k.OnBRMove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a5 */
    public /* synthetic */ void m4678a5(int i9) {
        this.f4925k.mo5106F0(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a6 */
    public /* synthetic */ void m4679a6(String[] strArr, int[] iArr) {
        this.f4919e.m4467t("JNile", " > OnUpdateBRStatus run");
        if (m4901I7()) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < strArr.length; i9++) {
            arrayList.add(new Pair(strArr[i9], Integer.valueOf(iArr[i9])));
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.m7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5371b.m4672Z5(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a7 */
    public /* synthetic */ void m4680a7(int i9, C0011a c0011a, ChatMsg chatMsg) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > sendMessage, nile network has been released");
            return;
        }
        if (nSendChatMsg(i9, c0011a.f63b, chatMsg.f5388h.getBytes())) {
            this.f4919e.m4467t("JNile", " > sendMessage: [" + i9 + "] successfully");
            return;
        }
        this.f4919e.m4468u("JNile", " > sendMessage: [" + i9 + "] failed");
        OnChatMsgSendFailed(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b4 */
    public /* synthetic */ void m4685b4(final String str) {
        this.f4919e.m4467t("JNile", " > OnBRMove run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.h2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5231b.m4677a4(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b5 */
    public /* synthetic */ void m4686b5(C0012b c0012b) {
        this.f4925k.mo5113O(c0012b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b6 */
    public /* synthetic */ void m4687b6(ArrayList arrayList, ArrayList arrayList2) {
        this.f4925k.mo5108H(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b7 */
    public /* synthetic */ void m4688b7(String str, String str2, String str3, int i9, String str4, String str5) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > sendPhoneLineInvite, nile network has been released");
        } else {
            if (nPhonelineInvite(str, str2, str3, i9, str4, str5)) {
                return;
            }
            this.f4919e.m4468u("JNile", " > sendPhoneLineInvite failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c4 */
    public /* synthetic */ void m4693c4(boolean z8) {
        this.f4925k.OnBRRegisterAck(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c5 */
    public /* synthetic */ void m4694c5(int i9) {
        this.f4919e.m4467t("JNile", " > OnMeetingHadBeenClosed run");
        if (m4901I7()) {
            return;
        }
        final C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > participant is unavailable, ignore");
        } else {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.s0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5570b.m4686b5(c0012b);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c6 */
    public /* synthetic */ void m4695c6(int[] iArr, String[] strArr, String[] strArr2, boolean[] zArr, boolean[] zArr2, boolean[] zArr3, boolean[] zArr4, String[] strArr3, boolean[] zArr5, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6) {
        final ArrayList arrayList;
        final NileNetwork nileNetwork;
        final ArrayList arrayList2;
        String str;
        NileNetwork nileNetwork2 = this;
        int[] iArr7 = iArr;
        String str2 = "JNile";
        nileNetwork2.f4919e.m4467t("JNile", " > OnUpdateParticipants run");
        if (m4901I7()) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        if (iArr7.length == 0) {
            nileNetwork2.f4919e.m4468u("JNile", " > clear all participants...");
            arrayList3.addAll(nileNetwork2.f4930p.values());
            nileNetwork2.f4930p.clear();
            arrayList3.remove(nileNetwork2.f4938x);
            nileNetwork2.f4930p.put(Integer.valueOf(nileNetwork2.f4938x.f66b.f63b), nileNetwork2.f4938x);
            arrayList = arrayList4;
            arrayList2 = arrayList3;
            str = "JNile";
            nileNetwork = nileNetwork2;
        } else {
            HashSet hashSet = new HashSet(nileNetwork2.f4930p.keySet());
            int i9 = 0;
            while (i9 < iArr7.length) {
                hashSet.remove(Integer.valueOf(iArr7[i9]));
                int i10 = i9;
                HashSet hashSet2 = hashSet;
                ArrayList arrayList5 = arrayList4;
                ArrayList arrayList6 = arrayList3;
                String str3 = str2;
                C0012b c0012bM4921X2 = m4921X2(iArr7[i9], strArr[i9], strArr2[i9], zArr[i9], zArr2[i9], zArr3[i9], zArr4[i9], strArr3[i9], zArr5[i9], iArr2[i9], iArr3[i9], iArr4[i9], iArr5[i9], iArr6[i9]);
                if (c0012bM4921X2 != null && !c0012bM4921X2.m96o()) {
                    arrayList5.add(c0012bM4921X2);
                }
                i9 = i10 + 1;
                iArr7 = iArr;
                arrayList4 = arrayList5;
                hashSet = hashSet2;
                arrayList3 = arrayList6;
                str2 = str3;
                nileNetwork2 = this;
            }
            HashSet hashSet3 = hashSet;
            arrayList = arrayList4;
            ArrayList arrayList7 = arrayList3;
            String str4 = str2;
            nileNetwork = nileNetwork2;
            nileNetwork.f4931q.clear();
            if (!hashSet3.isEmpty()) {
                Iterator it = hashSet3.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Integer) it.next()).intValue();
                    if (iIntValue != nileNetwork.f4938x.f66b.f63b) {
                        nileNetwork.f4919e.m4458g(str4, " > remove non-exists: " + iIntValue);
                        arrayList7.add(nileNetwork.f4930p.remove(Integer.valueOf(iIntValue)));
                    }
                }
            }
            arrayList2 = arrayList7;
            str = str4;
            nileNetwork.f4919e.m4458g(str, " > existing participants count: " + nileNetwork.f4930p.size());
            nileNetwork.f4919e.m4458g(str, " > updated participants count: " + arrayList.size());
        }
        nileNetwork.f4919e.m4458g(str, " > removed participants count: " + arrayList2.size());
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            return;
        }
        m4899H8();
        nileNetwork.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.z
            @Override // java.lang.Runnable
            public final void run() {
                this.f5772b.m4703d6();
            }
        });
        String[] strArr4 = nileNetwork.f4918d;
        if (strArr4 != null && strArr4.length > 0) {
            nileNetwork.m4896G8(strArr4, 0);
            nileNetwork.f4918d = null;
        }
        nileNetwork.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.a0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5057b.m4687b6(arrayList, arrayList2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c7 */
    public /* synthetic */ void m4696c7(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setActivateNum, nile network has been released");
            return;
        }
        if (nSetActiveNum(i9)) {
            this.f4919e.m4467t("JNile", " > setActivateNum : " + i9 + "successfully");
            return;
        }
        this.f4919e.m4467t("JNile", " > setActivateNum : " + i9 + "failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d4 */
    public /* synthetic */ void m4701d4(final boolean z8) {
        this.f4919e.m4467t("JNile", " > OnBRRegisterAck run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.h0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5227b.m4693c4(z8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d5 */
    public /* synthetic */ void m4702d5() {
        this.f4925k.OnMeetingHost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d6 */
    public /* synthetic */ void m4703d6() {
        this.f4925k.mo5123e(this.f4916b, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d7 */
    public /* synthetic */ void m4704d7(boolean z8) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setAllowMuteAnother, nile network has been released");
        } else {
            m4962r3(z8, ForwardOptionAction.MUTE_ANOTHER_MIC);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e4 */
    public /* synthetic */ void m4709e4(int i9, String str, String str2) {
        this.f4925k.OnBRRename(i9, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e5 */
    public /* synthetic */ void m4710e5() {
        this.f4919e.m4467t("JNile", " > OnMeetingHost run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5761b.m4702d5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e6 */
    public /* synthetic */ void m4711e6(int i9) {
        this.f4925k.OnUpdateRCDuration(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e7 */
    public /* synthetic */ void m4712e7(boolean z8) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setAllowRecordingEnabled, nile network has been released");
        } else {
            m4962r3(z8, ForwardOptionAction.LOCK_RECORDING);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f4 */
    public /* synthetic */ void m4717f4(final int i9, final String str, final String str2) {
        this.f4919e.m4467t("JNile", " > OnBRRename run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.a7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5074b.m4709e4(i9, str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f5 */
    public /* synthetic */ void m4718f5(ArrayList arrayList) {
        this.f4925k.mo5129i0(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f6 */
    public /* synthetic */ void m4719f6(final int i9) {
        this.f4919e.m4467t("JNile", " > OnUpdateRCDuration run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.g2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5209b.m4711e6(i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f7 */
    public /* synthetic */ void m4720f7(int i9) {
        if (nSetBlackListVersion(i9)) {
            this.f4919e.m4467t("JNile", " > setBlackListVersion[" + i9 + "] successfully");
            return;
        }
        this.f4919e.m4468u("JNile", " > setBlackListVersion[" + i9 + "] failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g4 */
    public /* synthetic */ void m4725g4(int i9) {
        NileError nileErrorM4475a = NileError.m4475a(i9);
        this.f4919e.m4467t("JNile", " > OnCallError run: " + nileErrorM4475a);
        if (C0945b.f5048b[nileErrorM4475a.ordinal()] != 1) {
            this.f4925k.mo5150z(nileErrorM4475a, i9);
        } else {
            this.f4925k.mo5121d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g5 */
    public /* synthetic */ void m4726g5(int[] iArr) {
        this.f4919e.m4467t("JNile", " > OnMeetingHost2 run");
        if (m4901I7()) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (int i9 : iArr) {
            arrayList.add(Integer.valueOf(i9));
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.f2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5185b.m4718f5(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g6 */
    public /* synthetic */ void m4727g6(ArrayList arrayList) {
        this.f4925k.mo5141s(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g7 */
    public /* synthetic */ void m4728g7(AbstractC6381r abstractC6381r) {
        abstractC6381r.m24506d(DisplayMode.m4981b(nGetDisplayMode()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h4 */
    public /* synthetic */ void m4733h4() {
        this.f4926l.mo5041I();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h5 */
    public /* synthetic */ void m4734h5(int i9, int i10, int i11, int i12, int i13, int i14) {
        if (this.f4914E == Long.MIN_VALUE) {
            return;
        }
        this.f4920f.m4471a(i9, i10, i11, i12, i13, i14);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h6 */
    public /* synthetic */ void m4735h6(int[] iArr, boolean[] zArr) {
        this.f4919e.m4467t("JNile", " > OnUpdateSpeechList run");
        if (m4901I7()) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < iArr.length; i9++) {
            int i10 = iArr[i9];
            if (i10 > 0) {
                C0012b c0012b = this.f4930p.get(Integer.valueOf(i10));
                if (c0012b == null) {
                    this.f4919e.m4468u("JNile", " > participant is absent, UID: " + i10);
                } else if (c0012b.m107z(zArr[i9])) {
                    this.f4919e.m4467t("JNile", " > UID: " + i10 + " -> speech: " + zArr[i9]);
                    arrayList.add(c0012b);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.j2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5300b.m4727g6(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h7 */
    public /* synthetic */ void m4736h7(boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setDesktopSharingEnabled, nile network has been released");
            return;
        }
        if (!m4894G3() && nSetEnableDesktopSharing(z8)) {
            this.f4919e.m4467t("JNile", " > setDesktopSharingEnabled[" + z8 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > setDesktopSharingEnabled[" + z8 + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.c0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5115b.m4728g7(abstractC6381r);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i4 */
    public /* synthetic */ void m4741i4() {
        this.f4926l.mo5043V();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i5 */
    public /* synthetic */ void m4742i5(String str) {
        this.f4925k.OnNewBlackListURL(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i6 */
    public /* synthetic */ void m4743i6(int i9, BackToWaitingRoomStatus backToWaitingRoomStatus) {
        this.f4925k.mo5131k(i9, backToWaitingRoomStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i7 */
    public /* synthetic */ Boolean m4744i7(String str) {
        return Boolean.valueOf(nSetDeviceId(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j4 */
    public /* synthetic */ void m4749j4() {
        this.f4926l.mo5042T();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j5 */
    public /* synthetic */ void m4750j5(boolean z8) {
        this.f4925k.mo5133l(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j6 */
    public /* synthetic */ void m4751j6(int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnWRBackAck run");
        if (m4901I7()) {
            return;
        }
        final BackToWaitingRoomStatus backToWaitingRoomStatusM4979a = BackToWaitingRoomStatus.m4979a(i9);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.i7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5286b.m4743i6(i10, backToWaitingRoomStatusM4979a);
            }
        });
    }

    /* renamed from: j7 */
    public static /* synthetic */ void m4752j7(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k4 */
    public /* synthetic */ void m4757k4() {
        this.f4925k.mo5132k0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k5 */
    public /* synthetic */ void m4758k5() {
        this.f4925k.OnParticipantReset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k6 */
    public /* synthetic */ void m4759k6(int i9, int i10) {
        this.f4925k.OnWRBackNotify(i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k7 */
    public /* synthetic */ void m4760k7(boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setLowResolution, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > setLowResolution run");
        final boolean zNSetLowResolution = nSetLowResolution(z8);
        if (zNSetLowResolution) {
            this.f4919e.m4467t("JNile", " > setLowResolution[" + z8 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > setLowResolution[" + z8 + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.o3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4752j7(abstractC6381r, zNSetLowResolution);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l4 */
    public /* synthetic */ void m4765l4() {
        this.f4925k.mo5111L();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l5 */
    public /* synthetic */ void m4766l5() {
        this.f4919e.m4467t("JNile", " > OnParticipantReset run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.m2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5361b.m4758k5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l6 */
    public /* synthetic */ void m4767l6(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnWRBackNotify run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.a2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5062b.m4759k6(i9, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l7 */
    public /* synthetic */ void m4768l7() {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setRaiseOrLowerHand, nile network has been released");
        } else {
            m4962r3(false, ForwardOptionAction.RAISE_HAND);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m4 */
    public /* synthetic */ void m4773m4() {
        this.f4926l.mo5044b0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m5 */
    public /* synthetic */ void m4774m5() {
        this.f4925k.OnParticipantUpdateDone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m6 */
    public /* synthetic */ void m4775m6(String str) {
        this.f4925k.OnWRMsg(str);
    }

    /* renamed from: m7 */
    public static /* synthetic */ void m4776m7(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n4 */
    public /* synthetic */ void m4781n4(int i9) {
        this.f4919e.m4467t("JNile", " > OnChatMsgCount run");
        C0946c c0946c = this.f4912C;
        if (c0946c.f5051b == i9) {
            if (i9 == 0) {
                c0946c.f5052c = 0;
                c0946c.f5054e = 0;
                c0946c.f5053d = 0;
                c0946c.f5055f = 0;
                return;
            }
            return;
        }
        c0946c.f5051b = i9;
        c0946c.f5053d = i9 - 1;
        if (c0946c.f5052c == -1) {
            c0946c.f5052c = Math.max(i9 - 200, 0);
            C0946c c0946c2 = this.f4912C;
            c0946c2.f5054e = c0946c2.f5052c;
        }
        C0946c c0946c3 = this.f4912C;
        c0946c3.f5055f = Math.min((c0946c3.f5054e + 200) - 1, c0946c3.f5053d);
        m4909M7();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n5 */
    public /* synthetic */ void m4782n5() {
        this.f4919e.m4467t("JNile", " > OnParticipantUpdateDone run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.g1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5208b.m4774m5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n6 */
    public /* synthetic */ void m4783n6(final String str) {
        this.f4919e.m4467t("JNile", " > OnWRMsg run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.y
            @Override // java.lang.Runnable
            public final void run() {
                this.f5759b.m4775m6(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n7 */
    public /* synthetic */ void m4784n7(final boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setMicrophoneEnabled, nile network has been released");
            return;
        }
        if (!m4894G3() && nSetEnableMicrophone(z8)) {
            this.f4919e.m4467t("JNile", " > setMicrophoneEnabled[" + z8 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > setMicrophoneEnabled[" + z8 + "] failed");
            z8 ^= true;
        }
        C0012b c0012b = this.f4938x;
        if (c0012b != null) {
            c0012b.m102u(!z8);
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.h3
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4776m7(abstractC6381r, z8);
                }
            });
        }
    }

    private native boolean nAddHost(int i9);

    private native void nAddVideoRenderer(int i9, long j9);

    private native boolean nAllowAllEnterMeeting(int[] iArr);

    private native boolean nAllowEnterMeeting(int i9);

    private native boolean nCFP(byte[] bArr, byte[] bArr2);

    private native boolean nConfirmJoin();

    private native long nCreateNileClient(Context context, Logger logger, Object obj, String str, String str2, String str3);

    private native boolean nFileUploadCanceled();

    private native boolean nFileUploadCompleted();

    private native boolean nForwardOption(int[] iArr, int i9, String[] strArr, int i10, int i11);

    private native int nGetDisplayMode();

    private native int nGetEventTimeLimit();

    private native int nGetParticipantLimit();

    private native boolean nIsDesktopSharingEnabled();

    private native boolean nIsMicrophoneEnabled();

    private native boolean nIsWebcamEnabled();

    private native boolean nJoinEvent(String str, String str2, String str3, String str4, byte[] bArr, String str5, int i9, int i10, int i11, byte[] bArr2);

    private native boolean nLeaveEvent();

    private native boolean nMoveToWaitingRoom(int i9);

    private native boolean nPhonelineCancel();

    private native boolean nPhonelineDTMF(int i9);

    private native boolean nPhonelineInvite(String str, String str2, String str3, int i9, String str4, String str5);

    private native boolean nPreJoinEvent(String str, String str2, String str3, String str4, byte[] bArr, String str5, int i9, int i10, int i11, byte[] bArr2);

    private native boolean nPrepareToFileUpload(String str, String str2, int i9, int i10);

    private native boolean nQueryChatMsg(int i9, int i10);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nQueryChatMsgCount();

    private native boolean nQueryChatUserInfo();

    private native boolean nRegisterViewerInfo(int i9, byte[] bArr, byte[] bArr2, byte[] bArr3);

    private native boolean nReleaseNileNetwork();

    private native boolean nRemoveHost(int i9);

    private native boolean nRequestAudioMute(int i9, boolean z8);

    private native boolean nRequestKickUser(int i9);

    private native boolean nRequestLaserPointer(boolean z8);

    private native boolean nRequestPauseRecording(int i9);

    private native boolean nSendChatMsg(int i9, int i10, byte[] bArr);

    private native boolean nSendFileMsg(int i9, int i10, byte[] bArr);

    private native boolean nSendLaserPointer(int i9, float f9, float f10);

    private native boolean nSetActiveNum(int i9);

    private native boolean nSetBlackListVersion(int i9);

    private native boolean nSetDeviceId(String str);

    private native boolean nSetDisplayMode(int i9);

    private native boolean nSetEnableDesktopSharing(boolean z8);

    private native boolean nSetEnableMicrophone(boolean z8);

    private native boolean nSetEnableWebcam(boolean z8);

    private native boolean nSetLowResolution(boolean z8);

    private native boolean nSetMuteAll(boolean z8);

    private native boolean nSetPinkNoiseEnabled(boolean z8, int i9, int i10);

    private native boolean nSetPoorConnectionAudioHintEnabled(boolean z8, int i9);

    private native boolean nSetProperty(byte[] bArr, byte[] bArr2);

    private native boolean nSetScreenResolution(int i9, int i10);

    private native boolean nSetSystemVolume(int i9);

    private native boolean nSetVideoPinList(int i9, int[] iArr);

    private native void nSetVideoSource(int i9, long j9);

    private native boolean nStopDesktopSharing();

    private native boolean nVerified(int[] iArr, boolean z8);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o4 */
    public /* synthetic */ void m4789o4(ArrayList arrayList) {
        this.f4925k.mo5115Q(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o5 */
    public /* synthetic */ void m4790o5(int i9) {
        this.f4925k.mo5104D(PhoneLineInviteResult.m4988a(i9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o6 */
    public /* synthetic */ void m4791o6(ArrayList arrayList) {
        this.f4925k.mo5125f(arrayList);
    }

    /* renamed from: o7 */
    public static /* synthetic */ void m4792o7(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p4 */
    public /* synthetic */ void m4797p4(int[] iArr, boolean[] zArr, int[] iArr2, long[] jArr, boolean[] zArr2, String[] strArr, int[] iArr3) {
        this.f4919e.m4467t("JNile", " > OnChatMsgQueried run");
        this.f4912C.f5050a = false;
        final ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < iArr.length; i9++) {
            if (!zArr[i9]) {
                int i10 = iArr[i9];
                ChatMsg chatMsg = new ChatMsg(i10, iArr2[i9], jArr[i9], zArr2[i9], strArr[i9], iArr3[i9]);
                chatMsg.m5017h(ChatMsg.Status.NORMAL);
                if (this.f4930p.get(Integer.valueOf(i10)) == null) {
                    chatMsg.m5015f(false);
                }
                C0011a c0011a = this.f4929o.get(i10);
                if (c0011a == null) {
                    m4917U7(i10, chatMsg);
                } else {
                    chatMsg.m5018i(c0011a);
                    arrayList.add(chatMsg);
                }
            }
        }
        if (arrayList.size() > 0) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.d1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5139b.m4789o4(arrayList);
                }
            });
        }
        if (this.f4911B.size() > 0) {
            nQueryChatUserInfo();
        }
        C0946c c0946c = this.f4912C;
        c0946c.f5054e = c0946c.f5055f + 1;
        c0946c.f5055f = Math.min((r2 + 200) - 1, c0946c.f5053d);
        C0946c c0946c2 = this.f4912C;
        if (c0946c2.f5054e <= c0946c2.f5055f) {
            m4909M7();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p5 */
    public /* synthetic */ void m4798p5(int i9, int i10) {
        this.f4925k.OnPinVideoResult(i9, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p6 */
    public /* synthetic */ void m4799p6(int[] iArr, String[] strArr) {
        this.f4919e.m4467t("JNile", " > OnWRUserList run");
        if (m4901I7()) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < iArr.length; i9++) {
            arrayList.add(new Pair(Integer.valueOf(iArr[i9]), strArr[i9]));
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.w0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5721b.m4791o6(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p7 */
    public /* synthetic */ void m4800p7(boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setMuteAll, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > setMuteAll run");
        final boolean zNSetMuteAll = nSetMuteAll(z8);
        if (zNSetMuteAll) {
            this.f4919e.m4467t("JNile", " > setMuteAll[" + z8 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > setMuteAll[" + z8 + "] failed");
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.d4
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4792o7(abstractC6381r, zNSetMuteAll);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q4 */
    public /* synthetic */ void m4805q4(String str) {
        this.f4925k.mo5147x0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q5 */
    public /* synthetic */ void m4806q5(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", " > OnPinVideoResult run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.q1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5480b.m4798p5(i9, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q6 */
    public /* synthetic */ void m4807q6(boolean z8) {
        this.f4925k.OnWaitingRoomEnabled(z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q7 */
    public /* synthetic */ void m4808q7(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setPhonelineExtension, nile network has been released");
        } else {
            if (nPhonelineDTMF(i9)) {
                return;
            }
            this.f4919e.m4468u("JNile", " > setPhonelineExtension failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r4 */
    public /* synthetic */ void m4813r4(ChatMsg chatMsg) {
        this.f4925k.mo5116X(chatMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r5 */
    public /* synthetic */ void m4814r5(boolean z8, boolean z9) {
        C0012b c0012b = this.f4938x;
        if (c0012b != null) {
            this.f4926l.mo5046r0(c0012b, z8, z9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r6 */
    public /* synthetic */ void m4815r6(final boolean z8) {
        this.f4919e.m4467t("JNile", " > OnWaitingRoomEnabled run");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.x0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5745b.m4807q6(z8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r7 */
    public /* synthetic */ void m4816r7(List list, int i9) {
        int[] iArr = new int[list.size()];
        for (int i10 = 0; i10 < list.size(); i10++) {
            iArr[i10] = ((Integer) list.get(i10)).intValue();
        }
        nSetVideoPinList(i9, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s4 */
    public /* synthetic */ void m4821s4(int i9, final String str, int i10, long j9, boolean z8, int i11) {
        this.f4919e.m4467t("JNile", " > OnChatMsgReceived run");
        if (i9 == 0) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.h1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5229b.m4805q4(str);
                }
            });
            return;
        }
        final ChatMsg chatMsg = new ChatMsg(i9, i10, j9, z8, str, i11);
        chatMsg.m5017h(ChatMsg.Status.NORMAL);
        C0011a c0011a = this.f4929o.get(i9);
        if (c0011a == null) {
            m4917U7(i9, chatMsg);
        } else {
            chatMsg.m5018i(c0011a);
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.i1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5265b.m4813r4(chatMsg);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s5 */
    public /* synthetic */ void m4822s5(int i9, final boolean z8, final boolean z9) {
        this.f4919e.m4467t("JNile", " > OnPreJoinCompleted run");
        this.f4919e.m4464q(this.f4940z, i9);
        C1019i c1019i = this.f4940z;
        C0012b c0012b = new C0012b(i9, c1019i.f5250d, c1019i.f5249c, nIsWebcamEnabled(), nIsMicrophoneEnabled());
        this.f4938x = c0012b;
        this.f4929o.put(i9, c0012b.f66b);
        this.f4930p.put(Integer.valueOf(i9), this.f4938x);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.d7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5158b.m4814r5(z8, z9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s6 */
    public /* synthetic */ void m4823s6(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > addHost, nile network has been released");
        } else {
            if (nAddHost(i9)) {
                return;
            }
            this.f4919e.m4468u("JNile", " > addHost failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s7 */
    public /* synthetic */ void m4824s7(boolean z8, int i9, int i10) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setPinkNoiseEnabled, nile network has been released");
            return;
        }
        if (nSetPinkNoiseEnabled(z8, i9, i10)) {
            this.f4919e.m4467t("JNile", " > setPinkNoiseEnabled[" + z8 + "] successfully");
            return;
        }
        this.f4919e.m4468u("JNile", " > setPinkNoiseEnabled[" + z8 + "] failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t4 */
    public /* synthetic */ void m4829t4(ChatMsg chatMsg) {
        this.f4925k.mo5117Y(chatMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t5 */
    public /* synthetic */ void m4830t5(PreJoinUserStatus preJoinUserStatus, int i9) {
        int i10 = C0945b.f5049c[preJoinUserStatus.ordinal()];
        if (i10 == 1) {
            this.f4925k.mo5138p0(i9);
        } else {
            if (i10 != 2) {
                return;
            }
            this.f4925k.mo5142s0(i9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t6 */
    public /* synthetic */ void m4831t6(List list) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > allowAllEnterMeeting, nile network has been released");
            return;
        }
        int[] iArr = new int[list.size()];
        for (int i9 = 0; i9 < list.size(); i9++) {
            iArr[i9] = ((Integer) list.get(i9)).intValue();
        }
        nAllowAllEnterMeeting(iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t7 */
    public /* synthetic */ void m4832t7(boolean z8, int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setPoorConnectionAudioHintEnabled, nile network has been released");
            return;
        }
        if (nSetPoorConnectionAudioHintEnabled(z8, i9)) {
            this.f4919e.m4467t("JNile", " > setPoorConnectionAudioHintEnabled[" + z8 + "] successfully");
            return;
        }
        this.f4919e.m4468u("JNile", " > setPoorConnectionAudioHintEnabled[" + z8 + "] failed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u4 */
    public /* synthetic */ void m4837u4(int i9) {
        this.f4919e.m4467t("JNile", " > OnChatMsgSendFailed run");
        final ChatMsg chatMsg = this.f4910A.get(i9);
        if (chatMsg != null) {
            chatMsg.m5017h(ChatMsg.Status.FAILED);
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.p0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5447b.m4829t4(chatMsg);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u5 */
    public /* synthetic */ void m4838u5(int i9, final int i10) {
        final PreJoinUserStatus preJoinUserStatusM4990a = PreJoinUserStatus.m4990a(i9);
        this.f4919e.m4467t("JNile", " > OnPreJoinUserState run: [" + i10 + "] " + preJoinUserStatusM4990a);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.y1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5762b.m4830t5(preJoinUserStatusM4990a, i10);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u6 */
    public /* synthetic */ void m4839u6(int i9) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > allowEnterMeeting, nile network has been released");
        } else {
            if (nAllowEnterMeeting(i9)) {
                return;
            }
            this.f4919e.m4468u("JNile", " > allowEnterMeeting failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u7 */
    public /* synthetic */ void m4840u7(String[] strArr) {
        if (strArr == null || strArr.length < 2 || m4894G3() || this.f4938x != null) {
            return;
        }
        nSetProperty(strArr[0].getBytes(), strArr[1].getBytes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v4 */
    public /* synthetic */ void m4845v4(ChatMsg chatMsg, ChatMsg chatMsg2) {
        this.f4925k.mo5148y(chatMsg, chatMsg2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v5 */
    public /* synthetic */ void m4846v5() {
        this.f4925k.OnQNAHostHangup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v6 */
    public /* synthetic */ void m4847v6() {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > cancelPhoneLineInvite, nile network has been released");
        } else {
            if (nPhonelineCancel()) {
                return;
            }
            this.f4919e.m4468u("JNile", " > cancelPhoneLineInvite failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v7 */
    public /* synthetic */ void m4848v7(int i9, boolean z8) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setRaiseOrLowerHand, nile network has been released");
            return;
        }
        int[] iArr = new int[1];
        if (i9 == 0) {
            i9 = this.f4938x.f66b.f63b;
        }
        iArr[0] = i9;
        m4960q3(z8, iArr, ForwardOptionAction.RAISE_HAND);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w4 */
    public /* synthetic */ void m4853w4(int i9, int i10, long j9) {
        this.f4919e.m4467t("JNile", " > OnChatMsgSent run");
        final ChatMsg chatMsg = this.f4910A.get(i9);
        if (chatMsg == null || chatMsg.m5014e() == null) {
            this.f4919e.m4468u("JNile", " > sent MID:" + i9 + " not found");
            return;
        }
        this.f4910A.delete(i9);
        long jM4488B3 = m4488B3() - chatMsg.f5385e;
        this.f4919e.m4467t("JNile", " > took " + jM4488B3 + "ms");
        final ChatMsg chatMsg2 = new ChatMsg(chatMsg.m5014e(), i10, j9, chatMsg.f5387g, chatMsg.f5388h, chatMsg.f5390j);
        chatMsg2.m5017h(ChatMsg.Status.NORMAL);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.m0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5357b.m4845v4(chatMsg, chatMsg2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w5 */
    public /* synthetic */ void m4854w5() {
        this.f4925k.OnQNAHostPicking();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w6 */
    public /* synthetic */ void m4855w6() {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > confirmJoin, nile network has been released");
            return;
        }
        this.f4919e.m4467t("JNile", " > confirmJoin run");
        nSetActiveNum(this.f4932r.size());
        boolean zNConfirmJoin = nConfirmJoin();
        this.f4919e.m4467t("JNile", " > confirm join meeting: " + zNConfirmJoin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w7 */
    public /* synthetic */ Boolean m4856w7(int i9, int i10) {
        return Boolean.valueOf(nSetScreenResolution(i9, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x4 */
    public /* synthetic */ void m4861x4(int[] iArr, String[] strArr, String[] strArr2) {
        this.f4919e.m4467t("JNile", " > OnChatUsersInfo run");
        if (m4901I7()) {
            return;
        }
        for (int i9 = 0; i9 < iArr.length; i9++) {
            m4923Y2(iArr[i9], strArr[i9], strArr2[i9]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x5 */
    public /* synthetic */ void m4862x5(int i9) {
        RTCError rTCErrorM4991a = RTCError.m4991a(i9);
        this.f4919e.m4467t("JNile", " > OnRTCError run : " + rTCErrorM4991a);
        this.f4925k.m5122d0(rTCErrorM4991a, i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x6 */
    public /* synthetic */ void m4863x6(String str, String str2) {
        if (Level.SEVERE.getName().equals(str)) {
            this.f4919e.m4459h("webRTC", "[webRTC] " + str2);
            return;
        }
        if (Level.WARNING.getName().equals(str)) {
            this.f4919e.m4468u("webRTC", "[webRTC] " + str2);
            return;
        }
        if (Level.INFO.getName().equals(str)) {
            this.f4919e.m4460i("webRTC", "[webRTC] " + str2);
            return;
        }
        if (Level.FINE.getName().equals(str)) {
            this.f4919e.m4467t("webRTC", "[webRTC] " + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x7 */
    public /* synthetic */ void m4864x7(List list, boolean z8) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setVerified, nile network has been released");
            return;
        }
        int[] iArr = new int[list.size()];
        for (int i9 = 0; i9 < list.size(); i9++) {
            iArr[i9] = ((C0012b) list.get(i9)).f66b.f63b;
        }
        nVerified(iArr, z8);
    }

    /* renamed from: y3 */
    public static String m4869y3() {
        return "Android:" + Build.VERSION.RELEASE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y4 */
    public /* synthetic */ void m4870y4() {
        this.f4926l.mo5045j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y5 */
    public /* synthetic */ void m4871y5(String str) {
        this.f4925k.mo5127g0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y6 */
    public /* synthetic */ void m4872y6() {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > fileUploadCanceled, nile network has been released");
        } else if (nFileUploadCanceled()) {
            this.f4919e.m4467t("JNile", " > fileUploadCanceled: successfully");
        } else {
            this.f4919e.m4468u("JNile", " > fileUploadCanceled: failed");
        }
    }

    /* renamed from: y7 */
    public static /* synthetic */ void m4873y7(AbstractC6381r abstractC6381r, boolean z8) {
        abstractC6381r.m24506d(Boolean.valueOf(z8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z4 */
    public /* synthetic */ void m4878z4() {
        this.f4919e.m4467t("JNile", " > OnConfirmJoinCompleted run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.s1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5572b.m4870y4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z5 */
    public /* synthetic */ void m4879z5(final String str) {
        this.f4919e.m4467t("JNile", " > OnRejectLimitUser run");
        if (m4901I7()) {
            return;
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.k7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5332b.m4871y5(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z6 */
    public /* synthetic */ void m4880z6() {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > fileUploadCompleted, nile network has been released");
        } else if (nFileUploadCompleted()) {
            this.f4919e.m4467t("JNile", " > fileUploadCompleted: successfully");
        } else {
            this.f4919e.m4468u("JNile", " > fileUploadCompleted: failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z7 */
    public /* synthetic */ void m4881z7(final boolean z8, final AbstractC6381r abstractC6381r) {
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > setWebcamEnabled, nile network has been released");
            return;
        }
        if (!m4894G3() && nSetEnableWebcam(z8)) {
            this.f4919e.m4467t("JNile", " > setWebcamEnabled[" + z8 + "] successfully");
        } else {
            this.f4919e.m4468u("JNile", " > setWebcamEnabled[" + z8 + "] failed");
            z8 ^= true;
        }
        C0012b c0012b = this.f4938x;
        if (c0012b != null) {
            c0012b.m81K(!z8);
            C0947a c0947a = this.f4936v;
            if (c0947a != null) {
                if (z8) {
                    c0947a.m4442q();
                } else {
                    c0947a.m4444s();
                }
            }
        }
        if (abstractC6381r != null) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.p2
                @Override // java.lang.Runnable
                public final void run() {
                    NileNetwork.m4873y7(abstractC6381r, z8);
                }
            });
        }
    }

    /* renamed from: A3 */
    public void m4882A3(final AbstractC6381r<List<C0012b>, Void> abstractC6381r) {
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.m5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5367b.m4507D6(abstractC6381r);
            }
        });
    }

    /* renamed from: A8 */
    public final void m4883A8() {
        m4886C8();
        C0956b c0956b = new C0956b(this.f4922h, this.f4921g, this.f4919e);
        this.f4937w = c0956b;
        VideoRenderer videoRenderer = this.f4934t;
        if (videoRenderer != null) {
            c0956b.m4443r(videoRenderer);
        }
        nSetVideoSource(1, this.f4937w.f4842f);
    }

    /* renamed from: B8 */
    public final void m4884B8() {
        C0947a c0947a = this.f4936v;
        if (c0947a != null) {
            c0947a.m4440o();
            this.f4936v.m4438j();
            this.f4936v = null;
        }
    }

    /* renamed from: C3 */
    public final void m4885C3(C0011a c0011a) {
        m4936e3();
        final HashSet<ChatMsg> hashSet = this.f4911B.get(c0011a.f63b);
        if (hashSet != null) {
            Iterator<ChatMsg> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().m5018i(c0011a);
            }
            this.f4911B.remove(c0011a.f63b);
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.j7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5313b.m4515E6(hashSet);
                }
            });
        }
    }

    /* renamed from: C8 */
    public final void m4886C8() {
        C0956b c0956b = this.f4937w;
        if (c0956b != null) {
            c0956b.m4440o();
            this.f4937w.m4438j();
            this.f4937w = null;
        }
    }

    /* renamed from: D3 */
    public final boolean m4887D3() {
        PackageManager packageManager = this.f4922h.getPackageManager();
        return packageManager.hasSystemFeature("android.hardware.camera") || packageManager.hasSystemFeature("android.hardware.camera.front");
    }

    /* renamed from: D8 */
    public void m4888D8() {
        this.f4919e.m4467t("JNile", "stopDesktopSharing");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.c4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5127b.m4483A7();
            }
        });
    }

    /* renamed from: E3 */
    public final boolean m4889E3(String str) {
        return this.f4922h.checkSelfPermission(str) == 0;
    }

    /* renamed from: E8 */
    public void m4890E8(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        C0947a c0947a = this.f4936v;
        if (c0947a != null) {
            c0947a.m4445t(cameraSwitchHandler);
        }
    }

    /* renamed from: F3 */
    public boolean m4891F3() {
        return nIsDesktopSharingEnabled();
    }

    /* renamed from: F7 */
    public void m4892F7() {
        this.f4919e.m4467t("JNile", "leaveMeeting");
        this.f4940z = C1019i.f5246i;
        this.f4935u.clear();
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.g0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5207b.m4530G6();
            }
        });
    }

    /* renamed from: F8 */
    public void m4893F8(final DisplayMode displayMode, final AbstractC6381r<DisplayMode, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "switchDisplayMode[" + displayMode + "]");
        if (!m4901I7()) {
            f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.c3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5124b.m4500C7(displayMode, abstractC6381r);
                }
            });
            return;
        }
        if (this.f4915a) {
            this.f4919e.m4467t("JNile", " > switchDisplayMode, nile network has been released");
            return;
        }
        if (nSetDisplayMode(displayMode.ordinal())) {
            this.f4919e.m4467t("JNile", " > switchDisplayMode[" + displayMode + "] directly");
            this.f4913D = displayMode;
        } else {
            this.f4919e.m4468u("JNile", " > switchDisplayMode[" + displayMode + "] failed");
        }
        if (abstractC6381r != null) {
            abstractC6381r.m24506d(this.f4913D);
        }
    }

    /* renamed from: G3 */
    public final boolean m4894G3() {
        return this.f4938x == null && this.f4940z != C1019i.f5246i;
    }

    /* renamed from: G7 */
    public void m4895G7(final int i9) {
        this.f4919e.m4467t("JNile", "moveToWaitingRoom userId : " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.l3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5342b.m4537H6(i9);
            }
        });
    }

    /* renamed from: G8 */
    public final void m4896G8(String[] strArr, int i9) {
        if (i9 == 0) {
            this.f4917c.clear();
            this.f4918d = strArr;
        }
        final boolean z8 = false;
        for (int i10 = 0; i10 < strArr.length; i10++) {
            if (i9 <= 1 && !strArr[i10].equals("0")) {
                this.f4917c.add(String.valueOf(strArr[i10]));
            } else if (i9 == 2) {
                this.f4917c.remove(String.valueOf(strArr[i10]));
            }
        }
        m4899H8();
        this.f4919e.m4467t("JNile", "OnForwardOptionNotify raise hand count: " + this.f4916b.size());
        if (this.f4930p.size() > 0) {
            if (i9 == 1 || (i9 == 0 && strArr.length > 0)) {
                z8 = true;
            }
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.r2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5509b.m4508D7(z8);
                }
            });
        }
    }

    /* renamed from: H3 */
    public boolean m4897H3() {
        return nIsMicrophoneEnabled();
    }

    /* renamed from: H7 */
    public final BroadcastReceiver m4898H7() {
        return new C0944a();
    }

    /* renamed from: H8 */
    public final void m4899H8() {
        if (this.f4930p.size() > 0) {
            this.f4916b.clear();
            for (int i9 = 0; i9 < this.f4917c.size(); i9++) {
                if (this.f4930p.get(Integer.valueOf(Integer.parseInt(this.f4917c.get(i9)))) != null) {
                    this.f4916b.add(this.f4917c.get(i9));
                }
            }
        }
    }

    /* renamed from: I3 */
    public boolean m4900I3() {
        return nIsWebcamEnabled();
    }

    /* renamed from: I7 */
    public final boolean m4901I7() {
        if (this.f4938x != null) {
            return false;
        }
        this.f4919e.m4467t("JNile", " > didn't join a event yet");
        return true;
    }

    /* renamed from: I8 */
    public void m4902I8(final C0012b c0012b, final List<String> list) {
        this.f4919e.m4467t("JNile", "verifyProperty: " + c0012b.f66b.f63b);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.d3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5144b.m4516E7(c0012b, list);
            }
        });
    }

    /* renamed from: J3 */
    public final void m4903J3(final C1019i c1019i, final EventType eventType) {
        if (m4889E3("android.permission.CAMERA")) {
            m4978z8();
        }
        m4883A8();
        this.f4914E = Long.MIN_VALUE;
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.k3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5321b.m4523F6(c1019i, eventType);
            }
        });
    }

    /* renamed from: J7 */
    public void m4904J7(RTCAudioManager.AudioDevice audioDevice) {
        this.f4919e.m4467t("JNile", "notifyAudioDeviceChanged:" + audioDevice);
        if (audioDevice != null) {
            this.f4927m.m5047a(audioDevice);
        } else {
            this.f4927m.onChange(true);
        }
    }

    /* renamed from: K3 */
    public void m4905K3(C1019i c1019i, boolean z8) {
        m4903J3(c1019i, z8 ? EventType.S_MEETING : EventType.MEETING);
    }

    /* renamed from: K7 */
    public void m4906K7(final C1019i c1019i) {
        this.f4919e.m4467t("JNile", "preJoinMeeting");
        this.f4914E = Long.MIN_VALUE;
        m4883A8();
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.o0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5427b.m4558K6(c1019i);
            }
        });
    }

    /* renamed from: L3 */
    public void m4907L3(C1019i c1019i) {
        m4903J3(c1019i, EventType.WEBINAR_ASKING_VIEWER);
    }

    /* renamed from: L7 */
    public void m4908L7(final String str, final String str2, final int i9, final int i10) {
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.h7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5241b.m4565L6(str, str2, i9, i10);
            }
        });
    }

    /* renamed from: M7 */
    public final void m4909M7() {
        this.f4919e.m4467t("JNile", " > queryArchiveMsg");
        if (this.f4912C.f5050a) {
            this.f4919e.m4468u("JNile", " > queryArchiveMsg abort cause of previous query is in progress.");
            return;
        }
        this.f4919e.m4458g("JNile", " > queryArchiveMsg: " + this.f4912C.f5054e + " ~ " + this.f4912C.f5055f);
        C0946c c0946c = this.f4912C;
        c0946c.f5050a = nQueryChatMsg(c0946c.f5054e, c0946c.f5055f);
        this.f4919e.m4467t("JNile", " > queryArchiveMsg end");
    }

    /* renamed from: N7 */
    public void m4910N7(final int i9, final String str, final String str2, final String str3, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "registerWebinarViewerInfo: " + i9 + " corporate : " + str + " department : " + str2 + " title : " + str3);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.i3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5271b.m4581N6(i9, str, str2, str3, abstractC6381r);
            }
        });
    }

    /* renamed from: O7 */
    public void m4911O7() {
        this.f4919e.m4467t("JNile", "release");
        this.f4915a = true;
        m4892F7();
        this.f4927m.m5048b();
        this.f4922h.unregisterReceiver(this.f4928n);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.q6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5497b.m4589O6();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnAddHostAck(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnAddHostAck");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.m4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5364b.m4578N3(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnAddHostNotify(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnAddHostNotify");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.j4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5304b.m4594P3(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    public void OnAnUserHadBeenKicked(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnAnUserHadBeenKicked");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.w4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5730b.m4610R3(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBRBroadcast(final String str, final String str2) {
        this.f4919e.m4467t("JNile", "OnBRBroadcast");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.v4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5641b.m4626T3(str, str2);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBREventTime(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnBREventTime");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.c7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5131b.m4642V3(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBRList(final String[] strArr, final String[] strArr2) {
        this.f4919e.m4467t("JNile", "OnBRList");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.f4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5189b.m4656X3(strArr, strArr2);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBRListHost(final int i9, final String str) {
        this.f4919e.m4467t("JNile", "OnBRListHost");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.u4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5623b.m4670Z3(i9, str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBRMove(final String str) {
        this.f4919e.m4467t("JNile", "OnBRMove");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.f0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5180b.m4685b4(str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBRRegisterAck(final boolean z8) {
        this.f4919e.m4467t("JNile", "OnBRRegisterAck");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.z5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5787b.m4701d4(z8);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnBRRename(final int i9, final String str, final String str2) {
        this.f4919e.m4467t("JNile", "OnBRRename");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.n4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5410b.m4717f4(i9, str, str2);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCallError(final int i9) {
        this.f4919e.m4467t("JNile", "OnCallError: " + i9);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.r4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5512b.m4725g4(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCalleeAnotherDeviceAnswered() {
        this.f4919e.m4467t("JNile", "OnCalleeAnotherDeviceAnswered");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.k4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5324b.m4733h4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCalleeAnotherDeviceBusy() {
        this.f4919e.m4467t("JNile", "OnCalleeAnotherDeviceBusy");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.h4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5235b.m4741i4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCalleeAnotherDeviceReject() {
        this.f4919e.m4467t("JNile", "OnCalleeAnotherDeviceReject");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.l5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5346b.m4749j4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCalleeBusy() {
        this.f4919e.m4467t("JNile", "OnCalleeBusy");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.r6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5516b.m4757k4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCalleeRefused() {
        this.f4919e.m4467t("JNile", "OnCalleeRefused");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.e5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5173b.m4765l4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnCallerHangUp() {
        this.f4919e.m4467t("JNile", "OnCallerHangUp");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.c5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5128b.m4773m4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnChatMsgCount(final int i9) {
        this.f4919e.m4467t("JNile", "OnChatMsgCount: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.e4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5171b.m4781n4(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnChatMsgQueried(final boolean[] zArr, final int[] iArr, final int[] iArr2, final long[] jArr, final boolean[] zArr2, final String[] strArr, final int[] iArr3) {
        this.f4919e.m4467t("JNile", "OnChatMsgQueried: size: " + iArr.length);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.q0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5472b.m4797p4(iArr, zArr, iArr2, jArr, zArr2, strArr, iArr3);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnChatMsgReceived(final int i9, final int i10, final long j9, final boolean z8, final String str, final int i11) {
        this.f4919e.m4467t("JNile", "OnChatMsgReceived: UID:" + i9 + "[" + j9 + "] " + str);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.d6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5151b.m4821s4(i9, str, i10, j9, z8, i11);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnChatMsgSendFailed(final int i9) {
        this.f4919e.m4467t("JNile", "OnChatMsgSendFailed: MID:" + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.p4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5457b.m4837u4(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnChatMsgSent(final int i9, final int i10, final long j9) {
        this.f4919e.m4467t("JNile", "OnChatMsgSent: MID:" + i9 + "[" + j9 + "]");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.i6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5282b.m4853w4(i9, i10, j9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnChatUsersInfo(final int[] iArr, final String[] strArr, final String[] strArr2) {
        this.f4919e.m4467t("JNile", "OnChatUsersInfo: " + Arrays.toString(iArr));
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.k5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5325b.m4861x4(iArr, strArr, strArr2);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnConfirmJoinCompleted() {
        this.f4919e.m4467t("JNile", "OnConfirmJoinCompleted");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.s4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5577b.m4878z4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnDesktopHostResolutionNotified(final int i9, final int i10) {
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.b1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5083b.m4480A4(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnDeviceChanged() {
        this.f4919e.m4467t("JNile", "OnDeviceChanged");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.o5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5441b.m4497C4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnDisplayModeChanged(int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnDisplayModeChanged: " + i9 + ", code: " + i10);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.c6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5129b.m4513E4(i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnDisplayNameChanged(final int i9, final String str) {
        this.f4919e.m4467t("JNile", "OnDisplayNameChanged: [" + i9 + "] " + str);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.b4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5090b.m4528G4(i9, str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnDowngradeVideoQuality() {
        this.f4919e.m4467t("JNile", "OnDowngradeVideoQuality");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.e6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5174b.m4535H4();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnEventTime(int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnEventTime: " + i9 + ", " + i10);
        this.f4914E = SystemClock.elapsedRealtime() - TimeUnit.SECONDS.toMillis((long) i9);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.y5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5769b.m4542I4(i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnFileUploadCanceled(final int i9) {
        this.f4919e.m4467t("JNile", "OnFileUploadCanceled: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.v5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5644b.m4556K4(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnFileUploadCompleted(final int i9, final int i10, final String str, final int i11, final String str2) {
        this.f4919e.m4467t("JNile", "OnFileUploadCompleted: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.z4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5781b.m4571M4(i9, i10, str, i11, str2);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnFileUploadPrepared(final int i9, final String[] strArr, final String[] strArr2, final String str) {
        this.f4919e.m4467t("JNile", "OnFileUploadPrepared: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.j5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5307b.m4587O4(i9, strArr, strArr2, str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnForwardOptionNotify(final int i9, final int i10, final int i11, int i12, int i13, final String[] strArr) {
        this.f4919e.m4467t("JNile", "OnForwardOptionNotify");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.p5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5459b.m4611R4(i10, i9, strArr, i11);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    public void OnHaveBeenKickedout(final int i9) {
        this.f4919e.m4467t("JNile", "OnHaveBeenKickedout");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.x4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5754b.m4627T4(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnJoinCompleted(final int i9, final boolean z8, final boolean z9) {
        this.f4919e.m4467t("JNile", "OnJoinCompleted: UID:" + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.i2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5267b.m4643V4(i9, z8, z9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    public void OnKickOldUserFailed(final int i9) {
        this.f4919e.m4467t("JNile", "OnKickOldUserFailed");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.q5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5495b.m4657X4(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnLaserPointerEnabled(final boolean z8) {
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.t4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5602b.m4664Y4(z8);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnLaserPointerReceived(final int i9, final float f9, final float f10) {
        this.f4919e.m4467t("JNile", "OnLaserPointerReceived: UID:" + i9 + ", (" + f9 + ", " + f10 + ")");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.a5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5069b.m4671Z4(i9, f9, f10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnLaserPointerRequestAck(final int i9) {
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.d5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5149b.m4678a5(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnLogUploadUrl(String str) {
        this.f4919e.m4467t("JNile", "OnLogUploadUrl: " + str);
        this.f4919e.m4466s(str);
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    public void OnMeetingHadBeenClosed(final int i9) {
        this.f4919e.m4467t("JNile", "OnMeetingHadBeenClosed");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.i4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5277b.m4694c5(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnMeetingHost() {
        this.f4919e.m4467t("JNile", "OnMeetingHost");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.o6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5442b.m4710e5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnMeetingHost2(final int[] iArr) {
        this.f4919e.m4467t("JNile", "OnMeetingHost2");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.h6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5239b.m4726g5(iArr);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnNetworkQualityNotify(final int i9, final int i10, final int i11, final int i12, final int i13, final int i14) {
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.w6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5736b.m4734h5(i9, i10, i11, i12, i13, i14);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnNewBlackListURL(final String str) {
        this.f4919e.m4467t("JNile", "OnNewBlackListURL");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.r5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5514b.m4742i5(str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnOldApp(final boolean z8) {
        this.f4919e.m4467t("JNile", "OnOldApp: " + z8);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.f6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5194b.m4750j5(z8);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnParticipantReset() {
        this.f4919e.m4467t("JNile", "OnParticipantReset");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.m1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5360b.m4766l5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnParticipantUpdateDone() {
        this.f4919e.m4467t("JNile", "OnParticipantUpdateDone");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.x5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5756b.m4782n5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnPhonelineInviteResult(final int i9) {
        this.f4919e.m4467t("JNile", "OnPhonelineInviteResult");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.b6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5108b.m4790o5(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnPinVideoResult(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnPinVideoResult: request_id = " + i9 + ", " + i10);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.e3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5168b.m4806q5(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnPreJoinCompleted(final int i9, final boolean z8, final boolean z9) {
        this.f4919e.m4467t("JNile", "OnPreJoinCompleted: UID:" + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.t5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5604b.m4822s5(i9, z8, z9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnPreJoinUserState(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnPreJoinUserState");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.h5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5236b.m4838u5(i10, i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnQNAHostHangup() {
        this.f4919e.m4467t("JNile", "OnQNAHostHangup");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.v6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5646b.m4846v5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnQNAHostPicking() {
        this.f4919e.m4467t("JNile", "OnQNAHostPicking");
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.t6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5608b.m4854w5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRTCError(final int i9) {
        this.f4919e.m4467t("JNile", "OnRTCError: " + i9);
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.p6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5464b.m4862x5(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRejectLimitUser(final String str) {
        this.f4919e.m4467t("JNile", "OnRejectLimitUser");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.y4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5767b.m4879z5(str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRemoveHostAck(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnRemoveHostAck");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.s6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5580b.m4490B5(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRemoveHostNotify(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnRemoveHostNotify");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.n5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5414b.m4506D5(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRequestAllowRecording(final int i9) {
        this.f4919e.m4467t("JNile", "OnRequestAllowRecording: [" + i9 + "]");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.g4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5214b.m4522F5(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRequestCollectLog(boolean z8) {
        this.f4919e.m4467t("JNile", "OnRequestCollectLog: " + z8);
        this.f4919e.m4465r(this.f4940z, this.f4938x.f66b.f63b, z8);
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRequestCollectLogNow(boolean z8) {
        this.f4919e.m4467t("JNile", "OnRequestCollectLogNow: " + z8);
        if (z8) {
            this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.j6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5312b.m4529G5();
                }
            });
        } else {
            m4940g3();
        }
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRequestMuteStatusChanged(final int i9, boolean z8, final boolean z9) {
        this.f4919e.m4467t("JNile", "OnRequestMuteStatusChanged: [" + i9 + "] " + z9);
        if (z8 == z9) {
            this.f4919e.m4460i("JNile", " > Mute status is identical, ignore");
        } else {
            f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.p3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5454b.m4550J5(i9, z9);
                }
            });
        }
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRequestVerifyUser(final int[] iArr) {
        this.f4919e.m4467t("JNile", "OnRequestVerifyUser: " + Arrays.toString(iArr));
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.t2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5598b.m4564L5(iArr);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRollCallStartNotify(final int i9, final int i10, final int i11) {
        this.f4919e.m4467t("JNile", "OnRollCallStartNotify");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.g6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5219b.m4580N5(i9, i10, i11);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnRollCallStop() {
        this.f4919e.m4467t("JNile", "OnRollCallStop");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.a6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5073b.m4596P5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnStats(String str) {
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnStatusChanged(final int i9) {
        this.f4919e.m4467t("JNile", "OnStatusChanged: " + i9);
        final Status statusM4992a = Status.m4992a(i9);
        if (Status.JOINED == statusM4992a) {
            f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.u5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5626b.nQueryChatMsgCount();
                }
            });
        }
        this.f4923i.post(new Runnable() { // from class: com.cyberlink.clrtc.w5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5733b.m4604Q5(statusM4992a, i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnStopDTAck(final int i9) {
        this.f4919e.m4467t("JNile", "OnStopDTAck");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.x6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5757b.m4620S5(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnStopDTByHost() {
        this.f4919e.m4467t("JNile", "OnStopDTByHost");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.x1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5747b.m4636U5();
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnUnderRecording(final int[] iArr) {
        this.f4919e.m4467t("JNile", "OnUnderRecording: " + Arrays.toString(iArr));
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.l4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5344b.m4651W5(iArr);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnUpdateActiveList(final int[] iArr, final int[] iArr2, final boolean z8) {
        this.f4919e.m4467t("JNile", "OnUpdateActiveList: " + z8 + ", " + Arrays.toString(iArr));
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.n7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5419b.m4665Y5(iArr, iArr2, z8);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnUpdateBRStatus(final String[] strArr, final int[] iArr) {
        this.f4919e.m4467t("JNile", "OnUpdateBRStatus");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.q4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5492b.m4679a6(strArr, iArr);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnUpdateParticipants(final int[] iArr, final boolean[] zArr, final boolean[] zArr2, final String[] strArr, final String[] strArr2, final boolean[] zArr3, final boolean[] zArr4, final String[] strArr3, final boolean[] zArr5, final int[] iArr2, final int[] iArr3, final int[] iArr4, final int[] iArr5, final int[] iArr6) {
        this.f4919e.m4467t("JNile", "OnUpdateParticipants: " + Arrays.toString(iArr));
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.b5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5093b.m4695c6(iArr, strArr, strArr2, zArr, zArr2, zArr3, zArr4, strArr3, zArr5, iArr2, iArr3, iArr4, iArr5, iArr6);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnUpdateRCDuration(final int i9) {
        this.f4919e.m4467t("JNile", "OnUpdateRCDuration");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.f5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5192b.m4719f6(i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnUpdateSpeechList(final int[] iArr, final boolean[] zArr) {
        this.f4919e.m4467t("JNile", "OnUpdateSpeechList: " + Arrays.toString(iArr) + ": " + Arrays.toString(zArr));
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.k6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5329b.m4735h6(iArr, zArr);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnVerifyFailed(int i9) {
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnWRBackAck(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnWRBackAck");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.g5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5216b.m4751j6(i10, i9);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnWRBackNotify(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "OnWRBackNotify");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.u
            @Override // java.lang.Runnable
            public final void run() {
                this.f5609b.m4767l6(i9, i10);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnWRMsg(final String str) {
        this.f4919e.m4467t("JNile", "OnWRMsg");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.l6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5347b.m4783n6(str);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnWRUserList(final int[] iArr, final String[] strArr) {
        this.f4919e.m4467t("JNile", "OnWRUserList");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.i5
            @Override // java.lang.Runnable
            public final void run() {
                this.f5279b.m4799p6(iArr, strArr);
            }
        });
    }

    @Override // com.cyberlink.clrtc.NileNativeCallback
    @Keep
    public void OnWaitingRoomEnabled(final boolean z8) {
        this.f4919e.m4467t("JNile", "OnWaitingRoomEnabled");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.o4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5439b.m4815r6(z8);
            }
        });
    }

    /* renamed from: P7 */
    public void m4912P7(final int i9) {
        this.f4919e.m4467t("JNile", "removeHost userId : " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.a3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5065b.m4597P6(i9);
            }
        });
    }

    /* renamed from: Q7 */
    public void m4913Q7(final C0012b c0012b, final boolean z8, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "requestAudioMute: " + c0012b.f66b.f63b + ", " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.o2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5433b.m4613R6(c0012b, z8, abstractC6381r);
            }
        });
    }

    /* renamed from: R7 */
    public void m4914R7(final C0012b c0012b, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "requestKickUser: " + c0012b.f66b.f63b);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.b3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5087b.m4629T6(c0012b, abstractC6381r);
            }
        });
    }

    /* renamed from: S7 */
    public void m4915S7(final boolean z8, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "requestLaserPointer[" + z8 + "]");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.f7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5196b.m4645V6(z8, abstractC6381r);
            }
        });
    }

    /* renamed from: T7 */
    public void m4916T7(C0012b c0012b, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        final int i9 = c0012b.f66b.f63b;
        this.f4919e.m4467t("JNile", "requestPauseRecording: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.z2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5776b.m4659X6(i9, abstractC6381r);
            }
        });
    }

    /* renamed from: U7 */
    public final void m4917U7(int i9, ChatMsg chatMsg) {
        m4936e3();
        HashSet<ChatMsg> hashSet = this.f4911B.get(i9);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.f4911B.put(i9, hashSet);
        }
        hashSet.add(chatMsg);
    }

    /* renamed from: V7 */
    public void m4918V7() {
        if (!m4889E3("android.permission.CAMERA")) {
            this.f4919e.m4460i("JNile", "resume capturer but permission denied");
            return;
        }
        if (this.f4936v == null) {
            m4978z8();
        }
        if (this.f4936v == null || !m4900I3()) {
            return;
        }
        this.f4936v.m4442q();
    }

    /* renamed from: W2 */
    public void m4919W2(final int i9) {
        this.f4919e.m4467t("JNile", "addHost userId : " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.w2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5725b.m4823s6(i9);
            }
        });
    }

    /* renamed from: W7 */
    public ChatMsg m4920W7(String str) {
        final int iDecrementAndGet = f4908F.decrementAndGet();
        this.f4919e.m4467t("JNile", "sendFileMessage: [" + iDecrementAndGet + "] " + str);
        if (m4901I7()) {
            return null;
        }
        final C0011a c0011a = this.f4938x.f66b;
        final ChatMsg chatMsg = new ChatMsg(c0011a, iDecrementAndGet, m4488B3(), false, str, 3);
        chatMsg.m5017h(ChatMsg.Status.SENDING);
        this.f4910A.put(iDecrementAndGet, chatMsg);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.t0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5590b.m4666Y6(iDecrementAndGet, c0011a, chatMsg);
            }
        });
        return chatMsg;
    }

    /* renamed from: X2 */
    public final C0012b m4921X2(int i9, String str, String str2, boolean z8, boolean z9, boolean z10, boolean z11, String str3, boolean z12, int i10, int i11, int i12, int i13, int i14) {
        boolean z13;
        m4936e3();
        C0012b c0012b = this.f4930p.get(Integer.valueOf(i9));
        if (c0012b == null) {
            this.f4919e.m4460i("JNile", " > Not exists, create a new one for: " + i9);
            c0012b = new C0012b(m4923Y2(i9, str, str2));
            this.f4930p.put(Integer.valueOf(i9), c0012b);
            z13 = true;
        } else {
            if (!c0012b.f66b.m66a(str2)) {
                this.f4919e.m4468u("JNile", " > Its CL account is violated: " + c0012b.f66b.f65d + " > " + str2);
            }
            z13 = false;
        }
        boolean zM72B = c0012b.m72B(str) | z13 | c0012b.m81K(z8) | c0012b.m102u(z9) | c0012b.m106y(z10) | c0012b.m75E(i13 == ParticipantState.ParticipantState_Pre_Join.value) | c0012b.m105x(str3) | c0012b.m103v(z12) | c0012b.m80J(NetQuality.Quality.m4474a(i10)) | c0012b.m104w(NetQuality.Quality.m4474a(i11)) | c0012b.m74D(PlatformType.m4989a(i12)) | c0012b.m79I(ParticipantState.m4987b(i13)) | c0012b.m71A(i14);
        ActiveMedia activeMediaRemove = this.f4931q.remove(Integer.valueOf(i9));
        if (activeMediaRemove != null) {
            this.f4919e.m4468u("JNile", " > active participant is picked up, UID: " + i9);
            zM72B |= c0012b.m101t(activeMediaRemove);
        }
        if (z8 && c0012b.m91j() && c0012b.m83b().f5375b == ActiveMedia.Type.WEBCAM) {
            int i15 = c0012b.m83b().f5374a;
            ActiveMedia.Type type = ActiveMedia.Type.ICON;
            zM72B |= c0012b.m101t(new ActiveMedia(i15, type));
            this.f4919e.m4467t("JNile", " > streamId: " + c0012b.m83b().f5374a + " -> UID: " + i9 + ", " + type);
        }
        if (!zM72B) {
            return null;
        }
        this.f4919e.m4467t("JNile", " > Update info for: " + i9);
        return c0012b;
    }

    /* renamed from: X7 */
    public void m4922X7(final PointF pointF) {
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.j3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5302b.m4673Z6(pointF);
            }
        });
    }

    /* renamed from: Y2 */
    public final C0011a m4923Y2(int i9, String str, String str2) {
        m4936e3();
        C0011a c0011a = this.f4929o.get(i9);
        if (c0011a == null) {
            C0011a c0011a2 = new C0011a(i9, str, str2);
            this.f4929o.put(i9, c0011a2);
            m4885C3(c0011a2);
            return c0011a2;
        }
        c0011a.m70e(str);
        if (!(!c0011a.m66a(str2))) {
            return c0011a;
        }
        this.f4919e.m4468u("JNile", " > Its CL account is violated: " + c0011a.f65d + " > " + str2);
        return c0011a;
    }

    /* renamed from: Y7 */
    public ChatMsg m4924Y7(String str) {
        final int iDecrementAndGet = f4908F.decrementAndGet();
        this.f4919e.m4467t("JNile", "sendMessage: [" + iDecrementAndGet + "] " + str);
        if (m4901I7()) {
            return null;
        }
        final C0011a c0011a = this.f4938x.f66b;
        final ChatMsg chatMsg = new ChatMsg(c0011a, iDecrementAndGet, m4488B3(), false, str, 1);
        chatMsg.m5017h(ChatMsg.Status.SENDING);
        this.f4910A.put(iDecrementAndGet, chatMsg);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.o1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5429b.m4680a7(iDecrementAndGet, c0011a, chatMsg);
            }
        });
        return chatMsg;
    }

    /* renamed from: Z2 */
    public void m4925Z2(int i9, SurfaceViewRenderer surfaceViewRenderer) {
        m4938f3(surfaceViewRenderer);
        VideoRenderer videoRenderer = new VideoRenderer(surfaceViewRenderer);
        this.f4932r.add(videoRenderer);
        nAddVideoRenderer(i9, C0718b.m3549a(VideoRenderer.class, videoRenderer, "nativeVideoRenderer"));
    }

    /* renamed from: Z7 */
    public void m4926Z7(final String str, final String str2, final String str3, final int i9, final String str4, final String str5) {
        this.f4919e.m4467t("JNile", "sendPhoneLineInvite domainKey : " + str + " QServerIP : " + str2 + " CS210IP :" + str3 + " CS210Port : " + i9 + " callerExt : " + str4 + " calleeExt : " + str5);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.q2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5483b.m4688b7(str, str2, str3, i9, str4, str5);
            }
        });
    }

    @Override // com.cyberlink.clrtc.C1090p7.a
    /* renamed from: a */
    public void mo4927a(final int i9) {
        this.f4919e.m4467t("JNile", "onVolumeChanged:" + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.v
            @Override // java.lang.Runnable
            public final void run() {
                this.f5630b.m4551J6(i9);
            }
        });
    }

    /* renamed from: a3 */
    public void m4928a3(final List<Integer> list) {
        this.f4919e.m4467t("JNile", "allowAllEnterMeeting: " + list);
        if (list.isEmpty()) {
            return;
        }
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.s2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5573b.m4831t6(list);
            }
        });
    }

    /* renamed from: a8 */
    public void m4929a8(final int i9) {
        this.f4919e.m4467t("JNile", "setActivateNum : " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.l1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5337b.m4696c7(i9);
            }
        });
    }

    /* renamed from: b3 */
    public void m4930b3(final int i9) {
        this.f4919e.m4467t("JNile", "allowEnterMeeting userId : " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.v2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5637b.m4839u6(i9);
            }
        });
    }

    /* renamed from: b8 */
    public void m4931b8(final boolean z8) {
        this.f4919e.m4467t("JNile", "setAllowMuteAnother isAllow : " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.z3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5779b.m4704d7(z8);
            }
        });
    }

    /* renamed from: c3 */
    public final void m4932c3(JSONObject jSONObject) throws JSONException {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f4922h.getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            String typeName = activeNetworkInfo.getTypeName();
            String subtypeName = activeNetworkInfo.getSubtypeName();
            if (!TextUtils.isEmpty(typeName)) {
                jSONObject.put("NETWORK", typeName);
            }
            if (!TextUtils.isEmpty(subtypeName)) {
                jSONObject.put("NETWORK_TYPE", subtypeName);
            }
            if (!"Mobile".equalsIgnoreCase(typeName)) {
                return;
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) this.f4922h.getSystemService("phone");
        if (telephonyManager != null) {
            jSONObject.put("ISP", telephonyManager.getNetworkOperatorName());
            if (telephonyManager.isNetworkRoaming()) {
                jSONObject.put("ROAMING", true);
            }
        }
    }

    /* renamed from: c8 */
    public void m4933c8(final boolean z8) {
        this.f4919e.m4467t("JNile", "setAllowRecordingEnabled enabled : " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.m3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5362b.m4712e7(z8);
            }
        });
    }

    /* renamed from: d3 */
    public void m4934d3() {
        this.f4919e.m4467t("JNile", "cancelPhoneLineInvite");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.b2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5086b.m4847v6();
            }
        });
    }

    /* renamed from: d8 */
    public void m4935d8(File file) {
        this.f4919e.m4467t("JNile", "setAppLogPath: " + file.getAbsolutePath());
        this.f4919e.m4463p(file);
    }

    /* renamed from: e3 */
    public final void m4936e3() {
        if (this.f4924j.get() == Thread.currentThread().getId()) {
            return;
        }
        throw new IllegalStateException("JNile should work on tid:" + this.f4924j.get() + " but is called on tid:" + Thread.currentThread().getId());
    }

    /* renamed from: e8 */
    public void m4937e8(final int i9) {
        this.f4919e.m4467t("JNile", "setBlackListVersion: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.n6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5417b.m4720f7(i9);
            }
        });
    }

    /* renamed from: f3 */
    public final void m4938f3(SurfaceViewRenderer surfaceViewRenderer) {
        if (!this.f4935u.contains(surfaceViewRenderer)) {
            this.f4935u.add(surfaceViewRenderer);
            return;
        }
        throw new IllegalArgumentException("This renderer has been set before: " + surfaceViewRenderer);
    }

    /* renamed from: f8 */
    public void m4939f8(final boolean z8, Intent intent, final AbstractC6381r<DisplayMode, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "setDesktopSharingEnabled[" + z8 + "]");
        if (z8) {
            if (intent == null) {
                this.f4919e.m4467t("JNile", " > setDesktopSharingEnabled[true] permission denied");
                if (abstractC6381r != null) {
                    abstractC6381r.m24507e();
                    return;
                }
                return;
            }
            this.f4937w.m4998v(intent);
            m4971v8();
        }
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.u6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5627b.m4736h7(z8, abstractC6381r);
            }
        });
    }

    /* renamed from: g3 */
    public void m4940g3() {
        this.f4919e.m4467t("JNile", "collectLog");
        this.f4919e.m4456e();
    }

    /* renamed from: g8 */
    public void m4941g8(final String str) {
        this.f4919e.m4467t("JNile", "setDeviceId: " + str);
        f4909G.submit(new Callable() { // from class: com.cyberlink.clrtc.m6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f5369b.m4744i7(str);
            }
        });
    }

    /* renamed from: h3 */
    public void m4942h3() {
        this.f4919e.m4467t("JNile", "confirmJoin");
        if (m4889E3("android.permission.CAMERA") && this.f4936v == null) {
            m4978z8();
        }
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.y2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5765b.m4855w6();
            }
        });
    }

    /* renamed from: h8 */
    public void m4943h8(SurfaceViewRenderer surfaceViewRenderer) {
        m4945i8(surfaceViewRenderer, null);
    }

    /* renamed from: i3 */
    public void m4944i3(C1019i c1019i, boolean z8) {
        m4903J3(c1019i, z8 ? EventType.S_VOICE_CALL : EventType.VOICE_CALL);
    }

    /* renamed from: i8 */
    public void m4945i8(SurfaceViewRenderer surfaceViewRenderer, SurfaceViewRenderer surfaceViewRenderer2) {
        m4938f3(surfaceViewRenderer);
        this.f4933s = new VideoRenderer(surfaceViewRenderer);
        if (surfaceViewRenderer2 != null) {
            m4938f3(surfaceViewRenderer2);
            this.f4934t = new VideoRenderer(surfaceViewRenderer2);
        }
    }

    /* renamed from: j3 */
    public void m4946j3(C1019i c1019i, boolean z8) {
        m4903J3(c1019i, z8 ? EventType.S_GROUP_CALL : EventType.GROUP_CALL);
    }

    /* renamed from: j8 */
    public void m4947j8(final boolean z8, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "setLowResolution: " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.g3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5211b.m4760k7(z8, abstractC6381r);
            }
        });
    }

    /* renamed from: k3 */
    public void m4948k3() {
        Logging.disableWebRTCLogToFile();
    }

    /* renamed from: k8 */
    public void m4949k8() {
        this.f4919e.m4467t("JNile", "setLowerAllHands");
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.y3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5766b.m4768l7();
            }
        });
    }

    /* renamed from: l3 */
    public final void m4950l3() {
        if (!this.f4932r.isEmpty()) {
            Iterator<VideoRenderer> it = this.f4932r.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
            this.f4932r.clear();
        }
        VideoRenderer videoRenderer = this.f4933s;
        if (videoRenderer != null) {
            videoRenderer.dispose();
            this.f4933s = null;
        }
        VideoRenderer videoRenderer2 = this.f4934t;
        if (videoRenderer2 != null) {
            videoRenderer2.dispose();
            this.f4934t = null;
        }
    }

    /* renamed from: l8 */
    public void m4951l8(final boolean z8, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "setMicrophoneEnabled[" + z8 + "]");
        if (!z8 || m4889E3("android.permission.RECORD_AUDIO")) {
            f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.u1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5615b.m4784n7(z8, abstractC6381r);
                }
            });
            return;
        }
        this.f4919e.m4467t("JNile", " > setMicrophoneEnabled[true] permission denied");
        if (abstractC6381r != null) {
            abstractC6381r.m24507e();
        }
    }

    /* renamed from: m3 */
    public final void m4952m3() {
        this.f4919e.m4467t("JNile", "DEVICE:" + Build.DEVICE + ", MODEL:" + Build.MODEL + ", PRODUCT:" + Build.PRODUCT + ", BOARD:" + Build.BOARD + ", HARDWARE:" + Build.HARDWARE + ", BRAND:" + Build.BRAND + ", MANUFACTURER:" + Build.MANUFACTURER);
        StringBuilder sb = new StringBuilder();
        sb.append("Built-In AEC(");
        sb.append(C4664a.m18632b());
        sb.append("):  HW(");
        sb.append(C4664a.m18638j());
        sb.append("),  !AOSP(");
        sb.append(C4664a.m18637i() ^ true);
        sb.append(") or forceAOSP(");
        sb.append(C4665b.m18666r());
        sb.append("),  !Blacklist(");
        sb.append(C4665b.m18667s() ^ true);
        sb.append(C1121t.m5156e() ? " *manual" : "");
        sb.append("),  Sampling-Rate(");
        sb.append(C4665b.m18651c());
        sb.append("Hz");
        sb.append(C1121t.m5155d() ? " *manual" : "");
        sb.append(")");
        this.f4919e.m4467t("JNile", sb.toString());
    }

    /* renamed from: m8 */
    public void m4953m8(final boolean z8, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "setMuteAll: " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.w3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5727b.m4800p7(z8, abstractC6381r);
            }
        });
    }

    /* renamed from: n3 */
    public void m4954n3() {
        Logging.enableWebRTCLogToFile(new Logging.LogBack() { // from class: com.cyberlink.clrtc.r3
            @Override // org.webrtc.Logging.LogBack
            public final void log(String str, String str2) {
                this.f5511a.m4863x6(str, str2);
            }
        });
    }

    /* renamed from: n8 */
    public void m4955n8(InterfaceC1113s interfaceC1113s) {
        if (interfaceC1113s == null) {
            interfaceC1113s = C1010h.f5225a;
        }
        this.f4925k = interfaceC1113s;
    }

    /* renamed from: o3 */
    public void m4956o3() {
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.y6
            @Override // java.lang.Runnable
            public final void run() {
                this.f5771b.m4872y6();
            }
        });
    }

    /* renamed from: o8 */
    public void m4957o8(final int i9) {
        this.f4919e.m4467t("JNile", "setPhonelineExtension: " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.e1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5164b.m4808q7(i9);
            }
        });
    }

    /* renamed from: p3 */
    public void m4958p3() {
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.d0
            @Override // java.lang.Runnable
            public final void run() {
                this.f5138b.m4880z6();
            }
        });
    }

    /* renamed from: p8 */
    public void m4959p8(final int i9, final List<Integer> list) {
        this.f4919e.m4467t("JNile", "setPinVideoList: request_id = " + i9);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.d2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5141b.m4816r7(list, i9);
            }
        });
    }

    /* renamed from: q3 */
    public void m4960q3(final boolean z8, final int[] iArr, final ForwardOptionAction forwardOptionAction) {
        this.f4919e.m4467t("JNile", "forwardGlobalAddOrRemove isAdd : " + z8 + " / optionActionId : " + forwardOptionAction);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.n3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5406b.m4482A6(iArr, forwardOptionAction, z8);
            }
        });
    }

    /* renamed from: q8 */
    public void m4961q8(final boolean z8, final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "setPinkNoiseEnabled: " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.t1
            @Override // java.lang.Runnable
            public final void run() {
                this.f5594b.m4824s7(z8, i9, i10);
            }
        });
    }

    /* renamed from: r3 */
    public void m4962r3(final boolean z8, final ForwardOptionAction forwardOptionAction) {
        this.f4919e.m4467t("JNile", "forwardGlobalSwitch isOn : " + z8 + " / optionActionId : " + forwardOptionAction);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.x3
            @Override // java.lang.Runnable
            public final void run() {
                this.f5751b.m4491B6(forwardOptionAction, z8);
            }
        });
    }

    /* renamed from: r8 */
    public void m4963r8(final boolean z8, final int i9) {
        this.f4919e.m4467t("JNile", "setPoorConnectionAudioHintEnabled: " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.e7
            @Override // java.lang.Runnable
            public final void run() {
                this.f5175b.m4832t7(z8, i9);
            }
        });
    }

    /* renamed from: s3 */
    public final String m4964s3(Context context) throws PackageManager.NameNotFoundException {
        String str = "0.0.0 (unknown)";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            str = packageInfo.versionName + " (" + packageInfo.versionCode + ")";
            this.f4919e.m4460i("JNile", "App version: " + str + ", " + m4869y3());
            return str;
        } catch (PackageManager.NameNotFoundException e9) {
            this.f4919e.m4459h("JNile", "Cannot retrieve app version info: " + e9.getMessage());
            return str;
        }
    }

    /* renamed from: s8 */
    public void m4965s8(InterfaceC1081o7 interfaceC1081o7) {
        if (interfaceC1081o7 == null) {
            interfaceC1081o7 = C1010h.f5226b;
        }
        this.f4926l = interfaceC1081o7;
    }

    /* renamed from: t3 */
    public C0012b m4966t3() {
        return this.f4938x;
    }

    /* renamed from: t8 */
    public void m4967t8(final String... strArr) {
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.a4
            @Override // java.lang.Runnable
            public final void run() {
                this.f5067b.m4840u7(strArr);
            }
        });
    }

    /* renamed from: u3 */
    public final String m4968u3() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DEVICE", Build.DEVICE);
            jSONObject.put("MODEL", Build.MODEL);
            jSONObject.put("PRODUCT", Build.PRODUCT);
            jSONObject.put("BOARD", Build.BOARD);
            jSONObject.put("HARDWARE", Build.HARDWARE);
            jSONObject.put("BRAND", Build.BRAND);
            jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
            jSONObject.put("RELEASE", Build.VERSION.RELEASE);
            m4932c3(jSONObject);
        } catch (Exception e9) {
            this.f4919e.m4459h("JNile", "Cannot retrieve device info: " + e9.getMessage());
        }
        return jSONObject.toString();
    }

    /* renamed from: u8 */
    public void m4969u8(final boolean z8, final int i9) {
        this.f4919e.m4467t("JNile", "setRaiseOrLowerHand doRaise : " + z8);
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.u2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5618b.m4848v7(i9, z8);
            }
        });
    }

    /* renamed from: v3 */
    public DisplayMode m4970v3() {
        return this.f4913D;
    }

    /* renamed from: v8 */
    public final void m4971v8() {
        WindowManager windowManager = (WindowManager) this.f4922h.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int iMin = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        Size size = iMin >= 1080 ? new Size(1920, 1080) : iMin >= 720 ? new Size(1280, 720) : new Size(640, 360);
        if (displayMetrics.heightPixels > displayMetrics.widthPixels) {
            size = new Size(size.getHeight(), size.getWidth());
        }
        m4973w8(size.getWidth(), size.getHeight());
        C0956b c0956b = this.f4937w;
        if (c0956b != null) {
            c0956b.m4999w(size.getWidth(), size.getHeight());
        }
    }

    /* renamed from: w3 */
    public long m4972w3() {
        return nGetEventTimeLimit();
    }

    /* renamed from: w8 */
    public void m4973w8(final int i9, final int i10) {
        this.f4919e.m4467t("JNile", "setScreenResolution: " + i9 + "x" + i10);
        f4909G.submit(new Callable() { // from class: com.cyberlink.clrtc.v0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f5632b.m4856w7(i9, i10);
            }
        });
    }

    /* renamed from: x3 */
    public long m4974x3() {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.f4914E);
    }

    /* renamed from: x8 */
    public void m4975x8(final List<C0012b> list, final boolean z8) {
        this.f4919e.m4467t("JNile", "setVerified: " + list.size() + ", " + z8);
        if (list.isEmpty()) {
            return;
        }
        f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.x2
            @Override // java.lang.Runnable
            public final void run() {
                this.f5748b.m4864x7(list, z8);
            }
        });
    }

    /* renamed from: y8 */
    public void m4976y8(final boolean z8, final AbstractC6381r<Boolean, Void> abstractC6381r) {
        this.f4919e.m4467t("JNile", "setWebcamEnabled[" + z8 + "]");
        if (z8 && !m4889E3("android.permission.CAMERA")) {
            this.f4919e.m4467t("JNile", " > setWebcamEnabled[true] permission denied");
            if (abstractC6381r != null) {
                abstractC6381r.m24507e();
                return;
            }
            return;
        }
        if (!z8 || m4887D3()) {
            if (z8 && this.f4936v == null) {
                m4978z8();
            }
            f4909G.submit(new Runnable() { // from class: com.cyberlink.clrtc.p1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5449b.m4881z7(z8, abstractC6381r);
                }
            });
            return;
        }
        this.f4919e.m4467t("JNile", " > setWebcamEnabled[true] hardware absent");
        if (abstractC6381r != null) {
            abstractC6381r.m24507e();
        }
    }

    /* renamed from: z3 */
    public int m4977z3() {
        return nGetParticipantLimit();
    }

    /* renamed from: z8 */
    public final void m4978z8() {
        m4884B8();
        if (this.f4933s != null && m4887D3()) {
            if (this.f4915a) {
                this.f4919e.m4467t("JNile", " > setupCameraCapturer, nile network has been released");
                return;
            }
            C0947a c0947a = new C0947a(this.f4922h, this.f4921g, this.f4919e);
            this.f4936v = c0947a;
            c0947a.m4441p(640, 360, 24);
            this.f4936v.m4443r(this.f4933s);
            nSetVideoSource(0, this.f4936v.f4842f);
        }
    }

    /* renamed from: com.cyberlink.clrtc.NileNetwork$c */
    public static class C0946c {

        /* renamed from: a */
        public boolean f5050a;

        /* renamed from: b */
        public int f5051b;

        /* renamed from: c */
        public int f5052c;

        /* renamed from: d */
        public int f5053d;

        /* renamed from: e */
        public int f5054e;

        /* renamed from: f */
        public int f5055f;

        public C0946c() {
            this.f5050a = false;
            this.f5051b = 0;
            this.f5052c = -1;
            this.f5053d = -1;
            this.f5054e = -1;
            this.f5055f = -1;
        }

        /* renamed from: a */
        public void m4994a() {
            this.f5050a = false;
            this.f5051b = 0;
            this.f5055f = -1;
            this.f5054e = -1;
            this.f5053d = -1;
            this.f5052c = -1;
        }

        public /* synthetic */ C0946c(C0944a c0944a) {
            this();
        }
    }
}
