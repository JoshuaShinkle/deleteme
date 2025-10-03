package com.cyberlink.you.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.friend.FriendProfileActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.UserInfo;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.cyberlink.you.utility.Permission.Permission;
import com.rockerhieu.emojicon.EmojiconTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.jivesoftware.smack.util.C5616j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p116k4.C5155j0;
import p116k4.C5172p;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;
import p136m3.C5321e;
import p173q2.C6127a;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class QRCodeInviteActivity extends BaseActivity {

    /* renamed from: n */
    public static final String f8569n = "QRCodeInviteActivity";

    /* renamed from: c */
    public FriendsClient f8570c;

    /* renamed from: d */
    public ImageView f8571d;

    /* renamed from: e */
    public RelativeLayout f8572e;

    /* renamed from: f */
    public RelativeLayout f8573f;

    /* renamed from: g */
    public Dialog f8574g;

    /* renamed from: h */
    public String f8575h;

    /* renamed from: i */
    public Bitmap f8576i;

    /* renamed from: j */
    public C1643b f8577j;

    /* renamed from: k */
    public View f8578k;

    /* renamed from: l */
    public View.OnClickListener f8579l = new ViewOnClickListenerC1642a();

    /* renamed from: m */
    public final C5321e.m f8580m = new C5321e.m() { // from class: com.cyberlink.you.activity.gc
        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public final boolean mo8241A0(C2904l c2904l, Map map) {
            return this.f10692b.m9451o0(c2904l, map);
        }
    };

    /* renamed from: com.cyberlink.you.activity.QRCodeInviteActivity$a */
    public class ViewOnClickListenerC1642a implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.QRCodeInviteActivity$a$a */
        public class a implements InterfaceC5288c {

            /* renamed from: a */
            public final /* synthetic */ Permission f8582a;

            public a(Permission permission) {
                this.f8582a = permission;
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: a */
            public void mo6907a(boolean z8) {
                if (z8) {
                    C5183t0.m20262b(QRCodeInviteActivity.this, this.f8582a);
                } else {
                    C5187v0.m20267c(R.string.permission_denied);
                }
            }

            @Override // p127l4.InterfaceC5288c
            /* renamed from: b */
            public void mo6908b() {
                if (QRCodeInviteActivity.this.f8576i == null || !CLUtility.m16613z1(null, CLUtility.m16554k2(QRCodeInviteActivity.this.f8576i, true))) {
                    return;
                }
                C5187v0.m20267c(R.string.group_qrcode_save_success);
            }
        }

        public ViewOnClickListenerC1642a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public /* synthetic */ void m9479d(View view) {
            QRCodeInviteActivity.this.f8574g.dismiss();
            QRCodeInviteActivity.this.m9465Q();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m9480e(View view) {
            QRCodeInviteActivity.this.f8574g.dismiss();
            QRCodeInviteActivity.this.m9475x0();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m9481f(View view) {
            QRCodeInviteActivity.this.f8574g.dismiss();
            Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.IMAGE : Permission.STORAGE;
            C5287b.m20583f(permission, new a(permission), QRCodeInviteActivity.this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (QRCodeInviteActivity.this.f8574g == null) {
                QRCodeInviteActivity qRCodeInviteActivity = QRCodeInviteActivity.this;
                qRCodeInviteActivity.f8574g = C3123g.m16384c(qRCodeInviteActivity);
                QRCodeInviteActivity.this.f8574g.setContentView(R.layout.dialog_qrcode_more);
                QRCodeInviteActivity.this.f8574g.findViewById(R.id.item_change_qrcode_link).setVisibility(8);
                QRCodeInviteActivity.this.f8574g.findViewById(R.id.item_change_qrcode).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.pc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11041b.m9479d(view2);
                    }
                });
                QRCodeInviteActivity.this.f8574g.findViewById(R.id.item_send_qrcode).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.qc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11084b.m9480e(view2);
                    }
                });
                QRCodeInviteActivity.this.f8574g.findViewById(R.id.item_save_qrcode).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.rc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f11143b.m9481f(view2);
                    }
                });
                QRCodeInviteActivity.this.f8574g.findViewById(R.id.cancel).setVisibility(8);
            }
            CLUtility.m16578q2(QRCodeInviteActivity.this.m9467V(), QRCodeInviteActivity.this.f8574g);
            QRCodeInviteActivity.this.f8574g.show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.QRCodeInviteActivity$b */
    public class C1643b extends BaseAdapter {

        /* renamed from: b */
        public Context f8584b;

        /* renamed from: c */
        public final ArrayList<InvitationFriend> f8585c = new ArrayList<>();

        /* renamed from: e */
        public View.OnClickListener f8587e = new View.OnClickListener() { // from class: com.cyberlink.you.activity.sc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11174b.m9484e(view);
            }
        };

        /* renamed from: f */
        public View.OnClickListener f8588f = new View.OnClickListener() { // from class: com.cyberlink.you.activity.tc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f11397b.m9485f(view);
            }
        };

        /* renamed from: d */
        public InvitationFriend.C3058b f8586d = new InvitationFriend.C3058b();

        /* renamed from: com.cyberlink.you.activity.QRCodeInviteActivity$b$a */
        public class a {

            /* renamed from: a */
            public ImageView f8590a;

            /* renamed from: b */
            public EmojiconTextView f8591b;

            /* renamed from: c */
            public EmojiconTextView f8592c;

            /* renamed from: d */
            public Button f8593d;

            /* renamed from: e */
            public Button f8594e;

            /* renamed from: f */
            public View f8595f;

            public /* synthetic */ a(C1643b c1643b, ViewGroup viewGroup, ViewOnClickListenerC1642a viewOnClickListenerC1642a) {
                this(viewGroup);
            }

            public a(ViewGroup viewGroup) {
                View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_friends_inviting, viewGroup, false);
                this.f8595f = viewInflate;
                this.f8590a = (ImageView) viewInflate.findViewById(R.id.FriendsInvitingCircleImageView_DisplayImage);
                this.f8591b = (EmojiconTextView) this.f8595f.findViewById(R.id.FriendsInvitingTextView_DisplayName);
                this.f8592c = (EmojiconTextView) this.f8595f.findViewById(R.id.FriendsInvitingTextView_message);
                this.f8593d = (Button) this.f8595f.findViewById(R.id.FriendsInvitingPinkBtn);
                this.f8594e = (Button) this.f8595f.findViewById(R.id.FriendsInvitingBtn);
            }
        }

        public C1643b(Context context) {
            this.f8584b = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: e */
        public /* synthetic */ void m9484e(View view) {
            QRCodeInviteActivity.this.m9466T((InvitationFriend) view.getTag(), false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m9485f(View view) {
            QRCodeInviteActivity.this.m9463B((InvitationFriend) view.getTag(), false);
        }

        /* renamed from: c */
        public void m9486c(InvitationFriend invitationFriend) {
            this.f8585c.add(invitationFriend);
            Collections.sort(this.f8585c, this.f8586d);
            notifyDataSetChanged();
            m9490i();
        }

        @Override // android.widget.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public InvitationFriend getItem(int i9) {
            return this.f8585c.get(i9);
        }

        /* renamed from: g */
        public void m9488g(long j9) {
            Iterator<InvitationFriend> it = this.f8585c.iterator();
            while (it.hasNext()) {
                InvitationFriend next = it.next();
                if (next.f13747h == j9) {
                    QRCodeInviteActivity.this.f8577j.m9489h(next);
                    return;
                }
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f8585c.size();
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            return i9;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            View view2;
            a aVar;
            if (view == null) {
                aVar = new a(this, viewGroup, null);
                view2 = aVar.f8595f;
                view2.setTag(aVar);
            } else {
                view2 = view;
                aVar = (a) view.getTag();
            }
            InvitationFriend item = getItem(i9);
            C6127a.m23471l(this.f8584b, aVar.f8590a, item);
            aVar.f8591b.setText(item.m15763a());
            aVar.f8592c.setText(item.f13752m);
            aVar.f8593d.setVisibility(0);
            aVar.f8594e.setVisibility(0);
            aVar.f8593d.setTag(item);
            aVar.f8594e.setTag(item);
            aVar.f8593d.setText(R.string.friends_friends_title_request_not_now);
            aVar.f8594e.setText(R.string.friends_friends_title_request_accept);
            aVar.f8593d.setOnClickListener(this.f8587e);
            aVar.f8594e.setOnClickListener(this.f8588f);
            return view2;
        }

        /* renamed from: h */
        public void m9489h(InvitationFriend invitationFriend) {
            this.f8585c.remove(invitationFriend);
            Collections.sort(this.f8585c, this.f8586d);
            notifyDataSetChanged();
            m9490i();
        }

        /* renamed from: i */
        public void m9490i() {
            if (getCount() > 0) {
                QRCodeInviteActivity.this.f8578k.setVisibility(0);
            } else {
                QRCodeInviteActivity.this.f8578k.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c0 */
    public /* synthetic */ void m9435c0(InvitationFriend invitationFriend, boolean z8, String str, String str2, String str3, String str4) {
        if (!"200".equals(str3)) {
            Log.i(f8569n, "[acceptInvite] Accept Fail");
        } else {
            m9473u0(invitationFriend, z8);
            Log.i(f8569n, "[acceptInvite] Accept Success");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e0 */
    public /* synthetic */ void m9437e0(Dialog dialog, View view) throws Throwable {
        m9468X(true);
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g0 */
    public /* synthetic */ void m9438g0(InvitationFriend invitationFriend, boolean z8, String str, String str2, String str3, String str4) {
        if (!"200".equals(str3)) {
            Log.i(f8569n, "[declineInvite] Decline Fail");
        } else {
            m9473u0(invitationFriend, z8);
            Log.i(f8569n, "[declineInvite] Decline Success");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i0 */
    public /* synthetic */ void m9440i0() {
        this.f8571d.setImageBitmap(this.f8576i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j0 */
    public /* synthetic */ void m9442j0(String str, String str2, String str3, String str4) throws JSONException {
        String string;
        if (!"200".equals(str3)) {
            Log.d(f8569n, "QRCode invite failed with status code " + str3);
            return;
        }
        if (str4 == null) {
            Log.d(f8569n, "Response JSONstr is null");
            return;
        }
        try {
            string = new JSONObject(str4).getString("inviteURL");
            this.f8575h = string;
        } catch (JSONException e9) {
            e9.printStackTrace();
        }
        if (string.isEmpty()) {
            Log.d(f8569n, "get mFriendLink from JSONstr fail");
            return;
        }
        getApplicationContext().getSharedPreferences("U", 0).edit().putString("inviteFriendLink", this.f8575h).apply();
        this.f8576i = C5155j0.m20084b(this.f8575h);
        m9467V().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.dc
            @Override // java.lang.Runnable
            public final void run() {
                this.f10370b.m9440i0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k0 */
    public /* synthetic */ void m9444k0(long j9) {
        this.f8577j.m9488g(j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l0 */
    public /* synthetic */ void m9446l0(long j9) {
        this.f8577j.m9488g(j9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m0 */
    public /* synthetic */ void m9447m0(InvitationFriend invitationFriend) {
        this.f8577j.m9486c(invitationFriend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n0 */
    public /* synthetic */ void m9449n0(String str, int i9, String str2, String str3, String str4, String str5, String str6) {
        JSONException e9;
        final InvitationFriend invitationFriendM20187i;
        if (!"200".equals(str5)) {
            Log.i(f8569n, "[QueryUserInviteListTask] fail statusCode=" + str5);
            return;
        }
        int iM16553k1 = CLUtility.m16553k1(str6);
        int iM16559m = CLUtility.m16559m(iM16553k1, 20);
        if (iM16553k1 > 0) {
            JSONArray jSONArrayM20196r = C5172p.m20196r(str6);
            int i10 = 0;
            boolean z8 = false;
            while (true) {
                boolean z9 = true;
                if (i10 >= jSONArrayM20196r.length()) {
                    break;
                }
                try {
                    invitationFriendM20187i = C5172p.m20187i(jSONArrayM20196r.getJSONObject(i10));
                } catch (JSONException e10) {
                    z9 = z8;
                    e9 = e10;
                }
                if (invitationFriendM20187i == null || Long.parseLong(str) != invitationFriendM20187i.f13747h) {
                    i10++;
                } else {
                    try {
                        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.ec
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f10404b.m9447m0(invitationFriendM20187i);
                            }
                        });
                        z8 = true;
                        break;
                    } catch (JSONException e11) {
                        e9 = e11;
                        Log.e(f8569n, "[paresInvitationFriendListFriends] 'invitatingJObj' parse error. " + e9.getMessage());
                        z8 = z9;
                        i10++;
                    }
                }
            }
            if (z8 || i9 == iM16559m) {
                return;
            }
            m9471b0(str2, i9 + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o0 */
    public /* synthetic */ boolean m9451o0(C2904l c2904l, Map map) throws NumberFormatException {
        String strM14428m;
        String strM14388C;
        Log.d(f8569n, "onReceive");
        if (c2904l != null) {
            strM14428m = c2904l.m14428m();
            strM14388C = c2904l.m14388C();
        } else {
            strM14428m = null;
            strM14388C = null;
        }
        String str = (String) map.get("eventType");
        if ("invite.friend.created".equals(str)) {
            m9471b0(strM14428m, 1);
        } else if ("invite.friend.canceled".equals(str)) {
            m9470Z(strM14428m);
        } else if ("invite.friend.accepted".equals(str)) {
            m9469Y(strM14428m, strM14388C);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p0 */
    public /* synthetic */ void m9452p0(View view) {
        m9472s0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q0 */
    public /* synthetic */ void m9454q0(AdapterView adapterView, View view, int i9, long j9) {
        Intent intent = new Intent(this, (Class<?>) FriendProfileActivity.class);
        InvitationFriend item = this.f8577j.getItem(i9);
        if (item != null) {
            intent.putExtra("InvitationFriend", item);
            intent.putExtra("type", FriendProfileActivity.FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION.name());
            startActivityForResult(intent, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r0 */
    public /* synthetic */ void m9456r0(boolean z8, InvitationFriend invitationFriend) {
        if (z8) {
            this.f8577j.m9488g(invitationFriend.f13747h);
        } else {
            this.f8577j.m9489h(invitationFriend);
        }
    }

    /* renamed from: B */
    public final void m9463B(final InvitationFriend invitationFriend, final boolean z8) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteId", Long.toString(invitationFriend.f13744e)));
        this.f8570c.m15734m("invite", "acceptInvitation", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.cc
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f9780a.m9435c0(invitationFriend, z8, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: P */
    public final void m9464P() {
        if (this.f8572e.getVisibility() == 0) {
            this.f8572e.setVisibility(4);
            this.f8573f.setVisibility(0);
        } else {
            this.f8572e.setVisibility(0);
            this.f8573f.setVisibility(4);
        }
    }

    /* renamed from: Q */
    public final void m9465Q() {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        dialogM16384c.setContentView(R.layout.dialog_remove_friend_confirm);
        ((TextView) dialogM16384c.findViewById(R.id.title)).setText(R.string.qrcode_renew_dialog_title);
        ((TextView) dialogM16384c.findViewById(R.id.warning_message)).setText(R.string.qrcode_renew_dialog_messsage);
        m9474w0(dialogM16384c);
        dialogM16384c.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.nc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogM16384c.dismiss();
            }
        });
        dialogM16384c.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.oc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                this.f11010b.m9437e0(dialogM16384c, view);
            }
        });
        dialogM16384c.show();
    }

    /* renamed from: T */
    public final void m9466T(final InvitationFriend invitationFriend, final boolean z8) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteId", Long.toString(invitationFriend.f13744e)));
        this.f8570c.m15734m("invite", "declineInvitation", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.fc
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10438a.m9438g0(invitationFriend, z8, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: V */
    public Activity m9467V() {
        return this;
    }

    /* renamed from: X */
    public final void m9468X(boolean z8) throws Throwable {
        String str;
        if (z8) {
            str = "renewInviteURL";
        } else {
            String string = getApplicationContext().getSharedPreferences("U", 0).getString("inviteFriendLink", "");
            this.f8575h = string;
            if (!string.isEmpty()) {
                Bitmap bitmapM20084b = C5155j0.m20084b(this.f8575h);
                this.f8576i = bitmapM20084b;
                this.f8571d.setImageBitmap(bitmapM20084b);
                return;
            }
            str = "genInviteURL";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        this.f8570c.m15734m("invite", str, arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.mc
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) throws JSONException {
                this.f10890a.m9442j0(str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: Y */
    public final void m9469Y(String str, String str2) throws NumberFormatException {
        String strM22346k = C5616j.m22346k(str);
        String strM22346k2 = C5616j.m22346k(str2);
        if (strM22346k == null || strM22346k2 == null) {
            Log.d(f8569n, "[handleInvitingAcceptEvent] myUserId or inviteUserId return null");
        } else if (strM22346k.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            final long j9 = Long.parseLong(strM22346k2);
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.jc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10785b.m9444k0(j9);
                }
            });
        }
    }

    /* renamed from: Z */
    public final void m9470Z(String str) throws NumberFormatException {
        String strM22346k = C5616j.m22346k(str);
        if (strM22346k == null) {
            Log.d(f8569n, "[handleInvitingCreatedEvent] parseName(from) return null");
        } else {
            if (strM22346k.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
                return;
            }
            final long j9 = Long.parseLong(strM22346k);
            runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.lc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10855b.m9446l0(j9);
                }
            });
        }
    }

    /* renamed from: b0 */
    public final void m9471b0(final String str, final int i9) {
        final String strM22346k = C5616j.m22346k(str);
        if (strM22346k == null) {
            Log.d(f8569n, "[handleInvitingCreatedEvent] parseName(from) return null");
            return;
        }
        if (strM22346k.equals(String.valueOf(Globals.m7388i0().m7568k1()))) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("pageIndex", String.valueOf(i9)));
        arrayList.add(new C6301o("pageSize", String.valueOf(20)));
        this.f8570c.m15734m("invite", "listReceivedInvite", arrayList, new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.kc
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f10823a.m9449n0(strM22346k, i9, str, str2, str3, str4, str5);
            }
        });
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 1 && i10 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("type");
            InvitationFriend invitationFriend = (InvitationFriend) intent.getParcelableExtra("InvitationFriend");
            if (FriendProfileActivity.FPA_RETURN_TYPE.RETURN_ACCEPT.name().equals(stringExtra)) {
                m9473u0(invitationFriend, true);
                return;
            }
            if (FriendProfileActivity.FPA_RETURN_TYPE.RETURN_REJECT.name().equals(stringExtra)) {
                m9473u0(invitationFriend, true);
                return;
            }
            Log.d(f8569n, "[onActivityResult] OP_SHOW_REQUEST_INVITATION_PROFILE: return type " + stringExtra);
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws Throwable {
        super.onCreate(bundle);
        setContentView(R.layout.activity_invitation_qrcode);
        this.f8570c = new FriendsClient(true);
        this.f8571d = (ImageView) findViewById(R.id.QRCodeGenerateImageView);
        m9468X(false);
        this.f8572e = (RelativeLayout) findViewById(R.id.layoutQRCodeSelfArea);
        this.f8573f = (RelativeLayout) findViewById(R.id.layoutQRCodeFriendArea);
        this.f8578k = findViewById(R.id.topDivider);
        findViewById(R.id.CQRCodeMoreBtn).setOnClickListener(this.f8579l);
        findViewById(R.id.QRCodeBackBtn).setOnClickListener(new View.OnClickListener() { // from class: com.cyberlink.you.activity.hc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10723b.m9452p0(view);
            }
        });
        C5321e.m20824o().m20875k(this.f8580m);
        this.f8577j = new C1643b(this);
        ListView listView = (ListView) findViewById(R.id.inviteReceivedList);
        listView.setAdapter((ListAdapter) this.f8577j);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.cyberlink.you.activity.ic
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i9, long j9) {
                this.f10753b.m9454q0(adapterView, view, i9, j9);
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        findViewById(R.id.CQRCodeMoreBtn).setOnClickListener(null);
        findViewById(R.id.QRCodeBackBtn).setOnClickListener(null);
        Dialog dialog = this.f8574g;
        if (dialog != null) {
            dialog.findViewById(R.id.item_change_qrcode).setOnClickListener(null);
            this.f8574g.findViewById(R.id.item_send_qrcode).setOnClickListener(null);
            this.f8574g.findViewById(R.id.item_save_qrcode).setOnClickListener(null);
        }
        FriendsClient friendsClient = this.f8570c;
        if (friendsClient != null) {
            friendsClient.m15717U0();
        }
        C5321e.m20824o().m20832B0(this.f8580m);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        if (i9 != 4) {
            return super.onKeyUp(i9, keyEvent);
        }
        m9472s0();
        return true;
    }

    /* renamed from: s0 */
    public final void m9472s0() {
        if (this.f8572e.getVisibility() == 0) {
            finish();
        } else {
            m9464P();
        }
    }

    /* renamed from: u0 */
    public final void m9473u0(final InvitationFriend invitationFriend, final boolean z8) {
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.bc
            @Override // java.lang.Runnable
            public final void run() {
                this.f9747b.m9456r0(z8, invitationFriend);
            }
        });
    }

    /* renamed from: w0 */
    public final void m9474w0(Dialog dialog) {
        m9467V().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        dialog.getWindow().getAttributes().width = (int) (930 * (r0.widthPixels / 1080.0f));
    }

    /* renamed from: x0 */
    public final void m9475x0() {
        UserInfo userInfoM16497V0 = CLUtility.m16497V0(getApplicationContext());
        if (userInfoM16497V0 == null) {
            return;
        }
        try {
            startActivity(C5155j0.m20083a(String.format(getString(R.string.invite_email_title_new), userInfoM16497V0.f13778c), String.format(getString(R.string.invite_email_new), userInfoM16497V0.f13778c, "<a href=\"" + this.f8575h + "\">" + this.f8575h + "</a>"), this.f8575h, null));
        } catch (ActivityNotFoundException unused) {
            C5187v0.m20267c(R.string.invite_email_no_client);
        }
    }
}
