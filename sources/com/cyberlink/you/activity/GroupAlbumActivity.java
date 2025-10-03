package com.cyberlink.you.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseFragmentActivity;
import com.cyberlink.you.activity.chatdialog.ChatDialogActivity;
import com.cyberlink.you.activity.ulauncher.ULauncherActivity;
import com.cyberlink.you.chat.C2904l;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.chat.XMPPManager;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.groupalbum.GroupAlbumFragment;
import com.cyberlink.you.utility.ULogUtility;
import java.util.Map;
import p136m3.C5321e;

/* loaded from: classes.dex */
public class GroupAlbumActivity extends BaseFragmentActivity {

    /* renamed from: c */
    public TextView f7715c;

    /* renamed from: e */
    public Group f7717e;

    /* renamed from: f */
    public GroupAlbumFragment.GroupAlbumStatus f7718f;

    /* renamed from: d */
    public GroupAlbumFragment f7716d = null;

    /* renamed from: g */
    public View.OnClickListener f7719g = new View.OnClickListener() { // from class: com.cyberlink.you.activity.u3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11422b.m8230X0(view);
        }
    };

    /* renamed from: h */
    public View.OnClickListener f7720h = new View.OnClickListener() { // from class: com.cyberlink.you.activity.v3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11806b.m8231Y0(view);
        }
    };

    /* renamed from: i */
    public View.OnClickListener f7721i = new View.OnClickListener() { // from class: com.cyberlink.you.activity.w3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f11837b.m8232Z0(view);
        }
    };

    /* renamed from: j */
    public XMPPManager.AbstractC2868s f7722j = new C1480a(false);

    /* renamed from: k */
    public C5321e.m f7723k = new C1481b();

    /* renamed from: l */
    public C2907m0.g f7724l = new C2907m0.g() { // from class: com.cyberlink.you.activity.x3
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f12244b.m8238d1();
        }
    };

    /* renamed from: m */
    public C2907m0.h f7725m = new C2907m0.h() { // from class: com.cyberlink.you.activity.y3
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f12267b.m8233a1(z8);
        }
    };

    /* renamed from: com.cyberlink.you.activity.GroupAlbumActivity$a */
    public class C1480a extends XMPPManager.AbstractC2868s {
        public C1480a(boolean z8) {
            super(z8);
        }

        @Override // com.cyberlink.you.chat.XMPPManager.AbstractC2868s
        /* renamed from: e */
        public String mo8240e(C2904l c2904l) {
            if (!c2904l.m14399N() && !c2904l.m14391F() && c2904l.m14389D() != MessageObj.MessageType.Event && !c2904l.m14398M() && GroupAlbumActivity.this.f7717e != null) {
                c2904l.m14418h().equals(GroupAlbumActivity.this.f7717e.f13723j);
            }
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.GroupAlbumActivity$b */
    public class C1481b implements C5321e.m {
        public C1481b() {
        }

        @Override // p136m3.C5321e.m
        /* renamed from: A0 */
        public boolean mo8241A0(C2904l c2904l, Map<String, String> map) {
            String str = map.get("eventType");
            ULogUtility.m16670f("GroupAlbumActivity", "[mEventListener] eventType = " + str);
            if (str.equals("media.album.updated") || str.equals("media.album.deleted")) {
                Log.d("GroupAlbumActivity", "[mEventListener] update group album list");
                if (GroupAlbumActivity.this.f7716d == null) {
                    return true;
                }
                GroupAlbumActivity.this.f7716d.m15892J0();
                return true;
            }
            if (!str.equals("media.media.created") || !GroupAlbumActivity.this.f7718f.equals(GroupAlbumFragment.GroupAlbumStatus.Select_Case)) {
                return true;
            }
            GroupAlbumActivity groupAlbumActivity = GroupAlbumActivity.this;
            groupAlbumActivity.f7718f = groupAlbumActivity.f7716d.m15914e0();
            GroupAlbumActivity groupAlbumActivity2 = GroupAlbumActivity.this;
            groupAlbumActivity2.m8239e1(groupAlbumActivity2.f7718f);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: W0 */
    public /* synthetic */ void m8229W0() {
        ULogUtility.m16670f("GroupAlbumActivity", "album UpdateBadges isReady:" + C2907m0.m14454I().m14489N());
        int iM14480D = C2907m0.m14454I().m14480D();
        if (iM14480D == -1) {
            return;
        }
        if (iM14480D == 0) {
            this.f7715c.setVisibility(8);
            return;
        }
        if (iM14480D > 99) {
            this.f7715c.setText("N");
        } else {
            this.f7715c.setText(String.valueOf(iM14480D));
        }
        this.f7715c.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X0 */
    public /* synthetic */ void m8230X0(View view) {
        m8235V0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y0 */
    public /* synthetic */ void m8231Y0(View view) {
        GroupAlbumFragment groupAlbumFragment = this.f7716d;
        if (groupAlbumFragment == null) {
            return;
        }
        groupAlbumFragment.m15886D0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z0 */
    public /* synthetic */ void m8232Z0(View view) {
        GroupAlbumFragment groupAlbumFragment = this.f7716d;
        if (groupAlbumFragment == null) {
            return;
        }
        groupAlbumFragment.m15887E0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a1 */
    public /* synthetic */ void m8233a1(boolean z8) {
        if (z8) {
            m8234Q0(true);
        }
    }

    /* renamed from: Q0 */
    public final void m8234Q0(boolean z8) {
        if (z8) {
            C2907m0.m14454I().m14495W(this.f7725m);
        }
        runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.z3
            @Override // java.lang.Runnable
            public final void run() {
                this.f12294b.m8229W0();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[Catch: all -> 0x0071, Exception -> 0x0073, TRY_LEAVE, TryCatch #1 {Exception -> 0x0073, blocks: (B:2:0x0000, B:4:0x000e, B:18:0x003d, B:19:0x0041, B:20:0x0058, B:9:0x0024, B:12:0x002e), top: B:30:0x0000, outer: #0 }] */
    /* renamed from: V0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m8235V0() {
        char c9;
        try {
            try {
                if (!this.f7716d.m15890H0().equals(GroupAlbumFragment.GroupAlbumStatus.Select_Case)) {
                    String strM15888F0 = this.f7716d.m15888F0();
                    int iHashCode = strM15888F0.hashCode();
                    if (iHashCode != 28754244) {
                        c9 = (iHashCode == 685436431 && strM15888F0.equals("ChatDialogActivity")) ? (char) 0 : (char) 65535;
                        if (c9 != 0) {
                            Intent intent = new Intent(getApplicationContext(), (Class<?>) ChatDialogActivity.class);
                            intent.putExtra("Group", this.f7717e);
                            startActivity(intent);
                            finish();
                        } else if (c9 != 1) {
                            finish();
                        } else {
                            Intent intent2 = new Intent(getApplicationContext(), (Class<?>) ULauncherActivity.class);
                            intent2.putExtra("Tab_Index", 1);
                            startActivity(intent2);
                            finish();
                        }
                    } else {
                        if (strM15888F0.equals("ULauncherActivity")) {
                            c9 = 1;
                        }
                        if (c9 != 0) {
                        }
                    }
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        } finally {
            finish();
        }
    }

    /* renamed from: b1 */
    public final void m8236b1() {
        XMPPManager.m14184g0().m14207H(this.f7722j);
        C5321e.m20824o().m20875k(this.f7723k);
    }

    /* renamed from: c1 */
    public final void m8237c1() {
        XMPPManager.m14184g0().m14233Z0(this.f7722j);
        C5321e.m20824o().m20832B0(this.f7723k);
    }

    /* renamed from: d1 */
    public final void m8238d1() {
        if (C2907m0.m14454I().m14489N()) {
            m8234Q0(false);
        } else {
            C2907m0.m14454I().m14511u(this.f7725m);
        }
    }

    /* renamed from: e1 */
    public void m8239e1(GroupAlbumFragment.GroupAlbumStatus groupAlbumStatus) {
        if (groupAlbumStatus == null) {
            return;
        }
        if (groupAlbumStatus.equals(GroupAlbumFragment.GroupAlbumStatus.Select_Case)) {
            findViewById(R.id.GroupAlbumAddBtn).setOnClickListener(this.f7721i);
        } else {
            findViewById(R.id.GroupAlbumAddBtn).setOnClickListener(this.f7720h);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        m8235V0();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Group group;
        super.onCreate(bundle);
        setContentView(R.layout.activity_group_album);
        Intent intent = getIntent();
        this.f7717e = (Group) intent.getParcelableExtra("Group");
        this.f7718f = !intent.getBooleanExtra("select", false) ? GroupAlbumFragment.GroupAlbumStatus.Normal_Case : GroupAlbumFragment.GroupAlbumStatus.Select_Case;
        if (intent.getBooleanExtra("CopyToMyAlbum", false) || (group = this.f7717e) == null) {
            ((TextView) findViewById(R.id.topBarTitle)).setText(R.string.my_album_title);
        } else {
            String str = group.f13717d;
            if (str != null && !str.isEmpty()) {
                ((TextView) findViewById(R.id.topBarTitle)).setText(this.f7717e.f13717d);
            }
        }
        this.f7715c = (TextView) findViewById(R.id.groupAlbumBadge);
        findViewById(R.id.GroupAlbumBackBtn).setOnClickListener(this.f7719g);
        if (bundle != null) {
            this.f7716d = (GroupAlbumFragment) getSupportFragmentManager().mo1850g(bundle, GroupAlbumFragment.class.getName());
        }
        if (bundle == null) {
            this.f7716d = new GroupAlbumFragment();
            getSupportFragmentManager().mo1844a().m1980b(R.id.GroupAlbumFragmentContainer, this.f7716d).mo1794h();
        }
        m8239e1(this.f7718f);
        Group group2 = this.f7717e;
        if (group2 != null && (group2.f13738y.equals("Official") || this.f7717e.f13738y.equals("Corporate") || !C2925v.m14625v(this.f7717e))) {
            findViewById(R.id.GroupAlbumAddBtn).setVisibility(4);
        }
        m8236b1();
        m8238d1();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m8237c1();
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        C2907m0.m14454I().m14494V(this.f7724l);
    }

    @Override // com.cyberlink.you.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        C2907m0.m14454I().m14510t(this.f7724l);
        m8238d1();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Log.d("GroupAlbumActivity", "[onSaveInstanceState] in");
        super.onSaveInstanceState(bundle);
        if (this.f7716d != null) {
            getSupportFragmentManager().mo1855l(bundle, GroupAlbumFragment.class.getName(), this.f7716d);
        }
    }
}
