package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.AbstractC0372k;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.PhotoCommentFragment;
import com.cyberlink.you.activity.VoiceRecordFragment;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.bulletin.AudioItem;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2971k0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.GroupAlbumObj;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.fingerpaint.FingerPaintActivity;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.C3062b;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.C3197a;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.plus.PlusShare;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p042d0.C4619d;
import p116k4.C5140e0;
import p116k4.C5145g;
import p116k4.C5154j;
import p116k4.C5169o;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5179r0;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p137m4.AbstractC5323a;
import p137m4.C5325c;
import p173q2.C6127a;
import p174q3.C6152i;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.AbstractRunnableC6364a;
import p209u2.C6383t;
import p209u2.C6385v;
import p209u2.ThreadFactoryC6373j;
import p218v2.C6456d;
import p236x2.C6566a;

/* loaded from: classes.dex */
public class PhotoCommentFragment extends Fragment {

    /* renamed from: Y0 */
    public static String f8246Y0;

    /* renamed from: A */
    public PhotoCommentFragment f8247A;

    /* renamed from: B */
    public TextView f8249B;

    /* renamed from: C */
    public ImageView f8251C;

    /* renamed from: D */
    public ImageView f8253D;

    /* renamed from: E */
    public TextView f8255E;

    /* renamed from: F */
    public ImageView f8257F;

    /* renamed from: G */
    public UserInfo f8259G;

    /* renamed from: X */
    public C1599g0 f8293X;

    /* renamed from: b */
    public C2973l0 f8298b;

    /* renamed from: c */
    public TextView f8300c;

    /* renamed from: c0 */
    public FriendsClient f8301c0;

    /* renamed from: d */
    public ListView f8302d;

    /* renamed from: e */
    public View f8304e;

    /* renamed from: f */
    public View f8306f;

    /* renamed from: g */
    public C1597f0 f8308g;

    /* renamed from: h */
    public ImageView f8310h;

    /* renamed from: i */
    public ImageView f8312i;

    /* renamed from: j */
    public ImageView f8314j;

    /* renamed from: k */
    public TextView f8316k;

    /* renamed from: l */
    public TextView f8318l;

    /* renamed from: l0 */
    public AsyncTaskC1605j0 f8319l0;

    /* renamed from: m */
    public View f8320m;

    /* renamed from: m0 */
    public AsyncTaskC1607k0 f8321m0;

    /* renamed from: n */
    public View f8322n;

    /* renamed from: n0 */
    public TextView f8323n0;

    /* renamed from: o */
    public View f8324o;

    /* renamed from: p */
    public View f8326p;

    /* renamed from: q */
    public TextView f8328q;

    /* renamed from: r */
    public View f8330r;

    /* renamed from: s */
    public ImageView f8332s;

    /* renamed from: t */
    public TextView f8334t;

    /* renamed from: u */
    public View f8336u;

    /* renamed from: v */
    public View f8338v;

    /* renamed from: w */
    public Button f8340w;

    /* renamed from: x */
    public EditText f8342x;

    /* renamed from: y */
    public Dialog f8344y;

    /* renamed from: z */
    public VoiceRecordFragment f8346z;

    /* renamed from: H */
    public C5140e0 f8261H = null;

    /* renamed from: I */
    public boolean f8263I = false;

    /* renamed from: J */
    public boolean f8265J = false;

    /* renamed from: K */
    public boolean f8267K = false;

    /* renamed from: L */
    public String f8269L = null;

    /* renamed from: M */
    public long f8271M = -1;

    /* renamed from: N */
    public long f8273N = -1;

    /* renamed from: O */
    public String f8275O = null;

    /* renamed from: P */
    public boolean f8277P = false;

    /* renamed from: Q */
    public long f8279Q = -1;

    /* renamed from: R */
    public boolean f8281R = false;

    /* renamed from: S */
    public boolean f8283S = false;

    /* renamed from: T */
    public boolean f8285T = false;

    /* renamed from: U */
    public boolean f8287U = false;

    /* renamed from: V */
    public Handler f8289V = new Handler();

    /* renamed from: W */
    public long f8291W = -1;

    /* renamed from: Y */
    public Timer f8295Y = new Timer();

    /* renamed from: Z */
    public boolean f8296Z = false;

    /* renamed from: a0 */
    public ProgressDialog f8297a0 = null;

    /* renamed from: b0 */
    public boolean f8299b0 = false;

    /* renamed from: d0 */
    public C4619d f8303d0 = null;

    /* renamed from: e0 */
    public final ExecutorService f8305e0 = Executors.newFixedThreadPool(5, new ThreadFactoryC6373j("PhotoComment"));

    /* renamed from: f0 */
    public C1603i0 f8307f0 = null;

    /* renamed from: g0 */
    public AsyncTask f8309g0 = null;

    /* renamed from: h0 */
    public boolean f8311h0 = false;

    /* renamed from: i0 */
    public Random f8313i0 = new Random(System.currentTimeMillis());

    /* renamed from: j0 */
    public final LongSparseArray<C3061a> f8315j0 = new LongSparseArray<>();

    /* renamed from: k0 */
    public Map<String, Friend> f8317k0 = new HashMap();

    /* renamed from: o0 */
    public int f8325o0 = -1;

    /* renamed from: p0 */
    public boolean f8327p0 = false;

    /* renamed from: q0 */
    public C1601h0 f8329q0 = new C1601h0();

    /* renamed from: r0 */
    public AbstractC5323a f8331r0 = null;

