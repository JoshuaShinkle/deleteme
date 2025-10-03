package com.cyberlink.you.activity.friend;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.friends.DepartmentFriend;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.friends.InvitationFriend;
import com.cyberlink.you.friends.SuggestionFriend;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.CLUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.zxing.client.android.Intents;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p116k4.C5152i0;
import p116k4.C5168n1;
import p116k4.C5170o0;
import p116k4.C5172p;
import p116k4.C5187v0;
import p173q2.C6127a;
import p173q2.C6129c;
import p201t3.C6288b;
import p201t3.C6289c;
import p201t3.C6301o;
import p209u2.AbstractC6381r;

/* loaded from: classes.dex */
public class FriendProfileActivity extends BaseActivity {

    /* renamed from: I */
    public FriendsClient.InterfaceC3051i f10573I;

    /* renamed from: c */
    public ImageView f10574c;

    /* renamed from: d */
    public ImageView f10575d;

    /* renamed from: e */
    public TextView f10576e;

    /* renamed from: f */
    public TextView f10577f;

    /* renamed from: g */
    public TextView f10578g;

    /* renamed from: h */
    public ProgressDialog f10579h;

    /* renamed from: i */
    public View f10580i;

    /* renamed from: j */
    public TextView f10581j;

    /* renamed from: k */
    public TextView f10582k;

    /* renamed from: l */
    public FPA_PROFILE_TYPE f10583l;

    /* renamed from: m */
    public FPA_RETURN_TYPE f10584m;

    /* renamed from: n */
    public boolean f10585n = false;

    /* renamed from: o */
    public boolean f10586o = false;

    /* renamed from: p */
    public FriendsClient f10587p = new FriendsClient();

    /* renamed from: q */
    public final ExecutorService f10588q = Executors.newSingleThreadExecutor();

    /* renamed from: r */
    public InvitationFriend f10589r = null;

    /* renamed from: s */
    public SuggestionFriend f10590s = null;

    /* renamed from: t */
    public DepartmentFriend f10591t = null;

    /* renamed from: u */
    public Friend f10592u = null;

    /* renamed from: v */
    public boolean f10593v = false;

    /* renamed from: w */
    public boolean f10594w = false;

