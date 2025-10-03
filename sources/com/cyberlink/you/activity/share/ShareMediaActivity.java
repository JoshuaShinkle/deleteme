package com.cyberlink.you.activity.share;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupAlbumActivity;
import com.cyberlink.you.activity.MessageGeneratorFragment;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.C2931y;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2971k0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.C3061a;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.p036ui.DialogC3133q;
import com.cyberlink.you.pages.photoimport.FileItem;
import com.cyberlink.you.pages.photoimport.ImageItem;
import com.cyberlink.you.sticker.StickerObj;
import com.cyberlink.you.utility.C3199c;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.cyberlink.you.utility.ULogUtility;
import com.cyberlink.you.utility.UploadMediaHelper;
import com.cyberlink.you.utility.UploadMultipleChatMediaHelperQueue;
import com.cyberlink.you.utility.UploadUtils;
import com.cyberlink.you.widgetpool.tokenautocomplete.PeopleAndGroupCompleteView;
import com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5172p;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p182r2.C6196d0;
import p182r2.C6201i;
import p201t3.C6287a;
import p201t3.C6290d;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6369f;
import p209u2.C6385v;
import p218v2.C6456d;
import p218v2.DialogC6459g;
import p244y1.C6597b;
import p254z2.C6818b;

/* loaded from: classes.dex */
public class ShareMediaActivity extends BaseActivity {

    /* renamed from: U */
    public static final String f11237U = "ShareMediaActivity";

    /* renamed from: Q */
    public C3199c.b f11254Q;

    /* renamed from: i */
    public FriendsClient f11264i;

    /* renamed from: k */
    public ListView f11266k;

    /* renamed from: l */
    public PeopleAndGroupCompleteView f11267l;

    /* renamed from: m */
    public TextView f11268m;

    /* renamed from: n */
    public TextView f11269n;

    /* renamed from: o */
    public TextView f11270o;

    /* renamed from: p */
    public View f11271p;

    /* renamed from: q */
    public View f11272q;

    /* renamed from: r */
    public Button f11273r;

    /* renamed from: s */
    public C6287a f11274s;

    /* renamed from: t */
    public C6290d f11275t;

    /* renamed from: u */
    public C2931y f11276u;

    /* renamed from: y */
    public boolean f11280y;

    /* renamed from: c */
    @Deprecated
    public final ArrayList<ImageItem> f11258c = new ArrayList<>();

    /* renamed from: d */
    public final ArrayList<Object> f11259d = new ArrayList<>();

    /* renamed from: e */
    public final ArrayList<Pair<File, Uri>> f11260e = new ArrayList<>();

    /* renamed from: f */
    @Deprecated
    public final HashMap<String, Uri> f11261f = new HashMap<>();

    /* renamed from: g */
    public boolean f11262g = false;

    /* renamed from: h */
    public boolean f11263h = false;

    /* renamed from: j */
    public DialogC6459g f11265j = null;

    /* renamed from: v */
    public View f11277v = null;

    /* renamed from: w */
    public TextView f11278w = null;

    /* renamed from: x */
    public TextView f11279x = null;

    /* renamed from: z */
    public C3199c f11281z = null;

    /* renamed from: A */
    public final Object f11238A = new Object();

    /* renamed from: B */
    public Object f11239B = null;

    /* renamed from: C */
    public DialogC3133q f11240C = null;

    /* renamed from: D */
    public List<String> f11241D = new ArrayList();

    /* renamed from: E */
    public Map<String, String> f11242E = new HashMap();

    /* renamed from: F */
    public int f11243F = 0;

    /* renamed from: G */
    public DialogC6459g f11244G = null;

