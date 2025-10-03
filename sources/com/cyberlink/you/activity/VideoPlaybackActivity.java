package com.cyberlink.you.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import com.cyberlink.p030U.R;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.Globals;
import com.cyberlink.you.activity.share.ShareMediaActivity;
import com.cyberlink.you.activity.share.ShareType;
import com.cyberlink.you.chat.C2907m0;
import com.cyberlink.you.chat.C2925v;
import com.cyberlink.you.database.C2950b0;
import com.cyberlink.you.database.C2973l0;
import com.cyberlink.you.database.MessageObj;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.friends.Group;
import com.cyberlink.you.p036ui.C3123g;
import com.cyberlink.you.utility.Permission.Permission;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import p116k4.C5183t0;
import p116k4.C5187v0;
import p127l4.C5287b;
import p127l4.InterfaceC5288c;

/* loaded from: classes.dex */
public class VideoPlaybackActivity extends BaseActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    /* renamed from: d */
    public Uri f9511d;

    /* renamed from: e */
    public int f9512e;

    /* renamed from: f */
    public boolean f9513f;

    /* renamed from: g */
    public MediaController f9514g;

    /* renamed from: h */
    public VideoView f9515h;

    /* renamed from: i */
    public View f9516i;

    /* renamed from: j */
    public View f9517j;

    /* renamed from: k */
    public View f9518k;

    /* renamed from: l */
    public TextView f9519l;

    /* renamed from: m */
    public String f9520m;

    /* renamed from: n */
    public C2973l0 f9521n;

    /* renamed from: o */
    public Group f9522o;

    /* renamed from: p */
    public boolean f9523p;

    /* renamed from: q */
    public boolean f9524q;

    /* renamed from: c */
    public final int f9510c = 0;

    /* renamed from: r */
    public C2907m0.g f9525r = new C2907m0.g() { // from class: com.cyberlink.you.activity.vl
        @Override // com.cyberlink.you.chat.C2907m0.g
        /* renamed from: A */
        public final void mo118A() {
            this.f11831b.m10824e0();
        }
    };

    /* renamed from: s */
    public C2907m0.h f9526s = new C2907m0.h() { // from class: com.cyberlink.you.activity.wl
        @Override // com.cyberlink.you.chat.C2907m0.h
        /* renamed from: x */
        public final void mo119x(boolean z8) {
            this.f12238b.m10803V(z8);
        }
    };

    /* renamed from: t */
    public View.OnClickListener f9527t = new View.OnClickListener() { // from class: com.cyberlink.you.activity.xl
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12262b.m10804X(view);
        }
    };

    /* renamed from: u */
    public View.OnClickListener f9528u = new View.OnClickListener() { // from class: com.cyberlink.you.activity.yl
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f12289b.m10806Z(view);
        }
    };

    /* renamed from: v */
    public View.OnClickListener f9529v = new ViewOnClickListenerC1830b();

    /* renamed from: w */
    public View.OnClickListener f9530w = new ViewOnClickListenerC1831c();

    /* renamed from: com.cyberlink.you.activity.VideoPlaybackActivity$a */
    public class AsyncTaskC1829a extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ long f9531a;

        public AsyncTaskC1829a(long j9) {
            this.f9531a = j9;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            if (this.f9531a == -1) {
                return null;
            }
            VideoPlaybackActivity.this.f9521n = C2950b0.m14914m().m14725v(this.f9531a);
            return null;
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoPlaybackActivity$b */
    public class ViewOnClickListenerC1830b implements View.OnClickListener {
        public ViewOnClickListenerC1830b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10830b(DialogInterface dialogInterface, int i9) {
            Log.d("VideoPlaybackActivity", "Dialog click item : " + i9);
            if (i9 == 0) {
                VideoPlaybackActivity.this.m10825u();
            } else if (i9 == 1) {
                VideoPlaybackActivity.this.m10823d0();
            } else {
                if (i9 != 2) {
                    return;
                }
                VideoPlaybackActivity.this.m10826v();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(VideoPlaybackActivity.this);
            BaseActivity baseActivityM10821O = VideoPlaybackActivity.this.m10821O();
            MessageObj messageObjM15179r = C2950b0.m14916o().m15179r(VideoPlaybackActivity.this.f9520m);
            if (messageObjM15179r == null) {
                return;
            }
            builderM16382a.setItems(messageObjM15179r.m14745G() != null ? messageObjM15179r.m14745G().equals(String.valueOf(Globals.m7388i0().m7568k1())) ? new String[]{baseActivityM10821O.getString(R.string.menu_share), baseActivityM10821O.getString(R.string.save_to_my_device), baseActivityM10821O.getString(R.string.call_back)} : new String[]{baseActivityM10821O.getString(R.string.menu_share), baseActivityM10821O.getString(R.string.save_to_my_device)} : null, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.em
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f10416b.m10830b(dialogInterface, i9);
                }
            });
            builderM16382a.create().show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoPlaybackActivity$c */
    public class ViewOnClickListenerC1831c implements View.OnClickListener {
        public ViewOnClickListenerC1831c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public /* synthetic */ void m10832b(DialogInterface dialogInterface, int i9) {
            Log.d("VideoPlaybackActivity", "Dialog click item : " + i9);
            if (i9 == 0) {
                if (VideoPlaybackActivity.this.f9524q) {
                    VideoPlaybackActivity.this.m10823d0();
                    return;
                } else {
                    VideoPlaybackActivity.this.m10822P();
                    return;
                }
            }
            if (i9 == 1) {
                VideoPlaybackActivity.this.m10823d0();
            } else {
                if (i9 != 2) {
                    return;
                }
                VideoPlaybackActivity.this.m10820N();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AlertDialog.Builder builderM16382a = C3123g.m16382a(VideoPlaybackActivity.this);
            BaseActivity baseActivityM10821O = VideoPlaybackActivity.this.m10821O();
            builderM16382a.setItems(VideoPlaybackActivity.this.f9524q ? new String[]{baseActivityM10821O.getString(R.string.save_to_my_device)} : (VideoPlaybackActivity.this.f9521n == null || VideoPlaybackActivity.this.f9522o == null || VideoPlaybackActivity.this.f9522o.f13704C || (VideoPlaybackActivity.this.f9522o.f13729p > 0L ? 1 : (VideoPlaybackActivity.this.f9522o.f13729p == 0L ? 0 : -1)) <= 0) ? true : VideoPlaybackActivity.this.f9521n.m15136h().equals(String.valueOf(Globals.m7388i0().m7568k1())) ? new String[]{baseActivityM10821O.getString(R.string.menu_share), baseActivityM10821O.getString(R.string.save_to_my_device), baseActivityM10821O.getString(R.string.delete_btn)} : new String[]{baseActivityM10821O.getString(R.string.menu_share), baseActivityM10821O.getString(R.string.save_to_my_device)}, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.fm
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i9) {
                    this.f10503b.m10832b(dialogInterface, i9);
                }
            });
            builderM16382a.create().show();
        }
    }

    /* renamed from: com.cyberlink.you.activity.VideoPlaybackActivity$d */
    public class C1832d implements InterfaceC5288c {

        /* renamed from: a */
        public final /* synthetic */ Permission f9535a;

        public C1832d(Permission permission) {
            this.f9535a = permission;
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: a */
        public void mo6907a(boolean z8) {
            if (z8) {
                C5183t0.m20262b(VideoPlaybackActivity.this, this.f9535a);
            } else {
                C5187v0.m20267c(R.string.permission_denied);
            }
        }

        @Override // p127l4.InterfaceC5288c
        /* renamed from: b */
        public void mo6908b() {
            if (VideoPlaybackActivity.this.f9521n != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(VideoPlaybackActivity.this.f9521n);
                C2925v.m14626v0(VideoPlaybackActivity.this, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Q */
    public /* synthetic */ void m10801Q() {
        long jM14485J;
        if (this.f9522o != null) {
            jM14485J = C2907m0.m14454I().m14485J(this.f9522o.f13723j) + C2907m0.m14454I().m14481E(this.f9522o.f13727n, true);
        } else {
            jM14485J = 0;
        }
        TextView textView = this.f9519l;
        if (textView == null || this.f9523p || jM14485J == -1) {
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
        this.f9519l.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: T */
    public /* synthetic */ void m10802T(String str, String str2, String str3, String str4) {
        if (str3 == null) {
            Log.d("VideoPlaybackActivity", "[media.deleteMedia] Response is null");
            return;
        }
        if (!str3.equals("200")) {
            Log.d("VideoPlaybackActivity", "[media.deleteMedia] statusCode=" + str3);
            return;
        }
        C2950b0.m14914m().m14716m(String.valueOf(this.f9521n.m15144p()));
        Group groupM10737i1 = VideoListActivity.m10737i1();
        if (groupM10737i1 != null) {
            String strM15131c = this.f9521n.m15131c();
            if (!groupM10737i1.f13718e.equals(strM15131c) && C2950b0.m14911j().m15056i(strM15131c) == null) {
                Log.d("VideoPlaybackActivity", "Cannot sendDeleteMediaMessage by no group album info");
            }
        } else {
            Log.d("VideoPlaybackActivity", "Cannot sendDeleteMediaMessage by no group info");
        }
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        intent.putExtra("operationResult", true);
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: V */
    public /* synthetic */ void m10803V(boolean z8) {
        if (z8) {
            m10827w(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: X */
    public /* synthetic */ void m10804X(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Y */
    public /* synthetic */ void m10805Y(DialogInterface dialogInterface, int i9) {
        Log.d("VideoPlaybackActivity", "Dialog click item : " + i9);
        if (i9 == 0) {
            m10823d0();
        } else {
            if (i9 != 1) {
                return;
            }
            dialogInterface.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Z */
    public /* synthetic */ void m10806Z(View view) {
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        BaseActivity baseActivityM10821O = m10821O();
        builderM16382a.setItems(new String[]{baseActivityM10821O.getString(R.string.save_to_my_device), baseActivityM10821O.getString(R.string.cancel_text)}, new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.cm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i9) {
                this.f10354b.m10805Y(dialogInterface, i9);
            }
        });
        builderM16382a.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b0 */
    public /* synthetic */ void m10807b0(DialogInterface dialogInterface, int i9) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c0 */
    public /* synthetic */ void m10808c0() {
        if (isFinishing()) {
            return;
        }
        this.f9514g.show();
    }

    /* renamed from: N */
    public final void m10820N() {
        if (this.f9521n == null) {
            return;
        }
        VideoListActivity.m10734e1(m10821O(), this.f9522o, this.f9521n.m15144p(), new FriendsClient.InterfaceC3051i() { // from class: com.cyberlink.you.activity.dm
            @Override // com.cyberlink.you.friends.FriendsClient.InterfaceC3051i
            /* renamed from: a */
            public final void mo134a(String str, String str2, String str3, String str4) {
                this.f10383a.m10802T(str, str2, str3, str4);
            }
        });
    }

    /* renamed from: O */
    public final BaseActivity m10821O() {
        return this;
    }

    /* renamed from: P */
    public void m10822P() {
        Log.d("VideoPlaybackActivity", "[handleOnForwardMessageButtonClick]");
        VideoListActivity.m10736h1(this, new long[]{this.f9521n.m15144p()});
    }

    /* renamed from: d0 */
    public final void m10823d0() {
        Log.d("VideoPlaybackActivity", "[saveMediaToLocalDevice]");
        Permission permission = Build.VERSION.SDK_INT >= 33 ? Permission.VIDEO : Permission.STORAGE;
        C5287b.m20583f(permission, new C1832d(permission), this);
    }

    /* renamed from: e0 */
    public final void m10824e0() {
        if (C2907m0.m14454I().m14489N()) {
            m10827w(false);
        } else {
            C2907m0.m14454I().m14511u(this.f9526s);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
        if (i9 == 0 && i10 == -1) {
            finish();
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("VideoPlaybackActivity", "onCompletion");
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        boolean booleanExtra;
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_playback);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("video_playback_url")) {
                this.f9511d = Uri.parse(intent.getStringExtra("video_playback_url"));
            }
            this.f9520m = intent.getStringExtra(Constants.FirelogAnalytics.PARAM_MESSAGE_ID);
            this.f9523p = intent.getBooleanExtra("bulletinMode", false);
            booleanExtra = intent.getBooleanExtra("videoMessage", false);
            this.f9524q = intent.getBooleanExtra("isOpenFromMeeting", false);
            this.f9522o = (Group) intent.getParcelableExtra("Group");
        } else {
            booleanExtra = false;
        }
        if (this.f9511d == null) {
            finish();
            return;
        }
        this.f9519l = (TextView) findViewById(R.id.txt_badge);
        this.f9516i = findViewById(R.id.loading);
        View viewFindViewById = findViewById(R.id.VideoPlaybackBackBtn);
        this.f9517j = viewFindViewById;
        viewFindViewById.setOnClickListener(this.f9527t);
        View viewFindViewById2 = findViewById(R.id.VideoPlaybackMoreBtn);
        this.f9518k = viewFindViewById2;
        viewFindViewById2.setVisibility(0);
        if (this.f9523p) {
            this.f9518k.setOnClickListener(this.f9528u);
        } else if (booleanExtra) {
            this.f9518k.setOnClickListener(this.f9529v);
        } else {
            this.f9518k.setOnClickListener(this.f9530w);
        }
        Group group = this.f9522o;
        if (group != null && group.f13712K) {
            this.f9518k.setVisibility(8);
        }
        this.f9515h = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        this.f9514g = mediaController;
        this.f9515h.setMediaController(mediaController);
        this.f9515h.setVideoURI(this.f9511d);
        this.f9515h.setOnCompletionListener(this);
        this.f9515h.setOnPreparedListener(this);
        this.f9515h.setOnErrorListener(this);
        this.f9515h.requestFocus();
        new AsyncTaskC1829a(intent != null ? intent.getLongExtra("mediaId", -1L) : -1L).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onDestroy() {
        this.f9515h.setOnCompletionListener(null);
        this.f9515h.setOnPreparedListener(null);
        this.f9515h.setOnErrorListener(null);
        this.f9515h.setMediaController(null);
        C2925v.m14631y();
        super.onDestroy();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i9, int i10) {
        Log.d("VideoPlaybackActivity", "onError what=" + i9 + " extra=" + i10);
        Context contextM7372O = Globals.m7372O();
        AlertDialog.Builder builderM16382a = C3123g.m16382a(this);
        builderM16382a.setMessage(contextM7372O.getString(R.string.open_file_no_exist_on_server));
        builderM16382a.setPositiveButton(contextM7372O.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.cyberlink.you.activity.bm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i11) {
                this.f9762b.m10807b0(dialogInterface, i11);
            }
        });
        builderM16382a.setCancelable(false);
        builderM16382a.show();
        return true;
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f9512e = this.f9515h.getCurrentPosition();
        this.f9513f = !this.f9515h.isPlaying();
        this.f9515h.pause();
        C2907m0.m14454I().m14494V(this.f9525r);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("VideoPlaybackActivity", "onPrepared");
        this.f9516i.setVisibility(8);
        new Handler().post(new Runnable() { // from class: com.cyberlink.you.activity.zl
            @Override // java.lang.Runnable
            public final void run() {
                this.f12316b.m10808c0();
            }
        });
    }

    @Override // com.cyberlink.you.BaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f9515h.seekTo(this.f9512e);
        if (!this.f9513f) {
            this.f9515h.start();
        }
        C2907m0.m14454I().m14510t(this.f9525r);
        m10824e0();
    }

    /* renamed from: u */
    public final void m10825u() {
        MessageObj messageObjM15179r;
        if (this.f9520m == null || (messageObjM15179r = C2950b0.m14916o().m15179r(this.f9520m)) == null) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(messageObjM15179r.m14777o());
        Intent intent = new Intent(m10821O(), (Class<?>) ShareMediaActivity.class);
        intent.putStringArrayListExtra("sharedMediaMsgIdList", arrayList);
        intent.putExtra("shareType", ShareType.Forward.toString());
        startActivity(intent);
    }

    /* renamed from: v */
    public final void m10826v() {
        MessageObj messageObjM15179r;
        if (this.f9520m == null || (messageObjM15179r = C2950b0.m14916o().m15179r(this.f9520m)) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(messageObjM15179r.m14777o());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Boolean.valueOf(messageObjM15179r.m14752N()));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        boolean[] zArr = new boolean[arrayList2.size()];
        Iterator it = arrayList2.iterator();
        int i9 = 0;
        while (it.hasNext()) {
            zArr[i9] = ((Boolean) it.next()).booleanValue();
            i9++;
        }
        Intent intent = new Intent(m10821O(), (Class<?>) RecallMessageAlertActivity.class);
        intent.putExtra("message", strArr);
        intent.putExtra("isScheduleSend", zArr);
        m10821O().startActivityForResult(intent, 0);
    }

    /* renamed from: w */
    public final void m10827w(boolean z8) {
        if (z8) {
            C2907m0.m14454I().m14495W(this.f9526s);
        }
        m10821O().runOnUiThread(new Runnable() { // from class: com.cyberlink.you.activity.am
            @Override // java.lang.Runnable
            public final void run() {
                this.f9731b.m10801Q();
            }
        });
    }
}
