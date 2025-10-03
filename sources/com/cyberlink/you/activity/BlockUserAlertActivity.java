package com.cyberlink.you.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.Friend;
import com.cyberlink.you.friends.FriendsClient;
import java.util.ArrayList;
import p116k4.C5179r0;
import p173q2.C6127a;
import p201t3.C6301o;

/* loaded from: classes.dex */
public class BlockUserAlertActivity extends BaseActivity {

    /* renamed from: m */
    public static final Object f7411m = new Object();

    /* renamed from: d */
    public Friend f7413d;

    /* renamed from: e */
    public ImageView f7414e;

    /* renamed from: f */
    public TextView f7415f;

    /* renamed from: g */
    public FriendsClient f7416g;

    /* renamed from: h */
    public Button f7417h;

    /* renamed from: i */
    public View f7418i;

    /* renamed from: j */
    public boolean f7419j;

    /* renamed from: c */
    public String f7412c = "BlockUserAlertActivity";

    /* renamed from: k */
    public View.OnClickListener f7420k = new ViewOnClickListenerC1425a();

    /* renamed from: l */
    public View.OnClickListener f7421l = new ViewOnClickListenerC1426b();

    /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$a */
    public class ViewOnClickListenerC1425a implements View.OnClickListener {
        public ViewOnClickListenerC1425a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BlockUserAlertActivity.this.m7795v(false);
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$b */
    public class ViewOnClickListenerC1426b implements View.OnClickListener {

        /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$b$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BlockUserAlertActivity.this.f7417h.setEnabled(false);
            }
        }

        public ViewOnClickListenerC1426b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C5179r0.m20246a()) {
                return;
            }
            BlockUserAlertActivity.this.m7794u();
            BlockUserAlertActivity.this.m7796w().runOnUiThread(new a());
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$c */
    public class RunnableC1427c implements Runnable {
        public RunnableC1427c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (BlockUserAlertActivity.f7411m) {
                if (!BlockUserAlertActivity.this.f7419j) {
                    BlockUserAlertActivity.this.m7798z(true);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$d */
    public class C1428d implements FriendsClient.InterfaceC3051i {

        /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$d$a */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BlockUserAlertActivity.this.f7417h.setText(BlockUserAlertActivity.this.getString(R.string.retry));
                BlockUserAlertActivity.this.f7417h.setEnabled(true);
            }
        }

        public C1428d() {
        }

        @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
        /* renamed from: a */
        public void mo134a(String str, String str2, String str3, String str4) {
            synchronized (BlockUserAlertActivity.f7411m) {
                BlockUserAlertActivity.this.f7419j = true;
                if (str3 == null || !str3.equals("200")) {
                    Log.e(BlockUserAlertActivity.this.f7412c, "Block Friend Fail");
                    BlockUserAlertActivity.this.m7796w().runOnUiThread(new a());
                    BlockUserAlertActivity.this.m7798z(false);
                } else {
                    Log.d(BlockUserAlertActivity.this.f7412c, "Block Friend success");
                    BlockUserAlertActivity.this.m7795v(true);
                }
            }
        }
    }

    /* renamed from: com.cyberlink.you.activity.BlockUserAlertActivity$e */
    public class RunnableC1429e implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ boolean f7428b;

        public RunnableC1429e(boolean z8) {
            this.f7428b = z8;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BlockUserAlertActivity.this.f7418i != null) {
                BlockUserAlertActivity.this.f7418i.setVisibility(this.f7428b ? 0 : 8);
            }
        }
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_block_user_alert);
        Friend friend = (Friend) m7796w().getIntent().getParcelableExtra("friend");
        this.f7413d = friend;
        if (friend == null) {
            finish();
        }
        this.f7416g = new FriendsClient(true);
        m7797y();
    }

    /* renamed from: u */
    public final void m7794u() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C6301o("token", Globals.m7388i0().m7449L()));
        arrayList.add(new C6301o("userId", Long.toString(this.f7413d.f13645c)));
        new Handler().postDelayed(new RunnableC1427c(), 1000L);
        this.f7419j = false;
        this.f7416g.m15734m("friend", "blockFriend", arrayList, new C1428d());
    }

    /* renamed from: v */
    public final void m7795v(boolean z8) {
        Intent intent = new Intent();
        if (z8) {
            m7796w().setResult(-1, intent);
        } else {
            m7796w().setResult(0, intent);
        }
        finish();
        m7798z(false);
    }

    /* renamed from: w */
    public final Activity m7796w() {
        return this;
    }

    /* renamed from: y */
    public final void m7797y() {
        ImageView imageView = (ImageView) findViewById(R.id.BlockUserAlertAvatar);
        this.f7414e = imageView;
        C6127a.m23469j(this, imageView, this.f7413d);
        ((TextView) findViewById(R.id.BlockUserAlertDisplayName)).setText(this.f7413d.m15621b());
        this.f7415f = (TextView) findViewById(R.id.BlockUserAlertListLocation);
        this.f7415f.setText(String.format(getResources().getString(R.string.block_list_location_note), this.f7413d.m15621b()));
        Button button = (Button) findViewById(R.id.BlockUserAlertBlock);
        this.f7417h = button;
        button.setOnClickListener(this.f7421l);
        findViewById(R.id.BlockUserAlertBackBtn).setOnClickListener(this.f7420k);
        findViewById(R.id.BlockUserAlertCancel).setOnClickListener(this.f7420k);
        this.f7418i = findViewById(R.id.waitingCursor);
    }

    /* renamed from: z */
    public void m7798z(boolean z8) {
        runOnUiThread(new RunnableC1429e(z8));
    }
}