    /* renamed from: s0 */
    public View.OnClickListener f8333s0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.x8
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12250b.m8913P1(view);
        }
    };

    /* renamed from: t0 */
    public View.OnClickListener f8335t0 = new ViewOnClickListenerC1618v();

    /* renamed from: u0 */
    public final View.OnClickListener f8337u0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.y8
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) throws IllegalStateException {
            this.f12273b.m8916Q1(view);
        }
    };

    /* renamed from: v0 */
    public View.OnClickListener f8339v0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.s7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            PhotoCommentFragment.m8922S1(view);
        }
    };

    /* renamed from: w0 */
    public View.OnClickListener f8341w0 = new ViewOnClickListenerC1621y();

    /* renamed from: x0 */
    public View.OnClickListener f8343x0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.t7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11392b.m8925T1(view);
        }
    };

    /* renamed from: y0 */
    public TextWatcher f8345y0 = new C1622z();

    /* renamed from: z0 */
    public View.OnClickListener f8347z0 = new ViewOnClickListenerC1587a0();

    /* renamed from: A0 */
    public View.OnClickListener f8248A0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.u7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11429b.m8928U1(view);
        }
    };

    /* renamed from: B0 */
    public View.OnClickListener f8250B0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.v7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11812b.m8931V1(view);
        }
    };

    /* renamed from: C0 */
    public View.OnClickListener f8252C0 = new View.OnClickListener() { // from class: com.cyberlink.you.activity.w7
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11843b.m8934W1(view);
        }
    };

    /* renamed from: D0 */
    public C2907m0.g f8254D0 = new C2907m0.g() { // from class: com.cyberlink.you.activity.x7
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f12249b.m9036S2();
        }
    };

    /* renamed from: E0 */
    public C2907m0.h f8256E0 = new C2907m0.h() { // from class: com.cyberlink.you.activity.y7
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f12272b.m8937X1(z8);
        }
    };

    /* renamed from: F0 */
    public View.OnClickListener f8258F0 = new ViewOnClickListenerC1591c0();

    /* renamed from: G0 */
    public VoiceRecordFragment.InterfaceC1861g f8260G0 = new C1593d0();

    /* renamed from: H0 */
    public Runnable f8262H0 = new RunnableC1588b();

    /* renamed from: I0 */
    public C5321e.m f8264I0 = new C1590c();

    /* renamed from: J0 */
    public XMPPManager.AbstractC2868s f8266J0 = new C1592d(false);

    /* renamed from: K0 */
    public C6456d.j f8268K0 = new C1594e();

    /* renamed from: L0 */
    public XMPPManager.InterfaceC2851b0 f8270L0 = new XMPPManager.InterfaceC2851b0() { // from class: com.cyberlink.you.activity.z7
        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2851b0
        /* renamed from: h0 */
        public final void mo13974h0(boolean z8) {
            this.f12297b.m8919R1(z8);
        }
    };

    /* renamed from: M0 */
    public Runnable f8272M0 = new RunnableC1596f();

    /* renamed from: N0 */
    public AbsListView.OnScrollListener f8274N0 = new C1598g();

    /* renamed from: O0 */
    public C5325c.b f8276O0 = new C1604j();

    /* renamed from: P0 */
    public final AbstractC6381r<Pair<C3061a, C3061a>, C3061a> f8278P0 = new C1609m(new Handler(Looper.getMainLooper()));

    /* renamed from: Q0 */
    public AdapterView.OnItemLongClickListener f8280Q0 = new C1612p();

    /* renamed from: R0 */
    public View.OnClickListener f8282R0 = new ViewOnClickListenerC1613q();

    /* renamed from: S0 */
    public View.OnClickListener f8284S0 = new ViewOnClickListenerC1614r();

    /* renamed from: T0 */
    public View.OnClickListener f8286T0 = new ViewOnClickListenerC1615s();

    /* renamed from: U0 */
    public View.OnClickListener f8288U0 = new ViewOnClickListenerC1616t();

    /* renamed from: V0 */
    public View.OnClickListener f8290V0 = new ViewOnClickListenerC1617u();

    /* renamed from: W0 */
    public View.OnClickListener f8292W0 = new ViewOnClickListenerC1619w();

    /* renamed from: X0 */
    public CommentStatus f8294X0 = CommentStatus.NEW;

    public enum CommentStatus {
        NEW,
        EDIT;

        private C3061a editObj = null;

        CommentStatus() {
        }

        /* renamed from: a */
        public C3061a m9072a() {
            return this.editObj;
        }

        /* renamed from: b */
        public void m9073b(C3061a c3061a) {
            this.editObj = c3061a;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$a */
    public class C1586a extends UploadMediaHelper.AbstractC3185v {

        /* renamed from: a */
        public final /* synthetic */ C3061a f8351a;

        /* renamed from: b */
        public final /* synthetic */ AbstractC6381r f8352b;

        public C1586a(C3061a c3061a, AbstractC6381r abstractC6381r) {
            this.f8351a = c3061a;
            this.f8352b = abstractC6381r;
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            PhotoCommentFragment.this.m9053q1();
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) {
            m9074f(uploadMediaHelper);
        }

        /* renamed from: f */
        public final void m9074f(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.UploadResultType uploadResultTypeM16828Y0 = uploadMediaHelper.m16828Y0();
            if (UploadUtils.UploadResultType.STEP_3_SUCCESS == uploadResultTypeM16828Y0) {
                PhotoCommentFragment.this.m9069y2(uploadMediaHelper.m16826X0(), this.f8351a, this.f8352b);
            } else {
                PhotoCommentFragment.this.m9033P2(uploadResultTypeM16828Y0, this.f8351a, this.f8352b);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$a0 */
    public class ViewOnClickListenerC1587a0 implements View.OnClickListener {
        public ViewOnClickListenerC1587a0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.handDrawEditImage) {
                PhotoCommentFragment.this.f8342x.getText().clear();
            }
            if (PhotoCommentFragment.this.getActivity() != null) {
                CLUtility.m16589t1(PhotoCommentFragment.this.getActivity());
            }
            String strM15225n = CLUtility.m16613z1(PhotoCommentFragment.this.f8329q0.f8395b, null) ? PhotoCommentFragment.this.f8329q0.f8395b : C2950b0.m14920s().m15225n(PhotoCommentFragment.this.f8298b.m15144p());
            PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
            photoCommentFragment.m9054q2(photoCommentFragment.f8298b.m15148t().f13200d, strM15225n);
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$b */
    public class RunnableC1588b implements Runnable {
        public RunnableC1588b() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            if (PhotoCommentFragment.this.f8279Q == -1 || PhotoCommentFragment.this.f8302d == null || PhotoCommentFragment.this.f8308g == null) {
                return;
            }
            Log.d("PhotoCommentFragment", "[mPlayVoiceRunnable] in");
            PhotoCommentFragment.this.f8277P = true;
            int iM9109l = PhotoCommentFragment.this.f8308g.m9109l(PhotoCommentFragment.this.f8279Q);
            if (iM9109l != -1) {
                int firstVisiblePosition = iM9109l - PhotoCommentFragment.this.f8302d.getFirstVisiblePosition();
                View childAt = PhotoCommentFragment.this.f8302d.getChildAt(firstVisiblePosition);
                if (PhotoCommentFragment.this.isResumed()) {
                    Log.d("PhotoCommentFragment", "[mPlayVoiceRunnable] isResume");
                    if (childAt == null) {
                        Log.d("PhotoCommentFragment", "[mPlayVoiceRunnable] voiceView is null");
                        PhotoCommentFragment.this.f8289V.postDelayed(PhotoCommentFragment.this.f8262H0, 500L);
                    } else {
                        Log.d("PhotoCommentFragment", "[mPlayVoiceRunnable] play voice");
                        PhotoCommentFragment.this.f8308g.m9112r(firstVisiblePosition);
                    }
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$b0 */
    public class C1589b0 implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ C2925v.d f8356a;

        /* renamed from: b */
        public final /* synthetic */ Permission f8357b;

        public C1589b0(C2925v.d dVar, Permission permission) {
            this.f8356a = dVar;
            this.f8357b = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (!z8 || PhotoCommentFragment.this.getActivity() == null) {
                C5187v0.m20267c(R.string.permission_denied);
            } else {
                C5183t0.m20262b(PhotoCommentFragment.this.getActivity(), this.f8357b);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() throws IOException {
            PhotoListActivity.m9212n2(PhotoCommentFragment.this.getActivity(), PhotoCommentFragment.this.f8298b, this.f8356a);
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$c */
    public class C1590c implements C5321e.m {
        public C1590c() {
        }

        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) {
            String str;
            if (map == null) {
                Log.d("PhotoCommentFragment", "[onReceive] Info is null.");
                return false;
            }
            if (PhotoCommentFragment.this.f8298b != null) {
                long jM15144p = PhotoCommentFragment.this.f8298b.m15144p();
                str = map.get("mediaId");
                if (str == null || !str.equals(String.valueOf(jM15144p))) {
                    Log.d("PhotoCommentFragment", "[onReceive] Don't need to handle event for different media id.");
                    return true;
                }
            } else {
                str = null;
            }
            String str2 = map.get("eventType");
            if (str2.equals("media.comment.created")) {
                PhotoCommentFragment.this.m9068y1(str, map.get("commentId"));
            } else if (str2.equals("media.comment.deleted")) {
                try {
                    PhotoCommentFragment.this.m9070z1(Long.parseLong(map.get("commentId")));
                } catch (NumberFormatException e9) {
                    e9.printStackTrace();
                }
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$c0 */
    public class ViewOnClickListenerC1591c0 implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$c0$a */
        public class a implements C2925v.d {
            public a() {
            }

            @Override // com.cyberlink.you.chat.C2925v.d
            /* renamed from: a */
            public void mo9091a() {
            }

            @Override // com.cyberlink.you.chat.C2925v.d
            /* renamed from: b */
            public void mo9092b(List<Uri> list) {
                Intent intent = new Intent();
                if (list.size() == 1) {
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.STREAM", list.get(0));
                    intent.setType("image/*");
                    PhotoCommentFragment.this.startActivity(Intent.createChooser(intent, PhotoCommentFragment.this.getActivity().getString(R.string.share_photo_to_other_app)));
                }
            }
        }

        public ViewOnClickListenerC1591c0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m9083i(Dialog dialog, View view) {
            C6566a.m25159r("Save");
            PhotoCommentFragment.this.m9045m1(null);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: j */
        public /* synthetic */ void m9084j(Dialog dialog, View view) {
            C6566a.m25159r("Share");
            if (PhotoCommentFragment.this.f8298b != null) {
                long[] jArr = {PhotoCommentFragment.this.f8298b.m15144p()};
                Intent intent = new Intent(PhotoCommentFragment.this.getActivity(), (Class<?>) ShareMediaActivity.class);
                intent.putExtra("share_media_id", jArr);
                intent.putExtra("shareType", ShareType.Internal_Media.toString());
                intent.putExtra("withComment", false);
                PhotoCommentFragment.this.startActivity(intent);
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void m9085k(Dialog dialog, View view) {
            PhotoCommentFragment.this.m9045m1(new a());
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: l */
        public /* synthetic */ void m9086l(Dialog dialog, View view) {
            C6566a.m25159r("Save_UAlbum");
            if (PhotoCommentFragment.this.f8298b != null) {
                long[] jArr = {PhotoCommentFragment.this.f8298b.m15144p()};
                Intent intent = new Intent(PhotoCommentFragment.this.getActivity(), (Class<?>) GroupAlbumActivity.class);
                intent.putExtra("CopyToMyAlbum", true);
                intent.putExtra("select", true);
                intent.putExtra("share_media_id", jArr);
                intent.putExtra("withComment", true);
                intent.putExtra("ShowShareToMyAlbum", true);
                PhotoCommentFragment.this.startActivityForResult(intent, 0);
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: m */
        public /* synthetic */ void m9087m(Dialog dialog, View view) {
            C6566a.m25159r("ShareWComment");
            if (PhotoCommentFragment.this.f8298b != null) {
                long[] jArr = {PhotoCommentFragment.this.f8298b.m15144p()};
                Intent intent = new Intent(PhotoCommentFragment.this.getActivity(), (Class<?>) ShareMediaActivity.class);
                intent.putExtra("share_media_id", jArr);
                intent.putExtra("shareType", ShareType.Internal_Media.toString());
                intent.putExtra("withComment", true);
                PhotoCommentFragment.this.startActivity(intent);
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: n */
        public /* synthetic */ void m9088n(Dialog dialog, View view) {
            C6566a.m25159r("Save_GAlbum");
            if (PhotoCommentFragment.this.f8298b != null) {
                long[] jArr = {PhotoCommentFragment.this.f8298b.m15144p()};
                Intent intent = new Intent(PhotoCommentFragment.this.getActivity(), (Class<?>) GroupAlbumActivity.class);
                intent.putExtra("Group", ((ShowMediaActivity) PhotoCommentFragment.this.getActivity()).m10015n1());
                intent.putExtra("select", true);
                intent.putExtra("share_media_id", jArr);
                intent.putExtra("withComment", true);
                intent.putExtra("ShareToGroupAlbum", true);
                PhotoCommentFragment.this.startActivityForResult(intent, 2);
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o */
        public /* synthetic */ void m9089o(Dialog dialog, View view) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            PhotoCommentFragment.this.m9051p1();
        }

        /* renamed from: p */
        public static /* synthetic */ void m9090p(Dialog dialog, View view) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean zEquals;
            if (PhotoCommentFragment.this.getActivity() == null) {
                return;
            }
            final Dialog dialogM16384c = C3123g.m16384c(PhotoCommentFragment.this.getActivity());
            dialogM16384c.setContentView(R.layout.dialog_media_more);
            dialogM16384c.findViewById(R.id.mediaMoreDownloadLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.a9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f9714b.m9083i(dialogM16384c, view2);
                }
            });
            if (PhotoCommentFragment.this.f8265J) {
                dialogM16384c.findViewById(R.id.mediaMoreShareLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.mediaMoreShareToOtherAppLayout).setVisibility(8);
            } else {
                dialogM16384c.findViewById(R.id.mediaMoreShareLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.b9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f9743b.m9084j(dialogM16384c, view2);
                    }
                });
                dialogM16384c.findViewById(R.id.mediaMoreShareToOtherAppLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.c9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f9776b.m9085k(dialogM16384c, view2);
                    }
                });
            }
            if (PhotoCommentFragment.this.f8267K) {
                dialogM16384c.findViewById(R.id.copytoMyAlbumLayout).setVisibility(0);
                dialogM16384c.findViewById(R.id.copytoMyAlbumLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.d9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10366b.m9086l(dialogM16384c, view2);
                    }
                });
            }
            if (PhotoCommentFragment.this.f8308g == null || PhotoCommentFragment.this.f8308g.getCount() <= 0) {
                dialogM16384c.findViewById(R.id.mediaMoreShareWithCommentLayout).setVisibility(8);
            } else {
                dialogM16384c.findViewById(R.id.mediaMoreShareWithCommentLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.e9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10399b.m9087m(dialogM16384c, view2);
                    }
                });
            }
            Group groupM10015n1 = ((ShowMediaActivity) PhotoCommentFragment.this.getActivity()).m10015n1();
            if (groupM10015n1 == null) {
                zEquals = false;
            } else if (!groupM10015n1.f13738y.equals("General") || PhotoCommentFragment.this.f8298b == null || groupM10015n1.m15751i()) {
                if (groupM10015n1.f13738y.equals("Official") || groupM10015n1.f13738y.equals("Corporate")) {
                    dialogM16384c.findViewById(R.id.mediaMoreDownloadLayout).setVisibility(8);
                    dialogM16384c.findViewById(R.id.copytoMyAlbumLayout).setVisibility(8);
                    dialogM16384c.findViewById(R.id.saveToGroupAlbumLayout).setVisibility(8);
                    dialogM16384c.findViewById(R.id.mediaMoreShareLayout).setVisibility(8);
                }
                zEquals = false;
            } else {
                zEquals = PhotoCommentFragment.this.f8298b.m15131c().equals(groupM10015n1.f13718e);
            }
            if (zEquals) {
                dialogM16384c.findViewById(R.id.saveToGroupAlbumLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.f9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10434b.m9088n(dialogM16384c, view2);
                    }
                });
            } else {
                dialogM16384c.findViewById(R.id.saveToGroupAlbumLayout).setVisibility(8);
            }
            Group groupM10015n12 = PhotoCommentFragment.this.getActivity() == null ? null : ((ShowMediaActivity) PhotoCommentFragment.this.getActivity()).m10015n1();
            boolean zEquals2 = (groupM10015n12 == null || groupM10015n12.f13704C || groupM10015n12.f13729p <= 0 || PhotoCommentFragment.this.f8298b == null) ? true : TextUtils.equals(PhotoCommentFragment.this.f8298b.m15136h(), String.valueOf(Globals.m7388i0().m7568k1()));
            if (PhotoCommentFragment.this.f8283S || PhotoCommentFragment.this.f8265J || !zEquals2) {
                dialogM16384c.findViewById(R.id.mediaMoreDeleteLayout).setVisibility(8);
            } else {
                dialogM16384c.findViewById(R.id.mediaMoreDeleteLayout).setVisibility(0);
                dialogM16384c.findViewById(R.id.mediaMoreDeleteLayout).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.g9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f10688b.m9089o(dialogM16384c, view2);
                    }
                });
            }
            dialogM16384c.findViewById(R.id.mediaMoreCancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.h9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PhotoCommentFragment.ViewOnClickListenerC1591c0.m9090p(dialogM16384c, view2);
                }
            });
            if (groupM10015n12 != null && groupM10015n12.f13711J) {
                dialogM16384c.findViewById(R.id.mediaMoreShareLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.mediaMoreShareWithCommentLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.mediaMoreShareToOtherAppLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.saveToGroupAlbumLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.copytoMyAlbumLayout).setVisibility(8);
            }
            if (PhotoCommentFragment.this.f8327p0) {
                dialogM16384c.findViewById(R.id.mediaMoreShareToOtherAppLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.mediaMoreShareLayout).setVisibility(8);
                dialogM16384c.findViewById(R.id.mediaMoreDeleteLayout).setVisibility(8);
            }
            PhotoCommentFragment.this.m9012A2(dialogM16384c);
            dialogM16384c.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$d */
    public class C1592d extends XMPPManager.AbstractC2868s {
        public C1592d(boolean z8) {
            super(z8);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: e */
        public String mo8240e(C2904l c2904l) throws JSONException {
            String string = null;
            if (c2904l.m14389D() != MessageObj.MessageType.CommentUpdate || c2904l.m14398M()) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(c2904l.m14420i());
                String string2 = (!jSONObject.has("mediaId") || jSONObject.isNull("mediaId")) ? null : jSONObject.getString("mediaId");
                if (jSONObject.has("commentId") && !jSONObject.isNull("commentId")) {
                    string = jSONObject.getString("commentId");
                }
                String string3 = (!jSONObject.has("comment") || jSONObject.isNull("comment")) ? "" : jSONObject.getString("comment");
                if (PhotoCommentFragment.this.f8298b != null) {
                    long jM15144p = PhotoCommentFragment.this.f8298b.m15144p();
                    if (string2 == null || !string2.equals(String.valueOf(jM15144p))) {
                        return PhotoCommentFragment.class.toString();
                    }
                }
                PhotoCommentFragment.this.m9011A1(string, string3);
            } catch (JSONException e9) {
                e9.printStackTrace();
            }
            return PhotoCommentFragment.class.toString();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$d0 */
    public class C1593d0 implements VoiceRecordFragment.InterfaceC1861g {

        /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$d0$a */
        public class a extends AsyncTask<Void, Void, Void> {

            /* renamed from: a */
            public final /* synthetic */ String f8364a;

            /* renamed from: b */
            public final /* synthetic */ String f8365b;

            public a(String str, String str2) {
                this.f8364a = str;
                this.f8365b = str2;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                C3061a c3061aM9065w1 = PhotoCommentFragment.this.m9065w1(this.f8365b, "CommentMedia", new C3061a.a(0L, "", "", "", this.f8364a, this.f8365b, "Audio"));
                C2950b0.m14920s().m15217f(c3061aM9065w1);
                PhotoCommentFragment.this.m9041k1(c3061aM9065w1);
                PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
                photoCommentFragment.m9048n2(this.f8364a, this.f8365b, c3061aM9065w1, photoCommentFragment.f8278P0);
                return null;
            }
        }

        public C1593d0() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: a */
        public void mo7976a() {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: b */
        public void mo7977b(boolean z8) {
        }

        @Override // com.cyberlink.you.activity.VoiceRecordFragment.InterfaceC1861g
        /* renamed from: c */
        public void mo7978c(String str, String str2) {
            PhotoCommentFragment.this.m9015C1();
            C6566a.m25159r("Voice");
            PhotoCommentFragment.this.f8336u.setVisibility(0);
            if (PhotoCommentFragment.this.getActivity() == null) {
                return;
            }
            PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
            photoCommentFragment.f8297a0 = ProgressDialog.show(photoCommentFragment.getActivity(), "", PhotoCommentFragment.this.getActivity().getResources().getString(R.string.uploading), true);
            new a(str, str2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$e */
    public class C1594e implements C6456d.j {
        public C1594e() {
        }

        @Override // p218v2.C6456d.j
        public void onConnected() {
            Log.d("PhotoCommentFragment", "[mConnectionListener] onConnected in");
            if (PhotoCommentFragment.this.f8298b != null) {
                PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
                photoCommentFragment.m9028M(photoCommentFragment.f8298b.m15144p());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$e0 */
    public class C1595e0 extends UploadMediaHelper.AbstractC3185v {

        /* renamed from: a */
        public final /* synthetic */ C3061a f8368a;

        /* renamed from: b */
        public final /* synthetic */ AbstractC6381r f8369b;

        public C1595e0(C3061a c3061a, AbstractC6381r abstractC6381r) {
            this.f8368a = c3061a;
            this.f8369b = abstractC6381r;
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: c */
        public void mo8381c(UploadMediaHelper uploadMediaHelper) {
            PhotoCommentFragment.this.m9053q1();
        }

        @Override // com.cyberlink.you.utility.UploadMediaHelper.AbstractC3185v
        /* renamed from: e */
        public void mo8382e(UploadMediaHelper uploadMediaHelper) {
            m9094f(uploadMediaHelper);
        }

        /* renamed from: f */
        public final void m9094f(UploadMediaHelper uploadMediaHelper) {
            UploadUtils.UploadResultType uploadResultTypeM16853k1 = uploadMediaHelper.m16853k1();
            if (UploadUtils.UploadResultType.STEP_3_SUCCESS == uploadResultTypeM16853k1) {
                PhotoCommentFragment.this.m9069y2(uploadMediaHelper.m16851j1(), this.f8368a, this.f8369b);
            } else {
                PhotoCommentFragment.this.m9033P2(uploadResultTypeM16853k1, this.f8368a, this.f8369b);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$f */
    public class RunnableC1596f implements Runnable {
        public RunnableC1596f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d("PhotoCommentFragment", "[mShowCommentAtSpecifyPosRunnable] in");
            if (PhotoCommentFragment.this.f8273N == -1 || PhotoCommentFragment.this.f8298b == null) {
                return;
            }
            if (PhotoCommentFragment.this.f8298b.m15144p() != PhotoCommentFragment.this.f8271M) {
                PhotoCommentFragment.this.f8285T = false;
                return;
            }
            int iM9109l = PhotoCommentFragment.this.f8308g.m9109l(PhotoCommentFragment.this.f8273N);
            if (iM9109l == -1) {
                if (PhotoCommentFragment.this.f8287U) {
                    PhotoCommentFragment.this.f8285T = false;
                    return;
                } else {
                    PhotoCommentFragment.this.f8289V.postDelayed(PhotoCommentFragment.this.f8272M0, 500L);
                    return;
                }
            }
            if (PhotoCommentFragment.this.isResumed()) {
                Log.d("PhotoCommentFragment", "[mShowCommentAtSpecifyPosRunnable] isResume");
                PhotoCommentFragment.this.f8285T = false;
                PhotoCommentFragment.this.f8302d.setSelection(iM9109l);
                if (!"CommentMedia".equals(PhotoCommentFragment.this.f8275O) || PhotoCommentFragment.this.f8277P) {
                    return;
                }
                PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
                photoCommentFragment.f8279Q = photoCommentFragment.f8273N;
                PhotoCommentFragment.this.f8289V.postDelayed(PhotoCommentFragment.this.f8262H0, 500L);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$f0 */
    public class C1597f0 extends ArrayAdapter<C3061a> {

        /* renamed from: b */
        public final ArrayList<C3061a> f8372b;

        /* renamed from: c */
        public final ArrayList<C3061a> f8373c;

        /* renamed from: d */
        public Context f8374d;

        /* renamed from: e */
        public UserInfo f8375e;

        /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$f0$a */
        public class a {

            /* renamed from: a */
            public ImageView f8377a;

            /* renamed from: b */
            public ImageView f8378b;

            /* renamed from: c */
            public TextView f8379c;

            /* renamed from: d */
            public TextView f8380d;

            /* renamed from: e */
            public TextView f8381e;

            /* renamed from: f */
            public View f8382f;

            /* renamed from: g */
            public ImageView f8383g;

            /* renamed from: h */
            public TextView f8384h;

            /* renamed from: i */
            public ImageView f8385i;

            /* renamed from: j */
            public View f8386j;

            public a() {
            }

            public /* synthetic */ a(C1597f0 c1597f0, C1606k c1606k) {
                this();
            }
        }

        public C1597f0(Context context, int i9, ArrayList<C3061a> arrayList) {
            super(context, i9, arrayList);
            this.f8373c = new ArrayList<>();
            this.f8372b = arrayList;
            this.f8374d = context;
            this.f8375e = CLUtility.m16497V0(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: o */
        public /* synthetic */ void m9099o(a aVar, Object obj, Friend friend) {
            Object tag = aVar.f8379c.getTag();
            if (tag == null || tag != obj) {
                return;
            }
            m9116v(aVar, friend);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: p */
        public /* synthetic */ void m9100p(String str, final a aVar, final Object obj, String str2, String str3, String str4, String str5) {
            if (str4 == null) {
                Log.d("PhotoCommentFragment", "Response is null");
                return;
            }
            if (!str4.equals("200")) {
                Log.d("PhotoCommentFragment", "statusCode=" + str4);
                return;
            }
            final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str5));
            C2950b0.m14899A().m15018j(friendM20184f, false);
            if (!PhotoCommentFragment.this.f8317k0.containsKey(str)) {
                PhotoCommentFragment.this.f8317k0.put(str, friendM20184f);
            }
            if (PhotoCommentFragment.this.getActivity() != null) {
                PhotoCommentFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.k9
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10818b.m9099o(aVar, obj, friendM20184f);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: q */
        public /* synthetic */ void m9101q(C3061a c3061a, View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            m9113s(c3061a);
        }

        @Override // android.widget.ArrayAdapter
        public void clear() {
            this.f8373c.clear();
            this.f8372b.clear();
            PhotoCommentFragment.this.f8315j0.clear();
        }

        @Override // android.widget.ArrayAdapter
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void add(C3061a c3061a) {
            if (c3061a == null) {
                Log.d("PhotoCommentFragment", "[add] Some list is null or input commentObj is null.");
            } else {
                if (PhotoCommentFragment.this.f8315j0.indexOfKey(c3061a.m15783e()) >= 0) {
                    Log.d("PhotoCommentFragment", "[add] The commentObj is already in list.");
                    return;
                }
                this.f8372b.add(c3061a);
                PhotoCommentFragment.this.f8315j0.put(c3061a.m15783e(), c3061a);
                notifyDataSetChanged();
            }
        }

        /* renamed from: f */
        public void m9103f(List<C3061a> list) {
            if (list == null) {
                return;
            }
            try {
                for (C3061a c3061a : list) {
                    this.f8372b.add(c3061a);
                    PhotoCommentFragment.this.f8315j0.put(c3061a.m15783e(), c3061a);
                }
                notifyDataSetChanged();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: g */
        public void m9104g(C3061a c3061a) {
            if (c3061a == null) {
                return;
            }
            try {
                this.f8373c.add(c3061a);
                PhotoCommentFragment.this.f8315j0.put(c3061a.m15783e(), c3061a);
                notifyDataSetChanged();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.f8372b.size() + this.f8373c.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = ((LayoutInflater) this.f8374d.getSystemService("layout_inflater")).inflate(R.layout.view_item_media_comment, viewGroup, false);
                aVar = new a(this, null);
                aVar.f8377a = (ImageView) view.findViewById(R.id.ImageCommentAvatar);
                aVar.f8379c = (TextView) view.findViewById(R.id.ImageCommentName);
                aVar.f8380d = (TextView) view.findViewById(R.id.ImageCommentText);
                aVar.f8381e = (TextView) view.findViewById(R.id.ImageCommentTime);
                aVar.f8382f = view.findViewById(R.id.ImageCommentVoiceArea);
                aVar.f8383g = (ImageView) view.findViewById(R.id.ImageCommentVoiceOpBtn);
                aVar.f8384h = (TextView) view.findViewById(R.id.ImageVoiceTime);
                aVar.f8385i = (ImageView) view.findViewById(R.id.errorIcon);
                aVar.f8386j = view.findViewById(R.id.downloadProgressbar);
                aVar.f8378b = (ImageView) view.findViewById(R.id.ImageCommentPenView);
                C5169o.m20161f(aVar.f8380d);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            m9117w(aVar, getItem(i9));
            return view;
        }

        /* renamed from: h */
        public void m9105h(List<C3061a> list) {
            if (list == null) {
                return;
            }
            try {
                for (C3061a c3061a : list) {
                    this.f8373c.add(c3061a);
                    PhotoCommentFragment.this.f8315j0.put(c3061a.m15783e(), c3061a);
                }
                notifyDataSetChanged();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: i */
        public void m9106i() {
            setNotifyOnChange(false);
            clear();
            setNotifyOnChange(true);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public C3061a getItem(int i9) {
            return m9111n(i9) ? this.f8373c.get(i9 - this.f8372b.size()) : this.f8372b.get(i9);
        }

        /* renamed from: k */
        public C3061a m9108k(long j9) {
            for (int i9 = 0; i9 < this.f8372b.size(); i9++) {
                if (this.f8372b.get(i9).m15783e() == j9) {
                    return this.f8372b.get(i9);
                }
            }
            return null;
        }

        /* renamed from: l */
        public int m9109l(long j9) {
            for (int i9 = 0; i9 < this.f8372b.size(); i9++) {
                if (this.f8372b.get(i9).m15783e() == j9) {
                    return i9;
                }
            }
            return -1;
        }

        /* renamed from: m */
        public int m9110m(C3061a c3061a) {
            return this.f8372b.indexOf(c3061a);
        }

        /* renamed from: n */
        public boolean m9111n(int i9) {
            return i9 >= this.f8372b.size();
        }

        /* renamed from: r */
        public final void m9112r(int i9) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            m9113s(getItem(i9));
        }

        /* renamed from: s */
        public final void m9113s(C3061a c3061a) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            if (PhotoCommentFragment.this.f8291W != -1 && PhotoCommentFragment.this.f8291W == c3061a.m15783e()) {
                PhotoCommentFragment.this.m9035R2();
                return;
            }
            C3061a.a aVarM15789k = c3061a.m15789k();
            if (aVarM15789k == null) {
                Log.d("PhotoCommentFragment", "[voiceOpView.onClick] mediaComment is null");
                return;
            }
            String unused = PhotoCommentFragment.f8246Y0 = aVarM15789k.f13818e;
            if (PhotoCommentFragment.f8246Y0 != null && new File(PhotoCommentFragment.f8246Y0).exists()) {
                PhotoCommentFragment.this.m9034Q2(c3061a);
                return;
            }
            c3061a.f13813l = true;
            PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
            PhotoCommentFragment.this.m9057s1(c3061a);
            aVarM15789k.f13818e = PhotoCommentFragment.f8246Y0;
            PhotoCommentFragment.this.m9040W2(c3061a);
        }

        @Override // android.widget.ArrayAdapter
        /* renamed from: t, reason: merged with bridge method [inline-methods] */
        public void remove(C3061a c3061a) {
            if (c3061a == null) {
                return;
            }
            try {
                this.f8372b.remove(c3061a);
                this.f8373c.remove(c3061a);
                PhotoCommentFragment.this.f8315j0.remove(c3061a.m15783e());
                notifyDataSetChanged();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        /* renamed from: u */
        public final void m9115u(final a aVar, final String str) {
            Friend friendM15003C;
            if (PhotoCommentFragment.this.f8317k0.containsKey(str)) {
                friendM15003C = (Friend) PhotoCommentFragment.this.f8317k0.get(str);
            } else {
                friendM15003C = C2950b0.m14899A().m15003C(str);
                PhotoCommentFragment.this.f8317k0.put(str, friendM15003C);
            }
            aVar.f8379c.setTag(null);
            if (friendM15003C != null) {
                m9116v(aVar, friendM15003C);
                return;
            }
            final Object obj = new Object();
            aVar.f8379c.setText("");
            aVar.f8379c.setTag(obj);
            aVar.f8377a.setImageResource(R.drawable.pic_default);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("userId", str));
            new FriendsClient().m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.j9
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str2, String str3, String str4, String str5) {
                    this.f10779a.m9100p(str, aVar, obj, str2, str3, str4, str5);
                }
            });
        }

        /* renamed from: v */
        public final void m9116v(a aVar, Friend friend) {
            long j9 = friend.f13645c;
            UserInfo userInfo = this.f8375e;
            if (j9 == userInfo.f13777b) {
                String str = userInfo.f13778c;
                if (str != null) {
                    aVar.f8379c.setText(str);
                } else {
                    aVar.f8379c.setText("");
                }
            } else if (friend.m15621b() == null || friend.m15621b().isEmpty()) {
                aVar.f8379c.setText("");
            } else {
                aVar.f8379c.setText(friend.m15621b());
            }
            C6127a.m23469j(PhotoCommentFragment.this.getActivity(), aVar.f8377a, friend);
        }

        /* renamed from: w */
        public final void m9117w(a aVar, final C3061a c3061a) {
            Friend friend;
            aVar.f8380d.setTypeface(Typeface.DEFAULT, 0);
            if (c3061a == null) {
                aVar.f8377a.setOnClickListener(null);
                aVar.f8379c.setText("");
                aVar.f8380d.setVisibility(8);
                aVar.f8381e.setText("");
                aVar.f8382f.setVisibility(8);
                aVar.f8383g.setVisibility(8);
                aVar.f8384h.setVisibility(8);
                aVar.f8385i.setVisibility(8);
                aVar.f8386j.setVisibility(8);
                return;
            }
            String strValueOf = String.valueOf(c3061a.m15786h());
            if (PhotoCommentFragment.this.f8317k0.containsKey(strValueOf)) {
                friend = (Friend) PhotoCommentFragment.this.f8317k0.get(strValueOf);
            } else {
                Friend friendM15003C = C2950b0.m14899A().m15003C(strValueOf);
                PhotoCommentFragment.this.f8317k0.put(strValueOf, friendM15003C);
                friend = friendM15003C;
            }
            aVar.f8377a.setTag(R.id.tag_User, friend);
            aVar.f8377a.setOnClickListener(PhotoCommentFragment.this.f8341w0);
            m9115u(aVar, String.valueOf(c3061a.m15786h()));
            if (c3061a.m15784f().equals("CommentMedia")) {
                aVar.f8380d.setVisibility(8);
                aVar.f8378b.setVisibility(8);
                aVar.f8382f.setVisibility(0);
                aVar.f8383g.setVisibility(0);
                if (PhotoCommentFragment.this.f8291W == -1 || PhotoCommentFragment.this.f8291W != c3061a.m15783e()) {
                    aVar.f8383g.setImageResource(R.drawable.icon_play_s);
                    if (PhotoCommentFragment.this.f8293X != null && PhotoCommentFragment.this.f8293X.m9120b() == aVar.f8384h) {
                        PhotoCommentFragment.this.f8293X.m9121d(null);
                    }
                    if (c3061a.m15789k() != null) {
                        aVar.f8384h.setText(CLUtility.m16531f(c3061a.m15789k().f13819f));
                    }
                } else {
                    aVar.f8383g.setImageResource(R.drawable.icon_stop_s);
                    if (PhotoCommentFragment.this.f8293X != null) {
                        PhotoCommentFragment.this.f8293X.m9121d(aVar.f8384h);
                    }
                }
                aVar.f8383g.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.i9
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
                        this.f10749b.m9101q(c3061a, view);
                    }
                });
            } else {
                String strM15782d = c3061a.m15782d();
                if (c3061a.m15784f().equals("CommentDoodle")) {
                    aVar.f8378b.setVisibility(0);
                    aVar.f8378b.setImageResource(C6152i.m23610b(c3061a.m15789k().f13819f, true));
                    if (strM15782d == null || strM15782d.isEmpty()) {
                        strM15782d = Globals.m7388i0().getApplicationContext().getString(R.string.doodle_comment_default_string) + StringUtils.SPACE;
                        TextView textView = aVar.f8380d;
                        textView.setTypeface(textView.getTypeface(), 2);
                    }
                } else {
                    aVar.f8378b.setVisibility(8);
                }
                aVar.f8382f.setVisibility(8);
                aVar.f8380d.setVisibility(0);
                if (strM15782d != null) {
                    aVar.f8380d.setText(strM15782d);
                    CLUtility.m16543i(aVar.f8380d);
                }
            }
            if (c3061a.m15792n() == 2) {
                if (c3061a.m15788j() != null) {
                    aVar.f8381e.setText(CLUtility.m16434E2(c3061a.m15788j()));
                } else {
                    aVar.f8381e.setText("");
                }
                aVar.f8381e.setVisibility(0);
            } else {
                aVar.f8381e.setText("");
                aVar.f8381e.setVisibility(8);
            }
            if (c3061a.m15792n() == 1) {
                aVar.f8385i.setVisibility(0);
            } else {
                aVar.f8385i.setVisibility(8);
            }
            if (c3061a.f13813l) {
                aVar.f8386j.setVisibility(0);
            } else {
                aVar.f8386j.setVisibility(8);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$g */
    public class C1598g implements AbsListView.OnScrollListener {
        public C1598g() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 1) {
                PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
                photoCommentFragment.f8325o0 = photoCommentFragment.f8302d.getFirstVisiblePosition();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$g0 */
    public class C1599g0 extends TimerTask {

        /* renamed from: b */
        public TextView f8389b;

        /* renamed from: c */
        public String f8390c;

        public C1599g0() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m9119c() {
            TextView textView;
            String str = this.f8390c;
            if (str == null || (textView = this.f8389b) == null) {
                return;
            }
            textView.setText(str);
        }

        /* renamed from: b */
        public TextView m9120b() {
            return this.f8389b;
        }

        /* renamed from: d */
        public void m9121d(TextView textView) {
            this.f8389b = textView;
            String str = this.f8390c;
            if (str == null || textView == null) {
                return;
            }
            textView.setText(str);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (PhotoCommentFragment.this.f8261H == null || PhotoCommentFragment.this.getActivity() == null) {
                Log.d("PhotoCommentFragment", "[CountDownRemainTimeTask] run for Player or activity is null");
                cancel();
            } else if (PhotoCommentFragment.this.f8261H.m20014d() != -1) {
                this.f8390c = CLUtility.m16531f(String.valueOf((PhotoCommentFragment.this.f8261H.m20014d() - PhotoCommentFragment.this.f8261H.m20013c()) / CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT));
                Log.d("PhotoCommentFragment", "[CountDownRemainTimeTask] mRemainTimeString= " + this.f8390c);
                if (PhotoCommentFragment.this.getActivity() != null) {
                    PhotoCommentFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.l9
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f10852b.m9119c();
                        }
                    });
                }
            }
        }

        public /* synthetic */ C1599g0(PhotoCommentFragment photoCommentFragment, C1606k c1606k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$h */
    public class C1600h implements C3197a.b {

        /* renamed from: a */
        public final /* synthetic */ C3061a f8392a;

        public C1600h(C3061a c3061a) {
            this.f8392a = c3061a;
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: a */
        public void mo9122a() {
            this.f8392a.f13813l = false;
            Log.d("PhotoCommentFragment", "download fail");
            if (PhotoCommentFragment.this.isAdded()) {
                C5187v0.m20267c(R.string.error_server_response);
            }
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: b */
        public void mo9123b(String str) throws IllegalStateException, IOException, SecurityException {
            this.f8392a.f13813l = false;
            Log.d("PhotoCommentFragment", "download success");
            MediaScannerConnection.scanFile(Globals.m7388i0().getApplicationContext(), new String[]{str}, null, null);
            try {
                PhotoCommentFragment.this.m9034Q2(this.f8392a);
            } catch (IllegalArgumentException e9) {
                e9.printStackTrace();
            }
        }

        @Override // com.cyberlink.you.utility.C3197a.b
        /* renamed from: c */
        public void mo9124c(int i9, int i10, int i11) {
            this.f8392a.f13813l = true;
            Log.d("PhotoCommentFragment", "download progress=" + String.valueOf(i9));
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$i */
    public class C1602i implements C5140e0.a {
        public C1602i() {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: a */
        public void mo9127a(int i9) {
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: b */
        public void mo9128b() throws IllegalStateException {
            PhotoCommentFragment.this.m9035R2();
        }

        @Override // p116k4.C5140e0.a
        /* renamed from: c */
        public void mo9129c(int i9) throws IllegalStateException {
            PhotoCommentFragment.this.m9035R2();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$i0 */
    public class C1603i0 extends AbstractRunnableC6364a {

        /* renamed from: d */
        public final WeakReference<FriendsClient> f8399d;

        /* renamed from: e */
        public final long f8400e;

        /* renamed from: c */
        public final String f8398c = "QueryCommentRunnable";

        /* renamed from: f */
        public final int f8401f = 1;

        /* renamed from: g */
        public int f8402g = 0;

        /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$i0$a */
        public class a implements FriendsClient.InterfaceC3051i {

            /* renamed from: a */
            public final /* synthetic */ int f8404a;

            public a(int i9) {
                this.f8404a = i9;
            }

            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public void mo134a(String str, String str2, String str3, String str4) {
                Log.d("QueryCommentRunnable", "[queryComment] Response is " + str3);
                if (!"200".equals(str3)) {
                    Log.d("QueryCommentRunnable", "[queryComment] omit ");
                    return;
                }
                int iM16553k1 = CLUtility.m16553k1(str4);
                int iM16494U0 = CLUtility.m16494U0(str4);
                if (iM16553k1 == -1 || iM16494U0 == -1) {
                    Log.d("QueryCommentRunnable", "[queryComment] Incorrect total size or result size");
                    return;
                }
                synchronized (this) {
                    C1603i0.m9132f(C1603i0.this);
                    C3062b.m15816p(String.valueOf(C1603i0.this.f8400e), str4);
                    if (C1603i0.this.m24448b()) {
                        Log.d("QueryCommentRunnable", "[processAlbumPages] Force stop");
                    } else {
                        if (C1603i0.this.f8402g == this.f8404a) {
                            PhotoCommentFragment.this.m9060t2();
                        }
                    }
                }
            }
        }

        public C1603i0(long j9) {
            this.f8399d = new WeakReference<>(PhotoCommentFragment.this.f8301c0);
            this.f8400e = j9;
        }

        /* renamed from: f */
        public static /* synthetic */ int m9132f(C1603i0 c1603i0) {
            int i9 = c1603i0.f8402g;
            c1603i0.f8402g = i9 + 1;
            return i9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m9134i(String str, String str2, String str3, String str4) throws IllegalStateException {
            Log.d("QueryCommentRunnable", "[queryComment] Response is " + str3);
            if (!"200".equals(str3)) {
                Log.d("QueryCommentRunnable", "[queryComment] omit ");
                return;
            }
            int iM16553k1 = CLUtility.m16553k1(str4);
            int iM16494U0 = CLUtility.m16494U0(str4);
            if (iM16553k1 == -1 || iM16494U0 == -1) {
                Log.d("QueryCommentRunnable", "[queryComment] Incorrect total size or result size");
                return;
            }
            this.f8402g++;
            C3062b.m15816p(String.valueOf(this.f8400e), str4);
            if (m24448b()) {
                Log.d("QueryCommentRunnable", "[getBackupAlbumList] Force stop");
            } else if (iM16553k1 == iM16494U0) {
                PhotoCommentFragment.this.m9060t2();
            } else {
                m9136j(String.valueOf(this.f8400e), CLUtility.m16559m(iM16553k1, 20));
            }
        }

        /* renamed from: h */
        public final FriendsClient m9135h() {
            return this.f8399d.get();
        }

        /* renamed from: j */
        public final void m9136j(String str, int i9) {
            String strM7449L = Globals.m7388i0().m7449L();
            FriendsClient friendsClientM9135h = m9135h();
            if (friendsClientM9135h == null) {
                Log.d("QueryCommentRunnable", "[queryComment] Can't get friend client instance.");
                return;
            }
            for (int i10 = 2; i10 <= i9; i10++) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new C6301o("token", strM7449L));
                arrayList.add(new C6301o("mediaId", str));
                arrayList.add(new C6301o("pageIndex", String.valueOf(i10)));
                arrayList.add(new C6301o("pageSize", String.valueOf(20)));
                friendsClientM9135h.m15734m("media", "listComment", arrayList, new a(i9));
            }
        }

        /* renamed from: k */
        public final void m9137k() {
            String strM7449L = Globals.m7388i0().m7449L();
            FriendsClient friendsClientM9135h = m9135h();
            if (friendsClientM9135h == null) {
                Log.d("QueryCommentRunnable", "[queryComment] Can't get friend client instance.");
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", strM7449L));
            arrayList.add(new C6301o("mediaId", String.valueOf(this.f8400e)));
            arrayList.add(new C6301o("pageIndex", String.valueOf(1)));
            arrayList.add(new C6301o("pageSize", String.valueOf(20)));
            friendsClientM9135h.m15734m("media", "listComment", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.m9
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) throws IllegalStateException {
                    this.f10888a.m9134i(str, str2, str3, str4);
                }
            });
        }

        @Override // java.lang.Runnable
        public void run() {
            if (m24448b()) {
                Log.d("QueryCommentRunnable", "[run] Stop!!! Don't continue.");
            } else {
                Log.d("QueryCommentRunnable", "[run] Query comment from server");
                m9137k();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$j */
    public class C1604j implements C5325c.b {
        public C1604j() {
        }

        @Override // p137m4.C5325c.b
        /* renamed from: a */
        public void mo6997a() {
            PhotoCommentFragment.this.m9013B1();
        }

        @Override // p137m4.C5325c.b
        /* renamed from: b */
        public void mo6998b() {
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$j0 */
    public class AsyncTaskC1605j0 extends AsyncTask<String, Void, C3061a> {
        public AsyncTaskC1605j0() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C3061a doInBackground(String... strArr) {
            Thread.currentThread().setName("Query comment info");
            String str = strArr[0];
            String str2 = strArr[1];
            if (str == null || str2 == null) {
                Log.e("PhotoCommentFragment", "[queryCommentInfoTask] mediaId/commentId is null.");
                return null;
            }
            C3061a c3061aM15223l = C2950b0.m14920s().m15223l(Long.parseLong(str2));
            if (c3061aM15223l != null) {
                return c3061aM15223l;
            }
            if (PhotoCommentFragment.this.f8301c0 == null) {
                PhotoCommentFragment.this.f8301c0 = new FriendsClient();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("mediaCommentId", str2));
            Pair<String, String> pairM15731j = PhotoCommentFragment.this.f8301c0.m15731j("media", "mediaCommentInfo", arrayList);
            String str3 = (String) pairM15731j.first;
            if (str3 == null) {
                Log.d("PhotoCommentFragment", "[queryCommentInfoTask] status code is null");
                return null;
            }
            if (str3.equals("200")) {
                C3062b.m15816p(str, (String) pairM15731j.second);
                return C2950b0.m14920s().m15223l(Long.parseLong(str2));
            }
            Log.d("PhotoCommentFragment", "[queryCommentInfoTask] status code is '" + str3 + ";");
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C3061a c3061a) throws Throwable {
            if (c3061a == null || !PhotoCommentFragment.this.isAdded()) {
                return;
            }
            PhotoCommentFragment.this.f8308g.add(c3061a);
            PhotoCommentFragment.this.m9037T2();
            PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
            if (PhotoCommentFragment.this.f8302d != null) {
                PhotoCommentFragment.this.f8302d.setSelection(PhotoCommentFragment.this.f8308g.getCount() - 1);
            }
            if (PhotoCommentFragment.this.getActivity() != null) {
                ((ShowMediaActivity) PhotoCommentFragment.this.getActivity()).m10007R0();
            }
            PhotoCommentFragment.this.m9039V2(c3061a);
        }

        public /* synthetic */ AsyncTaskC1605j0(PhotoCommentFragment photoCommentFragment, C1606k c1606k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$k */
    public class C1606k extends GestureDetector.SimpleOnGestureListener {
        public C1606k() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (PhotoCommentFragment.this.f8325o0 != 0 || f10 <= BitmapDescriptorFactory.HUE_RED) {
                return false;
            }
            PhotoCommentFragment.this.m9013B1();
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            PhotoCommentFragment.this.f8302d.performLongClick();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) throws IllegalStateException {
            PhotoCommentFragment.this.m9029M2();
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$k0 */
    public class AsyncTaskC1607k0 extends AsyncTask<String, Void, C3061a> {
        public AsyncTaskC1607k0() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C3061a doInBackground(String... strArr) {
            Thread.currentThread().setName("Update comment info");
            String str = strArr[0];
            String str2 = strArr[1];
            if (str == null || str2 == null) {
                Log.e("PhotoCommentFragment", "[updateCommentInfoTask] commentId/comment is null.");
                return null;
            }
            C3061a c3061aM15223l = C2950b0.m14920s().m15223l(Long.parseLong(str));
            if (c3061aM15223l != null) {
                c3061aM15223l.m15794p(str2);
                C2950b0.m14920s().m15218g(c3061aM15223l, true);
            }
            return c3061aM15223l;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C3061a c3061a) throws Throwable {
            if (c3061a == null || !PhotoCommentFragment.this.isAdded()) {
                return;
            }
            try {
                C3061a c3061aM9108k = PhotoCommentFragment.this.f8308g.m9108k(c3061a.m15783e());
                if (c3061aM9108k != null) {
                    c3061aM9108k.m15794p(c3061a.m15782d());
                    PhotoCommentFragment.this.m9037T2();
                    PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
                    PhotoCommentFragment.this.m9039V2(c3061a);
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        public /* synthetic */ AsyncTaskC1607k0(PhotoCommentFragment photoCommentFragment, C1606k c1606k) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$l */
    public class AsyncTaskC1608l extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f8410a;

        public AsyncTaskC1608l(String str) {
            this.f8410a = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            C3061a c3061aM9065w1 = PhotoCommentFragment.this.m9065w1(this.f8410a, "CommentText", new C3061a.a());
            C2950b0.m14920s().m15217f(c3061aM9065w1);
            PhotoCommentFragment.this.m9041k1(c3061aM9065w1);
            PhotoCommentFragment.m9006x2(this.f8410a, c3061aM9065w1, PhotoCommentFragment.this.f8278P0);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$m */
    public class C1609m extends AbstractC6381r<Pair<C3061a, C3061a>, C3061a> {
        public C1609m(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Pair<C3061a, C3061a> pair) throws Throwable {
            if (PhotoCommentFragment.this.getActivity() != null && ((C3061a) pair.first).m15791m() == PhotoCommentFragment.this.f8298b.m15144p()) {
                PhotoCommentFragment.this.f8298b.m15123J(PhotoCommentFragment.this.f8298b.m15150v() + 1);
                if ("CommentDoodle".equals(((C3061a) pair.second).m15784f())) {
                    PhotoCommentFragment.this.f8298b.m15115B(PhotoCommentFragment.this.f8298b.m15133e() + 1);
                } else if ("CommentMedia".equals(((C3061a) pair.second).m15784f())) {
                    PhotoCommentFragment.this.f8298b.m15116C(PhotoCommentFragment.this.f8298b.m15134f() + 1);
                } else if ("CommentText".equals(((C3061a) pair.second).m15784f())) {
                    PhotoCommentFragment.this.f8298b.m15117D(PhotoCommentFragment.this.f8298b.m15135g() + 1);
                }
                C2950b0.m14914m().m14712i(PhotoCommentFragment.this.f8298b);
                if (PhotoCommentFragment.this.f8308g != null) {
                    PhotoCommentFragment.this.f8308g.remove((C3061a) pair.first);
                    PhotoCommentFragment.this.f8308g.add((C3061a) pair.second);
                }
                PhotoCommentFragment.this.m9037T2();
                PhotoCommentFragment.this.m9023H2();
                if (PhotoCommentFragment.this.f8302d != null) {
                    PhotoCommentFragment.this.f8302d.setSelection(PhotoCommentFragment.this.f8308g.getCount() - 1);
                }
                PhotoCommentFragment.this.m9039V2((C3061a) pair.second);
                if (((C3061a) pair.second).m15784f().equals("CommentText")) {
                    C6566a.m25162u("Usage_Data_Msg");
                } else {
                    C6566a.m25162u("Usage_Data_File");
                }
            }
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(C3061a c3061a) {
            if (PhotoCommentFragment.this.getActivity() != null && c3061a.m15791m() == PhotoCommentFragment.this.f8298b.m15144p()) {
                c3061a.m15795q(1);
                C2950b0.m14920s().m15218g(c3061a, true);
                PhotoCommentFragment.this.m9037T2();
                if (PhotoCommentFragment.this.f8308g != null) {
                    PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$n */
    public class AsyncTaskC1610n extends AsyncTask<Void, Void, Pair<List<C3061a>, List<C3061a>>> {
        public AsyncTaskC1610n() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Pair<List<C3061a>, List<C3061a>> doInBackground(Void... voidArr) {
            List<C3061a> arrayList;
            List<C3061a> arrayList2;
            if (PhotoCommentFragment.this.f8298b != null) {
                arrayList = C2950b0.m14920s().m15226o(PhotoCommentFragment.this.f8298b.m15144p());
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                String strM15137i = PhotoCommentFragment.this.f8298b.m15137i();
                C2973l0.a aVarM15148t = PhotoCommentFragment.this.f8298b.m15148t();
                long time = (PhotoCommentFragment.this.f8298b.m15138j().getTime() == 0 ? PhotoCommentFragment.this.f8298b.m15143o() : PhotoCommentFragment.this.f8298b.m15138j()).getTime();
                if (!TextUtils.isEmpty(strM15137i)) {
                    arrayList.add(0, C3061a.m15780c(PhotoCommentFragment.this.f8298b.m15144p(), Long.parseLong(aVarM15148t.f13202f), strM15137i, time, time));
                }
                C2971k0 c2971k0M15104c = C2950b0.m14915n().m15104c(String.valueOf(PhotoCommentFragment.this.f8298b.m15144p()));
                if (c2971k0M15104c != null) {
                    arrayList.add(0, C3061a.m15779b(PhotoCommentFragment.this.f8298b.m15144p(), Long.parseLong(aVarM15148t.f13202f), c2971k0M15104c.m15111b(), time, time));
                }
                arrayList2 = C2950b0.m14920s().m15227p(PhotoCommentFragment.this.f8298b.m15144p());
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
            } else {
                arrayList = new ArrayList<>();
                arrayList2 = new ArrayList<>();
            }
            return Pair.create(arrayList, arrayList2);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Pair<List<C3061a>, List<C3061a>> pair) throws Throwable {
            List<C3061a> arrayList = (List) pair.first;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            List<C3061a> arrayList2 = (List) pair.second;
            if (arrayList2 == null) {
                arrayList2 = new ArrayList<>();
            }
            if (PhotoCommentFragment.this.f8308g != null) {
                PhotoCommentFragment.this.f8308g.m9106i();
                PhotoCommentFragment.this.f8308g.m9103f(arrayList);
                PhotoCommentFragment.this.f8308g.m9105h(arrayList2);
                PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
            }
            PhotoCommentFragment.this.m9037T2();
            ShowMediaActivity showMediaActivity = (ShowMediaActivity) PhotoCommentFragment.this.getActivity();
            if (showMediaActivity == null || showMediaActivity.m7367J0() || PhotoCommentFragment.this.f8298b == null) {
                return;
            }
            showMediaActivity.m10020y1(PhotoCommentFragment.this.f8298b.m15144p());
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$o */
    public class AsyncTaskC1611o extends AsyncTask<Void, Void, C2973l0> {

        /* renamed from: a */
        public final /* synthetic */ long f8414a;

        /* renamed from: b */
        public final /* synthetic */ String f8415b;

        public AsyncTaskC1611o(long j9, String str) {
            this.f8414a = j9;
            this.f8415b = str;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C2973l0 doInBackground(Void... voidArr) throws JSONException {
            if (PhotoCommentFragment.this.f8301c0 == null) {
                PhotoCommentFragment.this.f8301c0 = new FriendsClient();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("mediaId", String.valueOf(this.f8414a)));
            Pair<String, String> pairM15731j = PhotoCommentFragment.this.f8301c0.m15731j("media", "mediaInfo", arrayList);
            if ("200".equals(pairM15731j.first)) {
                Pair<C2973l0, List<String>> pairM20181c = C5172p.m20181c(this.f8415b, C5172p.m20196r((String) pairM15731j.second));
                if (pairM20181c.first != null) {
                    C2950b0.m14914m().m14699I(this.f8414a, (C2973l0) pairM20181c.first, (List) pairM20181c.second);
                }
            }
            if (PhotoCommentFragment.this.f8309g0.isCancelled()) {
                return null;
            }
            return C2950b0.m14914m().m14725v(this.f8414a);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(C2973l0 c2973l0) throws IllegalStateException {
            if (c2973l0 == null || PhotoCommentFragment.this.f8298b == null || PhotoCommentFragment.this.f8298b.m15144p() != c2973l0.m15144p()) {
                return;
            }
            PhotoCommentFragment.this.f8298b = c2973l0;
            PhotoCommentFragment.this.m9060t2();
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$p */
    public class C1612p implements AdapterView.OnItemLongClickListener {
        public C1612p() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            C3061a item;
            boolean z8;
            if (PhotoCommentFragment.this.getActivity() == null || (item = PhotoCommentFragment.this.f8308g.getItem(i9)) == null || (item.m15793o() && item.m15784f().equals("CommentMedia"))) {
                return false;
            }
            boolean zEquals = "CommentDoodle".equals(item.m15784f());
            boolean zEquals2 = "CommentMedia".equals(item.m15784f());
            boolean z9 = item.m15792n() == 1;
            PhotoCommentFragment.this.f8344y = new Dialog(PhotoCommentFragment.this.getActivity());
            PhotoCommentFragment.this.f8344y.requestWindowFeature(1);
            PhotoCommentFragment.this.f8344y.setContentView(R.layout.dialog_comment_long_click);
            if (item.m15786h() == Globals.m7388i0().m7568k1().longValue()) {
                View viewFindViewById = PhotoCommentFragment.this.f8344y.findViewById(R.id.DialogEditComment);
                if (zEquals || zEquals2 || z9 || item.m15793o()) {
                    z8 = false;
                } else {
                    viewFindViewById.setVisibility(0);
                    viewFindViewById.setOnClickListener(PhotoCommentFragment.this.f8282R0);
                    viewFindViewById.setTag(item);
                    z8 = true;
                }
                View viewFindViewById2 = PhotoCommentFragment.this.f8344y.findViewById(R.id.DialogRecallComment);
                if (!item.m15793o() && !z9) {
                    viewFindViewById2.setVisibility(0);
                    viewFindViewById2.setOnClickListener(PhotoCommentFragment.this.f8284S0);
                    viewFindViewById2.setTag(item);
                    z8 = true;
                }
                View viewFindViewById3 = PhotoCommentFragment.this.f8344y.findViewById(R.id.DialogResendComment);
                if (!zEquals && z9) {
                    viewFindViewById3.setVisibility(0);
                    viewFindViewById3.setOnClickListener(PhotoCommentFragment.this.f8286T0);
                    viewFindViewById3.setTag(item);
                    z8 = true;
                }
                View viewFindViewById4 = PhotoCommentFragment.this.f8344y.findViewById(R.id.DialogDeleteComment);
                if (!zEquals && z9) {
                    viewFindViewById4.setVisibility(0);
                    viewFindViewById4.setOnClickListener(PhotoCommentFragment.this.f8288U0);
                    viewFindViewById4.setTag(item);
                    z8 = true;
                }
            } else {
                z8 = false;
            }
            View viewFindViewById5 = PhotoCommentFragment.this.f8344y.findViewById(R.id.DialogCopyComment);
            if (zEquals || zEquals2) {
                viewFindViewById5.setVisibility(8);
            } else {
                viewFindViewById5.setVisibility(0);
                viewFindViewById5.setOnClickListener(PhotoCommentFragment.this.f8290V0);
                viewFindViewById5.setTag(item);
                z8 = true;
            }
            if (z8) {
                PhotoCommentFragment.this.f8344y.findViewById(R.id.DialogCancel).setOnClickListener(PhotoCommentFragment.this.f8292W0);
                PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
                photoCommentFragment.m9012A2(photoCommentFragment.f8344y);
                PhotoCommentFragment.this.f8344y.show();
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$q */
    public class ViewOnClickListenerC1613q implements View.OnClickListener {
        public ViewOnClickListenerC1613q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.f8344y != null) {
                PhotoCommentFragment.this.f8344y.dismiss();
            }
            if (!PhotoCommentFragment.this.f8311h0) {
                C5187v0.m20267c(R.string.error_server_response);
            } else {
                PhotoCommentFragment.this.m9026K2((C3061a) view.getTag());
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$r */
    public class ViewOnClickListenerC1614r implements View.OnClickListener {
        public ViewOnClickListenerC1614r() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m9150b(C3061a c3061a, String str, String str2, String str3, String str4) {
            if (!"200".equals(str3)) {
                Log.e("PhotoCommentFragment", "Delete Comment fail and statusCode = " + str3);
                return;
            }
            C2950b0.m14920s().m15221j(c3061a.m15783e());
            PhotoCommentFragment.this.f8298b.m15123J(PhotoCommentFragment.this.f8298b.m15150v() - 1);
            if ("CommentDoodle".equals(c3061a.m15784f())) {
                PhotoCommentFragment.this.f8298b.m15115B(PhotoCommentFragment.this.f8298b.m15133e() - 1);
            } else if ("CommentMedia".equals(c3061a.m15784f())) {
                PhotoCommentFragment.this.f8298b.m15116C(PhotoCommentFragment.this.f8298b.m15134f() - 1);
            } else if ("CommentText".equals(c3061a.m15784f())) {
                PhotoCommentFragment.this.f8298b.m15117D(PhotoCommentFragment.this.f8298b.m15135g() - 1);
            }
            C2950b0.m14914m().m14712i(PhotoCommentFragment.this.f8298b);
            PhotoCommentFragment.this.m9070z1(c3061a.m15783e());
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.f8344y != null) {
                PhotoCommentFragment.this.f8344y.dismiss();
            }
            if (!PhotoCommentFragment.this.f8311h0) {
                C5187v0.m20267c(R.string.error_server_response);
                return;
            }
            final C3061a c3061a = (C3061a) view.getTag();
            if (c3061a == null || c3061a.m15793o()) {
                return;
            }
            FriendsClient friendsClient = new FriendsClient();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("commentId", String.valueOf(c3061a.m15783e())));
            friendsClient.m15734m("media", "deleteComment", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.z8
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f12298a.m9150b(c3061a, str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$s */
    public class ViewOnClickListenerC1615s implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$s$a */
        public class a extends AsyncTask<Void, Void, Void> {

            /* renamed from: a */
            public final /* synthetic */ C3061a f8421a;

            public a(C3061a c3061a) {
                this.f8421a = c3061a;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                this.f8421a.m15795q(0);
                C2950b0.m14920s().m15230s(this.f8421a.m15783e(), this.f8421a, "UploadStatus");
                PhotoCommentFragment.this.m9041k1(this.f8421a);
                try {
                    if (this.f8421a.m15784f().equals("CommentMedia")) {
                        String str = this.f8421a.m15789k().f13818e;
                        String str2 = this.f8421a.m15789k().f13819f;
                        PhotoCommentFragment photoCommentFragment = PhotoCommentFragment.this;
                        photoCommentFragment.m9048n2(str, str2, this.f8421a, photoCommentFragment.f8278P0);
                    } else {
                        PhotoCommentFragment.m9006x2(this.f8421a.m15782d(), this.f8421a, PhotoCommentFragment.this.f8278P0);
                    }
                    return null;
                } catch (Exception e9) {
                    e9.printStackTrace();
                    return null;
                }
            }
        }

        public ViewOnClickListenerC1615s() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.f8344y != null) {
                PhotoCommentFragment.this.f8344y.dismiss();
            }
            if (!PhotoCommentFragment.this.f8311h0) {
                C5187v0.m20267c(R.string.error_server_response);
                return;
            }
            C3061a c3061a = (C3061a) view.getTag();
            if (c3061a == null || PhotoCommentFragment.this.f8308g == null) {
                return;
            }
            PhotoCommentFragment.this.f8308g.remove(c3061a);
            PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
            new a(c3061a).executeOnExecutor(C6385v.f21554b, new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$t */
    public class ViewOnClickListenerC1616t implements View.OnClickListener {
        public ViewOnClickListenerC1616t() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.f8344y != null) {
                PhotoCommentFragment.this.f8344y.dismiss();
            }
            C3061a c3061a = (C3061a) view.getTag();
            if (c3061a != null && c3061a.m15792n() == 1) {
                C2950b0.m14920s().m15221j(c3061a.m15783e());
                if (PhotoCommentFragment.this.f8308g != null) {
                    PhotoCommentFragment.this.f8308g.remove(c3061a);
                }
                PhotoCommentFragment.this.m9037T2();
                PhotoCommentFragment.this.f8308g.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$u */
    public class ViewOnClickListenerC1617u implements View.OnClickListener {
        public ViewOnClickListenerC1617u() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.f8344y != null) {
                PhotoCommentFragment.this.f8344y.dismiss();
            }
            C3061a c3061a = (C3061a) view.getTag();
            if (c3061a == null) {
                return;
            }
            C5145g.m20042a(c3061a.m15782d());
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$v */
    public class ViewOnClickListenerC1618v implements View.OnClickListener {
        public ViewOnClickListenerC1618v() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.getActivity() != null) {
                C6566a.m25159r("EnterGrid");
                Intent intent = new Intent();
                intent.putExtra("albumId", PhotoCommentFragment.this.f8298b.m15131c());
                PhotoCommentFragment.this.getActivity().setResult(-1, intent);
                PhotoCommentFragment.this.getActivity().finish();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$w */
    public class ViewOnClickListenerC1619w implements View.OnClickListener {
        public ViewOnClickListenerC1619w() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PhotoCommentFragment.this.f8344y != null) {
                PhotoCommentFragment.this.f8344y.dismiss();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$x */
    public static /* synthetic */ class C1620x {

        /* renamed from: a */
        public static final /* synthetic */ int[] f8427a;

        /* renamed from: b */
        public static final /* synthetic */ int[] f8428b;

        static {
            int[] iArr = new int[CommentStatus.values().length];
            f8428b = iArr;
            try {
                iArr[CommentStatus.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8428b[CommentStatus.EDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[UploadUtils.UploadResultType.values().length];
            f8427a = iArr2;
            try {
                iArr2[UploadUtils.UploadResultType.STEP_1_FAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8427a[UploadUtils.UploadResultType.STEP_2_VOICE_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8427a[UploadUtils.UploadResultType.STEP_2_SMALL_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8427a[UploadUtils.UploadResultType.STEP_3_VOICE_FAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8427a[UploadUtils.UploadResultType.STEP_3_SMALL_FAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8427a[UploadUtils.UploadResultType.STEP_3_BIG_FAIL.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$y */
    public class ViewOnClickListenerC1621y implements View.OnClickListener {
        public ViewOnClickListenerC1621y() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() != R.id.commentLastAvatar) {
                if (view.getId() == R.id.ImageCommentAvatar) {
                    PhotoCommentFragment.this.m9044l2((Friend) view.getTag(R.id.tag_User));
                }
            } else {
                if (PhotoCommentFragment.this.f8308g == null || PhotoCommentFragment.this.f8308g.getCount() <= 0) {
                    return;
                }
                String strValueOf = String.valueOf(PhotoCommentFragment.this.f8308g.getItem(PhotoCommentFragment.this.f8308g.getCount() - 1).m15786h());
                PhotoCommentFragment.this.m9044l2(PhotoCommentFragment.this.f8317k0.containsKey(strValueOf) ? (Friend) PhotoCommentFragment.this.f8317k0.get(strValueOf) : C2950b0.m14899A().m15003C(strValueOf));
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$z */
    public class C1622z implements TextWatcher {
        public C1622z() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            if (C5170o0.m20169d(charSequence.toString())) {
                PhotoCommentFragment.this.m9063v1(false);
            } else {
                PhotoCommentFragment.this.m9063v1(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F1 */
    public /* synthetic */ void m8885F1() {
        long jM14485J;
        if (((ShowMediaActivity) getActivity()).m10015n1() != null) {
            jM14485J = C2907m0.m14454I().m14485J(r0.f13723j) + C2907m0.m14454I().m14481E(r0.f13727n, true);
        } else {
            jM14485J = 0;
        }
        TextView textView = this.f8323n0;
        if (textView == null || this.f8265J || jM14485J == -1) {
            return;
        }
        if (jM14485J == 0) {
            textView.setVisibility(8);
            return;
        }
        if (jM14485J > 99) {
            textView.setText("N");
        } else {
            textView.setText(String.valueOf(jM14485J));
        }
        this.f8323n0.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G1 */
    public /* synthetic */ void m8888G1(C3061a c3061a) {
        C1597f0 c1597f0 = this.f8308g;
        if (c1597f0 != null) {
            c1597f0.m9104g(c3061a);
            this.f8308g.notifyDataSetChanged();
        }
        m9049o1();
        m9037T2();
        ListView listView = this.f8302d;
        if (listView != null) {
            listView.setSelection(this.f8308g.getCount() - 1);
        }
        ShowMediaActivity showMediaActivity = (ShowMediaActivity) getActivity();
        if (showMediaActivity != null) {
            showMediaActivity.m10007R0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H1 */
    public /* synthetic */ void m8891H1(Group group, String str, String str2, String str3, String str4) {
        if (str3 == null) {
            Log.d("PhotoCommentFragment", "[media.deleteMedia] Response is null");
            return;
        }
        if (!str3.equals("200")) {
            Log.d("PhotoCommentFragment", "[media.deleteMedia] statusCode=" + str3);
            return;
        }
        C2950b0.m14914m().m14716m(String.valueOf(this.f8298b.m15144p()));
        if (getActivity() != null) {
            if (group == null) {
                Log.d("PhotoCommentFragment", "Cannot sendDeleteMediaMessage by no group info");
                return;
            }
            String strM15131c = this.f8298b.m15131c();
            if (group.f13718e.equals(strM15131c)) {
                C2925v.m14634z0(group, strM15131c, group.f13717d, String.valueOf(1));
                return;
            }
            GroupAlbumObj groupAlbumObjM15056i = C2950b0.m14911j().m15056i(strM15131c);
            if (groupAlbumObjM15056i != null) {
                C2925v.m14634z0(group, groupAlbumObjM15056i.m14675b(), groupAlbumObjM15056i.m14676c(), String.valueOf(1));
            } else {
                Log.d("PhotoCommentFragment", "Cannot sendDeleteMediaMessage by no group album info");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I1 */
    public /* synthetic */ void m8894I1(Activity activity) {
        ProgressDialog progressDialog;
        if (activity.isFinishing() || (progressDialog = this.f8297a0) == null || !progressDialog.isShowing()) {
            return;
        }
        this.f8297a0.dismiss();
        this.f8297a0 = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J1 */
    public /* synthetic */ void m8897J1(C1601h0 c1601h0, String str) {
        Thread.currentThread().setName("Send Doodle Comment");
        C3061a c3061aM9065w1 = m9065w1(str, "CommentDoodle", new C3061a.a(0L, "", "", "", c1601h0.f8395b, c1601h0.f8396c, "Doodle"));
        C2950b0.m14920s().m15217f(c3061aM9065w1);
        m9041k1(c3061aM9065w1);
        m9046m2(c1601h0, c3061aM9065w1, this.f8278P0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K1 */
    public /* synthetic */ void m8900K1() {
        m9037T2();
        this.f8308g.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L1 */
    public /* synthetic */ void m8903L1(C3061a c3061a, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            try {
                C3061a c3061aM20183e = C5172p.m20183e(C5172p.m20195q(str4), this.f8298b.m15144p());
                c3061a.m15781a(c3061aM20183e);
                C2950b0.m14920s().m15217f(c3061aM20183e);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.p8
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f11038b.m8900K1();
                        }
                    });
                }
            } catch (Exception e9) {
                Log.d("PhotoCommentFragment", "Parse updated comment fail = " + e9.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M1 */
    public /* synthetic */ void m8905M1() {
        m9037T2();
        this.f8308g.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N1 */
    public /* synthetic */ void m8907N1(String str, C3061a c3061a, String str2, String str3, String str4, String str5) {
        if (str4 == null) {
            Log.d("PhotoCommentFragment", "[editTextNote]: statusCode == null");
            return;
        }
        if (!str4.equals("200")) {
            Log.d("PhotoCommentFragment", "[editTextNote]: statusCode=" + str4);
            return;
        }
        C2973l0 c2973l0M20188j = C5172p.m20188j(str, C5172p.m20195q(str5));
        if (c2973l0M20188j != null) {
            C2950b0.m14914m().m14712i(c2973l0M20188j);
            this.f8298b.m15119F(c2973l0M20188j.m15137i());
            this.f8298b.m15122I(c2973l0M20188j.m15143o());
        }
        String strM15137i = this.f8298b.m15137i();
        C2973l0.a aVarM15148t = this.f8298b.m15148t();
        if (strM15137i == null || strM15137i.isEmpty()) {
            return;
        }
        long time = (this.f8298b.m15138j().getTime() == 0 ? this.f8298b.m15143o() : this.f8298b.m15138j()).getTime();
        c3061a.m15781a(C3061a.m15780c(this.f8298b.m15144p(), Long.parseLong(aVarM15148t.f13202f), strM15137i, time, time));
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.o8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11005b.m8905M1();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O1 */
    public /* synthetic */ void m8910O1(long j9, Activity activity) throws Throwable {
        C2973l0 c2973l0;
        C3061a c3061aM9108k = this.f8308g.m9108k(j9);
        if (c3061aM9108k != null) {
            this.f8308g.remove(c3061aM9108k);
            m9037T2();
            this.f8308g.notifyDataSetChanged();
            if ("CommentDoodle".equals(c3061aM9108k.m15784f()) && (activity instanceof ShowMediaActivity) && (c2973l0 = this.f8298b) != null) {
                ((ShowMediaActivity) activity).m10020y1(c2973l0.m15144p());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P1 */
    public /* synthetic */ void m8913P1(View view) {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q1 */
    public /* synthetic */ void m8916Q1(View view) throws IllegalStateException {
        m9029M2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R1 */
    public /* synthetic */ void m8919R1(boolean z8) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.b8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f9742b.m9038U2();
                }
            });
        }
    }

    /* renamed from: S1 */
    public static /* synthetic */ void m8922S1(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T1 */
    public /* synthetic */ void m8925T1(View view) {
        if (view == this.f8255E) {
            m9027L2();
        } else if (view == this.f8342x && this.f8302d.getVisibility() == 8) {
            m9025J2();
        }
        m9023H2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U1 */
    public /* synthetic */ void m8928U1(View view) {
        m9031N2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V1 */
    public /* synthetic */ void m8931V1(View view) {
        m9055r1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W1 */
    public /* synthetic */ void m8934W1(View view) {
        C3061a c3061a = (C3061a) view.getTag();
        if (!this.f8311h0) {
            C5187v0.m20267c(R.string.error_server_response);
        } else {
            if (c3061a.m15784f().equals("CommentMedia")) {
                return;
            }
            if (c3061a.m15793o()) {
                m9061u1(c3061a);
            } else {
                m9059t1(c3061a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X1 */
    public /* synthetic */ void m8937X1(boolean z8) {
        if (z8) {
            m9030N(true);
        }
    }

    /* renamed from: Y1 */
    public static /* synthetic */ void m8940Y1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z1 */
    public /* synthetic */ void m8943Z1(String str) {
        try {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(getActivity());
            builderM16382a.setMessage(str);
            builderM16382a.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.j8
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    PhotoCommentFragment.m8940Y1(dialogInterface, i9);
                }
            });
            AlertDialog alertDialogCreate = builderM16382a.create();
            alertDialogCreate.requestWindowFeature(1);
            alertDialogCreate.show();
            ((TextView) alertDialogCreate.findViewById(android.R.id.message)).setGravity(17);
        } catch (Exception e9) {
            Log.d("PhotoCommentFragment", "[onActivityResult] Create AlertDialog fail = " + e9.getMessage());
            C5187v0.m20268d(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a2 */
    public /* synthetic */ void m8946a2(View view) {
        CLUtility.m16589t1(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b2 */
    public /* synthetic */ boolean m8949b2(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f8303d0;
        if (c4619d == null) {
            return false;
        }
        return c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c2 */
    public /* synthetic */ void m8952c2() {
        if (this.f8281R) {
            m9032O2();
        } else {
            m9015C1();
        }
    }

    /* renamed from: d2 */
    public static /* synthetic */ void m8955d2(C3061a c3061a, AbstractC6381r abstractC6381r, FriendsClient friendsClient, String str, String str2, String str3, String str4) {
        try {
            try {
                if ("200".equals(str3)) {
                    C3061a c3061aM20183e = C5172p.m20183e(C5172p.m20195q(str4), c3061a.m15791m());
                    C2950b0.m14920s().m15221j(c3061a.m15783e());
                    C2950b0.m14920s().m15217f(c3061aM20183e);
                    abstractC6381r.m24506d(new Pair(c3061a, c3061aM20183e));
                } else {
                    c3061a.m15795q(1);
                    C2950b0.m14920s().m15230s(c3061a.m15783e(), c3061a, "UploadStatus");
                    abstractC6381r.m24508f(c3061a);
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        } finally {
            friendsClient.m15717U0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e2 */
    public /* synthetic */ void m8958e2(C3061a c3061a, View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        long j9 = this.f8291W;
        if (j9 != -1 && j9 == c3061a.m15783e()) {
            m9035R2();
            return;
        }
        C3061a.a aVarM15789k = c3061a.m15789k();
        if (aVarM15789k == null) {
            Log.d("PhotoCommentFragment", "[mLastCommentVoiceView.onClick] mediaComment is null.");
            return;
        }
        String str = aVarM15789k.f13818e;
        f8246Y0 = str;
        if (str != null && new File(f8246Y0).exists()) {
            m9034Q2(c3061a);
            return;
        }
        c3061a.f13813l = true;
        this.f8320m.setVisibility(0);
        m9057s1(c3061a);
        aVarM15789k.f13818e = f8246Y0;
        m9040W2(c3061a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f2 */
    public /* synthetic */ void m8961f2(Friend friend) {
        C6127a.m23469j(getActivity(), this.f8312i, friend);
        this.f8316k.setText(friend.m15621b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g2 */
    public /* synthetic */ void m8965g2(String str, String str2, String str3, String str4, String str5) {
        if (str4 == null) {
            Log.d("PhotoCommentFragment", "Response is null");
            return;
        }
        if (!str4.equals("200")) {
            Log.d("PhotoCommentFragment", "statusCode=" + str4);
            return;
        }
        final Friend friendM20184f = C5172p.m20184f(C5172p.m20195q(str5));
        C2950b0.m14899A().m15018j(friendM20184f, false);
        if (!this.f8317k0.containsKey(str)) {
            this.f8317k0.put(str, friendM20184f);
        }
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.q8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11076b.m8961f2(friendM20184f);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h2 */
    public /* synthetic */ void m8969h2() {
        ListView listView = this.f8302d;
        listView.setSelection(listView.getLastVisiblePosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i2 */
    public /* synthetic */ void m8973i2() {
        C1597f0 c1597f0;
        if (this.f8302d.getVisibility() == 0 && (c1597f0 = this.f8308g) != null) {
            c1597f0.notifyDataSetChanged();
        }
        if (this.f8308g.m9109l(this.f8291W) == this.f8308g.getCount() - 1) {
            this.f8332s.setImageResource(R.drawable.icon_stop_s);
            this.f8320m.setVisibility(8);
            this.f8293X.m9121d(this.f8334t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j2 */
    public /* synthetic */ void m8977j2(C3061a c3061a) {
        C1597f0 c1597f0;
        if (this.f8302d.getVisibility() == 0 && (c1597f0 = this.f8308g) != null) {
            c1597f0.notifyDataSetChanged();
        }
        if (this.f8308g.m9110m(c3061a) == this.f8308g.getCount() - 1) {
            this.f8332s.setImageResource(R.drawable.icon_play_s);
            this.f8334t.setText(CLUtility.m16531f(c3061a.m15789k().f13819f));
        }
    }

    /* renamed from: x2 */
    public static void m9006x2(String str, final C3061a c3061a, final AbstractC6381r<Pair<C3061a, C3061a>, C3061a> abstractC6381r) {
        ArrayList arrayList;
        if (C5179r0.m20246a()) {
            return;
        }
        String strValueOf = String.valueOf(c3061a.m15791m());
        String strM7449L = Globals.m7388i0().m7449L();
        String strM15784f = c3061a.m15784f();
        arrayList = new ArrayList();
        arrayList.add(new C6301o("token", strM7449L));
        arrayList.add(new C6301o("mediaId", strValueOf));
        arrayList.add(new C6301o("commentType", strM15784f));
        strM15784f.hashCode();
        switch (strM15784f) {
            case "CommentDoodle":
                arrayList.add(new C6301o("mediaComment", str));
                if (!C5170o0.m20169d(c3061a.m15782d())) {
                    arrayList.add(new C6301o("comment", c3061a.m15782d()));
                    break;
                }
                break;
            case "CommentText":
                arrayList.add(new C6301o("comment", str));
                break;
            case "CommentMedia":
                arrayList.add(new C6301o("mediaComment", str));
                break;
            default:
                return;
        }
        final FriendsClient friendsClient = new FriendsClient();
        friendsClient.m15734m("media", "createComment", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.a8
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                PhotoCommentFragment.m8955d2(c3061a, abstractC6381r, friendsClient, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: A1 */
    public final void m9011A1(String str, String str2) {
        if (getActivity() == null) {
            return;
        }
        AsyncTaskC1607k0 asyncTaskC1607k0 = new AsyncTaskC1607k0(this, null);
        this.f8321m0 = asyncTaskC1607k0;
        asyncTaskC1607k0.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, str, str2);
    }

    /* renamed from: A2 */
    public final void m9012A2(Dialog dialog) {
        if (getActivity() == null) {
            return;
        }
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        dialog.getWindow().getAttributes().width = (int) (930 * (r0.widthPixels / 1080.0f));
    }

    /* renamed from: B1 */
    public final void m9013B1() {
        CLUtility.m16589t1(getActivity());
        getView().setBackgroundColor(Color.parseColor("#00000000"));
        if (this.f8338v.getVisibility() == 0) {
            if (this.f8302d.getCount() == 0) {
                this.f8302d.setVisibility(8);
            }
            if (m9042k2()) {
                this.f8336u.setVisibility(0);
                this.f8338v.setVisibility(8);
            }
        } else {
            this.f8302d.setVisibility(8);
            if (this.f8302d.getCount() > 0 && !this.f8265J) {
                this.f8304e.setVisibility(0);
            }
        }
        this.f8325o0 = -1;
    }

    /* renamed from: B2 */
    public void m9014B2(long j9, long j10, String str) {
        this.f8285T = true;
        this.f8273N = j9;
        this.f8271M = j10;
        this.f8275O = str;
    }

    /* renamed from: C1 */
    public void m9015C1() {
        if (this.f8346z.isAdded() && getActivity() != null) {
            getActivity().setRequestedOrientation(-1);
            Group groupM10015n1 = ((ShowMediaActivity) getActivity()).m10015n1();
            if (groupM10015n1 == null || !groupM10015n1.f13711J) {
                this.f8336u.setVisibility(0);
            } else {
                this.f8336u.setVisibility(8);
            }
            this.f8281R = false;
            m9067x1().mo1799n(this.f8346z).mo1794h();
            m9024I2();
        }
    }

    /* renamed from: C2 */
    public void m9016C2(Boolean bool) {
        this.f8267K = bool.booleanValue();
    }

    /* renamed from: D1 */
    public final void m9017D1() {
        VoiceRecordFragment voiceRecordFragment = new VoiceRecordFragment();
        this.f8346z = voiceRecordFragment;
        voiceRecordFragment.m11003X(this.f8260G0);
        m9043l1();
    }

    /* renamed from: D2 */
    public void m9018D2(boolean z8) {
        this.f8327p0 = z8;
    }

    /* renamed from: E1 */
    public boolean m9019E1() {
        return this.f8281R;
    }

    /* renamed from: E2 */
    public final void m9020E2(final C3061a c3061a) {
        String strValueOf = String.valueOf(c3061a.m15786h());
        Friend friendM15003C = this.f8317k0.containsKey(strValueOf) ? this.f8317k0.get(strValueOf) : C2950b0.m14899A().m15003C(strValueOf);
        if (friendM15003C == null) {
            m9021F2(this.f8316k, this.f8312i, String.valueOf(c3061a.m15786h()));
        } else {
            UserInfo userInfo = this.f8259G;
            if (userInfo == null || friendM15003C.f13645c != userInfo.f13777b) {
                this.f8316k.setText(friendM15003C.m15621b());
            } else {
                this.f8316k.setText(userInfo.f13778c);
            }
            C6127a.m23469j(getActivity(), this.f8312i, friendM15003C);
        }
        if (c3061a.f13813l) {
            this.f8320m.setVisibility(0);
        } else {
            this.f8320m.setVisibility(8);
        }
        this.f8318l.setTypeface(Typeface.DEFAULT, 0);
        if (c3061a.m15784f().equals("CommentMedia")) {
            this.f8318l.setVisibility(8);
            this.f8314j.setVisibility(8);
            this.f8330r.setVisibility(0);
            this.f8332s.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.v8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
                    this.f11813b.m8958e2(c3061a, view);
                }
            });
            if (c3061a.m15789k() != null) {
                this.f8334t.setText(CLUtility.m16531f(c3061a.m15789k().f13819f));
            }
        } else {
            String strM15782d = c3061a.m15782d();
            if (c3061a.m15784f().equals("CommentDoodle")) {
                this.f8314j.setVisibility(0);
                this.f8314j.setImageResource(C6152i.m23610b(c3061a.m15789k().f13819f, true));
                if (strM15782d == null || strM15782d.isEmpty()) {
                    strM15782d = Globals.m7388i0().getApplicationContext().getString(R.string.doodle_comment_default_string) + StringUtils.SPACE;
                    TextView textView = this.f8318l;
                    textView.setTypeface(textView.getTypeface(), 2);
                }
            } else {
                this.f8314j.setVisibility(8);
            }
            this.f8330r.setVisibility(8);
            this.f8318l.setVisibility(0);
            this.f8318l.setText(strM15782d);
            CLUtility.m16543i(this.f8318l);
        }
        if (c3061a.m15792n() == 1) {
            this.f8322n.setVisibility(8);
            this.f8310h.setVisibility(0);
            this.f8328q.setText("");
            this.f8328q.setVisibility(8);
            return;
        }
        if (c3061a.m15792n() != 2) {
            this.f8322n.setVisibility(0);
            this.f8310h.setVisibility(8);
            this.f8328q.setText("");
            this.f8328q.setVisibility(8);
            return;
        }
        this.f8322n.setVisibility(8);
        this.f8310h.setVisibility(8);
        if (c3061a.m15788j() != null) {
            this.f8328q.setText(CLUtility.m16434E2(c3061a.m15788j()));
        } else {
            this.f8328q.setText("");
        }
        this.f8328q.setVisibility(0);
    }

    /* renamed from: F2 */
    public final void m9021F2(TextView textView, ImageView imageView, final String str) {
        textView.setText("");
        imageView.setImageResource(R.drawable.pic_default);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", str));
        new FriendsClient().m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.f8
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f10432a.m8965g2(str, str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: G2 */
    public void m9022G2(String str) {
        this.f8269L = str;
    }

    /* renamed from: H2 */
    public final void m9023H2() {
        m9015C1();
        if (this.f8265J) {
            return;
        }
        this.f8302d.setVisibility(0);
        this.f8304e.setVisibility(8);
    }

    /* renamed from: I2 */
    public final void m9024I2() {
        if (getFragmentManager() != null) {
            getFragmentManager().mo1844a().mo1802r(this.f8247A).mo1794h();
        }
    }

    /* renamed from: J2 */
    public final void m9025J2() {
        C3061a c3061aM9072a;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.f8342x.requestFocus();
        CLUtility.m16606x2(activity);
        this.f8289V.postDelayed(new Runnable() { // from class: com.cyberlink.you.activity.i8
            @Override // java.lang.Runnable
            public final void run() {
                this.f10748b.m8969h2();
            }
        }, 500L);
        int i9 = C1620x.f8428b[this.f8294X0.ordinal()];
        if (i9 == 1) {
            this.f8340w.setOnClickListener(this.f8250B0);
            this.f8340w.setText(R.string.send_btn);
        } else if (i9 == 2 && (c3061aM9072a = this.f8294X0.m9072a()) != null) {
            this.f8340w.setOnClickListener(this.f8252C0);
            this.f8340w.setTag(c3061aM9072a);
            this.f8340w.setText(R.string.photo_comment_done);
            this.f8342x.setText(c3061aM9072a.m15782d());
            EditText editText = this.f8342x;
            editText.setSelection(editText.getText().length());
        }
        this.f8338v.setVisibility(0);
        this.f8336u.setVisibility(8);
    }

    /* renamed from: K2 */
    public final void m9026K2(C3061a c3061a) {
        CommentStatus commentStatus = CommentStatus.EDIT;
        this.f8294X0 = commentStatus;
        commentStatus.m9073b(c3061a);
        m9025J2();
    }

    /* renamed from: L2 */
    public final void m9027L2() {
        this.f8294X0 = CommentStatus.NEW;
        m9025J2();
    }

    /* renamed from: M */
    public final void m9028M(long j9) {
        Log.d("PhotoCommentFragment", "[QueryCommentAsync] IN, isOpenFromMeeting:" + this.f8327p0);
        if (this.f8327p0) {
            return;
        }
        try {
            if (this.f8301c0 == null) {
                this.f8301c0 = new FriendsClient();
            }
            C1603i0 c1603i0 = this.f8307f0;
            if (c1603i0 != null) {
                c1603i0.m24447a();
            }
            C1603i0 c1603i02 = new C1603i0(j9);
            this.f8307f0 = c1603i02;
            this.f8305e0.execute(c1603i02);
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        Log.d("PhotoCommentFragment", "[QueryCommentAsync] OUT");
    }

    /* renamed from: M2 */
    public final void m9029M2() throws IllegalStateException {
        if (this.f8261H != null) {
            m9035R2();
        }
        if (this.f8302d.getVisibility() == 8) {
            m9023H2();
        } else {
            m9013B1();
        }
    }

    /* renamed from: N */
    public final void m9030N(boolean z8) {
        if (z8) {
            C2907m0.m14454I().m14495W(this.f8256E0);
        }
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.n8
            @Override // java.lang.Runnable
            public final void run() {
                this.f10921b.m8885F1();
            }
        });
    }

    /* renamed from: N2 */
    public final void m9031N2() {
        if (this.f8281R) {
            m9015C1();
        } else {
            m9032O2();
        }
    }

    /* renamed from: O2 */
    public final void m9032O2() {
        if (this.f8346z.isAdded() && getActivity() != null) {
            getActivity().setRequestedOrientation(1);
            this.f8336u.setVisibility(8);
            this.f8281R = true;
            m9067x1().mo1802r(this.f8346z).mo1794h();
        }
    }

    /* renamed from: P2 */
    public final void m9033P2(UploadUtils.UploadResultType uploadResultType, C3061a c3061a, AbstractC6381r<Pair<C3061a, C3061a>, C3061a> abstractC6381r) {
        int i9 = C1620x.f8427a[uploadResultType.ordinal()];
        if (i9 == 1) {
            C5187v0.m20267c(R.string.error_server_response);
            abstractC6381r.m24508f(c3061a);
        } else {
            if (i9 != 5) {
                return;
            }
            C5187v0.m20267c(R.string.error_upload);
            abstractC6381r.m24508f(c3061a);
        }
    }

    /* renamed from: Q2 */
    public final void m9034Q2(C3061a c3061a) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        m9035R2();
        C5140e0 c5140e0 = new C5140e0();
        this.f8261H = c5140e0;
        c5140e0.m20021m(new C1602i());
        this.f8291W = c3061a.m15783e();
        this.f8261H.m20018j(f8246Y0);
        C1599g0 c1599g0 = new C1599g0(this, null);
        this.f8293X = c1599g0;
        this.f8295Y.schedule(c1599g0, 0L, 1000L);
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.h8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10717b.m8973i2();
                }
            });
        }
    }

    /* renamed from: R2 */
    public final void m9035R2() throws IllegalStateException {
        C1599g0 c1599g0;
        final C3061a c3061aM9108k = this.f8308g.m9108k(this.f8291W);
        this.f8291W = -1L;
        if (this.f8261H == null || (c1599g0 = this.f8293X) == null || c3061aM9108k == null) {
            return;
        }
        c1599g0.cancel();
        this.f8261H.m20027s();
        this.f8293X = null;
        this.f8261H = null;
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.u8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f11430b.m8977j2(c3061aM9108k);
                }
            });
        }
    }

    /* renamed from: S2 */
    public void m9036S2() {
        if (C2907m0.m14454I().m14489N()) {
            m9030N(false);
        } else {
            C2907m0.m14454I().m14511u(this.f8256E0);
        }
    }

    /* renamed from: T2 */
    public final void m9037T2() {
        C1597f0 c1597f0 = this.f8308g;
        if (c1597f0 == null || c1597f0.getCount() <= 0) {
            m9047n1();
        } else {
            C1597f0 c1597f02 = this.f8308g;
            m9020E2(c1597f02.getItem(c1597f02.getCount() - 1));
        }
        C1597f0 c1597f03 = this.f8308g;
        if (c1597f03 != null) {
            m9071z2(c1597f03.getCount());
        }
        C1597f0 c1597f04 = this.f8308g;
        if (c1597f04 == null || c1597f04.getCount() <= 1) {
            View view = this.f8324o;
            if (view != null && view.getVisibility() == 0) {
                this.f8324o.setVisibility(8);
            }
        } else {
            View view2 = this.f8324o;
            if (view2 != null && view2.getVisibility() != 0) {
                this.f8324o.setVisibility(0);
            }
        }
        C1597f0 c1597f05 = this.f8308g;
        if (c1597f05 == null || c1597f05.getCount() <= 0 || this.f8302d.getVisibility() == 0 || this.f8265J) {
            this.f8304e.setVisibility(8);
        } else {
            this.f8304e.setVisibility(0);
        }
    }

    /* renamed from: U2 */
    public final void m9038U2() {
        if (getActivity() == null) {
            return;
        }
        boolean zM14204A0 = XMPPManager.m14184g0().m14204A0();
        if (!zM14204A0 && C6456d.m24714D().m24748G() && !Globals.m7388i0().m7534d2()) {
            zM14204A0 = true;
        }
        this.f8311h0 = zM14204A0;
        this.f8249B.setVisibility(zM14204A0 ? 8 : 0);
    }

    /* renamed from: V2 */
    public final void m9039V2(C3061a c3061a) throws Throwable {
        ShowMediaActivity showMediaActivity;
        if (!c3061a.m15784f().equals("CommentDoodle") || (showMediaActivity = (ShowMediaActivity) getActivity()) == null || showMediaActivity.m7367J0()) {
            return;
        }
        showMediaActivity.m10008S0();
    }

    /* renamed from: W2 */
    public final void m9040W2(C3061a c3061a) {
        if (!c3061a.m15793o()) {
            C2950b0.m14920s().m15219h(c3061a, true, false);
        } else {
            C2950b0.m14915n().m15108g(new C2971k0(String.valueOf(c3061a.m15791m()), c3061a.m15789k()), false);
        }
    }

    /* renamed from: k1 */
    public final void m9041k1(final C3061a c3061a) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.e8
            @Override // java.lang.Runnable
            public final void run() {
                this.f10397b.m8888G1(c3061a);
            }
        });
    }

    /* renamed from: k2 */
    public final boolean m9042k2() {
        return C6383t.m24517f(this.f8342x.getText());
    }

    /* renamed from: l1 */
    public final void m9043l1() {
        m9067x1().m1981c(R.id.voiceFragmentContainer, this.f8346z, "voiceFragment").mo1799n(this.f8346z).mo1794h();
    }

    /* renamed from: l2 */
    public final void m9044l2(Friend friend) {
        if (friend == null || getActivity() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("friendObj", friend);
        bundle.putString("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
        Intent intent = new Intent();
        intent.setClass(getActivity(), FriendProfileActivity.class);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, 1);
    }

    /* renamed from: m1 */
    public final void m9045m1(C2925v.d dVar) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        C5287b.m20583f(permission, new C1589b0(dVar, permission), getActivity());
    }

    /* renamed from: m2 */
    public final void m9046m2(C1601h0 c1601h0, C3061a c3061a, AbstractC6381r<Pair<C3061a, C3061a>, C3061a> abstractC6381r) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(this.f8269L, new UploadMediaHelper.C3184u(c1601h0.f8395b, CLUtility.m16489S1(FilenameUtils.getBaseName(c1601h0.f8395b), c1601h0.f8394a, null), c1601h0.f8396c));
        uploadMediaHelper.m16815R1(new C1586a(c3061a, abstractC6381r));
        uploadMediaHelper.m16819T1();
    }

    /* renamed from: n1 */
    public final void m9047n1() {
        ImageView imageView = this.f8312i;
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
        TextView textView = this.f8316k;
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = this.f8318l;
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = this.f8328q;
        if (textView3 != null) {
            textView3.setText("");
        }
    }

    /* renamed from: n2 */
    public final void m9048n2(String str, String str2, C3061a c3061a, AbstractC6381r<Pair<C3061a, C3061a>, C3061a> abstractC6381r) {
        UploadMediaHelper uploadMediaHelper = new UploadMediaHelper(this.f8269L, new AudioItem(str, str2));
        uploadMediaHelper.m16815R1(new C1595e0(c3061a, abstractC6381r));
        uploadMediaHelper.m16819T1();
    }

    /* renamed from: o1 */
    public boolean m9049o1() {
        if (getActivity() != null) {
            CLUtility.m16589t1(getActivity());
        }
        this.f8329q0.m9126b();
        if (getActivity() != null) {
            ((ShowMediaActivity) getActivity()).m10006C1(null);
        }
        if (this.f8338v.getVisibility() == 0) {
            m9013B1();
            this.f8338v.setVisibility(8);
            this.f8336u.setVisibility(0);
            return true;
        }
        if (this.f8346z.isVisible()) {
            m9015C1();
            this.f8336u.setVisibility(0);
            return true;
        }
        this.f8340w.setOnClickListener(null);
        this.f8342x.getText().clear();
        return false;
    }

    /* renamed from: o2 */
    public void m9050o2(boolean z8) {
        this.f8296Z = z8;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        FragmentActivity activity;
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0) {
            if (i10 == -1) {
                final String string = getActivity().getString(R.string.the_photo_was_saved_successfully);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.r7
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f11136b.m8943Z1(string);
                        }
                    });
                    return;
                }
                return;
            }
            return;
        }
        if (i9 == 2) {
            if (i10 == -1) {
                C5187v0.m20267c(R.string.the_photo_was_saved_successfully);
                return;
            }
            return;
        }
        if (i9 != 3) {
            return;
        }
        if (i10 != -1) {
            if (i10 != 0 || (activity = getActivity()) == null) {
                return;
            }
            activity.runOnUiThread(new RunnableC1925c8(this));
            return;
        }
        this.f8329q0.f8394a = intent.getExtras().getString("handDrawImg");
        this.f8329q0.f8395b = intent.getExtras().getString("doodleImg");
        this.f8329q0.f8396c = intent.getExtras().getString(TtmlNode.ATTR_TTS_COLOR);
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            ((ShowMediaActivity) activity2).m10006C1(this.f8329q0.f8394a);
        }
        if (intent.getExtras().getBoolean("start_import")) {
            m9055r1();
            return;
        }
        if (activity2 != null) {
            if (this.f8338v.getVisibility() == 0) {
                CLUtility.m16606x2(getActivity());
            } else {
                m9023H2();
                m9027L2();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8303d0 = new C4619d(activity, new C1606k());
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        ProgressDialog progressDialog;
        super.onConfigurationChanged(configuration);
        int i9 = configuration.orientation;
        if ((i9 == 2 || i9 == 1) && !getActivity().isFinishing() && (progressDialog = this.f8297a0) != null && progressDialog.isShowing()) {
            this.f8297a0.dismiss();
            if (getActivity() != null) {
                this.f8297a0 = ProgressDialog.show(getActivity(), "", getActivity().getResources().getString(R.string.uploading), true);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        C5321e.m20824o().m20875k(this.f8264I0);
        XMPPManager.m14184g0().m14207H(this.f8266J0);
        XMPPManager.m14184g0().m14211K(this.f8270L0);
        C6456d.m24714D().m24744B(this.f8268K0);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws IllegalStateException {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_photo_comment, viewGroup, false);
        Bundle arguments = getArguments();
        this.f8283S = false;
        Group groupM10015n1 = getActivity() == null ? null : ((ShowMediaActivity) getActivity()).m10015n1();
        if (groupM10015n1 != null && !groupM10015n1.f13738y.equals("General")) {
            this.f8283S = true;
        }
        this.f8300c = (TextView) viewInflate.findViewById(R.id.commentStringText);
        View viewFindViewById = viewInflate.findViewById(R.id.commentLastLayout);
        this.f8304e = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f8337u0);
        this.f8302d = (ListView) viewInflate.findViewById(R.id.commentListView);
        this.f8320m = viewInflate.findViewById(R.id.downloadProgressbar);
        TextView textView = (TextView) viewInflate.findViewById(R.id.noConnectionText);
        this.f8249B = textView;
        textView.setOnClickListener(this.f8339v0);
        View viewFindViewById2 = viewInflate.findViewById(R.id.toolbarLayout);
        this.f8306f = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.s8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11167b.m8946a2(view);
            }
        });
        View viewFindViewById3 = viewInflate.findViewById(R.id.commentCountLayout);
        this.f8326p = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f8337u0);
        if (getActivity() != null) {
            this.f8308g = new C1597f0(getActivity(), R.layout.view_item_media_comment, new ArrayList());
        }
        this.f8302d.setAdapter((ListAdapter) this.f8308g);
        this.f8302d.setOnItemLongClickListener(this.f8280Q0);
        this.f8302d.setOnScrollListener(this.f8274N0);
        this.f8302d.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyberlink.you.activity.t8
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f11393b.m8949b2(view, motionEvent);
            }
        });
        this.f8336u = viewInflate.findViewById(R.id.toollayout);
        this.f8338v = viewInflate.findViewById(R.id.commentEditLayout);
        EditText editText = (EditText) viewInflate.findViewById(R.id.input);
        this.f8342x = editText;
        editText.addTextChangedListener(this.f8345y0);
        this.f8251C = (ImageView) viewInflate.findViewById(R.id.voiceRecordImage);
        this.f8253D = (ImageView) viewInflate.findViewById(R.id.handDrawEditImage);
        this.f8255E = (TextView) viewInflate.findViewById(R.id.photoinput);
        this.f8330r = viewInflate.findViewById(R.id.ImageCommentVoiceArea);
        this.f8332s = (ImageView) viewInflate.findViewById(R.id.ImageCommentVoiceOpBtn);
        this.f8334t = (TextView) viewInflate.findViewById(R.id.ImageVoiceTime);
        this.f8310h = (ImageView) viewInflate.findViewById(R.id.errorIcon);
        this.f8312i = (ImageView) viewInflate.findViewById(R.id.commentLastAvatar);
        this.f8314j = (ImageView) viewInflate.findViewById(R.id.commentLastPenView);
        this.f8316k = (TextView) viewInflate.findViewById(R.id.commentLastName);
        this.f8318l = (TextView) viewInflate.findViewById(R.id.commentLastText);
        this.f8328q = (TextView) viewInflate.findViewById(R.id.commentLastTime);
        this.f8324o = viewInflate.findViewById(R.id.commentSeeMoreArea);
        this.f8312i.setOnClickListener(this.f8341w0);
        this.f8322n = viewInflate.findViewById(R.id.commentSendingProgressbar);
        this.f8323n0 = (TextView) viewInflate.findViewById(R.id.unreadCnt);
        this.f8340w = (Button) viewInflate.findViewById(R.id.post);
        this.f8257F = (ImageView) viewInflate.findViewById(R.id.commentMoreArea);
        if (this.f8296Z) {
            View viewFindViewById4 = viewInflate.findViewById(R.id.commentGotoAllPhotos);
            viewFindViewById4.setVisibility(0);
            viewFindViewById4.setOnClickListener(this.f8335t0);
            viewInflate.findViewById(R.id.back).setVisibility(0);
            viewInflate.findViewById(R.id.back).setOnClickListener(this.f8333s0);
        } else {
            viewInflate.findViewById(R.id.back).setVisibility(0);
            viewInflate.findViewById(R.id.commentGotoAllPhotos).setVisibility(8);
            viewInflate.findViewById(R.id.back).setOnClickListener(this.f8333s0);
        }
        if (this.f8283S) {
            this.f8255E.setVisibility(8);
            this.f8251C.setVisibility(8);
            this.f8253D.setVisibility(8);
        } else {
            this.f8255E.setVisibility(0);
            this.f8255E.setOnClickListener(this.f8343x0);
            this.f8342x.setOnClickListener(this.f8343x0);
            this.f8251C.setVisibility(0);
            this.f8251C.setOnClickListener(this.f8248A0);
            this.f8253D.setVisibility(0);
            this.f8253D.setOnClickListener(this.f8347z0);
        }
        this.f8257F.setVisibility(0);
        this.f8257F.setOnClickListener(this.f8258F0);
        if (getActivity() != null) {
            this.f8259G = CLUtility.m16497V0(getActivity());
        } else {
            this.f8259G = null;
        }
        C5169o.m20161f(this.f8318l);
        if (bundle == null) {
            m9017D1();
        } else {
            VoiceRecordFragment voiceRecordFragment = (VoiceRecordFragment) getChildFragmentManager().mo1848e("voiceFragment");
            this.f8346z = voiceRecordFragment;
            if (voiceRecordFragment == null) {
                m9017D1();
            } else {
                voiceRecordFragment.m11003X(this.f8260G0);
            }
        }
        viewInflate.findViewById(R.id.btn_drawtool_pen).setOnClickListener(this.f8347z0);
        if (this.f8263I) {
            this.f8263I = false;
            m9060t2();
        }
        if (arguments != null) {
            boolean z8 = arguments.getBoolean("bulletinMode", false);
            this.f8265J = z8;
            if (z8) {
                this.f8306f.setVisibility(8);
                viewInflate.findViewById(R.id.dividerline).setVisibility(8);
                this.f8302d.setVisibility(8);
                this.f8304e.setVisibility(8);
                this.f8255E.setVisibility(8);
                this.f8251C.setVisibility(8);
                this.f8253D.setVisibility(8);
                this.f8323n0.setVisibility(8);
            }
        }
        if (groupM10015n1 != null && (groupM10015n1.f13711J || groupM10015n1.f13712K)) {
            this.f8306f.setVisibility(8);
            View viewFindViewById5 = viewInflate.findViewById(R.id.commentGotoAllPhotos);
            if (viewFindViewById5 != null) {
                viewFindViewById5.setVisibility(8);
            }
            if (groupM10015n1.f13712K) {
                this.f8257F.setVisibility(8);
                this.f8302d.setOnItemLongClickListener(null);
            }
        }
        if (this.f8327p0) {
            View viewFindViewById6 = viewInflate.findViewById(R.id.commentGotoAllPhotos);
            if (viewFindViewById6 != null) {
                viewFindViewById6.setVisibility(4);
            }
            View view = this.f8306f;
            if (view != null) {
                view.setVisibility(8);
            }
        }
        m9058s2();
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        ProgressDialog progressDialog = this.f8297a0;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.f8297a0.dismiss();
            this.f8297a0 = null;
        }
        AsyncTaskC1605j0 asyncTaskC1605j0 = this.f8319l0;
        if (asyncTaskC1605j0 != null && !asyncTaskC1605j0.isCancelled()) {
            this.f8319l0.cancel(false);
            this.f8319l0 = null;
        }
        AsyncTask asyncTask = this.f8309g0;
        if (asyncTask != null && !asyncTask.isCancelled()) {
            this.f8309g0.cancel(true);
            this.f8309g0 = null;
        }
        FriendsClient friendsClient = this.f8301c0;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        this.f8299b0 = false;
        C5321e.m20824o().m20832B0(this.f8264I0);
        XMPPManager.m14184g0().m14233Z0(this.f8266J0);
        XMPPManager.m14184g0().m14235a1(this.f8270L0);
        C6456d.m24714D().m24753M(this.f8268K0);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        C1603i0 c1603i0 = this.f8307f0;
        if (c1603i0 != null) {
            c1603i0.m24447a();
            this.f8307f0 = null;
        }
        TextView textView = this.f8249B;
        if (textView != null) {
            textView.setOnClickListener(null);
        }
        View view = this.f8326p;
        if (view != null) {
            view.setOnClickListener(null);
        }
        TextView textView2 = this.f8255E;
        if (textView2 != null) {
            textView2.setOnClickListener(null);
        }
        ImageView imageView = this.f8251C;
        if (imageView != null) {
            imageView.setOnClickListener(null);
        }
        ImageView imageView2 = this.f8253D;
        if (imageView2 != null) {
            imageView2.setOnClickListener(null);
        }
        ImageView imageView3 = this.f8257F;
        if (imageView3 != null) {
            imageView3.setOnClickListener(null);
        }
        this.f8315j0.clear();
        Map<String, Friend> map = this.f8317k0;
        if (map != null) {
            map.clear();
        }
        C1597f0 c1597f0 = this.f8308g;
        if (c1597f0 != null) {
            c1597f0.clear();
        }
        C5325c.m20917a().m20919c(getActivity());
        this.f8305e0.shutdown();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.f8303d0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() throws IllegalStateException {
        super.onPause();
        this.f8277P = true;
        this.f8285T = false;
        this.f8289V.removeCallbacks(this.f8262H0);
        this.f8289V.removeCallbacks(this.f8272M0);
        m9035R2();
        ProgressDialog progressDialog = this.f8297a0;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.f8299b0 = true;
            this.f8297a0.dismiss();
            this.f8297a0 = null;
        }
        C2907m0.m14454I().m14494V(this.f8254D0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        m9038U2();
        if (this.f8285T) {
            if (this.f8302d.getVisibility() != 0) {
                m9023H2();
            }
            this.f8272M0.run();
        }
        if (this.f8299b0 && getActivity() != null) {
            this.f8297a0 = ProgressDialog.show(getActivity(), "", getActivity().getResources().getString(R.string.uploading), true);
        }
        C2907m0.m14454I().m14510t(this.f8254D0);
        m9036S2();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        m9013B1();
        C5325c.m20917a().m20918b(view, getActivity(), this.f8276O0);
        view.post(new Runnable() { // from class: com.cyberlink.you.activity.w8
            @Override // java.lang.Runnable
            public final void run() {
                this.f11844b.m8952c2();
            }
        });
    }

    /* renamed from: p1 */
    public final void m9051p1() {
        if (getActivity() == null || this.f8298b == null) {
            return;
        }
        final Group groupM10015n1 = ((ShowMediaActivity) getActivity()).m10015n1();
        PhotoListActivity.m9157J1(getActivity(), groupM10015n1, this.f8298b.m15144p(), new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.r8
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f11138a.m8891H1(groupM10015n1, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: p2 */
    public void m9052p2(PhotoCommentFragment photoCommentFragment) {
        this.f8247A = photoCommentFragment;
    }

    /* renamed from: q1 */
    public final void m9053q1() {
        this.f8299b0 = false;
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.m8
            @Override // java.lang.Runnable
            public final void run() {
                this.f10886b.m8894I1(activity);
            }
        });
    }

    /* renamed from: q2 */
    public final void m9054q2(String str, String str2) {
        Intent intent = new Intent(getActivity(), (Class<?>) FingerPaintActivity.class);
        intent.putExtra("srcUrl", str);
        intent.putExtra("doodleUrl", str2);
        intent.putExtra("isLocalEditMode", false);
        intent.putExtra("isDoodleOnly", true);
        if (CLUtility.m16613z1(this.f8329q0.f8394a, null)) {
            intent.putExtra("mergedImage", this.f8329q0.f8394a);
            intent.putExtra("doodleImg", this.f8329q0.f8395b);
        }
        startActivityForResult(intent, 3);
    }

    /* renamed from: r1 */
    public final void m9055r1() {
        final String string = this.f8342x.getText().toString();
        this.f8342x.getText().clear();
        if (CLUtility.m16613z1(this.f8329q0.f8394a, null) && CLUtility.m16613z1(this.f8329q0.f8395b, null)) {
            C6566a.m25159r("Doodle");
            final C1601h0 c1601h0Clone = this.f8329q0.clone();
            if (getActivity() != null) {
                this.f8297a0 = ProgressDialog.show(getActivity(), "", getActivity().getResources().getString(R.string.uploading), true);
                new Thread(new Runnable() { // from class: com.cyberlink.you.activity.k8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f10815b.m8897J1(c1601h0Clone, string);
                    }
                }).start();
            }
        } else if (!string.isEmpty()) {
            C6566a.m25159r("TextSend");
            m9066w2(string);
        }
        CLUtility.m16589t1(getActivity());
    }

    /* renamed from: r2 */
    public final void m9056r2(String str, long j9) {
        AsyncTask asyncTask = this.f8309g0;
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        this.f8309g0 = new AsyncTaskC1611o(j9, str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: s1 */
    public final void m9057s1(C3061a c3061a) {
        f8246Y0 = CLUtility.m16488S0(getActivity(), String.valueOf(this.f8298b.m15144p()));
        C3197a c3197a = new C3197a();
        C3061a.a aVarM15789k = c3061a.m15789k();
        if (aVarM15789k == null) {
            Log.d("PhotoCommentFragment", "[downloadVoiceNoteAndPlay] mediaComment is null.");
        } else {
            c3197a.m16996s(aVarM15789k.f13817d, f8246Y0, new C1600h(c3061a));
        }
    }

    /* renamed from: s2 */
    public final void m9058s2() {
        if (this.f8306f.getVisibility() == 8) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f8302d.getLayoutParams();
            layoutParams.addRule(12, -1);
            layoutParams.removeRule(2);
            this.f8302d.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f8304e.getLayoutParams();
            layoutParams2.addRule(12, -1);
            layoutParams2.removeRule(2);
            this.f8304e.setLayoutParams(layoutParams2);
            return;
        }
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.f8302d.getLayoutParams();
        layoutParams3.addRule(2, R.id.toolbarLayout);
        layoutParams3.removeRule(12);
        this.f8302d.setLayoutParams(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.f8304e.getLayoutParams();
        layoutParams4.addRule(2, R.id.toolbarLayout);
        layoutParams4.removeRule(12);
        this.f8304e.setLayoutParams(layoutParams4);
    }

    /* renamed from: t1 */
    public final void m9059t1(final C3061a c3061a) {
        if (c3061a == null) {
            return;
        }
        String string = this.f8342x.getText().toString();
        if (string.isEmpty()) {
            return;
        }
        if (this.f8301c0 == null) {
            this.f8301c0 = new FriendsClient();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("commentId", String.valueOf(c3061a.m15783e())));
        arrayList.add(new C6301o("comment", string));
        this.f8301c0.m15734m("media", "updateComment", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.g8
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10686a.m8903L1(c3061a, str, str2, str3, str4);
            }
        });
        if (getActivity() != null) {
            getActivity().runOnUiThread(new RunnableC1925c8(this));
        }
    }

    /* renamed from: t2 */
    public void m9060t2() throws IllegalStateException {
        if (getActivity() == null) {
            this.f8263I = true;
            return;
        }
        if (this.f8308g != null) {
            m9035R2();
        }
        new AsyncTaskC1610n().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* renamed from: u1 */
    public final void m9061u1(final C3061a c3061a) {
        if (c3061a == null || this.f8298b == null) {
            return;
        }
        String string = this.f8342x.getText().toString();
        if (string.isEmpty()) {
            return;
        }
        if (this.f8301c0 == null) {
            this.f8301c0 = new FriendsClient();
        }
        String strValueOf = String.valueOf(this.f8298b.m15144p());
        final String strM15131c = this.f8298b.m15131c();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("mediaId", strValueOf));
        arrayList.add(new C6301o(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, string));
        this.f8301c0.m15734m("media", "updateMedia", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.d8
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10363a.m8907N1(strM15131c, c3061a, str, str2, str3, str4);
            }
        });
        if (getActivity() != null) {
            getActivity().runOnUiThread(new RunnableC1925c8(this));
        }
    }

    /* renamed from: u2 */
    public void m9062u2(C2973l0 c2973l0) {
        this.f8298b = c2973l0;
        m9060t2();
        C2973l0 c2973l02 = this.f8298b;
        if (c2973l02 != null) {
            m9028M(c2973l02.m15144p());
        }
    }

    /* renamed from: v1 */
    public final void m9063v1(boolean z8) {
        this.f8340w.setEnabled(z8);
    }

    /* renamed from: v2 */
    public void m9064v2(C2973l0 c2973l0) {
        this.f8298b = c2973l0;
        m9056r2(c2973l0.m15131c(), c2973l0.m15144p());
        m9028M(c2973l0.m15144p());
    }

    /* renamed from: w1 */
    public final C3061a m9065w1(String str, String str2, C3061a.a aVar) {
        long jNextLong = this.f8313i0.nextLong();
        if (jNextLong > 0) {
            jNextLong = -jNextLong;
        }
        long jM15646K = FriendsClient.m15646K();
        return new C3061a(-1L, jNextLong, this.f8298b.m15144p(), Globals.m7388i0().m7568k1().longValue(), str, jM15646K, jM15646K, str2, aVar, false, 0);
    }

    /* renamed from: w2 */
    public final void m9066w2(String str) {
        if (getActivity() == null) {
            return;
        }
        new AsyncTaskC1608l(str).executeOnExecutor(C6385v.f21554b, new Void[0]);
    }

    /* renamed from: x1 */
    public final AbstractC0372k m9067x1() {
        return getChildFragmentManager().mo1844a();
    }

    /* renamed from: y1 */
    public final void m9068y1(String str, String str2) {
        if (getActivity() == null || this.f8265J) {
            return;
        }
        AsyncTaskC1605j0 asyncTaskC1605j0 = new AsyncTaskC1605j0(this, null);
        this.f8319l0 = asyncTaskC1605j0;
        asyncTaskC1605j0.executeOnExecutor(C6385v.f21554b, str, str2);
    }

    /* renamed from: y2 */
    public final void m9069y2(C2973l0 c2973l0, C3061a c3061a, AbstractC6381r<Pair<C3061a, C3061a>, C3061a> abstractC6381r) {
        if (c2973l0 == null) {
            return;
        }
        try {
            m9006x2(String.valueOf(c2973l0.m15144p()), c3061a, abstractC6381r);
        } catch (Exception e9) {
            C5154j.m20076j(e9);
        }
    }

    /* renamed from: z1 */
    public final void m9070z1(final long j9) {
        final FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.l8
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f10849b.m8910O1(j9, activity);
            }
        });
    }

    /* renamed from: z2 */
    public final void m9071z2(int i9) {
        this.f8300c.setText(String.valueOf(i9));
    }

    /* renamed from: com.cyberlink.you.activity.PhotoCommentFragment$h0 */
    public static class C1601h0 {

        /* renamed from: a */
        public String f8394a;

        /* renamed from: b */
        public String f8395b;

        /* renamed from: c */
        public String f8396c;

        public C1601h0() {
            this.f8394a = null;
            this.f8395b = null;
            this.f8396c = null;
        }

        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C1601h0 clone() {
            return new C1601h0(this.f8394a, this.f8395b, this.f8396c);
        }

        /* renamed from: b */
        public void m9126b() {
            this.f8394a = null;
            this.f8395b = null;
            this.f8396c = null;
        }

        public String toString() {
            return "{ handDrawImage: " + this.f8394a + ", doodleImage: " + this.f8395b + ", color: " + this.f8396c + " }";
        }

        public C1601h0(String str, String str2, String str3) {
            this.f8394a = str;
            this.f8395b = str2;
            this.f8396c = str3;
        }
    }
}
