package com.cyberlink.you.chat;

import android.os.StrictMode;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyberlink.util.PriorityThreadPoolExecutor;
import com.cyberlink.you.Globals;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.chat.p035p.C2913a;
import com.cyberlink.you.database.ArchiveMessageObj$Type;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.C2997x0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.StringReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jivesoftware.smack.C5585d;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.InterfaceC5583c;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.AbstractC5586IQ;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.CLResumed;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.StreamMgmt;
import org.jivesoftware.smack.proxy.ProxyInfo;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.ping.PingManager;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import p116k4.C5154j;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5176q0;
import p116k4.C5180s;
import p136m3.C5321e;
import p201t3.C6301o;
import p209u2.C6383t;
import p209u2.C6385v;
import p209u2.C6389z;
import p209u2.ThreadFactoryC6373j;
import p214u7.InterfaceC6426a;
import p218v2.C6456d;
import p222v6.C6492e;
import p222v6.InterfaceC6490c;
import p236x2.C6566a;
import p254z2.C6818b;

/* loaded from: classes.dex */
public class XMPPManager {

    /* renamed from: F */
    public static List<C2874y> f12477F;

    /* renamed from: s */
    public static String f12484s;

    /* renamed from: t */
    public static String f12485t;

    /* renamed from: u */
    public static long f12486u;

    /* renamed from: v */
    public static long f12487v;

    /* renamed from: a */
    public Future<Void> f12492a;

    /* renamed from: b */
    public Random f12493b;

    /* renamed from: c */
    public final ExecutorService f12494c;

    /* renamed from: d */
    public final ExecutorService f12495d;

    /* renamed from: e */
    public final ExecutorService f12496e;

    /* renamed from: f */
    public final ExecutorService f12497f;

    /* renamed from: g */
    public final InterfaceC6490c f12498g;

    /* renamed from: h */
    public final AtomicBoolean f12499h;

    /* renamed from: i */
    public InterfaceC5583c f12500i;

    /* renamed from: j */
    public final InterfaceC6426a f12501j;

    /* renamed from: k */
    public InterfaceC2870u f12502k;

    /* renamed from: l */
    public XMPPConnection f12503l;

    /* renamed from: m */
    public final C6389z<InterfaceC2851b0> f12504m;

    /* renamed from: n */
    public final C6389z<AbstractC2868s> f12505n;

    /* renamed from: o */
    public final C6389z<InterfaceC2867r> f12506o;

    /* renamed from: p */
    public final AtomicBoolean f12507p;

    /* renamed from: q */
    public List<InterfaceC2869t> f12508q;

    /* renamed from: r */
    public final C6389z<InterfaceC2849a0> f12509r;

    /* renamed from: w */
    public static Presence.Type f12488w = Presence.Type.unavailable;

    /* renamed from: x */
    public static SSLContext f12489x = null;

    /* renamed from: y */
    public static boolean f12490y = false;

    /* renamed from: z */
    public static List<C2904l> f12491z = new ArrayList();

    /* renamed from: A */
    public static boolean f12472A = false;

    /* renamed from: B */
    public static Map<String, AbstractC5594b> f12473B = new ConcurrentHashMap();

    /* renamed from: C */
    public static String f12474C = "";

    /* renamed from: D */
    public static boolean f12475D = true;

    /* renamed from: E */
    public static final HashSet<String> f12476E = new HashSet<>();

    /* renamed from: G */
    public static int f12478G = 0;

    /* renamed from: H */
    public static int f12479H = 0;

    /* renamed from: I */
    public static long f12480I = -1;

    /* renamed from: J */
    public static int f12481J = -1;

    /* renamed from: K */
    public static final Object f12482K = new Object();

    /* renamed from: L */
    public static boolean f12483L = false;

    public enum HandleType {
        UNKNOWN,
        XMPP,
        HEART_BEAT,
        GCM,
        UPDATE_NEW_VERSION,
        CHATROOM,
        NOTICE
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$a */
    public class C2848a extends C6389z.a<InterfaceC2867r> {

        /* renamed from: a */
        public final /* synthetic */ C2904l f12518a;

