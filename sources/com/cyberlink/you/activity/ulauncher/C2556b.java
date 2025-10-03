package com.cyberlink.you.activity.ulauncher;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.GroupChatRoomCreateActivity;
import com.cyberlink.you.activity.GroupMemberListActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.AbstractC2555a;
import com.cyberlink.you.activity.ulauncher.C2556b;
import com.cyberlink.you.activity.ulauncher.C2562h;
import com.cyberlink.you.bulletin.TopicCommentObj;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2931y;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.database.TopicObj;
import com.cyberlink.you.friends.ChatListGroup;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendGroup;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.iid.ServiceStarter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.CharUtils;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONException;
import org.json.JSONObject;
import p042d0.C4619d;
import p075g3.C4857f;
import p116k4.C5143f0;
import p116k4.C5152i0;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5180s;
import p116k4.C5187v0;
import p136m3.C5321e;
import p182r2.C6196d0;
import p201t3.C6301o;
import p209u2.AbstractC6381r;
import p209u2.C6385v;
import p209u2.C6386w;
import p218v2.C6456d;

/* renamed from: com.cyberlink.you.activity.ulauncher.b */
/* loaded from: classes.dex */
public class C2556b extends AbstractC2555a {

    /* renamed from: A */
    public FriendsClient f11506A;

    /* renamed from: C */
    public AsyncTask f11508C;

    /* renamed from: D */
    public AsyncTask f11509D;

    /* renamed from: E */
    public s f11510E;

    /* renamed from: F */
    public r f11511F;

    /* renamed from: G */
    public t f11512G;

    /* renamed from: H */
    public C2907m0.h f11513H;

    /* renamed from: J */
    public View f11515J;

    /* renamed from: d */
    public p f11530d;

    /* renamed from: e */
    public View f11531e;

    /* renamed from: f */
    public TextView f11532f;

    /* renamed from: g */
    public View f11533g;

    /* renamed from: h */
    public View f11534h;

    /* renamed from: i */
    public EditText f11535i;

    /* renamed from: j */
    public ProgressBar f11536j;

    /* renamed from: k */
    public View f11537k;

    /* renamed from: l */
    public View f11538l;

    /* renamed from: m */
    public View f11539m;

    /* renamed from: n */
    public View f11540n;

    /* renamed from: o */
    public View f11541o;

    /* renamed from: p */
    public ListView f11542p;

    /* renamed from: q */
    public C2931y f11543q;

    /* renamed from: r */
    public ExpandableListView f11544r;

    /* renamed from: s */
    public C2562h f11545s;

    /* renamed from: t */
    public List<Group> f11546t;

    /* renamed from: z */
    public ProgressDialog f11552z;

    /* renamed from: u */
    public final C6386w f11547u = new C6386w();

    /* renamed from: v */
    public Handler f11548v = new Handler();

    /* renamed from: w */
    public int f11549w = 0;

    /* renamed from: x */
    public boolean f11550x = false;

    /* renamed from: y */
    public int f11551y = 1;

    /* renamed from: B */
    public final Object f11507B = new Object();

    /* renamed from: I */
    public C4619d f11514I = null;

    /* renamed from: K */
    public ExpandableListView.OnChildClickListener f11516K = new k();

    /* renamed from: L */
    public AdapterView.OnItemClickListener f11517L = new l();

    /* renamed from: M */
    public AdapterView.OnItemLongClickListener f11518M = new m();

    /* renamed from: N */
    public TextWatcher f11519N = new n();

