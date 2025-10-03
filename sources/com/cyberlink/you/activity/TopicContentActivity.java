package com.cyberlink.you.activity;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.p033u.glide.MediaLoader;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.C2027b;
import com.cyberlink.you.activity.poll.PollVotersListActivity;
import com.cyberlink.you.bulletin.AudioItem;
import com.cyberlink.you.bulletin.C2833a;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.C2896h;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.PollOptionObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.pages.photoimport.VideoItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.LoadImageUtils;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.plus.PlusShare;
import com.rockerhieu.emojicon.emoji.Emojicon;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p042d0.C4619d;
import p056e4.C4755b;
import p115k3.C5123b;
import p116k4.C5140e0;
import p116k4.C5152i0;
import p116k4.C5168n1;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5187v0;
import p136m3.C5321e;
import p197t.C6273a;
import p201t3.C6301o;
import p209u2.AbstractC6378o;
import p209u2.AbstractRunnableC6364a;
import p209u2.C6385v;
import p218v2.DialogC6459g;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class TopicContentActivity extends BaseFragmentActivity {

    /* renamed from: F0 */
    public static final String f9221F0 = "TopicContentActivity";

    /* renamed from: G0 */
    public static final int f9222G0 = Math.round(TypedValue.applyDimension(1, 214.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: H0 */
    public static final int f9223H0 = Math.round(TypedValue.applyDimension(1, 60.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: I0 */
    public static final int f9224I0 = Math.round(TypedValue.applyDimension(1, 12.0f, Globals.m7388i0().getResources().getDisplayMetrics()));

    /* renamed from: J0 */
    public static ArrayList<C2973l0> f9225J0 = new ArrayList<>();

    /* renamed from: A */
    public TextView f9226A;

    /* renamed from: A0 */
    public FriendsClient.InterfaceC3051i f9227A0;

    /* renamed from: B */
    public ImageView f9228B;

    /* renamed from: B0 */
    public FriendsClient.InterfaceC3051i f9229B0;

    /* renamed from: C */
    public String f9230C;

    /* renamed from: D */
    public String f9232D;

    /* renamed from: D0 */
    public FriendsClient.InterfaceC3051i f9233D0;

    /* renamed from: E */
    public C2027b f9234E;

    /* renamed from: F */
    public C4619d f9236F;

    /* renamed from: H */
    public DialogC6459g f9238H;

    /* renamed from: I */
    public C3199c f9239I;

    /* renamed from: J */
    public C1773a0 f9240J;

    /* renamed from: K */
    public boolean f9241K;

    /* renamed from: L */
    public TopicObj f9242L;

    /* renamed from: M */
    public Group f9243M;

    /* renamed from: O */
    public C1801z f9245O;

    /* renamed from: Q */
    public String f9247Q;

    /* renamed from: S */
    public ImageView f9249S;

    /* renamed from: U */
    public boolean f9251U;

    /* renamed from: W */
    public ImageView f9253W;

    /* renamed from: X */
    public int f9254X;

    /* renamed from: Y */
    public RelativeLayout f9255Y;

    /* renamed from: Z */
    public TextView f9256Z;

    /* renamed from: a0 */
    public ImageView f9257a0;

    /* renamed from: c */
    public ListView f9259c;

    /* renamed from: d */
    public LinearLayout f9261d;

    /* renamed from: e */
    public List<PollOptionObj> f9263e;

    /* renamed from: f */
    public List<View> f9265f;

    /* renamed from: g */
    public C2896h f9267g;

    /* renamed from: h */
    public View f9269h;

    /* renamed from: i */
    public TextView f9271i;

    /* renamed from: j */
    public View f9273j;

    /* renamed from: k */
    public View f9275k;

    /* renamed from: m */
    public Dialog f9279m;

    /* renamed from: n */
    public ProgressDialog f9281n;

    /* renamed from: o0 */
    public FriendsClient.InterfaceC3051i f9284o0;

    /* renamed from: p */
    public ImageView f9285p;

    /* renamed from: q */
    public ImageView f9287q;

    /* renamed from: q0 */
    public AsyncTaskC1800y f9288q0;

    /* renamed from: r */
    public TextView f9289r;

    /* renamed from: r0 */
    public boolean f9290r0;

    /* renamed from: s */
    public View f9291s;

    /* renamed from: s0 */
    public DialogC3133q f9292s0;

    /* renamed from: t */
    public TextView f9293t;

    /* renamed from: t0 */
    public FriendsClient.InterfaceC3051i f9294t0;

    /* renamed from: u */
    public LinearLayout f9295u;

    /* renamed from: v */
    public LinearLayout f9297v;

    /* renamed from: v0 */
    public FriendsClient.InterfaceC3051i f9298v0;

    /* renamed from: w */
    public TextView f9299w;

    /* renamed from: x */
    public TextView f9301x;

    /* renamed from: x0 */
    public FriendsClient.InterfaceC3051i f9302x0;

    /* renamed from: y */
    public TextView f9303y;

    /* renamed from: z */
    public TextView f9305z;

    /* renamed from: l */
    public Map<Long, Boolean> f9277l = new HashMap();

    /* renamed from: o */
    public C1775b0 f9283o = new C1775b0(this, null);

    /* renamed from: G */
    public FriendsClient f9237G = null;

    /* renamed from: N */
    public C5140e0 f9244N = null;

    /* renamed from: P */
    public Timer f9246P = new Timer();

    /* renamed from: R */
    public boolean f9248R = false;

    /* renamed from: T */
    public boolean f9250T = false;

    /* renamed from: V */
    public boolean f9252V = false;

    /* renamed from: b0 */
    public C5321e.m f9258b0 = new C1786k();

    /* renamed from: c0 */
    public C3199c.b f9260c0 = new C1791p();

    /* renamed from: d0 */
    public DialogC6459g.a f9262d0 = new C1792q();

    /* renamed from: e0 */
    public View.OnClickListener f9264e0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.fi
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10449b.m10323k4(view);
        }
    };

    /* renamed from: f0 */
    public View.OnClickListener f9266f0 = new ViewOnClickListenerC1793r();

    /* renamed from: g0 */
    public View.OnClickListener f9268g0 = new ViewOnClickListenerC1795t();

    /* renamed from: h0 */
    public View.OnClickListener f9270h0 = new ViewOnClickListenerC1796u();

    /* renamed from: i0 */
    public View.OnClickListener f9272i0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.gi
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10698b.m10326l4(view);
        }
    };

    /* renamed from: j0 */
    public View.OnClickListener f9274j0 = new ViewOnClickListenerC1798w();

    /* renamed from: k0 */
    public View.OnClickListener f9276k0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.hi
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10731b.m10311g4(view);
        }
    };

    /* renamed from: l0 */
    public View.OnClickListener f9278l0 = new ViewOnClickListenerC1772a();

    /* renamed from: m0 */
    public C2027b.i f9280m0 = new C1774b();

    /* renamed from: n0 */
    public View.OnClickListener f9282n0 = new ViewOnClickListenerC1776c();

    /* renamed from: p0 */
    public View.OnClickListener f9286p0 = new ViewOnClickListenerC1778d();

    /* renamed from: u0 */
    public View.OnClickListener f9296u0 = new ViewOnClickListenerC1782g();

    /* renamed from: w0 */
    public View.OnClickListener f9300w0 = new ViewOnClickListenerC1783h();

    /* renamed from: y0 */
    public View.OnClickListener f9304y0 = new ViewOnClickListenerC1784i();

    /* renamed from: z0 */
    public View.OnClickListener f9306z0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.ii
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f10761b.m10317i4(view);
        }
    };

    /* renamed from: C0 */
    public View.OnClickListener f9231C0 = new ViewOnClickListenerC1785j();

    /* renamed from: E0 */
    public View.OnClickListener f9235E0 = new ViewOnClickListenerC1790o();

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$a */
    public class ViewOnClickListenerC1772a implements View.OnClickListener {
        public ViewOnClickListenerC1772a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalStateException {
            C2973l0 c2973l0M14725v;
            C2833a c2833a = (C2833a) view.getTag(R.id.tag_Object);
            if (c2833a == null) {
                return;
            }
            long j9 = c2833a.f12409b;
            if (j9 != -1 && (c2973l0M14725v = C2950b0.m14914m().m14725v(j9)) != null) {
                TopicContentActivity.this.f9289r.setText(CLUtility.m16531f(c2973l0M14725v.m15148t().f13203g));
            }
            TopicContentActivity.this.m10391M4();
            TopicContentActivity.this.f9285p.setVisibility(0);
            TopicContentActivity.this.f9287q.setVisibility(8);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$a0 */
    public class C1773a0 extends AbstractRunnableC6364a {

        /* renamed from: c */
        public final WeakReference<FriendsClient> f9308c;

        /* renamed from: d */
        public String f9309d;

        /* renamed from: e */
        public int f9310e = 0;

        /* renamed from: com.cyberlink.you.activity.TopicContentActivity$a0$a */
        public class a implements FriendsClient.InterfaceC3051i {

            /* renamed from: a */
            public final /* synthetic */ int f9312a;

            public a(int i9) {
                this.f9312a = i9;
            }

            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public void mo134a(String str, String str2, String str3, String str4) {
                if (str3 == null) {
                    Log.d("GetPostRunnable", "Response is null");
                    return;
                }
                if (!str3.equals("200")) {
                    Log.d("GetPostRunnable", "statusCode=" + str3);
                    return;
                }
                synchronized (this) {
                    C1773a0.m10436f(C1773a0.this);
                    C1773a0.this.m10440j(str4);
                    if (C1773a0.this.m24448b()) {
                        Log.d("GetPostRunnable", "[processPostPages] Force stop");
                    } else {
                        if (C1773a0.this.f9310e == this.f9312a) {
                            TopicContentActivity.this.m10380E4();
                        }
                    }
                }
            }
        }

        public C1773a0(String str) {
            this.f9308c = new WeakReference<>(TopicContentActivity.this.f9237G);
            this.f9309d = str == null ? "" : str;
        }

        /* renamed from: f */
        public static /* synthetic */ int m10436f(C1773a0 c1773a0) {
            int i9 = c1773a0.f9310e;
            c1773a0.f9310e = i9 + 1;
            return i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m10438i(String str, String str2, String str3, String str4, String str5) {
            if (str4 == null) {
                Log.d("GetPostRunnable", "[queryPostList] Response is null");
                return;
            }
            if (!str4.equals("200")) {
                Log.d("GetPostRunnable", "[queryPostList] statusCode=" + str4);
                return;
            }
            Log.d("GetPostRunnable", "[queryPostList] Success.");
            int iM16553k1 = CLUtility.m16553k1(str5);
            int iM16494U0 = CLUtility.m16494U0(str5);
            if (iM16553k1 == -1 || iM16494U0 == -1) {
                Log.d("GetPostRunnable", "[queryPostList] totalSize(" + iM16553k1 + ") or resultsSize(" + iM16494U0 + ") is expected value");
                return;
            }
            this.f9310e++;
            m10440j(str5);
            if (m24448b()) {
                Log.d("GetPostRunnable", "[queryPostList] Force stop");
            } else if (iM16553k1 != iM16494U0) {
                m10441k(iM16553k1, str);
            } else {
                TopicContentActivity.this.m10380E4();
            }
        }

        /* renamed from: h */
        public final FriendsClient m10439h() {
            return this.f9308c.get();
        }

        /* renamed from: j */
        public final void m10440j(String str) {
            Log.d("GetPostRunnable", "[parsePostList] in");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayM20196r = C5172p.m20196r(str);
            for (int i9 = 0; i9 < jSONArrayM20196r.length(); i9++) {
                try {
                    TopicCommentObj topicCommentObjM20182d = C5172p.m20182d(jSONArrayM20196r.getJSONObject(i9));
                    if (topicCommentObjM20182d != null) {
                        C2950b0.m14905d().m14947j(topicCommentObjM20182d, TopicCommentObj.m14027m());
                        arrayList.add(topicCommentObjM20182d);
                    }
                } catch (JSONException e9) {
                    e9.printStackTrace();
                }
            }
            Log.d("GetPostRunnable", "[parsePostList] out topic list size=" + arrayList.size());
        }

        /* renamed from: k */
        public final void m10441k(int i9, String str) {
            if (str == null || str.isEmpty()) {
                Log.d("GetPostRunnable", "There is no token.");
                return;
            }
            if (m24448b()) {
                Log.d("GetPostRunnable", "[processPostPages] Force stop");
                return;
            }
            int iM16559m = CLUtility.m16559m(i9, 20);
            for (int i10 = 2; i10 <= iM16559m; i10++) {
                if (m24448b()) {
                    Log.d("GetPostRunnable", "[processPostPages] Force stop");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", str));
                arrayList.add(new C6301o("topicId", this.f9309d));
                arrayList.add(new C6301o("pageIndex", String.valueOf(i10)));
                arrayList.add(new C6301o("pageSize", String.valueOf(20)));
                FriendsClient friendsClientM10439h = m10439h();
                if (friendsClientM10439h == null) {
                    return;
                }
                friendsClientM10439h.m15734m("groupbulletin", "listPosts", arrayList, new a(iM16559m));
            }
        }

        /* renamed from: l */
        public final void m10442l() {
            Log.d("GetPostRunnable", "[queryPostList] in");
            final String strM7449L = Globals.m7388i0().m7449L();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("topicId", this.f9309d));
            arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList.add(new C6301o("pageSize", String.valueOf(20)));
            FriendsClient friendsClientM10439h = m10439h();
            if (friendsClientM10439h == null) {
                return;
            }
            friendsClientM10439h.m15734m("groupbulletin", "listPosts", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.zi
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f12311a.m10438i(strM7449L, str, str2, str3, str4);
                }
            });
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("GetPostRunnable", "[run] in");
            if (m24448b() || this.f9309d.isEmpty()) {
                Log.d("GetPostRunnable", "[run] Don't continue.");
            } else {
                m10442l();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$b */
    public class C1774b implements C2027b.i {
        public C1774b() {
        }

        @Override // com.cyberlink.you.activity.chatdialog.C2027b.i
        /* renamed from: a */
        public void mo7011a(String str, Map<String, Object> map) throws IllegalStateException, JSONException {
            str.hashCode();
            switch (str) {
                case "sendPhoto":
                    if (map.containsKey("importImages")) {
                        ArrayList arrayList = (ArrayList) map.get("importImages");
                        if (arrayList.size() > 0) {
                            ImageItem imageItem = (ImageItem) arrayList.get(0);
                            if (imageItem.m16134g().isEmpty()) {
                                TopicContentActivity.this.m10382F4();
                            } else {
                                TopicContentActivity.this.f9234E.m12072X(imageItem.m16134g());
                            }
                            TopicContentActivity topicContentActivity = TopicContentActivity.this;
                            topicContentActivity.m10414f5(topicContentActivity.f9243M.f13721h, imageItem);
                            break;
                        }
                    }
                    break;
                case "sendVideo":
                    TopicContentActivity.this.m10382F4();
                    if (map.containsKey("videoItem")) {
                        VideoItem videoItem = (VideoItem) map.get("videoItem");
                        TopicContentActivity topicContentActivity2 = TopicContentActivity.this;
                        topicContentActivity2.m10414f5(topicContentActivity2.f9243M.f13721h, videoItem);
                        break;
                    }
                    break;
                case "sendVoice":
                    TopicContentActivity.this.m10382F4();
                    if (map.containsKey("voicePath") && map.containsKey("duration")) {
                        String str2 = (String) map.get("voicePath");
                        String str3 = (String) map.get("duration");
                        TopicContentActivity topicContentActivity3 = TopicContentActivity.this;
                        topicContentActivity3.m10414f5(topicContentActivity3.f9243M.f13721h, new AudioItem(str2, str3));
                        break;
                    }
                    break;
                case "sendText":
                    TopicContentActivity.this.m10417i3(null);
                    break;
                case "recordVoice":
                    if (TopicContentActivity.this.f9248R) {
                        TopicContentActivity.this.f9248R = false;
                        TopicContentActivity.this.m10391M4();
                        TopicContentActivity.this.m10378D4();
                    }
                    if (TopicContentActivity.this.f9267g != null) {
                        TopicContentActivity.this.f9267g.m14348N();
                        break;
                    }
                    break;
                case "sendSticker":
                    Object obj = map.get("sticer");
                    if (!(obj instanceof StickerObj)) {
                        if (obj instanceof Emojicon) {
                            TopicContentActivity.this.f9234E.m12063G((Emojicon) obj);
                            break;
                        }
                    } else {
                        TopicContentActivity.this.m10382F4();
                        C2027b c2027b = TopicContentActivity.this.f9234E;
                        Boolean bool = Boolean.FALSE;
                        c2027b.m12075a0(bool, bool, bool);
                        JSONArray jSONArray = new JSONArray();
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("type", "Sticker");
                            jSONObject.put("value", ((StickerObj) obj).m16285j());
                        } catch (JSONException e9) {
                            e9.printStackTrace();
                        }
                        jSONArray.put(jSONObject);
                        TopicContentActivity.this.m10417i3(jSONArray.toString());
                        break;
                    }
                    break;
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$b0 */
    public class C1775b0 {

        /* renamed from: a */
        public ImageView f9315a;

        /* renamed from: b */
        public ImageView f9316b;

        /* renamed from: c */
        public ImageView f9317c;

        /* renamed from: d */
        public TextView f9318d;

        /* renamed from: e */
        public TextView f9319e;

        /* renamed from: f */
        public TextView f9320f;

        public C1775b0() {
        }

        public /* synthetic */ C1775b0(TopicContentActivity topicContentActivity, C1786k c1786k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$c */
    public class ViewOnClickListenerC1776c implements View.OnClickListener {
        public ViewOnClickListenerC1776c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z8 = (TopicContentActivity.this.f9251U && TopicContentActivity.this.f9242L.m14847l() == 0) || (!TopicContentActivity.this.f9251U && TopicContentActivity.this.f9242L.m14847l() > 0);
            if (TopicContentActivity.this.f9279m == null || z8) {
                TopicContentActivity topicContentActivity = TopicContentActivity.this;
                topicContentActivity.f9279m = topicContentActivity.m10416h3(view);
            }
            TopicContentActivity topicContentActivity2 = TopicContentActivity.this;
            topicContentActivity2.f9251U = topicContentActivity2.f9242L.m14847l() > 0;
            TopicContentActivity.this.f9279m.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$c0 */
    public class RunnableC1777c0 implements Runnable {
        public RunnableC1777c0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TopicContentActivity.this.f9242L != null) {
                TopicContentActivity.this.f9242L.m14834F(0);
                C2950b0.m14906e().m14989s(TopicContentActivity.this.f9242L.m14849o(), TopicContentActivity.this.f9242L, "Unread");
            }
        }

        public /* synthetic */ RunnableC1777c0(TopicContentActivity topicContentActivity, C1786k c1786k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$d */
    public class ViewOnClickListenerC1778d implements View.OnClickListener {
        public ViewOnClickListenerC1778d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m10457c() {
            ULogUtility.m16670f(TopicContentActivity.f9221F0, "onClickLikedBtn UI thread mTopicObj :" + TopicContentActivity.this.f9242L.toString());
            TopicContentActivity.this.m10398T4();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m10458d(String str, String str2, String str3, String str4) {
            if (!TopicContentActivity.this.isFinishing() && TopicContentActivity.this.f9281n != null && TopicContentActivity.this.f9281n.isShowing()) {
                TopicContentActivity.this.f9281n.dismiss();
            }
            if (str3 == null) {
                Log.d(TopicContentActivity.f9221F0, "Response is null");
                return;
            }
            if (!str3.equals("200")) {
                Log.d(TopicContentActivity.f9221F0, "statusCode=" + str3);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str4);
                ArrayList arrayList = new ArrayList();
                TopicContentActivity.this.f9242L.m14832D(jSONObject.getInt("likeCount"));
                arrayList.add("LikeCount");
                TopicContentActivity.this.f9242L.m14829A(jSONObject.getBoolean("isLike"));
                arrayList.add("isLiked");
                C2950b0.m14906e().m14990t(TopicContentActivity.this.f9242L.m14849o(), TopicContentActivity.this.f9242L, arrayList);
                ULogUtility.m16670f(TopicContentActivity.f9221F0, "onClickLikedBtn mTopicObj :" + TopicContentActivity.this.f9242L.toString());
                TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.li
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10864b.m10457c();
                    }
                });
            } catch (JSONException e9) {
                Log.d(TopicContentActivity.f9221F0, Log.getStackTraceString(e9));
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.f9281n = ProgressDialog.show(topicContentActivity, "", topicContentActivity.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("topicId", String.valueOf(TopicContentActivity.this.f9242L.m14849o())));
            arrayList.add(new C6301o("isLike", String.valueOf(!TopicContentActivity.this.f9242L.m14853s())));
            TopicContentActivity.this.f9284o0 = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.ki
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f10832a.m10458d(str, str2, str3, str4);
                }
            };
            TopicContentActivity.this.f9237G.m15734m("groupbulletin", "likeTopic", arrayList, TopicContentActivity.this.f9284o0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$d0 */
    public class C1779d0 {

        /* renamed from: a */
        public final ImageView f9325a;

        /* renamed from: b */
        public final TextView f9326b;

        /* renamed from: c */
        public final View f9327c;

        /* renamed from: d */
        public final TextView f9328d;

        /* renamed from: e */
        public final RelativeLayout f9329e;

        /* renamed from: f */
        public final ImageView f9330f;

        public C1779d0(View view, int i9, int i10, int i11, int i12, int i13, int i14) {
            this.f9325a = (ImageView) view.findViewById(i9);
            this.f9326b = (TextView) view.findViewById(i10);
            this.f9327c = view.findViewById(i11);
            this.f9328d = (TextView) view.findViewById(i12);
            this.f9329e = (RelativeLayout) view.findViewById(i13);
            this.f9330f = (ImageView) view.findViewById(i14);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$e */
    public class AsyncTaskC1780e extends AsyncTask<Void, Void, List<TopicCommentObj>> {
        public AsyncTaskC1780e() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<TopicCommentObj> doInBackground(Void... voidArr) {
            return C2950b0.m14905d().m14953p(TopicContentActivity.this.f9242L.m14849o());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<TopicCommentObj> list) {
            TopicContentActivity.this.m10403Y4(list);
            if (TopicContentActivity.this.f9234E != null) {
                TopicContentActivity.this.f9234E.m12067S();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$f */
    public class C1781f extends AbstractC6378o<Bitmap, Void, Void> {

        /* renamed from: d */
        public final /* synthetic */ ImageView f9333d;

        public C1781f(ImageView imageView) {
            this.f9333d = imageView;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: p, reason: merged with bridge method [inline-methods] */
        public void m24503g(Bitmap bitmap) {
            Pair pairM10409d3 = TopicContentActivity.this.m10409d3(bitmap.getWidth(), bitmap.getHeight());
            this.f9333d.getLayoutParams().width = ((Integer) pairM10409d3.first).intValue() / 2;
            this.f9333d.getLayoutParams().height = ((Integer) pairM10409d3.second).intValue() / 2;
            this.f9333d.setImageBitmap(bitmap);
        }

        @Override // p209u2.AbstractC6378o
        /* renamed from: q, reason: merged with bridge method [inline-methods] */
        public void m24498l(Void r12) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$g */
    public class ViewOnClickListenerC1782g implements View.OnClickListener {
        public ViewOnClickListenerC1782g() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m10465c() {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.m10400V4(topicContentActivity.f9242L.f13103r);
            if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                return;
            }
            TopicContentActivity.this.f9281n.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m10466d(String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d(TopicContentActivity.f9221F0, "Response is null");
                if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                    return;
                }
                TopicContentActivity.this.f9281n.dismiss();
                return;
            }
            if (str3.equals("200")) {
                TopicContentActivity.this.f9242L.f13103r = !TopicContentActivity.this.f9242L.f13103r;
                C2950b0.m14906e().m14991u(String.valueOf(TopicContentActivity.this.f9242L.m14849o()), TopicContentActivity.this.f9242L);
                TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ni
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10931b.m10465c();
                    }
                });
                return;
            }
            Log.d(TopicContentActivity.f9221F0, "statusCode=" + str3);
            if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                return;
            }
            TopicContentActivity.this.f9281n.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.f9281n = ProgressDialog.show(topicContentActivity, "", topicContentActivity.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("topicId", String.valueOf(TopicContentActivity.this.f9242L.m14849o())));
            arrayList.add(new C6301o("isDisabled", String.valueOf(!TopicContentActivity.this.f9242L.f13103r)));
            TopicContentActivity.this.f9294t0 = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.mi
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f10905a.m10466d(str, str2, str3, str4);
                }
            };
            TopicContentActivity.this.f9237G.m15734m("groupbulletin", "updateNotification", arrayList, TopicContentActivity.this.f9294t0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$h */
    public class ViewOnClickListenerC1783h implements View.OnClickListener {
        public ViewOnClickListenerC1783h() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m10469c() {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.m10401W4(topicContentActivity.f9242L.f13097l);
            if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                return;
            }
            TopicContentActivity.this.f9281n.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m10470d(String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d(TopicContentActivity.f9221F0, "Response is null");
                if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                    return;
                }
                TopicContentActivity.this.f9281n.dismiss();
                return;
            }
            if (str3.equals("200")) {
                TopicContentActivity.this.f9242L.f13097l = !TopicContentActivity.this.f9242L.f13097l;
                C2950b0.m14906e().m14991u(String.valueOf(TopicContentActivity.this.f9242L.m14849o()), TopicContentActivity.this.f9242L);
                TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.pi
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11048b.m10469c();
                    }
                });
                return;
            }
            Log.d(TopicContentActivity.f9221F0, "statusCode=" + str3);
            if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                return;
            }
            TopicContentActivity.this.f9281n.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.f9281n = ProgressDialog.show(topicContentActivity, "", topicContentActivity.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("topicId", String.valueOf(TopicContentActivity.this.f9242L.m14849o())));
            arrayList.add(new C6301o("isSticky", String.valueOf(!TopicContentActivity.this.f9242L.f13097l)));
            TopicContentActivity.this.f9298v0 = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.oi
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f11018a.m10470d(str, str2, str3, str4);
                }
            };
            TopicContentActivity.this.f9237G.m15734m("groupbulletin", "stickTopic", arrayList, TopicContentActivity.this.f9298v0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$i */
    public class ViewOnClickListenerC1784i implements View.OnClickListener {
        public ViewOnClickListenerC1784i() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m10473c() {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.m10399U4(topicContentActivity.f9242L.f13100o);
            if (!TopicContentActivity.this.isFinishing() && TopicContentActivity.this.f9281n != null && TopicContentActivity.this.f9281n.isShowing()) {
                TopicContentActivity.this.f9281n.dismiss();
            }
            TopicContentActivity topicContentActivity2 = TopicContentActivity.this;
            topicContentActivity2.m10406b5(topicContentActivity2.f9242L.f13100o);
            TopicContentActivity.this.f9267g.m14342H(!TopicContentActivity.this.f9242L.f13100o);
            TopicContentActivity.this.f9267g.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m10474d(String str, String str2, String str3, String str4) {
            if (str3 == null) {
                Log.d(TopicContentActivity.f9221F0, "Response is null");
                if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                    return;
                }
                TopicContentActivity.this.f9281n.dismiss();
                return;
            }
            if (str3.equals("200")) {
                TopicContentActivity.this.f9242L.f13100o = !TopicContentActivity.this.f9242L.f13100o;
                C2950b0.m14906e().m14991u(String.valueOf(TopicContentActivity.this.f9242L.m14849o()), TopicContentActivity.this.f9242L);
                TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ri
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11151b.m10473c();
                    }
                });
                return;
            }
            Log.d(TopicContentActivity.f9221F0, "statusCode=" + str3);
            if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9281n == null || !TopicContentActivity.this.f9281n.isShowing()) {
                return;
            }
            TopicContentActivity.this.f9281n.dismiss();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TopicContentActivity.this.f9279m != null && TopicContentActivity.this.f9279m.isShowing() && TopicContentActivity.this.f9242L.m14855u()) {
                TopicContentActivity.this.f9279m.dismiss();
            }
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.f9281n = ProgressDialog.show(topicContentActivity, "", topicContentActivity.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("topicId", String.valueOf(TopicContentActivity.this.f9242L.m14849o())));
            arrayList.add(new C6301o("isClosed", String.valueOf(!TopicContentActivity.this.f9242L.f13100o)));
            TopicContentActivity.this.f9302x0 = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.qi
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f11121a.m10474d(str, str2, str3, str4);
                }
            };
            TopicContentActivity.this.f9237G.m15734m("groupbulletin", "closeTopic", arrayList, TopicContentActivity.this.f9302x0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$j */
    public class ViewOnClickListenerC1785j implements View.OnClickListener {
        public ViewOnClickListenerC1785j() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10476b(String str, String str2, String str3, String str4) {
            if (str3 != null && str3.equals("200")) {
                if (!TopicContentActivity.this.isFinishing() && TopicContentActivity.this.f9281n != null && TopicContentActivity.this.f9281n.isShowing()) {
                    TopicContentActivity.this.f9281n.dismiss();
                }
                TopicContentActivity.this.m10388J4(true);
                return;
            }
            String str5 = TopicContentActivity.f9221F0;
            StringBuilder sb = new StringBuilder();
            sb.append("statusCode=");
            if (str3 == null) {
                str3 = "null";
            }
            sb.append(str3);
            Log.d(str5, sb.toString());
            if (!TopicContentActivity.this.isFinishing() && TopicContentActivity.this.f9281n != null && TopicContentActivity.this.f9281n.isShowing()) {
                TopicContentActivity.this.f9281n.dismiss();
            }
            TopicContentActivity.this.m10388J4(false);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.f9281n = ProgressDialog.show(topicContentActivity, "", topicContentActivity.getString(R.string.loading), true);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("topicId", String.valueOf(TopicContentActivity.this.f9242L.m14849o())));
            TopicContentActivity.this.f9229B0 = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.si
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f11354a.m10476b(str, str2, str3, str4);
                }
            };
            TopicContentActivity.this.f9237G.m15734m("groupbulletin", "reportToAdmin", arrayList, TopicContentActivity.this.f9229B0);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$k */
    public class C1786k implements C5321e.m {
        public C1786k() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) throws NumberFormatException {
            String str = map.get("eventType");
            String str2 = map.get("topicId");
            String str3 = map.get("postId");
            String str4 = map.get("userId");
            boolean z8 = str4 != null && str4.equals(String.valueOf(Globals.m7388i0().m7568k1()));
            if (str2 != null && str2.equals(String.valueOf(TopicContentActivity.this.f9242L.m14849o()))) {
                str.hashCode();
                char c9 = 65535;
                switch (str.hashCode()) {
                    case -1936932574:
                        if (str.equals("bulletin.topic.created")) {
                            c9 = 0;
                            break;
                        }
                        break;
                    case -1824655482:
                        if (str.equals("bulletin.post.liked")) {
                            c9 = 1;
                            break;
                        }
                        break;
                    case -1605464499:
                        if (str.equals("bulletin.post.unliked")) {
                            c9 = 2;
                            break;
                        }
                        break;
                    case -1555824044:
                        if (str.equals("bulletin.post.updated")) {
                            c9 = 3;
                            break;
                        }
                        break;
                    case -1415024045:
                        if (str.equals("bulletin.topic.deleted")) {
                            c9 = 4;
                            break;
                        }
                        break;
                    case -1092279544:
                        if (str.equals("bulletin.poll.option.uncasted")) {
                            c9 = 5;
                            break;
                        }
                        break;
                    case -560772247:
                        if (str.equals("bulletin.topic.sticked")) {
                            c9 = 6;
                            break;
                        }
                        break;
                    case -292839295:
                        if (str.equals("bulletin.post.created")) {
                            c9 = 7;
                            break;
                        }
                        break;
                    case -277693585:
                        if (str.equals("bulletin.poll.option.casted")) {
                            c9 = '\b';
                            break;
                        }
                        break;
                    case 229069234:
                        if (str.equals("bulletin.post.deleted")) {
                            c9 = '\t';
                            break;
                        }
                        break;
                    case 1045409518:
                        if (str.equals("bulletin.topic.unliked")) {
                            c9 = '\n';
                            break;
                        }
                        break;
                    case 1095049973:
                        if (str.equals("bulletin.topic.updated")) {
                            c9 = 11;
                            break;
                        }
                        break;
                    case 1169179300:
                        if (str.equals("bulletin.topic.casted")) {
                            c9 = '\f';
                            break;
                        }
                        break;
                    case 1179217906:
                        if (str.equals("bulletin.topic.closed")) {
                            c9 = CharUtils.f19105CR;
                            break;
                        }
                        break;
                    case 1846782000:
                        if (str.equals("bulletin.topic.unsticked")) {
                            c9 = 14;
                            break;
                        }
                        break;
                    case 2078126333:
                        if (str.equals("bulletin.topic.uncasted")) {
                            c9 = 15;
                            break;
                        }
                        break;
                    case 2104043874:
                        if (str.equals("bulletin.topic.reopened")) {
                            c9 = 16;
                            break;
                        }
                        break;
                    case 2124467303:
                        if (str.equals("bulletin.topic.liked")) {
                            c9 = 17;
                            break;
                        }
                        break;
                }
                switch (c9) {
                    case 0:
                    case 7:
                        TopicContentActivity.this.m10431x3(str3);
                        C6385v.f21553a.execute(new RunnableC1777c0(TopicContentActivity.this, null));
                        break;
                    case 1:
                    case 2:
                        TopicContentActivity.this.m10433z3(map.get("postId"));
                        break;
                    case 3:
                        TopicContentActivity.this.m10370A3(str3);
                        break;
                    case 4:
                        TopicContentActivity.this.m10420l3();
                        break;
                    case 5:
                    case '\b':
                        TopicContentActivity.this.m10430w3(map.get("pollOptionId"), z8);
                        break;
                    case 6:
                        TopicContentActivity.this.m10379E3(true);
                        break;
                    case '\t':
                        TopicContentActivity.this.m10432y3(str3);
                        break;
                    case '\n':
                    case 17:
                        TopicContentActivity.this.m10377D3();
                        break;
                    case 11:
                        TopicContentActivity.this.m10381F3(str2);
                        break;
                    case '\f':
                    case 15:
                        TopicContentActivity.this.m10372B3(str2);
                        break;
                    case '\r':
                        TopicContentActivity.this.m10374C3(true);
                        break;
                    case 14:
                        TopicContentActivity.this.m10379E3(false);
                        break;
                    case 16:
                        TopicContentActivity.this.m10374C3(false);
                        break;
                }
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$l */
    public class C1787l implements C5140e0.a {
        public C1787l() {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: a */
        public void mo9127a(int i9) {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: b */
        public void mo9128b() throws IllegalStateException {
            TopicContentActivity.this.f9248R = false;
            TopicContentActivity.this.m10391M4();
            TopicContentActivity.this.m10378D4();
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: c */
        public void mo9129c(int i9) throws IllegalStateException {
            TopicContentActivity.this.f9248R = false;
            TopicContentActivity.this.m10391M4();
            TopicContentActivity.this.m10378D4();
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$m */
    public class C1788m implements C3197a.b {
        public C1788m() {
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            Log.d(TopicContentActivity.f9221F0, "download fail");
            TopicContentActivity.this.f9248R = false;
            TopicContentActivity.this.m10378D4();
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) throws IllegalStateException, IOException, SecurityException {
            Log.d(TopicContentActivity.f9221F0, "download success");
            MediaScannerConnection.scanFile(Globals.m7388i0().getApplicationContext(), new String[]{str}, null, null);
            try {
                TopicContentActivity topicContentActivity = TopicContentActivity.this;
                topicContentActivity.m10390L4(topicContentActivity.f9232D);
            } catch (IllegalArgumentException e9) {
                e9.printStackTrace();
            }
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(int i9, int i10, int i11) {
            Log.d(TopicContentActivity.f9221F0, "download progress=" + String.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$n */
    public class AsyncTaskC1789n extends AsyncTask<Void, Void, TopicObj> {
        public AsyncTaskC1789n() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public TopicObj doInBackground(Void... voidArr) {
            if (TopicContentActivity.this.f9237G != null) {
                return TopicContentActivity.this.f9237G.m15726d0(String.valueOf(TopicContentActivity.this.f9242L.m14843h()), String.valueOf(TopicContentActivity.this.f9242L.m14849o()));
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(TopicObj topicObj) throws NumberFormatException {
            TopicContentActivity.this.f9242L = topicObj;
            TopicContentActivity.this.m10383G3(TopicContentActivity.this.f9242L.m14839d());
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$o */
    public class ViewOnClickListenerC1790o implements View.OnClickListener {
        public ViewOnClickListenerC1790o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TopicContentActivity.this.m10373B4(view);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$p */
    public class C1791p implements C3199c.b {
        public C1791p() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m10482g(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            TopicContentActivity.this.m10389K4();
            if (TopicContentActivity.this.f9238H != null) {
                TopicContentActivity.this.f9238H.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                if (uploadMediaHelper.m16842f1() != null) {
                    TopicContentActivity.this.f9238H.m24764f(uploadMediaHelper.m16842f1());
                } else if (uploadMediaHelper.m16844g1() != null) {
                    TopicContentActivity.this.f9238H.m24765g(uploadMediaHelper.m16844g1().m16216e());
                } else if (uploadMediaHelper.m16836c1() != null) {
                    TopicContentActivity.this.f9238H.m24761c(R.drawable.icon_voice_default_upload);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m10483h() {
            if (TopicContentActivity.this.f9238H != null) {
                TopicContentActivity.this.f9238H.m24760b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m10484i() {
            if (TopicContentActivity.this.isFinishing() || TopicContentActivity.this.f9238H == null || !TopicContentActivity.this.f9238H.isShowing()) {
                return;
            }
            TopicContentActivity.this.f9238H.dismiss();
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            C3199c.m17015D(true);
            TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ti
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11408b.m10482g(i9, i10, uploadMediaHelper);
                }
            });
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) throws JSONException {
            if (TopicContentActivity.this.f9238H != null) {
                TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ui
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11438b.m10484i();
                    }
                });
            }
            TopicContentActivity.this.m10417i3(C5123b.m19984b(c3199c.m17042t()));
            c3199c.m17034E();
            C3199c.m17015D(false);
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.vi
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11827b.m10483h();
                }
            });
            C3199c.m17015D(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$q */
    public class C1792q implements DialogC6459g.a {
        public C1792q() {
        }

        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public void mo7918a() {
            if (TopicContentActivity.this.f9239I != null) {
                TopicContentActivity.this.f9239I.m17036d();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$r */
    public class ViewOnClickListenerC1793r implements View.OnClickListener {
        public ViewOnClickListenerC1793r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TopicContentActivity.this.f9275k.setEnabled(false);
            TopicContentActivity.this.m10376D1();
            TopicContentActivity.this.m10375C4();
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$s */
    public class C1794s extends GestureDetector.SimpleOnGestureListener {
        public C1794s() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            TopicContentActivity.this.m10389K4();
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$t */
    public class ViewOnClickListenerC1795t implements View.OnClickListener {
        public ViewOnClickListenerC1795t() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Dialog dialog = new Dialog(TopicContentActivity.this, android.R.style.Theme.Holo.Light.Dialog);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.dialog_poll_advance_setting_info);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            if (TopicContentActivity.this.f9242L.f13080A) {
                dialog.findViewById(R.id.anonymousVoteArea).setVisibility(0);
            } else {
                dialog.findViewById(R.id.namedVoteArea).setVisibility(0);
            }
            if (TopicContentActivity.this.f9242L.f13082C) {
                dialog.findViewById(R.id.prohibitVoteArea).setVisibility(0);
            }
            if (TopicContentActivity.this.f9242L.f13081B) {
                dialog.findViewById(R.id.showResultArea).setVisibility(0);
            }
            dialog.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$u */
    public class ViewOnClickListenerC1796u implements View.OnClickListener {
        public ViewOnClickListenerC1796u() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2833a c2833a = (C2833a) view.getTag(R.id.tag_Object);
            if (c2833a == null) {
                return;
            }
            long[] jArr = new long[TopicContentActivity.this.f9242L.m14839d().size()];
            Iterator<C2833a> it = TopicContentActivity.this.f9242L.m14839d().iterator();
            int i9 = 0;
            while (it.hasNext()) {
                jArr[i9] = it.next().f12409b;
                i9++;
            }
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(c2833a.f12409b);
            if (c2973l0M14725v == null) {
                return;
            }
            Intent intent = new Intent(TopicContentActivity.this, (Class<?>) ShowMediaActivity.class);
            intent.putExtra("albumId", c2973l0M14725v.m15131c());
            intent.putExtra("checkedMediaList", jArr);
            intent.putExtra("mediaId", c2973l0M14725v.m15144p());
            intent.putExtra("Group", TopicContentActivity.this.f9243M);
            intent.putExtra("bulletinMode", true);
            intent.putExtra("activityName", "BulletinContentActivity");
            TopicContentActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$v */
    public class AsyncTaskC1797v extends AsyncTask<Void, Void, ArrayList<String>> {

        /* renamed from: a */
        public final /* synthetic */ long f9350a;

        public AsyncTaskC1797v(long j9) {
            this.f9350a = j9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<String> doInBackground(Void... voidArr) {
            if (this.f9350a == -1) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(this.f9350a);
            if (c2973l0M14725v == null) {
                return null;
            }
            arrayList.add(c2973l0M14725v.m15148t().f13200d);
            arrayList.add(c2973l0M14725v.m15148t().f13201e);
            return arrayList;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ArrayList<String> arrayList) {
            String str;
            if (arrayList.isEmpty() || (str = arrayList.get(0)) == null) {
                return;
            }
            Intent intent = new Intent(TopicContentActivity.this, (Class<?>) VideoPlaybackActivity.class);
            intent.putExtra("video_playback_url", str);
            String str2 = arrayList.get(1);
            if (str2 != null && !str2.isEmpty()) {
                intent.putExtra("video_playback_path", str2);
            }
            intent.putExtra("mediaId", this.f9350a);
            intent.putExtra("bulletinMode", true);
            TopicContentActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$w */
    public class ViewOnClickListenerC1798w implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.TopicContentActivity$w$a */
        public class a extends AsyncTask<Void, Void, ArrayList<String>> {

            /* renamed from: a */
            public final /* synthetic */ long f9353a;

            public a(long j9) {
                this.f9353a = j9;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public ArrayList<String> doInBackground(Void... voidArr) {
                if (this.f9353a == -1) {
                    return null;
                }
                ArrayList<String> arrayList = new ArrayList<>();
                C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(this.f9353a);
                if (c2973l0M14725v == null) {
                    return null;
                }
                arrayList.add(c2973l0M14725v.m15148t().f13201e);
                arrayList.add(c2973l0M14725v.m15148t().f13200d);
                return arrayList;
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(ArrayList<String> arrayList) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
                if (arrayList.isEmpty()) {
                    return;
                }
                String str = arrayList.get(0);
                if (str == null || str.equals("_")) {
                    TopicContentActivity.this.m10423o3(arrayList.get(1), Long.toString(this.f9353a));
                    C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(this.f9353a);
                    c2973l0M14725v.m15148t().f13201e = TopicContentActivity.this.f9232D;
                    C2950b0.m14914m().m14698H(c2973l0M14725v.m15144p(), c2973l0M14725v, "Original");
                } else {
                    TopicContentActivity.this.m10390L4(str);
                }
                TopicContentActivity.this.f9248R = true;
                TopicContentActivity.this.m10378D4();
            }
        }

        public ViewOnClickListenerC1798w() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2833a c2833a = (C2833a) view.getTag(R.id.tag_Object);
            if (c2833a == null) {
                return;
            }
            new a(c2833a.f12409b).executeOnExecutor(C6385v.f21553a, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$x */
    public class AsyncTaskC1799x extends AsyncTask<Void, Void, Void> {
        public AsyncTaskC1799x() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m10491d() {
            TopicContentActivity.this.m10394P4(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m10492e() {
            for (int i9 = 0; i9 < TopicContentActivity.this.f9263e.size(); i9++) {
                View viewM10418j3 = TopicContentActivity.this.m10418j3(i9, (PollOptionObj) TopicContentActivity.this.f9263e.get(i9));
                TopicContentActivity.this.f9261d.addView(viewM10418j3);
                TopicContentActivity.this.f9265f.add(viewM10418j3);
            }
            new Handler().postDelayed(new Runnable() { // from class: com.cyberlink.you.activity.xi
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12258b.m10491d();
                }
            }, 500L);
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Log.d(TopicContentActivity.f9221F0, "[AsyncTaskGetPollOptions] start");
            TopicContentActivity topicContentActivity = TopicContentActivity.this;
            topicContentActivity.f9263e = topicContentActivity.f9237G.m15723b0(TopicContentActivity.this.f9242L.m14849o(), true);
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r32) {
            if (TopicContentActivity.this.f9263e != null) {
                if (TopicContentActivity.this.f9265f == null) {
                    TopicContentActivity.this.f9265f = new ArrayList(TopicContentActivity.this.f9263e.size());
                    TopicContentActivity.this.f9261d.post(new Runnable() { // from class: com.cyberlink.you.activity.wi
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f12234b.m10492e();
                        }
                    });
                }
                TopicContentActivity.this.m10412e5();
                TopicContentActivity.this.m10375C4();
                TopicContentActivity.this.m10392N4();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$y */
    public class AsyncTaskC1800y extends AsyncTask<Void, Void, Boolean> {
        public AsyncTaskC1800y() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(TopicContentActivity.this.m10411e3());
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            if (TopicContentActivity.this.f9281n != null && TopicContentActivity.this.f9281n.isShowing()) {
                TopicContentActivity.this.f9281n.dismiss();
            }
            TopicContentActivity.this.f9290r0 = false;
            if (!bool.booleanValue()) {
                TopicContentActivity.this.m10376D1();
            }
            TopicContentActivity.this.f9275k.setEnabled(false);
            TopicContentActivity.this.m10375C4();
            TopicContentActivity.this.new AsyncTaskC1799x().executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.TopicContentActivity$z */
    public class C1801z extends TimerTask {
        public C1801z() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10498b() {
            TopicContentActivity.this.f9289r.setText(TopicContentActivity.this.f9247Q);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (TopicContentActivity.this.f9244N == null) {
                cancel();
                return;
            }
            if (TopicContentActivity.this.f9244N.m20014d() != -1) {
                TopicContentActivity topicContentActivity = TopicContentActivity.this;
                topicContentActivity.f9247Q = CLUtility.m16531f(String.valueOf((topicContentActivity.f9244N.m20014d() - TopicContentActivity.this.f9244N.m20013c()) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                if (TopicContentActivity.this.f9247Q != null) {
                    TopicContentActivity.this.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.yi
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f12284b.m10498b();
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I3 */
    public /* synthetic */ void m10221I3() {
        for (int i9 = 0; i9 < this.f9263e.size(); i9++) {
            m10408c5(this.f9265f.get(i9), this.f9263e.get(i9), true);
        }
        m10394P4(true);
    }

    /* renamed from: J3 */
    public static /* synthetic */ void m10224J3(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K3 */
    public /* synthetic */ void m10228K3() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.this_poll_was_removed_or_closed));
        builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.ci
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                TopicContentActivity.m10224J3(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L3 */
    public /* synthetic */ void m10232L3(DialogInterface dialogInterface, int i9) {
        m10421m3();
    }

    /* renamed from: M3 */
    public static /* synthetic */ void m10236M3(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N3 */
    public /* synthetic */ void m10240N3(TopicCommentObj topicCommentObj) {
        m10395Q4();
        C2896h c2896h = this.f9267g;
        if (c2896h != null) {
            c2896h.m14353p(topicCommentObj);
            this.f9267g.notifyDataSetChanged();
            this.f9259c.setSelection(this.f9267g.getCount() - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O3 */
    public /* synthetic */ void m10244O3(String str, String str2, String str3, String str4) throws JSONException {
        if (!"200".equals(str3)) {
            DialogC3133q dialogC3133q = this.f9292s0;
            if (dialogC3133q != null && dialogC3133q.isShowing()) {
                this.f9292s0.dismiss();
            }
            if ("400".equals(str3)) {
                C5187v0.m20270f(R.string.this_topic_was_removed_or_closed);
                return;
            }
            return;
        }
        final TopicCommentObj topicCommentObjM20182d = C5172p.m20182d(C5172p.m20195q(str4));
        if (topicCommentObjM20182d != null) {
            TopicObj topicObj = this.f9242L;
            topicObj.f13092g++;
            topicObj.m14830B(topicCommentObjM20182d.m14030e());
            C2950b0.m14906e().m14991u(String.valueOf(this.f9242L.m14849o()), this.f9242L);
            topicCommentObjM20182d.m14043s(false);
            C2950b0.m14905d().m14947j(topicCommentObjM20182d, TopicCommentObj.m14026c());
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.th
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11406b.m10240N3(topicCommentObjM20182d);
                }
            });
        }
        m10382F4();
        DialogC3133q dialogC3133q2 = this.f9292s0;
        if (dialogC3133q2 != null && dialogC3133q2.isShowing()) {
            this.f9292s0.dismiss();
        }
        C6566a.m25162u("Usage_Data_Discuss");
    }

    /* renamed from: P3 */
    public static /* synthetic */ void m10248P3(C1779d0 c1779d0, View view) {
        c1779d0.f9325a.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q3 */
    public /* synthetic */ void m10252Q3(TopicCommentObj topicCommentObj) {
        ProgressDialog progressDialog;
        if (!isFinishing() && (progressDialog = this.f9281n) != null && progressDialog.isShowing()) {
            this.f9281n.dismiss();
        }
        m10395Q4();
        this.f9267g.m14339E(topicCommentObj);
        this.f9267g.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R3 */
    public /* synthetic */ void m10256R3(final TopicCommentObj topicCommentObj, String str, String str2, String str3, String str4) {
        ProgressDialog progressDialog;
        ProgressDialog progressDialog2;
        if (str3 == null) {
            Log.d(f9221F0, "Response is null");
            if (isFinishing() || (progressDialog2 = this.f9281n) == null || !progressDialog2.isShowing()) {
                return;
            }
            this.f9281n.dismiss();
            return;
        }
        if (str3.equals("200")) {
            C2950b0.m14905d().m14948k(String.valueOf(topicCommentObj.m14033h()));
            TopicObj topicObj = this.f9242L;
            topicObj.f13092g--;
            C2950b0.m14906e().m14991u(String.valueOf(this.f9242L.m14849o()), this.f9242L);
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.jh
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10794b.m10252Q3(topicCommentObj);
                }
            });
            return;
        }
        Log.d(f9221F0, "statusCode=" + str3);
        if (isFinishing() || (progressDialog = this.f9281n) == null || !progressDialog.isShowing()) {
            return;
        }
        this.f9281n.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S3 */
    public /* synthetic */ void m10260S3() {
        ProgressDialog progressDialog;
        if (!isFinishing() && (progressDialog = this.f9281n) != null && progressDialog.isShowing()) {
            this.f9281n.dismiss();
        }
        m10420l3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T3 */
    public /* synthetic */ void m10264T3(String str, String str2, String str3, String str4) {
        ProgressDialog progressDialog;
        ProgressDialog progressDialog2;
        if (str3 == null) {
            Log.d(f9221F0, "Response is null");
            if (isFinishing() || (progressDialog2 = this.f9281n) == null || !progressDialog2.isShowing()) {
                return;
            }
            this.f9281n.dismiss();
            return;
        }
        if (str3.equals("200")) {
            C2950b0.m14906e().m14979i(String.valueOf(this.f9242L.m14849o()));
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bi
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9757b.m10260S3();
                }
            });
            return;
        }
        Log.d(f9221F0, "statusCode=" + str3);
        if (isFinishing() || (progressDialog = this.f9281n) == null || !progressDialog.isShowing()) {
            return;
        }
        this.f9281n.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U3 */
    public /* synthetic */ void m10268U3(PollOptionObj pollOptionObj, boolean z8) {
        int size = this.f9263e.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size) {
                break;
            }
            PollOptionObj pollOptionObj2 = this.f9263e.get(i9);
            if (pollOptionObj.f13041b != pollOptionObj2.f13041b) {
                i9++;
            } else if (z8 || !m10385H3()) {
                pollOptionObj2.m14794d(pollOptionObj);
                if (z8) {
                    m10375C4();
                }
            } else {
                boolean z9 = pollOptionObj.f13046g;
                pollOptionObj2.f13045f = (pollOptionObj.f13045f + (pollOptionObj2.f13046g ? 1 : 0)) - (z9 ? 1 : 0);
            }
        }
        m10394P4(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V3 */
    public /* synthetic */ void m10272V3(TopicObj topicObj, TopicCommentObj topicCommentObj) {
        if (topicObj != null) {
            this.f9242L.m14833E(topicObj.m14847l());
            m10395Q4();
            m10402X4(this.f9242L);
        }
        C2896h c2896h = this.f9267g;
        if (c2896h != null) {
            c2896h.m14353p(topicCommentObj);
            this.f9267g.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W3 */
    public /* synthetic */ void m10276W3(TopicObj topicObj, String str) {
        this.f9242L.m14833E(topicObj.m14847l());
        m10402X4(this.f9242L);
        m10395Q4();
        C2896h c2896h = this.f9267g;
        if (c2896h != null) {
            c2896h.m14338D(Long.parseLong(str));
            this.f9267g.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X3 */
    public /* synthetic */ void m10280X3(TopicCommentObj topicCommentObj) {
        C2896h c2896h = this.f9267g;
        if (c2896h != null) {
            c2896h.m14352R(topicCommentObj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y3 */
    public /* synthetic */ void m10284Y3(TopicCommentObj topicCommentObj) {
        C2896h c2896h = this.f9267g;
        if (c2896h != null) {
            c2896h.m14351Q(topicCommentObj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z3 */
    public /* synthetic */ void m10288Z3(String str) {
        this.f9242L = C2950b0.m14906e().m14984n(Long.parseLong(str));
        m10394P4(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a4 */
    public /* synthetic */ void m10292a4(boolean z8) {
        this.f9242L.f13100o = z8;
        m10406b5(z8);
        this.f9267g.m14342H(!this.f9242L.f13100o);
        this.f9267g.notifyDataSetChanged();
        if (this.f9279m != null) {
            m10399U4(z8);
            if (this.f9279m.isShowing()) {
                this.f9279m.hide();
                this.f9279m.show();
            }
        }
        if (this.f9242L.f13106u.equals("Poll")) {
            m10393O4(false);
            this.f9275k.setEnabled(false);
            this.f9273j.callOnClick();
            this.f9253W.setVisibility(z8 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b4 */
    public /* synthetic */ void m10296b4(TopicObj topicObj) {
        TopicObj topicObj2 = this.f9242L;
        if (topicObj2 != null) {
            topicObj2.m14832D(topicObj.m14846k());
            this.f9242L.m14829A(topicObj.m14853s());
            ULogUtility.m16670f(f9221F0, "handleTopicLiked UI thread mTopicObj :" + this.f9242L.toString());
            m10398T4();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c4 */
    public /* synthetic */ void m10299c4(boolean z8) {
        this.f9242L.f13097l = z8;
        if (this.f9279m != null) {
            m10401W4(z8);
            if (this.f9279m.isShowing()) {
                this.f9279m.hide();
                this.f9279m.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d4 */
    public /* synthetic */ void m10302d4(String str) {
        this.f9242L = C2950b0.m14906e().m14984n(Long.parseLong(str));
        m10404Z4();
    }

    /* renamed from: e4 */
    public static /* synthetic */ void m10305e4(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f4 */
    public /* synthetic */ void m10308f4(DialogInterface dialogInterface, int i9) {
        CLUtility.m16477P1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g4 */
    public /* synthetic */ void m10311g4(View view) {
        onBackPressed();
    }

    /* renamed from: h4 */
    public static /* synthetic */ void m10314h4(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i4 */
    public /* synthetic */ void m10317i4(View view) {
        m10415g3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j4 */
    public /* synthetic */ void m10320j4(DialogInterface dialogInterface, int i9) {
        m10422n3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k4 */
    public /* synthetic */ void m10323k4(View view) {
        TopicObj topicObj = this.f9242L;
        if (1 < topicObj.f13086G) {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
            builderM16382a.setTitle(getString(R.string.download_latest_version_title));
            builderM16382a.setMessage(getString(R.string.poll_update_version_warning));
            builderM16382a.setPositiveButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.kh
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    TopicContentActivity.m10305e4(dialogInterface, i9);
                }
            });
            builderM16382a.setNegativeButton(getString(R.string.update_app), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.lh
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f10863b.m10308f4(dialogInterface, i9);
                }
            });
            builderM16382a.show();
            return;
        }
        if (!topicObj.f13082C) {
            m10422n3();
            return;
        }
        AlertDialog.Builder builderM16382a2 = C3123g.m16382a(this);
        builderM16382a2.setTitle(getString(R.string.vote_confirm_title));
        builderM16382a2.setMessage(getString(R.string.vote_prohibit_modify_votes_warning));
        builderM16382a2.setPositiveButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.mh
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                TopicContentActivity.m10314h4(dialogInterface, i9);
            }
        });
        builderM16382a2.setNegativeButton(getString(R.string.vote), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.oh
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f11017b.m10320j4(dialogInterface, i9);
            }
        });
        builderM16382a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l4 */
    public /* synthetic */ void m10326l4(View view) {
        C2833a c2833a = (C2833a) view.getTag(R.id.tag_Object);
        if (c2833a == null) {
            return;
        }
        new AsyncTaskC1797v(c2833a.f12409b).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m4 */
    public /* synthetic */ void m10329m4(DialogInterface dialogInterface, int i9) {
        finish();
    }

    /* renamed from: n4 */
    public static /* synthetic */ void m10332n4(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o4 */
    public /* synthetic */ boolean m10335o4(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f9236F;
        return c4619d != null && c4619d.m18406a(motionEvent);
    }

    /* renamed from: p3 */
    public static ArrayList<C2973l0> m10338p3() {
        return f9225J0;
    }

    /* renamed from: p4 */
    public static /* synthetic */ void m10339p4(View view) {
    }

    /* renamed from: q4 */
    public static /* synthetic */ int m10342q4(PollOptionObj pollOptionObj, PollOptionObj pollOptionObj2) {
        return Integer.compare(pollOptionObj2.f13045f, pollOptionObj.f13045f);
    }

    /* renamed from: r4 */
    public static /* synthetic */ int m10345r4(PollOptionObj pollOptionObj, PollOptionObj pollOptionObj2) {
        return Integer.compare(pollOptionObj.f13043d, pollOptionObj2.f13043d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s4 */
    public /* synthetic */ boolean m10348s4(MenuItem menuItem) {
        if (this.f9263e != null && this.f9254X != menuItem.getItemId()) {
            int itemId = menuItem.getItemId();
            this.f9254X = itemId;
            if (itemId != 1) {
                Collections.sort(this.f9263e, new Comparator() { // from class: com.cyberlink.you.activity.vh
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return TopicContentActivity.m10345r4((PollOptionObj) obj, (PollOptionObj) obj2);
                    }
                });
            } else {
                Collections.sort(this.f9263e, new Comparator() { // from class: com.cyberlink.you.activity.uh
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return TopicContentActivity.m10342q4((PollOptionObj) obj, (PollOptionObj) obj2);
                    }
                });
            }
        }
        m10393O4(true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t4 */
    public /* synthetic */ void m10351t4() {
        this.f9285p.setVisibility(0);
        this.f9287q.setVisibility(8);
        this.f9289r.setText(this.f9230C);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u4 */
    public /* synthetic */ void m10354u4() {
        this.f9285p.setVisibility(8);
        this.f9287q.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v4 */
    public /* synthetic */ void m10357v4() {
        this.f9234E.m12068T();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w4 */
    public /* synthetic */ void m10360w4(View view) {
        if (this.f9242L.f13100o) {
            return;
        }
        C2027b c2027b = this.f9234E;
        Boolean bool = Boolean.FALSE;
        c2027b.m12075a0(bool, bool, bool);
        CLUtility.m16606x2(this);
    }

    /* renamed from: x4 */
    public static /* synthetic */ void m10363x4(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y4 */
    public /* synthetic */ void m10366y4(boolean z8) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(z8 ? R.string.thank_you_this_topic_is_reported_to_your_group_admin : R.string.we_are_sorry_unable_to_report_this_topic_please_try_again_later));
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.di
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                TopicContentActivity.m10363x4(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z4 */
    public /* synthetic */ void m10369z4() {
        this.f9238H.show();
    }

    /* renamed from: A3 */
    public final void m10370A3(String str) {
        try {
            final TopicCommentObj topicCommentObjM14952o = C2950b0.m14905d().m14952o(Long.parseLong(str));
            if (topicCommentObjM14952o != null) {
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ph
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11046b.m10284Y3(topicCommentObjM14952o);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.d(f9221F0, Log.getStackTraceString(e9));
        }
    }

    /* renamed from: A4 */
    public final void m10371A4(View view) {
        int iIntValue = ((Integer) view.getTag()).intValue();
        TopicObj topicObj = this.f9242L;
        if (topicObj.f13100o) {
            PollOptionObj pollOptionObj = this.f9263e.get(iIntValue);
            if (this.f9242L.f13080A || pollOptionObj.f13045f <= 0) {
                return;
            }
            Intent intent = new Intent(this, (Class<?>) PollVotersListActivity.class);
            intent.putExtra("pollOptionId", pollOptionObj.f13041b);
            intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, pollOptionObj.f13044e);
            startActivity(intent);
            return;
        }
        if (topicObj.f13082C) {
            Iterator<PollOptionObj> it = this.f9263e.iterator();
            while (it.hasNext()) {
                if (it.next().f13049j > 0) {
                    ULogUtility.m16676l(f9221F0, "This poll cannot modify votes");
                    return;
                }
            }
        }
        boolean z8 = !view.isSelected();
        view.setSelected(z8);
        if (this.f9242L.f13107v) {
            PollOptionObj pollOptionObj2 = this.f9263e.get(iIntValue);
            pollOptionObj2.f13046g = z8;
            if (z8) {
                pollOptionObj2.f13045f++;
            } else {
                pollOptionObj2.f13045f--;
            }
            m10394P4(true);
        } else {
            for (int i9 = 0; i9 < this.f9263e.size(); i9++) {
                View view2 = this.f9265f.get(i9);
                PollOptionObj pollOptionObj3 = this.f9263e.get(i9);
                if (i9 == iIntValue) {
                    pollOptionObj3.f13046g = z8;
                    if (z8) {
                        pollOptionObj3.f13045f++;
                    } else {
                        pollOptionObj3.f13045f--;
                    }
                } else {
                    if (pollOptionObj3.f13046g) {
                        pollOptionObj3.f13045f--;
                    }
                    pollOptionObj3.f13046g = false;
                }
                m10408c5(view2, pollOptionObj3, true);
            }
            m10394P4(true);
        }
        this.f9275k.setEnabled(m10385H3() && m10413f3());
    }

    /* renamed from: B3 */
    public final void m10372B3(final String str) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.sh
            @Override // java.lang.Runnable
            public final void run() {
                this.f11235b.m10288Z3(str);
            }
        });
    }

    /* renamed from: B4 */
    public final void m10373B4(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        Menu menu = popupMenu.getMenu();
        menu.add(0, 0, 0, R.string.sort_by_order);
        menu.add(0, 1, 0, R.string.sort_by_vote_count);
        m10405a5(menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.cyberlink.you.activity.gh
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return this.f10697a.m10348s4(menuItem);
            }
        });
        popupMenu.show();
    }

    /* renamed from: C3 */
    public final void m10374C3(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.dh
            @Override // java.lang.Runnable
            public final void run() {
                this.f10375b.m10292a4(z8);
            }
        });
    }

    /* renamed from: C4 */
    public final void m10375C4() {
        for (PollOptionObj pollOptionObj : this.f9263e) {
            this.f9277l.put(Long.valueOf(pollOptionObj.f13041b), Boolean.valueOf(pollOptionObj.f13046g));
        }
    }

    /* renamed from: D1 */
    public final void m10376D1() {
        this.f9263e = C2950b0.m14921t().m15236q(this.f9242L.m14849o());
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ih
            @Override // java.lang.Runnable
            public final void run() {
                this.f10760b.m10221I3();
            }
        });
    }

    /* renamed from: D3 */
    public final void m10377D3() {
        try {
            final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(this.f9242L.m14849o());
            if (topicObjM14984n != null) {
                ULogUtility.m16670f(f9221F0, "handleTopicLiked topicObj :" + topicObjM14984n.toString());
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.vg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f11825b.m10296b4(topicObjM14984n);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.d(f9221F0, Log.getStackTraceString(e9));
        }
    }

    /* renamed from: D4 */
    public final void m10378D4() {
        if (this.f9248R) {
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ei
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10412b.m10354u4();
                }
            });
        } else {
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.yh
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12283b.m10351t4();
                }
            });
        }
    }

    /* renamed from: E3 */
    public final void m10379E3(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.rh
            @Override // java.lang.Runnable
            public final void run() {
                this.f11149b.m10299c4(z8);
            }
        });
    }

    /* renamed from: E4 */
    public final void m10380E4() {
        new AsyncTaskC1780e().executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: F3 */
    public final void m10381F3(final String str) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.zg
            @Override // java.lang.Runnable
            public final void run() {
                this.f12308b.m10302d4(str);
            }
        });
    }

    /* renamed from: F4 */
    public final void m10382F4() {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ah
            @Override // java.lang.Runnable
            public final void run() {
                this.f9725b.m10357v4();
            }
        });
    }

    /* renamed from: G3 */
    public final void m10383G3(List<C2833a> list) throws NumberFormatException {
        Collections.sort(list, new C2833a.a());
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bulletin_content_scrollview_content);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int dimension = (int) Globals.m7388i0().getResources().getDimension(R.dimen.bulletin_content_scrollview_item_margin_left);
        layoutParams.setMargins(dimension, 0, dimension, 0);
        f9225J0 = new ArrayList<>();
        for (C2833a c2833a : list) {
            String str = c2833a.f12408a;
            str.hashCode();
            switch (str) {
                case "Sticker":
                    View viewInflate = getLayoutInflater().inflate(R.layout.view_item_bulletin_create_topic_sticker, (ViewGroup) linearLayout, false);
                    viewInflate.findViewById(R.id.delete).setVisibility(8);
                    linearLayout.addView(viewInflate);
                    StickerObj stickerObjM15278f = C2950b0.m14924w().m15278f(c2833a.f12409b);
                    if (stickerObjM15278f == null) {
                        m10396R4();
                        break;
                    } else {
                        LoadImageUtils.m16626k(this, stickerObjM15278f, (ImageView) viewInflate.findViewById(R.id.ChatMessageContentStickerView));
                        break;
                    }
                case "Audio":
                    linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, f9223H0));
                    View viewInflate2 = getLayoutInflater().inflate(R.layout.view_item_bulletin_create_topic_audio, (ViewGroup) linearLayout, false);
                    viewInflate2.findViewById(R.id.delete).setVisibility(8);
                    linearLayout.addView(viewInflate2);
                    this.f9285p = (ImageView) viewInflate2.findViewById(R.id.ChatMessageAudioOpView);
                    this.f9287q = (ImageView) viewInflate2.findViewById(R.id.ChatMessageStopView);
                    this.f9289r = (TextView) viewInflate2.findViewById(R.id.ChatMessageAudioDuration);
                    this.f9285p.setTag(R.id.tag_Object, c2833a);
                    this.f9287q.setTag(R.id.tag_Object, c2833a);
                    this.f9287q.setOnClickListener(this.f9278l0);
                    this.f9285p.setOnClickListener(this.f9274j0);
                    String strM16531f = CLUtility.m16531f(C2950b0.m14914m().m14725v(c2833a.f12409b).m15148t().f13203g);
                    this.f9230C = strM16531f;
                    this.f9289r.setText(strM16531f);
                    break;
                case "Photo":
                    ImageView imageView = new ImageView(this);
                    C2973l0 c2973l0M14725v = C2950b0.m14914m().m14725v(c2833a.f12409b);
                    if (c2973l0M14725v == null) {
                        Log.d(f9221F0, "[initComponent] a mediaObj of TopicCommentComponent is null");
                        break;
                    } else if (list.size() == 1) {
                        m10386H4(c2833a, c2973l0M14725v, imageView, linearLayout);
                        break;
                    } else {
                        Pair<Integer, Integer> pairM10409d3 = m10409d3(c2973l0M14725v.m15151w(), c2973l0M14725v.m15141m());
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(((Integer) pairM10409d3.first).intValue(), ((Integer) pairM10409d3.second).intValue());
                        layoutParams2.setMargins(dimension, 0, dimension, 0);
                        MediaLoader.m7156o(this, imageView, c2973l0M14725v, MediaLoader.Option.ORIGINAL_PREFER_CACHE);
                        imageView.setLayoutParams(layoutParams2);
                        linearLayout.addView(imageView);
                        imageView.setTag(R.id.tag_Object, c2833a);
                        imageView.setOnClickListener(this.f9270h0);
                        f9225J0.add(c2973l0M14725v);
                        break;
                    }
                case "Video":
                    View viewInflate3 = getLayoutInflater().inflate(R.layout.view_item_bulletin_create_topic_video, (ViewGroup) linearLayout, false);
                    viewInflate3.findViewById(R.id.delete).setVisibility(8);
                    ImageView imageView2 = (ImageView) viewInflate3.findViewById(R.id.ChatMessageContentPhotoView);
                    C2973l0 c2973l0M14725v2 = C2950b0.m14914m().m14725v(c2833a.f12409b);
                    viewInflate3.setTag(R.id.tag_Object, c2833a);
                    viewInflate3.setOnClickListener(this.f9272i0);
                    MediaLoader.m7146e(this, c2973l0M14725v2, true, new C1781f(imageView2));
                    linearLayout.addView(viewInflate3);
                    break;
            }
        }
        if (list.size() > 0) {
            View viewFindViewById = findViewById(R.id.bulletin_content_area_scrollview);
            if (list.size() == 1) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
                int i9 = f9224I0;
                layoutParams3.setMargins(i9, 0, i9, 0);
            }
            viewFindViewById.setVisibility(0);
        }
    }

    /* renamed from: G4 */
    public final void m10384G4(View view, Dialog dialog) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int iM10426s3 = view != null ? m10426s3(view) : 0;
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 53;
        attributes.y = iM10426s3;
        attributes.width = displayMetrics.widthPixels;
        dialog.getWindow().setAttributes(attributes);
    }

    /* renamed from: H3 */
    public final boolean m10385H3() {
        List<PollOptionObj> list = this.f9263e;
        if (list == null) {
            return false;
        }
        for (PollOptionObj pollOptionObj : list) {
            Boolean bool = this.f9277l.get(Long.valueOf(pollOptionObj.f13041b));
            if (bool != null && bool.booleanValue() != pollOptionObj.f13046g) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: H4 */
    public final void m10386H4(C2833a c2833a, C2973l0 c2973l0, ImageView imageView, LinearLayout linearLayout) {
        MediaLoader.m7156o(this, imageView, c2973l0, MediaLoader.Option.ORIGINAL_PREFER_CACHE);
        Pair<Integer, Integer> pairM10407c3 = m10407c3(c2973l0.m15151w(), c2973l0.m15141m());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Integer) pairM10407c3.first).intValue(), ((Integer) pairM10407c3.second).intValue());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(layoutParams);
        imageView.setTag(R.id.tag_Object, c2833a);
        imageView.setOnClickListener(this.f9270h0);
        linearLayout.addView(imageView);
        f9225J0.add(c2973l0);
    }

    /* renamed from: I4 */
    public final void m10387I4() throws NumberFormatException {
        View viewInflate = getLayoutInflater().inflate(R.layout.topic_content, (ViewGroup) this.f9259c, false);
        this.f9259c.addHeaderView(viewInflate, null, false);
        m10383G3(this.f9242L.m14839d());
        View viewFindViewById = viewInflate.findViewById(R.id.textViewPollClosedWarning);
        this.f9291s = viewFindViewById;
        viewFindViewById.setEnabled(false);
        this.f9293t = (TextView) viewInflate.findViewById(R.id.textViewVoteDate);
        C5168n1.m20152i((ImageView) viewInflate.findViewById(R.id.bulletin_content_avatar), (TextView) viewInflate.findViewById(R.id.bulletin_content_text_name), String.valueOf(this.f9242L.m14841f()), this.f9237G);
        this.f9299w = (TextView) viewInflate.findViewById(R.id.bulletin_content_title_area);
        this.f9301x = (TextView) viewInflate.findViewById(R.id.bulletin_content_text_area);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.sortByButton);
        this.f9253W = imageView;
        imageView.setOnClickListener(this.f9235E0);
        m10404Z4();
        this.f9255Y = (RelativeLayout) viewInflate.findViewById(R.id.pollSettingArea);
        this.f9256Z = (TextView) viewInflate.findViewById(R.id.textViewPollSettings);
        this.f9257a0 = (ImageView) viewInflate.findViewById(R.id.pollSettingInfo);
        m10392N4();
        ((TextView) viewInflate.findViewById(R.id.bulletin_content_time_area)).setText(CLUtility.m16462L2(new Date(this.f9242L.m14840e())));
        this.f9305z = (TextView) viewInflate.findViewById(R.id.bulletin_content_text_comment_count);
        this.f9228B = (ImageView) viewInflate.findViewById(R.id.bulletin_content_like_icon);
        this.f9303y = (TextView) viewInflate.findViewById(R.id.bulletin_content_text_like_count);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.clicking_area_like);
        this.f9295u = linearLayout;
        linearLayout.setOnClickListener(this.f9286p0);
        m10395Q4();
        m10398T4();
        View viewFindViewById2 = viewInflate.findViewById(R.id.voteBtn);
        this.f9275k = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f9264e0);
        this.f9275k.setEnabled(false);
        if (this.f9242L.f13106u.equals("Poll")) {
            this.f9275k.setVisibility(0);
        }
        LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(R.id.clicking_area_comment);
        this.f9297v = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.eh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10411b.m10360w4(view);
            }
        });
        registerForContextMenu(viewInflate.findViewById(R.id.view_header));
        this.f9261d = (LinearLayout) viewInflate.findViewById(R.id.linearLayoutPollOptions);
    }

    /* renamed from: J4 */
    public final void m10388J4(final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ai
            @Override // java.lang.Runnable
            public final void run() {
                this.f9726b.m10366y4(z8);
            }
        });
    }

    /* renamed from: K4 */
    public final void m10389K4() {
        C2027b c2027b = this.f9234E;
        Boolean bool = Boolean.FALSE;
        c2027b.m12075a0(bool, bool, bool);
        CLUtility.m16589t1(this);
    }

    /* renamed from: L4 */
    public void m10390L4(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        m10391M4();
        Log.d(f9221F0, "startPlaying in!");
        C5140e0 c5140e0 = new C5140e0();
        this.f9244N = c5140e0;
        c5140e0.m20021m(new C1787l());
        this.f9244N.m20018j(str);
        C1801z c1801z = new C1801z();
        this.f9245O = c1801z;
        Timer timer = this.f9246P;
        if (timer != null) {
            timer.schedule(c1801z, 0L, 1000L);
        }
    }

    /* renamed from: M4 */
    public void m10391M4() throws IllegalStateException {
        if (this.f9244N == null) {
            return;
        }
        C1801z c1801z = this.f9245O;
        if (c1801z != null) {
            c1801z.cancel();
            this.f9245O = null;
        }
        this.f9244N.m20027s();
        this.f9244N = null;
    }

    /* renamed from: N4 */
    public final void m10392N4() {
        String string;
        String string2 = "";
        if (this.f9242L.m14855u()) {
            TopicObj topicObj = this.f9242L;
            if (!topicObj.f13107v) {
                string2 = getString(R.string.poll_single_choice_hint);
            } else if (topicObj.f13083D) {
                int i9 = topicObj.f13085F;
                if ("le".contentEquals(topicObj.f13084E)) {
                    string = getString(R.string.poll_multiple_choice_max_hint, Integer.valueOf(i9));
                } else if ("ge".contentEquals(this.f9242L.f13084E)) {
                    string = getString(R.string.poll_multiple_choice_min_hint, Integer.valueOf(i9));
                } else if ("eq".contentEquals(this.f9242L.f13084E)) {
                    string = getString(R.string.poll_multiple_choice_equal_hint, Integer.valueOf(i9));
                }
                string2 = string;
            } else {
                string2 = getString(R.string.poll_multiple_choice_max_hint, Integer.valueOf(C2950b0.m14921t().m15236q(this.f9242L.m14849o()).size()));
            }
        }
        if (C5170o0.m20170e(string2)) {
            return;
        }
        this.f9255Y.setVisibility(0);
        String str = string2 + " ..." + getString(R.string.poll_advance_setting_more);
        this.f9257a0.setOnClickListener(this.f9268g0);
        this.f9256Z.setText(str);
        this.f9256Z.setTextColor(getResources().getColor(R.color.you_color_normal_blue_text));
    }

    /* renamed from: O4 */
    public final void m10393O4(boolean z8) {
        for (int i9 = 0; i9 < this.f9263e.size(); i9++) {
            m10408c5(this.f9265f.get(i9), this.f9263e.get(i9), z8);
        }
    }

    /* renamed from: P4 */
    public final void m10394P4(boolean z8) {
        List<View> list = this.f9265f;
        if (list == null || list.size() == 0) {
            return;
        }
        float fM10428u3 = m10428u3();
        int size = this.f9263e.size();
        int i9 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            C1779d0 c1779d0 = (C1779d0) this.f9265f.get(i10).getTag();
            PollOptionObj pollOptionObj = this.f9263e.get(i10);
            if (fM10428u3 > BitmapDescriptorFactory.HUE_RED) {
                i9 = (int) ((pollOptionObj.f13045f / fM10428u3) * 100.0f);
            }
            c1779d0.f9328d.setText(String.valueOf(i9) + "% (" + m10429v3(pollOptionObj.f13045f) + ")");
            c1779d0.f9328d.setTextColor(getResources().getColor(R.color.you_color_normal_blue_text));
            c1779d0.f9325a.setSelected(pollOptionObj.f13046g);
            m10410d5(c1779d0.f9327c, i9, true, z8);
        }
    }

    /* renamed from: Q4 */
    public final void m10395Q4() {
        int iM14847l = this.f9242L.m14847l();
        m10397S4(this.f9305z, iM14847l);
        this.f9305z.setText(Integer.toString(iM14847l));
    }

    /* renamed from: R4 */
    public final void m10396R4() {
        new AsyncTaskC1789n().executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: S4 */
    public final void m10397S4(View view, int i9) {
        if (i9 > 0) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    /* renamed from: T4 */
    public final void m10398T4() {
        int iM14846k = this.f9242L.m14846k();
        m10397S4(this.f9303y, iM14846k);
        this.f9303y.setText(Integer.toString(iM14846k));
        int i9 = this.f9242L.m14853s() ? R.drawable.icons_heart_large_s : R.drawable.icons_heart_large_n;
        int i10 = this.f9242L.m14853s() ? R.color.you_color_normal_blue_text : R.color.text_normal;
        this.f9228B.setImageDrawable(getResources().getDrawable(i9));
        this.f9303y.setTextColor(getResources().getColor(i10));
    }

    /* renamed from: U4 */
    public final void m10399U4(boolean z8) {
        if (this.f9283o.f9317c == null || this.f9283o.f9320f == null) {
            return;
        }
        if (z8) {
            this.f9283o.f9317c.setImageDrawable(getResources().getDrawable(R.drawable.bulletin_icons_reopen));
            this.f9283o.f9320f.setText(R.string.bulletin_more_dialog_reopen);
        } else {
            int i9 = this.f9242L.m14857w() ? R.string.bulletin_more_dialog_close_topic : R.string.close_the_poll;
            this.f9283o.f9317c.setImageDrawable(getResources().getDrawable(R.drawable.bulletin_icons_close_topic));
            this.f9283o.f9320f.setText(i9);
        }
    }

    /* renamed from: V4 */
    public final void m10400V4(boolean z8) {
        if (this.f9283o.f9315a == null || this.f9283o.f9318d == null) {
            return;
        }
        if (z8) {
            this.f9283o.f9315a.setImageDrawable(getResources().getDrawable(R.drawable.bulletin_icons_get_notifications));
            this.f9283o.f9318d.setText(R.string.bulletin_more_dialog_get_notifications);
            this.f9249S.setVisibility(0);
        } else {
            this.f9283o.f9315a.setImageDrawable(getResources().getDrawable(R.drawable.bulletin_icons_stop_notifications));
            this.f9283o.f9318d.setText(R.string.bulletin_more_dialog_stop_notifications);
            this.f9249S.setVisibility(8);
        }
    }

    /* renamed from: W4 */
    public final void m10401W4(boolean z8) {
        if (this.f9283o.f9316b == null || this.f9283o.f9319e == null) {
            return;
        }
        if (z8) {
            this.f9283o.f9316b.setImageDrawable(getResources().getDrawable(R.drawable.bulletin_icons_unstick));
            this.f9283o.f9319e.setText(R.string.bulletin_more_dialog_unstick);
        } else {
            this.f9283o.f9316b.setImageDrawable(getResources().getDrawable(R.drawable.bulletin_icons_stick_on_top));
            this.f9283o.f9319e.setText(R.string.bulletin_more_dialog_stick_on_top);
        }
    }

    /* renamed from: X4 */
    public final void m10402X4(TopicObj topicObj) {
        if (topicObj.m14855u()) {
            if (!"Community".equals(this.f9243M.f13705D) || this.f9243M.f13704C || (this.f9250T && topicObj.m14847l() <= 0)) {
                findViewById(R.id.BulletinContentMoreBtn).setVisibility(0);
            } else {
                findViewById(R.id.BulletinContentMoreBtn).setVisibility(4);
            }
        }
    }

    /* renamed from: Y4 */
    public final void m10403Y4(List<TopicCommentObj> list) {
        this.f9267g.m14343I(list);
        this.f9267g.notifyDataSetChanged();
    }

    /* renamed from: Z4 */
    public final void m10404Z4() {
        TopicObj topicObj = this.f9242L;
        if (topicObj != null) {
            if (topicObj.m14855u()) {
                this.f9299w.setVisibility(8);
            } else {
                this.f9299w.setVisibility(0);
                this.f9299w.setText(this.f9242L.m14848n());
            }
            if (this.f9242L.m14842g().isEmpty()) {
                this.f9301x.setVisibility(8);
                return;
            }
            this.f9301x.setVisibility(0);
            this.f9301x.setText(this.f9242L.m14842g());
            CLUtility.m16543i(this.f9301x);
        }
    }

    /* renamed from: a5 */
    public final void m10405a5(Menu menu) {
        for (int i9 = 0; i9 < menu.size(); i9++) {
            MenuItem item = menu.getItem(i9);
            SpannableString spannableString = new SpannableString(item.getTitle());
            if (i9 == this.f9254X) {
                spannableString.setSpan(new ForegroundColorSpan(C6273a.m24024c(this, R.color.you_color_normal_blue)), 0, spannableString.length(), 0);
            } else {
                spannableString.setSpan(new ForegroundColorSpan(-16777216), 0, spannableString.length(), 0);
            }
            item.setTitle(spannableString);
        }
    }

    /* renamed from: b5 */
    public final void m10406b5(boolean z8) {
        if (z8) {
            if (this.f9242L.f13106u.equals("Poll")) {
                this.f9275k.setVisibility(8);
                this.f9291s.setVisibility(0);
                this.f9253W.setVisibility(0);
            }
            this.f9226A.setVisibility(0);
            this.f9295u.setClickable(false);
            return;
        }
        if (this.f9242L.f13106u.equals("Poll")) {
            this.f9291s.setVisibility(8);
            this.f9275k.setVisibility(0);
            this.f9254X = 0;
        }
        this.f9226A.setVisibility(4);
        this.f9295u.setClickable(true);
    }

    /* renamed from: c3 */
    public final Pair<Integer, Integer> m10407c3(int i9, int i10) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i11 = displayMetrics.widthPixels - (f9224I0 * 2);
        return Pair.create(Integer.valueOf(i11), Integer.valueOf(Math.round(i10 * (i11 / i9))));
    }

    /* renamed from: c5 */
    public final void m10408c5(View view, PollOptionObj pollOptionObj, boolean z8) {
        C1779d0 c1779d0 = (C1779d0) view.getTag();
        if (c1779d0 == null) {
            return;
        }
        float fM10428u3 = m10428u3();
        int i9 = fM10428u3 > BitmapDescriptorFactory.HUE_RED ? (int) ((pollOptionObj.f13045f / fM10428u3) * 100.0f) : 0;
        c1779d0.f9326b.setText(pollOptionObj.f13044e);
        TextView textView = c1779d0.f9326b;
        textView.setTypeface(textView.getTypeface(), 1);
        CLUtility.m16543i(c1779d0.f9326b);
        c1779d0.f9328d.setText(String.valueOf(i9) + "% (" + m10429v3(pollOptionObj.f13045f) + ")");
        c1779d0.f9328d.setTextColor(getResources().getColor(R.color.you_color_normal_blue_text));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) c1779d0.f9326b.getLayoutParams();
        if (this.f9242L.f13100o) {
            c1779d0.f9325a.setEnabled(false);
            if (this.f9242L.f13081B) {
                layoutParams.removeRule(13);
            }
        } else {
            c1779d0.f9325a.setEnabled(true);
            c1779d0.f9325a.setSelected(pollOptionObj.f13046g);
            if (this.f9242L.f13081B) {
                layoutParams.addRule(13, -1);
            }
        }
        if (this.f9242L.f13081B) {
            c1779d0.f9326b.setLayoutParams(layoutParams);
            c1779d0.f9325a.setVisibility(this.f9242L.f13100o ? 8 : 0);
            c1779d0.f9327c.setVisibility(this.f9242L.f13100o ? 0 : 8);
            c1779d0.f9328d.setVisibility(this.f9242L.f13100o ? 0 : 8);
        }
        TopicObj topicObj = this.f9242L;
        if (!topicObj.f13100o || topicObj.f13080A) {
            c1779d0.f9330f.setVisibility(8);
        } else if (pollOptionObj.f13045f > 0) {
            c1779d0.f9330f.setVisibility(0);
        } else {
            c1779d0.f9330f.setVisibility(4);
        }
        m10410d5(c1779d0.f9327c, i9, true, z8);
    }

    /* renamed from: d3 */
    public final Pair<Integer, Integer> m10409d3(int i9, int i10) {
        int i11 = f9222G0;
        return Pair.create(Integer.valueOf(Math.round(i9 * (i11 / i10))), Integer.valueOf(i11));
    }

    /* renamed from: d5 */
    public final void m10410d5(View view, int i9, boolean z8, boolean z9) {
        Animation animation = view.getAnimation();
        boolean z10 = (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
        if (z8 && !z9 && z10) {
            Log.d(f9221F0, "[updateViewVoteProgress] animation aborted");
        } else if (z8) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(view, "progress", i9);
            objectAnimatorOfInt.setDuration(1000L);
            objectAnimatorOfInt.setInterpolator(new DecelerateInterpolator());
            objectAnimatorOfInt.start();
        }
    }

    /* renamed from: e3 */
    public final boolean m10411e3() {
        int size = this.f9263e.size();
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < size; i9++) {
            arrayList.add(new C6301o(((C1779d0) this.f9265f.get(i9).getTag()).f9325a.isSelected() ? "votedOptions" : "unVotedOptions", String.valueOf(this.f9263e.get(i9).f13041b)));
        }
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("topicId", String.valueOf(this.f9242L.m14849o())));
        arrayList.add(new C6301o("pollVersion", String.valueOf(1)));
        String str = (String) this.f9237G.m15731j("groupbulletin", "castVoteV2", arrayList).first;
        if (str == null) {
            Log.d(f9221F0, "Response is null");
            return false;
        }
        if (str.equals("403")) {
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.zh
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12310b.m10228K3();
                }
            });
            return false;
        }
        if (str.equals("200")) {
            return true;
        }
        Log.d(f9221F0, "statusCode=" + str);
        return false;
    }

    /* renamed from: e5 */
    public final void m10412e5() {
        boolean z8 = false;
        long j9 = 0;
        for (int i9 = 0; i9 < this.f9263e.size(); i9++) {
            if (this.f9263e.get(i9).f13046g) {
                z8 = true;
            }
            if (this.f9263e.get(i9).f13049j > j9) {
                j9 = this.f9263e.get(i9).f13049j;
            }
        }
        ULogUtility.m16670f(f9221F0, "isvoted = " + String.valueOf(z8) + ", lastVotedTime = " + String.valueOf(j9) + "(" + CLUtility.m16458K2(new Date(j9)) + ")");
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.user_voted));
        sb.append(StringUtils.SPACE);
        sb.append(CLUtility.m16458K2(new Date(j9)));
        this.f9293t.setText(sb.toString());
        if (!z8 || j9 <= 0) {
            this.f9293t.setVisibility(8);
        } else {
            this.f9293t.setVisibility(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* renamed from: f3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m10413f3() {
        Iterator<PollOptionObj> it = this.f9263e.iterator();
        boolean z8 = false;
        int i9 = 0;
        while (it.hasNext()) {
            if (it.next().f13046g) {
                i9++;
            }
        }
        if (i9 == 0) {
            return false;
        }
        TopicObj topicObj = this.f9242L;
        if (!topicObj.f13083D) {
            return true;
        }
        int i10 = topicObj.f13085F;
        if ("le".contentEquals(topicObj.f13084E)) {
            if (i9 <= i10) {
                z8 = true;
            }
        } else if ("ge".contentEquals(this.f9242L.f13084E)) {
            if (i9 >= i10) {
            }
        } else {
            if (!"eq".contentEquals(this.f9242L.f13084E)) {
                return true;
            }
            if (i9 == i10) {
            }
        }
        return z8;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d7  */
    /* renamed from: f5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m10414f5(String str, Object obj) {
        ImageItem imageItem;
        JSONException e9;
        ImageItem imageItem2;
        if (str == null || str.isEmpty() || obj == null) {
            return;
        }
        if (this.f9239I == null) {
            C3199c c3199c = new C3199c();
            this.f9239I = c3199c;
            c3199c.m17033C(this.f9260c0);
        }
        if (obj instanceof ImageItem) {
            ImageItem imageItem3 = (ImageItem) obj;
            if (TextUtils.isEmpty(imageItem3.m16139l()) && TextUtils.isEmpty(imageItem3.m16140m())) {
                imageItem2 = imageItem3;
            } else {
                try {
                    imageItem = new ImageItem(new JSONObject(imageItem3.m16127H()));
                    try {
                        imageItem.m16122C(CLUtility.m16540h0());
                        imageItem.m16124E(imageItem3.m16139l());
                        imageItem.m16125F(imageItem3.m16140m());
                    } catch (JSONException e10) {
                        e9 = e10;
                        Log.d(f9221F0, Log.getStackTraceString(e9));
                        imageItem2 = imageItem;
                        if (TextUtils.isEmpty(imageItem2.m16134g())) {
                        }
                        if (!this.f9238H.isShowing()) {
                        }
                        this.f9239I.m17044v();
                        this.f9239I.m17035F();
                    }
                } catch (JSONException e11) {
                    imageItem = imageItem3;
                    e9 = e11;
                }
                imageItem2 = imageItem;
            }
            if (TextUtils.isEmpty(imageItem2.m16134g())) {
                this.f9239I.m17039q(str, imageItem2, null, null, imageItem2.m16134g(), "0");
            } else if (imageItem2.m16129b() == null || imageItem2.m16129b().isEmpty()) {
                this.f9239I.m17039q(str, imageItem2, null, null, null, "0");
            } else {
                this.f9239I.m17039q(str, imageItem2, CLUtility.m16497V0(this).f13787l, imageItem2.m16129b(), null, imageItem2.m16128a());
            }
        } else if (obj instanceof AudioItem) {
            this.f9239I.m17037o(str, (AudioItem) obj);
        } else if (obj instanceof VideoItem) {
            this.f9239I.m17041s(str, (VideoItem) obj);
        }
        if (!this.f9238H.isShowing()) {
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.yg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f12282b.m10369z4();
                }
            });
        }
        this.f9239I.m17044v();
        this.f9239I.m17035F();
    }

    @Override // android.app.Activity
    public void finish() {
        CLUtility.m16589t1(this);
        Intent intent = new Intent();
        intent.putExtra("intent_topic", this.f9242L);
        intent.putExtra("delete", this.f9252V);
        setResult(-1, intent);
        super.finish();
    }

    /* renamed from: g3 */
    public final void m10415g3() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(getString(R.string.delete_btn));
        builderM16382a.setMessage(getString(R.string.are_you_sure_you_want_to_delete_this_topic));
        builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.ji
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10796b.m10232L3(dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(getString(R.string.cancel_text), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.sg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                TopicContentActivity.m10236M3(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* renamed from: h3 */
    public final Dialog m10416h3(View view) {
        Dialog dialog = new Dialog(this, R.style.FriendSelectorDialog);
        String str = this.f9243M.f13705D;
        if (str == null || str.equals("Small") || (this.f9243M.f13705D.equals("Community") && (this.f9243M.f13704C || (this.f9250T && this.f9242L.m14847l() == 0)))) {
            if (this.f9242L.f13106u.equals("Topic")) {
                if ("Community".equals(this.f9243M.f13705D) && !this.f9243M.f13704C && this.f9250T) {
                    dialog.setContentView(R.layout.dialog_topic_options_creator);
                    dialog.findViewById(R.id.clicking_area_report_to_admin).setOnClickListener(this.f9231C0);
                } else {
                    dialog.setContentView(R.layout.dialog_topic_options_admin);
                    dialog.findViewById(R.id.clicking_area_stick_unstick).setOnClickListener(this.f9300w0);
                }
                dialog.findViewById(R.id.enable_disable_notification_area).setOnClickListener(this.f9296u0);
            } else {
                dialog.setContentView(R.layout.dialog_poll_options_admin);
            }
            dialog.findViewById(R.id.clicking_area_close_reopen_topic).setOnClickListener(this.f9304y0);
            dialog.findViewById(R.id.deleteTopicArea).setOnClickListener(this.f9306z0);
            this.f9283o.f9315a = (ImageView) dialog.findViewById(R.id.enable_disable_NotificationImage);
            this.f9283o.f9316b = (ImageView) dialog.findViewById(R.id.image_stick_unstick);
            this.f9283o.f9317c = (ImageView) dialog.findViewById(R.id.image_close_reopen_topic);
            this.f9283o.f9318d = (TextView) dialog.findViewById(R.id.enable_disable_NotificationText);
            this.f9283o.f9319e = (TextView) dialog.findViewById(R.id.text_stick_unstick);
            this.f9283o.f9320f = (TextView) dialog.findViewById(R.id.text_close_reopen_topic);
            m10400V4(this.f9242L.f13103r);
            m10401W4(this.f9242L.m14856v());
            m10399U4(this.f9242L.m14851q());
        } else {
            dialog.setContentView(R.layout.dialog_topic_options_general);
            dialog.findViewById(R.id.enable_disable_notification_area).setOnClickListener(this.f9296u0);
            dialog.findViewById(R.id.clicking_area_report_to_admin).setOnClickListener(this.f9231C0);
            this.f9283o.f9315a = (ImageView) dialog.findViewById(R.id.enable_disable_NotificationImage);
            this.f9283o.f9318d = (TextView) dialog.findViewById(R.id.enable_disable_NotificationText);
            m10400V4(this.f9242L.f13103r);
        }
        m10384G4(view, dialog);
        return dialog;
    }

    /* renamed from: i3 */
    public final void m10417i3(String str) {
        DialogC6459g dialogC6459g = this.f9238H;
        if (dialogC6459g == null || !dialogC6459g.isShowing()) {
            this.f9292s0 = new DialogC3133q.b(this).m16411b();
        }
        String strM10424q3 = m10424q3();
        if ((strM10424q3 == null || strM10424q3.isEmpty()) && str == null) {
            Log.d(f9221F0, "No comment data");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("topicId", String.valueOf(this.f9242L.m14849o())));
        arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, strM10424q3));
        if (str != null) {
            arrayList.add(new C6301o("components", str));
        }
        this.f9237G.m15734m("groupbulletin", "createPost", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.wg
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) throws JSONException {
                this.f12232a.m10244O3(str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: j3 */
    public final View m10418j3(int i9, PollOptionObj pollOptionObj) {
        View viewInflate = getLayoutInflater().inflate(R.layout.polloptions_list_item, this.f9261d, false);
        final C1779d0 c1779d0 = new C1779d0(viewInflate, R.id.imageViewVote, R.id.textViewDescription, R.id.viewVoteProgress, R.id.textViewVotesCount, R.id.pollOptionLayout, R.id.arrowIcon);
        viewInflate.setTag(c1779d0);
        c1779d0.f9325a.setTag(Integer.valueOf(i9));
        m10408c5(viewInflate, pollOptionObj, true);
        c1779d0.f9325a.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.wh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f12233b.m10371A4(view);
            }
        });
        c1779d0.f9329e.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.xh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TopicContentActivity.m10248P3(c1779d0, view);
            }
        });
        TopicObj topicObj = this.f9242L;
        if (!topicObj.f13100o || topicObj.f13080A) {
            c1779d0.f9330f.setVisibility(8);
        } else if (pollOptionObj.f13045f > 0) {
            c1779d0.f9330f.setVisibility(0);
        } else {
            c1779d0.f9330f.setVisibility(4);
        }
        return viewInflate;
    }

    /* renamed from: k3 */
    public final void m10419k3(final TopicCommentObj topicCommentObj) {
        this.f9281n = ProgressDialog.show(this, "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("postId", String.valueOf(topicCommentObj.m14033h())));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.rg
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f11147a.m10256R3(topicCommentObj, str, str2, str3, str4);
            }
        };
        this.f9233D0 = interfaceC3051i;
        this.f9237G.m15734m("groupbulletin", "deletePost", arrayList, interfaceC3051i);
    }

    /* renamed from: l3 */
    public final void m10420l3() {
        this.f9252V = true;
        finish();
    }

    /* renamed from: m3 */
    public final void m10421m3() {
        this.f9281n = ProgressDialog.show(this, "", getString(R.string.loading), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("topicId", String.valueOf(this.f9242L.m14849o())));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.xg
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f12256a.m10264T3(str, str2, str3, str4);
            }
        };
        this.f9227A0 = interfaceC3051i;
        this.f9237G.m15734m("groupbulletin", "deleteTopic", arrayList, interfaceC3051i);
    }

    /* renamed from: n3 */
    public void m10422n3() {
        this.f9281n = ProgressDialog.show(this, "", getString(R.string.loading), true);
        if (this.f9290r0) {
            return;
        }
        this.f9290r0 = true;
        AsyncTaskC1800y asyncTaskC1800y = new AsyncTaskC1800y();
        this.f9288q0 = asyncTaskC1800y;
        asyncTaskC1800y.executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: o3 */
    public void m10423o3(String str, String str2) {
        this.f9232D = CLUtility.m16488S0(this, str2);
        new C3197a().m16996s(str, this.f9232D, new C1788m());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        this.f9234E.onActivityResult(i9, i10, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (m10385H3()) {
            C4755b.m18880c(this, R.string.abandon, R.string.bulletin_topic_ask_abandon_change, R.string.abandon, R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.tg
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f11405b.m10329m4(dialogInterface, i9);
                }
            }, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.ug
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    TopicContentActivity.m10332n4(dialogInterface, i9);
                }
            });
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        Intent intent = menuItem.getIntent();
        menuItem.setIntent(null);
        if (this.f9242L.f13100o) {
            return true;
        }
        if (menuItem.getItemId() == 0) {
            m10415g3();
            return true;
        }
        if (menuItem.getItemId() == 1) {
            TopicCommentObj topicCommentObjM14357t = this.f9267g.m14357t(intent.getLongExtra(TtmlNode.ATTR_ID, -1L));
            if (topicCommentObjM14357t != null) {
                m10419k3(topicCommentObjM14357t);
                return true;
            }
        }
        return false;
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws NumberFormatException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bulletin_content);
        View viewFindViewById = findViewById(R.id.BulletinContentBackBtn);
        this.f9269h = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f9276k0);
        View viewFindViewById2 = findViewById(R.id.cancelBtn);
        this.f9273j = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f9266f0);
        this.f9259c = (ListView) findViewById(R.id.BulletinCommentListView);
        this.f9249S = (ImageView) findViewById(R.id.NotificationDisableBtn);
        this.f9242L = (TopicObj) getIntent().getParcelableExtra("intent_topic");
        Group group = (Group) getIntent().getParcelableExtra("Group");
        this.f9243M = group;
        TopicObj topicObj = this.f9242L;
        if (topicObj == null || group == null) {
            finish();
            return;
        }
        if (topicObj.m14857w() && this.f9242L.f13103r) {
            this.f9249S.setVisibility(0);
        } else {
            this.f9249S.setVisibility(8);
        }
        this.f9250T = this.f9242L.m14841f() == Globals.m7388i0().m7568k1().longValue();
        this.f9251U = this.f9242L.m14847l() > 0;
        m10402X4(this.f9242L);
        findViewById(R.id.BulletinContentMoreBtn).setOnClickListener(this.f9282n0);
        this.f9236F = new C4619d(this, new C1794s());
        this.f9237G = new FriendsClient(true);
        this.f9271i = (TextView) findViewById(R.id.textViewTitle);
        if (this.f9242L.f13106u.equals("Poll")) {
            this.f9271i.setText(R.string.poll);
        }
        this.f9259c.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.ch
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f9788b.m10335o4(view, motionEvent);
            }
        });
        m10387I4();
        this.f9226A = (TextView) findViewById(R.id.textViewTopicClosedWarning);
        if (this.f9242L.f13106u.equals("Poll")) {
            this.f9226A.setText(R.string.the_poll_is_closed_no_reply_is_allowed);
        }
        this.f9226A.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.nh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TopicContentActivity.m10339p4(view);
            }
        });
        C2896h c2896h = new C2896h(this, 0, new ArrayList(), this.f9237G, !this.f9242L.f13100o);
        this.f9267g = c2896h;
        this.f9259c.setAdapter((ListAdapter) c2896h);
        registerForContextMenu(this.f9259c);
        if (bundle != null || this.f9243M == null) {
            C2027b c2027b = (C2027b) getSupportFragmentManager().mo1848e("MessageInput");
            this.f9234E = c2027b;
            c2027b.m12069U(this.f9280m0);
        } else {
            this.f9234E = new C2027b();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("enableChatPlus", true);
            bundle2.putBoolean("enableChatMeeting", false);
            bundle2.putParcelable("Group", this.f9243M);
            bundle2.putBoolean("enableExtraOp", false);
            bundle2.putBoolean("hideVoice4ImportPhoto", true);
            bundle2.putBoolean("singleSelect4ImportPhoto", true);
            this.f9234E.setArguments(bundle2);
            this.f9234E.m12069U(this.f9280m0);
            getSupportFragmentManager().mo1844a().m1981c(R.id.menuFragmentContainer, this.f9234E, "MessageInput").mo1802r(this.f9234E).mo1794h();
        }
        m10406b5(this.f9242L.f13100o);
        DialogC6459g dialogC6459g = new DialogC6459g(this, R.style.FriendSelectorDialog);
        this.f9238H = dialogC6459g;
        dialogC6459g.m24770m(this.f9262d0);
        C6385v.f21553a.execute(new RunnableC1777c0(this, null));
        C5321e.m20824o().m20875k(this.f9258b0);
        m10380E4();
        m10425r3();
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (view instanceof ListView) {
            int i9 = ((AdapterView.AdapterContextMenuInfo) contextMenuInfo).position;
            if (i9 == 0) {
                String strValueOf = String.valueOf(this.f9242L.m14841f());
                String str = this.f9243M.f13705D;
                if (str == null || str.equals("Small") || this.f9243M.f13704C || Globals.m7388i0().m7474Q1(strValueOf)) {
                    TopicObj topicObj = this.f9242L;
                    if (topicObj.f13092g == 0 && !topicObj.f13100o) {
                        contextMenu.add(0, 0, 0, getResources().getString(R.string.delete_btn));
                    }
                }
            } else {
                Intent intent = new Intent();
                TopicCommentObj topicCommentObj = (TopicCommentObj) this.f9267g.getItem(i9 - 1);
                String strValueOf2 = String.valueOf(topicCommentObj.m14031f());
                String str2 = this.f9243M.f13705D;
                if ((str2 == null || str2.equals("Small") || Globals.m7388i0().m7474Q1(strValueOf2) || this.f9243M.f13704C) && !this.f9242L.f13100o) {
                    MenuItem menuItemAdd = contextMenu.add(0, 1, 0, getResources().getString(R.string.delete_btn));
                    intent.putExtra(TtmlNode.ATTR_ID, topicCommentObj.m14033h());
                    menuItemAdd.setIntent(intent);
                }
            }
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ProgressDialog progressDialog = this.f9281n;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.f9281n.dismiss();
            this.f9281n = null;
        }
        Dialog dialog = this.f9279m;
        if (dialog != null && dialog.isShowing()) {
            this.f9279m.dismiss();
            this.f9279m = null;
        }
        C5152i0.m20065b(this.f9238H);
        C1773a0 c1773a0 = this.f9240J;
        if (c1773a0 != null) {
            c1773a0.m24447a();
            this.f9240J = null;
        }
        FriendsClient friendsClient = this.f9237G;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        TopicObj topicObj = this.f9242L;
        if (topicObj != null) {
            C5123b.m19987e(topicObj);
        }
        C5321e.m20824o().m20832B0(this.f9258b0);
        super.onDestroy();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() throws IllegalStateException {
        super.onPause();
        C2907m0.m14454I().m14477A(this.f9242L.m14849o());
        if (this.f9248R) {
            this.f9248R = false;
            m10391M4();
            m10378D4();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        if (this.f9242L.m14855u() && this.f9263e == null && !this.f9241K) {
            this.f9241K = true;
            new AsyncTaskC1799x().executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: q3 */
    public final String m10424q3() {
        return this.f9234E.m12084z();
    }

    /* renamed from: r3 */
    public final void m10425r3() {
        Log.d(f9221F0, "[getTopicAsync] in");
        try {
            C1773a0 c1773a0 = this.f9240J;
            if (c1773a0 != null) {
                c1773a0.m24447a();
            }
            C1773a0 c1773a02 = new C1773a0(String.valueOf(this.f9242L.m14849o()));
            this.f9240J = c1773a02;
            C6385v.m24526d(c1773a02);
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        Log.d(f9221F0, "[getTopicAsync] out");
    }

    /* renamed from: s3 */
    public final int m10426s3(View view) {
        if (view == null) {
            return 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return ((iArr[1] - CLUtility.m16533f1(this)) + view.getHeight()) - ((int) (displayMetrics.density * 8.0f));
    }

    /* renamed from: t3 */
    public C2027b m10427t3() {
        return this.f9234E;
    }

    /* renamed from: u3 */
    public final int m10428u3() {
        TopicObj topicObj = this.f9242L;
        int i9 = 0;
        if (!topicObj.f13107v) {
            List<PollOptionObj> list = this.f9263e;
            if (list == null) {
                return 0;
            }
            Iterator<PollOptionObj> it = list.iterator();
            while (it.hasNext()) {
                i9 += it.next().f13045f;
            }
            return i9;
        }
        boolean z8 = topicObj.f13110y;
        List<PollOptionObj> list2 = this.f9263e;
        if (list2 != null) {
            Iterator<PollOptionObj> it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (it2.next().f13046g) {
                    i9 = 1;
                    break;
                }
            }
        }
        return (i9 - (z8 ? 1 : 0)) + this.f9242L.f13109x;
    }

    /* renamed from: v3 */
    public final String m10429v3(int i9) {
        if (i9 <= 999) {
            return String.valueOf(i9);
        }
        return String.valueOf((float) (i9 / 1000.0d)) + "k";
    }

    /* renamed from: w3 */
    public final void m10430w3(String str, final boolean z8) throws NumberFormatException {
        try {
            final PollOptionObj pollOptionObjM14892e = C2950b0.m14921t().m14892e(Long.parseLong(str));
            if (pollOptionObjM14892e == null || this.f9263e == null) {
                return;
            }
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.qh
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11118b.m10268U3(pollOptionObjM14892e, z8);
                }
            });
        } catch (NumberFormatException e9) {
            Log.d(f9221F0, Log.getStackTraceString(e9));
        }
    }

    /* renamed from: x3 */
    public final void m10431x3(String str) {
        try {
            final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(this.f9242L.m14849o());
            final TopicCommentObj topicCommentObjM14952o = C2950b0.m14905d().m14952o(Long.parseLong(str));
            if (topicCommentObjM14952o != null) {
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.fh
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10446b.m10272V3(topicObjM14984n, topicCommentObjM14952o);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.d(f9221F0, Log.getStackTraceString(e9));
        }
    }

    /* renamed from: y3 */
    public final void m10432y3(final String str) {
        try {
            final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(this.f9242L.m14849o());
            if (topicObjM14984n != null) {
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.hh
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10728b.m10276W3(topicObjM14984n, str);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.d(f9221F0, Log.getStackTraceString(e9));
        }
    }

    /* renamed from: z3 */
    public final void m10433z3(String str) {
        try {
            final TopicCommentObj topicCommentObjM14952o = C2950b0.m14905d().m14952o(Long.parseLong(str));
            if (topicCommentObjM14952o != null) {
                runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bh
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f9755b.m10280X3(topicCommentObjM14952o);
                    }
                });
            }
        } catch (NumberFormatException e9) {
            Log.d(f9221F0, Log.getStackTraceString(e9));
        }
    }
}
