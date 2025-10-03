package com.cyberlink.you.activity.ulauncher;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.BlockUserAlertActivity;
import com.cyberlink.you.activity.BreakUpFriendActivity;
import com.cyberlink.you.activity.GroupEditActivity;
import com.cyberlink.you.activity.GroupInfoActivity;
import com.cyberlink.you.activity.GroupMemberListActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.friend.FriendAddActivity;
import com.cyberlink.you.activity.friend.FriendInfoUpdateActivity;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.activity.ulauncher.C2558d;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendGroup;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.CharUtils;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5152i0;
import p116k4.C5170o0;
import p116k4.C5172p;
import p136m3.C5321e;
import p201t3.C6301o;
import p209u2.C6386w;
import p218v2.C6456d;

/* renamed from: com.cyberlink.you.activity.ulauncher.e */
/* loaded from: classes.dex */
public class C2559e extends AbstractC2555a implements C2558d.b {

    /* renamed from: P */
    public static final k f11625P;

    /* renamed from: Q */
    public static final k f11626Q;

    /* renamed from: R */
    public static final k f11627R;

    /* renamed from: S */
    public static final k f11628S;

    /* renamed from: T */
    public static final k f11629T;

    /* renamed from: U */
    public static final k f11630U;

    /* renamed from: V */
    public static final k f11631V;

    /* renamed from: W */
    public static final k f11632W;

    /* renamed from: X */
    public static final k f11633X;

    /* renamed from: D */
    public ProgressDialog f11638D;

    /* renamed from: d */
    public l f11648d;

    /* renamed from: f */
    public FriendsClient f11650f;

    /* renamed from: g */
    public View f11651g;

    /* renamed from: h */
    public View f11652h;

    /* renamed from: i */
    public View f11653i;

    /* renamed from: j */
    public View f11654j;

    /* renamed from: k */
    public Dialog f11655k;

    /* renamed from: l */
    public EditText f11656l;

    /* renamed from: m */
    public TextView f11657m;

    /* renamed from: n */
    public RelativeLayout f11658n;

    /* renamed from: o */
    public RelativeLayout f11659o;

    /* renamed from: p */
    public ExpandableListView f11660p;

    /* renamed from: q */
    public C2558d f11661q;

    /* renamed from: r */
    public List<Group> f11662r;

    /* renamed from: t */
    public p f11664t;

    /* renamed from: u */
    public o f11665u;

    /* renamed from: v */
    public m f11666v;

    /* renamed from: w */
    public m f11667w;

    /* renamed from: x */
    public r f11668x;

    /* renamed from: y */
    public q f11669y;

    /* renamed from: z */
    public boolean f11670z;

    /* renamed from: N */
    public static final C6386w f11623N = new C6386w();

    /* renamed from: O */
    public static final ExecutorService f11624O = Executors.newCachedThreadPool();

    /* renamed from: Y */
    public static final Object f11634Y = new Object();

    /* renamed from: e */
    public final int[] f11649e = {557, 257};

    /* renamed from: s */
    public SparseArray<Long> f11663s = new SparseArray<>();

    /* renamed from: A */
    public final Handler f11635A = new b(Looper.getMainLooper());

    /* renamed from: B */
    public C6456d.j f11636B = new C6456d.j() { // from class: g3.w1
        @Override // p218v2.C6456d.j
        public final void onConnected() {
            this.f17034b.m13329j1();
        }
    };

    /* renamed from: C */
    public ExpandableListView.OnChildClickListener f11637C = new c();

    /* renamed from: E */
    public C2558d.f f11639E = new d();

    /* renamed from: F */
    public View.OnClickListener f11640F = new f();

    /* renamed from: G */
    public View.OnClickListener f11641G = new g();

    /* renamed from: H */
    public TextWatcher f11642H = new h();