    /* renamed from: O */
    public TextView.OnEditorActionListener f11520O = new TextView.OnEditorActionListener() { // from class: g3.r
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
            return this.f16994b.m13033p1(textView, i9, keyEvent);
        }
    };

    /* renamed from: P */
    public View.OnClickListener f11521P = new o();

    /* renamed from: Q */
    public XMPPManager.AbstractC2868s f11522Q = new a(false);

    /* renamed from: R */
    public C5321e.m f11523R = new C5321e.m() { // from class: g3.c0
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f16843b.m13036q1(c2904l, map);
        }
    };

    /* renamed from: S */
    public XMPPManager.InterfaceC2851b0 f11524S = new XMPPManager.InterfaceC2851b0() { // from class: g3.g0
        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2851b0
        /* renamed from: h0 */
        public final void mo13974h0(boolean z8) {
            this.f16886b.m13039r1(z8);
        }
    };

    /* renamed from: T */
    public XMPPManager.InterfaceC2849a0 f11525T = new b();

    /* renamed from: U */
    public View.OnClickListener f11526U = new c();

    /* renamed from: V */
    public View.OnClickListener f11527V = new View.OnClickListener() { // from class: g3.h0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f16892b.m13042s1(view);
        }
    };

    /* renamed from: W */
    public View.OnClickListener f11528W = new d();

    /* renamed from: X */
    public View.OnClickListener f11529X = new f();

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$a */
    public class a extends XMPPManager.AbstractC2868s {
        public a(boolean z8) {
            super(z8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m13117g(C2904l c2904l) {
            C2556b.this.f11543q.m14665v(c2904l);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: c */
        public boolean mo13118c() {
            C2556b.this.m13075J1();
            return true;
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: e */
        public String mo8240e(final C2904l c2904l) {
            if (c2904l.m14389D() == MessageObj.MessageType.Event || c2904l.m14389D() == MessageObj.MessageType.DeleteMedia) {
                Log.i("ChatGroupFragment", "Omit Packet, xmpp type : " + c2904l.m14389D());
                return null;
            }
            Group groupM14656m = C2556b.this.f11543q.m14656m(c2904l.m14418h(), C5616j.m22345j(c2904l.m14388C()));
            if (groupM14656m == null) {
                C2556b.this.m13079L1();
            }
            Date dateM14386A = c2904l.m14386A();
            if (c2904l.m14389D() != MessageObj.MessageType.DeliveryReceipt && !c2904l.m14398M() && dateM14386A != null) {
                C2556b.this.m12963k(new Runnable() { // from class: g3.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16966b.m13117g(c2904l);
                    }
                });
                if (!c2904l.m14399N()) {
                    C2556b.this.m13065D0(groupM14656m);
                }
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$b */
    public class b implements XMPPManager.InterfaceC2849a0 {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public /* synthetic */ void m13120c(Group group) {
            C2556b.this.f11543q.m14661r(group);
            C2556b.this.f11543q.notifyDataSetChanged();
            C2556b.this.m13065D0(group);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.InterfaceC2849a0
        /* renamed from: p */
        public void mo5718p(String str, Date date) {
            final Group groupM15077n;
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(str);
            if (messageObjM15179r == null || messageObjM15179r.m14778p() == MessageObj.MessageType.Event || (groupM15077n = C2950b0.m14912k().m15077n(messageObjM15179r.m14772j())) == null) {
                return;
            }
            C2556b.this.m12963k(new Runnable() { // from class: g3.o0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16975b.m13120c(groupM15077n);
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2556b.this.f11535i.setText("");
            C2556b.this.m13091R1(1);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2556b.this.f11541o.setVisibility(8);
            if (C2556b.this.getContext() != null) {
                if (new C5143f0(C2556b.this.getContext(), "U", 0).m20031d("skipDeleteDialog", false) || C2556b.this.m13080M0() <= 0) {
                    C2556b.this.m13097U1(false);
                } else {
                    C2556b.this.m13097U1(true);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$e */
    public class e extends q {

        /* renamed from: b */
        public final /* synthetic */ boolean f11557b;

        /* renamed from: c */
        public final /* synthetic */ List f11558c;

        /* renamed from: d */
        public final /* synthetic */ List f11559d;

        /* renamed from: e */
        public final /* synthetic */ ProgressDialog f11560e;

        /* renamed from: com.cyberlink.you.activity.ulauncher.b$e$a */
        public class a extends AbstractC6381r<Void, Void> {

            /* renamed from: c */
            public final /* synthetic */ Group f11562c;

            public a(Group group) {
                this.f11562c = group;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Void r42) {
                if (!C2556b.this.f11506A.m15699B0(this.f11562c.f13727n)) {
                    C5187v0.m20267c(R.string.error_server_response);
                    return;
                }
                C5321e c5321eM20824o = C5321e.m20824o();
                Group group = this.f11562c;
                c5321eM20824o.m20893v0(group, group.f13727n);
            }
        }

        /* renamed from: com.cyberlink.you.activity.ulauncher.b$e$b */
        public class b extends AbstractC6381r<Void, Void> {

            /* renamed from: c */
            public final /* synthetic */ Group f11564c;

            public b(Group group) {
                this.f11564c = group;
            }

            @Override // p209u2.AbstractC6381r
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public void m24503g(Void r22) {
                C2556b.this.m13074J0(this.f11564c);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(boolean z8, List list, List list2, ProgressDialog progressDialog) {
            super(C2556b.this, null);
            this.f11557b = z8;
            this.f11558c = list;
            this.f11559d = list2;
            this.f11560e = progressDialog;
        }

        /* renamed from: d */
        public static /* synthetic */ void m13122d(ProgressDialog progressDialog) {
            if (progressDialog != null) {
                C5152i0.m20065b(progressDialog);
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            if (this.f11557b) {
                for (Group group : this.f11558c) {
                    C6196d0.m23692d().m23701k(group, new a(group));
                }
            } else if (this.f11558c.size() > 0) {
                C5187v0.m20267c(R.string.error_server_response);
            }
            for (Group group2 : this.f11559d) {
                C6196d0.m23692d().m23701k(group2, new b(group2));
            }
            C2556b c2556b = C2556b.this;
            final ProgressDialog progressDialog = this.f11560e;
            c2556b.m12963k(new Runnable() { // from class: g3.p0
                @Override // java.lang.Runnable
                public final void run() {
                    C2556b.e.m13122d(progressDialog);
                }
            });
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$f */
    public class f implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.ulauncher.b$f$a */
        public class a extends q {
            public a() {
                super(C2556b.this, null);
            }

            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void doInBackground(Void... voidArr) {
                for (Group group : C2556b.this.f11543q.m25232a()) {
                    group.f13706E = true;
                    C2950b0.m14912k().m15062A(String.valueOf(group.f13727n), group, "isArchive");
                    C2907m0.m14454I().m14493U(group.f13727n, group.f13723j);
                    C2556b.this.m13066E0(group.f13723j, 1);
                }
                return null;
            }
        }

        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2556b.this.f11541o.setVisibility(8);
            new a().execute(new Void[0]);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$g */
    public class g extends GestureDetector.SimpleOnGestureListener {
        public g() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f9, float f10) {
            if (f10 < BitmapDescriptorFactory.HUE_RED || f10 > BitmapDescriptorFactory.HUE_RED) {
                CLUtility.m16589t1(C2556b.this.getActivity());
            }
            return super.onScroll(motionEvent, motionEvent2, f9, f10);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            CLUtility.m16589t1(C2556b.this.getActivity());
            return false;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$h */
    public class h extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ boolean f11569a;

        /* renamed from: b */
        public final /* synthetic */ Intent f11570b;

        public h(boolean z8, Intent intent) {
            this.f11569a = z8;
            this.f11570b = intent;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Thread.currentThread().setName("updateGroupLastMessage AsyncTask");
            C2556b.this.m13110b2();
            C2556b.this.m13079L1();
            C2556b.this.m13075J1();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r22) {
            if (C2556b.this.f11543q == null || !this.f11569a) {
                return;
            }
            Globals.m7388i0().m7634w1();
            this.f11570b.removeExtra("checkLastMsg");
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$i */
    public class i extends AsyncTask<Void, Void, List<Group>> {

        /* renamed from: a */
        public final /* synthetic */ boolean f11572a;

        public i(boolean z8) {
            this.f11572a = z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Group> doInBackground(Void... voidArr) {
            List<Group> listM15084u = C2950b0.m14912k().m15084u();
            ArrayList arrayList = new ArrayList();
            Iterator<Group> it = listM15084u.iterator();
            while (it.hasNext()) {
                arrayList.add(new ChatListGroup(it.next()));
            }
            Collections.sort(arrayList, new ChatListGroup.C3037a());
            return new ArrayList(arrayList);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Group> list) {
            C2556b.this.m13095T1(2, list);
            if (this.f11572a) {
                int[] iArr = {2, 1, 3};
                for (int i9 = 0; i9 < 3; i9++) {
                    int i10 = iArr[i9];
                    if ((C2556b.this.f11549w & i10) != 0) {
                        C2556b.this.f11545s.m13446m(i10, C2556b.this.f11535i.getText().toString());
                    }
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$j */
    public class j extends AsyncTask<Void, Void, List<Group>> {

        /* renamed from: a */
        public final /* synthetic */ boolean f11574a;

        public j(boolean z8) {
            this.f11574a = z8;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Group> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("resetGroupList AsyncTask");
            List<Group> listM16584s0 = CLUtility.m16584s0();
            if (listM16584s0 == null) {
                return null;
            }
            Collections.sort(listM16584s0, new Group.C3055b());
            return new ArrayList(listM16584s0);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Group> list) {
            C2556b.this.f11546t = list;
            C2556b.this.m13095T1(3, list);
            if (this.f11574a) {
                C2556b.this.m13107Z1();
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$k */
    public class k implements ExpandableListView.OnChildClickListener {
        public k() {
        }

        /* renamed from: a */
        public final void m13133a() {
            C2556b.this.f11547u.m24532d("ChatGroupFragment : ExpandableListView onChildClick");
        }

        @Override // android.widget.ExpandableListView.OnChildClickListener
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i9, int i10, long j9) {
            if (C2556b.this.f11536j != null && C2556b.this.f11536j.getVisibility() == 0) {
                return true;
            }
            C2556b.this.f11547u.m24533e();
            CLUtility.m16589t1(C2556b.this.getActivity());
            int iM13458y = C2556b.this.f11545s.m13458y(Integer.valueOf(i9));
            if (iM13458y == 1) {
                Friend friend = (Friend) view.getTag();
                if (C2556b.this.m13106Z0(friend)) {
                    C2556b.this.m13069G1();
                    m13133a();
                    return true;
                }
                Group groupM15081r = C2950b0.m14912k().m15081r(friend.f13648f);
                if (groupM15081r != null) {
                    C2556b.this.m13004d1(groupM15081r);
                } else {
                    C2556b.this.m13072I0(friend);
                }
            } else if (iM13458y == 2) {
                C2556b.this.m13004d1(C2950b0.m14912k().m15077n(String.valueOf(((ChatListGroup) view.getTag()).f13727n)));
            } else if (iM13458y == 3) {
                C2556b.this.m13004d1((Group) view.getTag());
            }
            m13133a();
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$l */
    public class l implements AdapterView.OnItemClickListener {
        public l() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (C2556b.this.f11550x) {
                return;
            }
            if (C2556b.this.f11543q.m25234c()) {
                C2556b.this.m13089Q1(view, i9, !r3.f11543q.m25235d(i9));
                return;
            }
            Group item = C2556b.this.f11543q.getItem(i9);
            int iM14483G = C2907m0.m14454I().m14483G(item.f13723j);
            ChatDialogActivity.Tab tab = (iM14483G != 0 || (iM14483G == 0 ? C2907m0.m14454I().m14481E(item.f13727n, false) : 0) <= 0 || item.f13711J || item.f13712K) ? ChatDialogActivity.Tab.Chats : ChatDialogActivity.Tab.Bulletins;
            C2556b c2556b = C2556b.this;
            c2556b.m13073I1(c2556b.f11543q.getItem(i9), tab);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$m */
    public class m implements AdapterView.OnItemLongClickListener {
        public m() {
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            if (((C4857f) C2556b.this.getParentFragment()).m19196r()) {
                return true;
            }
            if (C2556b.this.f11543q.m25234c()) {
                return false;
            }
            C2556b.this.m13093S1(true);
            C2556b.this.m13089Q1(view, i9, true);
            return true;
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$n */
    public class n implements TextWatcher {

        /* renamed from: b */
        public int f11579b;

        /* renamed from: c */
        public final int f11580c = ServiceStarter.ERROR_UNKNOWN;

        /* renamed from: d */
        public final Runnable f11581d = new a();

        /* renamed from: com.cyberlink.you.activity.ulauncher.b$n$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (n.this.f11579b == 0) {
                    C2556b.this.m13081M1(true);
                    return;
                }
                int[] iArr = {2, 1, 3};
                for (int i9 = 0; i9 < 3; i9++) {
                    int i10 = iArr[i9];
                    if ((C2556b.this.f11549w & i10) != 0) {
                        C2556b.this.f11545s.m13446m(i10, C2556b.this.f11535i.getText().toString());
                    }
                }
            }
        }

        public n() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            C2556b.this.m13093S1(false);
            this.f11579b = i10;
            boolean zM22342g = C5616j.m22342g(charSequence);
            boolean z8 = i10 > 0 && zM22342g;
            C2556b.this.f11545s.m13455v(!zM22342g);
            C2556b.this.f11537k.setVisibility(8);
            if (z8 || C2556b.this.f11550x) {
                C2556b.this.f11534h.setVisibility(8);
                C2556b.this.f11536j.setVisibility(8);
                ((C4857f) C2556b.this.getParentFragment()).m19199x(false);
                if (!C2556b.this.f11550x) {
                    C2556b.this.m13091R1(1);
                }
                C2556b.this.m13077K1(false);
                if (C2556b.this.f11515J != null) {
                    C2556b.this.f11515J.setVisibility(0);
                    return;
                }
                return;
            }
            if (zM22342g) {
                return;
            }
            C2556b.this.m13091R1(2);
            if (C2556b.this.f11544r != null) {
                C2556b.this.f11544r.setVisibility(0);
                C2556b.this.f11544r.setAlpha(BitmapDescriptorFactory.HUE_RED);
            }
            C2556b.this.f11534h.setVisibility(0);
            C2556b.this.f11536j.setVisibility(0);
            ((C4857f) C2556b.this.getParentFragment()).m19199x(true);
            C2556b.this.f11548v.removeCallbacks(this.f11581d);
            C2556b.this.f11548v.postDelayed(this.f11581d, 500L);
            if (C2556b.this.f11515J != null) {
                C2556b.this.f11515J.setVisibility(8);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$o */
    public class o implements View.OnClickListener {
        public o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            C2556b.this.m13091R1(1);
            C2556b.this.f11545s.m13455v(false);
            C2556b.this.startActivityForResult(new Intent(C2556b.this.getActivity(), (Class<?>) GroupChatRoomCreateActivity.class), 1);
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$p */
    public interface p extends AbstractC2555a.a {
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$q */
    public abstract class q extends AsyncTask<Void, Void, Void> {
        public q() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r22) {
            C2556b.this.m13093S1(false);
            C2556b.this.m13079L1();
            C2556b.this.m13077K1(false);
            C2556b.this.f11530d.mo12935n0();
        }

        public /* synthetic */ q(C2556b c2556b, g gVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$r */
    public class r extends AsyncTask<Void, Void, List<Friend>> {
        public r() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Friend> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("resetFriendList AsyncTask");
            List<Friend> listM16580r0 = CLUtility.m16580r0(C2950b0.m14899A().m15025q());
            Collections.sort(listM16580r0, new FriendGroup.C3042a());
            return new ArrayList(listM16580r0);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Friend> list) {
            if (C2556b.this.isAdded()) {
                ArrayList arrayList = new ArrayList();
                FriendGroup friendGroupM16475P = null;
                for (Friend friend : list) {
                    if (!friend.m15623d().equals("Corporate")) {
                        arrayList.add(friend);
                    } else if (friendGroupM16475P == null) {
                        friendGroupM16475P = CLUtility.m16475P(Globals.m7388i0().getString(R.string.official_accounts), 1);
                    } else {
                        friendGroupM16475P.f13670B++;
                    }
                }
                if (friendGroupM16475P != null) {
                    arrayList.add(0, friendGroupM16475P);
                }
                C2556b.this.m13095T1(1, arrayList);
            }
        }

        public /* synthetic */ r(C2556b c2556b, g gVar) {
            this();
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$s */
    public class s extends AsyncTask<Void, Void, List<Object>> {

        /* renamed from: a */
        public List<Group> f11587a;

        public s(List<Group> list) {
            ArrayList arrayList = new ArrayList();
            this.f11587a = arrayList;
            arrayList.addAll(list);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public List<Object> doInBackground(Void... voidArr) {
            Thread.currentThread().setName("syncGroupMembersListTask AsyncTask");
            if (this.f11587a.size() == 0) {
                return null;
            }
            Iterator<Group> it = this.f11587a.iterator();
            while (it.hasNext() && !isCancelled()) {
                it.next().m15754l();
                it.remove();
            }
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List<Object> list) {
        }
    }

    /* renamed from: com.cyberlink.you.activity.ulauncher.b$t */
    public class t extends AsyncTask<Void, Void, Void> {
        public t() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Thread.currentThread().setName("updateChatListLastMessage AsyncTask");
            C2556b.this.m13110b2();
            C2556b.this.m13079L1();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r22) {
            if (C2556b.this.isAdded()) {
                C2556b.this.m13077K1(false);
            }
        }

        public /* synthetic */ t(C2556b c2556b, g gVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: A1 */
    public /* synthetic */ void m12966A1(CheckBox checkBox, C5143f0 c5143f0, DialogInterface dialogInterface, int i9) {
        c5143f0.m20036i("skipDeleteDialog", checkBox.isChecked());
        dialogInterface.dismiss();
        m13076K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: B1 */
    public /* synthetic */ void m12969B1(DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        m13093S1(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: C1 */
    public /* synthetic */ void m12971C1(DialogInterface dialogInterface, int i9) {
        dialogInterface.dismiss();
        m13076K0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D1 */
    public /* synthetic */ void m12973D1(boolean z8) {
        if (z8) {
            m13067F0();
            this.f11513H = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E1 */
    public /* synthetic */ void m12975E1() {
        m13093S1(false);
        m13105Y1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F1 */
    public /* synthetic */ void m12977F1(Group group) {
        C2931y c2931y = this.f11543q;
        if (c2931y != null) {
            c2931y.m14661r(group);
            this.f11543q.notifyDataSetChanged();
        }
        m13112d2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b1 */
    public /* synthetic */ void m13000b1(Group group) {
        synchronized (this.f11507B) {
            int iM14657n = group != null ? this.f11543q.getPosition(group) : -1;
            if (iM14657n < this.f11543q.getCount() && iM14657n >= 0) {
                while (iM14657n > 0) {
                    int i9 = iM14657n - 1;
                    if (this.f11543q.getItem(iM14657n).m15749e() < this.f11543q.getItem(i9).m15749e()) {
                        break;
                    }
                    this.f11543q.m14660q(iM14657n, i9);
                    iM14657n--;
                }
                this.f11543q.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c1 */
    public /* synthetic */ void m13002c1() {
        if (!C2907m0.m14454I().m14489N()) {
            ULogUtility.m16683s("ChatGroupFragment", "[checkAndFixChatListByUnreadCount] UnreadCountManager is not ready, skip to check ");
            return;
        }
        List<String> listM14484H = C2907m0.m14454I().m14484H();
        if (listM14484H.size() > 0) {
            List<Group> listM15084u = C2950b0.m14912k().m15084u();
            HashMap map = new HashMap();
            Iterator<Group> it = listM15084u.iterator();
            while (it.hasNext()) {
                map.put(it.next().f13723j, Boolean.TRUE);
            }
            boolean z8 = false;
            for (String str : listM14484H) {
                if (!map.containsKey(str)) {
                    ULogUtility.m16680p("ChatGroupFragment", "[checkAndFixChatListByUnreadCount] found groups which has unreadCount but not contain in groupChatList Jid =  " + str + ", update lastMessage.");
                    Group groupM15081r = C2950b0.m14912k().m15081r(str);
                    if (groupM15081r != null) {
                        C5180s.m20255h(String.valueOf(groupM15081r.f13727n));
                        z8 = true;
                    } else {
                        ULogUtility.m16676l("ChatGroupFragment", "[checkAndFixChatListByUnreadCount] error, can not found groupId in DB = " + str);
                    }
                }
            }
            if (z8) {
                m13079L1();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e1 */
    public /* synthetic */ void m13006e1(String str, String str2, String str3, String str4) {
        m13078L0();
        if ("200".equals(str3)) {
            final Group groupM20186h = null;
            try {
                groupM20186h = C5172p.m20186h(new JSONObject(str4).getJSONObject("result"));
                C2950b0.m14912k().m15070g(groupM20186h, true);
            } catch (JSONException unused) {
                Log.e("ChatGroupFragment", "[chatWithFriend] Parse error. JSONStr= " + str4);
            }
            if (groupM20186h != null) {
                m12963k(new Runnable() { // from class: g3.e0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f16855b.m13004d1(groupM20186h);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f1 */
    public /* synthetic */ void m13008f1(boolean z8, Group group, Group group2, long j9) {
        if (z8) {
            if (group == null || this.f11543q == null) {
                return;
            }
            synchronized (this.f11507B) {
                this.f11543q.remove(group);
                this.f11543q.notifyDataSetChanged();
            }
            if (group2 != null && !C5616j.m22344i(group2.f13723j)) {
                C2907m0.m14454I().m14498Z(group2.f13723j, 0);
            }
            this.f11530d.mo12935n0();
            m13112d2();
            return;
        }
        if (group2 == null) {
            return;
        }
        group2.f13736w = j9;
        group2.f13739z = C5180s.m20255h(String.valueOf(group2.f13727n));
        if (m13108a1(group2) || C5616j.m22344i(group2.f13739z)) {
            return;
        }
        Log.d("ChatGroupFragment", "[handleGroupUpdate] add group.");
        if (this.f11543q != null) {
            synchronized (this.f11507B) {
                this.f11543q.m14661r(group2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g1 */
    public /* synthetic */ void m13010g1(Group group) {
        this.f11543q.m14662s(group);
        this.f11543q.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h1 */
    public /* synthetic */ void m13012h1(Group group) {
        if (isAdded()) {
            this.f11543q.m14662s(group);
            this.f11543q.notifyDataSetChanged();
            this.f11530d.mo12935n0();
            m13079L1();
            m13077K1(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i1 */
    public /* synthetic */ void m13014i1(Group group) {
        this.f11543q.m14661r(group);
        this.f11543q.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j1 */
    public /* synthetic */ void m13016j1(String str, TopicCommentObj topicCommentObj) {
        this.f11543q.m14663t(str, topicCommentObj);
        TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(topicCommentObj.m14038n());
        if (topicObjM14984n != null) {
            m13064C0(topicObjM14984n.m14843h(), topicCommentObj.m14030e());
        }
        this.f11543q.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k1 */
    public /* synthetic */ void m13018k1(Group group) {
        this.f11543q.m14662s(group);
        this.f11543q.notifyDataSetChanged();
        this.f11530d.mo12935n0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l1 */
    public /* synthetic */ void m13021l1(String str, TopicObj topicObj) {
        this.f11543q.m14664u(str, topicObj);
        m13064C0(topicObj.m14843h(), topicObj.m14840e());
        this.f11543q.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m1 */
    public /* synthetic */ void m13024m1(Group group) {
        this.f11543q.m14662s(group);
        this.f11543q.notifyDataSetChanged();
        this.f11530d.mo12935n0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n1 */
    public /* synthetic */ void m13027n1(Group group) {
        this.f11543q.m14661r(group);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o1 */
    public /* synthetic */ boolean m13030o1(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f11514I;
        return c4619d != null && c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p1 */
    public /* synthetic */ boolean m13033p1(TextView textView, int i9, KeyEvent keyEvent) {
        if (i9 != 3) {
            return false;
        }
        CLUtility.m16589t1(getActivity());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: q1 */
    public /* synthetic */ boolean m13036q1(C2904l c2904l, Map map) {
        String str = (String) map.get("eventType");
        Log.i("ChatGroupFragment", "XMPP Event receive : " + str);
        str.hashCode();
        char c9 = 65535;
        switch (str.hashCode()) {
            case -2138412222:
                if (str.equals("user.status.updated")) {
                    c9 = 0;
                    break;
                }
                break;
            case -1936932574:
                if (str.equals("bulletin.topic.created")) {
                    c9 = 1;
                    break;
                }
                break;
            case -1868147997:
                if (str.equals("group.member.created")) {
                    c9 = 2;
                    break;
                }
                break;
            case -1645150902:
                if (str.equals("group.group.created")) {
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
            case -1346239468:
                if (str.equals("group.member.deleted")) {
                    c9 = 5;
                    break;
                }
                break;
            case -1065518524:
                if (str.equals("group.group.hided")) {
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
            case -1009547174:
                if (str.equals("group.group.disabled")) {
                    c9 = '\b';
                    break;
                }
                break;
            case -826276900:
                if (str.equals("user.coverart.updated")) {
                    c9 = '\t';
                    break;
                }
                break;
            case -772340311:
                if (str.equals("user.avatar.updated")) {
                    c9 = '\n';
                    break;
                }
                break;
            case -471004516:
                if (str.equals("chat.message.deleted")) {
                    c9 = 11;
                    break;
                }
                break;
            case -292839295:
                if (str.equals("bulletin.post.created")) {
                    c9 = '\f';
                    break;
                }
                break;
            case 461331:
                if (str.equals("group.display.name.updated")) {
                    c9 = CharUtils.f19105CR;
                    break;
                }
                break;
            case 11667875:
                if (str.equals("group.group.enabled")) {
                    c9 = 14;
                    break;
                }
                break;
            case 189290855:
                if (str.equals("group.member.created.v2")) {
                    c9 = 15;
                    break;
                }
                break;
            case 229069234:
                if (str.equals("bulletin.post.deleted")) {
                    c9 = 16;
                    break;
                }
                break;
            case 673160263:
                if (str.equals("user.display.name.updated")) {
                    c9 = 17;
                    break;
                }
                break;
            case 879535989:
                if (str.equals("meeting.meeting.end")) {
                    c9 = 18;
                    break;
                }
                break;
            case 946682189:
                if (str.equals("chat.message.recalled")) {
                    c9 = 19;
                    break;
                }
                break;
            case 1386831645:
                if (str.equals("group.group.updated")) {
                    c9 = 20;
                    break;
                }
                break;
            case 1418851467:
                if (str.equals("friend.nickname.updated")) {
                    c9 = 21;
                    break;
                }
                break;
            case 1643006394:
                if (str.equals("group.group.showed")) {
                    c9 = 22;
                    break;
                }
                break;
            case 2026580972:
                if (str.equals("group.notification.changed.event")) {
                    c9 = 23;
                    break;
                }
                break;
        }
        switch (c9) {
            case 0:
            case '\t':
            case '\n':
            case 17:
                m13098V0(C5616j.m22346k(c2904l.m14428m()));
                return true;
            case 1:
                m13094T0((String) map.get("groupId"), (String) map.get("topicId"));
                return true;
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\r':
            case 14:
            case 15:
            case 20:
            case 22:
                m13082N0(str, (String) map.get("groupId"), (String) map.get("userId"));
                return true;
            case 4:
                m13096U0((String) map.get("groupId"));
                return true;
            case 11:
                m13105Y1();
                return true;
            case '\f':
                m13090R0((String) map.get("groupId"), (String) map.get("postId"));
                return true;
            case 16:
                m13092S0((String) map.get("groupId"));
                return true;
            case 18:
                m13084O0(c2904l);
                return true;
            case 19:
                m13086P0(c2904l);
                return true;
            case 21:
                m13098V0((String) map.get("userId"));
                return true;
            case 23:
                m13088Q0((String) map.get("groupId"), (String) map.get("isDisabled"));
                return true;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r1 */
    public /* synthetic */ void m13039r1(boolean z8) {
        if (isAdded()) {
            m12963k(new Runnable() { // from class: g3.b0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16835b.m13111c2();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s1 */
    public /* synthetic */ void m13042s1(View view) {
        m13093S1(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t1 */
    public /* synthetic */ boolean m13045t1(View view, MotionEvent motionEvent) {
        C4619d c4619d = this.f11514I;
        return c4619d != null && c4619d.m18406a(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u1 */
    public /* synthetic */ void m13048u1() {
        this.f11536j.setVisibility(8);
        if (!isVisible()) {
            this.f11544r.setVisibility(8);
            return;
        }
        this.f11544r.setVisibility(this.f11535i.getText().length() > 0 ? 0 : 8);
        m13099V1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v1 */
    public /* synthetic */ void m13051v1(View view) {
        if (getParentFragment() != null) {
            ((C4857f) getParentFragment()).m19197v();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w1 */
    public /* synthetic */ void m13054w1() {
        synchronized (this.f11507B) {
            this.f11543q.sort(new Group.C3056c());
            this.f11543q.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x1 */
    public /* synthetic */ void m13057x1(List list) {
        this.f11543q.clear();
        if (list != null) {
            this.f11543q.addAll(list);
        }
        this.f11543q.notifyDataSetChanged();
        m13112d2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y1 */
    public /* synthetic */ void m13060y1(int i9) {
        this.f11543q.notifyDataSetChanged();
        this.f11542p.setSelection(i9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: z1 */
    public /* synthetic */ void m13063z1(CheckBox checkBox, C5143f0 c5143f0, DialogInterface dialogInterface, int i9) {
        c5143f0.m20036i("skipDeleteDialog", checkBox.isChecked());
        dialogInterface.dismiss();
        m13093S1(false);
    }

    /* renamed from: C0 */
    public final void m13064C0(long j9, long j10) {
        Group groupM15077n;
        int count = 0;
        while (true) {
            if (count >= this.f11543q.getCount()) {
                groupM15077n = null;
                count = -1;
                break;
            } else {
                groupM15077n = this.f11543q.getItem(count);
                if (groupM15077n != null && groupM15077n.f13727n == j9) {
                    break;
                } else {
                    count++;
                }
            }
        }
        if (groupM15077n == null) {
            groupM15077n = C2950b0.m14912k().m15077n(String.valueOf(j9));
            if (groupM15077n == null) {
                return;
            }
            count = this.f11543q.getCount();
            m13114f2(groupM15077n);
        }
        if (count == -1 || j10 == 0) {
            return;
        }
        groupM15077n.f13736w = j10;
        m13065D0(groupM15077n);
    }

    /* renamed from: D0 */
    public final void m13065D0(final Group group) {
        m12963k(new Runnable() { // from class: g3.d0
            @Override // java.lang.Runnable
            public final void run() {
                this.f16848b.m13000b1(group);
            }
        });
    }

    /* renamed from: E0 */
    public final void m13066E0(String str, int i9) {
        if (m12960h()) {
            return;
        }
        ((NotificationManager) getActivity().getSystemService("notification")).cancel(str, i9);
    }

    /* renamed from: F0 */
    public final void m13067F0() {
        C6385v.f21553a.execute(new Runnable() { // from class: g3.f0
            @Override // java.lang.Runnable
            public final void run() {
                this.f16879b.m13002c1();
            }
        });
    }

    /* renamed from: G0 */
    public void m13068G0() {
        EditText editText = this.f11535i;
        if (editText == null || C5170o0.m20170e(editText.getText().toString())) {
            return;
        }
        EditText editText2 = this.f11535i;
        editText2.setText(editText2.getText().toString());
        EditText editText3 = this.f11535i;
        editText3.setSelection(editText3.getText().length());
    }

    /* renamed from: G1 */
    public final void m13069G1() {
        this.f11547u.m24533e();
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = (ArrayList) C2950b0.m14899A().m15023o();
        bundle.putInt("type", 1);
        bundle.putParcelableArrayList("originalMembers", arrayList);
        Intent intent = new Intent();
        intent.setClass(getActivity(), GroupMemberListActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        this.f11547u.m24532d("ChatGroupFragment : onOfficialAccountItemClick");
    }

    /* renamed from: H0 */
    public void m13070H0(boolean z8) {
        EditText editText;
        View view = this.f11515J;
        if (view != null) {
            view.setVisibility(z8 ? 0 : 8);
        }
        if (!z8 || (editText = this.f11535i) == null) {
            return;
        }
        editText.requestFocus();
        CLUtility.m16606x2(getActivity());
    }

    /* renamed from: H1, reason: merged with bridge method [inline-methods] */
    public final void m13004d1(Group group) {
        m13073I1(group, ChatDialogActivity.Tab.Chats);
    }

    /* renamed from: I0 */
    public final void m13072I0(Friend friend) {
        if (m12960h()) {
            return;
        }
        this.f11552z = ProgressDialog.show(getActivity(), "", getString(R.string.processing), true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("displayName", friend.m15621b()));
        arrayList.add(new C6301o("userId", Long.toString(friend.f13645c)));
        arrayList.add(new C6301o("groupType", "Dual"));
        this.f11506A.m15734m("group", "create", arrayList, new FriendsClient.InterfaceC3051i() { // from class: g3.z
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f17050a.m13006e1(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: I1 */
    public final void m13073I1(Group group, ChatDialogActivity.Tab tab) {
        if (m12960h()) {
            return;
        }
        this.f11550x = true;
        Intent intent = new Intent(getActivity(), (Class<?>) ChatDialogActivity.class);
        intent.putExtra("Group", group);
        intent.putExtra("StartTimeOnClick", System.currentTimeMillis());
        intent.putExtra("chatDialogTabType", tab.ordinal());
        startActivityForResult(intent, 0);
    }

    /* renamed from: J0 */
    public final void m13074J0(Group group) {
        MessageObj messageObjM15175n = C2950b0.m14916o().m15175n(Long.toString(group.f13727n));
        C2950b0.m14916o().m15170i(group.f13727n);
        ArrayList arrayList = new ArrayList();
        if (messageObjM15175n != null) {
            C2907m0.m14454I().m14514x(group.f13723j);
            group.f13735v = messageObjM15175n.m14788z().getTime();
            arrayList.add("LastDeleteChatTime");
        }
        group.f13739z = "";
        group.f13714M = false;
        C2907m0.m14454I().m14512v(group.f13727n);
        arrayList.add("LastMsg");
        arrayList.add("setAsReminder");
        C2950b0.m14912k().m15063B(String.valueOf(group.f13727n), group, arrayList);
        C2950b0.m14906e().m14977g(group.f13727n);
        this.f11506A.m15716T0(group.f13727n, false);
        m13066E0(group.f13723j, 1);
    }

    /* renamed from: J1 */
    public final void m13075J1() {
        m12963k(new Runnable() { // from class: g3.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f16897b.m13054w1();
            }
        });
    }

    /* renamed from: K0 */
    public final void m13076K0() {
        List<Group> listM25232a = this.f11543q.m25232a();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Group group : listM25232a) {
            if (group.f13716c.equals("ChatRoom")) {
                arrayList.add(group);
            } else {
                arrayList2.add(group);
            }
        }
        boolean zM24748G = C6456d.m24714D().m24748G();
        new e(zM24748G, arrayList, arrayList2, (arrayList.size() <= 0 || !zM24748G) ? null : ProgressDialog.show(getActivity(), "", getString(R.string.loading), true)).execute(new Void[0]);
    }

    /* renamed from: K1 */
    public final void m13077K1(boolean z8) {
        m13081M1(false);
        m13083N1();
        m13085O1(z8);
    }

    /* renamed from: L0 */
    public final void m13078L0() {
        ProgressDialog progressDialog = this.f11552z;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.f11552z = null;
        }
    }

    /* renamed from: L1 */
    public final void m13079L1() {
        final List<Group> listM15084u = C2950b0.m14912k().m15084u();
        if (listM15084u != null) {
            Collections.sort(listM15084u, new Group.C3056c());
        }
        m12963k(new Runnable() { // from class: g3.m0
            @Override // java.lang.Runnable
            public final void run() {
                this.f16958b.m13057x1(listM15084u);
            }
        });
    }

    /* renamed from: M0 */
    public final int m13080M0() {
        Iterator<Group> it = this.f11543q.m25232a().iterator();
        int iM14483G = 0;
        while (it.hasNext()) {
            iM14483G += C2907m0.m14454I().m14483G(it.next().f13723j);
        }
        return iM14483G;
    }

    /* renamed from: M1 */
    public final void m13081M1(boolean z8) {
        this.f11509D = new i(z8).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: N0 */
    public final void m13082N0(String str, String str2, String str3) {
        final Group groupM14655l;
        Log.d("ChatGroupFragment", "[handleGroupUpdate] groupId = " + str2);
        if (str2 == null) {
            return;
        }
        final boolean z8 = ((str.equals("group.member.leaved") || str.equals("group.member.deleted")) && str3 != null && str3.equals(String.valueOf(Globals.m7388i0().m7568k1()))) || str.equals("group.group.hided");
        synchronized (this.f11507B) {
            groupM14655l = this.f11543q.m14655l(str2);
        }
        if (m12960h()) {
            return;
        }
        final Group groupM15077n = C2950b0.m14912k().m15077n(str2);
        final long jM15082s = C2950b0.m14912k().m15082s(str2);
        m12963k(new Runnable() { // from class: g3.a0
            @Override // java.lang.Runnable
            public final void run() {
                this.f16824b.m13008f1(z8, groupM14655l, groupM15077n, jM15082s);
            }
        });
    }

    /* renamed from: N1 */
    public final void m13083N1() {
        r rVar = new r(this, null);
        this.f11511F = rVar;
        rVar.executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: O0 */
    public final void m13084O0(C2904l c2904l) {
        final Group groupM15081r = C2950b0.m14912k().m15081r(c2904l.m14418h());
        if (groupM15081r == null) {
            groupM15081r = C2950b0.m14912k().m15081r(c2904l.m14388C());
        }
        m12963k(new Runnable() { // from class: g3.q
            @Override // java.lang.Runnable
            public final void run() {
                this.f16988b.m13010g1(groupM15081r);
            }
        });
    }

    /* renamed from: O1 */
    public void m13085O1(boolean z8) {
        this.f11508C = new j(z8).executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: P0 */
    public final void m13086P0(C2904l c2904l) {
        if (c2904l == null) {
            return;
        }
        final Group groupM15081r = C2950b0.m14912k().m15081r(c2904l.m14418h());
        if (groupM15081r == null) {
            groupM15081r = C2950b0.m14912k().m15081r(c2904l.m14388C());
        }
        if (groupM15081r != null) {
            m12963k(new Runnable() { // from class: g3.o
                @Override // java.lang.Runnable
                public final void run() {
                    this.f16973b.m13012h1(groupM15081r);
                }
            });
        }
    }

    /* renamed from: P1 */
    public void m13087P1() {
        final int iM14658o = this.f11543q.m14658o();
        if (iM14658o >= 0) {
            m12963k(new Runnable() { // from class: g3.w
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17031b.m13060y1(iM14658o);
                }
            });
        }
    }

    /* renamed from: Q0 */
    public final void m13088Q0(String str, String str2) {
        final Group groupM15077n = C2950b0.m14912k().m15077n(str);
        if (groupM15077n == null || this.f11543q.getPosition(groupM15077n) <= -1) {
            return;
        }
        groupM15077n.f13733t = Boolean.valueOf(str2).booleanValue();
        m12963k(new Runnable() { // from class: g3.x
            @Override // java.lang.Runnable
            public final void run() {
                this.f17037b.m13014i1(groupM15077n);
            }
        });
    }

    /* renamed from: Q1 */
    public final void m13089Q1(View view, int i9, boolean z8) {
        ((C2931y.c) view.getTag()).f12865h.setChecked(z8);
        this.f11543q.m25237f(i9, z8);
        this.f11543q.notifyDataSetChanged();
        boolean zM25233b = this.f11543q.m25233b();
        this.f11539m.setEnabled(zM25233b);
        this.f11540n.setEnabled(zM25233b);
    }

    /* renamed from: R0 */
    public final void m13090R0(final String str, String str2) {
        final TopicCommentObj topicCommentObjM14952o = C2950b0.m14905d().m14952o(Long.valueOf(str2).longValue());
        if (topicCommentObjM14952o != null) {
            m12963k(new Runnable() { // from class: g3.v
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17024b.m13016j1(str, topicCommentObjM14952o);
                }
            });
        }
    }

    /* renamed from: R1 */
    public final void m13091R1(int i9) {
        this.f11551y = i9;
        m13112d2();
        m13115g2(this.f11551y);
    }

    /* renamed from: S0 */
    public final void m13092S0(String str) {
        final Group groupM15077n = C2950b0.m14912k().m15077n(str);
        if (groupM15077n != null) {
            m12963k(new Runnable() { // from class: g3.s
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17001b.m13018k1(groupM15077n);
                }
            });
        }
    }

    /* renamed from: S1 */
    public final void m13093S1(boolean z8) {
        this.f11543q.m25236e(z8);
        this.f11541o.setVisibility(z8 ? 0 : 8);
        if (z8) {
            this.f11540n.setVisibility(Globals.m7388i0().m7582n() ? 0 : 8);
        }
        this.f11543q.notifyDataSetChanged();
    }

    /* renamed from: T0 */
    public final void m13094T0(final String str, String str2) {
        final TopicObj topicObjM14984n = C2950b0.m14906e().m14984n(Long.valueOf(str2).longValue());
        if (topicObjM14984n != null) {
            m12963k(new Runnable() { // from class: g3.y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17043b.m13021l1(str, topicObjM14984n);
                }
            });
        }
    }

    /* renamed from: T1 */
    public final void m13095T1(int i9, List<Object> list) {
        this.f11545s.m13445l(i9);
        if (list != null) {
            this.f11545s.m13443j(i9, list);
            if (m13100W0()) {
                this.f11545s.m13446m(i9, this.f11535i.getText().toString());
            }
        }
        this.f11545s.notifyDataSetChanged();
        this.f11549w = i9 | this.f11549w;
    }

    /* renamed from: U0 */
    public final void m13096U0(String str) {
        final Group groupM15077n = C2950b0.m14912k().m15077n(str);
        if (groupM15077n != null) {
            m12963k(new Runnable() { // from class: g3.u
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17017b.m13024m1(groupM15077n);
                }
            });
        }
    }

    /* renamed from: U1 */
    public final void m13097U1(boolean z8) {
        if (getContext() != null) {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(getContext());
            if (!z8) {
                builderM16382a.setMessage(getContext().getString(R.string.delete_chat_notification)).setPositiveButton(getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: g3.l
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f16950b.m12969B1(dialogInterface, i9);
                    }
                }).setNegativeButton(getContext().getString(R.string.delete_btn), new DialogInterface.OnClickListener() { // from class: g3.m
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i9) {
                        this.f16957b.m12971C1(dialogInterface, i9);
                    }
                }).create().show();
                return;
            }
            final C5143f0 c5143f0 = new C5143f0(getContext(), "U", 0);
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_faq_checkbox, (ViewGroup) null);
            final CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.skip);
            builderM16382a.setView(viewInflate).setMessage(getContext().getString(R.string.send_unread_message_receipt_notification, Integer.valueOf(m13080M0()))).setPositiveButton(getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: g3.j
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f16933b.m13063z1(checkBox, c5143f0, dialogInterface, i9);
                }
            }).setNegativeButton(getContext().getString(R.string.delete_btn), new DialogInterface.OnClickListener() { // from class: g3.k
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f16943b.m12966A1(checkBox, c5143f0, dialogInterface, i9);
                }
            }).create().show();
        }
    }

    /* renamed from: V0 */
    public final void m13098V0(String str) {
        Friend friendM15001A;
        if (str == null || (friendM15001A = C2950b0.m14899A().m15001A(str)) == null) {
            return;
        }
        final Group groupM15081r = C2950b0.m14912k().m15081r(friendM15001A.f13648f);
        if (m13108a1(groupM15081r)) {
            return;
        }
        m12963k(new Runnable() { // from class: g3.t
            @Override // java.lang.Runnable
            public final void run() {
                this.f17008b.m13027n1(groupM15081r);
            }
        });
    }

    /* renamed from: V1 */
    public final void m13099V1() {
        boolean z8 = this.f11551y == 2 || this.f11535i.getText().length() > 0;
        int iM13450q = this.f11545s.m13450q();
        if (!z8) {
            this.f11544r.setVisibility(8);
            this.f11537k.setVisibility(8);
            return;
        }
        this.f11544r.setVisibility(0);
        if (iM13450q == 0) {
            this.f11544r.setAlpha(BitmapDescriptorFactory.HUE_RED);
            this.f11537k.setVisibility(0);
        } else {
            this.f11544r.setAlpha(1.0f);
            this.f11537k.setVisibility(8);
        }
    }

    /* renamed from: W0 */
    public final boolean m13100W0() {
        EditText editText = this.f11535i;
        return editText != null && editText.length() > 0;
    }

    /* renamed from: W1 */
    public final void m13101W1() {
        if (m13100W0()) {
            m13115g2(2);
        } else {
            m13115g2(1);
        }
    }

    /* renamed from: X0 */
    public final void m13102X0() {
        View view = this.f11531e;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* renamed from: X1 */
    public final void m13103X1() {
        if (C2907m0.m14454I().m14489N()) {
            m13067F0();
        } else {
            this.f11513H = new C2907m0.h() { // from class: g3.p
                @Override // com.cyberlink.you.chat.C2907m0.h
                /* renamed from: x */
                public final void mo119x(boolean z8) {
                    this.f16980b.m12973D1(z8);
                }
            };
            C2907m0.m14454I().m14511u(this.f11513H);
        }
    }

    /* renamed from: Y0 */
    public final void m13104Y0(View view) {
        this.f11544r = (ExpandableListView) view.findViewById(R.id.AllCategoryExpandableListView);
        this.f11545s.m13444k(1, new ArrayList());
        this.f11545s.m13444k(2, new ArrayList());
        this.f11545s.m13444k(3, new ArrayList());
        this.f11544r.setAdapter(this.f11545s);
        this.f11544r.setOnChildClickListener(this.f11516K);
        this.f11544r.setOnTouchListener(new View.OnTouchListener() { // from class: g3.n
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f16965b.m13030o1(view2, motionEvent);
            }
        });
        registerForContextMenu(this.f11544r);
    }

    /* renamed from: Y1 */
    public final void m13105Y1() {
        t tVar = new t(this, null);
        this.f11512G = tVar;
        tVar.executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: Z0 */
    public final boolean m13106Z0(Friend friend) {
        return friend instanceof FriendGroup;
    }

    /* renamed from: Z1 */
    public final void m13107Z1() {
        s sVar = new s(this.f11546t);
        this.f11510E = sVar;
        sVar.executeOnExecutor(C6385v.f21553a, new Void[0]);
    }

    /* renamed from: a1 */
    public final boolean m13108a1(Group group) {
        return group == null || C2950b0.m14916o().m15178q(String.valueOf(group.f13727n)) <= 0;
    }

    /* renamed from: a2 */
    public void m13109a2() {
        m12963k(new Runnable() { // from class: g3.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f16891b.m12975E1();
            }
        });
    }

    /* renamed from: b2 */
    public final void m13110b2() {
        Iterator<String> it = Globals.m7388i0().m7537e0().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null) {
                C5180s.m20255h(next);
            }
        }
        Globals.m7388i0().m7634w1();
    }

    /* renamed from: c2 */
    public final void m13111c2() {
        boolean zM14204A0 = XMPPManager.m14184g0().m14204A0();
        if (!zM14204A0) {
            if (C6456d.m24714D().m24748G()) {
                String string = getString(R.string.connecting);
                if (Globals.m7388i0().m7534d2()) {
                    string = string + " (" + C6456d.m24714D().m24746E() + ")";
                } else {
                    zM14204A0 = true;
                }
                this.f11532f.setText(string);
            } else {
                this.f11532f.setText(getString(R.string.error_no_network));
            }
        }
        this.f11532f.setVisibility(zM14204A0 ? 8 : 0);
    }

    /* renamed from: d2 */
    public final void m13112d2() {
        if (this.f11531e == null) {
            return;
        }
        boolean z8 = true;
        int i9 = 0;
        boolean z9 = this.f11551y == 2 || this.f11535i.getText().length() > 0;
        if (this.f11543q.getCount() <= 0 && !z9) {
            z8 = false;
        }
        this.f11531e.setVisibility(z8 ? 8 : 0);
        View view = this.f11533g;
        if (view != null) {
            if (!((C4857f) getParentFragment()).m19196r() && this.f11543q.getCount() <= 0) {
                i9 = 8;
            }
            view.setVisibility(i9);
        }
    }

    /* renamed from: e2 */
    public final void m13113e2() {
        long jM7528c0 = Globals.m7388i0().m7528c0();
        List<Integer> listM13451r = this.f11545s.m13451r();
        if (listM13451r == null) {
            return;
        }
        long[] jArr = {16, 2, 1};
        int[] iArr = {2, 1, 3};
        for (int i9 = 0; i9 < 3; i9++) {
            int i10 = iArr[i9];
            long j9 = jArr[i9];
            int iIndexOf = listM13451r.indexOf(Integer.valueOf(i10));
            if (iIndexOf >= 0) {
                boolean zIsGroupExpanded = this.f11544r.isGroupExpanded(iIndexOf);
                if ((j9 & jM7528c0) != 0) {
                    if (!zIsGroupExpanded) {
                        this.f11544r.expandGroup(iIndexOf);
                    }
                } else if (zIsGroupExpanded) {
                    this.f11544r.collapseGroup(iIndexOf);
                }
            }
        }
    }

    /* renamed from: f2 */
    public final void m13114f2(final Group group) {
        m12963k(new Runnable() { // from class: g3.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f16884b.m12977F1(group);
            }
        });
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: g */
    public int mo12959g() {
        return R.layout.fragment_chat_group;
    }

    /* renamed from: g2 */
    public final void m13115g2(int i9) {
        if (i9 == 1) {
            this.f11542p.setVisibility(0);
            this.f11544r.setVisibility(8);
            this.f11537k.setVisibility(8);
        } else if (i9 == 2) {
            this.f11542p.setVisibility(8);
        }
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a
    /* renamed from: j */
    public boolean mo12962j() {
        C2931y c2931y = this.f11543q;
        if (c2931y == null || !c2931y.m25234c()) {
            return false;
        }
        this.f11538l.callOnClick();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i9, int i10, Intent intent) {
        Group group;
        super.onActivityResult(i9, i10, intent);
        if (i10 == 0) {
            return;
        }
        if (i9 != 0) {
            if (i9 != 1 || (group = (Group) intent.getParcelableExtra("Group")) == null) {
                return;
            }
            if (this.f11543q.m14656m(group.f13723j) == null && !m13108a1(group)) {
                m13114f2(group);
            }
            m13004d1(group);
            return;
        }
        this.f11550x = false;
        if (intent.getBooleanExtra(ProductAction.ACTION_REMOVE, false)) {
            this.f11543q.notifyDataSetChanged();
            return;
        }
        Group group2 = (Group) intent.getParcelableExtra("StartOtherGroup");
        if (group2 != null) {
            m13004d1(group2);
        } else {
            m13105Y1();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof p) {
            this.f11530d = (p) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        int i9 = adapterContextMenuInfo.position;
        if (menuItem.getItemId() == 0) {
            Group item = this.f11543q.getItem(i9);
            if (!item.f13714M && C2907m0.m14454I().m14483G(item.f13723j) == 0) {
                this.f11506A.m15716T0(item.f13727n, true);
            }
            return true;
        }
        if (menuItem.getItemId() == 1) {
            View view = adapterContextMenuInfo.targetView;
            if (!this.f11543q.m25234c()) {
                m13093S1(true);
                m13089Q1(view, i9, true);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        if (contextMenu == null || view == null || this.f11543q == null || ((C4857f) getParentFragment()).m19196r()) {
            return;
        }
        if (view instanceof ListView) {
            Group item = this.f11543q.getItem(((AdapterView.AdapterContextMenuInfo) contextMenuInfo).position);
            contextMenu.setHeaderTitle(item.f13717d);
            if (!item.f13714M && C2907m0.m14454I().m14483G(item.f13723j) == 0) {
                contextMenu.add(0, 0, 0, getResources().getString(R.string.set_reminder));
            }
            contextMenu.add(0, 1, 0, getResources().getString(R.string.delete_btn));
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        m13078L0();
        AsyncTask asyncTask = this.f11508C;
        if (asyncTask != null) {
            asyncTask.cancel(true);
            this.f11508C = null;
        }
        AsyncTask asyncTask2 = this.f11509D;
        if (asyncTask2 != null) {
            asyncTask2.cancel(true);
            this.f11509D = null;
        }
        s sVar = this.f11510E;
        if (sVar != null) {
            sVar.cancel(true);
            this.f11510E = null;
        }
        r rVar = this.f11511F;
        if (rVar != null) {
            rVar.cancel(true);
            this.f11511F = null;
        }
        t tVar = this.f11512G;
        if (tVar != null) {
            tVar.cancel(true);
            this.f11512G = null;
        }
        super.onDestroy();
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        unregisterForContextMenu(this.f11544r);
        XMPPManager.m14184g0().m14233Z0(this.f11522Q);
        C5321e.m20824o().m20832B0(this.f11523R);
        XMPPManager.m14184g0().m14232Y0(this.f11525T);
        if (this.f11513H != null) {
            C2907m0.m14454I().m14495W(this.f11513H);
        }
        FriendsClient friendsClient = this.f11506A;
        if (friendsClient != null) {
            friendsClient.m15717U0();
            this.f11506A = null;
        }
        this.f11535i.removeTextChangedListener(this.f11519N);
    }

    @Override // com.cyberlink.you.activity.ulauncher.AbstractC2555a, androidx.fragment.app.Fragment
    public void onDetach() {
        this.f11530d = null;
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.f11547u.m24533e();
        super.onPause();
        XMPPManager.m14184g0().m14235a1(this.f11524S);
        m13102X0();
        this.f11535i.clearFocus();
        this.f11547u.m24532d("ChatGroupFragment : onPause");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        this.f11547u.m24533e();
        super.onResume();
        m13111c2();
        XMPPManager.m14184g0().m14211K(this.f11524S);
        m13113e2();
        m13101W1();
        this.f11550x = false;
        Intent intent = getActivity().getIntent();
        new h(intent.getBooleanExtra("checkLastMsg", false), intent).executeOnExecutor(C6385v.f21553a, new Void[0]);
        m13102X0();
        this.f11547u.m24532d("ChatGroupFragment : onResume");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        this.f11547u.m24533e();
        super.onViewCreated(view, bundle);
        View viewFindViewById = view.findViewById(R.id.ChatThreadCancelBtn);
        this.f11538l = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f11527V);
        View viewFindViewById2 = view.findViewById(R.id.ChatThreadDeleteBtn);
        this.f11539m = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this.f11528W);
        View viewFindViewById3 = view.findViewById(R.id.ChatThreadArchiveBtn);
        this.f11540n = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this.f11529X);
        this.f11531e = getActivity().findViewById(R.id.ULauncherChatGroupEmptyView);
        this.f11532f = (TextView) getParentFragment().getView().findViewById(R.id.noConnectionText);
        this.f11541o = view.findViewById(R.id.ChatThreadBottomLayout);
        this.f11506A = new FriendsClient(true);
        this.f11543q = new C2931y(getContext(), R.layout.view_item_chat_group, new ArrayList());
        ListView listView = (ListView) view.findViewById(R.id.ChatThreadListView);
        this.f11542p = listView;
        listView.setAdapter((ListAdapter) this.f11543q);
        this.f11542p.setOnItemClickListener(this.f11517L);
        this.f11542p.setOnTouchListener(new View.OnTouchListener() { // from class: g3.i0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f16898b.m13045t1(view2, motionEvent);
            }
        });
        C2562h c2562h = new C2562h(getContext());
        this.f11545s = c2562h;
        c2562h.m13456w(new C2562h.e() { // from class: g3.j0
            @Override // com.cyberlink.you.activity.ulauncher.C2562h.e
            /* renamed from: a */
            public final void mo13465a() {
                this.f16936a.m13048u1();
            }
        });
        m13104Y0(view);
        m13077K1(this.f11510E == null);
        this.f11536j = (ProgressBar) getParentFragment().getView().findViewById(R.id.progressbar_searching);
        this.f11537k = getParentFragment().getView().findViewById(R.id.emptyTextView);
        this.f11533g = getParentFragment().getView().findViewById(R.id.searchChat);
        EditText editText = (EditText) getParentFragment().getView().findViewById(R.id.SearchEditText);
        this.f11535i = editText;
        editText.addTextChangedListener(this.f11519N);
        this.f11535i.setHint(getString(R.string.search_chat_hint_v2));
        this.f11535i.requestFocus();
        this.f11535i.setOnEditorActionListener(this.f11520O);
        View viewFindViewById4 = getParentFragment().getView().findViewById(R.id.SearchEditXImage);
        this.f11534h = viewFindViewById4;
        viewFindViewById4.setOnClickListener(this.f11526U);
        C6385v.m24525c(new Runnable() { // from class: g3.k0
            @Override // java.lang.Runnable
            public final void run() {
                this.f16946b.m13079L1();
            }
        });
        this.f11530d.mo12937q0(this.f11521P, PageType.Chats);
        XMPPManager.m14184g0().m14207H(this.f11522Q);
        C5321e.m20824o().m20875k(this.f11523R);
        XMPPManager.m14184g0().m14206G(this.f11525T);
        m13103X1();
        m13068G0();
        this.f11514I = new C4619d(getContext(), new g());
        View viewFindViewById5 = view.findViewById(R.id.SearchShadow);
        this.f11515J = viewFindViewById5;
        viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: g3.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f16951b.m13051v1(view2);
            }
        });
        if (((C4857f) getParentFragment()).m19196r() && this.f11535i.getText().length() == 0) {
            this.f11515J.setVisibility(0);
        }
        registerForContextMenu(this.f11542p);
        this.f11547u.m24532d("ChatGroupFragment : onViewCreated");
    }
}