    /* renamed from: H */
    public View.OnClickListener f11245H = new View.OnClickListener() { // from class: e3.w
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16527b.m12636G1(view);
        }
    };

    /* renamed from: I */
    public View.OnClickListener f11246I = new ViewOnClickListenerC2469j();

    /* renamed from: J */
    public View.OnClickListener f11247J = new ViewOnClickListenerC2470k();

    /* renamed from: K */
    public View.OnClickListener f11248K = new ViewOnClickListenerC2471l();

    /* renamed from: L */
    public DialogC6459g.a f11249L = new DialogC6459g.a() { // from class: e3.x
        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public final void mo7918a() {
            this.f16528a.m12645J1();
        }
    };

    /* renamed from: M */
    public AdapterView.OnItemClickListener f11250M = new C2472m();

    /* renamed from: N */
    public View.OnClickListener f11251N = new ViewOnClickListenerC2475p();

    /* renamed from: O */
    public View.OnClickListener f11252O = new ViewOnClickListenerC2477r();

    /* renamed from: P */
    public AbsListView.OnScrollListener f11253P = new C2460a();

    /* renamed from: R */
    public DialogC6459g.a f11255R = new DialogC6459g.a() { // from class: e3.y
        @Override // p218v2.DialogC6459g.a
        /* renamed from: a */
        public final void mo7918a() {
            this.f16529a.m12642I1();
        }
    };

    /* renamed from: S */
    public final AtomicInteger f11256S = new AtomicInteger();

    /* renamed from: T */
    public XMPPManager.InterfaceC2849a0 f11257T = new C2467h();

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$a */
    public class C2460a implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f11282a = false;

        /* renamed from: b */
        public int f11283b = -1;

        /* renamed from: c */
        public String f11284c = null;

        public C2460a() {
        }

        /* renamed from: a */
        public final String m12767a(String str) {
            return str.length() > 0 ? String.valueOf(Character.toChars(str.codePointAt(0))) : "";
        }

        /* renamed from: b */
        public final void m12768b() {
            if (ShareMediaActivity.this.f11277v != null && ShareMediaActivity.this.f11277v.isShown()) {
                ShareMediaActivity.this.f11277v.setVisibility(4);
            }
            m12769c();
        }

        /* renamed from: c */
        public final void m12769c() {
            this.f11283b = -1;
            this.f11284c = null;
        }

        /* renamed from: d */
        public final void m12770d() {
            Object item = ShareMediaActivity.this.f11266k.getAdapter().getItem(ShareMediaActivity.this.f11266k.getFirstVisiblePosition());
            if (item != null) {
                String strM15621b = item instanceof Friend ? ((Friend) item).m15621b() : item instanceof Group ? ((Group) item).f13717d : null;
                if (ShareMediaActivity.this.f11278w == null || strM15621b == null || strM15621b.isEmpty()) {
                    return;
                }
                String strM12767a = m12767a(strM15621b);
                if (strM12767a.equals(this.f11284c)) {
                    return;
                }
                ShareMediaActivity.this.f11278w.setText(strM12767a);
                m12771e();
                this.f11284c = strM12767a;
            }
        }

        /* renamed from: e */
        public final void m12771e() {
            if (ShareMediaActivity.this.f11277v != null && !ShareMediaActivity.this.f11277v.isShown()) {
                ShareMediaActivity.this.f11277v.setVisibility(0);
            }
            m12769c();
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            int firstVisiblePosition;
            if (this.f11282a && this.f11283b != (firstVisiblePosition = ShareMediaActivity.this.f11266k.getFirstVisiblePosition())) {
                this.f11283b = firstVisiblePosition;
                if (ShareMediaActivity.this.f11266k.getAdapter() == ShareMediaActivity.this.f11276u) {
                    m12768b();
                } else {
                    m12770d();
                }
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                this.f11282a = false;
                m12768b();
            } else {
                if (i9 != 1) {
                    return;
                }
                CLUtility.m16589t1(ShareMediaActivity.this);
                this.f11282a = true;
                m12769c();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$b */
    public class C2461b implements TokenCompleteTextView.InterfaceC3249e {
        public C2461b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m12773e(int i9) {
            ShareMediaActivity.this.f11272q.setVisibility(i9 > 0 ? 4 : 0);
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: a */
        public void mo5801a(String str) {
            ((C6597b) ShareMediaActivity.this.f11266k.getAdapter()).getFilter().filter(str, new Filter.FilterListener() { // from class: e3.d0
                @Override // android.widget.Filter.FilterListener
                public final void onFilterComplete(int i9) {
                    this.f16480b.m12773e(i9);
                }
            });
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: b */
        public void mo5802b(Object obj) {
            m12774f(obj, true);
        }

        @Override // com.cyberlink.you.widgetpool.tokenautocomplete.TokenCompleteTextView.InterfaceC3249e
        /* renamed from: c */
        public void mo5803c(Object obj) {
            m12774f(obj, false);
        }

        /* renamed from: f */
        public final void m12774f(Object obj, boolean z8) {
            Friend friendM24072j;
            if (obj instanceof Friend) {
                Friend friend = (Friend) obj;
                ShareMediaActivity.this.f11274s.m25238g(friend, z8);
                Group groupM14654k = ShareMediaActivity.this.f11276u.m14654k(friend.f13648f);
                if (groupM14654k != null) {
                    ShareMediaActivity.this.f11276u.m25238g(groupM14654k, z8);
                }
            } else if (obj instanceof Group) {
                Group group = (Group) obj;
                if (group.m15750g() && (friendM24072j = ShareMediaActivity.this.f11274s.m24072j(group.f13723j)) != null) {
                    ShareMediaActivity.this.f11274s.m25238g(friendM24072j, z8);
                }
                Group groupM24107j = ShareMediaActivity.this.f11275t.m24107j(group.f13723j);
                if (groupM24107j != null) {
                    ShareMediaActivity.this.f11275t.m25238g(groupM24107j, z8);
                }
                Group groupM14654k2 = ShareMediaActivity.this.f11276u.m14654k(group.f13723j);
                if (groupM14654k2 != null) {
                    ShareMediaActivity.this.f11276u.m25238g(groupM14654k2, z8);
                }
            }
            if (ShareMediaActivity.this.m12758o1()) {
                ShareMediaActivity.this.m12753l2();
            } else {
                ShareMediaActivity.this.m12759o2();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$c */
    public class C2462c extends AbstractC6381r<Group, Void> {
        public C2462c() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            ShareMediaActivity.this.m12738d2(group, true);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$d */
    public class C2463d implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ AbstractC6381r f11288a;

        /* renamed from: b */
        public final /* synthetic */ Permission f11289b;

        public C2463d(AbstractC6381r abstractC6381r, Permission permission) {
            this.f11288a = abstractC6381r;
            this.f11289b = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(ShareMediaActivity.this, this.f11289b);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            ShareMediaActivity.this.m12731a1(this.f11288a);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$e */
    public class C2464e implements C3199c.b {

        /* renamed from: a */
        public final /* synthetic */ boolean f11291a;

        /* renamed from: b */
        public final /* synthetic */ Group f11292b;

        public C2464e(boolean z8, Group group) {
            this.f11291a = z8;
            this.f11292b = group;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m12779g(int i9, int i10, UploadMediaHelper uploadMediaHelper) {
            if (ShareMediaActivity.this.f11265j != null) {
                ShareMediaActivity.this.f11265j.m24771n(Integer.toString(i9 + 1), Integer.toString(i10));
                if (uploadMediaHelper.m16842f1() != null) {
                    ShareMediaActivity.this.f11265j.m24764f(uploadMediaHelper.m16842f1());
                } else if (uploadMediaHelper.m16840e1() != null) {
                    ShareMediaActivity.this.f11265j.m24763e(uploadMediaHelper.m16840e1());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m12780h() {
            ShareMediaActivity.this.f11265j.m24760b();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: i */
        public /* synthetic */ void m12781i() {
            try {
                if (ShareMediaActivity.this.isFinishing() || ShareMediaActivity.this.f11265j == null || !ShareMediaActivity.this.f11265j.isShowing()) {
                    return;
                }
                ShareMediaActivity.this.f11265j.dismiss();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: a */
        public void mo7916a(final int i9, final int i10, final UploadMediaHelper uploadMediaHelper) {
            C3199c.m17015D(true);
            ShareMediaActivity.this.runOnUiThread(new Runnable() { // from class: e3.e0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16482b.m12779g(i9, i10, uploadMediaHelper);
                }
            });
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        /* renamed from: b */
        public void mo7917b(C3199c c3199c) throws NumberFormatException {
            String strM14572P;
            MessageObj.MessageType messageType;
            MessageObj messageObjM12750k1;
            if (ShareMediaActivity.this.f11265j != null) {
                ShareMediaActivity.this.runOnUiThread(new Runnable() { // from class: e3.f0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16489b.m12781i();
                    }
                });
            }
            ArrayList arrayList = new ArrayList();
            for (UploadMediaHelper uploadMediaHelper : c3199c.m17042t()) {
                if ((uploadMediaHelper.m16855l1() && uploadMediaHelper.m16810O0() != UploadUtils.UploadResultType.STEP_3_SUCCESS) || (uploadMediaHelper.m16857m1() && uploadMediaHelper.m16828Y0() != UploadUtils.UploadResultType.STEP_3_SUCCESS)) {
                    C5187v0.m20267c(R.string.error_server_response);
                } else if (this.f11291a) {
                    if (uploadMediaHelper.m16857m1()) {
                        ImageItem imageItemM16842f1 = uploadMediaHelper.m16842f1();
                        if (imageItemM16842f1 != null) {
                            C2973l0 c2973l0M16826X0 = uploadMediaHelper.m16826X0();
                            String strM16135h = imageItemM16842f1.m16135h();
                            Uri uriM16510Z1 = CLUtility.m16510Z1(imageItemM16842f1.m16136i());
                            String strValueOf = String.valueOf(imageItemM16842f1.m16142o());
                            if (!TextUtils.isEmpty(imageItemM16842f1.m16134g())) {
                                strM14572P = C2925v.m14574R(String.valueOf(c2973l0M16826X0.m15144p()), imageItemM16842f1.m16134g(), "", "", "0", strM16135h, uriM16510Z1, strValueOf);
                                messageType = MessageObj.MessageType.PhotoNote;
                            } else if (TextUtils.isEmpty(imageItemM16842f1.m16129b())) {
                                strM14572P = C2925v.m14570O(strM16135h, uriM16510Z1, c2973l0M16826X0.m15148t().f13200d, strValueOf, String.valueOf(c2973l0M16826X0.m15144p()), uploadMediaHelper.m16834b1(), uploadMediaHelper.m16824W0(), c2973l0M16826X0.m15151w(), c2973l0M16826X0.m15141m());
                                messageType = MessageObj.MessageType.Photo;
                            } else {
                                strM14572P = C2925v.m14574R(String.valueOf(c2973l0M16826X0.m15144p()), "", "Audio", imageItemM16842f1.m16128a(), "0", strM16135h, uriM16510Z1, strValueOf);
                                messageType = MessageObj.MessageType.PhotoNote;
                            }
                        }
                    } else if (uploadMediaHelper.m16855l1()) {
                        strM14572P = C2925v.m14572P(uploadMediaHelper.m16840e1(), uploadMediaHelper.m16808N0(), uploadMediaHelper.m16824W0());
                        messageType = MessageObj.MessageType.File;
                    } else {
                        strM14572P = null;
                        messageType = null;
                    }
                    if (messageType != null && (messageObjM12750k1 = ShareMediaActivity.this.m12750k1(this.f11292b, messageType, strM14572P)) != null) {
                        arrayList.add(new C6201i(this.f11292b.f13723j, messageObjM12750k1));
                    }
                }
            }
            c3199c.m17034E();
            C6196d0.m23692d().m23699i(arrayList);
            ShareMediaActivity.this.m12718S0(this.f11292b, this.f11291a);
            C3199c.m17015D(false);
        }

        @Override // com.cyberlink.you.utility.C3199c.b
        public void onCancel() {
            if (ShareMediaActivity.this.f11265j != null) {
                ShareMediaActivity.this.runOnUiThread(new Runnable() { // from class: e3.g0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16493b.m12780h();
                    }
                });
            }
            C3199c.m17015D(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$f */
    public class C2465f extends AbstractC6381r<Group, Void> {

        /* renamed from: c */
        public final /* synthetic */ AbstractC6381r f11294c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C2465f(Handler handler, AbstractC6381r abstractC6381r) {
            super(handler);
            this.f11294c = abstractC6381r;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            ShareMediaActivity.this.f11240C.dismiss();
            this.f11294c.m24506d(group);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: l, reason: merged with bridge method [inline-methods] */
        public void m24504h(Void r22) {
            ShareMediaActivity.this.f11240C.dismiss();
            this.f11294c.m24507e();
            ULogUtility.m16670f(ShareMediaActivity.f11237U, "[getDualGroup] error");
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$g */
    public class C2466g implements C6818b.a {

        /* renamed from: a */
        public final /* synthetic */ boolean f11296a;

        /* renamed from: b */
        public final /* synthetic */ Group f11297b;

        public C2466g(boolean z8, Group group) {
            this.f11296a = z8;
            this.f11297b = group;
        }

        @Override // p254z2.C6818b.a
        /* renamed from: a */
        public void mo11883a() {
            ULogUtility.m16683s(ShareMediaActivity.f11237U, "forwardAndSendMessages fail");
            ShareMediaActivity.this.f11256S.decrementAndGet();
            if (ShareMediaActivity.this.f11240C == null || !ShareMediaActivity.this.f11240C.isShowing()) {
                return;
            }
            ShareMediaActivity.this.f11240C.dismiss();
        }

        @Override // p254z2.C6818b.a
        public void onSuccess() {
            if (ShareMediaActivity.this.f11256S.decrementAndGet() == 0) {
                if (ShareMediaActivity.this.f11240C != null && ShareMediaActivity.this.f11240C.isShowing()) {
                    ShareMediaActivity.this.f11240C.dismiss();
                }
                if (this.f11296a) {
                    ShareMediaActivity.this.m12718S0(this.f11297b, true);
                } else {
                    if (ShareMediaActivity.this.m7364e()) {
                        return;
                    }
                    ShareMediaActivity.this.finish();
                    C5187v0.m20267c(R.string.sent);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$h */
    public class C2467h implements XMPPManager.InterfaceC2849a0 {
        public C2467h() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m12786d(String str, Date date) {
            synchronized (ShareMediaActivity.this.f11238A) {
                ShareMediaActivity.this.m12732a2(str, date, true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m12787e(String str) {
            synchronized (ShareMediaActivity.this.f11238A) {
                ShareMediaActivity.this.m12732a2(str, null, false);
            }
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: F */
        public void mo5716F(final String str) {
            ShareMediaActivity.this.runOnUiThread(new Runnable() { // from class: e3.h0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16494b.m12787e(str);
                }
            });
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: p */
        public void mo5718p(final String str, final Date date) {
            ShareMediaActivity.this.runOnUiThread(new Runnable() { // from class: e3.i0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16500b.m12786d(str, date);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$i */
    public static /* synthetic */ class C2468i {

        /* renamed from: a */
        public static final /* synthetic */ int[] f11300a;

        static {
            int[] iArr = new int[ShareType.values().length];
            f11300a = iArr;
            try {
                iArr[ShareType.Internal_Media.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11300a[ShareType.Voice.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11300a[ShareType.External_Media.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11300a[ShareType.EXTERNAL_FILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11300a[ShareType.Internal_Video.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f11300a[ShareType.Internal_File.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$j */
    public class ViewOnClickListenerC2469j implements View.OnClickListener {
        public ViewOnClickListenerC2469j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareMediaActivity shareMediaActivity = ShareMediaActivity.this;
            shareMediaActivity.m12761p2(shareMediaActivity.f11274s);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$k */
    public class ViewOnClickListenerC2470k implements View.OnClickListener {
        public ViewOnClickListenerC2470k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareMediaActivity shareMediaActivity = ShareMediaActivity.this;
            shareMediaActivity.m12761p2(shareMediaActivity.f11275t);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$l */
    public class ViewOnClickListenerC2471l implements View.OnClickListener {
        public ViewOnClickListenerC2471l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareMediaActivity shareMediaActivity = ShareMediaActivity.this;
            shareMediaActivity.m12761p2(shareMediaActivity.f11276u);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$m */
    public class C2472m implements AdapterView.OnItemClickListener {

        /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$m$a */
        public class a extends AbstractC6381r<Void, Void> {

            /* renamed from: c */
            public final /* synthetic */ AdapterView f11305c;

            /* renamed from: d */
            public final /* synthetic */ int f11306d;

            public a(AdapterView adapterView, int i9) {
                this.f11305c = adapterView;
                this.f11306d = i9;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Void r32) {
                ShareMediaActivity.this.m12752l1(this.f11305c, this.f11306d);
            }
        }

        public C2472m() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m12790c() {
            ShareMediaActivity.this.m12753l2();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m12791d() {
            ShareMediaActivity.this.m12759o2();
        }

        /* renamed from: e */
        public final void m12792e(Object obj) {
            ShareMediaActivity.this.f11267l.m17426S(obj);
            if (obj instanceof Friend) {
                Group groupM14654k = ShareMediaActivity.this.f11276u.m14654k(((Friend) obj).f13648f);
                if (groupM14654k != null) {
                    ShareMediaActivity.this.f11267l.m17426S(groupM14654k);
                    return;
                }
                return;
            }
            if (obj instanceof Group) {
                Group group = (Group) obj;
                if (group.m15750g()) {
                    Friend friendM24072j = ShareMediaActivity.this.f11274s.m24072j(group.f13723j);
                    if (friendM24072j != null) {
                        ShareMediaActivity.this.f11267l.m17426S(friendM24072j);
                        return;
                    }
                    return;
                }
                Group groupM14654k2 = ShareMediaActivity.this.f11276u.m14654k(group.f13723j);
                if (groupM14654k2 == obj) {
                    groupM14654k2 = ShareMediaActivity.this.f11275t.m24107j(group.f13723j);
                }
                if (groupM14654k2 != null) {
                    ShareMediaActivity.this.f11267l.m17426S(groupM14654k2);
                }
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            CLUtility.m16589t1(ShareMediaActivity.this);
            if (!ShareMediaActivity.this.m12766s1()) {
                ShareMediaActivity.this.m12714O0(new a(adapterView, i9));
                return;
            }
            C6597b c6597b = (C6597b) adapterView.getAdapter();
            boolean z8 = !c6597b.m25235d(i9);
            c6597b.m25237f(i9, z8);
            if (z8) {
                ShareMediaActivity.this.f11267l.m17434p(c6597b.getItem(i9));
            } else {
                m12792e(c6597b.getItem(i9));
            }
            if (ShareMediaActivity.this.m12758o1()) {
                ShareMediaActivity.this.f11267l.post(new Runnable() { // from class: e3.j0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16507b.m12790c();
                    }
                });
            } else {
                ShareMediaActivity.this.f11267l.post(new Runnable() { // from class: e3.k0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16511b.m12791d();
                    }
                });
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$n */
    public class C2473n extends AbstractC6381r<Group, Void> {

        /* renamed from: c */
        public final /* synthetic */ boolean f11308c;

        public C2473n(boolean z8) {
            this.f11308c = z8;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            ShareMediaActivity.this.m12738d2(group, this.f11308c);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$o */
    public class C2474o extends AbstractC6381r<Group, Void> {
        public C2474o() {
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            ShareMediaActivity.this.m12736c2(group);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$p */
    public class ViewOnClickListenerC2475p implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$p$a */
        public class a extends AsyncTask<Void, Void, Boolean> {

            /* renamed from: a */
            public final /* synthetic */ List f11312a;

            public a(List list) {
                this.f11312a = list;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean doInBackground(Void... voidArr) {
                boolean z8;
                Iterator it = this.f11312a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z8 = false;
                        break;
                    }
                    Object next = it.next();
                    if (next instanceof Friend) {
                        Friend friend = (Friend) next;
                        Group groupM15081r = C2950b0.m14912k().m15081r(friend.f13648f);
                        z8 = true;
                        if (groupM15081r == null) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
                            arrayList.add(new C6301o("displayName", friend.m15621b()));
                            arrayList.add(new C6301o("userId", Long.toString(friend.f13645c)));
                            arrayList.add(new C6301o("groupType", "Dual"));
                            Pair<String, String> pairM15731j = ShareMediaActivity.this.f11264i.m15731j("group", "create", arrayList);
                            if ("200".equals(pairM15731j.first)) {
                                try {
                                    groupM15081r = C5172p.m20186h(new JSONObject((String) pairM15731j.second).getJSONObject("result"));
                                    C2950b0.m14912k().m15070g(groupM15081r, true);
                                } catch (Exception unused) {
                                    ULogUtility.m16683s(ShareMediaActivity.f11237U, "Parse group exception when forward to friend");
                                }
                            }
                        }
                        if (groupM15081r != null && groupM15081r.f13711J) {
                            ULogUtility.m16683s(ShareMediaActivity.f11237U, "Forward to a fried who enable e2ee" + groupM15081r.f13727n);
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z8);
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Boolean bool) {
                ShareMediaActivity.this.f11240C.dismiss();
                if (!bool.booleanValue()) {
                    ShareMediaActivity.this.m12748j1(this.f11312a);
                } else {
                    ULogUtility.m16683s(ShareMediaActivity.f11237U, "One of forward group enable e2ee");
                    ShareMediaActivity.this.m12749j2();
                }
            }
        }

        public ViewOnClickListenerC2475p() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z8;
            List<Object> objects = ShareMediaActivity.this.f11267l.getObjects();
            if (objects != null) {
                Iterator<Object> it = objects.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z8 = false;
                        break;
                    } else if (it.next() instanceof Friend) {
                        z8 = true;
                        break;
                    }
                }
                if (ShareMediaActivity.this.m12764r1()) {
                    ShareMediaActivity.this.m12748j1(objects);
                } else if (!z8) {
                    ShareMediaActivity.this.m12748j1(objects);
                } else {
                    ShareMediaActivity.this.f11240C.show();
                    new a(objects).executeOnExecutor(C6385v.f21554b, new Void[0]);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$q */
    public class C2476q extends AbstractC6381r<Group, Void> {

        /* renamed from: c */
        public final /* synthetic */ boolean f11314c;

        public C2476q(boolean z8) {
            this.f11314c = z8;
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            ShareMediaActivity.this.m12738d2(group, this.f11314c);
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$r */
    public class ViewOnClickListenerC2477r implements View.OnClickListener {
        public ViewOnClickListenerC2477r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShareMediaActivity.this.m12744h1(ShareMediaActivity.this.f11267l.getObjects());
        }
    }

    /* renamed from: com.cyberlink.you.activity.share.ShareMediaActivity$s */
    public class C2478s implements FriendsClient.InterfaceC3051i {

        /* renamed from: a */
        public final boolean f11317a;

        /* renamed from: b */
        public final long[] f11318b;

        /* renamed from: c */
        public final boolean f11319c;

        /* renamed from: d */
        public final String f11320d;

        /* renamed from: e */
        public final Group f11321e;

        /* renamed from: f */
        public final boolean f11322f;

        public C2478s(long[] jArr, String str, Group group, boolean z8, boolean z9, boolean z10) {
            this.f11318b = jArr;
            this.f11320d = str;
            this.f11321e = group;
            this.f11317a = z8;
            this.f11319c = z9;
            this.f11322f = z10;
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            JSONObject jSONObject;
            C2973l0 c2973l0M14725v;
            if (m12800c(str3)) {
                return;
            }
            try {
                jSONObject = new JSONObject(str4);
            } catch (JSONException unused) {
                Log.d(ShareMediaActivity.f11237U, "[media.copyMedia] parse error jsonStr=" + str4);
                jSONObject = null;
            }
            try {
                if (jSONObject == null) {
                    return;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    for (long j9 : this.f11318b) {
                        long j10 = jSONObject.getLong(String.valueOf(j9));
                        if (this.f11317a && (c2973l0M14725v = C2950b0.m14914m().m14725v(j9)) != null) {
                            C2973l0 c2973l0M15129a = c2973l0M14725v.m15129a(this.f11320d, j10, this.f11319c);
                            m12801d(c2973l0M15129a, j10);
                            MessageObj messageObjM12799b = m12799b(c2973l0M15129a, j10);
                            if (messageObjM12799b != null) {
                                arrayList.add(new C6201i(this.f11321e.f13723j, messageObjM12799b));
                            }
                        }
                    }
                    C6196d0.m23692d().m23699i(arrayList);
                    if (this.f11322f) {
                        ShareMediaActivity.this.m12718S0(this.f11321e, this.f11317a);
                    } else {
                        ShareMediaActivity.this.finish();
                    }
                } catch (JSONException unused2) {
                    Log.d(ShareMediaActivity.f11237U, "[media.copyMedia] mediaIds missing jsonStr=" + str4);
                }
            } finally {
                ShareMediaActivity.this.f11240C.dismiss();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final MessageObj m12799b(C2973l0 c2973l0, long j9) {
            String strM14575S;
            MessageObj.MessageType messageType = MessageObj.MessageType.None;
            ShareType shareTypeM12737d1 = ShareMediaActivity.this.m12737d1();
            if (shareTypeM12737d1 == ShareType.Voice) {
                Pair pairM12741f1 = ShareMediaActivity.this.m12741f1();
                if (pairM12741f1 != null) {
                    MessageObj messageObjM15179r = C2950b0.m14916o().m15179r((String) pairM12741f1.first);
                    if (messageObjM15179r != null) {
                        strM14575S = C2925v.m14580X(c2973l0.m15148t().f13201e, String.valueOf(j9), messageObjM15179r.m14747I("duration"));
                        messageType = MessageObj.MessageType.Audio;
                    } else {
                        strM14575S = null;
                    }
                }
            } else if (shareTypeM12737d1 == ShareType.Internal_Media && c2973l0.m15153y()) {
                strM14575S = ShareMediaActivity.this.m12721V0(c2973l0, j9);
                messageType = MessageObj.MessageType.PhotoNote;
            } else if (shareTypeM12737d1 == ShareType.Internal_Video) {
                strM14575S = C2925v.m14579W(c2973l0.m15148t().f13200d, null, String.valueOf(c2973l0.m15144p()), c2973l0.m15148t().f13203g, c2973l0.m15149u().f13200d, null, null, null, null);
                messageType = MessageObj.MessageType.Video;
            } else if (shareTypeM12737d1 == ShareType.Internal_File) {
                strM14575S = C2925v.m14566M(c2973l0);
                messageType = MessageObj.MessageType.File;
            } else {
                strM14575S = C2925v.m14575S(String.valueOf(j9), c2973l0.m15137i(), "", "", "" + c2973l0.m15150v(), null, null, c2973l0.m15148t().f13200d, null, c2973l0.m15151w(), c2973l0.m15141m());
                messageType = MessageObj.MessageType.Photo;
            }
            return ShareMediaActivity.this.m12750k1(this.f11321e, messageType, strM14575S);
        }

        /* renamed from: c */
        public final boolean m12800c(String str) {
            if (str == null) {
                Log.d(ShareMediaActivity.f11237U, "[media.copyMedia] Response is null");
                return true;
            }
            if (str.equals("200")) {
                return false;
            }
            Log.d(ShareMediaActivity.f11237U, "[media.copyMedia] statusCode=" + str);
            return true;
        }

        /* renamed from: d */
        public final void m12801d(C2973l0 c2973l0, long j9) {
            C2950b0.m14914m().m14712i(c2973l0);
            String str = c2973l0.m15149u().f13201e;
            if (str == null || str.isEmpty()) {
                return;
            }
            File file = new File(str);
            if (file.exists()) {
                String strM16517b1 = CLUtility.m16517b1(ShareMediaActivity.this.getApplicationContext(), String.valueOf(j9));
                if (C6369f.m24459a(file, new File(strM16517b1))) {
                    c2973l0.m15149u().f13201e = strM16517b1;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A1 */
    public /* synthetic */ void m12621A1(List list, Dialog dialog, ArrayList arrayList, View view) {
        if (m12745h2(list)) {
            dialog.dismiss();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ShareTouchImageActivity.class);
        intent.putExtra("position", 0);
        intent.putExtra("isTextNote", true);
        intent.putExtra("sharedImageItemList", arrayList);
        startActivityForResult(intent, 2210);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B1 */
    public /* synthetic */ void m12623B1(List list, Dialog dialog, ArrayList arrayList, View view) {
        if (m12745h2(list)) {
            dialog.dismiss();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), (Class<?>) ShareTouchImageActivity.class);
        intent.putExtra("position", 0);
        intent.putExtra("isVoiceNote", true);
        intent.putExtra("sharedImageItemList", arrayList);
        startActivityForResult(intent, 2210);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C1 */
    public /* synthetic */ void m12626C1(List list, Dialog dialog, View view) {
        m12717R0(list);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D1 */
    public /* synthetic */ void m12629D1(List list, Dialog dialog, View view) {
        m12716Q0(list);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F1 */
    public /* synthetic */ void m12634F1(List list, long j9) {
        this.f11276u.clear();
        this.f11276u.addAll(list);
        this.f11276u.notifyDataSetChanged();
        Log.d(f11237U, "resetGroupList " + (System.currentTimeMillis() - j9) + "ms");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G1 */
    public /* synthetic */ void m12636G1(View view) {
        CLUtility.m16589t1(this);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H1 */
    public /* synthetic */ void m12639H1(DialogInterface dialogInterface, int i9) {
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I1 */
    public /* synthetic */ void m12642I1() {
        C3199c c3199c = this.f11281z;
        if (c3199c != null) {
            c3199c.m17036d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J1 */
    public /* synthetic */ void m12645J1() {
        UploadMultipleChatMediaHelperQueue.m16892F().m16939f(this.f11241D);
        AlertDialog.Builder builderM12720U0 = m12720U0(R.string.broadcast_cancel_title, R.string.broadcast_cancel_content);
        builderM12720U0.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: e3.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f16481b.m12639H1(dialogInterface, i9);
            }
        });
        builderM12720U0.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K1 */
    public /* synthetic */ void m12647K1(int i9, boolean z8) {
        this.f11244G.m24771n(String.valueOf(i9), String.valueOf(this.f11243F));
        this.f11244G.m24772o((i9 * 100) / this.f11243F);
        if (this.f11241D.size() == 0) {
            this.f11244G.dismiss();
            m12740e2(z8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L1 */
    public /* synthetic */ void m12650L1(DialogInterface dialogInterface, int i9) {
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N1 */
    public /* synthetic */ void m12653N1(boolean z8) {
        if (!z8 || m7364e()) {
            return;
        }
        finish();
    }

    /* renamed from: P1 */
    public static /* synthetic */ void m12657P1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q1 */
    public /* synthetic */ void m12659Q1() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        if (m12762q1()) {
            builderM16382a.setMessage(getString(R.string.forward_image_file_to_e2ee_group));
        } else {
            builderM16382a.setMessage(getString(R.string.forward_other_to_e2ee_group));
        }
        builderM16382a.setPositiveButton(getString(R.string.got_it), new DialogInterface.OnClickListener() { // from class: e3.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ShareMediaActivity.m12657P1(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* renamed from: R1 */
    public static /* synthetic */ void m12660R1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S1 */
    public /* synthetic */ void m12661S1() {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(getString(R.string.forward_to_broadcast_only_group));
        builderM16382a.setPositiveButton(getString(R.string.got_it), new DialogInterface.OnClickListener() { // from class: e3.r
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                ShareMediaActivity.m12660R1(dialogInterface, i9);
            }
        });
        builderM16382a.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T1 */
    public /* synthetic */ void m12663T1(ListAdapter listAdapter, int i9) {
        this.f11272q.setVisibility(i9 > 0 ? 4 : 0);
        this.f11266k.setAdapter(listAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U1 */
    public /* synthetic */ void m12664U1() {
        this.f11265j.show();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* renamed from: g1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Uri m12674g1(String str) {
        Uri uriWithAppendedId;
        Cursor cursorQuery = Globals.m7372O().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, "_data=? ", new String[]{str}, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    int columnIndex = cursorQuery.getColumnIndex("_id");
                    uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columnIndex > 0 ? cursorQuery.getLong(columnIndex) : -1L);
                } else {
                    uriWithAppendedId = null;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return uriWithAppendedId;
    }

    /* renamed from: t1 */
    public static /* synthetic */ void m12695t1(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: u1 */
    public static /* synthetic */ void m12698u1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v1 */
    public /* synthetic */ void m12700v1(List list, DialogInterface dialogInterface, int i9) {
        m12734b2(list, true);
    }

    /* renamed from: w1 */
    public static /* synthetic */ void m12703w1(DialogInterface dialogInterface, int i9) {
    }

    /* renamed from: x1 */
    public static /* synthetic */ void m12705x1(DialogInterface dialogInterface, int i9) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y1 */
    public /* synthetic */ void m12708y1(boolean z8, final List list) {
        if (!z8) {
            AlertDialog.Builder builderM12720U0 = m12720U0(R.string.broadcast_e2ee_unable_title, R.string.broadcast_e2ee_unable_content);
            builderM12720U0.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() { // from class: e3.u
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ShareMediaActivity.m12705x1(dialogInterface, i9);
                }
            });
            builderM12720U0.show();
        } else {
            AlertDialog.Builder builderM12720U02 = m12720U0(R.string.broadcast_e2ee_title, R.string.broadcast_e2ee_content);
            builderM12720U02.setPositiveButton(getString(R.string.broadcast_e2ee_send), new DialogInterface.OnClickListener() { // from class: e3.s
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f16524b.m12700v1(list, dialogInterface, i9);
                }
            });
            builderM12720U02.setNegativeButton(getString(R.string.broadcast_e2ee_cancel), new DialogInterface.OnClickListener() { // from class: e3.t
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ShareMediaActivity.m12703w1(dialogInterface, i9);
                }
            });
            builderM12720U02.show();
        }
    }

    /* renamed from: M0 */
    public final boolean m12712M0() {
        Intent intent = getIntent();
        return intent != null && intent.getLongExtra("time", -1L) < FriendsClient.m15646K();
    }

    /* renamed from: N0 */
    public final boolean m12713N0() {
        long[] longArrayExtra;
        Intent intent = getIntent();
        return intent != null && intent.hasExtra("share_media_id") && (longArrayExtra = intent.getLongArrayExtra("share_media_id")) != null && longArrayExtra.length > 0;
    }

    /* renamed from: O0 */
    public final void m12714O0(AbstractC6381r<Void, Void> abstractC6381r) {
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
        if (!C5287b.m20579b(permission, this) || this.f11259d.isEmpty()) {
            C5287b.m20583f(permission, new C2463d(abstractC6381r, permission), this);
        } else if (abstractC6381r != null) {
            abstractC6381r.m24505c();
        }
    }

    /* renamed from: P0 */
    public final void m12715P0(long[] jArr, String str, Group group, boolean z8, boolean z9, boolean z10) {
        if (str == null) {
            Log.e(f11237U, "albumId is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        for (long j9 : jArr) {
            arrayList.add(new C6301o("mediaId", String.valueOf(j9)));
        }
        arrayList.add(new C6301o("albumId", str));
        arrayList.add(new C6301o("includeComments", String.valueOf(z9)));
        arrayList.add(new C6301o("includeDescription", String.valueOf(z9)));
        arrayList.add(new C6301o("includeMediaNotes", String.valueOf(z9)));
        this.f11240C.show();
        this.f11264i.m15734m("media", "copyMedia", arrayList, new C2478s(jArr, str, group, z8, z9, z10));
    }

    /* renamed from: Q0 */
    public final void m12716Q0(List<Object> list) {
        for (Object obj : list) {
            if (obj instanceof Friend) {
                m12725X0((Friend) obj, new C2474o());
            } else if (obj instanceof Group) {
                m12736c2((Group) obj);
            }
        }
    }

    /* renamed from: R0 */
    public final void m12717R0(List<Object> list) {
        boolean z8 = list.size() == 1;
        if (m12745h2(list)) {
            return;
        }
        for (Object obj : list) {
            if (obj instanceof Friend) {
                m12725X0((Friend) obj, new C2473n(z8));
            } else if (obj instanceof Group) {
                m12738d2((Group) obj, z8);
            }
        }
    }

    /* renamed from: S0 */
    public final void m12718S0(Group group, boolean z8) {
        if (z8) {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
            intent.putExtra("Group", group);
            startActivity(intent);
            finish();
            return;
        }
        Intent intent2 = new Intent(getApplicationContext(), (Class<?>) GroupAlbumActivity.class);
        intent2.putExtra("Group", group);
        intent2.putExtra("page", 1);
        startActivity(intent2);
        finish();
    }

    /* renamed from: T0, reason: merged with bridge method [inline-methods] */
    public final void m12711z1(List<Object> list) {
        boolean z8 = list.size() == 1;
        for (Object obj : list) {
            if (obj instanceof Group) {
                m12738d2((Group) obj, z8);
            } else if (obj instanceof Friend) {
                m12725X0((Friend) obj, new C2476q(z8));
            }
        }
    }

    /* renamed from: U0 */
    public final AlertDialog.Builder m12720U0(int i9, int i10) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setTitle(i9);
        builderM16382a.setMessage(i10);
        return builderM16382a;
    }

    /* renamed from: V0 */
    public final String m12721V0(C2973l0 c2973l0, long j9) {
        String str;
        String str2;
        C2971k0 c2971k0M15146r = c2973l0.m15146r();
        String strValueOf = String.valueOf(c2973l0.m15150v());
        if (c2971k0M15146r != null) {
            C3061a.a aVarM15111b = c2971k0M15146r.m15111b();
            String str3 = aVarM15111b.f13820g;
            str2 = aVarM15111b.f13819f;
            str = str3;
        } else {
            str = "";
            str2 = str;
        }
        return C2925v.m14575S(String.valueOf(j9), c2973l0.m15137i() != null ? c2973l0.m15137i() : "", str, str2, strValueOf, null, null, c2973l0.m15148t().f13200d, null, c2973l0.m15151w(), c2973l0.m15141m());
    }

    /* renamed from: V1 */
    public final void m12722V1() {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        List<Group> listM15084u = C2950b0.m14912k().m15084u();
        final ArrayList arrayList = new ArrayList();
        for (Group group : listM15084u) {
            if (!Group.m15744h(group)) {
                arrayList.add(group);
            }
        }
        Collections.sort(arrayList, new Group.C3056c());
        runOnUiThread(new Runnable() { // from class: e3.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f16474b.m12634F1(arrayList, jCurrentTimeMillis);
            }
        });
    }

    /* renamed from: W0 */
    public final MessageGeneratorFragment.InputType m12723W0() {
        Intent intent = getIntent();
        if (intent.hasExtra("type")) {
            String stringExtra = intent.getStringExtra("type");
            if (!TextUtils.isEmpty(stringExtra)) {
                return MessageGeneratorFragment.InputType.valueOf(stringExtra);
            }
        }
        return MessageGeneratorFragment.InputType.Text;
    }

    /* renamed from: W1 */
    public final void m12724W1() {
        List<Friend> listM15025q = C2950b0.m14899A().m15025q();
        ArrayList arrayList = new ArrayList();
        for (Friend friend : listM15025q) {
            if (!friend.f13660r.equals("Corporate") && !friend.f13660r.equals("Official")) {
                arrayList.add(friend);
            }
        }
        Collections.sort(arrayList, new Friend.C3041b());
        this.f11274s.addAll(arrayList);
        this.f11274s.notifyDataSetChanged();
    }

    /* renamed from: X0 */
    public final void m12725X0(Friend friend, AbstractC6381r<Group, Void> abstractC6381r) {
        this.f11240C.show();
        this.f11264i.m15719Z(friend, new C2465f(m7363d(), abstractC6381r));
    }

    /* renamed from: X1 */
    public final void m12726X1() {
        List<Group> listM15083t = C2950b0.m14912k().m15083t("Circle");
        Collections.sort(listM15083t, new Group.C3055b());
        this.f11275t.addAll(listM15083t);
        this.f11275t.notifyDataSetChanged();
    }

    /* renamed from: Y0 */
    public final ArrayList<ImageItem> m12727Y0() {
        return this.f11258c;
    }

    /* renamed from: Y1 */
    public final void m12728Y1(Group group, boolean z8) {
        ArrayList<String> arrayListM12733b1 = m12733b1();
        if (arrayListM12733b1 == null || arrayListM12733b1.size() == 0) {
            ULogUtility.m16670f(f11237U, "[onMultipleMessageShare] get sharedMediaMsgIdList null or zero");
            return;
        }
        this.f11256S.incrementAndGet();
        DialogC3133q dialogC3133q = this.f11240C;
        if (dialogC3133q != null) {
            dialogC3133q.show();
        }
        C6818b.m25398i(group, getIntent().getBooleanExtra("withComment", false), arrayListM12733b1, new C2466g(z8, group));
    }

    /* renamed from: Z0 */
    public final long[] m12729Z0() {
        Intent intent = getIntent();
        long[] longArrayExtra = intent.getLongArrayExtra("share_media_id");
        this.f11280y = intent.getBooleanExtra("withComment", false);
        return longArrayExtra;
    }

    /* renamed from: Z1 */
    public final void m12730Z1(File file, Uri uri) {
        FileItem fileItem = CLUtility.m16613z1(file.getPath(), uri) ? new FileItem(null, file.getPath(), uri.toString()) : null;
        if (fileItem == null) {
            return;
        }
        if (fileItem.m16116c() > 52428800) {
            this.f11262g = true;
        } else {
            this.f11259d.add(fileItem);
        }
    }

    /* renamed from: a1 */
    public final void m12731a1(AbstractC6381r<Void, Void> abstractC6381r) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.f11258c.clear();
        this.f11259d.clear();
        Iterator<Pair<File, Uri>> it = this.f11260e.iterator();
        while (it.hasNext()) {
            Pair<File, Uri> next = it.next();
            File file = (File) next.first;
            Uri uri = (Uri) next.second;
            ImageItem imageItemM16432E0 = null;
            if (file.exists()) {
                if (CLUtility.m16417A1(uri)) {
                    String strM16520c0 = CLUtility.m16520c0();
                    String path = ((Uri) next.second).getPath();
                    if (!TextUtils.isEmpty(path) && path.startsWith(strM16520c0)) {
                        imageItemM16432E0 = new ImageItem("", -1L, "", path, uri.toString(), CLUtility.m16552k0(path, uri), 0, -1, "");
                    }
                }
                if (imageItemM16432E0 == null) {
                    imageItemM16432E0 = CLUtility.m16428D0(getApplicationContext(), file.getPath());
                }
            }
            if (imageItemM16432E0 == null) {
                imageItemM16432E0 = CLUtility.m16432E0(getApplicationContext(), uri);
            }
            if (imageItemM16432E0 == null || !CLUtility.m16457K1(imageItemM16432E0.m16144q(), CLUtility.m16510Z1(imageItemM16432E0.m16145r()))) {
                m12730Z1(file, uri);
            } else {
                CLUtility.m16459L(getApplicationContext(), String.valueOf(imageItemM16432E0.m16142o()));
                imageItemM16432E0.m16151x(true);
                this.f11258c.add(imageItemM16432E0);
                this.f11259d.add(imageItemM16432E0);
            }
        }
        m12747i2();
        if (abstractC6381r != null) {
            abstractC6381r.m24505c();
        }
    }

    /* renamed from: a2 */
    public final void m12732a2(String str, Date date, final boolean z8) {
        if (m12758o1()) {
            int i9 = 0;
            while (true) {
                if (i9 >= this.f11241D.size()) {
                    i9 = -1;
                    break;
                } else if (this.f11241D.get(i9).equals(str)) {
                    break;
                } else {
                    i9++;
                }
            }
            if (i9 == -1) {
                return;
            }
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(this.f11241D.get(i9));
            if (z8) {
                if (date != null) {
                    messageObjM15179r.m14761W(date);
                }
                messageObjM15179r.m14762X("0");
                String str2 = this.f11242E.get(messageObjM15179r.m14772j());
                if (!TextUtils.isEmpty(str2)) {
                    CLUtility.m16594u2(str2);
                }
            } else {
                messageObjM15179r.m14762X("3");
            }
            C2950b0.m14916o().m15158C(messageObjM15179r.m14777o(), messageObjM15179r);
            this.f11241D.remove(i9);
            final int size = this.f11243F - this.f11241D.size();
            runOnUiThread(new Runnable() { // from class: e3.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16486b.m12647K1(size, z8);
                }
            });
        }
    }

    /* renamed from: b1 */
    public final ArrayList<String> m12733b1() {
        return getIntent().getStringArrayListExtra("sharedMediaMsgIdList");
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0168 A[LOOP:4: B:61:0x0162->B:63:0x0168, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /* renamed from: b2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m12734b2(List<Object> list, boolean z8) {
        String str;
        String str2;
        String str3;
        Iterator<MessageObj> it;
        JSONObject jSONObject;
        boolean z9;
        this.f11241D.clear();
        this.f11242E.clear();
        this.f11243F = 0;
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        long longExtra = intent.getLongExtra("time", -1L);
        if (longExtra == -1) {
            return;
        }
        Date date = !z8 ? new Date(longExtra) : null;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof Group) {
                Group group = (Group) obj;
                arrayList.add(group);
                this.f11242E.put(String.valueOf(group.f13727n), group.f13723j);
            }
        }
        MessageGeneratorFragment.InputType inputTypeM12723W0 = m12723W0();
        ArrayList arrayList2 = new ArrayList();
        if (inputTypeM12723W0 == MessageGeneratorFragment.InputType.Text) {
            String stringExtra = intent.getStringExtra("message");
            if (!TextUtils.isEmpty(stringExtra)) {
                Iterator<MessageObj> it2 = C2925v.m14545B0(MessageObj.MessageType.Text, stringExtra, 0, date, arrayList, null, true).iterator();
                while (it2.hasNext()) {
                    arrayList2.add(it2.next().m14777o());
                }
                z9 = true;
            }
            z9 = false;
        } else {
            if (inputTypeM12723W0 == MessageGeneratorFragment.InputType.Sticker) {
                Iterator<MessageObj> it3 = C2925v.m14561J0((StickerObj) intent.getParcelableExtra("sticker"), 0, date, arrayList, null, true).iterator();
                while (it3.hasNext()) {
                    arrayList2.add(it3.next().m14777o());
                }
            } else if (inputTypeM12723W0 == MessageGeneratorFragment.InputType.Photo) {
                Iterator<MessageObj> it4 = C2925v.m14557H0(intent.getExtras() != null ? (ArrayList) intent.getExtras().getSerializable("photo") : new ArrayList(), 0, date, arrayList, true, false).iterator();
                while (it4.hasNext()) {
                    arrayList2.add(it4.next().m14777o());
                }
            } else {
                if (inputTypeM12723W0 == MessageGeneratorFragment.InputType.Voice) {
                    try {
                        jSONObject = new JSONObject(intent.getExtras() != null ? (String) intent.getExtras().getSerializable("voice") : null);
                        str = (String) jSONObject.get("data");
                    } catch (Exception e9) {
                        e = e9;
                        str = null;
                    }
                    try {
                        str2 = (String) jSONObject.get("duration");
                    } catch (Exception e10) {
                        e = e10;
                        Log.d(f11237U, "Parse voice exception : " + e.getMessage());
                        str2 = null;
                        str3 = str;
                        if (str3 != null) {
                            it = C2925v.m14563K0(str3, str2, 0, date, arrayList, true).iterator();
                            while (it.hasNext()) {
                            }
                            z9 = true;
                            this.f11241D.addAll(arrayList2);
                            this.f11243F = this.f11241D.size();
                            if (z9) {
                            }
                        }
                        z9 = false;
                        this.f11241D.addAll(arrayList2);
                        this.f11243F = this.f11241D.size();
                        if (z9) {
                        }
                    }
                    str3 = str;
                    if (str3 != null && str2 != null) {
                        it = C2925v.m14563K0(str3, str2, 0, date, arrayList, true).iterator();
                        while (it.hasNext()) {
                            arrayList2.add(it.next().m14777o());
                        }
                    }
                }
                z9 = false;
            }
            z9 = true;
        }
        this.f11241D.addAll(arrayList2);
        this.f11243F = this.f11241D.size();
        if (z9) {
            DialogC6459g dialogC6459g = new DialogC6459g(this, R.style.FriendSelectorDialog);
            this.f11244G = dialogC6459g;
            dialogC6459g.m24771n(String.valueOf(0), String.valueOf(this.f11243F));
            this.f11244G.setCancelable(true);
            this.f11244G.m24770m(this.f11249L);
            this.f11244G.m24766h(true);
            this.f11244G.m24768j();
            this.f11244G.m24769k();
            this.f11244G.m24772o(0);
            this.f11244G.m24774q(false);
            this.f11244G.show();
        }
    }

    /* renamed from: c1 */
    public final String m12735c1() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString("android.intent.extra.TEXT");
        }
        return null;
    }

    /* renamed from: c2 */
    public final void m12736c2(Group group) {
        if (m12743g2(group)) {
            return;
        }
        Intent intent = new Intent(getApplicationContext(), (Class<?>) GroupAlbumActivity.class);
        intent.putExtra("Group", group);
        intent.putExtra("select", true);
        intent.putExtra("share_media_id", getIntent().getLongArrayExtra("share_media_id"));
        intent.putExtra("withComment", getIntent().getBooleanExtra("withComment", false));
        intent.putExtra("shared_external_imageitem", m12727Y0());
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", getIntent().getParcelableArrayListExtra("android.intent.extra.STREAM"));
        startActivity(intent);
        finish();
    }

    /* renamed from: d1 */
    public final ShareType m12737d1() {
        Intent intent = getIntent();
        if (intent.hasExtra("shareType")) {
            String stringExtra = intent.getStringExtra("shareType");
            if (!TextUtils.isEmpty(stringExtra)) {
                return ShareType.valueOf(stringExtra);
            }
        }
        return ShareType.Internal_Text;
    }

    /* renamed from: d2 */
    public final void m12738d2(Group group, boolean z8) {
        ShareType shareTypeM12737d1 = m12737d1();
        Globals.m7388i0().m7480R2(String.valueOf(group.f13727n));
        if (shareTypeM12737d1 == ShareType.Forward) {
            m12728Y1(group, z8);
            return;
        }
        if (m12737d1() == ShareType.Internal_Text) {
            String strM12735c1 = m12735c1();
            if (strM12735c1 != null) {
                MessageObj messageObjM12750k1 = m12750k1(group, MessageObj.MessageType.Text, strM12735c1);
                if (messageObjM12750k1 != null) {
                    C6196d0.m23692d().m23700j(new C6201i(group.f13723j, messageObjM12750k1));
                }
                m12718S0(group, true);
                return;
            }
            return;
        }
        if (shareTypeM12737d1 == ShareType.External_Text) {
            String strM12735c12 = m12735c1();
            if (strM12735c12 != null) {
                Intent intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
                intent.putExtra("Group", group);
                intent.putExtra("defaultText", strM12735c12);
                startActivity(intent);
                finish();
                return;
            }
            return;
        }
        if (shareTypeM12737d1 == ShareType.User_Contact) {
            MessageObj messageObjM12750k12 = m12750k1(group, MessageObj.MessageType.UserContact, m12739e1());
            if (messageObjM12750k12 != null) {
                C6196d0.m23692d().m23700j(new C6201i(group.f13723j, messageObjM12750k12));
            }
            m12718S0(group, true);
            return;
        }
        if (shareTypeM12737d1 != ShareType.External_Video) {
            m12763q2(group, true, z8);
            return;
        }
        Intent intent2 = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
        intent2.putExtra("Group", group);
        intent2.putExtra("android.intent.extra.STREAM", this.f11261f);
        startActivity(intent2);
        finish();
    }

    /* renamed from: e1 */
    public final String m12739e1() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return null;
        }
        MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(extras.getString(Constants.FirelogAnalytics.PARAM_MESSAGE_ID));
        if (messageObjM15179r != null) {
            return messageObjM15179r.m14779q();
        }
        return null;
    }

    /* renamed from: e2 */
    public final void m12740e2(boolean z8) {
        AlertDialog.Builder builderM12720U0 = m12720U0(z8 ? R.string.broadcast_success_title : R.string.broadcast_fail_title, z8 ? R.string.broadcast_success_content : R.string.broadcast_fail_content);
        builderM12720U0.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: e3.q
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f16523b.m12650L1(dialogInterface, i9);
            }
        });
        builderM12720U0.create().show();
    }

    /* renamed from: f1 */
    public final Pair<String, Long> m12741f1() {
        Intent intent = getIntent();
        long longExtra = intent.getLongExtra("mediaId", 0L);
        String stringExtra = intent.getStringExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
        if (stringExtra == null || longExtra == 0) {
            return null;
        }
        return Pair.create(stringExtra, Long.valueOf(longExtra));
    }

    /* renamed from: f2 */
    public final void m12742f2(Object obj, final Runnable runnable) {
        C3123g.m16382a(this).setMessage(getString(R.string.forward_confirm_dialog_message, obj instanceof Group ? ((Group) obj).f13717d : obj instanceof Friend ? ((Friend) obj).m15621b() : "")).setPositiveButton(R.string.forward, new DialogInterface.OnClickListener() { // from class: e3.o
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                runnable.run();
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
    }

    /* renamed from: g2 */
    public final boolean m12743g2(Group group) {
        boolean zM12764r1 = m12764r1();
        boolean zM7616t1 = Globals.m7388i0().m7616t1("BROADCASTER");
        if (!zM12764r1 && group.f13711J) {
            m12749j2();
            return true;
        }
        if (zM7616t1 || !group.f13712K) {
            return false;
        }
        m12751k2();
        return true;
    }

    /* renamed from: h1 */
    public final void m12744h1(List<Object> list) {
        boolean z8;
        if (!C6456d.m24714D().m24748G()) {
            AlertDialog.Builder builderM12720U0 = m12720U0(R.string.error_no_network, R.string.not_internet_connect);
            builderM12720U0.setPositiveButton(getString(R.string.bc_dialog_button_ok), new DialogInterface.OnClickListener() { // from class: e3.b0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ShareMediaActivity.m12695t1(dialogInterface, i9);
                }
            });
            builderM12720U0.show();
            return;
        }
        if (m12712M0()) {
            AlertDialog.Builder builderM12720U02 = m12720U0(R.string.warning_timer_title, R.string.warning_timer_picker);
            builderM12720U02.setPositiveButton(getString(R.string.bc_dialog_button_ok), new DialogInterface.OnClickListener() { // from class: e3.c0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    ShareMediaActivity.m12698u1(dialogInterface, i9);
                }
            });
            builderM12720U02.show();
            return;
        }
        Iterator<Object> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z8 = false;
                break;
            }
            Object next = it.next();
            if ((next instanceof Group) && ((Group) next).f13711J) {
                z8 = true;
                break;
            }
        }
        if (z8) {
            m12746i1(list);
        } else {
            m12734b2(list, false);
        }
    }

    /* renamed from: h2 */
    public final boolean m12745h2(List<Object> list) {
        boolean zM12743g2 = false;
        for (Object obj : list) {
            if ((obj instanceof Group) && (zM12743g2 = m12743g2((Group) obj))) {
                break;
            }
        }
        return zM12743g2;
    }

    /* renamed from: i1 */
    public final void m12746i1(final List<Object> list) {
        MessageGeneratorFragment.InputType inputTypeM12723W0 = m12723W0();
        Intent intent = getIntent();
        final boolean z8 = true;
        if (intent != null) {
            if (inputTypeM12723W0 != MessageGeneratorFragment.InputType.Photo) {
                if (inputTypeM12723W0 != MessageGeneratorFragment.InputType.Voice) {
                    z8 = false;
                    break;
                } else {
                    z8 = false;
                    break;
                }
            }
            if (intent.getExtras() != null) {
                Iterator it = ((ArrayList) intent.getExtras().getSerializable("photo")).iterator();
                while (it.hasNext()) {
                    ImageItem imageItem = (ImageItem) it.next();
                    if (!TextUtils.isEmpty(imageItem.m16134g()) || !TextUtils.isEmpty(imageItem.m16129b())) {
                        z8 = false;
                        break;
                    }
                }
            }
        }
        runOnUiThread(new Runnable() { // from class: e3.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f16490b.m12708y1(z8, list);
            }
        });
    }

    /* renamed from: i2 */
    public final void m12747i2() {
        ShareType shareTypeM12737d1 = m12737d1();
        if ((shareTypeM12737d1 == ShareType.External_Media || shareTypeM12737d1 == ShareType.EXTERNAL_FILE) && this.f11260e.size() != this.f11259d.size()) {
            final boolean zIsEmpty = this.f11259d.isEmpty();
            final Runnable runnable = new Runnable() { // from class: e3.z
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16530b.m12653N1(zIsEmpty);
                }
            };
            C3123g.m16382a(this).setMessage((this.f11260e.size() == 1 && this.f11263h) ? getResources().getQuantityString(R.plurals.file_type_not_supported, 1) : (this.f11260e.size() == 1 && this.f11262g) ? getString(R.string.file_size_is_exceeded, CLUtility.m16592u0(getApplicationContext(), 52428800L)) : this.f11259d.isEmpty() ? getString(R.string.file_type_not_supported_all) : getResources().getQuantityString(R.plurals.file_type_not_supported, 2)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: e3.a0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    runnable.run();
                }
            }).show();
        }
    }

    /* renamed from: j1 */
    public final void m12748j1(final List<Object> list) {
        if (!m12760p1()) {
            m12754m1(list);
        } else {
            if (m12745h2(list)) {
                return;
            }
            if (list.size() == 1) {
                m12742f2(list.get(0), new Runnable() { // from class: e3.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16478b.m12711z1(list);
                    }
                });
            } else {
                m12711z1(list);
            }
        }
    }

    /* renamed from: j2 */
    public final void m12749j2() {
        runOnUiThread(new Runnable() { // from class: e3.c
            @Override // java.lang.Runnable
            public final void run() {
                this.f16477b.m12659Q1();
            }
        });
    }

    /* renamed from: k1 */
    public final MessageObj m12750k1(Group group, MessageObj.MessageType messageType, String str) {
        try {
            MessageObj messageObjM14558I = C2925v.m14558I(String.valueOf(group.f13727n), messageType, str, 0, null);
            messageObjM14558I.m14758T(C2925v.m14593f(group.f13716c, group.f13723j, messageObjM14558I).m22161k());
            if (C2925v.m14594f0(messageObjM14558I)) {
                messageObjM14558I.m14765a0("3");
            }
            C2950b0.m14916o().m15157B(messageObjM14558I);
            return messageObjM14558I;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    /* renamed from: k2 */
    public final void m12751k2() {
        runOnUiThread(new Runnable() { // from class: e3.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f16522b.m12661S1();
            }
        });
    }

    /* renamed from: l1 */
    public final void m12752l1(AdapterView<?> adapterView, int i9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(adapterView.getItemAtPosition(i9));
        m12754m1(arrayList);
    }

    /* renamed from: l2 */
    public final void m12753l2() {
        String string = getString(R.string.broadcast);
        int size = this.f11267l.getObjects().size();
        if (size > 0) {
            string = string + " (" + size + ")";
            this.f11273r.setEnabled(true);
        } else {
            this.f11273r.setEnabled(false);
        }
        this.f11273r.setText(string);
    }

    /* renamed from: m1 */
    public final void m12754m1(final List<Object> list) {
        if (list.size() == 1) {
            this.f11239B = list.get(0);
        }
        if (!C6456d.m24714D().m24748G()) {
            C5187v0.m20267c(R.string.error_no_network);
            return;
        }
        ShareType shareTypeM12737d1 = m12737d1();
        if (shareTypeM12737d1 == ShareType.Forward && !m12713N0()) {
            m12717R0(list);
            return;
        }
        if (shareTypeM12737d1 == ShareType.External_Text || shareTypeM12737d1 == ShareType.Internal_Text || shareTypeM12737d1 == ShareType.Voice || shareTypeM12737d1 == ShareType.User_Contact) {
            m12717R0(list);
            return;
        }
        final Dialog dialogM16384c = C3123g.m16384c(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_share_target, (ViewGroup) null);
        dialogM16384c.setContentView(viewInflate);
        final ArrayList<ImageItem> arrayListM12727Y0 = m12727Y0();
        ShareType shareType = ShareType.EXTERNAL_FILE;
        if (shareTypeM12737d1 != shareType && arrayListM12727Y0.size() == 1) {
            View viewFindViewById = viewInflate.findViewById(R.id.addTextToShareArea);
            if (viewFindViewById != null) {
                viewFindViewById.setVisibility(0);
                viewInflate.findViewById(R.id.addTextToShareView).setOnClickListener(new View.OnClickListener() { // from class: e3.i
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f16496b.m12621A1(list, dialogM16384c, arrayListM12727Y0, view);
                    }
                });
            }
            View viewFindViewById2 = viewInflate.findViewById(R.id.addVoiceToShareArea);
            if (viewFindViewById2 != null) {
                viewFindViewById2.setVisibility(0);
                viewInflate.findViewById(R.id.addVoiceToShareView).setOnClickListener(new View.OnClickListener() { // from class: e3.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f16503b.m12623B1(list, dialogM16384c, arrayListM12727Y0, view);
                    }
                });
            }
        }
        viewInflate.findViewById(R.id.shareToChatView).setOnClickListener(new View.OnClickListener() { // from class: e3.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f16508b.m12626C1(list, dialogM16384c, view);
            }
        });
        View viewFindViewById3 = viewInflate.findViewById(R.id.shareToAlbumView);
        if (shareTypeM12737d1 == ShareType.External_Video || shareTypeM12737d1 == shareType || shareTypeM12737d1 == ShareType.Internal_Video || shareTypeM12737d1 == ShareType.Internal_File || list.size() > 1) {
            viewFindViewById3.setVisibility(8);
        } else {
            viewFindViewById3.setOnClickListener(new View.OnClickListener() { // from class: e3.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f16512b.m12629D1(list, dialogM16384c, view);
                }
            });
        }
        viewInflate.findViewById(R.id.shareToCancel).setOnClickListener(new View.OnClickListener() { // from class: e3.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16384c.dismiss();
            }
        });
        CLUtility.m16578q2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* renamed from: m2 */
    public final void m12755m2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList parcelableArrayListExtra;
        this.f11258c.clear();
        this.f11259d.clear();
        this.f11260e.clear();
        if ((m12737d1() != ShareType.External_Media && m12737d1() != ShareType.EXTERNAL_FILE) || (parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("android.intent.extra.STREAM")) == null || parcelableArrayListExtra.isEmpty()) {
            return;
        }
        Iterator it = parcelableArrayListExtra.iterator();
        while (true) {
            File file = null;
            if (!it.hasNext()) {
                break;
            }
            Uri uri = (Uri) it.next();
            if (CLUtility.m16417A1(uri) && !TextUtils.isEmpty(uri.getPath())) {
                file = new File(uri.getPath());
            }
            if (file == null) {
                file = new File(CLUtility.m16576q0(getApplicationContext(), uri));
            }
            this.f11260e.add(new Pair<>(file, uri));
        }
        if (C5287b.m20579b(Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE, this)) {
            m12731a1(null);
        }
    }

    /* renamed from: n1 */
    public final void m12756n1() {
        this.f11271p.setVisibility(0);
        if (m12758o1()) {
            this.f11267l.setPrefix(getString(R.string.group_broadcast_search_hint));
        } else {
            this.f11267l.setPrefix(getString(R.string.group_add_member_search_hint));
        }
        this.f11267l.m17437s(false);
        this.f11267l.setTokenListener(new C2461b());
    }

    /* renamed from: n2 */
    public final void m12757n2() {
        this.f11261f.clear();
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("android.intent.extra.STREAM");
        if (m12737d1() != ShareType.External_Video || parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
            return;
        }
        Iterator it = parcelableArrayListExtra.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            this.f11261f.put(CLUtility.m16576q0(getApplicationContext(), uri), uri);
        }
    }

    /* renamed from: o1 */
    public final boolean m12758o1() {
        return m12737d1() == ShareType.Broadcast;
    }

    /* renamed from: o2 */
    public final void m12759o2() {
        String string = getString(R.string.forward);
        int size = this.f11267l.getObjects().size();
        if (size > 0) {
            string = string + " (" + size + ")";
            this.f11273r.setEnabled(true);
        } else {
            this.f11273r.setEnabled(false);
        }
        this.f11273r.setText(string);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        ImageItem imageItem;
        String string;
        String string2;
        String string3;
        super.onActivityResult(i9, i10, intent);
        if (i9 == 2210 && i10 == -1 && intent != null && intent.getBooleanExtra("start_import", false)) {
            try {
                imageItem = m12727Y0().get(intent.getIntExtra("position", -1));
            } catch (Exception e9) {
                e9.printStackTrace();
                imageItem = null;
            }
            if (imageItem == null) {
                return;
            }
            Bundle extras = intent.getExtras();
            String str = "";
            if (extras != null) {
                String string4 = extras.getString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, "");
                string2 = extras.getString("voicePath", "");
                string3 = extras.getString("duration", "");
                string = extras.getString("handDrawImg", "");
                str = string4;
            } else {
                string = "";
                string2 = string;
                string3 = string2;
            }
            imageItem.m16153z(str);
            imageItem.m16150w(string2);
            imageItem.m16149v(string3);
            imageItem.m16120A(string);
            Object obj = this.f11239B;
            if (obj instanceof Friend) {
                m12725X0((Friend) obj, new C2462c());
            } else if (obj instanceof Group) {
                m12738d2((Group) obj, true);
            }
            this.f11239B = null;
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super.onCreate(bundle);
        if (bundle != null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_share_media);
        this.f11279x = (TextView) findViewById(R.id.topBarTitle);
        DialogC6459g dialogC6459g = new DialogC6459g(this, R.style.FriendSelectorDialog);
        this.f11265j = dialogC6459g;
        dialogC6459g.m24770m(this.f11255R);
        this.f11240C = new DialogC3133q.b(this).m16410a();
        findViewById(R.id.CancelBtn).setOnClickListener(this.f11245H);
        TextView textView = (TextView) findViewById(R.id.friendBtn);
        this.f11268m = textView;
        textView.setOnClickListener(this.f11246I);
        TextView textView2 = (TextView) findViewById(R.id.groupBtn);
        this.f11269n = textView2;
        textView2.setOnClickListener(this.f11247J);
        TextView textView3 = (TextView) findViewById(R.id.chatBtn);
        this.f11270o = textView3;
        textView3.setOnClickListener(this.f11248K);
        ListView listView = (ListView) findViewById(R.id.shareListView);
        this.f11266k = listView;
        listView.setOnItemClickListener(this.f11250M);
        this.f11266k.setOnScrollListener(this.f11253P);
        this.f11277v = findViewById(R.id.AlphabeticScrollTextLayout);
        this.f11278w = (TextView) findViewById(R.id.AlphabeticScrollTextView);
        boolean zM12766s1 = m12766s1();
        this.f11272q = findViewById(R.id.ShareMediaEmptyView);
        this.f11274s = new C6287a(this, R.layout.view_item_friends_friends, new ArrayList());
        this.f11275t = new C6290d(this, R.layout.view_item_friends_groups, new ArrayList());
        this.f11276u = new C2931y(this, R.layout.view_item_chat_group, new ArrayList());
        this.f11267l = (PeopleAndGroupCompleteView) findViewById(R.id.token_complete);
        this.f11271p = findViewById(R.id.linearLayoutLayoutSearch);
        Button button = (Button) findViewById(R.id.send);
        this.f11273r = button;
        if (zM12766s1) {
            this.f11274s.m25236e(true);
            this.f11275t.m25236e(true);
            this.f11276u.m25236e(true);
            m12756n1();
            this.f11273r.setVisibility(0);
            this.f11273r.setEnabled(false);
            if (m12758o1()) {
                this.f11279x.setText(getResources().getString(R.string.broadcast) + "(2/2)");
                findViewById(R.id.menuLayoutArea).setVisibility(8);
                this.f11273r.setText(R.string.broadcast);
                this.f11273r.setOnClickListener(this.f11252O);
            } else {
                this.f11273r.setOnClickListener(this.f11251N);
            }
        } else {
            button.setVisibility(8);
            this.f11271p.setVisibility(8);
        }
        this.f11264i = new FriendsClient();
        m12724W1();
        m12726X1();
        m12722V1();
        m12755m2();
        m12757n2();
        if (m12758o1()) {
            m12761p2(this.f11275t);
        } else {
            m12761p2(this.f11274s);
        }
        XMPPManager.m14184g0().m14206G(this.f11257T);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        C3199c c3199c = this.f11281z;
        if (c3199c != null) {
            c3199c.m17033C(null);
            this.f11281z.m17036d();
        }
        XMPPManager.m14184g0().m14232Y0(this.f11257T);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super.onNewIntent(intent);
        setIntent(intent);
        m12755m2();
        m12757n2();
    }

    /* renamed from: p1 */
    public final boolean m12760p1() {
        return m12737d1() == ShareType.Forward;
    }

    /* renamed from: p2 */
    public final void m12761p2(final ListAdapter listAdapter) {
        ((C6597b) listAdapter).getFilter().filter(this.f11267l.getFilterSearchText(), new Filter.FilterListener() { // from class: e3.m
            @Override // android.widget.Filter.FilterListener
            public final void onFilterComplete(int i9) {
                this.f16516b.m12663T1(listAdapter, i9);
            }
        });
        this.f11268m.setSelected(this.f11274s == listAdapter);
        this.f11269n.setSelected(this.f11275t == listAdapter);
        this.f11270o.setSelected(this.f11276u == listAdapter);
    }

    /* renamed from: q1 */
    public final boolean m12762q1() {
        ArrayList<String> arrayListM12733b1 = m12733b1();
        if (arrayListM12733b1 == null || arrayListM12733b1.size() == 0) {
            return false;
        }
        Iterator<String> it = arrayListM12733b1.iterator();
        while (it.hasNext()) {
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(it.next());
            if (messageObjM15179r == null) {
                return false;
            }
            if (messageObjM15179r.m14778p() != MessageObj.MessageType.Photo && messageObjM15179r.m14778p() != MessageObj.MessageType.File && messageObjM15179r.m14778p() != MessageObj.MessageType.Text && messageObjM15179r.m14778p() != MessageObj.MessageType.ReplyText) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: q2 */
    public final void m12763q2(Group group, boolean z8, boolean z9) {
        switch (C2468i.f11300a[m12737d1().ordinal()]) {
            case 1:
                long[] jArrM12729Z0 = m12729Z0();
                if (jArrM12729Z0 != null) {
                    m12715P0(jArrM12729Z0, group.f13718e, group, z8, this.f11280y, z9);
                    break;
                }
                break;
            case 2:
                Pair<String, Long> pairM12741f1 = m12741f1();
                if (pairM12741f1 != null) {
                    m12715P0(new long[]{((Long) pairM12741f1.second).longValue()}, group.f13720g, group, z8, false, z9);
                    break;
                }
                break;
            case 3:
            case 4:
                m12765r2(group.f13718e, group, z8);
                break;
            case 5:
            case 6:
                long[] longArrayExtra = getIntent().getLongArrayExtra("share_media_id");
                if (longArrayExtra != null) {
                    m12715P0(longArrayExtra, group.f13718e, group, z8, false, z9);
                    break;
                }
                break;
        }
    }

    /* renamed from: r1 */
    public final boolean m12764r1() {
        ArrayList<String> arrayListM12733b1 = m12733b1();
        if (arrayListM12733b1 == null || arrayListM12733b1.size() == 0) {
            return false;
        }
        Iterator<String> it = arrayListM12733b1.iterator();
        while (it.hasNext()) {
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(it.next());
            if (messageObjM15179r == null) {
                return false;
            }
            if (messageObjM15179r.m14778p() != MessageObj.MessageType.Text && messageObjM15179r.m14778p() != MessageObj.MessageType.ReplyText) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0094 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0026 A[SYNTHETIC] */
    /* renamed from: r2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m12765r2(String str, Group group, boolean z8) {
        ImageItem imageItem;
        JSONException e9;
        ImageItem imageItem2;
        if (this.f11259d.isEmpty()) {
            return;
        }
        if (this.f11281z == null) {
            this.f11281z = new C3199c();
            C2464e c2464e = new C2464e(z8, group);
            this.f11254Q = c2464e;
            this.f11281z.m17033C(c2464e);
        }
        Iterator<Object> it = this.f11259d.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof ImageItem) {
                ImageItem imageItem3 = (ImageItem) next;
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
                            Log.d(f11237U, Log.getStackTraceString(e9));
                            imageItem2 = imageItem;
                            if (CLUtility.m16457K1(imageItem2.m16144q(), CLUtility.m16510Z1(imageItem2.m16145r()))) {
                            }
                        }
                    } catch (JSONException e11) {
                        imageItem = imageItem3;
                        e9 = e11;
                    }
                    imageItem2 = imageItem;
                }
                if (CLUtility.m16457K1(imageItem2.m16144q(), CLUtility.m16510Z1(imageItem2.m16145r()))) {
                    if (imageItem2.m16134g() != null && !imageItem2.m16134g().isEmpty()) {
                        this.f11281z.m17039q(str, imageItem2, null, null, imageItem2.m16134g(), "0");
                    } else if (imageItem2.m16129b() == null || imageItem2.m16129b().isEmpty()) {
                        this.f11281z.m17040r(str, imageItem2, null, null, null, "0", group.f13711J);
                    } else {
                        this.f11281z.m17039q(str, imageItem2, group.f13720g, imageItem2.m16129b(), null, imageItem2.m16128a());
                    }
                }
            } else if (next instanceof FileItem) {
                this.f11281z.m17038p(str, (FileItem) next, group.f13711J);
            }
        }
        if (this.f11281z.m17042t().size() <= 0) {
            C5187v0.m20267c(R.string.error_load_photo_fail);
            return;
        }
        if (!this.f11265j.isShowing()) {
            runOnUiThread(new Runnable() { // from class: e3.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16526b.m12664U1();
                }
            });
        }
        this.f11281z.m17044v();
        this.f11281z.m17035F();
    }

    /* renamed from: s1 */
    public final boolean m12766s1() {
        ShareType shareTypeM12737d1 = m12737d1();
        return shareTypeM12737d1 == ShareType.Forward || shareTypeM12737d1 == ShareType.Internal_Media || shareTypeM12737d1 == ShareType.Internal_Video || shareTypeM12737d1 == ShareType.Internal_File || shareTypeM12737d1 == ShareType.Broadcast;
    }
}