    /* renamed from: x */
    public View.OnClickListener f10595x = new View.OnClickListener() { // from class: b3.g1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3181b.m12283N0(view);
        }
    };

    /* renamed from: y */
    public View.OnClickListener f10596y = new View.OnClickListener() { // from class: b3.h1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3185b.m12285O0(view);
        }
    };

    /* renamed from: z */
    public View.OnClickListener f10597z = new ViewOnClickListenerC2126b();

    /* renamed from: A */
    public View.OnClickListener f10565A = new ViewOnClickListenerC2127c();

    /* renamed from: B */
    public final View.OnClickListener f10566B = new View.OnClickListener() { // from class: b3.i1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3188b.m12289Q0(view);
        }
    };

    /* renamed from: C */
    public View.OnClickListener f10567C = new View.OnClickListener() { // from class: b3.j1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3193b.m12290R0(view);
        }
    };

    /* renamed from: D */
    public View.OnClickListener f10568D = new ViewOnClickListenerC2130f();

    /* renamed from: E */
    public View.OnClickListener f10569E = new ViewOnClickListenerC2131g();

    /* renamed from: F */
    public View.OnClickListener f10570F = new View.OnClickListener() { // from class: b3.k1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3196b.m12291S0(view);
        }
    };

    /* renamed from: G */
    public C6288b.d<Friend> f10571G = new C2133i();

    /* renamed from: H */
    public C6288b.h f10572H = new C6288b.h() { // from class: b3.l1
        @Override // p201t3.C6288b.h
        public final void onError(String str) {
            Log.e("FriendProfileActivity", str);
        }
    };

    public enum FPA_PROFILE_TYPE {
        PROFILE_TYPE_NONE,
        PROFILE_TYPE_REQUEST_INVITATION,
        PROFILE_TYPE_SENT_INVITATION,
        PROFILE_TYPE_VIEW_FRIEND,
        PROFILE_TYPE_VIEW_QRCODE_SCAN_RESULT,
        PROFILE_TYPE_SUGGESTIONS,
        PROFILE_TYPE_DEPARTMENTS,
        PROFILE_TYPE_INVITATION_LINK
    }

    public enum FPA_RETURN_TYPE {
        RETURN_NONE,
        RETURN_ACCEPT,
        RETURN_REJECT,
        RETURN_CANCEL,
        RETURN_CHAT_DUAL,
        RETURN_ADD_FRIEND,
        RETURN_BLOCK_FRIEND,
        RETURN_CALL
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$a */
    public class C2125a extends AbstractC6381r<Group, Void> {
        public C2125a(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("Group", group);
            Intent intent = new Intent(FriendProfileActivity.this, (Class<?>) ChatDialogActivity.class);
            intent.putExtras(bundle);
            FriendProfileActivity.this.startActivity(intent);
            FriendProfileActivity.this.finish();
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$b */
    public class ViewOnClickListenerC2126b implements View.OnClickListener {
        public ViewOnClickListenerC2126b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION)) {
                FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_ACCEPT;
                FriendProfileActivity friendProfileActivity = FriendProfileActivity.this;
                friendProfileActivity.m12334b1(friendProfileActivity.f10585n);
                return;
            }
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND)) {
                if (FriendProfileActivity.this.f10592u == null || C5168n1.m20149f(FriendProfileActivity.this.f10592u.f13645c)) {
                    return;
                }
                FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_ADD_FRIEND;
                FriendProfileActivity friendProfileActivity2 = FriendProfileActivity.this;
                friendProfileActivity2.m12334b1(friendProfileActivity2.f10585n);
                return;
            }
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SUGGESTIONS)) {
                FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_ADD_FRIEND;
                FriendProfileActivity friendProfileActivity3 = FriendProfileActivity.this;
                friendProfileActivity3.m12340h1(friendProfileActivity3.f10590s.f13761e, FriendProfileActivity.this.f10590s.f13759c);
            } else if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
                if (FriendProfileActivity.this.f10591t.f13626b > 0) {
                    FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_ADD_FRIEND;
                    FriendProfileActivity friendProfileActivity4 = FriendProfileActivity.this;
                    friendProfileActivity4.m12340h1(friendProfileActivity4.f10591t.f13629e, FriendProfileActivity.this.f10591t.f13626b);
                } else {
                    if (C5170o0.m20170e(FriendProfileActivity.this.f10591t.f13638n)) {
                        return;
                    }
                    FriendProfileActivity friendProfileActivity5 = FriendProfileActivity.this;
                    CLUtility.m16418A2(friendProfileActivity5, null, CLUtility.InviteType.EMail, new String[]{friendProfileActivity5.f10591t.f13638n});
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$c */
    public class ViewOnClickListenerC2127c implements View.OnClickListener {
        public ViewOnClickListenerC2127c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SUGGESTIONS)) {
                FriendProfileActivity.this.m12345r0();
            } else {
                Log.e("FriendProfileActivity", "[onBlockButtonClick] no match profileType do nothing");
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$d */
    public class C2128d extends AbstractC6381r<Group, Void> {
        public C2128d(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            C2925v.m14571O0(FriendProfileActivity.this, group, true, MimeTypes.BASE_TYPE_AUDIO, "friend profile audio meeting button");
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$e */
    public class C2129e extends AbstractC6381r<Group, Void> {
        public C2129e(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            C2925v.m14571O0(FriendProfileActivity.this, group, true, MimeTypes.BASE_TYPE_VIDEO, "friend profile video meeting button");
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$f */
    public class ViewOnClickListenerC2130f implements View.OnClickListener {
        public ViewOnClickListenerC2130f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FriendProfileActivity friendProfileActivity = FriendProfileActivity.this;
            friendProfileActivity.f10584m = C5168n1.m20149f(friendProfileActivity.f10592u.f13645c) ? FPA_RETURN_TYPE.RETURN_CHAT_DUAL : FPA_RETURN_TYPE.RETURN_BLOCK_FRIEND;
            FriendProfileActivity friendProfileActivity2 = FriendProfileActivity.this;
            friendProfileActivity2.m12335c1(friendProfileActivity2.f10592u);
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$g */
    public class ViewOnClickListenerC2131g implements View.OnClickListener {
        public ViewOnClickListenerC2131g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION)) {
                FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_REJECT;
                FriendProfileActivity friendProfileActivity = FriendProfileActivity.this;
                friendProfileActivity.m12334b1(friendProfileActivity.f10585n);
                return;
            }
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SENT_INVITATION) || FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
                FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_CANCEL;
                FriendProfileActivity friendProfileActivity2 = FriendProfileActivity.this;
                friendProfileActivity2.m12334b1(friendProfileActivity2.f10585n);
                return;
            }
            if (FriendProfileActivity.this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SUGGESTIONS)) {
                FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_BLOCK_FRIEND;
                FriendProfileActivity friendProfileActivity3 = FriendProfileActivity.this;
                friendProfileActivity3.m12334b1(friendProfileActivity3.f10585n);
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$h */
    public class C2132h extends FriendsClient.AbstractC3053k {
        public C2132h() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: f */
        public /* synthetic */ void m12358f(Friend friend) {
            if (!friend.f13660r.equals("Corporate")) {
                FriendProfileActivity friendProfileActivity = FriendProfileActivity.this;
                C6127a.m23469j(friendProfileActivity, friendProfileActivity.f10574c, friend);
                FriendProfileActivity.this.f10577f.setText(friend.m15621b());
                if (C5168n1.m20149f(friend.f13645c)) {
                    C5187v0.m20267c(R.string.error_friend_add_400);
                }
            } else if (C5168n1.m20149f(friend.f13645c)) {
                C5187v0.m20267c(R.string.error_friend_add_400);
            }
            FriendProfileActivity.this.m12337e1(friend);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g */
        public /* synthetic */ void m12359g(DialogInterface dialogInterface, int i9) {
            FriendProfileActivity.this.onBackPressed();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h */
        public /* synthetic */ void m12360h() {
            FriendProfileActivity.this.f10574c.setImageResource(R.drawable.pic_default);
            FriendProfileActivity.this.findViewById(R.id.buttonLayout).setVisibility(8);
            FriendProfileActivity.this.f10580i.setVisibility(8);
            AlertDialog.Builder builderM16382a = C3123g.m16382a(FriendProfileActivity.this);
            builderM16382a.setMessage(FriendProfileActivity.this.getString(R.string.user_not_found));
            builderM16382a.setPositiveButton(FriendProfileActivity.this.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: b3.p1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f3235b.m12359g(dialogInterface, i9);
                }
            });
            builderM16382a.setCancelable(false);
            builderM16382a.show();
        }

        @Override // com.cyberlink.you.friends.FriendsClient.AbstractC3053k
        /* renamed from: a */
        public void mo12361a(final Friend friend) {
            FriendProfileActivity.this.runOnUiThread(new Runnable() { // from class: b3.o1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3230b.m12358f(friend);
                }
            });
        }

        @Override // com.cyberlink.you.friends.FriendsClient.AbstractC3053k
        /* renamed from: b */
        public void mo12362b(String str, String str2) {
            FriendProfileActivity.this.runOnUiThread(new Runnable() { // from class: b3.n1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3226b.m12360h();
                }
            });
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$i */
    public class C2133i implements C6288b.d<Friend> {
        public C2133i() {
        }

        @Override // p201t3.C6288b.d
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(Friend friend) {
            FriendProfileActivity friendProfileActivity = FriendProfileActivity.this;
            C6127a.m23469j(friendProfileActivity, friendProfileActivity.f10574c, friend);
            if (FriendProfileActivity.this.f10576e != null) {
                FriendProfileActivity.this.f10576e.setText(friend.m15621b());
            }
            FriendProfileActivity.this.f10577f.setVisibility(8);
            if (TextUtils.isEmpty(friend.f13662t)) {
                FriendProfileActivity.this.f10593v = false;
            } else {
                FriendProfileActivity.this.f10593v = true;
                FriendProfileActivity.this.f10581j.setText(friend.f13662t);
            }
            FriendProfileActivity.this.m12341i1();
            FriendProfileActivity.this.m12342j1(friend.f13665w, friend.f13667y);
            FriendProfileActivity friendProfileActivity2 = FriendProfileActivity.this;
            C6129c.m23482c(friendProfileActivity2, friendProfileActivity2.f10575d, friend);
        }
    }

    /* renamed from: com.cyberlink.you.activity.friend.FriendProfileActivity$j */
    public class C2134j extends AbstractC6381r<Group, Void> {
        public C2134j(Handler handler) {
            super(handler);
        }

        @Override // p209u2.AbstractC6381r
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public void m24503g(Group group) {
            Intent intent = new Intent();
            intent.putExtra("StartOtherGroup", group);
            intent.putExtra("type", FriendProfileActivity.this.f10584m.name());
            intent.putExtra("userId", FriendProfileActivity.this.f10592u.f13645c);
            FriendProfileActivity.this.setResult(-1, intent);
            FriendProfileActivity.this.finish();
            FriendProfileActivity.this.f10584m = FPA_RETURN_TYPE.RETURN_NONE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: D0 */
    public /* synthetic */ void m12267D0(ProgressDialog progressDialog, String str) {
        C5152i0.m20065b(progressDialog);
        if ("200".equals(str)) {
            this.f10584m = FPA_RETURN_TYPE.RETURN_BLOCK_FRIEND;
            Intent intent = new Intent();
            intent.putExtra("suggestionFriend", this.f10590s);
            intent.putExtra("type", this.f10584m.name());
            setResult(-1, intent);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: E0 */
    public /* synthetic */ void m12269E0(final ProgressDialog progressDialog, String str, String str2, final String str3, String str4) {
        runOnUiThread(new Runnable() { // from class: b3.c1
            @Override // java.lang.Runnable
            public final void run() {
                this.f3156b.m12267D0(progressDialog, str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F0 */
    public /* synthetic */ void m12270F0(InvitationFriend invitationFriend) {
        this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_SENT_INVITATION;
        this.f10589r = invitationFriend;
        findViewById(R.id.friendBtnLayout).setVisibility(8);
        findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
        findViewById(R.id.cancelBtnLayout).setVisibility(0);
        findViewById(R.id.buttonLayout).setVisibility(8);
        m12341i1();
        this.f10580i.setVisibility(8);
        this.f10578g.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: G0 */
    public /* synthetic */ void m12271G0(InvitationFriend invitationFriend) {
        this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION;
        this.f10589r = invitationFriend;
        findViewById(R.id.friendBtnLayout).setVisibility(8);
        findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
        findViewById(R.id.cancelBtnLayout).setVisibility(8);
        findViewById(R.id.buttonLayout).setVisibility(0);
        if (C5170o0.m20170e(this.f10589r.f13752m)) {
            this.f10594w = false;
        } else {
            this.f10594w = true;
            this.f10582k.setText(this.f10589r.f13752m.trim());
        }
        m12341i1();
        this.f10580i.setVisibility(8);
        this.f10578g.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: H0 */
    public /* synthetic */ void m12273H0() {
        List<InvitationFriend> listM15702J = this.f10587p.m15702J(FriendsClient.InvitationFriendType.SENT);
        List<InvitationFriend> listM15702J2 = this.f10587p.m15702J(FriendsClient.InvitationFriendType.RECEIVED);
        for (final InvitationFriend invitationFriend : listM15702J) {
            if (invitationFriend.f13746g == this.f10592u.f13645c) {
                runOnUiThread(new Runnable() { // from class: b3.w0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3269b.m12270F0(invitationFriend);
                    }
                });
                return;
            }
        }
        for (final InvitationFriend invitationFriend2 : listM15702J2) {
            if (invitationFriend2.f13747h == this.f10592u.f13645c) {
                runOnUiThread(new Runnable() { // from class: b3.x0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3272b.m12271G0(invitationFriend2);
                    }
                });
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: I0 */
    public /* synthetic */ void m12275I0(Friend friend) {
        this.f10592u = friend;
        this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND;
        m12349x0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: J0 */
    public /* synthetic */ void m12277J0(Friend friend) {
        if (m7364e()) {
            return;
        }
        C6127a.m23469j(this, this.f10574c, friend);
        TextView textView = this.f10576e;
        if (textView != null) {
            textView.setText(friend.m15621b());
        }
        TextView textView2 = this.f10577f;
        if (textView2 != null) {
            textView2.setText(friend.m15620a());
        }
        TextView textView3 = this.f10581j;
        if (textView3 != null) {
            this.f10593v = true;
            textView3.setVisibility(0);
            this.f10581j.setText(friend.f13662t);
        }
        Log.d("FriendProfileActivity", "Scan success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: K0 */
    public /* synthetic */ void m12278K0(String str, String str2, String str3, String str4) {
        final Friend friendM20184f;
        if (str3 == null || !str3.equals("200") || (friendM20184f = C5172p.m20184f(C5172p.m20195q(str4))) == null) {
            C5187v0.m20267c(R.string.qrcode_scan_error);
            return;
        }
        if (friendM20184f.f13645c == Globals.m7388i0().m7568k1().longValue()) {
            C5187v0.m20267c(R.string.add_yourself_friend);
        } else if (C5168n1.m20149f(friendM20184f.f13645c)) {
            runOnUiThread(new Runnable() { // from class: b3.d1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3164b.m12275I0(friendM20184f);
                }
            });
        } else {
            m12337e1(friendM20184f);
        }
        runOnUiThread(new Runnable() { // from class: b3.e1
            @Override // java.lang.Runnable
            public final void run() {
                this.f3169b.m12277J0(friendM20184f);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: M0 */
    public /* synthetic */ void m12281M0(String str, String str2, String str3, String str4) {
        if (!isFinishing()) {
            C5152i0.m20065b(this.f10579h);
        }
        if ("200".equals(str3)) {
            C5187v0.m20267c(R.string.success);
            if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SUGGESTIONS) || this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND)) {
                Intent intent = new Intent();
                SuggestionFriend suggestionFriend = this.f10590s;
                if (suggestionFriend == null) {
                    suggestionFriend = new SuggestionFriend();
                    Friend friend = this.f10592u;
                    if (friend != null) {
                        suggestionFriend.f13759c = friend.f13645c;
                    }
                }
                intent.putExtra("suggestionFriend", suggestionFriend);
                intent.putExtra("type", this.f10584m.name());
                setResult(-1, intent);
            } else if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
                Intent intent2 = new Intent();
                DepartmentFriend departmentFriend = this.f10591t;
                if (departmentFriend == null) {
                    departmentFriend = new DepartmentFriend();
                    Friend friend2 = this.f10592u;
                    if (friend2 != null) {
                        departmentFriend.f13626b = friend2.f13645c;
                    }
                }
                intent2.putExtra("departmentFriend", departmentFriend);
                intent2.putExtra("type", this.f10584m.name());
                setResult(-1, intent2);
            } else if (getCallingActivity() == null) {
                Globals.m7388i0().m7490T2(true);
                Intent intent3 = new Intent();
                intent3.setClass(this, ULauncherActivity.class);
                intent3.putExtra("Tab_Index", 1);
                intent3.putExtra("checkLastMsg", true);
                intent3.setFlags(268468224);
                startActivity(intent3);
            } else {
                setResult(-1, new Intent());
            }
            finish();
        }
        this.f10584m = FPA_RETURN_TYPE.RETURN_NONE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N0 */
    public /* synthetic */ void m12283N0(View view) {
        m12334b1(this.f10586o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: O0 */
    public /* synthetic */ void m12285O0(View view) {
        CLUtility.m16589t1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q0 */
    public /* synthetic */ void m12289Q0(View view) {
        this.f10587p.m15719Z(this.f10592u, new C2128d(m7363d()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: R0 */
    public /* synthetic */ void m12290R0(View view) {
        this.f10587p.m15719Z(this.f10592u, new C2129e(m7363d()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: S0 */
    public /* synthetic */ void m12291S0(View view) {
        m12348w0(this.f10592u);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T0 */
    public /* synthetic */ void m12293T0(String str) {
        if ("200".equals(str)) {
            Intent intent = new Intent();
            if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
                intent.putExtra("departmentFriend", this.f10591t);
            } else {
                intent.putExtra("InvitationFriend", this.f10589r);
            }
            intent.putExtra("type", this.f10584m.name());
            setResult(-1, intent);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: U0 */
    public /* synthetic */ void m12294U0(String str, String str2, final String str3, String str4) {
        runOnUiThread(new Runnable() { // from class: b3.u0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3264b.m12293T0(str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V0 */
    public /* synthetic */ void m12296V0(String str, String str2, String str3, String str4) {
        Friend friendM20184f;
        if (!"200".equals(str3) || (friendM20184f = C5172p.m20184f(C5172p.m20195q(str4))) == null) {
            return;
        }
        this.f10592u = friendM20184f;
        C2950b0.m14899A().m15019k(friendM20184f, friendM20184f.f13658p, true);
        runOnUiThread(new Runnable() { // from class: b3.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3275b.m12343k1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W0 */
    public /* synthetic */ void m12297W0(EditText editText, long j9, Dialog dialog, View view) {
        String string = editText.getText().toString();
        Globals.m7388i0().m7462N3(string);
        m12350y0(j9, string);
        dialog.dismiss();
        CLUtility.m16589t1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X0 */
    public /* synthetic */ void m12299X0(Dialog dialog, View view) {
        dialog.dismiss();
        CLUtility.m16589t1(this);
    }

    /* renamed from: C0 */
    public final boolean m12331C0(long j9) {
        return C2950b0.m14918q().m15203j(Long.valueOf(j9));
    }

    /* renamed from: Z0 */
    public final void m12332Z0() {
        DepartmentFriend departmentFriend = this.f10591t;
        if (departmentFriend != null) {
            C6129c.m23481b(this, this.f10575d, departmentFriend);
            C6127a.m23468i(this, this.f10574c, this.f10591t);
            TextView textView = this.f10576e;
            if (textView != null) {
                textView.setText(this.f10591t.f13629e);
                this.f10577f.setText(this.f10591t.f13630f);
            }
        }
    }

    /* renamed from: a1 */
    public final void m12333a1() {
        SuggestionFriend suggestionFriend = this.f10590s;
        if (suggestionFriend != null) {
            C6129c.m23485f(this, this.f10575d, suggestionFriend);
            C6127a.m23472m(this, this.f10574c, this.f10590s);
            TextView textView = this.f10576e;
            if (textView != null) {
                textView.setText(this.f10590s.f13761e);
            }
            this.f10577f.setVisibility(8);
            if (TextUtils.isEmpty(this.f10590s.f13766j)) {
                this.f10593v = false;
            } else {
                this.f10593v = true;
                this.f10581j.setText(this.f10590s.f13766j);
            }
            m12341i1();
            C6289c.m24104c(String.valueOf(this.f10590s.f13759c), this.f10571G, this.f10572H).m24088p();
        }
    }

    /* renamed from: b1 */
    public final void m12334b1(boolean z8) {
        Friend friend;
        CLUtility.m16589t1(this);
        if (!z8) {
            finish();
            return;
        }
        if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION) || this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SENT_INVITATION) || this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
            FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: b3.f1
                @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
                /* renamed from: a */
                public final void mo134a(String str, String str2, String str3, String str4) {
                    this.f3174a.m12294U0(str, str2, str3, str4);
                }
            };
            FPA_RETURN_TYPE fpa_return_type = this.f10584m;
            if (fpa_return_type == FPA_RETURN_TYPE.RETURN_REJECT) {
                this.f10587p.m15707N0(this.f10589r.f13744e, interfaceC3051i);
                return;
            }
            if (fpa_return_type != FPA_RETURN_TYPE.RETURN_CANCEL) {
                if (fpa_return_type == FPA_RETURN_TYPE.RETURN_ACCEPT) {
                    this.f10587p.m15735p(this.f10589r.f13744e, interfaceC3051i);
                    return;
                }
                return;
            } else if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
                this.f10587p.m15739z(this.f10591t.f13628d, interfaceC3051i);
                return;
            } else {
                this.f10587p.m15739z(this.f10589r.f13744e, interfaceC3051i);
                return;
            }
        }
        if (!this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND) || (friend = this.f10592u) == null) {
            return;
        }
        if (!C5168n1.m20149f(friend.f13645c)) {
            if (this.f10584m.equals(FPA_RETURN_TYPE.RETURN_ADD_FRIEND)) {
                m12340h1(this.f10592u.m15620a(), this.f10592u.f13645c);
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("suggestionFriend", this.f10590s);
            setResult(-1, intent);
            intent.putExtra("type", this.f10584m.name());
            finish();
            return;
        }
        if (!this.f10584m.equals(FPA_RETURN_TYPE.RETURN_NONE)) {
            m12338f1(this.f10592u);
            return;
        }
        Group groupM15081r = C2950b0.m14912k().m15081r(this.f10592u.f13648f);
        Intent intent2 = new Intent();
        if (groupM15081r != null) {
            groupM15081r.f13717d = this.f10592u.m15621b();
            intent2.putExtra("StartOtherGroup", groupM15081r);
        }
        intent2.putExtra("userId", this.f10592u.f13645c);
        setResult(-1, intent2);
        finish();
    }

    /* renamed from: c1 */
    public final void m12335c1(Friend friend) {
        this.f10587p.m15719Z(friend, new C2125a(m7363d()));
    }

    /* renamed from: d1 */
    public final void m12336d1(long j9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", String.valueOf(j9)));
        this.f10587p.m15734m("user", "userInfoV2", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.v0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f3267a.m12296V0(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: e1 */
    public final void m12337e1(Friend friend) {
        this.f10592u = friend;
        friend.f13654l = false;
        this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND;
        runOnUiThread(new Runnable() { // from class: b3.z0
            @Override // java.lang.Runnable
            public final void run() {
                this.f3276b.m12349x0();
            }
        });
    }

    /* renamed from: f1 */
    public final void m12338f1(Friend friend) {
        this.f10587p.m15719Z(friend, new C2134j(m7363d()));
    }

    /* renamed from: g1 */
    public final void m12339g1(String str) {
        if (C5170o0.m20170e(str)) {
            return;
        }
        TextView textView = (TextView) findViewById(R.id.emailTextView);
        textView.setVisibility(0);
        textView.setText(str);
    }

    /* renamed from: h1 */
    public final void m12340h1(String str, final long j9) {
        final Dialog dialogM16384c = C3123g.m16384c(this);
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_friend_invitation, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.invitationDialogTitle);
        final EditText editText = (EditText) viewInflate.findViewById(R.id.invitationEdit);
        editText.requestFocus();
        textView.setText(getString(R.string.friend_invitation_info, str));
        editText.setText(Globals.m7388i0().m7561j0());
        ((Button) viewInflate.findViewById(R.id.sendInvitationBtn)).setOnClickListener(new View.OnClickListener() { // from class: b3.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3217b.m12297W0(editText, j9, dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.cancelinvitationBtn).setOnClickListener(new View.OnClickListener() { // from class: b3.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3239b.m12299X0(dialogM16384c, view);
            }
        });
        viewInflate.findViewById(R.id.invitationXImage).setOnClickListener(new View.OnClickListener() { // from class: b3.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                editText.setText("");
            }
        });
        dialogM16384c.setContentView(viewInflate);
        CLUtility.m16590t2(this, dialogM16384c);
        dialogM16384c.show();
    }

    /* renamed from: i1 */
    public final void m12341i1() {
        if (!this.f10593v && !this.f10594w) {
            findViewById(R.id.messageLayout).setVisibility(8);
            findViewById(R.id.horizontalLine).setVisibility(0);
            return;
        }
        findViewById(R.id.messageLayout).setVisibility(0);
        findViewById(R.id.horizontalLine).setVisibility(4);
        this.f10582k.setVisibility(8);
        this.f10581j.setVisibility(8);
        if (this.f10594w) {
            this.f10582k.setVisibility(0);
        } else if (this.f10593v) {
            this.f10581j.setVisibility(0);
        }
    }

    /* renamed from: j1 */
    public final void m12342j1(String str, String str2) {
        if (C5170o0.m20170e(str) && C5170o0.m20170e(str2)) {
            return;
        }
        TextView textView = (TextView) findViewById(R.id.organizationTextView);
        textView.setVisibility(0);
        if (!C5170o0.m20170e(str) && !C5170o0.m20170e(str2)) {
            textView.setText(getString(R.string.friend_title_at_company, str2, str));
        } else if (!C5170o0.m20170e(str)) {
            textView.setText(str);
        } else {
            if (C5170o0.m20170e(str2)) {
                return;
            }
            textView.setText(str2);
        }
    }

    /* renamed from: k1 */
    public final void m12343k1() {
        Friend friend = this.f10592u;
        if (friend == null) {
            return;
        }
        C6129c.m23482c(this, this.f10575d, friend);
        C6127a.m23469j(this, this.f10574c, this.f10592u);
        TextView textView = this.f10576e;
        if (textView != null) {
            textView.setText(this.f10592u.m15621b());
        }
        if (this.f10577f != null) {
            if (m12351z0(this.f10592u.m15621b(), this.f10592u.m15620a())) {
                this.f10577f.setVisibility(8);
            } else {
                this.f10577f.setVisibility(0);
                this.f10577f.setText(this.f10592u.m15620a());
            }
        }
        if (TextUtils.isEmpty(this.f10592u.f13662t)) {
            this.f10593v = false;
        } else {
            this.f10593v = true;
            this.f10581j.setText(this.f10592u.f13662t);
        }
        m12341i1();
        Friend friend2 = this.f10592u;
        m12342j1(friend2.f13665w, friend2.f13667y);
        m12339g1(this.f10592u.f13668z);
        if (!C5168n1.m20149f(this.f10592u.f13645c) || C5170o0.m20169d(this.f10592u.f13661s)) {
            this.f10578g.setVisibility(8);
        } else {
            this.f10578g.setVisibility(0);
            ((TextView) findViewById(R.id.publicId)).setText(getString(R.string.userID, this.f10592u.f13661s));
        }
    }

    /* renamed from: l1 */
    public final void m12344l1(String str, String str2) {
        if (this.f10576e != null && str != null && !str.isEmpty()) {
            this.f10576e.setText(str);
        }
        if (this.f10577f != null) {
            if (m12351z0(str, str2)) {
                this.f10577f.setVisibility(8);
            } else {
                this.f10577f.setVisibility(0);
                this.f10577f.setText(str2);
            }
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 != 0) {
            if (i9 == 1 && i10 == -1) {
                m12334b1(this.f10586o);
                return;
            }
            return;
        }
        if (i10 == -1) {
            this.f10586o = true;
            Friend friend = (Friend) intent.getParcelableExtra("data");
            this.f10592u = friend;
            m12344l1(friend.m15621b(), this.f10592u.m15620a());
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        m12334b1(this.f10586o);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_friend_profile);
        this.f10584m = FPA_RETURN_TYPE.RETURN_NONE;
        this.f10575d = (ImageView) findViewById(R.id.cover);
        this.f10574c = (ImageView) findViewById(R.id.avatar);
        this.f10576e = (TextView) findViewById(R.id.nickname);
        this.f10577f = (TextView) findViewById(R.id.displayname);
        this.f10578g = (TextView) findViewById(R.id.publicId);
        this.f10580i = findViewById(R.id.editName);
        this.f10581j = (TextView) findViewById(R.id.statusMessage);
        this.f10582k = (TextView) findViewById(R.id.invitedMessage);
        findViewById(R.id.contentLayout).setOnClickListener(this.f10596y);
        findViewById(R.id.addfriendLayout).setOnClickListener(this.f10597z);
        findViewById(R.id.addRequestBtn).setOnClickListener(this.f10597z);
        findViewById(R.id.blockFriendLayout).setOnClickListener(this.f10565A);
        findViewById(R.id.textViewNotNow).setOnClickListener(this.f10569E);
        findViewById(R.id.voiceCallLayout).setOnClickListener(this.f10566B);
        findViewById(R.id.videoCallLayout).setOnClickListener(this.f10567C);
        findViewById(R.id.chatLayout).setOnClickListener(this.f10568D);
        findViewById(R.id.cancel).setOnClickListener(this.f10569E);
        this.f10580i.setOnClickListener(this.f10570F);
        Intent intent = getIntent();
        if (intent == null) {
            this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_NONE;
        } else if (intent.getData() != null) {
            this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_INVITATION_LINK;
        } else {
            try {
                this.f10583l = FPA_PROFILE_TYPE.valueOf(intent.getStringExtra("type"));
            } catch (Exception e9) {
                Log.e("FriendProfileActivity", "[onCreate] Cannot get profile type: " + e9.getMessage());
                this.f10583l = FPA_PROFILE_TYPE.PROFILE_TYPE_NONE;
            }
        }
        m12349x0();
        m12346s0();
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C5152i0.m20065b(this.f10579h);
        Log.d("FriendProfileActivity", "onDestroy");
    }

    /* renamed from: r0 */
    public final void m12345r0() {
        final ProgressDialog progressDialogShow = ProgressDialog.show(this, "", getString(R.string.loading), true);
        this.f10587p.m15738y(this.f10590s.f13759c, new FriendsClient.InterfaceC3051i() { // from class: b3.s0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f3247a.m12269E0(progressDialogShow, str, str2, str3, str4);
            }
        });
    }

    /* renamed from: s0 */
    public final void m12346s0() {
        if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND)) {
            this.f10588q.execute(new Runnable() { // from class: b3.a1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3148b.m12273H0();
                }
            });
        }
    }

    /* renamed from: u0, reason: merged with bridge method [inline-methods] */
    public final void m12280L0(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteURL", str));
        this.f10587p.m15734m("friend", "friendInfoViaInviteURL", arrayList, new FriendsClient.InterfaceC3051i() { // from class: b3.t0
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f3262a.m12278K0(str2, str3, str4, str5);
            }
        });
    }

    /* renamed from: w0 */
    public final void m12348w0(Friend friend) {
        Intent intent = new Intent(this, (Class<?>) FriendInfoUpdateActivity.class);
        intent.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, getResources().getString(R.string.friend_change_title_firend_nikname));
        intent.putExtra(FirebaseAnalytics.Param.TERM, "nickname");
        intent.putExtra("data", friend);
        startActivityForResult(intent, 0);
    }

    /* renamed from: x0 */
    public final void m12349x0() {
        boolean z8;
        boolean z9;
        findViewById(R.id.back).setOnClickListener(this.f10595x);
        if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_REQUEST_INVITATION)) {
            this.f10585n = true;
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                InvitationFriend invitationFriend = (InvitationFriend) extras.getParcelable("InvitationFriend");
                this.f10589r = invitationFriend;
                if (invitationFriend != null) {
                    if (this.f10574c != null) {
                        C6129c.m23484e(this, this.f10575d, invitationFriend);
                        C6127a.m23471l(this, this.f10574c, this.f10589r);
                    }
                    TextView textView = this.f10576e;
                    if (textView != null) {
                        textView.setText(this.f10589r.f13748i);
                    }
                    if (this.f10577f != null) {
                        InvitationFriend invitationFriend2 = this.f10589r;
                        if (m12351z0(invitationFriend2.f13748i, invitationFriend2.f13741b)) {
                            this.f10577f.setVisibility(8);
                        } else {
                            this.f10577f.setVisibility(0);
                            this.f10577f.setText(this.f10589r.f13741b);
                        }
                    }
                    if (TextUtils.isEmpty(this.f10589r.f13752m)) {
                        this.f10593v = true;
                        this.f10581j.setText("  ");
                    } else {
                        this.f10594w = true;
                        this.f10582k.setText(this.f10589r.f13752m.trim());
                    }
                    m12341i1();
                    InvitationFriend invitationFriend3 = this.f10589r;
                    m12342j1(invitationFriend3.f13753n, invitationFriend3.f13754o);
                    m12339g1(this.f10589r.f13756q);
                }
            }
            findViewById(R.id.friendBtnLayout).setVisibility(8);
            findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
            findViewById(R.id.cancelBtnLayout).setVisibility(8);
            findViewById(R.id.buttonLayout).setVisibility(0);
            this.f10580i.setVisibility(8);
            this.f10578g.setVisibility(8);
            return;
        }
        if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SUGGESTIONS)) {
            this.f10585n = true;
            Bundle extras2 = getIntent().getExtras();
            if (extras2 != null) {
                this.f10590s = (SuggestionFriend) extras2.getParcelable("suggestionFriend");
                m12333a1();
            }
            findViewById(R.id.friendBtnLayout).setVisibility(8);
            findViewById(R.id.NotfriendBtnLayout).setVisibility(0);
            if (C5170o0.m20170e(this.f10590s.f13762f)) {
                findViewById(R.id.blockFriendLayout).setVisibility(8);
            } else {
                findViewById(R.id.blockFriendLayout).setVisibility(0);
            }
            findViewById(R.id.cancelBtnLayout).setVisibility(8);
            findViewById(R.id.buttonLayout).setVisibility(8);
            m12341i1();
            this.f10580i.setVisibility(8);
            this.f10578g.setVisibility(8);
            return;
        }
        if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_DEPARTMENTS)) {
            this.f10585n = true;
            Bundle extras3 = getIntent().getExtras();
            if (extras3 != null) {
                this.f10591t = (DepartmentFriend) extras3.getParcelable("departmentFriend");
                this.f10592u = (Friend) extras3.getParcelable("friendObj");
                m12332Z0();
                Iterator<InvitationFriend> it = new FriendsClient(true).m15702J(FriendsClient.InvitationFriendType.SENT).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InvitationFriend next = it.next();
                    DepartmentFriend departmentFriend = this.f10591t;
                    if (departmentFriend.f13626b == next.f13746g) {
                        departmentFriend.f13635k = true;
                        departmentFriend.f13628d = next.f13744e;
                        break;
                    }
                }
                DepartmentFriend departmentFriend2 = this.f10591t;
                z9 = departmentFriend2.f13635k;
                z8 = departmentFriend2.f13637m;
            } else {
                z8 = false;
                z9 = false;
            }
            Friend friend = this.f10592u;
            if (friend != null) {
                m12339g1(friend.f13668z);
                Friend friend2 = this.f10592u;
                m12342j1(friend2.f13665w, friend2.f13667y);
            } else {
                if (!C5170o0.m20170e(this.f10591t.f13638n)) {
                    m12339g1(this.f10591t.f13638n);
                }
                if (!C5170o0.m20170e(this.f10591t.f13639o)) {
                    m12342j1("", this.f10591t.f13639o);
                }
            }
            if (!TextUtils.isEmpty(this.f10591t.f13633i)) {
                this.f10593v = true;
                this.f10581j.setText(this.f10591t.f13633i);
            }
            if (z9) {
                findViewById(R.id.friendBtnLayout).setVisibility(8);
                findViewById(R.id.NotfriendBtnLayout).setVisibility(0);
                findViewById(R.id.cancelBtnLayout).setVisibility(0);
                findViewById(R.id.addfriendLayout).setVisibility(8);
                this.f10578g.setVisibility(8);
            } else if (z8) {
                findViewById(R.id.friendBtnLayout).setVisibility(0);
                findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
                findViewById(R.id.cancelBtnLayout).setVisibility(8);
                findViewById(R.id.addfriendLayout).setVisibility(8);
                if (TextUtils.isEmpty(this.f10591t.f13627c)) {
                    this.f10578g.setVisibility(8);
                } else {
                    this.f10578g.setVisibility(0);
                    ((TextView) findViewById(R.id.publicId)).setText(getString(R.string.userID, this.f10592u.f13661s));
                }
                findViewById(R.id.voiceCallLayout).setVisibility(0);
                findViewById(R.id.videoCallLayout).setVisibility(0);
                findViewById(R.id.chatLayout).setVisibility(0);
            } else {
                findViewById(R.id.friendBtnLayout).setVisibility(8);
                findViewById(R.id.NotfriendBtnLayout).setVisibility(0);
                findViewById(R.id.cancelBtnLayout).setVisibility(8);
                findViewById(R.id.addfriendLayout).setVisibility(0);
                this.f10578g.setVisibility(8);
                if (this.f10591t.f13626b <= 0) {
                    ((TextView) findViewById(R.id.addfriendTextView)).setText(R.string.invitation_sms_invite_btn);
                }
            }
            m12341i1();
            findViewById(R.id.buttonLayout).setVisibility(8);
            this.f10580i.setVisibility(8);
            return;
        }
        if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_SENT_INVITATION)) {
            this.f10585n = true;
            Bundle extras4 = getIntent().getExtras();
            if (extras4 != null) {
                InvitationFriend invitationFriend4 = (InvitationFriend) extras4.getParcelable("InvitationFriend");
                this.f10589r = invitationFriend4;
                if (invitationFriend4 != null) {
                    if (this.f10574c != null) {
                        C6129c.m23484e(this, this.f10575d, invitationFriend4);
                        C6127a.m23471l(this, this.f10574c, this.f10589r);
                    }
                    TextView textView2 = this.f10576e;
                    if (textView2 != null) {
                        textView2.setText(this.f10589r.f13748i);
                    }
                    if (this.f10577f != null) {
                        InvitationFriend invitationFriend5 = this.f10589r;
                        if (m12351z0(invitationFriend5.f13748i, invitationFriend5.f13741b)) {
                            this.f10577f.setVisibility(8);
                        } else {
                            this.f10577f.setVisibility(0);
                            this.f10577f.setText(this.f10589r.f13741b);
                        }
                    }
                    InvitationFriend invitationFriend6 = this.f10589r;
                    m12342j1(invitationFriend6.f13753n, invitationFriend6.f13754o);
                    C6289c.m24104c(String.valueOf(this.f10589r.f13746g), this.f10571G, this.f10572H).m24088p();
                }
            }
            findViewById(R.id.friendBtnLayout).setVisibility(8);
            findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
            findViewById(R.id.cancelBtnLayout).setVisibility(0);
            findViewById(R.id.buttonLayout).setVisibility(8);
            m12341i1();
            this.f10580i.setVisibility(8);
            this.f10578g.setVisibility(8);
            return;
        }
        if (!this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_FRIEND)) {
            if (this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_VIEW_QRCODE_SCAN_RESULT)) {
                Intent intent = getIntent();
                if (intent != null) {
                    final String stringExtra = intent.getStringExtra(Intents.Scan.RESULT);
                    if (stringExtra == null || stringExtra.isEmpty()) {
                        m12334b1(false);
                    } else {
                        new Thread(new Runnable() { // from class: b3.p0
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f3233b.m12280L0(stringExtra);
                            }
                        }).start();
                    }
                }
                findViewById(R.id.friendBtnLayout).setVisibility(8);
                findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
                findViewById(R.id.cancelBtnLayout).setVisibility(8);
                m12341i1();
                return;
            }
            if (!this.f10583l.equals(FPA_PROFILE_TYPE.PROFILE_TYPE_INVITATION_LINK)) {
                Log.d("FriendProfileActivity", "[initView] profile type: " + this.f10583l.name());
                return;
            }
            Uri data = getIntent().getData();
            if (data != null && data.getHost().equals("invite") && data.getQueryParameter("tab").equals("addFriend")) {
                this.f10587p.m15728g0(data.getQueryParameter("userId"), new C2132h());
            }
            findViewById(R.id.friendBtnLayout).setVisibility(8);
            findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
            findViewById(R.id.cancelBtnLayout).setVisibility(8);
            m12341i1();
            return;
        }
        this.f10585n = true;
        Bundle extras5 = getIntent().getExtras();
        if (extras5 != null) {
            if (this.f10592u == null) {
                this.f10592u = (Friend) extras5.getParcelable("friendObj");
            }
            Friend friend3 = this.f10592u;
            if (friend3 != null) {
                m12336d1(friend3.f13645c);
                m12343k1();
                findViewById(R.id.friendBtnLayout).setVisibility(8);
                findViewById(R.id.NotfriendBtnLayout).setVisibility(8);
                findViewById(R.id.buttonLayout).setVisibility(8);
                this.f10580i.setVisibility(8);
                Friend friend4 = this.f10592u;
                if (friend4 != null && friend4.f13645c != Globals.m7388i0().m7568k1().longValue()) {
                    if (C5168n1.m20149f(this.f10592u.f13645c)) {
                        findViewById(R.id.friendBtnLayout).setVisibility(0);
                        this.f10580i.setVisibility(0);
                        if (this.f10592u.f13660r.equals("Official") || this.f10592u.f13660r.equals("Corporate")) {
                            findViewById(R.id.voiceCallLayout).setVisibility(8);
                            findViewById(R.id.videoCallLayout).setVisibility(8);
                        }
                    } else if (C5168n1.m20150g(this.f10592u.f13645c)) {
                        findViewById(R.id.friendBtnLayout).setVisibility(0);
                        this.f10580i.setVisibility(0);
                        findViewById(R.id.voiceCallLayout).setVisibility(8);
                        findViewById(R.id.videoCallLayout).setVisibility(8);
                    } else if (m12331C0(this.f10592u.f13645c)) {
                        findViewById(R.id.friendBtnLayout).setVisibility(0);
                    } else {
                        findViewById(R.id.NotfriendBtnLayout).setVisibility(0);
                    }
                }
            }
        }
        m12341i1();
    }

    /* renamed from: y0 */
    public final void m12350y0(long j9, String str) {
        this.f10579h = ProgressDialog.show(this, "", getString(R.string.processing));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("inviteeId", String.valueOf(j9)));
        arrayList.add(new C6301o("message", str));
        FriendsClient.InterfaceC3051i interfaceC3051i = new FriendsClient.InterfaceC3051i() { // from class: b3.b1
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str2, String str3, String str4, String str5) {
                this.f3152a.m12281M0(str2, str3, str4, str5);
            }
        };
        this.f10573I = interfaceC3051i;
        this.f10587p.m15734m("invite", "inviteFriend", arrayList, interfaceC3051i);
    }

    /* renamed from: z0 */
    public final boolean m12351z0(String str, String str2) {
        return str != null && str.equals(str2);
    }
}
