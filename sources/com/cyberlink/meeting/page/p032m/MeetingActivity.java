package com.cyberlink.meeting.page.p032m;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.C0476e;
import androidx.recyclerview.widget.C0487p;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.cyberlink.clrtc.C1019i;
import com.cyberlink.clrtc.InterfaceC1081o7;
import com.cyberlink.clrtc.InterfaceC1113s;
import com.cyberlink.clrtc.NetQuality;
import com.cyberlink.clrtc.NileError;
import com.cyberlink.clrtc.NileNetwork;
import com.cyberlink.clrtc.model.ActiveMedia;
import com.cyberlink.clrtc.model.ChatMsg;
import com.cyberlink.clrtc.rtc.RTCAudioManager;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.meeting.clrtc.MeetingManager;
import com.cyberlink.meeting.doserver.C1260a;
import com.cyberlink.meeting.doserver.NetworkManager;
import com.cyberlink.meeting.model.BreakoutRoom;
import com.cyberlink.meeting.model.C1262a;
import com.cyberlink.meeting.model.InviteeList;
import com.cyberlink.meeting.model.Meeting;
import com.cyberlink.meeting.model.SubscriptionInfo;
import com.cyberlink.meeting.page.BreakoutRoomTransitionActivity;
import com.cyberlink.meeting.page.ConfirmPwdActivity;
import com.cyberlink.meeting.page.InviteMeetingActivity;
import com.cyberlink.meeting.page.p032m.AvatarDrawableCache;
import com.cyberlink.meeting.page.p032m.C1375e;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.meeting.page.p032m.ParticipantsAdapter;
import com.cyberlink.p030U.R;
import com.cyberlink.util.DeviceCapability;
import com.cyberlink.widget.ViewPager;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.ShowMediaActivity;
import com.cyberlink.you.activity.UpgradeToProUserActivity;
import com.cyberlink.you.activity.VideoPlaybackActivity;
import com.cyberlink.you.activity.chatdialog.C2027b;
import com.cyberlink.you.activity.splash.SplashActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.NotificationHelper;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.chat.p034e.C2889b;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MediaDao;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.PhoneCallObj;
import com.cyberlink.you.feedback.PromisedTask;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.messaging.Constants;
import com.rockerhieu.emojicon.EmojiconEditText;
import com.rockerhieu.emojicon.EmojiconTextView;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipInputStream;
import okhttp3.C5522w;
import okhttp3.C5523x;
import okhttp3.C5525z;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglRenderer;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceViewRenderer;
import p003a2.C0011a;
import p003a2.C0012b;
import p013b2.C0598a;
import p042d0.C4619d;
import p047d5.C4677a;
import p116k4.C5145g;
import p116k4.C5154j;
import p116k4.C5157k;
import p116k4.C5169o;
import p116k4.C5170o0;
import p116k4.C5177q1;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p135m2.C5315b;
import p136m3.C5321e;
import p137m4.C5325c;
import p164p2.AbstractViewOnTouchListenerC5918e;
import p164p2.C5926f;
import p164p2.RunnableC6018q3;
import p164p2.ViewOnTouchListenerC5910d;
import p173q2.C6127a;
import p189s0.AbstractC6243a;
import p197t.C6273a;
import p209u2.AbstractC6381r;
import p209u2.C6366c;
import p209u2.C6370g;
import p209u2.C6374k;
import p209u2.C6382s;
import p209u2.C6385v;
import p218v2.C6456d;
import p218v2.C6468p;
import p245y2.C6696m5;

/* loaded from: classes.dex */
public class MeetingActivity extends BaseFragmentActivity implements InterfaceC1113s, InterfaceC1081o7 {

    /* renamed from: v5 */
    public static boolean f6496v5 = false;

    /* renamed from: A */
    public boolean f6501A;

    /* renamed from: A0 */
    public ImageView f6502A0;

    /* renamed from: A1 */
    public MediaPlayer f6503A1;

    /* renamed from: A2 */
    public View f6504A2;

    /* renamed from: A3 */
    public Dialog f6505A3;

    /* renamed from: A4 */
    public AdapterView.OnItemLongClickListener f6506A4;

    /* renamed from: B */
    public boolean f6507B;

    /* renamed from: B0 */
    public ViewGroup f6508B0;

    /* renamed from: B1 */
    public C1358w1 f6509B1;

    /* renamed from: B2 */
    public EditText f6510B2;

    /* renamed from: B3 */
    public Dialog f6511B3;

    /* renamed from: B4 */
    public AbsListView.OnScrollListener f6512B4;

    /* renamed from: C */
    public boolean f6513C;

    /* renamed from: C0 */
    public EmojiconEditText f6514C0;

    /* renamed from: C1 */
    public C0598a f6515C1;

    /* renamed from: C2 */
    public TextView f6516C2;

    /* renamed from: C4 */
    public C2027b.i f6518C4;

    /* renamed from: D */
    public boolean f6519D;

    /* renamed from: D0 */
    public Button f6520D0;

    /* renamed from: D1 */
    public C2027b f6521D1;

    /* renamed from: D2 */
    public TextView f6522D2;

    /* renamed from: D4 */
    public View.OnClickListener f6524D4;

    /* renamed from: E */
    public boolean f6525E;

    /* renamed from: E0 */
    public View f6526E0;

    /* renamed from: E1 */
    public C4619d f6527E1;

    /* renamed from: E2 */
    public ImageView f6528E2;

    /* renamed from: E4 */
    public Runnable f6530E4;

    /* renamed from: F */
    public boolean f6531F;

    /* renamed from: F0 */
    public View f6532F0;

    /* renamed from: F1 */
    public ChatMsg f6533F1;

    /* renamed from: F3 */
    public View f6535F3;

    /* renamed from: F4 */
    public boolean f6536F4;

    /* renamed from: G */
    public boolean f6537G;

    /* renamed from: G0 */
    public C4619d f6538G0;

    /* renamed from: G1 */
    public PowerManager.WakeLock f6539G1;

    /* renamed from: G2 */
    public String f6540G2;

    /* renamed from: G3 */
    public View f6541G3;

    /* renamed from: G4 */
    public AlertDialog f6542G4;

    /* renamed from: H */
    public boolean f6543H;

    /* renamed from: H0 */
    public ViewGroup f6544H0;

    /* renamed from: H1 */
    public PowerManager.WakeLock f6545H1;

    /* renamed from: H2 */
    public String f6546H2;

    /* renamed from: H4 */
    public AlertDialog f6548H4;

    /* renamed from: I0 */
    public EmojiconTextView f6550I0;

    /* renamed from: I3 */
    public boolean f6553I3;

    /* renamed from: I4 */
    public WebViewClient f6554I4;

    /* renamed from: J */
    public boolean f6555J;

    /* renamed from: J0 */
    public View f6556J0;

    /* renamed from: J2 */
    public String f6558J2;

    /* renamed from: J3 */
    public boolean f6559J3;

    /* renamed from: J4 */
    public WebChromeClient f6560J4;

    /* renamed from: K */
    public boolean f6561K;

    /* renamed from: K0 */
    public View f6562K0;

    /* renamed from: K3 */
    public Button f6565K3;

    /* renamed from: K4 */
    public final Runnable f6566K4;

    /* renamed from: L */
    public boolean f6567L;

    /* renamed from: L0 */
    public View f6568L0;

    /* renamed from: L3 */
    public boolean f6571L3;

    /* renamed from: L4 */
    public boolean f6572L4;

    /* renamed from: M0 */
    public View f6574M0;

    /* renamed from: M2 */
    public ToneGenerator f6576M2;

    /* renamed from: M3 */
    public View f6577M3;

    /* renamed from: M4 */
    public final Runnable f6578M4;

    /* renamed from: N */
    public boolean f6579N;

    /* renamed from: N0 */
    public TextView f6580N0;

    /* renamed from: N2 */
    public MediaSession f6582N2;

    /* renamed from: N3 */
    public View f6583N3;

    /* renamed from: N4 */
    public final Runnable f6584N4;

    /* renamed from: O */
    public boolean f6585O;

    /* renamed from: O0 */
    public ViewGroup f6586O0;

    /* renamed from: O1 */
    public boolean f6587O1;

    /* renamed from: O4 */
    public Runnable f6590O4;

    /* renamed from: P0 */
    public TextView f6592P0;

    /* renamed from: P3 */
    public Runnable f6595P3;

    /* renamed from: P4 */
    public View f6596P4;

    /* renamed from: Q0 */
    public TextView f6598Q0;

    /* renamed from: Q1 */
    public boolean f6599Q1;

    /* renamed from: Q2 */
    public ImageView f6600Q2;

    /* renamed from: Q4 */
    public TextView f6602Q4;

    /* renamed from: R */
    public ViewPager f6603R;

    /* renamed from: R0 */
    public View f6604R0;

    /* renamed from: R1 */
    public int f6605R1;

    /* renamed from: R2 */
    public Button f6606R2;

    /* renamed from: R4 */
    public Runnable f6608R4;

    /* renamed from: S */
    public LinearLayout f6609S;

    /* renamed from: S0 */
    public ListView f6610S0;

    /* renamed from: S4 */
    public boolean f6614S4;

    /* renamed from: T */
    public C1355v1 f6615T;

    /* renamed from: T0 */
    public C1375e f6616T0;

    /* renamed from: T1 */
    public NileNetwork f6617T1;

    /* renamed from: T3 */
    public int f6619T3;

    /* renamed from: T4 */
    public boolean f6620T4;

    /* renamed from: U */
    public C1376f f6621U;

    /* renamed from: U0 */
    public TextView f6622U0;

    /* renamed from: U2 */
    public View f6624U2;

    /* renamed from: U3 */
    public FrameLayout f6625U3;

    /* renamed from: U4 */
    public int f6626U4;

    /* renamed from: V */
    public C1376f f6627V;

    /* renamed from: V0 */
    public ViewGroup f6628V0;

    /* renamed from: V2 */
    public String f6630V2;

    /* renamed from: V4 */
    public ViewPager.InterfaceC0557j f6632V4;

    /* renamed from: W */
    public C1376f f6633W;

    /* renamed from: W0 */
    public ViewGroup f6634W0;

    /* renamed from: W2 */
    public long f6636W2;

    /* renamed from: W4 */
    public C5325c.b f6638W4;

    /* renamed from: X0 */
    public ViewGroup f6640X0;

    /* renamed from: X2 */
    public long f6642X2;

    /* renamed from: X4 */
    public NileNetwork.DisplayMode f6644X4;

    /* renamed from: Y0 */
    public ViewGroup f6646Y0;

    /* renamed from: Y2 */
    public AlertDialog f6648Y2;

    /* renamed from: Y4 */
    public final Object f6650Y4;

    /* renamed from: Z */
    public ViewGroup f6651Z;

    /* renamed from: Z0 */
    public ViewGroup f6652Z0;

    /* renamed from: Z1 */
    public SurfaceViewRenderer f6653Z1;

    /* renamed from: Z4 */
    public boolean f6656Z4;

    /* renamed from: a0 */
    public View f6657a0;

    /* renamed from: a1 */
    public ViewGroup f6658a1;

    /* renamed from: a5 */
    public final Animator.AnimatorListener f6662a5;

    /* renamed from: b0 */
    public TextView f6663b0;

    /* renamed from: b1 */
    public ViewGroup f6664b1;

    /* renamed from: b3 */
    public Dialog f6666b3;

    /* renamed from: b5 */
    public final int f6668b5;

    /* renamed from: c0 */
    public ListView f6670c0;

    /* renamed from: c1 */
    public TextView f6671c1;

    /* renamed from: c5 */
    public final int f6675c5;

    /* renamed from: d0 */
    public ParticipantsAdapter f6677d0;

    /* renamed from: d1 */
    public TextView f6678d1;

    /* renamed from: d2 */
    public boolean f6679d2;

    /* renamed from: d5 */
    public final int f6682d5;

    /* renamed from: e0 */
    public ViewGroup f6684e0;

    /* renamed from: e1 */
    public TextView f6685e1;

    /* renamed from: e3 */
    public String f6687e3;

    /* renamed from: e5 */
    public final int f6689e5;

    /* renamed from: f0 */
    public ViewGroup f6691f0;

    /* renamed from: f1 */
    public TextView f6692f1;

    /* renamed from: f2 */
    public AlertDialog f6693f2;

    /* renamed from: f3 */
    public C1337p1 f6694f3;

    /* renamed from: f5 */
    public volatile int f6696f5;

    /* renamed from: g0 */
    public View f6698g0;

    /* renamed from: g1 */
    public TextView f6699g1;

    /* renamed from: g2 */
    public boolean f6700g2;

    /* renamed from: g5 */
    public final TimeInterpolator f6703g5;

    /* renamed from: h0 */
    public ViewGroup f6705h0;

    /* renamed from: h1 */
    public View f6706h1;

    /* renamed from: h5 */
    public final TimeInterpolator f6710h5;

    /* renamed from: i */
    public String f6711i;

    /* renamed from: i0 */
    public View f6712i0;

    /* renamed from: i1 */
    public ImageView f6713i1;

    /* renamed from: i5 */
    public final long f6717i5;

    /* renamed from: j */
    public String f6718j;

    /* renamed from: j0 */
    public View f6719j0;

    /* renamed from: j1 */
    public ImageView f6720j1;

    /* renamed from: j5 */
    public final long f6724j5;

    /* renamed from: k */
    public String f6725k;

    /* renamed from: k0 */
    public View f6726k0;

    /* renamed from: k1 */
    public AlertDialog f6727k1;

    /* renamed from: k5 */
    public final Runnable f6731k5;

    /* renamed from: l */
    public String f6732l;

    /* renamed from: l0 */
    public ViewGroup f6733l0;

    /* renamed from: l1 */
    public View f6734l1;

    /* renamed from: l5 */
    public final Animator.AnimatorListener f6738l5;

    /* renamed from: m */
    public String f6739m;

    /* renamed from: m0 */
    public View f6740m0;

    /* renamed from: m1 */
    public View f6741m1;

    /* renamed from: m5 */
    public final Animator.AnimatorListener f6745m5;

    /* renamed from: n */
    public String f6746n;

    /* renamed from: n0 */
    public View f6747n0;

    /* renamed from: n2 */
    public RunnableC1346s1 f6749n2;

    /* renamed from: n3 */
    public View f6750n3;

    /* renamed from: n5 */
    public View.OnTouchListener f6752n5;

    /* renamed from: o */
    public String f6753o;

    /* renamed from: o0 */
    public View f6754o0;

    /* renamed from: o1 */
    public AvatarDrawableCache f6755o1;

    /* renamed from: o2 */
    public RunnableC1349t1 f6756o2;

    /* renamed from: o3 */
    public Runnable f6757o3;

    /* renamed from: o5 */
    public RendererCommon.RendererEvents f6759o5;

    /* renamed from: p */
    public String f6760p;

    /* renamed from: p0 */
    public TextView f6761p0;

    /* renamed from: p2 */
    public RunnableC1352u1 f6763p2;

    /* renamed from: p5 */
    public final Runnable f6766p5;

    /* renamed from: q */
    public String f6767q;

    /* renamed from: q0 */
    public EmojiconTextView f6768q0;

    /* renamed from: q4 */
    public AbstractC1343r1 f6772q4;

    /* renamed from: q5 */
    public EglRenderer.FrameListener f6773q5;

    /* renamed from: r */
    public String f6774r;

    /* renamed from: r0 */
    public TextView f6775r0;

    /* renamed from: r4 */
    public PhoneStateListener f6779r4;

    /* renamed from: r5 */
    public RendererCommon.RendererEvents f6780r5;

    /* renamed from: s */
    public String f6781s;

    /* renamed from: s0 */
    public EmojiconTextView f6782s0;

    /* renamed from: s1 */
    public C1260a f6783s1;

    /* renamed from: s4 */
    public AudioManager.OnAudioFocusChangeListener f6786s4;

    /* renamed from: s5 */
    public RendererCommon.RendererEvents f6787s5;

    /* renamed from: t */
    public String f6788t;

    /* renamed from: t0 */
    public ImageView f6789t0;

    /* renamed from: t1 */
    public AudioManager f6790t1;

    /* renamed from: t3 */
    public int f6792t3;

    /* renamed from: t4 */
    public final View.OnClickListener f6793t4;

    /* renamed from: t5 */
    public final C6374k.c f6794t5;

    /* renamed from: u */
    public Friend f6795u;

    /* renamed from: u0 */
    public TextView f6796u0;

    /* renamed from: u1 */
    public C0012b f6797u1;

    /* renamed from: u3 */
    public Dialog f6799u3;

    /* renamed from: u4 */
    public View.OnClickListener f6800u4;

    /* renamed from: u5 */
    public View.OnClickListener f6801u5;

    /* renamed from: v0 */
    public ImageView f6803v0;

    /* renamed from: v1 */
    public C0012b f6804v1;

    /* renamed from: v3 */
    public Dialog f6806v3;

    /* renamed from: v4 */
    public View.OnClickListener f6807v4;

    /* renamed from: w */
    public Group f6808w;

    /* renamed from: w0 */
    public EmojiconTextView f6809w0;

    /* renamed from: w3 */
    public Dialog f6812w3;

    /* renamed from: w4 */
    public View.OnClickListener f6813w4;

    /* renamed from: x */
    public String f6814x;

    /* renamed from: x0 */
    public ImageView f6815x0;

    /* renamed from: x3 */
    public Runnable f6818x3;

    /* renamed from: x4 */
    public View.OnClickListener f6819x4;

    /* renamed from: y */
    public InviteCallType f6820y;

    /* renamed from: y0 */
    public ImageView f6821y0;

    /* renamed from: y1 */
    public Vibrator f6822y1;

    /* renamed from: y4 */
    public View.OnClickListener f6825y4;

    /* renamed from: z */
    public boolean f6826z;

    /* renamed from: z0 */
    public ImageView f6827z0;

    /* renamed from: z1 */
    public MediaPlayer f6828z1;

    /* renamed from: z2 */
    public ViewGroup f6829z2;

    /* renamed from: z4 */
    public View.OnClickListener f6831z4;

    /* renamed from: w5 */
    public static final ExecutorService f6497w5 = Executors.newSingleThreadExecutor();

    /* renamed from: x5 */
    public static float f6498x5 = BitmapDescriptorFactory.HUE_RED;

    /* renamed from: y5 */
    public static int f6499y5 = 0;

    /* renamed from: z5 */
    public static int f6500z5 = 0;

    /* renamed from: A5 */
    public static int f6493A5 = 0;

    /* renamed from: B5 */
    public static int f6494B5 = 0;

    /* renamed from: C5 */
    public static boolean f6495C5 = false;

    /* renamed from: c */
    public final SimpleDateFormat f6669c = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /* renamed from: d */
    public boolean f6676d = false;

    /* renamed from: e */
    public C6696m5 f6683e = new C6696m5(1, R.string.copy, R.drawable.icon_option_copy);

    /* renamed from: f */
    public C6696m5 f6690f = new C6696m5(2, R.string.save_to_my_device, R.drawable.icon_option_save);

    /* renamed from: g */
    public final ExecutorService f6697g = Executors.newSingleThreadExecutor();

    /* renamed from: h */
    public final ExecutorService f6704h = Executors.newSingleThreadExecutor();

    /* renamed from: v */
    public Meeting.Creator f6802v = null;

    /* renamed from: I */
    public boolean f6549I = true;

    /* renamed from: M */
    public MeetingEstablishStep f6573M = MeetingEstablishStep.UNKNOWN;

    /* renamed from: P */
    public boolean f6591P = false;

    /* renamed from: Q */
    public Activity f6597Q = null;

    /* renamed from: X */
    public ArrayList<C1376f> f6639X = new ArrayList<>();

    /* renamed from: Y */
    public ArrayList<C0012b> f6645Y = new ArrayList<>();

    /* renamed from: n1 */
    public final String f6748n1 = ULogUtility.m16677m();

    /* renamed from: p1 */
    public ArrayList<View> f6762p1 = new ArrayList<>();

    /* renamed from: q1 */
    public final LongSparseArray<Friend> f6769q1 = new LongSparseArray<>();

    /* renamed from: r1 */
    public final LongSparseArray<InviteeList.Invitee> f6776r1 = new LongSparseArray<>();

    /* renamed from: w1 */
    public List<C0012b> f6810w1 = new ArrayList();

    /* renamed from: x1 */
    public List<ActiveMedia.Type> f6816x1 = new ArrayList();

    /* renamed from: I1 */
    public boolean f6551I1 = false;

    /* renamed from: J1 */
    public volatile boolean f6557J1 = false;

    /* renamed from: K1 */
    public boolean f6563K1 = false;

    /* renamed from: L1 */
    public boolean f6569L1 = false;

    /* renamed from: M1 */
    public boolean f6575M1 = false;

    /* renamed from: N1 */
    public boolean f6581N1 = false;

    /* renamed from: P1 */
    public boolean f6593P1 = false;

    /* renamed from: S1 */
    public int f6611S1 = 600;

    /* renamed from: U1 */
    public RTCAudioManager f6623U1 = null;

    /* renamed from: V1 */
    public DeviceCapability.C1399a f6629V1 = null;

    /* renamed from: W1 */
    public ViewOnTouchListenerC5910d f6635W1 = null;

    /* renamed from: X1 */
    public boolean f6641X1 = false;

    /* renamed from: Y1 */
    public boolean f6647Y1 = false;

    /* renamed from: a2 */
    public int f6659a2 = 0;

    /* renamed from: b2 */
    public boolean f6665b2 = true;

    /* renamed from: c2 */
    public boolean f6672c2 = true;

    /* renamed from: e2 */
    public boolean f6686e2 = false;

    /* renamed from: h2 */
    public volatile boolean f6707h2 = false;

    /* renamed from: i2 */
    public volatile boolean f6714i2 = false;

    /* renamed from: j2 */
    public volatile boolean f6721j2 = false;

    /* renamed from: k2 */
    public volatile boolean f6728k2 = false;

    /* renamed from: l2 */
    public List<Pair<Object, String>> f6735l2 = new ArrayList();

    /* renamed from: m2 */
    public boolean f6742m2 = false;

    /* renamed from: q2 */
    public boolean f6770q2 = false;

    /* renamed from: r2 */
    public String f6777r2 = null;

    /* renamed from: s2 */
    public String f6784s2 = null;

    /* renamed from: t2 */
    public String f6791t2 = null;

    /* renamed from: u2 */
    public boolean f6798u2 = false;

    /* renamed from: v2 */
    public long f6805v2 = 0;

    /* renamed from: w2 */
    public boolean f6811w2 = false;

    /* renamed from: x2 */
    public boolean f6817x2 = false;

    /* renamed from: y2 */
    public boolean f6823y2 = false;

    /* renamed from: F2 */
    public boolean f6534F2 = false;

    /* renamed from: I2 */
    public boolean f6552I2 = false;

    /* renamed from: K2 */
    public Meeting.VoipInfo f6564K2 = null;

    /* renamed from: L2 */
    public boolean f6570L2 = false;

    /* renamed from: O2 */
    public BreakoutRoomState f6588O2 = BreakoutRoomState.NONE;

    /* renamed from: P2 */
    public String f6594P2 = null;

    /* renamed from: S2 */
    public ArrayList<BreakoutRoom> f6612S2 = new ArrayList<>();

    /* renamed from: T2 */
    public boolean f6618T2 = false;

    /* renamed from: Z2 */
    public int f6654Z2 = 10;

    /* renamed from: a3 */
    public boolean f6660a3 = false;

    /* renamed from: c3 */
    public boolean f6673c3 = false;

    /* renamed from: d3 */
    public boolean f6680d3 = false;

    /* renamed from: g3 */
    public boolean f6701g3 = true;

    /* renamed from: h3 */
    public Dialog f6708h3 = null;

    /* renamed from: i3 */
    public boolean f6715i3 = false;

    /* renamed from: j3 */
    public boolean f6722j3 = false;

    /* renamed from: k3 */
    public boolean f6729k3 = true;

    /* renamed from: l3 */
    public List<Integer> f6736l3 = new ArrayList();

    /* renamed from: m3 */
    public List<Integer> f6743m3 = new ArrayList();

    /* renamed from: p3 */
    public boolean f6764p3 = false;

    /* renamed from: q3 */
    public boolean f6771q3 = false;

    /* renamed from: r3 */
    public boolean f6778r3 = false;

    /* renamed from: s3 */
    public boolean f6785s3 = false;

    /* renamed from: y3 */
    public boolean f6824y3 = false;

    /* renamed from: z3 */
    public boolean f6830z3 = true;

    /* renamed from: C3 */
    public long f6517C3 = 0;

    /* renamed from: D3 */
    public String f6523D3 = "";

    /* renamed from: E3 */
    public boolean f6529E3 = false;

    /* renamed from: H3 */
    public int f6547H3 = -1;

    /* renamed from: O3 */
    public List<JSONObject> f6589O3 = new ArrayList();

    /* renamed from: Q3 */
    public final Object f6601Q3 = new Object();

    /* renamed from: R3 */
    public final Map<String, MessageObj> f6607R3 = new HashMap();

    /* renamed from: S3 */
    public UploadMultipleChatMediaHelperQueue.InterfaceC3194g f6613S3 = new C1320k();

    /* renamed from: V3 */
    public C6366c f6631V3 = new C6366c(10);

    /* renamed from: W3 */
    public boolean f6637W3 = false;

    /* renamed from: X3 */
    public boolean f6643X3 = false;

    /* renamed from: Y3 */
    public int f6649Y3 = 0;

    /* renamed from: Z3 */
    public List<Integer> f6655Z3 = new ArrayList();

    /* renamed from: a4 */
    public List<Integer> f6661a4 = new ArrayList();

    /* renamed from: b4 */
    public CountDownTimer f6667b4 = null;

    /* renamed from: c4 */
    public int f6674c4 = 3;

    /* renamed from: d4 */
    public boolean f6681d4 = false;

    /* renamed from: e4 */
    public final Runnable f6688e4 = new Runnable() { // from class: p2.d5
        @Override // java.lang.Runnable
        public final void run() {
            this.f20402b.m6508rc();
        }
    };

    /* renamed from: f4 */
    public final Runnable f6695f4 = new Runnable() { // from class: p2.z0
        @Override // java.lang.Runnable
        public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            this.f20675b.m6520sc();
        }
    };

    /* renamed from: g4 */
    public final Runnable f6702g4 = new Runnable() { // from class: p2.k1
        @Override // java.lang.Runnable
        public final void run() {
            this.f20494b.m6051Cc();
        }
    };

    /* renamed from: h4 */
    public final C6468p.c f6709h4 = new C6468p.c() { // from class: p2.q1
        @Override // p218v2.C6468p.c
        /* renamed from: a */
        public final void mo117a(String str, boolean z8) {
            this.f20579a.m6532tc(str, z8);
        }
    };

    /* renamed from: i4 */
    public View f6716i4 = null;

    /* renamed from: j4 */
    public ParticipantsAdapter.InterfaceC1367c f6723j4 = new C1353v();

    /* renamed from: k4 */
    public final Runnable f6730k4 = new RunnableC1328m1();

    /* renamed from: l4 */
    public final PromisedTask.AbstractC3021b<Meeting> f6737l4 = new C1331n1();

    /* renamed from: m4 */
    public Runnable f6744m4 = new RunnableC1290a();

    /* renamed from: n4 */
    public Runnable f6751n4 = new RunnableC1293b();

    /* renamed from: o4 */
    public C5321e.m f6758o4 = new C5321e.m() { // from class: p2.r1
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f20587b.m6062Dc(c2904l, map);
        }
    };

    /* renamed from: p4 */
    public boolean f6765p4 = false;

    public enum AspectRatio {
        PORTRAIT,
        LANDSCAPE
    }

    public enum BreakoutRoomState {
        NONE,
        PARENT,
        CHILD
    }

    public enum InviteCallType {
        CALLER,
        CALLEE
    }

    public enum MeetingEstablishStep {
        UNKNOWN(0),
        CALLER_DO_SERVER_CREATE_MEETING_COMPLETE(1),
        CALLER_M_SERVER_JOIN_MEETING_COMPLETE(2),
        CALLER_XMPP_SEND_INVITE_EVENT_COMPLETE(3),
        CALLER_M_SERVER_CALLEE_ONLINE(4);

        private final int value;

        MeetingEstablishStep(int i9) {
            this.value = i9;
        }
    }

    public enum RollCallEvent {
        START,
        START_NO_LIMIT,
        UPDATE,
        STOP
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$a */
    public class RunnableC1290a implements Runnable {
        public RunnableC1290a() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] meeting invite overtime, leave meeting | id = " + MeetingActivity.this.f6711i + ", inviteType = " + MeetingActivity.this.f6820y);
            MeetingActivity.this.m7366I0().removeCallbacks(this);
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6834oa(meetingActivity.f6537G ? "timeout" : "unreached", "invite timeout");
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$a0 */
    public class ViewOnClickListenerC1291a0 implements View.OnClickListener {
        public ViewOnClickListenerC1291a0() {
        }

        /* renamed from: a */
        public final void m6904a(int i9) {
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.m4957o8(i9);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id != R.id.keypad_well) {
                switch (id) {
                    case R.id.keypad_0 /* 2131298012 */:
                        MeetingActivity.this.f6510B2.append("0");
                        MeetingActivity.this.f6576M2.startTone(0, 100);
                        m6904a(0);
                        break;
                    case R.id.keypad_1 /* 2131298013 */:
                        MeetingActivity.this.f6510B2.append("1");
                        MeetingActivity.this.f6576M2.startTone(1, 100);
                        m6904a(1);
                        break;
                    case R.id.keypad_2 /* 2131298014 */:
                        MeetingActivity.this.f6510B2.append("2");
                        MeetingActivity.this.f6576M2.startTone(2, 100);
                        m6904a(2);
                        break;
                    case R.id.keypad_3 /* 2131298015 */:
                        MeetingActivity.this.f6510B2.append("3");
                        MeetingActivity.this.f6576M2.startTone(3, 100);
                        m6904a(3);
                        break;
                    case R.id.keypad_4 /* 2131298016 */:
                        MeetingActivity.this.f6510B2.append("4");
                        MeetingActivity.this.f6576M2.startTone(4, 100);
                        m6904a(4);
                        break;
                    case R.id.keypad_5 /* 2131298017 */:
                        MeetingActivity.this.f6510B2.append("5");
                        MeetingActivity.this.f6576M2.startTone(5, 100);
                        m6904a(5);
                        break;
                    case R.id.keypad_6 /* 2131298018 */:
                        MeetingActivity.this.f6510B2.append("6");
                        MeetingActivity.this.f6576M2.startTone(6, 100);
                        m6904a(6);
                        break;
                    case R.id.keypad_7 /* 2131298019 */:
                        MeetingActivity.this.f6510B2.append("7");
                        MeetingActivity.this.f6576M2.startTone(7, 100);
                        m6904a(7);
                        break;
                    case R.id.keypad_8 /* 2131298020 */:
                        MeetingActivity.this.f6510B2.append("8");
                        MeetingActivity.this.f6576M2.startTone(8, 100);
                        m6904a(8);
                        break;
                    case R.id.keypad_9 /* 2131298021 */:
                        MeetingActivity.this.f6510B2.append("9");
                        MeetingActivity.this.f6576M2.startTone(9, 100);
                        m6904a(9);
                        break;
                    case R.id.keypad_asterisk /* 2131298022 */:
                        MeetingActivity.this.f6510B2.append("*");
                        MeetingActivity.this.f6576M2.startTone(10, 100);
                        m6904a(10);
                        break;
                }
            } else {
                MeetingActivity.this.f6510B2.append("#");
                MeetingActivity.this.f6576M2.startTone(11, 100);
                m6904a(11);
            }
            MeetingActivity.this.f6510B2.setSelection(MeetingActivity.this.f6510B2.getText().length());
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$a1 */
    public class RunnableC1292a1 implements Runnable {
        public RunnableC1292a1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m6906b(Bitmap bitmap) {
            MeetingActivity.this.f6721j2 = true;
            MeetingActivity.this.m6616Cg(bitmap, false, true);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.m7367J0() || !MeetingActivity.this.f6513C || !MeetingActivity.this.f6555J || (MeetingActivity.this.f6603R.getCurrentItem() != 1 && MeetingActivity.this.f6603R.getCurrentItem() != MeetingActivity.this.m6671L9())) {
                MeetingActivity.this.m6622Dg();
                return;
            }
            MeetingActivity.this.f6714i2 = true;
            if (MeetingActivity.this.f6728k2) {
                MeetingActivity.this.m7366I0().postDelayed(MeetingActivity.this.f6766p5, 100L);
            } else {
                MeetingActivity.this.f6653Z1.addFrameListener(new EglRenderer.FrameListener() { // from class: p2.y5
                    @Override // org.webrtc.EglRenderer.FrameListener
                    public final void onFrame(Bitmap bitmap) {
                        this.f20673a.m6906b(bitmap);
                    }
                }, 1.0f);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$b */
    public class RunnableC1293b implements Runnable {
        public RunnableC1293b() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] wait caller connect overtime, force leave meeting | id = " + MeetingActivity.this.f6711i + ", inviteType = " + MeetingActivity.this.f6820y);
            String str = MeetingActivity.this.f6537G ? "timeout" : "unreached";
            MeetingActivity.this.f6783s1.m5687A(MeetingActivity.this.f6808w, MeetingActivity.this.f6711i, MeetingActivity.this.f6781s, MeetingActivity.this.f6788t, MeetingActivity.this.f6725k, str);
            MeetingActivity.this.f6783s1.m5689C(MeetingActivity.this.f6808w, MeetingActivity.this.f6711i, MeetingActivity.this.f6781s, MeetingActivity.this.f6788t, MeetingActivity.this.f6725k, str);
            String str2 = MeetingActivity.this.f6808w != null ? MeetingActivity.this.f6808w.f13717d : "";
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6757Zf(null, meetingActivity.getString(R.string.unable_to_reach_or_busy, str2, str2));
            MeetingActivity.this.m6738We();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$b0 */
    public class ViewOnLongClickListenerC1294b0 implements View.OnLongClickListener {
        public ViewOnLongClickListenerC1294b0() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!Globals.m7388i0().m7534d2()) {
                return true;
            }
            if (C5170o0.m20170e(MeetingActivity.this.f6746n)) {
                C5187v0.m20268d("shareUrl is null");
            } else {
                C5187v0.m20268d("copy shareUrl success");
                C5145g.m20042a(MeetingActivity.this.f6746n);
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$b1 */
    public class C1295b1 implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ C2973l0 f6858a;

        public C1295b1(C2973l0 c2973l0) {
            this.f6858a = c2973l0;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            Log.d("MeetingActivity", "[saveMedias] onDenied, isNeverAskAgain:" + z8);
            if (z8) {
                C5183t0.m20262b(MeetingActivity.this.f6597Q, Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            Log.d("MeetingActivity", "[saveMedias] onAccept");
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.f6858a);
            C2925v.m14626v0(MeetingActivity.this.f6597Q, arrayList);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$c */
    public class C1296c implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Runnable f6860a;

        /* renamed from: b */
        public final /* synthetic */ Permission f6861b;

        /* renamed from: c */
        public final /* synthetic */ Runnable f6862c;

        public C1296c(Runnable runnable, Permission permission, Runnable runnable2) {
            this.f6860a = runnable;
            this.f6861b = permission;
            this.f6862c = runnable2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m6911e(DialogInterface dialogInterface, int i9) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + MeetingActivity.this.getPackageName()));
            MeetingActivity.this.startActivity(intent);
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (!z8) {
                C5187v0.m20267c(R.string.permission_denied);
                this.f6862c.run();
                return;
            }
            Permission permission = this.f6861b;
            AlertDialog alertDialogCreate = C3123g.m16382a(MeetingActivity.this).setMessage(permission == Permission.MICROPHONE ? R.string.permission_ask_microphone : permission == Permission.BLUETOOTH_BLE ? R.string.permission_ask_nearby_devices : permission == Permission.PHONE_STATE ? R.string.permission_ask_phone : R.string.permission_ask_camera).setPositiveButton(R.string.permission_go_app_setting, new DialogInterface.OnClickListener() { // from class: p2.g5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f20451b.m6911e(dialogInterface, i9);
                }
            }).create();
            final Runnable runnable = this.f6862c;
            alertDialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: p2.h5
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    runnable.run();
                }
            });
            alertDialogCreate.show();
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            this.f6860a.run();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$c0 */
    public class C1297c0 extends GestureDetector.SimpleOnGestureListener {
        public C1297c0() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            int i9 = MeetingActivity.this.f6696f5;
            if (i9 == 3 || i9 == 4) {
                MeetingActivity.this.m6686Nf(true);
                return false;
            }
            MeetingActivity.this.m6862ta(true);
            return false;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$c1 */
    public class C1298c1 extends PromisedTask.AbstractC3021b<SubscriptionInfo> {
        public C1298c1() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ULogUtility.m16676l("MeetingManager", "checkIsProUser failed, error code = " + i9 + " | errorBody = " + str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(SubscriptionInfo subscriptionInfo) {
            ULogUtility.m16680p("MeetingManager", "getSubscriptionInfo : " + MeetingActivity.this.f6742m2);
            MeetingActivity.this.f6742m2 = "pro".equalsIgnoreCase(subscriptionInfo.plan);
            Globals.m7388i0().m7476Q3(MeetingActivity.this.f6742m2);
            Globals.m7388i0().m7642x3(Integer.parseInt(subscriptionInfo.attendeeCapacity));
            Globals.m7388i0().m7647y3(Integer.parseInt(subscriptionInfo.maximumLength));
            Globals.m7388i0().m7646y2(subscriptionInfo.androidOnHoldProductIds);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$d */
    public class C1299d extends AbstractC1343r1 {
        public C1299d() {
            super(null);
        }

        @Override // android.telephony.TelephonyCallback.CallStateListener
        public void onCallStateChanged(int i9) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            if (i9 != 2) {
                return;
            }
            ULogUtility.m16683s("MeetingActivity", "Answer or dial a phone call, close meeting.");
            MeetingActivity.this.m6633Fe("hang up button");
            NotificationHelper.m14078V(MeetingActivity.this.getString(R.string.clm_meeting_answer_or_dial_phone_call));
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$d0 */
    public class C1300d0 implements XMPPManager.InterfaceC2873x {
        public C1300d0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m6915c() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            MeetingActivity.this.m6757Zf(null, null);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        /* renamed from: a */
        public void mo5816a() {
            ULogUtility.m16676l("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] send invite meeting message fail");
            MeetingActivity.this.runOnUiThread(new Runnable() { // from class: p2.q5
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                    this.f20584b.m6915c();
                }
            });
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2873x
        public void onSuccess() {
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            ULogUtility.m16680p("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] send invite meeting done");
            MeetingActivity.this.m6877vh(MeetingEstablishStep.CALLER_XMPP_SEND_INVITE_EVENT_COMPLETE);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$d1 */
    public class C1301d1 extends PromisedTask.AbstractC3021b<Meeting> {
        public C1301d1() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ULogUtility.m16676l("MeetingManager", "queryMeetingInfo failed, error code = " + i9 + " | errorBody = " + str);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            MeetingActivity.this.f6802v = meeting.creator;
            MeetingActivity.this.f6811w2 = meeting.invitationOnly.isInvitationOnly.booleanValue();
            MeetingActivity.this.f6817x2 = meeting.hasPassword.booleanValue();
            MeetingActivity meetingActivity = MeetingActivity.this;
            Boolean bool = meeting.isLtiMeeting;
            meetingActivity.f6823y2 = bool != null ? bool.booleanValue() : false;
            if (MeetingActivity.this.f6823y2 && (MeetingActivity.this.f6588O2 == BreakoutRoomState.CHILD || !MeetingActivity.this.f6618T2)) {
                MeetingActivity.this.f6657a0.findViewById(R.id.btn_meeting_invite).setVisibility(8);
            }
            ULogUtility.m16676l("MeetingManager", "queryMeetingInfo creator = " + MeetingActivity.this.f6802v + " / isLti? " + MeetingActivity.this.f6823y2);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$e */
    public class C1302e extends PhoneStateListener {
        public C1302e() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i9, String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            if (i9 != 2) {
                return;
            }
            ULogUtility.m16683s("MeetingActivity", "Answer or dial a phone call, close meeting.");
            MeetingActivity.this.m6633Fe("hang up button");
            NotificationHelper.m14078V(MeetingActivity.this.getString(R.string.clm_meeting_answer_or_dial_phone_call));
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$e0 */
    public class AsyncTaskC1303e0 extends AsyncTask<Void, Void, LongSparseArray<Friend>> {

        /* renamed from: a */
        public final /* synthetic */ List f6870a;

        /* renamed from: b */
        public final /* synthetic */ Runnable f6871b;

        public AsyncTaskC1303e0(List list, Runnable runnable) {
            this.f6870a = list;
            this.f6871b = runnable;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LongSparseArray<Friend> doInBackground(Void... voidArr) {
            LongSparseArray<Friend> longSparseArray = new LongSparseArray<>();
            if (Globals.m7388i0().m7464O1()) {
                for (C0012b c0012b : this.f6870a) {
                    if (c0012b != MeetingActivity.this.f6797u1 && c0012b.f66b.f65d > 0 && MeetingActivity.this.f6769q1.indexOfKey(c0012b.f66b.f65d) < 0) {
                        Friend friendM15001A = C2950b0.m14899A().m15001A(String.valueOf(c0012b.f66b.f65d));
                        if (friendM15001A == null) {
                            Log.w("MeetingActivity", "Friend of participant not found in database, uid: " + c0012b.f66b.f65d);
                        } else {
                            longSparseArray.put(c0012b.f66b.f65d, friendM15001A);
                        }
                    }
                }
            }
            return longSparseArray;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(LongSparseArray<Friend> longSparseArray) {
            for (int i9 = 0; i9 < longSparseArray.size(); i9++) {
                MeetingActivity.this.f6769q1.put(longSparseArray.keyAt(i9), longSparseArray.valueAt(i9));
            }
            this.f6871b.run();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$e1 */
    public class C1304e1 implements RendererCommon.RendererEvents {
        public C1304e1() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int i9, int i10, int i11) {
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6629Eh(meetingActivity.f6627V, i9, i10, i11);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$f */
    public class C1305f extends AbstractC6381r<List<C0012b>, Void> {
        public C1305f() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(List<C0012b> list) {
            for (C0012b c0012b : list) {
                if (!MeetingActivity.this.f6569L1 && c0012b.m98q()) {
                    MeetingActivity.this.f6569L1 = true;
                }
                if (MeetingActivity.this.f6677d0.m7047q(c0012b)) {
                    MeetingActivity.this.f6677d0.f6990d.m2906a(c0012b);
                }
            }
            if (MeetingActivity.this.f6734l1 != null) {
                MeetingActivity.this.f6734l1.setVisibility(((MeetingActivity.this.f6603R.getCurrentItem() == 1 || MeetingActivity.this.f6603R.getCurrentItem() == MeetingActivity.this.m6671L9()) && MeetingActivity.this.f6569L1 && !MeetingActivity.this.f6778r3) ? 0 : 8);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$f0 */
    public class C1306f0 extends AbstractC6381r<Map<Long, List<String>>, Void> {

        /* renamed from: c */
        public final /* synthetic */ List f6875c;

        public C1306f0(List list) {
            this.f6875c = list;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Map<Long, List<String>> map) {
            NileNetwork nileNetwork = MeetingActivity.this.f6617T1;
            if (nileNetwork == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (C0012b c0012b : this.f6875c) {
                List<String> list = map.get(Long.valueOf(c0012b.f66b.f65d));
                if (list == null) {
                    arrayList.add(c0012b);
                } else {
                    nileNetwork.m4902I8(c0012b, list);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            nileNetwork.m4975x8(arrayList, false);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$f1 */
    public class C1307f1 implements RendererCommon.RendererEvents {
        public C1307f1() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int i9, int i10, int i11) {
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6629Eh(meetingActivity.f6633W, i9, i10, i11);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$g */
    public class C1308g extends AbstractC6381r<List<InviteeList.Invitee>, Integer> {
        public C1308g() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(List<InviteeList.Invitee> list) {
            for (InviteeList.Invitee invitee : list) {
                if (!TextUtils.isEmpty(invitee.uid) && !TextUtils.isEmpty(invitee.avatar)) {
                    MeetingActivity.this.f6776r1.put(Long.valueOf(invitee.uid).longValue(), invitee);
                }
            }
            MeetingActivity.this.f6715i3 = true;
            MeetingActivity.this.f6677d0.m7031A(true);
            MeetingActivity.this.f6677d0.m7037g(list);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Integer num) {
            if (num.intValue() == 403) {
                MeetingActivity.this.f6715i3 = false;
                MeetingActivity.this.f6677d0.m7031A(false);
            }
            super.m24504h(num);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$g0 */
    public class RunnableC1309g0 implements Runnable {
        public RunnableC1309g0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m6924b(View view) {
            MeetingActivity.this.m7366I0().removeCallbacks(this);
            MeetingActivity.this.f6799u3.dismiss();
            MeetingActivity.this.m6839p9("receive kick event in waiting room");
        }

        @Override // java.lang.Runnable
        public void run() {
            TextView textView = (TextView) MeetingActivity.this.f6799u3.findViewById(R.id.v_btn);
            textView.setText(MeetingActivity.this.getResources().getString(R.string.ok) + " (" + MeetingActivity.this.f6792t3 + ")");
            textView.setOnClickListener(new View.OnClickListener() { // from class: p2.o5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20549b.m6924b(view);
                }
            });
            MeetingActivity.this.f6799u3.show();
            if (MeetingActivity.this.f6792t3 == 0) {
                MeetingActivity.this.f6799u3.dismiss();
                MeetingActivity.this.m6839p9("receive kick event in waiting room count down to 0");
            } else {
                MeetingActivity.m6229T4(MeetingActivity.this);
                MeetingActivity.this.m7366I0().postDelayed(this, 1000L);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$g1 */
    public class C1310g1 implements C6374k.c {
        public C1310g1() {
        }

        @Override // p209u2.C6374k.c
        /* renamed from: a */
        public void mo6925a() {
            ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] network changed");
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.f4919e.m4467t("MeetingActivity", "network changed");
            }
        }

        @Override // p209u2.C6374k.c
        /* renamed from: b */
        public void mo6926b() {
            ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] network connected");
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.f4919e.m4467t("MeetingActivity", "network connected");
            }
        }

        @Override // p209u2.C6374k.c
        /* renamed from: c */
        public void mo6927c() {
            ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] network disconnected");
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.f4919e.m4467t("MeetingActivity", "network disconnected");
            }
            C5187v0.m20270f(R.string.clm_warning_disconnected);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$h */
    public class ViewTreeObserverOnGlobalLayoutListenerC1311h implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserverOnGlobalLayoutListenerC1311h() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() throws Resources.NotFoundException {
            MeetingActivity.this.f6651Z.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6652I8(meetingActivity.f6603R.getCurrentItem());
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$h0 */
    public class C1312h0 extends PromisedTask.AbstractC3021b<String> {
        public C1312h0() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(String str) {
            MeetingActivity.this.f6625U3.setVisibility(0);
            WebView webView = (WebView) MeetingActivity.this.f6625U3.findViewById(R.id.rollCallPage);
            webView.setVisibility(0);
            webView.setWebViewClient(MeetingActivity.this.f6554I4);
            webView.setWebChromeClient(MeetingActivity.this.f6560J4);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setTextZoom(100);
            settings.setDomStorageEnabled(true);
            webView.loadUrl(str);
            MeetingActivity.this.m6735W8(true);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$h1 */
    public class C1313h1 extends MediaSession.Callback {
        public C1313h1() {
        }

        @Override // android.media.session.MediaSession.Callback
        public boolean onMediaButtonEvent(Intent intent) throws IllegalStateException {
            if (MeetingActivity.this.f6582N2 == null || intent == null) {
                return false;
            }
            if (!"android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) {
                return super.onMediaButtonEvent(intent);
            }
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            int keyCode = keyEvent.getKeyCode();
            if (keyEvent.getAction() == 1 && (keyCode == 85 || keyCode == 126 || keyCode == 127)) {
                MeetingActivity.this.f6732l = MimeTypes.BASE_TYPE_AUDIO;
                MeetingActivity.this.m6726Ue();
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$i */
    public class C1314i extends PromisedTask.AbstractC3021b<Meeting> {

        /* renamed from: j */
        public final /* synthetic */ DialogC3133q f6884j;

        /* renamed from: k */
        public final /* synthetic */ Runnable f6885k;

        public C1314i(DialogC3133q dialogC3133q, Runnable runnable) {
            this.f6884j = dialogC3133q;
            this.f6885k = runnable;
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) {
            ULogUtility.m16676l("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] get meetingShareUrl failed");
            this.f6884j.dismiss();
            this.f6885k.run();
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            this.f6884j.dismiss();
            MeetingActivity.this.f6746n = meeting.shareAddr;
            this.f6885k.run();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$i0 */
    public class C1315i0 extends WebViewClient {
        public C1315i0() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$i1 */
    public class AsyncTaskC1316i1 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f6888a;

        /* renamed from: b */
        public final /* synthetic */ long f6889b;

        /* renamed from: c */
        public final /* synthetic */ DialogC3133q f6890c;

        public AsyncTaskC1316i1(String str, long j9, DialogC3133q dialogC3133q) {
            this.f6888a = str;
            this.f6889b = j9;
            this.f6890c = dialogC3133q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Globals.C1408b.m7655b().m7657d(this.f6888a, Collections.singletonList(C2950b0.m14914m().m14694C(this.f6888a, C2950b0.m14914m().m14693B(this.f6888a, this.f6889b), 50, MediaDao.SelectType.BOTH)));
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r42) {
            this.f6890c.cancel();
            Intent intent = new Intent(MeetingActivity.this, (Class<?>) ShowMediaActivity.class);
            intent.putExtra("mediaId", this.f6889b);
            intent.putExtra("albumId", this.f6888a);
            intent.putExtra("activityName", "chatDialogActivity");
            intent.putExtra("isOpenFromMeeting", true);
            MeetingActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$j */
    public class C1317j implements Animator.AnimatorListener {

        /* renamed from: a */
        public final /* synthetic */ C1376f f6892a;

        public C1317j(C1376f c1376f) {
            this.f6892a = c1376f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MeetingActivity.this.m6795hf(this.f6892a, false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$j0 */
    public class C1318j0 extends WebChromeClient {
        public C1318j0() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            ((TextView) MeetingActivity.this.f6625U3.findViewById(R.id.title)).setText(webView.getTitle());
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$j1 */
    public class RunnableC1319j1 implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ int f6895b;

        public RunnableC1319j1(int i9) {
            this.f6895b = i9;
        }

        /* renamed from: c */
        public static /* synthetic */ void m6934c(DialogInterface dialogInterface, int i9) {
        }

        /* renamed from: d */
        public static /* synthetic */ void m6935d(DialogInterface dialogInterface, int i9) {
        }

        @Override // java.lang.Runnable
        public void run() throws Resources.NotFoundException {
            int i9 = this.f6895b;
            if (i9 == 2) {
                if (Globals.m7388i0().m7484S1()) {
                    String string = MeetingActivity.this.getResources().getString(R.string.upload_file_size_is_exceeded, CLUtility.m16592u0(MeetingActivity.this.getApplicationContext(), 52428800L));
                    AlertDialog.Builder builderM16382a = C3123g.m16382a(MeetingActivity.this.f6597Q);
                    builderM16382a.setMessage(string);
                    builderM16382a.setNegativeButton(MeetingActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: p2.z5
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i10) {
                            MeetingActivity.RunnableC1319j1.m6934c(dialogInterface, i10);
                        }
                    });
                    builderM16382a.setCancelable(false);
                    builderM16382a.create().show();
                } else {
                    boolean zM7591p = Globals.m7388i0().m7591p();
                    Log.d("MeetingActivity", "NILE_RESULT_CODE_FILE_SIZE_LIMIT canUpgradeToProUser:" + zM7591p);
                    MeetingActivity.this.m6641Gf(zM7591p);
                }
                MeetingActivity.this.m6706R8();
                return;
            }
            if (i9 == 3) {
                String string2 = MeetingActivity.this.getResources().getString(R.string.upload_total_file_size_is_exceeded, CLUtility.m16592u0(MeetingActivity.this.getApplicationContext(), 524288000L));
                AlertDialog.Builder builderM16382a2 = C3123g.m16382a(MeetingActivity.this.f6597Q);
                builderM16382a2.setMessage(string2);
                builderM16382a2.setNegativeButton(MeetingActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: p2.a6
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        MeetingActivity.RunnableC1319j1.m6935d(dialogInterface, i10);
                    }
                });
                builderM16382a2.setCancelable(false);
                builderM16382a2.create().show();
                MeetingActivity.this.m6706R8();
                return;
            }
            if (i9 != 6) {
                return;
            }
            boolean zM7591p2 = Globals.m7388i0().m7591p();
            Log.d("MeetingActivity", "NILE_RESULT_CODE_NUMBER_OF_FILE_LIMIT canUpgradeToProUser:" + zM7591p2);
            MeetingActivity.this.m6634Ff(zM7591p2);
            MeetingActivity.this.m6706R8();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$k */
    public class C1320k implements UploadMultipleChatMediaHelperQueue.InterfaceC3194g {

        /* renamed from: b */
        public long f6897b = 0;

        public C1320k() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m6938d() {
            MeetingActivity.this.f6616T0.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m6939e(String str, int i9) {
            MessageObj messageObjM6748Y9 = MeetingActivity.this.m6748Y9(str);
            messageObjM6748Y9.f12924A = true;
            messageObjM6748Y9.f12959z = i9;
            MeetingActivity.this.f6616T0.m7112C(messageObjM6748Y9);
            MeetingActivity.this.runOnUiThread(new Runnable() { // from class: p2.f5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20435b.m6938d();
                }
            });
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: G */
        public void mo6940G(String str) {
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: K */
        public void mo6941K(String str) {
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: U */
        public void mo6942U(String str) {
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: W */
        public void mo6943W(String str) {
        }

        /* renamed from: f */
        public final void m6944f(String str) {
            if (str == null) {
                UploadUtils.m16965l("MeetingActivity", "[mUploadCallback::updateMsgObj] messageId == null");
                return;
            }
            UploadUtils.m16965l("MeetingActivity", "[mUploadCallback::updateMsgObj] messageId = " + str);
            MessageObj messageObjM6748Y9 = MeetingActivity.this.m6748Y9(str);
            if (messageObjM6748Y9 == null) {
                UploadUtils.m16965l("MeetingActivity", "[mUploadCallback::updateMsgObj] Can't get message object. Message Id=" + str);
                return;
            }
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
            if (messageObjM15179r != null) {
                messageObjM6748Y9.m14771g0(messageObjM15179r);
                messageObjM6748Y9.m14762X("0");
            } else {
                UploadUtils.m16965l("MeetingActivity", "[mUploadCallback::updateMsgObj] Can't get message object from DB. Message Id=" + str);
            }
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: f0 */
        public void mo6945f0(String str, int i9) {
            ULogUtility.m16670f("MeetingActivity", "onBeginUpload" + str);
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: m */
        public void mo6946m(C2973l0 c2973l0, String str) {
            UploadUtils.m16965l("MeetingActivity", "[onComplete] in");
            m6944f(str);
            UploadUtils.m16965l("MeetingActivity", "[onComplete] get messageObj(" + str + ") from mMapMessageList");
            MessageObj messageObjM6748Y9 = MeetingActivity.this.m6748Y9(str);
            if (messageObjM6748Y9 != null) {
                messageObjM6748Y9.f12924A = false;
                UploadUtils.m16965l("MeetingActivity", "[onComplete] get messageObj getUploadStatus is " + messageObjM6748Y9.m14744F());
            }
            if (messageObjM6748Y9 != null && messageObjM6748Y9.m14778p() == MessageObj.MessageType.Video && C2925v.m14604k0(messageObjM6748Y9)) {
                String strM14582Z = C2925v.m14582Z(messageObjM6748Y9, c2973l0);
                UploadUtils.m16965l("MeetingActivity", "[onComplete] isServerTranscode, put  messageObj into map list with new message id");
                MeetingActivity.this.f6607R3.put(strM14582Z, messageObjM6748Y9);
            }
            UploadUtils.m16965l("MeetingActivity", "[onComplete] start to updatePhoto UI ");
            MeetingActivity.this.f6617T1.m4920W7(new C5926f(c2973l0).m23391m());
            MeetingActivity.this.m6896yh();
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: t */
        public void mo6947t(final String str, final int i9) {
            ULogUtility.m16670f("MeetingActivity", "onProgress" + str + ", progress:" + i9);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - 500 > this.f6897b) {
                this.f6897b = jCurrentTimeMillis;
                new Thread(new Runnable() { // from class: p2.e5
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f20419b.m6939e(str, i9);
                    }
                }).start();
            }
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: t0 */
        public void mo6948t0(String str) {
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$k0 */
    public class RunnableC1321k0 implements Runnable {
        public RunnableC1321k0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m6950b(DialogInterface dialogInterface, int i9) {
            dialogInterface.dismiss();
            MeetingActivity.this.m7366I0().removeCallbacks(MeetingActivity.this.f6566K4);
            MeetingActivity.this.f6600Q2.callOnClick();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.f6648Y2 == null) {
                MeetingActivity meetingActivity = MeetingActivity.this;
                AlertDialog.Builder builderM16382a = C3123g.m16382a(meetingActivity);
                MeetingActivity meetingActivity2 = MeetingActivity.this;
                AlertDialog.Builder message = builderM16382a.setMessage(meetingActivity2.getString(R.string.breakout_room_host_close_count_down, Integer.valueOf(meetingActivity2.f6654Z2)));
                MeetingActivity meetingActivity3 = MeetingActivity.this;
                meetingActivity.f6648Y2 = message.setPositiveButton(meetingActivity3.m6861t9(meetingActivity3.getString(R.string.breakout_room_leave_dialog_return_to_main)), new DialogInterface.OnClickListener() { // from class: p2.r5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f20593b.m6950b(dialogInterface, i9);
                    }
                }).setCancelable(false).create();
            } else {
                TextView textView = (TextView) MeetingActivity.this.f6648Y2.findViewById(android.R.id.message);
                if (textView != null) {
                    textView.setGravity(17);
                    MeetingActivity meetingActivity4 = MeetingActivity.this;
                    textView.setText(meetingActivity4.getString(R.string.breakout_room_host_close_count_down, Integer.valueOf(meetingActivity4.f6654Z2)));
                }
            }
            MeetingActivity.this.f6648Y2.show();
            Button button = MeetingActivity.this.f6648Y2.getButton(-1);
            if (button != null) {
                button.setTextColor(MeetingActivity.this.getResources().getColor(R.color.you_color_normal_blue));
                button.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t17dp));
            }
            if (MeetingActivity.this.f6654Z2 == 0) {
                MeetingActivity.this.f6600Q2.callOnClick();
            } else {
                MeetingActivity.m6294Z3(MeetingActivity.this);
                MeetingActivity.this.m7366I0().postDelayed(this, 1000L);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$k1 */
    public static /* synthetic */ class C1322k1 {

        /* renamed from: a */
        public static final /* synthetic */ int[] f6900a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f6901b;

        static {
            int[] iArr = new int[NileError.values().length];
            f6901b = iArr;
            try {
                iArr[NileError.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6901b[NileError.CALL_JOIN_TIMEOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6901b[NileError.CALL_CALLEE_TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6901b[NileError.CALL_PREJOIN_TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6901b[NileError.CALL_CONFIRMJOIN_TIMEOUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6901b[NileError.CALL_JOIN_FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6901b[NileError.CALL_PREJOIN_FAILED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6901b[NileError.CALL_CONFIRMJOIN_FAILED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f6901b[NileError.CALL_CONNECTION_BROKEN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f6901b[NileError.UNKNOWN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[NileNetwork.Status.values().length];
            f6900a = iArr2;
            try {
                iArr2[NileNetwork.Status.RECONNECTING_TO_SERVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f6900a[NileNetwork.Status.JOINED.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f6900a[NileNetwork.Status.WAITINGROOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f6900a[NileNetwork.Status.START_STREAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f6900a[NileNetwork.Status.RESUME_STREAMING.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f6900a[NileNetwork.Status.NOT_JOINED.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f6900a[NileNetwork.Status.JOIN_NEED_MORE_INFO.ordinal()] = 7;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f6900a[NileNetwork.Status.VERSION_UNSYNC.ordinal()] = 8;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f6900a[NileNetwork.Status.MSERVER_REJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f6900a[NileNetwork.Status.REACH_LIMIT.ordinal()] = 10;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f6900a[NileNetwork.Status.MSERVER_DISCONNECTED.ordinal()] = 11;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f6900a[NileNetwork.Status.MSERVER_UNREACHABLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f6900a[NileNetwork.Status.EVENT_NOT_READY.ordinal()] = 13;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f6900a[NileNetwork.Status.EVENT_CLOSED.ordinal()] = 14;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f6900a[NileNetwork.Status.EVENT_ABNORMAL.ordinal()] = 15;
            } catch (NoSuchFieldError unused25) {
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$l */
    public class C1323l extends AbstractC6381r<Boolean, Void> {

        /* renamed from: c */
        public final /* synthetic */ boolean f6902c;

        public C1323l(boolean z8) {
            this.f6902c = z8;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Boolean bool) {
            if (bool.booleanValue()) {
                MeetingActivity.this.m6870uf(this.f6902c);
                MeetingActivity.this.m6739Wf(500L);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$l0 */
    public class RunnableC1324l0 implements Runnable {
        public RunnableC1324l0() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            if (MeetingActivity.this.f6617T1 == null) {
                return;
            }
            long jM4974x3 = MeetingActivity.this.f6617T1.m4974x3();
            if (MeetingActivity.this.f6588O2 == BreakoutRoomState.CHILD) {
                jM4974x3 = (System.currentTimeMillis() / 1000) - MeetingActivity.this.f6636W2;
            }
            if (jM4974x3 < 0) {
                return;
            }
            String strM20086b = C5157k.m20086b(jM4974x3);
            if (MeetingActivity.this.f6513C) {
                MeetingActivity.this.f6796u0.setText(strM20086b);
                MeetingActivity.this.f6592P0.setText(strM20086b);
                MeetingActivity.this.f6598Q0.setText(strM20086b);
                if (MeetingActivity.this.f6534F2) {
                    MeetingActivity.this.f6522D2.setText(strM20086b);
                }
            }
            long jM4972w3 = MeetingActivity.this.f6617T1.m4972w3();
            long jMax = Math.max(jM4972w3 - jM4974x3, 0L);
            TimeUnit timeUnit = TimeUnit.HOURS;
            boolean z8 = jM4972w3 < timeUnit.toSeconds(24L);
            if (jMax <= 0) {
                MeetingActivity.this.f6617T1.m4976y8(false, null);
                MeetingActivity.this.f6617T1.m4951l8(false, null);
                NotificationHelper.m14057A();
                if (z8 && Globals.m7388i0().m7591p()) {
                    MeetingActivity.this.m6751Yf();
                } else {
                    String str = (MeetingActivity.this.f6802v == null || C5170o0.m20170e(MeetingActivity.this.f6802v.displayName)) ? "the host" : MeetingActivity.this.f6802v.displayName;
                    if (jM4972w3 >= timeUnit.toSeconds(2L)) {
                        MeetingActivity meetingActivity = MeetingActivity.this;
                        meetingActivity.m6757Zf(null, meetingActivity.getString(R.string.clm_meeting_end_dialog_message_hour_with_host, str, meetingActivity.m6701Q9(jM4972w3)));
                    } else {
                        MeetingActivity meetingActivity2 = MeetingActivity.this;
                        meetingActivity2.m6757Zf(null, meetingActivity2.getString(R.string.clm_meeting_end_dialog_message_with_host, str, Long.valueOf(jM4972w3 / 60)));
                    }
                }
                MeetingActivity.this.m6809k9();
                return;
            }
            if (z8 && jMax <= MeetingActivity.this.f6611S1) {
                MeetingActivity.this.m6745Xf(jMax, jM4972w3);
            }
            NetQuality.Quality qualityM4473c = MeetingActivity.this.f6617T1.f4920f.m4473c();
            NetQuality.Quality qualityM4472b = MeetingActivity.this.f6617T1.f4920f.m4472b();
            NetQuality.Quality quality = NetQuality.Quality.UNAVAILABLE;
            if (qualityM4473c.compareTo(quality) != 0 && qualityM4472b.compareTo(quality) != 0) {
                NetQuality.Quality quality2 = NetQuality.Quality.ACCEPTABLE;
                if (qualityM4473c.compareTo(quality2) > 0 || qualityM4472b.compareTo(quality2) > 0) {
                    MeetingActivity.this.f6579N = true;
                } else {
                    MeetingActivity.this.m6846qa();
                    MeetingActivity.this.f6579N = false;
                }
            }
            MeetingActivity.this.m7366I0().postDelayed(this, 500L);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$l1 */
    public class C1325l1 extends AbstractC6381r<NileNetwork.DisplayMode, Void> {
        public C1325l1() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(NileNetwork.DisplayMode displayMode) {
            ULogUtility.m16683s("MeetingActivity", "[DesktopSharing] onActivityResult / REQUEST_MEDIA_PROJECTION - onComplete");
            C5187v0.m20268d("Start screen sharing: " + displayMode);
            MeetingActivity.this.f6559J3 = true;
            MeetingActivity.this.m6734Vg();
            MeetingActivity.this.f6547H3 = 0;
            MeetingActivity.this.m6674Lf(true, true);
            MeetingActivity.this.f6720j1.setImageResource(R.drawable.btn_share_screen_n);
            MeetingActivity.this.f6671c1.setText(R.string.clm_meeting_desktop_sharing_stop_button);
            MeetingActivity.this.f6684e0.setVisibility(8);
            MeetingActivity.this.m6636Fh();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$m */
    public class ViewOnClickListenerC1326m implements View.OnClickListener {
        public ViewOnClickListenerC1326m() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m6957e(Dialog dialog, View view) {
            dialog.dismiss();
            MeetingActivity.this.f6617T1.m4969u8(!MeetingActivity.this.f6722j3, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m6959g(Dialog dialog, View view) {
            dialog.dismiss();
            C0012b c0012bM6833o9 = MeetingActivity.this.m6833o9(ActiveMedia.Type.DESKTOP);
            if (c0012bM6833o9 != null) {
                c0012bM6833o9.m86e();
            }
            final Dialog dialogM16385d = C3123g.m16385d(MeetingActivity.this, "", MeetingActivity.this.getString(R.string.clm_speaker_desktop_sharing_warning, MeetingActivity.this.f6804v1.m86e()));
            TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
            textView.setText(MeetingActivity.this.getString(R.string.close));
            textView.setOnClickListener(new View.OnClickListener() { // from class: p2.l5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    dialogM16385d.dismiss();
                }
            });
            dialogM16385d.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m6960h(Dialog dialog, View view) {
            dialog.dismiss();
            MeetingActivity.this.m6887xf(!r2.f6559J3);
            boolean z8 = !MeetingActivity.this.f6559J3;
            if (z8) {
                MeetingActivity.this.m6674Lf(z8, false);
            } else {
                MeetingActivity.this.m6674Lf(z8, true);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MeetingActivity meetingActivity;
            int i9;
            final Dialog dialogM16384c = C3123g.m16384c(MeetingActivity.this);
            dialogM16384c.setContentView(R.layout.dialog_meeting_btn_more);
            TextView textView = (TextView) dialogM16384c.findViewById(R.id.item_raise_or_lower_hand);
            if (MeetingActivity.this.f6722j3) {
                meetingActivity = MeetingActivity.this;
                i9 = R.string.clm_meeting_lower_hand;
            } else {
                meetingActivity = MeetingActivity.this;
                i9 = R.string.clm_meeting_raise_hand;
            }
            textView.setText(meetingActivity.getString(i9));
            textView.setOnClickListener(new View.OnClickListener() { // from class: p2.i5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f20475b.m6957e(dialogM16384c, view2);
                }
            });
            TextView textView2 = (TextView) dialogM16384c.findViewById(R.id.item_share_desktop);
            if (MeetingActivity.this.f6778r3) {
                textView2.setVisibility(8);
            } else {
                MeetingActivity meetingActivity2 = MeetingActivity.this;
                meetingActivity2.f6559J3 = meetingActivity2.f6617T1.m4891F3();
                if (MeetingActivity.this.f6559J3) {
                    MeetingActivity.this.m6887xf(!r4.f6559J3);
                    return;
                } else {
                    textView2.setText(MeetingActivity.this.getString(R.string.clm_meeting_desktop_sharing_start_button));
                    if (MeetingActivity.this.m6835ob()) {
                        textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.j5
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                this.f20489b.m6959g(dialogM16384c, view2);
                            }
                        });
                    } else {
                        textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.k5
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                this.f20500b.m6960h(dialogM16384c, view2);
                            }
                        });
                    }
                }
            }
            dialogM16384c.show();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$m0 */
    public class RunnableC1327m0 implements Runnable {
        public RunnableC1327m0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.f6617T1 == null) {
                return;
            }
            long jMax = Math.max(MeetingActivity.this.f6642X2 - ((System.currentTimeMillis() / 1000) - MeetingActivity.this.f6636W2), 0L);
            if (jMax <= 0) {
                MeetingActivity.this.f6606R2.setText(String.valueOf(0));
                if (!MeetingActivity.this.f6660a3) {
                    MeetingActivity.this.f6660a3 = true;
                    int currentItem = MeetingActivity.this.f6603R.getCurrentItem();
                    if (currentItem == 1 || currentItem == MeetingActivity.this.m6671L9()) {
                        MeetingActivity.this.f6606R2.setVisibility(0);
                    }
                }
                if (!MeetingActivity.this.f6673c3) {
                    MeetingActivity.this.f6673c3 = true;
                    MeetingActivity.this.m6627Ef();
                }
            } else if (jMax <= 30) {
                if (!MeetingActivity.this.f6660a3) {
                    MeetingActivity.this.f6660a3 = true;
                    int currentItem2 = MeetingActivity.this.f6603R.getCurrentItem();
                    if (currentItem2 == 1 || currentItem2 == MeetingActivity.this.m6671L9()) {
                        MeetingActivity.this.f6606R2.setVisibility(0);
                    }
                }
                MeetingActivity.this.f6606R2.setText(String.valueOf(jMax));
                if (jMax <= 5) {
                    MeetingActivity.this.f6606R2.setBackgroundResource(R.drawable.img_timer_red_2x);
                }
            }
            MeetingActivity.this.m7366I0().postDelayed(this, 500L);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$m1 */
    public class RunnableC1328m1 implements Runnable {

        /* renamed from: b */
        public final Runnable f6908b = new Runnable() { // from class: p2.b6
            @Override // java.lang.Runnable
            public final void run() {
                this.f20373b.m6965e();
            }
        };

        public RunnableC1328m1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m6965e() {
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            MeetingActivity.this.m6798i9();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m6966f() {
            MeetingActivity.this.f6555J = false;
            this.f6908b.run();
            C5187v0.m20267c(R.string.permission_denied);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m6967g() {
            if (MimeTypes.BASE_TYPE_VIDEO.equals(MeetingActivity.this.f6732l)) {
                MeetingActivity.this.m6753Z8(Permission.CAMERA, this.f6908b, new Runnable() { // from class: p2.e6
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f20422b.m6966f();
                    }
                });
            } else {
                this.f6908b.run();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m6968h() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6834oa(meetingActivity.m6760aa(), "Microphone permission denied");
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            MeetingActivity.this.m6643Gh();
            MeetingActivity.this.m6753Z8(Permission.MICROPHONE, new Runnable() { // from class: p2.c6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20385b.m6967g();
                }
            }, new Runnable() { // from class: p2.d6
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                    this.f20403b.m6968h();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$n */
    public class C1329n extends AbstractC6381r<NileNetwork.DisplayMode, Void> {
        public C1329n() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(NileNetwork.DisplayMode displayMode) throws Resources.NotFoundException {
            ULogUtility.m16670f("MeetingActivity", "[stopDesktopSharing] onComplete / displayMode: " + displayMode);
            MeetingActivity.this.f6559J3 = false;
            MeetingActivity.this.m6674Lf(false, false);
            MeetingActivity.this.f6720j1.setImageResource(R.drawable.btn_meeting_more);
            MeetingActivity.this.f6671c1.setText(R.string.more_fragment);
            MeetingActivity.this.m6636Fh();
            if (MeetingActivity.this.f6603R.getCurrentItem() == MeetingActivity.this.m6671L9()) {
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.m6710Rg(meetingActivity.f6587O1 ? NileNetwork.DisplayMode.GALLERY : NileNetwork.DisplayMode.SPEAKER);
                MeetingActivity.this.m6820lh(false);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$n0 */
    public class C1330n0 extends AbstractC6381r<Boolean, Void> {
        public C1330n0() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: m, reason: merged with bridge method [inline-methods] */
        public void m24503g(Boolean bool) {
            if (bool.booleanValue()) {
                return;
            }
            MeetingActivity.this.m6658Ih();
            final Dialog dialogM16385d = C3123g.m16385d(MeetingActivity.this, "", MeetingActivity.this.getString(R.string.clm_meeting_your_microphone_has_not_turn_on));
            dialogM16385d.setCancelable(false);
            TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
            textView.setOnClickListener(new View.OnClickListener() { // from class: p2.s5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dialogM16385d.dismiss();
                }
            });
            textView.setText(R.string.ok);
            dialogM16385d.show();
            MeetingActivity.this.f6658a1.setSelected(false);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$n1 */
    public class C1331n1 extends PromisedTask.AbstractC3021b<Meeting> {
        public C1331n1() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            int iM5671h = C1260a.m5671h(i9, str);
            if (iM5671h != R.string.clm_error_pwd) {
                ULogUtility.m16676l("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] joinMeeting fail | errorCode : " + i9 + " errorBody : " + str);
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.m6757Zf(null, meetingActivity.getString(iM5671h));
                return;
            }
            ULogUtility.m16680p("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] joinMeeting need pwd | errorCode : " + i9 + " errorBody : " + str);
            Intent intent = new Intent(MeetingActivity.this, (Class<?>) ConfirmPwdActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("meetingId", MeetingActivity.this.f6711i);
            intent.putExtras(bundle);
            MeetingActivity.this.startActivityForResult(intent, 40001);
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            Log.i("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] joinMeeting result : " + meeting);
            MeetingActivity.this.f6711i = meeting.eventId;
            MeetingActivity.this.f6746n = meeting.shareAddr;
            MeetingActivity.this.f6753o = meeting.mserverAddr;
            MeetingActivity.this.f6739m = meeting.title;
            MeetingActivity.this.f6760p = meeting.token;
            Group groupM15077n = C2950b0.m14912k().m15077n(meeting.groupId);
            if (groupM15077n != null) {
                MeetingActivity.this.f6808w = groupM15077n;
            }
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.f6826z = meetingActivity.f6808w != null && MeetingActivity.this.f6808w.f13716c.equals("Dual");
            MeetingActivity meetingActivity2 = MeetingActivity.this;
            meetingActivity2.runOnUiThread(meetingActivity2.f6730k4);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$o */
    public class ViewOnClickListenerC1332o implements View.OnClickListener {
        public ViewOnClickListenerC1332o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MeetingActivity.this.m6686Nf(true);
            boolean z8 = !view.isSelected();
            view.setSelected(z8);
            MeetingActivity.this.f6543H = z8;
            if (MeetingActivity.this.f6623U1 != null) {
                ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] switch speaker: " + z8);
                MeetingActivity.this.f6623U1.m5066q(z8);
                if (z8) {
                    MeetingActivity.this.f6623U1.m5062m(RTCAudioManager.AudioDevice.SPEAKER_PHONE);
                } else {
                    MeetingActivity.this.f6623U1.m5062m(RTCAudioManager.AudioDevice.NONE);
                }
                MeetingActivity.this.f6623U1.m5070u();
                if (MeetingActivity.this.f6617T1 != null) {
                    MeetingActivity.this.f6617T1.m4904J7(null);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$o0 */
    public class RunnableC1333o0 implements Runnable {
        public RunnableC1333o0() {
        }

        @Override // java.lang.Runnable
        public void run() throws Resources.NotFoundException {
            if (MeetingActivity.this.f6778r3) {
                return;
            }
            MeetingActivity.this.m6783eh();
            if (MeetingActivity.this.f6804v1 == null) {
                MeetingActivity.this.m6840pa();
                return;
            }
            if (MeetingActivity.this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) > 1) {
                if (MeetingActivity.this.f6559J3 || !((!MeetingActivity.this.f6581N1 && MeetingActivity.this.f6804v1.m99r()) || MeetingActivity.this.m6777db() || MeetingActivity.this.m6829nb())) {
                    MeetingActivity.this.m6840pa();
                } else {
                    MeetingActivity.this.m6621Df();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$o1 */
    public class C1334o1 extends PromisedTask.AbstractC3021b<Meeting> {
        public C1334o1() {
        }

        @Override // com.cyberlink.you.feedback.PromisedTask
        /* renamed from: k */
        public void mo5702k(int i9, String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            ULogUtility.m16676l("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] createMeeting error | errorCode : " + i9 + " errorBody : " + str);
            if (i9 == 422) {
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.m6757Zf(null, meetingActivity.getString(R.string.not_support_version));
                return;
            }
            if (i9 != NetworkManager.NetworkErrorCode.E_BAD_REQUEST.m5658a() || MeetingActivity.this.f6659a2 >= 2) {
                MeetingActivity.this.m6757Zf(null, null);
                return;
            }
            MeetingActivity.m6081F7(MeetingActivity.this);
            ULogUtility.m16676l("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] Retry to connect Do server " + MeetingActivity.this.f6659a2 + " times");
            MeetingActivity.this.m6780e9();
        }

        @Override // com.cyberlink.you.feedback.PromisedTask.AbstractC3021b
        /* renamed from: r, reason: merged with bridge method [inline-methods] */
        public void mo5703q(Meeting meeting) {
            ULogUtility.m16680p("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] createMeeting done.");
            if (MeetingActivity.this.f6557J1) {
                return;
            }
            String str = MeetingActivity.this.f6711i;
            MeetingActivity.this.f6711i = meeting.eventId;
            MeetingActivity.this.f6746n = meeting.shareAddr;
            MeetingActivity.this.f6753o = meeting.mserverAddr;
            MeetingActivity.this.f6739m = meeting.title;
            MeetingActivity.this.f6760p = meeting.token;
            MeetingActivity.this.f6564K2 = meeting.voipInfo;
            MeetingActivity.this.f6811w2 = meeting.invitationOnly.isInvitationOnly.booleanValue();
            MeetingActivity.this.f6817x2 = meeting.hasPassword.booleanValue();
            MeetingManager.m5603C(str, MeetingActivity.this.f6711i);
            ULogUtility.m16683s("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] replaceMeeting oldMeetingId = " + str + " New meetingId = " + MeetingActivity.this.f6711i);
            MeetingActivity.this.m6877vh(MeetingEstablishStep.CALLER_DO_SERVER_CREATE_MEETING_COMPLETE);
            MeetingActivity.this.f6730k4.run();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$p */
    public class ViewOnClickListenerC1335p implements View.OnClickListener {

        /* renamed from: b */
        public boolean f6916b = false;

        /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$p$a */
        public class a extends AbstractC6381r<Boolean, Void> {

            /* renamed from: c */
            public final /* synthetic */ boolean f6918c;

            /* renamed from: d */
            public final /* synthetic */ View f6919d;

            public a(boolean z8, View view) {
                this.f6918c = z8;
                this.f6919d = view;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Boolean bool) {
                if (this.f6918c != bool.booleanValue()) {
                    Log.e("MeetingActivity", "switch camera enable to " + this.f6918c + " failed");
                    return;
                }
                Log.v("MeetingActivity", "switch camera enable to " + this.f6918c + " successfully");
                if (!MeetingActivity.this.m6755Za() && this.f6918c && !MeetingActivity.this.f6543H && MeetingActivity.this.f6729k3) {
                    MeetingActivity.this.f6646Y0.callOnClick();
                    C5187v0.m20267c(R.string.clm_speaker_on);
                }
                this.f6919d.setSelected(this.f6918c);
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.mo5119b(this.f6918c, meetingActivity.f6617T1.m4897H3());
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: l, reason: merged with bridge method [inline-methods] */
            public void m24504h(Void r12) {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        public ViewOnClickListenerC1335p() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m6977d(View view) {
            boolean z8 = !view.isSelected();
            if (m6979c(z8)) {
                return;
            }
            MeetingActivity.this.f6617T1.m4976y8(z8, new a(z8, view));
        }

        /* renamed from: c */
        public final boolean m6979c(boolean z8) {
            if (!C5315b.m20805u()) {
                return false;
            }
            if (z8) {
                this.f6916b = false;
                MeetingActivity.this.f6617T1.m4947j8(false, null);
                C5187v0.m20268d("Enable camera (H)");
                return false;
            }
            if (this.f6916b) {
                C5187v0.m20268d("Disable camera");
                return false;
            }
            this.f6916b = true;
            MeetingActivity.this.f6617T1.m4947j8(true, null);
            C5187v0.m20268d("Enable camera (L)");
            return true;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            MeetingActivity.this.m6686Nf(true);
            if (MeetingActivity.this.f6617T1 == null) {
                return;
            }
            if (MeetingActivity.this.m6777db()) {
                C5187v0.m20270f(R.string.clm_warning_unsupported_device_for_video_call);
            } else {
                MeetingActivity.this.m6753Z8(Permission.CAMERA, new Runnable() { // from class: p2.m5
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f20522b.m6977d(view);
                    }
                }, new Runnable() { // from class: p2.n5
                    @Override // java.lang.Runnable
                    public final void run() {
                        C5187v0.m20267c(R.string.permission_denied);
                    }
                });
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$p0 */
    public class C1336p0 implements ViewPager.InterfaceC0557j {

        /* renamed from: c */
        public boolean f6922c;

        /* renamed from: d */
        public int f6923d;

        /* renamed from: e */
        public int f6924e;

        /* renamed from: f */
        public float f6925f;

        /* renamed from: g */
        public float f6926g;

        /* renamed from: i */
        public float f6928i;

        /* renamed from: j */
        public float f6929j;

        /* renamed from: k */
        public float f6930k;

        /* renamed from: m */
        public float f6932m;

        /* renamed from: n */
        public float f6933n;

        /* renamed from: o */
        public float f6934o;

        /* renamed from: p */
        public float f6935p;

        /* renamed from: q */
        public float f6936q;

        /* renamed from: r */
        public float f6937r;

        /* renamed from: s */
        public float f6938s;

        /* renamed from: t */
        public boolean f6939t;

        /* renamed from: b */
        public final int f6921b = -1;

        /* renamed from: h */
        public float f6927h = BitmapDescriptorFactory.HUE_RED;

        /* renamed from: l */
        public int f6931l = -1;

        public C1336p0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m6986f() throws Resources.NotFoundException {
            MeetingActivity.this.m6820lh(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m6987g(boolean z8) {
            if (z8) {
                MeetingActivity.this.m6807jg();
            }
            if (MeetingActivity.this.f6581N1) {
                return;
            }
            MeetingActivity.this.f6684e0.setVisibility(MeetingActivity.this.m6900zf() ? 4 : 0);
            MeetingActivity.this.f6653Z1.setVisibility(MeetingActivity.this.m6603Af() ? 8 : 0);
        }

        /* renamed from: e */
        public final void m6990e() {
            float fM6897z9 = BitmapDescriptorFactory.HUE_RED;
            this.f6927h = BitmapDescriptorFactory.HUE_RED;
            MeetingActivity.this.f6614S4 = true;
            MeetingActivity.this.f6620T4 = true;
            int currentItem = MeetingActivity.this.f6603R.getCurrentItem();
            this.f6931l = MeetingActivity.this.f6603R.getCurrentItem();
            this.f6923d = -1;
            this.f6924e = -1;
            this.f6926g = MeetingActivity.this.f6621U.f7078a.getX();
            this.f6930k = MeetingActivity.this.f6684e0.getX() - this.f6926g;
            if (currentItem != 1 && currentItem != MeetingActivity.this.m6671L9()) {
                fM6897z9 = MeetingActivity.this.m6897z9();
            }
            this.f6932m = fM6897z9;
        }

        /* renamed from: j */
        public final void m6991j(int i9) {
            int i10;
            if (MeetingActivity.this.f6587O1 && (i10 = i9 - 1) < MeetingActivity.this.f6609S.getChildCount()) {
                int i11 = 0;
                while (i11 < MeetingActivity.this.f6609S.getChildCount()) {
                    MeetingActivity.this.f6609S.getChildAt(i11).setSelected(i11 == i10);
                    i11++;
                }
            }
        }

        /* renamed from: k */
        public final void m6992k(int i9) {
            MeetingActivity.this.f6608R4.run();
            MeetingActivity.this.m6623Dh();
            MeetingActivity.this.m6611Bh();
            MeetingActivity.this.m6902zh();
            MeetingActivity meetingActivity = MeetingActivity.this;
            meetingActivity.m6859sf(i9 == 1 || i9 == meetingActivity.m6671L9());
            MeetingActivity meetingActivity2 = MeetingActivity.this;
            meetingActivity2.f6696f5 = (i9 == 1 || i9 == meetingActivity2.m6671L9()) ? 0 : 4;
            MeetingActivity.this.m6814kh();
            MeetingActivity.this.m6636Fh();
            if (i9 == 1 || i9 == MeetingActivity.this.m6671L9()) {
                MeetingActivity.this.m6750Ye();
                if (!MeetingActivity.this.f6581N1) {
                    final boolean z8 = MeetingActivity.this.f6621U.f7080c.getVisibility() == 0;
                    MeetingActivity.this.m7366I0().postDelayed(new Runnable() { // from class: p2.u5
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f20623b.m6987g(z8);
                        }
                    }, 1000L);
                    MeetingActivity.this.m6891ya();
                }
                if (MeetingActivity.this.f6587O1) {
                    MeetingActivity.this.f6726k0.setVisibility(0);
                }
                if (MeetingActivity.this.f6588O2 == BreakoutRoomState.CHILD && MeetingActivity.this.f6660a3) {
                    MeetingActivity.this.f6606R2.setVisibility(0);
                }
            } else {
                MeetingActivity.this.f6684e0.setVisibility(4);
                MeetingActivity.this.f6653Z1.setVisibility(8);
                MeetingActivity.this.f6726k0.setVisibility(8);
                MeetingActivity.this.f6624U2.setVisibility(8);
                if (MeetingActivity.this.f6588O2 == BreakoutRoomState.CHILD) {
                    MeetingActivity.this.f6606R2.setVisibility(8);
                }
            }
            if (i9 == MeetingActivity.this.m6671L9()) {
                MeetingActivity meetingActivity3 = MeetingActivity.this;
                meetingActivity3.m6710Rg(meetingActivity3.f6559J3 ? NileNetwork.DisplayMode.DESKTOP : NileNetwork.DisplayMode.GALLERY);
                MeetingActivity.this.f6617T1.m4929a8(3);
            } else {
                MeetingActivity.this.m6868ua(i9);
                MeetingActivity.this.m6873va();
                if (!MeetingActivity.this.f6581N1) {
                    MeetingActivity meetingActivity4 = MeetingActivity.this;
                    meetingActivity4.m6710Rg(meetingActivity4.f6559J3 ? NileNetwork.DisplayMode.DESKTOP : NileNetwork.DisplayMode.SPEAKER);
                }
                if (i9 == 1 && MeetingActivity.this.f6581N1) {
                    MeetingActivity.this.f6617T1.m4929a8(2);
                    MeetingActivity.this.f6691f0.setVisibility(0);
                } else {
                    MeetingActivity.this.f6617T1.m4929a8(1);
                    MeetingActivity.this.f6691f0.setVisibility(8);
                }
            }
            if (MeetingActivity.this.f6734l1 != null) {
                MeetingActivity.this.f6734l1.setVisibility(((i9 == 1 || i9 == MeetingActivity.this.m6671L9()) && MeetingActivity.this.f6569L1 && !MeetingActivity.this.f6778r3) ? 0 : 8);
            }
            if (MeetingActivity.this.f6603R.getCurrentItem() == MeetingActivity.this.m6754Z9()) {
                C5325c c5325cM20917a = C5325c.m20917a();
                View view = MeetingActivity.this.f6532F0;
                MeetingActivity meetingActivity5 = MeetingActivity.this;
                c5325cM20917a.m20918b(view, meetingActivity5, meetingActivity5.f6638W4);
            } else {
                C5325c.m20917a().m20919c(MeetingActivity.this);
            }
            m6991j(i9);
            if (MeetingActivity.this.f6700g2) {
                if (i9 == 1) {
                    Handler handlerM7366I0 = MeetingActivity.this.m7366I0();
                    final MeetingActivity meetingActivity6 = MeetingActivity.this;
                    handlerM7366I0.postDelayed(new Runnable() { // from class: p2.v5
                        @Override // java.lang.Runnable
                        public final void run() {
                            MeetingActivity.m6196Q5(meetingActivity6);
                        }
                    }, 200L);
                } else {
                    Handler handlerM7366I02 = MeetingActivity.this.m7366I0();
                    final MeetingActivity meetingActivity7 = MeetingActivity.this;
                    handlerM7366I02.removeCallbacks(new Runnable() { // from class: p2.w5
                        @Override // java.lang.Runnable
                        public final void run() {
                            MeetingActivity.m6196Q5(meetingActivity7);
                        }
                    });
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrollStateChanged(int i9) throws Resources.NotFoundException {
            int currentItem = MeetingActivity.this.f6603R.getCurrentItem();
            if (i9 == 0) {
                this.f6922c = false;
                MeetingActivity.this.f6620T4 = false;
                this.f6927h = BitmapDescriptorFactory.HUE_RED;
                this.f6931l = -1;
                this.f6939t = true;
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.f6696f5 = meetingActivity.f6733l0.getAlpha() <= BitmapDescriptorFactory.HUE_RED ? 4 : 0;
                MeetingActivity.this.m6659J8(currentItem);
                if (MeetingActivity.this.f6626U4 != currentItem) {
                    MeetingActivity.this.m6652I8(currentItem);
                }
                if (currentItem == MeetingActivity.this.m6671L9()) {
                    MeetingActivity.this.m7366I0().postDelayed(new Runnable() { // from class: p2.t5
                        @Override // java.lang.Runnable
                        public final void run() throws Resources.NotFoundException {
                            this.f20612b.m6986f();
                        }
                    }, 100L);
                }
                MeetingActivity.this.f6626U4 = currentItem;
                return;
            }
            if (i9 != 1) {
                if (i9 != 2) {
                    return;
                }
                if (!this.f6922c) {
                    m6990e();
                    this.f6931l = MeetingActivity.this.f6626U4;
                    MeetingActivity.this.m6808jh();
                }
                MeetingActivity.this.f6614S4 = false;
                if (this.f6931l == currentItem) {
                    m6992k(currentItem);
                    return;
                }
                return;
            }
            if (MeetingActivity.this.f6620T4) {
                return;
            }
            this.f6922c = true;
            m6990e();
            if (currentItem == 1 || currentItem == MeetingActivity.this.m6671L9()) {
                MeetingActivity.this.f6621U.f7091n = MeetingActivity.this.f6621U.f7080c.getWidth() > MeetingActivity.this.f6621U.f7080c.getHeight() ? AspectRatio.LANDSCAPE : AspectRatio.PORTRAIT;
            } else if (currentItem == MeetingActivity.this.m6754Z9()) {
                MeetingActivity.this.f6514C0.setText(MeetingActivity.this.f6521D1.m12084z());
                CLUtility.m16589t1(MeetingActivity.this);
                MeetingActivity.this.m6768bg(false);
            }
            MeetingActivity.this.f6621U.m7140c();
            this.f6939t = false;
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageScrolled(int i9, float f9, int i10) {
            boolean z8 = MeetingActivity.this.f6620T4;
            float height = BitmapDescriptorFactory.HUE_RED;
            if (!z8 && f9 == BitmapDescriptorFactory.HUE_RED && i10 == 0) {
                return;
            }
            int currentItem = MeetingActivity.this.f6603R.getCurrentItem();
            float f10 = 1.0f;
            if (MeetingActivity.this.f6614S4) {
                if (i9 == currentItem) {
                    i9++;
                    this.f6925f = f9;
                } else {
                    this.f6925f = 1.0f - f9;
                }
                this.f6923d = i9;
                if (MeetingActivity.this.f6559J3) {
                    MeetingActivity.this.m6674Lf(false, true);
                }
                currentItem = i9;
            } else {
                float f11 = this.f6925f;
                if (!this.f6922c) {
                    this.f6923d = currentItem;
                }
                if (i9 == currentItem) {
                    if (this.f6931l > currentItem) {
                        this.f6925f = 1.0f - f9;
                    } else {
                        this.f6925f = f9;
                    }
                } else if (this.f6931l < currentItem) {
                    this.f6925f = f9;
                } else {
                    this.f6925f = 1.0f - f9;
                }
                this.f6924e = currentItem;
                if (f9 == BitmapDescriptorFactory.HUE_RED && i10 == 0) {
                    this.f6925f = this.f6931l == currentItem ? 0.0f : 1.0f;
                }
                if (Math.abs(this.f6931l - currentItem) > 1) {
                    this.f6925f = Math.max(f11, this.f6925f);
                }
                if (currentItem == MeetingActivity.this.m6784fa() && MeetingActivity.this.f6559J3) {
                    MeetingActivity.this.m6674Lf(true, true);
                }
            }
            boolean z9 = !MeetingActivity.this.f6614S4 ? !(MeetingActivity.this.f6587O1 && ((this.f6931l == 1 && this.f6924e == MeetingActivity.this.m6671L9()) || (this.f6931l == MeetingActivity.this.m6671L9() && this.f6924e == 1))) : !(MeetingActivity.this.f6587O1 && ((this.f6931l == 1 && this.f6923d == MeetingActivity.this.m6671L9()) || (this.f6931l == MeetingActivity.this.m6671L9() && this.f6923d == 1)));
            if (!this.f6939t) {
                this.f6939t = true;
                if (!z9) {
                    MeetingActivity.this.m7366I0().removeCallbacks(MeetingActivity.this.f6731k5);
                    MeetingActivity.this.m6717T8();
                    MeetingActivity.this.f6768q0.setVisibility(8);
                    MeetingActivity.this.f6508B0.setVisibility(8);
                }
            }
            if (this.f6931l != MeetingActivity.this.m6671L9() || currentItem == 1 || currentItem == MeetingActivity.this.m6671L9()) {
                float f12 = currentItem == 1 ? 1.0f : 0.0f;
                this.f6936q = f12;
                float f13 = this.f6931l == 1 ? 1.0f : 0.0f;
                float f14 = f12 - f13;
                this.f6937r = f14;
                this.f6938s = f13 + (f14 * this.f6925f);
                MeetingActivity.this.f6782s0.setAlpha(this.f6938s);
                MeetingActivity.this.f6789t0.setAlpha(this.f6938s);
            }
            int i11 = this.f6931l;
            boolean z10 = i11 == 1 || i11 == MeetingActivity.this.m6671L9();
            if (!z9 && this.f6922c) {
                this.f6936q = (currentItem == 1 || currentItem == MeetingActivity.this.m6671L9()) ? 1.0f : 0.0f;
                int i12 = this.f6931l;
                if (i12 != 1 && i12 != MeetingActivity.this.m6671L9()) {
                    f10 = 0.0f;
                }
                float f15 = this.f6936q - f10;
                this.f6937r = f15;
                this.f6938s = f10 + (f15 * this.f6925f);
                MeetingActivity.this.f6609S.setAlpha(this.f6938s);
                if (!z10 || MeetingActivity.this.f6733l0.getAlpha() != BitmapDescriptorFactory.HUE_RED) {
                    MeetingActivity.this.f6733l0.setAlpha(this.f6938s);
                    MeetingActivity.this.f6719j0.setAlpha(this.f6938s);
                    MeetingActivity.this.f6796u0.setAlpha(this.f6938s);
                    MeetingActivity.this.f6705h0.setAlpha(this.f6938s);
                    MeetingActivity.this.f6600Q2.setAlpha(this.f6938s);
                    MeetingActivity.this.f6628V0.setAlpha(this.f6938s);
                    MeetingActivity.this.f6634W0.setAlpha(this.f6938s);
                    float fM6897z9 = (currentItem == 1 || currentItem == MeetingActivity.this.m6671L9()) ? 0.0f : MeetingActivity.this.m6897z9();
                    this.f6933n = fM6897z9;
                    float f16 = this.f6932m;
                    float f17 = fM6897z9 - f16;
                    this.f6934o = f17;
                    this.f6935p = f16 + (f17 * this.f6925f);
                    MeetingActivity.this.f6796u0.setTranslationY(this.f6935p);
                    MeetingActivity.this.f6609S.setTranslationY(this.f6935p);
                    MeetingActivity.this.f6705h0.setTranslationY(this.f6935p);
                    MeetingActivity.this.f6628V0.setTranslationY(this.f6935p);
                    MeetingActivity.this.f6634W0.setTranslationY(this.f6935p);
                    if (currentItem != 1 && currentItem != MeetingActivity.this.m6671L9()) {
                        height = (MeetingActivity.this.f6733l0.getHeight() * (-1)) / 2;
                    }
                    this.f6933n = height;
                    float f18 = this.f6932m;
                    float f19 = height - f18;
                    this.f6934o = f19;
                    this.f6935p = f18 + (f19 * this.f6925f);
                    MeetingActivity.this.f6782s0.setTranslationY(this.f6935p);
                    MeetingActivity.this.f6789t0.setTranslationY(this.f6935p);
                    MeetingActivity.this.f6733l0.requestLayout();
                    MeetingActivity.this.f6719j0.requestLayout();
                    MeetingActivity.this.f6796u0.requestLayout();
                    MeetingActivity.this.f6705h0.requestLayout();
                    MeetingActivity.this.f6782s0.requestLayout();
                    MeetingActivity.this.f6789t0.requestLayout();
                    MeetingActivity.this.f6600Q2.requestLayout();
                    MeetingActivity.this.f6628V0.requestLayout();
                    MeetingActivity.this.f6634W0.requestLayout();
                }
            }
            float fM6742X9 = this.f6931l - this.f6923d < 0 ? this.f6926g - MeetingActivity.this.m6742X9() : this.f6926g + MeetingActivity.this.m6742X9();
            this.f6927h = fM6742X9;
            float f20 = this.f6926g;
            float f21 = fM6742X9 - f20;
            this.f6928i = f21;
            this.f6929j = f20 + (f21 * this.f6925f);
            MeetingActivity.this.f6621U.f7078a.getLayoutParams().width = MeetingActivity.this.m6631F9(this.f6931l);
            MeetingActivity.this.f6621U.f7078a.getLayoutParams().height = MeetingActivity.this.m6612C9(this.f6931l);
            MeetingActivity.this.f6621U.f7078a.setX(this.f6929j);
            MeetingActivity.this.f6621U.f7078a.requestLayout();
            MeetingActivity.this.f6684e0.setVisibility(4);
            MeetingActivity.this.f6653Z1.setVisibility(4);
            MeetingActivity.this.f6691f0.setVisibility(8);
        }

        @Override // androidx.viewpager.widget.ViewPager.InterfaceC0557j
        public void onPageSelected(int i9) {
            m6992k(i9);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$p1 */
    public class C1337p1 extends RecyclerView.AbstractC0446g<ViewOnClickListenerC1340q1> {
        public C1337p1() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(ViewOnClickListenerC1340q1 viewOnClickListenerC1340q1, int i9) {
            BreakoutRoom breakoutRoom = (BreakoutRoom) MeetingActivity.this.f6612S2.get(i9);
            TextView textView = viewOnClickListenerC1340q1.f6948c;
            if (textView != null) {
                textView.setText(breakoutRoom.f6327b);
            }
            Button button = viewOnClickListenerC1340q1.f6949d;
            if (button != null) {
                button.setText(String.valueOf(breakoutRoom.f6328c));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ViewOnClickListenerC1340q1 onCreateViewHolder(ViewGroup viewGroup, int i9) {
            return MeetingActivity.this.new ViewOnClickListenerC1340q1(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_recv_breakout_room, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AbstractC0446g
        public int getItemCount() {
            return MeetingActivity.this.f6612S2.size();
        }

        public /* synthetic */ C1337p1(MeetingActivity meetingActivity, C1320k c1320k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$q */
    public class ViewOnClickListenerC1338q implements View.OnClickListener {

        /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$q$a */
        public class a extends AbstractC6381r<Boolean, Void> {

            /* renamed from: c */
            public final /* synthetic */ boolean f6943c;

            /* renamed from: d */
            public final /* synthetic */ View f6944d;

            public a(boolean z8, View view) {
                this.f6943c = z8;
                this.f6944d = view;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Boolean bool) {
                if (this.f6943c != bool.booleanValue()) {
                    Log.e("MeetingActivity", "switch microphone enable to " + this.f6943c + " failed");
                    return;
                }
                Log.v("MeetingActivity", "switch microphone enable to " + this.f6943c + " successfully");
                this.f6944d.setSelected(this.f6943c);
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.mo5119b(meetingActivity.f6617T1.m4900I3(), this.f6943c);
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: l, reason: merged with bridge method [inline-methods] */
            public void m24504h(Void r12) {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        public ViewOnClickListenerC1338q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MeetingActivity.this.m6686Nf(true);
            if (MeetingActivity.this.f6617T1 == null) {
                return;
            }
            boolean z8 = !view.isSelected();
            MeetingActivity.this.f6617T1.m4951l8(z8, new a(z8, view));
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$q0 */
    public class C1339q0 implements C5325c.b {
        public C1339q0() {
        }

        @Override // p137m4.C5325c.b
        /* renamed from: a */
        public void mo6997a() {
        }

        @Override // p137m4.C5325c.b
        /* renamed from: b */
        public void mo6998b() {
            MeetingActivity.this.m6740Wg();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$q1 */
    public class ViewOnClickListenerC1340q1 extends RecyclerView.AbstractC0442c0 implements View.OnClickListener {

        /* renamed from: b */
        public ImageView f6947b;

        /* renamed from: c */
        public TextView f6948c;

        /* renamed from: d */
        public Button f6949d;

        public ViewOnClickListenerC1340q1(View view) {
            super(view);
            view.setOnClickListener(this);
            this.f6947b = (ImageView) view.findViewById(R.id.icon);
            this.f6948c = (TextView) view.findViewById(R.id.name);
            this.f6949d = (Button) view.findViewById(R.id.room_size);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition < 0 || adapterPosition >= MeetingActivity.this.f6612S2.size()) {
                ULogUtility.m16680p("MeetingActivity", "On click breakout room position " + adapterPosition + " out of bound!");
            }
            if (MeetingActivity.this.f6588O2 == BreakoutRoomState.PARENT) {
                MeetingManager.m5605E(MeetingActivity.this.f6655Z3);
                MeetingManager.m5604D(MeetingActivity.this.f6661a4);
            }
            Intent intent = new Intent(MeetingActivity.this, (Class<?>) BreakoutRoomTransitionActivity.class);
            intent.putExtra("password", MeetingActivity.this.f6718j);
            intent.putExtra("ltiToken", MeetingActivity.this.f6687e3);
            intent.putExtra("breakoutRoom", (Parcelable) MeetingActivity.this.f6612S2.get(adapterPosition));
            intent.putExtra("type", MeetingActivity.this.f6732l);
            intent.putExtra("breakoutRoomState", BreakoutRoomState.CHILD);
            intent.putExtra("isMicrophoneOn", MeetingActivity.this.f6549I);
            intent.putExtra("isCameraOn", MeetingActivity.this.f6555J);
            intent.putExtra("isSpeakerOn", MeetingActivity.this.f6543H);
            intent.putExtra("displayName", MeetingActivity.this.f6774r);
            intent.setFlags(1073741824);
            MeetingActivity.this.startActivity(intent);
            MeetingActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$r */
    public class ViewOnClickListenerC1341r implements View.OnClickListener {
        public ViewOnClickListenerC1341r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            Log.d("MeetingActivity", "onClick LeaveWithoutSaving");
            MeetingActivity.this.m6633Fe("hang up button");
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$r0 */
    public class CountDownTimerC1342r0 extends CountDownTimer {
        public CountDownTimerC1342r0(long j9, long j10) {
            super(j9, j10);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MeetingActivity.this.m6881wf();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j9) {
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$r1 */
    public static abstract class AbstractC1343r1 extends TelephonyCallback implements TelephonyCallback.CallStateListener {
        public AbstractC1343r1() {
        }

        public /* synthetic */ AbstractC1343r1(C1320k c1320k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$s */
    public class ViewOnClickListenerC1344s implements View.OnClickListener {
        public ViewOnClickListenerC1344s() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.d("MeetingActivity", "onClick Cancel");
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$s0 */
    public class C1345s0 extends AbstractC6381r<NileNetwork.DisplayMode, Void> {

        /* renamed from: c */
        public final /* synthetic */ NileNetwork.DisplayMode f6954c;

        /* renamed from: d */
        public final /* synthetic */ Object f6955d;

        public C1345s0(NileNetwork.DisplayMode displayMode, Object obj) {
            this.f6954c = displayMode;
            this.f6955d = obj;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(NileNetwork.DisplayMode displayMode) {
            ULogUtility.m16680p("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] switch displayMode to " + this.f6954c + " success.");
            synchronized (this.f6955d) {
                this.f6955d.notify();
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r32) {
            ULogUtility.m16676l("MeetingActivity", "[" + MeetingActivity.this.f6748n1 + "] switch displayMode to " + this.f6954c + " fail.");
            synchronized (this.f6955d) {
                this.f6955d.notify();
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$s1 */
    public class RunnableC1346s1 implements Runnable {

        /* renamed from: b */
        public float f6957b;

        /* renamed from: c */
        public float f6958c;

        public RunnableC1346s1(float f9, float f10) {
            this.f6957b = f9;
            this.f6958c = f10;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.f6621U.f7078a.getX() == this.f6957b && MeetingActivity.this.f6621U.f7078a.getY() == this.f6958c) {
                return;
            }
            ULogUtility.m16670f("MeetingActivity", "[CheckRemoteViewPanel0PosRunnable] run");
            MeetingActivity.this.f6621U.f7078a.setX(this.f6957b);
            MeetingActivity.this.f6621U.f7078a.setY(this.f6958c);
            MeetingActivity.this.f6621U.f7078a.requestLayout();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$t */
    public class ViewOnClickListenerC1347t implements View.OnClickListener {

        /* renamed from: b */
        public boolean f6960b = false;

        /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$t$a */
        public class a implements CameraVideoCapturer.CameraSwitchHandler {
            public a() {
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
            public void onCameraSwitchDone(boolean z8) {
                ViewOnClickListenerC1347t.this.f6960b = false;
                MeetingActivity.this.f6653Z1.setMirror(z8);
            }

            @Override // org.webrtc.CameraVideoCapturer.CameraSwitchHandler
            public void onCameraSwitchError(String str) {
                ViewOnClickListenerC1347t.this.f6960b = false;
            }
        }

        public ViewOnClickListenerC1347t() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MeetingActivity.this.m6686Nf(true);
            if (MeetingActivity.this.f6617T1 == null) {
                return;
            }
            if (MeetingActivity.this.m6777db()) {
                C5187v0.m20270f(R.string.clm_warning_unsupported_device_for_video_call);
            } else {
                if (this.f6960b) {
                    return;
                }
                this.f6960b = true;
                MeetingActivity.this.f6617T1.m4890E8(new a());
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$t0 */
    public class C1348t0 extends AbstractViewOnTouchListenerC5918e {
        public C1348t0(Context context) {
            super(context);
        }

        @Override // p164p2.AbstractViewOnTouchListenerC5918e
        /* renamed from: a */
        public boolean mo7002a(View view) {
            if (MeetingActivity.this.f6620T4 || MeetingActivity.this.f6656Z4) {
                return false;
            }
            if (view == MeetingActivity.this.f6621U.f7078a) {
                return (MeetingActivity.this.f6603R.getCurrentItem() == 1 || MeetingActivity.this.f6603R.getCurrentItem() == MeetingActivity.this.m6671L9()) ? false : true;
            }
            if (view == MeetingActivity.this.f6691f0) {
                return MeetingActivity.this.f6581N1;
            }
            return true;
        }

        @Override // p164p2.AbstractViewOnTouchListenerC5918e
        /* renamed from: c */
        public void mo7003c(View view, boolean z8) {
            if (z8) {
                MeetingActivity.this.m6734Vg();
            } else {
                MeetingActivity.this.m6863te(view, view.getWidth(), view.getHeight());
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$t1 */
    public class RunnableC1349t1 implements Runnable {

        /* renamed from: b */
        public float f6964b;

        /* renamed from: c */
        public float f6965c;

        public RunnableC1349t1(float f9, float f10) {
            this.f6964b = f9;
            this.f6965c = f10;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.f6627V.f7079b.getX() == this.f6964b && MeetingActivity.this.f6627V.f7079b.getY() == this.f6965c) {
                return;
            }
            ULogUtility.m16670f("MeetingActivity", "[CheckRemoteViewPanel1RenderPosRunnable] run");
            MeetingActivity.this.f6627V.f7079b.setX(this.f6964b);
            MeetingActivity.this.f6627V.f7079b.setY(this.f6965c);
            MeetingActivity.this.f6627V.f7079b.requestLayout();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$u */
    public class C1350u implements AdapterView.OnItemLongClickListener {
        public C1350u() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (MeetingActivity.this.f6677d0.m7041k(i9) != ParticipantsAdapter.ParticipantType.PARTICIPANT) {
                return false;
            }
            MeetingActivity.this.m6691Oe((C0012b) MeetingActivity.this.f6677d0.getItem(i9));
            return false;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$u0 */
    public class C1351u0 implements Animator.AnimatorListener {

        /* renamed from: a */
        public boolean f6968a = false;

        /* renamed from: b */
        public int f6969b;

        public C1351u0() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            MeetingActivity.this.f6656Z4 = false;
            this.f6968a = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MeetingActivity.this.f6656Z4 = false;
            if (this.f6968a) {
                return;
            }
            MeetingActivity.this.f6621U.f7080c.setVisibility((!MeetingActivity.this.f6559J3 || MeetingActivity.this.f6603R.getCurrentItem() == MeetingActivity.this.m6671L9()) ? this.f6969b : 8);
            MeetingActivity.this.m6617Ch();
            if (MeetingActivity.this.f6620T4 || MeetingActivity.this.f6603R.getCurrentItem() != MeetingActivity.this.m6754Z9()) {
                return;
            }
            MeetingActivity.this.m6866th();
            MeetingActivity.this.m6651Hh();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            MeetingActivity.this.f6656Z4 = true;
            this.f6968a = false;
            this.f6969b = MeetingActivity.this.f6621U.f7080c.getVisibility();
            MeetingActivity.this.f6621U.f7080c.setVisibility(4);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$u1 */
    public class RunnableC1352u1 implements Runnable {

        /* renamed from: b */
        public float f6971b;

        /* renamed from: c */
        public float f6972c;

        public RunnableC1352u1(float f9, float f10) {
            this.f6971b = f9;
            this.f6972c = f10;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MeetingActivity.this.f6633W.f7079b.getX() == this.f6971b && MeetingActivity.this.f6633W.f7079b.getY() == this.f6972c) {
                return;
            }
            ULogUtility.m16670f("MeetingActivity", "[CheckRemoteViewPanel2RenderPosRunnable] run");
            MeetingActivity.this.f6633W.f7079b.setX(this.f6971b);
            MeetingActivity.this.f6633W.f7079b.setY(this.f6972c);
            MeetingActivity.this.f6633W.f7079b.requestLayout();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$v */
    public class C1353v implements ParticipantsAdapter.InterfaceC1367c {
        public C1353v() {
        }

        @Override // com.cyberlink.meeting.page.p032m.ParticipantsAdapter.InterfaceC1367c
        /* renamed from: a */
        public void mo7004a(int i9) {
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.m4930b3(i9);
            }
        }

        @Override // com.cyberlink.meeting.page.p032m.ParticipantsAdapter.InterfaceC1367c
        /* renamed from: b */
        public void mo7005b(C0012b c0012b) {
            MeetingActivity.this.m6691Oe(c0012b);
        }

        @Override // com.cyberlink.meeting.page.p032m.ParticipantsAdapter.InterfaceC1367c
        /* renamed from: c */
        public void mo7006c(C0012b c0012b) {
            MeetingActivity.this.m6691Oe(c0012b);
        }

        @Override // com.cyberlink.meeting.page.p032m.ParticipantsAdapter.InterfaceC1367c
        /* renamed from: d */
        public void mo7007d() {
            MeetingActivity.this.m6682N8();
        }

        @Override // com.cyberlink.meeting.page.p032m.ParticipantsAdapter.InterfaceC1367c
        /* renamed from: e */
        public void mo7008e(int i9) {
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.m4914R7(new C0012b(new C0011a(i9, "", "0")), null);
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$v0 */
    public class C1354v0 extends AnimatorListenerAdapter {
        public C1354v0() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MeetingActivity.this.f6696f5 = 0;
            MeetingActivity.this.m6859sf(true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            MeetingActivity.this.f6696f5 = 1;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$v1 */
    public static class C1355v1 extends AbstractC6243a {

        /* renamed from: a */
        public final List<View> f6976a;

        public /* synthetic */ C1355v1(List list, C1320k c1320k) {
            this(list);
        }

        @Override // p189s0.AbstractC6243a
        public void destroyItem(ViewGroup viewGroup, int i9, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // p189s0.AbstractC6243a
        public int getCount() {
            return this.f6976a.size();
        }

        @Override // p189s0.AbstractC6243a
        public Object instantiateItem(ViewGroup viewGroup, int i9) {
            View view = this.f6976a.get(i9);
            viewGroup.addView(view);
            return view;
        }

        @Override // p189s0.AbstractC6243a
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public C1355v1(List<View> list) {
            this.f6976a = list;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$w */
    public class C1356w extends GestureDetector.SimpleOnGestureListener {
        public C1356w() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (f10 < BitmapDescriptorFactory.HUE_RED) {
                CLUtility.m16589t1(MeetingActivity.this);
            }
            return super.onScroll(motionEvent, motionEvent2, f9, f10);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            MeetingActivity.this.m6770c9();
            return false;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$w0 */
    public class C1357w0 extends AnimatorListenerAdapter {
        public C1357w0() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            MeetingActivity.this.f6696f5 = 4;
            MeetingActivity.this.m6859sf(false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            MeetingActivity.this.f6696f5 = 3;
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$w1 */
    public class C1358w1 extends BroadcastReceiver {
        public C1358w1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
            if (MeetingActivity.this.f6557J1 || MeetingActivity.this.f6513C || MeetingActivity.this.f6623U1 == null || MeetingActivity.this.f6820y == null) {
                return;
            }
            if (MeetingActivity.this.f6623U1.m5055f() == 2 || MeetingActivity.this.f6820y == InviteCallType.CALLER || MeetingActivity.this.m6805jb()) {
                MeetingActivity.this.m6699Pg();
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.m6714Se(meetingActivity.f6820y == InviteCallType.CALLER);
            } else if (MeetingActivity.this.f6623U1.m5055f() == 1) {
                MeetingActivity.this.m6681Mg();
                MeetingActivity.this.m6635Fg();
            } else if (MeetingActivity.this.f6623U1.m5055f() == 0) {
                MeetingActivity.this.m6681Mg();
            }
        }

        public /* synthetic */ C1358w1(MeetingActivity meetingActivity, C1320k c1320k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$x */
    public class C1359x implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f6980a = true;

        public C1359x() {
        }

        /* renamed from: a */
        public final void m7010a(AbsListView absListView) {
            if (MeetingActivity.this.f6551I1) {
                this.f6980a = absListView.getLastVisiblePosition() >= absListView.getCount() - 1;
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            m7010a(absListView);
            if (absListView.getLastVisiblePosition() == i11 - 1 && MeetingActivity.this.f6622U0.getVisibility() == 0) {
                MeetingActivity.this.f6622U0.startAnimation(AnimationUtils.loadAnimation(MeetingActivity.this, R.anim.exit_to_bottom));
                MeetingActivity.this.f6622U0.setVisibility(4);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                MeetingActivity.this.f6551I1 = false;
            } else if (i9 == 1) {
                MeetingActivity.this.f6551I1 = true;
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$x0 */
    public class ViewOnTouchListenerC1360x0 implements View.OnTouchListener {
        public ViewOnTouchListenerC1360x0() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (MeetingActivity.this.f6641X1 || MeetingActivity.this.f6538G0 == null) {
                return false;
            }
            return MeetingActivity.this.f6538G0.m18406a(motionEvent);
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$y */
    public class C1361y implements C2027b.i {
        public C1361y() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.cyberlink.you.activity.chatdialog.C2027b.i
        /* renamed from: a */
        public void mo7011a(String str, Map<String, Object> map) {
            str.hashCode();
            int iIntValue = 0;
            char c9 = 65535;
            switch (str.hashCode()) {
                case -1765631230:
                    if (str.equals("selfDestruct")) {
                        c9 = 0;
                        break;
                    }
                    break;
                case 12514186:
                    if (str.equals("sendPhoto")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 18074067:
                    if (str.equals("sendVideo")) {
                        c9 = 2;
                        break;
                    }
                    break;
                case 1247032612:
                    if (str.equals("sendFile")) {
                        c9 = 3;
                        break;
                    }
                    break;
                case 1247446229:
                    if (str.equals("sendText")) {
                        c9 = 4;
                        break;
                    }
                    break;
            }
            Date date = null;
            switch (c9) {
                case 0:
                case 1:
                case 4:
                    if (!map.containsKey(MimeTypes.BASE_TYPE_TEXT)) {
                        if (map.containsKey("importImages")) {
                            ArrayList arrayList = (ArrayList) map.get("importImages");
                            if (map.containsKey(Constants.FirelogAnalytics.PARAM_TTL)) {
                                iIntValue = ((Integer) map.get(Constants.FirelogAnalytics.PARAM_TTL)).intValue();
                            } else if (map.containsKey("scheduleTime")) {
                                date = (Date) map.get("scheduleTime");
                            }
                            MeetingActivity.this.m6842pf(arrayList, iIntValue, date);
                            break;
                        }
                    } else if (MeetingActivity.this.f6525E) {
                        MeetingActivity.this.m6836of((String) map.get(MimeTypes.BASE_TYPE_TEXT));
                        break;
                    }
                    break;
                case 2:
                    MeetingActivity.this.m6853rf((VideoItem) map.get("videoItem"), 0, null);
                    break;
                case 3:
                    MeetingActivity.this.m6818lf((Uri) map.get("filePath"));
                    break;
            }
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$y0 */
    public class C1362y0 implements RTCAudioManager.InterfaceC1108b {
        public C1362y0() {
        }

        @Override // com.cyberlink.clrtc.rtc.RTCAudioManager.InterfaceC1108b
        /* renamed from: a */
        public void mo5071a(RTCAudioManager.AudioDevice audioDevice, Set<RTCAudioManager.AudioDevice> set) {
            MeetingActivity.this.m6626Ee(audioDevice, set);
        }

        @Override // com.cyberlink.clrtc.rtc.RTCAudioManager.InterfaceC1108b
        /* renamed from: b */
        public void mo5072b() {
            MeetingActivity.this.m6732Ve();
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$z */
    public class C1363z implements CLUtility.InterfaceC3137d {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f6985a;

        public C1363z(DialogC3133q dialogC3133q) {
            this.f6985a = dialogC3133q;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m7013d(DialogC3133q dialogC3133q, Uri uri) {
            dialogC3133q.dismiss();
            MeetingActivity.this.m6824mf(new FileItem(null, new File(uri.getPath()).getPath(), uri.toString(), true));
        }

        @Override // com.cyberlink.you.utility.CLUtility.InterfaceC3137d
        /* renamed from: a */
        public void mo7014a() {
            this.f6985a.dismiss();
            C5187v0.m20268d(MeetingActivity.this.getResources().getQuantityString(R.plurals.file_type_not_supported, 1));
        }

        @Override // com.cyberlink.you.utility.CLUtility.InterfaceC3137d
        /* renamed from: b */
        public void mo7015b(final Uri uri) {
            MeetingActivity meetingActivity = MeetingActivity.this;
            final DialogC3133q dialogC3133q = this.f6985a;
            meetingActivity.runOnUiThread(new Runnable() { // from class: p2.p5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20572b.m7013d(dialogC3133q, uri);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.meeting.page.m.MeetingActivity$z0 */
    public class C1364z0 implements RendererCommon.RendererEvents {
        public C1364z0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m7018c() {
            MeetingActivity.this.f6621U.f7080c.setVisibility(MeetingActivity.this.f6559J3 ? 8 : 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m7019d(AspectRatio aspectRatio, AtomicBoolean atomicBoolean) throws Resources.NotFoundException {
            if (aspectRatio == MeetingActivity.this.f6621U.f7091n) {
                synchronized (atomicBoolean) {
                    atomicBoolean.set(false);
                    atomicBoolean.notify();
                }
                return;
            }
            MeetingActivity.this.f6621U.f7091n = aspectRatio;
            MeetingActivity.this.m6800if();
            if (MeetingActivity.this.f6603R.getCurrentItem() == 1) {
                MeetingActivity meetingActivity = MeetingActivity.this;
                meetingActivity.m6652I8(meetingActivity.f6603R.getCurrentItem());
            } else if (MeetingActivity.this.f6603R.getCurrentItem() == MeetingActivity.this.m6671L9()) {
                MeetingActivity meetingActivity2 = MeetingActivity.this;
                meetingActivity2.m6795hf(meetingActivity2.f6621U, false);
            }
            MeetingActivity.this.f6621U.f7080c.setVisibility(8);
            synchronized (atomicBoolean) {
                atomicBoolean.set(false);
                atomicBoolean.notify();
            }
            MeetingActivity.this.m7366I0().post(new Runnable() { // from class: p2.x5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20663b.m7018c();
                }
            });
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int i9, int i10, int i11) {
            if (MeetingActivity.this.f6617T1 != null) {
                MeetingActivity.this.f6617T1.f4919e.m4467t("MeetingActivity", "resolution: " + i9 + "x" + i10 + ", " + i11);
            }
            boolean z8 = i11 == 90 || i11 == 270;
            int i12 = z8 ? i10 : i9;
            if (!z8) {
                i9 = i10;
            }
            final AspectRatio aspectRatio = i12 < i9 ? AspectRatio.PORTRAIT : AspectRatio.LANDSCAPE;
            final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            MeetingActivity.this.m7366I0().post(new Runnable() { // from class: com.cyberlink.meeting.page.m.d
                @Override // java.lang.Runnable
                public final void run() throws Resources.NotFoundException {
                    this.f7059b.m7019d(aspectRatio, atomicBoolean);
                }
            });
            try {
                synchronized (atomicBoolean) {
                    if (atomicBoolean.get()) {
                        atomicBoolean.wait(50L);
                    }
                }
            } catch (InterruptedException e9) {
                Log.e("MeetingActivity", "syncLock interrupt " + e9);
            }
        }
    }

    public MeetingActivity() {
        int i9 = Build.VERSION.SDK_INT;
        this.f6772q4 = i9 >= 31 ? new C1299d() : null;
        this.f6779r4 = i9 < 31 ? new C1302e() : null;
        this.f6786s4 = new AudioManager.OnAudioFocusChangeListener() { // from class: p2.s1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i10) {
                MeetingActivity.m6073Ec(i10);
            }
        };
        this.f6793t4 = new View.OnClickListener() { // from class: p2.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20605b.m6083Fc(view);
            }
        };
        this.f6800u4 = new ViewOnClickListenerC1326m();
        this.f6807v4 = new ViewOnClickListenerC1332o();
        this.f6813w4 = new ViewOnClickListenerC1335p();
        this.f6819x4 = new ViewOnClickListenerC1338q();
        this.f6825y4 = new View.OnClickListener() { // from class: p2.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20615b.m6093Gc(view);
            }
        };
        this.f6831z4 = new ViewOnClickListenerC1347t();
        this.f6506A4 = new C1350u();
        this.f6512B4 = new C1359x();
        this.f6518C4 = new C1361y();
        this.f6524D4 = new ViewOnClickListenerC1291a0();
        this.f6530E4 = new Runnable() { // from class: p2.v1
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                this.f20628b.m6102Hc();
            }
        };
        this.f6536F4 = false;
        this.f6554I4 = new C1315i0();
        this.f6560J4 = new C1318j0();
        this.f6566K4 = new RunnableC1321k0();
        this.f6572L4 = false;
        this.f6578M4 = new RunnableC1324l0();
        this.f6584N4 = new RunnableC1327m0();
        this.f6590O4 = new Runnable() { // from class: p2.w1
            @Override // java.lang.Runnable
            public final void run() {
                this.f20638b.m6738We();
            }
        };
        this.f6608R4 = new RunnableC1333o0();
        this.f6614S4 = false;
        this.f6620T4 = false;
        this.f6626U4 = 1;
        this.f6632V4 = new C1336p0();
        this.f6638W4 = new C1339q0();
        this.f6650Y4 = new Object();
        this.f6662a5 = new C1351u0();
        this.f6668b5 = 0;
        this.f6675c5 = 1;
        this.f6682d5 = 3;
        this.f6689e5 = 4;
        this.f6696f5 = 0;
        this.f6703g5 = new DecelerateInterpolator();
        this.f6710h5 = new AccelerateInterpolator();
        this.f6717i5 = 500L;
        this.f6724j5 = 3000L;
        this.f6731k5 = new Runnable() { // from class: p2.s
            @Override // java.lang.Runnable
            public final void run() {
                this.f20594b.m6543uc();
            }
        };
        this.f6738l5 = new C1354v0();
        this.f6745m5 = new C1357w0();
        this.f6752n5 = new ViewOnTouchListenerC1360x0();
        this.f6759o5 = new C1364z0();
        this.f6766p5 = new RunnableC1292a1();
        this.f6773q5 = new EglRenderer.FrameListener() { // from class: p2.d0
            @Override // org.webrtc.EglRenderer.FrameListener
            public final void onFrame(Bitmap bitmap) {
                this.f20395a.m6555vc(bitmap);
            }
        };
        this.f6780r5 = new C1304e1();
        this.f6787s5 = new C1307f1();
        this.f6794t5 = new C1310g1();
        this.f6801u5 = new View.OnClickListener() { // from class: p2.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20539b.m6040Bc(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ab */
    public /* synthetic */ void m6028Ab(C0012b c0012b, DialogInterface dialogInterface, int i9) {
        m6839p9("receive terminate by : " + c0012b.m100s());
    }

    /* renamed from: Ac */
    public static /* synthetic */ void m6029Ac(Dialog dialog, View view) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ad */
    public /* synthetic */ void m6030Ad(Dialog dialog, View view) {
        dialog.dismiss();
        this.f6600Q2.callOnClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Bb */
    public /* synthetic */ void m6039Bb() throws JSONException {
        if (!this.f6830z3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", RollCallEvent.STOP.name());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            this.f6589O3.add(jSONObject);
            return;
        }
        Dialog dialog = this.f6505A3;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f6505A3.dismiss();
        m6825mg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Bc */
    public /* synthetic */ void m6040Bc(View view) {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_reject_message);
        dialogM16384c.findViewById(R.id.rejectMessageCanNotTalk).setOnClickListener(new View.OnClickListener() { // from class: p2.y2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f20668b.m6566wc(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.rejectMessageInMeeting).setOnClickListener(new View.OnClickListener() { // from class: p2.z2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f20677b.m6577xc(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.rejectMessageCallLater).setOnClickListener(new View.OnClickListener() { // from class: p2.b3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f20369b.m6587yc(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.rejectMessageUrgent).setOnClickListener(new View.OnClickListener() { // from class: p2.c3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f20380b.m6598zc(dialogM16384c, view2);
            }
        });
        dialogM16384c.findViewById(R.id.rejectMessageCancel).setOnClickListener(new View.OnClickListener() { // from class: p2.d3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MeetingActivity.m6029Ac(dialogM16384c, view2);
            }
        });
        dialogM16384c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Bd */
    public /* synthetic */ void m6041Bd(Dialog dialog, View view) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        dialog.dismiss();
        m6633Fe("hang up button");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Cb */
    public /* synthetic */ void m6050Cb() throws Resources.NotFoundException {
        m6652I8(this.f6603R.getCurrentItem());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Cc */
    public /* synthetic */ void m6051Cc() {
        if (m7367J0()) {
            return;
        }
        m6839p9("Auto-Hangup");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Cd */
    public /* synthetic */ void m6052Cd(View view) {
        this.f6708h3.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Db */
    public /* synthetic */ void m6061Db(int i9) {
        if ((i9 & 4) != 0 || this.f6625U3.getVisibility() == 0) {
            return;
        }
        this.f6701g3 = true;
        if (this.f6581N1 && getResources().getConfiguration().orientation == 2) {
            m6741X8(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Dc */
    public /* synthetic */ boolean m6062Dc(C2904l c2904l, Map map) {
        String str = (String) map.get("eventType");
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "][EventListener onReceive] eventType = " + str);
        if (this.f6557J1) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "][EventListener] not hand xmpp event if meeting is hang up");
            return false;
        }
        str.hashCode();
        if (str.equals("meeting.meeting.end")) {
            return m6816la(map);
        }
        if (str.equals("client.rtc.hangup")) {
            return m6804ja(map);
        }
        ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "][EventListener] unknown event type = " + str + ", skip it.");
        return false;
    }

    /* renamed from: Dd */
    public static /* synthetic */ void m6063Dd(Runnable runnable, DialogInterface dialogInterface, int i9) {
        f6495C5 = true;
        runnable.run();
    }

    /* renamed from: Ec */
    public static /* synthetic */ void m6073Ec(int i9) {
        Log.d("MeetingActivity", "onAudioFocusChange: " + (i9 != -3 ? i9 != -2 ? i9 != -1 ? i9 != 1 ? i9 != 2 ? i9 != 3 ? i9 != 4 ? "AUDIOFOCUS_INVALID" : "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE" : "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK" : "AUDIOFOCUS_GAIN_TRANSIENT" : "AUDIOFOCUS_GAIN" : "AUDIOFOCUS_LOSS" : "AUDIOFOCUS_LOSS_TRANSIENT" : "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ed */
    public /* synthetic */ void m6074Ed(String str, DialogInterface dialogInterface, int i9) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        m6827n9(str);
    }

    /* renamed from: F7 */
    public static /* synthetic */ int m6081F7(MeetingActivity meetingActivity) {
        int i9 = meetingActivity.f6659a2;
        meetingActivity.f6659a2 = i9 + 1;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Fc */
    public /* synthetic */ void m6083Fc(View view) {
        boolean z8 = !view.isSelected();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4915S7(z8, new C1323l(z8));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Fd */
    public /* synthetic */ void m6084Fd(DialogInterface dialogInterface, int i9) {
        finish();
    }

    /* renamed from: Gb */
    public static /* synthetic */ void m6092Gb(String str, boolean z8, Group group, boolean z9, String str2, String str3, boolean z10, String str4, String str5, Activity activity) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "create");
        String strM6519s9 = m6519s9();
        if (str != null) {
            bundle.putString("type", str);
        }
        bundle.putString("meetingId", strM6519s9);
        if (z8) {
            if (group != null) {
                bundle.putParcelable("group", group);
            }
            bundle.putSerializable("inviteCallType", InviteCallType.CALLER);
        }
        bundle.putBoolean("isFromPhoneLine", z9);
        bundle.putString("callNumber", str2);
        bundle.putString("callDisplayName", str3);
        bundle.putBoolean("isFromContact", z10);
        bundle.putString("callUserId", str4);
        ULogUtility.m16683s("MeetingActivity", "start meeting | from = " + str5);
        Intent intent = new Intent(activity, (Class<?>) MeetingActivity.class);
        intent.putExtras(bundle);
        MeetingManager.m5630w(strM6519s9, MeetingManager.MeetingStatus.START_ACTIVITY);
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Gc */
    public /* synthetic */ void m6093Gc(View view) {
        if (this.f6588O2 == BreakoutRoomState.CHILD) {
            m6649Hf();
        } else {
            Log.d("MeetingActivity", "hangupOnClickListener");
            m6837og(new ViewOnClickListenerC1341r(), new ViewOnClickListenerC1344s());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Gd */
    public /* synthetic */ void m6094Gd(Dialog dialog, View view) {
        dialog.dismiss();
        CLUtility.m16477P1(this);
        m6839p9("force leave meeting dialog - version too old");
    }

    /* renamed from: Hb */
    public static /* synthetic */ void m6101Hb(MessageObj messageObj, Activity activity, String str, Runnable runnable) {
        if (messageObj != null) {
            m6313ag(activity, messageObj.m14747I("callId"), messageObj.m14747I("callType"), str);
        } else {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Hc */
    public /* synthetic */ void m6102Hc() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (this.f6557J1) {
            return;
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] wait phone line connect overtime");
        m6757Zf(null, getString(R.string.unable_to_reach_phoneline));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Hd */
    public /* synthetic */ void m6103Hd(Dialog dialog, View view) {
        dialog.dismiss();
        m6839p9("force leave meeting dialog");
    }

    /* renamed from: Ib */
    public static /* synthetic */ void m6111Ib(Group group, final Activity activity, final String str, final Runnable runnable) {
        final MessageObj messageObjM6357ea = m6357ea(group);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: p2.f0
            @Override // java.lang.Runnable
            public final void run() {
                MeetingActivity.m6101Hb(messageObjM6357ea, activity, str, runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ic */
    public /* synthetic */ void m6112Ic(Configuration configuration) {
        if (this.f6581N1 && configuration.orientation == 2) {
            m6741X8(false);
        } else {
            m6741X8(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Id */
    public /* synthetic */ void m6113Id(Dialog dialog, View view) {
        dialog.dismiss();
        m6839p9("force leave meeting dialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Jb */
    public /* synthetic */ void m6121Jb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4953m8(true, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Jc */
    public /* synthetic */ void m6122Jc(C5926f c5926f) {
        C2973l0 c2973l0M23392n = c5926f.m23392n();
        C6468p.m24787m().m24798v(this, c2973l0M23392n.m15148t().f13200d, c2973l0M23392n.m15145q(), C1199a.m5277a(c2973l0M23392n.m15148t().f13204h, c2973l0M23392n.m15148t().f13205i), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Jd */
    public /* synthetic */ void m6123Jd(View view) {
        this.f6666b3.dismiss();
        this.f6600Q2.setVisibility(0);
        int currentItem = this.f6603R.getCurrentItem();
        if (currentItem == 1 || currentItem == m6671L9()) {
            m6686Nf(false);
        }
    }

    /* renamed from: Jf */
    public static boolean m6124Jf(Activity activity, Group group, final Runnable runnable) {
        if (f6495C5 || group == null || "Dual".equals(group.f13716c)) {
            return false;
        }
        int i9 = (int) group.f13728o;
        C3123g.m16382a(activity).setMessage(activity.getString(R.string.clm_confirm_group_call_text, Integer.valueOf(i9), group.f13717d)).setPositiveButton(R.string.cancel_text, (DialogInterface.OnClickListener) null).setNegativeButton(activity.getString(R.string.clm_confirm_group_call_button, Integer.valueOf(i9)), new DialogInterface.OnClickListener() { // from class: p2.t0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                MeetingActivity.m6063Dd(runnable, dialogInterface, i10);
            }
        }).create().show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Kb */
    public /* synthetic */ void m6133Kb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4953m8(false, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Kc */
    public /* synthetic */ void m6134Kc(TelephonyManager telephonyManager) {
        telephonyManager.registerTelephonyCallback(getMainExecutor(), this.f6772q4);
        this.f6765p4 = true;
        m6789ga();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Kd */
    public /* synthetic */ void m6135Kd(View view) {
        this.f6666b3.dismiss();
        this.f6600Q2.callOnClick();
        this.f6600Q2.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Lb */
    public /* synthetic */ void m6144Lb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4933c8(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Lc */
    public /* synthetic */ void m6145Lc() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        m6834oa(m6760aa(), "Phone state permission denied");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ld */
    public /* synthetic */ void m6146Ld(Dialog dialog, View view) {
        dialog.dismiss();
        startActivity(new Intent(this, (Class<?>) UpgradeToProUserActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Mb */
    public /* synthetic */ void m6155Mb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4933c8(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Mc */
    public /* synthetic */ void m6156Mc(final TelephonyManager telephonyManager) {
        if (this.f6765p4) {
            return;
        }
        m6753Z8(Permission.PHONE_STATE, new Runnable() { // from class: p2.j4
            @Override // java.lang.Runnable
            public final void run() {
                this.f20487b.m6134Kc(telephonyManager);
            }
        }, new Runnable() { // from class: p2.k4
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                this.f20499b.m6145Lc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Md */
    public /* synthetic */ void m6157Md(Dialog dialog, View view) {
        dialog.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Nb */
    public /* synthetic */ void m6166Nb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4949k8();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Nc */
    public /* synthetic */ void m6167Nc() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        m6834oa(m6760aa(), "BLUE TOOTH (BLE) permission denied");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ob */
    public /* synthetic */ void m6177Ob(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4931b8(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Oc */
    public /* synthetic */ void m6178Oc() {
        this.f6623U1.m5066q(this.f6543H);
    }

    /* renamed from: Od */
    public static /* synthetic */ void m6179Od(Runnable runnable, DialogInterface dialogInterface, int i9) {
        f6495C5 = false;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Pb */
    public /* synthetic */ void m6188Pb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4931b8(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Pc */
    public /* synthetic */ void m6189Pc(C5926f c5926f) {
        m6662Je(c5926f.m23379a(), c5926f.m23383e());
    }

    /* renamed from: Q5 */
    public static /* synthetic */ void m6196Q5(MeetingActivity meetingActivity) {
        meetingActivity.m6628Eg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Qb */
    public /* synthetic */ void m6199Qb(Dialog dialog, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null && this.f6618T2 && this.f6581N1) {
            nileNetwork.m4888D8();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Qc */
    public /* synthetic */ void m6200Qc(C5926f c5926f) {
        Intent intent = new Intent(this, (Class<?>) VideoPlaybackActivity.class);
        intent.putExtra("video_playback_url", c5926f.m23387i());
        intent.putExtra("mediaId", c5926f.m23383e());
        intent.putExtra("videoMessage", false);
        intent.putExtra("isOpenFromMeeting", true);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Qd */
    public /* synthetic */ void m6201Qd(View view) {
        this.f6812w3.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Rb */
    public /* synthetic */ void m6210Rb(String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        m6810ka(str, "xmpp - hang up event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Rd */
    public /* synthetic */ void m6212Rd(DialogInterface dialogInterface, int i9) {
        this.f6548H4.dismiss();
    }

    /* renamed from: Rf */
    public static boolean m6213Rf(Activity activity) {
        if (!MeetingManager.m5619l()) {
            return false;
        }
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            C3123g.m16382a(activity).setMessage(R.string.clm_error_other_meeting_in_progress).setPositiveButton(activity.getString(R.string.ok), (DialogInterface.OnClickListener) null).setCancelable(false).create().show();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Sb */
    public /* synthetic */ void m6222Sb() {
        m6839p9("xmpp - meeting end event");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Sc */
    public /* synthetic */ void m6223Sc(DialogInterface dialogInterface, int i9) {
        CLUtility.m16477P1(this);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Sd */
    public /* synthetic */ void m6224Sd(View view) {
        this.f6750n3.setVisibility(8);
        this.f6603R.setCurrentItem(0);
    }

    /* renamed from: T4 */
    public static /* synthetic */ int m6229T4(MeetingActivity meetingActivity) {
        int i9 = meetingActivity.f6792t3;
        meetingActivity.f6792t3 = i9 - 1;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Tb */
    public /* synthetic */ void m6233Tb(String str) {
        this.f6783s1.m5687A(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6725k, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Tc */
    public /* synthetic */ void m6234Tc() throws Resources.NotFoundException {
        this.f6684e0.setTranslationY(BitmapDescriptorFactory.HUE_RED);
        m6872uh(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Td */
    public /* synthetic */ void m6235Td(View view) {
        this.f6750n3.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ub */
    public /* synthetic */ void m6244Ub(String str) {
        this.f6783s1.m5688B(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6795u.f13645c, this.f6725k, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Uc */
    public /* synthetic */ void m6245Uc() throws Resources.NotFoundException {
        m6826mh();
        m6734Vg();
        m6652I8(this.f6603R.getCurrentItem());
        this.f6691f0.setVisibility(0);
        this.f6627V.f7080c.setVisibility(this.f6593P1 ? 0 : 8);
        this.f6691f0.findViewById(R.id.desktopCameraVideoContainer).setVisibility(this.f6593P1 ? 0 : 8);
        this.f6691f0.findViewById(R.id.desktopCameraAvatarContainer).setVisibility(this.f6593P1 ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Ud */
    public /* synthetic */ void m6246Ud() {
        this.f6750n3.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Vb */
    public /* synthetic */ void m6255Vb() {
        if (this.f6624U2.getVisibility() == 0) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, BitmapDescriptorFactory.HUE_RED);
            alphaAnimation.setDuration(1000L);
            alphaAnimation.setFillAfter(true);
            this.f6624U2.setAnimation(alphaAnimation);
        }
        this.f6624U2.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Vc */
    public /* synthetic */ void m6256Vc(DialogInterface dialogInterface, int i9) {
        this.f6542G4.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4933c8(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Vd */
    public /* synthetic */ void m6257Vd(C0012b c0012b, DialogInterface dialogInterface, int i9) {
        if (!m6823mb(c0012b)) {
            m6727Uf();
            return;
        }
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4914R7(c0012b, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Wb */
    public /* synthetic */ void m6266Wb(View view) {
        m6887xf(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Wc */
    public /* synthetic */ void m6267Wc(DialogInterface dialogInterface, int i9) {
        this.f6542G4.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Wd */
    public /* synthetic */ void m6268Wd(View view) {
        this.f6511B3.dismiss();
    }

    /* renamed from: Xb */
    public static /* synthetic */ void m6277Xb(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Xc */
    public /* synthetic */ void m6278Xc() throws Resources.NotFoundException {
        m6820lh(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Xd */
    public /* synthetic */ void m6279Xd(View view) {
        this.f6505A3.dismiss();
        m6685Ne();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Yb */
    public /* synthetic */ void m6288Yb(View view) throws IllegalStateException {
        this.f6732l = MimeTypes.BASE_TYPE_VIDEO;
        m6726Ue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Yc */
    public /* synthetic */ void m6289Yc(List list, List list2) throws IllegalStateException, Resources.NotFoundException {
        if (this.f6557J1) {
            return;
        }
        this.f6677d0.f6990d.m2912g();
        Iterator it = list.iterator();
        while (true) {
            boolean z8 = false;
            if (!it.hasNext()) {
                break;
            }
            C0012b c0012b = (C0012b) it.next();
            if (!c0012b.m96o()) {
                if (c0012b.m88g() == NileNetwork.ParticipantState.ParticipantState_Lobby) {
                    C1262a c1262a = (C1262a) this.f6677d0.m7040j(ParticipantsAdapter.ParticipantType.WAITING_ROOM, c0012b.f66b.f63b);
                    if (c1262a == null) {
                        C0011a c0011a = c0012b.f66b;
                        this.f6677d0.m7046p().add(new C1262a(c0011a.f63b, c0011a.f65d, c0011a.m68c()));
                    } else {
                        c1262a.m5735g(c0012b.f66b.f65d);
                        c1262a.m5734f(c0012b.f66b.m68c());
                    }
                    this.f6677d0.m7048w(ParticipantsAdapter.ParticipantType.PARTICIPANT, c0012b.f66b.f63b);
                } else {
                    int i9 = 0;
                    while (true) {
                        if (i9 >= this.f6677d0.f6990d.m2926u()) {
                            break;
                        }
                        if (this.f6677d0.f6990d.m2918m(i9).f66b.f63b == c0012b.f66b.f63b) {
                            this.f6677d0.f6990d.m2929x(i9, c0012b);
                            z8 = true;
                            break;
                        }
                        i9++;
                    }
                    if (!z8) {
                        this.f6677d0.f6990d.m2906a(c0012b);
                    }
                    this.f6677d0.m7048w(ParticipantsAdapter.ParticipantType.WAITING_ROOM, c0012b.f66b.f63b);
                }
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            C0012b c0012b2 = (C0012b) it2.next();
            this.f6677d0.f6990d.m2922q(c0012b2);
            this.f6677d0.m7048w(ParticipantsAdapter.ParticipantType.WAITING_ROOM, c0012b2.f66b.f63b);
        }
        m6664K8();
        if (this.f6715i3) {
            this.f6677d0.m7049x();
        }
        this.f6677d0.f6990d.m2915j();
        Dialog dialog = this.f6806v3;
        if ((dialog != null && dialog.isShowing()) || this.f6824y3) {
            if (this.f6677d0.m7046p().size() > 0) {
                m6871ug();
            } else {
                Dialog dialog2 = this.f6806v3;
                if (dialog2 != null && dialog2.isShowing()) {
                    this.f6806v3.dismiss();
                }
            }
        }
        int iM7044n = this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT);
        if (iM7044n > 2) {
            this.f6798u2 = true;
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] participants size = " + iM7044n);
        if (iM7044n <= 1) {
            this.f6804v1 = null;
            if (this.f6513C && this.f6826z && !this.f6557J1 && !this.f6778r3) {
                if (this.f6534F2 && this.f6617T1 != null) {
                    C2950b0.m14919r().m15208d(new PhoneCallObj(-1L, this.f6540G2, this.f6546H2, this.f6820y == InviteCallType.CALLEE, (C5170o0.m20170e(this.f6540G2) || this.f6552I2) ? false : true, false, System.currentTimeMillis(), this.f6552I2, this.f6558J2));
                }
                this.f6557J1 = true;
                m6839p9("hang up when all other participants leave(dualMeeting)");
                return;
            }
            m6840pa();
            m6891ya();
            this.f6621U.f7078a.setVisibility(8);
        } else if (this.f6603R.getCurrentItem() != m6671L9() && !this.f6778r3) {
            m6807jg();
        }
        if (iM7044n > 1) {
            if (!this.f6513C && !this.f6778r3) {
                if (this.f6826z) {
                    m6694P8();
                } else if (!this.f6501A) {
                    m6888xg();
                }
            }
            C0012b c0012b3 = this.f6804v1;
            boolean z9 = c0012b3 != null && list2.contains(c0012b3);
            boolean z10 = this.f6804v1 == null && iM7044n == 2;
            if (z9 || z10) {
                this.f6804v1 = m6759a9(Collections.emptyList());
            }
        }
        this.f6591P = true;
        this.f6561K = false;
        if (this.f6764p3) {
            this.f6677d0.m7035E(this.f6743m3);
            m6611Bh();
            if (this.f6743m3.size() == 0) {
                m7366I0().removeCallbacks(this.f6757o3);
                this.f6750n3.setVisibility(8);
            } else {
                List<Integer> list3 = this.f6743m3;
                int iIntValue = list3.get(list3.size() - 1).intValue();
                if (this.f6677d0.m7042l(iIntValue) != null) {
                    m6796hg(iIntValue);
                }
            }
            this.f6764p3 = false;
        }
        m6902zh();
        m6636Fh();
        m6883wh();
        m6802ih();
        m6788fh();
        m6688O8();
        m6826mh();
    }

    /* renamed from: Yd */
    public static /* synthetic */ void m6290Yd(Dialog dialog, View.OnClickListener onClickListener, View view) {
        dialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* renamed from: Z3 */
    public static /* synthetic */ int m6294Z3(MeetingActivity meetingActivity) {
        int i9 = meetingActivity.f6654Z2;
        meetingActivity.f6654Z2 = i9 - 1;
        return i9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Zb */
    public /* synthetic */ void m6299Zb(View view) throws IllegalStateException {
        this.f6732l = MimeTypes.BASE_TYPE_AUDIO;
        m6726Ue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Zc */
    public /* synthetic */ void m6300Zc(View view) {
        m6764b9();
    }

    /* renamed from: Zd */
    public static /* synthetic */ void m6301Zd(Dialog dialog, View.OnClickListener onClickListener, View view) {
        dialog.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    /* renamed from: ac */
    public static /* synthetic */ void m6310ac(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ad */
    public /* synthetic */ void m6311ad(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(((WebView) this.f6625U3.findViewById(R.id.rollCallPage)).getWindowToken(), 2);
        m6735W8(false);
        m6764b9();
        if (this.f6612S2.size() > 0) {
            m6721Tf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ae */
    public /* synthetic */ void m6312ae(DialogInterface dialogInterface, int i9) {
        this.f6548H4.dismiss();
    }

    /* renamed from: ag */
    public static void m6313ag(final Activity activity, final String str, final String str2, final String str3) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(activity);
        builderM16382a.setTitle(R.string.clmw_join_meeting_in_progress_title);
        builderM16382a.setMessage(R.string.clmw_join_meeting_in_progress_description);
        builderM16382a.setPositiveButton(R.string.clmw_join_join_meeting, new DialogInterface.OnClickListener() { // from class: p2.o1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                MeetingActivity.m6531tb(activity, str, null, null, str2, null, null, null, str3);
            }
        });
        builderM16382a.setNegativeButton(R.string.cancel_text, (DialogInterface.OnClickListener) null);
        builderM16382a.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bc */
    public /* synthetic */ void m6322bc(View view) {
        if (this.f6587O1) {
            m6716Sg();
        } else {
            m6734Vg();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bd */
    public /* synthetic */ void m6323bd(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        this.f6617T1.m4969u8(false, c0012b.f66b.f63b);
    }

    /* renamed from: be */
    public static /* synthetic */ void m6324be(Activity activity, DialogInterface dialogInterface, int i9) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://u.cyberlink.com/download")));
        } catch (ActivityNotFoundException e9) {
            C5154j.m20076j(e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cc */
    public /* synthetic */ void m6333cc(View view) {
        this.f6622U0.startAnimation(AnimationUtils.loadAnimation(this, R.anim.exit_to_bottom));
        this.f6622U0.setVisibility(4);
        m6746Xg(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cd */
    public /* synthetic */ void m6334cd(Dialog dialog, C0012b c0012b, boolean z8, View view) {
        dialog.dismiss();
        ULogUtility.m16670f("MeetingActivity", "Pin user " + c0012b.f66b.f63b);
        if (m6637G8(c0012b)) {
            m6881wf();
            c0012b.m73C(!z8);
            this.f6677d0.notifyDataSetChanged();
        }
    }

    /* renamed from: cg */
    public static boolean m6336cg(Activity activity, Runnable runnable) {
        return m6348dg(activity, null, runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dc */
    public /* synthetic */ boolean m6345dc(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f6527E1;
        if (c4619d == null) {
            return false;
        }
        return c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dd */
    public /* synthetic */ void m6346dd(Dialog dialog, C0012b c0012b, boolean z8, View view) {
        dialog.dismiss();
        ULogUtility.m16670f("MeetingActivity", "Unpin user " + c0012b.f66b.f63b);
        if (m6744Xe(c0012b)) {
            m6881wf();
            c0012b.m73C(!z8);
            this.f6677d0.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: de */
    public /* synthetic */ void m6347de(View view) {
        this.f6806v3.dismiss();
        this.f6603R.setCurrentItem(0);
    }

    /* renamed from: dg */
    public static boolean m6348dg(Activity activity, Runnable runnable, Runnable runnable2) {
        if (m6407ib(activity)) {
            return false;
        }
        m6361eg(activity, runnable, runnable2);
        return true;
    }

    /* renamed from: ea */
    public static MessageObj m6357ea(Group group) {
        MessageObj messageObj = null;
        MessageObj messageObj2 = null;
        for (MessageObj messageObj3 : C2950b0.m14916o().m15177p(String.valueOf(group.f13727n), FriendsClient.m15646K() - TimeUnit.MINUTES.toMillis(10L))) {
            String strM14747I = messageObj3.m14747I("statusV2");
            if (C5170o0.m20170e(strM14747I)) {
                strM14747I = messageObj3.m14747I("status");
            }
            if (strM14747I != null) {
                if ("meeting".equals(strM14747I)) {
                    messageObj = messageObj3;
                } else if (TtmlNode.END.equals(strM14747I)) {
                    messageObj2 = messageObj3;
                }
                if (messageObj != null && messageObj2 != null) {
                    break;
                }
            }
        }
        if (messageObj != null) {
            String strM14747I2 = messageObj.m14747I("callId");
            String strM14747I3 = messageObj2 != null ? messageObj2.m14747I("callId") : null;
            if (!C5170o0.m20170e(strM14747I2) && !strM14747I2.equals(strM14747I3)) {
                return messageObj;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ec */
    public /* synthetic */ void m6358ec(View view) {
        m6836of(this.f6514C0.getText().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ed */
    public /* synthetic */ void m6359ed(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        if (this.f6581N1 && this.f6804v1.equals(c0012b)) {
            C5187v0.m20268d(getString(R.string.clm_meeting_disable_mute_presenter));
        } else {
            m6756Ze(c0012b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ee */
    public /* synthetic */ void m6360ee() {
        this.f6806v3.dismiss();
    }

    /* renamed from: eg */
    public static void m6361eg(Activity activity, final Runnable runnable, final Runnable runnable2) {
        C3123g.m16382a(activity).setTitle(R.string.feedback_error).setMessage(R.string.clm_meeting_microphone_not_available).setPositiveButton(R.string.clm_meeting_button_hang_up, new DialogInterface.OnClickListener() { // from class: p2.d4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                MeetingActivity.m6179Od(runnable, dialogInterface, i9);
            }
        }).setNegativeButton(R.string.clm_meeting_retry, new DialogInterface.OnClickListener() { // from class: p2.e4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                runnable2.run();
            }
        }).setCancelable(false).create().show();
    }

    /* renamed from: f9 */
    public static void m6370f9(Activity activity, Group group, boolean z8, String str, String str2) {
        m6382g9(activity, group, z8, str, str2, false, "", "", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fc */
    public /* synthetic */ void m6371fc() {
        CLUtility.m16606x2(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fd */
    public /* synthetic */ void m6372fd(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        m6773cf(c0012b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fe */
    public /* synthetic */ void m6373fe(DialogInterface dialogInterface) {
        m7366I0().removeCallbacks(this.f6818x3);
    }

    /* renamed from: g9 */
    public static void m6382g9(Activity activity, Group group, boolean z8, String str, String str2, boolean z9, String str3, String str4, String str5) {
        m6395h9(activity, group, z8, str, str2, z9, str3, str4, false, str5);
    }

    /* renamed from: gb */
    public static boolean m6383gb() {
        return MeetingManager.m5619l() && f6496v5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: gc */
    public /* synthetic */ void m6384gc(View view) {
        m6768bg(true);
        this.f6521D1.m12067S();
        this.f6514C0.post(new Runnable() { // from class: p2.p1
            @Override // java.lang.Runnable
            public final void run() {
                this.f20553b.m6371fc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: gd */
    public /* synthetic */ void m6385gd(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        m6762af(c0012b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ge */
    public /* synthetic */ void m6386ge() {
        if (this.f6623U1 == null || this.f6617T1 == null) {
            return;
        }
        Log.i("MeetingActivity", "[" + this.f6748n1 + "] re-init speaker due to some device can't output audio by speaker when default is on");
        this.f6623U1.m5066q(this.f6543H ^ true);
        this.f6617T1.m4904J7(null);
        this.f6623U1.m5066q(this.f6543H);
        this.f6617T1.m4904J7(null);
    }

    /* renamed from: h9 */
    public static void m6395h9(final Activity activity, final Group group, final boolean z8, final String str, final String str2, final boolean z9, final String str3, final String str4, final boolean z10, final String str5) {
        if (m6213Rf(activity) || m6124Jf(activity, group, new Runnable() { // from class: p2.u4
            @Override // java.lang.Runnable
            public final void run() {
                MeetingActivity.m6370f9(activity, group, z8, str, str2);
            }
        }) || m6336cg(activity, new Runnable() { // from class: p2.v4
            @Override // java.lang.Runnable
            public final void run() {
                MeetingActivity.m6370f9(activity, group, z8, str, str2);
            }
        })) {
            return;
        }
        final Runnable runnable = new Runnable() { // from class: p2.w4
            @Override // java.lang.Runnable
            public final void run() {
                MeetingActivity.m6092Gb(str, z8, group, z9, str3, str4, z10, str5, str2, activity);
            }
        };
        if (group == null || "Dual".equals(group.f13716c)) {
            runnable.run();
        } else {
            f6497w5.execute(new Runnable() { // from class: p2.x4
                @Override // java.lang.Runnable
                public final void run() {
                    MeetingActivity.m6111Ib(group, activity, str2, runnable);
                }
            });
        }
        f6495C5 = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hc */
    public /* synthetic */ void m6396hc(View view) {
        m6734Vg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hd */
    public /* synthetic */ void m6397hd(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        m6819lg(c0012b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: he */
    public /* synthetic */ void m6398he() {
        if (!this.f6515C1.m3295e()) {
            m6774ch();
            if (this.f6603R.getCurrentItem() != 1 || this.f6696f5 == 0) {
                return;
            }
            m6686Nf(true);
            return;
        }
        if (this.f6581N1 || this.f6543H || !m6766bb() || !this.f6513C || this.f6559J3) {
            return;
        }
        m6769bh();
    }

    /* renamed from: ib */
    public static boolean m6407ib(Activity activity) {
        AudioManager audioManager = (AudioManager) activity.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            ULogUtility.m16670f("MeetingActivity", "[isMicAvailable] audioManager is null");
            return false;
        }
        int mode = audioManager.getMode();
        ULogUtility.m16670f("MeetingActivity", "[isMicAvailable] mode = " + mode);
        return mode == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ic */
    public /* synthetic */ void m6408ic(View view) {
        m6614Ce();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: id */
    public /* synthetic */ void m6409id(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4919W2(c0012b.f66b.f63b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ie */
    public /* synthetic */ void m6410ie() {
        NileNetwork nileNetwork;
        if (this.f6557J1 || (nileNetwork = this.f6617T1) == null || this.f6644X4 == null || nileNetwork.m4970v3() == this.f6644X4) {
            return;
        }
        Object obj = new Object();
        synchronized (this.f6650Y4) {
            NileNetwork.DisplayMode displayMode = this.f6644X4;
            ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] switch displayMode to " + displayMode + " | currentMode = " + this.f6617T1.m4970v3());
            this.f6617T1.m4893F8(this.f6644X4, new C1345s0(displayMode, obj));
        }
        try {
            synchronized (obj) {
                obj.wait(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
            }
        } catch (Exception e9) {
            Log.d("MeetingActivity", "switchDisplayMode error, info = " + e9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: jc */
    public /* synthetic */ void m6419jc(View view) {
        m6803j9();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: jd */
    public /* synthetic */ void m6420jd(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4912P7(c0012b.f66b.f63b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: kc */
    public /* synthetic */ void m6430kc(View view) {
        if (this.f6829z2.getVisibility() == 8) {
            this.f6504A2.setSelected(true);
            this.f6829z2.setVisibility(0);
            findViewById(R.id.meetingUserInfoLayout).setVisibility(8);
            this.f6796u0.setVisibility(8);
            this.f6782s0.setVisibility(8);
            return;
        }
        this.f6504A2.setSelected(false);
        this.f6829z2.setVisibility(8);
        findViewById(R.id.meetingUserInfoLayout).setVisibility(0);
        if (this.f6586O0.getVisibility() == 0) {
            return;
        }
        this.f6796u0.setVisibility(0);
        this.f6782s0.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: kd */
    public /* synthetic */ void m6431kd(Dialog dialog, C0012b c0012b, View view) {
        dialog.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4895G7(c0012b.f66b.f63b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lc */
    public /* synthetic */ void m6441lc(View view) {
        if (this.f6631V3.m24458a()) {
            boolean z8 = !this.f6637W3;
            this.f6637W3 = z8;
            if (z8) {
                this.f6617T1.m4954n3();
                C5187v0.m20268d("enableWebRTCLog");
            } else {
                this.f6617T1.m4948k3();
                C5187v0.m20268d("disableWebRTCLog");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ld */
    public /* synthetic */ void m6442ld(MediaPlayer mediaPlayer, MediaPlayer mediaPlayer2) throws IllegalStateException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] Complete playing end sound IN");
        mediaPlayer.setOnCompletionListener(null);
        mediaPlayer.stop();
        mediaPlayer.release();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] Complete playing end sound OUT");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: le */
    public /* synthetic */ void m6443le(View view) {
        m6432ke(view, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mc */
    public /* synthetic */ void m6452mc(View view) {
        m6728Ug();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: md */
    public /* synthetic */ void m6453md() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] release audio manager");
        m6687Ng();
        m6644H3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: me */
    public /* synthetic */ void m6454me() {
        this.f6610S0.setSelection(r0.getCount() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: nc */
    public /* synthetic */ void m6463nc(View view) {
        m6722Tg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: nd */
    public /* synthetic */ void m6464nd() {
        this.f6574M0.callOnClick();
    }

    /* renamed from: ne */
    public static /* synthetic */ void m6465ne(String str) {
        try {
            C5525z c5525zExecute = new C5522w().mo21256a(new C5523x.a().m21820i(str).m21813b()).execute();
            if (c5525zExecute.m21849f() != null) {
                CLUtility.m16482Q2(new ZipInputStream(c5525zExecute.m21849f().m21229f()));
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: oc */
    public /* synthetic */ void m6474oc(View view) {
        m6614Ce();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: od */
    public /* synthetic */ void m6475od(DialogInterface dialogInterface, int i9) {
        this.f6548H4.dismiss();
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4951l8(true, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: oe */
    public /* synthetic */ void m6476oe() {
        if (this.f6557J1) {
            return;
        }
        m6855rh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: pd */
    public /* synthetic */ void m6486pd(DialogInterface dialogInterface, int i9) {
        this.f6548H4.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: pe */
    public /* synthetic */ void m6487pe() {
        synchronized (this.f6601Q3) {
            C1375e c1375e = this.f6616T0;
            if (c1375e != null) {
                c1375e.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: qc */
    public /* synthetic */ void m6496qc() {
        Friend friend;
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        C0487p<C0012b> c0487pM7043m = this.f6677d0.m7043m();
        int iM2926u = c0487pM7043m.m2926u();
        boolean z8 = false;
        for (int i9 = 0; i9 < iM2926u; i9++) {
            C0012b c0012bM2918m = c0487pM7043m.m2918m(i9);
            if (c0012bM2918m != null && (friend = this.f6769q1.get(c0012bM2918m.f66b.f65d)) != null) {
                arrayList.add(friend);
            }
        }
        List<C1262a> listM7046p = this.f6677d0.m7046p();
        for (int i10 = 0; i10 < listM7046p.size(); i10++) {
            Friend friend2 = this.f6769q1.get(listM7046p.get(i10).m5732d());
            if (friend2 != null) {
                arrayList.add(friend2);
            }
        }
        Intent intent = new Intent(this, (Class<?>) InviteMeetingActivity.class);
        intent.setFlags(1073741824);
        Bundle extras = getIntent().getExtras();
        extras.putString("INTENT_TITLE", getString(R.string.clm_invite_page_title));
        extras.putParcelableArrayList("INTENT_PREV_SELECTED_USERS_LIST", arrayList);
        extras.putParcelableArrayList("INTENT_CANNOT_MODIFIED_USERS_LIST", arrayList);
        extras.putString("meetingId", this.f6711i);
        extras.putString("type", this.f6732l);
        extras.putString("meetingShareUrl", this.f6746n);
        extras.putString("meetingMServerAddress", this.f6753o);
        extras.putString("meetingMServerToken", this.f6760p);
        extras.putBoolean("isLtiMeeting", this.f6823y2);
        if (!C5170o0.m20170e(this.f6718j)) {
            extras.putString("password", this.f6718j);
        }
        if (!this.f6817x2 && !this.f6811w2) {
            z8 = true;
        }
        extras.putBoolean("canSendPSTNInvitation", z8);
        intent.putExtras(extras);
        startActivityForResult(intent, 40002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: qd */
    public /* synthetic */ void m6497qd() {
        if (this.f6603R.getCurrentItem() == 1 || this.f6603R.getCurrentItem() == m6671L9()) {
            return;
        }
        m6863te(this.f6621U.f7078a, m6624E9(), m6618D9());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: qe */
    public /* synthetic */ void m6498qe(C1376f c1376f) {
        m6795hf(c1376f, false);
    }

    /* renamed from: rb */
    public static boolean m6507rb(String str) {
        return str.startsWith("temp");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: rc */
    public /* synthetic */ void m6508rc() {
        if (m7367J0() || this.f6513C) {
            return;
        }
        if (m6749Ya()) {
            this.f6562K0.performClick();
        } else {
            this.f6556J0.performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: rd */
    public /* synthetic */ void m6509rd(List list) {
        synchronized (this.f6601Q3) {
            C1375e c1375e = this.f6616T0;
            if (c1375e != null) {
                c1375e.m7119h(list);
                m6752Yg(true, 150L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: re */
    public /* synthetic */ void m6510re(AspectRatio aspectRatio) {
        m6790gf(this.f6691f0, aspectRatio);
    }

    /* renamed from: s9 */
    public static String m6519s9() {
        StringBuilder sb = new StringBuilder();
        sb.append("temp");
        for (int i9 = 0; i9 < 9; i9++) {
            sb.append((int) (Math.random() * 10.0d));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sc */
    public /* synthetic */ void m6520sc() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (m7367J0() || this.f6513C) {
            return;
        }
        m6827n9("Auto-Reject");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sd */
    public /* synthetic */ void m6521sd() {
        ULogUtility.m16683s("MeetingActivity", "[DesktopSharing] setShareScreen / create MediaProjectionManager - in");
        startActivityForResult(((MediaProjectionManager) getSystemService("media_projection")).createScreenCaptureIntent(), 40004);
    }

    /* renamed from: sg */
    public static void m6522sg(final Activity activity) {
        C3123g.m16382a(activity).setMessage(R.string.clm_device_unsupported).setPositiveButton(R.string.clm_learn_more, new DialogInterface.OnClickListener() { // from class: p2.m4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                MeetingActivity.m6324be(activity, dialogInterface, i9);
            }
        }).setNegativeButton(R.string.close, (DialogInterface.OnClickListener) null).create().show();
    }

    /* renamed from: tb */
    public static void m6531tb(Activity activity, String str, String str2, String str3, String str4, Group group, String str5, String str6, String str7) {
        m6542ub(activity, str, str2, str3, str4, group, str5, str6, str7, null, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: tc */
    public /* synthetic */ void m6532tc(String str, boolean z8) {
        C6468p.m24793u(this, str, z8);
    }

    /* renamed from: ub */
    public static void m6542ub(Activity activity, String str, String str2, String str3, String str4, Group group, String str5, String str6, String str7, BreakoutRoomState breakoutRoomState, BreakoutRoom breakoutRoom, Boolean bool, Boolean bool2, Boolean bool3) {
        m6554vb(activity, str, str2, str3, null, str4, group, str5, str6, str7, breakoutRoomState, breakoutRoom, bool, bool2, bool3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: uc */
    public /* synthetic */ void m6543uc() {
        m6862ta(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ud */
    public /* synthetic */ void m6544ud(DialogInterface dialogInterface, int i9) {
        this.f6600Q2.callOnClick();
    }

    /* renamed from: v9 */
    public static String m6553v9(Context context) {
        UserInfo userInfoM16497V0;
        if (Globals.m7388i0().m7464O1() && (userInfoM16497V0 = CLUtility.m16497V0(context)) != null && !C5170o0.m20170e(userInfoM16497V0.f13778c)) {
            return userInfoM16497V0.f13778c;
        }
        C4677a.m18711o(context);
        String strM18708l = C4677a.m18708l();
        return !C5170o0.m20170e(strM18708l) ? strM18708l : "";
    }

    /* renamed from: vb */
    public static void m6554vb(final Activity activity, final String str, final String str2, final String str3, final String str4, final String str5, final Group group, final String str6, final String str7, final String str8, final BreakoutRoomState breakoutRoomState, final BreakoutRoom breakoutRoom, final Boolean bool, final Boolean bool2, final Boolean bool3, Boolean bool4) {
        if (m6213Rf(activity) || m6336cg(activity, new Runnable() { // from class: p2.p2
            @Override // java.lang.Runnable
            public final void run() {
                MeetingActivity.m6576xb(activity, str, str2, str3, str4, str5, group, str6, str7, str8, breakoutRoomState, breakoutRoom, bool, bool2, bool3);
            }
        })) {
            return;
        }
        if (DeviceCapability.m7311e().m7323d()) {
            m6522sg(activity);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "join");
        bundle.putString("meetingId", str);
        if (!C5170o0.m20170e(str2)) {
            bundle.putString("displayName", str2);
        }
        if (!C5170o0.m20170e(str6)) {
            bundle.putString("meetingMServerAddress", str6);
        }
        if (!C5170o0.m20170e(str7)) {
            bundle.putString("meetingMServerToken", str7);
        }
        if (group != null) {
            bundle.putParcelable("group", group);
        }
        if (str5 != null) {
            bundle.putString("type", str5);
        }
        if (str3 != null) {
            bundle.putString("password", str3);
        }
        if (str4 != null) {
            bundle.putString("ltiToken", str4);
        }
        if (breakoutRoomState != null) {
            bundle.putSerializable("breakoutRoomState", breakoutRoomState);
        }
        if (breakoutRoom != null) {
            bundle.putParcelable("breakoutRoom", breakoutRoom);
        }
        if (bool != null) {
            bundle.putBoolean("isMicrophoneOn", bool.booleanValue());
        }
        if (bool2 != null) {
            bundle.putBoolean("isCameraOn", bool2.booleanValue());
        }
        if (bool3 != null) {
            bundle.putBoolean("isSpeakerOn", bool3.booleanValue());
        }
        if (bool4 != null) {
            bundle.putBoolean("moveToWaitingRoom", bool4.booleanValue());
        }
        ULogUtility.m16683s("MeetingActivity", "join meeting from: " + str8);
        Intent intent = new Intent(activity, (Class<?>) MeetingActivity.class);
        intent.putExtras(bundle);
        MeetingManager.m5630w(str, MeetingManager.MeetingStatus.START_ACTIVITY);
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: vc */
    public /* synthetic */ void m6555vc(Bitmap bitmap) {
        if (this.f6714i2) {
            m7366I0().postDelayed(new RunnableC6018q3(this), 100L);
        } else {
            this.f6728k2 = true;
            m6616Cg(bitmap, true, false);
        }
    }

    /* renamed from: wb */
    public static void m6565wb(Activity activity, String str, String str2, String str3, String str4, String str5, Group group, String str6, String str7, String str8) {
        m6576xb(activity, str, str2, str3, str4, str5, group, str6, str7, str8, null, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: wc */
    public /* synthetic */ void m6566wc(Dialog dialog, View view) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        m6828na(getString(R.string.cant_talk_right_now));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: wd */
    public /* synthetic */ void m6567wd(Dialog dialog, View view) {
        dialog.dismiss();
        startActivity(new Intent(this, (Class<?>) UpgradeToProUserActivity.class));
    }

    /* renamed from: xb */
    public static void m6576xb(Activity activity, String str, String str2, String str3, String str4, String str5, Group group, String str6, String str7, String str8, BreakoutRoomState breakoutRoomState, BreakoutRoom breakoutRoom, Boolean bool, Boolean bool2, Boolean bool3) {
        m6554vb(activity, str, str2, str3, str4, str5, group, str6, str7, str8, breakoutRoomState, breakoutRoom, bool, bool2, bool3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: xc */
    public /* synthetic */ void m6577xc(Dialog dialog, View view) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        m6828na(getString(R.string.i_am_in_a_meeting));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: yc */
    public /* synthetic */ void m6587yc(Dialog dialog, View view) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        m6828na(getString(R.string.i_will_call_you_later));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: yd */
    public /* synthetic */ void m6588yd(Dialog dialog, View view) {
        dialog.dismiss();
        startActivity(new Intent(this, (Class<?>) UpgradeToProUserActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zb */
    public /* synthetic */ void m6597zb(String str, DialogInterface dialogInterface, int i9) {
        m6839p9("receive kick event by : " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zc */
    public /* synthetic */ void m6598zc(Dialog dialog, View view) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        m6828na(getString(R.string.urgent_calls_only_detail));
    }

    /* renamed from: A9 */
    public final int m6600A9() throws Resources.NotFoundException {
        int iM6736W9 = m6736W9();
        if (this.f6581N1 || this.f6621U.f7091n != AspectRatio.LANDSCAPE) {
            return iM6736W9;
        }
        return (int) ((m6606B9() / getResources().getDimension(R.dimen.t136dp)) * getResources().getDimension(R.dimen.t76dp));
    }

    /* renamed from: Aa */
    public final void m6601Aa() {
        Button button = (Button) findViewById(R.id.desktopSharingStopButton);
        this.f6565K3 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: p2.v3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20629b.m6266Wb(view);
            }
        });
    }

    /* renamed from: Ae */
    public final void m6602Ae(boolean z8) {
        int iM6624E9 = m6624E9();
        int iM6618D9 = m6618D9();
        int dimension = (int) getResources().getDimension(R.dimen.t10dp);
        float y8 = (iM6618D9 / 2.0f) + this.f6603R.getY() + ((int) getResources().getDimension(R.dimen.clm_meeting_room_header_height)) + ((int) getResources().getDimension(R.dimen.t10dp));
        this.f6621U.m7140c();
        C1376f c1376f = this.f6621U;
        c1376f.f7089l = C5177q1.m20228b(c1376f.f7078a, iM6624E9, iM6618D9, (m6742X9() - dimension) - (iM6624E9 / 2.0f), y8, 300);
        this.f6621U.f7089l.addListener(this.f6662a5);
        if (!z8) {
            this.f6621U.f7089l.setDuration(0L);
        }
        this.f6621U.f7089l.start();
    }

    /* renamed from: Af */
    public final boolean m6603Af() {
        int currentItem = this.f6603R.getCurrentItem();
        return !(Build.VERSION.SDK_INT == 26 && this.f6559J3 && (currentItem != m6671L9() || (currentItem == m6671L9() && this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) <= 3))) && (m6900zf() || !this.f6555J);
    }

    /* renamed from: Ag */
    public final void m6604Ag() {
        int iM5067r = this.f6623U1.m5067r(new C1362y0(), ("join".equals(this.f6814x) && this.f6820y == InviteCallType.CALLEE) ? 0 : 3);
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] Start RTCAudioManager with speakerOn: " + this.f6543H);
        this.f6623U1.m5066q(this.f6543H);
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] Start RTCAudioManager with saved mode: " + iM5067r);
    }

    /* renamed from: Ah */
    public final void m6605Ah(List<String> list) throws NumberFormatException {
        this.f6743m3.clear();
        this.f6722j3 = false;
        for (String str : list) {
            Log.d("MeetingActivity", "onUpdateRaisedHandList / id: " + str);
            int i9 = Integer.parseInt(str);
            this.f6743m3.add(Integer.valueOf(i9));
            C0012b c0012b = this.f6797u1;
            if (c0012b != null && c0012b.f66b.f63b == i9) {
                this.f6722j3 = true;
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: B */
    public void mo5102B(C0012b c0012b, C0012b c0012b2) {
        if (c0012b2 == null) {
            C5187v0.m20268d(getString(R.string.clm_meeting_participant_removed_by_the_other, c0012b.m86e()));
        } else {
            C5187v0.m20268d(getString(R.string.clm_meeting_someone_removed_by_the_other, c0012b2.m86e(), c0012b.m86e()));
        }
    }

    /* renamed from: B9 */
    public final int m6606B9() {
        int iM6742X9 = m6742X9();
        return (this.f6581N1 || m6766bb()) ? iM6742X9 : m6742X9() - m6707R9();
    }

    /* renamed from: Ba */
    public final void m6607Ba() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.extraPage);
        this.f6625U3 = frameLayout;
        View viewFindViewById = frameLayout.findViewById(R.id.extraPageFakeStatusBar);
        if (this.f6599Q1) {
            viewFindViewById.getLayoutParams().height = this.f6605R1;
        }
    }

    /* renamed from: Be */
    public final void m6608Be(SurfaceViewRenderer surfaceViewRenderer, ViewGroup viewGroup, ViewGroup viewGroup2, boolean z8) {
        boolean z9 = false;
        int i9 = 0;
        while (true) {
            if (i9 >= viewGroup.getChildCount()) {
                break;
            }
            if (surfaceViewRenderer == viewGroup.getChildAt(i9)) {
                z9 = true;
                break;
            }
            i9++;
        }
        if (!z9) {
            Log.d("MeetingActivity", "surfaceView is not in oldContainer, skip it");
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewGroup.getClass() == viewGroup2.getClass() ? surfaceViewRenderer.getLayoutParams() : null;
        int visibility = surfaceViewRenderer.getVisibility();
        surfaceViewRenderer.setVisibility(8);
        viewGroup.removeView(surfaceViewRenderer);
        surfaceViewRenderer.setZOrderMediaOverlay(z8);
        if (layoutParams != null) {
            viewGroup2.addView(surfaceViewRenderer, layoutParams);
        } else {
            viewGroup2.addView(surfaceViewRenderer);
        }
        surfaceViewRenderer.setVisibility(visibility);
    }

    /* renamed from: Bf */
    public boolean m6609Bf() {
        return !this.f6519D;
    }

    /* renamed from: Bg */
    public final void m6610Bg() {
        if (this.f6515C1 == null) {
            this.f6515C1 = C0598a.m3291a(this, new Runnable() { // from class: p2.g2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20445b.m6398he();
                }
            });
        }
        this.f6515C1.m3296f();
    }

    /* renamed from: Bh */
    public final void m6611Bh() {
        this.f6789t0.setVisibility(8);
        this.f6815x0.setVisibility(8);
        if (this.f6581N1) {
            return;
        }
        if (this.f6603R.getCurrentItem() != 1) {
            if (this.f6603R.getCurrentItem() == m6671L9()) {
                m6832nh();
                return;
            } else {
                this.f6621U.f7086i.setVisibility(m6743Xa() ? 0 : 8);
                return;
            }
        }
        if (this.f6804v1 != null) {
            boolean zM6743Xa = m6743Xa();
            boolean z8 = this.f6586O0.getVisibility() == 0 && this.f6809w0.getVisibility() == 0;
            this.f6789t0.setVisibility(((this.f6782s0.getVisibility() == 0) && zM6743Xa) ? 0 : 8);
            ImageView imageView = this.f6815x0;
            if (z8 && zM6743Xa) {
                i = 0;
            }
            imageView.setVisibility(i);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: C0 */
    public void mo5103C0(boolean z8) {
        this.f6665b2 = z8;
        Log.d("MeetingActivity", "onUpdateAllowRecordingEnabled - in");
    }

    /* renamed from: C9 */
    public final int m6612C9(int i9) {
        return i9 == 1 ? m6600A9() : i9 == m6671L9() ? m6689O9() : m6618D9();
    }

    /* renamed from: Ca */
    public final void m6613Ca() {
        C1348t0 c1348t0 = new C1348t0(getApplicationContext());
        this.f6621U.f7078a.setOnTouchListener(c1348t0);
        this.f6691f0.setOnTouchListener(c1348t0);
    }

    /* renamed from: Ce */
    public final void m6614Ce() {
        Runnable runnable = new Runnable() { // from class: p2.h1
            @Override // java.lang.Runnable
            public final void run() {
                this.f20455b.m6496qc();
            }
        };
        if (!C5170o0.m20170e(this.f6746n)) {
            runnable.run();
            return;
        }
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] meetingShareUrl is null, get meetingShareUrl from Do Server.");
        C1260a.m5675m(this.f6711i, this.f6718j, this.f6687e3, Globals.m7388i0().m7506X()).m15439e(new C1314i(new DialogC3133q.b(this).m16411b(), runnable));
    }

    /* renamed from: Cf */
    public final boolean m6615Cf() {
        if (!m6785fb()) {
            Log.d("MeetingActivity", "isHaveSharedFiles false");
            return false;
        }
        if (!this.f6676d) {
            return true;
        }
        Log.d("MeetingActivity", "isSaveUploadFilesReminderHaveShown false");
        return false;
    }

    /* renamed from: Cg */
    public final void m6616Cg(Bitmap bitmap, boolean z8, boolean z9) {
    }

    /* renamed from: Ch */
    public final void m6617Ch() {
        if (this.f6603R.getCurrentItem() == m6671L9()) {
            m6795hf(this.f6621U, false);
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f6621U.f7079b.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 17;
        this.f6621U.f7079b.requestLayout();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: D */
    public void mo5104D(NileNetwork.PhoneLineInviteResult phoneLineInviteResult) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnPhonelineInviteResult : " + phoneLineInviteResult);
        if (phoneLineInviteResult == NileNetwork.PhoneLineInviteResult.PhoneLine_Invite_PreJoinOK) {
            m7366I0().removeCallbacks(this.f6530E4);
        } else if (phoneLineInviteResult == NileNetwork.PhoneLineInviteResult.PhoneLine_Invite_Ringing && this.f6820y == InviteCallType.CALLER && this.f6534F2) {
            m6901zg();
        }
    }

    /* renamed from: D9 */
    public final int m6618D9() {
        return (int) getResources().getDimension(this.f6621U.f7091n == AspectRatio.PORTRAIT ? R.dimen.t136dp : R.dimen.t76dp);
    }

    /* renamed from: Da */
    public final void m6619Da() {
        this.f6549I = true;
        this.f6646Y0.setSelected(this.f6543H);
        this.f6652Z0.setSelected(this.f6555J);
        this.f6658a1.setSelected(this.f6549I);
        this.f6502A0.setVisibility(8);
        this.f6747n0.setVisibility(this.f6555J ? 0 : 8);
        this.f6684e0.setVisibility(m6900zf() ? 4 : 0);
        this.f6653Z1.setVisibility(m6603Af() ? 8 : 0);
        this.f6821y0.setVisibility(!this.f6555J ? 0 : 8);
        this.f6741m1.setVisibility(this.f6679d2 ? 0 : 8);
    }

    /* renamed from: De */
    public final void m6620De() {
        if ("notificationDecline".equals(this.f6777r2)) {
            this.f6574M0.callOnClick();
        }
    }

    /* renamed from: Df */
    public final void m6621Df() {
        int i9 = 8;
        if (this.f6603R.getCurrentItem() != m6671L9()) {
            this.f6621U.f7078a.setVisibility(8);
            m6891ya();
        }
        this.f6586O0.setVisibility(0);
        this.f6796u0.setVisibility(8);
        this.f6698g0.findViewById(R.id.fakeStatusBar).setBackgroundResource(R.color.clm_bg);
        this.f6782s0.setVisibility((m6766bb() || this.f6559J3) ? 8 : 0);
        EmojiconTextView emojiconTextView = this.f6809w0;
        if (m6766bb() && !this.f6559J3) {
            i9 = 0;
        }
        emojiconTextView.setVisibility(i9);
        m6611Bh();
        m6902zh();
        if (!this.f6534F2 || this.f6570L2 || this.f6798u2) {
            if (this.f6770q2) {
                C6127a.m23474o(this, this.f6803v0, this.f6791t2, R.drawable.pic_default);
                this.f6809w0.setText(this.f6784s2);
                return;
            }
            C0012b c0012b = this.f6804v1;
            if (c0012b != null) {
                if ((c0012b.f66b.f65d > 0) || c0012b.m87f() != NileNetwork.PlatformType.Platform_PhoneLine) {
                    this.f6755o1.m6013r(this.f6804v1.f66b, this.f6803v0, AvatarDrawableCache.AvatarSize.LARGE);
                } else {
                    C6127a.m23474o(this, this.f6803v0, "", R.drawable.pic_default);
                }
                this.f6809w0.setText(this.f6804v1.m86e());
                return;
            }
            return;
        }
        Group group = this.f6808w;
        m6858se(group != null ? group.f13724k : "", true, this.f6803v0);
        String str = this.f6546H2;
        if (!C5170o0.m20170e(this.f6540G2) && !this.f6552I2) {
            str = this.f6546H2 + "(#" + this.f6540G2 + ")";
        }
        this.f6809w0.setText(str);
    }

    /* renamed from: Dg */
    public final void m6622Dg() {
        m7366I0().removeCallbacks(this.f6766p5);
        m7366I0().postDelayed(this.f6766p5, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    /* renamed from: Dh */
    public final void m6623Dh() {
        if (this.f6581N1) {
            return;
        }
        if (this.f6603R.getCurrentItem() == m6671L9()) {
            this.f6621U.f7081d.setVisibility(0);
            this.f6621U.f7078a.setBackgroundResource(R.color.clm_gallery_background);
            return;
        }
        if (this.f6603R.getCurrentItem() == m6784fa()) {
            this.f6621U.f7081d.setVisibility(8);
            this.f6621U.f7078a.setBackgroundResource(R.color.transparent);
            return;
        }
        this.f6621U.f7081d.setVisibility(this.f6559J3 ? 8 : 0);
        this.f6621U.f7078a.setBackgroundResource(R.color.transparent);
        this.f6621U.f7088k.setVisibility(8);
        this.f6621U.f7082e.setVisibility(8);
        this.f6621U.f7083f.setVisibility(4);
        this.f6621U.f7084g.setVisibility(8);
        this.f6621U.f7085h.setVisibility(8);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: E */
    public void mo5105E(boolean z8) {
        this.f6672c2 = z8;
        this.f6677d0.m7050y(z8);
        this.f6677d0.notifyDataSetChanged();
        m6889xh();
    }

    /* renamed from: E9 */
    public final int m6624E9() {
        return (int) getResources().getDimension(this.f6621U.f7091n == AspectRatio.PORTRAIT ? R.dimen.t76dp : R.dimen.t136dp);
    }

    /* renamed from: Ea */
    public final void m6625Ea() {
        this.f6526E0.setOnTouchListener(this.f6752n5);
    }

    /* renamed from: Ee */
    public final void m6626Ee(RTCAudioManager.AudioDevice audioDevice, Set<RTCAudioManager.AudioDevice> set) {
        Log.d("AudioManager", "onAudioManagerDevicesChanged: " + set + ", selected: " + audioDevice);
        RTCAudioManager.AudioDevice audioDevice2 = RTCAudioManager.AudioDevice.SPEAKER_PHONE;
        if (set.contains(audioDevice2) && !set.contains(RTCAudioManager.AudioDevice.EARPIECE)) {
            this.f6543H = true;
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onAudioDevicesChanged, no earpiece.");
            this.f6623U1.m5066q(this.f6543H);
            this.f6646Y0.setSelected(this.f6543H);
            this.f6568L0.setSelected(this.f6543H);
            this.f6729k3 = false;
            this.f6568L0.setEnabled(false);
        } else if (set.contains(RTCAudioManager.AudioDevice.EARPIECE) && !set.contains(audioDevice2)) {
            this.f6543H = false;
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onAudioDevicesChanged, no speaker.");
            this.f6623U1.m5066q(this.f6543H);
            this.f6646Y0.setSelected(this.f6543H);
            this.f6568L0.setSelected(this.f6543H);
            this.f6729k3 = false;
            this.f6568L0.setEnabled(false);
        }
        if (audioDevice == RTCAudioManager.AudioDevice.WIRED_HEADSET || audioDevice == RTCAudioManager.AudioDevice.BLUETOOTH) {
            Log.d("MeetingActivity", "Wired headset or BlueTooth is plug in");
            this.f6543H = false;
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onAudioDevicesChanged, external.");
            this.f6623U1.m5066q(this.f6543H);
            this.f6646Y0.setSelected(this.f6543H);
            this.f6568L0.setSelected(this.f6543H);
        }
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4904J7(audioDevice);
        }
    }

    /* renamed from: Ef */
    public void m6627Ef() {
        AlertDialog alertDialogCreate = C3123g.m16382a(this).setMessage(R.string.breakout_room_end_session).setPositiveButton(R.string.breakout_room_return, new DialogInterface.OnClickListener() { // from class: p2.o4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20548b.m6544ud(dialogInterface, i9);
            }
        }).setNegativeButton(R.string.breakout_room_later, new DialogInterface.OnClickListener() { // from class: p2.p4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        }).setCancelable(false).create();
        alertDialogCreate.show();
        TextView textView = (TextView) alertDialogCreate.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setGravity(17);
        }
        Button button = alertDialogCreate.getButton(-1);
        if (button != null) {
            button.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
            button.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t17dp));
        }
        Button button2 = alertDialogCreate.getButton(-2);
        if (button2 != null) {
            button2.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t17dp));
        }
    }

    /* renamed from: Eg */
    public final void m6628Eg() {
        if (!this.f6700g2 || this.f6603R.getCurrentItem() != 1) {
            this.f6621U.f7087j.setVisibility(8);
            return;
        }
        C1376f c1376f = this.f6621U;
        c1376f.f7087j.setX(c1376f.f7079b.getX());
        C1376f c1376f2 = this.f6621U;
        c1376f2.f7087j.setY(c1376f2.f7079b.getY());
        ViewGroup.LayoutParams layoutParams = this.f6621U.f7087j.getLayoutParams();
        layoutParams.width = this.f6621U.f7079b.getWidth();
        layoutParams.height = this.f6621U.f7079b.getHeight();
        this.f6621U.f7087j.setLayoutParams(layoutParams);
        this.f6621U.f7080c.addFrameListener(this.f6773q5, 1.0f);
    }

    /* renamed from: Eh */
    public final void m6629Eh(final C1376f c1376f, int i9, int i10, int i11) {
        boolean z8 = i11 == 90 || i11 == 270;
        int i12 = z8 ? i10 : i9;
        if (!z8) {
            i9 = i10;
        }
        final AspectRatio aspectRatio = i12 < i9 ? AspectRatio.PORTRAIT : AspectRatio.LANDSCAPE;
        if (aspectRatio == c1376f.f7091n) {
            return;
        }
        c1376f.f7091n = aspectRatio;
        m7366I0().post(new Runnable() { // from class: p2.x2
            @Override // java.lang.Runnable
            public final void run() {
                this.f20657b.m6498qe(c1376f);
            }
        });
        if (this.f6581N1 && c1376f == this.f6627V) {
            m7366I0().post(new Runnable() { // from class: com.cyberlink.meeting.page.m.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7057b.m6510re(aspectRatio);
                }
            });
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: F0 */
    public void mo5106F0(int i9) {
        boolean z8 = false;
        if (i9 == 0) {
            AlertDialog alertDialogCreate = C3123g.m16382a(this).setMessage(R.string.clm_meeting_laser_pointer_request_failed).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).setCancelable(false).create();
            alertDialogCreate.show();
            TextView textView = (TextView) alertDialogCreate.findViewById(android.R.id.message);
            if (textView != null) {
                textView.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t16dp));
                textView.setGravity(17);
            }
            Button button = alertDialogCreate.getButton(-1);
            if (button != null) {
                button.setTextSize(0, Globals.m7374X0().getDimensionPixelSize(R.dimen.t16dp));
                button.setGravity(17);
            }
        } else if (i9 == 200) {
            m6864tf();
        } else if (i9 >= 1 && i9 < 200) {
            z8 = true;
        }
        this.f6641X1 = z8;
        m6870uf(z8);
        this.f6635W1.m23375i(z8);
        this.f6603R.setSwipeable(!z8);
        if (this.f6641X1) {
            m6862ta(true);
        } else if (i9 == 201) {
            m6686Nf(true);
        }
    }

    /* renamed from: F8 */
    public final void m6630F8() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "]----addNileNetworkRenderer IN----");
        this.f6617T1.m4925Z2(0, this.f6621U.f7080c);
        this.f6617T1.m4925Z2(1, this.f6627V.f7080c);
        this.f6617T1.m4925Z2(2, this.f6633W.f7080c);
        this.f6617T1.m4943h8(this.f6653Z1);
        Log.i("MeetingActivity", "NileNetwork instance instantiated.");
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "]----addNileNetworkRenderer OUT----");
    }

    /* renamed from: F9 */
    public final int m6631F9(int i9) {
        return i9 == 1 ? m6606B9() : i9 == m6671L9() ? m6695P9() : m6624E9();
    }

    /* renamed from: Fa */
    public final void m6632Fa() {
        this.f6544H0 = (ViewGroup) findViewById(R.id.meetingInviteLayout);
        this.f6550I0 = (EmojiconTextView) findViewById(R.id.meetingInviteStatus);
        this.f6556J0 = findViewById(R.id.btnMeetingInviteReceiveVideoCall);
        this.f6562K0 = findViewById(R.id.btnMeetingInviteReceiveAudioCall);
        this.f6568L0 = findViewById(R.id.btnMeetingInviteSpeaker);
        this.f6574M0 = findViewById(R.id.btnMeetingInviteHangUp);
        this.f6580N0 = (TextView) findViewById(R.id.textMeetingInviteHangUp);
        this.f6706h1 = findViewById(R.id.btnMeetingRejectMessage);
        this.f6713i1 = (ImageView) findViewById(R.id.status_icon);
        this.f6556J0.setSelected(true);
        Group group = this.f6808w;
        if (group != null) {
            if (this.f6795u == null || this.f6820y != InviteCallType.CALLEE || this.f6826z) {
                m6858se(group.f13724k, this.f6826z, (ImageView) findViewById(R.id.meetingInviteeAvatar));
                ((EmojiconTextView) findViewById(R.id.meetingInviteeName)).setText(this.f6808w.f13717d);
                ((EmojiconTextView) findViewById(R.id.meetingInviteeGroup)).setText("");
            } else {
                C6127a.m23469j(this, (ImageView) findViewById(R.id.meetingInviteeAvatar), this.f6795u);
                ((EmojiconTextView) findViewById(R.id.meetingInviteeName)).setText(this.f6795u.m15620a());
                ((EmojiconTextView) findViewById(R.id.meetingInviteeGroup)).setText(getString(R.string.clm_invite_from_group, this.f6808w.f13717d));
            }
        }
        if (this.f6534F2) {
            Group group2 = this.f6808w;
            if (group2 == null || C5170o0.m20170e(group2.f13724k)) {
                m6858se("", true, (ImageView) findViewById(R.id.meetingInviteeAvatar));
            }
            String str = this.f6546H2;
            if (!C5170o0.m20170e(this.f6540G2) && !this.f6552I2) {
                str = this.f6546H2 + "(#" + this.f6540G2 + ")";
            }
            ((EmojiconTextView) findViewById(R.id.meetingInviteeName)).setText(str);
            ((EmojiconTextView) findViewById(R.id.meetingInviteeGroup)).setText("");
        }
        this.f6544H0.setOnClickListener(new View.OnClickListener() { // from class: p2.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeetingActivity.m6277Xb(view);
            }
        });
        this.f6556J0.setOnClickListener(new View.OnClickListener() { // from class: p2.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                this.f20375b.m6288Yb(view);
            }
        });
        this.f6562K0.setOnClickListener(new View.OnClickListener() { // from class: p2.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException {
                this.f20413b.m6299Zb(view);
            }
        });
        this.f6568L0.setOnClickListener(this.f6807v4);
        this.f6574M0.setOnClickListener(this.f6825y4);
        this.f6706h1.setOnClickListener(this.f6801u5);
    }

    /* renamed from: Fe */
    public final void m6633Fe(String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (this.f6603R.getCurrentItem() != 1 && this.f6603R.getCurrentItem() != m6671L9()) {
            m6734Vg();
        } else if ("android native back button".equals(str) && this.f6513C) {
            m6668Kf(str);
        } else {
            m6827n9(str);
        }
        m6732Ve();
    }

    /* renamed from: Ff */
    public final void m6634Ff(boolean z8) {
        final Dialog dialogM16386e = C3123g.m16386e(this, "", getString(R.string.upload_file_number_is_exceeded_for_basic_users) + "\n" + getString(R.string.upgrade_to_pro_suggestion), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        if (z8) {
            textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
            textView2.setText(getString(R.string.upgrade_pro_no_free_trial));
            textView2.setTypeface(textView2.getTypeface(), 1);
            textView2.setTextSize(2, 16.0f);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.l1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20506b.m6567wd(dialogM16386e, view);
                }
            });
        } else {
            textView2.setVisibility(8);
        }
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.ok);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16386e.dismiss();
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: Fg */
    public final void m6635Fg() {
        m6699Pg();
        if (this.f6822y1 == null) {
            this.f6822y1 = (Vibrator) getSystemService("vibrator");
        }
        if (Build.VERSION.SDK_INT < 29) {
            this.f6822y1.vibrate(new long[]{0, 1000, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS}, 0);
            return;
        }
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setContentType(4).setUsage(4).build();
        this.f6822y1.vibrate(VibrationEffect.createWaveform(new long[]{0, 1000, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS}, 0), audioAttributesBuild);
    }

    /* renamed from: Fh */
    public final void m6636Fh() {
        int currentItem = this.f6603R.getCurrentItem();
        boolean z8 = currentItem == 1 || currentItem == m6671L9();
        TextView textView = (TextView) findViewById(R.id.extra_message);
        if (!z8) {
            this.f6768q0.setVisibility(8);
            this.f6508B0.setVisibility(8);
            textView.setVisibility(8);
            return;
        }
        this.f6768q0.setVisibility(0);
        if (this.f6561K || this.f6567L) {
            this.f6768q0.setText(R.string.clm_status_connecting);
            this.f6508B0.setVisibility(8);
            return;
        }
        if (this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) != 1 || this.f6603R.getCurrentItem() != 1) {
            this.f6768q0.setText("");
            this.f6508B0.setVisibility(8);
            if (this.f6778r3) {
                return;
            }
            textView.setVisibility(8);
            return;
        }
        if (this.f6826z && !this.f6513C && this.f6820y == InviteCallType.CALLEE) {
            Group group = this.f6808w;
            this.f6768q0.setText(getString(R.string.wait_for_caller_connect, group != null ? group.f13717d : ""));
            this.f6508B0.setVisibility(8);
            return;
        }
        if (this.f6588O2 == BreakoutRoomState.CHILD) {
            this.f6768q0.setText("");
            this.f6508B0.setVisibility(8);
            textView.setText(R.string.breakout_room_only_one);
            textView.setVisibility(0);
            return;
        }
        if (this.f6778r3 || this.f6559J3) {
            this.f6768q0.setText("");
            this.f6508B0.setVisibility(8);
        } else if (!this.f6823y2 || this.f6618T2) {
            this.f6768q0.setText(R.string.clm_invite_people_join);
            this.f6508B0.setVisibility(0);
        } else {
            this.f6768q0.setText(R.string.clm_invite_people_join);
            this.f6508B0.setVisibility(8);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: G0 */
    public void mo5107G0(int i9, String[] strArr, String[] strArr2, String str) {
        Log.d("MeetingActivity", "[FU] onFileUploadPrepared: " + i9 + " / expiryStr: " + str);
        m6799ia(i9);
        UploadMultipleChatMediaHelperQueue.m16892F().m16923J(i9, strArr, strArr2, str);
    }

    /* renamed from: G8 */
    public final boolean m6637G8(C0012b c0012b) {
        Iterator<Integer> it = this.f6655Z3.iterator();
        while (it.hasNext()) {
            if (c0012b.f66b.f63b == it.next().intValue()) {
                ULogUtility.m16670f("MeetingActivity", "User " + c0012b.f66b.f63b + " already pin");
                return false;
            }
        }
        int size = this.f6655Z3.size();
        int i9 = this.f6674c4;
        if (size >= i9) {
            Integer num = this.f6655Z3.get(i9 - 1);
            this.f6677d0.m7042l(num.intValue()).m73C(false);
            this.f6655Z3.remove(num);
        }
        this.f6655Z3.add(0, Integer.valueOf(c0012b.f66b.f63b));
        return true;
    }

    /* renamed from: G9 */
    public final Animator.AnimatorListener m6638G9(C1376f c1376f) {
        return new C1317j(c1376f);
    }

    /* renamed from: Ga */
    public final void m6639Ga(ImageView imageView, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        marginLayoutParams.height = i9;
        marginLayoutParams.width = i9;
        marginLayoutParams.bottomMargin = i10;
        imageView.requestLayout();
    }

    /* renamed from: Ge */
    public final void m6640Ge(View view) {
        BreakoutRoomState breakoutRoomState;
        if (C5179r0.m20246a()) {
            return;
        }
        BreakoutRoomState breakoutRoomState2 = this.f6588O2;
        BreakoutRoomState breakoutRoomState3 = BreakoutRoomState.PARENT;
        if (breakoutRoomState2 == breakoutRoomState3 || breakoutRoomState2 == (breakoutRoomState = BreakoutRoomState.NONE)) {
            Log.d("MeetingActivity", "onClickBreakoutRoom / enter breakout room");
            m6679Me();
            return;
        }
        if (breakoutRoomState2 == BreakoutRoomState.CHILD) {
            Log.d("MeetingActivity", "onClickBreakoutRoom / back to main session room");
            this.f6680d3 = true;
            Intent intent = new Intent(this, (Class<?>) BreakoutRoomTransitionActivity.class);
            intent.putExtra("meetingId", this.f6594P2);
            intent.putExtra("password", this.f6718j);
            intent.putExtra("ltiToken", this.f6687e3);
            intent.putExtra("type", this.f6732l);
            intent.putExtra("displayName", this.f6774r);
            if (this.f6654Z2 < 10) {
                intent.putExtra("breakoutRoomState", breakoutRoomState);
            } else {
                intent.putExtra("breakoutRoomState", breakoutRoomState3);
            }
            intent.putExtra("isMicrophoneOn", this.f6549I);
            intent.putExtra("isCameraOn", this.f6555J);
            intent.putExtra("isSpeakerOn", this.f6543H);
            intent.setFlags(1073741824);
            startActivity(intent);
            finish();
        }
    }

    /* renamed from: Gf */
    public final void m6641Gf(boolean z8) {
        final Dialog dialogM16386e = C3123g.m16386e(this, "", getString(R.string.upload_basic_user_file_size_is_exceeded) + "\n" + getString(R.string.upgrade_to_pro_suggestion), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        if (z8) {
            textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
            textView2.setText(getString(R.string.upgrade_pro_no_free_trial));
            textView2.setTypeface(textView2.getTypeface(), 1);
            textView2.setTextSize(2, 16.0f);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.i1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20467b.m6588yd(dialogM16386e, view);
                }
            });
        } else {
            textView2.setVisibility(8);
        }
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.ok);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16386e.dismiss();
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: Gg */
    public final void m6642Gg() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] start waitCallerConnectTimer");
        m7366I0().removeCallbacks(this.f6751n4);
        m7366I0().postDelayed(this.f6751n4, 20000L);
    }

    /* renamed from: Gh */
    public final void m6643Gh() {
        if (this.f6588O2 == BreakoutRoomState.CHILD) {
            this.f6775r0.setText(this.f6630V2);
            return;
        }
        if (this.f6778r3) {
            this.f6775r0.setText("");
        } else {
            if (C5170o0.m20170e(this.f6711i) || m6507rb(this.f6711i)) {
                return;
            }
            this.f6775r0.setText(C5170o0.m20172g(this.f6711i, 3, '-'));
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: H */
    public void mo5108H(final List<C0012b> list, final List<C0012b> list2) throws Resources.NotFoundException {
        NetQuality.Quality quality;
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onUpdateParticipants updated size:" + list.size() + " removed size:" + list2.size());
        this.f6810w1.removeAll(list2);
        if (this.f6581N1 && list2.contains(this.f6804v1)) {
            m6667Ke();
        }
        if (this.f6826z && this.f6513C) {
            Iterator<C0012b> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C0012b next = it.next();
                if (next != this.f6797u1) {
                    NetQuality.Quality qualityM89h = next.m89h();
                    NetQuality.Quality qualityM84c = next.m84c();
                    if (next.m93l() && (qualityM89h != (quality = NetQuality.Quality.UNAVAILABLE) || qualityM84c != quality)) {
                        NetQuality.Quality quality2 = NetQuality.Quality.ACCEPTABLE;
                        if (qualityM89h.compareTo(quality2) > 0 || qualityM84c.compareTo(quality2) > 0) {
                            this.f6585O = true;
                        } else {
                            m6852ra();
                            this.f6585O = false;
                        }
                    }
                }
            }
        }
        Runnable runnable = new Runnable() { // from class: p2.g0
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException, Resources.NotFoundException {
                this.f20440b.m6289Yc(list, list2);
            }
        };
        if (m6781eb(list)) {
            runnable.run();
        } else {
            new AsyncTaskC1303e0(list, runnable).executeOnExecutor(this.f6697g, new Void[0]);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: H0 */
    public void mo5109H0(NileNetwork.HostControlStatus hostControlStatus, int i9) {
        if (hostControlStatus == NileNetwork.HostControlStatus.OLD_USER) {
            m6704Qf(i9);
        }
    }

    /* renamed from: H3 */
    public final void m6644H3() {
        AudioManager audioManager = this.f6790t1;
        if (audioManager == null || !this.f6563K1) {
            return;
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] abandon audio focus for ringtone: " + (audioManager.abandonAudioFocus(this.f6786s4) == 1));
        this.f6563K1 = false;
    }

    /* renamed from: H8 */
    public final void m6645H8(boolean z8) {
        View view = this.f6541G3;
        if (view != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, (int) (z8 ? getResources().getDimension(R.dimen.t100dp) : getResources().getDimension(R.dimen.t150dp)), layoutParams.rightMargin, layoutParams.bottomMargin);
            this.f6541G3.setLayoutParams(layoutParams);
        }
        this.f6577M3.setVisibility(z8 ? 8 : 0);
        this.f6583N3.setVisibility(z8 ? 0 : 8);
    }

    /* renamed from: H9 */
    public final float m6646H9(int i9) {
        return m6766bb() ? m6742X9() / 2 : (m6742X9() - i9) / 2;
    }

    /* renamed from: Ha */
    public final void m6647Ha() throws Resources.NotFoundException {
        this.f6628V0 = (ViewGroup) findViewById(R.id.btn_meeting_laser_pointer_portrait);
        this.f6634W0 = (ViewGroup) findViewById(R.id.btn_meeting_laser_pointer_landscape);
        this.f6699g1 = (TextView) findViewById(R.id.meeting_laser_pointer_landscape_text);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.clm_meeting_button_size_desktop_share);
        int i9 = (dimensionPixelOffset * 280) / 180;
        int i10 = (dimensionPixelOffset - i9) / 2;
        m6639Ga((ImageView) this.f6628V0.getChildAt(0), i9, i10);
        m6639Ga((ImageView) this.f6634W0.getChildAt(0), i9, i10);
        m6654Ia(this.f6628V0, i10);
        m6654Ia(this.f6634W0, i10);
    }

    /* renamed from: He */
    public final void m6648He() {
        this.f6778r3 = true;
        this.f6640X0.setEnabled(false);
        this.f6720j1.setEnabled(false);
        this.f6603R.setSwipeable(false);
        m6891ya();
        this.f6733l0.setVisibility(8);
        this.f6586O0.setVisibility(8);
        this.f6609S.setVisibility(8);
        this.f6782s0.setVisibility(8);
        m6611Bh();
        m6902zh();
        m6636Fh();
        View view = this.f6734l1;
        if (view != null) {
            view.setVisibility(8);
        }
        m6643Gh();
        m6686Nf(true);
    }

    /* renamed from: Hf */
    public final void m6649Hf() {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_meeting_breakroom_leave);
        dialogM16384c.findViewById(R.id.item_return_to_main).setOnClickListener(new View.OnClickListener() { // from class: p2.c2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20378b.m6030Ad(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_leave_meeting).setOnClickListener(new View.OnClickListener() { // from class: p2.d2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                this.f20398b.m6041Bd(dialogM16384c, view);
            }
        });
        CLUtility.m16578q2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* renamed from: Hg */
    public final void m6650Hg() {
        if (this.f6826z && !this.f6513C && this.f6820y == InviteCallType.CALLEE) {
            m6642Gg();
            m7366I0().removeCallbacks(this.f6731k5);
        }
    }

    /* renamed from: Hh */
    public final void m6651Hh() {
        boolean zIsSelected = this.f6754o0.isSelected();
        boolean z8 = false;
        if (this.f6616T0.getCount() > 0 && this.f6616T0.m7122k() != this.f6533F1) {
            z8 = true;
        }
        if (zIsSelected != z8) {
            this.f6754o0.setSelected(z8);
            if (z8) {
                if (this.f6603R.getCurrentItem() == 1 || this.f6603R.getCurrentItem() == m6671L9()) {
                    m6686Nf(true);
                }
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1081o7
    /* renamed from: I */
    public void mo5041I() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeAnsweredFromAnotherDevice");
        if (this.f6820y == InviteCallType.CALLEE) {
            this.f6557J1 = true;
            m6839p9("NileNetwork - onCalleeAnsweredFromAnotherDevice");
            return;
        }
        ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] caller received onCalleeAnsweredFromAnotherDevice, skip it.");
    }

    /* renamed from: I8 */
    public final void m6652I8(int i9) throws Resources.NotFoundException {
        if (this.f6651Z.getWidth() == 0 || this.f6651Z.getHeight() == 0) {
            Log.w("MeetingActivity", "MeetingContainer width and height is incorrect, not adjustFloatingView");
            return;
        }
        m6617Ch();
        if (i9 == 0) {
            m6886xe(false);
            return;
        }
        if (i9 == 1) {
            if (!this.f6581N1) {
                m6872uh(1);
            }
            m6893ye(false);
        } else if (i9 != m6671L9()) {
            if (i9 == m6754Z9()) {
                m6602Ae(false);
            }
        } else {
            m6899ze(false);
            if (this.f6655Z3.size() > 0) {
                m6826mh();
            }
        }
    }

    /* renamed from: I9 */
    public final float m6653I9(int i9) {
        return ((int) (i9 / 2.0f)) + m6660J9();
    }

    /* renamed from: Ia */
    public final void m6654Ia(View view, int i9) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.topMargin += i9;
        marginLayoutParams.leftMargin += i9;
        marginLayoutParams.rightMargin += i9;
        view.requestLayout();
    }

    /* renamed from: Ie */
    public final void m6655Ie() throws Resources.NotFoundException {
        this.f6778r3 = false;
        this.f6771q3 = false;
        this.f6640X0.setEnabled(true);
        this.f6720j1.setEnabled(true);
        this.f6603R.setSwipeable(true);
        m6807jg();
        ((TextView) findViewById(R.id.extra_message)).setVisibility(8);
        m6652I8(this.f6603R.getCurrentItem());
        View view = this.f6734l1;
        if (view != null) {
            view.setVisibility(this.f6569L1 ? 0 : 8);
        }
        Dialog dialog = this.f6812w3;
        if (dialog != null && dialog.isShowing()) {
            this.f6812w3.dismiss();
        }
        m6643Gh();
    }

    /* renamed from: If */
    public final void m6656If(String str, String str2) {
        if (this.f6778r3) {
            return;
        }
        if (this.f6708h3 == null) {
            this.f6708h3 = C3123g.m16383b(this, getString(R.string.clm_meeting_broadcast_title, str2), str);
        }
        this.f6708h3.setCancelable(false);
        ((TextView) this.f6708h3.findViewById(R.id.dialogTitle)).setText(getString(R.string.clm_meeting_broadcast_title, str2));
        ((TextView) this.f6708h3.findViewById(R.id.dialogContent)).setText(str);
        TextView textView = (TextView) this.f6708h3.findViewById(R.id.v_btn);
        textView.setText(getString(R.string.close));
        textView.setOnClickListener(new View.OnClickListener() { // from class: p2.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20551b.m6052Cd(view);
            }
        });
        this.f6708h3.show();
    }

    /* renamed from: Ig */
    public final void m6657Ig() {
        View view = this.f6698g0;
        if (view != null) {
            view.removeCallbacks(this.f6731k5);
        }
        C1376f c1376f = this.f6621U;
        if (c1376f != null) {
            c1376f.m7138a();
        }
        C1376f c1376f2 = this.f6627V;
        if (c1376f2 != null) {
            c1376f2.m7138a();
        }
        C1376f c1376f3 = this.f6633W;
        if (c1376f3 != null) {
            c1376f3.m7138a();
        }
        m6717T8();
    }

    /* renamed from: Ih */
    public final void m6658Ih() {
        Vibrator vibrator = (Vibrator) getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(300L, -1));
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: J */
    public void mo5110J(NileNetwork.HostControlStatus hostControlStatus, int i9) {
        if (hostControlStatus == NileNetwork.HostControlStatus.OLD_USER) {
            m6704Qf(i9);
        }
    }

    /* renamed from: J8 */
    public final void m6659J8(int i9) {
        if (i9 == m6754Z9()) {
            m6768bg(true);
        } else {
            m6768bg(false);
        }
    }

    /* renamed from: J9 */
    public final int m6660J9() {
        return (!m6761ab() || this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) <= 3) ? (this.f6605R1 + this.f6733l0.getHeight()) - m6665K9() : this.f6605R1;
    }

    /* renamed from: Ja */
    public final void m6661Ja() throws Resources.NotFoundException {
        this.f6603R = (com.cyberlink.widget.ViewPager) findViewById(R.id.meetingViewpager);
        m6737Wa();
        this.f6621U = new C1376f((FrameLayout) findViewById(R.id.remoteViewPanel0));
        this.f6627V = new C1376f((FrameLayout) this.f6526E0.findViewById(R.id.remoteViewPanel1));
        this.f6633W = new C1376f((FrameLayout) this.f6526E0.findViewById(R.id.remoteViewPanel2));
        this.f6653Z1 = (SurfaceViewRenderer) findViewById(R.id.localRendererView);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.meetingContainer);
        this.f6651Z = viewGroup;
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC1311h());
        this.f6604R0 = findViewById(R.id.meetingScreenOff);
        this.f6639X.add(this.f6621U);
        this.f6639X.add(this.f6627V);
        this.f6639X.add(this.f6633W);
        for (int i9 = 0; i9 < this.f6639X.size(); i9++) {
            this.f6645Y.add(null);
        }
        this.f6621U.f7091n = AspectRatio.PORTRAIT;
        C1376f c1376f = this.f6627V;
        AspectRatio aspectRatio = AspectRatio.LANDSCAPE;
        c1376f.f7091n = aspectRatio;
        this.f6633W.f7091n = aspectRatio;
        this.f6604R0.setOnClickListener(new View.OnClickListener() { // from class: p2.x3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeetingActivity.m6310ac(view);
            }
        });
        m6632Fa();
        m6719Ta();
        m6731Va();
        m6625Ea();
        m6678Ma();
        m6702Qa();
        m6619Da();
        m6623Dh();
        m6620De();
        this.f6750n3 = findViewById(R.id.raise_hand_notify);
    }

    /* renamed from: Je */
    public final void m6662Je(String str, long j9) {
        if (C5179r0.m20246a()) {
            return;
        }
        DialogC3133q dialogC3133qM16410a = new DialogC3133q.b(this).m16413d(1500L).m16410a();
        dialogC3133qM16410a.show();
        new AsyncTaskC1316i1(str, j9, dialogC3133qM16410a).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: Jg */
    public final void m6663Jg() {
        m7366I0().removeCallbacks(this.f6744m4);
    }

    /* renamed from: K8 */
    public final void m6664K8() {
        if (!this.f6681d4) {
            Log.d("MeetingActivity", "[adjustPinUser] ParticipantList is not ready");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < this.f6655Z3.size(); i9++) {
            Integer num = this.f6655Z3.get(i9);
            C0012b c0012bM7042l = this.f6677d0.m7042l(num.intValue());
            if (c0012bM7042l == null) {
                this.f6655Z3.remove(num);
                arrayList.add(num);
            } else {
                c0012bM7042l.m73C(true);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i10 = 0; i10 < this.f6661a4.size(); i10++) {
            Integer num2 = this.f6661a4.get(i10);
            if (this.f6677d0.m7042l(num2.intValue()) != null) {
                this.f6661a4.remove(num2);
                arrayList2.add(num2);
            }
        }
        int size = this.f6674c4 - this.f6655Z3.size();
        if (arrayList2.size() > 0 && size > 0) {
            int iMin = Math.min(arrayList2.size(), size);
            for (int i11 = 0; i11 < iMin; i11++) {
                C0012b c0012bM7042l2 = this.f6677d0.m7042l(((Integer) arrayList2.get(i11)).intValue());
                if (c0012bM7042l2 != null) {
                    c0012bM7042l2.m73C(true);
                    this.f6655Z3.add((Integer) arrayList2.get(i11));
                }
            }
        }
        if (arrayList.size() > 0 || (arrayList2.size() > 0 && size > 0)) {
            this.f6661a4.addAll(arrayList);
            m6881wf();
        }
    }

    /* renamed from: K9 */
    public final int m6665K9() {
        return (int) getResources().getDimension(R.dimen.clm_meeting_gallery_header_padding);
    }

    /* renamed from: Ka */
    public final void m6666Ka() {
        this.f6549I = true;
        this.f6543H = !m6749Ya();
        this.f6555J = true ^ m6749Ya();
        if (this.f6623U1.m5058i()) {
            this.f6543H = false;
        }
    }

    /* renamed from: Ke */
    public final void m6667Ke() throws Resources.NotFoundException {
        if (this.f6581N1) {
            this.f6581N1 = false;
            this.f6647Y1 = false;
            m6786ff(false);
            this.f6691f0.setVisibility(8);
            this.f6740m0.setVisibility(0);
            this.f6747n0.setVisibility(this.f6555J ? 0 : 8);
            this.f6754o0.setVisibility(0);
            m6783eh();
            if (this.f6603R.getCurrentItem() == 1) {
                this.f6782s0.setAlpha(1.0f);
            }
            m6782ef();
            this.f6627V.f7080c.setVisibility(8);
            this.f6691f0.findViewById(R.id.desktopCameraVideoContainer).setVisibility(8);
            this.f6691f0.findViewById(R.id.desktopCameraAvatarContainer).setVisibility(8);
            this.f6609S.setVisibility(this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) > 2 ? 0 : 8);
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_portrait_bg);
            SurfaceViewRenderer surfaceViewRenderer = this.f6621U.f7080c;
            RendererCommon.ScalingType scalingType = RendererCommon.ScalingType.SCALE_ASPECT_FILL;
            RendererCommon.ScalingType scalingType2 = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
            surfaceViewRenderer.setScalingType(scalingType, scalingType2);
            this.f6627V.f7080c.setScalingType(scalingType, scalingType2);
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_portrait_bg);
            m6608Be(this.f6627V.f7080c, (ViewGroup) this.f6691f0.findViewById(R.id.desktopCameraVideoContainer), this.f6627V.f7079b, false);
            m6826mh();
            if (this.f6603R.getCurrentItem() == 1) {
                m6851r9();
                this.f6617T1.m4929a8(1);
                m7366I0().postDelayed(new Runnable() { // from class: p2.q2
                    @Override // java.lang.Runnable
                    public final void run() throws Resources.NotFoundException {
                        this.f20580b.m6234Tc();
                    }
                }, 1000L);
            } else {
                m6860sh();
            }
            m6652I8(this.f6603R.getCurrentItem());
            m6741X8(true);
            m6611Bh();
            m6902zh();
        }
        this.f6603R.setSwipeable(true);
        this.f6641X1 = false;
        this.f6635W1.m23375i(false);
        m6870uf(false);
        this.f6621U.f7080c.setOnTouchListener(null);
    }

    /* renamed from: Kf */
    public final void m6668Kf(final String str) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.clm_leave_meeting_dialog_message));
        builderM16382a.setPositiveButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        builderM16382a.setNegativeButton(R.string.chat_group_more_dialog_leave_chat, new DialogInterface.OnClickListener() { // from class: p2.w3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                this.f20640b.m6074Ed(str, dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.show();
        alertDialogCreate.getButton(-2).setTextColor(getResources().getColor(R.color.you_color_delete_red));
    }

    /* renamed from: Kg */
    public final void m6669Kg() {
        this.f6617T1.m4939f8(false, null, new C1329n());
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: L */
    public void mo5111L() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeRefused");
        if (this.f6820y != InviteCallType.CALLER) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "]  skip onCalleeRefused, because I am not caller.");
            return;
        }
        if (this.f6826z) {
            m6834oa("reject", "NileNetwork - onCalleeRefused");
            return;
        }
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive onCalleeRefused, but is not dual meeting. skip it");
    }

    /* renamed from: L8 */
    public final void m6670L8(boolean z8) {
        if (C5170o0.m20170e(this.f6767q)) {
            C5187v0.m20268d("Face server token is empty");
            return;
        }
        this.f6700g2 = z8;
        if (z8) {
            m7366I0().post(new RunnableC6018q3(this));
        } else {
            this.f6621U.f7087j.setVisibility(8);
            m7366I0().removeCallbacks(new RunnableC6018q3(this));
        }
    }

    /* renamed from: L9 */
    public final int m6671L9() {
        return (!this.f6587O1 || this.f6581N1) ? -1 : 2;
    }

    /* renamed from: La */
    public final void m6672La(Bundle bundle) {
        if (bundle != null) {
            C2027b c2027b = (C2027b) getSupportFragmentManager().mo1848e("MessageInput");
            this.f6521D1 = c2027b;
            c2027b.m12069U(this.f6518C4);
            return;
        }
        this.f6521D1 = new C2027b();
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("enableChatPlus", true);
        bundle2.putBoolean("enableChatSticker", false);
        bundle2.putBoolean("enableChatMeeting", false);
        bundle2.putBoolean("enableMeetingShareDoc", true);
        bundle2.putParcelable("Group", this.f6808w);
        this.f6521D1.setArguments(bundle2);
        this.f6521D1.m12069U(this.f6518C4);
        getSupportFragmentManager().mo1844a().m1981c(R.id.menuFragmentContainer, this.f6521D1, "MessageInput").mo1799n(this.f6521D1).mo1794h();
    }

    /* renamed from: Le */
    public final void m6673Le() throws Resources.NotFoundException {
        if (!this.f6581N1) {
            if (this.f6559J3) {
                ULogUtility.m16683s("MeetingActivity", "[DesktopSharing] onReceiveDesktopSharingStart() / someone has been sharing desktop / stop local desktop sharing!");
                m6669Kg();
            }
            this.f6617T1.m4929a8(2);
            this.f6581N1 = true;
            this.f6570L2 = true;
            m6786ff(true);
            if (this.f6534F2) {
                this.f6698g0.setOnTouchListener(this.f6752n5);
                this.f6504A2.setVisibility(8);
                this.f6504A2.setSelected(false);
                this.f6829z2.setVisibility(8);
                findViewById(R.id.meetingUserInfoLayout).setVisibility(8);
                this.f6733l0.setVisibility(0);
            }
            this.f6653Z1.setVisibility(8);
            this.f6684e0.setVisibility(8);
            this.f6621U.f7081d.setVisibility(8);
            this.f6627V.f7078a.setVisibility(8);
            this.f6633W.f7078a.setVisibility(8);
            this.f6633W.f7080c.setVisibility(8);
            this.f6747n0.setVisibility(8);
            this.f6609S.setVisibility(8);
            m6783eh();
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_landscape_bg);
            SurfaceViewRenderer surfaceViewRenderer = this.f6621U.f7080c;
            RendererCommon.ScalingType scalingType = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
            surfaceViewRenderer.setScalingType(scalingType);
            this.f6627V.f7080c.setScalingType(scalingType);
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_landscape_bg);
            m6851r9();
            C1376f c1376f = this.f6627V;
            m6608Be(c1376f.f7080c, c1376f.f7079b, (ViewGroup) this.f6691f0.findViewById(R.id.desktopCameraVideoContainer), true);
            m7366I0().postDelayed(new Runnable() { // from class: p2.f2
                @Override // java.lang.Runnable
                public final void run() throws Resources.NotFoundException {
                    this.f20430b.m6245Uc();
                }
            }, 100L);
            m6741X8(getResources().getConfiguration().orientation == 1);
        }
        this.f6621U.f7080c.setOnTouchListener(this.f6635W1);
    }

    /* renamed from: Lf */
    public final void m6674Lf(boolean z8, boolean z9) {
        int i9 = 0;
        this.f6535F3.setVisibility((z8 && z9) ? 0 : 8);
        if (z9) {
            if (this.f6581N1) {
                i9 = 1;
            } else if (!z8) {
                i9 = -1;
            }
            this.f6547H3 = i9;
        }
    }

    /* renamed from: Lg */
    public final void m6675Lg() throws IllegalStateException {
        MediaPlayer mediaPlayer = this.f6503A1;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.f6503A1.release();
            this.f6503A1 = null;
        }
    }

    /* renamed from: M8 */
    public final void m6676M8() {
        ImageView imageView = this.f6803v0;
        if (imageView != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, (int) (m6766bb() ? getResources().getDimension(R.dimen.t100dp) : getResources().getDimension(R.dimen.t70dp)), layoutParams.rightMargin, layoutParams.bottomMargin);
            this.f6803v0.setLayoutParams(layoutParams);
        }
        int i9 = 8;
        this.f6592P0.setVisibility(m6766bb() ? 0 : 8);
        this.f6598Q0.setVisibility(m6766bb() ? 8 : 0);
        boolean z8 = this.f6586O0.getVisibility() == 0;
        this.f6809w0.setVisibility((!m6766bb() || this.f6559J3) ? 8 : 0);
        EmojiconTextView emojiconTextView = this.f6782s0;
        if ((!m6766bb() || !z8) && !this.f6559J3) {
            i9 = 0;
        }
        emojiconTextView.setVisibility(i9);
    }

    /* renamed from: M9 */
    public final int m6677M9(AspectRatio aspectRatio) {
        return m6689O9();
    }

    /* renamed from: Ma */
    public final void m6678Ma() {
        this.f6532F0.findViewById(R.id.btn_meeting_back).setOnClickListener(new View.OnClickListener() { // from class: p2.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20505b.m6322bc(view);
            }
        });
        this.f6610S0 = (ListView) this.f6532F0.findViewById(R.id.messageListView);
        this.f6622U0 = (TextView) this.f6532F0.findViewById(R.id.newMessageText);
        this.f6514C0 = (EmojiconEditText) this.f6532F0.findViewById(R.id.inputEditText);
        this.f6520D0 = (Button) this.f6532F0.findViewById(R.id.sendBtn);
        this.f6532F0.findViewById(R.id.sendBtn).setVisibility(0);
        this.f6532F0.findViewById(R.id.sendMeetingInviteBtn).setVisibility(8);
        this.f6532F0.findViewById(R.id.stickerBtn).setVisibility(8);
        this.f6532F0.findViewById(R.id.chatPlusBtn).setVisibility(8);
        this.f6514C0.setFocusableInTouchMode(false);
        C5169o.m20162g(this.f6514C0, true);
        this.f6610S0.setOnScrollListener(this.f6512B4);
        this.f6622U0.setOnClickListener(new View.OnClickListener() { // from class: p2.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20515b.m6333cc(view);
            }
        });
        this.f6527E1 = new C4619d(this, new C1356w());
        this.f6610S0.setOnTouchListener(new View.OnTouchListener() { // from class: p2.n0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f20528b.m6345dc(view, motionEvent);
            }
        });
        C5179r0.m20247b(this.f6520D0, 1);
        this.f6520D0.setOnClickListener(new View.OnClickListener() { // from class: p2.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20552b.m6358ec(view);
            }
        });
        this.f6514C0.setOnClickListener(new View.OnClickListener() { // from class: p2.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20578b.m6384gc(view);
            }
        });
        this.f6610S0.setAdapter((ListAdapter) this.f6616T0);
    }

    /* renamed from: Me */
    public final void m6679Me() {
        this.f6625U3.setVisibility(0);
        this.f6625U3.findViewById(R.id.extraPageFakeStatusBar).setVisibility(0);
        ((TextView) this.f6625U3.findViewById(R.id.title)).setText(getString(R.string.breakout_room_title));
        RecyclerView recyclerView = (RecyclerView) this.f6625U3.findViewById(R.id.breakoutRoomPage);
        recyclerView.setVisibility(0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        C1337p1 c1337p1 = new C1337p1(this, null);
        this.f6694f3 = c1337p1;
        recyclerView.setAdapter(c1337p1);
        recyclerView.addItemDecoration(new C0476e(recyclerView.getContext(), linearLayoutManager.m2303n2()));
        this.f6625U3.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: p2.n1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20529b.m6300Zc(view);
            }
        });
    }

    /* renamed from: Mf */
    public final void m6680Mf(String str) {
        if (isFinishing()) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(str);
        builderM16382a.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: p2.h4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20462b.m6084Fd(dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    /* renamed from: Mg */
    public final void m6681Mg() throws IllegalStateException {
        MediaPlayer mediaPlayer = this.f6828z1;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.f6828z1.release();
            this.f6828z1 = null;
        }
        m6699Pg();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: N */
    public void mo5112N(C6382s c6382s) {
        this.f6635W1.m23374h(c6382s);
    }

    /* renamed from: N8 */
    public final void m6682N8() {
        if (this.f6617T1 != null) {
            ArrayList arrayList = new ArrayList();
            Iterator<C1262a> it = this.f6677d0.m7046p().iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().m5730b()));
            }
            if (arrayList.size() > 0) {
                this.f6617T1.m4928a3(arrayList);
            }
        }
    }

    /* renamed from: N9 */
    public final int m6683N9(AspectRatio aspectRatio) {
        float f9;
        float f10;
        float f11;
        int iM6689O9 = m6689O9();
        if (aspectRatio == AspectRatio.LANDSCAPE) {
            if (this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) < 4) {
                f10 = iM6689O9;
                f11 = 1.7777778f;
            } else {
                f10 = iM6689O9;
                f11 = 1.6f;
            }
            f9 = f10 * f11;
        } else {
            this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT);
            f9 = iM6689O9 * 0.75f;
        }
        return (int) f9;
    }

    /* renamed from: Na */
    public final void m6684Na() {
        C1260a.m5679q(Globals.m7388i0().m7506X(), this.f6711i, 0, new ArrayList(), new C1308g());
    }

    /* renamed from: Ne */
    public final void m6685Ne() {
        this.f6625U3.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() { // from class: p2.i4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20474b.m6311ad(view);
            }
        });
        C1260a.m5682t(Globals.m7388i0().m7506X(), Long.parseLong(this.f6711i), true).m15439e(new C1312h0());
    }

    /* renamed from: Nf */
    public final void m6686Nf(boolean z8) {
        int currentItem;
        if (this.f6641X1) {
            return;
        }
        m7366I0().removeCallbacks(this.f6731k5);
        m6717T8();
        m6859sf(true);
        long j9 = z8 ? 500L : 0L;
        this.f6733l0.animate().alpha(1.0f).setListener(this.f6738l5).setDuration(j9).start();
        this.f6600Q2.animate().alpha(1.0f).setDuration(j9).start();
        this.f6705h0.animate().translationY(BitmapDescriptorFactory.HUE_RED).alpha(1.0f).setDuration(j9).setInterpolator(this.f6703g5).start();
        this.f6719j0.animate().alpha(1.0f).setDuration(j9).start();
        this.f6796u0.animate().translationY(BitmapDescriptorFactory.HUE_RED).alpha(1.0f).setDuration(j9).setInterpolator(this.f6703g5).start();
        this.f6609S.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6703g5).start();
        if (this.f6581N1) {
            this.f6782s0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6703g5).alpha(1.0f).start();
            this.f6789t0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6703g5).alpha(1.0f).start();
        } else {
            this.f6782s0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6703g5).start();
            this.f6789t0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6703g5).start();
        }
        m6733Vf(j9);
        if (m6766bb()) {
            this.f6684e0.setTranslationY(this.f6705h0.getTranslationY());
            this.f6684e0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6703g5).start();
        }
        if (this.f6587O1 && ((currentItem = this.f6603R.getCurrentItem()) == 1 || currentItem == m6671L9())) {
            this.f6726k0.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).start();
        }
        m6750Ye();
    }

    /* renamed from: Ng */
    public final void m6687Ng() {
        RTCAudioManager rTCAudioManager = this.f6623U1;
        if (rTCAudioManager != null) {
            int iM5068s = rTCAudioManager.m5068s();
            this.f6623U1 = null;
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] Stop RTCAudioManager with saved mode: " + iM5068s);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: O */
    public void mo5113O(final C0012b c0012b) {
        if (c0012b != this.f6797u1) {
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnMeetingHadBeenClosed: " + c0012b.f66b.f63b);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setTitle(getString(R.string.clm_meeting_was_over));
            builderM16382a.setMessage(getString(R.string.clm_meeting_terminate_by_someone, c0012b.m86e()));
            builderM16382a.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: p2.v
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f20625b.m6028Ab(c0012b, dialogInterface, i9);
                }
            });
            builderM16382a.setCancelable(false);
            builderM16382a.create().show();
        }
    }

    /* renamed from: O8 */
    public final void m6688O8() {
        if (this.f6536F4) {
            return;
        }
        DeviceCapability.C1399a c1399a = this.f6629V1;
        if (c1399a != null && c1399a.m7321b() && this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) >= 50) {
            this.f6536F4 = true;
            C3123g.m16382a(this).setMessage(R.string.clm_meeting_performance_low_ux).setPositiveButton(R.string.clm_continue_in_phone, (DialogInterface.OnClickListener) null).setCancelable(false).create().show();
        }
    }

    /* renamed from: O9 */
    public final int m6689O9() {
        int i9;
        int iMin;
        int iM6742X9 = m6742X9();
        int iM6736W9 = m6736W9();
        int iM7044n = this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT);
        int iM6660J9 = iM6736W9 - m6660J9();
        float f9 = iM6742X9;
        int i10 = (int) (0.625f * f9);
        if (m6766bb()) {
            int iM6897z9 = iM6660J9 - ((int) m6897z9());
            if (iM7044n < 4) {
                i9 = (int) (f9 * 0.5625f);
                iMin = Math.min(i10, iM6897z9 / 2);
            } else {
                i9 = iM6897z9 / 3;
                iMin = Math.min(i10, i9);
            }
        } else if (iM7044n < 4) {
            i9 = (int) (((int) (f9 / 2.0f)) * 0.5625f);
            iMin = Math.min(i10 / 2, iM6660J9);
        } else {
            i9 = (iM6660J9 + 0) / 2;
            iMin = Math.min(i10 / 2, i9);
        }
        return Math.min(i9, iMin);
    }

    /* renamed from: Oa */
    public final void m6690Oa() {
        this.f6539G1 = ((PowerManager) getSystemService("power")).newWakeLock(1, "MeetingActivity");
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01d5  */
    /* renamed from: Oe */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m6691Oe(final C0012b c0012b) {
        boolean z8;
        boolean z9;
        TextView textView;
        if (c0012b == null || c0012b == this.f6797u1) {
            return;
        }
        boolean zM92k = c0012b.m92k();
        boolean zM98q = c0012b.m98q();
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_meeting_participant_more);
        ((TextView) dialogM16384c.findViewById(R.id.item_name)).setText(c0012b.m86e());
        TextView textView2 = (TextView) dialogM16384c.findViewById(R.id.item_lower_hand);
        Iterator<Integer> it = this.f6743m3.iterator();
        while (true) {
            if (!it.hasNext()) {
                z8 = false;
                break;
            } else {
                if (c0012b.f66b.f63b == it.next().intValue()) {
                    z8 = true;
                    break;
                }
            }
        }
        textView2.setVisibility((z8 && this.f6618T2) ? 0 : 8);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.f3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20431b.m6323bd(dialogM16384c, c0012b, view);
            }
        });
        final boolean zM95n = c0012b.m95n();
        TextView textView3 = (TextView) dialogM16384c.findViewById(R.id.item_pin_video);
        textView3.setVisibility((zM95n || this.f6581N1) ? 8 : 0);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.g3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20446b.m6334cd(dialogM16384c, c0012b, zM95n, view);
            }
        });
        TextView textView4 = (TextView) dialogM16384c.findViewById(R.id.item_unpin_video);
        textView4.setVisibility((!zM95n || this.f6581N1) ? 8 : 0);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: p2.h3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20458b.m6346dd(dialogM16384c, c0012b, zM95n, view);
            }
        });
        View viewFindViewById = dialogM16384c.findViewById(R.id.mute_microphone_layout);
        viewFindViewById.setVisibility((zM92k || !(this.f6618T2 || this.f6672c2)) ? 8 : 0);
        TextView textView5 = (TextView) viewFindViewById.findViewById(R.id.item_mute_microphone);
        textView5.setOnClickListener(new View.OnClickListener() { // from class: p2.i3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20471b.m6359ed(dialogM16384c, c0012b, view);
            }
        });
        TextView textView6 = (TextView) dialogM16384c.findViewById(R.id.item_unmute_microphone);
        textView6.setVisibility((zM92k && (this.f6618T2 || this.f6672c2)) ? 0 : 8);
        textView6.setOnClickListener(new View.OnClickListener() { // from class: p2.j3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20484b.m6372fd(dialogM16384c, c0012b, view);
            }
        });
        TextView textView7 = (TextView) dialogM16384c.findViewById(R.id.item_stop_recording);
        textView7.setVisibility((zM98q && this.f6618T2) ? 0 : 8);
        textView7.setOnClickListener(new View.OnClickListener() { // from class: p2.k3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20496b.m6385gd(dialogM16384c, c0012b, view);
            }
        });
        TextView textView8 = (TextView) dialogM16384c.findViewById(R.id.item_remove_from_meeting);
        textView8.setVisibility(this.f6618T2 ? 0 : 8);
        textView8.setOnClickListener(new View.OnClickListener() { // from class: p2.m3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20518b.m6397hd(dialogM16384c, c0012b, view);
            }
        });
        Iterator<Integer> it2 = this.f6736l3.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z9 = false;
                break;
            } else {
                if (c0012b.f66b.f63b == it2.next().intValue()) {
                    z9 = true;
                    break;
                }
            }
        }
        TextView textView9 = (TextView) dialogM16384c.findViewById(R.id.item_assign_host);
        textView9.setVisibility(this.f6618T2 && !z9 && (c0012b.f66b.f65d > 0L ? 1 : (c0012b.f66b.f65d == 0L ? 0 : -1)) > 0 ? 0 : 8);
        textView9.setOnClickListener(new View.OnClickListener() { // from class: p2.n3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20531b.m6409id(dialogM16384c, c0012b, view);
            }
        });
        TextView textView10 = (TextView) dialogM16384c.findViewById(R.id.item_remove_host);
        if (this.f6618T2 && z9) {
            textView = textView10;
            boolean z10 = c0012b.f66b.f65d > 0;
            TextView textView11 = textView;
            textView11.setVisibility(!z10 ? 0 : 8);
            textView11.setOnClickListener(new View.OnClickListener() { // from class: p2.o3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20545b.m6420jd(dialogM16384c, c0012b, view);
                }
            });
            TextView textView12 = (TextView) dialogM16384c.findViewById(R.id.item_move_to_waiting_room);
            textView12.setVisibility((this.f6618T2 || z9 || !this.f6785s3) ? 8 : 0);
            textView12.setOnClickListener(new View.OnClickListener() { // from class: p2.p3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20569b.m6431kd(dialogM16384c, c0012b, view);
                }
            });
            if (c0012b.m87f() == NileNetwork.PlatformType.Platform_PhoneLine) {
                viewFindViewById.setVisibility(8);
                textView6.setVisibility(8);
                textView7.setVisibility(8);
                textView9.setVisibility(8);
                textView11.setVisibility(8);
                textView3.setVisibility(8);
                textView4.setVisibility(8);
            }
            C5179r0.m20247b(textView5, 1);
            C5179r0.m20247b(textView6, 1);
            C5179r0.m20247b(textView7, 1);
            C5179r0.m20247b(textView8, 1);
            CLUtility.m16578q2(this, dialogM16384c);
            dialogM16384c.show();
        }
        textView = textView10;
        TextView textView112 = textView;
        textView112.setVisibility(!z10 ? 0 : 8);
        textView112.setOnClickListener(new View.OnClickListener() { // from class: p2.o3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20545b.m6420jd(dialogM16384c, c0012b, view);
            }
        });
        TextView textView122 = (TextView) dialogM16384c.findViewById(R.id.item_move_to_waiting_room);
        textView122.setVisibility((this.f6618T2 || z9 || !this.f6785s3) ? 8 : 0);
        textView122.setOnClickListener(new View.OnClickListener() { // from class: p2.p3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20569b.m6431kd(dialogM16384c, c0012b, view);
            }
        });
        if (c0012b.m87f() == NileNetwork.PlatformType.Platform_PhoneLine) {
        }
        C5179r0.m20247b(textView5, 1);
        C5179r0.m20247b(textView6, 1);
        C5179r0.m20247b(textView7, 1);
        C5179r0.m20247b(textView8, 1);
        CLUtility.m16578q2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* renamed from: Of */
    public final void m6692Of(String str, String str2) {
        final Dialog dialogM16385d;
        if (isFinishing() || this.f6572L4) {
            return;
        }
        this.f6572L4 = true;
        AlertDialog alertDialog = this.f6727k1;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6727k1.dismiss();
            this.f6727k1 = null;
        }
        boolean zEqualsIgnoreCase = str2.equalsIgnoreCase(getString(R.string.clm_error_old_app_client));
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] showForceLeaveMeetingDialog: " + str2);
        if (zEqualsIgnoreCase) {
            dialogM16385d = C3123g.m16386e(this, "", str2, false);
            TextView textView = (TextView) dialogM16385d.findViewById(R.id.h_btn_ok);
            textView.setText(R.string.clm_meeting_update_now);
            textView.setOnClickListener(new View.OnClickListener() { // from class: p2.y3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20670b.m6094Gd(dialogM16385d, view);
                }
            });
            TextView textView2 = (TextView) dialogM16385d.findViewById(R.id.h_btn_cancel);
            textView2.setText(R.string.close);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.z3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20679b.m6103Hd(dialogM16385d, view);
                }
            });
        } else {
            dialogM16385d = C3123g.m16385d(this, "", str2);
            TextView textView3 = (TextView) dialogM16385d.findViewById(R.id.v_btn);
            textView3.setText(R.string.close);
            textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.a4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20361b.m6113Id(dialogM16385d, view);
                }
            });
        }
        if (Globals.m7388i0().m7534d2() && !C5170o0.m20170e(str)) {
            dialogM16385d.findViewById(R.id.titlePanel).setVisibility(0);
            ((TextView) dialogM16385d.findViewById(R.id.dialogTitle)).setText(str);
        }
        dialogM16385d.setCancelable(false);
        dialogM16385d.show();
    }

    /* renamed from: Og */
    public final void m6693Og() {
        C0598a c0598a = this.f6515C1;
        if (c0598a != null) {
            c0598a.m3297g();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnAddHostNotify(int i9, int i10) {
        C0012b c0012bM7042l = this.f6677d0.m7042l(i9);
        C5187v0.m20268d(getString(R.string.clm_meeting_set_host_notify, c0012bM7042l != null ? c0012bM7042l.f66b.m68c() : ""));
        this.f6618T2 = true;
        this.f6677d0.m7033C(true);
        this.f6677d0.notifyDataSetChanged();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnBRBroadcast(String str, String str2) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnBRBroadcast message : " + str + " hostName : " + str2);
        m6656If(str, str2);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnBREventTime(int i9, int i10) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnBREventTime currentSec : " + i9 + " totalSec : " + i10);
        this.f6636W2 = (System.currentTimeMillis() / 1000) - ((long) i9);
        this.f6642X2 = (long) i10;
        Iterator<BreakoutRoom> it = this.f6612S2.iterator();
        while (it.hasNext()) {
            BreakoutRoom next = it.next();
            next.f6331f = this.f6636W2;
            next.f6332g = this.f6642X2;
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnBRListHost(int i9, String str) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnBRListHost hostId : " + i9 + " hostName : " + str);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnBRMove(String str) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnBRMove meetingId : " + str);
        this.f6594P2 = str;
        m7366I0().post(this.f6566K4);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnBRRename(int i9, String str, String str2) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnBRRename meetingId : " + str + " title : " + str2);
        Iterator<BreakoutRoom> it = this.f6612S2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BreakoutRoom next = it.next();
            if (next.f6329d.equals(str)) {
                next.f6327b = str2;
                break;
            }
        }
        if (this.f6588O2 == BreakoutRoomState.CHILD) {
            this.f6630V2 = str2;
            this.f6775r0.setText(str2);
        }
        C1337p1 c1337p1 = this.f6694f3;
        if (c1337p1 != null) {
            c1337p1.notifyDataSetChanged();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnDowngradeVideoQuality() {
        if (m6700Q8()) {
            Globals.m7388i0().m7600q3(this.f6669c.format(new Date()));
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnDowngradeVideoQuality");
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setMessage(getString(this.f6742m2 ? R.string.clm_meeting_using_basic_video_quality_for_pro_account : R.string.clm_meeting_using_basic_video_quality));
            builderM16382a.setNegativeButton(getString(R.string.close), (DialogInterface.OnClickListener) null);
            builderM16382a.setCancelable(false);
            builderM16382a.create().show();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnHaveBeenKickedout(int i9) {
        final String strM69d;
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnHaveBeenKickedout: " + i9);
        AlertDialog alertDialog = this.f6693f2;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6693f2.hide();
        }
        if (this.f6778r3) {
            m6813kg();
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.clm_meeting_remove_from_meeting));
        builderM16382a.setMessage(getString(R.string.clm_meeting_you_have_been_removed_by_someone_v2));
        C0012b c0012b = (C0012b) this.f6677d0.m7040j(ParticipantsAdapter.ParticipantType.PARTICIPANT, i9);
        if (c0012b != null) {
            strM69d = c0012b.f66b.m69d();
        } else {
            strM69d = "id = " + i9;
        }
        builderM16382a.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: p2.o
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                this.f20537b.m6597zb(strM69d, dialogInterface, i10);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnMeetingHost() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnMeetingHost");
        if (this.f6588O2 != BreakoutRoomState.CHILD) {
            this.f6618T2 = true;
            this.f6677d0.m7033C(true);
            this.f6677d0.notifyDataSetChanged();
            m6889xh();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnNewBlackListURL(String str) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnNewBlackListURL: " + str);
        m6792gh(str);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnParticipantReset() {
        Log.d("MeetingActivity", "OnParticipantReset");
        this.f6681d4 = false;
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnParticipantUpdateDone() {
        Log.d("MeetingActivity", "OnParticipantUpdateDone");
        this.f6681d4 = true;
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnPinVideoResult(int i9, int i10) {
        ULogUtility.m16670f("MeetingActivity", "OnPinVideoResult: request_id = " + i9 + ", resultCode = " + i10);
        if (i9 == this.f6649Y3) {
            m6723U8();
            if (i10 != 0) {
                m6881wf();
            } else {
                m6902zh();
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnRemoveHostNotify(int i9, int i10) {
        C0012b c0012bM7042l = this.f6677d0.m7042l(i9);
        C5187v0.m20268d(getString(R.string.clm_meeting_remove_host_notify, c0012bM7042l != null ? c0012bM7042l.f66b.m68c() : ""));
        this.f6618T2 = false;
        this.f6677d0.m7033C(false);
        this.f6677d0.notifyDataSetChanged();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnRollCallStartNotify(int i9, int i10, int i11) throws JSONException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnRollCallStartNotify host : " + i9 + " duration : " + i10 + " totalTime : " + i11);
        if (this.f6797u1.f66b.f65d == 0 || this.f6778r3) {
            return;
        }
        C0012b c0012bM7042l = this.f6677d0.m7042l(i9);
        String strM68c = c0012bM7042l != null ? c0012bM7042l.f66b.m68c() : "";
        this.f6523D3 = strM68c;
        if (TextUtils.isEmpty(strM68c)) {
            return;
        }
        boolean z8 = c0012bM7042l != null && c0012bM7042l.f66b.f65d == this.f6797u1.f66b.f65d;
        this.f6529E3 = z8;
        if (z8) {
            return;
        }
        boolean z9 = i11 == Integer.MAX_VALUE;
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j9 = jCurrentTimeMillis - (i10 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        this.f6517C3 = j9;
        long j10 = j9 + (i11 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        String string = z9 ? getString(R.string.clm_roll_call_start_no_limit, this.f6523D3) : getString(R.string.clm_roll_call_start, this.f6523D3, Integer.valueOf(i11 / 60), CLUtility.m16454J2(new Date(j10)));
        if (this.f6595P3 == null) {
            this.f6595P3 = new Runnable() { // from class: p2.b5
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    this.f20372b.m6039Bb();
                }
            };
        }
        if (this.f6830z3) {
            m6831ng(string);
            if (z9) {
                return;
            }
            m7366I0().postDelayed(this.f6595P3, j10 - jCurrentTimeMillis);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (z9) {
                jSONObject.put("event", RollCallEvent.START_NO_LIMIT.name());
            } else {
                jSONObject.put("event", RollCallEvent.START.name());
                jSONObject.put("endTime", j10);
            }
            jSONObject.put("message", string);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        this.f6589O3.add(jSONObject);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnRollCallStop() throws JSONException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnRollCallStop");
        if (this.f6797u1.f66b.f65d == 0 || this.f6778r3 || this.f6529E3) {
            return;
        }
        m7366I0().removeCallbacks(this.f6595P3);
        if (!this.f6830z3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", RollCallEvent.STOP.name());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            this.f6589O3.add(jSONObject);
            return;
        }
        Dialog dialog = this.f6505A3;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.f6505A3.dismiss();
        m6825mg();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnStopDTByHost() {
        ULogUtility.m16683s("MeetingActivity", "OnStopDTByHost meetingInForeground : " + this.f6830z3);
        if (this.f6830z3) {
            m6887xf(false);
            m6734Vg();
            m6849qg();
        } else {
            this.f6643X3 = true;
            Intent intent = new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) SplashActivity.class);
            intent.setFlags(4194304);
            startActivity(intent);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnUpdateRCDuration(int i9) throws JSONException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnUpdateRCDuration totalTime : " + i9);
        if (this.f6797u1.f66b.f65d == 0 || this.f6778r3 || this.f6529E3) {
            return;
        }
        long j9 = this.f6517C3 + (i9 * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
        String string = getString(R.string.clm_roll_call_start, this.f6523D3, Integer.valueOf(i9 / 60), CLUtility.m16454J2(new Date(j9)));
        m7366I0().removeCallbacks(this.f6595P3);
        if (this.f6830z3) {
            Dialog dialog = this.f6505A3;
            if (dialog == null || !dialog.isShowing()) {
                return;
            }
            m6831ng(string);
            m7366I0().postDelayed(this.f6595P3, j9 - System.currentTimeMillis());
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", RollCallEvent.UPDATE.name());
            jSONObject.put("endTime", j9);
            jSONObject.put("message", string);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        this.f6589O3.add(jSONObject);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnWRBackNotify(int i9, int i10) throws Resources.NotFoundException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnWRBackNotify removed : " + i9 + " host : " + i10);
        if (this.f6581N1) {
            m6667Ke();
        }
        if (!this.f6612S2.isEmpty()) {
            Dialog dialog = this.f6666b3;
            if (dialog != null && dialog.isShowing()) {
                this.f6666b3.dismiss();
            }
            this.f6600Q2.setVisibility(8);
            this.f6588O2 = BreakoutRoomState.NONE;
            this.f6612S2.clear();
        }
        if (this.f6595P3 != null) {
            m7366I0().removeCallbacks(this.f6595P3);
        }
        Dialog dialog2 = this.f6505A3;
        if (dialog2 != null && dialog2.isShowing()) {
            this.f6505A3.dismiss();
        }
        Dialog dialog3 = this.f6511B3;
        if (dialog3 != null && dialog3.isShowing()) {
            this.f6511B3.dismiss();
        }
        this.f6589O3.clear();
        if (this.f6625U3.getVisibility() == 0) {
            m6764b9();
            m6735W8(false);
        }
        this.f6796u0.setVisibility(8);
        m6734Vg();
        m6876vg();
        m6738We();
        this.f6771q3 = true;
        this.f6677d0.f6990d.m2913h();
        this.f6677d0.m7046p().clear();
        this.f6501A = false;
        this.f6513C = false;
        this.f6686e2 = false;
        this.f6826z = false;
        this.f6795u = null;
        this.f6820y = null;
        this.f6781s = null;
        this.f6788t = null;
        m6648He();
        Intent intent = new Intent(this, (Class<?>) MeetingActivity.class);
        intent.setFlags(603979776);
        startActivity(intent);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnWRMsg(String str) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnWRMsg message : " + str);
        if (this.f6778r3) {
            TextView textView = (TextView) findViewById(R.id.extra_message);
            textView.setText(str);
            textView.setVisibility(0);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    public void OnWaitingRoomEnabled(boolean z8) {
        this.f6785s3 = z8;
        this.f6677d0.m7036F(z8);
        this.f6677d0.notifyDataSetChanged();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: P */
    public void mo5114P(List<C0012b> list, List<ActiveMedia.Type> list2, boolean z8) throws Resources.NotFoundException {
        if (this.f6617T1 == null) {
            return;
        }
        this.f6810w1 = list;
        this.f6816x1 = list2;
        ULogUtility.m16670f("MeetingActivity", "[onUpdateActiveList] active list : " + list);
        ULogUtility.m16670f("MeetingActivity", "[onUpdateActiveList] active types : " + list2);
        this.f6553I3 = z8;
        if (!m6841pb()) {
            this.f6547H3 = z8 ? 1 : -1;
        }
        this.f6804v1 = m6759a9(list);
        if (this.f6778r3) {
            return;
        }
        if (z8 && !m6777db()) {
            if (this.f6603R.getCurrentItem() == 1) {
                C0012b c0012b = (list.size() <= 1 || list.get(1) == null) ? this.f6804v1 : list.get(1);
                this.f6593P1 = m6817lb(list2.size() > 1 ? list2.get(1) : c0012b.m83b().f5375b, c0012b.m99r());
                this.f6691f0.setVisibility(0);
                this.f6627V.f7080c.setVisibility(this.f6593P1 ? 0 : 8);
                this.f6691f0.findViewById(R.id.desktopCameraVideoContainer).setVisibility(this.f6593P1 ? 0 : 8);
                View viewFindViewById = this.f6691f0.findViewById(R.id.desktopCameraAvatarContainer);
                ImageView imageView = (ImageView) this.f6691f0.findViewById(R.id.desktopCameraAvatar);
                if (this.f6593P1) {
                    viewFindViewById.setVisibility(8);
                } else {
                    viewFindViewById.setVisibility(0);
                    this.f6755o1.m6013r(c0012b.f66b, imageView, AvatarDrawableCache.AvatarSize.DESKTOP);
                }
                ULogUtility.m16670f("MeetingActivity", "[onUpdateActiveList] hasRemoteCameraStream : " + this.f6593P1);
            } else {
                this.f6691f0.setVisibility(8);
                this.f6627V.f7080c.setVisibility(8);
            }
        }
        if ((!this.f6581N1 && NileNetwork.DisplayMode.GALLERY == this.f6617T1.m4970v3()) || (this.f6559J3 && NileNetwork.DisplayMode.DESKTOP == this.f6617T1.m4970v3())) {
            m7366I0().postDelayed(new Runnable() { // from class: p2.w
                @Override // java.lang.Runnable
                public final void run() throws Resources.NotFoundException {
                    this.f20636b.m6278Xc();
                }
            }, 400L);
        }
        m6788fh();
        if (this.f6581N1 == z8) {
            return;
        }
        ULogUtility.m16670f("MeetingActivity", "[onUpdateActiveList] isDesktopSharing : " + z8);
        if (!z8 || m6777db()) {
            m6667Ke();
        } else {
            m6673Le();
        }
        this.f6677d0.m7032B(z8);
        this.f6677d0.notifyDataSetChanged();
        if (!this.f6581N1 && NileNetwork.DisplayMode.GALLERY == this.f6617T1.m4970v3()) {
            m6820lh(false);
        }
        m6788fh();
    }

    /* renamed from: P8 */
    public final void m6694P8() throws IllegalStateException {
        if (!this.f6513C && this.f6826z && this.f6820y == InviteCallType.CALLER && this.f6677d0.f6990d.m2926u() > 1 && this.f6507B) {
            m6888xg();
        }
    }

    /* renamed from: P9 */
    public final int m6695P9() {
        float f9;
        float f10;
        int iM6689O9 = m6689O9();
        if (this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) < 4) {
            f9 = iM6689O9;
            f10 = 1.7777778f;
        } else {
            f9 = iM6689O9;
            f10 = 1.6f;
        }
        return Math.min((int) (f9 * f10), m6766bb() ? m6742X9() : m6742X9() / 2);
    }

    /* renamed from: Pa */
    public final void m6696Pa() {
        this.f6617T1.m4882A3(new C1305f());
        m6684Na();
    }

    /* renamed from: Pe */
    public final void m6697Pe() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        m6675Lg();
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = getResources().openRawResourceFd(R.raw.call_end);
        if (assetFileDescriptorOpenRawResourceFd == null) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] playErrorSound error = cant not open rawResource");
            return;
        }
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.f6503A1 = mediaPlayer;
            mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
            this.f6503A1.setAudioStreamType(0);
            this.f6503A1.setLooping(false);
            this.f6503A1.prepare();
            this.f6503A1.start();
        } catch (Exception e9) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] playErrorSound error = " + e9);
        }
        try {
            assetFileDescriptorOpenRawResourceFd.close();
        } catch (Exception e10) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] playErrorSound error = " + e10);
        }
    }

    /* renamed from: Pf */
    public final void m6698Pf(String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        m6663Jg();
        m6779dh();
        m6681Mg();
        m6697Pe();
        this.f6556J0.setVisibility(8);
        this.f6562K0.setVisibility(8);
        this.f6568L0.setVisibility(8);
        this.f6713i1.setVisibility(8);
        this.f6550I0.setText(str);
        this.f6550I0.setTextColor(getResources().getColor(R.color.you_color_delete_red));
        C5179r0.m20247b(this.f6550I0, getResources().getInteger(R.integer.clm_status_layout_line_num));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f6706h1.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, 8);
        this.f6706h1.setLayoutParams(layoutParams);
    }

    /* renamed from: Pg */
    public final void m6699Pg() {
        Vibrator vibrator = this.f6822y1;
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: Q */
    public void mo5115Q(Collection<ChatMsg> collection) throws JSONException {
        Log.d("MeetingActivity", "onChatMsgReceived: " + collection.size());
        Collection<ChatMsg> collectionM6797hh = m6797hh(collection);
        Log.d("MeetingActivity", "updated: " + collection.size());
        this.f6616T0.m7117f(collectionM6797hh);
        this.f6610S0.setSelection(Math.min(this.f6610S0.getLastVisiblePosition() + collectionM6797hh.size(), this.f6616T0.getCount() + (-1)));
    }

    /* renamed from: Q8 */
    public final boolean m6700Q8() {
        String strM7621u0 = Globals.m7388i0().m7621u0();
        if (C5170o0.m20170e(strM7621u0)) {
            return true;
        }
        try {
            SimpleDateFormat simpleDateFormat = this.f6669c;
            return true ^ simpleDateFormat.format(simpleDateFormat.parse(strM7621u0)).equals(this.f6669c.format(new Date()));
        } catch (ParseException e9) {
            e9.printStackTrace();
            return true;
        }
    }

    /* renamed from: Q9 */
    public final String m6701Q9(long j9) {
        if (j9 <= 0) {
            return "";
        }
        long j10 = j9 / 60;
        if (j10 % 60 == 0) {
            return String.format(Locale.getDefault(), "%d", Long.valueOf(j10 / 60));
        }
        return String.format(Locale.getDefault(), "%.1f", Float.valueOf(((float) Math.floor((j10 / 60.0f) * 10.0f)) / 10.0f));
    }

    /* renamed from: Qa */
    public final void m6702Qa() {
        this.f6761p0 = (TextView) this.f6657a0.findViewById(R.id.meeting_participants_title);
        View viewFindViewById = this.f6657a0.findViewById(R.id.btn_meeting_back);
        View viewFindViewById2 = this.f6657a0.findViewById(R.id.btn_meeting_invite_click_area);
        View viewFindViewById3 = this.f6657a0.findViewById(R.id.btn_meeting_invite);
        View viewFindViewById4 = this.f6657a0.findViewById(R.id.meeting_participants_more);
        BreakoutRoomState breakoutRoomState = this.f6588O2;
        BreakoutRoomState breakoutRoomState2 = BreakoutRoomState.CHILD;
        if (breakoutRoomState == breakoutRoomState2) {
            viewFindViewById2.setVisibility(8);
            viewFindViewById3.setVisibility(8);
        } else if (this.f6823y2 && (breakoutRoomState == breakoutRoomState2 || !this.f6618T2)) {
            viewFindViewById3.setVisibility(8);
        }
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: p2.u0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20614b.m6396hc(view);
            }
        });
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: p2.v0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20627b.m6408ic(view);
            }
        });
        viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: p2.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20637b.m6419jc(view);
            }
        });
        m6883wh();
    }

    /* renamed from: Qe */
    public final void m6703Qe(Runnable runnable) throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException, InvocationTargetException {
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = getResources().openRawResourceFd(R.raw.call_end);
        try {
            if (assetFileDescriptorOpenRawResourceFd == null) {
                ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] playMeetingEndSound error = cant not open rawResource");
                runnable.run();
                return;
            }
            try {
                final MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                mediaPlayer.setAudioStreamType(0);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: p2.q
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer2) throws IllegalStateException {
                        this.f20576b.m6442ld(mediaPlayer, mediaPlayer2);
                    }
                });
                mediaPlayer.setLooping(false);
                mediaPlayer.prepare();
                mediaPlayer.start();
                m7366I0().postDelayed(runnable, 1000L);
            } catch (Exception e9) {
                ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] Play end sound error: " + e9);
                runnable.run();
            }
        } finally {
            C6370g.m24480b(assetFileDescriptorOpenRawResourceFd);
        }
    }

    /* renamed from: Qf */
    public void m6704Qf(int i9) {
        C0012b c0012bM7042l = this.f6677d0.m7042l(i9);
        String strM68c = c0012bM7042l != null ? c0012bM7042l.f66b.m68c() : "";
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.clm_meeting_old_version_set_host, strM68c)).setCancelable(true).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
        builderM16382a.show();
    }

    /* renamed from: Qg */
    public final void m6705Qg() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] stop waitCallerConnectTimer");
        m7366I0().removeCallbacks(this.f6751n4);
    }

    /* renamed from: R8 */
    public final void m6706R8() {
        if (this.f6616T0 != null) {
            NileNetwork nileNetwork = this.f6617T1;
            if (nileNetwork != null) {
                nileNetwork.m4956o3();
            }
            List<MessageObj> listM7124m = this.f6616T0.m7124m();
            if (listM7124m == null || listM7124m.size() <= 0) {
                return;
            }
            UploadMultipleChatMediaHelperQueue.m16892F().m16918C(listM7124m.get(0).m14777o());
            this.f6616T0.m7127t();
        }
    }

    /* renamed from: R9 */
    public final int m6707R9() {
        boolean zM16445H1 = CLUtility.m16445H1(getApplicationContext());
        int i9 = f6494B5;
        if (i9 != 0) {
            return i9;
        }
        int dimensionPixelOffset = zM16445H1 ? (getResources().getDimensionPixelOffset(R.dimen.clm_local_container_long_edge) * 3) / 2 : getResources().getDimensionPixelOffset(R.dimen.clm_local_container_long_edge);
        f6494B5 = dimensionPixelOffset;
        return dimensionPixelOffset;
    }

    /* renamed from: Ra */
    public final void m6708Ra() {
        this.f6829z2 = (ViewGroup) findViewById(R.id.meetingKeypadLayout);
        View viewFindViewById = findViewById(R.id.btn_meeting_keypad);
        this.f6504A2 = viewFindViewById;
        viewFindViewById.setVisibility(0);
        EditText editText = (EditText) findViewById(R.id.meetingKeypadNumber);
        this.f6510B2 = editText;
        editText.setKeyListener(null);
        this.f6522D2 = (TextView) findViewById(R.id.meetingInviteKeypadTime);
        this.f6528E2 = (ImageView) findViewById(R.id.meetingKeypadInviteeAvatar);
        this.f6516C2 = (TextView) findViewById(R.id.meetingKeypadInviteeName);
        this.f6576M2 = new ToneGenerator(8, 70);
        findViewById(R.id.keypad_1).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_2).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_3).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_4).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_5).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_6).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_7).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_8).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_9).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_0).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_well).setOnClickListener(this.f6524D4);
        findViewById(R.id.keypad_asterisk).setOnClickListener(this.f6524D4);
        this.f6504A2.setOnClickListener(new View.OnClickListener() { // from class: p2.b4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20371b.m6430kc(view);
            }
        });
        this.f6603R.setSwipeable(false);
        this.f6652Z0.setVisibility(8);
        this.f6733l0.setVisibility(8);
        this.f6609S.setVisibility(8);
        m6686Nf(false);
        m7366I0().removeCallbacks(this.f6731k5);
        this.f6698g0.setOnTouchListener(null);
        m6858se("", true, this.f6528E2);
        String str = this.f6546H2;
        if (!C5170o0.m20170e(this.f6540G2) && !this.f6552I2) {
            str = this.f6546H2 + "(#" + this.f6540G2 + ")";
        }
        this.f6516C2.setText(str);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f6528E2.getLayoutParams();
        double d9 = CLUtility.m16445H1(Globals.m7388i0().getApplicationContext()) ? 4.0d : 1.5d;
        int i9 = displayMetrics.densityDpi;
        double d10 = 440.0d;
        if (440.0d / i9 < 1.0d) {
            d10 = 440.0d / i9;
        } else {
            d9 = i9;
        }
        float f9 = (float) (d10 / d9);
        int i10 = displayMetrics.heightPixels;
        float f10 = ((double) i10) / 1794.0d < 1.0d ? 1.0f : (float) (i10 / 1794.0d);
        int i11 = (int) (layoutParams.topMargin * f9 * f10);
        int i12 = (int) (layoutParams.bottomMargin * f9 * f10);
        if (f10 > 1.0f) {
            i12 *= 2;
        }
        layoutParams.setMargins(layoutParams.leftMargin, i11, layoutParams.rightMargin, i12);
    }

    /* renamed from: Re */
    public final void m6709Re() throws IllegalAccessException, Resources.NotFoundException, IllegalArgumentException, InvocationTargetException {
        Runnable runnable = new Runnable() { // from class: p2.f4
            @Override // java.lang.Runnable
            public final void run() {
                this.f20434b.m6453md();
            }
        };
        boolean z8 = this.f6513C;
        if ((!z8 || this.f6572L4 || this.f6680d3 || this.f6778r3) && (z8 || this.f6820y != InviteCallType.CALLER || this.f6531F)) {
            runnable.run();
            return;
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] delay to release audio manager after play meeting end sound.");
        m6703Qe(runnable);
    }

    /* renamed from: Rg */
    public final void m6710Rg(NileNetwork.DisplayMode displayMode) {
        synchronized (this.f6650Y4) {
            this.f6644X4 = displayMode;
            NileNetwork nileNetwork = this.f6617T1;
            if (nileNetwork == null || nileNetwork.m4970v3() != this.f6644X4) {
                this.f6704h.execute(new Runnable() { // from class: p2.l4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f20510b.m6410ie();
                    }
                });
            }
        }
    }

    /* renamed from: S8 */
    public final void m6711S8() {
        m7366I0().removeCallbacksAndMessages(this.f6590O4);
    }

    /* renamed from: S9 */
    public final int m6712S9() throws Resources.NotFoundException {
        int i9 = f6499y5;
        if (i9 != 0) {
            return i9;
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.clm_local_container_margin_bottom);
        f6499y5 = dimensionPixelOffset;
        return dimensionPixelOffset;
    }

    /* renamed from: Sa */
    public final void m6713Sa() {
        PowerManager powerManager = (PowerManager) getSystemService("power");
        if (powerManager.isWakeLockLevelSupported(32)) {
            this.f6545H1 = powerManager.newWakeLock(32, "MeetingActivity");
        }
    }

    /* renamed from: Se */
    public final void m6714Se(boolean z8) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        RTCAudioManager rTCAudioManager;
        m6681Mg();
        if (!z8 && (((rTCAudioManager = this.f6623U1) == null || rTCAudioManager.m5055f() != 2) && !m6805jb())) {
            m6635Fg();
            return;
        }
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = getResources().openRawResourceFd(R.raw.beacon);
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd2 = getResources().openRawResourceFd(R.raw.call);
        if ((z8 && assetFileDescriptorOpenRawResourceFd != null) || (!z8 && assetFileDescriptorOpenRawResourceFd2 != null)) {
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.f6828z1 = mediaPlayer;
                if (z8) {
                    mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                    this.f6828z1.setAudioStreamType(0);
                    ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] caller play ringtone with speakerOn: " + this.f6543H);
                    this.f6623U1.m5066q(this.f6543H);
                } else {
                    ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] callee play ringtone");
                    this.f6828z1.setDataSource(assetFileDescriptorOpenRawResourceFd2.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd2.getStartOffset(), assetFileDescriptorOpenRawResourceFd2.getLength());
                    if (m6805jb()) {
                        this.f6828z1.setAudioStreamType(3);
                    } else {
                        this.f6828z1.setAudioStreamType(2);
                    }
                }
                this.f6828z1.setLooping(true);
                this.f6828z1.prepare();
                this.f6828z1.start();
            } catch (Exception e9) {
                Log.e("MeetingActivity", "playRingtoneOrVibrate error:" + e9);
            }
            try {
                assetFileDescriptorOpenRawResourceFd.close();
            } catch (Exception e10) {
                Log.e("MeetingActivity", "connectingSound.close() error:" + e10);
            }
            try {
                assetFileDescriptorOpenRawResourceFd2.close();
            } catch (Exception e11) {
                Log.e("MeetingActivity", "callSound.close() error:" + e11);
            }
        }
        if (z8 || !m6857sb()) {
            return;
        }
        m6635Fg();
    }

    /* renamed from: Sf */
    public final void m6715Sf() {
        String str;
        this.f6519D = true;
        this.f6544H0.setVisibility(0);
        this.f6621U.f7078a.setVisibility(8);
        if (this.f6820y == InviteCallType.CALLEE) {
            String strM15620a = this.f6795u.m15620a();
            if (m6749Ya()) {
                str = strM15620a + getString(R.string.incoming_voice_call);
                this.f6550I0.setText(R.string.incoming_voice_call);
            } else {
                str = strM15620a + getString(R.string.incoming_video_call);
                this.f6550I0.setText(R.string.incoming_video_call);
            }
            NotificationHelper.m14077U(str);
            this.f6568L0.setVisibility(8);
            this.f6562K0.setVisibility(0);
            this.f6706h1.setVisibility(0);
            this.f6580N0.setText(getString(R.string.clm_meeting_button_decline));
            if (m6749Ya()) {
                this.f6556J0.setVisibility(8);
                this.f6562K0.setVisibility(0);
                this.f6713i1.setVisibility(8);
            } else {
                this.f6556J0.setVisibility(0);
                this.f6562K0.setVisibility(0);
                this.f6713i1.setVisibility(0);
            }
        } else {
            NotificationHelper.m14076T();
            this.f6550I0.setText(R.string.calling);
            this.f6568L0.setVisibility(0);
            this.f6562K0.setVisibility(8);
            this.f6556J0.setVisibility(8);
            this.f6706h1.setVisibility(4);
            this.f6713i1.setVisibility(8);
            this.f6580N0.setText(getString(R.string.clm_meeting_button_cancel));
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f6574M0.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            this.f6574M0.setLayoutParams(layoutParams);
        }
        this.f6568L0.setSelected(this.f6543H);
        m6855rh();
    }

    /* renamed from: Sg */
    public final void m6716Sg() {
        this.f6514C0.setText(this.f6521D1.m12084z());
        m6768bg(false);
        CLUtility.m16589t1(this);
        this.f6603R.m3180M(m6671L9(), true);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1081o7
    /* renamed from: T */
    public void mo5042T() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeRejectFromAnotherDevice");
        if (this.f6820y == InviteCallType.CALLEE) {
            this.f6557J1 = true;
            m6839p9("NileNetwork - onCalleeRejectFromAnotherDevice");
            return;
        }
        ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] caller received onCalleeRejectFromAnotherDevice, skip it.");
    }

    /* renamed from: T8 */
    public final void m6717T8() {
        ViewGroup viewGroup = this.f6733l0;
        if (viewGroup != null) {
            viewGroup.animate().cancel();
        }
        EmojiconTextView emojiconTextView = this.f6782s0;
        if (emojiconTextView != null) {
            emojiconTextView.animate().cancel();
        }
        ImageView imageView = this.f6789t0;
        if (imageView != null) {
            imageView.animate().cancel();
        }
        ViewGroup viewGroup2 = this.f6705h0;
        if (viewGroup2 != null) {
            viewGroup2.animate().cancel();
        }
        View view = this.f6719j0;
        if (view != null) {
            view.animate().cancel();
        }
        TextView textView = this.f6796u0;
        if (textView != null) {
            textView.animate().cancel();
        }
        ViewGroup viewGroup3 = this.f6684e0;
        if (viewGroup3 != null) {
            viewGroup3.animate().cancel();
        }
        ViewGroup viewGroup4 = this.f6691f0;
        if (viewGroup4 != null) {
            viewGroup4.animate().cancel();
        }
        LinearLayout linearLayout = this.f6609S;
        if (linearLayout != null) {
            linearLayout.animate().cancel();
        }
        ViewGroup viewGroup5 = this.f6628V0;
        if (viewGroup5 != null) {
            viewGroup5.animate().cancel();
        }
        ViewGroup viewGroup6 = this.f6634W0;
        if (viewGroup6 != null) {
            viewGroup6.animate().cancel();
        }
    }

    /* renamed from: T9 */
    public final float m6718T9() {
        float f9 = f6498x5;
        if (f9 != BitmapDescriptorFactory.HUE_RED) {
            return f9;
        }
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.clm_local_container_margin_bottom);
        f6498x5 = dimensionPixelOffset;
        return dimensionPixelOffset;
    }

    /* renamed from: Ta */
    public final void m6719Ta() throws Resources.NotFoundException {
        this.f6733l0 = (ViewGroup) findViewById(R.id.floatingMenuHeader);
        this.f6719j0 = findViewById(R.id.speakerBackground);
        this.f6726k0 = findViewById(R.id.viewPagerIndicatorBackground);
        this.f6768q0 = (EmojiconTextView) findViewById(R.id.meetingStatusTextView);
        this.f6782s0 = (EmojiconTextView) findViewById(R.id.speakerName);
        this.f6789t0 = (ImageView) findViewById(R.id.speakerRaiseHand);
        this.f6747n0 = findViewById(R.id.btn_meeting_camera_switch);
        this.f6754o0 = findViewById(R.id.btn_meeting_messages);
        this.f6740m0 = findViewById(R.id.btn_meeting_participant);
        this.f6663b0 = (TextView) findViewById(R.id.meeting_participant_count);
        this.f6775r0 = (TextView) findViewById(R.id.meetingTitle);
        this.f6734l1 = findViewById(R.id.speakerMeetingRecording);
        this.f6741m1 = findViewById(R.id.meetingLock);
        this.f6600Q2 = (ImageView) findViewById(R.id.btn_breakout_room);
        this.f6606R2 = (Button) findViewById(R.id.count_down_breakout_room);
        this.f6624U2 = findViewById(R.id.breakout_room_hint_view);
        TextView textView = (TextView) findViewById(R.id.breakout_room_hint_text);
        m6647Ha();
        this.f6640X0 = (ViewGroup) findViewById(R.id.btn_meeting_more);
        this.f6646Y0 = (ViewGroup) findViewById(R.id.btn_meeting_speaker);
        this.f6652Z0 = (ViewGroup) findViewById(R.id.btn_meeting_camera);
        this.f6658a1 = (ViewGroup) findViewById(R.id.btn_meeting_microphone);
        this.f6664b1 = (ViewGroup) findViewById(R.id.btn_meeting_hang_up);
        this.f6671c1 = (TextView) findViewById(R.id.meeting_more_text);
        this.f6678d1 = (TextView) findViewById(R.id.meeting_camera_text);
        this.f6685e1 = (TextView) findViewById(R.id.meeting_microphone_text);
        this.f6692f1 = (TextView) findViewById(R.id.meeting_hang_up_text);
        this.f6796u0 = (TextView) findViewById(R.id.speakerTimer);
        this.f6684e0 = (ViewGroup) findViewById(R.id.localRendererContainer);
        this.f6691f0 = (ViewGroup) findViewById(R.id.desktopCameraContainer);
        this.f6827z0 = (ImageView) findViewById(R.id.localRendererRaiseHandIndicator);
        this.f6821y0 = (ImageView) findViewById(R.id.localRendererVideoCloseIndicator);
        this.f6502A0 = (ImageView) findViewById(R.id.localRendererMuteIndicator);
        this.f6705h0 = (ViewGroup) findViewById(R.id.floatingBottomMenuContainer);
        this.f6712i0 = findViewById(R.id.floatingBottomMenuContainerParent);
        this.f6535F3 = findViewById(R.id.desktopSharingHingPanel);
        this.f6541G3 = findViewById(R.id.desktopSharingHintContainer);
        this.f6720j1 = (ImageView) findViewById(R.id.btn_meeting_more_image);
        this.f6577M3 = findViewById(R.id.desktopSharingHintPortrait);
        this.f6583N3 = findViewById(R.id.desktopSharingHintLandscape);
        this.f6508B0 = (ViewGroup) findViewById(R.id.meetingSpeakerInviteParticipantsArea);
        if (!this.f6501A) {
            this.f6733l0.setVisibility(8);
        }
        m6643Gh();
        C5179r0.m20247b((TextView) findViewById(R.id.meetingInviteTextView), 1);
        this.f6628V0.setOnClickListener(this.f6793t4);
        this.f6634W0.setOnClickListener(this.f6793t4);
        this.f6640X0.setOnClickListener(this.f6800u4);
        this.f6646Y0.setOnClickListener(this.f6807v4);
        this.f6652Z0.setOnClickListener(this.f6813w4);
        this.f6658a1.setOnClickListener(this.f6819x4);
        this.f6664b1.setOnClickListener(this.f6825y4);
        this.f6747n0.setOnClickListener(this.f6831z4);
        this.f6775r0.setOnClickListener(new View.OnClickListener() { // from class: p2.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20465b.m6441lc(view);
            }
        });
        this.f6775r0.setOnLongClickListener(new ViewOnLongClickListenerC1294b0());
        this.f6740m0.setOnClickListener(new View.OnClickListener() { // from class: p2.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20479b.m6452mc(view);
            }
        });
        this.f6600Q2.setOnClickListener(new View.OnClickListener() { // from class: p2.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20492b.m6640Ge(view);
            }
        });
        BreakoutRoomState breakoutRoomState = this.f6588O2;
        if (breakoutRoomState != BreakoutRoomState.NONE) {
            if (breakoutRoomState == BreakoutRoomState.PARENT) {
                this.f6600Q2.setImageResource(R.drawable.btn_breakoutroom);
                textView.setText(R.string.breakout_room_press_to_join);
            } else {
                this.f6600Q2.setVisibility(0);
                this.f6600Q2.setImageResource(R.drawable.btn_leave);
                textView.setText(R.string.breakout_room_back_to_main_session);
                this.f6624U2.setVisibility(0);
                m6856sa();
            }
        }
        this.f6754o0.setOnClickListener(new View.OnClickListener() { // from class: p2.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20504b.m6463nc(view);
            }
        });
        this.f6508B0.setOnClickListener(new View.OnClickListener() { // from class: p2.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20514b.m6474oc(view);
            }
        });
        this.f6538G0 = new C4619d(this, new C1297c0());
    }

    /* renamed from: Te */
    public final void m6720Te() {
        C1260a.m5680r(this.f6711i).m15439e(new C1301d1());
    }

    /* renamed from: Tf */
    public void m6721Tf() {
        Dialog dialogM16386e = C3123g.m16386e(this, "", getString(R.string.breakout_room_create), false);
        this.f6666b3 = dialogM16386e;
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogContent);
        textView.setText(R.string.breakout_room_create);
        textView.setGravity(17);
        this.f6666b3.setCancelable(false);
        TextView textView2 = (TextView) this.f6666b3.findViewById(R.id.h_btn_cancel);
        textView2.setText(R.string.breakout_room_later);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.y1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20667b.m6123Jd(view);
            }
        });
        TextView textView3 = (TextView) this.f6666b3.findViewById(R.id.h_btn_ok);
        textView3.setText(R.string.breakout_room_join);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.z1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20676b.m6135Kd(view);
            }
        });
        this.f6666b3.show();
    }

    /* renamed from: Tg */
    public final void m6722Tg() {
        if (this.f6559J3) {
            m6674Lf(false, false);
        }
        this.f6603R.m3180M(m6754Z9(), true);
    }

    /* renamed from: U8 */
    public final void m6723U8() {
        CountDownTimer countDownTimer = this.f6667b4;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.f6667b4 = null;
        }
    }

    /* renamed from: U9 */
    public final int m6724U9() throws Resources.NotFoundException {
        int i9 = f6500z5;
        if (i9 != 0) {
            return i9;
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.clm_local_container_margin_right);
        f6500z5 = dimensionPixelOffset;
        return dimensionPixelOffset;
    }

    /* renamed from: Ua */
    public final void m6725Ua() {
        C0012b c0012bM4966t3 = this.f6617T1.m4966t3();
        this.f6797u1 = c0012bM4966t3;
        if (c0012bM4966t3 == null) {
            this.f6797u1 = new C0012b(0, this.f6774r, Globals.m7388i0().m7568k1().longValue(), false, false);
        }
        this.f6677d0.f6990d.m2906a(this.f6797u1);
        this.f6755o1.m6011p(this.f6797u1.f66b);
        this.f6677d0.m7051z(this.f6797u1.f66b);
        this.f6616T0.m7128u(this.f6797u1.f66b);
    }

    /* renamed from: Ue */
    public final void m6726Ue() throws IllegalStateException {
        if (m6348dg(this, new Runnable() { // from class: p2.a3
            @Override // java.lang.Runnable
            public final void run() {
                this.f20360b.m6464nd();
            }
        }, new Runnable() { // from class: p2.l3
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                this.f20509b.m6726Ue();
            }
        }) || C5179r0.m20246a()) {
            return;
        }
        this.f6567L = true;
        m6636Fh();
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] accept call with meetingType = " + this.f6732l);
        m6666Ka();
        m6619Da();
        m6681Mg();
        m6663Jg();
        m6779dh();
        m6879wa();
        m6686Nf(false);
        m6650Hg();
        m6892yb();
        if (this.f6534F2) {
            m6708Ra();
        }
        this.f6623U1.m5066q(this.f6543H);
        this.f6623U1.m5053c(3);
        this.f6623U1.m5065p(true);
        m6732Ve();
    }

    /* renamed from: Uf */
    public final void m6727Uf() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.clm_meeting_remove_user_not_in_meeting));
        builderM16382a.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null);
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    /* renamed from: Ug */
    public final void m6728Ug() {
        if (this.f6559J3) {
            m6674Lf(false, false);
        }
        this.f6603R.m3180M(0, true);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1081o7
    /* renamed from: V */
    public void mo5043V() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeBusyFromAnotherDevice");
        if (this.f6820y == InviteCallType.CALLEE) {
            this.f6557J1 = true;
            m6839p9("NileNetwork - onCalleeBusyFromAnotherDevice");
            return;
        }
        ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] caller received onCalleeBusyFromAnotherDevice, skip it.");
    }

    /* renamed from: V8 */
    public final void m6729V8() {
        if (this.f6616T0 != null) {
            NileNetwork nileNetwork = this.f6617T1;
            if (nileNetwork != null) {
                nileNetwork.m4956o3();
            }
            Iterator<MessageObj> it = this.f6616T0.m7124m().iterator();
            while (it.hasNext()) {
                UploadMultipleChatMediaHelperQueue.m16892F().m16918C(it.next().m14777o());
            }
        }
    }

    /* renamed from: V9 */
    public final int m6730V9() {
        boolean zM16445H1 = CLUtility.m16445H1(getApplicationContext());
        int i9 = f6493A5;
        if (i9 != 0) {
            return i9;
        }
        int dimensionPixelOffset = zM16445H1 ? (getResources().getDimensionPixelOffset(R.dimen.clm_local_container_short_edge) * 3) / 2 : getResources().getDimensionPixelOffset(R.dimen.clm_local_container_short_edge);
        f6493A5 = dimensionPixelOffset;
        return dimensionPixelOffset;
    }

    /* renamed from: Va */
    public final void m6731Va() {
        this.f6803v0 = (ImageView) this.f6698g0.findViewById(R.id.meetingActiveParticipantAvatar);
        this.f6809w0 = (EmojiconTextView) this.f6698g0.findViewById(R.id.meetingActiveParticipantName);
        this.f6815x0 = (ImageView) this.f6698g0.findViewById(R.id.meetingActiveParticipantRaiseHand);
        this.f6698g0.setOnTouchListener(this.f6752n5);
        m6898za();
    }

    /* renamed from: Ve */
    public final void m6732Ve() {
        MediaSession mediaSession = this.f6582N2;
        if (mediaSession != null) {
            mediaSession.setCallback(null);
            this.f6582N2.setActive(false);
            this.f6582N2.setMediaButtonReceiver(null);
            this.f6582N2.release();
            this.f6582N2 = null;
        }
    }

    /* renamed from: Vf */
    public final void m6733Vf(long j9) {
        this.f6628V0.animate().cancel();
        this.f6634W0.animate().cancel();
        this.f6628V0.animate().translationY(BitmapDescriptorFactory.HUE_RED).alpha(1.0f).setDuration(j9).setInterpolator(this.f6703g5).start();
        this.f6634W0.animate().translationY(BitmapDescriptorFactory.HUE_RED).alpha(1.0f).setDuration(j9).setInterpolator(this.f6703g5).start();
    }

    /* renamed from: Vg */
    public final void m6734Vg() {
        this.f6514C0.setText(this.f6521D1.m12084z());
        m6768bg(false);
        CLUtility.m16589t1(this);
        this.f6603R.m3180M(1, true);
    }

    /* renamed from: W8 */
    public final void m6735W8(boolean z8) {
        int i9;
        Window window = getWindow();
        if (z8) {
            window.setStatusBarColor(this.f6619T3);
            i9 = 256;
        } else {
            window.setStatusBarColor(C6273a.m24024c(this, R.color.transparent));
            i9 = (getResources().getConfiguration().orientation == 1 || !this.f6581N1) ? 1280 : 5894;
        }
        getWindow().getDecorView().setSystemUiVisibility(i9);
    }

    /* renamed from: W9 */
    public final int m6736W9() {
        return m6766bb() ? Math.max(this.f6651Z.getWidth(), this.f6651Z.getHeight()) : Math.min(this.f6651Z.getWidth(), this.f6651Z.getHeight());
    }

    /* renamed from: Wa */
    public final void m6737Wa() {
        getLayoutInflater();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this);
        C1320k c1320k = null;
        this.f6657a0 = layoutInflaterFrom.inflate(R.layout.view_meeting_tab_participants, (ViewGroup) null);
        this.f6698g0 = layoutInflaterFrom.inflate(R.layout.view_meeting_tab_speaker, (ViewGroup) null);
        this.f6526E0 = layoutInflaterFrom.inflate(R.layout.view_meeting_tab_gallery, (ViewGroup) null);
        this.f6532F0 = layoutInflaterFrom.inflate(R.layout.view_meeting_tab_messages, (ViewGroup) null);
        if (this.f6599Q1) {
            this.f6657a0.findViewById(R.id.fakeStatusBar).getLayoutParams().height = this.f6605R1;
            this.f6698g0.findViewById(R.id.fakeStatusBar).getLayoutParams().height = this.f6605R1;
            this.f6532F0.findViewById(R.id.fakeStatusBar).getLayoutParams().height = this.f6605R1;
            findViewById(R.id.fakeStatusBar).getLayoutParams().height = this.f6605R1;
        }
        ListView listView = (ListView) this.f6657a0.findViewById(R.id.participants_list);
        this.f6670c0 = listView;
        listView.setAdapter((ListAdapter) this.f6677d0);
        this.f6762p1.add(this.f6657a0);
        this.f6762p1.add(this.f6698g0);
        this.f6762p1.add(this.f6532F0);
        C1355v1 c1355v1 = new C1355v1(this.f6762p1, c1320k);
        this.f6615T = c1355v1;
        this.f6603R.setAdapter(c1355v1);
        this.f6609S = (LinearLayout) findViewById(R.id.viewPagerIndicator);
        this.f6603R.setOffscreenPageLimit(3);
        this.f6603R.setCurrentItem(1);
        this.f6603R.m3187c(this.f6632V4);
    }

    /* renamed from: We */
    public final void m6738We() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] releaseNileNetwork");
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4948k3();
        }
        MeetingManager.m5632y(this.f6711i);
        this.f6617T1 = null;
    }

    /* renamed from: Wf */
    public final void m6739Wf(long j9) {
        if (m6860sh()) {
            m6733Vf(j9);
        }
    }

    /* renamed from: Wg */
    public final void m6740Wg() {
        m6746Xg(true);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: X */
    public void mo5116X(ChatMsg chatMsg) throws JSONException {
        Log.d("MeetingActivity", "onChatMsgReceived: " + chatMsg.f5388h);
        ArrayList arrayList = new ArrayList();
        arrayList.add(chatMsg);
        ChatMsg next = m6797hh(arrayList).iterator().next();
        Log.d("MeetingActivity", "new Received: " + next.f5388h);
        int count = this.f6616T0.getCount() + (-1);
        int lastVisiblePosition = this.f6610S0.getLastVisiblePosition();
        if (count > lastVisiblePosition) {
            m6821m9(next);
        } else if (count == lastVisiblePosition) {
            m6758Zg();
        }
        this.f6616T0.m7116e(next);
        if (this.f6603R.getCurrentItem() == m6754Z9()) {
            m6866th();
        }
        m6651Hh();
    }

    /* renamed from: X8 */
    public final void m6741X8(boolean z8) {
        if (this.f6625U3.getVisibility() == 0 || this.f6701g3 == z8) {
            return;
        }
        this.f6701g3 = z8;
        View viewFindViewById = findViewById(R.id.ChatDialogFragmentArea);
        int i9 = z8 ? 1280 : 5894;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(i9);
        viewFindViewById.setFitsSystemWindows(z8);
        m7366I0().post(new Runnable() { // from class: p2.a2
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                this.f20359b.m6050Cb();
            }
        });
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: p2.b2
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i10) {
                this.f20368a.m6061Db(i10);
            }
        });
    }

    /* renamed from: X9 */
    public final int m6742X9() {
        return m6766bb() ? Math.min(this.f6651Z.getWidth(), this.f6651Z.getHeight()) : Math.max(this.f6651Z.getWidth(), this.f6651Z.getHeight());
    }

    /* renamed from: Xa */
    public final boolean m6743Xa() {
        C0012b c0012b = this.f6804v1;
        return c0012b != null && c0012b.m97p();
    }

    /* renamed from: Xe */
    public final boolean m6744Xe(C0012b c0012b) {
        return this.f6655Z3.remove(Integer.valueOf(c0012b.f66b.f63b));
    }

    /* renamed from: Xf */
    public final void m6745Xf(long j9, long j10) {
        long j11;
        boolean z8;
        AlertDialog alertDialog;
        long j12 = j10 / 60;
        if (300 < j9 && j9 <= 600) {
            this.f6611S1 = 300;
            j11 = 10;
        } else {
            if (60 < j9 && j9 <= 300) {
                this.f6611S1 = 60;
                j11 = 5;
                z8 = true;
                alertDialog = this.f6727k1;
                if (alertDialog != null && alertDialog.isShowing()) {
                    this.f6727k1.dismiss();
                    this.f6727k1 = null;
                }
                if (this.f6742m2 || z8) {
                    AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
                    builderM16382a.setMessage(getString(R.string.clm_remind_meeting_end_soon_dialog_message, Long.valueOf(j11), Long.valueOf(j12)));
                    builderM16382a.setPositiveButton(getString(R.string.confirm_btn), (DialogInterface.OnClickListener) null);
                    AlertDialog alertDialogCreate = builderM16382a.create();
                    this.f6727k1 = alertDialogCreate;
                    alertDialogCreate.show();
                }
                return;
            }
            this.f6611S1 = 0;
            j11 = 1;
        }
        z8 = false;
        alertDialog = this.f6727k1;
        if (alertDialog != null) {
            this.f6727k1.dismiss();
            this.f6727k1 = null;
        }
        if (this.f6742m2) {
        }
        AlertDialog.Builder builderM16382a2 = C3123g.m16382a(this);
        builderM16382a2.setMessage(getString(R.string.clm_remind_meeting_end_soon_dialog_message, Long.valueOf(j11), Long.valueOf(j12)));
        builderM16382a2.setPositiveButton(getString(R.string.confirm_btn), (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate2 = builderM16382a2.create();
        this.f6727k1 = alertDialogCreate2;
        alertDialogCreate2.show();
    }

    /* renamed from: Xg */
    public final void m6746Xg(boolean z8) {
        m6752Yg(z8, 0L);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: Y */
    public void mo5117Y(ChatMsg chatMsg) {
        Log.e("MeetingActivity", "onChatMsgSendFailed: " + chatMsg.f5388h);
        this.f6616T0.m7116e(chatMsg);
    }

    /* renamed from: Y8 */
    public final void m6747Y8() {
        C1260a.m5672i(Globals.m7388i0().m7506X()).m15439e(new C1298c1());
    }

    /* renamed from: Y9 */
    public final MessageObj m6748Y9(String str) {
        MessageObj messageObj;
        MessageObj messageObj2 = this.f6607R3.get(str);
        if (messageObj2 != null || this.f6616T0 == null) {
            return messageObj2;
        }
        for (int i9 = 0; i9 < this.f6616T0.getCount(); i9++) {
            Object item = this.f6616T0.getItem(i9);
            if ((item instanceof MessageObj) && (messageObj = (MessageObj) item) != null && messageObj.m14777o() != null && messageObj.m14777o().equals(str)) {
                this.f6607R3.put(str, messageObj);
                return messageObj;
            }
        }
        return messageObj2;
    }

    /* renamed from: Ya */
    public final boolean m6749Ya() {
        return !C5170o0.m20170e(this.f6732l) && this.f6732l.equals(MimeTypes.BASE_TYPE_AUDIO);
    }

    /* renamed from: Ye */
    public final void m6750Ye() {
        m7366I0().removeCallbacks(this.f6731k5);
        m7366I0().postDelayed(this.f6731k5, 3000L);
    }

    /* renamed from: Yf */
    public final void m6751Yf() {
        if (isFinishing()) {
            return;
        }
        AlertDialog alertDialog = this.f6727k1;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6727k1.dismiss();
            this.f6727k1 = null;
        }
        NileNetwork nileNetwork = this.f6617T1;
        final Dialog dialogM16386e = C3123g.m16386e(this, getString(R.string.clm_meeting_end_dialog_upgrade_pro_title), getString(R.string.clm_meeting_end_dialog_upgrade_pro_content_with_duration, Long.valueOf(nileNetwork == null ? 0L : nileNetwork.m4972w3() / 60)), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView2.setText(getString(R.string.dialog_upgrade_pro_view));
        textView2.setTypeface(textView2.getTypeface(), 1);
        textView2.setTextSize(2, 16.0f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.h2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20456b.m6146Ld(dialogM16386e, view);
            }
        });
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.close);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.i2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20469b.m6157Md(dialogM16386e, view);
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: Yg */
    public final void m6752Yg(final boolean z8, long j9) {
        final View currentFocus = getCurrentFocus();
        if (j9 == 0) {
            this.f6610S0.post(new Runnable() { // from class: p2.s3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20598b.m6421je(currentFocus, z8);
                }
            });
        } else {
            this.f6610S0.postDelayed(new Runnable() { // from class: p2.t3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20607b.m6432ke(currentFocus, z8);
                }
            }, j9);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: Z */
    public void mo5118Z(List<C0012b> list) {
        if (!Globals.m7388i0().m7464O1()) {
            NileNetwork nileNetwork = this.f6617T1;
            if (nileNetwork == null) {
                return;
            }
            nileNetwork.m4975x8(list, true);
            return;
        }
        HashSet hashSet = new HashSet();
        for (C0012b c0012b : list) {
            if (c0012b.f66b.f65d > 0 && c0012b.m85d() != null) {
                hashSet.add(Long.valueOf(c0012b.f66b.f65d));
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        C2889b.m14298h().m14310n(hashSet, new C1306f0(list));
    }

    /* renamed from: Z8 */
    public final void m6753Z8(Permission permission, Runnable runnable, Runnable runnable2) {
        C5287b.m20583f(permission, new C1296c(runnable, permission, runnable2), this);
    }

    /* renamed from: Z9 */
    public final int m6754Z9() {
        return this.f6615T.getCount() - 1;
    }

    /* renamed from: Za */
    public final boolean m6755Za() {
        RTCAudioManager.AudioDevice audioDeviceM5056g = this.f6623U1.m5056g();
        return audioDeviceM5056g == RTCAudioManager.AudioDevice.WIRED_HEADSET || audioDeviceM5056g == RTCAudioManager.AudioDevice.BLUETOOTH;
    }

    /* renamed from: Ze */
    public final void m6756Ze(C0012b c0012b) {
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4913Q7(c0012b, true, null);
        }
    }

    /* renamed from: Zf */
    public final void m6757Zf(String str, String str2) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (C5170o0.m20170e(str2)) {
            str2 = getString(R.string.error_server_response);
        }
        if (!this.f6519D) {
            m6834oa(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "force leave meeting dialog");
            m6692Of(str, str2);
            m6697Pe();
            return;
        }
        if (Globals.m7388i0().m7534d2() && !C5170o0.m20170e(str)) {
            str2 = "[" + str + "] " + str2;
        }
        m6834oa(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "invite hangup detail");
        m6698Pf(str2);
    }

    /* renamed from: Zg */
    public final void m6758Zg() {
        final View currentFocus = getCurrentFocus();
        this.f6610S0.post(new Runnable() { // from class: p2.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f20526b.m6443le(currentFocus);
            }
        });
    }

    /* renamed from: a9 */
    public final C0012b m6759a9(List<C0012b> list) {
        C0012b next;
        Iterator<C0012b> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next != null && next.m91j()) {
                break;
            }
        }
        if (next != null) {
            return next;
        }
        C0487p<C0012b> c0487pM7043m = this.f6677d0.m7043m();
        int iM2926u = c0487pM7043m.m2926u();
        for (int i9 = 0; i9 < iM2926u; i9++) {
            C0012b c0012bM2918m = c0487pM7043m.m2918m(i9);
            if (c0012bM2918m != null && c0012bM2918m != this.f6797u1 && (!c0012bM2918m.m99r() || !c0012bM2918m.m92k())) {
                ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] chooseActiveParticipant: pick an un-muted one: " + c0012bM2918m.m100s());
                next = c0012bM2918m;
                break;
            }
        }
        if (next != null) {
            return next;
        }
        for (int i10 = 0; i10 < iM2926u; i10++) {
            C0012b c0012bM2918m2 = c0487pM7043m.m2918m(i10);
            if (c0012bM2918m2 != null && c0012bM2918m2 != this.f6797u1) {
                Log.v("MeetingActivity", "activeCandidate: pick the 1st one: " + c0012bM2918m2.m100s());
                return c0012bM2918m2;
            }
        }
        return next;
    }

    /* renamed from: aa */
    public final String m6760aa() {
        if (!this.f6513C) {
            InviteCallType inviteCallType = this.f6820y;
            if (inviteCallType == InviteCallType.CALLER) {
                return "cancel";
            }
            if (inviteCallType == InviteCallType.CALLEE) {
                return "reject";
            }
        }
        return "normal";
    }

    /* renamed from: ab */
    public final boolean m6761ab() {
        return !m6766bb();
    }

    /* renamed from: af */
    public final void m6762af(C0012b c0012b) {
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4916T7(c0012b, null);
        }
    }

    /* renamed from: ah, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void m6432ke(View view, boolean z8) {
        C1375e c1375e = this.f6616T0;
        if (c1375e == null || c1375e.isEmpty()) {
            return;
        }
        if (z8) {
            this.f6610S0.requestFocus();
        }
        this.f6610S0.post(new Runnable() { // from class: p2.h0
            @Override // java.lang.Runnable
            public final void run() {
                this.f20454b.m6454me();
            }
        });
        if (!z8 || view == null) {
            return;
        }
        view.requestFocus();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: b */
    public void mo5119b(boolean z8, boolean z9) {
        this.f6549I = z9;
        this.f6658a1.setSelected(z9);
        if (this.f6658a1.isSelected()) {
            this.f6685e1.setText(R.string.clm_meeting_button_mute);
        } else {
            this.f6685e1.setText(R.string.clm_meeting_button_unmute);
        }
        if (!this.f6534F2 || this.f6570L2) {
            this.f6555J = z8;
            if (!this.f6620T4) {
                m6782ef();
            }
            this.f6502A0.setVisibility(this.f6549I ? 8 : 0);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1081o7
    /* renamed from: b0 */
    public void mo5044b0() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCallerHangUp");
        this.f6575M1 = true;
        m6834oa("cancel", "NileNetwork - onCallerHangUp");
        if (!this.f6534F2 || this.f6513C) {
            return;
        }
        String str = this.f6546H2;
        if (!C5170o0.m20170e(this.f6540G2) && !this.f6552I2) {
            str = this.f6546H2 + "(#" + this.f6540G2 + ")";
        }
        NotificationHelper.m14073Q(this.f6808w, str);
    }

    /* renamed from: b9 */
    public final void m6764b9() {
        this.f6625U3.setVisibility(8);
        this.f6625U3.findViewById(R.id.extraPageFakeStatusBar).setVisibility(8);
        ((TextView) this.f6625U3.findViewById(R.id.title)).setText("");
        this.f6625U3.findViewById(R.id.rollCallPage).setVisibility(8);
        this.f6625U3.findViewById(R.id.breakoutRoomPage).setVisibility(8);
    }

    /* renamed from: ba */
    public final View m6765ba() {
        if (this.f6596P4 == null) {
            this.f6596P4 = findViewById(R.id.txtBBOSD);
        }
        return this.f6596P4;
    }

    /* renamed from: bb */
    public final boolean m6766bb() {
        return getResources().getConfiguration().orientation == 1;
    }

    /* renamed from: bf */
    public final void m6767bf() {
        AudioManager audioManager = this.f6790t1;
        if (audioManager != null) {
            boolean z8 = audioManager.requestAudioFocus(this.f6786s4, 2, 2) == 1;
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] request audio focus for ringtone: " + z8);
            this.f6563K1 = z8;
        }
    }

    /* renamed from: bg */
    public final void m6768bg(boolean z8) {
        if (this.f6521D1 == null) {
            return;
        }
        try {
            if (z8) {
                getSupportFragmentManager().mo1844a().mo1802r(this.f6521D1).mo1794h();
            } else {
                getSupportFragmentManager().mo1844a().mo1799n(this.f6521D1).mo1794h();
                this.f6520D0.setEnabled(this.f6521D1.m12064H());
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: bh */
    public final void m6769bh() {
        PowerManager.WakeLock wakeLock = this.f6545H1;
        if (wakeLock != null && !wakeLock.isHeld()) {
            this.f6545H1.acquire();
        }
        View view = this.f6604R0;
        if (view != null) {
            view.bringToFront();
            this.f6604R0.setVisibility(0);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: c */
    public void mo5120c(C0012b c0012b, boolean z8) {
        if (!z8) {
            m6778df(c0012b);
        } else {
            m6658Ih();
            m6791gg(c0012b);
        }
    }

    /* renamed from: c9 */
    public final void m6770c9() {
        C2027b c2027b = this.f6521D1;
        Boolean bool = Boolean.FALSE;
        c2027b.m12075a0(bool, bool, bool);
        CLUtility.m16589t1(this);
    }

    /* renamed from: ca */
    public final boolean m6771ca() {
        if (this.f6501A) {
            NileNetwork nileNetworkM5616i = MeetingManager.m5616i(this.f6711i);
            this.f6617T1 = nileNetworkM5616i;
            if (nileNetworkM5616i == null) {
                ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] NileNetwork is not init for pre-join case, id = " + this.f6711i);
            }
        } else {
            this.f6617T1 = MeetingManager.m5610c(this.f6711i);
        }
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork == null) {
            return false;
        }
        this.f6635W1 = new ViewOnTouchListenerC5910d(nileNetwork);
        return true;
    }

    /* renamed from: cb */
    public final boolean m6772cb() {
        DeviceCapability.C1399a c1399a = this.f6629V1;
        return c1399a != null && c1399a.m7325f();
    }

    /* renamed from: cf */
    public final void m6773cf(C0012b c0012b) {
        C5187v0.m20268d(getString(R.string.clm_meeting_invite_turn_on_microphone_send_hint, c0012b.m86e()));
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4913Q7(c0012b, false, null);
        }
    }

    /* renamed from: ch */
    public final void m6774ch() {
        PowerManager.WakeLock wakeLock = this.f6545H1;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.f6545H1.release();
        }
        View view = this.f6604R0;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: d */
    public void mo5121d() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeTimeout");
        if (this.f6826z) {
            m6834oa(this.f6537G ? "timeout" : "unreached", "NileNetwork - onCalleeTimeout");
            return;
        }
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive onCalleeTimeout, but is not dual meeting. skip it");
    }

    /* renamed from: d9 */
    public final void m6775d9() {
        C1313h1 c1313h1 = new C1313h1();
        if (this.f6582N2 == null) {
            MediaSession mediaSession = new MediaSession(this, MeetingActivity.class.getSimpleName());
            this.f6582N2 = mediaSession;
            mediaSession.setCallback(c1313h1);
            this.f6582N2.setFlags(3);
            this.f6582N2.setActive(true);
        }
    }

    /* renamed from: da */
    public final TextView m6776da() {
        if (this.f6602Q4 == null) {
            this.f6602Q4 = (TextView) findViewById(R.id.txtPeerBBOSD);
        }
        return this.f6602Q4;
    }

    /* renamed from: db */
    public final boolean m6777db() {
        DeviceCapability.C1399a c1399a = this.f6629V1;
        return c1399a != null && (c1399a.m7328i() || this.f6629V1.m7322c());
    }

    /* renamed from: df */
    public final void m6778df(C0012b c0012b) {
        AlertDialog alertDialog = this.f6548H4;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6548H4.dismiss();
        }
        AlertDialog alertDialogCreate = C3123g.m16382a(this).setMessage(getString(R.string.clm_request_mic_unmute, c0012b.m86e())).setPositiveButton(R.string.clm_request_mic_unmute_turn_on, new DialogInterface.OnClickListener() { // from class: p2.r0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20586b.m6475od(dialogInterface, i9);
            }
        }).setNegativeButton(R.string.close, new DialogInterface.OnClickListener() { // from class: p2.s0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20595b.m6486pd(dialogInterface, i9);
            }
        }).setCancelable(false).create();
        this.f6548H4 = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* renamed from: dh */
    public final void m6779dh() {
        C1358w1 c1358w1 = this.f6509B1;
        if (c1358w1 != null) {
            unregisterReceiver(c1358w1);
            this.f6509B1 = null;
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: e */
    public void mo5123e(List<String> list, boolean z8) throws NumberFormatException {
        Log.d("MeetingActivity", "onUpdateRaisedHandList / count: " + list.size());
        boolean z9 = z8 || this.f6750n3.getVisibility() == 0;
        m6605Ah(list);
        this.f6827z0.setVisibility(this.f6722j3 ? 0 : 8);
        this.f6677d0.m7035E(this.f6743m3);
        m6611Bh();
        if (z9) {
            if (list.size() == 0) {
                if (this.f6750n3.getVisibility() == 0) {
                    m7366I0().removeCallbacks(this.f6757o3);
                    this.f6750n3.setVisibility(8);
                    return;
                }
                return;
            }
            List<Integer> list2 = this.f6743m3;
            int iIntValue = list2.get(list2.size() - 1).intValue();
            if (this.f6677d0.m7042l(iIntValue) == null) {
                this.f6764p3 = true;
            } else {
                this.f6764p3 = false;
                m6796hg(iIntValue);
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: e0 */
    public void mo5124e0(NileNetwork.Status status, int i9) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        int i10;
        if (i9 > 0) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] onStatusChanged:" + status.name() + "(" + i9 + ")");
            switch (C1322k1.f6900a[status.ordinal()]) {
                case 7:
                case 8:
                    i10 = R.string.clm_error_version;
                    break;
                case 9:
                case 10:
                    m6801ig(status);
                    return;
                case 11:
                    i10 = R.string.error_server_response;
                    break;
                case 12:
                    i10 = R.string.clm_meeting_unable_to_join_meeting;
                    break;
                case 13:
                case 14:
                case 15:
                    return;
                default:
                    i10 = R.string.clm_error_try_again;
                    break;
            }
            m6757Zf(status.name(), getString(i10));
            return;
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onStatusChanged:" + status.name() + "(" + i9 + ")");
        int i11 = C1322k1.f6900a[status.ordinal()];
        if (i11 == 1) {
            this.f6561K = true;
            m6636Fh();
            return;
        }
        if (i11 == 2) {
            this.f6561K = false;
            if (this.f6591P) {
                m6636Fh();
            }
            m6881wf();
            return;
        }
        if (i11 == 3) {
            this.f6561K = false;
            m6636Fh();
        } else {
            if (i11 != 4) {
                return;
            }
            this.f6507B = true;
            m6694P8();
            m6881wf();
        }
    }

    /* renamed from: e9 */
    public final void m6780e9() {
        Group group = this.f6808w;
        C1260a.m5670e(m6553v9(this), Globals.m7388i0().m7506X(), (group == null || this.f6534F2) ? C1260a.f6268e.longValue() : group.f13727n, null, ((Calendar) Calendar.getInstance().clone()).getTimeZone().getID(), !C5170o0.m20170e(this.f6725k) ? this.f6725k.equals(MimeTypes.BASE_TYPE_VIDEO) ? "VIDEO" : "VOICE" : null, m6867u9(), this.f6534F2).m15439e(new C1334o1());
    }

    /* renamed from: eb */
    public final boolean m6781eb(List<C0012b> list) {
        if (!Globals.m7388i0().m7464O1()) {
            return true;
        }
        Iterator<C0012b> it = list.iterator();
        while (it.hasNext()) {
            long j9 = it.next().f66b.f65d;
            if (j9 > 0 && this.f6769q1.indexOfKey(j9) < 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: ef */
    public final void m6782ef() {
        if (this.f6581N1) {
            return;
        }
        this.f6652Z0.setSelected(this.f6555J);
        this.f6747n0.setVisibility(this.f6555J ? 0 : 8);
        this.f6821y0.setVisibility(this.f6555J ? 8 : 0);
        this.f6653Z1.setVisibility(m6603Af() ? 8 : 0);
        this.f6684e0.setVisibility(m6900zf() ? 4 : 0);
    }

    /* renamed from: eh */
    public final void m6783eh() {
        if (this.f6804v1 == null) {
            this.f6782s0.setText("");
            return;
        }
        if (this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) > 1) {
            String strM86e = this.f6804v1.m86e();
            if (C5170o0.m20170e(strM86e)) {
                return;
            }
            if (this.f6581N1) {
                strM86e = getString(R.string.clm_speaker_desktop_sharing_title, strM86e);
            } else if (this.f6534F2 && !this.f6570L2 && !this.f6798u2) {
                strM86e = this.f6546H2;
                if (!C5170o0.m20170e(this.f6540G2) && !this.f6552I2) {
                    strM86e = this.f6546H2 + "(#" + this.f6540G2 + ")";
                }
            }
            this.f6782s0.setText(strM86e);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: f */
    public void mo5125f(List<Pair<Integer, String>> list) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnWRUserList list : " + list);
        for (Pair<Integer, String> pair : list) {
            C1262a c1262a = (C1262a) this.f6677d0.m7040j(ParticipantsAdapter.ParticipantType.WAITING_ROOM, ((Integer) pair.first).intValue());
            if (c1262a != null) {
                c1262a.m5733e((String) pair.second);
            } else {
                this.f6677d0.m7046p().add(new C1262a(((Integer) pair.first).intValue(), (String) pair.second));
            }
        }
        if (this.f6715i3) {
            this.f6677d0.m7049x();
        }
        this.f6677d0.notifyDataSetChanged();
        m6871ug();
    }

    /* renamed from: fa */
    public final int m6784fa() {
        return 1;
    }

    /* renamed from: fb */
    public final boolean m6785fb() {
        for (int i9 = 0; i9 < this.f6616T0.getCount(); i9++) {
            Object item = this.f6616T0.getItem(i9);
            if (item != null && (item instanceof ChatMsg)) {
                ChatMsg chatMsg = (ChatMsg) item;
                int i10 = chatMsg.f5390j;
                ChatMsg.Status statusM5013d = chatMsg.m5013d();
                boolean z8 = chatMsg.m5014e() == this.f6797u1.f66b;
                Log.d("MeetingActivity", "isHaveSharedFiles MessageType:" + i10 + ", status:" + statusM5013d);
                if (i10 == 3 && statusM5013d == ChatMsg.Status.NORMAL && !z8 && !chatMsg.m5012c()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: ff */
    public final void m6786ff(boolean z8) throws Resources.NotFoundException {
        Resources resources;
        int i9;
        if (z8) {
            resources = getResources();
            i9 = R.dimen.clm_meeting_button_size_desktop_share;
        } else {
            resources = getResources();
            i9 = R.dimen.clm_meeting_button_size;
        }
        int dimensionPixelOffset = resources.getDimensionPixelOffset(i9);
        for (int i10 = 0; i10 < this.f6705h0.getChildCount(); i10++) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ((ViewGroup) this.f6705h0.getChildAt(i10)).getChildAt(0).getLayoutParams();
            marginLayoutParams.height = dimensionPixelOffset;
            marginLayoutParams.width = dimensionPixelOffset;
            this.f6705h0.getChildAt(i10).requestLayout();
        }
    }

    /* renamed from: fg */
    public final void m6787fg() {
        if (this.f6812w3 == null) {
            this.f6812w3 = C3123g.m16385d(this, "", getResources().getString(R.string.clm_meeting_move_to_waiting_room_by_host));
        }
        this.f6812w3.setCancelable(false);
        TextView textView = (TextView) this.f6812w3.findViewById(R.id.v_btn);
        textView.setText(getString(R.string.ok));
        textView.setOnClickListener(new View.OnClickListener() { // from class: p2.z4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20681b.m6201Qd(view);
            }
        });
        this.f6812w3.show();
    }

    /* renamed from: fh */
    public final void m6788fh() {
        m7366I0().removeCallbacks(this.f6608R4);
        m7366I0().postDelayed(this.f6608R4, 150L);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: g */
    public void mo5126g(NileNetwork.StopDTStatus stopDTStatus) {
        if (stopDTStatus == NileNetwork.StopDTStatus.OLD_USER) {
            m6843pg();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: g0 */
    public void mo5127g0(String str) {
        ULogUtility.m16683s("MeetingActivity", "onRejectLimitUser: " + str);
        if (m7367J0()) {
            return;
        }
        NileNetwork nileNetwork = this.f6617T1;
        C5187v0.m20271g(getString(R.string.clm_reject_user_cause_of_limit, Integer.valueOf(nileNetwork == null ? 0 : nileNetwork.m4977z3())));
    }

    /* renamed from: ga */
    public final void m6789ga() {
        if (this.f6557J1) {
            return;
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] handle action : " + this.f6814x + " invite type = " + this.f6820y);
        if ("create".equals(this.f6814x)) {
            m6604Ag();
            if (this.f6820y == InviteCallType.CALLER) {
                this.f6781s = Globals.m7388i0().m7587o0();
                Group group = this.f6808w;
                this.f6788t = group == null ? "" : group.f13723j;
                if (!this.f6534F2) {
                    m6901zg();
                }
                m6715Sf();
                if (this.f6826z) {
                    m6882wg(true);
                }
            }
            m6780e9();
            return;
        }
        if ("join".equals(this.f6814x)) {
            if (this.f6820y != InviteCallType.CALLEE) {
                m6604Ag();
                m6892yb();
                return;
            }
            this.f6623U1.m5065p(false);
            m6604Ag();
            this.f6781s = this.f6795u.f13648f;
            this.f6788t = this.f6826z ? Globals.m7388i0().m7587o0() : this.f6808w.f13723j;
            this.f6537G = true;
            m6767bf();
            m6901zg();
            m6775d9();
            m6715Sf();
            m6882wg(false);
            if (C5315b.m20802r()) {
                m7366I0().postDelayed(this.f6688e4, C5315b.m20799o());
            }
            if (C5315b.m20804t()) {
                m7366I0().postDelayed(this.f6695f4, C5315b.m20801q());
            }
        }
    }

    /* renamed from: gf */
    public final void m6790gf(ViewGroup viewGroup, AspectRatio aspectRatio) {
        if (viewGroup != null && this.f6581N1) {
            int dimension = (int) getResources().getDimension(R.dimen.clm_local_container_long_edge);
            int dimension2 = (int) getResources().getDimension(R.dimen.clm_local_container_short_edge);
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (aspectRatio == AspectRatio.PORTRAIT) {
                layoutParams.height = dimension;
                layoutParams.width = dimension2;
            } else {
                layoutParams.height = dimension2;
                layoutParams.width = dimension;
            }
            ULogUtility.m16683s("MeetingActivity", "resizeDesktopCameraContainer, width:" + layoutParams.width + ", height:" + layoutParams.height);
            viewGroup.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: gg */
    public final void m6791gg(C0012b c0012b) {
        AlertDialog alertDialog = this.f6548H4;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6548H4.dismiss();
        }
        AlertDialog alertDialogCreate = C3123g.m16382a(this).setMessage(getString(R.string.clm_request_mic_mute, c0012b.m86e())).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: p2.c5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20384b.m6212Rd(dialogInterface, i9);
            }
        }).create();
        this.f6548H4 = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* renamed from: gh */
    public final void m6792gh(final String str) {
        new Thread(new Runnable() { // from class: p2.u3
            @Override // java.lang.Runnable
            public final void run() {
                MeetingActivity.m6465ne(str);
            }
        }).start();
    }

    /* renamed from: ha */
    public final void m6793ha() {
        Dialog dialog;
        Dialog dialog2;
        for (JSONObject jSONObject : this.f6589O3) {
            String strOptString = jSONObject.optString("event");
            if (RollCallEvent.START.name().equals(strOptString)) {
                long jOptLong = jSONObject.optLong("endTime");
                String strOptString2 = jSONObject.optString("message");
                long jCurrentTimeMillis = jOptLong - System.currentTimeMillis();
                if (jCurrentTimeMillis > 0) {
                    m6831ng(strOptString2);
                    m7366I0().postDelayed(this.f6595P3, jCurrentTimeMillis);
                }
            } else if (RollCallEvent.START_NO_LIMIT.name().equals(strOptString)) {
                m6831ng(jSONObject.optString("message"));
            } else if (RollCallEvent.UPDATE.name().equals(strOptString)) {
                long jOptLong2 = jSONObject.optLong("endTime");
                String strOptString3 = jSONObject.optString("message");
                long jCurrentTimeMillis2 = jOptLong2 - System.currentTimeMillis();
                if (jCurrentTimeMillis2 > 0 && (dialog = this.f6505A3) != null && dialog.isShowing()) {
                    m6831ng(strOptString3);
                    m7366I0().removeCallbacks(this.f6595P3);
                    m7366I0().postDelayed(this.f6595P3, jCurrentTimeMillis2);
                }
            } else if (RollCallEvent.STOP.name().equals(strOptString) && (dialog2 = this.f6505A3) != null && dialog2.isShowing()) {
                this.f6505A3.dismiss();
                m6825mg();
            }
        }
        this.f6589O3.clear();
    }

    /* renamed from: hb */
    public final boolean m6794hb() {
        return ((this.f6691f0.getY() + ((float) this.f6691f0.getHeight())) + m6718T9()) + this.f6705h0.getTranslationY() > m6890y9();
    }

    /* renamed from: hf */
    public final void m6795hf(C1376f c1376f, boolean z8) {
        if (this.f6581N1) {
            return;
        }
        c1376f.m7139b();
        int iM6695P9 = m6695P9();
        int iM6689O9 = m6689O9();
        int iM6683N9 = m6683N9(c1376f.f7091n);
        int iM6677M9 = m6677M9(c1376f.f7091n);
        float f9 = iM6695P9 / 2;
        float f10 = iM6689O9 / 2;
        ValueAnimator valueAnimatorM20228b = C5177q1.m20228b(c1376f.f7079b, iM6683N9, iM6677M9, f9, f10, z8 ? 300 : 0);
        c1376f.f7090m = valueAnimatorM20228b;
        valueAnimatorM20228b.start();
        if (c1376f == this.f6627V) {
            if (this.f6756o2 != null) {
                m7366I0().removeCallbacks(this.f6756o2);
            }
            this.f6756o2 = new RunnableC1349t1(f9 - (iM6683N9 / 2.0f), f10 - (iM6677M9 / 2.0f));
            m7366I0().postDelayed(this.f6756o2, 500L);
            return;
        }
        if (c1376f == this.f6633W) {
            if (this.f6763p2 != null) {
                m7366I0().removeCallbacks(this.f6763p2);
            }
            this.f6763p2 = new RunnableC1352u1(f9 - (iM6683N9 / 2.0f), f10 - (iM6677M9 / 2.0f));
            m7366I0().postDelayed(this.f6763p2, 500L);
        }
    }

    /* renamed from: hg */
    public final void m6796hg(int i9) {
        String string;
        if (this.f6603R.getCurrentItem() == 0 || !this.f6618T2) {
            return;
        }
        this.f6750n3.findViewById(R.id.raise_hand_notify).setOnClickListener(new View.OnClickListener() { // from class: p2.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20585b.m6224Sd(view);
            }
        });
        if (this.f6743m3.size() == 1) {
            C0012b c0012bM7042l = this.f6677d0.m7042l(i9);
            string = getString(R.string.clm_meeting_raise_hand_notify, c0012bM7042l != null ? c0012bM7042l.f66b.m68c() : "");
        } else {
            string = this.f6743m3.size() + StringUtils.SPACE + getString(R.string.clm_schedule_people_count);
        }
        ((TextView) this.f6750n3.findViewById(R.id.raise_hand_notify_message)).setText(string);
        this.f6750n3.findViewById(R.id.raise_hand_notify_close).setOnClickListener(new View.OnClickListener() { // from class: p2.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20603b.m6235Td(view);
            }
        });
        if (this.f6757o3 == null) {
            this.f6757o3 = new Runnable() { // from class: p2.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20613b.m6246Ud();
                }
            };
        }
        m7366I0().removeCallbacks(this.f6757o3);
        m7366I0().postDelayed(this.f6757o3, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
        this.f6750n3.setVisibility(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0062 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* renamed from: hh */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Collection<ChatMsg> m6797hh(Collection<ChatMsg> collection) throws JSONException {
        JSONObject jSONObject;
        String string;
        int iHashCode;
        char c9;
        ArrayList arrayList = new ArrayList(collection.size());
        for (ChatMsg chatMsg : collection) {
            try {
                jSONObject = new JSONObject(chatMsg.f5388h);
                string = jSONObject.getString("mediaType");
                iHashCode = string.hashCode();
            } catch (JSONException unused) {
            }
            if (iHashCode == 2189724) {
                if (string.equals("File")) {
                    c9 = 2;
                }
                if (c9 == 0) {
                }
                jSONObject.put("albumId", "Meeting:" + this.f6711i);
                ChatMsg chatMsg2 = new ChatMsg(chatMsg.m5014e(), chatMsg.f5384d, chatMsg.f5385e, chatMsg.f5387g, jSONObject.toString(), chatMsg.f5390j);
                chatMsg2.m5017h(chatMsg.m5013d());
                arrayList.add(chatMsg2);
            } else if (iHashCode != 77090322) {
                c9 = (iHashCode == 82650203 && string.equals("Video")) ? (char) 1 : (char) 65535;
                if (c9 == 0 || c9 == 1 || c9 == 2) {
                    jSONObject.put("albumId", "Meeting:" + this.f6711i);
                    ChatMsg chatMsg22 = new ChatMsg(chatMsg.m5014e(), chatMsg.f5384d, chatMsg.f5385e, chatMsg.f5387g, jSONObject.toString(), chatMsg.f5390j);
                    chatMsg22.m5017h(chatMsg.m5013d());
                    arrayList.add(chatMsg22);
                } else {
                    arrayList.add(chatMsg);
                }
            } else {
                if (string.equals("Photo")) {
                    c9 = 0;
                }
                if (c9 == 0) {
                }
                jSONObject.put("albumId", "Meeting:" + this.f6711i);
                ChatMsg chatMsg222 = new ChatMsg(chatMsg.m5014e(), chatMsg.f5384d, chatMsg.f5385e, chatMsg.f5387g, jSONObject.toString(), chatMsg.f5390j);
                chatMsg222.m5017h(chatMsg.m5013d());
                arrayList.add(chatMsg222);
            }
        }
        return arrayList;
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: i0 */
    public void mo5129i0(List<Integer> list) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnMeetingHost2 hostList : " + list);
        if (this.f6797u1 != null) {
            this.f6618T2 = false;
            Iterator<Integer> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (this.f6797u1.f66b.f63b == it.next().intValue()) {
                    this.f6618T2 = true;
                    break;
                }
            }
        }
        this.f6736l3 = list;
        this.f6677d0.m7034D(list);
        this.f6677d0.m7033C(this.f6618T2);
        this.f6677d0.notifyDataSetChanged();
        m6889xh();
    }

    /* renamed from: i9 */
    public final void m6798i9() {
        if (isFinishing() || this.f6617T1 == null || this.f6557J1) {
            return;
        }
        if (m6777db()) {
            ULogUtility.m16684t("MeetingActivity", "[" + this.f6748n1 + "] unsupported device for video call");
            this.f6555J = false;
            this.f6684e0.setVisibility(8);
            if (MimeTypes.BASE_TYPE_VIDEO.equals(this.f6732l)) {
                C5187v0.m20270f(R.string.clm_warning_unsupported_device_for_video_call);
            }
        }
        m6782ef();
        m6720Te();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] try to join meeting on: " + this.f6753o);
        C1019i c1019iM5000a = new C1019i.b().m5005f(this.f6753o).m5006g(this.f6760p).m5007h(Globals.m7388i0().m7568k1().longValue()).m5008i(this.f6774r).m5002c(this.f6711i).m5003d(m6553v9(this)).m5004e(Globals.m7388i0().m7498V0()).m5001b(TextUtils.isEmpty(Globals.m7388i0().m7442J0()) ? 0 : Integer.parseInt(Globals.m7388i0().m7442J0())).m5000a();
        this.f6617T1.m4976y8(this.f6555J, null);
        this.f6617T1.m4951l8(this.f6549I, null);
        boolean zM20806v = C5315b.m20806v();
        if (this.f6501A) {
            this.f6617T1.m4942h3();
            return;
        }
        if (!"create".equals(this.f6814x) || this.f6820y != InviteCallType.CALLER) {
            setRequestedOrientation(-1);
            this.f6617T1.m4905K3(c1019iM5000a, zM20806v);
        } else if (this.f6826z) {
            this.f6617T1.m4944i3(c1019iM5000a, zM20806v);
        } else {
            this.f6617T1.m4946j3(c1019iM5000a, zM20806v);
        }
    }

    /* renamed from: ia */
    public final void m6799ia(int i9) {
        Log.d("MeetingActivity", "[FU] handleFileUploadPreparedResultCode: " + i9);
        if (i9 == 2 || i9 == 3 || i9 == 6) {
            runOnUiThread(new RunnableC1319j1(i9));
        }
    }

    /* renamed from: if */
    public final void m6800if() {
        if (this.f6620T4) {
            return;
        }
        m6845q9();
        m7366I0().post(new Runnable() { // from class: p2.x0
            @Override // java.lang.Runnable
            public final void run() {
                this.f20654b.m6497qd();
            }
        });
    }

    /* renamed from: ig */
    public final void m6801ig(NileNetwork.Status status) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (isFinishing()) {
            return;
        }
        NileNetwork nileNetwork = this.f6617T1;
        int iM4977z3 = nileNetwork == null ? 0 : nileNetwork.m4977z3();
        Meeting.Creator creator = this.f6802v;
        m6757Zf(status.name(), getString(R.string.clm_error_limit_with_count_host, (creator == null || C5170o0.m20170e(creator.displayName)) ? "the host" : this.f6802v.displayName, Integer.valueOf(iM4977z3)));
    }

    /* renamed from: ih */
    public final void m6802ih() {
        int iM7044n = this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT);
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] updateChatPlusBtnStatus itemCount:" + iM7044n);
        C2027b c2027b = this.f6521D1;
        if (c2027b != null) {
            if (iM7044n <= 1) {
                c2027b.m12081w();
            } else {
                c2027b.m12082x();
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1081o7
    /* renamed from: j */
    public void mo5045j() throws IllegalStateException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onConfirmJoinCompleted");
        this.f6525E = true;
        this.f6567L = false;
        m6636Fh();
        if (!this.f6513C && "join".equals(this.f6814x) && this.f6820y == InviteCallType.CALLEE) {
            this.f6783s1.m5691E(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6725k, this.f6795u.f13645c);
        }
        m6888xg();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: j0 */
    public void mo5130j0(List<Pair<String, Integer>> list) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnUpdateBRStatus : " + list);
        for (Pair<String, Integer> pair : list) {
            Iterator<BreakoutRoom> it = this.f6612S2.iterator();
            while (true) {
                if (it.hasNext()) {
                    BreakoutRoom next = it.next();
                    if (((String) pair.first).equalsIgnoreCase(next.f6329d)) {
                        next.f6328c = ((Integer) pair.second).intValue();
                        break;
                    }
                }
            }
        }
        C1337p1 c1337p1 = this.f6694f3;
        if (c1337p1 != null) {
            c1337p1.notifyDataSetChanged();
        }
    }

    /* renamed from: j9 */
    public final void m6803j9() {
        boolean z8;
        int i9 = 0;
        while (true) {
            if (i9 >= this.f6677d0.f6990d.m2926u()) {
                z8 = true;
                break;
            }
            C0012b c0012bM2918m = this.f6677d0.f6990d.m2918m(i9);
            if (c0012bM2918m != null && c0012bM2918m != this.f6797u1 && !c0012bM2918m.m92k()) {
                z8 = false;
                break;
            }
            i9++;
        }
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_meeting_participant_page_more);
        int i10 = 8;
        dialogM16384c.findViewById(R.id.mute_all_area).setVisibility((z8 || !(this.f6618T2 || this.f6672c2)) ? 8 : 0);
        dialogM16384c.findViewById(R.id.unmute_all_area).setVisibility((z8 && (this.f6618T2 || this.f6672c2)) ? 0 : 8);
        dialogM16384c.findViewById(R.id.allow_recording_area).setVisibility((!this.f6618T2 || this.f6665b2) ? 8 : 0);
        dialogM16384c.findViewById(R.id.prohibit_recording_area).setVisibility((this.f6618T2 && this.f6665b2) ? 0 : 8);
        dialogM16384c.findViewById(R.id.allow_mute_area).setVisibility((!this.f6618T2 || this.f6672c2) ? 8 : 0);
        dialogM16384c.findViewById(R.id.prohibit_mute_area).setVisibility((this.f6618T2 && this.f6672c2) ? 0 : 8);
        dialogM16384c.findViewById(R.id.stop_desktop_Sharing_area).setVisibility((this.f6618T2 && this.f6581N1) ? 0 : 8);
        View viewFindViewById = dialogM16384c.findViewById(R.id.raise_or_lower_hand_area);
        if (this.f6618T2 && this.f6743m3.size() > 0) {
            i10 = 0;
        }
        viewFindViewById.setVisibility(i10);
        dialogM16384c.findViewById(R.id.item_mute_all).setOnClickListener(new View.OnClickListener() { // from class: p2.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20665b.m6121Jb(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_unmute_all).setOnClickListener(new View.OnClickListener() { // from class: p2.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20357b.m6133Kb(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_allow_recording).setOnClickListener(new View.OnClickListener() { // from class: p2.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20366b.m6144Lb(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_prohibit_recording).setOnClickListener(new View.OnClickListener() { // from class: p2.c1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20376b.m6155Mb(dialogM16384c, view);
            }
        });
        TextView textView = (TextView) dialogM16384c.findViewById(R.id.item_raise_or_lower_hand);
        textView.setText(getString(R.string.clm_meeting_lower_all_hands, Integer.valueOf(this.f6743m3.size())));
        textView.setOnClickListener(new View.OnClickListener() { // from class: p2.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20396b.m6166Nb(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_allow_mute).setOnClickListener(new View.OnClickListener() { // from class: p2.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20414b.m6177Ob(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_prohibit_mute).setOnClickListener(new View.OnClickListener() { // from class: p2.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20428b.m6188Pb(dialogM16384c, view);
            }
        });
        dialogM16384c.findViewById(R.id.item_stop_desktop_Sharing).setOnClickListener(new View.OnClickListener() { // from class: p2.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20443b.m6199Qb(dialogM16384c, view);
            }
        });
        CLUtility.m16578q2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* renamed from: ja */
    public final boolean m6804ja(Map<String, String> map) {
        Friend friendM15003C;
        final String str = map.get("statusV2");
        if (C5170o0.m20170e(str)) {
            str = map.get("status");
        }
        String str2 = map.get("callId");
        if (!this.f6711i.equals(str2)) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] Event(client.rtc.hangup) not for this call | meetingId = " + str2);
            return false;
        }
        if (this.f6513C && "reject".equals(str) && map.containsKey("calleeId") && (friendM15003C = C2950b0.m14899A().m15003C(map.get("calleeId").split("@")[0])) != null) {
            C5187v0.m20268d(getString(R.string.clm_meeting_someone_declined_invitation, friendM15003C.m15620a()));
        }
        if (this.f6808w == null) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] Event(client.rtc.hangup) skip hangup event for non group meeting | meetingId = " + str2);
            return false;
        }
        if (this.f6820y == InviteCallType.CALLER && !this.f6826z) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] Event(client.rtc.hangup) skip hangup event for group meeting | meetingId = " + str2);
            return false;
        }
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive hangup event, handle by status : " + str);
        if (this.f6513C) {
            ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive hangup event but meeting is started, skip it.");
        } else {
            this.f6557J1 = true;
            if (!str.equals("busy")) {
                MeetingManager.m5630w(this.f6711i, MeetingManager.MeetingStatus.HANG_UP);
            }
            runOnUiThread(new Runnable() { // from class: p2.t4
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                    this.f20610b.m6210Rb(str);
                }
            });
        }
        return true;
    }

    /* renamed from: jb */
    public final boolean m6805jb() {
        RTCAudioManager rTCAudioManager = this.f6623U1;
        if (rTCAudioManager == null) {
            return false;
        }
        return rTCAudioManager.m5056g() == RTCAudioManager.AudioDevice.WIRED_HEADSET || this.f6623U1.m5059j();
    }

    /* renamed from: jf */
    public void m6806jf(C2973l0 c2973l0) {
        Log.d("MeetingActivity", "[saveMedias] IN");
        C5287b.m20583f(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, new C1295b1(c2973l0), this.f6597Q);
        Log.d("MeetingActivity", "[saveMedias] OUT");
    }

    /* renamed from: jg */
    public final void m6807jg() {
        if (this.f6559J3 || this.f6771q3) {
            return;
        }
        this.f6621U.f7078a.setBackgroundResource(R.color.transparent);
        this.f6621U.f7080c.setVisibility(0);
    }

    /* renamed from: jh */
    public final void m6808jh() {
        int currentItem = this.f6603R.getCurrentItem();
        if (currentItem != 1 && currentItem != m6671L9()) {
            this.f6782s0.setAlpha(BitmapDescriptorFactory.HUE_RED);
            this.f6789t0.setAlpha(BitmapDescriptorFactory.HUE_RED);
            this.f6609S.setAlpha(BitmapDescriptorFactory.HUE_RED);
            m6862ta(false);
            return;
        }
        if (currentItem == 1) {
            this.f6782s0.setAlpha(1.0f);
            this.f6789t0.setAlpha(1.0f);
        }
        this.f6609S.setAlpha(1.0f);
        m6686Nf(true);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: k */
    public void mo5131k(int i9, NileNetwork.BackToWaitingRoomStatus backToWaitingRoomStatus) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnWRBackAck userId : " + i9 + " status : " + backToWaitingRoomStatus.name());
        if (backToWaitingRoomStatus == NileNetwork.BackToWaitingRoomStatus.OLD_USER) {
            m6865tg(i9);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: k0 */
    public void mo5132k0() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeBusy");
        if (this.f6820y != InviteCallType.CALLER) {
            ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive onCalleeBusy, but i am not caller. skip it.");
            return;
        }
        if (!this.f6826z) {
            ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive onCalleeBusy, but it's not dual meeting. skip it.");
            return;
        }
        if (!this.f6513C) {
            m6834oa("busy", "NileNetwork - onCalleeBusy");
            return;
        }
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] receive onCalleeBusy callback but meeting is started, skip it.");
    }

    /* renamed from: k9 */
    public final void m6809k9() {
        m7366I0().postDelayed(this.f6590O4, 10000L);
    }

    /* renamed from: ka */
    public final void m6810ka(String str, String str2) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] handleHangupStatus : " + str + " | from = " + str2);
        if (Constants.IPC_BUNDLE_KEY_SEND_ERROR.equals(str)) {
            m6663Jg();
            m6705Qg();
            m6779dh();
            m6681Mg();
            if (!this.f6513C && this.f6820y == InviteCallType.CALLER) {
                m6697Pe();
            }
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] Not finish activity and show error message when hangup with error, error reason = " + str2);
            return;
        }
        if (this.f6820y != InviteCallType.CALLER || str == null) {
            m6839p9(str2);
            return;
        }
        Group group = this.f6808w;
        String str3 = group != null ? group.f13717d : "";
        if (!"timeout".equals(str) && !"unreached".equals(str)) {
            if (str.equals("busy")) {
                m6698Pf(getString(R.string.callee_is_busy, str3));
                return;
            } else {
                m6839p9(str2);
                return;
            }
        }
        if (!C6456d.m24714D().m24748G()) {
            m6698Pf(getString(R.string.error_server_response));
        } else if ("timeout".equals(str)) {
            m6698Pf(getString(R.string.unable_to_reach_or_busy, str3, str3));
        } else if ("unreached".equals(str)) {
            m6698Pf(getString(R.string.unable_to_reach_disconnect_internet, str3, str3));
        }
    }

    /* renamed from: kb */
    public final boolean m6811kb(C0012b c0012b) {
        return m6817lb(c0012b.m83b().f5375b, c0012b.m99r());
    }

    /* renamed from: kf */
    public final void m6812kf(ChatMsg chatMsg) {
        Log.d("MeetingActivity", "call scrolltoListEnd()");
        if (chatMsg.m5014e() == this.f6797u1.f66b) {
            m6752Yg(true, 100L);
        }
    }

    /* renamed from: kg */
    public final void m6813kg() {
        if (this.f6799u3 == null) {
            Dialog dialogM16385d = C3123g.m16385d(this, "", getResources().getString(R.string.clm_meeting_waiting_room_remove_user));
            this.f6799u3 = dialogM16385d;
            dialogM16385d.setCancelable(false);
        }
        this.f6792t3 = 10;
        m7366I0().post(new RunnableC1309g0());
    }

    /* renamed from: kh */
    public final void m6814kh() {
        if (this.f6581N1) {
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_landscape_bg);
        } else if (this.f6603R.getCurrentItem() == m6671L9()) {
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_gallery_bg);
        } else {
            this.f6719j0.setBackgroundResource(R.drawable.meeting_speaker_portrait_bg);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: l */
    public void mo5133l(boolean z8) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (z8) {
            m6757Zf(null, getString(R.string.clm_error_old_app_client));
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.clm_error_missing_feature_to_update));
        builderM16382a.setNegativeButton(getString(R.string.breakout_room_later), new DialogInterface.OnClickListener() { // from class: p2.v2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                dialogInterface.dismiss();
            }
        });
        builderM16382a.setPositiveButton(getString(R.string.update_now), new DialogInterface.OnClickListener() { // from class: p2.w2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20639b.m6223Sc(dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: l0 */
    public void mo5134l0(C0012b c0012b, boolean z8, boolean z9) throws Resources.NotFoundException {
        Meeting.VoipInfo voipInfo;
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onJoinCompleted, isSecure : " + z8 + " isWaitingRoom : " + z9);
        m6877vh(MeetingEstablishStep.CALLER_M_SERVER_JOIN_MEETING_COMPLETE);
        if (!this.f6525E) {
            m6830nf();
            m6782ef();
            this.f6658a1.setSelected(this.f6549I);
            this.f6646Y0.setSelected(this.f6543H);
            this.f6502A0.setVisibility(this.f6549I ? 8 : 0);
            if (this.f6617T1 != null && (voipInfo = this.f6564K2) != null) {
                String str = voipInfo.qserverAddr;
                String strSubstring = str.substring(0, str.lastIndexOf(":"));
                String str2 = this.f6564K2.exchangeAddr;
                String strSubstring2 = str2.substring(0, str2.lastIndexOf(":"));
                String str3 = this.f6564K2.exchangeAddr;
                this.f6617T1.m4926Z7(this.f6564K2.domainKey, strSubstring, strSubstring2, Integer.valueOf(str3.substring(str3.lastIndexOf(":") + 1)).intValue(), this.f6564K2.extension, (C5170o0.m20170e(this.f6540G2) ? this.f6546H2 : this.f6540G2).replaceAll("[^a-zA-Z0-9]+", ""));
                m7366I0().removeCallbacks(this.f6530E4);
                m7366I0().postDelayed(this.f6530E4, 15000L);
                m6877vh(MeetingEstablishStep.CALLER_XMPP_SEND_INVITE_EVENT_COMPLETE);
            }
        }
        this.f6677d0.f6990d.m2922q(this.f6797u1);
        this.f6797u1 = c0012b;
        this.f6677d0.f6990d.m2906a(c0012b);
        this.f6755o1.m6011p(this.f6797u1.f66b);
        this.f6677d0.m7051z(this.f6797u1.f66b);
        this.f6616T0.m7128u(this.f6797u1.f66b);
        this.f6525E = true;
        this.f6679d2 = z8;
        this.f6741m1.setVisibility(z8 ? 0 : 8);
        if (z9) {
            if (this.f6771q3) {
                m6787fg();
            }
            m6648He();
        } else {
            this.f6733l0.setVisibility(0);
            if (this.f6778r3) {
                m6655Ie();
            }
        }
        if (this.f6588O2 == BreakoutRoomState.CHILD) {
            m7366I0().post(this.f6584N4);
        }
    }

    /* renamed from: l9 */
    public final void m6815l9(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }

    /* renamed from: la */
    public final boolean m6816la(Map<String, String> map) {
        String str = map.get(TtmlNode.ATTR_ID);
        if (!this.f6711i.equals(str)) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] Event(meeting.meeting.end) not for this call | meetingId = " + str);
            return false;
        }
        if (this.f6519D) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] receive meeting end event and stay at invite page, finish activity.");
            this.f6557J1 = true;
            MeetingManager.m5630w(this.f6711i, MeetingManager.MeetingStatus.MEETING_END);
            runOnUiThread(new Runnable() { // from class: p2.r3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20590b.m6222Sb();
                }
            });
        }
        return true;
    }

    /* renamed from: lb */
    public final boolean m6817lb(ActiveMedia.Type type, boolean z8) {
        return (z8 || type == ActiveMedia.Type.NO_DATA || type == ActiveMedia.Type.ICON) ? false : true;
    }

    /* renamed from: lf */
    public final void m6818lf(Uri uri) {
        if (uri == null) {
            return;
        }
        FileItem fileItem = null;
        if (CLUtility.m16613z1(null, uri)) {
            File file = new File(CLUtility.m16576q0(getApplicationContext(), uri));
            fileItem = new FileItem(null, file.exists() ? file.getPath() : null, uri.toString());
        }
        if (fileItem == null) {
            CLUtility.m16599w(getApplicationContext(), uri, new C1363z(new DialogC3133q.b(this).m16411b()));
        } else {
            m6824mf(fileItem);
        }
    }

    /* renamed from: lg */
    public final void m6819lg(final C0012b c0012b) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.clm_meeting_remove_from_meeting));
        builderM16382a.setMessage(getString(R.string.clm_meeting_will_remove_someone_all_people_know, c0012b.m86e()));
        builderM16382a.setPositiveButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        builderM16382a.setNegativeButton(R.string.clm_meeting_remove, new DialogInterface.OnClickListener() { // from class: p2.a5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20363b.m6257Vd(c0012b, dialogInterface, i9);
            }
        });
        builderM16382a.setCancelable(false);
        AlertDialog alertDialogCreate = builderM16382a.create();
        this.f6693f2 = alertDialogCreate;
        alertDialogCreate.show();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1, 2.0f);
        Button button = this.f6693f2.getButton(-1);
        if (button != null) {
            ((LinearLayout) button.getParent()).setOrientation(1);
            button.setLayoutParams(layoutParams);
            C5179r0.m20247b(button, 1);
        }
        Button button2 = this.f6693f2.getButton(-2);
        if (button2 != null) {
            button2.setLayoutParams(layoutParams);
            button2.setTextColor(getResources().getColor(R.color.you_color_delete_red));
            C5179r0.m20247b(button2, 1);
        }
    }

    /* renamed from: lh */
    public final void m6820lh(boolean z8) throws Resources.NotFoundException {
        m6838oh();
        m6844ph(z8);
        m6850qh();
        m6832nh();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: m0 */
    public void mo5135m0(int i9) {
        ULogUtility.m16683s("MeetingActivity", "onUnderRecordingChanged activeCount : " + i9);
        View view = this.f6734l1;
        if (view != null) {
            view.setVisibility(((this.f6603R.getCurrentItem() == 1 || this.f6603R.getCurrentItem() == m6671L9()) && i9 > 0 && !this.f6778r3) ? 0 : 8);
        }
        this.f6569L1 = i9 > 0;
        this.f6677d0.notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005a  */
    /* renamed from: m9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m6821m9(ChatMsg chatMsg) throws JSONException {
        String string;
        char c9;
        String str = chatMsg.m5014e().m68c() + " : ";
        try {
            String string2 = new JSONObject(chatMsg.f5388h).getString("mediaType");
            switch (string2.hashCode()) {
                case 2189724:
                    if (!string2.equals("File")) {
                        c9 = 65535;
                        break;
                    } else {
                        c9 = 3;
                        break;
                    }
                case 63613878:
                    if (string2.equals("Audio")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 77090322:
                    if (string2.equals("Photo")) {
                        c9 = 0;
                        break;
                    }
                    break;
                case 82650203:
                    if (string2.equals("Video")) {
                        c9 = 2;
                        break;
                    }
                    break;
                default:
                    c9 = 65535;
                    break;
            }
            if (c9 == 0) {
                string = getString(R.string.notification_chat_user_send_photo, chatMsg.m5014e().m68c());
            } else if (c9 == 1) {
                string = getString(R.string.notification_chat_user_receive_voice_msg, chatMsg.m5014e().m68c());
            } else if (c9 == 2) {
                string = getString(R.string.notification_chat_user_send_video, chatMsg.m5014e().m68c());
            } else if (c9 != 3) {
                string = str + chatMsg.f5388h;
            } else {
                string = String.format(getString(R.string.notification_chat_user_send_file), chatMsg.m5014e().m68c());
            }
        } catch (JSONException unused) {
            string = str + chatMsg.f5388h;
        }
        this.f6622U0.setText(string);
        if (this.f6622U0.getVisibility() != 0) {
            this.f6622U0.setVisibility(0);
            this.f6622U0.startAnimation(AnimationUtils.loadAnimation(this, R.anim.enter_from_bottom));
        }
        if (!Globals.m7388i0().m7557i2() || Globals.m7396z1()) {
            return;
        }
        ((Vibrator) getSystemService("vibrator")).vibrate(500L);
    }

    /* renamed from: ma */
    public void m6822ma(List<ChatMsg> list) {
        Log.d("MeetingActivity", "[handleOnSaveToMyDeviceMessageButtonClick] IN");
        if (list == null || list.size() == 0) {
            return;
        }
        for (ChatMsg chatMsg : list) {
            m6806jf(new C5926f(chatMsg.f5388h).m23392n());
            chatMsg.m5016g();
        }
        Log.d("MeetingActivity", "[handleOnSaveToMyDeviceMessageButtonClick] OUT");
    }

    /* renamed from: mb */
    public final boolean m6823mb(C0012b c0012b) {
        for (int i9 = 0; i9 < this.f6677d0.f6990d.m2926u(); i9++) {
            if (this.f6677d0.f6990d.m2918m(i9) == c0012b) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: mf */
    public final void m6824mf(FileItem fileItem) {
        if (fileItem == null) {
            C5187v0.m20268d(getResources().getQuantityString(R.plurals.file_type_not_supported, 1));
            return;
        }
        if (fileItem.m16116c() > 52428800) {
            C5187v0.m20268d(getString(R.string.file_size_is_exceeded, CLUtility.m16592u0(getApplicationContext(), 52428800L)));
            return;
        }
        try {
            UploadUtils.m16965l("Upload Performance", "0.1 sendFile(filePath); ==== start");
            if (CLUtility.m16613z1(fileItem.m16115b(), CLUtility.m16510Z1(fileItem.m16117d()))) {
                UploadUtils.m16965l("Upload Performance", "0.1. generateFileContent();");
                String str = "Chat:" + this.f6711i;
                String strM14573Q = C2925v.m14573Q(str, fileItem);
                UploadUtils.m16965l("Upload Performance", "0.1. generateMessage();");
                MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(this.f6711i), MessageObj.MessageType.File, strM14573Q, 0, null);
                UploadUtils.m16965l("Upload Performance", "0.1. getMessageDao().insert();");
                C2950b0.m14916o().m15157B(messageObjM14558I);
                messageObjM14558I.f12924A = false;
                messageObjM14558I.f12959z = 0;
                UploadUtils.m16965l("Upload Performance", "0.2 sendFile(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addFile()");
                UploadMultipleChatMediaHelperQueue.m16892F().m16941t(false, str, fileItem, messageObjM14558I, this.f6711i, true);
                UploadUtils.m16965l("Upload Performance", "0.2. sendFile(MessageObj); ==== end");
                C1375e c1375e = this.f6616T0;
                if (c1375e != null) {
                    c1375e.m7118g(messageObjM14558I);
                    m6752Yg(true, 150L);
                }
            }
            UploadUtils.m16965l("Upload Performance", "0.1 sendFile(fileItem); ==== end");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: mg */
    public final void m6825mg() {
        Dialog dialogM16385d = C3123g.m16385d(this, "", getString(R.string.clm_roll_call_end));
        this.f6511B3 = dialogM16385d;
        TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
        textView.setText(R.string.close);
        textView.setOnClickListener(new View.OnClickListener() { // from class: p2.e2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20416b.m6268Wd(view);
            }
        });
        this.f6511B3.show();
    }

    /* renamed from: mh */
    public final void m6826mh() throws Resources.NotFoundException {
        if (this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) > 2 && !this.f6581N1 && !this.f6778r3) {
            if (!this.f6587O1) {
                this.f6587O1 = true;
                int currentItem = this.f6603R.getCurrentItem();
                this.f6615T.f6976a.add(2, this.f6526E0);
                this.f6603R.setAdapter(this.f6615T);
                if (currentItem == 2) {
                    currentItem = m6754Z9();
                }
                this.f6603R.m3180M(currentItem, false);
                this.f6626U4 = currentItem;
                m6659J8(currentItem);
                this.f6609S.setVisibility(0);
            }
            if (NileNetwork.DisplayMode.GALLERY == this.f6617T1.m4970v3() || (this.f6559J3 && NileNetwork.DisplayMode.DESKTOP == this.f6617T1.m4970v3())) {
                m6820lh(false);
                return;
            }
            return;
        }
        if (this.f6587O1) {
            this.f6587O1 = false;
            int currentItem2 = this.f6603R.getCurrentItem();
            this.f6615T.f6976a.remove(this.f6526E0);
            this.f6603R.setAdapter(this.f6615T);
            int count = currentItem2 == 2 ? 1 : currentItem2;
            if (count >= this.f6615T.getCount()) {
                count = this.f6615T.getCount() - 1;
            }
            m6659J8(count);
            this.f6609S.setVisibility(8);
            this.f6603R.m3180M(count, false);
            this.f6626U4 = count;
            if (currentItem2 != count) {
                m6808jh();
                m6652I8(count);
            }
        }
    }

    /* renamed from: n9 */
    public final void m6827n9(String str) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        this.f6531F = true;
        if (this.f6557J1) {
            m6839p9(str);
        } else {
            m6834oa(m6760aa(), str);
        }
    }

    /* renamed from: na */
    public final void m6828na(String str) {
        if (!C5170o0.m20170e(this.f6711i) && !m6507rb(this.f6711i)) {
            this.f6783s1.m5688B(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6795u.f13645c, this.f6725k, "reject");
            if (this.f6826z) {
                this.f6783s1.m5698z(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6725k, "reject", str);
            } else {
                this.f6783s1.m5692F(C2950b0.m14912k().m15081r(this.f6795u.f13648f), str);
            }
        }
        m6839p9(str);
    }

    /* renamed from: nb */
    public final boolean m6829nb() {
        Iterator<ActiveMedia.Type> it = this.f6816x1.iterator();
        while (it.hasNext()) {
            if (it.next() != ActiveMedia.Type.ICON) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: nf */
    public final void m6830nf() {
        Group group;
        if (this.f6557J1 || this.f6534F2 || !"create".equals(this.f6814x) || this.f6820y != InviteCallType.CALLER || (group = this.f6808w) == null) {
            return;
        }
        this.f6783s1.m5690D(group, this.f6711i, this.f6725k, this.f6753o, this.f6760p, new C1300d0());
    }

    /* renamed from: ng */
    public final void m6831ng(String str) {
        Dialog dialog = this.f6505A3;
        if (dialog == null) {
            this.f6505A3 = C3123g.m16385d(this, "", str);
        } else {
            ((TextView) dialog.findViewById(R.id.dialogContent)).setText(str);
        }
        this.f6505A3.setCancelable(false);
        TextView textView = (TextView) this.f6505A3.findViewById(R.id.v_btn);
        textView.setText(R.string.continue_btn);
        textView.setOnClickListener(new View.OnClickListener() { // from class: p2.s4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f20601b.m6279Xd(view);
            }
        });
        Dialog dialog2 = this.f6511B3;
        if (dialog2 != null && dialog2.isShowing()) {
            this.f6511B3.dismiss();
        }
        this.f6505A3.show();
    }

    /* renamed from: nh */
    public final void m6832nh() {
        if (this.f6603R.getCurrentItem() != m6671L9()) {
            return;
        }
        for (int i9 = 0; i9 < this.f6645Y.size(); i9++) {
            C0012b c0012b = this.f6645Y.get(i9);
            C1376f c1376f = this.f6639X.get(i9);
            if (c0012b != null && c1376f != null) {
                c1376f.f7082e.setText(c0012b.m86e());
                c1376f.f7082e.setSelected(c0012b.m90i());
                c1376f.f7082e.setVisibility(0);
                c1376f.f7083f.setVisibility(c0012b.m90i() ? 0 : 4);
                c1376f.f7084g.setVisibility(c0012b.m92k() ? 0 : 8);
                c1376f.f7085h.setVisibility(c0012b.m99r() ? 0 : 8);
                c1376f.f7086i.setVisibility(c0012b.m97p() ? 0 : 8);
                c1376f.f7092o.setVisibility(c0012b.m95n() ? 0 : 8);
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: o */
    public void mo5136o(List<Pair<String, String>> list) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnBRList : " + list);
        if (!this.f6612S2.isEmpty()) {
            this.f6612S2.clear();
        }
        for (Pair<String, String> pair : list) {
            BreakoutRoom breakoutRoom = new BreakoutRoom();
            breakoutRoom.f6329d = (String) pair.first;
            breakoutRoom.f6327b = (String) pair.second;
            breakoutRoom.f6330e = this.f6711i;
            this.f6612S2.add(breakoutRoom);
        }
        if (this.f6778r3) {
            return;
        }
        if (this.f6612S2.size() <= 0) {
            Dialog dialog = this.f6666b3;
            if (dialog != null && dialog.isShowing()) {
                this.f6666b3.dismiss();
            }
            this.f6600Q2.setVisibility(8);
            this.f6588O2 = BreakoutRoomState.NONE;
            if (this.f6625U3.findViewById(R.id.breakoutRoomPage).getVisibility() == 0) {
                m6764b9();
                return;
            }
            return;
        }
        if (this.f6588O2 != BreakoutRoomState.NONE) {
            m6686Nf(false);
            this.f6600Q2.setVisibility(0);
            this.f6624U2.setVisibility(0);
            m6856sa();
            return;
        }
        if (this.f6625U3.findViewById(R.id.rollCallPage).getVisibility() != 0) {
            m6721Tf();
            BreakoutRoomState breakoutRoomState = BreakoutRoomState.PARENT;
            Iterator<BreakoutRoom> it = this.f6612S2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (this.f6711i.equalsIgnoreCase(it.next().f6329d)) {
                    breakoutRoomState = BreakoutRoomState.CHILD;
                    break;
                }
            }
            this.f6588O2 = breakoutRoomState;
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: o0 */
    public void mo5137o0(C0012b c0012b) {
        if (c0012b != this.f6797u1) {
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] OnMeetingHadBeenClosed: " + c0012b.f66b.f63b);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setTitle(getString(R.string.clm_meeting_fail_remove));
            builderM16382a.setMessage(getString(R.string.clm_meeting_fail_remove_notice, c0012b.m86e()));
            builderM16382a.setNegativeButton(getString(R.string.close), (DialogInterface.OnClickListener) null);
            builderM16382a.setCancelable(false);
            builderM16382a.create().show();
        }
    }

    /* renamed from: o9 */
    public final C0012b m6833o9(ActiveMedia.Type type) {
        for (int i9 = 0; i9 < this.f6816x1.size(); i9++) {
            if (type == this.f6816x1.get(i9) && i9 < this.f6810w1.size()) {
                return this.f6810w1.get(i9);
            }
        }
        return null;
    }

    /* renamed from: oa */
    public final void m6834oa(String str, String str2) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        NileNetwork nileNetwork;
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] hangupMeeting, hangupStatus = " + str + ", from = " + str2);
        if (this.f6557J1) {
            ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] user is hang up, not hang up again. hangupStatus = " + str + " from = " + str2);
            return;
        }
        m6663Jg();
        if (this.f6534F2 && (nileNetwork = this.f6617T1) != null) {
            InviteCallType inviteCallType = this.f6820y;
            InviteCallType inviteCallType2 = InviteCallType.CALLER;
            if (inviteCallType == inviteCallType2) {
                nileNetwork.m4934d3();
            }
            boolean z8 = (C5170o0.m20170e(this.f6540G2) || this.f6552I2) ? false : true;
            if (Arrays.asList("timeout", "cancel").contains(str)) {
                C2950b0.m14919r().m15208d(new PhoneCallObj(-1L, this.f6540G2, this.f6546H2, this.f6820y == InviteCallType.CALLEE, z8, (this.f6820y == inviteCallType2 || "hang up button".equals(str2)) ? false : true, System.currentTimeMillis(), this.f6552I2, this.f6558J2));
            } else if (Arrays.asList("normal", "reject").contains(str)) {
                C2950b0.m14919r().m15208d(new PhoneCallObj(-1L, this.f6540G2, this.f6546H2, this.f6820y == InviteCallType.CALLEE, z8, false, System.currentTimeMillis(), this.f6552I2, this.f6558J2));
            }
        }
        this.f6557J1 = true;
        if (!this.f6513C) {
            if (C5170o0.m20170e(this.f6711i) || m6507rb(this.f6711i)) {
                ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] hangup meeting without meetingId, not send hangup event and message.");
            } else {
                final String strM6760aa = Constants.IPC_BUNDLE_KEY_SEND_ERROR.equals(str) ? m6760aa() : str;
                if (this.f6808w != null) {
                    InviteCallType inviteCallType3 = this.f6820y;
                    if (inviteCallType3 == InviteCallType.CALLER) {
                        NileNetwork nileNetwork2 = this.f6617T1;
                        if (nileNetwork2 != null) {
                            nileNetwork2.m4892F7();
                        }
                        m7366I0().postDelayed(new Runnable() { // from class: p2.r2
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f20588b.m6233Tb(strM6760aa);
                            }
                        }, 1000L);
                        if (this.f6826z) {
                            this.f6783s1.m5689C(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6725k, strM6760aa);
                        }
                    } else if (inviteCallType3 == InviteCallType.CALLEE) {
                        NileNetwork nileNetwork3 = this.f6617T1;
                        if (nileNetwork3 != null) {
                            nileNetwork3.m4892F7();
                        }
                        m7366I0().postDelayed(new Runnable() { // from class: p2.s2
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f20596b.m6244Ub(strM6760aa);
                            }
                        }, 1000L);
                        if (this.f6826z && !this.f6575M1) {
                            this.f6783s1.m5689C(this.f6808w, this.f6711i, this.f6781s, this.f6788t, this.f6725k, strM6760aa);
                        }
                    }
                } else {
                    ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] mGroup is null, can not send hang up message/event.");
                }
            }
        }
        m6810ka(str, str2);
    }

    /* renamed from: ob */
    public final boolean m6835ob() {
        return this.f6547H3 > 0;
    }

    /* renamed from: of */
    public final void m6836of(String str) {
        if (C5170o0.m20170e(str)) {
            return;
        }
        ChatMsg chatMsgM4924Y7 = this.f6617T1.m4924Y7(str);
        if (chatMsgM4924Y7 == null) {
            C5187v0.m20268d("Send message failed");
            return;
        }
        this.f6616T0.m7116e(chatMsgM4924Y7);
        m6812kf(chatMsgM4924Y7);
        this.f6616T0.notifyDataSetChanged();
        this.f6521D1.m12072X("");
        this.f6514C0.setText("");
    }

    /* renamed from: og */
    public final void m6837og(final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        if (!m6615Cf()) {
            if (onClickListener != null) {
                onClickListener.onClick(null);
                return;
            }
            return;
        }
        this.f6676d = true;
        final Dialog dialogM16386e = C3123g.m16386e(this, "", getString(R.string.upload_files_in_meeting) + "\n" + getString(R.string.upload_files_leave_meeting_reminder), true);
        dialogM16386e.setCancelable(false);
        TextView textView = (TextView) dialogM16386e.findViewById(R.id.dialogTitle);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setGravity(17);
        TextView textView2 = (TextView) dialogM16386e.findViewById(R.id.v_btn_ok);
        textView2.setTextColor(getResources().getColor(R.color.you_color_red));
        textView2.setText(getString(R.string.leave_without_saving));
        textView2.setTypeface(textView2.getTypeface(), 1);
        textView2.setTextSize(2, 16.0f);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.q4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeetingActivity.m6290Yd(dialogM16386e, onClickListener, view);
            }
        });
        TextView textView3 = (TextView) dialogM16386e.findViewById(R.id.v_btn_cancel);
        textView3.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
        textView3.setText(R.string.cancel);
        textView3.setTextSize(2, 16.0f);
        textView3.setOnClickListener(new View.OnClickListener() { // from class: p2.r4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MeetingActivity.m6301Zd(dialogM16386e, onClickListener2, view);
            }
        });
        dialogM16386e.show();
    }

    /* renamed from: oh */
    public final void m6838oh() {
        C0012b c0012b;
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < this.f6677d0.f6990d.m2926u(); i9++) {
            if (this.f6677d0.f6990d.m2918m(i9) != this.f6797u1) {
                arrayList.add(this.f6677d0.f6990d.m2918m(i9));
            }
        }
        ArrayList<C0012b> arrayList2 = new ArrayList<>();
        for (int i10 = 0; i10 < this.f6639X.size(); i10++) {
            arrayList2.add(null);
        }
        for (int i11 = 0; i11 < this.f6810w1.size(); i11++) {
            C0012b c0012b2 = this.f6810w1.get(i11);
            if (c0012b2 != null) {
                arrayList2.set(i11, c0012b2);
                arrayList.remove(c0012b2);
            }
        }
        for (int i12 = 0; i12 < arrayList2.size() && i12 < this.f6645Y.size(); i12++) {
            if (arrayList2.get(i12) == null && (c0012b = this.f6645Y.get(i12)) != null && arrayList.contains(c0012b) && m6823mb(c0012b)) {
                arrayList2.set(i12, c0012b);
                arrayList.remove(c0012b);
            }
        }
        for (int i13 = 0; i13 < arrayList2.size() && arrayList.size() > 0; i13++) {
            if (arrayList2.get(i13) == null) {
                arrayList2.set(i13, (C0012b) arrayList.get(0));
                arrayList.remove(0);
            }
        }
        for (int i14 = 0; i14 < arrayList2.size(); i14++) {
            C0012b c0012b3 = arrayList2.get(i14);
            if (c0012b3 != null) {
                if (i14 == 0) {
                    this.f6621U.f7093p = c0012b3.f66b.f63b;
                } else if (i14 == 1) {
                    this.f6627V.f7093p = c0012b3.f66b.f63b;
                } else if (i14 == 2) {
                    this.f6633W.f7093p = c0012b3.f66b.f63b;
                }
            }
        }
        if (this.f6655Z3.size() > 0) {
            for (int i15 = 0; i15 < this.f6655Z3.size(); i15++) {
                Integer num = this.f6655Z3.get(i15);
                int i16 = 0;
                while (true) {
                    if (i16 >= arrayList2.size()) {
                        break;
                    }
                    C0012b c0012b4 = arrayList2.get(i16);
                    if (c0012b4 == null || c0012b4.f66b.f63b != num.intValue()) {
                        i16++;
                    } else if (i16 != i15) {
                        Collections.swap(arrayList2, i16, i15);
                    }
                }
                int i17 = 0;
                while (true) {
                    if (i17 >= this.f6639X.size()) {
                        break;
                    }
                    C1376f c1376f = this.f6639X.get(i17);
                    if (c1376f == null || c1376f.f7093p != num.intValue()) {
                        i17++;
                    } else if (i17 != i15) {
                        Collections.swap(this.f6639X, i17, i15);
                    }
                }
            }
        } else {
            for (int i18 = 0; i18 < arrayList2.size(); i18++) {
                int i19 = 0;
                while (true) {
                    if (i19 >= this.f6639X.size()) {
                        break;
                    }
                    C0012b c0012b5 = arrayList2.get(i18);
                    if (c0012b5 == null || c0012b5.f66b.f63b != this.f6639X.get(i19).f7093p) {
                        i19++;
                    } else if (i18 != i19) {
                        Collections.swap(this.f6639X, i18, i19);
                    }
                }
            }
        }
        this.f6645Y = arrayList2;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        switch (i9) {
            case 40001:
                if (i10 != 0) {
                    if (-1 == i10) {
                        Bundle extras = intent.getExtras();
                        if (extras != null) {
                            String string = extras.getString("c.c.m.p.ConfirmPwd_EXTRA_PASSWD");
                            if (!C5170o0.m20170e(string)) {
                                this.f6718j = string;
                                this.f6753o = extras.getString("meetingMServerAddress");
                                this.f6760p = extras.getString("meetingMServerToken");
                                m6892yb();
                                break;
                            } else {
                                m6839p9("Result has no password");
                                break;
                            }
                        } else {
                            m6839p9("Result has no password");
                            break;
                        }
                    }
                } else {
                    m6839p9("cancel pwd validation");
                    break;
                }
                break;
            case 40002:
                if (i10 == -1) {
                    C5187v0.m20267c(R.string.clm_invite_select_people);
                    break;
                }
                break;
            case 40003:
                ULogUtility.m16683s("MeetingActivity", "onActivityResult / REQUEST_OVERLAY_PERMISSION - in");
                m6887xf(true);
                break;
            case 40004:
                if (i10 == -1) {
                    if (this.f6617T1 != null) {
                        if (intent != null) {
                            if (!this.f6581N1) {
                                ULogUtility.m16683s("MeetingActivity", "[DesktopSharing] onActivityResult / mNileNetwork.setDesktopSharingEnabled()");
                                this.f6617T1.m4939f8(true, intent, new C1325l1());
                                break;
                            } else {
                                ULogUtility.m16683s("MeetingActivity", "[DesktopSharing] onActivityResult / someone has been sharing desktop, skip desktop sharing enabled action");
                                break;
                            }
                        }
                    } else {
                        C5187v0.m20268d("NileNetwork instance does not exist.");
                        break;
                    }
                } else {
                    C5187v0.m20268d("User cancelled");
                    m6674Lf(false, true);
                    break;
                }
                break;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (this.f6625U3.getVisibility() != 0) {
            if (this.f6588O2 == BreakoutRoomState.CHILD) {
                m6649Hf();
                return;
            } else {
                m6633Fe("android native back button");
                return;
            }
        }
        boolean z8 = this.f6625U3.findViewById(R.id.rollCallPage).getVisibility() == 0;
        m6764b9();
        m6735W8(false);
        if (!z8 || this.f6612S2.size() <= 0) {
            return;
        }
        m6721Tf();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration configuration) throws Resources.NotFoundException {
        super.onConfigurationChanged(configuration);
        int i9 = configuration.orientation;
        if (i9 == 2 || i9 == 1) {
            ULogUtility.m16670f("MeetingActivity", "[onConfigurationChanged] onConfigurationChanged : " + configuration.orientation);
            NileNetwork nileNetwork = this.f6617T1;
            if (nileNetwork != null) {
                nileNetwork.f4919e.m4467t("MeetingActivity", "onConfigurationChanged: " + configuration.orientation);
            }
            if (!this.f6581N1) {
                if ((this.f6644X4 == NileNetwork.DisplayMode.GALLERY || (this.f6559J3 && NileNetwork.DisplayMode.DESKTOP == this.f6617T1.m4970v3() && this.f6603R.getCurrentItem() == m6671L9())) && this.f6587O1) {
                    this.f6603R.m3180M(m6671L9(), true);
                    m6826mh();
                } else if (this.f6603R.getCurrentItem() == 1) {
                    this.f6684e0.setTranslationY(BitmapDescriptorFactory.HUE_RED);
                    m6872uh(1);
                }
            }
            m6860sh();
            m6652I8(this.f6603R.getCurrentItem());
            m6676M8();
            m6611Bh();
            m6902zh();
            m6645H8(configuration.orientation == 2);
            if (this.f6559J3) {
                this.f6684e0.setVisibility(m6900zf() ? 4 : 0);
                this.f6653Z1.setVisibility(m6603Af() ? 8 : 0);
            }
            if (this.f6603R.getCurrentItem() == 1 || this.f6603R.getCurrentItem() == m6671L9()) {
                m6686Nf(true);
            }
            m7366I0().post(new Runnable() { // from class: p2.x1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20655b.m6112Ic(configuration);
                }
            });
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (menuItem != null && this.f6616T0 != null) {
            int itemId = menuItem.getItemId();
            Intent intent = menuItem.getIntent();
            menuItem.setIntent(null);
            int intExtra = intent.getIntExtra("data", -1);
            if (intExtra < 0) {
                Log.e("MeetingActivity", "[onContextItemSelected] invalid index");
                return false;
            }
            Log.e("MeetingActivity", "[onContextItemSelected] Index = " + intExtra + ", Click id : " + itemId);
            ChatMsg chatMsg = (ChatMsg) this.f6616T0.getItem(intExtra);
            if (chatMsg != null && itemId == this.f6683e.f22367a) {
                C5145g.m20042a(chatMsg.f5388h);
                return true;
            }
            if (chatMsg != null && itemId == this.f6690f.f22367a) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(chatMsg);
                m6822ma(arrayList);
                return true;
            }
            Log.e("MeetingActivity", "[onContextItemSelected] No matched Click id : " + itemId);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
        View view = this.f6716i4;
        if (view != null) {
            view.setEnabled(true);
            this.f6716i4 = null;
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        super.onCreate(bundle);
        this.f6597Q = this;
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCreate() IN");
        m6690Oa();
        if (Globals.m7396z1()) {
            ULogUtility.m16680p("WakeLock", "[" + this.f6748n1 + "] acquire wakeLock - MeetingActivity(" + this.f6748n1 + ") onCreate");
            this.f6539G1.acquire();
        }
        NotificationHelper.m14096l();
        NotificationHelper.m14098n();
        m6878w9();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] MeetingActivity onCreate meetingId = " + this.f6711i);
        C5321e.m20824o().m20875k(this.f6758o4);
        MeetingManager.MeetingStatus meetingStatusM5615h = MeetingManager.m5615h(this.f6711i);
        if (MeetingManager.m5621n(meetingStatusM5615h)) {
            ULogUtility.m16676l("MeetingActivity", "[" + this.f6748n1 + "] meeting is <" + meetingStatusM5615h + "> when start activity, finish activity when onCreate.");
            m6839p9("meeting canceled when onCreate check");
            return;
        }
        MeetingManager.m5630w(this.f6711i, MeetingManager.MeetingStatus.IN_MEETING);
        if (!m6771ca()) {
            m6839p9("can not get NileNetwork for meetingManager.");
            return;
        }
        this.f6617T1.m4955n8(this);
        this.f6617T1.m4965s8(this);
        Window window = getWindow();
        window.addFlags(2097152);
        window.addFlags(128);
        window.addFlags(524288);
        setContentView(R.layout.activity_meeting_room);
        int iM16533f1 = CLUtility.m16533f1(this);
        this.f6605R1 = iM16533f1;
        int i9 = Build.VERSION.SDK_INT;
        if (iM16533f1 > 0) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            this.f6619T3 = window.getStatusBarColor();
            window.setStatusBarColor(C6273a.m24024c(this, R.color.transparent));
            window.getDecorView().setSystemUiVisibility(1280);
            this.f6599Q1 = true;
        }
        this.f6783s1 = new C1260a(this);
        this.f6629V1 = DeviceCapability.m7311e();
        this.f6790t1 = (AudioManager) getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.f6623U1 = RTCAudioManager.m5052d(getApplicationContext(), this.f6786s4);
        this.f6755o1 = new AvatarDrawableCache(getApplicationContext(), this.f6769q1, this.f6776r1);
        this.f6677d0 = new ParticipantsAdapter(this, this.f6755o1, this.f6723j4);
        this.f6616T0 = new C1375e(this, this.f6755o1);
        m6725Ua();
        m6696Pa();
        m6666Ka();
        m6661Ja();
        m6672La(bundle);
        m6607Ba();
        m6601Aa();
        m6613Ca();
        m6713Sa();
        m6894yf();
        m6630F8();
        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (i9 >= 31) {
            m6753Z8(Permission.BLUETOOTH_BLE, new Runnable() { // from class: p2.j2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20482b.m6156Mc(telephonyManager);
                }
            }, new Runnable() { // from class: p2.k2
                @Override // java.lang.Runnable
                public final void run() throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
                    this.f20495b.m6167Nc();
                }
            });
        } else {
            m6789ga();
            if (!this.f6765p4) {
                telephonyManager.listen(this.f6779r4, 32);
                this.f6765p4 = true;
            }
        }
        this.f6653Z1.setVisibility(8);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("isMicrophoneOn")) {
                this.f6549I = intent.getBooleanExtra("isMicrophoneOn", this.f6549I);
            }
            if (intent.hasExtra("isCameraOn")) {
                this.f6555J = intent.getBooleanExtra("isCameraOn", this.f6555J);
            }
            if (intent.hasExtra("isSpeakerOn")) {
                this.f6543H = intent.getBooleanExtra("isSpeakerOn", this.f6543H);
                m7366I0().postDelayed(new Runnable() { // from class: p2.l2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f20508b.m6178Oc();
                    }
                }, 100L);
            }
        }
        m6747Y8();
        UploadMultipleChatMediaHelperQueue.m16892F().m16942u(this.f6613S3);
        this.f6616T0.m7130w(new C1375e.c() { // from class: p2.m2
            @Override // com.cyberlink.meeting.page.p032m.C1375e.c
            /* renamed from: a */
            public final void mo7137a(C5926f c5926f) {
                this.f20517a.m6189Pc(c5926f);
            }
        });
        this.f6616T0.m7131x(new C1375e.c() { // from class: p2.n2
            @Override // com.cyberlink.meeting.page.p032m.C1375e.c
            /* renamed from: a */
            public final void mo7137a(C5926f c5926f) {
                this.f20530a.m6200Qc(c5926f);
            }
        });
        this.f6616T0.m7129v(new C1375e.c() { // from class: p2.o2
            @Override // com.cyberlink.meeting.page.p032m.C1375e.c
            /* renamed from: a */
            public final void mo7137a(C5926f c5926f) {
                this.f20544a.m6122Jc(c5926f);
            }
        });
        if (this.f6588O2 == BreakoutRoomState.PARENT) {
            this.f6655Z3 = new ArrayList(MeetingManager.m5618k());
            this.f6661a4 = new ArrayList(MeetingManager.m5617j());
        }
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCreate() OUT");
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Log.d("MeetingActivity", "[onCreateContextMenu]");
        if (contextMenu == null || view == null || this.f6616T0 == null) {
            return;
        }
        this.f6716i4 = view;
        view.setEnabled(false);
        Integer num = (Integer) view.getTag(R.id.tag_Position);
        if (num == null) {
            Log.e("MeetingActivity", "[onCreateContextMenu] invalid tag");
            return;
        }
        int iIntValue = num.intValue();
        if (iIntValue < 0) {
            Log.e("MeetingActivity", "[onCreateContextMenu] invalid index");
            return;
        }
        Log.d("MeetingActivity", "[onCreateContextMenu] index:" + iIntValue);
        int itemViewType = this.f6616T0.getItemViewType(iIntValue);
        ChatMsg chatMsg = (ChatMsg) this.f6616T0.getItem(iIntValue);
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent();
        intent.putExtra("data", iIntValue);
        m6875vf(itemViewType, chatMsg, arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        int i9 = 0;
        while (i9 < arrayList.size()) {
            C6696m5 c6696m5 = arrayList.get(i9);
            i9++;
            contextMenu.add(0, c6696m5.f22367a, i9, getResources().getString(c6696m5.f22368b)).setIntent(intent);
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() throws IllegalStateException, IllegalAccessException, Resources.NotFoundException, IllegalArgumentException, InvocationTargetException {
        TelephonyManager telephonyManager;
        super.onDestroy();
        MeetingManager.m5633z(this.f6711i);
        C5321e.m20824o().m20832B0(this.f6758o4);
        UploadMultipleChatMediaHelperQueue.m16892F().m16931W(this.f6613S3);
        m6729V8();
        m6711S8();
        m6663Jg();
        m6705Qg();
        m6657Ig();
        m6779dh();
        m6681Mg();
        m6675Lg();
        m6876vg();
        m6738We();
        if (this.f6539G1.isHeld()) {
            this.f6539G1.release();
            ULogUtility.m16680p("WakeLock", "[" + this.f6748n1 + "] release wakeLock - MeetingActivity(" + this.f6748n1 + ") onDestroy");
        }
        PowerManager.WakeLock wakeLock = this.f6545H1;
        if (wakeLock != null && wakeLock.isHeld()) {
            this.f6545H1.release();
        }
        m6709Re();
        if (this.f6765p4 && (telephonyManager = (TelephonyManager) getSystemService("phone")) != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                telephonyManager.unregisterTelephonyCallback(this.f6772q4);
            } else {
                telephonyManager.listen(this.f6779r4, 0);
            }
        }
        if (this.f6700g2) {
            m6670L8(false);
        }
        ToneGenerator toneGenerator = this.f6576M2;
        if (toneGenerator != null) {
            toneGenerator.release();
        }
        if (this.f6818x3 != null) {
            m7366I0().removeCallbacks(this.f6818x3);
        }
        if (this.f6595P3 != null) {
            m7366I0().removeCallbacks(this.f6595P3);
        }
        if (this.f6757o3 != null) {
            m7366I0().removeCallbacks(this.f6757o3);
        }
        m6815l9(this.f6708h3);
        m6815l9(this.f6806v3);
        m6815l9(this.f6812w3);
        m6815l9(this.f6505A3);
        m6815l9(this.f6511B3);
        m6815l9(this.f6666b3);
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onDestroy()");
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        if (i9 != 85 && i9 != 126 && i9 != 127) {
            return super.onKeyDown(i9, keyEvent);
        }
        if (this.f6513C) {
            m6633Fe("media button");
            return true;
        }
        this.f6732l = MimeTypes.BASE_TYPE_AUDIO;
        m6726Ue();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.f6771q3) {
            NileNetwork nileNetworkM5610c = MeetingManager.m5610c(this.f6711i);
            this.f6617T1 = nileNetworkM5610c;
            nileNetworkM5610c.m4955n8(this);
            m6725Ua();
            m6696Pa();
            m6894yf();
            m6630F8();
            m6892yb();
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onPause()");
        this.f6830z3 = false;
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.f4919e.m4467t("MeetingActivity", "onPause");
            if (this.f6559J3) {
                m6734Vg();
            }
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() throws Resources.NotFoundException {
        super.onResume();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onResume()");
        boolean z8 = true;
        this.f6830z3 = true;
        if (this.f6643X3) {
            this.f6643X3 = false;
            m6887xf(false);
            m6734Vg();
            m6849qg();
        }
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null && this.f6525E) {
            nileNetwork.f4919e.m4467t("MeetingActivity", "onResume");
            this.f6617T1.m4918V7();
        }
        if (this.f6581N1 && m6766bb()) {
            m6673Le();
        } else if (!this.f6581N1 && m6761ab()) {
            m6667Ke();
        }
        if (getResources().getConfiguration().orientation != 1 && this.f6581N1) {
            z8 = false;
        }
        m6741X8(z8);
        m6793ha();
        if (this.f6571L3) {
            m6849qg();
            this.f6571L3 = false;
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() throws Resources.NotFoundException {
        super.onStart();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onStart()");
        f6496v5 = true;
        m6610Bg();
        C6374k.m24485c().m24487b(this.f6794t5);
        m6652I8(this.f6603R.getCurrentItem());
        C6468p.m24787m().m24794k(this.f6709h4);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onStop()");
        f6496v5 = false;
        m6693Og();
        C6374k.m24485c().m24493j(this.f6794t5);
        C6468p.m24787m().m24800x(this.f6709h4);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onWindowFocusChanged() | hasFocus = " + z8);
        if (z8 && this.f6539G1.isHeld()) {
            this.f6539G1.release();
            ULogUtility.m16680p("WakeLock", "[" + this.f6748n1 + "] release wakeLock - MeetingActivity(" + this.f6748n1 + ") onWindowFocus");
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: p0 */
    public void mo5138p0(int i9) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCalleeOnline");
        this.f6537G = true;
        m6877vh(MeetingEstablishStep.CALLER_M_SERVER_CALLEE_ONLINE);
    }

    /* renamed from: p9 */
    public final void m6839p9(String str) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] finish meetingActivity | reason = " + str);
        NotificationHelper.m14057A();
        if (!m6847qb()) {
            finish();
            return;
        }
        NileNetwork nileNetwork = this.f6617T1;
        long jM4972w3 = nileNetwork == null ? 0L : nileNetwork.m4972w3();
        if (Globals.m7388i0().m7591p()) {
            m6751Yf();
        } else {
            Meeting.Creator creator = this.f6802v;
            m6680Mf(getString(R.string.clm_meeting_end_dialog_message_with_host, (creator == null || C5170o0.m20170e(creator.displayName)) ? "the host" : this.f6802v.displayName, Long.valueOf(jM4972w3 / 60)));
        }
        m6738We();
    }

    /* renamed from: pa */
    public final void m6840pa() throws Resources.NotFoundException {
        ULogUtility.m16680p("MeetingActivity", "[" + this.f6748n1 + "] [hideAudioMeetingLayout] start ");
        if (this.f6778r3) {
            return;
        }
        if (this.f6603R.getCurrentItem() != m6671L9()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(this.f6748n1);
            sb.append("] [hideAudioMeetingLayout] ParticipantType.PARTICIPANT count : ");
            ParticipantsAdapter participantsAdapter = this.f6677d0;
            ParticipantsAdapter.ParticipantType participantType = ParticipantsAdapter.ParticipantType.PARTICIPANT;
            sb.append(participantsAdapter.m7044n(participantType));
            ULogUtility.m16680p("MeetingActivity", sb.toString());
            if (this.f6677d0.m7044n(participantType) > 1) {
                m6807jg();
                if (this.f6621U.f7078a.getVisibility() == 8) {
                    this.f6621U.f7078a.setVisibility(0);
                    m6652I8(this.f6603R.getCurrentItem());
                }
            }
        }
        this.f6586O0.setVisibility(8);
        this.f6796u0.setVisibility(0);
        this.f6698g0.findViewById(R.id.fakeStatusBar).setBackgroundResource(R.color.transparent);
        this.f6782s0.setVisibility(this.f6559J3 ? 8 : 0);
        m6611Bh();
        m6902zh();
    }

    /* renamed from: pb */
    public final boolean m6841pb() {
        return this.f6547H3 == 0;
    }

    /* renamed from: pf */
    public final void m6842pf(ArrayList<ImageItem> arrayList, int i9, Date date) {
        Group group = new Group();
        group.f13711J = false;
        String str = this.f6711i;
        group.f13723j = str;
        group.f13727n = Long.parseLong(str);
        group.f13718e = "Meeting:" + this.f6711i;
        final List<MessageObj> listM14557H0 = C2925v.m14557H0(arrayList, i9, date, Collections.singletonList(group), false, true);
        runOnUiThread(new Runnable() { // from class: p2.c4
            @Override // java.lang.Runnable
            public final void run() {
                this.f20382b.m6509rd(listM14557H0);
            }
        });
    }

    /* renamed from: pg */
    public void m6843pg() {
        C0012b c0012b = this.f6804v1;
        String strM68c = c0012b != null ? c0012b.f66b.m68c() : "";
        if (TextUtils.isEmpty(strM68c)) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.clm_meeting_stop_desktop_Sharing_old_version_error, strM68c)).setCancelable(true).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
        builderM16382a.show();
    }

    /* renamed from: ph */
    public final void m6844ph(boolean z8) throws Resources.NotFoundException {
        int i9;
        boolean z9;
        if (this.f6587O1) {
            this.f6621U.m7140c();
            this.f6627V.m7140c();
            this.f6633W.m7140c();
            this.f6621U.m7139b();
            this.f6627V.m7139b();
            this.f6633W.m7139b();
            boolean z10 = true;
            boolean z11 = this.f6603R.getCurrentItem() == m6671L9() && !this.f6620T4;
            int iM6695P9 = m6695P9();
            int iM6689O9 = m6689O9();
            float fM6646H9 = m6646H9(iM6695P9);
            float fM6653I9 = m6653I9(iM6689O9);
            if (this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT) > 1) {
                float f9 = fM6646H9;
                float f10 = fM6653I9;
                int i10 = 0;
                int i11 = 0;
                while (i11 < this.f6639X.size()) {
                    C1376f c1376f = this.f6639X.get(i11);
                    if (this.f6645Y.get(i11) == null) {
                        c1376f.f7078a.setVisibility(8);
                        c1376f.f7080c.setVisibility(8);
                        z9 = z10;
                    } else {
                        int i12 = i10 + 1;
                        c1376f.f7089l = C5177q1.m20228b(c1376f.f7078a, iM6695P9, iM6689O9, f9, f10, z8 ? 300 : 0);
                        if (m6766bb()) {
                            f10 += iM6689O9;
                            z9 = true;
                        } else {
                            z9 = true;
                            if (i12 == 1) {
                                f9 = fM6646H9 + iM6695P9;
                                f10 = fM6653I9;
                            } else if (i12 == 2) {
                                f10 = fM6653I9 + iM6689O9;
                                f9 = fM6646H9;
                            }
                        }
                        c1376f.f7089l.addListener(m6638G9(c1376f));
                        if (c1376f != this.f6621U || z11) {
                            c1376f.f7089l.start();
                        }
                        if (!this.f6620T4) {
                            c1376f.f7078a.setVisibility(0);
                        }
                        i10 = i12;
                    }
                    i11++;
                    z10 = z9;
                }
                i9 = i10;
            } else {
                i9 = 0;
            }
            m6872uh(i9);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: q */
    public void mo5139q(long j9, long j10) {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onEventTime");
        m6782ef();
        this.f6658a1.setSelected(this.f6549I);
        this.f6502A0.setVisibility(this.f6549I ? 8 : 0);
        m7366I0().post(this.f6578M4);
    }

    /* renamed from: q9 */
    public final void m6845q9() {
        ViewGroup.LayoutParams layoutParams = this.f6621U.f7078a.getLayoutParams();
        int currentItem = this.f6603R.getCurrentItem();
        layoutParams.width = m6631F9(currentItem);
        layoutParams.height = m6612C9(currentItem);
        this.f6621U.f7078a.requestLayout();
    }

    /* renamed from: qa */
    public final void m6846qa() {
        if (m6765ba().getAlpha() != BitmapDescriptorFactory.HUE_RED) {
            m6765ba().animate().cancel();
            m6765ba().animate().alpha(BitmapDescriptorFactory.HUE_RED).start();
        }
    }

    /* renamed from: qb */
    public final boolean m6847qb() {
        NileNetwork nileNetwork;
        return this.f6513C && !this.f6742m2 && (nileNetwork = this.f6617T1) != null && nileNetwork.m4974x3() >= TimeUnit.MINUTES.toSeconds(20L) && this.f6617T1.m4972w3() < TimeUnit.HOURS.toSeconds(24L);
    }

    /* renamed from: qf */
    public final void m6848qf(MessageObj messageObj, VideoItem videoItem) {
        UploadUtils.m16965l("Upload Performance", "0.2. sendVideo(MessageObj); ==== start");
        if (messageObj == null || videoItem == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        String str = "Chat:" + this.f6711i;
        UploadUtils.m16965l("Upload Performance", "0.2 sendVoice(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
        UploadMultipleChatMediaHelperQueue.m16892F().m16947z(false, str, videoItem, messageObj, this.f6711i, true);
        UploadUtils.m16965l("Upload Performance", "0.2. sendVoice(MessageObj); ==== end");
    }

    /* renamed from: qg */
    public final void m6849qg() {
        if (Build.VERSION.SDK_INT < 29 || !Globals.m7396z1()) {
            C5187v0.m20268d(getString(R.string.clm_meeting_desktop_sharing_stop_by_host_hint));
        } else {
            NotificationHelper.m14071O(null, getString(R.string.clm_meeting_desktop_sharing_stop_by_host_hint_title), getString(R.string.clm_meeting_desktop_sharing_stop_by_host_hint), new Intent(Globals.m7388i0().getApplicationContext(), (Class<?>) MeetingActivity.class), null);
            this.f6571L3 = true;
        }
        this.f6559J3 = false;
    }

    /* renamed from: qh */
    public final void m6850qh() {
        if (this.f6603R.getCurrentItem() != m6671L9()) {
            return;
        }
        for (int i9 = 0; i9 < this.f6639X.size(); i9++) {
            C1376f c1376f = this.f6639X.get(i9);
            C0012b c0012b = this.f6645Y.get(i9);
            if (c0012b != null && c1376f != null) {
                boolean zM6811kb = m6811kb(c0012b);
                c1376f.f7080c.setVisibility(zM6811kb ? 0 : 8);
                c1376f.f7088k.setVisibility(zM6811kb ? 8 : 0);
                if (zM6811kb) {
                    c1376f.f7082e.setBackgroundResource(R.drawable.bg_meeting_gallery_name);
                } else {
                    c1376f.f7082e.setBackground(null);
                    this.f6755o1.m6013r(c0012b.f66b, c1376f.f7088k, AvatarDrawableCache.AvatarSize.GALLERY);
                    c1376f.f7078a.setBackgroundResource(R.color.clm_gallery_background);
                }
            }
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: r */
    public void mo5140r(C0012b c0012b) {
        AlertDialog alertDialog = this.f6542G4;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6542G4.dismiss();
        }
        if (this.f6618T2) {
            AlertDialog alertDialogCreate = C3123g.m16382a(this).setMessage(getString(R.string.clm_meeting_want_to_record_meeting, c0012b.m86e())).setPositiveButton(R.string.clm_meeting_allow, new DialogInterface.OnClickListener() { // from class: p2.j0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f20480b.m6256Vc(dialogInterface, i9);
                }
            }).setNegativeButton(R.string.skip, new DialogInterface.OnClickListener() { // from class: p2.k0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f20493b.m6267Wc(dialogInterface, i9);
                }
            }).setCancelable(false).create();
            this.f6542G4 = alertDialogCreate;
            alertDialogCreate.show();
        }
    }

    /* renamed from: r9 */
    public final void m6851r9() {
        m6862ta(false);
        m6686Nf(true);
        if (m6794hb()) {
            this.f6691f0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(500L).setInterpolator(this.f6703g5).start();
        }
        m6860sh();
    }

    /* renamed from: ra */
    public final void m6852ra() {
        if (m6776da().getAlpha() != BitmapDescriptorFactory.HUE_RED) {
            m6776da().animate().cancel();
            m6776da().animate().alpha(BitmapDescriptorFactory.HUE_RED).start();
        }
    }

    /* renamed from: rf */
    public final void m6853rf(VideoItem videoItem, int i9, Date date) {
        if (videoItem == null) {
            return;
        }
        try {
            UploadUtils.m16965l("Upload Performance", "0.1 sendVideo(videoPath); ==== start");
            String strM16218g = videoItem.m16218g();
            Uri uriM16510Z1 = CLUtility.m16510Z1(videoItem.m16219h());
            if (CLUtility.m16613z1(strM16218g, uriM16510Z1)) {
                UploadUtils.m16965l("Upload Performance", "0.1. generateVideoContent();");
                String strM14579W = C2925v.m14579W(strM16218g, uriM16510Z1, null, String.valueOf(videoItem.m16213b()), videoItem.m16216e(), String.valueOf(videoItem.m16217f()), String.valueOf(videoItem.m16215d()), String.valueOf(videoItem.m16220i()), String.valueOf(videoItem.m16214c()));
                UploadUtils.m16965l("Upload Performance", "0.1. generateMessage();");
                MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(this.f6711i), MessageObj.MessageType.Video, strM14579W, i9, date);
                UploadUtils.m16965l("Upload Performance", "0.1. getMessageDao().insert();");
                C2950b0.m14916o().m15157B(messageObjM14558I);
                UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(messageObj);");
                m6848qf(messageObjM14558I, videoItem);
                C1375e c1375e = this.f6616T0;
                if (c1375e != null) {
                    c1375e.m7118g(messageObjM14558I);
                    m6752Yg(true, 150L);
                }
            }
            UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(voicePath); ==== end");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: rg */
    public final void m6854rg() {
        AlertDialog alertDialog = this.f6548H4;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f6548H4.dismiss();
        }
        AlertDialog alertDialogCreate = C3123g.m16382a(this).setMessage(getString(R.string.clm_system_request_mic_mute)).setPositiveButton(R.string.close, new DialogInterface.OnClickListener() { // from class: p2.y4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f20672b.m6312ae(dialogInterface, i9);
            }
        }).create();
        this.f6548H4 = alertDialogCreate;
        alertDialogCreate.show();
    }

    /* renamed from: rh */
    public final void m6855rh() {
        if (this.f6820y == InviteCallType.CALLER) {
            String string = getString(R.string.calling);
            MeetingEstablishStep meetingEstablishStep = this.f6573M;
            if (meetingEstablishStep == MeetingEstablishStep.UNKNOWN) {
                string = string.replace("…", "");
            } else if (meetingEstablishStep == MeetingEstablishStep.CALLER_DO_SERVER_CREATE_MEETING_COMPLETE) {
                string = string.replace("…", ".");
            } else if (meetingEstablishStep == MeetingEstablishStep.CALLER_M_SERVER_JOIN_MEETING_COMPLETE) {
                string = string.replace("…", "..");
            } else if (meetingEstablishStep == MeetingEstablishStep.CALLER_XMPP_SEND_INVITE_EVENT_COMPLETE) {
                Group group = this.f6808w;
                string = (group == null || !group.m15750g()) ? string.replace("…", "...") : getString(R.string.clm_invite_reaching_callee, this.f6808w.f13717d);
            } else if (meetingEstablishStep == MeetingEstablishStep.CALLER_M_SERVER_CALLEE_ONLINE) {
                Group group2 = this.f6808w;
                if (group2 == null || !group2.m15750g()) {
                    string = string.replace("…", "....");
                } else {
                    String str = this.f6808w.f13717d;
                    int iIntValue = 15;
                    if (str.length() > 15) {
                        Iterator<Pair<Integer, Integer>> it = CLUtility.m16483R(str).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Pair<Integer, Integer> next = it.next();
                            if (((Integer) next.second).intValue() > 15) {
                                iIntValue = ((Integer) next.first).intValue();
                                break;
                            }
                        }
                        str = C5170o0.m20167b(str, iIntValue) + "...";
                    }
                    string = getString(R.string.clm_invite_callee_reached, str);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f6706h1.getLayoutParams();
                    layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, 0);
                    this.f6706h1.setLayoutParams(layoutParams);
                }
            }
            this.f6550I0.setText(string);
            C5179r0.m20247b(this.f6550I0, getResources().getInteger(R.integer.clm_status_layout_line_num));
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: s */
    public void mo5141s(List<C0012b> list) {
        if (this.f6617T1 == null) {
            return;
        }
        this.f6677d0.f6990d.m2912g();
        this.f6677d0.f6990d.m2908c(list);
        this.f6677d0.f6990d.m2915j();
        if (this.f6603R.getCurrentItem() == m6671L9()) {
            m6832nh();
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: s0 */
    public void mo5142s0(int i9) {
    }

    /* renamed from: sa */
    public final void m6856sa() {
        m7366I0().postDelayed(new Runnable() { // from class: p2.x
            @Override // java.lang.Runnable
            public final void run() {
                this.f20653b.m6255Vb();
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    /* renamed from: sb */
    public final boolean m6857sb() {
        return Settings.System.getInt(getContentResolver(), "vibrate_when_ringing", 0) == 1;
    }

    /* renamed from: se */
    public final void m6858se(String str, boolean z8, ImageView imageView) {
        C6127a.m23474o(this, imageView, str, z8 ? R.drawable.pic_default : R.drawable.pic_default_group);
    }

    /* renamed from: sf */
    public final void m6859sf(boolean z8) {
        if (this.f6733l0 != null) {
            for (int i9 = 0; i9 < this.f6733l0.getChildCount(); i9++) {
                this.f6733l0.getChildAt(i9).setClickable(z8);
            }
        }
        if (this.f6705h0 != null) {
            for (int i10 = 0; i10 < this.f6705h0.getChildCount(); i10++) {
                this.f6705h0.getChildAt(i10).setClickable(z8);
            }
        }
        ImageView imageView = this.f6600Q2;
        if (imageView != null) {
            imageView.setClickable(z8);
        }
    }

    /* renamed from: sh */
    public final boolean m6860sh() {
        if (!this.f6581N1 || !this.f6647Y1) {
            m6864tf();
            return false;
        }
        if (m6766bb()) {
            this.f6628V0.setVisibility(0);
            this.f6634W0.setVisibility(8);
            return true;
        }
        this.f6634W0.setVisibility(0);
        this.f6628V0.setVisibility(8);
        return true;
    }

    /* renamed from: t9 */
    public final SpannableString m6861t9(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
        return spannableString;
    }

    /* renamed from: ta */
    public final void m6862ta(boolean z8) {
        if ((!this.f6534F2 || this.f6570L2) && !this.f6778r3) {
            m7366I0().removeCallbacks(this.f6731k5);
            m6717T8();
            float fM6897z9 = m6897z9();
            long j9 = z8 ? 500L : 0L;
            this.f6733l0.animate().alpha(BitmapDescriptorFactory.HUE_RED).setListener(this.f6745m5).setDuration(j9).start();
            this.f6600Q2.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).start();
            this.f6705h0.animate().translationY(fM6897z9).alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6710h5).start();
            this.f6719j0.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).start();
            this.f6796u0.animate().translationY(fM6897z9).alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6710h5).start();
            this.f6609S.animate().translationY(getResources().getDimension(R.dimen.t28dp) + fM6897z9).setDuration(j9).setInterpolator(this.f6710h5).start();
            if (m6766bb()) {
                this.f6684e0.animate().translationY(fM6897z9).setDuration(j9).setInterpolator(this.f6710h5).start();
            }
            float height = this.f6733l0.getHeight();
            if (this.f6581N1) {
                float f9 = height * (-1.0f);
                this.f6782s0.animate().translationY(f9).setDuration(j9).setInterpolator(this.f6710h5).alpha(BitmapDescriptorFactory.HUE_RED).start();
                this.f6789t0.animate().translationY(f9).setDuration(j9).setInterpolator(this.f6710h5).alpha(BitmapDescriptorFactory.HUE_RED).start();
            }
            if (!this.f6641X1) {
                m6885xa(fM6897z9, j9);
            }
            if (this.f6587O1) {
                int currentItem = this.f6603R.getCurrentItem();
                if (currentItem == 1 || currentItem == m6671L9()) {
                    this.f6726k0.animate().alpha(1.0f).setDuration(j9).start();
                }
            }
        }
    }

    /* renamed from: te */
    public final void m6863te(View view, int i9, int i10) {
        int dimension = (int) getResources().getDimension(R.dimen.t10dp);
        int iM6736W9 = (m6736W9() - i10) - dimension;
        float y8 = view.getY();
        float f9 = dimension;
        if (y8 < f9) {
            y8 = f9;
        } else {
            float f10 = iM6736W9;
            if (y8 > f10) {
                y8 = f10;
            }
        }
        view.animate().cancel();
        C5177q1.m20228b(view, i9, i10, (m6742X9() - dimension) - (i9 / 2.0f), y8 + (i10 / 2.0f), 300).start();
    }

    /* renamed from: tf */
    public final void m6864tf() {
        this.f6628V0.setVisibility(8);
        this.f6634W0.setVisibility(8);
    }

    /* renamed from: tg */
    public final void m6865tg(int i9) {
        C0012b c0012bM7042l = this.f6677d0.m7042l(i9);
        String strM68c = c0012bM7042l != null ? c0012bM7042l.f66b.m68c() : "";
        if (TextUtils.isEmpty(strM68c)) {
            return;
        }
        final Dialog dialogM16385d = C3123g.m16385d(this, "", getResources().getString(R.string.clm_meeting_waiting_room_old_user, strM68c));
        TextView textView = (TextView) dialogM16385d.findViewById(R.id.v_btn);
        textView.setText(getString(R.string.ok));
        textView.setOnClickListener(new View.OnClickListener() { // from class: p2.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16385d.dismiss();
            }
        });
        dialogM16385d.show();
    }

    /* renamed from: th */
    public final void m6866th() {
        this.f6533F1 = this.f6616T0.m7122k();
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: u0 */
    public void mo5144u0(boolean z8) {
        this.f6647Y1 = z8;
        m6860sh();
    }

    /* renamed from: u9 */
    public final long m6867u9() {
        List<Long> list;
        Group group = this.f6808w;
        if (group != null && (list = group.f13702A) != null && list.size() > 0) {
            long jLongValue = Globals.m7388i0().m7568k1().longValue();
            Iterator<Long> it = this.f6808w.f13702A.iterator();
            while (it.hasNext()) {
                long jLongValue2 = it.next().longValue();
                if (jLongValue != jLongValue2) {
                    return jLongValue2;
                }
            }
        }
        return C1260a.f6268e.longValue();
    }

    /* renamed from: ua */
    public final void m6868ua(int i9) {
        this.f6627V.f7078a.setVisibility(8);
        this.f6627V.f7080c.setVisibility((i9 == 1 && this.f6581N1 && this.f6593P1) ? 0 : 8);
        this.f6633W.f7078a.setVisibility(8);
        this.f6633W.f7080c.setVisibility(8);
    }

    /* renamed from: ue */
    public final void m6869ue(boolean z8) throws Resources.NotFoundException {
        int iM6707R9;
        int iM6730V9;
        int iM6724U9 = m6724U9();
        int iM6712S9 = m6712S9();
        if (z8) {
            iM6707R9 = m6730V9();
            iM6730V9 = m6707R9();
            iM6712S9 = (int) (iM6712S9 + (this.f6651Z.getHeight() - m6890y9()));
        } else {
            iM6707R9 = m6707R9();
            iM6730V9 = m6730V9();
            iM6724U9 = 0;
        }
        ValueAnimator valueAnimatorM20228b = C5177q1.m20228b(this.f6684e0, iM6707R9, iM6730V9, (m6742X9() - iM6724U9) - (r4 / 2), (m6736W9() - iM6712S9) - (r5 / 2), 0);
        this.f6684e0.setTag(R.id.tag_Local_Renderer_Animator, valueAnimatorM20228b);
        valueAnimatorM20228b.start();
    }

    /* renamed from: uf */
    public final void m6870uf(boolean z8) {
        this.f6628V0.setSelected(z8);
        this.f6634W0.setSelected(z8);
    }

    /* renamed from: ug */
    public final void m6871ug() {
        if (this.f6618T2) {
            List<C1262a> listM7046p = this.f6677d0.m7046p();
            String strM5731c = listM7046p.get(0).m5731c();
            if (TextUtils.isEmpty(strM5731c)) {
                this.f6824y3 = true;
                return;
            }
            this.f6824y3 = false;
            String string = listM7046p.size() == 1 ? getString(R.string.clm_meeting_waiting_room_reminder_one, strM5731c) : listM7046p.size() > 1 ? getString(R.string.clm_meeting_waiting_room_reminder_multiple, strM5731c, Integer.valueOf(listM7046p.size() - 1)) : "";
            if (this.f6806v3 == null) {
                this.f6806v3 = C3123g.m16385d(this, "", string);
            }
            TextView textView = (TextView) this.f6806v3.findViewById(R.id.dialogContent);
            textView.setText(string);
            textView.setGravity(17);
            TextView textView2 = (TextView) this.f6806v3.findViewById(R.id.v_btn);
            textView2.setText(getString(R.string.clm_meeting_waiting_room_see_list));
            textView2.setTextColor(getResources().getColor(R.color.you_color_normal_blue));
            textView2.setGravity(17);
            textView2.setTextSize(2, 16.0f);
            WindowManager.LayoutParams attributes = this.f6806v3.getWindow().getAttributes();
            attributes.gravity = 48;
            attributes.y = Globals.m7374X0().getDimensionPixelSize(R.dimen.t70dp);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: p2.y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f20664b.m6347de(view);
                }
            });
            if (this.f6818x3 == null) {
                this.f6818x3 = new Runnable() { // from class: p2.z
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f20674b.m6360ee();
                    }
                };
            }
            if (!this.f6806v3.isShowing()) {
                m7366I0().postDelayed(this.f6818x3, 15000L);
            }
            this.f6806v3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: p2.a0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f20356b.m6373fe(dialogInterface);
                }
            });
            this.f6806v3.show();
        }
    }

    /* renamed from: uh */
    public final void m6872uh(int i9) throws Resources.NotFoundException {
        Object tag = this.f6684e0.getTag(R.id.tag_Local_Renderer_Animator);
        if (tag instanceof Animator) {
            ((Animator) tag).cancel();
        }
        if (m6766bb()) {
            m6869ue(true);
        } else if (i9 == 1) {
            m6880we();
        } else if (i9 == 2) {
            m6869ue(false);
        } else {
            if (i9 != 3) {
                throw new IllegalArgumentException("invalid remoteViewCount");
            }
            m6874ve();
        }
        ViewGroup.LayoutParams layoutParams = this.f6827z0.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = this.f6821y0.getLayoutParams();
        ViewGroup.LayoutParams layoutParams3 = this.f6502A0.getLayoutParams();
        if (m6761ab() && i9 == 3) {
            layoutParams.height = (int) getResources().getDimension(R.dimen.t22dp);
            layoutParams.width = (int) getResources().getDimension(R.dimen.t22dp);
            layoutParams2.height = (int) getResources().getDimension(R.dimen.t30dp);
            layoutParams2.width = (int) getResources().getDimension(R.dimen.t30dp);
            layoutParams3.height = (int) getResources().getDimension(R.dimen.t30dp);
            layoutParams3.width = (int) getResources().getDimension(R.dimen.t30dp);
            return;
        }
        layoutParams.height = (int) getResources().getDimension(R.dimen.t33dp);
        layoutParams.width = (int) getResources().getDimension(R.dimen.t33dp);
        layoutParams2.height = (int) getResources().getDimension(R.dimen.t40dp);
        layoutParams2.width = (int) getResources().getDimension(R.dimen.t43dp);
        layoutParams3.height = (int) getResources().getDimension(R.dimen.t40dp);
        layoutParams3.width = (int) getResources().getDimension(R.dimen.t43dp);
    }

    /* renamed from: va */
    public final void m6873va() {
        Iterator<C1376f> it = this.f6639X.iterator();
        while (it.hasNext()) {
            C1376f next = it.next();
            next.f7082e.setText("");
            next.f7082e.setBackground(null);
            next.f7083f.setVisibility(4);
            next.f7084g.setVisibility(8);
            next.f7088k.setVisibility(8);
        }
    }

    /* renamed from: ve */
    public final void m6874ve() {
        int iM6695P9 = m6695P9();
        int iM6689O9 = m6689O9();
        ValueAnimator valueAnimatorM20228b = C5177q1.m20228b(this.f6684e0, iM6695P9, iM6689O9, m6646H9(iM6695P9) + iM6695P9, m6653I9(iM6689O9) + iM6689O9, 0);
        this.f6684e0.setTag(R.id.tag_Local_Renderer_Animator, valueAnimatorM20228b);
        valueAnimatorM20228b.start();
    }

    /* renamed from: vf */
    public final void m6875vf(int i9, ChatMsg chatMsg, List<C6696m5> list) {
        if (i9 == 0 || 21 == i9) {
            list.add(this.f6683e);
        } else {
            list.add(this.f6690f);
        }
    }

    /* renamed from: vg */
    public final void m6876vg() {
        SurfaceViewRenderer surfaceViewRenderer = this.f6653Z1;
        if (surfaceViewRenderer != null) {
            surfaceViewRenderer.release();
        }
        C1376f c1376f = this.f6621U;
        if (c1376f != null) {
            c1376f.m7141d();
        }
        C1376f c1376f2 = this.f6627V;
        if (c1376f2 != null) {
            c1376f2.m7141d();
        }
        C1376f c1376f3 = this.f6633W;
        if (c1376f3 != null) {
            c1376f3.m7141d();
        }
    }

    /* renamed from: vh */
    public final void m6877vh(MeetingEstablishStep meetingEstablishStep) {
        if (meetingEstablishStep.value >= this.f6573M.value) {
            this.f6573M = meetingEstablishStep;
            runOnUiThread(new Runnable() { // from class: p2.n4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20534b.m6476oe();
                }
            });
            return;
        }
        Log.d("MeetingActivity", "updateMeetingEstablishStep fail, not allow step reverse | currentStep = " + this.f6573M + " , updateStep = " + meetingEstablishStep);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: w */
    public void mo5145w(int i9) {
        Log.d("MeetingActivity", "[FU] onFileUploadCanceled: " + i9);
        UploadMultipleChatMediaHelperQueue.m16892F().m16921H(i9);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: w0 */
    public void mo5146w0(boolean z8) {
        if (z8) {
            m6658Ih();
            m6854rg();
        }
    }

    /* renamed from: w9 */
    public final void m6878w9() {
        Intent intent = getIntent();
        this.f6711i = intent.getStringExtra("meetingId");
        this.f6718j = intent.getStringExtra("password");
        String stringExtra = intent.getStringExtra("ltiToken");
        this.f6687e3 = stringExtra;
        if (!C5170o0.m20170e(stringExtra)) {
            this.f6823y2 = true;
        }
        String stringExtra2 = intent.getStringExtra("type");
        this.f6725k = stringExtra2;
        if (C5170o0.m20170e(stringExtra2)) {
            this.f6725k = MimeTypes.BASE_TYPE_VIDEO;
        }
        this.f6732l = this.f6725k;
        this.f6814x = intent.getStringExtra("action");
        this.f6774r = intent.getStringExtra("displayName");
        this.f6808w = (Group) intent.getParcelableExtra("group");
        this.f6501A = intent.getBooleanExtra("isPreJoinMeeting", false);
        this.f6679d2 = intent.getBooleanExtra("isSecure", false);
        this.f6795u = (Friend) intent.getParcelableExtra("inviter");
        this.f6820y = (InviteCallType) intent.getSerializableExtra("inviteCallType");
        if (C5170o0.m20170e(this.f6774r)) {
            this.f6774r = m6553v9(this);
        }
        this.f6519D = this.f6820y != null;
        Group group = this.f6808w;
        this.f6826z = group != null && group.f13716c.equals("Dual");
        this.f6753o = intent.getStringExtra("meetingMServerAddress");
        this.f6760p = intent.getStringExtra("meetingMServerToken");
        this.f6534F2 = intent.getBooleanExtra("isFromPhoneLine", false);
        this.f6540G2 = intent.getStringExtra("callNumber");
        this.f6546H2 = intent.getStringExtra("callDisplayName");
        this.f6552I2 = intent.getBooleanExtra("isFromContact", false);
        this.f6558J2 = intent.getStringExtra("callUserId");
        boolean booleanExtra = intent.getBooleanExtra("isFromNotification", false);
        this.f6770q2 = booleanExtra;
        if (booleanExtra) {
            this.f6777r2 = intent.getStringExtra("notificationAction");
            this.f6784s2 = intent.getStringExtra("notificationName");
            this.f6791t2 = intent.getStringExtra("notificationAvatar");
        }
        BreakoutRoomState breakoutRoomState = (BreakoutRoomState) intent.getSerializableExtra("breakoutRoomState");
        this.f6588O2 = breakoutRoomState;
        if (breakoutRoomState == null) {
            this.f6588O2 = BreakoutRoomState.NONE;
        }
        BreakoutRoom breakoutRoom = (BreakoutRoom) intent.getParcelableExtra("breakoutRoom");
        if (breakoutRoom != null) {
            this.f6594P2 = breakoutRoom.f6330e;
            this.f6630V2 = breakoutRoom.f6327b;
            this.f6636W2 = breakoutRoom.f6331f;
            this.f6642X2 = breakoutRoom.f6332g;
        }
        this.f6771q3 = intent.getBooleanExtra("moveToWaitingRoom", false);
    }

    /* renamed from: wa */
    public final void m6879wa() {
        this.f6519D = false;
        this.f6544H0.setVisibility(8);
        this.f6621U.f7078a.setVisibility(0);
    }

    /* renamed from: we */
    public final void m6880we() {
        ValueAnimator valueAnimatorM20228b = C5177q1.m20228b(this.f6684e0, m6707R9(), m6730V9(), m6742X9() - (r1 / 2), (m6736W9() / 3) * 2, 0);
        this.f6684e0.setTag(R.id.tag_Local_Renderer_Animator, valueAnimatorM20228b);
        valueAnimatorM20228b.start();
    }

    /* renamed from: wf */
    public final void m6881wf() {
        NileNetwork nileNetwork = this.f6617T1;
        if (nileNetwork != null) {
            nileNetwork.m4959p8(this.f6649Y3, this.f6655Z3);
            this.f6649Y3++;
            m6723U8();
            m6895yg();
        }
    }

    /* renamed from: wg */
    public final void m6882wg(boolean z8) {
        m7366I0().removeCallbacks(this.f6744m4);
        m7366I0().postDelayed(this.f6744m4, z8 ? 130000L : 70000L);
    }

    /* renamed from: wh */
    public final void m6883wh() {
        int iM7044n = this.f6677d0.m7044n(ParticipantsAdapter.ParticipantType.PARTICIPANT);
        this.f6677d0.notifyDataSetChanged();
        this.f6663b0.setText(String.valueOf(iM7044n));
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: x0 */
    public void mo5147x0(String str) {
        Log.d("MeetingActivity", "onSystemMsgReceived: " + str);
        C5187v0.m20268d(str);
    }

    /* renamed from: x9 */
    public final float m6884x9() {
        return this.f6712i0.getTop() + this.f6705h0.getTop();
    }

    /* renamed from: xa */
    public final void m6885xa(float f9, long j9) {
        this.f6628V0.animate().cancel();
        this.f6634W0.animate().cancel();
        this.f6628V0.animate().translationY(f9).alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6710h5).start();
        this.f6634W0.animate().translationY(f9).alpha(BitmapDescriptorFactory.HUE_RED).setDuration(j9).setInterpolator(this.f6710h5).start();
    }

    /* renamed from: xe */
    public final void m6886xe(boolean z8) {
        int iM6624E9 = m6624E9();
        int iM6618D9 = m6618D9();
        int dimension = (int) getResources().getDimension(R.dimen.t10dp);
        this.f6621U.m7140c();
        C1376f c1376f = this.f6621U;
        c1376f.f7089l = C5177q1.m20228b(c1376f.f7078a, iM6624E9, iM6618D9, (m6742X9() - dimension) - (iM6624E9 / 2.0f), (m6736W9() - dimension) - (iM6618D9 / 2.0f), 300);
        this.f6621U.f7089l.addListener(this.f6662a5);
        if (!z8) {
            this.f6621U.f7089l.setDuration(0L);
        }
        this.f6621U.f7089l.start();
    }

    /* renamed from: xf */
    public void m6887xf(boolean z8) {
        Log.i("MeetingActivity", " > setShareScreen(" + z8 + ")");
        if (this.f6617T1 == null) {
            C5187v0.m20268d("NileNetwork instance does not exist.");
            return;
        }
        if (m6835ob()) {
            C5187v0.m20268d(getString(R.string.clm_speaker_desktop_sharing_warning, this.f6804v1.m86e()));
            return;
        }
        if (!z8) {
            m6669Kg();
            return;
        }
        final Runnable runnable = new Runnable() { // from class: p2.t2
            @Override // java.lang.Runnable
            public final void run() {
                this.f20606b.m6521sd();
            }
        };
        if (this.f6549I) {
            runnable.run();
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.clm_meeting_your_microphone_has_not_turn_on));
        builderM16382a.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: p2.u2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                runnable.run();
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.create().show();
    }

    /* renamed from: xg */
    public final void m6888xg() throws IllegalStateException {
        NileNetwork nileNetwork;
        List<Integer> listM5613f;
        ULogUtility.m16670f("MeetingActivity", "startMeeting");
        if (this.f6501A) {
            ArrayList<BreakoutRoom> arrayListM5612e = MeetingManager.m5612e(this.f6711i);
            if (arrayListM5612e != null) {
                this.f6612S2 = arrayListM5612e;
                if (arrayListM5612e.size() > 0) {
                    m6721Tf();
                }
            }
            if (this.f6588O2 != BreakoutRoomState.CHILD && (listM5613f = MeetingManager.m5613f(this.f6711i)) != null) {
                this.f6736l3 = listM5613f;
                if (this.f6797u1 != null) {
                    this.f6618T2 = false;
                    Iterator<Integer> it = listM5613f.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (this.f6797u1.f66b.f63b == it.next().intValue()) {
                                this.f6618T2 = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                this.f6677d0.m7034D(this.f6736l3);
                this.f6677d0.m7033C(this.f6618T2);
                this.f6677d0.notifyDataSetChanged();
            }
            this.f6665b2 = Boolean.TRUE.equals(MeetingManager.m5614g(this.f6711i));
        }
        this.f6513C = true;
        this.f6805v2 = System.currentTimeMillis();
        m6750Ye();
        m6663Jg();
        m6705Qg();
        this.f6796u0.setVisibility(0);
        NotificationHelper.m14076T();
        if (this.f6534F2) {
            setRequestedOrientation(1);
        } else {
            setRequestedOrientation(-1);
        }
        if (this.f6808w != null && this.f6544H0.getVisibility() == 0) {
            m6681Mg();
            m6779dh();
            m6619Da();
            m6879wa();
            m6686Nf(false);
        }
        if (this.f6804v1 == null) {
            Log.i("MeetingActivity", "[" + this.f6748n1 + "] no active participant when meeting start, choose one participant as active.");
            this.f6804v1 = m6759a9(Collections.emptyList());
            this.f6608R4.run();
        }
        m7366I0().removeCallbacks(this.f6688e4);
        m7366I0().removeCallbacks(this.f6695f4);
        if (C5315b.m20803s()) {
            m7366I0().postDelayed(this.f6702g4, C5315b.m20800p());
        }
        if (this.f6543H && m6772cb()) {
            m7366I0().postDelayed(new Runnable() { // from class: p2.e3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f20417b.m6386ge();
                }
            }, 500L);
        }
        if (this.f6513C && !this.f6686e2 && this.f6588O2 != BreakoutRoomState.CHILD) {
            this.f6686e2 = true;
            int i9 = 0;
            for (int i10 = 0; i10 < this.f6677d0.f6990d.m2926u(); i10++) {
                if (!this.f6677d0.f6990d.m2918m(i10).m96o()) {
                    i9++;
                }
            }
            if (i9 > 3 && (nileNetwork = this.f6617T1) != null) {
                nileNetwork.m4951l8(false, new C1330n0());
            }
        }
        if (this.f6534F2 && this.f6829z2 == null) {
            m6708Ra();
        }
    }

    /* renamed from: xh */
    public final void m6889xh() {
        View viewFindViewById = this.f6657a0.findViewById(R.id.meeting_participants_more);
        if (this.f6618T2 || this.f6672c2) {
            viewFindViewById.setVisibility(0);
        } else {
            viewFindViewById.setVisibility(4);
        }
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: y */
    public void mo5148y(ChatMsg chatMsg, ChatMsg chatMsg2) {
        Log.d("MeetingActivity", "onChatMsgSent: " + chatMsg.f5388h);
        this.f6616T0.m7111B(chatMsg, chatMsg2);
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: y0 */
    public void mo5149y0(int i9, int i10, String str, int i11, String str2) {
        Log.d("MeetingActivity", "[FU] onFileUploadCompleted: " + i9 + " / fileUrl: " + str + " / thumbUrl: " + str2);
        UploadMultipleChatMediaHelperQueue.m16892F().m16922I(i9, i10, str, i11, str2);
    }

    /* renamed from: y9 */
    public final float m6890y9() {
        return this.f6712i0.getY() + this.f6705h0.getY();
    }

    /* renamed from: ya */
    public final void m6891ya() {
        this.f6621U.f7078a.setBackgroundResource(R.color.clm_bg);
        this.f6621U.f7080c.setVisibility(8);
    }

    /* renamed from: yb */
    public final void m6892yb() {
        if (C5170o0.m20170e(this.f6753o) || C5170o0.m20170e(this.f6760p)) {
            C1260a.m5675m(this.f6711i, this.f6718j, this.f6687e3, Globals.m7388i0().m7506X()).m15439e(this.f6737l4);
        } else {
            this.f6730k4.run();
        }
    }

    /* renamed from: ye */
    public final void m6893ye(boolean z8) throws Resources.NotFoundException {
        int iM6606B9 = m6606B9();
        int iM6600A9 = m6600A9();
        float f9 = iM6606B9 / 2.0f;
        float fM6736W9 = m6736W9() / 2.0f;
        this.f6621U.m7140c();
        C1376f c1376f = this.f6621U;
        c1376f.f7089l = C5177q1.m20228b(c1376f.f7078a, iM6606B9, iM6600A9, f9, fM6736W9, 300);
        this.f6621U.f7089l.addListener(this.f6662a5);
        if (!z8) {
            this.f6621U.f7089l.setDuration(0L);
        }
        this.f6621U.f7089l.start();
        if (this.f6749n2 != null) {
            m7366I0().removeCallbacks(this.f6749n2);
        }
        this.f6749n2 = new RunnableC1346s1(f9 - f9, fM6736W9 - (iM6600A9 / 2.0f));
        m7366I0().postDelayed(this.f6749n2, 500L);
    }

    /* renamed from: yf */
    public final void m6894yf() {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "]----setupRenderers IN----");
        this.f6621U.f7080c.init(this.f6617T1.f4921g, this.f6759o5);
        this.f6621U.f7080c.setEnableHardwareScaler(true);
        SurfaceViewRenderer surfaceViewRenderer = this.f6621U.f7080c;
        RendererCommon.ScalingType scalingType = RendererCommon.ScalingType.SCALE_ASPECT_FILL;
        RendererCommon.ScalingType scalingType2 = RendererCommon.ScalingType.SCALE_ASPECT_FIT;
        surfaceViewRenderer.setScalingType(scalingType, scalingType2);
        this.f6627V.f7080c.init(this.f6617T1.f4921g, this.f6780r5);
        this.f6627V.f7080c.setEnableHardwareScaler(true);
        this.f6627V.f7080c.setScalingType(scalingType, scalingType2);
        this.f6633W.f7080c.init(this.f6617T1.f4921g, this.f6787s5);
        this.f6633W.f7080c.setEnableHardwareScaler(true);
        this.f6633W.f7080c.setScalingType(scalingType, scalingType2);
        this.f6653Z1.init(this.f6617T1.f4921g, null);
        this.f6653Z1.setZOrderMediaOverlay(true);
        this.f6653Z1.setEnableHardwareScaler(true);
        this.f6653Z1.setScalingType(scalingType, scalingType2);
        this.f6653Z1.setMirror(true);
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "]----setupRenderers OUT----");
    }

    /* renamed from: yg */
    public final void m6895yg() {
        this.f6667b4 = new CountDownTimerC1342r0(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, 100L).start();
    }

    /* renamed from: yh */
    public final void m6896yh() {
        runOnUiThread(new Runnable() { // from class: p2.g4
            @Override // java.lang.Runnable
            public final void run() {
                this.f20450b.m6487pe();
            }
        });
    }

    @Override // com.cyberlink.clrtc.InterfaceC1113s
    /* renamed from: z */
    public void mo5150z(NileError nileError, int i9) throws IllegalStateException, Resources.NotFoundException, IOException, IllegalArgumentException {
        ULogUtility.m16683s("MeetingActivity", "[" + this.f6748n1 + "] onCallError, error = " + nileError);
        int i10 = C1322k1.f6901b[nileError.ordinal()];
        if (i10 != 1) {
            if (i10 != 2 && i10 != 3 && i10 != 4 && i10 != 5) {
                m6757Zf(null, null);
                return;
            }
            m6834oa("timeout", "NileNetwork - " + nileError);
        }
    }

    /* renamed from: z9 */
    public final float m6897z9() {
        return this.f6651Z.getHeight() - m6884x9();
    }

    /* renamed from: za */
    public final void m6898za() {
        this.f6586O0 = (ViewGroup) this.f6698g0.findViewById(R.id.meetingAudioMeetingLayout);
        this.f6592P0 = (TextView) this.f6698g0.findViewById(R.id.meetingAudioMeetingTimerText);
        this.f6598Q0 = (TextView) this.f6698g0.findViewById(R.id.meetingAudioMeetingTimerTextLand);
    }

    /* renamed from: ze */
    public final void m6899ze(boolean z8) {
        int iM6695P9 = m6695P9();
        int iM6689O9 = m6689O9();
        float fM6646H9 = m6646H9(iM6695P9);
        float fM6653I9 = m6653I9(iM6689O9);
        this.f6621U.m7140c();
        C1376f c1376f = this.f6621U;
        c1376f.f7089l = C5177q1.m20228b(c1376f.f7078a, iM6695P9, iM6689O9, fM6646H9, fM6653I9, 300);
        this.f6621U.f7089l.addListener(this.f6662a5);
        if (!z8) {
            this.f6621U.f7089l.setDuration(0L);
        }
        this.f6621U.f7089l.start();
    }

    /* renamed from: zf */
    public final boolean m6900zf() {
        int currentItem = this.f6603R.getCurrentItem();
        boolean z8 = (this.f6559J3 && currentItem == m6671L9() && getResources().getConfiguration().orientation != 2) || (this.f6559J3 && currentItem != m6671L9());
        if (currentItem != m6784fa() && currentItem != m6671L9()) {
            return true;
        }
        if (this.f6555J || !this.f6549I) {
            return (this.f6534F2 && !this.f6570L2) || z8;
        }
        return true;
    }

    /* renamed from: zg */
    public final void m6901zg() {
        m6779dh();
        this.f6509B1 = new C1358w1(this, null);
        registerReceiver(this.f6509B1, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
    }

    /* renamed from: zh */
    public final void m6902zh() {
        if (this.f6603R.getCurrentItem() == m6671L9()) {
            m6832nh();
            return;
        }
        for (int i9 = 0; i9 < this.f6645Y.size(); i9++) {
            this.f6639X.get(i9).f7092o.setVisibility(8);
        }
    }
}
