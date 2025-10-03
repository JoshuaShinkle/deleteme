package com.cyberlink.you.activity.chatdialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.cyberlink.clsm.C1199a;
import com.cyberlink.link.detect.CustomURLSpan;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.AnnounceMsgDetailActivity;
import com.cyberlink.you.activity.BlockUserAlertActivity;
import com.cyberlink.you.activity.BreakUpFriendActivity;
import com.cyberlink.you.activity.CreateAlbumActivity;
import com.cyberlink.you.activity.CreateTopicActivity;
import com.cyberlink.you.activity.GroupAlbumActivity;
import com.cyberlink.you.activity.GroupEditActivity;
import com.cyberlink.you.activity.GroupInfoActivity;
import com.cyberlink.you.activity.GroupJoinLinkActivity;
import com.cyberlink.you.activity.GroupMemberListActivity;
import com.cyberlink.you.activity.LockScreenActivity;
import com.cyberlink.you.activity.PhotoListActivity;
import com.cyberlink.you.activity.SearchChatActivity;
import com.cyberlink.you.activity.ShowMediaActivity;
import com.cyberlink.you.activity.StickerShopActivity;
import com.cyberlink.you.activity.StickerShopDetailActivity;
import com.cyberlink.you.activity.TopicContentActivity;
import com.cyberlink.you.activity.UserProfileActivity;
import com.cyberlink.you.activity.VideoListActivity;
import com.cyberlink.you.activity.VideoPlaybackActivity;
import com.cyberlink.you.activity.WebViewActivity;
import com.cyberlink.you.activity.chatdialog.C2026a;
import com.cyberlink.you.activity.chatdialog.C2027b;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.chatdialog.ChatOptionDialog;
import com.cyberlink.you.activity.chatdialog.message.ChatDialogMode;
import com.cyberlink.you.activity.chatdialog.message.SelfDestructView;
import com.cyberlink.you.activity.filelist.FileListActivity;
import com.cyberlink.you.activity.friend.FriendInfoUpdateActivity;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.activity.selectfromfriendgroup.SelectFromFriendGroupActivity;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.AsyncTaskC2897h0;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2909n0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.ScheduleSendObj;
import com.cyberlink.you.chat.XMPPArchiveHelper;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2971k0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MediaDao;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.StickerPackObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.groupalbum.C3072a;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.PhotoImportActivity;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.transcode.C3114a;
import com.cyberlink.you.transcode.TranscodeUtility;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.rockerhieu.emojicon.EmojiconTextView;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.jivesoftware.smack.packet.AbstractC5594b;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.C5616j;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p042d0.C4619d;
import p094i2.C5044a;
import p094i2.C5047d;
import p096i4.C5050a;
import p103j1.C5074g;
import p103j1.C5085r;
import p115k3.C5126e;
import p116k4.C5128a0;
import p116k4.C5140e0;
import p116k4.C5145g;
import p116k4.C5152i0;
import p116k4.C5154j;
import p116k4.C5157k;
import p116k4.C5166n;
import p116k4.C5169o;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5176q0;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p173q2.C6127a;
import p173q2.C6136j;
import p174q3.C6152i;
import p182r2.C6196d0;
import p182r2.C6201i;
import p182r2.InterfaceC6190a0;
import p190s1.C6250g;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.AbstractRunnableC6364a;
import p209u2.C6365b;
import p209u2.C6369f;
import p209u2.C6385v;
import p209u2.ThreadFactoryC6373j;
import p218v2.C6456d;
import p218v2.C6468p;
import p218v2.DialogC6459g;
import p236x2.C6566a;
import p245y2.C6598a;
import p245y2.C6661h6;
import p245y2.C6675j5;
import p245y2.C6696m5;
import p245y2.C6766w5;
import p245y2.C6773x5;
import p245y2.HandlerC6652g5;
import p245y2.RunnableC6747u0;
import p254z2.AbstractC6817a;
import p254z2.C6818b;
import p254z2.C6819c;
import p254z2.C6820d;
import p254z2.C6821e;
import p254z2.C6822f;

/* loaded from: classes.dex */
public class ChatDialogActivity extends BaseFragmentActivity {

    /* renamed from: U2 */
    public static ArrayList<String> f9789U2 = new ArrayList<>();

    /* renamed from: V2 */
    public static long f9790V2 = 0;

    /* renamed from: W2 */
    public static String f9791W2 = "setting_notification_sound";

    /* renamed from: X2 */
    public static boolean f9792X2 = true;

    /* renamed from: Y2 */
    public static ThreadPoolExecutor f9793Y2 = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC6373j("Chat.SingleThread"));

    /* renamed from: A */
    public SwipeRefreshLayout f9794A;

    /* renamed from: A2 */
    public C2027b.i f9797A2;

    /* renamed from: B */
    public TextView f9798B;

    /* renamed from: B2 */
    public C2925v.e f9801B2;

    /* renamed from: C */
    public ImageView f9802C;

    /* renamed from: C2 */
    public View.OnClickListener f9805C2;

    /* renamed from: D */
    public Map<String, MessageObj> f9806D;

    /* renamed from: D0 */
    public View f9807D0;

    /* renamed from: D2 */
    public AsyncTask<Void, Void, Void> f9809D2;

    /* renamed from: E */
    public C1954e2 f9810E;

    /* renamed from: E0 */
    public View f9811E0;

    /* renamed from: E2 */
    public FriendsClient.InterfaceC3051i f9813E2;

    /* renamed from: F */
    public TextView f9814F;

    /* renamed from: F0 */
    public List<MessageObj> f9815F0;

    /* renamed from: F1 */
    public ProgressDialog f9816F1;

    /* renamed from: F2 */
    public DialogC6459g.a f9817F2;

    /* renamed from: G */
    public View f9818G;

    /* renamed from: G0 */
    public C3114a f9819G0;

    /* renamed from: G2 */
    public C3199c.b f9821G2;

    /* renamed from: H */
    public View f9822H;

    /* renamed from: H0 */
    public View f9823H0;

    /* renamed from: H2 */
    public XMPPManager.AbstractC2868s f9825H2;

    /* renamed from: I */
    public View f9826I;

    /* renamed from: I2 */
    public AsyncTask<Void, Void, C2973l0> f9829I2;

    /* renamed from: J */
    public View f9830J;

    /* renamed from: J2 */
    public boolean f9833J2;

    /* renamed from: K */
    public ViewGroup f9834K;

    /* renamed from: K2 */
    public AsyncTask<Void, Void, GroupAlbumObj> f9837K2;

    /* renamed from: L */
    public ViewGroup f9838L;

    /* renamed from: L2 */
    public boolean f9841L2;

    /* renamed from: M */
    public ViewGroup f9842M;

    /* renamed from: M2 */
    public AsyncTaskC1942b2 f9845M2;

    /* renamed from: N */
    public ViewGroup f9846N;

    /* renamed from: N0 */
    public Date f9847N0;

    /* renamed from: N2 */
    public AsyncTask<Void, Void, Pair<GroupAlbumObj, Boolean>> f9849N2;

    /* renamed from: O */
    public FrameLayout f9850O;

    /* renamed from: O2 */
    public boolean f9853O2;

    /* renamed from: P */
    public ViewGroup f9854P;

    /* renamed from: P0 */
    public ImageView f9855P0;

    /* renamed from: P2 */
    public boolean f9857P2;

    /* renamed from: Q2 */
    public AsyncTaskC1942b2 f9861Q2;

    /* renamed from: R1 */
    public View f9864R1;

    /* renamed from: R2 */
    public AsyncTask<Void, TopicObj, TopicObj> f9865R2;

    /* renamed from: S1 */
    public C3072a f9868S1;

    /* renamed from: S2 */
    public boolean f9869S2;

    /* renamed from: T1 */
    public C1938a2 f9872T1;

    /* renamed from: T2 */
    public boolean f9873T2;

    /* renamed from: X */
    public AsyncTaskC1946c2 f9883X;

    /* renamed from: X0 */
    public long f9884X0;

    /* renamed from: X1 */
    public TextView f9885X1;

    /* renamed from: Y */
    public AsyncTaskC1950d2 f9886Y;

    /* renamed from: Y1 */
    public TextView f9888Y1;

    /* renamed from: Z1 */
    public TextView f9891Z1;

    /* renamed from: a2 */
    public RelativeLayout f9894a2;

    /* renamed from: b2 */
    public C6696m5 f9897b2;

    /* renamed from: c */
    public ProgressDialog f9898c;

    /* renamed from: c0 */
    public FriendsClient f9899c0;

    /* renamed from: c2 */
    public C6696m5 f9901c2;

    /* renamed from: d0 */
    public C2017x1 f9903d0;

    /* renamed from: d2 */
    public C6696m5 f9905d2;

    /* renamed from: e2 */
    public C6696m5 f9909e2;

    /* renamed from: f */
    public String f9910f;

    /* renamed from: f0 */
    public MessageObj f9911f0;

    /* renamed from: f1 */
    public String f9912f1;

    /* renamed from: f2 */
    public C6696m5 f9913f2;

    /* renamed from: g */
    public Group f9914g;

    /* renamed from: g0 */
    public MessageObj f9915g0;

    /* renamed from: g2 */
    public C6696m5 f9917g2;

    /* renamed from: h */
    public ImageView f9918h;

    /* renamed from: h1 */
    public FriendsClient.InterfaceC3051i f9920h1;

    /* renamed from: h2 */
    public C6696m5 f9921h2;

    /* renamed from: i */
    public ImageView f9922i;

    /* renamed from: i1 */
    public FriendsClient.InterfaceC3051i f9924i1;

    /* renamed from: i2 */
    public C6696m5 f9925i2;

    /* renamed from: j */
    public ImageView f9926j;

    /* renamed from: j2 */
    public C6696m5 f9929j2;

    /* renamed from: k */
    public ImageView f9930k;

    /* renamed from: k2 */
    public C6696m5 f9933k2;

    /* renamed from: l */
    public Button f9934l;

    /* renamed from: l2 */
    public C6696m5 f9937l2;

    /* renamed from: m */
    public TextView f9938m;

    /* renamed from: m2 */
    public C6696m5 f9941m2;

    /* renamed from: n */
    public TextView f9942n;

    /* renamed from: n2 */
    public C6696m5 f9945n2;

    /* renamed from: o */
    public ListView f9946o;

    /* renamed from: o0 */
    public AssetFileDescriptor f9947o0;

    /* renamed from: o2 */
    public C6696m5 f9949o2;

    /* renamed from: p */
    public View f9950p;

    /* renamed from: p0 */
    public long f9951p0;

    /* renamed from: p2 */
    public C6696m5 f9953p2;

    /* renamed from: q */
    public TextView f9954q;

    /* renamed from: q2 */
    public C6696m5 f9957q2;

    /* renamed from: r */
    public C6766w5 f9958r;

    /* renamed from: r0 */
    public C3199c f9959r0;

    /* renamed from: r2 */
    public C6696m5 f9961r2;

    /* renamed from: s */
    public ImageView f9962s;

    /* renamed from: s0 */
    public DialogC6459g f9963s0;

    /* renamed from: s2 */
    public C6696m5 f9965s2;

    /* renamed from: t */
    public ImageView f9966t;

    /* renamed from: t2 */
    public C5126e f9969t2;

    /* renamed from: u */
    public TextView f9970u;

    /* renamed from: u2 */
    public View.OnClickListener f9973u2;

    /* renamed from: v */
    public View f9974v;

    /* renamed from: v2 */
    public AdapterView.OnItemClickListener f9977v2;

    /* renamed from: w */
    public TextView f9978w;

    /* renamed from: w0 */
    public AsyncTaskC1970i2 f9979w0;

    /* renamed from: w1 */
    public ChatOptionDialog f9980w1;

    /* renamed from: w2 */
    public C2027b f9981w2;

    /* renamed from: x */
    public View f9982x;

    /* renamed from: x2 */
    public int f9985x2;

    /* renamed from: y */
    public ImageView f9986y;

    /* renamed from: y2 */
    public int f9989y2;

    /* renamed from: z */
    public RelativeLayout f9990z;

    /* renamed from: z0 */
    public Timer f9991z0;

    /* renamed from: z2 */
    public C2027b.j f9993z2;

    /* renamed from: d */
    public final int f9902d = 5;

    /* renamed from: e */
    public final int f9906e = CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT;

    /* renamed from: Q */
    public View f9858Q = null;

    /* renamed from: R */
    public final Object f9862R = new Object();

    /* renamed from: S */
    public boolean f9866S = false;

    /* renamed from: T */
    public boolean f9870T = false;

    /* renamed from: U */
    public boolean f9874U = false;

    /* renamed from: V */
    public boolean f9877V = false;

    /* renamed from: W */
    public boolean f9880W = false;

    /* renamed from: Z */
    public C2026a f9889Z = null;

    /* renamed from: a0 */
    public long f9892a0 = 0;

    /* renamed from: b0 */
    public boolean f9895b0 = false;

    /* renamed from: e0 */
    public Timer f9907e0 = new Timer();

    /* renamed from: h0 */
    public ArrayList<ScheduleSendObj> f9919h0 = new ArrayList<>();

    /* renamed from: i0 */
    public boolean f9923i0 = false;

    /* renamed from: j0 */
    public final HashMap<Long, Friend> f9927j0 = new HashMap<>();

    /* renamed from: k0 */
    public final List<C2904l> f9931k0 = new ArrayList();

    /* renamed from: l0 */
    public int f9935l0 = -1;

    /* renamed from: m0 */
    public C5140e0 f9939m0 = null;

    /* renamed from: n0 */
    public C5140e0 f9943n0 = null;

    /* renamed from: q0 */
    public Map<String, Friend> f9955q0 = new HashMap();

    /* renamed from: t0 */
    public C4619d f9967t0 = null;

    /* renamed from: u0 */
    public C4619d f9971u0 = null;

    /* renamed from: v0 */
    public MessageObj f9975v0 = null;

    /* renamed from: x0 */
    public ChatDialogMode f9983x0 = ChatDialogMode.NORMAL;

    /* renamed from: y0 */
    public AsyncTask<Void, Void, List<MessageObj>> f9987y0 = null;

    /* renamed from: A0 */
    public Tab f9795A0 = Tab.Chats;

    /* renamed from: B0 */
    public final HandlerC6652g5 f9799B0 = new HandlerC6652g5();

    /* renamed from: C0 */
    public boolean f9803C0 = false;

    /* renamed from: I0 */
    public boolean f9827I0 = false;

    /* renamed from: J0 */
    public MessageObj f9831J0 = null;

    /* renamed from: K0 */
    public MessageObj f9835K0 = null;

    /* renamed from: L0 */
    public C5044a f9839L0 = new C5044a();

    /* renamed from: M0 */
    public Map<String, C5047d> f9843M0 = new HashMap();

    /* renamed from: O0 */
    public ArrayList<MessageObj> f9851O0 = new ArrayList<>();

    /* renamed from: Q0 */
    public boolean f9859Q0 = false;

    /* renamed from: R0 */
    public boolean f9863R0 = false;

    /* renamed from: S0 */
    public C2014w1 f9867S0 = null;

    /* renamed from: T0 */
    public SwipeRefreshLayout.InterfaceC0510j f9871T0 = new C1975k();

    /* renamed from: U0 */
    public View.OnClickListener f9875U0 = new ViewOnClickListenerC1964h0();

    /* renamed from: V0 */
    public View.OnLayoutChangeListener f9878V0 = new View.OnLayoutChangeListener() { // from class: y2.m
        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            this.f22353b.m11251a9(view, i9, i10, i11, i12, i13, i14, i15, i16);
        }
    };

    /* renamed from: W0 */
    public final C6468p.c f9881W0 = new C6468p.c() { // from class: y2.x
        @Override // p218v2.C6468p.c
        /* renamed from: a */
        public final void mo117a(String str, boolean z8) {
            this.f22482a.m11258b9(str, z8);
        }
    };

    /* renamed from: Y0 */
    public UploadMultipleChatMediaHelperQueue.InterfaceC3194g f9887Y0 = new C2001s0();

    /* renamed from: Z0 */
    public View.OnClickListener f9890Z0 = new View.OnClickListener() { // from class: y2.i0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22305b.m11279e9(view);
        }
    };

    /* renamed from: a1 */
    public C2909n0.b f9893a1 = new C1945c1();

    /* renamed from: b1 */
    public XMPPManager.InterfaceC2851b0 f9896b1 = new XMPPManager.InterfaceC2851b0() { // from class: y2.t0
        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2851b0
        /* renamed from: h0 */
        public final void mo13974h0(boolean z8) {
            this.f22431b.m11314j9(z8);
        }
    };

    /* renamed from: c1 */
    public XMPPManager.InterfaceC2849a0 f9900c1 = new C2002s1();

    /* renamed from: d1 */
    public XMPPArchiveHelper.InterfaceC2846c f9904d1 = new C2005t1();

    /* renamed from: e1 */
    public AsyncTaskC2897h0.a f9908e1 = new AsyncTaskC2897h0.a() { // from class: y2.e1
        @Override // com.cyberlink.you.chat.AsyncTaskC2897h0.a
        public final void onComplete() {
            this.f22225a.m11321k9();
        }
    };

    /* renamed from: g1 */
    public BroadcastReceiver f9916g1 = new C1955f();

    /* renamed from: j1 */
    public View.OnClickListener f9928j1 = new ViewOnClickListenerC1971j();

    /* renamed from: k1 */
    public View.OnClickListener f9932k1 = new ViewOnClickListenerC1982m();

    /* renamed from: l1 */
    public final View.OnClickListener f9936l1 = new View.OnClickListener() { // from class: y2.p1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            C5187v0.m20267c(R.string.chatroom_cannot_decrypt_msg);
        }
    };

    /* renamed from: m1 */
    public View.OnClickListener f9940m1 = new ViewOnClickListenerC1985n();

    /* renamed from: n1 */
    public final InterfaceC6190a0 f9944n1 = new C1994q();

    /* renamed from: o1 */
    public View.OnClickListener f9948o1 = new ViewOnClickListenerC2003t();

    /* renamed from: p1 */
    public View.OnClickListener f9952p1 = new View.OnClickListener() { // from class: y2.a2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22181b.m11272d9(view);
        }
    };

    /* renamed from: q1 */
    public AdapterView.OnItemClickListener f9956q1 = new C2006u();

    /* renamed from: r1 */
    public View.OnClickListener f9960r1 = new View.OnClickListener() { // from class: y2.i2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22308b.m11286f9(view);
        }
    };

    /* renamed from: s1 */
    public View.OnClickListener f9964s1 = new ViewOnClickListenerC2009v();

    /* renamed from: t1 */
    public C5321e.m f9968t1 = new C2015x();

    /* renamed from: u1 */
    public View.OnClickListener f9972u1 = new ViewOnClickListenerC2018y();

    /* renamed from: v1 */
    public View.OnClickListener f9976v1 = new ViewOnClickListenerC2021z();

    /* renamed from: x1 */
    public View.OnClickListener f9984x1 = new ViewOnClickListenerC1940b0();

    /* renamed from: y1 */
    public View.OnClickListener f9988y1 = new ViewOnClickListenerC1944c0();

    /* renamed from: z1 */
    public View.OnClickListener f9992z1 = new ViewOnClickListenerC1948d0();

    /* renamed from: A1 */
    public View.OnClickListener f9796A1 = new ViewOnClickListenerC1952e0();

    /* renamed from: B1 */
    public View.OnClickListener f9800B1 = new ViewOnClickListenerC1956f0();

    /* renamed from: C1 */
    public View.OnClickListener f9804C1 = new ViewOnClickListenerC1960g0();

    /* renamed from: D1 */
    public View.OnClickListener f9808D1 = new ViewOnClickListenerC1968i0();

    /* renamed from: E1 */
    public View.OnClickListener f9812E1 = new ViewOnClickListenerC1972j0();

    /* renamed from: G1 */
    public View.OnClickListener f9820G1 = new ViewOnClickListenerC1976k0();

    /* renamed from: H1 */
    public View.OnClickListener f9824H1 = new ViewOnClickListenerC1980l0();

    /* renamed from: I1 */
    public View.OnClickListener f9828I1 = new ViewOnClickListenerC1983m0();

    /* renamed from: J1 */
    public View.OnClickListener f9832J1 = new ViewOnClickListenerC1986n0();

    /* renamed from: K1 */
    public View.OnClickListener f9836K1 = new ViewOnClickListenerC1989o0();

    /* renamed from: L1 */
    public View.OnClickListener f9840L1 = new ViewOnClickListenerC1992p0();

    /* renamed from: M1 */
    public View.OnClickListener f9844M1 = new ViewOnClickListenerC1995q0();

    /* renamed from: N1 */
    public View.OnClickListener f9848N1 = C6365b.m24452c(new ViewOnClickListenerC1998r0());

    /* renamed from: O1 */
    public View.OnClickListener f9852O1 = new ViewOnClickListenerC2004t0();

    /* renamed from: P1 */
    public View.OnClickListener f9856P1 = new ViewOnClickListenerC2007u0();

    /* renamed from: Q1 */
    public C2907m0.g f9860Q1 = new C2907m0.g() { // from class: y2.j2
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f22325b.m11293g9();
        }
    };

    /* renamed from: U1 */
    public AdapterView.OnItemClickListener f9876U1 = new C2010v0();

    /* renamed from: V1 */
    public View.OnTouchListener f9879V1 = new ViewOnTouchListenerC2016x0();

    /* renamed from: W1 */
    public final View.OnClickListener f9882W1 = new View.OnClickListener() { // from class: y2.c
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f22200b.m11300h9(view);
        }
    };

    public enum Tab {
        Chats,
        Albums,
        Bulletins,
        Poll
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$a */
    public class C1935a extends GestureDetector.SimpleOnGestureListener {
        public C1935a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (f10 < BitmapDescriptorFactory.HUE_RED) {
                ChatDialogActivity.this.m11536b7(false);
            }
            return super.onScroll(motionEvent, motionEvent2, f9, f10);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            ChatDialogActivity.this.m11536b7(false);
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$a0 */
    public class AsyncTaskC1936a0 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ Group f10000a;

        public AsyncTaskC1936a0(Group group) {
            this.f10000a = group;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            ChatDialogActivity.this.m11570ja(this.f10000a);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$a1 */
    public class C1937a1 implements AdapterView.OnItemClickListener {
        public C1937a1() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (i9 > 0) {
                try {
                    ChatDialogActivity.this.m11558ga((TopicObj) ChatDialogActivity.this.f9969t2.getItem(i9 - ((ListView) adapterView).getHeaderViewsCount()));
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$a2 */
    public class C1938a2 extends AbstractRunnableC6364a {

        /* renamed from: c */
        public String f10003c;

        /* renamed from: d */
        public String f10004d;

        /* renamed from: e */
        public final WeakReference<FriendsClient> f10005e;

        public C1938a2(String str) {
            this.f10005e = new WeakReference<>(ChatDialogActivity.this.f9899c0);
            this.f10003c = str == null ? "" : str;
            this.f10004d = "group";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m11626g(List list, int i9, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("GetAlbumRunnable", "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d("GetAlbumRunnable", "statusCode=" + str3);
                return;
            }
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                return;
            }
            synchronized (list) {
                List<GroupAlbumObj> listM11629i = m11629i(str4);
                if (listM11629i != null) {
                    list.addAll(listM11629i);
                }
                if (m24448b()) {
                    Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                } else {
                    if (list.size() == i9) {
                        m11630j(list);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m11627h(String str, String str2, String str3, String str4, String str5) {
            if (str4 == null) {
                Log.d("GetAlbumRunnable", "[queryAlbumList] Response is null");
                return;
            }
            if (!str4.equals("200")) {
                Log.d("GetAlbumRunnable", "[queryAlbumList] statusCode=" + str4);
                return;
            }
            Log.d("GetAlbumRunnable", "[queryAlbumList] Success.");
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[queryAlbumList] Force stop");
                return;
            }
            int iM16553k1 = CLUtility.m16553k1(str5);
            int iM16494U0 = CLUtility.m16494U0(str5);
            if (iM16553k1 != -1 && iM16494U0 != -1) {
                if (iM16553k1 != iM16494U0) {
                    m11631k(iM16553k1, str, str5);
                    return;
                } else {
                    m11630j(m11629i(str5));
                    return;
                }
            }
            Log.d("GetAlbumRunnable", "[queryAlbumList] totalSize(" + iM16553k1 + ") or resultsSize(" + iM16494U0 + ") is expected value");
        }

        /* renamed from: f */
        public final FriendsClient m11628f() {
            return this.f10005e.get();
        }

        /* renamed from: i */
        public final List<GroupAlbumObj> m11629i(String str) throws JSONException {
            try {
                try {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("results");
                    ArrayList arrayList = new ArrayList();
                    for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i9);
                            try {
                            } catch (JSONException unused) {
                            }
                            try {
                                arrayList.add(new GroupAlbumObj(-1L, this.f10003c, jSONObject.getString("albumId"), jSONObject.getString("albumName"), jSONObject.getLong("lastModified") * 1000, jSONObject.getInt("numberOfMedia"), jSONObject.getString("creatorId"), jSONObject.getString("albumType")));
                            } catch (JSONException unused2) {
                                Log.e("ChatDialogActivity", "[backup.listAlbum] groupInfo attribute parse error. JSONStr=" + str);
                            }
                        } catch (JSONException unused3) {
                            Log.e("ChatDialogActivity", "[backup.listAlbum] groupAlbum parse error. JSONStr=" + str);
                        }
                    }
                    return arrayList;
                } catch (JSONException unused4) {
                    Log.e("ChatDialogActivity", "[backup.listAlbum] 'results' missing. JSONStr=" + str);
                    return null;
                }
            } catch (JSONException unused5) {
                Log.e("ChatDialogActivity", "[backup.listAlbum] Parse error. JSONstr=" + str);
                return null;
            }
        }

        /* renamed from: j */
        public final void m11630j(List<GroupAlbumObj> list) {
            if (list != null) {
                for (GroupAlbumObj groupAlbumObj : list) {
                    if (m24448b()) {
                        Log.d("GetAlbumRunnable", "[processAlbumList] Force stop");
                        return;
                    }
                    GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(groupAlbumObj.m14675b());
                    if (groupAlbumObjM15056i == null || !groupAlbumObjM15056i.m14681h().equals(groupAlbumObj.m14681h())) {
                        C2950b0.m14911j().m15053f(groupAlbumObj);
                    } else if (groupAlbumObjM15056i.m14682i() != groupAlbumObj.m14682i()) {
                        C2950b0.m14911j().m15061n(groupAlbumObj.m14675b(), groupAlbumObj, "NumberOfMedia");
                    }
                }
            }
            ChatDialogActivity.this.m11613wb();
        }

        /* renamed from: k */
        public final void m11631k(final int i9, String str, String str2) {
            if (str == null || str.isEmpty()) {
                Log.d("GetAlbumRunnable", "There is no token.");
                return;
            }
            final ArrayList arrayList = new ArrayList(m11629i(str2));
            if (m24448b()) {
                Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                return;
            }
            int iM16559m = CLUtility.m16559m(i9, 100);
            for (int i10 = 2; i10 <= iM16559m; i10++) {
                if (m24448b()) {
                    Log.d("GetAlbumRunnable", "[processAlbumPages] Force stop");
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new C6301o("token", str));
                arrayList2.add(new C6301o("groupId", this.f10003c));
                arrayList2.add(new C6301o("pageIndex", String.valueOf(i10)));
                arrayList2.add(new C6301o("pageSize", String.valueOf(100)));
                FriendsClient friendsClientM11628f = m11628f();
                if (friendsClientM11628f == null) {
                    return;
                }
                friendsClientM11628f.m15734m(this.f10004d, "listAlbum", arrayList2, new FriendsClient.InterfaceC3051i() { // from class: y2.u3
                    @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                    /* renamed from: a */
                    public final void mo134a(String str3, String str4, String str5, String str6) {
                        this.f22442a.m11626g(arrayList, i9, str3, str4, str5, str6);
                    }
                });
            }
        }

        /* renamed from: l */
        public final void m11632l() {
            Log.d("GetAlbumRunnable", "[queryAlbumList] in");
            final String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("groupId", this.f10003c));
            arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList.add(new C6301o("pageSize", String.valueOf(100)));
            FriendsClient friendsClientM11628f = m11628f();
            if (friendsClientM11628f == null) {
                return;
            }
            friendsClientM11628f.m15734m(this.f10004d, "listAlbum", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.t3
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f22434a.m11627h(strM7449L, str, str2, str3, str4);
                }
            });
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("GetAlbumRunnable", "[run] in");
            if (m24448b() || this.f10003c.isEmpty()) {
                Log.d("GetAlbumRunnable", "[run] Don't continue.");
            } else {
                m11632l();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$b */
    public class C1939b implements C2026a.e {
        public C1939b() {
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.e
        /* renamed from: a */
        public void mo11633a(String str) {
            ChatDialogActivity.this.m11444Ba(MessageObj.MessageType.Text, str, 0, null);
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.e
        /* renamed from: b */
        public void mo11634b(boolean z8) {
            String str = z8 ? MimeTypes.BASE_TYPE_AUDIO : MimeTypes.BASE_TYPE_VIDEO;
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            MeetingActivity.m6370f9(chatDialogActivity, chatDialogActivity.f9914g, true, str, "Auto-Caller");
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2026a.e
        /* renamed from: c */
        public void mo11635c(StickerObj stickerObj) {
            ChatDialogActivity.this.m11465Ia(stickerObj, 0, null);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$b0 */
    public class ViewOnClickListenerC1940b0 implements View.OnClickListener {
        public ViewOnClickListenerC1940b0() {
        }

        /* renamed from: f */
        public static /* synthetic */ void m11641f(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m11642g(String str, DialogInterface dialogInterface, int i9) {
            ChatDialogActivity.this.m11491Q9(str);
        }

        /* renamed from: h */
        public static /* synthetic */ void m11643h(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m11644i(String str, DialogInterface dialogInterface, int i9) {
            ChatDialogActivity.this.m11491Q9(str);
        }

        /* renamed from: j */
        public static /* synthetic */ void m11645j(DialogInterface dialogInterface, int i9) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Leave");
            ChatDialogActivity.this.f9980w1.dismiss();
            if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual")) {
                return;
            }
            AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
            builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.chat_group_more_dialog_leave_chat));
            final String string = Long.toString(ChatDialogActivity.this.f9914g.f13727n);
            if (ChatDialogActivity.this.f9914g.f13704C && ChatDialogActivity.this.f9914g.f13729p == 1 && ChatDialogActivity.this.f9914g.f13728o > 1) {
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.you_cannot_leave_this_group_because_you_are_the_only_admin_of_the_group));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: y2.x2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.ViewOnClickListenerC1940b0.m11641f(dialogInterface, i9);
                    }
                });
            } else if (ChatDialogActivity.this.f9914g.f13704C) {
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.you_are_one_of_the_admins_if_you_leave_this_group_youll_no_longer_be_an_administrator));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: y2.y2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f22509b.m11642g(string, dialogInterface, i9);
                    }
                });
                builderM16382a.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.z2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.ViewOnClickListenerC1940b0.m11643h(dialogInterface, i9);
                    }
                });
            } else {
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.if_you_leave_this_group_this_group_will_be_closed_all_the_chats_text_and_photos_will_be_removed));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: y2.a3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f22182b.m11644i(string, dialogInterface, i9);
                    }
                });
                builderM16382a.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.b3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.ViewOnClickListenerC1940b0.m11645j(dialogInterface, i9);
                    }
                });
            }
            builderM16382a.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$b1 */
    public class C1941b1 implements C2027b.j {
        public C1941b1() {
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2027b.j
        /* renamed from: a */
        public void mo11646a(String str) {
            if (Arrays.asList("backKey", "meetingInvite").contains(str)) {
                ChatDialogActivity.this.m11559gb();
            } else {
                ChatDialogActivity.this.f9818G.setVisibility(8);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$b2 */
    public class AsyncTaskC1942b2 extends AsyncTask<Void, Void, StickerPackObj> {

        /* renamed from: a */
        public long f10010a;

        /* renamed from: b */
        public InterfaceC1962g2 f10011b;

        public AsyncTaskC1942b2(long j9, InterfaceC1962g2 interfaceC1962g2) {
            this.f10010a = j9;
            this.f10011b = interfaceC1962g2;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StickerPackObj doInBackground(Void... voidArr) {
            Thread.currentThread().setName("RecvSticker AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("packId", String.valueOf(this.f10010a)));
            Pair<String, String> pairM15731j = ChatDialogActivity.this.f9899c0.m15731j("sticker", "pack.info", arrayList);
            String str = (String) pairM15731j.first;
            if ("200".equals(str)) {
                List<StickerPackObj> listM20177D = C5172p.m20177D(C5172p.m20196r((String) pairM15731j.second), false, false);
                if (listM20177D == null || listM20177D.size() == 0) {
                    return null;
                }
                return listM20177D.get(0);
            }
            Log.d("ChatDialogActivity", "statusCode = " + str);
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(StickerPackObj stickerPackObj) {
            this.f10011b.mo11877a(stickerPackObj);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$c */
    public class AsyncTaskC1943c extends AsyncTask<Void, Void, List<TopicObj>> {
        public AsyncTaskC1943c() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<TopicObj> doInBackground(Void... voidArr) {
            List<TopicObj> listM14985o = C2950b0.m14906e().m14985o(ChatDialogActivity.this.f9914g.f13727n);
            if (!listM14985o.isEmpty()) {
                ChatDialogActivity.this.f9895b0 = true;
            }
            Collections.sort(listM14985o, new TopicObj.C2945b());
            return listM14985o;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<TopicObj> list) {
            Log.d("ChatDialogActivity", "[resetTopicList] onPostExecute, result = " + list.size());
            if (ChatDialogActivity.this.f9969t2 == null) {
                Log.d("ChatDialogActivity", "[resetTopicList] new TopicAdapter");
                ChatDialogActivity.this.f9969t2 = new C5126e(ChatDialogActivity.this, 0, new ArrayList());
                ChatDialogActivity.this.f9969t2.m19992e(ChatDialogActivity.this.f9899c0);
                if (ChatDialogActivity.this.f9795A0 == Tab.Bulletins) {
                    ChatDialogActivity.this.f9946o.setAdapter((ListAdapter) ChatDialogActivity.this.f9969t2);
                }
            }
            ChatDialogActivity.this.f9969t2.clear();
            ChatDialogActivity.this.f9969t2.addAll(list);
            ChatDialogActivity.this.m11622zb();
            ChatDialogActivity.this.f9969t2.notifyDataSetChanged();
            if (ChatDialogActivity.this.f9795A0 == Tab.Bulletins) {
                ChatDialogActivity.this.m11531Za();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$c0 */
    public class ViewOnClickListenerC1944c0 implements View.OnClickListener {
        public ViewOnClickListenerC1944c0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Edit");
            ChatDialogActivity.this.f9980w1.dismiss();
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7().getApplicationContext(), (Class<?>) GroupEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", ChatDialogActivity.this.f9914g);
            intent.putExtras(bundle);
            ChatDialogActivity.this.startActivityForResult(intent, 5001);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$c1 */
    public class C1945c1 implements C2909n0.b {
        public C1945c1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m11652c() {
            if (ChatDialogActivity.this.f9810E != null) {
                ChatDialogActivity.this.f9810E.notifyDataSetChanged();
            }
        }

        @Override // com.cyberlink.you.chat.C2909n0.b
        /* renamed from: a */
        public boolean mo11653a() {
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.d3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22216b.m11652c();
                }
            });
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$c2 */
    public class AsyncTaskC1946c2 extends AsyncTask<Void, Void, Integer> {
        public AsyncTaskC1946c2() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) {
            Log.d("ChatDialogActivity", "[GetTopicsFromDbTask] in");
            return Integer.valueOf(C2950b0.m14906e().m14986p(ChatDialogActivity.this.f9914g.f13727n));
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            if (num.intValue() > 0) {
                Log.d("ChatDialogActivity", "GetTopicsFromDbTask resetTopicList");
                ChatDialogActivity.this.m11602ta();
            }
            if (ChatDialogActivity.this.f9886Y != null && !ChatDialogActivity.this.f9886Y.isCancelled()) {
                ChatDialogActivity.this.f9886Y.cancel(true);
            }
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            chatDialogActivity.f9886Y = chatDialogActivity.new AsyncTaskC1950d2(num.intValue());
            ChatDialogActivity.this.f9886Y.executeOnExecutor(C6385v.f21554b, new Void[0]);
            Log.d("ChatDialogActivity", "[GetTopicsFromDbTask] out, topicsCount = " + num);
        }

        public /* synthetic */ AsyncTaskC1946c2(ChatDialogActivity chatDialogActivity, C1975k c1975k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$d */
    public class AsyncTaskC1947d extends AsyncTask<Void, Void, C1979l> {
        public AsyncTaskC1947d() {
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0165  */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public C1979l doInBackground(Void... voidArr) {
            MessageObj messageObjM11818m0;
            C1979l c1979l;
            synchronized (ChatDialogActivity.this.f9862R) {
                Thread.currentThread().setName("resetMessageList AsyncTask");
                long jCurrentTimeMillis = System.currentTimeMillis();
                Group groupM15081r = C2950b0.m14912k().m15081r(ChatDialogActivity.this.f9914g.f13723j);
                if (groupM15081r != null && groupM15081r.m15748d() > ChatDialogActivity.this.f9914g.m15748d()) {
                    ChatDialogActivity.this.f9914g.m15753k(groupM15081r.m15748d());
                }
                int iM15186y = C2950b0.m14916o().m15186y(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), String.valueOf(ChatDialogActivity.this.f9914g.m15748d()));
                List<MessageObj> listM15180s = C2950b0.m14916o().m15180s(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), iM15186y);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (MessageObj messageObj : listM15180s) {
                    String strM14740B = messageObj.m14740B();
                    if ("2".equals(strM14740B) || "3".equals(strM14740B)) {
                        arrayList2.add(messageObj);
                    } else {
                        arrayList.add(messageObj);
                    }
                }
                Comparator<MessageObj> comparator = MessageObj.f12923K;
                Collections.sort(arrayList, comparator);
                Collections.sort(arrayList2, comparator);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                Log.d("ChatDialogActivity", "get message list " + jCurrentTimeMillis2 + " ms");
                C6566a.m25153l("MessageList_QueryTime", C5176q0.m20222a(jCurrentTimeMillis2));
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                ChatDialogActivity chatDialogActivity2 = ChatDialogActivity.this;
                chatDialogActivity.f9810E = chatDialogActivity2.new C1954e2(chatDialogActivity2.m11582n7(), R.layout.view_item_send_text_msg, new ArrayList());
                if (arrayList.isEmpty() || arrayList.size() - iM15186y < 0 || iM15186y <= 5) {
                    messageObjM11818m0 = null;
                    ChatDialogActivity.this.f9810E.m11769S(arrayList);
                    ChatDialogActivity.this.f9810E.m11769S(ChatDialogActivity.this.f9815F0);
                    ChatDialogActivity.this.f9810E.m11787Y(arrayList2);
                    c1979l = ChatDialogActivity.this.new C1979l();
                    c1979l.f10159a = arrayList;
                    c1979l.f10160b = arrayList2;
                    c1979l.f10161c = messageObjM11818m0;
                } else {
                    MessageObj messageObj2 = (MessageObj) arrayList.get(arrayList.size() - iM15186y);
                    if (messageObj2.m14788z().getTime() > ChatDialogActivity.this.f9914g.m15748d()) {
                        messageObjM11818m0 = ChatDialogActivity.this.f9810E.m11818m0(ChatDialogActivity.this.f9914g.m15748d() > 0 ? ChatDialogActivity.this.f9914g.m15748d() : messageObj2.m14788z().getTime() - 1);
                        arrayList.add(arrayList.size() - iM15186y, messageObjM11818m0);
                    }
                    ChatDialogActivity.this.f9810E.m11769S(arrayList);
                    ChatDialogActivity.this.f9810E.m11769S(ChatDialogActivity.this.f9815F0);
                    ChatDialogActivity.this.f9810E.m11787Y(arrayList2);
                    c1979l = ChatDialogActivity.this.new C1979l();
                    c1979l.f10159a = arrayList;
                    c1979l.f10160b = arrayList2;
                    c1979l.f10161c = messageObjM11818m0;
                }
            }
            return c1979l;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C1979l c1979l) {
            boolean z8;
            ChatDialogActivity.this.f9946o.setAdapter((ListAdapter) ChatDialogActivity.this.f9810E);
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            chatDialogActivity.f9935l0 = chatDialogActivity.f9946o.getCount() - 1;
            if (c1979l.f10161c != null) {
                ChatDialogActivity chatDialogActivity2 = ChatDialogActivity.this;
                chatDialogActivity2.f9935l0 = chatDialogActivity2.f9810E.m11823r0(ChatDialogActivity.this.f9810E.m11756N0(c1979l.f10161c) - 1);
                z8 = true;
            } else {
                z8 = false;
            }
            if (ChatDialogActivity.this.f9795A0 == Tab.Chats && ChatDialogActivity.this.f9810E.getCount() == 0 && !ChatDialogActivity.this.f9914g.f13711J) {
                ChatDialogActivity.this.f9854P.setVisibility(0);
            }
            ChatDialogActivity.this.f9946o.setOnItemClickListener(ChatDialogActivity.this.f9956q1);
            if (Group.m15743f(ChatDialogActivity.this.f9914g.f13716c)) {
                Log.d("ChatDialogActivity", "[resetMessageList] call setTabSelection");
                ChatDialogActivity chatDialogActivity3 = ChatDialogActivity.this;
                chatDialogActivity3.m11515Va(chatDialogActivity3.f9795A0, false, true, false);
            } else {
                if (ChatDialogActivity.this.f9981w2 != null) {
                    ChatDialogActivity.this.f9981w2.m12067S();
                }
                ChatDialogActivity.this.f9794A.setEnabled(!ChatDialogActivity.this.f9914g.f13711J);
            }
            ChatDialogActivity.this.f9867S0.f10224p = !z8;
            ChatDialogActivity.this.m11461H7();
            ChatDialogActivity.this.m11442Ab();
            ChatDialogActivity.this.m11559gb();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$d0 */
    public class ViewOnClickListenerC1948d0 implements View.OnClickListener {
        public ViewOnClickListenerC1948d0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Breakup");
            if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual")) {
                Friend friendM15004D = C2950b0.m14899A().m15004D(ChatDialogActivity.this.f9914g.f13723j);
                if (friendM15004D == null) {
                    Iterator it = ChatDialogActivity.this.f9927j0.values().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Friend friend = (Friend) it.next();
                        if (friend.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                            friendM15004D = friend;
                            break;
                        }
                    }
                }
                if (friendM15004D != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("friend", friendM15004D);
                    Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) BreakUpFriendActivity.class);
                    intent.putExtras(bundle);
                    ChatDialogActivity.this.startActivityForResult(intent, 4);
                }
            }
            if (ChatDialogActivity.this.f9980w1 == null || !ChatDialogActivity.this.f9980w1.isShowing()) {
                return;
            }
            ChatDialogActivity.this.f9980w1.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$d1 */
    public class C1949d1 implements C2027b.i {
        public C1949d1() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // com.cyberlink.you.activity.chatdialog.C2027b.i
        /* renamed from: a */
        public void mo7011a(String str, Map<String, Object> map) throws IllegalStateException {
            if (!"sendText".equals(str)) {
                ChatDialogActivity.this.m11559gb();
            }
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
                case -1329927011:
                    if (str.equals("introduceFriend")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case 12514186:
                    if (str.equals("sendPhoto")) {
                        c9 = 2;
                        break;
                    }
                    break;
                case 18074067:
                    if (str.equals("sendVideo")) {
                        c9 = 3;
                        break;
                    }
                    break;
                case 18257546:
                    if (str.equals("sendVoice")) {
                        c9 = 4;
                        break;
                    }
                    break;
                case 1162130111:
                    if (str.equals("scheduleSend")) {
                        c9 = 5;
                        break;
                    }
                    break;
                case 1247032612:
                    if (str.equals("sendFile")) {
                        c9 = 6;
                        break;
                    }
                    break;
                case 1247446229:
                    if (str.equals("sendText")) {
                        c9 = 7;
                        break;
                    }
                    break;
                case 1308987233:
                    if (str.equals("recordVoice")) {
                        c9 = '\b';
                        break;
                    }
                    break;
                case 1821181108:
                    if (str.equals("shareLocation")) {
                        c9 = '\t';
                        break;
                    }
                    break;
                case 2141243541:
                    if (str.equals("sendSticker")) {
                        c9 = '\n';
                        break;
                    }
                    break;
            }
            Date date = null;
            switch (c9) {
                case 0:
                case 2:
                case 4:
                case 5:
                case 7:
                case '\n':
                    if ("scheduleSend".equals(str)) {
                        CLUtility.m16589t1(ChatDialogActivity.this);
                    }
                    if (!map.containsKey(MimeTypes.BASE_TYPE_TEXT)) {
                        if (!map.containsKey("importImages")) {
                            if (!map.containsKey("sticer")) {
                                if (map.containsKey("voicePath") && map.containsKey("duration")) {
                                    String str2 = (String) map.get("voicePath");
                                    String str3 = (String) map.get("duration");
                                    if (map.containsKey(Constants.FirelogAnalytics.PARAM_TTL)) {
                                        iIntValue = ((Integer) map.get(Constants.FirelogAnalytics.PARAM_TTL)).intValue();
                                    } else if (map.containsKey("scheduleTime")) {
                                        date = (Date) map.get("scheduleTime");
                                    }
                                    ChatDialogActivity.this.m11487Pa(str2, str3, iIntValue, date);
                                    break;
                                }
                            } else {
                                Object obj = map.get("sticer");
                                if (!(obj instanceof StickerObj)) {
                                    if (obj instanceof Emojicon) {
                                        ChatDialogActivity.this.f9981w2.m12063G((Emojicon) obj);
                                        break;
                                    }
                                } else {
                                    if (map.containsKey(Constants.FirelogAnalytics.PARAM_TTL)) {
                                        iIntValue = ((Integer) map.get(Constants.FirelogAnalytics.PARAM_TTL)).intValue();
                                    } else if (map.containsKey("scheduleTime")) {
                                        date = (Date) map.get("scheduleTime");
                                    }
                                    ChatDialogActivity.this.m11465Ia((StickerObj) obj, iIntValue, date);
                                    break;
                                }
                            }
                        } else {
                            ArrayList arrayList = (ArrayList) map.get("importImages");
                            if (map.containsKey(Constants.FirelogAnalytics.PARAM_TTL)) {
                                iIntValue = ((Integer) map.get(Constants.FirelogAnalytics.PARAM_TTL)).intValue();
                            } else if (map.containsKey("scheduleTime")) {
                                date = (Date) map.get("scheduleTime");
                            }
                            ChatDialogActivity.this.m11456Fa(arrayList, iIntValue, date);
                            break;
                        }
                    } else {
                        String str4 = (String) map.get(MimeTypes.BASE_TYPE_TEXT);
                        if (!map.containsKey(Constants.FirelogAnalytics.PARAM_TTL)) {
                            if (!map.containsKey("scheduleTime")) {
                                if (ChatDialogActivity.this.f9974v.getVisibility() != 8) {
                                    ChatDialogActivity.this.m11459Ga(str4);
                                    break;
                                } else {
                                    ChatDialogActivity.this.m11468Ja(str4);
                                    break;
                                }
                            } else {
                                ChatDialogActivity.this.m11444Ba(MessageObj.MessageType.Text, str4, 0, (Date) map.get("scheduleTime"));
                                break;
                            }
                        } else {
                            ChatDialogActivity.this.m11444Ba(MessageObj.MessageType.Text, str4, ((Integer) map.get(Constants.FirelogAnalytics.PARAM_TTL)).intValue(), null);
                            break;
                        }
                    }
                    break;
                case 1:
                    ChatDialogActivity.this.m11471Ka((List) map.get("selectFriends"), 0, null);
                    break;
                case 3:
                    ChatDialogActivity.this.m11480Na((VideoItem) map.get("videoItem"), 0, null);
                    break;
                case 6:
                    ChatDialogActivity.this.m11615xa((Uri) map.get("filePath"));
                    break;
                case '\b':
                    ChatDialogActivity.this.m11581mb();
                    break;
                case '\t':
                    ChatDialogActivity.this.m11621za((Location) map.get(FirebaseAnalytics.Param.LOCATION), (List) map.get("addresses"), (Uri) map.get("snapshotPath"), 0, null);
                    break;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$d2 */
    public class AsyncTaskC1950d2 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final boolean f10020a;

        /* renamed from: b */
        public DialogC3133q f10021b = null;

        public AsyncTaskC1950d2(int i9) {
            this.f10020a = i9 == 0;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Void r22 = null;
            if (isCancelled()) {
                return null;
            }
            long j9 = ChatDialogActivity.this.f9914g.f13727n;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("groupId", String.valueOf(j9)));
            arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList.add(new C6301o("pageSize", String.valueOf(20)));
            Pair<String, String> pairM15731j = ChatDialogActivity.this.f9899c0.m15731j("groupbulletin", "listTopics", arrayList);
            if ("200".equals(pairM15731j.first)) {
                m11659b(C5172p.m20174A((String) pairM15731j.second, j9));
                if (isCancelled()) {
                    return null;
                }
                int iM16553k1 = CLUtility.m16553k1((String) pairM15731j.second);
                int iM16494U0 = CLUtility.m16494U0((String) pairM15731j.second);
                int iM16559m = CLUtility.m16559m(iM16553k1, 20);
                if (iM16553k1 != -1 && iM16494U0 != -1 && iM16553k1 != iM16494U0) {
                    int i9 = 2;
                    while (i9 <= iM16559m) {
                        if (isCancelled()) {
                            return r22;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                        arrayList2.add(new C6301o("groupId", String.valueOf(j9)));
                        arrayList2.add(new C6301o("pageIndex", String.valueOf(i9)));
                        arrayList2.add(new C6301o("pageSize", String.valueOf(20)));
                        Pair<String, String> pairM15731j2 = ChatDialogActivity.this.f9899c0.m15731j("groupbulletin", "listTopics", arrayList2);
                        if ("200".equals(pairM15731j2.first)) {
                            m11659b(C5172p.m20174A((String) pairM15731j2.second, j9));
                        }
                        i9++;
                        r22 = null;
                    }
                }
            }
            return r22;
        }

        /* renamed from: b */
        public final void m11659b(List<TopicObj> list) {
            Iterator<TopicObj> it = list.iterator();
            while (it.hasNext()) {
                C2950b0.m14906e().m14978h(it.next(), TopicObj.m14828m());
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
            DialogC3133q dialogC3133q = this.f10021b;
            if (dialogC3133q != null) {
                dialogC3133q.dismiss();
            }
            ChatDialogActivity.this.m11602ta();
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            if (this.f10020a) {
                this.f10021b = new DialogC3133q.b(ChatDialogActivity.this).m16411b();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e */
    public class AsyncTaskC1951e extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1951e() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Thread.currentThread().setName("onResume");
            Group groupM15081r = C2950b0.m14912k().m15081r(ChatDialogActivity.this.f9910f);
            if (groupM15081r == null || ChatDialogActivity.this.f9914g == null) {
                return null;
            }
            ChatDialogActivity.this.f9914g.m15758p(groupM15081r);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e0 */
    public class ViewOnClickListenerC1952e0 implements View.OnClickListener {
        public ViewOnClickListenerC1952e0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Profile");
            if (ChatDialogActivity.this.f9980w1 != null && ChatDialogActivity.this.f9980w1.isShowing()) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            ChatDialogActivity.this.m11546da();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e1 */
    public class C1953e1 extends AbstractC6381r<Void, Void> {
        public C1953e1(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Void r22) {
            ULogUtility.m16680p("ChatDialogActivity", "sendDeliverReceiptMessages completed");
            ChatDialogActivity.this.m11469Jb(false);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r22) {
            ULogUtility.m16680p("ChatDialogActivity", "sendDeliverReceiptMessages fail");
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2 */
    public class C1954e2 extends ArrayAdapter<MessageObj> {

        /* renamed from: A */
        public View.OnClickListener f10026A;

        /* renamed from: B */
        public View.OnClickListener f10027B;

        /* renamed from: C */
        public View.OnClickListener f10028C;

        /* renamed from: D */
        public View.OnClickListener f10029D;

        /* renamed from: E */
        public View.OnClickListener f10030E;

        /* renamed from: b */
        public final int f10032b;

        /* renamed from: c */
        public int f10033c;

        /* renamed from: d */
        public final int f10034d;

        /* renamed from: e */
        public final int f10035e;

        /* renamed from: f */
        public final int f10036f;

        /* renamed from: g */
        public final int f10037g;

        /* renamed from: h */
        public final int f10038h;

        /* renamed from: i */
        public final int f10039i;

        /* renamed from: j */
        public List<MessageObj> f10040j;

        /* renamed from: k */
        public List<MessageObj> f10041k;

        /* renamed from: l */
        public C6820d f10042l;

        /* renamed from: m */
        public View.OnClickListener f10043m;

        /* renamed from: n */
        public View.OnClickListener f10044n;

        /* renamed from: o */
        public View.OnTouchListener f10045o;

        /* renamed from: p */
        public View.OnClickListener f10046p;

        /* renamed from: q */
        public View.OnClickListener f10047q;

        /* renamed from: r */
        public View.OnClickListener f10048r;

        /* renamed from: s */
        public View.OnClickListener f10049s;

        /* renamed from: t */
        public View.OnClickListener f10050t;

        /* renamed from: u */
        public View.OnClickListener f10051u;

        /* renamed from: v */
        public View.OnClickListener f10052v;

        /* renamed from: w */
        public Runnable f10053w;

        /* renamed from: x */
        public String f10054x;

        /* renamed from: y */
        public View.OnClickListener f10055y;

        /* renamed from: z */
        public View.OnClickListener f10056z;

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$a */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str = (String) view.getTag();
                Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) AnnounceMsgDetailActivity.class);
                intent.putExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, str);
                intent.putExtra("displayName", ChatDialogActivity.this.f9914g.f13717d);
                intent.putExtra("avatar", ChatDialogActivity.this.f9914g.f13724k);
                intent.putExtra("groupSubType", ChatDialogActivity.this.f9914g.f13738y);
                ChatDialogActivity.this.startActivity(intent);
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$b */
        public class b implements View.OnClickListener {
            public b() {
            }

            /* renamed from: a */
            public final boolean m11832a(String str) {
                String queryParameter = Uri.parse(str).getQueryParameter("callback");
                return (queryParameter == null || queryParameter.isEmpty()) ? false : true;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent;
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    if (textView.getSelectionStart() != -1 || textView.getSelectionEnd() != -1) {
                        return;
                    }
                }
                String str = (String) view.getTag();
                if (str == null || str.isEmpty()) {
                    return;
                }
                try {
                    if (str.startsWith("u://stickershop")) {
                        intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) StickerShopActivity.class);
                    } else if (str.startsWith("u://u-appstore")) {
                        CLUtility.m16477P1(ChatDialogActivity.this);
                        intent = null;
                    } else if (m11832a(str)) {
                        WebViewActivity.m11036z(ChatDialogActivity.this, str, true);
                        return;
                    } else {
                        Intent intent2 = new Intent("android.intent.action.VIEW");
                        intent2.setData(Uri.parse(str));
                        intent = intent2;
                    }
                    if (intent != null) {
                        ChatDialogActivity.this.startActivity(intent);
                    }
                } catch (Exception e9) {
                    Log.d("ChatDialogActivity", "[onAnnounceLinkBtnViewClick] Exception e : " + e9.getMessage());
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$c */
        public class c implements View.OnClickListener {

            /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$c$a */
            public class a extends AsyncTask<Void, Void, String> {

                /* renamed from: a */
                public final /* synthetic */ long f10060a;

                /* renamed from: b */
                public final /* synthetic */ String f10061b;

                /* renamed from: c */
                public final /* synthetic */ MessageObj f10062c;

                public a(long j9, String str, MessageObj messageObj) {
                    this.f10060a = j9;
                    this.f10061b = str;
                    this.f10062c = messageObj;
                }

                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String doInBackground(Void... voidArr) {
                    C2973l0 c2973l0M14725v;
                    if (this.f10060a == -1 || (c2973l0M14725v = C2950b0.m14914m().m14725v(this.f10060a)) == null) {
                        return null;
                    }
                    return c2973l0M14725v.m15148t().f13200d;
                }

                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(String str) {
                    if (str != null) {
                        Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) VideoPlaybackActivity.class);
                        intent.putExtra("video_playback_url", str);
                        String str2 = this.f10061b;
                        if (str2 != null && !str2.isEmpty()) {
                            intent.putExtra("video_playback_path", this.f10061b);
                        }
                        intent.putExtra("mediaId", this.f10060a);
                        intent.putExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, this.f10062c.m14777o());
                        intent.putExtra("videoMessage", true);
                        intent.putExtra("Group", ChatDialogActivity.this.f9914g);
                        ChatDialogActivity.this.startActivity(intent);
                    }
                }
            }

            public c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int iIntValue;
                MessageObj item;
                Integer num = (Integer) view.getTag(R.id.tag_Position);
                if (num == null || (iIntValue = num.intValue()) < 0 || (item = C1954e2.this.getItem(iIntValue)) == null) {
                    return;
                }
                new a(NumberUtils.toLong(item.m14747I("mediaId"), -1L), item.m14747I("videoPath"), item).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$d */
        public class d implements View.OnClickListener {
            public d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int iIntValue;
                MessageObj item;
                Object tag = view.getTag(R.id.tag_Position);
                if (!(tag instanceof Integer)) {
                    tag = view.getTag();
                }
                boolean z8 = tag instanceof Integer;
                Integer num = (Integer) tag;
                if (num == null || (iIntValue = num.intValue()) < 0 || (item = C1954e2.this.getItem(iIntValue)) == null) {
                    return;
                }
                MessageObj.MessageType messageTypeM14778p = item.m14778p();
                MessageObj.MessageType messageType = MessageObj.MessageType.Photo;
                if (!messageTypeM14778p.equals(messageType) && !item.m14778p().equals(MessageObj.MessageType.PhotoNote)) {
                    if (item.m14778p().equals(MessageObj.MessageType.AnnouncementType01)) {
                        ChatDialogActivity.this.m11522X9();
                        return;
                    }
                    return;
                }
                long j9 = NumberUtils.toLong(item.m14747I("mediaId"), -1L);
                if (j9 == -1) {
                    return;
                }
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                if (c2973l0M14725v == null || TextUtils.isEmpty(c2973l0M14725v.m15147s())) {
                    C5187v0.m20267c(R.string.error_media_not_found);
                    return;
                }
                if (item.m14778p().equals(messageType)) {
                    ChatDialogActivity.this.m11526Y9(c2973l0M14725v.m15131c(), c2973l0M14725v.m15144p(), item.m14784v(), true);
                    return;
                }
                if (item.m14778p().equals(MessageObj.MessageType.PhotoNote)) {
                    if (ChatDialogActivity.this.f9914g.f13738y.equals("Official") || ChatDialogActivity.this.f9914g.f13738y.equals("Corporate")) {
                        ChatDialogActivity.this.m11508U6(c2973l0M14725v.m15131c());
                    } else {
                        ChatDialogActivity.this.m11520X6(c2973l0M14725v, item.m14784v(), true, false, item.m14780r());
                    }
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$e */
        public class e implements View.OnClickListener {

            /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$e$a */
            public class a extends AsyncTask<Void, Void, Pair<GroupAlbumObj, Boolean>> {

                /* renamed from: a */
                public final /* synthetic */ String f10066a;

                /* renamed from: b */
                public final /* synthetic */ ProgressDialog f10067b;

                /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$e$a$a, reason: collision with other inner class name */
                public class C6848a implements C3062b.b {

                    /* renamed from: a */
                    public int f10069a = 0;

                    /* renamed from: b */
                    public final /* synthetic */ GroupAlbumObj f10070b;

                    public C6848a(GroupAlbumObj groupAlbumObj) {
                        this.f10070b = groupAlbumObj;
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    /* renamed from: d */
                    public /* synthetic */ void m11838d(ProgressDialog progressDialog) {
                        if (ChatDialogActivity.this.isFinishing() || progressDialog == null || !progressDialog.isShowing()) {
                            return;
                        }
                        progressDialog.dismiss();
                    }

                    @Override // com.cyberlink.you.friends.C3062b.b
                    /* renamed from: a */
                    public void mo9343a() {
                        this.f10069a++;
                    }

                    @Override // com.cyberlink.you.friends.C3062b.b
                    /* renamed from: b */
                    public void mo9344b(int i9, int i10) {
                        int i11 = this.f10069a + 1;
                        this.f10069a = i11;
                        if (i9 == i11) {
                            a aVar = a.this;
                            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                            final ProgressDialog progressDialog = aVar.f10067b;
                            chatDialogActivity.runOnUiThread(new Runnable() { // from class: y2.a5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f22184b.m11838d(progressDialog);
                                }
                            });
                            ChatDialogActivity.this.m11518W9(this.f10070b);
                        }
                    }
                }

                public a(String str, ProgressDialog progressDialog) {
                    this.f10066a = str;
                    this.f10067b = progressDialog;
                }

                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public Pair<GroupAlbumObj, Boolean> doInBackground(Void... voidArr) {
                    Thread.currentThread().setName("onThumbnailImageViewClick AsyncTask");
                    GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(this.f10066a);
                    boolean z8 = false;
                    if (groupAlbumObjM15056i == null && (groupAlbumObjM15056i = ChatDialogActivity.this.f9899c0.m15708O(this.f10066a, String.valueOf(ChatDialogActivity.this.f9914g.f13727n))) != null) {
                        C2950b0.m14911j().m15053f(groupAlbumObjM15056i);
                        C3062b.m15824x(this.f10066a, new C6848a(groupAlbumObjM15056i));
                        z8 = true;
                    }
                    return Pair.create(groupAlbumObjM15056i, Boolean.valueOf(z8));
                }

                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(Pair<GroupAlbumObj, Boolean> pair) {
                    ChatDialogActivity.this.f9857P2 = false;
                    if (pair == null || pair.first == null) {
                        ProgressDialog progressDialog = this.f10067b;
                        if (progressDialog != null && progressDialog.isShowing()) {
                            this.f10067b.dismiss();
                        }
                        Log.d("ChatDialogActivity", "mOnChatTextClickListener fail.");
                        return;
                    }
                    if (((Boolean) pair.second).booleanValue()) {
                        return;
                    }
                    ProgressDialog progressDialog2 = this.f10067b;
                    if (progressDialog2 != null && progressDialog2.isShowing()) {
                        this.f10067b.dismiss();
                    }
                    ChatDialogActivity.this.m11518W9((GroupAlbumObj) pair.first);
                }
            }

            public e() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                MessageObj item;
                Object tag = view.getTag(R.id.tag_Position);
                if (!(tag instanceof Integer)) {
                    tag = view.getTag();
                }
                boolean z8 = tag instanceof Integer;
                Integer num = (Integer) tag;
                if (num == null || (item = C1954e2.this.getItem(num.intValue())) == null || !item.m14778p().equals(MessageObj.MessageType.CreateMedia)) {
                    return;
                }
                try {
                    String string = new JSONObject(item.m14779q()).getString("albumId");
                    ProgressDialog progressDialogShow = ProgressDialog.show(ChatDialogActivity.this.m11582n7(), "", ChatDialogActivity.this.getString(R.string.processing), true);
                    if (ChatDialogActivity.this.f9857P2) {
                        return;
                    }
                    ChatDialogActivity.this.f9857P2 = true;
                    ChatDialogActivity.this.f9849N2 = new a(string, progressDialogShow);
                    ChatDialogActivity.this.f9849N2.executeOnExecutor(C6385v.f21554b, new Void[0]);
                } catch (JSONException e9) {
                    Log.e("ChatDialogActivity", Log.getStackTraceString(e9));
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$f */
        public class f implements View.OnClickListener {

            /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$f$a */
            public class a extends AsyncTask<Void, Void, C2973l0> {

                /* renamed from: a */
                public final /* synthetic */ String f10073a;

                /* renamed from: b */
                public final /* synthetic */ long f10074b;

                public a(String str, long j9) {
                    this.f10073a = str;
                    this.f10074b = j9;
                }

                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public C2973l0 doInBackground(Void... voidArr) {
                    return (C2973l0) FriendsClient.m15657X(this.f10073a, this.f10074b).first;
                }

                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(C2973l0 c2973l0) {
                    if (c2973l0 != null) {
                        f.this.m11840b(c2973l0);
                    } else {
                        C5187v0.m20267c(R.string.error_media_not_found);
                    }
                }
            }

            public f() {
            }

            /* renamed from: b */
            public final void m11840b(C2973l0 c2973l0) {
                Date date = new Date(c2973l0.m15140l() * 1000);
                C6468p.m24787m().m24799w(ChatDialogActivity.this.m11582n7(), c2973l0.m15148t().f13200d, c2973l0.m15145q(), C1199a.m5277a(c2973l0.m15148t().f13204h, c2973l0.m15148t().f13205i), false, date);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MessageObj item;
                String strM14747I;
                Integer num = (Integer) view.getTag();
                if (num == null || (item = C1954e2.this.getItem(num.intValue())) == null || (strM14747I = item.m14747I("albumId")) == null) {
                    return;
                }
                long j9 = NumberUtils.toLong(item.m14747I("mediaId"), -1L);
                if (j9 == -1) {
                    return;
                }
                ULogUtility.m16683s("ChatDialogActivity", "open media: " + j9);
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                if (c2973l0M14725v != null) {
                    m11840b(c2973l0M14725v);
                } else {
                    new a(strM14747I, j9).executeOnExecutor(C6385v.f21554b, new Void[0]);
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$g */
        public class g implements View.OnClickListener {
            public g() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: b */
            public /* synthetic */ void m11844b(MessageObj messageObj) {
                C1954e2.this.f10054x = "";
                try {
                    JSONObject jSONObject = new JSONObject(messageObj.m14779q());
                    MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(jSONObject.getString("replyMessageId"));
                    if (messageObjM15179r != null) {
                        Iterator it = ChatDialogActivity.this.f9851O0.iterator();
                        boolean z8 = false;
                        while (it.hasNext()) {
                            MessageObj messageObj2 = (MessageObj) it.next();
                            if (!z8 && messageObj2.m14777o().equals(messageObj.m14777o())) {
                                z8 = true;
                            }
                        }
                        if (!z8) {
                            ChatDialogActivity.this.f9851O0.add(messageObj);
                        }
                        ChatDialogActivity.this.f9831J0 = messageObjM15179r;
                        ChatDialogActivity.this.m11606ua(messageObjM15179r, jSONObject.getString("replyMessage"));
                        ChatDialogActivity.this.m11535ab(0, false);
                    }
                } catch (Throwable unused) {
                    ULogUtility.m16670f("ChatDialogActivity", "mOnReplyTextItemClickListener onClick");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws NumberFormatException {
                final MessageObj item;
                Integer num = (Integer) view.getTag();
                if (num == null || (item = C1954e2.this.getItem(num.intValue())) == null) {
                    return;
                }
                String strM14747I = item.m14747I("replyMessageId");
                if (C5170o0.m20170e(strM14747I)) {
                    return;
                }
                if ("".equals(C1954e2.this.f10054x)) {
                    C1954e2.this.f10053w = new Runnable() { // from class: y2.b5
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f22197b.m11844b(item);
                        }
                    };
                    C1954e2 c1954e2 = C1954e2.this;
                    c1954e2.f10054x = strM14747I;
                    ChatDialogActivity.this.m7366I0().postDelayed(C1954e2.this.f10053w, 250L);
                    return;
                }
                if (strM14747I.equals(C1954e2.this.f10054x)) {
                    C1954e2 c1954e22 = C1954e2.this;
                    c1954e22.f10054x = "";
                    ChatDialogActivity.this.f9915g0 = item;
                    ChatDialogActivity.this.m11555fb();
                    ChatDialogActivity.this.m7366I0().removeCallbacks(C1954e2.this.f10053w);
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$h */
        public class h implements View.OnClickListener {
            public h() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: b */
            public /* synthetic */ void m11846b(ProgressDialog progressDialog, StickerPackObj stickerPackObj) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                ChatDialogActivity.this.f9873T2 = false;
                if (stickerPackObj == null) {
                    return;
                }
                C2950b0.m14925x().m15286d(stickerPackObj);
                ChatDialogActivity.this.m11534aa(stickerPackObj);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StickerObj stickerObjM11617y7;
                Integer num = (Integer) view.getTag();
                if (num == null) {
                    return;
                }
                MessageObj item = C1954e2.this.getItem(num.intValue());
                if (item == null || item.m14779q() == null || (stickerObjM11617y7 = ChatDialogActivity.this.m11617y7(item)) == null) {
                    return;
                }
                long jM16284i = stickerObjM11617y7.m16284i();
                StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(jM16284i);
                if (stickerPackObjM15293k == null) {
                    final ProgressDialog progressDialogShow = ProgressDialog.show(ChatDialogActivity.this.m11582n7(), "", ChatDialogActivity.this.getString(R.string.loading), true);
                    if (ChatDialogActivity.this.f9873T2) {
                        return;
                    }
                    ChatDialogActivity.this.f9873T2 = true;
                    ChatDialogActivity.this.f9861Q2 = ChatDialogActivity.this.new AsyncTaskC1942b2(jM16284i, new InterfaceC1962g2() { // from class: y2.c5
                        @Override // com.cyberlink.you.activity.chatdialog.ChatDialogActivity.InterfaceC1962g2
                        /* renamed from: a */
                        public final void mo11877a(StickerPackObj stickerPackObj) {
                            this.f22206a.m11846b(progressDialogShow, stickerPackObj);
                        }
                    });
                    ChatDialogActivity.this.f9861Q2.executeOnExecutor(C6385v.f21554b, new Void[0]);
                    return;
                }
                if (!stickerPackObjM15293k.m14815s()) {
                    ChatDialogActivity.this.m11534aa(stickerPackObjM15293k);
                    return;
                }
                if (C2925v.m14625v(ChatDialogActivity.this.f9914g) && ChatDialogActivity.this.f9981w2.m12083y() != null) {
                    if (!ChatDialogActivity.this.f9981w2.m12083y().isVisible()) {
                        CLUtility.m16589t1(ChatDialogActivity.this);
                        C2027b c2027b = ChatDialogActivity.this.f9981w2;
                        Boolean bool = Boolean.FALSE;
                        c2027b.m12075a0(bool, Boolean.TRUE, bool);
                    }
                    ChatDialogActivity.this.f9981w2.m12083y().m19516r0(stickerObjM11617y7.m16284i());
                    ChatDialogActivity.this.f9981w2.m12083y().m19505g0(stickerObjM11617y7.m16284i());
                    if (stickerPackObjM15293k.m14811o().equals(StickerPackObj.Status.NOT_DOWNLOADED) || stickerPackObjM15293k.m14811o().equals(StickerPackObj.Status.NONE)) {
                        ChatDialogActivity.this.f9981w2.m12083y().m19495P();
                    }
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$i */
        public class i implements View.OnClickListener {
            public i() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                C2973l0 c2973l0M14725v;
                Integer num = (Integer) view.getTag();
                if (num == null) {
                    return;
                }
                MessageObj item = C1954e2.this.getItem(num.intValue());
                if (item == null) {
                    return;
                }
                if (!item.m14778p().equals(MessageObj.MessageType.Photo) && !item.m14778p().equals(MessageObj.MessageType.PhotoNote)) {
                    if (item.m14778p().equals(MessageObj.MessageType.AnnouncementType01)) {
                        ChatDialogActivity.this.m11522X9();
                        return;
                    }
                    return;
                }
                long j9 = NumberUtils.toLong(item.m14747I("mediaId"), -1L);
                if (j9 == -1 || (c2973l0M14725v = C2950b0.m14914m().m14725v(j9)) == null) {
                    return;
                }
                if (ChatDialogActivity.this.f9914g.f13738y.equals("Official") || ChatDialogActivity.this.f9914g.f13738y.equals("Corporate")) {
                    ChatDialogActivity.this.m11508U6(c2973l0M14725v.m15131c());
                } else {
                    ChatDialogActivity.this.m11520X6(c2973l0M14725v, item.m14784v(), true, true, item.m14780r());
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$j */
        public class j implements View.OnClickListener {
            public j() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Integer num = (Integer) view.getTag();
                if (num == null) {
                    return;
                }
                MessageObj item = C1954e2.this.getItem(num.intValue());
                if (item == null || item.m14779q() == null) {
                    return;
                }
                String strM14747I = item.m14747I("callId");
                String strM14747I2 = item.m14747I("callType");
                if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual")) {
                    C2925v.m14571O0(ChatDialogActivity.this.m11582n7(), ChatDialogActivity.this.f9914g, true, strM14747I2, "dual chatroom msg");
                } else if (C1954e2.this.m11808h0(item)) {
                    C2925v.m14571O0(ChatDialogActivity.this.m11582n7(), ChatDialogActivity.this.f9914g, true, strM14747I2, "group chatRoom msg");
                } else {
                    MeetingActivity.m6531tb(ChatDialogActivity.this.m11582n7(), strM14747I, null, null, strM14747I2, ChatDialogActivity.this.f9914g, null, null, "group chatroom msg");
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$k */
        public class k extends AsyncTask<Object, Object, Object> {

            /* renamed from: a */
            public final /* synthetic */ StickerPackObj f10080a;

            /* renamed from: b */
            public final /* synthetic */ MessageObj f10081b;

            /* renamed from: c */
            public final /* synthetic */ String f10082c;

            /* renamed from: d */
            public final /* synthetic */ C6819c f10083d;

            /* renamed from: e */
            public final /* synthetic */ int f10084e;

            public k(StickerPackObj stickerPackObj, MessageObj messageObj, String str, C6819c c6819c, int i9) {
                this.f10080a = stickerPackObj;
                this.f10081b = messageObj;
                this.f10082c = str;
                this.f10083d = c6819c;
                this.f10084e = i9;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: b */
            public /* synthetic */ void m11848b() {
                ChatDialogActivity.this.f9810E.notifyDataSetChanged();
            }

            @Override // android.os.AsyncTask
            public Object doInBackground(Object... objArr) {
                Thread.currentThread().setName("ChatDialogActivity.setStickerUnknownView AsyncTask");
                C2950b0.m14925x().m15286d(this.f10080a);
                this.f10081b.m14757S(this.f10082c);
                this.f10081b.m14756R(CLUtility.m16607y(this.f10080a.m14805i()));
                ArrayList arrayList = new ArrayList();
                arrayList.add("MessageContent");
                arrayList.add("MessageType");
                C2950b0.m14916o().m15160E(this.f10081b.m14777o(), this.f10081b, arrayList);
                return null;
            }

            @Override // android.os.AsyncTask
            public void onPostExecute(Object obj) {
                if (!(((Integer) this.f10083d.f22616d.getTag()).intValue() == this.f10084e) || ChatDialogActivity.this.f9946o == null || ChatDialogActivity.this.f9810E == null) {
                    return;
                }
                ChatDialogActivity.this.f9946o.post(new Runnable() { // from class: y2.z4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22526b.m11848b();
                    }
                });
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$l */
        public class l implements View.OnClickListener {

            /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$l$a */
            public class a extends AsyncTask<Void, TopicObj, TopicObj> {

                /* renamed from: a */
                public Group f10087a;

                /* renamed from: b */
                public final /* synthetic */ String f10088b;

                /* renamed from: c */
                public final /* synthetic */ long f10089c;

                /* renamed from: d */
                public final /* synthetic */ String f10090d;

                /* renamed from: e */
                public final /* synthetic */ int f10091e;

                public a(String str, long j9, String str2, int i9) {
                    this.f10088b = str;
                    this.f10089c = j9;
                    this.f10090d = str2;
                    this.f10091e = i9;
                }

                @Override // android.os.AsyncTask
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public TopicObj doInBackground(Void... voidArr) {
                    if (this.f10088b == null) {
                        this.f10087a = ChatDialogActivity.this.f9914g;
                    } else {
                        this.f10087a = C2950b0.m14912k().m15077n(this.f10088b);
                    }
                    TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(this.f10089c);
                    return topicObjM14984n == null ? ChatDialogActivity.this.f9899c0.m15726d0(String.valueOf(this.f10087a.f13727n), this.f10090d) : topicObjM14984n;
                }

                @Override // android.os.AsyncTask
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onPostExecute(TopicObj topicObj) {
                    if (ChatDialogActivity.this.f9898c != null && ChatDialogActivity.this.f9898c.isShowing()) {
                        ChatDialogActivity.this.f9898c.dismiss();
                        ChatDialogActivity.this.f9898c = null;
                    }
                    ChatDialogActivity.this.f9869S2 = false;
                    if (topicObj != null) {
                        Intent intent = new Intent(ChatDialogActivity.this, (Class<?>) TopicContentActivity.class);
                        intent.putExtra("Group", this.f10087a);
                        intent.putExtra("intent_topic", topicObj);
                        ChatDialogActivity.this.startActivity(intent);
                        return;
                    }
                    if (this.f10091e == 45) {
                        ChatDialogActivity.this.m11567ib();
                    } else {
                        ChatDialogActivity.this.m11547db();
                    }
                }
            }

            public l() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws NumberFormatException {
                int iIntValue;
                MessageObj item;
                if (ChatDialogActivity.this.f9983x0 != ChatDialogMode.NORMAL || ChatDialogActivity.this.f9914g.f13712K) {
                    C3123g.m16382a(ChatDialogActivity.this).setTitle("").setMessage(ChatDialogActivity.this.getString(R.string.admin_disable_vote)).setNegativeButton(ChatDialogActivity.this.getString(R.string.ok), (DialogInterface.OnClickListener) null).create().show();
                    return;
                }
                Integer num = (Integer) view.getTag();
                if (num == null || (item = C1954e2.this.getItem((iIntValue = num.intValue()))) == null || item.m14779q() == null) {
                    return;
                }
                String strM14747I = item.m14747I("topicId");
                String strM14747I2 = item.m14747I("groupId");
                try {
                    long j9 = Long.parseLong(strM14747I);
                    int itemViewType = C1954e2.this.getItemViewType(iIntValue);
                    if (ChatDialogActivity.this.f9898c == null || !ChatDialogActivity.this.f9898c.isShowing()) {
                        ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                        chatDialogActivity.f9898c = ProgressDialog.show(chatDialogActivity.m11582n7(), "", ChatDialogActivity.this.getString(R.string.loading), true);
                    }
                    if (ChatDialogActivity.this.f9869S2) {
                        return;
                    }
                    ChatDialogActivity.this.f9869S2 = true;
                    ChatDialogActivity.this.f9865R2 = new a(strM14747I2, j9, strM14747I, itemViewType);
                    ChatDialogActivity.this.f9865R2.executeOnExecutor(C6385v.f21554b, new Void[0]);
                } catch (NumberFormatException unused) {
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$m */
        public class m extends AsyncTask<Void, Void, Friend> {

            /* renamed from: a */
            public final /* synthetic */ String f10093a;

            /* renamed from: b */
            public final /* synthetic */ C6819c f10094b;

            /* renamed from: c */
            public final /* synthetic */ int f10095c;

            /* renamed from: d */
            public final /* synthetic */ MessageObj f10096d;

            public m(String str, C6819c c6819c, int i9, MessageObj messageObj) {
                this.f10093a = str;
                this.f10094b = c6819c;
                this.f10095c = i9;
                this.f10096d = messageObj;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Friend doInBackground(Void... voidArr) throws NumberFormatException {
                Thread.currentThread().setName("updateDeleteMediaContent getUserInfo AsyncTask");
                Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(this.f10093a);
                if (friendM11440A7 == null) {
                    friendM11440A7 = (Friend) ChatDialogActivity.this.f9927j0.get(Long.valueOf(NumberUtils.toLong(this.f10093a, 0L)));
                }
                return friendM11440A7 == null ? ChatDialogActivity.this.f9899c0.m15727f0(this.f10093a) : friendM11440A7;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Friend friend) throws JSONException, NumberFormatException {
                if (friend == null) {
                    return;
                }
                C1954e2.this.m11807g2(friend);
                C1954e2.this.m11801d2(this.f10094b, this.f10096d, friend.m15621b());
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                if (!chatDialogActivity.m11561h8(chatDialogActivity.f9946o) || ChatDialogActivity.this.f9866S) {
                    return;
                }
                ChatDialogActivity.this.f9946o.setSelection(ChatDialogActivity.this.f9946o.getCount() - 1);
            }

            @Override // android.os.AsyncTask
            public void onPreExecute() {
                this.f10094b.f22634v.setTag(R.id.tag_Position, Integer.valueOf(this.f10095c));
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$n */
        public class n extends AsyncTask<Void, Void, Friend> {

            /* renamed from: a */
            public final /* synthetic */ String f10098a;

            /* renamed from: b */
            public final /* synthetic */ C6819c f10099b;

            /* renamed from: c */
            public final /* synthetic */ int f10100c;

            /* renamed from: d */
            public final /* synthetic */ MessageObj f10101d;

            public n(String str, C6819c c6819c, int i9, MessageObj messageObj) {
                this.f10098a = str;
                this.f10099b = c6819c;
                this.f10100c = i9;
                this.f10101d = messageObj;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Friend doInBackground(Void... voidArr) throws NumberFormatException {
                Thread.currentThread().setName("updateDeleteVideoContent getUserInfo AsyncTask");
                Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(this.f10098a);
                if (friendM11440A7 == null) {
                    friendM11440A7 = (Friend) ChatDialogActivity.this.f9927j0.get(Long.valueOf(NumberUtils.toLong(this.f10098a, 0L)));
                }
                return friendM11440A7 == null ? ChatDialogActivity.this.f9899c0.m15727f0(this.f10098a) : friendM11440A7;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Friend friend) throws NumberFormatException {
                if (friend == null) {
                    return;
                }
                C1954e2.this.m11807g2(friend);
                C1954e2.this.m11805f2(this.f10099b, this.f10101d, friend.m15621b());
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                if (!chatDialogActivity.m11561h8(chatDialogActivity.f9946o) || ChatDialogActivity.this.f9866S) {
                    return;
                }
                ChatDialogActivity.this.f9946o.setSelection(ChatDialogActivity.this.f9946o.getCount() - 1);
            }

            @Override // android.os.AsyncTask
            public void onPreExecute() {
                this.f10099b.f22634v.setTag(R.id.tag_Position, Integer.valueOf(this.f10100c));
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$o */
        public class o extends AsyncTask<Void, Void, Friend> {

            /* renamed from: a */
            public final /* synthetic */ String f10103a;

            /* renamed from: b */
            public final /* synthetic */ C6819c f10104b;

            /* renamed from: c */
            public final /* synthetic */ int f10105c;

            /* renamed from: d */
            public final /* synthetic */ MessageObj f10106d;

            public o(String str, C6819c c6819c, int i9, MessageObj messageObj) {
                this.f10103a = str;
                this.f10104b = c6819c;
                this.f10105c = i9;
                this.f10106d = messageObj;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Friend doInBackground(Void... voidArr) throws NumberFormatException {
                Thread.currentThread().setName("updateDeleteFileContent getUserInfo AsyncTask");
                Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(this.f10103a);
                if (friendM11440A7 == null) {
                    friendM11440A7 = (Friend) ChatDialogActivity.this.f9927j0.get(Long.valueOf(NumberUtils.toLong(this.f10103a, 0L)));
                }
                return friendM11440A7 == null ? ChatDialogActivity.this.f9899c0.m15727f0(this.f10103a) : friendM11440A7;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Friend friend) throws NumberFormatException {
                if (friend == null) {
                    return;
                }
                C1954e2.this.m11807g2(friend);
                C1954e2.this.m11797b2(this.f10104b, this.f10106d, friend.m15621b());
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                if (!chatDialogActivity.m11561h8(chatDialogActivity.f9946o) || ChatDialogActivity.this.f9866S) {
                    return;
                }
                ChatDialogActivity.this.f9946o.setSelection(ChatDialogActivity.this.f9946o.getCount() - 1);
            }

            @Override // android.os.AsyncTask
            public void onPreExecute() {
                this.f10104b.f22634v.setTag(R.id.tag_Position, Integer.valueOf(this.f10105c));
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$p */
        public class p implements View.OnClickListener {
            public p() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String messageId = ((SelfDestructView) view).getMessageId();
                Integer num = (Integer) view.getTag();
                if (num == null || messageId == null) {
                    return;
                }
                if (!C6456d.m24714D().m24748G()) {
                    C5187v0.m20267c(R.string.error_no_network);
                    return;
                }
                if (num.intValue() < ChatDialogActivity.this.f9810E.m11826u0()) {
                    MessageObj item = ChatDialogActivity.this.f9810E.getItem(num.intValue());
                    if (messageId.equals(item.m14777o())) {
                        if (item.m14778p() != MessageObj.MessageType.Audio) {
                            C1954e2.this.m11786X1(item);
                            return;
                        }
                        try {
                            long j9 = NumberUtils.toLong(new JSONObject(item.m14779q()).getString("mediaId"), -1L);
                            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                            if (c2973l0M14725v != null) {
                                String str = c2973l0M14725v.m15148t().f13201e;
                                if (str == null || !new File(str).exists()) {
                                    item.f12925B = true;
                                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                                    ChatDialogActivity.this.m11572k7(item, c2973l0M14725v.m15148t().f13200d, String.valueOf(j9));
                                    c2973l0M14725v.m15148t().f13201e = str;
                                    C2950b0.m14914m().m14698H(c2973l0M14725v.m15144p(), c2973l0M14725v, "Original");
                                } else {
                                    Log.d("ChatDialogActivity", "Playing local voice file!");
                                    ChatDialogActivity.this.m11578lb(item, str);
                                }
                            }
                        } catch (Exception unused) {
                            Log.d("ChatDialogActivity", "Parse audio content duration exception");
                        }
                    }
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$q */
        public class q extends AbstractC6381r<Void, Void> {

            /* renamed from: c */
            public final /* synthetic */ MessageObj f10109c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public q(Handler handler, MessageObj messageObj) {
                super(handler);
                this.f10109c = messageObj;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Void r32) {
                this.f10109c.m14763Y(MessageObj.TTLStatus.START);
                this.f10109c.m14760V(FriendsClient.m15646K());
                this.f10109c.m14762X("11");
                C1954e2.this.notifyDataSetChanged();
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$r */
        public class r extends AbstractC6381r<Boolean, Void> {

            /* renamed from: c */
            public final /* synthetic */ DialogC3133q f10111c;

            /* renamed from: d */
            public final /* synthetic */ List f10112d;

            public r(DialogC3133q dialogC3133q, List list) {
                this.f10111c = dialogC3133q;
                this.f10112d = list;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Boolean bool) {
                this.f10111c.dismiss();
                if (bool.booleanValue()) {
                    if (ChatDialogActivity.this.getSharedPreferences("cached_schedule_send_map", 0).getBoolean(ChatDialogActivity.this.f9910f + "_is_schedule_send_list_update", false)) {
                        ChatDialogActivity.this.m11484Ob();
                    }
                    Iterator it = this.f10112d.iterator();
                    while (it.hasNext()) {
                        ChatDialogActivity.this.m11473L7(((MessageObj) it.next()).m14777o());
                    }
                }
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$s */
        public class s extends AsyncTask<Void, Void, List<MessageObj.MessageType>> {

            /* renamed from: a */
            public final /* synthetic */ List f10114a;

            /* renamed from: b */
            public final /* synthetic */ long[] f10115b;

            public s(List list, long[] jArr) {
                this.f10114a = list;
                this.f10115b = jArr;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: d */
            public /* synthetic */ void m11861d(long[] jArr, DialogInterface dialogInterface, int i9) {
                VideoListActivity.m10747s1(ChatDialogActivity.this.m11582n7(), jArr);
            }

            /* renamed from: e */
            public static /* synthetic */ void m11862e(DialogInterface dialogInterface, int i9) {
            }

            @Override // android.os.AsyncTask
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public List<MessageObj.MessageType> doInBackground(Void... voidArr) {
                Thread.currentThread().setName("handleOnSaveToMyDeviceMessageButtonClick");
                return C1954e2.this.m11821p0(this.f10114a);
            }

            @Override // android.os.AsyncTask
            /* renamed from: f, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(List<MessageObj.MessageType> list) {
                if (list.size() <= 0) {
                    VideoListActivity.m10747s1(ChatDialogActivity.this.m11582n7(), this.f10115b);
                    return;
                }
                boolean z8 = false;
                boolean z9 = false;
                boolean z10 = false;
                for (MessageObj.MessageType messageType : list) {
                    if (messageType == MessageObj.MessageType.Photo) {
                        z8 = true;
                    } else if (messageType == MessageObj.MessageType.Video) {
                        z9 = true;
                    } else if (messageType == MessageObj.MessageType.File) {
                        z10 = true;
                    }
                }
                String string = ChatDialogActivity.this.getString(R.string.chat_option_dialog_files);
                if (z8 && !z9 && !z10) {
                    string = ChatDialogActivity.this.getString(R.string.chat_group_more_dialog_photos);
                } else if (!z8 && z9 && !z10) {
                    string = ChatDialogActivity.this.getString(R.string.videos);
                }
                AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
                builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.save_to_my_device));
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.error_notice_save_deleted_item, string));
                String string2 = ChatDialogActivity.this.getString(R.string.ok);
                final long[] jArr = this.f10115b;
                builderM16382a.setPositiveButton(string2, new DialogInterface.OnClickListener() { // from class: y2.d5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f22219b.m11861d(jArr, dialogInterface, i9);
                    }
                });
                builderM16382a.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.e5
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.C1954e2.s.m11862e(dialogInterface, i9);
                    }
                });
                builderM16382a.show();
            }
        }

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$e2$t */
        public class t implements View.OnTouchListener {
            public t() {
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int iIntValue;
                MessageObj item;
                Integer num = (Integer) view.getTag();
                if (num == null || ChatDialogActivity.this.f9971u0 == null || (iIntValue = num.intValue()) < 0 || C1954e2.this.m11761P0(iIntValue) || (item = C1954e2.this.getItem(iIntValue)) == null) {
                    return false;
                }
                ChatDialogActivity.this.f9915g0 = item;
                return ChatDialogActivity.this.f9971u0.m18406a(motionEvent);
            }
        }

        public C1954e2(Context context, int i9, List<MessageObj> list) {
            super(context, i9);
            this.f10032b = Math.round(TypedValue.applyDimension(1, 270.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
            this.f10033c = Math.round(TypedValue.applyDimension(1, 180.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
            this.f10034d = Math.round(TypedValue.applyDimension(1, 110.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
            this.f10035e = (int) (Globals.m7388i0().getResources().getDisplayMetrics().widthPixels * 0.53d * m11827v0() * 1.02d);
            this.f10036f = (int) (Globals.m7388i0().getResources().getDisplayMetrics().heightPixels * 0.4d * m11827v0() * 1.02d);
            this.f10037g = Math.round(TypedValue.applyDimension(1, 16.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
            this.f10038h = Math.round(TypedValue.applyDimension(1, 30.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
            this.f10039i = Math.round(TypedValue.applyDimension(1, 32.0f, Globals.m7388i0().getResources().getDisplayMetrics()));
            this.f10043m = new p();
            this.f10044n = new View.OnClickListener() { // from class: y2.r4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f22416b.m11712o1(view);
                }
            };
            this.f10045o = new t();
            this.f10046p = new View.OnClickListener() { // from class: y2.s4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f22427b.m11714p1(view);
                }
            };
            this.f10047q = new a();
            this.f10048r = new b();
            this.f10049s = new c();
            this.f10050t = C6365b.m24452c(new d());
            this.f10051u = new e();
            this.f10052v = new f();
            this.f10053w = null;
            this.f10054x = "";
            this.f10055y = new g();
            this.f10056z = new h();
            this.f10026A = new View.OnClickListener() { // from class: y2.t4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws JSONException, NumberFormatException {
                    this.f22436b.m11716q1(view);
                }
            };
            this.f10027B = new i();
            this.f10028C = new View.OnClickListener() { // from class: y2.u4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f22445b.m11718r1(view);
                }
            };
            this.f10029D = new j();
            this.f10030E = new l();
            this.f10040j = list;
            this.f10041k = new ArrayList();
            this.f10042l = new C6820d(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: A1 */
        public /* synthetic */ void m11665A1(TextView textView, Object obj, ImageView imageView, Friend friend) {
            Object tag = textView.getTag();
            if (tag == null || tag != obj) {
                return;
            }
            m11777U1(textView, imageView, friend);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: B1 */
        public /* synthetic */ void m11667B1(TextView textView, Object obj, int i9, Friend friend) {
            Object tag = textView.getTag();
            if (tag == null || tag != obj) {
                return;
            }
            textView.setText(ChatDialogActivity.this.getString(i9, friend.m15621b()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: C1 */
        public /* synthetic */ void m11669C1(final TextView textView, final Object obj, final int i9, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("ChatDialogActivity", "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d("ChatDialogActivity", "statusCode=" + str3);
                return;
            }
            final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str4));
            C2950b0.m14899A().m15018j(friendM20184f, false);
            if (ChatDialogActivity.this.f9927j0.containsKey(Long.valueOf(friendM20184f.f13645c))) {
                ChatDialogActivity.this.f9927j0.put(Long.valueOf(friendM20184f.f13645c), friendM20184f);
            }
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.m4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22362b.m11667B1(textView, obj, i9, friendM20184f);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: D1 */
        public /* synthetic */ void m11671D1(String str, C6819c c6819c, List list, MessageObj messageObj, int i9, int i10, List list2) {
            if (ChatDialogActivity.this.f9839L0.m19699c() || !str.equals(c6819c.f22620h.getTag(R.id.tag_messageID)) || list.size() == 0) {
                return;
            }
            if (messageObj.m14773k() != null) {
                ChatDialogActivity.this.f9946o.setSelectionFromTop(m11823r0(i9), ChatDialogActivity.this.f9946o.getHeight() / 6);
            }
            if (m11745H1(i9) && ChatDialogMode.NORMAL == ChatDialogActivity.this.f9983x0) {
                m11809h2(i10, c6819c, i9, list2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: E1 */
        public /* synthetic */ void m11673E1(final C6819c c6819c, final MessageObj messageObj, final int i9, final int i10, final List list, final String str, final List list2) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                C5047d c5047d = (C5047d) it.next();
                ChatDialogActivity.this.f9843M0.put(c5047d.m19714f(), c5047d);
            }
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.f4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22238b.m11671D1(str, c6819c, list2, messageObj, i9, i10, list);
                }
            });
        }

        /* renamed from: b1 */
        public static /* synthetic */ void m11686b1(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c1 */
        public /* synthetic */ void m11688c1(List list, DialogInterface dialogInterface, int i9) throws JSONException {
            m11778V(list);
        }

        /* renamed from: d1 */
        public static /* synthetic */ void m11690d1(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e1 */
        public /* synthetic */ void m11692e1(ProgressDialog progressDialog, StickerPackObj stickerPackObj) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            ChatDialogActivity.this.f9853O2 = false;
            if (stickerPackObj == null) {
                return;
            }
            C2950b0.m14925x().m15286d(stickerPackObj);
            ChatDialogActivity.this.m11534aa(stickerPackObj);
        }

        /* renamed from: f1 */
        public static /* synthetic */ void m11694f1(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g1 */
        public /* synthetic */ void m11696g1(List list, DialogInterface dialogInterface, int i9) {
            m11812j0(list);
        }

        /* renamed from: h1 */
        public static /* synthetic */ void m11698h1(DialogInterface dialogInterface, int i9) {
        }

        /* renamed from: i1 */
        public static /* synthetic */ void m11700i1(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j1 */
        public /* synthetic */ void m11702j1(List list, DialogInterface dialogInterface, int i9) throws NumberFormatException {
            m11814k0(list);
        }

        /* renamed from: k1 */
        public static /* synthetic */ void m11704k1(DialogInterface dialogInterface, int i9) {
        }

        /* renamed from: l1 */
        public static /* synthetic */ void m11706l1(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: m1 */
        public /* synthetic */ void m11708m1(List list, boolean z8, DialogInterface dialogInterface, int i9) throws NumberFormatException {
            m11753L1(list, z8);
        }

        /* renamed from: n1 */
        public static /* synthetic */ void m11710n1(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o1 */
        public /* synthetic */ void m11712o1(View view) {
            CLUtility.m16589t1(ChatDialogActivity.this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p1 */
        public /* synthetic */ void m11714p1(View view) {
            CLUtility.m16477P1(ChatDialogActivity.this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
        /* renamed from: q1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public /* synthetic */ void m11716q1(View view) throws JSONException, NumberFormatException {
            MessageObj item;
            String str;
            String str2;
            Integer num = (Integer) view.getTag();
            if (num == null || (item = getItem(num.intValue())) == null) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(item.m14779q());
                String string = jSONObject.getString("albumId");
                long j9 = -1;
                long j10 = NumberUtils.toLong(jSONObject.getString("mediaId"), -1L);
                if (j10 != -1) {
                    C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j10);
                    String strM14747I = item.m14747I("commentId");
                    if (strM14747I != null && !strM14747I.isEmpty()) {
                        try {
                            j9 = Long.parseLong(strM14747I);
                        } catch (Exception unused) {
                        }
                    }
                    long j11 = j9;
                    String strM14747I2 = item.m14747I("commentType");
                    if (strM14747I2 == null) {
                        str = "None";
                    } else {
                        if (strM14747I2.equals("Text")) {
                            str2 = "CommentText";
                        } else if (strM14747I2.equals("Audio")) {
                            str2 = "CommentMedia";
                        } else if (item.m14778p() == MessageObj.MessageType.CommentUpdate) {
                            str = strM14747I2;
                        }
                        str = str2;
                    }
                    if (c2973l0M14725v == null) {
                        ChatDialogActivity.this.m11587ob(string, j10, 0L, true, j11, str, false);
                    } else {
                        ChatDialogActivity.this.m11530Z9(c2973l0M14725v.m15131c(), c2973l0M14725v.m15144p(), 0L, true, j11, str, false);
                    }
                }
            } catch (JSONException e9) {
                Log.d("ChatDialogActivity", Log.getStackTraceString(e9));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: r1 */
        public /* synthetic */ void m11718r1(View view) {
            MessageObj item;
            Integer num = (Integer) view.getTag();
            if (num == null || (item = getItem(num.intValue())) == null || item.m14779q() == null) {
                return;
            }
            ChatDialogActivity.this.m11538ba(C2950b0.m14899A().m15003C(item.m14747I("userId")));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: t1 */
        public /* synthetic */ void m11722t1(String str, View view) {
            if (ChatDialogMode.NORMAL.equals(ChatDialogActivity.this.f9983x0)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse(str));
                ChatDialogActivity.this.startActivity(intent);
            }
        }

        /* renamed from: u1 */
        public static /* synthetic */ void m11724u1(TextView textView, Object obj, Friend friend) {
            Object tag = textView.getTag();
            if (tag == null || tag != obj) {
                return;
            }
            textView.setText(friend.m15621b());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: v1 */
        public /* synthetic */ void m11726v1(final TextView textView, final Object obj, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("ChatDialogActivity", "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d("ChatDialogActivity", "statusCode=" + str3);
                return;
            }
            final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str4));
            if (friendM20184f == null) {
                ULogUtility.m16670f("ChatDialogActivity", "[setForwarderName] Can not parse friend info.");
            } else {
                C2950b0.m14899A().m15016h(friendM20184f);
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.q4
                    @Override // java.lang.Runnable
                    public final void run() {
                        ChatDialogActivity.C1954e2.m11724u1(textView, obj, friendM20184f);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: w1 */
        public /* synthetic */ void m11728w1(MessageObj messageObj, String str, C6819c c6819c, int i9, StickerPackObj stickerPackObj) {
            if (stickerPackObj == null) {
                return;
            }
            new k(stickerPackObj, messageObj, str, c6819c, i9).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: x1 */
        public /* synthetic */ void m11730x1(C6819c c6819c, Object obj, Friend friend) {
            Object tag = c6819c.f22611a.getTag();
            if (tag == null || tag != obj) {
                return;
            }
            m11783W1(c6819c, friend);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: y1 */
        public /* synthetic */ void m11732y1(final C6819c c6819c, final Object obj, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("ChatDialogActivity", "Response is null");
                return;
            }
            if (str3.equals("200")) {
                final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str4));
                C2950b0.m14899A().m15018j(friendM20184f, false);
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.i4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22310b.m11730x1(c6819c, obj, friendM20184f);
                    }
                });
            } else {
                Log.d("ChatDialogActivity", "statusCode=" + str3);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: z1 */
        public /* synthetic */ void m11734z1(final TextView textView, final Object obj, final ImageView imageView, String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d("ChatDialogActivity", "Response is null");
                return;
            }
            if (str3.equals("200")) {
                final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str4));
                C2950b0.m14899A().m15018j(friendM20184f, false);
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.b4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22192b.m11665A1(textView, obj, imageView, friendM20184f);
                    }
                });
            } else {
                Log.d("ChatDialogActivity", "statusCode=" + str3);
            }
        }

        /* renamed from: A0 */
        public void m11735A0(List<MessageObj> list) {
            if (list == null || list.size() <= 0) {
                return;
            }
            ChatDialogActivity.this.new AsyncTaskC2020y1(list).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: B0 */
        public void m11736B0(MessageObj messageObj) {
            StickerObj stickerObjM11617y7;
            Log.d("ChatDialogActivity", "[handleOnDetailMessageButtonClick]");
            if (messageObj == null) {
                return;
            }
            int iM25239a = C6598a.m25239a(messageObj);
            if ((23 == iM25239a || 2 == iM25239a || 22 == iM25239a || 1 == iM25239a || 25 == iM25239a || 4 == iM25239a || 32 == iM25239a || 11 == iM25239a) && (stickerObjM11617y7 = ChatDialogActivity.this.m11617y7(messageObj)) != null) {
                StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerObjM11617y7.m16284i());
                if (stickerPackObjM15293k != null) {
                    ChatDialogActivity.this.m11534aa(stickerPackObjM15293k);
                    return;
                }
                final ProgressDialog progressDialogShow = ProgressDialog.show(ChatDialogActivity.this.m11582n7(), "", ChatDialogActivity.this.getString(R.string.loading), true);
                if (ChatDialogActivity.this.f9853O2) {
                    return;
                }
                ChatDialogActivity.this.f9853O2 = true;
                ChatDialogActivity.this.f9845M2 = ChatDialogActivity.this.new AsyncTaskC1942b2(stickerObjM11617y7.m16284i(), new InterfaceC1962g2() { // from class: y2.w4
                    @Override // com.cyberlink.you.activity.chatdialog.ChatDialogActivity.InterfaceC1962g2
                    /* renamed from: a */
                    public final void mo11877a(StickerPackObj stickerPackObj) {
                        this.f22473a.m11692e1(progressDialogShow, stickerPackObj);
                    }
                });
                ChatDialogActivity.this.f9845M2.executeOnExecutor(C6385v.f21554b, new Void[0]);
            }
        }

        /* renamed from: C0 */
        public void m11737C0(final List<MessageObj> list) {
            Log.d("ChatDialogActivity", "[handleOnForwardMessageButtonClick]");
            if (list == null || list.size() == 0) {
                return;
            }
            List<MessageObj> listM11820o0 = m11820o0(list);
            list.removeAll(listM11820o0);
            if (listM11820o0.size() <= 0) {
                m11812j0(list);
                return;
            }
            if (list.size() == 0) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
                builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.feedback_error));
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.error_media_not_found));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.j4
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.C1954e2.m11694f1(dialogInterface, i9);
                    }
                });
                builderM16382a.show();
                return;
            }
            AlertDialog.Builder builderM16382a2 = C3123g.m16382a(ChatDialogActivity.this);
            builderM16382a2.setTitle(ChatDialogActivity.this.getString(R.string.forward_messages));
            builderM16382a2.setMessage(ChatDialogActivity.this.getString(R.string.forward_delete_media_messages_warning));
            builderM16382a2.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.k4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f22338b.m11696g1(list, dialogInterface, i9);
                }
            });
            builderM16382a2.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.l4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ChatDialogActivity.C1954e2.m11698h1(dialogInterface, i9);
                }
            });
            builderM16382a2.show();
        }

        /* renamed from: D0 */
        public void m11738D0(final List<MessageObj> list) throws NumberFormatException {
            Log.d("ChatDialogActivity", "[handleOnForwardPhotoWithCommentMessageButtonClick]");
            if (list == null || list.size() == 0) {
                return;
            }
            List<MessageObj> listM11820o0 = m11820o0(list);
            list.removeAll(listM11820o0);
            if (listM11820o0.size() <= 0) {
                m11814k0(list);
                return;
            }
            if (list.size() == 0) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
                builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.feedback_error));
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.error_media_not_found));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.c4
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.C1954e2.m11700i1(dialogInterface, i9);
                    }
                });
                builderM16382a.show();
                return;
            }
            AlertDialog.Builder builderM16382a2 = C3123g.m16382a(ChatDialogActivity.this);
            builderM16382a2.setTitle(ChatDialogActivity.this.getString(R.string.forward_messages));
            builderM16382a2.setMessage(ChatDialogActivity.this.getString(R.string.forward_delete_media_messages_warning));
            builderM16382a2.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.d4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) throws NumberFormatException {
                    this.f22217b.m11702j1(list, dialogInterface, i9);
                }
            });
            builderM16382a2.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.e4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ChatDialogActivity.C1954e2.m11704k1(dialogInterface, i9);
                }
            });
            builderM16382a2.show();
        }

        /* renamed from: E0 */
        public void m11739E0(MessageObj messageObj) {
            StickerObj stickerObjM11617y7;
            Log.d("ChatDialogActivity", "[handleOnOpenMessageButtonClick]");
            if (messageObj == null) {
                return;
            }
            int iM25239a = C6598a.m25239a(messageObj);
            if ((23 == iM25239a || 2 == iM25239a || 22 == iM25239a || 1 == iM25239a || 25 == iM25239a || 4 == iM25239a || 32 == iM25239a || 11 == iM25239a) && (stickerObjM11617y7 = ChatDialogActivity.this.m11617y7(messageObj)) != null) {
                if (!ChatDialogActivity.this.f9981w2.m12083y().isVisible()) {
                    C2027b c2027b = ChatDialogActivity.this.f9981w2;
                    Boolean bool = Boolean.FALSE;
                    c2027b.m12075a0(bool, Boolean.TRUE, bool);
                }
                ChatDialogActivity.this.f9981w2.m12083y().m19505g0(stickerObjM11617y7.m16284i());
            }
        }

        /* renamed from: F0 */
        public void m11740F0(List<MessageObj> list) {
            if (list == null || list.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (MessageObj messageObj : list) {
                if (messageObj.m14752N()) {
                    arrayList.add(messageObj.m14777o());
                } else {
                    arrayList2.add(messageObj.m14777o());
                }
            }
            new AsyncTaskC1958f2(ChatDialogActivity.this.f9910f, arrayList, arrayList2, new r(new DialogC3133q.b(ChatDialogActivity.this).m16411b(), list)).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: F1 */
        public boolean m11741F1(MessageObj messageObj) {
            for (MessageObj messageObj2 : this.f10041k) {
                if (messageObj2 != null && messageObj != null && TextUtils.equals(messageObj2.m14777o(), messageObj.m14777o())) {
                    this.f10041k.remove(messageObj2);
                    m11763Q(messageObj, false);
                    return true;
                }
            }
            return false;
        }

        /* renamed from: G0 */
        public void m11742G0(MessageObj messageObj) throws NumberFormatException {
            String strM14747I;
            if (messageObj == null) {
                return;
            }
            ULogUtility.m16670f("ChatDialogActivity", "[ResendMessage] : " + messageObj.m14777o());
            if (ChatDialogActivity.this.f9810E != null) {
                ChatDialogActivity.this.f9810E.remove(messageObj);
            }
            ArrayList arrayList = new ArrayList();
            messageObj.m14761W(new Date());
            messageObj.m14764Z(new Date());
            arrayList.add("SendTime");
            boolean z8 = false;
            boolean z9 = messageObj.m14778p() != null && messageObj.m14778p().equals(MessageObj.MessageType.ShareLocation) && (strM14747I = messageObj.m14747I("snapshotUrl")) != null && strM14747I.equals("NO");
            if (!messageObj.m14744F().equals("4") && !messageObj.m14744F().equals("5") && !z9) {
                if (messageObj.m14740B().equals("2") || messageObj.m14740B().equals("3")) {
                    messageObj.m14762X("2");
                    arrayList.add("Status");
                    C2950b0.m14916o().m15160E(messageObj.m14777o(), messageObj, arrayList);
                    if (ChatDialogActivity.this.f9810E != null) {
                        ChatDialogActivity.this.f9810E.m11781W(messageObj);
                    }
                    ChatDialogActivity.this.m11447Ca(ChatDialogActivity.this.f9910f, messageObj);
                }
                if (z8 || ChatDialogActivity.this.f9991z0 == null) {
                }
                ChatDialogActivity.this.f9991z0.schedule(ChatDialogActivity.this.new C1974j2(), 1000L);
                return;
            }
            messageObj.m14765a0("1");
            arrayList.add("UploadStatus");
            C2950b0.m14916o().m15160E(messageObj.m14777o(), messageObj, arrayList);
            if (ChatDialogActivity.this.f9810E != null) {
                ChatDialogActivity.this.f9810E.m11781W(messageObj);
            }
            if (messageObj.m14778p().equals(MessageObj.MessageType.PhotoNote)) {
                ChatDialogActivity.this.m11453Ea(messageObj);
            } else if (messageObj.m14778p().equals(MessageObj.MessageType.Audio)) {
                ChatDialogActivity.this.m11483Oa(messageObj);
            } else if (messageObj.m14778p().equals(MessageObj.MessageType.Video)) {
                ChatDialogActivity.this.m11474La(messageObj);
            } else if (messageObj.m14778p().equals(MessageObj.MessageType.ShareLocation)) {
                ChatDialogActivity.this.m11462Ha(messageObj, messageObj.m14747I("imageItem"));
            } else {
                ChatDialogActivity.this.m11450Da(messageObj);
            }
            z8 = true;
            if (z8) {
            }
        }

        /* renamed from: G1 */
        public final View.OnClickListener m11743G1(final ImageView imageView) {
            return new View.OnClickListener() { // from class: y2.x3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    imageView.performClick();
                }
            };
        }

        /* renamed from: H0 */
        public void m11744H0(final List<MessageObj> list, final boolean z8) throws NumberFormatException {
            Log.d("ChatDialogActivity", "[handleOnSaveToAlbumMessageButtonClick] isSaveToMyAlbum : " + z8);
            if (list == null || list.size() == 0) {
                return;
            }
            List<MessageObj> listM11820o0 = m11820o0(list);
            list.removeAll(listM11820o0);
            if (listM11820o0.size() <= 0) {
                m11753L1(list, z8);
                return;
            }
            if (list.size() == 0) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
                builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.feedback_error));
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.error_media_not_found));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.y3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.C1954e2.m11706l1(dialogInterface, i9);
                    }
                });
                builderM16382a.show();
                return;
            }
            AlertDialog.Builder builderM16382a2 = C3123g.m16382a(ChatDialogActivity.this);
            builderM16382a2.setTitle(ChatDialogActivity.this.getString(R.string.save_photos));
            builderM16382a2.setMessage(ChatDialogActivity.this.getString(R.string.save_delete_photo_warning));
            builderM16382a2.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.z3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) throws NumberFormatException {
                    this.f22523b.m11708m1(list, z8, dialogInterface, i9);
                }
            });
            builderM16382a2.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.a4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ChatDialogActivity.C1954e2.m11710n1(dialogInterface, i9);
                }
            });
            builderM16382a2.show();
        }

        /* renamed from: H1 */
        public final boolean m11745H1(int i9) {
            return ChatDialogActivity.this.f9946o.getLastVisiblePosition() >= m11823r0(i9) && m11823r0(i9) >= ChatDialogActivity.this.f9946o.getFirstVisiblePosition();
        }

        /* renamed from: I0 */
        public void m11746I0(List<MessageObj> list) {
            Log.d("ChatDialogActivity", "[handleOnSaveToMyDeviceMessageButtonClick]");
            if (list == null || list.size() == 0) {
                return;
            }
            new s(list, m11824s0(list)).executeOnExecutor(ChatDialogActivity.f9793Y2, new Void[0]);
        }

        /* renamed from: I1 */
        public final void m11747I1(View view, final String str) {
            view.setOnClickListener(new View.OnClickListener() { // from class: y2.h4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f22272b.m11722t1(str, view2);
                }
            });
        }

        /* renamed from: J0 */
        public final boolean m11748J0(int i9) {
            return !ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.NORMAL) && isEnabled(i9);
        }

        @Override // android.widget.ArrayAdapter
        /* renamed from: J1, reason: merged with bridge method [inline-methods] */
        public void remove(MessageObj messageObj) {
            int iIndexOf = this.f10040j.indexOf(messageObj);
            if (iIndexOf >= 0) {
                if (iIndexOf >= 1) {
                    MessageObj messageObj2 = this.f10040j.get(iIndexOf - 1);
                    if (m11782W0(messageObj2) && m11785X0(iIndexOf + 1)) {
                        this.f10040j.remove(messageObj2);
                        ChatDialogActivity.this.f9806D.remove(messageObj2.m14777o());
                    }
                }
                this.f10040j.remove(messageObj);
            }
            this.f10041k.remove(messageObj);
            ChatDialogActivity.this.f9806D.remove(messageObj.m14777o());
            notifyDataSetChanged();
        }

        /* renamed from: K0 */
        public final boolean m11750K0(C6819c c6819c) {
            ImageView imageView = c6819c.f22607W;
            if (imageView != null && imageView.getVisibility() == 0) {
                return true;
            }
            TextView textView = c6819c.f22608X;
            if (textView != null && textView.getVisibility() == 0) {
                return true;
            }
            TextView textView2 = c6819c.f22609Y;
            if (textView2 != null && textView2.getVisibility() == 0) {
                return true;
            }
            LinearLayout linearLayout = c6819c.f22610Z;
            return linearLayout != null && linearLayout.getVisibility() == 0;
        }

        /* renamed from: K1 */
        public final void m11751K1(MessageObj messageObj) {
            MessageObj messageObj2 = (MessageObj) ChatDialogActivity.this.f9806D.get(messageObj.m14777o());
            if (messageObj2 == null) {
                return;
            }
            MessageObj.MessageType messageTypeM14778p = messageObj2.m14778p();
            MessageObj.MessageType messageType = MessageObj.MessageType.ENCRYPTED_MSG;
            if (messageTypeM14778p == messageType && messageObj.m14778p() != messageType) {
                remove(messageObj2);
            }
        }

        /* renamed from: L0 */
        public void m11752L0(String str, String str2) {
            MessageObj messageObj = (MessageObj) ChatDialogActivity.this.f9806D.get(str);
            if (messageObj == null || C5170o0.m20170e(str2)) {
                return;
            }
            messageObj.m14755Q(str2);
        }

        /* renamed from: L1 */
        public void m11753L1(List<MessageObj> list, boolean z8) throws NumberFormatException {
            long[] jArrM11824s0 = m11824s0(list);
            if (jArrM11824s0.length != 0) {
                Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) GroupAlbumActivity.class);
                if (z8) {
                    intent.putExtra("CopyToMyAlbum", true);
                } else {
                    intent.putExtra("Group", ChatDialogActivity.this.f9914g);
                }
                intent.putExtra("select", true);
                intent.putExtra("share_media_id", jArrM11824s0);
                intent.putExtra("withComment", true);
                if (z8) {
                    intent.putExtra("ShowShareToMyAlbum", true);
                } else {
                    intent.putExtra("ShareToGroupAlbum", true);
                }
                ChatDialogActivity.this.startActivity(intent);
            }
        }

        /* renamed from: M0 */
        public final void m11754M0(MessageObj messageObj, C6819c c6819c, int i9) {
            boolean z8 = messageObj.m14773k() != null;
            if (i9 == 0) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_r_s2_highlight);
                return;
            }
            if (i9 == 20) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_r_s2_highlight);
                return;
            }
            if (i9 == 5 || i9 == 7) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_r_s2_highlight);
                return;
            }
            if (i9 == 8) {
                int paddingLeft = c6819c.f22590F.getPaddingLeft();
                int paddingRight = c6819c.f22590F.getPaddingRight();
                int paddingTop = c6819c.f22590F.getPaddingTop();
                c6819c.f22590F.setBackgroundResource(z8 ? R.drawable.photo_voice_message_bg_blue_highlight : R.drawable.photo_voice_message_bg_blue);
                c6819c.f22589E.setBackgroundResource(z8 ? R.drawable.photo_voice_message_arrow_blue2_highlight : R.drawable.photo_voice_message_arrow_blue2);
                c6819c.f22590F.setPadding(paddingLeft, paddingTop, paddingRight, 0);
                if (z8) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) c6819c.f22590F.getLayoutParams();
                    layoutParams.setMargins(0, Math.round(TypedValue.applyDimension(1, -1.6f, Globals.m7388i0().getResources().getDisplayMetrics())), 0, 0);
                    c6819c.f22590F.setLayoutParams(layoutParams);
                    c6819c.f22589E.bringToFront();
                    return;
                }
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) c6819c.f22590F.getLayoutParams();
                layoutParams2.setMargins(0, Math.round(TypedValue.applyDimension(1, -2.0f, Globals.m7388i0().getResources().getDisplayMetrics())), 0, 0);
                c6819c.f22590F.setLayoutParams(layoutParams2);
                c6819c.f22590F.bringToFront();
                return;
            }
            if (i9 == 18) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22638z.setBackgroundResource(R.drawable.chat_bg_r_s2_highlight);
                return;
            }
            if (i9 == 21) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_l_r_highlight);
                return;
            }
            if (i9 == 46) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_l_r_highlight);
                return;
            }
            if (i9 == 26 || i9 == 28) {
                if (!z8 || m11750K0(c6819c)) {
                    return;
                }
                c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_l_r_highlight);
                return;
            }
            if (i9 != 29) {
                if (i9 == 43) {
                    if (!z8 || m11750K0(c6819c)) {
                        return;
                    }
                    c6819c.f22638z.setBackgroundResource(R.drawable.chat_bg_l_r_highlight);
                    return;
                }
                if ((i9 == 37 || i9 == 13) && z8 && !m11750K0(c6819c)) {
                    c6819c.f22638z.setBackgroundResource(R.drawable.chat_bg_l_r_highlight);
                    return;
                }
                return;
            }
            int paddingLeft2 = c6819c.f22590F.getPaddingLeft();
            int paddingRight2 = c6819c.f22590F.getPaddingRight();
            int paddingTop2 = c6819c.f22590F.getPaddingTop();
            c6819c.f22590F.setBackgroundResource(z8 ? R.drawable.photo_voice_message_bg_gray_highlight : R.drawable.photo_voice_message_bg_gray);
            c6819c.f22589E.setBackgroundResource(z8 ? R.drawable.photo_voice_message_arrow_gray2_highlight : R.drawable.photo_voice_message_arrow_gray2);
            c6819c.f22590F.setPadding(paddingLeft2, paddingTop2, paddingRight2, 0);
            if (z8) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) c6819c.f22590F.getLayoutParams();
                layoutParams3.setMargins(0, Math.round(TypedValue.applyDimension(1, -1.6f, Globals.m7388i0().getResources().getDisplayMetrics())), 0, 0);
                c6819c.f22590F.setLayoutParams(layoutParams3);
                c6819c.f22589E.bringToFront();
                return;
            }
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) c6819c.f22590F.getLayoutParams();
            layoutParams4.setMargins(0, Math.round(TypedValue.applyDimension(1, -2.0f, Globals.m7388i0().getResources().getDisplayMetrics())), 0, 0);
            c6819c.f22590F.setLayoutParams(layoutParams4);
            c6819c.f22590F.bringToFront();
        }

        /* renamed from: M1 */
        public final void m11755M1(final TextView textView, String str) {
            if (C5170o0.m20170e(str)) {
                return;
            }
            Friend friendM15004D = C2950b0.m14899A().m15004D(str);
            if (friendM15004D != null) {
                textView.setText(friendM15004D.m15621b());
                return;
            }
            final Object obj = new Object();
            textView.setText("");
            textView.setTag(obj);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("jid", str));
            ChatDialogActivity.this.f9899c0.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.v4
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str2, String str3, String str4, String str5) {
                    this.f22458a.m11726v1(textView, obj, str2, str3, str4, str5);
                }
            });
        }

        /* renamed from: N0 */
        public int m11756N0(MessageObj messageObj) {
            int iIndexOf = this.f10040j.indexOf(messageObj);
            if (iIndexOf != -1) {
                return iIndexOf;
            }
            int iIndexOf2 = this.f10041k.indexOf(messageObj);
            return iIndexOf2 != -1 ? iIndexOf2 + this.f10040j.size() : iIndexOf2;
        }

        /* renamed from: N1 */
        public final void m11757N1(int i9, C6819c c6819c, int i10, List<C5047d> list) {
            LinearLayout linearLayout = c6819c.f22610Z;
            LayoutInflater layoutInflater = (LayoutInflater) ChatDialogActivity.this.m11582n7().getSystemService("layout_inflater");
            linearLayout.setVisibility(0);
            if (i9 == 26 || i9 == 28) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) c6819c.f22628p.getLayoutParams();
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) c6819c.f22623k.getLayoutParams();
                layoutParams.addRule(8, c6819c.f22610Z.getId());
                layoutParams2.addRule(8, c6819c.f22610Z.getId());
                c6819c.f22628p.setLayoutParams(layoutParams);
                c6819c.f22623k.setLayoutParams(layoutParams2);
            }
            c6819c.f22620h.setMinWidth((int) ChatDialogActivity.this.getResources().getDimension(R.dimen.t160dp));
            for (int i11 = 0; i11 < list.size(); i11++) {
                if (i11 > 0) {
                    TextView textView = new TextView(Globals.m7372O());
                    textView.setWidth(c6819c.f22620h.getWidth());
                    textView.setHeight((int) ChatDialogActivity.this.getResources().getDimension(R.dimen.t3dp));
                    linearLayout.addView(textView);
                }
                C5047d c5047d = list.get(i11);
                View viewInflate = layoutInflater.inflate(R.layout.view_item_url_preview, (ViewGroup) null);
                viewInflate.setBackgroundColor(Color.argb(255, 217, 217, 217));
                viewInflate.setTag(R.id.tag_Position, Integer.valueOf(i10));
                m11747I1(viewInflate, c5047d.m19714f());
                ChatDialogActivity.this.registerForContextMenu(viewInflate);
                if (i11 == list.size() - 1) {
                    viewInflate.setBackgroundResource(R.drawable.bg_url_bottom_corner);
                }
                ImageView imageView = (ImageView) viewInflate.findViewById(R.id.previewImage);
                C6136j.m23605y(ChatDialogActivity.this, imageView, c5047d.m19710b() != null ? c5047d.m19711c() : "", R.drawable.url_default_img, new C6250g().mo23527l0(new C5074g(), new C5085r(12)));
                imageView.setVisibility(0);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.previewText);
                textView2.setMaxWidth(c6819c.f22620h.getMaxWidth());
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView2.getLayoutParams();
                marginLayoutParams.leftMargin = (int) ChatDialogActivity.this.getResources().getDimension(R.dimen.t2dp);
                textView2.setLayoutParams(marginLayoutParams);
                if (!TextUtils.isEmpty(c5047d.m19713e())) {
                    textView2.setText(c5047d.m19713e());
                } else if (TextUtils.isEmpty(c5047d.m19709a())) {
                    textView2.setText("");
                } else {
                    textView2.setText(c5047d.m19709a());
                }
                linearLayout.addView(viewInflate);
            }
        }

        /* renamed from: O0 */
        public final void m11758O0(MessageObj messageObj) {
            int iM11793a0 = m11793a0(messageObj);
            if (iM11793a0 < 0) {
                this.f10040j.add(messageObj);
            } else {
                this.f10040j.add(iM11793a0, messageObj);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00b5  */
        /* renamed from: O1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m11759O1(C6819c c6819c, MessageObj messageObj, boolean z8, int i9) throws NumberFormatException, FileNotFoundException {
            String strM14747I;
            Uri uriM16510Z1;
            int iIntValue;
            int iIntValue2;
            int i10;
            int i11;
            if (c6819c == null || messageObj == null) {
                return;
            }
            if (i9 == 14) {
                C6136j.m23599s(ChatDialogActivity.this, c6819c.f22615c, messageObj.m14747I("imageItem"));
                return;
            }
            long j9 = NumberUtils.toLong(messageObj.m14747I("mediaId"), -1L);
            if (j9 != -1) {
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                if (c2973l0M14725v != null) {
                    int iM15151w = c2973l0M14725v.m15151w();
                    int iM15141m = c2973l0M14725v.m15141m();
                    if (iM15151w == 0 || iM15141m == 0) {
                        c6819c.f22615c.getLayoutParams().height = this.f10033c;
                        c6819c.f22615c.getLayoutParams().width = this.f10032b;
                        ChatDialogActivity.m11188Rb(c2973l0M14725v);
                    } else {
                        float f9 = iM15151w;
                        float f10 = this.f10035e / f9;
                        int i12 = (int) (f9 * f10);
                        float f11 = iM15141m;
                        int i13 = (int) (f10 * f11);
                        int i14 = this.f10036f;
                        if (i13 > i14) {
                            i12 = (int) (f9 * (i14 / f11));
                            i13 = i14;
                        }
                        c6819c.f22615c.getLayoutParams().height = i13;
                        c6819c.f22615c.getLayoutParams().width = i12;
                    }
                    MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, c2973l0M14725v, MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                    return;
                }
                return;
            }
            if (12 == i9) {
                strM14747I = messageObj.m14747I("videoThumbPath");
                uriM16510Z1 = null;
            } else {
                strM14747I = messageObj.m14747I("imageItem");
                uriM16510Z1 = CLUtility.m16510Z1(messageObj.m14747I("imageItemUri"));
            }
            String str = strM14747I;
            if (12 == i9) {
                iIntValue2 = 0;
                try {
                    iIntValue = Integer.parseInt(messageObj.m14747I("width"));
                } catch (NumberFormatException e9) {
                    e = e9;
                    iIntValue = 0;
                }
                try {
                    iIntValue2 = Integer.parseInt(messageObj.m14747I("height"));
                } catch (NumberFormatException e10) {
                    e = e10;
                    Log.d("ChatDialogActivity", "ex:" + e.getMessage());
                    if (iIntValue != 0) {
                        TranscodeUtility.m16303a(messageObj.m14747I("videoPath"), CLUtility.m16510Z1(messageObj.m14747I("videoUri")));
                    }
                    float f12 = iIntValue;
                    float f13 = this.f10035e / f12;
                    int i15 = (int) (f12 * f13);
                    float f14 = iIntValue2;
                    i10 = (int) (f13 * f14);
                    i11 = this.f10036f;
                    if (i10 > i11) {
                    }
                    c6819c.f22615c.getLayoutParams().height = i10;
                    c6819c.f22615c.getLayoutParams().width = i15;
                    C6136j.m23604x(ChatDialogActivity.this, c6819c.f22615c, str, 0, 384, true);
                }
                if (iIntValue != 0 || iIntValue2 == 0) {
                    TranscodeUtility.m16303a(messageObj.m14747I("videoPath"), CLUtility.m16510Z1(messageObj.m14747I("videoUri")));
                }
            } else {
                Pair<Integer, Integer> pairM16503X0 = CLUtility.m16503X0(str, uriM16510Z1);
                iIntValue = ((Integer) pairM16503X0.first).intValue();
                iIntValue2 = ((Integer) pairM16503X0.second).intValue();
            }
            float f122 = iIntValue;
            float f132 = this.f10035e / f122;
            int i152 = (int) (f122 * f132);
            float f142 = iIntValue2;
            i10 = (int) (f132 * f142);
            i11 = this.f10036f;
            if (i10 > i11) {
                i152 = (int) (f122 * (i11 / f142));
                i10 = i11;
            }
            c6819c.f22615c.getLayoutParams().height = i10;
            c6819c.f22615c.getLayoutParams().width = i152;
            C6136j.m23604x(ChatDialogActivity.this, c6819c.f22615c, str, 0, 384, true);
        }

        @Override // android.widget.ArrayAdapter
        /* renamed from: P, reason: merged with bridge method [inline-methods] */
        public void add(MessageObj messageObj) {
            m11763Q(messageObj, true);
        }

        /* renamed from: P0 */
        public final boolean m11761P0(int i9) {
            List<MessageObj> list = this.f10040j;
            if (list == null) {
                return true;
            }
            int size = i9 - list.size();
            if (!m11791Z0(i9)) {
                return false;
            }
            List<MessageObj> list2 = this.f10041k;
            if (list2 != null && size < list2.size()) {
                return false;
            }
            Log.i("ChatDialogActivity", "[isIllegalMsgPosition]the position : " + i9 + "is illegal message index");
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:46:0x00ee  */
        /* renamed from: P1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m11762P1(int i9, C6819c c6819c, int i10, C5047d c5047d) {
            RelativeLayout.LayoutParams layoutParams;
            RelativeLayout.LayoutParams layoutParams2;
            int measuredWidth;
            int i11;
            if (i9 == 26 || i9 == 28) {
                layoutParams = (RelativeLayout.LayoutParams) c6819c.f22628p.getLayoutParams();
                layoutParams2 = (RelativeLayout.LayoutParams) c6819c.f22623k.getLayoutParams();
            } else {
                layoutParams = null;
                layoutParams2 = null;
            }
            c6819c.f22620h.setMinWidth((int) ChatDialogActivity.this.getResources().getDimension(R.dimen.t160dp));
            Bitmap bitmapM19710b = c5047d.m19710b();
            if (bitmapM19710b != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) c6819c.f22607W.getLayoutParams();
                int i12 = (i9 == 0 || i9 == 20 || i9 == 18 || i9 == 5 || i9 == 7) ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
                if (i9 == 0 || i9 == 21) {
                    c6819c.f22620h.measure(-2, -2);
                    measuredWidth = c6819c.f22620h.getMeasuredWidth();
                } else if (i9 == 20 || i9 == 46) {
                    c6819c.f22627o.measure(-2, -2);
                    measuredWidth = c6819c.f22627o.getMeasuredWidth();
                } else if (i9 == 18 || i9 == 43) {
                    measuredWidth = c6819c.f22638z.getMeasuredWidth();
                } else if (i9 == 5 || i9 == 7 || i9 == 26 || i9 == 28) {
                    c6819c.f22638z.measure(-2, -2);
                    measuredWidth = c6819c.f22638z.getMeasuredWidth();
                } else {
                    i11 = 0;
                    marginLayoutParams.height = (int) (i11 * 0.56d);
                    marginLayoutParams.width = i11;
                    c6819c.f22607W.setLayoutParams(marginLayoutParams);
                    c6819c.f22607W.setImageBitmap(bitmapM19710b);
                    c6819c.f22607W.setBackgroundColor(Color.argb(255, 217, 217, 217));
                    c6819c.f22607W.setTag(R.id.tag_Position, Integer.valueOf(i10));
                    m11747I1(c6819c.f22607W, c5047d.m19714f());
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22607W);
                    c6819c.f22607W.setVisibility(0);
                    if (i9 != 26 || i9 == 28) {
                        layoutParams.addRule(8, c6819c.f22607W.getId());
                        layoutParams2.addRule(8, c6819c.f22607W.getId());
                    }
                }
                i11 = measuredWidth - i12;
                marginLayoutParams.height = (int) (i11 * 0.56d);
                marginLayoutParams.width = i11;
                c6819c.f22607W.setLayoutParams(marginLayoutParams);
                c6819c.f22607W.setImageBitmap(bitmapM19710b);
                c6819c.f22607W.setBackgroundColor(Color.argb(255, 217, 217, 217));
                c6819c.f22607W.setTag(R.id.tag_Position, Integer.valueOf(i10));
                m11747I1(c6819c.f22607W, c5047d.m19714f());
                ChatDialogActivity.this.registerForContextMenu(c6819c.f22607W);
                c6819c.f22607W.setVisibility(0);
                if (i9 != 26) {
                    layoutParams.addRule(8, c6819c.f22607W.getId());
                    layoutParams2.addRule(8, c6819c.f22607W.getId());
                }
            }
            if (!TextUtils.isEmpty(c5047d.m19713e())) {
                c6819c.f22608X.setMaxWidth(m11828w0(i9, c6819c, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                c6819c.f22608X.setText(c5047d.m19713e());
                int i13 = (bitmapM19710b == null && TextUtils.isEmpty(c5047d.m19709a())) ? 1 : 0;
                TextView textView = c6819c.f22608X;
                textView.setTypeface(textView.getTypeface(), i13 ^ 1);
                if (TextUtils.isEmpty(c5047d.m19709a())) {
                    c6819c.f22608X.setBackgroundResource(R.drawable.bg_url_bottom_corner);
                } else {
                    c6819c.f22608X.setBackgroundColor(Color.argb(255, 217, 217, 217));
                }
                c6819c.f22608X.setTag(R.id.tag_Position, Integer.valueOf(i10));
                m11747I1(c6819c.f22608X, c5047d.m19714f());
                ChatDialogActivity.this.registerForContextMenu(c6819c.f22608X);
                c6819c.f22608X.setVisibility(0);
                if (i9 == 26 || i9 == 28) {
                    layoutParams.addRule(8, c6819c.f22608X.getId());
                    layoutParams2.addRule(8, c6819c.f22608X.getId());
                }
            }
            if (!TextUtils.isEmpty(c5047d.m19709a())) {
                c6819c.f22609Y.setMaxWidth(m11828w0(i9, c6819c, PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
                c6819c.f22609Y.setText(c5047d.m19709a());
                c6819c.f22609Y.setBackgroundResource(R.drawable.bg_url_bottom_corner);
                c6819c.f22609Y.setTag(R.id.tag_Position, Integer.valueOf(i10));
                m11747I1(c6819c.f22609Y, c5047d.m19714f());
                ChatDialogActivity.this.registerForContextMenu(c6819c.f22609Y);
                c6819c.f22609Y.setVisibility(0);
                if (i9 == 26 || i9 == 28) {
                    layoutParams.addRule(8, c6819c.f22609Y.getId());
                    layoutParams2.addRule(8, c6819c.f22609Y.getId());
                }
            }
            if (i9 == 26 || i9 == 28) {
                c6819c.f22628p.setLayoutParams(layoutParams);
                c6819c.f22623k.setLayoutParams(layoutParams2);
            }
        }

        /* renamed from: Q */
        public void m11763Q(MessageObj messageObj, boolean z8) {
            if (messageObj == null) {
                return;
            }
            m11751K1(messageObj);
            if (z8 && ChatDialogActivity.this.f9806D.containsKey(messageObj.m14777o())) {
                return;
            }
            m11775U(messageObj);
            ChatDialogActivity.this.m11609va(messageObj);
            notifyDataSetChanged();
        }

        /* renamed from: Q0 */
        public boolean m11764Q0(MessageObj messageObj) {
            if (messageObj == null) {
                return false;
            }
            for (MessageObj messageObj2 : this.f10041k) {
                if (messageObj2 != null && TextUtils.equals(messageObj2.m14777o(), messageObj.m14777o())) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: Q1 */
        public final void m11765Q1(final C6819c c6819c, final MessageObj messageObj, final int i9) {
            String strM14747I = messageObj.m14747I("width");
            String strM14747I2 = messageObj.m14747I("height");
            if (strM14747I == null || strM14747I2 == null) {
                return;
            }
            Pair<Integer, Integer> pairM11796b0 = m11796b0(NumberUtils.toInt(strM14747I, -1), NumberUtils.toInt(strM14747I2, -1));
            c6819c.f22616d.getLayoutParams().height = ((Integer) pairM11796b0.second).intValue();
            c6819c.f22616d.getLayoutParams().width = ((Integer) pairM11796b0.first).intValue();
            String strM14747I3 = messageObj.m14747I("packId");
            final String strM14747I4 = messageObj.m14747I("stickerId");
            ChatDialogActivity.this.new AsyncTaskC1942b2(NumberUtils.toInt(strM14747I3, -1), new InterfaceC1962g2() { // from class: y2.x4
                @Override // com.cyberlink.you.activity.chatdialog.ChatDialogActivity.InterfaceC1962g2
                /* renamed from: a */
                public final void mo11877a(StickerPackObj stickerPackObj) {
                    this.f22485a.m11728w1(messageObj, strM14747I4, c6819c, i9, stickerPackObj);
                }
            }).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: R */
        public void m11766R(List<MessageObj> list) {
            ArrayList arrayList = new ArrayList();
            Iterator<MessageObj> it = list.iterator();
            while (it.hasNext()) {
                MessageObj next = it.next();
                if (ChatDialogActivity.this.f9806D.containsKey(next.m14777o())) {
                    it.remove();
                } else {
                    arrayList.add(next);
                }
            }
            m11775U((MessageObj[]) arrayList.toArray(new MessageObj[0]));
            if (!list.isEmpty()) {
                notifyDataSetChanged();
            }
            ChatDialogActivity.this.f9854P.setVisibility(8);
        }

        /* renamed from: R0 */
        public final boolean m11767R0(int i9) {
            MessageObj item = getItem(i9);
            int iM25239a = C6598a.m25239a(item);
            if (C6598a.m25244f(47, iM25239a)) {
                return iM25239a == 48 || iM25239a == 49 || iM25239a == 51 || iM25239a == 52;
            }
            if (MessageObj.MessageType.Event.equals(item.m14778p())) {
                String strM11591q7 = ChatDialogActivity.this.m11591q7(item);
                if ("meeting".equals(strM11591q7) || TtmlNode.END.equals(strM11591q7)) {
                    return false;
                }
            }
            return (item.m14752N() || C2925v.m14598h0(item)) ? false : true;
        }

        /* renamed from: R1 */
        public final void m11768R1(TextView textView) {
            if (textView.getText() instanceof SpannableString) {
                SpannableString spannableString = (SpannableString) textView.getText();
                for (URLSpan uRLSpan : (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class)) {
                    if (uRLSpan instanceof CustomURLSpan) {
                        CustomURLSpan customURLSpan = (CustomURLSpan) uRLSpan;
                        if (ChatDialogMode.NORMAL.equals(ChatDialogActivity.this.f9983x0)) {
                            customURLSpan.m5297g(true);
                        } else {
                            customURLSpan.m5297g(false);
                        }
                    }
                }
            }
        }

        /* renamed from: S */
        public void m11769S(List<MessageObj> list) {
            for (MessageObj messageObj : list) {
                if (!ChatDialogActivity.this.f9806D.containsKey(messageObj.m14777o())) {
                    m11772T(messageObj);
                    ChatDialogActivity.this.f9806D.put(messageObj.m14777o(), messageObj);
                    m11758O0(messageObj);
                }
            }
        }

        /* renamed from: S0 */
        public final boolean m11770S0(int i9) {
            MessageObj item = getItem(i9);
            if (m11788Y0(0, item) && (C2925v.m14596g0(item) || C2925v.m14598h0(item))) {
                return false;
            }
            int iM25239a = C6598a.m25239a(item);
            return 3 == iM25239a || 24 == iM25239a || 8 == iM25239a || 29 == iM25239a || 5 == iM25239a || 26 == iM25239a || 7 == iM25239a || 28 == iM25239a;
        }

        /* renamed from: S1 */
        public final void m11771S1(final C6819c c6819c, String str) throws NumberFormatException {
            if (C5170o0.m20170e(str)) {
                return;
            }
            Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(str);
            c6819c.f22611a.setTag(null);
            if (friendM11440A7 != null) {
                m11783W1(c6819c, friendM11440A7);
                return;
            }
            Friend friend = (Friend) ChatDialogActivity.this.f9927j0.get(Long.valueOf(NumberUtils.toLong(str, 0L)));
            if (friend != null) {
                m11783W1(c6819c, friend);
                return;
            }
            final Object obj = new Object();
            c6819c.f22611a.setText("");
            c6819c.f22611a.setTag(obj);
            c6819c.f22613b.setImageResource(R.drawable.pic_default);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("userId", str));
            ChatDialogActivity.this.f9899c0.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.v3
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str2, String str3, String str4, String str5) {
                    this.f22455a.m11732y1(c6819c, obj, str2, str3, str4, str5);
                }
            });
        }

        /* renamed from: T */
        public final void m11772T(MessageObj messageObj) {
            MessageObj messageObjM11816l0 = m11816l0(messageObj.m14788z());
            if (ChatDialogActivity.this.f9806D.containsKey(messageObjM11816l0.m14777o())) {
                return;
            }
            ChatDialogActivity.this.f9806D.put(messageObjM11816l0.m14777o(), messageObj);
            m11758O0(messageObjM11816l0);
        }

        /* renamed from: T0 */
        public final boolean m11773T0(int i9) {
            MessageObj item = getItem(i9);
            if (item == null) {
                return false;
            }
            if ((m11788Y0(0, item) && (C2925v.m14596g0(item) || C2925v.m14598h0(item))) || item.m14752N()) {
                return false;
            }
            switch (C1999r1.f10204b[item.m14778p().ordinal()]) {
            }
            return false;
        }

        /* renamed from: T1 */
        public final void m11774T1(final TextView textView, final ImageView imageView, String str, String str2) throws NumberFormatException {
            if (C5170o0.m20170e(str)) {
                return;
            }
            Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(str);
            textView.setTag(null);
            if (friendM11440A7 != null) {
                m11777U1(textView, imageView, friendM11440A7);
                return;
            }
            Friend friend = (Friend) ChatDialogActivity.this.f9927j0.get(Long.valueOf(NumberUtils.toLong(str, 0L)));
            if (friend != null) {
                m11777U1(textView, imageView, friend);
                return;
            }
            final Object obj = new Object();
            textView.setText(str2);
            textView.setTag(obj);
            imageView.setImageResource(R.drawable.pic_default);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("userId", str));
            ChatDialogActivity.this.f9899c0.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.w3
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str3, String str4, String str5, String str6) {
                    this.f22469a.m11734z1(textView, obj, imageView, str3, str4, str5, str6);
                }
            });
        }

        /* renamed from: U */
        public final void m11775U(MessageObj... messageObjArr) {
            for (MessageObj messageObj : messageObjArr) {
                if (messageObj != null) {
                    m11772T(messageObj);
                    ChatDialogActivity.this.f9806D.put(messageObj.m14777o(), messageObj);
                    m11758O0(messageObj);
                }
            }
            if (messageObjArr.length > 0) {
                ChatDialogActivity.this.f9854P.setVisibility(8);
            }
        }

        /* renamed from: U0 */
        public final boolean m11776U0(int i9) {
            MessageObj item = getItem(i9);
            return (!m11788Y0(0, item) || C2925v.m14596g0(item) || C2925v.m14598h0(item)) ? false : true;
        }

        /* renamed from: U1 */
        public final void m11777U1(TextView textView, ImageView imageView, Friend friend) {
            if (ChatDialogActivity.this.f9927j0.containsKey(Long.valueOf(friend.f13645c))) {
                ChatDialogActivity.this.f9927j0.put(Long.valueOf(friend.f13645c), friend);
            }
            if (friend.m15621b() == null || friend.m15621b().isEmpty()) {
                textView.setText("");
            } else {
                textView.setText(friend.m15621b());
            }
            C6127a.m23469j(ChatDialogActivity.this, imageView, friend);
        }

        /* renamed from: V */
        public void m11778V(List<MessageObj> list) throws JSONException {
            ArrayList arrayList = new ArrayList();
            Iterator<MessageObj> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().m14777o());
            }
            if (C2950b0.m14912k().m15085v() != null) {
                ChatDialogActivity.this.m11489Q6(arrayList);
            } else {
                ChatDialogActivity.this.m11560h7(arrayList);
            }
        }

        /* renamed from: V0 */
        public final boolean m11779V0(int i9) {
            MessageObj item = getItem(i9);
            if (item != null) {
                MessageObj.MessageType messageTypeM14778p = item.m14778p();
                if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_DEVICE)) {
                    if (messageTypeM14778p.equals(MessageObj.MessageType.Photo) || messageTypeM14778p.equals(MessageObj.MessageType.PhotoNote) || messageTypeM14778p.equals(MessageObj.MessageType.Video) || messageTypeM14778p.equals(MessageObj.MessageType.File)) {
                        return true;
                    }
                } else if (messageTypeM14778p.equals(MessageObj.MessageType.Photo) || messageTypeM14778p.equals(MessageObj.MessageType.PhotoNote)) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: V1 */
        public final void m11780V1(final TextView textView, final int i9, String str) throws NumberFormatException {
            if (C5170o0.m20170e(str)) {
                return;
            }
            Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(str);
            if (friendM11440A7 != null) {
                textView.setText(ChatDialogActivity.this.getString(i9, friendM11440A7.m15621b()));
                return;
            }
            final Object obj = new Object();
            textView.setText(ChatDialogActivity.this.getString(i9, ""));
            textView.setTag(obj);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("userId", str));
            ChatDialogActivity.this.f9899c0.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.y4
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str2, String str3, String str4, String str5) {
                    this.f22511a.m11669C1(textView, obj, i9, str2, str3, str4, str5);
                }
            });
        }

        /* renamed from: W */
        public void m11781W(MessageObj messageObj) {
            if (m11764Q0(messageObj)) {
                return;
            }
            this.f10041k.add(messageObj);
            ChatDialogActivity.this.f9806D.put(messageObj.m14777o(), messageObj);
            notifyDataSetChanged();
            ChatDialogActivity.this.f9854P.setVisibility(8);
        }

        /* renamed from: W0 */
        public final boolean m11782W0(MessageObj messageObj) {
            MessageObj.MessageType messageTypeM14778p = messageObj.m14778p();
            return messageTypeM14778p == MessageObj.MessageType.Date || messageTypeM14778p == MessageObj.MessageType.UnreadLine;
        }

        /* renamed from: W1 */
        public final void m11783W1(C6819c c6819c, Friend friend) {
            if (ChatDialogActivity.this.f9927j0.containsKey(Long.valueOf(friend.f13645c))) {
                ChatDialogActivity.this.f9927j0.put(Long.valueOf(friend.f13645c), friend);
            }
            if (friend.f13664v) {
                c6819c.f22611a.setText(ChatDialogActivity.this.getString(R.string.unknown_user_name));
            } else if (friend.m15621b() == null || friend.m15621b().isEmpty()) {
                c6819c.f22611a.setText("");
            } else {
                c6819c.f22611a.setText(friend.m15621b());
            }
            c6819c.f22613b.setTag(R.id.tag_User, friend);
            C6127a.m23469j(ChatDialogActivity.this, c6819c.f22613b, friend);
        }

        /* renamed from: X */
        public void m11784X(List<MessageObj> list) {
            for (MessageObj messageObj : list) {
                if (!m11764Q0(messageObj)) {
                    this.f10041k.add(messageObj);
                }
                if (!ChatDialogActivity.this.f9806D.containsKey(messageObj.m14777o())) {
                    ChatDialogActivity.this.f9806D.put(messageObj.m14777o(), messageObj);
                }
            }
            notifyDataSetChanged();
            ChatDialogActivity.this.f9854P.setVisibility(8);
        }

        /* renamed from: X0 */
        public final boolean m11785X0(int i9) {
            return i9 >= this.f10040j.size() || m11782W0(this.f10040j.get(i9));
        }

        /* renamed from: X1 */
        public final void m11786X1(MessageObj messageObj) {
            if (messageObj == null) {
                return;
            }
            C6196d0.m23692d().m23703m(ChatDialogActivity.this.f9914g, messageObj.m14777o(), new q(ChatDialogActivity.this.m7366I0(), messageObj));
        }

        /* renamed from: Y */
        public void m11787Y(List<MessageObj> list) {
            for (MessageObj messageObj : list) {
                if (!m11764Q0(messageObj)) {
                    this.f10041k.add(messageObj);
                }
                if (!ChatDialogActivity.this.f9806D.containsKey(messageObj.m14777o())) {
                    ChatDialogActivity.this.f9806D.put(messageObj.m14777o(), messageObj);
                }
            }
        }

        /* renamed from: Y0 */
        public final boolean m11788Y0(int i9, MessageObj messageObj) {
            return C6598a.m25244f(i9, C6598a.m25239a(messageObj));
        }

        /* renamed from: Y1 */
        public final void m11789Y1(C6819c c6819c, int i9) {
            int headerViewsCount = ChatDialogActivity.this.f9946o.getHeaderViewsCount();
            if (c6819c.f22596L != null) {
                if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.NORMAL)) {
                    c6819c.f22596L.setVisibility(8);
                } else if (!isEnabled(i9)) {
                    c6819c.f22596L.setVisibility(4);
                } else {
                    c6819c.f22596L.setVisibility(0);
                    c6819c.f22596L.setSelected(ChatDialogActivity.this.f9946o.isItemChecked(i9 + headerViewsCount));
                }
            }
        }

        /* renamed from: Z */
        public final void m11790Z(int i9, C6819c c6819c, int i10) {
            TextView textView;
            int iM25422n = this.f10042l.m25422n(i10, m11748J0(i9));
            if (c6819c.f22632t == iM25422n || iM25422n <= 0) {
                return;
            }
            int i11 = Globals.m7388i0().m7519a0() == 3 ? 20 : 0;
            float f9 = ChatDialogActivity.this.getResources().getConfiguration().fontScale;
            if (Float.compare(f9, 0.85f) != 0) {
                if (Float.compare(f9, 1.15f) == 0) {
                    i11 += 15;
                } else if (Float.compare(f9, 1.3f) == 0) {
                    i11 += 35;
                }
            }
            int i12 = iM25422n - i11;
            c6819c.f22632t = i12;
            c6819c.f22620h.setMaxWidth(i12);
            if (21 == i10) {
                c6819c.f22620h.setMaxWidth(c6819c.f22632t - ((int) TypedValue.applyDimension(2, 8.0f, ChatDialogActivity.this.getResources().getDisplayMetrics())));
            }
            if (C6598a.m25243e(i10)) {
                c6819c.f22599O.setMaxWidth(c6819c.f22632t - this.f10042l.m25416h());
            }
            if (C6598a.m25240b(i10)) {
                c6819c.f22620h.setMaxWidth((c6819c.f22632t - this.f10042l.m25416h()) - this.f10042l.m25419k());
            }
            if (C6598a.m25241c(i10)) {
                TextView textView2 = c6819c.f22603S;
                if (textView2 != null) {
                    textView2.setMaxWidth(c6819c.f22632t - this.f10042l.m25416h());
                }
                TextView textView3 = c6819c.f22605U;
                if (textView3 != null) {
                    textView3.setMaxWidth(c6819c.f22632t - this.f10042l.m25416h());
                }
                c6819c.f22620h.setMaxWidth((c6819c.f22632t - this.f10042l.m25420l()) - ((int) TypedValue.applyDimension(2, 8.0f, ChatDialogActivity.this.getResources().getDisplayMetrics())));
            }
            if (!C6598a.m25242d(i10) || (textView = c6819c.f22599O) == null) {
                return;
            }
            textView.setMaxWidth(c6819c.f22632t);
        }

        /* renamed from: Z0 */
        public boolean m11791Z0(int i9) {
            return i9 >= this.f10040j.size();
        }

        /* renamed from: Z1 */
        public final void m11792Z1(int i9, C6819c c6819c, int i10) {
            TextView textView;
            if ((i9 == 0 || i9 == 21 || i9 == 5 || i9 == 26 || i9 == 7 || i9 == 28) && (textView = c6819c.f22620h) != null) {
                textView.setTag(Integer.valueOf(i10));
            }
            if (i9 == 2 || i9 == 23) {
                c6819c.f22619g.setTag(Integer.valueOf(i10));
            }
            if (i9 == 1 || i9 == 22 || i9 == 4 || i9 == 25 || i9 == 11 || i9 == 32) {
                c6819c.f22616d.setTag(Integer.valueOf(i10));
            }
            if (i9 != 3) {
            }
            if (i9 == 5 || i9 == 26 || i9 == 6 || i9 == 27 || i9 == 7 || i9 == 28 || i9 == 8 || i9 == 29 || i9 == 9 || i9 == 30 || i9 == 10 || i9 == 31 || i9 == 37 || i9 == 13 || i9 == 14 || i9 == 38 || i9 == 34) {
                c6819c.f22638z.setTag(Integer.valueOf(i10));
            }
            if (44 == i9 || 19 == i9) {
                c6819c.f22627o.setTag(R.id.tag_Position, Integer.valueOf(i10));
            }
            if (i9 == 20 || i9 == 46) {
                TextView textView2 = c6819c.f22620h;
                if (textView2 != null) {
                    textView2.setTag(Integer.valueOf(i10));
                }
                View view = c6819c.f22627o;
                if (view != null) {
                    view.setTag(Integer.valueOf(i10));
                }
            }
        }

        /* renamed from: a0 */
        public final int m11793a0(MessageObj messageObj) {
            if (this.f10040j.isEmpty()) {
                return -1;
            }
            int i9 = 0;
            if (messageObj.m14788z().before(this.f10040j.get(0).m14788z())) {
                return 0;
            }
            if (messageObj.m14788z().after(this.f10040j.get(r2.size() - 1).m14788z())) {
                return -1;
            }
            int size = this.f10040j.size() - 1;
            while (i9 <= size) {
                int i10 = (i9 + size) >>> 1;
                int iM14738i = MessageObj.m14738i(this.f10040j.get(i10), messageObj);
                if (iM14738i < 0) {
                    i9 = i10 + 1;
                } else {
                    if (iM14738i <= 0) {
                        return i10;
                    }
                    size = i10 - 1;
                }
            }
            return i9;
        }

        /* renamed from: a1 */
        public final boolean m11794a1(Integer num) {
            Friend friendM15003C;
            return (num == null || (friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(num))) == null || !friendM15003C.f13664v) ? false : true;
        }

        /* renamed from: a2 */
        public final void m11795a2(C6819c c6819c, int i9) {
            MessageObj item = getItem(i9);
            new o(item.m14745G(), c6819c, i9, item).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: b0 */
        public final Pair<Integer, Integer> m11796b0(int i9, int i10) {
            if (i10 <= i9) {
                return Pair.create(Integer.valueOf(Math.round(i9 * (this.f10034d / i10))), Integer.valueOf(this.f10034d));
            }
            int i11 = this.f10034d;
            return Pair.create(Integer.valueOf(i11), Integer.valueOf(Math.round(i10 * (i11 / i9))));
        }

        /* renamed from: b2 */
        public final void m11797b2(C6819c c6819c, MessageObj messageObj, String str) throws NumberFormatException {
            c6819c.f22634v.setVisibility(0);
            try {
                int i9 = Integer.parseInt(new JSONObject(messageObj.m14779q()).getString("numberDelete"));
                if (i9 == 0) {
                    return;
                }
                String str2 = String.format(ChatDialogActivity.this.m11582n7().getResources().getQuantityString(R.plurals.event_group_files_delete, i9), str, Integer.valueOf(i9));
                c6819c.f22634v.setText(CLUtility.m16430D2(messageObj.m14788z()) + "\n" + str2);
                C5169o.m20158c(c6819c.f22634v);
            } catch (JSONException e9) {
                Log.d("ChatDialogActivity", Log.getStackTraceString(e9));
            }
        }

        /* renamed from: c0 */
        public final boolean m11798c0(int i9) {
            return i9 == 3 || i9 == 8 || i9 == 24 || i9 == 29;
        }

        /* renamed from: c2 */
        public final void m11799c2(C6819c c6819c, int i9) {
            MessageObj item = getItem(i9);
            new m(item.m14745G(), c6819c, i9, item).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: d0 */
        public final boolean m11800d0(C5047d c5047d) {
            int i9 = c5047d.m19710b() != null ? 1 : 0;
            if (!TextUtils.isEmpty(c5047d.m19713e())) {
                i9 += 2;
            }
            if (!TextUtils.isEmpty(c5047d.m19709a())) {
                i9++;
            }
            return i9 >= 2;
        }

        /* renamed from: d2 */
        public final void m11801d2(C6819c c6819c, MessageObj messageObj, String str) throws JSONException, NumberFormatException {
            c6819c.f22634v.setVisibility(0);
            try {
                JSONObject jSONObject = new JSONObject(messageObj.m14779q());
                String string = jSONObject.getString("albumName");
                int i9 = Integer.parseInt(jSONObject.getString("numberDelete"));
                if (i9 == 0) {
                    return;
                }
                String str2 = String.format(ChatDialogActivity.this.m11582n7().getResources().getQuantityString(R.plurals.event_group_photos_delete, i9), str, Integer.valueOf(i9), string);
                c6819c.f22634v.setText(CLUtility.m16430D2(messageObj.m14788z()) + "\n" + str2);
                C5169o.m20158c(c6819c.f22634v);
            } catch (JSONException e9) {
                Log.d("ChatDialogActivity", Log.getStackTraceString(e9));
            }
        }

        /* renamed from: e0 */
        public void m11802e0() {
            setNotifyOnChange(false);
            clear();
            setNotifyOnChange(true);
        }

        /* renamed from: e2 */
        public final void m11803e2(int i9, C6819c c6819c, int i10) {
            MessageObj item = getItem(i10);
            new n(item.m14745G(), c6819c, i10, item).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }

        /* renamed from: f0 */
        public void m11804f0() {
            this.f10040j.clear();
            this.f10041k.clear();
            ChatDialogActivity.this.f9806D.clear();
        }

        /* renamed from: f2 */
        public final void m11805f2(C6819c c6819c, MessageObj messageObj, String str) throws NumberFormatException {
            c6819c.f22634v.setVisibility(0);
            try {
                int i9 = Integer.parseInt(new JSONObject(messageObj.m14779q()).getString("numberDelete"));
                if (i9 == 0) {
                    return;
                }
                String str2 = String.format(ChatDialogActivity.this.m11582n7().getResources().getQuantityString(R.plurals.event_group_videos_delete, i9), str, Integer.valueOf(i9));
                c6819c.f22634v.setText(CLUtility.m16430D2(messageObj.m14788z()) + "\n" + str2);
                C5169o.m20158c(c6819c.f22634v);
            } catch (JSONException e9) {
                Log.d("ChatDialogActivity", Log.getStackTraceString(e9));
            }
        }

        /* renamed from: g0 */
        public final Integer m11806g0(String str) {
            if (C5170o0.m20170e(str)) {
                return null;
            }
            return Integer.valueOf(str);
        }

        /* renamed from: g2 */
        public final void m11807g2(Friend friend) {
            if (friend == null || !friend.f13664v) {
                return;
            }
            friend.m15625f(ChatDialogActivity.this.getString(R.string.unknown_user_name));
            friend.m15624e(ChatDialogActivity.this.getString(R.string.unknown_user_name));
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.f10040j.size() + this.f10041k.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i9) {
            return 0L;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i9) {
            List<MessageObj> list = this.f10040j;
            if (list == null) {
                Log.e("ChatDialogActivity", "list is null");
                return 47;
            }
            if (i9 >= list.size() && i9 < getCount()) {
                return C6598a.m25239a(this.f10041k.get(i9 - this.f10040j.size()));
            }
            if (i9 <= getCount()) {
                return C6598a.m25239a(this.f10040j.get(i9));
            }
            Log.e("ChatDialogActivity", "position " + i9 + " >= size " + this.f10040j.size());
            return 47;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) throws JSONException, Resources.NotFoundException, NumberFormatException, IOException {
            C6819c c6819c;
            View view2;
            String str;
            View viewM25460p;
            MessageObj item = getItem(i9);
            int itemViewType = getItemViewType(i9);
            if (view == null) {
                LayoutInflater layoutInflater = (LayoutInflater) ChatDialogActivity.this.m11582n7().getSystemService("layout_inflater");
                c6819c = new C6819c();
                if (itemViewType == 0) {
                    viewM25460p = C6822f.m25461q(c6819c, layoutInflater, viewGroup);
                    c6819c.f22620h.setOnClickListener(this.f10044n);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22620h);
                } else if (itemViewType == 20) {
                    viewM25460p = C6822f.m25451g(c6819c, layoutInflater, viewGroup);
                    c6819c.f22620h.setOnClickListener(this.f10044n);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22627o);
                } else if (itemViewType == 1 || itemViewType == 11) {
                    viewM25460p = C6822f.m25460p(c6819c, layoutInflater, viewGroup);
                    c6819c.f22616d.setOnClickListener(this.f10056z);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22616d);
                } else if (itemViewType == 2) {
                    viewM25460p = C6822f.m25446b(c6819c, layoutInflater, viewGroup);
                    c6819c.f22619g.setOnClickListener(this.f10056z);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22619g);
                } else if (itemViewType == 4) {
                    viewM25460p = C6822f.m25445a(c6819c, layoutInflater, viewGroup);
                    c6819c.f22616d.setOnClickListener(this.f10056z);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22616d);
                } else if (itemViewType == 3) {
                    viewM25460p = C6822f.m25455k(c6819c, layoutInflater, viewGroup);
                    c6819c.f22615c.setOnClickListener(this.f10050t);
                    c6819c.f22627o.setOnClickListener(m11743G1(c6819c.f22615c));
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                } else if (itemViewType == 8) {
                    viewM25460p = C6822f.m25456l(c6819c, layoutInflater, viewGroup);
                    c6819c.f22615c.setOnClickListener(this.f10050t);
                    c6819c.f22638z.setOnClickListener(this.f10027B);
                    C5169o.m20161f(c6819c.f22620h);
                    String strM14747I = item.m14747I("mediaNotes");
                    if (strM14747I == null || strM14747I.isEmpty()) {
                        c6819c.f22624l.setMax(200);
                    } else {
                        c6819c.f22624l.setMax(300);
                    }
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22636x);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 9) {
                    viewM25460p = C6822f.m25447c(c6819c, layoutInflater, viewGroup);
                    c6819c.f22638z.setOnClickListener(ChatDialogActivity.this.f9805C2);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 6) {
                    viewM25460p = C6822f.m25453i(c6819c, layoutInflater, viewGroup);
                    c6819c.f22638z.setOnClickListener(this.f10051u);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 5 || itemViewType == 7) {
                    viewM25460p = C6822f.m25454j(c6819c, layoutInflater, viewGroup);
                    int i10 = c6819c.f22632t;
                    if (i10 != 0) {
                        c6819c.f22620h.setMaxWidth(i10);
                    }
                    c6819c.f22638z.setOnClickListener(this.f10026A);
                    C5169o.m20157b(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 10) {
                    viewM25460p = C6822f.m25462r(c6819c, layoutInflater, viewGroup);
                    c6819c.f22638z.setOnClickListener(this.f10028C);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 12) {
                    viewM25460p = C6822f.m25463s(c6819c, layoutInflater, viewGroup);
                    c6819c.f22615c.setOnClickListener(this.f10049s);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                } else if (itemViewType == 14) {
                    viewM25460p = C6822f.m25452h(c6819c, layoutInflater, viewGroup);
                    c6819c.f22638z.setOnClickListener(ChatDialogActivity.this.f9940m1);
                    c6819c.f22615c.setOnClickListener(ChatDialogActivity.this.f9940m1);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                } else if (itemViewType == 13) {
                    viewM25460p = C6822f.m25450f(c6819c, layoutInflater, viewGroup);
                    c6819c.f22638z.setOnClickListener(this.f10052v);
                    C5169o.m20161f(c6819c.f22620h);
                    C5169o.m20161f(c6819c.f22631s);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 15) {
                    viewM25460p = C6822f.m25448d(c6819c, layoutInflater, viewGroup);
                    String strM14747I2 = item.m14747I("callType");
                    if (strM14747I2.equals(MimeTypes.BASE_TYPE_VIDEO) || strM14747I2.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                        c6819c.f22638z.setOnClickListener(this.f10029D);
                    } else {
                        c6819c.f22638z.setOnClickListener(null);
                    }
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 16) {
                    viewM25460p = C6822f.m25457m(c6819c, layoutInflater, viewGroup);
                    C5169o.m20161f(c6819c.f22620h);
                    C5169o.m20161f((TextView) viewM25460p.findViewById(R.id.textViewVote));
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                    c6819c.f22638z.setOnClickListener(this.f10030E);
                } else if (itemViewType == 17) {
                    viewM25460p = C6822f.m25458n(c6819c, layoutInflater, viewGroup);
                    C5169o.m20161f(c6819c.f22620h);
                    C5169o.m20161f(c6819c.f22599O);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                    c6819c.f22638z.setOnClickListener(this.f10030E);
                } else if (itemViewType == 18) {
                    viewM25460p = C6822f.m25459o(c6819c, layoutInflater, viewGroup);
                    C5169o.m20161f(c6819c.f22620h);
                    c6819c.f22638z.setOnClickListener(this.f10055y);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 19) {
                    viewM25460p = C6822f.m25449e(c6819c, layoutInflater, viewGroup);
                    c6819c.f22615c.setOnClickListener(ChatDialogActivity.this.f9936l1);
                    c6819c.f22627o.setOnClickListener(m11743G1(c6819c.f22615c));
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22627o);
                } else if (itemViewType == 21) {
                    viewM25460p = C6821e.m25442t(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22620h);
                    c6819c.f22620h.setOnClickListener(this.f10044n);
                    c6819c.f22620h.setOnTouchListener(this.f10045o);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                } else if (itemViewType == 46) {
                    viewM25460p = C6821e.m25430h(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22627o);
                    c6819c.f22620h.setOnClickListener(this.f10044n);
                    c6819c.f22620h.setOnTouchListener(this.f10045o);
                    c6819c.f22627o.setOnTouchListener(this.f10045o);
                } else if (itemViewType == 22 || itemViewType == 32) {
                    viewM25460p = C6821e.m25441s(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22616d.setOnClickListener(this.f10056z);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22616d);
                } else if (itemViewType == 23) {
                    viewM25460p = C6821e.m25425c(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22619g.setOnClickListener(this.f10056z);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22619g);
                } else if (itemViewType == 25) {
                    viewM25460p = C6821e.m25424b(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22616d.setOnClickListener(this.f10056z);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22616d);
                } else if (itemViewType == 24) {
                    viewM25460p = C6821e.m25435m(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22615c.setOnClickListener(this.f10050t);
                    c6819c.f22627o.setOnClickListener(m11743G1(c6819c.f22615c));
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                } else if (itemViewType == 30) {
                    viewM25460p = C6821e.m25426d(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22638z.setOnClickListener(ChatDialogActivity.this.f9805C2);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 29) {
                    viewM25460p = C6821e.m25436n(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22615c.setOnClickListener(this.f10050t);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    c6819c.f22638z.setOnClickListener(this.f10027B);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22636x);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 27) {
                    viewM25460p = C6821e.m25432j(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22638z.setOnClickListener(this.f10051u);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 26 || itemViewType == 28) {
                    viewM25460p = C6821e.m25434l(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22638z.setOnClickListener(this.f10026A);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                    int i11 = c6819c.f22632t;
                    if (i11 != 0) {
                        c6819c.f22620h.setMaxWidth(i11);
                    }
                    C5169o.m20157b(c6819c.f22620h);
                } else if (itemViewType == 31) {
                    viewM25460p = C6821e.m25443u(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22638z.setOnClickListener(this.f10028C);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 34) {
                    viewM25460p = layoutInflater.inflate(R.layout.view_item_recv_photo_note_msg, viewGroup, false);
                    ImageView imageView = (ImageView) viewM25460p.findViewById(R.id.ChatMessageAvatar);
                    c6819c.f22613b = imageView;
                    imageView.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    TextView textView = (TextView) viewM25460p.findViewById(R.id.ChatMessageContentTextView);
                    c6819c.f22620h = textView;
                    textView.setMaxLines(Integer.MAX_VALUE);
                    c6819c.f22611a = (TextView) viewM25460p.findViewById(R.id.ChatMessageNameTextView);
                    ImageView imageView2 = (ImageView) viewM25460p.findViewById(R.id.ChatMessageContentPhotoView);
                    c6819c.f22615c = imageView2;
                    imageView2.setOnClickListener(this.f10050t);
                    c6819c.f22623k = (TextView) viewM25460p.findViewById(R.id.ChatMessageTimeTextView);
                    c6819c.f22587C = (TextView) viewM25460p.findViewById(R.id.VoiceTimeTextView);
                    c6819c.f22636x = (RelativeLayout) viewM25460p.findViewById(R.id.ChatMessageVoicePlayLayout);
                    c6819c.f22637y = (RelativeLayout) viewM25460p.findViewById(R.id.ChatMessageTextLayout);
                    c6819c.f22592H = (TextView) viewM25460p.findViewById(R.id.commentStringText);
                    c6819c.f22591G = (RelativeLayout) viewM25460p.findViewById(R.id.ChatPhotoCommentCntView);
                    c6819c.f22627o = c6819c.f22615c;
                    SelfDestructView selfDestructView = (SelfDestructView) viewM25460p.findViewById(R.id.selfDestructImage);
                    c6819c.f22625m = selfDestructView;
                    selfDestructView.setOnClickListener(this.f10043m);
                    c6819c.f22628p = (TextView) viewM25460p.findViewById(R.id.ttl);
                    c6819c.f22626n = (ImageView) viewM25460p.findViewById(R.id.explode);
                    c6819c.f22595K = viewM25460p.findViewById(R.id.downloadProgressbar);
                    RelativeLayout relativeLayout = (RelativeLayout) viewM25460p.findViewById(R.id.ChatMessageClickLayout);
                    c6819c.f22638z = relativeLayout;
                    relativeLayout.setOnClickListener(this.f10027B);
                    c6819c.f22596L = viewM25460p.findViewById(R.id.itemCheckBox);
                    C5169o.m20161f(c6819c.f22620h);
                } else if (itemViewType == 35) {
                    viewM25460p = layoutInflater.inflate(R.layout.view_item_recv_u_advertisement_msg, viewGroup, false);
                    c6819c.f22588D = (TextView) viewM25460p.findViewById(R.id.ChatMessageAdTitle);
                    c6819c.f22633u = (TextView) viewM25460p.findViewById(R.id.ChatMessageDateInfo);
                    c6819c.f22613b = (ImageView) viewM25460p.findViewById(R.id.ChatMessageAvatar);
                    c6819c.f22611a = (TextView) viewM25460p.findViewById(R.id.ChatMessageNameTextView);
                    c6819c.f22615c = (ImageView) viewM25460p.findViewById(R.id.ChatMessageAdImage);
                    c6819c.f22620h = (TextView) viewM25460p.findViewById(R.id.ChatMessageContentTextView);
                    c6819c.f22597M = (TextView) viewM25460p.findViewById(R.id.update_text);
                    c6819c.f22627o = viewM25460p.findViewById(R.id.contentLayout);
                    c6819c.f22625m = (SelfDestructView) viewM25460p.findViewById(R.id.selfDestructImage);
                    c6819c.f22623k = (TextView) viewM25460p.findViewById(R.id.ChatMessageTimeTextView);
                    c6819c.f22628p = (TextView) viewM25460p.findViewById(R.id.ttl);
                    c6819c.f22626n = (ImageView) viewM25460p.findViewById(R.id.explode);
                    c6819c.f22596L = viewM25460p.findViewById(R.id.itemCheckBox);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22627o);
                } else if (itemViewType == 36) {
                    viewM25460p = layoutInflater.inflate(R.layout.view_item_recv_u_get_it_msg, viewGroup, false);
                    c6819c.f22613b = (ImageView) viewM25460p.findViewById(R.id.ChatMessageAvatar);
                    c6819c.f22611a = (TextView) viewM25460p.findViewById(R.id.ChatMessageNameTextView);
                    c6819c.f22620h = (TextView) viewM25460p.findViewById(R.id.ChatMessageContentTextView);
                    c6819c.f22623k = (TextView) viewM25460p.findViewById(R.id.ChatMessageTimeTextView);
                    c6819c.f22597M = (TextView) viewM25460p.findViewById(R.id.get_it);
                    c6819c.f22625m = (SelfDestructView) viewM25460p.findViewById(R.id.selfDestructImage);
                    c6819c.f22628p = (TextView) viewM25460p.findViewById(R.id.ttl);
                    c6819c.f22626n = (ImageView) viewM25460p.findViewById(R.id.explode);
                    c6819c.f22596L = viewM25460p.findViewById(R.id.itemCheckBox);
                    c6819c.f22627o = viewM25460p.findViewById(R.id.contentLayout);
                    C5169o.m20161f(c6819c.f22620h);
                } else if (itemViewType == 38) {
                    viewM25460p = C6821e.m25431i(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22615c.setOnClickListener(ChatDialogActivity.this.f9940m1);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    c6819c.f22638z.setOnClickListener(ChatDialogActivity.this.f9940m1);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                } else if (itemViewType == 39) {
                    viewM25460p = C6821e.m25433k(c6819c, layoutInflater, viewGroup);
                    c6819c.f22597M.setOnClickListener(this.f10046p);
                } else if (itemViewType == 40) {
                    viewM25460p = C6821e.m25427e(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    String strM14747I3 = item.m14747I("callType");
                    if (strM14747I3.equals(MimeTypes.BASE_TYPE_VIDEO) || strM14747I3.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                        c6819c.f22638z.setOnClickListener(this.f10029D);
                    } else {
                        c6819c.f22638z.setOnClickListener(null);
                    }
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 41) {
                    viewM25460p = C6821e.m25437o(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    TextView textView2 = (TextView) viewM25460p.findViewById(R.id.ChatMessageContentTextView);
                    c6819c.f22620h = textView2;
                    C5169o.m20161f(textView2);
                    C5169o.m20161f((TextView) viewM25460p.findViewById(R.id.textViewVote));
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                    c6819c.f22638z.setOnClickListener(this.f10030E);
                } else if (itemViewType == 42) {
                    viewM25460p = C6821e.m25438p(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    C5169o.m20161f(c6819c.f22620h);
                    C5169o.m20161f(c6819c.f22599O);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                    c6819c.f22638z.setOnClickListener(this.f10030E);
                } else if (itemViewType == 45) {
                    viewM25460p = C6821e.m25440r(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    C5169o.m20161f(c6819c.f22620h);
                    C5169o.m20161f((TextView) viewM25460p.findViewById(R.id.textViewVote));
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                    c6819c.f22638z.setOnClickListener(this.f10030E);
                } else if (itemViewType == 43) {
                    viewM25460p = C6821e.m25439q(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22638z.setOnClickListener(this.f10055y);
                    C5169o.m20161f(c6819c.f22620h);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else if (itemViewType == 44) {
                    viewM25460p = C6821e.m25428f(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22615c.setOnClickListener(ChatDialogActivity.this.f9936l1);
                    c6819c.f22627o.setOnClickListener(m11743G1(c6819c.f22615c));
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22627o);
                } else if (itemViewType == 47) {
                    viewM25460p = layoutInflater.inflate(R.layout.view_item_date_msg, viewGroup, false);
                    c6819c.f22633u = (TextView) viewM25460p.findViewById(R.id.dateView);
                } else if (itemViewType == 50) {
                    viewM25460p = layoutInflater.inflate(R.layout.view_item_unread_line_msg, viewGroup, false);
                } else if (itemViewType == 48 || itemViewType == 49 || itemViewType == 51 || itemViewType == 52) {
                    viewM25460p = layoutInflater.inflate(R.layout.view_item_event_msg, viewGroup, false);
                    c6819c.f22634v = (TextView) viewM25460p.findViewById(R.id.eventView);
                    c6819c.f22635w = (ImageView) viewM25460p.findViewById(R.id.eventIconView);
                    c6819c.f22634v.setTag(R.id.tag_Position, Integer.valueOf(i9));
                    c6819c.f22596L = viewM25460p.findViewById(R.id.itemCheckBox);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22634v);
                } else if (itemViewType == 33) {
                    viewM25460p = C6821e.m25444v(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22615c.setOnClickListener(this.f10049s);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22615c);
                } else if (itemViewType == 37) {
                    viewM25460p = C6821e.m25429g(c6819c, layoutInflater, viewGroup);
                    c6819c.f22613b.setOnClickListener(ChatDialogActivity.this.f9932k1);
                    c6819c.f22625m.setOnClickListener(this.f10043m);
                    c6819c.f22638z.setOnClickListener(this.f10052v);
                    int i12 = c6819c.f22632t;
                    if (i12 != 0) {
                        c6819c.f22620h.setMaxWidth(i12);
                    }
                    C5169o.m20161f(c6819c.f22620h);
                    C5169o.m20161f(c6819c.f22631s);
                    ChatDialogActivity.this.registerForContextMenu(c6819c.f22638z);
                } else {
                    viewM25460p = view;
                }
                if (viewM25460p != null) {
                    viewM25460p.setTag(c6819c);
                }
                view2 = viewM25460p;
            } else {
                c6819c = (C6819c) view.getTag();
                if (item != null && ChatDialogActivity.this.f9831J0 != null && item.m14777o().equals(ChatDialogActivity.this.f9831J0.m14777o())) {
                    String strM14779q = ChatDialogActivity.this.f9831J0.m14779q();
                    ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                    chatDialogActivity.m11606ua(chatDialogActivity.f9831J0, strM14779q);
                    ChatDialogActivity.this.f9831J0 = null;
                }
                view2 = view;
            }
            m11790Z(i9, c6819c, itemViewType);
            SelfDestructView selfDestructView2 = c6819c.f22625m;
            if (selfDestructView2 != null) {
                selfDestructView2.setMessageId(item.m14777o());
            }
            TextView textView3 = c6819c.f22620h;
            if (textView3 != null) {
                textView3.setTypeface(Typeface.DEFAULT, 0);
            }
            if (itemViewType == 47) {
                try {
                    c6819c.f22633u.setText(CLUtility.m16422B2(item.m14788z()));
                } catch (NullPointerException e9) {
                    ULogUtility.m16670f("ChatDialogActivity", "[getView] catch exception: " + e9);
                }
            } else if (itemViewType == 49) {
                m11799c2(c6819c, i9);
            } else if (itemViewType == 51) {
                m11803e2(itemViewType, c6819c, i9);
            } else if (itemViewType == 52) {
                m11795a2(c6819c, i9);
            } else if (itemViewType == 48) {
                c6819c.f22635w.setVisibility(8);
                c6819c.f22634v.setTag(R.id.tag_Position, Integer.valueOf(i9));
                Integer numM11819n0 = m11819n0(item.m14739A());
                Integer numM11806g0 = m11806g0(item.m14745G());
                if (m11794a1(numM11819n0)) {
                    item.m14757S(ChatDialogActivity.this.getString(R.string.unknown_user_name));
                }
                if (m11794a1(numM11806g0)) {
                    item.m14766b0(ChatDialogActivity.this.getString(R.string.unknown_user_name));
                }
                if (item.m14776n().equals(MessageObj.MemberStatus.MemberLeave)) {
                    c6819c.f22634v.setVisibility(0);
                    String str2 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_member_leave), item.m14779q());
                    c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str2);
                    C5169o.m20158c(c6819c.f22634v);
                } else if (MessageObj.MemberStatus.MemberCreate.equals(item.m14776n())) {
                    if (ChatDialogActivity.this.f9914g != null && !ChatDialogActivity.this.f9914g.m15750g() && numM11806g0 != null) {
                        String str3 = CLUtility.m16430D2(item.m14788z()) + "\n";
                        if ("ChatRoom".equals(ChatDialogActivity.this.f9914g.f13716c)) {
                            if (numM11806g0.equals(numM11819n0)) {
                                str = str3 + String.format(ChatDialogActivity.this.getString(R.string.event_chat_join), item.m14746H());
                            } else {
                                str = str3 + String.format(ChatDialogActivity.this.getString(R.string.event_chat_member_create), item.m14746H(), item.m14779q());
                            }
                        } else if (numM11806g0.equals(numM11819n0)) {
                            str = str3 + String.format(ChatDialogActivity.this.getString(R.string.event_group_join), item.m14746H());
                        } else {
                            str = str3 + String.format(ChatDialogActivity.this.getString(R.string.event_group_member_create), item.m14746H(), item.m14779q());
                        }
                        c6819c.f22634v.setVisibility(0);
                        c6819c.f22634v.setText(str);
                        C5169o.m20158c(c6819c.f22634v);
                    }
                } else if (MessageObj.MemberStatus.MemberCreateV2.equals(item.m14776n())) {
                    if (ChatDialogActivity.this.f9914g != null && !ChatDialogActivity.this.f9914g.m15750g() && numM11806g0 != null) {
                        String str4 = CLUtility.m16430D2(item.m14788z()) + "\n";
                        if ("ChatRoom".equals(ChatDialogActivity.this.f9914g.f13716c)) {
                            if (!numM11806g0.equals(numM11819n0)) {
                                Log.d("ChatDialogActivity", "[newGIEvent][getView] show chat invite event message (V2) / invitee: " + item.m14779q());
                                str4 = str4 + String.format(ChatDialogActivity.this.getString(R.string.event_chat_member_create_v2), item.m14746H(), item.m14779q(), Integer.valueOf(item.m14743E() - 1));
                            }
                        } else if (!numM11806g0.equals(numM11819n0)) {
                            Log.d("ChatDialogActivity", "[newGIEvent][getView] show group invite event message (V2) / invitee: " + item.m14779q());
                            str4 = str4 + String.format(ChatDialogActivity.this.getString(R.string.event_group_member_create_v2), item.m14746H(), item.m14779q(), Integer.valueOf(item.m14743E() - 1));
                        }
                        c6819c.f22634v.setVisibility(0);
                        c6819c.f22634v.setText(str4);
                        C5169o.m20158c(c6819c.f22634v);
                    }
                } else if (item.m14776n().equals(MessageObj.MemberStatus.MemberDeleted)) {
                    if (ChatDialogActivity.this.f9914g.f13716c.equals("ChatRoom")) {
                        c6819c.f22634v.setVisibility(0);
                        String str5 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_chat_member_delete), item.m14746H(), item.m14779q());
                        c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str5);
                    } else if (Group.m15743f(ChatDialogActivity.this.f9914g.f13716c)) {
                        c6819c.f22634v.setVisibility(0);
                        String str6 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_member_delete), item.m14746H(), item.m14779q());
                        c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str6);
                    }
                    C5169o.m20158c(c6819c.f22634v);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.AdminCreate)) {
                    c6819c.f22634v.setVisibility(0);
                    String str7 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_admin_create), item.m14779q());
                    c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str7);
                    C5169o.m20158c(c6819c.f22634v);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.AdminDeleted)) {
                    c6819c.f22634v.setVisibility(0);
                    String str8 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_admin_delete), item.m14746H(), item.m14779q());
                    c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str8);
                    C5169o.m20158c(c6819c.f22634v);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.MediaCreate)) {
                    c6819c.f22634v.setVisibility(0);
                    String str9 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_photo_create), item.m14746H());
                    c6819c.f22634v.setText(CLUtility.m16422B2(item.m14788z()) + "\n" + str9);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.AlbumCreate)) {
                    c6819c.f22634v.setVisibility(0);
                    String str10 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_album_create), item.m14746H());
                    c6819c.f22634v.setText(CLUtility.m16422B2(item.m14788z()) + "\n" + str10);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.AlbumDelete)) {
                    c6819c.f22634v.setVisibility(0);
                    String str11 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_album_delete), item.m14746H());
                    c6819c.f22634v.setText(CLUtility.m16422B2(item.m14788z()) + "\n" + str11 + "\n" + item.m14779q());
                } else if (item.m14776n().equals(MessageObj.MemberStatus.AlbumUpdate)) {
                    c6819c.f22634v.setVisibility(0);
                    String str12 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_album_update), item.m14746H(), item.m14747I("albumName"), item.m14747I("albumRename"));
                    c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str12);
                    C5169o.m20158c(c6819c.f22634v);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.DisplayNameUpdated)) {
                    c6819c.f22634v.setVisibility(0);
                    String str13 = String.format(ChatDialogActivity.this.getResources().getString(R.string.event_group_display_name_update), item.m14746H(), item.m14779q());
                    c6819c.f22634v.setText(CLUtility.m16430D2(item.m14788z()) + "\n" + str13);
                    C5169o.m20158c(c6819c.f22634v);
                } else if (item.m14776n().equals(MessageObj.MemberStatus.NO_MemberStatus) && item.m14747I("callId") != null && (TtmlNode.END.equals(item.m14747I("status")) || TtmlNode.END.equals(item.m14747I("statusV2")))) {
                    String strM14747I4 = item.m14747I("callType");
                    Date dateM20085a = C5157k.m20085a(item.m14747I("startTime"));
                    Date dateM20085a2 = C5157k.m20085a(item.m14747I("endTime"));
                    c6819c.f22634v.setText(ChatDialogActivity.this.getString(R.string.clm_end_message_timestamp, dateM20085a == null ? "" : CLUtility.m16454J2(dateM20085a), dateM20085a2 == null ? "" : CLUtility.m16454J2(dateM20085a2)));
                    c6819c.f22635w.setVisibility(0);
                    if (strM14747I4.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                        c6819c.f22635w.setImageResource(R.drawable.icon_audio_call_start);
                    } else {
                        c6819c.f22635w.setImageResource(R.drawable.icon_video_call_start);
                    }
                    ViewGroup.LayoutParams layoutParams = c6819c.f22635w.getLayoutParams();
                    layoutParams.width = (int) ChatDialogActivity.this.getResources().getDimension(R.dimen.clm_meeting_indicator_width);
                    layoutParams.height = (int) ChatDialogActivity.this.getResources().getDimension(R.dimen.clm_meeting_indicator_height);
                    c6819c.f22635w.setLayoutParams(layoutParams);
                    C5169o.m20158c(c6819c.f22634v);
                } else {
                    c6819c.f22634v.setVisibility(8);
                }
            } else if (itemViewType != 50) {
                m11792Z1(itemViewType, c6819c, i9);
                m11813j2(itemViewType, c6819c, i9);
            }
            m11817l2(itemViewType, view2, i9);
            m11754M0(item, c6819c, itemViewType);
            return view2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 53;
        }

        /* renamed from: h0 */
        public final boolean m11808h0(MessageObj messageObj) {
            if (messageObj != null && messageObj.m14788z() != null) {
                String strM14747I = messageObj.m14747I("callId");
                if (C5170o0.m20170e(strM14747I)) {
                    return false;
                }
                for (MessageObj messageObj2 : C2950b0.m14916o().m15177p(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), messageObj.m14788z().getTime())) {
                    String strM14747I2 = messageObj2.m14747I("callId");
                    if ((TtmlNode.END.equals(messageObj2.m14747I("status")) || TtmlNode.END.equals(messageObj2.m14747I("statusV2"))) && strM14747I.equals(strM14747I2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* JADX WARN: Removed duplicated region for block: B:50:0x0106  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x01d0  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01d2  */
        /* renamed from: h2 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m11809h2(int i9, C6819c c6819c, int i10, List<String> list) {
            int i11;
            int i12;
            int i13;
            boolean z8;
            ChatDialogActivity chatDialogActivity;
            Intent intent;
            MessageObj item = getItem(i10);
            boolean z9 = (item == null || item.m14773k() == null) ? false : true;
            List<C5047d> listM11810i0 = m11810i0(list);
            c6819c.f22607W.setVisibility(8);
            c6819c.f22608X.setVisibility(8);
            c6819c.f22609Y.setVisibility(8);
            c6819c.f22610Z.removeAllViewsInLayout();
            c6819c.f22610Z.setVisibility(8);
            c6819c.f22620h.setMinWidth(0);
            if (z9) {
                i11 = 28;
                i12 = 26;
            } else {
                if (i9 == 0) {
                    c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_r_s2);
                } else if (i9 == 20) {
                    c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_r_s2);
                } else if (i9 == 18) {
                    c6819c.f22638z.setBackgroundResource(R.drawable.chat_bg_r_s2);
                } else if (i9 == 5 || i9 == 7) {
                    c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_r_s2);
                }
                if (i9 == 21) {
                    c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_l_r);
                } else if (i9 == 46) {
                    c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_l_r);
                } else if (i9 == 43) {
                    c6819c.f22638z.setBackgroundResource(R.drawable.chat_bg_l_r);
                } else {
                    i12 = 26;
                    i11 = 28;
                    if (i9 == 26 || i9 == 28) {
                        c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_l_r);
                    }
                }
                i11 = 28;
                i12 = 26;
            }
            if (i9 == i12 || i9 == i11) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) c6819c.f22628p.getLayoutParams();
                layoutParams.addRule(8, c6819c.f22638z.getId());
                c6819c.f22628p.setLayoutParams(layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) c6819c.f22623k.getLayoutParams();
                layoutParams2.addRule(8, c6819c.f22638z.getId());
                c6819c.f22623k.setLayoutParams(layoutParams2);
            }
            m11768R1(c6819c.f22620h);
            if (listM11810i0.isEmpty()) {
                return;
            }
            if (new HashSet(list).size() == 1) {
                i13 = 0;
                C5047d c5047d = listM11810i0.get(0);
                if (!m11800d0(c5047d)) {
                    z8 = false;
                    if (z8) {
                        if (i9 == 0) {
                            if (z9) {
                                c6819c.f22621i.setBackgroundResource(R.drawable.bg_url_right_top_highlight);
                            } else {
                                c6819c.f22621i.setBackgroundResource(R.drawable.bg_url_right_top);
                            }
                        } else if (i9 == 20) {
                            if (z9) {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_url_right_top_highlight);
                            } else {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_url_right_top);
                            }
                        } else if (i9 == 18) {
                            if (z9) {
                                c6819c.f22638z.setBackgroundResource(R.drawable.bg_url_right_top_highlight);
                            } else {
                                c6819c.f22638z.setBackgroundResource(R.drawable.bg_url_right_top);
                            }
                        } else if (i9 == 5 || i9 == 7) {
                            if (z9) {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_url_right_top_highlight);
                            } else {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_url_right_top);
                            }
                        }
                        if (i9 == 21) {
                            if (z9) {
                                c6819c.f22621i.setBackgroundResource(R.drawable.bg_url_left_top_highlight);
                            } else {
                                c6819c.f22621i.setBackgroundResource(R.drawable.bg_url_left_top);
                            }
                        } else if (i9 == 46) {
                            if (z9) {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_url_left_top_highlight);
                            } else {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_url_left_top);
                            }
                        } else if (i9 == 43) {
                            if (z9) {
                                c6819c.f22638z.setBackgroundResource(R.drawable.bg_url_left_top_highlight);
                            } else {
                                c6819c.f22638z.setBackgroundResource(R.drawable.bg_url_left_top);
                            }
                        } else if (i9 == 26 || i9 == 28) {
                            if (z9) {
                                c6819c.f22638z.setBackgroundResource(R.drawable.bg_url_left_top_highlight);
                            } else {
                                c6819c.f22638z.setBackgroundResource(R.drawable.bg_url_left_top);
                            }
                        }
                    }
                    chatDialogActivity = ChatDialogActivity.this;
                    if (chatDialogActivity.m11561h8(chatDialogActivity.f9946o) && !ChatDialogActivity.this.f9866S && ChatDialogActivity.this.f9831J0 == null) {
                        View childAt = ChatDialogActivity.this.f9946o.getChildAt(ChatDialogActivity.this.f9946o.getChildCount() - 1);
                        ChatDialogActivity.this.f9946o.setSelectionFromTop(ChatDialogActivity.this.f9946o.getCount() - 1, -(childAt != null ? i13 : childAt.getHeight()));
                    }
                    if (ChatDialogActivity.this.f9831J0 != null || ChatDialogActivity.this.f9870T || (intent = ChatDialogActivity.this.getIntent()) == null) {
                        return;
                    }
                    String stringExtra = intent.getStringExtra("SearchChat");
                    if (stringExtra != null) {
                        ChatDialogActivity chatDialogActivity2 = ChatDialogActivity.this;
                        chatDialogActivity2.m11606ua(chatDialogActivity2.f9831J0, stringExtra);
                        return;
                    } else {
                        String strM14779q = ChatDialogActivity.this.f9831J0.m14779q();
                        ChatDialogActivity chatDialogActivity3 = ChatDialogActivity.this;
                        chatDialogActivity3.m11606ua(chatDialogActivity3.f9831J0, strM14779q);
                        ChatDialogActivity.this.f9831J0 = null;
                        return;
                    }
                }
                m11762P1(i9, c6819c, i10, c5047d);
            } else {
                i13 = 0;
                m11757N1(i9, c6819c, i10, listM11810i0);
            }
            z8 = true;
            if (z8) {
            }
            chatDialogActivity = ChatDialogActivity.this;
            if (chatDialogActivity.m11561h8(chatDialogActivity.f9946o)) {
                View childAt2 = ChatDialogActivity.this.f9946o.getChildAt(ChatDialogActivity.this.f9946o.getChildCount() - 1);
                ChatDialogActivity.this.f9946o.setSelectionFromTop(ChatDialogActivity.this.f9946o.getCount() - 1, -(childAt2 != null ? i13 : childAt2.getHeight()));
            }
            if (ChatDialogActivity.this.f9831J0 != null) {
            }
        }

        /* renamed from: i0 */
        public final List<C5047d> m11810i0(List<String> list) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                C5047d c5047d = (C5047d) ChatDialogActivity.this.f9843M0.get(str);
                if (c5047d != null) {
                    Bitmap bitmapM19710b = c5047d.m19710b();
                    String strM19713e = c5047d.m19713e();
                    String strM19709a = c5047d.m19709a();
                    if ((bitmapM19710b != null || !TextUtils.isEmpty(strM19713e) || !TextUtils.isEmpty(strM19709a)) && !arrayList.contains(c5047d)) {
                        arrayList.add(c5047d);
                    }
                    if (arrayList.size() == 5) {
                        break;
                    }
                } else {
                    ULogUtility.m16676l("ChatDialogActivity", "[filterPreview] Can not get url from map:" + str);
                }
            }
            return arrayList;
        }

        /* renamed from: i2 */
        public final void m11811i2(final int i9, final C6819c c6819c, final int i10, final List<String> list) {
            final MessageObj item = getItem(i10);
            C5044a.a<String, List<C5047d>> aVar = new C5044a.a() { // from class: y2.g4
                @Override // p094i2.C5044a.a
                /* renamed from: a */
                public final void mo19700a(Object obj, Object obj2) {
                    this.f22254a.m11673E1(c6819c, item, i10, i9, list, (String) obj, (List) obj2);
                }
            };
            c6819c.f22620h.setTag(R.id.tag_messageID, item.m14777o());
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                if (((C5047d) ChatDialogActivity.this.f9843M0.get(str)) == null) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() <= 0) {
                m11809h2(i9, c6819c, i10, list);
            } else {
                m11809h2(i9, c6819c, i10, new ArrayList());
                ChatDialogActivity.this.f9839L0.m19698b(item.m14777o(), arrayList, aVar);
            }
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i9) {
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.NORMAL)) {
                return true;
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.DELETE)) {
                return m11767R0(i9);
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.RECALL)) {
                return m11776U0(i9);
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.FORWARD) || ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.ADD_TO_TODO)) {
                return m11773T0(i9);
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.FORWARD_PHOTO_WITH_COMMENT)) {
                return m11770S0(i9);
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_DEVICE) || ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_ALBUM) || ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_GROUP_ALBUM)) {
                return m11779V0(i9);
            }
            return true;
        }

        /* renamed from: j0 */
        public void m11812j0(List<MessageObj> list) {
            ArrayList<String> arrayList = new ArrayList<>();
            boolean z8 = true;
            for (MessageObj messageObj : list) {
                arrayList.add(messageObj.m14777o());
                if (z8 && !m11798c0(C6598a.m25239a(messageObj))) {
                    z8 = false;
                }
            }
            Intent intent = new Intent(ChatDialogActivity.this, (Class<?>) ShareMediaActivity.class);
            intent.putStringArrayListExtra("sharedMediaMsgIdList", arrayList);
            intent.putExtra("withComment", false);
            if (z8) {
                intent.putExtra("shareType", ShareType.Internal_Media.toString());
                intent.putExtra("share_media_id", m11824s0(list));
            } else {
                intent.putExtra("shareType", ShareType.Forward.toString());
            }
            ChatDialogActivity.this.startActivity(intent);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:1123:0x2310  */
        /* JADX WARN: Removed duplicated region for block: B:1126:0x2330  */
        /* JADX WARN: Removed duplicated region for block: B:1128:0x2334  */
        /* JADX WARN: Removed duplicated region for block: B:1129:0x2344  */
        /* JADX WARN: Removed duplicated region for block: B:1138:0x23ec  */
        /* JADX WARN: Removed duplicated region for block: B:1214:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:1254:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:132:0x035a  */
        /* JADX WARN: Removed duplicated region for block: B:134:0x0368  */
        /* JADX WARN: Removed duplicated region for block: B:142:0x03b3  */
        /* JADX WARN: Removed duplicated region for block: B:425:0x0d20  */
        /* JADX WARN: Removed duplicated region for block: B:551:0x114b  */
        /* JADX WARN: Removed duplicated region for block: B:554:0x116b  */
        /* JADX WARN: Removed duplicated region for block: B:556:0x116f  */
        /* JADX WARN: Removed duplicated region for block: B:557:0x117f  */
        /* JADX WARN: Removed duplicated region for block: B:565:0x122c  */
        /* JADX WARN: Removed duplicated region for block: B:639:0x143a  */
        /* JADX WARN: Removed duplicated region for block: B:647:0x147e  */
        /* JADX WARN: Removed duplicated region for block: B:775:0x1850  */
        /* JADX WARN: Removed duplicated region for block: B:778:0x186f  */
        /* JADX WARN: Removed duplicated region for block: B:779:0x1878  */
        /* JADX WARN: Removed duplicated region for block: B:781:0x1881  */
        /* JADX WARN: Removed duplicated region for block: B:802:0x1934  */
        /* JADX WARN: Removed duplicated region for block: B:818:0x19a0  */
        /* JADX WARN: Removed duplicated region for block: B:819:0x19b1  */
        /* JADX WARN: Removed duplicated region for block: B:825:0x19dd  */
        /* JADX WARN: Removed duplicated region for block: B:826:0x19e7  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x0212  */
        /* JADX WARN: Removed duplicated region for block: B:990:0x1ef1  */
        /* renamed from: j2 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m11813j2(int i9, C6819c c6819c, int i10) throws JSONException, Resources.NotFoundException, NumberFormatException, IOException {
            String strM16454J2;
            String str;
            Object obj;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            int i11;
            int i12;
            int i13;
            int iM15222k;
            int i14;
            long j9;
            int i15;
            int i16;
            int i17;
            int i18;
            int i19;
            String str7;
            Object obj2;
            int color;
            int i20;
            String str8;
            String str9;
            Object obj3;
            String str10;
            Object obj4;
            String str11;
            String str12;
            String str13;
            int i21;
            String str14;
            String str15;
            String str16;
            String str17;
            int i22;
            int i23;
            int i24;
            int i25;
            int iM15222k2;
            int i26;
            TextView textView;
            int i27;
            if (this.f10040j == null || i10 >= getCount()) {
                return;
            }
            MessageObj item = getItem(i10);
            if (item == null) {
                ULogUtility.m16676l("ChatDialogActivity", "[updateMsgContent] messageObj is null");
                return;
            }
            if (!C5170o0.m20170e(item.m14745G()) && m11794a1(m11806g0(item.m14745G()))) {
                item.m14766b0(ChatDialogActivity.this.getString(R.string.unknown_user_name));
            }
            strM16454J2 = "";
            if (C6598a.m25244f(0, i9)) {
                if (C2925v.m14600i0(item)) {
                    if (c6819c.f22622j != null) {
                        str8 = "width";
                        int iMin = Math.min(((int) ChatDialogActivity.this.f9914g.f13728o) - 1, item.m14783u());
                        if (iMin != 0) {
                            if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual")) {
                                c6819c.f22622j.setText(ChatDialogActivity.this.getString(R.string.chat_dialog_read));
                            } else {
                                c6819c.f22622j.setText(String.format(ChatDialogActivity.this.getResources().getString(R.string.chat_dialog_read_by), Integer.valueOf(iMin)));
                            }
                            C5169o.m20164i(c6819c.f22622j);
                        } else {
                            c6819c.f22622j.setText("");
                        }
                    } else {
                        str8 = "width";
                    }
                    ImageView imageView = c6819c.f22593I;
                    if (imageView != null) {
                        imageView.setVisibility(8);
                    }
                    TextView textView2 = c6819c.f22623k;
                    if (textView2 != null) {
                        textView2.setText(CLUtility.m16454J2(item.m14788z()));
                        C5169o.m20164i(c6819c.f22623k);
                    }
                    View view = c6819c.f22594J;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                } else {
                    str8 = "width";
                    if (C2925v.m14596g0(item)) {
                        TextView textView3 = c6819c.f22622j;
                        if (textView3 != null) {
                            textView3.setText("");
                        }
                        ImageView imageView2 = c6819c.f22593I;
                        if (imageView2 != null) {
                            imageView2.setVisibility(0);
                        }
                        TextView textView4 = c6819c.f22623k;
                        if (textView4 != null) {
                            textView4.setText("");
                        }
                        View view2 = c6819c.f22594J;
                        if (view2 != null) {
                            view2.setVisibility(8);
                        }
                    } else if (C2925v.m14598h0(item) || C2925v.m14602j0(item)) {
                        TextView textView5 = c6819c.f22622j;
                        if (textView5 != null) {
                            textView5.setText("");
                        }
                        ImageView imageView3 = c6819c.f22593I;
                        if (imageView3 != null) {
                            imageView3.setVisibility(8);
                        }
                        TextView textView6 = c6819c.f22623k;
                        if (textView6 != null) {
                            textView6.setText("");
                        }
                        if (c6819c.f22594J != null) {
                            if (!C2925v.m14594f0(item) || C2925v.m14602j0(item)) {
                                Date dateM14742D = item.m14742D();
                                if (dateM14742D == null) {
                                    ULogUtility.m16676l("ChatDialogActivity", "[send] tapSendTime is null : " + item.m14777o() + " status : " + item.m14740B());
                                    c6819c.f22594J.setVisibility(0);
                                } else if (new Date().getTime() - dateM14742D.getTime() >= 1000) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[send] over DELAY_UPDATE_UI_PERIOD : ");
                                    sb.append(item.m14777o());
                                    sb.append(" send time : ");
                                    str9 = "duration";
                                    obj3 = MimeTypes.BASE_TYPE_VIDEO;
                                    sb.append(dateM14742D.getTime());
                                    sb.append(" status : ");
                                    sb.append(item.m14740B());
                                    ULogUtility.m16676l("ChatDialogActivity", sb.toString());
                                    c6819c.f22594J.setVisibility(0);
                                } else {
                                    str9 = "duration";
                                    obj3 = MimeTypes.BASE_TYPE_VIDEO;
                                    c6819c.f22594J.setVisibility(8);
                                }
                            } else {
                                c6819c.f22594J.setVisibility(8);
                            }
                        }
                        if (item.m14778p().equals(MessageObj.MessageType.NewVersion)) {
                            if (item.m14741C().equals(MessageObj.TTLStatus.NOT_START)) {
                                c6819c.f22627o.setVisibility(0);
                                c6819c.f22626n.setVisibility(8);
                                c6819c.f22628p.setTextColor(ChatDialogActivity.this.getResources().getColor(R.color.you_color_red));
                                c6819c.f22628p.setVisibility(0);
                                c6819c.f22628p.setText(m11825t0(item.m14741C(), item.m14787y()));
                                str10 = "mediaId";
                                obj4 = obj3;
                                str11 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                                str12 = str9;
                                str13 = str8;
                                i21 = 2;
                                str14 = "height";
                            } else if (item.m14741C().equals(MessageObj.TTLStatus.START)) {
                                c6819c.f22627o.setVisibility(0);
                                c6819c.f22626n.setVisibility(8);
                                c6819c.f22628p.setVisibility(0);
                                Activity activityM11582n7 = ChatDialogActivity.this.m11582n7();
                                TextView textView7 = c6819c.f22628p;
                                View view3 = c6819c.f22627o;
                                ImageView imageView4 = c6819c.f22626n;
                                RelativeLayout relativeLayout = c6819c.f22591G;
                                str11 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                                str10 = "mediaId";
                                str12 = str9;
                                i21 = 2;
                                obj4 = obj3;
                                str13 = str8;
                                str14 = "height";
                                item.m14767c0(activityM11582n7, textView7, view3, imageView4, relativeLayout);
                            } else {
                                str10 = "mediaId";
                                obj4 = obj3;
                                str11 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                                str12 = str9;
                                str13 = str8;
                                i21 = 2;
                                str14 = "height";
                                if (item.m14741C().equals(MessageObj.TTLStatus.END)) {
                                    c6819c.f22627o.setVisibility(8);
                                    c6819c.f22626n.setVisibility(0);
                                } else {
                                    View view4 = c6819c.f22627o;
                                    if (view4 != null) {
                                        view4.setVisibility(0);
                                    }
                                    ImageView imageView5 = c6819c.f22626n;
                                    if (imageView5 != null) {
                                        imageView5.setVisibility(8);
                                    }
                                    TextView textView8 = c6819c.f22628p;
                                    if (textView8 != null) {
                                        textView8.setVisibility(8);
                                    }
                                }
                            }
                            if (item.m14752N()) {
                                View view5 = c6819c.f22629q;
                                if (view5 != null) {
                                    view5.setVisibility(0);
                                }
                                TextView textView9 = c6819c.f22623k;
                                if (textView9 != null) {
                                    textView9.setVisibility(8);
                                }
                                TextView textView10 = c6819c.f22628p;
                                if (textView10 != null) {
                                    textView10.setVisibility(0);
                                    c6819c.f22628p.setTextColor(ChatDialogActivity.this.getResources().getColor(R.color.you_color_normal));
                                    String string = ChatDialogActivity.this.getString(R.string.will_send_at);
                                    Object[] objArr = new Object[i21];
                                    objArr[0] = CLUtility.m16466M2(new Date(item.m14784v()));
                                    objArr[1] = CLUtility.m16454J2(new Date(item.m14784v()));
                                    c6819c.f22628p.setText(String.format(string, objArr));
                                }
                            } else {
                                View view6 = c6819c.f22629q;
                                if (view6 != null) {
                                    view6.setVisibility(8);
                                }
                                TextView textView11 = c6819c.f22623k;
                                if (textView11 != null) {
                                    textView11.setVisibility(0);
                                }
                            }
                        } else {
                            str10 = "mediaId";
                            obj4 = obj3;
                            str11 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                            str12 = str9;
                            str13 = str8;
                            i21 = 2;
                            str14 = "height";
                        }
                        if (i9 != 0) {
                            if (C5050a.m19750c(ChatDialogActivity.this.m11582n7(), item.m14779q())) {
                                ((EmojiconTextView) c6819c.f22620h).setEmojiconSize(this.f10039i);
                            }
                            c6819c.f22620h.setText(item.m14779q());
                            c6819c.f22620h.setOnTouchListener(this.f10045o);
                            if (item.m14753O()) {
                                c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_r_selfd);
                            } else {
                                c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_r_s2);
                            }
                            m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                            return;
                        }
                        if (i9 == 20) {
                            if (C5050a.m19750c(ChatDialogActivity.this.m11582n7(), item.m14779q())) {
                                ((EmojiconTextView) c6819c.f22620h).setEmojiconSize(this.f10039i);
                            }
                            c6819c.f22620h.setText(item.m14779q());
                            c6819c.f22620h.setOnTouchListener(this.f10045o);
                            c6819c.f22627o.setOnTouchListener(this.f10045o);
                            m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                            String strM14775m = item.m14775m();
                            String strM14782t = item.m14782t();
                            if (C5170o0.m20170e(strM14782t)) {
                                ULogUtility.m16670f("ChatDialogActivity", "lastForwarderId or originalSenderId is null : " + item.m14777o());
                            }
                            if (C5170o0.m20170e(strM14775m)) {
                                m11755M1(c6819c.f22603S, strM14782t);
                                return;
                            } else {
                                if (strM14775m.equalsIgnoreCase(strM14782t)) {
                                    m11755M1(c6819c.f22603S, strM14775m);
                                    return;
                                }
                                m11755M1(c6819c.f22603S, strM14775m);
                                m11755M1(c6819c.f22605U, strM14782t);
                                c6819c.f22604T.setVisibility(0);
                                return;
                            }
                        }
                        if (i9 == 14) {
                            if (C2925v.m14598h0(item)) {
                                c6819c.f22624l.setVisibility(0);
                                if (item.m14740B().equals("2") && item.m14744F().equals("3")) {
                                    ProgressBar progressBar = c6819c.f22624l;
                                    progressBar.setProgress(progressBar.getMax());
                                } else {
                                    c6819c.f22624l.setProgress(item.f12959z);
                                }
                                String strM14747I = item.m14747I(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                String strM14747I2 = item.m14747I("address");
                                if (strM14747I == null || strM14747I.equals("")) {
                                    c6819c.f22590F.setVisibility(8);
                                    c6819c.f22589E.setVisibility(8);
                                } else {
                                    c6819c.f22589E.setVisibility(0);
                                    c6819c.f22590F.setVisibility(0);
                                    c6819c.f22588D.setText(strM14747I);
                                    if (C5170o0.m20170e(strM14747I2)) {
                                        c6819c.f22620h.setVisibility(8);
                                    } else {
                                        c6819c.f22620h.setText(strM14747I2);
                                    }
                                    CLUtility.m16543i(c6819c.f22620h);
                                }
                                m11759O1(c6819c, item, false, i9);
                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                                return;
                            }
                            if (item.m14744F().equals("4") || item.m14740B().equals("3")) {
                                c6819c.f22624l.setVisibility(8);
                                m11759O1(c6819c, item, false, i9);
                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                                return;
                            }
                            c6819c.f22624l.setVisibility(8);
                            String strM14779q = item.m14779q();
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                            try {
                                JSONObject jSONObject = new JSONObject(strM14779q);
                                String string2 = jSONObject.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                String string3 = jSONObject.has("address") ? jSONObject.getString("address") : null;
                                if (string2.equals("")) {
                                    c6819c.f22590F.setVisibility(8);
                                    c6819c.f22589E.setVisibility(8);
                                } else {
                                    c6819c.f22589E.setVisibility(0);
                                    c6819c.f22590F.setVisibility(0);
                                    c6819c.f22588D.setText(string2);
                                    if (C5170o0.m20170e(string3)) {
                                        c6819c.f22620h.setVisibility(8);
                                    } else {
                                        c6819c.f22620h.setText(string3);
                                    }
                                    CLUtility.m16543i(c6819c.f22620h);
                                }
                            } catch (Exception unused) {
                                Log.d("ChatDialogActivity", "Parse send location exception");
                            }
                            String strM14747I3 = item.m14747I("snapshotUrl");
                            if (strM14747I3 == null) {
                                BitmapDrawable bitmapDrawable = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.location);
                                int height = bitmapDrawable.getBitmap().getHeight();
                                int width = bitmapDrawable.getBitmap().getWidth();
                                float fMax = this.f10038h / Math.max(height, width);
                                c6819c.f22615c.getLayoutParams().height = (int) (height * fMax);
                                c6819c.f22615c.getLayoutParams().width = (int) (width * fMax);
                                C6136j.m23592l(ChatDialogActivity.this, c6819c.f22615c, R.drawable.location);
                                return;
                            }
                            int iApplyDimension = (int) TypedValue.applyDimension(1, 130.0f, ChatDialogActivity.this.getResources().getDisplayMetrics());
                            int iApplyDimension2 = (int) TypedValue.applyDimension(1, 130.0f, ChatDialogActivity.this.getResources().getDisplayMetrics());
                            if (c6819c.f22615c.getLayoutParams().height != iApplyDimension || c6819c.f22615c.getLayoutParams().width != iApplyDimension2) {
                                c6819c.f22615c.getLayoutParams().height = iApplyDimension;
                                c6819c.f22615c.getLayoutParams().width = iApplyDimension2;
                                if (ChatDialogActivity.this.f9946o.getLastVisiblePosition() == i10) {
                                    ChatDialogActivity.this.m11590pb();
                                }
                            }
                            C6136j.m23606z(ChatDialogActivity.this, c6819c.f22615c, strM14747I3.replace("&amp;", "&"), true);
                            return;
                        }
                        if (i9 == 1) {
                            StickerObj stickerObjM11617y7 = ChatDialogActivity.this.m11617y7(item);
                            if (stickerObjM11617y7 != null) {
                                Pair<Integer, Integer> pairM11796b0 = m11796b0(stickerObjM11617y7.m16289n(), stickerObjM11617y7.m16279d());
                                c6819c.f22616d.getLayoutParams().height = ((Integer) pairM11796b0.second).intValue();
                                c6819c.f22616d.getLayoutParams().width = ((Integer) pairM11796b0.first).intValue();
                                LoadImageUtils.m16638w(getContext(), stickerObjM11617y7, false, c6819c.f22616d, ChatDialogActivity.this.f9866S);
                                return;
                            }
                            return;
                        }
                        if (i9 == 11) {
                            m11765Q1(c6819c, item, i10);
                            return;
                        }
                        if (i9 == i21) {
                            StickerObj stickerObjM11617y72 = ChatDialogActivity.this.m11617y7(item);
                            if (stickerObjM11617y72 != null) {
                                File file = new File(stickerObjM11617y72.m16282g());
                                if (file.getPath().equals("_") || !file.exists()) {
                                    File file2 = new File(CLUtility.m16541h1(stickerObjM11617y72.m16284i()));
                                    if (!file2.exists()) {
                                        file2.mkdir();
                                    }
                                    String str18 = file2 + File.separator + stickerObjM11617y72.m16285j();
                                    stickerObjM11617y72.m16293r(str18);
                                    C2950b0.m14924w().m15281i(stickerObjM11617y72.m16285j(), stickerObjM11617y72);
                                    LoadImageUtils.m16635t(stickerObjM11617y72.m16283h(), c6819c.f22619g, str18);
                                } else {
                                    c6819c.f22619g.setGifImage(file);
                                }
                                if (ChatDialogActivity.this.f9866S) {
                                    c6819c.f22619g.m16301b();
                                    return;
                                } else {
                                    c6819c.f22619g.m16300a();
                                    return;
                                }
                            }
                            return;
                        }
                        if (i9 == 4) {
                            StickerObj stickerObjM11617y73 = ChatDialogActivity.this.m11617y7(item);
                            if (stickerObjM11617y73 != null) {
                                Pair<Integer, Integer> pairM11796b02 = m11796b0(stickerObjM11617y73.m16289n(), stickerObjM11617y73.m16279d());
                                c6819c.f22616d.getLayoutParams().height = ((Integer) pairM11796b02.second).intValue();
                                c6819c.f22616d.getLayoutParams().width = ((Integer) pairM11796b02.first).intValue();
                                LoadImageUtils.m16634s(getContext(), stickerObjM11617y73, c6819c.f22616d, ChatDialogActivity.this.f9866S);
                                return;
                            }
                            return;
                        }
                        if (19 == i9) {
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                            return;
                        }
                        if (i9 == 3) {
                            str15 = str11;
                            str16 = str10;
                            str17 = str13;
                            i22 = 8;
                        } else {
                            if (i9 != 8) {
                                if (i9 == 9) {
                                    RelativeLayout relativeLayout2 = c6819c.f22638z;
                                    if (relativeLayout2 != null) {
                                        relativeLayout2.setTag(Integer.valueOf(i10));
                                    }
                                    if (ChatDialogActivity.this.f9911f0 == null || !ChatDialogActivity.this.f9911f0.m14777o().equals(item.m14777o())) {
                                        c6819c.f22617e.setVisibility(0);
                                        c6819c.f22586B.setVisibility(8);
                                        if (ChatDialogActivity.this.f9903d0 != null && ChatDialogActivity.this.f9903d0.m11986b() == c6819c.f22620h) {
                                            ChatDialogActivity.this.f9903d0.m11987d(null);
                                        }
                                        try {
                                            c6819c.f22620h.setText(CLUtility.m16531f(new JSONObject(item.m14779q()).getString(str12)));
                                        } catch (Exception unused2) {
                                            Log.d("ChatDialogActivity", "Parse audio content duration exception");
                                        }
                                    } else {
                                        c6819c.f22617e.setVisibility(8);
                                        c6819c.f22586B.setVisibility(0);
                                        if (ChatDialogActivity.this.f9903d0 != null) {
                                            ChatDialogActivity.this.f9903d0.m11987d(c6819c.f22620h);
                                        }
                                    }
                                    if (item.m14753O()) {
                                        RelativeLayout relativeLayout3 = c6819c.f22638z;
                                        if (relativeLayout3 != null) {
                                            relativeLayout3.setBackgroundResource(R.drawable.chat_bg_r_selfd);
                                        }
                                    } else {
                                        RelativeLayout relativeLayout4 = c6819c.f22638z;
                                        if (relativeLayout4 != null) {
                                            relativeLayout4.setBackgroundResource(R.drawable.chat_bg_r_s2);
                                        }
                                    }
                                    if (!C2925v.m14598h0(item)) {
                                        c6819c.f22624l.setVisibility(8);
                                        return;
                                    } else {
                                        c6819c.f22624l.setVisibility(0);
                                        c6819c.f22624l.setProgress(item.f12959z);
                                        return;
                                    }
                                }
                                String str19 = str12;
                                if (i9 == 5 || i9 == 7) {
                                    String str20 = str10;
                                    String strM14747I4 = item.m14747I("commentType");
                                    if (strM14747I4 == null || !strM14747I4.equals("Audio")) {
                                        String strM14747I5 = item.m14747I("comment");
                                        if (strM14747I4 == null || !strM14747I4.equals("Doodle")) {
                                            i26 = 8;
                                            c6819c.f22598N.setVisibility(8);
                                        } else {
                                            String strM14747I6 = item.m14747I(TtmlNode.ATTR_TTS_COLOR);
                                            c6819c.f22598N.setVisibility(0);
                                            c6819c.f22598N.setImageResource(C6152i.m23610b(strM14747I6, true));
                                            if (strM14747I5.isEmpty()) {
                                                strM14747I5 = ChatDialogActivity.this.m11582n7().getString(R.string.doodle_comment_default_string) + StringUtils.SPACE;
                                                TextView textView12 = c6819c.f22620h;
                                                textView12.setTypeface(textView12.getTypeface(), i21);
                                            }
                                            i26 = 8;
                                        }
                                        c6819c.f22620h.setText(strM14747I5);
                                        m11811i2(i9, c6819c, i10, CLUtility.m16547j(c6819c.f22620h, 11));
                                        c6819c.f22620h.setVisibility(0);
                                        c6819c.f22585A.setVisibility(i26);
                                    } else {
                                        String strM14747I7 = item.m14747I(str19);
                                        c6819c.f22620h.setVisibility(8);
                                        c6819c.f22598N.setVisibility(8);
                                        c6819c.f22585A.setVisibility(0);
                                        c6819c.f22587C.setText(CLUtility.m16531f(strM14747I7));
                                    }
                                    C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(NumberUtils.toLong(item.m14747I(str20), -1L));
                                    if (strM14747I4 != null && strM14747I4.equals("Doodle")) {
                                        C6136j.m23599s(ChatDialogActivity.this, c6819c.f22615c, item.m14747I("thumbnail"));
                                    } else if (c2973l0M14725v != null) {
                                        MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, c2973l0M14725v, MediaLoader.Option.THUMBNAIL);
                                    } else {
                                        c6819c.f22615c.setImageDrawable(ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed));
                                        C6136j.m23592l(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed);
                                    }
                                    c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                                    return;
                                }
                                if (i9 == 6) {
                                    try {
                                        JSONObject jSONObject2 = new JSONObject(item.m14779q());
                                        String string4 = jSONObject2.getString("albumName");
                                        c6819c.f22588D.setText(String.format(ChatDialogActivity.this.getResources().getString(R.string.message_add_photos_into_album), jSONObject2.getString("numberUpload")));
                                        c6819c.f22620h.setText(string4);
                                        String string5 = jSONObject2.getString(FirebaseAnalytics.Param.CONTENT);
                                        if (TextUtils.isEmpty(string5)) {
                                            BitmapDrawable bitmapDrawable2 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed);
                                            int height2 = bitmapDrawable2.getBitmap().getHeight();
                                            int width2 = bitmapDrawable2.getBitmap().getWidth();
                                            DisplayMetrics displayMetrics = Globals.m7388i0().getResources().getDisplayMetrics();
                                            int i28 = this.f10035e;
                                            float f9 = i28;
                                            int i29 = displayMetrics.widthPixels;
                                            if (i28 / i29 > 0.72f) {
                                                f9 = i29 * 0.72f;
                                            }
                                            float f10 = width2;
                                            float f11 = f9 / f10;
                                            int i30 = (int) (f10 * f11);
                                            float f12 = height2;
                                            int i31 = (int) (f11 * f12);
                                            int i32 = this.f10036f;
                                            if (i31 > i32) {
                                                i30 = (int) (f10 * (i32 / f12));
                                                i31 = i32;
                                            }
                                            c6819c.f22615c.getLayoutParams().height = i31;
                                            c6819c.f22615c.getLayoutParams().width = i30;
                                            C6136j.m23592l(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed);
                                        } else {
                                            int iOptInt = jSONObject2.optInt(str13);
                                            int iOptInt2 = jSONObject2.optInt(str14);
                                            if (iOptInt == 0 || iOptInt2 == 0) {
                                                c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                                c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                            } else {
                                                DisplayMetrics displayMetrics2 = Globals.m7388i0().getResources().getDisplayMetrics();
                                                int i33 = this.f10035e;
                                                float f13 = i33;
                                                int i34 = displayMetrics2.widthPixels;
                                                if (i33 / i34 > 0.72f) {
                                                    f13 = i34 * 0.72f;
                                                }
                                                float f14 = iOptInt;
                                                float f15 = f13 / f14;
                                                int i35 = (int) (f14 * f15);
                                                float f16 = iOptInt2;
                                                int i36 = (int) (f15 * f16);
                                                int i37 = this.f10036f;
                                                if (i36 > i37) {
                                                    i35 = (int) (f14 * (i37 / f16));
                                                    i36 = i37;
                                                }
                                                c6819c.f22615c.getLayoutParams().height = i36;
                                                c6819c.f22615c.getLayoutParams().width = i35;
                                                C2973l0.a aVar = new C2973l0.a();
                                                aVar.f13200d = string5;
                                                MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar, aVar, iOptInt, iOptInt2, 0, 0, 0, 0, 0L), MediaLoader.Option.ORIGINAL_PREFER_CACHE);
                                            }
                                        }
                                        c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                                        return;
                                    } catch (JSONException e9) {
                                        Log.d("ChatDialogActivity", Log.getStackTraceString(e9));
                                        return;
                                    }
                                }
                                if (i9 == 10) {
                                    String strM14747I8 = item.m14747I("userId");
                                    String strM14747I9 = item.m14747I("displayName");
                                    m11774T1(c6819c.f22620h, c6819c.f22615c, strM14747I8, strM14747I9 != null ? strM14747I9 : "");
                                    return;
                                }
                                if (i9 == 12) {
                                    c6819c.f22591G.setVisibility(4);
                                    if (C2925v.m14598h0(item)) {
                                        c6819c.f22624l.setVisibility(0);
                                        c6819c.f22624l.setProgress(item.f12959z);
                                        m11759O1(c6819c, item, false, i9);
                                        c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                                    } else if (item.m14744F().equals("4") || item.m14740B().equals("3")) {
                                        c6819c.f22624l.setVisibility(8);
                                        m11759O1(c6819c, item, false, i9);
                                        c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                                    } else {
                                        c6819c.f22624l.setVisibility(8);
                                        c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                                        if (C2950b0.m14914m().m14725v(NumberUtils.toLong(item.m14747I(str10), -1L)) != null) {
                                            m11759O1(c6819c, item, false, i9);
                                        } else {
                                            BitmapDrawable bitmapDrawable3 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed);
                                            int height3 = bitmapDrawable3.getBitmap().getHeight();
                                            float width3 = bitmapDrawable3.getBitmap().getWidth();
                                            float f17 = this.f10035e / width3;
                                            int i38 = (int) (width3 * f17);
                                            float f18 = height3;
                                            int i39 = (int) (f17 * f18);
                                            int i40 = this.f10036f;
                                            if (i39 > i40) {
                                                i38 = (int) (width3 * (i40 / f18));
                                                i39 = i40;
                                            }
                                            c6819c.f22615c.getLayoutParams().height = i39;
                                            c6819c.f22615c.getLayoutParams().width = i38;
                                            C6136j.m23593m(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                                        }
                                    }
                                    String strM14747I10 = item.m14747I(str19);
                                    if (strM14747I10 != null) {
                                        c6819c.f22630r.setText(CLUtility.m16519c(strM14747I10));
                                        return;
                                    }
                                    return;
                                }
                                if (i9 == 13) {
                                    try {
                                        if (C2925v.m14598h0(item)) {
                                            c6819c.f22624l.setVisibility(0);
                                            c6819c.f22624l.setProgress(item.f12959z);
                                        } else {
                                            c6819c.f22624l.setVisibility(8);
                                        }
                                        JSONObject jSONObject3 = new JSONObject(item.m14779q());
                                        String string6 = jSONObject3.getString("mediaName");
                                        String str21 = ChatDialogActivity.this.getResources().getString(R.string.file_size) + ": " + CLUtility.m16592u0(ChatDialogActivity.this, Long.parseLong(jSONObject3.getString("mediaSize")));
                                        c6819c.f22620h.setText(string6);
                                        c6819c.f22631s.setText(str21);
                                        c6819c.f22618f.setImageResource(CLUtility.m16560m0(string6));
                                        return;
                                    } catch (JSONException e10) {
                                        e10.printStackTrace();
                                        return;
                                    }
                                }
                                if (i9 != 15) {
                                    if (i9 == 16) {
                                        String strM14747I11 = item.m14747I(str11);
                                        if (C5170o0.m20170e(strM14747I11)) {
                                            c6819c.f22620h.setText(ChatDialogActivity.this.getString(R.string.S_replied_to_a_question_in_polls, item.m14746H()));
                                        } else {
                                            c6819c.f22620h.setText(strM14747I11);
                                        }
                                        c6819c.f22638z.setTag(Integer.valueOf(i10));
                                        c6819c.f22612a0.setTextColor(ChatDialogActivity.this.getResources().getColor(!ChatDialogActivity.this.f9914g.f13712K ? R.color.you_color_normal_blue_text : R.color.you_color_normal_gray));
                                        c6819c.f22614b0.setVisibility(!ChatDialogActivity.this.f9914g.f13712K ? 0 : 8);
                                        return;
                                    }
                                    String str22 = str11;
                                    if (i9 == 17) {
                                        String strM14747I12 = item.m14747I("topicDesc");
                                        if (C5170o0.m20170e(strM14747I12)) {
                                            c6819c.f22620h.setText(ChatDialogActivity.this.getString(R.string.poll));
                                        } else {
                                            c6819c.f22620h.setText(strM14747I12);
                                        }
                                        String strM14747I13 = item.m14747I(str22);
                                        if (C5170o0.m20170e(strM14747I13)) {
                                            c6819c.f22599O.setText(ChatDialogActivity.this.getString(R.string.S_replied_to_a_question_in_polls, item.m14746H()));
                                        } else {
                                            c6819c.f22599O.setText(strM14747I13);
                                        }
                                        c6819c.f22638z.setTag(Integer.valueOf(i10));
                                        return;
                                    }
                                    if (i9 == 18) {
                                        if (C5050a.m19750c(ChatDialogActivity.this.m11582n7(), item.m14779q())) {
                                            ((EmojiconTextView) c6819c.f22620h).setEmojiconSize(this.f10039i);
                                        }
                                        String strM14747I14 = item.m14747I("replyMessage");
                                        String strM14747I15 = item.m14747I("replySender");
                                        String strM14747I16 = item.m14747I("replyText");
                                        if ("File".equalsIgnoreCase(item.m14747I("sourceType"))) {
                                            c6819c.f22601Q.setVisibility(8);
                                            c6819c.f22602R.setVisibility(8);
                                            c6819c.f22618f.setImageResource(CLUtility.m16560m0(strM14747I14));
                                            c6819c.f22618f.setVisibility(0);
                                        } else {
                                            c6819c.f22601Q.setVisibility(0);
                                            c6819c.f22602R.setVisibility(0);
                                            c6819c.f22618f.setVisibility(8);
                                        }
                                        c6819c.f22599O.setText(strM14747I14);
                                        c6819c.f22620h.setText(strM14747I16);
                                        m11780V1(c6819c.f22600P, R.string.chat_message_sender_reply_reply_name, strM14747I15);
                                        c6819c.f22638z.setTag(Integer.valueOf(i10));
                                        m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                                        return;
                                    }
                                    return;
                                }
                                TextView textView13 = c6819c.f22622j;
                                if (textView13 != null) {
                                    textView13.setVisibility(8);
                                }
                                c6819c.f22628p.setTextColor(ChatDialogActivity.this.getResources().getColor(R.color.you_color_normal));
                                c6819c.f22628p.setVisibility(0);
                                String strM11591q7 = ChatDialogActivity.this.m11591q7(item);
                                String strM14747I17 = item.m14747I("callType");
                                if ((item.m14740B().equals("0") || item.m14740B().equals("5")) && (textView = c6819c.f22623k) != null) {
                                    textView.setText(CLUtility.m16454J2(item.m14788z()));
                                }
                                Object obj5 = obj4;
                                if (strM14747I17.equals(obj5) || strM14747I17.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                                    if (!strM11591q7.equals("meeting")) {
                                        if (!strM11591q7.equals(TtmlNode.END)) {
                                            if (strM14747I17.equals(obj5)) {
                                                c6819c.f22617e.setImageResource(R.drawable.icon_video_hangup);
                                            } else if (strM14747I17.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                                                c6819c.f22617e.setImageResource(R.drawable.icon_audio_hangup);
                                            }
                                            switch (strM11591q7.hashCode()) {
                                                case -1502783335:
                                                    if (!strM11591q7.equals("unreached")) {
                                                        i27 = -1;
                                                        break;
                                                    } else {
                                                        i27 = 0;
                                                        break;
                                                    }
                                                case -1367724422:
                                                    if (strM11591q7.equals("cancel")) {
                                                        i27 = 1;
                                                        break;
                                                    }
                                                    break;
                                                case -934710369:
                                                    if (strM11591q7.equals("reject")) {
                                                        i27 = i21;
                                                        break;
                                                    }
                                                    break;
                                                case 3035641:
                                                    if (strM11591q7.equals("busy")) {
                                                        i27 = 3;
                                                        break;
                                                    }
                                                    break;
                                            }
                                            switch (i27) {
                                                case 0:
                                                    c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.not_connected));
                                                    C5179r0.m20247b(c6819c.f22628p, 1);
                                                    c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallUnreached");
                                                    break;
                                                case 1:
                                                    c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.canncelled));
                                                    c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallCancel");
                                                    break;
                                                case 2:
                                                case 3:
                                                    c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.busy));
                                                    c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallBusy");
                                                    break;
                                                default:
                                                    c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.missed));
                                                    c6819c.f22617e.setContentDescription("[AID]Chatroom_SendUncall");
                                                    break;
                                            }
                                        } else {
                                            if (strM14747I17.equals(obj5)) {
                                                c6819c.f22617e.setImageResource(R.drawable.icon_video_call_end);
                                            } else {
                                                c6819c.f22617e.setImageResource(R.drawable.icon_audio_call_end);
                                            }
                                            Date dateM20085a = C5157k.m20085a(item.m14747I("startTime"));
                                            Date dateM20085a2 = C5157k.m20085a(item.m14747I("endTime"));
                                            long time = (dateM20085a2 == null || dateM20085a == null) ? 0L : (dateM20085a2.getTime() - dateM20085a.getTime()) / 1000;
                                            j9 = time >= 0 ? time : 0L;
                                            strM16454J2 = dateM20085a2 != null ? CLUtility.m16454J2(dateM20085a2) : "";
                                            String strM20086b = C5157k.m20086b(j9);
                                            if (c6819c.f22623k != null && !C2925v.m14596g0(item)) {
                                                c6819c.f22623k.setText(strM16454J2);
                                            }
                                            c6819c.f22628p.setText(strM20086b);
                                        }
                                    } else {
                                        if (strM14747I17.equals(obj5)) {
                                            c6819c.f22617e.setImageResource(R.drawable.icon_video_call_start);
                                        } else {
                                            c6819c.f22617e.setImageResource(R.drawable.icon_audio_call_start);
                                        }
                                        c6819c.f22628p.setText("");
                                        Date dateM20085a3 = C5157k.m20085a(item.m14747I("startTime"));
                                        if (dateM20085a3 == null) {
                                            dateM20085a3 = item.m14788z();
                                        }
                                        String strM16454J22 = CLUtility.m16454J2(dateM20085a3);
                                        if (c6819c.f22623k != null && !C2925v.m14596g0(item)) {
                                            c6819c.f22623k.setText(strM16454J22);
                                        }
                                        c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.started));
                                        c6819c.f22638z.setContentDescription("[AID]Chatroom_meetingCall");
                                    }
                                } else if (strM11591q7.equals("normal")) {
                                    String strM14747I18 = item.m14747I(str19);
                                    if (strM14747I18 != null && !strM14747I18.isEmpty()) {
                                        strM16454J2 = CLUtility.m16523d(strM14747I18);
                                    }
                                    c6819c.f22628p.setText(strM16454J2);
                                    c6819c.f22617e.setImageResource(R.drawable.icon_call);
                                    c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCall");
                                } else {
                                    c6819c.f22617e.setImageResource(R.drawable.icon_uncall);
                                    if (strM11591q7.equals("cancel")) {
                                        c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.canncelled));
                                        c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallCancel");
                                    } else {
                                        c6819c.f22628p.setText(ChatDialogActivity.this.getString(R.string.missed));
                                        c6819c.f22617e.setContentDescription("[AID]Chatroom_SendUncall");
                                    }
                                }
                                c6819c.f22638z.setTag(Integer.valueOf(i10));
                                return;
                            }
                            i22 = 8;
                            str15 = str11;
                            str16 = str10;
                            str17 = str13;
                        }
                        boolean z8 = i9 == i22;
                        c6819c.f22591G.setVisibility(4);
                        if (C2925v.m14598h0(item)) {
                            c6819c.f22624l.setVisibility(0);
                            if (item.m14740B().equals("2") && item.m14744F().equals("3")) {
                                ProgressBar progressBar2 = c6819c.f22624l;
                                progressBar2.setProgress(progressBar2.getMax());
                            } else {
                                c6819c.f22624l.setProgress(item.f12959z);
                            }
                            m11759O1(c6819c, item, z8, i9);
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                        } else {
                            if (item.m14744F().equals("4") || item.m14740B().equals("3")) {
                                i23 = 8;
                                c6819c.f22624l.setVisibility(8);
                                m11759O1(c6819c, item, z8, i9);
                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                                if (i9 == i23) {
                                    if (item.m14780r().equals(MessageObj.NoteType.Text)) {
                                        c6819c.f22636x.setVisibility(i23);
                                        c6819c.f22620h.setText(item.m14747I(str15));
                                        c6819c.f22637y.setVisibility(0);
                                        return;
                                    } else {
                                        c6819c.f22637y.setVisibility(i23);
                                        c6819c.f22636x.setVisibility(0);
                                        c6819c.f22587C.setText(CLUtility.m16531f(item.m14747I("noteMediaDescription")));
                                        return;
                                    }
                                }
                                return;
                            }
                            c6819c.f22624l.setVisibility(8);
                            long j10 = NumberUtils.toLong(item.m14747I(str16), -1L);
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                            C2973l0 c2973l0M14725v2 = C2950b0.m14914m().m14725v(j10);
                            String strM14747I19 = item.m14747I("imageUrl");
                            if (c2973l0M14725v2 != null && !TextUtils.isEmpty(c2973l0M14725v2.m15147s())) {
                                m11759O1(c6819c, item, z8, i9);
                                int iM15139k = c2973l0M14725v2.m15139k();
                                if (iM15139k > 0) {
                                    c6819c.f22591G.setVisibility(0);
                                    c6819c.f22592H.setText(String.valueOf(iM15139k));
                                } else {
                                    c6819c.f22591G.setVisibility(4);
                                }
                            } else if (TextUtils.isEmpty(strM14747I19)) {
                                BitmapDrawable bitmapDrawable4 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed);
                                int height4 = bitmapDrawable4.getBitmap().getHeight();
                                float width4 = bitmapDrawable4.getBitmap().getWidth();
                                float f19 = this.f10035e / width4;
                                int i41 = (int) (width4 * f19);
                                float f20 = height4;
                                int i42 = (int) (f19 * f20);
                                int i43 = this.f10036f;
                                if (i42 > i43) {
                                    i41 = (int) (width4 * (i43 / f20));
                                    i42 = i43;
                                }
                                c6819c.f22615c.getLayoutParams().height = i42;
                                c6819c.f22615c.getLayoutParams().width = i41;
                                C6136j.m23593m(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                            } else {
                                try {
                                    i24 = Integer.parseInt(item.m14747I(str17));
                                    try {
                                        i25 = Integer.parseInt(item.m14747I(str14));
                                    } catch (NumberFormatException e11) {
                                        e = e11;
                                        Log.d("ChatDialogActivity", "ex:" + e.getMessage());
                                        i25 = 0;
                                        if (i24 != 0) {
                                            c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                            c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                            iM15222k2 = C2950b0.m14920s().m15222k(j10);
                                            if (i9 == 8) {
                                            }
                                            if (iM15222k2 <= 0) {
                                            }
                                            C2973l0.a aVar2 = new C2973l0.a();
                                            aVar2.f13200d = strM14747I19;
                                            MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar2, aVar2, i24, i25, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                                            i23 = 8;
                                        }
                                        if (i9 == i23) {
                                        }
                                    }
                                } catch (NumberFormatException e12) {
                                    e = e12;
                                    i24 = 0;
                                }
                                if (i24 != 0 || i25 == 0) {
                                    c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                    c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                } else {
                                    float f21 = i24;
                                    float f22 = this.f10035e / f21;
                                    int i44 = (int) (f21 * f22);
                                    float f23 = i25;
                                    int i45 = (int) (f22 * f23);
                                    int i46 = this.f10036f;
                                    if (i45 > i46) {
                                        i44 = (int) (f21 * (i46 / f23));
                                        i45 = i46;
                                    }
                                    c6819c.f22615c.getLayoutParams().height = i45;
                                    c6819c.f22615c.getLayoutParams().width = i44;
                                }
                                iM15222k2 = C2950b0.m14920s().m15222k(j10);
                                if (i9 == 8) {
                                    iM15222k2++;
                                }
                                if (iM15222k2 <= 0) {
                                    c6819c.f22591G.setVisibility(0);
                                    c6819c.f22592H.setText(String.valueOf(iM15222k2));
                                } else {
                                    c6819c.f22591G.setVisibility(4);
                                }
                                C2973l0.a aVar22 = new C2973l0.a();
                                aVar22.f13200d = strM14747I19;
                                MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar22, aVar22, i24, i25, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                            }
                        }
                        i23 = 8;
                        if (i9 == i23) {
                        }
                    } else {
                        TextView textView14 = c6819c.f22622j;
                        if (textView14 != null) {
                            textView14.setText("");
                        }
                        ImageView imageView6 = c6819c.f22593I;
                        if (imageView6 != null) {
                            imageView6.setVisibility(8);
                        }
                        TextView textView15 = c6819c.f22623k;
                        if (textView15 != null) {
                            textView15.setText("");
                        }
                        View view7 = c6819c.f22594J;
                        if (view7 != null) {
                            view7.setVisibility(8);
                        }
                    }
                }
                str9 = "duration";
                obj3 = MimeTypes.BASE_TYPE_VIDEO;
                if (item.m14778p().equals(MessageObj.MessageType.NewVersion)) {
                }
                if (i9 != 0) {
                }
            } else {
                if (!C6598a.m25244f(21, i9)) {
                    Log.d("ChatDialogActivity", "viewType " + i9 + " not update");
                    return;
                }
                if (item.m14778p().equals(MessageObj.MessageType.NewVersion)) {
                    str = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                    obj = MimeTypes.BASE_TYPE_VIDEO;
                    str2 = "mediaId";
                    str3 = "width";
                    str4 = "duration";
                } else {
                    String strM14745G = item.m14745G();
                    if (i9 == 40) {
                        try {
                            strM14745G = item.m14747I("callerId").split("@")[0];
                        } catch (Exception e13) {
                            e13.printStackTrace();
                        }
                    }
                    String str23 = strM14745G;
                    ImageView imageView7 = c6819c.f22606V;
                    if (imageView7 != null) {
                        boolean zM14750L = item.m14750L();
                        str7 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                        imageView7.setVisibility(zM14750L ? 0 : 8);
                    } else {
                        str7 = PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION;
                    }
                    c6819c.f22613b.setSelected(item.m14750L());
                    TextView textView16 = c6819c.f22611a;
                    if (item.m14750L()) {
                        Resources resources = ChatDialogActivity.this.getResources();
                        obj2 = MimeTypes.BASE_TYPE_VIDEO;
                        color = resources.getColor(R.color.you_color_broadcaster_name);
                    } else {
                        obj2 = MimeTypes.BASE_TYPE_VIDEO;
                        color = ChatDialogActivity.this.getResources().getColor(R.color.black);
                    }
                    textView16.setTextColor(color);
                    m11771S1(c6819c, str23);
                    SelfDestructView selfDestructView = c6819c.f22625m;
                    if (selfDestructView != null) {
                        selfDestructView.setTag(Integer.valueOf(i10));
                    }
                    if (!item.m14741C().equals(MessageObj.TTLStatus.NOT_START)) {
                        if (item.m14741C().equals(MessageObj.TTLStatus.START)) {
                            c6819c.f22627o.setVisibility(0);
                            c6819c.f22628p.setVisibility(0);
                            c6819c.f22625m.setVisibility(8);
                            String str24 = str7;
                            str5 = "height";
                            obj = obj2;
                            str2 = "mediaId";
                            str3 = "width";
                            str4 = "duration";
                            str = str24;
                            str6 = "address";
                            item.m14767c0(ChatDialogActivity.this.m11582n7(), c6819c.f22628p, c6819c.f22627o, c6819c.f22626n, c6819c.f22591G);
                            c6819c.f22626n.setVisibility(8);
                            RelativeLayout relativeLayout5 = c6819c.f22591G;
                            if (relativeLayout5 != null) {
                                relativeLayout5.setVisibility(0);
                            }
                            if (i9 == 29) {
                                c6819c.f22638z.setVisibility(0);
                            }
                        } else {
                            str2 = "mediaId";
                            str3 = "width";
                            str4 = "duration";
                            str = str7;
                            obj = obj2;
                            str5 = "height";
                            str6 = "address";
                            if (item.m14741C().equals(MessageObj.TTLStatus.END)) {
                                c6819c.f22627o.setVisibility(4);
                                c6819c.f22626n.setVisibility(0);
                                RelativeLayout relativeLayout6 = c6819c.f22591G;
                                if (relativeLayout6 != null) {
                                    relativeLayout6.setVisibility(8);
                                }
                                if (i9 == 29) {
                                    c6819c.f22638z.setVisibility(8);
                                }
                            } else {
                                View view8 = c6819c.f22627o;
                                if (view8 != null) {
                                    view8.setVisibility(0);
                                }
                                TextView textView17 = c6819c.f22628p;
                                if (textView17 != null) {
                                    textView17.setVisibility(8);
                                }
                                SelfDestructView selfDestructView2 = c6819c.f22625m;
                                if (selfDestructView2 != null) {
                                    selfDestructView2.setVisibility(8);
                                }
                                ImageView imageView8 = c6819c.f22626n;
                                if (imageView8 != null) {
                                    imageView8.setVisibility(8);
                                }
                            }
                        }
                        c6819c.f22623k.setText(CLUtility.m16454J2(item.m14788z()));
                        C5169o.m20164i(c6819c.f22623k);
                        if (i9 != 21) {
                            if (C5050a.m19750c(ChatDialogActivity.this.m11582n7(), item.m14779q())) {
                                ((EmojiconTextView) c6819c.f22620h).setEmojiconSize(48);
                            }
                            if (item.m14753O()) {
                                c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_l_selfd);
                            } else {
                                c6819c.f22621i.setBackgroundResource(R.drawable.chat_bg_l_r);
                            }
                            c6819c.f22620h.setText(item.m14779q());
                            m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                            return;
                        }
                        if (i9 == 46) {
                            if (C5050a.m19750c(ChatDialogActivity.this.m11582n7(), item.m14779q())) {
                                ((EmojiconTextView) c6819c.f22620h).setEmojiconSize(48);
                            }
                            c6819c.f22620h.setText(item.m14779q());
                            m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                            String strM14782t2 = item.m14782t();
                            if (C5170o0.m20170e(strM14782t2)) {
                                ULogUtility.m16670f("ChatDialogActivity", "originalSenderId is null : " + item.m14777o());
                            }
                            m11755M1(c6819c.f22605U, strM14782t2);
                            return;
                        }
                        if (i9 == 38) {
                            try {
                                JSONObject jSONObject4 = new JSONObject(item.m14779q());
                                String string7 = jSONObject4.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                String string8 = jSONObject4.has(str6) ? jSONObject4.getString(str6) : null;
                                if (string7.equals("")) {
                                    c6819c.f22590F.setVisibility(8);
                                    c6819c.f22589E.setVisibility(8);
                                } else {
                                    c6819c.f22590F.setVisibility(0);
                                    c6819c.f22588D.setText(string7);
                                    if (C5170o0.m20170e(string8)) {
                                        c6819c.f22620h.setVisibility(8);
                                    } else {
                                        c6819c.f22620h.setText(string8);
                                    }
                                    CLUtility.m16543i(c6819c.f22620h);
                                }
                            } catch (Exception unused3) {
                                Log.d("ChatDialogActivity", "Parse receive location exception");
                            }
                            String strM14747I20 = item.m14747I("snapshotUrl");
                            if (strM14747I20 != null) {
                                C6136j.m23606z(ChatDialogActivity.this, c6819c.f22615c, strM14747I20.replace("&amp;", "&"), true);
                            } else {
                                BitmapDrawable bitmapDrawable5 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.location);
                                int height5 = bitmapDrawable5.getBitmap().getHeight();
                                int width5 = bitmapDrawable5.getBitmap().getWidth();
                                float fMax2 = this.f10037g / Math.max(height5, width5);
                                c6819c.f22615c.getLayoutParams().height = (int) (height5 * fMax2);
                                c6819c.f22615c.getLayoutParams().width = (int) (width5 * fMax2);
                                c6819c.f22615c.setImageDrawable(ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.location));
                                C6136j.m23593m(ChatDialogActivity.this, c6819c.f22615c, R.drawable.location, true);
                            }
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                            return;
                        }
                        if (i9 == 22) {
                            StickerObj stickerObjM11617y74 = ChatDialogActivity.this.m11617y7(item);
                            if (stickerObjM11617y74 != null) {
                                Pair<Integer, Integer> pairM11796b03 = m11796b0(stickerObjM11617y74.m16289n(), stickerObjM11617y74.m16279d());
                                c6819c.f22616d.getLayoutParams().height = ((Integer) pairM11796b03.second).intValue();
                                c6819c.f22616d.getLayoutParams().width = ((Integer) pairM11796b03.first).intValue();
                                LoadImageUtils.m16638w(getContext(), stickerObjM11617y74, false, c6819c.f22616d, ChatDialogActivity.this.f9866S);
                                return;
                            }
                            return;
                        }
                        if (i9 == 32) {
                            m11765Q1(c6819c, item, i10);
                            return;
                        }
                        if (i9 == 23) {
                            StickerObj stickerObjM11617y75 = ChatDialogActivity.this.m11617y7(item);
                            if (stickerObjM11617y75 != null) {
                                File file3 = new File(stickerObjM11617y75.m16282g());
                                if (file3.getPath().equals("_") || !file3.exists()) {
                                    File file4 = new File(CLUtility.m16541h1(stickerObjM11617y75.m16284i()));
                                    if (!file4.exists()) {
                                        file4.mkdir();
                                    }
                                    String str25 = file4 + File.separator + stickerObjM11617y75.m16285j();
                                    stickerObjM11617y75.m16293r(str25);
                                    C2950b0.m14924w().m15281i(stickerObjM11617y75.m16285j(), stickerObjM11617y75);
                                    LoadImageUtils.m16635t(stickerObjM11617y75.m16283h(), c6819c.f22619g, str25);
                                } else {
                                    c6819c.f22619g.setGifImage(file3);
                                }
                                if (ChatDialogActivity.this.f9866S) {
                                    c6819c.f22619g.m16301b();
                                    return;
                                } else {
                                    c6819c.f22619g.m16300a();
                                    return;
                                }
                            }
                            return;
                        }
                        if (i9 == 25) {
                            StickerObj stickerObjM11617y76 = ChatDialogActivity.this.m11617y7(item);
                            if (stickerObjM11617y76 != null) {
                                Pair<Integer, Integer> pairM11796b04 = m11796b0(stickerObjM11617y76.m16289n(), stickerObjM11617y76.m16279d());
                                c6819c.f22616d.getLayoutParams().height = ((Integer) pairM11796b04.second).intValue();
                                c6819c.f22616d.getLayoutParams().width = ((Integer) pairM11796b04.first).intValue();
                                LoadImageUtils.m16634s(getContext(), stickerObjM11617y76, c6819c.f22616d, ChatDialogActivity.this.f9866S);
                                return;
                            }
                            return;
                        }
                        if (44 == i9) {
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(i10));
                            return;
                        }
                        if (i9 == 24 || i9 == 29) {
                            String str26 = str;
                            String str27 = str5;
                            String str28 = str3;
                            c6819c.f22591G.setVisibility(4);
                            long j11 = NumberUtils.toLong(item.m14747I(str2), -1L);
                            if (j11 != -1) {
                                C2973l0 c2973l0M14725v3 = C2950b0.m14914m().m14725v(j11);
                                String strM14747I21 = item.m14747I("imageUrl");
                                if (c2973l0M14725v3 != null && !TextUtils.isEmpty(c2973l0M14725v3.m15147s())) {
                                    int iM15151w = c2973l0M14725v3.m15151w();
                                    int iM15141m = c2973l0M14725v3.m15141m();
                                    if (iM15151w == 0 || iM15141m == 0) {
                                        c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                        c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                        ChatDialogActivity.m11188Rb(c2973l0M14725v3);
                                    } else {
                                        float f24 = iM15151w;
                                        float f25 = this.f10035e / f24;
                                        int i47 = (int) (f24 * f25);
                                        float f26 = iM15141m;
                                        int i48 = (int) (f25 * f26);
                                        int i49 = this.f10036f;
                                        if (i48 > i49) {
                                            i47 = (int) (f24 * (i49 / f26));
                                            i48 = i49;
                                        }
                                        c6819c.f22615c.getLayoutParams().height = i48;
                                        c6819c.f22615c.getLayoutParams().width = i47;
                                    }
                                    int iM15139k2 = c2973l0M14725v3.m15139k();
                                    if (iM15139k2 > 0) {
                                        c6819c.f22591G.setVisibility(0);
                                        c6819c.f22592H.setText(String.valueOf(iM15139k2));
                                    } else {
                                        c6819c.f22591G.setVisibility(4);
                                    }
                                    MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, c2973l0M14725v3, MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                                } else if (TextUtils.isEmpty(strM14747I21)) {
                                    BitmapDrawable bitmapDrawable6 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed);
                                    int height6 = bitmapDrawable6.getBitmap().getHeight();
                                    float width6 = bitmapDrawable6.getBitmap().getWidth();
                                    float f27 = this.f10035e / width6;
                                    int i50 = (int) (width6 * f27);
                                    float f28 = height6;
                                    int i51 = (int) (f27 * f28);
                                    int i52 = this.f10036f;
                                    if (i51 > i52) {
                                        i50 = (int) (width6 * (i52 / f28));
                                        i51 = i52;
                                    }
                                    c6819c.f22615c.getLayoutParams().height = i51;
                                    c6819c.f22615c.getLayoutParams().width = i50;
                                    C6136j.m23593m(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                                } else {
                                    try {
                                        i12 = Integer.parseInt(item.m14747I(str28));
                                        try {
                                            i13 = Integer.parseInt(item.m14747I(str27));
                                        } catch (NumberFormatException e14) {
                                            e = e14;
                                            Log.d("ChatDialogActivity", "ex:" + e.getMessage());
                                            i13 = 0;
                                            if (i12 != 0) {
                                                c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                                c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                                iM15222k = C2950b0.m14920s().m15222k(j11);
                                                if (i9 == 29) {
                                                }
                                                if (iM15222k <= 0) {
                                                }
                                                C2973l0.a aVar3 = new C2973l0.a();
                                                aVar3.f13200d = strM14747I21;
                                                MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar3, aVar3, i12, i13, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                                            }
                                            if (i9 != 29) {
                                            }
                                        }
                                    } catch (NumberFormatException e15) {
                                        e = e15;
                                        i12 = 0;
                                    }
                                    if (i12 != 0 || i13 == 0) {
                                        c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                        c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                    } else {
                                        float f29 = i12;
                                        float f30 = this.f10035e / f29;
                                        int i53 = (int) (f29 * f30);
                                        float f31 = i13;
                                        int i54 = (int) (f30 * f31);
                                        int i55 = this.f10036f;
                                        if (i54 > i55) {
                                            i53 = (int) (f29 * (i55 / f31));
                                            i54 = i55;
                                        }
                                        c6819c.f22615c.getLayoutParams().height = i54;
                                        c6819c.f22615c.getLayoutParams().width = i53;
                                    }
                                    iM15222k = C2950b0.m14920s().m15222k(j11);
                                    if (i9 == 29) {
                                        iM15222k++;
                                    }
                                    if (iM15222k <= 0) {
                                        c6819c.f22591G.setVisibility(0);
                                        c6819c.f22592H.setText(String.valueOf(iM15222k));
                                    } else {
                                        c6819c.f22591G.setVisibility(4);
                                    }
                                    C2973l0.a aVar32 = new C2973l0.a();
                                    aVar32.f13200d = strM14747I21;
                                    MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar32, aVar32, i12, i13, 0, 0, 0, 0, 0L), MediaLoader.Option.ROUNDED_ORIGINAL_PREFER_CACHE);
                                }
                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                            }
                            if (i9 != 29) {
                                if (item.f12925B) {
                                    c6819c.f22595K.setVisibility(0);
                                    i11 = 8;
                                } else {
                                    i11 = 8;
                                    c6819c.f22595K.setVisibility(8);
                                }
                                if (item.m14780r().equals(MessageObj.NoteType.Text)) {
                                    c6819c.f22636x.setVisibility(i11);
                                    c6819c.f22620h.setText(item.m14747I(str26));
                                    c6819c.f22637y.setVisibility(0);
                                    return;
                                } else {
                                    c6819c.f22637y.setVisibility(i11);
                                    c6819c.f22636x.setVisibility(0);
                                    c6819c.f22587C.setText(CLUtility.m16531f(item.m14747I("noteMediaDescription")));
                                    return;
                                }
                            }
                            return;
                        }
                        if (i9 == 30) {
                            RelativeLayout relativeLayout7 = c6819c.f22638z;
                            if (relativeLayout7 != null) {
                                relativeLayout7.setTag(Integer.valueOf(i10));
                            }
                            if (item.f12925B) {
                                c6819c.f22595K.setVisibility(0);
                            } else {
                                c6819c.f22595K.setVisibility(8);
                            }
                            if (ChatDialogActivity.this.f9911f0 == null || !ChatDialogActivity.this.f9911f0.m14777o().equals(item.m14777o())) {
                                c6819c.f22617e.setVisibility(0);
                                c6819c.f22586B.setVisibility(8);
                                if (ChatDialogActivity.this.f9903d0 != null && ChatDialogActivity.this.f9903d0.m11986b() == c6819c.f22620h) {
                                    ChatDialogActivity.this.f9903d0.m11987d(null);
                                }
                                try {
                                    c6819c.f22620h.setText(CLUtility.m16531f(new JSONObject(item.m14779q()).getString(str4)));
                                } catch (Exception unused4) {
                                    Log.d("ChatDialogActivity", "Parse audio content duration exception");
                                }
                            } else {
                                c6819c.f22617e.setVisibility(8);
                                c6819c.f22586B.setVisibility(0);
                                if (ChatDialogActivity.this.f9903d0 != null) {
                                    ChatDialogActivity.this.f9903d0.m11987d(c6819c.f22620h);
                                }
                            }
                            if (!item.m14753O()) {
                                RelativeLayout relativeLayout8 = c6819c.f22638z;
                                if (relativeLayout8 != null) {
                                    relativeLayout8.setBackgroundResource(R.drawable.chat_bg_l_r);
                                    return;
                                }
                                return;
                            }
                            RelativeLayout relativeLayout9 = c6819c.f22638z;
                            if (relativeLayout9 != null) {
                                relativeLayout9.setBackgroundResource(R.drawable.chat_bg_l_selfd);
                            }
                            if (item.m14741C().equals(MessageObj.TTLStatus.NOT_START)) {
                                if (ChatDialogActivity.this.f9911f0 == item) {
                                    c6819c.f22625m.setVisibility(8);
                                    c6819c.f22617e.setVisibility(0);
                                    return;
                                } else {
                                    c6819c.f22617e.setVisibility(8);
                                    c6819c.f22586B.setVisibility(8);
                                    return;
                                }
                            }
                            return;
                        }
                        if (i9 == 26 || i9 == 28) {
                            String str29 = str2;
                            String strM14747I22 = item.m14747I("commentType");
                            if ("Audio".equals(strM14747I22)) {
                                String strM14747I23 = item.m14747I(str4);
                                c6819c.f22620h.setVisibility(8);
                                c6819c.f22598N.setVisibility(8);
                                c6819c.f22585A.setVisibility(0);
                                c6819c.f22587C.setText(CLUtility.m16531f(strM14747I23));
                            } else {
                                String strM14747I24 = item.m14747I("comment");
                                if ("Doodle".equals(strM14747I22)) {
                                    String strM14747I25 = item.m14747I(TtmlNode.ATTR_TTS_COLOR);
                                    c6819c.f22598N.setVisibility(0);
                                    c6819c.f22598N.setImageResource(C6152i.m23610b(strM14747I25, true));
                                    if (strM14747I24.isEmpty()) {
                                        strM14747I24 = ChatDialogActivity.this.m11582n7().getString(R.string.doodle_comment_default_string) + StringUtils.SPACE;
                                        TextView textView18 = c6819c.f22620h;
                                        textView18.setTypeface(textView18.getTypeface(), 2);
                                    }
                                    i14 = 8;
                                } else {
                                    i14 = 8;
                                    c6819c.f22598N.setVisibility(8);
                                }
                                c6819c.f22620h.setText(strM14747I24);
                                m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                                c6819c.f22620h.setVisibility(0);
                                c6819c.f22585A.setVisibility(i14);
                            }
                            C2973l0 c2973l0M14725v4 = C2950b0.m14914m().m14725v(NumberUtils.toLong(item.m14747I(str29), -1L));
                            if (strM14747I22 != null && strM14747I22.equals("Doodle")) {
                                C6136j.m23599s(ChatDialogActivity.this, c6819c.f22615c, item.m14747I("thumbnail"));
                            } else if (c2973l0M14725v4 != null) {
                                MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, c2973l0M14725v4, MediaLoader.Option.THUMBNAIL);
                            } else {
                                c6819c.f22615c.setImageDrawable(ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed));
                                C6136j.m23592l(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed);
                            }
                            c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                            return;
                        }
                        if (i9 == 34) {
                            String strM14748J = item.m14748J(str, FirebaseAnalytics.Param.CONTENT);
                            String strM14748J2 = item.m14748J("image", "src");
                            try {
                                i17 = Integer.parseInt(item.m14748J("image", str3));
                                try {
                                    i18 = Integer.parseInt(item.m14748J("image", str5));
                                } catch (Exception unused5) {
                                    i18 = 0;
                                    if (i17 != 0) {
                                        c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                        c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                    }
                                    C6136j.m23599s(ChatDialogActivity.this, c6819c.f22615c, strM14748J2);
                                    if (item.f12925B) {
                                    }
                                    if (strM14748J != null) {
                                    }
                                    c6819c.f22636x.setVisibility(i19);
                                    c6819c.f22637y.setVisibility(0);
                                    c6819c.f22615c.setTag(Integer.valueOf(this.f10040j.indexOf(item)));
                                    return;
                                }
                            } catch (Exception unused6) {
                                i17 = 0;
                            }
                            if (i17 != 0 || i18 == 0) {
                                c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                c6819c.f22615c.getLayoutParams().width = this.f10032b;
                            } else {
                                float f32 = i17;
                                float f33 = this.f10035e / f32;
                                int i56 = (int) (f32 * f33);
                                float f34 = i18;
                                int i57 = (int) (f33 * f34);
                                int i58 = this.f10036f;
                                if (i57 > i58) {
                                    i56 = (int) (f32 * (i58 / f34));
                                    i57 = i58;
                                }
                                c6819c.f22615c.getLayoutParams().height = i57;
                                c6819c.f22615c.getLayoutParams().width = i56;
                            }
                            C6136j.m23599s(ChatDialogActivity.this, c6819c.f22615c, strM14748J2);
                            if (item.f12925B) {
                                i19 = 8;
                                c6819c.f22595K.setVisibility(8);
                            } else {
                                c6819c.f22595K.setVisibility(0);
                                i19 = 8;
                            }
                            if (strM14748J != null) {
                                c6819c.f22620h.setText(strM14748J);
                            }
                            c6819c.f22636x.setVisibility(i19);
                            c6819c.f22637y.setVisibility(0);
                            c6819c.f22615c.setTag(Integer.valueOf(this.f10040j.indexOf(item)));
                            return;
                        }
                        String str30 = str;
                        String str31 = str5;
                        String str32 = str3;
                        if (i9 == 35) {
                            String strM14747I26 = item.m14747I("type");
                            String strM14747I27 = item.m14747I(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE);
                            String strM14748J3 = item.m14748J(str30, FirebaseAnalytics.Param.CONTENT);
                            String strM14748J4 = item.m14748J("image", "src");
                            String strM14747I28 = item.m14747I("url");
                            String strM14747I29 = item.m14747I("titleOfUrl");
                            try {
                                i15 = Integer.parseInt(item.m14748J("image", str32));
                            } catch (Exception e16) {
                                e = e16;
                                i15 = 0;
                            }
                            try {
                                i16 = Integer.parseInt(item.m14748J("image", str31));
                            } catch (Exception e17) {
                                e = e17;
                                e.printStackTrace();
                                i16 = 0;
                                if (strM14748J4 != null) {
                                    C6136j.m23591k(ChatDialogActivity.this, c6819c.f22615c);
                                    c6819c.f22615c.setVisibility(8);
                                }
                                if (strM14747I27 != null) {
                                    c6819c.f22588D.setText(strM14747I27);
                                    CLUtility.m16543i(c6819c.f22588D);
                                }
                                c6819c.f22633u.setText(CLUtility.m16458K2(item.m14788z()));
                                if (strM14748J3 != null) {
                                    c6819c.f22620h.setText(strM14748J3);
                                    CLUtility.m16543i(c6819c.f22620h);
                                }
                                if (strM14747I29 != null) {
                                    SpannableString spannableString = new SpannableString(strM14747I29);
                                    spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                                    c6819c.f22597M.setText(spannableString);
                                }
                                if (!strM14747I26.equals("type-005")) {
                                }
                                c6819c.f22627o.setTag(R.id.contentView, Integer.valueOf(this.f10040j.indexOf(item)));
                                if (C6818b.m25402m(item)) {
                                }
                            }
                            if (strM14748J4 != null || strM14748J4.isEmpty()) {
                                C6136j.m23591k(ChatDialogActivity.this, c6819c.f22615c);
                                c6819c.f22615c.setVisibility(8);
                            } else {
                                if (i15 == 0 || i16 == 0) {
                                    c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                    c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                } else {
                                    int iM25421m = this.f10042l.m25421m(i9);
                                    c6819c.f22615c.getLayoutParams().height = (int) (i16 * (this.f10042l.m25421m(i9) / i15));
                                    c6819c.f22615c.getLayoutParams().width = iM25421m;
                                }
                                C6136j.m23599s(ChatDialogActivity.this, c6819c.f22615c, strM14748J4);
                            }
                            if (strM14747I27 != null && !strM14747I27.isEmpty()) {
                                c6819c.f22588D.setText(strM14747I27);
                                CLUtility.m16543i(c6819c.f22588D);
                            }
                            c6819c.f22633u.setText(CLUtility.m16458K2(item.m14788z()));
                            if (strM14748J3 != null && !strM14748J3.isEmpty()) {
                                c6819c.f22620h.setText(strM14748J3);
                                CLUtility.m16543i(c6819c.f22620h);
                            }
                            if (strM14747I29 != null && !strM14747I29.isEmpty()) {
                                SpannableString spannableString2 = new SpannableString(strM14747I29);
                                spannableString2.setSpan(new UnderlineSpan(), 0, spannableString2.length(), 0);
                                c6819c.f22597M.setText(spannableString2);
                            }
                            if (!strM14747I26.equals("type-005")) {
                                c6819c.f22627o.setOnClickListener(this.f10047q);
                                c6819c.f22627o.setTag(item.m14777o());
                            } else if (strM14747I28 != null && !strM14747I28.isEmpty()) {
                                c6819c.f22627o.setOnClickListener(this.f10048r);
                                c6819c.f22627o.setTag(strM14747I28);
                            }
                            c6819c.f22627o.setTag(R.id.contentView, Integer.valueOf(this.f10040j.indexOf(item)));
                            if (C6818b.m25402m(item)) {
                                c6819c.f22627o.setBackgroundResource(R.drawable.bg_receive_u_ad);
                                return;
                            } else {
                                c6819c.f22627o.setBackgroundResource(R.drawable.chat_bg_r_s2);
                                return;
                            }
                        }
                        if (i9 == 36) {
                            String strM14748J5 = item.m14748J(str30, FirebaseAnalytics.Param.CONTENT);
                            String strM14747I30 = item.m14747I("url");
                            String strM14747I31 = item.m14747I("titleOfUrl");
                            if (strM14748J5 != null) {
                                c6819c.f22620h.setText(strM14748J5);
                                CLUtility.m16543i(c6819c.f22620h);
                            }
                            if (strM14747I31 != null && !strM14747I31.isEmpty() && strM14747I30 != null) {
                                SpannableString spannableString3 = new SpannableString(strM14747I31);
                                spannableString3.setSpan(new UnderlineSpan(), 0, spannableString3.length(), 0);
                                c6819c.f22597M.setText(spannableString3);
                            }
                            if (strM14747I30 == null || strM14747I30.isEmpty()) {
                                return;
                            }
                            c6819c.f22620h.setOnClickListener(this.f10048r);
                            c6819c.f22620h.setTag(strM14747I30);
                            c6819c.f22627o.setOnClickListener(this.f10048r);
                            c6819c.f22627o.setTag(strM14747I30);
                            return;
                        }
                        if (i9 == 27) {
                            try {
                                JSONObject jSONObject5 = new JSONObject(item.m14779q());
                                String string9 = jSONObject5.getString("albumName");
                                c6819c.f22588D.setText(String.format(ChatDialogActivity.this.getResources().getString(R.string.message_add_photos_into_album), jSONObject5.getString("numberUpload")));
                                c6819c.f22620h.setText(string9);
                                String string10 = jSONObject5.getString(FirebaseAnalytics.Param.CONTENT);
                                if (TextUtils.isEmpty(string10)) {
                                    BitmapDrawable bitmapDrawable7 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed);
                                    int height7 = bitmapDrawable7.getBitmap().getHeight();
                                    int width7 = bitmapDrawable7.getBitmap().getWidth();
                                    DisplayMetrics displayMetrics3 = Globals.m7388i0().getResources().getDisplayMetrics();
                                    int i59 = this.f10035e;
                                    float f35 = i59;
                                    int i60 = displayMetrics3.widthPixels;
                                    if (i59 / i60 > 0.6f) {
                                        f35 = i60 * 0.6f;
                                    }
                                    float f36 = width7;
                                    float f37 = f35 / f36;
                                    int i61 = (int) (f36 * f37);
                                    float f38 = height7;
                                    int i62 = (int) (f37 * f38);
                                    int i63 = this.f10036f;
                                    if (i62 > i63) {
                                        i61 = (int) (f36 * (i63 / f38));
                                        i62 = i63;
                                    }
                                    c6819c.f22615c.getLayoutParams().height = i62;
                                    c6819c.f22615c.getLayoutParams().width = i61;
                                    C6136j.m23592l(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed);
                                } else {
                                    int iOptInt3 = jSONObject5.optInt(str32);
                                    int iOptInt4 = jSONObject5.optInt(str31);
                                    if (iOptInt3 == 0 || iOptInt4 == 0) {
                                        c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                        c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                    } else {
                                        DisplayMetrics displayMetrics4 = Globals.m7388i0().getResources().getDisplayMetrics();
                                        int i64 = this.f10035e;
                                        float f39 = i64;
                                        int i65 = displayMetrics4.widthPixels;
                                        if (i64 / i65 > 0.6f) {
                                            f39 = i65 * 0.6f;
                                        }
                                        float f40 = iOptInt3;
                                        float f41 = f39 / f40;
                                        int i66 = (int) (f40 * f41);
                                        float f42 = iOptInt4;
                                        int i67 = (int) (f41 * f42);
                                        int i68 = this.f10036f;
                                        if (i67 > i68) {
                                            i66 = (int) (f40 * (i68 / f42));
                                            i67 = i68;
                                        }
                                        c6819c.f22615c.getLayoutParams().height = i67;
                                        c6819c.f22615c.getLayoutParams().width = i66;
                                        C2973l0.a aVar4 = new C2973l0.a();
                                        aVar4.f13200d = string10;
                                        MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, new C2973l0(-1L, "", -1L, "", "", "Photo", -1L, "", aVar4, aVar4, iOptInt3, iOptInt4, 0, 0, 0, 0, 0L), MediaLoader.Option.ORIGINAL_PREFER_CACHE);
                                    }
                                }
                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                                return;
                            } catch (Exception e18) {
                                Log.d("ChatDialogActivity", Log.getStackTraceString(e18));
                                return;
                            }
                        }
                        if (i9 == 31) {
                            String strM14747I32 = item.m14747I("userId");
                            String strM14747I33 = item.m14747I("displayName");
                            m11774T1(c6819c.f22620h, c6819c.f22615c, strM14747I32, strM14747I33 != null ? strM14747I33 : "");
                            return;
                        }
                        if (i9 == 33) {
                            long j12 = NumberUtils.toLong(item.m14747I(str2), -1L);
                            if (j12 != -1) {
                                C2973l0 c2973l0M14725v5 = C2950b0.m14914m().m14725v(j12);
                                if (c2973l0M14725v5 != null) {
                                    int iM15151w2 = c2973l0M14725v5.m15151w();
                                    int iM15141m2 = c2973l0M14725v5.m15141m();
                                    if (iM15151w2 == 0 || iM15141m2 == 0) {
                                        c6819c.f22615c.getLayoutParams().height = this.f10033c;
                                        c6819c.f22615c.getLayoutParams().width = this.f10032b;
                                        ChatDialogActivity.m11188Rb(c2973l0M14725v5);
                                    } else {
                                        float f43 = iM15151w2;
                                        float f44 = this.f10035e / f43;
                                        int i69 = (int) (f43 * f44);
                                        float f45 = iM15141m2;
                                        int i70 = (int) (f44 * f45);
                                        int i71 = this.f10036f;
                                        if (i70 > i71) {
                                            i69 = (int) (f43 * (i71 / f45));
                                            i70 = i71;
                                        }
                                        c6819c.f22615c.getLayoutParams().height = i70;
                                        c6819c.f22615c.getLayoutParams().width = i69;
                                    }
                                    MediaLoader.m7156o(ChatDialogActivity.this, c6819c.f22615c, c2973l0M14725v5, MediaLoader.Option.ROUNDED_THUMBNAIL);
                                } else {
                                    BitmapDrawable bitmapDrawable8 = (BitmapDrawable) ChatDialogActivity.this.m11582n7().getResources().getDrawable(R.drawable.icon_photo_failed);
                                    int height8 = bitmapDrawable8.getBitmap().getHeight();
                                    float width8 = bitmapDrawable8.getBitmap().getWidth();
                                    float f46 = this.f10035e / width8;
                                    int i72 = (int) (width8 * f46);
                                    float f47 = height8;
                                    int i73 = (int) (f46 * f47);
                                    int i74 = this.f10036f;
                                    if (i73 > i74) {
                                        i72 = (int) (width8 * (i74 / f47));
                                        i73 = i74;
                                    }
                                    c6819c.f22615c.getLayoutParams().height = i73;
                                    c6819c.f22615c.getLayoutParams().width = i72;
                                    C6136j.m23593m(ChatDialogActivity.this, c6819c.f22615c, R.drawable.icon_photo_failed, true);
                                }
                                c6819c.f22615c.setTag(R.id.tag_Position, Integer.valueOf(this.f10040j.indexOf(item)));
                                String strM14747I34 = item.m14747I(str4);
                                if (strM14747I34 != null) {
                                    c6819c.f22630r.setText(CLUtility.m16519c(strM14747I34));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        if (i9 == 37) {
                            try {
                                JSONObject jSONObject6 = new JSONObject(item.m14779q());
                                String string11 = jSONObject6.getString("mediaName");
                                long j13 = Long.parseLong(jSONObject6.getString("mediaSize"));
                                try {
                                    Long.parseLong(jSONObject6.getString("expirationTime"));
                                } catch (Exception e19) {
                                    e19.printStackTrace();
                                }
                                String str33 = ChatDialogActivity.this.getResources().getString(R.string.file_size) + ": " + CLUtility.m16592u0(ChatDialogActivity.this, j13);
                                c6819c.f22620h.setText(string11);
                                c6819c.f22631s.setText(str33);
                                c6819c.f22618f.setImageResource(CLUtility.m16560m0(string11));
                                return;
                            } catch (JSONException e20) {
                                e20.printStackTrace();
                                return;
                            }
                        }
                        if (i9 == 39) {
                            m11780V1(c6819c.f22620h, R.string.need_update_description, item.m14745G());
                            c6819c.f22623k.setText(CLUtility.m16454J2(item.m14788z()));
                            return;
                        }
                        if (i9 != 40) {
                            if (i9 == 41) {
                                String strM14747I35 = item.m14747I(str30);
                                if (C5170o0.m20170e(strM14747I35)) {
                                    c6819c.f22620h.setText(ChatDialogActivity.this.getString(R.string.S_replied_to_a_question_in_polls, item.m14746H()));
                                } else {
                                    c6819c.f22620h.setText(strM14747I35);
                                }
                                c6819c.f22638z.setTag(Integer.valueOf(i10));
                                c6819c.f22612a0.setTextColor(ChatDialogActivity.this.getResources().getColor(!ChatDialogActivity.this.f9914g.f13712K ? R.color.you_color_normal_blue_text : R.color.you_color_normal_gray));
                                c6819c.f22614b0.setVisibility(!ChatDialogActivity.this.f9914g.f13712K ? 0 : 8);
                                return;
                            }
                            if (i9 == 42) {
                                String strM14747I36 = item.m14747I("topicDesc");
                                if (C5170o0.m20170e(strM14747I36)) {
                                    c6819c.f22620h.setText(ChatDialogActivity.this.getString(R.string.poll));
                                } else {
                                    c6819c.f22620h.setText(strM14747I36);
                                }
                                String strM14747I37 = item.m14747I(str30);
                                if (C5170o0.m20170e(strM14747I37)) {
                                    c6819c.f22599O.setText(ChatDialogActivity.this.getString(R.string.S_replied_to_a_question_in_polls, item.m14746H()));
                                } else {
                                    c6819c.f22599O.setText(strM14747I37);
                                }
                                c6819c.f22638z.setTag(Integer.valueOf(i10));
                                return;
                            }
                            if (i9 != 43) {
                                if (i9 == 45) {
                                    String strM14747I38 = item.m14747I(str30);
                                    if (!C5170o0.m20170e(strM14747I38)) {
                                        c6819c.f22620h.setText(strM14747I38);
                                    }
                                    c6819c.f22638z.setTag(Integer.valueOf(i10));
                                    return;
                                }
                                return;
                            }
                            if (C5050a.m19750c(ChatDialogActivity.this.m11582n7(), item.m14779q())) {
                                ((EmojiconTextView) c6819c.f22620h).setEmojiconSize(this.f10039i);
                            }
                            String strM14747I39 = item.m14747I("replyMessage");
                            String strM14747I40 = item.m14747I("replyText");
                            String strM14747I41 = item.m14747I("replySender");
                            if ("File".equalsIgnoreCase(item.m14747I("sourceType"))) {
                                c6819c.f22601Q.setVisibility(8);
                                c6819c.f22602R.setVisibility(8);
                                c6819c.f22618f.setImageResource(CLUtility.m16560m0(strM14747I39));
                                c6819c.f22618f.setVisibility(0);
                            } else {
                                c6819c.f22601Q.setVisibility(0);
                                c6819c.f22602R.setVisibility(0);
                                c6819c.f22618f.setVisibility(8);
                            }
                            c6819c.f22599O.setText(strM14747I39);
                            c6819c.f22620h.setText(strM14747I40);
                            m11780V1(c6819c.f22600P, R.string.chat_message_sender_reply_reply_name, strM14747I41);
                            c6819c.f22638z.setTag(Integer.valueOf(i10));
                            m11811i2(i9, c6819c, i10, CLUtility.m16543i(c6819c.f22620h));
                            return;
                        }
                        TextView textView19 = c6819c.f22628p;
                        if (textView19 != null) {
                            textView19.setTextColor(ChatDialogActivity.this.getResources().getColor(R.color.you_color_normal));
                            c6819c.f22628p.setVisibility(0);
                        }
                        String strM11591q72 = ChatDialogActivity.this.m11591q7(item);
                        String strM14747I42 = item.m14747I("callType");
                        Object obj6 = obj;
                        if (strM14747I42.equals(obj6) || strM14747I42.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                            if (!strM11591q72.equals("meeting")) {
                                if (!strM11591q72.equals(TtmlNode.END)) {
                                    if (strM14747I42.equals(obj6)) {
                                        c6819c.f22617e.setImageResource(R.drawable.icon_video_hangup);
                                    } else if (strM14747I42.equals(MimeTypes.BASE_TYPE_AUDIO)) {
                                        c6819c.f22617e.setImageResource(R.drawable.icon_audio_hangup);
                                    }
                                    switch (strM11591q72) {
                                        case "unreached":
                                            TextView textView20 = c6819c.f22628p;
                                            if (textView20 != null) {
                                                textView20.setText(ChatDialogActivity.this.getString(R.string.not_connected));
                                                C5179r0.m20247b(c6819c.f22628p, 1);
                                            }
                                            c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallUnreached");
                                            break;
                                        case "cancel":
                                            TextView textView21 = c6819c.f22628p;
                                            if (textView21 != null) {
                                                textView21.setText(ChatDialogActivity.this.getString(R.string.canncelled));
                                            }
                                            c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallCancel");
                                            break;
                                        case "reject":
                                        case "busy":
                                            TextView textView22 = c6819c.f22628p;
                                            if (textView22 != null) {
                                                textView22.setText(ChatDialogActivity.this.getString(R.string.busy));
                                            }
                                            c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallBusy");
                                            break;
                                        default:
                                            TextView textView23 = c6819c.f22628p;
                                            if (textView23 != null) {
                                                textView23.setText(ChatDialogActivity.this.getString(R.string.missed));
                                            }
                                            c6819c.f22617e.setContentDescription("[AID]Chatroom_SendUncall");
                                            break;
                                    }
                                } else {
                                    if (strM14747I42.equals(obj6)) {
                                        c6819c.f22617e.setImageResource(R.drawable.icon_video_call_end);
                                    } else {
                                        c6819c.f22617e.setImageResource(R.drawable.icon_audio_call_end);
                                    }
                                    Date dateM20085a4 = C5157k.m20085a(item.m14747I("startTime"));
                                    Date dateM20085a5 = C5157k.m20085a(item.m14747I("endTime"));
                                    long time2 = (dateM20085a5 == null || dateM20085a4 == null) ? 0L : (dateM20085a5.getTime() - dateM20085a4.getTime()) / 1000;
                                    j9 = time2 >= 0 ? time2 : 0L;
                                    strM16454J2 = dateM20085a5 != null ? CLUtility.m16454J2(dateM20085a5) : "";
                                    String strM20086b2 = C5157k.m20086b(j9);
                                    if (c6819c.f22623k != null && !C2925v.m14596g0(item)) {
                                        c6819c.f22623k.setText(strM16454J2);
                                    }
                                    c6819c.f22628p.setText(strM20086b2);
                                }
                            } else {
                                if (strM14747I42.equals(obj6)) {
                                    c6819c.f22617e.setImageResource(R.drawable.icon_video_call_start);
                                } else {
                                    c6819c.f22617e.setImageResource(R.drawable.icon_audio_call_start);
                                }
                                Date dateM20085a6 = C5157k.m20085a(item.m14747I("startTime"));
                                if (dateM20085a6 == null) {
                                    dateM20085a6 = item.m14788z();
                                }
                                String strM16454J23 = CLUtility.m16454J2(dateM20085a6);
                                if (c6819c.f22623k != null && !C2925v.m14596g0(item)) {
                                    c6819c.f22623k.setText(strM16454J23);
                                }
                                TextView textView24 = c6819c.f22628p;
                                if (textView24 != null) {
                                    textView24.setText(ChatDialogActivity.this.getString(R.string.started));
                                }
                                c6819c.f22638z.setContentDescription("[AID]Chatroom_meetingCall");
                            }
                        } else if (strM11591q72.equals("normal")) {
                            String strM14747I43 = item.m14747I(str4);
                            if (strM14747I43 != null && !strM14747I43.isEmpty()) {
                                strM16454J2 = CLUtility.m16523d(strM14747I43);
                            }
                            TextView textView25 = c6819c.f22628p;
                            if (textView25 != null) {
                                textView25.setText(strM16454J2);
                            }
                            c6819c.f22617e.setImageResource(R.drawable.icon_call);
                            c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCall");
                        } else {
                            c6819c.f22617e.setImageResource(R.drawable.icon_uncall);
                            if (strM11591q72.equals("cancel")) {
                                TextView textView26 = c6819c.f22628p;
                                if (textView26 != null) {
                                    textView26.setText(ChatDialogActivity.this.getString(R.string.missed));
                                }
                                c6819c.f22617e.setContentDescription("[AID]Chatroom_SendCallCancel");
                            } else {
                                TextView textView27 = c6819c.f22628p;
                                if (textView27 != null) {
                                    textView27.setText(ChatDialogActivity.this.getString(R.string.missed));
                                }
                                c6819c.f22617e.setContentDescription("[AID]Chatroom_SendUncall");
                            }
                        }
                        c6819c.f22638z.setTag(Integer.valueOf(i10));
                        return;
                    }
                    if (item.m14778p().equals(MessageObj.MessageType.Audio)) {
                        i20 = 0;
                        c6819c.f22627o.setVisibility(0);
                    } else {
                        c6819c.f22627o.setVisibility(8);
                        i20 = 0;
                    }
                    c6819c.f22625m.setVisibility(i20);
                    c6819c.f22628p.setVisibility(i20);
                    c6819c.f22628p.setText(m11825t0(item.m14741C(), item.m14787y()));
                    c6819c.f22626n.setVisibility(8);
                    RelativeLayout relativeLayout10 = c6819c.f22591G;
                    if (relativeLayout10 != null) {
                        relativeLayout10.setVisibility(8);
                    }
                    if (i9 == 29) {
                        c6819c.f22638z.setVisibility(8);
                    }
                    str2 = "mediaId";
                    str3 = "width";
                    str4 = "duration";
                    str = str7;
                    obj = obj2;
                }
                str5 = "height";
                str6 = "address";
                c6819c.f22623k.setText(CLUtility.m16454J2(item.m14788z()));
                C5169o.m20164i(c6819c.f22623k);
                if (i9 != 21) {
                }
            }
        }

        /* renamed from: k0 */
        public void m11814k0(List<MessageObj> list) throws NumberFormatException {
            ArrayList<String> arrayList = new ArrayList<>();
            boolean z8 = true;
            for (MessageObj messageObj : list) {
                arrayList.add(messageObj.m14777o());
                if (z8 && !m11798c0(C6598a.m25239a(messageObj))) {
                    z8 = false;
                }
            }
            long[] jArrM11824s0 = m11824s0(list);
            if (jArrM11824s0.length != 0) {
                Intent intent = new Intent(ChatDialogActivity.this, (Class<?>) ShareMediaActivity.class);
                intent.putStringArrayListExtra("sharedMediaMsgIdList", arrayList);
                if (z8) {
                    intent.putExtra("shareType", ShareType.Internal_Media.toString());
                } else {
                    intent.putExtra("shareType", ShareType.Forward.toString());
                }
                intent.putExtra("share_media_id", jArrM11824s0);
                intent.putExtra("withComment", true);
                ChatDialogActivity.this.startActivity(intent);
            }
        }

        /* renamed from: k2 */
        public void m11815k2(View view, int i9) {
            ProgressBar progressBar = ((C6819c) view.getTag()).f22624l;
            if (progressBar != null) {
                progressBar.setProgress(i9);
            }
        }

        /* renamed from: l0 */
        public final MessageObj m11816l0(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return new MessageObj(-1L, "D_" + calendar.get(1) + "_" + (calendar.get(2) + 1) + "_" + calendar.get(5), "", calendar.getTimeInMillis(), MessageObj.MessageType.Date, "", 0, "", "", "", MessageObj.TTLStatus.NO_TTL, -1L, -1, 0L, MessageObj.MemberStatus.NO_MemberStatus, false, "", "0", "", "", "", false, 0);
        }

        /* renamed from: l2 */
        public final void m11817l2(int i9, View view, int i10) {
            if (view == null) {
                Log.d("ChatDialogActivity", "[updateListener] get null convertView");
                return;
            }
            C6819c c6819c = (C6819c) view.getTag();
            AbstractC6817a abstractC6817aM25388a = AbstractC6817a.m25388a(i9, c6819c);
            if (c6819c == null) {
                return;
            }
            m11789Y1(c6819c, i10);
            abstractC6817aM25388a.mo25389b(ChatDialogActivity.this.f9983x0);
        }

        /* renamed from: m0 */
        public final MessageObj m11818m0(long j9) {
            return new MessageObj(-1L, "R_" + j9, "", j9, MessageObj.MessageType.UnreadLine, "", 0, "", "", "", MessageObj.TTLStatus.NO_TTL, -1L, -1, 0L, MessageObj.MemberStatus.NO_MemberStatus, false, "", "0", "", "", "", false, 0);
        }

        /* renamed from: n0 */
        public final Integer m11819n0(String str) {
            Map<String, String> mapM20828w0;
            if (C5170o0.m20170e(str)) {
                return null;
            }
            XMPPManager.HandleType handleType = XMPPManager.HandleType.CHATROOM;
            List<AbstractC5594b> listM14187k = XMPPManager.m14187k(str, handleType);
            if (listM14187k.isEmpty() || (mapM20828w0 = C5321e.m20828w0(new C2904l((Message) listM14187k.get(0), handleType))) == null) {
                return null;
            }
            Integer numM11806g0 = m11806g0(mapM20828w0.get("userId"));
            return numM11806g0 != null ? numM11806g0 : m11806g0(mapM20828w0.get("adminId"));
        }

        /* renamed from: o0 */
        public final List<MessageObj> m11820o0(List<MessageObj> list) {
            ArrayList arrayList = new ArrayList();
            for (MessageObj messageObj : list) {
                if (Arrays.asList(MessageObj.MessageType.Photo, MessageObj.MessageType.Comment, MessageObj.MessageType.PhotoNote, MessageObj.MessageType.Audio, MessageObj.MessageType.CommentUpdate, MessageObj.MessageType.Video, MessageObj.MessageType.File).contains(messageObj.m14778p())) {
                    long j9 = NumberUtils.toLong(messageObj.m14747I("mediaId"), -1L);
                    if (j9 != -1 && C2950b0.m14914m().m14725v(j9) == null) {
                        arrayList.add(messageObj);
                    }
                }
            }
            return arrayList;
        }

        /* renamed from: p0 */
        public final List<MessageObj.MessageType> m11821p0(List<MessageObj> list) throws NumberFormatException {
            if (list == null || list.size() == 0) {
                return new ArrayList();
            }
            HashMap map = new HashMap();
            for (MessageObj messageObj : list) {
                String strM14747I = messageObj.m14747I("mediaId");
                if (!C5170o0.m20170e(strM14747I)) {
                    try {
                        long j9 = Long.parseLong(strM14747I);
                        if (!map.containsKey(Long.valueOf(j9))) {
                            map.put(Long.valueOf(j9), messageObj.m14778p());
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            List<C2973l0> listM14726w = C2950b0.m14914m().m14726w(m11824s0(list));
            if (listM14726w != null) {
                Iterator<C2973l0> it = listM14726w.iterator();
                while (it.hasNext()) {
                    map.remove(Long.valueOf(it.next().m15144p()));
                }
            }
            return new ArrayList(map.values());
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: q0, reason: merged with bridge method [inline-methods] */
        public MessageObj getItem(int i9) {
            return !m11791Z0(i9) ? this.f10040j.get(i9) : this.f10041k.get(i9 - this.f10040j.size());
        }

        /* renamed from: r0 */
        public final int m11823r0(int i9) {
            return (!Group.m15743f(ChatDialogActivity.this.f9914g.f13716c) || ChatDialogActivity.this.f9914g.f13711J || ChatDialogActivity.this.f9914g.m15751i() || ChatDialogActivity.this.f9914g.f13712K) ? i9 : i9 + 1;
        }

        /* renamed from: s0 */
        public final long[] m11824s0(List<MessageObj> list) throws NumberFormatException {
            if (list == null || list.size() == 0) {
                return new long[0];
            }
            ArrayList arrayList = new ArrayList();
            Iterator<MessageObj> it = list.iterator();
            while (it.hasNext()) {
                String strM14747I = it.next().m14747I("mediaId");
                if (!C5170o0.m20170e(strM14747I)) {
                    try {
                        long j9 = Long.parseLong(strM14747I);
                        if (!arrayList.contains(Long.valueOf(j9))) {
                            arrayList.add(Long.valueOf(j9));
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            int size = arrayList.size();
            long[] jArr = new long[size];
            for (int i9 = 0; i9 < size; i9++) {
                jArr[i9] = ((Long) arrayList.get(i9)).longValue();
            }
            return jArr;
        }

        /* renamed from: t0 */
        public final String m11825t0(MessageObj.TTLStatus tTLStatus, int i9) {
            if (!tTLStatus.equals(MessageObj.TTLStatus.NOT_START)) {
                return "";
            }
            Pair<Integer, Long> pairM16562m2 = CLUtility.m16562m2(i9);
            int iIntValue = ((Long) pairM16562m2.second).intValue();
            int iIntValue2 = ((Integer) pairM16562m2.first).intValue();
            return String.format(iIntValue2 != 1 ? iIntValue2 != 2 ? iIntValue2 != 3 ? ChatDialogActivity.this.getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_d, iIntValue) : ChatDialogActivity.this.getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_s, iIntValue) : ChatDialogActivity.this.getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_m, iIntValue) : ChatDialogActivity.this.getResources().getQuantityString(R.plurals.self_destruct_chat_not_start_h, iIntValue), Integer.valueOf(iIntValue));
        }

        /* renamed from: u0 */
        public int m11826u0() {
            return this.f10040j.size();
        }

        /* renamed from: v0 */
        public final float m11827v0() {
            float f9 = Globals.m7388i0().getResources().getDisplayMetrics().densityDpi / 440.0f;
            if (f9 >= 1.0f) {
                return f9;
            }
            return 1.0f;
        }

        /* renamed from: w0 */
        public final int m11828w0(int i9, C6819c c6819c, String str) {
            int maxWidth;
            int i10;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) (PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE.equals(str) ? c6819c.f22608X : c6819c.f22609Y).getLayoutParams();
            if (i9 == 0) {
                maxWidth = c6819c.f22620h.getMaxWidth();
                i10 = marginLayoutParams.rightMargin;
            } else {
                if (i9 != 21) {
                    return c6819c.f22620h.getMaxWidth();
                }
                maxWidth = c6819c.f22620h.getMaxWidth();
                i10 = marginLayoutParams.leftMargin;
            }
            return maxWidth - i10;
        }

        /* renamed from: x0 */
        public void m11829x0(final List<MessageObj> list) throws JSONException {
            Log.d("ChatDialogActivity", "[handleOnAddToTODOMessageButtonClick]");
            if (list == null || list.size() == 0) {
                return;
            }
            List<MessageObj> listM11820o0 = m11820o0(list);
            list.removeAll(listM11820o0);
            if (listM11820o0.size() <= 0) {
                m11778V(list);
                return;
            }
            if (list.size() == 0) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
                builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.feedback_error));
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.error_media_not_found));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.n4
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        ChatDialogActivity.C1954e2.m11686b1(dialogInterface, i9);
                    }
                });
                builderM16382a.show();
                return;
            }
            AlertDialog.Builder builderM16382a2 = C3123g.m16382a(ChatDialogActivity.this);
            builderM16382a2.setTitle(ChatDialogActivity.this.getString(R.string.forward_messages));
            builderM16382a2.setMessage(ChatDialogActivity.this.getString(R.string.forward_delete_media_messages_warning));
            builderM16382a2.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.o4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) throws JSONException {
                    this.f22388b.m11688c1(list, dialogInterface, i9);
                }
            });
            builderM16382a2.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.p4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ChatDialogActivity.C1954e2.m11690d1(dialogInterface, i9);
                }
            });
            builderM16382a2.show();
        }

        /* renamed from: y0 */
        public void m11830y0(MessageObj messageObj) {
            if (C2925v.m14598h0(messageObj)) {
                ChatDialogActivity.this.new AsyncTaskC2011v1(messageObj).executeOnExecutor(C6385v.f21554b, new Void[0]);
            }
        }

        /* renamed from: z0 */
        public void m11831z0(MessageObj messageObj) {
            Log.d("ChatDialogActivity", "[handleOnCopyMessageButtonClick]");
            if (messageObj == null) {
                return;
            }
            C5145g.m20042a(messageObj.m14778p().equals(MessageObj.MessageType.ReplyText) ? messageObj.m14747I("replyText") : messageObj.m14779q());
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$f */
    public class C1955f extends BroadcastReceiver {
        public C1955f() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m11867c(DialogInterface dialogInterface, int i9) {
            ChatDialogActivity.this.m11441Aa();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m11868d(DialogInterface dialogInterface, int i9) {
            if (ChatDialogActivity.this.m7367J0()) {
                return;
            }
            dialogInterface.dismiss();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("Custom_Broadcast_Send_Log".equals(intent.getAction())) {
                C3123g.m16382a(ChatDialogActivity.this).setMessage(ChatDialogActivity.this.getString(R.string.send_log_notice)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: y2.m2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f22358b.m11867c(dialogInterface, i9);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: y2.n2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f22374b.m11868d(dialogInterface, i9);
                    }
                }).setCancelable(false).create().show();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$f0 */
    public class ViewOnClickListenerC1956f0 implements View.OnClickListener {
        public ViewOnClickListenerC1956f0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Profile");
            if (ChatDialogActivity.this.f9980w1 != null && ChatDialogActivity.this.f9980w1.isShowing()) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            ChatDialogActivity.this.m11542ca();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$f1 */
    public class C1957f1 implements CLUtility.InterfaceC3137d {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f10120a;

        public C1957f1(DialogC3133q dialogC3133q) {
            this.f10120a = dialogC3133q;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m11870d(DialogC3133q dialogC3133q, Uri uri) {
            dialogC3133q.dismiss();
            ChatDialogActivity.this.m11618ya(new FileItem(null, new File(uri.getPath()).getPath(), uri.toString(), true));
        }

        @Override // com.cyberlink.you.utility.CLUtility.InterfaceC3137d
        /* renamed from: a */
        public void mo7014a() {
            this.f10120a.dismiss();
            C5187v0.m20268d(ChatDialogActivity.this.getResources().getQuantityString(R.plurals.file_type_not_supported, 1));
        }

        @Override // com.cyberlink.you.utility.CLUtility.InterfaceC3137d
        /* renamed from: b */
        public void mo7015b(final Uri uri) {
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            final DialogC3133q dialogC3133q = this.f10120a;
            chatDialogActivity.runOnUiThread(new Runnable() { // from class: y2.h3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22269b.m11870d(dialogC3133q, uri);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$f2 */
    public static class AsyncTaskC1958f2 extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public final String f10122a;

        /* renamed from: b */
        public final List<String> f10123b;

        /* renamed from: c */
        public final List<String> f10124c;

        /* renamed from: d */
        public final AbstractC6381r<Boolean, Void> f10125d;

        public AsyncTaskC1958f2(String str, List<String> list, List<String> list2, AbstractC6381r<Boolean, Void> abstractC6381r) {
            this.f10122a = str;
            this.f10123b = list;
            this.f10124c = list2;
            this.f10125d = abstractC6381r;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            boolean z8;
            boolean zEquals;
            boolean z9 = false;
            if (this.f10123b.isEmpty()) {
                z8 = true;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                Iterator<String> it = this.f10123b.iterator();
                while (it.hasNext()) {
                    arrayList.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, it.next()));
                }
                Pair<String, String> pairM15676n = FriendsClient.m15676n("chat", "cancelScheduleSend", arrayList);
                z8 = "200".equals(pairM15676n.first) || "400".equals(pairM15676n.first);
                if (z8) {
                    CLUtility.m16594u2(this.f10122a);
                }
            }
            if (this.f10124c.isEmpty()) {
                zEquals = true;
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                Iterator<String> it2 = this.f10124c.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, it2.next()));
                }
                zEquals = "200".equals(FriendsClient.m15676n("chat", "recallMessage", arrayList2).first);
            }
            if (z8 && zEquals) {
                z9 = true;
            }
            return Boolean.valueOf(z9);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            this.f10125d.m24506d(bool);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$g */
    public class AsyncTaskC1959g extends AsyncTask<Void, Void, File> {

        /* renamed from: a */
        public final /* synthetic */ DialogC3133q f10126a;

        public AsyncTaskC1959g(DialogC3133q dialogC3133q) {
            this.f10126a = dialogC3133q;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public File doInBackground(Void... voidArr) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
            CLUtility.m16571p(ChatDialogActivity.this, false);
            return new File(CLUtility.m16496V());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(File file) {
            this.f10126a.cancel();
            ChatDialogActivity.this.m11615xa(C6369f.m24471m(file));
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$g0 */
    public class ViewOnClickListenerC1960g0 implements View.OnClickListener {
        public ViewOnClickListenerC1960g0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Videos");
            ChatDialogActivity.this.f9980w1.dismiss();
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) VideoListActivity.class);
            intent.putExtra("Group", ChatDialogActivity.this.f9914g);
            ChatDialogActivity.this.startActivityForResult(intent, 5020);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$g1 */
    public class C1961g1 implements C2925v.e {
        public C1961g1() {
        }

        @Override // com.cyberlink.you.chat.C2925v.e
        /* renamed from: a */
        public void mo11875a(List<MessageObj> list) {
            if (ChatDialogActivity.this.f9991z0 != null) {
                ChatDialogActivity.this.f9991z0.schedule(ChatDialogActivity.this.new C1974j2(), 1000L);
            }
            ChatDialogActivity.this.f9835K0 = list.get(list.size() - 1);
        }

        @Override // com.cyberlink.you.chat.C2925v.e
        /* renamed from: b */
        public void mo11876b(List<MessageObj> list) {
            if (ChatDialogActivity.this.f9810E != null) {
                ChatDialogActivity.this.f9810E.m11784X(list);
            }
            ChatDialogActivity.this.m11590pb();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$g2 */
    public interface InterfaceC1962g2 {
        /* renamed from: a */
        void mo11877a(StickerPackObj stickerPackObj);
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$h */
    public class C1963h extends AbstractC6381r<File, Void> {

        /* renamed from: c */
        public final /* synthetic */ C2973l0 f10130c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C1963h(Handler handler, C2973l0 c2973l0) {
            super(handler);
            this.f10130c = c2973l0;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(File file) {
            ULogUtility.m16680p("ChatDialogActivity", "share photo to other apps completed");
            if (file != null) {
                ChatDialogActivity.this.m11554fa(this.f10130c.m15147s().equals("Video") ? MimeTypes.VIDEO_MP4 : "image/jpeg", null, null, C6369f.m24471m(file));
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r22) {
            ULogUtility.m16680p("ChatDialogActivity", "share photo to other apps fail");
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$h0 */
    public class ViewOnClickListenerC1964h0 implements View.OnClickListener {
        public ViewOnClickListenerC1964h0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (int size = ChatDialogActivity.this.f9919h0.size() - 1; size >= 0; size--) {
                UploadUtils.m16965l("ChatDialogActivity", "[onOpenScheduleListBtnClick] mScheduleSendList.get(i).messageId = " + ((ScheduleSendObj) ChatDialogActivity.this.f9919h0.get(size)).f12448c);
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                MessageObj messageObjM11608v7 = chatDialogActivity.m11608v7(((ScheduleSendObj) chatDialogActivity.f9919h0.get(size)).f12448c);
                if (messageObjM11608v7 != null) {
                    messageObjM11608v7.m14761W(new Date(FriendsClient.m15646K()));
                    C2950b0.m14916o().m15159D(messageObjM11608v7.m14777o(), messageObjM11608v7, "SendTime");
                    if (ChatDialogActivity.this.f9810E != null) {
                        ChatDialogActivity.this.f9810E.remove(messageObjM11608v7);
                        ChatDialogActivity.this.f9810E.add(messageObjM11608v7);
                    }
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$h1 */
    public class ViewOnClickListenerC1965h1 implements View.OnClickListener {
        public ViewOnClickListenerC1965h1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Integer num;
            try {
                num = (Integer) view.getTag();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
            if (num == null) {
                return;
            }
            MessageObj item = ChatDialogActivity.this.f9810E.getItem(num.intValue());
            if (ChatDialogActivity.this.f9911f0 != null && ChatDialogActivity.this.f9911f0.equals(item) && ChatDialogActivity.this.f9939m0 != null && ChatDialogActivity.this.f9939m0.m20016f() == 1) {
                ChatDialogActivity.this.m11581mb();
                return;
            }
            long j9 = NumberUtils.toLong(new JSONObject(item.m14779q()).getString("mediaId"), -1L);
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
            if (c2973l0M14725v != null) {
                String str = c2973l0M14725v.m15148t().f13201e;
                if (str == null || !new File(str).exists()) {
                    item.f12925B = true;
                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                    ChatDialogActivity.this.m11572k7(item, c2973l0M14725v.m15148t().f13200d, String.valueOf(j9));
                    c2973l0M14725v.m15148t().f13201e = str;
                    C2950b0.m14914m().m14698H(c2973l0M14725v.m15144p(), c2973l0M14725v, "Original");
                } else {
                    Log.d("ChatDialogActivity", "Playing local voice file!");
                    ChatDialogActivity.this.m11578lb(item, str);
                }
            }
            if (ChatDialogActivity.this.f9810E != null) {
                ChatDialogActivity.this.f9810E.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$h2 */
    public class AsyncTaskC1966h2 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final ArrayList<MessageObj> f10134a = new ArrayList<>();

        /* renamed from: b */
        public final ArrayList<C6201i> f10135b = new ArrayList<>();

        public AsyncTaskC1966h2() {
        }

        /* renamed from: a */
        public void m11880a(MessageObj messageObj) {
            if (messageObj != null) {
                this.f10134a.add(messageObj);
                this.f10135b.add(new C6201i(ChatDialogActivity.this.f9910f, messageObj));
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Iterator<MessageObj> it = this.f10134a.iterator();
            while (it.hasNext()) {
                C2950b0.m14916o().m15157B(it.next());
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r22) {
            if (this.f10134a.size() > 0) {
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.m11784X(this.f10134a);
                }
                C6196d0.m23692d().m23699i(this.f10135b);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$i */
    public class C1967i implements C6818b.a {

        /* renamed from: a */
        public final /* synthetic */ Group f10137a;

        public C1967i(Group group) {
            this.f10137a = group;
        }

        @Override // p254z2.C6818b.a
        /* renamed from: a */
        public void mo11883a() {
            ULogUtility.m16670f("ChatDialogActivity", "[addMessageToDo] forwardAndSendMessages fail");
            if (ChatDialogActivity.this.isFinishing()) {
                return;
            }
            C5152i0.m20065b(ChatDialogActivity.this.f9898c);
        }

        @Override // p254z2.C6818b.a
        public void onSuccess() {
            if (!ChatDialogActivity.this.isFinishing()) {
                C5152i0.m20065b(ChatDialogActivity.this.f9898c);
            }
            Intent intent = new Intent(ChatDialogActivity.this.getApplicationContext(), (Class<?>) ChatDialogActivity.class);
            intent.putExtra("Group", this.f10137a);
            ChatDialogActivity.this.startActivity(intent);
            ChatDialogActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$i0 */
    public class ViewOnClickListenerC1968i0 implements View.OnClickListener {
        public ViewOnClickListenerC1968i0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Invite");
            ChatDialogActivity.this.f9980w1.dismiss();
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7().getApplicationContext(), (Class<?>) SelectFromFriendGroupActivity.class);
            Bundle bundle = new Bundle();
            ArrayList arrayList = new ArrayList(ChatDialogActivity.this.f9927j0.size());
            arrayList.addAll(ChatDialogActivity.this.f9927j0.values());
            Globals.C1408b.m7655b().m7657d("INTENT_CANNOT_MODIFIED_USERS_LIST", arrayList);
            Globals.C1408b.m7655b().m7657d("INTENT_PREV_SELECTED_USERS", arrayList);
            bundle.putParcelable("Group", ChatDialogActivity.this.f9914g);
            intent.putExtras(bundle);
            ChatDialogActivity.this.startActivityForResult(intent, 5010);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$i1 */
    public class AsyncTaskC1969i1 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ long f10140a;

        /* renamed from: b */
        public final /* synthetic */ String f10141b;

        /* renamed from: c */
        public final /* synthetic */ long f10142c;

        /* renamed from: d */
        public final /* synthetic */ DialogC3133q f10143d;

        /* renamed from: e */
        public final /* synthetic */ boolean f10144e;

        /* renamed from: f */
        public final /* synthetic */ boolean f10145f;

        /* renamed from: g */
        public final /* synthetic */ String f10146g;

        /* renamed from: h */
        public final /* synthetic */ long f10147h;

        public AsyncTaskC1969i1(long j9, String str, long j10, DialogC3133q dialogC3133q, boolean z8, boolean z9, String str2, long j11) {
            this.f10140a = j9;
            this.f10141b = str;
            this.f10142c = j10;
            this.f10143d = dialogC3133q;
            this.f10144e = z8;
            this.f10145f = z9;
            this.f10146g = str2;
            this.f10147h = j11;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            if (this.f10140a >= System.currentTimeMillis()) {
                return null;
            }
            Globals.C1408b.m7655b().m7657d(this.f10141b, Collections.singletonList(C2950b0.m14914m().m14694C(this.f10141b, C2950b0.m14914m().m14693B(this.f10141b, this.f10142c), 50, MediaDao.SelectType.BOTH)));
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r8) {
            this.f10143d.cancel();
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) ShowMediaActivity.class);
            intent.putExtra("albumId", this.f10141b);
            intent.putExtra("mediaId", this.f10142c);
            intent.putExtra("Group", ChatDialogActivity.this.f9914g);
            intent.putExtra("scheduleTime", this.f10140a);
            intent.putExtra("isNoteMessage", this.f10144e);
            intent.putExtra("activityName", "chatDialogActivity");
            intent.putExtra("ShowShareToMyAlbum", this.f10145f);
            if ("CommentText".equals(this.f10146g) && !this.f10144e) {
                z = (this.f10147h == -1 || C2950b0.m14920s().m15223l(this.f10147h) == null) ? false : true;
                intent.putExtra("commentTextExist", z);
            }
            if (z) {
                long j9 = this.f10147h;
                if (j9 != -1) {
                    intent.putExtra("commentId", j9);
                    intent.putExtra("commentType", this.f10146g);
                }
            }
            ChatDialogActivity.this.m11582n7().startActivityForResult(intent, 5012);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$i2 */
    public class AsyncTaskC1970i2 extends AsyncTask<Void, Void, Integer> {
        public AsyncTaskC1970i2() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer doInBackground(Void... voidArr) throws InterruptedException {
            Thread.currentThread().setName("UpdateBadgesTask");
            while (!C2907m0.m14454I().m14489N()) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e9) {
                    e9.printStackTrace();
                }
            }
            if (ChatDialogActivity.this.f9914g == null) {
                return -1;
            }
            return Integer.valueOf(C2907m0.m14454I().m14480D());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Integer num) {
            if (isCancelled()) {
                return;
            }
            int iIntValue = num.intValue();
            if (iIntValue != -1) {
                if (iIntValue != 0) {
                    if (num.intValue() > 99) {
                        ChatDialogActivity.this.f9814F.setText("N");
                    } else {
                        ChatDialogActivity.this.f9814F.setText(String.valueOf(num));
                    }
                    ChatDialogActivity.this.f9814F.setVisibility(0);
                } else {
                    ChatDialogActivity.this.f9814F.setVisibility(8);
                }
            }
            ChatDialogActivity.this.m11604u2();
        }

        public /* synthetic */ AsyncTaskC1970i2(ChatDialogActivity chatDialogActivity, C1975k c1975k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$j */
    public class ViewOnClickListenerC1971j implements View.OnClickListener {
        public ViewOnClickListenerC1971j() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m11890c(EditText editText, DialogInterface dialogInterface, int i9) {
            if (editText.getText().toString().trim().length() == 0) {
                C5187v0.m20267c(R.string.group_empty_title);
            } else {
                dialogInterface.dismiss();
                ChatDialogActivity.this.m11616xb(editText.getText().toString());
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Resources.NotFoundException {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "CreateGroup");
            ChatDialogActivity.this.f9980w1.dismiss();
            ArrayList arrayList = new ArrayList(ChatDialogActivity.this.f9927j0.size());
            for (Friend friend : ChatDialogActivity.this.f9927j0.values()) {
                if (friend.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                    arrayList.add(friend);
                }
            }
            int integer = ChatDialogActivity.this.getResources().getInteger(R.integer.MaxDisplayNameLength);
            final EditText editText = new EditText(ChatDialogActivity.this);
            AlertDialog.Builder builder = new AlertDialog.Builder(ChatDialogActivity.this);
            builder.setTitle(ChatDialogActivity.this.getString(R.string.create_group_hint_enter_name));
            editText.setHint(R.string.create_group_hint_enter_name);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(integer)});
            editText.setInputType(1);
            builder.setView(editText);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: y2.o2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f22382b.m11890c(editText, dialogInterface, i9);
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: y2.p2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$j0 */
    public class ViewOnClickListenerC1972j0 implements View.OnClickListener {
        public ViewOnClickListenerC1972j0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatDialogActivity.this.f9980w1 != null) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", ChatDialogActivity.this.f9914g);
            Intent intent = new Intent(ChatDialogActivity.this.getApplicationContext(), (Class<?>) GroupJoinLinkActivity.class);
            intent.putExtras(bundle);
            ChatDialogActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$j1 */
    public class C1973j1 implements DialogC6459g.a {
        public C1973j1() {
        }

        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public void mo7918a() {
            if (ChatDialogActivity.this.f9959r0 != null) {
                ChatDialogActivity.this.f9959r0.m17036d();
            }
            ChatDialogActivity.this.m11613wb();
            if (!ChatDialogActivity.this.f9914g.f13716c.equals("Circle") || ChatDialogActivity.this.f9914g.f13712K) {
                return;
            }
            ChatDialogActivity.this.m11585o7();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$j2 */
    public class C1974j2 extends TimerTask {
        public C1974j2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m11893b() {
            if (ChatDialogActivity.this.f9810E != null) {
                ChatDialogActivity.this.f9810E.notifyDataSetChanged();
            }
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.f5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22246b.m11893b();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$k */
    public class C1975k implements SwipeRefreshLayout.InterfaceC0510j {
        public C1975k() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m11896d() {
            ChatDialogActivity.this.f9794A.setRefreshing(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m11897e(String str, Date date) {
            if (ChatDialogActivity.this.f9914g == null || !TextUtils.equals(ChatDialogActivity.this.f9914g.f13723j, str)) {
                return;
            }
            ChatDialogActivity.this.f9847N0 = date;
            ChatDialogActivity.this.m11571jb(false);
            ChatDialogActivity.this.m11580ma(false, false);
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.InterfaceC0510j
        /* renamed from: a */
        public void mo3015a() {
            Date dateM14788z;
            ChatDialogActivity.this.m7366I0().post(new Runnable() { // from class: y2.k2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22336b.m11896d();
                }
            });
            if (XMPPArchiveHelper.m14138o()) {
                return;
            }
            if (ChatDialogActivity.this.f9810E == null || ChatDialogActivity.this.f9810E.isEmpty()) {
                dateM14788z = null;
            } else {
                for (int i9 = 0; i9 < ChatDialogActivity.this.f9810E.getCount(); i9++) {
                    MessageObj item = ChatDialogActivity.this.f9810E.getItem(i9);
                    if (item != null && item.m14778p() != MessageObj.MessageType.Date && item.m14778p() != MessageObj.MessageType.UnreadLine) {
                        dateM14788z = item.m14788z();
                        break;
                    }
                }
                dateM14788z = null;
            }
            if (ChatDialogActivity.this.f9847N0 != null && dateM14788z != null && ChatDialogActivity.this.f9847N0.before(dateM14788z)) {
                dateM14788z = ChatDialogActivity.this.f9847N0;
            }
            if (dateM14788z != null) {
                long jConvert = TimeUnit.DAYS.convert(dateM14788z.getTime() - new Date().getTime(), TimeUnit.MILLISECONDS);
                if (!ChatDialogActivity.this.f9863R0 && Math.abs(jConvert) > 15) {
                    ULogUtility.m16676l("ChatDialogActivity", "Does not query history msg due to is not messenger pro");
                    return;
                }
            }
            if (XMPPArchiveHelper.m14119A(ChatDialogActivity.this.f9910f, dateM14788z, new XMPPArchiveHelper.InterfaceC2847d() { // from class: y2.l2
                @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2847d
                /* renamed from: a */
                public final void mo14151a(String str, Date date) {
                    this.f22348a.m11897e(str, date);
                }
            })) {
                ChatDialogActivity.this.m11571jb(true);
                ChatDialogActivity.this.f9854P.setVisibility(8);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$k0 */
    public class ViewOnClickListenerC1976k0 implements View.OnClickListener {
        public ViewOnClickListenerC1976k0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m11899b(String str, String str2, String str3, String str4) {
            if (!ChatDialogActivity.this.isFinishing() && ChatDialogActivity.this.f9816F1 != null && ChatDialogActivity.this.f9816F1.isShowing()) {
                ChatDialogActivity.this.f9816F1.dismiss();
            }
            if (str3 == null || !str3.equals("200")) {
                Log.d("ChatDialogActivity", "statusCode = " + str3);
                return;
            }
            Group groupM20186h = C5172p.m20186h(C5172p.m20195q(str4));
            if (groupM20186h == null) {
                return;
            }
            ChatDialogActivity.this.f9914g.m15758p(groupM20186h);
            C2950b0.m14912k().m15089z(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), ChatDialogActivity.this.f9914g);
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            chatDialogActivity.m11610vb(chatDialogActivity.f9980w1);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatDialogActivity.this.f9914g.f13733t) {
                C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Unmute");
            } else {
                C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Mute");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("groupId", String.valueOf(ChatDialogActivity.this.f9914g.f13727n)));
            arrayList.add(new C6301o("isNotificationDisabled", String.valueOf(!ChatDialogActivity.this.f9914g.f13733t)));
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            chatDialogActivity.f9816F1 = ProgressDialog.show(chatDialogActivity.m11582n7(), "", ChatDialogActivity.this.getString(R.string.processing), true);
            ChatDialogActivity.this.f9899c0.m15734m("group", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.c3
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f22205a.m11899b(str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$k1 */
    public class C1977k1 implements C3199c.b {
        public C1977k1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m11903g(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            if (ChatDialogActivity.this.f9963s0 != null) {
                ChatDialogActivity.this.f9963s0.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                ChatDialogActivity.this.f9963s0.m24764f(uploadMediaHelper.m16842f1());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m11904h() {
            if (ChatDialogActivity.this.f9963s0 != null) {
                ChatDialogActivity.this.f9963s0.m24760b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m11905i() {
            if (ChatDialogActivity.this.isFinishing() || ChatDialogActivity.this.f9963s0 == null || !ChatDialogActivity.this.f9963s0.isShowing()) {
                return;
            }
            ChatDialogActivity.this.f9963s0.dismiss();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.j3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22326b.m11903g(i9, i10, uploadMediaHelper);
                }
            });
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) {
            if (ChatDialogActivity.this.f9963s0 != null) {
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.i3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22309b.m11905i();
                    }
                });
            }
            List<UploadMediaHelper> listM17042t = c3199c.m17042t();
            C2973l0 c2973l0M16826X0 = null;
            int i9 = 0;
            for (UploadMediaHelper uploadMediaHelper : listM17042t) {
                if (uploadMediaHelper.m16828Y0().equals(UploadUtils.UploadResultType.STEP_3_SUCCESS)) {
                    i9++;
                    if (c2973l0M16826X0 == null) {
                        c2973l0M16826X0 = uploadMediaHelper.m16826X0();
                    }
                } else {
                    Log.d("ChatDialogActivity", "[onComplete] resultType=" + uploadMediaHelper.m16828Y0());
                    C5187v0.m20267c(R.string.error_server_response);
                }
            }
            if (i9 != listM17042t.size()) {
                Log.d("ChatDialogActivity", "[onUploadMultipleMediaCallback] All medias are not uploaded completed.");
            } else if (ChatDialogActivity.this.f9914g != null && c2973l0M16826X0 != null) {
                C2925v.m14630x0(ChatDialogActivity.this.f9914g, c2973l0M16826X0, String.valueOf(i9));
            }
            c3199c.m17034E();
            ChatDialogActivity.this.m11607ub();
            if (!ChatDialogActivity.this.f9914g.f13716c.equals("Circle") || ChatDialogActivity.this.f9914g.f13712K) {
                return;
            }
            ChatDialogActivity.this.m11585o7();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.k3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22337b.m11904h();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$k2 */
    public class AsyncTaskC1978k2 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public boolean f10157a;

        public AsyncTaskC1978k2(boolean z8) {
            this.f10157a = z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Thread.currentThread().setName("updateLastRead");
            if (isCancelled()) {
                Log.d("ChatDialogActivity", "[updateLastReadTask] cancelled");
                return null;
            }
            String str = ChatDialogActivity.this.f9914g.f13723j;
            long j9 = ChatDialogActivity.this.f9914g.f13727n;
            if (this.f10157a) {
                ChatDialogActivity.this.m11457Fb(j9);
            }
            if (ChatDialogActivity.this.f9810E != null && ChatDialogActivity.this.f9810E.m11826u0() > 0) {
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                chatDialogActivity.f9835K0 = chatDialogActivity.f9810E.getItem(ChatDialogActivity.this.f9810E.m11826u0() - 1);
                if (ChatDialogActivity.this.f9835K0 != null && ChatDialogActivity.this.f9914g.m15748d() < ChatDialogActivity.this.f9835K0.m14788z().getTime()) {
                    ChatDialogActivity.this.f9914g.m15753k(ChatDialogActivity.this.f9835K0.m14788z().getTime());
                    ULogUtility.m16680p("ChatDialogActivity", "update group lastRead, groupId = " + j9 + " lastRead = " + ChatDialogActivity.this.f9914g.m15748d());
                    C2950b0.m14912k().m15062A(String.valueOf(j9), ChatDialogActivity.this.f9914g, "LastRead");
                }
            }
            C2907m0.m14454I().m14514x(str);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$l */
    public class C1979l {

        /* renamed from: a */
        public ArrayList<MessageObj> f10159a;

        /* renamed from: b */
        public ArrayList<MessageObj> f10160b;

        /* renamed from: c */
        public MessageObj f10161c;

        public C1979l() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$l0 */
    public class ViewOnClickListenerC1980l0 implements View.OnClickListener {
        public ViewOnClickListenerC1980l0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Photos");
            ChatDialogActivity.this.f9980w1.dismiss();
            ChatDialogActivity.this.m11522X9();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$l1 */
    public class C1981l1 implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ ChatDialogMode f10164a;

        /* renamed from: b */
        public final /* synthetic */ int f10165b;

        /* renamed from: c */
        public final /* synthetic */ Permission f10166c;

        public C1981l1(ChatDialogMode chatDialogMode, int i9, Permission permission) {
            this.f10164a = chatDialogMode;
            this.f10165b = i9;
            this.f10166c = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(ChatDialogActivity.this, this.f10166c);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            ChatDialogActivity.this.m11600t2(this.f10164a, this.f10165b);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$m */
    public class ViewOnClickListenerC1982m implements View.OnClickListener {
        public ViewOnClickListenerC1982m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Profile");
            ChatDialogActivity.this.m11538ba((Friend) view.getTag(R.id.tag_User));
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$m0 */
    public class ViewOnClickListenerC1983m0 implements View.OnClickListener {
        public ViewOnClickListenerC1983m0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Albums");
            ChatDialogActivity.this.f9980w1.dismiss();
            if (ChatDialogActivity.this.f9914g.f13716c.equals("Circle") && !ChatDialogActivity.this.f9914g.f13712K) {
                if (ChatDialogActivity.this.f9827I0) {
                    ChatDialogActivity.this.f9827I0 = false;
                    ChatDialogActivity.this.m11585o7();
                }
                ChatDialogActivity.this.m11511Ua(Tab.Albums, true);
                return;
            }
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            if (chatDialogActivity.m11561h8(chatDialogActivity.f9946o)) {
                ChatDialogActivity.this.f9880W = true;
            }
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) GroupAlbumActivity.class);
            intent.putExtra("Group", ChatDialogActivity.this.f9914g);
            ChatDialogActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$m1 */
    public class AsyncTaskC1984m1 extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ MessageObj f10170a;

        public AsyncTaskC1984m1(MessageObj messageObj) {
            this.f10170a = messageObj;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) throws JSONException {
            JSONObject jSONObjectM7487T = Globals.m7388i0().m7487T();
            try {
                jSONObjectM7487T.put(this.f10170a.m14777o(), this.f10170a.m14788z().getTime());
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            Globals.m7388i0().m7440I2(jSONObjectM7487T);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$n */
    public class ViewOnClickListenerC1985n implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$n$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ View f10173a;

            public a(View view) {
                this.f10173a = view;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(ChatDialogActivity.this.m11582n7(), Permission.LOCATION);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                MessageObj item;
                Integer num = (Integer) this.f10173a.getTag(R.id.tag_Position);
                if (num == null || (item = ChatDialogActivity.this.f9810E.getItem(num.intValue())) == null) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(item.m14779q());
                    Double dValueOf = Double.valueOf(Double.parseDouble(jSONObject.getString("latitude")));
                    Double dValueOf2 = Double.valueOf(Double.parseDouble(jSONObject.getString("longitude")));
                    String str = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.getDefault(), "http://maps.google.com/maps?hl=" + str + "&mrt=loc&q=%f,%f", dValueOf, dValueOf2)));
                    if (C5128a0.m20001a(ChatDialogActivity.this.getApplicationContext(), "com.google.android.apps.maps")) {
                        intent.addFlags(0);
                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    }
                    if (intent.resolveActivity(ChatDialogActivity.this.getPackageManager()) != null) {
                        ChatDialogActivity.this.startActivity(intent);
                    }
                } catch (JSONException e9) {
                    Log.e("ChatDialogActivity", "", e9);
                    e9.printStackTrace();
                }
            }
        }

        public ViewOnClickListenerC1985n() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C5287b.m20583f(Permission.LOCATION, new a(view), ChatDialogActivity.this.m11582n7());
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$n0 */
    public class ViewOnClickListenerC1986n0 implements View.OnClickListener {
        public ViewOnClickListenerC1986n0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ChatDialogActivity.this.f9980w1.dismiss();
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) FileListActivity.class);
            intent.putExtra("Group", ChatDialogActivity.this.f9914g);
            ChatDialogActivity.this.startActivityForResult(intent, 5022);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$n1 */
    public class RunnableC1987n1 implements Runnable {
        public RunnableC1987n1() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$o */
    public class AsyncTaskC1988o extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f10177a;

        public AsyncTaskC1988o(String str) {
            this.f10177a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Thread.currentThread().setName("updateGroupMember4AddNewMember AsyncTask");
            Friend friendM15727f0 = ChatDialogActivity.this.f9899c0.m15727f0(this.f10177a);
            if (friendM15727f0 == null) {
                return null;
            }
            C2950b0.m14899A().m15016h(friendM15727f0);
            ChatDialogActivity.this.f9927j0.put(Long.valueOf(friendM15727f0.f13645c), friendM15727f0);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$o0 */
    public class ViewOnClickListenerC1989o0 implements View.OnClickListener {
        public ViewOnClickListenerC1989o0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatDialogActivity.this.f9980w1 != null && ChatDialogActivity.this.f9980w1.isShowing()) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            ChatDialogActivity.this.m11511Ua(Tab.Albums, true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$o1 */
    public class C1990o1 extends XMPPManager.AbstractC2868s {
        public C1990o1(boolean z8) {
            super(z8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m11917k(MessageObj messageObj) {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.m11741F1(messageObj);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m11918l(MessageObj messageObj) {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.add(messageObj);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n */
        public /* synthetic */ void m11919n(MessageObj messageObj) {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.add(messageObj);
                    if (ChatDialogActivity.this.f9877V) {
                        ChatDialogActivity.this.f9880W = true;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o */
        public /* synthetic */ void m11920o(boolean z8, MessageObj messageObj, String str) {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (ChatDialogActivity.this.f9810E != null) {
                    if (!z8) {
                        ChatDialogActivity.this.f9810E.getCount();
                        ChatDialogActivity.this.f9946o.getHeaderViewsCount();
                        ChatDialogActivity.this.f9946o.getLastVisiblePosition();
                        if (ChatDialogActivity.this.f9795A0 == Tab.Chats) {
                            if (ChatDialogActivity.this.f9867S0.m11982n()) {
                                ChatDialogActivity.this.m11599sb();
                            } else {
                                ChatDialogActivity.this.m11568j7(messageObj, str);
                            }
                            if (messageObj != null && ChatDialogActivity.this.f9835K0 != null && !messageObj.m14777o().equals(ChatDialogActivity.this.f9835K0.m14777o())) {
                                ChatDialogActivity.this.f9835K0 = messageObj;
                            }
                        }
                        if (ChatDialogActivity.this.getApplicationContext().getSharedPreferences("U", 0).getBoolean(ChatDialogActivity.f9791W2, ChatDialogActivity.f9792X2)) {
                            if (ChatDialogActivity.this.f9943n0 == null) {
                                ChatDialogActivity.this.f9943n0 = new C5140e0();
                                ChatDialogActivity.this.f9943n0.m20025q(5);
                            }
                            if (!ChatDialogActivity.this.f9877V && SystemClock.uptimeMillis() - ChatDialogActivity.this.f9951p0 > AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS && ChatDialogActivity.this.f9943n0.m20019k(ChatDialogActivity.this.f9947o0, false)) {
                                ChatDialogActivity.this.f9951p0 = SystemClock.uptimeMillis();
                            }
                        }
                    }
                    ChatDialogActivity.this.f9810E.add(messageObj);
                }
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: d */
        public void mo11921d(C2904l c2904l) {
            if (c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                String strM14446v = c2904l.m14446v();
                ULogUtility.m16670f("ChatDialogActivity", "[onMessageUpdate] message id : " + strM14446v);
                final MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(strM14446v);
                if (messageObjM11608v7 != null) {
                    ULogUtility.m16670f("ChatDialogActivity", "[onMessageUpdate] update status and sending time in message list");
                    messageObjM11608v7.m14762X("0");
                    if (c2904l.m14386A() != null) {
                        messageObjM11608v7.m14761W(c2904l.m14386A());
                    }
                }
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.n3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22375b.m11917k(messageObjM11608v7);
                    }
                });
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: e */
        public String mo8240e(C2904l c2904l) {
            return c2904l.m14399N() ? m11922q(c2904l) : c2904l.m14391F() ? m11923r(c2904l) : c2904l.m14389D() == MessageObj.MessageType.Event ? m11924s(c2904l) : c2904l.m14398M() ? m11926v(c2904l) : m11925u(c2904l);
        }

        /* renamed from: q */
        public final String m11922q(C2904l c2904l) {
            if (XMPPArchiveHelper.m14138o()) {
                String strM14428m = c2904l.m14428m();
                if (strM14428m != null && strM14428m.equals(ChatDialogActivity.this.f9914g.f13723j) && !c2904l.m14397L()) {
                    ChatDialogActivity.this.m11514V9(c2904l);
                }
                return ChatDialogActivity.class.toString();
            }
            synchronized (ChatDialogActivity.this.f9931k0) {
                ChatDialogActivity.this.f9931k0.add(c2904l);
            }
            if (ChatDialogActivity.this.f9910f == null || !TextUtils.equals(c2904l.m14418h(), ChatDialogActivity.this.f9910f) || c2904l.m14389D() != MessageObj.MessageType.DeliveryReceipt) {
                return null;
            }
            ChatDialogActivity.this.m11510U9(c2904l);
            return null;
        }

        /* renamed from: r */
        public final String m11923r(C2904l c2904l) {
            String strM7587o0 = Globals.m7388i0().m7587o0();
            if ((c2904l.m14418h().equals(ChatDialogActivity.this.f9910f) && c2904l.m14388C().equals(strM7587o0)) || (c2904l.m14418h().equals(strM7587o0) && c2904l.m14388C().equals(ChatDialogActivity.this.f9910f))) {
                final MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(ChatDialogActivity.this.f9914g.f13727n));
                ULogUtility.m16664G("[ChatDialogActivity] onReceive: messageId=" + c2904l.m14446v(), "Receive");
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.q3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22403b.m11918l(messageObjM14449w0);
                    }
                });
                ChatDialogActivity.this.m11484Ob();
            }
            if (c2904l.m14389D() != MessageObj.MessageType.DeliveryReceipt || !c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                return null;
            }
            ChatDialogActivity.this.m11510U9(c2904l);
            return null;
        }

        /* renamed from: s */
        public final String m11924s(C2904l c2904l) {
            if (!c2904l.m14418h().equals(ChatDialogActivity.this.f9910f) || ChatDialogActivity.this.f9877V) {
                return null;
            }
            return ChatDialogActivity.class.toString();
        }

        /* renamed from: u */
        public final String m11925u(C2904l c2904l) throws NumberFormatException {
            if (!c2904l.m14418h().equals(ChatDialogActivity.this.f9910f) && (!c2904l.m14418h().equals(Globals.m7388i0().m7587o0()) || !c2904l.m14388C().equals(ChatDialogActivity.this.f9910f))) {
                return null;
            }
            if (c2904l.m14389D() == MessageObj.MessageType.DeliveryReceipt) {
                ChatDialogActivity.this.m11510U9(c2904l);
                return ChatDialogActivity.class.toString();
            }
            if (c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                ChatDialogActivity.this.m11484Ob();
            }
            MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(ChatDialogActivity.this.f9914g.f13727n));
            if (messageObjM14449w0.m14778p().equals(MessageObj.MessageType.Call)) {
                String strM14747I = messageObjM14449w0.m14747I("callerId");
                String strM14747I2 = messageObjM14449w0.m14747I("callType");
                if (!C5170o0.m20170e(strM14747I2) && strM14747I2.equals("voice") && strM14747I != null && !strM14747I.equals(ChatDialogActivity.this.f9914g.f13723j) && !strM14747I.equals(Globals.m7388i0().m7587o0())) {
                    return ChatDialogActivity.class.toString();
                }
            }
            ULogUtility.m16664G("[ChatDialogActivity] onReceive: messageId=" + c2904l.m14446v(), "Receive");
            String strM14745G = messageObjM14449w0.m14745G();
            Friend friendM11440A7 = ChatDialogActivity.this.m11440A7(strM14745G);
            if (friendM11440A7 == null) {
                friendM11440A7 = new FriendsClient().m15727f0(strM14745G);
            }
            m11927w(messageObjM14449w0, strM14745G, friendM11440A7);
            if (ChatDialogActivity.this.f9889Z != null && messageObjM14449w0.m14778p() == MessageObj.MessageType.Text && !c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                ChatDialogActivity.this.f9889Z.m12022s(messageObjM14449w0.m14779q());
            }
            if (ChatDialogActivity.this.f9877V || Globals.m7388i0().m7460N1()) {
                return null;
            }
            if (!c2904l.m14397L() && !c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1())) && ChatDialogActivity.this.f9795A0 == Tab.Chats) {
                ChatDialogActivity.this.m11514V9(c2904l);
            }
            return ChatDialogActivity.class.toString();
        }

        /* renamed from: v */
        public final String m11926v(C2904l c2904l) {
            final MessageObj messageObjM15179r;
            if (!c2904l.m14418h().equals(ChatDialogActivity.this.f9910f) || (messageObjM15179r = C2950b0.m14916o().m15179r(c2904l.m14446v())) == null || ChatDialogActivity.this.f9806D.containsKey(c2904l.m14446v())) {
                return null;
            }
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.p3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22394b.m11919n(messageObjM15179r);
                }
            });
            return null;
        }

        /* renamed from: w */
        public final void m11927w(final MessageObj messageObj, String str, Friend friend) {
            final String strM15621b = friend != null ? friend.m15621b() : "";
            final boolean zM7474Q1 = Globals.m7388i0().m7474Q1(str);
            ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.o3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22384b.m11920o(zM7474Q1, messageObj, strM15621b);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$p */
    public class AsyncTaskC1991p extends AsyncTask<Void, Void, List<ScheduleSendObj>> {
        public AsyncTaskC1991p() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<ScheduleSendObj> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("updateScheduleSendList AsyncTask");
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            return chatDialogActivity.m11614x7(chatDialogActivity.f9910f);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<ScheduleSendObj> list) {
            if (list == null || list.isEmpty()) {
                ChatDialogActivity.this.f9919h0.clear();
            } else {
                ChatDialogActivity.this.f9919h0.clear();
                ChatDialogActivity.this.f9919h0.addAll(list);
            }
            ChatDialogActivity.this.m11559gb();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$p0 */
    public class ViewOnClickListenerC1992p0 implements View.OnClickListener {
        public ViewOnClickListenerC1992p0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatDialogActivity.this.f9980w1 != null) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            ChatDialogActivity.this.m11511Ua(Tab.Bulletins, true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$p1 */
    public class AsyncTaskC1993p1 extends AsyncTask<Void, Void, C2973l0> {

        /* renamed from: a */
        public final /* synthetic */ long f10183a;

        /* renamed from: b */
        public final /* synthetic */ String f10184b;

        /* renamed from: c */
        public final /* synthetic */ ProgressDialog f10185c;

        /* renamed from: d */
        public final /* synthetic */ long f10186d;

        /* renamed from: e */
        public final /* synthetic */ boolean f10187e;

        /* renamed from: f */
        public final /* synthetic */ long f10188f;

        /* renamed from: g */
        public final /* synthetic */ String f10189g;

        /* renamed from: h */
        public final /* synthetic */ boolean f10190h;

        public AsyncTaskC1993p1(long j9, String str, ProgressDialog progressDialog, long j10, boolean z8, long j11, String str2, boolean z9) {
            this.f10183a = j9;
            this.f10184b = str;
            this.f10185c = progressDialog;
            this.f10186d = j10;
            this.f10187e = z8;
            this.f10188f = j11;
            this.f10189g = str2;
            this.f10190h = z9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C2973l0 doInBackground(Void... voidArr) {
            JSONArray jSONArrayM20196r;
            Thread.currentThread().setName("ChatDialogActivity_GetMediaObj AsyncTask");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("mediaId", String.valueOf(this.f10183a)));
            Pair<String, String> pairM15731j = ChatDialogActivity.this.f9899c0.m15731j("media", "mediaInfo", arrayList);
            String str = (String) pairM15731j.first;
            if (!"200".equals(str)) {
                Log.d("ChatDialogActivity", "statusCode = " + str);
                return null;
            }
            String strM14676c = this.f10184b;
            GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(this.f10184b);
            if (groupAlbumObjM15056i != null && groupAlbumObjM15056i.m14676c() != null && groupAlbumObjM15056i.m14676c().contains("Chat")) {
                strM14676c = groupAlbumObjM15056i.m14676c();
            }
            if ((groupAlbumObjM15056i != null || ChatDialogActivity.this.f9899c0.m15708O(strM14676c, String.valueOf(ChatDialogActivity.this.f9914g.f13727n)) != null) && (jSONArrayM20196r = C5172p.m20196r((String) pairM15731j.second)) != null) {
                for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                    try {
                        C2973l0 c2973l0M20188j = C5172p.m20188j(strM14676c, jSONArrayM20196r.getJSONObject(i9));
                        if (c2973l0M20188j != null) {
                            C2950b0.m14914m().m14712i(c2973l0M20188j);
                        }
                        C3061a.a aVarM20192n = C5172p.m20192n(jSONArrayM20196r.getJSONObject(i9));
                        if (!aVarM20192n.m15800a()) {
                            C2950b0.m14915n().m15107f(new C2971k0(String.valueOf(this.f10183a), aVarM20192n));
                        }
                        return c2973l0M20188j;
                    } catch (JSONException e9) {
                        Log.e("ChatDialogActivity", "", e9);
                    }
                }
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C2973l0 c2973l0) {
            ProgressDialog progressDialog = this.f10185c;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.f10185c.dismiss();
            }
            ChatDialogActivity.this.f9833J2 = false;
            if (c2973l0 != null) {
                ChatDialogActivity.this.m11530Z9(c2973l0.m15131c(), this.f10183a, this.f10186d, this.f10187e, this.f10188f, this.f10189g, this.f10190h);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$q */
    public class C1994q implements InterfaceC6190a0 {
        public C1994q() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m11935f(MessageObj messageObj) throws JSONException {
            MessageObj messageObjM11608v7;
            synchronized (ChatDialogActivity.this.f9862R) {
                ULogUtility.m16670f("ChatDialogActivity", "send message fail id:" + messageObj.m14777o() + " content:" + messageObj.m14779q());
                if (ChatDialogActivity.this.f9806D.containsKey(messageObj.m14777o()) && (messageObjM11608v7 = ChatDialogActivity.this.m11608v7(messageObj.m14777o())) != null) {
                    messageObjM11608v7.m14762X("3");
                }
                if (ChatDialogActivity.this.f9810E != null) {
                    if (ChatDialogActivity.this.f9810E.f10041k != null) {
                        Iterator it = ChatDialogActivity.this.f9810E.f10041k.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            MessageObj messageObj2 = (MessageObj) it.next();
                            if (messageObj2 != null && messageObj2.m14777o() != null && messageObj2.m14777o().equals(messageObj.m14777o())) {
                                messageObj2.m14762X("3");
                                break;
                            }
                        }
                    }
                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                }
            }
            m11939e(messageObj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m11936g(MessageObj messageObj) throws JSONException {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (ChatDialogActivity.this.f9810E != null && !ChatDialogActivity.this.f9806D.containsKey(messageObj.m14777o())) {
                    String strM14747I = messageObj.m14747I("calleeId");
                    String strM14747I2 = messageObj.m14747I("callerId");
                    if (strM14747I != null && strM14747I2 != null && (strM14747I.equals(ChatDialogActivity.this.f9914g.f13723j) || strM14747I2.equals(ChatDialogActivity.this.f9914g.f13723j))) {
                        ChatDialogActivity.this.f9810E.m11781W(messageObj);
                    } else if (messageObj.m14778p().equals(MessageObj.MessageType.Text) && messageObj.m14772j().equals(String.valueOf(ChatDialogActivity.this.f9914g.f13727n))) {
                        ChatDialogActivity.this.f9810E.m11781W(messageObj);
                    }
                }
            }
            m11939e(messageObj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m11937i(MessageObj messageObj) throws JSONException {
            synchronized (ChatDialogActivity.this.f9862R) {
                ULogUtility.m16670f("ChatDialogActivity", "send message success id:" + messageObj.m14777o() + " content:" + messageObj.m14779q());
                if (ChatDialogActivity.this.f9810E != null) {
                    if (Arrays.asList(MessageObj.MessageType.DeleteMedia, MessageObj.MessageType.FileDelete, MessageObj.MessageType.VideoDelete, MessageObj.MessageType.CreateMedia).contains(messageObj.m14778p())) {
                        ChatDialogActivity.this.f9810E.m11763Q(messageObj, false);
                        ChatDialogActivity.this.m11609va(messageObj);
                    }
                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                }
            }
            m11939e(messageObj);
        }

        @Override // p182r2.InterfaceC6190a0
        /* renamed from: B0 */
        public void mo11938B0(final MessageObj messageObj) {
            if (String.valueOf(ChatDialogActivity.this.f9914g.f13727n).equals(messageObj.m14772j())) {
                ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.q2
                    @Override // java.lang.Runnable
                    public final void run() throws JSONException {
                        this.f22401b.m11936g(messageObj);
                    }
                });
            }
        }

        /* renamed from: e */
        public final void m11939e(MessageObj messageObj) throws JSONException {
            if (messageObj.m14752N()) {
                ChatDialogActivity.this.f9818G.setVisibility(0);
                ChatDialogActivity.this.m11598sa(messageObj);
                CLUtility.m16558l2(ChatDialogActivity.this.f9910f, ChatDialogActivity.this.f9919h0);
            }
        }

        @Override // p182r2.InterfaceC6190a0
        /* renamed from: h */
        public void mo11940h(final MessageObj messageObj) {
            if (String.valueOf(ChatDialogActivity.this.f9914g.f13727n).equals(messageObj.m14772j())) {
                ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.r2
                    @Override // java.lang.Runnable
                    public final void run() throws JSONException {
                        this.f22412b.m11937i(messageObj);
                    }
                });
            }
        }

        @Override // p182r2.InterfaceC6190a0
        /* renamed from: v0 */
        public void mo11941v0(final MessageObj messageObj) {
            if (String.valueOf(ChatDialogActivity.this.f9914g.f13727n).equals(messageObj.m14772j())) {
                ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.s2
                    @Override // java.lang.Runnable
                    public final void run() throws JSONException {
                        this.f22424b.m11935f(messageObj);
                    }
                });
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$q0 */
    public class ViewOnClickListenerC1995q0 implements View.OnClickListener {
        public ViewOnClickListenerC1995q0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatDialogActivity.this.f9980w1 != null) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            ChatDialogActivity.this.m11511Ua(Tab.Poll, true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$q1 */
    public class AsyncTaskC1996q1 extends AsyncTask<Void, Void, GroupAlbumObj> {

        /* renamed from: a */
        public final /* synthetic */ String f10194a;

        /* renamed from: b */
        public final /* synthetic */ ProgressDialog f10195b;

        public AsyncTaskC1996q1(String str, ProgressDialog progressDialog) {
            this.f10194a = str;
            this.f10195b = progressDialog;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public GroupAlbumObj doInBackground(Void... voidArr) {
            Thread.currentThread().setName("ChatDialogActivity_GetAlbumObj AsyncTask");
            return ChatDialogActivity.this.f9899c0.m15708O(this.f10194a, String.valueOf(ChatDialogActivity.this.f9914g.f13727n));
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(GroupAlbumObj groupAlbumObj) {
            ProgressDialog progressDialog = this.f10195b;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.f10195b.dismiss();
            }
            ChatDialogActivity.this.f9841L2 = false;
            if (groupAlbumObj != null) {
                ChatDialogActivity.this.m11522X9();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$r */
    public class C1997r implements C3197a.b {

        /* renamed from: a */
        public final /* synthetic */ MessageObj f10197a;

        /* renamed from: b */
        public final /* synthetic */ String f10198b;

        public C1997r(MessageObj messageObj, String str) {
            this.f10197a = messageObj;
            this.f10198b = str;
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            this.f10197a.f12925B = false;
            Log.d("ChatDialogActivity", "download fail");
            C5187v0.m20268d(ChatDialogActivity.this.getString(R.string.error_server_response));
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) throws IllegalStateException, IOException, SecurityException {
            this.f10197a.f12925B = false;
            Log.d("ChatDialogActivity", "download success");
            MediaScannerConnection.scanFile(Globals.m7388i0().getApplicationContext(), new String[]{str}, null, null);
            try {
                ChatDialogActivity.this.m11578lb(this.f10197a, this.f10198b);
            } catch (IllegalArgumentException e9) {
                e9.printStackTrace();
            }
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(int i9, int i10, int i11) {
            this.f10197a.f12925B = true;
            Log.d("ChatDialogActivity", "download progress=" + i9);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$r0 */
    public class ViewOnClickListenerC1998r0 implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$r0$a */
        public class a extends AbstractC6381r<Void, Void> {

            /* renamed from: c */
            public final /* synthetic */ DialogC3133q f10201c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Handler handler, DialogC3133q dialogC3133q) {
                super(handler);
                this.f10201c = dialogC3133q;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Void r12) {
                this.f10201c.dismiss();
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: l, reason: merged with bridge method [inline-methods] */
            public void m24504h(Void r12) {
                this.f10201c.dismiss();
            }
        }

        public ViewOnClickListenerC1998r0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DialogC3133q dialogC3133qM16411b = new DialogC3133q.b(ChatDialogActivity.this).m16411b();
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "AddToHome");
            ChatDialogActivity.this.f9980w1.dismiss();
            ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
            C6675j5.m25303d(chatDialogActivity, chatDialogActivity.f9914g, new a(ChatDialogActivity.this.m7366I0(), dialogC3133qM16411b));
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$r1 */
    public static /* synthetic */ class C1999r1 {

        /* renamed from: a */
        public static final /* synthetic */ int[] f10203a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f10204b;

        static {
            int[] iArr = new int[MessageObj.MessageType.values().length];
            f10204b = iArr;
            try {
                iArr[MessageObj.MessageType.Sticker.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10204b[MessageObj.MessageType.AnimSticker.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10204b[MessageObj.MessageType.AnimPngSticker.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10204b[MessageObj.MessageType.CreateMedia.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10204b[MessageObj.MessageType.Call.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10204b[MessageObj.MessageType.Event.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10204b[MessageObj.MessageType.Poll.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10204b[MessageObj.MessageType.PollPost.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10204b[MessageObj.MessageType.VideoDelete.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f10204b[MessageObj.MessageType.FileDelete.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f10204b[MessageObj.MessageType.DeleteMedia.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr2 = new int[Tab.values().length];
            f10203a = iArr2;
            try {
                iArr2[Tab.Chats.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f10203a[Tab.Albums.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f10203a[Tab.Bulletins.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f10203a[Tab.Poll.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$s */
    public class C2000s implements C5140e0.a {
        public C2000s() {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: a */
        public void mo9127a(int i9) {
            if (i9 == 1) {
                ChatDialogActivity.this.getWindow().addFlags(128);
                return;
            }
            ChatDialogActivity.this.getWindow().clearFlags(128);
            if (ChatDialogActivity.this.f9911f0 != null && ChatDialogActivity.this.f9911f0.m14753O()) {
                ChatDialogActivity.this.f9810E.m11786X1(ChatDialogActivity.this.f9911f0);
            }
            ChatDialogActivity.this.f9810E.notifyDataSetChanged();
            if (ChatDialogActivity.this.f9903d0 != null) {
                ChatDialogActivity.this.f9903d0.cancel();
                ChatDialogActivity.this.f9903d0 = null;
            }
            ChatDialogActivity.this.f9911f0 = null;
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: b */
        public void mo9128b() {
            if (ChatDialogActivity.this.f9903d0 != null) {
                ChatDialogActivity.this.f9903d0.cancel();
                ChatDialogActivity.this.f9903d0 = null;
            }
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: c */
        public void mo9129c(int i9) {
            if (ChatDialogActivity.this.f9903d0 != null) {
                ChatDialogActivity.this.f9903d0.cancel();
                ChatDialogActivity.this.f9903d0 = null;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$s0 */
    public class C2001s0 implements UploadMultipleChatMediaHelperQueue.InterfaceC3194g {
        public C2001s0() {
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: G */
        public void mo6940G(String str) {
            UploadUtils.m16965l("ChatDialogActivity", "[onSmallPhotoComplete] in");
            m11946b(str);
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: K */
        public void mo6941K(String str) {
            UploadUtils.m16965l("ChatDialogActivity", "[onFileComplete] in ");
            m11946b(str);
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: U */
        public void mo6942U(String str) {
            UploadUtils.m16965l("ChatDialogActivity", "[onVideoComplete] in ");
            m11946b(str);
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: W */
        public void mo6943W(String str) {
            UploadUtils.m16965l("ChatDialogActivity", "[onVoiceComplete] in ");
            m11946b(str);
        }

        /* renamed from: b */
        public final void m11946b(String str) {
            if (str == null) {
                UploadUtils.m16965l("ChatDialogActivity", "[mUploadCallback::updateMsgObj] messageId == null");
                return;
            }
            UploadUtils.m16965l("ChatDialogActivity", "[mUploadCallback::updateMsgObj] messageId = " + str);
            MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(str);
            if (messageObjM11608v7 == null) {
                UploadUtils.m16965l("ChatDialogActivity", "[mUploadCallback::updateMsgObj] Can't get message object. Message Id=" + str);
                return;
            }
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
            if (messageObjM15179r != null) {
                messageObjM11608v7.m14771g0(messageObjM15179r);
                return;
            }
            UploadUtils.m16965l("ChatDialogActivity", "[mUploadCallback::updateMsgObj] Can't get message object from DB. Message Id=" + str);
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: f0 */
        public void mo6945f0(String str, int i9) {
            UploadUtils.m16965l("ChatDialogActivity", "[onBeginUploading] in: Message id:'" + str + "', progress:'" + i9 + "'");
            m11946b(str);
            MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(str);
            if (messageObjM11608v7 != null) {
                messageObjM11608v7.f12924A = true;
                messageObjM11608v7.f12959z = i9;
            } else {
                UploadUtils.m16965l("ChatDialogActivity", "[onBeginUpload] Can't get message object. Message Id=" + str);
            }
            ChatDialogActivity.this.m11478Mb();
            ChatDialogActivity.this.f9884X0 = System.currentTimeMillis();
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: m */
        public void mo6946m(C2973l0 c2973l0, String str) {
            Log.d("ChatDialogActivity", "[uploadPhotoTimer] total time = " + (System.currentTimeMillis() - ChatDialogActivity.this.f9884X0) + "ms");
            UploadUtils.m16965l("ChatDialogActivity", "[onComplete] in");
            m11946b(str);
            UploadUtils.m16965l("ChatDialogActivity", "[onComplete] get messageObj(" + str + ") from mMapMessageList");
            MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(str);
            if (messageObjM11608v7 != null) {
                messageObjM11608v7.f12924A = false;
                UploadUtils.m16965l("ChatDialogActivity", "[onComplete] get messageObj getUploadStatus is " + messageObjM11608v7.m14744F());
            }
            if (messageObjM11608v7 != null && messageObjM11608v7.m14778p() == MessageObj.MessageType.Video && C2925v.m14604k0(messageObjM11608v7)) {
                String strM14582Z = C2925v.m14582Z(messageObjM11608v7, c2973l0);
                UploadUtils.m16965l("ChatDialogActivity", "[onComplete] isServerTranscode, put  messageObj into map list with new message id");
                ChatDialogActivity.this.f9806D.put(strM14582Z, messageObjM11608v7);
            }
            UploadUtils.m16965l("ChatDialogActivity", "[onComplete] start to updatePhoto UI ");
            ChatDialogActivity.this.m11478Mb();
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: t */
        public void mo6947t(String str, int i9) {
            UploadUtils.m16965l("ChatDialogActivity", "[onProgress]  in. messageId =" + str + ", Progress=" + i9);
            if (ChatDialogActivity.this.f9810E == null || str == null) {
                UploadUtils.m16965l("ChatDialogActivity", "[onProgress] mMessageAdapter == null || messageId == null");
                return;
            }
            MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(str);
            if (messageObjM11608v7 == null) {
                UploadUtils.m16965l("ChatDialogActivity", "[onProgress] Can't get message object. Message Id=" + str);
                return;
            }
            UploadUtils.m16965l("ChatDialogActivity", "[onProgress] messageId =" + str + ", Progress=" + i9);
            int iM11756N0 = ChatDialogActivity.this.f9810E.m11756N0(messageObjM11608v7);
            messageObjM11608v7.f12924A = true;
            messageObjM11608v7.f12959z = i9;
            ChatDialogActivity.this.m11481Nb(iM11756N0, i9);
        }

        @Override // com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue.InterfaceC3194g
        /* renamed from: t0 */
        public void mo6948t0(String str) {
            m11946b(str);
            ChatDialogActivity.this.m11478Mb();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$s1 */
    public class C2002s1 implements XMPPManager.InterfaceC2849a0 {
        public C2002s1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m11949d(String str, Date date) {
            synchronized (ChatDialogActivity.this.f9862R) {
                MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(str);
                if (messageObjM11608v7 == null) {
                    ULogUtility.m16670f("ChatDialogActivity", "[send] cannot find message when ack : " + str);
                    return;
                }
                ULogUtility.m16670f("ChatDialogActivity", "[send] start to update UI when ack : " + str);
                if (date != null) {
                    messageObjM11608v7.m14761W(date);
                }
                messageObjM11608v7.m14762X("0");
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.m11741F1(messageObjM11608v7);
                    ChatDialogActivity.this.m11609va(messageObjM11608v7);
                }
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                    return;
                }
                ULogUtility.m16670f("ChatDialogActivity", "[send] does not update UI when ack : " + str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m11950e(String str) {
            synchronized (ChatDialogActivity.this.f9862R) {
                MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(str);
                if (messageObjM11608v7 == null) {
                    ULogUtility.m16670f("ChatDialogActivity", "[send] cannot find message when onNACK : " + str);
                    return;
                }
                ULogUtility.m16670f("ChatDialogActivity", "[send] start to update UI when onNACK : " + str);
                messageObjM11608v7.m14762X("3");
                if (ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                }
                ChatDialogActivity.this.m11516W6();
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: F */
        public void mo5716F(final String str) {
            ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.l3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22349b.m11950e(str);
                }
            });
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: p */
        public void mo5718p(final String str, final Date date) {
            ChatDialogActivity.this.runOnUiThread(new Runnable() { // from class: y2.m3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22359b.m11949d(str, date);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$t */
    public class ViewOnClickListenerC2003t implements View.OnClickListener {
        public ViewOnClickListenerC2003t() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m11955e(List list, DialogInterface dialogInterface, int i9) {
            ChatDialogActivity.this.f9810E.m11735A0(list);
            ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
        }

        /* renamed from: f */
        public static /* synthetic */ void m11956f(DialogInterface dialogInterface, int i9) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m11957g(List list, DialogInterface dialogInterface, int i9) {
            ChatDialogActivity.this.f9810E.m11740F0(list);
            ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
        }

        /* renamed from: h */
        public static /* synthetic */ void m11958h(String str, String str2, String str3, String str4) {
            if ("200".equals(str3)) {
                CLUtility.m16527e(Globals.m7372O(), C5172p.m20198t(str4));
                ULogUtility.m16680p("ChatDialogActivity", "update myself info success");
            } else {
                ULogUtility.m16676l("ChatDialogActivity", "update myself info fail statusCode:" + str3);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws JSONException, NumberFormatException {
            final ArrayList arrayList = new ArrayList();
            SparseBooleanArray checkedItemPositions = ChatDialogActivity.this.f9946o.getCheckedItemPositions();
            int headerViewsCount = ChatDialogActivity.this.f9946o.getHeaderViewsCount();
            for (int i9 = 0; i9 < ChatDialogActivity.this.f9810E.getCount() && checkedItemPositions.size() > 0 && arrayList.size() != checkedItemPositions.size(); i9++) {
                if (checkedItemPositions.get(i9 + headerViewsCount)) {
                    arrayList.add(ChatDialogActivity.this.f9810E.getItem(i9));
                }
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.DELETE)) {
                AlertDialog.Builder builderM16382a = C3123g.m16382a(ChatDialogActivity.this);
                builderM16382a.setTitle(ChatDialogActivity.this.getString(R.string.delete_message_title));
                builderM16382a.setMessage(ChatDialogActivity.this.getString(R.string.delete_message_content_in_your_side));
                builderM16382a.setPositiveButton(ChatDialogActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.t2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        this.f22432b.m11955e(arrayList, dialogInterface, i10);
                    }
                });
                builderM16382a.setNegativeButton(ChatDialogActivity.this.getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: y2.u2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        ChatDialogActivity.ViewOnClickListenerC2003t.m11956f(dialogInterface, i10);
                    }
                });
                builderM16382a.show();
                return;
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.RECALL)) {
                C3123g.m16382a(ChatDialogActivity.this).setTitle(R.string.recall_message_title).setMessage(R.string.recall_message_short).setPositiveButton(R.string.recall_message_btn, new DialogInterface.OnClickListener() { // from class: y2.v2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i10) {
                        this.f22453b.m11957g(arrayList, dialogInterface, i10);
                    }
                }).setNegativeButton(R.string.cancel_text, (DialogInterface.OnClickListener) null).show();
                return;
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.FORWARD)) {
                ChatDialogActivity.this.f9810E.m11737C0(arrayList);
                View viewFindViewById = ChatDialogActivity.this.f9826I.findViewById(R.id.senderNameToggle);
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(Globals.m7372O());
                if (userInfoM16497V0 == null || viewFindViewById.isSelected() != userInfoM16497V0.f13800y) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("profile.forward.include.sender", viewFindViewById.isSelected() ? "1" : "0");
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(new C6301o("token", Globals.m7388i0().m7449L()));
                        arrayList2.add(new C6301o("attrs", jSONObject.toString()));
                        ChatDialogActivity.this.f9899c0.m15734m("user", "updateUser", arrayList2, new FriendsClient.InterfaceC3051i() { // from class: y2.w2
                            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                            /* renamed from: a */
                            public final void mo134a(String str, String str2, String str3, String str4) {
                                ChatDialogActivity.ViewOnClickListenerC2003t.m11958h(str, str2, str3, str4);
                            }
                        });
                    } catch (Exception e9) {
                        ULogUtility.m16676l("ChatDialogActivity", "update myself info fail exception:" + e9.getMessage());
                    }
                }
                ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
                return;
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.FORWARD_PHOTO_WITH_COMMENT)) {
                ChatDialogActivity.this.f9810E.m11738D0(arrayList);
                ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
                return;
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_DEVICE)) {
                ChatDialogActivity.this.f9810E.m11746I0(arrayList);
                ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
                return;
            }
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_ALBUM)) {
                ChatDialogActivity.this.f9810E.m11744H0(arrayList, true);
                ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
            } else if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.SAVE_TO_GROUP_ALBUM)) {
                ChatDialogActivity.this.f9810E.m11744H0(arrayList, false);
                ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
            } else if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.ADD_TO_TODO)) {
                ChatDialogActivity.this.f9810E.m11829x0(arrayList);
                ChatDialogActivity.this.m11600t2(ChatDialogMode.NORMAL, 0);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$t0 */
    public class ViewOnClickListenerC2004t0 implements View.OnClickListener {
        public ViewOnClickListenerC2004t0() {
        }

        /* renamed from: d */
        public static /* synthetic */ void m11962d(Dialog dialog, View view) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* renamed from: e */
        public static /* synthetic */ void m11963e(Friend friend, String str, String str2, String str3, String str4) {
            if (str3 == null || !str3.equals("200")) {
                Log.d("ChatDialogActivity", "Unblock Friend Fail");
                return;
            }
            Log.d("ChatDialogActivity", "Unblock Friend " + friend.m15621b() + FirebaseAnalytics.Param.SUCCESS);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m11964f(Dialog dialog, final Friend friend, View view) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("userId", Long.toString(friend.f13645c)));
            ChatDialogActivity.this.f9899c0.m15734m("friend", "unblockFriend", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.g3
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    ChatDialogActivity.ViewOnClickListenerC2004t0.m11963e(friend, str, str2, str3, str4);
                }
            });
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final Friend friend;
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Block");
            if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual")) {
                if (ChatDialogActivity.this.f9927j0.size() != 2) {
                    Log.d("ChatDialogActivity", "[mOnEditNameClick]The number of group member is not for dual : " + ChatDialogActivity.this.f9927j0.size());
                    return;
                }
                Iterator it = ChatDialogActivity.this.f9927j0.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        friend = null;
                        break;
                    } else {
                        friend = (Friend) it.next();
                        if (friend.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                            break;
                        }
                    }
                }
                if (friend == null || friend.f13655m) {
                    final Dialog dialogM16384c = C3123g.m16384c(ChatDialogActivity.this);
                    dialogM16384c.setContentView(R.layout.dialog_remove_friend_confirm);
                    ((TextView) dialogM16384c.findViewById(R.id.title)).setText(R.string.dialog_unblock_friend_title);
                    ((TextView) dialogM16384c.findViewById(R.id.warning_message)).setText(R.string.dialog_unblock_friend_msg);
                    CLUtility.m16578q2(ChatDialogActivity.this.m11582n7(), dialogM16384c);
                    dialogM16384c.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: y2.e3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            ChatDialogActivity.ViewOnClickListenerC2004t0.m11962d(dialogM16384c, view2);
                        }
                    });
                    dialogM16384c.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() { // from class: y2.f3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            this.f22235b.m11964f(dialogM16384c, friend, view2);
                        }
                    });
                    dialogM16384c.show();
                } else {
                    Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) BlockUserAlertActivity.class);
                    intent.putExtra("friend", friend);
                    ChatDialogActivity.this.startActivityForResult(intent, 5);
                }
            }
            if (ChatDialogActivity.this.f9980w1 == null || !ChatDialogActivity.this.f9980w1.isShowing()) {
                return;
            }
            ChatDialogActivity.this.f9980w1.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$t1 */
    public class C2005t1 implements XMPPArchiveHelper.InterfaceC2846c {
        public C2005t1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m11966e(List list) {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (ChatDialogActivity.this.f9810E != null) {
                    Log.d("ChatDialogActivity", "Sort archive message");
                    m11970f();
                    ChatDialogActivity.this.f9810E.m11766R(list);
                }
            }
        }

        @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2846c
        /* renamed from: a */
        public void mo11967a() {
            final ArrayList arrayList = new ArrayList();
            synchronized (ChatDialogActivity.this.f9931k0) {
                Iterator it = ChatDialogActivity.this.f9931k0.iterator();
                while (it.hasNext()) {
                    MessageObj messageObjM11449D7 = ChatDialogActivity.this.m11449D7((C2904l) it.next());
                    if (messageObjM11449D7 != null) {
                        arrayList.add(messageObjM11449D7);
                    }
                }
                ChatDialogActivity.this.f9931k0.clear();
            }
            if (arrayList.isEmpty()) {
                return;
            }
            ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.r3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22414b.m11966e(arrayList);
                }
            });
            ChatDialogActivity.this.m11484Ob();
        }

        @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2846c
        /* renamed from: b */
        public void mo11968b() {
            ChatDialogActivity.this.m11571jb(false);
        }

        @Override // com.cyberlink.you.chat.XMPPArchiveHelper.InterfaceC2846c
        /* renamed from: c */
        public void mo11969c() {
            ChatDialogActivity.this.m11571jb(true);
        }

        /* renamed from: f */
        public final void m11970f() {
            int count = (ChatDialogActivity.this.f9810E.getCount() - 1) + ChatDialogActivity.this.f9946o.getHeaderViewsCount();
            if (count <= ChatDialogActivity.this.f9989y2 - 1 || count != ChatDialogActivity.this.f9946o.getLastVisiblePosition()) {
                return;
            }
            ChatDialogActivity.this.m11593qb(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$u */
    public class C2006u implements AdapterView.OnItemClickListener {
        public C2006u() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            View view2;
            if (ChatDialogActivity.this.f9983x0.equals(ChatDialogMode.NORMAL) || (view2 = ((C6819c) view.getTag()).f22596L) == null) {
                return;
            }
            view2.setSelected(ChatDialogActivity.this.f9946o.isItemChecked(i9));
            ChatDialogActivity.this.m11454Eb();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$u0 */
    public class ViewOnClickListenerC2007u0 implements View.OnClickListener {
        public ViewOnClickListenerC2007u0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Edit");
            if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual")) {
                if (ChatDialogActivity.this.f9927j0.size() != 2) {
                    Log.d("ChatDialogActivity", "[mOnEditNameClick]The number of group member is not for dual : " + ChatDialogActivity.this.f9927j0.size());
                    return;
                }
                Friend friend = null;
                for (Friend friend2 : ChatDialogActivity.this.f9927j0.values()) {
                    if (friend2.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                        friend = friend2;
                    }
                }
                Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) FriendInfoUpdateActivity.class);
                intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, ChatDialogActivity.this.getResources().getString(R.string.friend_change_title_firend_nikname));
                intent.putExtra(FirebaseAnalytics.Param.TERM, "nickname");
                intent.putExtra("data", friend);
                ChatDialogActivity.this.startActivityForResult(intent, 5002);
            }
            if (ChatDialogActivity.this.f9980w1 == null || !ChatDialogActivity.this.f9980w1.isShowing()) {
                return;
            }
            ChatDialogActivity.this.f9980w1.dismiss();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$u1 */
    public class AsyncTaskC2008u1 extends AsyncTask<Void, Void, Map<String, Object>> {
        public AsyncTaskC2008u1() {
        }

        @Override // android.os.AsyncTask
        @SuppressLint({"UseSparseArrays"})
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map<String, Object> doInBackground(Void... voidArr) throws IOException {
            CLUtility.m16423C();
            CLUtility.m16419B();
            XMPPArchiveHelper.m14132i(ChatDialogActivity.this.f9904d1);
            XMPPManager.m14184g0().m14211K(ChatDialogActivity.this.f9896b1);
            C6196d0.m23692d().m23693b(ChatDialogActivity.this.f9944n1);
            UploadMultipleChatMediaHelperQueue.m16892F().m16942u(ChatDialogActivity.this.f9887Y0);
            ChatDialogActivity.this.m11537b8();
            ChatDialogActivity.this.m11549e8();
            ChatDialogActivity.this.m11592qa();
            C2950b0.m14916o().m15171j(ChatDialogActivity.this.f9914g.f13727n);
            return ChatDialogActivity.this.m11601t7();
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Map<String, Object> map) {
            ChatDialogActivity.this.m11541c8();
            if (ChatDialogActivity.this.f9923i0) {
                ChatDialogActivity.this.m11484Ob();
            }
            if (XMPPArchiveHelper.m14139p()) {
                ChatDialogActivity.this.m11571jb(true);
            }
            if (Group.m15743f(ChatDialogActivity.this.f9914g.f13716c)) {
                ChatDialogActivity.this.m11585o7();
            }
            ChatDialogActivity.this.m11569j8(map);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$v */
    public class ViewOnClickListenerC2009v implements View.OnClickListener {
        public ViewOnClickListenerC2009v() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C6566a.m25157p(ChatDialogActivity.this.f9914g.f13716c, "Forums");
            if (ChatDialogActivity.this.f9980w1 != null && ChatDialogActivity.this.f9980w1.isShowing()) {
                ChatDialogActivity.this.f9980w1.dismiss();
            }
            ChatDialogActivity.this.m11511Ua(Tab.Bulletins, true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$v0 */
    public class C2010v0 implements AdapterView.OnItemClickListener {
        public C2010v0() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (i9 > 0) {
                try {
                    GroupAlbumObj groupAlbumObj = (GroupAlbumObj) ChatDialogActivity.this.f9868S1.getItem(i9 - ((ListView) adapterView).getHeaderViewsCount());
                    if (groupAlbumObj != null) {
                        Intent intent = new Intent(ChatDialogActivity.this, (Class<?>) PhotoListActivity.class);
                        intent.putExtra("ShowShareToMyAlbum", true);
                        intent.putExtra("album", groupAlbumObj);
                        intent.putStringArrayListExtra("album_name_list", ChatDialogActivity.this.m11588p7());
                        ChatDialogActivity.this.startActivityForResult(intent, 15);
                    }
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$v1 */
    public class AsyncTaskC2011v1 extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public MessageObj f10216a;

        public AsyncTaskC2011v1(MessageObj messageObj) {
            this.f10216a = messageObj;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            Thread.currentThread().setName("[DeleteSendMediaTask] AsyncTask");
            try {
                if (this.f10216a.m14740B().equals("0")) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                    arrayList.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, this.f10216a.m14777o()));
                    String str = (String) ChatDialogActivity.this.f9899c0.m15731j("chat", "deleteMessage", arrayList).first;
                    UploadUtils.m16965l("ChatDialogActivity", "[CancelSendMediaTask] statusCode = " + str);
                    if (str == null || !(str.equals("200") || str.equals("400"))) {
                        return Boolean.FALSE;
                    }
                    if (C2925v.m14594f0(this.f10216a)) {
                        UploadMultipleChatMediaHelperQueue.m16892F().m16918C(this.f10216a.m14777o());
                    }
                } else if (C2925v.m14594f0(this.f10216a)) {
                    UploadMultipleChatMediaHelperQueue.m16892F().m16918C(this.f10216a.m14777o());
                }
                return Boolean.TRUE;
            } catch (Exception e9) {
                UploadUtils.m16965l("ChatDialogActivity", "[DeleteSendMediaTask] Exception = " + e9.getMessage());
                return Boolean.FALSE;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                synchronized (ChatDialogActivity.this.f9862R) {
                    MessageObj messageObj = this.f10216a;
                    if (messageObj != null) {
                        try {
                            String strM14777o = messageObj.m14777o();
                            C2950b0.m14916o().m15169h(strM14777o);
                            UploadUtils.m16965l("ChatDialogActivity", "[CancelSendMediaTask][onPostExecute] messageId = " + strM14777o);
                            MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(strM14777o);
                            if (messageObjM11608v7 != null && ChatDialogActivity.this.f9810E != null) {
                                ChatDialogActivity.this.f9810E.remove(messageObjM11608v7);
                            }
                        } catch (Exception e9) {
                            UploadUtils.m16965l("ChatDialogActivity", "[DeleteSendMediaTask][onPostExecute] e = " + e9.getMessage());
                        }
                    }
                }
            }
            this.f10216a = null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$w */
    public class AsyncTaskC2012w extends AsyncTask<Void, Void, List<MessageObj>> {

        /* renamed from: a */
        public final /* synthetic */ boolean f10218a;

        /* renamed from: b */
        public final /* synthetic */ boolean f10219b;

        public AsyncTaskC2012w(boolean z8, boolean z9) {
            this.f10218a = z8;
            this.f10219b = z9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<MessageObj> doInBackground(Void... voidArr) {
            MessageObj item;
            long jCurrentTimeMillis = System.currentTimeMillis();
            List<MessageObj> listM15185x = null;
            if (ChatDialogActivity.this.f9810E != null && ChatDialogActivity.this.f9914g != null) {
                if (ChatDialogActivity.this.f9810E.isEmpty()) {
                    listM15185x = C2950b0.m14916o().m15180s(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), 100);
                } else if (this.f10218a) {
                    item = ChatDialogActivity.this.f9810E.m11826u0() > 0 ? ChatDialogActivity.this.f9810E.getItem(ChatDialogActivity.this.f9810E.m11826u0() - 1) : null;
                    if (item != null) {
                        listM15185x = C2950b0.m14916o().m15185x(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), String.valueOf(item.m14788z().getTime()), !this.f10218a);
                    }
                } else {
                    for (int i9 = 0; i9 < ChatDialogActivity.this.f9810E.getCount(); i9++) {
                        if (ChatDialogActivity.this.f9810E.getItem(i9).m14778p() != MessageObj.MessageType.Date) {
                            item = ChatDialogActivity.this.f9810E.getItem(i9);
                            break;
                        }
                    }
                    if (item != null && item.m14788z() != null) {
                        listM15185x = C2950b0.m14916o().m15185x(String.valueOf(ChatDialogActivity.this.f9914g.f13727n), String.valueOf(item.m14788z().getTime()), !this.f10218a);
                    }
                }
            }
            if (listM15185x != null) {
                Collections.sort(listM15185x, MessageObj.f12923K);
            }
            Log.d("ChatDialogActivity", "query message list " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
            return listM15185x;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<MessageObj> list) {
            if (list == null) {
                return;
            }
            if (!list.isEmpty() && ChatDialogActivity.this.f9810E != null && ChatDialogActivity.this.f9795A0 == Tab.Chats) {
                int iM11823r0 = ChatDialogActivity.this.f9810E.m11823r0(ChatDialogActivity.this.f9810E.getCount() - 1);
                ChatDialogActivity.this.f9810E.m11766R(list);
                int iM11823r02 = ChatDialogActivity.this.f9810E.m11823r0(ChatDialogActivity.this.f9810E.getCount() - 1);
                if (this.f10218a) {
                    ChatDialogActivity.this.f9946o.setSelection(iM11823r0);
                } else {
                    ChatDialogActivity.this.f9946o.setSelection(iM11823r02 - iM11823r0);
                }
            }
            if (ChatDialogActivity.this.f9810E != null && ChatDialogActivity.this.f9795A0 == Tab.Chats && ChatDialogActivity.this.f9810E.getCount() == 0 && !ChatDialogActivity.this.f9914g.f13711J) {
                ChatDialogActivity.this.f9854P.setVisibility(0);
            }
            if (!this.f10219b) {
                ChatDialogActivity.this.m11559gb();
            }
            ChatDialogActivity.this.f9987y0 = null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$w0 */
    public class AsyncTaskC2013w0 extends AsyncTask<Void, Void, List<GroupAlbumObj>> {
        public AsyncTaskC2013w0() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<GroupAlbumObj> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("initAlbumList");
            return C2950b0.m14911j().m15057j(String.valueOf(ChatDialogActivity.this.f9914g.f13727n));
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<GroupAlbumObj> list) {
            if (list == null) {
                return;
            }
            if (ChatDialogActivity.this.f9868S1 == null) {
                ChatDialogActivity.this.f9868S1 = new C3072a(ChatDialogActivity.this, R.layout.view_item_group_album, new ArrayList());
            }
            ChatDialogActivity.this.f9868S1.clear();
            ChatDialogActivity.this.f9868S1.addAll(list);
            ChatDialogActivity.this.m11466Ib();
            ChatDialogActivity.this.m11475Lb(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$w1 */
    public class C2014w1 extends C6773x5 {

        /* renamed from: n */
        public int f10222n;

        /* renamed from: o */
        public boolean f10223o;

        /* renamed from: p */
        public boolean f10224p;

        /* renamed from: q */
        public TextView f10225q;

        /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$w1$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
            }
        }

        public C2014w1(ListView listView, View view) {
            super(listView, view, ChatDialogActivity.this.f9950p);
            this.f10222n = -1;
            this.f10223o = true;
            this.f10224p = true;
            this.f10225q = (TextView) ChatDialogActivity.this.f9950p.findViewById(R.id.dateView);
            this.f10223o = view == null;
        }

        /* renamed from: m */
        public final void m11981m(AbsListView absListView) {
            if (ChatDialogActivity.this.f9866S) {
                this.f10224p = absListView.getLastVisiblePosition() >= absListView.getCount() - 1;
            }
        }

        /* renamed from: n */
        public boolean m11982n() {
            return this.f10224p;
        }

        /* renamed from: o */
        public final void m11983o() {
            View childAt = ChatDialogActivity.this.f9946o.getChildAt(0);
            if (childAt == null) {
                ChatDialogActivity.this.f9950p.setVisibility(4);
                return;
            }
            int height = childAt.getHeight() + childAt.getTop();
            float fM25320d = m25320d();
            int i9 = 0;
            while (height < fM25320d && (i9 = i9 + 1) < ChatDialogActivity.this.f9946o.getChildCount()) {
                height += ChatDialogActivity.this.f9946o.getChildAt(i9).getHeight();
            }
            if (ChatDialogActivity.this.f9946o.getFirstVisiblePosition() + i9 < ChatDialogActivity.this.f9946o.getAdapter().getCount()) {
                Object itemAtPosition = ChatDialogActivity.this.f9946o.getItemAtPosition(ChatDialogActivity.this.f9946o.getFirstVisiblePosition() + i9);
                if (!(itemAtPosition instanceof MessageObj)) {
                    ChatDialogActivity.this.f9950p.setVisibility(4);
                } else {
                    ChatDialogActivity.this.f9950p.setVisibility(0);
                    this.f10225q.setText(CLUtility.m16422B2(((MessageObj) itemAtPosition).m14788z()));
                }
            }
        }

        @Override // p245y2.C6773x5, android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            if (ChatDialogActivity.this.f9866S || !m11982n()) {
                super.onScroll(absListView, i9, i10, i11);
            } else {
                m25326j(0);
            }
            if (this.f10223o) {
                ChatDialogActivity.this.f9799B0.m25245a();
            }
            m11981m(absListView);
            if (i11 > 0) {
                this.f10222n = i9;
            }
            if (ChatDialogActivity.this.f9989y2 != i10) {
                ChatDialogActivity.this.f9989y2 = i10;
            }
            if (absListView.getLastVisiblePosition() == i11 - 1) {
                ChatDialogActivity.this.f9958r.m25313e();
            }
            Tab tab = Tab.Chats;
            if (tab.equals(ChatDialogActivity.this.f9795A0)) {
                m11983o();
            }
            if (ChatDialogActivity.this.f9866S && tab.equals(ChatDialogActivity.this.f9795A0)) {
                if (!ChatDialogActivity.this.m11565i8()) {
                    ChatDialogActivity.this.m11535ab(0, false);
                    return;
                }
                ChatDialogActivity.this.f9851O0.clear();
                ChatDialogActivity.this.m11535ab(8, false);
                ChatDialogActivity.this.f9855P0.animate().translationY(BitmapDescriptorFactory.HUE_RED).setDuration(300L).withStartAction(new a()).start();
            }
        }

        @Override // p245y2.C6773x5, android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            super.onScrollStateChanged(absListView, i9);
            if (i9 == 0) {
                if (Tab.Chats.equals(ChatDialogActivity.this.f9795A0)) {
                    ChatDialogActivity.this.f9950p.setAlpha(0.7f);
                    ChatDialogActivity.this.f9950p.animate().setStartDelay(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS).alpha(BitmapDescriptorFactory.HUE_RED).start();
                }
                if (Tab.Albums.equals(ChatDialogActivity.this.f9795A0)) {
                    ChatDialogActivity.this.m11475Lb(true);
                }
                if (this.f10222n == 0 || this.f10224p) {
                    ChatDialogActivity.this.m11580ma(this.f10224p, true);
                }
                ChatDialogActivity.this.f9866S = false;
                return;
            }
            Tab tab = Tab.Chats;
            if (tab.equals(ChatDialogActivity.this.f9795A0)) {
                ChatDialogActivity.this.f9950p.animate().setStartDelay(0L).alpha(0.7f).start();
                m11983o();
            }
            if (i9 == 1) {
                ChatDialogActivity.this.f9866S = true;
            }
            if (tab.equals(ChatDialogActivity.this.f9795A0)) {
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                if (!chatDialogActivity.m11561h8(chatDialogActivity.f9946o)) {
                    ChatDialogActivity.this.m11535ab(0, false);
                }
            }
            ChatDialogActivity.this.f9870T = true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$x */
    public class C2015x implements C5321e.m {
        public C2015x() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) throws NumberFormatException {
            String strM14428m;
            String str;
            if (c2904l != null) {
                String strM14446v = c2904l.m14446v();
                strM14428m = c2904l.m14428m();
                str = strM14446v;
            } else {
                strM14428m = null;
                str = null;
            }
            String str2 = map.get("eventType");
            str2.hashCode();
            char c9 = 65535;
            switch (str2.hashCode()) {
                case -2138412222:
                    if (str2.equals("user.status.updated")) {
                        c9 = 0;
                        break;
                    }
                    break;
                case -1936932574:
                    if (str2.equals("bulletin.topic.created")) {
                        c9 = 1;
                        break;
                    }
                    break;
                case -1868147997:
                    if (str2.equals("group.member.created")) {
                        c9 = 2;
                        break;
                    }
                    break;
                case -1681592280:
                    if (str2.equals("group.reminder.unset.event")) {
                        c9 = 3;
                        break;
                    }
                    break;
                case -1645150902:
                    if (str2.equals("group.group.created")) {
                        c9 = 4;
                        break;
                    }
                    break;
                case -1524614164:
                    if (str2.equals("friend.friend.blocked")) {
                        c9 = 5;
                        break;
                    }
                    break;
                case -1415024045:
                    if (str2.equals("bulletin.topic.deleted")) {
                        c9 = 6;
                        break;
                    }
                    break;
                case -1346239468:
                    if (str2.equals("group.member.deleted")) {
                        c9 = 7;
                        break;
                    }
                    break;
                case -1061631694:
                    if (str2.equals("group.member.leaved")) {
                        c9 = '\b';
                        break;
                    }
                    break;
                case -1009547174:
                    if (str2.equals("group.group.disabled")) {
                        c9 = '\t';
                        break;
                    }
                    break;
                case -912614478:
                    if (str2.equals("media.album.updated")) {
                        c9 = '\n';
                        break;
                    }
                    break;
                case -861363076:
                    if (str2.equals("meeting.meeting.start")) {
                        c9 = 11;
                        break;
                    }
                    break;
                case -826276900:
                    if (str2.equals("user.coverart.updated")) {
                        c9 = '\f';
                        break;
                    }
                    break;
                case -800201132:
                    if (str2.equals("media.media.created")) {
                        c9 = CharUtils.f19105CR;
                        break;
                    }
                    break;
                case -772340311:
                    if (str2.equals("user.avatar.updated")) {
                        c9 = 14;
                        break;
                    }
                    break;
                case -714851814:
                    if (str2.equals("group.admin.created")) {
                        c9 = 15;
                        break;
                    }
                    break;
                case -560772247:
                    if (str2.equals("bulletin.topic.sticked")) {
                        c9 = 16;
                        break;
                    }
                    break;
                case -471004516:
                    if (str2.equals("chat.message.deleted")) {
                        c9 = 17;
                        break;
                    }
                    break;
                case -292839295:
                    if (str2.equals("bulletin.post.created")) {
                        c9 = 18;
                        break;
                    }
                    break;
                case -278292603:
                    if (str2.equals("media.media.deleted")) {
                        c9 = 19;
                        break;
                    }
                    break;
                case -192943285:
                    if (str2.equals("group.admin.deleted")) {
                        c9 = 20;
                        break;
                    }
                    break;
                case 461331:
                    if (str2.equals("group.display.name.updated")) {
                        c9 = 21;
                        break;
                    }
                    break;
                case 11667875:
                    if (str2.equals("group.group.enabled")) {
                        c9 = 22;
                        break;
                    }
                    break;
                case 189290855:
                    if (str2.equals("group.member.created.v2")) {
                        c9 = 23;
                        break;
                    }
                    break;
                case 229069234:
                    if (str2.equals("bulletin.post.deleted")) {
                        c9 = 24;
                        break;
                    }
                    break;
                case 247054131:
                    if (str2.equals("friend.friend.unblocked")) {
                        c9 = 25;
                        break;
                    }
                    break;
                case 350370271:
                    if (str2.equals("media.album.created")) {
                        c9 = 26;
                        break;
                    }
                    break;
                case 454648247:
                    if (str2.equals("bulletin.poll.option.created")) {
                        c9 = 27;
                        break;
                    }
                    break;
                case 519222351:
                    if (str2.equals("media.comment.created")) {
                        c9 = 28;
                        break;
                    }
                    break;
                case 673160263:
                    if (str2.equals("user.display.name.updated")) {
                        c9 = 29;
                        break;
                    }
                    break;
                case 872278800:
                    if (str2.equals("media.album.deleted")) {
                        c9 = 30;
                        break;
                    }
                    break;
                case 879535989:
                    if (str2.equals("meeting.meeting.end")) {
                        c9 = 31;
                        break;
                    }
                    break;
                case 946682189:
                    if (str2.equals("chat.message.recalled")) {
                        c9 = ' ';
                        break;
                    }
                    break;
                case 1018813168:
                    if (str2.equals("friend.friend.broke.up")) {
                        c9 = '!';
                        break;
                    }
                    break;
                case 1041130880:
                    if (str2.equals("media.comment.deleted")) {
                        c9 = '\"';
                        break;
                    }
                    break;
                case 1045409518:
                    if (str2.equals("bulletin.topic.unliked")) {
                        c9 = '#';
                        break;
                    }
                    break;
                case 1095049973:
                    if (str2.equals("bulletin.topic.updated")) {
                        c9 = '$';
                        break;
                    }
                    break;
                case 1099704914:
                    if (str2.equals("bulletin.topic.lastRead")) {
                        c9 = '%';
                        break;
                    }
                    break;
                case 1179217906:
                    if (str2.equals("bulletin.topic.closed")) {
                        c9 = '&';
                        break;
                    }
                    break;
                case 1203126703:
                    if (str2.equals("group.media.created")) {
                        c9 = '\'';
                        break;
                    }
                    break;
                case 1386831645:
                    if (str2.equals("group.group.updated")) {
                        c9 = '(';
                        break;
                    }
                    break;
                case 1776528225:
                    if (str2.equals("group.reminder.set.event")) {
                        c9 = ')';
                        break;
                    }
                    break;
                case 1794212992:
                    if (str2.equals("media.transcode.failed")) {
                        c9 = '*';
                        break;
                    }
                    break;
                case 1846782000:
                    if (str2.equals("bulletin.topic.unsticked")) {
                        c9 = '+';
                        break;
                    }
                    break;
                case 2026580972:
                    if (str2.equals("group.notification.changed.event")) {
                        c9 = ',';
                        break;
                    }
                    break;
                case 2104043874:
                    if (str2.equals("bulletin.topic.reopened")) {
                        c9 = '-';
                        break;
                    }
                    break;
                case 2124467303:
                    if (str2.equals("bulletin.topic.liked")) {
                        c9 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                        break;
                    }
                    break;
            }
            switch (c9) {
                case 0:
                case '\f':
                case 14:
                case 29:
                    ChatDialogActivity.this.m11529Z7(strM14428m);
                    return true;
                case 1:
                case 18:
                case 24:
                case '%':
                    map.get("actor");
                    ChatDialogActivity.this.m11513V7(map.get("topicId"));
                    if (str2.equals("bulletin.topic.created")) {
                        ChatDialogActivity.this.m11499S7();
                    }
                    return true;
                case 2:
                case 4:
                case 7:
                case '\b':
                case 15:
                case 20:
                case 21:
                case 23:
                case '(':
                    ChatDialogActivity.this.m11458G7(str2, map.get("groupId"), map.get("userId"), str);
                    return true;
                case 3:
                case ')':
                    if (String.valueOf(ChatDialogActivity.this.f9914g.f13727n).equals(map.get("groupId"))) {
                        ChatDialogActivity.this.f9914g.f13714M = str2.equals("group.reminder.set.event");
                    }
                    return true;
                case 5:
                case 25:
                    ChatDialogActivity.this.m11525Y7(map.get("userId"));
                    return true;
                case 6:
                    ChatDialogActivity.this.m11504T7(map.get("topicId"));
                    return true;
                case '\t':
                case 22:
                    ChatDialogActivity.this.m11455F7(map.get("groupId"));
                    return true;
                case '\n':
                case '\r':
                case 19:
                case 26:
                case 30:
                    ChatDialogActivity.this.m11464I7(str2, map.get("groupId"), str, map.get("albumId"), map.get("mediaId"));
                    return true;
                case 11:
                case 31:
                    ChatDialogActivity.this.m11467J7(c2904l);
                    return true;
                case 16:
                case '+':
                    ChatDialogActivity.this.m11490Q7();
                    return true;
                case 17:
                    ChatDialogActivity.this.m11470K7(map.get(Constants.FirelogAnalytics.PARAM_MESSAGE_ID));
                    return true;
                case 27:
                    ChatDialogActivity.this.m11479N7();
                    return true;
                case 28:
                case '\"':
                    ChatDialogActivity.this.m11452E7();
                    return true;
                case ' ':
                    ChatDialogActivity.this.m11473L7(map.get(Constants.FirelogAnalytics.PARAM_MESSAGE_ID));
                    return true;
                case '!':
                    if (ChatDialogActivity.this.f9914g.f13716c.equals("Dual") && c2904l != null && ChatDialogActivity.this.f9914g.f13723j.equals(c2904l.m14428m())) {
                        ChatDialogActivity.this.m11579m7(null);
                    }
                    return true;
                case '#':
                case '.':
                    ChatDialogActivity.this.m11509U7(map.get("topicId"));
                    return true;
                case '$':
                    ChatDialogActivity.this.m11517W7(map.get("topicId"));
                    return true;
                case '&':
                case '-':
                    ChatDialogActivity.this.m11495R7(map.get("topicId"));
                    return true;
                case '\'':
                    ChatDialogActivity.this.f9827I0 = true;
                    return true;
                case '*':
                    ChatDialogActivity.this.m11521X7(map.get("mediaId"));
                    return true;
                case ',':
                    ChatDialogActivity.this.m11476M7(map.get("groupId"), map.get("isDisabled"));
                    return true;
                default:
                    return true;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$x0 */
    public class ViewOnTouchListenerC2016x0 implements View.OnTouchListener {
        public ViewOnTouchListenerC2016x0() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                ChatDialogActivity.this.m11535ab(0, true);
                return true;
            }
            if (action != 1) {
                if (action != 3 && action != 4) {
                    return false;
                }
                ChatDialogActivity.this.m11535ab(0, false);
                return true;
            }
            if (ChatDialogActivity.this.f9851O0.size() > 0) {
                MessageObj messageObj = (MessageObj) ChatDialogActivity.this.f9851O0.get(ChatDialogActivity.this.f9851O0.size() - 1);
                try {
                    JSONObject jSONObject = new JSONObject(messageObj.m14779q());
                    ChatDialogActivity.this.f9831J0 = messageObj;
                    ChatDialogActivity.this.m11606ua(messageObj, jSONObject.getString("replyText"));
                    ChatDialogActivity.this.f9851O0.remove(messageObj);
                    ChatDialogActivity.this.f9859Q0 = true;
                    return true;
                } catch (Throwable unused) {
                    ULogUtility.m16670f("ChatDialogActivity", "mOnReplyTextItemClickListener onClick");
                }
            } else {
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                if (!chatDialogActivity.m11561h8(chatDialogActivity.f9946o) && !ChatDialogActivity.this.f9866S && ChatDialogActivity.this.f9835K0 != null) {
                    ChatDialogActivity chatDialogActivity2 = ChatDialogActivity.this;
                    chatDialogActivity2.m11606ua(chatDialogActivity2.f9835K0, null);
                    ChatDialogActivity.this.m11535ab(8, false);
                    return true;
                }
                ChatDialogActivity.this.m11535ab(8, false);
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$x1 */
    public class C2017x1 extends TimerTask {

        /* renamed from: b */
        public Object f10230b = new Object();

        /* renamed from: c */
        public String f10231c;

        public C2017x1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m11985c() {
            synchronized (this.f10230b) {
                if (this.f10231c != null) {
                    if (ChatDialogActivity.this.f9798B != null) {
                        ChatDialogActivity.this.f9798B.setText(this.f10231c);
                        Log.d("ChatDialogActivity", "[CountDownRemainTimeTask] mRemainTimeString = " + this.f10231c);
                    } else {
                        Log.d("ChatDialogActivity", "[CountDownRemainTimeTask] mRemainTimeTextView is null");
                    }
                }
            }
        }

        /* renamed from: b */
        public TextView m11986b() {
            TextView textView;
            synchronized (this.f10230b) {
                textView = ChatDialogActivity.this.f9798B;
            }
            return textView;
        }

        /* renamed from: d */
        public void m11987d(TextView textView) {
            synchronized (this.f10230b) {
                ChatDialogActivity.this.f9798B = textView;
                if (this.f10231c != null && ChatDialogActivity.this.f9798B != null) {
                    ChatDialogActivity.this.f9798B.setText(this.f10231c);
                }
            }
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (ChatDialogActivity.this.f9939m0 == null) {
                Log.d("ChatDialogActivity", "[CountDownRemainTimeTask] run for Player or activity is null");
                cancel();
            } else if (ChatDialogActivity.this.f9939m0.m20014d() != -1) {
                this.f10231c = CLUtility.m16531f(String.valueOf((ChatDialogActivity.this.f9939m0.m20014d() - ChatDialogActivity.this.f9939m0.m20013c()) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                ChatDialogActivity.this.m11582n7().runOnUiThread(new Runnable() { // from class: y2.s3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22426b.m11985c();
                    }
                });
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$y */
    public class ViewOnClickListenerC2018y implements View.OnClickListener {
        public ViewOnClickListenerC2018y() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChatDialogActivity.this.f9980w1 == null) {
                ChatDialogActivity chatDialogActivity = ChatDialogActivity.this;
                chatDialogActivity.f9980w1 = chatDialogActivity.m11544d7(view);
            }
            if (ChatDialogActivity.this.isFinishing()) {
                return;
            }
            ChatDialogActivity.this.f9980w1.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$y0 */
    public class C2019y0 implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f10234a;

        public C2019y0(Permission permission) {
            this.f10234a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(ChatDialogActivity.this, this.f10234a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            try {
                if (C2950b0.m14909h().m15037b(C2950b0.f13117a) == null) {
                    C5187v0.m20267c(R.string.no_local_photos);
                } else {
                    ChatDialogActivity.this.m11512V6();
                }
            } catch (Exception e9) {
                C5154j.m20076j(e9);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$y1 */
    public class AsyncTaskC2020y1 extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        public List<MessageObj> f10236a;

        /* renamed from: b */
        public int f10237b = 0;

        /* renamed from: c */
        public ProgressDialog f10238c;

        public AsyncTaskC2020y1(List<MessageObj> list) {
            this.f10236a = list;
            this.f10238c = ProgressDialog.show(ChatDialogActivity.this.m11582n7(), "", ChatDialogActivity.this.getString(R.string.processing), true);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) throws JSONException {
            Thread.currentThread().setName("[DeleteSendMediaTask] AsyncTask");
            try {
                if (this.f10236a.size() > 0) {
                    JSONObject jSONObjectM7487T = Globals.m7388i0().m7487T();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                    Iterator<MessageObj> it = this.f10236a.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new C6301o(Constants.FirelogAnalytics.PARAM_MESSAGE_ID, it.next().m14777o()));
                    }
                    String str = (String) ChatDialogActivity.this.f9899c0.m15731j("chat", "deleteMessage", arrayList).first;
                    ULogUtility.m16680p("ChatDialogActivity", "[DeleteSendMediaTask] statusCode = " + str);
                    if (str != null && str.equals("200")) {
                        for (MessageObj messageObj : this.f10236a) {
                            C2950b0.m14916o().m15169h(messageObj.m14777o());
                            jSONObjectM7487T.put(messageObj.m14777o(), messageObj.m14788z().getTime());
                            this.f10237b++;
                        }
                        if (this.f10237b > 0) {
                            Globals.m7388i0().m7440I2(jSONObjectM7487T);
                        }
                        return Boolean.TRUE;
                    }
                    C5187v0.m20268d(ChatDialogActivity.this.getString(R.string.delete_message_fail));
                }
            } catch (Exception e9) {
                UploadUtils.m16965l("ChatDialogActivity", "[DeleteSendMediaTask] Exception = " + e9.getMessage());
            }
            return Boolean.FALSE;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            synchronized (ChatDialogActivity.this.f9862R) {
                if (bool.booleanValue() && ChatDialogActivity.this.f9810E != null) {
                    ChatDialogActivity.this.f9810E.setNotifyOnChange(false);
                    for (int i9 = 0; i9 < this.f10237b; i9++) {
                        try {
                            MessageObj messageObjM11608v7 = ChatDialogActivity.this.m11608v7(this.f10236a.get(i9).m14777o());
                            if (messageObjM11608v7 != null) {
                                ChatDialogActivity.this.f9810E.remove(messageObjM11608v7);
                            }
                        } catch (Exception e9) {
                            UploadUtils.m16965l("ChatDialogActivity", "[DeleteSendMediaTask][onPostExecute] e = " + e9.getMessage());
                        }
                    }
                    ChatDialogActivity.this.f9810E.setNotifyOnChange(true);
                    ChatDialogActivity.this.f9810E.notifyDataSetChanged();
                }
            }
            C5152i0.m20065b(this.f10238c);
            this.f10236a.clear();
            this.f10236a = null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$z */
    public class ViewOnClickListenerC2021z implements View.OnClickListener {
        public ViewOnClickListenerC2021z() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(ChatDialogActivity.this.m11582n7(), (Class<?>) SearchChatActivity.class);
            intent.putExtra("Group", ChatDialogActivity.this.f9914g);
            ChatDialogActivity.this.startActivityForResult(intent, 5021);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$z0 */
    public class ViewOnClickListenerC2022z0 implements View.OnClickListener {
        public ViewOnClickListenerC2022z0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(ChatDialogActivity.this, (Class<?>) CreateTopicActivity.class);
            intent.putExtra("Group", ChatDialogActivity.this.f9914g);
            ChatDialogActivity.this.startActivityForResult(intent, 16);
        }
    }

    /* renamed from: com.cyberlink.you.activity.chatdialog.ChatDialogActivity$z1 */
    public class C2023z1 extends GestureDetector.SimpleOnGestureListener {
        public C2023z1() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            Log.d("ChatDialogActivity", "onDoubleTap in");
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) throws NumberFormatException {
            Log.d("ChatDialogActivity", "onDoubleTapEvent in");
            ChatDialogActivity.this.m11555fb();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
        }
    }

    public ChatDialogActivity() {
        C6696m5 c6696m5 = new C6696m5(1, R.string.forward, R.drawable.icon_option_forward);
        this.f9897b2 = c6696m5;
        C6696m5 c6696m52 = new C6696m5(c6696m5.f22367a + 1, R.string.copy, R.drawable.icon_option_copy);
        this.f9901c2 = c6696m52;
        C6696m5 c6696m53 = new C6696m5(c6696m52.f22367a + 1, R.string.menu_delete, R.drawable.icon_option_delete);
        this.f9905d2 = c6696m53;
        C6696m5 c6696m54 = new C6696m5(c6696m53.f22367a + 1, R.string.menu_resend, R.drawable.icon_option_resend);
        this.f9909e2 = c6696m54;
        C6696m5 c6696m55 = new C6696m5(c6696m54.f22367a + 1, R.string.call_back, R.drawable.icon_option_recall);
        this.f9913f2 = c6696m55;
        C6696m5 c6696m56 = new C6696m5(c6696m55.f22367a + 1, R.string.menu_details, R.drawable.icon_option_detail);
        this.f9917g2 = c6696m56;
        C6696m5 c6696m57 = new C6696m5(c6696m56.f22367a + 1, R.string.menu_open, R.drawable.icon_option_open);
        this.f9921h2 = c6696m57;
        C6696m5 c6696m58 = new C6696m5(c6696m57.f22367a + 1, R.string.forward_photo_with_comment, R.drawable.icon_option_forward_with_comment);
        this.f9925i2 = c6696m58;
        C6696m5 c6696m59 = new C6696m5(c6696m58.f22367a + 1, R.string.save_to_my_device, R.drawable.icon_option_save);
        this.f9929j2 = c6696m59;
        C6696m5 c6696m510 = new C6696m5(c6696m59.f22367a + 1, R.string.save_to_my_album, R.drawable.icon_option_saveto_album);
        this.f9933k2 = c6696m510;
        C6696m5 c6696m511 = new C6696m5(c6696m510.f22367a + 1, R.string.save_to_group_album, R.drawable.icon_option_save_to_group_album);
        this.f9937l2 = c6696m511;
        C6696m5 c6696m512 = new C6696m5(c6696m511.f22367a + 1, R.string.menu_cancel_message, R.drawable.icon_option_recall);
        this.f9941m2 = c6696m512;
        C6696m5 c6696m513 = new C6696m5(c6696m512.f22367a + 1, R.string.menu_reply, R.drawable.icon_option_reply);
        this.f9945n2 = c6696m513;
        C6696m5 c6696m514 = new C6696m5(c6696m513.f22367a + 1, R.string.menu_quote, R.drawable.icon_option_quote);
        this.f9949o2 = c6696m514;
        C6696m5 c6696m515 = new C6696m5(c6696m514.f22367a + 1, R.string.find_source_message, R.drawable.icon_option_back2source);
        this.f9953p2 = c6696m515;
        C6696m5 c6696m516 = new C6696m5(c6696m515.f22367a + 1, R.string.set_reminder, R.drawable.icon_option_remind);
        this.f9957q2 = c6696m516;
        C6696m5 c6696m517 = new C6696m5(c6696m516.f22367a + 1, R.string.add_to_todo, R.drawable.icon_option_todo);
        this.f9961r2 = c6696m517;
        this.f9965s2 = new C6696m5(c6696m517.f22367a + 1, R.string.share_to_other_apps, R.drawable.icon_option_share_to_app);
        this.f9973u2 = new ViewOnClickListenerC2022z0();
        this.f9977v2 = new C1937a1();
        this.f9985x2 = 0;
        this.f9989y2 = 0;
        this.f9993z2 = new C1941b1();
        this.f9797A2 = new C1949d1();
        this.f9801B2 = new C1961g1();
        this.f9805C2 = new ViewOnClickListenerC1965h1();
        this.f9817F2 = new C1973j1();
        this.f9821G2 = new C1977k1();
        this.f9825H2 = new C1990o1(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A8 */
    public /* synthetic */ void m11047A8() {
        C3199c.m17015D(false);
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m11582n7());
        builderM16382a.setMessage(R.string.reach_album_limit);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.x1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ChatDialogActivity.m11438z8(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.requestWindowFeature(1);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B8 */
    public /* synthetic */ void m11055B8(ArrayList arrayList, String str, String str2, String str3, String str4) {
        m11540c7();
        if (str3 == null) {
            Log.d("ChatDialogActivity", "Response is null");
            C5187v0.m20267c(R.string.error_server_response);
            return;
        }
        if (!str3.equals("200")) {
            if (str3.equals("403") && str4.contains("Max album limit exceeded")) {
                m11582n7().runOnUiThread(new Runnable() { // from class: y2.o1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22381b.m11047A8();
                    }
                });
            } else {
                C5187v0.m20267c(R.string.error_server_response);
            }
            Log.d("ChatDialogActivity", "statusCode=" + str3);
            return;
        }
        GroupAlbumObj groupAlbumObjM20185g = C5172p.m20185g(C5172p.m20195q(str4), Long.toString(this.f9914g.f13727n));
        if (groupAlbumObjM20185g == null) {
            Log.d("ChatDialogActivity", "[createAlbum] groupAlbumObj is null");
            return;
        }
        String strM14675b = groupAlbumObjM20185g.m14675b();
        if (strM14675b == null || strM14675b.isEmpty()) {
            Log.d("ChatDialogActivity", "[createAlbum] Album id is null or empty.");
            return;
        }
        C2950b0.m14911j().m15053f(groupAlbumObjM20185g);
        m11613wb();
        if (arrayList != null) {
            m11507Tb(strM14675b, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C8 */
    public /* synthetic */ void m11063C8(String str, String str2, String str3, String str4) {
        ProgressDialog progressDialog;
        if (str3 == null || !str3.equals("200")) {
            Log.d("ChatDialogActivity", "statusCode = " + str3);
            return;
        }
        if (!isFinishing() && (progressDialog = this.f9898c) != null && progressDialog.isShowing()) {
            this.f9898c.dismiss();
        }
        try {
            try {
                m11579m7(C5172p.m20186h(new JSONObject(str4).getJSONObject("result")));
            } catch (JSONException unused) {
                Log.e("ChatDialogActivity", "[createAndSwitchToNewChatRoom] 'results' missing. JSONstr=" + str4);
            }
        } catch (JSONException unused2) {
            Log.e("ChatDialogActivity", "[createAndSwitchToNewChatRoom] Parse error. JSONstr=" + str4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C9 */
    public /* synthetic */ void m11064C9(View view) {
        synchronized (this.f9862R) {
            m11056B9(view, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D8 */
    public /* synthetic */ void m11071D8(List list, String str, String str2, String str3, String str4) throws JSONException {
        if (!isFinishing()) {
            C5152i0.m20065b(this.f9898c);
        }
        if (str3 == null || !str3.equals("200")) {
            Log.d("ChatDialogActivity", "statusCode = " + str3);
            return;
        }
        try {
            try {
                Group groupM20186h = C5172p.m20186h(new JSONObject(str4).getJSONObject("result"));
                C2950b0.m14912k().m15070g(groupM20186h, true);
                Globals.m7388i0().m7485S2(groupM20186h.f13727n);
                m11489Q6(list);
            } catch (JSONException unused) {
                Log.e("ChatDialogActivity", "[createToDoGroup] 'results' missing. JSONstr=" + str4);
            }
        } catch (JSONException unused2) {
            Log.e("ChatDialogActivity", "[createToDoGroup] Parse error. JSONstr=" + str4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D9 */
    public /* synthetic */ void m11072D9() {
        View childAt = this.f9946o.getChildAt(r0.getChildCount() - 1);
        int height = childAt == null ? 0 : childAt.getHeight();
        this.f9946o.setSelectionFromTop(r1.getCount() - 1, -height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E8 */
    public /* synthetic */ void m11079E8(GroupAlbumObj groupAlbumObj, String str, String str2, String str3, String str4) throws JSONException {
        if (str3 == null) {
            Log.d("ChatDialogActivity", "[media.listMedia] Response is null");
            return;
        }
        if (str3.equals("200")) {
            CLUtility.m16501W1(groupAlbumObj.m14675b(), str4);
            m11607ub();
        } else {
            Log.d("ChatDialogActivity", "[media.listMedia] statusCode=" + str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E9 */
    public /* synthetic */ void m11080E9() {
        C3072a c3072a = this.f9868S1;
        if (c3072a == null) {
            return;
        }
        c3072a.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F8 */
    public /* synthetic */ void m11087F8(String str, String str2, String str3, String str4) throws JSONException {
        ULogUtility.m16670f("ChatDialogActivity", "[getUserMsgSubscriptionStatus] statusCode: " + str3 + " / JSON: " + str4);
        if (str3 == null || !str3.equals("200")) {
            return;
        }
        try {
            String string = new JSONObject(str4).getString("isPro");
            if (C5170o0.m20170e(string)) {
                return;
            }
            this.f9863R0 = Boolean.parseBoolean(string);
            Globals.m7388i0().m7546f3(this.f9863R0);
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F9 */
    public /* synthetic */ void m11088F9(ChatOptionDialog chatOptionDialog) {
        ChatOptionDialog.ChatOptionButton chatOptionButton = ChatOptionDialog.ChatOptionButton.TurnOffNotification;
        ImageView imageView = chatOptionDialog.m11991b(chatOptionButton).f10267b;
        TextView textView = chatOptionDialog.m11991b(chatOptionButton).f10266a;
        if (this.f9914g.f13733t) {
            this.f9802C.setVisibility(0);
            m11604u2();
            imageView.setImageResource(R.drawable.dropdown_icon_turn_on_alberts);
            textView.setText(getString(R.string.chat_group_more_dialog_open_allert));
            return;
        }
        this.f9802C.setVisibility(8);
        m11604u2();
        imageView.setImageResource(R.drawable.dropdown_icon_turn_off_alberts);
        textView.setText(getString(R.string.chat_group_more_dialog_close_allert));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G8 */
    public /* synthetic */ void m11095G8() {
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            c1954e2.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G9 */
    public /* synthetic */ void m11096G9() {
        this.f9980w1 = m11544d7(this.f9922i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H8 */
    public /* synthetic */ void m11103H8(Group group) {
        this.f9914g.f13704C = group.f13704C;
        boolean zIsShowing = this.f9980w1.isShowing();
        this.f9980w1.dismiss();
        ChatOptionDialog chatOptionDialogM11544d7 = m11544d7(this.f9922i);
        this.f9980w1 = chatOptionDialogM11544d7;
        if (zIsShowing) {
            chatOptionDialogM11544d7.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H9 */
    public /* synthetic */ void m11104H9(ProgressDialog progressDialog, String str, String str2, String str3, String str4) {
        if (str3 == null || !str3.equals("200")) {
            Log.e("ChatDialogActivity", "Create group fail.");
        } else {
            Group groupM8330k1 = GroupEditActivity.m8330k1(str4);
            C2950b0.m14912k().m15069f(groupM8330k1);
            this.f9914g = groupM8330k1;
            runOnUiThread(new Runnable() { // from class: y2.g2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22252b.m11096G9();
                }
            });
        }
        if (isFinishing()) {
            return;
        }
        C5152i0.m20065b(progressDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I8 */
    public /* synthetic */ void m11111I8(String str, MessageObj messageObj) {
        C1954e2 c1954e2;
        if ((str.equals("group.member.leaved") || str.equals("group.member.created") || str.equals("group.member.created.v2") || str.equals("group.member.deleted") || str.equals("group.admin.created") || str.equals("group.admin.deleted") || str.equals("group.display.name.updated")) && messageObj != null && (c1954e2 = this.f9810E) != null) {
            c1954e2.add(messageObj);
        }
        m11502Sb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I9 */
    public /* synthetic */ void m11112I9(ChatOptionDialog chatOptionDialog, Friend friend) {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        TextView textView11;
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        ImageView imageView5;
        ImageView imageView6;
        ImageView imageView7;
        ImageView imageView8;
        ImageView imageView9;
        ImageView imageView10;
        ImageView imageView11;
        View view;
        View view2;
        View view3;
        View view4;
        View view5;
        View view6;
        View view7;
        View view8;
        View view9;
        View view10;
        View view11;
        ChatOptionDialog.ChatOptionButton chatOptionButton = ChatOptionDialog.ChatOptionButton.Block;
        if (chatOptionDialog.m11992c(chatOptionButton)) {
            if (friend.f13655m) {
                chatOptionDialog.m11991b(chatOptionButton).f10267b.setImageResource(R.drawable.dropdown_icon_unblock);
                chatOptionDialog.m11991b(chatOptionButton).f10266a.setText(getString(R.string.unblock));
            } else {
                chatOptionDialog.m11991b(chatOptionButton).f10267b.setImageResource(R.drawable.dropdown_icon_block);
                chatOptionDialog.m11991b(chatOptionButton).f10266a.setText(getString(R.string.friends_friends_title_frieds_block));
            }
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton2 = ChatOptionDialog.ChatOptionButton.Invite;
        if (chatOptionDialog.m11992c(chatOptionButton2) && (view11 = chatOptionDialog.m11991b(chatOptionButton2).f10268c) != null) {
            view11.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton3 = ChatOptionDialog.ChatOptionButton.TurnOffNotification;
        if (chatOptionDialog.m11992c(chatOptionButton3) && (view10 = chatOptionDialog.m11991b(chatOptionButton3).f10268c) != null) {
            view10.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton4 = ChatOptionDialog.ChatOptionButton.EditName;
        if (chatOptionDialog.m11992c(chatOptionButton4) && (view9 = chatOptionDialog.m11991b(chatOptionButton4).f10268c) != null) {
            view9.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton5 = ChatOptionDialog.ChatOptionButton.Photo;
        if (chatOptionDialog.m11992c(chatOptionButton5) && (view8 = chatOptionDialog.m11991b(chatOptionButton5).f10268c) != null) {
            view8.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton6 = ChatOptionDialog.ChatOptionButton.Album;
        if (chatOptionDialog.m11992c(chatOptionButton6) && (view7 = chatOptionDialog.m11991b(chatOptionButton6).f10268c) != null) {
            view7.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton7 = ChatOptionDialog.ChatOptionButton.BreakUp;
        if (chatOptionDialog.m11992c(chatOptionButton7) && (view6 = chatOptionDialog.m11991b(chatOptionButton7).f10268c) != null) {
            view6.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton8 = ChatOptionDialog.ChatOptionButton.Video;
        if (chatOptionDialog.m11992c(chatOptionButton8) && (view5 = chatOptionDialog.m11991b(chatOptionButton8).f10268c) != null) {
            view5.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton9 = ChatOptionDialog.ChatOptionButton.Call;
        if (chatOptionDialog.m11992c(chatOptionButton9) && (view4 = chatOptionDialog.m11991b(chatOptionButton9).f10268c) != null) {
            view4.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton10 = ChatOptionDialog.ChatOptionButton.AddToHome;
        if (chatOptionDialog.m11992c(chatOptionButton10) && (view3 = chatOptionDialog.m11991b(chatOptionButton10).f10268c) != null) {
            view3.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton11 = ChatOptionDialog.ChatOptionButton.Profile;
        if (chatOptionDialog.m11992c(chatOptionButton11) && (view2 = chatOptionDialog.m11991b(chatOptionButton11).f10268c) != null) {
            view2.setEnabled(!friend.f13655m);
        }
        ChatOptionDialog.ChatOptionButton chatOptionButton12 = ChatOptionDialog.ChatOptionButton.File;
        if (chatOptionDialog.m11992c(chatOptionButton12) && (view = chatOptionDialog.m11991b(chatOptionButton12).f10268c) != null) {
            view.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton2) && (imageView11 = chatOptionDialog.m11991b(chatOptionButton2).f10267b) != null) {
            imageView11.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton3) && (imageView10 = chatOptionDialog.m11991b(chatOptionButton3).f10267b) != null) {
            imageView10.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton4) && (imageView9 = chatOptionDialog.m11991b(chatOptionButton4).f10267b) != null) {
            imageView9.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton5) && (imageView8 = chatOptionDialog.m11991b(chatOptionButton5).f10267b) != null) {
            imageView8.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton6) && (imageView7 = chatOptionDialog.m11991b(chatOptionButton6).f10267b) != null) {
            imageView7.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton7) && (imageView6 = chatOptionDialog.m11991b(chatOptionButton7).f10267b) != null) {
            imageView6.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton8) && (imageView5 = chatOptionDialog.m11991b(chatOptionButton8).f10267b) != null) {
            imageView5.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton9) && (imageView4 = chatOptionDialog.m11991b(chatOptionButton9).f10267b) != null) {
            imageView4.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton10) && (imageView3 = chatOptionDialog.m11991b(chatOptionButton10).f10267b) != null) {
            imageView3.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton11) && (imageView2 = chatOptionDialog.m11991b(chatOptionButton11).f10267b) != null) {
            imageView2.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton12) && (imageView = chatOptionDialog.m11991b(chatOptionButton12).f10267b) != null) {
            imageView.setAlpha(friend.f13655m ? 0.15f : 1.0f);
        }
        if (chatOptionDialog.m11992c(chatOptionButton2) && (textView11 = chatOptionDialog.m11991b(chatOptionButton2).f10266a) != null) {
            textView11.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton3) && (textView10 = chatOptionDialog.m11991b(chatOptionButton3).f10266a) != null) {
            textView10.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton4) && (textView9 = chatOptionDialog.m11991b(chatOptionButton4).f10266a) != null) {
            textView9.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton6) && (textView8 = chatOptionDialog.m11991b(chatOptionButton6).f10266a) != null) {
            textView8.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton5) && (textView7 = chatOptionDialog.m11991b(chatOptionButton5).f10266a) != null) {
            textView7.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton7) && (textView6 = chatOptionDialog.m11991b(chatOptionButton7).f10266a) != null) {
            textView6.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton8) && (textView5 = chatOptionDialog.m11991b(chatOptionButton8).f10266a) != null) {
            textView5.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton9) && (textView4 = chatOptionDialog.m11991b(chatOptionButton9).f10266a) != null) {
            textView4.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton10) && (textView3 = chatOptionDialog.m11991b(chatOptionButton10).f10266a) != null) {
            textView3.setEnabled(!friend.f13655m);
        }
        if (chatOptionDialog.m11992c(chatOptionButton11) && (textView2 = chatOptionDialog.m11991b(chatOptionButton11).f10266a) != null) {
            textView2.setEnabled(!friend.f13655m);
        }
        if (!chatOptionDialog.m11992c(chatOptionButton12) || (textView = chatOptionDialog.m11991b(chatOptionButton12).f10266a) == null) {
            return;
        }
        textView.setEnabled(!friend.f13655m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J8 */
    public /* synthetic */ void m11119J8(MessageObj messageObj) {
        C1954e2 c1954e2;
        if (messageObj == null || (c1954e2 = this.f9810E) == null) {
            return;
        }
        c1954e2.add(messageObj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J9 */
    public /* synthetic */ void m11120J9(int i9) {
        if (i9 == 0 || this.f9795A0 == Tab.Chats) {
            this.f9970u.setVisibility(4);
        } else if (i9 <= 99) {
            this.f9970u.setVisibility(0);
            this.f9970u.setText(String.valueOf(i9));
        } else {
            this.f9970u.setVisibility(0);
            this.f9970u.setText("N");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K8 */
    public /* synthetic */ void m11128K8(String str, String str2) {
        boolean z8 = "media.album.created".equals(str) || "media.media.created".equals(str);
        boolean zEquals = "media.album.deleted".equals(str);
        if (z8 || zEquals) {
            C3072a c3072a = this.f9868S1;
            if (c3072a != null) {
                GroupAlbumObj groupAlbumObjM15951j = c3072a.m15951j(str2);
                if ((z8 && groupAlbumObjM15951j == null) || (zEquals && groupAlbumObjM15951j != null)) {
                    m11589pa();
                }
            }
            C1954e2 c1954e2 = this.f9810E;
            if (c1954e2 != null) {
                c1954e2.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K9 */
    public /* synthetic */ void m11129K9() {
        this.f9990z.setVisibility(this.f9914g.f13732s ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L8 */
    public /* synthetic */ void m11137L8(MessageObj messageObj) {
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            c1954e2.add(messageObj);
            m11609va(messageObj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L9 */
    public /* synthetic */ void m11138L9(boolean z8) {
        AsyncTaskC1978k2 asyncTaskC1978k2 = new AsyncTaskC1978k2(z8);
        this.f9809D2 = asyncTaskC1978k2;
        asyncTaskC1978k2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M8 */
    public /* synthetic */ void m11146M8(MessageObj messageObj) {
        synchronized (this.f9862R) {
            this.f9810E.remove(messageObj);
            m11559gb();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M9 */
    public /* synthetic */ void m11147M9() {
        synchronized (this.f9862R) {
            C1954e2 c1954e2 = this.f9810E;
            if (c1954e2 != null) {
                c1954e2.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N8 */
    public /* synthetic */ void m11155N8(MessageObj messageObj) {
        synchronized (this.f9862R) {
            this.f9810E.remove(messageObj);
            C1954e2 c1954e2 = this.f9810E;
            if (c1954e2 != null && c1954e2.m11826u0() > 0) {
                this.f9835K0 = this.f9810E.getItem(r1.m11826u0() - 1);
            }
            if (messageObj.m14777o().equals(this.f9958r.m25311c())) {
                this.f9958r.m25313e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N9 */
    public /* synthetic */ void m11156N9(int i9) {
        if (i9 > 0) {
            this.f9888Y1.setVisibility(0);
        } else {
            this.f9888Y1.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O8 */
    public /* synthetic */ void m11164O8() {
        ChatOptionDialog chatOptionDialog = this.f9980w1;
        if (chatOptionDialog != null) {
            m11610vb(chatOptionDialog);
            return;
        }
        ImageView imageView = this.f9802C;
        if (imageView != null) {
            if (this.f9914g.f13733t) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
    }

    /* renamed from: O9 */
    public static /* synthetic */ void m11165O9(C2973l0 c2973l0) {
        Bitmap bitmapM7145d = MediaLoader.m7145d(Globals.m7372O(), c2973l0, true);
        if (bitmapM7145d == null) {
            return;
        }
        c2973l0.m15124K(bitmapM7145d.getWidth());
        c2973l0.m15121H(bitmapM7145d.getHeight());
        ArrayList arrayList = new ArrayList();
        arrayList.add("Height");
        arrayList.add("Width");
        C2950b0.m14914m().m14699I(c2973l0.m15144p(), c2973l0, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P8 */
    public /* synthetic */ void m11172P8(Map map) {
        this.f9797A2.mo7011a("sendVideo", map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P9 */
    public /* synthetic */ void m11173P9() {
        DialogC6459g dialogC6459g = new DialogC6459g(this, R.style.FriendSelectorDialog);
        this.f9963s0 = dialogC6459g;
        dialogC6459g.m24770m(this.f9817F2);
        this.f9963s0.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q8 */
    public /* synthetic */ void m11180Q8(TopicObj topicObj) {
        C5126e c5126e = this.f9969t2;
        if (c5126e != null) {
            c5126e.m19995h(topicObj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R8 */
    public /* synthetic */ void m11187R8() {
        if (this.f9795A0 != Tab.Bulletins || this.f9969t2.getCount() <= 0) {
            return;
        }
        this.f9894a2.setVisibility(4);
    }

    /* renamed from: Rb */
    public static void m11188Rb(final C2973l0 c2973l0) {
        C6385v.m24526d(new Runnable() { // from class: y2.b1
            @Override // java.lang.Runnable
            public final void run() {
                ChatDialogActivity.m11165O9(c2973l0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S8 */
    public /* synthetic */ void m11195S8(String str) {
        C5126e c5126e = this.f9969t2;
        if (c5126e != null) {
            c5126e.m19990c(Long.parseLong(str));
            this.f9969t2.notifyDataSetChanged();
            if (this.f9795A0 == Tab.Bulletins && this.f9969t2.getCount() == 0) {
                this.f9894a2.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T8 */
    public /* synthetic */ void m11202T8(TopicObj topicObj) {
        C5126e c5126e = this.f9969t2;
        if (c5126e != null) {
            c5126e.m19996i(topicObj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U8 */
    public /* synthetic */ void m11209U8(TopicObj topicObj) {
        C5126e c5126e = this.f9969t2;
        if (c5126e != null) {
            c5126e.m19994g(topicObj);
            m11622zb();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V8 */
    public /* synthetic */ void m11216V8(TopicObj topicObj) {
        C5126e c5126e = this.f9969t2;
        if (c5126e != null) {
            c5126e.m19988a(topicObj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W8 */
    public /* synthetic */ void m11223W8() {
        this.f9810E.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X8 */
    public /* synthetic */ void m11230X8() {
        synchronized (this.f9862R) {
            C1954e2 c1954e2 = this.f9810E;
            if (c1954e2 != null) {
                c1954e2.notifyDataSetChanged();
            }
            m11502Sb();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y8 */
    public /* synthetic */ void m11237Y8() {
        m11593qb(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z8 */
    public /* synthetic */ void m11244Z8(String str, String str2, String str3, String str4, String str5) {
        if (str4 == null || !str4.equals("200")) {
            Log.e("ChatDialogActivity", "[OnDeleteGroup] status code = " + str4);
            return;
        }
        this.f9874U = true;
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            c1954e2.m11802e0();
        }
        C5321e.m20824o().m20893v0(this.f9914g, Long.parseLong(str));
        C5321e.m20824o().m20896x0(null, C5321e.m20823m(str, String.valueOf(Globals.m7388i0().m7568k1())));
        m11579m7(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a9 */
    public /* synthetic */ void m11251a9(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        int i17 = i16 - i14;
        int i18 = i12 - i10;
        boolean z8 = i10 != i14;
        boolean z9 = i18 < i17;
        boolean zEquals = this.f9983x0.equals(ChatDialogMode.NORMAL);
        if (this.f9867S0.f10224p && zEquals && (z9 || z8)) {
            m11593qb(false);
        }
        if (this.f9859Q0) {
            this.f9859Q0 = false;
            if (!Tab.Chats.equals(this.f9795A0)) {
                m11535ab(8, false);
            } else if (this.f9851O0.size() == 0) {
                if (m11565i8()) {
                    m11535ab(8, false);
                } else {
                    m11535ab(0, false);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b9 */
    public /* synthetic */ void m11258b9(String str, boolean z8) {
        C6468p.m24793u(this, str, z8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d9 */
    public /* synthetic */ void m11272d9(View view) {
        m11600t2(ChatDialogMode.NORMAL, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e9 */
    public /* synthetic */ void m11279e9(View view) {
        m11576l7();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f9 */
    public /* synthetic */ void m11286f9(View view) {
        m11511Ua(Tab.Chats, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g9 */
    public /* synthetic */ void m11293g9() {
        m11445Bb();
        m11442Ab();
        m11493Qb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h9 */
    public /* synthetic */ void m11300h9(View view) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C2019y0(permission), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i9 */
    public /* synthetic */ void m11307i9(boolean z8) throws Resources.NotFoundException {
        m11448Cb();
        if (!z8 || Globals.m7388i0().m7460N1()) {
            m11571jb(false);
        } else {
            m11612wa();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j9 */
    public /* synthetic */ void m11314j9(final boolean z8) {
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.a1
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                this.f22179b.m11307i9(z8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k9 */
    public /* synthetic */ void m11321k9() {
        m11571jb(false);
    }

    /* renamed from: l8 */
    public static /* synthetic */ void m11328l8(View view, View view2) {
        view.setSelected(!view.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l9 */
    public /* synthetic */ void m11329l9(GroupAlbumObj groupAlbumObj) {
        C3072a c3072a = this.f9868S1;
        if (c3072a != null) {
            c3072a.m15949h(groupAlbumObj.m14675b());
            m11466Ib();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m8 */
    public /* synthetic */ void m11336m8(String str, String str2, String str3, String str4) {
        ProgressDialog progressDialog;
        if (!isFinishing() && (progressDialog = this.f9898c) != null && progressDialog.isShowing()) {
            this.f9898c.dismiss();
        }
        if (str3 == null || !str3.equals("200")) {
            Log.d("ChatDialogActivity", "statusCode = " + str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m9 */
    public /* synthetic */ void m11337m9(View view) {
        this.f9974v.setVisibility(8);
        m11559gb();
        CLUtility.m16589t1(this);
        this.f9975v0 = null;
    }

    /* renamed from: n8 */
    public static /* synthetic */ void m11344n8(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: n9 */
    public static /* synthetic */ boolean m11345n9(View view, MotionEvent motionEvent) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o8 */
    public /* synthetic */ void m11352o8() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.forward_other_to_e2ee_group));
        builderM16382a.setPositiveButton(getString(R.string.got_it), new DialogInterface.OnClickListener() { // from class: y2.h2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ChatDialogActivity.m11344n8(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o9 */
    public /* synthetic */ boolean m11353o9(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f9967t0;
        return c4619d != null && c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p8 */
    public /* synthetic */ void m11360p8() {
        this.f9898c = ProgressDialog.show(m11582n7(), "", getString(R.string.loading), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p9 */
    public /* synthetic */ void m11361p9(View view) {
        Group group = this.f9914g;
        if (group != null && group.f13716c.equals("Dual")) {
            m11542ca();
            return;
        }
        Group group2 = this.f9914g;
        if (group2 == null || !group2.f13716c.equals("ChatRoom")) {
            m11546da();
        } else {
            m11550ea();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q8 */
    public /* synthetic */ void m11368q8(Group group, List list, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            CLUtility.m16527e(Globals.m7372O(), C5172p.m20198t(str4));
            ULogUtility.m16680p("ChatDialogActivity", "update myself info success");
        } else {
            ULogUtility.m16676l("ChatDialogActivity", "update myself info fail statusCode:" + str3);
        }
        m11575kb(group, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q9 */
    public /* synthetic */ void m11369q9(String str) {
        synchronized (this.f9862R) {
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
            if (messageObjM15179r == null) {
                ULogUtility.m16664G("[ChatDialogActivity] onHandleReceiveDeliveryReceipt: messageId=" + str + ", messageObj is null", "Receive");
                return;
            }
            MessageObj messageObjM11608v7 = m11608v7(messageObjM15179r.m14777o());
            if (messageObjM11608v7 == null) {
                ULogUtility.m16664G("[ChatDialogActivity] onHandleReceiveDeliveryReceipt: readMessageObj is null", "Receive");
                return;
            }
            if (messageObjM15179r.m14741C().equals(MessageObj.TTLStatus.NO_TTL)) {
                messageObjM11608v7.m14759U(messageObjM15179r.m14783u());
                if (messageObjM11608v7.m14740B().equals("3") || messageObjM11608v7.m14740B().equals("2")) {
                    messageObjM11608v7.m14762X("0");
                }
            } else if (messageObjM11608v7.m14741C().equals(MessageObj.TTLStatus.NOT_START)) {
                messageObjM11608v7.m14760V(messageObjM15179r.m14786x());
                messageObjM11608v7.m14763Y(MessageObj.TTLStatus.START);
            }
            C1954e2 c1954e2 = this.f9810E;
            if (c1954e2 != null) {
                c1954e2.m11741F1(messageObjM11608v7);
            }
            C1954e2 c1954e22 = this.f9810E;
            if (c1954e22 != null) {
                c1954e22.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r8 */
    public /* synthetic */ void m11376r8(View view) {
        m11564i7();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r9 */
    public /* synthetic */ void m11377r9() {
        this.f9810E.notifyDataSetChanged();
        if (this.f9880W) {
            this.f9880W = false;
            m11590pb();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s8 */
    public /* synthetic */ void m11384s8(SharedPreferences sharedPreferences, View view) {
        sharedPreferences.edit().putBoolean("isShowE2EEReminder", false).apply();
        this.f9807D0.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s9 */
    public /* synthetic */ void m11385s9() {
        if (Globals.m7388i0().m7460N1()) {
            return;
        }
        if (this.f9877V && this.f9795A0 == Tab.Chats) {
            m11612wa();
        }
        this.f9877V = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t8 */
    public /* synthetic */ void m11391t8() {
        this.f9810E.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t9 */
    public /* synthetic */ void m11392t9(List list) {
        synchronized (this.f9862R) {
            C1954e2 c1954e2 = this.f9810E;
            if (c1954e2 != null) {
                c1954e2.m11784X(list);
                m11596rb(true, 150L);
            } else {
                this.f9815F0.addAll(list);
            }
        }
    }

    /* renamed from: u8 */
    public static /* synthetic */ void m11398u8(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u9 */
    public /* synthetic */ void m11399u9(ImageView imageView, Friend friend) {
        C6127a.m23469j(this, imageView, friend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v8 */
    public /* synthetic */ void m11406v8() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(m11582n7());
        builderM16382a.setMessage(R.string.reach_album_limit);
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: y2.f2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ChatDialogActivity.m11398u8(dialogInterface, i9);
            }
        });
        AlertDialog alertDialogCreate = builderM16382a.create();
        alertDialogCreate.requestWindowFeature(1);
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v9 */
    public /* synthetic */ void m11407v9(final ImageView imageView, String str, String str2, String str3, String str4) {
        if (str3 == null) {
            Log.d("ChatDialogActivity", "Response is null");
            return;
        }
        if (str3.equals("200")) {
            final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str4));
            C2950b0.m14899A().m15018j(friendM20184f, false);
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.j0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22319b.m11399u9(imageView, friendM20184f);
                }
            });
        } else {
            Log.d("ChatDialogActivity", "statusCode=" + str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w8 */
    public /* synthetic */ void m11414w8(String str, String str2, String str3, String str4, String str5, String str6) {
        if (C5172p.m20180b(str, str6)) {
            runOnUiThread(new Runnable() { // from class: y2.s1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22423b.m11406v8();
                }
            });
            return;
        }
        Intent intent = new Intent(this, (Class<?>) PhotoImportActivity.class);
        intent.putExtra("isImportedToAlbums", true);
        intent.putExtra("selectLimitCount", C5172p.m20179a(str2, str6));
        startActivityForResult(intent, 5018);
    }

    /* renamed from: w9 */
    public static /* synthetic */ void m11415w9(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x8 */
    public /* synthetic */ void m11422x8() {
        if (C6456d.m24714D().m24748G()) {
            Group groupM15650P = FriendsClient.m15650P(String.valueOf(this.f9914g.f13727n));
            if (groupM15650P != null) {
                this.f9914g.f13711J = groupM15650P.f13711J;
                C2950b0.m14912k().m15062A(String.valueOf(this.f9914g.f13727n), this.f9914g, "isEnableE2EE");
            }
            C6385v.m24527e(new RunnableC6747u0(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x9 */
    public /* synthetic */ void m11423x9() {
        this.f9898c = ProgressDialog.show(m11582n7(), "", getString(R.string.loading), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y8 */
    public /* synthetic */ void m11430y8() {
        ProgressDialog progressDialog;
        try {
            try {
                if (!isFinishing() && (progressDialog = this.f9898c) != null && progressDialog.isShowing()) {
                    this.f9898c.dismiss();
                    this.f9898c = null;
                }
            } catch (Exception e9) {
                Log.e("ChatDialogActivity", "", e9);
            }
        } finally {
            this.f9898c = null;
        }
    }

    /* renamed from: y9 */
    public static /* synthetic */ void m11431y9(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: z8 */
    public static /* synthetic */ void m11438z8(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z9 */
    public /* synthetic */ void m11439z9(boolean z8) {
        View view = this.f9830J;
        if (view != null) {
            view.setVisibility(z8 ? 0 : 8);
        }
    }

    /* renamed from: A7 */
    public final Friend m11440A7(String str) throws NumberFormatException {
        if (C5170o0.m20169d(str)) {
            return null;
        }
        Friend friend = this.f9927j0.get(Long.valueOf(Long.parseLong(str)));
        if (friend != null) {
            return friend;
        }
        boolean z8 = false;
        if (this.f9955q0.containsKey(str) && this.f9955q0.get(str) != null) {
            z8 = true;
        }
        if (z8) {
            return this.f9955q0.get(str);
        }
        Friend friendM15003C = C2950b0.m14899A().m15003C(str);
        this.f9955q0.put(str, friendM15003C);
        return friendM15003C;
    }

    /* renamed from: Aa */
    public final void m11441Aa() {
        DialogC3133q dialogC3133qM16410a = new DialogC3133q.b(m11582n7()).m16410a();
        dialogC3133qM16410a.show();
        new AsyncTaskC1959g(dialogC3133qM16410a).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: Ab */
    public void m11442Ab() {
        AsyncTaskC1970i2 asyncTaskC1970i2 = this.f9979w0;
        if (asyncTaskC1970i2 != null) {
            asyncTaskC1970i2.cancel(true);
        }
        AsyncTaskC1970i2 asyncTaskC1970i22 = new AsyncTaskC1970i2(this, null);
        this.f9979w0 = asyncTaskC1970i22;
        try {
            asyncTaskC1970i22.executeOnExecutor(f9793Y2, new Void[0]);
        } catch (IllegalStateException e9) {
            Log.e("ChatDialogActivity", "", e9);
        }
    }

    /* renamed from: B7 */
    public final void m11443B7() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        this.f9899c0.m15734m("user", "queryMsgSubscription", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.w
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f22465a.m11087F8(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: Ba */
    public void m11444Ba(MessageObj.MessageType messageType, String str, int i9, Date date) {
        C2925v.m14545B0(messageType, str, i9, date, Collections.singletonList(this.f9914g), this.f9801B2, false);
    }

    /* renamed from: Bb */
    public final void m11445Bb() {
        if (this.f9970u != null) {
            final int iM14483G = C2907m0.m14454I().m14483G(this.f9914g.f13723j);
            if (this.f9795A0 == Tab.Chats) {
                C2907m0.m14454I().m14515y(this.f9914g.f13723j, false);
            }
            runOnUiThread(new Runnable() { // from class: y2.t
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22429b.m11120J9(iM14483G);
                }
            });
        }
    }

    /* renamed from: C7 */
    public final int m11446C7(View view, int... iArr) {
        if (view.getVisibility() != 0) {
            return 0;
        }
        int dimensionPixelSize = 0;
        for (int i9 : iArr) {
            dimensionPixelSize += getResources().getDimensionPixelSize(i9);
        }
        return dimensionPixelSize;
    }

    /* renamed from: Ca */
    public final void m11447Ca(String str, MessageObj messageObj) {
        C6196d0.m23692d().m23700j(new C6201i(str, messageObj));
    }

    /* renamed from: Cb */
    public final void m11448Cb() throws Resources.NotFoundException {
        Activity activityM11582n7 = m11582n7();
        boolean zM14204A0 = XMPPManager.m14184g0().m14204A0();
        if (!zM14204A0) {
            if (C6456d.m24714D().m24748G()) {
                String string = activityM11582n7.getResources().getString(R.string.connecting);
                if (Globals.m7388i0().m7534d2()) {
                    string = string + " (" + C6456d.m24714D().m24746E() + ")";
                } else {
                    zM14204A0 = true;
                }
                this.f9954q.setText(string);
            } else {
                this.f9954q.setText(activityM11582n7.getResources().getString(R.string.error_no_network));
            }
        }
        this.f9954q.setVisibility(zM14204A0 ? 8 : 0);
    }

    /* renamed from: D7 */
    public final MessageObj m11449D7(C2904l c2904l) {
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null && !c1954e2.isEmpty() && c2904l.m14422j().before(this.f9810E.getItem(0).m14788z())) {
            return null;
        }
        String strM7587o0 = Globals.m7388i0().m7587o0();
        if ((TextUtils.equals(c2904l.m14418h(), this.f9910f) && TextUtils.equals(c2904l.m14388C(), strM7587o0)) || (TextUtils.equals(c2904l.m14418h(), strM7587o0) && TextUtils.equals(c2904l.m14388C(), this.f9910f))) {
            if (!c2904l.m14418h().equals(String.valueOf(Globals.m7388i0().m7568k1())) && !this.f9877V && !c2904l.m14397L() && !c2904l.m14430n().equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                m11514V9(c2904l);
            }
            MessageObj messageObjM14449w0 = c2904l.m14449w0(String.valueOf(this.f9914g.f13727n));
            if (messageObjM14449w0 != null) {
                return messageObjM14449w0;
            }
        }
        return null;
    }

    /* renamed from: Da */
    public final void m11450Da(MessageObj messageObj) {
        C2925v.m14551E0(messageObj, this.f9914g, false);
    }

    /* renamed from: Db */
    public final void m11451Db() {
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.q
            @Override // java.lang.Runnable
            public final void run() {
                this.f22397b.m11129K9();
            }
        });
    }

    /* renamed from: E7 */
    public final void m11452E7() {
        runOnUiThread(new Runnable() { // from class: y2.q0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22398b.m11095G8();
            }
        });
    }

    /* renamed from: Ea */
    public final void m11453Ea(MessageObj messageObj) {
        C2925v.m14555G0(messageObj, this.f9914g);
    }

    /* renamed from: Eb */
    public final void m11454Eb() {
        int checkedItemCount = this.f9946o.getCheckedItemCount();
        if (checkedItemCount == 0) {
            this.f9934l.setEnabled(false);
        } else if (!this.f9934l.isEnabled()) {
            this.f9934l.setEnabled(true);
        }
        String str = " (" + checkedItemCount + ")";
        if (this.f9983x0.equals(ChatDialogMode.DELETE)) {
            this.f9934l.setText(getResources().getString(R.string.menu_delete) + str);
        } else if (this.f9983x0.equals(ChatDialogMode.RECALL)) {
            this.f9934l.setText(getResources().getString(R.string.call_back) + str);
        } else if (this.f9983x0.equals(ChatDialogMode.FORWARD) || this.f9983x0.equals(ChatDialogMode.FORWARD_PHOTO_WITH_COMMENT)) {
            this.f9934l.setText(getResources().getString(R.string.forward) + str);
        } else if (this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_DEVICE) || this.f9983x0.equals(ChatDialogMode.SAVE_TO_MY_ALBUM) || this.f9983x0.equals(ChatDialogMode.SAVE_TO_GROUP_ALBUM)) {
            this.f9934l.setText(getResources().getString(R.string.menu_save_to_camera_roll) + str);
        } else if (this.f9983x0.equals(ChatDialogMode.ADD_TO_TODO)) {
            this.f9934l.setText(getResources().getString(R.string.add_to_todo) + str);
        }
        C5179r0.m20247b(this.f9934l, 1);
    }

    /* renamed from: F7 */
    public final void m11455F7(String str) {
        if (str != null && this.f9914g.f13727n == Long.parseLong(str)) {
            Group groupM15077n = C2950b0.m14912k().m15077n(str);
            if (groupM15077n == null) {
                Log.d("ChatDialogActivity", "[handleGroupUpdate] group does not in database");
            } else {
                this.f9914g.m15758p(groupM15077n);
                m11451Db();
            }
        }
    }

    /* renamed from: Fa */
    public final void m11456Fa(ArrayList<ImageItem> arrayList, int i9, Date date) {
        final List<MessageObj> listM14557H0 = C2925v.m14557H0(arrayList, i9, date, Collections.singletonList(this.f9914g), false, false);
        runOnUiThread(new Runnable() { // from class: y2.p0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22392b.m11392t9(listM14557H0);
            }
        });
    }

    /* renamed from: Fb */
    public final void m11457Fb(long j9) {
        this.f9914g.f13737x = m11594r7();
        C2950b0.m14912k().m15062A(String.valueOf(j9), this.f9914g, "DraftText");
    }

    /* renamed from: G7 */
    public final void m11458G7(final String str, String str2, String str3, String str4) throws NumberFormatException {
        Log.d("ChatDialogActivity", "[handleGroupUpdate]");
        Group group = this.f9914g;
        if (group == null || str2 == null || !str2.equals(String.valueOf(group.f13727n))) {
            Log.d("ChatDialogActivity", "[handleGroupUpdate] not this group");
            return;
        }
        if (str.equals("group.member.leaved") || str.equals("group.member.deleted")) {
            if (str3 != null && !str3.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                this.f9927j0.remove(Long.valueOf(Long.parseLong(str3)));
            } else if (str3 != null && str3.equals(String.valueOf(Globals.m7388i0().m7568k1())) && str.equals("group.member.deleted")) {
                if (C2950b0.m14912k().m15077n(str2) == null) {
                    finish();
                    return;
                }
                return;
            }
        }
        if (str.equals("group.member.created") || str.equals("group.member.created.v2")) {
            m11463Hb(str3);
        }
        final Group groupM15077n = C2950b0.m14912k().m15077n(str2);
        if (groupM15077n == null) {
            Log.d("ChatDialogActivity", "[handleGroupUpdate] group does not in database");
            return;
        }
        if ((("Community".equals(this.f9914g.f13705D) && this.f9914g.f13704C != groupM15077n.f13704C) || this.f9914g.f13711J != groupM15077n.f13711J) && this.f9980w1 != null) {
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.r0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22410b.m11103H8(groupM15077n);
                }
            });
        }
        this.f9914g.m15758p(groupM15077n);
        if (str4 != null) {
            final MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str4);
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.s0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22420b.m11111I8(str, messageObjM15179r);
                }
            });
        }
        C6385v.m24527e(new RunnableC6747u0(this));
        C6385v.m24527e(new Runnable() { // from class: y2.v0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22450b.m11498S6();
            }
        });
    }

    /* renamed from: Ga */
    public final void m11459Ga(String str) {
        String strM14779q;
        String str2;
        MessageObj messageObj = this.f9975v0;
        if (messageObj != null) {
            MessageObj.MessageType messageTypeM14778p = messageObj.m14778p();
            MessageObj.MessageType messageType = MessageObj.MessageType.ReplyText;
            if (messageTypeM14778p.equals(messageType)) {
                strM14779q = this.f9975v0.m14747I("replyText");
            } else if (this.f9975v0.m14778p().equals(MessageObj.MessageType.File)) {
                strM14779q = this.f9975v0.m14747I("mediaName");
                str2 = "File";
                m11444Ba(messageType, C2925v.m14564L(this.f9975v0.m14777o(), strM14779q, this.f9975v0.m14745G(), str.trim(), str2), 0, null);
                this.f9975v0 = null;
            } else {
                strM14779q = this.f9975v0.m14779q();
            }
            str2 = null;
            m11444Ba(messageType, C2925v.m14564L(this.f9975v0.m14777o(), strM14779q, this.f9975v0.m14745G(), str.trim(), str2), 0, null);
            this.f9975v0 = null;
        }
        CLUtility.m16589t1(this);
        this.f9974v.setVisibility(8);
        this.f9981w2.m12068T();
        m11559gb();
    }

    /* renamed from: Gb */
    public final void m11460Gb(Long l9) {
        Friend friendM15001A;
        if (l9 == null || l9.longValue() == 0 || (friendM15001A = C2950b0.m14899A().m15001A(String.valueOf(l9))) == null) {
            return;
        }
        this.f9927j0.put(l9, friendM15001A);
    }

    /* renamed from: H7 */
    public final void m11461H7() {
        this.f9831J0 = null;
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("messageID");
            String stringExtra2 = intent.getStringExtra("SearchChat");
            Log.d("ChatDialogActivity", "[handleScrollToSearchMessage] msgId = " + stringExtra);
            if (stringExtra != null) {
                this.f9831J0 = C2950b0.m14916o().m15179r(stringExtra);
            }
            MessageObj messageObj = this.f9831J0;
            if (messageObj != null) {
                m11606ua(messageObj, stringExtra2);
                return;
            }
        }
        if (this.f9795A0 == Tab.Chats) {
            if (!this.f9867S0.f10224p) {
                this.f9946o.setSelection(this.f9935l0);
                return;
            }
            View childAt = this.f9946o.getChildAt(r0.getChildCount() - 1);
            int height = childAt == null ? 0 : childAt.getHeight();
            this.f9946o.setSelectionFromTop(r1.getCount() - 1, -height);
        }
    }

    /* renamed from: Ha */
    public final void m11462Ha(MessageObj messageObj, String str) {
        UploadUtils.m16965l("Upload Performance", "--- 0.2 sendSnapShot(MessageObj); ==== start");
        if (messageObj == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            ImageItem imageItem = new ImageItem("", CLUtility.m16540h0(), "", str, file.getName(), 0, -1, "");
            String str2 = this.f9914g.f13720g;
            UploadUtils.m16965l("Upload Performance", "--- 0.2 sendSnapShot(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
            UploadMultipleChatMediaHelperQueue.m16892F().m16945x(false, str2, imageItem, messageObj, this.f9910f, str);
            UploadUtils.m16965l("Upload Performance", "--- 0.2 sendSnapShot(MessageObj); ==== end");
        }
    }

    /* renamed from: Hb */
    public final void m11463Hb(String str) throws NumberFormatException {
        if (str == null || str.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            return;
        }
        Friend friendM15001A = C2950b0.m14899A().m15001A(str);
        if (friendM15001A != null) {
            this.f9927j0.put(Long.valueOf(friendM15001A.f13645c), friendM15001A);
            return;
        }
        Friend friendM11440A7 = m11440A7(str);
        if (friendM11440A7 != null) {
            this.f9927j0.put(Long.valueOf(friendM11440A7.f13645c), friendM11440A7);
        } else {
            new AsyncTaskC1988o(str).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: I7 */
    public final void m11464I7(final String str, String str2, String str3, final String str4, String str5) {
        if (str2 == null || !str2.equals(String.valueOf(this.f9914g.f13727n)) || str3 == null) {
            return;
        }
        if ("media.album.updated".equals(str)) {
            final MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str3);
            runOnUiThread(new Runnable() { // from class: y2.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22438b.m11119J8(messageObjM15179r);
                }
            });
        }
        runOnUiThread(new Runnable() { // from class: y2.v
            @Override // java.lang.Runnable
            public final void run() {
                this.f22447b.m11128K8(str, str4);
            }
        });
    }

    /* renamed from: Ia */
    public final void m11465Ia(StickerObj stickerObj, int i9, Date date) {
        C2925v.m14561J0(stickerObj, i9, date, Collections.singletonList(this.f9914g), this.f9801B2, false);
    }

    /* renamed from: Ib */
    public final void m11466Ib() {
        C3072a c3072a;
        if (Tab.Albums != this.f9795A0 || ((c3072a = this.f9868S1) != null && c3072a.getCount() > 0)) {
            this.f9864R1.setVisibility(4);
        } else {
            this.f9864R1.setVisibility(0);
        }
    }

    /* renamed from: J7 */
    public final void m11467J7(C2904l c2904l) {
        if (c2904l == null) {
            ULogUtility.m16670f("ChatDialogActivity", "[handleMeetingEvent] xmppMessage is null");
            return;
        }
        final MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(c2904l.m14446v());
        if (messageObjM15179r == null) {
            ULogUtility.m16670f("ChatDialogActivity", "[handleMeetingEvent] message was null in db: " + c2904l.m14446v());
            return;
        }
        if (messageObjM15179r.m14772j().equals(String.valueOf(this.f9914g.f13727n))) {
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.g1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22250b.m11137L8(messageObjM15179r);
                }
            });
        } else {
            ULogUtility.m16680p("ChatDialogActivity", "[handleMeetingEvent] message is not for this chatRoom, skip it.");
        }
    }

    /* renamed from: Ja */
    public final void m11468Ja(String str) {
        String strTrim = str.trim();
        if (strTrim.equals("")) {
            return;
        }
        C2026a c2026a = this.f9889Z;
        if (c2026a != null && c2026a.m12020q(strTrim)) {
            this.f9981w2.m12068T();
            return;
        }
        m11444Ba(MessageObj.MessageType.Text, strTrim, 0, null);
        this.f9981w2.m12068T();
        this.f9981w2.m12067S();
    }

    /* renamed from: Jb */
    public final void m11469Jb(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: y2.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f22186b.m11138L9(z8);
            }
        });
    }

    /* renamed from: K7 */
    public final void m11470K7(String str) {
        if (str == null) {
            return;
        }
        synchronized (this.f9862R) {
            final MessageObj messageObjM11608v7 = m11608v7(str);
            if (messageObjM11608v7 != null && this.f9810E != null) {
                m11582n7().runOnUiThread(new Runnable() { // from class: y2.h1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22267b.m11146M8(messageObjM11608v7);
                    }
                });
            }
        }
    }

    /* renamed from: Ka */
    public final void m11471Ka(List<Friend> list, int i9, Date date) {
        if (list == null || list.size() <= 0) {
            return;
        }
        m11596rb(true, 150L);
        AsyncTaskC1966h2 asyncTaskC1966h2 = new AsyncTaskC1966h2();
        for (Friend friend : list) {
            asyncTaskC1966h2.m11880a(C2925v.m14558I(String.valueOf(this.f9914g.f13727n), MessageObj.MessageType.UserContact, C2925v.m14578V(String.valueOf(friend.f13645c), friend.m15620a()), i9, date));
        }
        asyncTaskC1966h2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: Kb */
    public final void m11472Kb(List<Friend> list) {
        Friend friendM15001A;
        for (Friend friend : list) {
            this.f9927j0.put(Long.valueOf(friend.f13645c), friend);
            if (this.f9914g.f13716c.equals("Dual") && (friendM15001A = C2950b0.m14899A().m15001A(String.valueOf(friend.f13645c))) != null) {
                this.f9927j0.put(Long.valueOf(friend.f13645c), friendM15001A);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a4 A[Catch: JSONException -> 0x00b4, all -> 0x00d2, TryCatch #1 {JSONException -> 0x00b4, blocks: (B:18:0x002b, B:20:0x004e, B:44:0x00ac, B:41:0x0092, B:42:0x009b, B:43:0x00a4, B:27:0x006c, B:30:0x0076, B:33:0x0080), top: B:55:0x002b, outer: #0 }] */
    /* renamed from: L7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m11473L7(String str) {
        char c9;
        if (str == null) {
            return;
        }
        synchronized (this.f9862R) {
            final MessageObj messageObjM11608v7 = m11608v7(str);
            if (messageObjM11608v7 == null) {
                return;
            }
            if (this.f9810E == null) {
                return;
            }
            if (C2950b0.m14916o().m15169h(messageObjM11608v7.m14777o()) && MessageObj.MessageType.Comment == messageObjM11608v7.m14778p()) {
                try {
                    JSONObject jSONObject = new JSONObject(messageObjM11608v7.m14779q());
                    String string = jSONObject.getString("mediaId");
                    String string2 = jSONObject.getString("commentType");
                    C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(Long.parseLong(string));
                    if (c2973l0M14725v != null) {
                        c2973l0M14725v.m15123J(c2973l0M14725v.m15150v() - 1);
                        int iHashCode = string2.hashCode();
                        if (iHashCode == 2603341) {
                            if (string2.equals("Text")) {
                                c9 = 2;
                            }
                            if (c9 == 0) {
                            }
                            C2950b0.m14914m().m14712i(c2973l0M14725v);
                        } else if (iHashCode != 63613878) {
                            c9 = (iHashCode == 2052699449 && string2.equals("Doodle")) ? (char) 0 : (char) 65535;
                            if (c9 == 0) {
                                c2973l0M14725v.m15115B(c2973l0M14725v.m15133e() - 1);
                            } else if (c9 == 1) {
                                c2973l0M14725v.m15116C(c2973l0M14725v.m15134f() - 1);
                            } else if (c9 == 2) {
                                c2973l0M14725v.m15117D(c2973l0M14725v.m15135g() - 1);
                            }
                            C2950b0.m14914m().m14712i(c2973l0M14725v);
                        } else {
                            if (string2.equals("Audio")) {
                                c9 = 1;
                            }
                            if (c9 == 0) {
                            }
                            C2950b0.m14914m().m14712i(c2973l0M14725v);
                        }
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            new AsyncTaskC1984m1(messageObjM11608v7).executeOnExecutor(f9793Y2, new Void[0]);
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.e0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22223b.m11155N8(messageObjM11608v7);
                }
            });
        }
    }

    /* renamed from: La */
    public final void m11474La(MessageObj messageObj) throws NumberFormatException {
        UploadUtils.m16965l("Upload Performance", "0.2 sendVideo(MessageObj); ==== start");
        if (messageObj == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        String strM14747I = messageObj.m14747I("videoPath");
        String strM14747I2 = messageObj.m14747I("videoUri");
        Uri uri = TextUtils.isEmpty(strM14747I2) ? null : Uri.parse(strM14747I2);
        String strM14747I3 = messageObj.m14747I("videoId");
        String strM14747I4 = messageObj.m14747I("videoThumbPath");
        String strM14747I5 = messageObj.m14747I("duration");
        String strM14747I6 = messageObj.m14747I("isServerTranscode");
        String strM14747I7 = messageObj.m14747I("width");
        String strM14747I8 = messageObj.m14747I("height");
        if (strM14747I == null && strM14747I2 == null) {
            UploadUtils.m16965l("ChatDialogActivity", "[sendVideo] Can't get video path or uri");
            return;
        }
        if (!CLUtility.m16613z1(strM14747I, uri)) {
            UploadUtils.m16965l("ChatDialogActivity", "[sendVideo] Video file is not exist.");
            return;
        }
        if (strM14747I4 == null) {
            UploadUtils.m16965l("ChatDialogActivity", "[sendVideo] Can't get video thumbnail path");
            return;
        }
        if (!new File(strM14747I4).exists()) {
            UploadUtils.m16965l("ChatDialogActivity", "[sendVideo] Video thumbnail file is not exist.");
            return;
        }
        VideoItem videoItem = new VideoItem("", Long.parseLong(strM14747I3), strM14747I, strM14747I2, strM14747I4, CLUtility.m16552k0(strM14747I, uri), Long.parseLong(strM14747I5), strM14747I6 != null ? Boolean.parseBoolean(strM14747I6) : false, strM14747I7 != null ? Integer.parseInt(strM14747I7) : 0, strM14747I8 != null ? Integer.parseInt(strM14747I8) : 0);
        String str = this.f9914g.f13720g;
        UploadUtils.m16965l("Upload Performance", "0.2 sendVideo(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
        UploadMultipleChatMediaHelperQueue.m16892F().m16947z(this.f9914g.f13711J, str, videoItem, messageObj, this.f9910f, false);
        UploadUtils.m16965l("Upload Performance", "0.2 sendVideo(MessageObj); ==== end");
    }

    /* renamed from: Lb */
    public final void m11475Lb(boolean z8) {
        C3072a c3072a = this.f9868S1;
        if (c3072a == null || c3072a.getCount() == 0) {
            return;
        }
        int iMax = Math.max((z8 ? this.f9946o.getFirstVisiblePosition() : 0) - this.f9946o.getHeaderViewsCount(), 0);
        int iMin = Math.min(iMax + 3, this.f9868S1.getCount() - 1);
        while (iMax <= iMin) {
            if (iMax <= this.f9868S1.getCount() - 1) {
                m11605u7((GroupAlbumObj) this.f9868S1.getItem(iMax));
            }
            iMax++;
        }
    }

    /* renamed from: M7 */
    public final void m11476M7(String str, String str2) {
        Group groupM15077n;
        if (str == null || this.f9914g.f13727n != Long.parseLong(str) || (groupM15077n = C2950b0.m14912k().m15077n(str)) == null) {
            return;
        }
        groupM15077n.f13733t = Boolean.parseBoolean(str2);
        this.f9914g.m15758p(groupM15077n);
        runOnUiThread(new Runnable() { // from class: y2.f1
            @Override // java.lang.Runnable
            public final void run() {
                this.f22234b.m11164O8();
            }
        });
    }

    /* renamed from: Ma */
    public final void m11477Ma(MessageObj messageObj, VideoItem videoItem) {
        UploadUtils.m16965l("Upload Performance", "0.2. sendVideo(MessageObj); ==== start");
        if (messageObj == null || videoItem == null) {
            return;
        }
        messageObj.f12924A = false;
        messageObj.f12959z = 0;
        String str = this.f9914g.f13718e;
        UploadUtils.m16965l("Upload Performance", "0.2 sendVoice(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addMedia()");
        UploadMultipleChatMediaHelperQueue.m16892F().m16947z(this.f9914g.f13711J, str, videoItem, messageObj, this.f9910f, false);
        UploadUtils.m16965l("Upload Performance", "0.2. sendVoice(MessageObj); ==== end");
    }

    /* renamed from: Mb */
    public final void m11478Mb() {
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.g0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22249b.m11147M9();
            }
        });
    }

    /* renamed from: N7 */
    public final void m11479N7() {
        runOnUiThread(new Runnable() { // from class: y2.k0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22332b.m11493Qb();
            }
        });
    }

    /* renamed from: Na */
    public final void m11480Na(VideoItem videoItem, int i9, Date date) {
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
                MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(this.f9914g.f13727n), MessageObj.MessageType.Video, strM14579W, i9, date);
                UploadUtils.m16965l("Upload Performance", "0.1. getMessageDao().insert();");
                C2950b0.m14916o().m15157B(messageObjM14558I);
                UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(messageObj);");
                m11477Ma(messageObjM14558I, videoItem);
                C1954e2 c1954e2 = this.f9810E;
                if (c1954e2 != null) {
                    c1954e2.m11781W(messageObjM14558I);
                    m11596rb(true, 150L);
                }
            }
            UploadUtils.m16965l("Upload Performance", "0.1 sendVoice(voicePath); ==== end");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: Nb */
    public final void m11481Nb(int i9, int i10) {
        C1954e2 c1954e2;
        int headerViewsCount = this.f9946o.getHeaderViewsCount();
        int firstVisiblePosition = this.f9946o.getFirstVisiblePosition() - headerViewsCount;
        int lastVisiblePosition = this.f9946o.getLastVisiblePosition() - headerViewsCount;
        if (i9 < firstVisiblePosition || i9 > lastVisiblePosition) {
            return;
        }
        View childAt = this.f9946o.getChildAt(i9 - firstVisiblePosition);
        if (childAt == null || (c1954e2 = this.f9810E) == null) {
            return;
        }
        c1954e2.m11815k2(childAt, i10);
    }

    /* renamed from: O7 */
    public final void m11482O7() {
        ArrayList arrayList;
        Intent intent = getIntent();
        if (intent == null || (arrayList = (ArrayList) intent.getSerializableExtra("import_images")) == null || arrayList.size() <= 0) {
            return;
        }
        HashMap map = new HashMap();
        map.put("importImages", arrayList);
        this.f9797A2.mo7011a("sendPhoto", map);
        intent.removeExtra("import_images");
    }

    /* renamed from: Oa */
    public final void m11483Oa(MessageObj messageObj) {
        C2925v.m14565L0(messageObj, this.f9914g);
    }

    /* renamed from: Ob */
    public void m11484Ob() {
        new AsyncTaskC1991p().executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: P6 */
    public final void m11485P6(List<Friend> list) {
        this.f9898c = ProgressDialog.show(this, "", getString(R.string.loading), true);
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: y2.p
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f22391a.m11336m8(str, str2, str3, str4);
            }
        };
        this.f9920h1 = interfaceC3051i;
        this.f9899c0.m15737x(this.f9914g.f13727n, list, interfaceC3051i);
    }

    /* renamed from: P7 */
    public final void m11486P7() {
        this.f9819G0 = new C3114a(this, new C3114a.d() { // from class: y2.z0
            @Override // com.cyberlink.you.transcode.C3114a.d
            /* renamed from: a */
            public final void mo12439a(Map map) {
                this.f22520a.m11172P8(map);
            }
        });
        HashMap map = (HashMap) getIntent().getSerializableExtra("android.intent.extra.STREAM");
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            this.f9819G0.m16337u((Uri) entry.getValue(), (String) entry.getKey());
        }
    }

    /* renamed from: Pa */
    public final void m11487Pa(String str, String str2, int i9, Date date) {
        List<MessageObj> listM14563K0 = C2925v.m14563K0(str, str2, i9, date, Collections.singletonList(this.f9914g), false);
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            c1954e2.m11784X(listM14563K0);
            m11596rb(true, 150L);
        }
    }

    /* renamed from: Pb */
    public final void m11488Pb() {
        Log.d("ChatDialogActivity", "updateStickerList");
        if (Globals.m7388i0().m7573l0()) {
            Log.d("ChatDialogActivity", "updateStickerList checkAndResetSticker");
            this.f9981w2.m12083y().m19493N();
            Globals.m7388i0().m7530c3(false);
        }
    }

    /* renamed from: Q6 */
    public final void m11489Q6(final List<String> list) throws JSONException {
        boolean z8;
        final Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(Globals.m7388i0().m7543f0()));
        if (groupM15077n == null && (groupM15077n = C2950b0.m14912k().m15085v()) != null) {
            Globals.m7388i0().m7485S2(groupM15077n.f13727n);
        }
        if (groupM15077n == null) {
            ULogUtility.m16670f("ChatDialogActivity", "[addMessageToDo] can not find todo group");
            return;
        }
        if (groupM15077n.f13711J || this.f9914g.f13711J) {
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z8 = false;
                    break;
                }
                MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(it.next());
                if (messageObjM15179r != null && messageObjM15179r.m14778p() != MessageObj.MessageType.Text && messageObjM15179r.m14778p() != MessageObj.MessageType.ReplyText && messageObjM15179r.m14778p() != MessageObj.MessageType.ENCRYPTED_MSG) {
                    z8 = true;
                    break;
                }
            }
            if (z8) {
                runOnUiThread(new Runnable() { // from class: y2.b2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22191b.m11352o8();
                    }
                });
                return;
            }
        }
        C6385v.m24527e(new Runnable() { // from class: y2.c2
            @Override // java.lang.Runnable
            public final void run() {
                this.f22204b.m11360p8();
            }
        });
        View viewFindViewById = this.f9826I.findViewById(R.id.senderNameToggle);
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(Globals.m7372O());
        if (userInfoM16497V0 != null && viewFindViewById.isSelected() == userInfoM16497V0.f13800y) {
            m11575kb(groupM15077n, list);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("profile.forward.include.sender", viewFindViewById.isSelected() ? "1" : "0");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("attrs", jSONObject.toString()));
            this.f9899c0.m15734m("user", "updateUser", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.d2
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f22213a.m11368q8(groupM15077n, list, str, str2, str3, str4);
                }
            });
        } catch (Exception e9) {
            ULogUtility.m16676l("ChatDialogActivity", "update myself info fail exception:" + e9.getMessage());
        }
    }

    /* renamed from: Q7 */
    public final void m11490Q7() {
        Log.d("ChatDialogActivity", "[handleStickerChanged] resetTopicList");
        m11602ta();
    }

    /* renamed from: Q9 */
    public final void m11491Q9(final String str) {
        this.f9899c0.m15697A0(Long.parseLong(str), new FriendsClient.InterfaceC3051i() { // from class: y2.m1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f22356a.m11244Z8(str, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: Qa */
    public final void m11492Qa(Tab tab) {
        if (!tab.equals(Tab.Chats)) {
            this.f9950p.setVisibility(8);
            return;
        }
        this.f9950p.setVisibility(0);
        this.f9950p.setAlpha(0.7f);
        this.f9950p.animate().setStartDelay(AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS).alpha(BitmapDescriptorFactory.HUE_RED).start();
    }

    /* renamed from: Qb */
    public final void m11493Qb() {
        final int iM14487L = C2907m0.m14454I().m14487L(this.f9914g.f13727n);
        runOnUiThread(new Runnable() { // from class: y2.f0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22232b.m11156N9(iM14487L);
            }
        });
    }

    /* renamed from: R6 */
    public final void m11494R6() {
        if (this.f9914g != null) {
            final SharedPreferences sharedPreferences = getSharedPreferences("U", 0);
            if (sharedPreferences.getBoolean("isShowE2EEReminder", true)) {
                this.f9807D0.setVisibility(this.f9914g.f13711J ? 0 : 8);
                findViewById(R.id.e2eeLearMoreTextView).setOnClickListener(new View.OnClickListener() { // from class: y2.a0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f22178b.m11376r8(view);
                    }
                });
                findViewById(R.id.e2eeCloseTextView).setOnClickListener(new View.OnClickListener() { // from class: y2.b0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f22188b.m11384s8(sharedPreferences, view);
                    }
                });
            }
            this.f9811E0.setVisibility(this.f9914g.f13711J ? 0 : 8);
            C2027b c2027b = this.f9981w2;
            if (c2027b != null) {
                c2027b.m12080v();
            }
        }
    }

    /* renamed from: R7 */
    public final void m11495R7(String str) {
        final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
        if (topicObjM14984n != null) {
            runOnUiThread(new Runnable() { // from class: y2.c1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22202b.m11180Q8(topicObjM14984n);
                }
            });
        }
    }

    /* renamed from: R9 */
    public final void m11496R9() {
        if (this.f9892a0 != 0) {
            C6566a.m25153l("EnterChatRoom_TakenTime", C5176q0.m20224c(System.currentTimeMillis() - this.f9892a0));
            this.f9892a0 = 0L;
        }
    }

    /* renamed from: Ra */
    public final void m11497Ra(ViewGroup viewGroup, boolean z8) {
        viewGroup.getChildAt(0).setSelected(z8);
        viewGroup.getChildAt(1).setSelected(z8);
    }

    /* renamed from: S6 */
    public final void m11498S6() {
        Group group = this.f9914g;
        if (group == null || !Group.m15743f(group.f13716c)) {
            return;
        }
        this.f9990z.setVisibility(!C2925v.m14625v(this.f9914g) ? 0 : 4);
        View view = this.f9823H0;
        Group group2 = this.f9914g;
        view.setVisibility((group2.f13712K || group2.f13711J || group2.m15751i()) ? 8 : 0);
        if (this.f9946o.getHeaderViewsCount() == 0 && this.f9823H0.getVisibility() == 0) {
            C2014w1 c2014w1 = new C2014w1(this.f9946o, this.f9823H0);
            this.f9867S0 = c2014w1;
            c2014w1.m25323g(this);
        }
        TextView textView = (TextView) findViewById(R.id.coverInputBarText);
        if (textView != null) {
            textView.setText(getResources().getString(R.string.chat_dialog_disabled_by_broadcaster));
        }
        Tab tab = this.f9795A0;
        Tab tab2 = Tab.Chats;
        if (tab != tab2 && this.f9914g.f13712K) {
            m11511Ua(tab2, true);
        }
        ListView listView = this.f9946o;
        if (listView == null || this.f9810E == null) {
            return;
        }
        listView.post(new Runnable() { // from class: y2.h0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22266b.m11391t8();
            }
        });
    }

    /* renamed from: S7 */
    public final void m11499S7() {
        runOnUiThread(new Runnable() { // from class: y2.o0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22380b.m11187R8();
            }
        });
    }

    /* renamed from: S9 */
    public final CharSequence m11500S9(Drawable drawable, String str) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        SpannableString spannableString = new SpannableString("    " + str);
        spannableString.setSpan(new ImageSpan(drawable, 2), 0, 1, 33);
        return spannableString;
    }

    /* renamed from: Sa */
    public final void m11501Sa(MessageObj messageObj, List<C6696m5> list) {
        if (C2925v.m14596g0(messageObj)) {
            list.add(this.f9905d2);
            list.add(this.f9909e2);
        } else if (C2925v.m14598h0(messageObj)) {
            list.add(this.f9941m2);
        }
    }

    /* renamed from: Sb */
    public final void m11502Sb() {
        Group group = this.f9914g;
        if (group == null) {
            return;
        }
        if (!group.f13716c.equals("Dual")) {
            if (this.f9914g.f13728o < 999) {
                this.f9938m.setText(" (" + this.f9914g.f13728o + ")");
            } else {
                this.f9938m.setText(" (999+)");
            }
        }
        m11604u2();
        this.f9942n.setText(this.f9914g.f13717d);
    }

    /* renamed from: T6 */
    public final void m11503T6(Tab tab) {
        m11497Ra(this.f9834K, false);
        m11497Ra(this.f9838L, false);
        m11497Ra(this.f9842M, false);
        m11497Ra(this.f9846N, false);
        int i9 = C1999r1.f10203a[tab.ordinal()];
        if (i9 == 1) {
            m11497Ra(this.f9834K, true);
            return;
        }
        if (i9 == 2) {
            m11497Ra(this.f9838L, true);
        } else if (i9 == 3) {
            m11497Ra(this.f9842M, true);
        } else {
            if (i9 != 4) {
                return;
            }
            m11497Ra(this.f9846N, true);
        }
    }

    /* renamed from: T7 */
    public final void m11504T7(final String str) {
        runOnUiThread(new Runnable() { // from class: y2.s
            @Override // java.lang.Runnable
            public final void run() {
                this.f22418b.m11195S8(str);
            }
        });
    }

    /* renamed from: T9 */
    public final boolean m11505T9(String str) {
        if (this.f9914g != null) {
            return false;
        }
        C5154j.m20075i("Group is absent: " + str);
        C5187v0.m20270f(R.string.query_friend_error);
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
        intent.putExtra("Tab_Index", 0);
        startActivity(intent);
        finish();
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0129  */
    /* renamed from: Ta */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m11506Ta(int i9, MessageObj messageObj, List<C6696m5> list) {
        boolean z8;
        StickerObj stickerObjM11617y7;
        boolean z9;
        Log.d("ChatDialogActivity", "[onCreateContextMenu]Original Rule");
        switch (i9) {
            case 0:
            case 18:
            case 20:
            case 21:
            case 43:
            case 46:
                z8 = true;
                if (20 == i9) {
                    list.add(this.f9953p2);
                }
                list.add(this.f9897b2);
                if (21 == i9 || 43 == i9 || 46 == i9) {
                    list.add(this.f9945n2);
                }
                if (i9 == 0 || 18 == i9 || 20 == i9) {
                    list.add(this.f9949o2);
                }
                list.add(this.f9913f2);
                list.add(this.f9901c2);
                list.add(this.f9905d2);
                list.add(this.f9909e2);
                if (21 == i9 || 43 == i9 || 46 == i9) {
                    list.remove(this.f9909e2);
                    list.remove(this.f9913f2);
                }
                if (i9 == 0 || 18 == i9 || 20 == i9) {
                    list.remove(this.f9905d2);
                }
                list.add(this.f9957q2);
                if (!this.f9914g.m15751i()) {
                    list.add(this.f9961r2);
                }
                if (messageObj.m14752N()) {
                    list.remove(this.f9897b2);
                    list.remove(this.f9949o2);
                    list.remove(this.f9961r2);
                    list.remove(this.f9949o2);
                    list.remove(this.f9905d2);
                }
                if (messageObj.m14740B().equals("0") || messageObj.m14740B().equals("6") || messageObj.m14740B().equals("5")) {
                    list.remove(this.f9909e2);
                } else if (messageObj.m14740B().equals("3")) {
                    list.remove(this.f9913f2);
                }
                list.add(this.f9965s2);
                break;
            case 1:
            case 2:
            case 4:
            case 11:
            case 22:
            case 23:
            case 25:
            case 32:
                list.add(this.f9905d2);
                list.add(this.f9909e2);
                list.add(this.f9917g2);
                list.add(this.f9921h2);
                list.add(this.f9913f2);
                if (23 == i9 || 22 == i9 || 25 == i9) {
                    list.remove(this.f9909e2);
                    list.remove(this.f9913f2);
                }
                if (2 != i9) {
                    z8 = true;
                    if (1 == i9 || 4 == i9 || 11 == i9) {
                    }
                    if (messageObj.m14752N()) {
                        list.remove(this.f9905d2);
                    }
                    if (!messageObj.m14740B().equals("0") || messageObj.m14740B().equals("6") || messageObj.m14740B().equals("5")) {
                        list.remove(this.f9909e2);
                    } else if (messageObj.m14740B().equals("3")) {
                        list.remove(this.f9913f2);
                    }
                    stickerObjM11617y7 = m11617y7(messageObj);
                    if (stickerObjM11617y7 != null) {
                        StickerPackObj stickerPackObjM15293k = C2950b0.m14925x().m15293k(stickerObjM11617y7.m16284i());
                        if (stickerPackObjM15293k == null || !stickerPackObjM15293k.m14815s() || stickerPackObjM15293k.m14811o().equals(StickerPackObj.Status.NONE)) {
                            list.remove(this.f9921h2);
                        }
                    }
                    list.add(this.f9957q2);
                    break;
                } else {
                    z8 = true;
                }
                list.remove(this.f9905d2);
                if (messageObj.m14752N()) {
                }
                if (messageObj.m14740B().equals("0")) {
                    list.remove(this.f9909e2);
                    stickerObjM11617y7 = m11617y7(messageObj);
                    if (stickerObjM11617y7 != null) {
                    }
                    list.add(this.f9957q2);
                }
                break;
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 24:
            case 26:
            case 28:
            case 29:
            case 30:
            case 31:
            case 33:
                list.add(this.f9897b2);
                list.add(this.f9925i2);
                if (33 == i9 || 12 == i9) {
                    list.add(this.f9929j2);
                }
                if (24 == i9 || 3 == i9 || 8 == i9 || 29 == i9) {
                    if (3 != i9 && 8 != i9) {
                        list.add(this.f9945n2);
                    } else if (C2925v.m14600i0(messageObj)) {
                        list.add(this.f9949o2);
                    } else {
                        z9 = false;
                        if (z9) {
                            list.add(this.f9929j2);
                            list.add(this.f9933k2);
                            if (!this.f9914g.m15751i()) {
                                list.add(this.f9937l2);
                            }
                        }
                    }
                    z9 = true;
                    if (z9) {
                    }
                }
                if ((5 == i9 || 26 == i9) && NumberUtils.toLong(messageObj.m14747I("mediaId"), -1L) != -1) {
                    if (5 != i9) {
                        list.add(this.f9945n2);
                    } else if (C2925v.m14600i0(messageObj)) {
                        list.add(this.f9949o2);
                    }
                }
                list.add(this.f9905d2);
                list.add(this.f9909e2);
                list.add(this.f9913f2);
                if (i9 == 9 || i9 == 30 || i9 == 10 || i9 == 31 || i9 == 12 || i9 == 33) {
                    list.remove(this.f9925i2);
                }
                if (i9 == 24 || i9 == 29 || i9 == 30 || 26 == i9 || 28 == i9 || 31 == i9 || 33 == i9) {
                    list.remove(this.f9909e2);
                    list.remove(this.f9913f2);
                }
                if (i9 == 3 || i9 == 8 || i9 == 9 || i9 == 5 || i9 == 7 || i9 == 10 || i9 == 12) {
                    list.remove(this.f9905d2);
                }
                list.add(this.f9957q2);
                if (!this.f9914g.m15751i()) {
                    list.add(this.f9961r2);
                }
                if (messageObj.m14752N()) {
                    list.remove(this.f9897b2);
                    list.remove(this.f9925i2);
                    list.remove(this.f9929j2);
                    list.remove(this.f9933k2);
                    list.remove(this.f9937l2);
                    list.remove(this.f9905d2);
                    list.remove(this.f9961r2);
                }
                if (messageObj.m14740B().equals("0") || messageObj.m14740B().equals("6") || messageObj.m14740B().equals("5")) {
                    list.remove(this.f9909e2);
                } else if (messageObj.m14744F().equals("4") || messageObj.m14740B().equals("3")) {
                    list.remove(this.f9913f2);
                }
                if (24 == i9 || 3 == i9 || 33 == i9 || 33 == i9) {
                    list.add(this.f9965s2);
                }
                z8 = true;
                break;
            case 6:
            case 27:
                list.add(this.f9905d2);
                list.add(this.f9957q2);
                z8 = true;
                break;
            case 13:
            case 37:
                list.add(this.f9897b2);
                if (37 == i9) {
                    list.add(this.f9945n2);
                }
                if (13 == i9) {
                    list.add(this.f9949o2);
                }
                list.add(this.f9929j2);
                list.add(this.f9905d2);
                list.add(this.f9913f2);
                if (37 == i9) {
                    list.remove(this.f9913f2);
                }
                if (13 == i9) {
                    list.remove(this.f9905d2);
                }
                list.add(this.f9957q2);
                if (!this.f9914g.m15751i()) {
                    list.add(this.f9961r2);
                }
                list.add(this.f9965s2);
                z8 = true;
                break;
            case 14:
            case 38:
                list.add(this.f9897b2);
                list.add(this.f9905d2);
                list.add(this.f9913f2);
                if (38 == i9) {
                    list.remove(this.f9913f2);
                }
                if (14 == i9) {
                    list.remove(this.f9905d2);
                }
                list.add(this.f9957q2);
                if (!this.f9914g.m15751i()) {
                    list.add(this.f9961r2);
                }
                z8 = true;
                break;
            case 15:
            case 40:
                String strM11591q7 = m11591q7(messageObj);
                if (!"meeting".equals(strM11591q7) && !TtmlNode.END.equals(strM11591q7)) {
                    list.add(this.f9905d2);
                }
                list.add(this.f9957q2);
                z8 = true;
                break;
            case 16:
            case 17:
            case 19:
            case 41:
            case 42:
            case 44:
            case 48:
            case 49:
            case 51:
            case 52:
                list.add(this.f9905d2);
                if (i9 != 48) {
                    list.add(this.f9957q2);
                }
                z8 = true;
                break;
            case 34:
            case 36:
            case 39:
            case 45:
            case 47:
            case 50:
            default:
                z8 = true;
                break;
            case 35:
                list.add(this.f9905d2);
                list.add(this.f9897b2);
                list.add(this.f9957q2);
                z8 = true;
                break;
        }
        Group group = this.f9914g;
        if (group != null && group.f13711J) {
            if (21 == i9 || i9 == 0 || 18 == i9 || 43 == i9 || 46 == i9 || 20 == i9) {
                z8 = false;
            }
            if (list.contains(this.f9897b2) && z8) {
                list.remove(this.f9897b2);
            }
            list.remove(this.f9925i2);
            list.remove(this.f9933k2);
            list.remove(this.f9937l2);
        }
        Group group2 = this.f9914g;
        if (group2 == null || !group2.f13712K) {
            return;
        }
        list.remove(this.f9897b2);
        list.remove(this.f9917g2);
        list.remove(this.f9921h2);
        list.remove(this.f9925i2);
        list.remove(this.f9929j2);
        list.remove(this.f9933k2);
        list.remove(this.f9937l2);
        list.remove(this.f9941m2);
        list.remove(this.f9945n2);
        list.remove(this.f9949o2);
        list.remove(this.f9953p2);
        list.remove(this.f9961r2);
    }

    /* renamed from: Tb */
    public final void m11507Tb(String str, List<ImageItem> list) {
        ImageItem imageItem;
        ImageItem imageItem2;
        JSONException e9;
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return;
        }
        if (this.f9959r0 == null) {
            C3199c c3199c = new C3199c();
            this.f9959r0 = c3199c;
            c3199c.m17033C(this.f9821G2);
        }
        for (ImageItem imageItem3 : list) {
            if (imageItem3 == null || (TextUtils.isEmpty(imageItem3.m16139l()) && TextUtils.isEmpty(imageItem3.m16140m()))) {
                imageItem = imageItem3;
            } else {
                try {
                    imageItem2 = new ImageItem(new JSONObject(imageItem3.m16127H()));
                    try {
                        imageItem2.m16122C(CLUtility.m16540h0());
                        imageItem2.m16124E(imageItem3.m16139l());
                        imageItem2.m16125F(imageItem3.m16140m());
                    } catch (JSONException e10) {
                        e9 = e10;
                        Log.d("ChatDialogActivity", Log.getStackTraceString(e9));
                        imageItem = imageItem2;
                        if (imageItem == null) {
                        }
                        if (imageItem != null) {
                        }
                        this.f9959r0.m17039q(str, imageItem, null, null, null, "0");
                    }
                } catch (JSONException e11) {
                    imageItem2 = imageItem3;
                    e9 = e11;
                }
                imageItem = imageItem2;
            }
            if (imageItem == null && imageItem.m16134g() != null && !imageItem.m16134g().isEmpty()) {
                this.f9959r0.m17039q(str, imageItem, null, null, imageItem.m16134g(), "0");
            } else if (imageItem != null || imageItem.m16129b() == null || imageItem.m16129b().isEmpty()) {
                this.f9959r0.m17039q(str, imageItem, null, null, null, "0");
            } else {
                this.f9959r0.m17039q(str, imageItem, CLUtility.m16497V0(m11582n7()).f13787l, imageItem.m16129b(), null, imageItem.m16128a());
            }
        }
        DialogC6459g dialogC6459g = this.f9963s0;
        if (dialogC6459g == null || !dialogC6459g.isShowing()) {
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.w1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22468b.m11173P9();
                }
            });
        }
        this.f9959r0.m17044v();
        this.f9959r0.m17035F();
    }

    /* renamed from: U6 */
    public final void m11508U6(String str) {
        if (C2950b0.m14911j().m15056i(str) == null) {
            m11584nb(str);
        } else {
            m11522X9();
        }
    }

    /* renamed from: U7 */
    public final void m11509U7(String str) {
        try {
            final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
            if (topicObjM14984n != null) {
                runOnUiThread(new Runnable() { // from class: y2.w0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22466b.m11202T8(topicObjM14984n);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.e("ChatDialogActivity", "", e9);
        }
    }

    /* renamed from: U9 */
    public final void m11510U9(C2904l c2904l) {
        DeliveryReceipt deliveryReceipt = (DeliveryReceipt) c2904l.m14426l("received", "urn:xmpp:receipts");
        if (deliveryReceipt != null) {
            final String strM22697d = deliveryReceipt.m22697d();
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.v1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22451b.m11369q9(strM22697d);
                }
            });
        }
    }

    /* renamed from: Ua */
    public final void m11511Ua(Tab tab, boolean z8) {
        if (z8) {
            Tab tab2 = this.f9795A0;
            Tab tab3 = Tab.Chats;
            if (tab2.equals(tab3) && !tab.equals(tab3)) {
                m11457Fb(this.f9914g.f13727n);
            }
        }
        if (this.f9810E.getCount() == 0 && tab.equals(Tab.Chats) && !this.f9914g.f13711J) {
            this.f9854P.setVisibility(0);
        } else {
            this.f9854P.setVisibility(8);
        }
        m11515Va(tab, z8, false, true);
    }

    /* renamed from: V6 */
    public final void m11512V6() {
        final String strValueOf = String.valueOf(this.f9914g.f13727n);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        final String str = "Group:new";
        arrayList.add(new C6301o("albumId", "Group:new"));
        arrayList.add(new C6301o("groupId", strValueOf));
        this.f9899c0.m15734m("media", "queryUsage", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.l0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f22344a.m11414w8(strValueOf, str, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: V7 */
    public final void m11513V7(String str) {
        try {
            final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
            if (topicObjM14984n != null && topicObjM14984n.m14857w() && topicObjM14984n.m14843h() == this.f9914g.f13727n) {
                runOnUiThread(new Runnable() { // from class: y2.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22371b.m11209U8(topicObjM14984n);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.e("ChatDialogActivity", "", e9);
        }
    }

    /* renamed from: V9 */
    public final void m11514V9(C2904l c2904l) {
        if (((DeliveryReceiptRequest) c2904l.m14426l("request", "urn:xmpp:receipts")) != null) {
            C6196d0.m23692d().m23702l(this.f9914g.f13716c.equals("Dual") ? C5616j.m22345j(c2904l.m14428m()) : c2904l.m14428m(), c2904l.m14446v());
        }
    }

    /* renamed from: Va */
    public final void m11515Va(Tab tab, boolean z8, boolean z9, boolean z10) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Log.d("ChatDialogActivity", "[setTabSelection] start");
        if (this.f9799B0.m25247c()) {
            return;
        }
        if (z9 || this.f9795A0 != tab) {
            if (z8) {
                Tab tab2 = this.f9795A0;
                Tab tab3 = Tab.Bulletins;
                if (tab2.equals(tab3) && !tab.equals(tab3)) {
                    C2950b0.m14906e().m14988r(this.f9914g.f13727n);
                    C5126e c5126e = this.f9969t2;
                    if (c5126e != null) {
                        c5126e.m19993f();
                        C2907m0.m14454I().m14516z(this.f9914g.f13727n);
                    }
                }
            }
            m11519Wa(tab);
            this.f9799B0.m25245a();
            m11503T6(tab);
            m11586oa(tab);
            m11532a7();
            this.f9794A.setEnabled(false);
            if (tab.equals(Tab.Chats)) {
                this.f9799B0.m25248d();
                m11543cb(true);
                this.f9850O.setVisibility(8);
                this.f9864R1.setVisibility(4);
                this.f9894a2.setVisibility(4);
                this.f9966t.setVisibility(8);
                this.f9962s.setVisibility(4);
                this.f9946o.setVisibility(0);
                this.f9946o.setAdapter((ListAdapter) this.f9810E);
                this.f9946o.setOnItemClickListener(this.f9956q1);
                int i9 = this.f9935l0;
                if (i9 >= 0) {
                    this.f9946o.setSelection(i9);
                }
                if (z10) {
                    m11612wa();
                    m11442Ab();
                }
                this.f9794A.setEnabled(!this.f9914g.f13711J);
                this.f9981w2.m12067S();
            } else if (tab.equals(Tab.Albums)) {
                this.f9850O.setVisibility(8);
                this.f9966t.setVisibility(8);
                this.f9962s.setVisibility(0);
                this.f9946o.setVisibility(0);
                this.f9946o.setAdapter((ListAdapter) this.f9868S1);
                this.f9946o.setOnItemClickListener(this.f9876U1);
                this.f9894a2.setVisibility(4);
                m11466Ib();
                m11543cb(false);
                if (this.f9914g.f13716c.equals("Circle") && !this.f9914g.f13712K && this.f9827I0) {
                    this.f9827I0 = false;
                    m11585o7();
                }
            } else if (tab.equals(Tab.Bulletins)) {
                Log.d("ChatDialogActivity", "[setTabSelection] getTopicFromServerAsync start");
                m11620z7();
                Log.d("ChatDialogActivity", "[setTabSelection] getTopicFromServerAsync end " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                this.f9850O.setVisibility(8);
                this.f9966t.setVisibility(0);
                this.f9962s.setVisibility(4);
                this.f9946o.setVisibility(0);
                this.f9946o.setAdapter((ListAdapter) this.f9969t2);
                Log.d("ChatDialogActivity", "[setTabSelection] set mTopicAdapter done");
                this.f9946o.setOnItemClickListener(this.f9977v2);
                this.f9864R1.setVisibility(4);
                m11543cb(false);
            } else {
                this.f9850O.setVisibility(0);
                this.f9966t.setVisibility(8);
                this.f9962s.setVisibility(8);
                this.f9946o.setVisibility(8);
                this.f9894a2.setVisibility(4);
                this.f9864R1.setVisibility(4);
                m11543cb(false);
                C2907m0.m14454I().m14478B(this.f9914g.f13727n);
                m11563hb();
            }
            this.f9859Q0 = true;
            m11559gb();
            m11622zb();
            m11493Qb();
            m11445Bb();
            Log.d("ChatDialogActivity", "[setTabSelection] end " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        }
    }

    /* renamed from: W6 */
    public final void m11516W6() {
        if (this.f9914g != null) {
            C6385v.m24526d(new Runnable() { // from class: y2.n1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22373b.m11422x8();
                }
            });
        }
    }

    /* renamed from: W7 */
    public final void m11517W7(String str) {
        try {
            final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.parseLong(str));
            if (topicObjM14984n != null) {
                runOnUiThread(new Runnable() { // from class: y2.r
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f22408b.m11216V8(topicObjM14984n);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.e("ChatDialogActivity", "", e9);
        }
    }

    /* renamed from: W9 */
    public final void m11518W9(GroupAlbumObj groupAlbumObj) {
        Intent intent = new Intent(m11582n7(), (Class<?>) PhotoListActivity.class);
        intent.putExtra("album", groupAlbumObj);
        intent.putExtra("ShowShareToMyAlbum", true);
        intent.putExtra("Group", this.f9914g);
        if ("Chat".equals(groupAlbumObj.m14677d())) {
            startActivityForResult(intent, 3);
        } else {
            intent.putStringArrayListExtra("album_name_list", m11588p7());
            startActivityForResult(intent, 15);
        }
    }

    /* renamed from: Wa */
    public final void m11519Wa(Tab tab) {
        this.f9795A0 = tab;
        m11492Qa(tab);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* renamed from: X6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m11520X6(C2973l0 c2973l0, long j9, boolean z8, boolean z9, MessageObj.NoteType noteType) {
        long j10;
        String str;
        String strM15137i = c2973l0.m15137i();
        boolean z10 = true;
        if ((strM15137i == null || strM15137i.isEmpty() || strM15137i.equals("null")) && C2950b0.m14915n().m15104c(String.valueOf(c2973l0.m15144p())) == null) {
            z10 = false;
        }
        if (!z9) {
            j10 = -1;
            str = "None";
        } else if (noteType.equals(MessageObj.NoteType.Text)) {
            j10 = Long.MIN_VALUE;
            str = "CommentText";
        } else if (noteType.equals(MessageObj.NoteType.Audio)) {
            j10 = C3322C.TIME_UNSET;
            str = "CommentMedia";
        }
        long j11 = j10;
        String str2 = str;
        if (z10) {
            m11530Z9(c2973l0.m15131c(), c2973l0.m15144p(), j9, z8, j11, str2, true);
        } else {
            m11587ob(c2973l0.m15131c(), c2973l0.m15144p(), j9, z8, j11, str2, true);
        }
    }

    /* renamed from: X7 */
    public final void m11521X7(String str) {
        if (str == null) {
            return;
        }
        MessageObj messageObjM11608v7 = m11608v7(C2925v.m14584a0(str));
        if (messageObjM11608v7 != null) {
            messageObjM11608v7.m14762X("3");
        }
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.x0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22483b.m11223W8();
            }
        });
    }

    /* renamed from: X9 */
    public final void m11522X9() {
        String string = Long.toString(this.f9914g.f13727n);
        Group group = this.f9914g;
        m11518W9(new GroupAlbumObj(string, group.f13718e, group.f13717d, "Chat"));
    }

    /* renamed from: Xa */
    public final void m11523Xa(String str, final ImageView imageView) throws NumberFormatException {
        if (C5170o0.m20170e(str)) {
            return;
        }
        C6127a.m23469j(this, imageView, this.f9955q0.get(str));
        Friend friendM11440A7 = m11440A7(str);
        if (friendM11440A7 != null) {
            C6127a.m23469j(this, imageView, friendM11440A7);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        this.f9899c0.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.i
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f22303a.m11407v9(imageView, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: Y6 */
    public final void m11524Y6(ChatDialogMode chatDialogMode, int i9) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C1981l1(chatDialogMode, i9, permission), this);
    }

    /* renamed from: Y7 */
    public final void m11525Y7(String str) throws NumberFormatException {
        Friend friendM15001A;
        if (str != null && this.f9914g.f13716c.equals("Dual")) {
            long j9 = Long.parseLong(str);
            if (!this.f9927j0.containsKey(Long.valueOf(j9)) || (friendM15001A = C2950b0.m14899A().m15001A(str)) == null) {
                return;
            }
            this.f9927j0.put(Long.valueOf(j9), friendM15001A);
            m11619yb(this.f9980w1);
        }
    }

    /* renamed from: Y9 */
    public final void m11526Y9(String str, long j9, long j10, boolean z8) {
        m11530Z9(str, j9, j10, z8, -1L, "None", false);
    }

    /* renamed from: Ya */
    public final void m11527Ya(View view, boolean z8) {
        view.setEnabled(z8);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
                m11527Ya(viewGroup.getChildAt(i9), z8);
            }
        }
    }

    /* renamed from: Z6 */
    public final void m11528Z6() {
        List<MessageObj> listM15181t = C2950b0.m14916o().m15181t(String.valueOf(this.f9914g.f13727n), String.valueOf(Calendar.getInstance().getTime()), true);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MessageObj messageObj : listM15181t) {
            String strM14740B = messageObj.m14740B();
            if ("2".equals(strM14740B) || "3".equals(strM14740B)) {
                arrayList2.add(messageObj);
            } else {
                arrayList.add(messageObj);
            }
        }
        Comparator<MessageObj> comparator = MessageObj.f12923K;
        Collections.sort(arrayList2, comparator);
        Collections.sort(arrayList, comparator);
        this.f9810E.m11804f0();
        this.f9810E.m11766R(arrayList);
        this.f9810E.m11766R(this.f9815F0);
        this.f9810E.m11784X(arrayList2);
        if (this.f9795A0 == Tab.Chats) {
            this.f9867S0.f10224p = true;
        }
        m11573k8();
    }

    /* renamed from: Z7 */
    public final void m11529Z7(String str) throws NumberFormatException {
        Friend friendM15003C;
        boolean z8;
        Group groupM15081r;
        String strM22346k = C5616j.m22346k(str);
        if (strM22346k == null || (friendM15003C = C2950b0.m14899A().m15003C(strM22346k)) == null) {
            return;
        }
        long j9 = Long.parseLong(strM22346k);
        boolean z9 = true;
        if (this.f9927j0.containsKey(Long.valueOf(j9))) {
            this.f9927j0.put(Long.valueOf(j9), friendM15003C);
            this.f9955q0.put(strM22346k, friendM15003C);
            z8 = true;
        } else {
            z8 = false;
        }
        if (this.f9914g.f13716c.equals("Dual") && (groupM15081r = C2950b0.m14912k().m15081r(friendM15003C.f13648f)) != null && this.f9914g.f13727n == groupM15081r.f13727n) {
            this.f9914g = groupM15081r;
        } else {
            z9 = z8;
        }
        if (z9) {
            m11582n7().runOnUiThread(new Runnable() { // from class: y2.d1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22212b.m11230X8();
                }
            });
        }
    }

    /* renamed from: Z9 */
    public final void m11530Z9(String str, long j9, long j10, boolean z8, long j11, String str2, boolean z9) {
        if (C5179r0.m20246a()) {
            return;
        }
        if (!str.equals(this.f9914g.f13718e) && !str.equals(this.f9914g.f13720g) && C2950b0.m14911j().m15056i(str) == null) {
            C5187v0.m20267c(R.string.click_deleted_album);
            return;
        }
        DialogC3133q dialogC3133qM16410a = new DialogC3133q.b(m11582n7()).m16413d(1500L).m16410a();
        dialogC3133qM16410a.show();
        new AsyncTaskC1969i1(j10, str, j9, dialogC3133qM16410a, z9, z8, str2, j11).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: Za */
    public final void m11531Za() {
        C5126e c5126e = this.f9969t2;
        if (c5126e != null) {
            if (c5126e.getCount() > 0) {
                this.f9894a2.setVisibility(4);
            } else {
                this.f9894a2.setVisibility(0);
            }
        }
    }

    /* renamed from: a7 */
    public final void m11532a7() {
        CLUtility.m16589t1(this);
        this.f9983x0 = ChatDialogMode.NORMAL;
        this.f9946o.clearChoices();
        this.f9946o.setChoiceMode(0);
        this.f9822H.setVisibility(8);
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            c1954e2.notifyDataSetChanged();
        }
    }

    /* renamed from: a8 */
    public final void m11533a8() {
        this.f9889Z = new C2026a(Globals.m7388i0().m7534d2(), new C1939b());
    }

    /* renamed from: aa */
    public final void m11534aa(StickerPackObj stickerPackObj) {
        ArrayList<String> arrayListM7478R0 = Globals.m7388i0().m7478R0();
        f9789U2 = arrayListM7478R0;
        boolean z8 = false;
        if (arrayListM7478R0 != null) {
            boolean z9 = false;
            for (int i9 = 0; i9 < f9789U2.size(); i9++) {
                if (f9789U2.get(i9).equals(String.valueOf(stickerPackObj.m14803g()))) {
                    z9 = true;
                }
            }
            z8 = z9;
        }
        Intent intent = new Intent(m11582n7(), (Class<?>) StickerShopDetailActivity.class);
        intent.putExtra("stickerPckObj", stickerPackObj);
        intent.putExtra("isPurchased", z8);
        m11582n7().startActivityForResult(intent, 5051);
    }

    /* renamed from: ab */
    public final void m11535ab(int i9, boolean z8) {
        this.f9855P0.setVisibility(i9);
        if (this.f9851O0.size() > 0) {
            this.f9855P0.setImageResource(z8 ? R.drawable.btn_backtoquote_p : R.drawable.btn_backtoquote_n);
        } else {
            this.f9855P0.setImageResource(z8 ? R.drawable.btn_bottom_p : R.drawable.btn_bottom_n);
        }
    }

    /* renamed from: b7 */
    public final void m11536b7(boolean z8) {
        C2027b c2027b = this.f9981w2;
        Boolean bool = Boolean.FALSE;
        boolean zM12075a0 = c2027b.m12075a0(bool, bool, bool);
        m11559gb();
        if (zM12075a0 || !z8) {
            CLUtility.m16589t1(this);
        } else {
            m11576l7();
        }
    }

    /* renamed from: b8 */
    public final void m11537b8() {
        Group groupM15077n;
        if (this.f9914g == null || (groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(this.f9914g.f13727n))) == null) {
            return;
        }
        this.f9914g.f13737x = groupM15077n.f13737x;
    }

    /* renamed from: ba */
    public final void m11538ba(Friend friend) {
        if (friend == null || friend.f13664v) {
            return;
        }
        if (friend.f13645c == Globals.m7388i0().m7568k1().longValue()) {
            startActivity(new Intent(this, (Class<?>) UserProfileActivity.class));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("friendObj", friend);
        bundle.putString("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
        Intent intent = new Intent();
        intent.setClass(this, FriendProfileActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 5008);
    }

    /* renamed from: bb */
    public final void m11539bb(C2973l0 c2973l0) {
        ULogUtility.m16680p("ChatDialogActivity", "share photo to other apps");
        MediaLoader.m7151j(this, c2973l0, false, new C1963h(m7366I0(), c2973l0));
    }

    /* renamed from: c7 */
    public final void m11540c7() {
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.y1
            @Override // java.lang.Runnable
            public final void run() {
                this.f22508b.m11430y8();
            }
        });
    }

    /* renamed from: c8 */
    public final void m11541c8() {
        try {
            String stringExtra = getIntent().getStringExtra("defaultText");
            if (stringExtra != null) {
                this.f9981w2.m12072X(stringExtra);
            } else {
                this.f9981w2.m12072X(this.f9914g.f13737x);
            }
            C2027b c2027b = this.f9981w2;
            c2027b.m12071W(c2027b.m12084z().length());
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: ca */
    public final void m11542ca() {
        Friend next;
        Iterator<Friend> it = this.f9927j0.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                    break;
                }
            }
        }
        if (next != null) {
            Intent intent = new Intent(m11582n7(), (Class<?>) FriendProfileActivity.class);
            intent.putExtra("friendObj", next);
            intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
            startActivityForResult(intent, 5008);
        }
    }

    /* renamed from: cb */
    public final void m11543cb(boolean z8) {
        if (this.f9981w2 == null || getSupportFragmentManager() == null) {
            return;
        }
        try {
            if (z8) {
                getSupportFragmentManager().mo1844a().mo1802r(this.f9981w2).mo1794h();
            } else {
                getSupportFragmentManager().mo1844a().mo1799n(this.f9981w2).mo1794h();
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: d7 */
    public final ChatOptionDialog m11544d7(View view) {
        ChatOptionDialog.C2024a c2024a = new ChatOptionDialog.C2024a();
        if (this.f9914g.f13738y.equals("General")) {
            if (this.f9914g.f13716c.equals("Dual")) {
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Invite, this.f9808D1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.TurnOffNotification, this.f9820G1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.EditName, this.f9856P1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Profile, this.f9800B1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.AddToHome, this.f9848N1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Block, this.f9852O1);
                if (!this.f9914g.f13711J) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Photo, this.f9824H1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Album, this.f9828I1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Video, this.f9804C1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.File, this.f9832J1);
                }
            } else if (this.f9914g.f13716c.equals("Circle")) {
                if (!this.f9914g.m15751i()) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Invite, this.f9808D1);
                }
                if (!this.f9914g.m15751i() && (!"Community".equals(this.f9914g.f13705D) || this.f9914g.f13704C)) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.InviteByLink, this.f9812E1);
                }
                if (!this.f9914g.m15751i()) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.TurnOffNotification, this.f9820G1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.GroupProfile, this.f9796A1);
                }
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.AddToHome, this.f9848N1);
                if (!this.f9914g.m15751i()) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Leave, this.f9984x1);
                }
                if (!this.f9914g.m15751i() && (!"Community".equals(this.f9914g.f13705D) || this.f9914g.f13704C)) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.EditGroup, this.f9988y1);
                }
                if (!this.f9914g.f13711J) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Photo, this.f9824H1);
                    if (!this.f9914g.m15751i()) {
                        c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Album, this.f9828I1);
                    }
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Video, this.f9804C1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.File, this.f9832J1);
                    if (!this.f9914g.m15751i() && !this.f9914g.f13712K) {
                        c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Forum, this.f9840L1);
                        c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Poll, this.f9844M1);
                    }
                }
            } else if (this.f9914g.f13716c.equals("ChatRoom")) {
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Invite, this.f9808D1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.TurnOffNotification, this.f9820G1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.EditName, this.f9928j1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.CreateGroup, this.f9928j1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.AddToHome, this.f9848N1);
                c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Leave, this.f9984x1);
                if (!this.f9914g.f13711J) {
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Photo, this.f9824H1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Album, this.f9828I1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Video, this.f9804C1);
                    c2024a.m11995a(ChatOptionDialog.ChatOptionButton.File, this.f9832J1);
                }
            }
        } else if (this.f9914g.f13738y.equals("Official")) {
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.TurnOffNotification, this.f9820G1);
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Photo, this.f9824H1);
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Album, this.f9828I1);
        } else {
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.TurnOffNotification, this.f9820G1);
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Block, this.f9852O1);
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Photo, this.f9824H1);
            c2024a.m11995a(ChatOptionDialog.ChatOptionButton.Album, this.f9828I1);
        }
        ChatOptionDialog chatOptionDialogM11996b = c2024a.m11996b(this, view);
        if (!this.f9914g.m15751i()) {
            m11610vb(chatOptionDialogM11996b);
        }
        if (this.f9914g.f13716c.equals("Dual") || (!this.f9914g.f13738y.equals("General") && !this.f9914g.f13738y.equals("Official"))) {
            m11619yb(chatOptionDialogM11996b);
        }
        if (Group.m15743f(this.f9914g.f13716c)) {
            Group group = this.f9914g;
            if (group.f13709H) {
                if (!group.f13704C) {
                    ChatOptionDialog.ChatOptionButton chatOptionButton = ChatOptionDialog.ChatOptionButton.Invite;
                    chatOptionDialogM11996b.m11991b(chatOptionButton).f10268c.setEnabled(false);
                    chatOptionDialogM11996b.m11991b(chatOptionButton).f10267b.setColorFilter(-7829368);
                    chatOptionDialogM11996b.m11991b(chatOptionButton).f10266a.setEnabled(false);
                }
                ChatOptionDialog.ChatOptionButton chatOptionButton2 = ChatOptionDialog.ChatOptionButton.Leave;
                chatOptionDialogM11996b.m11991b(chatOptionButton2).f10268c.setEnabled(false);
                chatOptionDialogM11996b.m11991b(chatOptionButton2).f10267b.setColorFilter(-7829368);
                chatOptionDialogM11996b.m11991b(chatOptionButton2).f10266a.setEnabled(false);
            }
        }
        return chatOptionDialogM11996b;
    }

    /* renamed from: d8 */
    public final void m11545d8() {
        this.f9958r = new C6766w5((TextView) findViewById(R.id.newMessageText), new Runnable() { // from class: y2.d0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22211b.m11237Y8();
            }
        });
    }

    /* renamed from: da */
    public final void m11546da() {
        Intent intent = new Intent(m11582n7(), (Class<?>) GroupInfoActivity.class);
        intent.putExtra("Group", this.f9914g);
        startActivity(intent);
    }

    /* renamed from: db */
    public final void m11547db() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.this_poll_was_removed_or_not_existed));
        builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: y2.t1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ChatDialogActivity.m11415w9(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* renamed from: e7 */
    public final void m11548e7(String str, final ArrayList<ImageItem> arrayList) {
        String str2 = Long.toString(this.f9914g.f13727n).equals("backup") ? "backup" : "group";
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new C6301o("token", strM7449L));
        arrayList2.add(new C6301o("groupId", Long.toString(this.f9914g.f13727n)));
        arrayList2.add(new C6301o("albumName", str));
        m11551eb();
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: y2.y
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str3, String str4, String str5, String str6) {
                this.f22505a.m11055B8(arrayList, str3, str4, str5, str6);
            }
        };
        this.f9813E2 = interfaceC3051i;
        this.f9899c0.m15734m(str2, "createAlbum", arrayList2, interfaceC3051i);
    }

    /* renamed from: e8 */
    public final void m11549e8() {
        JSONArray jSONArrayM20196r;
        SharedPreferences sharedPreferences = getSharedPreferences("cached_schedule_send_map", 0);
        this.f9919h0.clear();
        String string = sharedPreferences.getString(this.f9910f, null);
        this.f9923i0 = sharedPreferences.getBoolean(this.f9910f + "_is_schedule_send_list_update", false);
        if (string == null || string.equals("") || (jSONArrayM20196r = C5172p.m20196r(string)) == null) {
            return;
        }
        this.f9919h0.addAll(m11562ha(jSONArrayM20196r));
    }

    /* renamed from: ea */
    public final void m11550ea() {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.f9927j0.size());
        arrayList.addAll(this.f9927j0.values());
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f9914g);
        bundle.putInt("type", 0);
        bundle.putParcelableArrayList("originalMembers", arrayList);
        Intent intent = new Intent();
        intent.setClass(this, GroupMemberListActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 5011);
    }

    /* renamed from: eb */
    public final void m11551eb() {
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.l1
            @Override // java.lang.Runnable
            public final void run() {
                this.f22347b.m11423x9();
            }
        });
    }

    /* renamed from: f7 */
    public final void m11552f7(List<Friend> list) {
        this.f9898c = ProgressDialog.show(m11582n7(), "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        for (Friend friend : this.f9927j0.values()) {
            if (friend.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                arrayList.add(new C6301o("userId", Long.toString(friend.f13645c)));
            }
        }
        Iterator<Friend> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C6301o("userId", Long.toString(it.next().f13645c)));
        }
        arrayList.add(new C6301o("groupType", "ChatRoom"));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: y2.y0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f22507a.m11063C8(str, str2, str3, str4);
            }
        };
        this.f9924i1 = interfaceC3051i;
        this.f9899c0.m15734m("group", "create", arrayList, interfaceC3051i);
    }

    /* renamed from: f8 */
    public final boolean m11553f8(Group group) {
        Group groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(group.f13727n));
        List<Long> listM15098i = C2950b0.m14913l().m15098i(Long.valueOf(group.f13727n));
        return (listM15098i == null || groupM15077n == null || groupM15077n.f13728o != ((long) listM15098i.size())) ? false : true;
    }

    /* renamed from: fa */
    public final void m11554fa(String str, String str2, String str3, Uri uri) {
        ULogUtility.m16670f("ChatDialogActivity", "[openSharePanel] mimeType: " + str + " / uri: " + uri);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if (str != null) {
            intent.setType(str);
        }
        if (str2 != null) {
            intent.putExtra("android.intent.extra.TITLE", str2);
        }
        if (str3 != null) {
            intent.putExtra("android.intent.extra.TEXT", str3);
        }
        if (uri != null) {
            intent.putExtra("android.intent.extra.STREAM", uri);
        }
        startActivity(Intent.createChooser(intent, null));
    }

    /* renamed from: fb */
    public final void m11555fb() throws NumberFormatException {
        String strM14779q;
        if (this.f9915g0.m14752N()) {
            return;
        }
        this.f9975v0 = this.f9915g0;
        this.f9974v.setVisibility(0);
        if (this.f9975v0.m14778p().equals(MessageObj.MessageType.ReplyText)) {
            strM14779q = this.f9975v0.m14747I("replyText");
            Log.d("ChatDialogActivity", "onDoubleTapEvent in content = " + strM14779q);
        } else {
            strM14779q = this.f9975v0.m14779q();
        }
        this.f9978w.setText(strM14779q);
        m11523Xa(this.f9975v0.m14745G(), this.f9986y);
    }

    /* renamed from: g7 */
    public final ScheduleSendObj m11556g7(MessageObj messageObj) {
        ScheduleSendObj scheduleSendObj = new ScheduleSendObj();
        scheduleSendObj.f12447b = String.valueOf(messageObj.m14778p());
        scheduleSendObj.f12448c = messageObj.m14777o();
        scheduleSendObj.f12449d = 0;
        scheduleSendObj.f12450e = messageObj.m14788z();
        scheduleSendObj.f12451f = "NotYet";
        scheduleSendObj.f12452g = messageObj.m14779q();
        return scheduleSendObj;
    }

    /* renamed from: g8 */
    public final boolean m11557g8() {
        C1954e2 c1954e2 = this.f9810E;
        return (c1954e2 == null || this.f9985x2 == c1954e2.getCount()) ? false : true;
    }

    /* renamed from: ga */
    public final void m11558ga(TopicObj topicObj) {
        Intent intent = new Intent(this, (Class<?>) TopicContentActivity.class);
        intent.putExtra("Group", this.f9914g);
        intent.putExtra("intent_topic", topicObj);
        startActivityForResult(intent, 17);
    }

    /* renamed from: gb */
    public final void m11559gb() {
        ArrayList<ScheduleSendObj> arrayList = this.f9919h0;
        if (arrayList == null || arrayList.size() == 0 || this.f9806D.size() == 0 || Tab.Chats != this.f9795A0) {
            this.f9818G.setVisibility(8);
            return;
        }
        if (this.f9946o.getChoiceMode() == 2) {
            this.f9818G.setVisibility(8);
            return;
        }
        if (this.f9974v.getVisibility() == 0) {
            this.f9818G.setVisibility(8);
            return;
        }
        Iterator<ScheduleSendObj> it = this.f9919h0.iterator();
        while (it.hasNext()) {
            if (this.f9806D.containsKey(it.next().f12448c)) {
                this.f9818G.setVisibility(0);
                return;
            }
        }
        this.f9818G.setVisibility(8);
    }

    /* renamed from: h7 */
    public final void m11560h7(final List<String> list) {
        this.f9898c = ProgressDialog.show(m11582n7(), "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", Long.toString(Globals.m7388i0().m7568k1().longValue())));
        arrayList.add(new C6301o("groupType", "Circle"));
        arrayList.add(new C6301o("displayName", "(☑) ToDo"));
        arrayList.add(new C6301o("circleType", "Small"));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: y2.z1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f22521a.m11071D8(list, str, str2, str3, str4);
            }
        };
        this.f9924i1 = interfaceC3051i;
        this.f9899c0.m15734m("group", "create", arrayList, interfaceC3051i);
    }

    /* renamed from: h8 */
    public final boolean m11561h8(AbsListView absListView) {
        int childCount = absListView.getChildCount();
        if (childCount == 0) {
            return false;
        }
        return absListView.getLastVisiblePosition() >= absListView.getCount() - 1 && absListView.getChildAt(childCount - 1).getBottom() <= absListView.getBottom() - (absListView.getListPaddingBottom() / 2);
    }

    /* renamed from: ha */
    public final List<ScheduleSendObj> m11562ha(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i9);
                ScheduleSendObj scheduleSendObj = new ScheduleSendObj();
                try {
                    scheduleSendObj.f12447b = jSONObject.getString(Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE);
                    scheduleSendObj.f12448c = jSONObject.getString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
                    scheduleSendObj.f12449d = jSONObject.getInt(Constants.FirelogAnalytics.PARAM_TTL);
                    scheduleSendObj.f12450e = jSONObject.has("sendTime") ? new Date(jSONObject.getLong("sendTime") * 1000) : new Date();
                    scheduleSendObj.f12451f = jSONObject.getString("sendStatus");
                    scheduleSendObj.f12452g = jSONObject.getString(FirebaseAnalytics.Param.CONTENT);
                    arrayList.add(scheduleSendObj);
                } catch (JSONException unused) {
                    Log.e("ChatDialogActivity", "[chat.listScheduleSend] attribute missing. JSONStr=" + jSONObject.toString());
                }
            } catch (JSONException unused2) {
                Log.e("ChatDialogActivity", "[chat.listScheduleSend] parse error. JSONStr=" + jSONArray.toString());
            }
        }
        return arrayList;
    }

    /* renamed from: hb */
    public final void m11563hb() {
        this.f9867S0.m25325i();
    }

    /* renamed from: i7 */
    public final void m11564i7() {
        String country = LocaleList.getDefault().get(0).getCountry();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.cyberlink.com/prog/ap/u/redirect.jsp?locale=" + (LocaleList.getDefault().get(0).getLanguage() + "_" + country) + "&type=faq_e2ee")));
    }

    /* renamed from: i8 */
    public final boolean m11565i8() {
        MessageObj item;
        if (this.f9946o.getCount() > 0 && this.f9946o.getLastVisiblePosition() > 0) {
            int lastVisiblePosition = (this.f9946o.getLastVisiblePosition() - this.f9946o.getHeaderViewsCount()) - this.f9946o.getFooterViewsCount();
            ULogUtility.m16670f("ChatDialogActivity", "[isReachBottomByLastMessage] / mChatDialogListView count = " + this.f9946o.getCount() + " / visible pos = " + this.f9946o.getLastVisiblePosition() + " / mMessageAdapter count = " + this.f9810E.getCount() + " / final pos = " + lastVisiblePosition);
            if (lastVisiblePosition < this.f9810E.getCount() && (item = this.f9810E.getItem(lastVisiblePosition)) != null && this.f9835K0 != null && item.m14777o().equals(this.f9835K0.m14777o())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: ia */
    public final String m11566ia(MessageObj messageObj, String str) throws NumberFormatException {
        MessageObj.MessageType messageTypeM14778p = messageObj.m14778p();
        if (messageObj.m14741C() == MessageObj.TTLStatus.NOT_START) {
            return getString(R.string.notification_default_string);
        }
        if (messageTypeM14778p == MessageObj.MessageType.Text) {
            return str + " : " + messageObj.m14779q();
        }
        if (messageTypeM14778p == MessageObj.MessageType.ReplyText) {
            try {
                return str + " : " + new JSONObject(messageObj.m14779q()).getString("replyText");
            } catch (Exception unused) {
                Log.d("ChatDialogActivity", "Parse reply content replyText exception");
            }
        } else {
            if (messageTypeM14778p == MessageObj.MessageType.PhotoNote) {
                return messageObj.m14780r().equals(MessageObj.NoteType.Text) ? getString(R.string.notification_chat_user_send_photo_text_note, str) : getString(R.string.notification_chat_user_send_photo_voice_note, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.Comment) {
                return getString(R.string.notification_chat_user_comment_photo, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.CommentUpdate) {
                return getString(R.string.notification_chat_user_edit_comment_photo, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.Sticker || messageTypeM14778p == MessageObj.MessageType.AnimPngSticker || messageTypeM14778p == MessageObj.MessageType.AnimSticker) {
                return getString(R.string.notification_chat_user_send_sticker, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.Photo) {
                return getString(R.string.notification_chat_user_send_photo, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.Audio) {
                return getString(R.string.notification_chat_user_receive_voice_msg, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.Video) {
                return getString(R.string.notification_chat_user_send_video, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.File) {
                return getString(R.string.notification_chat_user_send_file, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.ShareLocation) {
                return getString(R.string.notification_chat_user_send_location, str);
            }
            if (messageTypeM14778p == MessageObj.MessageType.CreateMedia) {
                try {
                    int i9 = Integer.parseInt(new JSONObject(messageObj.m14779q()).getString("numberUpload"));
                    if (i9 != 0) {
                        return String.format(getResources().getQuantityString(R.plurals.chatlist_user_share_photo, i9), str, Integer.valueOf(i9));
                    }
                } catch (JSONException e9) {
                    Log.e("ChatDialogActivity", Log.getStackTraceString(e9));
                }
            } else {
                if (messageTypeM14778p != MessageObj.MessageType.DeleteMedia) {
                    if (messageTypeM14778p == MessageObj.MessageType.UserContact) {
                        return getString(R.string.notification_chat_user_send_user_contact, str);
                    }
                    if (messageTypeM14778p == MessageObj.MessageType.AnnouncementType01 || messageTypeM14778p == MessageObj.MessageType.AnnouncementType02 || messageTypeM14778p == MessageObj.MessageType.AnnouncementType03) {
                        return messageObj.m14748J(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, FirebaseAnalytics.Param.CONTENT);
                    }
                    return null;
                }
                try {
                    int i10 = Integer.parseInt(new JSONObject(messageObj.m14779q()).getString("numberDelete"));
                    if (i10 != 0) {
                        return String.format(getResources().getQuantityString(R.plurals.chatlist_user_delete_photo, i10), str, Integer.valueOf(i10));
                    }
                } catch (JSONException e10) {
                    Log.e("ChatDialogActivity", Log.getStackTraceString(e10));
                }
            }
        }
        return "";
    }

    /* renamed from: ib */
    public final void m11567ib() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.topic_has_been_deleted));
        builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: y2.r1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ChatDialogActivity.m11431y9(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* renamed from: j7 */
    public final void m11568j7(MessageObj messageObj, String str) throws NumberFormatException {
        String strM11566ia = m11566ia(messageObj, str);
        if (strM11566ia != null) {
            this.f9958r.m25316h(strM11566ia, messageObj);
            float height = findViewById(R.id.newMessageText).getHeight();
            if (height == BitmapDescriptorFactory.HUE_RED) {
                height = 200.0f;
            }
            this.f9855P0.animate().translationY(height * (-1.0f)).setDuration(300L).withStartAction(new RunnableC1987n1()).start();
            if (!Globals.m7388i0().m7557i2() || Globals.m7396z1()) {
                return;
            }
            ((Vibrator) getSystemService("vibrator")).vibrate(500L);
        }
    }

    /* renamed from: j8 */
    public final void m11569j8(Map<String, Object> map) {
        Group group = this.f9914g;
        if ((group == null || !group.f13711J) && !map.isEmpty()) {
            if (map.get("album") != null) {
                m11518W9((GroupAlbumObj) map.get("album"));
                return;
            }
            if (map.get("media") == null) {
                if (map.get(Constants.FirelogAnalytics.PARAM_TOPIC) != null) {
                    m11558ga((TopicObj) map.get(Constants.FirelogAnalytics.PARAM_TOPIC));
                }
            } else {
                C2973l0 c2973l0 = (C2973l0) map.get("media");
                m11530Z9(c2973l0.m15131c(), c2973l0.m15144p(), 0L, true, ((Long) map.get("commentId")).longValue(), (String) map.get("commentType"), ((Boolean) map.get("isNoteMessage")).booleanValue());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006c A[ORIG_RETURN, RETURN] */
    @SuppressLint({"UseSparseArrays"})
    /* renamed from: ja */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m11570ja(Group group) {
        boolean z8;
        if (!m11553f8(group)) {
            Group groupM15650P = FriendsClient.m15650P(String.valueOf(group.f13727n));
            if (groupM15650P != null) {
                C2950b0.m14912k().m15069f(groupM15650P);
                if (m7367J0()) {
                    return;
                } else {
                    z8 = m11577la(groupM15650P) ? false : true;
                }
            }
            if (z8) {
                return;
            }
            List<Long> listM15098i = C2950b0.m14913l().m15098i(Long.valueOf(group.f13727n));
            for (int i9 = 0; listM15098i != null && listM15098i.size() > i9; i9++) {
                Friend friendM15003C = C2950b0.m14899A().m15003C(String.valueOf(listM15098i.get(i9)));
                if (friendM15003C != null) {
                    this.f9927j0.put(Long.valueOf(friendM15003C.f13645c), friendM15003C);
                }
            }
            return;
        }
        Log.d("ChatDialogActivity", "[queryAndSyncGroupMember] GroupInfo.numberOfMember and GroupMemberList count is same");
        if (z8) {
        }
    }

    /* renamed from: jb */
    public void m11571jb(final boolean z8) {
        if (Globals.m7388i0().m7534d2()) {
            runOnUiThread(new Runnable() { // from class: y2.i1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22306b.m11439z9(z8);
                }
            });
        }
    }

    /* renamed from: k7 */
    public void m11572k7(MessageObj messageObj, String str, String str2) throws IOException {
        Log.d("ChatDialogActivity", "download voice file and play!");
        String strM16488S0 = CLUtility.m16488S0(m11582n7(), str2);
        new C3197a().m16996s(str, strM16488S0, new C1997r(messageObj, strM16488S0));
    }

    /* renamed from: k8 */
    public final void m11573k8() {
        Intent intent = getIntent();
        if (intent != null) {
            this.f9810E.m11752L0(intent.getStringExtra("messageID"), intent.getStringExtra("SearchChat"));
        }
    }

    /* renamed from: ka */
    public final void m11574ka(Group group) {
        new AsyncTaskC1936a0(group).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: kb */
    public void m11575kb(Group group, List<String> list) {
        C6818b.m25398i(group, true, list, new C1967i(group));
    }

    /* renamed from: l7 */
    public final void m11576l7() {
        CLUtility.m16589t1(this);
        m11579m7(null);
    }

    /* renamed from: la */
    public final boolean m11577la(Group group) {
        List<Friend> listM15714S = this.f9899c0.m15714S(group.f13727n, true);
        if (listM15714S == null) {
            return false;
        }
        m11472Kb(listM15714S);
        return true;
    }

    /* renamed from: lb */
    public final void m11578lb(MessageObj messageObj, String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (this.f9939m0 == null) {
            C5140e0 c5140e0 = new C5140e0();
            this.f9939m0 = c5140e0;
            c5140e0.m20021m(new C2000s());
        }
        MessageObj messageObj2 = this.f9911f0;
        if (messageObj2 != null && messageObj2.m14753O()) {
            this.f9810E.m11786X1(this.f9911f0);
        }
        this.f9939m0.m20027s();
        this.f9911f0 = messageObj;
        this.f9810E.notifyDataSetChanged();
        this.f9939m0.m20018j(str);
        C2017x1 c2017x1 = new C2017x1();
        this.f9903d0 = c2017x1;
        Timer timer = this.f9907e0;
        if (timer != null) {
            timer.schedule(c2017x1, 0L, 1000L);
        }
    }

    /* renamed from: m7 */
    public final void m11579m7(Group group) {
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null && c1954e2.m11826u0() > 0) {
            C1954e2 c1954e22 = this.f9810E;
            MessageObj item = c1954e22.getItem(c1954e22.m11826u0() - 1);
            if (item != null && this.f9914g.m15748d() < item.m14788z().getTime()) {
                this.f9914g.m15753k(item.m14788z().getTime());
                ULogUtility.m16680p("ChatDialogActivity", "[finishActivity] update group lastRead, groupId = " + this.f9914g.f13727n + " lastRead = " + this.f9914g.m15748d());
                C2950b0.m14912k().m15062A(String.valueOf(this.f9914g.f13727n), this.f9914g, "LastRead");
            }
        }
        this.f9914g.f13737x = m11594r7();
        ChatOptionDialog chatOptionDialog = this.f9980w1;
        if (chatOptionDialog != null) {
            chatOptionDialog.dismiss();
        }
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("isFinishDirectly", false)) {
            finish();
            return;
        }
        if (m11582n7().getCallingActivity() != null) {
            Intent intent2 = new Intent();
            intent2.putExtra("Group", this.f9914g);
            intent2.putExtra(ProductAction.ACTION_REMOVE, this.f9874U);
            if (group != null) {
                intent2.putExtra("StartOtherGroup", group);
            }
            m11582n7().setResult(-1, intent2);
            finish();
            return;
        }
        if (group != null) {
            Intent intent3 = new Intent(m11582n7().getApplicationContext(), (Class<?>) ChatDialogActivity.class);
            intent3.putExtra("Group", group);
            startActivity(intent3);
            finish();
            return;
        }
        Intent intent4 = new Intent();
        intent4.setClass(this, ULauncherActivity.class);
        intent4.putExtra("Tab_Index", 0);
        intent4.putExtra("checkLastMsg", true);
        intent4.setFlags(268468224);
        startActivity(intent4);
        finish();
    }

    /* renamed from: ma */
    public final void m11580ma(boolean z8, boolean z9) {
        if (this.f9987y0 == null) {
            AsyncTaskC2012w asyncTaskC2012w = new AsyncTaskC2012w(z8, z9);
            this.f9987y0 = asyncTaskC2012w;
            asyncTaskC2012w.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: mb */
    public final void m11581mb() throws IllegalStateException {
        C5140e0 c5140e0 = this.f9939m0;
        if (c5140e0 == null) {
            return;
        }
        c5140e0.m20027s();
        C2017x1 c2017x1 = this.f9903d0;
        if (c2017x1 != null) {
            c2017x1.cancel();
            this.f9903d0 = null;
        }
        this.f9911f0 = null;
    }

    /* renamed from: n7 */
    public final Activity m11582n7() {
        return this;
    }

    /* renamed from: na */
    public void m11583na(MessageObj messageObj) {
        this.f9810E.remove(messageObj);
    }

    /* renamed from: nb */
    public final void m11584nb(String str) {
        ProgressDialog progressDialogShow = ProgressDialog.show(m11582n7(), "", getString(R.string.processing), true);
        if (this.f9841L2) {
            return;
        }
        this.f9841L2 = true;
        AsyncTaskC1996q1 asyncTaskC1996q1 = new AsyncTaskC1996q1(str, progressDialogShow);
        this.f9837K2 = asyncTaskC1996q1;
        asyncTaskC1996q1.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: o7 */
    public final void m11585o7() {
        if (m7367J0()) {
            return;
        }
        C1938a2 c1938a2 = this.f9872T1;
        if (c1938a2 != null) {
            c1938a2.m24447a();
        }
        C1938a2 c1938a22 = new C1938a2(String.valueOf(this.f9914g.f13727n));
        this.f9872T1 = c1938a22;
        C6385v.f21554b.submit(c1938a22);
    }

    /* renamed from: oa */
    public final void m11586oa(Tab tab) {
        if (tab != Tab.Poll) {
            return;
        }
        C6661h6 c6661h6 = new C6661h6();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", this.f9914g);
        c6661h6.setArguments(bundle);
        getSupportFragmentManager().mo1844a().m1986p(R.id.fragment_container, c6661h6, "PollsFragment").mo1794h();
    }

    /* renamed from: ob */
    public final void m11587ob(String str, long j9, long j10, boolean z8, long j11, String str2, boolean z9) {
        if (this.f9833J2) {
            return;
        }
        ProgressDialog progressDialogShow = ProgressDialog.show(m11582n7(), "", getString(R.string.processing), true);
        this.f9833J2 = true;
        AsyncTaskC1993p1 asyncTaskC1993p1 = new AsyncTaskC1993p1(j9, str, progressDialogShow, j10, z8, j11, str2, z9);
        this.f9829I2 = asyncTaskC1993p1;
        asyncTaskC1993p1.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) throws NumberFormatException {
        Bundle extras;
        Friend friend;
        GroupAlbumObj groupAlbumObjM15951j;
        TopicObj topicObj;
        C5126e c5126e;
        C5126e c5126e2;
        List<Friend> listM7656c;
        GroupAlbumObj groupAlbumObjM15056i;
        MessageObj messageObjM15179r;
        super.onActivityResult(i9, i10, intent);
        Log.d("ChatDialogActivity", "onActivityResult");
        C2027b c2027b = this.f9981w2;
        Boolean bool = Boolean.FALSE;
        c2027b.m12075a0(bool, bool, bool);
        m11559gb();
        if (i9 == 3) {
            ArrayList arrayList = (ArrayList) intent.getExtras().getSerializable("import_images");
            if (arrayList != null && arrayList.size() > 0) {
                HashMap map = new HashMap();
                map.put("importImages", arrayList);
                this.f9797A2.mo7011a("sendPhoto", map);
            }
            m11511Ua(Tab.Chats, false);
            return;
        }
        if (i9 == 4) {
            if (i10 == -1 && this.f9914g.f13716c.equals("Dual") && intent.getBooleanExtra("isBrokenUp", false)) {
                m11579m7(null);
                return;
            }
            return;
        }
        if (i9 == 5) {
            if (i10 == -1) {
                m11579m7(null);
                return;
            }
            return;
        }
        if (i9 == 2000) {
            this.f9981w2.onActivityResult(i9, i10, intent);
            return;
        }
        if (i9 == 5001) {
            if (i10 != -1 || intent == null || (extras = intent.getExtras()) == null) {
                return;
            }
            Group group = (Group) extras.getParcelable("Group");
            if (group != null) {
                this.f9914g = group;
                m11502Sb();
            }
            long[] longArray = extras.getLongArray("deleteMembers");
            if (longArray != null) {
                for (long j9 : longArray) {
                    this.f9927j0.remove(Long.valueOf(j9));
                }
            }
            ArrayList<Friend> parcelableArrayList = extras.getParcelableArrayList("addMembers");
            if (parcelableArrayList != null) {
                for (Friend friend2 : parcelableArrayList) {
                    this.f9927j0.put(Long.valueOf(friend2.f13645c), friend2);
                }
                return;
            }
            return;
        }
        if (i9 == 5002) {
            if (i10 == -1 && this.f9914g.f13716c.equals("Dual") && (friend = (Friend) intent.getParcelableExtra("data")) != null) {
                this.f9927j0.put(Long.valueOf(friend.f13645c), friend);
                this.f9914g.f13717d = friend.m15621b();
                C2950b0.m14912k().m15070g(this.f9914g, true);
                m11502Sb();
                C1954e2 c1954e2 = this.f9810E;
                if (c1954e2 != null) {
                    c1954e2.notifyDataSetChanged();
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 5007) {
            if (i10 == -1) {
                Bundle extras2 = intent.getExtras();
                Intent intent2 = new Intent(this, (Class<?>) ChatDialogActivity.class);
                intent2.putExtras(extras2);
                startActivity(intent2);
                finish();
                return;
            }
            return;
        }
        if (i9 != 5008) {
            switch (i9) {
                case 15:
                    if (i10 == -1) {
                        int intExtra = intent.getIntExtra("operationResult", 0);
                        final GroupAlbumObj groupAlbumObj = (GroupAlbumObj) intent.getParcelableExtra("album");
                        if (groupAlbumObj != null) {
                            if ((intExtra & 1) > 0) {
                                runOnUiThread(new Runnable() { // from class: y2.l
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f22342b.m11329l9(groupAlbumObj);
                                    }
                                });
                                return;
                            }
                            if ((intExtra & 2) > 0) {
                                C3072a c3072a = this.f9868S1;
                                if (c3072a != null && (groupAlbumObjM15951j = c3072a.m15951j(groupAlbumObj.m14675b())) != null) {
                                    GroupAlbumObj.m14674a(groupAlbumObjM15951j, groupAlbumObj);
                                }
                                m11607ub();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 16:
                    if (i10 == -1 && (topicObj = (TopicObj) intent.getParcelableExtra("intent_topic")) != null && (c5126e = this.f9969t2) != null) {
                        c5126e.m19988a(topicObj);
                        this.f9969t2.sort(new TopicObj.C2945b());
                        this.f9969t2.notifyDataSetChanged();
                        this.f9946o.smoothScrollToPosition(0);
                        this.f9894a2.setVisibility(4);
                    }
                    CLUtility.m16589t1(this);
                    return;
                case 17:
                    if (i10 == -1) {
                        TopicObj topicObj2 = (TopicObj) intent.getParcelableExtra("intent_topic");
                        boolean booleanExtra = intent.getBooleanExtra("delete", false);
                        if (topicObj2 == null || (c5126e2 = this.f9969t2) == null) {
                            return;
                        }
                        if (booleanExtra) {
                            m11504T7(String.valueOf(topicObj2.m14849o()));
                        } else {
                            c5126e2.m19988a(topicObj2);
                            this.f9969t2.m19991d(topicObj2.m14849o());
                        }
                        m11622zb();
                        if (topicObj2.m14855u()) {
                            m11511Ua(Tab.Poll, false);
                            return;
                        } else {
                            m11511Ua(Tab.Bulletins, false);
                            return;
                        }
                    }
                    return;
                default:
                    switch (i9) {
                        case 5010:
                            if (i10 == -1 && intent.getExtras() != null && (listM7656c = Globals.C1408b.m7655b().m7656c("INTENT_RESULT_SELECTED_USERS_LIST")) != null) {
                                if (this.f9914g.f13716c.equals("Dual")) {
                                    m11552f7(listM7656c);
                                    break;
                                } else {
                                    m11485P6(listM7656c);
                                    m11502Sb();
                                    break;
                                }
                            }
                            break;
                        case 5011:
                            break;
                        case 5012:
                            if (i10 == -1) {
                                String stringExtra = intent.getStringExtra("albumId");
                                if (m11557g8()) {
                                    m11593qb(false);
                                }
                                if (stringExtra != null && !stringExtra.isEmpty()) {
                                    if (stringExtra.equals(this.f9914g.f13718e)) {
                                        String string = Long.toString(this.f9914g.f13727n);
                                        Group group2 = this.f9914g;
                                        groupAlbumObjM15056i = new GroupAlbumObj(string, group2.f13718e, group2.f13717d, "Chat");
                                    } else {
                                        groupAlbumObjM15056i = C2950b0.m14911j().m15056i(stringExtra);
                                    }
                                    if (groupAlbumObjM15056i != null) {
                                        Intent intent3 = new Intent(m11582n7(), (Class<?>) PhotoListActivity.class);
                                        intent3.putExtra("Group", this.f9914g);
                                        intent3.putExtra("ShowShareToMyAlbum", true);
                                        intent3.putExtra("album", groupAlbumObjM15056i);
                                        startActivityForResult(intent3, 5013);
                                        break;
                                    }
                                }
                            }
                            break;
                        case 5013:
                            if (i10 == -1) {
                                ArrayList arrayList2 = (ArrayList) intent.getExtras().getSerializable("import_images");
                                if (arrayList2 != null && arrayList2.size() > 0) {
                                    HashMap map2 = new HashMap();
                                    map2.put("importImages", arrayList2);
                                    this.f9797A2.mo7011a("sendPhoto", map2);
                                }
                                if (m11557g8()) {
                                    m11590pb();
                                    break;
                                }
                            }
                            break;
                        case 5014:
                            if (i10 == -1) {
                                m11621za((Location) intent.getParcelableExtra(FirebaseAnalytics.Param.LOCATION), intent.getParcelableArrayListExtra("location_addresses"), (Uri) intent.getParcelableExtra("snapshotUrl"), 0, null);
                                break;
                            }
                            break;
                        default:
                            switch (i9) {
                                case 5018:
                                    if (i10 == -1) {
                                        ArrayList arrayList3 = (ArrayList) intent.getExtras().getSerializable("import_images");
                                        Intent intent4 = new Intent(m11582n7(), (Class<?>) CreateAlbumActivity.class);
                                        intent4.putExtra("import_images", arrayList3);
                                        intent4.putStringArrayListExtra("album_name_list", m11588p7());
                                        startActivityForResult(intent4, 5019);
                                        break;
                                    }
                                    break;
                                case 5019:
                                    if (i10 == -1) {
                                        Bundle extras3 = intent.getExtras();
                                        if (extras3.getBoolean("isImport")) {
                                            if (extras3.getInt("selectPhotoOpType") == 1) {
                                                String string2 = extras3.getString("create_album_name");
                                                if (string2 != null && !string2.trim().isEmpty()) {
                                                    m11548e7(string2, (ArrayList) extras3.getSerializable("import_images"));
                                                    break;
                                                } else {
                                                    C5187v0.m20267c(R.string.input_album_name);
                                                    break;
                                                }
                                            }
                                        } else {
                                            try {
                                                Intent intent5 = new Intent(m11582n7().getBaseContext(), (Class<?>) PhotoImportActivity.class);
                                                intent5.putExtra("isImportedToAlbums", true);
                                                startActivityForResult(intent5, 5018);
                                                break;
                                            } catch (Exception unused) {
                                                Log.e("ChatDialogActivity", "TODO: Handle Exception");
                                                return;
                                            }
                                        }
                                    }
                                    break;
                                case 5020:
                                    Uri uriM16587t = CLUtility.m16587t(intent.getData());
                                    if (uriM16587t == null) {
                                        uriM16587t = intent.getData();
                                    }
                                    if (uriM16587t != null) {
                                        this.f9819G0.m16336t(uriM16587t);
                                    }
                                    m11511Ua(Tab.Chats, false);
                                    break;
                                case 5021:
                                    if (i10 == -1) {
                                        String stringExtra2 = intent.getStringExtra("messageID");
                                        String stringExtra3 = intent.getStringExtra("SearchChat");
                                        Log.d("ChatDialogActivity", "[onActivityResult] msgId = " + stringExtra2);
                                        if (stringExtra2 != null && (messageObjM15179r = C2950b0.m14916o().m15179r(stringExtra2)) != null) {
                                            m11606ua(messageObjM15179r, stringExtra3);
                                            break;
                                        }
                                    }
                                    break;
                                case 5022:
                                    if (i10 == -1) {
                                        m11615xa(intent.getData());
                                        break;
                                    }
                                    break;
                                default:
                                    switch (i9) {
                                        case 5050:
                                            Globals.m7388i0().m7530c3(false);
                                            this.f9981w2.onActivityResult(i9, i10, intent);
                                            break;
                                        case 5051:
                                            if (i10 == -1) {
                                                this.f9981w2.m12083y().m19493N();
                                                break;
                                            }
                                            break;
                                        case 5052:
                                            if (i10 == -1) {
                                                this.f9981w2.m12083y().onActivityResult(i9, i10, intent);
                                                break;
                                            }
                                            break;
                                    }
                            }
                    }
                    return;
            }
        }
        if (i10 == -1) {
            FriendProfileActivity.FPA_RETURN_TYPE fpa_return_typeValueOf = FriendProfileActivity.FPA_RETURN_TYPE.RETURN_NONE;
            try {
                fpa_return_typeValueOf = FriendProfileActivity.FPA_RETURN_TYPE.valueOf(intent.getStringExtra("type"));
            } catch (Exception e9) {
                Log.e("ChatDialogActivity", "[onActivityResult] OP_SHOW_REQUEST_INVITATION_PROFILE: " + e9.getMessage());
            }
            Group group3 = (Group) intent.getParcelableExtra("StartOtherGroup");
            Long lValueOf = Long.valueOf(intent.getLongExtra("userId", 0L));
            if (fpa_return_typeValueOf == FriendProfileActivity.FPA_RETURN_TYPE.RETURN_CHAT_DUAL) {
                if (group3 != null) {
                    m11579m7(group3);
                    return;
                }
                return;
            }
            if (group3 != null) {
                Group group4 = this.f9914g;
                if (group4.f13727n == group3.f13727n) {
                    group4.f13717d = group3.f13717d;
                    C2950b0.m14912k().m15070g(this.f9914g, true);
                    m11502Sb();
                }
            }
            m11460Gb(lValueOf);
            C1954e2 c1954e22 = this.f9810E;
            if (c1954e22 != null) {
                c1954e22.notifyDataSetChanged();
            }
            Log.d("ChatDialogActivity", "[onActivityResult] OP_SHOW_USER_INFO: return type (%s)" + fpa_return_typeValueOf.name());
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ChatDialogMode chatDialogMode = this.f9983x0;
        ChatDialogMode chatDialogMode2 = ChatDialogMode.NORMAL;
        if (chatDialogMode.equals(chatDialogMode2)) {
            m11536b7(true);
        } else {
            m11600t2(chatDialogMode2, 0);
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) throws NumberFormatException {
        String str;
        if (this.f9810E == null) {
            Log.d("ChatDialogActivity", "[onContextItemSelected] MenuItem = " + menuItem + " , mMessageAdapter = " + this.f9810E);
            return false;
        }
        int itemId = menuItem.getItemId();
        Intent intent = menuItem.getIntent();
        menuItem.setIntent(null);
        if (intent != null) {
            if (!intent.hasExtra("data")) {
                Log.d("ChatDialogActivity", "[onContextItemSelected] No data");
                return false;
            }
            int intExtra = intent.getIntExtra("data", -1);
            if (intExtra == -1) {
                Log.d("ChatDialogActivity", "[onContextItemSelected] Get Wrong Index");
                return false;
            }
            int headerViewsCount = intExtra - this.f9946o.getHeaderViewsCount();
            if (headerViewsCount >= 0 && !this.f9810E.m11761P0(headerViewsCount)) {
                MessageObj item = this.f9810E.getItem(headerViewsCount);
                if (headerViewsCount < this.f9810E.getCount()) {
                    if (item != null && (str = this.f9912f1) != null && str.equals(item.m14777o())) {
                        boolean zM25402m = C6818b.m25402m(item);
                        if (itemId == this.f9897b2.f22367a) {
                            C6566a.m25158q(zM25402m, "Forward");
                            m11600t2(ChatDialogMode.FORWARD, intExtra);
                        } else if (itemId == this.f9901c2.f22367a) {
                            C6566a.m25158q(zM25402m, "Copy");
                            this.f9810E.m11831z0(item);
                        } else if (itemId == this.f9905d2.f22367a) {
                            C6566a.m25158q(zM25402m, "Delete");
                            m11600t2(ChatDialogMode.DELETE, intExtra);
                        } else if (itemId == this.f9909e2.f22367a) {
                            this.f9810E.m11742G0(item);
                        } else if (itemId == this.f9913f2.f22367a) {
                            C6566a.m25158q(true, "Recall");
                            m11600t2(ChatDialogMode.RECALL, intExtra);
                        } else if (itemId == this.f9917g2.f22367a) {
                            C6566a.m25158q(zM25402m, "Delete");
                            this.f9810E.m11736B0(item);
                        } else if (itemId == this.f9921h2.f22367a) {
                            this.f9810E.m11739E0(item);
                        } else if (itemId == this.f9925i2.f22367a) {
                            m11600t2(ChatDialogMode.FORWARD_PHOTO_WITH_COMMENT, intExtra);
                        } else if (itemId == this.f9929j2.f22367a) {
                            m11524Y6(ChatDialogMode.SAVE_TO_MY_DEVICE, intExtra);
                        } else if (itemId == this.f9933k2.f22367a) {
                            m11600t2(ChatDialogMode.SAVE_TO_MY_ALBUM, intExtra);
                        } else if (itemId == this.f9937l2.f22367a) {
                            m11600t2(ChatDialogMode.SAVE_TO_GROUP_ALBUM, intExtra);
                        } else if (itemId == this.f9941m2.f22367a) {
                            this.f9810E.m11830y0(item);
                        } else if (itemId == this.f9945n2.f22367a || itemId == this.f9949o2.f22367a) {
                            if (itemId == this.f9949o2.f22367a) {
                                C6566a.m25158q(true, "Quote");
                            } else {
                                C6566a.m25158q(false, "Reply");
                            }
                            long j9 = (item.m14778p().equals(MessageObj.MessageType.Photo) || item.m14778p().equals(MessageObj.MessageType.PhotoNote) || item.m14778p().equals(MessageObj.MessageType.Comment)) ? NumberUtils.toLong(item.m14747I("mediaId"), -1L) : -1L;
                            if (j9 != -1) {
                                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j9);
                                if (c2973l0M14725v == null || TextUtils.isEmpty(c2973l0M14725v.m15147s())) {
                                    C5187v0.m20267c(R.string.error_media_not_found);
                                    return false;
                                }
                                m11526Y9(c2973l0M14725v.m15131c(), c2973l0M14725v.m15144p(), item.m14784v(), true);
                            } else {
                                this.f9975v0 = item;
                                this.f9974v.setVisibility(0);
                                String strM14747I = item.m14778p().equals(MessageObj.MessageType.ReplyText) ? item.m14747I("replyText") : item.m14778p().equals(MessageObj.MessageType.File) ? item.m14747I("mediaName") : item.m14779q();
                                m11559gb();
                                this.f9978w.setText(strM14747I);
                                m11523Xa(item.m14745G(), this.f9986y);
                                C2027b c2027b = this.f9981w2;
                                Boolean bool = Boolean.FALSE;
                                c2027b.m12075a0(bool, bool, bool);
                                this.f9981w2.m12067S();
                                CLUtility.m16606x2(this);
                            }
                        } else if (itemId == this.f9953p2.f22367a) {
                            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(item.m14781s());
                            if (messageObjM15179r == null) {
                                C5187v0.m20267c(R.string.error_find_source_message);
                                return false;
                            }
                            Group groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j());
                            Intent intent2 = new Intent(m11582n7(), (Class<?>) ChatDialogActivity.class);
                            intent2.putExtra("Group", groupM15077n);
                            intent2.putExtra("isFinishDirectly", true);
                            intent2.putExtra("messageID", messageObjM15179r.m14777o());
                            intent2.putExtra("SearchChat", messageObjM15179r.m14779q());
                            startActivity(intent2);
                        } else if (itemId == this.f9957q2.f22367a) {
                            Group group = this.f9914g;
                            if (!group.f13714M) {
                                this.f9899c0.m15716T0(group.f13727n, true);
                            }
                        } else {
                            if (itemId != this.f9961r2.f22367a) {
                                if (itemId != this.f9965s2.f22367a) {
                                    Log.d("ChatDialogActivity", "[onContextItemSelected] No matched Click id : " + itemId);
                                    return false;
                                }
                                Log.d("ChatDialogActivity", "[onContextItemSelected] start to share to other apps (" + itemId + ")");
                                if (item.m14778p().equals(MessageObj.MessageType.ReplyText)) {
                                    m11554fa("text/plain", null, item.m14747I("replyText"), null);
                                } else if (item.m14778p().equals(MessageObj.MessageType.Photo) || item.m14778p().equals(MessageObj.MessageType.Video) || item.m14778p().equals(MessageObj.MessageType.File)) {
                                    C2973l0 c2973l0M14725v2 = C2950b0.m14914m().m14725v(NumberUtils.toLong(item.m14747I("mediaId"), -1L));
                                    if (!item.m14778p().equals(MessageObj.MessageType.File)) {
                                        m11539bb(c2973l0M14725v2);
                                    } else if (c2973l0M14725v2 != null) {
                                        C6468p.m24787m().m24798v(m11582n7(), c2973l0M14725v2.m15148t().f13200d, c2973l0M14725v2.m15145q(), C1199a.m5277a(c2973l0M14725v2.m15148t().f13204h, c2973l0M14725v2.m15148t().f13205i), true);
                                    }
                                } else {
                                    m11554fa("text/plain", null, item.m14779q(), null);
                                }
                                return false;
                            }
                            m11600t2(ChatDialogMode.ADD_TO_TODO, intExtra);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
        View view = this.f9858Q;
        if (view != null) {
            m11527Ya(view, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0574  */
    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle bundle) throws Resources.NotFoundException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Log.v("ChatDialogActivity", "[timer] onCreate");
        super.onCreate(bundle);
        this.f9947o0 = getResources().openRawResourceFd(R.raw.msg_receive_chatroom);
        this.f9971u0 = new C4619d(this, new C2023z1());
        this.f9815F0 = new ArrayList();
        setContentView(R.layout.activity_chat_dialog);
        LockScreenActivity.m8713k();
        f9789U2 = Globals.m7388i0().m7478R0();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        this.f9794A = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this.f9871T0);
        this.f9794A.setColorSchemeResources(R.color.you_color_normal_blue_text_pressed, R.color.you_color_normal_blue_text, R.color.you_color_normal_gray_text, R.color.you_color_normal_gray);
        ImageView imageView = (ImageView) findViewById(R.id.ChatDialogBackBtn);
        this.f9918h = imageView;
        imageView.setOnClickListener(this.f9890Z0);
        this.f9802C = (ImageView) findViewById(R.id.NotificationDisableBtn);
        Button button = (Button) findViewById(R.id.ChatDialogSelectDone);
        this.f9934l = button;
        button.setOnClickListener(this.f9948o1);
        findViewById(R.id.ChatDialogSelectCancel).setOnClickListener(this.f9952p1);
        this.f9822H = findViewById(R.id.ChatDialogEditConfirmArea);
        this.f9826I = findViewById(R.id.forwardWithOriginalName);
        findViewById(R.id.ChatDialogTopBarTitleArea).setOnClickListener(new View.OnClickListener() { // from class: y2.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f22222b.m11361p9(view);
            }
        });
        this.f9830J = findViewById(R.id.waitingCursor);
        this.f9814F = (TextView) findViewById(R.id.chatRoomBadge);
        View viewFindViewById = findViewById(R.id.scheduleListBtn);
        this.f9818G = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f9875U0);
        this.f9854P = (ViewGroup) findViewById(R.id.chatHistoryLayout);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.tab_chats);
        this.f9834K = viewGroup;
        viewGroup.setOnClickListener(this.f9960r1);
        ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.tab_albums);
        this.f9838L = viewGroup2;
        viewGroup2.setOnClickListener(this.f9836K1);
        ViewGroup viewGroup3 = (ViewGroup) findViewById(R.id.tab_bulletins);
        this.f9842M = viewGroup3;
        viewGroup3.setOnClickListener(this.f9840L1);
        ViewGroup viewGroup4 = (ViewGroup) findViewById(R.id.tab_polls);
        this.f9846N = viewGroup4;
        viewGroup4.setOnClickListener(this.f9844M1);
        this.f9850O = (FrameLayout) findViewById(R.id.fragment_container);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageViewAddPhoto);
        this.f9962s = imageView2;
        imageView2.setOnClickListener(this.f9882W1);
        ImageView imageView3 = (ImageView) findViewById(R.id.btn_write);
        this.f9966t = imageView3;
        imageView3.setOnClickListener(this.f9973u2);
        this.f9864R1 = findViewById(R.id.relativeLayoutIntroduceAlbum);
        this.f9894a2 = (RelativeLayout) findViewById(R.id.relativeLayoutIntroduceBulletin);
        this.f9970u = (TextView) findViewById(R.id.chat_unread);
        this.f9885X1 = (TextView) findViewById(R.id.bulletin_unread);
        this.f9888Y1 = (TextView) findViewById(R.id.poll_unread);
        this.f9891Z1 = (TextView) findViewById(R.id.bulletin_member_post_unread);
        ImageView imageView4 = (ImageView) findViewById(R.id.ChatDialogMoreBtn);
        this.f9922i = imageView4;
        imageView4.setOnClickListener(this.f9972u1);
        ImageView imageView5 = (ImageView) findViewById(R.id.ChatDialogSearchChatBtn);
        this.f9930k = imageView5;
        imageView5.setOnClickListener(this.f9976v1);
        if (Globals.m7388i0().m7499V1().booleanValue()) {
            this.f9930k.setVisibility(0);
        } else {
            this.f9930k.setVisibility(8);
        }
        this.f9942n = (TextView) findViewById(R.id.ChatDialogTopBarTitle);
        ImageView imageView6 = (ImageView) findViewById(R.id.ChatDialogAddBtn);
        this.f9926j = imageView6;
        imageView6.setOnClickListener(this.f9882W1);
        this.f9938m = (TextView) findViewById(R.id.ChatDialogTopBarTitleCount);
        this.f9954q = (TextView) findViewById(R.id.noConnectionText);
        ImageView imageView7 = (ImageView) findViewById(R.id.actionButton);
        this.f9855P0 = imageView7;
        imageView7.setOnTouchListener(this.f9879V1);
        Group group = (Group) m11582n7().getIntent().getParcelableExtra("Group");
        this.f9914g = group;
        String str = group == null ? "empty from Intent" : null;
        this.f9892a0 = getIntent().getLongExtra("StartTimeOnClick", 0L);
        this.f9899c0 = new FriendsClient(true);
        String string = Long.toString(getIntent().getLongExtra("groupId", 0L));
        ULogUtility.m16676l("ChatDialogActivity", "mGroupIDFromShortcut:" + string);
        if (!"0".equals(string)) {
            this.f9914g = C2950b0.m14912k().m15077n(string);
            ULogUtility.m16676l("ChatDialogActivity", "[timer] getGroupInfo start:" + stopWatch.getTime() + " ms");
            if (this.f9914g == null) {
                try {
                    Group groupM15650P = FriendsClient.m15650P(string);
                    this.f9914g = groupM15650P;
                    if (groupM15650P == null) {
                        str = "empty from server: " + string;
                    }
                } catch (Exception e9) {
                    C5154j.m20076j(e9);
                    str = "exception: " + e9.getMessage();
                }
                ULogUtility.m16676l("ChatDialogActivity", "[timer] getGroupInfo end:" + stopWatch.getTime() + " ms");
            }
        }
        if (m11505T9(str)) {
            return;
        }
        Group group2 = this.f9914g;
        if (group2.f13714M) {
            this.f9899c0.m15716T0(group2.f13727n, false);
        }
        m11545d8();
        this.f9974v = findViewById(R.id.ReplyMessageLayout);
        this.f9986y = (ImageView) findViewById(R.id.replyAvatar);
        this.f9978w = (TextView) findViewById(R.id.replyText);
        this.f9978w.setMaxWidth(Globals.m7388i0().getResources().getDisplayMetrics().widthPixels - Math.round(TypedValue.applyDimension(1, 138.0f, Globals.m7388i0().getResources().getDisplayMetrics())));
        View viewFindViewById2 = findViewById(R.id.replyClose);
        this.f9982x = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: y2.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f22231b.m11337m9(view);
            }
        });
        this.f9807D0 = findViewById(R.id.e2eeReminderLayout);
        this.f9811E0 = findViewById(R.id.enableE2EEView);
        m11494R6();
        Group group3 = this.f9914g;
        if (group3 != null) {
            if (group3.f13733t) {
                this.f9802C.setVisibility(0);
            } else {
                this.f9802C.setVisibility(8);
            }
            this.f9910f = this.f9914g.f13723j;
            Globals.m7388i0().m7480R2(String.valueOf(this.f9914g.f13727n));
            boolean z8 = (this.f9914g.f13738y.equals("Official") || this.f9914g.f13738y.equals("Corporate")) ? false : true;
            if (bundle == null) {
                this.f9981w2 = new C2027b();
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("enableChatPlus", z8);
                bundle2.putBoolean("enableChatMeeting", !this.f9914g.m15751i());
                bundle2.putParcelable("Group", this.f9914g);
                this.f9981w2.setArguments(bundle2);
                this.f9981w2.m12069U(this.f9797A2);
                this.f9981w2.m12070V(this.f9993z2);
                getSupportFragmentManager().mo1844a().m1981c(R.id.menuFragmentContainer, this.f9981w2, "MessageInput").mo1799n(this.f9981w2).mo1794h();
                m11543cb(true);
                Log.v("ChatDialogActivity", "[timer] onCreate mMessageInputFragment transaction " + stopWatch.getTime() + " ms");
            } else {
                C2027b c2027b = (C2027b) getSupportFragmentManager().mo1848e("MessageInput");
                this.f9981w2 = c2027b;
                c2027b.m12069U(this.f9797A2);
                this.f9981w2.m12070V(this.f9993z2);
                m11543cb(true);
                Log.v("ChatDialogActivity", "[timer] onCreate mMessageInputFragment transaction by tag " + stopWatch.getTime() + " ms");
            }
            ULogUtility.m16676l("ChatDialogActivity", "[timer] mMessageInputFragment transaction:" + stopWatch.getTime() + " ms");
        }
        boolean booleanExtra = getIntent().getBooleanExtra("isQueryMsgTimeout", false);
        m11571jb(booleanExtra);
        if (booleanExtra) {
            AsyncTaskC2897h0 asyncTaskC2897h0M7488T0 = Globals.m7388i0().m7488T0();
            if (asyncTaskC2897h0M7488T0 == null || asyncTaskC2897h0M7488T0.m14367c()) {
                m11571jb(false);
            } else {
                asyncTaskC2897h0M7488T0.m14365a(this.f9908e1);
            }
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.coverInputBarArea);
        this.f9990z = relativeLayout;
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: y2.g
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ChatDialogActivity.m11345n9(view, motionEvent);
            }
        });
        TextView textView = (TextView) findViewById(R.id.coverInputBarText);
        if (textView != null) {
            Group group4 = this.f9914g;
            String string2 = (group4 == null || !group4.f13716c.equals("Dual")) ? getResources().getString(R.string.chat_dialog_group_disabled) : String.format(getResources().getString(R.string.chat_dialog_disabled_message), this.f9914g.f13717d);
            textView.setText(string2);
        }
        this.f9823H0 = findViewById(R.id.quickReturnView);
        this.f9946o = (ListView) findViewById(R.id.ChatDialogListView);
        this.f9950p = findViewById(R.id.dateIndicator);
        ((RelativeLayout) findViewById(R.id.messageListLayout)).addOnLayoutChangeListener(this.f9878V0);
        if (Group.m15743f(this.f9914g.f13716c)) {
            Group group5 = this.f9914g;
            if (group5.f13711J || group5.m15751i() || this.f9914g.f13712K) {
                this.f9823H0.setVisibility(8);
            } else {
                this.f9823H0.setVisibility(0);
            }
        }
        this.f9946o.setSelector(android.R.color.transparent);
        if (Group.m15743f(this.f9914g.f13716c)) {
            Group group6 = this.f9914g;
            if (group6.f13711J || group6.m15751i() || this.f9914g.f13712K) {
                this.f9867S0 = new C2014w1(this.f9946o, null);
            } else {
                C2014w1 c2014w1 = new C2014w1(this.f9946o, this.f9823H0);
                this.f9867S0 = c2014w1;
                c2014w1.m25323g(this);
            }
        }
        this.f9799B0.m25249e(this.f9867S0);
        this.f9799B0.m25248d();
        this.f9946o.setOnScrollListener(this.f9867S0);
        this.f9946o.setOnTouchListener(new View.OnTouchListener() { // from class: y2.h
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f22265b.m11353o9(view, motionEvent);
            }
        });
        m11519Wa(Tab.values()[getIntent().getIntExtra("chatDialogTabType", Tab.Chats.ordinal())]);
        m11574ka(this.f9914g);
        new AsyncTaskC2008u1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        m11595ra();
        if (Globals.m7388i0().m7460N1()) {
            this.f9877V = true;
        } else {
            m11612wa();
        }
        m11589pa();
        if (Group.m15743f(this.f9914g.f13716c)) {
            m11602ta();
        }
        m11533a8();
        this.f9967t0 = new C4619d(this, new C1935a());
        m11486P7();
        m11482O7();
        m11535ab(8, false);
        this.f9991z0 = new Timer();
        stopWatch.stop();
        ULogUtility.m16676l("ChatDialogActivity", "[timer] onCreate exit:" + stopWatch.getTime() + " ms");
        Log.v("ChatDialogActivity", "[timer] onCreate exit " + stopWatch.getTime() + " ms");
        m11443B7();
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        int iIntValue;
        if (contextMenu == null || view == null || this.f9810E == null || this.f9983x0 != ChatDialogMode.NORMAL || !C2925v.m14625v(this.f9914g)) {
            return;
        }
        this.f9858Q = view;
        m11527Ya(view, false);
        Object tag = view.getTag(R.id.tag_Position);
        if (!(tag instanceof Integer)) {
            tag = view.getTag();
            if (tag instanceof String) {
                tag = view.getTag(R.id.contentView);
            }
        }
        boolean z8 = tag instanceof Integer;
        Integer num = (Integer) tag;
        if (num != null && (iIntValue = num.intValue()) >= 0) {
            int itemViewType = this.f9810E.getItemViewType(iIntValue);
            MessageObj item = this.f9810E.getItem(iIntValue);
            this.f9912f1 = item.m14777o();
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            intent.putExtra("data", iIntValue + this.f9946o.getHeaderViewsCount());
            if (C6598a.m25244f(0, itemViewType)) {
                m11501Sa(item, arrayList);
            }
            if (arrayList.isEmpty()) {
                m11506Ta(itemViewType, item, arrayList);
                if (arrayList.isEmpty()) {
                    return;
                }
            }
            int i9 = 0;
            while (i9 < arrayList.size()) {
                C6696m5 c6696m5 = arrayList.get(i9);
                i9++;
                contextMenu.add(0, c6696m5.f22367a, i9, m11500S9(getResources().getDrawable(c6696m5.f22369c), getResources().getString(c6696m5.f22368b))).setIntent(intent);
            }
            super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        }
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() throws IOException {
        C5152i0.m20065b(this.f9898c);
        C5152i0.m20065b(this.f9816F1);
        C5152i0.m20065b(this.f9963s0);
        if (this.f9795A0.equals(Tab.Bulletins)) {
            C2950b0.m14906e().m14988r(this.f9914g.f13727n);
            C2907m0.m14454I().m14516z(this.f9914g.f13727n);
        }
        try {
            this.f9947o0.close();
        } catch (IOException e9) {
            e9.printStackTrace();
        }
        if (this.f9946o != null) {
            for (int i9 = 0; i9 < this.f9946o.getChildCount(); i9++) {
                View childAt = this.f9946o.getChildAt(i9);
                if (childAt.getTag() instanceof C6819c) {
                    C6819c c6819c = (C6819c) childAt.getTag();
                    RelativeLayout relativeLayout = c6819c.f22638z;
                    if (relativeLayout != null) {
                        relativeLayout.setOnClickListener(null);
                    }
                    ImageView imageView = c6819c.f22613b;
                    if (imageView != null) {
                        imageView.setOnClickListener(null);
                    }
                }
            }
            this.f9946o.setOnScrollListener(null);
            this.f9946o.setAdapter((ListAdapter) null);
        }
        this.f9927j0.clear();
        XMPPManager.m14184g0().m14233Z0(this.f9825H2);
        XMPPManager.m14184g0().m14232Y0(this.f9900c1);
        C5321e.m20824o().m20832B0(this.f9968t1);
        XMPPManager.m14184g0().m14235a1(this.f9896b1);
        XMPPArchiveHelper.m14122D(this.f9904d1);
        C6196d0.m23692d().m23698h(this.f9944n1);
        UploadMultipleChatMediaHelperQueue.m16892F().m16931W(this.f9887Y0);
        C2925v.m14631y();
        if (Globals.m7388i0().m7488T0() != null) {
            Globals.m7388i0().m7488T0().m14370f(this.f9908e1);
        }
        C1938a2 c1938a2 = this.f9872T1;
        if (c1938a2 != null) {
            c1938a2.m24447a();
            this.f9872T1 = null;
        }
        C2026a c2026a = this.f9889Z;
        if (c2026a != null) {
            c2026a.m12015l();
        }
        AsyncTaskC1946c2 asyncTaskC1946c2 = this.f9883X;
        if (asyncTaskC1946c2 != null) {
            asyncTaskC1946c2.cancel(true);
        }
        AsyncTaskC1950d2 asyncTaskC1950d2 = this.f9886Y;
        if (asyncTaskC1950d2 != null) {
            asyncTaskC1950d2.cancel(true);
        }
        AsyncTask<Void, Void, Void> asyncTask = this.f9809D2;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        AsyncTask<Void, Void, C2973l0> asyncTask2 = this.f9829I2;
        if (asyncTask2 != null) {
            asyncTask2.cancel(true);
        }
        AsyncTask<Void, Void, GroupAlbumObj> asyncTask3 = this.f9837K2;
        if (asyncTask3 != null) {
            asyncTask3.cancel(true);
        }
        AsyncTaskC1970i2 asyncTaskC1970i2 = this.f9979w0;
        if (asyncTaskC1970i2 != null) {
            asyncTaskC1970i2.cancel(true);
        }
        AsyncTask<Void, Void, Pair<GroupAlbumObj, Boolean>> asyncTask4 = this.f9849N2;
        if (asyncTask4 != null) {
            asyncTask4.cancel(false);
        }
        AsyncTask<Void, TopicObj, TopicObj> asyncTask5 = this.f9865R2;
        if (asyncTask5 != null) {
            asyncTask5.cancel(true);
        }
        AsyncTaskC1942b2 asyncTaskC1942b2 = this.f9861Q2;
        if (asyncTaskC1942b2 != null) {
            asyncTaskC1942b2.cancel(true);
        }
        AsyncTaskC1942b2 asyncTaskC1942b22 = this.f9845M2;
        if (asyncTaskC1942b22 != null) {
            asyncTaskC1942b22.cancel(true);
        }
        FriendsClient friendsClient = this.f9899c0;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        ListView listView = this.f9946o;
        if (listView != null) {
            listView.setOnScrollListener(null);
            this.f9946o.setOnTouchListener(null);
        }
        C2027b c2027b = this.f9981w2;
        if (c2027b != null) {
            c2027b.m12069U(null);
            this.f9981w2.m12070V(null);
        }
        ViewGroup viewGroup = this.f9834K;
        if (viewGroup != null) {
            viewGroup.setOnClickListener(null);
        }
        ViewGroup viewGroup2 = this.f9838L;
        if (viewGroup2 != null) {
            viewGroup2.setOnClickListener(null);
        }
        ViewGroup viewGroup3 = this.f9842M;
        if (viewGroup3 != null) {
            viewGroup3.setOnClickListener(null);
        }
        Timer timer = this.f9907e0;
        if (timer != null) {
            timer.cancel();
            this.f9907e0 = null;
        }
        Timer timer2 = this.f9991z0;
        if (timer2 != null) {
            timer2.cancel();
            this.f9991z0 = null;
        }
        C3114a c3114a = this.f9819G0;
        if (c3114a != null) {
            c3114a.m16332p();
        }
        this.f9839L0.m19697a();
        C5166n.m20136g();
        super.onDestroy();
        Log.d("ChatDialogActivity", "onDestroy");
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() throws IllegalStateException {
        super.onPause();
        this.f9877V = true;
        C2909n0.m14531b().m14534d(this.f9893a1);
        C2907m0.m14454I().m14494V(this.f9860Q1);
        unregisterReceiver(this.f9916g1);
        f9790V2 = 0L;
        if (this.f9795A0.equals(Tab.Chats)) {
            m11469Jb(true);
        }
        this.f9819G0.m16333q();
        m11581mb();
        C6456d.m24714D().m24751K(getClass().getSimpleName());
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() throws Resources.NotFoundException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ULogUtility.m16676l("ChatDialogActivity", "onResume");
        Log.v("ChatDialogActivity", "[timer] onResume");
        super.onResume();
        C6456d.m24714D().m24743A(getClass().getSimpleName());
        C2909n0.m14531b().m14532a(this.f9893a1);
        if (Build.VERSION.SDK_INT >= 34) {
            registerReceiver(this.f9916g1, new IntentFilter("Custom_Broadcast_Send_Log"), 2);
        } else {
            registerReceiver(this.f9916g1, new IntentFilter("Custom_Broadcast_Send_Log"));
        }
        C2907m0.m14454I().m14510t(this.f9860Q1);
        m11442Ab();
        Group group = this.f9914g;
        if (group != null) {
            f9790V2 = group.f13727n;
        }
        new AsyncTaskC1951e().executeOnExecutor(f9793Y2, new Void[0]);
        m11502Sb();
        m11451Db();
        m11498S6();
        m11448Cb();
        m11488Pb();
        if (!XMPPManager.m14184g0().m14204A0()) {
            m11571jb(false);
        }
        if (this.f9810E != null) {
            this.f9946o.post(new Runnable() { // from class: y2.k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22331b.m11377r9();
                }
            });
        }
        if (this.f9914g != null) {
            ((NotificationManager) getSystemService("notification")).cancel(this.f9914g.f13723j, 1);
        }
        this.f9819G0.m16334r();
        C2026a c2026a = this.f9889Z;
        if (c2026a != null) {
            c2026a.m12023v();
        }
        stopWatch.stop();
        Log.v("ChatDialogActivity", "[timer] onResume exit " + stopWatch.getTime() + " ms");
        ULogUtility.m16676l("ChatDialogActivity", "[timer] onResume exit:" + stopWatch.getTime() + " ms");
        m11496R9();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        C6468p.m24787m().m24794k(this.f9881W0);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        C6468p.m24787m().m24800x(this.f9881W0);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        if (!z8) {
            this.f9877V = true;
            return;
        }
        Runnable runnable = new Runnable() { // from class: y2.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f22210b.m11385s9();
            }
        };
        if (Globals.m7388i0().m7460N1()) {
            this.f9799B0.postDelayed(runnable, 1000L);
        } else {
            runnable.run();
        }
    }

    /* renamed from: p7 */
    public final ArrayList<String> m11588p7() {
        List<GroupAlbumObj> listM15950i;
        ArrayList<String> arrayList = new ArrayList<>();
        C3072a c3072a = this.f9868S1;
        if (c3072a != null && (listM15950i = c3072a.m15950i()) != null) {
            Iterator<GroupAlbumObj> it = listM15950i.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().m14676c());
            }
        }
        return arrayList;
    }

    /* renamed from: pa */
    public final void m11589pa() {
        new AsyncTaskC2013w0().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: pb */
    public final void m11590pb() {
        m11593qb(true);
    }

    /* renamed from: q7 */
    public String m11591q7(MessageObj messageObj) {
        String strM14747I = messageObj.m14747I("statusV2");
        return C5170o0.m20170e(strM14747I) ? messageObj.m14747I("status") : strM14747I;
    }

    /* renamed from: qa */
    public final void m11592qa() {
        this.f9914g.f13706E = false;
        C2950b0.m14912k().m15062A(String.valueOf(this.f9914g.f13727n), this.f9914g, "isArchive");
    }

    /* renamed from: qb */
    public final void m11593qb(boolean z8) {
        m11596rb(z8, 0L);
    }

    /* renamed from: r7 */
    public String m11594r7() {
        return this.f9981w2.m12084z();
    }

    /* renamed from: ra */
    public final void m11595ra() {
        Log.d("ChatDialogActivity", "resetMessageList");
        this.f9806D = new HashMap();
        XMPPManager.m14184g0().m14207H(this.f9825H2);
        XMPPManager.m14184g0().m14206G(this.f9900c1);
        C5321e.m20824o().m20875k(this.f9968t1);
        new AsyncTaskC1947d().executeOnExecutor(f9793Y2, new Void[0]);
    }

    /* renamed from: rb */
    public final void m11596rb(final boolean z8, long j9) {
        m11535ab(8, false);
        final View currentFocus = getCurrentFocus();
        if (j9 == 0) {
            this.f9946o.post(new Runnable() { // from class: y2.j1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22322b.m11048A9(currentFocus, z8);
                }
            });
        } else {
            this.f9946o.postDelayed(new Runnable() { // from class: y2.k1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f22333b.m11056B9(currentFocus, z8);
                }
            }, j9);
        }
    }

    /* renamed from: s7 */
    public Group m11597s7() {
        return this.f9914g;
    }

    /* renamed from: sa */
    public final void m11598sa(MessageObj messageObj) {
        this.f9919h0.add(0, m11556g7(messageObj));
    }

    /* renamed from: sb */
    public final void m11599sb() {
        final View currentFocus = getCurrentFocus();
        this.f9946o.post(new Runnable() { // from class: y2.e2
            @Override // java.lang.Runnable
            public final void run() {
                this.f22226b.m11064C9(currentFocus);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i9, Bundle bundle) {
        super.startActivityForResult(intent, i9, bundle);
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            this.f9985x2 = c1954e2.getCount();
        }
    }

    /* renamed from: t2 */
    public final void m11600t2(ChatDialogMode chatDialogMode, int i9) {
        C2027b c2027b = this.f9981w2;
        Boolean bool = Boolean.FALSE;
        c2027b.m12075a0(bool, bool, bool);
        CLUtility.m16589t1(this);
        this.f9983x0 = chatDialogMode;
        if (chatDialogMode.equals(ChatDialogMode.NORMAL)) {
            this.f9946o.clearChoices();
            this.f9946o.setChoiceMode(0);
            this.f9822H.setVisibility(8);
            this.f9981w2.m12073Y();
        } else {
            this.f9946o.setChoiceMode(2);
            this.f9946o.setItemChecked(i9, true);
            this.f9822H.setVisibility(0);
            this.f9974v.setVisibility(8);
            this.f9975v0 = null;
            if (ChatDialogMode.FORWARD.equals(this.f9983x0) || ChatDialogMode.ADD_TO_TODO.equals(this.f9983x0)) {
                int lastVisiblePosition = this.f9946o.getLastVisiblePosition();
                this.f9826I.setVisibility(0);
                final View viewFindViewById = this.f9826I.findViewById(R.id.senderNameToggle);
                UserInfo userInfoM16497V0 = CLUtility.m16497V0(Globals.m7372O());
                viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: y2.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ChatDialogActivity.m11328l8(viewFindViewById, view);
                    }
                });
                viewFindViewById.setSelected(userInfoM16497V0 == null || userInfoM16497V0.f13800y);
                if (i9 == lastVisiblePosition) {
                    this.f9946o.smoothScrollToPosition(lastVisiblePosition);
                }
            } else {
                this.f9826I.setVisibility(8);
            }
            this.f9981w2.m12057A();
            m11454Eb();
        }
        m11559gb();
        this.f9810E.notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf A[Catch: all -> 0x00e7, NumberFormatException -> 0x00ea, TryCatch #5 {NumberFormatException -> 0x00ea, all -> 0x00e7, blocks: (B:29:0x00b7, B:31:0x00bf, B:37:0x00e2, B:34:0x00d8), top: B:63:0x00b7 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0124 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: t7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Map<String, Object> m11601t7() throws Throwable {
        String stringExtra;
        HashMap map = new HashMap();
        map.put("Group", (Group) getIntent().getParcelableExtra("Group"));
        String stringExtra2 = getIntent().getStringExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
        MessageObj messageObjM15179r = stringExtra2 != null ? C2950b0.m14916o().m15179r(stringExtra2) : null;
        if (messageObjM15179r != null) {
            boolean z8 = true;
            map.put("isNormalMessage", Boolean.valueOf(!messageObjM15179r.m14752N()));
            String strM14747I = messageObjM15179r.m14747I("albumId");
            if (messageObjM15179r.m14778p() != MessageObj.MessageType.CreateMedia) {
                MessageObj.MessageType messageTypeM14778p = messageObjM15179r.m14778p();
                MessageObj.MessageType messageType = MessageObj.MessageType.PhotoNote;
                if (messageTypeM14778p == messageType || messageObjM15179r.m14778p() == MessageObj.MessageType.Photo || messageObjM15179r.m14778p() == MessageObj.MessageType.Comment) {
                    long j9 = -1;
                    boolean z9 = false;
                    try {
                        try {
                            long j10 = NumberUtils.toLong(messageObjM15179r.m14747I("mediaId"), -1L);
                            if (j10 != -1) {
                                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(j10);
                                map.put("media", c2973l0M14725v);
                                if (messageObjM15179r.m14778p() != messageType) {
                                    z8 = false;
                                    try {
                                        if (messageObjM15179r.m14778p() == MessageObj.MessageType.Comment) {
                                            j9 = NumberUtils.toLong(messageObjM15179r.m14747I("commentId"), -1L);
                                            String strM14747I2 = messageObjM15179r.m14747I("commentType");
                                            String str = "None";
                                            if ("Text".equals(strM14747I2)) {
                                                str = "CommentText";
                                            } else if ("Audio".equals(strM14747I2)) {
                                                str = "CommentMedia";
                                            }
                                            map.put("commentType", str);
                                        }
                                        z9 = z8;
                                    } catch (NumberFormatException e9) {
                                        e = e9;
                                        z9 = z8;
                                        Log.d("ChatDialogActivity", e.getMessage());
                                        map.put("isNoteMessage", Boolean.valueOf(z9));
                                        map.put("commentId", Long.valueOf(j9));
                                        stringExtra = getIntent().getStringExtra(Constants.FirelogAnalytics.PARAM_TOPIC);
                                        if (stringExtra != null) {
                                        }
                                        return map;
                                    } catch (Throwable th) {
                                        th = th;
                                        z9 = z8;
                                        map.put("isNoteMessage", Boolean.valueOf(z9));
                                        map.put("commentId", Long.valueOf(j9));
                                        throw th;
                                    }
                                } else {
                                    if (C5170o0.m20170e(c2973l0M14725v.m15137i())) {
                                        if (C2950b0.m14915n().m15104c(String.valueOf(j10)) == null) {
                                        }
                                    }
                                    if (messageObjM15179r.m14778p() == MessageObj.MessageType.Comment) {
                                    }
                                    z9 = z8;
                                }
                            }
                        } catch (NumberFormatException e10) {
                            e = e10;
                        }
                        map.put("isNoteMessage", Boolean.valueOf(z9));
                        map.put("commentId", Long.valueOf(j9));
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } else if (strM14747I != null) {
                map.put("album", C2950b0.m14911j().m15056i(strM14747I));
            }
        }
        stringExtra = getIntent().getStringExtra(Constants.FirelogAnalytics.PARAM_TOPIC);
        if (stringExtra != null) {
            try {
                map.put(Constants.FirelogAnalytics.PARAM_TOPIC, C2950b0.m14906e().m14984n(Long.parseLong(stringExtra)));
            } catch (NumberFormatException e11) {
                e11.printStackTrace();
            }
        }
        return map;
    }

    /* renamed from: ta */
    public final void m11602ta() {
        new AsyncTaskC1943c().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: tb, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final void m11056B9(View view, boolean z8) {
        C1954e2 c1954e2;
        if (this.f9795A0 != Tab.Chats || (c1954e2 = this.f9810E) == null || c1954e2.isEmpty()) {
            return;
        }
        if (!this.f9803C0) {
            this.f9803C0 = true;
            m11528Z6();
        }
        if (z8) {
            this.f9946o.requestFocus();
        }
        this.f9946o.post(new Runnable() { // from class: y2.u1
            @Override // java.lang.Runnable
            public final void run() {
                this.f22441b.m11072D9();
            }
        });
        if (!z8 || view == null) {
            return;
        }
        view.requestFocus();
    }

    /* renamed from: u2 */
    public final void m11604u2() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m11582n7().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int iM11446C7 = m11446C7(this.f9918h, R.dimen.Chat_dialog_title_width_back);
        int iM11446C72 = m11446C7(this.f9814F, R.dimen.Chat_dialog_title_width_badge, R.dimen.Chat_dialog_title_width_margin_left_badge, R.dimen.Chat_dialog_title_width_margin_right_badge);
        int iM11446C73 = m11446C7(this.f9930k, R.dimen.Chat_dialog_title_width_search);
        this.f9942n.setMaxWidth((((((displayMetrics.widthPixels - iM11446C7) - iM11446C72) - iM11446C73) - m11446C7(this.f9922i, R.dimen.Chat_dialog_title_width_more)) - (Math.max(m11446C7(this.f9802C, R.dimen.Chat_dialog_title_width_notification), m11446C7(this.f9811E0, R.dimen.Chat_dialog_title_width_e2eelock)) * 2)) - ((int) this.f9938m.getPaint().measureText(this.f9938m.getText().toString())));
    }

    /* renamed from: u7 */
    public final void m11605u7(final GroupAlbumObj groupAlbumObj) {
        if (this.f9899c0 == null) {
            return;
        }
        String strM7449L = Globals.m7388i0().m7449L();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("albumId", groupAlbumObj.m14675b()));
        arrayList.add(new C6301o("pageIndex", "1"));
        arrayList.add(new C6301o("pageSize", "8"));
        this.f9899c0.m15734m("media", "listMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.o
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) throws JSONException {
                this.f22378a.m11079E8(groupAlbumObj, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: ua */
    public final void m11606ua(MessageObj messageObj, String str) {
        MessageObj messageObjM11608v7;
        if ("2".equals(messageObj.m14740B()) || "3".equals(messageObj.m14740B())) {
            messageObjM11608v7 = m11608v7(messageObj.m14777o());
        } else {
            List<MessageObj> listM15182u = C2950b0.m14916o().m15182u(String.valueOf(this.f9914g.f13727n), messageObj.m14788z().getTime());
            List<MessageObj> listM15156A = C2950b0.m14916o().m15156A(String.valueOf(this.f9914g.f13727n));
            if (listM15182u == null) {
                listM15182u = new ArrayList<>();
            }
            if (listM15156A == null) {
                listM15156A = new ArrayList<>();
            }
            int size = listM15182u.size();
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    messageObjM11608v7 = null;
                    break;
                } else {
                    if (listM15182u.get(i9).m14777o().equals(messageObj.m14777o())) {
                        messageObjM11608v7 = listM15182u.get(i9);
                        break;
                    }
                    i9++;
                }
            }
            this.f9810E.m11804f0();
            this.f9810E.m11766R(listM15182u);
            this.f9810E.m11766R(this.f9815F0);
            this.f9810E.m11784X(listM15156A);
        }
        if (messageObjM11608v7 == null) {
            ULogUtility.m16676l("ChatDialogActivity", "[scrollToAndHighLightSearchMessage] can not find search message.");
            return;
        }
        if (this.f9795A0 == Tab.Chats) {
            this.f9867S0.f10224p = false;
            this.f9946o.requestFocus();
            C1954e2 c1954e2 = this.f9810E;
            int iM11823r0 = c1954e2.m11823r0(c1954e2.m11756N0(messageObjM11608v7));
            ListView listView = this.f9946o;
            listView.setSelectionFromTop(iM11823r0, listView.getHeight() / 6);
        }
        this.f9810E.m11752L0(messageObj.m14777o(), str);
    }

    /* renamed from: ub */
    public final void m11607ub() {
        runOnUiThread(new Runnable() { // from class: y2.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f22370b.m11080E9();
            }
        });
    }

    /* renamed from: v7 */
    public final MessageObj m11608v7(String str) {
        MessageObj messageObj = this.f9806D.get(str);
        if (messageObj != null || this.f9810E == null) {
            return messageObj;
        }
        for (int i9 = 0; i9 < this.f9810E.getCount(); i9++) {
            MessageObj item = this.f9810E.getItem(i9);
            if (item != null && item.m14777o() != null && item.m14777o().equals(str)) {
                this.f9806D.put(str, item);
                return item;
            }
        }
        return messageObj;
    }

    /* renamed from: va */
    public final void m11609va(MessageObj messageObj) {
        Log.v("ChatDialogActivity", "scrollToListEndIfNeed");
        if (messageObj.m14778p() == MessageObj.MessageType.Call) {
            if (TextUtils.equals(messageObj.m14747I("callerId"), String.valueOf(Globals.m7388i0().m7587o0()))) {
                m11590pb();
                this.f9835K0 = messageObj;
                return;
            }
            return;
        }
        boolean zEquals = messageObj.m14745G().equals(String.valueOf(Globals.m7388i0().m7568k1()));
        int count = this.f9946o.getCount() - 1;
        int lastVisiblePosition = this.f9946o.getLastVisiblePosition();
        if (zEquals && count == lastVisiblePosition) {
            m11590pb();
            this.f9835K0 = messageObj;
        }
    }

    /* renamed from: vb */
    public final void m11610vb(final ChatOptionDialog chatOptionDialog) {
        if (chatOptionDialog == null) {
            return;
        }
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.m0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22354b.m11088F9(chatOptionDialog);
            }
        });
    }

    /* renamed from: w7 */
    public C2027b m11611w7() {
        return this.f9981w2;
    }

    /* renamed from: wa */
    public final void m11612wa() {
        Group group = this.f9914g;
        if (group == null) {
            return;
        }
        ULogUtility.m16680p("ChatDialogActivity", "start sendDeliverReceiptMessages");
        C6196d0.m23692d().m23701k(group, new C1953e1(m7366I0()));
    }

    /* renamed from: wb */
    public final void m11613wb() {
        runOnUiThread(new Runnable() { // from class: y2.c0
            @Override // java.lang.Runnable
            public final void run() {
                this.f22201b.m11589pa();
            }
        });
    }

    /* renamed from: x7 */
    public final List<ScheduleSendObj> m11614x7(String str) throws JSONException {
        String str2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("jid", str));
        SharedPreferences sharedPreferences = getSharedPreferences("cached_schedule_send_map", 0);
        FriendsClient friendsClient = this.f9899c0;
        List<ScheduleSendObj> listM11562ha = null;
        if (friendsClient != null) {
            Pair<String, String> pairM15731j = friendsClient.m15731j("chat", "listScheduleSend", arrayList);
            String str3 = (String) pairM15731j.first;
            if (str3 == null || !str3.equals("200")) {
                Log.d("ChatDialogActivity", "statusCode = " + str3);
                sharedPreferences.edit().remove(this.f9910f).apply();
                return null;
            }
            str2 = (String) pairM15731j.second;
            if (str2 != null) {
                listM11562ha = m11562ha(C5172p.m20196r(str2));
            }
        } else {
            str2 = null;
        }
        if (listM11562ha == null || listM11562ha.isEmpty()) {
            sharedPreferences.edit().putString(this.f9910f, "").apply();
        } else {
            sharedPreferences.edit().putString(this.f9910f, str2).apply();
        }
        sharedPreferences.edit().remove(this.f9910f + "_is_schedule_send_list_update").apply();
        return listM11562ha;
    }

    /* renamed from: xa */
    public final void m11615xa(Uri uri) {
        if (uri == null) {
            return;
        }
        FileItem fileItem = null;
        if (CLUtility.m16613z1(null, uri)) {
            File file = new File(CLUtility.m16576q0(getApplicationContext(), uri));
            fileItem = new FileItem(null, file.exists() ? file.getPath() : null, uri.toString());
        }
        if (fileItem == null) {
            CLUtility.m16599w(getApplicationContext(), uri, new C1957f1(new DialogC3133q.b(this).m16411b()));
        } else {
            m11618ya(fileItem);
        }
    }

    /* renamed from: xb */
    public final void m11616xb(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        CLUtility.m16589t1(this);
        final ProgressDialog progressDialogShow = ProgressDialog.show(m11582n7(), "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("groupId", String.valueOf(this.f9914g.f13727n)));
        arrayList.add(new C6301o("groupType", "Circle"));
        arrayList.add(new C6301o("displayName", str));
        this.f9899c0.m15734m("group", "update", arrayList, new FriendsClient.InterfaceC3051i() { // from class: y2.q1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f22399a.m11104H9(progressDialogShow, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: y7 */
    public final StickerObj m11617y7(MessageObj messageObj) {
        long j9;
        try {
            j9 = messageObj.m14778p().equals(MessageObj.MessageType.StickerTypeUnknown) ? NumberUtils.toLong(messageObj.m14747I("stickerId"), -1L) : NumberUtils.toLong(messageObj.m14779q(), -1L);
        } catch (Exception unused) {
            j9 = -1;
        }
        if (j9 != -1) {
            return C2950b0.m14924w().m15278f(j9);
        }
        return null;
    }

    /* renamed from: ya */
    public final void m11618ya(FileItem fileItem) {
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
                String str = this.f9914g.f13718e;
                String strM14573Q = C2925v.m14573Q(str, fileItem);
                UploadUtils.m16965l("Upload Performance", "0.1. generateMessage();");
                MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(this.f9914g.f13727n), MessageObj.MessageType.File, strM14573Q, 0, null);
                UploadUtils.m16965l("Upload Performance", "0.1. getMessageDao().insert();");
                C2950b0.m14916o().m15157B(messageObjM14558I);
                messageObjM14558I.f12924A = false;
                messageObjM14558I.f12959z = 0;
                UploadUtils.m16965l("Upload Performance", "0.2 sendFile(MessageObj); ==== UploadMultipleChatMediaHelperQueue.addFile()");
                UploadMultipleChatMediaHelperQueue.m16892F().m16941t(this.f9914g.f13711J, str, fileItem, messageObjM14558I, this.f9910f, false);
                UploadUtils.m16965l("Upload Performance", "0.2. sendFile(MessageObj); ==== end");
                C1954e2 c1954e2 = this.f9810E;
                if (c1954e2 != null) {
                    c1954e2.m11781W(messageObjM14558I);
                    m11596rb(true, 150L);
                }
            }
            UploadUtils.m16965l("Upload Performance", "0.1 sendFile(fileItem); ==== end");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    /* renamed from: yb */
    public final void m11619yb(final ChatOptionDialog chatOptionDialog) {
        final Friend next;
        if (chatOptionDialog == null || !this.f9914g.f13716c.equals("Dual")) {
            return;
        }
        Iterator<Friend> it = this.f9927j0.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                    break;
                }
            }
        }
        if (next == null) {
            return;
        }
        m11582n7().runOnUiThread(new Runnable() { // from class: y2.z
            @Override // java.lang.Runnable
            public final void run() {
                this.f22517b.m11112I9(chatOptionDialog, next);
            }
        });
    }

    /* renamed from: z7 */
    public final void m11620z7() {
        Log.d("ChatDialogActivity", "[getTopicAsync] in");
        try {
            AsyncTaskC1946c2 asyncTaskC1946c2 = this.f9883X;
            if (asyncTaskC1946c2 != null && !asyncTaskC1946c2.isCancelled()) {
                this.f9883X.cancel(true);
            }
            AsyncTaskC1946c2 asyncTaskC1946c22 = new AsyncTaskC1946c2(this, null);
            this.f9883X = asyncTaskC1946c22;
            asyncTaskC1946c22.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        Log.d("ChatDialogActivity", "[getTopicAsync] out");
    }

    /* renamed from: za */
    public final void m11621za(Location location, List<Address> list, Uri uri, int i9, Date date) {
        String str;
        String strM14556H;
        Address address = (list == null || list.size() <= 0) ? null : list.get(0);
        Log.d("ChatDialogActivity", "location: " + location + ", address:" + address);
        String strM16576q0 = CLUtility.m16576q0(getApplicationContext(), uri);
        Log.d("ChatDialogActivity", "0.1 sendLocationMessage(ImageItem); ==== start GO!!!!!!!");
        UploadUtils.m16965l("Upload Performance", "0.1. generateLocationMessageContent();");
        if (address != null) {
            String addressLine = address.getAddressLine(0);
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();
            str = "Upload Performance";
            strM14556H = C2925v.m14556H(addressLine, "", latitude, longitude, null, 0, 0, strM16576q0, "");
        } else {
            str = "Upload Performance";
            strM14556H = C2925v.m14556H("", "", location.getLatitude(), location.getLongitude(), null, 0, 0, strM16576q0, "");
        }
        UploadUtils.m16965l(str, "0.1. generateMessage();");
        MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(this.f9914g.f13727n), MessageObj.MessageType.ShareLocation, strM14556H, i9, date);
        UploadUtils.m16965l(str, "0.1. getMessageDao().insert();");
        C2950b0.m14916o().m15157B(messageObjM14558I);
        UploadUtils.m16965l(str, "0.1 sendPhotoWithNotes(messageObj);");
        m11462Ha(messageObjM14558I, strM16576q0);
        C1954e2 c1954e2 = this.f9810E;
        if (c1954e2 != null) {
            c1954e2.m11781W(messageObjM14558I);
            m11596rb(true, 150L);
        }
    }

    /* renamed from: zb */
    public final void m11622zb() {
        C5126e c5126e;
        if (this.f9885X1 == null || (c5126e = this.f9969t2) == null) {
            return;
        }
        int iM19989b = c5126e.m19989b();
        if (iM19989b == 0) {
            this.f9885X1.setVisibility(8);
            this.f9891Z1.setVisibility(8);
            return;
        }
        if (iM19989b == -1) {
            this.f9885X1.setVisibility(8);
            this.f9891Z1.setVisibility(0);
        } else if (iM19989b <= 99) {
            this.f9885X1.setVisibility(0);
            this.f9891Z1.setVisibility(8);
            this.f9885X1.setText(String.valueOf(iM19989b));
        } else {
            this.f9885X1.setVisibility(0);
            this.f9891Z1.setVisibility(8);
            this.f9885X1.setText("N");
        }
    }
}