    /* renamed from: I */
    public TextView.OnEditorActionListener f11643I = new TextView.OnEditorActionListener() { // from class: g3.x1
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
            return this.f17040b.m13279c1(textView, i9, keyEvent);
        }
    };

    /* renamed from: J */
    public View.OnClickListener f11644J = new i();

    /* renamed from: K */
    public C5321e.m f11645K = new C5321e.m() { // from class: g3.y1
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f17048b.m13281d1(c2904l, map);
        }
    };

    /* renamed from: L */
    public View.OnClickListener f11646L = new j();

    /* renamed from: M */
    public AbsListView.OnScrollListener f11647M = new a();

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$a */
    public class a implements AbsListView.OnScrollListener {

        /* renamed from: a */
        public boolean f11671a = false;

        /* renamed from: b */
        public int f11672b = -1;

        /* renamed from: c */
        public String f11673c = "";

        public a() {
        }

        /* renamed from: a */
        public final String m13360a(String str) {
            return str.length() > 0 ? String.valueOf(Character.toChars(str.codePointAt(0))) : "";
        }

        /* renamed from: b */
        public final void m13361b(boolean z8) {
            if (C2559e.this.f11658n == null) {
                return;
            }
            C2559e.this.f11658n.setVisibility(z8 ? 0 : 8);
        }

        /* renamed from: c */
        public final void m13362c(boolean z8) {
            if (C2559e.this.f11659o == null) {
                return;
            }
            C2559e.this.f11659o.setVisibility(z8 ? 0 : 8);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
        /* renamed from: d */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void m13363d() {
            Friend friend;
            long expandableListPosition = C2559e.this.f11660p.getExpandableListPosition(C2559e.this.f11660p.getFirstVisiblePosition());
            boolean z8 = false;
            boolean z9 = true;
            if (ExpandableListView.getPackedPositionType(expandableListPosition) == 1) {
                int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                int iM13199a0 = C2559e.this.f11661q.m13199a0(packedPositionGroup);
                if (1 != iM13199a0) {
                    if (2 != iM13199a0 || (friend = (Friend) C2559e.this.f11661q.getChild(packedPositionGroup, packedPositionChild)) == null) {
                        z9 = false;
                    } else {
                        this.f11673c = m13360a(friend.m15621b());
                        C2559e.this.f11657m.setText(this.f11673c);
                        z9 = false;
                        z8 = true;
                    }
                }
            }
            m13362c(z8);
            m13361b(z9);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i9, int i10, int i11) {
            int firstVisiblePosition;
            if (this.f11671a && this.f11672b != (firstVisiblePosition = C2559e.this.f11660p.getFirstVisiblePosition())) {
                this.f11672b = firstVisiblePosition;
                m13363d();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i9) {
            if (i9 == 0) {
                this.f11671a = false;
                m13362c(false);
                m13361b(false);
            } else if (i9 == 1) {
                this.f11671a = true;
                CLUtility.m16589t1(C2559e.this.getActivity());
            }
            this.f11672b = -1;
            this.f11673c = "";
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$b */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i9 = message.what;
            if (i9 == 0) {
                C2559e.this.m13318E1();
            } else if (i9 == 1) {
                C2559e.this.m13316D1();
            } else {
                if (i9 != 2) {
                    return;
                }
                C2559e.this.m13329j1();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$c */
    public class c implements ExpandableListView.OnChildClickListener {
        public c() {
        }

        @Override // android.widget.ExpandableListView.OnChildClickListener
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i9, int i10, long j9) {
            int iM13199a0 = C2559e.this.f11661q.m13199a0(i9);
            if (iM13199a0 == 1) {
                Group group = (Group) view.getTag();
                Intent intent = new Intent(C2559e.this.getActivity(), (Class<?>) GroupInfoActivity.class);
                intent.putExtra("Group", group);
                C2559e.this.startActivity(intent);
            } else if (iM13199a0 == 2 || iM13199a0 == 16 || iM13199a0 == 32) {
                if (!C2559e.this.f11661q.m13182D()) {
                    Friend friend = (Friend) view.getTag();
                    if (C2559e.this.m13327L0(friend)) {
                        C2559e.this.m13333m1();
                    } else {
                        Intent intent2 = new Intent(C2559e.this.getContext(), (Class<?>) FriendProfileActivity.class);
                        intent2.putExtra("friendObj", friend);
                        intent2.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND.name());
                        C2559e.this.startActivityForResult(intent2, 0);
                    }
                }
            } else if (iM13199a0 == 8) {
                Intent intent3 = new Intent(C2559e.this.getContext(), (Class<?>) FriendProfileActivity.class);
                InvitationFriend invitationFriend = (InvitationFriend) view.getTag();
                if (invitationFriend != null) {
                    intent3.putExtra("InvitationFriend", invitationFriend);
                    intent3.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION.name());
                    C2559e.this.startActivityForResult(intent3, 5);
                }
            } else if (iM13199a0 == 4) {
                Intent intent4 = new Intent(C2559e.this.getContext(), (Class<?>) FriendProfileActivity.class);
                InvitationFriend invitationFriend2 = (InvitationFriend) view.getTag();
                if (invitationFriend2 != null) {
                    intent4.putExtra("InvitationFriend", invitationFriend2);
                    intent4.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_SENT_INVITATION.name());
                    C2559e.this.startActivityForResult(intent4, 4);
                }
            }
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$d */
    public class d implements C2558d.f {
        public d() {
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2558d.f
        /* renamed from: a */
        public void mo13227a(int i9, int i10) {
            m13364d(i10, false);
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2558d.f
        /* renamed from: b */
        public void mo13228b(int i9, int i10) {
            m13364d(i10, true);
        }

        @Override // com.cyberlink.you.activity.ulauncher.C2558d.f
        /* renamed from: c */
        public void mo13229c() {
            C2559e.this.m13347t1();
        }

        /* renamed from: d */
        public final void m13364d(int i9, boolean z8) {
            Globals.m7388i0().m7631v4(((Long) C2559e.this.f11663s.get(i9)).longValue(), z8);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$e */
    public class e extends Dialog {
        public e(Context context, int i9) {
            super(context, i9);
        }

        @Override // android.app.Dialog
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (zOnTouchEvent || motionEvent.getAction() != 0) {
                return zOnTouchEvent;
            }
            dismiss();
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$f */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2559e.this.m12960h()) {
                return;
            }
            C2559e.this.f11655k.dismiss();
            C2559e.this.startActivity(new Intent(C2559e.this.getContext(), (Class<?>) FriendAddActivity.class));
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$g */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2559e.this.m12960h()) {
                return;
            }
            C2559e.this.f11655k.dismiss();
            Intent intent = new Intent(C2559e.this.getContext(), (Class<?>) GroupEditActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("INTENT_IS_NEW_GROUP", true);
            intent.putExtras(bundle);
            C2559e.this.startActivity(intent);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$h */
    public class h implements TextWatcher {
        public h() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            boolean z8 = !C5170o0.m20169d(charSequence) && C2559e.this.isResumed();
            C2559e.this.f11661q.m13196X(z8);
            C2559e.this.f11654j.setVisibility(z8 ? 0 : 8);
            if (z8) {
                C2559e.this.f11661q.m13206r(charSequence.toString());
            }
            C2559e.this.f11661q.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$i */
    public class i implements View.OnClickListener {
        public i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2559e.this.f11656l.setText("");
            C2559e.this.f11661q.m13196X(false);
            C2559e.this.f11661q.notifyDataSetChanged();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$j */
    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C2559e.this.m12960h()) {
                return;
            }
            if (C2559e.this.f11655k == null) {
                C2559e c2559e = C2559e.this;
                c2559e.f11655k = c2559e.m13336o0(view);
            }
            C2559e.this.f11655k.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$k */
    public static class k {

        /* renamed from: a */
        public int f11684a;

        /* renamed from: b */
        public int f11685b;

        public /* synthetic */ k(int i9, int i10, b bVar) {
            this(i9, i10);
        }

        public k(int i9, int i10) {
            this.f11684a = i9;
            this.f11685b = i10;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$l */
    public interface l extends AbstractC2555a.a {
        /* renamed from: E0 */
        void mo12914E0(String str);

        /* renamed from: v */
        void mo12940v(UpdateFriendBadgeType updateFriendBadgeType, boolean z8);
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$m */
    public class m extends AsyncTask<Integer, Void, List<Object>> {

        /* renamed from: a */
        public int f11686a;

        public m() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Object> doInBackground(Integer... numArr) {
            int iIntValue = numArr[0].intValue();
            this.f11686a = iIntValue;
            return new ArrayList(C2559e.this.f11650f.m15702J(C2559e.this.m13350v0(iIntValue)));
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Object> list) {
            if (C2559e.this.isAdded()) {
                if (list == null) {
                    C2559e.this.f11661q.m13204p(this.f11686a).notifyDataSetChanged();
                } else {
                    C2559e.this.m13359z1(this.f11686a, list);
                }
                if (C6456d.m24714D().m24748G()) {
                    C2559e.f11624O.submit(C2559e.this.new n(this.f11686a));
                }
            }
        }

        public /* synthetic */ m(C2559e c2559e, b bVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$n */
    public class n implements Runnable {

        /* renamed from: b */
        public final int f11688b;

        /* renamed from: c */
        public final FriendsClient.InvitationFriendType f11689c;

        /* renamed from: d */
        public final JSONArray f11690d = new JSONArray();

        /* renamed from: e */
        public final List<InvitationFriend> f11691e = new ArrayList();

        /* renamed from: f */
        public int f11692f = 1;

        public n(int i9) {
            this.f11688b = i9;
            this.f11689c = C2559e.this.m13350v0(i9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m13371c(String str, String str2, String str3, String str4) {
            if (!"200".equals(str3)) {
                Log.e("FriendFragment", "[QueryUserInviteListTask] fail statusCode=" + str3);
                return;
            }
            int iM16553k1 = CLUtility.m16553k1(str4);
            int iM16494U0 = CLUtility.m16494U0(str4);
            if (iM16553k1 <= 0 || iM16494U0 <= 0) {
                m13373e();
                return;
            }
            JSONArray jSONArrayM20196r = C5172p.m20196r(str4);
            if (jSONArrayM20196r != null) {
                this.f11690d.put(jSONArrayM20196r);
            }
            List listM13337o1 = C2559e.this.m13337o1(jSONArrayM20196r);
            if (listM13337o1 != null) {
                this.f11691e.addAll(listM13337o1);
            }
            if (iM16553k1 == iM16494U0) {
                m13373e();
                return;
            }
            int iM16559m = CLUtility.m16559m(iM16553k1, 100);
            int i9 = this.f11692f;
            if (i9 < iM16559m) {
                this.f11692f = i9 + 1;
                run();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m13372d() {
            if (this.f11691e.size() == 0) {
                C2559e.this.f11661q.m13204p(this.f11688b).notifyDataSetChanged();
            } else {
                C2559e.this.m13359z1(this.f11688b, this.f11691e);
            }
        }

        /* renamed from: e */
        public final void m13373e() {
            if (C2559e.this.f11650f != null) {
                C2559e.this.f11650f.m15722a1(this.f11690d, this.f11689c);
            }
            if (this.f11691e.size() > 0) {
                Collections.sort(this.f11691e, new InvitationFriend.C3058b());
            }
            C2559e.this.m12963k(new Runnable() { // from class: g3.d2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16852b.m13372d();
                }
            });
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
            arrayList.add(new C6301o("pageIndex", String.valueOf(this.f11692f)));
            arrayList.add(new C6301o("pageSize", String.valueOf(100)));
            if (this.f11688b == 4) {
                arrayList.add(new C6301o("inviteStatus", "Inviting"));
                arrayList.add(new C6301o("inviteStatus", "Declined"));
            }
            C2559e.this.f11650f.m15734m("invite", C2559e.this.f11650f.m15718W(this.f11689c), arrayList, new FriendsClient.InterfaceC3051i() { // from class: g3.c2
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f16846a.m13371c(str, str2, str3, str4);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$o */
    public class o extends AsyncTask<Void, Void, List<Object>> {
        public o() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Object> doInBackground(Void... voidArr) {
            List listM13344s0 = C2559e.this.m13344s0(C2950b0.m14899A().m15025q());
            Collections.sort(listM13344s0, new FriendGroup.C3042a());
            return new ArrayList(listM13344s0);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Object> list) {
            if (C2559e.this.isAdded()) {
                C2559e.this.m13355x1(list);
                ArrayList arrayList = new ArrayList();
                FriendGroup friendGroupM13346t0 = null;
                for (Object obj : list) {
                    Friend friend = (Friend) obj;
                    if (friend != null) {
                        if (!friend.m15623d().equals("Corporate")) {
                            arrayList.add(obj);
                        } else if (friendGroupM13346t0 == null) {
                            C2559e c2559e = C2559e.this;
                            friendGroupM13346t0 = c2559e.m13346t0(c2559e.getString(R.string.official_accounts), 1);
                            friendGroupM13346t0.f13660r = "Corporate";
                        } else {
                            friendGroupM13346t0.f13670B++;
                        }
                    }
                }
                if (friendGroupM13346t0 != null) {
                    arrayList.add(0, friendGroupM13346t0);
                }
                C2559e.this.m13359z1(2, arrayList);
                C2559e.this.m13320F1();
            }
        }

        public /* synthetic */ o(C2559e c2559e, b bVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$p */
    public class p extends AsyncTask<Void, Void, List<Object>> {

        /* renamed from: a */
        public boolean f11695a;

        public p(boolean z8) {
            this.f11695a = z8;
        }

        /* renamed from: a */
        public final void m13376a() {
            long time = new Date().getTime();
            ArrayList arrayList = new ArrayList();
            for (Group group : C2559e.this.f11662r) {
                boolean z8 = true;
                if (group.m15748d() == 0 ? time - group.f13726m.longValue() <= 2592000000L : time - group.m15748d() <= 2592000000L) {
                    z8 = false;
                }
                if (z8) {
                    C2559e.this.mo13215a(group);
                    arrayList.add(group);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C2559e.this.f11662r.remove((Group) it.next());
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public List<Object> doInBackground(Void... voidArr) {
            C2559e.this.f11662r = C2950b0.m14912k().m15074k();
            if (C2559e.this.f11662r == null) {
                return null;
            }
            if (Globals.m7388i0().m7586o()) {
                m13376a();
            }
            Collections.sort(C2559e.this.f11662r, new Group.C3055b());
            return new ArrayList(C2559e.this.f11662r);
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Object> list) {
            if (C2559e.this.isAdded()) {
                if (this.f11695a) {
                    C2559e.this.m13314C1();
                }
                C2559e.this.m13359z1(1, list);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$q */
    public class q extends AsyncTask<Void, Void, List<Object>> {
        public q() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Object> doInBackground(Void... voidArr) {
            List<Friend> listM15031w = C2950b0.m14899A().m15031w();
            if (listM15031w == null) {
                return null;
            }
            return new ArrayList(listM15031w);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Object> list) {
            if (list == null || C2559e.this.f11661q == null || !C2559e.this.isAdded()) {
                return;
            }
            C2559e.this.m13359z1(32, list);
        }

        public /* synthetic */ q(C2559e c2559e, b bVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.e$r */
    public class r extends AsyncTask<Void, Void, Void> {
        public r() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            if (C2559e.this.f11662r == null) {
                return null;
            }
            for (Group group : C2559e.this.f11662r) {
                if (isCancelled()) {
                    return null;
                }
                group.m15754l();
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r12) {
        }

        public /* synthetic */ r(C2559e c2559e, b bVar) {
            this();
        }
    }

    static {
        b bVar = null;
        f11625P = new k(1, R.string.chat_group_more_dialog_leave_chat, bVar);
        f11626Q = new k(2, R.string.archive_chat_btn, bVar);
        f11627R = new k(3, R.string.friend_change_title_firend_nikname, bVar);
        f11628S = new k(4, R.string.chat, bVar);
        f11629T = new k(5, R.string.friends_friends_title_frieds_block, bVar);
        f11630U = new k(6, R.string.break_up, bVar);
        f11631V = new k(7, R.string.friends_friends_title_request_accept, bVar);
        f11632W = new k(8, R.string.friends_friends_title_request_not_now, bVar);
        f11633X = new k(9, R.string.friends_friends_title_inviting_cancel, bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M0 */
    public /* synthetic */ void m13247M0(InvitationFriend invitationFriend, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            m13323I(invitationFriend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m13249N0(InvitationFriend invitationFriend, Friend friend) {
        C2558d c2558d = this.f11661q;
        if (c2558d != null) {
            c2558d.m13190R(8, invitationFriend);
            this.f11661q.m13186H(friend);
            this.f11661q.m13187I(friend);
            this.f11661q.notifyDataSetChanged();
            m13320F1();
            m13345s1();
        }
        l lVar = this.f11648d;
        if (lVar != null) {
            lVar.mo12940v(UpdateFriendBadgeType.UFB_TYPE_UPDATE, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m13251O0(final InvitationFriend invitationFriend) {
        final Friend friendM15727f0 = this.f11650f.m15727f0(Long.toString(invitationFriend.f13747h));
        m12963k(new Runnable() { // from class: g3.m1
            @Override // java.lang.Runnable
            public final void run() {
                this.f16960b.m13249N0(invitationFriend, friendM15727f0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P0 */
    public /* synthetic */ void m13253P0(InvitationFriend invitationFriend, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            m13334n0(invitationFriend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q0 */
    public /* synthetic */ void m13255Q0(InvitationFriend invitationFriend) {
        this.f11661q.m13190R(4, invitationFriend).notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R0 */
    public /* synthetic */ void m13257R0(String str, String str2, String str3, String str4) {
        Group groupM20186h;
        ProgressDialog progressDialog = this.f11638D;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        if ("200".equals(str3)) {
            try {
                groupM20186h = C5172p.m20186h(new JSONObject(str4).getJSONObject("result"));
            } catch (JSONException unused) {
                Log.e("FriendFragment", "[FriendListFriends] Parse error. JSONstr=" + str4);
                groupM20186h = null;
            }
            if (groupM20186h != null) {
                m13335n1(groupM20186h);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S0 */
    public /* synthetic */ void m13259S0(long j9) {
        this.f11661q.m13192T(j9);
        this.f11661q.m13193U(j9);
        this.f11661q.m13194V(j9);
        this.f11661q.notifyDataSetChanged();
        m13320F1();
        m13345s1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T0 */
    public /* synthetic */ void m13261T0(Friend friend, Friend friend2) {
        if (this.f11661q != null) {
            if (friend.m15623d().equals("Corporate")) {
                this.f11661q.m13186H(friend2);
            } else {
                this.f11661q.m13186H(friend);
            }
            this.f11661q.m13187I(friend);
            if (m13324I0()) {
                this.f11661q.m13205q(2, this.f11656l.getText().toString());
                this.f11661q.m13205q(16, this.f11656l.getText().toString());
            }
            this.f11661q.notifyDataSetChanged();
            m13320F1();
            m13345s1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U0 */
    public /* synthetic */ void m13263U0(Friend friend, FriendGroup friendGroup) {
        if (this.f11661q != null) {
            if (friend.m15623d().equals("Corporate")) {
                this.f11661q.m13186H(friendGroup);
            } else {
                this.f11661q.m13186H(friend);
            }
            this.f11661q.m13187I(friend);
            this.f11661q.notifyDataSetChanged();
            m13320F1();
            m13345s1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W0 */
    public /* synthetic */ void m13267W0(Friend friend) {
        C2558d c2558d = this.f11661q;
        if (c2558d != null) {
            c2558d.m13188J(friend);
            this.f11661q.notifyDataSetChanged();
            m13320F1();
            m13345s1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X0 */
    public /* synthetic */ void m13269X0() {
        Log.d("FriendFragment", "[handleUserInfoUpdate] Update UI");
        this.f11661q.m13197Y(2, new FriendGroup.C3042a()).notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y0 */
    public /* synthetic */ void m13271Y0() {
        synchronized (f11634Y) {
            if (!this.f11670z) {
                m13312B1(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z0 */
    public /* synthetic */ void m13273Z0(Group group, String str, String str2, String str3, String str4) {
        JSONObject jSONObject;
        synchronized (f11634Y) {
            this.f11670z = true;
            if ("200".equals(str3)) {
                try {
                    jSONObject = new JSONObject(str4);
                } catch (JSONException unused) {
                    Log.e("FriendFragment", "[FriendListFriends] Parse error. JSONstr=" + str4);
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    Log.d("FriendFragment", "[leaveGroup] Finish");
                    C5321e.m20824o().m20893v0(group, group.f13727n);
                    C5321e.m20824o().m20896x0(null, C5321e.m20823m(String.valueOf(group.f13727n), String.valueOf(Globals.m7388i0().m7568k1())));
                }
            } else {
                Log.d("FriendFragment", "[leaveGroup] status code = " + str3);
            }
            m13312B1(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a1 */
    public /* synthetic */ void m13275a1(Group group, DialogInterface dialogInterface, int i9) {
        m13328i1(group);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b1 */
    public /* synthetic */ void m13277b1(Group group, DialogInterface dialogInterface, int i9) {
        m13328i1(group);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c1 */
    public /* synthetic */ boolean m13279c1(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 3) {
            return false;
        }
        CLUtility.m16589t1(getActivity());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: d1 */
    public /* synthetic */ boolean m13281d1(C2904l c2904l, Map map) throws NumberFormatException {
        Log.d("FriendFragment", "onReceive");
        String strM14428m = c2904l != null ? c2904l.m14428m() : null;
        String str = (String) map.get("eventType");
        str.hashCode();
        char c9 = 65535;
        switch (str.hashCode()) {
            case -2138412222:
                if (str.equals("user.status.updated")) {
                    c9 = 0;
                    break;
                }
                break;
            case -1868147997:
                if (str.equals("group.member.created")) {
                    c9 = 1;
                    break;
                }
                break;
            case -1645150902:
                if (str.equals("group.group.created")) {
                    c9 = 2;
                    break;
                }
                break;
            case -1524614164:
                if (str.equals("friend.friend.blocked")) {
                    c9 = 3;
                    break;
                }
                break;
            case -1346239468:
                if (str.equals("group.member.deleted")) {
                    c9 = 4;
                    break;
                }
                break;
            case -1270911290:
                if (str.equals("invite.friend.denied")) {
                    c9 = 5;
                    break;
                }
                break;
            case -1267404515:
                if (str.equals("invite.friend.created")) {
                    c9 = 6;
                    break;
                }
                break;
            case -1061631694:
                if (str.equals("group.member.leaved")) {
                    c9 = 7;
                    break;
                }
                break;
            case -826276900:
                if (str.equals("user.coverart.updated")) {
                    c9 = '\b';
                    break;
                }
                break;
            case -772340311:
                if (str.equals("user.avatar.updated")) {
                    c9 = '\t';
                    break;
                }
                break;
            case -714851814:
                if (str.equals("group.admin.created")) {
                    c9 = '\n';
                    break;
                }
                break;
            case -474621720:
                if (str.equals("friend.friend.created")) {
                    c9 = 11;
                    break;
                }
                break;
            case -306809838:
                if (str.equals("invite.friend.accepted")) {
                    c9 = '\f';
                    break;
                }
                break;
            case -228547230:
                if (str.equals("friend.friend.hided")) {
                    c9 = CharUtils.f19105CR;
                    break;
                }
                break;
            case -192943285:
                if (str.equals("group.admin.deleted")) {
                    c9 = 14;
                    break;
                }
                break;
            case 461331:
                if (str.equals("group.display.name.updated")) {
                    c9 = 15;
                    break;
                }
                break;
            case 47286809:
                if (str.equals("friend.friend.deleted")) {
                    c9 = 16;
                    break;
                }
                break;
            case 189290855:
                if (str.equals("group.member.created.v2")) {
                    c9 = 17;
                    break;
                }
                break;
            case 673160263:
                if (str.equals("user.display.name.updated")) {
                    c9 = 18;
                    break;
                }
                break;
            case 687329364:
                if (str.equals("org.membership.self.created")) {
                    c9 = 19;
                    break;
                }
                break;
            case 1209237893:
                if (str.equals("org.membership.self.deleted")) {
                    c9 = 20;
                    break;
                }
                break;
            case 1232334874:
                if (str.equals("org.membership.created")) {
                    c9 = 21;
                    break;
                }
                break;
            case 1386831645:
                if (str.equals("group.group.updated")) {
                    c9 = 22;
                    break;
                }
                break;
            case 1418851467:
                if (str.equals("friend.nickname.updated")) {
                    c9 = 23;
                    break;
                }
                break;
            case 1716541700:
                if (str.equals("invite.friend.canceled")) {
                    c9 = 24;
                    break;
                }
                break;
            case 1754243403:
                if (str.equals("org.membership.deleted")) {
                    c9 = 25;
                    break;
                }
                break;
            case 1819312732:
                if (str.equals("friend.friend.showed")) {
                    c9 = 26;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
            case '\b':
            case '\t':
            case 18:
                m13322H0(C5616j.m22346k(strM14428m));
                return true;
            case 1:
            case 2:
            case 4:
            case 7:
            case '\n':
            case 14:
            case 15:
            case 17:
            case 22:
                m13311B0(str, (String) map.get("groupId"), (String) map.get("userId"));
                return true;
            case 3:
                m13352w0((String) map.get("userId"));
                return true;
            case 5:
            case 6:
            case '\f':
            case 24:
                m13315D0(strM14428m);
                return true;
            case 11:
                m13354x0(strM14428m);
                return true;
            case '\r':
                m13358z0((String) map.get("userId"));
                return true;
            case 16:
                m13356y0((String) map.get("userId"));
                return true;
            case 19:
                m13317E0();
                return true;
            case 20:
                m13317E0();
                return true;
            case 21:
                m13319F0((String) map.get("userId"));
                return true;
            case 23:
                m13322H0((String) map.get("userId"));
                return true;
            case 25:
                m13321G0((String) map.get("userId"));
                return true;
            case 26:
                m13309A0((String) map.get("userId"));
                return true;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e1 */
    public /* synthetic */ void m13283e1(InvitationFriend invitationFriend, String str, String str2, String str3, String str4) {
        if ("200".equals(str3)) {
            m13339p1(invitationFriend);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f1 */
    public /* synthetic */ void m13285f1(InvitationFriend invitationFriend) {
        this.f11661q.m13190R(8, invitationFriend).notifyDataSetChanged();
        this.f11648d.mo12940v(UpdateFriendBadgeType.UFB_TYPE_UPDATE, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g1 */
    public /* synthetic */ void m13287g1(int i9, List list) {
        this.f11661q.m13204p(i9);
        this.f11661q.m13201m(i9, list);
        if (m13324I0()) {
            this.f11661q.m13205q(i9, this.f11656l.getText().toString());
        }
        this.f11661q.notifyDataSetChanged();
        m13345s1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h1 */
    public /* synthetic */ void m13289h1() {
        if (this.f11661q == null || !isAdded()) {
            return;
        }
        List list = (List) this.f11661q.m13210v(2);
        int size = list.size();
        for (int i9 = 0; i9 < list.size(); i9++) {
            Friend friend = (Friend) list.get(i9);
            if (!m13327L0(friend)) {
                break;
            }
            size = (size - 1) + ((FriendGroup) friend).f13670B;
        }
        this.f11648d.mo12914E0(getString(R.string.u_launcher_title_contacts) + " (" + size + ")");
    }

    /* renamed from: A0 */
    public final void m13309A0(String str) {
        final FriendGroup friendGroupM13346t0;
        Log.d("FriendFragment", "handleFriendShowedEvent");
        if (m12960h()) {
            Log.d("FriendFragment", "[handleFriendShowedEvent] InDestroyView");
            return;
        }
        if (str == null) {
            Log.d("FriendFragment", "[handleFriendShowedEvent] parseName(from) return null");
            return;
        }
        final Friend friendM15001A = C2950b0.m14899A().m15001A(str);
        if (friendM15001A == null) {
            Log.d("FriendFragment", "[handleFriendShowedEvent] friend is exist");
            return;
        }
        if (friendM15001A.m15623d().equals("Corporate")) {
            List list = (List) this.f11661q.m13210v(2);
            List<Friend> listM15023o = C2950b0.m14899A().m15023o();
            Friend friend = (Friend) list.get(0);
            if (m13327L0(friend)) {
                friendGroupM13346t0 = (FriendGroup) friend;
                friendGroupM13346t0.f13670B = listM15023o.size();
            } else {
                friendGroupM13346t0 = m13346t0(getString(R.string.official_accounts), listM15023o.size());
            }
        } else {
            friendGroupM13346t0 = null;
        }
        m12963k(new Runnable() { // from class: g3.n1
            @Override // java.lang.Runnable
            public final void run() {
                this.f16968b.m13263U0(friendM15001A, friendGroupM13346t0);
            }
        });
    }

    /* renamed from: A1 */
    public final void m13310A1(boolean z8) {
        View view = this.f11651g;
        if (view == null) {
            return;
        }
        view.setVisibility(z8 ? 0 : 8);
    }

    /* renamed from: B0 */
    public final void m13311B0(final String str, final String str2, final String str3) {
        m12963k(new Runnable() { // from class: g3.p1
            @Override // java.lang.Runnable
            public final void run() throws NumberFormatException {
                this.f16982b.m13265V0(str, str2, str3);
            }
        });
    }

    /* renamed from: B1 */
    public final void m13312B1(boolean z8) {
        l lVar = this.f11648d;
        if (lVar == null || !(lVar instanceof ULauncherActivity)) {
            return;
        }
        ((ULauncherActivity) lVar).m12927f2(z8);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* renamed from: C0, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m13265V0(String str, String str2, String str3) throws NumberFormatException {
        Log.d("FriendFragment", "[handleGroupUpdate]");
        if (m12960h()) {
            Log.d("FriendFragment", "[handleGroupUpdate] InDestroyView");
            return;
        }
        if (str2 == null) {
            Log.d("FriendFragment", "[handleGroupUpdate] groupId is null");
            return;
        }
        boolean zM13324I0 = m13324I0();
        boolean z8 = true;
        List<Group> list = (List) this.f11661q.m13210v(1);
        boolean z9 = (str.equals("group.member.leaved") || str.equals("group.member.deleted")) && str3 != null && str3.equals(String.valueOf(Globals.m7388i0().m7568k1()));
        boolean z10 = z9;
        long j9 = Long.parseLong(str2);
        if (list != null) {
            for (Group group : list) {
                if (group.f13727n == j9) {
                    break;
                }
            }
            group = null;
        } else {
            group = null;
        }
        if (!z9) {
            Group groupM15077n = C2950b0.m14912k().m15077n(str2);
            if (groupM15077n == null || !Group.m15743f(groupM15077n.f13716c)) {
                Log.d("FriendFragment", "[handleGroupUpdate] group does not in DB or it is not circle group");
                return;
            }
            if (group == null) {
                Log.d("FriendFragment", "[handleGroupUpdate] add group.");
                this.f11661q.m13200l(1, groupM15077n);
                this.f11661q.m13197Y(1, new Group.C3055b());
                if (zM13324I0) {
                    this.f11661q.m13205q(1, this.f11656l.getText().toString());
                }
                this.f11661q.notifyDataSetChanged();
                if (z8) {
                    return;
                }
                m13345s1();
                return;
            }
            if (!group.f13717d.equals(groupM15077n.f13717d)) {
                this.f11661q.m13197Y(1, new Group.C3055b()).notifyDataSetChanged();
            }
            group.m15758p(groupM15077n);
        } else if (group != null) {
            Log.d("FriendFragment", "[handleGroupUpdate] remove group.");
            this.f11661q.m13190R(1, group).notifyDataSetChanged();
            if (m13324I0()) {
                this.f11661q.m13205q(1, this.f11656l.getText().toString());
            }
        }
        z8 = z10;
        if (z8) {
        }
    }

    /* renamed from: C1 */
    public final void m13314C1() {
        r rVar = new r(this, null);
        this.f11668x = rVar;
        rVar.executeOnExecutor(f11624O, new Void[0]);
    }

    /* renamed from: D0 */
    public final void m13315D0(String str) {
        if (C5616j.m22346k(str) != null && C6456d.m24714D().m24748G()) {
            ExecutorService executorService = f11624O;
            executorService.submit(new n(4));
            executorService.submit(new n(8));
        }
    }

    /* renamed from: D1 */
    public final void m13316D1() {
        if (m12961i()) {
            return;
        }
        boolean z8 = this.f11661q.m13214z(2) <= 0 && this.f11661q.m13214z(1) <= 0 && this.f11661q.m13214z(4) <= 0 && this.f11661q.m13214z(8) <= 0 && this.f11661q.m13214z(16) <= 0 && this.f11661q.m13214z(32) <= 0;
        m13310A1(z8);
        View view = this.f11653i;
        if (view != null) {
            view.setVisibility(z8 ? 4 : 0);
        }
    }

    /* renamed from: E0 */
    public final void m13317E0() {
        Log.d("FriendFragment", "handleOrganizationSelfEvent");
        if (m12960h()) {
            Log.d("FriendFragment", "[handleFriendDeletedEvent] InDestroyView");
        } else {
            m13357y1();
        }
    }

    /* renamed from: E1 */
    public final void m13318E1() {
        if (this.f11660p == null) {
            return;
        }
        long jM7528c0 = Globals.m7388i0().m7528c0();
        List<Integer> listM13181C = this.f11661q.m13181C();
        int i9 = 0;
        while (true) {
            if (i9 >= listM13181C.size()) {
                break;
            }
            long jLongValue = this.f11663s.get(listM13181C.get(i9).intValue()).longValue();
            boolean zIsGroupExpanded = this.f11660p.isGroupExpanded(i9);
            if ((jLongValue & jM7528c0) != 0) {
                if (!zIsGroupExpanded) {
                    this.f11660p.expandGroup(i9);
                }
            } else if (zIsGroupExpanded) {
                this.f11660p.collapseGroup(i9);
            }
            i9++;
        }
        Iterator<List<Object>> it = this.f11661q.m13207s().values().iterator();
        boolean z8 = false;
        while (it.hasNext()) {
            z8 = it.next().size() == 0;
            if (!z8) {
                break;
            }
        }
        this.f11652h.setVisibility(z8 ? 0 : 8);
        this.f11660p.setVisibility(z8 ? 8 : 0);
    }

    /* renamed from: F0 */
    public final void m13319F0(String str) {
        Log.d("FriendFragment", "handleOrganizationMemberCreatedEvent");
        if (m12960h()) {
            Log.d("FriendFragment", "[handleFriendDeletedEvent] InDestroyView");
            return;
        }
        if (str == null) {
            return;
        }
        final Friend friendM15003C = C2950b0.m14899A().m15003C(str);
        if (friendM15003C == null) {
            Log.d("FriendFragment", "[handleOrganizationMemberCreatedEvent] friend is exist");
        } else {
            m12963k(new Runnable() { // from class: g3.r1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16996b.m13267W0(friendM15003C);
                }
            });
        }
    }

    /* renamed from: F1 */
    public final void m13320F1() {
        m12963k(new Runnable() { // from class: g3.b2
            @Override // java.lang.Runnable
            public final void run() {
                this.f16838b.m13289h1();
            }
        });
    }

    /* renamed from: G0 */
    public final void m13321G0(String str) {
        Log.d("FriendFragment", "handleOrganizationMemberDeletedEvent");
        m13340q0(str);
    }

    /* renamed from: H0 */
    public final void m13322H0(String str) throws NumberFormatException {
        Friend friend;
        Log.d("FriendFragment", "handleUserInfoUpdate");
        if (m12960h()) {
            Log.d("FriendFragment", "[handleUserInfoUpdate] InDestroyView");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.d("FriendFragment", "[handleUserInfoUpdate] userId return empty string !!!");
            return;
        }
        List list = (List) this.f11661q.m13210v(2);
        Friend friendM15001A = C2950b0.m14899A().m15001A(str);
        if (friendM15001A == null) {
            Log.d("FriendFragment", "[handleUserInfoUpdate] friend does not in database!!!");
            return;
        }
        long j9 = Long.parseLong(str);
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                friend = null;
                break;
            } else {
                friend = (Friend) it.next();
                if (friend.f13645c == j9) {
                    break;
                }
            }
        }
        if (friend == null) {
            Log.d("FriendFragment", "[handleUserInfoUpdate] Cannot find the friend info at friend list");
        } else {
            friend.m15628i(friendM15001A);
            m12963k(new Runnable() { // from class: g3.o1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16977b.m13269X0();
                }
            });
        }
    }

    /* renamed from: I */
    public void m13323I(final InvitationFriend invitationFriend) {
        f11624O.execute(new Runnable() { // from class: g3.v1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17028b.m13251O0(invitationFriend);
            }
        });
    }

    /* renamed from: I0 */
    public final boolean m13324I0() {
        EditText editText = this.f11656l;
        return editText != null && editText.length() > 0;
    }

    /* renamed from: J0 */
    public final void m13325J0() {
        C2558d c2558d = new C2558d(getContext(), this, this.f11639E);
        this.f11661q = c2558d;
        c2558d.m13203o(1);
        this.f11661q.m13203o(2);
        this.f11661q.m13203o(4);
        this.f11661q.m13203o(8);
        this.f11661q.m13203o(16);
        this.f11661q.m13203o(32);
    }

    /* renamed from: K0 */
    public final void m13326K0(View view) {
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.AllCategoryExpandableListView);
        this.f11660p = expandableListView;
        expandableListView.setAdapter(this.f11661q);
        this.f11660p.setOnScrollListener(this.f11647M);
        this.f11660p.setOnChildClickListener(this.f11637C);
        registerForContextMenu(this.f11660p);
    }

    /* renamed from: L0 */
    public final boolean m13327L0(Object obj) {
        return obj instanceof FriendGroup;
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2558d.b
    /* renamed from: a */
    public void mo13215a(Group group) {
        group.f13706E = true;
        C2950b0.m14912k().m15062A(String.valueOf(group.f13727n), group, "isArchive");
        C2907m0.m14454I().m14493U(group.f13727n, group.f13723j);
        l lVar = this.f11648d;
        if (lVar != null) {
            lVar.mo12935n0();
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2558d.b
    /* renamed from: b */
    public void mo13216b(final InvitationFriend invitationFriend) {
        this.f11650f.m15707N0(invitationFriend.f13744e, new FriendsClient.InterfaceC3051i() { // from class: g3.i1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f16899a.m13283e1(invitationFriend, str, str2, str3, str4);
            }
        });
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2558d.b
    /* renamed from: c */
    public void mo13217c(Friend friend) {
        Intent intent = new Intent(getContext(), (Class<?>) BlockUserAlertActivity.class);
        intent.putExtra("friend", friend);
        startActivity(intent);
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2558d.b
    /* renamed from: d */
    public void mo13218d(final InvitationFriend invitationFriend) {
        this.f11650f.m15735p(invitationFriend.f13744e, new FriendsClient.InterfaceC3051i() { // from class: g3.z1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f17053a.m13247M0(invitationFriend, str, str2, str3, str4);
            }
        });
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2558d.b
    /* renamed from: e */
    public void mo13219e(final Group group) {
        if (group.f13716c.equals("Dual")) {
            return;
        }
        AlertDialog.Builder builderM16382a = C3123g.m16382a(getActivity());
        builderM16382a.setTitle(getString(R.string.chat_group_more_dialog_leave_chat));
        if (group.f13705D.equals("Community") && group.f13704C) {
            long j9 = group.f13729p;
            if (j9 == 1 && group.f13728o > 1) {
                builderM16382a.setMessage(getString(R.string.you_cannot_leave_this_group_because_you_are_the_only_admin_of_the_group));
                builderM16382a.setPositiveButton(getString(R.string.confirm_btn), (DialogInterface.OnClickListener) null);
                builderM16382a.show();
                return;
            } else if (j9 > 1) {
                builderM16382a.setMessage(getString(R.string.you_are_one_of_the_admins_if_you_leave_this_group_youll_no_longer_be_an_administrator));
                builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: g3.g1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f16887b.m13275a1(group, dialogInterface, i9);
                    }
                });
                builderM16382a.setNegativeButton(getString(R.string.cancel), (DialogInterface.OnClickListener) null);
                builderM16382a.show();
                return;
            }
        }
        builderM16382a.setMessage(getString(R.string.if_you_leave_this_group_this_group_will_be_closed_all_the_chats_text_and_photos_will_be_removed));
        builderM16382a.setPositiveButton(getString(R.string.confirm_btn), new DialogInterface.OnClickListener() { // from class: g3.h1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f16893b.m13277b1(group, dialogInterface, i9);
            }
        });
        builderM16382a.setNegativeButton(getString(R.string.cancel), (DialogInterface.OnClickListener) null);
        builderM16382a.show();
    }

    @Override // com.cyberlink.you.activity.ulauncher.C2558d.b
    /* renamed from: f */
    public void mo13220f(final InvitationFriend invitationFriend) {
        this.f11650f.m15739z(invitationFriend.f13744e, new FriendsClient.InterfaceC3051i() { // from class: g3.f1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f16880a.m13253P0(invitationFriend, str, str2, str3, str4);
            }
        });
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.fragment_friends_tab;
    }

    /* renamed from: i1 */
    public final void m13328i1(final Group group) {
        new Handler().postDelayed(new Runnable() { // from class: g3.k1
            @Override // java.lang.Runnable
            public final void run() {
                this.f16947b.m13271Y0();
            }
        }, 1000L);
        this.f11670z = false;
        this.f11650f.m15697A0(group.f13727n, new FriendsClient.InterfaceC3051i() { // from class: g3.l1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f16952a.m13273Z0(group, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: j1 */
    public final void m13329j1() {
        m13330k1();
        m13331l1();
    }

    /* renamed from: k1 */
    public final void m13330k1() {
        m mVar = new m(this, null);
        this.f11667w = mVar;
        mVar.executeOnExecutor(f11624O, 4);
    }

    /* renamed from: l1 */
    public final void m13331l1() {
        m mVar = new m(this, null);
        this.f11666v = mVar;
        mVar.executeOnExecutor(f11624O, 8);
    }

    /* renamed from: m0 */
    public final void m13332m0(Friend friend) {
        Intent intent = new Intent(getContext(), (Class<?>) BreakUpFriendActivity.class);
        intent.putExtra("friend", friend);
        startActivity(intent);
    }

    /* renamed from: m1 */
    public final void m13333m1() {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = (ArrayList) C2950b0.m14899A().m15023o();
        bundle.putInt("type", 1);
        bundle.putParcelableArrayList("originalMembers", arrayList);
        Intent intent = new Intent();
        intent.setClass(getContext(), GroupMemberListActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 3);
    }

    /* renamed from: n0 */
    public void m13334n0(final InvitationFriend invitationFriend) {
        m12963k(new Runnable() { // from class: g3.u1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17020b.m13255Q0(invitationFriend);
            }
        });
    }

    /* renamed from: n1 */
    public final void m13335n1(Group group) {
        if (m12960h()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("Group", group);
        Intent intent = new Intent(getActivity(), (Class<?>) ChatDialogActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* renamed from: o0 */
    public final Dialog m13336o0(View view) {
        int[] iArr = this.f11649e;
        int[] iArrM13348u0 = {0, 0, iArr[0], iArr[1]};
        if (view != null) {
            iArrM13348u0 = m13348u0(view);
        }
        e eVar = new e(getContext(), R.style.FriendSelectorDialog);
        eVar.setContentView(R.layout.dialog_friend_add_selection);
        eVar.findViewById(R.id.DialogFriendAddSelectionSingleFriend).setOnClickListener(this.f11640F);
        eVar.findViewById(R.id.DialogFriendAddSelectionGroup).setOnClickListener(this.f11641G);
        WindowManager.LayoutParams attributes = eVar.getWindow().getAttributes();
        attributes.gravity = 53;
        attributes.x = iArrM13348u0[0];
        attributes.y = iArrM13348u0[1];
        attributes.width = iArrM13348u0[2];
        attributes.height = iArrM13348u0[3];
        eVar.getWindow().setAttributes(attributes);
        return eVar;
    }

    /* renamed from: o1 */
    public final List<InvitationFriend> m13337o1(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i9 = 0; i9 < jSONArray.length(); i9++) {
            try {
                InvitationFriend invitationFriendM20187i = C5172p.m20187i(jSONArray.getJSONObject(i9));
                if (invitationFriendM20187i != null) {
                    arrayList.add(invitationFriendM20187i);
                }
            } catch (JSONException e9) {
                Log.e("FriendFragment", "[parseInvitationFriendListFriends] 'invitationFriendJObj' parse error. " + e9.getMessage());
            }
        }
        return arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        Group group;
        InvitationFriend invitationFriendM13213y;
        Bundle extras;
        InvitationFriend invitationFriendM13179A;
        Bundle extras2;
        super.onActivityResult(i9, i10, intent);
        if (i9 == 2) {
            if (i10 == -1) {
                m13351v1();
                return;
            }
            return;
        }
        InvitationFriend invitationFriend = null;
        if (i9 == 5) {
            if (i10 == -1) {
                if (intent != null && (extras2 = intent.getExtras()) != null) {
                    invitationFriend = (InvitationFriend) extras2.getParcelable("InvitationFriend");
                }
                if (invitationFriend == null || (invitationFriendM13179A = this.f11661q.m13179A(invitationFriend.f13747h)) == null || !intent.hasExtra("type")) {
                    return;
                }
                FriendProfileActivity.FPA_RETURN_TYPE fpa_return_typeValueOf = FriendProfileActivity.FPA_RETURN_TYPE.valueOf(intent.getStringExtra("type"));
                if (fpa_return_typeValueOf == FriendProfileActivity.FPA_RETURN_TYPE.RETURN_ACCEPT) {
                    m13323I(invitationFriendM13179A);
                    return;
                }
                if (fpa_return_typeValueOf == FriendProfileActivity.FPA_RETURN_TYPE.RETURN_REJECT) {
                    m13339p1(invitationFriendM13179A);
                    return;
                }
                Log.d("FriendFragment", "[onActivityResult] REQUEST_SHOW_REQUEST_INVITATION_PROFILE: return type (%s)" + fpa_return_typeValueOf);
                return;
            }
            return;
        }
        if (i9 == 4) {
            if (i10 == -1) {
                if (intent != null && (extras = intent.getExtras()) != null) {
                    invitationFriend = (InvitationFriend) extras.getParcelable("InvitationFriend");
                }
                if (invitationFriend == null || (invitationFriendM13213y = this.f11661q.m13213y(invitationFriend.f13746g)) == null || !intent.hasExtra("type")) {
                    return;
                }
                FriendProfileActivity.FPA_RETURN_TYPE fpa_return_typeValueOf2 = FriendProfileActivity.FPA_RETURN_TYPE.valueOf(intent.getStringExtra("type"));
                if (fpa_return_typeValueOf2 == FriendProfileActivity.FPA_RETURN_TYPE.RETURN_CANCEL) {
                    m13334n0(invitationFriendM13213y);
                    return;
                }
                Log.d("FriendFragment", "[onActivityResult] REQUEST_SHOW_SENT_INVITATION_PROFILE: return type (%s)" + fpa_return_typeValueOf2);
                return;
            }
            return;
        }
        if (i9 == 3) {
            if (i10 != -1 || intent == null || (group = (Group) intent.getParcelableExtra("StartOtherGroup")) == null) {
                return;
            }
            m13335n1(group);
            return;
        }
        if (i9 == 0 && i10 == -1) {
            m13351v1();
            if (intent == null || !intent.hasExtra("type")) {
                return;
            }
            FriendProfileActivity.FPA_RETURN_TYPE fpa_return_typeValueOf3 = FriendProfileActivity.FPA_RETURN_TYPE.valueOf(intent.getStringExtra("type"));
            if (fpa_return_typeValueOf3 == FriendProfileActivity.FPA_RETURN_TYPE.RETURN_CHAT_DUAL) {
                Group group2 = (Group) intent.getParcelableExtra("StartOtherGroup");
                if (group2 != null) {
                    m13335n1(group2);
                    return;
                }
                return;
            }
            Log.d("FriendFragment", "[onActivityResult] REQUEST_SHOW_USER_INFO: return type (%s)" + fpa_return_typeValueOf3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof l) {
            this.f11648d = (l) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        Intent intent = menuItem.getIntent();
        menuItem.setIntent(null);
        if (intent == null || !intent.hasExtra("data")) {
            return false;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("data");
        if (itemId == f11625P.f11684a) {
            mo13219e((Group) parcelableExtra);
        } else {
            if (itemId == f11626Q.f11684a) {
                Group group = (Group) parcelableExtra;
                mo13215a(group);
                this.f11661q.m13190R(1, group).notifyDataSetChanged();
                return false;
            }
            if (itemId == f11627R.f11684a) {
                m13342r0((Friend) parcelableExtra);
            } else if (itemId == f11628S.f11684a) {
                m13338p0((Friend) parcelableExtra);
            } else if (itemId == f11629T.f11684a) {
                mo13217c((Friend) parcelableExtra);
            } else if (itemId == f11630U.f11684a) {
                m13332m0((Friend) parcelableExtra);
            } else if (itemId == f11633X.f11684a) {
                mo13220f((InvitationFriend) parcelableExtra);
            } else if (itemId == f11631V.f11684a) {
                mo13218d((InvitationFriend) parcelableExtra);
            } else if (itemId == f11632W.f11684a) {
                mo13216b((InvitationFriend) parcelableExtra);
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        C6386w c6386w = f11623N;
        c6386w.m24533e();
        super.onCreate(bundle);
        this.f11663s.put(1, 1L);
        this.f11663s.put(2, 2L);
        this.f11663s.put(4, 4L);
        this.f11663s.put(8, 8L);
        this.f11663s.put(16, 16L);
        this.f11663s.put(32, 32L);
        m13325J0();
        C5321e.m20824o().m20875k(this.f11645K);
        c6386w.m24532d("FriendFragment : onCreate");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007e  */
    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        String strM15621b;
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        ExpandableListView.ExpandableListContextMenuInfo expandableListContextMenuInfo = (ExpandableListView.ExpandableListContextMenuInfo) contextMenuInfo;
        int packedPositionType = ExpandableListView.getPackedPositionType(expandableListContextMenuInfo.packedPosition);
        if (this.f11661q.m13182D()) {
            Log.d("FriendFragment", "[onCreateContextMenu] is EditMode");
            return;
        }
        if (packedPositionType == 1) {
            int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListContextMenuInfo.packedPosition);
            int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListContextMenuInfo.packedPosition);
            int iM13199a0 = this.f11661q.m13199a0(packedPositionGroup);
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            if (iM13199a0 == 1) {
                Group group = (Group) this.f11661q.getChild(packedPositionGroup, packedPositionChild);
                intent.putExtra("data", group);
                if (!group.m15751i()) {
                    arrayList.add(f11625P);
                }
                if (Globals.m7388i0().m7586o()) {
                    arrayList.add(f11626Q);
                }
                strM15621b = group.f13717d;
            } else if (iM13199a0 == 2) {
                Friend friend = (Friend) this.f11661q.getChild(packedPositionGroup, packedPositionChild);
                if (m13327L0(friend)) {
                    return;
                }
                intent.putExtra("data", friend);
                if (friend.f13660r.equals("Official")) {
                    arrayList.add(f11628S);
                } else if (friend.f13660r.equals("Corporate")) {
                    arrayList.add(f11628S);
                    arrayList.add(f11629T);
                } else {
                    arrayList.add(f11627R);
                    arrayList.add(f11628S);
                    arrayList.add(f11629T);
                }
                strM15621b = friend.m15621b();
            } else if (iM13199a0 == 4) {
                InvitationFriend invitationFriend = (InvitationFriend) this.f11661q.getChild(packedPositionGroup, packedPositionChild);
                intent.putExtra("data", invitationFriend);
                arrayList.add(f11633X);
                strM15621b = invitationFriend.f13741b;
            } else if (iM13199a0 == 8) {
                InvitationFriend invitationFriend2 = (InvitationFriend) this.f11661q.getChild(packedPositionGroup, packedPositionChild);
                intent.putExtra("data", invitationFriend2);
                arrayList.add(f11631V);
                arrayList.add(f11632W);
                strM15621b = invitationFriend2.f13741b;
            } else if (iM13199a0 != 16) {
                strM15621b = "";
            }
            if (arrayList.size() > 0) {
                contextMenu.setHeaderTitle(strM15621b);
                int i9 = 0;
                while (i9 < arrayList.size()) {
                    k kVar = (k) arrayList.get(i9);
                    i9++;
                    contextMenu.add(0, kVar.f11684a, i9, getString(kVar.f11685b)).setIntent(intent);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        o oVar = this.f11665u;
        if (oVar != null) {
            oVar.cancel(true);
            this.f11665u = null;
        }
        m mVar = this.f11667w;
        if (mVar != null) {
            mVar.cancel(true);
            this.f11667w = null;
        }
        m mVar2 = this.f11666v;
        if (mVar2 != null) {
            mVar2.cancel(true);
            this.f11666v = null;
        }
        p pVar = this.f11664t;
        if (pVar != null) {
            pVar.cancel(true);
            this.f11664t = null;
        }
        q qVar = this.f11669y;
        if (qVar != null) {
            qVar.cancel(true);
            this.f11669y = null;
        }
        r rVar = this.f11668x;
        if (rVar != null) {
            rVar.cancel(false);
            this.f11668x = null;
        }
        FriendsClient friendsClient = this.f11650f;
        if (friendsClient != null) {
            friendsClient.m15717U0();
            this.f11650f = null;
        }
        C5321e.m20824o().m20832B0(this.f11645K);
        super.onDestroy();
        Log.d("FriendFragment", "onDestroy");
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        C6456d.m24714D().m24753M(this.f11636B);
        FriendsClient friendsClient = this.f11650f;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        C5152i0.m20065b(this.f11638D);
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onDetach() {
        this.f11648d = null;
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        C6386w c6386w = f11623N;
        c6386w.m24533e();
        super.onPause();
        m13310A1(false);
        m13341q1();
        c6386w.m24532d("FriendFragment : onPause");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        C6386w c6386w = f11623N;
        c6386w.m24533e();
        super.onResume();
        View view = this.f11654j;
        if (view != null) {
            view.callOnClick();
        }
        m13343r1();
        m13345s1();
        c6386w.m24532d("FriendFragment : onResume");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        C6386w c6386w = f11623N;
        c6386w.m24533e();
        this.f11653i = view.findViewById(R.id.FriendMainContent);
        this.f11651g = getActivity().findViewById(R.id.ULauncherFriendTabEmptyView);
        this.f11652h = view.findViewById(R.id.emptyFilterTextView);
        this.f11650f = new FriendsClient(true);
        this.f11657m = (TextView) view.findViewById(R.id.AlphabeticScrollTextView);
        this.f11658n = (RelativeLayout) view.findViewById(R.id.AlphabeticScrollHintImageLayout);
        this.f11659o = (RelativeLayout) view.findViewById(R.id.AlphabeticScrollTextLayout);
        m13326K0(view);
        EditText editText = (EditText) view.findViewById(R.id.SearchEditText);
        this.f11656l = editText;
        editText.addTextChangedListener(this.f11642H);
        this.f11656l.setHint(getString(R.string.search));
        this.f11656l.setOnEditorActionListener(this.f11643I);
        View viewFindViewById = view.findViewById(R.id.SearchEditXImage);
        this.f11654j = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f11644J);
        m13353w1(true);
        m13351v1();
        m13357y1();
        this.f11661q.m13195W();
        this.f11648d.mo12937q0(this.f11646L, PageType.Friends);
        m13329j1();
        C6456d.m24714D().m24744B(this.f11636B);
        c6386w.m24532d("FriendFragment : onViewCreated");
    }

    /* renamed from: p0 */
    public final void m13338p0(Friend friend) {
        Group groupM15081r = C2950b0.m14912k().m15081r(friend.f13648f);
        if (groupM15081r != null) {
            m13335n1(groupM15081r);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("displayName", friend.m15621b()));
        arrayList.add(new C6301o("userId", Long.toString(friend.f13645c)));
        arrayList.add(new C6301o("groupType", "Dual"));
        this.f11638D = ProgressDialog.show(getActivity(), "", getString(R.string.processing), true);
        this.f11650f.m15734m("group", "create", arrayList, new FriendsClient.InterfaceC3051i() { // from class: g3.q1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f16991a.m13257R0(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: p1 */
    public void m13339p1(final InvitationFriend invitationFriend) {
        m12963k(new Runnable() { // from class: g3.a2
            @Override // java.lang.Runnable
            public final void run() {
                this.f16831b.m13285f1(invitationFriend);
            }
        });
    }

    /* renamed from: q0 */
    public final void m13340q0(String str) {
        Friend friendM15003C;
        if (m12960h() || C5170o0.m20169d(str) || (friendM15003C = C2950b0.m14899A().m15003C(str)) == null) {
            return;
        }
        if (friendM15003C.m15623d().equals("Corporate")) {
            List<Friend> listM15023o = C2950b0.m14899A().m15023o();
            Friend friend = (Friend) ((List) this.f11661q.m13210v(2)).get(0);
            if (m13327L0(friend)) {
                if (listM15023o.size() == 0) {
                    this.f11661q.m13190R(2, friend);
                } else {
                    ((FriendGroup) friend).f13670B = listM15023o.size();
                }
            }
        }
        final long j9 = friendM15003C.f13645c;
        m12963k(new Runnable() { // from class: g3.s1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17004b.m13259S0(j9);
            }
        });
    }

    /* renamed from: q1 */
    public final void m13341q1() {
        this.f11635A.removeMessages(0);
        this.f11635A.removeMessages(2);
        this.f11635A.removeMessages(1);
    }

    /* renamed from: r0 */
    public final void m13342r0(Friend friend) {
        if (m12960h()) {
            return;
        }
        Intent intent = new Intent(getContext(), (Class<?>) FriendInfoUpdateActivity.class);
        intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, getString(R.string.friend_change_title_firend_nikname));
        intent.putExtra(FirebaseAnalytics.Param.TERM, "nickname");
        intent.putExtra("data", friend);
        startActivityForResult(intent, 2);
    }

    /* renamed from: r1 */
    public final void m13343r1() {
        m13349u1(2, 150L);
    }

    /* renamed from: s0 */
    public final List<Friend> m13344s0(List<Friend> list) {
        ArrayList arrayList = new ArrayList();
        long jLongValue = Globals.m7388i0().m7568k1().longValue();
        for (Friend friend : list) {
            if (friend != null) {
                if (friend.f13645c == jLongValue) {
                    C2950b0.m14899A().m15019k(friend, false, true);
                } else {
                    arrayList.add(friend);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: s1 */
    public final void m13345s1() {
        m13349u1(1, 50L);
    }

    /* renamed from: t0 */
    public final FriendGroup m13346t0(String str, int i9) {
        return new FriendGroup(str, i9);
    }

    /* renamed from: t1 */
    public final void m13347t1() {
        m13349u1(0, 100L);
    }

    /* renamed from: u0 */
    public final int[] m13348u0(View view) {
        if (view == null || this.f11648d == null) {
            return new int[]{0, 0, 0, 0};
        }
        int[] iArr = {64, 0};
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f9 = (displayMetrics.widthPixels * 1.0f) / 1080.0f;
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        return new int[]{(displayMetrics.widthPixels - (iArr2[0] + (view.getWidth() / 2))) - ((int) (iArr[0] * f9)), ((iArr2[1] - CLUtility.m16533f1(getContext())) + view.getHeight()) - ((int) (iArr[1] * f9)), Math.round(this.f11649e[0] * f9), Math.round(this.f11649e[1] * f9)};
    }

    /* renamed from: u1 */
    public final void m13349u1(int i9, long j9) {
        this.f11635A.removeMessages(i9);
        this.f11635A.sendEmptyMessageDelayed(i9, j9);
    }

    /* renamed from: v0 */
    public final FriendsClient.InvitationFriendType m13350v0(int i9) {
        return i9 != 4 ? FriendsClient.InvitationFriendType.RECEIVED : FriendsClient.InvitationFriendType.SENT;
    }

    /* renamed from: v1 */
    public void m13351v1() {
        o oVar = new o(this, null);
        this.f11665u = oVar;
        oVar.executeOnExecutor(f11624O, new Void[0]);
    }

    /* renamed from: w0 */
    public final void m13352w0(String str) {
        Log.d("FriendFragment", "handleFriendBlockEvent");
        m13340q0(str);
    }

    /* renamed from: w1 */
    public void m13353w1(boolean z8) {
        p pVar = new p(z8);
        this.f11664t = pVar;
        pVar.executeOnExecutor(f11624O, new Void[0]);
    }

    /* renamed from: x0 */
    public final void m13354x0(String str) {
        final FriendGroup friendGroupM13346t0;
        Log.d("FriendFragment", "handleFriendCreatedEvent");
        if (m12960h()) {
            Log.d("FriendFragment", "[handleFriendCreatedEvent] InDestroyView");
            return;
        }
        String strM22346k = C5616j.m22346k(str);
        if (strM22346k == null) {
            Log.d("FriendFragment", "[handleFriendCreatedEvent] parseName(from) return null");
            return;
        }
        final Friend friendM15001A = C2950b0.m14899A().m15001A(strM22346k);
        if (friendM15001A == null) {
            Log.d("FriendFragment", "[handleFriendCreatedEvent] friend is exist");
            return;
        }
        if (friendM15001A.m15623d().equals("Corporate")) {
            List list = (List) this.f11661q.m13210v(2);
            List<Friend> listM15023o = C2950b0.m14899A().m15023o();
            Friend friend = (Friend) list.get(0);
            if (m13327L0(friend)) {
                friendGroupM13346t0 = (FriendGroup) friend;
                friendGroupM13346t0.f13670B = listM15023o.size();
            } else {
                friendGroupM13346t0 = m13346t0(getString(R.string.official_accounts), listM15023o.size());
            }
        } else {
            friendGroupM13346t0 = null;
        }
        m12963k(new Runnable() { // from class: g3.t1
            @Override // java.lang.Runnable
            public final void run() {
                this.f17011b.m13261T0(friendM15001A, friendGroupM13346t0);
            }
        });
    }

    /* renamed from: x1 */
    public final void m13355x1(List<Object> list) {
        List<Object> arrayList = new ArrayList<>();
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            Friend friend = (Friend) it.next();
            if (friend != null && friend.f13659q >= 0) {
                if (friend.f13659q + 86400 >= System.currentTimeMillis() / 1000) {
                    arrayList.add(friend);
                }
            }
        }
        m13359z1(16, arrayList);
    }

    /* renamed from: y0 */
    public final void m13356y0(String str) {
        Log.d("FriendFragment", "handleFriendDeletedEvent");
        m13340q0(str);
    }

    /* renamed from: y1 */
    public void m13357y1() {
        q qVar = new q(this, null);
        this.f11669y = qVar;
        qVar.executeOnExecutor(f11624O, new Void[0]);
    }

    /* renamed from: z0 */
    public final void m13358z0(String str) {
        Log.d("FriendFragment", "handleFriendHiddenEvent");
        m13340q0(str);
    }

    /* renamed from: z1 */
    public final void m13359z1(final int i9, final List<Object> list) {
        if (list == null) {
            return;
        }
        m12963k(new Runnable() { // from class: g3.j1
            @Override // java.lang.Runnable
            public final void run() {
                this.f16937b.m13287g1(i9, list);
            }
        });
    }
}