        public C2848a(C2904l c2904l) {
            this.f12518a = c2904l;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC2867r interfaceC2867r) {
            interfaceC2867r.mo14289a(this.f12518a);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$a0 */
    public interface InterfaceC2849a0 extends C6389z.b {
        /* renamed from: F */
        default void mo5716F(String str) {
        }

        /* renamed from: p */
        void mo5718p(String str, Date date);
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$b */
    public class C2850b extends PriorityThreadPoolExecutor.AbstractCallableC1403c<Boolean> {
        public C2850b(PriorityThreadPoolExecutor.Priority priority) {
            super(priority);
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            try {
                long unused = XMPPManager.f12486u = System.currentTimeMillis();
                long unused2 = XMPPManager.f12487v = 0L;
                XMPPManager.this.f12503l.m22000k();
                XMPPManager.this.m14254l1();
                return Boolean.TRUE;
            } catch (SmackException.ConnectionException e9) {
                Log.e("XMPPManager", "[connect] ConnectionException: " + e9.m21960a());
                for (int i9 = 0; i9 < e9.m21960a().size(); i9++) {
                    Log.e("XMPPManager", e9.m21960a().get(i9).m3628a());
                }
                return Boolean.FALSE;
            } catch (Exception e10) {
                Log.e("XMPPManager", "[connect] Something wrong", e10);
                return Boolean.FALSE;
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$b0 */
    public interface InterfaceC2851b0 extends C6389z.b {
        /* renamed from: h0 */
        void mo13974h0(boolean z8);
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$c */
    public class C2852c implements InterfaceC2872w {

        /* renamed from: a */
        public final /* synthetic */ AbstractC5594b f12521a;

        /* renamed from: b */
        public final /* synthetic */ InterfaceC2873x f12522b;

        public C2852c(AbstractC5594b abstractC5594b, InterfaceC2873x interfaceC2873x) {
            this.f12521a = abstractC5594b;
            this.f12522b = interfaceC2873x;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2872w
        /* renamed from: a */
        public void mo14280a(String str) {
            Log.d("XMPPManager", "connect fail. " + str);
            InterfaceC2873x interfaceC2873x = this.f12522b;
            if (interfaceC2873x != null) {
                interfaceC2873x.mo5816a();
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2872w
        public void onSuccess() {
            if (XMPPManager.this.m14231Y(this.f12521a)) {
                Log.d("XMPPManager", "connect and send success.");
                InterfaceC2873x interfaceC2873x = this.f12522b;
                if (interfaceC2873x != null) {
                    interfaceC2873x.onSuccess();
                    return;
                }
                return;
            }
            Log.d("XMPPManager", "connect success and send fail.");
            InterfaceC2873x interfaceC2873x2 = this.f12522b;
            if (interfaceC2873x2 != null) {
                interfaceC2873x2.mo5816a();
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$d */
    public class C2853d extends Thread {

        /* renamed from: b */
        public final /* synthetic */ AbstractC5594b f12524b;

        /* renamed from: c */
        public final /* synthetic */ InterfaceC2873x f12525c;

        public C2853d(AbstractC5594b abstractC5594b, InterfaceC2873x interfaceC2873x) {
            this.f12524b = abstractC5594b;
            this.f12525c = interfaceC2873x;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ULogUtility.m16670f("XMPPManager", "[send] Try to reconnect XMPP.");
            Thread.currentThread().setName("XMPP connect");
            Log.d("XMPPManager", "connect: " + XMPPManager.this.m14220S());
            if (XMPPManager.this.m14231Y(this.f12524b)) {
                Log.d("XMPPManager", "send success.");
                InterfaceC2873x interfaceC2873x = this.f12525c;
                if (interfaceC2873x != null) {
                    interfaceC2873x.onSuccess();
                    return;
                }
                return;
            }
            Log.d("XMPPManager", "send fail.");
            InterfaceC2873x interfaceC2873x2 = this.f12525c;
            if (interfaceC2873x2 != null) {
                interfaceC2873x2.mo5816a();
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$e */
    public class C2854e extends Thread {
        public C2854e() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e9) {
                e9.printStackTrace();
            }
            XMPPManager.this.m14252k1(Presence.Type.available);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$f */
    public class C2855f extends Thread {

        /* renamed from: b */
        public final /* synthetic */ String f12528b;

        /* renamed from: c */
        public final /* synthetic */ String f12529c;

        /* renamed from: d */
        public final /* synthetic */ boolean f12530d;

        /* renamed from: e */
        public final /* synthetic */ InterfaceC2872w f12531e;

        public C2855f(String str, String str2, boolean z8, InterfaceC2872w interfaceC2872w) {
            this.f12528b = str;
            this.f12529c = str2;
            this.f12530d = z8;
            this.f12531e = interfaceC2872w;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Thread.currentThread().setName("XMPP Auth");
            Pair pairM14227W = XMPPManager.this.m14227W(this.f12528b, this.f12529c, this.f12530d);
            if (((Boolean) pairM14227W.first).booleanValue()) {
                InterfaceC2872w interfaceC2872w = this.f12531e;
                if (interfaceC2872w != null) {
                    interfaceC2872w.onSuccess();
                    return;
                }
                return;
            }
            InterfaceC2872w interfaceC2872w2 = this.f12531e;
            if (interfaceC2872w2 != null) {
                interfaceC2872w2.mo14280a((String) pairM14227W.second);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$g */
    public class C2856g implements FriendsClient.InterfaceC3052j {

        /* renamed from: a */
        public final /* synthetic */ String f12533a;

        /* renamed from: b */
        public final /* synthetic */ String f12534b;

        /* renamed from: c */
        public final /* synthetic */ boolean f12535c;

        /* renamed from: d */
        public final /* synthetic */ InterfaceC2872w f12536d;

        public C2856g(String str, String str2, boolean z8, InterfaceC2872w interfaceC2872w) {
            this.f12533a = str;
            this.f12534b = str2;
            this.f12535c = z8;
            this.f12536d = interfaceC2872w;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3052j
        /* renamed from: a */
        public void mo12854a() {
            InterfaceC2872w interfaceC2872w = this.f12536d;
            if (interfaceC2872w != null) {
                interfaceC2872w.mo14280a("LoadCommandUrl failed");
            }
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3052j
        public void onSuccess() {
            Pair pairM14227W = XMPPManager.this.m14227W(this.f12533a, this.f12534b, this.f12535c);
            if (((Boolean) pairM14227W.first).booleanValue()) {
                InterfaceC2872w interfaceC2872w = this.f12536d;
                if (interfaceC2872w != null) {
                    interfaceC2872w.onSuccess();
                    return;
                }
                return;
            }
            InterfaceC2872w interfaceC2872w2 = this.f12536d;
            if (interfaceC2872w2 != null) {
                interfaceC2872w2.mo14280a((String) pairM14227W.second);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$h */
    public class C2857h extends C6389z.a<InterfaceC2849a0> {

        /* renamed from: a */
        public final /* synthetic */ boolean f12538a;

        /* renamed from: b */
        public final /* synthetic */ String f12539b;

        /* renamed from: c */
        public final /* synthetic */ Date f12540c;

        public C2857h(boolean z8, String str, Date date) {
            this.f12538a = z8;
            this.f12539b = str;
            this.f12540c = date;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC2849a0 interfaceC2849a0) {
            if (this.f12538a) {
                interfaceC2849a0.mo5716F(this.f12539b);
            } else {
                interfaceC2849a0.mo5718p(this.f12539b, this.f12540c);
            }
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$i */
    public class C2858i extends PriorityThreadPoolExecutor.AbstractCallableC1403c<Void> {
        public C2858i(PriorityThreadPoolExecutor.Priority priority) {
            super(priority);
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Void call() {
            for (AbstractC5594b abstractC5594b : XMPPManager.f12473B.values()) {
                ULogUtility.m16670f("XMPPManager", "start to resend message : " + abstractC5594b.m22161k());
                XMPPManager.this.m14231Y(abstractC5594b);
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$j */
    public class C2859j implements InterfaceC6490c {
        public C2859j() {
        }

        @Override // p222v6.InterfaceC6490c
        public void authenticated(XMPPConnection xMPPConnection) {
            Log.i("XMPPManager", "[library] authenticated");
            ULogUtility.m16664G("[library] authenticated", "XMPP Connection");
        }

        @Override // p222v6.InterfaceC6490c
        public void connected(XMPPConnection xMPPConnection) {
            Globals.m7388i0().m7495U2(false);
            XMPPManager.this.m14224U0(true);
            Log.i("XMPPManager", "[library] connected");
            ULogUtility.m16664G("[library] connected", "XMPP Connection");
        }

        @Override // p222v6.InterfaceC6490c
        public void connectionClosed() {
            Globals.m7388i0().m7495U2(true);
            XMPPManager.this.m14256m1(Presence.Type.unavailable);
            Log.w("XMPPManager", "[library] connectionClosed");
            ULogUtility.m16664G("[library] connectionClosed", "XMPP Connection");
            XMPPManager.this.m14224U0(false);
            XMPPManager.this.m14230X0();
        }

        @Override // p222v6.InterfaceC6490c
        public void connectionClosedOnError(Exception exc) {
            String strM14251k0;
            String str = "[library] connectionClosedOnError " + exc.getMessage();
            Log.w("XMPPManager", str);
            ULogUtility.m16664G(str, "XMPP Connection");
            XMPPManager.this.m14224U0(false);
            Globals.m7388i0().m7495U2(true);
            XMPPManager.this.m14256m1(Presence.Type.unavailable);
            XMPPManager.this.m14214N();
            if (C6456d.m24714D() == null || !C6456d.m24714D().m24748G() || !(exc instanceof XMPPException.StreamErrorException) || !"conflict".equals(((XMPPException.StreamErrorException) exc).m22017a().m22168a()) || (strM14251k0 = XMPPManager.this.m14251k0()) == null || !strM14251k0.equals("LOGOUT")) {
                XMPPManager.this.m14230X0();
            } else {
                Globals.m7388i0().m7402B();
                XMPPManager.this.m14223U();
            }
        }

        @Override // p222v6.InterfaceC6490c
        public void reconnectingIn(int i9) {
            if (i9 <= 1) {
                String str = "reconnectingIn " + i9 + " seconds";
                Log.d("XMPPManager", str);
                ULogUtility.m16664G(str, "XMPP Connection");
            }
        }

        @Override // p222v6.InterfaceC6490c
        public void reconnectionFailed(Exception exc) {
            Globals.m7388i0().m7495U2(true);
            XMPPManager.this.m14256m1(Presence.Type.unavailable);
            String str = "reconnectionFailed. " + exc.toString();
            if (exc instanceof SmackException.ConnectionException) {
                str = str + StringUtils.SPACE + ((SmackException.ConnectionException) exc).m21960a();
            }
            Log.w("XMPPManager", str);
            ULogUtility.m16664G(str, "XMPP Connection");
            XMPPManager.this.m14224U0(false);
            XMPPManager.this.m14230X0();
        }

        @Override // p222v6.InterfaceC6490c
        public void reconnectionSuccessful() {
            Globals.m7388i0().m7495U2(false);
            XMPPManager.this.m14224U0(true);
            ULogUtility.m16670f("XMPPManager", "[reconnectionSuccessful] start to resend message");
            XMPPManager.this.m14238c1();
            Log.i("XMPPManager", "[library] reconnectionSuccessful");
            ULogUtility.m16664G("[library] reconnectionSuccessful", "XMPP Connection");
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$k */
    public class C2860k extends PriorityThreadPoolExecutor.AbstractRunnableC1405e {
        public C2860k(PriorityThreadPoolExecutor.Priority priority) {
            super(priority);
        }

        @Override // java.lang.Runnable
        public void run() {
            XMPPManager.this.f12493b.setSeed(System.currentTimeMillis());
            int iNextInt = XMPPManager.this.f12493b.nextInt(3) + 3;
            XMPPManager.m14199v1("[reconnect] task start");
            if (XMPPManager.this.f12503l != null) {
                XMPPManager.m14199v1("[reconnect] mXMPPConnection isConnected = " + XMPPManager.this.f12503l.mo21971H());
            }
            if (C6456d.m24714D().m24748G()) {
                int i9 = 0;
                while (true) {
                    if (XMPPManager.this.f12503l == null || XMPPManager.this.f12503l.mo21971H() || !C6456d.m24714D().m24748G()) {
                        break;
                    }
                    XMPPManager.m14199v1("[reconnect] retry loop start | attempts = " + i9);
                    i9++;
                    if (i9 > 15) {
                        try {
                            XMPPManager.m14199v1("[reconnect] tried 15 times, give up");
                            XMPPManager.this.f12503l.m22004o();
                            XMPPManager.this.m14258n1(null);
                            C6456d.m24714D().m24755P();
                            break;
                        } catch (SmackException.NotConnectedException e9) {
                            e9.printStackTrace();
                        }
                    } else {
                        int iPow = (int) (iNextInt * Math.pow(1.2d, i9));
                        XMPPManager.m14199v1("[reconnect] waiting for " + iPow + " second(s) to reconnect");
                        while (iPow > 0) {
                            SystemClock.sleep(1000L);
                            iPow--;
                            XMPPManager.this.f12498g.reconnectingIn(iPow);
                        }
                        try {
                            XMPPManager.m14199v1("[reconnect] reconnect #" + i9);
                            if (XMPPManager.this.f12503l != null) {
                                long unused = XMPPManager.f12486u = System.currentTimeMillis();
                                long unused2 = XMPPManager.f12487v = 0L;
                                XMPPManager.this.f12503l.m22000k();
                                XMPPManager.m14199v1("[reconnect] isConnected");
                                Globals.m7388i0().m7495U2(false);
                                XMPPManager.this.m14254l1();
                            }
                        } catch (Exception e10) {
                            XMPPManager.this.f12498g.reconnectionFailed(e10);
                            XMPPManager.m14199v1("[reconnect] failed: " + e10.toString());
                            if (Globals.m7396z1()) {
                                XMPPManager.m14199v1("[reconnect] retry in background");
                                break;
                            }
                        }
                    }
                }
                if (XMPPManager.this.f12503l != null && XMPPManager.this.f12503l.mo21971H() && C6456d.m24714D().m24748G()) {
                    XMPPManager.m14199v1("[reconnect] query archive");
                    XMPPArchiveHelper.m14147x();
                }
            }
            XMPPManager.this.f12499h.set(false);
            XMPPManager.m14199v1("[reconnect] task exit");
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$l */
    public class C2861l extends C6389z.a<AbstractC2868s> {

        /* renamed from: a */
        public final /* synthetic */ C2904l f12545a;

        /* renamed from: b */
        public final /* synthetic */ AtomicBoolean f12546b;

        public C2861l(C2904l c2904l, AtomicBoolean atomicBoolean) {
            this.f12545a = c2904l;
            this.f12546b = atomicBoolean;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(AbstractC2868s abstractC2868s) {
            String strMo8240e;
            if (!abstractC2868s.f12557b || (strMo8240e = abstractC2868s.mo8240e(this.f12545a)) == null) {
                return;
            }
            this.f12546b.set(true);
            ULogUtility.m16659B("Notification", "Not notify. is synchronized handled by " + strMo8240e);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$m */
    public class C2862m extends C6389z.a<AbstractC2868s> {

        /* renamed from: a */
        public final /* synthetic */ C2904l f12548a;

        /* renamed from: b */
        public final /* synthetic */ AtomicBoolean f12549b;

        public C2862m(C2904l c2904l, AtomicBoolean atomicBoolean) {
            this.f12548a = c2904l;
            this.f12549b = atomicBoolean;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(AbstractC2868s abstractC2868s) {
            String strMo8240e;
            if (abstractC2868s.f12557b || (strMo8240e = abstractC2868s.mo8240e(this.f12548a)) == null) {
                return;
            }
            this.f12549b.set(true);
            ULogUtility.m16659B("Notification", "Not notify. is asynchronous handled by " + strMo8240e);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$n */
    public class C2863n extends C6389z.a<AbstractC2868s> {

        /* renamed from: a */
        public final /* synthetic */ C2904l f12551a;

        public C2863n(C2904l c2904l) {
            this.f12551a = c2904l;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(AbstractC2868s abstractC2868s) {
            abstractC2868s.mo11921d(this.f12551a);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$o */
    public class C2864o extends C6389z.a<AbstractC2868s> {
        public C2864o() {
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(AbstractC2868s abstractC2868s) {
            abstractC2868s.mo13118c();
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$p */
    public class C2865p extends PriorityThreadPoolExecutor.AbstractCallableC1403c<Void> {
        public C2865p(PriorityThreadPoolExecutor.Priority priority) {
            super(priority);
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Void call() {
            ULogUtility.m16670f("XMPPManager", " XMPP disconnect");
            if (XMPPManager.this.f12503l != null) {
                try {
                    XMPPManager.this.f12503l.m22004o();
                } catch (Exception e9) {
                    C5154j.m20076j(e9);
                }
            }
            Globals.m7388i0().m7495U2(true);
            XMPPManager.this.m14258n1(null);
            ULogUtility.m16670f("XMPPManager", " XMPP disconnected");
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$q */
    public class C2866q extends C6389z.a<InterfaceC2851b0> {

        /* renamed from: a */
        public final /* synthetic */ boolean f12555a;

        public C2866q(boolean z8) {
            this.f12555a = z8;
        }

        @Override // p209u2.C6389z.a
        /* renamed from: a */
        public void mo5634a(String str, Throwable th) {
            XMPPManager.m14199v1(th.toString());
            C5154j.m20076j(th);
        }

        @Override // p209u2.C6389z.a
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void mo5635b(InterfaceC2851b0 interfaceC2851b0) {
            interfaceC2851b0.mo13974h0(this.f12555a);
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$r */
    public interface InterfaceC2867r extends C6389z.b {
        /* renamed from: a */
        void mo14289a(C2904l c2904l);
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$s */
    public static abstract class AbstractC2868s implements C6389z.b {

        /* renamed from: b */
        public final boolean f12557b;

        public AbstractC2868s(boolean z8) {
            this.f12557b = z8;
        }

        /* renamed from: c */
        public boolean mo13118c() {
            return false;
        }

        /* renamed from: d */
        public void mo11921d(C2904l c2904l) {
        }

        /* renamed from: e */
        public abstract String mo8240e(C2904l c2904l);
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$t */
    public interface InterfaceC2869t {
        /* renamed from: a */
        void mo9838a(String str);
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$u */
    public interface InterfaceC2870u {
        /* renamed from: a */
        void mo9677a(String str);
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$v */
    public class RunnableC2871v implements Runnable {

        /* renamed from: b */
        public final C2904l f12558b;

        /* renamed from: c */
        public final C2898i f12559c;

        /* renamed from: d */
        public final HandleType f12560d;

        public RunnableC2871v(C2904l c2904l, C2898i c2898i, HandleType handleType) {
            this.f12558b = c2904l;
            this.f12559c = c2898i;
            this.f12560d = handleType;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0082 A[Catch: Exception -> 0x0086, TRY_LEAVE, TryCatch #0 {Exception -> 0x0086, blocks: (B:21:0x0074, B:23:0x0082), top: B:35:0x0074 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            boolean z8;
            TopicObj topicObjM14984n;
            System.currentTimeMillis();
            C5321e.m20824o().m20830A0(this.f12558b);
            Map<String, String> mapM14373e = this.f12559c.m14373e();
            C5321e.m20824o().m20896x0(this.f12558b, mapM14373e);
            if (CLUtility.m16433E1() && !this.f12558b.m14396K()) {
                String str = mapM14373e.get("eventType");
                if (XMPPManager.m14163H0(this.f12558b)) {
                    z8 = false;
                    if ("bulletin.topic.created".equals(str) || "bulletin.post.created".equals(str)) {
                        try {
                            topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(mapM14373e.get("topicId")));
                            if (topicObjM14984n != null) {
                                z8 = topicObjM14984n.f13103r;
                            }
                        } catch (Exception e9) {
                            C5154j.m20076j(e9);
                        }
                    }
                    if (XMPPManager.m14154B0(this.f12558b) && !XMPPManager.m14161F0(this.f12558b) && !z8) {
                        XMPPManager.this.m14228W0(this.f12558b, this.f12560d, true);
                    }
                } else {
                    if ("invite.friend.accepted".equals(str)) {
                        NotificationHelper.m14070N(this.f12558b, this.f12560d);
                    } else {
                        if ("invite.friend.created".equals(str)) {
                            NotificationHelper.m14075S(this.f12558b, this.f12560d);
                        }
                        z8 = false;
                        if ("bulletin.topic.created".equals(str)) {
                            topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(mapM14373e.get("topicId")));
                            if (topicObjM14984n != null) {
                            }
                            if (XMPPManager.m14154B0(this.f12558b)) {
                                XMPPManager.this.m14228W0(this.f12558b, this.f12560d, true);
                            }
                        }
                    }
                    z8 = true;
                    if ("bulletin.topic.created".equals(str)) {
                    }
                }
            }
            System.currentTimeMillis();
        }
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$w */
    public interface InterfaceC2872w {
        /* renamed from: a */
        void mo14280a(String str);

        void onSuccess();
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$x */
    public interface InterfaceC2873x {
        /* renamed from: a */
        void mo5816a();

        void onSuccess();
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$z */
    public static class C2875z {

        /* renamed from: a */
        public static final XMPPManager f12566a = new XMPPManager(null);
    }

    public /* synthetic */ XMPPManager(C2858i c2858i) {
        this();
    }

    /* renamed from: B0 */
    public static boolean m14154B0(C2904l c2904l) {
        C2898i c2898i = (C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event");
        if (c2898i == null) {
            return false;
        }
        String strM22346k = C5616j.m22346k(c2904l.m14388C());
        Map<String, String> mapM14373e = c2898i.m14373e();
        String str = mapM14373e.get("userId");
        String str2 = mapM14373e.get("eventType");
        String str3 = mapM14373e.get("actor");
        if ((str2.equals("group.member.created") && strM22346k.equals(str)) || str2.equals("group.member.created.v2")) {
            return true;
        }
        return (str2.equals("bulletin.topic.created") || str2.equals("bulletin.post.created")) && !str3.equals(String.valueOf(Globals.m7388i0().m7568k1()));
    }

    /* renamed from: C0 */
    public static boolean m14156C0(String str) {
        return "chat.message.recalled".equals(str) || "chat.message.deleted".equals(str);
    }

    /* renamed from: E0 */
    public static boolean m14159E0(MessageObj messageObj, C2904l c2904l) {
        if (C6383t.m24512a(messageObj.m14777o(), c2904l.m14446v())) {
            MessageObj.MessageType messageTypeM14778p = messageObj.m14778p();
            MessageObj.MessageType messageType = MessageObj.MessageType.ENCRYPTED_MSG;
            if (messageTypeM14778p == messageType && c2904l.m14389D() != messageType) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: F0 */
    public static boolean m14161F0(C2904l c2904l) {
        String strM14428m = c2904l.m14428m();
        String strM14388C = c2904l.m14388C();
        String strM22347l = C5616j.m22347l(strM14428m);
        return strM22347l != null && strM22347l.equals(C5616j.m22346k(strM14388C));
    }

    /* renamed from: G0 */
    public static boolean m14162G0(C2904l c2904l) {
        String strValueOf = String.valueOf(Globals.m7388i0().m7568k1());
        String strM14430n = c2904l.m14430n();
        return strM14430n != null && strM14430n.equals(strValueOf);
    }

    /* renamed from: H0 */
    public static boolean m14163H0(C2904l c2904l) {
        String strM22346k = C5616j.m22346k(c2904l.m14428m());
        return strM22346k != null && strM22346k.equals(String.valueOf(Globals.m7388i0().m7568k1()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J0 */
    public /* synthetic */ Pair m14164J0(String str, String str2, XMPPTCPConnection xMPPTCPConnection, String str3) {
        SASLMechanism.SASLFailure sASLFailureM22187a;
        String strM22194v;
        String str4;
        Thread.currentThread().setName("XMPP Auth Callable");
        Boolean bool = Boolean.FALSE;
        try {
            m14199v1("Connect to (" + f12478G + ") " + str + " by " + str2);
            f12486u = System.currentTimeMillis();
            f12487v = 0L;
            xMPPTCPConnection.m22000k();
            Log.w("XMPPManager", "[doAuthenticate2XMPPServer] Spent " + (System.currentTimeMillis() - f12486u) + " ms");
            xMPPTCPConnection.m21993d(this.f12500i, null);
            m14199v1("Connected");
            String strM14247i = m14247i();
            xMPPTCPConnection.m22218w0(strM14247i);
            if (strM14247i == null) {
                xMPPTCPConnection.mo21972I(str2, str3, Globals.m7388i0().m7598q1());
                m14199v1("Login as " + xMPPTCPConnection.mo21967D());
            } else {
                xMPPTCPConnection.m22215t0(str2, str3, Globals.m7388i0().m7598q1());
                m14199v1("Resume as " + xMPPTCPConnection.mo21967D() + " session id : " + strM14247i);
            }
            m14258n1(xMPPTCPConnection);
            m14242f1(str2);
            m14254l1();
            this.f12496e.submit(new RunnableC2928w0());
            Globals.m7388i0().m7495U2(false);
            f12483L = true;
            m14199v1("Auth complete");
            str4 = "Success";
            bool = Boolean.TRUE;
        } catch (Exception e9) {
            ULogUtility.m16684t("XMPPManager", "Connect Fail, spent " + (System.currentTimeMillis() - f12486u) + " ms");
            m14199v1(e9.toString());
            String string = e9.toString();
            if (e9 instanceof SmackException.ConnectionException) {
                SmackException.ConnectionException connectionException = (SmackException.ConnectionException) e9;
                m14199v1(connectionException.m21960a().toString());
                for (int i9 = 0; i9 < connectionException.m21960a().size(); i9++) {
                    m14199v1(connectionException.m21960a().get(i9).m3628a());
                }
            } else {
                if ((e9 instanceof SASLErrorException) && (sASLFailureM22187a = ((SASLErrorException) e9).m22187a()) != null && (strM22194v = sASLFailureM22187a.m22194v()) != null && strM22194v.contains("not-authorized") && m14215O()) {
                    f12483L = false;
                    m14199v1("Log out");
                    str4 = "Force Logout";
                }
                f12478G++;
                m14258n1(null);
                m14242f1("");
            }
            str4 = string;
            f12478G++;
            m14258n1(null);
            m14242f1("");
        }
        return new Pair(bool, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K0 */
    public /* synthetic */ void m14165K0(ArrayList arrayList) {
        for (C2904l c2904l : f12491z) {
            ULogUtility.m16680p("XMPPManager", "XMPPManager:handleArchivePacket() handle archive message : " + m14262p1(c2904l.toString(), c2904l.m14389D()));
            if (!m14173Z(c2904l)) {
                if (c2904l.m14394I()) {
                    arrayList.add(Pair.create(c2904l, Boolean.valueOf(m14263q0(c2904l, true, true))));
                } else if (c2904l.m14389D() == MessageObj.MessageType.DeliveryReceipt) {
                    m14273v0(c2904l);
                    arrayList.add(Pair.create(c2904l, Boolean.TRUE));
                } else {
                    Map<String, String> mapM14373e = ((C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event")).m14373e();
                    if (m14156C0(mapM14373e.get("eventType"))) {
                        String str = mapM14373e.get(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
                        C2950b0.m14904c().m14870e(str, Long.parseLong(mapM14373e.get("actor")), ArchiveMessageObj$Type.DELETE, c2904l.m14386A().getTime());
                        arrayList.add(Pair.create(c2904l, Boolean.valueOf(m14265r0(str))));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M0 */
    public /* synthetic */ void m14167M0(AbstractC5594b abstractC5594b) throws JSONException {
        String strM14446v;
        HandleType handleType = HandleType.XMPP;
        m14269t0(abstractC5594b, handleType, true);
        if (abstractC5594b instanceof Message) {
            C2904l c2904l = new C2904l((Message) abstractC5594b, handleType);
            if (!c2904l.m14399N() || (strM14446v = c2904l.m14446v()) == null || strM14446v.isEmpty() || !new Date(Globals.m7388i0().m7515Z()).before(c2904l.m14422j())) {
                return;
            }
            Globals.m7388i0().m7461N2(c2904l.m14422j().getTime(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m14168N0() {
        ULogUtility.m16664G("ping failed", "XMPP Connection");
        m14223U();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m14169O0(C2904l c2904l, AtomicBoolean atomicBoolean, boolean z8, HandleType handleType) {
        AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
        this.f12505n.m24540f(new C2862m(c2904l, atomicBoolean2));
        try {
            boolean z9 = atomicBoolean.get() || atomicBoolean2.get();
            boolean zM14162G0 = m14162G0(c2904l);
            String strM14446v = c2904l.m14446v();
            if (!z9 && !zM14162G0 && z8) {
                m14267s0(c2904l, handleType);
                return;
            }
            if (!z8) {
                ULogUtility.m16659B(handleType.toString(), "Not notify (This message is not the last message in HB). msgId: " + strM14446v);
                return;
            }
            ULogUtility.m16659B(handleType.toString(), "Not notify. isHandled=" + z9 + " isMyselfMessage=" + zM14162G0 + ". msgId: " + strM14446v);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: P */
    public static void m14170P(C2904l c2904l) {
        String strM7587o0 = Globals.m7388i0().m7587o0();
        String strM14418h = c2904l.m14418h();
        String strM14388C = c2904l.m14388C();
        if (strM14418h == null || strM7587o0 == null || strM14388C == null) {
            return;
        }
        String str = strM14418h.equals(strM7587o0) ? strM14388C : strM14418h;
        if (c2904l.m14391F()) {
            if ((strM14418h.equals(str) && strM14388C.equals(strM7587o0)) || (strM14418h.equals(strM7587o0) && strM14388C.equals(str))) {
                m14186i1(str);
                return;
            }
            return;
        }
        if ((strM14418h.equals(str) || (strM14418h.equals(strM7587o0) && strM14388C.equals(str))) && c2904l.m14389D() != MessageObj.MessageType.DeliveryReceipt) {
            String strM14430n = c2904l.m14430n();
            String strValueOf = String.valueOf(Globals.m7388i0().m7568k1());
            if (strM14430n == null || !strM14430n.equals(strValueOf)) {
                return;
            }
            m14186i1(str);
        }
    }

    /* renamed from: P0 */
    public static /* synthetic */ void m14171P0(MessageObj messageObj) {
        Group groupM15650P = FriendsClient.m15650P(messageObj.m14772j());
        if (groupM15650P != null) {
            C2950b0.m14912k().m15069f(groupM15650P);
        }
    }

    /* renamed from: T0 */
    public static void m14172T0(C2904l c2904l) {
        if (c2904l.m14389D() != MessageObj.MessageType.ENCRYPTED_SESSION) {
            return;
        }
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(c2904l.m14446v());
        if (messageObjM15179r != null && messageObjM15179r.m14778p() == MessageObj.MessageType.NewVersion) {
            C2950b0.m14916o().m15169h(c2904l.m14446v());
            ULogUtility.m16670f("CLSM", "Delete local sessionInfo: " + c2904l.m14446v());
        }
        C2898i c2898i = (C2898i) c2904l.m14426l("encrypted", "U");
        String strM14450x = c2904l.m14450x();
        String strM14372d = c2898i.m14372d("sessionId");
        String strM14374f = c2898i.m14374f();
        if (!C5170o0.m20170e(strM14450x)) {
            C2889b.m14298h().m14309m(strM14450x, c2904l.m14422j().getTime(), strM14372d, strM14374f);
            return;
        }
        Group groupM14182f0 = m14182f0(c2904l);
        if (strM14372d == null || strM14374f == null || groupM14182f0 == null) {
            ULogUtility.m16670f("CLSM", "SessionInfo update w/o data for GID: " + c2904l.m14418h());
            return;
        }
        ULogUtility.m16670f("CLSM", "update sessionInfo: [" + groupM14182f0.f13727n + "] " + groupM14182f0.f13717d);
        C2889b.m14298h().m14315s(groupM14182f0.f13727n, c2904l.m14422j().getTime(), strM14372d, strM14374f);
    }

    /* renamed from: Z */
    public static boolean m14173Z(C2904l c2904l) {
        if (!c2904l.m14399N()) {
            return false;
        }
        String str = "";
        if (c2904l.m14428m() == null) {
            str = "\nFrom absent";
        }
        if (c2904l.m14388C() == null) {
            str = str + "\nTo absent";
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ULogUtility.m16684t("XMPPManager", c2904l.m14452z() + str);
        return true;
    }

    /* renamed from: b1 */
    public static void m14176b1(C2904l c2904l) {
        C2898i c2898i;
        if (c2904l.m14389D() != MessageObj.MessageType.Call) {
            return;
        }
        String strM14446v = c2904l.m14446v();
        if (C5616j.m22344i(strM14446v)) {
            return;
        }
        if ((strM14446v.startsWith("UPC") || strM14446v.startsWith("UMAC")) && (c2898i = (C2898i) c2904l.m14426l("call", "urn:xmpp:call:0")) != null) {
            String str = c2898i.m14373e().get("callId");
            if (C5616j.m22344i(str)) {
                return;
            }
            c2904l.m14445u0(str);
            ULogUtility.m16680p("XMPPManager", "[UPC] Replace msgId for call msg: " + strM14446v + " -> " + str);
        }
    }

    /* renamed from: d1 */
    public static void m14179d1(Group group, MessageObj messageObj) {
        String strM14777o = messageObj.m14777o();
        if (String.valueOf(Globals.m7388i0().m7568k1()).equals(messageObj.m14745G())) {
            messageObj.m14759U(C2950b0.m14904c().m14873h(strM14777o));
        } else if (C2950b0.m14904c().m14872g(strM14777o)) {
            if (!"5".equals(messageObj.m14740B())) {
                messageObj.m14762X("5");
            }
            if (group.m15748d() < messageObj.m14788z().getTime()) {
                group.m15753k(messageObj.m14788z().getTime());
                C2950b0.m14912k().m15062A(String.valueOf(group.f13727n), group, "LastRead");
            }
        }
        C2950b0.m14916o().m15157B(messageObj);
    }

    /* renamed from: f0 */
    public static Group m14182f0(C2904l c2904l) {
        Group groupM14185i0 = m14185i0(c2904l);
        if (groupM14185i0 != null) {
            return groupM14185i0;
        }
        String strM14418h = c2904l.m14418h();
        String strM14388C = c2904l.m14388C();
        FriendsClient friendsClient = new FriendsClient();
        List<Group> listM15713R = friendsClient.m15713R(strM14418h, strM14388C);
        friendsClient.m15717U0();
        if (listM15713R == null || listM15713R.isEmpty()) {
            ULogUtility.m16664G("Can not get group info", "HandleMessage");
            return groupM14185i0;
        }
        C2950b0.m14912k().m15071h(listM15713R);
        if (listM15713R.size() > 1) {
            StringBuilder sb = new StringBuilder("get multiple group: ");
            Iterator<Group> it = listM15713R.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(",");
            }
            ULogUtility.m16664G(sb.toString(), "HandleMessage");
        }
        return listM15713R.get(0);
    }

    /* renamed from: g0 */
    public static XMPPManager m14184g0() {
        return C2875z.f12566a;
    }

    /* renamed from: i0 */
    public static Group m14185i0(C2904l c2904l) {
        String strM14418h = c2904l.m14418h();
        String strM14388C = c2904l.m14388C();
        Group groupM15081r = C2950b0.m14912k().m15081r(strM14418h);
        return groupM15081r != null ? groupM15081r : C2950b0.m14912k().m15081r(strM14388C);
    }

    /* renamed from: i1 */
    public static void m14186i1(String str) {
        Log.d("XMPPManager", "[setIsScheduleSendListUpdate] chatId = " + str);
        String strM22345j = C5616j.m22345j(str);
        if (strM22345j == null) {
            return;
        }
        Log.d("XMPPManager", "[setIsScheduleSendListUpdate] chatId.contains '/', sChatId=  " + strM22345j);
        Globals.m7388i0().getSharedPreferences("cached_schedule_send_map", 0).edit().putBoolean(strM22345j + "_is_schedule_send_list_update", true).apply();
    }

    /* renamed from: k */
    public static List<AbstractC5594b> m14187k(String str, HandleType handleType) {
        StrictMode.noteSlowCall("ProcessStringToPacket");
        StringReader stringReader = new StringReader(str);
        ArrayList arrayList = new ArrayList();
        try {
            XmlPullParser xmlPullParserM22241d = PacketParserUtils.m22241d();
            xmlPullParserM22241d.setInput(stringReader);
            xmlPullParserM22241d.next();
            int eventType = xmlPullParserM22241d.getEventType();
            while (eventType == 2) {
                if (xmlPullParserM22241d.getName().equals("message")) {
                    try {
                        Message messageM22251n = PacketParserUtils.m22251n(xmlPullParserM22241d);
                        arrayList.add(messageM22251n);
                        if (handleType == HandleType.HEART_BEAT) {
                            ULogUtility.m16690z("[Parse query Message] parse result=" + messageM22251n.toString());
                        } else if (handleType == HandleType.GCM) {
                            ULogUtility.m16689y("[Parse query Message] parse result=" + messageM22251n.toString());
                        }
                    } catch (Exception unused) {
                    }
                }
                eventType = xmlPullParserM22241d.next();
            }
        } catch (Exception e9) {
            Log.e("XMPPManager", "Cannot parse message", e9);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0153 A[Catch: all -> 0x0295, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:7:0x0011, B:10:0x0023, B:11:0x0030, B:13:0x0036, B:15:0x003c, B:17:0x0089, B:18:0x008f, B:21:0x00b2, B:23:0x00ba, B:25:0x00c4, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x012a, B:33:0x0130, B:35:0x013a, B:36:0x0153, B:38:0x015b, B:40:0x0165, B:41:0x0171, B:43:0x0177, B:45:0x017d, B:47:0x01be, B:48:0x01c4, B:50:0x01ce, B:51:0x01e7, B:53:0x01ef, B:55:0x01f9, B:57:0x0203, B:59:0x0232, B:60:0x0237, B:62:0x0241, B:64:0x0270, B:65:0x0275, B:20:0x0099), top: B:71:0x0003, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x015b A[Catch: all -> 0x0295, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:7:0x0011, B:10:0x0023, B:11:0x0030, B:13:0x0036, B:15:0x003c, B:17:0x0089, B:18:0x008f, B:21:0x00b2, B:23:0x00ba, B:25:0x00c4, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x012a, B:33:0x0130, B:35:0x013a, B:36:0x0153, B:38:0x015b, B:40:0x0165, B:41:0x0171, B:43:0x0177, B:45:0x017d, B:47:0x01be, B:48:0x01c4, B:50:0x01ce, B:51:0x01e7, B:53:0x01ef, B:55:0x01f9, B:57:0x0203, B:59:0x0232, B:60:0x0237, B:62:0x0241, B:64:0x0270, B:65:0x0275, B:20:0x0099), top: B:71:0x0003, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01e7 A[Catch: all -> 0x0295, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:7:0x0011, B:10:0x0023, B:11:0x0030, B:13:0x0036, B:15:0x003c, B:17:0x0089, B:18:0x008f, B:21:0x00b2, B:23:0x00ba, B:25:0x00c4, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x012a, B:33:0x0130, B:35:0x013a, B:36:0x0153, B:38:0x015b, B:40:0x0165, B:41:0x0171, B:43:0x0177, B:45:0x017d, B:47:0x01be, B:48:0x01c4, B:50:0x01ce, B:51:0x01e7, B:53:0x01ef, B:55:0x01f9, B:57:0x0203, B:59:0x0232, B:60:0x0237, B:62:0x0241, B:64:0x0270, B:65:0x0275, B:20:0x0099), top: B:71:0x0003, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01ef A[Catch: all -> 0x0295, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:7:0x0011, B:10:0x0023, B:11:0x0030, B:13:0x0036, B:15:0x003c, B:17:0x0089, B:18:0x008f, B:21:0x00b2, B:23:0x00ba, B:25:0x00c4, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x012a, B:33:0x0130, B:35:0x013a, B:36:0x0153, B:38:0x015b, B:40:0x0165, B:41:0x0171, B:43:0x0177, B:45:0x017d, B:47:0x01be, B:48:0x01c4, B:50:0x01ce, B:51:0x01e7, B:53:0x01ef, B:55:0x01f9, B:57:0x0203, B:59:0x0232, B:60:0x0237, B:62:0x0241, B:64:0x0270, B:65:0x0275, B:20:0x0099), top: B:71:0x0003, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0203 A[Catch: all -> 0x0295, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:7:0x0011, B:10:0x0023, B:11:0x0030, B:13:0x0036, B:15:0x003c, B:17:0x0089, B:18:0x008f, B:21:0x00b2, B:23:0x00ba, B:25:0x00c4, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x012a, B:33:0x0130, B:35:0x013a, B:36:0x0153, B:38:0x015b, B:40:0x0165, B:41:0x0171, B:43:0x0177, B:45:0x017d, B:47:0x01be, B:48:0x01c4, B:50:0x01ce, B:51:0x01e7, B:53:0x01ef, B:55:0x01f9, B:57:0x0203, B:59:0x0232, B:60:0x0237, B:62:0x0241, B:64:0x0270, B:65:0x0275, B:20:0x0099), top: B:71:0x0003, inners: #1, #2, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0241 A[Catch: all -> 0x0295, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000e, B:7:0x0011, B:10:0x0023, B:11:0x0030, B:13:0x0036, B:15:0x003c, B:17:0x0089, B:18:0x008f, B:21:0x00b2, B:23:0x00ba, B:25:0x00c4, B:26:0x00d1, B:28:0x00d7, B:30:0x00dd, B:32:0x012a, B:33:0x0130, B:35:0x013a, B:36:0x0153, B:38:0x015b, B:40:0x0165, B:41:0x0171, B:43:0x0177, B:45:0x017d, B:47:0x01be, B:48:0x01c4, B:50:0x01ce, B:51:0x01e7, B:53:0x01ef, B:55:0x01f9, B:57:0x0203, B:59:0x0232, B:60:0x0237, B:62:0x0241, B:64:0x0270, B:65:0x0275, B:20:0x0099), top: B:71:0x0003, inners: #1, #2, #3 }] */
    /* renamed from: l */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void m14188l(boolean z8) {
        String strM15642G;
        String strM15642G2;
        String strM15642G3;
        String strM15642G4;
        String strM15642G5;
        long jCurrentTimeMillis = System.currentTimeMillis();
        m14199v1("UpdateServerList");
        if (!z8) {
            FriendsClient.m15643G0();
        }
        f12477F = new ArrayList();
        String strM15642G6 = FriendsClient.m15642G("chat", "xmpp.serversV3");
        if (strM15642G6 != null) {
            try {
                JSONArray jSONArray = new JSONArray(strM15642G6);
                m14199v1(jSONArray.toString());
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i9);
                    if (jSONObject != null) {
                        m14199v1("V3 " + jSONObject.getString("server") + ":" + jSONObject.getInt("port"));
                        C2874y c2874y = new C2874y(jSONObject.getString("server"), jSONObject.getInt("port"), jSONObject.getString("type"), jSONObject.getBoolean("tlsRequired"));
                        if (f12477F.contains(c2874y)) {
                            Log.d("Auth", "duplicate server !");
                        } else {
                            f12477F.add(c2874y);
                        }
                    }
                }
            } catch (JSONException unused) {
                m14199v1("Parse " + strM15642G6 + " failed");
            }
            if (!f12477F.isEmpty() && (strM15642G5 = FriendsClient.m15642G("chat", "xmpp.serversV2")) != null) {
                try {
                    JSONArray jSONArray2 = new JSONArray(strM15642G5);
                    m14199v1(jSONArray2.toString());
                    for (int i10 = 0; i10 < jSONArray2.length(); i10++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i10);
                        if (jSONObject2 != null) {
                            m14199v1("V2 " + jSONObject2.getString("server") + ":" + jSONObject2.getInt("port"));
                            C2874y c2874y2 = new C2874y(jSONObject2.getString("server"), jSONObject2.getInt("port"), jSONObject2.getString("type"), jSONObject2.getBoolean("tlsRequired"));
                            if (f12477F.contains(c2874y2)) {
                                Log.d("Auth", "duplicate server !");
                            } else {
                                f12477F.add(c2874y2);
                            }
                        }
                    }
                } catch (JSONException unused2) {
                    m14199v1("Parse " + strM15642G5 + " failed");
                }
                if (f12477F.size() != 0) {
                    if (f12477F.size() == 0) {
                    }
                    m14199v1("UpdateServerList done " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                }
            } else if (f12477F.size() != 0 || (strM15642G4 = FriendsClient.m15642G("chat", "xmpp.servers")) == null) {
                if (f12477F.size() == 0 && (strM15642G = FriendsClient.m15642G("chat", "xmpp.server")) != null) {
                    strM15642G2 = FriendsClient.m15642G("chat", "xmpp.port");
                    if (strM15642G2 != null) {
                        int i11 = NumberUtils.toInt(strM15642G2, 443);
                        m14199v1("V0 " + strM15642G + ":" + i11);
                        C2874y c2874y3 = new C2874y(strM15642G, i11);
                        if (!f12477F.contains(c2874y3)) {
                            f12477F.add(c2874y3);
                        }
                    }
                    strM15642G3 = FriendsClient.m15642G("chat", "xmpp.standardPort");
                    if (strM15642G3 != null) {
                        int i12 = NumberUtils.toInt(strM15642G3, 5222);
                        m14199v1("O " + strM15642G + ":" + i12);
                        C2874y c2874y4 = new C2874y(strM15642G, i12);
                        if (!f12477F.contains(c2874y4)) {
                            f12477F.add(c2874y4);
                        }
                    }
                }
                m14199v1("UpdateServerList done " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            } else {
                try {
                    JSONArray jSONArray3 = new JSONArray(strM15642G4);
                    m14199v1(jSONArray3.toString());
                    for (int i13 = 0; i13 < jSONArray3.length(); i13++) {
                        JSONObject jSONObject3 = jSONArray3.getJSONObject(i13);
                        if (jSONObject3 != null) {
                            m14199v1("V1 " + jSONObject3.getString("server") + ":" + jSONObject3.getInt("port"));
                            C2874y c2874y5 = new C2874y(jSONObject3.getString("server"), jSONObject3.getInt("port"));
                            if (f12477F.contains(c2874y5)) {
                                Log.d("Auth", "duplicate server !");
                            } else {
                                f12477F.add(c2874y5);
                            }
                        }
                    }
                } catch (JSONException unused3) {
                    m14199v1("Parse " + strM15642G4 + " failed");
                }
                if (f12477F.size() == 0) {
                    strM15642G2 = FriendsClient.m15642G("chat", "xmpp.port");
                    if (strM15642G2 != null) {
                    }
                    strM15642G3 = FriendsClient.m15642G("chat", "xmpp.standardPort");
                    if (strM15642G3 != null) {
                    }
                }
                m14199v1("UpdateServerList done " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            }
        } else if (!f12477F.isEmpty()) {
            if (f12477F.size() != 0) {
            }
        }
    }

    /* renamed from: v1 */
    public static void m14199v1(String str) {
        ULogUtility.m16664G(str, "XMPP Connection");
    }

    /* renamed from: A0 */
    public boolean m14204A0() {
        XMPPConnection xMPPConnection = this.f12503l;
        return xMPPConnection != null && xMPPConnection.mo21971H();
    }

    /* renamed from: D0 */
    public final boolean m14205D0(C2904l c2904l) {
        Group groupM14185i0 = m14185i0(c2904l);
        if (groupM14185i0 == null || groupM14185i0.f13735v <= 0) {
            return false;
        }
        return !c2904l.m14422j().after(new Date(groupM14185i0.f13735v + 500));
    }

    /* renamed from: G */
    public void m14206G(InterfaceC2849a0 interfaceC2849a0) {
        this.f12509r.m24539c(interfaceC2849a0);
    }

    /* renamed from: H */
    public void m14207H(AbstractC2868s abstractC2868s) {
        this.f12505n.m24539c(abstractC2868s);
    }

    /* renamed from: I */
    public void m14208I(InterfaceC2869t interfaceC2869t) {
        this.f12508q.add(interfaceC2869t);
    }

    /* renamed from: I0 */
    public final boolean m14209I0(Message message, HandleType handleType) {
        String strM7587o0 = Globals.m7388i0().m7587o0();
        String strM7511Y0 = Globals.m7388i0().m7511Y0();
        String strM22162l = message.m22162l();
        if (strM7511Y0 == null || strM22162l == null) {
            ULogUtility.m16664G("myJid or toJid is null.", handleType + "Receive");
            return true;
        }
        if (!strM22162l.startsWith(strM7587o0) || strM7511Y0.contains(strM22162l)) {
            return true;
        }
        ULogUtility.m16664G("resource id is wrong. myJid=" + strM7587o0 + " myResJid=" + strM7511Y0 + " toJid=" + strM22162l, handleType + "Receive");
        return false;
    }

    /* renamed from: J */
    public void m14210J(InterfaceC2867r interfaceC2867r) {
        this.f12506o.m24539c(interfaceC2867r);
    }

    /* renamed from: K */
    public void m14211K(InterfaceC2851b0 interfaceC2851b0) {
        this.f12504m.m24539c(interfaceC2851b0);
    }

    /* renamed from: L */
    public void m14212L(String str, String str2, boolean z8, InterfaceC2872w interfaceC2872w) {
        if (FriendsClient.m15680p0()) {
            new C2855f(str, str2, z8, interfaceC2872w).start();
        } else {
            FriendsClient.m15635C0(new C2856g(str, str2, z8, interfaceC2872w), true);
        }
    }

    /* renamed from: M */
    public Pair<Boolean, String> m14213M() {
        m14199v1("[authorize] start. isServerAPILoad=" + FriendsClient.m15680p0());
        if (!FriendsClient.m15680p0()) {
            m14199v1("[authorize] load server api start");
            boolean zM15643G0 = FriendsClient.m15643G0();
            m14199v1("[authorize] load server api end. success=" + zM15643G0);
            if (!zM15643G0) {
                return new Pair<>(Boolean.FALSE, "Init fail");
            }
        }
        return m14229X(true);
    }

    /* renamed from: N */
    public final void m14214N() {
        Future<Void> future = this.f12492a;
        if (future != null) {
            future.cancel(true);
        }
        this.f12492a = null;
    }

    /* renamed from: O */
    public final boolean m14215O() {
        String strM14251k0;
        if (C6456d.m24714D() == null || !C6456d.m24714D().m24748G() || (strM14251k0 = m14251k0()) == null || !(strM14251k0.equals("LOGOUT") || strM14251k0.equals("401"))) {
            return false;
        }
        Globals.m7388i0().m7402B();
        return true;
    }

    /* renamed from: Q */
    public final void m14216Q() throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis() - f12480I;
        if (jCurrentTimeMillis >= SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS) {
            f12480I = 0L;
            return;
        }
        try {
            m14199v1("waiting for next server list update, " + ((SsMediaSource.DEFAULT_LIVE_PRESENTATION_DELAY_MS - jCurrentTimeMillis) / 1000) + "s");
            Thread.sleep(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        } catch (InterruptedException e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: Q0 */
    public final void m14217Q0(StreamMgmt streamMgmt) {
        String strM22161k = streamMgmt.m22161k();
        if (strM22161k == null) {
            return;
        }
        f12473B.remove(strM22161k);
        this.f12509r.m24540f(new C2857h("nack".equals(streamMgmt.m22136w()), strM22161k, streamMgmt.m22137x()));
    }

    /* renamed from: R */
    public void m14218R() {
        this.f12508q.clear();
    }

    /* renamed from: R0 */
    public final void m14219R0(C2904l c2904l) {
        this.f12506o.m24540f(new C2848a(c2904l));
    }

    /* renamed from: S */
    public final boolean m14220S() {
        ULogUtility.m16670f("XMPPManager", "[connect] start");
        if (this.f12507p.getAndSet(true)) {
            ULogUtility.m16670f("XMPPManager", "[connect] already connecting...");
            return false;
        }
        try {
            boolean zBooleanValue = ((Boolean) this.f12496e.submit(new C2850b(PriorityThreadPoolExecutor.Priority.NOW)).get(10L, TimeUnit.SECONDS)).booleanValue();
            ULogUtility.m16670f("XMPPManager", "[connect] end, result: " + zBooleanValue);
            return zBooleanValue;
        } catch (Throwable th) {
            try {
                ULogUtility.m16670f("XMPPManager", "[connect] error, " + th.getMessage());
                return false;
            } finally {
                this.f12507p.set(false);
            }
        }
    }

    /* renamed from: S0 */
    public final void m14221S0(String str) {
        Iterator<InterfaceC2869t> it = this.f12508q.iterator();
        while (it.hasNext()) {
            it.next().mo9838a(str);
        }
    }

    /* renamed from: T, reason: merged with bridge method [inline-methods] */
    public final void m14166L0(C2904l c2904l, boolean z8, boolean z9) {
        C2898i c2898i = (C2898i) c2904l.m14426l("encrypted", "U");
        if (c2898i == null) {
            return;
        }
        Group groupM14182f0 = m14182f0(c2904l);
        if (groupM14182f0 == null) {
            ULogUtility.m16684t("CLSM", "Decrypt msg w/o group for GID: " + c2904l.m14418h());
            return;
        }
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(c2904l.m14446v());
        if (messageObjM15179r == null || messageObjM15179r.m14778p() == MessageObj.MessageType.ENCRYPTED_MSG || messageObjM15179r.m14778p() == MessageObj.MessageType.NewVersion) {
            String strM14302d = C2889b.m14298h().m14302d(groupM14182f0.f13727n, c2898i.m14374f(), c2904l.m14386A().getTime());
            if (C5170o0.m20169d(strM14302d)) {
                ULogUtility.m16684t("CLSM", "Cannot decrypt msg: " + c2904l.m14446v());
                synchronized (m14184g0()) {
                    if (!m14263q0(c2904l, z8, c2904l.m14399N())) {
                        m14170P(c2904l);
                        m14228W0(c2904l, c2904l.m14438r(), z9);
                    }
                }
                return;
            }
            ULogUtility.m16670f("CLSM", c2904l.m14446v() + ": " + strM14302d);
            String strM14452z = c2904l.m14452z();
            if (C5170o0.m20169d(strM14452z)) {
                return;
            }
            int iIndexOf = strM14452z.indexOf("<encrypted");
            int iLastIndexOf = strM14452z.lastIndexOf("</encrypted>");
            if (iIndexOf < 0 || iIndexOf > iLastIndexOf) {
                ULogUtility.m16676l("CLSM", "Unexpected XML, <encrypted> not found");
                return;
            }
            HandleType handleTypeM14438r = c2904l.m14438r();
            List<AbstractC5594b> listM14187k = m14187k(strM14452z.replace(strM14452z.substring(iIndexOf, iLastIndexOf + 12), strM14302d), handleTypeM14438r);
            if (listM14187k.isEmpty()) {
                return;
            }
            if (listM14187k.size() != 1) {
                ULogUtility.m16684t("CLSM", "Unexpected msg count decrypted: " + listM14187k.size());
            }
            C2904l c2904l2 = new C2904l((Message) listM14187k.get(0), handleTypeM14438r);
            synchronized (m14184g0()) {
                if (!m14263q0(c2904l2, z8, c2904l2.m14399N())) {
                    m14170P(c2904l2);
                    m14228W0(c2904l2, handleTypeM14438r, z9);
                }
            }
        }
    }

    /* renamed from: U */
    public Future<Void> m14223U() {
        m14214N();
        return this.f12496e.submit(new C2865p(PriorityThreadPoolExecutor.Priority.NOW));
    }

    /* renamed from: U0 */
    public final void m14224U0(boolean z8) {
        this.f12504m.m24540f(new C2866q(z8));
    }

    /* renamed from: V */
    public void m14225V() throws ExecutionException, InterruptedException, TimeoutException {
        try {
            m14223U().get(1L, TimeUnit.SECONDS);
        } catch (TimeoutException unused) {
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: V0 */
    public final void m14226V0(C2904l c2904l) {
        this.f12505n.m24540f(new C2863n(c2904l));
    }

    /* renamed from: W */
    public final Pair<Boolean, String> m14227W(final String str, final String str2, boolean z8) {
        StrictMode.noteSlowCall("doAuthenticate2XMPPServer");
        synchronized (f12482K) {
            if (this.f12503l != null && !z8) {
                m14199v1("doAuthenticate2XMPPServer exit (Connection exist)");
                return new Pair<>(Boolean.TRUE, "Has connection");
            }
            f12484s = str;
            f12485t = str2;
            C2874y c2874yM14253l0 = m14253l0();
            if (c2874yM14253l0 == null) {
                m14199v1("doAuthenticate2XMPPServer exit (Server Info is null)");
                return new Pair<>(Boolean.FALSE, "Server Info is null");
            }
            String str3 = c2874yM14253l0.f12562a;
            int i9 = c2874yM14253l0.f12563b;
            String str4 = c2874yM14253l0.f12564c;
            boolean z9 = c2874yM14253l0.f12565d;
            String strM15642G = FriendsClient.m15642G("chat", "xmpp.domain");
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(":");
            sb.append(i9);
            sb.append(" (");
            sb.append(str4);
            sb.append(")");
            sb.append(z9 ? "+ TLS" : "- No TLS");
            final String string = sb.toString();
            m14199v1("XMPP connect " + string);
            if (str3 != null && strM15642G != null) {
                if (!f12490y) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    C6492e.m24835k(10000);
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    m14199v1("Smack version: " + C6492e.m24830f());
                    m14199v1("Smack Initialize Latency = " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " ms");
                    f12490y = true;
                }
                ProxyInfo proxyInfoM22170a = ProxyInfo.m22170a();
                Pair<String, String> pairM16484R0 = CLUtility.m16484R0();
                if (!C5170o0.m20170e((CharSequence) pairM16484R0.first) && !C5170o0.m20170e((CharSequence) pairM16484R0.second)) {
                    proxyInfoM22170a = new ProxyInfo(ProxyInfo.ProxyType.HTTP, (String) pairM16484R0.first, Integer.parseInt((String) pairM16484R0.second), "", "");
                }
                ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(str3, i9, strM15642G, proxyInfoM22170a);
                if (z9) {
                    SSLContext sSLContext = f12489x;
                    if (sSLContext == null) {
                        try {
                            SSLContext sSLContext2 = SSLContext.getInstance("TLS");
                            f12489x = sSLContext2;
                            if (sSLContext2 != null) {
                                sSLContext2.init(null, null, new SecureRandom());
                                f12489x.getClientSessionContext().setSessionTimeout(86400);
                                connectionConfiguration.m21946w(f12489x);
                            }
                        } catch (KeyManagementException | NoSuchAlgorithmException e9) {
                            e9.printStackTrace();
                        }
                    } else {
                        connectionConfiguration.m21946w(sSLContext);
                    }
                    if (str4.equalsIgnoreCase("tls")) {
                        connectionConfiguration.m21922A(ConnectionConfiguration.SecurityMode.legacy);
                    } else {
                        connectionConfiguration.m21922A(ConnectionConfiguration.SecurityMode.enabled);
                    }
                } else {
                    connectionConfiguration.m21922A(ConnectionConfiguration.SecurityMode.disabled);
                }
                connectionConfiguration.m21947x(true);
                connectionConfiguration.m21923B(true);
                connectionConfiguration.m21949z(false);
                C5585d.m22033m(org.apache.qpid.management.common.sasl.Constants.MECH_PLAIN, 0);
                final XMPPTCPConnection xMPPTCPConnection = new XMPPTCPConnection(connectionConfiguration);
                Callable callable = new Callable() { // from class: com.cyberlink.you.chat.u0
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.f12819b.m14164J0(string, str, xMPPTCPConnection, str2);
                    }
                };
                ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
                FutureTask futureTask = new FutureTask(callable);
                executorServiceNewCachedThreadPool.execute(futureTask);
                try {
                    Pair<Boolean, String> pair = (Pair) futureTask.get(15L, TimeUnit.SECONDS);
                    m14199v1("doAuth = " + pair.first + StringUtils.SPACE + ((String) pair.second));
                    return pair;
                } catch (Exception e10) {
                    String string2 = e10.toString();
                    if (e10 instanceof TimeoutException) {
                        f12478G++;
                    }
                    m14199v1("doAuth " + e10.toString());
                    return new Pair<>(Boolean.FALSE, string2);
                }
            }
            m14199v1("doAuthenticate2XMPPServer exit (host or service is null)");
            return new Pair<>(Boolean.FALSE, "host or service is null");
        }
    }

    /* renamed from: W0 */
    public void m14228W0(final C2904l c2904l, final HandleType handleType, final boolean z8) {
        ULogUtility.m16659B(handleType.toString(), "[passToListenerAndNotify] xmppMessage content:" + c2904l.m14420i());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f12505n.m24540f(new C2861l(c2904l, atomicBoolean));
        this.f12494c.execute(new Runnable() { // from class: com.cyberlink.you.chat.x0
            @Override // java.lang.Runnable
            public final void run() {
                this.f12850b.m14169O0(c2904l, atomicBoolean, z8, handleType);
            }
        });
    }

    /* renamed from: X */
    public final Pair<Boolean, String> m14229X(boolean z8) {
        String strM7449L = Globals.m7388i0().m7449L();
        String strM7587o0 = Globals.m7388i0().m7587o0();
        return (strM7449L == null || strM7449L.isEmpty() || strM7587o0 == null || strM7587o0.isEmpty()) ? new Pair<>(Boolean.FALSE, "No JID or Token") : m14227W(strM7587o0, strM7449L, z8);
    }

    /* renamed from: X0 */
    public final void m14230X0() {
        ULogUtility.m16664G("[reconnect] start", "XMPP Connection");
        if (!f12475D) {
            m14199v1("[reconnect] no needed");
            return;
        }
        if (this.f12499h.getAndSet(true)) {
            m14199v1("[reconnect] task is running");
            return;
        }
        try {
            this.f12496e.submit(new C2860k(PriorityThreadPoolExecutor.Priority.NOW));
        } catch (Throwable th) {
            m14199v1("[reconnect] something wrong: " + th.getMessage());
            C5154j.m20076j(th);
            this.f12499h.set(false);
        }
    }

    /* renamed from: Y */
    public final boolean m14231Y(AbstractC5594b abstractC5594b) {
        Message message;
        Date dateM22080F;
        if (this.f12503l == null) {
            return false;
        }
        try {
            if ((abstractC5594b instanceof Message) && (dateM22080F = (message = (Message) abstractC5594b).m22080F()) != null) {
                Date date = new Date(new Date().getTime() + FriendsClient.f13679k);
                Log.v("XMPPManager", "Message Sending Time Diff = " + (date.getTime() - dateM22080F.getTime()) + " ms");
                message.m22095U(date);
            }
            if (abstractC5594b == null || this.f12503l == null) {
                return false;
            }
            if (abstractC5594b instanceof Message) {
                f12473B.put(abstractC5594b.m22161k(), abstractC5594b);
            }
            this.f12503l.m21979P(abstractC5594b);
            if (Globals.m7388i0().m7409C1().booleanValue()) {
                return true;
            }
            ULogUtility.m16664G(abstractC5594b.mo22057u().toString(), "Send");
            return true;
        } catch (SmackException.NotConnectedException e9) {
            ULogUtility.m16664G("Packet not send by exception:\nPacket = " + abstractC5594b.mo22057u().toString() + "\n" + Log.getStackTraceString(e9), "Send");
            m14230X0();
            return false;
        }
    }

    /* renamed from: Y0 */
    public void m14232Y0(InterfaceC2849a0 interfaceC2849a0) {
        this.f12509r.m24541g(interfaceC2849a0);
    }

    /* renamed from: Z0 */
    public void m14233Z0(AbstractC2868s abstractC2868s) {
        this.f12505n.m24541g(abstractC2868s);
    }

    /* renamed from: a0 */
    public final ArrayList<Pair<C2904l, Boolean>> m14234a0() {
        final ArrayList<Pair<C2904l, Boolean>> arrayList = new ArrayList<>();
        C2950b0.m14901C(new Runnable() { // from class: com.cyberlink.you.chat.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f12871b.m14165K0(arrayList);
            }
        });
        return arrayList;
    }

    /* renamed from: a1 */
    public void m14235a1(InterfaceC2851b0 interfaceC2851b0) {
        this.f12504m.m24541g(interfaceC2851b0);
    }

    /* renamed from: b0 */
    public final Date m14236b0() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(FriendsClient.m15646K()));
        int i9 = calendar.get(12);
        int i10 = calendar.get(13);
        int i11 = i9 % 5;
        if ((i11 == 0 && i10 < 15 && i10 >= 0) || (i11 == 4 && i10 > 45 && i10 <= 59)) {
            calendar.set(14, 0);
            calendar.set(13, 0);
            if (i11 == 4) {
                calendar.set(12, i9 + 1);
            }
        }
        return calendar.getTime();
    }

    /* renamed from: c0 */
    public final long m14237c0() {
        if (f12487v != 0) {
            Log.d("XMPPManager", "[getConnectDuration] U_XmppServerOpenConnectionTime (Full) = " + f12487v);
            ULogUtility.m16664G("U_XmppServerOpenConnectionTime(Full) =" + f12487v, "XMPP Connection Duration");
            return f12487v;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - f12486u;
        Log.d("XMPPManager", "[getConnectDuration] U_XmppServerOpenConnectionTime (CLResumed) = " + jCurrentTimeMillis);
        ULogUtility.m16664G("U_XmppServerOpenConnectionTime(CLResumed) = " + jCurrentTimeMillis, "XMPP Connection Duration");
        return jCurrentTimeMillis;
    }

    /* renamed from: c1 */
    public final void m14238c1() {
        if (f12473B.isEmpty()) {
            return;
        }
        m14214N();
        this.f12492a = this.f12496e.submit(new C2858i(PriorityThreadPoolExecutor.Priority.NORMAL));
    }

    /* renamed from: d0 */
    public String m14239d0() {
        return f12474C;
    }

    /* renamed from: e0 */
    public String m14240e0() throws InterruptedException {
        C2874y c2874yM14253l0 = m14253l0();
        if (c2874yM14253l0 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c2874yM14253l0.f12562a);
        sb.append(":");
        sb.append(c2874yM14253l0.f12563b);
        sb.append("(");
        sb.append(c2874yM14253l0.f12564c);
        sb.append(")");
        sb.append(c2874yM14253l0.f12565d ? " TLS Required" : " TLS not Required");
        return sb.toString();
    }

    /* renamed from: e1 */
    public void m14241e1(AbstractC5594b abstractC5594b, InterfaceC2873x interfaceC2873x) {
        XMPPConnection xMPPConnection = this.f12503l;
        if (xMPPConnection == null) {
            ULogUtility.m16664G("Message not send by no xmpp connection!!", "Send");
            String strM7449L = Globals.m7388i0().m7449L();
            String strM7587o0 = Globals.m7388i0().m7587o0();
            if (strM7449L != null && !strM7449L.isEmpty() && strM7587o0 != null && !strM7587o0.isEmpty()) {
                m14212L(strM7587o0, strM7449L, false, new C2852c(abstractC5594b, interfaceC2873x));
                return;
            }
            Log.d("XMPPManager", "does not register.");
            if (interfaceC2873x != null) {
                interfaceC2873x.mo5816a();
                return;
            }
            return;
        }
        if (!xMPPConnection.mo21971H()) {
            new C2853d(abstractC5594b, interfaceC2873x).start();
            return;
        }
        if (m14231Y(abstractC5594b)) {
            Log.d("XMPPManager", "send success.");
            if (interfaceC2873x != null) {
                interfaceC2873x.onSuccess();
                return;
            }
            return;
        }
        Log.d("XMPPManager", "send fail.");
        if (interfaceC2873x != null) {
            interfaceC2873x.mo5816a();
        }
    }

    /* renamed from: f1 */
    public final void m14242f1(String str) {
        f12474C = str;
    }

    /* renamed from: g1 */
    public void m14243g1(int i9) {
        f12481J = i9;
    }

    /* renamed from: h */
    public final boolean m14244h(C2904l c2904l) {
        Iterator<String> itKeys = Globals.m7388i0().m7487T().keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (c2904l.m14446v() != null && c2904l.m14446v().equals(next)) {
                Log.d("XMPPManager", String.format("[CheckMessageIsDeleted] Filter message(%s) which has been deleted", next));
                return true;
            }
        }
        return false;
    }

    /* renamed from: h0 */
    public Executor m14245h0() {
        return this.f12494c;
    }

    /* renamed from: h1 */
    public void m14246h1(InterfaceC2870u interfaceC2870u) {
        this.f12502k = interfaceC2870u;
    }

    /* renamed from: i */
    public final String m14247i() {
        long jM7588o1 = Globals.m7388i0().m7588o1();
        long jM7593p1 = Globals.m7388i0().m7593p1();
        if (jM7593p1 == 0 || jM7593p1 + (jM7588o1 * 1000) < System.currentTimeMillis()) {
            return null;
        }
        String strM7604r1 = Globals.m7388i0().m7604r1();
        if (strM7604r1 == null || strM7604r1.length() != 0) {
            return strM7604r1;
        }
        return null;
    }

    /* renamed from: j */
    public final void m14248j() {
        this.f12505n.m24540f(new C2864o());
    }

    /* renamed from: j0 */
    public List<String> m14249j0() {
        ArrayList arrayList = new ArrayList();
        for (C2874y c2874y : f12477F) {
            StringBuilder sb = new StringBuilder();
            sb.append(c2874y.f12562a);
            sb.append(":");
            sb.append(c2874y.f12563b);
            sb.append("(");
            sb.append(c2874y.f12564c);
            sb.append(")");
            sb.append(c2874y.f12565d ? " TLS Required" : " TLS not Required");
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    /* renamed from: j1 */
    public void m14250j1() {
        f12480I = 0L;
    }

    /* renamed from: k0 */
    public final String m14251k0() {
        Object obj;
        FriendsClient friendsClient = new FriendsClient();
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        Pair<String, String> pairM15731j = friendsClient.m15731j("user", "userStatus", arrayList);
        Log.d("XMPPManager", "[UserStatus] statusCode=" + ((String) pairM15731j.first) + " result=" + ((String) pairM15731j.second));
        Object obj2 = pairM15731j.first;
        if (obj2 == null || !((String) obj2).equals("200") || (obj = pairM15731j.second) == null) {
            return null;
        }
        return C5172p.m20176C(C5172p.m20195q((String) obj));
    }

    /* renamed from: k1 */
    public boolean m14252k1(Presence.Type type) {
        Log.i("XMPPManager", "setPresence: " + type.toString());
        return m14231Y(new Presence(type));
    }

    /* renamed from: l0 */
    public final C2874y m14253l0() throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        m14199v1("getXMPPServer");
        long j9 = f12480I;
        if (j9 == 0 || j9 == -1 || System.currentTimeMillis() - f12480I > DateUtils.MILLIS_PER_DAY) {
            boolean z8 = f12480I == -1;
            f12480I = System.currentTimeMillis();
            m14188l(z8);
            f12478G = 0;
            f12479H = 0;
        }
        List<C2874y> list = f12477F;
        if (list == null || list.isEmpty()) {
            m14199v1("ServerList is empty");
            m14216Q();
            m14199v1("getXMPPServer exit a " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            return null;
        }
        if (f12478G >= f12477F.size() || f12478G >= 10) {
            int i9 = f12479H + 1;
            f12479H = i9;
            if (i9 >= 3) {
                m14199v1("Tried 3 times");
                m14216Q();
                m14199v1("getXMPPServer exit b " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                return null;
            }
            f12478G = 0;
        }
        C2874y c2874y = f12477F.get(f12478G);
        int i10 = f12481J;
        if (i10 >= 0 && i10 < f12477F.size()) {
            c2874y = f12477F.get(f12481J);
        }
        m14199v1("getXMPPServer done " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        return c2874y;
    }

    /* renamed from: l1 */
    public void m14254l1() {
        new C2854e().start();
    }

    /* renamed from: m0 */
    public void m14255m0() {
        ArrayList<Pair<C2904l, Boolean>> arrayListM14234a0;
        ULogUtility.m16680p("XMPPManager", "XMPPManager:handleArchivePacket() IN");
        if (f12472A) {
            synchronized (C2950b0.m14914m().m14724u()) {
                arrayListM14234a0 = m14234a0();
            }
        } else {
            arrayListM14234a0 = m14234a0();
        }
        ULogUtility.m16680p("XMPPManager", "XMPPManager:handleArchivePacket() passToListener start");
        Iterator<Pair<C2904l, Boolean>> it = arrayListM14234a0.iterator();
        while (it.hasNext()) {
            Pair<C2904l, Boolean> next = it.next();
            C2904l c2904l = (C2904l) next.first;
            boolean zBooleanValue = ((Boolean) next.second).booleanValue();
            if (c2904l.m14394I()) {
                if (!zBooleanValue) {
                    m14170P((C2904l) next.first);
                    m14228W0(c2904l, HandleType.XMPP, true);
                }
            } else if (c2904l.m14389D() == MessageObj.MessageType.DeliveryReceipt) {
                m14228W0(c2904l, HandleType.XMPP, false);
            } else {
                Map<String, String> mapM14373e = ((C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event")).m14373e();
                if (zBooleanValue && m14156C0(mapM14373e.get("eventType"))) {
                    C5321e.m20824o().m20896x0(c2904l, mapM14373e);
                }
            }
        }
        f12491z.clear();
        f12472A = false;
        ULogUtility.m16680p("XMPPManager", "XMPPManager:handleArchivePacket() OUT");
    }

    /* renamed from: m1 */
    public final void m14256m1(Presence.Type type) {
        f12488w = type;
    }

    /* renamed from: n0 */
    public final void m14257n0(AbstractC5586IQ abstractC5586IQ) {
        if (abstractC5586IQ.m22160j() == null) {
            if (abstractC5586IQ.m22066B() == null || !abstractC5586IQ.m22066B().equals(AbstractC5586IQ.a.f19233d)) {
                return;
            }
            Log.d("XMPPManager", "receive session result");
            f12487v = System.currentTimeMillis() - f12486u;
            return;
        }
        Log.d("XMPPManager", "ResultIQ: " + abstractC5586IQ.toString());
        if (abstractC5586IQ.m22156e() != null && abstractC5586IQ.m22156e().m22141b() != null) {
            Log.e("XMPPManager", "handleIQ: " + abstractC5586IQ.m22156e().m22141b());
        }
        C2889b.m14298h().m14309m(abstractC5586IQ.m22161k(), 0L, null, null);
        if (XMPPArchiveHelper.m14136m(abstractC5586IQ)) {
            m14248j();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Response from ");
        sb.append(FriendsClient.m15642G("chat", "xmpp.server"));
        sb.append("\n");
        sb.append("messageId: ");
        sb.append(abstractC5586IQ.m22161k());
        if (abstractC5586IQ.m22156e() != null) {
            sb.append("\n");
            sb.append(abstractC5586IQ.m22156e());
        }
        if (abstractC5586IQ.m22071x() != null && abstractC5586IQ.m22072z() != null) {
            sb.append("\n");
            sb.append("cv=");
            sb.append(abstractC5586IQ.m22071x());
            sb.append(" empty=");
            sb.append(abstractC5586IQ.m22072z());
        }
        ULogUtility.m16660C(sb.toString(), "Receive", ULogUtility.PingType.XmppServer);
        InterfaceC2870u interfaceC2870u = this.f12502k;
        if (interfaceC2870u != null) {
            interfaceC2870u.mo9677a(sb.toString());
        }
    }

    /* renamed from: n1 */
    public final void m14258n1(XMPPConnection xMPPConnection) {
        StringBuilder sb = new StringBuilder();
        sb.append("setXMPPConnection = ");
        sb.append(xMPPConnection == null ? "null" : Integer.toHexString(xMPPConnection.hashCode()));
        ULogUtility.m16670f("XMPPManager", sb.toString());
        XMPPConnection xMPPConnection2 = this.f12503l;
        if (xMPPConnection2 != null && xMPPConnection == null) {
            xMPPConnection2.m21975L(this.f12498g);
            this.f12503l.m21977N(this.f12500i);
        }
        this.f12503l = xMPPConnection;
        if (xMPPConnection != null) {
            xMPPConnection.m21992c(this.f12498g);
            PingManager pingManagerM22638l = PingManager.m22638l(xMPPConnection);
            if (pingManagerM22638l != null) {
                pingManagerM22638l.m22647u(30);
                pingManagerM22638l.m22646t(this.f12501j);
            }
            ULogUtility.m16670f("XMPPManager", "[setXMPPConnection] start to resend message");
            m14238c1();
        }
        m14224U0(m14204A0());
    }

    /* renamed from: o0 */
    public final void m14259o0(C2904l c2904l, HandleType handleType, boolean z8) {
        if (!m14263q0(c2904l, false, false)) {
            m14170P(c2904l);
            m14228W0(c2904l, handleType, z8);
            return;
        }
        ULogUtility.m16664G("Message exist. message id=" + c2904l.m14446v(), handleType + "Receive");
    }

    /* renamed from: o1 */
    public final void m14260o1(Group group, C2904l c2904l, boolean z8, boolean z9) throws JSONException {
        String str;
        if (C2925v.m14592e0(c2904l.m14389D())) {
            if (c2904l.m14397L() || c2904l.m14389D().equals(MessageObj.MessageType.Audio) || c2904l.m14389D().equals(MessageObj.MessageType.Video)) {
                str = c2904l.m14389D().equals(MessageObj.MessageType.Video) ? group.f13718e : group.f13720g;
            } else if (group.f13738y.equals("Official") || group.f13738y.equals("Corporate")) {
                FriendsClient friendsClient = new FriendsClient();
                GroupAlbumObj groupAlbumObjM15708O = friendsClient.m15708O(group.f13718e, String.valueOf(group.f13727n));
                String str2 = (groupAlbumObjM15708O == null || !groupAlbumObjM15708O.m14675b().equals(c2904l.m14436q().f12742c)) ? c2904l.m14436q().f12742c : group.f13718e;
                friendsClient.m15717U0();
                str = str2;
            } else {
                str = group.f13718e;
            }
            C2973l0 c2973l0M14416g = c2904l.m14416g(str);
            c2973l0M14416g.m15118E(c2904l.m14430n());
            C2950b0.m14914m().m14702L(c2973l0M14416g, true);
        }
        MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(group.f13727n));
        if (messageObjM14449w0 == null) {
            Log.e("XMPPManager", "messageObj is null");
            return;
        }
        m14179d1(group, messageObjM14449w0);
        if (C2907m0.m14454I().m14483G(group.f13723j) == 0 && C6818b.m25402m(messageObjM14449w0) && group.m15748d() < messageObjM14449w0.m14788z().getTime()) {
            group.m15753k(messageObjM14449w0.m14788z().getTime());
            C2950b0.m14912k().m15062A(String.valueOf(group.f13727n), group, "LastRead");
        }
        if (z8) {
            m14264q1(messageObjM14449w0, z9);
        }
    }

    /* renamed from: p0 */
    public final void m14261p0(C2904l c2904l, HandleType handleType) {
        m14273v0(c2904l);
        m14228W0(c2904l, handleType, false);
    }

    /* renamed from: p1 */
    public final String m14262p1(String str, MessageObj.MessageType messageType) {
        if (C5170o0.m20170e(str)) {
            return "";
        }
        if (messageType != MessageObj.MessageType.Text) {
            return str;
        }
        int iIndexOf = str.indexOf("<body>") + 5;
        int iLastIndexOf = str.lastIndexOf("</body>");
        if (iLastIndexOf - iIndexOf <= 5) {
            return str;
        }
        return str.substring(0, iIndexOf + 6) + str.substring(iLastIndexOf);
    }

    /* renamed from: q0 */
    public final boolean m14263q0(C2904l c2904l, boolean z8, boolean z9) throws JSONException {
        String strM14446v = c2904l.m14446v();
        if (C2950b0.m14904c().m14871f(strM14446v)) {
            ULogUtility.m16684t("XMPPManager", "Deleted: " + strM14446v);
            return true;
        }
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(strM14446v);
        if (messageObjM15179r == null || messageObjM15179r.m14751M() || m14159E0(messageObjM15179r, c2904l)) {
            Group groupM14182f0 = m14182f0(c2904l);
            if (groupM14182f0 == null) {
                return false;
            }
            if (messageObjM15179r != null) {
                m14272u1(groupM14182f0, c2904l, messageObjM15179r);
                return false;
            }
            c2904l.m14443t0(groupM14182f0);
            m14260o1(groupM14182f0, c2904l, z8, z9);
            if (c2904l.m14389D() == MessageObj.MessageType.DeleteMedia) {
                return false;
            }
            m14266r1(groupM14182f0, c2904l);
            return false;
        }
        if (z8) {
            if ((messageObjM15179r.m14740B().equals("2") || messageObjM15179r.m14740B().equals("3")) && ((messageObjM15179r.m14778p().equals(MessageObj.MessageType.Text) || messageObjM15179r.m14778p().equals(MessageObj.MessageType.Sticker) || messageObjM15179r.m14778p().equals(MessageObj.MessageType.AnimSticker) || messageObjM15179r.m14778p().equals(MessageObj.MessageType.AnimPngSticker) || messageObjM15179r.m14778p().equals(MessageObj.MessageType.StickerTypeUnknown) || messageObjM15179r.m14778p().equals(MessageObj.MessageType.ShareLocation)) && messageObjM15179r.m14745G().equals(String.valueOf(Globals.m7388i0().m7568k1())))) {
                ArrayList arrayList = new ArrayList();
                messageObjM15179r.m14762X("0");
                arrayList.add("Status");
                if (!messageObjM15179r.m14788z().equals(c2904l.m14422j())) {
                    ULogUtility.m16664G("Correct local msg ts since got archive, id:" + strM14446v + ", ts:" + messageObjM15179r.m14788z().getTime() + " > " + c2904l.m14422j().getTime(), "Receive");
                    messageObjM15179r.m14761W(c2904l.m14422j());
                    arrayList.add("SendTime");
                }
                C2950b0.m14916o().m15160E(strM14446v, messageObjM15179r, arrayList);
                m14226V0(c2904l);
                f12473B.remove(messageObjM15179r.m14777o());
            }
            m14264q1(messageObjM15179r, z9);
        }
        if (messageObjM15179r.m14778p().equals(MessageObj.MessageType.Video) && c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("Status");
            messageObjM15179r.m14762X("0");
            if (c2904l.m14386A() != null) {
                messageObjM15179r.m14761W(c2904l.m14386A());
                arrayList2.add("SendTime");
            }
            if (c2904l.m14446v().endsWith("-video")) {
                try {
                    JSONObject jSONObject = new JSONObject(messageObjM15179r.m14779q());
                    jSONObject.put("width", String.valueOf(c2904l.m14436q().f12745f));
                    jSONObject.put("height", String.valueOf(c2904l.m14436q().f12746g));
                    messageObjM15179r.m14757S(jSONObject.toString());
                    arrayList2.add("MessageContent");
                } catch (JSONException e9) {
                    C5154j.m20076j(e9);
                }
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(c2904l.m14436q().f12740a);
                c2973l0M14725v.m15124K(c2904l.m14436q().f12745f);
                c2973l0M14725v.m15121H(c2904l.m14436q().f12746g);
                ArrayList arrayList3 = new ArrayList(2);
                arrayList3.add("Width");
                arrayList3.add("Height");
                C2950b0.m14914m().m14699I(c2973l0M14725v.m15144p(), c2973l0M14725v, arrayList3);
            }
            C2950b0.m14916o().m15160E(strM14446v, messageObjM15179r, arrayList2);
            m14226V0(c2904l);
        }
        return true;
    }

    /* renamed from: q1 */
    public final void m14264q1(MessageObj messageObj, boolean z8) throws JSONException {
        if (z8 && new Date(Globals.m7388i0().m7515Z()).before(messageObj.m14788z())) {
            Globals.m7388i0().m7461N2(messageObj.m14788z().getTime(), false);
        }
    }

    /* renamed from: r0 */
    public final boolean m14265r0(String str) throws JSONException {
        if (str == null) {
            return false;
        }
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
        ULogUtility.m16664G("isDeleteMessage(" + str + ") Success : " + C2950b0.m14916o().m15169h(str), "XMPPManager");
        if (messageObjM15179r == null) {
            return false;
        }
        JSONObject jSONObjectM7487T = Globals.m7388i0().m7487T();
        try {
            jSONObjectM7487T.put(str, messageObjM15179r.m14788z().getTime());
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        Globals.m7388i0().m7440I2(jSONObjectM7487T);
        C5180s.m20255h(messageObjM15179r.m14772j());
        Group groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j());
        if (groupM15077n == null || messageObjM15179r.m14788z().getTime() <= groupM15077n.m15748d()) {
            return true;
        }
        ULogUtility.m16662E("updateGroup:" + groupM15077n.f13727n + " Unread count", "XMPPManager");
        C2907m0.m14454I().m14503e0(groupM15077n.f13723j, false, messageObjM15179r.m14788z().getTime());
        return true;
    }

    /* renamed from: r1 */
    public final void m14266r1(Group group, C2904l c2904l) throws JSONException {
        MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(group.f13727n));
        if (messageObjM14449w0 == null) {
            return;
        }
        m14268s1(group, messageObjM14449w0);
    }

    /* renamed from: s0 */
    public final void m14267s0(C2904l c2904l, HandleType handleType) {
        Group groupM15081r = C2950b0.m14912k().m15081r(c2904l.m14418h());
        if (groupM15081r != null) {
            NotificationHelper.m14068L(groupM15081r, c2904l, handleType);
        } else {
            ULogUtility.m16659B(handleType.toString(), "Notify fail. group is null.");
        }
    }

    /* renamed from: s1 */
    public void m14268s1(Group group, MessageObj messageObj) throws JSONException {
        if (group == null) {
            return;
        }
        String str = group.f13739z;
        if (str != null && !str.isEmpty()) {
            try {
                long j9 = new JSONObject(group.f13739z).getLong("time");
                if (j9 != 0) {
                    if (messageObj.m14788z().before(new Date(j9))) {
                        return;
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }
        group.f13739z = C5180s.m20250c(Globals.m7388i0(), messageObj);
        group.f13706E = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add("LastMsg");
        arrayList.add("isArchive");
        C2950b0.m14912k().m15063B(String.valueOf(group.f13727n), group, arrayList);
    }

    /* renamed from: t0 */
    public final void m14269t0(AbstractC5594b abstractC5594b, HandleType handleType, final boolean z8) {
        StrictMode.noteSlowCall("handlePacket");
        synchronized (m14184g0()) {
            final boolean z9 = handleType == HandleType.XMPP;
            if (!(abstractC5594b instanceof Message)) {
                ULogUtility.m16680p("XMPPManager", "[" + handleType + "] handlePacket = " + abstractC5594b.toString());
            }
            if (abstractC5594b instanceof AbstractC5586IQ) {
                m14257n0((AbstractC5586IQ) abstractC5594b);
            } else if (abstractC5594b instanceof Message) {
                final C2904l c2904l = new C2904l((Message) abstractC5594b, handleType);
                if (!Globals.m7388i0().m7409C1().booleanValue()) {
                    ULogUtility.m16680p("XMPPManager", "[" + handleType + "] handlePacket = " + m14262p1(abstractC5594b.toString(), c2904l.m14389D()));
                }
                m14176b1(c2904l);
                if (!m14209I0((Message) abstractC5594b, handleType)) {
                    return;
                }
                if (c2904l.m14424k() != null) {
                    Log.d("XMPPManager", "Got Error Message");
                    return;
                }
                if ((c2904l.m14399N() || !z9) && m14244h(c2904l)) {
                    return;
                }
                if (m14173Z(c2904l)) {
                    return;
                }
                if (c2904l.m14399N()) {
                    XMPPArchiveHelper.m14137n(c2904l);
                    m14219R0(c2904l);
                }
                if (c2904l.m14389D() == MessageObj.MessageType.Event) {
                    C2898i c2898i = (C2898i) c2904l.m14426l("event", "urn:xmpp:custom:event");
                    if (c2898i == null) {
                        return;
                    }
                    String str = c2898i.m14373e().get("eventType");
                    if (c2904l.m14399N() && m14156C0(str)) {
                        f12491z.add(c2904l);
                    } else {
                        boolean zM14138o = XMPPArchiveHelper.m14138o();
                        if (!zM14138o) {
                            zM14138o = !C2876a.m14292a(str, c2904l);
                        }
                        if (zM14138o) {
                            this.f12495d.submit(new RunnableC2871v(c2904l, c2898i, handleType));
                        }
                    }
                } else if (c2904l.m14389D() == MessageObj.MessageType.ENCRYPTED_SESSION) {
                    m14172T0(c2904l);
                    return;
                } else if (c2904l.m14389D() == MessageObj.MessageType.ENCRYPTED_MSG) {
                    this.f12497f.submit(new Runnable() { // from class: com.cyberlink.you.chat.v0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f12843b.m14166L0(c2904l, z9, z8);
                        }
                    });
                    return;
                }
                if (c2904l.m14394I()) {
                    if (z9 || c2904l.m14422j().after(Globals.m7388i0().m7502W0())) {
                        if (!XMPPArchiveHelper.m14138o() && m14205D0(c2904l)) {
                            Log.d("XMPPManager", "[handlePacket] This is last message for chat, don't handle it");
                            return;
                        }
                        if (c2904l.m14399N()) {
                            f12491z.add(c2904l);
                            if (!f12472A) {
                                f12472A = C2925v.m14592e0(c2904l.m14389D());
                            }
                        } else {
                            if (z9) {
                                if (c2904l.m14386A() != null) {
                                    c2904l.m14441s0(c2904l.m14386A());
                                } else {
                                    c2904l.m14441s0(m14236b0());
                                }
                            }
                            m14259o0(c2904l, handleType, z8);
                        }
                    }
                } else if (c2904l.m14389D() != MessageObj.MessageType.DeliveryReceipt) {
                    Log.d("XMPPManager", "Receive packet is\n" + c2904l.toString());
                } else if (c2904l.m14399N()) {
                    f12491z.add(c2904l);
                } else {
                    m14261p0(c2904l, handleType);
                }
            } else if (abstractC5594b instanceof Presence) {
                Presence presence = (Presence) abstractC5594b;
                Log.d("XMPPManager", "Presence packet: " + presence.toString());
                if (abstractC5594b.m22160j().equals(Globals.m7388i0().m7511Y0())) {
                    m14256m1(presence.m22122y());
                }
                Presence.Type type = Presence.Type.available;
            } else if (abstractC5594b instanceof StreamMgmt) {
                m14274w0((StreamMgmt) abstractC5594b);
                m14217Q0((StreamMgmt) abstractC5594b);
            } else if (abstractC5594b instanceof CLResumed) {
                CLResumed cLResumed = (CLResumed) abstractC5594b;
                Log.i("Auth", "CLResumed: " + cLResumed.m22061y() + " expiration=" + cLResumed.m22058v() + " time=" + (System.currentTimeMillis() - f12486u));
                Log.i("Auth", "-------------------------------------------------------------------");
                m14199v1("CLResumed: " + cLResumed.m22061y() + " expiration = " + cLResumed.m22058v() + " session id : " + cLResumed.m22060x());
                if (cLResumed.m22061y().equals(FirebaseAnalytics.Param.SUCCESS)) {
                    C6566a.m25164w(C5176q0.m20222a(m14237c0()), C5176q0.m20223b(m14237c0()));
                    m14221S0("Connected to server, it costs " + (System.currentTimeMillis() - f12486u) + " milliseconds");
                    Globals.m7388i0().m7590o4(cLResumed.m22060x());
                    XMPPConnection xMPPConnection = this.f12503l;
                    if (xMPPConnection != null && (xMPPConnection instanceof XMPPTCPConnection)) {
                        ((XMPPTCPConnection) xMPPConnection).m22218w0(cLResumed.m22060x());
                    }
                    Globals.m7388i0().m7581m4(cLResumed.m22058v());
                    Globals.m7388i0().m7585n4(System.currentTimeMillis());
                } else {
                    Globals.m7388i0().m7590o4(null);
                    Globals.m7388i0().m7581m4(0L);
                    Globals.m7388i0().m7585n4(0L);
                    try {
                        ((XMPPTCPConnection) this.f12503l).m22216u0(false);
                        m14199v1("[CLResume fail] start to Login.");
                        this.f12503l.mo21972I(f12484s, f12485t, Globals.m7388i0().m7598q1());
                        m14199v1("[CLResume fail] Login success.");
                        this.f12496e.submit(new RunnableC2928w0());
                    } catch (Exception e9) {
                        String str2 = e9.toString() + ". Failed to connect or login as " + f12484s;
                        if (e9 instanceof SmackException.ConnectionException) {
                            SmackException.ConnectionException connectionException = (SmackException.ConnectionException) e9;
                            str2 = str2 + StringUtils.SPACE + connectionException.m21960a();
                            for (int i9 = 0; i9 < connectionException.m21960a().size(); i9++) {
                                Log.v("Auth", connectionException.m21960a().get(i9).toString());
                            }
                        }
                        Log.e("Auth", str2);
                        m14199v1("[CLResume fail] " + str2);
                        m14221S0(str2);
                        String message = e9.getMessage();
                        if (message != null && message.contains("not-authorized")) {
                            f12483L = false;
                            m14199v1("[CLResume fail] " + message);
                        }
                        m14258n1(null);
                        m14242f1("");
                        m14199v1("[CLResume fail] in background : " + Globals.m7396z1());
                        if (!Globals.m7396z1()) {
                            m14199v1("[CLResume fail] Start to reconnect XMPP");
                            C6456d.m24714D().m24755P();
                        }
                    }
                }
            } else if (abstractC5594b instanceof SASLMechanism.Success) {
                Log.d("Auth", "Auth Success. " + abstractC5594b.toString());
            } else {
                Log.d("XMPPManager", "Unknown packet " + abstractC5594b.toString());
            }
        }
    }

    /* renamed from: t1 */
    public final void m14270t1(StreamMgmt streamMgmt, final MessageObj messageObj) {
        ArrayList arrayList = new ArrayList();
        boolean zEquals = "nack".equals(streamMgmt.m22136w());
        if (zEquals) {
            messageObj.m14762X("3");
            C6566a.m25152k(true, messageObj.m14778p());
        } else {
            messageObj.m14762X("0");
            CLUtility.m16566n2(messageObj);
        }
        arrayList.add("Status");
        if (streamMgmt.m22137x() != null) {
            messageObj.m14761W(streamMgmt.m22137x());
            arrayList.add("SendTime");
            Group groupM15077n = C2950b0.m14912k().m15077n(messageObj.m14772j());
            if (groupM15077n != null && C2907m0.m14454I().m14483G(groupM15077n.f13723j) == 0 && C6818b.m25402m(messageObj) && groupM15077n.m15748d() < messageObj.m14788z().getTime()) {
                groupM15077n.m15753k(messageObj.m14788z().getTime());
                C2950b0.m14912k().m15062A(String.valueOf(groupM15077n.f13727n), groupM15077n, "LastRead");
            }
        }
        C2950b0.m14916o().m15160E(messageObj.m14777o(), messageObj, arrayList);
        C5180s.m20255h(String.valueOf(messageObj.m14772j()));
        if (zEquals) {
            C6385v.m24526d(new Runnable() { // from class: com.cyberlink.you.chat.z0
                @Override // java.lang.Runnable
                public final void run() {
                    XMPPManager.m14171P0(messageObj);
                }
            });
        }
    }

    /* renamed from: u0 */
    public void m14271u0(List<AbstractC5594b> list, HandleType handleType, boolean z8) {
        for (int i9 = 0; i9 < list.size(); i9++) {
            ULogUtility.m16670f("XMPPManager", "handlePackets " + i9 + " of " + list.size());
            m14269t0(list.get(i9), handleType, z8);
        }
    }

    /* renamed from: u1 */
    public final void m14272u1(Group group, C2904l c2904l, MessageObj messageObj) throws JSONException {
        Log.d("Translate new message", "start update message id=" + messageObj.m14777o());
        boolean zM14159E0 = m14159E0(messageObj, c2904l);
        if ((messageObj.m14751M() && !c2904l.m14395J()) || zM14159E0) {
            Log.d("Translate new message", "start store mediaObj");
            if (C2925v.m14592e0(c2904l.m14389D())) {
                C2973l0 c2973l0M14416g = c2904l.m14416g((c2904l.m14397L() || c2904l.m14389D().equals(MessageObj.MessageType.Audio) || c2904l.m14389D().equals(MessageObj.MessageType.Video)) ? group.f13720g : group.f13718e);
                C2950b0.m14914m().m14712i(c2973l0M14416g);
                Log.d("Translate new message", "store mediaObj id=" + c2973l0M14416g.m15144p());
                Log.d("XMPPManager", "receive new media, call updateDB");
                C2909n0.m14531b().m14535e(c2973l0M14416g);
            }
            Log.d("Translate new message", "end store mediaObj");
            Log.d("Translate new message", "update message id=" + messageObj.m14777o());
            MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(group.f13727n));
            C2950b0.m14916o().m15158C(messageObjM14449w0.m14777o(), messageObjM14449w0);
            if (zM14159E0) {
                m14268s1(group, messageObjM14449w0);
            }
        }
        Log.d("Translate new message", "end update message id=" + messageObj.m14777o());
    }

    /* renamed from: v0 */
    public final void m14273v0(C2904l c2904l) {
        DeliveryReceipt deliveryReceipt = (DeliveryReceipt) c2904l.m14426l("received", "urn:xmpp:receipts");
        if (deliveryReceipt == null) {
            return;
        }
        String strM22697d = deliveryReceipt.m22697d();
        String strM14430n = c2904l.m14430n();
        if (strM14430n == null) {
            strM14430n = c2904l.m14428m();
        }
        if (strM14430n == null) {
            return;
        }
        Date dateM14386A = c2904l.m14386A();
        long time = dateM14386A == null ? 0L : dateM14386A.getTime();
        if (String.valueOf(Globals.m7388i0().m7568k1()).equals(strM14430n)) {
            m14276y0(strM22697d, strM14430n, time);
        } else {
            m14275x0(strM22697d, strM14430n, time);
        }
    }

    /* renamed from: w0 */
    public final void m14274w0(StreamMgmt streamMgmt) {
        String strM15269a;
        MessageObj messageObjM15179r;
        String strM22161k = streamMgmt.m22161k();
        if (strM22161k == null) {
            return;
        }
        MessageObj messageObjM15179r2 = C2950b0.m14916o().m15179r(strM22161k);
        if (messageObjM15179r2 != null) {
            m14270t1(streamMgmt, messageObjM15179r2);
            return;
        }
        C2997x0 c2997x0M15266g = C2950b0.m14923v().m15266g(strM22161k);
        if (c2997x0M15266g != null && (messageObjM15179r = C2950b0.m14916o().m15179r((strM15269a = c2997x0M15266g.m15269a()))) != null) {
            messageObjM15179r.m14762X("5");
            C2950b0.m14916o().m15159D(strM15269a, messageObjM15179r, "Status");
            C2950b0.m14923v().m15264e(strM15269a);
        } else {
            Log.d("XMPPManager", "unknown StreamMgmt packet " + strM22161k);
        }
    }

    /* renamed from: x0 */
    public final void m14275x0(String str, String str2, long j9) {
        C2950b0.m14904c().m14870e(str, Long.parseLong(str2), ArchiveMessageObj$Type.RECEIVED_RECEIPT, j9);
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
        if (messageObjM15179r == null) {
            return;
        }
        if (MessageObj.TTLStatus.NO_TTL != messageObjM15179r.m14741C()) {
            if (MessageObj.TTLStatus.NOT_START == messageObjM15179r.m14741C()) {
                ArrayList arrayList = new ArrayList();
                messageObjM15179r.m14763Y(MessageObj.TTLStatus.START);
                arrayList.add("TTLStatus");
                messageObjM15179r.m14760V(FriendsClient.m15646K());
                arrayList.add("SDStarttime");
                C2950b0.m14916o().m15160E(str, messageObjM15179r, arrayList);
                return;
            }
            return;
        }
        Group groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j());
        if (groupM15077n == null) {
            return;
        }
        int iM14873h = "Dual".equals(groupM15077n.f13716c) ? 1 : C2950b0.m14904c().m14873h(str);
        ArrayList arrayList2 = new ArrayList();
        messageObjM15179r.m14759U(iM14873h);
        arrayList2.add("ReadCount");
        if ("2".equals(messageObjM15179r.m14740B()) || "3".equals(messageObjM15179r.m14740B())) {
            ULogUtility.m16664G("Correct local msg status since got receipts, id:" + str, "Receive");
            messageObjM15179r.m14762X("0");
            arrayList2.add("Status");
        }
        C2950b0.m14916o().m15160E(str, messageObjM15179r, arrayList2);
    }

    /* renamed from: y0 */
    public final void m14276y0(String str, String str2, long j9) {
        C2950b0.m14904c().m14870e(str, Long.parseLong(str2), ArchiveMessageObj$Type.SENT_RECEIPT, j9);
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
        if (messageObjM15179r == null) {
            return;
        }
        if (!"5".equals(messageObjM15179r.m14740B())) {
            messageObjM15179r.m14762X("5");
            C2950b0.m14916o().m15159D(str, messageObjM15179r, "Status");
        }
        Group groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j());
        if (groupM15077n != null && groupM15077n.m15748d() < messageObjM15179r.m14788z().getTime()) {
            groupM15077n.m15753k(messageObjM15179r.m14788z().getTime());
            C2950b0.m14912k().m15062A(String.valueOf(groupM15077n.f13727n), groupM15077n, "LastRead");
        }
    }

    /* renamed from: z0 */
    public boolean m14277z0() {
        return f12483L;
    }

    public XMPPManager() {
        this.f12493b = new Random();
        this.f12494c = Executors.newSingleThreadExecutor(new ThreadFactoryC6373j("Xmpp.Listener"));
        this.f12495d = Executors.newSingleThreadExecutor(new ThreadFactoryC6373j("Xmpp.Event"));
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.f12496e = new PriorityThreadPoolExecutor(1, 1, 0L, timeUnit, new ThreadFactoryC6373j("Xmpp.Manager"));
        this.f12497f = new PriorityThreadPoolExecutor(1, 4, 0L, timeUnit, new ThreadFactoryC6373j("Xmpp.CLSM"));
        this.f12498g = new C2859j();
        this.f12499h = new AtomicBoolean(false);
        this.f12500i = new InterfaceC5583c() { // from class: com.cyberlink.you.chat.s0
            @Override // org.jivesoftware.smack.InterfaceC5583c
            public final void processPacket(AbstractC5594b abstractC5594b) throws JSONException {
                this.f12809a.m14167M0(abstractC5594b);
            }
        };
        this.f12501j = new InterfaceC6426a() { // from class: com.cyberlink.you.chat.t0
            @Override // p214u7.InterfaceC6426a
            /* renamed from: a */
            public final void mo14541a() {
                this.f12815a.m14168N0();
            }
        };
        this.f12502k = null;
        this.f12503l = null;
        this.f12504m = new C6389z<>("XmppConnection");
        this.f12505n = new C6389z<>("XmppMsg");
        this.f12506o = new C6389z<>("XmppArchive");
        this.f12507p = new AtomicBoolean(false);
        this.f12508q = new ArrayList();
        this.f12509r = new C6389z<>("XmppAck");
        C2913a.m14540a();
    }

    /* renamed from: com.cyberlink.you.chat.XMPPManager$y */
    public static class C2874y {

        /* renamed from: a */
        public String f12562a;

        /* renamed from: b */
        public int f12563b;

        /* renamed from: c */
        public String f12564c;

        /* renamed from: d */
        public boolean f12565d;

        public C2874y(String str, int i9, String str2, boolean z8) {
            this.f12562a = str;
            this.f12563b = i9;
            this.f12564c = str2;
            this.f12565d = z8;
        }

        public String toString() {
            return this.f12562a + ":" + this.f12563b + StringUtils.SPACE + this.f12564c;
        }

        public C2874y(String str, int i9) {
            this.f12562a = str;
            this.f12563b = i9;
            this.f12564c = "starttls";
            this.f12565d = true;
        }
    }
}
